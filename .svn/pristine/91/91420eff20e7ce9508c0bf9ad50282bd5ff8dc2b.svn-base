define(function(require, exports, module) {
	var rm = require("./rm");
	var utils = require("utils");
	var view = Backbone.View.extend({
		el : "body",
		events : {
			"change #irTypeCd" : "judgeIrTypeCd",          //根据利率类型改变利率信息中相应内容
			"change #irNegoSymbCd" : "floatRateOnChange",  //利率上浮比例修改触发利率修改
			"change #applyTermUnitSelect" : "floatRateOnChange",  //授信期限单位修改触发利率修改
			"change #applyTerm" : "floatRateOnChange",            //授信期限修改触发利率修改
			"click #showTree_" : "toggleTree"                     //切换投放行业下拉框显示与隐藏状态
		},
		initialize : function() {  //初始化
			this.render();
		},  
		render : function() {    //渲染
			this.initBasicInfoForm();  //初始化业务信息页面
			this.initTree();   //初始化投放行业树
		},
		initBasicInfoForm : function() {
			var applyTermUnit = $('#applyTermUnitFromProduct').val();
			if (applyTermUnit=="" && $('#type').val() != 'check') {  //非查看业务信息和期限单位为空的情况下，期限单位控件可选，否则不可选
				$("#applyTermUnitSelect").attr("disabled",false);
			}
			//格式化金额
            $.each($('#basicInfoForm').find('.num_amt_2fixed'), function() {
            	var val = $.trim($(this).val());
            	if (val) {
            		$(this).val(utils.number.toAmt(val));
            	}
            });
			$('#bizRate').addClass('required');
			$('#bizRate').addClass('isRate');
			$('#basicInfoForm').validate({   //页面信息校验
				rules: rm.rules,
                messages: rm.messages,
				submitHandler : function(form) {
					var guaranteeMode = $('#guaranteeMode').val();
					var $form = $("#basicInfoForm");
					var productType = $form.find('select[name="productType"]').val();
					var applyAmt = $form.find(':input[name="applyAmt"]').val();
					applyAmt = applyAmt.split(',').join("");
					if (productType == g_app.product.agroLoanCode && applyAmt >= 50000 && applyAmt < 100000 && guaranteeMode.split(",").indexOf('3') < 0){
						utils.alert.warn("<strong>担保方式必须包含【保证】这种担保方式</strong>");
						return;
					}
					if (!guaranteeMode || guaranteeMode == '') {
						utils.alert.warn("<strong>请至少选择一种担保方式！</strong>");
					} else {   //保存授信业务信息
					    $.each($('#basicInfoForm').find('.num_amt_2fixed'), function() {
			            	var val = $.trim($(this).val());
			            	if (val) {
			            		$(this).val(utils.num.normal(val));
			            	}
			            });
						var data = $(form).serialize();
						//期限单位原来已有的话，serialize取不到值，所以特殊处理一下，
						if (data.indexOf("applyTermUnit")<0) {
							data += "&applyTermUnit=" + $("#applyTermUnitSelect").val();
						}
						//禁用保存按钮
						utils.button.ban("#saveBasicInfo");
						$.ajax({
							type : 'post',
							url : $$ctx + 'bizCreditApplication/saveBusiness',
							data : data,
							success : function(result) {
								if (result.success) {
									utils.alert.suc("<strong>" + result.msg + "</strong>", function() {
										utils.button.reset("#saveBasicInfo");
										window.location.href = $$ctx + "bizCreditApplication/editApplication/" + $("#wfCode").val() + "/" + $("#workflowId").val() + "/" + $("#taskId").val() + "/" + $("#taskStageCode").val();
									});
								} else {
									utils.alert.err("<strong>" + result.msg + "</strong>", function() {
										utils.button.reset("#saveBasicInfo");
									});
								}
							}
						});
					}
				}
			});
		},
		initTree : function() {   //加载投放行业树
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
		},
		
		judgeIrTypeCd : function() {    //根据利率类型改变利率信息中相应内容
			var irTypeCd = $('#irTypeCd').val();
			if (irTypeCd == '1') {  //固定利率
				$('#adjustPeriod').val('');
				$('#irNegoSymbCd').val('');
				$('#bizRate').addClass('required');
				$('#bizRate').addClass('isRate');
				$('#adjustPeriod').removeClass('required');
				$('#irNegoSymbCd').removeClass('required');
				$('#irNegoSymbCd').removeClass('isPercentNumberFour');
				$('#bizRate').removeAttr("readonly");
				$('#adjustPeriodAndFloatRateShowHide').hide();
			} else {   //浮动利率
				$('#adjustPeriod').addClass('required');
				$('#irNegoSymbCd').addClass('required');
				$('#irNegoSymbCd').addClass('isPercentNumberFour');  //上浮比例校验：输入大于0且小于100的正整数或四位小数
				$('#adjustPeriodAndFloatRateShowHide').show();
				$('#bizRate').attr("readonly", "readonly");
			}
		},
		floatRateOnChange : function() {   //根据授信期限，授信期限单位，利率上浮比例计算得到相应年利率
			var applyTerm = $("#basicInfoForm").find(':input[name="applyTerm"]').val();	
			var applyTermUnit = $('#applyTermUnitSelect').val();
			var irNegoSymbCd = $('#irNegoSymbCd').val();
			var validateApplyTerm1 = /^[1-9]\d*$/.test(applyTerm);
//			var validateApplyTerm2 = ((/^[1-9]\d*$/.test(applyTerm))&&(parseInt(applyTerm)>0&&parseInt(applyTerm)<=998));
			var validateIrNegoSymbCd = /^\d+(\.\d{1,4})?$/.test(irNegoSymbCd);
//			if(!applyTerm || !applyTermUnit || !irNegoSymbCd || !validateApplyTerm1 || 
//					!validateApplyTerm2 || !validateIrNegoSymbCd) {
//			} else { 
			if(validateApplyTerm1 && validateIrNegoSymbCd) {
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
		toggleTree : function() {  //切换投放行业下拉框显示与隐藏状态
			$("#controlZTree").toggle(300, function() {
				if (($("#controlZTree").attr("style")) == "") {
                    $("#showTree_")[0].innerHTML = "<i class='ace-icon fa fa-eye'></i>";
                } else if (($("#controlZTree").attr("style")) == "display: none;") {
                    $("#showTree_")[0].innerHTML = "<i class='ace-icon fa fa-eye'></i>";
                } else {
                    $("#showTree_")[0].innerHTML = "<i class='ace-icon fa fa-eye'></i>";
                }
			});
		}
	});
	module.exports = view;
});