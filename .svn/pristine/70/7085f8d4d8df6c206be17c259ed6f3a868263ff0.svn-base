package com.coamctech.bxloan.service.customermng;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.data.domain.Page;

import com.coamctech.bxloan.entity.Party;
import com.coamctech.bxloan.entity.UnCustTab;
import com.coamctech.bxloan.entity.UniteGuNego;
import com.coamctech.bxloan.service.model.tipmsg.MsgResult;

public interface UniteGuCustomerService {
	/**
	 * 根据类型查询对应的联保体信息
	 * 包个三个字段：id联保体id,un_gu_id联保体协议编号,un_group_name联保体小组名称
	 * @author wangxy 20150618
	 */
	List<UniteGuNego> findUniteCustomerByType(String unType);
	
	
	/**
	 * 客户所在的联保体客户列表
	 * @author HWL
	 * @since 2015-07-27
	 * @param partyId	该客户id
	 * @param unType	联保体类型
	 * @param minMemberSize	最小成员人数
	 * @param maxMemberSize 最大成员人数
	 * @return
	 * 
	 */
	List<UniteGuNego> findUgnCustListByPartyId(Long partyId,String unType,int minMemberSize,int maxMemberSize);
	/**
	 * 根据联保体Id查联保体成员列表
	 * @return
	 * @author wangxy 20150623
	 */
	List<UnCustTab> findUgnMembersByUgnId(Long ugnId);
	/**
	 * 根据联保体协议编号查联保体成员列表
	 * @return
	 * @author wangxy 20150623
	 */
	List<UnCustTab> findUniteMembersByUnId(String unGuId);
	/**
	 * 查询联保体客户列表
	 * */
	Page<Object[]> findUniteCustomerBySearch(Integer pageNumber, Integer pageSize, Map<String, String> params);
	/**
	 * 通过id查询联保体
	 * @param id
	 * @return UniteGuNego对象
	 * */
	UniteGuNego findUniteCustomerById(Long id);
	/**
	 * 保存联保体
	 * */
	UniteGuNego saveUniteCustomer(UniteGuNego ugn);
	/**
	 * 通过id删除联保体
	 * */
	void deleteUniteGuNego(Long id);
	/**
	 * 删除联保体的操作
	 * */
	void deleteUniteCustomer(UniteGuNego ugn);
	/**
	 * 通过机构id获取机构名
	 * */
	String getOrgNameByOrgId(String segiOrg);
	/**
	 * 查询客户经理列表
	 * */
	Page<Object[]> findCustManagerBySearch(Integer pageNumber, Integer pageSize,Long orgId, String custManager);
	/**
	 * 获取联保体成员列表
	 * */
	Page<Object[]> findUniteMembersBySearch(Integer pageNumber, Integer pageSize, String unId);
	/**
	 * 查询客户列表
	 * */
	/**
	 * 查询客户列表
	 * @param pageNumber
	 * @param pageSize
	 * @param unGuId 联保体协议编号
	 * @param params
	 * @return
	 */
	Page<Object[]> findCustomersBySearch(Integer pageNumber, Integer pageSize,String unGuId, List<String> params);
	/**
	 * 保存UnCustTab
	 * */
	void  saveUnCustTab(UnCustTab tab);
	/**
	 * 通过partyId获取客户
	 * */
	Party findPartyByPartyId(String partyId);
	/**
	 * 查询序列
	 * */
	String findNextId();
	/**
	 * 根据partyId和unGuId查找UnCustTab
	 * */
	List findUnCustTabByPartyId(Long partyId, String unGuId);
	/**
	 * 通过id获取UnCustTab
	 * @param meb_id
	 * */
	UnCustTab getUnCustTabById(String meb_id);
	/**
	 * 通过id删除UnCustTab
	 * @param id
	 * */
	void deleteUnCustTab(String id);
	
	/**
	 * 通过unGuId查询UniteGuNego
	 * */
	UniteGuNego findUniteCustomerByUnGuId(String unGuId);
	/**
	 * 通过unGuId查询上传文档的数量
	 * @param unGuId
	 * */
	String findDocumentCount(String unGuId);
	/**
	 * 通过unGuId查询UnCustTab的list
	 * @param unGuId
	 * */
	List<UnCustTab> findUnCustTabList(String unGuId);
	/**
	 * 联保体生效
	 * @param unGuId
	 * @param action 1生效2失效
	 * */
	void updateUniteStatus(String unGuId, Integer action);
	/**
	 * 通过id获取UnCustTab
	 * @param id
	 * @return UnCustTab
	 * */
	UnCustTab findUnCustTabById(String id);
	/**
	 * 获取下一个uncusttab的seq
	 * */
	String findNextSeq();
	
	
	/**
	 * 批量增加联保体成员
	 * @param ugnId
	 * @param custIds
	 */
	String addMembersOfBatch(Long ugnId,Set<Long> custIds);
	
	
	/**
	 * 校验所有业务状态
	 * @param ugnId
	 * @return
	 */
	MsgResult checkAllBizStatus(Long ugnId);
}
