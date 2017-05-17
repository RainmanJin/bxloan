package com.coamctech.bxloan.web.controller.collateral;

import static com.coamctech.bxloan.commons.GlobalConstants.Collateral;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coamctech.bxloan.commons.GlobalConstants;
import com.coamctech.bxloan.commons.GlobalConstants.DocStageCode;
import com.coamctech.bxloan.commons.controller.BaseController;
import com.coamctech.bxloan.commons.result.DataTablesPage;
import com.coamctech.bxloan.commons.result.Result;
import com.coamctech.bxloan.commons.utils.CommonHelper;
import com.coamctech.bxloan.commons.utils.DateTools;
import com.coamctech.bxloan.commons.utils.MathUtil;
import com.coamctech.bxloan.datadict.DataDict;
import com.coamctech.bxloan.document.CustDocInfo;
import com.coamctech.bxloan.entity.Collateral;
import com.coamctech.bxloan.entity.Individual;
import com.coamctech.bxloan.entity.LandProduce;
import com.coamctech.bxloan.entity.MachineEquipmentMortgage;
import com.coamctech.bxloan.entity.ProjectPawnInfo;
import com.coamctech.bxloan.entity.RealEstateMortgage;
import com.coamctech.bxloan.entity.TrafficCar;
import com.coamctech.bxloan.service.collateral.CollateralService;
import com.coamctech.bxloan.service.model.collateral.CollateralVO;
import com.coamctech.bxloan.service.model.tipmsg.MsgResult;
import com.coamctech.bxloan.service.model.tipmsg.ResultEnums;
import com.coamctech.bxloan.service.sysmng.DocumentService;
import com.coamctech.bxloan.service.sysmng.ICommonBizNumberBS;
import com.coamctech.bxloan.web.security.ShiroUser;
import com.coamctech.bxloan.web.vo.collateral.CollateralItemVo;
import com.coamctech.bxloan.web.vo.customermng.DocumentUploadVO;
import com.google.common.base.Function;
import com.google.common.collect.Lists;

/**
 * 抵质押管理
 * 
 * @author wf
 * 
 */
@Controller
@RequestMapping("/" + Collateral)
public class CollateralController extends BaseController {

	@Autowired
	private CollateralService collateralService;
	@Autowired
	private DocumentService documentService;
	@Autowired
	private ICommonBizNumberBS commonBizNumberBS;

	@Autowired
	private DataDict dataDict;

	@RequestMapping
	public String index() {
		return Collateral + "/main";
	}

	@RequestMapping("/findBySearch")
	@ResponseBody
	public DataTablesPage findBySearch(@RequestParam("sEcho") Integer sEcho,
			@RequestParam("iDisplayStart") Integer firstIndex,
			@RequestParam("iDisplayLength") Integer pageSize,
			HttpServletRequest request, String guarantorName,
			String guarantyName, String guarantyStatusCd, String guaranteeMode,
			Long projectId) {

		ShiroUser curUser = (ShiroUser) SecurityUtils.getSubject()
				.getPrincipal();

		Collateral collateral = new Collateral();
		collateral.setGuarantorName(guarantorName);
		collateral.setGuarantyName(guarantyName);
		if (StringUtils.isBlank(guarantyStatusCd)
				|| StringUtils.equalsIgnoreCase("all", guarantyStatusCd)) {
			collateral.setGuarantyStatusCd(null);
		} else {
			collateral.setGuarantyStatusCd(guarantyStatusCd);
		}
		//modify by mz 20150702 start
		Page<Object[]> page = collateralService.findBySearch(
				(firstIndex / pageSize), pageSize, collateral, guaranteeMode,
				curUser.getLogOrgid(), projectId);
		//modify by mz 20150702 end
		// 结果处理
		DataTablesPage dtPage = new DataTablesPage(sEcho, page);
		dtPage.setAaData(Lists.transform(page.getContent(),
				new Function<Object[], CollateralItemVo>() {
					@Override
					public CollateralItemVo apply(Object[] objs) {
						return new CollateralItemVo(objs);
					}
				}));
		return dtPage;
	}

	@RequestMapping("/findCustomersBySearch")
	@ResponseBody
	public DataTablesPage findCustomersBySearch(
			@RequestParam("sEcho") Integer sEcho,
			@RequestParam("iDisplayStart") Integer firstIndex,
			@RequestParam("iDisplayLength") Integer pageSize,
			HttpServletRequest request) {
		String customerName = request.getParameter("customerName");
		String certificateTypeCd = request.getParameter("certificateTypeCd");
		String certificateNum = request.getParameter("certificateNum");
		ShiroUser curUser = (ShiroUser) SecurityUtils.getSubject()
				.getPrincipal();
		Individual individual = new Individual();
		individual.setCustomerName(customerName);
		individual.setCertificateTypeCd(certificateTypeCd);
		individual.setCertificateNum(certificateNum);
		Page<Object[]> page = collateralService.findCustomersBySearch(
				firstIndex / pageSize, pageSize, curUser.getId(), individual);
		List<Object[]> list = page.getContent();
		return new DataTablesPage(sEcho, list, page.getTotalElements());
	}

	@RequestMapping("/add")
	public String add(@RequestParam(required = false) String source, Model model) {
		ShiroUser curUser = (ShiroUser) SecurityUtils.getSubject()
				.getPrincipal();
		//modify by mz 20150702 start
		String guarantyNum = commonBizNumberBS.generateGuarantyNumber("DZY",
				curUser.getLogOrgid().toString());
		//modify by mz 20150702 end
		Collateral collateral = new Collateral();
		collateral.setGuarantyNum(guarantyNum);
		collateral.setSysUpdateDateStr(new SimpleDateFormat("yyyy-MM-dd")
				.format(new Date()));
		model.addAttribute("type", "add");
		model.addAttribute("source", source);
		model.addAttribute("collateral", collateral);
		model.addAttribute("uploadPath", GlobalConstants.UPLOAD_DESTINATION);
		model.addAttribute("allDocType", DocStageCode.DZY_DOC.getCodeId());
		return Collateral + "/input";
	}

	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, Model model) {
		fillModel(model, id);
		model.addAttribute("type", "edit");
		model.addAttribute("uploadPath", GlobalConstants.UPLOAD_DESTINATION);
		model.addAttribute("allDocType", DocStageCode.DZY_DOC.getCodeId());
		return Collateral + "/input";
	}

	@RequestMapping("/detail/{id}")
	public String detail(@PathVariable("id") Long id, Model model) {
		fillModel(model, id);
		model.addAttribute("type", "detail");
		return Collateral + "/input";
	}

	@RequestMapping("/save")
	@ResponseBody
	public Result save(CollateralVO form) {
		// 修改
		if (form != null && form.getGuarantyId() != null) {
			Collateral collateral = collateralService.get(form.getGuarantyId());
			// 防止多台电脑同事操作此条数据，如果在修改页面保存时，此条数据以被其他业务关联，则不允许保存
			if (StringUtils.equals(collateral.getGuarantyStatusCd(), "2")) {
				return failure("抵质押物已关联，不允许修改操作！", null);
			}

		}
		ShiroUser curUser = (ShiroUser) SecurityUtils.getSubject()
				.getPrincipal();
		try {
			//modify by mz 20150702 start
			MsgResult result = collateralService.saveCollateral(form, curUser.getId(),
					curUser.getLogOrgid());
			//modify by mz 20150702 end
			return success("抵质物信息保存成功!", result.getCode());
		} catch (Exception e) {
			e.printStackTrace();
			return failure("抵质物信息保存失败！", null);
		}
	}

	@RequestMapping("/delete")
	@ResponseBody
	public Result delete(Long id) {
		try {
			MsgResult msgResult = collateralService.deleteCollateral(id);
			if (msgResult.equalsMsgResult(ResultEnums.SUCCESS)) {
				return success("抵质物信息删除成功！");
			} else {
				return failure(msgResult);

			}
		} catch (Exception e) {
			e.printStackTrace();
			return failure("数据错误！");
		}
	}

	@RequestMapping("/getCollateralData")
	@ResponseBody
	public ProjectPawnInfo getCollateralData(@RequestParam String projectId,
			@RequestParam String projectPawnInfoId) {
		ProjectPawnInfo ppi = this.collateralService
				.findProjectPawnInfoById(new Long(projectPawnInfoId));
		Collateral collateral = this.collateralService.findCollateralById(ppi
				.getGuarantyId());
		return ppi;
	}

	@RequestMapping("/updateCollateral")
	@ResponseBody
	public Result updateCollateral(
			@ModelAttribute ProjectPawnInfo projectPawnInfo,
			@RequestParam String contractAmt) {
		ProjectPawnInfo ppi = this.collateralService
				.findProjectPawnInfoById(projectPawnInfo.getProjectPawnInfoId());
		Collateral collateral = this.collateralService.findCollateralById(ppi
				.getGuarantyId());
		// 验证担保规则
		Result result = validateProjectPawnInfoRules(contractAmt, collateral,
				projectPawnInfo, dataDict.getCodeVal("CtrlIndicator", "S2"));
		if (result.getSuccess()) {
			BigDecimal lockingAmount = MathUtil.BDsubtract(
					collateral.getLockingAmount(),
					projectPawnInfo.getActualCreditAmount(), 2);
			lockingAmount = MathUtil.BDadd(lockingAmount,
					projectPawnInfo.getActualCreditAmount(), 2);
			collateral.setLockingAmount(lockingAmount);
			ppi.setSetGuaranteeAmt(projectPawnInfo.getSetGuaranteeAmt());
			ppi.setActualCreditAmount(projectPawnInfo.getActualCreditAmount());
			ppi.setActualGuaranteeRate(projectPawnInfo.getActualGuaranteeRate());
			ppi.setSysUpdateTime(DateTools.stringToDate(
					DateTools.getCurrentDate("yyyy-MM-dd"), "yyyy-MM-dd"));
			this.collateralService.save(collateral);
			this.collateralService.saveProjectPawnInfo(ppi);
			return new Result(true);
		} else {
			return result;
		}
	}

	@RequestMapping("/findHistoryBySearch")
	@ResponseBody
	public DataTablesPage findHistoryBySearch(
			@RequestParam("sEcho") Integer sEcho,
			@RequestParam("iDisplayStart") Integer firstIndex,
			@RequestParam("iDisplayLength") Integer pageSize, Long guarantyId) {
		Page<Object[]> page = collateralService.findHistoryBySearch(firstIndex
				/ pageSize + 1, pageSize, guarantyId);
		List<Object[]> list = page.getContent();
		return new DataTablesPage(sEcho, list, page.getTotalElements());
	}

	/** 校验担保规则 **/
	private Result validateProjectPawnInfoRules(String contractAmt,
			Collateral collateral, ProjectPawnInfo projectPawnInfo, String type) {
		BigDecimal applyAmt = null;
		if (StringUtils.isNotBlank(contractAmt)) {
			applyAmt = new BigDecimal(contractAmt);
		} else {
			return new Result(false, "未能读取合同金额，请检查数据完整性！", null);
		}
		BigDecimal actualCreditAmount = projectPawnInfo.getActualCreditAmount();
		if (null != actualCreditAmount && null != applyAmt) {
			if (actualCreditAmount.compareTo(applyAmt) > 0) {
				if (dataDict.getCodeVal("CtrlIndicator", "S1").equals(type)) {
					return new Result(false, "本次担保债权金额不能大于申报金额", null);
				} else if (dataDict.getCodeVal("CtrlIndicator", "S2").equals(
						type)) {
					return new Result(false, "本次担保债权金额不能大于合同金额", null);
				}
			}
		}
		return new Result(true);
	}

	protected Result failure(MsgResult msgResult) {
		return new Result(false, msgResult.getDesc(), null);
	}

	private void fillModel(Model model, Long id) {
		Collateral collateral = collateralService.get(id);
		collateral.setSysUpdateDateStr(new SimpleDateFormat("yyyy-MM-dd")
				.format(collateral.getSysUpdateDate()));
		model.addAttribute("collateral", collateral);
		Integer guarantyTypeCd = CommonHelper.toInt(collateral
				.getGuarantyTypeCd());
		if (guarantyTypeCd == null) {
			return;
		}
		switch (guarantyTypeCd) {
		case 0:// 房产
			RealEstateMortgage realEstateMortgage = collateralService
					.getRealEstateMortgage(id);
			realEstateMortgage.setConstructedDateStr(CommonHelper.date2Str(
					realEstateMortgage.getConstructedDate(),
					CommonHelper.DF_DATE));
			model.addAttribute("realEstateMortgage", realEstateMortgage);
			break;
		case 1:// 地产
			LandProduce landProduce = collateralService.getLandProduce(id);
			landProduce.setLandEndDateStr(CommonHelper.date2Str(
					landProduce.getLandEndDate(), CommonHelper.DF_DATE));
			model.addAttribute("landProduce", landProduce);
			break;
		case 2:// 机器设备
			MachineEquipmentMortgage machineEquipmentMortgage = collateralService
					.getMachineEquipmentMortgage(id);
			model.addAttribute("machineEquipmentMortgage",
					machineEquipmentMortgage);
			break;
		case 3:// 机动车
			TrafficCar trafficCar = collateralService.getTrafficCar(id);
			trafficCar.setRegisterDateStr(CommonHelper.date2Str(
					trafficCar.getRegisterDate(), CommonHelper.DF_DATE));
			model.addAttribute("trafficCar", trafficCar);
			break;
		default:
			break;
		}
	}
	
	
	
	
	/**
	 * 文档（DocumentIndex）的查询方法
	 * 
	 * @param request
	 *            HttpServletRequest的对象
	 * @param sEcho
	 *            datatables的被请求次数
	 * @param firstIndex
	 *            起始页数
	 * @param pageSize
	 *            每页多少条记录
	 * @return page DataTablesPage对象的实例
	 * 
	 * @author lijing
	 * @lastModified lijing 2014-08-20 11:30:50
	 */
	@RequestMapping("/findDocuments")
	@ResponseBody
	public DataTablesPage findDocumentsBySearch(
			@RequestParam("sEcho") Integer sEcho,
			@RequestParam("iDisplayStart") Integer firstIndex,
			@RequestParam("iDisplayLength") Integer pageSize,
			HttpServletRequest request) {
		// 查询条件
		String guarantyNum = (String) request.getParameter("guarantyNum");
		String documentName = request.getParameter("query_documentName");
		String contentType = request.getParameter("query_contentType");
		List<Object> params = new ArrayList<Object>();
		// String customerName =
		// uniqueCustomerService.findPartyByPartyId(partyId).getPartyName();
		ShiroUser curUser = (ShiroUser) SecurityUtils.getSubject()
				.getPrincipal();
		params.add(guarantyNum);
		params.add("46");
		params.add(documentName);
		if (StringUtils.isNotBlank(contentType)) {
			params.add(contentType);
		} else {
			params.add("");
		}

		List<Object[]> finalList = new ArrayList<Object[]>();
		Page queryList = documentService.findDocumentsInCollateral(
				(firstIndex / pageSize) + 1, pageSize, params);
		List<Object[]> resultList = queryList.getContent();
		DataTablesPage page = new DataTablesPage(sEcho, queryList);
		return page;
	}
	
	
	/**TODO
	 * 新增文档前的参数获取
	 * 
	 * @param guId
	 *            抵质押Id
	 * @param request
	 *            HttpServletRequest
	 * @return DocumentUploadVO 对象
	 * @author lijing
	 * @lastModified lijing 2014-08-18 9:30:50
	 */
	@RequestMapping("/beforeUpdate")
	@ResponseBody
	public DocumentUploadVO beforeUpdate(@RequestParam String guId,
			HttpServletRequest request) {
		DocumentUploadVO documentUploadVO = new DocumentUploadVO();
		Collateral collateral = collateralService.findCollateralById(Long.valueOf(guId));
		documentUploadVO.setPartyId(collateral.getGuarantyId().toString());
		ShiroUser curUser = (ShiroUser) SecurityUtils.getSubject()
				.getPrincipal();
		documentUploadVO.setUserNum(curUser.getId().toString());
		//modify by HWL start 20150701 修改为登录机构
		documentUploadVO.setCreateOrgCd(curUser.getLogOrgid().toString());
		//modify by HWL end 20150701 修改为登录机构
		documentUploadVO.setCreateDateTime(CommonHelper.getNowStr(CommonHelper.DF_DATE));
		documentUploadVO.setCustomerNum(collateral.getGuarantyNum());
		documentUploadVO.setBizNum("");
		documentUploadVO.setBizId("-1");
		documentUploadVO.setDocumentType("46");
		documentUploadVO.setCreateUserName(curUser.getName());
		documentUploadVO.setFileTypes("doc,docx,xls,xlsx,pdf,jpg,gif,png,rar");
		documentUploadVO.setCreateUserNum(curUser.getId().toString());
		//documentUploadVO.setCreateUserNum(curUser.getId().toString());
		documentUploadVO.setCreateTypeCd("01");
		documentUploadVO.setDocumentNum(commonBizNumberBS.generateDocumentNum(
				collateral.getGuarantyNum(), "01"));
		return documentUploadVO;
	}
	
	/**TODO
	 * 获取已经上传文档的种类
	 * 
	 * @param unGuId
	 * @return List<String>
	 * */
	@RequestMapping("/findDocumentCustDocTypes")
	@ResponseBody
	public List<Object[]> findDocumentCustDocTypes(@RequestParam String guId) {
		Collateral collateral = collateralService.findCollateralById(Long.valueOf(guId));
		String custDocTypeCd = DocStageCode.DZY_DOC.getCodeId();
		return documentService.findDocumentCustDocTypes(collateral.getGuarantyNum(),
				custDocTypeCd);
	}
	
	/**TODO
	 * 获取上传文档的种类
	 * 
	 * @param partyId
	 * @return Map<String,String>
	 * */
	@RequestMapping("/findUploadCustDocTypes")
	@ResponseBody
	public CustDocInfo findUploadCustDocTypes(@RequestParam String guId) {
		Collateral collateral = collateralService.findCollateralById(Long.valueOf(guId));
		String custDocTypeCd = "";
		custDocTypeCd = DocStageCode.DZY_DOC.getCodeId();
		return documentService.findUploadCustDocTypes(null, collateral.getGuarantyId(),
				custDocTypeCd);
	}
}
