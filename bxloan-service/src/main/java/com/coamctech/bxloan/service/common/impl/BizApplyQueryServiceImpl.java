package com.coamctech.bxloan.service.common.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coamctech.bxloan.commons.dynamicquery.DynamicQuery;
import com.coamctech.bxloan.commons.utils.CommonHelper;
import com.coamctech.bxloan.commons.utils.MathUtil;
import com.coamctech.bxloan.dao.CollateralDao;
import com.coamctech.bxloan.dao.ProjectPawnInfoDao;
import com.coamctech.bxloan.datadict.CodeData;
import com.coamctech.bxloan.datadict.DataDict;
import com.coamctech.bxloan.entity.Collateral;
import com.coamctech.bxloan.entity.ProjectPawnInfo;
import com.coamctech.bxloan.service.common.BizApplyQueryService;

@Service
@Transactional
public class BizApplyQueryServiceImpl implements BizApplyQueryService {
	@Autowired
	private DynamicQuery dynamicQuery;
	@Autowired
	private DataDict dataDict;
	@Autowired
	private CollateralDao collateralDao;
	@Autowired
	private ProjectPawnInfoDao projectPawnInfoDao;
	
	@Override
	public String findAgroBizTypeByProjId(Long projId) {
		String str="select pa.argo_biz_type from project_application pa where pa.project_id=?1";
		List<Object> list=dynamicQuery.nativeQuery(Object.class, str, projId);
		if(CollectionUtils.isEmpty(list)){
			return StringUtils.EMPTY;
		}
		return CommonHelper.toStr(list.get(0));
	}
	
	@Override
	public void releasePledgeWhenRejectOrCancel(Long projectId) throws Exception {
		List<ProjectPawnInfo> pawnInfoList = projectPawnInfoDao.findByProjectId(projectId);
		if(pawnInfoList == null || pawnInfoList.size() <= 0){
			return;
		}
		for(ProjectPawnInfo projectPawnInfo : pawnInfoList){
			//释放抵质押物
			this.releaseCollateral(projectId, projectPawnInfo);
		}
	}

	@Override
	public boolean checkPawnInfoRelease(Long guarantyId) {
		//Comment by wangyawei 20150812 start 协调dba优化sql
		/*StringBuffer sb = new StringBuffer();
		List<Object> params = new ArrayList<Object>(0);
		sb.append("select count(1) from project_application pa where ");
		sb.append("((pa.project_id in (select p.project_id from project_pawn_info p where ");
		sb.append("p.pawn_state != '4' and p.guaranty_id =?1) ");
		//0：未提交，1：已提交，2：已批复
		sb.append("and pa.project_status in ('0', '1', '2')) ");
		sb.append("or pa.project_id in (select c.project_id from contract c where ");
		sb.append("c.project_id in (select p.project_id from project_pawn_info p where ");
		//不等于'关联已删除'
		sb.append("p.pawn_state != '4' and ");
		sb.append("p.guaranty_id =?2 ) and (");
		//只查合同状态是 421：已核销，423：放款中，424：核销中，521：审批中，300：已签订，316：已放款，330：已逾期，422：已结清
		sb.append("c.contract_status_cd in (").append(this.getContractStatusValStr()).append(") and ");
		sb.append("c.if_release_flag is null)))");
		//封装查询参数
		params.add(guaranteeId);
		params.add(guaranteeId);*/
		//Comment by wangyawei 20150812 end
		
		StringBuffer sb = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sb.append(" select pa.project_id from project_application pa, ")
		  .append(" (select p.project_id from project_pawn_info p where p.pawn_state != '4' and p.guaranty_id = ?1) pb ")
		  .append(" where pa.project_id = pb.project_id and pa.project_status in ('0', '1', '2') ")
          .append(" union ")
          .append(" select pa.project_id from project_application pa,  ")
          .append(" (select c.project_id from contract c where c.project_id in ")
          .append(" (select p.project_id from project_pawn_info p where p.pawn_state != '4'  and p.guaranty_id = ?2) ")
          .append(" and c.contract_status_cd not in ('425', '331', '500') ")
          .append(" and c.if_release_flag is null) pc ")
          .append(" where pa.project_id = pc.project_id ");
		//封装查询参数
		params.add(guarantyId);
		params.add(guarantyId);
		return dynamicQuery.nativeQueryCount(sb.toString(), params.toArray()).compareTo(0L) > 0 ? false : true;
	}
	
	/**
	 * 得到合同状态不等于不等于'失效331','否决425','审批中500'的拼接字符串
	 * @return
	 */
	private String getContractStatusValStr(){
		Collection<CodeData> contractStatusList = dataDict.getOneType("ContractStatusCode");
		Iterator<CodeData> its = contractStatusList.iterator();
		StringBuffer sbf = new StringBuffer();
		while (its.hasNext()) {
			CodeData codeData = (CodeData) its.next();
			//合同状态不等于'失效','否决','审批中'
			if(!codeData.getCodeValue().equals("331") && !codeData.getCodeValue().equals("425") && !codeData.getCodeValue().equals("500")){
				sbf.append(codeData.getCodeValue()+",");
			}
		}
		return StringUtils.substringBeforeLast(sbf.toString(), ",");
	}

	@Override
	public void releasePledgeWhenDeletePawnInfo(Long projectPawnInfoId) throws Exception {
		ProjectPawnInfo projectPawnInfo = projectPawnInfoDao.findOne(projectPawnInfoId);
		//释放抵质押物
		this.releaseCollateral(projectPawnInfo.getProjectId(), projectPawnInfo);
	}
	
	/** 
	 * 释放抵质押物公用方法
	 * 
	 * @param projectId 当前业务ID
	 * @param projectPawnInfo 业务抵质押物对象
	 */
	private void releaseCollateral(Long projectId,
			ProjectPawnInfo projectPawnInfo) {
		//修改业务担保物表抵质押状态为"关联已删除"
		projectPawnInfo.setPawnState(dataDict.getCodeVal("PawnState", "S4"));
		
		//通过项目id、抵质押物状态、抵质押物id，查询除当前业务外,抵质押物是否关联其他业务
		List<ProjectPawnInfo> otherPawnList = projectPawnInfoDao.findByProjectIdAndPawnStateAndGuarantyId(
						projectId,
						dataDict.getCodeVal("PawnState", "S2"),
						projectPawnInfo.getGuarantyId());
		//获取抵质押物对象
		Long guarantyId = projectPawnInfo.getGuarantyId();
		Collateral collateral = collateralDao.findOne(guarantyId);
		
		if (otherPawnList != null && otherPawnList.size() > 0) {
			//若抵质押物关联其他业务且均"已解除关联"，则设置抵质押物状态为"已解除关联"
			if (checkPawnInfoRelease(guarantyId)) {
				collateral.setGuarantyStatusCd(dataDict.getUniqueOne("RevGuaranteeStatusCd", "S3").getCodeValue());
				//锁定金额回滚
				collateral.setLockingAmount(MathUtil.BDsubtract(
						collateral.getLockingAmount(),
						projectPawnInfo.getAppGuaDebtInterAmt(), 
						2));
			} else {
				//若抵质押物关联其他业务且均"未解除关联"，则设置抵质押物状态为"已关联"
				collateral.setGuarantyStatusCd(dataDict.getUniqueOne("RevGuaranteeStatusCd", "S2").getCodeValue());
			}
		} else {
			//若当前业务关联的抵质押物没有关联其他业务，则设置抵质押物状态为"未关联"
			collateral.setGuarantyStatusCd(dataDict.getUniqueOne("RevGuaranteeStatusCd", "S1").getCodeValue());
		}		
	}
}
