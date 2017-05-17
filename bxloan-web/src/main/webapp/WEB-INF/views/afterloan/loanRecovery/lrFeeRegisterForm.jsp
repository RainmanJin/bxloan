<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.coamc-tech.com/taglibs/datadict" prefix="dd" %>
<div id="input_fee_register_modal" class="modal" tabindex="-1" data-backdrop="static">
	<div class="modal-dialog" style="width:70%">
		<div class="modal-content">
			<!-- input_fee_register_form start  -->
			<form id="input_fee_register_form" class="form-horizontal" onsubmit="return false;">
				<!-- 隐藏域 -->
				<input type="hidden" name="contractId" value="" />
				<input type="hidden" name="partyId" value=""/>
				<input type="hidden" name="addressId" value=""/>
				<input type="hidden" name="bizExpenseRateId" value="" />
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="blue bigger">
						<i class='ace-icon fa fa-plus'></i>&nbsp;费用登记
					</h4>
				</div>
				<div class="modal-body">
					
				<div class="row">
					<div class="col-md-12">
			        <div class="form-group">
			        	<label class="col-xs-12 col-sm-2 control-label no-padding-right">
							客户名称：
						</label>
						<div class="col-xs-12 col-sm-3">
							<span class="block input-icon input-icon-right">
								<input name="customerName" class="form-control" readonly="readonly">
							</span>
						</div>
						<div class="help-block col-xs-12 col-sm-reset inline"></div>
						<label class="col-xs-12 col-sm-2 control-label no-padding-right">
							还款编号：
						</label>
						<div class="col-xs-12 col-sm-3">
							<span class="block input-icon input-icon-right">
								<input name="repayLoanNum" class="form-control" readonly="readonly">
							</span>
						</div>
						<div class="help-block col-xs-12 col-sm-reset inline"></div>
					    
					</div>
					
					<div class="form-group">
						<label class="col-xs-12 col-sm-2 control-label no-padding-right">
							贷款产品：
						</label>
						<div class="col-xs-12 col-sm-3">
							<span class="block input-icon input-icon-right">
							 	<input name="loanProductName" class="form-control" readonly="readonly">
							</span>
						</div>
						<div class="help-block col-xs-12 col-sm-reset inline"></div>
						<label class="col-xs-12 col-sm-2 control-label no-padding-right">
							客户经理：
						</label>
						<div class="col-xs-12 col-sm-3">
							<span class="block input-icon input-icon-right">
								<input name="custMngName" class="form-control" readonly="readonly">
							</span>
						</div>
						<div class="help-block col-xs-12 col-sm-reset inline"></div>
					</div> 
					
					<div class="form-group">
						<label class="col-xs-12 col-sm-2 control-label no-padding-right">
							贷款金额：
						</label>
						<div class="col-xs-12 col-sm-3">
							<span class="block input-icon input-icon-right">
							<div class="input-group">
								<input name="loanAmtStr" class="form-control formatNum" readonly="readonly">
								 <span class="input-group-addon">元</span>
							</div>
							</span>
						</div>
						<div class="help-block col-xs-12 col-sm-reset inline"></div>
						<label class="col-xs-12 col-sm-2 control-label no-padding-right">
							贷款期限：
						</label>
						<div class="col-xs-12 col-sm-3">
							<span class="block input-icon input-icon-right">
							<div class="input-group">
								<input name="contractTerm" class="form-control" readonly="readonly">
								 <span class="input-group-addon"></span>
							</div>
							</span>
						</div>
						<div class="help-block col-xs-12 col-sm-reset inline"></div>
					</div>
					<div class="form-group ">
						<label class="col-xs-12 col-sm-2 control-label no-padding-right">
							<font color="red">* </font>实还日期：
						</label>
						<div class="col-xs-12 col-sm-3">
							<span class="block input-icon input-icon-right">
								<input name="repayDateStr" id="repayDateStr" type="text" class="form-control formdate" 
								data-date-format="yyyy-mm-dd" readonly="readonly"/>
							</span>
						</div>
						<div class="help-block col-xs-12 col-sm-reset inline"></div>
						<label class="col-xs-12 col-sm-2 control-label no-padding-right">
							<font color="red">* </font>资金来源：
						</label>
						<div class="col-xs-12 col-sm-3">
							<span class="block input-icon input-icon-right">
								<select name="fundsSource" class="form-control">
									<option value="">请选择</option>
									<dd:options codeType="FundsSource"/>
			                    </select>
							</span>
						</div>
						<div class="help-block col-xs-12 col-sm-reset inline"></div>
					</div>
					<div class="form-group ">
						<label class="col-xs-12 col-sm-2 control-label no-padding-right">
							<font color="red">* </font>费用类型：
						</label>
						<div class="col-xs-12 col-sm-3">
							<span class="block input-icon input-icon-right">
								<select name="feeType" class="form-control">
									<option value="">请选择</option>
									<dd:options codeType="FeeType"/>
			                    </select>
							</span>
						</div>
						<div class="help-block col-xs-12 col-sm-reset inline"></div>
						<label class="col-xs-12 col-sm-2 control-label no-padding-right">
							<font color="red">* </font>收费金额：
						</label>
						<div class="col-xs-12 col-sm-3">
							<span class="block input-icon input-icon-right">
							<div class="input-group">
								<input name="expenseAmt" type="text" class="form-control formatNum" 
								placeholder="0.00" />
								<span class="input-group-addon">元</span>
							</div>
							</span>
						</div>
						<div class="help-block col-xs-12 col-sm-reset inline"></div>
					</div>
					<div class="form-group ">
						<label class="col-xs-12 col-sm-2 control-label no-padding-right">
							备注：
						</label>
						<div class="col-xs-12 col-sm-8">
							<span class="block input-icon input-icon-right">
								<textarea name="remark" class="form-control"  rows="3"></textarea>
							</span>
						</div>
						<div class="help-block col-xs-12 col-sm-reset inline"></div>
					</div>
					</div>
				</div>
				</div>
				<div class="modal-footer">
					<button type="submit" class="btn btn-sm btn-primary" >
						<i class="ace-icon fa fa-search"></i> 确定
					</button>
					<button type="button" class="btn btn-sm btn-default" data-dismiss="modal">
						<i class="ace-icon fa fa-times"></i> 取消
					</button>
				</div>
			</form>
			<!-- input_fee_register_form end  -->
		</div>
	</div>
</div>