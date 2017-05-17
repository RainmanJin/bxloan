define(function(require, exports, module) {
	var model = Backbone.Model.extend({
		initialize: function() {
			// do nothing
		},
		getUserById: function(id, callback) {
			$.post("userMng/get/" + id, function(obj) {
				callback(obj);
			});
		},
		delUserById: function(id, callback) {
			$.post("userMng/delete/" + id, function(r) {
				callback(r);
			});
		},
		submitForm: function($form, callback) {
			$form.ajaxSubmit(function(r) {
				callback(r);
			});
		},
		getIndustryType: function(callback) {
			$.post("userMng/getAllIndustry", function(r) {
				callback(r);
			});
		}
	});
	module.exports = model;
});