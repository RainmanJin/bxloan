package com.coamctech.bxloan.service.workflowmonitor.impl;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.coamctech.bxloan.commons.dynamicquery.DynamicQuery;
import com.coamctech.bxloan.service.model.workflowmonitor.WorkflowMonitorDetailVO;
import com.coamctech.bxloan.service.workflowmonitor.WorkflowMonitorService;
import com.google.common.base.Function;
import com.google.common.collect.Lists;

@Service
@Transactional
public class WorkflowMonitorServiceImpl implements WorkflowMonitorService{

	@Autowired
	private DynamicQuery query;

	private final Long CLASS_ONE_ADMIN = 60140L;
	private final Long CLASS_TWO_ADMIN = 60150L;
	
	@Override
	public Page<Object[]> search(String customerName,
			String processType, String processStatus, String submitTimeStart,
			String submitTimeEnd, String managerUserNum,Long managerOrgId,
			Integer pageNo, Integer pageSize) {
		
		int paramIndex = 1;
		List<Object> params = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT  ");
		sql.append("  tt.TASK_KIND as \"taskKindId\", ");
		sql.append("  cwp.WORKFLOW_TYPE_CN as \"workFlowType\", ");
		sql.append("  tt.APP_VAR_3 as \"stageID\", ");
		sql.append("  tt.TASK_STAGE_NAME as \"stageName\", ");
		sql.append("  tt.TASK_CREATE_TIME as \"createTime\", ");
		sql.append("  tt.TASK_APP_STATE as \"status\", ");
		sql.append("  tt.APP_VAR_2 as \"workFlowId\", ");
		sql.append("  pa.CUSTOMER_NAME as \"customerName\", ");
		sql.append("  pa.CUSTOMER_MANAGER_NAME as \"cusManagerName\", ");
		sql.append("  tt.TASK_ID as \"taskId\", ");
		sql.append("  pa.PROJECT_ID as \"projectID\" ");
		sql.append("from  ");
		sql.append("  ( ");
		sql.append("    SELECT TASK_ID,TASK_CREATE_TIME, ");
		sql.append("    RANK() OVER (PARTITION BY APP_VAR_2 ORDER BY TASK_CREATE_TIME DESC) rk ");
		sql.append("    FROM TASK_TODOLIST ");
		sql.append("  ) ranks, ");
		sql.append("  TASK_TODOLIST tt  ");
		sql.append("   ");
		sql.append("    LEFT JOIN PROJECT_APPLICATION pa  ");
		sql.append("    ON pa.WORKFLOW_ID = tt.APP_VAR_2  ");
		sql.append("     ");
		sql.append("    LEFT JOIN WFUSER1.CCBL_WORKFLOW_TYPE cwp ");
		sql.append("    ON tt.TASK_KIND = cwp.WORKFLOW_TYPE_CODE ");
		
		sql.append("WHERE  ");
		sql.append("  ranks.rk=1 ");
		sql.append("  AND ranks.TASK_ID = tt.TASK_ID ");

		if(isNotBlank(customerName)){
			sql.append("  AND pa.CUSTOMER_NAME LIKE ?")
			.append(paramIndex++);
			params.add("%" + customerName + "%");
		}
		
		if(isNotBlank(processType)){
			sql.append(" AND tt.TASK_KIND=?")
			.append(paramIndex++);
			params.add(Long.parseLong(processType));
		}
		
		if(isNotBlank(processStatus)){
			sql.append(" AND tt.TASK_APP_STATE = ?")
			.append(paramIndex++);
			params.add(Long.parseLong(processStatus));
		}
		if(isNotBlank(submitTimeStart)){
			sql.append(" AND tt.TASK_SUBMIT_TIME > ?")
			.append(paramIndex++);
			params.add(submitTimeStart);
		}
		if(isNotBlank(submitTimeEnd)){
			sql.append(" AND tt.TASK_SUBMIT_TIME < ?")
			.append(paramIndex++);
			params.add(submitTimeEnd);
		}
		
		if(isNotBlank(managerUserNum)){
			sql.append(" AND pa.CUSTOMER_MANAGER_NUM= ?")
			.append(paramIndex++);
			params.add(managerUserNum);
		}
		
		if(managerOrgId!=null){
			sql.append(" AND pa.CUSTOMER_MANAGER_NUM in ")
			.append("(SELECT TO_CHAR(ID) FROM EC_ORG_PERSON WHERE ORGID=?")
			.append(paramIndex++).append(")");
			params.add(managerOrgId);
		}
		
		sql.append(" ORDER BY ");
		sql.append("  tt.TASK_CREATE_TIME desc");
		
		Page pager =  this.query
				.nativeQuery(new PageRequest(pageNo , pageSize),
						sql.toString(), params.toArray());
		
		return pager;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<WorkflowMonitorDetailVO> findDetaiByWorkId(String workflowId) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT  ");
		sql.append("  t.TASK_SUBMIT_TIME      AS 完成时间, ");
//		sql.append("  t.TASK_COMMENTS         AS 审批意见, ");
		sql.append("  cwt.COMMENTS            AS 审批意见, ");
		sql.append("  t.TASK_STAGE_NAME       AS 环节名称, ");
		sql.append("  t.TASK_ASSIGNEER        AS 经办人ID, ");
		sql.append("  op.NAME                 AS 经办人名称, ");
		sql.append("  t.TASK_APP_STATE        AS 状态, ");
		sql.append("  cw.ACTION_NAME_CN       AS 操作名称, ");
		sql.append("  t.APP_VAR_3             AS taskType, ");
		sql.append("  t.APP_VAR_4             AS actionCode ");
		sql.append("FROM ");
		sql.append("  TASK_TODOLIST t ");
		sql.append("  LEFT JOIN EC_ORG_PERSON op ON t.TASK_ASSIGNEER = op.LOGNAME ");
		sql.append("  LEFT JOIN wfuser1.ccbl_worklist_action cw ");
		sql.append("  ON (cw.TASK_TYPE_ID = t.APP_VAR_3 ");
		sql.append("  AND t.APP_VAR_4    = TO_CHAR(cw.ACTION_CODE)) ");
		sql.append("  LEFT JOIN wfuser1.ccbl_worklist_task cwt ");
		sql.append("  ON cwt.TASK_ID = t.APP_VAR_1 ");
		sql.append("   ");
		sql.append("WHERE ");
		sql.append("  t.APP_VAR_2 = ?1 ");
		sql.append("ORDER BY t.TASK_SUBMIT_TIME DESC,t.TASK_ASSIGNEE_TIME DESC");
		
		
		List<Object[]> resultSet = (List<Object[]>) this.query.nativeQuery(sql.toString(), workflowId);
		if(CollectionUtils.isEmpty(resultSet)){
			return Collections.EMPTY_LIST;
		}else{
			return Lists.transform(resultSet, new Function<Object[], WorkflowMonitorDetailVO>() {
				@Override
				public WorkflowMonitorDetailVO apply(Object[] input) {
					return new WorkflowMonitorDetailVO(input);
				}
			});
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public AdminLevel findUserAdminLevel(String userNum) {
		String roleJql = "SELECT id.roleid FROM EcOrgPersonconnrole WHERE id.personid=?1";
		List<Long> roleIds = (List<Long>) this.query.query(roleJql, Long.parseLong(userNum)); 
		
		for (Long role : roleIds) {
			if (role == CLASS_ONE_ADMIN) {
				return AdminLevel.CLASS_ONE;
			}
			if (role == CLASS_TWO_ADMIN) {
				return AdminLevel.CLASS_TWO;
			}
		}
		return AdminLevel.COMMON;
	}
	
	
}
