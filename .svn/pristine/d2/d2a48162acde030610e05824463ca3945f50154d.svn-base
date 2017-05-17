package com.coamctech.bxloan.service.contractmng.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coamctech.bxloan.commons.dynamicquery.DynamicQuery;
import com.coamctech.bxloan.commons.utils.DateTools;
import com.coamctech.bxloan.datadict.DataDict;
import com.coamctech.bxloan.service.contractmng.DocumentMngService;
@Transactional
@Service
public class DocumentMngServiceImpl implements DocumentMngService{

	@Autowired
	private DataDict dataDict;
	@Autowired
	private DynamicQuery dynamicQuery;
	
	@Override
	public void updateCreateTypeAll(Long partyId, String createTypeCd)
			throws RuntimeException {
		String documentType = dataDict.getUniqueOne("DocumentType", "S01").getCodeValue();	//文档类型，01-客户文档
		String status = dataDict.getUniqueOne("CtrlIndicator", "S1").getCodeValue();	//文档状态，1-有效
		String systemUpdateTime = DateTools.dateToString(new Date(), "yyyy-MM-dd");
		
		StringBuffer sql = new StringBuffer();
		sql.append("update document_index set create_type_cd = '").append(createTypeCd).append("',");
		sql.append("system_update_time = to_date('").append(systemUpdateTime).append("','yyyy-MM-dd') ");
		sql.append(" where party_id = ").append(partyId);
		sql.append(" and document_type = '").append(documentType).append("' ");
		sql.append(" and status = '").append(status).append("'");
//		System.out.println("updateCreateTypeAll.sql:[" + sql + "]");
		dynamicQuery.nativeExecuteUpdate(sql.toString());
	}

}
