define(function(require, exports, module) {
	var model = Backbone.Model.extend({
		initialize: function() {
			// do nothing
		},
		getMenus: function(callback) {
			$.post($$ctx+"dashboard/getMenus", function(obj) {
				callback(obj);
			});
		},
		fetchPersonDetail : function(callBack){
			$.post($$ctx+"userinfo/detail", function(data) {
				callBack(data);
			});
		}
	});
	module.exports = model;
});