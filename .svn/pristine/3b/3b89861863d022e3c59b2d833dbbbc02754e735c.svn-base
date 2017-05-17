package com.coamctech.bxloan.service.corpcustomer.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coamctech.bxloan.commons.GlobalConstants;
import com.coamctech.bxloan.commons.dynamicquery.DynamicQuery;
import com.coamctech.bxloan.commons.utils.Checkout;
import com.coamctech.bxloan.commons.utils.DateTools;
import com.coamctech.bxloan.dao.AddressDao;
import com.coamctech.bxloan.dao.CorpCustomerRelaCorpDao;
import com.coamctech.bxloan.dao.CorpCustomerRelaPersonDao;
import com.coamctech.bxloan.dao.CorporationCustomerDao;
import com.coamctech.bxloan.dao.CustomerManagerTeamDao;
import com.coamctech.bxloan.dao.DocumentIndexDao;
import com.coamctech.bxloan.dao.IndividualDao;
import com.coamctech.bxloan.dao.PartyDao;
import com.coamctech.bxloan.datadict.DataDict;
import com.coamctech.bxloan.entity.Address;
import com.coamctech.bxloan.entity.CorpCustomerRelaCorp;
import com.coamctech.bxloan.entity.CorpCustomerRelaPerson;
import com.coamctech.bxloan.entity.CorporationCustomer;
import com.coamctech.bxloan.entity.CustomerManagerTeam;
import com.coamctech.bxloan.entity.DocumentIndex;
import com.coamctech.bxloan.entity.Individual;
import com.coamctech.bxloan.entity.Party;
import com.coamctech.bxloan.service.corpcustomer.CorporationCustomerService;
import com.coamctech.bxloan.service.model.CheckResult;
import com.coamctech.bxloan.service.model.NationAreaVo;
import com.coamctech.bxloan.service.model.corpcustomer.CorpAddCorpVO;
import com.coamctech.bxloan.service.model.corpcustomer.CorpAddPersonVO;
import com.coamctech.bxloan.service.model.corpcustomer.CorpCusSaveVO;
import com.coamctech.bxloan.service.model.corpcustomer.CorpRelaCorpDetailVO;
import com.coamctech.bxloan.service.model.corpcustomer.CorpRelaPersonDetailVO;
import com.coamctech.bxloan.service.model.tipmsg.MsgResult;
import com.coamctech.bxloan.service.sysmng.DocumentService;
import com.coamctech.bxloan.service.sysmng.impl.Generator;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@Service
@Transactional
public class CorporationCustomerServiceImpl implements CorporationCustomerService{

	private static final Logger log = LoggerFactory.getLogger(CorporationCustomerServiceImpl.class);
	
	private static final String CORP_CUSTOMER_PARTY_TYPE_CD = "1";
	
	private static final String DICT_CODE_TYPE_ROLE_CODE = "RoleCode";
	private static final String DICT_CODE_KEY_CUSTOMER_MANAGER = "S60170";
	/**管护权**/
	private static final String CMT_TYPE_ADMIN = "01";
	
	@Autowired
	private AddressDao addressDao;
	
	@Autowired
	private DocumentIndexDao documentIndexDao;
	
	@Autowired
	private DynamicQuery query;
	
	@Autowired
	private DataDict codeRepo;
	
	@Autowired
	private Generator generator;
	
	@Autowired
	private PartyDao partyDao;
	
	@Autowired
	private DataDict dataDict;
	
	@Autowired
	private CustomerManagerTeamDao cusManaTeamDao;
	
	@Autowired
	private CorporationCustomerDao corpCusDao;
	
	@Autowired
	private CorpCustomerRelaPersonDao corpCusRelaPersonDao;
	
	@Autowired
	private CorpCustomerRelaCorpDao corpCusRelaCorpDao;
	
	@Autowired
	private IndividualDao individuaDao;
	
	@Autowired
	private DocumentService documentService;
	
	@Override
	@SuppressWarnings("unchecked")
	public int findCorpCountByCertificateNum(String certificateNum){
		String jql = "SELECT COUNT(*) FROM CorporationCustomer WHERE certificateNum =?1";
		List<Object> result =  (List<Object>) this.query.query(jql, certificateNum);
		return Integer.parseInt(result.get(0).toString());
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public int findBlackListCount(String certificateTypeCd,String certificateCd){
		String jql = "SELECT COUNT(*) FROM BlackList b WHERE b.certificateTypeCd=?1 AND b.certificateCd=?2";
		this.query.query(jql, certificateTypeCd,certificateCd);
		List<Object> result =  (List<Object>) this.query
				.query(jql, certificateTypeCd,certificateCd);
		return Integer.parseInt(result.get(0).toString());
	}
	
	/**
	  * 组织机构检验
	  * @param strOrgNum
	  * @return
	  * @throws BizException
	  */
	@Override
	public CheckResult checkCertificateNum(String certificateNum) throws Exception {

		CheckResult checkResult = new CheckResult();
		checkResult.setErrorResult(Boolean.FALSE);
		
		if (certificateNum.length() != 10 && certificateNum.length() != 18) {
			// 需求第一部分，客户组织机构代码只能为10位或者18位数
			checkResult.setErrorMsg("组织机构编码长度只能为10位或者18位数");
			checkResult.setErrorResult(Boolean.TRUE);
			return checkResult;
		} 
		
		// 组织机构号前8位和最后一位只能为大小写英文字母或数字，否则错误，客户端提示信息：“组织机构编码中含有非法字符！”
		if (certificateNum.getBytes().length != certificateNum.length()) {
			checkResult.setErrorMsg("组织机构代码中不能含有中文，不能含有全角字符！");
			checkResult.setErrorResult(Boolean.TRUE);
			return checkResult;
		}
		
		for (int i = 0; i < certificateNum.length(); i++) {
			if (i == 8)
				continue;
			if (!Character.isLetterOrDigit(certificateNum.charAt(i))) {
				checkResult.setErrorMsg("组织机构编码中含有非法字符");
				checkResult.setErrorResult(Boolean.TRUE);
				return checkResult;
			}
			if (Character.isSpaceChar(certificateNum.charAt(i))) {
				checkResult.setErrorMsg("组织机构编码中含有非法字符");
				checkResult.setErrorResult(Boolean.TRUE);
				return checkResult;
			}
		}
		// #37208 modify by huangrj
		
		if (certificateNum.length() == 10) {
			
			if (certificateNum.getBytes().length != certificateNum.length()) {
				checkResult.setErrorMsg("组织机构代码中不能含有中文，不能含有全角字符！");
				checkResult.setErrorResult(Boolean.TRUE);
				return checkResult;
			}

			if ("00000000-0".equals(certificateNum)) {
				checkResult.setErrorMsg("组织机构编码错误不能为00000000-0");
				checkResult.setErrorResult(Boolean.TRUE);
				return checkResult;
			}
			
			if (!"-".equals(certificateNum.substring(8, 9))) {
				checkResult.setErrorMsg("组织机构编码第9位必须为横线！");
				checkResult.setErrorResult(Boolean.TRUE);
				return checkResult;
			}

			// 定义权值数组w[1] = 3, w[2] = 7, w[3] = 9,w[4] = 10, w[5] = 5, w[6] = 8,
			// w[7] = 4, w[8] = 2
			int qzArray[] = new int[] { 3, 7, 9, 10, 5, 8, 4, 2 };
			byte[] bOrgnum;
			bOrgnum = certificateNum.toLowerCase().getBytes("US-ASCII");
			int c = 0;
			int s = 0;
			for (int j = 0; j < qzArray.length; j++) {
				if (Character.isLetter(certificateNum.charAt(j))) {
					c = bOrgnum[j] - 87;
				} else if (Character.isDigit(certificateNum.charAt(j))) {
					c = Character.getNumericValue(certificateNum.charAt(j));
				}
				s = s + qzArray[j] * c;
			}
			int x = 11 - s % 11;
			if (Character.isDigit(certificateNum.charAt(9))) {
				if ((certificateNum.charAt(9) == '0' && x == 11)
						|| Character.getNumericValue(certificateNum.charAt(9)) == x) {
					return checkResult;
				} else {
					checkResult.setErrorMsg("组织机构编码中含有不符合规则字符！");
					checkResult.setErrorResult(Boolean.TRUE);
					return checkResult;
				}
			} else if (Character.isLetter(certificateNum.charAt(9))) {
				if (certificateNum.charAt(9) == 'X' && x == 10) {
					return checkResult;
				} else {
					checkResult.setErrorMsg("组织机构编码中含有不符合规则字符！");
					checkResult.setErrorResult(Boolean.TRUE);
					return checkResult;
				}
			}
		}
		return checkResult;

	}

	public String generateCustomerNumber(String customerType,
			String certificateType, String certificateNumber)
			throws Exception {
		if (log.isInfoEnabled()) {
			log.info("==>Enter:generateCustomerNumber(participantType="
					+ customerType + ", certificateType=" + certificateType
					+ ", certificateNumber=" + certificateNumber + ")");
		}
		if (StringUtils.isBlank(certificateType)) {
			certificateType = "1";// 默认值
		}
		if (StringUtils.isBlank(certificateNumber)) {
			throw new Exception("客户编号生成异常：证件号码不能为空!");
		}
		String key = "";
		if (codeRepo.getUniqueOne("CustomerType", "S1").getCodeValue().equals(customerType)) {
			key = GlobalConstants.CUSTOMER_ENTERPRISE_KEY;
		} else if (codeRepo.getUniqueOne("CustomerType", "S2").getCodeValue()
				.equals(customerType)) {
			key = GlobalConstants.CUSTOMER_PERSONAL_KEY;
		} else if (codeRepo.getUniqueOne("CustomerType", "S3").getCodeValue()
				.equals(customerType)) { // 添加上“第三方机构”客户类别
			key = GlobalConstants.CUSTOMER_THREE_ORG_KEY;
		}

		Long sequenceNumber = this.generator.generateSequenceNumber(key);// 得到序号(Long)
		if (log.isDebugEnabled()){
			log.debug("得到序号(Long)==" + sequenceNumber);
		}

		/** 字符串类型的序号 */
		String strSequenceNumber = sequenceNumber.toString();


		/** 校验码 */
		char checkString = '0';
		try {
			checkString = Checkout.generate(certificateType + certificateNumber);
			if (log.isDebugEnabled()){
				log.debug("checkString=" + checkString);
			}
		} catch (Exception e) {
			log.error("产生检验位时出错！", e);
			throw new Exception("产生客户序列号时出错", e);
		}

		String sequenceNum = certificateType + certificateNumber
				+ strSequenceNumber + checkString;

		String yyMMdd = DateTools.dateToString(new Date(), "yyMMdd");
		String customerNum = "";
		Long customerNumber = this.generator.generateSequenceNumber(key+ yyMMdd);
		/**
		 * 1、 客户编号规则 （12位） 客户类型（1位字符，C表示企业客户，P表示个人客户） + 时间戳（6位数字yymmdd） +
		 * 序列号（4位数字） + 校验码(1位字符，ISO 7064 MOD 37,36算法）youjg change
		 */
		if (StringUtils.isNotBlank(sequenceNum)) {
			int sequenceNumberLength = 4;
			customerNum = key + yyMMdd + 
					this.getSequenceNumberOfString(customerNumber, sequenceNumberLength)
					+ checkString;
		}
		if (StringUtils.isBlank(customerNum)) {
			throw new Exception("客户编号生成异常：请联系系统管理员!");
		}
		return customerNum;
	}
	/**
	 * 把数字类型的序号转成字符串类型，长度不足的在前面补"0"
	 * 
	 * @param sequenceNumber
	 *            序号
	 * @param length
	 *            输出字符串序号的长度
	 * @return
	 * @throws BizException
	 *             当规定输出的字符串序号的长度小于数字类型的序号长度
	 */
	private String getSequenceNumberOfString(Long sequenceNumber, int length) throws Exception {
		if (sequenceNumber == null)
			throw new IllegalArgumentException("sequence number can NOT be null");
		
		String originSeqNumber = sequenceNumber.toString();
		int originLength = originSeqNumber.length();
		
		if (length < originLength){
			throw new IllegalStateException("the length (" + length + ") is small than the sequeceNumber(" + sequenceNumber
					+ ")'s length");
		}
		
		return StringUtils.leftPad(originSeqNumber, length, "0");
//		else if (length == originLength){
//			return originSeqNumber;
//		}
//		else{
//			String zero = "00000000000000000000000000000000000000000000000000";
//			return zero.substring(0, length - originLength) + originSeqNumber;
//			
//		}
			
	}

	public Party doSaveParty(Party party) {
		return this.partyDao.save(party);
	}

	public CorporationCustomer saveCorprationCustomer(
			CorporationCustomer cusToSave) {
		return this.corpCusDao.save(cusToSave);
	}

	public CustomerManagerTeam saveCustomerManagerTeam(
			CustomerManagerTeam cusManaTeamToSave) {
		return this.cusManaTeamDao.save(cusManaTeamToSave);
	}

	@Override
	public Long createCorpCustomer(String certificateNum, 
									String customerName,
									String businessLicenseNum,
									String customerNum,
									String managerUserId,
									String managerOrgid,
									String managerDepId) {
		Party savedParty = saveParty(certificateNum, customerName, customerNum);
		
		CorporationCustomer savedCus = saveCorpCus(savedParty.getPartyId(),certificateNum, customerName, 
												businessLicenseNum,customerNum,
												managerUserId);
		CustomerManagerTeam savedCUsMaTeam = 
				saveCusManaTeam(savedParty,managerUserId,managerOrgid,managerDepId);
		return savedParty.getPartyId();
	}
	
	
	
	private CustomerManagerTeam saveCusManaTeam(Party savedParty,String managerUserNum,
			String managerOrgId, String managerDepId) {
		CustomerManagerTeam cusManaTeamToSave = new CustomerManagerTeam();
		Timestamp now = new Timestamp(System.currentTimeMillis());
		
		cusManaTeamToSave.setPartyId(savedParty.getPartyId());
		cusManaTeamToSave.setCustomerNum(savedParty.getCustomerNum());
		cusManaTeamToSave.setUserNum(managerUserNum);
		cusManaTeamToSave.setRoleCd(this.dataDict.
				getUniqueOne(DICT_CODE_TYPE_ROLE_CODE,DICT_CODE_KEY_CUSTOMER_MANAGER)
				.getCodeValue());
		cusManaTeamToSave.setDeptCd(managerDepId);
		cusManaTeamToSave.setOrgCd(managerOrgId);
		cusManaTeamToSave.setStates("1"); //客户经理状态 ，1代表有效，即未删除状态；
		cusManaTeamToSave.setCreateDate(now);
		cusManaTeamToSave.setSysUpdateTime(now);
		cusManaTeamToSave.setManagerType("01"); //新建该客户的经理为管户权客户经理；
		return  this.saveCustomerManagerTeam(cusManaTeamToSave);
	}
	

	private CorporationCustomer saveCorpCus(Long partyId,String certificateNum, String customerName,
			String businessLicenseNum, String customerNum,String managerUserNum) {
		CorporationCustomer cusToSave = new CorporationCustomer();
		Timestamp curTime = new Timestamp(System.currentTimeMillis());
		cusToSave.setPartyId(partyId);
		cusToSave.setCustomerName(customerName);
		cusToSave.setCustomerNum(customerNum);
		cusToSave.setCertificateNum(certificateNum);
		cusToSave.setBusinessLicenseNum(businessLicenseNum);
		cusToSave.setCreateDate(curTime);
		cusToSave.setStates("1");//1为未生效
		cusToSave.setLastUpdateUserNum(managerUserNum);//TODO
		cusToSave.setNewlyMaintenanceDate(curTime);
		return this.saveCorprationCustomer(cusToSave);
	}
	

	private Party saveParty(String certificateNum, String customerName,
			String customerNum) {
		Party partyToSave = new Party();
		partyToSave.setCustomerSource("2");
		partyToSave.setCustomerNum(customerNum);
		partyToSave.setSysUpdateTime(new Timestamp(System.currentTimeMillis()));
		partyToSave.setPartyName(customerName);
		partyToSave.setPartyTypeCd(CORP_CUSTOMER_PARTY_TYPE_CD);
		partyToSave.setCertificateNum(certificateNum);
		partyToSave.setCertificateTypeCd(
				this.dataDict.getUniqueOne("CertificateType", "S210").getCodeValue());//FIXME WTF IS S210??
		partyToSave.setStatus("1");//1为未生效
		Party savedParty = this.doSaveParty(partyToSave);
		return savedParty;
	}

	@Override
	public CorporationCustomer findById(Long partyId) {
		return this.corpCusDao.findOne(partyId);
	}

	@Override
	public CorporationCustomer update(CorpCusSaveVO cus) {
		CorporationCustomer cusTosave = this.corpCusDao.findOne(cus.getPartyId());
		BeanUtils.copyProperties(cus, cusTosave,"certificateNum","customerNum","partyId");
		return this.doUpdataCorpCus(cusTosave);
	}
	
	@Override
	public CorporationCustomer enable(CorpCusSaveVO cus) {
		CorporationCustomer cusTosave = this.corpCusDao.findOne(cus.getPartyId());
		BeanUtils.copyProperties(cus, cusTosave,"certificateNum","customerNum","partyId");
		cusTosave.setStates("2");//生效
		return this.doUpdataCorpCus(cusTosave);
	}
	
	private CorporationCustomer doUpdataCorpCus(CorporationCustomer cusTosave){
		cusTosave.setSysUpdateTime(new Timestamp(System.currentTimeMillis()));
		Party partyToUpdate = this.partyDao.findByPartyId(cusTosave.getPartyId());
		partyToUpdate.setPartyName(cusTosave.getCustomerName());
		partyToUpdate.setSysUpdateTime(new Date());
		partyToUpdate.setStatus(cusTosave.getStates());//两表的状态保持一致
		this.partyDao.save(partyToUpdate);
		cusTosave.setNewlyMaintenanceDate(new Date());
		return this.corpCusDao.save(cusTosave);
	}

	@Override
	public CorpCustomerRelaPerson savePerson(CorpAddPersonVO vo) {//TODO
		CorpCustomerRelaPerson entityToSave = new CorpCustomerRelaPerson();
		if(vo.getId()!=null){
			entityToSave = this.corpCusRelaPersonDao.findOne(vo.getId());
		}else{
			entityToSave.setCreateTime(new Date());
		}
		BeanUtils.copyProperties(vo, entityToSave, "id");
		return this.corpCusRelaPersonDao.save(entityToSave);
	}
	
	@Override
	public String valiPersonSaveVo(CorpAddPersonVO vo) {
		
		if(vo.getPersonIsActController().equals("1")){
			boolean dontHaveController = 
					this.findActControllerCount(vo.getPartyId(),vo.getId(),null)<=0;
			if(!dontHaveController){
				return "实际控制人只能为一个";
			}
		}
		
		if(vo.getPersonIsStockHolder().equals("1")){
			if(!checkStockShares(vo.getStockProportion(),
					vo.getPartyId(),vo.getId(),null)){
				return "总持股比例不能超过100%";
			}
		}
		
		if(vo.getPersonIsLegalRepresent().equals("1")){
			if(this.findLegalRepresentCount(vo.getPartyId(), vo.getId())>0){
				return "只能有一个法定代表人";
			}
		}
		
		return null;
	}

	/**
	 * 总持股比例不能超过100%
	 * @param id
	 * @return
	 * @author xc
	 * @param stockProportion 
	 */
	public boolean checkStockShares(BigDecimal share,Long partyId,Long personId,Long corpId) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT SUM(stock) ");
		sql.append("FROM ");
		sql.append("  (SELECT SUM(p.STOCK_PROPORTION) AS stock ");
		sql.append("  FROM CORP_CUSTOMER_RELA_PERSON p ");
		sql.append("  WHERE p.PARTY_ID = ?1 ");
		sql.append("  AND p.PERSON_IS_STOCK_HOLDER='1' ");
		sql.append("  AND p.id <> ?2 ");
		sql.append("  UNION ALL ");
		sql.append("  SELECT SUM(c.STOCK_PROPORTION) AS stock ");
		sql.append("  FROM CORP_CUSTOMER_RELA_CORP c ");
		sql.append("  WHERE c.PARTY_ID = ?1 ");
		sql.append("  AND c.CORP_IS_STOCK_HOLDER='1' ");
		sql.append("  AND c.id <> ?3 ");
		sql.append("  )");
		List<Object> params = new ArrayList<Object>(3);
		params.add(partyId);
		
		if (personId == null) {
			personId = 0L;
		}
		params.add(personId);

		if (corpId == null) {
			corpId = 0L;
		}
		params.add(corpId);
		
		BigDecimal percentSum = this.query.nativeQuerySingleResult(
				BigDecimal.class, sql.toString(), params.toArray());
		if(percentSum==null){
			percentSum = new BigDecimal("0");
		}
		BigDecimal fullPercent = new BigDecimal("100");
		percentSum = percentSum.add(share);
		return fullPercent.compareTo(percentSum)!=-1;
	}


	

	@Override
	public String valiRelaCorp(CorpAddCorpVO vo) {
		if(vo.getCorpIsActController().equals("1")){
			boolean dontHaveController = 
					this.findActControllerCount(vo.getPartyId(),null,vo.getId())<=0;
			if(!dontHaveController){
				return "实际控制人只能为一个";
			}
		}
		
		if(vo.getCorpIsStockHolder().equals("1")){
			if(!checkStockShares(vo.getStockProportion(),
					vo.getPartyId(),null,vo.getId())){
				return "总持股比例不能超过100%";
			}
		}
		return null;
	}

	@Override
	public CorpCustomerRelaCorp createRelaCorp(CorpAddCorpVO corp) {
		CorpCustomerRelaCorp corpToSave = new CorpCustomerRelaCorp();
		if(corp.getId()!=null){
			corpToSave = this.corpCusRelaCorpDao.findOne(corp.getId());
		}else{
			corpToSave.setCreateTime(new Date());
		}
		BeanUtils.copyProperties(corp, corpToSave,"id");
		return this.corpCusRelaCorpDao.save(corpToSave);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public Page<Object[]> listActController(Long partyId, Integer pageNo,
			Integer pageSize) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * ");
		sql.append("from ");
		sql.append("  (SELECT p.ID AS id, ");
		sql.append("    p.PARTY_ID, ");
		sql.append("    p.NAME, ");
		sql.append("    p.CERTIFICATE_TYPE_CD, ");
		sql.append("    p.CERTIFICATE_CD, ");
		sql.append("    p.FAMILY_TEL_NUM, ");
		sql.append("    p.CONTACT_TEL_NUM, ");
		sql.append("    p.FAMILY_ADDRESS, ");
		sql.append("    p.CREATE_TIME AS ct, ");
		sql.append("    'p'      AS RELA_TYPE ");
		sql.append("  FROM CORP_CUSTOMER_RELA_PERSON p ");
		sql.append("  WHERE p.PARTY_ID=?1");
		sql.append("  	AND p.PERSON_IS_ACT_CONTROLLER='1' ");
		sql.append("  UNION ALL ");
		sql.append("  SELECT c.ID AS id, ");
		sql.append("    c.PARTY_ID, ");
		sql.append("    c.CORP_NAME, ");
		sql.append("    c.CERTIFICATE_TYPE_CD, ");
		sql.append("    c.CERTIFICATE_CD, ");
		sql.append("    '-', ");
		sql.append("    c.CONTACTOR_TEL_NUM, ");
		sql.append("    '-', ");
		sql.append("    c.CREATE_TIME AS ct, ");
		sql.append("    'c'        AS RELA_TYPE ");
		sql.append("  FROM CORP_CUSTOMER_RELA_CORP c ");
		sql.append("  WHERE c.PARTY_ID=?1");
		sql.append("  	AND c.CORP_IS_ACT_CONTROLLER='1' ");
		sql.append("  ) ");
		sql.append("ORDER BY ct DESC");
		return this.query.nativeQuery(new PageRequest(pageNo, pageSize),
				sql.toString(), partyId);
	}

	@Override
	@SuppressWarnings("unchecked")
	public Page<Object[]> listStockHolder(Long partyId, Integer pageNo,
			Integer pageSize) {
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * ");
		sql.append("FROM ");
		sql.append("  (SELECT  ");
		sql.append("    p.ID AS id, ");
		sql.append("    p.PARTY_ID, ");
		sql.append("    p.NAME, ");
		sql.append("    p.CERTIFICATE_TYPE_CD, ");
		sql.append("    p.CERTIFICATE_CD, ");
		sql.append("    p.INVESTMENT_TYPE_CD, ");
		sql.append("    p.STOCK_PROPORTION, ");
		sql.append("    p.CONTACT_TEL_NUM, ");
		sql.append("    p.CREATE_TIME AS ct, ");
		sql.append("    'p'           AS RELA_TYPE ");
		sql.append("  FROM CORP_CUSTOMER_RELA_PERSON p ");
		sql.append("  WHERE p.PARTY_ID              =?1 ");
		sql.append("  AND p.PERSON_IS_STOCK_HOLDER='1' ");
		sql.append("  UNION ALL ");
		sql.append("  SELECT  ");
		sql.append("    c.ID AS id, ");
		sql.append("    c.PARTY_ID, ");
		sql.append("    c.CORP_NAME, ");
		sql.append("    c.CERTIFICATE_TYPE_CD, ");
		sql.append("    c.CERTIFICATE_CD, ");
		sql.append("    c.INVESTMENT_TYPE_CD, ");
		sql.append("    c.STOCK_PROPORTION, ");
		sql.append("    c.CONTACTOR_TEL_NUM, ");
		sql.append("    c.CREATE_TIME AS ct, ");
		sql.append("    'c'           AS RELA_TYPE ");
		sql.append("  FROM CORP_CUSTOMER_RELA_CORP c ");
		sql.append("  WHERE c.PARTY_ID            =?1 ");
		sql.append("  AND c.CORP_IS_STOCK_HOLDER='1' ");
		sql.append("  ) ");
		sql.append("ORDER BY ct DESC ");

		return this.query.nativeQuery(new PageRequest(pageNo, pageSize),
				sql.toString(), partyId);
	}

	@Override
	@SuppressWarnings("unchecked")
	public Page<Object[]> listHighManager(Long partyId, Integer pageNo,
			Integer pageSize) {
		StringBuilder sql = new StringBuilder();
		sql.append("  SELECT p.ID AS id, ");
		sql.append("    p.PARTY_ID, ");
		sql.append("    p.NAME, ");
		sql.append("    p.POSITION_CD,");
		sql.append("    p.PERSON_IS_LEGAL_REPRESENT, ");
		sql.append("    p.CERTIFICATE_TYPE_CD, ");
		sql.append("    p.CERTIFICATE_CD, ");
		sql.append("    p.FAMILY_TEL_NUM, ");
		sql.append("    p.CONTACT_TEL_NUM, ");
		sql.append("    p.CREATE_TIME AS ct ");
		sql.append("  FROM CORP_CUSTOMER_RELA_PERSON p ");
		sql.append("  WHERE p.PARTY_ID              =?1 ");
		sql.append("  AND p.PERSON_IS_MANAGER='1'");
		return this.query.nativeQuery(new PageRequest(pageNo, pageSize),
				sql.toString(), partyId);
	}
	
	@Override
	public Integer findActControllerCount(Long partyId,Long personId,Long corpId) {
		String relaPersonTypeColumn = "PERSON_IS_ACT_CONTROLLER";
		String relaCorpTypeColumn = "CORP_IS_ACT_CONTROLLER";
		
		return findCountByRelaType(partyId, personId, corpId,
				relaPersonTypeColumn, relaCorpTypeColumn);
	}
	
	@Override
	public Integer findStockHolderCount(Long partyId,Long personId,Long corpId){
		String relaPersonTypeColumn = "PERSON_IS_STOCK_HOLDER";
		String relaCorpTypeColumn = "CORP_IS_STOCK_HOLDER";
		
		return findCountByRelaType(partyId, personId, corpId,
				relaPersonTypeColumn, relaCorpTypeColumn);
		
	}

	private Integer findCountByRelaType(Long partyId, Long personId,
			Long corpId, String relaPersonTypeColumn, String relaCorpTypeColumn) {
		int paramIndex = 2;
		List<Object> params = new ArrayList<Object>();
		params.add(partyId);

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COUNT(p.id) ");
		sql.append("FROM CORP_CUSTOMER_RELA_PERSON p ");
		sql.append("WHERE  ");
		sql.append("  p.PARTY_ID = ?1 ");
		sql.append("  AND p.").append(relaPersonTypeColumn).append("='1' ");
		if(personId!=null){
			sql.append("  AND p.id <> ?").append(paramIndex).append(" ");
			params.add(personId);
			paramIndex++;
		}
		sql.append("UNION all ");
		sql.append("SELECT count(c.id) ");
		sql.append("FROM CORP_CUSTOMER_RELA_CORP c  ");
		sql.append("WHERE ");
		sql.append("  c.PARTY_ID  = ?1  ");
		sql.append("  AND c.").append(relaCorpTypeColumn).append("='1'  ");
		if(corpId!=null){
			sql.append("  AND c.id <> ?").append(paramIndex).append(" ");
			params.add(corpId);
			paramIndex ++;
		}

		
		List<Number> result = 
				this.query.nativeQuery(Number.class,sql.toString(),params.toArray());
		Integer total = 0; 
		for (Number count : result) {
			total += count.intValue();
		}
		return total;
	}

	@Override
	public Integer findLegalRepresentCount(Long partyId, Long personId) {
		List<Long> params = new ArrayList<Long>();
		StringBuilder jql = new StringBuilder();
	
		jql.append("SELECT COUNT(id) ");
		jql.append("FROM CorpCustomerRelaPerson t ");
		jql.append("WHERE t.personIsLegalRepresent = '1' ");
		jql.append("AND t.partyId=?1 ");
		params.add(partyId);
		if(personId!=null){
			jql.append(" AND t.id<>?2");
			params.add(personId);
		}
		Integer count = this.query
				.querySingleResult(Number.class, jql.toString(), params.toArray())
				.intValue();
		return count;
	}
	
	
	
	
	@Override
	public String validateEnableCorpCus(CorpCusSaveVO cus){
		
		String markType = StringUtils.trimToEmpty(cus.getMarkType());
		
		boolean hasProjectApp = this.findProjectApplicationCount(cus.getPartyId())>0L;
		boolean hasBorrow = this.findBorrowCount(cus.getPartyId())>0L;
		
		if(hasBorrow||hasProjectApp){
			if(!markType.contains("01")){//贷款人
				return "该客户已经有贷款历史,不能取消贷款人选项";
			}
		}
		
		boolean isAssurer = this.findAssurerCount(cus.getPartyId())>0L;
		if(isAssurer){
			if(!markType.contains("02")){//担保人
				return "该客户已经有担保历史,不能取消担保人选项";
			}
		}
		
		boolean registerAddr = this.findCountAddr(cus.getPartyId(),null,"2")==1L;
		if(!registerAddr){
			return  "必须填写注册地址信息";
		}
		
		boolean contactAddr = this.findCountAddr(cus.getPartyId(), null, "1")>0L;
		if(!contactAddr){
			return "必须填写联系地址信息";
		}
		
		boolean hasStockHolder = this.findStockHolderCount(cus.getPartyId(), null, null)>0;
		if(!hasStockHolder){
			return "至少输入一个股东信息";
		}
		
		boolean hasActController = 
				this.findActControllerCount(cus.getPartyId(), null, null)==1;
		if(!hasActController){
			return "必须输入一个实际控制人";
		}
		
		boolean legalPerson = this.findLegalRepresentCount(cus.getPartyId(), null)==1;
		if(!legalPerson){
			return "高管必须输入一位法定代表人";
		}
		return null;
	}

	/**
	 * 查看是否有业务申请
	 * @param partyId
	 * @return
	 * @author xc
	 */
	@Override
	public Long findProjectApplicationCount(Long partyId) {
		String jqpl = "from ProjectApplication p where p.partyId = ?1 and p.projectStatus in (0,1,2)";
		return this.query.queryCount(jqpl, partyId);
	}
	

	/**
	 * 获取作为担保人次数
	 * @param partyId
	 * @return
	 * @author xc
	 */
	private Long findAssurerCount(Long partyId) {
		StringBuilder jql = new StringBuilder();
		jql.append("SELECT COUNT(*) ");
		jql.append("FROM ProjectAssurerInfo pa ");
		jql.append("WHERE pa.certificateTypeCd='210' ");
		jql.append("AND pa.certificateNum = ( ");
		jql.append("SELECT certificateNum FROM CorporationCustomer WHERE partyId = ?1) ");
		jql.append("AND (SELECT projectStatus FROM ProjectApplication WHERE projectId = pa.projectId) in (0,1,2) ");
		return this.query.querySingleResult(Long.class, jql.toString(), partyId);
	}

	/**
	 * 获取作为联合借款人次数
	 * @param partyId
	 * @return
	 * @author xc
	 */
	private Long findBorrowCount(Long partyId) {
		StringBuilder jql = new StringBuilder();
		jql.append("SELECT COUNT(*) ");
		jql.append("FROM CommonBorrow c ");
		jql.append("WHERE c.certificateTypeCd='210' ");
		jql.append("AND c.certificateNum = ( ");
		jql.append("SELECT certificateNum FROM CorporationCustomer WHERE partyId = ?1) ");
		jql.append("AND (SELECT projectStatus FROM ProjectApplication WHERE projectId = c.projectId) in (0,1,2) ");
		return this.query.querySingleResult(Long.class, jql.toString(), partyId);
	}

	@Override
	public void removeRelaPerson(Long id) {
		this.corpCusRelaPersonDao.delete(id);
	}

	@Override
	public void removeRelaCorp(Long id) {
		this.corpCusRelaCorpDao.delete(id);
	}

	@Override
	public CorpCustomerRelaPerson findRelaPersonById(Long id) {
		return this.corpCusRelaPersonDao.findOne(id);
	}

	@Override
	public CorpCustomerRelaCorp findRelaCorpById(Long id) {
		return this.corpCusRelaCorpDao.findOne(id);
	}
	
	public Page<Object[]> addressPageList(int pageSize,int pageNum,Long partyId){
		StringBuilder strBuffer=new StringBuilder();
		strBuffer.append("select addr.address_id,addr.address_type_cd,addr.street_address,addr.telephone,addr.sys_update_time,");
		strBuffer.append("addr.nationality_cd,(select na.nation_area_name from nation_area na where na.nation_area_cd=addr.nationality_cd) nationality_name,");
		strBuffer.append("addr.province_cd,(select na.nation_area_name from nation_area na where na.nation_area_cd=addr.province_cd) province_name,");
		strBuffer.append("addr.city_cd,(select na.nation_area_name from nation_area na where na.nation_area_cd=addr.city_cd) city_name,");
		strBuffer.append("addr.county_cd,(select na.nation_area_name from nation_area na where na.nation_area_cd=addr.county_cd) county_name");
		strBuffer.append(" from address addr");
		strBuffer.append(" where addr.party_id=?1 ");
		strBuffer.append(" order by addr.address_id desc");
		return query.nativeQuery(Object[].class ,new PageRequest(pageNum, pageSize), strBuffer.toString(), partyId);
	}
	@Override
	public Address getAddr(Long id) {
		return addressDao.findOne(id);
	}

	@Override
	public Address saveAddr(Address address) {
		if(address==null){
			throw new NullPointerException("地址信息不可为空");
		}
		Address temp_address=null;
		if(address.getAddressId()!=null){
			temp_address=addressDao.findOne(address.getAddressId());//修改时查询
		}
		if(temp_address==null){
			temp_address=new Address();
			temp_address.setCreateDate(new Date());
			temp_address.setPartyId(address.getPartyId());
		}
		temp_address.setSysUpdateTime(new Date());
		temp_address.setAddressTypeCd(address.getAddressTypeCd());
		temp_address.setTelephone(address.getTelephone());
		temp_address.setStreetAddress(StringUtils.trim(address.getStreetAddress()));
		temp_address.setCustomerNum(address.getCustomerNum());
		temp_address.setNationalityCd(address.getNationalityCd());
		temp_address.setProvinceCd(address.getProvinceCd());
		temp_address.setCityCd(address.getCityCd());
		temp_address.setCountyCd(address.getCountyCd());
		temp_address.setCustomerNum(address.getCustomerNum());
		temp_address.setWebsite(address.getWebsite());
		temp_address.setFax(address.getFax());
		temp_address.setZipNum(address.getZipNum());
		addressDao.save(temp_address);
		return temp_address;
	}
	@Override
	public long findCountAddr(Long partyId,Long addrId,String addrTypeCd){
		StringBuilder strBuffer=new StringBuilder("select * from address addr where addr.address_type_cd=?1 and addr.party_id=?2");
		List<Object> params=Lists.newArrayList();
		params.add(addrTypeCd);
		params.add(partyId);
		if(addrId!=null&&addrId!=0){
			strBuffer.append(" and addr.address_id != ?3");
			params.add(addrId);
		}
		return query.nativeQueryCount(strBuffer.toString(), params.toArray());
	}

	@Override
	public void deleteAddr(Long id) {
		addressDao.delete(id);
	}
	@Override
	public Page<Object[]> docIndexPageList(int pageSize, int pageNum,
			Long partyId, String docName, String docContentType, String docType, String docStatus,
			String docNum) {
		StringBuilder sBuffer=new StringBuilder("select di.document_id,di.document_num,di.document_name,di.document_type,")
		.append("di.customer_num,cc.customer_name,di.create_user_num,eop.name,di.create_date_time,di.create_type_cd,di.document_route, di.cust_doc_type ")
		.append(" from document_index di left join corporation_customer cc on di.customer_num=cc.customer_num")
		.append(" left join ec_org_person eop on eop.usernum=di.create_user_num where di.party_id=?1");
		List<Object> params=Lists.newArrayList();
		params.add(partyId==null?0:partyId);
		int paramsCounter=2;
		if(StringUtils.isNotBlank(docName)){
			sBuffer.append(" and di.document_name like ?").append(paramsCounter++);
			params.add("%"+docName+"%");
			
		}
		if(StringUtils.isNotBlank(docContentType)){
			List<String> custDocValues = documentService.findCustDocTypeNames(docContentType);
			if(CollectionUtils.isNotEmpty(custDocValues)){
				sBuffer.append(" and di.cust_doc_type in (?"+ (paramsCounter++) +") ");
				params.add(custDocValues);
			}else{
				sBuffer.append(" and di.cust_doc_type in ('') ");
			}
			
		}
		if(StringUtils.isNotBlank(docType)){
			sBuffer.append(" and di.document_type = ?").append(paramsCounter++);
			params.add(docType);
			
		}
		if(StringUtils.isNotBlank(docStatus)){
			sBuffer.append(" and di.status = ?").append(paramsCounter++);
			params.add(docStatus);
			
		}
		if(StringUtils.isNotBlank(docNum)){
			sBuffer.append(" and di.document_num like ?").append(paramsCounter++);
			params.add("%"+docNum+"%");
			
		}
		sBuffer.append(" order by di.document_id desc");
		return query.nativeQuery(Object[].class, new PageRequest(pageNum, pageSize), sBuffer.toString(), params.toArray());
		
	}
	@Override
	public List<NationAreaVo> findNationAreaList(String code) {
		List<Object[]> resultList=null;
		if(StringUtils.isBlank(code)){
			resultList=query.nativeQuery(Object[].class, "SELECT na.nation_area_cd,na.nation_area_name,na.parent_nation_area_cd,na.code_key  FROM nation_area na WHERE na.usable_status_ind='1' and na.parent_nation_area_cd is null ORDER BY na.region_level, na.order_num");
		}else{
			resultList=query.nativeQuery(Object[].class, "SELECT na.nation_area_cd,na.nation_area_name,na.parent_nation_area_cd,na.code_key  FROM nation_area na WHERE na.usable_status_ind='1' and na.parent_nation_area_cd = ?1 ORDER BY na.region_level, na.order_num",code);
		}
		if(CollectionUtils.isEmpty(resultList)){
			return null;
		}
		List<NationAreaVo> list=Lists.transform(resultList, new Function<Object[], NationAreaVo>() {
			@Override
			public NationAreaVo apply(Object[] input) {
				return new NationAreaVo(input);
			}
		});
		return list;
	}
	@Override
	public MsgResult updateDocumentIndexStatus(Long id,String userNum) {
		DocumentIndex docIndex=documentIndexDao.findOne(id);
		if(StringUtils.equals(dataDict.getCodeVal("CreateType", "S2"), docIndex.getCreateTypeCd())){
			return MsgResult.getMsgResult("used", "该文档正在被使用中");
		}
		docIndex.setUserNum(userNum);
		docIndex.setSystemUpdateTime(new Date());
		docIndex.setStatus("2");//表删除
		documentIndexDao.save(docIndex);
		return MsgResult.getMsgResult("success", "删除成功");
	}

	@Override
	public CustomerManagerTeam findCusManager(Long partyId, String userNum) {
		String jql = "FROM CustomerManagerTeam c WHERE c.partyId=?1 AND c.userNum=?2";
		List<CustomerManagerTeam> result =
				this.query.query(CustomerManagerTeam.class,jql,partyId,userNum);
		if(CollectionUtils.isEmpty(result)){
			return null;
		}else{
			return result.get(0);
		}
	}
	
	@Override
	public String getUserName(String userNum){
		return corpCusDao.getUserName(userNum);
	}

	@Override
	public List<Address> getAddressByPartyId(Long partyId) {
		return addressDao.findByPartyId(partyId);
	}
	
	@Override
	public Page<Object[]> existingPartyList(
											int pageNum,
											int pageSize, 
											String partyTypeCd,
											String name,
											String licence,
											String certificatetypeCd) {
		List<String> params = new ArrayList<String>();
		params.add(partyTypeCd);
		int paramIndex = 2;
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT p.PARTY_ID, ");
		sql.append("  p.CUSTOMER_NUM, ");
		sql.append("  p.PARTY_NAME , ");
		sql.append("  p.CERTIFICATE_TYPE_CD , ");
		sql.append("  c.CODE_NAME, ");
		sql.append("  p.CERTIFICATE_NUM, ");
		sql.append("  p.STATUS, ");
		sql.append("  ep.NAME ");
		sql.append("FROM party p ");
		sql.append("LEFT JOIN CODE c ");
		sql.append("ON (c.CODE_VALUE = p.CERTIFICATE_TYPE_CD ");
		sql.append("AND c.CODE_TYPE  ='CertificateType') ");
		sql.append("LEFT JOIN customer_manager_team cmt ");
		sql.append("ON (cmt.PARTY_ID     = p.PARTY_ID ");
		sql.append("AND cmt.MANAGER_TYPE = '").append(CMT_TYPE_ADMIN).append("')  ");
		sql.append("LEFT JOIN ec_org_person ep ");
		sql.append("ON TO_CHAR(ep.ID)     = cmt.USER_NUM ");
		sql.append("WHERE p.PARTY_TYPE_CD = ?1 ");

		if(StringUtils.isNotBlank(name)){
			name = StringUtils.trim(name).replace("%", "\\%");
			params.add("%" + name + "%");
			sql.append("	AND p.PARTY_NAME like ?").append(paramIndex++).append(" ");
		}
		if(StringUtils.isNotBlank(licence)){
			licence = licence.trim();
			params.add(licence);
			sql.append("	AND p.CERTIFICATE_NUM =?").append(paramIndex++).append(" ");
		}
		if(StringUtils.isNotBlank(certificatetypeCd)){
			certificatetypeCd = certificatetypeCd.trim();
			params.add(certificatetypeCd);
			sql.append("	AND p.CERTIFICATE_TYPE_CD =?").append(paramIndex++).append(" ");
		}
		sql.append("ORDER BY p.PARTY_ID DESC ");
		
		return this.query.nativeQuery(Object[].class,
								new PageRequest(pageNum, pageSize), 
								sql.toString(), 
								params.toArray());
	}
	
	@Override
	public CorpRelaCorpDetailVO findExistingCorpDetail(Long partyId) {
		CorporationCustomer corp = this.corpCusDao.findOne(partyId);
		CorpRelaCorpDetailVO result = new CorpRelaCorpDetailVO();
		result.setActualRevAmt(corp.getActualRevAmt());
		result.setBusinessLicenseNum(corp.getBusinessLicenseNum());
		result.setCertificateCd(corp.getCertificateNum());
		result.setContactorName(corp.getLinkmanName());
		result.setContactorTelNum(corp.getLinkmanTel());
		result.setCorpName(corp.getCustomerName());
		result.setOrgTypeCd(corp.getOrgTypeCd());
		result.setCurrencyCd(corp.getRegisterCapitalCurrencyCd());
		result.setRegisteredCapital(corp.getRegisteredCapital());
		result.setTaxRegistrationNumLocal(corp.getLocalTaxRegistrationCum());
		result.setTaxRegistrationNumNational(corp.getNationalTaxRegistrationNum());
		return result;
	}
	
	@Override
	public CorpRelaPersonDetailVO findExistingPersonDetail(Long partyId) {
		Individual ind = this.individuaDao.findOne(partyId);
		CorpRelaPersonDetailVO result = new CorpRelaPersonDetailVO();
		result.setName(ind.getCustomerName());
		result.setBirthDate(ind.getBirthday());
		result.setCertificateCd(ind.getCertificateNum());
		result.setCertificateTypeCd(ind.getCertificateTypeCd());
		result.setCompanyAddress(ind.getCompanyAddress());
		result.setContactTelNum(ind.getMobileTel());
		result.setCreditRecord(ind.getCreditRecord());
		result.setDegreeCd(ind.getDegreeCd());
		result.setFamilyAddress(ind.getFamilyAddress());
		result.setFamilyMonthlyTotalAmt(ind.getFamilyMonthlyTotalAmt());
		result.setFamilyMonthlyTotalPayout(ind.getFamilyMonthlyTotalPayout());
		result.setFamilyTelNum(ind.getFamilyTel());
		result.setFamilyTotalAsset(ind.getFamilyTotalAsset());
		result.setFamilyTotalOwes(ind.getFamilyTotalOwes());
		result.setIncomeBonusYear(ind.getBonusAndOtherIncome());
		result.setIncomeMonthFact(ind.getMonIncome());
		result.setInhabitancyStatus(ind.getInhabitancyStatus());
		result.setMailQq(ind.getEmail());
		result.setNation(ind.getNation());
		result.setPositionCd(ind.getPosition());
		result.setProfessional(ind.getProfessional());
		result.setWorkCompany(ind.getWorkCompany());
		result.setWorkCompanyTel(ind.getCompanyTel());
		return result;
	}

	@Override
	public Map<String, String> findUploadCustDocTypes() {
		Map<String, String> map = Maps.newHashMap();
		String sql = "select c.code_name,c.code_value from code c where c.code_type ='BwdProcCustomerDocType' ";
		List<Object[]> list = query.nativeQuery(sql);
		for (Object[] obj : list) {
			map.put(obj[1].toString(), obj[0].toString());
		}
		return map;
	}

	@Override
	public List<String> findDocumentCustDocTypes(Long partyId) {
		String jpql = "select di.custDocType from DocumentIndex di where di.partyId = ?1 " +
					  "and di.status = '1' " +
					  "and di.documentType = '01' "+
					  "and di.custDocType is not null ";
		return (List<String>) query.query(jpql, partyId);
	}
	
}
