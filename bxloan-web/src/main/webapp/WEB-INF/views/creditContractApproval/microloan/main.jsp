<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../../commons/taglibs.jsp" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="zh">
	<head>
		<base href="${ctx}/">
	<title>
		${title}
	</title>
	<meta charset="utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<meta name="description" content="Approval Page" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
	<c:import url="../../../commons/pre-include.jsp" charEncoding="UTF-8" />
	<!-- page specific plugin styles START -->
	<link rel="stylesheet" href="${ctx}/static/assets/css/jquery-ui.custom.min.css"/>
	<link rel="stylesheet" href="${ctx}/static/assets/css/chosen.css" />
	<link rel="stylesheet" href="${ctx}/static/assets/css/datepicker.css"/>
	<link rel="stylesheet" href="${ctx}/static/assets/css/bootstrap-timepicker.css"/>
	<link rel="stylesheet" href="${ctx}/static/assets/css/daterangepicker.css"/>
	<link rel="stylesheet" href="${ctx}/static/assets/css/bootstrap-datetimepicker.css"/>
	<link rel="stylesheet" href="${ctx}/static/assets/css/colorpicker.css"/>
	<link rel="stylesheet" href="${ctx}/static/assets/js/uploadify/uploadify.css"/>
	<!-- page specific plugin styles END -->
	<!-- inline styles related to this page -->
	<style type="text/css">
		.form-group-wdapproval { 
			float: left; 
			margin-bottom: 15px; 
			width: 45%;
		} 
		.form-group-wdapproval2 { 
			float: left; 
			margin-bottom: 15px; 
			width: 58%;
		}
		.form-horizontal .form-group-wdapproval { 
			margin-left: -12px\0; 
			margin-right:-12px\0;
		} 
		.form-group-wdapproval { 
			margin-bottom: 15px\0; 
		} 
		.lbl { 
			cursor:pointer; 
		} 
		.col-sm-2 { 
			width: 16.6666% \0; 
		} 
		.ace-settings-box.open { 
			height:
			100% !important; 
			margin-top: 0px !important; 
		} 
		.col-sm-4 { 
			width: 33.3333%\0; 
		} 
		.form-horizontal .control-label { 
			text-align: right\0; 
		} 
		.no-padding-right{ 
			padding-right: 0\0; 
		} 
		.col-sm-1,.col-sm-2,.col-sm-3,.col-sm-4,.col-sm-5,.col-sm-6,.col-sm-7,.col-sm-8,.col-sm-9,.col-sm-10,.col-sm-11,.col-sm-12{ 
			float: left\0; 
		} 
		.col-xs-1,.col-sm-1,.col-md-1,.col-lg-1,.col-xs-2,.col-sm-2,.col-md-2,.col-lg-2,.col-xs-3,.col-sm-3,.col-md-3,.col-lg-3,.col-xs-4,.col-sm-4,.col-md-4,.col-lg-4,.col-xs-5,.col-sm-5,.col-md-5,.col-lg-5,.col-xs-6,.col-sm-6,.col-md-6,.col-lg-6,.col-xs-7,.col-sm-7,.col-md-7,.col-lg-7,.col-xs-8,.col-sm-8,.col-md-8,.col-lg-8,.col-xs-9,.col-sm-9,.col-md-9,.col-lg-9,.col-xs-10,.col-sm-10,.col-md-10,.col-lg-10,.col-xs-11,.col-sm-11,.col-md-11,.col-lg-11,.col-xs-12,.col-sm-12,.col-md-12,.col-lg-12{ 
			min-height: 1px\0; 
			padding-left: 12px\0; 
			padding-right: 12px\0; 
			position: relative\0; 
		} 
		label { 
			font-size: 14px\0; 
			font-weight: normal\0; 
		} 
		.datepicker{ 
			z-index: 99999!important; 
		}
		.timeline-style2 .timeline-item::before{ 
			left:40px;
		}
		.timeline-style2 .timeline-indicator{
			left:35px;
		}
		.timeline-style2 .timeline-info{
			width:0;
		}
		.timeline-style2 .timeline-item .widget-box{ 
			margin-left:52px;
		}
	</style>
	<script>
		var $$ctx = "${ctx}/";
	</script>
</head>   
<body class="no-skin">
	<c:import url="../../../commons/navbar.jsp" charEncoding="UTF-8" />
	<div class="main-container" id="main-container">
		<script type="text/javascript">
			try {
				ace.settings.check('main-container', 'fixed')
			} catch(e) {}
		</script>
		<c:import url="../../../commons/sidebar.jsp" charEncoding="UTF-8" />
		<div class="main-content">
			<div class="breadcrumbs" id="breadcrumbs">
				<script type="text/javascript">
					try {
						ace.settings.check('breadcrumbs', 'fixed')
					} catch(e) {}
				</script>
				<ul class="breadcrumb">
					<li>
						<i class="ace-icon fa fa-home home-icon">
						</i>
						<a href="${ctx}">
							主页
						</a>
					</li>
					<li class="active">
						工作面板
					</li>
					<li class="active">
						集中审批
					</li>
				</ul>
			</div>
			<div class="page-content">
				<div class="ace-settings-container" id="ace-settings-container">
					<div class="btn btn-app btn-xs btn-warning ace-settings-btn" id="ace-settings-btn">
						<i class="ace-icon fa fa-eye bigger-150">
						</i>
					</div>
					<div class="ace-settings-box clearfix" id="ace-settings-box">
						<div class="pull-left width-50">
							<div class="ace-settings-item" style="margin-top:10px;">
								<label class="lbl" for="ace-settings-navbar">
									<a style="text-decoration: none;" id="customerForFloatWindow" target="_blank">
										客户信息
									</a>
								</label>
							</div>
							<div class="ace-settings-item">
								<label class="lbl" for="ace-settings-sidebar">
									<a style="text-decoration: none;" id="businessForFloatWindow" target="_blank">
										业务信息
									</a>
								</label>
							</div>
							<div class="ace-settings-item">
								<label class="lbl" for="ace-settings-breadcrumbs">
									<a style="text-decoration: none;" id="downloadForFloatWindow" target="_blank">
										贷款申请表
									</a>
								</label>
							</div>
							<div class="ace-settings-item">
								<label class="lbl" for="ace-settings-breadcrumbs">
									<a style="text-decoration: none;" id="documentsForFloatWindow" target="_blank">
										相关文档
									</a>
								</label>
							</div>
						</div>
						<!-- /.pull-left -->
					</div>
					<!-- /.ace-settings-box -->
				</div>
				<!-- /.ace-settings-container -->
				<div class="page-header">
					<h1>
						工作面板
						<small>
							<i class="ace-icon fa fa-angle-double-right">
							</i>
							授信审批流程
						</small>
					</h1>
				</div>
				<!-- /.page-header -->
				<!-- 隐藏域start -->
				<div>
					<input type="hidden" id="wfCode" value="${wfCode}" />
					<input type="hidden" id="workflowId" value="${workflowId}" />
					<input type="hidden" id="taskId" value="${taskId}" />
					<input type="hidden" id="taskStageCode" value="${taskStageCode}" />
					<input type="hidden" id="projectId" value="${projectId}" />
					<input type="hidden" id="partyType" value="${partyType}" />
					<input type="hidden" id="productTypeCd" value="${productTypeCd}" />
					<input type="hidden" id="partyId" value="${approvalContent.partyId}"/>
					<input type="hidden" id="showContract" value="${showContract}"/>
					<input id="projectNo" type="hidden" value="${approvalContent.projectNo}">
					<input type="hidden" id="upload_path" value="${uploadPath}" />
				</div>
				<!-- 隐藏域end -->
				<div class="row">
					<div class="col-xs-12">
						<!-- PAGE CONTENT BEGINS -->
						<div id="timeline-2">
							<div class="row">
								<div class="col-xs-2 col-md-2">
									<div class="timeline-container timeline-style2">
										<div class="timeline-items" style="margin-top: 20px;">
											<div class="timeline-item clearfix">
												<div class="timeline-info">
													<span class="timeline-date">
													</span>
													<i class="timeline-indicator btn btn-info no-hover">
													</i>
												</div>
												<div class="widget-box transparent">
													<div class="widget-body">
														<div class="widget-main no-padding">
															<span taskTypeId="100710" class="green bolder">
																录入业务
															</span>
														</div>
													</div>
												</div>
											</div>
											<div class="timeline-item clearfix">
												<div class="timeline-info">
													<span class="timeline-date">
													</span>
													<i class="timeline-indicator btn btn-info no-hover">
													</i>
												</div>
												<div class="widget-box transparent" style="cursor:pointer;">
													<div class="widget-body">
														<div class="widget-main no-padding">
															<span taskTypeId="100711" class="green bolder">
																贷款审查
															</span>
														</div>
													</div>
												</div>
											</div>
											<div class="timeline-item clearfix">
												<div class="timeline-info">
													<span class="timeline-date">
													</span>
													<i class="timeline-indicator btn btn-info no-hover">
													</i>
												</div>
												<div class="widget-box transparent" style="cursor:pointer;">
													<div class="widget-body">
														<div class="widget-main no-padding">
															<span taskTypeId="100712" class="green bolder">
																初审环节
															</span>
														</div>
													</div>
												</div>
											</div>
											<div class="timeline-item clearfix">
												<div class="timeline-info">
													<span class="timeline-date">
													</span>
													<i class="timeline-indicator btn btn-info no-hover">
													</i>
												</div>
												<div class="widget-box transparent">
													<div class="widget-body">
														<div class="widget-main no-padding">
															<span taskTypeId="100713" class="green bolder">
																任务分配
															</span>
														</div>
													</div>
												</div>
											</div>
											<div class="timeline-item clearfix">
												<div class="timeline-info">
													<span class="timeline-date">
													</span>
													<i class="timeline-indicator btn btn-info no-hover">
													</i>
												</div>
												<div class="widget-box transparent" style="cursor:pointer;">
													<div class="widget-body">
														<div class="widget-main no-padding">
															<span taskTypeId="100714" class="green bolder">
																一级审批
															</span>
														</div>
													</div>
												</div>
											</div>
											<div class="timeline-item clearfix">
												<div class="timeline-info">
													<span class="timeline-date">
													</span>
													<i class="timeline-indicator btn btn-info no-hover">
													</i>
												</div>
												<div class="widget-box transparent" style="cursor:pointer;">
													<div class="widget-body">
														<div class="widget-main no-padding">
															<span taskTypeId="100715" class="green bolder">
																二级审批
															</span>
														</div>
													</div>
												</div>
											</div>
											<div class="timeline-item clearfix">
												<div class="timeline-info">
													<span class="timeline-date">
													</span>
													<i class="timeline-indicator btn btn-info no-hover">
													</i>
												</div>
												<div class="widget-box transparent" style="cursor:pointer;">
													<div class="widget-body">
														<div class="widget-main no-padding">
															<span taskTypeId="100716" class="green bolder">
																制定电子合同
															</span>
														</div>
													</div>
												</div>
											</div>
											<div class="timeline-item clearfix">
												<div class="timeline-info">
													<span class="timeline-date">
													</span>
													<i class="timeline-indicator btn btn-info no-hover">
													</i>
												</div>
												<div class="widget-box transparent">
													<div class="widget-body">
														<div class="widget-main no-padding">
															<span taskTypeId="100717" class="green bolder">
																总经理审核合同
															</span>
														</div>
													</div>
												</div>
											</div>
											<div class="timeline-item clearfix">
												<div class="timeline-info">
													<span class="timeline-date">
													</span>
													<i class="timeline-indicator btn btn-info no-hover">
													</i>
												</div>
												<div class="widget-box transparent">
													<div class="widget-body">
														<div class="widget-main no-padding">
															<span taskTypeId="100718" class="green bolder">
																签订合同
															</span>
														</div>
													</div>
												</div>
											</div>
										</div>
										<!-- /.timeline-items -->
									</div>
									<!-- /.timeline-container -->
								</div>
								<!-- /.timeline-items -->
								<div class="step-content pos-rel col-md-10" id="step-container">
									<div class="active">
										<h3 class="lighter block green" id="content_title">
											<!-- 标题 -->
										</h3>
										<form class="form-horizontal" id="sample-form">
											<div class="row">
												<div class="form-group-wdapproval form-group">
													<label for="inputInfo" class="col-xs-12 col-sm-4 control-label no-padding-right">
														贷款产品：
													</label>
													<div class="col-xs-12 col-sm-8">
														<span class="block input-icon input-icon-right">
															<input value="${approvalContent.productType }" disabled="disabled" type="text"
															id="inputInfo" class="width-100">
														</span>
													</div>
												</div>
												<div class="form-group-wdapproval form-group">
													<label for="inputError" class="col-xs-12 col-sm-4 control-label no-padding-right">
														客户名称：
													</label>
													<div class="col-xs-12 col-sm-8">
														<span class="block input-icon input-icon-right">
															<input value="${approvalContent.customerName }" disabled="disabled" type="text"
															id="inputError" class="width-100">
														</span>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="form-group-wdapproval form-group">
													<label for="inputError2" class="col-xs-12 col-sm-4 control-label no-padding-right">
															授信额度：
													</label>
													<div class="col-xs-12 col-sm-8">
														<span class="block input-icon input-icon-right">
															<div class="input-group">
																<input value="${approvalContent.applyAmont }" disabled="disabled" type="text"
																id="inputError2" class="width-100 num_amt_2fixed">
																<span class="input-group-addon">
																	元
																</span>
															</div>
														</span>
													</div>
												</div>
												<div class="form-group-wdapproval form-group">
													<label for="inputWarning" class="col-xs-12 col-sm-4 control-label no-padding-right">
														授信期限：
													</label>
													<div class="col-xs-12 col-sm-8">
														<span class="block input-icon ">
															<div class="input-group">
																<input value="${approvalContent.applyTerm}" name="old_applyTerm" disabled="disabled"
																type="text" id="inputWarning" class="width-100">
															</div>
														</span>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="form-group-wdapproval form-group">
													<label for="inputError" class="col-xs-12 col-sm-4 control-label no-padding-right">
														授信类型：
													</label>
													<div class="col-xs-12 col-sm-8">
														<span class="block input-icon input-icon-right">
															<select class="form-control required" name="creditType" id="creditType" disabled="disabled">
																<dd:options codeType="CreditType" selected="${approvalContent.creditType }" />
															</select>
														</span>
													</div>
												</div>
												<div class="form-group-wdapproval form-group">
													<label for="inputInfo" class="col-xs-12 col-sm-4 control-label no-padding-right">
														申报时间：
													</label>
													<div class="col-xs-12 col-sm-8">
														<span class="block input-icon input-icon-right">
															<i class="ace-icon fa fa-calendar">
															</i>
															<input value="${fn:substring(approvalContent.applyDate,0,10) }" disabled="disabled"
															type="text" id="inputInfo" class="width-100">
														</span>
													</div>
												</div>
												
											</div>
											<div class="row">
												<div class="form-group-wdapproval form-group">
													<label for="inputWarning" class="col-xs-12 col-sm-4 control-label no-padding-right">
														担保方式：
													</label>
													<div class="col-xs-12 col-sm-8">
														<span class="block input-icon input-icon-right">
															<input value="${approvalContent.guaranteeMode }" disabled="disabled" type="text"
															id="guaranteeMode" class="width-100">
														</span>
													</div>
												</div>
												<div class="form-group-wdapproval form-group">
													<label for="inputError" class="col-xs-12 col-sm-4 control-label no-padding-right">
														投放行业：
													</label>
													<div class="col-xs-12 col-sm-8">
														<span class="block input-icon input-icon-right">
															<input value="${approvalContent.industryName }" disabled="disabled" type="text"
															id="inputError" class="width-100">
														</span>
													</div>
												</div>
											</div>
											<div class="row">
											    <div class="form-group-wdapproval form-group">
													<label for="inputError2" class="col-xs-12 col-sm-4 control-label no-padding-right">
														年利率：
													</label>
													<div class="col-xs-12 col-sm-8">
														<span class="block input-icon input-icon-right">
															<div class="input-group">
																<input value="${approvalContent.bizRate }" disabled="disabled" type="text"
																id="inputError2" class="width-100">
																<span class="input-group-addon">
																	%
																</span>
															</div>
														</span>
													</div>
												</div>
												<div class="form-group-wdapproval form-group">
													<label for="inputSuccess" class="col-xs-12 col-sm-4 control-label no-padding-right">
														经办人：
													</label>
													<div class="col-xs-12 col-sm-8">
														<span class="block input-icon input-icon-right">
															<input value="${approvalContent.customerManagerName }" disabled="disabled"
															type="text" id="inputSuccess" class="width-100">
														</span>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="form-group-wdapproval form-group">
													<label for="inputError2" class="col-xs-12 col-sm-4 control-label no-padding-right">
														经办机构：
													</label>
													<div class="col-xs-12 col-sm-8">
														<span class="block input-icon input-icon-right">
															<input value="${approvalContent.customerOrgName }" disabled="disabled"
															type="text" id="inputError2" class="width-100">
														</span>
													</div>
												</div>
											</div>
											<div id="tableDiv">
												<c:if test="${taskStageCode!='100713' && taskStageCode!='100717' && taskStageCode!='100718'}">
													<jsp:include page="replyForm.jsp"></jsp:include>
												</c:if>
											</div>
											<div class="row">
												<div class="form-group-wdapproval form-group">
													<label for="inputInfo"
														class="col-xs-12 col-sm-4 control-label no-padding-right"> <font
														color="red">*</font>批复意见：</label>
													<div class="col-xs-12 col-sm-8">
														<textarea name="replyOpinion" style="width:256%;"
															placeholder="请输入您宝贵的批复意见" role="comments_content" id="form-field-8"
															class="form-control"></textarea>
													</div>
												</div>
											</div>
										</form>
									</div>
								</div>
							</div>
							<hr>
							<div class="wizard-actions" style=" text-align:center;">
							    <!-- 在贷款审查和初审环节显示上传和下载意见表 -->
								<c:if test="${taskStageCode=='100711' || taskStageCode=='100712'}">
									<button class="btn btn-info" role="uploadCommentFile" data-loading-text="正在提交..">
										<i class="ace-icon fa fa-upload">
										</i>
										上传意见表
									</button>
									<button class="btn btn-yellow" role="downloadCommentFile" data-loading-text="正在提交..">
										<i class="ace-icon fa fa-download">
										</i>
										下载意见表
									</button>
								</c:if>
								<c:if test="${taskStageCode=='100718' }">
									<button class="btn btn-info" role="uploadCommentFile" data-loading-text="正在提交..">
										<i class="ace-icon fa fa-upload">
										</i>
										上传扫描件
									</button>
								</c:if>
								<%-- <c:if test="${showContract and taskStageCode!='100712'}"> --%>
								<%-- <c:if test="${showContract}">
									<button class="btn btn-success" role="contractAppr" data-loading-text="正在提交..">
										<i class="ace-icon fa fa-check-square-o">
										</i>
										制定电子合同
									</button>
								</c:if> --%>
								<button class="btn btn-success" role="submitAppr" data-loading-text="正在提交..">
									<i class="ace-icon fa fa-check-square-o">
									</i>
									提交
								</button>
								<c:if test="${taskStageCode!='100713'}">
									<button role="quitAppr" data-last="Finish" class="btn btn-danger btn-next">
										<i class="ace-icon fa fa-floppy-o " data-loading-text="正在退回..">
										</i>
										退回
									</button>
								</c:if>
								<c:if test="${taskStageCode!='100713' and taskStageCode!='100717' and taskStageCode!='100718'}">
									<button role="dismissAppr" data-last="Finish" class="btn btn-danger btn-next">
										<i class="ace-icon fa fa-floppy-o " data-loading-text="正在拒绝..">
										</i>
										拒绝
									</button>
								</c:if>
								<button role="turnBack" class="btn" type="button" onclick="window.history.go(-1)">
									<i class="ace-icon fa fa-trash-o">
									</i>
									返回
								</button>
							</div>
						</div>
					</div>
				</div>
				<!-- 过程意见start -->
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
										<i class="timeline-indicator ace-icon fa fa-hand-o-right btn btn-success no-hover">
										</i>
									</div>
									<div class="widget-box transparent">
										<div class="widget-header widget-header-small">
											<h5 class="widget-title smaller">
												<span class="grey">
												</span>
											</h5>
											<span class="widget-toolbar no-border">
												<i class="ace-icon fa fa-clock-o bigger-110">
												</i>
											</span>
										</div>
										<div class="widget-body">
											<div class="widget-main">
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
				<!-- 过程意见end -->
				<!-- 选择下一步的任务执行者 start-->
				<jsp:include page="modal/modalSelectAssigner.jsp"></jsp:include>
				<!-- 选择下一步的任务执行者 end -->
				<!--客户/业务信息Modal start -->
				<div id="modalOfProjApp" class="modal fade text-center" tabindex="-1" role="basic" aria-hidden="true" style="z-index:1061;" data-backdrop="static">
					<div class="modal-dialog modal-full" style="display: inline-block; width: 1100px; ">
						<div class="modal-content">
							<div class="modal-body">
								<iframe src="" name="mainFrameOfProjApp" id="mainFrameOfProjApp" width="100%"
								height="700px" frameborder="0" marginheight="0" marginwidth="0" scrolling-x="no"
								scrolling-y="auto" style=" overflow-x:scroll; overflow-y:hidden; ">
								</iframe>
							</div>
							<div style="margin-bottom:10px;">
								<button type="button" class="btn btn-default" data-dismiss="modal">
									关闭
								</button>
							</div>
						</div>
					</div>
				</div>
				<!--客户/业务信息Modal end -->
				<!-- 相关文档的模式窗口start -->
				<jsp:include page="modal/modalInputDoc.jsp"></jsp:include>
				<!-- 相关文档的模式窗口 end-->
				<!-- 查看历代批复信息 start -->
				<jsp:include page="modal/modalApporvals.jsp"></jsp:include>
				<!-- 查看历代批复信息 end -->
		   </div>
		   <!-- /.page-content -->
	    </div>
		<!-- /.main-content -->
		<c:import url="../../../commons/footer.jsp" charEncoding="UTF-8" />
	</div>
	<!-- /.main-container -->
	<c:import url="../../../commons/post-include.jsp" />
	<!-- page specific plugin scripts START -->
	<script type="text/javascript" src="${ctx}/static/assets/js/uploadify/jquery.uploadify.min.js">
	</script>
	<!-- page specific plugin scripts END -->
	<!-- inline scripts related to this page -->
	<script>
		seajs.use('${ctx}/static/my/js/creditContractApproval/microloan/main');
	</script>
</body>    
</html>