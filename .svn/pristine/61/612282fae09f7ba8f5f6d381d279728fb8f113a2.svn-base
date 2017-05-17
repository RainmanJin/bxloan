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
					<li>统计查询</li>
					<li>业务查询</li>
				</ul>
			</div>
			<div class="page-content">
				<div class="page-header">
					<h1>
						业务查询
						<small>
							<i class="ace-icon fa fa-angle-double-right"></i>
						业务列表
						</small>
						<span style="float: right;">
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
						<form class="form-horizontal" id="searchForm" role="contract_searchform">
							<div class="form-group">
								<label class="control-label col-sm-1">所属机构：</label>
								<div class="col-sm-3">
								<c:choose>
									<c:when test="${orgLevel == 1}">
										<select id="orgId" name="orgId" class="form-control col-sm-5 input-sm">
											<option value="">全部</option>
											<c:forEach items="${orgs}" var="org">
												<option value="${org.id }">${org.name}</option>
											</c:forEach>
										</select>
									</c:when>
									<c:when test="${orgLevel == 2}">
										<select id="orgId" name="orgId" class="form-control col-sm-5 input-sm" disabled="disabled">
											<c:forEach items="${orgs}" var="org">
												<option value="${org.id }">${org.name}</option>
											</c:forEach>
										</select>
									</c:when>
									<c:otherwise>
										<select id="orgId" name="orgId" class="form-control col-sm-5 input-sm" disabled="disabled">
											<option value="1">${orgName}</option>
										</select>
									</c:otherwise>
								</c:choose>
							</div>
								<label class="control-label col-sm-1">贷款产品：</label>
								<div class="col-sm-3">
									<span class="block input-icon input-icon-right">
										<div class="input-group">
											<input type="text" id="productCdMask" name="productCdMask" class="form-control" readonly="readonly">
											<input type="hidden" id="productType" name="productType" class="form-control">
											<input type="hidden" id="parentProductCd">
											<span class="input-group-btn">
												<button id="showTree" class="btn btn-sm btn-yellow" type="button">
													<i class="ace-icon fa fa-eye"></i>
												</button>
											</span>
										</div>
									</span>
									<div id="controlZTree" style="display:none;">
										<div class="col-xs-12" style="overflow-y: auto;max-height: 500px;position:absolute;z-index:999;background:#fff;border:1px solid #e3e3e3;width: 93.5%;">
											<ul id="tree" class="ztree"></ul>
										</div>
									</div>
								</div>
								<label class="control-label col-sm-1">业务编号：</label>
								<div class="col-sm-3">
									<input type="text" class="form-control" id="projectNo">
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-sm-1">客户名称：</label>
								<div class="col-sm-3">
									<input type="text" class="form-control" id="customerName">
								</div>
								<label class="control-label col-sm-1">业务状态：</label>
								<div class="col-sm-3">
									<select id="projectStatus" class="form-control">
										<option value="">全部</option>
										<dd:options codeType="ProjectStatus" excludes="3,6" /><!-- 去掉【中止】【已终止】两个状态 -->
									</select>
								</div>
								<label class="control-label col-sm-1">申请日期：</label>
								<div class="col-xs-12 col-sm-3">
									<span class="block input-icon input-icon-right">
										<div class="input-group">
											<input type="text" class="form-control" id="applyDateStart" name="applyDateStart" data-date-format="yyyy-mm-dd">
											<span class="input-group-addon">至</span>
											<input type="text" class="form-control" id="applyDateEnd" name="applyDateEnd" data-date-format="yyyy-mm-dd">
										</div>
									</span>
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-sm-1">
									申请金额：
								</label>
								<div class="col-xs-12 col-sm-3">
									<span class="block input-icon input-icon-right">
										<div class="input-group">
											<input type="text" class="form-control isPositiveNumberTwo" id="applyAmtMin" name="applyAmtMin">
											<span class="input-group-addon">至</span>
											<input type="text" class="form-control isPositiveNumberTwo" id="applyAmtMax" name="applyAmtMax">
											<span class="input-group-addon">元</span>
										</div>
									</span>
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
									<th>业务编号</th>
									<th>贷款产品</th>
									<th>所属机构</th>
									<th>客户名称</th>
									<th>申请金额（元）</th>
									<th>申请日期</th>
									<th>客户经理</th>
									<th>业务状态</th>
									<th>业务流程</th>
								</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>
		</div>
		<div id="detail-modal" class="modal" tabindex="-1">
			<div class="modal-dialog" style="width:50%">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="blue bigger">
							<i class='ace-icon fa fa-search'></i>流程详细信息
						</h4>
					</div>
					<div class="modal-body">
						<div class="row">
							<div class="col-xs-12 col-sm-10 col-sm-offset-1">
								<div class="timeline-container">
									<div class="timeline-label">
										<span class="label label-primary arrowed-in-right label-lg">最新状态</span>
									</div>
									<div class="timeline-items" id="wfDetailWarp">
										<div class="timeline-item clearfix">
											<div class="timeline-info">
												<i class="timeline-indicator ace-icon fa fa-hand-o-right btn btn-success no-hover"></i>
											</div>
											<div class="widget-box transparent">
												<div class="widget-header widget-header-small">
													<h5 class="widget-title smaller"><span class="grey">录入业务申请信息</span></h5>
													<span class="widget-toolbar no-border">
														<i class="ace-icon fa fa-clock-o bigger-110"></i>2014-08-29 16:22
													</span>
												</div>
												<div class="widget-body">
													<div class="widget-main">无审批意见</div>
												</div>
											</div>
										</div>
									</div><!-- /.timeline-items -->
								</div><!-- /.timeline-container -->
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button class="btn btn-sm btn-default" data-dismiss="modal">
							<i class="ace-icon fa fa-times"></i> 取消
						</button>
					</div>
				</div>
			</div>
		</div>
		<!-- 待处理 & 处理中 -->
		<script type="x-tmpl-mustache" id="dt-row-operation-02">
		<div style="width:160px;">
			<button class="btn btn-info btn-xs" role="showWorkDetail" 
				data-workflowid="{{workflowId}}" 
				data-toggle="tooltip"  title="查看进度">
				<i class="ace-icon fa fa-eye"></i>
			</button>
		</script>
		
		<c:import url="../../commons/footer.jsp" charEncoding="UTF-8" />
	</div>
	<c:import url="../../commons/post-include.jsp" charEncoding="UTF-8" />

	<script>
		seajs.use('${ctx}/static/my/js/querybusiness/main');
	</script>
</body>
</html>