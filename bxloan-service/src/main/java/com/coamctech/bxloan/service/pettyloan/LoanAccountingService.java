package com.coamctech.bxloan.service.pettyloan;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.coamctech.bxloan.entity.AccountingInfo;
import com.coamctech.bxloan.entity.PayLoanInfo;
import com.coamctech.bxloan.service.pettyloan.bo.AccountingBo;
import com.coamctech.bxloan.service.pettyloan.bo.DoVchAmtBo;
import com.coamctech.bxloan.service.pettyloan.bo.DoVchResultBo;

/**
 * 贷款-账务
 * @author WallenHeng
 */
public interface LoanAccountingService {
	
	
	/**
	 * 放款时出帐调用
	 * @param doVchAmt
	 * @param bchId
	 * @param lastModUser
	 * @return	
	 */
	DoVchResultBo doVchByLoan(DoVchAmtBo doVchAmt,Integer bchId , Integer lastModUser,PayLoanInfo payLoan );
	/**
	 * 还款时账务处理
	 * @param doVchAmt	账务数据
	 * @param bchId	机构Id
	 * @param lastModUser	最后修改人id
	 * @return 业务编号
	 */
	String doVchByPaid(DoVchAmtBo doVchAmt,Long bchId , Long lastModUser);
	
	/**
	 * 出账
	 * @return
	 */
	DoVchResultBo doVch(AccountingBo accBo);
	/**
	 * 单据入账
	 * @param billCd	业务编号
	 * @param operator	操作人
	 * @param busiDate	业务时间
	 */
	void billEnter(String billCd,String operator,Date busiDate);
	
	/**
	 * 单据退单
	 * @param billCd	业务编号
	 * @param operator	操作人
	 * @param busiDate	业务时间
	 * @param backCause	退单原因
	 */
	void billBack(String billCd,String operator,Date busiDate,String backCause);
	/**
	 * 单据冲销入账
	 * @param billCd	业务编号
	 * @param operator	操作人
	 * @param busiDate	业务时间
	 */
	void billEnterOfCX(String billCd,String operator,Date busiDate);
	/**
	 * 单据冲销退单
	 * @param billCd	业务编号
	 * @param operator	操作人
	 * @param busiDate	业务时间
	 * @param backCause	退单原因
	 */
	void billBackOfCX(String billCd,String operator,Date busiDate,String backCause);
	/**
	 * 单据冲正（将单据改为冲销未入账）
	 * @param billCd
	 * @param operator
	 */
	void billNoneEnterOfCX(String billCd,String operator);
	
	
	public void buildTallyCertificate(List<AccountingInfo> accInfos,PayLoanInfo payLoan );
	
	/**
	 * 判断机构与接口联动(openAccounting)是否开启
	 * @param orgId
	 * @return true：开启，false：关闭
	 */
	public boolean isInterfaceOpenOfAccounting(String orgId);
	
	/**
	 * 获取同一凭证编号的第一条账务明细
	 * @param transNo
	 * @return
	 */
	AccountingInfo findFirstAccountingInfo(String transNo);
	/**
	 * 费用录入
	 * 
	 * @param doVchAmtVO
	 * @param bchid
	 * @param lastmoduser
	 * @return
	 */
	public Map doVchByCost(DoVchAmtBo amtVO, Long applyOrgId, Long applyUserNum);
}
