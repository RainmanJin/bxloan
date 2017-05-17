define(function(require, exports, module) {
	var rm = require("../main/rm");
	var utils = require("utils");
	var view = Backbone.View.extend({
		el : "body",
		events : {
			"click #submitApply": "submitApply",
			"click #downloadBizApply": "downloadBizApply",
			"click #cancelApply": "cancelApply"
		},
		initialize : function() {
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
			} else if(!nextTaskReceiver) {
				utils.alert.warn("<strong>请选择下一环节接收人。</strong>");
			} else {
				$("#submitApply").attr({
					"disabled" : "disabled"
				});
				//禁用提交按钮
				utils.button.ban("#submitApply");
				$.ajax({
					type : 'POST',
					url : $$ctx + 'underCreditContractMng/checkBusinessInfoBeforeSendProcess',
					data : {
						'projectId' : $('#underProjectId').val(),
						'guaranteeMode' : guaranteeMode
					},
					success : function(result) {
						if(result.success) {
							$.ajax({
								type : 'post',
								url : $$ctx + 'underCreditContractMng/submitApply',
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
    	    							utils.alert.suc("<strong>提交成功！</strong>", function(){
											utils.button.reset("#submitApply");
										});
										setTimeout(function() {
			    							window.location.href = $$ctx + 'dashboard';
			    						}, 1000);
									} else {
										utils.alert.err("<strong>" + result.msg + "</strong>", function(){
											utils.button.reset("#submitApply");
										});
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
							utils.alert.warn("<strong>" + result.msg + "</strong>", function(){
								utils.button.reset("#submitApply");
							});
							$("#submitApply").removeAttr('disabled');
						}
					}
				});
			}
		},
		downloadBizApply: function() {
           	location.href = $$ctx+'underCreditContractMng/downloadFile';
		},
		cancelApply: function() {
			utils.button.confirm(null, function(result) {
				//禁用拒绝按钮
				utils.button.ban("#cancelApply");
				if (result) {
					$.ajax({
						type: "POST",
						url: $$ctx + "underCreditContractMng/cancelApply",
						data: {
							"projectId" : $('#projectId').val(),
							"workflowId" : $('#workflowId').val(),
							"taskId" : $('#taskId').val()
						},
						success: function(r) {
							if (r.success) {
								utils.alert.suc("<strong>贷款申请撤销成功！</strong>", function(){
									utils.button.reset("#cancelApply");//启用按钮
								});
								setTimeout(function() {
									window.location.href = $$ctx+'dashboard';
								},1000);
							} else {
								utils.alert.err("<strong>撤销失败！</strong>", function(){
									utils.button.reset("#cancelApply");//启用按钮
								});
							}
						}
					});
				}
			});
		}
	});
	module.exports = view;
});