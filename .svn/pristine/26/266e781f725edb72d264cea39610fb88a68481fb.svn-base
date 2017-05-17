package com.coamctech.bxloan.service.sysmng;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestParam;

import com.coamctech.bxloan.document.CustDocInfo;
import com.coamctech.bxloan.entity.DocumentIndex;

public interface DocumentService {
	/**
	 * 根据客户id改变指定类型（documentTypes）的DocumentIndex的createTypeCd
	 * @param partyId 
	 * @param documentTypes	为空，则更新所有类型的
	 * @param createTypeCd 1：创建，2：引用
	 * @return
	 */
	public boolean updateDocumentIndexByPartyId(Long partyId, Set<String> documentTypes,String createTypeCd);
	/**
	 * 根据业务id改变指定类型（documentTypes）的DocumentIndex的createTypeCd
	 * @param projectId	业务id
	 * @param documentTypes	为空，则更新所有类型的
	 * @param createTypeCd 1：创建，2：引用
	 * @return
	 */
	public boolean updateDocumentIndexByProjectId(Long projectId, Set<String> documentTypes,String createTypeCd);
	
	/**
	 * 根据信息获取上传文档种类
	 * @param projectId
	 * @param partyId
	 * @param custDocTypeCd
	 * 1-经营客户
	 * 2-企业客户
	 * 3-受薪客户
	 * 4-农户
	 * 5-微贷
	 * 6-易贷
	 * 7-易贷评审
	 * 8-易贷审批；微贷贷款审查
	 * 9-微贷初审
	 * */
	public CustDocInfo findUploadCustDocTypes(Long projectId, Long partyId, String custDocTypeCd);
	/**
	 * 根据信息获取已上传文档的类型
	 * @param projectId
	 * @param partyId
	 * @param custDocTypeCd
	 * 1-经营客户
	 * 2-企业客户
	 * 3-受薪客户
	 * 4-农户
	 * 5-微贷
	 * 6-易贷
	 * 7-易贷评审
	 * 8-易贷审批；微贷贷款审查
	 * 9-微贷初审
	 * */
	public List<Object[]> findDocumentCustDocTypes(Long projectId, Long partyId, String custDocTypeCd);
	/**
	 * 根据信息获取已上传文档的类型
	 * @param projectId
	 * @param partyId
	 * @param custDocTypeCd
	 * 7-易贷评审
	 * 8-易贷审批；微贷贷款审查
	 * 9-微贷初审
	 * */
	public List<Object[]> findDocumentCustDocTypes(Long projectId, String custDocTypeCd);
	/**
	 * 模糊查询内容类型
	 * */
	public List<String> findCustDocTypeNames(String queryName);
	/**
	 * 流程中的文件分页查询
	 * @param params 参数集合
	 * @param pageNumber 页号
	 * @param pageSize 每页条数
	 * 
	 * @return Page 分页查询对象
	 * */
	public Page findDocumentsInApproval(int pageNumber, Integer pageSize, List<Object> params);
	/**
	 * 查找某一内容类型文件的上传数量
	 * @param projectId,
	 * @param custDocType
	 * */
	public Integer findCustDocTypeNum(Long projectId, String custDocType);
	/**
	 * 查找文档（合同处）
	 * */
	public List<DocumentIndex> findByPartyIdAndProject(Long partyId, Long projectId);
	/**
	 * 查找文档（联保体处）
	 * */
	public Page<Object[]> findDocumentsInUnite(int pageNumber, Integer pageSize, List<Object> params);
	/**
	 * 查找文档（抵质押处）
	 * */
	public Page findDocumentsInCollateral(Integer pageNumber, Integer pageSize, List<Object> params);
	/**
	 * 查找已上传文档（抵质押处）
	 * */
	public List<Object[]> findDocumentCustDocTypes(String guarantyNum, String custDocTypeCd);
	
}
