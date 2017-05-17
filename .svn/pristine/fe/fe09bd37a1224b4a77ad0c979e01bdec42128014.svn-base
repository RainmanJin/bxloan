package com.coamctech.bxloan.service.collateral.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.management.RuntimeErrorException;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.crypto.RuntimeCryptoException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coamctech.bxloan.commons.dynamicquery.DynamicQuery;
import com.coamctech.bxloan.commons.utils.MathUtil;
import com.coamctech.bxloan.dao.CollateralDao;
import com.coamctech.bxloan.dao.LandProduceDao;
import com.coamctech.bxloan.dao.MachineEquipmentMortgageDao;
import com.coamctech.bxloan.dao.ProjectPawnInfoDao;
import com.coamctech.bxloan.dao.RealEstateMortgageDao;
import com.coamctech.bxloan.dao.TrafficCarDao;
import com.coamctech.bxloan.datadict.DataDict;
import com.coamctech.bxloan.entity.Collateral;
import com.coamctech.bxloan.entity.Individual;
import com.coamctech.bxloan.entity.LandProduce;
import com.coamctech.bxloan.entity.MachineEquipmentMortgage;
import com.coamctech.bxloan.entity.ProjectPawnInfo;
import com.coamctech.bxloan.entity.RealEstateMortgage;
import com.coamctech.bxloan.entity.TrafficCar;
import com.coamctech.bxloan.service.collateral.CollateralService;
import com.coamctech.bxloan.service.model.collateral.CollateralVO;
import com.coamctech.bxloan.service.model.tipmsg.MsgResult;
import com.coamctech.bxloan.service.model.tipmsg.ResultEnums;
import com.coamctech.bxloan.service.pettyloan.util.CommonHelper;

@Service
@Transactional
public class CollateralServiceImpl implements CollateralService {
	private final String REV_GUARANTEE_STATUS_CD = "RevGuaranteeStatusCd";
	@Autowired
	private DynamicQuery dynamicQuery;

	@Autowired
	private CollateralDao collateralDao;

	@Autowired
	private LandProduceDao landProduceDao;

	@Autowired
	private MachineEquipmentMortgageDao machineEquipmentMortgageDao;

	@Autowired
	private RealEstateMortgageDao realEstateMortgageDao;

	@Autowired
	private TrafficCarDao trafficCarDao;
	
	
	@Autowired
	private ProjectPawnInfoDao projectPawnInfoDao;
	@Autowired
	private DataDict dataDict;

	@SuppressWarnings("unchecked")
	@Override
	public Page<Object[]> findBySearch(Integer pageNumber, Integer pageSize,
			Collateral form, String guaranteeMode, Long orgId, Long projectId) {
		StringBuffer strBuffer = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		strBuffer
				.append("select coll.GUARANTY_ID,coll.GUARANTY_NUM,coll.GUARANTOR_NAME,")
				.append("coll.GUARANTY_NAME,coll.GUARANTY_TYPE_CD,coll.market_value,coll.EVAL_VALUE,")
				.append("coll.SET_GUARANTY_AMT,coll.GUARANTY_STATUS_CD")
				.append(" from COLLATERAL coll where coll.REGISTER_ORG_CD = ?1");
		params.add(orgId);
		if (form != null) {
			int paramsIndex = 2;// 参数索引
			if (StringUtils.isNotBlank(form.getGuarantorName())) {
				strBuffer.append(" and coll.guarantor_name like ?"
						+ paramsIndex++);
				params.add("%" + form.getGuarantorName() + "%");
			}
			if (StringUtils.isNotBlank(form.getGuarantyName())) {
				strBuffer.append(" and coll.guaranty_name like ?"
						+ paramsIndex++);
				params.add("%" + form.getGuarantyName() + "%");
			}
			if (StringUtils.isNotBlank(form.getGuarantyStatusCd())) {
				strBuffer.append(" and coll.guaranty_status_cd = ?"
						+ paramsIndex++);
				params.add(form.getGuarantyStatusCd());
			}
		}
		strBuffer.append(" order by coll.GUARANTY_ID desc");
		return dynamicQuery.nativeQuery(new PageRequest(pageNumber, pageSize),
				strBuffer.toString(), params.toArray());
	}

	@Override
	public void delete(Long id) {
		Collateral collateral = collateralDao.findOne(id);
		Integer guarantyTypeCd = Integer.parseInt(collateral
				.getGuarantyTypeCd());
		switch (guarantyTypeCd) {
		case 0:// 房产
			collateralDao.delete(id);
			break;
		case 1:// 地产
			landProduceDao.delete(id);
			break;
		case 2:// 机器设备
			machineEquipmentMortgageDao.delete(id);
			break;
		case 3:// 机动车
			trafficCarDao.delete(id);
			break;
		default:
			break;
		}
		collateralDao.delete(id);
	}

	@Override
	public void save(Collateral collateral) {
		collateralDao.save(collateral);
	}

	@Override
	public void saveLandProduce(LandProduce landProduce) {
		landProduceDao.save(landProduce);
	}

	@Override
	public void saveMachineEquipmentMortgage(
			MachineEquipmentMortgage machineEquipmentMortgage) {
		machineEquipmentMortgageDao.save(machineEquipmentMortgage);
	}

	@Override
	public void saveRealEstateMortgage(RealEstateMortgage realEstateMortgage) {
		realEstateMortgageDao.save(realEstateMortgage);
	}

	@Override
	public void saveTrafficCar(TrafficCar trafficCar) {
		trafficCarDao.save(trafficCar);
	}

	@Override
	public Collateral get(Long id) {
		return collateralDao.findOne(id);
	}

	@Override
	public LandProduce getLandProduce(Long id) {
		return landProduceDao.findOne(id);
	}

	@Override
	public MachineEquipmentMortgage getMachineEquipmentMortgage(Long id) {
		return machineEquipmentMortgageDao.findOne(id);
	}

	@Override
	public RealEstateMortgage getRealEstateMortgage(Long id) {
		return realEstateMortgageDao.findOne(id);
	}

	@Override
	public TrafficCar getTrafficCar(Long id) {
		return trafficCarDao.findOne(id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<Object[]> findCustomersBySearch(Integer pageNumber,
			Integer pageSize, Long curUserId, Individual form) {
		List<Object> params = new ArrayList<Object>();
		StringBuffer strBuffer = new StringBuffer();
		strBuffer
				.append("select 1,p.customer_num,p.party_name,p.party_type_cd,p.certificate_type_cd,p.certificate_num,")
				.append("p.status,(select eop.name from ec_org_person eop where eop.id=cmt.user_num ) custMngName")
				.append(" from party p left join customer_manager_team cmt on p.party_id = cmt.party_id")
				.append(" where cmt.user_num=?1");
		params.add(curUserId);
		if (form != null) {
			int i = 1;
			if (StringUtils.isNotBlank(form.getCustomerName())) {
				strBuffer.append(" and p.party_name like ?" + ++i);
				params.add("%" + form.getCustomerName() + "%");
			}
			if (StringUtils.isNotBlank(form.getCertificateNum())) {
				strBuffer.append(" and p.certificate_num like ?" + ++i);
				params.add("%" + form.getCertificateNum() + "%");
			}
			if (StringUtils.isNotBlank(form.getCertificateTypeCd())) {
				strBuffer.append(" and p.certificate_type_cd = ?" + ++i);
				params.add(form.getCertificateTypeCd());
			}
		}
		strBuffer.append(" ORDER By p.party_id DESC");
		return dynamicQuery.nativeQuery(new PageRequest(pageNumber, pageSize),
				strBuffer.toString(), params.toArray());
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<Object[]> findBySearchForBiz(Integer pageNumber,
			Integer pageSize, Collateral form, String guaranteeMode,
			Long orgId, Long projectId) {
		StringBuffer sql = null;

		List<ProjectPawnInfo> projectPawnInfos = projectPawnInfoDao
				.findByProjectId(projectId);
		StringBuffer s = new StringBuffer();
		for (int i = 0; i < projectPawnInfos.size(); i++) {
			s.append(projectPawnInfos.get(i).getGuarantyId());
			if (i != projectPawnInfos.size() - 1)
				s.append(",");
		}

		sql = new StringBuffer(
				"select '',t.guaranty_num,t.guarantor_name,t.guaranty_name,c.code_name guaranty_type_cd,t.eval_value,");
		sql.append("t.set_guaranty_amt,d.code_name guaranty_status_cd,e.code_name guaranty_type,t.guaranty_id from COLLATERAL t ");
		sql.append("join CODE c on t.guaranty_type_cd=c.code_value and c.code_type='ResvAssetTypeCode' ");
		sql.append("join CODE d on t.guaranty_status_cd=d.code_value and d.code_type='RevGuaranteeStatusCd'");
		sql.append("join CODE e on t.GUARANTEE_TYPE=e.code_value and e.code_type='GuaranteeTypeCode' ");
		sql.append("where t.REGISTER_ORG_CD = " + orgId);
		if (StringUtils.isNotBlank(s))
			sql.append(" and t.guaranty_id not in (" + s + ")");

		if (guaranteeMode.contains("1") && !guaranteeMode.contains("2")) {
			sql.append(" and t.GUARANTEE_TYPE = '1'");
		} else if (!guaranteeMode.contains("1") && guaranteeMode.contains("2")) {
			sql.append(" and t.GUARANTEE_TYPE = '2'");
		}
		List<Object> params = new ArrayList<Object>();

		if (form != null) {
			int i = 0;
			if (StringUtils.isNotBlank(form.getGuarantorName())) {
				sql.append(" and t.guarantor_name like ?" + ++i);
				params.add("%" + form.getGuarantorName() + "%");
			}
			if (StringUtils.isNotBlank(form.getGuarantyName())) {
				sql.append(" and t.guaranty_name like ?" + ++i);
				params.add("%" + form.getGuarantyName() + "%");
			}
			if (StringUtils.isNotBlank(form.getGuarantyStatusCd())) {
				sql.append(" and t.guaranty_status_cd = ?" + ++i);
				params.add(form.getGuarantyStatusCd());
			}
			if (StringUtils.isNotBlank(form.getGuaranteeType())) {
				sql.append(" and t.guarantee_type = ?" + ++i);
				params.add(form.getGuaranteeType());
			}
		}
		sql.append(" order by t.sys_update_date desc");
		return dynamicQuery.nativeQuery(new PageRequest(pageNumber - 1,
				pageSize), sql.toString(), params.toArray());
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<Object[]> findCustomersBySearchForBiz(Integer pageNumber,
			Integer pageSize, Individual form) {

		List<Object> params = new ArrayList<Object>();
		StringBuffer sql = new StringBuffer(
				"select '',i.customer_num,i.customer_name,c.code_name professional,d.code_name certificate_type,");
		sql.append("i.certificate_num,e.code_name status,i.certificate_type_cd from individual i ");
		sql.append("left join code c on i.professional=c.code_value and c.code_type='CustomerType' ");
		sql.append("left join code d on i.certificate_type_cd=d.code_value and d.code_type='CertificateType' ");
		sql.append("left join code e on i.status=e.code_value and e.code_type='CustomerStatusCode' where 1=1");
		if (form != null) {
			int i = 0;
			if (StringUtils.isNotBlank(form.getCustomerName())) {
				sql.append(" and i.customer_name like ?" + ++i);
				params.add("%" + form.getCustomerName() + "%");
			}
			if (StringUtils.isNotBlank(form.getCertificateNum())) {
				sql.append(" and i.certificate_num like ?" + ++i);
				params.add("%" + form.getCertificateNum() + "%");
			}
			if (StringUtils.isNotBlank(form.getCertificateTypeCd())) {
				sql.append(" and i.certificate_type_cd = ?" + ++i);
				params.add(form.getCertificateTypeCd());
			}
		}
		return dynamicQuery.nativeQuery(new PageRequest(pageNumber - 1,
				pageSize), sql.toString(), params.toArray());
	}

	// 2014-10-17
	@Override
	public MsgResult deleteCollateral(Long id) {
		Collateral collateral = collateralDao.findOne(id);
		if (collateral == null) {
			return MsgResult.getMsgResult("01", "记录不存在！");
		}
		if (StringUtils.equals(collateral.getGuarantyStatusCd(),
				dataDict.getCodeVal(REV_GUARANTEE_STATUS_CD, "S2"))) {
			return MsgResult.getMsgResult("02", "该抵质押物已被关联！");
		}
		deleteOtherByGuarantyTypeCd(id, collateral.getGuarantorTypeCd());
		collateralDao.delete(id);// 抵押物总表
		return MsgResult.getMsgResult(ResultEnums.SUCCESS);
	}

	@Override
	public MsgResult saveCollateral(CollateralVO form, Long curUserId,
			Long curOrgId) {
		Collateral db_Collateral = null;
		if (form.getGuarantyId() != null) {
			db_Collateral = collateralDao
					.findByGuarantyId(form.getGuarantyId());
		}
		final String guarantyTypeCd = form.getGuarantyTypeCd();
		if (db_Collateral == null) {// 新增
			db_Collateral = new Collateral();
			BeanUtils.copyProperties(form, db_Collateral, "guarantyId",
					"sysUpdateDate");
			db_Collateral.setGuarantyStatusCd("1");// 未关联
			db_Collateral.setRegisterUserNum(curUserId.toString());
			db_Collateral.setRegisterOrgCd(curOrgId);
			db_Collateral.setSysCreateDate(new Date());
		} else {// 编辑
			if (!StringUtils.equals(guarantyTypeCd,
					db_Collateral.getGuarantyTypeCd())) {// 清除
				deleteOtherByGuarantyTypeCd(db_Collateral.getGuarantyId(),
						db_Collateral.getGuarantyTypeCd());
			}
			BeanUtils.copyProperties(form, db_Collateral, "guarantyId",
					"sysUpdateDate");
		}
		db_Collateral.setGuaranteeType(this.findGuaranteeType(guarantyTypeCd));// 更新担保类型
		db_Collateral.setSysUpdateDate(new Date());//
		if ("2".equals(db_Collateral.getCommonAssetsInd())) {
			// 如果是否共同财产为否，共有人名称为空
			db_Collateral.setPartOwnerName(null);
		}
		if ("2".equals(db_Collateral.getGuarantySetupInd())) {
			// 如果是否已设定抵质押为否，已设定担保额为空
			db_Collateral.setSetGuarantyAmt(null);
		}
		collateralDao.save(db_Collateral);
		final Long guarantyId = db_Collateral.getGuarantyId();
		Integer key = CommonHelper.toInt(guarantyTypeCd);
		if (key == null) {
			throw new NullPointerException("数据错误");
		}
		switch (key) {
		case 0:// 房产
			RealEstateMortgage dbRealEstateMortgage = realEstateMortgageDao
					.findOne(guarantyId);
			;
			if (dbRealEstateMortgage == null) {
				dbRealEstateMortgage = new RealEstateMortgage();
				dbRealEstateMortgage.setGuarantyId(guarantyId);
			}
			BeanUtils.copyProperties(form, dbRealEstateMortgage, "guarantyId");
			dbRealEstateMortgage.setConstructedDate(CommonHelper.str2Date(
					form.getConstructedDate(), CommonHelper.DF_DATE));
			realEstateMortgageDao.save(dbRealEstateMortgage);
			break;
		case 1:// 地产
			LandProduce dbLandProduce = landProduceDao.findOne(guarantyId);
			if (dbLandProduce == null) {
				dbLandProduce = new LandProduce();
				dbLandProduce.setGuarantyId(guarantyId);
			}
			BeanUtils.copyProperties(form, dbLandProduce, "guarantyId");
			dbLandProduce.setLandEndDate(CommonHelper.str2Date(
					form.getLandEndDate(), CommonHelper.DF_DATE));
			landProduceDao.save(dbLandProduce);
			break;
		case 2:// 机器设备
			MachineEquipmentMortgage dbMem = machineEquipmentMortgageDao
					.findOne(guarantyId);
			if (dbMem == null) {
				dbMem = new MachineEquipmentMortgage();
				dbMem.setGuarantyId(guarantyId);
			}
			BeanUtils.copyProperties(form, dbMem, "guarantyId");
			machineEquipmentMortgageDao.save(dbMem);
			break;
		case 3:// 机动车
			TrafficCar dbTc = trafficCarDao.findOne(guarantyId);
			if (dbTc == null) {
				dbTc = new TrafficCar();
				dbTc.setGuarantyId(guarantyId);
			}
			BeanUtils.copyProperties(form, dbTc, "guarantyId");
			dbTc.setRegisterDate(CommonHelper.str2Date(form.getRegisterDate(),
					CommonHelper.DF_DATE));
			trafficCarDao.save(dbTc);
			break;
		default:
			break;
		}
		return MsgResult.getMsgResult(db_Collateral.getGuarantyId().toString(), null);
	}

	/**
	 * 删除房产、地产、机器设备、机动车
	 * 
	 * @param id
	 * @param guarantyType
	 */
	private void deleteOtherByGuarantyTypeCd(Long id, String guarantyTypeCd) {
		// 删除原从表信息
		Integer guarantyTypeCdForDelete = Integer.parseInt(guarantyTypeCd);
		switch (guarantyTypeCdForDelete) {
		case 0:// 房产
			realEstateMortgageDao.delete(id);
			break;
		case 1:// 地产
			landProduceDao.delete(id);
			break;
		case 2:// 机器设备
			machineEquipmentMortgageDao.delete(id);
			break;
		case 3:// 机动车
			trafficCarDao.delete(id);
			break;
		default:
			break;
		}
	}

	@Override
	public String findGuaranteeType(String guarantyTypeCd) {
		// 与mortgagetype_table对应
		if (StringUtils.isNoneBlank(guarantyTypeCd)
				&& dataDict.getCodeValList("ResvAssetTypeCode", "S0", "S1",
						"S2", "S3", "S4", "S17", "S18")
						.contains(guarantyTypeCd)) {
			return "1";
		}
		return "2";
	}

	@Override
	public Collateral findCollateralById(Long guarantyId) {
		return collateralDao.findByGuarantyId(guarantyId);
	}

	@Override
	public ProjectPawnInfo findProjectPawnInfoById(Long projectPawnInfoId) {
		return projectPawnInfoDao.findOne(projectPawnInfoId);
	}

	@Override
	public List<ProjectPawnInfo> findProjectPawnInfoListByProjectId(
			Long projectId) {
		return projectPawnInfoDao.findByProjectId(projectId);
	}

	@Override
	public void saveProjectPawnInfo(ProjectPawnInfo ppi) {
		this.projectPawnInfoDao.save(ppi);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<Object[]> findHistoryBySearch(Integer pageNumber,
			Integer pageSize, Long guarantyId) {
		StringBuffer sb = new StringBuffer(
				"select c.guaranty_name, pa.project_no, cd1.code_name as project_status, ct.contract_num, "
						+ "cd2.code_name as contract_status, pa.customer_name, pa.sys_cd  "
						+ "from collateral c "
						+ "join project_pawn_info ppi on c.guaranty_id = ppi.guaranty_id "
						+ "left join project_application pa on ppi.project_id = pa.project_id "
						+ "left join contract ct on ct.project_id = pa.project_id "
						+ "left join CODE cd1 on pa.project_status = cd1.code_value and cd1.code_type = 'ProjectStatus' "
						+ "left join CODE cd2 on ct.contract_status_cd = cd2.code_value and cd2.code_type = 'ContractStatusCode' "
						+ "where 1=1");
		List<Object> params = new ArrayList<Object>();
		if (guarantyId == null) {
			sb.append(" and c.guaranty_id is null");
		} else {
			sb.append(" and c.guaranty_id = ?1");
			params.add(guarantyId);
		}
		return dynamicQuery.nativeQuery(new PageRequest(pageNumber - 1,
				pageSize), sb.toString(), params.toArray());
	}
	/**
	 * 功能说明：根据项目id，处理抵质押物列表
	 * 当合同结清或者提前还款时，合同状态为【合同结清】，累计抵押金额（即已设定金额）需要释放
	 * 主要对象：【关联新增】，【已关联】，【关联置换】
	 * @param projectId  
	 */
	@Override
	public void handlePledgeAmtWhenContractEnd(Long projectId) {
		try {
			List<ProjectPawnInfo> listPawn = projectPawnInfoDao.findByProjectId(projectId);
			//将相应的担保物对应的抵质押物进行金额的释放，以及判断是否修改抵质押物的状态，并且删除担保物
			if(CollectionUtils.isNotEmpty(listPawn)){
				ProjectPawnInfo projectPawnInfo = null;
				Collateral collateral = null;
				for (ProjectPawnInfo ppi : listPawn) {
					String pawnState = ppi.getPawnState();
					//状态为【关联新增】和【关联置换】和【已关联】需要  扣除抵押物累计抵押金额
					collateral = collateralDao.findByGuarantyId(ppi.getGuarantyId());
					if(pawnState.equals(dataDict.getCodeVal("PawnState", "S1"))
							||pawnState.equals(dataDict.getCodeVal("PawnState", "S2"))
							||pawnState.equals(dataDict.getCodeVal("PawnState", "S3"))){
						//累计抵押金额=累计抵押金额-贷款放款金额
						deductSetGuarantyAmt(collateral, ppi.getActualCreditAmount());
						//抵押物状态需要改，但是有点复杂，判断（所有合同）已解除关联
						if(!this.isPledgeIsUsedByGuarantyId(collateral.getGuarantyId())){
							//已解除关联
							collateral.setGuarantyStatusCd(dataDict.getCodeVal("RevGuaranteeStatusCd", "S3"));
						}else{
							//已关联
							collateral.setGuarantyStatusCd(dataDict.getCodeVal("RevGuaranteeStatusCd", "S2"));
						}
						collateralDao.save(collateral);
					}
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new  RuntimeCryptoException(e.getMessage());
		}
	}
	
	
	/**
	 * 功能说明：根据抵质押物id,判断（所有合同）是否已解除关联
	 * @param guarantyId  
	 */
	@Override
	public boolean isPledgeIsUsedByGuarantyId(Long guarantyId) throws RuntimeException{
		boolean result=false;
		
		if(null==guarantyId){
			throw new  RuntimeException("获取抵质押物ID为空，请与系统管理员联系");
		}
		
		StringBuffer sql =new StringBuffer("select count(1) from project_application pa  where 1 = 1");
		sql.append(" and ((pa.project_id in (select p.project_id  from project_pawn_info p where 1=1" );
		sql.append(" and p.pawn_state!='").append(dataDict.getCodeVal("PawnState", "S4")).append("'");
		sql.append(" and p.guaranty_id = ?1 ) ");
		sql.append(" and pa.project_status in ");
		sql.append("(" + dataDict.getCodeValList("ProjectStatus", "S0","S1","S2")+")) ");
		sql.append(" or pa.project_id in  (select c.project_id from contract c where 1 = 1  and c.project_id in");
		sql.append("(select p.project_id  from project_pawn_info p where 1=1 ");
		sql.append(" and p.pawn_state!='").append(dataDict.getCodeVal("PawnState", "S4")).append("'");
		sql.append(" and p.guaranty_id ='").append(guarantyId).append("')");
		sql.append(" and  (c.contract_status_cd in ");
		
		sql.append("(").append(dataDict.getCodeValList("ContractStatusCode", "S1","S2","S3","S5","S6","S8","S9","S10")).append(") ");
		sql.append(" and c.if_release_flag is null)))");
		List<Long> count = dynamicQuery.nativeQuery(Long.class, sql.toString(), guarantyId);
		if(CollectionUtils.isNotEmpty(count)){
			if(count.size()>1){
			result=true;
			}
		}
	    return result;
	}
	/**
	 * 功能说明：抵质押物实际担保金额：从抵押物累计抵押金额中扣除
	 *  累计抵押金额=累计抵押金额-贷款放款金额
	 * @param collateral  
	 */
	@Override
	public void deductSetGuarantyAmt(Collateral collateral, BigDecimal actualCreditAmount) {
		try {
			//已设定金额（即累计抵押金额）
			BigDecimal setGuarantyAmt=collateral.getSetGuarantyAmt();
			if(null==setGuarantyAmt){
				setGuarantyAmt=new BigDecimal(0);
			}
			//实际担保物的担保金额
			if(null==actualCreditAmount){
				actualCreditAmount=new BigDecimal(0);
			}
			collateral.setSetGuarantyAmt(MathUtil.BDsubtract(setGuarantyAmt, actualCreditAmount, 2));
		} catch (Exception e) {
			throw new  RuntimeException(e.getMessage());
		}
	}
}
