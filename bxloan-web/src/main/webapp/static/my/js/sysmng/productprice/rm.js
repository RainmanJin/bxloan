define(function(require, exports, module) {
	var rm = {
		rules: {
			loanTerm: {
				isPercentNumberFour: true
			},
			rate: {
				isPercentNumberFour: true
			},
			overdueRate: {
				isPercentNumberFour: true
			},
			perculNegoRate: {
				isPercentNumberFour: true
			},
			earlyRepayment: {
				isPercentNumberFour: true
			},
			repaymentCucle: {
				isIntNotNegative: true
			}
		}
	};
	module.exports = rm;
});