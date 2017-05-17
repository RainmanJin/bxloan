define(function(require, exports, module) {
	var reg = /^-?[0-9]*(\.\d*)?$|^-?d^(\.\d*)?$/;
	var integerReg =  /^\d+$/;
	var rm = require("./rm");
	var utils = require("utils");
	var view = Backbone.View.extend({
		el : "body",
		events : {
			"click #customerForFloatWindow": "openCustomerWindow"
		},
		initialize : function() {
			this.render();
		},
		render : function() {
			this.initPage();
			this.initFrameHight();
		},
		initFrameHight: function(){//初始化客户信息弹框的高度
 			$("#partyIframe").load(function() {
 				 setInterval(function(){
 	        	    	var clientHeight = $("#partyIframe").contents().find("body").height();
 	        	    	$("#partyIframe").attr("height",clientHeight+"px!important;");
 	        	 },100);
 			});
 		},
		initPage : function() {
			/**
			 * 格式化数字（保留两位小数）
			 */
			var applyAmt = $('#applyAmt').val();
			var preRepaymentRate = $('#preRepaymentRate').val();
			var irNegoSymbCd = $('#irNegoSymbCd').val();
			var bizRate = $('#bizRate').val();
			var ovdueRate = $('#ovdueRate').val();
			var perculNegoRate = $('#perculNegoRate').val();
			var prePremium = $('#prePremium').val();
			var applyUsefulAmt = $('#applyUsefulAmt').val();//授信可用额度
			var applyAmts = $('#applyAmts').val();//授信额度
			if(applyAmt)
				$('#applyAmt').val(utils.number.toAmt(applyAmt));
			if(preRepaymentRate)
				$('#preRepaymentRate').val(utils.number.toAmt(preRepaymentRate));
			if(irNegoSymbCd)
				$('#irNegoSymbCd').val(utils.number.toAmt(irNegoSymbCd));
			if(bizRate)
				$('#bizRate').val(utils.number.toAmt(bizRate));
			if(ovdueRate)
				$('#ovdueRate').val(utils.number.toAmt(ovdueRate));
			if(perculNegoRate)
				$('#perculNegoRate').val(utils.number.toAmt(perculNegoRate));
			if(prePremium)
				$('#prePremium').val(utils.number.toAmt(prePremium));
			if(applyUsefulAmt)
				$('#applyUsefulAmt').val(utils.number.toAmt(applyUsefulAmt));
			if(applyAmts)
				$('#applyAmts').val(utils.number.toAmt(applyAmts));
			this.judgeRepayingMode();
			/** 去掉在数字文本框内输入的002,010等无效0 */
			$(document).on("blur", "#applyAmt, #applyTermForBiz, #preRepaymentRate, #bizRate, #irNegoSymbCd, " +
					"#ovdueRate, #perculNegoRate", function(e) {
				var value = $(this).val();
				var id = $(this).attr('id');
				if($(this).attr('id') == 'applyTermForBiz' && value && value.match(integerReg) != null) {
					$('#applyTermForBiz').val(parseInt(value));
				} else if(($(this).attr('id') == 'prePremium') && value && value.match(reg) != null) {
					$(this).val(parseFloat(value));
				} else if($(this).attr('id') == 'applyAmt' && $("#applyAmt").valid()) {
					utils.num.thousands(this);
				} else if(value && value.match(reg) != null) {
					$(this).val(parseFloat(value));
				}
			});
			
			/** 绑定有关保险信息所需的值得change事件，当其改变时，对应改变应收保费的值 */
			$(document).on("change", "#applyDate, #applyAmt, #applyTermForBiz, #applyTermUnitForBiz, " +
					"#guaranteeMode, #bizRate", function(e) {
				if($('#irTypeCd').val() == '2') {// 浮动利率
					var applyTerm = $('#applyTermForBiz').val();
					var applyTermUnit = $('#applyTermUnitForBiz').val();
					var irNegoSymbCd = $('#irNegoSymbCd').val();
					
					var validateApplyTerm1 = /^[1-9]\d*$/.test(applyTerm);
					var validateApplyTerm2 = ((/^[1-9]\d*$/.test(applyTerm))&&(parseInt(applyTerm)>0&&parseInt(applyTerm)<=998));
					var validateIrNegoSymbCd1 = /^[1-9]\d*$/.test(irNegoSymbCd);
					var validateIrNegoSymbCd2 = /^\d+(\.\d{1,4})?$/.test(irNegoSymbCd);
					if(!applyTerm || !applyTermUnit || !irNegoSymbCd || !validateApplyTerm1 || 
							!validateApplyTerm2 || !validateIrNegoSymbCd1 || !validateIrNegoSymbCd2) {
					} else {
						$.ajax({
							type : 'POST',
							url : $$ctx + 'businessapplicationwd/countRateByFloatRate',
							data : {
								"applyTerm" : applyTerm,
								"applyTermUnit" : applyTermUnit,
								"floatRate" : irNegoSymbCd
							},
							success : function(bizRate) {
								$('#bizRate').val(bizRate.toFixed(2));
								$('#bizRate').change();
							}
						});
					}
				}
			});
			if($('#type').val() == 'check'){  //type为check是，只能进行查看，控件都不可用
				$.each($("div").find(".page-content input[id!=query_documentName][name!=query_contentType][name!=documentNums][id!=wdcb]," +
				" .page-content select, .page-content textarea, #showTree_"), function() {
			     $(this).attr('disabled',true);
		        });
			}
		},
		judgeRepayingMode : function() {
			var repayingMode = $('#repayingMode').val();
			var replyPeriodNumInput= $('#replyPeriodNumInput');
			if(replyPeriodNumInput.val()==""){
				replyPeriodNumInput.val('1');
			}
			if (repayingMode == '2') {
				$('#replyPeriodNum').val('');
				$('#replyPeriodNumShowHide').hide();
				$('#repaymentPlanDiv').hide();
			} else if(repayingMode == '3') {
				$('#replyPeriodNum').val('');
				$('#replyPeriodNumShowHide').hide();
				
				$('#startDate').val('');
				$('#monthDate').val('');
				$('#repayDateForCount').val('');
				setTimeout(function() {
						$("#batchInitRepayPlan").attr({
							"disabled" : "disabled"
						});
				}, 100);
				$('#repaymentPlanDiv').show();
			} else {
				$('#replyPeriodNum').val(replyPeriodNumInput.val());
				$('#replyPeriodNumShowHide').show();
				$('#repaymentPlanDiv').hide();
			}
		},
		openCustomerWindow: function() {
			$.ajax({
				type : 'post',
				url : $$ctx + 'businessapplicationwd/findOneParty',
				data : {
					"partyId" : $('#creditPartyId').val()
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