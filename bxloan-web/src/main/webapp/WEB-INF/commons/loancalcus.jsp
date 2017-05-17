<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.coamc-tech.com/taglibs/datadict" prefix="dd"%>
<form id="side_bar_calcu_form">
	<div id="side_bar_calcu_modal" class="modal fade"
		data-backdrop="static" tabindex="-1">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title">贷款试算</h4>
				</div>

				<div class="modal-body">
					<div class="row">
						<div class="form-group">
							<label class="col-xs-12 col-sm-2 control-label no-padding-right">贷款金额（元）：</label>
							<div class="col-xs-12 col-sm-3">
								<span class="block input-icon input-icon-right"> <input
									type="text" id="loanAmount" name="loanAmount"
									class="form-control required isPositiveNumberTwo"> </span>
							</div>
							<label class="col-xs-12 col-sm-3 control-label no-padding-right">贷款时间：</label>
							<div class="col-xs-12 col-sm-3">
								<span class="block input-icon input-icon-right"> <input
									type="text" id="loan_cal_loan_start_date" name="loanStartDate"
									data-date-format="yyyy-mm-dd" class="form-control required">
								</span>
							</div>
						</div>
					</div>
					<br>
					<div class="row">
						<div class="form-group">
							<label class="col-xs-12 col-sm-2 control-label no-padding-right">期限：</label>
							<div class="col-xs-12 col-sm-3">
								<span class="block input-icon input-icon-right"> <input
									type="text" id="applyTerm" name="applyTerm"
									class="required isIntPositive" style="width: 70%;"><select
									name="applyTermUnit" style="width: 30%;height: 33.5px;">
										<dd:options codeType="TermUnitCd" />
								</select> </span>
							</div>
							<label class="col-xs-12 col-sm-3 control-label no-padding-right">还款方式：</label>
							<div class="col-xs-12 col-sm-3">
								<span class="input-icon block input-icon-right"> <select
									name="repayment" id="repayment" class="form-control required">
										<dd:options codeType="RepaymentMode" />
								</select> </span>
							</div>
							<div class="help-block col-xs-12 col-sm-reset inline"></div>
						</div>
					</div>
					<br>
					<div class="row">
						<div class="form-group">
							<label class="col-xs-12 col-sm-2 control-label no-padding-right">还款日：</label>
							<div class="col-xs-12 col-sm-3">
								<span class="block input-icon input-icon-right"> <input
									type="text" id="repaymentDate" name="repaymentDate"
									class="form-control required isDayInMonth"> </span>
							</div>
							<label class="col-xs-12 col-sm-3 control-label no-padding-right">年利率（%）：</label>
							<div class="col-xs-12 col-sm-3">
								<span class="block input-icon input-icon-right"> <input
									type="text" id="rateBiz" name="rate"
									class="form-control required isPercentNumberFour"> </span>
							</div>
						</div>
					</div>
					<br>
					<div class="row">
						<div class="form-group">
							<label class="col-xs-12 col-sm-2 control-label no-padding-right">还款周期月数：</label>
							<div class="col-xs-12 col-sm-3">
								<span class="block input-icon input-icon-right"> <input
									type="text" id="repaymentNumber" name="repaymentNumber"
									class="form-control required isIntPositive"> </span>
							</div>
						</div>
					</div>
					
					<div id="cal_dkss_repaymentPlanTable_div" style="display: none;">
						<table id="cal_dkss_repaymentPlanTable"
							class="table table-striped table-hover">
							<thead>
								<tr>
									<th>计划还款时间</th>
									<th>计划还款本金金额（元）</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody id="cal_dkss_tbody"></tbody>
							<tfoot id="cal_dkss_tfoot"></tfoot>
						</table>
					</div>
					<br>
					<div class="row" align="right">
						<div>
							<button id="cal_addUserDefindPlan" type="button"
								class="btn btn-sm btn-success" style="display: none;">
								<i class="ace-icon fa fa-plus"></i> 新增还款计划
							</button>
							<button id="subCalculatorLoan" type="submit"
								class="btn btn-sm btn-primary">
								<i class="ace-ico fa fa-cny"></i> 计算
							</button>
							<button id="resetCalculatorLoan" type="button"
								class="btn btn-sm btn-default">
								<i class="ace-icon fa fa-undo"></i> 重置
							</button>
						</div>
					</div>
					<div>
						<span>
							<h4 class="blue">
								<i class="orange ace-icon fa fa-credit-card bigger-110"></i>
								还款计划
							</h4> </span>
					</div>
					<table id="tbl_cal" class="table table-striped table-hover">
						<thead>
							<tr>
								<th>期次</th>
								<th>计划还款日</th>
								<th>本期应还金额(元)</th>
								<th>应还本金(元)</th>
								<th>应还利息(元)</th>
								<th>累计还本(元)</th>
								<th>贷款余额(元)</th>
							</tr>
						</thead>
						<tbody id="tbld_cal">
						</tbody>
						<tfoot id="tbfoot_cal"></tfoot>
					</table>
				</div>
			</div>
		</div>
	</div>
</form>

<div id="cal_repayPlanModal" class="modal fade" data-backdrop="static"
	tabindex="-1">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title">还款计划</h4>
			</div>

			<div class="modal-body">
				<form id="cal_repayPlanForm" class="form-horizontal">
					<div class="modal-body">
						<div class="row">
							<div class="col-xs-12">
								<div class="form-group">
									<label class="col-sm-5 control-label no-padding-right"
										for="repayDate"> <font color='red'> * </font> 计划还款时间：
									</label>
									<div class="col-sm-7">
										<span class="block input-icon input-icon-right"> <input
											type="text" class="form-control required" id="cal_repayDate"
											name="repayDateStr" data-date-format="yyyy-mm-dd"> </span>
									</div>
									<div class="help-block col-xs-12 col-sm-reset inline"></div>
								</div>

								<div class="form-group">
									<label class="col-sm-5 control-label no-padding-right"
										for="repayAmt"> <font color='red'> * </font>
										计划还款本金金额（元）： </label>
									<div class="col-sm-7">
										<span class="block input-icon input-icon-right"> <input
											type="text" class="form-control required isPositiveNumberTwo"
											id="cal_repayAmt" name="repayAmt" /> </span>
									</div>
									<div class="help-block col-xs-12 col-sm-reset inline"></div>
								</div>

								<input type="hidden" id="cal_repayPlanId" name="repayPlanId">
							</div>
						</div>

					</div>

					<hr>

					<div class="row" align="right">
						<button id="cal_sureRepayPlanForm" type="submit"
							class="btn btn-primary">确定</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>