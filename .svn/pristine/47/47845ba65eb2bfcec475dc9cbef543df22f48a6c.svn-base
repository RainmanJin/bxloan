package com.coamctech.bxloan.service.contractmng;

import com.coamctech.bxloan.service.freemarker.data.GuarantyContractInfo;
import com.coamctech.bxloan.service.freemarker.data.LoanContractInfo;
import com.coamctech.bxloan.service.freemarker.data.LoanCorpContractInfo;
import com.coamctech.bxloan.service.freemarker.data.ScpListBaseInfo;
import com.coamctech.bxloan.service.freemarker.data.SubContrBaseInfo;
import com.coamctech.bxloan.service.model.contractmng.SubContractVo;

public interface ContractFileService {
	/**
	 * 查询合同类型名称
	 * @param contractTypeKey
	 * @return
	 */
	public String getContractTypeName(String contractTypeKey);
	
	/**
	 * 根据子合同id查询保证合同信息
	 * @param subContractId	从合同Id
	 * @return
	 */
	public GuarantyContractInfo findGuarantyContractInfo(Long subContractId);
	
	/**
	 * 查询从合同信息
	 * @param subContractId	从合同Id
	 * @return
	 */
	public SubContractVo findSubContract(Long subContractId);

	/**
	 * 查询抵质押合同信息
	 * 
	 * @param subContractId 从合同Id
	 * @return SubContrMortInfo：抵押从合同信息，SubContrPledgeInfo：质押从合同信息
	 */
	public SubContrBaseInfo findSubContrBaseInfo(Long subContractId);
	
	/**
	 * @param subContractId
	 * @return
	 */
	public ScpListBaseInfo findScpListBaseInfo(Long subContractId);
	
	/**
	 * 装配主合同 个人客户
	 * @param contractId
	 */
	public LoanContractInfo getLoanContract(Long contractId);
	
	/**
	 * 通过contractId查客户类型
	 * @param contractId
	 * 1企业客户
	 * 2个人客户
	 */
	public String findPartyTypeByContractId(Long contractId);
	
	/**
	 * 装配主合同 企业客户
	 * @param contractId
	 */
	public LoanCorpContractInfo getLoanCorpContract(Long contractId);
	
	/**
	 * 装配个人客户-授信主合同文本信息
	 * 
	 * @author: wangyawei
	 * @createDate: 2015年7月15日
	 * @param creditContractId
	 * @return LoanContractInfo 授信主合同-个人对象
	 */
	public LoanContractInfo assembleCreditContractIndividual(Long creditContractId);
	
	/**
	 * 装配企业客户-授信主合同文本信息
	 * 
	 * @author: wangyawei
	 * @createDate: 2015年7月15日
	 * @param creditContractId
	 * @return LoanCorpContractInfo 授信主合同-企业对象
	 */
	public LoanCorpContractInfo assembleCreditContractCorporation(Long creditContractId);
	
	/**
	 * 装配授信合同-抵质押合同信息
	 * 
	 * @author: wangyawei
	 * @createDate: 2015年7月15日
	 * @param subContractId 从合同Id
	 * @return SubContrMortInfo：抵押从合同信息，SubContrPledgeInfo：质押从合同信息
	 */
	public SubContrBaseInfo assembleCreditContractCollaSubContr(Long subContractId);
	
	/**
	 * 装配授信合同-保证人合同信息
	 * 
	 * @author: wangyawei
	 * @createDate: 2015年7月15日
	 * @param subContractId 从合同Id
	 * @return GuarantyContractInfo：保证从合同信息
	 */
	public GuarantyContractInfo assembleCreditContractAssureSubContr(Long subContractId);
}
