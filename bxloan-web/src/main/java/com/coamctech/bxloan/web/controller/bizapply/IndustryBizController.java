package com.coamctech.bxloan.web.controller.bizapply;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coamctech.bxloan.commons.controller.BaseController;
import com.coamctech.bxloan.commons.result.DataTablesPage;
import com.coamctech.bxloan.commons.result.Result;
import com.coamctech.bxloan.commons.utils.CommonHelper;
import com.coamctech.bxloan.datadict.DataDict;
import com.coamctech.bxloan.entity.CommonInfo;
import com.coamctech.bxloan.entity.FamilyConsume;
import com.coamctech.bxloan.entity.IndustryBizIncomeCost;
import com.coamctech.bxloan.entity.IndustryBizStock;
import com.coamctech.bxloan.entity.OtherIncomeCommon;
import com.coamctech.bxloan.service.bizapply.ExpectCashFlowInfoService;
import com.coamctech.bxloan.service.bizapply.IndustryBizService;
import com.coamctech.bxloan.service.bizapply.StatisticsService;
import com.coamctech.bxloan.service.model.bizapply.IndustryBizIncomeCostAgrVO;
import com.coamctech.bxloan.service.model.bizapply.IndustryBizIncomeCostVO;
import com.coamctech.bxloan.service.model.bizapply.IndustryBizVO;
import com.coamctech.bxloan.service.model.bizapply.OtherIncomeCommonVO;
import com.coamctech.bxloan.service.model.bizapply.Statistics;
import com.coamctech.bxloan.service.utils.EcfiObjTypeEnum;

@Controller
@RequestMapping("/industryBiz")
public class IndustryBizController extends BaseController {
	@Autowired
	private IndustryBizService industryBizService;
	@Autowired
	private StatisticsService statisticsService;
	@Autowired
	private DataDict dataDict;
	@Autowired
	private ExpectCashFlowInfoService expectedCashFlowInfoService;

	@RequestMapping("/saveBasicInfo")
	@ResponseBody
	public Result saveBasicInfo(IndustryBizVO vo) {
		try {
			Map<String, Long> idMap = industryBizService.saveBasicInfo(vo);
			return success(IndustryBizService.Msg.SUC.getMsg(), idMap);
		} catch (Exception e) {
			e.printStackTrace();
			return failure(IndustryBizService.Msg.ERR.getMsg());
		}
	}
	
	@RequestMapping("/saveOtherBizIncome")
	@ResponseBody
	public Result saveOtherBizIncome(IndustryBizIncomeCostVO vo) {
		try {
			industryBizService.saveIndustryBizIncomeCost(vo);
			return success(IndustryBizService.Msg.SUC.getMsg());
		} catch (Exception e) {
			e.printStackTrace();
			return failure(IndustryBizService.Msg.ERR.getMsg());
		}
	}
	
	@RequestMapping("/saveOtherBizIncomeAgr")
	@ResponseBody
	public Result saveOtherBizIncomeAgr(IndustryBizIncomeCostAgrVO vo) {
		try {
			BigDecimal bid = new BigDecimal(0);
			industryBizService.saveIndustryBizIncomeCost(vo);
			if(vo.getId() == null){
				bid = industryBizService.getMaxId();
			}else{
				bid = BigDecimal.valueOf(vo.getId());
			}
			return success(IndustryBizService.Msg.SUC.getMsg(),bid);
		} catch (Exception e) {
			e.printStackTrace();
			return failure(IndustryBizService.Msg.ERR.getMsg());
		}
	}

	@RequestMapping("/initOtherBizIncomeTable")
	@ResponseBody
	public DataTablesPage initOtherBizIncomeTable(
			@RequestParam("sEcho") Integer sEcho,
			@RequestParam("iDisplayStart") Integer firstIndex,
			@RequestParam("iDisplayLength") Integer pageSize,
			HttpServletRequest request) {
		final Long projectId = CommonHelper.toLong(request.getParameter("projectId"));
		final String type = request.getParameter("type");
		final String futurePastType = request.getParameter("futurePastType");
		// 查询
		Page<IndustryBizIncomeCost> page = industryBizService.findIncomeCostPageByProIdAndTypeAndFuturePastType((firstIndex / pageSize), pageSize, projectId, type, futurePastType);
		
		// 结果处理
		DataTablesPage dtPage = new DataTablesPage(sEcho, page);

		return dtPage;
	}
	
	@RequestMapping("/findOneBizIncome")
	@ResponseBody
	public IndustryBizIncomeCostVO findOneBizIncome(Long id) {
		return industryBizService.findOneBizIncomeVO(id);
	}
	
	@RequestMapping("/findOneBizIncomeAgr")
	@ResponseBody
	public IndustryBizIncomeCostAgrVO findOneBizIncomeAgr(Long id) {
		return industryBizService.findOneBizIncomeAgrVO(id);
	}
	
	@RequestMapping("/deleteBizIncome")
	@ResponseBody
	public Result deleteBizIncome(Long id) {
		try {
			IndustryBizIncomeCost ibc = industryBizService.findOne(id);
			industryBizService.deleteBizIncome(id);
			
			//删除成功后，再删除对应的收入支出
			String type = dataDict.getCodeVal("AgroNCulitivateTbType", "S2");
			if(ibc.getFuturePastType().equals(type)){//未来12个月
				//expectedCashFlowInfoService.deleteByObjId(id);
				expectedCashFlowInfoService.deleteEcfiByObj(ibc.getProjectId(), EcfiObjTypeEnum.INDUSTRY_BIZ, ibc.getId(), null);
			}
			
			return success("删除成功！");
		} catch (Exception e) {
			logger.error("删除失败,原因："+e.getMessage());
			return failure("删除失败！");
		}
	}
	
	@RequestMapping("/initStockTable")
	@ResponseBody
	public DataTablesPage initStockTable(
			@RequestParam("sEcho") Integer sEcho,
			@RequestParam("iDisplayStart") Integer firstIndex,
			@RequestParam("iDisplayLength") Integer pageSize,
			HttpServletRequest request) {
		final Long projectId = CommonHelper.toLong(request.getParameter("projectId"));
		// 查询
		Page<IndustryBizStock> page = industryBizService.findStockPageByProId((firstIndex / pageSize), pageSize, projectId);
		
		// 结果处理
		DataTablesPage dtPage = new DataTablesPage(sEcho, page);

		return dtPage;
	}
	
	@RequestMapping("/saveStock")
	@ResponseBody
	public Result saveStock(IndustryBizStock industryBizStock) {
		try {
			industryBizService.saveStock(industryBizStock);
			return success(IndustryBizService.Msg.SUC.getMsg());
		} catch (Exception e) {
			e.printStackTrace();
			return failure(IndustryBizService.Msg.ERR.getMsg());
		}
	}
	
	@RequestMapping("/findOneStock")
	@ResponseBody
	public IndustryBizStock findOneStock(Long id) {
		return industryBizService.findOneStock(id);
	}
	
	@RequestMapping("/deleteStock")
	@ResponseBody
	public Result deleteStock(Long id) {
		try {
			industryBizService.deleteStock(id);
			return success(IndustryBizService.Msg.SUC.getMsg());
		} catch (Exception e) {
			return failure(IndustryBizService.Msg.ERR.getMsg());
		}
	}
	
	@RequestMapping("/initOtherIncomeCommonTable")
	@ResponseBody
	public DataTablesPage initOtherIncomeCommonTable(
			@RequestParam("sEcho") Integer sEcho,
			@RequestParam("iDisplayStart") Integer firstIndex,
			@RequestParam("iDisplayLength") Integer pageSize,
			HttpServletRequest request) {
		final Long projectId = CommonHelper.toLong(request.getParameter("projectId"));
		final String type = request.getParameter("type");
		// 查询
		Page<OtherIncomeCommon> page = industryBizService.findOtherIncomeCommonPageByProId((1 / 10), 10, projectId, type);
		
		// 结果处理
		DataTablesPage dtPage = new DataTablesPage(sEcho, page);

		return dtPage;
	}
	
	@RequestMapping("/saveOtherIncomeCommon")
	@ResponseBody
	public Result saveOtherIncomeCommon(OtherIncomeCommonVO vo) {
		try {
			industryBizService.saveOtherIncomeCommon(vo);
			return success(IndustryBizService.Msg.SUC.getMsg());
		} catch (Exception e) {
			e.printStackTrace();
			return failure(IndustryBizService.Msg.ERR.getMsg());
		}
	}
	
	@RequestMapping("/findOneOtherIncomeCommon")
	@ResponseBody
	public OtherIncomeCommon findOneOtherIncomeCommon(Long id) {
		return industryBizService.findOneOtherIncomeCommon(id);
	}
	
	@RequestMapping("/deleteOtherIncomeCommon")
	@ResponseBody
	public Result deleteOtherIncomeCommon(Long id) {
		try {
			industryBizService.deleteOtherIncomeCommon(id);
			return success(IndustryBizService.Msg.SUC.getMsg());
		} catch (Exception e) {
			e.printStackTrace();
			return failure(IndustryBizService.Msg.ERR.getMsg());
		}
	}
	
	@RequestMapping("/saveFamilyConsume")
	@ResponseBody
	public Result saveFamilyConsume(FamilyConsume vo) {
		try {
			Long id = industryBizService.saveFamilyConsume(vo);
			return success(IndustryBizService.Msg.SUC.getMsg(), id);
		} catch (Exception e) {
			e.printStackTrace();
			return failure(IndustryBizService.Msg.ERR.getMsg());
		}
	}
	
	@RequestMapping("/findOneIndustryBizVOByProjectId")
	@ResponseBody
	public IndustryBizVO findOneIndustryBizVOByProjectId(Long projectId) {
		return industryBizService.findOneIndustryBizVOByProjectId(projectId);
	}
	
	@RequestMapping("/findOneFamilyConsumeByProIdAndType")
	@ResponseBody
	public FamilyConsume findOneFamilyConsumeByProIdAndType(Long projectId, String type) {
		return industryBizService.findOneFamilyConsumeByProIdAndType(projectId, type);
	}
	
	@RequestMapping("/initStatisticsTable")
	@ResponseBody
	public DataTablesPage initStatisticsTable(
			@RequestParam("sEcho") Integer sEcho,
			HttpServletRequest request) {
		final Long projectId = CommonHelper.toLong(request.getParameter("projectId"));
		// 查询
		Page<Statistics> page = statisticsService.findBySearch((1 / 10), 10, projectId);
		
		// 结果处理
		DataTablesPage dtPage = new DataTablesPage(sEcho, page);

		return dtPage;
	}
	
	@RequestMapping("/findCommonInfoByProIdAndType")
	@ResponseBody
	public List<CommonInfo> findCommonInfoByProIdAndType(Long projectId, String type) {
		return industryBizService.findOneCommonInfoByProIdAndType(projectId, type);
	}

	@RequestMapping("/saveCommonInfo")
	@ResponseBody
	public Result saveCommonInfo(CommonInfo vo) {
		try {
			List<CommonInfo> list=findCommonInfoByProIdAndType(vo.getProjectId(),vo.getType());
			if(list!=null && list.size()>0){
				if(vo.getPrice1()==null){
					vo.setPrice1(list.get(0).getPrice1());
				}
				if(vo.getRemarks1()==null || vo.getRemarks1().length()==0){
					vo.setRemarks1(list.get(0).getRemarks1());
				}
				if(vo.getRemarks2()==null || vo.getRemarks2().length()==0){
					vo.setRemarks2(list.get(0).getRemarks2());
				}
			}
			Long id = industryBizService.saveCommonInfo(vo);
			return success(IndustryBizService.Msg.SUC.getMsg(), id);
		} catch (Exception e) {
			e.printStackTrace();
			return failure(IndustryBizService.Msg.ERR.getMsg());
		}
	}
}
