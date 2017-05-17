define(function(require, exports, module) {
	var model = Backbone.Model.extend({
		initialize: function() {
			// do nothing
		},
		abolishContract:function(data,callback){
			$.post($$ctx + "contractMng/abolishContract",data).success(function(r) {
				callback(r);
			}).error(function(){bootbox.alert("abolishContract失败请稍后重试");});
		},
		updateContract:function(data,callback){
			$.post($$ctx + "contractMng/updateContract",data).success(function(r) {
				callback(r);
			}).error(function(){bootbox.alert("updateContract失败请稍后重试");});
		},
		viewDetail: function(data,callback){
			$.post($$ctx + "contractMng/viewDetail?projectId="+data).success(function(r) {
				callback(r);
			}).error(function(){bootbox.alert("viewDetail失败请稍后重试");});
		},
		checkWorkflowType: function(data,callback){
			$.post($$ctx + "contractMng/checkWorkflowType", data).success(function(r) {
				callback(r);
			}).error(function(){bootbox.alert("checkWorkflowType失败请稍后重试");});
		},
	});
	module.exports = model;
});