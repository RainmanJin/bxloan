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
					<li>
						<i class="ace-icon fa fa-home home-icon"></i>
						<a href="${ctx}">主页</a>
					</li>
					<li>贷前管理</li>
					<li>授信合同管理</li>
				</ul>
			</div>
			<div class="page-content">
				<div class="page-header">
					<h1>
						授信合同管理
						<small>
							<i class="ace-icon fa fa-angle-double-right"></i>
						授信合同列表
						</small>
						<span style="float: right;">
							<button type="button" class="btn btn-sm btn-purple" id="creditSearch">
								<i class="ace-icon fa fa-search"></i>查询
							</button>

							<button type="button" class="btn btn-sm btn-default" id="contractReset">
								<i class="ace-icon fa fa-undo"></i>重置
							</button> </span>
					</h1>
				</div>

				<div class="row" style="height: 0px;">
					<div class="col-xs-12">
						<form class="form-horizontal" id="searchForm">
							<div class="form-group">
								<label class="control-label col-sm-1 no-padding-right">授信合同编号：</label>
								<div class="col-sm-2">
									<input type="text" id="contractNum" name="contractNum" class="form-control col-sm-5 input-sm"  placeholder="请输入授信合同编号">
								</div>
								<label class="control-label col-sm-1">客户名称：</label>
								<div class="col-sm-2">
									<input type="text" id="customerName" name="customerName" class="form-control col-sm-5 input-sm" placeholder="请输入客户名称">
								</div>
								<label class="control-label col-sm-1">授信类型：</label>
								<div class="col-sm-2">
									<select class="form-control col-sm-5 input-sm creditType" id="creditType" name="creditType"></select>
								</div>
							</div>
						</form>
					</div>
				</div>

				<div class="row">
					<div class="col-md-12">
						<table id="creditContractTable" class="table table-striped table-hover">
							<thead>
								<tr>
									<th style="width:12%">授信合同编号</th>
									<th style="width:16%">客户名称</th>
									<th style="width:12%">客户编号</th>
									<th style="width:10%">贷款产品</th>
									<th style="width:8%">授信额度（元）</th>
									<th style="width:8%">授信期限</th>
									<th style="width:8%">授信利率（%）</th>
									<th style="width:8%">授信类型</th>
									<th style="width:7%">合同状态</th>
									<th style="width:11%;align:center">操作</th>
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
	<script type="x-tmpl-mustache" id="dt-row-operation">
		<div>
				<button class="btn btn-info btn-xs" data-cid="{{cid}}" data-pid="{{pid}}" data-placement="left" data-toggle="tooltip" role="apply" title="发起借款申请">
					<i class="ace-icon fa fa-jpy"></i>
				</button>
				<button class="btn btn-warning btn-xs" data-cid="{{cid}}" data-pid="{{pid}}" data-placement="left" data-toggle="tooltip" role="change" title="担保方式变更">
					<i class="ace-icon fa fa-gavel"></i>
				</button>
				<button class="btn btn-danger btn-xs" data-cid="{{cid}}" data-pid="{{pid}}" data-placement="left" data-rel="tooltip" role="abolish" title="授信合同废除">
					<i class="ace-icon fa fa-trash-o"></i>
				</button>
		</div>
	</script>
	<script>
		seajs.use('${ctx}/static/my/js/creditContractMng/main');
	</script>
</body>
</html>