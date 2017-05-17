package com.coamctech.bxloan.service.approval.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coamctech.bxloan.commons.dynamicquery.DynamicQuery;
import com.coamctech.bxloan.dao.ApprovalPhoneDetailDao;
import com.coamctech.bxloan.entity.ApprovalPhoneDetail;
import com.coamctech.bxloan.entity.CultivateAgriculture;
import com.coamctech.bxloan.entity.FamilyFriend;
import com.coamctech.bxloan.service.approval.ApprovalPhoneDetailService;

@Service("approvalPhoneDetailService")
@Transactional
public class ApprovalPhoneDetailServiceImpl implements ApprovalPhoneDetailService {

	@Autowired
	private DynamicQuery dynamicQuery;
	@Autowired
	private ApprovalPhoneDetailDao approvalPhoneDetailDao;
	@Override
	public List<ApprovalPhoneDetail> findPhoneInfoBySearch(Integer pageNumber,
			Integer pageSize, String projectId) {
		StringBuffer jpql = new StringBuffer("");
		jpql.append("select apd from  ApprovalPhoneDetail apd where apd.projectId = ?1");
		return dynamicQuery.query(ApprovalPhoneDetail.class,jpql.toString(), Long.valueOf(projectId));
	}
	@Override
	public void saveApprovalPhoneDetail(ApprovalPhoneDetail approvalPhoneDetail) {
		approvalPhoneDetailDao.save(approvalPhoneDetail);
	}
	@Override
	public void deleteApprovalPhoneDetail(Long id) {
		approvalPhoneDetailDao.delete(id);
	}
	@Override
	public ApprovalPhoneDetail editApprovalPhoneDetail(Long id) {
		return approvalPhoneDetailDao.findOne(id);
	}
	public List<FamilyFriend> findWifeByPartyId(Long partyId){
		String sql = "select ff from FamilyFriend ff where ff.partyId = ?1 and ff.familyFriendType = '1' and ff.name is not null ";
		List<FamilyFriend> flist =  dynamicQuery.query(FamilyFriend.class,sql.toString(), partyId);
		return flist;
	}
}
