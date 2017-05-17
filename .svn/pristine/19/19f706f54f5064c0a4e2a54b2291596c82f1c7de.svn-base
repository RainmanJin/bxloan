define(function(require, exports, module) {
	var controllerUrl = "corpcustomer";
	var model = Backbone.Model.extend({
		initialize: function() {
		},
		removeRela : function(data,callback){
			$.post($$ctx + controllerUrl + "/removeRela",data).success(function(r) {
				callback(r);
			}).error(function(){bootbox.alert("删除失败.请稍后再试");});
		},
		detailRelaPerson : function(data,callback){
			$.post($$ctx + controllerUrl + "/detailPerson",data).success(function(r) {
				callback(r);
			}).error(function(){bootbox.alert("查看详细出现问题,请稍后重试");});
		},
		detailRelaCorp : function(data,callback){
			$.post($$ctx + controllerUrl + "/detailCorp",data).success(function(r) {
				callback(r);
			}).error(function(){bootbox.alert("查看详细出现问题,请稍后重试");});
		},
		findCustDocTypes: function(data,callback){
			$.post($$ctx + controllerUrl + "/findUploadCustDocTypes", data).success(function(r) {
				callback(r);
			}).error(function(){bootbox.alert("查看上传文档类型出错");});
		},
		loadNationAreaData:function(code,callback){
			$.ajax({
				async:false,
				url: $$ctx + "userMngInfo/loadNaData/"+code,
				success:function(obj){
					callback(obj);
				}
			});
		},
		getAddrById:function(id,callback){
			$.post($$ctx + "userMngInfo/getAddr/"+id,function(obj){
				callback(obj);
			});
		},
		saveAddr:function(data,callback){
			$.post($$ctx + "userMngInfo/saveAddr",data,function(obj){
				callback(obj);
			});
		},
		delAddrById:function(id,callback){
			$.post($$ctx + "userMngInfo/delAddr/" + id, function(obj) {
				callback(obj);
			});
		},
		beforeUpload:function(partyId,callback){
			$.post($$ctx + "userMngInfo/beforeUpload/"+partyId,function(obj){
				callback(obj);
			});
		},
		delDocById:function(id,callback){
			$.post($$ctx + "userMngInfo/delDoc/" + id, function(obj) {
				callback(obj);
			});
		},
		refreshSelectorTable: function(data, callback){
			$.post($$ctx + controllerUrl +"/findDocumentCustDocTypes", data, function(r) {
				callback(r);
			});
		}
	});
	module.exports = model;
});