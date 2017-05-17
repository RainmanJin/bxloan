package com.coamctech.bxloan.service.customermng;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.domain.Page;

import com.coamctech.bxloan.entity.CustomerAccountManagent;
import com.coamctech.bxloan.entity.CustomerBrieflyFinace;
import com.coamctech.bxloan.entity.CustomerBusinessInfo;
import com.coamctech.bxloan.entity.CustomerCorrelativeRelations;
import com.coamctech.bxloan.entity.CustomerManagerTeam;
import com.coamctech.bxloan.entity.DocumentIndex;
import com.coamctech.bxloan.entity.FamilyFriend;
import com.coamctech.bxloan.entity.Individual;
import com.coamctech.bxloan.entity.IndustryType;
import com.coamctech.bxloan.entity.Party;
import com.coamctech.bxloan.entity.SalaBusiCustFinalcial;
import com.coamctech.bxloan.entity.VerificatPersonInfo;

public interface UniqueCustomerService {
	/**
	 * 保存客户信息
	 * 
	 * @param individual 客户对象
	 * 
	 * @author lijing
	 * @lastModified lijing 2014-07-22 16:37:51
	 */
	void saveIndividual(Individual individual);

	/**
	 * 根据客户Id获取客户对象
	 * 
	 * @param partyId 客户对象
	 * @return Individual对象 返回查到的客户对象
	 * 
	 * @author lijing
	 * @lastModified lijing 2014-07-22 16:37:51
	 */
	Individual getIndividual(String partyId);

	/**
	 * 获取客户表记录数
	 * 
	 * @return Long 统计记录数
	 * 
	 * @author lijing
	 * @lastModified lijing 2014-07-22 16:37:51
	 */
	Long countIndividual();

	/**
	 * 判断含某客户Id的记录是否存在
	 * 
	 * @param partyId 客户id
	 * @return Boolean 是否存在
	 * 
	 * @author lijing
	 * @lastModified lijing 2014-07-22 16:37:51
	 */
	Boolean existsIndividual(String partyId);

	/**
	 * 根据客户Id删除客户记录
	 * 
	 * @param partyId 客户id
	 * @return String 是否删除
	 * 
	 * @author lijing
	 * @lastModified lijing 2014-07-22 16:37:51
	 */
	void deleteIndividual(String partyId);

	/**
	 * 通过客户名进行分页查询
	 * 
	 * @param customerName 客户名
	 * @param pageNumber 页号
	 * @param pageSize 每页条数
	 * @return 分页查询对象
	 * 
	 * @author
	 * @lastModified lijing 2014-07-22 16:37:51
	 */
	Page<Individual> pagingQueryByCustomerNameForIndividual(String customerName, Integer pageNumber, Integer pageSize);

	/**
	 * 根据客户名获取单个客户
	 * 
	 * @param CustomerName 客户名
	 * @return Individual对象 返回查到的客户对象
	 * 
	 * @author lijing
	 * @lastModified lijing 2014-07-22 16:37:51
	 */
	Individual findIndividualByCustomerName(String CustomerName);

	/**
	 * 客户生效
	 * 
	 * @param partyId 客户Id
	 * 
	 * 
	 * @author lijing
	 * @lastModified lijing 2014-07-22 16:37:51
	 */
	void updateIndividualStatus(String partyId);

	/**
	 * 客户列表查询
	 * 
	 * @param params 参数集合
	 * @param pageNumber 页号
	 * @param pageSize 每页条数
	 * @return Page 分页查询对象
	 * 
	 * @author
	 * @lastModified lijing 2014-07-22 16:37:51
	 */
	Page findIndividualBySearch(Integer pageNumber, Integer pageSize, List<Object> params);
	
	/**
	 * 客户查询列表
	 * 
	 * @param params 参数集合
	 * @param pageNumber 页号
	 * @param pageSize 每页条数
	 * @return Page 分页查询对象
	 * 
	 * @author
	 * @lastModified lijing 2014-07-22 16:37:51
	 */
	Page findIndividualQuery(Integer pageNumber, Integer pageSize, List<Object> params);

	/**
	 * 根据客户名获取ManagerName字段
	 * 
	 * @param CustomerName 客户名
	 * @return String managerName字段
	 * 
	 * @author lijing
	 * @lastModified lijing 2014-07-22 16:37:51
	 */
	String findManagerNameForIndividual(String customerNum);

	/**
	 * 根据客户customerNum查找客户信息信息
	 * 
	 * @param customerNum 客户Num
	 * @return Individual 客户对象
	 * 
	 * @author lijing
	 * @lastModified lijing 2014-07-22 16:37:51
	 */
	Individual findByCustomerNum(String customerNum);

	/**
	 * 保存账户信息
	 * 
	 * @param customerAccountManagent 账户对象
	 * 
	 * @author lijing
	 * @lastModified lijing 2014-07-22 16:37:51
	 */
	void saveAccountManagent(CustomerAccountManagent customerAccountManagent);


	/**
	 * 根据账户Id获取客户对象
	 * 
	 * @param accountId 账户Id
	 * @return CustomerAccountManagent对象 返回查到的账户信息
	 * 
	 * @author lijing
	 * @lastModified lijing 2014-07-22 16:37:51
	 */
	CustomerAccountManagent findAccountManagentByAccountId(String accountId);

	/**
	 * 获取账户表记录数
	 * 
	 * @return Long 统计记录数
	 * 
	 * @author lijing
	 * @lastModified lijing 2014-07-22 16:37:51
	 */
	Long countAccountManagent();

	/**
	 * 根据客户id获取账户表记录的集合
	 * 
	 * @param partyId 客户Id
	 * @return List 账户记录集合
	 * 
	 * @author lijing
	 * @lastModified lijing 2014-07-22 16:37:51
	 */
	List<CustomerAccountManagent> findAccountManagentListByPartyId(String partyId);

	/**
	 * 判断含某账户Id的记录是否存在
	 * 
	 * @param accoundId 账户id
	 * @return Boolean 是否存在
	 * 
	 * @author lijing
	 * @lastModified lijing 2014-07-22 16:37:51
	 */
	Boolean existsAccountManagent(String accoundId);

	/**
	 * 根据客户Id删除客户记录
	 * 
	 * @param accountId 客户id
	 * 
	 * @author lijing
	 * @lastModified lijing 2014-07-22 16:37:51
	 */
	void deleteAccountManagent(String accountId);

	/**
	 * 根据客户Id做账户列表查询
	 * 
	 * @param partyId 客户Id
	 * @param params 参数集合
	 * @param pageNumber 页号
	 * @param pageSize 每页条数
	 * @return Page 分页查询对象
	 * 
	 * @author
	 * @lastModified lijing 2014-07-22 16:37:51
	 */
	Page findAccountManagentBySearch(String partyId, int pageNumber, Integer pageSize, List<Object> params);

	/**
	 * 根据客户Id和账户号查询账户信息
	 * 
	 * @param partyId 客户Id
	 * @param accountNum 账号
	 * @return CustomerAccountManagent 账户信息对象
	 * 
	 * @author
	 * @lastModified lijing 2014-07-22 16:37:51
	 */
	CustomerAccountManagent findAccountManagentByPartyIdAndAccountNum(String partyId, String accountNum);

	/**
	 * 根据证件号码和类型查找客户
	 * 
	 * @param addCertificateType 账户类型
	 * @param addCertificateNum 账号
	 * @return Party 客户对象
	 * 
	 * @author
	 * @lastModified lijing 2014-07-22 16:37:51
	 */
	Party findPartyByCertificateNumAndCertificateType(String addCertificateType, String addCertificateNum);

	/**
	 * 保存联系人信息
	 * 
	 * @param FamilyFriend 联系人对象
	 * 
	 * @author lijing
	 * @lastModified lijing 2014-07-22 16:37:51
	 */
	Long saveFamilyFriend(FamilyFriend familyfriend);

	/**
	 * 保存联系人信息
	 * 
	 * @param FamilyFriend 联系人对象
	 * 
	 * @author lijing
	 * @return 
	 * @lastModified lijing 2014-07-22 16:37:51
	 */
	Long saveCorrelativeRelation(CustomerCorrelativeRelations customerCorrelativeRelations);

	/**
	 * 根据联系人Id查联系人对象
	 * 
	 * @param correlativeRelationsId 联系人Id
	 * @return FamilyFriend 联系人对象
	 * 
	 * @author lijing
	 * @lastModified lijing 2014-07-22 16:37:51
	 */
	FamilyFriend findFamilyFriendByCorrelativeRelationsId(Long correlativeRelationsId);

	/**
	 * 获取联系人表记录数
	 * 
	 * @return Long 统计记录数
	 * 
	 * @author lijing
	 * @lastModified lijing 2014-07-22 16:37:51
	 */
	Long countFamilyFriend();

	/**
	 * 根据partyId获取联系人表记录的集合
	 * 
	 * @param partyId 客户Id
	 * @return List 联系人集合
	 * 
	 * @author lijing
	 * @lastModified lijing 2014-07-22 16:37:51
	 */
	List<FamilyFriend> findFamilyFriendListByPartyId(String partyId);

	/**
	 * 判断含某联系人Id的记录是否存在
	 * 
	 * @param correlativeRelationsId 联系人id
	 * @return Boolean 是否存在
	 * 
	 * @author lijing
	 * @lastModified lijing 2014-07-22 16:37:51
	 */
	Boolean existsFamilyFriend(String correlativeRelationsId);

	/**
	 * 根据联系人Id删除联系人记录
	 * 
	 * @param correlativeRelationsId 联系人id
	 * 
	 * @author lijing
	 * @lastModified lijing 2014-07-22 16:37:51
	 */
	void deleteFamilyFriend(String correlativeRelationsId);

	/**
	 * 根据联系人Id删除联系人记录
	 * 
	 * @param CorrelativeRelation的id 联系人id
	 * 
	 * @author lijing
	 * @lastModified lijing 2014-07-22 16:37:51
	 */
	void deleteCorrelativeRelation(Long correlativeRelationsId);

	/**
	 * 查找CorrelativeRelation
	 * 
	 * @param CorrelativeRelation的id 联系人id
	 * 
	 * @author lijing
	 * @lastModified lijing 2014-07-22 16:37:51
	 */
	CustomerCorrelativeRelations findCorrelativeRelationById(Long correlativeRelationsId);

	/**
	 * 根据客户Id查联系人列表
	 * 
	 * @param params 参数集合
	 * @param pageNumber 页号
	 * @param pageSize 每页条数
	 * @return Page 分页查询对象
	 * 
	 * @author
	 * @lastModified lijing 2014-07-22 16:37:51
	 */
	Page findFamilyFriendBySearch(String partyId, int pageNumber, Integer pageSize, List<Object> params);

	/**
	 * 根据客户Id查联系人列表
	 * 
	 * @param partyId 联系人Id
	 * @param params 参数集合
	 * @param pageNumber 页号
	 * @param pageSize 每页条数
	 * @return Page 分页查询对象
	 * 
	 * @author
	 * @lastModified lijing 2014-07-22 16:37:51
	 */
	Page findFamilyFriendPageByPartyId(String partyId, int pageNumber, Integer pageSize, List<Object> params);

	/**
	 * 保存文档信息
	 * 
	 * @param documentIndex 文档对象
	 * 
	 * @author lijing
	 * @lastModified lijing 2014-07-22 16:37:51
	 */
	void saveDocumentIndex(DocumentIndex documentIndex);

	/**
	 * 根据文档Id查文档对象
	 * 
	 * @param documentId 文档Id
	 * @return DocumentIndex 文档对象
	 * 
	 * @author lijing
	 * @lastModified lijing 2014-07-22 16:37:51
	 */
	DocumentIndex findDocumentIndexByDocumentId(String documentId);

	/**
	 * 获取文档表记录数
	 * 
	 * @return Long 统计记录数
	 * 
	 * @author lijing
	 * @lastModified lijing 2014-07-22 16:37:51
	 */
	Long countDocumentIndex();

	/**
	 * 判断含某文档Id的记录是否存在
	 * 
	 * @param documentId 文档id
	 * @return Boolean 是否存在
	 * 
	 * @author lijing
	 * @lastModified lijing 2014-07-22 16:37:51
	 */
	Boolean existsDocumentIndex(String documentId);

	/**
	 * 根据文档Id删除文档记录
	 * 
	 * @param documentId 文档id
	 * 
	 * @author lijing
	 * @lastModified lijing 2014-07-22 16:37:51
	 */
	void deleteDocumentIndex(String documentId);

	/**
	 * 根据客户Id查文档列表
	 * 
	 * @param params 参数集合
	 * @param pageNumber 页号
	 * @param pageSize 每页条数
	 * @return Page 分页查询对象
	 * 
	 * @author lijing
	 * @lastModified lijing 2014-07-22 16:37:51 注：此方法有问题，调试中
	 */
	Page findDocumentIndexBySearch(int pageNumber, Integer pageSize, List<Object> params);

	/**
	 * 根据客户id获取文档列表
	 * 
	 * @param partyId 客户Id
	 * @return List<DocumentIndex> 文档集合
	 * 
	 * @author lijing
	 * @lastModified lijing 2014-07-22 16:37:51
	 */
	List<DocumentIndex> findDocumentIndexByPartyId(String partyId);

	/**
	 * 保存客户信息
	 * 
	 * @param party 客户对象
	 * 
	 * @author lijing
	 * @lastModified lijing 2014-07-22 16:37:51
	 */
	void saveParty(Party party);

	/**
	 * 根据客户Id找客户对象
	 * 
	 * @param partyId 客户Id
	 * @return Party 客户对象
	 * 
	 * @author lijing
	 * @lastModified lijing 2014-07-22 16:37:51
	 */
	Party findPartyByPartyId(String partyId);
	/**
	 * 查询客户
	 * @param partyId
	 * @return
	 */
	Party findParty(long partyId);

	/**
	 * 获取客户表记录数
	 * 
	 * @return Long 统计记录数
	 * 
	 * @author lijing
	 * @lastModified lijing 2014-07-22 16:37:51
	 */
	Long countParty();

	/**
	 * 判断含某客户Id的记录是否存在
	 * 
	 * @param partyId 客户id
	 * @return Boolean 是否存在
	 * 
	 * @author lijing
	 * @lastModified lijing 2014-07-22 16:37:51
	 */
	Boolean existsParty(String partyId);

	/**
	 * 根据客户Id删除客户记录
	 * 
	 * @param partyId 文档id
	 * 
	 * @author lijing
	 * @lastModified lijing 2014-07-22 16:37:51
	 */
	void deleteParty(String partyId);

	/**
	 * 根据证件账号找客户对象
	 * 
	 * @param certificateNum 账号
	 * @return Party 客户对象
	 * 
	 * @author lijing
	 * @lastModified lijing 2014-07-22 16:37:51
	 */
	Party findPartyByCertificateNum(String certificateNum);

	/**
	 * 根据客户编号找客户对象
	 * 
	 * @param customerNum 客户编号
	 * @return Party 客户对象
	 * 
	 * @author lijing
	 * @lastModified lijing 2014-07-22 16:37:51
	 */
	Party findPartyBycustomerNum(String customerNum);

	// 经营信息
	/**
	 * 根据客户id获取经营信息
	 * 
	 * @param partyId 客户id
	 * @return CustomerBusinessInfo 对象
	 * @author lijing
	 * */
	CustomerBusinessInfo findBussinessInfoByPartyId(Long partyId);

	/**
	 * 保存经营信息
	 * 
	 * @param customerBusinessInfo 对象
	 * @author lijing
	 * */
	void saveCustomerBusinessInfo(CustomerBusinessInfo customerBusinessInfo);

	// 简要财产信息
	/**
	 * 保存简要财务信息
	 * 
	 * @param customerBrieflyFinace 简要财务对象
	 * 
	 * @author lijing
	 * @lastModified lijing 2014-07-22 16:37:51
	 */
	void saveCustomerBrieflyFinace(CustomerBrieflyFinace customerBrieflyFinace);

	/**
	 * 根据客户Id找简要财务信息
	 * 
	 * @param partyId 客户Id
	 * @return CustomerBrieflyFinace 简要财务对象
	 * 
	 * @author lijing
	 * @lastModified lijing 2014-07-22 16:37:51
	 */
	CustomerBrieflyFinace findFinaceByPartyId(Long partyId);

	// 设置与使用者的关联关系
	/**
	 * 保存简要财务信息
	 * 
	 * @param customerManagerTeam 关联关系对象
	 * 
	 * @author lijing
	 * @lastModified lijing 2014-07-22 16:37:51
	 */
	void saveManagerTeam(CustomerManagerTeam customerManagerTeam);

	// 自定义标签获取行业信息
	/**
	 * 自定义标签：行业分类，获取所有行业分类
	 * 
	 * @return List<IndustryType> 行业分类的集合
	 * 
	 * @author lijing
	 * @lastModified lijing 2014-07-22 16:37:51
	 */
	List<IndustryType> getAllIndustry();

	/**
	 * 获取数据库时间
	 * */
	Timestamp getDBTime();

	/**
	 * 更新客户的更新时间
	 * */
	void updateCustomerUpdateSysTime(String customerId);
	/**
	 * 根据客户Id查受薪、经营的财务信息
	 * 
	 * @param params 参数集合
	 * @param pageNumber 页号
	 * @param pageSize 每页条数
	 * @return Page 分页查询对象
	 * 
	 * @author lijing
	 * @lastModified lijing 2014-07-22 16:37:51 注：此方法有问题，调试中
	 */
	Page findProjectFinalcials(Integer pageNumber, Integer pageSize, List<Object> params);
	/**
	 * 根据salaCustFinacial的id查对象
	 * @param id 主键
	 * */
	SalaBusiCustFinalcial getOneSalaCustFinace(String id);
	/**
	 * 根据partyId获取个人客户的雇佣类型
	 * */
	String getEmploymentTypeByPartyId(Long partyId);
	/**
	 * 根据partyId查询客户状态
	 * @param partyId
	 * @return status
	 * **/
	boolean findIndividualStatus(Long partyId);
	/**
	 * 获取客户婚姻状况
	 * */
	String findIndividualMarriageCd(Long partyId);
	/**
	 * 通过id和partyId联合主键获取CustomerCorrelativeRelations
	 * @return
	 * */
	CustomerCorrelativeRelations findCorrelativeRelationByIdAndPartyId(Long correlativeRelationsId, Long partyId);
	/**
	 * 用sql手动保存 CustomerCorrelativeRelations
	 * */
	Long sqlSaveCustomerCorrelativeRelations(CustomerCorrelativeRelations relation);
	/**
	 * 获取下一个relation的id序列
	 * */
	Long findNextSeqFromRelation();
	/**
	 * 农贷获取联系人列表
	 * */
	public Page findFamilyFriendAgroBySearch(Integer pageNumber, Integer pageSize, String partyId);
	/**
	 * 查询现在的客户数
	 * */
	Long findCustomerListCount(Long userId, Long orgId, Long queryCount);
	
	/**
	 * 保存核实人信息
	 */
	Long saveVerificatPersonInfo(VerificatPersonInfo verificatPersonInfo);
	/**
	 * 查询核实人信息
	 * */
	//public List<VerificatPersonInfo>  findVerificatPersonInfo(Integer pageNumber, Integer pageSize, String partyId);
	public Page  findVerificatPersonInfo(Integer pageNumber, Integer pageSize, String partyId);
	/**
	 * 根据核实人Id查核实人对象
	 * 
	 */
	VerificatPersonInfo findVerificatPersonInfoById(Long id);
	/**
	 * 根据核实人Id删除核实人记录
	 */
	void deleteVerificatPersonInfo(String id);
	
	/**
	 * 根据Page对象获取重要联系人信息列表
	 * 
	 * @param page 
	 * @lastModified wangyawei 2015-04-28 11:55:51
	 * @return
	 */
	public List<FamilyFriend> findFamilyFriendPage(Page<Object[]> page);
}
