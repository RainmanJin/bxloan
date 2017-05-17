define(function(require, exports, module) {
	var rm = {
		rules : {
			businessStartDate : {
				required : true
			},
			initialCapital : {
				required : true,
				isNotNegative : true
			},
			businessScope : {
				required : true
			},
			scaleArea : {
				required : true,
				isNotNegative : true
			},
			settlementMode : {
				required : true
			},
			pool : {
				required : true
			},
			customerPercentage : {
				required : true,
				isPositiveNumberFour : true
			},
			scaleWorker : {
				required : true
			},
			settlementPeriod : {
				required : true
			},
			scaleOther : {
				required : true
			},
			month_slack : {
				required : true,
				isMonth : true
			},
			month_peak : {
				required : true,
				isMonth : true
			},
			dailyIncome_slack : {
				required : true,
				isNotNegative : true
			},
			dailyIncome_peak : {
				required : true,
				isNotNegative : true
			},
			dailyChangeableCost_slack : {
				required : true,
				isNotNegative : true
			},
			dailyChangeableCost_peak : {
				required : true,
				isNotNegative : true
			},
			dailyGain_slack : {
				required : true
			},
			dailyGain_peak : {
				required : true
			},
			businessDay_slack : {
				required : true,
				isDayInMonth : true
			},
			businessDay_peak : {
				required : true,
				isDayInMonth : true
			},
			monthlyIncome_slack : {
				required : true,
				isNotNegative : true
			},
			monthlyIncome_peak : {
				required : true,
				isNotNegative : true
			},
			monthlyChangeableCost_slack : {
				required : true,
				isNotNegative : true
			},
			monthlyChangeableCost_peak : {
				required : true,
				isNotNegative : true
			},
			yearIncomeTotal : {
				required : true
			},
			yearChangeableCostTotal : {
				required : true
			},
			yearGainTotal : {
				required : true
			},
			customerDictateGainRatio : {
				required : true,
				isPositiveNumberFour : true
			},
			changeableRemarks : {
				required : true
			},
			salary : {
				required : true,
				isNotNegative : true
			},
			rent : {
				required : true,
				isNotNegative : true
			},
			hospitality : {
				required : true,
				isNotNegative : true
			},
			tranffic : {
				required : true,
				isNotNegative : true
			},
			waterElectric : {
				required : true,
				isNotNegative : true
			},
			communication : {
				required : true,
				isNotNegative : true
			},
			tax : {
				required : true,
				isNotNegative : true
			},
			repair : {
				required : true,
				isNotNegative : true
			},
			others1 : {
				required : true,
				isNotNegative : true
			},
			others2 : {
				required : true,
				isNotNegative : true
			},
			others3 : {
				required : true,
				isNotNegative : true
			},
			others4 : {
				required : true,
				isNotNegative : true
			},
			gainTotal : {
				required : true
			},
			costTotal : {
				required : true
			}
		},
		messages : {
			
		}
	};
	module.exports = rm;
});