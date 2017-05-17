<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../../commons/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="zh">
<head>
	<base href="${ctx}/">
	<title>${title }</title>
	<meta charset="utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	
	<meta name="description" content="Dashboard page" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
	<c:import url="../../commons/pre-include.jsp" charEncoding="UTF-8"/>
	<script>var $$ctx = "${ctx}/";</script>
	<style type="text/css">
	.chosen-container-multi .chosen-choices li.search-choice .search-choice-close{
		background-image: none;
	}
	</style>
</head>
<body class="no-skin">
	<c:import url="../../commons/navbar.jsp" charEncoding="UTF-8" />
		<div class="main-container" id="main-container">
		<script type="text/javascript">
			try{ace.settings.check('main-container' , 'fixed')}catch(e){}
		</script>
	
		<c:import url="../../commons/sidebar.jsp" charEncoding="UTF-8" />
		
		<div class="main-content">
			<div class="breadcrumbs" id="breadcrumbs">
				<script type="text/javascript">
					try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
				</script>

				<ul class="breadcrumb">
					<li>
						<a href="${ctx}">主页</a>
					</li>
					<li class="active">统计查询</li>
					<li class="active">审批台账</li>
				</ul><!-- /.breadcrumb -->
			</div>

			<div class="page-content">
				<form class="form-horizontal" role="searchForm">
				<div class="page-header">
					<h1>
						审批台账<small><i class="ace-icon fa fa-angle-double-right"></i> 业务列表</small>
						<span class="pull-right">
							<button id="btn-query" class="btn btn-sm btn-purple" type="button"> <i class="ace-icon fa fa-search"></i> 查询 </button>
	                       	<button id="btn-reset" class="btn btn-sm btn-default" type="reset"> <i class="ace-icon fa fa-undo"></i> 重置 </button>
	                       	<button id="btn-download" class="btn btn-sm btn-yellow" type="button"> <i class="ace-icon fa fa-download"></i> 下载  </button>
						</span>
					</h1>
				</div>
				<!-- Search start -->
				<div class="row" style="height: 0px;">
					<div class="col-xs-12">
						<div class="form-group">
							<div class="col-lg-3 col-md-4">
								<div class="input-group">
									<span class="input-group-addon">业务时间</span>
									<input type="text" name="startDate" placeholder="开始时间" class="form-control input-datepicker">
								</div>
							</div>
							<div class="col-lg-3 col-md-4">
								<div class="input-group">
									<span class="input-group-addon">至</span>
									<input type="text" name="endDate" placeholder="截止时间" class="form-control input-datepicker">
								</div>
							</div>
						</div>
						<div class="form-group">
							<%-- <label class="control-label col-md-1">客户名称：</label>
							<div class="col-md-6">
								<select multiple="multiple" class="form-control" name="orgIds">
									<option value="all">全部</option>
									<c:forEach items="${orgs}" var="obj">
										<option value="${obj.id}">${obj.description}</option>
									</c:forEach>
								</select>
							</div> --%>
							<div class="col-md-8">
								<div class="input-group">
									<span class="input-group-addon">小贷公司</span>
									<select multiple="multiple" class="form-control chosen-select2" name="orgIds" data-placeholder="请选择机构">
										<option value="all" selected="selected">全部</option>
										<c:forEach items="${orgs}" var="obj">
											<option value="${obj.id}">${obj.description}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
					</div>
				</div>
				</form>
				<!-- Search end -->
				<div class="row">
					<div class="col-xs-12">
						<table id="tbl" class="table table-striped table-hover">
							<thead>
								<tr>
									<th>业务编号</th>
									<th>客户名称</th>
									<th>业务类型</th>
									<th>贷款类型</th>
									<th>贷款产品</th>
									<th>申请金额</th>
									<th>业务日期</th>
									<th>客户经理</th>
									<th>小贷公司</th>
								</tr>
							</thead>
							<tbody>
							</tbody>
						</table>
					</div>
				</div><!-- /.row -->
			</div><!-- /.page-content -->
		</div><!-- /.main-content -->
			
		<c:import url="../../commons/footer.jsp" charEncoding="UTF-8" />
		
	</div><!-- /.main-container -->
	<c:import url="../../commons/post-include.jsp" charEncoding="UTF-8"/>
	<script>
		seajs.use('${ctx}/static/my/js/statistics/bizApproveAccount/main');
	</script>
</body>
</html>