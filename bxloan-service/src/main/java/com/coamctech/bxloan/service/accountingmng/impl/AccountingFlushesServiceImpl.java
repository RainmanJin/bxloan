package com.coamctech.bxloan.service.accountingmng.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coamctech.bxloan.commons.dynamicquery.DynamicQuery;
import com.coamctech.bxloan.dao.EcOrgDepartmentDao;
import com.coamctech.bxloan.dao.EcOrgPersonDao;
import com.coamctech.bxloan.dao.PayLoanInfoDao;
import com.coamctech.bxloan.dao.TallyCertificateDao;
import com.coamctech.bxloan.dao.TallyCertificateDetailDao;
import com.coamctech.bxloan.datadict.DataDict;
import com.coamctech.bxloan.entity.EcOrgDepartment;
import com.coamctech.bxloan.entity.EcOrgPerson;
import com.coamctech.bxloan.entity.Product;
import com.coamctech.bxloan.entity.TallyCertificate;
import com.coamctech.bxloan.entity.TallyCertificateDetail;
import com.coamctech.bxloan.service.accountingmng.AccountingFlushesService;
import com.coamctech.bxloan.service.model.accountingmng.AccountingFlushesVo;
import com.coamctech.bxloan.service.model.accountingmng.BizBaseVo;
import com.coamctech.bxloan.service.model.accountingmng.BizPayLoanVo;
import com.coamctech.bxloan.service.model.accountingmng.AccountingFlushesVo.BillAccountVo;
import com.coamctech.bxloan.service.model.accountingmng.AccountingFlushesVo.BillDetailVo;
import com.coamctech.bxloan.service.model.tipmsg.MsgResult;
import com.coamctech.bxloan.service.model.tipmsg.ResultEnums;
import com.coamctech.bxloan.service.pettyloan.LoanCommonServeice;
import com.coamctech.bxloan.service.pettyloan.LoanTallyCertificateService;
import com.coamctech.bxloan.service.pettyloan.util.AccountingConstants;
import com.coamctech.bxloan.service.pettyloan.util.LoanConstants.BillStatus;
import com.coamctech.bxloan.service.pettyloan.util.LoanConstants.LoanBusinessType;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
@Service
@Transactional
public class AccountingFlushesServiceImpl implements AccountingFlushesService {
	@Autowired
	private DynamicQuery dynamicQuery;
	@Autowired
	private TallyCertificateDao tallyCertificateDao;
	@Autowired
	private TallyCertificateDetailDao tallyCertificateDetailDao;
	@Autowired
	private LoanCommonServeice loanCommonServeice;
	@Autowired
	private PayLoanInfoDao payLoanInfoDao;
	@Autowired
	private LoanTallyCertificateService loanTallyCertificateService;
	@Autowired
	private EcOrgPersonDao orgPersonDao;
	@Autowired
	private EcOrgDepartmentDao ecOrgDepartmentDao;
	@Autowired
	private DataDict dataDict;
	@Override
	public Page<Object[]> findTallyCertificatePage(int pageSize, int pageNum,
			AccountingFlushesVo.Params params) {
		StringBuffer sql = new StringBuffer();
		sql.append("select tc.tally_certificate_id,tc.bill_cd,tc.bill_desc,tc.org_cd,tc.cont_cd,tc.bill_sts,tc.busi_typ_nm,tc.busi_dt,tc.bill_url from tally_certificate tc,contract c where tc.cont_cd=c.contract_num");
		int paramsCount=1;
		List<Object> list=Lists.newArrayList();
		if(params.getCurUserId()!=null){
			sql.append(" and tc.bill_cd in (select distinct ai.txrefno from accounting_info ai where ai.lastmoduser=?").append(paramsCount++).append(")");
			list.add(params.getCurUserId());
		}
		if(StringUtils.isNoneBlank(params.getBillState())){
			sql.append(" and tc.bill_sts=?").append(paramsCount++);
			list.add(params.getBillState());
		}
		if(StringUtils.isNoneBlank(params.getContCd())){
			sql.append(" and tc.cont_cd like ?").append(paramsCount++);
			list.add("%"+params.getContCd()+"%");
		}
		if(StringUtils.isNoneBlank(params.getCustCd())){
			sql.append(" and tc.cust_cd like ?").append(paramsCount++);
			list.add("%"+params.getCustCd()+"%");
		}
		if(StringUtils.isNoneBlank(params.getBusiTypCd())){
			sql.append(" and tc.busi_typ_cd=?").append(paramsCount++);
			list.add(params.getBusiTypCd());
		}
		if(StringUtils.isNoneBlank(params.getCustomerName())){
			sql.append(" and c.customer_name like ?").append(paramsCount++);
			list.add("%"+params.getCustomerName()+"%");
		}
		sql.append(" order by tc.bill_cd desc");
		Page<Object[]> resultList= dynamicQuery.nativeQuery(Object[].class,new PageRequest(pageNum, pageSize), sql.toString(),list.toArray());
		return resultList;
	}
	@Override
	public MsgResult updateTallyCertificateOfFlushes(Long id,String userName) {
		TallyCertificate tc=tallyCertificateDao.findOne(id);
		if(tc==null){
			return MsgResult.getMsgResult(ResultEnums.ACCOUNT_FLUSHES_NO_SELECT);
		}
		//单据未入账
		if(!BillStatus.ENTER.eq(tc.getBillSts())){
			return MsgResult.getMsgResult(ResultEnums.ACCOUNT_FLUSHES_NO_ENTER);
		}
		Integer disFlag = tc.getDisFlag()==null?0:tc.getDisFlag();
		switch (disFlag) {
		case 1://资产转让
			
			break;

		default:
			//正在提前还款中
			if(loanCommonServeice.isAdvanceRepayOfContract(tc.getContCd())){
				return MsgResult.getMsgResult(ResultEnums.ACCOUNT_FLUSHES_ADVANCE_REPAY_CONTRACT);
			}
			//此合同正在展期中
			if (loanCommonServeice.isExtensionOfContact(tc.getContCd())) {
				return MsgResult.getMsgResult(ResultEnums.ACCOUNT_FLUSHES_DOING_ZQ);
			}
			//此合同正在核销中
			if (loanCommonServeice.isVerifCancelOfContact(tc.getContCd())) {
				return MsgResult.getMsgResult(ResultEnums.ACCOUNT_FLUSHES_DOING_HX);
			}
			//合同未入账或是冲正未入账
			if (loanCommonServeice.isNoneEnterOfContact(tc.getContCd())) {
				return MsgResult.getMsgResult(ResultEnums.ACCOUNT_FLUSHES_CONTRACT_NONE_OR_CX_ENTER);
			}
			//单据冲正依次进行，判断是否是最后一条
			if(!loanCommonServeice.isLastTallyCertificate(tc.getBillCd(), tc.getContCd())){
				return MsgResult.getMsgResult(ResultEnums.ACCOUNT_FLUSHES_ORDER_DESC); 
			}
			if(AccountingConstants.BUSITYPCD_LOAN_CODE.equals(tc.getBusiTypCd())){
				//放款
				if(loanCommonServeice.isAllowContractNature(tc.getContCd())){
					return MsgResult.getMsgResult(ResultEnums.ACCOUNT_FLUSHES_NO_PAY); 
				}
			}else if(AccountingConstants.BUSITYPCD_PAID_CODE.equals(tc.getBusiTypCd())){
				//还款
				if(loanCommonServeice.isAcrossYearFlushes(tc.getBillCd(),tc.getContCd())){
					return MsgResult.getMsgResult(ResultEnums.ACCOUNT_FLUSHES_ACROSS_YEAR); 
				}
			}
			break;
		}
		//账务冲正
		loanTallyCertificateService.updateBillOfFlushes(tc.getBillCd(), userName);
		return MsgResult.getMsgResult(ResultEnums.ACCOUNT_FLUSHES_SUCCESS);
	}
	@Override
	public BillDetailVo findBillDetail(Long id) {
		TallyCertificate tc=tallyCertificateDao.findOne(id);
		BillDetailVo vo=AccountingFlushesVo.newBillDetailVo();
		vo.setTc(tc);
		return vo;
	}
	@Override
	public List<BillAccountVo> findBillAccountVoList(String billCd) {
		List<TallyCertificateDetail> tcdList=tallyCertificateDetailDao.findListByBillCd(billCd);
		List<BillAccountVo> voList=Lists.transform(tcdList, new Function<TallyCertificateDetail, BillAccountVo>() {
			@Override
			public BillAccountVo apply(TallyCertificateDetail tcd) {
				return AccountingFlushesVo.newBillAccountVo(tcd);
			}
		});
		return voList;
	}
	@Override
	@Transactional(readOnly=true)
	public BizBaseVo findBizInfo(Long id) {
		TallyCertificate tc=tallyCertificateDao.findOne(id);
		BizBaseVo baseVo=null;
		if(tc==null){
			return baseVo;
		}
		LoanBusinessType lbt=LoanBusinessType.valOf(tc.getBusiTypCd());
		switch (lbt) {
		case LOAN:
			baseVo=this.findBizPayLoan(tc.getBillCd());
			break;

		default:
			break;
		}
		if(baseVo!=null){
			baseVo.setBusinessType(lbt);
		}
		return baseVo;
	}
	/**
	 * 放款信息
	 * @return
	 */
	
	private BizPayLoanVo findBizPayLoan(String billCd){
		StringBuffer strBuffer=new StringBuffer();
		strBuffer.append("select pli.pay_loan_num,pli.customer_num,pli.customer_name,pli.product_type,")
		.append(" pli.loan_amt,pli.sum_amt,pli.apply_org_id,pli.apply_user_num,pli.remark,")
		.append("pli.bank_name,pli.loan_num,pli.loan_regist_time,pli.loan_actul_time,")
		.append(" cont.contract_num,cont.contract_amt,cont.currency,cont.contract_rate,")
		 .append(" cont.contract_term,cont.contract_term_unit,cont.repay_mode_cd,cont.repay_principal_monthes,")
		 .append(" cont.arrange_repay_day")
		 .append(" from pay_loan_info pli")
		 .append(" left join contract cont on pli.contract_id = cont.contract_id")
		.append(" where pli.transaction_no = ?1");
		List<Object[]> loanInfos=dynamicQuery.nativeQuery(strBuffer.toString(), billCd);
		if(CollectionUtils.isEmpty(loanInfos)){
			return null;
		}
		BizPayLoanVo vo=new BizPayLoanVo(loanInfos.get(0));
		EcOrgDepartment dept=ecOrgDepartmentDao.findOne(vo.getHandleOrgId());
		vo.setHandleOrgName(dept.getName());
		EcOrgPerson person=orgPersonDao.findOne(vo.getHandlePersonId());
		vo.setHandlePersonName(person.getName());
		if(StringUtils.isNoneBlank(vo.getContrRepaymentModeCd())){
			vo.setContrRepaymentModeName(dataDict.getCodeName("RepaymentMode", vo.getContrRepaymentModeCd()));
		}
		if(StringUtils.isNoneBlank(vo.getCurrency())){
			vo.setCurrencyStr(dataDict.getCodeName("CurrencyType", vo.getCurrency()));
		}
		if(StringUtils.isNoneBlank(vo.getProductTypeCd())){
			vo.setProductTypeName(this.findProductTypeName(vo.getProductTypeCd()));
		}
		if(StringUtils.isNoneBlank(vo.getContrTermUnit())){
			vo.setContrTermUnitStr(dataDict.getCodeName("TermUnitCd", vo.getContrTermUnit()));
		}
		return vo;
	}
	private String findProductTypeName(String productTypeCd){
		List<Product> list=dynamicQuery.query(Product.class, "from Product p where p.productCd=?1", Long.parseLong(productTypeCd));
		if(CollectionUtils.isEmpty(list)||list.get(0)==null){
			return null;
		}
		return list.get(0).getProductName();
	}

}
