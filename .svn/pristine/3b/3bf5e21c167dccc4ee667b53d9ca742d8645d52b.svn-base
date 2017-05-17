package com.coamctech.bxloan.web.controller.sysmng;

import static com.coamctech.bxloan.commons.GlobalConstants.PERSON_MNG;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coamctech.bxloan.commons.controller.BaseController;
import com.coamctech.bxloan.commons.result.DataTablesPage;
import com.coamctech.bxloan.commons.result.Result;
import com.coamctech.bxloan.commons.utils.DateTools;
import com.coamctech.bxloan.datadict.DataDict;
import com.coamctech.bxloan.entity.EcOrgDepartment;
import com.coamctech.bxloan.entity.EcOrgPerson;
import com.coamctech.bxloan.entity.IndustryType;
import com.coamctech.bxloan.entity.PersonDetails;
import com.coamctech.bxloan.entity.User;
import com.coamctech.bxloan.service.sysmng.PersonMngService;
import com.coamctech.bxloan.service.usermng.UserMngService;
import com.coamctech.bxloan.web.security.ShiroUser;
import com.coamctech.bxloan.web.vo.sysmng.PersonDetailsVO;
/**
 * @Description:用户管理控制器
 * @author lijing
 * @version V1.0
 * @Date 2014年10月11日
 */
@Controller
@RequestMapping("/" + PERSON_MNG)
public class PersonMngController  extends BaseController{
	
	@Autowired
	private PersonMngService personMngService;
	@Autowired
	private DataDict dataDict;
	
	/**
	 * 获取person的表单信息，用于到页面显示
	 * @param curUserId 用户Id
	 * @return personDetailsVO
	 * @author lijing
	 * */
	@RequestMapping("/getPersonData")
	@ResponseBody
	public PersonDetailsVO getPersonData(@RequestParam String curUserId){
		PersonDetailsVO personData = new PersonDetailsVO();
		EcOrgPerson eoPerson = personMngService.findPersonById(curUserId);
		PersonDetails personDetails = personMngService.findPersonDetailsById(curUserId);
		try {
			BeanUtils.copyProperties(personData, eoPerson);
			BeanUtils.copyProperties(personData, personDetails);
			personData.setBeforeCertificateNum(personData.getCertificateNum());
			personData.setBeforeLogName(personData.getLogname());
			if(personDetails.getLastlogtime()!=null){
				personData.setLastlogtime(DateTools.dateToString(personDetails.getLastlogtime(), "yyyy年MM月dd日HH点mm分ss秒"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		personData.setId(new Long(curUserId));
		return personData;
	}
	
	@RequestMapping("/updatePerson")
	@ResponseBody
	public String updatePerson(@RequestParam String curUserId, @ModelAttribute PersonDetailsVO personDetailsVO){
		boolean checkLogNameInd = true;
		
		PersonDetails pd = personMngService.findPersonDetailsById(curUserId);
		EcOrgPerson eop = personMngService.findPersonById(curUserId);
		pd = this.combinePersonDetailFromVO(pd, personDetailsVO);
		eop = this.combineEOPersonFromVO(eop, personDetailsVO);
		if(StringUtils.isNotBlank(personDetailsVO.getBeforeLogName()) && personDetailsVO.getBeforeLogName().equals(personDetailsVO.getLogname())){
			checkLogNameInd = false;
		}
		if(StringUtils.isNotBlank(personDetailsVO.getBeforeCertificateNum()) && !personDetailsVO.getBeforeCertificateNum().equals(personDetailsVO.getCertificateNum())){
			List personDetailList = personMngService.findPersonDetailByCertificateNum(pd.getCertificateNum(), pd.getCertificateTypeCd());
			if (null!=personDetailList && !personDetailList.isEmpty()) {
	        	return "certificateNumHasRegistedError";
	        }
		}
	
		// 二级系统管理员只能由一级系统管理员修改
		List pRoleList = personMngService.findPersonConnRoleList(curUserId, personDetailsVO.getOrgid(), null, new Long("60150"));
		if(pRoleList!=null && !pRoleList.isEmpty()){
		ShiroUser curUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		
		}
		personMngService.savePersonDetail(pd);
		personMngService.saveEcOrgPerson(eop);
		
		return "success";
	}
	
	
	/**
	 * 根据用户id查询角色，返回page对象
	 * @param personId 用户id
	 * @param pageNumber 总页数
	 * @param pageSize 一页几条
	 * @param params 参数集
	 * */
	@RequestMapping("/findRolesBySearch")
	@ResponseBody
	public DataTablesPage findRolesBySearch (@RequestParam("sEcho") Integer sEcho, @RequestParam("iDisplayStart") Integer firstIndex, @RequestParam("iDisplayLength") Integer pageSize, HttpServletRequest request) {
		// 查询条件
		String personId = (String) request.getParameter("personId");
		List<Object> params = new ArrayList<Object>();
		Page queryList = personMngService.findRolesBySearch(personId, firstIndex
				/ pageSize + 1, pageSize, params);
		DataTablesPage page = new DataTablesPage(sEcho, queryList);
		return page;
	}

	/**
	 * 自定义标签中查找组织机构
	 * 
	 * @return List<EcOrgDepartment>对象 查找出的组织机构
	 * 
	 * @author lijing
	 * @lastModified lijing 2014-07-25 9:30:50
	 */
	@RequestMapping(value = "/getAllDept", method = RequestMethod.POST)
	@ResponseBody
	public List<EcOrgDepartment> getAllDept(){
		return personMngService.getAllDepartment();
	}
	
	/*************************************工具************************************/
	
	/**将VO装载到PersonDetails*/
	public PersonDetails combinePersonDetailFromVO(PersonDetails pd, PersonDetailsVO vo) {
		pd.setAddress(vo.getAddress());
		pd.setCertificateNum(vo.getCertificateNum());
		pd.setCertificateTypeCd(vo.getCertificateTypeCd());
		pd.setEmail(vo.getEmail());
		pd.setFixedphone(vo.getFixedphone());
		pd.setMobilephone(vo.getMobilephone());
		pd.setUnitPosition(vo.getUnitPosition());
		pd.setLastUpdateDate(new Date());
		return pd;
	}
	/**将VO装载到EcOrgPerson*/
	public EcOrgPerson combineEOPersonFromVO(EcOrgPerson eop, PersonDetailsVO vo) {
		eop.setLogname(vo.getLogname());
		eop.setName(vo.getName());
		eop.setDeptName(vo.getDeptname());
		eop.setOrgid(vo.getOrgid());
		eop.setState(vo.getState());
		return eop;
	}
}
