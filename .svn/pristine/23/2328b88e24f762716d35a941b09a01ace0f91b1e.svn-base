package com.coamctech.bxloan.service.bizapply;

import java.util.Date;
import java.util.List;

import com.coamctech.bxloan.entity.ExpectedCashFlowInfo;
import com.coamctech.bxloan.service.utils.EcfiObjTypeEnum;

public interface ExpectCashFlowInfoService {

	/**
	 * 保存
	 * 
	 * @param ecf
	 */
	ExpectedCashFlowInfo save(ExpectedCashFlowInfo ecf);

	/**
	 * 删除
	 * 
	 * @param eid
	 *            收入支出编号
	 */
	void delete(Long eid);

	/**
	 * 删除。通过种植类型编号删除
	 * 
	 * @param objId
	 *            种植类型编号
	 */
	void deleteByObjId(Long objId);

	/**
	 * 得到最大编号
	 * 
	 * @return
	 */
	Long getMaxId();

	/**
	 * 通过id查找对象
	 * 
	 * @return
	 */
	ExpectedCashFlowInfo getExpectedCashFlowInfoById(Long id);

	/**
	 * 查询所有的收入支出列表
	 * 
	 * @param projectId
	 *            种植类型编号
	 * @param objType
	 *            种植类型
	 * @param objId
	 *            种植类型编号
	 * @param objCode
	 *            0501：学杂费，0502：其他贷款
	 * @param flag
	 *            收支标示:1,收入，2：支出
	 * @return list
	 */
	List<ExpectedCashFlowInfo> findAll(Long projectId,String objType, Long objId, String objCode,
			String incomeExpendFlag);

	/**
	 * 检查月份是否存在
	 * 
	 * @param objId
	 * @param monthOfYear
	 * @param flag
	 *            收入还是支出
	 * @return
	 */
	Long checkMonthIsNotExist(ExpectedCashFlowInfo info);
	
	/**
	 * 检查月份是否存在
	 * @param info
	 * @return true:存在,false:不存在
	 */
	boolean checkMonthIsExist(ExpectedCashFlowInfo info);
	
	
	/**
	 * 删除预期现金流明细
	 * @param objType 
	 * @param objId
	 * @param objCode
	 */
	void deleteEcfiByObj(Long projectId,EcfiObjTypeEnum objType,Long objId,String objCode);

}
