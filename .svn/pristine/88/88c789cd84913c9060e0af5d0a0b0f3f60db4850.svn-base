define(function(require, exports, module) {
	var utils = require("utils");
	var model = Backbone.Model.extend({
		initialize : function() {
		},
		saveUnderCreditLoan: function(data,callback){
			$.ajax({
                type: "POST",
                url : $$ctx + "underCreditContractMng/saveUnderCreditLoan?creditContractId=" + $("#creditContractId").val(),
                data: data,
                error: function(request) {
                    utils.alert.err("错误"+ request.status);
                },
                success: function(r_data) {
                	callback(r_data);
                }
			 });
		}
	});
	module.exports = model;
});