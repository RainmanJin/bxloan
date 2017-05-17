/**
 * 客户列表
 * 
 * author: lijing
 * lastModified: lijing 2014-08-05 16:30:24
 */
define(function(require, exports, module) {
	var view = require('./view');
	new view();
	
	var viewCusManager = require('../../../customer/cusmanager/view');
	new viewCusManager();
});