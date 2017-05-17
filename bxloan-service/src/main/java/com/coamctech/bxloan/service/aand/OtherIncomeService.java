package com.coamctech.bxloan.service.aand;

import org.springframework.data.domain.Page;

import com.coamctech.bxloan.entity.OtherIncome;

public interface OtherIncomeService {

	void save(OtherIncome otherIncome);

	void delete(Long id);

	Page<OtherIncome> findBySearch(String search, Integer pageNumber,Integer pageSize, String type,Long projectId,String futurePastType);

	OtherIncome get(Long id);

	void saveOtherIncome(OtherIncome otherIncome);

}
