<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../../../commons/taglibs.jsp" %>

<body class="no-skin">
	<div class="main-container" id="main-container">
		<!-- <div class="main-content"> -->
			<!-- <div class="page-content"> -->
				<input type="hidden" name="partyId" value="${partyId}">
				<div class="row">
					<div class="col-md-12">
						<table id="contractTable" class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<th>合同编号</th>
									<th>客户名称</th>
									<th>贷款产品</th>
									<th>贷款机构</th>
									<th>合同状态</th>
									<th>合同金额（元）</th>
									<th>合同期限</th>
									<th>合同余额（元）</th>
									<th>担保方式</th>
									<th><i class="ace-icon fa fa-clock-o bigger-110 hidden-480"></i>放款日期</th>
									<th><i class="ace-icon fa fa-clock-o bigger-110 hidden-480"></i>合同到期日期</th>
									<th>客户经理</th>
								</tr>
							</thead>
						</table>
					</div>
				</div>
			<!-- </div> -->
		<!-- </div> -->
	</div>
	<div id="contractModal" class="modal" data-backdrop="static" tabindex="-1">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title">合同信息</h4>
				</div>
				<div class="modal-body">
					<iframe id="contractIframe" width="100%" height="100%"
						style="border: 0px;"></iframe>
				</div>
			</div>
		</div>
	</div>
</body>
</html>