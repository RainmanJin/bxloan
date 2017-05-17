define(function(require, exports, module) {
	var expectUrl = $$ctx + "expectCashFlow/"
	var model = Backbone.Model.extend({
		initialize: function() {
		},
		deleteEcf: function(id,callback){
			$.ajax({
				url: expectUrl + 'delEcf/' + id,
				dataType: 'JSON',
				type: 'POST',
				success: function(data) {
					callback(data);
				}
			});
		},
		deleteEcfd: function(id,callback){
			$.ajax({
				url: expectUrl + 'delEcfd/' + id,
				dataType: 'JSON',
				type: 'POST',
				success: function(data) {
					callback(data);
				}
			});
		},
		saveEcfDetail: function(data,callback){
			$.post(expectUrl + "saveEcfDetail", data, function(r){
				callback(r);
			})
		},
		findCalculateEcf: function(data,callback){
			$.post(expectUrl + "findCalculateEcf", data, function(r){
				callback(r);
			})
		}
	});
	module.exports = model;
});