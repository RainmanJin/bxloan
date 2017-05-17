package com.coamctech.bxloan.service.collateral;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Page;

import com.coamctech.bxloan.entity.Collateral;
import com.coamctech.bxloan.entity.Individual;
import com.coamctech.bxloan.entity.LandProduce;
import com.coamctech.bxloan.entity.MachineEquipmentMortgage;
import com.coamctech.bxloan.entity.ProjectPawnInfo;
import com.coamctech.bxloan.entity.RealEstateMortgage;
import com.coamctech.bxloan.entity.TrafficCar;
import com.coamctech.bxloan.service.model.collateral.CollateralVO;
import com.coamctech.bxloan.service.model.tipmsg.MsgResult;

public interface CollateralService {

	Page<Object[]> findBySearch(Integer pageNumber, Integer pageSize,
			Collateral form, String guaranteeMode, Long orgId, Long projectId);

	Page<Object[]> findCustomersBySearch(Integer pageNumber, Integer pageSize,
			Long curUserId, Individual form);

	void delete(Long id);

	void save(Collateral collateral);

	void saveLandProduce(LandProduce landProduce);

	void saveMachineEquipmentMortgage(
			MachineEquipmentMortgage machineEquipmentMortgage);

	void saveRealEstateMortgage(RealEstateMortgage realEstateMortgage);

	void saveTrafficCar(TrafficCar trafficCar);

	Collateral get(Long id);

	LandProduce getLandProduce(Long id);

	MachineEquipmentMortgage getMachineEquipmentMortgage(Long id);

	RealEstateMortgage getRealEstateMortgage(Long id);

	TrafficCar getTrafficCar(Long id);

	Page<Object[]> findBySearchForBiz(Integer pageNumber, Integer pageSize,
			Collateral form, String guaranteeMode, Long orgId, Long projectId);

	Page<Object[]> findCustomersBySearchForBiz(Integer pageNumber,
			Integer pageSize, Individual form);

	// 2014-10-17 Add
	/**
	 * 抵质押物删除
	 * 
	 * @param id
	 * @return
	 */
	MsgResult deleteCollateral(Long id);

	/**
	 * @param form
	 * @param curUserId
	 * @param curOrgId
	 * @return
	 */
	MsgResult saveCollateral(CollateralVO form, Long curUserId, Long curOrgId);

	/**
	 * 查询担保类型
	 * 
	 * @param guarantyTypeCd
	 * @return
	 */
	public String findGuaranteeType(String guarantyTypeCd);

	Collateral findCollateralById(Long guarantyId);

	ProjectPawnInfo findProjectPawnInfoById(Long projectPawnInfoId);

	List<ProjectPawnInfo> findProjectPawnInfoListByProjectId(Long projectId);

	void saveProjectPawnInfo(ProjectPawnInfo ppi);

	Page<Object[]> findHistoryBySearch(Integer pageNumber, Integer pageSize,
			Long guarantyId);
	/**
	 * 功能说明：根据项目id，处理抵质押物列表
	 * 当合同结清或者提前还款时，合同状态为【合同结清】，累计抵押金额（即已设定金额）需要释放
	 * 主要对象：【关联新增】，【已关联】，【关联置换】
	 * @param projectId  
	 */
	void handlePledgeAmtWhenContractEnd(Long projectId);
	/**
	 * 功能说明：根据抵质押物id,判断（所有合同）是否已解除关联
	 * @param guarantyId  
	 */
	public boolean isPledgeIsUsedByGuarantyId(Long guarantyId);
	/**
	 * 功能说明：抵质押物实际担保金额：从抵押物累计抵押金额中扣除
	 *  累计抵押金额=累计抵押金额-贷款放款金额
	 * @param collateral  
	 */
	public void deductSetGuarantyAmt(Collateral collateral, BigDecimal actualCreditAmount);
}
