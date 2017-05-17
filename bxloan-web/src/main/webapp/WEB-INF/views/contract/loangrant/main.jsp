<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
%>
	<%@include file="../../../commons/taglibs.jsp" %>
		<!DOCTYPE html>
		<html lang="zh">
			<head>
				<base href="${ctx}/">
				<title>
					邦信微贷
				</title>
				<meta charset="utf-8" />
				<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
				<meta name="description" content="Dashboard page" />
				<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0"
				/>
				<c:import url="../../../commons/pre-include.jsp" charEncoding="UTF-8"
				/>
				<!-- page specific plugin styles START -->
				<script>
					var $$ctx = "${ctx}/";
				</script>
				<style type="text/css">
					#tbContracts ul,#tbl li{
	 background-color :#fff;
	 max-width:110px;
	}
	#tbContracts li{
	 text-align: left;
	}
	#tbContracts a{
	 padding-left: 5px;
	 padding-right: 5px;
	 }
	#tbContracts ul{
	 margin-top: 0px;
	 padding: 0px;
	 min-width: 105px!important;
	}
	#tbContracts span.line_span{
	 min-width: 25px;
	}
				</style>
			</head>
			<body class="no-skin">
				<c:import url="../../../commons/navbar.jsp" charEncoding="UTF-8" />
				<div class="main-container" id="main-container">
					<script type="text/javascript">
						try {
							ace.settings.check('main-container', 'fixed')
						} catch(e) {}
					</script>
					<c:import url="../../../commons/sidebar.jsp" charEncoding="UTF-8" />
					<div class="main-content">
						<div class="breadcrumbs" id="breadcrumbs">
							<script type="text/javascript">
								try {
									ace.settings.check('breadcrumbs', 'fixed')
								} catch(e) {}
							</script>
							<ul class="breadcrumb">
								<li>
									<i class="ace-icon fa fa-home home-icon">
									</i>
									<a href="${ctx}">
										主页
									</a>
								</li>
								<li class="active">
									贷前管理
								</li>
								<li class="active">
									贷款发放
								</li>
							</ul>
							<!-- /.breadcrumb -->
							<!-- <div class="nav-search" id="nav-search">
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
							<!-- 隐藏域 -->
							<div>
								<input type="hidden" id="projectIdCwField" value="" />
								<input type="hidden" id="cuserIdField" value="${cuserId}" />
							</div>
							<%-- <c:import url="../commons/settings.jsp" charEncoding="UTF-8" />
							--%>
							<div class="page-header">
								<h1>
									贷款发放
									<small>
										<i class="ace-icon fa fa-angle-double-right">
										</i>
										合同情况
									</small>
									<span style="float:right;">
										<button id="btn-query" class="btn btn-sm btn-purple" type="button">
											<i class="ace-icon fa fa-search">
											</i>
											查询
										</button>
										<button id="btn-reset" class="btn btn-sm btn-default" type="button">
											<i class="ace-icon fa fa-undo">
											</i>
											重置
										</button>
									</span>
								</h1>
							</div>
							<!-- /.page-header -->
							<!-- 条件搜索 -->
							<div class="row" id="condSearchDiv" style="margin:auto;height:40px;">
								<form id="exec_search_form">
									<span style="width:100%;">
										客户名称:
										<input id="customerName" name="customerName" placeholder="输入客户名称" class="form-group"
										/>
										合同放款状态:
										<select id="payloanStatus" name="payloanStatus" class="form-group">
											<option value="">
												全部
											</option>
											<dd:options codeType="ContractFindCd" />
										</select>
										<%-- 是否以上传借据： <select id="hasUploadIou" name="hasUploadIou" class="form-group">
											<dd:options codeType="CommonWhether" />
											</select>
											--%>
									</span>
								</form>
							</div>
							<div class="row">
								<div class="col-xs-12">
									<div class="row">
										<div class="col-xs-12">
											<table id="tbContracts" class="table table-striped table-hover dataTable"
											style="width:100%!important;">
												<thead>
													<tr>
														<th width="0%">
															选择
														</th>
														<th>
															合同编号
														</th>
														<th>
															客户名称
														</th>
														<th>
															贷款产品
														</th>
														<th>
															合同金额（元）
														</th>
														<th>
															合同可用金额（元）
														</th>
														<th>
															合同余额（元）
														</th>
														<th>
															最近一次还款时间
														</th>
														<th>
															最后上传借据时间
														</th>
														<th width="16%">
															操作
														</th>
														<th width="0%">
															状态
														</th>
													</tr>
												</thead>
												<tbody>
												</tbody>
											</table>
										</div>
									</div>
								</div>
							</div>
							<!-- /.row -->
						</div>
						<!-- /.page-content -->
					</div>
					<!-- /.main-content -->
					<c:import url="../../../commons/footer.jsp" charEncoding="UTF-8" />
				</div>
				<!-- /.main-container -->
				<c:import url="../../../commons/post-include.jsp" charEncoding="UTF-8"
				/>
				<!-- page specific plugin scripts START -->
				<!-- page specific plugin scripts END -->
				<!-- inline scripts related to this page -->
				</script>
				<script>
					seajs.use('${ctx}/static/my/js/contract/loangrant/main/main');
				</script>
			</body>