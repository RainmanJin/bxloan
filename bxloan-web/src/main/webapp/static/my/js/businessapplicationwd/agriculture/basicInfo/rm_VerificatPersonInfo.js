define(function(require, exports, module) {
	var rm_VerificatPersonInfo = {
			rules: {
				name: { 
					required: true
				},
				relation: { 
					required: true
				},
				telphone: { 
					required: true,
					isPhone: true
				}
				
	        },
	        messages: {
	        	
	        }
	};
	module.exports = rm_VerificatPersonInfo;
});