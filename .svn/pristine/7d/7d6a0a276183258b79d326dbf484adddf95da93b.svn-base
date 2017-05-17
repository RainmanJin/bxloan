define(function(require, exports, module) {
	var model = Backbone.Model.extend({
		initialize: function() {
		},
		/** 查看客户信息 */
		openCustomerWindow:function(mark, data, callback){
			switch (mark) {
				case "1":{
					var r =  ($$ctx + "corpcustomer/showDetail/" + data.customerId);
					callback(r);
					break;
				}
				case "2":{
					$.post($$ctx + "singleCustomer/modToForm",data).success(function(r) {
						callback($$ctx + r);
					}).error(function(){
						bootbox.alert("查询失败请稍后重试");
					});
					break;
				}	
				default:
					break;
			}
		},
		/** 查看授信业务申请信息 */
		openBusinessWindow:function(data, callback){
			$.post($$ctx + "bizCreditApplication/checkApplicationWindow",data).success(function(r) {
				callback(r);
			}).error(function(){
				bootbox.alert("查询失败请稍后重试");
			});
		},
		/** 查看联系人配偶信息 */
		openSpouseWindow: function(data, callback){//获取配偶信息
			$.post($$ctx + "singleCustomer/findOneFamilyFriend", data).success(function(r) {
				callback(r);
			}).error(function(){
				bootbox.alert("撤销失败请稍后重试");
			});
		}
	});
	module.exports = model;
});