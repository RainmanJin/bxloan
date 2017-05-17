define(function(require, exports, module) {
	var rm = {
		rules : {
			replyPeriodNum :{
				isIntPositive : true
			},
			applyAmt : {
				isApplyAmt : true,
				isApplyAmtAvailable : ["#basicInfoForm input[name ='projectId']"],
				checkCreditLoanAmt: true
			},
			ovdueRate : {
				isOverdueRate : true
			},
			perculNegoRate : {
				isPerculNegoRate : true
			},
			preRepaymentRate : {
				 isPreRepaymentRate : true
			},
			applyTerm : {
				isLoanTerm : true
			},
			bizRate : {
				isRate : true,
				remote : {
					type:"POST",
					url:$$ctx +"bizapply/validateRate",
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
				isApplyAmtAvailable : "",
				checkCreditLoanAmt:"申报金额不能大于授信剩余可用额度"
			},
			applyTerm : {
				isLoanTerm : ""
			},
			ovdueRate : {
				isOverdueRate : ""
			},
			perculNegoRate : {
				isPerculNegoRate : ""
			},
			preRepaymentRate : {
				 isPreRepaymentRate : ""
			},
			bizRate : {
				isRate : "",
				remote : "利率值必须小于或等于央行基准利率的定值倍数，请咨询管理员"
			}
		}
	};
	module.exports = rm;
});