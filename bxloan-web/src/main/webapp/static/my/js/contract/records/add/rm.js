define(function(require, exports, module) {
    var rm = {
        rules: {
        	bankName: {
                //账户
                required: true
            },
            loanAmt: {
                //发放金额
                required: true,
                isNotNegative: true,
                isAffordLoanAmt:"#contractAvailableAmt"
            },
            loanRegistTime: {
                //放款日期
            	required:true
            },
            sumAmt: {
                //手续费和佣金
                required: true,
                isNotNegative: true
            },
            sourceType: {
                //费用来源
            	needSourceType:"#sumAmt"
            },
            remark: {
            	required:true
            }
        },
        messages: {
        }
    };
    module.exports = rm;
});