package com.coamctech.bxloan.service.contractmng;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Page;

import com.coamctech.bxloan.commons.result.Result;
import com.coamctech.bxloan.entity.BizRate;
import com.coamctech.bxloan.entity.Contract;
import com.coamctech.bxloan.entity.CustomerAccountManagent;
import com.coamctech.bxloan.entity.DocumentIndex;
import com.coamctech.bxloan.entity.ProjectApplication;
import com.coamctech.bxloan.entity.ProjectAssurerInfo;
import com.coamctech.bxloan.entity.SubContract;
import com.coamctech.bxloan.service.model.PayLoanInfoVO;
import com.coamctech.bxloan.service.model.statistics.BizContractVo;

public interface ContractMngService {
	/**
	 * 合同列表查询
	 * 
	 * @param params
	 *            参数集合
	 * @param pageNumber
	 *            页号
	 * @param pageSize
	 *            每页条数
	 * @return Page 分页查询对象
	 * 
	 * @author
	 * @lastModified lijing 2014-07-22 16:37:51
	 */
	Page findContractBySearch(Integer pageNumber, Integer pageSize,
			List<Object> params);

	/**
	 * 合同台账查询列表
	 * 
	 * @param pageNumber
	 *            页号
	 * @param pageSize
	 *            每页条数
	 * @param vo
	 *            合同台账业务类
	 * @return 分页查询对象
	 * @author gph
	 */
	Page<BizContractVo> findContractBySearch(Integer pageNumber,
			Integer pageSize, BizContractVo vo);
	/**
	 * 合同台账查询列表(客户查询)
	 * 
	 * @param pageNumber
	 *            页号
	 * @param pageSize
	 *            每页条数
	 * @param vo
	 *            合同台账业务类
	 * @return 分页查询对象
	 * @author gph
	 */
	Page<BizContractVo> findContractByCondition(Integer pageNumber,
			Integer pageSize, BizContractVo vo, String partyId);

	/**
	 * 保存合同信息
	 * 
	 * @param contract
	 *            合同对象
	 * @return Long 合同Id
	 * @author lijing
	 * @lastModified lijing 2014-07-22 16:37:51
	 */
	Long saveContract(Contract contract);

	/**
	 * 根据合同Id获取合同对象
	 * 
	 * @param partyId
	 *            客户对象
	 * @return Individual对象 返回查到的客户对象
	 * 
	 * @author lijing
	 * @lastModified lijing 2014-07-22 16:37:51
	 */
	Contract getContract(Long contractId);

	/**
	 * 获取合同表记录数
	 * 
	 * @return Long 统计记录数
	 * 
	 * @author lijing
	 * @lastModified lijing 2014-07-22 16:37:51
	 */
	Long countContract();

	/**
	 * 判断含某合同Id的记录是否存在
	 * 
	 * @param contractId
	 *            合同id
	 * @return Boolean 是否存在
	 * 
	 * @author lijing
	 * @lastModified lijing 2014-07-22 16:37:51
	 */
	Boolean existsContract(Long contractId);

	/**
	 * 根据合同Id删除合同记录
	 * 
	 * @param contractId
	 *            合同id
	 * @return String 是否删除
	 * 
	 * @author lijing
	 * @lastModified lijing 2014-07-22 16:37:51
	 */
	void deleteContract(Long contractId);

	/**
	 * 根据业务Id获取业务对象
	 * 
	 * @param projectId
	 *            业务id
	 * @return ProjectApplication对象 返回查到的业务对象
	 * 
	 * @author lijing
	 * @lastModified lijing 2014-07-22 16:37:51
	 */
	ProjectApplication getProject(Long projectId);

	/**
	 * 根据业务Id获取利率对象
	 * 
	 * @param projectId
	 *            业务id
	 * @return BizRate对象 返回查到的利率对象
	 * 
	 * @author lijing
	 * @lastModified lijing 2014-07-22 16:37:51
	 */
	BizRate findBizRateByProjectId(Long projectId);

	/**
	 * 根据业务Id获取合同对象
	 * 
	 * @param projectId
	 *            业务id
	 * @return Contract对象 返回查到的合同对象
	 * 
	 * @author lijing
	 * @lastModified lijing 2014-07-22 16:37:51
	 */
	Contract findContractByProjectId(Long projectId);

	/**
	 * 根据客户Id获取账户信息
	 * 
	 * @param partyId
	 *            业务id
	 * @return List对象 返回查到的账户集合
	 * 
	 * @author lijing
	 * @lastModified lijing 2014-07-22 16:37:51
	 */
	List<CustomerAccountManagent> findAccountsByPartyId(Long partyId);

	/**
	 * 根据账户Id获取账号
	 * 
	 * @param accountId
	 *            账户id
	 * @return String 返回查到账号
	 * 
	 * @author lijing
	 * @lastModified lijing 2014-07-22 16:37:51
	 */
	List<String> findAccountNumByAccountId(Long accountId);

	/**
	 * 根据业务Id获取费用列表
	 * 
	 * @param projectId
	 *            业务id
	 * @param pageNumber
	 *            几页开始
	 * @param pageSize
	 *            一页几条
	 * @Param params 参数集合
	 * @return Page 分页对象
	 * 
	 * @author lijing
	 * @lastModified lijing 2014-07-22 16:37:51
	 */
	Page findExpenseBySearch(String projectId, int pageNumber,
			Integer pageSize, List<Object> params);

	/**
	 * 保存利率信息
	 * 
	 * @param bizRate
	 *            利率对象
	 * @author lijing
	 * @lastModified lijing 2014-08-15 16:37:51
	 */
	void saveBizRate(BizRate bizRate);

	/**
	 * 获取贷款人信息
	 * 
	 * @param orgId
	 *            contract中的applyOrgId
	 * @return List
	 * @author lijing
	 * @lastModified lijing 2014-08-28 16:37:51
	 */
	List getLoanerInfo(Long orgId);

	/**
	 * 由codeType,codeValue获取字典表翻译
	 * 
	 * @param codeType
	 *            Code表的code_type字段
	 * @param codeValue
	 *            Code表的code_value字段
	 * @return String codeName
	 * @author lijing
	 * @lastModified lijing 2014-08-28 16:37:51
	 */
	String codeTransilate(String codeType, String codeValue);

	/**
	 * 通过行业cd查行业名
	 * 
	 * @param investmentIndustry
	 *            行业cd
	 * @return String 行业名
	 * @author lijing
	 * @lastModified lijing 2014-08-28 16:37:51
	 */
	String findIndustryNameByIndustryCd(String investmentIndustry);

	/**
	 * 由codeType,codeValue获取codeKey
	 * 
	 * @param codeType
	 *            Code表的code_type字段
	 * @param codeValue
	 *            Code表的code_value字段
	 * @return String codeKey
	 * @author lijing
	 * @lastModified lijing 2014-08-28 16:37:51
	 */
	String getCodeByTypeAndValue(String string, String contractStatusCd);

	/**
	 * 根据projectid查共同借款人的数量
	 * 
	 * @param projectId
	 * @return
	 */
	Long getCommonCountByProjectId(String string);

	/**
	 * 根据projectId获取借款人列表
	 * 
	 * @param projectId
	 *            业务id
	 * @param pageNumber
	 *            几页开始
	 * @param pageSize
	 *            一页几条
	 * @Param params 参数集合
	 * @return Page 分页对象
	 * 
	 * @author lijing
	 * @lastModified lijing 2014-07-22 16:37:51
	 */
	Page findBorrowerListBySearch(String projectId, Integer pageNumber,
			Integer pageSize);

	/**
	 * 根据projectId获取抵质押物列表
	 * 
	 * @param projectId
	 *            业务id
	 * @param pageNumber
	 *            几页开始
	 * @param pageSize
	 *            一页几条
	 * @Param params 参数集合
	 * @return Page 分页对象
	 * 
	 * @author lijing
	 * @lastModified lijing 2014-07-22 16:37:51
	 */
	Page findCollateralsBySearch(String projectId, Integer pageNumber,
			Integer pageSize);

	/**
	 * 根据contractId获取从合同列表
	 * 
	 * @param contractId
	 *            主合同id
	 * @param pageNumber
	 *            几页开始
	 * @param pageSize
	 *            一页几条
	 * @Param params 参数集合
	 * @return Page 分页对象
	 * 
	 * @author lijing
	 * @lastModified lijing 2014-07-22 16:37:51
	 */
	Page getAssureListByContractId(Long contractId, Integer pageNumber,
			Integer pageSize);

	/**
	 * 根据contractId获取抵质押物列表
	 * 
	 * @param contractId
	 *            主合同id
	 * @param pageNumber
	 *            几页开始
	 * @param pageSize
	 *            一页几条
	 * @Param params 参数集合
	 * @return Page 分页对象
	 * 
	 * @author lijing
	 * @param projectId
	 * @lastModified lijing 2014-07-22 16:37:51
	 */
	Page getPawnListByContractId(Long contractId, Long projectId,
			Integer pageNumebr, Integer pageSize);
	/**
	 * 根据主合同id查询抵质押从合同列表
	 * @author HWL
	 * @param contractId
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	Page getPawnListByContractId(Long contractId, Integer pageNumebr, Integer pageSize);

	/**
	 * 根据projectId查找从合同信息
	 * 
	 * @param projectId
	 * @return List<Subcontract>
	 */
	List findSubcontractList(Long projectId);

	/**
	 * 根据projectId获取保证人信息
	 * 
	 * @param projectId
	 * @return
	 */
	List findProjectAssurerByPid(Long projectId);

	/**
	 * 保存从合同
	 * */
	void saveSubContract(SubContract subcontract);

	/**
	 * 保存保证人
	 * */
	void saveProjectAssurerInfo(ProjectAssurerInfo pai);

	/**
	 * 根据查询条件,算出抵质押物金额之和
	 * 
	 * @param projectId
	 */
	BigDecimal caculatePledgeSumByProjectId(Long projectId, String state,
			String codeVal);

	/**
	 * 根据产品代码查流程类型
	 * 
	 * @param productCd
	 * */
	String findWorkFlowTypeByProductCd(String productType);

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
	 * 根据subContractId找从合同
	 * 
	 * @param subcontractId
	 * @return SubContract
	 * */
	SubContract findSubContractBySubId(Long subcontractId);

	/**
	 * 根据contractNum获取contract
	 * 
	 * @param contractNum
	 * @return
	 */
	Contract findByContractNum(String contractNum);

	/**
	 * 根据文档编号查从合同
	 * 
	 * @param 文档编号
	 * @return
	 * */
	SubContract findByDocumentNum(String documentNum);

	/**
	 * 查找文本
	 * 
	 * @param Long
	 *            partyId
	 * @param Long
	 *            projectId
	 * @return
	 * */
	List<DocumentIndex> findDocumentIndexByPartyId(Long partyId, Long projectId);
	
	//add by wangyawei 20150423 start 合同台账-查看放款记录增加
	/**
	 * 组装根据合同编号查询放款记录sql 
	 * 
	 * @author wangyawei
	 * @lastModified wangyawei 2015-04-23 18:57:51
	 * @return
	 */
	String buildPayLoanRecordSearchSql();
	/**
	 * 根据合同Id获取该笔合同的放款记录
	 * 
	 * @param contractId 合同Id
	 * @author wangyawei
	 * @lastModified wangyawei 2015-04-23 18:57:51
	 * @return
	 */
	Page<Object[]> findPayLoanRecordSearch(Integer pageNumber, Integer pageSize, Long contractId);
	
	/**
	 * 根据Page对象获取该笔合同的放款记录
	 * 
	 * @param page
	 * @author wangyawei
	 * @lastModified wangyawei 2015-04-23 18:57:51
	 * @return
	 */
	List<PayLoanInfoVO> findPayLoanRecordInfo(Page<Object[]> page);
	//add by wangyawei 20150423 end

	/**
	 * 导出Excel
	 * @param vo
	 * @return 文件名
	 * @throws Exception
	 */
	String ExportExcel(BizContractVo vo) throws Exception;
	
	String getApplyOrgName(Long contractId);
}
