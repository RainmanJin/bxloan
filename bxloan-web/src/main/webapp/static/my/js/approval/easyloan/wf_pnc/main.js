define(function(require, exports, module) {
	var view = require('./view');
	new view();
	var telTableView = require("../../applyInfo/view");
	var documentView = require("../../../approvedocument/view");
	var documentSet = new documentView();
	documentSet.render();
	new telTableView();
});