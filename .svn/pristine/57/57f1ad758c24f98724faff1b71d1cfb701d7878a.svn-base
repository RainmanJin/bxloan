<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.coamc-tech.com/taglibs/datadict" prefix="dd" %>
<div id="input_repay_modal" class="modal" tabindex="-1" data-backdrop="static">
	<div class="modal-dialog" style="width:70%">
		<div class="modal-content">
			<form role="input_repay_form" class="form-horizontal">
				<input type="hidden" name="partyId" value=""/>
				<input type="hidden" name="addressId" value=""/>
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 id="addr_form_title" class="blue bigger">
						<i class='ace-icon fa fa-plus'></i>&nbsp;正常还款
					</h4>
				</div>
				<div class="modal-body">
					<!-- 隐藏域 -->
					<input type="hidden" name="contractId">
					<input type="hidden" name="repayingPlanId">
					<input type="hidden" name="repayingPlanDetailId">
					<input type="hidden" name="overdue">
					<input type="hidden" name="normal2Overdue">
				<!-- panel-group start-->
				<div id="faq-list-10" class="panel-group accordion-style1 accordion-style2">
					<!-- panel start-->
					<div class="panel panel-default" >
						<div class="panel-heading">
							<a href="#faq-1-1" data-parent="#faq-list-1" data-toggle="collapse" class="accordion-toggle" >
								<i class="pull-right ace-icon fa fa-chevron-down" data-icon-hide="ace-icon fa fa-chevron-down"
								data-icon-show="ace-icon fa fa-chevron-left"></i>
								<i class="ace-icon fa fa-credit-card"> </i> &nbsp; 基本信息
							</a>
						</div>
						<!-- panel-collapse start -->
						<div class="panel-collapse collapse in" id="faq-1-1">
							<div class="panel-body"><!-- panel-body start -->
								<div class="row"><!-- row start -->
									<div class="col-md-12">
											<div class="row">
												<div class="form-group col-md-6">
													<label class="col-md-4 control-label">
														还款编号：
													</label>
													<div class="col-md-8">
														<input type="text" name="repayLoanNum" class="form-control" readonly="readonly"/>
													</div>
												</div>
												<div class="form-group col-md-6">
													<label class="col-md-4 control-label">
														客户经理：
													</label>
													<div class="col-md-8">
														<input type="text" name="custMngName" class="form-control" readonly="readonly"/>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="form-group col-md-6">
													<label class="col-md-4 control-label">
														客户编号：
													</label>
													<div class="col-md-8">
														<input type="text" name="customerNum" class="form-control" readonly="readonly"/>
													</div>
												</div>
												<div class="form-group col-md-6">
													<label class="col-md-4 control-label">
														客户名称：
													</label>
													<div class="col-md-8">
														 <input type="text" name="customerName" class="form-control" readonly="readonly"/>
													</div>
												</div>
											</div>
										</div>
									</div><!-- row end -->
							</div><!-- panel-body end -->
						</div><!-- panel-collapse end -->
					</div><!-- panel end-->
					<!-- panel start-->
					<div class="panel panel-default" >
						<div class="panel-heading">
							<a href="#faq-2-1" data-parent="#faq-list-1" data-toggle="collapse" class="accordion-toggle" >
								<i class="pull-right ace-icon fa fa-chevron-down" data-icon-hide="ace-icon fa fa-chevron-down"
								data-icon-show="ace-icon fa fa-chevron-left"></i>
								<i class="ace-icon fa fa-credit-card"> </i> &nbsp; 合同信息
							</a>
						</div>
						<!-- panel-collapse start -->
						<div class="panel-collapse collapse in" id="faq-2-1">
							<div class="panel-body"><!-- panel-body start -->
								<div class="row"><!-- row start -->
									<div class="col-md-12">
											<div class="row">
												<div class="form-group col-md-6">
													<label class="col-md-4 control-label">
														合同编号：
													</label>
													<div class="col-md-8">
														<input type="text" name="contractNum" class="form-control" readonly="readonly"/>
													</div>
												</div>
												<div class="form-group col-md-6">
													<label class="col-md-4 control-label">
														贷款产品：
													</label>
													<div class="col-md-8">
														<input type="text" name="productName" class="form-control" readonly="readonly"/>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="form-group col-md-6">
													<label class="col-md-4 control-label">
														放款日期：
													</label>
													<div class="col-md-8">
														<input type="text" name="payloanDateStr" class="form-control" readonly="readonly"/>
													</div>
												</div>
												<div class="form-group col-md-6">
													<label class="col-md-4 control-label">
														到期日期：
													</label>
													<div class="col-md-8">
														 <input type="text" name="expirationDateStr" class="form-control" readonly="readonly"/>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="form-group col-md-6">
													<label class="col-md-4 control-label">
														合同期限：
													</label>
													<div class="col-md-8">
														<span class="block input-icon input-icon-right">
														<div class="input-group">
															<input type="text" name="contractTerm" class="form-control" readonly="readonly"/>
															<span role="" class="input-group-addon">年</span>
														</div>
														</span>
													</div>
												</div>
												<div class="form-group col-md-6">
													<label class="col-md-4 control-label">
														还款方式：
													</label>
													<div class="col-md-8">
														 <select  name="repayModeCd"  class="form-control" disabled="disabled">
																<option value=""></option>
																<dd:options codeType="RepaymentMode" />
														</select>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="form-group col-md-6">
													<label class="col-md-4 control-label">
														合同利率：
													</label>
													<div class="col-md-8">
														<span class="block input-icon input-icon-right">
														<div class="input-group">
															<input type="text" name="contractRate" class="form-control" readonly="readonly"/>
															<span class="input-group-addon">%</span>
														</div>
														</span>
													</div>
												</div>
												<div class="form-group col-md-6">
													<label class="col-md-4 control-label">
														约定还款日：
													</label>
													<div class="col-md-8">
														 <input type="text" name="arrangeRepayDay" class="form-control" readonly="readonly"/>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="form-group col-md-6">
													<label class="col-md-4 control-label">
														合同金额：
													</label>
													<div class="col-md-8">
														<span class="block input-icon input-icon-right">
														<div class="input-group">
															<input type="text" name="contractAmt" class="form-control" readonly="readonly"/>
															<span class="input-group-addon">元</span>
														</div>
														</span>
													</div>
												</div>
												<div class="form-group col-md-6">
													<label class="col-md-4 control-label">
														合同余额：
													</label>
													<div class="col-md-8">
														 <span class="block input-icon input-icon-right">
														<div class="input-group">
															<input type="text" name="contractBalance" class="form-control" readonly="readonly"/>
															<span class="input-group-addon">元</span>
														</div>
														</span>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="form-group col-md-6">
													<label class="col-md-4 control-label">
														累计回收额：
													</label>
													<div class="col-md-8">
														<span class="block input-icon input-icon-right">
														<div class="input-group">
															<input type="text" name="cumulativeRepayAmt" class="form-control" readonly="readonly"/>
															<span class="input-group-addon">元</span>
														</div>
														</span>
													</div>
												</div>
												<div class="form-group col-md-6">
													<label class="col-md-4 control-label">
														回收币种：
													</label>
													<div class="col-md-8">
														 <select  name="customerType"  class="form-control" disabled="disabled">
															<dd:options codeType="CurrencyType" selected="156"/>
														</select>
													</div>
												</div>
											</div>
										</div>
									</div><!-- row end -->
							</div><!-- panel-body end -->
						</div><!-- panel-collapse end -->
					</div><!-- panel end-->
					<!-- panel start-->
					<div class="panel panel-default" >
						<div class="panel-heading">
							<a href="#faq-3-1" data-parent="#faq-list-1" data-toggle="collapse" class="accordion-toggle" >
								<i class="pull-right ace-icon fa fa-chevron-down" data-icon-hide="ace-icon fa fa-chevron-down"
								data-icon-show="ace-icon fa fa-chevron-left"></i>
								<i class="ace-icon fa fa-credit-card"> </i> &nbsp; 实还信息
							</a>
						</div>
						<!-- panel-collapse start -->
						<div class="panel-collapse collapse in" id="faq-3-1">
							<div class="panel-body"><!-- panel-body start -->
								<div class="row"><!-- row start -->
									<div class="col-md-12">
											<div class="row">
												<div class="form-group col-md-6">
													<label class="col-md-4 control-label">
														本次应还总额：
													</label>
													<div class="col-md-8">
														<span class="block input-icon input-icon-right">
														<div class="input-group">
															<input type="text" name="customerName" class="form-control" readonly="readonly"/>
															<span class="input-group-addon">元</span>
														</div>
														</span>
													</div>
												</div>
												<div class="form-group col-md-6">
													<label class="col-md-4 control-label">
														实还日期 <font color="red">*</font>：
													</label>
													<div class="col-md-8">
														 <input type="text" name="actualRepayDateStr" class="form-control dp-date" readonly="readonly"/>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="form-group col-md-6">
													<label class="col-md-4 control-label">
														多收金额<font color="red">*</font>：
													</label>
													<div class="col-md-8">
														<span class="block input-icon input-icon-right">
														<div class="input-group">
															<input type="text" name="overchangeAmt" class="form-control"/>
															<span class="input-group-addon">元</span>
														</div>
														</span>
													</div>
												</div>
												<div class="form-group col-md-6">
													<label class="col-md-4 control-label">
														银行转账<font color="red">*</font>：
													</label>
													<div class="col-md-8">
														<span class="block input-icon input-icon-right">
														<div class="input-group">
															<input type="text" name="bankAmt" class="form-control"/>
															<span class="input-group-addon">元</span>
														</div>
														</span>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="form-group col-md-6">
													<label class="col-md-4 control-label">
														现金回款<font color="red">*</font>：
													</label>
													<div class="col-md-8">
														<span class="block input-icon input-icon-right">
														<div class="input-group">
															<input type="text" name="cashAmt" class="form-control"/>
															<span class="input-group-addon">元</span>
														</div>
														</span>
													</div>
												</div>
												<div class="form-group col-md-6">
													<label class="col-md-4 control-label">
														本次损溢：
													</label>
													<div class="col-md-8">
														<span class="block input-icon input-icon-right">
														<div class="input-group">
															<input type="text" name="0" class="form-control" readonly="readonly"/>
															<span class="input-group-addon">元</span>
														</div>
														</span>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="form-group col-md-6">
													<label class="col-md-4 control-label">
														本次回收合计<font color="red">*</font>：
													</label>
													<div class="col-md-8">
														<span class="block input-icon input-icon-right">
														<div class="input-group">
															<input type="text" name="a" class="form-control" readonly="readonly"/>
															<span class="input-group-addon">元</span>
														</div>
														</span>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="form-group col-md-12" style="padding-right: 36px;">
													<label class="col-md-2 control-label">
														备注<font color="red">*</font>：
													</label>
													<div class="col-md-10" >
														<textarea rows="3" name="remark" class="form-control"></textarea>
													</div>
												</div>
											</div>
										</div>
									</div><!-- row end -->
							</div><!-- panel-body end -->
						</div><!-- panel-collapse end -->
					</div><!-- panel end-->
					<!-- panel start-->
					<div class="panel panel-default" >
						<div class="panel-heading">
							<a href="#faq-4-1" data-parent="#faq-list-1" data-toggle="collapse" class="accordion-toggle" >
								<i class="pull-right ace-icon fa fa-chevron-down" data-icon-hide="ace-icon fa fa-chevron-down"
								data-icon-show="ace-icon fa fa-chevron-left"></i>
								<i class="ace-icon fa fa-credit-card"> </i> &nbsp; 还款情况
							</a>
						</div>
						<!-- panel-collapse start -->
						<div class="panel-collapse collapse in" id="faq-4-1">
							<div class="panel-body"><!-- panel-body start -->
								<div class="row"><!-- row start -->
									<div class="col-md-12">
										<table role="tb_repayment_info" class="table table-striped table-bordered table-hover dataTable">
											<thead>
												<tr>
												<th class="sorting_disabled"></th>
												<th class="sorting_disabled">日期</th>
												<th class="sorting_disabled">本息（元）</th>
												<th class="sorting_disabled">利息（元）</th>
												<th class="sorting_disabled">本金（元）</th>
												<th class="sorting_disabled"></th>
												</tr>
											</thead>
											<tbody>
												<tr>
												<td>本期应还信息</td>
												<td>2015-02-03</td>
												<td>232.23</td>
												<td>23.3</td>
												<td>2323.3</td>
												<td></td>
												</tr>
												<tr style="cursor:pointer;">
												<td>本期实还信息</td>
												<td>2015-02-03</td>
												<td>232.23</td>
												<td>23.3</td>
												<td>2323.3</td>
												<td class="center"><i class="ace-icon fa fa-chevron-down"></i></a></td>
												</tr>
												<tr class="actual_repayment" style="display: none;">
												<td >第1次</td>
												<td>2015-02-03</td>
												<td>232.23</td>
												<td>23.3</td>
												<td>2323.3</td>
												<td></td>
												</tr>
												<tr class="actual_repayment" style="display: none;">
												<td>第2次</td>
												<td>2015-02-03</td>
												<td>232.23</td>
												<td>23.3</td>
												<td>2323.3</td>
												<td></td>
												</tr>
												<tr>
												<td>本期应还未还信息</td>
												<td>2015-02-03</td>
												<td>232.23</td>
												<td>23.3</td>
												<td>2323.3</td>
												<td></td>
												</tr>
												<tr>
												<td>本次还款信息</td>
												<td>2015-02-03</td>
												<td>232.23</td>
												<td>23.3</td>
												<td>2323.3</td>
												<td></td>
												</tr>
											</tbody>
										</table>
									</div>
								</div><!-- row end -->
							</div><!-- panel-body end -->
						</div><!-- panel-collapse end -->
					</div><!-- panel end-->
				</div>
				<!-- panel-group end -->
				<div class="modal-footer">
					<button id="btn-print" type="button" class="btn btn-sm btn-primary" >
						<i class="ace-icon fa fa-print"></i> 生成还款凭证
					</button>
					<button id="btn-printMini" type="button" class="btn btn-sm btn-primary" >
						<i class="ace-icon fa fa-print"></i> 生成还款凭证（小版）
					</button>
					<button id="btn-repaySubmit" type="button" class="btn btn-sm btn-primary" >
						<i class="ace-icon fa fa-save"></i> 提交
					</button>
					<button type="button" class="btn btn-sm btn-yellow" data-dismiss="modal">
						<i class="ace-icon fa fa-times"></i> 取消
					</button>
				</div>
			</form>
		</div>
	</div>
</div>