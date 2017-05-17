package com.coamctech.bxloan.web.controller.contractmng;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coamctech.bxloan.commons.controller.BaseController;
import com.coamctech.bxloan.commons.result.Result;
import com.coamctech.bxloan.commons.utils.FileUtils;
import com.coamctech.bxloan.commons.utils.FreemarkerUtils;
import com.coamctech.bxloan.datadict.DataDict;
import com.coamctech.bxloan.entity.Party;
import com.coamctech.bxloan.entity.credit.CreditContract;
import com.coamctech.bxloan.service.contractmng.ContractFileService;
import com.coamctech.bxloan.service.creditcontractmng.CreditContractMngService;
import com.coamctech.bxloan.service.customermng.UniqueCustomerService;
import com.coamctech.bxloan.service.freemarker.data.GuarantyContractInfo;
import com.coamctech.bxloan.service.freemarker.data.LoanContractInfo;
import com.coamctech.bxloan.service.freemarker.data.LoanCorpContractInfo;
import com.coamctech.bxloan.service.freemarker.data.ScpListBaseInfo;
import com.coamctech.bxloan.service.freemarker.data.SubContrBaseInfo;
import com.coamctech.bxloan.service.freemarker.data.SubContrMortInfo;
import com.coamctech.bxloan.service.freemarker.data.SubContrPledgeInfo;
import com.coamctech.bxloan.service.model.contractmng.SubContractVo;

/**
 * <b>合同下载</b>
 * <p>抵押合同、质押合同、保证从合同下载</p>
 * @author Acore
 *
 */
@Controller
@RequestMapping("/contractFile")
public class ContractFileController extends BaseController{
	@Autowired
	private ContractFileService contractFileService;
	@Autowired
	private DataDict dataDict;
	
	@Autowired
	private CreditContractMngService creditContractMngService;
	
	@Autowired
	private UniqueCustomerService uniqueCustomerService;
	
	/**
	 * 确认是否可以下载合同清单
	 * @return
	 */
	@RequestMapping("/verifyContrDownload/{id}")
	@ResponseBody
	private Result verifyContrDownload(@PathVariable("id")Long subContractId){
		return failure("暂不支持下载");
	}
	
	/**
	 * 确认是否可以下载合同清单
	 * @return
	 */
	@RequestMapping("/verifyContrListDownload/{id}")
	@ResponseBody
	private Result verifyContrListDownload(@PathVariable("id")Long subContractId){
		SubContractVo vo=contractFileService.findSubContract(subContractId);
		if(vo==null){
			return failure("合同信息不存在，请确认！");
		}
		if(StringUtils.isBlank(vo.getGuarantyTypeCd())){
			return failure("无法判断该抵押物类型，请确认！");
		}
		if (dataDict.getCodeValList("ResvAssetTypeCode", "S0", "S1", "S2",
				"S3", "S4", "S17").contains(vo.getGuarantyTypeCd())) {
			return failure("该抵质押物类型，不需要清单!");
		} else if (dataDict.getCodeValList("ResvAssetTypeCode", "S5", "S6",
				"S7", "S8", "S9", "S10", "S11", "S12","S13","S14","S15","S16").contains(
				vo.getGuarantyTypeCd())) {
			return success();
		}
		return failure("不支持此种清单下载，请确认！");
	}
	
	/**
	 * 	抵质押合同
	 */
	@RequestMapping("/downloadCollaContr/{id}")
	public void downloadCollaContr(@PathVariable("id")Long subContractId,HttpServletRequest request,HttpServletResponse response){
		SubContrBaseInfo info=contractFileService.findSubContrBaseInfo(subContractId);
		if(info!=null){
			String template="";
			String subContrTypeName=null;
			if(info instanceof SubContrMortInfo){
				template="SubContrMort.ftl";
				subContrTypeName=contractFileService.getContractTypeName("S3");
			}else if(info instanceof SubContrPledgeInfo){
				template="SubContrPledge.ftl";
				subContrTypeName=contractFileService.getContractTypeName("S4");
			}
			StringBuffer fileName=new StringBuffer();
			if(StringUtils.isNoneBlank(subContrTypeName)){
				fileName.append(subContrTypeName);
			}else{
				fileName.append("抵质押从合同");
			}
			fileName.append("["+info.getSubContractNum()+"].doc");
			if(StringUtils.isNoneBlank(template)){
				try {
					FreemarkerUtils.createDoc(template, FileUtils.initDownload(request, response, fileName.toString()), info);
					return;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		throw new RuntimeException("文件不存在");
	}
	
	/**
	 * 	质押从合同清单
	 */
	@RequestMapping("/downloadPledContrList/{id}")
	public void downloadPledContrList(@PathVariable("id")Long subContractId,HttpServletRequest request,HttpServletResponse response){
		ScpListBaseInfo info=contractFileService.findScpListBaseInfo(subContractId);
		if(info!=null){
			final String subContrTypeName=info.getScpListName();
			final String template=info.getClass().getSimpleName()+".ftl";
			StringBuffer fileName=new StringBuffer();
			if(StringUtils.isNoneBlank(subContrTypeName)){
				fileName.append(subContrTypeName);
			}else{
				fileName.append("质押从合同清单");
			}
			fileName.append("["+info.getSubContractNum()+"].doc");
			try {
				FreemarkerUtils.createDoc(template, FileUtils.initDownload(request, response, fileName.toString()), info);
				return;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 保证合同
	 */
	@RequestMapping("/downloadGuarContr/{subContractId}")
	public void downloadGuaranty(@PathVariable("subContractId")Long subContractId,HttpServletRequest request,HttpServletResponse response){
		GuarantyContractInfo guarContr=contractFileService.findGuarantyContractInfo(subContractId);
		StringBuffer fileName=new StringBuffer();
		if(StringUtils.isNotBlank(guarContr.getContrTypeName())){
			fileName.append(guarContr.getContrTypeName());
		}else{
			fileName.append("保证从合同");
		}
		fileName.append("["+guarContr.getSubContractNum()+"].doc");
		try {
			FreemarkerUtils.createDoc("GuarantyContract.ftl", FileUtils.initDownload(request, response, fileName.toString()), guarContr);
			return;
		} catch (IOException e) {
			e.printStackTrace();
		}
		throw new RuntimeException("文件不存在");
	}
	
	/**
	 * 主合同
	 */
	@RequestMapping("/downloadMainContract/{contractId}")
	public void downloadMainContract(@PathVariable("contractId")Long contractId, HttpServletRequest request, HttpServletResponse response){
		String partyType = contractFileService.findPartyTypeByContractId(contractId);
		Object objectMap = null;
		String template = "";
		String fileName = "";
		if(StringUtils.isNotBlank(partyType)){
			if(StringUtils.equals(partyType, dataDict.getCodeVal("CustomerType", "S1"))){
				//企业客户
				template = "LoanCorpContract.xml";
				objectMap = contractFileService.getLoanCorpContract(contractId);
				fileName = ((LoanCorpContractInfo)objectMap).getDebitInfo().getName()+"_合同文本.doc";
			}else if(StringUtils.equals(partyType, dataDict.getCodeVal("CustomerType", "S2"))){
				//个人客户
				template = "LoanContract.xml";
				objectMap = contractFileService.getLoanContract(contractId);
				fileName =((LoanContractInfo)objectMap).getDebitInfo().getName()+"_合同文本.doc";
			}
		}
		try {
			if(objectMap!=null&&StringUtils.isNotEmpty(template)&&StringUtils.isNotEmpty(fileName)){
				FreemarkerUtils.createDoc(template, FileUtils.initDownload(request, response, fileName), objectMap);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 下载授信主合同文本
	 * @param creditContractId 授信合同Id
	 * @param request
	 * @param response
	 */
	@RequestMapping("/downloadMainCreditContract/{creditContractId}")
	public void downloadMainCreditContract(@PathVariable("creditContractId")Long creditContractId, HttpServletRequest request, HttpServletResponse response){
		try {
			Object objectMap = null;
			String template = "";
			String fileName = "";
			CreditContract creditContract = creditContractMngService.getCreditContractById(creditContractId);
			Party party = uniqueCustomerService.findParty(creditContract.getPartyId());
			String partyType = party.getPartyTypeCd();
			if(StringUtils.isNotBlank(partyType)){
				if(StringUtils.equals(partyType, dataDict.getCodeVal("CustomerType", "S1"))){
					//企业客户
					template = "CreditContractCorporation.ftl";
					//装配企业客户-授信主合同文本信息
					objectMap = contractFileService.assembleCreditContractCorporation(creditContractId);
					fileName = ((LoanCorpContractInfo)objectMap).getDebitInfo().getName()+"_合同文本.doc";
				}else if(StringUtils.equals(partyType, dataDict.getCodeVal("CustomerType", "S2"))){
					//个人客户
					template = "CreditContractIndividual.ftl";
					//装配个人客户-授信主合同文本信息
					objectMap = contractFileService.assembleCreditContractIndividual(creditContractId);
					fileName =((LoanContractInfo)objectMap).getDebitInfo().getName()+"_合同文本.doc";
				}
			}
			if(objectMap!=null && StringUtils.isNotEmpty(template) && StringUtils.isNotEmpty(fileName)){
				FreemarkerUtils.createDoc(template, FileUtils.initDownload(request, response, fileName), objectMap);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 下载授信抵质押合同
	 * @param creditContractId 授信合同Id
	 * @param request
	 * @param response
	 */
	@RequestMapping("/downloadCreditContractCollaSubContr/{id}")
	public void downloadCreditContractCollaSubContr(@PathVariable("id")Long subContractId,HttpServletRequest request,HttpServletResponse response){
		try {
			SubContrBaseInfo info = contractFileService.assembleCreditContractCollaSubContr(subContractId);
			if(info != null){
				String template = "";
				String subContrTypeName = null;
				if(info instanceof SubContrMortInfo){
					template="SubContrMort.ftl";
					subContrTypeName = contractFileService.getContractTypeName("S3");
				} else if(info instanceof SubContrPledgeInfo){
					template="SubContrPledge.ftl";
					subContrTypeName = contractFileService.getContractTypeName("S4");
				}
				StringBuffer fileName = new StringBuffer();
				if(StringUtils.isNoneBlank(subContrTypeName)){
					fileName.append(subContrTypeName);
				} else{
					fileName.append("抵质押从合同");
				}
				fileName.append("["+info.getSubContractNum()+"].doc");
				if(StringUtils.isNoneBlank(template)){
					FreemarkerUtils.createDoc(template, FileUtils.initDownload(request, response, fileName.toString()), info);
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 下载授信保证人合同
	 * @param creditContractId 授信合同Id
	 * @param request
	 * @param response
	 */
	@RequestMapping("/downloadCreditContractAssureSubContr/{subContractId}")
	public void downloadCreditContractAssureSubContr(@PathVariable("subContractId")Long subContractId, HttpServletRequest request, HttpServletResponse response){
		try {
			GuarantyContractInfo guarContr = contractFileService.assembleCreditContractAssureSubContr(subContractId);
			StringBuffer fileName = new StringBuffer();
			if(StringUtils.isNotBlank(guarContr.getContrTypeName())){
				fileName.append(guarContr.getContrTypeName());
			} else{
				fileName.append("保证从合同");
			}
			fileName.append("["+guarContr.getSubContractNum()+"].doc");
			FreemarkerUtils.createDoc("GuarantyContract.ftl", FileUtils.initDownload(request, response, fileName.toString()), guarContr);
		} catch(Exception e){
			e.printStackTrace();
		}
	}
}
