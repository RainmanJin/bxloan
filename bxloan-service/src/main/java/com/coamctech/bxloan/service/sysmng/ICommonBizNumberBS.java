package com.coamctech.bxloan.service.sysmng;


import java.util.Date;


/**
 * 编号生成公共服务类
 * @author 9th
 * generateMeetingSerialNumber 生成会次号
 * generateDocumentNum 生成文档编号
 * generateDocumentEventNum 生成文档事件编号
 */
public interface ICommonBizNumberBS{
	
	/**
     * 生成业务对象编号
     *
     * @param bizType   业务类型编号 如："KHED"
     * @param organizationNo 用户机构Id
     * @return String 业务事件编号
     * @
     * @since 业务事件编号的生成规则是：业务事件类型+机构+年份+序号， 一共30位长，其中： <br>
     *        业务事件类型参见编号规则文档中制定的业务事件编号 <br>
     *        机构号采用全国统一的机构编码，9位长度 <br>
     *        年份4位，为YYYY方式 <br>
     *        序号为5位, 与机构相关，每年年初重置 <br>
     *        <p/>
     *        业务事件编号的生成步骤如下：<br>
     *        年份＝当前的年份 <br>
     *        序号=IGenerator.getSequenceNumber("BizEventType+organizationNo+年份") <br>
     *        将(BizEventType,organizationNo,年份,序号) 赋值到业务事件编号<br>
     */
    public String generateBizEventNumber(String bizType,String organizationNo) ;
	
	/**
	 * 生成会次<br>
	 * 会次由9位组成：年份(4位)+类型(1位)+【补齐0】序号(4位)<br>
	 * @param orgCode 建行机构代码
	 * @param type 类型代码。分6种情况，分别为：授信（包括额度、业务）会议审批——1、授信（包括额度、业务）会签审批——2、评级会议审定——3、评级会签审定——4、保全会议审批——5、保全会签审批——6
	 * @return 会次
	 * @
	 * @since
	 * 年份＝当前的年份<br>
	 * 序号=IGenerator.getSequenceNumber(orgCode+年份+type)<br>
	 * 序号前补齐0<br>
	 * 
	 * 返回值=年份+type+序号<br>
	 */
	public String generateMeetingSerialNumber(String orgCode,String type) ;
	/**
	 * 生成会次<br>
	 * 会次由9位组成：年份(4位)+类型(1位)+机构编号后四位+【补齐0】序号(4位)<br>
	 * 返回值=年份+type+序号<br>
	 */
	public String generateMeetingNumber(String typ,String organizationNo) ;
	
	/**
	 * 生成文档编号
	 * 文档编号生成规则：文档编号25位=客户编号+文档类型+年月日+【补齐0】+序号
	 * @param customerNum
	 * @param documentType 文档类型，01-客户、02-业务、03-合同、04-回访
	 * @return String 文档编号
	 * @since 
	 * <pre>
	 * 
	 * </pre>
	 */
	public String generateDocumentNum(String customerNum, String documentType) ;
	/**
	 * 生成用户编号
	 * @since 
	 * <pre>
	 * z赵乾征
	 * </pre>
	 */
	public String generateUserNum(String orgId) ;
	
	/**
	 * 生成角色编号
	 * @since 
	 * <pre>
	 * 
	 * </pre>
	 */
	public String generateRoleNum(String finalStr) ;
	/**
	 * 生成机构或部门编号
	 * @since 
	 * <pre>
	 * 
	 * </pre>
	 */
	public String generateDepartmentNum(String finalStr) ;
	/**
	 * 生成文件名称编号
	 * 文件名称编号生成规则：文件名称编号25位=客户编号+年月日+【补齐0】+序号
	 * 
	 * @author zhud
	 * @date 2011-11-22
	 * @param customerNum
	 * @return
	 * @
	 * @since 
	 * <pre>
	 * 
	 * </pre>
	 */
	public String generateDocumentNameNum(String customerNum) ;
	/**
	 * 生成文件名称编号
	 * 文件名称编号生成规则：文件名称编号25位=客户编号+年月日+【补齐0】+序号
	 * 
	 * @author williamtwo
	 * @date 2012-05-28
	 * @param customerNum
	 * @param curDate 指定的日期
	 * @return
	 * @
	 * @since 
	 * <pre>
	 * 
	 * </pre>
	 */
	public String generateDocumentNameNum(String customerNum,Date curDate, String reportType) ;
	
	 /**
	 * 生成文档事件编号
	 * @author 郑思静
	 * @date 2011-11-16
	 * @param DocumentEventNum 事件编号SJ
	 * @return String 文档事件编号
	 * @
	 * @since
	 * 文档事件编号生成规则：文档编号20位=SJ+年月日+序号（2+8+6+4=20位）
	 * 将(DocumentEventNum,年份,序号) 赋值到业务事件编号<br>
	 */
	public String generateDocumentEventAndTempNum(String documentNum,int digitNum) ;
	
	/**
     * 生成客户编号
     *
     *  1、输入代码不足18位时，生成的客户编号的校验位前加'＃'；如果大于或等于18位，则不用加'＃'。
     *  2、根据证件号码生成校验位时，忽略所有的非字母和非数字，小写字母转换成大写字母计算。
     *
     * @param customerType   -   参与人类型: 1-企业客户，2-个人客户（对应codetype='CustomerType'）
     * @param certificateType   -   证件类型：字母（车贷用A，企贷用C，个贷用P）
     * @param certificateNumber - 证件编号: 机构号去除'-' (   orgNum.replaceAll("-", "")   )
     * @return 客户编号
     * @
     * @since 客户编号组成：
     *        T-证件类型代码
     *        N-证件号码
     *        S-不定长NUMBER（从1开始递增）
     *        序号=IGenerator.getSequenceNumber(participantType+certificateType+certificateNumber)，序号前补齐0 <br>
     *        C-校验字符（1位）：采用ISO 7064-1983 SJ/Z 9092 MOD37，36校验系统。算法待定。
     *        其中N+S一共19位, 序号前补齐0。例如P12345678900000000013C
     */
    public String generateCustomerNumber(String customerType, String certificateType,
                                         String certificateNumber) ;
    
    
    
    /**
	 * 获取账务交易编号（单据编号）
	 * @param busstype
	 * @param bchid
	 * @return
	 * @
	 */
	public String getTxRefNo(String busstype, String bchid , int customerType) ;
	
	 /**
     * 生成业务编号
     *
     * @param bizType   业务类型编号 如："KHED"
     * @param organizationNo 用户机构Id
     * @return String 业务事件编号
     * @
     * @since 业务事件编号的生成规则是：业务事件类型+客户类型+机构+年份+序号， 一共17位长
     */
    public String generateAppNumber(String bizType,String customerType,String organizationNo) ;
    
	/**
     * 生成业务对象编号
     *
     * @param organizationNo  机构id
     * @param businessType 业务类型： 个人1 企业0
     * @param contractType 合同类型
     * @return count 计数
     * @
     */
    public String generateContractNumber(String organizationNo,String businessType,String contractType,Integer count) ;
	/**
     * 生成业务对象编号
     *
     * @param organizationNo  机构id
     * @param businessType 业务类型： 个人1 企业0
     * @param contractType 合同类型
     * @return count 计数
     * @
     */
    public String generateSubContractNumber(String contractNumber,String contractType,Integer count) ;
    /**
     * 生成抵质押编号
     *
     * @param numPrefix  编号前缀
     * @param orgId  机构id
     * @return numPrefix + 0000 + yyMMdd +4位递增序号
     * @
     */
    public String generateGuarantyNumber(String numPrefix, String orgId) ;
    /**
     * 生成联保体协议编号
     *
     * @return unGuId LB+当前时间 = LByyyyMMddHHmmSS
     * @
     */
	public String generateUnGuId();

}