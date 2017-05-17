define(function(require, exports, module) {
	var model = require("./model");
	var rm = require("./rm");
	var utils = require("utils");
	var view = Backbone.View.extend({
		el: "body",
		events: {
			"click #showSearch" : "showSearchModal",
			"click #spReset" : "formReset"
		},
		initialize: function() { /** 初始化 */
			this.model = new model();
			this.render();
		},
		render: function() { /** 页面渲染 */
			this.initDatePickers();
			this.initSearchTable();
			this.initDetailBtn();
			this.initSearchConditions();
			this.initModifyPauseOrPalyStatusDialog();
			this.initModifyDropStatusDialog();//撤销
			this.initDropSubmitForm();//撤销提交方法
		},
		formReset : function(){//表单重置
			var viewSelf = this;
			viewSelf.toDoTable.fnPageChange(0);
		},
		initDropSubmitForm : function(){//终止提交方法
			var viewSelf = this;
			var cForm = $("#comments-form");
			cForm.validate({
				rules : rm.rules,
				messages : rm.messages,
				submitHandler : function(form){
					//按钮禁用
					utils.button.ban('#submit-comments-btn');
					viewSelf.model.cancelWorkflow(cForm.serialize(), function(result){
						utils.button.reset('#submit-comments-btn');
						if(result.success){
							utils.alert.suc(result.msg);
						}else{
							utils.alert.err(result.msg);
						}
						//隐藏弹出框&重置表单
						$("#model-comments").modal('hide');
						cForm.resetForm();
						viewSelf.refreshTable();
					});
				},
				errorPlacement: function(error, element) {//自定义错误显示位置
					error.appendTo(element.parent());
				}
			});
		},
		refreshTable: function(){//重新刷新表格
			var viewSelf = this;
			//刷新表格,不会跳转到第一页
			var tableSetings = viewSelf.toDoTable.fnSettings();
			var page_length = tableSetings._iDisplayLength;//当前每页显示多少  
			var page_start = tableSetings._iDisplayStart;//当前页开始  
			viewSelf.toDoTable.fnPageChange(page_start/page_length);
			
		},
		initModifyPauseOrPalyStatusDialog : function(){//初始化暂停、继续流程方法
			var viewSelf = this;
			$(document).on("click","button[role='modifyPauseOrPlayStatus']",function(e){
				var cForm = $("#comments-form");
				//初始化数据
				viewSelf.initForm(e);
				var _opt = $("#comments-form input[name='operation']").val();
				var _type;
				if(_opt == 'pause'){
					_type = '暂停';
				}else if(_opt == 'play'){
					_type = '继续';
				}
				utils.button.confirm(e.currentTarget,function(result){
					if(result){
						viewSelf.model.pauseOrPlayTask(cForm.serialize(), function(data){
							if(data.success){
								utils.alert.suc(data.msg);
							}else{
								utils.alert.err(data.msg);
							}
							viewSelf.refreshTable();
						});
					}
				},'确定要执行【'+_type+'】操作吗？');
			});
		},
		initModifyDropStatusDialog : function(){//初始化终止流程方法
			var viewSelf = this;
			$(document).on("click","button[role='modifyDropStatus']",function(e){
				//初始化数据
				viewSelf.initForm(e);
				utils.button.confirm(e.currentTarget,function(result){
					if(result){
						$('#comments-form h4 span').text('终止流程操作');
						$('#submit-comments-btn').html("<i class='ace-icon fa fa-reply'/> 提交");
						//弹出框
						$('#model-comments').modal('show');
					}
				},'确定要执行【终止】操作吗？');
			});
		},
		initForm : function(e){
			var viewSelf = this;
			var $this = $(e.currentTarget);
			$("#comments-form input[name='workflowId']").val($this.data('workflowid'));
			$("#comments-form input[name='workflowTypeCode']").val($this.data('wfcode'));
			$("#comments-form input[name='taskStatus']").val($this.data('status'));
			$("#comments-form input[name='nodeId']").val($this.data('nodeid'));
			$("#comments-form input[name='taskId']").val($this.data('taskid'));
			$("#comments-form input[name='operation']").val($this.data('opt'));//标记目前执行的什么操作
		},
		initSearchConditions : function(){
			var viewSelf = this;
			$(document).on("click","#do_search_condition",function(){
				viewSelf.toDoTable.refreshParamCache();
				viewSelf.toDoTable.fnPageChange(0);
			});
			$("#search_todo_conditions").on("click",".lbl",function(e){
				$(this).closest(".lbl-group").find(".lbl").addClass("lbluncheck")
				.removeClass("lblchecked");
				$(this).removeClass("lbluncheck")
				.addClass("lblchecked").parent().find("input[type='radio']")[0].click();
				viewSelf.toDoTable.refreshParamCache();
				viewSelf.toDoTable.fnPageChange(0);
			});
		},
		initDatePickers :function(){
			var dateOnTop = function(){$(".datepicker").css("z-index","99999");};
			var defaultDateConf = {
				autoclose : true,
				todayHighlight : true,
				clearBtn : true,
				endDate : 'd',
				format : 'yyyy-mm-dd'
			};
			
			var initDateP = function(selector,changeCallBack,config){
				var _dateConf = config || defaultDateConf;
				$(selector).datepicker(_dateConf)
				.on("changeDate",changeCallBack)
				.on("show",dateOnTop);
			};
			
			//待办modal时间
			initDateP("#submitTimeStart",function(ev) {
				$("#submitTimeEnd").datepicker("setStartDate",ev.date?ev.date:"");
			});
			initDateP("#submitTimeEnd",function(ev) {
				$("#submitTimeStart").datepicker("setEndDate",ev.date?ev.date:"");
			});
			//审批进度查询结束时间起止
			initDateP("#taskEndDateStart",function(ev) {
				$("#taskEndDateEnd").datepicker("setStartDate",ev.date?ev.date:"");
			});
			initDateP("#taskEndDateEnd",function(ev) {
				$("#taskEndDateStart").datepicker("setEndDate",ev.date?ev.date:"");
			});
		},
		showSearchModal :function(){
			$("#search-modal").modal("show");
		},
		initSearchTable:function(){
			var viewSelf = this;
			var dataDict=[];
			dataDict[80]="待处理";
			dataDict[81]="处理中";
			dataDict[82]="已完成";
			dataDict[83]="暂停中";
			dataDict[3]="已拒绝";
			dataDict[8]="已终止";
			//R00011：一级系统管理员
			var sys_admin_level = $("#sys_adm_level").val();
			
			var toDoTable = $("#tbl_todo").dataTable({
				bLengthChange: false,
				bServerSide: true,
				bFilter: false,
		    	sAjaxSource: $$ctx + "wfmonitor/search",
		    	fnServerParams: function (aoData) {
		    		if(!this.serverParamCache){
                		this.refreshParamCache = function(){
                			this.serverParamCache = $("#search_todo_conditions").serializeArray();
                        };
                        this.refreshParamCache();
                	}
                	$(this.serverParamCache).each(function(i,d){
                		aoData.push(d);
                	});
				},
		    	aoColumns: [
		    	    //流程名称   
		    	    {mData: "workflowName"},
		    	    //任务主题
		    	    {mData: "taskSubject"},
		    	    //开始时间
		    	    {mData: "createTime"},
		    	    //结束时间
		    	    {mData: "endTime",mRender:function(data, type, rowdata){
		    	    	return data;
		    	    }},
		    	    //任务状态
		    	    {mData: "taskStatus",mRender:function(data, type, rowdata){
		    	    	if(rowdata.actionCode == '3' && data == '82'){
		    	    		return dataDict[3];//已撤销
		    	    	}
		    	    	if(rowdata.actionCode == '8' && data == '82'){
		    	    		return dataDict[8];//已终止
		    	    	}
		    	    	return dataDict[data];
		    	    }},
		    	    //当前环节名称
		    	    {mData: "stageNameCn"},
		    	    /*{mData: "taskAssigneeCn"},//任务接收人*/
		    	    //操作
		    	    {mData: null, mRender: function(data, type, rowdata) {
		    	    	var _template ;
		    	    	if(sys_admin_level == 'R00011'){//如果是一级系统管理员
		    	    		//待处理&处理中 不显示继续按钮
			    	    	if(rowdata.taskStatus == 80 || rowdata.taskStatus == 81){
			    	    		_template = $("#dt-row-operation-01").html();
			    	    	}
			    	    	//处理完成和已撤销的不显示已撤销按钮
			    	    	if(rowdata.taskStatus == 82 || (rowdata.taskStatus == 82 && rowdata.actionCode == 3)){
			    	    		_template = $("#dt-row-operation-02").html();
			    	    	}
			    	    	//暂停中 不显示暂停按钮
			    	    	if(rowdata.taskStatus == 83){
			    	    		_template = $("#dt-row-operation-03").html();
			    	    	}
		    	    	}else{//如果不是则只能查看
		    	    		_template = $("#dt-row-operation-02").html();
		    	    	}
		    	    	return Mustache.render(_template,{
		    	    		workflowId : rowdata.workflowId,//流程编号
		    	    		workflowCode : rowdata.workflowTypeCode,//流程类型。例如：易贷1003；微贷1004
		    	    		taskStatus : rowdata.taskStatus,//任务状态。例如：待处理80；
		    	    		nodeId : rowdata.nodeId,//环节ID。例如：初审100412
		    	    		taskId : rowdata.taskId,//任务ID。
		    	    	});
		    		}}
		    	]
			});
			viewSelf.toDoTable = toDoTable;
		},
		initDetailBtn :function(){
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
				var nids = [];//审批流程节点id
				nids.push('100311');//易贷流程评审岗
				nids.push('100312');//易贷流程审批岗
				nids.push('100413');//微贷流程分配岗
				nids.push('100414');//微贷流程一级审批岗
				nids.push('100416');//微贷流程二级审批岗
				//add by wangyawei 20150812 start 增加授信审批流程-特殊环节处理环节ID
				nids.push('100713');//授信审批流程任务分配岗
				nids.push('100714');//授信审批流程一级审批岗
				nids.push('100715');//授信审批流程二级审批岗
				//add by wangyawei 20150812 end
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
							if (wfDetail.taskAssigneeCn && (result.msg == '10001' || $.inArray(wfDetail.nodeId, nids) < 0)) {
								htmlContent += '经办人:' + wfDetail.taskAssigneeCn + '(' + wfDetail.taskAssignee + ')<br/><br/>';
							} 
							//如果任务状态是'已终止'显示处理人
							if(wfDetail.actionCode == '8'){
								htmlContent += '处理人:' + wfDetail.taskClaimerCn + '(' + wfDetail.taskClaimer + ')<br/><br/>';
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
		}
	});
	module.exports = view;
});