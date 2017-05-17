package com.coamctech.bxloan.web.controller.bizapply;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coamctech.bxloan.commons.controller.BaseController;
import com.coamctech.bxloan.commons.result.DataTablesPage;
import com.coamctech.bxloan.commons.result.Result;
import com.coamctech.bxloan.entity.GainDistribution;
import com.coamctech.bxloan.service.aand.GainDistributionService;
import com.coamctech.bxloan.service.model.aand.GainDistributionVo;

@Controller
@RequestMapping("nyGainDistribution")
public class NyGainDistributionController extends BaseController{
	
	@Autowired
	private GainDistributionService gainDistributionService;
	
	@RequestMapping
	public String index(){
		return "businessapplicationwd/agriculture/nd_otherny";
	}
	
	/**
	 * 显示数据,查找其他收入
	 * @return
	 */
	@RequestMapping("/findBySearch")
	@ResponseBody
	public DataTablesPage findBySearch(@RequestParam(value="sSearch",required=false) String search, @RequestParam("sEcho") Integer sEcho, 
			@RequestParam("iDisplayStart") Integer firstIndex,
			@RequestParam("iDisplayLength") Integer pageSize, 
			HttpServletRequest request) {
		String type = request.getParameter("type");
		String project = request.getParameter("projectId");
		Long projectId = Long.parseLong(project);
		pageSize=pageSize<1?2000:pageSize;
		Page<GainDistribution> page = gainDistributionService.findBySearch(search, firstIndex / pageSize + 1, pageSize, type,projectId);
		return new DataTablesPage(sEcho, page);
	}
	
	/**
	 * 添加生产区域信息
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public Result add(@ModelAttribute GainDistributionVo vo){
		try {
			gainDistributionService.save(vo);
			return success();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return failure("保存失败！");
	}
	
	/**
	 * 删除生产区域信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	@ResponseBody
	public Result delete(@PathVariable("id") String id){
		gainDistributionService.delete(Long.parseLong(id));
		return success();
	}
	/**
	 * 修改生产区域信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/get/{id}", method = RequestMethod.POST)
	@ResponseBody
	public GainDistribution get(@PathVariable("id") String id){
		return gainDistributionService.get(Long.parseLong(id));
	}
	
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public Result update(@PathVariable("id") String id, @ModelAttribute GainDistributionVo vo){
		GainDistribution pai = gainDistributionService.get(Long.valueOf(id));
		if(pai!=null){
			BeanUtils.copyProperties(vo, pai, "id");
			this.gainDistributionService.saveGainDistribution(pai);
		}else{
			return new Result(false);
		}
		return new Result(true,"修改成功",null);
	}	
}
