define(function(require, exports, module) {
	var model = Backbone.Model.extend({
		initialize: function() {
		},
		checkUploadFile:function(projectId,docTypeKey,callback){
			var data=[];
			data.push("projectId="+projectId);
			data.push("docTypeKey="+docTypeKey);
			$.post( $$ctx+"approval/checkUploadFile",data.join("&"), function(obj) {
				callback(obj);
			}).error(function(){bootbox.alert("服务出错，请稍后重试");});
		},
		submitAppr:function(data,callback){
			$.post( $$ctx+"approval/submitApproval",data, function(obj) {
				callback(obj);
			}).error(function(){bootbox.alert("提交出错，请稍后重试");});
		},
		findNextTaskAssigners:function(taskId,workflowId,taskStageCode,callback){
			$.post($$ctx + "approval/findNextTaskAssigners/"+taskId + "/" + workflowId + "/" + taskStageCode).success(function(r) {
				callback(r);
			}).error(function(){bootbox.alert("findNextTaskAssigners失败请稍后重试");});
		},
		quitAppr:function(data,callback){
			$.post($$ctx + "approval/quitApproval",data).success(function(r) {
				callback(r);
			}).error(function(){bootbox.alert("退回失败请稍后重试");});
		},
		dismissAppr:function(data,callback){
			$.post($$ctx + "approval/dismissAppr",data).success(function(r) {
				callback(r);
			}).error(function(){bootbox.alert("撤销失败请稍后重试");});
		},
		openCustomerWindow:function(mark,data,callback){
			$.post($$ctx + "singleCustomer/modToForm",data).success(function(r) {
				callback(r);
			}).error(function(){bootbox.alert("查询失败请稍后重试");})
		},
		openBusinessWindow:function(mark,data,callback){
			switch (mark) {
			case "300":{
				var r =  ($$ctx +"bizapply/checkApplicationWindow" + data.customerId);
				callback(r);
				break;}
			case "283":{
				$.post($$ctx +"bizapply/checkApplicationWindow",data).success(function(r) {
					callback(r);
				}).error(function(){bootbox.alert("查询失败请稍后重试");});
				break;}	
			default:
				break;
			}
		},
		fetchCommentDetail : function(data,callback){
			$.post($$ctx + "wfmonitor/detail",data).success(function(r) {
				callback(r);
			}).error(function(){bootbox.alert("查看详细失败.请稍后再试");});
		}
	});
	module.exports = model;
});