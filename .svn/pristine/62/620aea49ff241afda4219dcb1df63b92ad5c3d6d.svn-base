package com.coamctech.bxloan.service.datatransend;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Page;

import com.coamctech.bxloan.commons.utils.excel.upload.ExcelImportBaseBo;
import com.coamctech.bxloan.entity.DataTranRecord;
import com.coamctech.bxloan.entity.KmExcelIn;
import com.coamctech.bxloan.service.model.tipmsg.MsgResult;
/**   
 * 类名称：DataImportService
 * 类描述 ：数据导入服务类
 * 创建人: 马峥  
 * 创建时间：2015年6月26日 下午5:07:01  
 * 修改人：马峥
 * 修改时间：2015年6月26日 下午5:07:01  
 * 修改备注：
 * 版本： V1.0
 */
public interface DataImportService {
	
	/**
	 * 导入模板下载
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void downloadExcel(HttpServletRequest request, HttpServletResponse response) throws Exception;

	/**
	 * 将导入数据保存到数据库表
	 * @param excelInBoVoList excel导入，输出vo的集合
	 * @param loginName  当前登录人名称
	 * @param orgId  当前登录人机构id
	 * @return
	 */
	public MsgResult saveData(List<ExcelImportBaseBo> excelInBoVoList, String loginName, String orgId);
	
	/**
	 * 查询导入记录
	 * @param pageNumber  起始页数
	 * @param pageSize  每页显示几条记录
	 * @param orgId   机构id
	 * @return Page  DataTablesPage对象实例
	 */
	public Page<KmExcelIn> findImportRecord(Integer pageNumber, Integer pageSize, Long orgId);
	
	/** 
	 * 删除导入的信息
	 * @param id 当前记录的id
	 */
	public void deleteRecord(Long id);
}
