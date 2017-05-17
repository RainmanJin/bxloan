package com.coamctech.bxloan.web.controller.bizapply;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.coamctech.bxloan.commons.controller.BaseController;
import com.coamctech.bxloan.commons.result.Result;
import com.coamctech.bxloan.commons.utils.CommonHelper;
import com.coamctech.bxloan.commons.utils.FileUtils;
import com.coamctech.bxloan.commons.utils.excel.PoiExcel;
import com.coamctech.bxloan.entity.BizFixedAssets;
import com.coamctech.bxloan.entity.BizNoFixedAssetLiabilities;
import com.coamctech.bxloan.entity.ProduceAreaInfo;
import com.coamctech.bxloan.entity.SysDictItem;
import com.coamctech.bxloan.service.bizapply.BizApplyInfoCountService;
import com.coamctech.bxloan.service.bizapply.BizFamilyAssetsService;
import com.coamctech.bxloan.service.common.BizApplyQueryService;
import com.coamctech.bxloan.service.common.SysDictService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
/**
 * 家庭资产相关信息
 * @author AcoreHeng
 */
@Controller
@RequestMapping("/bizApply/familyAssets")
public class BizFamilyAssetsController extends BaseController {
	private Logger logger=LoggerFactory.getLogger(BizFamilyAssetsController.class);
	private final String JSP_DIR="businessapplicationwd";
	@Autowired
	private BizFamilyAssetsService bizFamilyAssetsService;
	@Autowired
	private BizApplyQueryService bizApplyQueryService;
	@Autowired
	private BizApplyInfoCountService bizApplyInfoCountService;
	@Autowired
	private SysDictService sysDictService;
	@RequestMapping
	public String index(){
		logger.info("家庭资产表单初始化***************");
		return JSP_DIR+"/bizFamilyAssets";
	}
	@RequestMapping("/test")
	public void init(Long projId,HttpServletRequest req,HttpServletResponse resp){
		try {
			PoiExcel poiExcel= bizApplyInfoCountService.createProfitLossAsseLliabExcel(projId);
			poiExcel.write(FileUtils.initDownload(req, resp, poiExcel.getExcelName()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//TODO  生产区域信息
	/**
	 * 保存生产区域信息
	 * @return
	 */
	@RequestMapping("/saveProductionAreaInfo")
	@ResponseBody
	public Result saveProductionAreaInfo(ProduceAreaInfo pai,HttpServletRequest req){
		Date rentDate=CommonHelper.str2Date(req.getParameter("rentDateStr"), CommonHelper.DF_DATE);
		pai.setRentDate(rentDate);
		pai.setType("1");
		bizFamilyAssetsService.saveProductionAreaInfo(pai);
		return success("保存成功");
	}
	/**
	 * 删除生产区域信息
	 * @param id
	 * @return
	 */
	@RequestMapping("/delProductionAreaInfo")
	@ResponseBody
	public Result delProductionAreaInfo(Long id){
		try {
			bizFamilyAssetsService.deleteProductionAreaInfoById(id);
		} catch (Exception e) {
			e.printStackTrace();
			return failure("删除失败");
		}
		return success("删除成功");
	}
	/**
	 * 查询生产区域信息
	 * @param id
	 * @return
	 */
	@RequestMapping("/findProductionAreaInfo")
	@ResponseBody
	public Result findProductionAreaInfo(Long id){
		ProduceAreaInfo info=bizFamilyAssetsService.findProductionAreaInfo(id);//农业
		return success(info);
	}
	/**
	 * 查询生产区域列表
	 * @param projId
	 * @return
	 */
	@RequestMapping("/findProductionAreaInfoList")
	@ResponseBody
	public Result findProductionAreaInfoList(Long projId){
		List<ProduceAreaInfo> list=bizFamilyAssetsService.findProductionAreaInfoList(projId, "1");//农业
		return success(list);
	}
	//TODO 固定资产
	/**
	 * 保存固定资产
	 * @param asset
	 * @param req
	 * @return
	 */
	@RequestMapping("/saveFixedAsset")
	@ResponseBody
	public Result saveFixedAsset(BizFixedAssets asset,HttpServletRequest req){
		logger.info("保存固定资产信息");
		try {
			Date buyOrBuildYear=CommonHelper.str2Date(req.getParameter("buyOrBuildYearStr"), "yyyy");
			asset.setBuyOrBuildYear(buyOrBuildYear);
			bizFamilyAssetsService.saveBizFixedAsset(asset);
		} catch (Exception e) {
			e.printStackTrace();
			return failure("保存固定资产信息失败");
		}
		return success("保存固定资产信息成功");
	}
	@RequestMapping("/delFixedAsset")
	@ResponseBody
	public Result delFixedAsset(Long id){
		try {
			bizFamilyAssetsService.deleteBizFixedAssetById(id);
		} catch (Exception e) {
			e.printStackTrace();
			return failure("删除失败");
		}
		return success("删除成功");
	}
	@RequestMapping("/findFixedAsset")
	@ResponseBody
	public Result findFixedAsset(Long id){
		BizFixedAssets bizFa=null;
		try {
			bizFa=bizFamilyAssetsService.findFixedAsset(id);
		} catch (Exception e) {
			e.printStackTrace();
			return failure("查询失败");
		}
		return success(bizFa);
	}
	@RequestMapping("/findFixedAssetList")
	@ResponseBody
	public Result findFixedAssetList(Long projId){
		List<BizFixedAssets> list=null;
		try {
			final String bizType=bizApplyQueryService.findAgroBizTypeByProjId(projId);
			list = bizFamilyAssetsService.findBizFixedAssetList(projId,bizType);
			if(CollectionUtils.isEmpty(list)){
				list = bizFamilyAssetsService.updateBizFixedAssetLiabList(projId, bizType);
			}
		} catch (Exception e) {
			logger.error("ProjectApply无法判断农业类型");
		}
		return success(list);
	}
	
	@RequestMapping("/findFixedAssetDictData")
	@ResponseBody
	public Result findFixedAssetDictData(){
		Map<String, Object> map=sysDictService.findDictData("ND_FIEXED_ASSET");
		return success(map);
	}
	//TODO 非固定资产及负债
	
	/**
	 * 保存固定资产
	 * @param asset
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/saveNoFixedAssetLiab",method=RequestMethod.POST)
	@ResponseBody
	public Result saveNoFixedAssetLiab(Long projId,HttpServletRequest req){
		String jsonParams=req.getParameter("data");
		logger.info(jsonParams);
		try {
			bizFamilyAssetsService.saveBizNoFixedAssetList(this.toNoFixedAssetLiabList(jsonParams), projId);
		} catch (Exception e) {
			e.printStackTrace();
			return failure("保存失败");
		}
		return success("保存成功");
	}
	@RequestMapping("/findNoFixedAssetLiabList")
	@ResponseBody
	public Result findNoFixedAssetLiabList(Long projId){
		List<BizNoFixedAssetLiabilities> list=null;
		try {
			list = bizFamilyAssetsService.findBizNoFixedAssetList(projId);
			if(CollectionUtils.isEmpty(list)){
				list=bizFamilyAssetsService.initBizNoFixedAssetLiabList(projId);
			}
		} catch (Exception e) {
			logger.error("ProjectApply无法判断农业类型");
		}
		return success(this.toBizNfalTbData(list));
	}
	
	private List<BizNoFixedAssetLiabilities> toNoFixedAssetLiabList(String text){
		JSONArray jsonArray=JSON.parseArray(text);
		if(jsonArray.isEmpty()){
			return null;
		}
		List<BizNoFixedAssetLiabilities> list=Lists.newArrayList();
		BizNoFixedAssetLiabilities bizNfal=null;
		for (Object obj : jsonArray) {
			JSONObject jsonObj=(JSONObject)obj;
			bizNfal=new BizNoFixedAssetLiabilities();
			bizNfal.setId(CommonHelper.toLong(jsonObj.get("id")));
			bizNfal.setPrice(CommonHelper.toBigDecimal(jsonObj.get("money")));
			bizNfal.setRemark(CommonHelper.toStr(jsonObj.get("remark")));
			list.add(bizNfal);
		}
		return list;
	}
	
	private Map<String, Object> toBizNfalTbData(List<BizNoFixedAssetLiabilities> list){
		Map<String, Object> map=Maps.newHashMap();
		map.put("items", list);
		Map<String, Object> viewMap=Maps.newHashMap();
		map.put("assetTypes", viewMap);
		final String ND_NO_FIEXED_ASSET="ND_NO_FIEXED_ASSET";
		if(CollectionUtils.isNotEmpty(list)){
			List<SysDictItem> dictItems=sysDictService.findList(ND_NO_FIEXED_ASSET, null);
			if(CollectionUtils.isNotEmpty(dictItems)){
				for (SysDictItem sdi : dictItems) {
					viewMap.put(sdi.getValue(), sdi.getName());
				}
			}
		}
		return map;
		
	}
}
