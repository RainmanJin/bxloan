define(function(require, exports, module) {
	var model = Backbone.Model.extend({
		initialize: function() {
		},
		save:function(data,callback){
			$.post($$ctx+'collateral/save',data, function(obj) {
				callback(obj);
			});
		},
		findCustDocTypes: function(data,callback){
			$.post($$ctx  + "collateral/findUploadCustDocTypes", data).success(function(r) {
				callback(r);
			}).error(function(){bootbox.alert("查看上传文档类型出错");});
		}
	});
	module.exports = model;
});