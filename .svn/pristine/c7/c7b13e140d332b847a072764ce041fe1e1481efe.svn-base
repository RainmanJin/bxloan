package com.coamctech.bxloan.service.approval.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coamctech.bxloan.commons.utils.CommonHelper;
import com.coamctech.bxloan.commons.utils.MoneyRateUtils;
import com.coamctech.bxloan.commons.utils.MoneyUtil;
import com.coamctech.bxloan.commons.utils.ServletUtils;
import com.coamctech.bxloan.dao.CommonBorrowDao;
import com.coamctech.bxloan.dao.EcOrgPersonDao;
import com.coamctech.bxloan.datadict.DataDict;
import com.coamctech.bxloan.entity.CommonBorrow;
import com.coamctech.bxloan.entity.EcOrgPerson;
import com.coamctech.bxloan.entity.ProjectApplication;
import com.coamctech.bxloan.service.approval.ApprovalFileService;
import com.coamctech.bxloan.service.freemarker.data.ApprovalCSInfo;
import com.coamctech.bxloan.service.freemarker.data.ApprovalDKSCInfo;
import com.coamctech.bxloan.service.freemarker.data.ApprovalDKSCInfo.ApplyInfo;
import com.coamctech.bxloan.service.freemarker.data.ApprovalDKSCInfo.ApproveSuggestInfo;
import com.coamctech.bxloan.service.freemarker.data.ApprovalDKSCInfo.BizBaseInfo;
import com.coamctech.bxloan.service.freemarker.data.ApprovalDKSCInfo.LoanSuggestInfo;
import com.coamctech.bxloan.service.freemarker.utils.TemplateDataHelper;
import com.coamctech.bxloan.service.model.SessionUser;
import com.coamctech.bxloan.service.model.approval.ProjAppVo;

@Transactional
@Service
public class ApprovalFileServiceImpl implements ApprovalFileService{

	@Autowired
	private DataDict dataDict;
	@Autowired
	private CommonBorrowDao commonBorrowDao;
	@Autowired
	private EcOrgPersonDao ecOrgPersonDao;
	
	@Override
	public ApprovalDKSCInfo getDKSCInfo(ProjectApplication project, SessionUser sessionUser, HttpServletRequest request) {
		Map<String, String> map=ServletUtils.getParameterValueMap(request);
		ProjAppVo projApp=new ProjAppVo(map); 
		try {
			projApp.setReplyOpinion(URLDecoder.decode(request.getParameter("replyOpinion"), "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		ApprovalDKSCInfo  info = new ApprovalDKSCInfo();
		ApplyInfo ai = info.new ApplyInfo();
		BizBaseInfo bbi = info.new BizBaseInfo();
		LoanSuggestInfo lsi = info.new LoanSuggestInfo();
		ApproveSuggestInfo asi = info.new ApproveSuggestInfo();
		
		List<CommonBorrow> cbList = commonBorrowDao.findByProjectId(project.getProjectId());
		
		//下载审查表文件名称应是客户经理+贷款审查表，不应是操作人+贷款审查表
		info.setApplyUserName(StringUtils.isNotEmpty(project.getCustomerManagerName()) ? project.getCustomerManagerName() : sessionUser.getUserNameCN());
//		info.setApplyUserName(sessionUser.getUserNameCN());
		info.setApproveDate(CommonHelper.getNowStr(CommonHelper.DF_DATE));
		info.setSuggest(projApp.getReplyOpinion());
		
		//申报信息
		ai.setApplyDate(CommonHelper.getNowStr(CommonHelper.DF_DATE));
		ai.setApplyOrgName(sessionUser.getOrgName());
		//经办人显示不是当前审查人员，而是发起业务的那个
		ai.setApplyHost(StringUtils.isNotEmpty(project.getCustomerManagerName()) ? project.getCustomerManagerName() : sessionUser.getUserNameCN()+" ");
//		ai.setApplyHost(sessionUser.getUserNameCN()+" ");
		ai.setApplyTeam(TemplateDataHelper.BLANK_10);
		//协办人员
		
		ai.setApplyGest(TemplateDataHelper.defaultString(this.getAssistanceName(project.getAssistance())));
		info.setAi(ai);
		
		//业务基本信息
		bbi.setCustomerName(project.getCustomerName());
		bbi.setCertificateNum(project.getCertificateNum());
		bbi.setLoanUse(project.getPurpose());
		if(CollectionUtils.isEmpty(cbList)){	
			bbi.setPawnLoanName(TemplateDataHelper.BLANK_10);
			bbi.setPawnCertificateNum(TemplateDataHelper.BLANK_10);
		}else{
			CommonBorrow cb = cbList.get(0);
			bbi.setPawnLoanName(TemplateDataHelper.defaultString(cb.getCustomerName()));
			bbi.setPawnCertificateNum(TemplateDataHelper.defaultString(cb.getCertificateNum()));
		}
		info.setBbi(bbi);
		
		String gurantee = "";
		//贷款建议
		lsi.setLoanAmt(MoneyUtil.formatMoney(project.getApplyAmt().movePointLeft(4)));
		lsi.setLoanRate(MoneyUtil.formatMoney(project.getBizRate().movePointRight(2))+"%");
		lsi.setLoanTerm(project.getApplyTerm() + dataDict.getCodeName("TermUnitCd", project.getApplyTermUnit()));
		lsi.setRepayMode(dataDict.getCodeName("RepaymentMode", project.getRepayingMode()));
		gurantee = project.getGuaranteeMode();
		lsi.setItems(gurantee.split(","));
		info.setLsi(lsi);
		
		//审查意见
		asi.setApproveAmt(MoneyUtil.formatMoney(projApp.getApproveAmt())+"元");
		asi.setApproveRate(projApp.getApproveRateValueStr()+"%");
		asi.setApproveRepayMode(dataDict.getCodeName("RepaymentMode", projApp.getApproveRepayingMode()));
		asi.setApproveTerm(projApp.getTerm() + dataDict.getCodeName("TermUnitCd", projApp.getTermUnit()));
		gurantee = project.getGuaranteeMode();
		String guranteeCn = "";
		for(String str : gurantee.split(",")){
			if(StringUtils.isNotBlank(str)){
				guranteeCn += ","+ dataDict.getCodeName("CdsGuarantMode", str);
			}
		}
		asi.setGurranteMode(guranteeCn.substring(1));
		info.setAsi(asi);
		
		return info;
	}

	@Override
	public ApprovalCSInfo getCSInfo(ProjectApplication project, SessionUser sessionUser, HttpServletRequest request) {
		Map<String, String> map=ServletUtils.getParameterValueMap(request);
		ProjAppVo projApp=new ProjAppVo(map);
		try {
			projApp.setReplyOpinion(URLDecoder.decode(request.getParameter("replyOpinion"), "UTF-8"));
			//projApp.setReplyOpinion(new String(request.getParameter("replyOpinion").getBytes("ISO8859-1"),"UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		ApprovalDKSCInfo _info = new ApprovalDKSCInfo();
		ApprovalCSInfo info = new ApprovalCSInfo();
		ApplyInfo ai = _info.new ApplyInfo();
		BizBaseInfo bbi = _info.new BizBaseInfo();
		LoanSuggestInfo lsi = _info.new LoanSuggestInfo();
		
		List<CommonBorrow> cbList = commonBorrowDao.findByProjectId(project.getProjectId());
		
		//下载审查表文件名称应是客户经理+贷款审查表，不应是操作人+贷款审查表
		info.setApplyUserName(StringUtils.isNotEmpty(project.getCustomerManagerName()) ? project.getCustomerManagerName() : sessionUser.getUserNameCN());
//		info.setApplyUserName(sessionUser.getUserNameCN());
		info.setApproveDate(CommonHelper.getNowStr(CommonHelper.DF_DATE));
		info.setSuggest(projApp.getReplyOpinion());
		
		//申报信息
		ai.setApplyDate(CommonHelper.getNowStr(CommonHelper.DF_DATE));
		ai.setApplyOrgName(sessionUser.getOrgName());
		
		//经办人显示不是当前审查人员，而是发起业务的那个
		ai.setApplyHost(StringUtils.isNotEmpty(project.getCustomerManagerName()) ? project.getCustomerManagerName() : sessionUser.getUserNameCN()+" ");
//		ai.setApplyHost(sessionUser.getUserNameCN()+" ");
		ai.setApplyTeam(TemplateDataHelper.BLANK_10);
		ai.setApplyGest(TemplateDataHelper.defaultString(this.getAssistanceName(project.getAssistance())));
		info.setAi(ai);
		
		//业务基本信息
		bbi.setCustomerName(project.getCustomerName());
		bbi.setCertificateNum(project.getCertificateNum());
		bbi.setLoanUse(project.getPurpose());
		if(CollectionUtils.isEmpty(cbList)){	
			bbi.setPawnLoanName(TemplateDataHelper.BLANK_10);
			bbi.setPawnCertificateNum(TemplateDataHelper.BLANK_10);
		}else{
			CommonBorrow cb = cbList.get(0);
			bbi.setPawnLoanName(TemplateDataHelper.defaultString(cb.getCustomerName()));
			bbi.setPawnCertificateNum(TemplateDataHelper.defaultString(cb.getCertificateNum()));
		}
		info.setBbi(bbi);
		
		//贷款建议
		lsi.setLoanAmt(MoneyUtil.formatMoney(project.getApplyAmt().movePointLeft(4)));
		lsi.setLoanRate(MoneyUtil.formatMoney(project.getBizRate().movePointRight(2))+"%");
		lsi.setLoanTerm(project.getApplyTerm() + dataDict.getCodeName("TermUnitCd", project.getApplyTermUnit()));
		lsi.setRepayMode(dataDict.getCodeName("RepaymentMode", project.getRepayingMode()));
		lsi.setItems(project.getGuaranteeMode().split(","));
		info.setLsi(lsi);
		
		return info;
	}
	
	
	/**
	 * 获取协办人员名称
	 * @param personId
	 * @return
	 */
	private String getAssistanceName(String assistance){
		if(StringUtils.isBlank(assistance)){
			return StringUtils.EMPTY;
		}
		EcOrgPerson person= ecOrgPersonDao.findById(Long.valueOf(assistance));
		if(person==null){
			return StringUtils.EMPTY;
		}
		return StringUtils.defaultString(person.getName());
	}
}
