define(function(require, exports, module) {
	var rm = {
		rules : {
			type : {
				required : true
			},
			name : {
				required : true
			},
			price : {
				required : true,
				isNotNegative : true
			},
			unit : {
				required : true
			},
			num : {
				required : true,
				isIntPositive : true
			},
			total : {
				required : true,
				isNotNegative : true
			}
		},
		messages : {
			
		}
	};
	module.exports = rm;
});