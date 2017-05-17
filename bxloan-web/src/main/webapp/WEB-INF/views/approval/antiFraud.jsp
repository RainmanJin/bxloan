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
		<c:import url="../../commons/pre-include.jsp" charEncoding="UTF-8"/>
		<!-- inline styles related to this page -->
		<script>var $$ctx = "${ctx}/";</script>
	</head>
	<body class="no-skin">
		<div class="main-container" id="main-container">
			<script type="text/javascript">
				try{ace.settings.check('main-container' , 'fixed')}catch(e){}
			</script>
			<div class="page-content">
				<div class="page-header">
					<h1>
						反欺诈情况查询 <small><i class="ace-icon fa fa-angle-double-right"></i> 反欺诈详细信息查看</small>
					</h1>
				</div><!-- /.page-header -->
				<input id="businessNum" type="hidden" value="${businessNum}">
				<div class="row">
					<div class="col-xs-12">
						<div class="form-group">
							<!-- 表格开始 -->
							<table id="tbl_detail" class="table table-striped table-hover" style="width: 100%;">
								<thead>
									<tr>
										<th>序号</th>
										<th>规则名称</th>
										<th>规则类型</th>
										<th>试用范围</th>
										<th>检查状态</th>
										<th style="width: 150px">检查时间</th>
									</tr>
								</thead>
								<tbody>
								</tbody>
							</table>
						</div>
					</div><!-- /.col -->
				</div><!-- /.row -->
			</div><!-- /.page-content -->
		</div><!-- /.main-container -->

		<c:import url="../../commons/post-include.jsp" charEncoding="UTF-8"/>
		<script>
			seajs.use($$ctx + '/static/my/js/approval/antiFraud/main');
		</script>
	</body>

</html>