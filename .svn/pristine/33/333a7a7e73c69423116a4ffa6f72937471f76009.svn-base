package com.coamctech.bxloan.service.sysmng.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coamctech.bxloan.commons.GlobalConstants;
import com.coamctech.bxloan.commons.dynamicquery.DynamicQuery;
import com.coamctech.bxloan.commons.utils.NumberUtil;
import com.coamctech.bxloan.dao.EcOrgDepartmentDao;
import com.coamctech.bxloan.dao.ProductConfigDao;
import com.coamctech.bxloan.dao.ProductDao;
import com.coamctech.bxloan.dao.ProductWfConfigDao;
import com.coamctech.bxloan.dao.ProjectApplicationDao;
import com.coamctech.bxloan.dao.WfProductConfigRelationDao;
import com.coamctech.bxloan.entity.EcOrgDepartment;
import com.coamctech.bxloan.entity.Product;
import com.coamctech.bxloan.entity.ProductConfig;
import com.coamctech.bxloan.entity.ProductWfConfig;
import com.coamctech.bxloan.entity.ProjectApplication;
import com.coamctech.bxloan.entity.WfProductConfigRelation;
import com.coamctech.bxloan.service.model.ecOrgDep.EcOrgDepartmentVO;
import com.coamctech.bxloan.service.model.sysmng.ProductMngVO;
import com.coamctech.bxloan.service.model.tipmsg.MsgResult;
import com.coamctech.bxloan.service.sysmng.ProductMngService;

@Transactional
@Service
public class ProductMngServiceImpl implements ProductMngService {

	private final int BASE_RATE_MULTIPLE=Integer.valueOf(4);//央行基准利率默认4倍
	@Autowired
	private DynamicQuery dynamicQuery;
	@Autowired
	private ProductConfigDao productConfigDao;
	@Autowired
	private ProductDao productDao;
	@Autowired
	private EcOrgDepartmentDao ecOrgDepartmentDao;
	@Autowired
	private ProductWfConfigDao productWfConfigDao;
	@Autowired
	private ProjectApplicationDao projectApplicationDao;
	@Autowired
	private WfProductConfigRelationDao wfProductConfigRelationDao;
	
	/**
	 * 根据条件查询产品配置信息数据
	 * @param pageNumber
	 * @param pageSize
	 * @param productName
	 * @param orgIds 
	 * @return
	 */
	@Override
	public Page<Object[]> findBySearch(Integer pageNumber, Integer pageSize,
			String productName, String orgIds) {
		StringBuffer strBuffer = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		strBuffer
				.append("select  pd.product_cd,pd.customer_type,pd.product_name,pd.org_id,pd.product_control_type_cd ")
				.append("from product pd where 1=1 ");
		int paramsIndex = 1;// 参数索引
		if(StringUtils.isNotBlank(productName)){
			strBuffer.append(" and pd.product_name like ?"+paramsIndex++);
			params.add("%"+productName+"%");
		}
		if(StringUtils.isNotBlank(orgIds)){
			strBuffer.append(" and pcf.ORG_ID in?"+paramsIndex++);
			params.add(Long.valueOf(orgIds));
		}
		strBuffer.append(" order by pd.product_cd desc");
		Page<Object[]> page = dynamicQuery.nativeQuery(new PageRequest(pageNumber, pageSize),
				strBuffer.toString(), params.toArray());
		StringBuffer sql = null;
		StringBuffer strName = null;
		for(Object[] o:page.getContent()){
			if(o[3]!=null && !"".equals(o[3].toString())){
				sql = new StringBuffer("select name from  ec_org_department where id in(");
				sql.append(o[3]);
				sql.append(")");
				List<String> name = dynamicQuery.nativeQuery(String.class, sql.toString());
				strName = new StringBuffer();
				for(String s :name){
					strName.append(","+s);
				}
				if(strName!=null){
					strName.deleteCharAt(0);
					o[3]=strName.toString();
				}
			}
		}
		return page;
	}
	
	/**
	 * 根据id获取数据
	 * @param id
	 * @return
	 */
	@Override
	public ProductConfig getConfByProductCd(Long productCd) {
		List<ProductConfig> pcs = productConfigDao.getConfByProductCd(productCd);
		ProductConfig productConfig = new ProductConfig();
		if(pcs!=null && pcs.size()>0){
			productConfig = pcs.get(0);
		}
		return productConfig;
	}

	/**
	 * 保存产品 配置信息
	 * @param form
	 * @return
	 */
	@Override
	public MsgResult saveProductMng(ProductMngVO form) {
		if(form!=null){
			//处理product
			Product product = null;
			if(form.getProductCd()!=null){
				product = productDao.findByProductCd(form.getProductCd());
			}
			if(product==null){
				product = new Product();
			}
			BeanUtils.copyProperties(form, product,"productCd");
			if(form.getMinApplyAmt()!=null){
				product.setMinApplyAmt(form.getMinApplyAmt().doubleValue());
			}
			if(form.getMaxApplyAmt()!=null){
				product.setMaxApplyAmt(form.getMaxApplyAmt().doubleValue());
			}
			product.setProductControlTypeCd(form.getIsStart());
			if(form.getParentProductCd()==201){//易贷
				form.setWfCode(GlobalConstants.EL_WF_TYPE_30);
			}else if(form.getParentProductCd()==63){//微贷
				form.setWfCode(GlobalConstants.WD_WF_TYPE_40);
			}
			product.setProductLevel("2");
			productDao.save(product);
			
			//处理productConfig及productWfConfig
			List<ProductConfig> delProConfs = new ArrayList<ProductConfig>();
			if(product.getProductCd()!=null){
				delProConfs = productConfigDao.getConfByProductCd(product.getProductCd());
			}
			List<ProductWfConfig> entitiesWf = new ArrayList<ProductWfConfig>();
			updateProConf(form, product, delProConfs, entitiesWf);
			for(ProductConfig pf:delProConfs){
				productWfConfigDao.deleteProWfConf(pf.getPcId());
			}
			productConfigDao.delete(delProConfs);
			return MsgResult.getMsgResult(product.getProductCd().toString(), null);
		}else{
			return MsgResult.getMsgResult("0", null);
		}
	}

	/**
	 * 更新配置表数据及相关流程信息
	 * @param form
	 * @param product
	 * @param delProConfs
	 * @param entitiesWf
	 */
	private void updateProConf(ProductMngVO form, Product product,
			List<ProductConfig> delProConfs, List<ProductWfConfig> entitiesWf) {
		Boolean isNew = false;
		if(StringUtils.isNotBlank(form.getOrgId())){
			ProductConfig productConfig = null;
			String[] orgIds = form.getOrgId().split(",");
			for(String s:orgIds){
				isNew = false;
				productConfig = null;
				for(ProductConfig delPcf:delProConfs){
					if(delPcf!=null && s.equals(delPcf.getOrgId().toString())){
						productConfig = delPcf;
						delProConfs.remove(delPcf);
						if(form.getWfCode()!=null && !form.getWfCode().equals(delPcf.getWfCode())){
							isNew = true;
							productWfConfigDao.deleteProWfConf(delPcf.getPcId());
						}
						break;
					}
				}
				if(productConfig==null){
					isNew = true;
					productConfig = new ProductConfig();
					productConfig.setCreateDate(new Date());
				}
				BeanUtils.copyProperties(form, productConfig,"pcId");
				//如果贷款期限模式为空的话，产品配置表中的ReplyPeriodUnit设置为空
				if(StringUtils.isEmpty(form.getLoanTermMode())){
					productConfig.setReplyPeriodUnit(null);
				}
				if(GlobalConstants.LOAN_TERM_MODE.equals(form.getLoanTermMode())){
					productConfig.setMinLoanTerm(form.getMinLoanTerm1());
				} 
				if("3".equals(form.getLoanTermMode())){
					productConfig.setReplyPeriodUnit("2");
				}
				productConfig.setOrgId(Long.valueOf(s));
				productConfig.setUpdateDate(new Date());
				productConfig.setProductCd(product.getProductCd());
				//modify by HWL 20150812 start 央行基准利率倍数字段默认
				if(NumberUtil.isNullOrZero(productConfig.getRateMultiple())){
					productConfig.setRateMultiple(BASE_RATE_MULTIPLE);
				}
				//modify by HWL 20150812 end
				productConfigDao.save(productConfig);
				//处理productWfConfig
				if(isNew){
					createProWfConf(entitiesWf, productConfig);
				}
			}
			productWfConfigDao.save(entitiesWf);
		}
	}

	/**
	 * 创建ProductWfConfig对象
	 * @param entitiesWf
	 * @param productConfig
	 */
	private void createProWfConf(List<ProductWfConfig> entitiesWf,
			ProductConfig productConfig) {
		ProductWfConfig productWfConfig = null;
		if(GlobalConstants.WD_WF_TYPE_40.equals(productConfig.getWfCode())){//邦微贷流程
			productWfConfig = 
					new ProductWfConfig(
							productConfig.getPcId(), 
							GlobalConstants.WD_WF_TASK_ID_14, 
							GlobalConstants.WD_WF_ROLE_CONF_19, 
							GlobalConstants.WD_WF_ORG_ID_01);
			entitiesWf.add(productWfConfig);
			productWfConfig = new ProductWfConfig(
					productConfig.getPcId(), 
					GlobalConstants.WD_WF_TASK_ID_16, 
					GlobalConstants.WD_WF_ROLE_CONF_02, 
					productConfig.getOrgId());
			entitiesWf.add(productWfConfig);
			
		} else if(GlobalConstants.EL_WF_TYPE_30.equals(productConfig.getWfCode())){//邦易贷流程
			productWfConfig = 
					new ProductWfConfig(
							productConfig.getPcId(), 
							GlobalConstants.EL_WF_TASK_ID_10, 
							GlobalConstants.WD_WF_ROLE_CONF_16, 
							productConfig.getOrgId());
			entitiesWf.add(productWfConfig);
			productWfConfig = 
					new ProductWfConfig(
							productConfig.getPcId(), 
							GlobalConstants.EL_WF_TASK_ID_11, 
							GlobalConstants.WD_WF_ROLE_CONF_15, 
							GlobalConstants.WD_WF_ORG_ID_01);
			entitiesWf.add(productWfConfig);
			productWfConfig = 
					new ProductWfConfig(
							productConfig.getPcId(), 
							GlobalConstants.EL_WF_TASK_ID_12, 
							GlobalConstants.WD_WF_ROLE_CONF_02, 
							productConfig.getOrgId());
			entitiesWf.add(productWfConfig);
		}
	}

	/**
	 * 通过Product_cd 查找产品
	 * @param id
	 * @return
	 */
	@Override
	public Product getProductByProductCd(Long id) {
		return productDao.findOne(id);
	}

	/**
	 * 查询适用机构
	 * @return
	 */
	@Override
	public List<EcOrgDepartmentVO> getEcOrgDep() {
		List<EcOrgDepartment> orgDepartments = ecOrgDepartmentDao.getEcOrgDep();
		List<EcOrgDepartmentVO> ecOrgDepartmentVOs = new ArrayList<EcOrgDepartmentVO>();
		EcOrgDepartmentVO orgDepartmentVO = null;
		for(EcOrgDepartment od:orgDepartments){
			orgDepartmentVO = new EcOrgDepartmentVO();
			BeanUtils.copyProperties(od, orgDepartmentVO);
			ecOrgDepartmentVOs.add(orgDepartmentVO);
		}
		return ecOrgDepartmentVOs;
	}

	@Override
	public ProductConfig findByProjectId(Long projectId) {
		ProjectApplication p = projectApplicationDao.findOne(projectId);
		return productConfigDao.findByProductCdAndOrgId(Long.valueOf(p.getProductType()), Long.valueOf(p.getApplyOrg()));
	}
	
	@Override
	public ProductConfig findUniqueProductConfig(Long orgId, Long productCd, String workFlowType, String projectBusinessType){
		//获取流程与产品配置关系实体
		WfProductConfigRelation wfProductConfigRelation = wfProductConfigRelationDao.findUniqueWfProductConfigRelation(orgId, productCd,
						workFlowType, projectBusinessType);
		if(wfProductConfigRelation == null){
			return null;
		}
		//获取产品配置信息
		ProductConfig productConfig = null;
		Long productConfigId = wfProductConfigRelation.getProductConfigId();
		if(wfProductConfigRelation != null && productConfigId != null){
			productConfig = productConfigDao.findOne(productConfigId);
		}
		return productConfig;
	}
	
	@Override
	public ProductConfig findUniqueProductConfig(Long orgId, Long productCd, String workFlowType){
		//获取流程与产品配置关系实体
		WfProductConfigRelation wfProductConfigRelation = wfProductConfigRelationDao.findUniqueWfProductConfigRelation(
				orgId, productCd, workFlowType);
		if(wfProductConfigRelation == null){
			return null;
		}
		//获取产品配置信息
		ProductConfig productConfig = null;
		Long productConfigId = wfProductConfigRelation.getProductConfigId();
		if(wfProductConfigRelation != null && productConfigId != null){
			productConfig = productConfigDao.findOne(productConfigId);
		}
		return productConfig;
	}
}
