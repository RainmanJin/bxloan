package com.coamctech.bxloan.web.controller.contractmng;
//package com.coamctech.bxloan.web.controller.contractMng;
//
//import static com.coamctech.bxloan.commons.GlobalConstants.CONTRACT;
//import static com.coamctech.bxloan.commons.GlobalConstants.FORM_CONTRACT;
//import static com.coamctech.bxloan.commons.GlobalConstants.INIT_CONTRACT;
//
//import java.math.BigDecimal;
//import java.sql.Timestamp;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//
//import net.sf.ehcache.hibernate.management.impl.BeanUtils;
//
//import org.apache.commons.lang3.StringUtils;
//import org.apache.shiro.SecurityUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//import com.coamctech.bxloan.commons.result.DataTablesPage;
//import com.coamctech.bxloan.commons.utils.DateTools;
//import com.coamctech.bxloan.commons.utils.MathUtil;
//import com.coamctech.bxloan.entity.BizRate;
//import com.coamctech.bxloan.entity.Contract;
//import com.coamctech.bxloan.entity.CustomerAccountManagent;
//import com.coamctech.bxloan.entity.DocumentIndex;
//import com.coamctech.bxloan.entity.Individual;
//import com.coamctech.bxloan.entity.Party;
//import com.coamctech.bxloan.entity.ProjectApplication;
//import com.coamctech.bxloan.service.bizapply.BusinessApplicationService;
//import com.coamctech.bxloan.service.contractmng.contractMngService;
//import com.coamctech.bxloan.service.customermng.UniqueCustomerService;
//import com.coamctech.bxloan.service.sysmng.ICommonBizNumberBS;
//import com.coamctech.bxloan.web.security.ShiroUser;
//import com.coamctech.bxloan.web.vo.contractmng.ContractSaveVO;
//import com.coamctech.bxloan.web.vo.contractmng.ContractVO;
//import com.coamctech.bxloan.web.vo.customermng.DocumentUploadVO;
//import com.coamctech.bxloan.web.vo.customermng.IndividualVO;
//
///**
// * 合同管理控制器
// *
// * @author lijing
// * @lastModified lijing 2014-08-13 17:30:24
// */
//
//@Controller
//@RequestMapping("/" + CONTRACT)
//public class CopyOfcontractMngController
//{
//	
//    @Autowired
//    private contractMngService contractMngService;
//    @Autowired
//	private UniqueCustomerService uniqueCustomerService;
//    @Autowired
//    private BusinessApplicationService businessApplicationService;
//    @Autowired
//    private ICommonBizNumberBS commonBizNumberBS;
//
//
//    /***********************************跳转方法*******************************************/
//
//    /**
//     * sideBar上点击合同管理的跳转方法
//     *
//     *
//     * @return String 跳转路径
//     *
//     * @author lijing
//     * @lastModified lijing 2014-08-13 16:37:51
//    */
//    @RequestMapping
//    public String init()
//    {
//        return INIT_CONTRACT;
//    }
//
//    /**
//     * 在跳转合同Form页面之前完成值传递的方法
//     *
//     * @param request HttpServletRequest的对象
//     * @param projectId 业务id
//     * @return String 跳转路径
//     *
//     * @author lijing
//     * @lastModified lijing 2014-07-31 9:37:51
//    */
//    @RequestMapping("/signToForm")
//    @ResponseBody
//    public String signToForm(@RequestParam String projectId, HttpServletRequest request)
//    {
//        return CONTRACT + "/perCondition?projectId="+projectId;
//    }
//
//    /**
//     * 跳转到Form页面之前进行合同记录的插入
//     *
//     * @param request HttpServletRequest的对象
//     *
//     * @return String 跳转路径
//     *
//     * @author lijing
//     * @lastModified lijing 2014-07-31 9:37:51
//    */
//    @RequestMapping("/perCondition")
//    public String perCondition(@RequestParam String projectId, HttpServletRequest request)
//    {
//        request.setAttribute("projectId", projectId);
//        Contract oldContract = contractMngService.findContractByProjectId(Long.valueOf(projectId));
//        ProjectApplication project = contractMngService.getProject(Long.valueOf(projectId));
//        request.setAttribute("partyId", project.getPartyId());
//        if(oldContract == null)
//        {
//
//            //创建合同
//            Contract contract = new Contract();
//            ShiroUser curUser = (ShiroUser)SecurityUtils.getSubject().getPrincipal();
//
//            //生成合同编号
//            String contractNum ;
//            if("1".equals(project.getBusinessType()))  //个贷
//            {
//                contractNum = commonBizNumberBS.generateContractNumber(curUser.getOrgid().toString(), "1", "借", 1);
//                contract.setContractNum(contractNum);
//            }
//            else if("2".equals(project.getBusinessType()))   //企贷
//            {
//                contractNum = commonBizNumberBS.generateContractNumber(curUser.getOrgid().toString(), "0", "借", 1);
//                contract.setContractNum(contractNum);
//            }
//            //projectId的插入
//            contract.setProjectId(Long.valueOf(projectId));
//            //设置展期笔数
//            contract.setContractIndex(new Long(0));
//            //设置projectNo
//            contract.setProjectNo(project.getProjectNo());
//            //设置用户名，用户编号
//            contract.setCustomerName(project.getCustomerName());
//            contract.setCustomerNum(project.getCustomerNum());
//            //设置可用金额
//            contract.setContractAvailableAmt(project.getApplyAmt());
//            //合同期限和期限单位
//            if(project.getTerm() != null)
//            {
//                contract.setContractTerm(project.getTerm().intValue());
//                contract.setContractTermTotal(project.getTerm().intValue());
//            }
//            contract.setContractTermUnit(project.getTermUnit());
//            contract.setContractTermUnitTotal(project.getTermUnit());
//            //设置发起者
//            contract.setApplyUserNum(curUser.getId().toString());
//            //设置贷款产品
//            contract.setProductType(project.getProductType());
//            //设置检查状态
//            contract.setCheckStatus("1");
//            //contract.setApplyDeptId(project.);
//            contract.setCurrency(project.getCurrency());
//            contract.setPreRepaymentRate(project.getPreRepaymentRate());
//
//            String applyOrg = project.getApplyOrg();
//            if(StringUtils.isNotBlank(applyOrg) && !"null".equals(applyOrg))
//            {
//                contract.setApplyOrgId(Long.valueOf(applyOrg));
//            }
//            //设置客户Id
//            contract.setPartyId(project.getPartyId());
//            //设置批复还款方式
//            contract.setRepayModeCd(project.getApproveRepayingMode());
//            if(project.getReplyPeriodNum() != null)
//            {
//                contract.setRepayPrincipalMonthes(project.getReplyPeriodNum().intValue());
//            }
//
//            //设置合同性质
//            if(project.getBusinessProcessType() != null)
//            {
//                contract.setContractNatureCd(project.getBusinessProcessType());
//            }
//            //设置时间属性
//            contract.setSysCreateDate(new Timestamp(DateTools.stringToDate(DateTools.getCurrentDate("yyyy-MM-dd"), "yyyy-MM-dd").getTime()));
//            contract.setSysUpdateDate(new Timestamp(DateTools.stringToDate(DateTools.getCurrentDate("yyyy-MM-dd"), "yyyy-MM-dd").getTime()));
//
//            BizRate bizRate = contractMngService.findBizRateByProjectId(project.getProjectId());
//            //审批后的贷款年利率
//            contract.setContractRate(bizRate.getApproveRateValue());
//            //逾期加减值
//            Double ovdueIrNegoRate = bizRate.getOvdueIrNegoRate();
//            ovdueIrNegoRate = MathUtil.BDadd(1, ovdueIrNegoRate, 6);
//            //逾期利率=贷款利率*(1+逾期加减值)
//            contract.setContractOvdueRate(MathUtil.BDmultiply(contract.getContractRate(), ovdueIrNegoRate, 6)); //逾期贷款利率
//
//            Long contractId = contractMngService.saveContract(contract);
//            request.setAttribute("contractId", contractId);
//            request.setAttribute("projectId", projectId);
//        }
//        else
//        {
//            request.setAttribute("contractId", oldContract.getContractId());
//            request.setAttribute("projectId", projectId);
//        }
//        return FORM_CONTRACT;
//    }
//
//
//
//
//
//    /***********************************合同表的相关方法*******************************************/
//
//    /**
//     * 合同表的查询方法
//     *
//     * @param request HttpServletRequest的对象
//     * @param sEcho datatables的被请求次数
//     * @param firstIndex 起始页数
//     * @param pageSize 每页多少条记录
//     * @return page DataTablesPage对象的实例
//     *
//     * @author lijing
//     * @lastModified lijing 2014-08-23 15:30:50
//    */
//    @RequestMapping("/findBySearch")
//    @ResponseBody
//    public DataTablesPage findBySearch(@RequestParam("sEcho") Integer sEcho,
//                                       @RequestParam("iDisplayStart") Integer firstIndex,
//                                       @RequestParam("iDisplayLength") Integer pageSize,
//                                       HttpServletRequest request)
//    {
//        //查询条件
//        String customerName = request.getParameter("customerName");
//        String contractNum = request.getParameter("contractNum");
//        String contractStatusCd = request.getParameter("contractStatusCd");
//        List<Object> params = new ArrayList<Object>();
//        HttpSession session = request.getSession();
//        ShiroUser curUser = (ShiroUser)SecurityUtils.getSubject().getPrincipal();
//        params.add(customerName);
//        params.add(contractNum);
//        params.add(contractStatusCd);
//        params.add(curUser.getId().toString());
//        Page queryList = contractMngService.findContractBySearch(
//                             firstIndex / pageSize + 1, pageSize, params);
//        DataTablesPage page = new DataTablesPage(sEcho, queryList);
//        return page;
//    }
//
//    /**
//     * 查出合同信息
//     *
//     * @param projectId 业务id
//     * @param contractId 合同id
//     * @return contractVO 合同vo
//     *
//     * @author lijing
//     * @lastModified lijing 2014-08-14 9:37:51
//    */
//    @RequestMapping("/getContractInfo")
//    @ResponseBody
//    public ContractVO getContractInfo(@RequestParam("projectId") String projectId, @RequestParam("contractId") String contractId)
//    {
//        BizRate bizRate = contractMngService.findBizRateByProjectId(Long.valueOf(projectId));
//        Contract contract = contractMngService.findContractByProjectId(Long.valueOf(projectId));
//        ContractVO contractVO = new ContractVO();
//        org.springframework.beans.BeanUtils.copyProperties(contract, contractVO);
//        org.springframework.beans.BeanUtils.copyProperties(bizRate, contractVO);
//        contractVO.setArrangeRepayDate(timeStampToString(contract.getArrangeRepayDate()));
//        if("1".equals(contractVO.getApproveIrTypeCd()))
//        {
//            contractVO.setApproveIrTypeCd("固定利率");
//        }
//
//        return contractVO;
//
//    }
//
//    /**
//     * 查出客户的账户信息
//     *
//     * @param partyId 客户id
//     * @return List 账户信息list
//     *
//     * @author lijing
//     * @lastModified lijing 2014-08-14 10:37:51
//    */
//    @RequestMapping("/getCustomerAccount")
//    @ResponseBody
//    public List<CustomerAccountManagent> getCustomerAccount(@RequestParam("partyId") String partyId)
//    {
//        return contractMngService.findAccountsByPartyId(Long.valueOf(partyId));
//    }
//
//
//    /**
//     * 根据账户id查询账号
//     *
//     * @param partyId 客户id
//     * @return List 账户信息list
//     *
//     * @author lijing
//     * @lastModified lijing 2014-08-14 10:37:51
//    */
//    @RequestMapping("/getAccountNum")
//    @ResponseBody
//    public String getAccountNum(@RequestParam("accountId") String accountId)
//    {
//        return contractMngService.findAccountNumByAccountId(Long.valueOf(accountId));
//    }
//
//
//    /**
//     * 费用列表（）的查询方法
//     *
//     * @param request HttpServletRequest的对象
//     * @param sEcho datatables的被请求次数
//     * @param firstIndex 起始页数
//     * @param pageSize 每页多少条记录
//     * @return page DataTablesPage对象的实例
//     *
//     * @author lijing
//     * @lastModified lijing 2014-07-25 9:30:50
//    */
//    @RequestMapping("/findExpenseBySearch")
//    @ResponseBody
//    public DataTablesPage findExpenseBySearch(
//        @RequestParam("sEcho") Integer sEcho,
//        @RequestParam("iDisplayStart") Integer firstIndex,
//        @RequestParam("iDisplayLength") Integer pageSize,
//        HttpServletRequest request)
//    {
//        // 查询条件
//        String projectId = (String) request.getParameter("projectId");
//        List<Object> params = new ArrayList<Object>();
//        Page queryList = contractMngService.findExpenseBySearch(
//                             projectId, firstIndex / pageSize + 1, pageSize, params);
//        DataTablesPage page = new DataTablesPage(sEcho, queryList);
//        return page;
//
//    }
//
//
//    /**
//     * 根据合同Id修改合同
//     *
//     * @param ContractSaveVO 前台传来的表单对象，注：接收的不是实体而是VO
//     * @param contractId 合同Id
//     * @return String 修改是否成功
//     *
//     * @author lijing
//     * @lastModified lijing 2014-07-25 9:30:50
//     * 注：这里用VO是为了防止更新时覆盖掉表单中没有的字段数据
//    */
//    @RequestMapping("/saveContractInfo")
//    @ResponseBody
//    public String saveContractInfo(@ModelAttribute ContractSaveVO contractSaveVO,
//                                   @RequestParam String contractId)
//    {
//        // Individual motoIndividual = customerIndividualService.get(partyId);
//        Contract oldContract = contractMngService.getContract(Long.valueOf(contractId));
//        oldContract.setBankName(contractSaveVO.getBankName());
//        if(contractSaveVO.getLoanDateStyle().equals("1")){        	
//        	oldContract.setArrangeRepayDate(stringToTimestamp(contractSaveVO.getArrangeRepayDate()));
//        }
//        oldContract.setLoanDateStyle(contractSaveVO.getLoanDateStyle());
//        contractMngService.saveContract(oldContract);
//        BizRate bizRate = contractMngService.findBizRateByProjectId(oldContract.getProjectId());
//        bizRate.setFinalAdjustPeriod("1");
//        bizRate.setFinalRateValue(Double.valueOf(contractSaveVO.getFinalRateValue()));
//        bizRate.setRateAdjustmentReason(contractSaveVO.getRateAdjustmentReason());
//        contractMngService.saveBizRate(bizRate);
//        return "success";
//    }
//
//    /**
//     * 根据合同Id保存意见
//     *
//     * @param ContractSaveVO 前台传来的表单对象，注：接收的不是实体而是VO
//     * @param contractId 合同Id
//     * @return String 修改是否成功
//     *
//     * @author lijing
//     * @lastModified lijing 2014-07-25 9:30:50
//     * 注：这里用VO是为了防止更新时覆盖掉表单中没有的字段数据
//    */
//    @RequestMapping("/saveSuggest")
//    @ResponseBody
//    public String saveSuggest(@ModelAttribute ContractSaveVO contractSaveVO,
//                              @RequestParam String contractId)
//    {
//        Contract oldContract = contractMngService.getContract(Long.valueOf(contractId));
//        oldContract.setFulfillInstructionCd(contractSaveVO.getFulfillInstructionCd());
//        contractMngService.saveContract(oldContract);
//        return "success";
//    }
//    
//    /**
//     * 提交合同，更改合同状态
//     *
//     * @param contractId 合同Id
//     * @return String 修改是否成功
//     *
//     * @author lijing
//     * @lastModified lijing 2014-08-15 13:30:50
//    */
//    @RequestMapping("/submitContract")
//    @ResponseBody
//    public String submitContract(@RequestParam String contractId) {
//    	Contract contract = contractMngService.getContract(Long.valueOf(contractId));
//    	if(StringUtils.isBlank(contract.getBankName()))
//    	{
//    		return "bankNameEmptyError";
//    	}else{
//    		contract.setContractStatusCd("300");
//    		contractMngService.saveContract(contract);
//    		return "success";
//    	}
//    }
//    
//    
//    /**
//	 * 文档（DocumentIndex）的查询方法
//	 * 
//	 * @param request
//	 *            HttpServletRequest的对象
//	 * @param sEcho
//	 *            datatables的被请求次数
//	 * @param firstIndex
//	 *            起始页数
//	 * @param pageSize
//	 *            每页多少条记录
//	 * @return page DataTablesPage对象的实例
//	 * 
//	 * @author lijing
//	 * @lastModified lijing 2014-08-20 11:30:50
//	 */
//	@RequestMapping("/findDocuments")
//	@ResponseBody
//	public DataTablesPage findCustomerDocumentsBySearch(
//			@RequestParam("sEcho") Integer sEcho,
//			@RequestParam("iDisplayStart") Integer firstIndex,
//			@RequestParam("iDisplayLength") Integer pageSize,
//			HttpServletRequest request) {
//		// 查询条件
//		String partyId = (String) request.getParameter("partyId");
//		String projectId = (String) request.getParameter("projectId");
//		String documentName = request.getParameter("query_documentName");
//		List<Object> params = new ArrayList<Object>();
//		String customerName = uniqueCustomerService.getIndividual(partyId)
//				.getCustomerName();
//		ShiroUser curUser = (ShiroUser) SecurityUtils.getSubject()
//				.getPrincipal();
//		params.add(partyId);
//		params.add(documentName);
//		List<Object[]> finalList = new ArrayList<Object[]>();
//
//		Page queryList = uniqueCustomerService.findDocumentIndexBySearch(
//				firstIndex / pageSize + 1, pageSize, params);
//		List<Object[]> resultList = queryList.getContent();
//		for (Object[] obj : resultList) {
//			obj[3] = customerName;
//			obj[4] = curUser.getName();
//			obj[5] = timeStampToString((Timestamp) obj[5]);
//		}
//		DataTablesPage page = new DataTablesPage(sEcho, queryList);
//		return page;
//	}
//    
//    
//    /**
//	 * 新增文档前的参数获取
//	 * 
//	 * @param partyId
//	 *            客户Id
//	 * @param request
//	 *            HttpServletRequest
//	 * @return DocumentUploadVO 对象
//	 * @author lijing
//	 * @lastModified lijing 2014-08-18 9:30:50
//	 */
//	@RequestMapping("/beforeUpdate")
//	@ResponseBody
//	public DocumentUploadVO beforeUpdate(@RequestParam String partyId,
//			@RequestParam String projectId,@RequestParam String uploadType, HttpServletRequest request) {
//		DocumentUploadVO documentUploadVO = new DocumentUploadVO();
//		documentUploadVO.setPartyId(partyId);
//		ShiroUser curUser = (ShiroUser) SecurityUtils.getSubject()
//				.getPrincipal();
//		Individual individual = uniqueCustomerService.getIndividual(partyId);
//		ProjectApplication project = businessApplicationService
//				.searchProjectApplication(Long.valueOf(projectId));
//		documentUploadVO.setUserNum(curUser.getId().toString());
//		documentUploadVO.setCreateOrgCd(curUser.getOrgid().toString());
//		documentUploadVO.setCreateDateTime(timeStampToString(new Timestamp(
//				new Date().getTime())));
//		documentUploadVO.setCustomerNum(individual.getCustomerNum());
//		documentUploadVO.setBizNum(project.getProjectNo());
//		documentUploadVO.setBizId(projectId);
//		if(uploadType.equals("documents")){
//			documentUploadVO.setDocumentType("05");
//		}else if(uploadType.equals("loanApply")){
//			documentUploadVO.setDocumentType("27");
//		}else{
//			
//		}
//		documentUploadVO.setCreateUserName(curUser.getName());
//		documentUploadVO.setFileTypes("doc,docx,xls,xlsx,pdf,jpg,gif,png,rar");
//		documentUploadVO.setCreateUserNum(curUser.getUsernum());
//		documentUploadVO.setCreateTypeCd("01");
//		documentUploadVO.setDocumentNum(commonBizNumberBS.generateDocumentNum(
//				individual.getCustomerNum(), "01"));
//		return documentUploadVO;
//	}
//    
//    /**
//	 * 查找下载地址与文件名
//	 * 
//	 * @param documentId
//	 *            文档Id
//	 * @return List 对象
//	 * @author lijing
//	 * @lastModified lijing 2014-08-19 17:30:50
//	 */
//	@RequestMapping("/findDocumentPath")
//	@ResponseBody
//	public List<String> findDocumentPath(@RequestParam String documentId) {
//		DocumentIndex documentIndex = uniqueCustomerService
//				.findDocumentIndexByDocumentId(documentId);
//		List<String> list = new ArrayList<String>();
//		list.add(documentIndex.getDocumentRoute());
//		list.add(documentIndex.getDocumentName());
//		return list;
//	}
//    
//    
//    /**
//     * Timestamp转化成String的方法
//     * @param st Timestamp类型的变量
//     * @return String 返回时间转换成的字符串
//     *
//     * @author lijing
//     * @lastModified lijing 2014-07-25 9:30:50
//    */
//    public String timeStampToString(Timestamp st)
//    {
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 定义格式，不显示毫秒
//        String str = "";
//        if (st != null)
//        {
//            str = df.format(st);
//        }
//        else
//        {
//            str = df.format(new Date());
//        }
//        return str;
//    }
//    
//    /**
//     * String转化成Timestamp的方法
//     * @param time String类型的变量
//     * @return Timestamp 返回Timestamp对象,格式yyyy-MM-dd
//     *
//     * @author lijing
//     * @lastModified lijing 2014-07-25 9:30:50
//    */
//    public Timestamp stringToTimestamp(String time) 
//    {
//    	DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//    	  format.setLenient(false);
//    	  Timestamp ts = null;
//    	  try {
//    	   ts = new Timestamp(format.parse(time).getTime());
//    	  } catch (Exception e) {
//    	   e.printStackTrace();
//    	  }
//		return ts;	
//    }
//}
