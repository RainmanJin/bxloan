define(function(require, exports, module) {
	var rm = {
			rules : {
				gainDisType:{
					required:true
				},
				time:{
					required:true
				},
				content:{
					required:true
				},
				amount:{
					required:true,
					isIntPositive:true
				}
			},
			messages:{
				amount:{
					isIntPositive:"请输入金额，如：100,200"
				}
			}
	}
	module.exports = rm;
});