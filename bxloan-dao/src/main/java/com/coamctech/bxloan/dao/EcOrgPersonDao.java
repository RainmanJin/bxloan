package com.coamctech.bxloan.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.coamctech.bxloan.entity.EcOrgPerson;

public interface EcOrgPersonDao extends PagingAndSortingRepository<EcOrgPerson, Long>,
		JpaSpecificationExecutor<EcOrgPerson> {

	@Query("select new EcOrgPerson(eop.usernum,eop.logname,eop.name,eod.name) from EcOrgPerson eop, EcOrgDepartment eod where eop.orgid=eod.id and eop.usernum = ?1")
	EcOrgPerson findPersonByUsernum(String usernum);

	@Query("select eop from EcOrgPerson eop where eop.usernum = ?1")
	EcOrgPerson findUserByUsernum(String usernum);

	EcOrgPerson findBylogname(String logname);

	List<EcOrgPerson> findByEmpnum(String empnum);
	
	@Query("select eop from EcOrgPerson eop where eop.id = ?1")
	EcOrgPerson findById(Long id);
	
	@Query("select eop from EcOrgPerson eop where eop.empnum=?1 and eop.logname = ?2")
	EcOrgPerson findByEmpnumAndLogname(String empnum, String logname);

}
