define(function(require, exports, module) {
	var rm = {
		rules: {
			userLogonName: {
				required: true
			},
			managerType: {
				required: true
			}
		},
		messages: {
			userLogonName: {
				required: "请选择一个用户"
			}
		}
	};
	module.exports = rm;
});