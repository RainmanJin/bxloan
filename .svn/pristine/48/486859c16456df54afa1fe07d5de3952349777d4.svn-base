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
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<script>
	var $$ctx = "${ctx}/";
</script>
<c:import url="../../commons/pre-include.jsp" charEncoding="UTF-8" />
</head>

<body class="no-skin">
	<c:import url="../../commons/navbar.jsp" charEncoding="UTF-8" />
	<div class="main-container" id="main-container">
		<c:import url="../../commons/sidebar.jsp" charEncoding="UTF-8" />
		<div class="main-content">
			<div class="breadcrumbs" id="breadcrumbs">
				<ul class="breadcrumb">
					<li><i class="ace-icon fa fa-home home-icon"></i> <a
						href="${ctx}">主页</a>
					</li>
					<li>统计查询</li>
					<li>合同台账</li>
				</ul>
			</div>
			<div class="page-content">
				<div class="page-header">
					<h1>
						合同台账 <small> <i class="ace-icon fa fa-angle-double-right"></i>
							合同台账列表 </small> <span style="float: right;">
							<button type="button" class="btn btn-sm btn-purple" id="search">
								<i class="ace-icon fa fa-search"></i>查询
							</button>

							<button type="button" class="btn btn-sm btn-default" id="reset">
								<i class="ace-icon fa fa-undo"></i>重置
							</button> </span>
					</h1>
				</div>

				<div class="row" style="height: 0px;">
					<div class="col-xs-12">
						<form class="form-horizontal" id="searchForm">
							<div class="form-group">
								<label class="control-label col-sm-1">所属机构：</label>
								<div class="col-sm-3">
									<input type="text" class="form-control" readonly="readonly">
								</div>
								<label class="control-label col-sm-1">合同编号：</label>
								<div class="col-sm-3">
									<input type="text" class="form-control" name="contractNum">
								</div>
								<label class="control-label col-sm-1"> <span
									style="margin-left: -8px;"> 授信合同编号： </span> </label>
								<div class="col-sm-3">
									<input type="text" class="form-control">
								</div>
							</div>

							<div class="form-group">
								<label class="control-label col-sm-1">放款日期从：</label>
								<div class="col-sm-3">
									<input type="text" class="form-control"
										data-date-format="yyyy-mm-dd" name="payloanDateStart">
								</div>
								<label class="control-label col-sm-1">放款日期至：</label>
								<div class="col-sm-3">
									<input type="text" class="form-control"
										data-date-format="yyyy-mm-dd" name="payloanDateEnd">
								</div>
								<label class="control-label col-sm-1">合同期限：</label>
								<div class="col-sm-3">
									<select class="form-control" name="contractTerm">
										<dd:options codeType="TimeLimitType" />
									</select>
								</div>
							</div>

							<div class="form-group">
								<label class="control-label col-sm-1">客户名称：</label>
								<div class="col-sm-3">
									<input type="text" class="form-control" name="customerName">
								</div>
								<label class="control-label col-sm-1">客户编码：</label>
								<div class="col-sm-3">
									<input type="text" class="form-control" name="customerNum">
								</div>
								<label class="control-label col-sm-1">客户类型：</label>
								<div class="col-sm-3">
									<select class="form-control" name="customerType">
										<dd:options codeType="CustomerType" />
									</select>
								</div>
							</div>

							<div class="form-group">
								<label class="control-label col-sm-1">客户标识：</label>
								<div class="col-sm-3">
									<select class="form-control">
										<dd:options codeType="CustomerTypeCG" />
									</select>
								</div>
								<label class="control-label col-sm-1">担保方式：</label>
								<div class="col-sm-3">
									<select class="form-control" name="guaranteeMode">
										<dd:options codeType="GuaranteeTypeCode" />
									</select>
								</div>
								<label class="control-label col-sm-1">合同状态：</label>
								<div class="col-sm-3">
									<select class="form-control" name="contractStatusCd">
										<dd:options codeType="ContractStatusCode" />
									</select>
								</div>
							</div>

							<div class="form-group">
								<label class="control-label col-sm-1">客户经理：</label>
								<div class="col-sm-3">
									<input type="text" class="form-control" name="customerManagerName">
								</div>
								<label class="control-label col-sm-1">资产状态：</label>
								<div class="col-sm-3">
									<select class="form-control">
										<dd:options codeType="AssetsStatusCode" />
									</select>
								</div>
								<label class="control-label col-sm-1">是否有保险：</label>
								<div class="col-sm-3">
									<select class="form-control" name="ifInsure">
										<dd:options codeType="CommonWhether" />
									</select>
								</div>
							</div>

							<div class="form-group">
								<label class="control-label col-sm-1"> <font size="1">
										<span style="margin-left: -24px;"> 是否总部协同业务 </span> </font>： </label>
								<div class="col-sm-3">
									<select class="form-control" name="isheadcol">
										<dd:options codeType="CommonWhether" />
									</select>
								</div>
							</div>

						</form>
					</div>
				</div>

				<div class="row">
					<div class="col-md-12">
						<table id="table" class="table table-striped table-hover">
							<thead>
								<tr>
									<th>合同编号</th>
									<th>客户名称</th>
									<th>贷款产品</th>
									<th>合同状态</th>
									<th>合同金额</th>
									<th>合同期限</th>
									<th>合同余额</th>
									<th>担保方式</th>
									<th>放款日期</th>
									<th>资产状态</th>
									<th>转出协议名称</th>
									<th>客户经理</th>
									<th>操作</th>
								</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>

		</div>
		<c:import url="../../commons/footer.jsp" charEncoding="UTF-8" />
	</div>
	<c:import url="../../commons/post-include.jsp" charEncoding="UTF-8" />

	<script>
		seajs.use('${ctx}/static/my/js/contractStandingBook/main');
	</script>
</body>
</html>