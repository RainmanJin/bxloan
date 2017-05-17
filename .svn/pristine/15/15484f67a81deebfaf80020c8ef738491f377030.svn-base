package com.coamctech.bxloan.service.bizapply;

import java.util.List;

import org.springframework.data.domain.Page;

import com.coamctech.bxloan.entity.BreedAgriculture;
import com.coamctech.bxloan.entity.BreedNonAgriculture;
import com.coamctech.bxloan.entity.CultivateAgriculture;
import com.coamctech.bxloan.entity.CultivateNonAgriculture;

public interface CultivateAndBreedService {
	
	/**
	 * 查非农种植业
	 * */
	CultivateNonAgriculture findCnaById(Long id);
	/**
	 * 查非农种植业们，根据业务id
	 * */
	List<CultivateNonAgriculture> findCnaListById(Long projectId);
	/**
	 * 查农种植业
	 * */
	CultivateAgriculture findCaById(Long id);
	/**
	 * 查农种植业们，根据业务id
	 * */
	List<CultivateAgriculture> findCaListById(Long projectId);
	/**
	 * 保存非农种植
	 * */
	Long saveCultivateNonAgriculture(CultivateNonAgriculture cna);
	/**
	 * 保存非农种植
	 * */
	void saveCultivateNonAgricultureList(List<CultivateNonAgriculture> cnaList);
	/**
	 * 保存农种植
	 * */
	Long saveCultivateAgriculture(CultivateAgriculture ca);
	/**
	 * 保存农种植列表
	 * */
	void saveCultivateAgricultureList(List<CultivateAgriculture> caList);
	/**
	 * 删除非农种植
	 * */
	void deleteCultivateNonAgriculture(Long id);
	/**
	 * 删除农种植
	 * */
	void deleteCultivateAgriculture(Long id);
	/**
	 * 查农养殖业
	 * */
	BreedAgriculture findBaById(Long id);
	/**
	 * 查农养殖业，根据业务id
	 * */
	List<BreedAgriculture> findBaListById(Long projectId);
	/**
	 * 查非农养殖业
	 * */
	BreedNonAgriculture findBnaById(Long id);
	/**
	 * 查非农养殖业，根据业务id
	 * */
	List<BreedNonAgriculture> findBnaListById(Long projectId);
	/**
	 * 保存非农养殖
	 * */
	Long saveBreedNonAgriculture(BreedNonAgriculture bna);
	/**
	 * 保存非农养殖列表
	 * */
	void saveBreedNonAgricultureList(List<BreedNonAgriculture> bnaList);
	/**
	 * 保存农养殖
	 * */
	Long saveBreedAgriculture(BreedAgriculture ba);
	/**
	 * 保存农养殖
	 * */
	void saveBreedAgricultureList(List<BreedAgriculture> baList);
	/**
	 * 删除非农养殖
	 * */
	void deleteBreedNonAgriculture(Long id);
	/**
	 * 删除农养殖
	 * */
	void deleteBreedAgriculture(Long id);
	/**
	 * 查询非农种植列表
	 * */
	List<CultivateNonAgriculture> findfnCultivateBySearch(Integer pageNumber, Integer pageSize, String projectId);
	/**
	 * 查询非农养殖列表
	 * */
	List<BreedNonAgriculture> findfnBreedBySearch(Integer pageNumber, Integer pageSize, String projectId);
	/**
	 * 查询农种植列表
	 * @param projectId
	 * @param type 0-过去12月，1-未来12月
	 * */
	List<CultivateAgriculture> findNongPassCultivateBySearch(Integer pageNumber, Integer pageSize, String projectId, int type);
	/**
	 * 查询农养殖列表
	 * @param projectId
	 * @param type 0-过去12月，1-未来12月
	 * */
	List<BreedAgriculture> findNongPassBreedBySearch(Integer pageNumber, Integer pageSize, String projectId, int type);
	/**
	 * 查找农种植可选列表
	 * */
	List<CultivateAgriculture> findCultivateChoosenList(Long projectId, Long id);
	/**
	 * 查找农养殖可选列表
	 * @param id 
	 * */
	List<BreedAgriculture> findBreedChoosenList(Long projectId, Long id);
}
