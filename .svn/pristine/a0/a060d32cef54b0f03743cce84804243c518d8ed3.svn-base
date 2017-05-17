package com.coamctech.bxloan.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.coamctech.bxloan.entity.DocumentIndex;

public interface DocumentIndexDao extends
		PagingAndSortingRepository<DocumentIndex, Long>,
		JpaSpecificationExecutor<DocumentIndex> {

	DocumentIndex findByDocumentId(Long documentId);

	@Query("select d from DocumentIndex d where d.partyId=?1 and d.status = 1 ")
	List<DocumentIndex> findByPartyId(Long partyId);

	List<DocumentIndex> findByBizIdAndDocumentType(Long bizId,
			String documentType);

	List<DocumentIndex> findByBizIdAndDocumentTypeAndStatus(Long bizId,
			String documentType, String status);

	@Query(value = "select t.cust_doc_type from DOCUMENT_INDEX t where t.biz_id = ?1 and t.status = '1'", nativeQuery = true)
	List<String> findCustDocTypesByBizId(Long bizId);

	@Modifying
	@Query(nativeQuery = true, value = "UPDATE document_index SET create_type_cd = '2',system_update_time = sysdate WHERE party_id = ?1 AND biz_num = ?2 AND create_type_cd = '1' AND status = '1'")
	void updateCreateType(Long partyId, String bizNum);
	
	/**流程结束时客户文档置为创建*/
	@Modifying
	@Query(nativeQuery = true, value = "UPDATE document_index SET create_type_cd = '1',system_update_time = sysdate WHERE party_id = ?1 AND document_type = '01'  AND status = '1'")
	void changeCreateTypeWhenWorkflowFinish(Long partyId);
	
	@Query("select d from DocumentIndex d where d.partyId = ?1 and d.bizId = ?2")
	List<DocumentIndex> findByPartyIdOrBizId(Long partyId, Long bizId);
	@Query("select di from DocumentIndex di where di.bizId = ?1 and di.custDocType = ?2 and di.status = '1' ")
	List<DocumentIndex> findByBizIdAndCustDocType(Long projectId, String custDocType);
	@Query("select d from DocumentIndex d where d.partyId = ?1 and d.bizId = ?2 and d.status = '1' ")
	List<DocumentIndex> findByPartyIdAndProjectId(Long partyId, Long projectId);
}
