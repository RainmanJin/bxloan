<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.coamc-tech.com/taglibs/datadict" prefix="dd"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<div class="row"></div>
<input name="projectId" type="hidden" value="${projApp.projectId  }" />
<input name="approveLevel" type="hidden"
	value="${projApp.approveLevel }" />
<div class="row">
	<div class="form-group-wdapproval form-group">
		<label class="col-xs-12 col-sm-4 control-label no-padding-right">
			<font color="red">* </font>批复日期：</label>
		<div class="col-xs-12 col-sm-8">
			<input name="approveDateStr" type="text" class="width-100"
				value="${projApp.approveDateStr  }">
		</div>
	</div>
	<div class="form-group-wdapproval form-group">
		<label class="col-xs-12 col-sm-4 control-label no-padding-right">
			<font color="red">*</font>批复金额：</label>
		<div class="col-xs-12 col-sm-8">
			<span class="block input-icon input-icon-right"> 
				<div class="input-group">
					<input name="approveAmt" id="approveAmt" value="${projApp.applyAmt}" type="text" class="form-control formatNum">
			 		<span class="input-group-addon">元</span>
				</div>
			</span>
		</div>
	</div>
</div>
<div class="row">
	<div class="form-group-wdapproval form-group">
		<label class="col-xs-12 col-sm-4 control-label no-padding-right">
			<font color="red">* </font>批复期限：</label>
		<div class="col-xs-12 col-sm-8">
			<input name="term" value="${projApp.applyTerm }" type="text"
				class="form-control">
		</div>
	</div>
	<div class="form-group-wdapproval form-group">
		<label class="col-xs-12 col-sm-4 control-label no-padding-right">
			<font color="red">* </font>批复期限单位：</label>
		<div class="col-xs-12 col-sm-8 pf">
			<select class="form-control" name="applyTermUnitSelect" disabled="disabled" id="applyTermUnitSelect">
				<dd:options codeType="TermUnitCd" codeVals="2,3" selected="${projApp.applyTermUnit }" />
			</select>
			<input type="hidden" name="termUnit" value="" />
			<input type="hidden" name="applyTermUnitFromProduct" id="applyTermUnitFromProduct" value="${productConfig.replyPeriodUnit }">
		</div>
	</div>
</div>
<div class="row">
	<div class="form-group-wdapproval form-group">
		<label class="col-xs-12 col-sm-4 control-label no-padding-right">
			<font color="red">* </font>还款方式：</label>
		<div class="col-xs-12 col-sm-8">
			<select name="approveRepayingMode" class="form-control">
				<dd:options codeType="RepaymentMode"
					selected="${projApp.repayingMode }" />
			</select>
		</div>
	</div>
	<div class="form-group-wdapproval form-group">
		<label class="col-xs-12 col-sm-4 control-label no-padding-right">
			<font color="red">* </font>利率类型：</label>
		<div class="col-xs-12 col-sm-8">
			<select name="approveIrTypeCd" id="interestRateType"
				class="form-control">
				<dd:options codeType="InterestRateAdjustment"
					selected="${projApp.irTypeCd }" />
			</select>
		</div>
	</div>
</div>
<div class="row">
	<div class="form-group-wdapproval form-group float_ir">
		<label class="col-xs-12 col-sm-4 control-label no-padding-right">
			<font color="red">* </font>调整周期方式：</label>
		<div class="col-xs-12 col-sm-8">
			<select name="approveAdjustPeriod" id="interestRateType"
				class="form-control">
				<dd:options codeType="AdjustPeriod"
					selected="${projApp.adjustPeriod }" />
			</select>
		</div>
	</div>
	<div class="form-group-wdapproval form-group float_ir">
		<label class="col-xs-12 col-sm-4 control-label no-padding-right">
			<font color="red">* </font>利率上浮比例：</label>
		<div class="col-xs-12 col-sm-8">
			<span class="block input-icon input-icon-right"> 
			<div class="input-group">
			<input name="approveFloatRateStr" id="irFloatScale"
				value="<fmt:formatNumber  value="${projApp.floatRate*100}" type="number" pattern="0.0000" />" type="text" id="inputError2"
				class="width-100"> 
			 <span class="input-group-addon">%</span>
			</div>
			</span>
		</div>
	</div>
</div>
<div class="row">
	<div class="form-group-wdapproval form-group fixed_ir">
		<label class="col-xs-12 col-sm-4 control-label no-padding-right">
			<font color="red">* </font>年利率：</label>
		<div class="col-xs-12 col-sm-8">
			<span class="block input-icon input-icon-right"> 
			<div class="input-group">
			<input name="approveRateValueStr" id="yearRate"
				value="<fmt:formatNumber  value="${projApp.rateValue*100 }" type="number" pattern="0.00" />" type="text" id="inputError2"
				class="width-100"> 
			 <span class="input-group-addon">%</span>
			</div>
			</span>
		</div>
	</div>
</div>
<div class="row">
	<div class="form-group-wdapproval form-group">
		<label for="inputInfo"class="col-xs-12 col-sm-4 control-label no-padding-right"> <font color="red">*</font>批复意见：</label>
		<div class="col-xs-12 col-sm-8">
			<textarea name="replyOpinion" style="width:256%;"placeholder="请输入您宝贵的批复意见" role="comments_content" id="form-field-8"
				class="form-control"></textarea>
		</div>
	</div>
</div>
<div sign="repaymentPlanDiv" style="display: none;">
	<h4 class="blue">还款计划</h4>
	<div id="editableRepayPlan1" class="form-group" style="display: none;">
		<div class="row">
			<div class="col-md-4">
				<label class="col-sm-5 control-label" for="startDate"> 开始日期：
				</label>
				<div class="col-sm-7">
					<input type="text" class="form-control" id="startDate"
						name="startDate" data-date-format="yyyy-mm-dd">
				</div>
			</div>
			<div class="col-md-3">
				<label class="col-sm-6 control-label" for="monthDate"> 间隔月数：
				</label>
				<div class="col-sm-6">
					<input type="text" class="form-control" id="monthDate"
						name="monthDate">
				</div>
			</div>
			<div class="col-md-4">
				<label class="col-sm-6 control-label" for="repayDateForCount">
					每月还款日：</label>
				<div class="col-sm-4">
					<input type="text" class="form-control" id="repayDateForCount"
						name="repayDateForCount">
				</div>
			</div>
		</div>
	</div>

	<div class="row">
		<div class="col-xs-12">
			<table id="editableRepayPlan2" style="width: 100%;display: none;">
				<tr>
					<td align="right"><button id="addRepayPlan" type="button"
							class="btn btn-sm btn-success">
							<i class="ace-icon fa fa-plus"></i> 新增
						</button>
						<button id="batchInitRepayPlan" type="button"
							class="btn btn-sm btn-success" style="display:block">
							<i class="ace-icon fa fa-plus"></i> 批量初始化
						</button>
					</td>
				</tr>
			</table>
			<table sign="repaymentPlanTable"
				class="table table-striped table-hover">
				<thead>
					<tr>
						<th>计划还款时间</th>
						<th>计划还款本金金额（元）</th>
						<th>操作</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
</div>