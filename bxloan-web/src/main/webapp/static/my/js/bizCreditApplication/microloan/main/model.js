define(function(require, exports, module) {
	var wdBizUrl = "businessapplicationwd"
	var model = Backbone.Model.extend({
		initialize: function() {
		},
		findCustDocTypes: function(data,callback){
			$.post($$ctx + wdBizUrl+"/findUploadCustDocTypes", data).success(function(r) {
				callback(r);
			}).error(function(){bootbox.alert("查看上传文档类型出错");});
		}
	});
	module.exports = model;
});