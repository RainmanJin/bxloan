define(function(require, exports, module) {
	var model = require("./model");
	var rmedys = require("./rm_edys");
	var rmdg = require("./rm_dg");
	var utils = require("utils");
	
	var view = Backbone.View.extend({
		el: "body",
		events: {
		  "click #saveManu": "saveManuScript",
		  "click #interes": "initeInteres",
		  "click #resetForm": "resetForm",
		  "click #budget": "budget",
		  /** 贷款试算 */
		  "click #dkss" : "dkss",
		  "click #resetCountRateForm": "resetCountRateForm",
		  "click #customerForFloatWindow": "openCustomerWindow"
		},
		initialize: function() { /** 初始化 */
			this.model = new model();
			this.render();
		},
		render: function() { /** 页面渲染 */
			this.initFrameHight();
			this.initLoanStartDate();
			this.initCountRateForm();
			this.initMeasureForm();
			this.initQuotaMeasureForm();
			//家庭资产
			this.initFamilyAssetsForm();
		},
		initFrameHight: function(){
 			$("#partyIframe").load(function() {
 				 setInterval(function(){
 	        	    	var clientHeight = $("#partyIframe").contents().find("body").height();
 	        	    	$("#partyIframe").attr("height",clientHeight+"px!important;");
 	        	 },100);
 			});
 		},
		initLoanStartDate: function(){
			// 将datepicker控件放在最顶层
			var dateOnTop = function() {
				$(".datepicker").css("z-index", "99999");
			};
			var defaultDateConf = {
				autoclose : true,
				todayHighlight : true,
				clearBtn : true,
				endDate : 'd'
			};
			var initDateP = function(selector, changeCallBack, config) {
				var _dateConf = config || defaultDateConf;
				$(selector).datepicker(_dateConf).on("changeDate",
						changeCallBack).on("click", dateOnTop);
			};
			
			initDateP("#loanStartDateBiz");
		},
		initMeasureForm : function() {// 初始化可支配收入测算form
			$("#measureForm").validate({
				submitHandler: function(form) {
					$('#saveMeasure').attr({
						"disabled" : "disabled"
					});
					$.ajax({
						type : 'post',
						url : $$ctx + "bizapply/saveMeasure",
						data : $(form).serialize(),
						success: function(r) {
							$('#saveMeasure').removeAttr('disabled');
							if (r.success) {
								utils.alert.suc("<strong>" + r.msg + "</strong>");
								$("#measureForm input[name=id]").val(r.data.id);
							} else {
								utils.alert.err("<strong>" + r.msg + "</strong>");
							}
						}
					});
				}
			});
			
			$.ajax({
				type : 'post',
				url : $$ctx + "bizapply/getMeasureByProjectId",
				data : {
					'projectId' : $('#projectId').val()
				},
				success: function(measure) {
					$.each($("#measureForm").find("input"), function() {
						if($(this).attr("name") != 'projectId')
							$(this).val(measure[$(this).attr("name")]);
					});
				}
			});
			
			/**
			 * 计算
			 */
			var reg1 = /^[1-9]\d*$/;
			var reg2 = /^\d+(\.\d{1,2})?$/;
			// 借款人收入
			$(document).on("change", "#borrowerSalary, #borrowerBonus, #borrowerWelfare", function(e) {
				var borrowerIncome = 0;
				if($('#borrowerSalary').val().match(reg1) != null || $('#borrowerSalary').val().match(reg2) != null)
					borrowerIncome += $('#borrowerSalary').val() * 1;
				if($('#borrowerBonus').val().match(reg1) != null || $('#borrowerBonus').val().match(reg2) != null)
					borrowerIncome += $('#borrowerBonus').val() * 1;
				if($('#borrowerWelfare').val().match(reg1) != null || $('#borrowerWelfare').val().match(reg2) != null)
					borrowerIncome += $('#borrowerWelfare').val() * 1;
				$('#borrowerIncome').val(borrowerIncome);
				borrowerIncome = 0;
				$('#familyIncome').val($('#borrowerIncome').val() * 1 + $('#borrowerSpouseIncome').val() * 1);
				$('#familyControllableIncome').val($('#familyIncome').val() * 1 - $('#familyPay').val() * 1);
			});
			// 配偶收入
			$(document).on("change", "#borrowerSpouseSalary, #borrowerSpouseBonus, #borrowerSpouseWelfare", 
					function(e) {
				var spouseIncome = 0;
				if($('#borrowerSpouseSalary').val().match(reg1) != null || $('#borrowerSpouseSalary').val().match(reg2) != null)
					spouseIncome += $('#borrowerSpouseSalary').val() * 1;
				if($('#borrowerSpouseBonus').val().match(reg1) != null || $('#borrowerSpouseBonus').val().match(reg2) != null)
					spouseIncome += $('#borrowerSpouseBonus').val() * 1;
				if($('#borrowerSpouseWelfare').val().match(reg1) != null || $('#borrowerSpouseWelfare').val().match(reg2) != null)
					spouseIncome += $('#borrowerSpouseWelfare').val() * 1;
				$('#borrowerSpouseIncome').val(spouseIncome);
				spouseIncome = 0;
				$('#familyIncome').val($('#borrowerIncome').val() * 1 + $('#borrowerSpouseIncome').val() * 1);
				$('#familyControllableIncome').val($('#familyIncome').val() * 1 - $('#familyPay').val() * 1);
			});
			// 借款人支出
			$(document).on("change", "#borrowerHousingloan, #borrowerCarloan, #borrowerCredit, #borrowerOthers", 
					function(e) {
				var borrowerPay = 0;
				if($('#borrowerHousingloan').val().match(reg1) != null || $('#borrowerHousingloan').val().match(reg2) != null)
					borrowerPay += $('#borrowerHousingloan').val() * 1;
				if($('#borrowerCarloan').val().match(reg1) != null || $('#borrowerCarloan').val().match(reg2) != null)
					borrowerPay += $('#borrowerCarloan').val() * 1;
				if($('#borrowerCredit').val().match(reg1) != null || $('#borrowerCredit').val().match(reg2) != null)
					borrowerPay += $('#borrowerCredit').val() * 1;
				if($('#borrowerOthers').val().match(reg1) != null || $('#borrowerOthers').val().match(reg2) != null)
					borrowerPay += $('#borrowerOthers').val() * 1;
				$('#borrowerPay').val(borrowerPay);
				borrowerPay = 0;
				$('#familyPay').val($('#borrowerPay').val() * 1 + $('#borrowerSpousePay').val() * 1);
				$('#familyControllableIncome').val($('#familyIncome').val() * 1 - $('#familyPay').val() * 1);
			});
			// 配偶支出
			$(document).on("change", "#borrowerSpouseHousingloan, #borrowerSpouseCarloan, #borrowerSpouseCredit," +
					" #borrowerSpouseOthers", function(e) {
				var spousePay = 0;
				if($('#borrowerSpouseHousingloan').val().match(reg1) != null || $('#borrowerSpouseHousingloan').val().match(reg2) != null)
					spousePay += $('#borrowerSpouseHousingloan').val() * 1;
				if($('#borrowerSpouseCarloan').val().match(reg1) != null || $('#borrowerSpouseCarloan').val().match(reg2) != null)
					spousePay += $('#borrowerSpouseCarloan').val() * 1;
				if($('#borrowerSpouseCredit').val().match(reg1) != null || $('#borrowerSpouseCredit').val().match(reg2) != null)
					spousePay += $('#borrowerSpouseCredit').val() * 1;
				if($('#borrowerSpouseOthers').val().match(reg1) != null || $('#borrowerSpouseOthers').val().match(reg2) != null)
					spousePay += $('#borrowerSpouseOthers').val() * 1;
				$('#borrowerSpousePay').val(spousePay);
				spousePay = 0;
				$('#familyPay').val($('#borrowerPay').val() * 1 + $('#borrowerSpousePay').val() * 1);
				$('#familyControllableIncome').val($('#familyIncome').val() * 1 - $('#familyPay').val() * 1);
			});
		},
		initQuotaMeasureForm:function(){
			$("#quotaMeasureForm").validate({
				submitHandler: function(form) {
					$('#saveQuotaMeasure').attr({
						"disabled" : "disabled"
					});
					$.ajax({
						type : 'post',
						url : $$ctx + "bizapply/saveQuotaMeasure",
						data : $(form).serialize(),
						success: function(r) {
							$('#saveQuotaMeasure').removeAttr('disabled');
							if (r.success) {
								utils.alert.suc("<strong>" + r.msg + "</strong>");
								$("#quotaMeasureForm input[name=budgetId]").val(r.data.budgetId);
							} else {
								utils.alert.err("<strong>" + r.msg + "</strong>");
							}
						}
					});
				}
			});
			
			$.ajax({
				type : 'post',
				url : $$ctx + "bizapply/getQuotaMeasureByProjectId",
				data : {
					'projectId' : $('#projectId').val()
				},
				success: function(measure) {
					$.each($("#quotaMeasureForm").find("#applyAmt2, #repaymentThisTime, #monthIncome2, " +
							"#otherRepayment, #controllableIncome, #calcLoanTerm, #calcLoanAmt, #budgetId"), 
								function() {
						$(this).val(measure[$(this).attr("name")]);
					});
				}
			});
			
			$(document).on("change", "#applyAmt, #calcLoanTerm, #rateValue, #replyPeriodNum", function(e) {
				var loanAmount = utils.num.normal($('#applyAmt').val());
				var applyTerm = $('#calcLoanTerm').val();
				var rate = $('#rateValue').val();
				var repaymentNumber = $('#replyPeriodNum').val();
				var reg = /^-?[0-9]*(\.\d*)?$|^-?d^(\.\d*)?$/;
				var integerReg =  /^\d+$/;
				var validLoanAmount1 = loanAmount + "".match(reg) != null;
				var validLoanAmount2 = (parseFloat(loanAmount) >= parseFloat($('#minApplyAmt').val()) && 
		    		 					parseFloat(loanAmount) <= parseFloat($('#maxApplyAmt').val()));
				var validRate = rate.match(reg) != null;
				var validRepaymentNumber = repaymentNumber.match(integerReg) != null;
				if(loanAmount && applyTerm && rate && repaymentNumber && validLoanAmount1 && validLoanAmount2 &&
						validRate && validRepaymentNumber) {
					$.ajax({
						type : 'POST',
						url : $$ctx + "bizapply/getRepaymentThisTime",
						data : {
							'loanAmount' : loanAmount,
							'applyTerm' : applyTerm,
							'rate' : rate,
							'repaymentNumber' : repaymentNumber
						},
						success: function(r) {
							$('#repaymentThisTime').val(r);
						}
					});
					$('#calcLoanAmt').val(($('#controllableIncome').val() * 0.6 * $('#calcLoanTerm').val()).toFixed(2));
				} else {
					$('#repaymentThisTime').val('');
				}
			});
		},
		initeInteres: function(){
			$("#loanAmountBiz").val(utils.num.normal($('#applyAmt').val()));
			$("#applyTermBiz").val($("#renewalTermUnit").val());
			$("#repaymentBiz").val($("#repayingMode").val());
			$("#rateBiz").val($("#rateValue").val());
			$("#repaymentNumberBiz").val($("#replyPeriodNum").val());
		},
		resetForm: function(){
			$('#loanAmountBiz').val("");
			$('#applyTermBiz').val("");
			$('#repaymentBiz').val("");
			$('#rateBiz').val("");
			$('#repaymentNumberBiz').val("");
			$('#repaymentDateBiz').val("");
			$('#loanStartDateBiz').val("");
		},
		budget: function(){
			// 为额度测算赋值
			$("#applyAmt2").val(utils.num.normal($('#applyAmt').val()));
			$('#monthIncome2').val($('#familyIncome').val());
			$('#otherRepayment').val($('#familyPay').val());
			$('#controllableIncome').val($('#familyControllableIncome').val());
			
			var loanAmount = utils.num.normal($('#applyAmt').val());
			var applyTerm = $('#calcLoanTerm').val();
			var rate = $('#rateValue').val();
			var repaymentNumber = $('#replyPeriodNum').val();
			var reg = /^-?[0-9]*(\.\d*)?$|^-?d^(\.\d*)?$/;
			var integerReg =  /^\d+$/;
			var validLoanAmount1 = loanAmount + "".match(reg) != null;
			var validLoanAmount2 = (parseFloat(loanAmount) >= parseFloat($('#minApplyAmt').val()) && 
	    		 					parseFloat(loanAmount) <= parseFloat($('#maxApplyAmt').val()));
			var validRate = rate.match(reg) != null;
			var validRepaymentNumber = repaymentNumber.match(integerReg) != null;
			if(loanAmount && applyTerm && rate && repaymentNumber && validLoanAmount1 && validLoanAmount2 &&
					validRate && validRepaymentNumber) {
				$.ajax({
					type : 'POST',
					url : $$ctx + "bizapply/getRepaymentThisTime",
					data : {
						'loanAmount' : loanAmount,
						'applyTerm' : applyTerm,
						'rate' : rate,
						'repaymentNumber' : repaymentNumber
					},
					success: function(r) {
						$('#repaymentThisTime').val(r);
					}
				});
				$('#calcLoanAmt').val(($('#controllableIncome').val() * 0.6 * $('#calcLoanTerm').val()).toFixed(2));
			} else {
				$('#repaymentThisTime').val('');
			}
		},
		/** 贷款试算 */
		dkss : function() {
			var applyAmt = utils.num.normal($('#applyAmt').val());
			$('#countRateForm').resetForm();
			$('#loanAmountBiz').val(applyAmt);
			$('#countRateForm input[name=applyTerm]').val($('#basicInfoForm input[name=applyTerm]').val());
			$('#countRateForm select[name=applyTermUnit]').val($('#basicInfoForm input[name=applyTermUnit]').val());
			$('#repaymentBiz').val($('#repayingMode').val());
			$('#countRateForm input[name=rate]').val($('#basicInfoForm input[name=rateValue]').val());
			$('#repaymentNumberBiz').val($('#replyPeriodNum').val());
			$('#loanStartDateBiz').val($('#applyDate').val());
			$("#tbld").empty();
			$("#tbfoot").empty();
			$('#dkssModal').modal('show');
		},
		initCountRateForm : function() {
			$('#countRateForm').validate({
				submitHandler : function(form) {
					$.ajax({
						type : 'post',
						url : $$ctx + 'bizapply/loanCal',
						data : $('#countRateForm').serialize(),
						success : function(result) {
							if (result.success) {
								var list = result.data;
		                		$("tr").remove("#newAdd");
		                		var currentPricipalInterestTotal = 0;
		                		var currentPricipalTotal = 0;
		                		var currentInterestTotal = 0;
								for(var i=0;i<list.length;i++){
									currentPricipalInterestTotal += list[i].currentPricipalInterest;
									currentPricipalTotal += list[i].currentPricipal;
									currentInterestTotal += list[i].currentInterest;
									$("#tbld").append("<tr id='newAdd'>"+"<td>"+ list[i].repaymentNumber +"</td>"+"<td>"+ list[i].repaymentDate +"</td>"+
														 "<td>"+ list[i].currentPricipalInterest +"</td>"+"<td>"+list[i].currentPricipal +"</td>"+
														 "<td>"+ list[i].currentInterest +"</td>"+"<td>"+list[i].currentPricipal +"</td>"+
														 "<td>"+list[i].endCurrentPrincipalBalance  +"</td>"+"</tr>");
								}
								$("#tbfoot").append("<tr id='newAdd'>" +
										"<th></th>" +
										"<th>总计：</th>" +
										"<th>"+currentPricipalInterestTotal.toFixed(2)+"</th>" +
										"<th>"+currentPricipalTotal.toFixed(2)+"</th>" +
										"<th>"+currentInterestTotal.toFixed(2)+"</th>" +
										"<th></th>" +
										"<th></th>" +
										"</tr>");
							} else {
								utils.alert.err("<strong>因未知错误，计算失败！</strong>");
							}
						}
					});
				}
			});
		},
		resetCountRateForm : function() {
			$('#countRateForm').resetForm();
			$('#loanAmountBiz').val(utils.num.normal($('#applyAmt').val()));
			$('#applyTermBiz').val($('#renewalTerm').val());
			$('#repaymentBiz').val($('#repayingMode').val());
			$('#rateBiz').val($('#rateValue').val());
			$('#repaymentNumberBiz').val($('#replyPeriodNum').val());
			$("#tbld").empty();
			$("#tbfoot").empty();
		},
		initFamilyAssetsForm : function() {
			$("#familyAssetsForm").validate({
				submitHandler: function(form) {
					$('#saveFamilyAssets').attr({
						"disabled" : "disabled"
					});
					$.ajax({
						type : 'post',
						url : $$ctx + "bizapply/saveFamilyAssets",
						data : $(form).serialize(),
						success: function(r) {
							$('#saveFamilyAssets').removeAttr('disabled');
							if (r.success) {
								utils.alert.suc("<strong>" + r.msg + "</strong>");
								$("#familyAssetsForm input[name=id]").val(r.data.id);
								$("#familyAssetsForm div").removeClass("has-error");
							} else {
								utils.alert.err("<strong>" + r.msg + "</strong>");
							}
						}
					});
				}
			});
			
			$.ajax({
				type : 'post',
				url : $$ctx + "bizapply/getFamilyAssetsByProjectId",
				data : {
					'projectId' : $('#projectId').val()
				},
				success: function(familyAssets) {
					$.each($("#familyAssetsForm").find("input"), function() {
						if($(this).attr("name") != 'projectId')
							$(this).val(familyAssets[$(this).attr("name")]);
					});
				}
			});
			
			$(document).on("change", "#houseone input", function(e) {
				if($('#premisesPermitHouseone').val() || $('#areaHouseone').val() || 
						$('#evalValueHouseone').val() || $('#typeHouseone').val() || 
						$('#landSitHouseone').val() || $('#loanBalanceHouseone').val() || 
						$('#ownerHouseone').val()) {
					$.each($('#houseone').find('input'), function() {
						$(this).addClass('required');
					});
				} else if(!$('#premisesPermitHouseone').val() && !$('#areaHouseone').val() && 
						!$('#evalValueHouseone').val() && !$('#typeHouseone').val() && 
						!$('#landSitHouseone').val() && !$('#loanBalanceHouseone').val() && 
						!$('#ownerHouseone').val()) {
					$.each($('#houseone').find('input'), function() {
						$(this).removeClass('required');
					});
				}
			});
			$(document).on("change", "#housetwo input", function(e) {
				if($('#premisesPermitHousetwo').val() || $('#areaHousetwo').val() || 
						$('#evalValueHousetwo').val() || $('#typeHousetwo').val() || 
						$('#landSitHousetwo').val() || $('#loanBalanceHousetwo').val() || 
						$('#ownerHousetwo').val()) {
					$.each($('#housetwo').find('input'), function() {
						$(this).addClass('required');
					});
				} else if(!$('#premisesPermitHousetwo').val() && !$('#areaHousetwo').val() && 
						!$('#evalValueHousetwo').val() && !$('#typeHousetwo').val() && 
						!$('#landSitHousetwo').val() && !$('#loanBalanceHousetwo').val() && 
						!$('#ownerHousetwo').val()) {
					$.each($('#housetwo').find('input'), function() {
						$(this).removeClass('required');
					});
				}
			});
			$(document).on("change", "#carone input", function(e) {
				if($('#typeCarone').val() || $('#plateNumberCarone').val() || 
						$('#boughtYearCarone').val() || $('#evalValueCarone').val() || 
						$('#ownerCarone').val() || $('#loanBalanceCarone').val()) {
					$.each($('#carone').find('input'), function() {
						$(this).addClass('required');
					});
				} else if(!$('#typeCarone').val() && !$('#plateNumberCarone').val() && 
						!$('#boughtYearCarone').val() && !$('#evalValueCarone').val() && 
						!$('#ownerCarone').val() && !$('#loanBalanceCarone').val()) {
					$.each($('#carone').find('input'), function() {
						$(this).removeClass('required');
					});
				}
			});
		},
		openCustomerWindow: function() {
			$.ajax({
				type : 'post',
				url : $$ctx + 'businessapplicationwd/findOneParty',
				data : {
					"partyId" : $('#partyId').val()
				},
				success : function(party) {
					var url = '';
					if(party.partyTypeCd == '1')
						url = $$ctx + 'corpcustomer/showDetail/' +party.partyId+ '?consultLocation=business';
					else
						url = $$ctx + 'singleCustomer/backToForm?customerId=' + party.partyId +
								'&workCode=TODETAIL&customerSource=detail&consultLocation=contract';
					
					$('#partyIframe').attr('src' , url);
					$('#partyDetailDiv').modal('show');
				}
			});
        }
	});
	module.exports = view;
});