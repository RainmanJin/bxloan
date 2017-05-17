package com.coamctech.bxloan.web.controller.datatransend;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;

import com.coamctech.bxloan.commons.controller.BaseController;
import com.coamctech.bxloan.commons.result.DataTablesPage;
import com.coamctech.bxloan.commons.result.Result;
import com.coamctech.bxloan.commons.utils.CommonHelper;
import com.coamctech.bxloan.commons.utils.excel.upload.ExcelImportBaseBo;
import com.coamctech.bxloan.commons.utils.excel.upload.ExcelProcessService;
import com.coamctech.bxloan.entity.DataTranRecord;
import com.coamctech.bxloan.entity.KmExcelIn;
import com.coamctech.bxloan.service.datatransend.DataExportService;
import com.coamctech.bxloan.service.datatransend.DataImportService;
import com.coamctech.bxloan.service.model.datatransend.ContractInfoExportVo;
import com.coamctech.bxloan.service.model.datatransend.CustomerInfoExportVo;
import com.coamctech.bxloan.service.model.datatransend.GuarantorInfoExportVo;
import com.coamctech.bxloan.service.model.datatransend.GuarantyInfoExportVo;
import com.coamctech.bxloan.service.model.datatransend.RepayInfoExportVo;
import com.coamctech.bxloan.service.model.tipmsg.MsgResult;
import com.coamctech.bxloan.web.security.ShiroUser;

/**   
 * 类名称：DataTranSendController
 * 类描述 ：数据报送控制类
 * 创建人: 马峥  
 * 创建时间：2015年6月26日 下午5:07:01  
 * 修改人：马峥
 * 修改时间：2015年6月26日 下午5:07:01  
 * 修改备注：
 * 版本： V1.0
 */
@Controller
@RequestMapping("/dataTranSend")
public class DataTranSendController extends BaseController {
	private Logger logger = LoggerFactory.getLogger(DataTranSendController.class);
	/**数据导出服务类*/
	@Autowired
	private DataExportService dataExportService;
	/**数据导入服务类*/
	@Autowired
	private DataImportService dataImportService;
	/**模板名称(ExcelImportConfig中)*/
	public static final String EXCEL_TEMPLATE_UPLOAD_CONF_1 = "financialOfficeSHExcelConfiger";
	/**表示为sheet1*/
	public static final Integer EXCEL_SHEET_ONE = 0;
	
	/** 
	 * 跳转到数据报送页面
	 * @param model
	 * @return
	 */
	@RequestMapping
	public String index(Model model) {
		ShiroUser curUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		/**当前登录人名称*/
		model.addAttribute("loginName", curUser.getName());
		/**登录人机构id*/
		model.addAttribute("orgId", curUser.getLogOrgid());
		return "datatransend/main";
	}

	/**
	 * excel导出前的数据校验
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/checkDownload")
	@ResponseBody
	public Result checkDownload(HttpServletRequest request, HttpServletResponse response){
		try {
			/**开始日期*/
			String startDate = (String) request.getParameter("startDate");
			/**结束日期*/
			String endDate = (String) request.getParameter("endDate");
			/**查询客户资料*/
			List<CustomerInfoExportVo> customerInfoList = dataExportService.findCustomerInfo(startDate, endDate);
			/**查询合同信息*/
			List<ContractInfoExportVo> contractDataList = dataExportService.findContractInfo(startDate, endDate);
			/**查询还款信息*/
			List<RepayInfoExportVo> repayDataList = dataExportService.findRepayInfo(startDate, endDate);
			/**查询担保物信息*/
			List<GuarantyInfoExportVo> guarantyDataList = dataExportService.findGuarantyInfo(startDate, endDate);
			/**查询担保人信息*/
			List<GuarantorInfoExportVo> guarantorDataList = dataExportService.findGuarantorInfo(startDate, endDate);
		    /**客户资料，合同信息，还款信息，担保物信息，担保人信息都为空表明无数据，不能导出*/
			if(customerInfoList == null && contractDataList == null && repayDataList == null
					&& guarantyDataList == null && guarantorDataList == null){
				return failure("无相关数据,不能导出报表,请确认!");
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return failure("导出失败！");
		}
		return success();
	}
	
	/**
	 * 导出excel
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/downloadExcel")
	@ResponseBody
	public Result downloadExcel(HttpServletRequest request, HttpServletResponse response) {
		try {    
			ShiroUser shiroUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
			/**开始日期*/
			String startDate = (String) request.getParameter("startDate");
			/**结束日期*/
			String endDate = (String) request.getParameter("endDate");
			/**下载excel到本地*/
			dataExportService.exportExcel(startDate, endDate, request, response);
			/**封装参数*/
			DataTranRecord dataTranRecord = new DataTranRecord();
			dataTranRecord.setStartDate(CommonHelper.str2Date(startDate, "yyyy-MM-dd"));
			dataTranRecord.setEndDate(CommonHelper.str2Date(endDate, "yyyy-MM-dd"));
			dataTranRecord.setOperatorName(shiroUser.getName());
			dataTranRecord.setLogOrgName(shiroUser.getLogOrgname());
			/**保存导出操作记录*/
			dataExportService.saveRecord(dataTranRecord);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return failure("导出失败！" + e.getMessage());
		}
		return success("导出成功！");
	}
	
	/**
	 * 下载excel
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/downloadTemplate")
	@ResponseBody
	public void downloadTemplate(HttpServletRequest request, HttpServletResponse response) throws Exception {
		/**导入模板下载*/
		dataImportService.downloadExcel(request, response);
	}
	
    /**
     * 查询数据导出操作记录
     * @param sEcho  datatables的被请求次数
     * @param pageNumber 起始页数
     * @param pageSize 每页多少条记录
     * @return DataTablesPage DataTablesPage对象
     */
	@RequestMapping("/findOperateRecord")
	@ResponseBody
	public DataTablesPage findOperateRecord(@RequestParam("sEcho") Integer sEcho,
			@RequestParam("iDisplayStart") Integer pageNumber,
			@RequestParam("iDisplayLength") Integer pageSize
			){
		/**查询数据导出操作记录*/
		Page<DataTranRecord> page = dataExportService.findDataTranRecord(pageNumber / pageSize, pageSize);
		/**处理并返回查询结果*/
		return new DataTablesPage(sEcho, page);
	}
	
	
	/**
	 * 获取上传的模板文件
	 * @param request 
	 * @return
	 */
	public MultipartFile getUploadFile(HttpServletRequest request){
		DefaultMultipartHttpServletRequest multipartRequest = (DefaultMultipartHttpServletRequest) request;
		Iterator iterator = multipartRequest.getFileNames();
		while (iterator.hasNext()) {  
            MultipartFile multifile =  
                    multipartRequest.getFile((String) iterator.next());  
            return multifile;
        }  
		return null;
	}
	
	/**
	 * 跳转到数据导入页面
	 * @param model 参数
	 */
	@RequestMapping("/dataImport")
	public String dataImport(Model model) {
		ShiroUser curUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		/**当前登录人名称*/
		model.addAttribute("loginName", curUser.getName());
		/**当前登录人机构id*/
		model.addAttribute("orgId", curUser.getLogOrgid());
		return "datatransend/dataImport";
	}
	
	/**
     * 查询导入的记录
     * @param sEcho  datatables的被请求次数
     * @param pageNumber  起始页数
     * @param pageSize  每页多少条记录
     * @return DataTablesPage DataTablesPage对象
     */
	@RequestMapping("/findImportRecord")
	@ResponseBody
	public DataTablesPage findImportRecord(@RequestParam("sEcho") Integer sEcho,
			@RequestParam("iDisplayStart") Integer pageNumber,
			@RequestParam("iDisplayLength") Integer pageSize, Long orgId
			){
		/**查询导入的记录信息*/
		Page<KmExcelIn> page = dataImportService.findImportRecord(
				pageNumber / pageSize, pageSize, orgId);
		/**处理并返回查询结果*/
		return new DataTablesPage(sEcho, page);
	}
	
	
	/**
	 * 删除导入的信息
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteRecord")
	@ResponseBody
	public Result deleteRecord(Long id) {
		try {
			dataImportService.deleteRecord(id);
			return success("删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			return failure("删除失败！");
		}
	}
}
