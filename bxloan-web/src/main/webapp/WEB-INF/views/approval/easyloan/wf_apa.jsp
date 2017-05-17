<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../../../commons/taglibs.jsp" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<base href="${ctx}/">
<title>${title }</title>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

<meta name="description" content="Dashboard page" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<c:import url="../../../commons/pre-include.jsp" charEncoding="UTF-8"/>
<link rel="stylesheet" href="${ctx}/static/assets/js/uploadify/uploadify.css" />
<script>var $$ctx = "${ctx}/";</script>
<style type="text/css">
	.ace-settings-box.open {
		height:100%!important;
		margin-top:0px!important;
	}
</style>
</head>
<body class="no-skin">
	<c:import url="../../../commons/navbar.jsp" charEncoding="UTF-8" />
		<div class="main-container" id="main-container">
		<script type="text/javascript">
			try{ace.settings.check('main-container' , 'fixed')}catch(e){}
		</script>
	
		<c:import url="../../../commons/sidebar.jsp" charEncoding="UTF-8" />
		
		<div class="main-content">
			<div class="breadcrumbs" id="breadcrumbs">
				<script type="text/javascript">
					try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
				</script>

				<ul class="breadcrumb">
					<li>
					<a href="${ctx}">主页</a></li>
					<li class="active">工作面板</li>
					<li class="active">集中审批</li>
				</ul><!-- /.breadcrumb -->
			</div>
			<div class="page-content">
				<jsp:include page="../floatNavBar.jsp"></jsp:include>
				<!-- 隐藏域 -->
				<input type="hidden" id="workflowId" value="${workflowId }" />
				<input type="hidden" id="taskId" value="${taskId }" />
				<input type="hidden" id="taskStageCode" value="${taskStageCode }" />
				<input type="hidden" id="projectId" value="${projectId }" />
				<input type="hidden" id="partyId" value="${approvalContent.partyId }" />
				<input type="hidden" id="partyTypeField" value="${partyType}" />
				<input type="hidden" id="productTypeCd" value="${productTypeCd }" />
				<input type="hidden" id="upload_path" value="${uploadPath }" />
				<input type="hidden" id="projectNo" value="${approvalContent.projectNo }">
				<!-- .page-header -->
				<div class="page-header">
					<h1>
						工作面板<small>
						<i class="ace-icon fa fa-angle-double-right"></i>
						邦易贷审批流程
						</small>
					</h1>
				</div>
				<!-- /.page-header -->
				<div class="row">
					<div class="col-xs-12">
						<div id="timeline-2">
							<div class="row">
								<div class="col-xs-3" style="height:400px">
									<div class="timeline-container timeline-style2">
										<span class="timeline-label"> <b> </b></span>

										<div class="timeline-items">
											<div class="timeline-item clearfix">
												<div class="timeline-info">
													<span class="timeline-date"></span> <i
														class="timeline-indicator btn btn-info no-hover"></i>
												</div>
												<div class="widget-box transparent">
													<div class="widget-body">
														<div class="widget-main no-padding">
															<span 
															 class="green bolder">业务申请</span>
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
															 class="green bolder">评审环节</span>
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
															class="green bolder">审批环节</span>
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
															 class="green bolder">落实放款</span>
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
															class="green bolder">审核放款</span>
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
								<div class="col-xs-8 step-content pos-rel" id="step-container">
									<div class="active">
										<!-- <h3 class="lighter block green" id="content_title">标题</h3> -->
										<h3 class="lighter block green text-center">申请信息</h3>
										<form class="form-horizontal" id="form-projAppli">
											<input name="projectId" type="hidden" value="${projectId }">
											<input name="workflowId" type="hidden" value="${workflowId }">
											<input name="taskId" type="hidden" value="${taskId }">
											<input name="taskStageCode" type="hidden" value="${taskStageCode }">
											<!-- 下一环节执行信息 -->
											<input name="nextUser" type="hidden" value="">
											<input name="nextUserOrgId" type="hidden" value="">
										 	<div class="row">
											 	<div class="form-group col-md-6">
													<label for="inputInfo"
														class="col-xs-12 col-md-4 control-label no-padding-right">借款人：</label>
	
													<div class="col-xs-12 col-md-8">
														<span class="block input-icon input-icon-right"> 
															<input value="${elProjAppl.borrowerName }"
																disabled="disabled" type="text" 
																id="inputInfo" class="form-control">
														</span>
													</div>
												</div>
												
											 	<div class="form-group col-md-6">
													<label for="inputError"
														class="col-xs-12 col-md-4 control-label no-padding-right">共同借款人：</label>
		
													<div class="col-xs-12 col-md-8">
														<span class="block input-icon input-icon-right"> 
															<input value="${elProjAppl.commonBorrowerName }"
															disabled="disabled" type="text"
															id="inputError" class="width-100">
														</span>
													</div>
												</div>
										 	</div>
										 	<div class="row">
											 	<div class="form-group col-md-6">
													<label for="inputInfo"
														class="col-xs-12 col-md-4 control-label no-padding-right">金额：</label>
	
													<div class="col-xs-12 col-md-8">
														<span class="block input-icon input-icon-right"> 
														<div class="input-group">
															<input value="${elProjAppl.loanAmt }"
																disabled="disabled" type="text" 
																id="inputInfo" class="form-control formatNum" />
															<span class="input-group-addon">元</span>
														</div>
														</span>
													</div>
												</div>
												
											 	<div class="form-group col-md-6">
													<label for="inputError"
														class="col-xs-12 col-md-4 control-label no-padding-right">期限：</label>
		
													<div class="col-xs-12 col-md-8">
														<span class="block input-icon input-icon-right"> 
														<div class="input-group">
															<input value="${elProjAppl.loanTerm }"
															disabled="disabled" type="text"
															id="inputError" class="width-100" />
															<span class="input-group-addon">月</span>
														</div>
														</span>
													</div>
												</div>
										 	</div>
										 	<div class="row">
											 	<div class="form-group col-md-6">
													<label for="inputInfo"
														class="col-xs-12 col-md-4 control-label no-padding-right">利率：</label>
	
													<div class="col-xs-12 col-md-8">
														<span class="block input-icon input-icon-right"> 
														<div class="input-group">
															<input value="${elProjAppl.loanInteRate* 100}"
																disabled="disabled" type="text" 
																id="inputInfo" class="form-control" />
															<span class="input-group-addon">%</span>
														</div>
														</span>
													</div>
												</div>
												
											 	<div class="form-group col-md-6">
													<label for="inputError"
														class="col-xs-12 col-md-4 control-label no-padding-right">经办人：</label>
		
													<div class="col-xs-12 col-md-8">
														<span class="block input-icon input-icon-right"> 
															<input value="${elProjAppl.loanOfficer }"
															disabled="disabled" type="text"
															id="inputError" class="width-100">
														</span>
													</div>
												</div>
										 	</div>
										 	<div class="row">
										 		<div class="form-group col-md-6">
													<label for="inputError"
														class="col-xs-12 col-md-4 control-label no-padding-right">经办机构：</label>
		
													<div class="col-xs-12 col-md-8">
														<span class="block input-icon input-icon-right"> 
															<input value="${elProjAppl.loanOrg }"
															disabled="disabled" type="text"
															id="inputError" class="width-100">
														</span>
													</div>
												</div>
										 		
											 	<div class="form-group col-md-6">
													<label for="inputInfo"
														class="col-xs-12 col-md-4 control-label no-padding-right">风险审查员：</label>
	
													<div class="col-xs-12 col-md-8">
														<span class="block input-icon input-icon-right"> 
															<input value=" "
																disabled="disabled" type="text" 
																id="inputInfo" class="form-control">
														</span>
													</div>
												</div>
										 	</div>
										 	<h3 class="lighter block green text-center">评审意见</h3>
										 	<div class="row">
											 	<div class="form-group col-md-6">
													<label for="inputInfo"
														class="col-xs-12 col-md-4 control-label no-padding-right">建议金额：</label>
	
													<div class="col-xs-12 col-md-8">
														<span class="block input-icon input-icon-right"> 
														<div class="input-group">
														<input type="text" class="form-control formatNum" readonly="readonly" value="${elApprComment.proposalAmt }" />
														<span class="input-group-addon">元</span>
														</div>
														</span>
													</div>
												</div>
												
											 	<div class="form-group col-md-6">
													<label for="inputError"
														class="col-xs-12 col-md-4 control-label no-padding-right">建议期限：</label>
		
													<div class="col-xs-12 col-md-8">
														<select class="form-control" disabled="disabled">
															<dd:options codeType="LoanTerm" selected="${elApprComment.proposalTerm }"/>
														</select>
													</div>
												</div>
										 	</div>
										 	<div class="row">
											 	<div class="form-group col-md-6">
													<label for="inputInfo"
														class="col-xs-12 col-md-4 control-label no-padding-right">建议利率：</label>
													<div class="col-xs-12 col-md-8">
													<span class="block input-icon input-icon-right"> 
														<div class="input-group">
														<input type="text" class="form-control" readonly="readonly" value="${elApprComment.proposalRate*100 }">
														<span class="input-group-addon">%</span>
														</div>
													</span>
													</div>
												</div>
										 	</div>
										 	<h3 class="lighter block green text-center">审批建议</h3>
										 	<div class="row">
											 	<div class="form-group col-md-6">
													<label for="inputInfo"
														class="col-xs-12 col-md-4 control-label no-padding-right">建议金额：</label>
	
													<div class="col-xs-12 col-md-8">
														<span class="block input-icon input-icon-right"> 
														<div class="input-group">
														<input name="proposalAmtStr" type="text" class="form-control formatNum" />
														<span class="input-group-addon">元</span>
														</div>
														</span>
														
													</div>
												</div>
												
											 	<div class="form-group col-md-6">
													<label for="inputError"
														class="col-xs-12 col-md-4 control-label no-padding-right">建议期限：</label>
		
													<div class="col-xs-12 col-md-8">
														<select name="proposalTerm" class="form-control">
															<option value="">请选择</option>
															<dd:options codeType="LoanTerm"/>
														</select>
													</div>
												</div>
										 	</div>
										 	<div class="row">
											 	<div class="form-group col-md-6">
													<label for="inputInfo"
														class="col-xs-12 col-md-4 control-label no-padding-right">建议利率：</label>
													<div class="col-xs-12 col-md-8">
														<span class="block input-icon input-icon-right"> 
														<div class="input-group">
														<input name="proposalRateStr" type="text" class="form-control">
														<span class="input-group-addon">%</span>
														</div>
														</span>
														
													</div>
												</div>
										 	</div>
										 	
										 	<h3 class="lighter block green text-center">审查意见</h3>
											<div class="form-group">
												<label for="inputInfo" 
													class="col-xs-12 col-md-2 control-label"><font color="red">*</font>意见详情：</label>
												<div class="col-xs-12 col-md-8">
													<textarea placeholder="请输入您宝贵的意见" name="comments"
														class="form-control"></textarea>
												</div>
											</div>
											
										</form>
									</div>
								</div>
							</div>
							
							<!-- /.timeline-container -->
							<hr>
							<div class="wizard-actions" style=" text-align:center;">
								<button class="btn btn-info" role="uploadCommentFile" data-loading-text="正在提交.." >
									<i class="ace-icon fa fa-upload"></i> 上传意见表
								</button>
								<button class="btn btn-success" role="submitProjAppl" data-loading-text="正在提交.." >
									<i class="ace-icon fa fa-check-square-o"></i> 提交
								</button>
								<c:if test="${taskStageCode!='100315' and taskStageCode!='100316' }">
								<button role="quitProjAppl"
									data-last="Finish" class="btn btn-danger btn-next">
									<i class="ace-icon fa fa-floppy-o " data-loading-text="正在退回.."></i> 退回
								</button>
								<button role="dismissAppr" data-last="Finish" class="btn btn-danger btn-next">
								<i class="ace-icon fa fa-floppy-o " data-loading-text="正在拒绝.."></i> 拒绝
								</button>
								</c:if>
								<button role="cancelProjAppl" data-last="Finish" class="btn">
									<i class="ace-icon fa fa-trash-o"></i> 返回

								</button>
							</div>
						</div>
					</div>
				</div>
				<!-- 审批过程意见 -->
				<div class="row">
					<div class="col-xs-12 col-sm-10 col-sm-offset-1">
					<div class="timeline-container">
						<div class="timeline-label">
							<span class="label label-primary arrowed-in-right label-lg">
							过程意见
							</span>
						</div>
						<div class="timeline-items" id="wfDetailWarp">
						</div><!-- /.timeline-items -->
					</div><!-- /.timeline-container -->
					</div>
				</div>
			</div><!-- /.page-content -->
		</div><!-- /.main-content -->
		<c:import url="../../../commons/footer.jsp" charEncoding="UTF-8" />
	</div><!-- /.main-container -->
	<c:import url="../../../commons/post-include.jsp" charEncoding="UTF-8"/>
	<script type="text/javascript" src="${ctx}/static/assets/js/uploadify/jquery.uploadify.min.js"></script>
	<script>
		 seajs.use('${ctx}/static/my/js/approval/easyloan/wf_apa/main');
	</script>
	<!-- 浮窗链接的模式窗口 -->
		<div id="modalOfProjApp" class="modal fade text-center" tabindex="-1"  role="basic" aria-hidden="true" style="z-index:1061;" data-backdrop="static" >
           	<div class="modal-dialog modal-full" style="display: inline-block; width: 1100px; ">
               	<div class="modal-content">
		<div class="modal-body">
			<iframe src="" name="mainFrameOfProjApp" id="mainFrameOfProjApp" 
			 width="100%" height="678px" frameborder="0"
			marginheight="0" marginwidth="0" scrolling-x="no" 
			scrolling-y="auto"  style="overflow-x:scroll;overflow-y:hidden;"> </iframe>
		</div>
		<div style="margin-bottom:10px;">
			<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
			</div>
           	</div>
      	</div>
      	</div>
                                
	<!-- 相关文档的模式窗口 -->
	<jsp:include page="../../approvedocument/inputDocModal.jsp"></jsp:include>
	
	<div id="apply_info_tables_modal" class="modal" tabindex="-1" data-backdrop="static">
		<div class="modal-dialog" style="width:70%">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="blue bigger">
						<i class='ace-icon fa fa-search'></i> 电核网核信息
					</h4>
				</div>
				<div class="modal-body">
					<jsp:include page="../applyInfoTable.jsp"></jsp:include>
				</div>
				<div class="modal-footer center">
					<button class="btn btn-sm btn-default" data-dismiss="modal">
						<i class="ace-icon fa fa-times"></i>关闭
					</button>
				</div>
			</div>
			
		</div>
	</div>
	
	<!-- 选择下一步的任务执行者 -->
	 <div id="select_assigner" class="modal fade"  data-backdrop="static">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="blue bigger"></h4>
					</div>
					<div class="modal-body"  >
						<label>
						选择将任务委派给：
						</label>
                       		<div>
                       		<select id="selectAssignerName" class="form-control"></select>
                       		</div>
					<div class="modal-footer" style="background-color:#fff!important;">
						<button id="select_assigner_sure"  class="btn btn-sm btn-primary" data-loading-text="正在提交...">
							<i class="ace-icon fa fa-save"></i> 确定
						</button>
						<button class="btn btn-sm" data-dismiss="modal">
							<i class="ace-icon fa fa-times"></i> 取消
						</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>