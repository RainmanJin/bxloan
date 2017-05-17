define(function(require, exports, module) {
	var view = require('./view');
	new view();
	//费用
	var feeView = require('./feeRegist/view');
	new feeView();
	//正常还款
	var noramlRepayView = require('./normalRepay/view');
	new noramlRepayView();
});