<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../../commons/taglibs.jsp"%>
<form class="form-horizontal" id="quotaMeasureForm" action="bizapply/quotaMeasureForm" method="post" >
	<input type="hidden" id="partyId" name="partyId" value="${party.partyId }" />
	<input type="hidden" name="projectId" value="${projectApplication.projectId }" />
	<input type="hidden" name="budgetId" id="budgetId" />
	<input type="hidden" name="orgId" value="${curUserLogOrgid }">

	<div>
		<h4 class="blue">基本信息</h4>
	</div>

	<div class="form-group">
		<label class="col-xs-12 col-sm-2 control-label no-padding-right">
			小贷公司： </label>
		<div class="col-xs-12 col-sm-3">
			<span class="block input-icon input-icon-right">
			<input type="text" name="orgName" class="form-control" value="${curUserLogOrgname }" readonly="readonly" /> </span>
		</div>
		<div class="help-block col-xs-12 col-sm-reset inline"></div>

		<label class="col-xs-12 col-sm-3 control-label no-padding-right">
			业务编号： </label>
		<div class="col-xs-12 col-sm-3">
			<span class="block input-icon input-icon-right"> <input
				type="text" class="form-control" value="${projectApplication.projectNo }" readonly="readonly" /> </span>
		</div>
		<div class="help-block col-xs-12 col-sm-reset inline"></div>
	</div>

	<div class="form-group">
		<label class="col-xs-12 col-sm-2 control-label no-padding-right">借款人姓名：</label>
		<div class="col-xs-12 col-sm-3">
			<span class="block input-icon input-icon-right"> <input
				type="text" class="form-control"
				value="${projectApplication.customerName }" readonly="readonly" />
			</span>
		</div>
		<div class="help-block col-xs-12 col-sm-reset inline"></div>

		<label class="col-xs-12 col-sm-3 control-label no-padding-right">证件种类：</label>
		<div class="col-xs-12 col-sm-3">
			<span class="block input-icon input-icon-right"> <select
				class="form-control" disabled="disabled">
					<dd:options codeType="CertificateType"
						selected="${individual.certificateTypeCd }" />
			</select> </span>
		</div>
		<div class="help-block col-xs-12 col-sm-reset inline"></div>
	</div>

	<div class="form-group">
		<label class="col-xs-12 col-sm-2 control-label no-padding-right">客户类型：</label>
		<div class="col-xs-12 col-sm-3">
			<span class="input-icon block input-icon-right"> <select
				class="form-control" disabled="disabled">
					<dd:options codeType="CustomerType"
						selected="${party.partyTypeCd }" />
			</select> </span>
		</div>
		<div class="help-block col-xs-12 col-sm-reset inline"></div>

	</div>

	<div>
		<h4 class="blue">额度生成因子</h4>
	</div>
	<div class="form-group">
		<label class="col-xs-12 col-sm-2 control-label no-padding-right"><font
			color="red"> * </font>申请金额（元）：</label>
		<div class="col-xs-12 col-sm-3">
			<span class="input-icon block input-icon-right"> <input
				type="text" name="applyAmt" id="applyAmt2" class="form-control required"
				value="${projectApplication.applyAmt }" readonly="readonly" /> </span>
		</div>
		<div class="help-block col-xs-12 col-sm-reset inline"></div>

		<label class="col-xs-12 col-sm-3 control-label no-padding-right"><font
			color="red"> * </font>本笔贷款月还款额（元）：</label>
		<div class="col-xs-12 col-sm-3">
			<span class="input-icon block input-icon-right"> <input
				type="text" name="repaymentThisTime" id="repaymentThisTime"
				value="${quotaMeasure.repaymentThisTime }"
				class="form-control required" readonly="readonly" /> </span>
		</div>
		<div class="help-block col-xs-12 col-sm-reset inline"></div>
	</div>

	<div class="form-group">
		<label class="col-xs-12 col-sm-2 control-label no-padding-right"><font
			color="red"> * </font>认定的月收入（元）：</label>
		<div class="col-xs-12 col-sm-3">
			<span class="input-icon block input-icon-right"> <input
				type="text" name="monthIncome" id="monthIncome2"
				value="${quotaMeasure.monthIncome }" class="form-control required"
				readonly="readonly" /> </span>
		</div>
		<div class="help-block col-xs-12 col-sm-reset inline"></div>

		<label class="col-xs-12 col-sm-3 control-label no-padding-right"><font
			color="red"> * </font>其他贷款（包括信用卡）月还款额（元）：</label>
		<div class="col-xs-12 col-sm-3">
			<span class="input-icon block input-icon-right"> <input
				type="text" name="otherRepayment" id="otherRepayment"
				value="${quotaMeasure.otherRepayment }"
				class="form-control required" readonly="readonly" /> </span>
		</div>
		<div class="help-block col-xs-12 col-sm-reset inline"></div>

	</div>

	<div class="form-group">
		<label class="col-xs-12 col-sm-2 control-label no-padding-right"><font
			color="red"> * </font>认定的月可支配收入（元）：</label>
		<div class="col-xs-12 col-sm-3">
			<span class="input-icon block input-icon-right"> <input
				type="text" name="controllableIncome" id="controllableIncome"
				value="${quotaMeasure.controllableIncome }"
				class="form-control required" readonly="readonly" /> </span>
		</div>
		<div class="help-block col-xs-12 col-sm-reset inline"></div>

		<label class="col-xs-12 col-sm-3 control-label no-padding-right"><font
			color="red"> * </font>期限：</label>
		<div class="col-xs-12 col-sm-3">
			<span class="input-icon block input-icon-right"> <select
				id="calcLoanTerm" name="calcLoanTerm" class="form-control required"
				style="height: 34px;">
					<dd:options codeType="LoanTerm"
						selected="${quotaMeasure.calcLoanTerm }" />
			</select> </span>
		</div>
		<div class="help-block col-xs-12 col-sm-reset inline"></div>
	</div>

	<div>
		<h4 class="blue">推荐额度（系统生成）</h4>
	</div>
	<div class="form-group">
		<label class="col-xs-12 col-sm-2 control-label no-padding-right"><font
			color="red"> * </font>金额（元）：</label>
		<div class="col-xs-12 col-sm-3">
			<span class="input-icon block input-icon-right"> <input
				type="text" name="calcLoanAmt" id="calcLoanAmt"
				value="${quotaMeasure.calcLoanAmt }" class="form-control required" readonly="readonly" />
			</span>
		</div>
		<div class="help-block col-xs-12 col-sm-reset inline"></div>

	</div>

	<hr>

	<c:if test="${consultLocation == null }">
		<div class="row">
			<div class="col-md-6 col-md-offset-5">
				<%-- <c:if test="${judgeType == 'check' }">
					<!-- <button type="button" name="backToDashboard"
						class="btn btn-sm btn-default">
						<i class="ace-icon fa fa-arrow-left"></i>返回
					</button> -->
				</c:if> --%>
				<c:if test="${judgeType != 'check' }">
					<button class="btn btn-primary" id="saveQuotaMeasure">
						<i class="ace-icon fa fa-floppy-o"></i> 保存
					</button>
				</c:if>
			</div>
		</div>
	</c:if>
</form>