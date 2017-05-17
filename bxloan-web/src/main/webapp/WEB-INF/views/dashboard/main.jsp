<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../commons/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="zh">
	<head>
		<base href="${ctx}/">
		<title>${title }</title>
		<meta charset="utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		
		<meta name="description" content="Dashboard page" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
		
		<c:import url="../../commons/pre-include.jsp" charEncoding="UTF-8" />
		
		<!-- page specific plugin styles START -->
		<!-- page specific plugin styles END -->
		
		<!-- inline styles related to this page -->
		<link rel="stylesheet" href="${ctx}/static/my/css/dashboard.css" />
		<script>var $$ctx = "${ctx}/";</script>
		<style>
		.form-horizontal .form-group {margin-left: -12px\0;margin-right: -12px\0;}
.form-group {margin-bottom: 15px\0;}
.col-sm-2 {
    width: 16.6666%\0;
}
.col-sm-4 {
    width: 33.3333%\0;
}
.form-horizontal .control-label {
    text-align: right\0;
}
.no-padding-right {
    padding-right: 0\0;
}
.col-sm-1, .col-sm-2, .col-sm-3, .col-sm-4, .col-sm-5, .col-sm-6, .col-sm-7, .col-sm-8, .col-sm-9, .col-sm-10, .col-sm-11, .col-sm-12 {
    float: left\0;
}
.col-xs-1, .col-sm-1, .col-md-1, .col-lg-1, .col-xs-2, .col-sm-2, .col-md-2, .col-lg-2, .col-xs-3, .col-sm-3, .col-md-3, .col-lg-3, .col-xs-4, .col-sm-4, .col-md-4, .col-lg-4, .col-xs-5, .col-sm-5, .col-md-5, .col-lg-5, .col-xs-6, .col-sm-6, .col-md-6, .col-lg-6, .col-xs-7, .col-sm-7, .col-md-7, .col-lg-7, .col-xs-8, .col-sm-8, .col-md-8, .col-lg-8, .col-xs-9, .col-sm-9, .col-md-9, .col-lg-9, .col-xs-10, .col-sm-10, .col-md-10, .col-lg-10, .col-xs-11, .col-sm-11, .col-md-11, .col-lg-11, .col-xs-12, .col-sm-12, .col-md-12, .col-lg-12 {
    min-height: 1px\0;
    padding-left: 12px\0;
    padding-right: 12px\0;
    position: relative\0;
}
label {
    font-size: 14px\0;
    font-weight: normal\0;
}
<%-- 查询条件css--%>
.lbl{
	display: inline;
	padding: .2em .6em .3em;
	font-size: 100%;
	line-height: 1;
	text-align: center;
	white-space: nowrap;
	vertical-align: baseline;
}
.lbluncheck{
	cursor: pointer;
}
.lblchecked{
	background-color: #5bc0de;
	display: inline;
	padding: .2em .6em .3em;
	font-size: 100%;
	font-weight: 700;
	line-height: 1;
	color: #fff;
	text-align: center;
	white-space: nowrap;
	vertical-align: baseline;
	border-radius: .25em;
}
.search_conditions b{
	font-size: 14px;;
}
		</style>
	</head>
	<body class="no-skin">
		<c:import url="../../commons/navbar.jsp" charEncoding="UTF-8" />
		
		<div class="main-container" id="main-container">
			<script type="text/javascript">
				try{ace.settings.check('main-container' , 'fixed')}catch(e){}
			</script>
		
			<c:import url="../../commons/sidebar.jsp" charEncoding="UTF-8" />
			
			<div class="main-content">
				<div class="breadcrumbs" id="breadcrumbs">
					<script type="text/javascript">
						try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
					</script>
					
					<!-- 隐藏域 -->
					<input type="hidden" id="error" value="${error }">
					<input type="hidden" id="logOrgid" value='<shiro:principal property="logOrgid" />'>
					<!-- 隐藏域  -->

					<ul class="breadcrumb">
						<li>
							<i class="ace-icon fa fa-home home-icon"></i>
							<a href="${ctx}">主页</a>
						</li>
						<li class="active">工作面板</li>
					</ul><!-- /.breadcrumb -->

				</div>

				<div class="page-content">
					
					<!-- 页面内容 -->
					<div class="row">
						<div class="col-xs-12">
							<div class="row">
								<div class="col-xs-12">
									<div class="widget-box transparent" id="recent-box">
										<div class="widget-header">
											<div class="widget-toolbar no-border">
												<ul class="nav nav-tabs" id="recent-tab">
													<li class="active">
														<a data-toggle="tab" href="#task-todo">待办任务</a>
													</li>
													<li>
														<a data-toggle="tab" href="#task-done">已办任务</a>
													</li>
													<li><a data-toggle="tab" href="#msg_tab">消息</a></li>
												</ul>
											</div>
										</div>
										<div class="widget-body">
											<div class="widget-main padding-4">
												<div class="tab-content padding-8">
													<%--待办列表 --%>
													<div id="task-todo" class="tab-pane active">
														
														<form id="todo_search_conditions" class="search_conditions">
															<div class="row">
															<div class="col-md-7 form-group lbl-group" >
																<b class="">流程类型:</b>
																<span>
																<input type="radio" class=" hidden"
																	value="" checked="checked" name="workFlowCode" />
																<label class="lbl lblchecked" >全部
																</label>
																</span>
																
																<span>
																<label class="lbl lbluncheck" >易贷审批流程
																</label>
																<input type="radio" class="hidden"
																	value="1003" name="workFlowCode" />
																</span>
																
																<span>
																	<label class="lbl lbluncheck">微贷审批流程
																	</label>
																	<input type="radio" class="hidden"
																		value="1004" name="workFlowCode" />
																</span>
																<span>
																	<label class="lbl lbluncheck">授信审批流程
																	</label>
																	<input type="radio" class="hidden"
																		value="1007" name="workFlowCode" />
																</span>
																<span>
																	<label class="lbl lbluncheck">授信借款流程
																	</label>
																	<input type="radio" class="hidden"
																		value="1008" name="workFlowCode" />
																</span>
															</div>
															</div>
															
															<div class="col-md-12"></div>
															
															<div class="row ">
															<div class="col-md-3 form-group hidden">
														    	<b class="">提&nbsp;交&nbsp;人&nbsp;:</b>
															  	<input type="text" id="taskDesignator-todo" 
															  		name="taskDesignator" 
															  		class="input-md" />
															</div>
															
															<div class="col-xs-3 form-group">
																<div class="input-group">
																	<span class="input-group-addon">任务主题</span>
																	<span class="block input-icon input-icon-right">
																		<input class="form-control col-sm-5 input-small" name="taskSubject" placeholder="支持模糊查询" type="text" />
																		<i class="ace-icon fa fa-times-circle clean-input" style="display: none;"></i>
																	</span>
																</div>
															</div>
															
															<div class="col-xs-4 form-group">
																<div class="input-group">
															    	<span class="input-group-addon">提交时间：</span>
																	<span class="block input-icon input-icon-right">
																		<div class="input-group">
																			<input type="text" class="form-control col-sm-5 input-small date-picker" id=taskCreateTimeStart-todo name="taskCreateTimeStart" placeholder="开始时间" data-date-format="yyyy-mm-dd"/>
																			<span class="input-group-addon">至</span>
																			<input type="text" class="form-control col-sm-5 input-small date-picker" id="taskCreateTimeEnd-todo" name="taskCreateTimeEnd" placeholder="结束时间" data-date-format="yyyy-mm-dd"/>
																		</div>
																	</span>
															  	 </div>
															</div>
															
															<div class="col-md-1 form-group">
																<button id="do_todo_search_condition" type="button"
																class="btn btn-sm btn-purple">
																<i class="ace-icon fa fa-search"></i> 查询</button>
															</div>
															</div>
														</form>
														<table id="tbl_todo" class="table table-striped table-hover">
															<thead>
																<tr>
																	<th>流程类型</th>
																	<th>流程状态</th>
																	<th width="30%">任务主题</th>
																	<th>所处环节</th>
																	<th>分配人</th>
																	<th>分配时间</th>
																	<th>信贷员</th>
																	<th>权限机构</th>
																	<th>操作</th>
																</tr>
															</thead>
															<tbody>
															</tbody>
														</table>
													</div>
													<%--待办列表END --%>
													
													<%--已办列表 --%>
													<div id="task-done" class="tab-pane">
														<form id="done_search_conditions" class="search_conditions">
														
															<div class="row hidden">
															<div class="col-xs-4 form-group">
																<b class="">提交人:</b>
															  	<input type="text" id="taskDesignator-todo" 
															  		name="taskDesignator" 
															  		class="input-md" />
															</div>
															</div>
														
															<div class="row">
															<div class="col-xs-7 form-group lbl-group" >
																<b class="">流程类型:</b>
																
																<span>
																	<label class="lbl lblchecked" >全部
																	</label>
																	<input type="radio" class=" hidden"
																	value="" checked="checked" name="workFlowCode" />
																</span>
																
																<span>
																	<label class="lbl lbluncheck" >易贷审批流程
																	</label>
																	<input type="radio" class="hidden"
																	value="1003" name="workFlowCode" />
																</span>
																<span>
																	<label class="lbl lbluncheck">微贷审批流程
																	</label>
																	<input type="radio" class="hidden"
																	value="1004" name="workFlowCode" />
																</span>
																<span>
																	<label class="lbl lbluncheck" >授信审批流程
																	</label>
																	<input type="radio" class="hidden"
																	value="1007" name="workFlowCode" />
																</span>
																<span>
																	<label class="lbl lbluncheck">授信借款流程
																	</label>
																	<input type="radio" class="hidden"
																	value="1008" name="workFlowCode" />
																</span>
															</div>
															</div>

															<div class="row">
															
															<div class="col-xs-3 form-group">
																<div class="input-group">
																	<span class="input-group-addon">任务主题</span>
																	<span class="block input-icon input-icon-right">
																		<input class="form-control col-sm-5 input-small" name="taskSubject" placeholder="支持模糊查询" type="text" />
																		<i class="ace-icon fa fa-times-circle clean-input" style="display: none;"></i>
																	</span>
																</div>
															</div>
															
															<div class="col-xs-4 form-group">
																<div class="input-group">
															    	<span class="input-group-addon">处理开始时间：</span>
																	<span class="block input-icon input-icon-right">
																		<div class="input-group">
																			<input type="text" class="form-control col-sm-5 input-small date-picker" 
																			id="taskSubmitTimeStart-done" 
																			name="taskSubmitTimeStart" 
																			placeholder="开始时间" 
																			data-date-format="yyyy-mm-dd"/>
																			
																			<span class="input-group-addon">至</span>
																			
																			<input type="text" class="form-control col-sm-5 input-small date-picker" 
																			id="taskSubmitTimeEnd-done" 
																			name="taskSubmitTimeEnd" 
																			placeholder="结束时间" 
																			data-date-format="yyyy-mm-dd"/>
																		</div>
																	</span>
															  	 </div>
															</div>
															
															<div class="col-xs-4 form-group">
																<div class="input-group">
															    	<span class="input-group-addon">处理结束时间：</span>
																	<span class="block input-icon input-icon-right">
																		<div class="input-group">
																			<input type="text" class="form-control col-sm-5 input-small date-picker" 
																			id="taskCreateTimeStart-done" 
																			name="taskCreateTimeStart" 
																			placeholder="开始时间" 
																			data-date-format="yyyy-mm-dd"/>
																			
																			<span class="input-group-addon">至</span>
																			
																			<input type="text" class="form-control col-sm-5 input-small date-picker" 
																			id="taskCreateTimeEnd-done" 
																			name="taskCreateTimeEnd" 
																			placeholder="结束时间" 
																			data-date-format="yyyy-mm-dd"/>
																		</div>
																	</span>
															  	 </div>
															</div>
															
															<div class="col-md-1 form-group">
																<button id="do_done_search_condition" 
																type="button"
																class="btn btn-sm btn-purple">
																<i class="ace-icon fa fa-search"></i> 查询</button>
															</div>
															</div>
															
														</form>
														<table id="tbl_done" class="table table-striped table-hover">
															<thead>
																<tr>
																	<th>流程类型</th>
																	<th>流程状态</th>
																	<th>任务主题</th>
																	<th>所处环节</th>
																	<th>处理人</th>
																	<th>处理开始时间</th>
																	<th>处理结束时间</th>
																	<th>操作</th>
																</tr>
															</thead>
															<tbody>
															</tbody>
														</table>
													</div>
													<%--已办列表 END--%>
													
													<%-- 消息 --%>
													<div id="msg_tab" class="tab-pane">
														<table id="msg_table" class="table table-striped table-hover">
															<thead>
																<tr>
																	<th>提示信息类型</th>
																	<th>消息ID</th>
																	<th>消息条数</th>
																	<th>查看详情</th>
																</tr>
															</thead>
															<tbody>
															</tbody>
														</table>
														<div class="hr hr-double hr8"></div>
													</div>
													<%-- 消息END --%>
													
													<!-- /.#member-tab -->
												</div>
											</div><!-- /.widget-main -->
										</div><!-- /.widget-body -->
									</div><!-- /.widget-box -->
								</div>
							</div>
						</div><!-- /.col -->
						
						
					</div><!-- /.row -->
					
				</div><!-- /.page-content -->
			</div><!-- /.main-content -->
			
			<%-- 消息详细弹窗 --%>
			<div id="model-message-detail"  class="modal " tabindex="-1">
					<div class="modal-dialog " style="width:70%;margin:30px auto\0;" >
					
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close close-message-detail" data-dismiss="modal">&times;</button>
								<h4 class="blue bigger">
									消息详细内容
									<i class='ace-icon fa fa-search'></i> 
								</h4>
							</div>
							<div class="modal-body" >
								<div class="row">
									<div class="col-xs-12">
									<table  id="message_detail_tbl"
										class="table table-striped table-bordered table-hover ">
										<thead>
											<tr >
												<th>发送人</th>
												<th>发送时间</th>
												<th>消息内容</th>
												<th>操作</th>
											</tr>
										</thead>
									</table>
									</div>
								</div>
							</div>
							<div class="modal-footer">
								<button id="close_msg_detail"
								 class="btn btn-sm btn-default close-message-detail" data-dismiss="modal">
									<i class="ace-icon fa fa-times"></i> 取消
								</button>
							</div>
						</div>
						
					</div>
				</div>
			<%-- 消息详细弹窗 END--%>
			<%-- 流程详细弹窗 --%>
		<div id="detail-modal" class="modal" tabindex="-1">
			<div class="modal-dialog" style="width:50%">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="blue bigger">
							<i class='ace-icon fa fa-search'></i>流程详细信息
						</h4>
					</div>
					<div class="modal-body">
					
						<div class="row">
						
							<div class="col-xs-12 col-sm-10 col-sm-offset-1">
							
							
							<div class="timeline-container">
								<div class="timeline-label">
									<span class="label label-primary arrowed-in-right label-lg">
									最新状态
									</span>
								</div>
								
								<div class="timeline-items" id="wfDetailWarp">
								
		
								</div><!-- /.timeline-items -->
							
							</div><!-- /.timeline-container -->
							
							</div>
						
						
						</div>
					
					</div>
					<div class="modal-footer">
						<button class="btn btn-sm btn-default" data-dismiss="modal">
							<i class="ace-icon fa fa-times"></i> 取消
						</button>
					</div>
				</div>
			</div>
		</div>
		<%-- 流程详细弹窗END --%>
			
			<c:import url="../../commons/footer.jsp" charEncoding="UTF-8" />
			
		</div><!-- /.main-container -->

		<c:import url="../../commons/post-include.jsp" charEncoding="UTF-8" />
		
		<!-- page specific plugin scripts START -->
		<!--[if lte IE 8]>
		  <script src="static/assets/js/excanvas.min.js"></script>
		<![endif]-->
		<!-- page specific plugin scripts END -->

		<!-- inline scripts related to this page -->
		<script>
			seajs.use('${ctx}/static/my/js/dashboard/main/main');
		</script>
	</body>

</html>