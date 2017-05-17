define(function(require, exports, module) {
	var rm = {
			rules : {
				otherIncomeType:{
					required:true
				},
				yearIncome:{
					required:true,
					isNotNegative:true
				}
			},
			messages:{
				rent:{
					isNotNegative:"请输入金额，如：100,200.00"
				}
			}
	}
	module.exports = rm;
});