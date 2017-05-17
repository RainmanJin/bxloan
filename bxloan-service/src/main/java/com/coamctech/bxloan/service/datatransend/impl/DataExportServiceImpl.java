package com.coamctech.bxloan.service.datatransend.impl;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coamctech.bxloan.commons.dynamicquery.DynamicQuery;
import com.coamctech.bxloan.commons.utils.CommonHelper;
import com.coamctech.bxloan.commons.utils.FileUtils;
import com.coamctech.bxloan.commons.utils.PropertiesUtils;
import com.coamctech.bxloan.commons.utils.ReportExcelUtils;
import com.coamctech.bxloan.dao.DataTranRecordDao;
import com.coamctech.bxloan.entity.DataTranRecord;
import com.coamctech.bxloan.entity.KmExcelIn;
import com.coamctech.bxloan.service.datatransend.DataExportService;
import com.coamctech.bxloan.service.model.datatransend.ContractInfoExportVo;
import com.coamctech.bxloan.service.model.datatransend.CustomerInfoExportVo;
import com.coamctech.bxloan.service.model.datatransend.GuarantorInfoExportVo;
import com.coamctech.bxloan.service.model.datatransend.GuarantyInfoExportVo;
import com.coamctech.bxloan.service.model.datatransend.RepayInfoExportVo;
import com.google.common.base.Function;
import com.google.common.collect.Lists;

@Transactional
@Service
public class DataExportServiceImpl implements DataExportService {

	@Autowired
	private DynamicQuery dynamicQuery;
	
	@Autowired
	private DataTranRecordDao dataTranRecordDao;
	
	private final static String ORG_ID = "61540";
	
	@Override
	public List<CustomerInfoExportVo> findCustomerInfo(String startDate, String endDate) {
		StringBuffer sql = new StringBuffer();
		/**普通业务个人客户信息*/
		sql.append(" select '2' 是否关联人 , '0' 客户类别, decode(p.certificate_type_cd,'100','1010','456','1020','112','1030','1040')  证件类型,  p.certificate_num 证件号码, ");
		sql.append(" p.party_name 客户名称, i.mobile_tel 联系人电话,  '' 联系人名称,  '' 法人名称公司, i.permanent_address 户籍注册地址, i.work_company 工作地址营业地址, ");
		sql.append(" i.family_address 通讯地址,i.birthday 出生时间成立时间 from  party p,individual i where p.party_id=i.party_id ");
		sql.append(" and p.party_type_cd = '2' and instr(i.mark_type,'01') >0  and i.status = '2' and i.create_date >= to_date('").append(startDate).append("','yyyy-MM-dd') ");
		sql.append(" and i.create_date <= to_date('").append(endDate).append("','yyyy-MM-dd') ");
		sql.append(" and exists (select 1 from customer_manager_team cmt where cmt.party_id = p.party_id and cmt.manager_type = '01' and cmt.org_cd = '").append(ORG_ID).append("') union all ");
		/**普通业务企业客户信息*/
		sql.append(" select  '2' 是否关联人,'1' 客户类别, '1020' 证件类型, p.certificate_num 证件号码,p.party_name 客户名称,cc.linkman_tel 联系人电话,cc.linkman_name 联系人名称, ");
		sql.append(" (select distinct hm.hm_name from customer_correlative_relations ccr, high_manager hm where ccr.party_id = p.party_id and  ccr.correlative_relations_id=hm.correlative_relations_id ");
		sql.append(" and hm.representative_flag='1') 法人名称公司, (select max((select na.nation_area_name from nation_area na where na.nation_area_cd = a.province_cd) || ");
		sql.append(" (select na.nation_area_name from nation_area na where na.nation_area_cd = a.city_cd) || (select na.nation_area_name from nation_area na where na.nation_area_cd = a.county_cd) || ");
		sql.append(" a.street_address) from address a where a.party_id = cc.party_id and a.address_type_cd = '2' group by a.party_id) 户籍注册地址, (select max((select na.nation_area_name ");
		sql.append(" from nation_area na where na.nation_area_cd = a.province_cd) || (select na.nation_area_name from nation_area na where na.nation_area_cd = a.city_cd) || ");
		sql.append(" (select na.nation_area_name from nation_area na  where na.nation_area_cd = a.county_cd) || a.street_address) from address a where a.party_id = cc.party_id ");
		sql.append(" and a.address_type_cd = '1'  group by a.party_id) 工作地址营业地址,  (select max((select na.nation_area_name from nation_area na  where na.nation_area_cd = a.province_cd) || ");
		sql.append(" (select na.nation_area_name from nation_area na where na.nation_area_cd = a.city_cd) || (select na.nation_area_name from nation_area na where na.nation_area_cd = a.county_cd) || ");
		sql.append(" a.street_address)  from address a where a.party_id = cc.party_id and a.address_type_cd = '1' group by a.party_id)  通讯地址,cc.standed_date  出生时间成立时间 ");
		sql.append(" from party p,corporation_customer cc where  p.party_id=cc.party_id and p.party_type_cd = '1' ");
		sql.append(" and instr(cc.mark_type,'01') >0 and cc.states ='2' and cc.create_date >= to_date('").append(startDate).append("','yyyy-MM-dd') and cc.create_date <= to_date('").append(endDate).append("','yyyy-MM-dd') ");
		sql.append(" and exists (select 1 from customer_manager_team cmt where cmt.party_id = p.party_id and cmt.manager_type = '01' and cmt.org_cd = '").append(ORG_ID).append("') ");
		List<Object[]> list = dynamicQuery.nativeQuery(sql.toString());
		if(list.isEmpty() || list.size() == 0) {
			return null;
		}
		List<CustomerInfoExportVo> customerDataList =
				Lists.transform(list, new Function<Object[], CustomerInfoExportVo>() {
					@Override
					public CustomerInfoExportVo apply(Object[] objs) {
						return new CustomerInfoExportVo(objs);
					}
				});
		return customerDataList;
	}
	
	@Override
	public List<ContractInfoExportVo> findContractInfo(String startDate, String endDate) {
		StringBuffer sql = new StringBuffer();
		/**普通合同信息*/
		sql.append(" select p.party_name 客户名称,p.certificate_num 客户证件号,c.contract_num 合同编号,c.contract_amt 合同金额元,round(c.contract_rate * 1000 / 10) 贷款利率,to_char(c.payloan_date, 'yyyy-MM-dd') 合同开始日, ");
		sql.append(" to_char(case when c.contract_term_unit_total = '1' then add_months(c.payloan_date, c.contract_term_total * 12) when c.contract_term_unit_total = '2' then add_months(c.payloan_date, c.contract_term_total) ");
		sql.append(" else c.payloan_date + c.contract_term_total end, 'yyyy-MM-dd') 合同结束日, to_char(c.payloan_date, 'yyyy-MM-dd') 贷款实际发放日, decode(c.contract_status_cd,'316','1','330','2','422','3','421','4') 贷款状态, ");
		sql.append(" decode(c.fiveclassification, '1', '1','2', '3','3','2','4', '4','5','5') 五级分类,decode(p.party_type_cd,1,decode((select cc.customer_scale from corporation_customer cc where cc.party_id=p.party_id),1,1,2),'') 是否微型企业, ");
		sql.append(" nvl((select  decode(subStr(tit.credit_industry_type_cd, '0', '1'),'A','1','B','2','C','2', 'D','2','E','3','F','6', 'G','7','H','8','I','8','J', '7','K','4','L','8','7') from temp_industry_type tit where tit.industry_type_cd =  pa.investment_industry),'7') 贷款投向, ");
		sql.append(" '1' 借款用途,decode(p.party_type_cd,1,'2','') 是否面向创业企业,decode(p.party_type_cd,1,'2','') 是否面向科技企业,decode(p.party_type_cd,1,'2','') 是否面向文化创意企业,decode(p.party_type_cd,2,'2','') 是否妇女贷款, ");
		sql.append(" decode(instr(pa.guarantee_mode,'4'),0,'2','1')  信用,decode(instr(pa.guarantee_mode,'3'),0,'2','1')  保证,decode(instr(pa.guarantee_mode,'1'),0,'2','1')  抵押,decode(instr(pa.guarantee_mode,'2'),0,'2','1')  质押  ");
		sql.append(" from contract c, project_application pa, party p where pa.party_id = p.party_id and pa.project_id = c.project_id and c.contract_num in (select distinct tc.cont_cd from tally_certificate tc, contract c ");
		sql.append(" where tc.cont_cd = c.contract_num and c.CONTRACT_STATUS_CD in (316, 330, 422) and to_date(to_char(tc.busi_dt, 'yyyy-MM-dd'), 'yyyy-MM-dd') >= to_date('").append(startDate).append("','yyyy-MM-dd') ");
		sql.append(" and to_date(to_char(tc.busi_dt, 'yyyy-MM-dd'), 'yyyy-MM-dd') <= to_date('").append(endDate).append("','yyyy-MM-dd') and tc.bill_sts = '047003' and tc.busi_typ_cd = '001' and c.apply_org_id = '").append(ORG_ID).append("') union all ");
		/**授信合同信息*/
		sql.append(" select p.party_name 客户名称,p.certificate_num 客户证件号, lc.contract_num 合同编号, lc.contract_amt 合同金额元,round(lc.contract_rate * 1000 / 10) 贷款利率,to_char(lc.payloan_date, 'yyyy-MM-dd') 合同开始日, ");
		sql.append(" to_char(case when lc.contract_term_unit_total = '1' then  add_months(lc.payloan_date, lc.contract_term_total * 12) when lc.contract_term_unit_total = '2' then add_months(lc.payloan_date, lc.contract_term_total) ");
		sql.append(" else lc.payloan_date + lc.contract_term_total end,'yyyy-MM-dd') 合同结束日,to_char(lc.payloan_date, 'yyyy-MM-dd') 贷款实际发放日,decode(lc.contract_status_cd,'316', '1', '330', '2', '422', '3','421', '4') 贷款状态, ");
		sql.append(" decode(lc.fiveclassification, '1','1','2','3', '3', '2', '4','4', '5','5') 五级分类,decode(p.party_type_cd,1,decode((select cc.customer_scale from corporation_customer cc where cc.party_id=p.party_id),1,1,2),'') 是否微型企业, ");
		sql.append(" nvl((select  decode(subStr(tit.credit_industry_type_cd, '0', '1'),'A','1','B','2','C','2', 'D','2','E','3','F','6', 'G','7','H','8','I','8','J', '7','K','4','L','8','7') from temp_industry_type tit where  tit.industry_type_cd =  ca.investment_industry),'7') 贷款投向, ");
		sql.append(" '1' 借款用途,decode(p.party_type_cd,1,'2','') 是否面向创业企业,decode(p.party_type_cd,1,'2','') 是否面向科技企业,decode(p.party_type_cd,1,'2','') 是否面向文化创意企业,decode(p.party_type_cd,2,'2','') 是否妇女贷款, ");
		sql.append(" decode(instr(ca.guarantee_mode,'4'),0,'2','1')  信用,decode(instr(ca.guarantee_mode,'3'),0,'2','1')  保证,decode(instr(ca.guarantee_mode,'1'),0,'2','1')  抵押,decode(instr(ca.guarantee_mode,'2'),0,'2','1')  质押  ");
		sql.append(" from party  p,credit_application ca,credit_contract  cc,loan_contract lc where ca.party_id = p.party_id and ca.project_id = cc.project_id and cc.credit_contract_id = lc.contract_id and lc.contract_num in ( ");
		sql.append(" select distinct tc.cont_cd from tally_certificate tc, loan_contract c where tc.cont_cd = c.contract_num and c.CONTRACT_STATUS_CD in (316, 330, 422) and to_date(to_char(tc.busi_dt, 'yyyy-MM-dd'), 'yyyy-MM-dd') >= ");
		sql.append(" to_date('").append(startDate).append("','yyyy-MM-dd')  and to_date(to_char(tc.busi_dt, 'yyyy-MM-dd'), 'yyyy-MM-dd') <= to_date('").append(endDate).append("','yyyy-MM-dd') and tc.bill_sts = '047003' and tc.busi_typ_cd = '001' and c.apply_org_id = '").append(ORG_ID).append("') ");
		List<Object[]> list = dynamicQuery.nativeQuery(sql.toString());
		if(list.isEmpty() || list.size() == 0) {
			return null;
		}
		List<ContractInfoExportVo> contractDataList =
				Lists.transform(list, new Function<Object[], ContractInfoExportVo>() {
					@Override
					public ContractInfoExportVo apply(Object[] objs) {
						return new ContractInfoExportVo(objs);
					} 
				});
		return contractDataList;   
	}
	
	@Override
	public List<RepayInfoExportVo> findRepayInfo(String startDate, String endDate) {
		StringBuffer sql = new StringBuffer();
		/**普通业务还款信息*/
		sql.append(" select c.contract_num 合同编号,rl.actual_principal_amt 还款金额, rl.repay_date 还款日期 from contract c,pay_loan_info pi,repay_loan rl where rl.contract_num = c.contract_num and pi.contract_id = c.init_contract_id ");
		sql.append(" and pi.pay_status_cd in ('2', '4') and rl.repayment_status_cd in ('2', '4') and rl.valid = '1' and nvl(rl.actual_principal_amt,0) != 0 and rl.repay_date >= to_date('").append(startDate).append("', 'yyyy-MM-dd') ");
		sql.append(" and rl.repay_date <= to_date('").append(endDate).append("', 'yyyy-MM-dd') and pi.many_pay_status = 0 and c.contract_num in (select distinct rll.contract_num from repay_loan rll, contract c where rll.repay_date >=to_date('").append(startDate).append("','yyyy-MM-dd') ");
		sql.append(" and rll.repay_date <=to_date('").append(endDate).append("','yyyy-MM-dd') and rll.contract_num = c.contract_num and c.CONTRACT_STATUS_CD in (316, 330, 422) and rll.repayment_status_cd = '2' and rll.valid = '1' and c.apply_org_id = '").append(ORG_ID).append("' ");
		sql.append(" and rll.contract_num not in (select ke.contract_num from km_excel_in ke where ke.org_id = '").append(ORG_ID).append("' and ke.repay_date <= to_date('").append(endDate).append("','yyyy-MM-dd'))) union all ");
		/**授信业务还款信息*/
		sql.append(" select cc.contract_num 合同编号,rl.actual_principal_amt 还款金额, rl.repay_date 还款日期 from credit_contract cc,loan_contract lc,credit_pay_loan_info pi,credit_repay_loan rl where cc.credit_contract_id = lc.contract_id  ");
		sql.append(" and rl.contract_num = lc.contract_num and pi.contract_id = lc.init_contract_id and pi.pay_status_cd in ('2', '4') and rl.repayment_status_cd in ('2', '4') and rl.valid = '1' and nvl(rl.actual_principal_amt,0) != 0 ");
		sql.append(" and rl.repay_date >= to_date('").append(startDate).append("', 'yyyy-MM-dd') and rl.repay_date <= to_date('").append(endDate).append("', 'yyyy-MM-dd') and pi.many_pay_status = 0 and lc.contract_num in (select distinct rll.contract_num from repay_loan rll, loan_contract c ");
		sql.append(" where rll.repay_date >=to_date('").append(startDate).append("','yyyy-MM-dd') and rll.repay_date <=to_date('").append(endDate).append("','yyyy-MM-dd') and rll.contract_num = c.contract_num and c.CONTRACT_STATUS_CD in (316, 330, 422) and rll.repayment_status_cd = '2' ");
		sql.append(" and rll.valid = '1' and c.apply_org_id = '").append(ORG_ID).append("' and rll.contract_num not in (select ke.contract_num from km_excel_in ke where ke.org_id = '").append(ORG_ID).append("' and ke.repay_date <= to_date('").append(endDate).append("','yyyy-MM-dd'))) union all ");
		/**普通转出合同转出时间之前，在查询时间区间内的还款信息*/
		sql.append(" select c.contract_num 合同编号,rl.actual_principal_amt 还款金额,rl.repay_date 还款日期 from   contract c, pay_loan_info pi,repay_loan rl,km_excel_in ke where rl.contract_num = c.contract_num and ke.contract_num = c.contract_num ");
		sql.append(" and pi.contract_id = c.init_contract_id and pi.pay_status_cd in ('2', '4') and rl.repayment_status_cd in ('2', '4') and rl.valid = '1' and nvl(rl.actual_principal_amt,0) != 0 and rl.repay_date >= to_date('").append(startDate).append("', 'yyyy-MM-dd') ");
		sql.append(" and rl.repay_date <= ke.repay_date and pi.many_pay_status = 0 and  ke.org_id = '").append(ORG_ID).append("' and to_date(to_char(ke.repay_date, 'yyyy-MM-dd'), 'yyyy-MM-dd') >= to_date('").append(startDate).append("','yyyy-MM-dd')  ");
		sql.append(" and to_date(to_char(ke.repay_date, 'yyyy-MM-dd'), 'yyyy-MM-dd') <= to_date('").append(endDate).append("','yyyy-MM-dd') union all ");
		/**授信转出合同转出时间之前，在查询时间区间内的还款信息 */
		sql.append(" select cc.contract_num 合同编号, rl.actual_principal_amt 还款金额,rl.repay_date 还款日期 from credit_contract cc, loan_contract lc, credit_pay_loan_info pi,credit_repay_loan rl,km_excel_in ke where cc.credit_contract_id = lc.contract_id ");
		sql.append(" and ke.contract_num =lc.contract_num and rl.contract_num = lc.contract_num and pi.contract_id = lc.init_contract_id and pi.pay_status_cd in ('2', '4') and rl.repayment_status_cd in ('2', '4') and rl.valid = '1' and nvl(rl.actual_principal_amt,0) != 0 ");
		sql.append(" and rl.repay_date >= to_date('").append(startDate).append("', 'yyyy-MM-dd')  and rl.repay_date <= ke.repay_date and pi.many_pay_status = 0 and  ke.org_id = '").append(ORG_ID).append("' and to_date(to_char(ke.repay_date, 'yyyy-MM-dd'), 'yyyy-MM-dd') >= to_date('").append(startDate).append("','yyyy-MM-dd') ");
		sql.append(" and to_date(to_char(ke.repay_date, 'yyyy-MM-dd'), 'yyyy-MM-dd') <= to_date('").append(endDate).append("','yyyy-MM-dd') union all ");
		/**为查询时间区间内的转出合同组装还款信息*/
		sql.append(" select ke.contract_num 合同编号,ke.repay_amt 还款金额,ke.repay_date 还款日期 from  km_excel_in ke where ke.org_id = '").append(ORG_ID).append("' and to_date(to_char(ke.repay_date, 'yyyy-MM-dd'), 'yyyy-MM-dd') >= to_date('").append(startDate).append("','yyyy-MM-dd') ");
		sql.append(" and to_date(to_char(ke.repay_date, 'yyyy-MM-dd'), 'yyyy-MM-dd') <= to_date('").append(endDate).append("','yyyy-MM-dd')");
		List<Object[]> list = dynamicQuery.nativeQuery(sql.toString());
		if(list.isEmpty() || list.size() == 0) {
			return null;
		}
		List<RepayInfoExportVo> repayDataList =
				Lists.transform(list, new Function<Object[], RepayInfoExportVo>() {
					@Override
					public RepayInfoExportVo apply(Object[] objs) {
						return new RepayInfoExportVo(objs);
					} 
				});
		return repayDataList;   
	}
	
	@Override
	public List<GuarantyInfoExportVo> findGuarantyInfo(String startDate, String endDate) {
		StringBuffer sql = new StringBuffer();
		/**普通业务担保物信息*/
		sql.append(" select distinct ppi.guarantor_name 担保人名称,ppi.guarantor_certificate_num  担保人证件号,c.contract_num 合同编号,'' 登记号,'' 登记部门，''  登记日期, '' 登记有效期,ppi.guaranty_name 担保物名称,'' 担保物地理位置, ");
		sql.append(" '' 担保物顺位 from contract c, project_application pa, party p,subcontract sc,project_pawn_info ppi where pa.party_id = p.party_id and pa.project_id = c.project_id and sc.contract_id = c.contract_id ");
		sql.append(" and ppi.project_pawn_info_id = sc.guaranty_id and sc.subcontract_type_cd = '1' and c.contract_num in (select distinct tc.cont_cd from tally_certificate tc, contract c where tc.cont_cd = c.contract_num ");
		sql.append(" and c.CONTRACT_STATUS_CD in (316, 330, 422) and to_date(to_char(tc.busi_dt, 'yyyy-MM-dd'), 'yyyy-MM-dd') >= to_date('").append(startDate).append("','yyyy-MM-dd') and to_date(to_char(tc.busi_dt, 'yyyy-MM-dd'), 'yyyy-MM-dd') <= ");
		sql.append(" to_date('").append(endDate).append("','yyyy-MM-dd') and tc.bill_sts = '047003' and tc.busi_typ_cd = '001' and c.apply_org_id = '").append(ORG_ID).append("') union all ");
		/**信业务担保物信息*/
		sql.append(" select distinct ppi.guarantor_name 担保人名称,ppi.guarantor_certificate_num  担保人证件号,lc.contract_num 合同编号, '' 登记号,'' 登记部门，'' 登记日期,'' 登记有效期, ppi.guaranty_name 担保物名称, '' 担保物地理位置,  ");
		sql.append(" '' 担保物顺位 from party p, credit_application ca, credit_contract cc,loan_contract lc,subcredit_contract sc,credit_pawn_info ppi where ca.party_id = p.party_id and ca.project_id = cc.project_id ");
		sql.append(" and cc.credit_contract_id = lc.contract_id and sc.contract_id = cc.credit_contract_id and ppi.credit_pawn_info_id = sc.guaranty_id and sc.subcontract_type_cd = '1' and lc.contract_num in (select distinct tc.cont_cd ");
		sql.append(" from tally_certificate tc, loan_contract c where tc.cont_cd = c.contract_num and c.CONTRACT_STATUS_CD in (316, 330, 422) and to_date(to_char(tc.busi_dt, 'yyyy-MM-dd'), 'yyyy-MM-dd') >=  to_date('").append(startDate).append("','yyyy-MM-dd') ");
		sql.append(" and to_date(to_char(tc.busi_dt, 'yyyy-MM-dd'), 'yyyy-MM-dd') <= to_date('").append(endDate).append("','yyyy-MM-dd') and tc.bill_sts = '047003' and tc.busi_typ_cd = '001' and c.apply_org_id = '").append(ORG_ID).append("') ");
		List<Object[]> list = dynamicQuery.nativeQuery(sql.toString());
		if(list.isEmpty() || list.size() == 0) {
			return null;
		}
		List<GuarantyInfoExportVo> guarantyDataList =
				Lists.transform(list, new Function<Object[], GuarantyInfoExportVo>() {
					@Override
					public GuarantyInfoExportVo apply(Object[] objs) {
						return new GuarantyInfoExportVo(objs);
					} 
				});
		return guarantyDataList;   
	}

	@Override
	public List<GuarantorInfoExportVo> findGuarantorInfo(String startDate, String endDate) {
		StringBuffer sql = new StringBuffer();
		/**普通业务保证担保方式信息*/
		sql.append(" select c.contract_num 贷款合同编号,'2' 担保方式,pai.customer_name 客户名称,decode(pai.certificate_type_cd,'210','1','0') 担保人类型，decode(pai.certificate_type_cd,'100','1010','456','1020','210','1020','112','1030','1040') 证件类型, ");
		sql.append(" pai.certificate_num 证件号码,'2'  保证方式,'100' 保证份额 from contract c, project_assurer_info pai, subcontract sc where sc.contract_id = c.contract_id and pai.project_assurer_info_id = sc.assurer_id and sc.subcontract_type_cd = '2'  ");
		sql.append(" and c.contract_num in (select distinct tc.cont_cd from tally_certificate tc, contract c where tc.cont_cd = c.contract_num and c.CONTRACT_STATUS_CD in (316, 330, 422) and to_date(to_char(tc.busi_dt, 'yyyy-MM-dd'), 'yyyy-MM-dd') >= ");
		sql.append(" to_date('").append(startDate).append("','yyyy-MM-dd') and to_date(to_char(tc.busi_dt, 'yyyy-MM-dd'), 'yyyy-MM-dd') <= to_date('").append(endDate).append("','yyyy-MM-dd') and tc.bill_sts = '047003' and tc.busi_typ_cd = '001' and c.apply_org_id = '").append(ORG_ID).append("') union all ");
		/**授信业务保证担保方式信息*/
		sql.append(" select lc.contract_num 贷款合同编号,'2' 担保方式,cai.customer_name 客户名称,decode(cai.certificate_type_cd,'210','1','0') 担保人类型，decode(cai.certificate_type_cd,'100','1010','456','1020','210','1020','112','1030','1040')  证件类型, ");
		sql.append(" cai.certificate_num 证件号码,'2'  保证方式,'100' 保证份额 from credit_contract cc,loan_contract lc,credit_assurer_info cai,subcredit_contract sc where cc.credit_contract_id = lc.contract_id and sc.contract_id = cc.credit_contract_id ");
		sql.append(" and cai.credit_assurer_info_id = sc.assurer_id and sc.subcontract_type_cd = '2' and lc.contract_num in (select distinct tc.cont_cd from tally_certificate tc, loan_contract c where tc.cont_cd = c.contract_num and c.CONTRACT_STATUS_CD in (316, 330, 422) ");
		sql.append(" and to_date(to_char(tc.busi_dt, 'yyyy-MM-dd'), 'yyyy-MM-dd') >= to_date('").append(startDate).append("','yyyy-MM-dd') and to_date(to_char(tc.busi_dt, 'yyyy-MM-dd'), 'yyyy-MM-dd') <= to_date('").append(endDate).append("','yyyy-MM-dd')	and tc.bill_sts = '047003' and tc.busi_typ_cd = '001' ");
		sql.append(" and c.apply_org_id = '").append(ORG_ID).append("') union all ");
		/**普通业务抵押、质押担保方式信息*/
		sql.append(" select distinct c.contract_num 贷款合同编号,decode(ppi.guarantee_type,'1','3','2','4') 担保方式,ppi.guarantor_name 客户名称,decode(ppi.guarantor_type_cd,'210','1','0') 担保人类型，decode(ppi.guarantor_type_cd,'100','1010','456','1020','210','1020','112','1030','1040')  证件类型, ");
		sql.append(" ppi.guarantor_certificate_num 证件号码,'2'  保证方式, '100' 保证份额 from contract c, project_application pa, party p,subcontract sc,project_pawn_info ppi where pa.party_id = p.party_id and pa.project_id = c.project_id and sc.contract_id = c.contract_id and ppi.project_pawn_info_id = sc.guaranty_id ");
		sql.append(" and sc.subcontract_type_cd = '1'and c.contract_num in (select distinct tc.cont_cd from tally_certificate tc, contract c where tc.cont_cd = c.contract_num and c.CONTRACT_STATUS_CD in (316, 330, 422) and to_date(to_char(tc.busi_dt, 'yyyy-MM-dd'), 'yyyy-MM-dd') >= to_date('").append(startDate).append("','yyyy-MM-dd') ");
		sql.append(" and to_date(to_char(tc.busi_dt, 'yyyy-MM-dd'), 'yyyy-MM-dd') <= to_date('").append(endDate).append("','yyyy-MM-dd') and tc.bill_sts = '047003' and tc.busi_typ_cd = '001' and c.apply_org_id = '").append(ORG_ID).append("') union all ");
		/**授信业务抵押、质押担保方式信息*/
		sql.append(" select  distinct lc.contract_num 贷款合同编号,decode(ppi.guarantee_type,'1','3','2','4') 担保方式,ppi.guarantor_name 客户名称,decode(ppi.guarantor_type_cd,'210','1','0') 担保人类型，decode(ppi.guarantor_type_cd,'100','1010','456','1020','210','1020','112','1030','1040')  证件类型, ");
		sql.append(" ppi.guarantor_certificate_num 证件号码,'2'  保证方式,'100' 保证份额 from party p,credit_application ca,credit_contract cc,loan_contract lc,subcredit_contract sc,credit_pawn_info ppi where ca.party_id = p.party_id and ca.project_id = cc.project_id and cc.credit_contract_id = lc.contract_id");
		sql.append(" and sc.contract_id = cc.credit_contract_id and ppi.credit_pawn_info_id = sc.guaranty_id and sc.subcontract_type_cd = '1' and lc.contract_num in (select distinct tc.cont_cd from tally_certificate tc, loan_contract c where tc.cont_cd = c.contract_num and c.CONTRACT_STATUS_CD in (316, 330, 422) ");
		sql.append(" and to_date(to_char(tc.busi_dt, 'yyyy-MM-dd'), 'yyyy-MM-dd') >= to_date('").append(startDate).append("','yyyy-MM-dd') and to_date(to_char(tc.busi_dt, 'yyyy-MM-dd'), 'yyyy-MM-dd') <=to_date('").append(endDate).append("','yyyy-MM-dd') and tc.bill_sts = '047003' and tc.busi_typ_cd = '001' and c.apply_org_id = '").append(ORG_ID).append("')");
		List<Object[]> list = dynamicQuery.nativeQuery(sql.toString());
		if(list.isEmpty() || list.size() == 0) {
			return null;
		}
		List<GuarantorInfoExportVo> guarantorDataList =
				Lists.transform(list, new Function<Object[], GuarantorInfoExportVo>() {
					@Override
					public GuarantorInfoExportVo apply(Object[] objs) {
						return new GuarantorInfoExportVo(objs);
					} 
				});
		return guarantorDataList;   
	}
	
	@Override
	public void exportExcel(String startDate, String endDate, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map map = new HashMap();
		/**导出客户资料*/
		List<CustomerInfoExportVo> customerDataExport = this.findCustomerInfo(startDate, endDate);
		/**导出合同信息*/
		List<ContractInfoExportVo> contractDataExport = this.findContractInfo(startDate, endDate);
		/**导出还款信息*/
		List<RepayInfoExportVo> repayDataExport = this.findRepayInfo(startDate, endDate);
		/**导出担保物信息*/
		List<GuarantyInfoExportVo> guarantyDataExport = this.findGuarantyInfo(startDate, endDate);
		/**导出担保人信息*/
		List<GuarantorInfoExportVo> guarantorDataExport = this.findGuarantorInfo(startDate, endDate);
		/**存放客户资料*/
		map.put("customerDataExport", customerDataExport);  
		/**存放合同信息*/
		map.put("contractDataExport", contractDataExport); 
		/**存放还款信息*/
		map.put("repayDataExport", repayDataExport);   
		/**存放担保物信息*/
		map.put("guarantyDataExport", guarantyDataExport);  
		/**存放担保人信息*/
		map.put("guarantorDataExport", guarantorDataExport);  
		/**导出excel工具类*/
		ReportExcelUtils reportExcelUtils= new ReportExcelUtils();
		/**加载模版并且生成导出文件落地*/
		String fileName = reportExcelUtils.genernateExcelFileName("datatrandownloadSH", "datatrandownloadSH", "数据导出", map);
		/**获取文件路径*/
		String dfzcReportPath = PropertiesUtils.getConfigFileName("datatrandownloadSH"); 
		File file = new File(dfzcReportPath + fileName);
		/**从服务器下载到本地*/
		FileUtils.downloadFile(file, fileName, request, response);
		/**下载后删除服务器文件*/
		file.delete();
	}
	
	@Override
	public Page<DataTranRecord> findDataTranRecord(Integer pageNumber, Integer pageSize) {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(" select dt.data_tran_record_id,dt.operator_name,dt.log_org_name,dt.start_date,dt.end_date,dt.operate_time from data_tran_record dt order by dt.operate_time desc ");
		Page<Object[]> page = dynamicQuery.nativeQuery(Object[].class,
				new PageRequest(pageNumber, pageSize), stringBuffer.toString());
		Page<DataTranRecord> resultPage = new PageImpl<DataTranRecord>(
				Lists.transform(page.getContent(),
						new Function<Object[], DataTranRecord>() {
							@Override
							public DataTranRecord apply(Object[] objs) {
								return new DataTranRecord(objs);
							}
						}), new PageRequest(pageNumber, pageSize),
				page.getTotalElements());
		/*stringBuffer.append(" from DataTranRecord order by operateTime desc ");
		Page<DataTranRecord> resultPage = dynamicQuery.query(
				DataTranRecord.class, new PageRequest(pageNumber, pageSize),
				stringBuffer.toString());*/
		return resultPage;
	}

	@Override
	public void saveRecord(DataTranRecord dataTranRecord) {
		/**操作时间*/
		dataTranRecord.setOperateTime(CommonHelper.getNow());
		dataTranRecordDao.save(dataTranRecord);
	}
}