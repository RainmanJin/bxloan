package com.coamctech.bxloan.service.dashboard.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coamctech.bxloan.commons.GlobalConstants;
import com.coamctech.bxloan.commons.dynamicquery.DynamicQuery;
import com.coamctech.bxloan.dao.EcFunctiontreenodeDao;
import com.coamctech.bxloan.dao.TbMessageDao;
import com.coamctech.bxloan.entity.EcFunctiontreenode;
import com.coamctech.bxloan.entity.TbMessage;
import com.coamctech.bxloan.service.dashboard.DashboardService;

@Transactional
@Service
public class DashboardServiceImpl implements DashboardService {

	@Autowired
	private DynamicQuery dynamicQuery;
	@Autowired
	private EcFunctiontreenodeDao ecFunctiontreenodeDao;
	@Autowired
	private TbMessageDao msgDao;
	
	private static final Long MSG_ALREADY_READ = 1L;
	
	@Override
	public List<EcFunctiontreenode> getMenusByPermission(Long personid,
			Long orgid) {
		return ecFunctiontreenodeDao.findMenusByPermission(personid, orgid);
	}

	
	@Override
	@SuppressWarnings("serial")
	public Page findAllMessageList(String applicant, String orgId,
			 Integer pageNumber, Integer pageSize) {
//		StringBuffer sql = new StringBuffer();
//		sql.append("select '合同到期提醒' MessageTypeName, '5' MessageTypeCd, count(*) as count_num , '1' operation from ");
//		sql.append("TB_MESSAGE tm where tm.STATE='0' and tm.TYPE='5' and tm.RECEIVER=?1");
//		sql.append(" union ");
//		
//		sql.append("select '审批否决通知' MessageTypeName, '6' MessageTypeCd, count(*) as count_num , '1' operation from ");
//		sql.append("TB_MESSAGE tm where tm.STATE='0' and tm.TYPE='6' and tm.RECEIVER=?1");
//		sql.append(" union ");
//		/*
//		sql.append("select '提前还款通过' MessageTypeName, '11' MessageTypeCd, count(*) as count_num , '1' operation from ");
//		sql.append("TB_MESSAGE tm where tm.STATE='0' and tm.TYPE='11' and tm.RECEIVER=?1");
//		sql.append(" union ");
//		
//		sql.append("select '逾期客户通知' MessageTypeName, '8' MessageTypeCd, count(*) as count_num , '1' operation from ");
//		sql.append("TB_MESSAGE tm where tm.STATE='0' and tm.TYPE='8' and tm.RECEIVER=?1");
//		sql.append(" union ");
//		
//		sql.append("select '首次信贷资产检查通知' MessageTypeName, '9' MessageTypeCd, count(*) as count_num , '1' operation from ");
//		sql.append("TB_MESSAGE tm where tm.STATE='0' and tm.TYPE='9' and tm.RECEIVER=?1");
//		sql.append(" union ");
//		
//		sql.append("select '退单提醒' MessageTypeName, '13,14,15' MessageTypeCd, count(*) as count_num , '1' operation from ");
//		sql.append("TB_MESSAGE tm where tm.STATE='0' and (tm.TYPE in ('13' , '14' , '15')) ");
//		sql.append("and tm.RECEIVER=?1");
//		sql.append(" union ");
//		
//		sql.append("select '提前还款不通过' MessageTypeName, '12' MessageTypeCd, count(*) as count_num , '1' operation from ");
//		sql.append("TB_MESSAGE tm where tm.STATE='0' and tm.TYPE='12' and tm.RECEIVER=?1");
//		sql.append(" union ");
//		
//		sql.append("select '合同审核不通过' MessageTypeName, '16' MessageTypeCd, count(*) as count_num , '1' operation from ");
//		sql.append("TB_MESSAGE tm where tm.STATE='0' and tm.TYPE='16' and tm.RECEIVER=?1");
//		sql.append(" union ");
//		
//		sql.append("select  '信贷资产检查(月、季)' MessageTypeName, '18' MessageTypeCd, count(*) as count_num , '1' operation from ");
//		sql.append("TB_MESSAGE tm where tm.STATE='0' and tm.TYPE='18' and tm.RECEIVER=?1");
//		sql.append(" union ");
//		
//		sql.append("select '核销审核不通过' MessageTypeName, '17' MessageTypeCd, count(*) as count_num , '1' operation from ");
//		sql.append("TB_MESSAGE tm where tm.STATE='0' and tm.TYPE='17' and tm.RECEIVER=?1");
//		sql.append(" union ");
//		
//		sql.append("select '合同审核通过' MessageTypeName, '19' MessageTypeCd, count(*) as count_num , '1' operation from ");
//		sql.append("TB_MESSAGE tm where tm.STATE='0' and tm.TYPE='19' and tm.RECEIVER=?1");
//		sql.append(" union ");
//		*/
//		sql.append("select '还款到期通知' MessageTypeName, '21' MessageTypeCd, count(*) as count_num , '1' operation from ");
//		sql.append("TB_MESSAGE tm where tm.STATE='0' and tm.TYPE='21' and tm.RECEIVER=?1");
//		sql.append(" union ");
//		/*
//		sql.append("select '贷款展期审核不通过' MessageTypeName, '20' MessageTypeCd, count(*) as count_num , '1' operation from ");
//		sql.append("TB_MESSAGE tm where tm.STATE='0' and tm.TYPE='20' and tm.RECEIVER=?1");
//		sql.append(" union ");
//		
//		sql.append("select '借据上传通知' MessageTypeName, '22' MessageTypeCd, count(*) as count_num , '1' operation from ");
//		sql.append("TB_MESSAGE tm where tm.STATE='0' and tm.TYPE='22' and tm.RECEIVER=?1");
//		sql.append(" union ");
//		*/
//		sql.append("select '审批通过通知' MessageTypeName, '23' MessageTypeCd, count(*) as count_num , '1' operation from ");
//		sql.append("TB_MESSAGE tm where tm.STATE='0' and tm.TYPE='23' and tm.RECEIVER=?1");
//		sql.append(" union ");
//		
//		sql.append("select '系统公告' MessageTypeName, '1' MessageTypeCd, count(*) as count_num , '1' operation from ");
//		sql.append("TB_MESSAGE tm where tm.STATE='0' and tm.TYPE='1' and tm.RECEIVER=?1");
//		List<Object> params = new ArrayList<Object>();
//		params.add(applicant);
//		return dynamicQuery.nativeQuery(new PageRequest(pageNumber - 1,
//				pageSize), sql.toString(), params.toArray());
		
		
		List<Long> searchMsgTypeCds = new ArrayList<Long>(){{
			add(GlobalConstants.MSG_TYPE_CONTRACT_TIME_UP);//合同到期提醒
			add(GlobalConstants.MSG_TYPE_CANCEL);//审批否决通知
			add(GlobalConstants.MSG_TYPE_REPAY_TIME_UP);//还款到期通知
			add(GlobalConstants.MSG_TYPE_APPROVAL_FINISH);//审批通过通知
			add(GlobalConstants.MSG_TYPE_SYSTEM_MSG);//系统公告
			add(GlobalConstants.MSG_TYPE_SEND_BACK);//消息被退回
		}};
		
		List<Object> params = new ArrayList<Object>();
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT c.CODE_NAME, ");
		sql.append("  c.CODE_VALUE, ");
		sql.append("  COUNT(tm.TYPE), ");
		sql.append("  '' ");
		sql.append("FROM TB_MESSAGE tm ");
		sql.append("RIGHT JOIN code c ");
		sql.append("ON (c.code_value   = TO_CHAR(tm.TYPE) ");
		sql.append("AND tm.IF_ALREADY <>1 ");
		sql.append("AND tm.RECEIVER    =?1 ) ");
		sql.append("WHERE c.CODE_TYPE  = 'MessageType' ");
		sql.append("AND c.code_value  IN ?2 ");
		sql.append("GROUP BY c.CODE_VALUE, ");
		sql.append("  c.CODE_NAME ");
		sql.append("ORDER BY c.code_value");
		
		params.add(applicant);
		params.add(searchMsgTypeCds);

		return this.dynamicQuery.nativeQuery(new PageRequest(pageNumber - 1,
				pageSize), sql.toString(), params.toArray());
		
	}
	
	private String endDatePlus(String originDate){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date d = null;
		try {
			d = sdf.parse(originDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return sdf.format(DateUtils.addDays(d, 1));
	}
	
	@SuppressWarnings("unchecked")
	public Page<Object[]> findTodoList(String search, String curUser, String taskDesignator, String taskCreateTimeStart,
			String taskCreateTimeEnd, Integer pageNumber, Integer pageSize) {
		
		List<Object> params = new ArrayList<Object>();
		StringBuilder jpql = new StringBuilder();
		
		jpql.append("SELECT ");
		jpql.append("td.taskId,td.taskSubject,");
		jpql.append("td.taskSubmitTime,td.taskStageName,");
		jpql.append("td.appVar2,pa.projectId,");
		jpql.append("pa.customerName,pa.customerManagerName,td.appVar3 ");
		
		jpql.append("from TaskTodoList td,ProjectApplication pa ");
		jpql.append("WHERE td.appVar2=pa.workflowId ");
		
		jpql.append("AND td.taskKind in (").append("30").append(",")
		.append("40").append(") ");
		jpql.append(" AND td.taskAppState in (80,81) AND td.taskAssigneer = '"+curUser+"'");
		int paramsCount = 1;
		
		if (StringUtils.isNotEmpty(taskDesignator)) {
			jpql.append(" AND pa.customerManagerName LIKE?" + paramsCount++);
			params.add("%"+taskDesignator+"%");
		}
		if (StringUtils.isNotEmpty(taskCreateTimeStart)) {
			jpql.append(" AND td.taskCreateTime >= ?" + paramsCount++);
			params.add(taskCreateTimeStart);
		}
		if (StringUtils.isNotEmpty(taskCreateTimeEnd)) {
			jpql.append(" AND td.taskCreateTime <= ?" + paramsCount++);
			params.add(endDatePlus(taskCreateTimeEnd));
		}
		jpql.append(" ORDER BY td.taskCreateTime DESC ");
		
		return dynamicQuery.query(new PageRequest(pageNumber - 1,
				pageSize), jpql.toString(), params.toArray());
	}
	
	
	@SuppressWarnings("unchecked")
	public Page<Object[]> findDoneList(String search, String curUser,
			String taskDesignator, String taskCreateTimeStart,
			String taskCreateTimeEnd, String taskSubmitTimeStart,
			String taskSubmitTimeEnd, Integer pageNumber, Integer pageSize) {
		
		List<Object> params = new ArrayList<Object>();
		StringBuilder jpql = new StringBuilder();
		
		jpql.append("SELECT ");
		jpql.append("td.taskId,td.taskSubject,");
		jpql.append("td.taskSubmitTime,td.taskStageName,");
		jpql.append("td.appVar2,pa.projectId,");
		jpql.append("pa.customerName,pa.customerManagerName,td.appVar3,td.taskSubmitTime ");
		
		jpql.append("from TaskTodoList td,ProjectApplication pa ");
		jpql.append("WHERE td.appVar2=pa.workflowId ");
		
		jpql.append("AND td.taskKind in (").append("30").append(",")
		.append(GlobalConstants.WD_WF_TYPE_40).append(") ");
		jpql.append(" and td.taskAppState=82 and td.taskAssigneer = '"+curUser+"'");
		int paramsCount = 1;
		
		if (StringUtils.isNotEmpty(taskDesignator)) {
			jpql.append(" and pa.customerManagerName LIKE?" + paramsCount++);
			params.add("%"+taskDesignator+"%");
		}
		if (StringUtils.isNotEmpty(taskCreateTimeStart)) {
			jpql.append(" and td.taskCreateTime >= ?" + paramsCount++);
			params.add(taskCreateTimeStart);
		}
		if (StringUtils.isNotEmpty(taskCreateTimeEnd)) {
			jpql.append(" and td.taskCreateTime <= ?" + paramsCount++);
			params.add(endDatePlus(taskCreateTimeEnd));
		}
		if (StringUtils.isNotEmpty(taskSubmitTimeStart)) {
			jpql.append(" and td.taskSubmitTime >= ?" + paramsCount++);
			params.add(taskSubmitTimeStart);
		}
		if (StringUtils.isNotEmpty(taskSubmitTimeEnd)) {
			jpql.append(" and td.taskSubmitTime <= ?" + paramsCount++);
			params.add(endDatePlus(taskSubmitTimeEnd));
		}
		jpql.append(" order by td.taskSubmitTime desc ");
		
		return dynamicQuery.query(new PageRequest(pageNumber - 1,
				pageSize), jpql.toString(), params.toArray());
		
	}
	//////////////////////////
	////////////////////////////////
	
	
	
	
	@SuppressWarnings("rawtypes")
	public Page findTaskList(String taskFlag, String applicant, String orgId,
			String search, Integer pageNumber, Integer pageSize) {
		StringBuffer sql = new StringBuffer();
		
		sql.append("select pa.project_id ,pa.project_no , pa.product_type , pa.customer_name , pa.customer_manager_name , pa.party_id, '步骤名称' wf_name ,  pa.sys_create_time, '1' operation  ");
		sql.append(" from Project_Application pa where 1=1 ");
		sql.append(" and pa.project_status =?1 and pa.applicant = ?2 ");
		sql.append(" and pa.apply_org =?3");
		List<Object> params = new ArrayList<Object>();
		params.add("0");
		params.add(applicant);
		params.add(orgId);
		if(StringUtils.isNotEmpty(search)) {
			sql.append(" and  pa.customer_name like ?4 or pa.project_no like ?4");
			params.add("%" + search + "%");
		}
		sql.append(" order by pa.sys_create_time desc ");
		
		return dynamicQuery.nativeQuery(new PageRequest(pageNumber - 1,
				pageSize), sql.toString(), params.toArray());

	}

	

	@SuppressWarnings("unchecked")
	@Override
	public Page<Object[]> findOneTypeMessageList(String messFlag, String receiver,
			 Integer pageNumber, Integer pageSize) {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT ");
		sql.append("	tm.ID, ");
		sql.append("	tm.SENDERNAME, ");
		sql.append("	tm.SENDTIME, ");
		sql.append("	tm.CONTENT ");
		sql.append(" from TB_MESSAGE tm ");
		sql.append(" WHERE tm.IF_ALREADY <>1 ");
		sql.append("	AND tm.TYPE = ?1 ");
		sql.append("	AND tm.RECEIVER = ?2 ");
		sql.append("	ORDER BY tm.SENDTIME DESC ");
		List<Object> params = new ArrayList<Object>();
		params.add(messFlag);
		params.add(receiver);
		
		return  dynamicQuery.nativeQuery(new PageRequest(pageNumber - 1,
				pageSize), sql.toString(), params.toArray());
	
	}

	public List<Object[]> countRepayLoanNumber(String applicant, String orgId,
			String startDt, String endDt) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select repay_date,sum(actual_amt) from ( ");
		sql.append(" select  to_char(rl.repay_date,'yyyyMM') repay_date,rl.actual_amt ");
		sql.append(" from contract ct ,repay_loan rl ");
		sql.append(" where ct.init_contract_id=rl.contract_id");
		sql.append(" and rl.repayment_status_cd='2'");
		sql.append(" and ct.apply_user_num= ?1");
		sql.append(" and ct.apply_org_id= ?2");
		sql.append(" and rl.repay_date>=to_date('" + startDt
				+ "','yyyy-MM-dd')");
		sql.append(" and rl.repay_date<=to_date('" + endDt + "','yyyy-MM-dd')");
		sql.append(" ) tt group by tt.repay_date order by tt.repay_date ");
		List<Object> params = new ArrayList<Object>();
		params.add(applicant);
		params.add(orgId);
		return dynamicQuery.nativeQuery(sql.toString(), params.toArray());
	}

	public List<Object[]> countPayLoanNumber(String applicant, String orgId,
			String startDt, String endDt) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select loan_actul_time,sum(loan_amt) from ( ");
		sql.append(" select  to_char(pli.loan_actul_time,'yyyyMM') loan_actul_time,pli.loan_amt ");
		sql.append(" from contract ct ,pay_loan_info pli ");
		sql.append(" where ct.init_contract_id=pli.contract_id");
		sql.append(" and pli.pay_status_cd='2'");
		sql.append(" and ct.apply_user_num= ?1");
		sql.append(" and ct.apply_org_id= ?2");
		sql.append(" and pli.loan_actul_time>=to_date('" + startDt
				+ "','yyyy-MM-dd')");
		sql.append(" and pli.loan_actul_time<=to_date('" + endDt
				+ "','yyyy-MM-dd')");
		sql.append(" ) tt group by tt.loan_actul_time order by tt.loan_actul_time");
		List<Object> params = new ArrayList<Object>();
		params.add(applicant);
		params.add(orgId);
		/*
		 * params.add(startDt); params.add(endDt);
		 */
		return dynamicQuery.nativeQuery(sql.toString(), params.toArray());
	}

	@Override
	public void doAlreadyReadMsg(Long msgId) {
		TbMessage msg = this.msgDao.findOne(msgId);
		msg.setReadtime(new Date());
		msg.setIfAlready(MSG_ALREADY_READ);
		msgDao.save(msg);
	}
	

}
