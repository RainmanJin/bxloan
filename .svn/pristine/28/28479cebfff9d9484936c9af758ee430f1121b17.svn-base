package com.coamctech.bxloan.web.controller.sysmng;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coamctech.bxloan.commons.GlobalConstants;
import com.coamctech.bxloan.commons.controller.BaseController;
import com.coamctech.bxloan.commons.result.DataTablesPage;
import com.coamctech.bxloan.commons.result.Result;
import com.coamctech.bxloan.datadict.CodeData;
import com.coamctech.bxloan.datadict.DataDict;
import com.coamctech.bxloan.entity.EcOrgDepartment;
import com.coamctech.bxloan.entity.Product;
import com.coamctech.bxloan.entity.ProductConfig;
import com.coamctech.bxloan.service.model.ecOrgDep.EcOrgDepartmentVO;
import com.coamctech.bxloan.service.model.tipmsg.MsgResult;
import com.coamctech.bxloan.service.sysmng.ProductMngService;
import com.coamctech.bxloan.web.vo.sysmng.ProductMngVO;
import com.google.common.base.Function;
import com.google.common.collect.Lists;

@Controller
@RequestMapping(GlobalConstants.PRODUCT_MNG)
public class ProductMngController extends BaseController{
	@Autowired
	private DataDict dataDict;
	@Autowired
	private ProductMngService productMngService;
	
	@RequestMapping
	public String index(Model model) {
		Collection<CodeData> repaymentModes = dataDict.getOneType("RepaymentMode");
		model.addAttribute("repaymentModes", repaymentModes);
		return GlobalConstants.SYS_MNG + "/productmng/main";
	}
	
	/**
	 * 产品配置详细信息
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/detail/{id}")
	public String detail(@PathVariable("id") Long id, Model model) {
		Product product = productMngService.getProductByProductCd(id);
		ProductConfig productConfig = productMngService.getConfByProductCd(product.getProductCd());
		List<EcOrgDepartmentVO> orgDepartment = productMngService.getEcOrgDep();
		if(product!=null && !"".equals(product.getOrgId())){
			String[] selectedOrg = product.getOrgId().split(",");
			int n = 0;
			for(EcOrgDepartmentVO vo:orgDepartment){
				for(String s:selectedOrg){
					if(vo.getId()!=null && vo.getId().toString().equals(s)){
						vo.setIsSelected(true);
						n++;
						break;
					}
				}
				if(n==selectedOrg.length){
					break;
				}
			}
		}
		if(productConfig!=null){
			model.addAttribute("productConfig", productConfig);
			model.addAttribute("product", product);
		}
		model.addAttribute("viewOrEdit", "view");
		model.addAttribute("orgDepartment", orgDepartment);
		return GlobalConstants.SYS_MNG + "/productmng/detail";
	}
	/**
	 * 产品配置详细信息
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, Model model) {
		Product product = productMngService.getProductByProductCd(id);
		ProductConfig productConfig = productMngService.getConfByProductCd(product.getProductCd());
		List<EcOrgDepartmentVO> orgDepartment = productMngService.getEcOrgDep();
		if(product!=null && StringUtils.isNotEmpty(product.getOrgId())){
			String[] selectedOrg = product.getOrgId().split(",");
			int n = 0;
			for(EcOrgDepartmentVO vo:orgDepartment){
				for(String s:selectedOrg){
					if(vo.getId()!=null && vo.getId().toString().equals(s)){
						vo.setIsSelected(true);
						n++;
						break;
					}
				}
				if(n==selectedOrg.length){
					break;
				}
			}
		}
		if(productConfig!=null){
			model.addAttribute("productConfig", productConfig);
		}
		model.addAttribute("product", product);
		model.addAttribute("orgDepartment", orgDepartment);
		model.addAttribute("viewOrEdit", "edit");
		return GlobalConstants.SYS_MNG + "/productmng/detail";
	}
	
	/**
	 * 跳转到添加产品配置
	 * @param model
	 * @return
	 */
	@RequestMapping("/add")
	public String add(Model model) {
		List<EcOrgDepartmentVO> orgDepartment = productMngService.getEcOrgDep();
		model.addAttribute("orgDepartment", orgDepartment);
		return GlobalConstants.SYS_MNG + "/productmng/detail";
	}
	
	/**
	 * 添加产品配置
	 * @param model
	 * @return
	 */
	@RequestMapping("/save")
	@ResponseBody
	public Result save(com.coamctech.bxloan.service.model.sysmng.ProductMngVO form ,Model model) {
		try {
			MsgResult result = productMngService.saveProductMng(form);
			return success("产品信息保存成功!", result.getCode());
		} catch (Exception e) {
			e.printStackTrace();
			return failure("产品信息保存失败！", null);
		}
	}
	
	/**
	 * 查询产品配置数据列表
	 * @param sEcho
	 * @param firstIndex
	 * @param pageSize
	 * @param request
	 * @param guarantorName
	 * @param guarantyName
	 * @param guarantyStatusCd
	 * @param guaranteeMode
	 * @param projectId
	 * @return
	 */
	@RequestMapping("/findBySearch")
	@ResponseBody
	public DataTablesPage findBySearch(@RequestParam("sEcho") Integer sEcho,
			@RequestParam("iDisplayStart") Integer firstIndex,
			@RequestParam("iDisplayLength") Integer pageSize,
			HttpServletRequest request, String productName,
			String orgIds, Model model) {
		Page<Object[]> page = productMngService.findBySearch(
				(firstIndex / pageSize), pageSize,  productName,orgIds);
		// 结果处理
		DataTablesPage dtPage = new DataTablesPage(sEcho, page);
		dtPage.setAaData(Lists.transform(page.getContent(),
				new Function<Object[], ProductMngVO>() {
					@Override
					public ProductMngVO apply(Object[] objs) {
						return new ProductMngVO(objs);
					}
				}));
		return dtPage;
	}
	/**
	 * 获取适用机构
	 * @return
	 */
	@RequestMapping("/getSelectOrg")
	@ResponseBody
	public Result getSelectOrg() {
		List<EcOrgDepartmentVO> orgDepartment = null;
		try {
			orgDepartment = productMngService.getEcOrgDep();
			return success("获取机构信息成功!", orgDepartment);
		} catch (Exception e) {
			return failure("获取机构信息失败！", null);
		}
	}
	
	@RequestMapping("/findByProjectId")
	@ResponseBody
	public ProductConfig findByProjectId(Long projectId) {
		return productMngService.findByProjectId(projectId);
	}
}
