define(function(require, exports, module) {
	var model = Backbone.Model.extend({
		initialize: function() {
		},
		fetchDetail : function(data,callback){
			$.post($$ctx + "wfmonitor/detail",data).success(function(r) {
				callback(r);
			}).error(function(){bootbox.alert("执行【查看详细】操作失败.请稍后再试！");});
		},
		cancelWorkflow : function(data,callback){
			$.post($$ctx + "wfmonitor/cancel",data).success(function(r) {
				callback(r);
			}).error(function(){bootbox.alert("执行【撤销】操作失败.请稍后再试！");});
		}
	});
	module.exports = model;
});