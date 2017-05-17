package com.coamctech.bxloan.service.datatransend.impl;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coamctech.bxloan.commons.dynamicquery.DynamicQuery;
import com.coamctech.bxloan.commons.utils.CommonHelper;
import com.coamctech.bxloan.commons.utils.FileUtils;
import com.coamctech.bxloan.commons.utils.PropertiesUtils;
import com.coamctech.bxloan.commons.utils.ReportExcelUtils;
import com.coamctech.bxloan.commons.utils.excel.upload.ExcelImportBaseBo;
import com.coamctech.bxloan.dao.ExcelDataImportDao;
import com.coamctech.bxloan.entity.KmExcelIn;
import com.coamctech.bxloan.service.datatransend.DataImportService;
import com.coamctech.bxloan.service.model.datatransend.DataImportVo;
import com.coamctech.bxloan.service.model.tipmsg.MsgResult;
import com.google.common.base.Function;
import com.google.common.collect.Lists;

@Transactional
@Service
public class DataImportServiceImpl implements DataImportService {

	@Autowired
	private DynamicQuery dynamicQuery;
	
	@Autowired
	private ExcelDataImportDao excelDataImportDao;
	
	@Override
	public void downloadExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map map = new HashMap();
		//导出excel工具类
		ReportExcelUtils reportExcelUtils= new ReportExcelUtils();
		//模版加载并且生成导出文件落地
		String fileName = reportExcelUtils.genernateExcelFileName("datatranupload", "datatranupload", "上海转出数据导入模板", map);
		//获取文件路径
		String dfzcReportPath = PropertiesUtils.getConfigFileName("datatranupload"); 
		File file = new File(dfzcReportPath + fileName);
		//从服务器下载到本地
		FileUtils.downloadFile(file, fileName, request, response);
		//下载后删除服务器文件
		file.delete();
	}

	@Override
	public MsgResult saveData(List<ExcelImportBaseBo> excelInBoVoList, String loginName, String orgId) {
		DataImportVo dataImportVo = null; 
		ExcelImportBaseBo  excelInBoImportBaseBo = null;
		if(null != excelInBoVoList && excelInBoVoList.size() > 0){			
			for(int i = 0; i < excelInBoVoList.size(); i++){				
				excelInBoImportBaseBo = excelInBoVoList.get(i);
				//校验导入数据的格式		
				if(null != excelInBoImportBaseBo){ 		
					boolean haveError = excelInBoImportBaseBo.hasErrors();
					if(haveError){
						return MsgResult.getMsgResult("error", excelInBoImportBaseBo.getAllErrors());
					} 
				}
				//获取表格数据，保存到对象中
				dataImportVo = (DataImportVo)excelInBoVoList.get(i);
				dataImportVo.setInsertDate(CommonHelper.getNow());
				dataImportVo.setInsertUser(loginName);
				dataImportVo.setOrgId(orgId);
				//表中已存在的数据信息不在进行导入
				StringBuffer jpql = new StringBuffer();
				jpql.append(" from KmExcelIn ke ");
				jpql.append(" where ke.contractNum = ?1 ");
				jpql.append(" and ke.orgId = ?2 ");
				List<Object> params = Lists.newArrayList();
				params.add(dataImportVo.getContractNum());
				params.add(dataImportVo.getOrgId());
				Long count = dynamicQuery.queryCount(jpql.toString(), params.toArray());
				if(count > 0){
					continue;
				}
				KmExcelIn kmExcelIn = new KmExcelIn();
				BeanUtils.copyProperties(dataImportVo, kmExcelIn);
				excelDataImportDao.save(kmExcelIn);
			}
		} else{	  
			return MsgResult.getMsgResult("error","excel转入合同还款信息为空,请填写相关数据!");
		}
		return MsgResult.getMsgResult("success","数据导入成功");
	}

	@Override
	public Page<KmExcelIn> findImportRecord(Integer pageNumber, Integer pageSize, Long orgId){
		List<Object> params = new ArrayList<Object>();
		params.add(String.valueOf(orgId));
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(" select ke.id,ke.contract_num,ke.repay_date,ke.repay_amt,ke.insert_date,ke.insert_user,ke.org_id,ke.yq_flag from km_excel_in ke where ke.org_id=?1 order by ke.id desc ");
		Page<Object[]> page = dynamicQuery.nativeQuery(Object[].class,
				new PageRequest(pageNumber, pageSize), stringBuffer.toString(), params.toArray());
		Page<KmExcelIn> resultPage = new PageImpl<KmExcelIn>(
				Lists.transform(page.getContent(),
						new Function<Object[], KmExcelIn>() {
							@Override
							public KmExcelIn apply(Object[] objs) {
								return new KmExcelIn(objs);
							}
						}), new PageRequest(pageNumber, pageSize),
				page.getTotalElements());
		/*stringBuffer.append(" from KmExcelIn kei where kei.orgId = ?1 order by kei.excelInId desc ");
		Page<KmExcelIn> resultPage = dynamicQuery.query(KmExcelIn.class,
				new PageRequest(pageNumber, pageSize), stringBuffer.toString(),
				params.toArray());*/
		return resultPage;
	}
	
	@Override
	public void deleteRecord(Long id) {
		excelDataImportDao.delete(id);
	}
}