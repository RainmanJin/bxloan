define(function(require, exports, module) {
	var rm = {
		rules : {
			certificateNum : {
				isIDcard : "#addCertificateType",
				isAICard : "#addCertificateType",
				isPassport : "#addCertificateType"
			}
		}
	};
	module.exports = rm;
});