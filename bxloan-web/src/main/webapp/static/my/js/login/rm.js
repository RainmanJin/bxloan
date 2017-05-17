define(function(require, exports, module) {
	var rm = {
		rules: {
			logname: {
				required: true
			},
			orgid: {
				required: true
			}
		},
		messages: {
			logname: {
				required: "请选择一个用户"
			},
			orgid: {
				required: "请选择一个机构"
			}
		}
	};
	module.exports = rm;
});