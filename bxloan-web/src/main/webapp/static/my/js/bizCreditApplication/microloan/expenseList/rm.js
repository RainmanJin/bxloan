define(function(require, exports, module) {
	var rm = {
		rules : {
			expenseCollectionType : {  //费用收取方式
				required : true
			},
			expenseName : {   //费用名称
				required : true
			},
			expenseRate : {  //实际费率
				isPercentNumberTwo : true
			},
			expenseAmt : {  //实际收费
				required : true,
				isPositiveNumberTwo : true  //正整数或两位小数
			}
		},
		messages : {}
	};
	module.exports = rm;
});