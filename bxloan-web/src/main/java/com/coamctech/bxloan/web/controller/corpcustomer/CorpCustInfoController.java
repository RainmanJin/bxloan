package com.coamctech.bxloan.web.controller.corpcustomer;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coamctech.bxloan.commons.result.DataTablesPage;
import com.coamctech.bxloan.commons.result.Result;
import com.coamctech.bxloan.commons.utils.DateTools;
import com.coamctech.bxloan.entity.Address;
import com.coamctech.bxloan.entity.CorporationCustomer;
import com.coamctech.bxloan.service.corpcustomer.CorporationCustomerService;
import com.coamctech.bxloan.service.model.NationAreaVo;
import com.coamctech.bxloan.service.model.tipmsg.MsgResult;
import com.coamctech.bxloan.service.model.tipmsg.ResultEnums;
import com.coamctech.bxloan.service.sysmng.ICommonBizNumberBS;
import com.coamctech.bxloan.web.security.ShiroUser;
import com.coamctech.bxloan.web.vo.corpcustomer.CorpAddressVo;
import com.coamctech.bxloan.web.vo.corpcustomer.CorpDocumentVo;
import com.coamctech.bxloan.web.vo.customermng.DocumentUploadVO;
import com.google.common.base.Function;
import com.google.common.collect.Lists;

/**
 * 企业客户信息
 */
@Controller
@RequestMapping("/userMngInfo")
public class CorpCustInfoController {
	//private final String ADDRESS_TYPE_COMMUNICATION="1";
	/**
	 * 地址类型为注册
	 */
	private final String ADDRESS_TYPE_REGISTRATION="2";
	@Autowired
	private CorporationCustomerService corporationCustomerService;
	@Autowired
	private ICommonBizNumberBS commonBizNumberBS;

	/**
	 * 地址列表
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/addrList")
	public DataTablesPage addrList(@RequestParam("partyId") Long partyId,
			@RequestParam("sEcho") Integer sEcho,
			@RequestParam("iDisplayStart") Integer firstIndex,
			@RequestParam("iDisplayLength") Integer pageSize) {
		Page<Object[]> wfPage = corporationCustomerService.addressPageList(pageSize,
				firstIndex / pageSize, partyId);
		DataTablesPage dtPage = new DataTablesPage(sEcho, wfPage);
		dtPage.setAaData(Lists.transform(wfPage.getContent(), new Function<Object[], CorpAddressVo>() {
			@Override
			public CorpAddressVo apply(Object[] input) {
				return new CorpAddressVo(input);
			}
		}));
		return dtPage;
	}

	@ResponseBody
	@RequestMapping("/loadNaData/{code}")
	public List<NationAreaVo> loadNationArea(@PathVariable("code") String code) {
		return corporationCustomerService.findNationAreaList(code == null
				|| StringUtils.equalsIgnoreCase("root", code) ? null : code);
	}

	/**
	 * 获取地址
	 * 
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getAddr/{id}")
	public Result getAddr(@PathVariable("id") Long id) {
		Address address = null;
		try {
			address = corporationCustomerService.getAddr(id);
		} catch (Exception e) {
			return new Result(false, "获取地址信息失败",null);
		}
		return new Result(true, "", address);
	}

	/**
	 * 保存
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/saveAddr")
	public Result saveAddr(Address address) {
		try {
			if (address == null) {
				return new Result(false, "参数错误",null);
			}
			if(ADDRESS_TYPE_REGISTRATION.equals(address.getAddressTypeCd())
					&&corporationCustomerService.findCountAddr(address.getPartyId(), address.getAddressId(),ADDRESS_TYPE_REGISTRATION)>0){
				return new Result(false, "注册地址只能有一个",null);
			}
			ShiroUser user = (ShiroUser) SecurityUtils.getSubject()
					.getPrincipal();
			if (user != null) {
				address.setCustomerNum(user.getUsernum());
			}
			address = corporationCustomerService.saveAddr(address);
		} catch (Exception e) {
			return new Result(false, "保存失败",null);
		}
		return new Result(true, "", address);
	}

	/**
	 * 删除地址
	 * 
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/delAddr/{id}")
	public Result deleteAddr(@PathVariable("id") Long id) {
		try {
			corporationCustomerService.deleteAddr(id);
		} catch (Exception e) {
			return new Result(false, "删除失败",null);
		}
		return new Result(true);
	}

	/**
	 * 相关文档列表
	 * 
	 * @param partyId
	 * @param sEcho
	 * @param firstIndex
	 * @param pageSize
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/docsList")
	public DataTablesPage docsList(@RequestParam("partyId") Long partyId,
			@RequestParam("sEcho") Integer sEcho,
			@RequestParam("iDisplayStart") Integer firstIndex,
			@RequestParam("iDisplayLength") Integer pageSize,
			HttpServletRequest request) {
		String docName = request.getParameter("docName");
		String docContentType = request.getParameter("docContentType");
		Page<Object[]> wfPage = corporationCustomerService.docIndexPageList(
				pageSize, firstIndex / pageSize, partyId, docName, docContentType, "01", "1",
				null);
		DataTablesPage dtPage = new DataTablesPage(sEcho, wfPage);
		dtPage.setAaData(Lists.transform(wfPage.getContent(), new Function<Object[], CorpDocumentVo>() {
			@Override
			public CorpDocumentVo apply(Object[] input) {
				return new CorpDocumentVo(input);
			}
		}));
		return dtPage;
	}

	/**
	 * 获取地址
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/beforeUpload/{partyId}")
	@ResponseBody
	public DocumentUploadVO getDoc(@PathVariable("partyId") long partyId) {
		DocumentUploadVO docUploadVo = new DocumentUploadVO();
		docUploadVo.setPartyId(String.valueOf(partyId));
		ShiroUser curUser = (ShiroUser) SecurityUtils.getSubject()
				.getPrincipal();
		CorporationCustomer cc = corporationCustomerService.findById(partyId);
		docUploadVo.setUserNum(curUser.getId().toString());
		//modify by HWL start 20150701 修改为登录机构
		docUploadVo.setCreateOrgCd(curUser.getLogOrgid().toString());
		//modify by HWL end 20150701 修改为登录机构
		docUploadVo.setCreateDateTime(DateTools.getSystemDate("yyyy-MM-dd"));
		docUploadVo.setCustomerNum(cc.getCustomerNum());
		docUploadVo.setBizNum("");
		docUploadVo.setBizId("-1");
		docUploadVo.setDocumentType("01");
		docUploadVo.setCreateUserName(curUser.getName());
		docUploadVo.setFileTypes("doc,docx,xls,xlsx,pdf,jpg,gif,png,rar");
		docUploadVo.setCreateUserNum(curUser.getId().toString());
//		docUploadVo.setCreateUserNum(curUser.getUsernum());
		docUploadVo.setCreateTypeCd("01");
		docUploadVo.setDocumentNum(commonBizNumberBS.generateDocumentNum(
				cc.getCustomerNum(), "01"));//客户
		return docUploadVo;
	}

	/**
	 * 删除地址
	 * 
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/delDoc/{id}")
	public Result deleteDoc(@PathVariable("id")long id) {
		ShiroUser curUser = (ShiroUser) SecurityUtils.getSubject()
				.getPrincipal();
		try {
			MsgResult msgResult= corporationCustomerService.updateDocumentIndexStatus(id, String.valueOf(curUser.getId()));
			if(!"success".equals(msgResult.getCode())){
				return new Result(false, "删除失败", msgResult);
			}
		} catch (Exception e) {
			return new Result(false, "删除失败", MsgResult.getMsgResult(ResultEnums.DB_ERROR));
		}
		return new Result(true, "删除成功", null);
	}
}
