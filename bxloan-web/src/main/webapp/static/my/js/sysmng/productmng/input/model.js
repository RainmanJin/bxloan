define(function(require, exports, module) {
	var model = Backbone.Model.extend({
		initialize: function() {
		},
		save:function(data,callback){
			$.post($$ctx+'productMng/save',data, function(obj) {
				callback(obj);
			});
		}
	});
	module.exports = model;
});