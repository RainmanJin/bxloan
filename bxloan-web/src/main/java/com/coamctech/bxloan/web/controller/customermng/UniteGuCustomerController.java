package com.coamctech.bxloan.web.controller.customermng;

import static com.coamctech.bxloan.commons.GlobalConstants.UNITE_CUSMNG;
import static com.coamctech.bxloan.commons.GlobalConstants.UNITE_DETAIL_MNG;
import static com.coamctech.bxloan.commons.GlobalConstants.UNITE_MNG;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
import com.coamctech.bxloan.commons.utils.NumberUtil;
import com.coamctech.bxloan.datadict.DataDict;
import com.coamctech.bxloan.document.CustDocInfo;
import com.coamctech.bxloan.entity.Party;
import com.coamctech.bxloan.entity.UnCustTab;
import com.coamctech.bxloan.entity.UniteGuNego;
import com.coamctech.bxloan.service.bizapply.BizCreditApplicationService;
import com.coamctech.bxloan.service.bizapply.BusinessApplicationService;
import com.coamctech.bxloan.service.customermng.UniteGuCustomerService;
import com.coamctech.bxloan.service.exceptions.ServiceException;
import com.coamctech.bxloan.service.model.DocumentUniteVO;
import com.coamctech.bxloan.service.model.customermng.CustomerGuVO;
import com.coamctech.bxloan.service.model.customermng.UniteCustomerVO;
import com.coamctech.bxloan.service.model.tipmsg.MsgResult;
import com.coamctech.bxloan.service.model.tipmsg.ResultEnums;
import com.coamctech.bxloan.service.sysmng.DocumentService;
import com.coamctech.bxloan.service.sysmng.ICommonBizNumberBS;
import com.coamctech.bxloan.web.security.ShiroUser;
import com.coamctech.bxloan.web.vo.customermng.DocumentUploadVO;
import com.coamctech.bxloan.web.vo.customermng.UniteGuNegoVO;
import com.coamctech.bxloan.web.vo.sysmng.CustManagerVO;
import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@Controller
@RequestMapping("/" + UNITE_CUSMNG)
public class UniteGuCustomerController extends BaseController{
	
	private final String UN_STATUS="UnStatus";
	private Logger logger=LoggerFactory.getLogger(UniteGuCustomerController.class);
	@Autowired
	private DataDict dataDict;
	@Autowired
	private ICommonBizNumberBS commonBizNumberBS;
	@Autowired
	private UniteGuCustomerService uniteGuCustomerService;
	@Autowired
	private DocumentService documentService;
	@Autowired
	private BizCreditApplicationService bizCreditApplicationService;
	@Autowired
	private BusinessApplicationService businessApplicationService;
	
	/*********************************** 跳转方法 *******************************************/
	/**
	 * sideBar上点击联保体客户的跳转方法
	 * 
	 * 
	 * @return String 跳转路径
	 * 
	 * @author lijing
	 * @lastModified lijing 2014-07-25 16:37:51
	 */
	@RequestMapping
	public String init() {
		return UNITE_MNG;
	}
	
	/**
	 * 跳转至详情页面
	 * @param action 动作类型：add添加，mod修改，view查看
	 * @param id 联保体主键id
	 * */
	@RequestMapping("/findDetail")
	public String findDetail(@RequestParam String action, @RequestParam Long id, ModelMap model){
		UniteGuNego ugn=this.uniteGuCustomerService.findUniteCustomerById(id);
		model.addAttribute("action", action);
		model.addAttribute("ugn", ugn);
		model.addAttribute("allDocType", DocStageCode.LBT_DOC.getCodeId());
		model.addAttribute("uploadPath", GlobalConstants.UPLOAD_DESTINATION);
		return UNITE_DETAIL_MNG;
	}
	
	
	/**
	 * 联保体客户列表
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
		Set<String> keyset = ImmutableSet.of("unGuId", "unGroupName","customerNum","customerName","certificateTypeCd","certificateNum","unStatus","custManager");
		Map<String,String> params = Maps.newHashMap();
		for (String key : keyset) {
			params.put(key, request.getParameter(key));
		}
		ShiroUser curUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		final Long userId=curUser.getId();//当前登录人id
		/**
		 * 2015-7-2 libingqing 修改传递参数
		 */
		params.put("orgId", curUser.getLogOrgid().toString());//当前登录机构ID
		Page<Object[]> dPage = uniteGuCustomerService.findUniteCustomerBySearch(
				firstIndex / pageSize, pageSize, params);
		List<UniteCustomerVO> finalList =  Lists.transform(dPage.getContent(), new Function<Object[], UniteCustomerVO>(){
			@Override
			public UniteCustomerVO apply(Object[] input) {
				UniteCustomerVO vo=new UniteCustomerVO(input);
				vo.setEditFlag(NumberUtil.compareLong(userId, vo.getSegiManId()));
				return vo;
			}
		});
		DataTablesPage page = new DataTablesPage(sEcho, dPage);
		page.setAaData(finalList);
		return page;
	}
	
	/**
	 * @param uniteGuNegoVO
	 * @return
	 */
	@RequestMapping("/saveBasicInfo")
	@ResponseBody
	public Result saveBasicInfo(@ModelAttribute UniteGuNegoVO uniteGuNegoVO){
		UniteGuNego ugn = uniteGuCustomerService.findUniteCustomerById(uniteGuNegoVO.getId());
		BeanUtils.copyProperties(uniteGuNegoVO, ugn,"id","segiMan","segiOrg","segiManId","sysCd");
		
		try {
			if(ugn.getId()!=null){
				uniteGuCustomerService.saveUniteCustomer(ugn);
			}else{
				return new Result(false, "保存失败，缺少主键！", null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, e.getMessage(), null);
		}
		return new Result(true, "保存成功！", null);
	}
	
	/**
	 * 联保体选择所属客户经理列表
	 * @param sEcho
	 * @param firstIndex
	 * @param pageSize
	 * @param request
	 * @return
	 */
	@RequestMapping("/findCustManagerBySearch")
	@ResponseBody
	public DataTablesPage findCustManagerBySearch(@RequestParam("sEcho") Integer sEcho,
			@RequestParam("iDisplayStart") Integer firstIndex,
			@RequestParam("iDisplayLength") Integer pageSize,
			HttpServletRequest request){
		String custManager = request.getParameter("name");
		ShiroUser curUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		Page<Object[]> dPage =  uniteGuCustomerService.findCustManagerBySearch(
				firstIndex / pageSize, pageSize,curUser.getLogOrgid(), custManager);
		List<CustManagerVO> finalList =  Lists.transform(dPage.getContent(), new Function<Object[], CustManagerVO>(){
			@Override
			public CustManagerVO apply(Object[] input) {
				return new CustManagerVO(input);
			}
		});
		DataTablesPage page = new DataTablesPage(sEcho, dPage);
		page.setAaData(finalList);
		return page;
		
	}
	
	/**
	 * 添加联保体成员时的客户列表
	 * @param sEcho
	 * @param firstIndex
	 * @param pageSize
	 * @param request
	 * @return
	 */
	@RequestMapping("/findCustomersBySearch")
	@ResponseBody
	public DataTablesPage findCustomersBySearch(@RequestParam("sEcho") Integer sEcho,
			@RequestParam("iDisplayStart") Integer firstIndex,
			@RequestParam("iDisplayLength") Integer pageSize,
			HttpServletRequest request){
		String unGuId = request.getParameter("unGuId");//联保体编号
		String customerName = request.getParameter("customerName");
		String certificateCd = request.getParameter("certificateCd");
		String certificateNum = request.getParameter("certificateNum");
		ShiroUser curUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		String userId = curUser.getId().toString();
		String queryType = request.getParameter("queryType");//0全部，1企业，2个人
		List<String> params = ImmutableList.of(customerName, certificateCd, certificateNum, queryType ,userId);
		Page<Object[]> dPage =  uniteGuCustomerService.findCustomersBySearch(
				firstIndex / pageSize, pageSize,unGuId, params);
		List<CustomerGuVO> finalList =  Lists.transform(dPage.getContent(), new Function<Object[], CustomerGuVO>(){
			@Override
			public CustomerGuVO apply(Object[] input) {
				return new CustomerGuVO(input);
			}
		});
		DataTablesPage page = new DataTablesPage(sEcho, dPage);
		page.setAaData(finalList);
		return page;
		
	}
	
	/**
	 * 联保体成员列表
	 * @param sEcho
	 * @param firstIndex
	 * @param pageSize
	 * @param request
	 * @return
	 */
	@RequestMapping("/findUniteMembersBySearch")
	@ResponseBody
	public DataTablesPage findUniteMembersBySearch(@RequestParam("sEcho") Integer sEcho,
			@RequestParam("iDisplayStart") Integer firstIndex,
			@RequestParam("iDisplayLength") Integer pageSize,
			HttpServletRequest request){
		ShiroUser curUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		String unGuId = CommonHelper.toStr(request.getParameter("unGuId"));
		Page<Object[]> dPage =  uniteGuCustomerService.findUniteMembersBySearch(
				firstIndex / pageSize + 1, pageSize, unGuId);
		DataTablesPage page = new DataTablesPage(sEcho, dPage);
		return page;
	}
	
	
	/***
	 * 获取联保体信息
	 * @param id 联保体id
	 * @return Result
	 **/
	@RequestMapping("/getUgnInfo/{id}")
	@ResponseBody
	public Result getUgnInfo(@PathVariable String id){
		ShiroUser curUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		UniteGuNegoVO vo = new UniteGuNegoVO();
		UniteGuNego ugn = uniteGuCustomerService.findUniteCustomerById(CommonHelper.toLong(id));
		try {
			BeanUtils.copyProperties(ugn, vo);
			vo.setSegiOrgName(uniteGuCustomerService.getOrgNameByOrgId(vo.getSegiOrg()));
			vo.setSegiMan(curUser.getName());
			if(vo.getSegiDate() == null){
				vo.setSegiDate(new Date());
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, e.getMessage(), null);
		}
		return new Result(true, "获取成功", vo);
	}
	
	
	/**
	 * 根据Id删除联保体客户
	 * 
	 * @param id 所需删除的联保体的Id
	 * @return Result 删除是否成功
	 * 
	 * @author lijing
	 * @lastModified lijing 2014-07-25 9:30:50
	 */
	@RequestMapping("/delUniteCustomer/{id}")
	@ResponseBody
	public Result delUniteCustomer(@PathVariable String id) {
		try {
			ShiroUser curUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
			UniteGuNego ugn = uniteGuCustomerService.findUniteCustomerById(CommonHelper.toLong(id));
			if(ugn == null){
				return failure("所选择的联保体客户已不存在！请刷新页面。");
			}
			List<Long> userIds=Arrays.asList(CommonHelper.toLong(ugn.getManagerId()),ugn.getSegiManId());
			if(!userIds.contains(curUser.getId())){
				return failure("您没有删除此协议的权限！");
			}
			//生效的协议无法删除
			if(dataDict.getCodeValList("UnStatus", "S2").contains(ugn.getUnStatus())){
				return failure("生效的协议无法删除！");
			}
			//业务数据校验
			MsgResult msgResult=uniteGuCustomerService.checkAllBizStatus(CommonHelper.toLong(id));
			if(!msgResult.equalsMsgResult(ResultEnums.SUCCESS)){
				return failure(msgResult.getDesc());
			}
			uniteGuCustomerService.deleteUniteCustomer(ugn);
			return success("删除成功");
		} catch (Exception e) {
			return failure("删除失败");
		}
	}
	/**
	 * 根据Id删除联保体成员
	 * 
	 * @param id 所需删除的联保体的Id
	 * @return Result 删除是否成功
	 * 
	 * @author lijing
	 * @lastModified lijing 2014-07-25 9:30:50
	 */
	@RequestMapping("/delMember/{id}")
	@ResponseBody
	public Result delMember(@PathVariable String id) {
		if(StringUtils.isNotBlank(id)){
			uniteGuCustomerService.deleteUnCustTab(id);
			return new Result(true, "删除成功", null);
		}else{
			return new Result(false, "数据错误，请联系管理员！", null);
		}
	}
	
	
	/**
	 * 根据Id退出联保体成员
	 * 
	 * @param id 所需删除的联保体的Id
	 * @param unGuId 联保体num
	 * @return Result 删除是否成功
	 * 
	 * @author lijing
	 * @lastModified lijing 2014-07-25 9:30:50
	 */
	@RequestMapping("/exitMember")
	@ResponseBody
	public Result exitMember(@RequestParam String id, @RequestParam String unGuId) {
		if(StringUtils.isNotBlank(id)){
			//首先判断是否低于担保成员最低限2人
			List<UnCustTab> unList = uniteGuCustomerService.findUnCustTabList(unGuId);
			if(unList.size()<=2){
				return new Result(false, "联保体成员人数不可低于2人！", null);
			}
			UnCustTab tab = uniteGuCustomerService.findUnCustTabById(id);
			/*//该客户是否有授信未结束的业务
			boolean flag = bizCreditApplicationService.validateCustomerProjectInfo(tab.getPartyId(),dataDict.getCodeVal("BussinessProperty", "S1"), null);
			if(flag){
				return new Result(false, "该客户还有授信未结束的业务，不能进行退出", null);
			}*/
			//该客户是否还有未结束的业务
			boolean _flag = businessApplicationService.validateCustomerProjectInfo(tab.getPartyId(),dataDict.getCodeVal("BussinessProperty", "S1"), null);
			if(_flag){
				return new Result(false, "该客户还有未结束的业务，不能进行退出", null);
			}
			uniteGuCustomerService.deleteUnCustTab(id);
			return new Result(true, "退出成功", null);
		}else{
			return new Result(false, "数据错误，请联系管理员！", null);
		}
	}
	
	/**
	 * 获取联保体编号
	 * 
	 * @return String 联保体编号
	 * 
	 * @author lijing
	 * @lastModified lijing 2014-07-25 9:30:50
	 */
	@RequestMapping("/getGuNum")
	@ResponseBody
	public String getGuNum(){
		return commonBizNumberBS.generateUnGuId();
	}
	
	/**
	 * 联保体简单提交
	 * @return 跳转地址
	 * */
	@RequestMapping("/saveSimple")
	@ResponseBody
	public Result saveSimple(HttpServletRequest request){
		String unGuId = request.getParameter("unGuId");//联保体编号
		String unGroupName = request.getParameter("unGroupName");//联保体名称
		try {
			ShiroUser curUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
			UniteGuNego ugn = new UniteGuNego();
			ugn.setUnGuId(unGuId);
			ugn.setUnGroupName(unGroupName);
			ugn.setCustManager(curUser.getName());
			ugn.setManagerId(curUser.getId().toString());
			ugn.setSegiOrg(curUser.getLogOrgid().toString());
			ugn.setUnStatus(dataDict.getCodeVal(UN_STATUS, "S1"));//预登记
			ugn.setSegiMan(curUser.getName());//登记人
			ugn.setSegiManId(curUser.getId());//登记人Id
			ugn.setSysCd("2");//微贷
			uniteGuCustomerService.saveUniteCustomer(ugn);
			return new Result(true,"保存成功",ugn.getId());
		} catch (Exception e) {
			logger.error("UniteGuNego Save Fail !");
			e.printStackTrace();
			return new Result(false,"保存失败",null);
		}
	}
	
	/**
	 * 保存成员
	 * */
	@RequestMapping("/saveMember")
	@ResponseBody
	@Deprecated
	public Result saveMember(HttpServletRequest request){
		String action = request.getParameter("action");
		String partyId = request.getParameter("meb_customerId");
		String unGuId = request.getParameter("unGuId");
		Party party = uniteGuCustomerService.findPartyByPartyId(partyId);
		if(party==null){
			return new Result(false,"所选客户并不存在！",null);
		}
		if(StringUtils.equals("add", action)){//增加
			List<UnCustTab> unCustList = uniteGuCustomerService.findUnCustTabByPartyId(party.getPartyId(), unGuId);
			if(CollectionUtils.isNotEmpty(unCustList)){
				return new Result(false,"所选客户已被添加！",null);
			}
			UnCustTab tab = new UnCustTab();
			String id = uniteGuCustomerService.findNextSeq();
			tab.setId(id);
			tab.setPartyId(partyId);
			tab.setUnGuId(unGuId);
			tab.setCustomerNum(party.getCustomerNum());
			tab.setCustomerName(party.getPartyName());
			tab.setCertificateNum(party.getCertificateNum());
			tab.setCertificateTypeCd(party.getCertificateTypeCd());
			tab.setCurrency("156");// 人民币
			tab.setStatus(party.getStatus());
			uniteGuCustomerService.saveUnCustTab(tab);
			return new Result(true,"保存成功", null);
		}else if(StringUtils.equals("update", action)){//修改
			String meb_id = request.getParameter("meb_id");
			UnCustTab tab = uniteGuCustomerService.getUnCustTabById(meb_id);
			if(tab==null){
				return new Result(false,"所选客户已不存在！",null);
			}else{
				tab.setUnGuId(unGuId);
				tab.setCustomerNum(party.getCustomerNum());
				tab.setCustomerName(party.getPartyName());
				tab.setCurrency("156");
				tab.setStatus(party.getStatus());
				tab.setCertificateNum(party.getCertificateNum());
				tab.setCertificateTypeCd(party.getCertificateTypeCd());
				tab.setPartyId(partyId);
				uniteGuCustomerService.saveUnCustTab(tab);
				return new Result(true,"保存成功", null);
			}
		}
		return new Result(false,"保存失败", null);
	}
	/**
	 * 批量增加成员
	 * @param ugnId
	 * @param custIds
	 * @return
	 */
	@RequestMapping("/addMembersOfBatch")
	@ResponseBody
	public Result addMembersOfBatch(Long ugnId,String custIds){
		if(ugnId==null||StringUtils.isBlank(custIds)){
			return failure("参数错误");
		}
		try {
			String result=uniteGuCustomerService.addMembersOfBatch(ugnId, CommonHelper.str2SetOfLong(custIds));
			return success(StringUtils.defaultString(result, "保存成功"));
		} catch (Exception e) {
			String msg="保存失败";
			if(e instanceof ServiceException){
				msg=e.getMessage();
			}else{
				e.printStackTrace();
			}
			return failure(msg);
		}
	}
	/**
	 * 协议生效/失效
	 * @param action 生效还是失效 1、3生效 2失效
	 * @param unGuId 联保体编号
	 * */
	@RequestMapping("/changeProtocol")
	@ResponseBody
	public Result changeProtocol(@RequestParam String unGuId, @RequestParam Integer action){
		if(action.compareTo(1)==0||action.compareTo(3)==0){
			//生效
			return this.changeEnable(unGuId);
		}else if(action.compareTo(2)==0){
			//失效
			uniteGuCustomerService.updateUniteStatus(unGuId, 2);
			return new Result(true,"操作成功", null);
		}else{
			return new Result(false,"操作失败，参数错误", null);
		}
	}
	
	
	/**
	 * 生效
	 * */
	public Result changeEnable(String unGuId){
		UniteGuNego un = uniteGuCustomerService.findUniteCustomerByUnGuId(unGuId);
		Long memberNum = CommonHelper.toLong(un.getCustomerQuantity(),0L);//默认为0
		if(memberNum==null||memberNum<2){
			return new Result(false,"联保体成员至少为有两名！", null);
		}
		String docNum = uniteGuCustomerService.findDocumentCount(unGuId);
		if(StringUtils.isBlank(docNum)){
			return new Result(false,"上传的文档数量与联保体成员数量不等！", null);
		}
		Long docNumL = new Long(docNum);
		if(docNumL.compareTo(memberNum)<0){
			return new Result(false,"上传的文档数量与联保体成员数量不等！", null);
		}
		List<UnCustTab> unList = uniteGuCustomerService.findUnCustTabList(unGuId);
		Long memNum = Long.valueOf(unList.size());
		if(memNum.compareTo(memberNum)!=0){
			return new Result(false,"联保体成员实际数量与所填写的不一致！", null);
		}
		uniteGuCustomerService.updateUniteStatus(unGuId, 1);
		return new Result(true,"生效成功", null);
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
	public DataTablesPage findCustomerDocumentsBySearch(
			@RequestParam("sEcho") Integer sEcho,
			@RequestParam("iDisplayStart") Integer firstIndex,
			@RequestParam("iDisplayLength") Integer pageSize,
			HttpServletRequest request) {
		// 查询条件
		String unGuId = (String) request.getParameter("unGuId");
		String documentName = request.getParameter("query_documentName");
		String contentType = request.getParameter("query_contentType");
		List<Object> params = new ArrayList<Object>();
		// String customerName =
		// uniqueCustomerService.findPartyByPartyId(partyId).getPartyName();
		params.add("('01')");
		params.add(unGuId);
		params.add(documentName);
		if (StringUtils.isNotBlank(contentType)) {
			params.add(contentType);
		} else {
			params.add("");
		}

		Page<Object[]> dPage = documentService.findDocumentsInUnite(
				(firstIndex / pageSize) + 1, pageSize, params);
		List<DocumentUniteVO> finalList =  Lists.transform(dPage.getContent(), new Function<Object[], DocumentUniteVO>(){
			@Override
			public DocumentUniteVO apply(Object[] input) {
				return new DocumentUniteVO(input);
			}
		});
		DataTablesPage page = new DataTablesPage(sEcho, dPage);
		page.setAaData(finalList);
		return page;
	}
	
	/**TODO
	 * 新增文档前的参数获取
	 * 
	 * @param unGuId
	 *            联保体Id
	 * @param request
	 *            HttpServletRequest
	 * @return DocumentUploadVO 对象
	 * @author lijing
	 * @lastModified lijing 2014-08-18 9:30:50
	 */
	@RequestMapping("/beforeUpdate")
	@ResponseBody
	public DocumentUploadVO beforeUpdate(@RequestParam String unGuId,
			HttpServletRequest request) {
		DocumentUploadVO documentUploadVO = new DocumentUploadVO();
		UniteGuNego ugn = uniteGuCustomerService.findUniteCustomerByUnGuId(unGuId);
		documentUploadVO.setPartyId(ugn.getId().toString());
		ShiroUser curUser = (ShiroUser) SecurityUtils.getSubject()
				.getPrincipal();
		documentUploadVO.setUserNum(curUser.getUsernum());
		documentUploadVO.setCreateOrgCd(curUser.getLogOrgid().toString());//当前登录机构
		documentUploadVO.setCreateDateTime(CommonHelper.getNowStr(CommonHelper.DF_DATE));
		documentUploadVO.setCustomerNum(ugn.getUnGuId());
		documentUploadVO.setBizNum("");
		documentUploadVO.setBizId("-1");
		documentUploadVO.setDocumentType("01");
		documentUploadVO.setCreateUserName(curUser.getName());
		documentUploadVO.setFileTypes("doc,docx,xls,xlsx,pdf,jpg,gif,png,rar");
		documentUploadVO.setCreateUserNum(curUser.getId().toString());
		//documentUploadVO.setCreateUserNum(curUser.getId().toString());
		documentUploadVO.setCreateTypeCd("01");
		documentUploadVO.setDocumentNum(commonBizNumberBS.generateDocumentNum(
				ugn.getUnGuId(), "01"));
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
	public List<Object[]> findDocumentCustDocTypes(@RequestParam String unGuId) {
		UniteGuNego ugn = uniteGuCustomerService.findUniteCustomerByUnGuId(unGuId);
		String custDocTypeCd = DocStageCode.LBT_DOC.getCodeId();
		return documentService.findDocumentCustDocTypes(null, ugn.getId(),
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
	public CustDocInfo findUploadCustDocTypes(@RequestParam String unGuId) {
		UniteGuNego ugn = uniteGuCustomerService.findUniteCustomerByUnGuId(unGuId);
		String custDocTypeCd = "";
		custDocTypeCd = DocStageCode.LBT_DOC.getCodeId();
		return documentService.findUploadCustDocTypes(null, ugn.getId(),
				custDocTypeCd);
	}
	/**
	 * @param ugnId
	 * @return
	 */
	@RequestMapping("/checkAllBizStatus/{ugnId}")
	@ResponseBody
	public Result checkAllBizStatus(@PathVariable("ugnId")Long ugnId){
		//业务数据校验
		MsgResult msgResult=uniteGuCustomerService.checkAllBizStatus(ugnId);
		if(!msgResult.equalsMsgResult(ResultEnums.SUCCESS)){
			return failure(msgResult.getDesc());
		}
		return success();
	}
}
