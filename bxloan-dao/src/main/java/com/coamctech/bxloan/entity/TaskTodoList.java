package com.coamctech.bxloan.entity;

import static com.coamctech.bxloan.commons.GlobalConstants.WF_SCHEMA;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.coamctech.bxloan.commons.entity.BaseEntity;


@Entity
@Table(name = "TASK_TODOLIST", schema = WF_SCHEMA)
public class TaskTodoList extends BaseEntity {

	@Id
	@GeneratedValue
	@Column(name = "TASK_ID", unique = true, nullable = false, length = 64)
	private String taskId;

	@Column(name = "TASK_KIND")
	private Integer taskKind;

	@Column(name = "TASK_BATCH_ID", length = 32)
	private String taskBatchId;

	@Column(name = "TASK_STAGE_NAME", length = 256)
	private String taskStageName;

	@Column(name = "TASK_BATCH_NAME", length = 2000)
	private String taskBatchName;

	@Column(name = "TASK_SUBJECT", length = 256)
	private String taskSubject;

	@Column(name = "TASK_CONTENT", length = 500)
	private String taskContent;

	@Column(name = "TASK_STATUS", length = 10)
	private String taskStatus;

	@Column(name = "TASK_CREATOR", length = 32)
	private String taskCreator;

	@Column(name = "TASK_DESIGNATOR", length = 32)
	private String taskDesignator;

	@Column(name = "TASK_ASSIGNEER", length = 2000)
	private String taskAssigneer;

	@Column(name = "TASK_ASSIGNEE_RULE", length = 32)
	private String taskAssigneeRule;

	@Column(name = "TASK_CC", length = 32)
	private String taskCc;

	@Column(name = "TASK_ALLOW_DELIVER")
	private Integer taskAllowDeliver;

	@Column(name = "TASK_CONFIRMOR", length = 32)
	private String taskConfirmor;

	@Column(name = "TASK_SUBMIT", length = 32)
	private String taskSubmit;

	@Column(name = "TASK_CREATE_TIME", length = 32)
	private String taskCreateTime;

	@Column(name = "TASK_ASSIGNEE_TIME", length = 32)
	private String taskAssigneeTime;

	@Column(name = "TASK_CONFIRM_TIME", length = 32)
	private String taskConfirmTime;

	@Column(name = "TASK_SUBMIT_TIME", length = 32)
	private String taskSubmitTime;

	@Column(name = "TASK_RESULT_CODE", length = 32)
	private String taskResultCode;

	@Column(name = "DEPT_CODE", length = 128)
	private String deptCode;

	@Column(name = "TASK_COMMENTS", length = 1000)
	private String taskComments;

	@Column(name = "TASK_APP_SRC", length = 32)
	private String taskAppSrc;

	@Column(name = "TASK_APP_MOUDLE", length = 32)
	private String taskAppMoudle;

	@Column(name = "APP_VAR_1", length = 64)
	private String appVar1;

	@Column(name = "APP_VAR_2", length = 64)
	private String appVar2;

	@Column(name = "APP_VAR_3", length = 64)
	private String appVar3;

	@Column(name = "APP_VAR_4", length = 64)
	private String appVar4;
	@Column(name = "APP_VAR_5", length = 128)
	private String appVar5;

	@Column(name = "APP_VAR_6", length = 300)
	private String appVar6;

	@Column(name = "APP_VAR_7", length = 64)
	private String appVar7;

	@Column(name = "APP_VAR_8", length = 64)
	private String appVar8;
	@Column(name = "APP_VAR_9", length = 300)
	private String appVar9;

	@Column(name = "TASK_MSG_ID")
	private String taskMsgId;

	@Column(name = "TASK_MSG_STATUS")
	private Integer taskMsgStatus;
	@Column(name = "TASK_LINK_TYPE", length = 32)
	private String taskLinkType;

	@Column(name = "TASK_LINK2_NAME", length = 32)
	private String taskLink2Name;

	@Column(name = "TASK_LINK2_TYPE", length = 32)
	private String taskLink2Type;

	@Column(name = "TASK_LINK3_NAME", length = 32)
	private String taskLink3Name;

	@Column(name = "TASK_LINK3_TYPE", length = 32)
	private String taskLink3Type;

	@Column(name = "TASK_LINK4_NAME", length = 32)
	private String taskLink4Name;
	@Column(name = "TASK_LINK4_TYPE", length = 32)
	private String taskLink4Type;

	@Column(name = "TASK_LINK5_NAME", length = 32)
	private String taskLink5Name;

	@Column(name = "TASK_LINK5_TYPE", length = 32)
	private String taskLink5Type;

	@Column(name = "DELFLAG")
	private Integer delflag;

	@Column(name = "TASK_EXECUTOR", length = 32)
	private String taskExecutor;

	@Column(name = "TASK_APP_STATE", length = 32)
	private String taskAppState;

	public TaskTodoList() {
	}

	/** full constructor */
	public TaskTodoList(Integer taskKind, String taskBatchId,
			String taskStageName, String taskBatchName, String taskSubject,
			String taskContent, String taskStatus, String taskCreator,
			String taskDesignator, String taskAssigneer,
			String taskAssigneeRule, String taskCc, Integer taskAllowDeliver,
			String taskConfirmor, String taskSubmit, String taskCreateTime,
			String taskAssigneeTime, String taskConfirmTime,
			String taskSubmitTime, String taskResultCode, String deptCode,
			String taskComments, String taskAppSrc, String taskAppMoudle,
			String appVar1, String appVar2, String appVar3, String appVar4,
			String appVar5, String appVar6, String appVar7, String appVar8,
			String appVar9, String taskMsgId, Integer taskMsgStatus,
			String taskLinkType, String taskLink2Name, String taskLink2Type,
			String taskLink3Name, String taskLink3Type, String taskLink4Name,
			String taskLink4Type, String taskLink5Name, String taskLink5Type,
			Integer delflag, String taskExecutor, String taskAppState) {
		this.taskKind = taskKind;
		this.taskBatchId = taskBatchId;
		this.taskStageName = taskStageName;
		this.taskBatchName = taskBatchName;
		this.taskSubject = taskSubject;
		this.taskContent = taskContent;
		this.taskStatus = taskStatus;
		this.taskCreator = taskCreator;
		this.taskDesignator = taskDesignator;
		this.taskAssigneer = taskAssigneer;
		this.taskAssigneeRule = taskAssigneeRule;
		this.taskCc = taskCc;
		this.taskAllowDeliver = taskAllowDeliver;
		this.taskConfirmor = taskConfirmor;
		this.taskSubmit = taskSubmit;
		this.taskCreateTime = taskCreateTime;
		this.taskAssigneeTime = taskAssigneeTime;
		this.taskConfirmTime = taskConfirmTime;
		this.taskSubmitTime = taskSubmitTime;
		this.taskResultCode = taskResultCode;
		this.deptCode = deptCode;
		this.taskComments = taskComments;
		this.taskAppSrc = taskAppSrc;
		this.taskAppMoudle = taskAppMoudle;
		this.appVar1 = appVar1;
		this.appVar2 = appVar2;
		this.appVar3 = appVar3;
		this.appVar4 = appVar4;
		this.appVar5 = appVar5;
		this.appVar6 = appVar6;
		this.appVar7 = appVar7;
		this.appVar8 = appVar8;
		this.appVar9 = appVar9;
		this.taskMsgId = taskMsgId;
		this.taskMsgStatus = taskMsgStatus;
		this.taskLinkType = taskLinkType;
		this.taskLink2Name = taskLink2Name;
		this.taskLink2Type = taskLink2Type;
		this.taskLink3Name = taskLink3Name;
		this.taskLink3Type = taskLink3Type;
		this.taskLink4Name = taskLink4Name;
		this.taskLink4Type = taskLink4Type;
		this.taskLink5Name = taskLink5Name;
		this.taskLink5Type = taskLink5Type;
		this.delflag = delflag;
		this.taskExecutor = taskExecutor;
		this.taskAppState = taskAppState;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public Integer getTaskKind() {
		return taskKind;
	}

	public void setTaskKind(Integer taskKind) {
		this.taskKind = taskKind;
	}

	public String getTaskBatchId() {
		return taskBatchId;
	}

	public void setTaskBatchId(String taskBatchId) {
		this.taskBatchId = taskBatchId;
	}

	public String getTaskStageName() {
		return taskStageName;
	}

	public void setTaskStageName(String taskStageName) {
		this.taskStageName = taskStageName;
	}

	public String getTaskBatchName() {
		return taskBatchName;
	}

	public void setTaskBatchName(String taskBatchName) {
		this.taskBatchName = taskBatchName;
	}

	public String getTaskSubject() {
		return taskSubject;
	}

	public void setTaskSubject(String taskSubject) {
		this.taskSubject = taskSubject;
	}

	public String getTaskContent() {
		return taskContent;
	}

	public void setTaskContent(String taskContent) {
		this.taskContent = taskContent;
	}

	public String getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}

	public String getTaskCreator() {
		return taskCreator;
	}

	public void setTaskCreator(String taskCreator) {
		this.taskCreator = taskCreator;
	}

	public String getTaskDesignator() {
		return taskDesignator;
	}

	public void setTaskDesignator(String taskDesignator) {
		this.taskDesignator = taskDesignator;
	}

	public String getTaskAssigneer() {
		return taskAssigneer;
	}

	public void setTaskAssigneer(String taskAssigneer) {
		this.taskAssigneer = taskAssigneer;
	}

	public String getTaskAssigneeRule() {
		return taskAssigneeRule;
	}

	public void setTaskAssigneeRule(String taskAssigneeRule) {
		this.taskAssigneeRule = taskAssigneeRule;
	}

	public String getTaskCc() {
		return taskCc;
	}

	public void setTaskCc(String taskCc) {
		this.taskCc = taskCc;
	}

	public Integer getTaskAllowDeliver() {
		return taskAllowDeliver;
	}

	public void setTaskAllowDeliver(Integer taskAllowDeliver) {
		this.taskAllowDeliver = taskAllowDeliver;
	}

	public String getTaskConfirmor() {
		return taskConfirmor;
	}

	public void setTaskConfirmor(String taskConfirmor) {
		this.taskConfirmor = taskConfirmor;
	}

	public String getTaskSubmit() {
		return taskSubmit;
	}

	public void setTaskSubmit(String taskSubmit) {
		this.taskSubmit = taskSubmit;
	}

	public String getTaskCreateTime() {
		return taskCreateTime;
	}

	public void setTaskCreateTime(String taskCreateTime) {
		this.taskCreateTime = taskCreateTime;
	}

	public String getTaskAssigneeTime() {
		return taskAssigneeTime;
	}

	public void setTaskAssigneeTime(String taskAssigneeTime) {
		this.taskAssigneeTime = taskAssigneeTime;
	}

	public String getTaskConfirmTime() {
		return taskConfirmTime;
	}

	public void setTaskConfirmTime(String taskConfirmTime) {
		this.taskConfirmTime = taskConfirmTime;
	}

	public String getTaskSubmitTime() {
		return taskSubmitTime;
	}

	public void setTaskSubmitTime(String taskSubmitTime) {
		this.taskSubmitTime = taskSubmitTime;
	}

	public String getTaskResultCode() {
		return taskResultCode;
	}

	public void setTaskResultCode(String taskResultCode) {
		this.taskResultCode = taskResultCode;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getTaskComments() {
		return taskComments;
	}

	public void setTaskComments(String taskComments) {
		this.taskComments = taskComments;
	}

	public String getTaskAppSrc() {
		return taskAppSrc;
	}

	public void setTaskAppSrc(String taskAppSrc) {
		this.taskAppSrc = taskAppSrc;
	}

	public String getTaskAppMoudle() {
		return taskAppMoudle;
	}

	public void setTaskAppMoudle(String taskAppMoudle) {
		this.taskAppMoudle = taskAppMoudle;
	}

	public String getAppVar1() {
		return appVar1;
	}

	public void setAppVar1(String appVar1) {
		this.appVar1 = appVar1;
	}

	public String getAppVar2() {
		return appVar2;
	}

	public void setAppVar2(String appVar2) {
		this.appVar2 = appVar2;
	}

	public String getAppVar3() {
		return appVar3;
	}

	public void setAppVar3(String appVar3) {
		this.appVar3 = appVar3;
	}

	public String getAppVar4() {
		return appVar4;
	}

	public void setAppVar4(String appVar4) {
		this.appVar4 = appVar4;
	}

	public String getAppVar5() {
		return appVar5;
	}

	public void setAppVar5(String appVar5) {
		this.appVar5 = appVar5;
	}

	public String getAppVar6() {
		return appVar6;
	}

	public void setAppVar6(String appVar6) {
		this.appVar6 = appVar6;
	}

	public String getAppVar7() {
		return appVar7;
	}

	public void setAppVar7(String appVar7) {
		this.appVar7 = appVar7;
	}

	public String getAppVar8() {
		return appVar8;
	}

	public void setAppVar8(String appVar8) {
		this.appVar8 = appVar8;
	}

	public String getAppVar9() {
		return appVar9;
	}

	public void setAppVar9(String appVar9) {
		this.appVar9 = appVar9;
	}

	public String getTaskMsgId() {
		return taskMsgId;
	}

	public void setTaskMsgId(String taskMsgId) {
		this.taskMsgId = taskMsgId;
	}

	public Integer getTaskMsgStatus() {
		return taskMsgStatus;
	}

	public void setTaskMsgStatus(Integer taskMsgStatus) {
		this.taskMsgStatus = taskMsgStatus;
	}

	public String getTaskLinkType() {
		return taskLinkType;
	}

	public void setTaskLinkType(String taskLinkType) {
		this.taskLinkType = taskLinkType;
	}

	public String getTaskLink2Name() {
		return taskLink2Name;
	}

	public void setTaskLink2Name(String taskLink2Name) {
		this.taskLink2Name = taskLink2Name;
	}

	public String getTaskLink2Type() {
		return taskLink2Type;
	}

	public void setTaskLink2Type(String taskLink2Type) {
		this.taskLink2Type = taskLink2Type;
	}

	public String getTaskLink3Name() {
		return taskLink3Name;
	}

	public void setTaskLink3Name(String taskLink3Name) {
		this.taskLink3Name = taskLink3Name;
	}

	public String getTaskLink3Type() {
		return taskLink3Type;
	}

	public void setTaskLink3Type(String taskLink3Type) {
		this.taskLink3Type = taskLink3Type;
	}

	public String getTaskLink4Name() {
		return taskLink4Name;
	}

	public void setTaskLink4Name(String taskLink4Name) {
		this.taskLink4Name = taskLink4Name;
	}

	public String getTaskLink4Type() {
		return taskLink4Type;
	}

	public void setTaskLink4Type(String taskLink4Type) {
		this.taskLink4Type = taskLink4Type;
	}

	public String getTaskLink5Name() {
		return taskLink5Name;
	}

	public void setTaskLink5Name(String taskLink5Name) {
		this.taskLink5Name = taskLink5Name;
	}

	public String getTaskLink5Type() {
		return taskLink5Type;
	}

	public void setTaskLink5Type(String taskLink5Type) {
		this.taskLink5Type = taskLink5Type;
	}

	public Integer getDelflag() {
		return delflag;
	}

	public void setDelflag(Integer delflag) {
		this.delflag = delflag;
	}

	public String getTaskExecutor() {
		return taskExecutor;
	}

	public void setTaskExecutor(String taskExecutor) {
		this.taskExecutor = taskExecutor;
	}

	public String getTaskAppState() {
		return taskAppState;
	}

	public void setTaskAppState(String taskAppState) {
		this.taskAppState = taskAppState;
	}
	
}