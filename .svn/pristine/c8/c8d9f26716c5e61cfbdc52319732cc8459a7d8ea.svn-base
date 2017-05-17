define(function(require, exports, module) {
	var utils = require("utils");
	var view = Backbone.View.extend({
		el : "body",
		events : {
			"click #submitApply": "submitApply",  //提交
			"click #cancelApply": "cancelApply"   //拒绝
		},
		initialize : function() {
			this.render();
		},
		render : function() {
		},
		submitApply : function() {  //提交
			var opinion = $('#opinion').val().trim();
			var nextTaskReceiver = $('#nextTaskReceiver').val();
			var guaranteeMode = $('#guaranteeMode').val();
			if(!opinion) {
				utils.alert.warn("<strong>请填写意见。</strong>");
				$('#opinion').val('');
			} else if(!nextTaskReceiver) {
				utils.alert.warn("<strong>请选择下一环节接收人。</strong>");
			} else {
				/*$("#submitApply").attr({
					"disabled" : "disabled"
				});*/
				//禁用提交按钮
				utils.button.ban("#submitApply");
				$.ajax({
					type : 'POST',
					url : $$ctx + 'bizCreditApplication/checkBusinessInfoBeforeSendProcess',  //检查业务信息
					data : {
						'projectId' : $('#projectId').val(),
						'guaranteeMode' : guaranteeMode
					},
					success : function(result) {
						if(result.success) {
							$.ajax({
								type : 'post',
								url : $$ctx + 'bizCreditApplication/submitApply',   //发送下一环节
								data : {
									'wfCode': $("#wfCode").val(),
									'workflowId' : $('#workflowId').val(),
									'taskId' : $('#taskId').val(),
									'taskStageCode': $("#taskStageCode").val(),
									'opinion' : $('#opinion').val(),
									'actionCode' : $('#submitApply').val(),
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
										/*$('#submitApply').removeAttr('disabled');*/
										if(result.msg == '强制退回。') {
			    							setTimeout(function() {
			    								window.location.href=$$ctx+"dashboard";
			    							}, 1000);
			    						}
									}
								}
							});
						} else {
							/*$("#submitApply").removeAttr('disabled');*/
							utils.alert.warn("<strong>" + result.msg + "</strong>", function(){
								utils.button.reset("#submitApply");
							});
						}
					}
				});
			}
		},
		cancelApply: function() {
			utils.button.confirm(null, function(result) {
				//禁用拒绝按钮
				utils.button.ban("#cancelApply");
				if (result) {
					$.ajax({
						type: "POST",
						url: $$ctx + "bizCreditApplication/cancelApply",
						data: {
							'wfCode': $("#wfCode").val(),
							"workflowId" : $('#workflowId').val(),
							'taskId' : $('#taskId').val(),
							'taskStageCode': $("#taskStageCode").val()
						},
						success: function(result) {
							if (result.success) {
								utils.alert.suc("<strong>撤销成功！</strong>", function(){
									utils.button.reset("#cancelApply");
								});
								setTimeout(function() {
									window.location.href = $$ctx+'dashboard';
								},1000);
							} else {
								utils.alert.err("<strong>撤销失败！</strong>", function(){
									utils.button.reset("#cancelApply");
								});
							}
						}
					});
				} else{
					utils.button.reset("#cancelApply");
				}
			});
		},
	});
	module.exports = view;
});