define(function (require, exports, module){
	var rm = {
			rules: {
				proposalAmtStr:{
					required: true,
					isPositiveNumberTwo:true,
					isApplyAmt:true,
					isApplyAmtAvailable:["#form-projAppli input[name='projectId']"]
				},
				proposalTerm:{
					required: true
				},
				proposalRateStr:{
					required: true,
					isPositiveNumberFour :true,
					isRate:true,
					remote:{
						type:"POST",
						url:$$ctx + "bizapply/validateRate",
						data: {
							projectId : $("#projectId").val(),
							applyTerm : function(){ return $("#form-projAppli select[name='proposalTerm']").val();},
							applyTermUnit : 2,
							approveRateValueStr : function(){ return $("#_proposalRateStr").val();}
						}
					}
				},
				comments:{
					required: true,
					maxlength:400
				}
			},
			messages:{
				proposalAmtStr:{
					isApplyAmt : ""
				},
				proposalRateStr:{
					isRate : "",
					remote:"利率值必须小于或等于央行基准利率的定值倍数，请咨询管理员"
				}
			}
		}
		module.exports = rm;
});