define(function(require, exports, module) {
	var model = Backbone.Model.extend({
		initialize: function() {
		},
		getPersons: function(callback) {
			$.post($$ctx + "login/getPersons/", function(r) {
				callback(r);
			});
		},
		setAccAndOrg: function(id,callback) {
			$.post($$ctx + "login/setAccAndOrg/"+id, function(r) {
				callback(r);
			});
		},
		getOrgs: function(id,callback) {
			$.post($$ctx + "login/getOrgs/"+id, function(r) {
				callback(r);
			});
		}
	});
	module.exports = model;
});