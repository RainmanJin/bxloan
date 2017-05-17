define(function(require, exports, module) {
	var reg = /^-?[0-9]*(\.\d*)?$|^-?d^(\.\d*)?$/;
	var integerReg =  /^\d+$/;
	
	var model = require("../main/model");
	var rm = require("./rm");
	var utils = require("utils");
	var view = Backbone.View.extend({
		el : "body",
		events : {
			"change #repayingMode" : "judgeRepayingMode",
			"change #irTypeCd" : "judgeIrTypeCd",
			"change #insuranceOrgId" : "insuranceOrgIdOnChange",
			"change #irNegoSymbCd" : "floatRateOnChange",
			"change #applyTermUnitSelect" : "floatRateOnChange",
			"change #applyTermUnitForBiz" : "floatRateOnChange",
			"change #ifInsure" : "ifInsureOnChange",
			"change #isUniteCustomer" : "ifUniteCustomer",
			"change #repaymentBiz" : "judgeRepaymentBiz",
			"click #showTree_" : "ToggleTree",
			"click #dkss" : "dkss",
			"click #resetCountRateForm": "resetCountRateForm"
		},
		initialize : function() {
			this.model = new model();
			this.agriculturalLoan=utils.parseBool($("#agriculturalLoan").val());//是否是邦农贷
			this.render();
		},
		render : function() {
			this.initBasicInfoForm();
			this.initTree();
			this.initRepaymentPlanTable();
			this.initCountRateForm();
			this.judgeRepaymentBiz();
			this.prePremiumValidate();
		},
		initBasicInfoForm : function() {
			var applyTermUnit = $('#applyTermUnitFromProduct').val();
			if(applyTermUnit=="" && $('#type').val() != 'check'){
				$("#applyTermUnitSelect").attr("disabled",false);
			}
			$('#bizRate').addClass('required');
			$('#bizRate').addClass('isRate');
			
			$(".prePremiumValidate").bind("change", function(e) {
				var data = {};
				//担保方式只选择了信用
				if($('#guaranteeMode').val().indexOf('4') == 0 )   {
					return;
				}
				//判断是否有保险一项选择否
				if($("#ifInsure").val() == "2") return;
				$.each($(".prePremiumValidate"), function(i, item) {
					//验证输入框是否符合校验规则
					if (!$(item).valid()) return;
					if (data[item.name]) {
						data[item.name] = data[item.name] + "," + item.value;
					} else {
						data[item.name] = item.value;
					}
				});
				//alert(JSON.stringify(data));
				$.ajax({
					type : 'post',
					url : $$ctx + "businessapplicationwd/getPrePremium",
					data : data,
					success : function(result) {
						$("#prePremium").val(result.toFixed(2));
					}
				});
			});
			$('#basicInfoForm').validate({
				rules: rm.rules,
                messages: rm.messages,
				submitHandler : function(form) {
					var guaranteeMode = $('#guaranteeMode').val();
					var guaranteeModes=guaranteeMode.split(",");//担保方式集合
					var repayingMode = $('#repayingMode').val();
					var $form=$("#basicInfoForm");
					var productType=$form.find('select[name="productType"]').val();
					var applyAmt=$form.find(':input[name="applyAmt"]').val();
					applyAmt=utils.number.delcommafy(applyAmt);//去掉千分位分割
					//校验担保方式
					if(agriculturalLoan&&applyAmt>50000&&applyAmt<=100000&&$.inArray('3',guaranteeModes)<0){
						utils.alert.warn("<strong>担保方式必须包含【保证】这种担保方式</strong>");
						return;
					}
					/**
					 * 联保体校验
					 * businessapplicationwd/main/view.js中赋值
					 * @author wangxy 20150629
					 */
					var isUniteCustomer=$('#isUniteCustomer').val();
					if(isUniteCustomer=='1' && guaranteeMode.split(",").indexOf('3')<0){
						utils.alert.warn("<strong>联保体用户必须选择【保证】担保方式</strong>");
						return;
					}
					if (isUniteCustomer=='1' && $('#unId').val()==null){
						utils.alert.warn("<strong>请选择一个联保体</strong>");
						return;
					}
					
					if (!guaranteeMode || guaranteeMode == '') {
						utils.alert.warn("<strong>请至少选择一种担保方式！</strong>");
					} else {
						var $amt = $('#applyAmt');
						var applyAmt = utils.num.normal($amt.val());
						$amt.val(applyAmt);
						$("#saveBasicInfo").attr({
							"disabled" : "disabled"
						});
						var data = $(form).serialize();
						//szx2015-04-27期限单位原来已有的话，serialize取不到值，所以特殊处理一下，
						if(data.indexOf("applyTermUnit")<0){
							data += "&applyTermUnit="+$("#applyTermUnitSelect").val();
						}
						$.ajax({
							type : 'post',
							url : $$ctx + 'businessapplicationwd/saveBusiness',
							data : data,
							success : function(result) {
								$("#saveBasicInfo").removeAttr("disabled");
								utils.num.thousands('#applyAmt');
								if (result.success) {
									utils.alert.suc("<strong>" + result.msg + "</strong>", function() {
										window.location.href = $$ctx + "bizapply/editApplication/" + $("#projectId").val() + "/" + $("#workflowId").val() + "/" + $("#taskId").val();
									});
//									$('#bizRateId').val(result.data);
//									$('#addExpenseRate').removeAttr('disabled');
//									if(guaranteeMode.indexOf('1') >= 0 || guaranteeMode.indexOf('2') >= 0)// 选中抵、质押
//										$('#addPawn').removeAttr('disabled');
//									else
//										$('#addPawn').attr({'disabled' : 'disabled'});
//									if(guaranteeMode.indexOf('3') >= 0)// 选中保证
//										$('#addBail').removeAttr('disabled');
//									else
//										$('#addBail').attr({'disabled' : 'disabled'});
//									$('#dkss').removeAttr('disabled');
//									$("#bizExpenseTable").dataTable().fnDraw();
//									$("#table").dataTable().fnDraw();
//									$("#bailTable").dataTable().fnDraw();
//									$("#commonBorrowerTable").dataTable().fnDraw();
//									$("#repaymentPlanTable").dataTable().fnDraw();
//									$("#dkss_repaymentPlanTable").dataTable().fnDraw();
//									setTimeout(function() {
//										if($('#repaymentPlanTable tbody tr').html().indexOf('没有符合条件的记录') > 0)
//											$("#batchInitRepayPlan").removeAttr('disabled');
//									}, 100);
								} else {
									utils.alert.err("<strong>" + result.msg + "</strong>");
								}
							}
						});
					}
				}
			});
		},
		initTree : function() {
			var viewSelf = this;
			$.fn.zTree.init($("#tree_"), {
				async : {
					enable : true,
					url : $$ctx + "bizapply/getAllIndustry"
				},
				data : {
					simpleData : {
						enable : true,
						idKey : "industryTypeCd",
						pIdKey : "parentIndustryTypeCd"
					},
					key : {
						name : "industryTypeName"
					}
				},
				check : {
					enable : true,
					chkStyle : "radio",
					radioType : "all"
				},
				callback : {
					onClick : function(event, treeId, treeNode) {
						if (treeNode!=null&&treeNode.children!=null&&treeNode.children.length!=null&&treeNode.children.length>0){
							$("#industryTypeCdField_").val("");
                            $("#industryCdMask_").val("");
                            $("#industryCd_").val("");
                    		return false;
                    	} else {
                    		var industryTypeCd = treeNode.industryTypeCd;
                        	var treeObj = $.fn.zTree.getZTreeObj(treeId);
                        	var node = treeObj.getNodeByParam("industryTypeCd", industryTypeCd, null);
                        	treeObj.checkNode(node, true, true);
                        	var parentNode = node.getParentNode();
                            treeObj.expandNode(parentNode, true, false);
                        	$("#industryTypeCdField_").val(treeNode.industryTypeCd);
                            $("#industryCdMask_").val(treeNode.industryTypeName);
                            $("#industryCd_").val(treeNode.industryTypeCd);
                            $("#controlZTree").toggle(300,
                            function() {
                                if (($("#controlZTree").attr("style")) == "") {
                                    $("#showTree_")[0].innerHTML = "<i class='ace-icon fa fa-eye'></i>";
                                } else if (($("#controlZTree").attr("style")) == "display: none;") {
                                    $("#showTree_")[0].innerHTML = "<i class='ace-icon fa fa-eye'></i>";
                                } else {
                                    $("#showTree_")[0].innerHTML = "<i class='ace-icon fa fa-eye'></i>";
                                }
                            });
                    	}
					},
					onCheck : function(event, treeId, treeNode) {
						$("#industryTypeCdField__").val(treeNode.industryTypeCd);
						$("#industryCdMask_").val(treeNode.industryTypeName);
						$("#industryCd_").val(treeNode.industryTypeCd);
					},
					beforeCheck : function(treeId, treeNode) {
						//点击radio时赋值
						$("#industryTypeCdField_").val(treeNode.industryTypeCd);
                        $("#industryCd_").val(treeNode.industryTypeCd);
						return !treeNode.isParent;
					},
					onAsyncSuccess : function(event, treeId, treeNode, msg) {
						var treeObj = $.fn.zTree.getZTreeObj(treeId);
						var industryTypeCd = $("#industryTypeCdField_").val();
						if (industryTypeCd !== "") {
							var node = treeObj.getNodeByParam("industryTypeCd",
									industryTypeCd, null);
							treeObj.checkNode(node, true, true);
							var parentNode = node.getParentNode();
							treeObj.expandNode(parentNode, true, false);
							$("#industryCdMask_").val(node.industryTypeName);
							$("#industryCd_").val(node.industryTypeCd);
						}
					}
				}
			});

			$.fn.zTree.init($("#cus_tree"), {
				async : {
					enable : true,
					url : $$ctx + "bizapply/getAllIndustry"
				},
				data : {
					simpleData : {
						enable : true,
						idKey : "industryTypeCd",
						pIdKey : "parentIndustryTypeCd"
					},
					key : {
						name : "industryTypeName"
					}
				},
				check : {
					enable : true,
					chkStyle : "radio",
					radioType : "all"
				},
				callback : {
					beforeCheck : function(treeId, treeNode) {
						return !treeNode.isParent;
					},
					onAsyncSuccess : function(event, treeId, treeNode, msg) {
						var treeObj = $.fn.zTree.getZTreeObj(treeId);
						var industryTypeCd = $("#cus_industryTypeCdField").val();
						if (industryTypeCd !== "") {
							var node = treeObj.getNodeByParam("industryTypeCd",
									industryTypeCd, null);
							treeObj.checkNode(node, true, true);
							var parentNode = node.getParentNode();
							treeObj.expandNode(parentNode, true, false);
							$("#cus_industryCdMask").val(node.industryTypeName);
							$("#cus_industryCd").val(node.industryTypeCd);
						}
					}
				}
			});
		},
		initRepaymentPlanTable : function() {
			if($('#type').val() != 'check') {
				$("#repaymentPlanTable").dataTable({
					sAjaxSource : $$ctx + "businessapplicationwd/searchRepaymentPlanList",
					bFilter : false,
					bLengthChange : false,
					iDisplayLength : 15,
					aoColumnDefs : [ {
						aTargets : [ 2 ],
						mRender : function(data, type, full) {
							return "<div class='btn-group'style='width:100px;'><button title='修改' type='button' role='editRepayPlan' data-id='" + data + "' class='btn btn-xs btn-info'><i class='ace-icon fa fa-edit'></i></button>"+
								" <button title='删除' type='button' role='deleteRepayPlan' data-id='" + data + "' class='btn btn-xs btn-danger'><i class='ace-icon fa fa-trash-o'></i></button></div>";
						}
					} ],
					fnServerParams : function(aoData) {
						aoData.push({
							name : "projectNo",
							value : $('#projectNo').val()
						});
					}
				});
			} else {
				$("#repaymentPlanTable").dataTable({
					sAjaxSource : $$ctx + "businessapplicationwd/searchRepaymentPlanList",
					bFilter : false,
					bLengthChange : false,
					fnServerParams : function(aoData) {
						aoData.push({
							name : "projectNo",
							value : $('#projectNo').val()
						});
					}
				});
			}
			
			$(document).on("click", "button[id='addRepayPlan']", function(e) {
				$('#approvalHistoryRepayPlanId').val('');
				$('#repayPlanForm').resetForm();
				$('#repayPlanModal').modal('show');
			});
			
			$(document).on("click", "button[id='batchInitRepayPlan']", function(e) {
				var startDate = $('#startDate').val();
				var monthDate = $('#monthDate').val();
				var repayDateForCount = $('#repayDateForCount').val();
				
				var applyAmt = utils.num.normal($('#applyAmt').val());
				var applyTerm = $('#applyTermForBiz').val();
				var applyTermUnit = $('#applyTermUnitSelect').val();
				
				if(!startDate)
					utils.alert.warn("<strong>请选择开始日期！</strong>");
				else if(!monthDate)
					utils.alert.warn("<strong>请输入间隔月数！</strong>");
				else if(!repayDateForCount)
					utils.alert.warn("<strong>请输入每月还款日！</strong>");
				else if(monthDate.match(integerReg) == null)
					utils.alert.warn("<strong>间隔月数不合法！</strong>");
				else if(repayDateForCount.match(integerReg) == null)
					utils.alert.warn("<strong>每月还款日不合法！</strong>");
				else if(repayDateForCount <= 0 || repayDateForCount >= 31)
					utils.alert.warn("<strong>每月还款日不合法！</strong>");
				else if(!applyAmt)
					utils.alert.warn("<strong>请输入申请金额！</strong>");
				else if(applyAmt + "".match(reg) == null)
					utils.alert.warn("<strong>申请金额不合法！</strong>");
				else if(!applyTerm)
					utils.alert.warn("<strong>请输入贷款期限！</strong>");
				else if(applyTerm.match(integerReg) == null)
					utils.alert.warn("<strong>贷款期限不合法！</strong>");
				else if(!applyTermUnit)
					utils.alert.warn("<strong>请选择期限单位！</strong>");
				else {
					$("#batchInitRepayPlan").attr({
						"disabled" : "disabled"
					});
					$.ajax({
						type : 'post',
						url : $$ctx + 'businessapplicationwd/batchInitRepayPlan',
						data : {
							'projectId' : $('#projectId').val(),
							'applyAmt' : applyAmt,
							'applyTerm' : applyTerm,
							'applyTermUnit' : applyTermUnit,
							'startDate' : startDate,
							'monthDate' : monthDate,
							'repayDateForCount' : repayDateForCount
						},
						success : function(r) {
							if(r.success) {
								utils.alert.suc("<strong>" + r.msg + "</strong>");
								$("#repaymentPlanTable").dataTable().fnDraw();
								$("#dkss_repaymentPlanTable").dataTable().fnDraw();
							} else {
								utils.alert.err("<strong>" + r.msg + "</strong>");
								$("#batchInitRepayPlan").removeAttr("disabled");
							}
						}
					});
				}
			});
			
			$(document).on("click", "button[role='editRepayPlan']", function(e) {
				$.ajax({
					type : 'post',
					url : $$ctx + 'businessapplicationwd/getRepayPlan',
					data : {
						'id' : $(this).data("id")
					},
					success : function(repayPlan) {
						$.each($("#repayPlanForm").find("input"), function() {
							$(this).val(repayPlan[$(this).attr("name")]);
						});
						$('#repayPlanModal').modal('show');
					}
				});
			});
			
			$(document).on("click", "button[role='deleteRepayPlan']", function(e) {
				var id = $(this).data("id");
				utils.button.confirm(null, function(result) {
					if (result) {
						$.ajax({
							type : 'post',
							url : $$ctx + 'businessapplicationwd/deleteRepayPlan',
							data : {
								'id' : id
							},
							success : function(r) {
								if(r.success) {
									utils.alert.suc("<strong>" + r.msg + "</strong>");
									var table = $("#repaymentPlanTable").dataTable();
									table.fnSettings()._iDisplayStart = 0;
									table.fnDraw();
									setTimeout(function() {
										if($('#repaymentPlanTable tbody tr').html().indexOf('没有符合条件的记录') > 0)
											$("#batchInitRepayPlan").removeAttr('disabled');
									}, 100);
								} else {
									utils.alert.err("<strong>" + r.msg + "</strong>");
								}
							}
						});
					}
				});
			});
			
			/**
			 * 初始化还款计划-开始日期,计划还款时间
			 */
			// 将datepicker控件放在最顶层
			var dateOnTop = function() {
				$(".datepicker").css("z-index", "99999");
			};
			var defaultDateConf = {
				autoclose : true,
				todayHighlight : true,
				clearBtn : true
			};
			var initDateP = function(selector, changeCallBack, config) {
				var _dateConf = config || defaultDateConf;
				$(selector).datepicker(_dateConf).on("changeDate",
						changeCallBack).on("click", dateOnTop);
			};
			initDateP("#repayDate");
			initDateP("#startDate");
			initDateP("#dkss_repayDate");
			
			$('#repayPlanForm').validate({
				messages : rm.messages,
				submitHandler : function(form) {
					$("#sureRepayPlanForm").attr({
						"disabled" : "disabled"
					});
					$.ajax({
						type : 'post',
						url : $$ctx + 'businessapplicationwd/saveRepayPlan',
						data : $(form).serialize(),
						success : function(result) {
							$("#sureRepayPlanForm").removeAttr("disabled");
							if (result.success) {
								utils.alert.suc("<strong>" + result.msg + "</strong>");
								$('#repayPlanModal').modal('hide');
								var table = $("#repaymentPlanTable").dataTable();
								table.fnSettings()._iDisplayStart = 0;
								table.fnDraw();
								$("#dkss_repaymentPlanTable").dataTable().fnDraw();
								setTimeout(function() {
									if($('#repaymentPlanTable tbody tr').html().indexOf('没有符合条件的记录') <= 0)
										$("#batchInitRepayPlan").attr({
											"disabled" : "disabled"
										});
								}, 100);
							} else {
								utils.alert.err("<strong>" + result.msg + "</strong>");
							}
						}
					});
				}
			});
			
			//addUserDefindPlan
			$(document).on("click", "#addUserDefindPlan", function(e) {
				$('#dkss_repayPlanId').val('');
				$('#dkss_repayPlanForm').resetForm();
				$('#dkss_repayPlanModal').modal('show');
			});
			
			$("#dkss_repaymentPlanTable").dataTable({
				sAjaxSource : $$ctx + "businessapplicationwd/searchRepaymentPlanTempList",
				bFilter : false,
				bLengthChange : false,
				fnServerParams : function(aoData) {
					aoData.push({
						name : "projectNo",
						value : $('#projectNo').val()
					});
				},
				iDisplayLength : 15,
				aoColumnDefs : [ {
					aTargets : [ 2 ],
					mRender : function(data, type, full) {
						return "<div class='btn-group'style='width:100px;'><button title='修改' type='button' role='dkss_editRepayPlan' data-id='" + data + "' class='btn btn-xs btn-info'><i class='ace-icon fa fa-edit'></i></button>"+
							" <button title='删除' type='button' role='dkss_deleteRepayPlan' data-id='" + data + "' class='btn btn-xs btn-danger'><i class='ace-icon fa fa-trash-o'></i></button></div>";
					}
				} ],
			});
			
			$('#dkss_repayPlanForm').validate({
				submitHandler : function(form) {
					$("#dkss_sureRepayPlanForm").attr({
						"disabled" : "disabled"
					});
					$.ajax({
						type : 'post',
						url : $$ctx + 'businessapplicationwd/saveRepayPlanTemp',
						data : $(form).serialize(),
						success : function(result) {
							$("#dkss_sureRepayPlanForm").removeAttr("disabled");
							if (result.success) {
								utils.alert.suc("<strong>" + result.msg + "</strong>");
								$('#dkss_repayPlanModal').modal('hide');
								var table = $("#dkss_repaymentPlanTable").dataTable();
								table.fnSettings()._iDisplayStart = 0;
								table.fnDraw();
							} else {
								utils.alert.err("<strong>" + result.msg + "</strong>");
							}
						}
					});
				}
			});
			
			$(document).on("click", "button[role='dkss_editRepayPlan']", function(e) {
				$.ajax({
					type : 'post',
					url : $$ctx + 'businessapplicationwd/getRepayPlanTemp',
					data : {
						'id' : $(this).data("id")
					},
					success : function(repayPlan) {
						$.each($("#dkss_repayPlanForm").find("input"), function() {
							$(this).val(repayPlan[$(this).attr("name")]);
						});
						$('#dkss_repayPlanModal').modal('show');
					}
				});
			});
			
			$(document).on("click", "button[role='dkss_deleteRepayPlan']", function(e) {
				var id = $(this).data("id");
				utils.button.confirm(null, function(result) {
					if (result) {
						$.ajax({
							type : 'post',
							url : $$ctx + 'businessapplicationwd/deleteRepayPlanTemp',
							data : {
								'id' : id
							},
							success : function(r) {
								if(r.success) {
									utils.alert.suc("<strong>" + r.msg + "</strong>");
									var table = $("#dkss_repaymentPlanTable").dataTable();
									table.fnSettings()._iDisplayStart = 0;
									table.fnDraw();
								} else {
									utils.alert.err("<strong>" + r.msg + "</strong>");
								}
							}
						});
					}
				});
			});
		},
		judgeRepayingMode : function() {
			var repayingMode = $('#repayingMode').val();
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
				$('#replyPeriodNum').val($('#replyPeriodNumInput').val());
				$('#replyPeriodNumShowHide').show();
				$('#repaymentPlanDiv').hide();
			}
		},
		judgeIrTypeCd : function() {
			var irTypeCd = $('#irTypeCd').val();
			if (irTypeCd == '1') {
				$('#adjustPeriod').val('');
				$('#irNegoSymbCd').val('');
				$('#bizRate').addClass('required');
				$('#bizRate').addClass('isRate');
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
//								$('#prePremium').val(parseFloat(prePremium).toFixed(2));
//							}
//						});
//					}
//				}
			}
		},
		insuranceOrgIdOnChange : function() {
//			var applyTermUnit = $('#applyTermUnitForBiz').val();
//			var insuranceOrgId = $('#insuranceOrgId').val();
//			var applyDate = $('#applyDate').val();
//			var applyAmt = utils.num.normal($('#applyAmt').val());
//			var applyTerm = $('#applyTermForBiz').val();
//			var guaranteeMode = $('#guaranteeMode').val();
//			var bizRate = $('#bizRate').val();
//			var validateApplyAmt = /^\d+(\.\d{1,2})?$/.test(applyAmt);
//			var validateApplyTerm1 = /^[1-9]\d*$/.test(applyTerm);
//			var validateApplyTerm2 = ((/^[1-9]\d*$/.test(applyTerm))&&(parseInt(applyTerm)>0&&parseInt(applyTerm)<=998));
//			var validateBizRate = ((/^\d+(\.\d{1,4})?$/.test(bizRate))&&(parseFloat(bizRate)>0&&parseFloat(bizRate)<=100));
//			if(!insuranceOrgId || !applyDate || !applyAmt || !applyTerm || !applyTermUnit || !guaranteeMode || 
//					!bizRate || !validateApplyAmt || !validateApplyTerm1 || !validateApplyTerm2 || !validateBizRate) {
//				$('#prePremium').val('');
//			} else {
//				$.ajax({
//					type : 'POST',
//					url : $$ctx + 'businessapplicationwd/getPrePremium',
//					data : {
//						"insuranceOrgId" : insuranceOrgId,
//						"applyAmt" : applyAmt,
//						"bizRate" : bizRate,
//						"applyDate" : applyDate,
//						"applyTerm" : applyTerm,
//						"applyTermUnit" : applyTermUnit,
//						"guaranteeMode" : guaranteeMode,
//						"irTypeCd" : $('#irTypeCd').val()
//					},
//					success : function(prePremium) {
//						$('#prePremium').val(parseFloat(prePremium).toFixed(2));
//					}
//				});
//			}
		},
		floatRateOnChange : function() {
			var applyTerm = $('#applyTermForBiz').val();
			var applyTermUnit = $('#applyTermUnitSelect').val();
			var irNegoSymbCd = $('#irNegoSymbCd').val();
			
			var validateApplyTerm1 = /^[1-9]\d*$/.test(applyTerm);
			var validateApplyTerm2 = ((/^[1-9]\d*$/.test(applyTerm))&&(parseInt(applyTerm)>0&&parseInt(applyTerm)<=998));
			var validateIrNegoSymbCd = /^\d+(\.\d{1,4})?$/.test(irNegoSymbCd);
			if(!applyTerm || !applyTermUnit || !irNegoSymbCd || !validateApplyTerm1 || 
					!validateApplyTerm2 || !validateIrNegoSymbCd) {
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
		},
		ifInsureOnChange : function() {
			if($('#ifInsure').val() == '1') {
				$('#insuranceOrgId').attr('required', true);
				$('#prePremium').attr('required', true);
				$('#insureDetailDiv').slideDown();
			} else {
				$('#insuranceOrgId').val('');
				$('#prePremium').val('');
				$('#insuranceOrgId').removeAttr('required');
				$('#prePremium').removeAttr('required');
				$('#insureDetailDiv').slideUp();
			}
		},
		ifUniteCustomer: function() {
			if($('select[name="isUniteCustomer"]').val() == '2') {
				$('select[name="unId"]').attr('disabled',true);
				$('select[name="unId"]').val('');
			}else {
				$('select[name="unId"]').attr('disabled',false);
			}
		},
		ToggleTree : function() {
			$("#controlZTree").toggle(300, function() {
				if (($("#controlZTree").attr("style")) == "") {
                    $("#showTree_")[0].innerHTML = "<i class='ace-icon fa fa-eye'></i>";
                } else if (($("#controlZTree").attr("style")) == "display: none;") {
                    $("#showTree_")[0].innerHTML = "<i class='ace-icon fa fa-eye'></i>";
                } else {
                    $("#showTree_")[0].innerHTML = "<i class='ace-icon fa fa-eye'></i>";
                }
			});
		},
		initCountRateForm : function() {
			$('#countRateForm').validate({
				messages : rm.messages,
				submitHandler : function(form) {
					$.ajax({
						type : 'post',
						url : $$ctx + 'bizapply/loanCal',
						data : $(form).serialize(),
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
														 "<td>"+ list[i].currentInterest +"</td>"+"<td>"+list[i].endCurrentPricipal +"</td>"+
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
								var msg = "<strong>因未知错误，计算失败！</strong>";
								if(result.msg)
									msg = result.msg;
								utils.alert.err(msg);
							}
						}
					});
				}
			});
		},
		judgeRepaymentBiz : function() {
			var repayment = $('#repaymentBiz').val();
			if(repayment == '3') {
				$('#dkss_repaymentPlanTable_div').slideDown();
				$('#resetCountRateForm').slideUp();
				$('#addUserDefindPlan').slideDown();
				$('#repaymentDateBiz').removeClass('required');
				$('#repaymentNumberBiz').removeClass('required');
				$("#tbld").empty();
				$("#tbfoot").empty();
			} else {
				$('#dkss_repaymentPlanTable_div').slideUp();
				$('#resetCountRateForm').slideDown();
				$('#addUserDefindPlan').slideUp();
				$('#repaymentDateBiz').addClass('required');
				$('#repaymentNumberBiz').addClass('required');
			}
		},
		dkss : function() {
			var applyAmt = utils.num.normal($('#applyAmt').val());
			$('#countRateForm').resetForm();
			$('#loanAmountBiz').val(applyAmt);
			$('#applyTermBiz').val($('#applyTermForBiz').val());
			$('#countRateForm select[name=applyTermUnit]').val($('#basicInfoForm select[name=applyTermUnit]').val());
			$('#repaymentBiz').val($('#repayingMode').val());
			$('#countRateForm input[name=rate]').val($('#basicInfoForm input[name=bizRate]').val());
			$('#repaymentNumberBiz').val($('#replyPeriodNum').val());
			$('#loanStartDateBiz').val($('#applyDate').val());
			$("#tbld").empty();
			$("#tbfoot").empty();
			this.judgeRepaymentBiz();
			$('#dkssModal').modal('show');
		},
		resetCountRateForm : function() {
			var applyAmt = utils.num.normal($('#applyAmt').val());
			$('#countRateForm').resetForm();
			$('#loanAmountBiz').val(applyAmt);
			$('#applyTermBiz').val($('#applyTermForBiz').val());
			$('#repaymentBiz').val($('#repayingMode').val());
			$('#rateBiz').val($('#bizRate').val());
			$('#repaymentNumberBiz').val($('#replyPeriodNum').val());
			$("#tbld").empty();
			$("#tbfoot").empty();
		},
		prePremiumValidate : function() {
			$(".prePremiumValidate").bind("change", function(e) {
				var data = {};
				//担保方式只选择了信用
				if($('#guaranteeMode').val()==('4') 
						|| $('#guaranteeMode').val()==('4,') 
						|| $('#guaranteeMode').val()==(',4')) {
					return;
				}
				//判断是否有保险一项选择否
				if($("#ifInsure").val() == "2") return;
				$.each($(".prePremiumValidate"), function(i, item) {
					//验证输入框是否符合校验规则
					if (!$(item).valid()) return;
					data[item.name] = item.value;
					if(item.name=="applyAmt"){
						var applyAmtValue = item.value;
						if(applyAmtValue.indexOf(",")>0) {
							data[item.name] = applyAmtValue.replace(/,/g,"");
						}
					}
				});
				//alert(JSON.stringify(data));
				$.ajax({
					type : 'post',
					url : $$ctx + "businessapplicationwd/getPrePremium",
					data : data,
					success : function(result) {
						$("#prePremium").val(result.toFixed(2));
					}
				});
			});
		}
	});
	module.exports = view;
});