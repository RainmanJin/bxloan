/**
 * 经营信息表单的校验规则
 * 
 * author: lijing
 * lastModified: lijing 2014-08-07 16:30:24
 */

define(function(require, exports, module) {
    var rmjdcw = {
        rules: {
            busiContent: {
                //经营内容
                stringCheck: true,
                required: true,
                maxlength: 20
            },
            busiAge: {
                //经营年限
            	isIntThreeFloatOneBit: true,
                required: true,
                maxlength: 5
            },
            merchantName: {
                //商户名
                stringCheck: true,
                isPeasantMerchant: "#employmentTypeField",
                maxlength: 20
            },
            address: {
                //地址
                stringCheck: true,
                required: true,
                maxlength: 20
            },
            yearSellAmt: {
                //年销售额
                isNotNegative: true,
                required: true,
                maxlength: 15
            },
            yearNetprofitAmt: {
                //年净利润(单位：万元)
                isNotNegative: true,
                required: true,
                maxlength: 15
            },
            assetsTotalAmt: {
                //资产总额
                isNotNegative: true,
                required: true,
                maxlength: 15
            },
            liabilitiesTotalAmt: {
                //负债总额(万元)
                isNotNegative: true,
                required: true,
                maxlength: 15
            },
            otherProject: {
                //最高额度贷款行
                stringCheck: true,
                maxlength: 15
            },
            otherAssetsTotalAmt: {
                isNotNegative: true,
                maxlength: 12
            },
            otherYearIncome: {
                isNotNegative: true,
                maxlength: 12
            }

        },
        messages: {
        }
    };
    module.exports = rmjdcw;
});