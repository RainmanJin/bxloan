<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../../commons/taglibs.jsp"%>
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
<c:import url="../../../commons/pre-include.jsp" charEncoding="UTF-8" />
</head>

<body class="no-skin">
	<c:import url="../../../commons/navbar.jsp" charEncoding="UTF-8" />
	<div class="main-container" id="main-container">
		<c:import url="../../../commons/sidebar.jsp" charEncoding="UTF-8" />
		<div class="main-content">
			<div class="breadcrumbs" id="breadcrumbs">
				<ul class="breadcrumb">
					<li>
						<i class="ace-icon fa fa-home home-icon"></i>
						<a href="${ctx}">主页</a>
					</li>
					<li>系统管理</li>
					<li>产品管理</li>
				</ul>
			</div>
			<div class="page-content">
				<div class="page-header">
					<h1>
						产品管理
						<small>
							<i class="ace-icon fa fa-angle-double-right"></i>
						产品列表
						</small>
					</h1>
				</div>
				
				<div class="row">
					<div class="col-md-12">
						<form class="form-inline" role="form">
							<div class="form-group">
								<label class="sr-only" for="productName"></label> 贷款产品：<input
									type="text" class="form-control" style="width:auto;" id="productName">
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
							<div class="col-md-12">
								<table id="table" class="table table-striped table-hover">
									<thead>
										<tr>
											<th>客户类型</th>
											<th>贷款产品</th>
											<th>适用机构</th>
											<th>是否启用</th>
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
		<c:import url="../../../commons/footer.jsp" charEncoding="UTF-8" />
	</div>
	<c:import url="../../../commons/post-include.jsp" charEncoding="UTF-8" />

	<script>
		seajs.use('${ctx}/static/my/js/sysmng/productmng/main/main');
	</script>
</body>
</html>