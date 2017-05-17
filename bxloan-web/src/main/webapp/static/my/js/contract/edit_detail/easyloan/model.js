var _success = "<i class='ace-icon fa fa-check bigger-300' style='color: green;'>&nbsp</i>";
var _failure = "<i class='ace-icon fa fa-times bigger-300' style='color: red;'>&nbsp</i>";
var _tip = "<i class='ace-icon fa fa-exclamation bigger-300' style='color: orange;'>&nbsp</i>";


define(function(require, exports, module) {
	var model = Backbone.Model.extend({
		initialize: function() {
			// do nothing
		},
		checkContractReady:function(data,callback){
			$.post($$ctx + "contractMng/checkContractReady", data).success(function(r) {
				callback(r);
			}).error(function(){bootbox.alert(_failure + "checkContractReady失败请稍后重试");});
		},
		findNextTaskAssigners:function(taskId,workflowId,taskStageCode,callback){
			$.post($$ctx + "approval/findNextTaskAssigners/"+taskId+"/"+workflowId+"/"+taskStageCode).success(function(r) {
				callback(r);
			}).error(function(){bootbox.alert(_failure + "findNextTaskAssigners失败请稍后重试");});
		},
		dismissAppr:function(data,callback){
			$.post($$ctx + "approval/dismissAppr",data).success(function(r) {
				callback(r);
			}).error(function(){bootbox.alert("撤销失败请稍后重试");});
		},
		openCustomerWindow:function(mark,data,callback){
			switch (mark+"") {
			case "210":
			case "1":{
				var r =  ("corpcustomer/showDetail/" + data.customerId);
				callback(r);
				break;}
			case "2":
			default:{
				$.post($$ctx + "singleCustomer/modToForm",data).success(function(r) {
					callback(r);
				}).error(function(){bootbox.alert(_failure + "查询失败请稍后重试");})
				break;}	
			}
			
		},
		openBusinessWindow:function(path,data,callback){
			$.post($$ctx + path,data).success(function(r) {
				callback(r);
			}).error(function(){bootbox.alert(_failure + "查询失败请稍后重试");})
		}
	});
	module.exports = model;
});