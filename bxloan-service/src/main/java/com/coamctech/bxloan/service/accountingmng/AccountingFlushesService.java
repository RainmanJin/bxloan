package com.coamctech.bxloan.service.accountingmng;

import java.util.List;

import org.springframework.data.domain.Page;

import com.coamctech.bxloan.service.model.accountingmng.AccountingFlushesVo;
import com.coamctech.bxloan.service.model.accountingmng.BizBaseVo;
import com.coamctech.bxloan.service.model.tipmsg.MsgResult;

/**
 * @author Acore
 *
 */
public interface AccountingFlushesService {
	/**
	 * 查询单据凭证相关
	 * @param pageSize
	 * @param pageNum
	 * @param billState
	 * @param tc
	 * @return
	 */
	Page<Object[]> findTallyCertificatePage(int pageSize,int pageNum,AccountingFlushesVo.Params params);
	
	/**
	 * 冲销的单据凭证更新
	 * @param id	单据凭证id
	 * @param userName	当前用户名
	 * 
	 * @return
	 */
	MsgResult updateTallyCertificateOfFlushes(Long id,String userName); 
	/**
	 * 查询单据详细
	 * @param id	单据凭证id
	 * @return
	 */
	AccountingFlushesVo.BillDetailVo findBillDetail(Long id);
	
	
	/**
	 * 单据账务详细
	 * @param billCd 	业务编号
	 * @return
	 */
	List<AccountingFlushesVo.BillAccountVo> findBillAccountVoList(String billCd);
	/**
	 * 业务：放款信息、还款信息等
	 * @param id
	 * @return
	 */
	BizBaseVo findBizInfo(Long id);
}
