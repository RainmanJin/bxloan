define(function(require, exports, module) {
	
	var view = require('./view');
	new view();
	/** 基本信息 */
	var basicInfo = require('../basicInfo/view');
	new basicInfo();
	/** 费用列表 */
	var expenseList = require('../expenseList/view');
	new expenseList();
	/** 抵押质担保 */
	var collateral = require('../collateral/view');
	new collateral();
	/** 保证人担保 */
	var bail = require('../bail/view');
	new bail();
	/** 共同借款人 */
	var commonBorrower = require('../commonBorrower/view');
	new commonBorrower();	
	/** 相关文档 */
	var newDocList = require('../newDocList/view');
	new newDocList();
	/** 提交申请 */
	var submitApply = require('../submitApply/view');
	new submitApply();
	
});