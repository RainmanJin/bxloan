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
				<style type="text/css">
					.smallTitle { margin-left :45px; } #projectFinace_modal div.modal-dialog
					{ width: 800px; margin: 30px auto; }
				</style>
				<script>
					var $$ctx = "${ctx}/";
				</script>
			</head>
			<body class="no-skin" onunload="javascript: window.returnValue='modal';">
				<c:if test="${requestScope.consultLocation != 'contract' && requestScope.business != 'business' }">
					<c:import url="../../../commons/navbar.jsp" charEncoding="UTF-8" />
				</c:if>
				<div class="main-container" id="main-container">
					<script type="text/javascript">
						try {
							ace.settings.check('main-container', 'fixed')
						} catch(e) {}
					</script>
					<c:if test="${requestScope.consultLocation != 'contract' && requestScope.business != 'business' }">
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
										<a href="singleCustomer">
											客户管理
										</a>
									</li>
									<li>
										<a href="singleCustomer">
											单一客户
										</a>
									</li>
								</ul>
								<!-- /.breadcrumb -->
							</div>
					</c:if>
					<div class="page-content">
						<%-- <c:import url="../../../commons/settings.jsp" charEncoding="UTF-8"
						/>
						--%>
						<div class="page-header">
							<h1>
								单一客户
								<small>
									<i class="ace-icon fa fa-angle-double-right">
									</i>
									个人客户
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
												借款人信息
											</a>
										</li>
										<!-- <li>
										<a data-toggle="tab" href="#faq-tab-2">
										<i class="green ace-icon fa fa-user bigger-120"></i>
										工作资料
										</a>
										</li> -->
										<li>
											<a data-toggle="tab" href="#faq-tab-2">
												经营信息
											</a>
										</li>
										<li>
											<a data-toggle="tab" href="#faq-tab-3">
												家庭资产信息
											</a>
										</li>
										<li>
											<a data-toggle="tab" href="#faq-tab-4">
												联系人信息
											</a>
										</li>
										<li>
											<a data-toggle="tab" href="#faq-tab-5">
												文档资料
											</a>
										</li>
										<li>
											<a data-toggle="tab" href="#faq-tab-6">
												系统内过往财务信息
											</a>
										</li>
										<li>
											<a data-toggle="tab" href="#faq-tab-7">
												系统操作员
											</a>
										</li>
									</ul>
									<div class="tab-content no-border padding-24">
										<!-- 隐藏域 -->
										<div>
											<input type="hidden" name="customerId" id="partyIdField" class="form-control"
											value="${requestScope.individual.partyId}" />
											<input type="hidden" id="hiddenForCertificateTypeCd" value="${requestScope.individual.certificateTypeCd}"
											/>
											<input type="hidden" id="employmentTypeField" value="${requestScope.individual.employmentType}"
											/>
											<input type="hidden" id="accountIdCwField" />
											<input type="hidden" id="correlativeRelationsIdCwField" />
											<input type="hidden" id="customerIdField" value="${requestScope.customerId}"
											/>
											<input type="hidden" id="workTypeField" value="${requestScope.workCode}"
											/>
											<input type="hidden" id="industryTypeCdField" value="${requestScope.industryTypeCd}"
											/>
											<input type="hidden" id="hiddenForFamilyCertificateType" value="100" />
											<input type="hidden" id="hiddenForLxrWorkType" />
											<input type="hidden" id="hiddenForUserName" value="${requestScope.createUsername}"
											/>
											<input type="hidden" id="hiddenForConsultLocation" value="${requestScope.consultLocation}"
											/>
											<input type="hidden" id="allDocTypeField" value="${requestScope.allDocType}"
											/>
											<input type="hidden" name="uploadPathField" id="uploadPathField" class="form-control"
											value="${requestScope.uploadPath}" />
											<input type="hidden"  id="businessField" class="form-control"
											value="${requestScope.business}" />
											<div>
												<!-- 文档上传的专用隐藏域 -->
												<input type="hidden" id="userNameDI" />
												<input type="hidden" id="customerNumDI" />
												<input type="hidden" id="userNumDI" />
												<input type="hidden" id="userDateDI" />
												<input type="hidden" id="documentNumDI" />
												<input type="hidden" id="bizIdDI" />
												<input type="hidden" id="documentTypeDI" />
												<input type="hidden" id="bizNumDI" />
												<input type="hidden" id="userOrgDI" />
												<input type="hidden" id="createTypeDI" />
												<input type="hidden" id="fileTypeDI" />
												<input type="hidden" id="userIdDI" />
											</div>
										</div>
										<!-- 借款人信息 -->
										<div id="faq-tab-1" class="tab-pane fade in active">
											<jsp:include page="details/basicInfo_jy.jsp"></jsp:include>
										</div>
										<!-- 账户管理 -->
										<div id="faq-tab-2" class="tab-pane fade">
											<jsp:include page="details/busineInfo.jsp"></jsp:include>
										</div>
										<!-- 家庭资产信息 -->
										<div id="faq-tab-3" class="tab-pane fade">
											<jsp:include page="details/familyAsset.jsp"></jsp:include>
										</div>
										<!-- 联系人管理 -->
										<div id="faq-tab-4" class="tab-pane fade">
											<jsp:include page="details/relativesList_jy.jsp"></jsp:include>
										</div>
										<!-- 文档资料管理 -->
										<div id="faq-tab-5" class="tab-pane fade">
											<jsp:include page="details/docList.jsp"></jsp:include>
										</div>
										<!-- faq-tab-5 end -->
										<!-- 财务信息 -->
										<div id="faq-tab-6" class="tab-pane fade">
											<jsp:include page="details/projectFinance.jsp"></jsp:include>
										</div>
										<!-- 财务信息 END -->
										<div id="faq-tab-7" class="tab-pane fade">
											<jsp:include page="../../customer/cusmanager/cusmanager.jsp"></jsp:include>
										</div>
									</div>
								</div>
							</div>
						</div>
						<!-- 财务信息 -->
						<div id="projectFinace_modal" class="modal fade" tabindex="-1" role="basic"
						aria-hidden="true" style=";" data-backdrop="static">
							<div class="modal-dialog modal-full">
								<div class="modal-content">
									<form id="detailProjectFinaceForm" class="form-horizontal" role="form"
									method="post">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal">
												&times;
											</button>
											<h4 class="blue bigger">
											</h4>
										</div>
										<div class="modal-body">
											<jsp:include page="details/relations.jsp"></jsp:include>
										</div>
										<div class="modal-footer">
											<button class="btn btn-sm btn-default" data-dismiss="modal" type="button">
												<i class="ace-icon fa fa-times">
												</i>
												返回
											</button>
										</div>
									</form>
								</div>
							</div>
						</div>
					</div>
					<!-- /.page-content -->
					<c:if test="${requestScope.consultLocation != 'contract' && requestScope.business != 'business' }">
						</div>
						<!-- /.main-content -->
						<c:import url="../../../commons/footer.jsp" charEncoding="UTF-8" />
					</c:if>
				</div>
				<!-- /.main-container -->
				<c:import url="../../../commons/post-include.jsp" charEncoding="UTF-8"
				/>
				<!-- page specific plugin scripts START -->
				<script type="text/javascript" src="${res}/js/uploadify/jquery.uploadify.min.js">
				</script>
				<!-- page specific plugin scripts END -->
				<!-- inline scripts related to this page -->
				<script type="text/javascript" src="${my}/js/customer/uniqueCustomer/mana_add_edit_detail/format.js">
				</script>
				<script>
					seajs.use('${my}/js/customer/uniqueCustomer/mana_add_edit_detail/main');
				</script>
			</body>
		
		</html>