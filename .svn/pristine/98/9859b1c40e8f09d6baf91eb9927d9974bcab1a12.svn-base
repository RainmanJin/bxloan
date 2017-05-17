define(function(require, exports, module) {
	var model = Backbone.Model.extend({
		initialize: function() {
		},
		saveProduceArea:function(data,callback){
			$.ajax({
				type : 'POST',
				url : $$ctx + 'bizApply/familyAssets/saveProductionAreaInfo',
				data : data,
				success : function(r_data) {
					callback(r_data);
				}
			});
		},
		delProduceArea:function(id,callback){
			$.ajax({
				type : 'POST',
				url : $$ctx + 'bizApply/familyAssets/delProductionAreaInfo',
				data : {"id":id},
				success : function(r_data) {
					callback(r_data);
				}
			});
		},
		findProductionAreaInfo:function(id,callback){
			$.ajax({
				type : 'POST',
				url : $$ctx + 'bizApply/familyAssets/findProductionAreaInfo',
				data : {"id":id},
				success : function(r_data) {
					callback(r_data);
				}
			});
		},
		findProduceAreaList:function(data,callback){
			$.ajax({
				type : 'POST',
				url : $$ctx + 'bizApply/familyAssets/findProductionAreaInfoList',
				data : data,
				success : function(r_data) {
					callback(r_data);
				}
			});
		},
		saveFixedAsset:function(data,callback){
			$.ajax({
				type : 'POST',
				url : $$ctx + 'bizApply/familyAssets/saveFixedAsset',
				data : data,
				success : function(r_data) {
					callback(r_data);
				}
			});
		},
		delFixedAsset:function(id,callback){
			$.ajax({
				type : 'POST',
				url : $$ctx + 'bizApply/familyAssets/delFixedAsset',
				data : {"id":id},
				success : function(r_data) {
					callback(r_data);
				}
			});
		},
		findFixedAsset:function(id,callback){
			$.ajax({
				type : 'POST',
				url : $$ctx + 'bizApply/familyAssets/findFixedAsset',
				data : {"id":id},
				success : function(r_data) {
					callback(r_data);
				}
			});
		},
		findFixedAssetDictData:function(callback){
			$.ajax({
				type : 'POST',
				sync:false,
				url : $$ctx + 'bizApply/familyAssets/findFixedAssetDictData',
				success : function(r_data) {
					callback(r_data);
				}
			});
		},
		findFixedAssetList:function(data,callback){
			$.ajax({
				type : 'POST',
				url : $$ctx + 'bizApply/familyAssets/findFixedAssetList',
				data : data,
				success : function(r_data) {
					callback(r_data);
				}
			});
		},
		saveNoFixedAssetLiab:function(projId,data,callback){
			$.ajax({
				type : 'POST',
				url : $$ctx + 'bizApply/familyAssets/saveNoFixedAssetLiab',
				data : {"projId":projId,"data":data},
				success : function(r_data) {
					callback(r_data);
				}
			});
		},
		findNoFixedAssetList:function(data,callback){
			$.ajax({
				type : 'POST',
				url : $$ctx + 'bizApply/familyAssets/findNoFixedAssetLiabList',
				data : data,
				success : function(r_data) {
					callback(r_data);
				}
			});
		}
	});
	module.exports = model;
});