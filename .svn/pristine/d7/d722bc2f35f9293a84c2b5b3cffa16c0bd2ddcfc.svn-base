/**
 * 客户列表-经营
 * 
 * author: lijing
 * lastModified: lijing 2014-08-05 16:30:24
 */

define(function(require, exports, module) {

    var model = Backbone.Model.extend({
        initialize: function() {
            // do nothing
        },
        refreshSelectorTable: function(data, callback){
			$.post($$ctx + "singleCustomer/findDocumentCustDocTypes", data, function(r) {
				callback(r);
			});
		},
		findCustDocTypes: function(data,callback){
			$.post($$ctx + "singleCustomer/findUploadCustDocTypes", data).success(function(r) {
				callback(r);
			}).error(function(){bootbox.alert("查看上传文档类型出错");});
		},
        findOneCustomer: function(data, callback) {
            $.post($$ctx + "singleCustomer/findOneCustomer", data).success(function(r) {
                callback(r);
            }).error(function() {
                bootbox.alert("findOneCustomer失败请稍后重试");
            });
        },
        findOneFinace: function(data, callback) {
            $.post($$ctx + "singleCustomer/findOneFinace", data).success(function(r) {
                callback(r);
            }).error(function() {
                bootbox.alert("findOneFinace失败请稍后重试");
            });
        },
        findOneBusiness: function(data, callback) {
            $.post($$ctx + "singleCustomer/findOneBusiness", data).success(function(r) {
                callback(r);
            }).error(function() {
                bootbox.alert("findOneBusiness失败请稍后重试");
            });
        },
        findOneAccount: function(data, callback) {
            $.post($$ctx + "singleCustomer/findOneAccount", data).success(function(r) {
                callback(r);
            }).error(function() {
                bootbox.alert("findOneAccount失败请稍后重试");
            });
        },
        delAccount: function(data, callback) {
            $.post($$ctx + "singleCustomer/delAccount/" + data).success(function(r) {
                callback(r);
            }).error(function() {
                bootbox.alert("delAccount失败请稍后重试");
            });
        },
		loadNationAreaData:function(code,callback){
			$.ajax({
				async:false,
				url: $$ctx +"userMngInfo/loadNaData/"+code,
				success:function(obj){
					callback(obj);
				}
			});
		},
		deleteLxr: function(id,callback){
			 $.ajax({
                url: $$ctx + 'singleCustomer/delFamilyFriend/' + id,
                dataType: 'JSON',
                type: 'POST',
                success: function(data) {
                    callback(data);
                }
            });
		},
		actAliveBtn:function(data, callback){
			$.post($$ctx + "singleCustomer/actAlive", data).success(function(r) {
				callback(r);
			}).error(function(){bootbox.alert(_failure + "actAlive失败请稍后重试");});
		}
    });
    module.exports = model;
});