<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
%>
	<%@include file="../../../commons/taglibs.jsp" %>
		<div id="modal-formEcf" class="modal fade" tabindex="-1" role="basic"
		aria-hidden="true" style=";" data-backdrop="static">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<form id="ecfForm_nd" class="form-horizontal" role="form" method="post">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">
								&times;
							</button>
							<h4 class="blue bigger">
							</h4>
						</div>
						<div class="modal-body">
							<div class="row">
								<div class="col-xs-12">
									<input type="hidden" name="id" />
									<input type="hidden" name="expectCashFlowId" />
									<!-- START -->
									<div class="col-md-12 widget-container-col ui-sortable">
										<div class="widget-box ui-sortable-handle" style="opacity: 1; z-index: 0;">
											<div class="widget-header">
												<h4 class="blue widget-title">
													+家庭收入
												</h4>
												<a href="#" data-action="collapse">
													<i class="1 ace-icon fa bigger-125 fa-chevron-up">
													</i>
												</a>
												<div class="widget-toolbar">
													<a id="btn-addEcfIncome" href="javascript:void(0);" >
														<i class="ace-icon fa fa-plus">
														</i>
													</a>
												</div>
											</div>
											<div class="widget-body">
												<table id="cashFlowDetail_income_tb" class="table table-striped table-hover"
												style="white-space: nowrap;">
													<thead>
														<tr>
															<th>
																序号
															</th>
															<th>
																收入名称
															</th>
															<th>
																收入金额
															</th>
															<th width="30px">
																操作
															</th>
														</tr>
													</thead>
													<tbody>
													</tbody>
												</table>
											</div>
										</div>
									</div>
									<!-- END -->
									<!-- START -->
									<div class="col-md-12 widget-container-col ui-sortable">
										<div class="widget-box ui-sortable-handle" style="opacity: 1; z-index: 0;">
											<div class="widget-header">
												<h4 class="blue widget-title">
													-生产支出
												</h4>
												<a href="#" data-action="collapse">
													<i class="1 ace-icon fa bigger-125 fa-chevron-up">
													</i>
												</a>
												<div class="widget-toolbar">
													<a id="btn-addEcfConsume" href="javascript:void(0);">
														<i class="ace-icon fa fa-plus">
														</i>
													</a>
												</div>
											</div>
											<div class="widget-body">
												<table id="cashFlowDetail_consume_tb" class="table table-striped table-hover"
												style="white-space: nowrap;">
													<thead>
														<tr>
															<th>
																序号
															</th>
															<th>
																支出名称
															</th>
															<th>
																支出金额
															</th>
															<th width="30px">
																操作
															</th>
														</tr>
													</thead>
													<tbody>
													</tbody>
												</table>
											</div>
										</div>
									</div>
									<div id="caculateResultDiv" class="form-group col-md-12" align="center">
										<button type="button" class="btn btn-default btn-sm" style="text-shadow: black 5px 3px 3px;">
													支出现金流出合计：
													<span name="costTotal" class="badge pull-right">
														123123
													</span>
										</button>
										<button type="button" class="btn btn-default btn-sm" style="text-shadow: black 5px 3px 3px;">
													月末现金流余额：
													<span name="balance" class="badge pull-right">
														123123
													</span>
										</button>
										<button type="button" class="btn btn-default btn-sm" style="text-shadow: black 5px 3px 3px;">
													贷款前现金流余额：
													<span name="balanceBeforeLoan" class="badge pull-right">
														123123
													</span>
										</button>
										<button type="button" class="btn btn-default btn-sm" style="text-shadow: black 5px 3px 3px;">
													贷款后现金流余额：
													<span name="balanceAfterLoan" class="badge pull-right">
														123123
													</span>
										</button>
									</div>
									<!-- END -->
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<button class="btn btn-sm btn-default" data-dismiss="modal" type="button">
								<i class="ace-icon fa fa-times">
								</i>
								取消
							</button>
						</div>
					</form>
				</div>
			</div>
		</div>