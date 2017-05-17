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
<!-- <link rel="stylesheet" href="${ctx}/static/assets/css/jquery-ui.custom.min.css" />
<link rel="stylesheet" href="${ctx}/static/assets/css/chosen.css" />
<link rel="stylesheet" href="${ctx}/static/assets/css/datepicker.css" />
<link rel="stylesheet" href="${ctx}/static/assets/css/bootstrap-timepicker.css" />
<link rel="stylesheet" href="${ctx}/static/assets/css/daterangepicker.css" /> -->

<!-- page specific plugin styles END -->
<link rel="stylesheet" href="${ctx}/static/assets/js/uploadify/uploadify.css" />
<!-- inline styles related to this page -->
<!-- <style type="text/css">
#tbl_addrList .btn-danger,.btn-info{
	display: none;
}
</style> -->

<script>
	var $$ctx = "${ctx}/";
</script>
</head>
<body class="no-skin">
	<c:if test="${consultLocation == '' }">
		<c:import url="../../commons/navbar.jsp" charEncoding="UTF-8" />
	</c:if>
	<div class="main-container" id="main-container">
		<script type="text/javascript">
			try{ace.settings.check('main-container' , 'fixed')}catch(e){}
		</script>

		<c:if test="${consultLocation == '' }">
			<c:import url="../../commons/sidebar.jsp" charEncoding="UTF-8" />
		</c:if>
		
			<c:if test="${consultLocation == '' }">
		<div class="main-content">
				<div class="breadcrumbs" id="breadcrumbs">
					<script type="text/javascript">
						try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
					</script>
	
					<ul class="breadcrumb">
						<li>
							<a href="${ctx}">主页</a>
						</li>
						<li class="active"><a href="${ctx}/singleCustomer">客户管理</a></li>
						<li class="active"><a href="${ctx}/singleCustomer">单一客户</a></li>
					</ul>
					<!-- /.breadcrumb -->
				</div>
			</c:if>
			
			<!-- 隐藏域 -->
			<input type="hidden" id="detail_party_id" value="${corpCus.partyId }"/>
			<input type="hidden" id="detail_view_type" value="${showDetail}"/>
			<input type="hidden" id="uploadPath" value="${uploadDestination}"/>
			<input type="hidden" id="datail_corp_registeredCapital" value="${corpCus.registeredCapital }" /> 
			
			<!-- 隐藏域 -->
			<div class="page-content">
				<!-- /.ace-settings-container -->

				<div class="page-header">
					<h1>
						客户管理 <small> <i class="ace-icon fa fa-angle-double-right"></i>
							企业客户
						</small> 
						<c:if test="${corpCus.states!='2' }">
						<span id="showAlive" style="float:right;">
			              	<button class="btn btn-sm btn-primary" 
			              	id="basic_commit" type="button">
			                  	<i class="ace-icon fa fa-arrow-right"></i> 生效
			              	</button>
			          	</span>
			          	</c:if>
					</h1>
				</div>
				<!-- /.page-header -->

				<div class="row">
					<div class="col-xs-12">
						<!-- PAGE CONTENT  -->

						<div class="row">
							<div class="col-xs-12">
							</div>
						</div>
						 <div class="tabbable">
                              <ul class="nav nav-tabs padding-12 tab-color-blue" id="myTab">
                                <li class="active">
	                                <a data-toggle="tab" href="#faq-tab-1">
                                    	概况信息
	                                </a>
	                            </li>
	                            <li>
	                                <a data-toggle="tab" href="#faq-tab-2">
	                                   	地址信息
	                                </a>
	                            </li>
	                            <li>
	                                <a data-toggle="tab" href="#faq-tab-3">
                                   		实际控制人
	                                </a>
	                            </li>
	                            <li>
	                                <a data-toggle="tab" href="#faq-tab-4">
                                   		 股东
	                                </a>
	                            </li>
	                            <li>
	                                <a data-toggle="tab" href="#faq-tab-5">
                                   		高管
	                                </a>
	                            </li>
	                            <li>
	                                <a data-toggle="tab" href="#faq-tab-6">
                                   		客户相关资料
	                                </a>
	                            </li>
	                           
	                            <li>
									<a data-toggle="tab" href="#faq-tab-7">
										系统内过往财务信息
									</a>
								</li>
								 <li>
	                                <a data-toggle="tab" href="#faq-tab-8">
                                   		系统操作员
	                                </a>
	                            </li>
                              </ul>
                              <div class="tab-content no-border padding-24">
                              	<div id="faq-tab-1" class="tab-pane fade in active">
									<jsp:include page="detail/basicInfo.jsp"></jsp:include>
									<div class="hr hr-double hr8"></div>
								</div>
								<div id="faq-tab-2" class="tab-pane">
									<jsp:include page="detail/addrList.jsp"></jsp:include>
									<div class="hr hr-double hr8"></div>
								</div>
								<div id="faq-tab-3" class="tab-pane">
									<jsp:include page="detail/actController.jsp"></jsp:include>
									<div class="hr hr-double hr8"></div>
								</div>
								<div id="faq-tab-4" class="tab-pane">
									<jsp:include page="detail/stockHolder.jsp"></jsp:include>
									<div class="hr hr-double hr8"></div>
								</div>
								<div id="faq-tab-5" class="tab-pane">
									<jsp:include page="detail/highManager.jsp"></jsp:include>
									<div class="hr hr-double hr8"></div>
								</div>
								<div id="faq-tab-6" class="tab-pane">
									<jsp:include page="detail/docList.jsp"></jsp:include>
									<div class="hr hr-double hr8"></div>
								</div>
								
								<!-- 财务信息kyf create 2014-10-21 -->
								<div id="faq-tab-7" class="tab-pane">
									<jsp:include page="detail/projectFinance.jsp"></jsp:include>
									<div class="hr hr-double hr8"></div>
								</div>
								<!-- 财务信息END -->
								<div id="faq-tab-8" class="tab-pane">
									<jsp:include page="../customer/cusmanager/cusmanager.jsp"></jsp:include>
									<div class="hr hr-double hr8"></div>
								</div>
                              </div>
                         </div>
						
						<!-- 财务信息 -->
						<div id="projectFinace_modal" class="modal fade" tabindex="-1" role="basic" aria-hidden="true" style=";" data-backdrop="static">
							<div class="modal-dialog">
								<div class="modal-content">
									<form id="detailProjectFinaceForm" class="form-horizontal" role="form" method="post">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal">
												&times;
											</button>
											<h4 class="blue bigger">
											</h4>
										</div>
										<div class="modal-body">
											<jsp:include page="detail/projectFinanceInfo.jsp"></jsp:include>
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
						<!-- PAGE CONTENT ENDS -->
					</div>
				</div>
			
			<!-- /.page-content -->
		</div>
		<!-- /.main-content -->

		<c:if test="${consultLocation == '' }">
			<c:import url="../../commons/footer.jsp" charEncoding="UTF-8" />
		</c:if>
	</div>
	
	<c:if test="${consultLocation == '' }">
	</div>
	</c:if>
	<!-- /.main-container -->
	<%-- modals --%>
		
		<jsp:include page="detail/inputPersonModal.jsp"></jsp:include>						
		<jsp:include page="detail/inputCorpModal.jsp"></jsp:include>						
		<jsp:include page="detail/inputAddrModal.jsp"></jsp:include>						
		<jsp:include page="detail/inputDocModal.jsp"></jsp:include>						
		<jsp:include page="detail/chooseParty.jsp"></jsp:include>						
	<%-- modals --%>
	<c:import url="../../commons/post-include.jsp" />

	<!-- page specific plugin scripts START -->
	<script type="text/javascript" src="${ctx}/static/assets/js/uploadify/jquery.uploadify.min.js"></script>
	<!-- page specific plugin scripts END -->

	<!-- inline scripts related to this page -->
	<script>
		seajs.use('${ctx}/static/my/js/corpcustomer/detail/main');
	</script>
</body>

</html>