define(function(require, exports, module) {
	var view = require('./view');
	new view();
	var documentView = require('../approvedocument/view');
	var documentSet = new documentView();
	var taskStageCode = $("#taskStageCode").val();
	if($.inArray(taskStageCode,['100411','100412','100419'])>=0){
		documentSet.render();
	}else{
		documentSet.commonRender();
	}
	var phoneview = require('./phonedetail/phoneview');
	new phoneview();
});