define(function(require, exports, module) {
	var model = Backbone.Model.extend({
		initialize: function() {
		},
		updateGainById: function(id, callback) {
			$.post($$ctx +"gainDistribution/get/" + id, function(obj) {
				callback(obj);
			});
		},
		
		delGainById:function(id,callback){
			$.post($$ctx +"gainDistribution/delete/" + id, function(obj){
				callback(obj);
			})
		},
		
		submitForm: function(form, callback) {
			form.ajaxSubmit(function(r) {//异步提交
				callback(r);
			});
		}	

	});
	module.exports = model;
});