define(function(require, exports, module) {
	var model = Backbone.Model.extend({
		initialize: function() {
			// do nothing
		},
		findMainContract:function(data,callback){//查询主合同
			$.post($$ctx + "contractQuery/findPrincipalContractInfo", data, function(obj) {
				callback(obj);
			});
		},
		downloadGuarContr:function(id,callback){
			callback($$ctx+"contractFile/downloadGuarContr/"+id);
		},
		//下载抵质押从合同方法
		downloadCollaContr:function(id,callback){
			callback($$ctx+"contractFile/downloadCollaContr/"+id);
		},
		//下载抵质押从合同清单方法
		downloadPledContrList:function(id,callback){
			callback($$ctx+"contractFile/downloadPledContrList/"+id);
		},
		verifyContrListDownload:function(subContractId,callback){
			var modelSelf=$(this);
			$.post($$ctx + "contractFile/verifyContrListDownload/"+subContractId).success(function(r) {
				callback(r);
			}).error(function(){bootbox.alert("查询失败请稍后重试");});
		},
		downloadFile:function(url){
			var gotoLink = document.createElement('a');
			gotoLink.style.display = 'none';
			gotoLink.href = url;
		    document.body.appendChild(gotoLink);
		    gotoLink.click();
		    document.body.removeChild(gotoLink);
		},
		getSpouseInfo:function(correlativeRelationsId,callback){//获取配偶信息
			var params=[];
			params.push('correlativeRelationsId='+correlativeRelationsId);
			$.get($$ctx + "singleCustomer/findOneFamilyFriend",params.join('&')).success(function(r) {
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
				}).error(function(){bootbox.alert("查询失败请稍后重试");});
				break;}	
			default:
				break;
			}
		},
		fetchDetail : function(data,callback){
			$.post($$ctx + "wfmonitor/detail",data).success(function(r) {
				callback(r);
			}).error(function(){bootbox.alert("查看详细失败.请稍后再试");});
		},
		cancelWorkflow : function(data,callbacl){
			$.post($$ctx + "wfmonitor/cancel",data).success(function(r) {
				callbacl(r);
			}).error(function(){bootbox.alert("撤销流程失败.请稍后再试");});
		}
	});
	module.exports = model;
});