<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../commons/taglibs.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />

<script type="text/javascript">
	var $$ctx = "${ctx }/";
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
						href="${ctx}"> 主页 </a></li>
					<li>统计查询</li>
					<li>统计签到</li>
				</ul>
			</div>

			<div class="page-content">
				<div class="page-header">
					<h1>
						统计签到 <small> <i class="ace-icon fa fa-angle-double-right"></i>
							签到列表 </small>
					</h1>
				</div>

				<div class="row">
					<div class="col-xs-12">
						<table id="table" class="table table-striped table-hover">
							<thead>
								<tr>
									<th>姓名</th>
									<th>签到地点</th>
									<th>签到时间</th>
									<th>所在机构</th>
									<th>操作</th>
								</tr>
							</thead>
						</table>
					</div>
				</div>
				
				<div id="mapModal" class="modal fade" data-backdrop="static" tabindex="-1">
					<div class="modal-dialog modal-lg">
						<div class="modal-content" style="height:700px;">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h4 class="blue bigger"></h4>
							</div>
							<div class="modal-body">
								<iframe id="map" style="width: 100%;height: 620px;border: 0px;"></iframe>
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
		seajs.use('${ctx}/static/my/js/countRegistration/main');
	</script>

</body>
</html>
