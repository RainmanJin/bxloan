/**
 * 联保体客户
 * 
 * author: lijing
 * lastModified: lijing 2014-08-05 16:30:24
 */
define(function(require, exports, module) {
	var controllerUrl = $$ctx + 'unitecustomer/';
	var model = Backbone.Model.extend({
		initialize: function() {
			// do nothing
		},
		preAddUniteGuNego:function(data, callback){
			$.post(controllerUrl+"saveSimple",data, function(r){
				callback(r);
			})
		},
		delUniteCustomer: function(data, callback){
			$.post(controllerUrl+"delUniteCustomer/"+data, function(r){
				callback(r);
			})
		},
		calculateGuNum: function(callback){
			$.post(controllerUrl+"getGuNum", function(r){
				callback(r);
			})
		},
		checkAllBizStatus:function(ugnId,callback){
			$.post(controllerUrl+"checkAllBizStatus/"+ugnId, function(r){
				callback(r);
			});
		}
		
	});
	module.exports = model;
});