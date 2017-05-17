package com.coamctech.bxloan.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.coamctech.bxloan.entity.AccountingSubject;

public interface AccountingSubjectDao extends
		PagingAndSortingRepository<AccountingSubject, String>,
		JpaSpecificationExecutor<AccountingSubject> {

	AccountingSubject findListByAccNo(String accNo);

}
