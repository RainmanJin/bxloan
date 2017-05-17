define(function(require, exports, module) {
	var controllerUrl = "dashboard";
	var model = Backbone.Model.extend({
		
		initialize: function() {
		},
		doReadMsg : function(data,callback){
			$.post($$ctx + controllerUrl + "/doReadMsg",data).success(function(r) {
				callback(r);
			}).error(function(){bootbox.alert("删除失败.请稍后再试");});
		},
		fetchDetail : function(data,callback){
			$.post($$ctx + "wfmonitor/detail",data).success(function(r) {
				callback(r);
			}).error(function(){bootbox.alert("查看详细失败.请稍后再试");});
		}
	});
	module.exports = model;
});