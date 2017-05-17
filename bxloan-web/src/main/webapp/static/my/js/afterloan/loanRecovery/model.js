define(function(require, exports, module) {
	var model = Backbone.Model.extend({
		initialize: function() {
			// do nothing
		},
		findFeeRegisterInfo:function(id,callback){
			var data=[];
			data.push("contractId="+id);
			$.post($$ctx+"loanRecovery/feeRegister",data.join("&"), function(obj) {
				callback(obj);
			});
		},
		saveFeeRegisterInfo:function(form,callback){
			$.post($$ctx+"loanRecovery/saveFeeRegister",form, function(obj) {
				callback(obj);
			});
		},
		normalRepay:function(id,repayDate,callback){
			var data=[];
			if(repayDate){
				data.push("repayDate="+repayDate);
			}
			$.post($$ctx + "loanRecovery/normalRepay/" + id,data.join("&"), function(obj) {
				callback(obj);
			});
		},
		overdueRepay:function(id,normal2Overdue,repayDate,callback){
			var data=[];
			data.push("normal2Overdue="+normal2Overdue);
			if(repayDate){
				data.push("repayDate="+repayDate);
			}
			$.post($$ctx + "loanRecovery/overdueRepay/" + id,data.join("&"), function(obj) {
				callback(obj);
			});
		},
		overdueRepayTrial:function(id,callback){
			$.post($$ctx + "loanRecovery/overDueRepayTrial/" + id, function(obj) {
				callback(obj);
			});
		},
		overdueTrial:function(data, callback){
			$.post($$ctx + "loanRecovery/overdueTrial", data, function(obj) {
				callback(obj);
			});
		},
		repaySubmit:function(data,callback){
			$.post($$ctx + "loanRecovery/repaySubmit", data, function(obj) {
				callback(obj);
			});
		}
	});
	module.exports = model;
});