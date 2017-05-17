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
					<!-- 隐藏域  -->

					<ul class="breadcrumb">
						<li>
							<a href="${ctx}">主页 </a>
						</li>
						<li class="active" >
							统计查询
						</li>
						<li class="active">审批进度查询</li>
					</ul>
					
					<!-- /.breadcrumb -->
				</div>

				<div class="page-content">

					<div class="page-header">
						<h1>
							审批进度查询 <small> <i class="ace-icon fa fa-angle-double-right"></i>
								审批进度列表
							</small>
						</h1>
					</div> 
				
					<!-- 页面内容 -->
					
					<style>
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
						/* .search_conditions .form-group{
							border-bottom: 1px dashed #CCCCCC;
							min-height: 35px;
							padding-bottom: 10px;
						} */
					</style>
						<!-- start -->
					<div id="model-comments" class="modal fade bs-example-modal-sm" tabindex="-1" data-backdrop="static">
                      <div class="modal-dialog">
                        <div class="modal-content">
                          <form id="comments-form" class="form-horizontal" role="form" onsubmit="return false;">
                            <input type="hidden" name="workflowId"/>
                            <input type="hidden" name="workflowTypeCode"/>
                            <input type="hidden" name="taskStatus"/>
                            <input type="hidden" name="nodeId"/>
                            <input type="hidden" name="taskId"/>
                            <input type="hidden" name="taskStageCode"/>
                            <input type ="text" name="operation" style="display:none;">
                            <div class="modal-header">
                              <button type="button" class="close" data-dismiss="modal">
                                &times;
                              </button>
                              <h4 class="blue bigger">
                                <i class='ace-icon fa fa-comment'></i>
                                <span></span>
                              </h4>
                            </div>
                            <div class="modal-body">
                              <div class="row">
                                <div class="col-xs-12">
                                  <div class="form-group">
                                    <div class="col-xs-7">
                                    	<textarea class="form-control required" rows="3" id="comments" name="comments" placeholder="请输入意见..." 
                                    			style="width:556px;min-width:556px;max-width: 556px;min-height: 87px;"></textarea>
                                    </div>
                                  </div>
                                </div>
                              </div>
                            </div>
                            <div class="modal-footer">
                              <button class="btn btn-sm btn-info" id="submit-comments-btn" type="submit" data-loading-text="正在保存中...">
                                <i class="ace-icon fa fa-save"></i>确定
                              </button>
                              <button class="btn btn-sm btn-default" data-dismiss="modal">
                                <i class="ace-icon fa fa-times"></i>取消
                              </button>
                            </div>
                          </form>
                        </div>
                      </div>
                    </div>
					<!-- end -->
					<div class="row">
						<!-- 表格 -->
						<input type="hidden" id="sys_adm_level" value="${sys_adm_level}"/>
						<form id="search_todo_conditions" class="search_conditions">
							<div class="col-md-7 form-group lbl-group" >
								<b class="">任务状态：</b>
								<span>
									<label class="lbl lblchecked" >全部</label>
									<input type="radio" class=" hidden" value="" checked="checked" name="processStatus" />
								</span>
								
								<span>
									<label class="lbl lbluncheck" >待处理</label>
									<input type="radio" class="hidden" value="80" name="processStatus" />
								</span>
								
								<span>
									<label class="lbl lbluncheck">处理中</label>
									<input type="radio" class="hidden" value="81" name="processStatus" />
								</span>
								
								<span>
									<label class="lbl lbluncheck">已完成</label>
									<input type="radio" class="hidden" value="82" name="processStatus" />
								</span>
								
								<span>
									<label class="lbl lbluncheck">暂停中</label>
									<input type="radio" class="hidden" value="83" name="processStatus" />
								</span>
								
								<span>
									<label class="lbl lbluncheck">已拒绝</label>
									<input type="radio" class="hidden" value="3" name="processStatus" />
								</span>
								<span>
									<label class="lbl lbluncheck">已终止</label>
									<input type="radio" class="hidden" value="8" name="processStatus" />
								</span>
							</div>
							
							<div class="col-md-7 form-group lbl-group" >
								<b class="">流程类型：</b>
								<span>
									<label class="lbl lblchecked" >全部</label>
									<input type="radio" class=" hidden" value="" checked="checked" name="processType" />
								</span>
							
								<span>
									<label class="lbl lbluncheck" >易贷审批流程</label>
									<input type="radio" class="hidden" value="1003" name="processType" />
								</span>								
								
								<span>
									<label class="lbl lbluncheck">微贷审批流程</label>
									<input type="radio" class="hidden" value="1004" name="processType" />
								</span>
								
								<span>
									<label class="lbl lbluncheck">授信审批流程</label>
									<input type="radio" class="hidden" value="1007" name="processType" />
								</span>
								
								<span>
									<label class="lbl lbluncheck">授信借款流程</label>
									<input type="radio" class="hidden" value="1008" name="processType" />
								</span>
							</div>
							<div class="col-md-12"></div>
							
							<div class="col-xs-2 form-group">
								<div class="input-group">
							    	<span class="input-group-addon">任务主题：</span>
								  	<input name="taskSubject" type="text" class="form-control col-sm-5 input-small" placeholder="支持模糊查询">
							  	</div>
							</div>
							
							<div class="col-xs-3 form-group">
								<div class="input-group">
							    	<span class="input-group-addon">开始时间：</span>
									<span class="block input-icon input-icon-right">
										<div class="input-group">
											<input type="text" class="form-control col-sm-5 input-small" id="submitTimeStart" name="submitTimeStart" placeholder="起" data-date-format="yyyy-mm-dd">
											<span class="input-group-addon">至</span>
											<input type="text" class="form-control col-sm-5 input-small" id="submitTimeEnd" name="submitTimeEnd" placeholder="止" data-date-format="yyyy-mm-dd">
										</div>
									</span>
							  	 </div>
							</div>
							<div class="col-xs-3 form-group">
								<div class="input-group">
							    	<span class="input-group-addon">结束时间：</span>
									<span class="block input-icon input-icon-right">
										<div class="input-group">
											<input type="text" class="form-control col-sm-5 input-small" id="taskEndDateStart" name="taskEndDateStart" placeholder="起" data-date-format="yyyy-mm-dd">
											<span class="input-group-addon">至</span>
											<input type="text" class="form-control col-sm-5 input-small" id="taskEndDateEnd" name="taskEndDateEnd" placeholder="止" data-date-format="yyyy-mm-dd">
										</div>
									</span>
							  	 </div>
							</div>
							<div class="col-xs-2 form-group">
								<button id="do_search_condition" type="button" class="btn btn-sm btn-purple">
									<i class="ace-icon fa fa-search"></i> 查询
								</button>
								<button class="btn btn-sm" type="reset" id="spReset">
									<i class="ace-icon fa fa-undo bigger-110"></i>重置
								</button>
							</div>
						</form>	
						<div class="col-md-12">
							<table id="tbl_todo" class="table table-striped table-hover">
								<thead>
									<tr>
										<th>流程名称</th>
										<th style="width:25%">任务主题</th>
										<th>开始时间</th>
										<th>结束时间</th>
										<th>任务状态</th>
										<th>当前环节名称</th>
										<!-- <th>任务接收人</th> -->
										<th>操作</th>
									</tr>
								</thead>
								<tbody></tbody>
							</table>
						</div>
					</div>
				</div>
				<!-- /.page-content -->
			</div>
			<!-- /.main-content -->
			<c:import url="../../commons/footer.jsp" charEncoding="UTF-8" />
		</div>
		<!-- /.main-container -->
		<c:import url="../../commons/post-include.jsp" charEncoding="UTF-8" />
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
									<div class="timeline-item clearfix">
										<div class="timeline-info">
											<i class="timeline-indicator ace-icon fa fa-hand-o-right btn btn-success no-hover"></i>
										</div>
		
										<div class="widget-box transparent">
											<div class="widget-header widget-header-small">
												<h5 class="widget-title smaller">
													<span class="grey">录入业务申请信息</span>
												</h5>
												<span class="widget-toolbar no-border">
													<i class="ace-icon fa fa-clock-o bigger-110"></i>
													2014-08-29 16:22
												</span>
											</div>
											<div class="widget-body">
												<div class="widget-main">
													无审批意见
												</div>
											</div>
										</div>
									</div>
								</div>
								<!-- /.timeline-items -->
							</div>
							<!-- /.timeline-container -->
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
		<!-- page specific plugin scripts START -->
		<!--[if lte IE 8]>
		  <script src="${ctx}/static/assets/js/excanvas.min.js"></script>
		<![endif]-->
		<!-- page specific plugin scripts END -->

		<!-- inline scripts related to this page -->
		
		<!-- 待处理 & 处理中 -->
		<script type="x-tmpl-mustache" id="dt-row-operation-01">
		<div style="width:160px;">
			<button class="btn btn-info btn-xs" role="showWorkDetail" 
				data-wfcode="{{workflowCode}}" 
				data-workflowid="{{workflowId}}" 
				data-status="{{taskStatus}}" 
				data-toggle="tooltip"  title="查看进度">
				<i class="ace-icon fa fa-eye"></i>
			</button>

			<button class="btn btn-warning btn-xs" 
				role="modifyPauseOrPlayStatus" 
				data-opt="pause" 
				data-wfcode="{{workflowCode}}" 
				data-workflowid="{{workflowId}}" 
				data-nodeid="{{nodeId}}" 
				data-taskid="{{taskId}}" 
				data-status="{{taskStatus}}" 
				data-toggle="tooltip"  title="暂停">
				<i class="ace-icon fa fa-pause"></i>
			</button>

			<button class="btn btn-danger btn-xs" role="modifyDropStatus" 
				data-opt="drop"
				data-wfcode="{{workflowCode}}" 
				data-workflowid="{{workflowId}}" 
				data-nodeid="{{nodeId}}" 
				data-taskid="{{taskId}}"
				data-status="8" data-rel="tooltip" title="终止">
				<i class="ace-icon fa fa-minus-square"></i>
			</button>
		</div>
	</script>
	
	<!-- 处理完成 &已撤销-->
	<script type="x-tmpl-mustache" id="dt-row-operation-02">
		<div>
			<button class="btn btn-info btn-xs" role="showWorkDetail" 
				data-wfcode="{{workflowCode}}" 
				data-workflowid="{{workflowId}}" 
				data-status="{{taskStatus}}" 
				data-toggle="tooltip"  title="查看进度">
				<i class="ace-icon fa fa-eye"></i>
			</button>
		</div>
	</script>
	
	<!-- 暂停中-->
	<script type="x-tmpl-mustache" id="dt-row-operation-03">
		<div style="width:120px;">
			<button class="btn btn-info btn-xs" role="showWorkDetail" 
				data-wfcode="{{workflowCode}}" 
				data-workflowid="{{workflowId}}" 
				data-status="{{taskStatus}}" 
				data-toggle="tooltip"  title="查看进度">
				<i class="ace-icon fa fa-eye"></i>
			</button>

			<button class="btn btn-success btn-xs" 
				role="modifyPauseOrPlayStatus" 
				data-opt="play" 
				data-wfcode="{{workflowCode}}" 
				data-workflowid="{{workflowId}}" 
				data-nodeid="{{nodeId}}" 
				data-taskid="{{taskId}}" 
				data-status="{{taskStatus}}" 
				data-toggle="tooltip"  title="继续">
				<i class="ace-icon fa fa-play"></i>
			</button>
			
			<button class="btn btn-danger btn-xs" role="modifyDropStatus" 
				data-opt="drop"
				data-wfcode="{{workflowCode}}" 
				data-workflowid="{{workflowId}}" 
				data-nodeid="{{nodeId}}" 
				data-taskid="{{taskId}}"
				data-status="8" data-rel="tooltip" title="终止">
				<i class="ace-icon fa fa-minus-square"></i>
			</button>
		</div>
	</script>
		<script>
			seajs.use('${ctx}/static/my/js/wfmonitor/main/main');
		</script>
	</body>

</html>