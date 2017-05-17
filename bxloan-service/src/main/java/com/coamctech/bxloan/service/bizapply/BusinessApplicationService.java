package com.coamctech.bxloan.service.bizapply;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.ui.Model;

import com.coamctech.bxloan.commons.result.Result;
import com.coamctech.bxloan.entity.ApprovalHistoryRepayPlan;
import com.coamctech.bxloan.entity.BizExpenseRate;
import com.coamctech.bxloan.entity.BizRate;
import com.coamctech.bxloan.entity.CommonBorrow;
import com.coamctech.bxloan.entity.CustomerManagerTeam;
import com.coamctech.bxloan.entity.FamilyAssetsDetail;
import com.coamctech.bxloan.entity.FamilyFriend;
import com.coamctech.bxloan.entity.Individual;
import com.coamctech.bxloan.entity.InsuranceCompany;
import com.coamctech.bxloan.entity.MoneyRate;
import com.coamctech.bxloan.entity.MonthDominateIncludeMeasure;
import com.coamctech.bxloan.entity.Party;
import com.coamctech.bxloan.entity.Product;
import com.coamctech.bxloan.entity.ProductConfig;
import com.coamctech.bxloan.entity.ProductPrice;
import com.coamctech.bxloan.entity.ProjectApplication;
import com.coamctech.bxloan.entity.ProjectAssurerInfo;
import com.coamctech.bxloan.entity.ProjectPawnInfo;
import com.coamctech.bxloan.entity.QuotaMeasure;
import com.coamctech.bxloan.entity.RepayPlanTemp;
import com.coamctech.bxloan.entity.SalaBusiCustFinalcial;
import com.coamctech.bxloan.entity.WorkDraft;
import com.coamctech.bxloan.service.enums.DataAuthorityType;
import com.coamctech.bxloan.service.model.bizapply.BusinessApplicationWdVO;
import com.coamctech.bxloan.service.model.bizapply.NewCommonBorrowerVO;
import com.coamctech.bxloan.service.model.bizapply.QuotaMeasureVO;
import com.coamctech.bxloan.service.model.bizapply.WorkDraftVO;
import com.coamctech.bxloan.service.model.collateral.CollateralVO;
import com.coamctech.bxloan.service.model.workflow.TypedResult;
import com.coamctech.bxloan.service.model.workflow.WorkFlowTaskIds;
import com.coamctech.bxloan.service.workflow.WorkFlowService.ActionCode;
import com.coamctech.bxloan.service.workflow.WorkFlowService.WorkFlowCode;
import com.coamctech.bxloan.service.workflow.WorkFlowService.WorkFlowNode;

public interface BusinessApplicationService {

	/**
	 * 差查询ProjectApplication信息
	 * 
	 * @param customerNum
	 * @return
	 */
	ProjectApplication searchProjectApplication(Long id);

	/**
	 * Description 保存工作底稿
	 * 
	 * @return ProductPrice
	 */
	WorkDraft saveWorkDraft(WorkDraftVO form);

	/**
	 * Description 保存基本项目信息
	 * 
	 * @return ProductPrice
	 */
	void saveProjectApplication(ProjectApplication from);

	/**
	 * 通过projectId获得对应利率表信息
	 * 
	 * @param projectId
	 * @return
	 */
	BizRate getBizRateByProjectId(String projectId);

	/**
	 * Description 保存新增费用信息
	 * 
	 * @return ProductPrice
	 */
	void saveBizExpenseRate(BizExpenseRate from);

	/**
	 * Description 修改新增费用
	 * 
	 * @return ProductPrice
	 */
	void updateBizExpenseRate(BizExpenseRate form);

	/**
	 * 删除费用新增
	 * 
	 * @param projectId
	 */
	void deleteBizExpenseRate(Long id);

	/**
	 * 查询费用列表
	 * 
	 * @param projectNo
	 * @param i
	 * @param pageSize
	 * @param params
	 * @return
	 */
	Page findBizExpenseRateByProjectNo(String projectNo, int i,
			Integer pageSize, List<Object> params);

	Page<Object[]> findBizExpenseRateByProjectNo2(String projectNo,
			Integer pageNumber, Integer pageSize, List<Object> params);

	/**
	 * 提交意见
	 * 
	 * @param opinion
	 * @param opinionId
	 * @param projectId
	 */
	void submitOpinion(String opinion, Long opinionId, Long projectId);

	/**
	 * 查询工作底稿信息
	 * 
	 * @param partyId
	 * @return
	 */
	WorkDraft searchWorkDraft(Long projectId);

	/**
	 * 保存额度预算
	 * 
	 * @param form
	 */
	QuotaMeasure saveQuotaMeasure(QuotaMeasureVO form) throws Exception;

	/**
	 * 功能：组装费用列表
	 * 
	 */
	void addBizExpenseRate(ProjectApplication pa);

	/**
	 * 申请人发起业务时先检查此客户是否有正在处理中的业务
	 * 
	 * @param partyId
	 * @return
	 */
	boolean checkStatus(Long partyId);

	/**
	 * 申请人发起业务时先检查此客户是否有正在处理中的业务
	 * 
	 * @param partyId
	 * @param projectStatus
	 * @return
	 */
	boolean checkState(Long partyId, String projectStatus);

	/**
	 * 检查客户是否有正在处理中的授薪业务
	 * 
	 * @param partyId
	 * @return
	 */
	boolean checkCreditAppStatus(Long partyId);

	/**
	 * 发起业务时判断此客户经理是否可以对此客户发起业务
	 * 
	 * @param partyId
	 * @param userNum
	 * @return
	 */
	boolean checkManagerType(Long partyId, String userNum);

	/**
	 * 根据费用id查询费用
	 * 
	 * @param id
	 * @return
	 */
	BizExpenseRate getBizExpenseRateById(Long id);

	/**
	 * 根据projectId查询QuotaMeasure
	 * 
	 * @param projectId
	 * @return
	 */
	QuotaMeasure searchQuotaMeasure(Long projectId);

	/**
	 * 根据projectId取出费用列表
	 * 
	 * @param projectId
	 * @return
	 */
	List getBizExpenseRatesByProjectId(Long projectId);

	/**
	 * 根据projectNo获取projectApplication实例
	 * 
	 * @param projectNo
	 * @return
	 */
	ProjectApplication getProjectApplicationByProjectNo(String projectNo);

	/**
	 * 根据productCd查productName
	 * 
	 * @param productCd
	 * @return
	 * */
	String findProductNameByProductCd(String productCd);

	/**
	 * 根据年限-查询相关利率
	 * 
	 * @param ProjectApplication
	 * @throws RollbackableBizException
	 *             功能说明：新增项目申请信息
	 * @author zhengsijing
	 * @date 2012-3-5
	 */
	MoneyRate findValidMoneyRate(String term, String termUnit);

	/**
	 * 发送流程，修改业务状态
	 * 
	 * @param workflowCode
	 * @param workflowId
	 * @param taskId
	 * @param logName
	 * @param node
	 * @param actionCode
	 * @param taskReceiver
	 *            结构为“接收人logName,orgId”，调用接口时进行分割
	 * @param comments
	 * @param taskSubject
	 * @param taskStageCode
	 * @return
	 */
	void sendProccessAndUpdateApplication(WorkFlowCode workflowCode,
			String workflowId, String taskId, String logName,
			WorkFlowNode node, ActionCode actionCode, String taskReceiver,
			String comments, String taskSubject, String taskStageCode)
			throws Exception;

	/**
	 * 检查保存的费用是否有已经存在的费用名称
	 * 
	 * @param bizExpenseRate
	 * @return
	 */
	BizExpenseRate checkExpenseName(BizExpenseRate bizExpenseRate);

	/**
	 * 根据查询条件查询业务申请
	 * 
	 * @param pageNumber
	 * @param pageSize
	 * @param projectNo
	 * @param productType
	 * @param customerName
	 * @param applyDateStart
	 * @param applyDateEnd
	 * @param projectStatus
	 * @param applyAmtMin
	 * @param applyAmtMax
	 * @param customerManagerNum
	 * @return
	 */
	Page<Object[]> findBySearch(Integer pageNumber, Integer pageSize, String orgId, 
			String projectNo, String productType, String customerName,
			String applyDateStart, String applyDateEnd, String projectStatus,
			String applyAmtMin, String applyAmtMax, String customerManagerNum, Long logOrgId, Long personId, DataAuthorityType dataAuthType, List<Long> dataAuthOrgIds)
			throws ParseException;
	
	/**
	 * 客户查询-->业务查询
	 * 
	 * @param pageNumber
	 * @param pageSize
	 * @param partyId
	 * @return
	 * @throws ParseException
	 */
	Page<Object[]> findList(Integer pageNumber, Integer pageSize, String partyId)
			throws ParseException;

	/**
	 * 查询业务申请与抵质押关联表
	 * 
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	Page<Object[]> searchProjectPawnInfoList(Integer pageNumber,
			Integer pageSize, Long projectId);
	
	/**
	 * 查询抵质押物列表
	 * @param guaranteeMode 担保方式
	 * @param projectId 业务编号
	 * @return 抵质押物列表
	 */
	List<CollateralVO> searchProjectPawnInfoList(String guaranteeMode,Long projectId);

	/**
	 * 保存ProjectPawnInfo
	 * 
	 * @param guarantyId
	 * @param appGuaDebtInterAmt
	 * @param pawnRatio
	 * @param projectId
	 */
	void saveProjectPawnInfo(String guarantyId, String appGuaDebtInterAmt,
			String pawnRatio, Long projectId);

	/**
	 * 通过抵质押物编号获取pawninfo信息
	 * 
	 * @param guarantyId
	 * @return
	 */
	List<ProjectPawnInfo> getProjectPawnInfoByGuarantyId(Long guarantyId);

	/**
	 * 删除ProjectPawnInfo
	 * 
	 * @param projectPawnInfoId
	 */
	void deleteProjectPawnInfo(Long projectPawnInfoId);

	/**
	 * 删除ProjectAssurerInfo
	 * 
	 * @param projectAssurerInfoId
	 */
	void deleteProjectAssurerInfo(Long projectAssurerInfoId);

	/**
	 * 删除CommonBorrow
	 * 
	 * @param commonBorrowId
	 */
	void deleteCommonBorrow(Long commonBorrowId);

	/**
	 * 查询业务申请与保证人关联表
	 * 
	 * @param pageNumber
	 * @param pageSize
	 * @param projectId
	 * @return
	 */
	Page<Object[]> searchBailList(Integer pageNumber, Integer pageSize,
			Long projectId);

	/**
	 * 查询可以做保证人的客户
	 * 
	 * @param certificateNum
	 * @param certificateTypeCd
	 * @param customerNum
	 * @param customerName
	 * @param projectId
	 * 
	 * @param i
	 * @param iDisplayLength
	 * @param projectId
	 * @return
	 */
	Page<Object[]> searchCustomerForBailList(Integer pageNumber,
			Integer pageSize, String userNum, Integer partyId,
			String customerName, String customerNum, String certificateTypeCd,
			String certificateNum, Long projectId);

	/**
	 * 检查客户是否已婚，企业客户与未婚客户均抛出异常
	 * 
	 * @param partyId
	 * @throws Exception
	 */
	void isMarried(Long partyId) throws Exception;

	/**
	 * 查询业务申请与共同借款人关联表
	 * 
	 * @param pageNumber
	 * @param pageSize
	 * @param projectId
	 * @return
	 */
	Page<Object[]> searchCommonBorrowerList(Integer pageNumber,
			Integer pageSize, Long projectId);

	/**
	 * 查询可以做共同借款人的客户
	 * 
	 * @param pageNumber
	 * @param pageSize
	 * @param userNum
	 * @param partyId
	 * @param certificateNum
	 * @param certificateTypeCd
	 * @param customerNum
	 * @param customerName
	 * @param projectId
	 * @return
	 */
	Page<Object[]> searchCustomerForCommonBorrower(Integer pageNumber,
			Integer pageSize, String userNum, Integer partyId,
			String customerName, String customerNum, String certificateTypeCd,
			String certificateNum, Long projectId);

	/**
	 * 保存共同借款人
	 * 
	 * @param commonBorrow
	 */
	void saveCommonBorrow(CommonBorrow commonBorrow);

	/**
	 * 保存保证人
	 * 
	 * @param projectAssurerInfo
	 */
	void saveprojectAssurerInfo(ProjectAssurerInfo projectAssurerInfo,
			String bailMateBorrower);

	/**
	 * 保存保证人(团结贷自动添加联保体成员人入借款担保人)
	 * 
	 * @author wangxy 20150623
	 */
	void saveUniteProjectAssurerInfo(ProjectAssurerInfo projectAssurerInfo);
	
	/**
	 * 保存财务信息
	 * 
	 * @param salaBusiCustFinalcial
	 */
	void saveFinance(SalaBusiCustFinalcial salaBusiCustFinalcial);

	/**
	 * 获得财务信息
	 * 
	 * @param id
	 */
	SalaBusiCustFinalcial getFinance(Long id);

	/**
	 * 通过业务ID获得财务信息
	 * 
	 * @param projectId
	 * @return
	 */
	SalaBusiCustFinalcial getFinanceByProjectId(String projectId);

	/**
	 * 通过主键获取projectPawnInfo
	 * 
	 * @param id
	 * @return
	 */
	ProjectPawnInfo getProjectPawnInfo(Long id);

	/**
	 * 通过projectAssurerInfoId获取party
	 * 
	 * @param projectAssurerInfoId
	 * @return
	 */
	Party getPartyByProjectAssurerInfoId(Long projectAssurerInfoId);

	/**
	 * 通过commonBorrowerId获取party
	 * 
	 * @param commonBorrowerId
	 * @return
	 */
	Party getPartyByCommonBorrowerId(Long commonBorrowerId);

	/**
	 * 通过projectId获取关联保证人列表
	 * 
	 * @param projectId
	 * @return
	 */
	List<ProjectAssurerInfo> getProjectAssurerInfoByProjectId(Long projectId);

	/**
	 * 通过产品定价设置设置好的还款方式代码获取还款方式
	 * 
	 * @param productPrice
	 * @return
	 */
	List<Object[]> findRepayingModesByProductPrice(ProductPrice productPrice);

	/**
	 * 获得客户联系人列表
	 * 
	 * @param partyId
	 * @return
	 */
	List<FamilyFriend> findFamilyFriendsByPartyId(Long partyId);

	/**
	 * 获得客户配偶
	 * 
	 * @param partyId
	 * @return
	 */
	FamilyFriend getSpouseByPartyId(Long partyId);

	/**
	 * 查看配偶信息
	 * 
	 * @param commonBorrowerId
	 * @return
	 */
	FamilyFriend getSpouseByCommonBorrowerId(Long commonBorrowerId);

	/**
	 * 批量初始化还款计划列表
	 * 
	 * @param projectId
	 * @param applyAmt
	 * @param applyTerm
	 * @param applyTermUnit
	 * @param startDate
	 * @param monthDate
	 * @param repayDate
	 */
	void insertRepayPlanList(Long projectId, BigDecimal applyAmt,
			Integer applyTerm, String applyTermUnit, Date startDate,
			Integer monthDate, Integer repayDate) throws Exception;

	/**
	 * 查询还款计划列表
	 * 
	 * @param projectNo
	 * @return
	 */
	Page<Object[]> searchRepayPlanList(Integer pageNumber, Integer pageSize,
			String projectNo);

	/**
	 * 保存还款计划
	 * 
	 * @param form
	 */
	void saveRepayPlan(ApprovalHistoryRepayPlan form);

	/**
	 * 获取还款计划
	 * 
	 * @param id
	 * @return
	 */
	ApprovalHistoryRepayPlan getRepayPlan(Long id);

	/**
	 * 删除还款计划
	 * 
	 * @param id
	 */
	void deleteRepayPlan(Long id);

	/**
	 * --微贷产品-- 发起业务申请service 1、启动流程 2、任务处理 3、获取按钮 4、新建业务
	 * 
	 * @param productCd
	 * @param party
	 * @param curUserLongName
	 * @param curUserId
	 * @param curUserName
	 * @param curUserOrgId
	 * @param curUserOrgDesc
	 * @return
	 * @throws Exception
	 */
	BusinessApplicationWdVO startMicroloanBusiness(Long productCd, Party party,
			String curUserLongName, Long curUserId, String curUserName,
			Long curUserOrgId, String curUserOrgDesc) throws Exception;

	/**
	 * --微贷产品-- 保存业务申请信息
	 * 
	 * @param form
	 * @return
	 * @throws Exception
	 */
	Long saveMicroloanBusiness(BusinessApplicationWdVO form) throws Exception;

	/**
	 * --易贷产品-- 发起业务申请service 1、启动流程 2、任务处理 3、获取按钮 4、新建业务
	 * 
	 * @param productCd
	 * @param party
	 * @param curUserLongName
	 * @param curUserId
	 * @param curUserName
	 * @param curUserOrgId
	 * @param curUserOrgDesc
	 * @param model
	 * @return
	 * @throws Exception
	 */
	BusinessApplicationWdVO startEasyLoanBusiness(Long productCd, Party party,
			String curUserLongName, Long curUserId, String curUserName,
			Long curUserOrgId, String curUserOrgDesc, Model model)
			throws Exception;

	/**
	 * --易贷产品-- 保存业务申请信息
	 * 
	 * @param projectApplication
	 * @param bizRateId
	 * @param interestRateAdjustment
	 * @param overFloatRate
	 * @param divertFloatRate
	 * @param rateValue
	 * @return
	 * @throws Exception
	 */
	BizRate saveEasyLoanBusiness(ProjectApplication projectApplication,
			String bizRateId, String interestRateAdjustment,
			BigDecimal overFloatRate, BigDecimal divertFloatRate,
			BigDecimal rateValue) throws Exception;

	/**
	 * 获取产品配置信息
	 * 
	 * @param productCd
	 * @param orgId
	 * @return
	 */
	ProductConfig getProductConfigByProductCd(Long productCd, Long orgId);

	/**
	 * 通过workflowId获取对应业务
	 * 
	 * @param workflowId
	 * @return
	 */
	ProjectApplication findProjectApplicationByWorkflowId(Long workflowId);

	/**
	 * 撤销流程
	 * 
	 * @param projectId
	 * @param workflowId
	 * @param taskId
	 * @param curUserLogname
	 * @param easyLoan
	 * @param elEntrybusiapplinfo
	 * @return
	 */
	TypedResult<WorkFlowTaskIds> cancelApply(Long projectId, String workflowId,
			String taskId, String curUserLogname, WorkFlowCode easyLoan,
			WorkFlowNode elEntrybusiapplinfo);

	/**
	 * 获取Product
	 * 
	 * @param productCd
	 * @return
	 */
	Product findProductByProductCd(String productCd);

	/**
	 * 获取所有保险机构
	 * 
	 * @return
	 */
	List<InsuranceCompany> findAllInsuranceCompany();

	/**
	 * 计算保险费用
	 * 
	 * @param insuranceOrgId
	 * @param applyAmt
	 * @param bizRate
	 * @param applyDate
	 * @param applyTerm
	 * @param applyTermUnit
	 * @param guaranteeMode
	 * @param irTypeCd
	 * @return
	 */
	BigDecimal countPremiumFee(Long insuranceOrgId, BigDecimal applyAmt,
			BigDecimal bizRate, String applyDate, String applyTerm,
			String applyTermUnit, String guaranteeMode, String irTypeCd);

	/**
	 * 提交申请之前，检查此业务是否已具备提交的条件
	 * 
	 * @param projectId
	 * @param guaranteeMode
	 * @param workFlowCode
	 * @throws Exception
	 */
	void checkBusinessInfoBeforeSendProcess(Long projectId,
			String guaranteeMode, String workFlowCode) throws Exception;

	/**
	 * 保存用户新建的共同借款人
	 * 
	 * @param newCommonBorrowerVO
	 * @param customerManagerTeam
	 */
	void saveNewCommonBorrower(NewCommonBorrowerVO newCommonBorrowerVO,
			CustomerManagerTeam customerManagerTeam);

	Page findDocumentIndexBySearch(int i, Integer pageSize, List<Object> params);

	/**
	 * 保存月可支配收入测算
	 * 
	 * @param form
	 * @return
	 */
	MonthDominateIncludeMeasure saveMeasure(MonthDominateIncludeMeasure form);

	/**
	 * 通过projectId获取月可支配收入测算
	 * 
	 * @param projectId
	 * @return
	 */
	MonthDominateIncludeMeasure getMeasureByProjectId(Long projectId);

	/**
	 * 保存家庭资产
	 * 
	 * @param form
	 */
	FamilyAssetsDetail saveFamilyAssets(FamilyAssetsDetail form);

	/**
	 * 通过projectId获取家庭资产信息
	 * 
	 * @param projectId
	 * @return
	 */
	FamilyAssetsDetail getFamilyAssetsByProjectId(Long projectId);

	/**
	 * 查找该客户的custDocType的集合
	 * 
	 * @param partyId
	 * @param projectId
	 * @return List<String>
	 * */
	List<String> findDocumentCustDocTypes(Long partyId, Long projectId);

	/**
	 * 微贷申请选择浮动利率时，通过利率上浮比计算利率
	 * 
	 * @param applyTerm
	 * @param applyTermUnit
	 * @param floatRate
	 * @return
	 */
	BigDecimal countRateByFloatRate(Integer applyTerm, String applyTermUnit,
			BigDecimal floatRate);

	/**
	 * 当发其产品为房易贷是，检查客户是否符合要求
	 * 
	 * @param individual
	 * @param productConfig
	 * @throws Exception
	 */
	void checkPartyPropsWhenEasyLoan(Individual individual,
			ProductConfig productConfig) throws Exception;

	/**
	 * 通过projectNo批量删除还款计划
	 * 
	 * @param projectNo
	 */
	void deleteRepayPlanByProjectNo(String projectNo);

	/**
	 * 查询贷款计算器列表
	 * 
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	Page<Object[]> searchCalRepaymentPlanList(Integer pageNumber,
			Integer pageSize);

	/**
	 * 删除贷款计算器列表
	 */
	void deleteCalRepaymentPlan();

	/**
	 * 根据projectId删除还款计划表
	 * 
	 * @param projectId
	 */
	void deleteRepayPlanByProjectId(Long projectId);

	/**
	 * 获取微贷事业部管理人员角色
	 * 
	 * @return
	 */
	String findAdminRoles();

	/**
	 * 校验客户是否有未结清的项目--主要是业务申请和贷款展期
	 * 
	 * @param partyId
	 * @param businessProcessType
	 * @param contractNum
	 * */
	boolean validateCustomerProjectInfo(String partyId,
			String businessProcessType, String contractNum);

	/**
	 * 检查年利率是否大于央行基准利率的N倍，N从product_config表中取出
	 * 
	 * @param productCd
	 *            产品ID
	 * @param orgId
	 *            机构ID
	 * @param applyTerm
	 *            申请期限
	 * @param applyTermUnit
	 *            申请期限单位
	 * @param rate
	 *            年利率
	 * @throws Exception
	 */
	Result validateRate(Long productCd, Long orgId, Integer applyTerm, String applyTermUnit, BigDecimal rate);

	Page<Object[]> searchRepayPlanTempList(Integer pageNumber, Integer pageSize,
			String projectNo);

	RepayPlanTemp getRepayPlanTemp(Long id);

	void deleteRepayPlanTemp(Long id);

	void saveRepayPlanTemp(RepayPlanTemp form);
	/**
	 * 业务申请：检查申报金额是否满足条件
	 * @author:gph
	 * @createTime:2015年6月8日
	 * @param applyAmount 申报金额
	 * @param productCd 产品编号
	 * @param orgId 申请机构
	 */
	void checkApplyAmountIsAvailable(BigDecimal applyAmount, Long productCd, Long orgId);
	
	
	/***
	 * 获取某种贷款产品业务的标识
	 * 
	 * @param productCd 贷款产品ID
	 * @param productConfigName 贷款产品配置名称（在GlobalConstants中配置的名称）
	 * @return true:贷款产品业务； false:其他业务
	 * @author wangyawei
	 * @date 2015-07-02
	 */
	boolean getProductLoanFlag(String productConfigName, String productCd);
}
