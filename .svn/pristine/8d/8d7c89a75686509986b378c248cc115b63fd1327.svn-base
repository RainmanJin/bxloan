<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/commons/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<base href="${ctx}/">
<title>${title }</title>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

<meta name="description" content="Dashboard page" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<c:import url="/WEB-INF/commons/pre-include.jsp" charEncoding="UTF-8"/>
<script>var $$ctx = "${ctx}/";</script>
</head>
<body class="no-skin">
	<c:import url="/WEB-INF/commons/navbar.jsp" charEncoding="UTF-8" />
	<div class="main-container" id="main-container">
	<script type="text/javascript">
		try{ace.settings.check('main-container' , 'fixed')}catch(e){}
	</script>
	<c:import url="/WEB-INF/commons/sidebar.jsp" charEncoding="UTF-8" />
		<div class="main-content">
			<div class="breadcrumbs" id="breadcrumbs">
				<script type="text/javascript">
					try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
				</script>
				<ul class="breadcrumb">
					<li>
						<i class="ace-icon fa fa-home home-icon"></i>
						<a href="${ctx}">贷后管理</a>
					</li>
					<li class="active">贷款回收</li>
				</ul><!-- /.breadcrumb -->
			</div>
			
			<div class="page-content">
				<form class="form-horizontal" role="searchForm" onsubmit="return false;">
					<div class="page-header">
						<h1>
							贷款回收 <small><i class="ace-icon fa fa-angle-double-right"></i> 正常还款</small>
							<span class="pull-right">
								<button role="query" class="btn btn-sm btn-purple" type="button"> <i class="ace-icon fa fa-search"></i> 查询 </button>
		                       	<button role="query-reset" class="btn btn-sm btn-default" type="reset"> <i class="ace-icon fa fa-undo"></i> 重置 </button>
							</span>
						</h1>
					</div>
					<div class="row" style="height: 0px;">
						<div class="col-xs-12">
							<div class="form-group">
								<label class="control-label col-sm-1" for="form-field-1">客户编号：</label>
								<div class="col-sm-3">
									<input type="text" name="customerNum" placeholder="客户编号" class="form-control col-sm-5 input-sm">
								</div>
								<label class="control-label col-sm-1">客户名称：</label>
								<div class="col-sm-3">
									<input type="text" name="customerName" placeholder="客户名称" class="form-control col-sm-5 input-sm">
								</div>
								<label class="control-label col-sm-1" for="form-field-1">合同编号：</label>
								<div class="col-sm-3">
									<input type="text" name="contractNum" placeholder="合同编号" class="form-control col-sm-5 input-sm">
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-sm-1" for="form-field-1">贷款产品：</label>
								<div class="col-sm-3">
									<span class="block input-icon input-icon-right">
										<div class="input-group">
											<input type="text" role="productName" class="form-control input-sm" readonly="readonly">
											<input type="hidden" name="productCd">
											<span class="input-group-btn">
												<button role="showProductTree" class="btn btn-sm btn-yellow" type="button">
													<i class="ace-icon fa fa-eye"></i>
												</button>
												<button role="clearProductCd" class="btn btn-sm btn-default" type="button">
													<i class="ace-icon fa fa-undo"></i>
												</button>
											</span>
										</div>
									</span>
									<div id="productTreeDiv" style="display:none;">
										<div class="col-xs-12" style="overflow-y: auto;max-height: 500px;position:absolute;z-index:999;background:#fff;border:1px solid #e3e3e3;width: 93.5%;">
											<ul id="productTree" class="ztree"></ul>
										</div>
									</div>
									<!-- <input type="text" name="productName" placeholder="贷款产品" class="form-control col-sm-5 input-sm"> -->
								</div>
								<%-- <label class="control-label col-sm-1">期次状态：</label>
								<div class="col-sm-3">
									<select class="form-control col-sm-5 input-sm" name="contractStatus">
										<option value="">全部</option>
										<dd:options codeType="ContractSearchStatusCode"/>
									</select>
								</div> --%>
								<label class="control-label col-sm-1" for="form-field-1">应还日期：</label>
								<div class="col-sm-3">
									<div class="input-group">
										<input type="text" class="form-control input-sm" name="startDateStr" placeholder="开始日期" data-date-format="yyyy-mm-dd">
										<span class="input-group-addon">至</span>
										<input type="text" class="form-control input-sm"name="endDateStr" placeholder="结束日期" data-date-format="yyyy-mm-dd" value="${endDateStr}">
									</div>
								</div>
							</div>
						</div>
					</div>
				</form>
				<div class="row">
					<div class="col-xs-12">
						<table id="tb_normalRepayment" class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<th>合同编号</th>
									<th>客户名称</th>
									<!-- <th>客户编号</th> -->
									<th>贷款产品</th>
									<th>合同金额（元）</th>
									<th><i class="ace-icon fa fa-clock-o bigger-110 hidden-480"></i>应还日期</th>
									<th>本期应还本息（元）</th>
									<th>本期已还本息（元）</th>
									<th>已还期次占比</th>
									<th>客户联系方式</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
							</tbody>
						</table>
					</div>
				</div><!-- /.row -->
			</div><!-- /.page-content -->
		</div><!-- /.main-content -->
		<c:import url="/WEB-INF/commons/footer.jsp" charEncoding="UTF-8" />
	</div><!-- /.main-container -->
	<c:import url="./nrFormModal.jsp" charEncoding="UTF-8" />
	<c:import url="/WEB-INF/commons/post-include.jsp" charEncoding="UTF-8"/>
	<script>
		seajs.use('${ctx}/static/my/js/afterloan/normalRepayment/main');
	</script>
</body>
</html>