package com.coamctech.bxloan.service.customermng;

import java.util.List;

import org.springframework.data.domain.Page;

import com.coamctech.bxloan.entity.CustomerManagerTeam;
import com.coamctech.bxloan.entity.EcOrgPerson;
import com.coamctech.bxloan.service.model.customermng.CusManagerTeamSaveVO;

public interface CustomerManagerService {
	
	Page<Object[]> findCostomerManagerList(Long partyId, Integer pageNo, Integer pageSize);

	String removeCusManagerTeam(Long cmtId);

	String saveCusManagerTeam(CusManagerTeamSaveVO vo);

	/**
	 * 检查是否有删除权限
	 * @param cmtId
	 * @param userId
	 * @return
	 * @author xc
	 */
	String checkRemovePrivilege(String cmtId, String userId);

	CusManagerTeamSaveVO getEcOrgPersonById(Integer id);

	Page findUserBySearch(EcOrgPerson ecOrgPerson, Long orgid, Integer pageNumber, Integer pageSize);

	CusManagerTeamSaveVO getCustomerManagerTeamById(Long id);

	String handOverValid(String cmtIds);

	void setCustomerManagerTeam(String cmTeamIds, CusManagerTeamSaveVO cusManagerTeamSaveVO, String loginId);
	
	String transferManagerRole(String cmtIds);

	boolean isManageCustomerPrivilege(CustomerManagerTeam cmt, String personId);
	
	List<EcOrgPerson> getCoCustomerManager(Long orgId,Long roleId,Long personId,Integer roleState,Integer personState);
}
