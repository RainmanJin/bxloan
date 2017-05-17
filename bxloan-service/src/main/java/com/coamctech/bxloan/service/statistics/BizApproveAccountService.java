package com.coamctech.bxloan.service.statistics;

import java.util.Date;
import java.util.Set;

import org.springframework.data.domain.Page;

import com.coamctech.bxloan.service.model.statistics.BizAppAccItemVo;
import com.coamctech.bxloan.service.model.statistics.BizExcelExportVo;

public interface BizApproveAccountService {
	
	/**
	 * 业务申请审批流程分页查询
	 * @param pageNum
	 * @param pageSize
	 * @param startDate
	 * @param endDate
	 * @param orgIds
	 * @return
	 */
	Page<BizAppAccItemVo> findPageByProj(int pageNum,int pageSize,Date startDate,Date endDate,Set<Long> orgIds);
	
	
	/**
	 * 业务申请审批流程导出Excel分页查询（防止加载数据过多，内存溢出）
	 * @param pageNum
	 * @param pageSize
	 * @param startDate
	 * @param endDate
	 * @param orgIds
	 * @return
	 */
	Page<BizExcelExportVo> findPageForExcel(int pageNum,int pageSize,Date startDate,Date endDate,Set<Long> orgIds);
	
	
}
