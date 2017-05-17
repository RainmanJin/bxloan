package com.coamctech.bxloan.web.controller.sysmng;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coamctech.bxloan.commons.GlobalConstants;
import com.coamctech.bxloan.commons.controller.BaseController;
import com.coamctech.bxloan.commons.result.DataTablesPage;
import com.coamctech.bxloan.commons.result.Result;
import com.coamctech.bxloan.datadict.CodeData;
import com.coamctech.bxloan.datadict.DataDict;
import com.coamctech.bxloan.entity.ProductPrice;
import com.coamctech.bxloan.service.sysmng.ProductPriceService;

/**
 * @Description: 产品定价controller类
 * @author syy
 * @version V1.0
 * @Date 2014年7月22日
 */
@Controller
@RequestMapping(GlobalConstants.PRODUCT_PRICE)
public class ProductPriceController extends BaseController {

	@Autowired
	private ProductPriceService productPriceService;

	@Autowired
	private DataDict dataDict;

	@RequestMapping
	public String index(Model model) {
		List<Object[]> list = productPriceService.findProduct();
		Collection<CodeData> repaymentModes = dataDict
				.getOneType("RepaymentMode");
		model.addAttribute("list", list);
		model.addAttribute("repaymentModes", repaymentModes);
		return GlobalConstants.SYS_MNG + "/" + GlobalConstants.PRODUCT_PRICE;
	}

	/**
	 * Description 查询产品定价信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/findBySearch")
	@ResponseBody
	public DataTablesPage findBySearch(@RequestParam("sEcho") Integer sEcho,
			@RequestParam("iDisplayStart") Integer firstIndex,
			@RequestParam("iDisplayLength") Integer pageSize) {
		Page<ProductPrice> page = productPriceService.findBySearch(firstIndex
				/ pageSize + 1, pageSize);
		return new DataTablesPage(sEcho, page);
	}

	/**
	 * Description 添加产品定价
	 * 
	 * @return
	 */
	@RequestMapping("/save")
	@ResponseBody
	public Result save(ProductPrice form) {
		ProductPrice productPrice = productPriceService.findByLoanProduct(form);
		if (productPrice != null) {
			return failure("已设置此产品，请选择其他产品！");
		} else {
			try {
				form.setOverdueRepayment(new BigDecimal("0"));
				if (form.getRate() != null) {
					form.setRate(form.getRate().divide(new BigDecimal("100")));
				}
				if (form.getOverdueRate() != null) {
					form.setOverdueRate(form.getOverdueRate().divide(
							new BigDecimal("100")));
				}
				if (form.getPerculNegoRate() != null) {
					form.setPerculNegoRate(form.getPerculNegoRate().divide(
							new BigDecimal("100")));
				}
				if (form.getEarlyRepayment() != null) {
					form.setEarlyRepayment(form.getEarlyRepayment().divide(
							new BigDecimal("100")));
				}
				productPriceService.save(form);
				return success();
			} catch (Exception e) {
				e.printStackTrace();
				return failure("保存失败！");
			}

		}
	}

	/**
	 * Description 修改产品定价
	 * 
	 * @return ProductPrice
	 */
	@RequestMapping("/get")
	@ResponseBody
	public ProductPrice get(Long id) {
		ProductPrice p = productPriceService.get(id);
		if (p.getRate() != null) {
			p.setRate(p.getRate().multiply(new BigDecimal("100")));
		}
		if (p.getOverdueRate() != null) {
			p.setOverdueRate(p.getOverdueRate().multiply(new BigDecimal("100")));
		}
		if (p.getPerculNegoRate() != null) {
			p.setPerculNegoRate(p.getPerculNegoRate().multiply(
					new BigDecimal("100")));
		}
		if (p.getEarlyRepayment() != null) {
			p.setEarlyRepayment(p.getEarlyRepayment().multiply(
					new BigDecimal("100")));
		}

		return p;
	}

	/**
	 * Description 产品定价
	 * 
	 * @return ProductPrice
	 */
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	@ResponseBody
	public Result delete(@PathVariable("id") Long id) {
		productPriceService.delete(id);
		return success();
	}
}
