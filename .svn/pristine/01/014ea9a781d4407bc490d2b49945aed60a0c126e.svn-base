define(function(require, exports, module) {
	var model = Backbone.Model.extend({
		initialize : function() {
		},
		save : function(data, callback) {
			$.post($$ctx + 'collateral/save', data, function(obj) {
				callback(obj);
			});
		}
	});
	module.exports = model;
});