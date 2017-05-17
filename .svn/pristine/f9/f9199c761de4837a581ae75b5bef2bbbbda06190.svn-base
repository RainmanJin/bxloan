define(function(require, exports, module) {
	var model = Backbone.Model.extend({
		initialize: function() {
		},
		getSelectOrg:function(callback){
			$.post($$ctx+"productMng/getSelectOrg",function(obj) {
				callback(obj);
			});
		}
	});
	module.exports = model;
});