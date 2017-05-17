define(function(require, exports, module) {
	var rm = {
		rules : {
			time : {
				required : true
			},
			amount : {
				required : true,
				isNotNegative : true
			},
			content : {
				required : true
			}
		},
		messages : {
			
		}
	};
	module.exports = rm;
});