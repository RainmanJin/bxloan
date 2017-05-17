define(function(require, exports, module) {
	var rm = {
		rules:{
			repayDateStr: {
				required : true
			},
			addressTypeCd: {
				required : true
			},
			feeType: {
				required : true
			},
			website: {
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