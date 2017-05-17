define(function(require, exports, module) {
	var model=Backbone.Model.extend({
		initialize : function() {
		},
		loadDrCreditFormItem:function(callback){
			$.post( $$ctx+"drCreditInfo/loadDrCreditFormItem").success(function(obj) {
				callback(obj);
			}).error(function(){bootbox.alert("提交出错，请稍后重试");});
		},
		getDrCreditFormTemplate:function(callback){
			$.post( $$ctx+"drCreditInfo/getDrCreditFormTemplate").success(function(obj) {
				callback(obj);
			}).error(function(){bootbox.alert("提交出错，请稍后重试");});
		},
		saveDrCreditInfo:function(data,callback){
			$.post( $$ctx+"drCreditInfo/saveDrCreditInfo",data).success(function(obj) {
				callback(obj);
			}).error(function(){bootbox.alert("提交出错，请稍后重试");});
		},
		findDrCreditInfo:function(projectId,callback){
			$.post( $$ctx+"drCreditInfo/findDrCreditInfo/"+projectId).success(function(obj) {
				callback(obj);
			}).error(function(){bootbox.alert("提交出错，请稍后重试");});
		},
		saveDrCld:function(data,callback){
			$.post( $$ctx+"drCreditInfo/saveDrCld",data, function(obj) {
				callback(obj);
			}).error(function(){bootbox.alert("提交出错，请稍后重试");});
		},
		findDrCld:function(id,callback){
			$.post( $$ctx+"drCreditInfo/findDrCld/"+id).success(function(obj) {
				callback(obj);
			}).error(function(){bootbox.alert("提交出错，请稍后重试");});
		},
		delDrCld:function(id,callback){
			$.post( $$ctx+"drCreditInfo/delDrCld/"+id).success(function(obj) {
				callback(obj);
			}).error(function(){bootbox.alert("提交出错，请稍后重试");});
		}
	});
	module.exports=model;
});