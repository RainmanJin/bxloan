package com.coamctech.bxloan.service.sysmng.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coamctech.bxloan.commons.GlobalConstants;
import com.coamctech.bxloan.commons.dynamicquery.DynamicQuery;
import com.coamctech.bxloan.commons.utils.Checkout;
import com.coamctech.bxloan.commons.utils.CommonHelper;
import com.coamctech.bxloan.commons.utils.DateTools;
//import com.coamctech.bxloan.service.sysmng.CommonDBService;
import com.coamctech.bxloan.service.sysmng.ICommonBizNumberBS;

@Service("commonBizNumberBS")
public class CommonBizNumberBSImpl implements ICommonBizNumberBS {
	private final static String zero = "00000000000000000000000000000000000000000000000000";
	//modify by wangyawei 20150615 start 修改机构编号规则与小贷系统一致
	@Autowired
	private DynamicQuery dynamicQuery;
	//modify by wangyawei 20150615 end
	
	@Autowired
	private Generator generator;// Generator.newInstance();
/*
	public void setGenerator(Generator generator) {
		this.generator = generator;
	}*/

	/**
	 * 生成业务对象编号
	 * 
	 * @param bizType
	 *            业务类型编号 如："KHED"
	 * @param organizationNo
	 *            用户机构Id
	 * @return String 业务事件编号
	 * @
	 * @since 业务事件编号的生成规则是：业务事件类型+机构+年份+序号， 一共30位长，其中： <br>
	 *        业务事件类型参见编号规则文档中制定的业务事件编号 <br>
	 *        机构号采用全国统一的机构编码，9位长度 <br>
	 *        年份4位，为YYYY方式 <br>
	 *        序号为5位, 与机构相关，每年年初重置 <br>
	 *        <p/> 业务事件编号的生成步骤如下：<br>
	 *        年份＝当前的年份 <br>
	 *        序号=IGenerator.getSequenceNumber("BizType+organizationNo+年份") <br>
	 *        将(BizType,organizationNo,年份,序号) 赋值到业务事件编号<br>
	 */
	public String generateBizEventNumber(String bizType, String organizationNo)  {
		organizationNo = this.getOrganizationNo(organizationNo);
		String strYear = new SimpleDateFormat("yyMMdd").format(new Date());// 得到年份
		String key = bizType + organizationNo + strYear;// 得到产生序号的参数
		Long sequenceNumber = this.generator.generateSequenceNumber(key);// 得到序号(Long)
		return bizType + organizationNo + strYear + getSequenceNumberOfString(sequenceNumber, 4);
	}

	/**
	 * 生成会次<br>
	 * 会次由15位组成：年份(6位)+类型(1位)+机构编号(后4位)+【补齐0】序号(4位)<br>
	 * 返回值=年份+type+序号<br>
	 */
	public String generateMeetingNumber(String type,String organizationNo)  {  
		String strYear = new SimpleDateFormat("yyMMdd").format(new Date());// 得到年份
		String key =new SimpleDateFormat("yyyy").format(new Date());// 得到产生序号的参数
		organizationNo = this.getOrganizationNo(organizationNo);//得到机构编号后4位
		Long sequenceNumber = this.generator.generateSequenceNumber(key);// 得到序号(Long)
		return strYear + type + organizationNo +getSequenceNumberOfString(sequenceNumber, 4);
	}


	/**
	 * 把数字类型的序号转成字符串类型，长度不足的在前面补"0"
	 * 
	 * @param sequenceNumber
	 *            序号
	 * @param length
	 *            输出字符串序号的长度
	 * @return
	 * @
	 *             当规定输出的字符串序号的长度小于数字类型的序号长度
	 */
	private String getSequenceNumberOfString(Long sequenceNumber, int length)  {
		if (sequenceNumber == null)
			throw new IllegalArgumentException("sequence number can NOT be null");

		String originSequenceNumber = sequenceNumber.toString();
		int originLength = originSequenceNumber.length();

		if (length < originLength){
			//throw new BizException("the length (" + length + ") is small than the sequeceNumber(" + sequenceNumber+ ")'s length");
		    System.out.println("the length (" + length + ") is small than the sequeceNumber(" + sequenceNumber+ ")'s length");
		    return null;
		}
		else if (length == originLength)
			return originSequenceNumber;
		else
			return zero.substring(0, length - originLength) + originSequenceNumber;
	}

	/**
	 * 生成会次<br>
	 * 会次由9位组成：年份(4位)+类型(1位)+【补齐0】序号(4位)<br>
	 * 
	 * @param orgCode
	 *            建行机构代码
	 * @param type
	 *            类型代码。分6种情况，分别为：授信（包括额度、业务）会议审批——1、授信（包括额度、业务）会签审批——2、评级会议审定——3、评级会签审定——4、保全会议审批——5、保全会签审批——6
	 * @return 会次
	 * @
	 * @since 年份＝当前的年份<br>
	 *        序号=IGenerator.getSequenceNumber(orgCode+年份+type)<br>
	 *        序号前补齐0<br>
	 * 
	 * 返回值=年份+type+序号<br>
	 */
	public String generateMeetingSerialNumber(String orgCode, String type)  {
		if (StringUtils.isBlank(orgCode)) {
			//throw new BizException(" orgCode is null");
		}
		SimpleDateFormat formater = new SimpleDateFormat("yyyy");
		String strYear = formater.format(new Date());// 得到年份
		Long meetSerial = this.generator.generateSequenceNumber(orgCode + strYear + type);
		return strYear + type + getSequenceNumberOfString(meetSerial, 4);
	}

	/**
	 * 生成文档编号 文档编号生成规则：文档编号25位=客户编号+文档类型+年月日+【补齐0】+序号
	 * 
	 * @author zhud
	 * @date 2011-11-11
	 * @param customerNum
	 * @param documentType
	 *            文档类型，01-客户、02-业务、03-合同、04-回访
	 * @
	 * @return String 文档编号
	 * @since
	 * 
	 * <pre>
	 * </pre>
	 */
	public String generateDocumentNum(String customerNum, String documentType)  {

		if (StringUtils.isNotBlank(customerNum) && StringUtils.isNotBlank(documentType)) {

			SimpleDateFormat formater = new SimpleDateFormat("yyMMdd");
			String strDateTime = formater.format(new Date());// 得到日期时间
			Long documentSerial = this.generator.generateSequenceNumber(customerNum + documentType + strDateTime);
			return customerNum + documentType + strDateTime + this.getSequenceNumberOfString(documentSerial, 4);
		} else {
            return null;
			//throw new BizException("客户编号、文档类型为空，生成文档编号异常！");
		}
	}

	/**
	 * 生成文件名称编号 文件名称编号生成规则：文件名称编号25位=客户编号+年月日+【补齐0】+序号
	 * 
	 * @author zhud
	 * @date 2011-11-22
	 * @param customerNum
	 * @return
	 * @
	 * @since
	 * 
	 * <pre>
	 * </pre>
	 */
	public String generateDocumentNameNum(String customerNum)  {

		if (StringUtils.isNotBlank(customerNum)) {

			SimpleDateFormat formater = new SimpleDateFormat("yyMMdd");
			String strDateTime = formater.format(new Date());// 得到日期时间
			Long documentSerial = this.generator.generateSequenceNumber(customerNum + strDateTime);
			return customerNum + strDateTime + this.getSequenceNumberOfString(documentSerial, 6);
		} else {
			return null;
			//throw new BizException("客户编号为空，生成文件名称编号异常！");
		}
	}

	/**
	 * 生成文件名称编号 文件名称编号生成规则：文件名称编号25位=客户编号+年月日+【补齐0】+序号
	 * 
	 * @author williamtwo
	 * @date 2012-05-28
	 * @param customerNum
	 * @param curDate
	 *            指定的日期
	 * @return
	 * @
	 * @since
	 * 
	 * <pre>
	 * </pre>
	 */
	public String generateDocumentNameNum(String customerNum, Date curDate, String reportType)  {

		if (StringUtils.isNotBlank(customerNum)) {

			SimpleDateFormat formater = new SimpleDateFormat("yyMMdd");
			String strDateTime = formater.format(curDate);// 得到日期时间
			Long documentSerial = this.generator.generateSequenceNumber(customerNum + strDateTime);
			return customerNum + strDateTime + reportType + this.getSequenceNumberOfString(documentSerial, 6);
		} else {
			return null;
			//throw new BizException("客户编号为空，生成文件名称编号异常！");
		}
	}

	/**
	 * 生成用户编号
	 * 
	 * @since
	 * 
	 * <pre>
	 * </pre>
	 */
	public String generateUserNum(String organizationNo)  {
        if(StringUtils.isNotBlank(organizationNo)&&!"null".equals(organizationNo)){
    		organizationNo = this.getOrganizationNo(organizationNo);
    		String strYear = new SimpleDateFormat("yyMMdd").format(new Date());// 得到年份
    		String key = organizationNo + strYear;// 得到产生序号的参数
    		Long sequenceNumber = this.generator.generateSequenceNumber(key);// 得到序号(Long)
    		return organizationNo + strYear + getSequenceNumberOfString(sequenceNumber, 4);
        }else{
        	return null;
        	//throw new BizException("获取机构异常,请重新登录");
        }
	}

	/**
	 * 生成文档事件编号/文档模板编号
	 * 
	 * @author 郑思静
	 * @date 2011-11-16
	 * @param DocumentEventNum
	 *            事件编号SJ DocumentTempNum 文档模板编号MB
	 * @return String 文档事件编号
	 * @
	 * @since 文档事件编号生成规则：文档编号20位=SJ+年月日+序号（2+8+4=14位）
	 *        文档模板编号生成规则：文档编号20位=SJ+年月日+序号（2+8+4=14位） 将(DocumentEventNum,年份,序号)
	 *        赋值到业务事件编号<br>
	 */
	public String generateDocumentEventAndTempNum(String documentNum, int digitNum)  {
		if (StringUtils.isNotBlank(documentNum)) {
			SimpleDateFormat formater = new SimpleDateFormat("yyyyMMdd");
			String strDateTime = formater.format(new Date());// 得到日期时间
			Long documentEventSerial = this.generator.generateSequenceNumber(documentNum + strDateTime);
			return documentNum + strDateTime + getSequenceNumberOfString(documentEventSerial, digitNum);
		} else {
			return null;
			//throw new BizException("事件编号为空，生成文档事件编号异常！");
		}
	}

	/**
	 * 生成客户编号
	 * 1、输入代码不足18位时，生成的客户编号的校验位前加'＃'；如果大于或等于18位，则不用加'＃'。
	 * 2、根据证件号码生成校验位时，忽略所有的非字母和非数字，小写字母转换成大写字母计算。
	 * 
	 * @param customerType -
	 *            参与人类型: 1-企业客户，2-个人客户,3-第三方机构客户（对应codetype='CustomerType'）
	 * @param certificateType -
	 *            证件类型：字母（车贷用A，企贷用C，个贷用P）
	 * @param certificateNumber -
	 *            证件编号: 机构号去除'-' ( orgNum.replaceAll("-", "") )
	 * @return 客户编号
	 * @
	 * @since 客户编号组成： T-证件类型代码 N-证件号码 S-不定长NUMBER（从1开始递增）
	 *        序号=IGenerator.getSequenceNumber(participantType+certificateType+certificateNumber)，序号前补齐0
	 *        <br>
	 *        C-校验字符（1位）：采用ISO 7064-1983 SJ/Z 9092 MOD37，36校验系统。算法待定。
	 *        其中N+S一共19位, 序号前补齐0。例如P12345678900000000013C
	 */
	public String generateCustomerNumber(String customerType, String certificateType, String certificateNumber){
		if (StringUtils.isBlank(certificateType)) {
			certificateType = "1";// 默认值
		}
		if (StringUtils.isBlank(certificateNumber)) {
			//throw new BizException("客户编号生成异常：证件号码不能为空!");
            return null;
		}
		String key = "";
		//if (CodeTable.getValue("CustomerType", "S1").equals(customerType)) {
		if ("1".equals(customerType)) {
			key = GlobalConstants.CUSTOMER_ENTERPRISE_KEY;
		} //else if (CodeTable.getValue("CustomerType", "S2").equals(customerType)) {
		else if ("2".equals(customerType)) {
			key = GlobalConstants.CUSTOMER_PERSONAL_KEY;
		}
		//else if (CodeTable.getValue("CustomerType", "S3").equals(customerType)) {  //添加上“第三方机构”客户类别
		else if ("3".equals(customerType)) {
			key = GlobalConstants.CUSTOMER_THREE_ORG_KEY;
		}

		Long sequenceNumber = this.generator.generateSequenceNumber(key);// 得到序号(Long)

		/** 字符串类型的序号 */
		String strSequenceNumber = sequenceNumber.toString();


		/** 校验码 */
		// char checkString = '#';
		char checkString = '0';
		try {
			checkString = Checkout.generate(certificateType + certificateNumber);
		} catch (Exception e) {
			e.printStackTrace();
		}

		String sequenceNum = certificateType + certificateNumber + strSequenceNumber + checkString;

		//Calendar c = Calendar.getInstance();
		String customerNum = "";
		Long customerNumber = this.generator.generateSequenceNumber(key+(DateTools.dateToString(new Date(), "yy")) + (DateTools.dateToString(new Date(), "MM"))+DateTools.dateToString(new Date(), "dd"));
		/**
		 * 1、 客户编号规则 （12位） 客户类型（1位字符，C表示企业客户，P表示个人客户） + 时间戳（6位数字yymmdd） +
		 * 序列号（4位数字） + 校验码(1位字符，ISO 7064 MOD 37,36算法）youjg change
		 */
		if (StringUtils.isNotBlank(sequenceNum)) {
			customerNum = key + (DateTools.dateToString(new Date(), "yy")) + ""
					+ (DateTools.dateToString(new Date(), "MM")) + "" + DateTools.dateToString(new Date(), "dd") + ""
					+ this.getSequenceNumberOfString(customerNumber, 4)+ checkString;
		}
		if (StringUtils.isBlank(customerNum)) {
			//throw new BizException("客户编号生成异常：请联系系统管理员!");
			return null;
		}
		return customerNum;
	}

	/**
	 * 生成角色编号
	 * 
	 * @since
	 * 
	 * <pre>
	 * </pre>
	 */
	public String generateRoleNum(String finalStr)  {
		if (StringUtils.isNotBlank(finalStr)) {

			SimpleDateFormat formater = new SimpleDateFormat("yyyyMMdd");
			String strDateTime = formater.format(new Date());// 得到日期时间
			Long documentSerial = this.generator.generateSequenceNumber(finalStr + strDateTime);
			return finalStr + strDateTime + this.getSequenceNumberOfString(documentSerial, 3);
		} else {
			return null;
			//throw new BizException("角色编号为空，生成编号异常！");
		}
	}

	/**
	 * 生成部门编号
	 * 
	 * @since
	 * 
	 * <pre>
	 * </pre>
	 */
	public String generateDepartmentNum(String finalStr)  {
		if (StringUtils.isNotBlank(finalStr)) {

			SimpleDateFormat formater = new SimpleDateFormat("yyyyMMdd");
			String strDateTime = formater.format(new Date());// 得到日期时间
			Long documentSerial = this.generator.generateSequenceNumber(finalStr + strDateTime);
			return finalStr + strDateTime + this.getSequenceNumberOfString(documentSerial, 3);

		} else {
			return null;
			//throw new BizException("角色编号为空，生成编号异常！");
		}
	}

	/**
	 * 获取交易编号
	 * 
	 * @param busstype
	 * @param bchid
	 * @return
	 * @
	 */
	public synchronized String getTxRefNo(String busstype, String bchid, int customerType)  {
		if (busstype == null || "".equals(busstype) || bchid == null || "".equals(bchid)){
			return "";
		}
		bchid = this.getOrganizationNo(bchid);
		// String txrefno = busstype +
		// DateTools.getCurrentDate("yyyyMMddHHmmssSSS") + bchid;
		String strDateTime = DateTools.getCurrentDate("yyMMdd");// 得到日期时间
		Long documentSerial = this.generator.generateSequenceNumber(strDateTime + busstype + bchid);
		String txrefno = "D" + this.translateCustomerType(String.valueOf(customerType)) + bchid
				+ DateTools.getCurrentDate("yyMMdd") + this.getSequenceNumberOfString(documentSerial, 4);
		return txrefno;
	}

	/**
	 * 生成业务编号
	 * 
	 * @param bizType
	 *            业务类型编号 如："KHED"
	 * @param organizationNo
	 *            用户机构Id
	 * @return String 业务事件编号
	 * @
	 * @since 业务事件编号的生成规则是：业务事件类型+客户类型+机构+年份+序号， 一共17位长，其中： <br>
	 */
	public String generateAppNumber(String bizType, String customerType, String organizationNo)  {
		organizationNo = this.getOrganizationNo(organizationNo);
		String strYear = new SimpleDateFormat("yyMMdd").format(new Date());// 得到年份
		String key = bizType + organizationNo + strYear;// 得到产生序号的参数
		Long sequenceNumber = this.generator.generateSequenceNumber(key);// 得到序号(Long)
		return bizType + customerType + organizationNo + strYear + getSequenceNumberOfString(sequenceNumber, 4);
	}

	/**
	 * 获取机构后4位 目前处理:不够4位,左补0
	 * 
	 * @param organizationNo
	 * @return
	 */
	private String getOrganizationNo(String organizationNo) {
		
		if (StringUtils.isBlank(organizationNo) || "null".equals(organizationNo)) {
			organizationNo = "";
		} else {
			organizationNo = organizationNo.trim();
		}

		//modify by wangyawei 20150615 start 修改机构编号规则与小贷系统一致
		List<Object> departNumberLists = dynamicQuery.nativeQuery(Object.class,"select departmentnumber from ec_org_departmentdetails where id = ?1", organizationNo);
		if(CollectionUtils.isNotEmpty(departNumberLists)){
			organizationNo=CommonHelper.toStr(departNumberLists.get(0));
		}else{
			organizationNo=StringUtils.EMPTY;
		}
		//modify by wangyawei 20150615 end 
		/*List<Map<String, Object>> mapList = this.commonDBService.findList("select departmentnumber from ec_org_departmentdetails where id = '" + organizationNo + "'");

		if (mapList == null || mapList.size() == 0) {
			organizationNo = "";
		} else {
			if (mapList.get(0) != null && !"null".equals(mapList.get(0).get("DEPARTMENTNUMBER").toString())) {
				organizationNo = mapList.get(0).get("DEPARTMENTNUMBER").toString();
			} else {
				organizationNo = "";
			}
		}*/
       
		if (organizationNo.length() > 5) {
			return organizationNo.substring(organizationNo.length() - 4, organizationNo.length());
		} else {
			return StringUtils.leftPad(organizationNo, 4, "0");
		}
	}

	/**
     * 生成业务对象编号
     *
     * @param organizationNo  机构id
     * @param businessType 业务类型： 个人1 企业0
     * @param contractType 合同类型
     * @return count 计数
     * @
     */
    public synchronized String generateContractNumber(String organizationNo,String businessType,String contractType,Integer count) {
		organizationNo = this.getOrganizationNo(organizationNo);
		String strYear = new SimpleDateFormat("yy").format(new Date());// 得到年份
		String key = organizationNo + businessType + strYear;// 得到产生序号的参数
		Long sequenceNumber = this.generator.generateSequenceNumber(key);// 得到序号(Long)
		return organizationNo +businessType+ strYear + getSequenceNumberOfString(sequenceNumber, 4)+"【"+contractType+"】-"+StringUtils.leftPad(count.toString(),2, "0");
    }
	/**
     * 生成业务对象编号
     *
     * @param organizationNo  机构id
     * @param businessType 业务类型： 个人1 企业0
     * @param contractType 合同类型
     * @return count 计数
     * @
     */
    public String generateSubContractNumber(String contractNumber,String contractType,Integer count) {
		return StringUtils.substring(contractNumber, 0, 11)+"【"+contractType+"】-"+StringUtils.leftPad(count.toString(),2, "0");
    }
    public static String translateCustomerType(String customerType){
		if(customerType == null || "".equals(customerType)) return null;
		if("1".equals(customerType)){
			return GlobalConstants.CUSTOMER_ENTERPRISE_KEY;
        }else if("2".equals(customerType)){
        	return GlobalConstants.CUSTOMER_PERSONAL_KEY;
        }else if("3".equals(customerType)){
        	return GlobalConstants.CUSTOMER_THREE_ORG_KEY;
        }
        else{
        	return null;
        }
	}

	@Override
	public String generateGuarantyNumber(String numPrefix, String orgNo) {
		orgNo = this.getOrganizationNo(orgNo);
		String strYear = new SimpleDateFormat("yyMMdd").format(new Date());// 得到年份
		String key = numPrefix + orgNo + strYear;// 得到产生序号的参数
		Long sequenceNumber = this.generator.generateSequenceNumber(key);// 得到序号(Long)
		return key + getSequenceNumberOfString(sequenceNumber, 4);
	}

	@Override
	public String generateUnGuId() {
		//自动生成联保体协议编号
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmSS");
		String format = df.format(new Date());
		return "LB"+format;
	}

	
}
