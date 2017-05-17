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
		
		<!-- page specific plugin styles START -->
		<!-- page specific plugin styles END -->
		
		<!-- inline styles related to this page -->
		<script>var $$ctx = "${ctx}/";</script>
	</head>
	<body class="no-skin">
		<%-- <c:import url="../../commons/navbar.jsp" charEncoding="UTF-8"/> --%>
		
		<div class="main-container" id="main-container">
			<script type="text/javascript">
				try{ace.settings.check('main-container' , 'fixed')}catch(e){}
			</script>
		
			<%-- <c:import url="../../commons/sidebar.jsp" charEncoding="UTF-8"/> --%>
			

				<div class="page-content">
					
					<%-- <c:import url="../../commons/settings.jsp" /> --%>

					<div class="page-header">
						<h1>
							审批流程 <small><i class="ace-icon fa fa-angle-double-right"></i> 过程意见查询</small>
						</h1>
					</div><!-- /.page-header -->
					<input id="projectId" type="hidden" value="${projectId}">
					<input id="workflowId" type="hidden" value="${workflowId}">
					<div class="row">
						<div class="col-xs-12">
							<div class="form-group">
								<div class="page-content">
									<div class="row">
										<div class="col-sm-12">
											<table class="table table-striped table-hover table-bordered">
												<thead>
												<tr>
												<th width="40%">意见</th>
												<th>流程环节</th>
												<th>创建人</th>
												<th>接收人</th>
												<th>提交时间</th>
												<th>状态</th>
												</tr>		
												</thead>
												
												<tbody id="tbody">
												</tbody>								
											</table>
										</div>
									</div>
								</div>
							</div>
						</div><!-- /.col -->
					</div><!-- /.row -->
				</div><!-- /.page-content -->
			
			
		</div><!-- /.main-container -->

		<c:import url="../../commons/post-include.jsp" charEncoding="UTF-8"/>
		
		<!-- page specific plugin scripts START -->
		<!-- page specific plugin scripts END -->

		<!-- inline scripts related to this page -->
		<script>
			seajs.use($$ctx + '/static/my/js/approval/processOpinion/main');
		</script>
	</body>

</html>
