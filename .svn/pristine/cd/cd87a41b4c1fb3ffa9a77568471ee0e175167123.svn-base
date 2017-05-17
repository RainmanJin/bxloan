package com.coamctech.bxloan.service.bizapply.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coamctech.bxloan.commons.dynamicquery.DynamicQuery;
import com.coamctech.bxloan.dao.DrCreditLiabilityDetailDao;
import com.coamctech.bxloan.entity.DrCreditLiabilityDetail;
import com.coamctech.bxloan.entity.InfoInputConfig;
import com.coamctech.bxloan.service.approval.ApprovalApplyInfoService;
import com.coamctech.bxloan.service.bizapply.DrCreditInfoService;
import com.coamctech.bxloan.service.model.applyinfo.ApplyInfoCellVO;
import com.coamctech.bxloan.service.model.applyinfo.ApplyInfoLineGroupVO;
import com.coamctech.bxloan.service.model.applyinfo.ApplyInfoLineVO;
import com.coamctech.bxloan.service.model.applyinfo.ApplyInfoTableVO;
import com.google.common.collect.Lists;

@Transactional
@Service
public class DrCreditInfoServiceImpl implements DrCreditInfoService {
	@Autowired
	private DynamicQuery dynamicQuery;
	@Autowired
	private DrCreditLiabilityDetailDao drCreditLiabilityDetailDao;
	@Autowired
	private ApprovalApplyInfoService approvalApplyInfoService;

	@Override
	public Page<DrCreditLiabilityDetail> findDrCldPage(int pageSize,
			int pageNum, Long projectId) {
		return dynamicQuery.query(DrCreditLiabilityDetail.class,new PageRequest(pageNum, pageSize),
				"from DrCreditLiabilityDetail drcld where drcld.projectId=?1",
				projectId);
	}
	@Override
	public DrCreditLiabilityDetail findDrCld(Long id) {
		return drCreditLiabilityDetailDao.findOne(id);
	}
	@Override
	public void saveDrCld(DrCreditLiabilityDetail drCld) {
		DrCreditLiabilityDetail db_drCld=null;
		if(drCld.getDrCreditLiabilityDetailId()!=null&&drCld.getDrCreditLiabilityDetailId()>0){
			db_drCld=drCreditLiabilityDetailDao.findOne(drCld.getDrCreditLiabilityDetailId());
		}
		if(db_drCld==null){//新增
			db_drCld=new DrCreditLiabilityDetail();
			db_drCld.setProjectId(drCld.getProjectId());
		}else{//修改
			
		}
		BeanUtils.copyProperties(drCld, db_drCld, "drCreditLiabilityDetailId","projectId");//id和project不可改变
		drCreditLiabilityDetailDao.save(db_drCld);
		
	}
	@Override
	public void delDrCld(Long id) {
		drCreditLiabilityDetailDao.delete(id);
	}
	@Override
	@Transactional(readOnly=true)
	public String buildValidatJS(String... tableTypeCd) {
		StringBuilder js = new StringBuilder();
		js.append("define(function(require, exports, module) { var ruleAndMsg = {");
		Map<String, String> ruleMap = new HashMap<String, String>();
		Map<String, String> msgMap = new HashMap<String, String>();
		List<ApplyInfoTableVO> tables=Lists.newArrayList();
		for (String str : tableTypeCd) {
			tables.add(this.approvalApplyInfoService.getTableByType(str));
		}
		for (ApplyInfoTableVO table : tables) {
			for (ApplyInfoLineGroupVO lineG : table.getLinegroupList()) {
				for (ApplyInfoLineVO line : lineG.getLines()) {
					for (ApplyInfoCellVO cell : line.getCellList()) {
						for (InfoInputConfig input : cell.getInputList()) {
							
							if(StringUtils.isNotBlank(input.getValidator())){
								ruleMap.put(cell.getCell().getCellInputName(), 
										input.getValidator());
								if(StringUtils.isNotBlank(input.getValidatorMsg())){
									msgMap.put(cell.getCell().getCellInputName(),
											input.getValidatorMsg());
								}
							}
							
						}
					}
				}
			}
		}
		
		String rules = mapToJSON(ruleMap);
		String msges = mapToJSON(msgMap);
		js.append("rules:").append(rules).append(",");
		js.append("messages:").append(msges);
		js.append("};module.exports = ruleAndMsg;});");
		return js.toString();
	}
	private String mapToJSON(Map<String, String> map){
		StringBuilder json = new StringBuilder();
		json.append("{");
		if(map!=null&&!map.isEmpty()){
			boolean flag=false;
			for (Map.Entry<String, String> entry : map.entrySet()) {
				if(flag){
					json.append(",");
				}else{
					flag=true;
				}
				json.append(entry.getKey()).append(":{")
				.append(entry.getValue()).append("}");
			}
		}
		json.append("}");
		return json.toString();
	}

}
