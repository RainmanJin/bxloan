define(function(require, exports, module) {
	var model = Backbone.Model.extend({
		initialize: function() {
		},
		getPersonData: function(data, callback){
			$.post($$ctx + "personMng/getPersonData?curUserId="+data).success(function(r) {
				callback(r);
			}).error(function(){bootbox.alert("getPersonData失败！");});
		}
	});
	module.exports = model;
});