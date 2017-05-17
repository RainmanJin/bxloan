package com.coamctech.bxloan.service.pettyloan.util;

import java.math.BigDecimal;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.coamctech.bxloan.service.pettyloan.enums.EnumUtils;
import com.coamctech.bxloan.service.pettyloan.enums.IntEnum;
import com.coamctech.bxloan.service.pettyloan.enums.StrEnum;

/**
 *	@author WallenHeng
 *	@since 2014-09-01 11:46:04
 */
public final class LoanConstants {
	
	/** 月数 */
	public static final BigDecimal MCNT = new BigDecimal(12);
	/** 天数 */
	public static final BigDecimal DCNT = new BigDecimal(360);
	public static final  String KEY_S0="S0";
	public static final  String KEY_S1="S1";
	public static final  String KEY_S2="S2";
	public static final  String KEY_S3="S3";
	public static final  String KEY_S4="S4";
	public static final  String KEY_S5="S5";
	public static final  String PAY_LOAN_STATUS="PayLoanStatus";
	public static final  String PLAN_STATUS="PlanStatus";
	
	/** 还款计划表编号规则 */
	public static final String REPAYING_RULES = "PP";
	/** 合同状态 code_type = ContractStatusCode */
	public static final String CONTRACTSTATUSCODE = "ContractStatusCode";
	/** 还款周期单位 code表 code_type = TermUnitCd */
	public static final String TERMUNITCD = "TermUnitCd";
	
	
	/** 已跑批 */
	public static String BATCH_ONE = "1";
	/** 未跑批 */
	public static String BATCH_TWO = "2";
	/** 逾期批 */
	public static String BATCH_TYPE_ONE = "1";
	/** 系统批 */
	public static String BATCH_TYPE_TWO = "2";
	/** 利息结记批 */
	public static String BATCH_TYPE_THREE = "3";
	/** 逾期90批 */
	public static String BATCH_TYPE_FOUR = "4";
	/** 五级分类批 */
	public static String BATCH_TYPE_FIVE = "5";
	
	
	
	/** 按经办机构计提 */
	public static String OBJECTDIMENSIONTYPE_ORG = "1";
	
	/** 按单笔合同计提 */
	public static String OBJECTDIMENSIONTYPE_CONTRACT = "2";
	
	/** 逾期90内标识 */
	public static String REPAYING_FLAG_OVERDUE = "1";
	
	/** 逾期90外标识 */
	public static String REPAYING_FLAG_OVERTHEN90 = "2";
	
	
	/** 0无需财务反馈业务日期*/
	public static Integer IFFEED_NO = 0;
	/** 1 需要财务反馈业务日期*/
	public static Integer IFFEED_YES = 1;
	
	//1 科目编码
	public static Integer RULETYP_ACCOUNT = 1;
	//2 金额类型
	public static Integer RULETYP_AMT = 2;
	
	
	//2 借贷别： 借
	public static Integer DCMARK_D = 1;
	//1 借贷别： 贷
	public static Integer DCMARK_C = 2;
	
	public static void main(String[] args) {
		System.out.println(RecordedVchFlag.valueOf("2"));
	}
	//TODO 枚举
	/**
	 *	还款时的三种情况
	 *			
	 */
	public enum RepayType{
		NORMAL,//正常还款
		ORVERDUE,//逾期还款
		NORMAL_TO_ORVERDUE//正常转逾期
	}
	/**
	 * 贷款状态
	 */
	public enum LoanStatus{
		/** * 放款状态:未入账 */
		PAY_LOAN_STATUS_S1(PAY_LOAN_STATUS,KEY_S1),
		/** * 放款状态:已入账 */
		PAY_LOAN_STATUS_S2(PAY_LOAN_STATUS,KEY_S2),
		/** * 放款状态:已退单 */
		PAY_LOAN_STATUS_S3(PAY_LOAN_STATUS,KEY_S3),
		/** * 放款状态:冲销未入账 */
		PAY_LOAN_STATUS_S4(PAY_LOAN_STATUS,KEY_S4),
		/** * 放款状态:冲销已入账 */
		PAY_LOAN_STATUS_S5(PAY_LOAN_STATUS,KEY_S5),
		/** * 计划状态:未还 */
		PLAN_STATUS_S0(PLAN_STATUS,KEY_S0),
		/** * 计划状态:已还 */
		PLAN_STATUS_S1(PLAN_STATUS,KEY_S1),
		/** * 计划状态:逾期未还 */
		PLAN_STATUS_S2(PLAN_STATUS,KEY_S2);
		
		private String codeType;
		private String key;
		private LoanStatus(String codeType, String key) {
			this.codeType = codeType;
			this.key = key;
		}
		public String getCodeType() {
			return codeType;
		}
		public String getKey() {
			return key;
		}
		private static Map<String, EnumSet<LoanStatus>>  map=null;
		static{
			map=new HashMap<String, EnumSet<LoanStatus>>();
			for (LoanStatus ls : values()) {
				if(map.get(ls.getCodeType())==null){
					map.put(ls.getCodeType(), EnumSet.of(ls));
				}else{
					map.get(ls.getCodeType()).add(ls);
				}
			}
		}
		public static EnumSet<LoanStatus> getEnumSet(String codeType){
			return map.get(codeType);
		}
		
	}
	/**
	 *	事件
	 */
	public enum LoanEvent implements StrEnum{
		/** *事件:放款 */
		PAY_LOAN("1"),
		/** *事件:多次放款*/
		AGAIN_PAY_LOAN("2"),
		/** *事件:还款 */
		REPAYED("3"),
		/** *事件:利息结记 */
		INTEREST_RECORD("4"),
		/** *事件:展期  */
		EXTENSION("5"),
		/** *事件:冲销 */
		REVERSE("6"),
		/** *事件:转出 */
		ATTRON("7"),
		/** *事件:系统改变服务 */
		SYSTEM_CHANGE("sys");
		
		private String val;
		
		private LoanEvent(String val) {
			this.val = val;
		}
		@Override
		public String toStr() {
			return val;
		}
		public boolean eq(String val){
			return this.val.equals(val);
		}
		private static Map<String, LoanEvent> instanceMap = EnumUtils
				.mapStrEnum(LoanEvent.class);
		public static LoanEvent valOf(String str) {
			return instanceMap.get(str);
		}
	}
	/**
	 *	账务交易形态(eventType)
	 */
	public enum AccountEventType implements IntEnum{
		/** 账务交易形态 1 = 放款 */
		LOAN(1),
		/** 账务交易形态2 = 逾期 */
		OVERDUE(2),
		/** 账务交易形态 3 = 还款 */
		PAID(3),
		/** 账务交易形态 4 = 计提利息 */
		SETINT(4),
		/** 账务交易形态 5 = 逾期(已减值) */
		YES_OVERDUE(5),
		/** 账务交易形态 6 = 还款(已减值)*/
		YES_PAID(6),
		/** 账务交易形态 7 = 计提利息(已减值)*/
		YES_SETINT(7),
		/** 账务交易形态 8 = 单项准备金(未减值)*/
		NO_SINGLE(8),
		/** 账务交易形态 9 = 单项准备金(已减值)*/
		YES_SINGLE(9),
		/** 账务交易形态 10 = 专项准备金*/
		SPECIAL(10),
		/** 账务交易形态 11 = 核销(未减值)*/
		NO_VERIFICATION(11),
		/** 账务交易形态 12 = 核销(已减值)*/
		YES_VERIFICATION(12),
		/** 账务交易形态 13 = 手续费-银行(未减值)*/
		NO_COST_BANK(13),
		/** 账务交易形态 14 = 手续费-保证金(未减值)*/
		NO_COST_BOND(14),
		/** 账务交易形态 15 = 手续费-库存现金(未减值)*/
		NO_COST_STOCK(15),
		/** 账务交易形态 16 = 手续费-银行(已减值)*/
		YES_COST_BANK(16),
		/** 账务交易形态 17 = 手续费-保证金(已减值)*/
		YES_COST_BOND(17),
		/** 账务交易形态 18 = 手续费-库存现金(已减值)*/
		YES_COST_STOCK(18),
		/** 账务交易形态 19 = 现金还款(未减值)*/
		NO_BOND_PAID(19),
		/** 账务交易形态 20 = 现金还款(已减值)*/
		YES_BOND_PAID(20),
		/** 账务交易形态 21 = 保证还款(未减值)*/
		NO_STOCK_PAID(21),
		/** 账务交易形态 22 = 保证还款(未减值)*/
		YES_STOCK_PAID(22),
		/** 账务交易形态 23 = 现金放款*/
		BOND_LOAN(23),
		/** 账务交易形态 24 = 保证金放款*/
		STOCK_LOAN(24),
		/** 账务交易形态 25 = 资产转出*/
		ATTORN(25),
		/** 账务交易形态 26 = 资产转出利息结计*/
		ATTORN_SETINT(26),
		/** 账务交易形态 27 = 资产转出还款*/
		ATTORN_PAID(27),
		/** 账务交易形态 28 = 资产转出处置收益or损失*/
		ATTORN_DEAL(28),
		
		/** 账务交易形态 31 TEMP1*/
		NO_TEMP_BANK(31),
		/** 账务交易形态 32 = TEMP1*/
		NO_TEMP_BOND(32),
		/** 账务交易形态 33 = TEMP1*/
		NO_TEMP_STOCK(33);
		
		private Integer val;
		private AccountEventType(Integer val) {
			this.val = val;
		}
		@Override
		public int toInt() {
			return val;
		}
		/**
		 * 判断是否相等
		 * @param val
		 * @return
		 */
		public boolean eq(Integer val){
			if(val!=null){
				return val.equals(this.toInt());
			}else{
				return false;
			}
		}
		private static Map<Integer, AccountEventType> instanceMap = EnumUtils
				.mapIntEnum(AccountEventType.class);
		public static AccountEventType valOf(Integer i) {
			return instanceMap.get(i);
		}
	}
	/**
	 * 贷款业务类型
	 *
	 */
	public enum LoanBusinessType implements StrEnum{
		LOAN("贷款发放","001"),
		OVERDUE("贷款逾期","002"),
		PAID("贷款还款","003"),
		SETINT("贷款结息","004"),
		SINGLE("贷款单项准备金计提","009"),
		SPECIAL("贷款专项准备金计提","010"),
		VERIFICATION("贷款核销","011"),
		COST("贷款费用","012"),
		ATTORN("资产转出","013");
		private String name;
		private String code;
		
		private LoanBusinessType(String name,String code) {
			this.name=name;
			this.code = code;
		}
		@Override
		public String toStr() {
			return code;
		}
		private static Map<String, LoanBusinessType> instanceMap = EnumUtils
				.mapStrEnum(LoanBusinessType.class);
		public static LoanBusinessType valOf(String code) {
			return instanceMap.get(code);
		}
		public String getName() {
			return name;
		}
		public String getCode() {
			return code;
		}
		
	}
	
	/**
	 * 业务状态
	 *
	 */
	public enum BillStatus implements StrEnum{
		/** 发送未入账047002 */
		SEND_NONE("047002"),
		/** 已入账047003 */
		ENTER("047003"),
		/** 退单 047004*/
		BACK("047004"),
		/** 冲销未入账 047005*/
		CX_NONE("047005"),
		/** 冲销已入账047006 */
		CX_ENTER("047006");
		private String val;
		
		private BillStatus(String val) {
			this.val = val;
		}
		@Override
		public String toStr() {
			return val;
		}
		public boolean eq(String str){
			if(StringUtils.isNotBlank(str)){
				return StringUtils.equals(str, this.toStr());
			}
			return false;
		}
		private static Map<String, BillStatus> instanceMap = EnumUtils
				.mapStrEnum(BillStatus.class);
		public static BillStatus valOf(String str) {
			return instanceMap.get(str);
		}
		
	}
	/**
	 * 入账标识
	 * RecordedVchFlag
	 *
	 */
	public enum RecordedVchFlag implements StrEnum{
		/**	入账标识：未入账 */
		NONE("0"),
		/**	入账标识： 已入账 */
		ENTER("1"),
		/** 入账标识：退单 */
		BACK("2"),
		/** 入账标识：冲销未入账 */
		CX_NONE("3"),
		/** 入账标识：冲销已入账 */
		CX_ENTER("4");
		private String val;
		
		private RecordedVchFlag(String val) {
			this.val = val;
		}
		@Override
		public String toStr() {
			return val;
		}
		private static Map<String, RecordedVchFlag> instanceMap = EnumUtils
				.mapStrEnum(RecordedVchFlag.class);
		public static RecordedVchFlag valOf(String str) {
			return instanceMap.get(str);
		}
	}
	/**
	 * 传送标识
	 * TransmitFlag
	 *
	 */
	public enum TransmitFlag implements StrEnum{
		/**	传送标识：0=还未传送 */
		NONE("0"),
		/**	传送标识：1=系统自动传送 */
		AUTO("1"),
		/** 传送标识：2=人工手动传送 */
		HAND("2");
		private String val;
		
		private TransmitFlag(String val) {
			this.val = val;
		}
		@Override
		public String toStr() {
			return val;
		}
		private static Map<String, TransmitFlag> instanceMap = EnumUtils
				.mapStrEnum(TransmitFlag.class);
		public static TransmitFlag valOf(String str) {
			return instanceMap.get(str);
		}
	}
	/**
	 *	资金来源
	 */
	public enum AccountSource implements StrEnum{
		/** 银行转账 */
		BANK("1"),
		/** 现金 */
		BOND("2"),
		/** 保证金  */
		STOCK("3");
		private String val;
		
		private AccountSource(String val) {
			this.val = val;
		}
		@Override
		public String toStr() {
			return val;
		}
		private static Map<String, AccountSource> instanceMap = EnumUtils
				.mapStrEnum(AccountSource.class);
		public static AccountSource valOf(String str) {
			return instanceMap.get(str);
		}
	}
}
