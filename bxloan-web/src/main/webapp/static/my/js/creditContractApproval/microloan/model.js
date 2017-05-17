define(function(require, exports, module) {
	var model = Backbone.Model.extend({
		initialize: function() {
		},
		
		/** 提交下一环节 */
		checkUploadFiles : function(data, callback){    //检查文档数量和类型是否正确
			$.post($$ctx + "creditContractApproval/checkUploadFiles",data).success(function(r) {
				callback(r);
			}).error(function(){bootbox.alert("查看详细失败.请稍后再试");});
		},
		
		/** 提交下一环节 */
		submitApproval:function(data,callback){ 
			$.post($$ctx + "creditContractApproval/submitApproval",data).success(function(r) {
				callback(r);
			}).error(function(){bootbox.alert("提交失败请稍后重试");});
		},
		
		/** 初审环节和一级审批环节强制跳转到制定电子合同 */
		contractAppr:function(data,callback){  
			$.post($$ctx + "creditContractApproval/submitToContractApproval",data).success(function(r) {
				callback(r);
			}).error(function(){bootbox.alert("提交合同失败请稍后重试");});
		},
		
		/** 获取下一环节所有人 */
		getNextAssigner:function(data,callback){  
			$.post($$ctx + "wdapproval/getNextTaskAssigners/"+data).success(function(r) {
				callback(r);
			}).error(function(){bootbox.alert("getNextAssigner失败请稍后重试");});
		},
		
		/** 退回 */
		quitAppr:function(data,callback){  
			$.post($$ctx + "creditContractApproval/quitApproval",data).success(function(r) {
				callback(r);
			}).error(function(){bootbox.alert("退回失败请稍后重试");});
		},
		
		/** 拒绝 */
		dismissAppr:function(data,callback){ 
			$.post($$ctx + "creditContractApproval/dropBack",data).success(function(r) {
				callback(r);
			}).error(function(){bootbox.alert("撤销失败请稍后重试");});
		},
		
		/** 查询客户信息 */
		openCustomerWindow:function(mark,data,callback){  
			switch (mark) {
			case "1":{  //企业客户
				var r =  ($$ctx + "corpcustomer/showDetail/" + data.customerId);
				callback(r);
				break;}
			case "2":{  //个人客户
				$.post($$ctx + "singleCustomer/modToForm",data).success(function(r) {
					callback($$ctx + r);
				}).error(function(){bootbox.alert("查询失败请稍后重试");})
				break;}	
			default:
				break;
			}
		},
		
		/** 查询业务信息 */
		openBusinessWindow:function(data,callback){
			$.post($$ctx + "bizCreditApplication/checkApplicationWindow",data).success(function(r) {
				callback(r); 
			}).error(function(){bootbox.alert("查询失败请稍后重试");});
		},
		
		/** 查询过程意见信息 */
		fetchCommentDetail : function(data,callback){
			$.post($$ctx + "wfmonitor/detail",data).success(function(r) {
				callback(r);
			}).error(function(){bootbox.alert("查看详细失败.请稍后再试");});
		},
		
		/** 查询批复信息 */
		findApprovalMsg : function(data, callback){
			$.post($$ctx + "wdapproval/findApprovalMsg",data).success(function(r) {
				callback(r);
			}).error(function(){bootbox.alert("查看详细失败.请稍后再试");});
		}
	});
	module.exports = model;
});