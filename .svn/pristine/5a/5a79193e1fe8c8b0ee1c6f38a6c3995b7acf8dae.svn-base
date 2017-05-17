package com.coamctech.bxloan.web;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.rules.TestName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.coamctech.bxloan.commons.test.BaseSpringJUnit4Tester;
import com.coamctech.bxloan.dao.CodeDao;
import com.coamctech.bxloan.entity.Code;
import com.coamctech.bxloan.entity.CodePk;
import com.coamctech.bxloan.service.workflow.WorkFlowService.ActionCode;

public class TestCode extends BaseSpringJUnit4Tester {

	@Autowired
	private CodeDao codeDao;

	@Test
	@Transactional
	public void testCode() {
		final String[] codeNames = { "正常", "脱险" };
		final String codeType = "CarsInsurance";

		List<Code> codes = new ArrayList<Code>();
		for (int i = 1; i <= codeNames.length; i++) {
			CodePk pk = new CodePk("S" + i, codeType);
			Code code = new Code(pk, codeNames[i - 1], i + "", i, "1");
			codes.add(code);
		}
		codeDao.save(codes);
	}
	
}
