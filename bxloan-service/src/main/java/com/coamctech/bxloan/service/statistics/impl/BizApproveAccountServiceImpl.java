package com.coamctech.bxloan.service.statistics.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.coamctech.bxloan.commons.dynamicquery.DynamicQuery;
import com.coamctech.bxloan.commons.utils.CommonHelper;
import com.coamctech.bxloan.service.model.statistics.BizAppAccItemVo;
import com.coamctech.bxloan.service.model.statistics.BizApproveResultVo;
import com.coamctech.bxloan.service.model.statistics.BizPayLoanInfoVo;
import com.coamctech.bxloan.service.model.statistics.BizExcelExportVo;
import com.coamctech.bxloan.service.model.statistics.BizWfItemVo;
import com.coamctech.bxloan.service.model.statistics.BizWfNodeVo;
import com.coamctech.bxloan.service.statistics.BizApproveAccountService;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
@Service
public class BizApproveAccountServiceImpl implements BizApproveAccountService {
	@Autowired
	private DynamicQuery dynamicQuery;
	@Override
	public Page<BizAppAccItemVo> findPageByProj(int pageNum,int pageSize,Date startDate,Date endDate,Set<Long> orgIds) {
		StringBuffer sb=new StringBuffer();
		sb.append("select pa.project_id, pa.workflow_id,pa.project_no,pa.customer_name,");
		sb.append("pa.project_business_type,pa.apply_amt,pa.sys_create_time,pa.customer_manager_name,eod.name,eod.description,");
		sb.append("p.product_name,(select p_temp.product_name from product p_temp where p_temp.product_cd=p.parent_product_cd) product_type,");
		sb.append("pa.biz_rate,pa.apply_term,pa.apply_term_unit,pa.guarantee_mode,pa.repaying_mode");
		sb.append(" from project_application pa");
		sb.append(" left join product p on pa.product_type = p.product_cd");
		sb.append("  left join ec_org_department eod on eod.id=pa.apply_org");
		sb.append(" where pa.sys_cd in (1,2) ");
		sb.append(" AND pa.product_type in (select pc.product_cd from product_config pc where pc.wf_code = '1004')");
		int i=1;
		List<Object> objs=Lists.newArrayList();
		if(startDate!=null){
			sb.append(" AND to_char(pa.sys_create_time,'yyyy-mm-dd') >=?"+i++);
			objs.add(CommonHelper.date2Str(startDate, CommonHelper.DF_DATE));
		}
		if(endDate!=null){
			sb.append(" AND to_char(pa.sys_create_time,'yyyy-mm-dd') <=?"+i++);
			objs.add(CommonHelper.date2Str(endDate, CommonHelper.DF_DATE));
		}
		if(CollectionUtils.isNotEmpty(orgIds)){
			sb.append(" AND eod.id in (?").append(i++).append(")");
			objs.add(orgIds);
		}
		sb.append(" ORDER BY pa.apply_org,pa.apply_date ");
		Page<Object[]> page=dynamicQuery.nativeQuery(Object[].class,new PageRequest(pageNum, pageSize), sb.toString(),objs.toArray());
		Page<BizAppAccItemVo> resultPage=new PageImpl<BizAppAccItemVo>(Lists.transform(page.getContent(), new Function<Object[], BizAppAccItemVo>() {
			@Override
			public BizAppAccItemVo apply(Object[] objs) {
				return new BizAppAccItemVo(objs);
			}
		}), new PageRequest(pageNum, pageSize), page.getTotalElements());
		return resultPage;
	}
	@Override
	public Page<BizExcelExportVo> findPageForExcel(int pageNum, int pageSize,
			Date startDate, Date endDate, Set<Long> orgIds) {
		Page<BizAppAccItemVo> appAccPage=this.findPageByProj(pageNum, pageSize, startDate, endDate, orgIds);
		List<BizAppAccItemVo> list=appAccPage.getContent();
		if(CollectionUtils.isEmpty(list)){
			return new PageImpl<BizExcelExportVo>(new ArrayList<BizExcelExportVo>(), new PageRequest(pageNum, pageSize), appAccPage.getTotalElements());
		}
		Set<Long> workflowIds=Sets.newHashSet();//流程id集合
		Set<Long> projectIds=Sets.newHashSet();//流程id集合
		for (BizAppAccItemVo vo : list) {
			workflowIds.add(vo.getWorkflowId());
			projectIds.add(vo.getProjectId());
		}
		final Map<Long, List<BizWfItemVo>> wfNodesMap=this.findWfItemListMapByWorkflowIds(workflowIds);
		final Map<Long, BizPayLoanInfoVo> payLoanInfoMap=this.findPayLoanInfoMapByProjectIds(projectIds);
		final Map<Long, BizApproveResultVo> approveResultMap=this.findApproveResultMapByProjectIds(projectIds);
		Page<BizExcelExportVo> resultPage=new PageImpl<BizExcelExportVo>(Lists.transform(appAccPage.getContent(), new Function<BizAppAccItemVo, BizExcelExportVo>() {
			@Override
			public BizExcelExportVo apply(BizAppAccItemVo item) {
				BizExcelExportVo vo=new BizExcelExportVo();
				vo.setAppAccItem(item);
				vo.setWfItems(wfNodesMap.get(item.getWorkflowId()));
				vo.setPayLoanInfo(payLoanInfoMap.get(item.getProjectId()));
				vo.setApproveResult(approveResultMap.get(item.getProjectId()));
				return vo;
			}
		}), new PageRequest(pageNum, pageSize), appAccPage.getTotalElements());
		return resultPage;
	}
	
	/**
	 * 根据workflowIds查询流程环节信息
	 * @param workflowIds
	 * @return
	 */
	private List<BizWfNodeVo> findWfNodeListByWorkflowIds(Set<Long> workflowIds){
		StringBuffer sb=new StringBuffer();
		sb.append("select wf_task.workflow_id,wf_task.task_type_id,wf_task.create_time,wf_task.claim_time,");
		sb.append("wf_task.task_assignee,wf_task.task_assignee_name,");
		sb.append("wf_task.action_code,wf_task.comments");
		sb.append("  from DFBX_WF.ccbl_worklist_task wf_task");
		sb.append(" where wf_task.task_type_id in ('100413', '100414', '100416')");//分配岗、一级审批、二级审批
		sb.append("   and wf_task.workflow_id in (?1)");
		sb.append("   order by wf_task.workflow_id,wf_task.create_time");
		List<Object[]> list=dynamicQuery.nativeQuery(Object[].class, sb.toString(), workflowIds);
		List<BizWfNodeVo> voList=Lists.transform(list, new Function<Object[], BizWfNodeVo>() {
			@Override
			public BizWfNodeVo apply(Object[] objs) {
				return new BizWfNodeVo(objs);
			}
		});
		return voList;
	}
	
	/**
	 * 根据workflowIds查询流程环节信息
	 * @param workflowIds
	 * @return
	 */
	private Map<Long, List<BizWfItemVo>> findWfItemListMapByWorkflowIds(Set<Long> workflowIds){
		Map<Long, List<BizWfItemVo>> map=Maps.newHashMap();
		List<BizWfNodeVo> list=this.findWfNodeListByWorkflowIds(workflowIds);//固定顺序
		List<BizWfNodeVo> tempNodeList=null;
		List<BizWfItemVo> tempList=null;
		Long workflowId=null;//流程id
		BizWfItemVo itemVo=null;//审批期次
		int i=1;//同一业务审批次数
		String prevNodeCode=StringUtils.EMPTY;
		if(CollectionUtils.isNotEmpty(list)){
			for (BizWfNodeVo vo : list) {
				workflowId=vo.getWorkflowId();
				if(map.containsKey(workflowId)){
					tempList=map.get(workflowId);
					if(prevNodeCode.compareTo(vo.getNodeCode())<0){//该次审批未结束
						//prevNodeCode=vo.getNodeCode();
					}else{
						itemVo=new BizWfItemVo();//
						//第一次提交
						itemVo.setSeqNum(i++);
						tempNodeList=Lists.newArrayList();
						itemVo.setWfNodes(tempNodeList);
						tempList.add(itemVo);
					}
					tempNodeList.add(vo);
				}else{
					tempList=Lists.newArrayList();
					itemVo=new BizWfItemVo();//
					i=1;
					itemVo.setSeqNum(i++);//第一次提交
					tempNodeList=Lists.newArrayList();
					tempNodeList.add(vo);
					itemVo.setWfNodes(tempNodeList);
					tempList.add(itemVo);
					map.put(workflowId, tempList);
				}
				prevNodeCode=vo.getNodeCode();
			}
		}
		return map;
	}
	private List<BizPayLoanInfoVo> findContractListByProjectIds(Set<Long> projectIds){
		StringBuffer sb=new StringBuffer();
		sb.append("select c.project_id,c.contract_id, c.contract_num, c.customer_name, cpl.loan_actul_time,");
		sb.append("eop.name");
		sb.append("  from contract c");
		sb.append("  left join pay_loan_info cpl on cpl.contract_id = c.init_contract_id");
		sb.append("  left join ec_org_person eop on eop.id=cpl.apply_user_num ");
		sb.append(" where  c.project_id in (?1) ");
		List<Object[]> list=dynamicQuery.nativeQuery(Object[].class, sb.toString(), projectIds);
		if(CollectionUtils.isEmpty(list)){
			return null;
		}
		return Lists.transform(list, new Function<Object[], BizPayLoanInfoVo>() {
			@Override
			public BizPayLoanInfoVo apply(Object[] input) {
				return new BizPayLoanInfoVo(input);
			}
		});
	}
	/**
	 * 根据业务id查询合同信息
	 * @param projectIds
	 * @return
	 */
	private Map<Long, BizPayLoanInfoVo> findPayLoanInfoMapByProjectIds(Set<Long> projectIds){
		Map<Long, BizPayLoanInfoVo> map=Maps.newHashMap();
		List<BizPayLoanInfoVo> list=this.findContractListByProjectIds(projectIds);
		if(CollectionUtils.isNotEmpty(list)){
			for (BizPayLoanInfoVo vo : list) {
				map.put(vo.getProjectId(), vo);
			}
		}
		return map;
	}
	private List<BizApproveResultVo> findApproveResultListByProjectIds(Set<Long> projectIds){
		StringBuffer sb=new StringBuffer();
		sb.append("select pa.project_id,pa.approve_amt,pa.approve_repaying_mode,");
		sb.append("pa.term,pa.term_unit,br.final_rate_value");
		sb.append("  from project_application pa");
		sb.append("  left join biz_rate br on br.project_id = pa.project_id");
		sb.append("  where pa.project_id in (?1)");
		List<Object[]> list=dynamicQuery.nativeQuery(Object[].class, sb.toString(), projectIds);
		if(CollectionUtils.isEmpty(list)){
			return null;
		}
		return Lists.transform(list, new Function<Object[], BizApproveResultVo>() {
			@Override
			public BizApproveResultVo apply(Object[] input) {
				return new BizApproveResultVo(input);
			}
		});
	}
	/**
	 * 根据业务id查询审批结果
	 * @param projectIds
	 * @return
	 */
	private Map<Long, BizApproveResultVo> findApproveResultMapByProjectIds(Set<Long> projectIds){
		Map<Long, BizApproveResultVo> map=Maps.newHashMap();
		List<BizApproveResultVo> list=this.findApproveResultListByProjectIds(projectIds);
		if(CollectionUtils.isNotEmpty(list)){
			for (BizApproveResultVo vo : list) {
				map.put(vo.getProjectId(), vo);
			}
		}
		return map;
	}
}
