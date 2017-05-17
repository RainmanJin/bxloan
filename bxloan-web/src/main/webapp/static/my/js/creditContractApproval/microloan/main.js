define(function(require, exports, module) {
	var view = require('./view');
	new view();
	var documentView = require('./document/view');
	var documentSet = new documentView();
	var taskStageCode = $("#taskStageCode").val();
	/**贷款审查，初审，签订合同环节比其他环节多了上传文档窗口，删除文档功能*/
	if($.inArray(taskStageCode,['100711','100712','100718'])>=0){
		documentSet.render();
	}else{
		documentSet.commonRender();
	}
});