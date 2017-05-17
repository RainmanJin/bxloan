define(function(require, exports, module) {
	var model = Backbone.Model.extend({
		initialize: function() {
		},
	updateFnProduceAreaInfoById: function(id, callback) {
		$.post($$ctx +"addFnProduceAreaInfo/get/" + id, function(obj) {
			callback(obj);
		});
	},
	
	delFnProduceAreaInfoById:function(id,callback){
		$.post($$ctx +"addFnProduceAreaInfo/delete/" + id, function(obj){
			callback(obj);
		})
	},
	
	submitForm: function($form, callback) {
		$form.ajaxSubmit(function(r) {//异步提交
			callback(r);
		});
	} 	

	});
	module.exports = model;
});