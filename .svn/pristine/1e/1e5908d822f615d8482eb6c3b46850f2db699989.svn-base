package com.coamctech.bxloan.service.contractstandingbook.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coamctech.bxloan.commons.dynamicquery.DynamicQuery;
import com.coamctech.bxloan.commons.utils.DateTools;
import com.coamctech.bxloan.datadict.DataDict;
import com.coamctech.bxloan.service.contractstandingbook.ContractStandingBookService;
import com.coamctech.bxloan.service.model.contractstandingbook.CustomerInfoVO;

@Service
@Transactional
public class ContractStandingBookServiceImpl implements
		ContractStandingBookService {
	@Autowired
	private DataDict dataDict;
	@Autowired
	private DynamicQuery dynamicQuery;

	@Override
	public Page<Object[]> findListBySearch(CustomerInfoVO vo,
			Integer pageNumber, Integer pageSize) {
		StringBuffer sql = new StringBuffer(
				"select * from ( select   '1' as contract_type,pa.init_project_id,c.init_contract_id,c.contract_num,c.product_type, ");
		StringBuffer creditSql = new StringBuffer(
				" select   '2' as contract_type,pa.init_project_id,c.init_contract_id,c.contract_num,c.product_type, ");
		// sql.append("c.contract_status_cd ,c.contract_amt, c.contract_term,
		// c.contract_term_unit, eop.name, ");
		sql.append("c.contract_status_cd ,c.contract_amt,c.contract_term_total as contract_term, ");
		sql.append("c.contract_term_unit_total as contract_term_unit, eop.name,c.start_date,c.expiration_date, ");
		sql.append("c.contract_balance,case when c.contract_term_unit_total = '1' then c.contract_term_total * 360 ");
		sql.append("when c.contract_term_unit_total = '2' then c.contract_term_total * 30 else");
		sql.append(" c.contract_term_total * 1  end totaldays,c.arrange_repay_day ,pa.customer_name ,");
		sql.append("pa.guarantee_mode,pa.bp_info_id,pa.project_id,c.contract_id,c.party_id ,");
		sql.append("pa.business_type,c.payloan_date,pa.apply_org,(select ");
		sql.append(" ec.name from ec_org_department ec where ec.id=pa.apply_org) as apply_org_name,c.fpool_flag,");

		/* wangyawei add by 2014/3/11 start */
		sql.append("case when c.fpool_flag =1 then ")
				.append("(select app.asset_package_name from ass_tran_mid_project amp, ass_tran_project_application app ")
				.append(" where app.project_id = amp.project_id  and amp.mid_project_id =  c.txno  and app.project_id = c.fpool_project_id) ")
				.append(" else null end asset_package_name");
		/* wangyawei add by 2014/3/11 end */

		sql.append(" from contract c, project_application pa,ec_org_person eop,party p where ");
		sql.append(" pa.applicant = eop.id ");
		// sql.append(" and pa.business_process_type='2'"); //测试用的！得删除掉！
		sql.append(" and c.project_id = pa.project_id ");
		sql.append(" and pa.party_id = p.party_id ");
		// 查询合同状态为已签订之后的状态
		sql.append("and c.contract_status_cd in(");
		sql.append(dataDict.getCodeVal("ContractStatusCode", "S1")).append(",");
		sql.append(dataDict.getCodeVal("ContractStatusCode", "S2")).append(",");
		sql.append(dataDict.getCodeVal("ContractStatusCode", "S3")).append(",");
		sql.append(dataDict.getCodeVal("ContractStatusCode", "S4")).append(",");
		sql.append(dataDict.getCodeVal("ContractStatusCode", "S5")).append(",");
		sql.append(dataDict.getCodeVal("ContractStatusCode", "S6")).append(",");
		sql.append(dataDict.getCodeVal("ContractStatusCode", "S9")).append(",");
		sql.append(dataDict.getCodeVal("ContractStatusCode", "S10"));
		sql.append(")");

		creditSql
				.append("c.contract_status_cd ,c.contract_amt,c.contract_term_total as contract_term, ");
		creditSql
				.append("c.contract_term_unit_total as contract_term_unit, eop.name,c.start_date,null as expiration_date, ");
		creditSql
				.append("c.contract_balance,case when c.contract_term_unit_total = '1' then c.contract_term_total * 360 ");
		creditSql
				.append("when c.contract_term_unit_total = '2' then c.contract_term_total * 30 else");
		creditSql
				.append(" c.contract_term_total * 1  end totaldays,c.arrange_repay_day ,pa.customer_name ,");
		creditSql
				.append("pa.guarantee_mode,pa.bp_info_id,pa.project_id,c.loan_contract_id,c.party_id ,");
		creditSql
				.append("pa.business_type,c.payloan_date,pa.apply_org,(select ");
		creditSql
				.append(" ec.name from ec_org_department ec where ec.id=pa.apply_org) as apply_org_name,c.fpool_flag, ");

		/* wangyawei add by 2014/3/11 start */
		creditSql
				.append("case when c.fpool_flag =1 then ")
				.append("(select app.asset_package_name from ass_tran_mid_project amp, ass_tran_project_application app ")
				.append(" where app.project_id = amp.project_id  and amp.mid_project_id =  c.txno  and app.project_id = c.fpool_project_id) ")
				.append(" else null end asset_package_name");
		/* wangyawei add by 2014/3/11 end */

		creditSql
				.append(" from loan_contract c, credit_application pa,ec_org_person eop,party p where ");
		creditSql.append(" pa.applicant = eop.id ");
		creditSql.append(" and c.project_id = pa.project_id ");
		creditSql.append(" and pa.party_id = p.party_id ");
		// 查询合同状态为已签订之后的状态
		creditSql.append("and c.contract_status_cd in(");
		creditSql.append(dataDict.getCodeVal("ContractStatusCode", "S1"))
				.append(",");
		creditSql.append(dataDict.getCodeVal("ContractStatusCode", "S2"))
				.append(",");
		creditSql.append(dataDict.getCodeVal("ContractStatusCode", "S3"))
				.append(",");
		creditSql.append(dataDict.getCodeVal("ContractStatusCode", "S4"))
				.append(",");
		creditSql.append(dataDict.getCodeVal("ContractStatusCode", "S5"))
				.append(",");
		creditSql.append(dataDict.getCodeVal("ContractStatusCode", "S6"))
				.append(",");
		creditSql.append(dataDict.getCodeVal("ContractStatusCode", "S9"))
				.append(",");
		creditSql.append(dataDict.getCodeVal("ContractStatusCode", "S10"));
		creditSql.append(")");
		// String role=vo.getRole();
		// if(StringUtils.isNotBlank(role)&&!"null".equals(role)){ //客户经理
		// //20120727 modify by chenxh for 用户数据权限 start
		// //
		// if(dataDict.getCodeVal("RoleCode",RoleKey.CUSTOMER_MANAGE_ROLE).equals(role)
		// //客户经理
		// // ){
		// // sql.append(" and exists (select 1 from customer_manager_team cmt
		// where cmt.user_num='").append(vo.getApplyUserNum()).append("' and
		// cmt.party_id=pa.party_id)");
		if (StringUtils.isNotBlank(vo.getCreditLevel())
				&& !"null".equals(vo.getCreditLevel())) {
			if (dataDict.getCodeVal("OrgLevelCd", "S3").equals(
					vo.getCreditLevel())) {
				// 二级用户
				sql.append(" AND EXISTS (SELECT 1 FROM WF_PENDING W WHERE C.PROJECT_ID = W.BUSINESSOBJECTID AND (W.ORGID IN");
				sql.append(
						" (SELECT d.id FROM EC_ORG_DEPARTMENT D START WITH D.ID IN (SELECT L.ORGID FROM PERSON_PRIVILEGE_LEVEL L WHERE L.PERSONID = '")
						.append(vo.getApplyUserNum());
				sql.append("' AND L.PRIVILEGE_LEVEL = '")
						.append(dataDict.getCodeVal("privilegeLevelCd", "S2"))
						.append("' AND L.STATE = '1') CONNECT BY PRIOR D.ID = D.PARENTDEPARTMENTID) or");
				sql.append(" w.ownerid in (select p.logname from ec_org_person p where exists ");
				sql.append("(select 1 from ec_org_personconnrole r where r.personid = p.id and exists ");
				sql.append(
						"(select 1 from person_privilege_level l2 where l2.orgid = r.deptid and l2.personid = '")
						.append(vo.getApplyUserNum());
				sql.append("' and l2.privilege_level = '")
						.append(dataDict.getCodeVal("privilegeLevelCd", "S1"))
						.append("' and l2.state = '1'))) or");
				sql.append(" w.ownerid = '").append(vo.getLogName())
						.append("'))");

				creditSql
						.append(" AND EXISTS (SELECT 1 FROM WF_PENDING W WHERE C.PROJECT_ID = W.BUSINESSOBJECTID AND (W.ORGID IN");
				creditSql
						.append(" (SELECT d.id FROM EC_ORG_DEPARTMENT D START WITH D.ID IN (SELECT L.ORGID FROM PERSON_PRIVILEGE_LEVEL L WHERE L.PERSONID = '")
						.append(vo.getApplyUserNum());
				creditSql
						.append("' AND L.PRIVILEGE_LEVEL = '")
						.append(dataDict.getCodeVal("privilegeLevelCd", "S2"))
						.append("' AND L.STATE = '1') CONNECT BY PRIOR D.ID = D.PARENTDEPARTMENTID) or");
				creditSql
						.append(" w.ownerid in (select p.logname from ec_org_person p where exists ");
				creditSql
						.append("(select 1 from ec_org_personconnrole r where r.personid = p.id and exists ");
				creditSql
						.append("(select 1 from person_privilege_level l2 where l2.orgid = r.deptid and l2.personid = '")
						.append(vo.getApplyUserNum());
				creditSql.append("' and l2.privilege_level = '")
						.append(dataDict.getCodeVal("privilegeLevelCd", "S1"))
						.append("' and l2.state = '1'))) or");
				creditSql.append(" w.ownerid = '").append(vo.getLogName())
						.append("'))");
			}
		}

		// }else
		// if(dataDict.getCodeVal("RoleCode",RoleKey.CRIDITE_MANAGE_ROLE).equals(role)||
		// //风险管理人员
		// dataDict.getCodeVal("RoleCode",RoleKey.CREDIT_PERSON).equals(role)||
		// //信贷综合人员
		// dataDict.getCodeVal("RoleCode",RoleKey.FINANCIAL_PERSON).equals(role)||
		// //财务综合人员
		// dataDict.getCodeVal("RoleCode",RoleKey.PRIN_MANAGE_ROLE).equals(role)||
		// //总经理
		// dataDict.getCodeVal("RoleCode",RoleKey.BASIC_COMMISSIONER).equals(role)||
		// //初级审贷会
		// dataDict.getCodeVal("RoleCode",RoleKey.HIGH_COMMISSIONER).equals(role)||
		// //高级审贷会
		// dataDict.getCodeVal("RoleCode",RoleKey.CHARGE_MEETING_PERSON).equals(role)||
		// //经管会委员
		// dataDict.getCodeVal("RoleCode",RoleKey.HEAD_SECRTE_ROLE).equals(role)
		// //牵头审批人
		// ){
		// sql.append(" and exists (select 1 from customer_manager_team cmt
		// where cmt.org_cd='").append(vo.getOrgId()).append("' and
		// cmt.manager_type='").append(dataDict.getCodeVal("UserPlacing",
		// "S1")).append("' and cmt.party_id=pa.party_id )");
		// }
		// 20120727 modify by chenxh for 用户数据权限 end
		// }
		// 添加搜索条件
		if (null != vo.getPartyId()) {
			sql.append(" and c.party_id='").append(vo.getPartyId()).append("'");
			creditSql.append(" and c.party_id='").append(vo.getPartyId())
					.append("'");
		}

		// 客户名称
		if (StringUtils.isNotBlank(vo.getCustomerName())
				&& !"null".equals(vo.getCustomerName())) {
			sql.append(" and pa.customer_name like '%")
					.append(vo.getCustomerName()).append("%'");
			creditSql.append(" and pa.customer_name like '%")
					.append(vo.getCustomerName()).append("%'");
		}
		// 客户编码
		if (StringUtils.isNotBlank(vo.getCustomerNum())
				&& !"null".equals(vo.getCustomerNum())) {
			sql.append(" and pa.customer_num like '%")
					.append(vo.getCustomerNum()).append("%'");
			creditSql.append(" and pa.customer_num like '%")
					.append(vo.getCustomerNum()).append("%'");
		}
		// 合同状态
		if (StringUtils.isNotBlank(vo.getContractStatusCd())
				&& !"null".equals(vo.getContractStatusCd())) {
			sql.append(" and c.contract_status_cd='")
					.append(vo.getContractStatusCd()).append("'");
			creditSql.append(" and c.contract_status_cd='")
					.append(vo.getContractStatusCd()).append("'");
		}
		// 合同编号
		if (StringUtils.isNotBlank(vo.getContractNum())
				&& !"null".equals(vo.getContractNum())) {
			sql.append(" and c.contract_num like '%")
					.append(vo.getContractNum()).append("%'");
			creditSql.append(" and c.contract_num like '%")
					.append(vo.getContractNum()).append("%'");
		}
		// 授信合同编号
		if (StringUtils.isNotBlank(vo.getCredit_contract_num())
				&& !"null".equals(vo.getCredit_contract_num())) {
			sql.append(" and c.credit_contract_id in( select cc.credit_contract_id from credit_contract cc where cc.contract_num like '%");
			sql.append(vo.getCredit_contract_num()).append("%')");
			creditSql
					.append(" and c.contract_id in( select cc.credit_contract_id from credit_contract cc where cc.contract_num like '%");
			creditSql.append(vo.getCredit_contract_num()).append("%')");
		}
		// 担保方式GUARANTEE_MODE
		if (StringUtils.isNotBlank(vo.getGuaranteeMode())
				&& !"null".equals(vo.getCustomerName())) {
			sql.append(" and pa.guarantee_mode like '%")
					.append(vo.getGuaranteeMode()).append("%'");
			creditSql.append(" and pa.guarantee_mode like '%")
					.append(vo.getGuaranteeMode()).append("%'");
		}
		// 是否有保险
		if (StringUtils.isNotBlank(vo.getIfInsure())
				&& !"null".equals(vo.getIfInsure())) {
			sql.append(" and pa.if_insure='").append(vo.getIfInsure())
					.append("'");
		}
		// 是否总部协同业务
		if (StringUtils.isNotBlank(vo.getIsHeadCol())
				&& !"null".equals(vo.getIsHeadCol())) {
			sql.append(" and pa.isheadcol='").append(vo.getIsHeadCol())
					.append("'");
		}

		// 约定还款日
		// if (vo.getPayloanDate() != null){
		// sql.append(" and c.payloan_date =
		// to_date('").append(DateTools.dateToString(vo.getPayloanDate(),
		// "yyyy-MM-dd")).append("','yyyy-MM-dd')");
		// }
		if (vo.getPayloanDateFrom() != null) {
			sql.append(" and c.payloan_date >= to_date('")
					.append(DateTools.dateToString(vo.getPayloanDateFrom(),
							"yyyy-MM-dd")).append("','yyyy-MM-dd')");
			creditSql
					.append(" and c.payloan_date >= to_date('")
					.append(DateTools.dateToString(vo.getPayloanDateFrom(),
							"yyyy-MM-dd")).append("','yyyy-MM-dd')");
		}
		if (vo.getPayloanDateTo() != null) {
			sql.append(" and c.payloan_date <= to_date('")
					.append(DateTools.dateToString(vo.getPayloanDateTo(),
							"yyyy-MM-dd")).append("','yyyy-MM-dd')");
			creditSql
					.append(" and c.payloan_date <= to_date('")
					.append(DateTools.dateToString(vo.getPayloanDateTo(),
							"yyyy-MM-dd")).append("','yyyy-MM-dd')");
		}
		// 所属机构
		if (StringUtils.isNotBlank(vo.getAgencyOrgId())
				&& !"null".equals(vo.getAgencyOrgId())) {
			if (!"10001".equals(vo.getAgencyOrgId())) {
				sql.append(" and pa.apply_org = '").append(vo.getAgencyOrgId())
						.append("'");
				creditSql.append(" and pa.apply_org = '")
						.append(vo.getAgencyOrgId()).append("'");
			}

		}
		// 客户经理
		if (StringUtils.isNotBlank(vo.getApplyCustomerName())
				&& !"null".equals(vo.getApplyCustomerName())) {
			sql.append(" and pa.customer_manager_name like '%")
					.append(vo.getApplyCustomerName()).append("%'");
			creditSql.append(" and pa.customer_manager_name like '%")
					.append(vo.getApplyCustomerName()).append("%'");
		}
		// 资产状态 1,2
		if (StringUtils.isNotBlank(vo.getAssetsStatusCode())
				&& !"null".equals(vo.getAssetsStatusCode())) {
			if ("1".equals(vo.getAssetsStatusCode())) {
				sql.append(" and c.fpool_flag like '%")
						.append(vo.getAssetsStatusCode()).append("%'");
				creditSql.append(" and c.fpool_flag like '%")
						.append(vo.getAssetsStatusCode()).append("%'");
			} else {
				sql.append(" and (c.fpool_flag like '%")
						.append(vo.getAssetsStatusCode()).append("%'");
				sql.append("or c.fpool_flag is null)");
				creditSql.append(" and (c.fpool_flag like '%")
						.append(vo.getAssetsStatusCode()).append("%'");
				creditSql.append("or c.fpool_flag is null)");
			}
		}
		// 客户类型
		if (StringUtils.isNotBlank(vo.getCustomerType())
				&& !"null".equals(vo.getCustomerType())) {
			sql.append(" and p.party_type_cd = '").append(vo.getCustomerType())
					.append("'");
			creditSql.append(" and p.party_type_cd = '")
					.append(vo.getCustomerType()).append("'");
			// 选择客户标识时必须选中客户类型
			if (StringUtils.isNotBlank(vo.getTypeCd())
					&& !"null".equals(vo.getTypeCd())) {
				if (dataDict.getCodeVal("CustomerType", "S1").equals(
						vo.getCustomerType())) {
					sql.append(" and exists (select 1 from Corporation_Customer cc where cc.party_id=pa.party_id and cc.customer_scale = '"
							+ vo.getTypeCd() + "') ");
					creditSql
							.append(" and exists (select 1 from Corporation_Customer cc where cc.party_id=pa.party_id and cc.customer_scale = '"
									+ vo.getTypeCd() + "') ");
				} else if (dataDict.getCodeVal("CustomerType", "S2").equals(
						vo.getCustomerType())) {
					sql.append(" and exists (select 1 from individual ind where ind.party_id=pa.party_id and ind.ind_customer_type_cd = '"
							+ vo.getTypeCd() + "')");
					creditSql
							.append(" and exists (select 1 from individual ind where ind.party_id=pa.party_id and ind.ind_customer_type_cd = '"
									+ vo.getTypeCd() + "')");
				}
			}
		}
		sql.append(" union ").append(creditSql).append(")");

		if (StringUtils.isNotBlank(vo.getTermUnitCd())
				&& !"null".equals(vo.getTermUnitCd())) {
			if ("1".equals(vo.getTermUnitCd())) {
				sql.append(" where totaldays<=180");
				// creditSql.append(" where totaldays<=180");
			}
			if ("2".equals(vo.getTermUnitCd())) {
				sql.append(" where totaldays>180 and totaldays<=360");
				// creditSql.append(" where totaldays>180 and totaldays<=360");
			}
			if ("3".equals(vo.getTermUnitCd())) {
				sql.append(" where totaldays>360 and totaldays<=1080");
				// creditSql.append(" where totaldays>360 and totaldays<=1080");
			}
			if ("4".equals(vo.getTermUnitCd())) {
				sql.append(" where totaldays>1080 and totaldays<=1800");
				// creditSql.append(" where totaldays>1080 and totaldays<=1800");
			}
			if ("5".equals(vo.getTermUnitCd())) {
				sql.append(" where totaldays>1800 ");
				// creditSql.append(" where totaldays>1800 ");
			}
		}
		System.out.println(sql);
		return dynamicQuery.nativeQuery(new PageRequest(pageNumber - 1,
				pageSize), sql.toString());
	}
}