package com.coamctech.bxloan.web.controller.bizapply;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coamctech.bxloan.commons.controller.BaseController;
import com.coamctech.bxloan.commons.result.DataTablesPage;
import com.coamctech.bxloan.commons.result.Result;
import com.coamctech.bxloan.datadict.DataDict;
import com.coamctech.bxloan.entity.BreedAgriculture;
import com.coamctech.bxloan.entity.BreedNonAgriculture;
import com.coamctech.bxloan.entity.CultivateAgriculture;
import com.coamctech.bxloan.entity.CultivateNonAgriculture;
import com.coamctech.bxloan.entity.ExpectCashFlow;
import com.coamctech.bxloan.service.bizapply.CultivateAndBreedService;
import com.coamctech.bxloan.service.bizapply.ExpectCashFlowInfoService;
import com.coamctech.bxloan.service.utils.EcfiObjTypeEnum;
import com.coamctech.bxloan.web.vo.bizapply.ExpectCashFlowSimpleVo;
import com.google.common.base.Function;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@Controller
@RequestMapping("/cultivateAndBreed")
public class BizCultivateAndBreedController extends BaseController{
	
	@Autowired
	private DataDict dataDict;
	@Autowired
	private CultivateAndBreedService cultivateAndBreedService;
	@Autowired
	private ExpectCashFlowInfoService expectedCashFlowInfoService;
	
	
	
	/**
	 * 非农种植业列表的查询方法
	 * 
	 * @param request
	 *            HttpServletRequest的对象
	 * @param sEcho
	 *            datatables的被请求次数
	 * @param firstIndex
	 *            起始页数
	 * @param pageSize
	 *            每页多少条记录
	 * @return page DataTablesPage对象的实例
	 * 
	 * @author lijing
	 * @lastModified lijing 2014-07-25 9:30:50
	 */
	@RequestMapping("/findfnCultivateBySearch")
	@ResponseBody
	public DataTablesPage findfnCultivateBySearch(
			@RequestParam("sEcho") Integer sEcho,
			@RequestParam("iDisplayStart") Integer firstIndex,
			@RequestParam("iDisplayLength") Integer pageSize,
			HttpServletRequest request) {
		// 查询条件
		String projectId = (String) request.getParameter("projectId");
		List<Object> params = new ArrayList<Object>();
		List<CultivateNonAgriculture> queryList = this.cultivateAndBreedService.findfnCultivateBySearch(
				firstIndex / pageSize + 1, pageSize, projectId);
		
		DataTablesPage page = new DataTablesPage(sEcho, queryList, new Long(queryList.size()), 0);
		return page;
	}
	
	/**
	 * 非农养殖业列表的查询方法
	 * 
	 * @param request
	 *            HttpServletRequest的对象
	 * @param sEcho
	 *            datatables的被请求次数
	 * @param firstIndex
	 *            起始页数
	 * @param pageSize
	 *            每页多少条记录
	 * @return page DataTablesPage对象的实例
	 * 
	 * @author lijing
	 * @lastModified lijing 2014-07-25 9:30:50
	 */
	@RequestMapping("/findfnBreedBySearch")
	@ResponseBody
	public DataTablesPage findfnBreedBySearch(
			@RequestParam("sEcho") Integer sEcho,
			@RequestParam("iDisplayStart") Integer firstIndex,
			@RequestParam("iDisplayLength") Integer pageSize,
			HttpServletRequest request) {
		// 查询条件
		String projectId = (String) request.getParameter("projectId");
		List<Object> params = new ArrayList<Object>();
		List<BreedNonAgriculture> queryList = this.cultivateAndBreedService.findfnBreedBySearch(
				firstIndex / pageSize + 1, pageSize, projectId);
		
		DataTablesPage page = new DataTablesPage(sEcho, queryList, new Long(queryList.size()), 0);
		return page;
	}
	/**
	 * 查找必要信息
	 * */
	@RequestMapping("findNecessaryMsg")
	@ResponseBody
	public Map<String, BigDecimal> findNecessaryMsg(@RequestParam Long projectId, @RequestParam String type){
		List rsList = null;
		if(projectId!=null&&StringUtils.isNotBlank(type)){
			if(StringUtils.equals(type, "1")){//非农种植业
				rsList = this.cultivateAndBreedService.findCnaListById(projectId);
				if(CollectionUtils.isNotEmpty(rsList)){
					CultivateNonAgriculture cna = (CultivateNonAgriculture)rsList.get(0);
					if(cna!=null){
						Map<String, BigDecimal> rsMap  = ImmutableMap.of(
								"workingYears", cna.getWorkingYears(), 
								"initialCapital", cna.getInitialCapital());
						return rsMap;
					}
				}
			}else if(StringUtils.equals(type, "2")){
				rsList = this.cultivateAndBreedService.findBnaListById(projectId);
				if(CollectionUtils.isNotEmpty(rsList)){
					BreedNonAgriculture bna = (BreedNonAgriculture)rsList.get(0);
					if(bna!=null){
						Map<String, BigDecimal> rsMap  = ImmutableMap.of(
								"workingYears", bna.getWorkingYears(), 
								"initialCapital", bna.getInitialCapital());
						return rsMap;
					}
				}
			}
		}else{
			return null;
		}
		return null;
	}
	/**
	 * 查找非农种植
	 * */
	@RequestMapping("findOneCultivate")
	@ResponseBody
	public CultivateNonAgriculture findOneCultivate(@RequestParam Long id){
		if(id!=null){
			return this.cultivateAndBreedService.findCnaById(id);
		}else{
			return null;
		}
	}
	/**
	 * 查找农种植
	 * */
	@RequestMapping("findOneNongCultivate")
	@ResponseBody
	public CultivateAgriculture findOneNongCultivate(@RequestParam Long id){
		if(id!=null){
			CultivateAgriculture ca =  this.cultivateAndBreedService.findCaById(id);
			if(ca.getRelativeId()!=null&&StringUtils.equals(ca.getType(), dataDict.getCodeVal("AgroNCulitivateTbType", "S2"))){
				CultivateAgriculture _ca = this.cultivateAndBreedService.findCaById(ca.getRelativeId());
				ca.setPredictSingleProduce(new BigDecimal(_ca.getForecast()));
			}else{
				if(ca.getPredictSingleProduce()==null){
					ca.setPredictSingleProduce(BigDecimal.ZERO);
				}
			}
			return ca;
		}else{
			return null;
		}
	}
	/**
	 * 查找非农养殖
	 * */
	@RequestMapping("findOneBreed")
	@ResponseBody
	public BreedNonAgriculture findOneBreed(@RequestParam Long id){
		if(id!=null){
			return this.cultivateAndBreedService.findBnaById(id);
		}else{
			return null;
		}
	}
	
	/**
	 * 查找非农养殖
	 * */
	@RequestMapping("findOneNongBreed")
	@ResponseBody
	public BreedAgriculture findOneNongBreed(@RequestParam Long id){
		if(id!=null){
			BreedAgriculture ba =  this.cultivateAndBreedService.findBaById(id);
			if(ba.getRelativeId()!=null&&StringUtils.equals(ba.getType(), dataDict.getCodeVal("AgroNBreedTbType", "S2"))){
				BreedAgriculture _ba = this.cultivateAndBreedService.findBaById(ba.getRelativeId());
				ba.setPredictProduceTotal(_ba.getPredict().divide(new BigDecimal("100")).multiply(new BigDecimal(_ba.getStockInitScale())).setScale(2, BigDecimal.ROUND_HALF_UP));//保留两位小数
			}else{
				if(ba.getPredictProduceTotal()==null){
					ba.setPredictProduceTotal(BigDecimal.ZERO);
				}
			}
			return ba;
		}else{
			return null;
		}
	}
	
	/**
	 * 保存非农种植业
	 * */
	@RequestMapping("saveCultivate")
	@ResponseBody
	public Result saveCultivate(@ModelAttribute CultivateNonAgriculture cnaVo){
		CultivateNonAgriculture cna = null;
		if(cnaVo.getId()!=null){
			cna = cultivateAndBreedService.findCnaById(cnaVo.getId());
		}else{
			cna = new CultivateNonAgriculture();
		}
		BeanUtils.copyProperties(cnaVo, cna, "id");
		
		try {
			this.updateWorkYearsAndInitialCapital(cna);
			cultivateAndBreedService.saveCultivateNonAgriculture(cna);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Result(true, "保存成功", cna.getId());
		
	}
	
	/**
	 * 保存农种植业
	 * */
	@RequestMapping("saveNongCultivate")
	@ResponseBody
	public Result saveNongCultivate(@ModelAttribute CultivateAgriculture caVo){
		CultivateAgriculture ca = null;
		if(caVo.getId()!=null){
			ca = cultivateAndBreedService.findCaById(caVo.getId());
		}else{
			ca = new CultivateAgriculture();
		}
		
		if(caVo.getRelativeId()==null&&StringUtils.equals(caVo.getType(), dataDict.getCodeVal("AgroNCulitivateTbType", "S2"))){
			CultivateAgriculture rca = cultivateAndBreedService.findCaById(ca.getRelativeId());
			if(rca!=null){
					rca.setRelativeId(null);
					cultivateAndBreedService.saveCultivateAgriculture(rca);
			}
		}
		
		BeanUtils.copyProperties(caVo, ca, "id");
		
		try {
			this.caculateCultivate(ca);
			cultivateAndBreedService.saveCultivateAgriculture(ca);
			
			if(ca.getRelativeId()!=null&&StringUtils.equals(ca.getType(), dataDict.getCodeVal("AgroNCulitivateTbType", "S2"))){
				CultivateAgriculture rca = cultivateAndBreedService.findCaById(ca.getRelativeId());
				if(rca!=null){
					rca.setRelativeId(ca.getId());
					cultivateAndBreedService.saveCultivateAgriculture(rca);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Result(true, "保存成功", ca.getId());
		
	}
	
	private void caculateCultivate(CultivateAgriculture ca) {
		if(StringUtils.equals(ca.getType(), dataDict.getCodeVal("AgroNCulitivateTbType", "S1"))){
			//过去
			ca.setSaleNum(ca.getOutput().subtract(ca.getFamilyConsume()).subtract(ca.getLivestockConsume()));
			ca.setSaleIncomeTotal(ca.getSaleNum().multiply(ca.getSalePrice()));
			BigDecimal predict = new BigDecimal(ca.getMinSingleProduce()).multiply(new BigDecimal("2")).add(new BigDecimal(ca.getMaxSingleProduce())).add(new BigDecimal(ca.getLastYearSingleProduce()));
			predict = predict.divide(new BigDecimal("4"));
			ca.setForecast(predict.toPlainString());
		}else if(StringUtils.equals(ca.getType(), dataDict.getCodeVal("AgroNCulitivateTbType", "S2"))){
			//未来
			ca.setPredictTotalProduce(ca.getPredictSingleProduce().multiply(new BigDecimal(ca.getCultivateScale())));
			ca.setSaleNum(ca.getPredictTotalProduce().subtract(ca.getFamilyConsume()).subtract(ca.getLivestockConsume()));
			ca.setSaleIncomeTotal(ca.getSaleNum().multiply(ca.getSalePrice()));
		}
	}

	/**
	 * 保存非农养殖业
	 * */
	@RequestMapping("saveBreed")
	@ResponseBody
	public Result saveBreed(@ModelAttribute BreedNonAgriculture bnaVo){
		BreedNonAgriculture bna = null;
		if(bnaVo.getId()!=null){
			bna = cultivateAndBreedService.findBnaById(bnaVo.getId());
		}else{
			bna = new BreedNonAgriculture();
		}
		BeanUtils.copyProperties(bnaVo, bna, "id");
		
		try {
			this.updateWorkYearsAndInitialCapital(bna);
			cultivateAndBreedService.saveBreedNonAgriculture(bna);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Result(true, "保存成功", bna.getId());
		
	}
	
	/**
	 * 保存农养殖业
	 * */
	@RequestMapping("saveNongBreed")
	@ResponseBody
	public Result saveNongBreed(@ModelAttribute BreedAgriculture baVo){
		BreedAgriculture ba = null;
		if(baVo.getId()!=null){
			ba = cultivateAndBreedService.findBaById(baVo.getId());
		}else{
			ba = new BreedAgriculture();
		}
		
		if(baVo.getRelativeId()==null&&StringUtils.equals(baVo.getType(), dataDict.getCodeVal("AgroNCulitivateTbType", "S2"))){
			BreedAgriculture rba = cultivateAndBreedService.findBaById(ba.getRelativeId());
			if(rba != null){
				rba.setRelativeId(null);
				cultivateAndBreedService.saveBreedAgriculture(rba);
			}
		}
		
		BeanUtils.copyProperties(baVo, ba, "id");
		
		try {
			this.caculateNongBreed(ba);
			cultivateAndBreedService.saveBreedAgriculture(ba);
			
			if(ba.getRelativeId()!=null){
				BreedAgriculture rba = cultivateAndBreedService.findBaById(ba.getRelativeId());
				if(rba!=null&&StringUtils.equals(ba.getType(), dataDict.getCodeVal("AgroNBreedTbType", "S2"))){
					rba.setRelativeId(ba.getId());
					cultivateAndBreedService.saveBreedAgriculture(rba);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Result(true, "保存成功", ba.getId());
		
	}
	
	private void caculateNongBreed(BreedAgriculture ba) {
		if(StringUtils.equals(ba.getType(), dataDict.getCodeVal("AgroNBreedTbType", "S1"))){
			//过去
			ba.setSaleIncomeTotal(ba.getSaleNum().multiply(ba.getSalePrice()));
			BigDecimal predict = (ba.getLowest().multiply(new BigDecimal("2")).add(ba.getHighest()).add(ba.getLastyear())).
					divide(new BigDecimal("4")).setScale(2, BigDecimal.ROUND_HALF_UP);//保留两位小数
			//Math.floor(predict.doubleValue());
			ba.setPredict(predict);
		}else if(StringUtils.equals(ba.getType(), dataDict.getCodeVal("AgroNBreedTbType", "S2"))){
			//未来
			ba.setSaleIncomeTotal(ba.getPredictProduceTotal().multiply(ba.getSalePrice()).setScale(2, BigDecimal.ROUND_HALF_UP));//保留两位小数
		}
	}

	/**
	 * 根据Id删除非农种植业
	 * 
	 * @param id
	 * @return
	 * @author lijing
	 * @lastModified lijing 2014-07-25 9:30:50
	 */
	@RequestMapping("/delCultivate/{id}")
	@ResponseBody
	public Result delCultivate(@PathVariable Long id) {
		CultivateNonAgriculture cna = this.cultivateAndBreedService.findCnaById(id);
		
		if(cna==null){
			return new Result(false, "该记录已被删除，请刷新页面！", null);
		}

		cultivateAndBreedService.deleteCultivateNonAgriculture(id);
		
		return new Result(true, "删除成功！", null);
	}
	
	/**
	 * 根据Id删除农种植业
	 * 
	 * @param id
	 * @return
	 * @author lijing
	 * @lastModify gph
	 */
	@RequestMapping("/delNongCultivate/{id}")
	@ResponseBody
	public Result delNongCultivate(@PathVariable Long id) {
		CultivateAgriculture ca = this.cultivateAndBreedService.findCaById(id);
		try {
			if (ca == null) {
				return new Result(false, "该记录已被删除，请刷新页面！", null);
			}
			if (ca.getRelativeId() != null) {
				CultivateAgriculture _ca = this.cultivateAndBreedService
						.findCaById(ca.getRelativeId());
				_ca.setRelativeId(null);
				cultivateAndBreedService.saveCultivateAgriculture(_ca);
			}
			cultivateAndBreedService.deleteCultivateAgriculture(id);
			
			//删除成功后，再删除对应的收入支出
			String type = dataDict.getCodeVal("AgroNCulitivateTbType", "S2");
			if(ca.getType().equals(type)){//未来12个月
				//expectedCashFlowInfoService.deleteByObjId(id);
				expectedCashFlowInfoService.deleteEcfiByObj(ca.getProjectId(), EcfiObjTypeEnum.CULTIVATE_AGRICULTURE, ca.getId(), null);
			}
		} catch (Exception e) {
			logger.error("删除失败，原因："+e.getMessage());
			return failure();
		}
		return new Result(true, "删除成功！", null);
	}
	
	/**
	 * 根据Id删除非农养殖业
	 * 
	 * @param id
	 * @return
	 * @author lijing
	 * @lastModified lijing 2014-07-25 9:30:50
	 */
	@RequestMapping("/delBreed/{id}")
	@ResponseBody
	public Result delBreed(@PathVariable Long id) {
		BreedNonAgriculture bna = this.cultivateAndBreedService.findBnaById(id);
		
		if(bna==null){
			return new Result(false, "该记录已被删除，请刷新页面！", null);
		}

		cultivateAndBreedService.deleteBreedNonAgriculture(id);
		
		return new Result(true, "删除成功！", null);
	}
	
	/**
	 * 根据Id删除农养殖业
	 * 
	 * @param id
	 * @return
	 * @author lijing
	 * @lastModified lijing 2014-07-25 9:30:50
	 */
	@RequestMapping("/delNongBreed/{id}")
	@ResponseBody
	public Result delNongBreed(@PathVariable Long id) {
		BreedAgriculture ba = this.cultivateAndBreedService.findBaById(id);
		
		if(ba==null){
			return new Result(false, "该记录已被删除，请刷新页面！", null);
		}
		if(ba.getRelativeId()!=null){
			BreedAgriculture _ba = this.cultivateAndBreedService.findBaById(ba.getRelativeId());
			_ba.setRelativeId(null);
			cultivateAndBreedService.saveBreedAgriculture(_ba);
		}
		cultivateAndBreedService.deleteBreedAgriculture(id);
		
		//删除成功后，再删除对应的收入支出
		String type = dataDict.getCodeVal("AgroNCulitivateTbType", "S2");
		if(ba.getType().equals(type)){//未来12个月
			//expectedCashFlowInfoService.deleteByObjId(id);
			expectedCashFlowInfoService.deleteEcfiByObj(ba.getProjectId(), EcfiObjTypeEnum.BREED_AGRICULTURE, ba.getId(), null);
		}
		
		return new Result(true, "删除成功！", null);
	}
	
	/**
	 * 更新初始资金和工作年限字段
	 * */
	private void updateWorkYearsAndInitialCapital(Object obj){
		boolean isWorkYearsChange = false;
		boolean isInitialCapitalChange = false;
		if(obj instanceof BreedNonAgriculture){
			BreedNonAgriculture bna = (BreedNonAgriculture)obj;
			List<BreedNonAgriculture> bnaList = cultivateAndBreedService.findBnaListById(bna.getProjectId());
			if(CollectionUtils.isNotEmpty(bnaList)){
				isWorkYearsChange = bnaList.get(0).getWorkingYears().compareTo(bna.getWorkingYears()) != 0;
				isInitialCapitalChange = bnaList.get(0).getInitialCapital().compareTo(bna.getInitialCapital()) != 0;
				if(isWorkYearsChange||isInitialCapitalChange){
					for (BreedNonAgriculture _bna : bnaList) {
						_bna.setWorkingYears(bna.getWorkingYears());
						_bna.setInitialCapital(bna.getInitialCapital());
					}
					this.cultivateAndBreedService.saveBreedNonAgricultureList(bnaList);
				}
			}
		}else if(obj instanceof CultivateNonAgriculture){
			CultivateNonAgriculture cna = (CultivateNonAgriculture)obj;
			List<CultivateNonAgriculture> cnaList = cultivateAndBreedService.findCnaListById(cna.getProjectId());
			if(CollectionUtils.isNotEmpty(cnaList)){
				isWorkYearsChange = cnaList.get(0).getWorkingYears().compareTo(cna.getWorkingYears()) != 0;
				isInitialCapitalChange = cnaList.get(0).getInitialCapital().compareTo(cna.getInitialCapital()) != 0;
				if(isWorkYearsChange||isInitialCapitalChange){
					for (CultivateNonAgriculture _cna : cnaList) {
						_cna.setWorkingYears(cna.getWorkingYears());
						_cna.setInitialCapital(cna.getInitialCapital());
					}
					this.cultivateAndBreedService.saveCultivateNonAgricultureList(cnaList);
				}
			}
		}
	}
	
	@RequestMapping("/findCultivateChoosenList")
	@ResponseBody
	public Result findCultivateChoosenList(@RequestParam Long projectId,@RequestParam(required=false) Long id){
		Map<Long, String> map = Maps.newHashMap();
		List<CultivateAgriculture> caList = this.cultivateAndBreedService.findCultivateChoosenList(projectId, id);
		if(CollectionUtils.isEmpty(caList)){
			return new Result(false, "没有可关联的过去信息！", null);
		}else{
			for (CultivateAgriculture ca : caList) {
				map.put(ca.getId(), dataDict.getCodeName("AgroFnCultivateType", ca.getCultivateType()) +"-"+ ca.getCultivateContent());
			}
			return new Result(true, "查询成功！", map);
		}
	}
	
	@RequestMapping("/findBreedChoosenList")
	@ResponseBody
	public Result findBreedChoosenList(@RequestParam Long projectId, @RequestParam(required=false) Long id){
		Map<Long, String> map = Maps.newHashMap();
		List<BreedAgriculture> baList = this.cultivateAndBreedService.findBreedChoosenList(projectId, id);
		if(CollectionUtils.isEmpty(baList)){
			return new Result(false, "没有可关联的过去信息！", null);
		}else{
			for (BreedAgriculture ba : baList) {
				map.put(ba.getId(), dataDict.getCodeName("AgroFnBreedType", ba.getBreedType()) +"-"+ ba.getBreedContent());
			}
			return new Result(true, "查询成功！", map);
		}
	}
	
	/**
	 * 农种植业过去12月列表的查询方法
	 * 
	 * @param request
	 *            HttpServletRequest的对象
	 * @param sEcho
	 *            datatables的被请求次数
	 * @param firstIndex
	 *            起始页数
	 * @param pageSize
	 *            每页多少条记录
	 * @return page DataTablesPage对象的实例
	 * 
	 * @author lijing
	 * @lastModified lijing 2014-07-25 9:30:50
	 */
	@RequestMapping("/findNongPassCultivateBySearch")
	@ResponseBody
	public DataTablesPage findNongPassCultivateBySearch(
			@RequestParam("sEcho") Integer sEcho,
			@RequestParam("iDisplayStart") Integer firstIndex,
			@RequestParam("iDisplayLength") Integer pageSize,
			HttpServletRequest request) {
		// 查询条件
		String type = dataDict.getCodeVal("AgroNCulitivateTbType", "S1");
		String projectId = (String) request.getParameter("projectId");
		List<Object> params = new ArrayList<Object>();
		List<CultivateAgriculture> queryList = this.cultivateAndBreedService.findNongPassCultivateBySearch(
				firstIndex / pageSize + 1, pageSize, projectId, Integer.valueOf(type));
		
		DataTablesPage page = new DataTablesPage(sEcho, queryList, new Long(queryList.size()), new Integer(type));
		return page;
	}
	
	/**
	 * 农种植业未来12月列表的查询方法
	 * 
	 * @param request
	 *            HttpServletRequest的对象
	 * @param sEcho
	 *            datatables的被请求次数
	 * @param firstIndex
	 *            起始页数
	 * @param pageSize
	 *            每页多少条记录
	 * @return page DataTablesPage对象的实例
	 * 
	 * @author lijing
	 * @lastModified lijing 2014-07-25 9:30:50
	 */
	@RequestMapping("/findNongFutureCultivateBySearch")
	@ResponseBody
	public DataTablesPage findNongFutureCultivateBySearch(
			@RequestParam("sEcho") Integer sEcho,
			@RequestParam("iDisplayStart") Integer firstIndex,
			@RequestParam("iDisplayLength") Integer pageSize,
			HttpServletRequest request) {
		// 查询条件
		String type = dataDict.getCodeVal("AgroNCulitivateTbType", "S2");
		String projectId = (String) request.getParameter("projectId");
		List<Object> params = new ArrayList<Object>();
		List<CultivateAgriculture> queryList = this.cultivateAndBreedService.findNongPassCultivateBySearch(
				firstIndex / pageSize + 1, pageSize, projectId, Integer.valueOf(type));
		
		DataTablesPage page = new DataTablesPage(sEcho, queryList, new Long(queryList.size()), new Integer(type));
		return page;
	}
	
	/**
	 * 农养殖业过去12月列表的查询方法
	 * 
	 * @param request
	 *            HttpServletRequest的对象
	 * @param sEcho
	 *            datatables的被请求次数
	 * @param firstIndex
	 *            起始页数
	 * @param pageSize
	 *            每页多少条记录
	 * @return page DataTablesPage对象的实例
	 * 
	 * @author lijing
	 * @lastModified lijing 2014-07-25 9:30:50
	 */
	@RequestMapping("/findNongPassBreedBySearch")
	@ResponseBody
	public DataTablesPage findNongPassBreedBySearch(
			@RequestParam("sEcho") Integer sEcho,
			@RequestParam("iDisplayStart") Integer firstIndex,
			@RequestParam("iDisplayLength") Integer pageSize,
			HttpServletRequest request) {
		// 查询条件
		String type = dataDict.getCodeVal("AgroNBreedTbType", "S1");
		String projectId = (String) request.getParameter("projectId");
		List<Object> params = new ArrayList<Object>();
		List<BreedAgriculture> queryList = this.cultivateAndBreedService.findNongPassBreedBySearch(
				firstIndex / pageSize + 1, pageSize, projectId, Integer.valueOf(type));
		
		DataTablesPage page = new DataTablesPage(sEcho, queryList, new Long(queryList.size()), new Integer(type));
		return page;
	}
	
	/**
	 * 农养殖业未来12月列表的查询方法
	 * 
	 * @param request
	 *            HttpServletRequest的对象
	 * @param sEcho
	 *            datatables的被请求次数
	 * @param firstIndex
	 *            起始页数
	 * @param pageSize
	 *            每页多少条记录
	 * @return page DataTablesPage对象的实例
	 * 
	 * @author lijing
	 * @lastModified lijing 2014-07-25 9:30:50
	 */
	@RequestMapping("/findNongFutureBreedBySearch")
	@ResponseBody
	public DataTablesPage findNongFutureBreedBySearch(
			@RequestParam("sEcho") Integer sEcho,
			@RequestParam("iDisplayStart") Integer firstIndex,
			@RequestParam("iDisplayLength") Integer pageSize,
			HttpServletRequest request) {
		// 查询条件
		String type = dataDict.getCodeVal("AgroNBreedTbType", "S2");
		String projectId = (String) request.getParameter("projectId");
		List<Object> params = new ArrayList<Object>();
		List<BreedAgriculture> queryList = this.cultivateAndBreedService.findNongPassBreedBySearch(
				firstIndex / pageSize + 1, pageSize, projectId, Integer.valueOf(type));
		
		DataTablesPage page = new DataTablesPage(sEcho, queryList, new Long(queryList.size()), new Integer(type));
		return page;
	}
	
	@RequestMapping("/findRelativeCultivateMsg/{relativeId}")
	@ResponseBody
	public Result findRelativeCultivateMsg(@PathVariable Long relativeId){
		if(relativeId!=null){
			CultivateAgriculture ca = cultivateAndBreedService.findCaById(relativeId);
			if(ca!=null){
				return new Result(true, "", ca.getForecast());
			}else{
				return new Result(false, "关联对象不存在", null);
			}
		}else{
			return new Result(false, "关联id为空", null);
		}
		
		
	}
	
	@RequestMapping("/findRelativeBreedMsg/{relativeId}")
	@ResponseBody
	public Result findRelativeBreedMsg(@PathVariable Long relativeId){
		if(relativeId!=null){
			BreedAgriculture ba = cultivateAndBreedService.findBaById(relativeId);
			BigDecimal perdictNum = ba.getPredict().divide(new BigDecimal("100")).multiply(new BigDecimal(ba.getStockInitScale())).setScale(2, BigDecimal.ROUND_HALF_UP);//保留两位小数
			if(ba!=null){
				return new Result(true, "", perdictNum);
			}else{
				return new Result(false, "关联对象不存在", null);
			}
		}else{
			return new Result(false, "关联id为空", null);
		}
		
		
	}
 }
