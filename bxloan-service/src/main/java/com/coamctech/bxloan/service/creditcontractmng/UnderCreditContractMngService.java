package com.coamctech.bxloan.service.creditcontractmng;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Page;

import com.coamctech.bxloan.commons.result.Result;
import com.coamctech.bxloan.entity.Party;
import com.coamctech.bxloan.entity.SubContract;
import com.coamctech.bxloan.entity.credit.CreditContract;
import com.coamctech.bxloan.service.model.SessionUser;
import com.coamctech.bxloan.service.model.approval.BizApprovalOfWfParam;
import com.coamctech.bxloan.service.model.bizapply.BusinessApplicationWdVO;

public interface UnderCreditContractMngService {
	
	/** ***********授信下借款申请所用方法************ */
	/**
	 * 查询费用列表
	 * 
	 * @param underProjectNo
	 * @param projectNo
	 * @param pageNumber
	 * @param pageSize
	 * @param params
	 * @return
	 */
	Page<Object[]> findUnderCreditExpenseRateByProject(String underProjectNo,String projectNo, 
			Integer pageNumber, Integer pageSize, List<Object> params);
	/**
	 * 查询保证人关联表
	 * 
	 * @param pageNumber
	 * @param pageSize
	 * @param projectId
	 * @return
	 */
	Page<Object[]> searchBailList(Integer pageNumber, Integer pageSize,
			Long creditProjectId, Long underProjectId);
	/**
	 * 查询共同借款人关联表
	 * 
	 * @param pageNumber
	 * @param pageSize
	 * @param projectId
	 * @return
	 */
	Page<Object[]> searchCommonBorrowerList(Integer pageNumber,
			Integer pageSize, Long creditProjectId, Long underProjectId);
	/**
	 * 查找文档关联表
	 * @param i
	 * @param pageSize
	 * @param params
	 * @return
	 */
	Page findDocumentIndexBySearch(int i, Integer pageSize, List<Object> params);
	/***
	 *授信下借款申请查询抵质押相关信息
	 * 
	 * @param 
	 * @return 
	 * @author 
	 * @date 
	 */
	Page<Object[]> searchUCProjectPawnInfoList(Integer pageNumber,
			Integer pageSize, Long creditProjectId,Long underProjectId);
	/**
	 * 根据projectId获取抵质押物列表
	 * 签订合同环节显示抵质押物列表
	 */
	Page findCollateralsAllBySearch(Long businessId,Long parentProjectId , Integer pageNumber,Integer pageSize);
	/**
	 * 保存授信下借款业务信息
	 * @param form
	 * @return
	 */
	public Long saveUnderCreditLoanBusiness(BusinessApplicationWdVO form);
	
	public void checkBusinessInfoBeforeSendProcess(Long projectId,String guaranteeMode, String workFlowCode)throws Exception; 
	/** 
	 * 保存从合同信息
	 * 
	 * @author:
	 * @createDate: 2015年7月13日
	 * @param subcontract
	 * @return
	 */
	public void saveSubContract(SubContract subcontract);
	/**
	 * 根据抵质押信息生成抵质押从合同信息(授信下借款用)
	 * 
	 * @author: lp
	 * @createDate: 2015年8月13日
	 * @return 
	 */
	public void copyPawnInfoToSubContract(Long projectId,Long parentProjectId, SessionUser sessionUser);
	/**
	 * 根据保证人信息生成保证人从合同信息(授信下借款用)
	 * @author:lp
	 * @createDate: 2015年8月13日
	 * @return 
	 */
	public void copyAssureInfoToSubContract(Long projectId,Long parentProjectId, SessionUser sessionUser);

	/**
	 * 提交合同信息到工作流
	 * 
	 * @param workFlowType
	 * @param workflowId
	 * @param taskId
	 * @param userId
	 * @param nextUser
	 * @param comments
	 * */
	Result submitWorkFlow(Long projectId, String workFlowType,
			String workflowId, String taskId, String userId, String nextUser,
			String nextUserOrgId, String comments);
	/**
	 * 更新授信剩余可用金额
	 * 
	 * @author: lp
	 * @createDate: 2015年8.3
	 * @param projectId 业务ID
	 * @return  
	 */
	public void updateCreditAvaiableAmt(Long projectId,BigDecimal amount);
	
	/** 
	 * 保存授信借款基本项目信息前进行校验
	 * 
	 * @param creditContractId 授信合同ID
	 * @param form 
	 * @return
	 */
	public String checkBusinessInfoBeforeSave(Long creditContractId, BusinessApplicationWdVO form);
}

