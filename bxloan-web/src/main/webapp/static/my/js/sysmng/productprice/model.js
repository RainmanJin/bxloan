define(function(require, exports, module) {
	var model = Backbone.Model.extend({
		initialize : function() {
			// do nothing
		},
		delProductPriceById : function(id, callback) {
			$.post($$ctx + "sysmng/delete/" + id, function(r) {
				callback(r);
			});
		},
		submitForm : function($form, callback) {
			$form.ajaxSubmit(function(r) {
				callback(r);
			});
		}
	});
	module.exports = model;
});