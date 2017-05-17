define(function(require, exports, module) {
	var cAndbUrl = $$ctx + "cultivateAndBreed/"
	var ecfiUrl = $$ctx + "expectCashFlowInfo/"
	var model = Backbone.Model.extend({
		initialize: function() {
		},
		deleteCultivate: function(id,callback){
			$.ajax({
				url: cAndbUrl + 'delNongCultivate/' + id,
				dataType: 'JSON',
				type: 'POST',
				success: function(data) {
					callback(data);
				}
			});
		},
		deleteBreed: function(id,callback){
			$.ajax({
				url: cAndbUrl + 'delNongBreed/' + id,
				dataType: 'JSON',
				type: 'POST',
				success: function(data) {
					callback(data);
				}
			});
		},
		findOneCultivate: function(data,callback){
			$.post(cAndbUrl + "findOneNongCultivate", data, function(r){
				callback(r);
			})
		},
		findOneBreed: function(data,callback){
			$.post(cAndbUrl + "findOneNongBreed", data, function(r){
				callback(r);
			})
		},
		findRelativeCultivateList: function(data,callback){
			$.ajax({
				url: cAndbUrl + 'findCultivateChoosenList',
				data:data,
				dataType: 'JSON',
				type: 'POST',
				async:false,
				success: function(data) {
					callback(data);
				}
			});
		},
		findRelativeBreedList: function(data,callback){
			$.ajax({
				url: cAndbUrl + 'findBreedChoosenList',
				data:data,
				dataType: 'JSON',
				type: 'POST',
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
		}
	});
	module.exports = model;
});