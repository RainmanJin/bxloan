/** 文本域最大限度 */
jQuery.validator.addMethod("isTextarea", function(value, element) {
	var sStr,iCount,i,strTemp ;
	iCount = 0 ;
	sStr = value.split("");
	for (i = 0 ; i < sStr.length ; i ++) {
		strTemp = escape(sStr[i]);
		if (strTemp.indexOf("%u",0) == -1) {
			iCount = iCount + 1 ;
		} else {// 表示是汉字
			iCount = iCount + 3 ;
		}
	}
	return !(iCount > 500);
}, "超过最大限度");

/** 贷款期限最大输入值为998，最小为1，整数 */
jQuery.validator.addMethod("isApplyTerm", function(value, element) {
	return this.optional(element) ||((/^[1-9]\d*$/.test(value) || /^[1-9]\d*$/.test(value))&&(parseInt(value)>0&&parseInt(value)<=998));
}, "请输入1-998的整数");

/**千分位的正整数或两位小数*/
jQuery.validator.addMethod("isPositiveNumberTwoOfThousands", function(value, element) {       
	value = parseFloat(value.split(',').join(""));
	return this.optional(element) || /^[1-9]\d*$/.test(value) || /^\d+(\.\d{1,2})?$/.test(value);       
}, "正整数或两位小数");

define(function(require, exports, module) {
	var rm = {
		rules : {
			certificateNum : {
				isIDcard : "#addCertificateType",
				isAICard : "#addCertificateType",
				isPassport : "#addCertificateType"
			},
			customerName: {
                //姓名
        		required:true,
        		isCorpCustomer: "#customerName",
        		nameStringCheck: true,
        		maxlength:50
            }
		},
		messages:{
		}
	};
	module.exports = rm;
});