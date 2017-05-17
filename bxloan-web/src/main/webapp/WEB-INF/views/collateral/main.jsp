<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../commons/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<base href="${ctx}/">
<title>${title }</title>
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
					<li>抵质押管理</li>
				</ul>
			</div>
			<div class="page-content">
				<div class="page-header">
					<h1>
						抵质押管理 
						<small>
							<i class="ace-icon fa fa-angle-double-right"></i>
							抵质押列表
						</small>
					</h1>
				</div>

				<div class="row">
					<div class="col-xs-12">
						<form class="form-inline" role="form">
							<div class="form-group">
								<label class="sr-only" for="guarantorName"></label> 抵质押人名称：<input
									type="text" class="form-control" style="width:auto;" id="guarantorName">
							</div>
							<div class="form-group">
								<label class="sr-only" for="guarantyName"></label> 抵质押品名称：<input
									type="text" class="form-control" style="width:auto;" id="guarantyName">
							</div>

							<div class="form-group">
								<label class="sr-only" for="guarantyStatusCd"></label> 抵质押物状态： <select
									id="guarantyStatusCd" class="form-control" style="width:auto;">
									<option value="all">全部</option>
									<dd:options codeType="RevGuaranteeStatusCd" />
								</select>
							</div>

							<button type="button" class="btn btn-sm btn-purple" id="search">
								<i class="ace-icon fa fa-search"></i>查询
							</button>

							<button type="button" class="btn btn-sm btn-default" id="reset">
								<i class="ace-icon fa fa-undo"></i>重置
							</button>

							<button type="button" class="btn btn-sm btn-success" role="add">
								<i class="ace-icon fa fa-plus"></i> 新增
							</button>
						</form>

						<div class="row">
							<div class="col-xs-12">
								<table id="table" class="table table-striped table-hover">
									<thead>
										<tr>
											<th>抵质押物编号</th>
											<th>抵质押人名称</th>
											<th>抵质押物名称</th>
											<th>抵质押物类型</th>
											<th>评估价值</th>
											<th>已设定担保金额</th>
											<th>抵质押物状态</th>
											<th>操作</th>
										</tr>
									</thead>
								</table>
							</div>
						</div>
					</div>
				</div>

			</div>

		</div>
		<c:import url="../../commons/footer.jsp" charEncoding="UTF-8" />
	</div>
	<c:import url="../../commons/post-include.jsp" charEncoding="UTF-8" />

	<script>
		seajs.use('${ctx}/static/my/js/collateral/main/main');
	</script>
</body>
</html>