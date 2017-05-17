package com.coamctech.bxloan.service.bizapply;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.coamctech.bxloan.entity.BizFixedAssets;
import com.coamctech.bxloan.entity.BizNoFixedAssetLiabilities;
import com.coamctech.bxloan.entity.ProduceAreaInfo;

/**
 * 家庭资产信息Service
 * @author AcoreHeng
 *
 */
public interface BizFamilyAssetsService {
	
	/**
	 * 保存生产区域信息
	 */
	public void saveProductionAreaInfo(ProduceAreaInfo pai);
	
	/**
	 * 保存固定资产
	 * @param bizFa
	 */
	public void saveBizFixedAsset(BizFixedAssets bizFa);
	
	
	/**
	 * 保存非固定资产及负债
	 * @param bizNoFal
	 */
	public void saveBizNoFixedAssetList(List<BizNoFixedAssetLiabilities> bizNoFalList,Long projId);
	
	/**
	 * 查询生产区域信息
	 * @param projId
	 * @param type 1:农业，2：非农业
	 */
	public List<ProduceAreaInfo> findProductionAreaInfoList(Long projId,String type);
	/**
	 * 查询固定资产
	 * @param projId
	 */
	public List<BizFixedAssets> findBizFixedAssetList(Long projId,String bizType);
	/**
	 * 查询非固定资产及负债
	 * @param projId
	 */
	public List<BizNoFixedAssetLiabilities> findBizNoFixedAssetList(Long projId);
	
	
	/**
	 * 删除生产区域信息
	 * @param id
	 */
	void deleteProductionAreaInfoById(Long id);
	/**
	 * 删除固定资产
	 * @param id
	 */
	void deleteBizFixedAssetById(Long id);
	/**
	 * 删除非固定资产及负债
	 * @param id
	 */
	void deleteBizNoFixedAssetById(Long id);

	/**
	 * @param projId
	 * @return
	 */
	public List<BizNoFixedAssetLiabilities> initBizNoFixedAssetLiabList(Long projId);
	/**
	 * @param projId
	 * @return
	 */
	public List<BizFixedAssets> updateBizFixedAssetLiabList(Long projId,String bizType);
	
	

	/**
	 * @param id
	 * @return
	 */
	public ProduceAreaInfo findProductionAreaInfo(Long id);

	/**
	 * @param id
	 * @return
	 */
	public BizFixedAssets findFixedAsset(Long id);
	
}
