package com.coamctech.bxloan.service.bizapply.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coamctech.bxloan.commons.dynamicquery.DynamicQuery;
import com.coamctech.bxloan.commons.utils.CommonHelper;
import com.coamctech.bxloan.dao.BizFixedAssetsDao;
import com.coamctech.bxloan.dao.BizNoFixedAssetLiabilitiesDao;
import com.coamctech.bxloan.dao.ProduceAreaInfoDao;
import com.coamctech.bxloan.entity.BizFixedAssets;
import com.coamctech.bxloan.entity.BizNoFixedAssetLiabilities;
import com.coamctech.bxloan.entity.ProduceAreaInfo;
import com.coamctech.bxloan.entity.SysDictItem;
import com.coamctech.bxloan.service.bizapply.BizFamilyAssetsService;
import com.coamctech.bxloan.service.common.BizApplyQueryService;
import com.coamctech.bxloan.service.common.SysDictService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
@Service("bizFamilyAssetsService")
@Transactional
public class BizFamilyAssetsServiceImpl implements BizFamilyAssetsService {
	@Autowired
	private BizFixedAssetsDao bizFixedAssetsDao;
	@Autowired
	private BizNoFixedAssetLiabilitiesDao bizNoFixedAssetLiabilitiesDao;
	@Autowired
	private ProduceAreaInfoDao produceAreaInfoDao;
	@Autowired
	private SysDictService sysDictService;
	@Autowired
	private BizApplyQueryService bizApplyQueryService;
	@Autowired
	private DynamicQuery dynamicQuery;
	
	private final String BIZ_TYPE_NONGYE="1";
	private final String BIZ_TYPE_NO_NONGYE="2";
	@Override
	public void saveProductionAreaInfo(ProduceAreaInfo pai) {
		ProduceAreaInfo db_pai=null;
		if(pai.getId()!=null&&pai.getId()>0 ){
			db_pai=produceAreaInfoDao.findOne(pai.getId());
		}
		if(db_pai==null){
			db_pai=new ProduceAreaInfo();
			db_pai.setProjectId(pai.getProjectId());
		}
		BeanUtils.copyProperties(pai, db_pai,"projectId");
		produceAreaInfoDao.save(db_pai);
		
	}

	@Override
	public void saveBizFixedAsset(BizFixedAssets bizFa) {
		BizFixedAssets db_bizFa=null;
		if(bizFa.getId()!=null&&bizFa.getId()>0){
			db_bizFa=bizFixedAssetsDao.findOne(bizFa.getId());
		}
		if(db_bizFa==null){
			db_bizFa=new BizFixedAssets();
			db_bizFa.setProjectId(bizFa.getProjectId());
		}
		BeanUtils.copyProperties(bizFa, db_bizFa,"projectId");
		bizFixedAssetsDao.save(db_bizFa);
	}

	@Override
	public void saveBizNoFixedAssetList(
			List<BizNoFixedAssetLiabilities> bizNoFalList,Long projId) {
		if(CollectionUtils.isEmpty(bizNoFalList)){
			throw new NullPointerException("参数为空");
		}
		Map<Long, BizNoFixedAssetLiabilities> map=Maps.newHashMap();
		for (BizNoFixedAssetLiabilities bizNfal : bizNoFalList) {
			map.put(bizNfal.getId(), bizNfal);
		}
		List<BizNoFixedAssetLiabilities> db_bizNoFalList=this.findBizNoFixedAssetList(projId);
		if(CollectionUtils.isEmpty(db_bizNoFalList)){
			throw new NullPointerException("数据错误");
		}
		BizNoFixedAssetLiabilities temp_bizNfal=null;
		for (BizNoFixedAssetLiabilities bizNfal : db_bizNoFalList) {
			temp_bizNfal=map.get(bizNfal.getId());
			if(temp_bizNfal!=null){
				bizNfal.setPrice(temp_bizNfal.getPrice());
				bizNfal.setRemark(temp_bizNfal.getRemark());
			}
		}
		bizNoFixedAssetLiabilitiesDao.save(db_bizNoFalList);
	}
	@Override
	public List<ProduceAreaInfo> findProductionAreaInfoList(Long projId,String type) {
		return produceAreaInfoDao.findListByProjId(projId,type);
		
	}

	@Override
	public List<BizFixedAssets> findBizFixedAssetList(Long projId,String bizType) {
		return bizFixedAssetsDao.findListByProjId(projId,bizType);
		
	}
	@Override
	public List<BizFixedAssets> updateBizFixedAssetLiabList(
			Long projId, String bizType) {
		String jpql="update BizFixedAssets bizNfal set bizNfal.bizType=?1 where bizNfal.projectId=?2";
		int result=dynamicQuery.executeUpdate(jpql, bizType,projId);
		if(result<=0){
			return null;
		}else{
			return this.findBizFixedAssetList(projId, bizType);
		}
	}

	@Override
	public List<BizNoFixedAssetLiabilities> findBizNoFixedAssetList(Long projId) {
		
		return bizNoFixedAssetLiabilitiesDao.findListByProjId(projId,this.getBizTypeByProjId(projId));
	}

	@Override
	public void deleteProductionAreaInfoById(Long id) {
		produceAreaInfoDao.delete(id);
		
	}

	@Override
	public void deleteBizFixedAssetById(Long id) {
		bizFixedAssetsDao.delete(id);
		
	}

	@Override
	public void deleteBizNoFixedAssetById(Long id) {
		bizNoFixedAssetLiabilitiesDao.delete(id);
		
	}
	@Override
	public List<BizNoFixedAssetLiabilities> initBizNoFixedAssetLiabList(Long projId) {
		this.deleteBizNoFixedAssetLiabs(projId);
		final String ND_NO_FIEXED_ASSET="ND_NO_FIEXED_ASSET";
		String bizType=this.getBizTypeByProjId(projId);
		List<SysDictItem> assetTypeList=sysDictService.findList(ND_NO_FIEXED_ASSET, null);
		if (CollectionUtils.isEmpty(assetTypeList)) {
			return null;
		}
		Set<Long> pids=Sets.newHashSet();
		for (SysDictItem item : assetTypeList) {
			pids.add(item.getId());
		}
		Map<Long, List<SysDictItem>> map=this.findSysDictItemListMap(ND_NO_FIEXED_ASSET, pids,bizType);
		List<SysDictItem> tempList=null;
		List<BizNoFixedAssetLiabilities> bizNfalList=Lists.newArrayList();
		BizNoFixedAssetLiabilities bizNfal=null;
		for (SysDictItem type : assetTypeList) {
			tempList=map.get(type.getId());
			if(CollectionUtils.isEmpty(tempList)){
				bizNfal=new BizNoFixedAssetLiabilities();
				this.initBizNfal(bizNfal, type, null, projId,bizType);
				bizNfalList.add(bizNfal);
			}else{
				for (SysDictItem item : tempList) {
					bizNfal=new BizNoFixedAssetLiabilities();
					this.initBizNfal(bizNfal, type, item, projId,bizType);
					bizNfalList.add(bizNfal);
				}
			}
		}
		bizNoFixedAssetLiabilitiesDao.save(bizNfalList);
		return bizNfalList;
	}
	/**
	 * @param bizNfal
	 * @param type
	 * @param item
	 * @param projId
	 * @param bizType
	 */
	private void initBizNfal(BizNoFixedAssetLiabilities bizNfal,SysDictItem type,SysDictItem item,Long projId,String bizType){
		bizNfal.setProjectId(projId);
		bizNfal.setBizType(bizType);
		if(item!=null){
			bizNfal.setAssetNameCode(item.getValue());
			bizNfal.setAssetName(item.getName());
		}
		bizNfal.setAssetType(type.getValue());
	}
	/**
	 * @param type
	 * @param pids
	 * @param bizType
	 * @return
	 */
	private Map<Long, List<SysDictItem>> findSysDictItemListMap(final String type,Set<Long> pids,String bizType){
		Map<Long, List<SysDictItem>> map=Maps.newHashMap();
		List<SysDictItem> list=sysDictService.findListByPids(type, pids);
		if(CollectionUtils.isEmpty(list)){
			return map;
		}
		List<SysDictItem> tempList=null;
		final String NO_NONGYE=StringUtils.equals(BIZ_TYPE_NO_NONGYE, bizType)?"002":"001";
		for (SysDictItem item : list) {
			if(map.containsKey(item.getPid())){
				tempList=map.get(item.getPid());
			}else{
				tempList=Lists.newArrayList();
				map.put(item.getPid(), tempList);
			}
			if(StringUtils.isBlank(item.getRangeOfUse())||CommonHelper.str2Set(item.getRangeOfUse()).contains(NO_NONGYE)){
				tempList.add(item);
			}
		}
		return map;
	}
	@Override
	public BizFixedAssets findFixedAsset(Long id) {
		return bizFixedAssetsDao.findOne(id);
	}
	@Override
	public ProduceAreaInfo findProductionAreaInfo(Long id) {
		return produceAreaInfoDao.findOne(id);
	}
	
	
	private String getBizTypeByProjId(Long projId){
		String bizType=bizApplyQueryService.findAgroBizTypeByProjId(projId);
		if(StringUtils.isBlank(bizType)){
			throw new NullPointerException("无法识别业务申请是农业还是非农业");
		}
		return bizType;
	}
	
	/**
	 * 删除原有非固定资产及其负债
	 * @param projId
	 */
	private void deleteBizNoFixedAssetLiabs(Long projId){
		String jpql="delete FROM BizNoFixedAssetLiabilities bizNfal where bizNfal.projectId=?1";
		dynamicQuery.executeUpdate(jpql, projId);
	}
	
}
