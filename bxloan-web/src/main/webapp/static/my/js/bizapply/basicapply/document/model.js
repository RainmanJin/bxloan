define(function(require, exports, module) {
	var model = Backbone.Model.extend({
		initialize : function() {
		},
		refreshSelectorTable: function(data, callback){
				$.post($$ctx + "bizapply/findDocumentCustDocTypes", data, function(r) {
					callback(r);
				});
			},
		findCustDocTypes: function(data,callback){
				$.post($$ctx + "bizapply/findUploadCustDocTypes", data).success(function(r) {
					callback(r);
				}).error(function(){bootbox.alert("查看上传文档类型出错");});
		}

	});
	module.exports = model;
});