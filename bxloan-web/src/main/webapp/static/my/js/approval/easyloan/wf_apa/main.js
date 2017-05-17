define(function(require, exports, module) {
	var view = require('./view');
	new view();
	var telTableView = require("../../applyInfo/view");
	var telView = new telTableView();
	var documentView = require("../../../approvedocument/view");
	var documentSet = new documentView();
	documentSet.render();
	telView.disableForm();
});