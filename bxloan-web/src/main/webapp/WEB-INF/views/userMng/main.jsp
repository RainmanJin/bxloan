<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
		
		<c:import url="../../commons/pre-include.jsp" charEncoding="UTF-8" />
		
		<!-- page specific plugin styles START -->
		<!-- page specific plugin styles END -->
		
		<!-- inline styles related to this page -->
		<script>var $$ctx = "${ctx}/";</script>
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
							<i class="ace-icon fa fa-home home-icon"></i>
							<a href="${ctx}">主页</a>
						</li>
						<li class="active">用户管理</li>
					</ul><!-- /.breadcrumb -->

					<!--
					<div class="nav-search" id="nav-search">
						<form class="form-search">
							<span class="input-icon">
								<input type="text" placeholder="Search ..." class="nav-search-input" id="nav-search-input" autocomplete="off" />
								<i class="ace-icon fa fa-search nav-search-icon"></i>
							</span>
						</form>
					</div> 
					-->
				</div>

				<div class="page-content">
					
					<%-- <c:import url="../../commons/settings.jsp" charEncoding="UTF-8" /> --%>

					<div class="page-header">
						<h1>
							用户管理 <small><i class="ace-icon fa fa-angle-double-right"></i> 用户列表</small>
							<span class="my-button-group">
								<button id="btn-query" class="btn btn-sm btn-purple"><i class="ace-icon fa fa-search"></i> 精确查询</button>
								<button id="btn-add" class="btn btn-sm btn-success"><i class="ace-icon fa fa-plus"></i> 新增</button>
							</span>
						</h1>
					</div><!-- /.page-header -->
					
					<div class="row">
						<div class="col-xs-12">
							<div class="row">
								<div class="col-xs-12">
									<table id="tbl" class="table table-striped table-hover">
										<thead>
											<tr>
												<th>序号</th>
												<th>登录名</th>
												<th>用户名</th>
												<th>角色名</th>
												<th>操作</th>
											</tr>
										</thead>
										<tbody>
										</tbody>
									</table>
								</div>
							</div><!-- /.row -->
							<div id="modal-form" class="modal" tabindex="-1">
								<div class="modal-dialog">
									<div class="modal-content">
										<form id="userForm" action="userMng/save" class="form-horizontal" role="form" method="post">
											<div class="modal-header">
												<button type="button" class="close" data-dismiss="modal">&times;</button>
												<h4 class="blue bigger"></h4>
											</div>
											<div class="modal-body">
												<div class="row">
													<div class="col-xs-12">
														<input type="hidden" id="form-field-0" name="id" />
														<div class="form-group">
															<label class="col-sm-2 control-label no-padding-right" for="form-field-1"> 登录名 </label>
															<div class="col-sm-8">
																<input type="text" id="form-field-1" name="username" class="form-control required" />
															</div>
														</div>
														<div class="form-group">
															<label class="col-sm-2 control-label no-padding-right" for="form-field-2"> 中文名 </label>
															<div class="col-sm-8">
																<input type="text" id="form-field-2" name="name" class="form-control required" />
															</div>
														</div>
													</div>
												</div>
											</div>
											<div class="modal-footer">
												<button type="submit" class="btn btn-sm btn-primary">
													<i class="ace-icon fa fa-save"></i> 保存
												</button>
												<button class="btn btn-sm" data-dismiss="modal">
													<i class="ace-icon fa fa-times"></i> 取消
												</button>
											</div>
										</form>
									</div>
								</div>
							</div>
						</div>
					</div>
					
					<div class="row">
						<div class="col-xs-12">
							<button id="btn-getSelectedNode" class="btn btn-sm btn-success"><i class="ace-icon"></i> 获取选中节点</button>
							<ul id="treeDemo" class="ztree"></ul>
						</div>
					</div>
					
				</div><!-- /.page-content -->
			</div><!-- /.main-content -->
			
			<c:import url="../../commons/footer.jsp" charEncoding="UTF-8" />
			
		</div><!-- /.main-container -->

		<c:import url="../../commons/post-include.jsp" charEncoding="UTF-8" />
		
		<!-- page specific plugin scripts START -->
		<!-- page specific plugin scripts END -->

		<!-- inline scripts related to this page -->
		<script>
			seajs.use('${ctx}/static/my/js/userMng/main');
		</script>
	</body>

</html>