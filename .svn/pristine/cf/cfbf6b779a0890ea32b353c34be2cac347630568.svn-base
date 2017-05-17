define(function(require, exports, module) {
	var model = Backbone.Model.extend({
		initialize: function() {
		},
		loanTrial:function(data,callback){
			$.post( $$ctx+"calcu/loanCalc",data, function(obj) {
				callback(obj);
			}).error(function(){bootbox.alert("计算出错,请稍后重试");});
		}
	
	});
	module.exports = model;
});