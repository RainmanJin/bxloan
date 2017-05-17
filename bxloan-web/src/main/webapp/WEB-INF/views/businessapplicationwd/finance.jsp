<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../commons/taglibs.jsp"%>
<!--[if IE 8]>
<style>
.ie8{ width:50%; float:left;}
</style>
<![endif]-->
<div class="row">
	<div class="col-md-12">
		<form id="financeForm" class="form-horizontal">

			<input type="hidden" name="projectId" value="${vo.projectId }">
			<input type="hidden" id="financeId" name="id"
				value="${salaBusiCustFinalcial.id }">

			<!-- 企业客户 或 经营类个人客户 -->
			<c:if test="${party.partyTypeCd == '1' || vo.employmentType == '3'}">

				<input type="hidden" name="finalcialType" value="2">

				<h4 class="blue">资产负债信息</h4>

				<div class="form-group">
					<div class="row">
						<div class="col-md-7 ie8">
							<table style="width: 100%;">
								<tr>
									<td align="center">
										<h5 class="blue">资产</h5></td>
								</tr>
							</table>
						</div>

						<div class="col-md-5 ie8">
							<table style="width: 60%;">
								<tr>
									<td align="center">
										<h5 class="blue">负债</h5></td>
								</tr>
							</table>
						</div>
					</div>
				</div>

				<div class="form-group">
					<label class="col-xs-12 col-sm-2 control-label no-padding-right"
						for="assCashAmtq"> <font color='red'> * </font>现金/存款（元）： </label>
					<div class="col-xs-12 col-sm-3">
						<span class="block input-icon input-icon-right"> <input
							type="text" class="form-control required isNotNegative"
							id="assCashAmtq" name="assCashAmt"
							value="${salaBusiCustFinalcial.assCashAmt }"> </span>
					</div>
					<div class="help-block col-xs-12 col-sm-reset inline"></div>

					<label class="col-xs-12 col-sm-2 control-label no-padding-right"
						for="liaBusinessLoanAmtq"> <font color='red'> * </font>经营型贷款（元）： </label>
					<div class="col-xs-12 col-sm-3">
						<span class="block input-icon input-icon-right"> <input
							type="text" class="form-control required isNotNegative"
							id="liaBusinessLoanAmtq" name="liaBusinessLoanAmt"
							value="${salaBusiCustFinalcial.liaBusinessLoanAmt }"> </span>
					</div>
					<div class="help-block col-xs-12 col-sm-reset inline"></div>
				</div>

				<div class="form-group">
					<label class="col-xs-12 col-sm-2 control-label no-padding-right"
						for="assReceivablesAmtq"> <font color='red'> * </font>应收款（元）： </label>
					<div class="col-xs-12 col-sm-3">
						<span class="block input-icon input-icon-right"> <input
							type="text" class="form-control required isNotNegative"
							id="assReceivablesAmtq" name="assReceivablesAmt"
							value="${salaBusiCustFinalcial.assReceivablesAmt }"> </span>
					</div>
					<div class="help-block col-xs-12 col-sm-reset inline"></div>

					<label class="col-xs-12 col-sm-2 control-label no-padding-right"
						for="liaHouseloanAmtq"> <font color='red'> * </font>房贷（元）： </label>
					<div class="col-xs-12 col-sm-3">
						<span class="block input-icon input-icon-right"> <input
							type="text" class="form-control required isNotNegative"
							id="liaHouseloanAmtq" name="liaHouseloanAmt"
							value="${salaBusiCustFinalcial.liaHouseloanAmt }"> </span>
					</div>
					<div class="help-block col-xs-12 col-sm-reset inline"></div>
				</div>

				<div class="form-group">
					<label class="col-xs-12 col-sm-2 control-label no-padding-right"
						for="assStockAmtq"> <font color='red'> * </font>存货（元）： </label>
					<div class="col-xs-12 col-sm-3">
						<span class="block input-icon input-icon-right"> <input
							type="text" class="form-control required isNotNegative"
							id="assStockAmtq" name="assStockAmt"
							value="${salaBusiCustFinalcial.assStockAmt }"> </span>
					</div>
					<div class="help-block col-xs-12 col-sm-reset inline"></div>

					<label class="col-xs-12 col-sm-2 control-label no-padding-right"
						for="liaCarloanAmtq"> <font color='red'> * </font>车贷（元）： </label>
					<div class="col-xs-12 col-sm-3">
						<span class="block input-icon input-icon-right"> <input
							type="text" class="form-control required isNotNegative"
							id="liaCarloanAmtq" name="liaCarloanAmt"
							value="${salaBusiCustFinalcial.liaCarloanAmt }"> </span>
					</div>
					<div class="help-block col-xs-12 col-sm-reset inline"></div>
				</div>

				<div class="form-group">
					<label class="col-xs-12 col-sm-2 control-label no-padding-right"
						for="assHousePropertyAmtq"> <font color='red'> * </font>房产（元）： </label>
					<div class="col-xs-12 col-sm-3">
						<span class="block input-icon input-icon-right"> <input
							type="text" class="form-control required isNotNegative"
							id="assHousePropertyAmtq" name="assHousePropertyAmt"
							value="${salaBusiCustFinalcial.assHousePropertyAmt }"> </span>
					</div>
					<div class="help-block col-xs-12 col-sm-reset inline"></div>

					<label class="col-xs-12 col-sm-2 control-label no-padding-right"
						for="liaCreditcardq"> <font color='red'> * </font>信用卡（元）： </label>
					<div class="col-xs-12 col-sm-3">
						<span class="block input-icon input-icon-right"> <input
							type="text" class="form-control required isNotNegative"
							id="liaCreditcardq" name="liaCreditcard"
							value="${salaBusiCustFinalcial.liaCreditcard }"> </span>
					</div>
					<div class="help-block col-xs-12 col-sm-reset inline"></div>
				</div>

				<div class="form-group">
					<label class="col-xs-12 col-sm-2 control-label no-padding-right"
						for="assCarAmtq"> <font color='red'> * </font>车辆（元）： </label>
					<div class="col-xs-12 col-sm-3">
						<span class="block input-icon input-icon-right"> <input
							type="text" class="form-control required isNotNegative"
							id="assCarAmtq" name="assCarAmt"
							value="${salaBusiCustFinalcial.assCarAmt }"> </span>
					</div>
					<div class="help-block col-xs-12 col-sm-reset inline"></div>
				</div>

				<div class="form-group">
					<label class="col-xs-12 col-sm-2 control-label no-padding-right"
						for="assOtherAmtq"> <font color='red'> * </font>其它（元）： </label>
					<div class="col-xs-12 col-sm-3">
						<span class="block input-icon input-icon-right"> <input
							type="text" class="form-control required isNotNegative"
							id="assOtherAmtq" name="assOtherAmt"
							value="${salaBusiCustFinalcial.assOtherAmt }"> </span>
					</div>
					<div class="help-block col-xs-12 col-sm-reset inline"></div>
				</div>

				<div class="form-group">
					<label class="col-xs-12 col-sm-2 control-label no-padding-right"
						for="assetsTotalAmtq"> <font color='red'> * </font>资产合计（元）： </label>
					<div class="col-xs-12 col-sm-3">
						<span class="block input-icon input-icon-right"> <input
							type="text" class="form-control required" id="assetsTotalAmtq"
							name="assetsTotalAmt" readonly="readonly"
							value="${salaBusiCustFinalcial.assetsTotalAmt }"> </span>
					</div>
					<div class="help-block col-xs-12 col-sm-reset inline"></div>

					<label class="col-xs-12 col-sm-2 control-label no-padding-right"
						for="liabilitiesTotalAmtq"> <font color='red'> * </font>负债合计（元）： </label>
					<div class="col-xs-12 col-sm-3">
						<span class="block input-icon input-icon-right"> <input
							type="text" class="form-control required" id="liabilitiesTotalAmtq"
							name="liabilitiesTotalAmt" readonly="readonly"
							value="${salaBusiCustFinalcial.liabilitiesTotalAmt }"> </span>
					</div>
					<div class="help-block col-xs-12 col-sm-reset inline"></div>
				</div>
				
				<br>
				
				<div class="form-group">
					<label class="col-xs-12 col-sm-2 control-label no-padding-right"
						for="assetsTotalAmtg"> <font color='red'> * </font>所有者权益： </label>
					<div class="col-xs-12 col-sm-3">
						<span class="block input-icon input-icon-right"> <input
							type="text" class="form-control required" id="proprietorshipq"
							name="proprietorship" readonly="readonly"
							value="${salaBusiCustFinalcial.proprietorship }"> </span>
					</div>
					<div class="help-block col-xs-12 col-sm-reset inline"></div>
				</div>

				<h4 class="blue">收入信息</h4>

				<div class="form-group">
					<label class="col-xs-12 col-sm-2 control-label no-padding-right"
						for="incomeMonthBussAmtq"> <font color='red'> * </font>月营业额（元）： </label>
					<div class="col-xs-12 col-sm-3">
						<span class="block input-icon input-icon-right"> <input
							type="text" class="form-control required isNotNegative"
							id="incomeMonthBussAmtq" name="incomeMonthBussAmt"
							value="${salaBusiCustFinalcial.incomeMonthBussAmt }"> </span>
					</div>
					<div class="help-block col-xs-12 col-sm-reset inline"></div>

					<label class="col-xs-12 col-sm-2 control-label no-padding-right"
						for="incomeOtherAmtq"> <font color='red'> * </font>其它收入（元）： </label>
					<div class="col-xs-12 col-sm-3">
						<span class="block input-icon input-icon-right"> <input
							type="text" class="form-control required isNotNegative"
							id="incomeOtherAmtq" name="incomeOtherAmt"
							value="${salaBusiCustFinalcial.incomeOtherAmt }"> </span>
					</div>
					<div class="help-block col-xs-12 col-sm-reset inline"></div>
				</div>

				<div class="form-group">
					<label class="col-xs-12 col-sm-2 control-label no-padding-right"
						for="grossprofitAmtq"> <font color='red'> * </font>毛利润（元）： </label>
					<div class="col-xs-12 col-sm-3">
						<span class="block input-icon input-icon-right"> <input
							type="text" class="form-control required isNotNegative"
							id="grossprofitAmtq" name="grossprofitAmt"
							value="${salaBusiCustFinalcial.grossprofitAmt }"> </span>
					</div>
					<div class="help-block col-xs-12 col-sm-reset inline"></div>

					<label class="col-xs-12 col-sm-2 control-label no-padding-right"
						for="familypayAmtq"> <font color='red'> * </font>家庭开支（元）： </label>
					<div class="col-xs-12 col-sm-3">
						<span class="block input-icon input-icon-right"> <input
							type="text" class="form-control required isNotNegative"
							id="familypayAmtq" name="familypayAmt"
							value="${salaBusiCustFinalcial.familypayAmt }"> </span>
					</div>
					<div class="help-block col-xs-12 col-sm-reset inline"></div>
				</div>

				<div class="form-group">
					<label class="col-xs-12 col-sm-2 control-label no-padding-right"
						for="netprofitAmtq"> <font color='red'> * </font>净利润（元）： </label>
					<div class="col-xs-12 col-sm-3">
						<span class="block input-icon input-icon-right"> <input
							type="text" class="form-control required isNotNegative"
							id="netprofitAmtq" name="netprofitAmt"
							value="${salaBusiCustFinalcial.netprofitAmt }"> </span>
					</div>
					<div class="help-block col-xs-12 col-sm-reset inline"></div>

					<label class="col-xs-12 col-sm-2 control-label no-padding-right"
						for="otherloanMonthrepayAmtq"> <font color='red'> * </font>其他贷款月还款额（元）： </label>
					<div class="col-xs-12 col-sm-3">
						<span class="block input-icon input-icon-right"> <input
							type="text" class="form-control required isNotNegative"
							id="otherloanMonthrepayAmtq" name="otherloanMonthrepayAmt"
							value="${salaBusiCustFinalcial.otherloanMonthrepayAmt }">
						</span>
					</div>
					<div class="help-block col-xs-12 col-sm-reset inline"></div>
				</div>

				<div class="form-group">
					<label class="col-xs-12 col-sm-2 control-label no-padding-right"
						for="monthdominatIncomeAmtq"> <font color='red'> * </font>月可支配收入（元）： </label>
					<div class="col-xs-12 col-sm-3">
						<span class="block input-icon input-icon-right"> <input
							type="text" class="form-control required isNotNegative"
							id="monthdominatIncomeAmtq" name="monthdominatIncomeAmt" readonly="readonly"
							value="${salaBusiCustFinalcial.monthdominatIncomeAmt }">
						</span>
					</div>
					<div class="help-block col-xs-12 col-sm-reset inline"></div>
				</div>

			</c:if>

			<!-- 个人客户 -->
			<!-- 受薪||学生 -->
			<c:if test="${party.partyTypeCd == '2' && (vo.employmentType == '1' ||vo.employmentType == '4')}">

				<input type="hidden" name="finalcialType" value="1">

				<h4 class="blue">资产负债信息</h4>

				<div class="form-group">
					<div class="row">
						<div class="col-md-7">
							<table style="width: 100%;">
								<tr>
									<td align="center">
										<h5 class="blue">资产</h5></td>
								</tr>
							</table>
						</div>

						<div class="col-md-5">
							<table style="width: 60%;">
								<tr>
									<td align="center">
										<h5 class="blue">负债</h5></td>
								</tr>
							</table>
						</div>
					</div>
				</div>

				<div class="form-group">
					<label class="col-xs-12 col-sm-2 control-label no-padding-right"
						for="assCashAmtg"> <font color='red'> * </font>现金/存款（元）： </label>
					<div class="col-xs-12 col-sm-3">
						<span class="block input-icon input-icon-right"> <input
							type="text" class="form-control required isNotNegative"
							id="assCashAmtg" name="assCashAmt"
							value="${salaBusiCustFinalcial.assCashAmt }"> </span>
					</div>
					<div class="help-block col-xs-12 col-sm-reset inline"></div>

					<label class="col-xs-12 col-sm-2 control-label no-padding-right"
						for="liaHouseloanAmtg"> <font color='red'> * </font>房贷（元）： </label>
					<div class="col-xs-12 col-sm-3">
						<span class="block input-icon input-icon-right"> <input
							type="text" class="form-control required isNotNegative"
							id="liaHouseloanAmtg" name="liaHouseloanAmt"
							value="${salaBusiCustFinalcial.liaHouseloanAmt }"> </span>
					</div>
					<div class="help-block col-xs-12 col-sm-reset inline"></div>
				</div>

				<div class="form-group">
					<label class="col-xs-12 col-sm-2 control-label no-padding-right"
						for="assHousePropertyAmtg"> <font color='red'> * </font>房产（元）： </label>
					<div class="col-xs-12 col-sm-3">
						<span class="block input-icon input-icon-right"> <input
							type="text" class="form-control required isNotNegative"
							id="assHousePropertyAmtg" name="assHousePropertyAmt"
							value="${salaBusiCustFinalcial.assHousePropertyAmt }"> </span>
					</div>
					<div class="help-block col-xs-12 col-sm-reset inline"></div>

					<label class="col-xs-12 col-sm-2 control-label no-padding-right"
						for="liaCarloanAmtg"> <font color='red'> * </font>车贷（元）： </label>
					<div class="col-xs-12 col-sm-3">
						<span class="block input-icon input-icon-right"> <input
							type="text" class="form-control required isNotNegative"
							id="liaCarloanAmtg" name="liaCarloanAmt"
							value="${salaBusiCustFinalcial.liaCarloanAmt }"> </span>
					</div>
					<div class="help-block col-xs-12 col-sm-reset inline"></div>
				</div>

				<div class="form-group">
					<label class="col-xs-12 col-sm-2 control-label no-padding-right"
						for="assCarAmtg"> <font color='red'> * </font>车辆（元）： </label>
					<div class="col-xs-12 col-sm-3">
						<span class="block input-icon input-icon-right"> <input
							type="text" class="form-control required isNotNegative"
							id="assCarAmtg" name="assCarAmt"
							value="${salaBusiCustFinalcial.assCarAmt }"> </span>
					</div>
					<div class="help-block col-xs-12 col-sm-reset inline"></div>

					<label class="col-xs-12 col-sm-2 control-label no-padding-right"
						for="liaCreditcardg"> <font color='red'> * </font>信用卡（元）： </label>
					<div class="col-xs-12 col-sm-3">
						<span class="block input-icon input-icon-right"> <input
							type="text" class="form-control required isNotNegative"
							id="liaCreditcardg" name="liaCreditcard"
							value="${salaBusiCustFinalcial.liaCreditcard }"> </span>
					</div>
					<div class="help-block col-xs-12 col-sm-reset inline"></div>
				</div>

				<div class="form-group">
					<label class="col-xs-12 col-sm-2 control-label no-padding-right"
						for="assOtherAmtg"> <font color='red'> * </font>其它（元）： </label>
					<div class="col-xs-12 col-sm-3">
						<span class="block input-icon input-icon-right"> <input
							type="text" class="form-control required isNotNegative"
							id="assOtherAmtg" name="assOtherAmt"
							value="${salaBusiCustFinalcial.assOtherAmt }"> </span>
					</div>
					<div class="help-block col-xs-12 col-sm-reset inline"></div>

					<label class="col-xs-12 col-sm-2 control-label no-padding-right"
						for="liaOtherAmtg"> <font color='red'> * </font>其它（元）： </label>
					<div class="col-xs-12 col-sm-3">
						<span class="block input-icon input-icon-right"> <input
							type="text" class="form-control required isNotNegative"
							id="liaOtherAmtg" name="liaOtherAmt"
							value="${salaBusiCustFinalcial.liaOtherAmt }"> </span>
					</div>
					<div class="help-block col-xs-12 col-sm-reset inline"></div>
				</div>

				<div class="form-group">
					<label class="col-xs-12 col-sm-2 control-label no-padding-right"
						for="assetsTotalAmtg"> <font color='red'> * </font>资产合计（元）： </label>
					<div class="col-xs-12 col-sm-3">
						<span class="block input-icon input-icon-right"> <input
							type="text" class="form-control required" id="assetsTotalAmtg"
							name="assetsTotalAmt" readonly="readonly"
							value="${salaBusiCustFinalcial.assetsTotalAmt }"> </span>
					</div>
					<div class="help-block col-xs-12 col-sm-reset inline"></div>

					<label class="col-xs-12 col-sm-2 control-label no-padding-right"
						for="liabilitiesTotalAmtg"> <font color='red'> * </font>负债合计（元）： </label>
					<div class="col-xs-12 col-sm-3">
						<span class="block input-icon input-icon-right"> <input
							type="text" class="form-control required" id="liabilitiesTotalAmtg"
							name="liabilitiesTotalAmt" readonly="readonly"
							value="${salaBusiCustFinalcial.liabilitiesTotalAmt }"> </span>
					</div>
					<div class="help-block col-xs-12 col-sm-reset inline"></div>
				</div>
				
				<br>
				
				<div class="form-group">
					<label class="col-xs-12 col-sm-2 control-label no-padding-right"
						for="assetsTotalAmtg"> <font color='red'> * </font>所有者权益： </label>
					<div class="col-xs-12 col-sm-3">
						<span class="block input-icon input-icon-right"> <input
							type="text" class="form-control required" id="proprietorshipg"
							name="proprietorship" readonly="readonly"
							value="${salaBusiCustFinalcial.proprietorship }"> </span>
					</div>
					<div class="help-block col-xs-12 col-sm-reset inline"></div>
				</div>

				<h4 class="blue">收入信息</h4>

				<div class="form-group">
					<label class="col-xs-12 col-sm-2 control-label no-padding-right"
						for="familymonthwageAmtg"> <font color='red'> * </font>家庭月工资收入（元）： </label>
					<div class="col-xs-12 col-sm-3">
						<span class="block input-icon input-icon-right"> <input
							type="text" class="form-control required isNotNegative"
							id="familymonthwageAmtg" name="familymonthwageAmt"
							value="${salaBusiCustFinalcial.familymonthwageAmt }"> </span>
					</div>
					<div class="help-block col-xs-12 col-sm-reset inline"></div>

					<label class="col-xs-12 col-sm-2 control-label no-padding-right"
						for="familyPayAmtg"> <font color='red'> * </font>家庭开支（元）： </label>
					<div class="col-xs-12 col-sm-3">
						<span class="block input-icon input-icon-right"> <input
							type="text" class="form-control required isNotNegative"
							id="familyPayAmtg" name="familyPayAmt"
							value="${salaBusiCustFinalcial.familyPayAmt }"> </span>
					</div>
					<div class="help-block col-xs-12 col-sm-reset inline"></div>
				</div>

				<div class="form-group">
					<label class="col-xs-12 col-sm-2 control-label no-padding-right"
						for="incomeOtherAmtg"> <font color='red'> * </font>其它收入（元）： </label>
					<div class="col-xs-12 col-sm-3">
						<span class="block input-icon input-icon-right"> <input
							type="text" class="form-control required isNotNegative"
							id="incomeOtherAmtg" name="incomeOtherAmt"
							value="${salaBusiCustFinalcial.incomeOtherAmt }"> </span>
					</div>
					<div class="help-block col-xs-12 col-sm-reset inline"></div>

					<label class="col-xs-12 col-sm-2 control-label no-padding-right"
						for="otherloanMonthrepayAmtg"> <font color='red'> * </font>其他贷款月还款额（元）： </label>
					<div class="col-xs-12 col-sm-3">
						<span class="block input-icon input-icon-right"> <input
							type="text" class="form-control required isNotNegative"
							id="otherloanMonthrepayAmtg" name="otherloanMonthrepayAmt"
							value="${salaBusiCustFinalcial.otherloanMonthrepayAmt }">
						</span>
					</div>
					<div class="help-block col-xs-12 col-sm-reset inline"></div>
				</div>

				<div class="form-group">
					<label class="col-xs-12 col-sm-2 control-label no-padding-right"
						for="monthdominatIncomeAmtg"> <font color='red'> * </font>月可支配收入（元）： </label>
					<div class="col-xs-12 col-sm-3">
						<span class="block input-icon input-icon-right"> <input
							type="text" class="form-control required isNotNegative"
							id="monthdominatIncomeAmtg" name="monthdominatIncomeAmt" readonly="readonly"
							value="${salaBusiCustFinalcial.monthdominatIncomeAmt }">
						</span>
					</div>
					<div class="help-block col-xs-12 col-sm-reset inline"></div>
				</div>

			</c:if>

			<!-- 农户 -->
			<c:if test="${vo.employmentType == '2'}">
				<input type="hidden" name="finalcialType" value="3">

				<h4 class="blue">收入信息</h4>

				<div class="form-group">
					<label class="col-xs-12 col-sm-2 control-label no-padding-right"
						for="yearBussiAmtn"> <font color='red'> * </font>年营业额（元）： </label>
					<div class="col-xs-12 col-sm-3">
						<span class="block input-icon input-icon-right"> <input
							type="text" class="form-control required isNotNegative"
							id="yearBussiAmtn" name="yearBussiAmt"
							value="${salaBusiCustFinalcial.yearBussiAmt }"> </span>
					</div>
					<div class="help-block col-xs-12 col-sm-reset inline"></div>

					<label class="col-xs-12 col-sm-2 control-label no-padding-right"
						for="incomeOtherAmtn"> <font color='red'> * </font>其他收入（元）： </label>
					<div class="col-xs-12 col-sm-3">
						<span class="block input-icon input-icon-right"> <input
							type="text" class="form-control required isNotNegative"
							id="incomeOtherAmtn" name="incomeOtherAmt"
							value="${salaBusiCustFinalcial.incomeOtherAmt }"> </span>
					</div>
					<div class="help-block col-xs-12 col-sm-reset inline"></div>
				</div>

				<div class="form-group">
					<label class="col-xs-12 col-sm-2 control-label no-padding-right"
						for="grossprofitAmtn"> <font color='red'> * </font>毛利润（元）： </label>
					<div class="col-xs-12 col-sm-3">
						<span class="block input-icon input-icon-right"> <input
							type="text" class="form-control required isNotNegative"
							id="grossprofitAmtn" name="grossprofitAmt"
							value="${salaBusiCustFinalcial.grossprofitAmt }"> </span>
					</div>
					<div class="help-block col-xs-12 col-sm-reset inline"></div>

					<label class="col-xs-12 col-sm-2 control-label no-padding-right"
						for="familypayAmtn"> <font color='red'> * </font>家庭开支（元）： </label>
					<div class="col-xs-12 col-sm-3">
						<span class="block input-icon input-icon-right"> <input
							type="text" class="form-control required isNotNegative"
							id="familypayAmtn" name="familypayAmt"
							value="${salaBusiCustFinalcial.familypayAmt }"> </span>
					</div>
					<div class="help-block col-xs-12 col-sm-reset inline"></div>
				</div>

				<div class="form-group">
					<label class="col-xs-12 col-sm-2 control-label no-padding-right"
						for="netprofitAmtn"> <font color='red'> * </font>年净利润（元）： </label>
					<div class="col-xs-12 col-sm-3">
						<span class="block input-icon input-icon-right"> <input
							type="text" class="form-control required isNotNegative"
							id="netprofitAmtn" name="netprofitAmt"
							value="${salaBusiCustFinalcial.netprofitAmt }"> </span>
					</div>
					<div class="help-block col-xs-12 col-sm-reset inline"></div>

					<label class="col-xs-12 col-sm-2 control-label no-padding-right"
						for="otherloanMonthrepayAmtn"> <font color='red'> * </font>其他贷款月还款额（元）： </label>
					<div class="col-xs-12 col-sm-3">
						<span class="block input-icon input-icon-right"> <input
							type="text" class="form-control required isNotNegative"
							id="otherloanMonthrepayAmtn" name="otherloanMonthrepayAmt"
							value="${salaBusiCustFinalcial.otherloanMonthrepayAmt }">
						</span>
					</div>
					<div class="help-block col-xs-12 col-sm-reset inline"></div>
				</div>

				<div class="form-group">
					<label class="col-xs-12 col-sm-2 control-label no-padding-right"
						for="monthdominatIncomeAmtn"> <font color='red'> * </font>月可支配收入（元）： </label>
					<div class="col-xs-12 col-sm-3">
						<span class="block input-icon input-icon-right"> <input
							type="text" class="form-control required isNotNegative"
							id="monthdominatIncomeAmtn" name="monthdominatIncomeAmt" readonly="readonly"
							value="${salaBusiCustFinalcial.monthdominatIncomeAmt }">
						</span>
					</div>
					<div class="help-block col-xs-12 col-sm-reset inline"></div>
				</div>
			</c:if>

			<c:if test="${type != 'check' }">
				<hr>

				<div class="row">
					<div class="col-md-6 col-md-offset-5">
						<button id="saveFinance" type="submit"
							class="btn btn-sm btn-primary">
							<i class="ace-icon fa fa-floppy-o"></i> 保存
						</button>
					</div>
				</div>
			</c:if>
		</form>
	</div>
</div>