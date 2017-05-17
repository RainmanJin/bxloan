package com.coamctech.bxloan.web.controller.corpcustomer;

import static com.coamctech.bxloan.commons.GlobalConstants.CORP_CUSMNG;
import static com.coamctech.bxloan.commons.GlobalConstants.INID_MNG;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.coamctech.bxloan.commons.GlobalConstants;
import com.coamctech.bxloan.commons.GlobalConstants.DocStageCode;
import com.coamctech.bxloan.commons.controller.BaseController;
import com.coamctech.bxloan.commons.result.DataTablesPage;
import com.coamctech.bxloan.commons.result.Result;
import com.coamctech.bxloan.datadict.DataDict;
import com.coamctech.bxloan.document.CustDocInfo;
import com.coamctech.bxloan.entity.CorpCustomerRelaCorp;
import com.coamctech.bxloan.entity.CorpCustomerRelaPerson;
import com.coamctech.bxloan.entity.CorporationCustomer;
import com.coamctech.bxloan.entity.CustomerManagerTeam;
import com.coamctech.bxloan.entity.EcOrgDepartment;
import com.coamctech.bxloan.entity.EcOrgPersonconnrole;
import com.coamctech.bxloan.entity.SalaBusiCustFinalcial;
import com.coamctech.bxloan.service.bizapply.BusinessApplicationService;
import com.coamctech.bxloan.service.corpcustomer.CorporationCustomerService;
import com.coamctech.bxloan.service.customermng.UniqueCustomerService;
import com.coamctech.bxloan.service.model.CheckResult;
import com.coamctech.bxloan.service.model.corpcustomer.CorpAddCorpVO;
import com.coamctech.bxloan.service.model.corpcustomer.CorpAddPersonVO;
import com.coamctech.bxloan.service.model.corpcustomer.CorpCusSaveVO;
import com.coamctech.bxloan.service.model.corpcustomer.CorpRelaCorpDetailVO;
import com.coamctech.bxloan.service.model.corpcustomer.CorpRelaPersonDetailVO;
import com.coamctech.bxloan.service.model.corpcustomer.PartyListInfo;
import com.coamctech.bxloan.service.sysmng.DocumentService;
import com.coamctech.bxloan.service.sysmng.ICommonBizNumberBS;
import com.coamctech.bxloan.web.security.ShiroUser;
import com.coamctech.bxloan.web.vo.corpcustomer.ActControllerListVO;
import com.coamctech.bxloan.web.vo.corpcustomer.EntcusDisplayVO;
import com.coamctech.bxloan.web.vo.corpcustomer.HighManagerListVO;
import com.coamctech.bxloan.web.vo.corpcustomer.StockHolderListVO;
import com.google.common.base.Function;
import com.google.common.collect.Lists;

/**
 * 企业客户
 * 
 * @author xc
 */
@Controller
@RequestMapping("/" + CORP_CUSMNG)
public class CorporationCustomerController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(CorporationCustomerController.class);
	/*** JSP 目录 */
	private final String jspBaseDir = CORP_CUSMNG;
	/*** JSP 目录 */
	private final String mainDir = INID_MNG;
	/*** 证件类型 */
	private final String certificateTypeCodeType = "PCertificateType";
	/*** 出资类型 */
	private final String invesTypeCodeType = "InvestmentTypeCdarray";
	/*** 职务 */
	private final String positionCodeType = "PositionCode";
	@Autowired
	private DataDict dataDict;
	@Autowired
	private UniqueCustomerService uniCustomerService;
	@Autowired
	private BusinessApplicationService businessApplicationService;
	@Autowired
	private DocumentService documentService;
	@Autowired
	private ICommonBizNumberBS commBizNumber;
	@Autowired
	private CorporationCustomerService corpCusService;

	@RequestMapping
	public String index() {
		return jspBaseDir + "/main";
	}

	/**
	 * 新增企业客户
	 * 
	 * @param certificateNum
	 * @param customerName
	 * @param businessLicenseNum
	 * @return
	 * @author xc
	 */
	@ResponseBody
	@RequestMapping(value = "/addEntCustomer", method = RequestMethod.POST)
	public Result add(
	/** 组织机构代码 **/
	@RequestParam("certificateNum") String certificateNum,
	/** 客户名称 **/
	@RequestParam("customerName") String customerName,
	/** 营业执照 **/
	@RequestParam("businessLicenseNum") String businessLicenseNum) {
		String entCustomerTypeCd = this.dataDict.getUniqueOne("CustomerType", "S1").getCodeValue();
		boolean alreadyHave = this.corpCusService.findCorpCountByCertificateNum(certificateNum) > 0;
		if (alreadyHave) {
			return super.failure("已经有此用户", null);
		}
		boolean inBlackList = this.corpCusService.findBlackListCount(entCustomerTypeCd, certificateNum) > 0;
		if (inBlackList) {
			return super.failure("此用户在黑名单中", null);
		}
		try {
			CheckResult checkResult = this.corpCusService.checkCertificateNum(certificateNum);
			if (checkResult.isErrorResult()) {
				return super.failure(checkResult.getErrorMsg(), null);
			}
		} catch (Exception e) {
			logger.error("组织机构代码不合法", e);
			return super.failure("组织机构代码不合法", null);
		}
		// 客户编号
		String customerNum = this.commBizNumber.generateCustomerNumber(entCustomerTypeCd, null, certificateNum);
		String managerUserNum = curUserId();
		String managerOrgId = curUserOrgid();
		String managerDepId = this.curUserDepId(managerOrgId);
		try {// 保存企业客户
			Long partyId = this.corpCusService.createCorpCustomer(certificateNum, customerName, businessLicenseNum, customerNum, managerUserNum, managerOrgId, managerDepId);
			return super.success("保存成功", partyId);
		} catch (Exception e) {
			logger.error("初始化数据出错", e);
			return failure("初始化数据出错", null);
		}
	}

	/**
	 * 跳转到编辑企业客户
	 * 
	 * @param partyId
	 * @param consultLocation
	 * @return
	 * @author xc
	 */
	@RequestMapping("/toEdit/{partyId}")
	public ModelAndView toEdit(@PathVariable("partyId") String partyId, @RequestParam(required = false) String consultLocation) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("partyId", partyId);
		CorporationCustomer corpCus = this.corpCusService.findById(Long.parseLong(partyId));
		model.put("corpCus", corpCus);
		model.put("individual", corpCus);
		model.put("uploadDestination", GlobalConstants.UPLOAD_DESTINATION);
		if (!this.checkBizStatus(partyId)) {
			model.put("errorDateMessage", "该客户有正在发起中的业务，不能对其修改！");
			return new ModelAndView(this.mainDir, model);
		}
		// 客户翻译
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		String userName = user.getName();
		if (!StringUtils.equals(user.getUsernum(), corpCus.getLastUpdateUserNum())) {
			// 从数据库得到上一个更新着的名称
			userName = corpCusService.getUserName(corpCus.getLastUpdateUserNum());
		}
		if (userName == null) {
			userName = user.getName();
		}
		model.put("userName", userName);
		model.put("consultLocation", consultLocation);
		return new ModelAndView(this.jspBaseDir + "/detail", model);
	}

	/**
	 * 查看企业客户
	 * 
	 * @param partyId
	 * @param consultLocation
	 * @return
	 * @author xc
	 */
	@RequestMapping("/showDetail/{partyId}")
	public ModelAndView showDetail(@PathVariable("partyId") String partyId, @RequestParam(required = false) String consultLocation) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("partyId", partyId);
		CorporationCustomer corpCus = this.corpCusService.findById(Long.parseLong(partyId));
		model.put("corpCus", corpCus);
		model.put("showDetail", "showDetail");
		model.put("uploadDestination", GlobalConstants.UPLOAD_DESTINATION);
		// 客户翻译
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		String userName = user.getName();
		if (!StringUtils.equals(user.getUsernum(), corpCus.getLastUpdateUserNum())) {
			// 从数据库得到上一个更新着的名称
			userName = corpCusService.getUserName(corpCus.getLastUpdateUserNum());
		}
		if (userName == null) {
			userName = user.getName();
		}
		model.put("userName", userName);
		model.put("consultLocation", consultLocation);
		return new ModelAndView(this.jspBaseDir + "/detail", model);
	}

	/***
	 * 保存企业客户
	 * 
	 * @param cus
	 * @return
	 * @author xc
	 */
	@ResponseBody
	@RequestMapping(value = "/saveCorpCus", method = RequestMethod.POST)
	public Result saveCorpCus(@ModelAttribute CorpCusSaveVO cus) {
		try {
			cus.setLastUpdateUserNum(curUserNum());
			this.corpCusService.update(cus);
			return success();
		} catch (Exception e) {
			logger.error("保存企业客户时发生异常", e);
			return failure("保存企业客户时发生异常", e);
		}
	}

	/**
	 * 企业客户生效
	 * 
	 * @param cus
	 * @return
	 * @author xc
	 */
	@ResponseBody
	@RequestMapping(value = "/enableCorpCus", method = RequestMethod.POST)
	public Result enableCorpCus(@ModelAttribute CorpCusSaveVO cus) {
		String errMsg = this.validateCorpCusEnable(cus);
		if (StringUtils.isBlank(errMsg)) {
			try {
				cus.setLastUpdateUserNum(curUserNum());
				this.corpCusService.enable(cus);
			} catch (Exception e) {
				logger.error("保存客户时发生异常", e);
				return failure("保存客户时发生异常", null);
			}
		} else {
			return failure(errMsg, null);
		}
		return success();
	}

	/**
	 * 验证企业客户保存
	 * 
	 * @param cus
	 * @return
	 * @author xc
	 */
	private String validateCorpCusEnable(CorpCusSaveVO cus) {
		return this.corpCusService.validateEnableCorpCus(cus);
	}

	/**
	 * 保存相关法人
	 * 
	 * @param corp
	 * @return
	 * @author xc
	 */
	@ResponseBody
	@RequestMapping("/saveRelaCorp")
	public Result saveRelaCorp(CorpAddCorpVO corp) {
		String errMsg = this.corpCusService.valiRelaCorp(corp);
		if (StringUtils.isNotBlank(errMsg)) {
			return failure(errMsg, null);
		}
		try {
			CorpCustomerRelaCorp result = this.corpCusService.createRelaCorp(corp);
			return success("保存成功", result.getId());
		} catch (Exception e) {
			logger.error("保存相关法人错误", e);
			return failure("保存相关法人错误", null);
		}
	}

	/***
	 * 保存相关自然人
	 * 
	 * @param vo
	 * @return
	 * @author xc
	 */
	@ResponseBody
	@RequestMapping("/savePerson")
	public Result savePerson(CorpAddPersonVO vo) {
		String errMsg = this.corpCusService.valiPersonSaveVo(vo);
		if (StringUtils.isNotBlank(errMsg)) {
			return failure(errMsg, null);
		}
		try {
			CorpCustomerRelaPerson result = this.corpCusService.savePerson(vo);
			return success("保存成功", result.getId());
		} catch (Exception e) {
			logger.error("保存人员出错", e);
			return failure("保存人员出错", null);
		}
	}

	/**
	 * 实际控制人列表
	 * 
	 * @param partyId
	 * @param sEcho
	 * @param firstIndex
	 * @param pageSize
	 * @return
	 * @author xc
	 */
	@ResponseBody
	@RequestMapping("/actconList")
	public DataTablesPage actConList(@RequestParam("partyId") String partyId, @RequestParam("sEcho") Integer sEcho, @RequestParam("iDisplayStart") Integer firstIndex, @RequestParam("iDisplayLength") Integer pageSize) {
		Page<Object[]> wfPage = this.corpCusService.listActController(Long.parseLong(partyId), firstIndex
				/ pageSize, pageSize);
		DataTablesPage result = new DataTablesPage(sEcho, wfPage);
		result.setAaData(Lists.transform(wfPage.getContent(), new Function<Object[], ActControllerListVO>() {
			@Override
			public ActControllerListVO apply(Object[] input) {
				ActControllerListVO result = new ActControllerListVO(input);
				String typeName = dataDict.getCodeName(certificateTypeCodeType, result.getCertificateTypeCd());
				result.setCertificateTypeCd(typeName);
				return result;
			}
		}));
		return result;
	}

	/**
	 * 股东列表
	 * 
	 * @return
	 * @author xc
	 */
	@ResponseBody
	@RequestMapping("/stockHolderList")
	public DataTablesPage stockHolderList(@RequestParam("partyId") String partyId, @RequestParam("sEcho") Integer sEcho, @RequestParam("iDisplayStart") Integer firstIndex, @RequestParam("iDisplayLength") Integer pageSize) {
		Page<Object[]> wfPage = this.corpCusService.listStockHolder(Long.parseLong(partyId), firstIndex
				/ pageSize, pageSize);
		DataTablesPage result = new DataTablesPage(sEcho, wfPage);
		result.setAaData(Lists.transform(wfPage.getContent(), new Function<Object[], StockHolderListVO>() {
			@Override
			public StockHolderListVO apply(Object[] input) {
				StockHolderListVO vo = new StockHolderListVO(input);
				vo.setCertificateTypeCd(dataDict.getCodeName(certificateTypeCodeType, vo.getCertificateTypeCd()));
				vo.setInvestmentType(findInvestTypeNames(vo.getInvestmentType()));
				return vo;
			}
		}));
		return result;
	}

	/**
	 * 高管列表
	 * 
	 * @return
	 * @author xc
	 */
	@ResponseBody
	@RequestMapping("/highManagerList")
	public DataTablesPage highManagerList(@RequestParam("partyId") String partyId, @RequestParam("sEcho") Integer sEcho, @RequestParam("iDisplayStart") Integer firstIndex, @RequestParam("iDisplayLength") Integer pageSize) {
		Page<Object[]> wfPage = this.corpCusService.listHighManager(Long.parseLong(partyId), firstIndex
				/ pageSize, pageSize);
		DataTablesPage result = new DataTablesPage(sEcho, wfPage);
		result.setAaData(Lists.transform(wfPage.getContent(), new Function<Object[], HighManagerListVO>() {
			@Override
			public HighManagerListVO apply(Object[] input) {
				HighManagerListVO vo = new HighManagerListVO(input);
				vo.setCertificateTypeCd(dataDict.getCodeName(certificateTypeCodeType, vo.getCertificateTypeCd()));
				vo.setPositionCd(dataDict.getCodeName(positionCodeType, vo.getPositionCd()));
				vo.setPersonIsLegalRepresent("1".equals(vo.getPersonIsLegalRepresent()) ? "是" : "否");
				return vo;
			}
		}));
		return result;
	}

	/**
	 * 删除相关人员/法人
	 * 
	 * @param relaId
	 * @param relaType
	 * @return
	 * @author xc
	 */
	@ResponseBody
	@RequestMapping("/removeRela")
	public Result removeRela(String partyId, String relaId, String relaType) {
		Boolean hasPrivilege = this.checkRemovePri(Long.parseLong(partyId));
		if (!hasPrivilege) {
			return failure("您没有权限删除记录", null);
		}
		try {
			Long id = Long.parseLong(relaId);
			if (relaType.equals("c")) {// 法人
				this.corpCusService.removeRelaCorp(id);
			} else {
				this.corpCusService.removeRelaPerson(id);
			}
		} catch (Exception e) {
			logger.error("删除失败!", e);
			return failure("删除失败!", null);
		}
		return success();
	}

	/**
	 * 查看自然人详细
	 * 
	 * @param personId
	 * @return
	 * @author xc
	 */
	@ResponseBody
	@RequestMapping("/detailPerson")
	public Result detailPerson(@RequestParam("personId") String personId) {
		try {
			CorpCustomerRelaPerson person = this.corpCusService.findRelaPersonById(Long.parseLong(personId));
			CorpRelaPersonDetailVO detail = new CorpRelaPersonDetailVO();
			BeanUtils.copyProperties(person, detail);
			return success(detail);
		} catch (Exception e) {
			logger.error("查看详细出现问题,请稍后重试", e);
			return failure("查看详细出现问题,请稍后重试");
		}
	}

	/***
	 * 查看法人详细
	 * 
	 * @param corpId
	 * @return
	 * @author xc
	 */
	@ResponseBody
	@RequestMapping("/detailCorp")
	public Result detailCorp(@RequestParam("corpId") String corpId) {
		try {
			CorpCustomerRelaCorp corp = this.corpCusService.findRelaCorpById(Long.parseLong(corpId));
			CorpRelaCorpDetailVO detail = new CorpRelaCorpDetailVO();
			BeanUtils.copyProperties(corp, detail);
			return success(detail);
		} catch (Exception e) {
			logger.error("查看详细出现问题,请稍后重试", e);
			return failure("查看详细出现问题,请稍后重试");
		}
	}

	/**
	 * 检查是否有删除权限
	 * 
	 * @return
	 * @author xc
	 */
	private Boolean checkRemovePri(Long partyId) {
		String typeKey = "01";// 管护权
		String userNum = this.curUserId();
		CustomerManagerTeam cusManager = this.corpCusService.findCusManager(partyId, userNum);
		if (cusManager == null || cusManager.getManagerType() == null
				|| !cusManager.getManagerType().equals(typeKey)) {
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}

	/**
	 * 检查企业客户是否为借款人,是否有正在进行业务
	 * 
	 * @param partyId
	 * @return
	 * @author xc
	 */
	@ResponseBody
	@RequestMapping("checkCorpCusBizStatus")
	public Result checkCorpCusBizStatus(@RequestParam("partyId") String partyId) {
		Long partyIdLong = Long.parseLong(partyId);
		CorporationCustomer corpCus = this.corpCusService.findById(partyIdLong);
		if (corpCus.getMarkType() == null) {
			return failure("此客户信息不完全");
		}
		if (!corpCus.getMarkType().contains("01")) {// 不是贷款人
			return failure("此客户不是贷款人");
		}
		Long bizCount = this.corpCusService.findProjectApplicationCount(partyIdLong);
		if (bizCount > 0L) {
			return failure("此客户有正在进行中的业务");
		}
		return success(uniCustomerService.findPartyByPartyId(partyId));
	}

	/** kangyf create 2014-10-21 */
	/**
	 * 企业客户财务列表（SalaBusiCustFinalcial）的查询方法
	 * 
	 * @param request HttpServletRequest的对象
	 * @param sEcho datatables的被请求次数
	 * @param firstIndex 起始页数
	 * @param pageSize 每页多少条记录
	 * @return page DataTablesPage对象的实例
	 * 
	 * @author lijing
	 * @lastModified lijing 2014-07-25 9:30:50
	 */
	@ResponseBody
	@SuppressWarnings("rawtypes")
	@RequestMapping("/findProjectFinalcials")
	public DataTablesPage findProjectFinalcials(@RequestParam("sEcho") Integer sEcho, @RequestParam("iDisplayStart") Integer firstIndex, @RequestParam("iDisplayLength") Integer pageSize, HttpServletRequest request) {
		String partyId = (String) request.getParameter("partyId");
		List<Object> params = new ArrayList<Object>();
		params.add(partyId);
		Page queryList = uniCustomerService.findProjectFinalcials(firstIndex
				/ pageSize + 1, pageSize, params);
		DataTablesPage page = new DataTablesPage(sEcho, queryList);
		return page;
	}

	/**
	 * 根据salaCustFinacial的id查对象
	 * 
	 * @param id 主键
	 * @return SalaBusiCustFinalcial 对象
	 * */
	@RequestMapping("/findOneSalaCustFinace")
	@ResponseBody
	public SalaBusiCustFinalcial findOneSalaCustFinace(@RequestParam("id") String id) {
		return uniCustomerService.getOneSalaCustFinace(id);
	}

	/**
	 * 获取已存在客户列表
	 * 
	 * @param sEcho
	 * @param firstIndex
	 * @param pageSize
	 * @param partyTypeCd
	 * @param partyName
	 * @param licence
	 * @param certificatetypeCd
	 * @return
	 * @author xc
	 */
	@ResponseBody
	@RequestMapping("/findExistingParty")
	public DataTablesPage findExistingParty(@RequestParam("sEcho") Integer sEcho, @RequestParam("iDisplayStart") Integer firstIndex, @RequestParam("iDisplayLength") Integer pageSize, @RequestParam("partyTypeCd") String partyTypeCd, @RequestParam("partyName") String partyName, @RequestParam("licence") String licence, @RequestParam("certificatetypeCd") String certificatetypeCd) {
		Page<Object[]> page = this.corpCusService.existingPartyList(firstIndex
				/ pageSize, pageSize, partyTypeCd, partyName, licence, certificatetypeCd);
		DataTablesPage result = new DataTablesPage(sEcho, page);
		List<PartyListInfo> voList = Lists.transform(page.getContent(), new Function<Object[], PartyListInfo>() {
			@Override
			public PartyListInfo apply(Object[] input) {
				return new PartyListInfo(input);
			}
		});
		result.setAaData(voList);
		return result;
	}

	/**
	 * 查看已存在个人详细
	 * 
	 * @param partyId
	 * @return
	 * @author xc
	 */
	@ResponseBody
	@RequestMapping("/existingCorpDetail")
	public Result existingCorpDetail(@RequestParam("partyId") String partyId) {
		try {
			CorpRelaCorpDetailVO detail = this.corpCusService.findExistingCorpDetail(Long.parseLong(partyId));
			return success(detail);
		} catch (Exception e) {
			logger.error("获取企业客户信息出现问题", e);
			return failure("获取企业客户信息出现问题");
		}
	}

	/***
	 * 查看已存在企业详细
	 * 
	 * @param partyId
	 * @return
	 * @author xc
	 */
	@ResponseBody
	@RequestMapping("/existingPersonDetail")
	public Result existingPersonDetail(@RequestParam("partyId") String partyId) {
		try {
			CorpRelaPersonDetailVO detail = this.corpCusService.findExistingPersonDetail(Long.parseLong(partyId));
			return success(detail);
		} catch (Exception e) {
			logger.error("获取个人客户信息出现问题", e);
			return failure("获取个人客户信息出现问题");
		}
	}

	/**
	 * 获取上传文档的种类
	 * 
	 * @param partyId
	 * @return Map<String,String>
	 * */
	@RequestMapping("/findUploadCustDocTypes")
	@ResponseBody
	public CustDocInfo findUploadCustDocTypes(@RequestParam Long partyId) {
		return documentService.findUploadCustDocTypes(null, partyId, DocStageCode.QY_DOC.getCodeId());
	}

	/**
	 * 检查客户是否有发起中的业务
	 * 
	 * @param String partyId
	 * @return boolean true：没有 ，false：有
	 * */
	public boolean checkBizStatus(String partyId) {
		boolean check = businessApplicationService.checkStatus(Long.valueOf(partyId));
		return check;
	}

	/**
	 * 获取已经上传文档的种类
	 * 
	 * @param partyId
	 * @return List<String>
	 * */
	@RequestMapping("/findDocumentCustDocTypes")
	@ResponseBody
	public List<Object[]> findDocumentCustDocTypes(@RequestParam Long partyId) {
		return documentService.findDocumentCustDocTypes(null, partyId, DocStageCode.QY_DOC.getCodeId());
	}

	// //////////////
	// //PRIVATE/////
	// /////////////
	private String findInvestTypeNames(String typeCd) {
		if (StringUtils.isBlank(typeCd)) {
			return "";
		}
		StringBuilder result = new StringBuilder();
		for (String cd : typeCd.split(",")) {
			result.append(dataDict.getCodeName(invesTypeCodeType, cd)).append(",");
		}
		if (result.length() > 0) {
			result.deleteCharAt(result.length() - 1);
			return result.toString();
		} else {
			return "";
		}
	}

	private String curUserLogonName() {
		ShiroUser shiroUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		String curUser = "";
		if (shiroUser != null) {
			curUser = shiroUser.getLogname();
		}
		return curUser;
	}

	private String curUserDepId(String orgId) {
		ShiroUser shiroUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		for (EcOrgPersonconnrole role : shiroUser.getRoles()) {
			if (StringUtils.equals(String.valueOf(role.getOrgid()), orgId)) {
				EcOrgDepartment dep = role.getEcOrgDepartment();
				if (dep != null) {
					return dep.getId() == null ? "" : dep.getId().toString().trim();
				}
			}
		}
		return "";
	}

	private String curUserId() {
		ShiroUser shiroUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		String curUser = "";
		if (shiroUser != null) {
			curUser = shiroUser.getId().toString();
		}
		return curUser;
	}

	private String curUserNum() {
		ShiroUser shiroUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		String curUser = "";
		if (shiroUser != null) {
			curUser = shiroUser.getUsernum().toString();
		}
		return curUser;
	}

	private String curUserOrgid() {
		ShiroUser shiroUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		String curUser = "";
		if (shiroUser != null) {
			//curUser = shiroUser.getOrgid().toString();
			curUser = shiroUser.getLogOrgid().toString();
		}
		return curUser;
	}
}
