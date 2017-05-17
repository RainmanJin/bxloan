define(function(require, exports, module) {
	var view = require('./view');
	new view();
	/** 基本信息 */
	var basicInfo = require('../basicInfo/view');
	new basicInfo();
	/** 费用列表 */
	var expenseList = require('../../../businessapplicationwd/expenseList/view');
	new expenseList();
	/** 抵质押担保 */
	var collateral = require('../../../businessapplicationwd/collateral/view');
	new collateral();    
	/** 保证人担保 */
	var bail = require('../../../businessapplicationwd/bail/view');
	new bail();
	/** 共同借款人 */
	var commonBorrower = require('../../../businessapplicationwd/commonBorrower/view');
	new commonBorrower();
	/** 相关文档 */
	var document = require('../../../businessapplicationwd/document/view');
	new document();
	/** 提交申请 */
	var submitApply = require('../submitApply/view');
	new submitApply();
	
	/**农贷相关js*/
	//家庭资产信息
	var bizFamilyAssets = require('../../../businessapplicationwd/bizFamilyAssets/view');
	new bizFamilyAssets();
	var agriculturalLoan = $("#agriculturalLoan").val();
	if (agriculturalLoan) {// 是农贷申请
		var bizType = parseInt($("#bizType").val());
		switch (bizType) {
		case 1:// 农业
			/** 收入支出-工商业 */
			var businessBiz_argiculture = require('../../../businessapplicationwd/agriculture/incomeExpenditure_agr/businessBiz');
			new businessBiz_argiculture();
			/** 收入支出-其他收入*/
			var other = require('../../../businessapplicationwd/agriculture/incomeExpenditure_agr/other/view');
			new other();
			/** 收入支出-其他收入-过去12个月利润分配*/
			var profit = require('../../../businessapplicationwd/agriculture/incomeExpenditure_agr/other/profit/view');
			new profit();
			break;
		case 2:// 非农业
			/** 收入支出-工商业 */
			var businessBiz_argiculture = require('../../../businessapplicationwd/agriculture/incomeExpenditure/businessBiz');
			new businessBiz_argiculture();
			/** 收入支出-农业-生产区域信息*/
			var areaInfo = require('../../../businessapplicationwd/agriculture/incomeExpenditure/cultivateAndBreed/areaInfo/view');
			new areaInfo();
			/** 收入支出-其他收入*/
			var other = require('../../../businessapplicationwd/agriculture/incomeExpenditure/other/view');
			new other();
			/** 收入支出-其他收入-过去12个月利润分配*/
			var profit = require('../../../businessapplicationwd/agriculture/incomeExpenditure/other/profit/view');
			new profit();
			break;
		default:
			break;
		}
		/** 农贷客户信息 */
		var agri_basicInfo = require("../../../businessapplicationwd/agriculture/basicInfo/view");
		new agri_basicInfo();
	}
	/** 预期现金流量表 */
	var expectCashFlowView = require('../../../businessapplicationwd/agriculture/expectFlow/view');
	new expectCashFlowView();
	/** 非农 种植养殖业*/
	var fn_cultivateAndBreed = require('../../../businessapplicationwd/agriculture/incomeExpenditure/cultivateAndBreed/view');
	new fn_cultivateAndBreed();
	/** 农 种植养殖业*/
	var n_cultivateAndBreed = require('../../../businessapplicationwd/agriculture/incomeExpenditure/cultivateAndBreedFb/view');
	new n_cultivateAndBreed();
	
//	var isView = $('#type').val() == 'check' ? true : false;
//	if (isView) {
//		setTimeout(function() {
//			$.each($("div").find(".page-content .btn-danger,.page-content .btn-info,.page-content .btn-primary,.page-content .view_nd"), function() {
//				$(this).hide();
//			});
//		}, 400);
//	}
});