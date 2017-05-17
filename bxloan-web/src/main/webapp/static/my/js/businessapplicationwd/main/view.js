define(function(require, exports, module) {
	var reg = /^-?[0-9]*(\.\d*)?$|^-?d^(\.\d*)?$/;
	var integerReg =  /^\d+$/;
	var model = require("./model");
	var rm = require("./rm");
	var utils = require("utils");
	var view = Backbone.View.extend({
		el : "body",
		events : {
			"click #customerForFloatWindow": "openCustomerWindow"
		},
		initialize : function() {
			this.model = new model();
			this.render();
		},
		render : function() {
			this.initPage();
			this.initFrameHight();
		},
		initFrameHight: function(){
 			$("#partyIframe").load(function() {
 				 setInterval(function(){
 	        	    	var clientHeight = $("#partyIframe").contents().find("body").height();
 	        	    	$("#partyIframe").attr("height",clientHeight+"px!important;");
 	        	 },100);
 			});
 		},
		initPage : function() {
			
			$("#basicInfoForm").find("select[name='argoBizType']").val($("#bizType").val());
			
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
			if(applyAmt)
				utils.num.thousands('#applyAmt');
			if(preRepaymentRate)
				$('#preRepaymentRate').val(parseFloat(preRepaymentRate));
			if(irNegoSymbCd)
				$('#irNegoSymbCd').val(parseFloat(irNegoSymbCd));
			if(bizRate)
				$('#bizRate').val(parseFloat(bizRate));
			if(ovdueRate)
				$('#ovdueRate').val(parseFloat(ovdueRate));
			if(perculNegoRate)
				$('#perculNegoRate').val(parseFloat(perculNegoRate));
			if(prePremium)
				$('#prePremium').val(parseFloat(prePremium).toFixed(2));
			
			if ($('#type').val() == 'add') {
				$('select:enabled[id!=nextTaskReceiver]').val('');
			} else if($('#type').val() == 'edit') {
				// 选中担保方式
				var guaranteeMode = $('#guaranteeMode').val();
    			$.each(guaranteeMode.split(","), function(i,val) {
    				var check = $('#basicInfoForm').find("input[type='checkbox'][value='" + val + "']");
    				if(check[0])
    					check[0].checked = true;
    			});
    			// 选中总部协同业务
    			var isheadcol = $('#isheadcol').val();
    			if(isheadcol){
    				$('#basicInfoForm').find(':radio[name="isheadcol"][value="' + isheadcol + '"]').prop("checked",true);
    			}
    			/*$('#basicInfoForm').find(":radio[name='isheadcol'][value='" + isheadcol + "']")
    			var check = $('#basicInfoForm').find(":radio[name='isheadcol'][value='" + isheadcol + "']");
				if(check[0])
					check[0].checked = true;*/
    			
    			if(guaranteeMode) {
    				// 根据担保方式确定哪些“新增关联”按钮可用
        			if(guaranteeMode.indexOf('1') >= 0 || guaranteeMode.indexOf('2') >= 0) {// 选中抵、质押
        				$('#addPawn').removeAttr('disabled');
        				//$('#insureDiv').show();
        			}
        			if(guaranteeMode.indexOf('3') >= 0) {// 选中保证
        				$('#addBail').removeAttr('disabled');
        				//$('#insureDiv').show();
        			}
    			}
    			
    			if($('#bizRateId').val())// 说明是已经保存的基本信息
    				$('#dkss').removeAttr('disabled');
    			
    			this.judgeRepayingMode();
    			this.judgeIrTypeCd();
    			this.judgeIfInsure();
			} else {
				// 选中担保方式
				var guaranteeMode = $('#guaranteeMode').val();
    			$.each(guaranteeMode.split(","),function(i,val){
    				var check = $('#basicInfoForm').find("input[type='checkbox'][value='"+val+"']");
    				if(check[0])
    					check[0].checked = true;
    			});
    			// 选中总部协同业务
    			var isheadcol = $('#isheadcol').val();
    			if(isheadcol){
    				$('#basicInfoForm').find(':radio[name="isheadcol"][value="' + isheadcol + '"]').prop("checked",true);
    			}
    			this.judgeRepayingMode();
    			this.judgeIrTypeCd();
				
				$.each($("div").find(".page-content input[id!=query_documentName][name!=query_contentType][name!=documentNums][id!=wdcb]," +
						" .page-content select, .page-content textarea, #showTree_"), function() {
					$(this).attr('disabled',true);
				});
				this.judgeIfInsure();
			}
			
			setTimeout(function() {
				/** 获取婚姻状况，决定“配偶是否作为共同还款人”是否可选 */
				if(!$('#basicInfoForm #marriageCd').val() || $('#basicInfoForm #marriageCd').val() != '20') {// 非个人客户或非已婚客户
					$('#mateBorrower').val('2');
					$('#mateBorrower').attr('disabled', true);
				}
			}, 100);
			
			
			/**
			 * 为不在CODE表中的select框赋值
			 */
			// 选定贷款产品
			$('#productType').val($('#productCd').val());
			// 选定保险机构
			$('#insuranceOrgId').val($('#insuranceOrg').val());
			
			/**
			 * 是否为联保体 20150629 Wangxy
			 */
			var unId = $('#unId_input').val();
			if(unId != ""){//是联保体
				$('#unId').val(unId);
			}else{//不是联保体
				$('#isUniteCustomer').val(2);
				$('#unId').val('');
			}
			if ($('#isUniteCustomer').val()=='2'){
				$('select[name="unId"]').attr('disabled',true);
				$('select[name="unId"]').val('');
			}
			
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
			/** 初始化申报日期 */
			initDateP("#applyDate");
			/** 初始化贷款试算-贷款时间 */
			initDateP("#loanStartDateBiz");
			
			/**
			 * 绑定勾选担保方式事件
			 */
			$(document).on("click", "#basicInfoForm input[type=checkbox]", function(e) {
				if($(this).is(':checked'))
					$('#guaranteeMode').val($('#guaranteeMode').val() + $(this).val() + ',');
				else
					$('#guaranteeMode').val($('#guaranteeMode').val().replace($(this).val() + ',',''));
				if($('#guaranteeMode').val().indexOf('1') >= 0 || $('#guaranteeMode').val().indexOf('2') >= 0 || 
						$('#guaranteeMode').val().indexOf('3') >= 0) {
					$('#ifInsure').attr('required', true);
					//$('#insureDiv').slideDown();
				} else {
					$('#ifInsure').val(2);
					$('#insuranceOrgId').val('');
					$('#prePremium').val('');
					$('#ifInsure').removeAttr('required');
					$('#insuranceOrgId').removeAttr('required');
					$('#prePremium').removeAttr('required');
					$('#insureDetailDiv').slideUp();
					//$('#insureDiv').slideUp();
				}
				$('#guaranteeMode').change();
			});
			
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
					"#guaranteeMode", function(e) {
//				if($('#ifInsure').val() == '1') {
//					var applyTermUnit = $('#applyTermUnitForBiz').val();
//					var insuranceOrgId = $('#insuranceOrgId').val();
//					var applyDate = $('#applyDate').val();
//					var applyAmt = utils.num.normal($('#applyAmt').val());
//					var applyTerm = $('#applyTermForBiz').val();
//					var guaranteeMode = $('#guaranteeMode').val();
//					var bizRate = $('#bizRate').val();
//					var validateApplyAmt1 = /^\d+(\.\d{1,2})?$/.test(applyAmt);
//					var validateApplyAmt2 = (parseFloat(applyAmt) >= parseFloat($('#minApplyAmt').val()) && 
//												parseFloat(applyAmt) <= parseFloat($('#maxApplyAmt').val()));
//					var validateApplyTerm1 = /^[1-9]\d*$/.test(applyTerm);
//					var validateApplyTerm2 = ((/^[1-9]\d*$/.test(applyTerm))&&(parseInt(applyTerm)>0&&parseInt(applyTerm)<=998));
//					var validateBizRate = ((/^\d+(\.\d{1,4})?$/.test(bizRate))&&(parseFloat(bizRate)>0&&parseFloat(bizRate)<=100));
//					if(!insuranceOrgId || !applyDate || !applyAmt || !applyTerm || 
//							!applyTermUnit || !guaranteeMode || !bizRate || !validateApplyAmt1 || 
//							!validateApplyAmt2 || !validateApplyTerm1 || !validateApplyTerm2 || !validateBizRate) {
//						$('#prePremium').val('');
//					} else {
//						$.ajax({
//							type : 'POST',
//							url : $$ctx + 'businessapplicationwd/getPrePremium',
//							data : {
//								"insuranceOrgId" : insuranceOrgId,
//								"applyAmt" : applyAmt,
//								"bizRate" : bizRate,
//								"applyDate" : applyDate,
//								"applyTerm" : applyTerm,
//								"applyTermUnit" : applyTermUnit,
//								"guaranteeMode" : guaranteeMode,
//								"irTypeCd" : $('#irTypeCd').val()
//							},
//							success : function(prePremium) {
//								$('#prePremium').val(parseFloat(prePremium));
//							}
//						});
//					}
//				}
				
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
					if($('#repaymentPlanTable tbody tr').html().indexOf('没有符合条件的记录') <= 0)
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
		judgeIrTypeCd : function() {
			var irTypeCd = $('#irTypeCd').val();
			if (irTypeCd == '1') {
				$('#adjustPeriod').val('');
				$('#irNegoSymbCd').val('');
				//$('#bizRate').addClass('required');
				//$('#bizRate').addClass('isRate');
				$('#adjustPeriod').removeClass('required');
				$('#irNegoSymbCd').removeClass('required');
				$('#irNegoSymbCd').removeClass('isPercentNumberFour');
				$('#bizRate').removeAttr("readonly");
				$('#adjustPeriodAndFloatRateShowHide').hide();
			} else {
				$('#adjustPeriod').addClass('required');
				$('#irNegoSymbCd').addClass('required');
				$('#irNegoSymbCd').addClass('isPercentNumberFour');
				$('#adjustPeriodAndFloatRateShowHide').show();
				$('#bizRate').attr("readonly", "readonly");
//				if($('#ifInsure').val() == '1') {
//					var applyTermUnit = $('#applyTermUnitForBiz').val();
//					var insuranceOrgId = $('#insuranceOrgId').val();
//					var applyDate = $('#applyDate').val();
//					var applyAmt = utils.num.normal($('#applyAmt').val());
//					var applyTerm = $('#applyTermForBiz').val();
//					var guaranteeMode = $('#guaranteeMode').val();
//					var bizRate = $('#bizRate').val();
//					var validateApplyAmt1 = /^\d+(\.\d{1,2})?$/.test(applyAmt);
//					var validateApplyAmt2 = (parseFloat(applyAmt) >= parseFloat($('#minApplyAmt').val()) && 
//												parseFloat(applyAmt) <= parseFloat($('#maxApplyAmt').val()));
//					var validateApplyTerm1 = /^[1-9]\d*$/.test(applyTerm);
//					var validateApplyTerm2 = ((/^[1-9]\d*$/.test(applyTerm))&&(parseInt(applyTerm)>0&&parseInt(applyTerm)<=998));
//					var validateBizRate = ((/^\d+(\.\d{1,4})?$/.test(bizRate))&&(parseFloat(bizRate)>0&&parseFloat(bizRate)<=100));
//					if(!insuranceOrgId || !applyDate || !applyAmt || !applyTerm || 
//							!applyTermUnit || !guaranteeMode || !bizRate || !validateApplyAmt1 || 
//							!validateApplyAmt2 || !validateApplyTerm1 || !validateApplyTerm2 || !validateBizRate) {
//						$('#prePremium').val('');
//					} else {
//						$.ajax({
//							type : 'POST',
//							url : $$ctx + 'businessapplicationwd/getPrePremium',
//							data : {
//								"insuranceOrgId" : insuranceOrgId,
//								"applyAmt" : applyAmt,
//								"bizRate" : bizRate,
//								"applyDate" : applyDate,
//								"applyTerm" : applyTerm,
//								"applyTermUnit" : applyTermUnit,
//								"guaranteeMode" : guaranteeMode,
//								"irTypeCd" : $('#irTypeCd').val()
//							},
//							success : function(prePremium) {
//								$('#prePremium').val(parseFloat(prePremium));
//							}
//						});
//					}
//				}
			}
		},
		judgeIfInsure : function() {
			if(($('#guaranteeMode').val().indexOf('1') >= 0 || $('#guaranteeMode').val().indexOf('2') >= 0 || 
					$('#guaranteeMode').val().indexOf('3') >= 0) && $('#ifInsure').val() == '1') {
				$('#ifInsure').attr('required', true);
				$('#insuranceOrgId').attr('required', true);
				$('#prePremium').attr('required', true);
				//$('#insureDiv').slideDown();
				$('#insureDetailDiv').slideDown();
			}
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