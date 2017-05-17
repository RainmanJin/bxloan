package com.coamctech.bxloan.web.controller.customermng;

import static com.coamctech.bxloan.commons.GlobalConstants.FORM_MNG;
import static com.coamctech.bxloan.commons.GlobalConstants.INDIVIDUAL;
import static com.coamctech.bxloan.commons.GlobalConstants.INID_MNG;
import static com.coamctech.bxloan.commons.GlobalConstants.MANA_MNG;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coamctech.bxloan.commons.GlobalConstants;
import com.coamctech.bxloan.commons.GlobalConstants.DocStageCode;
import com.coamctech.bxloan.commons.controller.BaseController;
import com.coamctech.bxloan.commons.result.DataTablesPage;
import com.coamctech.bxloan.commons.result.Result;
import com.coamctech.bxloan.commons.utils.DateTools;
import com.coamctech.bxloan.datadict.DataDict;
import com.coamctech.bxloan.document.CustDocInfo;
import com.coamctech.bxloan.entity.CustomerAccountManagent;
import com.coamctech.bxloan.entity.CustomerBrieflyFinace;
import com.coamctech.bxloan.entity.CustomerBusinessInfo;
import com.coamctech.bxloan.entity.CustomerCorrelativeRelations;
import com.coamctech.bxloan.entity.CustomerManagerTeam;
import com.coamctech.bxloan.entity.DocumentIndex;
import com.coamctech.bxloan.entity.FamilyFriend;
import com.coamctech.bxloan.entity.Individual;
import com.coamctech.bxloan.entity.IndustryType;
import com.coamctech.bxloan.entity.Party;
import com.coamctech.bxloan.entity.SalaBusiCustFinalcial;
import com.coamctech.bxloan.entity.VerificatPersonInfo;
import com.coamctech.bxloan.service.bizapply.BusinessApplicationService;
import com.coamctech.bxloan.service.customermng.UniqueCustomerService;
import com.coamctech.bxloan.service.enums.DataAuthorityType;
import com.coamctech.bxloan.service.sysmng.DocumentService;
import com.coamctech.bxloan.service.sysmng.ICommonBizNumberBS;
import com.coamctech.bxloan.service.usermng.UserMngService;
import com.coamctech.bxloan.web.security.ShiroUser;
import com.coamctech.bxloan.web.vo.bizapply.IndividualAgroVO;
import com.coamctech.bxloan.web.vo.customermng.DocumentUploadVO;
import com.coamctech.bxloan.web.vo.customermng.FamilyFriendAgroVo;
import com.coamctech.bxloan.web.vo.customermng.IndividualVO;
import com.google.common.base.Function;
import com.google.common.collect.Lists;

/**
 * 单一客户管理控制器
 * 
 * @author lijing
 * @lastModified lijing 2014-08-05 17:30:24
 */
@Controller
@RequestMapping("/" + INDIVIDUAL)
public class UniqueCustomerController extends BaseController{
	/** 客户类型 */
	private final String CODETYPE_CUSTOMERTYPECD = "CustomerType";
	/** 行业类型 */
	private final String CODETYPE_INDUSTRYCD = "industryTypeCd";
	/** 雇佣类型 */
	private final String CODETYPE_EMPLOYMENTTYPE = "EmploymentType";
	/** 客户状态 */
	private final String CODETYPE_CUSTOMERSTATUS = "CustomerStatusCode";
	/** 个人客户类型 */
	private final String CODETYPE_CUSTOMERTYPECG = "CustomerTypeCG";
	/** 借款人、担保人，借款/担保人 */
	private final String CODETYPE_MARKTYPE = "CustomerMarkType";
	/** 文档状态 */
	private final String CODETYPE_DTCREATETYPE = "CreateType";
	@Autowired
	private UniqueCustomerService uniqueCustomerService;
	@Autowired
	private ICommonBizNumberBS commonBizNumberBS;
	@Autowired
	private UserMngService userMngService;
	@Autowired
	private BusinessApplicationService businessApplicationService;
	@Autowired
	private DataDict dataDict;
	@Autowired
	private DocumentService documentService;
	

	/*********************************** 跳转方法 *******************************************/
	/**
	 * sideBar上点击单一客户管理的跳转方法
	 * 
	 * 
	 * @return String 跳转路径
	 * 
	 * @author lijing
	 * @lastModified lijing 2014-07-25 16:37:51
	 */
	@RequestMapping
	public String init() {
		return INID_MNG;
	}

	/**
	 * 在客户列表点击新增按钮后的跳转操作
	 * 
	 * @param request
	 *            HttpServletRequest的对象
	 * @return String 跳转路径
	 * 
	 * @author lijing
	 * @lastModified lijing 2014-07-25 16:37:51
	 */
	@RequestMapping("/intoForm")
	public String addToform(HttpServletRequest request,
			@RequestParam String partyId, @RequestParam String employmentType,
			@RequestParam(required = false) String business) {
		// String addCertNum = request.getSession()
		// .getAttribute("addCertificateNum").toString();
		// String addCertType = request.getSession()
		// .getAttribute("addCertificateType").toString();
		Individual individual = uniqueCustomerService.getIndividual(partyId);
		individual.setEmploymentType(employmentType);
		request.setAttribute("individual", individual);
		ShiroUser curUser = (ShiroUser) SecurityUtils.getSubject()
				.getPrincipal();
		request.setAttribute("createUsername", curUser.getName());
		request.setAttribute("workCode", "TOADD");
		if (individual.getSysUpdateTime() != null) {
			request.setAttribute("sysUpdateDate", DateTools.dateToString(
					individual.getSysUpdateTime(), "yyyy/MM/dd HH:mm:ss"));
		}
		request.setAttribute(CODETYPE_INDUSTRYCD, individual.getIndustryCd());
		// Properties prop =
		// Utils.loadPropertiesFileFromClassPath(UPLOAD_TARGET);
		request.setAttribute("uploadPath", GlobalConstants.UPLOAD_DESTINATION);
		request.setAttribute("business", business);
		// request.getSession().removeAttribute("addCertificateNum");
		// request.getSession().removeAttribute("addCertificateType");
		if (employmentType.equals(dataDict.getCodeVal(CODETYPE_EMPLOYMENTTYPE,
				"S1"))) {
			request.setAttribute("allDocType", DocStageCode.SX_DOC.getCodeId());
			// 受薪
			return FORM_MNG;
		} else if (employmentType.equals(dataDict.getCodeVal(
				CODETYPE_EMPLOYMENTTYPE, "S3"))) {
			// 经营跳往一个页面
			request.setAttribute("allDocType", DocStageCode.JY_DOC.getCodeId());
			return MANA_MNG;
		} else if (employmentType.equals(dataDict.getCodeVal(
				CODETYPE_EMPLOYMENTTYPE, "S2"))){
			//农户跳往一个页面
			request.setAttribute("allDocType", DocStageCode.NH_DOC.getCodeId());
			return MANA_MNG;
		}else if (employmentType.equals(dataDict.getCodeVal(CODETYPE_EMPLOYMENTTYPE,
				"S4"))) {
			request.setAttribute("allDocType", DocStageCode.STUDENT_DOC.getCodeId());
			// 学生
			return FORM_MNG;
		}
		return INID_MNG;
	}

	/**
	 * 在客户列表点击修改与查看按钮的跳转操作
	 * 
	 * @param request
	 *            HttpServletRequest的对象
	 * @return String 跳转路径
	 * 
	 * @author lijing
	 * @lastModified lijing 2014-07-30 19:37:51
	 */
	// 修改与查看的跳转方法
	@RequestMapping("/backToForm")
	public String backToForm(HttpServletRequest request,
			@RequestParam String customerId, @RequestParam String workCode,
			@RequestParam String consultLocation) {
		// HttpSession session = request.getSession();
		Individual i = uniqueCustomerService.getIndividual(customerId);
		Party party = uniqueCustomerService.findPartyByPartyId(customerId);
		if (consultLocation != null && consultLocation.equals("contract")) {
			request.setAttribute("consultLocation", consultLocation);
		}
		request.setAttribute(CODETYPE_INDUSTRYCD, i.getIndustryCd());
		if (i.getSysUpdateTime() != null) {
			request.setAttribute("sysUpdateDate", DateTools.dateToString(
					i.getSysUpdateTime(), "yyyy/MM/dd HH:mm:ss"));
		}
		request.setAttribute("birthdayStr", DateTools.dateToString(i.getBirthday(), "yyyy/MM/dd"));
		request.setAttribute("customerId", customerId);
		request.setAttribute("workCode", workCode);
		ShiroUser curUser = (ShiroUser) SecurityUtils.getSubject()
				.getPrincipal();
		request.setAttribute("createUsername", curUser.getName());
		// Properties prop =
		// Utils.loadPropertiesFileFromClassPath(UPLOAD_TARGET);
		request.setAttribute("uploadPath", GlobalConstants.UPLOAD_DESTINATION);
		// session.removeAttribute("customerId");
		// session.removeAttribute("workCode");
		String employmentType = i.getEmploymentType();
		request.setAttribute("individual", i);
		if (StringUtils.isBlank(party.getCustomerSource())
				|| "1".equals(party.getCustomerSource())) {
			request.setAttribute("allDocType", DocStageCode.SX_DOC.getCodeId());
			return FORM_MNG;
		}
		if (!this.checkBizStatus(customerId)
				&& StringUtils.equals("TOMOD", workCode)) {
			request.setAttribute("errorDateMessage", "该客户有正在发起中的业务，不能对其修改！");
			return INID_MNG;
		}
		if (StringUtils.isNotBlank(employmentType)) {
			if (employmentType.trim().equals(
					dataDict.getCodeVal(CODETYPE_EMPLOYMENTTYPE, "S1"))) {
				request.setAttribute("allDocType", DocStageCode.SX_DOC.getCodeId());
				return FORM_MNG;
			} else if (employmentType.trim().equals(
					dataDict.getCodeVal(CODETYPE_EMPLOYMENTTYPE, "S3"))) {
				request.setAttribute("allDocType", DocStageCode.JY_DOC.getCodeId());
				return MANA_MNG;
			}else if (employmentType.trim().equals(
					dataDict.getCodeVal(CODETYPE_EMPLOYMENTTYPE, "S2"))){
				request.setAttribute("allDocType", DocStageCode.NH_DOC.getCodeId());
				return MANA_MNG;
			}else if (employmentType.equals(dataDict.getCodeVal(CODETYPE_EMPLOYMENTTYPE,
					"S4"))) {
				request.setAttribute("allDocType", DocStageCode.STUDENT_DOC.getCodeId());
				// 学生
				return FORM_MNG;
			}
			request.setAttribute("errorDateMessage", "数据错误！请联系管理员");
			return INID_MNG;
		} else {
			request.setAttribute("errorDateMessage", "数据错误！请联系管理员");
			return INID_MNG;
		}
	}

	/**
	 * 在查看与修改之前完成值传递的方法
	 * 
	 * @param request
	 *            HttpServletRequest的对象
	 * @param customerId
	 *            客户id
	 * @param workCode
	 *            修改或查看的操作标识
	 * @return customerId 跳转路径
	 * 
	 * @author lijing
	 * @lastModified lijing 2014-07-31 9:37:51
	 */
	@RequestMapping("/modToForm")
	@ResponseBody
	public String modToForm(
			@RequestParam String customerId,
			@RequestParam String workCode,
			@RequestParam String customerSource,
			@RequestParam(value = "consultLocation", required = false) String consultLocation,
			HttpServletRequest request) {
		if (customerSource.equals("once")) {// 编辑客户-第一次请求
			Party party = uniqueCustomerService.findPartyByPartyId(customerId);
			if ("2".equals(party.getCustomerSource())
					|| "3".equals(party.getCustomerSource())) {
				return "singleCustomer/" + "backToForm?customerId="
						+ customerId + "&workCode=" + workCode
						+ "&consultLocation=" + consultLocation;
			} else {
				return "notBangxindaiCustomer";
			}
		} else {
			return "singleCustomer/" + "backToForm?customerId=" + customerId
					+ "&workCode=" + workCode + "&consultLocation="
					+ consultLocation;
		}
	}

	/*********************************** 客户（Party与Individual）相关方法 *******************************************/
	/**
	 * 改变客户的状态为生效的方法
	 * 
	 * @param model
	 *            ModelMap的对象
	 * @param partyId
	 *            客户id
	 * @return String 生效是否成功、错误的原因
	 * 
	 * @author lijing
	 * @lastModified lijing 2014-08-07 17:37:51
	 */
	@RequestMapping("/takeEffect")
	@ResponseBody
	public Result takeEffect(@RequestParam String partyId, ModelMap model) {
		boolean hasUseAccount = false;
		List<FamilyFriend> familyFriendsList = uniqueCustomerService
				.findFamilyFriendListByPartyId(partyId);
		List<CustomerAccountManagent> accountList = uniqueCustomerService
				.findAccountManagentListByPartyId(partyId);
		List<DocumentIndex> documentList = uniqueCustomerService
				.findDocumentIndexByPartyId(partyId);
		Individual individual = uniqueCustomerService.getIndividual(partyId);
		CustomerBrieflyFinace customerBrieflyFinace = uniqueCustomerService
				.findFinaceByPartyId(Long.valueOf(partyId));
		if (individual.getGenderCd() == null) {
			return new Result(false, "请先填写并保存概况信息", null);
		}
		if (familyFriendsList.size() < 1) { // 没添加过联系人
			return new Result(false, "请添加联系人信息", null);
		}
		String employeType = individual.getEmploymentType();
		if (StringUtils.equals(dataDict.getCodeVal(CODETYPE_EMPLOYMENTTYPE, "S2"),
						employeType)
				|| StringUtils.equals(
						dataDict.getCodeVal(CODETYPE_EMPLOYMENTTYPE, "S3"),
						employeType)) {
			CustomerBusinessInfo cbf = uniqueCustomerService
					.findBussinessInfoByPartyId(Long.valueOf(partyId));
			if (cbf == null) {
				return new Result(false, "请填写并保存经营信息", null);
			}
			String relations = "";
			for (FamilyFriend familyFriend : familyFriendsList) {
				relations += familyFriend.getRelationsType() + "_";
			}
			if (!relations.contains(dataDict.getCodeVal("RelationsType", "S3"))
					|| !relations.contains(dataDict.getCodeVal("RelationsType",
							"S4"))) {
				return new Result(false, "请填写联系人信息，至少包含一名亲属和一名非亲属，如已婚请填写配偶信息",
						null);
			}
		} /*else {
			if (accountList.size() < 1) { // 没添加过用户
				return new Result(false, "请至少添加一个使用中的银行账户！", null);
			} else {
				for (CustomerAccountManagent account : accountList) {// 检查账户状态
					if (account.getAccountStatus().intValue() == 1) {
						hasUseAccount = true;
					}
				}
				if (!hasUseAccount) {
					return new Result(false, "请至少添加一个使用中的银行账户！", null);
				}
			}
		}*/
		if (customerBrieflyFinace == null) { // 没保存过简单财务信息
			return new Result(false, "请填写家庭资产或简要财务信息", null);
		}
		/*
		 * if (documentList.size() < 8) { return new Result(false, "请上传必要的文档",
		 * null); }
		 */
		{
			/*
			 * if(StringUtils.equals(dataDict.getCodeVal(CODETYPE_EMPLOYMENTTYPE,
			 * "S1"), employeType)){
			 * 
			 * }else{
			 */
			// 判断文档是否上传全
			/*
			 * boolean IsDocumentMarch = true; int count = 0;
			 * 
			 * List<String> requirements = Lists.newArrayList(); List<String>
			 * documentUpload = Lists.newArrayList();
			 * requirements.add("1");//身份证正面 requirements.add("2");//身份证反面
			 * requirements.add("6");//工作证明 requirements.add("7");//收入证明
			 * requirements.add("8");//人行征信报告 requirements.add("9");//固定住所证明
			 * requirements.add("10");//资产证明
			 * if(individual.getMarriageCd().equals("20")){
			 * requirements.add("4");//结婚证 }else{ requirements.add("5");//单身证明 }
			 * for (DocumentIndex document : documentList) {
			 * documentUpload.add(document.getCustDocType() + ""); }
			 * 
			 * for (String require : requirements) {
			 * if(!documentUpload.contains(require)){ IsDocumentMarch = false;
			 * break; } } if (!IsDocumentMarch) { return new Result(false,
			 * "文档上传不完全，请确保上传文件包含：身份证（正反面）、工作证明、收入证明、人行征信报告、固定住所证明、资产证明，且根据情况上传结婚证或单身证明"
			 * , null); }
			 */
			/* } */
			/*
			 * for (int i = 1; i < 7; i++) { for (DocumentIndex document :
			 * documentList) { if ((i +
			 * "").equals(document.getCustDocType().charAt(0) + "")) { count =
			 * 0; break; } else { if (count == (documentList.size() - 1)) {
			 * IsDocumentMarch = false; count = 0; break; } else { count++; } }
			 * } if (!IsDocumentMarch) { count = 0; break; } }
			 */
			// 判断是否添加过配偶
			boolean flag = false;
			if (individual.getMarriageCd().equals("20")) // 已婚是否添加了配偶
			{
				for (FamilyFriend familyFriend : familyFriendsList) {
					String type = familyFriend.getFamilyFriendType();
					if ("1".equals(type)) {
						flag = true;
					}
				}// for end
				if (flag) {
					uniqueCustomerService.updateIndividualStatus(partyId);
					return new Result(true);
				} else {
					return new Result(false, "请填写配偶信息", null);
				}
			}/*
			 * else if(!hasUseAccount) { return "accountNotInUseError"; }
			 */
			else {
				uniqueCustomerService.updateIndividualStatus(partyId);
				return new Result(true);
			}
		}
	}

	/**
	 * 客户列表（Party和Individual）的查询方法
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
	 * @lastModified lijing 2014-07-25 9:30:50
	 */
	@RequestMapping("/findBySearch")
	@ResponseBody
	public DataTablesPage findBySearch(@RequestParam("sEcho") Integer sEcho,
			@RequestParam("iDisplayStart") Integer firstIndex,
			@RequestParam("iDisplayLength") Integer pageSize,
			HttpServletRequest request) {
		// 查询条件
		String customerName = request.getParameter("customerName");
		String customerType = request.getParameter("customerType");
		String certificateType = request.getParameter("certificateType");
		String markType = request.getParameter("markType");
		String certificateCode = request.getParameter("certificateCode");
		
		List<Object> params = new ArrayList<Object>();
		HttpSession session = request.getSession();
		ShiroUser curUser = (ShiroUser) SecurityUtils.getSubject()
				.getPrincipal();
		params.add(customerName);
		params.add(markType);
		params.add(certificateType);
		params.add(certificateCode);
		params.add(new BigDecimal(Long.valueOf(curUser.getId())));
		params.add(customerType);
		
		
		Page queryList = uniqueCustomerService.findIndividualBySearch(
				firstIndex / pageSize + 1, pageSize, params);
		List<Object[]> resultList = queryList.getContent();
		List<Object[]> finalList = new ArrayList<Object[]>();
		for (Object[] obj : resultList) {
			obj[8] = timeStampToString((Timestamp) obj[8]);
			finalList.add(obj);
		}
		DataTablesPage page = new DataTablesPage(sEcho, finalList,
				queryList.getTotalElements());
		return page;
	}
	
	/**
	 * 客户列表（Party和Individual）的查询方法
	 * 2015.05.22 shenzuoxin add
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
	 * @author shenzuoxin
	 * @lastModified shenzuoxin 2015.05.22
	 */
	@RequestMapping("/findCustomerQuery")
	@ResponseBody
	public DataTablesPage findCustomerQuery(@RequestParam("sEcho") Integer sEcho,
			@RequestParam("iDisplayStart") Integer firstIndex,
			@RequestParam("iDisplayLength") Integer pageSize,
			HttpServletRequest request) {
		// 查询条件
		String customerName = request.getParameter("customerName");
		String customerType = request.getParameter("customerType");
		String certificateType = request.getParameter("certificateType");
		String markType = request.getParameter("markType");
		String certificateCode = request.getParameter("certificateCode");
		
		//用户客户查询的查询条件
		// 2015.05.22 shenzuoxin add
		String orgId = request.getParameter("orgId");
		String customerCd = request.getParameter("customerCd");
		String custMng = request.getParameter("custMng");
		String employmentType = request.getParameter("employmentType");
		String customerStatus = request.getParameter("customerStatus");
		//end
		
		List<Object> params = new ArrayList<Object>();
		ShiroUser curUser = (ShiroUser) SecurityUtils.getSubject()
				.getPrincipal();
		params.add(customerName);
		params.add(markType);
		params.add(certificateType);
		params.add(certificateCode);
		params.add(new BigDecimal(Long.valueOf(curUser.getId())));
		params.add(customerType);
		
		// 2015.05.22 shenzuoxin add
		params.add(orgId);//6	所属机构
		params.add(customerCd);//7	客户编号
		params.add(custMng);//8	客户经理
		params.add(employmentType);//9	雇佣类型
		params.add(customerStatus);//10	客户状态
		params.add(curUser.getDataAuthOrgIds());//11	当前登录人拥有所有机构id集合
		params.add(curUser.getDataAuthType());//12	判断是客户经理或者统计查询的角色
		//end
		Page queryList = uniqueCustomerService.findIndividualQuery(
				firstIndex / pageSize + 1, pageSize, params);
		List<Object[]> resultList = queryList.getContent();
		List<Object[]> finalList = new ArrayList<Object[]>();
		for (Object[] obj : resultList) {
			obj[8] = timeStampToString((Timestamp) obj[8]);
			finalList.add(obj);
		}
		DataTablesPage page = new DataTablesPage(sEcho, finalList,
				queryList.getTotalElements());
		return page;
	}

	/**
	 * 根据客户Id修改客户
	 * 
	 * @param individualVO
	 *            前台传来的表单对象，注：接收的不是实体而是VO
	 * @param partyId
	 *            客户Id
	 * @return String 修改是否成功
	 * 
	 * @author lijing
	 * @lastModified lijing 2014-07-25 9:30:50 注：这里用VO是为了防止更新时覆盖掉表单中没有的字段数据
	 */
	@RequestMapping("/updateIndividual")
	@ResponseBody
	public Result updateIndividual(@ModelAttribute IndividualVO individualVO,
			@RequestParam String partyId) {
		// Individual motoIndividual = customerIndividualService.get(partyId);
		Individual individual = uniqueCustomerService.getIndividual(partyId);
		if(individualVO.getBirthday()!=null)
			individual.setBirthday(DateTools.stringToDate(individualVO.getBirthday(), "yyyy/MM/dd"));
		Date time = DateTools.stringToDate(individualVO.getSysUpdateDate(),
				"yyyy/MM/dd HH:mm:ss");
		
		Party party = uniqueCustomerService.findPartyByPartyId(individual
				.getPartyId().toString());
		
		Date now = uniqueCustomerService.getIndividual(partyId)
				.getSysUpdateTime();
		
		List<FamilyFriend> ffList = uniqueCustomerService
				.findFamilyFriendListByPartyId(partyId);
		String coupleName = "";
		Long coupleId = null;
		if (CollectionUtils.isNotEmpty(ffList)) {
			for (FamilyFriend ff : ffList) {
				if((!StringUtils.equals(individualVO.getMarriageCd(), dataDict.getCodeVal("MaritalStatus", "S20")))&&StringUtils.equals(ff.getFamilyFriendType(), dataDict.getCodeVal("WdCorrelativeCustTypeCd", "S1"))){
					coupleName = ff.getName();
					coupleId = ff.getCorrelativeRelationsId();
				}
				if (StringUtils.equals(ff.getTelphone(),
						individualVO.getMobileTel())) {
					return new Result(false, "客户电话号码不可以与在录的联系人重复！", null);
				}
			}
		}
		
		//如果是农户且输入过身份证，向身份证字段赋值
		if(StringUtils.equals(individual.getEmploymentType(), dataDict.getCodeVal("EmploymentType", "S2"))&&StringUtils.equals(individual.getCertificateTypeCd(), "100")){
			individual.setIdCardNum(individual.getCertificateNum());
		}
		
		/*FIXME:旧数据没有sysUpdateTime，暂且允许其通过 */
		if (time == null || now == null) {
			party.setPartyName(individualVO.getCustomerName());
			party.setMarkType(individualVO.getMarkType());
			party.setSysUpdateTime(uniqueCustomerService.getDBTime());
			uniqueCustomerService.saveParty(party);
			BeanUtils.copyProperties(individualVO, individual, "employmentType");
			uniqueCustomerService.saveIndividual(individual);
		} else {
			
			if (now.getTime() == time.getTime()) {
				party.setSysUpdateTime(uniqueCustomerService.getDBTime());
				party.setPartyName(individualVO.getCustomerName());
				party.setMarkType(individualVO.getMarkType());
				uniqueCustomerService.saveParty(party);
				BeanUtils.copyProperties(individualVO, individual, "employmentType");
				uniqueCustomerService.saveIndividual(individual);
				if(StringUtils.isNotBlank(coupleName)){
					return new Result(true, "保存已成功！\n 目前婚姻状态为<font color='red'>"+ dataDict.getCodeName("MaritalStatus", individualVO.getMarriageCd())+"</font>， 请点击'确认'按钮， 删除联系人列表中名为<font color='red'>"+coupleName+"</font>的配偶。", coupleId);
				}
			} else {
				return new Result(false, "保存失败，当前数据已不是最新,请刷新当前页面", "refresh");
			}
		}
		return new Result(true, "保存成功！", null);
	} 

	/**
	 * 客户列表中点击新增按钮时的弹窗，其保存方法
	 * 
	 * @param request
	 *            HttpServletRequest的对象
	 * @param addCustomerName
	 *            查询条件：客户名
	 * @param addCertificateType
	 *            查询条件：证件类型
	 * @param addCertificateNum
	 *            查询条件：证件编号
	 * @return String 是否保存成功
	 * 
	 * @author lijing
	 * @lastModified lijing 2014-07-25 9:30:50
	 */
	@RequestMapping("/saveSimple")
	@ResponseBody
	public String saveSimple(@ModelAttribute Individual individual,
			HttpServletRequest request) {
		String addCertificateNum = individual.getCertificateNum();
		String addCertificateType = individual.getCertificateTypeCd();
		String addCustomerName = individual.getCustomerName();
		String addCustomerEmploymentType = individual.getEmploymentType();
		if (uniqueCustomerService.findPartyByCertificateNumAndCertificateType(
				addCertificateType, addCertificateNum) == null) {
			Party newParty = new Party();
			String customerNum = commonBizNumberBS.generateCustomerNumber(
					dataDict.getCodeVal(CODETYPE_CUSTOMERTYPECD, "S2"),
					addCertificateType, addCertificateNum);
			newParty.setPartyName(addCustomerName);
			newParty.setCertificateTypeCd(addCertificateType);
			newParty.setCertificateNum(addCertificateNum);
			newParty.setCustomerNum(customerNum);
			newParty.setStatus(dataDict.getCodeVal(CODETYPE_CUSTOMERSTATUS,
					"S1"));// 默认未生效
			// newParty.setCustomerSource(dataDict.getCodeVal("CustomerSource",
			// "S02"));
			newParty.setPartyTypeCd(dataDict.getCodeVal(
					CODETYPE_CUSTOMERTYPECD, "S2"));// 客户类型统一为2-个人客户
			newParty.setSysUpdateTime(uniqueCustomerService.getDBTime());
			newParty.setCustomerSource("2");// 微贷系统录入的客户标识
			newParty.setMarkType(dataDict.getCodeVal(CODETYPE_MARKTYPE, "S01"));
			uniqueCustomerService.saveParty(newParty);
			Long partyNow = Long.valueOf(uniqueCustomerService
					.findPartyBycustomerNum(customerNum).getPartyId());
			saveInManagerTeam(newParty, request);
			Individual newIndividual = new Individual();
			newIndividual.setCertificateNum(addCertificateNum);
			newIndividual.setCertificateTypeCd(addCertificateType);
			newIndividual.setCustomerName(addCustomerName);
			newIndividual.setCustomerNum(customerNum);
			newIndividual.setNationalityCd("156");// 户籍地默认中国
			newIndividual.setProfessionalLevelCd(dataDict.getCodeVal(
					CODETYPE_CUSTOMERTYPECG, "S2"));// 客户类型默认是个体工商
			newIndividual.setPartyId(partyNow);
			newIndividual.setMarkType(dataDict.getCodeVal(CODETYPE_MARKTYPE,
					"S01"));// 前期针对邦易贷产品客户默认为借款人
			newIndividual.setStatus(dataDict.getCodeVal(
					CODETYPE_CUSTOMERSTATUS, "S1"));// 默认是未激活状态
			newIndividual.setEmploymentType(addCustomerEmploymentType);
			// Date DBtime = new Date();
			newIndividual.setCreateDate(uniqueCustomerService.getDBTime());
			newIndividual.setSysUpdateTime(newIndividual.getCreateDate());
			uniqueCustomerService.saveIndividual(newIndividual);
			// request.getSession().setAttribute("addCertificateNum",
			// addCertificateNum);
			// request.getSession().setAttribute("addCertificateType",
			// addCertificateType);
			return "singleCustomer/" + "intoForm?partyId=" + partyNow
					+ "&employmentType=" + addCustomerEmploymentType;
		} else {
			return "CeritificateRepeatedError";
		}
	}

	/**
	 * 根据客户Id查询单个客户的方法
	 * 
	 * @param model
	 *            ModelMap的对象
	 * @param customerId
	 *            需要查询的客户id
	 * @return individual 查询的客户对象
	 * 
	 * @author lijing
	 * @lastModified lijing 2014-07-25 9:30:50
	 */
	@RequestMapping("/findOneCustomer")
	@ResponseBody
	public Individual findOneCustomer(@RequestParam String customerId,
			ModelMap model) {
		// HttpSession s = request.getSession();
		// s.removeAttribute("industryTypeCd");
		Individual individual = uniqueCustomerService.getIndividual(customerId);
		// request.setAttribute("industryTypeCd", i.getIndustryCd());
		return individual;
	}

	/*********************************** 账户（CustomerAccountManagent）相关方法 *******************************************/
	/**
	 * 根据客户Id添加账户信息
	 * 
	 * @param partyId
	 *            当前客户的Id
	 * @param customerAccountManagent
	 *            前台传递的表单对象
	 * @return string 添加是否成功
	 * 
	 * @author lijing
	 * @lastModified lijing 2014-07-25 9:30:50
	 */
	@RequestMapping(value = "/addCustomerAccount", method = RequestMethod.POST)
	@ResponseBody
	public String addCustomerAccout(
			@ModelAttribute CustomerAccountManagent customerAccountManagent,
			@RequestParam String partyId) {
		CustomerAccountManagent condCustomerAccount = uniqueCustomerService
				.findAccountManagentByPartyIdAndAccountNum(partyId,
						customerAccountManagent.getAccountNum());
		if (condCustomerAccount == null) {
			customerAccountManagent.setPartyId(new BigDecimal(Long
					.valueOf(partyId)));
			uniqueCustomerService.saveAccountManagent(customerAccountManagent);
			return "success";
		} else {
			return "accountHasRegistedError";
		}
	}

	/**
	 * 根据账户Id查找CustomerAccountManagent对象
	 * 
	 * @param accountId
	 *            查找账户的Id
	 * @return customerAccountManagent 查到的账户对象
	 * 
	 * @author lijing
	 * @lastModified lijing 2014-07-25 9:30:50
	 */
	@RequestMapping("/findOneAccount")
	@ResponseBody
	public CustomerAccountManagent findOneAccount(
			@RequestParam("accountId") String accountId) {
		CustomerAccountManagent customerAccountManagent = uniqueCustomerService
				.findAccountManagentByAccountId(accountId);
		return customerAccountManagent;
	}

	/**
	 * 根据账户Id修改账户信息
	 * 
	 * @param customerAccountManagent
	 *            前台传来的表单对象
	 * @param accountId
	 *            要修改的账户的Id
	 * @param partyId
	 *            该账户所属的客户的Id
	 * @return String 修改是否成功
	 * 
	 * @author lijing
	 * @lastModified lijing 2014-07-25 9:30:50
	 */
	@RequestMapping("/modCustomerAccount")
	@ResponseBody
	public String modCustomerAccount(
			@ModelAttribute CustomerAccountManagent customerAccountManagent,
			@RequestParam String accountId, @RequestParam String partyId) {
		customerAccountManagent.setAccountId(new BigDecimal(Long
				.valueOf(accountId)));
		customerAccountManagent
				.setPartyId(new BigDecimal(Long.valueOf(partyId)));
		uniqueCustomerService.saveAccountManagent(customerAccountManagent);
		this.handleCustomerStatus(partyId);
		return "success";
	}

	/**
	 * 根据账户Id删除账户
	 * 
	 * @param id
	 *            所需删除的账户的Id
	 * @return String 删除是否成功
	 * 
	 * @author lijing
	 * @lastModified lijing 2014-07-25 9:30:50
	 */
	@RequestMapping("/delAccount/{id}")
	@ResponseBody
	public String deleteAccount(@PathVariable String id) {
		CustomerAccountManagent cam = uniqueCustomerService
				.findAccountManagentByAccountId(id);
		String partyId = (cam != null) ? (cam.getPartyId().toString()) : "001";
		uniqueCustomerService.deleteAccountManagent(id);
		this.handleCustomerStatus(partyId);
		return "success";
	}

	/**
	 * 账户列表（CustomerAccountManagent）的查询方法
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
	 * @lastModified lijing 2014-07-25 9:30:50
	 */
	@RequestMapping("/findAccount")
	@ResponseBody
	public DataTablesPage findCustomerAccountBySearch(
			@RequestParam("sEcho") Integer sEcho,
			@RequestParam("iDisplayStart") Integer firstIndex,
			@RequestParam("iDisplayLength") Integer pageSize,
			HttpServletRequest request) {
		// 查询条件
		String partyId = (String) request.getParameter("partyId");
		List<Object> params = new ArrayList<Object>();
		Page queryList = uniqueCustomerService.findAccountManagentBySearch(
				partyId, firstIndex / pageSize + 1, pageSize, params);
		DataTablesPage page = new DataTablesPage(sEcho, queryList);
		return page;
	}

	/*********************************** 联系人（FamilyFriends）相关方法 *******************************************/
	/**
	 * 联系人列表（FamilyFriends）的查询方法
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
	 * @lastModified lijing 2014-07-25 9:30:50
	 */
	@RequestMapping("/findFamilyFriends")
	@ResponseBody
	public DataTablesPage findCustomerFamilyBySearch(
			@RequestParam("sEcho") Integer sEcho,
			@RequestParam("iDisplayStart") Integer firstIndex,
			@RequestParam("iDisplayLength") Integer pageSize,
			HttpServletRequest request) {
		// 查询条件
		String partyId = (String) request.getParameter("partyId");
		List<Object> params = new ArrayList<Object>();
		Page queryList = uniqueCustomerService.findFamilyFriendPageByPartyId(
				partyId, firstIndex / pageSize + 1, pageSize, params);
		DataTablesPage page = new DataTablesPage(sEcho, queryList);
		return page;
	}

	/**
	 * 根据客户Id添加联系人
	 * 
	 * @param familyFriend
	 *            前台传来的表单对象
	 * @param partyId
	 *            客户Id
	 * @return 添加是否成功
	 * 
	 * @author lijing
	 * @lastModified lijing 2014-07-25 9:30:50
	 */
	@RequestMapping(value = "/addFamilyFriend", method = RequestMethod.POST)
	@ResponseBody
	public String addFamilyFriend(@ModelAttribute FamilyFriend familyFriend,
			@RequestParam String partyId) {
		Individual individual = uniqueCustomerService.getIndividual(partyId);
		// 身份证号不可相同
		familyFriend.setPartyId(Long.valueOf(partyId));
		if (individual.getCertificateNum().equals(
				familyFriend.getCertificateCd())
				&& individual.getCertificateTypeCd().equals(
						familyFriend.getCertificateTypeCd())) {
			return "certificateNumSameError";
		}
		// 手机号不可相同
		String customerMobel = individual.getMobileTel();
		/*
		 * if(customerMobel==null){ return "customerConditionNotSaveError"; }
		 */
		if (StringUtils.isNotBlank(individual.getMobileTel())) {
			if (individual.getMobileTel().equals(familyFriend.getTelphone())) {
				return "telephoneNumSameError";
			}
		}
		if (familyFriend.getFamilyFriendType().equals("1")) {
			if (individual.getMarriageCd() == null) {
				return "customerConditionNotSaveError";
			}
			if (!individual.getMarriageCd().equals("20")) {
				return "customerNotMarryingError";
			}
			List<FamilyFriend> ffList = uniqueCustomerService
					.findFamilyFriendListByPartyId(partyId);
			for (FamilyFriend oldFamilyFriend : ffList) {
				if (oldFamilyFriend.getFamilyFriendType().equals("1")) {
					return "coupleHasBeenRegistedError";
				}
			}
		}
		Long relationsid = this.saveCustomerCorrelativeRelations(familyFriend);
		familyFriend.setCorrelativeRelationsId(relationsid);
		uniqueCustomerService.saveFamilyFriend(familyFriend);
		return "success";
	}

	/**
	 * 向CustomerCorrelativeRelations中传递改动
	 * 
	 * @param id
	 *            familyfriend的主键
	 * @param familyFriend
	 *            保存的表单对象
	 * */
	private Long saveCustomerCorrelativeRelations(FamilyFriend familyFriend) {
		CustomerCorrelativeRelations relation = null;
		if (familyFriend.getCorrelativeRelationsId() != null) {
			relation = uniqueCustomerService
					.findCorrelativeRelationByIdAndPartyId(familyFriend
							.getCorrelativeRelationsId(), familyFriend.getPartyId());
		}
		Long partyId = familyFriend.getPartyId();
		if (relation == null) {
			relation = new CustomerCorrelativeRelations();
			Party party = uniqueCustomerService.findPartyByPartyId(String
					.valueOf(partyId));
			/*Long nextSeq = uniqueCustomerService.findNextSeqFromRelation();
			relation.setCorrelativeRelationsId(nextSeq);*/
			relation.setPartyId(partyId);
			relation.setCorrelativeCustomerName(party.getPartyName());
			relation.setCorCertificateTypeCd(party.getCertificateTypeCd());
			relation.setCorrelativeCertificateCd(party.getCertificateNum());
			relation.setCustomerNum(party.getCustomerNum());
			relation.setCreateDate(new Timestamp(new Date().getTime()));
		}
		
		relation.setCorrelativeCustomerId(partyId.longValue());
		
		relation.setTelphone(familyFriend.getTelphone());
		relation.setMobileNum(familyFriend.getMobileTel());
		
		relation.setCertificateTypeCd(familyFriend.getCertificateTypeCd());
		relation.setCertificateCd(familyFriend.getCertificateCd());
		relation.setCorrelativeCustomerTypeCd(familyFriend
				.getFamilyFriendType());
		relation.setSysUpdateTime(new Timestamp(new Date().getTime()));
		return uniqueCustomerService.saveCorrelativeRelation(relation);
	}

	/**
	 * 根据联系人Id查找联系人
	 * 
	 * @param correlativeRelationsId
	 *            联系人Id
	 * @param partyId
	 *            客户Id
	 * @return familyFriend 查到的联系人对象
	 * 
	 * @author lijing
	 * @lastModified lijing 2014-07-25 9:30:50
	 */
	@RequestMapping("/findOneFamilyFriend")
	@ResponseBody
	public FamilyFriend findOneFamilyFriend(
			@RequestParam("correlativeRelationsId") String correlativeRelationsId) {
		FamilyFriend familyFriend = uniqueCustomerService
				.findFamilyFriendByCorrelativeRelationsId(Long
						.valueOf(correlativeRelationsId));
		return familyFriend;
	}

	/**
	 * 根据联系人Id删除联系人
	 * 
	 * @param id
	 *            联系人Id
	 * @return String 删除是否成功
	 * 
	 * @author lijing
	 * @lastModified lijing 2014-07-25 9:30:50
	 */
	@RequestMapping("/delFamilyFriend/{id}")
	@ResponseBody
	public Result delFamilyFriend(@PathVariable String id) {
		
		FamilyFriend ff = uniqueCustomerService.findFamilyFriendByCorrelativeRelationsId(Long.valueOf(id));
		if(ff==null){
			return new Result(false, "该记录已被删除，请刷新页面！", null);
		}
//		String marriageCd = uniqueCustomerService.findIndividualMarriageCd(ff.getPartyId());
//		if(StringUtils.equals(marriageCd, dataDict.getCodeVal("MaritalStatus", "S20"))&&StringUtils.equals(ff.getFamilyFriendType(), dataDict.getCodeVal("WdCorrelativeCustTypeCd", "S1"))){
//			
//		}
		uniqueCustomerService.deleteFamilyFriend(id);
		CustomerCorrelativeRelations relation = uniqueCustomerService
				.findCorrelativeRelationById(Long.valueOf(id));
		if (relation != null) {
			uniqueCustomerService.deleteCorrelativeRelation(Long.valueOf(id));
			this.handleCustomerStatus(relation.getPartyId().toString());// 再次修改置为未生效
		}
		return new Result(true, "删除联系人成功！", null);
	}

	/**
	 * 根据联系人Id修改联系人
	 * 
	 * @param familyFriend
	 *            前台传来的表单对象
	 * @param correlativeRelationsId
	 *            联系人Id
	 * @param partyId
	 *            客户Id
	 * @return String 修改是否成功
	 * 
	 * @author lijing
	 * @lastModified lijing 2014-07-25 9:30:50
	 */
	@RequestMapping("/modFamilyFriend")
	@ResponseBody
	public String modFamilyFriend(@ModelAttribute FamilyFriend familyFriend,
			@RequestParam String correlativeRelationsId,
			@RequestParam String partyId) {
		Individual individual = uniqueCustomerService.getIndividual(partyId);
		// 身份证号不可相同
		if (individual.getCertificateNum().equals(
				familyFriend.getCertificateCd())
				&& individual.getCertificateTypeCd().equals(
						familyFriend.getCertificateTypeCd())) {
			return "certificateNumSameError";
		}
		// 手机号不可相同
		String customerMobel = individual.getMobileTel();
		/*
		 * if(customerMobel==null){ return "customerConditionNotSaveError"; }
		 */
		if (StringUtils.isNotBlank(individual.getMobileTel())) {
			if (individual.getMobileTel().equals(familyFriend.getTelphone())) {
				return "telephoneNumSameError";
			}
		}
		if (familyFriend.getFamilyFriendType().equals("1")) {
			if (individual.getMarriageCd() == null) {
				return "customerConditionNotSaveError";
			}
			if (!individual.getMarriageCd().equals("20")) {
				return "customerNotMarryingError";
			}
			List<FamilyFriend> ffList = uniqueCustomerService
					.findFamilyFriendListByPartyId(partyId);
			for (FamilyFriend oldFamilyFriend : ffList) {
				if (oldFamilyFriend.getFamilyFriendType().equals("1")
						&& !oldFamilyFriend.getCorrelativeRelationsId()
								.toString().equals(correlativeRelationsId)) {
					return "coupleHasBeenRegistedError";
				}
			}
		}
		familyFriend.setCorrelativeRelationsId(Long
				.valueOf(correlativeRelationsId));
		familyFriend.setPartyId(Long.valueOf(partyId));
		FamilyFriend ff = uniqueCustomerService
				.findFamilyFriendByCorrelativeRelationsId(Long
						.valueOf(correlativeRelationsId));
		BeanUtils.copyProperties(familyFriend, ff);
		ff.setPartyId(Long.valueOf(partyId));
		Long relationsId = this.saveCustomerCorrelativeRelations(familyFriend);
		ff.setCorrelativeRelationsId(relationsId);
		uniqueCustomerService.saveFamilyFriend(ff);
		this.handleCustomerStatus(partyId);// 再次修改置为未生效
		return "success";
	}
	
	
	/*********************************** 文档（DocumentIndex）相关方法 *******************************************/
	/**
	 * 文档列表（DocumentIndex）的查询方法
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
	 * @lastModified lijing 2014-07-25 9:30:50
	 */
	@RequestMapping("/findDocuments")
	@ResponseBody
	public DataTablesPage findCustomerDocumentsBySearch(
			@RequestParam("sEcho") Integer sEcho,
			@RequestParam("iDisplayStart") Integer firstIndex,
			@RequestParam("iDisplayLength") Integer pageSize,
			HttpServletRequest request) {
		// 查询条件
		String partyId = (String) request.getParameter("partyId");
		String documentName = request.getParameter("query_documentName");
		String contentType = request.getParameter("query_contentType");
		List<Object> params = new ArrayList<Object>();
		String customerName = uniqueCustomerService.getIndividual(partyId)
				.getCustomerName();
		ShiroUser curUser = (ShiroUser) SecurityUtils.getSubject()
				.getPrincipal();
		params.add(partyId);
		params.add(documentName);
		params.add("('01')");
		params.add(contentType);
		List<Object[]> finalList = new ArrayList<Object[]>();
		Page queryList = uniqueCustomerService.findDocumentIndexBySearch(
				firstIndex / pageSize + 1, pageSize, params);
		List<Object[]> resultList = queryList.getContent();
		for (Object[] obj : resultList) {
			obj[4] = customerName;
			// obj[4] = curUser.getName();
			obj[6] = timeStampToString((Timestamp) obj[6]);
		}
		DataTablesPage page = new DataTablesPage(sEcho, queryList);
		return page;
	}

	/**
	 * 查找下载地址与文件名
	 * 
	 * @param documentId
	 *            文档Id
	 * @return List 对象
	 * @author lijing
	 * @lastModified lijing 2014-08-19 17:30:50
	 */
	@RequestMapping("/findDocumentPath")
	@ResponseBody
	public List<String> findDocumentPath(@RequestParam String documentId) {
		DocumentIndex documentIndex = uniqueCustomerService
				.findDocumentIndexByDocumentId(documentId);
		List<String> list = new ArrayList<String>();
		list.add(documentIndex.getDocumentRoute());
		list.add(documentIndex.getDocumentName());
		return list;
	}

	/**
	 * 新增文档前的参数获取
	 * 
	 * @param partyId
	 *            客户Id
	 * @param request
	 *            HttpServletRequest
	 * @return DocumentUploadVO 对象
	 * @author lijing
	 * @lastModified lijing 2014-08-18 9:30:50
	 */
	@RequestMapping("/beforeUpdate")
	@ResponseBody
	public DocumentUploadVO beforeUpdate(@RequestParam String partyId,
			HttpServletRequest request) {
		DocumentUploadVO documentUploadVO = new DocumentUploadVO();
		documentUploadVO.setPartyId(partyId);
		ShiroUser curUser = (ShiroUser) SecurityUtils.getSubject()
				.getPrincipal();
		Individual individual = uniqueCustomerService.getIndividual(partyId);
		documentUploadVO.setUserNum(curUser.getId().toString());
		//modify by HWL start 20150701 修改为登录机构
		documentUploadVO.setCreateOrgCd(curUser.getLogOrgid().toString());
		//modify by HWL end 20150701 修改为登录机构
		documentUploadVO.setCreateDateTime(timeStampToString(new Timestamp(
				new Date().getTime())));
		documentUploadVO.setCustomerNum(individual.getCustomerNum());
		documentUploadVO.setBizNum("");
		documentUploadVO.setBizId("-1");
		documentUploadVO.setDocumentType("01");
		documentUploadVO.setCreateUserName(curUser.getName());
		documentUploadVO.setFileTypes("doc,docx,xls,xlsx,pdf,jpg,gif,png,rar");
		documentUploadVO.setCreateUserNum(curUser.getId().toString());
		documentUploadVO.setCreateTypeCd("01");
		documentUploadVO.setDocumentNum(commonBizNumberBS.generateDocumentNum(
				individual.getCustomerNum(), "01"));
		return documentUploadVO;
	}

	/**
	 * 根据文档Id删除文档
	 * 
	 * @param id
	 *            文档Id
	 * @return String 删除是否成功
	 * 
	 * @author lijing
	 * @lastModified lijing 2014-08-19 9:30:50
	 */
	@RequestMapping("/delDocument/{id}")
	@ResponseBody
	public String delDocument(@PathVariable String id) {
		DocumentIndex documentIndex = uniqueCustomerService
				.findDocumentIndexByDocumentId(id);
		String documentType = documentIndex.getDocumentType();
		// “引用”状态的文档不能删除
		if (dataDict.getCodeVal(CODETYPE_DTCREATETYPE, "S2").equals(
				documentIndex.getCreateTypeCd())) {
			return "createTypeCdEquals2Error";
		}
		ShiroUser curUser = (ShiroUser) SecurityUtils.getSubject()
				.getPrincipal();
		documentIndex.setSystemUpdateTime(new Timestamp(new Date().getTime()));
		documentIndex.setUserNum(curUser.getId().toString());
		documentIndex.setStatus("2");
		uniqueCustomerService.saveDocumentIndex(documentIndex);
		this.handleCustomerStatus(documentIndex.getPartyId().toString());
		return "success";
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
		String employmentType = uniqueCustomerService
				.getEmploymentTypeByPartyId(partyId);
		String custDocTypeCd = "";
		if (StringUtils.equals(employmentType,
				dataDict.getCodeVal(CODETYPE_EMPLOYMENTTYPE, "S1"))) {
			custDocTypeCd = DocStageCode.SX_DOC.getCodeId(); // 受薪
		} else if (StringUtils.equals(employmentType,
				dataDict.getCodeVal(CODETYPE_EMPLOYMENTTYPE, "S2"))) {
			custDocTypeCd = DocStageCode.NH_DOC.getCodeId(); // 农户
		} else if (StringUtils.equals(employmentType,
				dataDict.getCodeVal(CODETYPE_EMPLOYMENTTYPE, "S3"))) {
			custDocTypeCd = DocStageCode.JY_DOC.getCodeId(); // 经营
		} else if (StringUtils.equals(employmentType,
				dataDict.getCodeVal(CODETYPE_EMPLOYMENTTYPE, "S4"))) {
			custDocTypeCd = DocStageCode.STUDENT_DOC.getCodeId(); // 学生
		}else{
			custDocTypeCd = DocStageCode.NULL_DOC.getCodeId();
		}
		return documentService.findUploadCustDocTypes(null, partyId,
				custDocTypeCd);
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
		String employmentType = uniqueCustomerService
				.getEmploymentTypeByPartyId(partyId);
		String custDocTypeCd = "";

		if(StringUtils.equals(employmentType, dataDict.getCodeVal(CODETYPE_EMPLOYMENTTYPE, "S1"))){
			custDocTypeCd = DocStageCode.SX_DOC.getCodeId(); //受薪
		}else if(StringUtils.equals(employmentType, dataDict.getCodeVal(CODETYPE_EMPLOYMENTTYPE, "S2"))){
			custDocTypeCd = DocStageCode.NH_DOC.getCodeId(); //农户
		}else if(StringUtils.equals(employmentType, dataDict.getCodeVal(CODETYPE_EMPLOYMENTTYPE, "S3"))){
			custDocTypeCd = DocStageCode.JY_DOC.getCodeId(); //经营
		} else if (StringUtils.equals(employmentType,
				dataDict.getCodeVal(CODETYPE_EMPLOYMENTTYPE, "S4"))) {
			custDocTypeCd = DocStageCode.STUDENT_DOC.getCodeId(); // 学生
		}
		return documentService.findDocumentCustDocTypes(null, partyId,
				custDocTypeCd);
	}

	/*********************************** 经营信息（CustomerBusinessInfo）相关方法 *******************************************/
	/**
	 * 根据客户Id更新经营信息
	 * 
	 * @param customerBusinessInfo
	 *            前台传来的表单对象
	 * @param partyId
	 *            客户Id
	 * @return String 修改是否成功
	 * 
	 * @author lijing
	 * @lastModified lijing 2014-07-25 9:30:50
	 */
	@RequestMapping("/updateBusinessInfo")
	@ResponseBody
	public String updateBusinessInfo(
			@ModelAttribute CustomerBusinessInfo customerBusinessInfo,
			@RequestParam String partyId) {
		CustomerBusinessInfo c = uniqueCustomerService
				.findBussinessInfoByPartyId(Long.valueOf(partyId));
		if (c != null) {
			customerBusinessInfo.setId(c.getId());
			BeanUtils.copyProperties(customerBusinessInfo, c);
			c.setPartyId(Long.valueOf(partyId));
			uniqueCustomerService.saveCustomerBusinessInfo(c);
		} else {
			customerBusinessInfo.setPartyId(Long.valueOf(partyId));
			uniqueCustomerService
					.saveCustomerBusinessInfo(customerBusinessInfo);
		}
		return "success";
	}

	/**
	 * 根据客户Id查询单个经营信息的方法
	 * 
	 * @param model
	 *            ModelMap的对象
	 * @param customerId
	 *            需要查询的客户id
	 * @return individual 查询的客户对象
	 * 
	 * @author lijing
	 * @lastModified lijing 2014-07-25 9:30:50
	 */
	@RequestMapping("/findOneBusiness")
	@ResponseBody
	public CustomerBusinessInfo findOneBusiness(
			@RequestParam String customerId, ModelMap model) {
		// HttpSession s = request.getSession();
		// s.removeAttribute("industryTypeCd");
		CustomerBusinessInfo customerBusinessInfo = uniqueCustomerService
				.findBussinessInfoByPartyId(Long.valueOf(customerId));
		// request.setAttribute("industryTypeCd", i.getIndustryCd());
		return customerBusinessInfo;
	}

	/*********************************** 受薪、经营客户财务表（SALA_BUSI_CUST_FINALCIAL）相关方法 *******************************************/
	/**
	 * 受薪、经营客户财务列表（SalaBusiCustFinalcial）的查询方法
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
	 * @lastModified lijing 2014-07-25 9:30:50
	 */
	@RequestMapping("/findProjectFinalcials")
	@ResponseBody
	public DataTablesPage findProjectFinalcials(
			@RequestParam("sEcho") Integer sEcho,
			@RequestParam("iDisplayStart") Integer firstIndex,
			@RequestParam("iDisplayLength") Integer pageSize,
			HttpServletRequest request) {
		String partyId = (String) request.getParameter("partyId");
		List<Object> params = new ArrayList<Object>();
		params.add(partyId);
		Page queryList = uniqueCustomerService.findProjectFinalcials(firstIndex
				/ pageSize + 1, pageSize, params);
		DataTablesPage page = new DataTablesPage(sEcho, queryList);
		return page;
	}

	/**
	 * 根据salaCustFinacial的id查对象
	 * 
	 * @param id
	 *            主键
	 * @return SalaBusiCustFinalcial 对象
	 * */
	@RequestMapping("/findOneSalaCustFinace")
	@ResponseBody
	public SalaBusiCustFinalcial findOneSalaCustFinace(
			@RequestParam("id") String id) {
		return uniqueCustomerService.getOneSalaCustFinace(id);
	}

	/*********************************** 简要财务信息（CustomerBrieflyFinace）相关方法 *******************************************/
	/**
	 * 根据客户Id更新简要财务信息
	 * 
	 * @param customerBrieflyFinace
	 *            前台传来的表单对象
	 * @param partyId
	 *            客户Id
	 * @return String 修改是否成功
	 * 
	 * @author lijing
	 * @lastModified lijing 2014-07-25 9:30:50
	 */
	@RequestMapping("/updateFinance")
	@ResponseBody
	public String updateFinance(
			@ModelAttribute CustomerBrieflyFinace customerBrieflyFinace,
			@RequestParam String partyId) {
		CustomerBrieflyFinace c = uniqueCustomerService
				.findFinaceByPartyId(Long.valueOf(partyId));
		Individual iv = uniqueCustomerService.getIndividual(partyId);
		if (!StringUtils.equals(iv.getHouseCondition(), customerBrieflyFinace
				.getLocalHouseCondition().toString())) {
			iv.setHouseCondition(customerBrieflyFinace.getLocalHouseCondition()
					.toString());
			uniqueCustomerService.saveIndividual(iv);
		}
		if (c != null) {
			customerBrieflyFinace
					.setCustomerFinanceId(c.getCustomerFinanceId());
			BeanUtils.copyProperties(customerBrieflyFinace, c);
			c.setPartyId(Long.valueOf(partyId));
			uniqueCustomerService.saveCustomerBrieflyFinace(c);
		} else {
			customerBrieflyFinace.setPartyId(Long.valueOf(partyId));
			uniqueCustomerService
					.saveCustomerBrieflyFinace(customerBrieflyFinace);
		}
		return "success";
	}

	/**
	 * 根据客户Id查找单个简要财务信息
	 * 
	 * @param customerId
	 *            客户Id
	 * @return CustomerBrieflyFinace对象 查找出的简要财务信息对象
	 * 
	 * @author lijing
	 * @lastModified lijing 2014-07-25 9:30:50
	 */
	@RequestMapping("/findOneFinace")
	@ResponseBody
	public CustomerBrieflyFinace findOneFinace(@RequestParam String customerId) {
		return uniqueCustomerService.findFinaceByPartyId(Long
				.valueOf(customerId));
	}

	/*********************************** 自定义标签的相关方法 *******************************************/
	/**
	 * 自定义标签中查找行业分类
	 * 
	 * @return List<IndustryType>对象 查找出的行业分类
	 * 
	 * @author lijing
	 * @lastModified lijing 2014-07-25 9:30:50
	 */
	@RequestMapping(value = "/getAllIndustry", method = RequestMethod.POST)
	@ResponseBody
	public List<IndustryType> getAllIndustry() {
		return uniqueCustomerService.getAllIndustry();
	}

	/*********************************** 提供农户业务申请接口 *******************************************/
	/**
	 * 获取农贷业务客户基本信息
	 * @param customerId
	 * @return 
	 * */
	@RequestMapping(value = "/getIndividualAgro", method = RequestMethod.POST)
	@ResponseBody
	public IndividualAgroVO getIndividualAgro(@RequestParam String customerId){
		Individual indiv = uniqueCustomerService.getIndividual(customerId);
		IndividualAgroVO agroVo = new IndividualAgroVO();
		if(indiv != null){
			BeanUtils.copyProperties(indiv, agroVo);
			if(StringUtils.equals(indiv.getEmploymentType(), dataDict.getCodeVal("EmploymentType", "S2"))&&StringUtils.equals(indiv.getCertificateTypeCd(), "100")){
				indiv.setIdCardNum(indiv.getCertificateNum());
			}
		}
		return agroVo;
	}
	/**
	 * 保存农贷业务客户基本信息
	 * @param argoVo
	 * @return Result
	 * */
	@RequestMapping(value = "/updateIndividualForAgro", method = RequestMethod.POST)
	@ResponseBody
	public Result updateIndividualForAgro(@ModelAttribute IndividualAgroVO argoVo){
		Individual indiv = uniqueCustomerService.getIndividual(argoVo.getPartyId().toString());
		Party party = uniqueCustomerService.findPartyByPartyId(argoVo.getPartyId().toString());
		List<FamilyFriend> ffList = uniqueCustomerService
				.findFamilyFriendListByPartyId(argoVo.getPartyId().toString());
		boolean hasCouple = false;
		for (FamilyFriend ff : ffList) {
			if(ff.getFamilyFriendType().equals("1")){
				hasCouple = true;
			}
		}
		if(indiv != null){
			if(hasCouple && !StringUtils.equals(argoVo.getMarriageCd(), dataDict.getCodeVal("MaritalStatus", "S20"))){
				return new Result(false, "该客户添加了配偶家庭成员，无法改为非已婚状态，请先删除配偶家庭成员", null);
			}
			if(!hasCouple && StringUtils.equals(argoVo.getMarriageCd(), dataDict.getCodeVal("MaritalStatus", "S20"))){
				return new Result(false, "已婚客户请添加配偶联系人", null);
			}
			String relations = "";
			for (FamilyFriend familyFriend : ffList) {
				relations += familyFriend.getRelationsType() + "_";
			}
			if (!relations.contains(dataDict.getCodeVal("RelationsType", "S3"))) {
				return new Result(false, "请填写联系人信息，至少包含一名亲属，如已婚请填写配偶信息",
						null);
			}
			
			BeanUtils.copyProperties(argoVo, indiv, "partyId", "customerName");
			uniqueCustomerService.saveIndividual(indiv);
		}else{
			return new Result(false, "保存失败！", null);
		}
		return new Result(true, "保存成功！", null);
	}
	//
	/**
	 * 农贷联系人列表（FamilyFriends）的查询方法
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
	 * @lastModified lijing 2014-07-25 9:30:50
	 */
	@RequestMapping("/findCustFamilyAgroBySearch")
	@ResponseBody
	public DataTablesPage findCustFamilyAgroBySearch(
			@RequestParam("sEcho") Integer sEcho,
			@RequestParam("iDisplayStart") Integer firstIndex,
			@RequestParam("iDisplayLength") Integer pageSize,
			HttpServletRequest request) {
		// 查询条件
		String partyId = (String) request.getParameter("customerId");
		List<Object> params = new ArrayList<Object>();
		Page qPage = uniqueCustomerService.findFamilyFriendAgroBySearch(
				firstIndex / pageSize + 1, pageSize, partyId);
		List<FamilyFriendAgroVo> finalList = Lists.transform(qPage.getContent(), new Function<Object[],FamilyFriendAgroVo>(){

			@Override
			public FamilyFriendAgroVo apply(Object[] input) {
				return new FamilyFriendAgroVo(input);
			}
			
		});
		
		DataTablesPage page = new DataTablesPage(sEcho, qPage);
		page.setAaData(finalList);
		return page;
	}
	@RequestMapping("/addFamilyFriendForAgro")
	@ResponseBody
	public Result addFamilyFriendForAgro(@ModelAttribute FamilyFriendAgroVo ffVo, @RequestParam String partyId,HttpServletRequest request){
		Individual individual = uniqueCustomerService.getIndividual(partyId);
		// 身份证号不可相同
		FamilyFriend familyFriend = uniqueCustomerService.findFamilyFriendByCorrelativeRelationsId(ffVo.getCorrelativeRelationsId());
		if(familyFriend==null){
			familyFriend = new FamilyFriend();
		}
		BeanUtils.copyProperties(ffVo, familyFriend, "correlativeRelationsId");
		familyFriend.setPartyId(Long.valueOf(partyId));
		
		
		
		// 手机号不可相同
		String customerMobel = individual.getMobileTel();
		/*
		 * if(customerMobel==null){ return "customerConditionNotSaveError"; }
		 */
		if (StringUtils.isNotBlank(individual.getMobileTel())) {
			if (individual.getMobileTel().equals(familyFriend.getTelphone())) {
				return new Result(false, "联系人手机号不可与客户本人相同！", null);
			}
		}
		if (familyFriend.getFamilyFriendType().equals("1")) {//配偶
			familyFriend.setRelationsType("1");
			/*if (!individual.getMarriageCd().equals("20")) {
				return new Result(false, "非已婚状态无法添加配偶！", null);
			}*/
			//上面代码从数据库获取客户婚姻状况不准确，应从前台获取
			String marriageCd = (String) request.getParameter("marriageCd");
			if (!marriageCd.equals("20")) {
				return new Result(false, "非已婚状态无法添加配偶！", null);
			}
			List<FamilyFriend> ffList = uniqueCustomerService
					.findFamilyFriendListByPartyId(partyId);
			for (FamilyFriend oldFamilyFriend : ffList) {
				System.out.println("11="+oldFamilyFriend.getFamilyFriendType()+"22="+oldFamilyFriend.getCorrelativeRelationsId()+"33="+familyFriend.getCorrelativeRelationsId());
				//if (oldFamilyFriend.getFamilyFriendType().equals("1")&&oldFamilyFriend.getCorrelativeRelationsId().compareTo(familyFriend.getCorrelativeRelationsId())!=0) {
				if (oldFamilyFriend.getFamilyFriendType().equals("1")) {
					return new Result(false, "该客户已添加过配偶！", null);
				}
			}
		}else{
			familyFriend.setRelationsType("3");//亲属
		}
		Long relationsid = this.saveCustomerCorrelativeRelations(familyFriend);
		familyFriend.setCorrelativeRelationsId(relationsid);
		uniqueCustomerService.saveFamilyFriend(familyFriend);
		return new Result(true, "保存成功！", null);
	}
	
	//添加核实人信息
	@RequestMapping("/addVerificatPerson")
	@ResponseBody
	public Result addVerificatPerson(@ModelAttribute VerificatPersonInfo verificatPersonInfo, @RequestParam String partyId,HttpServletRequest request){
		Individual individual = uniqueCustomerService.getIndividual(partyId);
		
		verificatPersonInfo.setPartyId(Long.valueOf(partyId));
		
		// 手机号不可相同
		if (StringUtils.isNotBlank(individual.getMobileTel())) {
			if (individual.getMobileTel().equals(verificatPersonInfo.getTelphone())) {
				return new Result(false, "核实人人手机号不可与客户本人相同！", null);
			}
		}else{
			return new Result(false, "核实人人手机必填！", null);
		}
		verificatPersonInfo.setCreateDate(new Date());
		verificatPersonInfo.setUpdateDate(new Date());
		uniqueCustomerService.saveVerificatPersonInfo(verificatPersonInfo);
		return new Result(true, "保存成功！", null);
	}
	
	/**
	 * 查询核实人列表
	 */
	@RequestMapping("/findVerificatPersonInfo")
	@ResponseBody
	public DataTablesPage findVerificatPersonInfo(
			@RequestParam("sEcho") Integer sEcho,
			@RequestParam String partyId,
			@RequestParam("iDisplayStart") Integer firstIndex,
			@RequestParam("iDisplayLength") Integer pageSize,
			HttpServletRequest request) {
		// 查询条件
		//List<VerificatPersonInfo> finalList
		Page qPage = uniqueCustomerService.findVerificatPersonInfo(
				firstIndex / pageSize + 1, pageSize, partyId);
		DataTablesPage page = new DataTablesPage(sEcho , qPage);
		//page.setAaData(finalList);
		
		return page;
	}
	/**
	 * 根据核实人Id查找核实人
	 */
	@RequestMapping("/findOneVerificatPerson")
	@ResponseBody
	public VerificatPersonInfo findOneVerificatPerson(
			@RequestParam("id") String id) {
		VerificatPersonInfo verificatPersonInfo = uniqueCustomerService.findVerificatPersonInfoById(Long.valueOf(id));
		return verificatPersonInfo;
	}
	/**
	 * 根据核实人Id删除核实人
	 */
	@RequestMapping("/delVerificatPerson/{id}")
	@ResponseBody
	public Result delVerificatPerson(@PathVariable String id) {
		
		VerificatPersonInfo ff = uniqueCustomerService.findVerificatPersonInfoById(Long.valueOf(id));
		if(ff==null){
			return new Result(false, "该记录已被删除，请刷新页面！", null);
		}
		uniqueCustomerService.deleteVerificatPersonInfo(id);
		return new Result(true, "删除核实人成功！", null);
	}
	
	/*********************************** 本控制器中所使用到的工具方法 *******************************************/
	@RequestMapping("/longAjax")
	@ResponseBody
	public Result longAjax(@RequestParam Long timed, @RequestParam Long queryCount, HttpServletRequest req){
		ShiroUser curUser = (ShiroUser) SecurityUtils.getSubject()
				.getPrincipal();
		if(queryCount==null){
				return new Result(false);
		}
		
		while(true){
			Long nowCount = uniqueCustomerService.findCustomerListCount(curUser.getId(),curUser.getOrgid(),queryCount);//TODO
				if(queryCount.compareTo(nowCount)!=0){
					break;
				}else{
					try {
						Thread.sleep(1200000L);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
		}
		
		return new Result(true,"need refresh",null);
	}
	
	
	/**
	 * 新增客户时，做新增客户与添加者的关联
	 * 
	 * @param newParty
	 *            新增客户对象
	 * @param request
	 *            HttpServletRequest的对象
	 * 
	 * @author lijing
	 * @lastModified lijing 2014-07-25 9:30:50
	 */
	public void saveInManagerTeam(Party newParty, HttpServletRequest request) {
		HttpSession session = request.getSession();
		ShiroUser curUser = (ShiroUser) SecurityUtils.getSubject()
				.getPrincipal();
		CustomerManagerTeam customerManagerTeam = new CustomerManagerTeam();
		customerManagerTeam.setPartyId(newParty.getPartyId());
		//customerManagerTeam.setOrgCd(curUser.getOrgid().toString());//TODO
		customerManagerTeam.setOrgCd(curUser.getLogOrgid().toString());//TODO
		customerManagerTeam.setCustomerNum(newParty.getCustomerNum());
		customerManagerTeam.setCreateDate(new Timestamp(new Date().getTime()));
		customerManagerTeam.setUserNum(curUser.getId().toString());
		customerManagerTeam.setManagerType("01");
		uniqueCustomerService.saveManagerTeam(customerManagerTeam);
	}

	/**
	 * Timestamp转化成String的方法
	 * 
	 * @param st
	 *            Timestamp类型的变量
	 * @return String 返回时间转换成的字符串
	 * 
	 * @author lijing
	 * @lastModified lijing 2014-07-25 9:30:50
	 */
	public String timeStampToString(Timestamp st) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 定义格式，不显示毫秒
		String str = "";
		if (st != null) {
			str = df.format(st);
		} else {
			str = df.format(new Date());
		}
		return str;
	}

	/**
	 * 数组扩容的方法
	 * 
	 * @param obj
	 *            旧数组
	 * @param newlength
	 *            扩容后数组的长度
	 * @param oldlength
	 *            旧数组的长度
	 * @return newArray 返回一个新数组，其每个成员都是Object
	 * 
	 * @author lijing
	 * @lastModified lijing 2014-07-25 9:30:50
	 */
	public Object[] arrayGrow(Object obj, Integer newlength, Integer oldlength) {
		Class componentType = obj.getClass().getComponentType();
		Object newArray = Array.newInstance(componentType, newlength);
		System.arraycopy(obj, 0, newArray, 0, oldlength);
		return (Object[]) newArray;
	}

	/**
	 * 修改用户状态，置为未生效
	 * 
	 * @param partyId
	 * @return boolean true未生效，false生效
	 * */
	public boolean handleCustomerStatus(String partyId) {
		Individual individual = uniqueCustomerService.getIndividual(partyId);
		if (individual != null) {
			if (StringUtils.isNotBlank(individual.getStatus())
					&& StringUtils.equals(individual.getStatus(),
							dataDict.getCodeVal("CustomerStatusCode", "S2"))) {
				individual.setStatus(dataDict.getCodeVal(
						CODETYPE_CUSTOMERSTATUS, "S1"));
				uniqueCustomerService.saveIndividual(individual);
				return true;
			}
			return false;
		}
		return false;
	}

	/**
	 * 检查客户是否有发起中的业务
	 * 
	 * @param String
	 *            partyId
	 * @return boolean true：没有 ，false：有
	 * */
	public boolean checkBizStatus(String partyId) {
		boolean check = businessApplicationService.checkState(
				Long.valueOf(partyId), "1,2");
		return check;
	}

	/**
	 * 检查用户生效状态
	 * */
	@RequestMapping("/actAlive")
	@ResponseBody
	public Result actAlive(@RequestParam Long partyId) {
		boolean status = uniqueCustomerService.findIndividualStatus(partyId);
		return new Result(status);
	}
	
	/**
	 * 个人客户、企业客户信息查看
	 * @return
	 */
	@RequestMapping("/toDetail")
	public String toDetail(Long partyId){
		Party party=this.uniqueCustomerService.findParty(partyId);
		if(party!=null){
			if(StringUtils.equals(dataDict.getCodeVal("CustomerType", "S2"), party.getPartyTypeCd())){
				//个人客户
				return StringUtils.join("redirect:/singleCustomer/backToForm?customerId=",String.valueOf(partyId),"&workCode=TODETAIL&consultLocation=customer");
			}else if(StringUtils.equals(dataDict.getCodeVal("CustomerType", "S1"), party.getPartyTypeCd())){
				//corpcustomer/showDetail/17340?consultLocation=
				//企业客户
				return StringUtils.join("redirect:/corpcustomer/showDetail/",String.valueOf(partyId),"?consultLocation=");
			}
		}
		return "error/500";
	}
}
