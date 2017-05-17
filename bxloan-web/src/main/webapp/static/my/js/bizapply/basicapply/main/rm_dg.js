define(function(require, exports, module) {
	var rmdg = {
		rules: {
			monthIncome: {
				isNumber: true,
				required: true
			},
			repaymentThisTime: {
				isNumber: true,
				required: true
			},
			repaymentMonth: {
				isNumber: true,
				required: true
			},
			applicationQuota: {
				isNumber: true,
				required: true
			}
		},
		messages: {
			monthIncome: {
				isNumber: "不合法",
				required: "必填"
			},
			repaymentThisTime: {
				isNumber: "不合法",
				required: "必填"
			},
			repaymentMonth: {
				isNumber: "不合法",
				required: "必填"
			},
			applicationQuota: {
				isNumber: "不合法",
				required: "必填"
			}
		}
	};
	module.exports = rmdg;
});