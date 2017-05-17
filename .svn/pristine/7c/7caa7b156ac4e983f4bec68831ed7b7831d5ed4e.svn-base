define(function(require, exports, module) {
	var view = require('./view');
	new view();
	var documentView = require('./document/view');
	var documentSet = new documentView();
	var taskStageCode = $("#taskStageCode").val();
	/**贷款审查，初审，落实贷款条件 比其他环节多了上传文档窗口，删除文档功能*/
	if($.inArray(taskStageCode,['100811','100812','100815'])>=0){
		documentSet.render();
	}else{
		documentSet.commonRender();
	}
});