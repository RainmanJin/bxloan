define(function(require, exports, module) {
	var rm = {
		rules: {
			approveDateStr:{
				required: true
			},
			approveAmt:{
				required: true,
				isPositiveNumberTwo:true,
				isApplyAmt:true,
				isApplyAmtAvailable:["#sample-form input[name='projectId']"]
			},
			term:{
				required: true,
				isIntPositive:true,
				isLoanTerm:true
			},
			termUnit:{
				required: true
			},
			approveRepayingMode:{
				required: true
			},
			approveIrTypeCd:{
				required: true
			},
			approveAdjustPeriod:{
				required: true
			},
			approveFloatRateStr:{
				required: true,
				isPercentNumberFour:true
			},
			approveRateValueStr:{
				required: true,
				isPercentNumberFour:true,
				isRate:true,
				remote:{
					type:"POST",
					url:$$ctx +"bizapply/validateRate",
					data: {
						projectId : $("#projectId").val(),
						applyTerm : function(){
							return $("#sample-form input[name='term']").val();
						},
						applyTermUnit : function(){
							return $("#sample-form input[name='termUnit']").val();
						}
					}
				}
			},
			replyOpinion:{
				required: true,
				maxlength:3000
			}
		},
		messages:{
			approveAmt : {
				isApplyAmt : ""
			},
			term : {
				isLoanTerm : ""
			},
			approveFloatRateStr:{
				remote : "利率值必须小于或等于央行基准利率的定值倍数，请咨询管理员"
			},
			approveRateValueStr:{
				isRate : "",
				remote : "利率值必须小于或等于央行基准利率的定值倍数，请咨询管理员"
			}
		}
	}
	module.exports = rm;
})