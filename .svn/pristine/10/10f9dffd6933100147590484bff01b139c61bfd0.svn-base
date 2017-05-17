define(function(require, exports, module) {
	var customerUrl = $$ctx + "singleCustomer/"
	var model = Backbone.Model.extend({
		initialize: function() {
		},
		getCustomerMessage: function(data, callback){
			$.post(customerUrl + "getIndividualAgro", data, function(r){
				callback(r);
			})
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
		deleteVerificatPerson: function(id,callback){
			 $.ajax({
              url: $$ctx + 'singleCustomer/delVerificatPerson/' + id,
              dataType: 'JSON',
              type: 'POST',
              success: function(data) {
                  callback(data);
              }
          });
		}
	});
	module.exports = model;
});