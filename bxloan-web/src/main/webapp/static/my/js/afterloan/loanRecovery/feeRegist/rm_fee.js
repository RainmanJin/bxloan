define(function(require, exports, module) {
	var rm = {
		rules:{
			repayDateStr: {
				required : true
			},
			fundsSource: {
				required : true
			},
			feeType: {
				required : true
			},
			expenseAmt: {
				required : true,
				isNotNegative: true,
				maxlength:15
			}
		},
		messages:{
			
		}
			
	}
	module.exports = rm;
})