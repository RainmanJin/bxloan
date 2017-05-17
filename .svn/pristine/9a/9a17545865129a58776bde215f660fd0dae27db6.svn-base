<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../commons/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<base href="${ctx}/">
<title>${title }</title>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta name="description" content="Dashboard page" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<style type="text/css">
.ace-settings-box.open {
	height: 100% !important;
	margin-top: 0px !important;
}
.datepicker {  
z-index: 99999!important;  
}
</style>
<script>
	var $$ctx = "${ctx}/";
</script>
<c:import url="../../commons/pre-include.jsp" charEncoding="UTF-8" />
<link rel="stylesheet" href="${ctx}/static/assets/js/uploadify/uploadify.css" />

</head>
<%
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Cache-Control", "no-store");
	response.setDateHeader("Expires", 0);
	response.setHeader("Pragma", "no-cache");
%>

<body class="no-skin">
	<!-- 隐藏域 START -->
	<div id="hiddenField">
	<input type="hidden" name="customerId" value="${customerId}" />
	<input type="hidden" name="projectId" value="${vo.projectId }">
	<input type="hidden" name="industryTypeCdField" id="industryTypeCdField" value="${industryTypeCd}" />
	</div>

	<!-- hidden field begin -->
	<input type="hidden" id="productCd" value="${productCd }">
	<input type="hidden" id="projectId" value="${vo.projectId }">
	<input type="hidden" id="partyId" value="${vo.partyId }">
	<input type="hidden" id="workflowId" value="${vo.workflowId }">
	<input type="hidden" id="taskId" value="${vo.taskId }">
	<input type="hidden" id="type" value="${type }">
	<input type="hidden" id="allDocTypeField" value="${allDocType}" />
	<input type="hidden" id="maxApplyAmt" value="${vo.product.maxApplyAmt }">
	<input type="hidden" id="minApplyAmt" value="${vo.product.minApplyAmt }">
	<input type="hidden" id="productName" value="${vo.product.productName }">
	<input type="hidden" id="insuranceOrg" value="${vo.insuranceOrgId }">
	<input type="hidden" id="agriculturalLoan" value="${agriculturalLoan }">
	<input type="hidden" id="bizType" value="${bizType }">
	<!-- add by HWL 20150814 start 跳转来源-->
	<input type="hidden" id="jumpSource" value="${jumpSource }">
	<!-- add by HWL 20150814 end -->
	<input type="hidden" id="ajaxHidden" value="0">
	<c:set var="isEdit" value="${!empty type && type=='edit'}" scope="request"/>
	<c:set var="isView" value="${!empty type && type=='check'}" scope="request"/>
	<input type="hidden" id="isView" value="${isView }" >
	<input type="hidden" id="isEdit" value="${isEdit }">
	<input type="hidden" id="unIds" value="${unIds }">
	
	<!-- 2015-03-23 -->
	<form id="productConfigInfoForm">
		<input type="hidden" name="minLoanTerm" value="${productConfig.minLoanTerm }">
		<input type="hidden" name="maxLoanTerm" value="${productConfig.maxLoanTerm }">
		<input type="hidden" name="specialLoanTerm" value="${productConfig.specialLoanTerm }">
		<input type="hidden" name="minRate" value="${productConfig.minRate }">
		<input type="hidden" name="maxRate" value="${productConfig.maxRate }">
		<input type="hidden" name="minOverdueRate" value="${productConfig.minOverdueRate }">
		<input type="hidden" name="maxOverdueRate" value="${productConfig.maxOverdueRate }">
		<input type="hidden" name="minPerculNegoRate" value="${productConfig.minPerculNegoRate }">
		<input type="hidden" name="maxPerculNegoRate" value="${productConfig.maxPerculNegoRate }">
		<input type="hidden" name="minPreRepaymentRate" value="${productConfig.minPreRepaymentRate }">
		<input type="hidden" name="maxPreRepaymentRate" value="${productConfig.maxPreRepaymentRate }">
		<input type="hidden" name="replyPeriodUnit" value="${productConfig.replyPeriodUnit }">
	</form>
	<!-- hidden field end -->

	<c:if test="${consultLocation == null }">
		<c:import url="../../commons/navbar.jsp" charEncoding="UTF-8" />
	</c:if>

	<div class="main-container" id="main-container">

		<c:if test="${consultLocation == null }">
			<c:import url="../../commons/sidebar.jsp" charEncoding="UTF-8" />
		</c:if>
		<c:if test="${consultLocation == null }">
			<div class="main-content">


				<div class="breadcrumbs" id="breadcrumbs">
					<ul class="breadcrumb">
						<li>
							<i class="ace-icon fa fa-home home-icon"></i>
							<a href="${ctx}">主页</a>
						</li>
						<li>客户管理</li>
						<li>单一客户</li>
					</ul>
				</div>
		</c:if>

		<div class="page-content">

			<div class="page-header">
				<h1>
					单一客户
					<small>
						<i class="ace-icon fa fa-angle-double-right"></i>
					业务申请
					</small>
					<div class="pull-right">
						<c:if test="${consultLocation == null }">
		               		<button class="btn btn-sm btn-pre" data-last="Finish" type="button" onclick="javascript:history.go(-1);">
		               			<i class="ace-icon fa fa-chevron-left"></i>
		               			返回
		              		 </button>
		              		 <span style="float:right;">&nbsp;&nbsp;&nbsp;</span>
	              		 </c:if>
					</div>
					<span style="float: right;"> <!-- 费用列表 --> 
						<c:if test="${type != 'check' }">
							<c:if test="${vo.bizRateId != null }">
								<button id="addExpenseRate" class="btn btn-sm btn-success"
									style="display: none;">
									<i class="ace-icon fa fa-plus"></i>新增
								</button>
							</c:if>
							<c:if test="${vo.bizRateId == null }">
								<button id="addExpenseRate" disabled="disabled"
									class="btn btn-sm btn-success" style="display: none;">
									<i class="ace-icon fa fa-plus"></i>新增
								</button>
							</c:if>
						</c:if> 
						<!-- 抵质押担保 --> 
						<c:if test="${type != 'check' }">
							<button id="addPawn" class="btn btn-sm btn-success"
								disabled="disabled" style="display: none;">
								<i class="ace-icon fa fa-plus"></i>新建关联
							</button>
						</c:if> 
						<!-- 保证人担保 --> 
						<c:if test="${type != 'check' }">
							<button id="addBail" class="btn btn-sm btn-success"
								disabled="disabled" style="display: none;">
								<i class="ace-icon fa fa-plus"></i>新建关联
							</button>
						</c:if> 
						<!-- 共同借款人 --> 
						<c:if test="${type != 'check' }">
							<!-- <button id="newCommonBorrower" class="btn btn-sm btn-success"
								style="display: none;">
								<i class="ace-icon fa fa-plus"></i>新建
							</button> -->
							<button id="addCommonBorrower" class="btn btn-sm btn-success"
								style="display: none;">
								<i class="ace-icon fa fa-plus"></i>新建关联
							</button>
						</c:if> 
					</span>
				</h1>
			</div>

			<div class="container-fluid">

				<c:if test="${consultLocation == null }">
					<div class="ace-settings-container" id="ace-settings-container">
						<div class="btn btn-app btn-xs btn-warning ace-settings-btn"
							id="ace-settings-btn">
							<i class="ace-icon fa fa-eye bigger-150"></i>
						</div>

						<div class="ace-settings-box clearfix" id="ace-settings-box">
							<div class="pull-left width-50">

								<div class="ace-settings-item">
									<label class="lbl" for="ace-settings-navbar"> <a
										style="text-decoration: none;" id="customerForFloatWindow"
										target="_blank"> 客户信息 </a> </label>
								</div>

							</div>
							<!-- /.pull-left -->
						</div>
						<!-- /.ace-settings-box -->
					</div>
					<!-- /.ace-settings-container -->
				</c:if>

				<div class="row">
					<div class="col-md-12">
						<div class="tabbable">
							<ul class="nav nav-tabs padding-12 tab-color-blue">
							
								<c:if test="${agriculturalLoan && !empty bizType }">
									<li class="active">
										<a data-toggle="tab" href="#tab-1" onclick="$('#addExpenseRate').hide();$('#addPawn').hide();$('#addBail').hide();$('#addCommonBorrower').hide();$('#newCommonBorrower').hide();">客户信息</a>
									</li>
								</c:if>
								
								<li class="<c:if test='${!agriculturalLoan || (agriculturalLoan && empty bizType) }'>active</c:if>">
									<a data-toggle="tab" href="#tab-2" onclick="$('#addExpenseRate').hide();$('#addPawn').hide();$('#addBail').hide();$('#addCommonBorrower').hide();$('#newCommonBorrower').hide();">基本项目信息</a>
								</li>
								<li>
									<a data-toggle="tab" href="#tab-3" onclick="$('#addExpenseRate').show();$('#addPawn').hide();$('#addBail').hide();$('#addCommonBorrower').hide();$('#newCommonBorrower').hide();">费用列表</a>
								</li>
								<li>
									<a data-toggle="tab" href="#tab-4" onclick="$('#addExpenseRate').hide();if($('#projectPawnInfoTableDiv').css('display') == 'block'){$('#addPawn').show();}$('#addBail').hide();$('#addCommonBorrower').hide();$('#newCommonBorrower').hide();">抵质押担保</a>
								</li>
								<li>
									<a data-toggle="tab" href="#tab-5" onclick="$('#addExpenseRate').hide();$('#addPawn').hide();if($('#bailTableDiv').css('display') == 'block'){$('#addBail').show();}$('#addCommonBorrower').hide();$('#newCommonBorrower').hide();">保证人担保</a>
								</li>
								
								<c:if test="${agriculturalLoan && !empty bizType }">
									<li>
										<a data-toggle="tab" href="#tab-7" onclick="$('#addExpenseRate').hide();$('#addPawn').hide();$('#addBail').hide();$('#addCommonBorrower').hide();$('#newCommonBorrower').hide();">家庭资产</a>
									</li>
									<c:if test="${bizType eq '1' }">
										<li>
											<a data-toggle="tab" href="#tab-12" onclick="$('#addExpenseRate').hide();$('#addPawn').hide();$('#addBail').hide();$('#addCommonBorrower').hide();$('#newCommonBorrower').hide();">收入支出情况</a>
										</li>
										<%--<li>
											<a data-toggle="tab" href="#tab-99" onclick="$('#addExpenseRate').hide();$('#addPawn').hide();$('#addBail').hide();$('#addCommonBorrower').hide();$('#newCommonBorrower').hide();">预计现金流量</a>
										</li>
									--%></c:if>
									<c:if test="${bizType eq '2' }">
										<li>
											<a data-toggle="tab" href="#tab-6" onclick="$('#addExpenseRate').hide();$('#addPawn').hide();$('#addBail').hide();$('#addCommonBorrower').hide();$('#newCommonBorrower').hide();">收入支出情况</a>
										</li>
									</c:if>
								</c:if>
								
								<li>
									<a data-toggle="tab" href="#tab-8" onclick="$('#addExpenseRate').hide();$('#addPawn').hide();$('#addBail').hide();if($('#commonBorrowerTableDiv').css('display') == 'block'){$('#addCommonBorrower').show();$('#newCommonBorrower').show();}">共同借款人</a>
								</li>
								<c:if test="${!agriculturalLoan }">
									<li>
										<a data-toggle="tab" href="#tab-9" onclick="$('#addExpenseRate').hide();$('#addPawn').hide();$('#addBail').hide();$('#addCommonBorrower').hide();$('#newCommonBorrower').hide();">财务信息</a>
									</li>
								</c:if>
								
								<li>
									<a data-toggle="tab" href="#tab-10" onclick="$('#addExpenseRate').hide();$('#addPawn').hide();$('#addBail').hide();$('#addCommonBorrower').hide();$('#newCommonBorrower').hide();">相关文档</a>
								</li>
								
								<c:if test="${type != 'check' }">
									<li>
										<a data-toggle="tab" href="#tab-11" onclick="$('#addExpenseRate').hide();$('#addPawn').hide();$('#addBail').hide();$('#addCommonBorrower').hide();$('#newCommonBorrower').hide();">提交申请</a>
									</li>
								</c:if>
							</ul>

							<div class="tab-content no-border padding-24">
							
								<!-- 客户信息 -->
								<div id="tab-1" class="tab-pane fade<c:if test='${agriculturalLoan && !empty bizType }'>  in active</c:if>">
									<jsp:include page="agriculture/nd_basic.jsp" />
								</div>
								
								 <!-- 预期现金流量表 -->
								<%--<div id="tab-99" class="tab-pane fade">
									<jsp:include page="agriculture/nd_expectCash.jsp" />
								</div>
								
								--%><!-- 基本项目信息 -->
								<div id="tab-2" class="tab-pane fade<c:if test='${!agriculturalLoan || (agriculturalLoan && empty bizType) }'> in active</c:if>">
									<jsp:include page="basicInfo.jsp" />
								</div>

								<!-- 费用列表 -->
								<div id="tab-3" class="tab-pane fade">
									<jsp:include page="expenseList.jsp" />
								</div>

								<!-- 抵质押担保 -->
								<div id="tab-4" class="tab-pane fade">
									<jsp:include page="collateral.jsp" />
								</div>

								<!-- 保证人担保 -->
								<div id="tab-5" class="tab-pane fade">
									<jsp:include page="bail.jsp" />
								</div>
								
								<!-- 收入支出情况-农业收入为主 -->
								<c:if test="${bizType eq '1' }">
									<div id="tab-12" class="tab-pane fade">
										<jsp:include page="agriculture/incomeExpenditure_agr.jsp"></jsp:include>
									</div>
								</c:if>
								<!-- 收入支出情况-非农业收入为主 -->
								<c:if test="${bizType eq '2' }">
									<div id="tab-6" class="tab-pane fade">
										<jsp:include page="agriculture/incomeExpenditure.jsp"></jsp:include>
									</div>
								</c:if>
								
								<!-- 家庭资产信息 -->
								<div id="tab-7" class="tab-pane fade">
									<jsp:include page="bizFamilyAssets.jsp" />
								</div>

								<!-- 共同借款人 -->
								<div id="tab-8" class="tab-pane fade">
									<jsp:include page="commonBorrower.jsp" />
								</div>

								<!-- 财务信息 -->
								<div id="tab-9" class="tab-pane fade">
									<jsp:include page="finance.jsp" />
								</div>

								<!-- 相关文档 -->
								<div id="tab-10" class="tab-pane fade">
									<jsp:include page="newDocList.jsp" />
								</div>

								<!-- 提交申请 -->
								<div id="tab-11" class="tab-pane fade">
									<jsp:include page="submitApply.jsp" />
								</div>

								<div id="partyDetailDiv" class="modal" data-backdrop="static"
									tabindex="-1">
									<div class="modal-dialog modal-lg" style="width: 1000px;">
										<div class="modal-content">
											<div class="modal-header">
												<button type="button" class="close" data-dismiss="modal">
													<span aria-hidden="true">&times;</span><span
														class="sr-only">Close</span>
												</button>
												<h4 class="modal-title">客户信息</h4>
											</div>
											<div class="modal-body">
												<iframe id="partyIframe" width="100%" height="100%" src="" frameborder="0"
													marginheight="0" marginwidth="0" scrolling-x="no" scrolling-y="auto" 
													style="overflow-x:scroll; overflow-y:hidden;"></iframe>
											</div>
										</div>
									</div>
								</div>
								
								<jsp:include page="agriculture/stock.jsp"></jsp:include>
								
								<c:if test="${bizType eq '1' }">
									<jsp:include page="agriculture/income_biz_form_agr.jsp"></jsp:include>
								</c:if>
								
								<c:if test="${bizType eq '2' }">
									<jsp:include page="agriculture/income_biz_form.jsp"></jsp:include>
								</c:if>
								
								<jsp:include page="agriculture/other_income_common_form.jsp"></jsp:include>
							
						</div>
					</div>
				</div>
			</div>
		</div>
		<c:if test="${consultLocation == null }">
	</div>
	</c:if>
	<c:if test="${consultLocation == null }">
		<c:import url="../../commons/footer.jsp" charEncoding="UTF-8" />
	</c:if>
	</div>

	<c:import url="../../commons/post-include.jsp" charEncoding="UTF-8" />

	<script type="text/javascript"
		src="${ctx }/static/assets/js/uploadify/jquery.uploadify.min.js"></script>

	<script>
		seajs.use('${ctx}/static/my/js/businessapplicationwd/main/main');
	</script>
</body>
</html>