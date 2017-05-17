package com.coamctech.bxloan.web.controller.upload;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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

import com.alibaba.fastjson.JSON;
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
 * 类名称：UploadFileController
 * 类描述 ：文件上传控制类
 * 创建人: 马峥  
 * 创建时间：2015年6月26日 下午5:07:01  
 * 修改人：马峥
 * 修改时间：2015年6月26日 下午5:07:01  
 * 修改备注：
 * 版本： V1.0
 */
@Controller
@RequestMapping("/uploadFile")
public class UploadFileController extends BaseController {
	private Logger logger = LoggerFactory.getLogger(UploadFileController.class);
	/**数据导入服务类*/
	@Autowired
	private DataImportService dataImportService;
	/**模板名称(ExcelImportConfig中)*/
	public static final String EXCEL_TEMPLATE_UPLOAD_CONF_1 = "financialOfficeSHExcelConfiger";
	/**表示为sheet1*/
	public static final Integer EXCEL_SHEET_ONE = 0;
	
	/**
	 * 导入上传模板数据
	 * @param request 
	 * @param response 
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/importTemplateData")
	@ResponseBody
	public String importTemplateData(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try {
			/** 获取上传的模板文件*/
			MultipartFile fileItem = this.getUploadFile(request);   
			/** 获取上传模板名称*/ 
			String fullFileName = fileItem.getOriginalFilename(); 
			String[] fileSplit = fullFileName.split("\\.");
			String fileType = fileSplit[fileSplit.length-1];
			/**解析上传的Excel*/
			List<ExcelImportBaseBo> excelInBoVoList = null;  
			/**创建一个解析Excel工具类实例*/
			ExcelProcessService excelProcessService = new ExcelProcessService();  
			/**读取sheet1信息,并保存在List中*/
			excelInBoVoList = excelProcessService.getExcelResultVoList(EXCEL_TEMPLATE_UPLOAD_CONF_1, fileItem, fileType, EXCEL_SHEET_ONE);	
			/**当前登录人的名称*/
			String loginName = request.getParameter("loginName");
			/**当前登录人的机构id*/
			String orgId = request.getParameter("orgId");
			/**将导入数据保存到数据库表*/
			MsgResult msgResult = dataImportService.saveData(excelInBoVoList, loginName, orgId);
			return JSON.toJSONString(msgResult);
		} catch (Exception e) {
			logger.error("数据导入失败,error:{}", e);
			return JSON.toJSONString(MsgResult.getMsgResult("error","数据导入失败!"));
		}
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
}
