package com.coamctech.bxloan.service.bizapply;

import com.coamctech.bxloan.entity.Party;
import com.coamctech.bxloan.entity.Product;
import com.coamctech.bxloan.service.model.SessionUser;
import com.coamctech.bxloan.service.model.bizapply.BusinessApplicationWdVO;
import com.coamctech.bxloan.service.workflow.WorkFlowService.WorkFlowCode;
import com.coamctech.bxloan.service.workflow.WorkFlowService.WorkFlowNode;

/**   
 * 类名称：BizCreditApplicationService
 * 类描述 ：授信业务申请服务类
 * 创建人: 马峥  
 * 创建时间：2015年7月14日 下午5:07:01  
 * 修改人：马峥
 * 修改时间：2015年7月14日 下午5:07:01  
 * 修改备注：
 * 版本： V1.0
 */
public interface BizCreditApplicationService {
	/**
	 * 保存业务申请信息
	 * @param form
	 * @return
	 * @throws Exception
	 * 
	 * @author mazheng
	 * @lastModified mazheng 2015年8月13日 上午9:22:18
	 */
	public void saveMicroloanBusiness(BusinessApplicationWdVO form) throws Exception;
	
	/**
	 * 发起授信业务申请service 1、启动流程 2、任务处理 3、获取按钮 4、新建业务
	 * @param productCd 贷款产品代码
	 * @param party 参与人对象
	 * @param sessionUser 
	 * @param workFlowCode 流程类型代码
	 * @param workFlowNode 流程节点代码
	 * @param projectBusinessType 业务类型（codeType=ProjectBussinessType）
	 * @return
	 * @throws Exception
	 * 
	 * @author mazheng
	 * @lastModified mazheng 2015年8月13日 上午9:22:18
	 */
	public BusinessApplicationWdVO startMicroloanBusiness(
			Long productCd, 
			Party party, 
			SessionUser sessionUser, 
			WorkFlowCode workFlowCode, 
			WorkFlowNode workFlowNode,
			String projectBusinessType) throws Exception;
	
	/** 
	 * 组装任务主题
	 * 
	 * @param party 参与人对象
	 * @param product 贷款对象
	 * @param curUserOrgDesc 贷款机构描述
	 * @return 任务主题
	 * 
	 * @author mazheng
	 * @lastModified mazheng 2015年8月13日 上午9:22:18
	 */
	public String assembelTaskSubject(Party party, Product product, String curUserOrgDesc);
	
	/** 
	 * 提交申请之前，检查此业务是否已具备提交的条件
	 * @param projectId      业务id
	 * @param guaranteeMode  担保方式
	 * @param workFlowCode   流程类型代码
	 * @throws Exception
	 * 
	 * @author mazheng
	 * @lastModified mazheng 2015年8月13日 上午9:22:18
	 */
	public void checkBusinessInfoBeforeSendProcess(Long projectId,
			String guaranteeMode, String workFlowCode) throws Exception;
}
