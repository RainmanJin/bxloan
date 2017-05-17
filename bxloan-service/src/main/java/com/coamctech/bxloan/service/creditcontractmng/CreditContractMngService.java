package com.coamctech.bxloan.service.creditcontractmng;

import java.util.List;

import org.springframework.data.domain.Page;

import com.coamctech.bxloan.commons.result.Result;
import com.coamctech.bxloan.entity.CommonBorrow;
import com.coamctech.bxloan.entity.DocumentIndex;
import com.coamctech.bxloan.entity.SubContract;
import com.coamctech.bxloan.entity.credit.CreditContract;
import com.coamctech.bxloan.service.model.SessionUser;
import com.coamctech.bxloan.service.model.contractmng.ProjectAssurerInfoVo;
import com.coamctech.bxloan.service.model.contractmng.ProjectPawnInfoVo;
import com.coamctech.bxloan.service.model.credit.CreditContractVo;

/**   
 * 类名称：CreditContractMng
 * 类描述 ：授信合同管理Service
 * 创建人: gph 
 * 创建时间：2015年5月13日 下午1:59:01  
 * 修改人：gph
 * 修改时间：2015年5月13日 下午1:59:01  
 * 修改备注：
 * 版本： V1.0
 */
public interface CreditContractMngService {
	/**
	 * 分页查询授信合同列表
	 * 
	 * @author:gph
	 * @createTime:2015年5月13日
	 * @param pageNumber 当前页数
	 * @param pageSize 每页显示条数
	 * @param creditContract 授信合同对象
	 * @return 授信合同列表
	 */
	public Page<CreditContractVo> creditContractList(Integer pageNumber, Integer pageSize, CreditContractVo vo);
	
	/**
	 * 根据业务Id获取授信合同对象
	 * 
	 * @param projectId 业务id
	 * @return CreditContract对象 返回查到的授信合同对象
	 * 
	 * @author wangyawei
	 * @lastModified wangyawei 2015年7月10日 下午2:27:40
	 */
	public CreditContract findCreditContractByProjectId(Long projectId);
	
	/**
	 * 保存授信合同信息
	 * 
	 * @param CreditContract 授信合同对象
	 * @return Long 授信合同Id
	 * 
	 * @author wangyawei
	 * @lastModified wangyawei 2015年7月10日 下午2:30:40
	 */
	public Long saveCreditContract(CreditContract creditContract);

	/**
	 * 根据授信合同Id获取授信合同对象
	 * 
	 * @param creditContractId 授信合同Id
	 * @return CreditContract对象 返回查到的授信合同对象
	 * 
	 * @author wangyawei
	 * @lastModified wangyawei 2015年7月10日 下午2:30:40
	 */
	public CreditContract getCreditContractById(Long creditContractId);
	
	/** 
	 * 保存从合同信息
	 * 
	 * @param subcontract
	 * 
	 * @author wangyawei
	 * @lastModified wangyawei 2015年7月13日 下午3:27:40
	 */
	public void saveSubContract(SubContract subcontract);
	
	/**
	 * 根据抵质押信息生成抵质押从合同信息
	 * 
	 * @param projectId 业务ID
	 * @param sessionUser 登录用户对象
	 * 
	 * @author wangyawei
	 * @lastModified wangyawei 2015年7月13日 下午3:30:40
	 */
	public void copyPawnInfoToSubContract(Long projectId, SessionUser sessionUser);
	
	/**
	 * 根据保证人信息生成保证人从合同信息
	 * 
	 * @param projectId 业务ID
	 * 
	 * @author wangyawei
	 * @lastModified wangyawei 2015年7月13日 下午3:30:40
	 */
	public void copyAssureInfoToSubContract(Long projectId, SessionUser sessionUser);
	
	/**
	 * 根据业务ID获取所有的从合同列表
	 * 
	 * @param projectId 业务ID
	 * @return List 
	 * 
	 * @author wangyawei
	 * @lastModified wangyawei 2015年7月13日 下午3:30:40
	 */
	public List<SubContract> findSubcontractList(Long projectId);
	
	/**
	 * 校验主合同下的从合同是否全部上传附件
	 * 
	 * @param projectId 业务ID
	 * @return Result 
	 * 
	 * @author wangyawei
	 * @lastModified wangyawei 2015年7月13日 下午3:30:40
	 */
	public Result checkSubContractDoc(Long projectId);
	
	/**
	 * 根据projectId获取共同人列表
	 * 
	 * @param projectId 业务id
	 * @param pageNumber 页码
	 * @param pageSize 每页数量
	 * @return Page 分页对象
	 * 
	 * @author wangyawei
	 * @lastModified wangyawei 2015年8月5日 下午3:27:40
	 */
	public Page<CommonBorrow> findBorrowerListBySearch(Long projectId, Integer pageNumber, Integer pageSize);
	
	/**
	 * 根据projectId获取保证人信息
	 * 
	 * @param projectId 业务id
	 * @param pageNumber 页码
	 * @param pageSize 每页数量
	 * @return Page 分页对象
	 * 
	 * @author wangyawei
	 * @lastModified wangyawei 2015年8月5日 下午3:30:40
	 */
	public Page findBailListBySearch(Long projectId, Integer pageNumber, Integer pageSize);
	
	/** 
	 * 根据授信合同/合同ID查询抵质押从合同列表方法
	 * 
	 * @param contractId 授信合同/合同ID
	 * @param pageNumber 页码
	 * @param pageSize 每页数量
	 * @return Page 分页对象
	 *
	 * @author wangyawei
	 * @lastModified wangyawei 2015年8月6日 下午3:27:40
	 */
	public Page<ProjectPawnInfoVo> findCollateralSubcontractListBySearch(Long contractId, Integer pageNumber, Integer pageSize);
	
	/** 
	 * 根据授信合同/合同ID查询保证人从合同列表方法
	 * 
	 * @param contractId 授信合同/合同ID
	 * @param pageNumber 页码
	 * @param pageSize 每页数量
	 * @return Page 分页对象
	 *
	 * @author wangyawei
	 * @lastModified wangyawei 2015年8月6日 下午3:27:40
	 */
	public Page<ProjectAssurerInfoVo> findAssureSubcontractListBySearch(Long contractId, Integer pageNumber, Integer pageSize);
	
	/** 
	 * 查询客户和业务文档列表方法
	 * 
	 * @param params 查询sql集合
	 * @param pageNumber 页码
	 * @param pageSize 每页数量
	 * @return Page 分页对象
	 *
	 * @author wangyawei
	 * @lastModified wangyawei 2015年8月6日 下午3:27:40
	 */
	public Page<DocumentIndex> findCustomerDocumentListBySearch(List<Object> params, Integer pageNumber, Integer pageSize);
}
