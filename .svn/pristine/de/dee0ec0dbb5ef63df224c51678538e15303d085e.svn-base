define(function(require, exports, module) {
	var model = Backbone.Model.extend({
		initialize: function() {
		},
		
		querySuggestion:function(data,callback){
			$.post($$ctx + "approval/getSuggestion",data).success(function(r) {
				callback(r);
			}).error(function(){bootbox.alert("查询建议失败请稍后重试");});
		},
		findDocuments: function(data,callback){
			$.post($$ctx + "approval/findDocuments",data).success(function(r) {
				callback(r);
			}).error(function(){bootbox.alert("查询建议失败请稍后重试");});
		}
		
	
	});
	module.exports = model;
});