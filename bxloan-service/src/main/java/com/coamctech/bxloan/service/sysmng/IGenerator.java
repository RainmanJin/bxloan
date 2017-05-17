package com.coamctech.bxloan.service.sysmng;


/**
 * Created on 2005-9-30
 * @author
 * 序号生成器基本算法内核接口<br><br>
 * 建议：如果存在级别，如合同序号下的不同机构 应采用分级的特征码 如："contract.1400000.1412000" ,以便于批量清空或设置
 * */
public interface IGenerator{
	
/**
 * 生成并返回根据特征码的序号
 * @param key 特征码 
 * @return 生成的Long型序号
 * */
public Long generateSequenceNumber(String key)throws Exception;
/**
 * 返回根据特征码的生成的多个连续序号
 * @param key 特征码 
 * @param count 个数
 * @return 生成的Long型序号
 * */
public Long generateSequenceNumber(String key,int count)throws Exception;

/**
 * 指定根据特征码的当前编号
 * @param key 特征码 
 * @return 结果Long型序号
 * */
public Long setSequenceNumber(String key,Long value)throws Exception;

/**
 * 批量指定符合特征码表达式的当前编号，
 * @param key 特征码  如清空所有机构的合同序号，且特征码符合建议格式,则可以这样写："contract.*"
 * @return 结果Long型序号
 * */	
public Long setSequenceNumbers(String key,Long value)throws Exception;
	
}