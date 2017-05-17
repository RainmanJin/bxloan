define(function(require, exports, module) {
	var stageCode = $("#taskStageCode").val();
	
	var view = require('./view');
	new view();
	var documentView = require('../../approvedocument/view');
	var documentSet = new documentView();
	if($.inArray($("#taskStageCode").val(),['100315'])>=0){//选择分配人
		documentSet.render();
	}else{
		documentSet.commonRender();
	}
});