(function(factory) {
	if (typeof define === "function" && define.amd) {
		define([ "jquery", "../jquery.validate" ], factory);
	} else {
		factory(jQuery);
	}
}(function($) {

	/*
	 * Translated default messages for the jQuery validation plugin. Locale: ZH
	 * (Chinese, 中文 (Zhōngwén), 汉语, 漢語)
	 */
	$.extend($.validator.messages, {
		required: "必填字段",
		remote: "远程校验无效",
		email: "请输入有效的电子邮件",
		url: "请输入有效的网址",
		date: "请输入有效的日期",
		dateISO: "请输入有效的日期 (YYYY-MM-DD)",
		number: "请输入正确的数字",
		digits: "只可输入数字",
		creditcard: "请输入有效的信用卡号码",
		equalTo: "你的输入不相同",
		extension: "上传文件后缀必须为{0}",
		maxlength: $.validator.format("最多 {0} 个字"),
		minlength: $.validator.format("最少 {0} 个字"),
		rangelength: $.validator.format("请输入长度为 {0} 至 {1} 之间的字串"),
		range: $.validator.format("请输入 {0} 至 {1} 之间的数值"),
		max: $.validator.format("请输入不大于 {0} 的数值"),
		min: $.validator.format("请输入不小于 {0} 的数值")
	});
	
	$.extend($.validator.defaults, {
		ignore: "[type='hidden']",
		errorElement: "div",
		errorClass: "help-block",
		//focusInvalid : false,
		highlight: function(e) {
			$(e).closest(".form-group").removeClass("has-info").addClass("has-error");
		},
		success: function(e) {
			$(e).closest(".form-group").removeClass("has-error");// .addClass("has-info");
			$(e).remove();
		},
		errorPlacement: function(error, element) {
			if (element.is(':checkbox') || element.is(':radio')) {
				var controls = element.closest('div[class*="col-"]');
				if (controls.find(':checkbox,:radio').length > 1) {
					controls.append(error);
				} else {
					error.insertAfter(element.nextAll('.lbl:eq(0)').eq(0));
				}
			} else if (element.is('.select2')) {
				error.insertAfter(element.siblings('[class*="select2-container"]:eq(0)'));
			} else if (element.is('.chosen-select')) {
				error.insertAfter(element.siblings('[class*="chosen-container"]:eq(0)'));
			} else {
				error.insertAfter(element.parent());
			}
		}
	});

}));