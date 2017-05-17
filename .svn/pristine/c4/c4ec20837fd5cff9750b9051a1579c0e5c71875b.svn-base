define(function(require, exports, module) {
	var cAndbUrl = $$ctx + "cultivateAndBreed/"
	var model = Backbone.Model.extend({
		initialize: function() {
		},
		deleteCultivate: function(id,callback){
			$.ajax({
				url: cAndbUrl + 'delCultivate/' + id,
				dataType: 'JSON',
				type: 'POST',
				success: function(data) {
					callback(data);
				}
			});
		},
		deleteBreed: function(id,callback){
			$.ajax({
				url: cAndbUrl + 'delBreed/' + id,
				dataType: 'JSON',
				type: 'POST',
				success: function(data) {
					callback(data);
				}
			});
		},
		findOneCultivate: function(data,callback){
			$.post(cAndbUrl + "findOneCultivate", data, function(r){
				callback(r);
			})
		},
		findOneBreed: function(data,callback){
			$.post(cAndbUrl + "findOneBreed", data, function(r){
				callback(r);
			})
		},
		findNecessaryMsg: function(data,callback){
			$.post(cAndbUrl + "findNecessaryMsg", data, function(r){
				callback(r);
			})
		}
	});
	module.exports = model;
});