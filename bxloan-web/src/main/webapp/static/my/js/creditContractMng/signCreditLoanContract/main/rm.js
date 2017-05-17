define(function(require, exports, module) {
    var rm = {
        rules: {
            bankName: {
                //账户
                required: true
            },
            loanNum: {
            	//贷款账号
            	required: true,
            	isBankNum: true
            },
            loanDateStyle: {
                //约定方式
                required: true
            },
            arrangeRepayDay: {
                //约定还款日期
                isArrangeRepayDay: "#loanDateStyle"
            },
            finalRateValue: {
            },
            rateAdjustmentReason: {
                //isRateAdjust: "#checkFinalRateField"
            }
        },
        messages: {}
    };
    module.exports = rm;
});