define(function(require, exports, module) {
	var controllerUrl = "cusmanager";
	var model = Backbone.Model.extend({
		initialize: function() {
		},
		getCustomerManagerTeamById:function(data,callback){
			$.post($$ctx + controllerUrl + "/getCustomerManagerTeamById",data).success(function(r) {
				callback(r);
			});
		},
		submitForm: function($form, callback) {
			$form.ajaxSubmit(function(r) {
				callback(r);
			});
		},
		removeMngPrivilege:function(data,callback){
			$.post($$ctx + controllerUrl + "/remove",data).success(function(r) {
				callback(r);
			});
		},
		handOverMngPrivilege:function(data,callback){
			$.post($$ctx + controllerUrl + "/handOverMngPrivilege",data).success(function(r) {
				callback(r);
			});
		},
		sureHandOver:function(data,callback){
			$.post($$ctx + controllerUrl + "/sureHandOver",data).success(function(r) {
				callback(r);
			});
		},
		roleTransfer:function(data,callback){
			$.post($$ctx + controllerUrl + "/roleTransfer",data).success(function(r) {
				callback(r);
			});
		},
		isManageCustomerPrivilege:function(data,callback){
			$.post($$ctx + controllerUrl + "/isManageCustomerPrivilege",data).success(function(r) {
				callback(r);
			});
		}
	});
	module.exports = model;
});