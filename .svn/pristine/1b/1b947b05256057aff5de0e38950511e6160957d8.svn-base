define(function(require, exports, module) {
	
	var model = Backbone.Model.extend({
		initialize: function() {
		},
		refreshSelectorTable: function(data, callback){
			$.post($$ctx + "approval/findDocumentCustDocTypes", data, function(r) {
				callback(r);
			});
		},
		findCustDocTypes: function(data,callback){
			$.post($$ctx + "approval/findUploadCustDocTypes", data).success(function(r) {
				callback(r);
			}).error(function(){bootbox.alert("查看上传文档类型出错sss");});
		},
		beforeUpload:function(data, callback){
			$.post( $$ctx+"approval/beforeUpload",data,function(obj){
				callback(obj);
			}).error(function(){bootbox.alert("服务出错，请稍后重试");});
		}
	});
	module.exports = model;
});