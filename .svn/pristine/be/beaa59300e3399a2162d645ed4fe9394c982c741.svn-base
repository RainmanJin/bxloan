package com.coamctech.bxloan.service.datatransend;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Page;

import com.coamctech.bxloan.entity.DataTranRecord;
import com.coamctech.bxloan.service.model.datatransend.ContractInfoExportVo;
import com.coamctech.bxloan.service.model.datatransend.CustomerInfoExportVo;
import com.coamctech.bxloan.service.model.datatransend.GuarantorInfoExportVo;
import com.coamctech.bxloan.service.model.datatransend.GuarantyInfoExportVo;
import com.coamctech.bxloan.service.model.datatransend.RepayInfoExportVo;
/**   
 * 类名称：DataExportService
 * 类描述 ：数据导出服务类
 * 创建人: 马峥  
 * 创建时间：2015年6月26日 下午5:07:01  
 * 修改人：马峥
 * 修改时间：2015年6月26日 下午5:07:01  
 * 修改备注：
 * 版本： V1.0
 */
public interface DataExportService {
	
	/**
	 * 导出excel
	 * @param startDate 开始日期
	 * @param endDate  结束日期
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void exportExcel(String startDate, String endDate, HttpServletRequest request, HttpServletResponse response) throws Exception;

	/**
	 * 查询客户基本资料
	 * @param startDate 开始日期
	 * @param endDate 结束日期
	 * @return
	 */
	public List<CustomerInfoExportVo> findCustomerInfo(String startDate, String endDate);
	
	/**
     * 查询合同信息
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return
     */
	public List<ContractInfoExportVo> findContractInfo(String startDate, String endDate);
	
	/**
     * 查询还款信息
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return
     */
	public List<RepayInfoExportVo> findRepayInfo(String startDate, String endDate);
	
	/**
     * 查询担保物信息
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return
     */
	public List<GuarantyInfoExportVo> findGuarantyInfo(String startDate, String endDate);
	
	/**
     * 查询担保人信息
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return
     */
	public List<GuarantorInfoExportVo> findGuarantorInfo(String startDate, String endDate);

	/**
	 * 查询数据导出操作记录
	 * @param pageNumber 起始页数
	 * @param pageSize   每页多少条记录
	 * @return
	 */
	public Page<DataTranRecord> findDataTranRecord(Integer pageNumber,Integer pageSize);
	
	/**
	 * 保存导出操作记录
	 * @param dataTranRecord  封装的数据导出操作记录信息
	 */
	public void saveRecord(DataTranRecord dataTranRecord);
}
