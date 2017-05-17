/**千分位的正整数或两位小数*/
jQuery.validator.addMethod("isPositiveNumberTwoOfThousands", function(value, element) {       
	value = parseFloat(value.split(',').join(""));
	return this.optional(element) || /^[1-9]\d*$/.test(value) || /^\d+(\.\d{1,2})?$/.test(value);       
}, "正整数或两位小数");

define(function(require, exports, module) {
	var model = require("./model");
	var rm = require("./rm");
	var utils = require("utils");
	
	var view = Backbone.View.extend({
		el: "body",
		events: {
		  "click #btn-add": "add",
		  "click #add-simple-submit": "saveBizExpenseRate",
		  "click #update-simple-submit": "updateBizExpenseRate",
		  "click #submitApply": "submitApply",
		  "change #expenseCollectionType": "changeExpenseCollectionType",
		  "change #expenseCollectionTypeupdate": "changeExpenseCollectionTypeupdate",
		  "change #expenseRateupdate": "expenseRateupdateOnChange",
		  "change #expenseRate": "expenseRateOnChange",
		  "click #btn-showTree": "ToggleTree",
		  "change #repayingMode": "changeRepayingMode",
		  "click #downloadBizApply": "downloadBizApply",
		  "click #cancelApply": "cancelApply"
		},
		initialize: function() { /** 初始化 */
			this.model = new model();
			this.render();
		},
		render: function() { /** 页面渲染 */
			this.formatNumber();
			this.initBasicInfoForm();
			this.initBizExpenseRateList();
			this.initApplyDate();
			this.editBtnLive();
			this.deleteBtnLive();
			this.initTree();
			this.judgeType();
			this.judgeRepayingMode();
		},
		formatNumber: function() {
			var applyAmt = $('#applyAmt').val();
			var rateValue = $('#rateValue').val();
			var overFloatRate = $('#overFloatRate').val();
			var divertFloatRate = $('#divertFloatRate').val();
			if(applyAmt)
				utils.num.thousands('#applyAmt');
			if(rateValue)
				$('#rateValue').val((parseFloat(rateValue * 100)).toFixed(2));
			if(overFloatRate)
//				$('#overFloatRate').val((parseFloat(overFloatRate) * 100));
				$('#overFloatRate').val((parseFloat(overFloatRate)));
			if(divertFloatRate)
//				$('#divertFloatRate').val((parseFloat(divertFloatRate) * 100));
				$('#divertFloatRate').val((parseFloat(divertFloatRate)));
			
			/** 去掉在数字文本框内输入的002,010等无效0 */
			$(document).on("blur", "#applyAmt, #rateValue, #overFloatRate, #divertFloatRate", function(e) {
				var reg = /^-?[0-9]*(\.\d*)?$|^-?d^(\.\d*)?$/;
				var value = $(this).val();
				var id = $(this).attr('id');
				if($(this).attr('id') == 'applyAmt' && $("#applyAmt").valid()) {
					utils.num.thousands(this);
				} else if (value && value.match(reg) != null) {
					$(this).val(parseFloat(value));
				}
			});
			
			/** 获取婚姻状况，决定“配偶是否作为共同还款人”是否可选 */
			if(!$('#marriageCd').val() || $('#marriageCd').val() != '20') {// 非个人客户或非已婚客户
				$('#mateBorrower').val('2');
				$('#mateBorrower').attr('disabled', true);
			}
		},
		judgeType: function() {
			if($('#judgeType').val() == 'check') {
				$.each($("div").find(".page-content input[name!=query_documentName][name!=query_contentType], .page-content select, #btn-showTree"), function() {
					$(this).attr('disabled',true);
				});
			}
			if($('#bizRateId').val()) {// 说明是已经保存的基本信息
				$('#dkss').removeAttr('disabled');
				$('#btn-add').removeAttr('disabled');
			}
			// 选中总部协同业务
			var isheadcol = $('#isheadcol').val();
			if(isheadcol){
				$('#basicInfoForm').find(':radio[name="isheadcol"][value="' + isheadcol + '"]').prop("checked",true);
			}
			/*var isheadcol = $('#isheadcol').val();
			var check = $('#basicInfoForm').find(":radio[name='isheadcol'][value='" + isheadcol + "']");
			if(check[0])
				check[0].checked = true;*/
			$(document).on("click", "button[name='backToDashboard']", function(e) {
				var type = $('#type').val();
				if(type == 'query') {
					window.location.href = $$ctx+'querybusiness';
				} else {
					window.location.href = $$ctx+'dashboard';
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
				//$('#repaymentPlanDiv').hide();
			} else if(repayingMode == '3') {
				$('#replyPeriodNum').val('');
				$('#replyPeriodNumShowHide').hide();
				
				//$('#startDate').val('');
				//$('#monthDate').val('');
				//$('#repayDateForCount').val('');
				/*setTimeout(function() {
					if($('#repaymentPlanTable tbody tr').html().indexOf('没有符合条件的记录') <= 0)
						$("#batchInitRepayPlan").attr({
							"disabled" : "disabled"
						});
				}, 100);*/
				//$('#repaymentPlanDiv').show();
			} else {
				$('#replyPeriodNum').val(replyPeriodNumInput.val());
				$('#replyPeriodNumShowHide').show();
				//$('#repaymentPlanDiv').hide();
			}
		},
		initApplyDate:function(){
			//将datepicker控件放在最顶层
			var dateOnTop = function(){$(".datepicker").css("z-index","99999");};
			var defaultDateConf = {
				autoclose : true,
				todayHighlight : true,
				clearBtn : true,
				endDate : 'd'
			};
			
			var initDateP = function(selector,changeCallBack,config){
				var _dateConf = config || defaultDateConf;
				$(selector).datepicker(_dateConf)
				.on("changeDate",changeCallBack)
				.on("click",dateOnTop);
			};
			
			initDateP("#applyDate");
		},
		editBtnLive: function() { // 修改按钮事件
        	var viewSelf = this;
			$(document).on("click", "button[role='edit']", function(e) { // 动态绑定所有修改按钮的click事件
				var $this = $(this);
				var id = $this.data("id");
				// 查询需要修改的费用信息
				var viweSelf = this;
				viewSelf.model.searchBizExpenseRate(id,function(r){
					if(r.success){
						$("#updateBizExpenseRate").resetForm();
						var bizExpenseRateId = r.data.bizExpenseRateId;
						var expenseName = r.data.expenseName;
						var expenseCollectionType = r.data.expenseCollectionType;
						var expenseRate = r.data.expenseRate;
						var expenseAmt = r.data.expenseAmt;
						$('#bizExpenseRateId').val(bizExpenseRateId);
						$("#expenseCollectionTypeupdate").val(expenseCollectionType);
						$("#expenseNameupdate").val(expenseName);
						$('#expenseRateupdate').val(expenseRate);
						$('#expenseAmtupdate').val(expenseAmt);
						$('#expenseAmtupdate').addClass('required');
						if(expenseCollectionType != '2'){
							$('#hideRateupdate').show();
							$('#expenseRateupdate').addClass('required'); 
							$('#expenseRateupdate').addClass('isPercentNumberTwo');
							$('#expenseAmtupdate').attr('readonly',true);
						}else {
							$('#hideRateupdate').hide();
							$('#expenseRateupdate').removeClass('required'); 
							$('#expenseRateupdate').removeClass('isPercentNumberTwo');
							$('#expenseAmtupdate').attr('readonly',false);
						}
						$("#update-modal-form div.modal-header h4").html("<i class='ace-icon fa fa-edit'></i> 修改费用信息");
						$("#update-modal-form").modal("show");
					}
				});
                return false;
			});
        },
        deleteBtnLive: function() { // 查看按钮事件
        	var viewSelf = this;
			$(document).on("click", "button[role='delete']", function(e) { // 动态绑定所有删除按钮的click事件
				var $this = $(this);
				var id = $this.data("id");
				// 删除费用信息
				var viweSelf = this;
				utils.button.confirm(null, function(result) {
					if (result) {
						viewSelf.model.deleteBizExpenseRate(id, function(r) {
							if(r.success){
								utils.alert.suc("<strong>费用信息删除成功！</strong>");
								viewSelf.oTable.fnDraw(); // 重新加载表格中的数据
							}
						});
					}
				});
                return false;
			});
        },
		initBizExpenseRateList:function(){
			var viewSelf = this;
			var id = null;
			if($('#judgeType').val() != 'check') {
				utils.dd.initDataDict(["FeeUsedType", "FeeType"], function(dataDict) {
					var oTable = $("#tblBER").dataTable({
				    	"sAjaxSource": $$ctx + "bizapply/searchBizExpenseRateList",
				    	"bFilter": false,
				    	"bLengthChange" : false,
				    	"aoColumns": [
							{mData: "bizExpenseRateId"},
							{mData: "expenseCollectionType"}, 
							{mData: "expenseName"},
							{mData: "standardExpenseRate"},
							{mData: "expenseRate"},
							{mData: "standaredAmt"},
							{mData: "expenseAmt"},
							{mData: "sysCreateDateStr"},
							{mData: "sysCreateDate"},
				    	],
				    	"aoColumnDefs": [
				    	        { "bVisible": false, "aTargets": [0] },
								{
								    "aTargets" : [0],
								    "mRender" : function(data, type, full){
								    	id = data;
								    	return data;
								    }
								},
				    	      {
	                              "aTargets" : [1],
	                              "mRender" : function(data, type, full){
	                              	return dataDict.FeeUsedType[data];
	                              }
	                          },
	                          {
	                              "aTargets" : [2],
	                              "mRender" : function(data, type, full){
	                              	return dataDict.FeeType[data];
	                              }
	                          },
	                          {
	                              "aTargets" : [8],
	                              "mRender" : function(data, type, full){
	                            	  var buttons = "";
	                            	  if($('#judgeType').val() == 'check') {
	                            		  
	                            	  }else{
	                            		  var buttons = "<div class='btn-group'style='width:100px;'><button type='button' title='修改' role='edit' data-id='" + id + "'  class='btn btn-xs btn-info'><i class='ace-icon fa fa-edit'></i></button> "
	                        				+ "<button type='button' title='删除' role='delete' data-id='" + id+ "'  class='btn btn-xs btn-danger'><i class='ace-icon fa fa-trash-o'></i></button></div> ";
	                            	  }
	                              	  return buttons;
	                              }
	                          }
				    	],
				        "fnServerParams" : function(aoData) {
				        	aoData.push({
								"name" : "projectNo",
								"value" : $('#projectNo').val()
							});
						}
					});
					viewSelf.oTable = oTable;
				});
			} else {
				utils.dd.initDataDict(["FeeUsedType", "FeeType"], function(dataDict) {
					var oTable = $("#tblBER").dataTable({
				    	"sAjaxSource": $$ctx + "bizapply/searchBizExpenseRateList",
				    	"bFilter": false,
				    	"bLengthChange" : false,
				    	"aoColumns": [
							{mData: "bizExpenseRateId"},
							{mData: "expenseCollectionType"}, 
							{mData: "expenseName"},
							{mData: "standardExpenseRate"},
							{mData: "expenseRate"},
							{mData: "standaredAmt"},
							{mData: "expenseAmt"},
							{mData: "sysCreateDateStr"}
				    	],
				    	"aoColumnDefs": [
				    	        { "bVisible": false, "aTargets": [0] },
								{
								    "aTargets" : [0],
								    "mRender" : function(data, type, full){
								    	id = data;
								    	return data;
								    }
								},
				    	      {
	                              "aTargets" : [1],
	                              "mRender" : function(data, type, full){
	                              	return dataDict.FeeUsedType[data];
	                              }
	                          },
	                          {
	                              "aTargets" : [2],
	                              "mRender" : function(data, type, full){
	                              	return dataDict.FeeType[data];
	                              }
	                          }
				    	],
				        "fnServerParams" : function(aoData) {
				        	aoData.push({
								"name" : "projectNo",
								"value" : $('#projectNo').val()
							});
						}
					});
					viewSelf.oTable = oTable;
				});
			}
		},
		cancelApply: function() {
			utils.button.confirm(null, function(result) {
				if (result) {
					$.ajax({
						type: "POST",
						url: $$ctx + "bizapply/cancelApply",
						data: {
							"projectId" : $('#projectId').val(),
							"workflowId" : $('#workflowId').val(),
							"taskId" : $('#taskId').val()
						},
						success: function(r) {
							if (r.success) {
								utils.alert.suc("<strong>贷款申请撤销成功！</strong>");
								setTimeout(function() {
									window.location.href = $$ctx+'dashboard';
								},1000);
							} else {
								utils.alert.err("<strong>贷款申请撤销失败！</strong>");
							}
						}
					});
				}
			});
		},
		downloadBizApply: function() {
			var filepath = 'doc_templet/“邦微贷”贷款申请表.docx';
			var filename = '“邦微贷”贷款申请表.docx';
           	location.href = $$ctx+'bizapply/downloadFile?filepath='+filepath+'&filename='+filename;
		},
		/*changeRepayingMode: function() {
			var repayingMode = $('#repayingMode').val();
			if(repayingMode == '2' || repayingMode == '3') {
				$('#hideReplyPeriodNum').hide();
				$('#replyPeriodNum').val('hide');
			}else{
				$('#hideReplyPeriodNum').show();
				$('#replyPeriodNum').val('');
			}
		},*/
		changeRepayingMode : function() {
			var repayingMode = $('#repayingMode').val();
			if (repayingMode == '2') {
				$('#replyPeriodNum').val('');
				$('#replyPeriodNumShowHide').hide();
			} else if(repayingMode == '3') {
				$('#replyPeriodNum').val('');
				$('#replyPeriodNumShowHide').hide();
			} else {
				$('#replyPeriodNum').val($('#replyPeriodNumInput').val());
				$('#replyPeriodNumShowHide').show();
			}
		},
		initTree: function() {
			/** 初始化树 */
            var viewSelf = this;
            $.fn.zTree.init($("#tree"), {
                async: {
                    enable: true,
                    url: $$ctx + "bizapply/getAllIndustry"
                },
                data: {
                    simpleData: {
                        enable: true,
                        idKey: "industryTypeCd",
                        pIdKey: "parentIndustryTypeCd"
                    },
                    key: {
                        name: "industryTypeName"
                    }
                },
                check: {
                    enable: true,
                    chkStyle: "radio",
                    radioType: "all"
                },
                callback: {
                    onClick: function(event, treeId, treeNode) {
                    	if(treeNode!=null&&treeNode.children!=null&&treeNode.children.length!=null&&treeNode.children.length>0){
                    		$("#industryTypeCdField").val("");
                            $("#industryCdMask").val("");
                            $("#industryCd").val("");
                    		return false;
                    	}else{
                    		var industryTypeCd = treeNode.industryTypeCd;
                        	var treeObj = $.fn.zTree.getZTreeObj(treeId);
                        	var node = treeObj.getNodeByParam("industryTypeCd", industryTypeCd, null);
                        	treeObj.checkNode(node, true, true);
                        	var parentNode = node.getParentNode();
                            treeObj.expandNode(parentNode, true, false);
                    		$("#industryTypeCdField").val(treeNode.industryTypeCd);
                            $("#industryCdMask").val(treeNode.industryTypeName);
                            $("#industryCd").val(treeNode.industryTypeCd);
                            $("#controlZTree").toggle(300,
                            function() {
                                if (($("#controlZTree").attr("style")) == "<i class='ace-icon fa fa-eye'></i>") {
                                    $("#btn-showTree")[0].innerHTML = "";
                                } else if (($("#controlZTree").attr("style")) == "display: none;") {
                                    $("#btn-showTree")[0].innerHTML = "<i class='ace-icon fa fa-eye'></i>";
                                } else {
                                    $("#btn-showTree")[0].innerHTML = "<i class='ace-icon fa fa-eye'></i>";
                                }
                            });
                    	}
                    },
                    onCheck: function(event, treeId, treeNode) {
                    	$("#industryTypeCdField").val(treeNode.industryTypeCd);
                        $("#industryCdMask").val(treeNode.industryTypeName);
                        $("#industryCd").val(treeNode.industryTypeCd);
                    },
                    beforeCheck: function(treeId, treeNode) {
                    	//点击radio时赋值
                    	$("#industryTypeCdField").val(treeNode.industryTypeCd);
                        $("#industryCd").val(treeNode.industryTypeCd);
                        return ! treeNode.isParent;
                    },
                    onAsyncSuccess: function(event, treeId, treeNode, msg) {
                        var treeObj = $.fn.zTree.getZTreeObj(treeId);
                        var industryTypeCd = $("#industryTypeCdField").val();
                        if (industryTypeCd !== "") {
                            var node = treeObj.getNodeByParam("industryTypeCd", industryTypeCd, null);
                            treeObj.checkNode(node, true, true);
                            var parentNode = node.getParentNode();
                            treeObj.expandNode(parentNode, true, false);
                            $("#industryCdMask").val(node.industryTypeName);
                            $("#industryCd").val(node.industryTypeCd);
                        }
                    }
                }
            });
        },
        ToggleTree: function() {
    		/** 显示隐藏行业的树 */
            $("#controlZTree").toggle(300,
            function() {
                if (($("#controlZTree").attr("style")) == "<i class='ace-icon fa fa-eye'></i>") {
                    $("#btn-showTree")[0].innerHTML = "";
                } else if (($("#controlZTree").attr("style")) == "display: none;") {
                    $("#btn-showTree")[0].innerHTML = "<i class='ace-icon fa fa-eye'></i>";
                } else {
                    $("#btn-showTree")[0].innerHTML = "<i class='ace-icon fa fa-eye'></i>";
                }
            });
        },
		expenseRateupdateOnChange: function() {
			var reg = /^-?[0-9]*(\.\d*)?$|^-?d^(\.\d*)?$/;
			var expenseRateupdate = $('#expenseRateupdate').val();
			var applyAmt = utils.num.normal($('#applyAmt').val());
			if($("div[for=expenseRateupdate]").val() != '' && expenseRateupdate.match(reg) != null){
				var expenseAmtupdate=applyAmt*expenseRateupdate/100;
				$('#expenseAmtupdate').val(expenseAmtupdate.toFixed(2));
			}
			else
				$('#expenseAmtupdate').val('');
		},
		expenseRateOnChange: function() {
			var reg = /^-?[0-9]*(\.\d*)?$|^-?d^(\.\d*)?$/;
			var expenseRate = $('#expenseRate').val();
			var applyAmt = utils.num.normal($('#applyAmt').val());
			if($("div[for=expenseRate]").val() != '' && expenseRate.match(reg) != null){
				var expenseAmt=applyAmt*expenseRate/100;
				$('#expenseAmt').val(expenseAmt.toFixed(2));
			}
			else
				$('#expenseAmt').val('');
		},
		add : function() { // 新增按钮事件
			$('#hideRate').show();
			$('#expenseAmt').attr('readonly',true);
			$("#addBizExpenseRate").resetForm();
			$("#add-modal-form div.modal-header h4").html("<i class='ace-icon fa fa-plus'></i> 新增费用信息");
			$("#add-modal-form").modal("show");
		},
		updateBizExpenseRate: function() {
			var viewSelf = this;
			var type = $('#expenseCollectionTypeupdate').val();
			if(type=='2'){
				$('#expenseRateupdate').removeAttr("required");
			}
			$("#updateBizExpenseRate").validate({
				submitHandler: function(form) {
					var formSelf = $(form);
					viewSelf.model.submitBizExpenseRate(formSelf, function(r) {
						if (r.success) {
							$("#update-modal-form").modal("hide");
							formSelf.resetForm();
							viewSelf.oTable.fnDraw(); // 重新加载表格中的数据
						} else {
							utils.alert.err("<strong>" + r.msg + "</strong>");
						}
					});
				}
			});
		},
		saveBizExpenseRate:function(){
			var viewSelf = this;
			var type = $('#expenseCollectionType').val();
			if(type=='2'){
				$('#expenseRate').remove("required");
			}
			$("#addBizExpenseRate").validate({
				submitHandler: function(form) {
					var formSelf = $(form);
					viewSelf.model.submitBizExpenseRate(formSelf, function(r) {
						if (r.success) {
							$("#add-modal-form").modal("hide");
							formSelf.resetForm();
							viewSelf.oTable.fnDraw(); // 重新加载表格中的数据
						} else {
							utils.alert.err("<strong>" + r.msg + "</strong>");
						}
					});
				}
			});
		},
		// 保存基本项目信息
		initBasicInfoForm: function() {
			$('#basicInfoForm').validate({
				rules : rm.rules,
				messages : rm.messages,
				submitHandler : function(form) {
					var $amt = $('#applyAmt');
					var applyAmt = utils.num.normal($amt.val());
					$amt.val(applyAmt);
					$("#btn-proapp").attr({
						"disabled" : "disabled"
					});
					$.ajax({
						type : 'post',
						url : $$ctx +'bizapply/saveBusiness',
						data : $('#basicInfoForm').serialize(),
						success : function(result) {
							$("#btn-proapp").removeAttr("disabled");
							utils.num.thousands('#applyAmt');
							if (result.success) {
								utils.alert.suc("<strong>" + result.msg + "</strong>");
								$('#basicInfoForm input[name=bizRateId]').val(result.data.bizRateId);
								$('#dkss').removeAttr('disabled');
								$('#btn-add').removeAttr('disabled');
								$('#tblBER').dataTable().fnDraw();
								$('#commonBorrowerTable').dataTable().fnDraw();
							} else {
								utils.alert.err("<strong>" + result.msg + "</strong>");
							}
						}
					});
				}
			});
		},
		changeExpenseCollectionType:function(){
			var type = $('#expenseCollectionType').val();
			if(type=='2'){
				$('#expenseAmt').val('');
				$('#expenseAmt').removeAttr('readonly');
				$('#expenseAmt').addClass('required');
				$('#expenseAmt').addClass('isPositiveNumberTwo');
				$('#expenseRate').removeClass('required');
				$('#expenseRate').removeClass('isPercentNumberTwo');
				$('#hideRate').hide();
			}else{
				$('#expenseAmt').val('');
				$('#expenseRate').val('');
				$('#expenseAmt').attr('readonly',true);
				$('#expenseAmt').removeClass('required');
				$('#expenseAmt').removeClass('isPositiveNumberTwo');
				$('#expenseRate').addClass('required');
				$('#expenseRate').addClass('isPercentNumberTwo');
				$('#hideRate').show();
			}
		},
		changeExpenseCollectionTypeupdate:function(){
			var type = $('#expenseCollectionTypeupdate').val();
			if(type=='2'){
				$('#expenseAmtupdate').val('');
				$('#expenseAmtupdate').removeAttr('readonly');
				$('#expenseAmtupdate').addClass('required');
				$('#expenseAmtupdate').addClass('isPercentNumberTwo');
				$('#expenseRateupdate').removeClass('required');
				$('#expenseRateupdate').removeClass('isPercentNumberTwo');
				$('#hideRateupdate').hide();
			}else{
				$('#expenseAmtupdate').val('');
				$('#expenseRateupdate').val('');
				$('#expenseAmtupdate').attr('readonly',true);
				$('#expenseAmtupdate').removeClass('required');
				$('#expenseAmtupdate').removeClass('isPercentNumberTwo');
				$('#expenseRateupdate').addClass('required');
				$('#expenseRateupdate').addClass('isPercentNumberTwo');
				$('#hideRateupdate').show();
			}
		},
		submitApply:function(){
			var opinion = $('#opinion').val().trim();
			if(!opinion) {
				utils.alert.warn("<strong>请填写意见。</strong>");
				$('#opinion').val('');
			} 
			else {
				$("#submitApply").attr({"disabled":"disabled"});
				$("#cancelApply").attr({"disabled":"disabled"});
				$.ajax({
					type: "POST",
	                url: $$ctx +"bizapply/checkBusinessInfoBeforeSendProcess",
	                data: {
	                	"projectId" : $('#projectId').val()
	                },
	                success: function(r) {
						if (r.success) {
	                		$.ajax({
	                            type: "POST",
	                            url: $$ctx + "bizapply/submitApply",
	                            data: {
	                            	"projectNo" : $('#projectNo').val(),
	                            	"workflowId" : $('#workflowId').val(),
	                            	"taskId" : $('#taskId').val(),
	                            	"opinion" : $('#opinion').val(),
	                            	"actionCode" : $('#submitApply').val(),
	                            	"productCd" : $('#productCd').val()
	                            },
	                            success: function(r) {
	                            	if (r.success) {
	            						utils.alert.suc("<strong>提交成功！</strong>");
	            						setTimeout(function() {
	            							window.location.href = $$ctx + 'dashboard';
	            						}, 1000);
	            					} else {
	            						$("#submitApply").removeAttr("disabled");
	            						$("#cancelApply").removeAttr("disabled");
	            						utils.alert.err("<strong>" + r.msg + "</strong>");
	            						if(r.msg == '强制退回。') {
	            							setTimeout(function() {
	            								window.location.href=$$ctx+"dashboard";
	            							}, 1000);
	            						}
	            					}
	                            }
	                        });
						} else {
							$("#submitApply").removeAttr('disabled');
							$("#cancelApply").removeAttr('disabled');
							utils.alert.warn("<strong>" + r.msg + "</strong>");
						}
	                }
				});
			}
		}
	});
	module.exports = view;
});