define(function(require, exports, module) {
	var model = Backbone.Model.extend({
		initialize: function() {
			// do nothing
		},
		findCustomer:function(data,callback){//查询客户
			$.post($$ctx + "customerQuery/findCustomer", data, function(obj) {
				callback(obj);
			});
		},
		findParty:function(data,callback){//查询party表
			$.post($$ctx + "customerQuery/findParty", data, function(obj) {
				callback(obj);
			});
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
				}).error(function(){bootbox.alert("查询失败请稍后重试");});
				break;}	
			default:
				break;
			}
		},
		openContractWindow:function(data,callback){
			$.post($$ctx + "contractQuery/detail",data).success(function(r) {
				callback(r);
			}).error(function(){bootbox.alert("查询失败请稍后重试");});
		},
		fetchDetail : function(data,callback){
			$.post($$ctx + "wfmonitor/detail",data).success(function(r) {
				callback(r);
			}).error(function(){bootbox.alert("查看详细失败.请稍后再试");});
		},
		cancelWorkflow : function(data,callbacl){
			$.post($$ctx + "wfmonitor/cancel",data).success(function(r) {
				callbacl(r);
			}).error(function(){bootbox.alert("撤销流程失败.请稍后再试");});
		}
//		openCustomerWindow:function(mark,data,callback){
//			
//			var r =  ($$ctx + "corpcustomer/showDetail/" + data.customerId);
//			callback(r);
//		}
	});
	module.exports = model;
});