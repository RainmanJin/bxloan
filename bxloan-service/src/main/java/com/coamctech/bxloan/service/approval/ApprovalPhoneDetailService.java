package com.coamctech.bxloan.service.approval;

import java.util.List;

import com.coamctech.bxloan.entity.ApprovalPhoneDetail;
import com.coamctech.bxloan.entity.FamilyFriend;

public interface ApprovalPhoneDetailService {

	List<ApprovalPhoneDetail> findPhoneInfoBySearch(Integer pageNumber, Integer pageSize, String projectId);
	
	void saveApprovalPhoneDetail(ApprovalPhoneDetail approvalPhoneDetail);
	
	void deleteApprovalPhoneDetail(Long id);
	
	ApprovalPhoneDetail editApprovalPhoneDetail(Long id);
	
	public List<FamilyFriend> findWifeByPartyId(Long partyId);
}
