define(function(require, exports, module) {
	var rm = {
		rules : {
			applyAmt : {
				isApplyAmt : true,    //判断是否符合产品表配置的申请金额
				isApplyAmtAvailable : ["#basicInfoForm input[name ='projectId']"]  //判断申请金额是否可用，是否符合产品配置表中的批量金额要求
			},
			applyTerm : {
				isLoanTerm : true    //判断是否符合产品表配置的贷款期限
			},
			bizRate : {
				isRate : true,   //判断是否符合产品表配置的利率
				remote : {
					type: "POST",
					url:  $$ctx +"bizapply/validateRate",  //检查年利率是否大于央行基准利率的N倍
					data: {
						projectId : $("#basicInfoForm input[name='projectId']").val(),
						applyTerm : function() {
							return $("#basicInfoForm input[name='applyTerm']").val();
						},
						applyTermUnit : function() {
							return $("#basicInfoForm select[name='applyTermUnit']").val();
						},
						approveRateValueStr : function() {
							return $("#basicInfoForm input[name='bizRate']").val();
						}
					}
				}
			}
		},
		messages : {
			applyAmt : {
				isApplyAmt : "",
				isApplyAmtAvailable : ""
			},
			applyTerm : {
				isLoanTerm : ""
			},
			bizRate : {
				isRate : "",
				remote : "利率值必须小于或等于央行基准利率的定值倍数，请咨询管理员"
			}
		}
	};
	module.exports = rm;
});

/**千分位的正整数或两位小数*/
jQuery.validator.addMethod("isPositiveNumberTwoOfThousands", function(value, element) {       
	value = parseFloat(value.split(',').join(""));
	return this.optional(element) || /^[1-9]\d*$/.test(value) || /^\d+(\.\d{1,2})?$/.test(value);       
}, "正整数或两位小数");

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