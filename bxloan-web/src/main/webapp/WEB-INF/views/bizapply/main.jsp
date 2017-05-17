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
<meta name="description" content="Dashboard page" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<c:import url="../../commons/pre-include.jsp" charEncoding="UTF-8" />
<!-- page specific plugin styles START -->
<link rel="stylesheet" href="${ctx}/static/assets/css/jquery-ui.custom.min.css" />
<link rel="stylesheet" href="${ctx}/static/assets/css/chosen.css" />
<link rel="stylesheet" href="${ctx}/static/assets/css/datepicker.css" />
<link rel="stylesheet" href="${ctx}/static/assets/css/bootstrap-timepicker.css" />
<link rel="stylesheet" href="${ctx}/static/assets/css/daterangepicker.css" />
<link rel="stylesheet"
	href="${ctx}/static/assets/css/bootstrap-datetimepicker.css" />
<link rel="stylesheet" href="${ctx}/static/assets/css/colorpicker.css" />
<link rel="stylesheet" href="${ctx}/static/my/css/switch-number.css" />
<link rel="stylesheet" href="${ctx}/static/assets/js/uploadify/uploadify.css" />
<!-- page specific plugin styles END -->
<!-- inline styles related to this page -->
<style type="text/css">
.ace-settings-box.open {
	height: 100% !important;
	margin-top: 0px !important;
}
</style>
<script>
	var $$ctx = "${ctx}/";
</script>
</head>
<body class="no-skin">
	<c:if test="${consultLocation == null }">
		<c:import url="../../commons/navbar.jsp" charEncoding="UTF-8" />
	</c:if>
	<div class="main-container" id="main-container">
		<c:if test="${consultLocation == null }">
			<c:import url="../../commons/sidebar.jsp" charEncoding="UTF-8" />
		</c:if>
		<script type="text/javascript">
			try {
				ace.settings.check('main-container', 'fixed')
			} catch (e) {
			}
		</script>
		<div class="main-content">
		<c:if test="${consultLocation == null }">
				<div class="breadcrumbs" id="breadcrumbs">
					<script type="text/javascript">
						try {
							ace.settings.check('breadcrumbs', 'fixed')
						} catch (e) {
						}
					</script>
					<ul class="breadcrumb">
						<li>
							<i class="ace-icon fa fa-home home-icon"></i>
							<a href="${ctx}">主页</a>
						</li>
						<li>客户管理</li>
						<li>单一客户</li>
					</ul>
					<!-- /.breadcrumb -->
				</div>
		</c:if>
		<div class="page-content">
			<%-- <c:import url="../commons/settings.jsp" charEncoding="UTF-8" />
						--%>
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
					<span style="float: right;">
						<!-- 共同借款人 -->
						<c:if test="${judgeType != 'check' }">
							<button id="newCommonBorrower" class="btn btn-sm btn-success"
								style="display: none;">
								<i class="ace-icon fa fa-plus"></i>新建
							</button>
							<!-- <button id="addCommonBorrower" class="btn btn-sm btn-success"
								style="display: none;">
								<i class="ace-icon fa fa-plus"></i>新建关联
							</button> -->
						</c:if>
					</span>
				</h1>
			</div>
			
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
			
			<!-- /.page-header -->
			<!-- 表单主体 -->
			<!-- TAB页标题 -->
			<div class="row">
				<div class="col-xs-12">
					<!-- PAGE CONTENT BEGINS -->
					<div class="tabbable">
						<ul class="nav nav-tabs padding-12 tab-color-blue" id="myTab">
							<li class="active">
								<a data-toggle="tab" href="#faq-tab-1" onclick="$('#addCommonBorrower').hide();$('#newCommonBorrower').hide();">
									<i class="bigger-120"> </i> 基本项目信息
								</a>
							</li>
							
							<li>
								<a data-toggle="tab" href="#faq-tab-2" onclick="$('#addCommonBorrower').hide();$('#newCommonBorrower').hide();">
									<i class="bigger-120"> </i> 费用列表
								</a>
							</li>
							
							<li>
								<a data-toggle="tab" href="#faq-tab-3" onclick="$('#addCommonBorrower').hide();$('#newCommonBorrower').hide();">
									<i class="bigger-120"> </i> 月可支配收入测算 
								</a>
							</li>
							
							<li>
								<a data-toggle="tab" href="#faq-tab-4" id="budget" onclick="$('#addCommonBorrower').hide();$('#newCommonBorrower').hide();">
									<i class="bigger-120"> </i> 额度测算 
								</a>
							</li>
							
							<li>
								<a data-toggle="tab" href="#faq-tab-8" onclick="$('#addCommonBorrower').hide();$('#newCommonBorrower').hide();">
									<i class="bigger-120"> </i> 征信情况
								</a>
							</li>
							
							<li>
								<a data-toggle="tab" href="#faq-tab-9" onclick="if($('#commonBorrowerTableDiv').attr('style') == 'display: block;'){$('#addCommonBorrower').show();$('#newCommonBorrower').show();}">
									<i class="bigger-120"> </i> 共同借款人
								</a>
							</li>
							
							<li>
								<a data-toggle="tab" href="#faq-tab-5" onclick="$('#addCommonBorrower').hide();$('#newCommonBorrower').hide();">
									<i class="bigger-120"> </i> 家庭资产
								</a>
							</li>
							
							<li>
								<a data-toggle="tab" href="#faq-tab-6" onclick="$('#addCommonBorrower').hide();$('#newCommonBorrower').hide();">
									<i class="bigger-120"> </i> 相关文档
								</a>
							</li>
							
							<c:if test="${judgeType != 'check' }">
								<li>
									<a data-toggle="tab" href="#faq-tab-7" onclick="$('#addCommonBorrower').hide();$('#newCommonBorrower').hide();">
										<i class="bigger-120"> </i> 提交申请
									</a>
								</li>
							</c:if>
						</ul>
						<input type="hidden" id="curUser" value="${curUser }">
						<input type="hidden" id="productCd" value="${productCd }">
						<input type="hidden" id="judgeType" value="${judgeType }">
						<input type="hidden" id="bizRateId" value="${bizRate.bizRateId }">
						<input type="hidden" id="workflowId" value="${workflowId }">
						<input type="hidden" id="taskId" value="${taskId }">
						<input type="hidden" id="type" value="${param.type }">
						<input type="hidden" id="allDocTypeField" value="${allDocType }" />
						<input type="hidden" id="uploadPathField" name="uploadPathField" value="${uploadPath}" />
						<input type="hidden" id="maxApplyAmt" value="${product.maxApplyAmt }">
						<input type="hidden" id="minApplyAmt" value="${product.minApplyAmt }">
						<input type="hidden" id="productName" value="${product.productName }">
						
						<!-- add by HWL 20150814 start 跳转来源-->
						<input type="hidden" id="jumpSource" value="${jumpSource }">
						<!-- add by HWL 20150814 end -->
						
						<div class="tab-content no-border padding-24">
							<!-- 基本项目信息 -->
							<div id="faq-tab-1" class="tab-pane fade in active">
								<jsp:include page="basicInfo.jsp" />
							</div>
							<!-- 费用列表 -->
							<div id="faq-tab-2" class="tab-pane fade">
								<jsp:include page="expenseList.jsp" />
							</div>
							<!-- 月可支配收入测算 -->
							<div id="faq-tab-3" class="tab-pane fade">
								<jsp:include page="controllableIncomeMeasure.jsp" />
							</div>
							<!-- 额度测算 -->
							<div id="faq-tab-4" class="tab-pane fade">
								<jsp:include page="quotaMeasure.jsp" />
							</div>
							<!-- 征信情况 -->
							<div id="faq-tab-8" class="tab-pane fade">
								<jsp:include page="drCreditInfo.jsp" />
							</div>
							<!-- 共同借款人 -->
							<div id="faq-tab-9" class="tab-pane fade">
								<jsp:include page="commonBorrower.jsp" />
							</div>
							<!-- 家庭资产 -->
							<div id="faq-tab-5" class="tab-pane fade">
								<jsp:include page="familyAssets.jsp" />
							</div>
							<!-- 相关文档 -->
							<div id="faq-tab-6" class="tab-pane fade">
								<jsp:include page="newDocList.jsp" />
							</div>
							<!-- 提交申请 -->
							<div id="faq-tab-7" class="tab-pane fade">
								<jsp:include page="submitApply.jsp" />
							</div>
							
						</div>
					</div>
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


				<div id="dkssModal" class="modal fade" data-backdrop="static"
					tabindex="-1">
					<div class="modal-dialog modal-lg">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">
									<span aria-hidden="true"> &times; </span> <span class="sr-only">
										Close </span>
								</button>
								<h4 class="modal-title">贷款试算</h4>
							</div>
							<div class="modal-body">
								<form class="form-horizontal" id="countRateForm">
									<input type="hidden" name="projectId" value="${projectApplication.projectId }">
									<div class="row">
										<div class="form-group">
											<label
												class="col-xs-8 col-sm-2 control-label no-padding-right">
												贷款金额（元）： </label>
											<div class="col-xs-8 col-sm-3">
												<span class="block input-icon input-icon-right"> <input
													type="text" id="loanAmountBiz" name="loanAmount"
													class="form-control required isNumber"> </span>
											</div>
											<label
												class="col-xs-8 col-sm-2 control-label no-padding-right">
												贷款时间： </label>
											<div class="col-xs-8 col-sm-3">
												<span class="block input-icon input-icon-right"> <input
													type="text" id="loanStartDateBiz" name="loanStartDate"
													data-date-format="yyyy-mm-dd" class="form-control required">
												</span>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group">
											<label class="col-xs-12 col-sm-2 control-label no-padding-right">
												期限： </label>
											<div class="col-xs-12 col-sm-3">
												<span class="block input-icon input-icon-right">
													<input type="text" class="form-control" id="applyTermBiz" name="applyTerm">
												</span>
											</div>
											<label class="col-xs-12 col-sm-2 control-label no-padding-right">
												期限单位： </label>
											<div class="col-xs-12 col-sm-3">
												<span class="input-icon block input-icon-right">
													<select name="applyTermUnit" class="form-control">
														<dd:options codeType="TermUnitCd"/>
													</select>
												</span>
											</div>
											<div class="help-block col-xs-12 col-sm-reset inline">
											</div>
										</div>
									</div>
									
									<div class="row">
										<div class="form-group">
											<label class="col-xs-12 col-sm-2 control-label no-padding-right">
												还款方式： </label>
											<div class="col-xs-12 col-sm-3">
												<span class="input-icon block input-icon-right"> <select
													name="repayment" id="repaymentBiz"
													class="form-control required">
														<dd:options codeType="RepaymentMode"
															selected="${projectApplication.repayingMode }" />
												</select> </span>
											</div>
											<div class="help-block col-xs-12 col-sm-reset inline">
											</div>
											
											<label
												class="col-xs-12 col-sm-2 control-label no-padding-right">
												还款周期月数： </label>
											<div class="col-xs-12 col-sm-3">
												<span class="block input-icon input-icon-right"> <input
													type="text" id="repaymentNumberBiz" name="repaymentNumber"
													class="form-control required isDayInMonth"> </span>
											</div>
										</div>
									</div>
									
									<div class="row">
										<div class="form-group">
											<label
												class="col-xs-12 col-sm-2 control-label no-padding-right">
												还款日： </label>
											<div class="col-xs-12 col-sm-3">
												<span class="block input-icon input-icon-right"> <input
													type="text" id="repaymentDateBiz" name="repaymentDate"
													class="form-control required isIntNotNegative"> </span>
											</div>
											<label
												class="col-xs-12 col-sm-2 control-label no-padding-right">
												年利率（%）： </label>
											<div class="col-xs-12 col-sm-3">
												<span class="block input-icon input-icon-right"> <input
													type="text" id="rateBiz" name="rate"
													class="form-control required isPercentNumberFour">
												</span>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group">
											
										</div>
									</div>
									<div class="row" align="right">
										<div>
											<button id="countRate" type="submit"
												class="btn btn-sm btn-primary">
												<i class="ace-ico fa fa-cny"> </i> 计算
											</button>
											<button id="resetCountRateForm" type="button"
												class="btn btn-sm btn-default">
												<i class="ace-icon fa fa-undo"> </i> 重置
											</button>
										</div>
									</div>
									<br> <br>
									<div>
										<span> <h7 class="blue" style="font-size:20px"> <i
												class="orange ace-icon fa fa-credit-card bigger-110"> </i>
											还款计划 </h7> </span>
									</div>
									<table id="tbl" class="table table-striped table-hover">
										<thead>
											<tr>
												<th>期次</th>
												<th>计划还款日</th>
												<th>本期应还金额(元)</th>
												<th>应还本金(元)</th>
												<th>应还利息(元)</th>
												<th>累计还本(元)</th>
												<th>贷款余额(元)</th>
											</tr>
										</thead>
										<tbody id="tbld">
										</tbody>
										<tfoot id="tbfoot">
										</tfoot>
									</table>
								</form>
							</div>
						</div>
					</div>
				</div>
				<div id="repayPlanModal" class="modal fade" data-backdrop="static"
					tabindex="-1">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">
									<span aria-hidden="true"> &times; </span> <span class="sr-only">
										Close </span>
								</button>
								<h4 class="modal-title">还款计划</h4>
							</div>
							<div class="modal-body">
								<form id="repayPlanForm" class="form-horizontal">
									<div class="modal-body">
										<div class="row">
											<div class="col-xs-12">
												<div class="form-group">
													<label class="col-sm-5 control-label no-padding-right"
														for="repayDate"> <font color='red'> * </font>
														计划还款时间： </label>
													<div class="col-sm-7">
														<input type="text" class="form-control required"
															id="repayDate" name="repayDateStr"
															data-date-format="yyyy-mm-dd">
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-5 control-label no-padding-right"
														for="repayAmt"> <font color='red'> * </font>
														计划还款本金金额（元）： </label>
													<div class="col-sm-7">
														<input type="text"
															class="form-control required isPositiveNumberTwo"
															id="repayAmt" name="repayAmt" />
													</div>
												</div>
												<input type="hidden" id="repayPlanId" name="repayPlanId">
												<input type="hidden" name="projectNo"
													value="${projectApplication.projectNo }"> <input type="hidden"
													name="projectId" value="${projectApplication.projectId }"> <input
													type="hidden" name="customerId" value="${party.partyId }">
											</div>
										</div>
									</div>
									<hr>
									<div class="row" align="right">
										<button id="sureRepayPlanForm" type="submit"
											class="btn btn-primary">确定</button>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- /.page-content -->
		</div>
		<!-- /.main-content -->
		<c:if test="${consultLocation == '' }">
			<c:import url="../../commons/footer.jsp" charEncoding="UTF-8" />
		</c:if>
	</div>
	<!-- /.main-container -->
	<c:import url="../../commons/post-include.jsp" charEncoding="UTF-8" />
	<!-- page specific plugin scripts START -->
	<script type="text/javascript"
		src="${ctx}/static/assets/js/uploadify/jquery.uploadify.min.js">
		
	</script>
	<!-- page specific plugin scripts END -->
	<!-- inline scripts related to this page -->
	<script>
		seajs.use('${ctx}/static/my/js/bizapply/basicapply/main/main');
	</script>
	<script>
		seajs.use('${ctx}/static/my/js/bizapply/basicapply/basicApply/main');
	</script>
	<script>
		seajs.use('${ctx}/static/my/js/bizapply/basicapply/document/main');
	</script>
</body>

</html>