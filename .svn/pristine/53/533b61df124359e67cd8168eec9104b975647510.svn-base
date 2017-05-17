package com.coamctech.bxloan.commons;



import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

/**
 * 定义全局常量
 * 
 * @author wh
 * 
 */
public class GlobalConstants {

	//TODO 数据Schema
	public static final String WD_SCHEMA = "DFZC_BXMC";
	public static final String FX_SCHEMA = "DFBX_FX";
	public static final String WF_SCHEMA = "DFBX_WF";
	
	//TODO 模块
	
	public static final String DASHBOARD = "dashboard";
	public static final String LOGIN = "login";
	public static final String USER_MNG = "userMng"; // 测试用
	public static final String LOAN_CALCULATE = "calcu";// 贷款试算

	public static final String RISKMNG = "riskmng"; // 风险管理
	public static final String ANTIFRAUD = "antiFraud";// 反欺诈

	public static final String WORKFLOW_MONITOR = "wfmonitor";// 流程监控

	/** 上传文档的配置 */
	public static final String DOC_TARGET = "doc.properties";
	// 链接风险系统接口超时时间
	public static final int CONN_RISK_TIME = 50000;
	/**
	 * 客户管理
	 */
	public static final String INDIVIDUAL = "singleCustomer";// 单一客户管理
	public static final String INID_MNG = "customer/uniqueCustomer/main"; // 单一客户列表的页面
	public static final String FORM_MNG = "customer/uniqueCustomer/add_edit_detail";// 单一客户表单的页面-受薪
	public static final String MANA_MNG = "customer/uniqueCustomer/mana_add_edit_detail";// 单一客户表单的页面-经营
	public static final String UNITE_MNG = "customer/unitecustomer/main";//联保体客户管理页面
	public static final String UNITE_DETAIL_MNG = "customer/unitecustomer/filldetail";//联保体客户详细页面
	public static final String CORP_CUSMNG = "corpcustomer";// 企业客户管理
	public static final String UNITE_CUSMNG = "unitecustomer";//联保体客户管理
	/** 客户经理管理 **/
	public static final String CUS_MANAGER = "cusmanager";
	/**
	 * 贷前管理
	 * 
	 * */
	public static final String BUSINESS_APPLICATION = "bizapply"; // 业务申请
	public static final String BUSINESS_APPLICATION_WD = "businessapplicationwd"; // 微贷产品业务申请
	//add by wangyawei 20150707 start 增加授信业务相关路径
	/**易贷根路径*/
	public static final String EAST_LOAN_PATH = "easyloan"; 	
	/**微贷根路径*/
	public static final String MICRO_LOAN_PATH = "microloan"; 
	/**授信业务申请主路径*/
	public static final String INIT_BIZ_CREDIT_APPLICATION = "bizCreditApplication"; 	
	/**授信业务申请-微贷主路径*/
	public static final String BIZ_CREDIT_APPLICATION_WL = INIT_BIZ_CREDIT_APPLICATION + File.separator + MICRO_LOAN_PATH;
	/**授信合同管理-主路径*/ 
	public static final String CREDIT_CONTRACT_MNG = "creditContractMng";
	/**授信合同管理-制定授信电子合同主路径*/
	public static final String MAKE_CREDIT_CONTRACT = CREDIT_CONTRACT_MNG + File.separator + "makeCreditContract";	
	/**授信合同管理-制定授信电子合同-微贷主路径*/
	public static final String MAKE_CREDIT_CONTRACT_WL = MAKE_CREDIT_CONTRACT + File.separator + MICRO_LOAN_PATH;	
	/**授信合同管理-担保方式变更主路径*/
	public static final String CHANGE_GUARANTEE_MODE = "changeGuaranteeMode";
	/**授信合同管理-查看授信合同信息主路径*/
	public static final String VIEW_CREDIT_BIZ = "viewCreditBiz"; 
	/**授信合同管理-发起授信项下借款申请主路径*/
	public static final String UNDER_CREDIT_CONTRACT_MNG = "underCreditContractMng";
	/**授信项下借款申请-签订电子合同主路径*/
	public static final String SIGN_CREDIT_LOAN_CONTRACT = "signCreditLoanContract";
	/** 授信合同审批流程主路径 */
	public static final String CREDIT_CONTRACT_APPROVAL = "creditContractApproval";
	/** 授信合同审批流程-微贷主路径 */
	public static final String CREDIT_CONTRACT_APPROVAL_WL = CREDIT_CONTRACT_APPROVAL + File.separator + MICRO_LOAN_PATH;
	/** 授信借款审批流程主路径 */
	public static final String UNDER_CREDIT_LOAN_APPROVAL = "underCreditLoanApproval";
	//add by wangyawei 20150707 end
	public static final String QUERYBUSINESS = "querybusiness"; // 业务查询
	public static final String CONTRACT = "contractMng";// 合同管理
	public static final String INIT_CONTRACT = "contract/contract/main"; // 合同管理的页面
	public static final String FORM_CONTRACT_EL = "contract/contract/easyloan/edit_detail"; // 合同表单的页面_易贷
	public static final String FORM_CONTRACT_ML = "contract/contract/microloan/edit_detail"; // 合同表单的页面_易贷
	public static final String LOANGRANT = "contractList";// 贷款发放
	public static final String INIT_LOANGRANT = "contract/loangrant/main"; // 贷款发放页面
	public static final String EDIT_LOANGRANT = "contract/loangrant/edit"; // 调整还款计划
	public static final String CONTRACTLIST = "contract/records/main";// 贷款发放-放款记录页面
	public static final String Collateral = "collateral";// 抵质押物管理
	public static final String ADDRECORD = "contract/records/add";// 放款记录-新增放款

	// TODO 账务处理
	/** * 账务处理 */
	public static final String ACCOUNTING_MNG = "accountingMng";
	/**账务冲正 */
	public static final String ACCOUNTING_FLUSHES = "accountingFlushes";
	/** 统计查询*/
	public static final String CONTRACTSTANDINGBOOK = "contractStandingBook";// 合同台账
	public static final String COUNT_REGISTRATION = "countRegistration";	// 统计签到

	/**系统管理*/
	public static final String SYS_MNG = "sysmng"; // 系统管理
	public static final String PRODUCT_PRICE = "productPrice"; // 产品定价设置
	public static final String PERSON_MNG = "personMng"; // 用户管理控制器
	public static final String PRODUCT_MNG = "productMng"; // 产品管理
	/**一级系统管理员*/
	public static final String ONE_LEVEL_SYS_ADMIN = "R00011";
	/**二级系统管理员*/
	public static final String TWO_LEVEL_SYS_ADMIN = "R00012";
	public static final String EXPENSE_RATE_PRE = "0.008";
	public static final String EXPENSE_RATE_BAK = "0.2";
	public static final String REPAYPLAN = "repayPlan";// 还款计划控制器
	/** 用户基本信息修改 */
	public static final String USER_INFO = "userinfo";
	/** 易贷审批流程 */
	public static final String APPROVAL = "approval";
	/** 微贷审批流程 */
	public static final String WDAPPROVAL = "wdapproval"; 
	public static final String WDAPPROVALREPAYPLAN = "wdapprovalRepayPlan";
	/** 客户编号生成规则 **/
	public static final String CUSTOMER_ENTERPRISE_KEY = "0";// 企贷
	public static final String CUSTOMER_PERSONAL_KEY = "1";// 个贷
	public static final String CUSTOMER_THREE_ORG_KEY = "2";// 三方机构

	/** 月数 */
	public static final BigDecimal MCNT = new BigDecimal(12);

	/** 天数 */
	public static final BigDecimal DCNT = new BigDecimal(360);

	/** 时间格式 yyyy-MM-dd */
	public static String DATE_FORMAT = "yyyy-MM-dd";

	public static final int MAXLOGERRTIME = 3;// 允许登录输入密码错误次数

	/** 合同状态 */
	// 047002 发送未入账
	public static String BILLSTS_FSWRZ = "047002";
	// 047003 已入账
	public static String BILLSTS_YRZ = "047003";
	// 047004 已退单
	public static String BILLSTS_YTD = "047004";
	// 047005 冲销未入账
	public static String BILLSTS_CXWRZ = "047005";
	// 047006 冲销已入账
	public static String BILLSTS_CXYRZ = "047006";
	/** 配置还款方式,能多次放款 */
	public static final String APPROVE_AGAIN_PAY = "1,2";

	/**
	 * 邦易贷流程
	 */
	public static final String EL_WF_TYPE_30 = "1003";
	//public static final String EL_WF_TYPE = "30";
	public static final String EL_WF_TASK_ID_10 = "100310";
	public static final String EL_WF_TASK_ID_11 = "100311";
	public static final String EL_WF_TASK_ID_12 = "100312";
	public static final String EL_WF_TASK_ID_13 = "100313";
	public static final String EL_WF_TASK_ID_14 = "100314";
	public static final String EL_WF_TASK_ID_15 = "100315";
	public static final String EL_WF_TASK_ID_16 = "100316";

	public static final int WD_WF_TYPE_40_INT;
	public static final int EL_WF_TYPE_30_INT;
	/**
	 * 邦微贷流程
	 */
	public static final String WD_WF_TYPE_40 = "1004";
	public static final String WD_WF_TASK_ID_10 = "100410";
	public static final String WD_WF_TASK_ID_11 = "100411";
	public static final String WD_WF_TASK_ID_12 = "100412";
	public static final String WD_WF_TASK_ID_13 = "100413";
	public static final String WD_WF_TASK_ID_14 = "100414";
	public static final String WD_WF_TASK_ID_15 = "100415";
	public static final String WD_WF_TASK_ID_16 = "100416";
	public static final String WD_WF_TASK_ID_17 = "100417";
	public static final String WD_WF_TASK_ID_18 = "100418";
	public static final String WD_WF_TASK_ID_19 = "100419";
	public static final String WD_WF_TASK_ID_20 = "100420";
	
	//product_wf_config  产品配置使用
	public static final String WD_WF_ROLE_CONF_19 = "R20141208019";
	public static final String WD_WF_ROLE_CONF_02 = "R20120515002";
	public static final String WD_WF_ROLE_CONF_16 = "R20141208016";
	public static final String WD_WF_ROLE_CONF_15 = "R20141208015";
	public static final Long WD_WF_ORG_ID_01 = 10001l;

	public static final String LOAN_TERM_MODE = "1";
	/**
	 * 导出Excel报表
	 */
	public static final String FIELDS = "fields";

	// ///////企业客户关联人员类型
	/*** 股东 */
	public static final String CORPCUS_RELA_PERSON_TYPE_STOCK = "1";
	/*** 高管 */
	public static final String CORPCUS_RELA_PERSON_TYPE_MANAGER = "2";
	/*** 实际控制人 */
	public static final String CORPCUS_RELA_PERSON_TYPE_CONTROLLER = "3";
	/*** 法定代表人 */
	public static final String CORPCUS_RELA_PERSON_TYPE_LEGAL_REPRESENTATIVE = "4";

	/** 上传文件路径 */
	public static final String UPLOAD_DESTINATION;
	/** 反欺诈接口开关 */
	public static final boolean RISKMNG_FLAG;
	/** 可设置产品定价的产品代码 */
	public static final String ALLOWED_SET_PRICE_PRODUCTS;
	/** 管理员角色父级产品 */
	public static final String ADMIN_PRODUCT;

	/** 系统通知 **/
	public static final Long MSG_TYPE_SYSTEM_MSG = 1L;
	/** 合同到期提醒 **/
	public static final Long MSG_TYPE_CONTRACT_TIME_UP = 5L;
	/** 审批通过通知 **/
	public static final Long MSG_TYPE_APPROVAL_FINISH = 23L;// 审批通过通知
	/** 审批否决通知 撤销 **/
	public static final Long MSG_TYPE_CANCEL = 6L;
	/** 审批退回通知 **/
	public static final Long MSG_TYPE_SEND_BACK = 24L;
	/** 合同生效通知 **/
	public static final Long MSG_TYPE_CONTRACT = 7L;// 合同生效通知
	/** 还款到期通知 **/
	public static final Long MSG_TYPE_REPAY_TIME_UP = 21L;// 还款到期通知

	/** 角色编码 用于系统操作员新增功能 人员列表查询 */
	public static final String SYSOP_ROLE_NUM;

	/** 年月日转换常量 */
	public static final Integer YEAR_TO_DAY = 360;
	public static final Integer MONTH_TO_DAY = 30;

	/**通知内容*/
	public static final String COMMON_MSG;
	public static final String AGREED_MSG;
	public static final String REFUSED_MSG;
	public static final String RETREATED_MSG;
	
	//TODO 
	
	/**
	 * 邦农贷产品代码
	 */
	public static final String FARMER_LOAN_PRODUCT_MARK;
	public static final boolean HTTP_REFERER;
	/**
	 * 团结贷
	 * add by wangxy 20150517
	 */
	public static final String UNITY_LOAN_PRODUCT_MARK;
	/**
	 * 默认登录方式
	 */
	public static final LoginMode DEFAULT_LOGIN_MODE;
	
	static {
		Properties prop = new Properties();
		try {
			prop.load(Thread.currentThread().getContextClassLoader()
					.getResourceAsStream("config.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		UPLOAD_DESTINATION = prop.getProperty("uploadDestination");
		FARMER_LOAN_PRODUCT_MARK=prop.getProperty("FarmerLoanProductMark",StringUtils.EMPTY);
		//团结贷
		UNITY_LOAN_PRODUCT_MARK=prop.getProperty("UnityLoanProductMark",StringUtils.EMPTY);
		
		WD_WF_TYPE_40_INT = Integer.valueOf(WD_WF_TYPE_40);
		EL_WF_TYPE_30_INT = Integer.valueOf(EL_WF_TYPE_30);

		SYSOP_ROLE_NUM = prop.getProperty("sysOperatorRoleNum");

		ALLOWED_SET_PRICE_PRODUCTS = prop.getProperty("allowedSetPriceProducts");
		
		ADMIN_PRODUCT = prop.getProperty("adminProduct");
		//风险 1：启用，0：关闭，默认0
		final String riskmngflag = prop.getProperty("riskmngflag", "0");
		RISKMNG_FLAG=StringUtils.equals("1", riskmngflag);
		//
		String httpReferer = prop.getProperty("httpReferer","1");
		//登录方式选定:默认门户登录
		LoginMode loginMode=LoginMode.PORTAL_SSO;
		try {
			loginMode=LoginMode.valueOf(prop.getProperty("loginMode", "PORTAL_SSO"));
		} catch (NumberFormatException e) {
			loginMode=LoginMode.PORTAL_SSO;
		}
		//业务系统登录必须关闭验证请求来源
		switch (loginMode) {
		case PORTAL_SSO:
			httpReferer="1";
			break;
		case BIZ_SSO:
			httpReferer="0";
			break;
		default:
			break;
		}
		HTTP_REFERER=StringUtils.equals("1", httpReferer);
		DEFAULT_LOGIN_MODE=loginMode;
		
	}
	 /**
	 * @author AcoreHeng登录模式
	 *
	 */
	public enum LoginMode {
	        PORTAL_SSO, BIZ_SSO;
	    }
	
	static {
		Properties prop = new Properties();
		try {
			prop.load(Thread.currentThread().getContextClassLoader()
					.getResourceAsStream("msg.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		COMMON_MSG = prop.getProperty("commonMsg");
		AGREED_MSG = prop.getProperty("agreedMsg");
		REFUSED_MSG = prop.getProperty("refusedMsg");
		RETREATED_MSG = prop.getProperty("retreatedMsg");
	}
	
public static enum DocStageCode{
		/**空*/
		NULL_DOC("空","000"),
		/**经营类*/
		JY_DOC("经营类","001"),
		/**企业类*/
		QY_DOC("企业类","002"),
		/**受薪类*/
		SX_DOC("受薪类","003"),
		/**农户类*/
		NH_DOC("农户类","004"),
		
		/**微贷业务申请*/
		YW_WD_DOC("微贷业务申请","005"),
		/**易贷业务申请*/
		YW_YD_DOC("易贷业务申请","006"),
		/**网核电核*/
		WHDH_YD_DOC("网核电核","007"),
		/**贷款审查*/
		DKSC_WD_DOC("贷款审查","008"),
		/**初审*/
		CS_DOC("初审","009"),
		/**易贷落实放款*/
		LSFK_YD_DOC("易贷落实放款","010"),
		/**微贷落实放款*/
		LSFK_WD_DOC("微贷落实放款","011"),
		/**签订合同*/
		HT_DOC("签订合同","012"),
		/**审批意见表*/
		YJB_DOC("审批意见表","013"),
		/**联保体*/
		LBT_DOC("联保体","014"),
		/**抵质押*/
		DZY_DOC("抵质押","015"),
		/** 雇佣类型为学生*/
		STUDENT_DOC("学生","016");
		
		private final String codeName;
		private final String codeId;
		
		private DocStageCode(String codeName,String codeId){
			this.codeId = codeId;
			this.codeName = codeName;
		}
		public String getCodeName() {
			return codeName;
		}
		public String getCodeId() {
			return codeId;
		}
		
		public static DocStageCode getById(String codeId){
			for (DocStageCode dsCode : DocStageCode.values()) {
				if(dsCode.getCodeId().equals(codeId)){
					return dsCode;
				}
			}
			throw new IllegalArgumentException("没有id为" + codeId + "的步骤号码");
		}
	}
	
	//add by wangyawei 20150619 start 增加Excel、Word导入，导出配置信息
	/**存储导出、导出路径资源文件名称*/
	/**资源文件存放位置*/
	public static final String CONFIG_PROPERTIES_PATH = "config/";
	/**导出Excel模板存放位置*/
	public static final String EXCEL_TEMPLATE_DOWNLOAD_PATH = "template/excel/download/";
	/**导入Excel模板存放位置*/
	public static final String EXCEL_TEMPLATE_UPLOAD_PATH = "template/excel/upload/";
	/**导出Word模板存放位置*/
	public static final String WORD_TEMPLATE_DOWNLOAD_PATH = "template/word/download/";
	/**导入Word模板存放位置*/
	public static final String WORD_TEMPLATE_UPLOAD_PATH = "template/word/upload/";
	//add by wangyawei 20150619 end
	/**浮点型数值保留位数*/
	public static final int BIGDECIMAL_SCALE = 2;
	/**浮点型数值不保留位数标识*/
	public static final int BIGDECIMAL_NOT_SCALE = -1;
}
