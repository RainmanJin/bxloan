define(function(require, exports, module) {
	var rm = {
		rules : {
			lifeConsume : {
				required : true,
				isNotNegative : true
			},
			tuition : {
				//required : true,
				isNotNegative : true
			},
			medical : {
				//required : true,
				isNotNegative : true
			},
			insurance : {
				//required : true,
				isNotNegative : true
			},
			others1 : {
				//required : true,
				isNotNegative : true
			},
			others2 : {
				//required : true,
				isNotNegative : true
			},
			others3 : {
				//required : true,
				isNotNegative : true
			},
			familyConsumeTotal : {
				required : true
			},
			interestCost : {
				//required : true,
				isNotNegative : true
			},
			repaymentCost : {
				//required : true,
				isNotNegative : true
			},
			remarks : {
				//required : true
				isTextarea : true
			},
			debtChangeExplain : {
				//required : true
				isTextarea : true
			}
		},
		messages : {
			
		}
	};
	module.exports = rm;
});