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
					<li class="active">账务处理</li>
					<li class="active">账务冲正</li>
				</ul><!-- /.breadcrumb -->
			</div>

			<div class="page-content">
				<form class="form-horizontal" role="searchForm">
				<div class="page-header">
					<h1>
						账务冲正 <small><i class="ace-icon fa fa-angle-double-right"></i> 合同列表</small>
						<span class="pull-right">
							<button id="btn-query" class="btn btn-sm btn-purple" type="button"> <i class="ace-icon fa fa-search"></i> 查询 </button>
	                       	<button id="btn-reset" class="btn btn-sm btn-default" type="reset"> <i class="ace-icon fa fa-undo"></i> 重置 </button>
						</span>
					</h1>
				</div>
				<!-- Search start -->
				<div class="row" style="height: 0px;">
					<div class="col-xs-12">
							<div class="form-group">
								<label class="control-label col-sm-2">客户名称：</label>
								<div class="col-sm-2">
									<input type="text" name="customerName" placeholder="客户名称" class="form-control col-sm-5">
								</div>
								<label class="control-label col-sm-2" for="form-field-1">客户编号：</label>
								<div class="col-sm-2">
									<input type="text" name="custCd" placeholder="客户编号" class="form-control col-sm-5">
								</div>
								<label class="control-label col-sm-2" for="form-field-1">合同编号：</label>
								<div class="col-sm-2">
									<input type="text" name="contCd" placeholder="合同编号" class="form-control col-sm-5">
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-sm-2 " for="form-field-1">单据状态：</label>
								<div class="col-sm-2">
									<select class="form-control col-sm-5" name="billState">
										<option value="all">全部</option>
										<option value="047002">发送未入账</option>
										<option value="047003">已入账</option>
										<option value="047004">已退单</option>
										<option value="047005">冲销未入账</option>
										<option value="047006">冲销已入账</option>
									</select>
								</div>
								<label class="control-label col-sm-2" for="form-field-1">业务类型：</label>
								<div class="col-sm-2">
									<select class="form-control col-sm-5" name="busiTypCd">
										<option value="all">全部</option>
										<dd:options codeType="BusinessType"/>
										<!-- <option value="001">贷款发放</option>
										<option value="002">贷款逾期</option>
										<option value="003">贷款还款</option>
										<option value="004">贷款结息</option>
										<option value="009">单项准备金计提</option>
										<option value="011">贷款核销</option>
										<option value="012">贷款费用</option>
										<option value="013">贷款转出</option> -->
									</select>
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
									<th>合同编号</th>
									<th>单据编号</th>
									<th>单据描述</th>
									<th>单据状态</th>
									<th>业务类型</th>
									<th>业务日期</th>
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
	<c:import url="../../commons/post-include.jsp" charEncoding="UTF-8"/>
	<script>
		seajs.use('${ctx}/static/my/js/accountingMng/accountingFlushes/main');
	</script>
	<!-- Modal start-->
	<div id="viewModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true" data-backdrop="static">
	  <div class="modal-dialog" style="width: 70%">
	    <div class="modal-content">
	    	<div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
		        <h4 class="modal-title" id="viewModalLabel">详细</h4>
		     </div>
		     <div class="modal-body">
		       <iframe name="mainFrame" id="mainFrame"  width="100%" height="700px" 
		        frameborder="0" marginheight="0" marginwidth="0" scrolling="auto" style="overflow:auto"></iframe>
		     </div>
	    </div>
	  </div>
	</div>
	<!-- Modal end-->
</body>
</html>