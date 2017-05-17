package com.coamctech.bxloan.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.coamctech.bxloan.entity.CustomerManagerTeam;
import com.coamctech.bxloan.entity.EcOrgPerson;

public interface CustomerManagerTeamDao extends
		PagingAndSortingRepository<CustomerManagerTeam, Long>,
		JpaSpecificationExecutor<CustomerManagerTeam> {

	CustomerManagerTeam findByCmTeamId(Long cmTeamId);

	CustomerManagerTeam findByPartyId(Long partyId);

	CustomerManagerTeam findByCustomerNum(String customerNum);

	@Query(value = "select MANAGER_TYPE from CUSTOMER_MANAGER_TEAM where PARTY_ID = ?1 and USER_NUM = ?2", nativeQuery = true)
	String findManagerTypeByPartyIdAndUserNum(Long partyId, String userNum);
	
	@Query(value = "select name from ec_org_role where id = ?1", nativeQuery = true)
	String findRoleNameById(String roleCd);
	
	@Query("select cmt from CustomerManagerTeam cmt where partyId = ?1 and userNum = ?2")
	List<CustomerManagerTeam> findCMTByPartyIdAndUserNum(Long partyId, String userNum);
	
	@Query("select cmt from CustomerManagerTeam cmt where partyId = ?1 and userNum = ?2 and managerType in(?3)")
	List<CustomerManagerTeam> findCMTByPartyIdAndUserNumAndManagerType(Long partyId, String userNum, String managerType);
	
	@Query(value = "select count(*) from customer_manager_team where party_id = ?1 and manager_type = ?2", nativeQuery = true)
	Integer findCountByPartyIdAndManagerType(Long partyId, String managerType);
	
	@Query(value = "select count(*) from customer_manager_team where customer_num = ?1 and user_num = ?2", nativeQuery = true)
	Integer findCountTByCusAndUsNAndMngT(String customerNum, String userNum);
	
	@Query("select cmt from CustomerManagerTeam cmt where customerNum = ?1 and userNum = ?2 and partyId = ?3 and managerType = ?4")
	CustomerManagerTeam findMaxManagerTypeByCnPiUnMt(String customerNum, String userNum, Long partyId, String managerType);
	
	/**
	 * 功能描述：查找当前用户登录机构下除本人外其他客户经理的信息
	 * @author wangpeng
	 * @date 2015-07-21
	 * @return
	 */
	@Query(" select distinct new EcOrgPerson(eop.name,eop.id)  from EcOrgPersonconnrole epr,EcOrgPerson eop  "
			+ "where  epr.id.personid = eop.id and eop.orgid=?1 and epr.id.roleid=?2 and eop.id!=?3 and epr.state=?4 and eop.state=?5 ")
	public List<EcOrgPerson> getCoCustomerManager(Long orgId,Long roleId,Long personId,Integer roleState,Integer personState);
	
	@Modifying
	@Query("update CustomerManagerTeam cmt set cmt.managerType = ?1 where cmt.cmTeamId = ?2")
	void roleTransfer(String managerType , Long cmTeamId);
	
	/** 移交业务 */
	@Modifying
	@Query(value = "update project_application pa set pa.customer_manager_num=?1,pa.applicant=?2 where pa.project_id=?3 and pa.party_id=?4", nativeQuery = true)
	void projectApplication(String customerManagerNum,String applicant, String projectId, String partyId);
	
	@Modifying
	@Query(value = "update contract c set c.apply_user_num=?1 where c.project_id=?2 and c.party_id=?3", nativeQuery = true)
	void contract(String applyUserNum,String projectId, String partyId);
	
	@Modifying
	@Query(value = "update pay_loan_info p set p.apply_user_num=?1 where p.contract_id=?2", nativeQuery = true)
	void payLoanInfo(String applyUserNum,String contractId);
	
	@Modifying
	@Query(value = "update accounting_info ai set ai.lastmoduser=?1 where ai.contractid=?2", nativeQuery = true)
	void accountingInfo(String lastmoduser,String contractid);
	
	@Modifying
	@Query(value = "update repaying_plan rp set rp.operator_user=?1 where rp.contract_id=?2", nativeQuery = true)
	void repayingPlan(String operatorUser,String contractId);
	
	@Modifying
	@Query(value = "update repaying_plan_detail rpd set rpd.operator_user=?1 where rpd.repaying_plan_id in(select rp.repaying_plan_id from repaying_plan rp where rp.contract_id=?2)", nativeQuery = true)
	void repayingPlanDetail(String operatorUser,String contractId);
	
	@Modifying
	@Query(value = "update wf_pending p set p.ownerid=(select eop.logname from ec_org_person eop where eop.id=?1) where p.businessobjectid=?2 and p.ownerid = (select eop.logname from ec_org_person eop where eop.id=?3)", nativeQuery = true)
	void pendingForm(String toUserNum,String businessobjectid, String fromUserNum);
}
