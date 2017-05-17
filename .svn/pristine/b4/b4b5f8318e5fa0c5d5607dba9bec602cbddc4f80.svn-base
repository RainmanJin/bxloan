define(function(require, exports, module) {
	var reg = /^-?[0-9]*(\.\d*)?$|^-?d^(\.\d*)?$/;
	var integerReg =  /^\d+$/;
	
	var model = require("../main/model");
	var rm = require("../main/rm");
	var utils = require("utils");
	var view = Backbone.View.extend({
		el : "body",
		events : {
			/** 财务信息 */
			"change #assCashAmtq" : "addQ",
			"change #assReceivablesAmtq" : "addQ",
			"change #assStockAmtq" : "addQ",
			"change #assHousePropertyAmtq" : "addQ",
			"change #assCarAmtq" : "addQ",
			"change #assOtherAmtq" : "addQ",
			"change #liaBusinessLoanAmtq" : "minusQ",
			"change #liaHouseloanAmtq" : "minusQ",
			"change #liaCarloanAmtq" : "minusQ",
			"change #liaCreditcardq" : "minusQ",
			"change #assCashAmtg" : "addG",
			"change #assHousePropertyAmtg" : "addG",
			"change #assCarAmtg" : "addG",
			"change #assOtherAmtg" : "addG",
			"change #liaHouseloanAmtg" : "minusG",
			"change #liaCarloanAmtg" : "minusG",
			"change #liaCreditcardg" : "minusG",
			"change #liaOtherAmtg" : "minusG",
			/** 企业客户月可支配收入 */
			"change #netprofitAmtq" : "changeMonthdominatIncomeAmtq",
			"change #incomeOtherAmtq" : "changeMonthdominatIncomeAmtq",
			"change #familypayAmtq" : "changeMonthdominatIncomeAmtq",
			"change #otherloanMonthrepayAmtq" : "changeMonthdominatIncomeAmtq",
			/** 个人客户月可支配收入 */
			"change #proprietorshipg" : "changeMonthdominatIncomeAmtg",
			"change #incomeOtherAmtg" : "changeMonthdominatIncomeAmtg",
			"change #familyPayAmtg" : "changeMonthdominatIncomeAmtg",
			"change #otherloanMonthrepayAmtg" : "changeMonthdominatIncomeAmtg",
			"change #familymonthwageAmtg" : "changeMonthdominatIncomeAmtg",
			/** 农户月可支配收入 */
			"change #netprofitAmtn" : "changeMonthdominatIncomeAmtn",
			"change #incomeOtherAmtn" : "changeMonthdominatIncomeAmtn",
			"change #familypayAmtn" : "changeMonthdominatIncomeAmtn",
			"change #otherloanMonthrepayAmtn" : "changeMonthdominatIncomeAmtn"
		},
		initialize : function() {
			this.model = new model();
			this.render();
		},
		render : function() {
			/** 财务信息 */
			this.initFinanceForm();
		},
		initFinanceForm : function() {
			$('#financeForm').validate({
				messages : rm.messages,
				submitHandler : function(form) {
					$("#saveFinance").attr({
						"disabled" : "disabled"
					});
					$.ajax({
						type : 'post',
						url : $$ctx + 'businessapplicationwd/saveFinance',
						data : $(form).serialize(),
						success : function(result) {
							$("#saveFinance").removeAttr("disabled");
							if (result.success) {
								utils.alert.suc("<strong>" + result.msg + "</strong>");
								$('#financeId').val(result.data);
							} else {
								utils.alert.err("<strong>" + result.msg + "</strong>");
							}
						}
					});
				}
			});
		},
        addQ : function() {
        	var sum = 0;
        	if($('#assCashAmtq').val().match(reg) != null)
        		sum += $('#assCashAmtq').val() * 1;
        	if($('#assReceivablesAmtq').val().match(reg) != null)
        		sum += $('#assReceivablesAmtq').val() * 1;
        	if($('#assStockAmtq').val().match(reg) != null)
        		sum += $('#assStockAmtq').val() * 1;
        	if($('#assHousePropertyAmtq').val().match(reg) != null)
        		sum += $('#assHousePropertyAmtq').val() * 1;
        	if($('#assCarAmtq').val().match(reg) != null)
        		sum += $('#assCarAmtq').val() * 1;
        	if($('#assOtherAmtq').val().match(reg) != null)
        		sum += $('#assOtherAmtq').val() * 1;
        	$('#assetsTotalAmtq').val(sum);
        	$('#proprietorshipq').val(sum - $('#liabilitiesTotalAmtq').val() * 1);
        },
        minusQ : function() {
        	var sum = 0;
        	if($('#liaBusinessLoanAmtq').val().match(reg) != null)
        		sum += $('#liaBusinessLoanAmtq').val() * 1;
        	if($('#liaHouseloanAmtq').val().match(reg) != null)
        		sum += $('#liaHouseloanAmtq').val() * 1;
        	if($('#liaCarloanAmtq').val().match(reg) != null)
        		sum += $('#liaCarloanAmtq').val() * 1;
        	if($('#liaCreditcardq').val().match(reg) != null)
        		sum += $('#liaCreditcardq').val() * 1;
        	$('#liabilitiesTotalAmtq').val(sum);
        	$('#proprietorshipq').val($('#assetsTotalAmtq').val() * 1 - sum);
        },
        addG : function() {
        	var sum = 0;
        	if($('#assCashAmtg').val().match(reg) != null)
        		sum += $('#assCashAmtg').val() * 1;
        	if($('#assHousePropertyAmtg').val().match(reg) != null)
        		sum += $('#assHousePropertyAmtg').val() * 1;
        	if($('#assCarAmtg').val().match(reg) != null)
        		sum += $('#assCarAmtg').val() * 1;
        	if($('#assOtherAmtg').val().match(reg) != null)
        		sum += $('#assOtherAmtg').val() * 1;
        	$('#assetsTotalAmtg').val(sum);
        	$('#proprietorshipg').val(sum - $('#liabilitiesTotalAmtg').val() * 1);
        	$('#proprietorshipg').change();
        },
        minusG : function() {
        	var sum = 0;
        	if($('#liaHouseloanAmtg').val().match(reg) != null)
        		sum += $('#liaHouseloanAmtg').val() * 1;
        	if($('#liaCarloanAmtg').val().match(reg) != null)
        		sum += $('#liaCarloanAmtg').val() * 1;
        	if($('#liaCreditcardg').val().match(reg) != null)
        		sum += $('#liaCreditcardg').val() * 1;
        	if($('#liaOtherAmtg').val().match(reg) != null)
        		sum += $('#liaOtherAmtg').val() * 1;
        	$('#liabilitiesTotalAmtg').val(sum);
        	$('#proprietorshipg').val($('#assetsTotalAmtg').val() * 1 - sum);
        	$('#proprietorshipg').change();
        },
        changeMonthdominatIncomeAmtq : function() {
        	if($('#netprofitAmtq').valid() && $('#incomeOtherAmtq').valid() && $('#familypayAmtq').valid() && 
        			$('#otherloanMonthrepayAmtq').valid()) {
        		var netprofitAmtq = parseFloat($('#netprofitAmtq').val());
            	var incomeOtherAmtq = parseFloat($('#incomeOtherAmtq').val());
            	var familypayAmtq = parseFloat($('#familypayAmtq').val());
            	var otherloanMonthrepayAmtq = parseFloat($('#otherloanMonthrepayAmtq').val());
            	$('#monthdominatIncomeAmtq').val(netprofitAmtq + incomeOtherAmtq - familypayAmtq - otherloanMonthrepayAmtq);
        	}
        },
        changeMonthdominatIncomeAmtg : function() {
        	if($('#proprietorshipg').valid() && $('#incomeOtherAmtg').valid() && $('#familyPayAmtg').valid() && 
        			$('#otherloanMonthrepayAmtg').valid() && $('#familymonthwageAmtg').valid()) {
        		var familymonthwageAmtg = parseFloat($('#familymonthwageAmtg').val());
            	var incomeOtherAmtg = parseFloat($('#incomeOtherAmtg').val());
            	var familyPayAmtg = parseFloat($('#familyPayAmtg').val());
            	var otherloanMonthrepayAmtg = parseFloat($('#otherloanMonthrepayAmtg').val());
            	$('#monthdominatIncomeAmtg').val(familymonthwageAmtg + incomeOtherAmtg - familyPayAmtg - otherloanMonthrepayAmtg);
        	}
        },
        changeMonthdominatIncomeAmtn : function() {
        	if($('#netprofitAmtn').valid() && $('#incomeOtherAmtn').valid() && $('#familypayAmtn').valid() && 
        			$('#otherloanMonthrepayAmtn').valid()) {
        		var netprofitAmtn = parseFloat($('#netprofitAmtn').val());
            	var incomeOtherAmtn = parseFloat($('#incomeOtherAmtn').val());
            	var familypayAmtn = parseFloat($('#familypayAmtn').val());
            	var otherloanMonthrepayAmtn = parseFloat($('#otherloanMonthrepayAmtn').val());
            	$('#monthdominatIncomeAmtn').val(((netprofitAmtn + incomeOtherAmtn - familypayAmtn - otherloanMonthrepayAmtn * 12) / 12).toFixed(2));
        	}
        }
	});
	module.exports = view;
});