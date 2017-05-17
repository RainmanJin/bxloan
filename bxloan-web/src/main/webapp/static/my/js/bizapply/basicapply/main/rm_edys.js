define(function(require, exports, module) {
	var rmedys = {
		rules: {
			monthIncome: {
				isNumber: true,
				required: true
			},
			highestLoanQuota: {
				isNumber: true,
				required: true
			},
			repaymentThisTime: {
				isNumber: true,
				required: true
			},
			otherRepayment: {
				isNumber: true,
				required: true
			},
			repaymentMonthSubmit: {
				isNumber: true,
				required: true
			},
			liabilitiesMonth: {
				isNumber: true,
				required: true
			},
			calcLoanAmt: {
				isNumber: true,
				required: true
			},
			calcLoanTerm: {
				isNumber: true,
				required: true
			}
		},
		messages: {
			monthIncome: {
				isNumber: "不合法",
				required: "必填"
			},
			highestLoanQuota: {
				isNumber: "不合法",
				required: "必填"
			},
			repaymentThisTime: {
				isNumber: "不合法",
				required: "必填"
			},
			otherRepayment: {
				isNumber: "不合法",
				required: "必填"
			},
			repaymentMonthSubmit: {
				isNumber: "不合法",
				required: "必填"
			},
			liabilitiesMonth: {
				isNumber: "不合法",
				required: "必填"
			},
			calcLoanAmt: {
				isNumber: "不合法",
				required: "必填"
			},
			calcLoanTerm: {
				isNumber: "不合法",
				required: "必填"
			}
		}
	};
	module.exports = rmedys;
});