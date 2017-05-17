define(function(require, exports, module) {
	var model = require("./model");
	var utils = require("utils");
	var view = Backbone.View.extend({
		el: "body",
//		events: {
//		},
		initialize: function() { /** 初始化 */
			this.model = new model();
			this.render();
		},
		render: function() { /** 页面渲染 */
			/**初始化待办列表*/
			this.initTodoTable();
			/**初始化已办列表*/
			this.initDoneTable();
			/** 初始化消息列表 */
			this.initMsgTable();
			/**初始化日历控件*/
			this.initDataPickers();
			/**初始化消息详细内容*/
			this.initMsgDetail();
			/**初始化待办列表操作按钮*/
			this.initEditTodo();
			/**错误信息提示*/
			this.haveError();
			/**初始化已办列表操作按钮*/
			this.initDetailDone();
			/**初始化待办查询*/
			this.initTodoSearch();
			/**初始化已办列表查询*/
			this.initDoneSearch();
			/**初始化tooltip插件*/
			this.initToolTips();
			/**初始化查看流程状态*/
			this.initDetailWorkBtn();
			/**初始化任务主题控件的效果*/
			this.initTaskSubject();
		},
		/**初始化tooltip插件*/
		initToolTips : function(){
			$(document).on("mouseover","button[data-rel='tooltip']",function(){
				$(this).tooltip({html:true});
				$(this).tooltip("show");
			});
		},
		/**错误信息提示*/
		haveError: function() {
			var error = $('#error').val();
			if(error == 'disposeTask') {
				utils.alert.err("任务处理失败，请联系管理员！");
        		history.pushState({},"",$$ctx+"dashboard");
			}else if(error == 'buttons') {
				utils.alert.err("获取按钮失败，请联系管理员！");
        		history.pushState({},"",$$ctx+"dashboard");
			}else if(error.match(/[^\u4e00-\u9fa5]/)){
				utils.alert.err(error,function() {
					$('#error').val('');
				});
			}
		},
		//初始化日历
		initDataPickers: function() {

			//将datepicker控件放在最顶层
			var dateOnTop = function(){$(".datepicker").css("z-index","99999");};
			var defaultDateConf = {
				autoclose : true,
				todayHighlight : true,
				clearBtn : true,
				endDate : 'd'
			};
			
			var initDateP = function(selector,changeCallBack,config){
				var _dateConf = config || defaultDateConf;
				$(selector).datepicker(_dateConf)
				.on("changeDate",changeCallBack)
				.on("show",dateOnTop);
			};
			
			//待办modal时间
			initDateP("#taskCreateTimeStart-todo",function(ev) {
				$("#taskCreateTimeEnd-todo").datepicker("setStartDate",ev.date?ev.date:"");
			});
			initDateP("#taskCreateTimeEnd-todo",function(ev) {
				$("#taskCreateTimeStart-todo").datepicker("setEndDate", ev.date?ev.date:"");
			});
			
			//已办modal时间
			initDateP("#taskCreateTimeStart-done",function(ev) {
				$("#taskCreateTimeEnd-done").datepicker("setStartDate",ev.date?ev.date:"");
			});
			initDateP("#taskCreateTimeEnd-done",function(ev) {
				$("#taskCreateTimeStart-done").datepicker("setEndDate", ev.date?ev.date:"");
			});
			initDateP("#taskSubmitTimeStart-done",function(ev) {
				$("#taskSubmitTimeEnd-done").datepicker("setStartDate",ev.date?ev.date:"");
			});
			initDateP("#taskSubmitTimeEnd-done",function(ev) {
				$("#taskSubmitTimeStart-done").datepicker("setEndDate", ev.date?ev.date:"");
			});
			
		},
		/**初始化待办查询*/
		initTodoSearch : function(){
			var viewSelf = this;
			$(document).on("click","#do_todo_search_condition",function(){
				viewSelf.toDoTable.refreshParamCache();
				viewSelf.toDoTable.fnPageChange(0);
			});
			$(document).on("click","#todo_search_conditions .lbl",function(e){
				$(this).closest(".lbl-group").find(".lbl").addClass("lbluncheck")
				.removeClass("lblchecked");
				$(this).removeClass("lbluncheck")
				.addClass("lblchecked").parent().find("input[type='radio']")[0].click();
				viewSelf.toDoTable.refreshParamCache();
				viewSelf.toDoTable.fnPageChange(0);
			});
		},
		/**初始化待办列表*/
		initTodoTable:function(){   
			var viewSelf = this;
			var toDoTable = $("#tbl_todo").dataTable({
				bLengthChange: false,
				bServerSide: true,
				bFilter: false,
		    	sAjaxSource: $$ctx + "dashboard/findTodoListBySearch",
		    	fnServerParams: function (aoData) {   //传递的参数
		    		if(!this.serverParamCache){
                		this.refreshParamCache = function(){
                			this.serverParamCache = $("#todo_search_conditions").serializeArray();
                        };
                        this.refreshParamCache();
                	}
		    		
                	$(this.serverParamCache).each(function(i,d){
                		aoData.push(d);
                	});
				},
		    	aoColumns: [
    	            {mData: "workflowName"},
    	            {mData: "taskStatus", mRender: function(data,type,rowdata){
    	            	if(!data){
    	            		return '';
    	            	}
    	            	if(data == 80){//待处理
    	            		return '待处理';
    	            	}
    	            	if(data == 81){//处理中
    	            		return '处理中';
    	            	}
    	            	if(data == 82){//处理完成
    	            		return '处理完成';
    	            	}
    	            	if(data == 5){//暂停中
    	            		return '暂停中';
    	            	}
    	            	if(data == 3){//已撤销
    	            		return '已撤销';
    	            	}
    	            }},
    	            {mData: "taskSubject"},
		    	    {mData: "currentName"},
		    	    {mData: "taskCreatorName"},
		    	    {mData: "taskCreateTime"},
		    	    {mData: "workflowCreatorName"},
		    	    {mData: "orgName"},
		    	    {mData: null, mRender: function(data, type, rowdata) {     //操作按钮
		    	    	var logOrgid = $("#logOrgid").val();
		    	    	var disabled = "disabled='disabled'";
		    	    	if(rowdata.orgId == logOrgid) {
		    	    		disabled = "";
		    	    	}
						var operation = 
						"<div class='btn-group'>"+
							"<button data-wfcode='" + rowdata.workflowCode + "' " +
								"data-wfid='" + rowdata.workflowId + "' " + 
								"data-taskid='" + rowdata.taskId + "' " + 
								"data-nodeid='" + rowdata.nodeId + "' " + 
								"data-task-status='" + rowdata.taskStatus + "' " + 
								"class='btn btn-xs btn-info' role='showTaskTodo'" +
								"title='打开'" + 
								disabled + 
								">" +
							"<i class='ace-icon fa fa-edit'></i>" +
							"</button>" +
						"</div>";
		    	    	return operation;
		    		}}
		    	]
			});
			viewSelf.toDoTable = toDoTable;
			
		},
		/**初始化已办列表查询*/
		initDoneSearch : function(){
			var viewSelf = this;
			$(document).on("click","#do_done_search_condition",function(){
				viewSelf.doneTable.refreshParamCache();
				viewSelf.doneTable.fnPageChange(0);
			});
			
			$(document).on("click","#done_search_conditions .lbl",function(e){
				$(this).closest(".lbl-group").find(".lbl").addClass("lbluncheck")
				.removeClass("lblchecked");
				$(this).removeClass("lbluncheck")
				.addClass("lblchecked").parent().find("input[type='radio']")[0].click();
				viewSelf.doneTable.refreshParamCache();
				viewSelf.doneTable.fnPageChange(0);
			});
		},
		/**初始化已办列表*/
		initDoneTable:function(){
			var viewSelf = this;
			var doneTable = $("#tbl_done").dataTable({
				bLengthChange: false,
				bServerSide: true,
				bFilter: false,
		    	sAjaxSource: $$ctx + "dashboard/findDoneListBySearch",
		    	fnServerParams: function (aoData) {
		    		if(!this.serverParamCache){
                		this.refreshParamCache = function(){
                			this.serverParamCache = $("#done_search_conditions").serializeArray();
                        };
                        this.refreshParamCache();
                	}
		    		
                	$(this.serverParamCache).each(function(i,d){
                		aoData.push(d);
                	});
				},
		    	aoColumns: [
	            	{mData: "workflowName"},
	            	{mData: "taskStatus", mRender: function(data,type,rowdata){
    	            	if(!data){
    	            		return '';
    	            	}
    	            	if(data == 80){//待处理
    	            		return '待处理';
    	            	}
    	            	if(data == 81){//处理中
    	            		return '处理中';
    	            	}
    	            	if(data == 82){//处理完成
    	            		return '处理完成';
    	            	}
    	            	if(data == 83){//暂停中
    	            		return '暂停中';
    	            	}
    	            }},
    	            {mData: "taskSubject"},
					/*{mData: "stageName"},*/
    	            {mData: "currentName"},
    	            {mData: "currentAssigneerName", mRender: function(data, type, rowdata) {
		    	    	var nodeId = rowdata.currentStage;
		    	    	if ($("#logOrgid").val() != '10001' && (nodeId == '100413' || nodeId == '100414' || nodeId == '100416' || nodeId == '100311' || nodeId == '100312')) {
		    	    		return "集中审批";
		    	    	} else {
		    	    		return data;
		    	    	}
		    	    }},
		    	    {mData: "taskAssignTime"},
		    	    {mData: "taskConfirmTime"},
		    	    {mData: null, mRender: function(data, type, rowdata) {
						var operation = 
						"<div class='btn-group'>"+
						
							"<button data-wfcode='" + rowdata.workflowCode + "' " +
							"data-wfid='" + rowdata.workflowId + "' " + 
							"class='btn btn-xs btn-info' role='showTaskDone' title='查看内容'><i class='ace-icon fa fa-eye'></i></button>" +
						
							"<button data-workflowId='" + rowdata.workflowId + "' " + 
							"data-status='" + rowdata.taskStatus + "' " + 
							"class='btn btn-xs btn-yellow' role='showWorkDetail' " +
							"data-toggle='tooltip' data-placement='bottom' title='查看流程状态'>" +
							"<i class='ace-icon fa fa-eye'></i></button>" + 
							
						"</div>";
		    	    	return operation;
		    		}}
				]
			});
			viewSelf.doneTable = doneTable;
		},
		/**初始化查看流程状态*/
		initDetailWorkBtn :function(){
			var _view = this;
			var dataDict=[];
			dataDict[80]="待处理";
			dataDict[81]="处理中";
			dataDict[82]="已完成";
			dataDict[83]="暂停中";
			$(document).on("click","button[role='showWorkDetail']",function(e){
				$("#wfDetailWarp").html("");
				var workflowId = $(this).data("workflowid");
				var workStatus = $(this).data("status");
				_view.model.fetchDetail({workflowId:workflowId},function(result){
					if(result.success){
						for ( var index in result.data) {
							
							wfDetail = result.data[index];
							var htmlContent = "";
							var isDone = wfDetail.taskStatus=='82';//已处理完成
							htmlContent += '<div class="timeline-item clearfix\">';
							htmlContent += '	<div class="timeline-info">';
							
							if(isDone){
								if(index ==0 && workStatus=='82'){
									htmlContent += '		<i class="timeline-indicator ace-icon fa fa-stop btn btn-purple no-hover"></i>';
								}else{
									htmlContent += '		<i class="timeline-indicator ace-icon fa fa-check-square-o btn btn-info no-hover"></i>';
								}
							}else{
								htmlContent += '		<i class="timeline-indicator ace-icon fa fa-hand-o-right btn btn-warning no-hover"></i>';
							}
							
							htmlContent += '	</div>';
							
							if(isDone){
								if(index ==0 && workStatus=='82'){
									htmlContent += '	<div class="widget-box widget-color-purple">';
								}else{
									htmlContent += '	<div class="widget-box transparent ">';
								}
							}else{
								htmlContent += '	<div class="widget-box widget-color-orange ">';
							}
							
							htmlContent += '		<div class="widget-header widget-header-small">';
							htmlContent += '			<h5 class="widget-title smaller">';
							
							if(isDone){
								if(index ==0 && workStatus=='82'){
									htmlContent += '				<span class="">'+ wfDetail.stageNameCn +'(流程已结束)</span>';
								}else{
									htmlContent += '				<span class="">'+ wfDetail.stageNameCn +'</span>';
								}
								
							}else{
								htmlContent += '				<span class="">'+ wfDetail.stageNameCn +'('+dataDict[wfDetail.taskStatus]+')</span>';
							}
							
							htmlContent += '			</h5>';
	
							htmlContent += '			<span class="widget-toolbar no-border">';
							htmlContent += '				<i class="ace-icon fa fa-clock-o bigger-110"></i>';
							htmlContent += wfDetail.createTime ;
							htmlContent += '			</span>';
	
							htmlContent += '		</div>';
	
							htmlContent += '		<div class="widget-body">';
							htmlContent += '			<div class="widget-main" style="overflow:hidden;word-warp:break-word;word-break:break-all;">';
							if(wfDetail.actionNameCn){
								htmlContent += '操作:' + wfDetail.actionNameCn + '<br/><br/>';
							}
							/**任务分配，一级审批，二级审批环节不显示经办人*/
							if (result.msg != '10001' && (wfDetail.nodeId == '100311' || wfDetail.nodeId == '100312' 
								|| wfDetail.nodeId == '100413' || wfDetail.nodeId == '100414' || wfDetail.nodeId == '100416'
									|| wfDetail.nodeId == '100713' || wfDetail.nodeId == '100714' || wfDetail.nodeId == '100715')) {
								
							} else {
								if(wfDetail.taskAssigneeCn){
									htmlContent += '经办人:' + wfDetail.taskAssigneeCn + '(' + wfDetail.taskAssignee + ')<br/><br/>';
								}
								//如果任务状态是'已终止'显示处理人
								if(wfDetail.actionCode == '8'){
									htmlContent += '处理人:' + wfDetail.taskClaimerCn + '(' + wfDetail.taskClaimer + ')<br/><br/>';
								}
							}
							htmlContent += '审批意见:' + (wfDetail.comments||'无审批意见');				
							htmlContent += '			</div>';
							htmlContent += '		</div>';
							htmlContent += '	</div>';
							htmlContent += '</div>';
							$(htmlContent).appendTo($("#wfDetailWarp"));
						}
						$("#detail-modal").modal("show");
					}else{
						bootbox.alert("查看详细失败请稍后重试");
					}
					
				});
			});
		},
		/** 初始化消息列表 */
		initMsgTable: function() { 
			var viewSelf = this;
			var msgTable = $("#msg_table").dataTable({
				bLengthChange: false,
				bFilter: false,
				sAjaxSource: $$ctx + "dashboard/findMessageList",
				aoColumns: [
		    	    null,null,null,
		    	    {mRender:function(data, type, rowdata){
		    	    	var operation = 
		    	    		"<div class='btn-group'>" +
			    				"<button role='showMsg' data-id='" + rowdata[1] + "' class='btn btn-xs btn-info'><i class='ace-icon fa fa-edit'></i></button>"
			    			"</div>";
		    	    	return operation;
		    	    }}
		    	],
				fnInitComplete: function(oSettings, json) {
					msgTable.fnSetColumnVis(1, false);
				}
			});
			viewSelf.msgTable = msgTable;			
		},
		/**初始化消息详细内容*/
		initMsgDetail : function () {
			var viewSelf = this;
			$(document).on("click", "button[role='showMsg']", function(e) { // 查看的click事件
				var $this = $(this);
				var idVal=$this.data("id");
				var msgDetailTbl = $("#message_detail_tbl").dataTable({
					bServerSide: true,
					bFilter: false,
					sAjaxSource: $$ctx + "dashboard/findOneMessageList?id="+idVal,
					aoColumns:[
						{mData: "senderName"},
						{mData: "sendTime"},
						{mData: "content"},
						{mData: "operation" ,mRender:function(data, type, rowdata){  
			    	    	var operation = 
			    	    		"<div class='btn-group'>" +
				    				"<button role='doReadMsg' data-id='" + rowdata.id + "' class='btn btn-xs btn-info' " +
				    				" data-rel='tooltip' data-original-title='<div style=\"white-space:nowrap;\">标记已读</div>' data-placement='top' " +
				    				"><i class='ace-icon fa fa-check'></i></button>"
				    			"</div>";
			    	    	return operation;
			    	    }}
				    ],
					fnInitComplete: function(oSettings, json) {
					},
					search: null
				});
				viewSelf.msgDetailTbl = msgDetailTbl;
				$("#model-message-detail").modal("show");
			});
			/**绑定标记已读事件*/
			$(document).on("click","button[role='doReadMsg']",function(){
				var $this = $(this);
				viewSelf.model.doReadMsg({msgId:$this.data("id")},function(result){
					if(result.success){
						viewSelf.msgDetailTbl.fnDraw();
						viewSelf.msgTable.fnDraw();
					}else{
						utils.alert.err("操作失败请稍后重试");
					}
				});
			});			
			/**绑定叉号和取消事件*/
			$(document).on("click",".close-message-detail",function(){
				if($("#message_detail_tbl").dataTable()){
					$("#message_detail_tbl").dataTable().fnDestroy();
				}
				$("#message_detail_tbl").attr("style","");//destroy会把table的 width改成固定值
			});
			
		},
		/**初始化待办列表操作按钮*/
		initEditTodo : function(){    
			$(document).on("click", "button[role='showTaskTodo']", function(e) { // 动态绑定所有查看按钮的click事件
				var $this = $(this);
				var taskStatus = $this.data("task-status");//任务状态
				if(taskStatus == '5'){//如果任务状态是暂停中，不能进行【打开】操作
					utils.alert.warn('该待办处于暂停状态，不能打开！');
					return ;
				}
				var wfTypecode = $this.data("wfcode")+"";
				var jumpLocation = [];
				jumpLocation.push($$ctx);
				jumpLocation.push('approval/todoOperation');
				//Comment by wangyawei 20150720 start 打开流程修改为统一入口
				/*if(wfTypecode=="1003") {
				}else if(wfTypecode== "1004") {
					jumpLocation.push('wdapproval/todoOperation');
				}*/
				//Comment by wangyawei 20150720 end
				jumpLocation.push("/"+$this.data("wfcode"));
				jumpLocation.push("/"+$this.data("wfid"));
				jumpLocation.push("/"+$this.data("taskid"));
				jumpLocation.push("/"+$this.data("nodeid"));
				jumpLocation.push("/"+$this.data("task-status"));
				var url = jumpLocation.join('');
				self.location.href = url;
			});
		},
		/**初始化已办列表操作按钮*/
		initDetailDone : function(){
			$(document).on("click", "button[role='showTaskDone']", function(e) { // 查看
				var $this = $(this);
				var wfcode = $this.data("wfcode");
				if(wfcode == '1007'){
					var jumpLocation = $$ctx + 'bizCreditApplication/checkApplication/' + $this.data("wfid");
				}else if(wfcode == '1008'){
					var jumpLocation = $$ctx + 'underCreditContractMng/checkApplication/' + $this.data("wfid");
				}else{
					var jumpLocation=$$ctx + 'bizapply/checkApplication/' + $this.data("wfid")+"/taskDone";
				}
				self.location.href = jumpLocation;
			});  
		},
		/**初始化任务主题控件*/
		initTaskSubject : function() {
			$("input[name=taskSubject]").bind("keyup", function(e) {  //绑定键盘输入判断输入框中小叉号的显示
				var $this = $(this);
				var _icon = $this.next();
				$this.val() == "" ? _icon.hide() : _icon.show();
			});
			$(".clean-input").bind("click", function(e) {    //点击叉号清除输入框中数据并隐藏叉号
				var $this = $(this);
				$this.prev().val("");
				$this.hide();
			});
		}
	});
	module.exports = view;
});