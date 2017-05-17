package com.coamctech.bxloan.web.controller.approval;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.coamctech.bxloan.commons.controller.BaseController;
import com.coamctech.bxloan.commons.utils.FileUtils;
import com.coamctech.bxloan.commons.utils.FreemarkerUtils;
import com.coamctech.bxloan.datadict.DataDict;
import com.coamctech.bxloan.entity.ProjectApplication;
import com.coamctech.bxloan.entity.ProjectAssurerInfo;
import com.coamctech.bxloan.service.approval.ApprovalFileService;
import com.coamctech.bxloan.service.bizapply.BusinessApplicationService;
import com.coamctech.bxloan.service.freemarker.data.ApprovalCSInfo;
import com.coamctech.bxloan.service.freemarker.data.ApprovalDKSCInfo;
import com.coamctech.bxloan.service.model.SessionUser;
import com.coamctech.bxloan.service.model.collateral.CollateralVO;
import com.coamctech.bxloan.web.security.ShiroUser;

@Controller
@RequestMapping("/approvalFile")
public class ApprovalFileController extends BaseController{
	@Autowired
	private DataDict dataDict;
	@Autowired
	private ApprovalFileService approvalFileService;
	@Autowired
	private BusinessApplicationService businessApplicationService;
	
	/**
	 * 下载贷款审查表
	 * @param request
	 * @param response
	 */
	@RequestMapping("/downloadDKSCInfo")
	public void downloadDKSCInfo(HttpServletRequest request, HttpServletResponse response){
		String projectId = request.getParameter("projectId");
		ProjectApplication project = businessApplicationService.searchProjectApplication(new Long(projectId));
		ShiroUser curUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		//modify by wangyawei 20150702 start 将所属机构修改为登陆机构
		SessionUser sessionUser=new SessionUser(curUser.getId(), curUser.getLogOrgid(), null);
		//modify by wangyawei 20150702 end
		sessionUser.setLoginName(curUser.getLogname());
		sessionUser.setUserNameCN(curUser.getName());
		sessionUser.setOrgName(curUser.getLogOrgname());
		
		Object objectMap = null;
		String template = "";
		String fileName = "";
		if(project!=null){
			template = "ApproveWdSCYJInfo.xml";
			ApprovalDKSCInfo approvalDKSCInfo = approvalFileService.getDKSCInfo(project, sessionUser, request);
			
			//获取担保人列表
			List<ProjectAssurerInfo> guaranteerList = businessApplicationService.getProjectAssurerInfoByProjectId(Long.valueOf(projectId));
			if(guaranteerList.size() > 0){
				approvalDKSCInfo.getLsi().setGuaranteerList(guaranteerList);
			}
			//获取抵质押物列表
			List<CollateralVO> pawnInfoList = businessApplicationService.searchProjectPawnInfoList(project.getGuaranteeMode(),Long.valueOf(projectId));
			if(pawnInfoList != null && pawnInfoList.size() > 0){
				approvalDKSCInfo.getLsi().setPawnInfoList(pawnInfoList);
			}
			objectMap = approvalDKSCInfo;
			fileName = ((ApprovalDKSCInfo)objectMap).getApplyUserName() +"_贷款审查表.doc";
		}
		try {
			if(objectMap!=null && StringUtils.isNotEmpty(template)&&StringUtils.isNotEmpty(fileName)){
				FreemarkerUtils.createDoc(template, FileUtils.initDownload(request, response, fileName), objectMap);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	@RequestMapping("/downloadCSInfo")
	public void downloadCSInfo(HttpServletRequest request, HttpServletResponse response){
		String projectId = request.getParameter("projectId");
		ProjectApplication project = businessApplicationService.searchProjectApplication(new Long(projectId));
		ShiroUser curUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		//modify by wangyawei 20150702 start 将所属机构修改为登陆机构
		SessionUser sessionUser=new SessionUser(curUser.getId(), curUser.getLogOrgid(), null);
		//modify by wangyawei 20150702 end
		sessionUser.setLoginName(curUser.getLogname());
		sessionUser.setUserNameCN(curUser.getName());
		sessionUser.setOrgName(curUser.getLogOrgname());
		
		Object objectMap = null;
		String template = "";
		String fileName = "";
		if(project!=null){
			template = "ApprovalWdCSInfo.xml";
			ApprovalCSInfo approvalCSInfo = approvalFileService.getCSInfo(project, sessionUser, request);
			//获取担保人列表
			List<ProjectAssurerInfo> guaranteerList = businessApplicationService.getProjectAssurerInfoByProjectId(Long.valueOf(projectId));
			if(guaranteerList.size() > 0){
				approvalCSInfo.getLsi().setGuaranteerList(guaranteerList);
			}
			//获取抵质押物列表
			List<CollateralVO> pawnInfoList = businessApplicationService.searchProjectPawnInfoList(project.getGuaranteeMode(),Long.valueOf(projectId));
			if(pawnInfoList != null && pawnInfoList.size() > 0){
				approvalCSInfo.getLsi().setPawnInfoList(pawnInfoList);
			}
			objectMap = approvalCSInfo;
			fileName = ((ApprovalCSInfo)objectMap).getApplyUserName() +"_审批意见表.doc";
		}
		try {
			if(objectMap!=null && StringUtils.isNotEmpty(template)&&StringUtils.isNotEmpty(fileName)){
				FreemarkerUtils.createDoc(template, FileUtils.initDownload(request, response, fileName), objectMap);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
