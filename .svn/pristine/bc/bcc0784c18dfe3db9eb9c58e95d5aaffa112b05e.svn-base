<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
%>
	<%@include file="../../../../commons/taglibs.jsp" %>
	<!-- <h4 class="blue">
		经营信息
	</h4> -->
	<div class="space-8">
	</div>
	<div id="faq-list-2" class="panel-group accordion-style1 accordion-style2">
		<div class="step-content pos-rel" id="step-container">
			<div class="step-pane active" id="step1">
				<!-- 经营信息 -->
				<form class="form-horizontal" id="form-jyxx">
					<div class="form-group">
						<label class="col-xs-12 col-sm-2 control-label no-padding-right">
							经营内容<font color="red">*</font>
						</label>
						<div class="col-xs-12 col-sm-3">
							<span class="block input-icon input-icon-right">
								<input type="text" id="busiContent" name="busiContent" class="width-100"
								 />
							</span>
						</div>
						<div class="help-block col-xs-12 col-sm-reset inline">
						</div>
						<label class="col-xs-12 col-sm-2 control-label no-padding-right">
							经营年限<font color="red">*</font>
						</label>
						<div class="col-xs-12 col-sm-3">
							<span class="block input-icon input-icon-right">
							<div class="input-group">
								<input type="text" id="busiAge" name="busiAge" class="width-100" 
								/>
								<span class="input-group-addon">年</span>
							</div>
							</span>
						</div>
						<div class="help-block col-xs-12 col-sm-reset inline">
						</div>
					</div>
					<div class="form-group">
						<label class="col-xs-12 col-sm-2 control-label no-padding-right">
							商户名<c:if test="${requestScope.individual.employmentType=='3'}"><font><font color="red">*</font></font></c:if>
						</label>
						<div class="col-xs-12 col-sm-3">
							<span class="block input-icon input-icon-right">
								<input type="text" id="merchantName" name="merchantName" class="width-100"
								 />
							</span>
						</div>
						<div class="help-block col-xs-12 col-sm-reset inline">
						</div>
						<label class="col-xs-12 col-sm-2 control-label no-padding-right">
							地址<font color="red">*</font>
						</label>
						<div class="col-xs-12 col-sm-3">
							<span class="block input-icon input-icon-right">
								<input type="text" id="address" name="address" class="width-100" 
								/>
							</span>
						</div>
						<div class="help-block col-xs-12 col-sm-reset inline">
						</div>
					</div>
					<div class="form-group">
						<label class="col-xs-12 col-sm-2 control-label no-padding-right">
							年销售额<font color="red">*</font>
						</label>
						<div class="col-xs-12 col-sm-3">
							<span class="block input-icon input-icon-right">
							 <div class="input-group">
								<input type="text" id="yearSellAmt" name="yearSellAmt" class="width-100 formatNum"
								 />
								 <span class="input-group-addon">元</span>
							 </div>
							</span>
						</div>
						<div class="help-block col-xs-12 col-sm-reset inline">
						</div>
						<label class="col-xs-12 col-sm-2 control-label no-padding-right">
							年净利润<font color="red">*</font>
						</label>
						<div class="col-xs-12 col-sm-3">
							<span class="block input-icon input-icon-right">
							<div class="input-group">
								<input type="text" id="yearNetprofitAmt" name="yearNetprofitAmt" class="width-100 formatNum"
								 />
								 <span class="input-group-addon">元</span>
							</div>
							</span>
						</div>
						<div class="help-block col-xs-12 col-sm-reset inline">
						</div>
					</div>
					<div class="form-group">
						<label class="col-xs-12 col-sm-2 control-label no-padding-right">
							资产总额<font color="red">*</font>
						</label>
						<div class="col-xs-12 col-sm-3">
							<span class="block input-icon input-icon-right">
							<div class="input-group">
								<input type="text" id="assetsTotalAmt" name="assetsTotalAmt" class="width-100 formatNum"
								/>
								 <span class="input-group-addon">元</span>
							</div>
							</span>
						</div>
						<div class="help-block col-xs-12 col-sm-reset inline">
						</div>
						<label class="col-xs-12 col-sm-2 control-label no-padding-right">
							负债总额<font color="red">*</font>
						</label>
						<div class="col-xs-12 col-sm-3">
							<span class="block input-icon input-icon-right">
							<div class="input-group">
								<input type="text" id="liabilitiesTotalAmt" name="liabilitiesTotalAmt"
								class="width-100 formatNum" />
								 <span class="input-group-addon">元</span>
							</div>
							</span>
						</div>
						<div class="help-block col-xs-12 col-sm-reset inline">
						</div>
					</div>
					<div class="form-group">
						<label class="col-xs-12 col-sm-2 control-label no-padding-right">
							其他经营项目
						</label>
						<div class="col-xs-12 col-sm-3">
							<span class="block input-icon input-icon-right">
								<input type="text" id="otherProject" name="otherProject" class="width-100"
								 />
							</span>
						</div>
						<div class="help-block col-xs-12 col-sm-reset inline">
						</div>
						<label class="col-xs-12 col-sm-2 control-label no-padding-right">
							其他项目资产总额
						</label>
						<div class="col-xs-12 col-sm-3">
							<span class="block input-icon input-icon-right">
							<div class="input-group">
								<input type="text" id="otherAssetsTotalAmt" name="otherAssetsTotalAmt"
								class="width-100 formatNum"  />
								 <span class="input-group-addon">元</span>
							</div>
							</span>
						</div>
						<div class="help-block col-xs-12 col-sm-reset inline">
						</div>
					</div>
					<div class="form-group">
						<label class="col-xs-12 col-sm-2 control-label no-padding-right">
							其他项目年收入
						</label>
						<div class="col-xs-12 col-sm-3">
							<span class="block input-icon input-icon-right">
							<div class="input-group">
								<input type="text" id="otherYearIncome" name="otherYearIncome" class="width-100 formatNum"
								 />
								  <span class="input-group-addon">元</span>
							</div>
							</span>
						</div>
						<div class="help-block col-xs-12 col-sm-reset inline">
						</div>
						<label class="col-xs-12 col-sm-2 control-label no-padding-right">
						</label>
						<div class="col-xs-12 col-sm-3">
							<span class="block input-icon input-icon-right">
							</span>
						</div>
						<div class="help-block col-xs-12 col-sm-reset inline">
						</div>
					</div>
					<div class="wizard-actions" style=" text-align:center;">
						<hr/>
						<span id="jyxxSpan">
							<button class="btn btn-sm btn-primary" id="saveJYXX" type="submit" data-loading-text="正在保存">
								<i class="ace-icon fa fa-floppy-o">
								</i>
								保存
							</button>
						</span>
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