define(function(require, exports, module) {
	var model = Backbone.Model.extend({
		initialize : function() {
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
		submitForm : function($form, callback) {
			$form.ajaxSubmit(function(r) {
				callback(r);
			});
		},
		searchCustomer : function(id, callback) {
			$.post($$ctx + "bizapply/getCustomer/" + id, function(obj) {
				callback(obj);
			});
		},
		submitWorkForm : function($form, callback) {
			$form.ajaxSubmit(function(r) {
				callback(r);
			});
		},
		searchBizExpenseRate : function(projectId, callback) {
			$.post($$ctx + "bizapply/searchBizExpenseRate/" + projectId, function(r) {
				callback(r);
			});
		},
		updateBizExpenseRate : function($form, callback) {
			$form.ajaxSubmit(function(r) {
				callback(r);
			});
		},
		saveBizExpenseRate : function($form, callback) {
			$form.ajaxSubmit(function(r) {
				callback(r);
			});
		},
		deleteBizExpenseRate : function(projectId, callback) {
			$.post($$ctx + "bizapply/deleteBizExpenseRate/" + projectId, function(r) {
				callback(r);
			});
		},
		submitOpinion : function($form, callback) {
			$form.ajaxSubmit(function(r) {
				callback(r);
			});
		},
		saveManuScript : function($form, callback) {
			$form.ajaxSubmit(function(r) {
				callback(r);
			});
		}
	});
	module.exports = model;
});