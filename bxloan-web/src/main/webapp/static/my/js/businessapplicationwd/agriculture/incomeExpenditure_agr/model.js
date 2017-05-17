define(function(require, exports, module) {
	var ecfiUrl = $$ctx + "expectCashFlowInfo/"
	var model = Backbone.Model.extend({
		initialize: function() {
		},
		findEcfiList: function(data,callback){
			$.ajax({
				url: ecfiUrl + 'findIncomeOrConsumeList',
				dataType: 'JSON',
				type: 'POST',
				data:data,
				async:false,
				success: function(data) {
					callback(data);
				}
			});
		},
		saveEcfiDetail: function(data,callback){
			$.post(ecfiUrl + 'saveEcfi', data, function(r){
				callback(r);
			})
		},
		modifyEcfiDetail: function(data,callback){
			$.post(ecfiUrl + 'saveEcfi', data, function(r){
				callback(r);
			})
		},
		deleteEcfi: function(id,callback){
			$.ajax({
				url: ecfiUrl + 'delExpectCashFlowInfo',
				dataType: 'JSON',
				type: 'POST',
				data:{'eid':id},
				success: function(data) {
					callback(data);
				}
			});
		}
	});
	module.exports = model;
});