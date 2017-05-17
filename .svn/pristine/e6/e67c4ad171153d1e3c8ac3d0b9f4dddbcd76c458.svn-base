package com.coamctech.bxloan.service.bizapply;

import java.util.List;

import org.springframework.data.domain.Page;

import com.coamctech.bxloan.service.model.bizapply.Statistics;

public interface StatisticsService {
	public enum Type {
		AGRICULTURE("1", "农业收入为主"), NON_AGRICULTURE("2", "非农业收入为主");
		private final String value;
		private final String name;
		private Type(String value, String name) {
			this.value = value;
			this.name = name;
		}
		public String getValue() {
			return value;
		}
		public String getName() {
			return name;
		}
	}
	public enum FuturePastType {
		PAST("1", "过去12个月全年"), FUTURE("2", "未来12个月全年");
		private final String value;
		private final String name;
		private FuturePastType(String value, String name) {
			this.value = value;
			this.name = name;
		}
		public String getValue() {
			return value;
		}
		public String getName() {
			return name;
		}
	}
	public enum IncomeStructure {
		INCOME, COST, GAIN, STOCK, PAST_INCOME, PREDICTABLE_INCOME, PREDICTABLE_GAIN
	}
	public enum IncomeType {
		INDUSTRY_COMMERCE("1", "工商业"), CROP_FARMING("2", "种植业"), BREED("3", "养殖业"), OTHER("4", "其他"), SUM("5", "合计");
		private final String value;
		private final String name;
		private IncomeType(String value, String name) {
			this.value = value;
			this.name = name;
		}
		public String getValue() {
			return value;
		}
		public String getName() {
			return name;
		}
	}
	public enum IncomeDetail {
		AMOUNT, PERCENT
	}

	public List<Statistics> count(Long projectId);

	public List<Statistics> count(Long projectId, IncomeType... incomeTypes);

	//public Statistics count(IncomeStructure incomeStructure, IncomeType... incomeTypes);
	
	//public Statistics count(IncomeStructure incomeStructure, IncomeDetail incomeDetail, IncomeType... incomeTypes);
	
	public Statistics count(Long projectId, IncomeStructure incomeStructure, IncomeDetail incomeDetail, IncomeType incomeType);
	
	public Page<Statistics> findBySearch(Integer pageNumber, Integer pageSize, Long projectId);
}
