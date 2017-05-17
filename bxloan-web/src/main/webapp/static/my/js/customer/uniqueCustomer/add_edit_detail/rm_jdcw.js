/**
 * 简单财务信息表单的校验规则
 * 
 * author: lijing
 * lastModified: lijing 2014-08-07 16:30:24
 */

define(function(require, exports, module) {
    var rmjdcw = {
        rules: {
        	localHouseCondition: {
        		required: true
        	},
            localHouseNum: {
                //房产
                hasHouseOrCar: "#localHouseCondition",
                maxlength:3
            },
            carNum: {
                //车产
                hasHouseOrCar: "#carCondition",
                maxlength:3
            },
            houseesPrice: {
                //房产价值
            	isNotNegative: true,
                maxlength:10,
                required: true
            },
            houseesLoan: {
                //房贷
            	isNotNegative: true,
                maxlength:10,
                required: true
            },
            loanNum: {
                //借款人贷款笔数
                isIntNotNegative: true,
                required: true,
                maxlength:2
            },
            loanBalance: {
                //余额(单位：万元)
            	isNotNegative: true,
                maxlength:15,
                required: true
            },
            repaymentMonth: {
                //月还款
            	isNotNegative: true,
                maxlength:10,
                required: true
            },
            highestLoanQuota: {
                //最高额度(万元)
            	isNotNegative: true,
                maxlength:15,
                required: true
            },
            highestLoanBank: {
                //最高额度贷款行
                stringCheck: true,
                maxlength:100,
                required: true
            },
            monthIncome: {
                //借款人税后月收入(元)
                //整数或小数
            	isNotNegative: true,
                required: true,
                maxlength:15
            }
        },
        messages: {
            loanNum: {
                //借款人贷款笔数
                required: "此项必填"
            },
            monthIncome: {
                //借款人税后月收入(元)
                required: "此项必填"
            }

        }
    };
    module.exports = rmjdcw;
});