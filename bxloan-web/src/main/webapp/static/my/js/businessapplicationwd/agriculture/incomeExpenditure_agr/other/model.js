define(function(require, exports, module) {
	var model = Backbone.Model.extend({
		initialize: function() {
		},
		updateOtherIncomeById: function(id, callback) {
			$.post($$ctx +"addOtherIncome/get/" + id, function(obj) {
				callback(obj);
			});
		},
		
		delOtherIncomeById:function(id,callback){
			$.post($$ctx +"addOtherIncome/delete/" + id, function(obj){
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