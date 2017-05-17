<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.coamc-tech.com/taglibs/datadict" prefix="dd" %>
<div id="repayTrial_modal" class="modal" tabindex="-1" data-backdrop="static">
	<div class="modal-dialog" style="width:70%">
		<div class="modal-content">
			<form id="repayTrialForm" class="form-horizontal">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 id="addr_form_title" class="blue bigger">
						<i class='ace-icon fa fa-plus'></i>&nbsp;逾期试算
					</h4>
				</div>
				<div class="modal-body">
					<!-- 隐藏域 -->
					<input type="hidden" name="contractId" />
					<input type="hidden" name="partyId" value=""/>
					<input type="hidden" name="repayingPlanId" />
					<input type="hidden" name="repayingPlanDetailId" />
					<input type="hidden" name="overdue" />
					<input type="hidden" name="normal2Overdue"/>
					<input type="hidden" name="lastRepayDate" />
				<div class="row">
					<div class="col-md-12">
			        <div class="form-group">
			        	<label class="col-xs-12 col-sm-2 control-label no-padding-right">
							客户名称：
						</label>
						<div class="col-xs-12 col-sm-3">
							<span class="block input-icon input-icon-right">
								<input name="customerName" class="form-control" readonly="readonly">
							</span>
						</div>
						<div class="help-block col-xs-12 col-sm-reset inline"></div>
						<label class="col-xs-12 col-sm-2 control-label no-padding-right">
							合同编号：
						</label>
						<div class="col-xs-12 col-sm-3">
							<span class="block input-icon input-icon-right">
								<input name="contractNum" class="form-control" readonly="readonly">
							</span>
						</div>
						<div class="help-block col-xs-12 col-sm-reset inline"></div>
					    
					</div>
					
					<div class="form-group">
						<label class="col-xs-12 col-sm-2 control-label no-padding-right">
							贷款产品：
						</label>
						<div class="col-xs-12 col-sm-3">
							<span class="block input-icon input-icon-right">
							 	<input name="loanProductName" class="form-control" readonly="readonly">
							</span>
						</div>
						<div class="help-block col-xs-12 col-sm-reset inline"></div>
						<label class="col-xs-12 col-sm-2 control-label no-padding-right">
							客户经理：
						</label>
						<div class="col-xs-12 col-sm-3">
							<span class="block input-icon input-icon-right">
								<input name="custMngName" class="form-control" readonly="readonly">
							</span>
						</div>
						<div class="help-block col-xs-12 col-sm-reset inline"></div>
					</div> 
					
					<div class="form-group">
						<label class="col-xs-12 col-sm-2 control-label no-padding-right">
							贷款金额：
						</label>
						<div class="col-xs-12 col-sm-3">
							<span class="block input-icon input-icon-right">
							<div class="input-group">
								<input name="loanAmt" class="form-control formatNum" readonly="readonly">
								<span class="input-group-addon">元</span>
							</div>
							</span>
						</div>
						<div class="help-block col-xs-12 col-sm-reset inline"></div>
						<label class="col-xs-12 col-sm-2 control-label no-padding-right">
							贷款期限：
						</label>
						<div class="col-xs-12 col-sm-3">
							<span class="block input-icon input-icon-right">
							<div class="input-group">
								<input name="contractTerm" class="form-control" readonly="readonly">
								<span class="input-group-addon"></span>
							</div>
							</span>
						</div>
						<div class="help-block col-xs-12 col-sm-reset inline"></div>
					</div>
					<div class="form-group">
				    	<label class="col-xs-12 col-sm-2 control-label no-padding-right">
							贷款余额：
						</label>
						<div class="col-xs-12 col-sm-3">
							<span class="block input-icon input-icon-right">
							<div class="input-group">
								<input type="text" name="contractBalance" class="form-control formatNum" 
								readonly="readonly"/>
								<span class="input-group-addon">元</span>
							</div>
							</span>
						</div>
						<div class="help-block col-xs-12 col-sm-reset inline"></div>
						<label class="col-xs-12 col-sm-2 control-label no-padding-right">
							贷款利率：
						</label>
						<div class="col-xs-12 col-sm-3">
							<span class="block input-icon input-icon-right">
							<div class="input-group">
								<input name="contractInterestRate" class="form-control" readonly="readonly">
								<span class="input-group-addon">%</span>
							</div>
							</span>
						</div>
						<div class="help-block col-xs-12 col-sm-reset inline"></div>
					</div>
					
					<div class="form-group">
						<label class="col-xs-12 col-sm-2 control-label no-padding-right">
							还款方式：
						</label>
						<div class="col-xs-12 col-sm-3">
							<span class="block input-icon input-icon-right">
								<input name="repayModeName" type="text" class="form-control" 
								readonly="readonly"/>
							</span>
						</div>
						<div class="help-block col-xs-12 col-sm-reset inline"></div>
						<label class="col-xs-12 col-sm-2 control-label no-padding-right">
							还款周期月数：
						</label>
						<div class="col-xs-12 col-sm-3">
							<span class="block input-icon input-icon-right">
								<input name="repayPrincipalMonthes" type="text" class="form-control" 
								readonly="readonly"/>
							</span>
						</div>
						<div class="help-block col-xs-12 col-sm-reset inline"></div>
					</div>
					<div class="form-group ">
						<label class="col-xs-12 col-sm-2 control-label no-padding-right">
							贷款发放日：
						</label>
						<div class="col-xs-12 col-sm-3">
							<span class="block input-icon input-icon-right">
								<input name="payloanDateStr" type="text" class="form-control" 
								readonly="readonly"/>
							</span>
						</div>
						<div class="help-block col-xs-12 col-sm-reset inline"></div>
					</div>
					<div class="form-group">
						<label class="col-xs-12 col-sm-2 control-label no-padding-right">
							拖欠本息：
						</label>
						<div class="col-xs-12 col-sm-3">
							<span class="block input-icon input-icon-right">
							<div class="input-group">
								<input name="overduePi" type="text" class="form-control formatNum" 
								readonly="readonly"/>
								<span class="input-group-addon">元</span>
							</div>
							</span>
						</div>
						<div class="help-block col-xs-12 col-sm-reset inline"></div>
						<label class="col-xs-12 col-sm-2 control-label no-padding-right">
							逾期利息：
						</label>
						<div class="col-xs-12 col-sm-3">
							<span class="block input-icon input-icon-right">
							<div class="input-group">
								<input name="overdueInt" type="text" class="form-control" 
								readonly="readonly"/>
								<span class="input-group-addon">元</span>
							</div>
							</span>
						</div>
						<div class="help-block col-xs-12 col-sm-reset inline"></div>
					</div>
					
					<div class="form-group">
						<label class="col-xs-12 col-sm-2 control-label no-padding-right">
							实还日期：
						</label>
						<div class="col-xs-12 col-sm-3">
							<span class="block input-icon input-icon-right">
								<input id="repayDate_trial" name="repayDateStr" type="text" class="form-control required" 
								data-date-format="yyyy-mm-dd" />
							</span>
						</div>
						<div class="help-block col-xs-12 col-sm-reset inline"></div>
					</div>
					
					</div>
				</div>
				<div class="row">
					<div class="col-xs-12 table-responsive">
						<table id="tb_repayTrial_items" class="table table-striped table-hover ">
							<thead>
								<tr>
								<th>期次</th>
								<th>应还日期</th>
								<th>本期应还金额（元）</th>
								<th>应还本金（元）</th>
								<th>应还利息（元）</th>
								<th>已还本金（元）</th>
								<th>已还利息（元）</th>
								<th>逾期利息（元）</th>
								<th>逾期天数</th>
								<th>状态</th>
								</tr>
							</thead>
							<tbody>
							
							</tbody>
						</table>
					</div>
				</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-sm btn-default" data-dismiss="modal">
						<i class="ace-icon fa fa-times"></i> 取消
					</button>
				</div>
			</form>
		</div>
	</div>
</div>