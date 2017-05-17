define(function(require, exports, module) {
	var model = require("../main/model");
	var rm = require("../main/rm");
	var utils = require("utils");
	var view = Backbone.View.extend({
		el : "body",
		events : {
			"click #submitApply": "submitApply",
			"click #downloadBizApply": "downloadBizApply",
			"click #cancelApply": "cancelApply",
			'click button[role="bndFinancialStatements"]':"downloadFinancialStatementsForBnd"
		},
		initialize : function() {
			this.model = new model();
			this.render();
		},
		render : function() {
		},
		submitApply : function() {
			var opinion = $('#opinion').val().trim();
			var nextTaskReceiver = $('#nextTaskReceiver').val();
			var guaranteeMode = $('#guaranteeMode').val();
			if(!opinion) {
				utils.alert.warn("<strong>请填写意见。</strong>");
				$('#opinion').val('');
			} 
			else if(!nextTaskReceiver) {
				utils.alert.warn("<strong>请选择下一环节接收人。</strong>");
			}
			else {
				$("#submitApply").attr({
					"disabled" : "disabled"
				});
				$.ajax({
					type : 'POST',
					url : $$ctx + 'businessapplicationwd/checkBusinessInfoBeforeSendProcess',
					data : {
						'projectId' : $('#projectId').val(),
						'guaranteeMode' : guaranteeMode
					},
					success : function(result) {
						if(result.success) {
							$.ajax({
								type : 'post',
								url : $$ctx + 'businessapplicationwd/submitApply',
								data : {
									'projectNo' : $('#projectNo').val(),
									'workflowId' : $('#workflowId').val(),
									'taskId' : $('#taskId').val(),
									'opinion' : $('#opinion').val(),
									'actionCode' : $('#submitApply').val(),
									'productCd' : $('#productCd').val(),
									'nextTaskReceiver' : $('#nextTaskReceiver').val()
								},
								success : function(result) {
    	    						if (result.success) {
										utils.alert.suc("<strong>提交成功！</strong>");
										setTimeout(function() {
			    							window.location.href = $$ctx + 'dashboard';
			    						}, 1000);
									} else {
										utils.alert.err("<strong>" + result.msg + "</strong>");
										$('#submitApply').removeAttr('disabled');
										if(result.msg == '强制退回。') {
			    							setTimeout(function() {
			    								window.location.href=$$ctx+"dashboard";
			    							}, 1000);
			    						}
									}
								}
							});
						} else {
							utils.alert.warn("<strong>" + result.msg + "</strong>");
							$("#submitApply").removeAttr('disabled');
						}
					}
				});
			}
		},
		downloadBizApply: function() {
           	location.href = $$ctx+'businessapplicationwd/downloadFile';
		},
		cancelApply: function() {
			utils.button.confirm(null, function(result) {
				if (result) {
					$.ajax({
						type: "POST",
						url: $$ctx + "businessapplicationwd/cancelApply",
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
		downloadFinancialStatementsForBnd:function(){//下载邦农贷财务报表
			var viewSelf=this;
			var projId=$("#projectId").val();
			viewSelf.model.downloadExfi(projId,function(r_data){
				if(r_data&&r_data.success){
					location.href = $$ctx+'expectCashFlowInfo/downloadEcfi?projId='+projId;
				}else{
					utils.alert.err("<strong>"+r_data.msg+"</strong>");
				}
			});
		}
	});
	module.exports = view;
});