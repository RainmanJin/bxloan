package com.coamctech.bxloan.service.contractmng;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Page;

import com.coamctech.bxloan.commons.result.Result;
import com.coamctech.bxloan.entity.Contract;
import com.coamctech.bxloan.entity.MoneyRate;
import com.coamctech.bxloan.entity.PayLoanInfo;
import com.coamctech.bxloan.entity.PersonDetails;
import com.coamctech.bxloan.entity.Product;
import com.coamctech.bxloan.service.model.CustomerVO;
import com.coamctech.bxloan.service.model.PayLoanInfoVO;
import com.coamctech.bxloan.service.model.RepayLoanVO;

public interface LoanGrantService {
	/**
	 * 放款列表查询
	 * 
	 * @param params 参数集合
	 * @param pageNumber 页号
	 * @param pageSize 每页条数
	 * @return Page 分页查询对象
	 * 
	 * @author
	 * @lastModified lijing 2014-07-22 16:37:51
	 */
	Page findPayLoanBySearch(Integer pageNumber, Integer pageSize, List<Object> params);

	/**
	 * 判断提前还款是否在流程中
	 * 
	 * @param transactionNO
	 * @return
	 */
	boolean validate(RepayLoanVO tmpRepayLoanVO);

	/**
	 * 根据还款信息 查还款信息列表
	 * 
	 * @param repayLoanVO
	 * @return
	 */
	List findRepayLoan(RepayLoanVO repayLoanVO);

	/**
	 * 查询此合同是否入账
	 * 
	 * @param contractNum
	 * @return
	 */
	boolean isTallyCertificateCanref(String contractNum) throws RuntimeException;

	/**
	 * 功能说明：校验该笔合同下，是否在展期申请中
	 * 
	 * @throws
	 * @return String
	 * @author lijing
	 * @date 2012-9-26
	 */
	boolean validateContractInRenewal(String contractNum);

	/**
	 * 贷款逾期或计提了损失准备就不能继续进行放款操作
	 * 
	 * @param contractId
	 * @return
	 * @throws BizException
	 */
	boolean isAgainPayLoanBy(Long contractId);

	/**
	 * 根据产品编号查产品信息
	 * 
	 * @param productCd
	 * @return
	 * @throws BizException
	 */
	Product findProductByProductCd(Long productCd);

	/**
	 * 获取最近执行利率
	 * 
	 * @param contract
	 * @return
	 * 
	 */
	BigDecimal[] getLoanRate(Contract contract);

	/**
	 * 根据年限-查询基准利率
	 * 
	 * @param timeLimit
	 * @param termUnitCd 功能说明：根据年限-查询基准利率
	 * 
	 */
	MoneyRate findValidMoneyRate(String timeLimit, String termUnitCd);

	/**
	 * 生成借款凭据编号
	 * 
	 * @param partyId
	 * @param orgId 当前用户的组织id
	 * @return String 借款凭据编号
	 */
	String generateLoanEvidenceNum(Long partyId, Long orgId);

	/**
	 * 通过合同id找放款记录
	 * 
	 * @param contractId 合同id
	 * @return List<PayLoanInfo>
	 * */
	List<PayLoanInfo> findPayLoanListByContractIdAndStatus(Long contractId, List<String> status);

	/**
	 * 保存一个放款记录的List
	 * 
	 * @param payLoanInfoList
	 * 
	 * */
	void saveLoanInfoList(List<PayLoanInfo> payLoanInfoList);

	/**
	 * 保存一个放款记录
	 * 
	 * @param payLoanInfo
	 * 
	 * */
	Long savePayLoanInfo(PayLoanInfo payLoan);

	/**
	 * 根据项目id,查询还款计划记录
	 * 
	 * @param projectId
	 */
	List findRepayPlanList(Long projectId);

	/**
	 * 通过userNum获取当前用户的详细信息
	 * 
	 * @param projectId
	 */
	PersonDetails getPersonDetailsById(Long userId);

	/**
	 * 根据客户编号查询客户信息
	 * 
	 * @param customerNum
	 * @return
	 */
	CustomerVO findCustomerByPartyId(Long partyId);

	/**
	 * 根据payLoanInfo 的id查对象
	 * 
	 * @param payLoanId
	 * @return PayLoanInfo对象
	 */
	PayLoanInfo findPayLoanById(Long payLoanId);

	/**
	 * 根据payLoanInfo 的合同id和合同状态查对象
	 * 
	 * @param contractId
	 * @param 合同状态值
	 * @return PayLoanInfo对象
	 */
	PayLoanInfo findPayLoanByContractIdAndContractStatus(String contractId, String contractStatus);
	/**
	 * 处理放款逻辑
	 * @param curUser 
	 * */
	Result  generateLoanIssues(PayLoanInfo payLoan, Contract contract, PayLoanInfoVO payLoanInfoVO, Long orgid) throws RuntimeException;
	/**
	 * 修改借据上传状态和时间
	 * @param contract
	 * @param payLoanInfo
	 * */
	void changeIsUpload(Contract contract, PayLoanInfo payLoanInfo);
}
