<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../commons/taglibs.jsp"%>
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

<c:import url="../commons/pre-include.jsp" charEncoding="UTF-8" />
<script type="text/javascript"
	src="${ctx}/static/assets/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="${ctx}/static/assets/js/highcharts.js"></script>
<script src="${ctx}/static/assets/js/bootstrap.min.js"></script>
<script src="${ctx}/static/assets/js/jquery-ui.custom.min.js"></script>
<script src="${ctx}/static/assets/js/jquery.ui.touch-punch.min.js"></script>
<script src="${ctx}/static/assets/js/jquery.easypiechart.min.js"></script>
<script src="${ctx}/static/assets/js/jquery.sparkline.min.js"></script>
<script src="${ctx}/static/assets/js/flot/jquery.flot.min.js"></script>
<script src="${ctx}/static/assets/js/flot/jquery.flot.pie.min.js"></script>

<!-- page specific plugin styles START -->
<!-- page specific plugin styles END -->

<!-- inline styles related to this page -->
<script>
	var $$ctx = "${ctx}/";
</script>
</head>
<body class="no-skin">
	<c:import url="../commons/navbar.jsp" charEncoding="UTF-8" />

	<div class="main-container" id="main-container">
		<script type="text/javascript">
			try {
				ace.settings.check('main-container', 'fixed')
			} catch (e) {
			}
		</script>

		<c:import url="../commons/sidebar.jsp" charEncoding="UTF-8" />

		<div class="main-content">
			<div class="breadcrumbs" id="breadcrumbs">
				<script type="text/javascript">
					try {
						ace.settings.check('breadcrumbs', 'fixed')
					} catch (e) {
					}
				</script>

				<ul class="breadcrumb">
					<li><i class="ace-icon fa fa-home home-icon"></i> <a
						href="${ctx}">主页</a></li>
					<li class="active">工作面板</li>
				</ul>
			</div>

			<div class="page-content">

				<%-- <c:import url="../commons/settings.jsp" charEncoding="UTF-8" /> --%>

				<div class="page-header">
					<h1>
						工作面板 <small><i class="ace-icon fa fa-angle-double-right"></i>
							概览 &amp; 统计</small>
					</h1>
				</div>
				<!-- /.page-header -->

				<!-- 页面内容 -->
				<div class="row">
					<div class="col-xs-12">

						<div class="widget-box transparent" id="recent-box">
							<div class="widget-header">

								<div class="widget-toolbar no-border">
									<ul class="nav nav-tabs" id="recent-tab">
										<li class="active"><a data-toggle="tab" href="#task-tab">代办事宜</a></li>
										<!-- <li><a data-toggle="tab" href="#member-tab">已办事宜</a></li> -->

										<li><a data-toggle="tab" href="#comment-tab">消息</a></li>
									</ul>

								</div>
							</div>

							<div class="widget-body">
								<div class="widget-main padding-4">
									<div class="tab-content padding-8">
										<div id="task-tab" class="tab-pane active">
											<div class="col-xs-12">
												<table class="table table-striped table-hover" id="doSTHTbl">
													<thead>
														<tr role="row">
															<th>业务类型</th>
															<th>项目编号</th>
															<th>贷款产品</th>
															<th>客户名称</th>
															<th>提交人</th>
															<th>客户类型</th>
															<th>步骤名称</th>
															<th>操作时间</th>
															<th>操作</th>
														</tr>
													</thead>
												</table>
											</div>
										</div>

										<!-- <div id="member-tab" class="tab-pane active">
											<div class="col-xs-12">
												<table class="table table-striped table-hover" id="asSTHTbl">
													<thead>
														<tr role="row">
															<th>业务类型</th>
															<th>项目编号</th>
															<th>贷款产品</th>
															<th>客户名称</th>
															<th>提交人</th>
															<th>客户类型</th>
															<th>步骤名称</th>
															<th>操作时间</th>
															<th>操作</th>
														</tr>
													</thead>
												</table>
											</div>
										</div> -->

										<div id="comment-tab" class="tab-pane">
											<div class="col-xs-12">
												<div>
													<div id="sample-table-2_wrapper"
														class="dataTables_wrapper form-inline" role="grid">
														<table aria-describedby="sample-table-2_info"
															id="doMessage" name="doMessage"
															class="table table-striped table-bordered table-hover dataTable">
															<thead>
																<tr role="row">
																	<th>提示信息类型</th>
																	<th>消息ID</th>
																	<th>消息条数</th>
																	<th>查看详情</th>
																</tr>
															</thead>
														</table>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
								<!-- /.widget-main -->
							</div>
							<!-- /.widget-body -->
						</div>
						<!-- /.widget-box -->
						<!-- /.row -->
						<div id="calculatorLoandiv" style="display: none">
							<div>
								<div class="row"></div>


								<!-- <div id="piechart-placeholder"></div>
						<div id="sales-charts"></div> -->

							</div>
							<!-- /.col -->
						</div>
						<!-- /.row -->
					</div>
					<!-- /.page-content -->
				</div>
				<!-- /.main-content -->

			</div>


			<!-- /.main-container -->

			<div id="modal-show" class="modal" tabindex="-1">
				<div class='col-xs-10'>
					<div class="modal-content">
						<div class="modal-body">
							<div id="oneyTypeMsg">
								<div class="col-xs-12">
									<div id='testdivid'>
										<div id="sample-table-2_wrapper"
											class="dataTables_wrapper form-inline" role="grid">
											<table aria-describedby="sample-table-2_info" id="doMessage2"
												name="doMessage2"
												class="table table-striped table-bordered table-hover dataTable">
												<thead>
													<tr role="row">
														<th>消息编号</th>
														<th>审核状态</th>
														<th></th>
														<th>消息内容</th>
														<th></th>
														<th></th>
														<th></th>
														<th></th>
														<th>操作人</th>
														<th></th>
														<th></th>
														<th></th>
														<th></th>
														<th>号码</th>
														<th></th>
														<th></th>
														<th></th>
														<th></th>
														<th></th>
														<th></th>
													</tr>
												</thead>
											</table>
										</div>
									</div>
								</div>
							</div>

						</div>
						<div class="modal-footer">
							<button class="btn btn-sm" data-dismiss="modal">
								<i class="ace-icon fa fa-times"></i> 取消
							</button>
						</div>
					</div>
				</div>
			</div>

			<div id="modal-form" class="modal" tabindex="-1">
				<div class='col-xs-10'>
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
							<h4 class="blue bigger"></h4>
						</div>
						<div class="modal-body">
							<div>
								<div class="row">
									<div>
										<label type="label"
											class="col-xs-12 col-sm-1 control-label no-padding-right">贷款金额(元)：</label>
										<input type='text' id='loanAmount' name='loanAmount' /> <label
											type="label" class="control-label ">贷款时间：</label> <input
											type="text" id="loanStartDate" data-date-format="yyyy-mm-dd" />

										<label type="label">期限：</label> <input type="text"
											id='applyTerm' name='applyTerm' /> <select
											name='applyTermUnit' id='applyTermUnit'>
											<!-- <option value='0' id='aaa'>==期限单位==</option>
											<option value='1'>年</option>
											<option value='2'>月</option>
											<option value='3'>日</option> -->
											<dd:options codeType="TermUnitCd" selected="${projectApplication.renewalTermUnit }"/>
										</select></br>
									</div>
								</div>
								<div class="row">
									<div>
										<label type="label"
											class="col-xs-12 col-sm-1 control-label no-padding-right">还款日期：</label>
										<input type='text' name='repaymentDate' id='repaymentDate' />
										<label type="label">还款周期月数：</label> <input type='text'
											name='repaymentNumber' id='repaymentNumber' /> <label
											type="label" class="control-label ">还款方式：</label> 
										<select
											name='repayment' id='repayment'>
											<dd:options codeType="RepaymentMode" selected="${projectApplication.renewalTermUnit }"/>
										</select>
									</div>
								</div>
								<div class="row">
									<div>
										&nbsp;&nbsp;&nbsp;<label type="label" class="control-label ">贷款年利率：</label> 
										&nbsp;&nbsp;<input type='text' name='rate' id='rate' />
									</div>
								</div>
								<br /> <br />
								<div class="row" align="right">
									<div>
										<!-- <input type='button' value='计算' id='subCalculatorLoan'>
										<input type='button' value='重置' id='resetCalculatorLoan'> -->
										<button id="subCalculatorLoan" type="button"
											class="btn btn-sm btn-info">
											<i class="ace-ico fa fa-send"></i>计算
										</button>
										<button id="resetCalculatorLoan" type="button"
											class="btn btn-sm btn-default">
											<i class="ace-icon fa fa-undo"></i>重置
										</button>
									</div>
								</div>
								<div id='comment-tab' class='tab-pane'>
									<div class='col-xs-12'>
										<div id='sample-table-2_wrapper'
											class='dataTables_wrapper form-inline' role='grid'>
											<table aria-describedby='sample-table-2_info'
												id='calculation' name='calculation'
												class='table table-striped table-bordered table-hover dataTable'>
											</table>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<button class="btn btn-sm" data-dismiss="modal">
								<i class="ace-icon fa fa-times"></i> 取消
							</button>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-12">
					<!-- <div class="row">
						<div class="vspace-12-sm"></div>
						<div class="col-sm-12" style="margin-top:20px;">
							<div class="widget-box transparent">
								<div class="widget-header widget-header-flat">
									<h4 class="widget-title lighter">
										<i class="ace-icon fa fa-signal orange">柱状图</i>
								</div>

								<div class="widget-body">
									<div id="container-2" style="height: 400px; margin: 0 auto"></div>
								</div>
								/.widget-body
							</div>
							/.widget-box
						</div>
						/.col


					</div>
					/.row -->

					<div class="hr hr32 hr-dotted"></div>

					<div class="row">
						<div class="col-sm-5">
							<div class="widget-box">
								<div class="widget-header widget-header-flat ">
									<h4 class="widget-title">
										<i class="ace-icon fa fa-signal"></i> 饼状图
									</h4>

								</div>

								<div class="widget-body">
									<div class="widget-main">
										<div id="container-1" style="height: 400px; margin: 0 auto"></div>
									</div>
									<!-- /.widget-main -->
								</div>
								<!-- /.widget-body -->
							</div>
							<!-- /.widget-box -->
						</div>
						<!-- /.col -->

						<div class="col-sm-7">
							<div class="widget-box transparent">
								<div class="widget-header widget-header-flat">
									<h4 class="widget-title lighter">
										<i class="ace-icon fa fa-signal"></i> 还款线性图
									</h4>
								</div>

								<div class="widget-body">
									<div class="widget-main padding-4">
										<div id="container"></div>
									</div>
									<!-- /.widget-main -->
								</div>
								<!-- /.widget-body -->
							</div>
							<!-- /.widget-box -->
						</div>
						
						<div class="col-sm-7">
							<div class="widget-box transparent">
								<div class="widget-header widget-header-flat">
									<h4 class="widget-title lighter">
										<i class="ace-icon fa fa-signal"></i> 放款线性图
									</h4>
								</div>

								<div class="widget-body">
									<div class="widget-main padding-4">
										<div id="loan"></div>
									</div>
									<!-- /.widget-main -->
								</div>
								<!-- /.widget-body -->
							</div>
							<!-- /.widget-box -->
						</div>
						<!-- /.col -->
					</div>
					<!-- /.row -->

					<div class="hr hr32 hr-dotted"></div>
				</div>
				<!-- /.col -->
			</div>
			<!-- /.row -->
		</div>
	</div>

	<c:import url="../commons/footer.jsp" charEncoding="UTF-8" />
	<c:import url="../commons/post-include.jsp" charEncoding="UTF-8" />
	<!-- page specific plugin scripts START -->
	<!--[if lte IE 8]>
		  <script src="${ctx}/static/assets/js/excanvas.min.js"></script>
		<![endif]-->
	<script src="${ctx}/static/assets/js/jquery.easypiechart.min.js"></script>
	<script src="${ctx}/static/assets/js/jquery.sparkline.min.js"></script>
	<script src="${ctx}/static/assets/js/flot/jquery.flot.min.js"></script>
	<script src="${ctx}/static/assets/js/flot/jquery.flot.pie.min.js"></script>
	<script src="${ctx}/static/assets/js/flot/jquery.flot.resize.min.js"></script>



	<!-- page specific plugin scripts END -->

	<!-- inline scripts related to this page -->
	<script type="text/javascript" src="static/my/js/dashboard.js"></script>
	<script>
		seajs.use('${ctx}/static/my/js/dashboard/main');
	</script>
</body>

</html>