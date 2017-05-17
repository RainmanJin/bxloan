<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../../../../commons/taglibs.jsp" %>
<!-- <h4 class="blue">
	财务信息</h4> -->
<div class="space-8"></div>
<div id="faq-list-2"
	class="panel-group accordion-style1 accordion-style2">
	<div class="step-content pos-rel" id="step-container">
		<div class="step-pane active" id="step1">
			<form class="form-horizontal" id="form-cwxx">
				<div class="form-group">
					<label class="col-xs-12 col-sm-2 control-label no-padding-right">
						本地房产情况<font color="red">*</font> </label>
					<div class="col-xs-12 col-sm-3">
						<span class="block input-icon input-icon-right"> <select
							name="localHouseCondition" id="localHouseCondition"
							class="form-control" onchange="houseConditionChange(this)">
								<dd:options codeType="CommonWhether" />
						</select> </span>
					</div>
					<div class="help-block col-xs-12 col-sm-reset inline"></div>
					<label class="col-xs-12 col-sm-2 control-label no-padding-right">
						共有套数<font color="red">*</font> </label>
					<div class="col-xs-12 col-sm-3">
						<span class="block input-icon input-icon-right"> 
						 <div class="input-group">
						<input type="text" id="localHouseNum" name="localHouseNum" class="width-100"
							 />
						<span class="input-group-addon">套</span> 
						</div>
						</span>
					</div>
					<div class="help-block col-xs-12 col-sm-reset inline"></div>
				</div>
				<div class="form-group">
					<label class="col-xs-12 col-sm-2 control-label no-padding-right">
						总价值预估<font color="red">*</font> </label>
					<div class="col-xs-12 col-sm-3">
						<span class="block input-icon input-icon-right"> 
						 <div class="input-group">
						<input type="text" id="houseesPrice" name="houseesPrice" class="width-100 formatNum"
							 />
						<span class="input-group-addon">元</span> 
						</div>
						</span>
					</div>
					<div class="help-block col-xs-12 col-sm-reset inline"></div>
					<label class="col-xs-12 col-sm-2 control-label no-padding-right">
						其中按揭贷款<font color="red">*</font> </label>
					<div class="col-xs-12 col-sm-3">
						<span class="block input-icon input-icon-right"> 
						<div class="input-group">
							<input type="text" id="houseesLoan" name="houseesLoan" class="width-100 formatNum"
							 /> 
							<span class="input-group-addon">元</span>
						</div>	 
						</span>
					</div>
					<div class="help-block col-xs-12 col-sm-reset inline"></div>
				</div>
				<div class="form-group">
					<label class="col-xs-12 col-sm-2 control-label no-padding-right">
						车产信息 </label>
					<div class="col-xs-12 col-sm-3">
						<span class="block input-icon input-icon-right"> <select
							name="carCondition" id="carCondition" class="form-control"
							onchange="carConditionChange(this)">
								<dd:options codeType="CommonWhether" />
						</select> </span>
					</div>
					<div class="help-block col-xs-12 col-sm-reset inline"></div>
					<label class="col-xs-12 col-sm-2 control-label no-padding-right">
						共有辆数 </label>
					<div class="col-xs-12 col-sm-3">
						<span class="block input-icon input-icon-right">
						 <div class="input-group">
						<input type="text" id="carNum" name="carNum" class="width-100"
							 />
						<span class="input-group-addon">辆</span> 
						</div>
						</span>
					</div>
					<div class="help-block col-xs-12 col-sm-reset inline"></div>
				</div>
				<div class="form-group">
					<label class="col-xs-12 col-sm-2 control-label no-padding-right">
						借款人贷款笔数<font color="red">*</font> </label>
					<div class="col-xs-12 col-sm-3">
						<span class="block input-icon input-icon-right"> 
						<div class="input-group">
							<input type="text" id="loanNum" name="loanNum" class="width-100"
							/> 
							 <span class="input-group-addon">笔</span>
						</div>
						</span>
					</div>
					<div class="help-block col-xs-12 col-sm-reset inline"></div>
					<label class="col-xs-12 col-sm-2 control-label no-padding-right">
						余额<font color="red">*</font> </label>
					<div class="col-xs-12 col-sm-3">
						<span class="block input-icon input-icon-right"> 
						<div class="input-group">
							<input type="text" id="loanBalance" name="loanBalance" class="width-100 formatNum"
							 />
							 <span class="input-group-addon">元</span> 
						</div>
						</span>
					</div>
					<div class="help-block col-xs-12 col-sm-reset inline"></div>
				</div>
				<div class="form-group">
					<label class="col-xs-12 col-sm-2 control-label no-padding-right">
						月还款额<font color="red">*</font> </label>
					<div class="col-xs-12 col-sm-3">
						<span class="block input-icon input-icon-right"> 
						<div class="input-group">
						<input type="text" id="repaymentMonth" name="repaymentMonth"
							class="width-100 formatNum" /> 
						 <span class="input-group-addon">元</span>
						</div>
						</span>
					</div>
					<div class="help-block col-xs-12 col-sm-reset inline"></div>
					<label class="col-xs-12 col-sm-2 control-label no-padding-right">
						最高额度<font color="red">*</font> </label>
						
					<div class="col-xs-12 col-sm-3">
						<span class="block input-icon input-icon-right"> 
						<div class="input-group">
							<input type="text" id="highestLoanQuota" name="highestLoanQuota" class="width-100 formatNum"
							 />
							 <span class="input-group-addon">元</span> 
						</div>
						</span>
					</div>
					<div class="help-block col-xs-12 col-sm-reset inline"></div>
				</div>
				<div class="form-group">
					<label class="col-xs-12 col-sm-2 control-label no-padding-right">
						最高额度贷款行<font color="red">*</font> </label>
					<div class="col-xs-12 col-sm-3">
						<span class="block input-icon input-icon-right"> <input
							type="text" id="highestLoanBank" name="highestLoanBank"
							class="width-100" /> </span>
					</div>
					<div class="help-block col-xs-12 col-sm-reset inline"></div>
					<label class="col-xs-12 col-sm-2 control-label no-padding-right">
						借款人税后月收入<font color="red">*</font> </label>
					<div class="col-xs-12 col-sm-3">
						<span class="block input-icon input-icon-right"> 
						<div class="input-group">
							<input type="text" id="monthIncome" name="monthIncome" class="width-100 formatNum"
							 />
							<span class="input-group-addon">元</span>
						</div>	
						</span>
					</div>
					<div class="help-block col-xs-12 col-sm-reset inline"></div>
				</div>
				<div class="wizard-actions" style=" text-align:center;">
					<span id="cwxxSpan">
						<button class="btn btn-sm btn-primary" id="saveJYCW" type="button"
							data-loading-text="正在保存">
							<i class="ace-icon fa fa-floppy-o"> </i> 保存
						</button> </span>
					<!-- <button class="btn btn-white btn-default btn-round" data-last="Finish"
																type="button">
																<i class="ace-icon fa fa-arrow-right icon-on-right"></i>
																取消
																</button> -->
				</div>
			</form>
		</div>
	</div>
</div>