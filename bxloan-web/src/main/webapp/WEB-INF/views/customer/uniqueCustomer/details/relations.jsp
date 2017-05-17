<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
%>
<%@include file="../../../../commons/taglibs.jsp" %>
	<div class="row">
		<div class="col-md-12">
			<!-- 经营类客户 -->
			<c:if test="${requestScope.individual.employmentType == '3'}">
				<input type="hidden" name="finalcialType" value="2">
				<h6 class="blue">
					资产负债信息
				</h6>
				<div class="form-group">
					<div class="row">
						<div class="col-md-7 ie8">
							<table style="width: 100%;">
								<tr>
									<td align="center">
										<h5 class="blue">
											资产
										</h5>
									</td>
								</tr>
							</table>
						</div>
						<div class="col-md-5 ie8">
							<table style="width: 60%;">
								<tr>
									<td align="center">
										<h5 class="blue">
											负债
										</h5>
									</td>
								</tr>
							</table>
						</div>
					</div>
				</div>
				<div class="form-group">
					<div class="row">
						<div class="col-md-5 ie8">
							<label class="col-sm-6 control-label" for="assCashAmtq">
								现金/存款：
							</label>
							<div class="col-sm-5">
								<input type="text" class="form-control required isNumber" id="assCashAmtq"
								name="assCashAmt" />
							</div>
						</div>
						<div class="col-md-4 col-md-offset-1 ie8">
							<label class="col-sm-5 control-label" for="liaBusinessLoanAmtq">
								经营型贷款：
							</label>
							<div class="col-sm-6">
								<input type="text" class="form-control required isNumber" id="liaBusinessLoanAmtq"
								name="liaBusinessLoanAmt" />
							</div>
						</div>
					</div>
				</div>
				<div class="form-group">
					<div class="row">
						<div class="col-md-5 ie8">
							<label class="col-sm-6 control-label" for="assReceivablesAmtq">
								应收款：
							</label>
							<div class="col-sm-5">
								<input type="text" class="form-control required isNumber" id="assReceivablesAmtq"
								name="assReceivablesAmt" />
							</div>
						</div>
						<div class="col-md-4 col-md-offset-1 ie8">
							<label class="col-sm-5 control-label" for="liaHouseloanAmtq">
								房贷：
							</label>
							<div class="col-sm-6">
								<input type="text" class="form-control required isNumber" id="liaHouseloanAmtq"
								name="liaHouseloanAmt" />
							</div>
						</div>
					</div>
				</div>
				<div class="form-group">
					<div class="row">
						<div class="col-md-5 ie8">
							<label class="col-sm-6 control-label" for="assStockAmtq">
								存货：
							</label>
							<div class="col-sm-5">
								<input type="text" class="form-control required isNumber" id="assStockAmtq"
								name="assStockAmt" />
							</div>
						</div>
						<div class="col-md-4 col-md-offset-1 ie8">
							<label class="col-sm-5 control-label" for="liaCarloanAmtq">
								车贷：
							</label>
							<div class="col-sm-6">
								<input type="text" class="form-control required isNumber" id="liaCarloanAmtq"
								name="liaCarloanAmt" />
							</div>
						</div>
					</div>
				</div>
				<div class="form-group">
					<div class="row">
						<div class="col-md-5 ie8">
							<label class="col-sm-6 control-label" for="assHousePropertyAmtq">
								房产：
							</label>
							<div class="col-sm-5">
								<input type="text" class="form-control required isNumber" id="assHousePropertyAmtq"
								name="assHousePropertyAmt" />
							</div>
						</div>
						<div class="col-md-4 col-md-offset-1 ie8">
							<label class="col-sm-5 control-label" for="liaCreditcardq">
								信用卡：
							</label>
							<div class="col-sm-6">
								<input type="text" class="form-control required isNumber" id="liaCreditcardq"
								name="liaCreditcard" />
							</div>
						</div>
					</div>
				</div>
				<div class="form-group">
					<div class="row">
						<div class="col-md-5 ie8">
							<label class="col-sm-6 control-label" for="assCarAmtq">
								车辆：
							</label>
							<div class="col-sm-5">
								<input type="text" class="form-control required isNumber" id="assCarAmtq"
								name="assCarAmt" />
							</div>
						</div>
					</div>
				</div>
				<div class="form-group">
					<div class="row">
						<div class="col-md-5 ie8">
							<label class="col-sm-6 control-label" for="assOtherAmtq">
								其它：
							</label>
							<div class="col-sm-5">
								<input type="text" class="form-control required isNumber" id="assOtherAmtq"
								name="assOtherAmt" />
							</div>
						</div>
					</div>
				</div>
				<div class="form-group">
					<div class="row">
						<div class="col-md-5 ie8">
							<label class="col-sm-6 control-label" for="assetsTotalAmtq">
								资产合计：
							</label>
							<div class="col-sm-5">
								<input type="text" class="form-control" id="assetsTotalAmtq" name="assetsTotalAmt"
								readonly="readonly" />
							</div>
						</div>
						<div class="col-md-4 col-md-offset-1 ie8">
							<label class="col-sm-5 control-label" for="liabilitiesTotalAmtq">
								负债合计：
							</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="liabilitiesTotalAmtq" name="liabilitiesTotalAmt"
								readonly="readonly" />
							</div>
						</div>
					</div>
				</div>
				<h6 class="blue">
					收入信息
				</h6>
				<div class="form-group">
					<div class="row">
						<div class="col-md-5 ie8">
							<label class="col-sm-6 control-label" for="incomeMonthBussAmtq">
								月营业额：
							</label>
							<div class="col-sm-5">
								<input type="text" class="form-control required isNumber" id="incomeMonthBussAmtq"
								name="incomeMonthBussAmt" />
							</div>
						</div>
						<div class="col-md-4 col-md-offset-1 ie8">
							<label class="col-sm-5 control-label" for="incomeOtherAmtq">
								其它收入：
							</label>
							<div class="col-sm-6">
								<input type="text" class="form-control required isNumber" id="incomeOtherAmtq"
								name="incomeOtherAmt" />
							</div>
						</div>
					</div>
				</div>
				<div class="form-group">
					<div class="row">
						<div class="col-md-5 ie8">
							<label class="col-sm-6 control-label" for="grossprofitAmtq">
								毛利润：
							</label>
							<div class="col-sm-5">
								<input type="text" class="form-control required isNumber" id="grossprofitAmtq"
								name="grossprofitAmt" />
							</div>
						</div>
						<div class="col-md-4 col-md-offset-1 ie8">
							<label class="col-sm-5 control-label" for="familypayAmtq">
								家庭开支：
							</label>
							<div class="col-sm-6">
								<input type="text" class="form-control required isNumber" id="familypayAmtq"
								name="familypayAmt" />
							</div>
						</div>
					</div>
				</div>
				<div class="form-group">
					<div class="row">
						<div class="col-md-5 ie8">
							<label class="col-sm-6 control-label" for="netprofitAmtq">
								净利润：
							</label>
							<div class="col-sm-5">
								<input type="text" class="form-control required isNumber" id="netprofitAmtq"
								name="netprofitAmt" />
							</div>
						</div>
						<div class="col-md-4 col-md-offset-1 ie8">
							<label class="col-sm-5 control-label" for="otherloanMonthrepayAmtq">
								其他贷款月还款额：
							</label>
							<div class="col-sm-6">
								<input type="text" class="form-control required isNumber" id="otherloanMonthrepayAmtq"
								name="otherloanMonthrepayAmt" />
							</div>
						</div>
					</div>
				</div>
				<div class="form-group">
					<div class="row">
						<div class="col-md-5 ie8">
							<label class="col-sm-6 control-label" for="monthdominatIncomeAmtq">
								月可支配收入：
							</label>
							<div class="col-sm-5">
								<input type="text" class="form-control required isNumber" id="monthdominatIncomeAmtq"
								name="monthdominatIncomeAmt" />
							</div>
						</div>
					</div>
				</div>
			</c:if>
			<!-- 受薪客户 -->
			<c:if test="${requestScope.individual.employmentType == '1' ||requestScope.individual.employmentType == '4'}">
				<input type="hidden" name="finalcialType" value="1">
				<h6 class="blue">
					资产负债信息
				</h6>
				<div class="form-group">
					<div class="row">
						<div class="col-md-7">
							<table style="width: 100%;">
								<tr>
									<td align="center">
										<h5 class="blue">
											资产
										</h5>
									</td>
								</tr>
							</table>
						</div>
						<div class="col-md-5">
							<table style="width: 60%;">
								<tr>
									<td align="center">
										<h5 class="blue">
											负债
										</h5>
									</td>
								</tr>
							</table>
						</div>
					</div>
				</div>
				<div class="form-group">
					<div class="row">
						<div class="col-md-5">
							<label class="col-sm-6 control-label" for="assCashAmtg">
								现金/存款：
							</label>
							<div class="col-sm-5">
								<input type="text" class="form-control required isNumber" id="assCashAmtg"
								name="assCashAmt" />
							</div>
						</div>
						<div class="col-md-4 col-md-offset-1">
							<label class="col-sm-5 control-label" for="liaHouseloanAmtg">
								房贷：
							</label>
							<div class="col-sm-6">
								<input type="text" class="form-control required isNumber" id="liaHouseloanAmtg"
								name="liaHouseloanAmt" />
							</div>
						</div>
					</div>
				</div>
				<div class="form-group">
					<div class="row">
						<div class="col-md-5">
							<label class="col-sm-6 control-label" for="assHousePropertyAmtg">
								房产：
							</label>
							<div class="col-sm-5">
								<input type="text" class="form-control required isNumber" id="assHousePropertyAmtg"
								name="assHousePropertyAmt" />
							</div>
						</div>
						<div class="col-md-4 col-md-offset-1">
							<label class="col-sm-5 control-label" for="liaCarloanAmtg">
								车贷：
							</label>
							<div class="col-sm-6">
								<input type="text" class="form-control required isNumber" id="liaCarloanAmtg"
								name="liaCarloanAmt" />
							</div>
						</div>
					</div>
				</div>
				<div class="form-group">
					<div class="row">
						<div class="col-md-5">
							<label class="col-sm-6 control-label" for="assCarAmtg">
								车辆：
							</label>
							<div class="col-sm-5">
								<input type="text" class="form-control required isNumber" id="assCarAmtg"
								name="assCarAmt" />
							</div>
						</div>
						<div class="col-md-4 col-md-offset-1">
							<label class="col-sm-5 control-label" for="liaCreditcardg">
								信用卡：
							</label>
							<div class="col-sm-6">
								<input type="text" class="form-control required isNumber" id="liaCreditcardg"
								name="liaCreditcard" />
							</div>
						</div>
					</div>
				</div>
				<div class="form-group">
					<div class="row">
						<div class="col-md-5">
							<label class="col-sm-6 control-label" for="assOtherAmtg">
								其它：
							</label>
							<div class="col-sm-5">
								<input type="text" class="form-control required isNumber" id="assOtherAmtg"
								name="assOtherAmt" />
							</div>
						</div>
						<div class="col-md-4 col-md-offset-1">
							<label class="col-sm-5 control-label" for="liaOtherAmtg">
								其它：
							</label>
							<div class="col-sm-6">
								<input type="text" class="form-control required isNumber" id="liaOtherAmtg"
								name="liaOtherAmt" />
							</div>
						</div>
					</div>
				</div>
				<div class="form-group">
					<div class="row">
						<div class="col-md-5">
							<label class="col-sm-6 control-label" for="assetsTotalAmtg">
								资产合计：
							</label>
							<div class="col-sm-5">
								<input type="text" class="form-control" id="assetsTotalAmtg" name="assetsTotalAmt"
								readonly="readonly" />
							</div>
						</div>
						<div class="col-md-4 col-md-offset-1">
							<label class="col-sm-5 control-label" for="liabilitiesTotalAmtg">
								负债合计：
							</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="liabilitiesTotalAmtg" name="liabilitiesTotalAmt"
								readonly="readonly" />
							</div>
						</div>
					</div>
				</div>
				<h6 class="blue">
					收入信息
				</h6>
				<div class="form-group">
					<div class="row">
						<div class="col-md-5">
							<label class="col-sm-6 control-label" for="familymonthwageAmtg">
								家庭月工资收入：
							</label>
							<div class="col-sm-5">
								<input type="text" class="form-control required isNumber" id="familymonthwageAmtg"
								name="familymonthwageAmt" />
							</div>
						</div>
						<div class="col-md-4 col-md-offset-1">
							<label class="col-sm-5 control-label" for="familyPayAmtg">
								家庭开支：
							</label>
							<div class="col-sm-6">
								<input type="text" class="form-control required isNumber" id="familyPayAmtg"
								name="familyPayAmt" />
							</div>
						</div>
					</div>
				</div>
				<div class="form-group">
					<div class="row">
						<div class="col-md-5">
							<label class="col-sm-6 control-label" for="incomeOtherAmtg">
								其它收入：
							</label>
							<div class="col-sm-5">
								<input type="text" class="form-control required isNumber" id="incomeOtherAmtg"
								name="incomeOtherAmt" />
							</div>
						</div>
						<div class="col-md-4 col-md-offset-1">
							<label class="col-sm-5 control-label" for="otherloanMonthrepayAmtg">
								其他贷款月还款额：
							</label>
							<div class="col-sm-6">
								<input type="text" class="form-control required isNumber" id="otherloanMonthrepayAmtg"
								name="otherloanMonthrepayAmt" />
							</div>
						</div>
					</div>
				</div>
				<div class="form-group">
					<div class="row">
						<div class="col-md-5">
							<label class="col-sm-6 control-label" for="monthdominatIncomeAmtg">
								月可支配收入：
							</label>
							<div class="col-sm-5">
								<input type="text" class="form-control required isNumber" id="monthdominatIncomeAmtg"
								name="monthdominatIncomeAmt" />
							</div>
						</div>
					</div>
				</div>
			</c:if>
			
			<!-- 农户 -->
			<c:if test="${requestScope.individual.employmentType=='2'}">
				<input type="hidden" name="finalcialType" value="3">

				<h6 class="blue">收入信息</h6>

				<div class="form-group">
					<div class="row">
						<div class="col-md-5 ie8">
							<label class="col-sm-6 control-label" for="yearBussiAmtn">
								年营业额： </label>
							<div class="col-sm-5">
								<input type="text" class="form-control required isNumber"
									id="yearBussiAmtn" name="yearBussiAmt"
									/>
							</div>
						</div>

						<div class="col-md-4 col-md-offset-1 ie8">
							<label class="col-sm-5 control-label" for="incomeOtherAmtn">
								其他收入： </label>
							<div class="col-sm-6">
								<input type="text" class="form-control required isNumber"
									id="incomeOtherAmtn" name="incomeOtherAmt"
									/>
							</div>
						</div>
					</div>
				</div>

				<div class="form-group">
					<div class="row">
						<div class="col-md-5 ie8">
							<label class="col-sm-6 control-label" for="grossprofitAmtn">
								毛利润： </label>
							<div class="col-sm-5">
								<input type="text" class="form-control required isNumber"
									id="grossprofitAmtn" name="grossprofitAmt"
									/>
							</div>
						</div>

						<div class="col-md-4 col-md-offset-1 ie8">
							<label class="col-sm-5 control-label" for="familypayAmtn">
								家庭开支： </label>
							<div class="col-sm-6">
								<input type="text" class="form-control required isNumber"
									id="familypayAmtn" name="familypayAmt"
									/>
							</div>
						</div>
					</div>
				</div>

				<div class="form-group">
					<div class="row">
						<div class="col-md-5 ie8">
							<label class="col-sm-6 control-label" for="netprofitAmtn">
								年净利润： </label>
							<div class="col-sm-5">
								<input type="text" class="form-control required isNumber"
									id="netprofitAmtn" name="netprofitAmt"
									/>
							</div>
						</div>

						<div class="col-md-4 col-md-offset-1 ie8">
							<label class="col-sm-5 control-label" for="otherloanMonthrepayAmtn">
								其他贷款月还款额： </label>
							<div class="col-sm-6">
								<input type="text" class="form-control required isNumber"
									id="otherloanMonthrepayAmtn" name="otherloanMonthrepayAmt"
									/>
							</div>
						</div>
					</div>
				</div>

				<div class="form-group">
					<div class="row">
						<div class="col-md-5">
							<label class="col-sm-6 control-label"
								for="monthdominatIncomeAmtn"> 月可支配收入： </label>
							<div class="col-sm-5">
								<input type="text" class="form-control required isNumber"
									id="monthdominatIncomeAmtn" name="monthdominatIncomeAmt"
									/>
							</div>
						</div>
					</div>
				</div>
			</c:if>
			
			
		</div>
	</div>