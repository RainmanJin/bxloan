package com.coamctech.bxloan.service.customermng.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import static com.coamctech.bxloan.commons.GlobalConstants.SYSOP_ROLE_NUM;

import com.coamctech.bxloan.commons.dynamicquery.DynamicQuery;
import com.coamctech.bxloan.dao.BusinessTransferHistoryDao;
import com.coamctech.bxloan.dao.CustomerManagerTeamDao;
import com.coamctech.bxloan.dao.CustomerTransferHistoryDao;
import com.coamctech.bxloan.datadict.DataDict;
import com.coamctech.bxloan.entity.BusinessTransferHistory;
import com.coamctech.bxloan.entity.CustomerManagerTeam;
import com.coamctech.bxloan.entity.CustomerTransferHistory;
import com.coamctech.bxloan.entity.EcOrgPerson;
import com.coamctech.bxloan.service.customermng.CustomerManagerService;
import com.coamctech.bxloan.service.model.customermng.CusManagerTeamSaveVO;

@Service
@Transactional
public class CustomerManagerServiceImpl implements CustomerManagerService {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private static final String MANAGER_TYPE_ADMIN = "01";
	private static final String MANAGER_TYPE_BIZ = "02";
	private static final String MANAGER_TYPE_READ = "04";
	
	@Autowired
	private CustomerManagerTeamDao customerManagerTeamDao;
	
	@Autowired
	private BusinessTransferHistoryDao businessTransferHistoryDao;
	
	@Autowired
	private CustomerTransferHistoryDao customerTransferHistoryDao;
	
	@Autowired
	private DynamicQuery dynamicQuery;
	
	@Autowired
	private DataDict dataDict;
	

	@Override
	public Page<Object[]> findCostomerManagerList(Long partyId, Integer pageNo,
			Integer pageSize) {
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT  ");
		sql.append("  cmt.CM_TEAM_ID, ");
		sql.append("  person.NAME AS PERSON_NAME, ");
		sql.append("  cmt.MANAGER_TYPE, ");
		sql.append("  code.CODE_NAME, ");
		sql.append("  cmt.ORG_CD, ");
		sql.append("  dep.NAME AS DEP_NAME ");
		sql.append(" ");
		sql.append("FROM  ");
		sql.append("  CUSTOMER_MANAGER_TEAM cmt ");
		sql.append("  LEFT JOIN EC_ORG_PERSON person ON TO_CHAR(person.ID) = cmt.USER_NUM ");
		sql.append("  LEFT JOIN CODE code ON (code.CODE_TYPE  ='ManagementType' ");
		sql.append("    AND code.CODE_VALUE = cmt.MANAGER_TYPE) ");
		sql.append("  LEFT JOIN  EC_ORG_DEPARTMENT dep ON cmt.ORG_CD      = TO_CHAR(dep.ID) ");
		sql.append("WHERE  ");
		sql.append("  cmt.PARTY_ID    = ?1");

		Pageable pagerReq = new PageRequest(pageNo, pageSize);
		Page<Object[]> result = this.dynamicQuery.nativeQuery(Object[].class, pagerReq,
				sql.toString(), partyId);
		return result;
	}
	
	@Override
	public boolean isManageCustomerPrivilege (CustomerManagerTeam cmt, String personId){
		cmt = customerManagerTeamDao.findMaxManagerTypeByCnPiUnMt(cmt.getCustomerNum(), personId, cmt.getPartyId(), "01");
		if(cmt != null && "01".equals(cmt.getManagerType())) {
			return true;
		}
		return false;
	}

	@Override
	public String removeCusManagerTeam(Long cmtId) {
		String msg = "";
		CustomerManagerTeam customerManagerTeam = customerManagerTeamDao.findByCmTeamId(cmtId);
		if(customerManagerTeam.getManagerType().equals(dataDict.getCodeVal("UserPlacing", "S1"))){
			msg = "管户权客户经理不可以删除!";
		}
		if(!"".equals(msg) && customerManagerTeam.getManagerType().equals(dataDict.getCodeVal("UserPlacing", "S3"))){
			//当只有一个风险经理时不能删除
			Integer count = customerManagerTeamDao.findCountByPartyIdAndManagerType(customerManagerTeam.getPartyId(), dataDict.getCodeVal("UserPlacing", "S3"));
			if(count == 1){
				msg = "此客户只有一个风险管理人员，不能删除!";
			}
		}
		if("".equals(msg)) {
			this.customerManagerTeamDao.delete(cmtId);
		}
		return msg;
	}
	
	@Override
	public String saveCusManagerTeam(CusManagerTeamSaveVO vo) {
		CustomerManagerTeam cmtToSave = null;
		String msg = "";
		//根据角色ID 找到绝名称
		String roleName = customerManagerTeamDao.findRoleNameById(vo.getRoleCd());
		String managerType = vo.getManagerType();
		if (managerType.equals("03")) {
			if (!roleName.equals("风险管理人员")) {
				msg = "角色不匹配！";
			}
		}
		if (managerType.equals("02") || managerType.equals("04")) {
			if (!roleName.equals("客户经理")) {
				msg = "角色不匹配！";
			}
		}
		if ("".equals(msg)) {
			String customerNum = vo.getCustomerNum();
			Integer count = customerManagerTeamDao.findCountTByCusAndUsNAndMngT(customerNum, vo.getUserNum());
			if(count > 0){
				msg = "此客户已存在所选的权限，请重新选择！";
			}
		}
		if("".equals(msg)) {
			if (vo.getCmTeamId() != null) {
				cmtToSave = this.customerManagerTeamDao.findOne(vo.getCmTeamId());
			}else{
				cmtToSave = new CustomerManagerTeam();
				cmtToSave.setCreateDate(new Date());
				cmtToSave.setStates("1");
			}
			BeanUtils.copyProperties(vo, cmtToSave);
			cmtToSave.setSysUpdateTime(new Date());
			this.customerManagerTeamDao.save(cmtToSave);
		}
		return msg;
	}

	@Override
	public String transferManagerRole(String cmtIds) {
		String[] cmtIdArray = cmtIds.split(",");
		String msg = "";
		if (cmtIdArray == null || cmtIdArray.length != 2) {
			msg = "请选择两个项目经理";
		} else {
			CustomerManagerTeam firstSel = customerManagerTeamDao.findByCmTeamId(Long.parseLong(cmtIdArray[0]));
			CustomerManagerTeam secondSel = customerManagerTeamDao.findByCmTeamId(Long.parseLong(cmtIdArray[1]));
			String firManagerType = null;
			String secManagerType = null;
			if (null != firstSel && null != secondSel) {
				if (StringUtils.isNotBlank(firstSel.getManagerType())
						&& StringUtils.isNotBlank(secondSel.getManagerType())) {
					firManagerType = firstSel.getManagerType();
					secManagerType = secondSel.getManagerType();
					if (firstSel.getManagerType().equals(
							dataDict.getCodeVal("UserPlacing", "S3"))
							|| secondSel.getManagerType().equals(
									dataDict.getCodeVal("UserPlacing", "S3"))) {
						msg = "不同角色的用户不能互换!";
					}
					if("".equals(msg) && firManagerType.equals(secManagerType)){
						msg = "请选择两个不同管理类型的客户经理用户!";
					}
					customerManagerTeamDao.roleTransfer(secManagerType, firstSel.getCmTeamId());
					customerManagerTeamDao.roleTransfer(firManagerType, secondSel.getCmTeamId());
				}
			}
		}
		
		return msg;
	}

	@Override
	public String checkRemovePrivilege(String cmtId, String userId) {
		StringBuilder sql = new StringBuilder();
		sql.append("FROM CustomerManagerTeam cmt ");
		sql.append("WHERE cmt.partyId = ");
		sql.append("  (SELECT partyId  ");
		sql.append("  FROM CustomerManagerTeam ");
		sql.append("  WHERE cmTeamId = ?1 ");
		sql.append("  ) ");
		sql.append("AND cmt.userNum = ?2");
		
		List<CustomerManagerTeam> team = this.dynamicQuery.query(
				CustomerManagerTeam.class, 
				sql.toString(), 
				new Object[]{Long.parseLong(cmtId),userId});
		if(CollectionUtils.isEmpty(team)||team.get(0)==null||
				!StringUtils.equals(team.get(0).getManagerType(),MANAGER_TYPE_ADMIN)){
			return "没有删除权限";
		}
		return "";
	}
	
	@Override
	public CusManagerTeamSaveVO getEcOrgPersonById(Integer id) {
		List<Object> params = new ArrayList<Object>();
		StringBuffer jpql = new StringBuffer("select new CusManagerTeamSaveVO(eod.name,eod.name,eop.name,eop.logname,eop.usernum,eop.orgid,eop.orgid) from EcOrgPerson eop,EcOrgDepartment eod where eop.orgid=eod.id and eop.id= ?1");
		params.add(id);
		Object obj = dynamicQuery.query(jpql.toString(), params).get(0);
		return (CusManagerTeamSaveVO) obj;
	}

	@Override
	public Page findUserBySearch(EcOrgPerson ecOrgPerson, Long orgid,
			Integer pageNumber, Integer pageSize) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder();
		sql.append("select  ");
		sql.append("       p.id userCd, ");
		sql.append("       p.logname userLogonName, ");
		sql.append("       p.name userName, ");
		sql.append("       oo.name orgName, ");
		sql.append("       p.state state, ");
		sql.append("       d.name depName, ");
		sql.append("       oo.id orgCd, ");
		sql.append("       d.id deptCd, ");
		sql.append("       p.userNum userNum, ");
		sql.append("       r.id rId, ");
		sql.append("       r.name rName, ");
		sql.append("       pr.personid || ',' || pr.deptid || ',' || pr.roleid pdrID, ");
		sql.append("       tt.id tId, ");
		sql.append("       tt.name tName ");
		sql.append("  from ec_org_personconnrole pr, ");
		sql.append("       ec_org_person         p, ");
		sql.append("       ec_org_role           r, ");
		sql.append("       ec_org_department     d, ");
		sql.append("       ec_org_department     oo, ");
		sql.append("       ec_org_department     tt ");
		sql.append(" where p.id = pr.personid(+) ");
		sql.append("   and pr.deptid = d.id(+) ");
		sql.append("   and pr.orgid = oo.id ");
		sql.append("   and pr.roleid = r.id(+) ");
		sql.append("   and pr.teamid = tt.id(+) ");
		sql.append("   and pr.state = 1 ");
		sql.append("   and p.state = 1 ");
		sql.append("   and oo.id = ?1 ");
		sql.append("   and r.role_num in ").append(SYSOP_ROLE_NUM).append(" ");
		sql.append("   and p.name like ?2");
		sql.append(" order by userCd desc ");
		params.add(orgid);
		params.add("%" + (StringUtils.isNotEmpty(ecOrgPerson.getName()) ? ecOrgPerson.getName() : "") + "%");
		return dynamicQuery.nativeQuery(new PageRequest(pageNumber - 1, pageSize), sql.toString(), params.toArray());
	}
	
	@Override
	public CusManagerTeamSaveVO getCustomerManagerTeamById(Long id) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder();
		sql.append("select t.cm_team_id cmTeamId, ");
		sql.append("       t.party_id partyId, ");
		sql.append("       t.org_cd orgCd, ");
		sql.append("       t.dept_cd deptCd, ");
		sql.append("       t.customer_num customerNum, ");
		sql.append("       p.id userNum, ");
		sql.append("       p.logname userLogonName, ");
		sql.append("       t.manager_type managerType, ");
		sql.append("       t.role_cd roleCd, ");
		sql.append("       p.name userName, ");
		sql.append("       (select d.name from ec_org_department d where d.id = t.dept_cd) depName, ");
		sql.append("       (select d.name ");
		sql.append("          from ec_org_department d, ec_org_personconnrole pc ");
		sql.append("         where d.id = pc.orgid ");
		sql.append("           and pc.orgid = t.org_cd ");
		sql.append("           and pc.personid = t.user_num" );
		sql.append("           and rownum<2) orgName ");
		sql.append("  from customer_manager_team t ");
		sql.append("  left join ec_org_person p ");
		sql.append("    on p.id = t.user_num ");
		sql.append(" where t.cm_team_id = ?1 ");
		params.add(id);
		return new CusManagerTeamSaveVO(dynamicQuery.nativeQuery(sql.toString(), params).get(0));
	}

	@Override
	public String handOverValid(String cmtIds) {
		String msgTotal = "";//返回值
		String[] cmtIdArray = null;
		if (cmtIds.indexOf(",") > -1) {
			cmtIdArray = cmtIds.split(",");
		} else {
			cmtIdArray = new String[1];
			cmtIdArray[0] = cmtIds;
		}
		for (int i = 0; i < cmtIdArray.length; i++) {
			Long cmtId = Long.parseLong(cmtIdArray[i]);
			CustomerManagerTeam customerManagerTeam = customerManagerTeamDao.findByCmTeamId(cmtId);
			String msg = "";// 异常信息
			if(customerManagerTeam == null) {
				msg = "不存在该客户，无法移交";
			} else if(customerManagerTeam.getPartyId() == null) {
				msg = "不存在该客户，无法移交";
			} else {
				String userNum = customerManagerTeam.getUserNum();//用户编号
				String customerNum = customerManagerTeam.getCustomerNum();//客户编号
				Long partyId = customerManagerTeam.getPartyId();
				msg = "客户编号：" + customerNum;
				String temporary = "";
				if(!isProjectLocancancleStatue(partyId, userNum)) {
					msg += "合同状态为核销中的不能移交,请确认!";
				} else if (!isContractRepayLoanStatue(partyId, userNum)) {
					msg += "提前还款流程未完成不能移交,请确认!";
				} else if (!(temporary = isContractStatus(partyId, userNum)).equals("")) {
					msg += temporary;
				} else if (!(temporary = isProjectApplicationStatus(partyId, userNum)).equals("")) {
					msg += temporary;
				} else {
					msg = "";
				}
			}
			msgTotal += msg;
		}
		return msgTotal;
	}
	
	private boolean isProjectLocancancleStatue(Long partyId, String userNum) {
		
		String state = dataDict.getCodeVal("ProjectStatus", "S5");
		String sql = "select pa.project_id,pa.project_no from project_application pa where pa.applicant='" + userNum + "' and pa.party_id=" + partyId + " and pa.project_status=" + state;
		List<Object[]> arrList = dynamicQuery.nativeQuery(sql, new ArrayList<Object>().toArray());
		if(arrList != null && arrList.size() > 0) {
			for (int i = 0; i < arrList.size(); i++) {
				Object[] objArr = arrList.get(i);
				state = dataDict.getCodeVal("LoanCancleStateCD", "S1");
				sql = "select * from loan_cancle lc where lc.project_id = '" + objArr[0] + "' and lc.party_id ='" + partyId + "'  and loan_cancle_state_cd =" + state;
				List<Object[]> loanCanclelist = dynamicQuery.nativeQuery(sql, new ArrayList<Object>().toArray());
				if (loanCanclelist != null && loanCanclelist.size() > 0) {
					//合同状态为核销中的不能移交,请确认!
					return false;
				}
			}
		}
		return true;
	}
	
	private boolean isContractRepayLoanStatue(Long partyId, String userNum) {
		StringBuffer state = new StringBuffer();
		state.append("'").append(dataDict.getCodeVal("ContractStatusCode", "S5")).append("',");//合同状态 已核销,否决,已结清 不移交不验证
		state.append("'").append(dataDict.getCodeVal("ContractStatusCode", "S6")).append("',");//合同状态 已核销,否决,已结清 不移交不验证
		state.append("'").append(dataDict.getCodeVal("ContractStatusCode", "S7")).append("',");//合同状态 已核销,否决,已结清 不移交不验证
		state.append("'").append(dataDict.getCodeVal("ContractStatusCode", "S8")).append("',");//合同状态为审批中、放款中
		state.append("'").append(dataDict.getCodeVal("ContractStatusCode", "S9")).append("'");//合同状态为审批中、放款中
		
		String sql = "select contract_num, contract_id from contract c where c.apply_user_num='" + userNum + "' and c.party_id=" + partyId + " and c.contract_status_cd not in(" + state.toString() + ")";
		List<Object[]> arrList = dynamicQuery.nativeQuery(sql, new ArrayList<Object>().toArray());
		if(arrList != null && arrList.size() > 0) {
			for (int i = 0; i < arrList.size(); i++) {
				Object[] objArr = arrList.get(i);
				sql = "select repayment_status_cd, clean_cut_cd, valid from repay_loan where contract_num = '" + objArr[0] + "' and clean_cut_cd =" + dataDict.getCodeVal("ContractStatusCode", "S2");
				List<Object[]> repayLoanList = dynamicQuery.nativeQuery(sql, new ArrayList<Object>().toArray());
				if (repayLoanList != null && repayLoanList.size() > 0) {
					//合同状态为核销中的不能移交,请确认!
					String back = dataDict.getCodeVal("PayLoanStatus", "S3"); // 账务退单
					String reverseBack = dataDict.getCodeVal("PayLoanStatus", "S5"); // 账务冲销入账
					for (int int_i = 0; int_i < repayLoanList.size(); int_i++) {
						Object[] repayLoan = repayLoanList.get(int_i);
						if (!back.equals(repayLoan[0]) && !reverseBack.equals(repayLoan[0])) {
							if ((dataDict.getCodeVal("CleanCutCode", "S2").equals(repayLoan[1]) || 
									dataDict.getCodeVal("CleanCutCode", "S3").equals(repayLoan[1])) && 
									dataDict.getCodeVal("CtrlIndicator", "S2").equals(repayLoan[2])) {//提前还款还在流程中
								return false;
							}
						}
					}
				}
			}
		}
		return true;
	}
	
	private String isContractStatus(Long partyId, String userNum) {
		
		StringBuffer state = new StringBuffer();
		state.append("'").append(dataDict.getCodeVal("ContractStatusCode", "S8")).append("',");//合同状态为审批中、放款中不能移交
		state.append("'").append(dataDict.getCodeVal("ContractStatusCode", "S9")).append("'");//合同状态为审批中、放款中不能移交
		
		String sql = "select contract_num, contract_id, contract_status_cd from contract c where c.apply_user_num='" + userNum + "' and c.party_id=" + partyId + " and c.contract_status_cd in(" + state.toString() + ")";
		
		List<Object[]> contranctlist = dynamicQuery.nativeQuery(sql, new ArrayList<Object>().toArray());
		if(contranctlist != null && contranctlist.size() > 0) {
			Object[] objArr = contranctlist.get(0);
			return getStateProReturnMsg(Integer.parseInt(String.valueOf(objArr[2])));
		}
		return "";
	}
	
	private String isProjectApplicationStatus(Long partyId, String userNum) {
		
		StringBuffer state = new StringBuffer();
		state.append("'").append(dataDict.getCodeVal("ProjectStatus", "S0")).append("',");//项目状态 未提交、已提交、已批复的不能移交
		state.append("'").append(dataDict.getCodeVal("ProjectStatus", "S1")).append("',");//项目状态 未提交、已提交、已批复的不能移交
		state.append("'").append(dataDict.getCodeVal("ProjectStatus", "S2")).append("'");//项目状态 未提交、已提交、已批复的不能移交
		
		String sql = "select pa.project_id,pa.project_no, pa.project_status from project_application pa where pa.applicant='" + userNum + "' and pa.party_id=" + partyId + " and pa.project_status in(" + state + ")";
		
		List<Object[]> projectlist = dynamicQuery.nativeQuery(sql, new ArrayList<Object>().toArray());
		if(projectlist != null && projectlist.size() > 0) {
			Object[] objArr = projectlist.get(0);
			return getStateProReturnMsg(Integer.parseInt(String.valueOf(objArr[2])));
		}
		return "";
	}
	
	// 根据项目状态值得到提示信息
	private String getStateProReturnMsg(Integer state) {
		String mesg = "";
		switch (state) {
		case 0:
			mesg = " 项目状态:未提交 不能移交,请确认!";
			break;
		case 1:
			mesg = " 项目状态:已提交 不能移交,请确认!";
			break;
		case 2:
			mesg = " 项目状态:已批复 不能移交,请确认!";
			break;
		case 3:
			mesg = " 项目状态:中止  不能移交,请确认!";
			break;
		case 4:
			mesg = " 项目状态:否决 不能移交,请确认!";
			break;
		case 331:
			mesg = " 合同状态:中止 不能移交,请确认!";
			break;
		case 500:
			mesg = " 合同状态:否决 不能移交,请确认!";
			break;
		case 521:
			mesg = " 合同状态:审批中 不能移交,请确认!";
			break;
		case 423:
			mesg = " 合同状态:放款中 不能移交,请确认!";
			break;
		}
		return mesg;
	}

	@Override
	public void setCustomerManagerTeam(String cmTeamIds,
			CusManagerTeamSaveVO cusManagerTeamSaveVO, String loginId) {
		String userNum = cusManagerTeamSaveVO.getUserNum();//用户Id
		String did = cusManagerTeamSaveVO.getDeptCd();//部门Id
		//String pnum = cusManagerTeamSaveVO.getUserLogonName();//登录名
		//String perName = cusManagerTeamSaveVO.getUserName();//用户名

		String[] arg = null;
		if (cmTeamIds.indexOf(",") > -1) {
			arg = cmTeamIds.split(",");
		} else {
			arg = new String[1];
			arg[0] = cmTeamIds;
		}
		
		String manageType = null;
		CustomerManagerTeam custmanage = null;
		CustomerManagerTeam custmanage2 = null;
		Long partyId = null;
		for (String str : arg) {
			if (StringUtils.isNotBlank(str)) {
				// 修改管理团队
				custmanage = customerManagerTeamDao.findByCmTeamId(Long.parseLong(str));
				partyId = custmanage.getPartyId();
				// 修改业务相关数据
				handleCustomerBizDateprocess(custmanage, userNum.toString(), loginId);// 修改业务相关数据
				if(manageType == null){
					manageType = custmanage.getManagerType();
					custmanage2 = custmanage;
				}else{
					if(dataDict.getCodeVal("ManagementType", "S2").equals(manageType) && dataDict.getCodeVal("ManagementType", "S1").equals(custmanage.getManagerType())){
						manageType = custmanage.getManagerType();
						custmanage2 = custmanage;
					}else if(dataDict.getCodeVal("ManagementType", "S4").equals(manageType)){
						manageType = custmanage.getManagerType();
						custmanage2 = custmanage;
					}
				}
			}
		}
		//去除移交权限级别低的
		for(String stt2:arg){
			if (StringUtils.isNotBlank(stt2)) {
				CustomerManagerTeam custmanage_temp = customerManagerTeamDao.findByCmTeamId(Long.parseLong(stt2));
				if(!custmanage2.getManagerType().equals(custmanage_temp.getManagerType())){
					customerManagerTeamDao.delete(custmanage_temp);
				}
			}
		}
		//移交管理团队
		modCustomerManagerTeam(userNum, partyId, custmanage2, did.toString(), loginId);
	}
	/***
	 *移交管理团队信息 
	 * 
	 */
	private void modCustomerManagerTeam(String userNum, Long partyId, CustomerManagerTeam custmanage, String did, String loginId) {
		
		String managerType = custmanage.getManagerType();//接受的移交权限
		CustomerTransferHistory cth = new CustomerTransferHistory();
		cth.setNewUser(userNum);
		cth.setOldUser(custmanage.getUserNum());
		
		List<CustomerManagerTeam> retulist = customerManagerTeamDao.findCMTByPartyIdAndUserNum(partyId, userNum);
		if (retulist != null && retulist.size() > 0) {
			for(CustomerManagerTeam cmt : retulist){
				if (dataDict.getCodeVal("ManagementType", "S1").equals(cmt.getManagerType())) {
					String  mtype = "'"+dataDict.getCodeVal("ManagementType", "S1")+"','" + dataDict.getCodeVal("ManagementType", "S2")+"','" + dataDict.getCodeVal("ManagementType", "S4")+"'";
					isManagerType(userNum, partyId, mtype);
					custmanage.setManagerType(dataDict.getCodeVal("ManagementType", "S1"));
					break;
				} else if(dataDict.getCodeVal("ManagementType", "S2").equals(cmt.getManagerType())){
					String  mtype = "'"+dataDict.getCodeVal("ManagementType", "S4")+"','" + dataDict.getCodeVal("ManagementType", "S2")+"'";
					isManagerType(userNum, partyId, mtype);
					if(dataDict.getCodeVal("ManagementType", "S4").equals(managerType)){
					     custmanage.setManagerType(dataDict.getCodeVal("ManagementType", "S2"));
					}
				}else{
					isManagerType(userNum, partyId, cmt.getManagerType());
				}
			}
		}
		custmanage.setUserNum(userNum.toString());
		custmanage.setDeptCd(did);
		customerManagerTeamDao.save(custmanage);
		
		cth.setOperator(loginId);
		cth.setOperateTime(new Date());
		customerTransferHistoryDao.save(cth);
	}
	
	/**
	 * 判断移交的权限
	 */
	private void isManagerType(String userNum, Long partyId, String mtype) {
		List<CustomerManagerTeam> retulist = customerManagerTeamDao.findCMTByPartyIdAndUserNumAndManagerType(partyId, userNum, mtype);
		if (retulist != null && retulist.size() > 0) {
			for (CustomerManagerTeam cmt : retulist) {
				customerManagerTeamDao.delete(cmt);
			}
		}
	}
	
	/**
	 * 功能：客户移交，业务数据的处理
	 * @param custmanage
	 * @param userNum
	 * @param perName
	 * @param pnum
	 */
	private void handleCustomerBizDateprocess(CustomerManagerTeam custmanage,String userNum,String operator) {
		Long partyId = custmanage.getPartyId();
		//合同状态可以移交
		StringBuffer contranctState = new StringBuffer("'");
		contranctState.append(dataDict.getCodeVal("ContractStatusCode", "S1")).append("'");
		contranctState.append(",'").append(dataDict.getCodeVal("ContractStatusCode", "S2")).append("'");
		contranctState.append(",'").append(dataDict.getCodeVal("ContractStatusCode", "S3")).append("'");
		
		//项目状态合同签订
		StringBuffer projectState = new StringBuffer("'");
		projectState.append(dataDict.getCodeVal("ProjectStatus", "S5")).append("'");
		
		StringBuffer sql = new StringBuffer();
		sql.append("select c.contract_id,p.project_id,p.bp_info_id ");
		sql.append("from project_application p, contract c ");
		sql.append("where p.project_id = c.project_id ");
		sql.append("	  and p.project_status in (").append(projectState).append(") ");
		sql.append("	  and c.contract_status_cd in (").append(contranctState).append(") ");
		sql.append("	  and not exists (select 1 from loan_cancle l where l.contract_id = c.contract_id and l.loan_cancle_state_cd = ").append(dataDict.getCodeVal("LoanCancleStateCD", "S1")).append(") ");
		sql.append("	  and p.applicant = '").append(custmanage.getUserNum()).append("' ");
		sql.append("	  and p.party_id = '").append(partyId).append("' ");
		
		List<Object[]> listProjectId = dynamicQuery.nativeQuery(sql.toString(), new ArrayList<Object>().toArray());
		logger.info("需要移交的业务笔数：" + listProjectId.size() + "(笔)");
		if (listProjectId != null) {
			String projectId = null;
			String bpInfoId = null;
			String contractId = null;
			for (int i = 0; i < listProjectId.size(); i++) {
				Object[] objArr = listProjectId.get(i);
				contractId =  objArr[0].toString();
				projectId = objArr[1].toString();
				bpInfoId =  objArr[2].toString();
				//处理业务数据
				customerManagerTeamDao.projectApplication(userNum, userNum, projectId, partyId.toString());
				save(projectId, null, custmanage.getUserNum(), userNum, operator, "1", "项目");
				//处理合同数据
				customerManagerTeamDao.contract(userNum, projectId, partyId.toString());
				save(null, contractId, custmanage.getUserNum(), userNum, operator, "2", "合同");
				//处理放款记录
				customerManagerTeamDao.payLoanInfo(userNum, contractId);
				//处理单据数据
				customerManagerTeamDao.accountingInfo(userNum, contractId);
				//处理还款计划数据
				customerManagerTeamDao.repayingPlan(userNum, contractId);
				//还款计划明细
				customerManagerTeamDao.repayingPlanDetail(userNum, contractId);
				//处理任务列表
				//customerManagerTeamDao.pendingForm(userNum, projectId, custmanage.getUserNum());
			}
		}
	}
	
	private void save(String projectId, String contractId, String oldUser, String newUser, String operator, String type, String typeName) {
		BusinessTransferHistory bth = new BusinessTransferHistory();
		bth.setProjectId(projectId);
		bth.setContractId(contractId);
		bth.setOldUser(oldUser);
		bth.setNewUser(newUser);
		bth.setOperator(operator);
		bth.setType(type);
		bth.setOperateTime(new Date());
		bth.setTypeName(typeName);
		businessTransferHistoryDao.save(bth);
	}
	
	public List<EcOrgPerson> getCoCustomerManager(Long orgId,Long roleId,Long personId,Integer roleState,Integer personState){
		return customerManagerTeamDao.getCoCustomerManager(orgId,roleId,personId,roleState,personState);
	}
}
