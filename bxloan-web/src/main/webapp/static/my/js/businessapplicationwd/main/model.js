define(function(require, exports, module) {
	var wdBizUrl = "businessapplicationwd"
	var model = Backbone.Model.extend({
		initialize: function() {
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
		findCustDocTypes: function(data,callback){
			$.post($$ctx + wdBizUrl+"/findUploadCustDocTypes", data).success(function(r) {
				callback(r);
			}).error(function(){bootbox.alert("查看上传文档类型出错");});
		},
		downloadExfi:function(projId,callback){
			var params=[];
			params.push("projId="+projId);
			$.post($$ctx + "expectCashFlowInfo/downloadEcfiBefore", params.join('&')).success(function(r) {
				callback(r);
			}).error(function(){bootbox.alert("生成财务报表出错");});
		}

	});
	module.exports = model;
});