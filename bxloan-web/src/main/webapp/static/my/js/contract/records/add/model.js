define(function(require, exports, module) {
	var model = Backbone.Model.extend({
		initialize: function() {
			// do nothing
		},
		queryMessages:function(data,callback){
			$.post($$ctx + "contractList/queryMessages",data).success(function(r) {
				callback(r);
			}).error(function(){bootbox.alert("queryMessages失败请稍后重试");});
		},
		getCustomerAccount:function(data,callback){
			$.post($$ctx + "contractList/getCustomerAccounts",data).success(function(r) {
				callback(r);
			}).error(function(){bootbox.alert("getCustomerAccounts失败请稍后重试");});
		},
		getAccountNum:function(data,callback){
			$.post($$ctx + "contractList/getAccountNum",data).success(function(r) {
				callback(r);
			}).error(function(){bootbox.alert("getAccountNum失败请稍后重试");});
		},
		subimitAndPrint:function(data,callback){
		}
	});
	module.exports = model;
});