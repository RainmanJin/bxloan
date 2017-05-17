package com.coamctech.bxloan.service.approval.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coamctech.bxloan.commons.GlobalConstants;
import com.coamctech.bxloan.commons.dynamicquery.DynamicQuery;
import com.coamctech.bxloan.service.approval.AntiFraudService;

@Transactional
@Service
public class AntiFraudServiceImpl implements AntiFraudService {


	@Autowired
	private DynamicQuery dynamicQuery;


	/**
	 * 根据ID查找反欺诈明细
	 * @param id 反欺诈主表ID
	 * @param ruleNum 规则编号 对应页面datatable 过滤条件
	 * */
	@Override
	public Page findAntiFraudDetailByBizNum(String ruleNum, Integer pageNumber, Integer pageSize,
			String bizNum) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder nativeSql=new StringBuilder("select r.id,r.rule_Name,r.type,r.party_Type,afd.status,to_char(af.apply_date,'yyyy-MM-dd HH:mm:ss') apply_date from ");
		nativeSql.append(GlobalConstants.FX_SCHEMA).append(".tb_Anti_Fraud  af ,");
		nativeSql.append(GlobalConstants.FX_SCHEMA).append(".tb_Anti_Fraud_Detail  afd ,");
		nativeSql.append(GlobalConstants.FX_SCHEMA).append(".tb_Rule r ");
		nativeSql.append("where afd.anti_fraud_id=af.id and r.id = afd.rule_Id and af.business_num = ?1 ");
		params.add(bizNum);
		if(StringUtils.isNoneEmpty(ruleNum)){
			nativeSql.append("and r.rule_num like ?2");
			params.add("%"+ ruleNum +"%");
		}
		nativeSql.append(" order by afd.status,r.rule_num");
		Page page = dynamicQuery.nativeQuery(new PageRequest(pageNumber - 1,
				pageSize), nativeSql.toString(), params.toArray());
		return page;
	}
}
