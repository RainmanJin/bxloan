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
<!-- page specific plugin styles END -->

<!-- inline styles related to this page -->
<script>
	var $$ctx = "${ctx}/";
</script>
<body class="no-skin">
	<c:import url="../../commons/navbar.jsp" charEncoding="UTF-8" />

	<div class="main-container" id="main-container">
		<script type="text/javascript">
			try {
				ace.settings.check('main-container', 'fixed')
			} catch (e) {
			}
		</script>

		<c:import url="../../commons/sidebar.jsp" charEncoding="UTF-8" />

		<div class="main-content">
			<div class="breadcrumbs" id="breadcrumbs">
				<ul class="breadcrumb">
					<li>
						<i class="ace-icon fa fa-home home-icon"></i>
						<a href="${ctx}">主页</a>
					</li>
					<li>系统管理</li>
					<li>产品定价设置</li>
				</ul>
			</div>

			<div class="page-content">
				<div class="page-header">
					<table style="width: 100%;">
						<tr>
							<td>
								<h1>
									产品定价设置 <small><i
										class="ace-icon fa fa-angle-double-right"></i> 产品定价设置列表</small>
								</h1></td>
							<td width="10%"><span>
									<button id="btn-add" class="btn btn-sm btn-success">
										<i class="ace-icon fa fa-plus"></i> 新增
									</button> </span></td>
						</tr>
					</table>
				</div>

				<div class="row">
					<div class="col-md-12">
						<table id="tbl" class="table table-striped table-hover">
							<thead>
								<tr>
									<th>贷款产品</th>
									<th>贷款期限（月）</th>
									<th>偿还周期</th>
									<th>利率（%）</th>
									<th>逾期利率上浮比例（%）</th>
									<th>挪用利率上浮比例（%）</th>
									<th>提前还款违约金比例（%）</th>
									<th style="width: 12%">操作</th>
								</tr>
							</thead>
							<tbody>
							</tbody>
						</table>
					</div>
				</div>

			</div>
		</div>

		<div id="modal-productForm" class="modal" data-backdrop="static"
			tabindex="-1">
			<div class="modal-dialog">
				<div class="modal-content">
					<form id="productForm" action="sysmng/save" class="form-horizontal"
						role="form" method="post">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
							<h4 class="blue bigger"></h4>
						</div>
						<div class="modal-body">
							<div class="row">
								<div class="col-xs-12">
									<input type="hidden" id="form-field-0" name="id" />
									<div class="form-group">
										<label
											class="col-xs-12 col-sm-4 control-label no-padding-right">
											<font color='red'> * </font>贷款产品 </label>
										<div class="col-xs-12 col-sm-7" style="width: 58.5%;">
											<span class="block input-icon input-icon-right">
												<div class="input-group">
													<input type="text" id="productCdMask" name="productCdMask"
														class="form-control required" readonly="readonly">
													<input type="hidden" id="loanProduct" name="loanProduct"
														class="form-control"> <input type="hidden"
														id="parentProductCd"> <span
														class="input-group-btn">
														<button id="showTree" class="btn btn-sm btn-yellow"
															type="button">
															<i class="ace-icon fa fa-eye"></i>
														</button> </span>
												</div> </span>
											<div id="controlZTree" style="display:none;">
												<div class="col-xs-12"
													style="overflow-y: auto;max-height: 500px;position:absolute;z-index:999;background:#fff;border:1px solid #e3e3e3;width: 93.5%;">
													<ul id="tree" class="ztree">
													</ul>
												</div>
											</div>
										</div>
										<div class="help-block col-xs-12 col-sm-reset inline"></div>
									</div>

									<div class="form-group">
										<label
											class="col-xs-12 col-sm-4 control-label no-padding-right">
											<font color='red'> * </font>贷款期限 </label>
										<div class="col-xs-12 col-sm-7">
											<span class="block input-icon input-icon-right"> <select
												id="loanTerm" name="loanTerm" class="form-control">
													<dd:options codeType="LoanTerm" />
											</select> </span>
										</div>
										<div class="help-block col-xs-12 col-sm-reset inline"></div>
									</div>
									<div class="form-group">
										<label
											class="col-xs-12 col-sm-4 control-label no-padding-right">
											<font color='red'> * </font>利率（%） </label>
										<div class="col-xs-12 col-sm-7">
											<span class="block input-icon input-icon-right"> <input
												type="text" id="rate" name="rate"
												class="form-control" /> </span>
										</div>
										<div class="help-block col-xs-12 col-sm-reset inline"></div>
									</div>
									<div class="form-group">
										<label
											class="col-xs-12 col-sm-4 control-label no-padding-right">
											<font color='red'> * </font>逾期利率上浮比例（%） </label>
										<div class="col-xs-12 col-sm-7">
											<span class="block input-icon input-icon-right"> <input
												type="text" id="overdueRate" name="overdueRate"
												class="form-control" /> </span>
										</div>
										<div class="help-block col-xs-12 col-sm-reset inline"></div>
									</div>
									<div class="form-group">
										<label
											class="col-xs-12 col-sm-4 control-label no-padding-right">
											<font color='red'> * </font>挪用利率上浮比例（%）</label>
										<div class="col-xs-12 col-sm-7">
											<span class="block input-icon input-icon-right"> <input
												type="text" id="perculNegoRate" name="perculNegoRate"
												class="form-control" /> </span>
										</div>
										<div class="help-block col-xs-12 col-sm-reset inline"></div>
									</div>
									<div class="form-group">
										<label
											class="col-xs-12 col-sm-4 control-label no-padding-right">
											<font color='red'> * </font>提前还款违约金比例（%）</label>
										<div class="col-xs-12 col-sm-7">
											<span class="block input-icon input-icon-right"> <input
												type="text" id="earlyRepayment" name="earlyRepayment"
												class="form-control" /> </span>
										</div>
										<div class="help-block col-xs-12 col-sm-reset inline"></div>
									</div>
									<div class="form-group">
										<label
											class="col-xs-12 col-sm-4 control-label no-padding-right">
											<font color='red'> * </font>还款方式</label>
										<div class="col-xs-12 col-sm-7">
											<span class="block input-icon input-icon-right"> <c:forEach
													items="${repaymentModes }" var="repaymentMode">
													<label class="checkbox-inline"> <input
														type="checkbox" class="ace add_corp_Type form-control"
														value="${repaymentMode.codeValue }" /> <span class="lbl">${repaymentMode.codeName
															}</span> </label>
													<br>
												</c:forEach> </span> <input type="hidden" id="repaymentType"
												name="repaymentType">
										</div>
										<div class="help-block col-xs-12 col-sm-reset inline"></div>
									</div>
									<div class="form-group">
										<label
											class="col-xs-12 col-sm-4 control-label no-padding-right">
											<font color='red'> * </font>还款周期</label>
										<div class="col-xs-12 col-sm-7">
											<span class="block input-icon input-icon-right"> <input
												type="text" id="repaymentCucle" name="repaymentCucle"
												class="form-control" /> </span>
										</div>
										<div class="help-block col-xs-12 col-sm-reset inline"></div>
									</div>
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<button type="submit" class="btn btn-sm btn-primary">
								<i class="ace-icon fa fa-save"></i> 保存
							</button>
						</div>
					</form>
				</div>
			</div>
		</div>

		<c:import url="../../commons/footer.jsp" charEncoding="UTF-8" />

	</div>

</body>
<!-- /.main-container -->

<c:import url="../../commons/post-include.jsp" />

<!-- page specific plugin scripts START -->
<!-- page specific plugin scripts END -->

<!-- inline scripts related to this page -->
<script>
	seajs.use('${ctx}/static/my/js/sysmng/productprice/main');
</script>
</head>
</body>

</html>