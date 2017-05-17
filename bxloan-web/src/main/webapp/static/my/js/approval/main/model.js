define(function(require, exports, module) {
	var _success = "<i class='ace-icon fa fa-check bigger-300' style='color: green;'>&nbsp</i>";
	var _failure = "<i class='ace-icon fa fa-times bigger-300' style='color: red;'>&nbsp</i>";
	var _tip = "<i class='ace-icon fa fa-exclamation bigger-300' style='color: orange;'>&nbsp</i>";

	var model = Backbone.Model.extend({
		initialize: function() {
		},
		submitForm:function(data,callback){
			
			$.post($$ctx + "approval/submitApproval",data).success(function(r) {
				callback(r);
			}).error(function(){bootbox.alert(_failure+"提交失败请稍后重试");});
		},
		findNextTaskAssigners:function(taskId,workflowId,taskStageCode,callback){
			$.post($$ctx + "approval/findNextTaskAssigners/"+taskId+"/"+workflowId+"/"+taskStageCode).success(function(r) {
				callback(r);
			}).error(function(){bootbox.alert(_failure+"findNextTaskAssigners失败请稍后重试");});
		},
		quitAppr:function(data,callback){
			$.post($$ctx + "approval/quitApproval",data).success(function(r) {
				callback(r);
			}).error(function(){bootbox.alert(_failure+"退回失败请稍后重试");});
		},
		openCustomerWindow:function(mark,data,callback){
			switch (mark) {
			case "1":{
				var r =  ($$ctx + "corpcustomer/showDetail/" + data.customerId);
				callback(r);
				break;}
			case "2":{
				$.post($$ctx + "singleCustomer/modToForm",data).success(function(r) {
					callback(r);
				}).error(function(){bootbox.alert(_failure+"查询失败请稍后重试");})
				break;}	
			default:
				break;
			}
			
		},
		openBusinessWindow:function(mark,data,callback){
			$.post($$ctx +"bizapply/checkApplicationWindow",data).success(function(r) {
				callback(r);
			}).error(function(){_failure+bootbox.alert("查询失败请稍后重试");});
		},
		fetchCommentDetail : function(data,callback){
			$.post($$ctx + "wfmonitor/detail",data).success(function(r) {
				callback(r);
			}).error(function(){_failure+bootbox.alert("查看详细失败.请稍后再试");});
		},
		checkDocIsUpload:function(data,callback){
			$.post( $$ctx+"approval/checkUploadFile",data,function(obj){
				callback(obj);
			}).error(function(){bootbox.alert("服务出错，请稍后重试");});
		}
	
	});
	module.exports = model;
});