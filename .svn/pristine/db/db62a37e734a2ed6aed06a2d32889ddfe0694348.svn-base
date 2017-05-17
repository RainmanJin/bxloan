<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../../commons/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<base href="${ctx}/">
<title>${title }</title>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

<meta name="description" content="Approval Page" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

<c:import url="../../commons/pre-include.jsp" charEncoding="UTF-8" />

<!-- page specific plugin styles START -->
<link rel="stylesheet" href="${ctx}/static/assets/css/jquery-ui.custom.min.css" />
<link rel="stylesheet" href="${ctx}/static/assets/css/chosen.css" />
<link rel="stylesheet" href="${ctx}/static/assets/css/datepicker.css" />
<link rel="stylesheet" href="${ctx}/static/assets/css/bootstrap-timepicker.css" />
<link rel="stylesheet" href="${ctx}/static/assets/css/daterangepicker.css" />
<link rel="stylesheet" href="${ctx}/static/assets/css/bootstrap-datetimepicker.css" />
<link rel="stylesheet" href="${ctx}/static/assets/css/colorpicker.css" />
<!-- page specific plugin styles END -->
<link rel="stylesheet" href="${ctx}/static/assets/js/uploadify/uploadify.css" />
<!-- inline styles related to this page -->
<!-- <style type="text/css">
#tbl_addrList .btn-danger,.btn-info{
	display: none;
}
</style> -->
<link rel="stylesheet" href="${ctx}/static/my/css/timeline-style3.css" />
<script>
	var $$ctx = "${ctx}/";
</script>
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

				<ul class="breadcrumb">
					<li><i class="ace-icon fa fa-home home-icon"></i> <a
						href="${ctx}">主页</a></li>
					<li class="active">客户管理</li>
				</ul>
				<!-- /.breadcrumb -->
			</div>
			
			<!-- 隐藏域 -->
			<input type="hidden" id="workflowId" value="${workflowId }">
			<input type="hidden" id="taskId" value="${taskId }">
			<input type="hidden" id="taskStageCode" value="${taskStageCode }">
			<input type="hidden" id="projectId" value="${projectId }">
			<input type="hidden" id="partyId" value="${approvalContent.partyId }">
			<input type="hidden" id="productTypeCd" value="${productTypeCd}">
			<input type="hidden" id="partyTypeField" value="${partyType}">
			 <input type="hidden" id="upload_path" value="${uploadPath }" />
			<!-- 隐藏域 -->

			<div class="page-content">
			
			<div class="ace-settings-container" id="ace-settings-container">
					<div class="btn btn-app btn-xs btn-warning ace-settings-btn"
						id="ace-settings-btn">
						<i class="ace-icon fa fa-eye bigger-150"></i>
					</div>

					<div class="ace-settings-box clearfix" id="ace-settings-box">
						<div class="pull-left width-50">
						
							<div class="ace-settings-item" style="margin-top:10px;">
								<label class="lbl" for="ace-settings-navbar">
								<a style="text-decoration: none;" id="customerForFloatWindow"  target="_blank">
                                 	客户信息
                                </a>
								 </label>
							</div>
							
							<div class="ace-settings-item">
								<label class="lbl" for="ace-settings-sidebar"> 
								<a style="text-decoration: none;" id="businessForFloatWindow"  target="_blank">
                                 	业务信息
                                </a>
								</label>
							</div>
							
							
							<div class="ace-settings-item">
								<label class="lbl" for="ace-settings-breadcrumbs"> 
								<a style="text-decoration: none;" id="downloadForFloatWindow"  target="_blank">
                                 	贷款申请表
                                </a>
								</label>
							</div>
							
							<div class="ace-settings-item">
								<label class="lbl" for="ace-settings-breadcrumbs">
								<a style="text-decoration: none;" id="documentsForFloatWindow"  target="_blank">
                                 	相关文档
                                </a>
								 </label>
							</div>
							
							<div class="ace-settings-item">
								<label class="lbl" for="ace-settings-breadcrumbs"> 
									<a style="text-decoration: none;" id="loanTrialWindow"  target="_blank">贷款试算</a></label>
							</div>
							<c:if test="${taskStageCode!='100315' and taskStageCode!='100316' }">
								<div class="ace-settings-item">
									<label class="lbl" for="ace-settings-breadcrumbs">
									<a style="text-decoration: none;" id="antiFakeFloatWindow"  target="_blank">
	                                 		 反欺诈情况
	                                </a>
									</label>
								</div>
							</c:if>
						</div>
						<!-- /.pull-left -->
					</div>
					<!-- /.ace-settings-box -->
				</div>


				<!-- /.page-header -->

				<div class="row">
					<div class="col-xs-12">
						<!-- PAGE CONTENT  -->
						
						<div class="row">
							<div class="timeline-container timeline-style3">
								<span class="timeline-label">
								流程名称
								</span>

								<div class="timeline-items">
									<div class="timeline-item clearfix">
										<div class="timeline-info">
											<span class="timeline-date"></span> 
											<i class="timeline-indicator btn btn-info no-hover"></i>
										</div>

										<div class="widget-box transparent">
											<div class="widget-body">
												<div class="widget-main no-padding">
													<span 
													 class="green bolder">录入业务申请信息</span>
												</div>
											</div>
										</div>
									</div>

									<div class="timeline-item clearfix">
										<div class="timeline-info">
											<span class="timeline-date"></span> <i
												class="timeline-indicator btn btn-info no-hover"></i>
										</div>

										<div class="widget-box transparent">
											<div class="widget-body">
												<div class="widget-main no-padding">
													<span taskTypeId="100311"
													 class="green bolder">电核网核</span>
												</div>
											</div>
										</div>
									</div>

									<div class="timeline-item clearfix">
										<div class="timeline-info">
											<span class="timeline-date"></span> <i
												class="timeline-indicator btn btn-info no-hover"></i>
										</div>

										<div class="widget-box transparent">
											<div class="widget-body">
												<div class="widget-main no-padding">
													<span taskTypeId="100312"
													class="green bolder">审批岗审批</span>
												</div>
											</div>
										</div>
									</div>

									<div class="timeline-item clearfix">
										<div class="timeline-info">
											<span class="timeline-date"></span> <i
												class="timeline-indicator btn btn-info no-hover"></i>
										</div>

										<div class="widget-box transparent">
											<div class="widget-body">
												<div class="widget-main no-padding">
													<span taskTypeId="100313"
													 class="green bolder">稽核岗-审查</span>
												</div>
											</div>
										</div>
									</div>

									<div class="timeline-item clearfix">
										<div class="timeline-info">
											<span class="timeline-date"></span> <i
												class="timeline-indicator btn btn-info no-hover"></i>
										</div>

										<div class="widget-box transparent">
											<div class="widget-body">
												<div class="widget-main no-padding">
													<span taskTypeId="100314"
													 class="green bolder">签订合同</span>
												</div>
											</div>
										</div>
									</div>

									<div class="timeline-item clearfix">
										<div class="timeline-info">
											<span class="timeline-date"></span> <i
												class="timeline-indicator btn btn-info no-hover"></i>
										</div>

										<div class="widget-box transparent">
											<div class="widget-body">
												<div class="widget-main no-padding">
													<span taskTypeId="100315"
													 class="green bolder">审核放款</span>
												</div>
											</div>
										</div>
									</div>
									<div class="timeline-item clearfix">
										<div class="timeline-info">
											<span class="timeline-date"></span> <i
												class="timeline-indicator btn btn-info no-hover"></i>
										</div>

										<div class="widget-box transparent">
											<div class="widget-body">
												<div class="widget-main no-padding">
													<span taskTypeId="100316" 
													class="green bolder">落实放款</span>
												</div>
											</div>
										</div>
									</div>
								</div>
								<!-- /.timeline-items -->
							</div>
						</div>
						
						
						<div class="row">
							<%-- 图片 --%>
							<div class="col-xs-6">
								<div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
								  <!-- Indicators -->
								  <ol class="carousel-indicators">
								    <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
								    <li data-target="#carousel-example-generic" data-slide-to="1"></li>
								    <li data-target="#carousel-example-generic" data-slide-to="2"></li>
								    <li data-target="#carousel-example-generic" data-slide-to="3"></li>
								  </ol>
								
								  <!-- Wrapper for slides -->
								  <div class="carousel-inner" role="listbox">
								    <div class="item active">
								      <img src="${ctx}/static/my/img/01.jpg" alt="...">
								      <div class="carousel-caption">
								        01
								      </div>
								    </div>
								    <div class="item">
								      <img src="${ctx}/static/my/img/02.jpg" alt="...">
								      <div class="carousel-caption">
								       02
								      </div>
								    </div>
								    <div class="item">
								      <img src="${ctx}/static/my/img/03.jpg" alt="...">
								      <div class="carousel-caption">
								       03
								      </div>
								    </div>
								    <div class="item">
								      <img src="${ctx}/static/my/img/04.jpg" alt="...">
								      <div class="carousel-caption">
								       04
								      </div>
								    </div>
								  </div>
								
								  <!-- Controls -->
								  <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
								    <span class="glyphicon glyphicon-chevron-left"></span>
								    <span class="sr-only">Previous</span>
								  </a>
								  <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
								    <span class="glyphicon glyphicon-chevron-right"></span>
								    <span class="sr-only">Next</span>
								  </a>
								</div>
							
							</div>
							<%-- 图片 --%>
							
							<%-- 数据表单 --%>

							<div class="col-xs-5">
							
								<div class="step-content pos-rel" id="step-container">
									<div class="active">
										<h3 id="content_title" style="display: none;	" ><!-- 标题 --></h3>
										<form class="form-horizontal" id="sample-form">
										
											<div class="form-group">
												<label for="inputSuccess"
													class="col-xs-12 col-sm-3 control-label no-padding-right">还款方式</label>

												<div class="col-xs-12 col-sm-5">
													<span class="block input-icon input-icon-right"> 
														<input value="${approvalContent.repayMode }"
															disabled="disabled" type="text" 
															id="inputSuccess" class="width-100">
													</span>
												</div>
											</div>
										 
										 	<div class="form-group">
												<label for="inputError"
													class="col-xs-12 col-sm-3 col-md-3 control-label no-padding-right">客户名称</label>
	
												<div class="col-xs-12 col-sm-5">
													<span class="block input-icon input-icon-right"> 
														<input value="${approvalContent.customerName }"
														disabled="disabled" type="text"
														id="inputError" class="width-100">
													</span>
												</div>
											</div>
											

											<div class="form-group">
												<label for="inputInfo"
													class="col-xs-12 col-sm-3 control-label no-padding-right">贷款产品</label>

												<div class="col-xs-12 col-sm-5">
													<span class="block input-icon input-icon-right"> 
														<input value="${approvalContent.productType }"
															disabled="disabled" type="text" 
															id="inputInfo" class="width-100">
													</span>
												</div>
											</div>

											<div class="form-group">
											
												<label for="inputError2"
													class="col-xs-12 col-sm-3 control-label no-padding-right">申报金额</label>

												<div class="col-xs-12 col-sm-5">
													<span class="block input-icon input-icon-right">
														<input value="${approvalContent.applyAmont }"
															disabled="disabled" type="text"
															id="inputError2" class="width-100">
													</span>
												</div>

											</div>
											<div class="form-group">
												<label for="inputWarning"
													class="col-xs-12 col-sm-3 control-label no-padding-right">期限</label>

												<div class="col-xs-12 col-sm-5">
													<span class="block input-icon input-icon-right"> 
														<i class="ace-icon fa fa-calendar"></i> 
														<input value="${approvalContent.applyTerm }个月" 
															disabled="disabled" type="text"
															id="inputWarning" class="width-100">
													</span>
												</div>
											</div>

											<div class="form-group">
												<label for="inputError"
													class="col-xs-12 col-sm-3 col-md-3 control-label no-padding-right">投放行业</label>

												<div class="col-xs-12 col-sm-5">
													<span class="block input-icon input-icon-right"> 
														<input value="${approvalContent.industryName }"
															disabled="disabled" type="text" 
															id="inputError" class="width-100">
													</span>
												</div>
											</div>

											<div class="form-group">
												<label for="inputError2"
													class="col-xs-12 col-sm-3 control-label no-padding-right">年利率(%)</label>

												<div class="col-xs-12 col-sm-5">
													<span class="block input-icon input-icon-right"> 
													<input value="${approvalContent.bizRate }"
														disabled="disabled" type="text" 
														id="inputError2" class="width-100">
													</span>
												</div>
											</div>

											<div class="form-group">
												<label for="inputInfo"
													class="col-xs-12 col-sm-3 control-label no-padding-right">申报时间</label>

												<div class="col-xs-12 col-sm-5">
													<span class="block input-icon input-icon-right"> 
														<i class="ace-icon fa fa-calendar"></i> 
														<input value="${approvalContent.applyDate }"
															disabled="disabled" type="text"
															id="inputInfo" class="width-100">
													</span>
												</div>
											</div>

											
											<div class="form-group">
												<label for="inputSuccess"
													class="col-xs-12 col-sm-3 control-label no-padding-right">经办人</label>

												<div class="col-xs-12 col-sm-5">
													<span class="block input-icon input-icon-right"> 
														<input value="${approvalContent.customerManagerName }"
															disabled="disabled" type="text" 
															id="inputSuccess" class="width-100">
													</span>
												</div>
											</div>
											
											<div class="form-group2">
												<label for="inputInfo" 
													class="col-xs-12 col-sm-2 control-label">意见详情</label>

												<div class="col-xs-12 col-sm-10" style="margin-left:-15px;">
													<textarea placeholder="请输入您宝贵的意见"
														role="comments_content" id="form-field-8"
														class="form-control"></textarea>
												</div>
											</div>
											
										</form>
									</div>
								</div>
								
								<%-- 数据表单  END--%>
							
								<div class="col-xs-1"></div>
								
							</div>
						</div>
						
						<div class="row" style="text-align: center;">
							<div class="col-xs-6 col-xs-offset-4">
								<label for="inputInfo" 
									class="col-xs-12 col-sm-2 control-label">意见详情</label>
		
								<div class="col-xs-12 col-sm-6" style="">
									<textarea placeholder="请数入您宝贵的意见"
										role="comments_content" id="form-field-8"
										class="form-control"></textarea>
								</div>
							</div>
							
						</div>
						
						
						<hr>
						<div class="wizard-actions" style=" text-align:center;">
							<button class="btn btn-success" role="submitAppr" data-loading-text="正在提交.." >
								<i class="ace-icon fa fa-check-square-o"></i> 提交
							</button>
							<c:if test="${taskStageCode!='100315' and taskStageCode!='100316' }">
								<button role="quitAppr"
									data-last="Finish" class="btn btn-danger btn-next">
									<i class="ace-icon fa fa-floppy-o " data-loading-text="正在退回.."></i> 退回
								</button>
							</c:if>
							<button role="cancelAppr" data-last="Finish" class="btn">
								<i class="ace-icon fa fa-trash-o"></i> 取消
							</button>
						</div>
						
						<%--审批意见 --%>
				<div id="">
				<div class="row">
									
						<div class="col-xs-12 col-sm-10 col-sm-offset-1">
						
							<div class="timeline-container">
								<div class="timeline-label">
									<span class="label label-primary arrowed-in-right label-lg">
									过程意见
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
		
								</div><!-- /.timeline-items -->
							
							</div><!-- /.timeline-container -->
						
						</div>
						
					
					</div>
				
				</div>
				<%--审批意见 --%>
						<!-- PAGE CONTENT ENDS -->
					</div>
				</div>
			
			<!-- /.page-content -->
		</div>
		<!-- /.main-content -->
		
		

		<c:import url="../../commons/footer.jsp" charEncoding="UTF-8" />

	</div>
	</div>
	<!-- /.main-container -->
	<%-- modals --%>

				<!-- 浮窗链接的模式窗口 -->
 				<div id="modalForView" class="modal fade text-center" tabindex="-1"  role="basic" aria-hidden="true" style="z-index:1061;" data-backdrop="static" >
                	<div class="modal-dialog modal-full" style="display: inline-block; width: 1100px; ">
                    	<div class="modal-content">
	                        <div class="modal-header">
	                        	<h4></h4>
							</div>
							<div class="modal-body">
								<iframe src="" name="mainFrame" id="mainFrame"  width="100%" height="678px" frameborder="0"
									marginheight="0" marginwidth="0" scrolling-x="no" scrolling-y="auto"  style=" overflow-x:scroll; overflow-y:hidden; "
									> </iframe>
							</div>
							<div style="margin-bottom:10px;">
								<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
							</div>
                      	</div>
                 	</div>
               	</div>
                                
				<!-- 相关文档的模式窗口 -->
				<div id="showDocuments" class="modal fade text-center" tabindex="-1"  role="basic" aria-hidden="true" style="z-index:1061;" data-backdrop="static" >
					<div class="modal-dialog modal-full" style="width: 1100px; ">
                    	<div class="modal-content">
                        	<div class="modal-header">
                                    	<h4></h4>
							</div>
							<div class="modal-body">
								<div class="col-xs-12 col-sm-12 widget-container-col ui-sortable">
								 	<div class="page-header" align="left">
										<h1>
										审批流程 <small><i class="ace-icon fa fa-angle-double-right"></i> 相关文档查询</small>
										</h1>
									</div>
									<!-- 条件搜索 -->
									<div class="row" id="condSearchDiv" style="margin:auto;height:40px;">
									    <div class="col-xs-12" align='left'>
									        <form id="document_search_form">
									            	查找：
									            <input id="query_documentName" name="query_documentName" placeholder="输入文档名称"
									            />
									            <button id="btn-query" class="btn btn-sm btn-purple" type="button">
									                <i class="ace-icon fa fa-search">
									                </i>
									               	 查询
									            </button>
									        </form>
									    </div>
									</div>
								<!-- /.row -->
								<div class="row">
								    <div class="col-xs-12">
								        <div class="row">
								            <div class="col-xs-12">
								                <table id="tbWd" class="table table-striped table-hover">
								                    <thead>
								                        <tr>
								                            <th>
								                               	          选择
								                            </th>
								                            <th>
								                                	文档名称
								                            </th>
								                            <th>
								                                	文档类型
								                            </th>
								                            <th>
								                               	         客户名称
								                            </th>
								                            <th>
								                                	创建人
								                            </th>
								                            <th>
								                                	创建日期
								                            </th>
								                            <th>
								                                	关联方式
								                            </th>
								                            <th>
								                                	操作
								                            </th>
								                        </tr>
								                    </thead>
								                    <tbody>
								                    </tbody>
								                </table>
								            </div>
								        </div>
								    </div>
								</div>
								<!-- /.row -->
								</div>
								<div style="margin-bottom:10px;">
									<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
								</div>
                               </div>
                                </div>
                                </div>
							</div>
							<!-- 相关文档的模式窗口 END-->
	
	<%-- modals --%>
	<c:import url="../../commons/post-include.jsp" />

	<!-- page specific plugin scripts START -->
	<script type="text/javascript" src="${ctx}/static/assets/js/uploadify/jquery.uploadify.min.js"></script>
	<!-- page specific plugin scripts END -->

	<!-- inline scripts related to this page -->
	<script>
		seajs.use('${ctx}/static/my/js/approval/main/main');
	</script>
</body>

</html>