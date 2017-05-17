define(function(require, exports, module) {
	var rm = {
		rules: {
			applyAmt: {
				isApplyAmt : true,
				isApplyAmtAvailable : ["#basicInfoForm input[name ='projectId']"]
			},
			rateValue: {
				isRate : true
			},
			overFloatRate: {
				isOverdueRate : true
			},
			divertFloatRate: {
				isPerculNegoRate : true
			},
			applyTerm : {
				isLoanTerm : true
			},
			rateValue : {
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
							return $("#basicInfoForm input[name='rateValue']").val();
						}
					}
				}
			}
		},
		messages: {
			applyAmt: {
				isApplyAmt : ""
			},
			rateValue: {
				isRate : ""
			},
			overFloatRate: {
				isOverdueRate : ""
			},
			divertFloatRate: {
				isPerculNegoRate : ""
			},
			applyTerm : {
				isLoanTerm : ""
			},
			rateValue : {
				isRate : "",
				remote : "利率值必须小于或等于央行基准利率的定值倍数，请咨询管理员"
			}
		}
	};
	module.exports = rm;
});