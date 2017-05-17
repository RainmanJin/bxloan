/**
 * 联保体客户详细
 * 
 * author: lijing
 * lastModified: lijing 2014-08-05 16:30:24
 */


define(function(require, exports, module) {
	 var utils = require("utils");
	var controllerUrl = $$ctx + 'unitecustomer/';
    var model = Backbone.Model.extend({
        initialize: function() {
            // do nothing
        },
        getUgnInfo: function(data, callback) {
        	$.post(controllerUrl+"getUgnInfo/"+data, function(r){
        		callback(r);
        	});
        },
        saveBasicInfo: function(data, callback) {
        	$.ajax({
                  cache: false,
                  type: "POST",
                  url: controllerUrl + "saveBasicInfo",
                  data: data,
                  async: true,
                  error: function(request) {
                     utils.alert.err("错误" + request.status);
                  },
                  success: function(r) {
                  	callback(r);
                  }
              }); //ajax end
        },
        saveMember : function(data, callback) {
        	$.ajax({
                cache: false,
                type: "POST",
                url: controllerUrl + "saveMember",
                data: data,
                async: true,
                error: function(request) {
                   utils.alert.err("错误" + request.status);
                },
                success: function(r) {
                	callback(r);
                }
            }); //ajax end
      },
      addMembersOfBatch:function(data,callback){//保存联保体成员
    	  $.post(controllerUrl + "addMembersOfBatch" , data, function(r){
    		  callback(r);
    	  }); 
      },
      delMember: function(data, callback){
    	  $.post(controllerUrl + "delMember/" + data, function(r){
    		  callback(r);
    	  });
      },
      exitMember: function(data, callback){
    	  $.post(controllerUrl + "exitMember" , data, function(r){
    		  callback(r);
    	  });
      },
      changeProtocol: function(data, callback){
    	  $.post(controllerUrl + "changeProtocol" , data, function(r){
    		  callback(r);
    	  });
      },
      findCustDocTypes: function(data, callback){
    	  $.post(controllerUrl + "findUploadCustDocTypes" , data, function(r){
    		  callback(r);
    	  });
      }
    });
    module.exports = model;
});