define(function(require, exports, module) {
	var rm = {
		rules : {
			expenseCollectionType : {
				required : true
			},
			expenseName : {
				required : true
			},
			expenseRate : {
				isPercentNumberTwo : true
			},
			expenseAmt : {
				required : true,
				isPositiveNumberTwo : true
			}
		},
		messages : {}
	};
	module.exports = rm;
});