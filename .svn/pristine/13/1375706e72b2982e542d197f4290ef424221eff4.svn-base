package com.coamctech.bxloan.service.bizapply.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coamctech.bxloan.commons.dynamicquery.DynamicQuery;
import com.coamctech.bxloan.dao.BreedAgricultureDao;
import com.coamctech.bxloan.dao.BreedNonAgricultureDao;
import com.coamctech.bxloan.dao.CultivateAgricultureDao;
import com.coamctech.bxloan.dao.CultivateNonAgricultureDao;
import com.coamctech.bxloan.datadict.DataDict;
import com.coamctech.bxloan.entity.BreedAgriculture;
import com.coamctech.bxloan.entity.BreedNonAgriculture;
import com.coamctech.bxloan.entity.CultivateAgriculture;
import com.coamctech.bxloan.entity.CultivateNonAgriculture;
import com.coamctech.bxloan.service.bizapply.CultivateAndBreedService;
import com.google.common.collect.Lists;
@Transactional
@Service
public class CultivateAndBreedServiceImpl implements CultivateAndBreedService{
	
	@Autowired
	private DataDict dataDict;
	@Autowired
	private DynamicQuery dynamicQuery;
	@Autowired
	private CultivateAgricultureDao cultivateAgricultureDao;
	@Autowired
	private CultivateNonAgricultureDao cultivateNonAgricultureDao;
	@Autowired
	private BreedAgricultureDao breedAgricultureDao;
	@Autowired
	private BreedNonAgricultureDao breedNonAgricultureDao;
	@Override
	public CultivateNonAgriculture findCnaById(Long id) {
		return this.cultivateNonAgricultureDao.findById(id);
	}
	@Override
	public List<CultivateNonAgriculture> findCnaListById(Long projectId) {
		return this.cultivateNonAgricultureDao.findByProjectId(projectId);
	}
	@Override
	public CultivateAgriculture findCaById(Long id) {
		return this.cultivateAgricultureDao.findById(id);
	}
	@Override
	public List<CultivateAgriculture> findCaListById(Long projectId) {
		return this.cultivateAgricultureDao.findByProjectId(projectId);
	}
	@Override
	public Long saveCultivateNonAgriculture(CultivateNonAgriculture cna) {
		this.cultivateNonAgricultureDao.save(cna);
		return cna.getId();
	}
	@Override
	public Long saveCultivateAgriculture(CultivateAgriculture ca) {
		this.cultivateAgricultureDao.save(ca);
		return ca.getId();
	}
	@Override
	public void deleteCultivateNonAgriculture(Long id) {
		this.cultivateNonAgricultureDao.delete(id);
	}
	@Override
	public void deleteCultivateAgriculture(Long id) {
		this.cultivateAgricultureDao.delete(id);
	}
	@Override
	public BreedAgriculture findBaById(Long id) {
		return this.breedAgricultureDao.findById(id);
	}
	@Override
	public List<BreedAgriculture> findBaListById(Long projectId) {
		return this.breedAgricultureDao.findByProjectId(projectId);
	}
	@Override
	public BreedNonAgriculture findBnaById(Long id) {
		return this.breedNonAgricultureDao.findById(id);
	}
	@Override
	public List<BreedNonAgriculture> findBnaListById(Long projectId) {
		return this.breedNonAgricultureDao.findByProjectId(projectId);
	}
	@Override
	public Long saveBreedNonAgriculture(BreedNonAgriculture bna) {
		this.breedNonAgricultureDao.save(bna);
		return bna.getId();
	}
	@Override
	public Long saveBreedAgriculture(BreedAgriculture ba) {
		this.breedAgricultureDao.save(ba);
		return ba.getId();
	}
	@Override
	public void deleteBreedNonAgriculture(Long id) {
		this.breedNonAgricultureDao.delete(id);
	}
	@Override
	public void deleteBreedAgriculture(Long id) {
		this.breedAgricultureDao.delete(id);
	}
	@Override
	public List<CultivateNonAgriculture> findfnCultivateBySearch(Integer pageNumber, Integer pageSize, String projectId) {
		StringBuffer jpql = new StringBuffer("");
		jpql.append("select cna from  CultivateNonAgriculture cna where cna.projectId = ?1 ");
		return dynamicQuery.query(CultivateNonAgriculture.class,jpql.toString(), Long.valueOf(projectId));
	}
	@Override
	public List<BreedNonAgriculture> findfnBreedBySearch(Integer pageNumber, Integer pageSize, String projectId) {
		StringBuffer jpql = new StringBuffer("");
		jpql.append("select bna from  BreedNonAgriculture bna where bna.projectId = ?1 ");
		return dynamicQuery.query(BreedNonAgriculture.class,jpql.toString(), Long.valueOf(projectId));
	}
	@Override
	public void saveCultivateNonAgricultureList(List<CultivateNonAgriculture> cnaList) {
		this.cultivateNonAgricultureDao.save(cnaList);
	}
	@Override
	public void saveCultivateAgricultureList(List<CultivateAgriculture> caList) {
		this.cultivateAgricultureDao.save(caList);
	}
	@Override
	public void saveBreedNonAgricultureList(List<BreedNonAgriculture> bnaList) {
		this.breedNonAgricultureDao.save(bnaList);
	}
	@Override
	public void saveBreedAgricultureList(List<BreedAgriculture> baList) {
		this.breedAgricultureDao.save(baList);
	}
	@Override
	public List<CultivateAgriculture> findNongPassCultivateBySearch(Integer pageNumber, Integer pageSize, String projectId, int type) {
		StringBuffer jpql = new StringBuffer("");
		jpql.append("select ca from  CultivateAgriculture ca where ca.projectId = ?1 and ca.type = ?2");
		return dynamicQuery.query(CultivateAgriculture.class,jpql.toString(), Long.valueOf(projectId), String.valueOf(type));
	}
	@Override
	public List<BreedAgriculture> findNongPassBreedBySearch(Integer pageNumber, Integer pageSize, String projectId, int type) {
		StringBuffer jpql = new StringBuffer("");
		jpql.append("select ba from  BreedAgriculture ba where ba.projectId = ?1 and ba.type = ?2");
		return dynamicQuery.query(BreedAgriculture.class,jpql.toString(), Long.valueOf(projectId), String.valueOf(type));
	}
	@Override
	public List<CultivateAgriculture> findCultivateChoosenList(Long projectId, Long id) {
		
		String sql = "select ca from CultivateAgriculture ca where ca.projectId = ?1 and ca.type = '1' and ca.relativeId is null ";
		
		List<CultivateAgriculture> list =  dynamicQuery.query(CultivateAgriculture.class,sql.toString(), projectId);
		if(id != null){
			String _sql = "select ca from CultivateAgriculture ca where ca.relativeId = ?1";
			List<CultivateAgriculture> _rlist = dynamicQuery.query(CultivateAgriculture.class,_sql, id);
			if(CollectionUtils.isNotEmpty(_rlist)){
				list.add(_rlist.get(0));
			}
		}
		return list;
	}
	@Override
	public List<BreedAgriculture> findBreedChoosenList(Long projectId, Long id) {
		
		String sql = "select ba from BreedAgriculture ba where ba.projectId = ?1 and ba.type = '1' and ba.relativeId is null";
		
		List<BreedAgriculture> list =  dynamicQuery.query(BreedAgriculture.class,sql.toString(), projectId);
		if(id != null){
			String _sql = "select ba from BreedAgriculture ba where ba.relativeId = ?1";
			List<BreedAgriculture> _rlist = dynamicQuery.query(BreedAgriculture.class,_sql, id);
			if(CollectionUtils.isNotEmpty(_rlist)){
				list.add(_rlist.get(0));
			}
		}
		return list;
	}
}
