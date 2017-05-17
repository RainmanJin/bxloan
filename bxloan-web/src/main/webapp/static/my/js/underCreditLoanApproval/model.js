define(function(require, exports, module) {
	var model = Backbone.Model.extend({
		initialize: function() {
		},
		submitForm:function(data,callback){
			$.post($$ctx + "underCreditLoanApproval/submitApproval",data).success(function(r) {
				callback(r);
			}).error(function(){bootbox.alert("提交失败请稍后重试");});
		},
		contractAppr:function(data,callback){
			$.post($$ctx + "underCreditLoanApproval/contractAppr",data).success(function(r) {
				callback(r);
			}).error(function(){bootbox.alert("提交合同失败请稍后重试");});
		},
		getNextAssigner:function(data,callback){
			$.post($$ctx + "wdapproval/getNextTaskAssigners/"+data).success(function(r) {
				callback(r);
			}).error(function(){bootbox.alert("getNextAssigner失败请稍后重试");});
		},
		quitAppr:function(data,callback){
			$.post($$ctx + "underCreditLoanApproval/quitApproval",data).success(function(r) {
				callback(r);
			}).error(function(){bootbox.alert("退回失败请稍后重试");});
		},
		dismissAppr:function(data,callback){
			$.post($$ctx + "wdapproval/dropBack",data).success(function(r) {
				callback(r);
			}).error(function(){bootbox.alert("撤销失败请稍后重试");});
		},
		openCustomerWindow:function(mark,data,callback){
			switch (mark) {
			case "1":{
				var r =  ($$ctx + "corpcustomer/showDetail/" + data.customerId);
				callback(r);
				break;}
			case "2":{
				$.post($$ctx + "singleCustomer/modToForm",data).success(function(r) {
					callback(r);
				}).error(function(){bootbox.alert("查询失败请稍后重试");})
				break;}	
			default:
				break;
			}
		},
//		openBusinessWindow:function(data,callback){
//				$.post($$ctx + "underCreditContractMng/checkApplicationWindow",data).success(function(r) {
//					callback(r);
//				}).error(function(){bootbox.alert("查询失败请稍后重试");})
//		},
		fetchCommentDetail : function(data,callback){
			$.post($$ctx + "wfmonitor/detail",data).success(function(r) {
				callback(r);
			}).error(function(){bootbox.alert("查看详细失败.请稍后再试");});
		},
		checkUploadFiles : function(data, callback){
			$.post($$ctx + "creditContractApproval/checkUploadFiles",data).success(function(r) {
				callback(r);
			}).error(function(){bootbox.alert("查看详细失败.请稍后再试");});
		},
		findApprovalMsg : function(data, callback){
			$.post($$ctx + "underCreditLoanApproval/findApprovalMsg",data).success(function(r) {
				callback(r);
			}).error(function(){bootbox.alert("查看详细失败.请稍后再试");});
		}
	});
	module.exports = model;
});