package com.coamctech.bxloan.service.message.impl;

import java.text.MessageFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.cxf.common.util.PropertiesLoaderUtils;
import org.apache.cxf.common.util.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coamctech.bxloan.commons.GlobalConstants;
import com.coamctech.bxloan.commons.dynamicquery.DynamicQuery;
import com.coamctech.bxloan.dao.ContractDao;
import com.coamctech.bxloan.dao.ProductDao;
import com.coamctech.bxloan.dao.ProjectApplicationDao;
import com.coamctech.bxloan.dao.TbMessageDao;
import com.coamctech.bxloan.entity.Contract;
import com.coamctech.bxloan.entity.Product;
import com.coamctech.bxloan.entity.ProjectApplication;
import com.coamctech.bxloan.entity.TbMessage;
import com.coamctech.bxloan.service.message.MessageCenterService;
import com.coamctech.bxloan.service.model.message.MessageDTO;
import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
@Transactional
@Service
public class MessageCenterServiceImpl implements MessageCenterService{
	// "{0}：\n  你司上报的借款人{1}的{2}产品申请，已经做出批复，批复结果为{3}。\n 如有任何问题，请随时联系我们。"
	private final static String templateContent = GlobalConstants.COMMON_MSG;
	private final static String agreedTemplateContent = GlobalConstants.AGREED_MSG;
	private final static String retreatedTemplateContent = GlobalConstants.RETREATED_MSG;
	private final static String refusedTemplateContent = GlobalConstants.REFUSED_MSG;
	
	@Autowired
	private ProjectApplicationDao projectApplicationDao;
	@Autowired
	private ContractDao contractDao;
	@Autowired
	private ProductDao productDao;
	@Autowired
	private DynamicQuery dynamicQuery;
	@Autowired
	private TbMessageDao msgDao;
	
	/**申请结果*/
	private enum ApprovalResult{
		AGREE("同意"),
		REJECT("拒绝"),
		RETREAT("退回");
		private final String codeName;

		public String getCodeName() {
			return codeName;
		}

		private ApprovalResult(String codeName) {
			this.codeName = codeName;
		}
	}
	/**消息抬头*/
	public enum MsgContentTittle{
		AGREE("申请已同意"),
		REJECT("申请被拒绝"),
		RETREAT("申请被退回");
		private final String codeName;

		public String getCodeName() {
			return codeName;
		}

		private MsgContentTittle(String codeName) {
			this.codeName = codeName;
		}
	}
	/**消息内容*/
	private enum MsgContent{
		AGREE(agreedTemplateContent),
		REJECT(refusedTemplateContent),
		RETREAT(retreatedTemplateContent);
		private final String codeName;

		public String getCodeName() {
			return codeName;
		}

		private MsgContent(String codeName) {
			this.codeName = codeName;
		}
	}
	/**消息类型*/
	private enum MsgType{
		/**系统公告*/
		MSG_TYPE_SYSTEM_MSG(1L),
		/**合同到期提醒*/
		MSG_TYPE_CONTRACT_TIME_UP(5L),
		/**审批通过通知*/
		MSG_TYPE_APPROVAL_FINISH(23L),
		/**审批否决通知 撤销*/
		MSG_TYPE_CANCEL(6L),
		/**审批退回通知*/
		MSG_TYPE_SEND_BACK(24L), 
		/** 合同生效通知 **/
		MSG_TYPE_CONTRACT(7L),
		/**还款到期通知*/
		MSG_TYPE_REPAY_TIME_UP(21L);
		private final Long codeValue;

		public Long getCodeValue() {
			return codeValue;
		}

		private MsgType(Long codeValue) {
			this.codeValue = codeValue;
		}
	}
	
	
	
	private TbMessage combineMsg(Long workflowId, String curUserLogName, String curUserName){
		TbMessage msg = new TbMessage();
		msg.setWorkitemid(workflowId);
		msg.setSender(curUserLogName);
		msg.setSendername(curUserName);
		msg.setIfAlready(0L);
		msg.setState(0L);
		msg.setSendtime(new Date());
		return msg;
	}

	@Override
	public void sendRefusedMsg(Long workflowId, String curUserLogName, String curUserName) {
		List msgParams = ImmutableList.of(
				MsgType.MSG_TYPE_CANCEL.getCodeValue(),
				MsgContentTittle.REJECT.getCodeName(),
				MsgContent.REJECT.getCodeName(),
				ApprovalResult.REJECT.getCodeName()
				);
		this.sendMsg(msgParams, workflowId, curUserLogName, curUserName);
	}

	@Override
	public void sendPassedMsg(Long workflowId, String curUserLogName, String curUserName) {
		List msgParams = ImmutableList.of(
				MsgType.MSG_TYPE_APPROVAL_FINISH.getCodeValue(),
				MsgContentTittle.AGREE.getCodeName(),
				MsgContent.AGREE.getCodeName(),
				ApprovalResult.AGREE.getCodeName()
				);
		this.sendMsg(msgParams, workflowId, curUserLogName, curUserName);
	}

	@Override
	public void sendRetreatedMsg(Long workflowId, String curUserLogName, String curUserName) {
		if(workflowId==null){
			return ;
		}
		
		List msgParams = ImmutableList.of(
				MsgType.MSG_TYPE_SEND_BACK.getCodeValue(),
				MsgContentTittle.RETREAT.getCodeName(),
				MsgContent.RETREAT.getCodeName(),
				ApprovalResult.RETREAT.getCodeName()
				);
		this.sendMsg(msgParams, workflowId, curUserLogName, curUserName);
		
		
	}
	
	
	
	private void sendMsg(List msgParams, Long workflowId, String curUserLogName,String curUserName){
		TbMessage msg = this.combineMsg(workflowId, curUserLogName, curUserName);
		ProjectApplication proApp = this.projectApplicationDao
				.findProjectApplicationByWorkflowId(workflowId);
		Contract contract = this.contractDao.findByProjectId(proApp
				.getProjectId());
		Long contractId = contract!=null ? contract.getContractId() : null;
		String productName = productDao.findByProductCd(new Long(proApp.getProductType())).getProductName();
		List<MessageDTO> result = this.queryReceiverList(workflowId);
		
		msg.setPartyId(proApp.getPartyId());
		msg.setContractid(contractId);
		msg.setProjectid(proApp.getProjectId());
		
		msg.setType((Long)msgParams.get(0));
		msg.setTitle((String)msgParams.get(1));
		
		if(CollectionUtils.isNotEmpty(result)){
			  for (MessageDTO dto : result) {
				  		TbMessage _msg = new TbMessage();
				  		BeanUtils.copyProperties(msg, _msg, "id");
				  		String content = (String)msgParams.get(2);
						content = MessageFormat.format(content, 
								dto.getDeptName(),//小贷公司
								proApp.getCustomerName(),//借款人名称
								productName,//贷款产品
								(String)msgParams.get(3)//审批结果
								);
						_msg.setContent(content.toString());
						_msg.setReceiver(dto.getUserId().toString());
						_msg.setReceivername(dto.getPersonName());
						_msg.setSendtime(new Date());
						this.msgDao.save(_msg);
			 }
			}
	}
	
	private List<MessageDTO> queryReceiverList(Long workflowId){
		StringBuffer sql = new StringBuffer("");
		sql.append("SELECT distinct " +
						  "eop.id, " +
						  "eop.name," +
						  "eop.logname, " +
						  "eod.name deptname, " +
						  "task.task_stage_name stagename, " +
						  "task.app_var_3 tasktypeid ");
		sql.append("FROM task_todolist task " +
				   "LEFT JOIN ec_org_person eop " +
				   "ON task.task_assigneer = eop.logname ");
		sql.append("LEFT JOIN ec_org_department eod " +
				   "ON eod.id = eop.orgid ");
		sql.append("WHERE task.app_var_2 = ?1 " +
				   "AND task.task_app_state = '82' " +
				   "ORDER BY tasktypeid DESC  ");
		List<Object[]> rslist = dynamicQuery.nativeQuery(sql.toString(), workflowId.toString());
		List<MessageDTO> result = Lists.transform(rslist, new Function<Object[], MessageDTO>(){
			@Override
			public MessageDTO apply(Object[] input) {
				return new MessageDTO(input);
			}
		});
		return result;
	}
}
