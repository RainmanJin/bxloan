package com.coamctech.bxloan.web.controller.bizapply;

import javax.servlet.http.HttpServletRequest;

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
import com.coamctech.bxloan.entity.OtherIncome;
import com.coamctech.bxloan.service.aand.OtherIncomeService;

@Controller
@RequestMapping("addOtherIncome")
public class OtherIncomeController extends BaseController{

	@Autowired
	private OtherIncomeService otherIncomeService;
	
	@RequestMapping
	public String index(){
		return "businessapplicationwd/agriculture/familyConsume_agr";
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
		String futurePastType = request.getParameter("futurePastType");
		Long projectId = Long.parseLong(project);
		pageSize=pageSize<1?2000:pageSize;
		Page<OtherIncome> page = otherIncomeService.findBySearch(search, firstIndex / pageSize + 1, pageSize, type,projectId,futurePastType);
		return new DataTablesPage(sEcho, page);
	}
	
	/**
	 * 添加生产区域信息
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public Result add(@ModelAttribute OtherIncome otherIncome){	
		otherIncomeService.save(otherIncome);
		return success();
	}
	
	/**
	 * 删除生产区域信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	@ResponseBody
	public Result delete(@PathVariable("id") String id){
		otherIncomeService.delete(Long.parseLong(id));
		return success();
	}
	/**
	 * 修改生产区域信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/get/{id}", method = RequestMethod.POST)
	@ResponseBody
	public OtherIncome get(@PathVariable("id") String id){
		return otherIncomeService.get(Long.parseLong(id));
	}
	
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public Result update(@PathVariable("id") String id){
		OtherIncome other = otherIncomeService.get(Long.valueOf(id));
		if(other!=null){
			this.otherIncomeService.saveOtherIncome(other);;
		}else{
			return new Result(false);
		}
		return new Result(true,"修改成功",null);
	}	
}
