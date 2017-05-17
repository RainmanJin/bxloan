package com.coamctech.bxloan.service.bizapply;

import java.util.List;

import org.springframework.data.domain.Page;

import com.coamctech.bxloan.entity.ExpectCashFlow;
import com.coamctech.bxloan.entity.ExpectCashFlowDetail;

public interface ExpectCashFlowService {
	
	/**
	 * 根据id查找ExpectCashFlow
	 * @param id
	 * */
	ExpectCashFlow findExpectCashFlowById(Long id);
	/**
	 * 根据id查找ExpectCashFlowDetail
	 * @param id
	 * */
	ExpectCashFlowDetail findExpectCashFlowDetailById(Long id);
	/**
	 * 根据id查找ExpectCashFlowDetail
	 * @param expectCashFlowId
	 * */
	List<ExpectCashFlowDetail> findEcfListById(Long expectCashFlowId);
	/**
	 * 根据id查找ExpectCashFlowDetail
	 * @param expectCashFlowId
	 * */
	List<ExpectCashFlowDetail> findEcfdListByIdAndType(Long expectCashFlowId, String type);
	/**
	 * 保存ExpectCashFlow
	 * @param expectCashFlow
	 * */
	Long saveExpectCashFlow(ExpectCashFlow expectCashFlow);
	/**
	 * 保存ExpectCashFlowDetail
	 * @param expectCashFlowDetail
	 * */
	Long saveExpectCashFlowDetail(ExpectCashFlowDetail expectCashFlowDetail);
	/**
	 * 查询预计现金流量列表
	 * @param pageNumber
	 * @parma pageSize
	 * @param projectId
	 * */
	Page findBySearch(Integer pageNumber, Integer pageSize, String projectId);
	/**
	 * 根据id删除现金流量
	 * @param id
	 * */
	void deleteExpectCashFlow(Long id);
	/**
	 * 查找现金流量细则列表
	 * */
	List<Object[]> findDetailsBySearch(Integer pageNumber, Integer pageSize, List<Object> params);
	/**
	 * 根据id删除现金流量细节
	 * @param id
	 * */
	void deleteExpectCashFlowDetail(Long id);
	
}
