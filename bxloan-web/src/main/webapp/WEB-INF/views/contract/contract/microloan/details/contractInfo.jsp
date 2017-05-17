<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
%>
<%@include file="../../../../../commons/taglibs.jsp" %>
	<!-- <h4 class="blue">
		合同内容
	</h4> -->
	<div id="faq-list-1" class="panel-group accordion-style1 accordion-style2">
		<div class="step-pane active" id="step1">
			<form class="form-horizontal" id="form-contractInfo">
				<div class="col-xs-8 col-sm-12 widget-container-col ui-sortable">
					<!-- input content -->
					<!-- 合同信息 -->
					<div>
						<div>
							<h8 class="blue">
								合同信息
							</h8>
						</div>
						<div class="form-group">
							<label class="col-xs-8 col-sm-2 control-label no-padding-right">
								业务编号
							</label>
							<div class="col-xs-8 col-sm-3">
								<span class="block input-icon input-icon-right">
									<input type="text" name="projectNo" class="form-control" readonly="readonly"
									/>
								</span>
							</div>
							<div class="help-block col-xs-8 col-sm-reset inline">
							</div>
							<label class="col-xs-8 col-sm-2 control-label no-padding-right">
								客户类型
							</label>
							<div class="col-xs-8 col-sm-3">
								<span class="block input-icon input-icon-right">
									<input type="text" name="customerType" class="form-control" readonly="readonly"
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
									<input type="text" name="contractNum" class="form-control" readonly="readonly"
									/>
								</span>
							</div>
							<div class="help-block col-xs-8 col-sm-reset inline">
							</div>
							<label class="col-xs-8 col-sm-2 control-label no-padding-right">
								客户名称
							</label>
							<div class="col-xs-8 col-sm-3">
								<span class="block input-icon input-icon-right">
									<input type="text" name="customerName" class="form-control" readonly="readonly"
									/>
								</span>
							</div>
							<div class="help-block col-xs-8 col-sm-reset inline">
							</div>
						</div>
						<div class="form-group">
							<label class="col-xs-8 col-sm-2 control-label no-padding-right">
								贷款产品
							</label>
							<div class="col-xs-8 col-sm-3">
								<span class="block input-icon input-icon-right">
									<input type="text" name="productType" class="form-control" readonly="readonly"/>
								</span>
							</div>
							<div class="help-block col-xs-8 col-sm-reset inline">
							</div>
							<label class="col-xs-8 col-sm-2 control-label no-padding-right">
								合同金额
							</label>
							<div class="col-xs-8 col-sm-3">
								<span class="block input-icon input-icon-right">
									<div class="input-group">
									<input type="text" name="contractAmt" id="contractAmt" class="form-control" readonly="readonly"
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
								合同期限
							</label>
							<div class="col-xs-8 col-sm-3">
								<span class="block input-icon input-icon-right">
								<div class="input-group">
									<input type="text" name="contractTerm" class="form-control" readonly="readonly"
									/>
								</div>
								</span>
							</div>
							<div class="help-block col-xs-8 col-sm-reset inline">
							</div>
							<label class="col-xs-8 col-sm-2 control-label no-padding-right">
								还款方式
							</label>
							<div class="col-xs-8 col-sm-3">
								<span class="block input-icon input-icon-right">
									<input type="text" name="repayModeCd" class="form-control" readonly="readonly"
									/>
								</span>
							</div>
							<div class="help-block col-xs-8 col-sm-reset inline">
							</div>
						</div>
						<div class="form-group">
							<label class="col-xs-8 col-sm-2 control-label no-padding-right">
								还款周期月数
							</label>
							<div class="col-xs-8 col-sm-3">
								<span class="block input-icon input-icon-right">
									<div class="input-group">
									<input type="text" name="repayPrincipalMonthes" class="form-control" readonly="readonly"
									/>
									<span class="input-group-addon">月</span>
									</div>	
								</span>
							</div>
							<div class="help-block col-xs-8 col-sm-reset inline">
							</div>
							<label class="col-xs-8 col-sm-2 control-label no-padding-right">
								投放行业
							</label>
							<div class="col-xs-8 col-sm-3">
								<span class="block input-icon input-icon-right">
									<input type="text" name="investmentIndustry" class="form-control" readonly="readonly"
									/>
								</span>
							</div>
							<div class="help-block col-xs-8 col-sm-reset inline">
							</div>
						</div>
						<div class="form-group">
							<label class="col-xs-8 col-sm-2 control-label no-padding-right">
								<font color='red'>*</font>开户行名称
							</label>
							<div class="col-xs-8 col-sm-3">
								<span class="block input-icon input-icon-right">
									<input type="text" id="bankName" name="bankName" class="form-control" />
								</span>
							</div>
							<div class="help-block col-xs-8 col-sm-reset inline">
							</div>
							<label class="col-xs-8 col-sm-2 control-label no-padding-right">
								<font color='red'>*</font>贷款账号
							</label>
							<div class="col-xs-8 col-sm-3">
								<span class="block input-icon input-icon-right">
									<input type="text" id="loanNum" name="loanNum"  class="form-control"
									/>
								</span>
							</div>
							<div class="help-block col-xs-8 col-sm-reset inline">
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-xs-8 col-sm-2 control-label no-padding-right">
								<font color='red'>*</font>约定方式
							</label>
							<div class="col-xs-8 col-sm-3">
								<span class="block input-icon input-icon-right">
									<select id="loanDateStyle" name="loanDateStyle" class="form-control">
										<option value="">请选择</option>
										<dd:options codeType="LoanDateStyle" />
									</select>
								</span>
							</div>
							<div class="help-block col-xs-8 col-sm-reset inline">
							</div>
							<label class="col-xs-8 col-sm-2 control-label no-padding-right">
								约定还款日
							</label>
							<div class="col-xs-8 col-sm-3">
								<span class="block input-icon input-icon-right">
									<div class="input-append date" id="arrangeRepayDateMask">
										<input class="form-control" type="text" name="arrangeRepayDay" id="arrangeRepayDay"
										/>
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
							<div class="col-xs-8 col-sm-3">
								<span class="block input-icon input-icon-right">
									<input type="text" name="purpose" class="form-control" readonly="readonly"
									/>
								</span>
							</div>
							<div class="help-block col-xs-8 col-sm-reset inline">
							</div>
							<label class="col-xs-8 col-sm-2 control-label no-padding-right">
							</label>
							<div class="col-xs-8 col-sm-3">
								<span class="block input-icon input-icon-right">
								</span>
							</div>
							<div class="help-block col-xs-8 col-sm-reset inline">
							</div>
						</div>
					</div>
				</div>
				
				<hr/>
				<!-- 抵质押物列表 -->
				<div class="col-xs-8 col-sm-12 widget-container-col ui-sortable">
				<jsp:include page="collateral.jsp"></jsp:include>
				</div>
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
									<select  name="approveIrTypeCd" class="form-control" id="approveIrTypeCdTd"
									  disabled="disabled" />
									 <dd:options codeType="InterestRateAdjustment"/>
									 </select>
								</span>
							</div>
							<div class="help-block col-xs-8 col-sm-reset inline">
							</div>
							<label class="col-xs-8 col-sm-2 control-label no-padding-right">
								年利率
							</label>
							<div class="col-xs-8 col-sm-3">
								<span class="block input-icon input-icon-right">
									<div class="input-group">
									<input type="text" name="approveRateValue" readonly="readonly" class="form-control"
									/>
									<span class="input-group-addon">%</span>
									</div>
								</span>
							</div>
							<div class="help-block col-xs-8 col-sm-reset inline">
							</div>
						</div>
					</div>
				</div>
				<!-- 最终利率 -->
				<div class="col-xs-8 col-sm-12 widget-container-col ui-sortable">
					<!-- 最终利率 -->
					<div>
						<h8 class="blue">
							最终利率
						</h8>
					</div>
					<div>
						<div class="form-group">
							<label class="col-xs-8 col-sm-2 control-label no-padding-right">
								利率类型
							</label>
							<div class="col-xs-8 col-sm-3">
								<span class="block input-icon input-icon-right">
									<select  name="finalIrTypeCd" class="form-control" id="finalIrTypeCd"
									disabled="disabled">
									 <dd:options codeType="InterestRateAdjustment"/>
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
									<input type="text" name="finalRateValue" id="finalRateValue" class="form-control"
									readonly="readonly"/>
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
									<input type="text" name="finalFloatRate" id="finalFloatRate" class="form-control"
									readonly="readonly"/>
									<span class="input-group-addon">%</span>
								</div>
								</span>
							</div>
							<div class="help-block col-xs-8 col-sm-reset inline">
							</div>
						</div>
						<!-- 
						onchange="checkFinalRateReason(this)" 
						<div class="form-group">
							<label class="col-xs-8 col-sm-2 control-label no-padding-right">
								利率调整原因
							</label>
							<div class="col-xs-8 col-sm-8">
								<span class="block input-icon input-icon-right">
									<textarea name="rateAdjustmentReason" id="rateAdjustmentReason" rows="5"
									cols="40" class="form-control">
									</textarea>
								</span>
							</div>
							<div class="help-block col-xs-8 col-sm-reset inline">
							</div>
						</div> -->
					</div>
				</div>
				<!-- 逾期利率 -->
				<div class="col-xs-8 col-sm-12 widget-container-col ui-sortable">
					<!-- 逾期利率 -->
					<div>
						<h8 class="blue">
							逾期利率
						</h8>
					</div>
					<div>
						<div class="form-group">
							<label class="col-xs-8 col-sm-2 control-label no-padding-right">
								逾期利率上浮比例
							</label>
							<div class="col-xs-8 col-sm-3">
								<span class="block input-icon input-icon-right">
									<div class="input-group">
									<input type="text" name="ovdueIrNegoRate" class="form-control" readonly="readonly"
									/>
									<span class="input-group-addon">%</span>
									</div>
								</span>
							</div>
							<div class="help-block col-xs-8 col-sm-reset inline">
							</div>
							<label class="col-xs-8 col-sm-2 control-label no-padding-right">
								挪用利率上浮比例
							</label>
							<div class="col-xs-8 col-sm-3">
								<span class="block input-icon input-icon-right">
									<div class="input-group">
									<input type="text" name="perculIrNegoRate" class="form-control" readonly="readonly"/>
									 <span class="input-group-addon">%</span>
									</div>
								</span>
							</div>
							<div class="help-block col-xs-8 col-sm-reset inline">
							</div>
						</div>
					</div>
				</div>
			</form>
			<div class="col-xs-12">
				<div class="wizard-actions" style="text-align: center;margin-top: 10px;">
					<button type="button" class="btn btn-sm btn-primary" id="saveContractInfo"
					data-loading-text="正在保存中...">
						<i class="ace-icon fa fa-floppy-o">
						</i>
						保存
					</button>
				</div>
			</div>
			<!-- button field end -->
		</div>
		<!-- step pane -->
	</div>
	<!-- step content -->
	
	
	
	
	
	
<!-- 修改抵质押物 -->
<div id="modCollateral" class="modal fade">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					&times;
				</button>
				<h4 class="blue bigger">
				</h4>
			</div>
			<form id="form-collateral">
			<div class="modal-body">
				
					<input type="hidden" name="projectPawnInfoId" id="projectPawnInfoId"/>
					<div class="form-group" style="margin-bottom:25px;">
						<label class="col-xs-6 col-sm-3 control-label no-padding-right">
						  抵质押物编号
						</label>
						<div class="col-xs-6 col-sm-3">
							<span class="block input-icon input-icon-right">
								<input type="text" name="guarantyNum" class="form-control" readonly="readonly"
								/>
							</span>
						</div>
						<div class="help-block col-xs-8 col-sm-reset inline">
						</div>
						<label class="col-xs-6 col-sm-3 control-label no-padding-right">
							抵质押物名称
						</label>
						<div class="col-xs-6 col-sm-3">
							<span class="block input-icon input-icon-right">
								<input type="text" name="guarantyName" id="guarantyName" class="form-control" readonly="readonly"
								/>
							</span>
						</div>
						
					</div>
					
					<div class="form-group" style="margin-bottom:25px;">
						<label class="col-xs-6 col-sm-3 control-label no-padding-right">
						  抵质押物类型
						</label>
						<div class="col-xs-6 col-sm-3">
							<span class="block input-icon input-icon-right">
								<select type="text" name="guaranteeType" id="guaranteeType" class="form-control" disabled="disabled">
								 <dd:options codeType="GuaranteeTypeCode"/>
								<select/>
								
							</span>
						</div>
						<div class="help-block col-xs-8 col-sm-reset inline">
						</div>
						<label class="col-xs-6 col-sm-3 control-label no-padding-right">
							评估价值（元）
						</label>
						<div class="col-xs-6 col-sm-3">
							<span class="block input-icon input-icon-right">
								<input type="text" name="evalValue" id="evalValue" class="form-control" readonly="readonly"
								/>
							</span>
						</div>
						
					</div>
					
					<div class="form-group" style="margin-bottom:25px;">
						<label class="col-xs-6 col-sm-3 control-label no-padding-right">
					   已设定担保额（元）
						</label>
						<div class="col-xs-6 col-sm-3">
							<span class="block input-icon input-icon-right">
								<input type="text" name="setGuaranteeAmt" id="setGuaranteeAmt" class="form-control" readonly="readonly"
								/>
							</span>
						</div>
						<div class="help-block col-xs-8 col-sm-reset inline">
						</div>
						<label class="col-xs-6 col-sm-3 control-label no-padding-right">
							担保率（%）
						</label>
						<div class="col-xs-6 col-sm-3">
							<span class="block input-icon input-icon-right">
								<input type="text" name="pawnRatio" class="form-control" readonly="readonly"
								/>
							</span>
						</div>
						
					</div>
					
					<div class="form-group" style="margin-bottom:35px;">
						<label class="col-xs-6 col-sm-3 control-label no-padding-right">
						本次申请担保债权金额（元）
						</label>
						<div class="col-xs-6 col-sm-3">
							<span class="block input-icon input-icon-right">
								<input type="text" name="appGuaDebtInterAmt" class="form-control"  readonly="readonly"
								/>
							</span>
						</div>
						<div class="help-block col-xs-8 col-sm-reset inline">
						</div>
						<label class="col-xs-6 col-sm-3 control-label no-padding-right">
							实际担保率
						</label>
						<div class="col-xs-6 col-sm-3">
							<span class="block input-icon input-icon-right">
								<input type="text" name="actualGuaranteeRate" id="actualGuaranteeRate" class="form-control" readonly="readonly" 
								/>
							</span>
						</div>
						
					</div>
					
					<div class="form-group" style="margin-bottom:25px;" >
						<label class="col-xs-6 col-sm-3 control-label no-padding-right">
						本次实际担保债权金额（元）
						</label>
						<div class="col-xs-6 col-sm-3">
							<span class="block input-icon input-icon-right">
								<input type="text" name="actualCreditAmount" class="form-control" 
								/>
							</span>
						</div>
						<div class="help-block col-xs-8 col-sm-reset inline">
						</div>
						<label class="col-xs-6 col-sm-3 control-label no-padding-right">
						</label>
						<div class="col-xs-6 col-sm-3">
							<span class="block input-icon input-icon-right">
							</span>
						</div>
						
					</div>
					
					</div>
					<div class="modal-footer" style="background-color:#fff!important;">
						<button id="modCollateral_sure" class="btn btn-sm btn-primary" type="button">
							<i class="ace-icon fa fa-save">
							</i>
							保存
						</button>
						<button class="btn btn-sm" data-dismiss="modal">
							<i class="ace-icon fa fa-times">
							</i>
							取消
						</button>
					</div>
				</form>
		</div>
	</div>
</div>