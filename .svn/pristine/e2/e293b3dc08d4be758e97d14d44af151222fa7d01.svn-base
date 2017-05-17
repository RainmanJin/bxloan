package com.coamctech.bxloan.service.contractmng.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coamctech.bxloan.commons.GlobalConstants;
import com.coamctech.bxloan.commons.dynamicquery.DynamicQuery;
import com.coamctech.bxloan.commons.utils.MoneyUtil;
import com.coamctech.bxloan.dao.AddressDao;
import com.coamctech.bxloan.dao.BizRateDao;
import com.coamctech.bxloan.dao.CollateralDao;
import com.coamctech.bxloan.dao.ContractDao;
import com.coamctech.bxloan.dao.CorporationCustomerDao;
import com.coamctech.bxloan.dao.CustomerIndividualDao;
import com.coamctech.bxloan.dao.PartyDao;
import com.coamctech.bxloan.dao.ProductConfigDao;
import com.coamctech.bxloan.dao.ProductDao;
import com.coamctech.bxloan.dao.ProjectApplicationDao;
import com.coamctech.bxloan.dao.ProjectAssurerInfoDao;
import com.coamctech.bxloan.dao.ProjectPawnInfoDao;
import com.coamctech.bxloan.dao.SubContractDao;
import com.coamctech.bxloan.dao.credit.CreditContractDao;
import com.coamctech.bxloan.datadict.DataDict;
import com.coamctech.bxloan.entity.Address;
import com.coamctech.bxloan.entity.BizRate;
import com.coamctech.bxloan.entity.Collateral;
import com.coamctech.bxloan.entity.Contract;
import com.coamctech.bxloan.entity.CorpCustomerRelaPerson;
import com.coamctech.bxloan.entity.CorporationCustomer;
import com.coamctech.bxloan.entity.CustomerCorrelativeRelations;
import com.coamctech.bxloan.entity.Individual;
import com.coamctech.bxloan.entity.Party;
import com.coamctech.bxloan.entity.Product;
import com.coamctech.bxloan.entity.ProjectApplication;
import com.coamctech.bxloan.entity.ProjectAssurerInfo;
import com.coamctech.bxloan.entity.ProjectPawnInfo;
import com.coamctech.bxloan.entity.SubContract;
import com.coamctech.bxloan.entity.credit.CreditContract;
import com.coamctech.bxloan.service.contractmng.ContractFileService;
import com.coamctech.bxloan.service.freemarker.data.GuarantyContractInfo;
import com.coamctech.bxloan.service.freemarker.data.LoanContractInfo;
import com.coamctech.bxloan.service.freemarker.data.LoanContractInfo.ContractForm;
import com.coamctech.bxloan.service.freemarker.data.LoanContractInfo.ContractTerm;
import com.coamctech.bxloan.service.freemarker.data.LoanContractInfo.CreditInfo;
import com.coamctech.bxloan.service.freemarker.data.LoanContractInfo.DebitInfo;
import com.coamctech.bxloan.service.freemarker.data.LoanContractInfo.IntRate;
import com.coamctech.bxloan.service.freemarker.data.LoanContractInfo.RepaymentMode;
import com.coamctech.bxloan.service.freemarker.data.LoanCorpContractInfo;
import com.coamctech.bxloan.service.freemarker.data.LoanCorpContractInfo.GuarantorInfo;
import com.coamctech.bxloan.service.freemarker.data.ScpListBaseInfo;
import com.coamctech.bxloan.service.freemarker.data.ScpListS10Info;
import com.coamctech.bxloan.service.freemarker.data.ScpListS11Info;
import com.coamctech.bxloan.service.freemarker.data.ScpListS12Info;
import com.coamctech.bxloan.service.freemarker.data.ScpListS13Info;
import com.coamctech.bxloan.service.freemarker.data.ScpListS14Info;
import com.coamctech.bxloan.service.freemarker.data.ScpListS15Info;
import com.coamctech.bxloan.service.freemarker.data.ScpListS16Info;
import com.coamctech.bxloan.service.freemarker.data.ScpListS17Info;
import com.coamctech.bxloan.service.freemarker.data.ScpListS6Info;
import com.coamctech.bxloan.service.freemarker.data.ScpListS7Info;
import com.coamctech.bxloan.service.freemarker.data.ScpListS8Info;
import com.coamctech.bxloan.service.freemarker.data.ScpListS9Info;
import com.coamctech.bxloan.service.freemarker.data.SubContrBaseInfo;
import com.coamctech.bxloan.service.freemarker.data.SubContrMortInfo;
import com.coamctech.bxloan.service.freemarker.data.SubContrPledgeInfo;
import com.coamctech.bxloan.service.freemarker.utils.TemplateDataHelper;
import com.coamctech.bxloan.service.model.contractmng.SubContractVo;
import com.coamctech.bxloan.service.pettyloan.util.CommonHelper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import freemarker.template.TemplateDateModel;

@Service
public class ContractFileServiceImpl implements ContractFileService {
	private final String RESV_ASSET_TYPE_CODE = "ResvAssetTypeCode";// 抵质押类型
	private final String CONTRACT_TYPE_CD = "ContractTypeCd";// 合同类型
	private final String GUARANTY_CERTIFICATE = "GuarantyCertificate";// 权利类型
	@Autowired
	private SubContractDao subContractDao;
	@Autowired
	private ContractDao contractDao;
	@Autowired
	private CollateralDao collateralDao;
	@Autowired
	private ProjectPawnInfoDao projectPawnInfoDao;
	@Autowired
	private PartyDao partyDao;
	@Autowired
	private AddressDao addressDao;
	@Autowired
	private ProductDao productDao;
	@Autowired
	private ProductConfigDao productConfigDao;
	@Autowired
	private BizRateDao bizRateDao;
	@Autowired
	private DataDict dataDict;
	@Autowired
	private ProjectApplicationDao projectApplicationDao;
	@Autowired
	private ProjectAssurerInfoDao projectAssurerInfoDao;
	@Autowired
	private CorporationCustomerDao corporationCustomerDao;
	@Autowired
	private CustomerIndividualDao customerIndividualDao;
	@Autowired
	private CreditContractDao creditContractDao;
	@Autowired
	private DynamicQuery dynamicQuery;

	@Override
	public GuarantyContractInfo findGuarantyContractInfo(Long subContractId) {
		SubContract subContr = subContractDao.findOne(subContractId);
		Contract contr = contractDao.findOne(subContr.getContractId());
		GuarantyContractInfo gci = new GuarantyContractInfo();
		//modify by wangyawei 20150716 start 将授信合同和普通借款合同公用的信息进行组装
		this.assembleCommonInfo(subContr, gci);
		//modify by wangyawei 20150716 end
		String contrType = dataDict.getCodeVal(CONTRACT_TYPE_CD, "S5");
		gci.setContrTypeName(dataDict.getCodeName(CONTRACT_TYPE_CD, contrType));
		gci.setDebtorName(contr.getCustomerName());
		gci.setContrNum(contr.getContractNum());
		String contrAmt = MoneyUtil.formatMoney(contr.getContractAmt());
		gci.setContrAmt(contrAmt);
		gci.setContrAmtCN(MoneyUtil.CNValueOf(contrAmt));
		return gci;
	}

	/** 
	 * 组装授信合同/普通借款合同公用的保证人信息
	 * 
	 * @param subContr 从合同对象
	 * @param gci 保证人信息对象
	 */
	private void assembleCommonInfo(SubContract subContr, GuarantyContractInfo gci) {
		Party party = partyDao.findOne(subContr.getCustomerId());
		ProjectAssurerInfo projAssu = projectAssurerInfoDao.findOne(subContr.getAssurerId());
		gci.setSubContractNum(subContr.getSubcontractNum());
		// 1.保证方
		StringBuffer pledgorName = new StringBuffer();// 保证人
		StringBuffer pledgorPaperworkType = new StringBuffer();// 保证人证件类型
		StringBuffer pledgorPaperworkNum = new StringBuffer();// 保证人证件编号
		String pledgorLegalRepr = null;// 保证方法定代表人
		String pledgorPost = null;// 保证方职位
		StringBuffer pledgorAddr = new StringBuffer();// 保证住址
		StringBuffer pledgorContactAddr = new StringBuffer();// 保证人联系地址
		StringBuffer pledgorPhoneNum = new StringBuffer();// 保证人联系电话
		if (projAssu != null) {
			pledgorName.append(projAssu.getCustomerName());
			pledgorPaperworkType.append(this.getPaperWorkTypeName(projAssu.getCertificateTypeCd()));
			pledgorPaperworkNum.append(projAssu.getCertificateNum());
		}
		if (StringUtils.equals(dataDict.getCodeVal("CustomerType", "S1"), party.getPartyTypeCd())) {// 企业
			CorporationCustomer corpCust = corporationCustomerDao.findOne(party.getPartyId());
			pledgorPhoneNum.append(corpCust.getLinkmanTel());
			if (corpCust != null) {
				List<CorpCustomerRelaPerson> ccrpList = this.findCorpCustomerRelaPersonList(party.getPartyId(), dataDict.getCodeVal("RepresentativeFlag", "S1"));
				if (!CollectionUtils.isEmpty(ccrpList)) {
					pledgorLegalRepr = ccrpList.get(0).getName();// 法定代表人
					pledgorPost = dataDict.getCodeName("Professional", ccrpList.get(0).getProfessional());// 法定代表人职务
				}
				// 地址
				List<Address> addrList = this.findAddressList(party.getPartyId());
				if (!CollectionUtils.isEmpty(addrList)) {
					final String ADDR_TYPE_LINK = dataDict.getCodeVal("AddressType", "S1");// 通讯地址
					pledgorAddr.append(TemplateDataHelper.BLANK_20);
					Set<String> codes = Sets.newHashSet();
					Map<String, String> map = null;
					for (Address address : addrList) {
						codes.clear();
						if (StringUtils.equals(ADDR_TYPE_LINK, address.getAddressTypeCd())) {
							initSet(codes, address.getNationalityCd());
							initSet(codes, address.getProvinceCd());
							initSet(codes, address.getCityCd());
							initSet(codes, address.getCountyCd());
							map = this.findProvincesMap(codes);
							initStringBuffer(map, address.getNationalityCd(), pledgorContactAddr);
							initStringBuffer(map, address.getProvinceCd(), pledgorContactAddr);
							initStringBuffer(map, address.getCityCd(), pledgorContactAddr);
							initStringBuffer(map, address.getCountyCd(), pledgorContactAddr);
							pledgorContactAddr.append(address.getStreetAddress());
							break;
						} else {
							continue;
						}
					}
				}
			}
		} else if (StringUtils.equals(dataDict.getCodeVal("CustomerType", "S2"), party.getPartyTypeCd())) {// 个人
			Individual individual = customerIndividualDao.findOne(party.getPartyId());
			if (individual != null) {
				pledgorPhoneNum.append(individual.getMobileTel());
				pledgorAddr.append(individual.getFamilyAddress());
				//保证从合同证件号码是担保人的 gph 201506181004
				pledgorPaperworkNum.append(this.strAddPrefix(individual.getCertificateNum()));//证件号码
				pledgorPaperworkType.append(this.strAddPrefix(this.getPaperWorkTypeName(individual.getCertificateTypeCd())));//证件类型
				pledgorName.append(this.strAddPrefix(individual.getCustomerName()));//客户姓名
				
			}
//			if (StringUtils.equals("1", projAssu.getWifeGuarante())) {
//				List<CustomerCorrelativeRelations> custCorrList = this.findCustCorrList(party.getPartyId(), dataDict.getCodeVal("CorrelativeCustomerTypeCd", "S1"));
//				if (!CollectionUtils.isEmpty(custCorrList)) {
//					for (CustomerCorrelativeRelations custCorr : custCorrList) {
//						pledgorName.append(this.strAddPrefix(custCorr.getCorrelativeCustomerName()));
//						pledgorPaperworkType.append(this.strAddPrefix(this.getPaperWorkTypeName(custCorr.getCorCertificateTypeCd())));
//						pledgorPhoneNum.append(this.strAddPrefix(custCorr.getMobileNum()));
//					}
//				}
//			}
		}
		gci.setPledgorName(pledgorName.toString());
		gci.setPledgorPaperworkType(pledgorPaperworkType.toString());

		gci.setPledgorPaperworkNum(pledgorPaperworkNum.toString());
		gci.setPledgorLegalRepr(pledgorLegalRepr);
		gci.setPledgorPost(pledgorPost);
		gci.setPledgorAddr(pledgorAddr.toString());
		gci.setPledgorContactAddr(pledgorContactAddr.toString());
		gci.setPledgorPhoneNum(pledgorPhoneNum.toString());
		// 2贷方信息
		OrgInfo orgInfo = this.findOrgInfo(subContr.getHandlingOrgCd());
		if (orgInfo != null) {
			gci.setCompanyName(orgInfo.getName());
			gci.setCrName(orgInfo.getName());
			gci.setCrPaperworkType(TemplateDataHelper.BLANK_20);
			gci.setCrPaperworkNum(TemplateDataHelper.BLANK_20);
			gci.setCrLegalRepr(TemplateDataHelper.BLANK_20);
			gci.setCrPost(TemplateDataHelper.BLANK_20);
			gci.setCrAddr(TemplateDataHelper.BLANK_20);
			gci.setCrContactAddr(orgInfo.getAddress());
			gci.setCrPhoneNum(orgInfo.getFixedPhone());
		}
	}

	@Override
	@Transactional(readOnly = true)
	public SubContractVo findSubContract(Long subContractId) {
		SubContract subContr = subContractDao.findOne(subContractId);
		if (subContr == null) {
			return null;
		}
		ProjectPawnInfo ppi=projectPawnInfoDao.findOne(subContr.getGuarantyId());
		if ( ppi== null) {
			return null;
		}
		Collateral collateral = collateralDao.findOne(ppi.getGuarantyId());
		SubContractVo vo = new SubContractVo();
		if (collateral != null) {
			vo.setGuarantyTypeCd(collateral.getGuarantyTypeCd());
		}
		return vo;
	}

	@Override
	public SubContrBaseInfo findSubContrBaseInfo(Long subContractId) {
		SubContract subContr = subContractDao.findOne(subContractId);
		if (StringUtils.equals(dataDict.getCodeVal("GuaranteeTypeCode", "S1"), subContr.getGuaranteeTypeCd())) {
			SubContrMortInfo info = new SubContrMortInfo();
			this.initSubContrDrInfo(info, subContr);
			// 2贷方信息
			this.initOrgInfo(info, subContr.getHandlingOrgCd());
			// 3主合同信息
			this.initContrInfo(info, subContr.getContractId(), subContr.getProjectId());
			this.initMortInfo(info, subContr.getGuarantyId());
			return info;
		} else if (StringUtils.equals(dataDict.getCodeVal("GuaranteeTypeCode", "S2"), subContr.getGuaranteeTypeCd())) {
			SubContrPledgeInfo info = new SubContrPledgeInfo();
			this.initSubContrDrInfo(info, subContr);
			// 2贷方信息
			this.initOrgInfo(info, subContr.getHandlingOrgCd());
			info.setPledgePersonName(info.getCrName());// 质权人为贷方机构名称
			// 3主合同信息
			this.initContrInfo(info, subContr.getContractId(), subContr.getProjectId());
			this.initPledgeInfo(info, subContr.getGuarantyId());
			return info;
		}
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public ScpListBaseInfo findScpListBaseInfo(Long subContractId) {
		SubContract subContr = subContractDao.findOne(subContractId);
		ProjectPawnInfo ppi=projectPawnInfoDao.findOne(subContr.getGuarantyId());
		Collateral colla = collateralDao.findOne(ppi.getGuarantyId());
		
		final String guarantyTypeCd = colla.getGuarantyTypeCd();
		if (StringUtils.equals(dataDict.getCodeVal(RESV_ASSET_TYPE_CODE, "S5"), guarantyTypeCd)) {// 质押财产清单(存单)
			ScpListS6Info info = new ScpListS6Info();
			this.initCollaInfo(info, "S4", subContr, colla);
			info.setScpListName(this.getContractTypeName("S6"));
			return info;
		} else if (StringUtils.equals(dataDict.getCodeVal(RESV_ASSET_TYPE_CODE, "S6"), guarantyTypeCd)) {// 质押财产清单(动产)
			ScpListS7Info info = new ScpListS7Info();
			this.initCollaInfo(info, "S4", subContr, colla);
			info.setScpListName(this.getContractTypeName("S7"));// 质押财产清单(动产)
			return info;
		} else if (StringUtils.equals(dataDict.getCodeVal(RESV_ASSET_TYPE_CODE, "S7"), guarantyTypeCd)) {// 质押财产清单(股权)
			ScpListS8Info info = new ScpListS8Info();
			this.initCollaInfo(info, "S4", subContr, colla);
			info.setScpListName(this.getContractTypeName("S8"));// 质押财产清单(股权)
			return info;
		} else if (StringUtils.equals(dataDict.getCodeVal(RESV_ASSET_TYPE_CODE, "S8"), guarantyTypeCd)) {// 质押财产清单(国债)
			ScpListS9Info info = new ScpListS9Info();
			this.initCollaInfo(info, "S4", subContr, colla);
			info.setScpListName(this.getContractTypeName("S9"));// 质押财产清单(国债)
			return info;
		} else if (StringUtils.equals(dataDict.getCodeVal(RESV_ASSET_TYPE_CODE, "S9"), guarantyTypeCd)) {// 质押财产清单(汇票本票支票)
			ScpListS10Info info = new ScpListS10Info();
			this.initCollaInfo(info, "S4", subContr, colla);
			info.setScpListName(this.getContractTypeName("S10"));// 质押财产清单(汇票本票支票)
			return info;
		} else if (StringUtils.equals(dataDict.getCodeVal(RESV_ASSET_TYPE_CODE, "S10"), guarantyTypeCd)) {// 质押财产清单(其它权利)
			ScpListS11Info info = new ScpListS11Info();
			this.initCollaInfo(info, "S4", subContr, colla);
			info.setScpListName(this.getContractTypeName("S11"));
			return info;
		} else if (StringUtils.equals(dataDict.getCodeVal(RESV_ASSET_TYPE_CODE, "S11"), guarantyTypeCd)) {// 质押财产清单(收费权)
			ScpListS12Info info = new ScpListS12Info();
			this.initCollaInfo(info, "S4", subContr, colla);
			info.setScpListName(this.getContractTypeName("S12"));
			return info;
		} else if (StringUtils.equals(dataDict.getCodeVal(RESV_ASSET_TYPE_CODE, "S12"), guarantyTypeCd)) {// 质押财产清单(提单)
			ScpListS13Info info = new ScpListS13Info();
			this.initCollaInfo(info, "S4", subContr, colla);
			info.setScpListName(this.getContractTypeName("S13"));
			return info;
		} else if (StringUtils.equals(dataDict.getCodeVal(RESV_ASSET_TYPE_CODE, "S13"), guarantyTypeCd)) {// 质押财产清单(应收账款)
			ScpListS14Info info = new ScpListS14Info();
			this.initCollaInfo(info, "S4", subContr, colla);
			info.setScpListName(this.getContractTypeName("S14"));
			return info;
		} else if (StringUtils.equals(dataDict.getCodeVal(RESV_ASSET_TYPE_CODE, "S14"), guarantyTypeCd)) {// 质押财产清单(债券)
			ScpListS15Info info = new ScpListS15Info();
			this.initCollaInfo(info, "S4", subContr, colla);
			info.setScpListName(this.getContractTypeName("S15"));
			return info;
		} else if (StringUtils.equals(dataDict.getCodeVal(RESV_ASSET_TYPE_CODE, "S15"), guarantyTypeCd)) {// 质押物清单(仓单)
			ScpListS16Info info = new ScpListS16Info();
			this.initCollaInfo(info, "S4", subContr, colla);
			info.setScpListName(this.getContractTypeName("S16"));
			return info;
		} else if (StringUtils.equals(dataDict.getCodeVal(RESV_ASSET_TYPE_CODE, "S16"), guarantyTypeCd)) {// 质押财产清单(其它权利)
			ScpListS17Info info = new ScpListS17Info();
			this.initCollaInfo(info, "S4", subContr, colla);
			info.setScpListName("质押财产清单(其它权利)");
			return info;
		}
		return null;
	}

	/**
	 * @param info
	 * @param subContrTypeKey
	 * @param subContr
	 * @param colla
	 */
	private void initCollaInfo(ScpListBaseInfo info, String subContrTypeKey, SubContract subContr, Collateral colla) {
		info.setSubContractNum(subContr.getSubcontractNum());// 从合同编号
		info.setSubContractName(this.getContractTypeName(subContrTypeKey));
		info.setScpListNum(TemplateDataHelper.defaultString(colla.getGuarantyNum()));
		info.setReceiptNum(TemplateDataHelper.defaultString(colla.getRightCertificationNum()));
		info.setOtherDesc(TemplateDataHelper.defaultString(colla.getOtherNote()));
		ProjectPawnInfo pawnInfo = this.findProjectPawnInfo(subContr.getGuarantyId());
		/**
		 * 质押率取值规则 如果存在本次实际担保率，则取之，否则取担保率
		 */
		if (pawnInfo != null) {
			BigDecimal bd = pawnInfo.getActualGuaranteeRate();
			if (bd == null) {
				bd = pawnInfo.getPawnRatio();
			}
			if (bd != null) {
				bd = bd.multiply(new BigDecimal(100)).stripTrailingZeros();
			}
			info.setRatePledge(TemplateDataHelper.defaultString(bd.toString()));
		} else {
			info.setRatePledge(TemplateDataHelper.EMPTY);
		}
		info.setPledgeName(TemplateDataHelper.defaultString(colla.getGuarantyName()));
		info.setRightProofTypeName(TemplateDataHelper.defaultString(this.getRightProofTypeName(colla.getRightCertTypeCd())));
		info.setRightProofNum(TemplateDataHelper.defaultString(colla.getRightCertificationNum()));
		info.setAssessValue(colla.getEvalValue() != null ? colla.getEvalValue().toString() : TemplateDataHelper.EMPTY);
		info.setCommOwnerName(TemplateDataHelper.defaultString(colla.getPartOwnerName()));
		info.setYesSetValueFlag(StringUtils.equals(colla.getGuarantySetupInd(), "1") ? "是" : "否");
	}

	@Override
	public String getContractTypeName(String contractTypeKey) {
		if (StringUtils.isBlank(contractTypeKey)) {
			return null;
		}
		return dataDict.getCodeName(CONTRACT_TYPE_CD, dataDict.getCodeVal(CONTRACT_TYPE_CD, contractTypeKey));
	}

	private String getRightProofTypeName(String rightCertTypeCd) {
		if (StringUtils.isBlank(rightCertTypeCd)) {
			return null;
		}
		return dataDict.getCodeName(GUARANTY_CERTIFICATE, rightCertTypeCd);
	}

	/**
	 * 初始化借方信息
	 * 
	 * @param info
	 * @param subContr
	 */
	private void initSubContrDrInfo(SubContrBaseInfo info, SubContract subContr) {
		info.setSubContractNum(subContr.getSubcontractNum());// 子合同编号
		StringBuffer pledgorName = new StringBuffer();// 保证人
		StringBuffer pledgorPaperworkType = new StringBuffer();// 保证人证件类型
		StringBuffer pledgorPaperworkNum = new StringBuffer();// 保证人证件编号
		String pledgorLegalRepr = null;// 保证方法定代表人
		String pledgorPost = null;// 保证方职位
		StringBuffer pledgorAddr = new StringBuffer();// 保证住址
		StringBuffer pledgorContactAddr = new StringBuffer();// 保证人联系地址
		StringBuffer pledgorPhoneNum = new StringBuffer();// 保证人联系电话
		Party party = partyDao.findOne(subContr.getCustomerId());
		if (StringUtils.equals(dataDict.getCodeVal("CustomerType", "S1"), party.getPartyTypeCd())) {// 企业
			CorporationCustomer corpCust = corporationCustomerDao.findOne(party.getPartyId());
			pledgorPhoneNum.append(corpCust.getLinkmanTel());
			if (corpCust != null) {
				List<CorpCustomerRelaPerson> ccrpList = this.findCorpCustomerRelaPersonList(party.getPartyId(), dataDict.getCodeVal("RepresentativeFlag", "S1"));
				if (!CollectionUtils.isEmpty(ccrpList)) {
					pledgorLegalRepr = ccrpList.get(0).getName();// 法定代表人
					pledgorPost = dataDict.getCodeName("Professional", ccrpList.get(0).getProfessional());// 法定代表人职务
				}
				// 地址
				List<Address> addrList = this.findAddressList(party.getPartyId());
				if (!CollectionUtils.isEmpty(addrList)) {
					final String ADDR_TYPE_LINK = dataDict.getCodeVal("AddressType", "S1");// 通讯地址
					pledgorAddr.append(TemplateDataHelper.BLANK_20);
					Set<String> codes = Sets.newHashSet();
					Map<String, String> map = null;
					for (Address address : addrList) {
						codes.clear();
						if (StringUtils.equals(ADDR_TYPE_LINK, address.getAddressTypeCd())) {
							initSet(codes, address.getNationalityCd());
							initSet(codes, address.getProvinceCd());
							initSet(codes, address.getCityCd());
							initSet(codes, address.getCountyCd());
							map = this.findProvincesMap(codes);
							initStringBuffer(map, address.getNationalityCd(), pledgorContactAddr);
							initStringBuffer(map, address.getProvinceCd(), pledgorContactAddr);
							initStringBuffer(map, address.getCityCd(), pledgorContactAddr);
							initStringBuffer(map, address.getCountyCd(), pledgorContactAddr);
							pledgorContactAddr.append(address.getStreetAddress());
							break;
						} else {
							continue;
						}
					}
				}
			}
		} else if (StringUtils.equals(dataDict.getCodeVal("CustomerType", "S2"), party.getPartyTypeCd())) {// 个人
			Individual individual = customerIndividualDao.findOne(party.getPartyId());
			if (individual != null) {
				pledgorPhoneNum.append(individual.getMobileTel());
				pledgorAddr.append(individual.getFamilyAddress());
			}
		}
		// 1.保证方
		info.setPledgorName(pledgorName.toString());
		info.setPledgorPaperworkType(pledgorPaperworkType.toString());
		info.setPledgorPaperworkNum(pledgorPaperworkNum.toString());
		info.setPledgorLegalRepr(TemplateDataHelper.formatLength(pledgorLegalRepr, 10));
		info.setPledgorPost(TemplateDataHelper.formatLength(pledgorPost, 10));
		info.setPledgorAddr(pledgorAddr.toString());
		info.setPledgorContactAddr(pledgorContactAddr.toString());
		info.setPledgorPhoneNum(pledgorPhoneNum.toString());
	}

	/**
	 * 初始化抵押物信息
	 * 
	 * @param info
	 * @param guarantyId
	 */
	private void initMortInfo(SubContrMortInfo info, Long guarantyId) {
		ProjectPawnInfo ppi =projectPawnInfoDao.findOne(guarantyId);//
		if(ppi==null){
			return ;
		}
		Collateral collateral = collateralDao.findOne(ppi.getGuarantyId());
		if (collateral != null) {
			info.setMortName(collateral.getGuarantyName());// 质押财产名称
			BigDecimal bd = ppi.getEvalValue();// 评估价值
			if (bd != null) {
				bd = bd.divide(new BigDecimal(10000));
				info.setMortAssessValue(bd.toPlainString());
			} else {
				info.setMortAssessValue(TemplateDataHelper.BLANK_10);
			}
			bd = ppi.getSetGuaranteeAmt();
			if (bd != null) {
				bd = bd.divide(new BigDecimal(10000));
				info.setMortYesSetValue(bd.toPlainString());
			} else {
				info.setMortYesSetValue(TemplateDataHelper.BLANK_10);
			}
			info.setPledgorName(collateral.getGuarantorName());
			info.setPledgorPaperworkType(this.getPaperWorkTypeName(collateral.getGuarantorTypeCd()));
			info.setPledgorPaperworkNum(collateral.getGuarantorCertificateNum());
		}
	}

	/**
	 * 质押物信息初始化
	 * 
	 * @param info
	 * @param guarantyId
	 */
	private void initPledgeInfo(SubContrPledgeInfo info, Long guarantyId) {
		ProjectPawnInfo ppi =projectPawnInfoDao.findOne(guarantyId);//
		if(ppi==null){
			return ;
		}
		Collateral collateral = collateralDao.findOne(ppi.getGuarantyId());
		if (collateral != null) {
			info.setPledgorName(collateral.getGuarantorName());
			info.setPledgorPaperworkType(this.getPaperWorkTypeName(collateral.getGuarantorTypeCd()));
			info.setPledgorPaperworkNum(collateral.getGuarantorCertificateNum());
			info.setPledgeName(collateral.getGuarantyName());// 质押物名称
		}
	}

	/**
	 * 初始化机构信息
	 * 
	 * @param info
	 * @param deptId
	 */
	private void initOrgInfo(SubContrBaseInfo info, Long deptId) {
		OrgInfo orgInfo = this.findOrgInfo(deptId);
		if (orgInfo != null) {
			info.setCompanyName(orgInfo.getName());
			info.setCrName(orgInfo.getName());
			info.setCrPaperworkType(TemplateDataHelper.BLANK_20);
			info.setCrPaperworkNum(TemplateDataHelper.BLANK_20);
			info.setCrLegalRepr(TemplateDataHelper.BLANK_20);
			info.setCrPost(TemplateDataHelper.BLANK_20);
			info.setCrAddr(TemplateDataHelper.BLANK_20);
			info.setCrContactAddr(orgInfo.getAddress());
			info.setCrPhoneNum(orgInfo.getFixedPhone());
		}
	}

	/**
	 * 初始化主合同信息
	 * 
	 * @param info
	 * @param contractId
	 * @param projectId
	 */
	private void initContrInfo(SubContrBaseInfo info, Long contractId, Long projectId) {
		Contract contr = contractDao.findOne(contractId);
		ProjectApplication pa = projectApplicationDao.findOne(projectId);
		String contrType = "";
		if (StringUtils.equals("1", pa.getBusinessProcessType())) {
			contrType = dataDict.getCodeVal("ContractTypeCd", "S1");
		} else {
			contrType = dataDict.getCodeVal("ContractTypeCd", "S2");
		}
		info.setDebtorName(TemplateDataHelper.formatLength(contr.getCustomerName(), 10));
		info.setContrTypeName(dataDict.getCodeName("ContractTypeCd", contrType));
		info.setContrNum(contr.getContractNum());
		String contrAmt = MoneyUtil.formatMoney(contr.getContractAmt());
		info.setContrAmt(contrAmt);
		info.setContrAmtCN(MoneyUtil.CNValueOf(contrAmt));
		info.setProductName(this.findProductTypeName(pa.getProductType()));
		info.setCurrencyType(dataDict.getCodeName("CurrencyType", pa.getCurrency()));
	}

	/**
	 * 查询高管
	 * 
	 * @param partyId
	 * @param representativeFlag
	 * @return
	 */
	private List<CorpCustomerRelaPerson> findCorpCustomerRelaPersonList(Long partyId, String personIsLegalRepresent) {
		StringBuffer strBuffer = new StringBuffer();
		strBuffer.append("from CorpCustomerRelaPerson ccrp");
		strBuffer.append(" where ccrp.partyId=?1 AND ccrp.personIsLegalRepresent=?2");
		return dynamicQuery.query(CorpCustomerRelaPerson.class, strBuffer.toString(), partyId, personIsLegalRepresent);
	}

	/**
	 * @param partyId
	 * @return
	 */
	private List<Address> findAddressList(Long partyId) {
		return dynamicQuery.query(Address.class, "from Address addr where addr.partyId=?1", partyId);
	}

	/**
	 * @param partyId
	 * @param correlativeCustomerTypeCd
	 * @return
	 */
	private List<CustomerCorrelativeRelations> findCustCorrList(Long partyId, String correlativeCustomerTypeCd) {
		String str = "from CustomerCorrelativeRelations ccr where ccr.partyId=?1 and ccr.correlativeCustomerTypeCd=?2";
		return dynamicQuery.query(CustomerCorrelativeRelations.class, str, partyId, correlativeCustomerTypeCd);
	}

	/**
	 * 
	 * @param guarantyId
	 * @return
	 */
	private ProjectPawnInfo findProjectPawnInfo(Long guarantyId) {
		List<ProjectPawnInfo> ppis = projectPawnInfoDao.findByGuarantyId(guarantyId);
		if (CollectionUtils.isEmpty(ppis)) {
			return null;
		}
		return ppis.get(0);
	}

	/**
	 * 获取证件类型名称
	 * 
	 * @param paperWorkTypeCd 证件类型编号
	 * @return
	 */
	private String getPaperWorkTypeName(String paperWorkTypeCd) {
		return dataDict.getCodeName("CertificateType", paperWorkTypeCd);
	}

	/**
	 * 为str增加前缀,
	 * 
	 * @param str
	 * @return
	 */
	private String strAddPrefix(String str) {
		if (StringUtils.isBlank(str)) {
			return StringUtils.EMPTY;
		}
		return "," + str;
	}

	private void initSet(Set<String> set, String str) {
		if (StringUtils.isBlank(str)) {
			return;
		}
		set.add(str);
	}

	private void initStringBuffer(Map<String, String> map, String key, StringBuffer strBuffer) {
		if (StringUtils.isBlank(key) || StringUtils.isBlank(map.get(key))) {
			return;
		}
		strBuffer.append(map.get(key));
	}

	private Map<String, String> findProvincesMap(Set<String> codes) {
		Map<String, String> map = Maps.newHashMap();
		if (CollectionUtils.isEmpty(codes)) {
			return map;
		}
		List<Object[]> list = dynamicQuery.nativeQuery("select na.nation_area_cd,na.nation_area_name from nation_area na where na.nation_area_cd in (?1)", codes);
		if (!CollectionUtils.isEmpty(list)) {
			for (Object[] objs : list) {
				map.put(String.valueOf(objs[0]), String.valueOf(objs[1]));
			}
		}
		return map;
	}

	private String findProductTypeName(String productTypeCd) {
		List<Product> list = dynamicQuery.query(Product.class, "from Product p where p.productCd=?1", Long.parseLong(productTypeCd));
		if (CollectionUtils.isEmpty(list) || list.get(0) == null) {
			return null;
		}
		return list.get(0).getProductName();
	}

	/**
	 * 根据机构id查询机构信息
	 * 
	 * @param deptId
	 * @return
	 */
	private OrgInfo findOrgInfo(final Long deptId) {
		StringBuffer strBuffer = new StringBuffer();
		strBuffer.append("select eod.name,eodd.address,eodd.fixedphone from ec_org_department eod").append(" left join ec_org_departmentdetails eodd on eod.id=eodd.id where eod.id=?1");
		List<Object[]> list = dynamicQuery.nativeQuery(strBuffer.toString(), deptId);
		if (CollectionUtils.isEmpty(list)) {
			return null;
		}
		OrgInfo info = new OrgInfo(list.get(0));
		return info;
	}

	private class OrgInfo {
		private String name;
		private String address;
		private String fixedPhone;

		public OrgInfo(Object[] objs) {
			super();
			this.name = CommonHelper.toStr(objs[0]);
			this.address = CommonHelper.toStr(objs[1]);
			this.fixedPhone = CommonHelper.toStr(objs[2]);
		}

		public String getName() {
			return name;
		}

		public String getAddress() {
			return address;
		}

		public String getFixedPhone() {
			return fixedPhone;
		}
	}

	@Override
	public LoanContractInfo getLoanContract(Long contractId) {
		LoanContractInfo lcInfo = new LoanContractInfo();
		Contract contr = contractDao.findOne(contractId);
		ProjectApplication pa = projectApplicationDao.findOne(contr.getProjectId());
		BizRate bizRate = bizRateDao.findByProjectId(String.valueOf(contr.getProjectId()));
		Party party = partyDao.findByPartyId(contr.getPartyId());
		DebitInfo debitInfo = null;
		// 个人客户
		Individual indiv = customerIndividualDao.findByPartyId(contr.getPartyId());
		debitInfo = lcInfo.new DebitInfo(party.getPartyName(),// 客户名
		dataDict.getCodeName("CertificateType", party.getCertificateTypeCd()),// 证件类型
		party.getCertificateNum(),// 证件号
		TemplateDataHelper.BLANK_10,// 营业执照号码
		TemplateDataHelper.BLANK_10,// 法定代表人
		TemplateDataHelper.formatLength(indiv.getFamilyAddress(), 20),// 住址/地址
		TemplateDataHelper.formatLength(indiv.getFamilyAddress(), 20),// 通讯地址
		TemplateDataHelper.formatLength(indiv.getMobileTel(), 20)// 电话号码/手机号
		);
		lcInfo.setDebitInfo(debitInfo);
		lcInfo.setContractNum(contr.getContractNum());
		// 放款人信息
		List loanList = this.findLoanerInfo(contr.getApplyOrgId());
		CreditInfo creditInfo = null;
		if (loanList.size() > 0) {
			creditInfo = lcInfo.new CreditInfo(TemplateDataHelper.formatLength((String) loanList.get(0), 15),// 名字
			TemplateDataHelper.formatLength((String) loanList.get(1), 15),// 法定代表人
			TemplateDataHelper.formatLength((String) loanList.get(2), 15),// 地址
			TemplateDataHelper.formatLength((String) loanList.get(3), 15)// 手机号
			);
		}
		lcInfo.setCreditInfo(creditInfo);
		lcInfo.setContractCompanyName((String) loanList.get(0));
		Product product = productDao.findByProductCd(new Long(pa.getProductType()));
		String wf = productConfigDao.findWfCodeByProductCd(new Long(pa.getProductType()));
		if (StringUtils.equals(wf, GlobalConstants.EL_WF_TYPE_30)) {
			pa.setPurpose(dataDict.getCodeName("LoanPurpose", pa.getPurpose()));
		}
		lcInfo.setLoanTypeAndPurpose(TemplateDataHelper.formatLength(product.getProductName(), 10), TemplateDataHelper.formatLength(pa.getPurpose(), 10));
		if (contr.getContractAmt() != null) {
			lcInfo.setLoanAmtCapital(TemplateDataHelper.formatLength(MoneyUtil.CNValueOf(contr.getContractAmt()
					+ ""), 10));
			lcInfo.setLoanAmtLower(TemplateDataHelper.formatLength(contr.getContractAmt()
					+ "", 10));
		}
		ContractForm contractForm = lcInfo.new ContractForm(TemplateDataHelper.BLANK_10, TemplateDataHelper.BLANK_10, TemplateDataHelper.BLANK_10, TemplateDataHelper.BLANK_10, TemplateDataHelper.BLANK_10);
		contractForm.addItem(TemplateDataHelper.BLANK_20, TemplateDataHelper.BLANK_20, TemplateDataHelper.BLANK_20);
		lcInfo.setContractForm(contractForm);
		// 贷款期限
		ContractTerm contractTerm = lcInfo.new ContractTerm(new Integer(contr.getContractTermUnit()));
		List<String> term = Lists.newArrayList();
		for (int i = 1; i <= 3; i++) {
			if (Integer.valueOf(contr.getContractTermUnit()).compareTo(i) == 0) {
				term.add(contr.getContractTerm() + "");
			} else {
				term.add(null);
			}
		}
		contractTerm.addItem(term);
		lcInfo.setContractTerm(contractTerm);
		// 利率
		List<String> rates = Lists.newArrayList();
		rates.add(bizRate.getFinalIrTypeCd());
		if (StringUtils.equals(bizRate.getFinalIrTypeCd(), dataDict.getCodeVal("InterestRateAdjustment", "S1"))) {
			// 固定利率
			rates.add(bizRate.getFinalRateValue().movePointRight(2) + "");
			rates.add(TemplateDataHelper.BLANK_20);
			rates.add(TemplateDataHelper.BLANK_20);
		} else if (StringUtils.equals(bizRate.getFinalIrTypeCd(), dataDict.getCodeVal("InterestRateAdjustment", "S2"))) {
			rates.add(TemplateDataHelper.BLANK_20);
			rates.add(bizRate.getFinalFloatRate().movePointRight(2) + "");
			rates.add(dataDict.getCodeName("AdjustPeriod", bizRate.getFinalAdjustPeriod()));
		}
		IntRate intRate = lcInfo.new IntRate(rates);
		lcInfo.setIntRate(intRate);
		// 还款方式
		Integer repayCd = Integer.valueOf(contr.getRepayModeCd()) > 3 ? 3 : Integer.valueOf(contr.getRepayModeCd());
		RepaymentMode repaymentMode = lcInfo.new RepaymentMode(repayCd, contr.getBankName(), TemplateDataHelper.BLANK_20, contr.getLoanNum());
		if (!StringUtils.equals(dataDict.getCodeVal("RepaymentMode", "S2"), contr.getRepayModeCd())
				&& !StringUtils.equals(dataDict.getCodeVal("RepaymentMode", "S1"), contr.getRepayModeCd())) {
			repaymentMode.setItem3(dataDict.getCodeName("RepaymentMode", contr.getRepayModeCd()));
		}
		lcInfo.setRepaymentMode(repaymentMode);
		lcInfo.setNoticeDrDayOfPrepayment(30);
		lcInfo.setScaleOfSeverance(TemplateDataHelper.BLANK_10);
		lcInfo.setPeIntRateOfUseChange(TemplateDataHelper.BLANK_10);
		lcInfo.setPeIntRateOfOverdue(TemplateDataHelper.BLANK_10);
		lcInfo.setClearFee(TemplateDataHelper.BLANK_10);
		lcInfo.setBreakContRate(TemplateDataHelper.BLANK_10);
		return lcInfo;
	}

	public List<String> findLoanerInfo(Long orgId) {
		StringBuffer sql = new StringBuffer(" SELECT ");
		sql.append(" eod.name, eod.principal, dd.address, dd.fixedphone ");
		sql.append(" FROM ");
		sql.append(" ec_org_department eod, ec_org_departmentdetails dd ");
		sql.append(" WHERE eod.id= ?1 AND eod.id=dd.id");
		List<Object> params = new ArrayList<Object>();
		params.add(String.valueOf(orgId));
		List<String> valueList = new ArrayList<String>();
		List<Object[]> queryList = dynamicQuery.nativeQuery(sql.toString(), params);
		if (!CollectionUtils.isEmpty(queryList)) {
			for (int i = 0; i < queryList.get(0).length; i++) {
				valueList.add(queryList.get(0)[i].toString());
			}
		}
		return valueList;
	}

	@Override
	public LoanCorpContractInfo getLoanCorpContract(Long contractId) {
		// 企业客户
		LoanCorpContractInfo lcInfo = new LoanCorpContractInfo();
		Contract contr = contractDao.findOne(contractId);
		ProjectApplication pa = projectApplicationDao.findOne(contr.getProjectId());
		BizRate bizRate = bizRateDao.findByProjectId(String.valueOf(contr.getProjectId()));
		Party party = partyDao.findByPartyId(contr.getPartyId());
		CorporationCustomer corpCur = corporationCustomerDao.findOne(contr.getPartyId());
		List<Address> addressList = addressDao.findByPartyId(party.getPartyId());
		String corpAddress = "";
		if (CollectionUtils.isNotEmpty(addressList)) {
			Address addr = addressList.get(0);
			corpAddress = dataDict.getCodeName("NationArea", addr.getNationalityCd())
					+ dataDict.getCodeName("NationArea", addr.getProvinceCd())
					+ dataDict.getCodeName("NationArea", addr.getCityCd())
					+ dataDict.getCodeName("NationArea", addr.getCountyCd());
		}
		LoanCorpContractInfo.DebitInfo debitInfo = lcInfo.new DebitInfo(party.getPartyName(),// 客户名
		corpCur.getCertificateNum(),// 证件号
		TemplateDataHelper.formatLength(corpCur.getBusinessLicenseNum(), 20),// 营业执照号码
		TemplateDataHelper.formatLength(corpCur.getLinkmanName(), 20),// 法定代表人
		TemplateDataHelper.formatLength(corpAddress, 20),// 住址/地址
		TemplateDataHelper.formatLength(corpAddress, 20),// 通讯地址
		TemplateDataHelper.formatLength(corpCur.getLinkmanTel(), 20)// 电话号码/手机号
		);
		lcInfo.setDebitInfo(debitInfo);
		lcInfo.setContractNum(contr.getContractNum());
		// 借款人信息
		List loanList = this.findLoanerInfo(contr.getApplyOrgId());
		LoanCorpContractInfo.CreditInfo creditInfo = null;
		if (loanList.size() > 0) {
			creditInfo = lcInfo.new CreditInfo(TemplateDataHelper.formatLength((String) loanList.get(0), 15),// 名字
			TemplateDataHelper.formatLength((String) loanList.get(1), 15),// 法定代表人
			TemplateDataHelper.formatLength((String) loanList.get(2), 15),// 地址
			TemplateDataHelper.formatLength((String) loanList.get(3), 15)// 手机号
			);
		}
		lcInfo.setCreditInfo(creditInfo);
		// 机构名
		lcInfo.setContractCompanyName((String) loanList.get(0));
		// 贷款用途
		lcInfo.setLoanPurpose(TemplateDataHelper.formatLength(pa.getPurpose(), 10));
		// 贷款金额
		if (contr.getContractAmt() != null) {
			lcInfo.setLoanAmtCapital(TemplateDataHelper.formatLength(MoneyUtil.CNValueOf(contr.getContractAmt()
					+ ""), 10));
			lcInfo.setLoanAmtLower(TemplateDataHelper.formatLength("￥"+contr.getContractAmt(), 10));
		}
		// 贷款期限
		LoanCorpContractInfo.ContractTerm contractTerm = lcInfo.new ContractTerm(new Integer(contr.getContractTermUnit()));
		List<String> term = Lists.newArrayList();
		for (int i = 1; i <= 3; i++) {
			if (Integer.valueOf(contr.getContractTermUnit()).compareTo(i) == 0) {
				term.add(contr.getContractTerm() + "");
			} else {
				term.add(null);
			}
		}
		contractTerm.addItem(term);
		lcInfo.setContractTerm(contractTerm);
		// 利率
		List<String> rates = Lists.newArrayList();
		rates.add(bizRate.getFinalIrTypeCd());
		if (StringUtils.equals(bizRate.getFinalIrTypeCd(), dataDict.getCodeVal("InterestRateAdjustment", "S1"))) {
			// 固定利率
			rates.add(bizRate.getFinalRateValue().movePointRight(2) + "");
			rates.add(TemplateDataHelper.BLANK_20);
			rates.add(TemplateDataHelper.BLANK_20);
		} else if (StringUtils.equals(bizRate.getFinalIrTypeCd(), dataDict.getCodeVal("InterestRateAdjustment", "S2"))) {
			rates.add(TemplateDataHelper.BLANK_20);
			rates.add(bizRate.getFinalFloatRate().movePointRight(2) + "");
			rates.add(dataDict.getCodeName("AdjustPeriod", bizRate.getFinalAdjustPeriod()));
		}
		LoanCorpContractInfo.IntRate intRate = lcInfo.new IntRate(rates);
		lcInfo.setIntRate(intRate);
		//担保信息
		List<ProjectAssurerInfo> paiList = projectAssurerInfoDao.findByProjectId(contr.getProjectId());
		GuarantorInfo guarantorInfo = null;
		String guarantorName = "";
		String subcontractNum = "";
		if(!CollectionUtils.isEmpty(paiList)){
			 guarantorName = paiList.get(0).getCustomerName();
			 subcontractNum = subContractDao.findByAssurerId(paiList.get(0).getProjectAssurerInfoId()).getContractNum();
			
		}
		guarantorInfo = lcInfo.new GuarantorInfo(
					TemplateDataHelper.formatLength(guarantorName, 10), 
					TemplateDataHelper.formatLength(subcontractNum, 10)
		 );
		lcInfo.setGuarantorInfo(guarantorInfo);
		// 还款方式
		Integer repayCd = Integer.valueOf(contr.getRepayModeCd()) > 3 ? 3 : Integer.valueOf(contr.getRepayModeCd());
		LoanCorpContractInfo.RepaymentMode repaymentMode = lcInfo.new RepaymentMode(repayCd, contr.getBankName(), TemplateDataHelper.BLANK_10, contr.getLoanNum());
		if (!StringUtils.equals(dataDict.getCodeVal("RepaymentMode", "S2"), contr.getRepayModeCd())
				&& !StringUtils.equals(dataDict.getCodeVal("RepaymentMode", "S1"), contr.getRepayModeCd())) {
			repaymentMode.setItem3(dataDict.getCodeName("RepaymentMode", contr.getRepayModeCd()));
		}
		lcInfo.setRepaymentMode(repaymentMode);
		lcInfo.setNoticeDrDayOfPrepayment("30");
		lcInfo.setScaleOfSeverance(TemplateDataHelper.BLANK_10);
		lcInfo.setNoticeDrDayOfDangerous(TemplateDataHelper.BLANK_10);
		lcInfo.setNoticeDrDayOfExtendEndTime(TemplateDataHelper.BLANK_10);
		lcInfo.setLoanOverduePunitiveInterestRate(TemplateDataHelper.BLANK_10);
		lcInfo.setLoanFundsPunitiveInterestRate(TemplateDataHelper.BLANK_10);
		lcInfo.setCopyTotal(TemplateDataHelper.BLANK_10);
		return lcInfo;
	}

	@Override
	public String findPartyTypeByContractId(Long contractId) {
		String sql = "select p.party_type_cd from party p left join contract contr "
				+ "on contr.party_id = p.party_id "
				+ "where contr.contract_id = ?1 ";
		List<String> list = dynamicQuery.nativeQuery(String.class, sql, contractId);
		return CollectionUtils.isEmpty(list) ? null : list.get(0);
	}

	@Override
	public LoanContractInfo assembleCreditContractIndividual(Long creditContractId) {
		LoanContractInfo creditContractIndividual = new LoanContractInfo();
		CreditContract creditContract = creditContractDao.findOne(creditContractId);
		ProjectApplication pa = projectApplicationDao.findOne(creditContract.getProjectId());
		Party party = partyDao.findByPartyId(creditContract.getPartyId());
		/** 组装借款人信息start */
		//个人客户
		Individual indiv = customerIndividualDao.findByPartyId(creditContract.getPartyId());
		DebitInfo debitInfo = creditContractIndividual.new DebitInfo(
				party.getPartyName(),													// 客户名
				dataDict.getCodeName("CertificateType", party.getCertificateTypeCd()),	// 证件类型
				party.getCertificateNum(),												// 证件号码
				TemplateDataHelper.BLANK_10,											// 营业执照号码
				TemplateDataHelper.BLANK_10,											// 法定代表人
				TemplateDataHelper.formatLength(indiv.getFamilyAddress(), 20),			// 住址/地址
				TemplateDataHelper.formatLength(indiv.getFamilyAddress(), 20),			// 通讯地址
				TemplateDataHelper.formatLength(indiv.getMobileTel(), 20)				// 电话号码/手机号
		);
		creditContractIndividual.setDebitInfo(debitInfo);
		creditContractIndividual.setContractNum(creditContract.getContractNum());
		/** 组装借款人信息end */
		
		/** 组装贷款人信息start */
		//放款人信息
		List<String> loanList = this.findLoanerInfo(creditContract.getApplyOrgId());
		CreditInfo creditInfo = null;
		if (loanList.size() > 0) {
			creditInfo = creditContractIndividual.new CreditInfo(
				TemplateDataHelper.formatLength((String) loanList.get(0), 15),	// 名字
				TemplateDataHelper.formatLength((String) loanList.get(1), 15),	// 法定代表人
				TemplateDataHelper.formatLength((String) loanList.get(2), 15),	// 地址
				TemplateDataHelper.formatLength((String) loanList.get(3), 15)	// 手机号
			);
		}
		creditContractIndividual.setCreditInfo(creditInfo);
		creditContractIndividual.setContractCompanyName((String) loanList.get(0));
		/** 组装贷款人信息end */
		
		/** 组装贷款用途和贷款金额start */
		//贷款用途
		Product product = productDao.findByProductCd(new Long(pa.getProductType()));
		String wf = productConfigDao.findWfCodeByProductCd(new Long(pa.getProductType()));
		if (StringUtils.equals(wf, GlobalConstants.EL_WF_TYPE_30)) {
			pa.setPurpose(dataDict.getCodeName("LoanPurpose", pa.getPurpose()));
		}
		creditContractIndividual.setLoanTypeAndPurpose(
				TemplateDataHelper.formatLength(product.getProductName(), 10), 
				TemplateDataHelper.formatLength(pa.getPurpose(), 10));
		//贷款金额
		if (creditContract.getContractAmt() != null) {
			creditContractIndividual.setLoanAmtCapital(
					TemplateDataHelper.formatLength(MoneyUtil.CNValueOf(creditContract.getContractAmt()+ ""), 10));
			creditContractIndividual.setLoanAmtLower(
					TemplateDataHelper.formatLength(creditContract.getContractAmt()+ "", 10));
		}
		/** 组装贷款用途和贷款金额end */
		
		return creditContractIndividual;
	}

	@Override
	public LoanCorpContractInfo assembleCreditContractCorporation(Long creditContractId) {
		LoanCorpContractInfo creditContractCorporation = new LoanCorpContractInfo();
		CreditContract creditContract = creditContractDao.findOne(creditContractId);
		ProjectApplication pa = projectApplicationDao.findOne(creditContract.getProjectId());
		Party party = partyDao.findByPartyId(creditContract.getPartyId());
		
		/** 组装借款人信息start */
		//企业客户
		CorporationCustomer corpCur = corporationCustomerDao.findOne(creditContract.getPartyId());
		List<Address> addressList = addressDao.findByPartyId(party.getPartyId());
		String corpAddress = "";
		if (CollectionUtils.isNotEmpty(addressList)) {
			Address addr = addressList.get(0);
			corpAddress = dataDict.getCodeName("NationArea", addr.getNationalityCd())
					+ dataDict.getCodeName("NationArea", addr.getProvinceCd())
					+ dataDict.getCodeName("NationArea", addr.getCityCd())
					+ dataDict.getCodeName("NationArea", addr.getCountyCd());
		}
		LoanCorpContractInfo.DebitInfo debitInfo = creditContractCorporation.new DebitInfo(
				party.getPartyName(),													//客户名称
				corpCur.getCertificateNum(),											//证件号码
				TemplateDataHelper.formatLength(corpCur.getBusinessLicenseNum(), 20),	//营业执照号码
				TemplateDataHelper.formatLength(corpCur.getLinkmanName(), 20),			//法定代表人
				TemplateDataHelper.formatLength(corpAddress, 20),						//住址/地址
				TemplateDataHelper.formatLength(corpAddress, 20),						//通讯地址
				TemplateDataHelper.formatLength(corpCur.getLinkmanTel(), 20)			//电话号码/手机号
		);
		creditContractCorporation.setDebitInfo(debitInfo);
		creditContractCorporation.setContractNum(creditContract.getContractNum());
		/** 组装借款人信息end */
		
		/** 组装贷款人信息start */
		//放款人信息
		List<String> loanList = this.findLoanerInfo(creditContract.getApplyOrgId());
		LoanCorpContractInfo.CreditInfo creditInfo = null;
		if (loanList.size() > 0) {
			creditInfo = creditContractCorporation.new CreditInfo(
					TemplateDataHelper.formatLength((String) loanList.get(0), 15),	//名字
					TemplateDataHelper.formatLength((String) loanList.get(1), 15),	//法定代表人
					TemplateDataHelper.formatLength((String) loanList.get(2), 15),	//地址
					TemplateDataHelper.formatLength((String) loanList.get(3), 15)	//手机号
			);
		}
		creditContractCorporation.setCreditInfo(creditInfo);
		//机构名
		creditContractCorporation.setContractCompanyName((String) loanList.get(0));
		/** 组装贷款人信息end */
		
		/** 组装贷款用途和贷款金额start */
		//贷款用途
		creditContractCorporation.setLoanPurpose(TemplateDataHelper.formatLength(pa.getPurpose(), 10));
		//贷款金额
		if (creditContract.getContractAmt() != null) {
			creditContractCorporation.setLoanAmtCapital(
					TemplateDataHelper.formatLength(MoneyUtil.CNValueOf(creditContract.getContractAmt()+ ""), 10));
			creditContractCorporation.setLoanAmtLower(
					TemplateDataHelper.formatLength(creditContract.getContractAmt()+ "", 10));
		}
		/** 组装贷款用途和贷款金额end */
		return creditContractCorporation;
	}

	@Override
	public SubContrBaseInfo assembleCreditContractCollaSubContr(Long subContractId) {
		SubContract subContract = subContractDao.findOne(subContractId);
		if(StringUtils.equals(dataDict.getCodeVal("GuaranteeTypeCode", "S1"), subContract.getGuaranteeTypeCd())) {
			SubContrMortInfo subContrMortInfo = new SubContrMortInfo();
			/** 初始化借方信息 */
			this.initSubContrDrInfo(subContrMortInfo, subContract);
			/** 初始化贷方信息 */
			this.initOrgInfo(subContrMortInfo, subContract.getHandlingOrgCd());
			/** 初始化主合同信息 */ 
			this.initCreditContractInfo(subContrMortInfo, subContract.getContractId(), subContract.getProjectId());
			/** 初始化抵押物信息 */
			this.initMortInfo(subContrMortInfo, subContract.getGuarantyId());
			return subContrMortInfo;
		} else if (StringUtils.equals(dataDict.getCodeVal("GuaranteeTypeCode", "S2"), subContract.getGuaranteeTypeCd())) {
			SubContrPledgeInfo subContrPledgeInfo = new SubContrPledgeInfo();
			/** 初始化借方信息 */
			this.initSubContrDrInfo(subContrPledgeInfo, subContract);
			/** 初始化贷方信息 */
			this.initOrgInfo(subContrPledgeInfo, subContract.getHandlingOrgCd());
			subContrPledgeInfo.setPledgePersonName(subContrPledgeInfo.getCrName());// 质权人为贷方机构名称
			/** 初始化主合同信息 */ 
			this.initCreditContractInfo(subContrPledgeInfo, subContract.getContractId(), subContract.getProjectId());
			/** 初始化质押物信息 */
			this.initPledgeInfo(subContrPledgeInfo, subContract.getGuarantyId());
			return subContrPledgeInfo;
		}
		return null;
	}
	
	/**
	 * 初始化主合同信息
	 * 
	 * @param info
	 * @param contractId
	 * @param projectId
	 */
	private void initCreditContractInfo(SubContrBaseInfo subContrBaseInfo, Long contractId, Long projectId) {
		CreditContract creditContract = creditContractDao.findOne(contractId);
		ProjectApplication pa = projectApplicationDao.findOne(projectId);
		String contrType = "";
		if (StringUtils.equals("1", pa.getBusinessProcessType())) {
			contrType = dataDict.getCodeVal("ContractTypeCd", "S1");
		} else {
			contrType = dataDict.getCodeVal("ContractTypeCd", "S2");
		}
		subContrBaseInfo.setDebtorName(TemplateDataHelper.formatLength(creditContract.getCustomerName(), 10));
		subContrBaseInfo.setContrTypeName(dataDict.getCodeName("ContractTypeCd", contrType));
		subContrBaseInfo.setContrNum(creditContract.getContractNum());
		String contrAmt = MoneyUtil.formatMoney(creditContract.getContractAmt());
		subContrBaseInfo.setContrAmt(contrAmt);
		subContrBaseInfo.setContrAmtCN(MoneyUtil.CNValueOf(contrAmt));
		subContrBaseInfo.setProductName(this.findProductTypeName(pa.getProductType()));
		subContrBaseInfo.setCurrencyType(dataDict.getCodeName("CurrencyType", pa.getCurrency()));
	}

	@Override
	public GuarantyContractInfo assembleCreditContractAssureSubContr(Long subContractId) {
		SubContract subContract = subContractDao.findOne(subContractId);
		CreditContract creditContract = creditContractDao.findOne(subContract.getContractId());
		GuarantyContractInfo guarantyContractInfo = new GuarantyContractInfo();
		this.assembleCommonInfo(subContract, guarantyContractInfo);
		String contrType = dataDict.getCodeVal(CONTRACT_TYPE_CD, "S5");
		guarantyContractInfo.setContrTypeName(dataDict.getCodeName(CONTRACT_TYPE_CD, contrType));
		guarantyContractInfo.setDebtorName(creditContract.getCustomerName());
		guarantyContractInfo.setContrNum(creditContract.getContractNum());
		String contrAmt = MoneyUtil.formatMoney(creditContract.getContractAmt());
		guarantyContractInfo.setContrAmt(contrAmt);
		guarantyContractInfo.setContrAmtCN(MoneyUtil.CNValueOf(contrAmt));
		return guarantyContractInfo;
	}
}
