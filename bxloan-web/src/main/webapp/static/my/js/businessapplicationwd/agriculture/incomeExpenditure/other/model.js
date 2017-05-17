define(function(require, exports, module) {
	var model = Backbone.Model.extend({
		initialize: function() {
		},
		updateFnOtherIncomeById: function(id, callback) {
			$.post($$ctx +"addFnOtherIncome/get/" + id, function(obj) {
				callback(obj);
			});
		},
		
		delOtherFnIncomeById:function(id,callback){
			$.post($$ctx +"addFnOtherIncome/delete/" + id, function(obj){
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