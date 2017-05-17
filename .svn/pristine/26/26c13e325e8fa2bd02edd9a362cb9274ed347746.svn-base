define(function(require, exports, module) {
	var model = Backbone.Model.extend({
		initialize: function() {
		},
		delById:function(id,callback){
			var data=[];
			data.push("id="+id);
			$.post($$ctx+"collateral/delete",data.join("&"),function(obj) {
				callback(obj);
			});
		}
	});
	module.exports = model;
});