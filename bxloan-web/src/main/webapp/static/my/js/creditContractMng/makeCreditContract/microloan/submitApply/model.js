define(function(require, exports, module) {
	var model = Backbone.Model.extend({
		initialize: function() {
		},
		/** 检查合同是否具备提交下一环节条件 */
		checkContractReady:function(data,callback){
			$.post($$ctx + "creditContractMng/checkContractReady", data).success(function(r) {
				callback(r);
			}).error(function(){
				bootbox.alert("checkContractReady失败请稍后重试");
			});
		},
		/** 获取下一环节操作人 */
		getNextAssigner:function(data,callback){
			$.post($$ctx + "wdapproval/getNextTaskAssigners/"+data).success(function(r) {
				callback(r);
			}).error(function(){
				bootbox.alert("getNextAssigner失败请稍后重试");
			});
		},
		/** 拒绝 */
		dismissAppr:function(data,callback){
			$.post($$ctx + "creditContractApproval/dropBack",data).success(function(r) {
				callback(r);
			}).error(function(){
				bootbox.alert("撤销失败请稍后重试");
			});
		}
	});
	module.exports = model;
});