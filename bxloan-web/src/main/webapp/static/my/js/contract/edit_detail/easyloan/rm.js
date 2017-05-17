/**
 * 合同信息表单的校验规则
 * 
 * author: lijing
 * lastModified: lijing 2014-08-07 16:30:24
 */
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
            }
            /*,
           finalRateValue: {
                //最终年利率
                required: true,
                isPositiveNumberTwo: true,
                maxlength: 5
            },
            rateAdjustmentReason: {
                isRateAdjust: "#checkFinalRateField"
            }*/
        },
        messages: {}
    };
    module.exports = rm;
});