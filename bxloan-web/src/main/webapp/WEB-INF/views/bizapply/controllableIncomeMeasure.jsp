<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../../commons/taglibs.jsp"%>
<form class="form-horizontal" id="measureForm">

	<input type="hidden" name="projectId"
		value="${projectApplication.projectId}" /> <input type="hidden"
		name="id" />

	<h4 class="blue">月收入信息（元）</h4>

	<div class="form-group">
		<div class="row">
			<div class="col-md-7 ie8">
				<table style="width: 100%;">
					<tr>
						<td align="center">
							<h6 class="green">借款人月收入（税后）</h6>
						</td>
					</tr>
				</table>
			</div>

			<div class="col-md-5 ie8">
				<table style="width: 60%;">
					<tr>
						<td align="center">
							<h6 class="green">借款人配偶月收入（税后）</h6>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</div>

	<div class="form-group">
		<label class="col-xs-12 col-sm-2 control-label no-padding-right">
			<font color='red'> * </font>工资： </label>
		<div class="col-xs-12 col-sm-3">
			<span class="block input-icon input-icon-right"> <input
				type="text" class="form-control required isPositiveNumberTwo"
				id="borrowerSalary" name="borrowerSalary"> </span>
		</div>
		<div class="help-block col-xs-12 col-sm-reset inline"></div>

		<label class="col-xs-12 col-sm-2 control-label no-padding-right">
			<font color='red'> * </font>工资： </label>
		<div class="col-xs-12 col-sm-3">
			<span class="block input-icon input-icon-right"> <input
				type="text" class="form-control required isPositiveNumberTwo"
				id="borrowerSpouseSalary" name="borrowerSpouseSalary"> </span>
		</div>
		<div class="help-block col-xs-12 col-sm-reset inline"></div>
	</div>

	<div class="form-group">
		<label class="col-xs-12 col-sm-2 control-label no-padding-right">
			<font color='red'> * </font>奖金： </label>
		<div class="col-xs-12 col-sm-3">
			<span class="block input-icon input-icon-right"> <input
				type="text" class="form-control required isPositiveNumberTwo"
				id="borrowerBonus" name="borrowerBonus"> </span>
		</div>
		<div class="help-block col-xs-12 col-sm-reset inline"></div>

		<label class="col-xs-12 col-sm-2 control-label no-padding-right">
			<font color='red'> * </font>奖金： </label>
		<div class="col-xs-12 col-sm-3">
			<span class="block input-icon input-icon-right"> <input
				type="text" class="form-control required isPositiveNumberTwo"
				id="borrowerSpouseBonus" name="borrowerSpouseBonus"> </span>
		</div>
		<div class="help-block col-xs-12 col-sm-reset inline"></div>
	</div>

	<div class="form-group">
		<label class="col-xs-12 col-sm-2 control-label no-padding-right">
			<font color='red'> * </font>福利（不包括报销）： </label>
		<div class="col-xs-12 col-sm-3">
			<span class="block input-icon input-icon-right"> <input
				type="text" class="form-control required isPositiveNumberTwo"
				id="borrowerWelfare" name="borrowerWelfare"> </span>
		</div>
		<div class="help-block col-xs-12 col-sm-reset inline"></div>

		<label class="col-xs-12 col-sm-2 control-label no-padding-right">
			<font color='red'> * </font>福利（不包括报销）： </label>
		<div class="col-xs-12 col-sm-3">
			<span class="block input-icon input-icon-right"> <input
				type="text" class="form-control required isPositiveNumberTwo"
				id="borrowerSpouseWelfare" name="borrowerSpouseWelfare"> </span>
		</div>
		<div class="help-block col-xs-12 col-sm-reset inline"></div>
	</div>

	<div class="form-group">
		<label class="col-xs-12 col-sm-2 control-label no-padding-right">
			<font color='red'> * </font>借款人月收入合计： </label>
		<div class="col-xs-12 col-sm-3">
			<span class="block input-icon input-icon-right"> <input
				type="text" class="form-control required isPositiveNumberTwo"
				id="borrowerIncome" name="borrowerIncome" readonly="readonly">
			</span>
		</div>
		<div class="help-block col-xs-12 col-sm-reset inline"></div>

		<label class="col-xs-12 col-sm-2 control-label no-padding-right">
			<font color='red'> * </font>借款人配偶月收入合计： </label>
		<div class="col-xs-12 col-sm-3">
			<span class="block input-icon input-icon-right"> <input
				type="text" class="form-control required isPositiveNumberTwo"
				id="borrowerSpouseIncome" name="borrowerSpouseIncome"
				readonly="readonly"> </span>
		</div>
		<div class="help-block col-xs-12 col-sm-reset inline"></div>
	</div>

	<div class="form-group">
		<label class="col-xs-12 col-sm-2 control-label no-padding-right">
			<font color='red'> * </font>家庭月收入合计： </label>
		<div class="col-xs-12 col-sm-3">
			<span class="block input-icon input-icon-right"> <input
				type="text" class="form-control required isPositiveNumberTwo"
				id="familyIncome" name="familyIncome" readonly="readonly"> </span>
		</div>
		<div class="help-block col-xs-12 col-sm-reset inline"></div>
	</div>

	<h4 class="blue">月负债信息（元）</h4>

	<div class="form-group">
		<div class="row">
			<div class="col-md-7 ie8">
				<table style="width: 100%;">
					<tr>
						<td align="center">
							<h6 class="green">借款人月还款额</h6>
						</td>
					</tr>
				</table>
			</div>

			<div class="col-md-5 ie8">
				<table style="width: 60%;">
					<tr>
						<td align="center">
							<h6 class="green">借款人配偶月还款额</h6>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</div>

	<div class="form-group">
		<label class="col-xs-12 col-sm-2 control-label no-padding-right">
			<font color='red'> * </font>房贷： </label>
		<div class="col-xs-12 col-sm-3">
			<span class="block input-icon input-icon-right"> <input
				type="text" class="form-control required isPositiveNumberTwo"
				id="borrowerHousingloan" name="borrowerHousingloan"> </span>
		</div>
		<div class="help-block col-xs-12 col-sm-reset inline"></div>

		<label class="col-xs-12 col-sm-2 control-label no-padding-right">
			<font color='red'> * </font>房贷： </label>
		<div class="col-xs-12 col-sm-3">
			<span class="block input-icon input-icon-right"> <input
				type="text" class="form-control required isPositiveNumberTwo"
				id="borrowerSpouseHousingloan" name="borrowerSpouseHousingloan">
			</span>
		</div>
		<div class="help-block col-xs-12 col-sm-reset inline"></div>
	</div>

	<div class="form-group">
		<label class="col-xs-12 col-sm-2 control-label no-padding-right">
			<font color='red'> * </font>车贷： </label>
		<div class="col-xs-12 col-sm-3">
			<span class="block input-icon input-icon-right"> <input
				type="text" class="form-control required isPositiveNumberTwo"
				id="borrowerCarloan" name="borrowerCarloan"> </span>
		</div>
		<div class="help-block col-xs-12 col-sm-reset inline"></div>

		<label class="col-xs-12 col-sm-2 control-label no-padding-right">
			<font color='red'> * </font>车贷： </label>
		<div class="col-xs-12 col-sm-3">
			<span class="block input-icon input-icon-right"> <input
				type="text" class="form-control required isPositiveNumberTwo"
				id="borrowerSpouseCarloan" name="borrowerSpouseCarloan"> </span>
		</div>
		<div class="help-block col-xs-12 col-sm-reset inline"></div>
	</div>

	<div class="form-group">
		<label class="col-xs-12 col-sm-2 control-label no-padding-right">
			<font color='red'> * </font>信用卡： </label>
		<div class="col-xs-12 col-sm-3">
			<span class="block input-icon input-icon-right"> <input
				type="text" class="form-control required isPositiveNumberTwo"
				id="borrowerCredit" name="borrowerCredit"> </span>
		</div>
		<div class="help-block col-xs-12 col-sm-reset inline"></div>

		<label class="col-xs-12 col-sm-2 control-label no-padding-right">
			<font color='red'> * </font>信用卡： </label>
		<div class="col-xs-12 col-sm-3">
			<span class="block input-icon input-icon-right"> <input
				type="text" class="form-control required isPositiveNumberTwo"
				id="borrowerSpouseCredit" name="borrowerSpouseCredit"> </span>
		</div>
		<div class="help-block col-xs-12 col-sm-reset inline"></div>
	</div>

	<div class="form-group">
		<label class="col-xs-12 col-sm-2 control-label no-padding-right">
			<font color='red'> * </font>其他负债： </label>
		<div class="col-xs-12 col-sm-3">
			<span class="block input-icon input-icon-right"> <input
				type="text" class="form-control required isPositiveNumberTwo"
				id="borrowerOthers" name="borrowerOthers"> </span>
		</div>
		<div class="help-block col-xs-12 col-sm-reset inline"></div>

		<label class="col-xs-12 col-sm-2 control-label no-padding-right">
			<font color='red'> * </font>其他负债： </label>
		<div class="col-xs-12 col-sm-3">
			<span class="block input-icon input-icon-right"> <input
				type="text" class="form-control required isPositiveNumberTwo"
				id="borrowerSpouseOthers" name="borrowerSpouseOthers"> </span>
		</div>
		<div class="help-block col-xs-12 col-sm-reset inline"></div>
	</div>

	<div class="form-group">
		<label class="col-xs-12 col-sm-2 control-label no-padding-right">
			<font color='red'> * </font>借款人月还款额合计： </label>
		<div class="col-xs-12 col-sm-3">
			<span class="block input-icon input-icon-right"> <input
				type="text" class="form-control required isPositiveNumberTwo"
				id="borrowerPay" name="borrowerPay" readonly="readonly"> </span>
		</div>
		<div class="help-block col-xs-12 col-sm-reset inline"></div>

		<label class="col-xs-12 col-sm-2 control-label no-padding-right">
			<font color='red'> * </font>借款人配偶月还款额合计： </label>
		<div class="col-xs-12 col-sm-3">
			<span class="block input-icon input-icon-right"> <input
				type="text" class="form-control required isPositiveNumberTwo"
				id="borrowerSpousePay" name="borrowerSpousePay" readonly="readonly">
			</span>
		</div>
		<div class="help-block col-xs-12 col-sm-reset inline"></div>
	</div>

	<div class="form-group">
		<label class="col-xs-12 col-sm-2 control-label no-padding-right">
			<font color='red'> * </font>家庭月还款额合计： </label>
		<div class="col-xs-12 col-sm-3">
			<span class="block input-icon input-icon-right"> <input
				type="text" class="form-control required isPositiveNumberTwo" id="familyPay"
				name="familyPay" readonly="readonly"> </span>
		</div>
		<div class="help-block col-xs-12 col-sm-reset inline"></div>
	</div>

	<h4 class="blue">月可支配收入（元）</h4>

	<div class="form-group">
		<label class="col-xs-12 col-sm-2 control-label no-padding-right">
			<font color='red'> * </font>家庭月可支配收入： </label>
		<div class="col-xs-12 col-sm-3">
			<span class="block input-icon input-icon-right"> <input
				type="text" class="form-control required" id="familyControllableIncome"
				name="familyControllableIncome" readonly="readonly"> </span>
		</div>
		<div class="help-block col-xs-12 col-sm-reset inline"></div>
	</div>

	<c:if test="${consultLocation == null }">
		<hr>
		<div class="row">
			<div class="col-md-6 col-md-offset-5">
				<c:if test="${judgeType != 'check' }">
					<button type="submit" id="saveMeasure" class="btn btn-sm btn-primary">
						<i class="ace-icon fa fa-floppy-o"></i> 保存
					</button>
				</c:if>
				<%-- <c:if test="${judgeType == 'check' }">
					<!-- <button type="button" name="backToDashboard"
						class="btn btn-sm btn-default">
						<i class="ace-icon fa fa-arrow-left"></i>返回1
					</button> -->
				</c:if> --%>
			</div>
		</div>
	</c:if>

</form>