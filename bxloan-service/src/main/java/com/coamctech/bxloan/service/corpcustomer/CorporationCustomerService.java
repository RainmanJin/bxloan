package com.coamctech.bxloan.service.corpcustomer;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.coamctech.bxloan.entity.Address;
import com.coamctech.bxloan.entity.CorpCustomerRelaCorp;
import com.coamctech.bxloan.entity.CorpCustomerRelaPerson;
import com.coamctech.bxloan.entity.CorporationCustomer;
import com.coamctech.bxloan.entity.CustomerManagerTeam;
import com.coamctech.bxloan.service.model.CheckResult;
import com.coamctech.bxloan.service.model.NationAreaVo;
import com.coamctech.bxloan.service.model.corpcustomer.CorpAddCorpVO;
import com.coamctech.bxloan.service.model.corpcustomer.CorpAddPersonVO;
import com.coamctech.bxloan.service.model.corpcustomer.CorpCusSaveVO;
import com.coamctech.bxloan.service.model.corpcustomer.CorpRelaCorpDetailVO;
import com.coamctech.bxloan.service.model.corpcustomer.CorpRelaPersonDetailVO;
import com.coamctech.bxloan.service.model.tipmsg.MsgResult;

public interface CorporationCustomerService {

	/**
	 * 根据证件号查询客户数量
	 * @param certificateNum
	 * @return
	 * @author xc
	 */
	int findCorpCountByCertificateNum(String certificateNum);

	/**
	 * 查询莫个用户在黑名单出现次数
	 * @param certificateTypeCd
	 * @param certificateCd
	 * @return
	 * @author xc
	 */
	int findBlackListCount(String certificateTypeCd, String certificateCd);

	CheckResult checkCertificateNum(String certificateNum) throws Exception;
	
	/**
	 * 创建企业客户
	 * @param certificateNum
	 * @param customerName
	 * @param businessLicenseNum
	 * @param customerNum
	 * @param managerUserId
	 * @param managerOrgid
	 * @return partyId
	 * @author xc
	 * @param managerDepId 
	 */
	Long createCorpCustomer(String certificateNum, 
			String customerName,
			String businessLicenseNum,
			String customerNum,
			String managerUserId,
			String managerOrgid, String managerDepId);
	 
	 
	CorporationCustomer findById(Long partyId);
	 
	CorporationCustomer update(CorpCusSaveVO cus);
	
	CorporationCustomer enable(CorpCusSaveVO cus);

	CorpCustomerRelaPerson savePerson(CorpAddPersonVO vo);

	String valiPersonSaveVo(CorpAddPersonVO vo);

	/**
	 * 验证法人
	 * @param corp
	 * @return
	 * @author xc
	 */
	String valiRelaCorp(CorpAddCorpVO corp);

	/**
	 * 创建法人
	 * @param corp
	 * @return
	 * @author xc
	 */
	CorpCustomerRelaCorp createRelaCorp(CorpAddCorpVO corp);
	
	/**
	 * 检查持股总数
	 * @param share
	 * @param partyId
	 * @param personId
	 * @param corpId
	 * @return
	 * @author xc
	 */
	public boolean checkStockShares(BigDecimal share,Long partyId,Long personId,Long corpId);

	/**
	 * 检查实际控制人人数
	 * @param partyId
	 * @param personId 排除的人员ID
	 * @param corpId 排除的法人id
	 * @return
	 * @author xc
	 */
	public Integer findActControllerCount(Long partyId,Long personId,Long corpId);
	
	/**
	 * 检查法人代表人数
	 * @param partyId
	 * @param personId 排除的人员ID
	 * @return
	 * @author xc
	 */
	public Integer findLegalRepresentCount(Long partyId,Long personId);
	
	/**
	 * 检查股东数量
	 * @param partyId
	 * @param personId 排除的人员ID
	 * @return
	 * @author xc
	 */
	public Integer findStockHolderCount(Long partyId,Long personId,Long corpId);
	
	Page<Object[]> listActController(Long parseLong, Integer pageNo, Integer pageSize);
	
	Page<Object[]> listStockHolder(Long partyId, Integer pageNo,Integer pageSize);
	
	Page<Object[]> listHighManager(Long partyId, Integer pageNo,Integer pageSize);

	void removeRelaPerson(Long id);

	void removeRelaCorp(Long id);
	
	CorpCustomerRelaPerson findRelaPersonById(Long id);
	
	CorpCustomerRelaCorp findRelaCorpById(Long id);

	/**
	 * 企业客户生效前的校验
	 * @param cus
	 * @return
	 * @author xc
	 */
	public String validateEnableCorpCus(CorpCusSaveVO cus);
	
	/**
	 * @param code
	 * @return
	 */
	public List<NationAreaVo> findNationAreaList(String code);
	/**
	 * 分页查询地址信息
	 * @param pageSize
	 * @param pageNum
	 * @param partyId	企业id
	 * @return
	 */
	public Page<Object[]> addressPageList(int pageSize,int pageNum,Long partyId);
	/**
	 * 根据partyId查地址
	 * @param partyId
	 * @return List
	 * **/
	public List<Address> getAddressByPartyId(Long partyId);
	/**
	 * 获取地址
	 * @param id
	 * @return
	 */
	public Address getAddr(Long id);
	/**
	 * 保存
	 * @return
	 */
	public Address saveAddr(Address address);
	
	/**
	 * 查询地址数量
	 * @param addrId
	 * @param addrTypeCd	1:通讯地址，2：注册地址（只能有一条）
	 * @return 
	 */
	public long findCountAddr(Long partyId,Long addrId,String addrTypeCd);
	/**
	 * 删除地址
	 * @param id
	 * @return
	 */
	public void deleteAddr(Long id);
	/**
	 * 分页查询相关文档
	 * @param pageSize
	 * @param pageNum
	 * @param partyId	企业id
	 * @param docName	文档名称
	 * @param docType	文档类型
	 * @param docStatus	文档状态
	 * @param docNum	文档编号
	 * @param object 
	 * @return
	 */
	public Page<Object[]> docIndexPageList(int pageSize,int pageNum,Long partyId,String docName,String docContentType, String docType,String docStatus,String docNum);
	
	
	/**
	 * 修改documentIndex状态为2，表删除
	 * @param id
	 * @param userNum
	 * @return
	 */
	public MsgResult updateDocumentIndexStatus(Long id,String userNum);

	/**
	 * 根据partyId和userNum查找企业客户维护人员
	 * @param partyId
	 * @param userNum
	 * @return
	 * @author xc
	 */
	CustomerManagerTeam findCusManager(Long partyId, String userNum);
	
	/**
	 * 查看是否有业务申请
	 * @param partyId
	 * @return
	 * @author xc
	 */
	public Long findProjectApplicationCount(Long partyId);
	/**
	 * 根据客户编号获取名字
	 * @param userNum
	 * @return
	 */
	String getUserName(String userNum);
	
	/**
	 * 获取已存在客户列表
	 * @param partyTypeCd
	 * @param pageNum
	 * @param pageSize
	 * @param name
	 * @param licence
	 * @return
	 * @author xc
	 */
	public Page<Object[]> existingPartyList(
											int pageNum,
											int pageSize, 
											String partyTypeCd,
											String name,
											String licence,
											String certificatetypeCd) ;

	/**
	 * 获取已经存在的企业客户信息
	 * @param partyId
	 * @return
	 * @author xc
	 */
	CorpRelaCorpDetailVO findExistingCorpDetail(Long partyId);
	
	/**
	 * 获取已经存在的个人客户信息
	 * @param partyId
	 * @return
	 * @author xc
	 */
	CorpRelaPersonDetailVO findExistingPersonDetail(Long partyId);
	
	/**
	 * 获取字典表的上传类型
	 * */
	Map<String, String> findUploadCustDocTypes();
	/**
	 * 获取客户上传过的文档列表的custDocType
	 * */
	List<String> findDocumentCustDocTypes(Long partyId);
}
