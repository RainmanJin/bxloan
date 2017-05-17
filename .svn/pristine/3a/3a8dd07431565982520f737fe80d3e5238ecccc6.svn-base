<!-- 主合同信息-->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../../../commons/taglibs.jsp"%>

<div id="faq-list-1" class="panel-group accordion-style1 accordion-style2">
	<div class="step-pane active" id="step1">
		<form class="form-horizontal" id="form-contractInfo">
			<div>
				<h8 class="blue">
					主合同信息
				</h8>
			</div>
			<!-- 合同信息 -->
			<div class="col-xs-8 col-sm-12 widget-container-col ui-sortable">
				<!-- input content -->
					<div class="form-group">
						<label class="col-xs-8 col-sm-2 control-label no-padding-right">
							客户名称
						</label>
						<div class="col-xs-8 col-sm-3">
							<span class="block input-icon input-icon-right">
								<input type="text" name="customerName" class="form-control" disabled="disabled" value="${vo.customerName}"/>
							</span>
						</div>
						<div class="help-block col-xs-8 col-sm-reset inline">
						</div>
						<label class="col-xs-8 col-sm-2 control-label no-padding-right">
							客户类型
						</label>
						<div class="col-xs-8 col-sm-3">
							<span class="block input-icon input-icon-right">
								<!-- <input type="text" name="customerType" class="form-control" readonly="readonly"
								/> -->
								<select class="form-control" disabled="disabled">
									<dd:options codeType="CustomerType" selected="${vo.customerType}" />
								</select>
							</span>
						</div>
						<div class="help-block col-xs-8 col-sm-reset inline">
						</div>
					</div>
					<div class="form-group">
						<label class="col-xs-8 col-sm-2 control-label no-padding-right">
							业务编号
						</label>
						<div class="col-xs-8 col-sm-3">
							<span class="block input-icon input-icon-right">
								<input type="text" name="projectNo" class="form-control" disabled="disabled" value="${vo.projectNo}"/>
							</span>
						</div>
						<div class="help-block col-xs-8 col-sm-reset inline">
						</div>
						<label class="col-xs-8 col-sm-2 control-label no-padding-right">
							贷款产品
						</label>
						<div class="col-xs-8 col-sm-3">
							<span class="block input-icon input-icon-right">
								<input type="text" name="productType" class="form-control" disabled="disabled" value="${vo.productType}"/> 
							</span>
						</div>
						<div class="help-block col-xs-8 col-sm-reset inline">
						</div>
					</div>
					<div class="form-group">
						<label class="col-xs-8 col-sm-2 control-label no-padding-right">
							担保方式
						</label>
						<div class="col-xs-8 col-sm-3">
							<span class="block input-icon input-icon-right">
								<input type="text" name="guaranteeMode" class="form-control" disabled="disabled" value="${vo.guaranteeMode}"/> 
							</span>
						</div>
						<div class="help-block col-xs-8 col-sm-reset inline">
						</div>
						<label class="col-xs-8 col-sm-2 control-label no-padding-right">
							投放行业
						</label>
						<div class="col-xs-8 col-sm-3">
							<span class="block input-icon input-icon-right">
								<input type="text" name="investmentIndustry" class="form-control" disabled="disabled"  value="${vo.investmentIndustry}"
								/>
							</span>
						</div>
						<div class="help-block col-xs-8 col-sm-reset inline">
						</div>
					</div>
					<div class="form-group">
						<label class="col-xs-8 col-sm-2 control-label no-padding-right">
							合同编号
						</label>
						<div class="col-xs-8 col-sm-3">
							<span class="block input-icon input-icon-right">
								<input type="text" name="contractNum" class="form-control" disabled="disabled" value="${vo.contractNum}"
								/>
							</span>
						</div>
						<div class="help-block col-xs-8 col-sm-reset inline">
						</div>
						<label class="col-xs-8 col-sm-2 control-label no-padding-right">
							授信额度
						</label>
						<div class="col-xs-8 col-sm-3">
							<span class="block input-icon input-icon-right">
								<div class="input-group">
									<input type="text" name="contractAmt" id="contractAmt" class="form-control num_amt_2fixed" disabled="disabled" value="${vo.contractAmt}"
									/>
									<span class="input-group-addon">元</span>
								</div>
							</span>
						</div>
						<div class="help-block col-xs-8 col-sm-reset inline">
						</div>
					</div>
					<div class="form-group">
						<label class="col-xs-8 col-sm-2 control-label no-padding-right">
							授信类型
						</label>
						<div class="col-xs-8 col-sm-3">
							<span class="block input-icon input-icon-right">
								<select class="form-control" disabled="disabled">
									<dd:options codeType="CreditType" selected="${vo.creditType}" />
								</select>
							</span>
						</div>
						<div class="help-block col-xs-8 col-sm-reset inline">
						</div>
						<label class="col-xs-8 col-sm-2 control-label no-padding-right">
							授信合同期限
						</label>
						<div class="col-xs-8 col-sm-3">
							<span class="block input-icon input-icon-right">
								<div class="input-group">
									<input type="text" name="creditContractTerm" id="creditContractTerm" class="form-control" disabled="disabled" value="${vo.contractTerm}"
									/>
									<span class="input-group-addon">${vo.contractTermUnit}</span>
								</div>
							</span>
						</div>
						<div class="help-block col-xs-8 col-sm-reset inline">
						</div>
					</div>
					<div class="form-group">
						<label class="col-xs-8 col-sm-2 control-label no-padding-right">
							贷款用途
						</label>
						<div class="col-xs-8 ">
							<span class="block input-icon input-icon-right">
								<textarea rows="4"  name="purpose" class="form-control" disabled="disabled" style="width: 89%;">${vo.purpose}</textarea>
							</span>
						</div>
						<div class="help-block col-xs-8 col-sm-reset inline">
						</div>
					</div>
			</div>
			<hr/>
			<!-- 批复利率 -->
			<div class="col-xs-8 col-sm-12 widget-container-col ui-sortable">
				<!-- 批复利率 -->
				<div>
					<h8 class="blue">
						批复利率
					</h8>
				</div>
				<div>
					<div class="form-group">
						<label class="col-xs-8 col-sm-2 control-label no-padding-right">
							利率类型
						</label>
						<div class="col-xs-8 col-sm-3">
							<span class="block input-icon input-icon-right">
								<select name="finalIrTypeCd" class="form-control" id="finalIrTypeCd" disabled="disabled">
								 	<dd:options codeType="InterestRateAdjustment" selected="${vo.finalIrTypeCd}"/>
								</select>
							</span>
						</div>
						<div id="finalRateValueDiv">
							<div class="help-block col-xs-8 col-sm-reset inline">
							</div>
							<label class="col-xs-8 col-sm-2 control-label no-padding-right">
								年利率
							</label>
							<div class="col-xs-8 col-sm-3">
								<span class="block input-icon input-icon-right">
									<div class="input-group">
										<input type="text" name="finalRateValue" id="finalRateValue" class="form-control num_2fixed" disabled="disabled" value="${vo.finalRateValue}"/>
										<span class="input-group-addon">%</span>
									</div>
								</span>
							</div>
							<div class="help-block col-xs-8 col-sm-reset inline">
							</div>
						</div>
					</div>
					<div class="form-group" id="finalAdjustDiv">
						<label class="col-xs-8 col-sm-2 control-label no-padding-right">
							调整周期
						</label>
						<div class="col-xs-8 col-sm-3">
							<span class="block input-icon input-icon-right">
								<select  name="finalAdjustPeriod" class="form-control" id="finalAdjustPeriod"
								disabled="disabled">
								 <dd:options codeType="AdjustPeriod"/>
								</select>
							</span>
						</div>
						<div class="help-block col-xs-8 col-sm-reset inline">
						</div>
						<label class="col-xs-8 col-sm-2 control-label no-padding-right">
							利率上浮比例
						</label>
						<div class="col-xs-8 col-sm-3">
							<span class="block input-icon input-icon-right">
							<div class="input-group">
								<input type="text" name="finalFloatRate" id="finalFloatRate" class="form-control num_2fixed"
								disabled="disabled"/>
								<span class="input-group-addon">%</span>
							</div>
							</span>
						</div>
						<div class="help-block col-xs-8 col-sm-reset inline">
						</div>
					</div>
				</div>
			</div>
			<!-- 批复利率 -->
			<!-- 抵质押物列表 -->
			<div class="col-xs-8 col-sm-12 widget-container-col ui-sortable">
				<jsp:include page="collateralList.jsp"></jsp:include>
			</div>
			<hr/>
			<!-- 保证人列表 -->
			<div class="col-xs-8 col-sm-12 widget-container-col ui-sortable">
				<jsp:include page="assureList.jsp"></jsp:include>
			</div>
		</form>
		<div class="col-xs-12">
			<div class="wizard-actions" style="text-align: center;margin-top: 10px;">
				<button type="button" class="btn btn-sm btn-primary"
					id="saveCreditContractInfo" data-loading-text="正在保存中...">
					<i class="ace-icon fa fa-floppy-o"> </i> 保存
				</button>
			</div>
		</div>
		<!-- button field end -->
	</div>
	<!-- step pane -->
</div>
<!-- step content -->
