define(function(require, exports, module) {
	var model = Backbone.Model.extend({
		initialize: function() {
			// do nothing
		},
		getNormalRepaymentInfo:function(data,callback){
			$.post($$ctx+"afterLoan/normalRepayment/getNormalRepaymentInfo",data, function(obj) {
				callback(obj);
			});
		}
	});
	module.exports = model;
});