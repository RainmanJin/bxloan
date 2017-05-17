<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.coamc-tech.com/taglibs/datadict" prefix="dd"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
			<input name="approveAmt" id="approveAmt" value="${projApp.applyAmt  }" type="text"
				class="form-control num_amt_2fixed isPositiveNumberTwoOfThousands">
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
			<input name="term" value="${projApp.applyTerm }" id="applyTerm" type="text"
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
			<font color="red">* </font>批复授信类型：</label>
		<div class="col-xs-12 col-sm-8">
			<select name="approveCreditType" class="form-control">
				<dd:options codeType="CreditType"
					selected="${projApp.creditType }" />
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
			<input name="approveFloatRateStr" id="approveFloatRateStr"
				value="${empty projApp.floatRate?'':projApp.floatRate*100}" type="text"
				class="width-100 num_4fixed"> 
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
				class="width-100 num_2fixed"> 
			 <span class="input-group-addon">%</span>
			</div>
			</span>
		</div>
	</div>
</div>
