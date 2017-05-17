package com.coamctech.bxloan.service.sysmng.impl;

import java.sql.Types;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;

import com.coamctech.bxloan.service.sysmng.IGenerator;



/**
 * 序列号生成类
 */
@Service("generator")
public class Generator implements IGenerator {

	private Log log = LogFactory.getLog(this.getClass());
	@Autowired
	private JdbcTemplate jdbcTemplate;

	private PlatformTransactionManager dataSourceTransactionManager;
	
	/**
	 * 生成序列号
	 * @param key String 
	 * @return Long 
	 */
	public Long generateSequenceNumber(final String key) {
		return generateSequenceNumber(key,1);
	}
	/**
	 * 生成多个序列号
	 * @param key String 
	 * @param count int
	 * @return list 多个连续序列号列表
	 */
	public Long generateSequenceNumber(final String key,int count){
		if(log.isDebugEnabled())
			log.debug("start---------generateSequenceNumber");
		if(count<=0)
			count=1;
		Long newValue = null;
		Long oldValue = null;
		String sql = "select value from SEQUENCE_NUMBER where key=? for update";
		System.out.println("--------------start sql=" + sql + "    key =" + key);
		if(log.isDebugEnabled())
			
			log.debug("--------------start sql=" + sql + "    key =" + key);
		List oldValueList = (List) jdbcTemplate.queryForList(sql,new Object[] { key }, String.class);
		if(log.isDebugEnabled())
			log.debug("ListSize--------------" + oldValueList.size());
		if (oldValueList.size() != 0) {
			oldValue = new Long(Long.parseLong((String)oldValueList.get(0)));
			newValue = new Long((oldValue.longValue()+count+""));
			String updatesql = "update SEQUENCE_NUMBER a set a.value=? where key=?";
			if(log.isDebugEnabled())
				log.debug("----------------sql=" + updatesql + " value=" + newValue+ " key= " + key);
			Object[] params = new Object[] { newValue, key };
			int[] types = new int[] { Types.BIGINT, Types.VARCHAR };
			jdbcTemplate.update(updatesql, params, types);
		} else {
			newValue =new Long(0+count);
			String insertsql = "insert into SEQUENCE_NUMBER values (?, ?)";
			if(log.isDebugEnabled())
				log.debug("----------------sql=" + insertsql + "  key=" + key + "  value=" + newValue);
			Object[] params = new Object[] { key, newValue };
			int[] types = new int[] { Types.VARCHAR, Types.BIGINT };
			jdbcTemplate.update(insertsql, params, types);
		}
		return newValue;
	}
	/**
	 * 指定根据特征码的当前编号
	 * 
	 * @param key
	 *            特征码
	 * @return 结果Long型序号
	 */
	public Long setSequenceNumber(final String key, final Long value) {

		String sql = "select value from SEQUENCE_NUMBER where key=? for update";
		log.info("--------------start sql=" + sql);
		List oldValueList = (List) jdbcTemplate.queryForList(sql,
				new Object[] { key }, String.class);

		if (oldValueList.size() != 0) {
			String updatesql = "update SEQUENCE_NUMBER a set a.value=? where key=?";
			log.info("start update");
			log.info("----------------sql=" + updatesql + " value=" + value
					+ " key= " + key);
			Object[] params = new Object[] { value, key };
			int[] types = new int[] { Types.BIGINT, Types.VARCHAR };
			jdbcTemplate.update(updatesql, params, types);
			log.info("successful update");
		} else {
			String insertsql = "insert into SEQUENCE_NUMBER values (?, ?)";
			log.info("start insert");
			log.info("----------------sql=" + insertsql + "  key=" + key
					+ "  value=" + value);
			Object[] params = new Object[] { key, value };
			int[] types = new int[] { Types.VARCHAR, Types.BIGINT };
			jdbcTemplate.update(insertsql, params, types);
			log.info("successful insert");
		}
		return value;

	}

	/**
	 * 批量指定符合特征码表达式的当前编号，
	 * @param key 特征码  如清空所有机构的合同序号，且特征码符合建议格式,则可以这样写："contract.*"
	 * @return 结果Long型序号
	 * */
	public Long setSequenceNumbers(final String key, final Long value) {

		String newKey = key + "%";
		String sql = "select value from SEQUENCE_NUMBER where key like ? for update";
		List valueList = (List) jdbcTemplate.queryForList(sql,
				new Object[] { newKey }, String.class);
		if (valueList.size() != 0) {
			String updatesql = "update SEQUENCE_NUMBER  set value=? where key like ?";
			log.info("start update");
			log.info("----------------sql=" + updatesql + " value=" + value
					+ " key= " + newKey);
			Object[] params = new Object[] { value, newKey };
			int[] types = new int[] { Types.BIGINT, Types.VARCHAR };
			jdbcTemplate.update(updatesql, params, types);
			log.info("successful update");
		} else {
			String insertsql = "insert into SEQUENCE_NUMBER values (?, ?)";
			log.info("start insert");
			log.info("----------------sql=" + insertsql + "  key=" + key
					+ "  value=" + value);
			Object[] params = new Object[] { key, value };
			int[] types = new int[] { Types.VARCHAR, Types.BIGINT };
			jdbcTemplate.update(insertsql, params, types);
			log.info("successful insert");
		}
		return value;

	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public PlatformTransactionManager getDataSourceTransactionManager() {
		return dataSourceTransactionManager;
	}

	public void setDataSourceTransactionManager(
			PlatformTransactionManager dataSourceTransactionManager) {
		this.dataSourceTransactionManager = dataSourceTransactionManager;
	}

}
