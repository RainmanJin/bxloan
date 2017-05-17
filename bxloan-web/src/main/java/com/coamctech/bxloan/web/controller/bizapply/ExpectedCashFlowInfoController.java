package com.coamctech.bxloan.web.controller.bizapply;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coamctech.bxloan.commons.controller.BaseController;
import com.coamctech.bxloan.commons.result.Result;
import com.coamctech.bxloan.commons.utils.CommonHelper;
import com.coamctech.bxloan.commons.utils.FileUtils;
import com.coamctech.bxloan.commons.utils.excel.PoiExcel;
import com.coamctech.bxloan.entity.ExpectedCashFlowInfo;
import com.coamctech.bxloan.service.bizapply.BizApplyInfoCountService;
import com.coamctech.bxloan.service.bizapply.ExpectCashFlowInfoService;
import com.coamctech.bxloan.service.model.tipmsg.MsgResult;
import com.coamctech.bxloan.service.model.tipmsg.ResultEnums;

/**
 * 收入支出情况
 * 
 * @author gph
 * 
 */
@Controller
@RequestMapping("/expectCashFlowInfo")
public class ExpectedCashFlowInfoController extends BaseController {

	@Autowired
	private ExpectCashFlowInfoService expectedCashFlowInfoService;
	
	@Autowired
	private BizApplyInfoCountService bizApplyInfoCountService;

	@RequestMapping("saveExpectCashFlowInfo")
	@ResponseBody
	@Deprecated
	public Result save(HttpServletRequest request) {
		Long eid = 0L;
		try {
			ExpectedCashFlowInfo ecfi = new ExpectedCashFlowInfo();
			String objIdStr=request.getParameter("objId");
			if(StringUtils.isNumeric(objIdStr)){
				ecfi.setObjId(Long.valueOf(objIdStr));
			}
			ecfi.setObjType(request.getParameter("objType"));
			ecfi.setAmtMoney(new BigDecimal(request.getParameter("amtMoney")));
			ecfi.setMonthOfYear(CommonHelper.str2Date(
					request.getParameter("monthOfyear"), "yyyy-MM"));
			ecfi.setIncomeExpendFlag(request.getParameter("incomeExpendFlag"));
			ecfi.setObjContent(request.getParameter("objContent"));
			ecfi.setProjectId(Long.parseLong(request.getParameter("projectId")));
			ecfi.setObjName(request.getParameter("cultivateContent"));
			ecfi.setCreateTime(new Date());
			ecfi.setUpdateTime(new Date());
			ecfi.setObjCode(request.getParameter("objCode"));//条目编号
			
			//得到查询结果
			Long count = expectedCashFlowInfoService.checkMonthIsNotExist(ecfi);
			
			if(count >= 1){//如果还有相同的月份则不能修改
				return failure("该月份已存在！");
			}
			expectedCashFlowInfoService.save(ecfi);

			// 如果保存成功，返回当前数据的id
			eid = expectedCashFlowInfoService.getMaxId();

		} catch (Exception e) {
			logger.error("保存失败,原因：" + e.getMessage());
			return failure("保存失败!");
		}
		return success("保存成功！",eid);
	}
	@RequestMapping("modifyExpectCashFlowInfo")
	@ResponseBody
	@Deprecated
	public Result modify(HttpServletRequest request) {

		try {
			String id = request.getParameter("id");
			String monthOfYear = request.getParameter("monthOfyear");
			String amtMoney = request.getParameter("amtMoney");
			String objContent = request.getParameter("objContent");
			Date date = CommonHelper.formatDate(
					CommonHelper.str2Date(monthOfYear, "yyyy-MM"),
					"yyyy-MM");
			String flag = request.getParameter("flag");
			
			if(StringUtils.isEmpty(flag)){
				return failure("修改失败！");
			}
			//查找实例对象
			ExpectedCashFlowInfo ecf = expectedCashFlowInfoService
					.getExpectedCashFlowInfoById(Long.valueOf(id));
			//得到查询结果
			Long count = expectedCashFlowInfoService.checkMonthIsNotExist(ecf);
			
			if(count >= 1 ){//如果还有相同的月份则不能修改
				return failure("该月份已存在！");
			}
			if (ecf != null) {
				// 修改
				ecf.setMonthOfYear(date);
				ecf.setAmtMoney(new BigDecimal(amtMoney));
				ecf.setObjContent(objContent);
				ecf.setUpdateTime(new Date());
				expectedCashFlowInfoService.save(ecf);
				
			} else {
				return failure("修改失败！");
			}

		} catch (Exception e) {
			logger.error("修改数据失败，原因：" + e.getMessage());
			return failure("修改失败！");
		}
		return success("修改成功！");
	}
	/**
	 * 保存
	 * @param info
	 * @param req
	 * @return
	 */
	@RequestMapping("saveEcfi")
	@ResponseBody
	public Result saveEcfi(ExpectedCashFlowInfo info,HttpServletRequest req){
		String msg = "该月份已经填写过了，如需修改，请对这个月份数据进行修改！";
		try {
			String monthOfYearStr=req.getParameter("monthOfYearStr");
			info.setMonthOfYear(CommonHelper.str2Date(monthOfYearStr, "yyyy-MM"));
			
			if(expectedCashFlowInfoService.checkMonthIsExist(info)){
				return failure(msg);
			}
			info=expectedCashFlowInfoService.save(info);
			return success("保存成功！",info.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return failure("保存失败！");
	}
	/**
	 * 删除
	 * @param eid
	 * @return
	 */
	@RequestMapping("delExpectCashFlowInfo")
	@ResponseBody
	public Result delete(String eid) {
		try {
			if (!StringUtils.isNumeric(eid)) {
				return failure("参数错误");
			}
			expectedCashFlowInfoService.delete(Long.valueOf(eid));
		} catch (Exception e) {
			logger.error("删除失败,原因：" + e.getMessage());
			return failure("删除失败");
		}
		return success("删除成功");
	}

	/**
	 * 查询
	 * @param projectId
	 * @param objId
	 * @param objCode
	 * @param objType
	 * @param flag
	 * @return
	 */
	@RequestMapping("findIncomeOrConsumeList")
	@ResponseBody
	public Result findAll(Long projectId,Long objId, String objCode, String objType, String flag) {
		List<ExpectedCashFlowInfo> list;
		try {
			list = expectedCashFlowInfoService.findAll(projectId,objType,objId, objCode, flag);
		} catch (Exception e) {
			logger.error("查询收入支出列表失败，原因：" + e.getMessage());
			return failure("查询失败");
		}
		return success(list);
	}
	
	
	
	/**
	 * 下载预期现金流前校验
	 * @return
	 */
	@RequestMapping("downloadEcfiBefore")
	@ResponseBody
	public Result downloadEcfiBefore(Long projId,HttpServletRequest req,HttpServletResponse resp){
		logger.info("projId：{}",projId);
		try {
			MsgResult msg= bizApplyInfoCountService.checkFinancialStatements(projId);
			if(msg.equalsMsgResult(ResultEnums.SUCCESS)){
				return success("");
			}
			return failure(msg.getDesc());
			/*poiExcel.write(FileUtils.initDownload(req, resp, poiExcel.getExcelName()));*/
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("查询收入支出列表失败，原因：" + e.getMessage());
		}
		return failure("数据错误，暂无法生成预期现金流");
	}
	/**
	 * 下载预期现金流、损溢、资产负债
	 * @param projId
	 * @param req
	 * @param resp
	 */
	@RequestMapping("downloadEcfi")
	public void downloadEcfi(Long projId,HttpServletRequest req,HttpServletResponse resp){
		try {
			PoiExcel poiExcel=bizApplyInfoCountService.createProfitLossAsseLliabExcel(projId);
			poiExcel.write(FileUtils.initDownload(req, resp, poiExcel.getExcelName()));
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("导出预期现金流、损溢、资产负债，原因：" + e.getMessage());
		}
	}
}
