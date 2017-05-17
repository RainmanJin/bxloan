package com.coamctech.bxloan.service.aand;

import org.springframework.data.domain.Page;

import com.coamctech.bxloan.entity.ProduceAreaInfo;
import com.coamctech.bxloan.service.model.aand.ProduceAreaInfoVo;

public interface ProduceAreaInfoService {

	/**
	 * Description 查询生产区域信息
	 * @return
	 */
	Page<ProduceAreaInfo> findBySearch(String search, Integer pageNumber, Integer pageSize,String type,Long projectId);

	/**
	 * Description 保存生产区域信息
	 * @return
	 */
	void save(ProduceAreaInfoVo produceAreaInfoVo);
	/**
	 * Description 保存生产区域信息
	 * @return
	 */
	void saveProduceAreaInfo(ProduceAreaInfo produceAreaInfo);
	/**
	 * Description 删除生产区域信息
	 * @return
	 */
	void delete(Long id);
	
	/**
	 * Description 修改生产区域信息
	 * @return
	 */	
	ProduceAreaInfo get(Long id);
}
