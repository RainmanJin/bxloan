<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
%>
<%@include file="../../../commons/taglibs.jsp" %>
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
									<input type="text" name="projectNo" class="form-control" readonly="readonly" value="${vo.projectNo}"/>
								</span>
							</div>
							<div class="help-block col-xs-8 col-sm-reset inline">
							</div>
							<label class="col-xs-8 col-sm-2 control-label no-padding-right">
								客户类型
							</label>
							<div class="col-xs-8 col-sm-3">
								<span class="block input-icon input-icon-right">
									<select class="form-control" disabled="disabled">
									<dd:options codeType="CustomerType" selected="${vo.customerType}" />
								</select>
							    <input type="hidden" name="customerType" class="form-control" readonly="readonly" value="${vo.customerType}"/>
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
									<input type="text" name="contractNum" class="form-control" readonly="readonly" value="${vo.contractNum}"/>
								</span>
							</div>
							<div class="help-block col-xs-8 col-sm-reset inline">
							</div>
							<label class="col-xs-8 col-sm-2 control-label no-padding-right">
								客户名称
							</label>
							<div class="col-xs-8 col-sm-3">
								<span class="block input-icon input-icon-right">
									<input type="text" name="customerName" class="form-control" readonly="readonly" value="${vo.customerName}"/>
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
									<input type="text" name="productType" class="form-control" readonly="readonly" value="${vo.productType}"/>
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
									<input type="text" name="contractAmt" id="contractAmt" class="form-control" readonly="readonly" value="${vo.contractAmt}"/>
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
										<input type="text" name="contractTerm" id="contractTerm" class="form-control" readonly="readonly" value="${vo.contractTerm}"/>
										<span class="input-group-addon">${vo.contractTermUnit}</span>
									</div>
								</span>
						    </div>
							<div class="help-block col-xs-8 col-sm-reset inline">
							</div>
							<label class="col-xs-8 col-sm-2 control-label no-padding-right">
								还款方式
							</label>
							<div class="col-xs-8 col-sm-3" >
								<select class="form-control"  disabled=disabled >
										<dd:options codeType="RepaymentMode" codeVals="${productConfig.repayingMode}" selected="${vo.repayModeCd}"  />
								</select>
								<input type="hidden" name="repayModeCd" class="form-control"  value="${vo.repayModeCd}"/>
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
									<input type="text" name="repayPrincipalMonthes" class="form-control" readonly="readonly" value="${vo.repayPrincipalMonthes}"/>
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
									<input type="text" name="investmentIndustry" class="form-control" readonly="readonly" value="${vo.investmentIndustry}"/>
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
									<input type="text" id="bankName" name="bankName" class="form-control"  value="${vo.bankName}"/>
								</span>
							</div>
							<div class="help-block col-xs-8 col-sm-reset inline">
							</div>
							<label class="col-xs-8 col-sm-2 control-label no-padding-right">
								<font color='red'>*</font>贷款账号
							</label>
							<div class="col-xs-8 col-sm-3">
								<span class="block input-icon input-icon-right">
									<input type="text" id="loanNum" name="loanNum"  class="form-control" value="${vo.loanNum}"/>
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
										<dd:options codeType="LoanDateStyle"  selected="${vo.loanDateStyle}"/>
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
										<input class="form-control" type="text" name="arrangeRepayDay" id="arrangeRepayDay" value="${vo.arrangeRepayDay} "/>
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
									<textarea rows="4"  name="purpose" class="form-control" readonly="readonly" style="width: 89%;">${vo.purpose}</textarea>
								</span>
							</div>
							<div class="help-block col-xs-8 col-sm-reset inline">
							</div>
						</div>
					</div>
				</div>
				<!-- 最终利率 -->
				<div class="col-xs-8 col-sm-12 widget-container-col ui-sortable">
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
									<select  class="form-control"  disabled="disabled" >
									 <dd:options codeType="InterestRateAdjustment" selected="${vo.approveIrTypeCd}"/>
									</select>
									<input type="hidden" value="${vo.approveIrTypeCd}" name="finalIrTypeCd" id="finalIrTypeCd" />
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
									<input type="text" name="finalRateValue" id="finalRateValue" class="form-control num_2fixed" readonly="readonly" value="${vo.finalRateValue}"/>
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
									<input type="text" name="finalFloatRate" id="finalFloatRate" class="form-control num_2fixed" readonly="readonly" value="${vo.finalFloatRate}"/>
									<span class="input-group-addon">%</span>
								</div>
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
				<hr/>
				<!-- 保证人列表 -->
				<div class="col-xs-8 col-sm-12 widget-container-col ui-sortable">
					<jsp:include page="assureList.jsp"></jsp:include>
				</div>
	
			</form>
			<div class="col-xs-12">
				<div class="wizard-actions" style="text-align: center;margin-top: 10px;">
					<button type="button" class="btn btn-sm btn-primary" id="saveUCContractInfo" data-loading-text="正在保存中...">
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
				</form>
				<div class="col-xs-12">
					<div class="wizard-actions" style="text-align: center;margin-top: 10px;">
						<button type="button" class="btn btn-sm btn-primary" id="saveCreditContractInfo" data-loading-text="正在保存中...">
							<i class="ace-icon fa fa-floppy-o"> </i> 保存
						</button>
					</div>
				</div>
		</div>
	</div>
</div>