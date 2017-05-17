<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div>
	<div>
		<!-- 隐藏域 -->
        <div>
	        <input type="hidden" id="projectIdCwField" value="" />
	        <input type="hidden" id="cuserIdField" value="${cuserId}" />
	        <input type="hidden" id="contractIdField" value="${contractId}" />
	        <input type="hidden" id="payLoanIdField" value="" />
        </div>
		<div class="form-group" id="repayPlanParams">
			<label for="payLoanDate" class="control-label col-sm-1">放款日期：</label>
			<div class="col-sm-2">
				<input type="text" id="payLoanDate" class="form-control col-sm-5 input-sm" name="payLoanDate" readonly="readonly">
			</div>
			<label for="contractTermTotal" class="control-label col-sm-1">合同期限：</label>
			<div class="col-sm-2">
				<input type="text" id="contractTermTotal" class="form-control col-sm-5 input-sm" name="contractTermTotal" readonly="readonly">
			</div>
			<label for="contractRate" class="control-label col-sm-1">合同利率(%)：</label>
			<div class="col-sm-2">
				<input type="text" id="contractRate" class="form-control col-sm-5 input-sm" name="contractRate" readonly="readonly">
			</div>
			<label for="payLoanStatus" class="control-label col-sm-1">还款情况：</label>
			<div class="col-sm-2">
				<input type="text" id="payLoanStatus" class="form-control col-sm-5 input-sm" name="payLoanStatus" readonly="readonly">
			</div>
		</div>
		<table id="tbRepayLoan" class="table table-striped table-hover" style="width: 100%!important;">
			<thead>
            	<tr>
            		<!-- 还款计划 -->
                	<th width="0%">选择</th>
                	<th>期次</th>
                    <th>应还日期</th>
                    <th>应还本息（元）</th>
                    <th>应还本金（元）</th>
                    <th>应还利息（元）</th>
                    <!-- 还款情况 -->
                    <th>实还日期</th>
                    <th>实还本金（元）</th>
                    <th>实还利息（元）</th>
                    <th>实还逾期利息（元）</th>
                    <th>本期逾期天数</th>
                    <th>逾期利息</th>
                    <th>合同余额</th>
                    
                    <th>状态</th>
                    <th>操作</th>
                </tr>
            </thead>
			<tbody>
			</tbody>
		</table>
	</div>
</div>