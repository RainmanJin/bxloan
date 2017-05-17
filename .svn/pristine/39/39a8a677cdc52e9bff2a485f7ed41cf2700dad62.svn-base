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
	#tbl ul,#tbl li{
	 background-color :#fff;
	 max-width:110px;
	}
	#tbl li{
	 text-align: left;
	}
	#tbl a{
	 padding-left: 5px;
	 padding-right: 5px;
	 }
	#tbl ul{
	 margin-top: 0px;
	 padding: 0px;
	 min-width: 105px!important;
	}
	#tbl span.line_span{
	 min-width: 25px;
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
							贷款回收 <small><i class="ace-icon fa fa-angle-double-right"></i> 合同列表</small>
							<span class="pull-right">
								<button id="btn-query" class="btn btn-sm btn-purple" type="button"> <i class="ace-icon fa fa-search"></i> 查询 </button>
		                       	<button id="btn-reset" class="btn btn-sm btn-default" type="reset"> <i class="ace-icon fa fa-undo"></i> 重置 </button>
							</span>
						</h1>
					</div>
					<div class="row" style="height: 0px;">
						<div class="col-xs-12">
							<div class="form-group">
								<label class="control-label col-sm-2">客户名称：</label>
								<div class="col-sm-2">
									<input type="text" name="customerName" placeholder="客户名称" class="form-control col-sm-5">
								</div>
								<label class="control-label col-sm-2" for="form-field-1">客户编号：</label>
								<div class="col-sm-2">
									<input type="text" name="customerNum" placeholder="客户编号" class="form-control col-sm-5">
								</div>
								<label class="control-label col-sm-2" for="form-field-1">合同编号：</label>
								<div class="col-sm-2">
									<input type="text" name="contractNum" placeholder="合同编号" class="form-control col-sm-5">
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-sm-2">合同状态：</label>
								<div class="col-sm-2">
									<select class="form-control col-sm-5" name="contractStatus">
										<option value="">全部</option>
										<dd:options codeType="ContractSearchStatusCode"/>
									</select>
								</div>
								<label class="control-label col-sm-2" for="form-field-1">放款日期起：</label>
								<div class="col-sm-2">
									<input type="text" name="startDate" placeholder="放款日期" class="form-control col-sm-5" data-date-format="yyyy-mm-dd">
								</div>
								<label class="control-label col-sm-2" for="form-field-1">放款日期止：</label>
								<div class="col-sm-2">
									<input type="text" name="endDate" placeholder="放款日期" class="form-control col-sm-5" data-date-format="yyyy-mm-dd">
								</div>
							</div>
						</div>
					</div>
				</form>
				<div class="row">
					<div class="col-xs-12">
						<table id="tbl" class="table table-striped table-hover">
							<thead>
								<tr>
									<th>合同编号</th>
									<th>客户名称</th>
									<th>贷款产品</th>
									<th>合同金额（元）</th>
									<th>合同余额（元）</th>
									<th>还款状态</th>
									<th>放款日期</th>
									<th>已还期占比</th>
									<th>最近一次还款时间</th>
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
	
	<c:import url="../../commons/footer.jsp" charEncoding="UTF-8" />
		
	</div><!-- /.main-container -->
	<input type="hidden" id="global_contractId">
	<jsp:include page="loanRecovery/lrFeeRegisterForm.jsp"></jsp:include>
	<jsp:include page="loanRecovery/lrRepayForm.jsp"></jsp:include>
	<jsp:include page="loanRecovery/lrRepayTrial.jsp"></jsp:include>
	<jsp:include page="loanRecovery/lrRepayInfoList.jsp"></jsp:include>
	<jsp:include page="loanRecovery/lrRepayPlanInfoList.jsp"></jsp:include>
	
	<c:import url="../../commons/post-include.jsp" charEncoding="UTF-8"/>
	<script>
		seajs.use('${ctx}/static/my/js/afterloan/loanRecovery/main');
	</script>
	<!--  -->
</body>
</html>