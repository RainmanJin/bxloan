define(function(require, exports, module) {
	var model = Backbone.Model.extend({
		initialize: function() {
			// do nothing
		},
		flushes:function(id,callback){
			$.post($$ctx+"accountingFlushes/flushes/" + id, function(obj) {
				callback(obj);
			});
		},
		getView:function(id,callback){
			callback($$ctx+"accountingFlushes/view/"+id);
		},
		getTcAccountView:function(billCd,callback){
			callback($$ctx+"accountingFlushes/accountList?billCd="+billCd);
		},
		getBizView:function(id,callback){
			callback($$ctx+"accountingFlushes/showBiz/"+id);
		}
	});
	module.exports = model;
});