/**
 * 合同信息表单的校验规则
 * 
 * author: lijing
 * lastModified: lijing 2014-08-07 16:30:24
 */
define(function(require, exports, module) {
    var rm_co = {
        rules: {
        	guarantyName: {
                //抵质押物名称
                required: true,
                stringCheck: true
            },
            guaranteeType: {
            	//抵质押物类型
            	required: true
            },
            setGuaranteeAmt: {
                //已设定担保额（元）
            	isPositiveNumber: true
            },
            actualGuaranteeRate: {
            	//实际担保率
            	isPositiveNumber: true
            },
            actualCreditAmount: {
            	//本次实际担保债权金额（元）
            	isPositiveNumber: true,
            	 required: true
            }
            
        },
        messages: {}
    };
    module.exports = rm_co;
});