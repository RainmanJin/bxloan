define(function(require, exports, module) {
	var model = Backbone.Model.extend({
		initialize: function() {
			// do nothing
		},
		changeIsUpload:function(data,callback){
			 $.ajax({
	                cache: true,
	                type: "POST",
	                url: $$ctx + "contractMng/changeIsUpload",
	                data: {
	                    "subcontractId": data
	                },
	                async: false,
	                error: function(request) {
	                    bootbox.alert("改变上传状态出错");
	                },
	                success: function(data) {
	                    callback(data);
	                }
	            });
		},
		checkContractReady:function(data,callback){
			$.post($$ctx + "contractMng/checkContractReady", data).success(function(r) {
				callback(r);
			}).error(function(){bootbox.alert("checkContractReady失败请稍后重试");});
		},
		getNextAssigner:function(data,callback){
			$.post($$ctx + "wdapproval/getNextTaskAssigners/"+data).success(function(r) {
				callback(r);
			}).error(function(){bootbox.alert("getNextAssigner失败请稍后重试");});
		},
		getCollateralInfo:function(data,callback){
			$.post($$ctx + "collateral/getCollateralData",data).success(function(r) {
				callback(r);
			}).error(function(){bootbox.alert("getCollateralInfo失败请稍后重试");});
		},
		dismissAppr:function(data,callback){
			$.post($$ctx + "wdapproval/dropBack",data).success(function(r) {
				callback(r);
			}).error(function(){bootbox.alert("撤销失败请稍后重试");});
		},
		getSpouseInfo:function(correlativeRelationsId,callback){//获取配偶信息
			var params=[];
			params.push('correlativeRelationsId='+correlativeRelationsId);
			$.get($$ctx + "singleCustomer/findOneFamilyFriend",params.join('&')).success(function(r) {
				callback(r);
			}).error(function(){bootbox.alert("撤销失败请稍后重试");});
		},
		openCustomerWindow:function(mark,data,callback){
			switch (mark+"") {
			case "210":
			case "1":{
				var r =  ("corpcustomer/showDetail/" + data.customerId);
				callback(r);
				break;}
			case "2":
			default:{
				$.post($$ctx + "singleCustomer/modToForm",data).success(function(r) {
					callback(r);
				}).error(function(){bootbox.alert("查询失败请稍后重试");})
				break;}	
			}
			
		},
		openBusinessWindow:function(path,data,callback){
			$.post($$ctx + path,data).success(function(r) {
				callback(r);
			}).error(function(){bootbox.alert("查询失败请稍后重试");})
		},
		verifyContrDownload:function(subContractId,callback){
			$.post($$ctx + "contractFile/verifyContrDownload/"+subContractId).success(function(r) {
				callback(r);
			}).error(function(){bootbox.alert("查询失败请稍后重试");})
		},
		verifyContrListDownload:function(subContractId,callback){
			var modelSelf=$(this);
			$.post($$ctx + "contractFile/verifyContrListDownload/"+subContractId).success(function(r) {
				callback(r);
			}).error(function(){bootbox.alert("查询失败请稍后重试");})
		},
		downloadGuarContr:function(id,callback){
			callback($$ctx+"contractFile/downloadGuarContr/"+id);
		},
		downloadPledContrList:function(id,callback){
			callback($$ctx+"contractFile/downloadPledContrList/"+id);
		},
		downloadCollaContr:function(id,callback){
			callback($$ctx+"contractFile/downloadCollaContr/"+id);
		},
		downloadFile:function(url){
			var gotoLink = document.createElement('a');
			gotoLink.style.display = 'none';
			gotoLink.href = url;
		    document.body.appendChild(gotoLink);
		    gotoLink.click();
		    document.body.removeChild(gotoLink);
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