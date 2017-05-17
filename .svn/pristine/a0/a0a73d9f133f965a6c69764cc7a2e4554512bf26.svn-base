package com.coamctech.bxloan.service.afterloan.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.coamctech.bxloan.commons.dynamicquery.DynamicQuery;
import com.coamctech.bxloan.commons.utils.CommonHelper;
import com.coamctech.bxloan.commons.utils.DateTools;
import com.coamctech.bxloan.commons.utils.MathUtil;
import com.coamctech.bxloan.dao.ContractDao;
import com.coamctech.bxloan.datadict.DataDict;
import com.coamctech.bxloan.entity.Contract;
import com.coamctech.bxloan.entity.Party;
import com.coamctech.bxloan.service.afterloan.NormalRepaymentService;
import com.coamctech.bxloan.service.common.SysCommonService;
import com.coamctech.bxloan.service.model.afterloan.LrRepayInfoVo;
import com.coamctech.bxloan.service.model.afterloan.NormalRepaymentDetailVo;
import com.coamctech.bxloan.service.model.afterloan.NormalRepaymentVo;
import com.coamctech.bxloan.service.sysmng.ICommonBizNumberBS;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
/**
 * 类名称：NormalRepaymentServiceImpl
 * 类描述 ：正常还款
 * 创建人: 衡万里
 * 创建时间：2015年5月12日 上午10:39:46  
 * 修改人：衡万里
 * 修改时间：2015年5月12日 上午10:39:46  
 * 修改备注：
 * 版本： V1.0
 */
@Service
public class NormalRepaymentServiceImpl implements NormalRepaymentService {
	
	@Autowired
	private DynamicQuery dynamicQuery;
	@Autowired
	private DataDict dataDict;
	@Autowired
	private ContractDao contractDao;
	@Autowired
	private ICommonBizNumberBS commonBizNumberBS;
	@Autowired
	private SysCommonService  sysCommonService;
	
	@Override
	public Page<NormalRepaymentVo> findPage(int pageSize, int pageNum,
			Long userId, Long orgId, NormalRepaymentVo vo) {
		StringBuffer sb=new StringBuffer();
		sb.append("select c.party_id, c.project_id, c.contract_id, rpd.repaying_plan_detail_id, rp.repaying_plan_id,");
		sb.append("rpd.current_period, rp.repayed_period, rp.total_period,");
		sb.append("c.contract_num, c.customer_num, c.customer_name, pdt.product_name, c.contract_amt, c.contract_balance,");
		sb.append("rpd.current_end_date, rpd.current_principal_interest, rd.repayed_impose_interest,");
		sb.append("CASE WHEN p.party_type_cd = '1' THEN (select cc.linkman_tel from corporation_customer cc where cc.party_id = p.party_id) WHEN p.party_type_cd = '2' THEN (select ind.mobile_tel from individual ind where ind.party_id = p.party_id) ELSE '' END AS contact_way");
		sb.append(" from repaying_plan_detail rpd ");
		sb.append(" inner join repaying_plan rp on rp.repaying_plan_id = rpd.repaying_plan_id ");
		sb.append(" and rpd.status = '0' and rp.transaction_status != '5'");
		sb.append(" inner join repaying_detail rd on rd.repaying_plan_detail_id = rpd.repaying_plan_detail_id");
		sb.append(" inner join pay_loan_info pli on pli.pay_loan_id = rp.pay_loan_id and pli.pay_status_cd in (2)");
		sb.append(" and pli.pay_status_cd = '2' and pli.many_pay_status = '0'");
		sb.append(" inner join contract c on c.contract_id = rp.contract_id ");
		sb.append(" inner join project_application pa on pa.project_id=c.project_id and pa.sys_cd in ('1','2') ");
		sb.append(" left join party p on p.party_id=c.party_id  ");
		sb.append(" inner join product pdt on pdt.product_cd = c.product_type");
		sb.append(" where  c.contract_status_cd = '316' and c.apply_user_num=?1 and c.apply_org_id=?2 ");
		int paramsIndex=3;//参数索引
		List<Object> params=Lists.newArrayList();
		params.add(userId);
		params.add(orgId);
		if(vo!=null){
			if(StringUtils.isNoneBlank(vo.getContractNum())){
				sb.append("  and c.contract_num like ?").append(paramsIndex++);
				params.add(StringUtils.join("%",vo.getContractNum(),"%"));
			}
			if(StringUtils.isNoneBlank(vo.getCustomerNum())){
				sb.append("  and c.customer_num like ?").append(paramsIndex++);
				params.add(StringUtils.join("%",vo.getCustomerNum(),"%"));
			}
			if(StringUtils.isNoneBlank(vo.getCustomerName())){
				sb.append("  and c.customer_name like ?").append(paramsIndex++);
				params.add(StringUtils.join("%",vo.getCustomerName(),"%"));
			}
			//贷款产品
			if(StringUtils.isNoneBlank(vo.getProductCd())){
				sb.append("  and c.product_type = ?").append(paramsIndex++);
				params.add(vo.getProductCd());
			}
			if(vo.getStartDate()!=null){
				sb.append("  and rpd.current_end_date > to_date(?").append(paramsIndex++).append(",'yyyy-mm-dd')");
				params.add(CommonHelper.date2Str(vo.getStartDate(), CommonHelper.DF_DATE));
			}
			if(vo.getEndDate()!=null){
				sb.append("  and rpd.current_end_date <= to_date(?").append(paramsIndex++).append(",'yyyy-mm-dd')");;
				params.add(CommonHelper.date2Str(vo.getEndDate(), CommonHelper.DF_DATE));
			}
		}
		sb.append("  order by rpd.current_end_date,rpd.repaying_plan_detail_id ");
		Pageable pageable =new PageRequest(pageNum, pageSize);
		//查询
		Page<Object[]> page= dynamicQuery.nativeQuery(Object[].class,pageable, sb.toString(), params.toArray());
		//转换
		Page<NormalRepaymentVo> pageVo=new PageImpl<NormalRepaymentVo>(Lists.transform(page.getContent(), new Function<Object[], NormalRepaymentVo>() {
			@Override
			public NormalRepaymentVo apply(Object[] objs) {
				return new NormalRepaymentVo(objs);
			}
		}), pageable, page.getTotalElements());
		return pageVo;
	}
	
	@Override
	public NormalRepaymentDetailVo findNrDetail(long contractId, long partyId,
			long rpId, long rpdId,Long curOrgId) {
		NormalRepaymentDetailVo vo=new NormalRepaymentDetailVo();
		Contract contract=contractDao.findOne(contractId);
		//计算还款编号
		String repayLoanNum=this.buildRepayLoanNum(contract.getCustomerNum(),curOrgId);//还款编号
		vo.setRepayLoanNum(repayLoanNum);
		BeanUtils.copyProperties(contract, vo);
		vo.setContractTerm(contract.getContractTermTotal());//合同期限
		vo.setContractTermUnit(dataDict.getCodeName("TermUnitCd", contract.getContractTermUnitTotal()));//合同期限单位
		if(contract.getContractRate()!=null){//合同利率*100
			vo.setContractRate(MathUtil.BDmultiply(contract.getContractRate(),MathUtil.ONE_HUNDRED, 2));
		}
		vo.setExpirationDate(sysCommonService.computeExpirationDate(
				contract.getPayloanDate(), contract.getContractTermTotal(),
				contract.getContractTermUnitTotal()));//到期日期
		vo.setCumulativeRepayAmt(contract.getCumulativeRepayAmt());//累计回收额
		this.addContractOtherInfo(vo, contractId);
		
		return vo;
	}
	
	/**
	 * 生成还款编号
	 * @param customerNum
	 * @param curOrgId
	 * @return
	 */
	private String buildRepayLoanNum(String customerNum,Long curOrgId) {
		List<Party> partyList=dynamicQuery.query(Party.class, "from Party p where p.customerNum=?1", customerNum);
		Party party=null;
		if(CollectionUtils.isNotEmpty(partyList)){
			party=partyList.get(0);
		}
		if(party==null){
			throw new NullPointerException("客户信息不存在");
		}
		return commonBizNumberBS.generateAppNumber("K", party.getPartyTypeCd(),String.valueOf(curOrgId));
	}
	
	/**
	 * 增加贷款产品名称、客户经理名称等信息
	 * @param lrVo
	 * @param contractId
	 */
	private void addContractOtherInfo(NormalRepaymentDetailVo nrVo,Long contractId){
		StringBuffer strbBuffer=new StringBuffer();
		strbBuffer.append("select p.product_name,eop.usernum,eop.name")
		.append(" from contract cont  left join product p on cont.product_type =p.product_cd")
		.append(" left join ec_org_person eop on eop.id=cont.apply_user_num where cont.contract_id=?1");
		Object[] objs=dynamicQuery.nativeQuerySingleResult(Object[].class, strbBuffer.toString(), contractId);
		if (objs!=null) {
			nrVo.setProductName(CommonHelper.toStr(objs[0]));
			nrVo.setCustMngNum(CommonHelper.toStr(objs[1]));
			nrVo.setCustMngName(CommonHelper.toStr(objs[2]));
		}
	}
	
}
