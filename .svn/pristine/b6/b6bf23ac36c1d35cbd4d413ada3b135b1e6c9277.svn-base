define(function(require, exports, module) {
	var model = Backbone.Model.extend({
		initialize: function() {
		},
		saveApprovalPhoneDetail: function(data,callback){
			$.ajax({
                cache: false,
                type: "POST",
                url: $$ctx + "wdapproval/saveApprovalPhoneDetail",
                data: $('#phoneForm').serialize(),
                async: false,
                error: function(request) {
                    utils.alert.err("错误"+ request.status);
                },
                success: function(data) {
                	callback(data);
                }
			 });
		},
		findWifeNameByPartyId: function(data,callback){
			$.ajax({
				url: $$ctx + 'wdapproval/findWifeByPartyId',
				data:data,
				dataType: 'JSON',
				type: 'POST',
				async:false,
				success: function(data) {
					callback(data);
				}
			});
		},
		delApprovalPhoneDetail:function(id,callback){
			$.ajax({
				type : 'POST',
				url : $$ctx + 'wdapproval/delApprovalPhoneDetail',
				data : {"id":id},
				success : function(r_data) {
					callback(r_data);
				}
			});
		},
		editApprovalPhoneDetail:function(id,callback){
			$.ajax({
				type : 'POST',
				url : $$ctx + 'wdapproval/editApprovalPhoneDetail',
				data : {"id":id},
				success : function(r_data) {
					callback(r_data);
				}
			});
		},
		submitForm:function(data,callback){
			
			$.post($$ctx + "wdapproval/submitApproval",data).success(function(r) {
				callback(r);
			}).error(function(){bootbox.alert("提交失败请稍后重试");});
		},
		contractAppr:function(data,callback){
			$.post($$ctx + "wdapproval/contractAppr",data).success(function(r) {
				callback(r);
			}).error(function(){bootbox.alert("提交合同失败请稍后重试");});
		},
		getNextAssigner:function(data,callback){
			$.post($$ctx + "wdapproval/getNextTaskAssigners/"+data).success(function(r) {
				callback(r);
			}).error(function(){bootbox.alert("getNextAssigner失败请稍后重试");});
		},
		quitAppr:function(data,callback){
			$.post($$ctx + "wdapproval/quitApproval",data).success(function(r) {
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
					callback($$ctx +r);
				}).error(function(){bootbox.alert("查询失败请稍后重试");})
				break;}	
			default:
				break;
			}
		},
		openBusinessWindow:function(data,callback){
				$.post($$ctx + "bizapply/checkApplicationWindow",data).success(function(r) {
					callback(r);
				}).error(function(){bootbox.alert("查询失败请稍后重试");})
		},
		fetchCommentDetail : function(data,callback){
			$.post($$ctx + "wfmonitor/detail",data).success(function(r) {
				callback(r);
			}).error(function(){bootbox.alert("查看详细失败.请稍后再试");});
		},
		checkUploadFiles : function(data, callback){
			$.post($$ctx + "wdapproval/checkUploadFiles",data).success(function(r) {
				callback(r);
			}).error(function(){bootbox.alert("查看详细失败.请稍后再试");});
		},
		findApprovalMsg : function(data, callback){
			$.post($$ctx + "wdapproval/findApprovalMsg",data).success(function(r) {
				callback(r);
			}).error(function(){bootbox.alert("查看详细失败.请稍后再试");});
		},
		//add by wangyawei 20150701 start 在审批过程中生成邦农贷财务报表，下载前进行数据校验
		downloadEcfiBefore: function(projId, callback){
			var params = [];
			params.push("projId=" + projId);
			$.post($$ctx + "expectCashFlowInfo/downloadEcfiBefore", params.join('&')).success(function(r) {
				callback(r);
			}).error(function(){bootbox.alert("生成财务报表出错");});
		}
		//add by wangyawei 20150701 end
	});
	module.exports = model;
});