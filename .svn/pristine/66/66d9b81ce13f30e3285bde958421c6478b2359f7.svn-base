<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
%>
	<%@include file="../../../commons/taglibs.jsp" %>
		<!DOCTYPE html>
		<html lang="zh">
			<head>
				<base href="${ctx}/" target="_self">
				<title>
					邦信微贷
				</title>
				<meta charset="utf-8" />
				<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
				<meta name="description" content="Dashboard page" />
				<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0"
				/>
				<c:import url="../../../commons/pre-include.jsp" charEncoding="UTF-8"
				/>
				<!-- page specific plugin styles START -->
				<link rel="stylesheet" href="${ctx}/static/assets/js/uploadify/uploadify.css"
				/>
				<!-- page specific plugin styles END -->
				<!-- inline styles related to this page -->
				<style>
				</style>
				
				<script>
					var $$ctx = "${ctx}/";
				</script>
			</head>
			<body class="no-skin" onunload="javascript: window.returnValue='modal';">
				<c:import url="../../../commons/navbar.jsp" charEncoding="UTF-8" />
				<div class="main-container" id="main-container">
					<script type="text/javascript">
						try {
							ace.settings.check('main-container', 'fixed')
						} catch(e) {}
					</script>
						<c:import url="../../../commons/sidebar.jsp" charEncoding="UTF-8" />
						<div class="main-content" id="main-content">
							<div class="breadcrumbs" id="breadcrumbs">
								<script type="text/javascript">
									try {
										ace.settings.check('breadcrumbs', 'fixed')
									} catch(e) {}
								</script>
								<ul class="breadcrumb" id="breadcrumb">
									<li>
										<i class="ace-icon fa fa-home home-icon">
										</i>
										<a href="/bxloan">
											主页
										</a>
									</li>
									<li>
										<a href="unitecustomer">
											客户管理
										</a>
									</li>
									<li>
										<a href="unitecustomer">
											联保体客户
										</a>
									</li>
								</ul>
								<!-- /.breadcrumb -->
							</div>
					<div class="page-content">
					<!-- 隐藏域 -->
						<div id="hiddenParams">
							<!-- 联保体id-->
						  <input id="ugnId" type="hidden" name="id" value="${ugn.id}" />
						  <input id="unGuId" type="hidden" name="unGuId" value="${ugn.unGuId}" />
						  <input type="hidden" name="action" value="${action}" />	
						  <input type="hidden" name="unstatus" value="${ugn.unStatus}" />		
						  <input type="hidden" name="upload_path" value="${uploadPath}" />
						  <input type="hidden" name="alldoctype" id="allDocTypeField" value="${allDocType}" />
						  <input type="hidden" name="nowDate" value="" />
						  <!-- 展示与编辑 -->
						  <c:set var="isEdit" value="${!empty action && (action=='edit'||action=='add')}" scope="request"/>
						  <c:set var="isView" value="${!empty action && action=='check'}" scope="request"/>	
						  <input id="isEdit" type="hidden" name="isEdit" value="${isEdit}" />
						  <input id="isView" type="hidden" name="isView" value="${isView}" />
						</div>
						<%-- <c:import url="../../../commons/settings.jsp" charEncoding="UTF-8"
						/>
						--%>
						<div class="page-header">
							<h1>
								联保体客户
								<small>
									<i class="ace-icon fa fa-angle-double-right">
									</i>
									详细信息
								</small>
								<span id="showAlive" style="float:right;">
								</span>
							</h1>
						</div>
						<!-- /.page-header -->
						<!-- 表单主体 -->
						<!-- TAB页标题 -->
						<div class="row">
							<div class="col-xs-12">
								<!-- PAGE CONTENT BEGINS -->
								<div class="tabbable">
									<ul class="nav nav-tabs padding-12 tab-color-blue" id="myTab">
										<li class="active">
											<a data-toggle="tab" href="#faq-tab-1">
												联保体信息
											</a>
										</li>
										<li>
											<a data-toggle="tab" href="#faq-tab-2">
												联保体成员信息
											</a>
										</li>
										<li>
											<a data-toggle="tab" href="#faq-tab-3">
												文档资料
											</a>
										</li>
									</ul>
									<div class="tab-content no-border padding-24">
										
										<!-- 联保体信息 -->
										<div id="faq-tab-1" class="tab-pane fade in active">
											<jsp:include page="details/basicInfo.jsp"></jsp:include>
										</div>
										
										<!-- 联保体成员信息 -->
										<div id="faq-tab-2" class="tab-pane fade">
											<jsp:include page="details/members.jsp"></jsp:include>
										</div>
										
										<!-- 文档资料 -->
										<div id="faq-tab-3" class="tab-pane fade">
											<jsp:include page="details/docList.jsp"></jsp:include>
										</div>
									</div>
								</div>
							</div>
						</div>
						
						<div role="modalInclude">
							<jsp:include page="details/selectModal.jsp"></jsp:include>
						</div>
						
						
						
					</div>
					<!-- /.page-content -->
						</div>
						<!-- /.main-content -->
						<c:import url="../../../commons/footer.jsp" charEncoding="UTF-8" />
				</div>
				<!-- /.main-container -->
				<c:import url="../../../commons/post-include.jsp" charEncoding="UTF-8"
				/>
				<!-- page specific plugin scripts START -->
				<script type="text/javascript" src="${res}/js/uploadify/jquery.uploadify.min.js">
				</script>
				<!-- page specific plugin scripts END -->
				<!-- inline scripts related to this page -->
				</script>
				<script>
					seajs.use('${my}/js/customer/uniteCustomer/filldetail/main');
				</script>
			</body>
		
		</html>