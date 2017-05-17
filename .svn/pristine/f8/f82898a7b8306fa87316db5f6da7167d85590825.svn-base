define(function(require, exports, module) {
	var model = Backbone.Model.extend({
		initialize : function() {
		},
		submitForm : function($form, callback) {
			$form.ajaxSubmit(function(r) {
				callback(r);
			});
		},
		searchCustomer : function(id, callback) {
			$.post($$ctx +"bizapply/getCustomer/" + id, function(obj) {
				callback(obj);
			});
		},
		submitWorkForm : function($form, callback) {
			$form.ajaxSubmit(function(r) {
				callback(r);
			});
		},
		submitBizExpenseRate : function($form, callback) {
			$form.ajaxSubmit(function(r) {
				callback(r);
			});
		},
		searchBizExpenseRate : function(bizExpenseRateId, callback) {
			$.post($$ctx +"bizapply/searchBizExpenseRate/" + bizExpenseRateId, function(r) {
				callback(r);
			});
		},
		deleteBizExpenseRate : function(bizExpenseRateId, callback) {
			$.post($$ctx +"bizapply/deleteBizExpenseRate/" + bizExpenseRateId, function(r) {
				callback(r);
			});
		}
	});
	module.exports = model;
});