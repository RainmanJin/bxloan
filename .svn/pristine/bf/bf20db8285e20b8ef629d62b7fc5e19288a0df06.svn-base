define(function(require, exports, module) {
	var rm = {
		rules: {
			productName : {
				required : true
			},
			customerType : {
				required : true
			},
			orgId : {
				required : true
			},
			minLoanTerm1 : {
				//required : true,
				isNumber : true
			},
			minLoanTerm : {
				//required : true,
				isPositiveNumberTwo:true,
				max:function(){
					if($("#maxLoanTerm").val()!="" && $("#maxLoanTerm").val()!=undefined){
						return parseFloat($("#maxLoanTerm").val());
					}else{
						return parseFloat($("#minLoanTerm").val())+1;
					}
				}
			},
			maxLoanTerm : {
				//required : true,
				isPositiveNumberTwo:true,
				min:function(){
					if($("#minLoanTerm").val()!="" && $("#minLoanTerm").val()!=undefined){
						return parseFloat($("#minLoanTerm").val());
					}else{
						return 0;
					}
				}
			},
			specialLoanTerm : {
				//required : true
			},
			minApplyAmt : {
				//required : true,
				isPositiveNumberTwo:true,
				max:function(){
					if($("#maxApplyAmt").val()!="" && $("#maxApplyAmt").val()!=undefined){
						return parseFloat($("#maxApplyAmt").val());
					}else{
						return parseFloat($("#minApplyAmt").val())+1;
					}
				}
			},
			maxApplyAmt : {
				//required : true,
				isPositiveNumberTwo:true,
				min:function(){
					if($("#minApplyAmt").val()!="" && $("#minApplyAmt").val()!=undefined){
						return parseFloat($("#minApplyAmt").val());
					}else{
						return 0;
					}
				}
			},
			minRate : {
				isPositiveNumberFour:true,
				max:function(){
					if($("#maxRate").val()!="" && $("#maxRate").val()!=undefined){
						return parseFloat($("#maxRate").val());
					}else{
						return parseFloat($("#minRate").val())+1;
					}
				}
			},
			maxRate : {
				//required : true,
				isPositiveNumberFour:true,
				min:function(){
					if($("#minRate").val()!="" && $("#minRate").val()!=undefined){
						return parseFloat($("#minRate").val());
					}else{
						return 0;
					}
				}
			},
			minOverdueRate : {
				//required : true,
				isPositiveNumberFour:true,
				max:function(){
					if($("#maxOverdueRate").val()!="" && $("#maxOverdueRate").val()!=undefined){
						return parseFloat($("#maxOverdueRate").val());
					}else{
						return parseFloat($("#minOverdueRate").val())+1;
					}
				}
			},
			maxOverdueRate : {
				//required : true,
				isPositiveNumberFour:true,
				min:function(){
					if($("#minOverdueRate").val()!="" && $("#minOverdueRate").val()!=undefined){
						return parseFloat($("#minOverdueRate").val());
					}else{
						return 0;
					}
				}
			},
			minPerculNegoRate : {
				//required : true,
				isPositiveNumberFour:true,
				max:function(){
					if($("#maxPerculNegoRate").val()!="" && $("#maxPerculNegoRate").val()!=undefined){
						return parseFloat($("#maxPerculNegoRate").val());
					}else{
						return parseFloat($("#minPerculNegoRate").val())+1;
					}
				}
			},
			maxPerculNegoRate : {
				//required : true,
				isPositiveNumberFour:true,
				min:function(){
					if($("#minPerculNegoRate").val()!="" && $("#minPerculNegoRate").val()!=undefined){
						return parseFloat($("#minPerculNegoRate").val());
					}else{
						return 0;
					}
				}
			},
			minPreRepaymentRate : {
				//required : true,
				isPositiveNumberFour:true,
				max:function(){
					if($("#maxPreRepaymentRate").val()!="" && $("#maxPreRepaymentRate").val()!=undefined){
						return parseFloat($("#maxPreRepaymentRate").val());
					}else{
						return parseFloat($("#minPreRepaymentRate").val())+1;
					}
				}
			},
			maxPreRepaymentRate : {
				//required : true,
				isPositiveNumberFour:true,
				min:function(){
					if($("#minPreRepaymentRate").val()!="" && $("#minPreRepaymentRate").val()!=undefined){
						return parseFloat($("#minPreRepaymentRate").val());
					}else{
						return 0;
					}
				}
			},
			repayingMode : {
				//required : true
			},
			repayPeriodNum : {
				isNumber : true
			},
			guaranteeMode : {
				//required : true
			},
			batchLimit : {
				//required : true,
				isPositiveNumberTwo:true
			},
			isBatch : {
				//required : true
			},
			isStart : {
				required : true
			}
		},
		messages:{
			
		}
	};
	module.exports = rm;
});