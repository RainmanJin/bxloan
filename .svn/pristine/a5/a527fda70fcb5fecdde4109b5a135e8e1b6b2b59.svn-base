<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../../../commons/taglibs.jsp" %>
	

<form role="projectInfo" class="form-horizontal">
	<div id="faq-list-1" class="panel-group accordion-style1 accordion-style2">
		<div >
		<div class="panel panel-default" >
			<div class="panel-heading">
				<a href="#faq-0-1" data-parent="#faq-list-1" data-toggle="collapse" class="accordion-toggle" >
					<i class="pull-right ace-icon fa fa-chevron-down" data-icon-hide="ace-icon fa fa-chevron-down"
					data-icon-show="ace-icon fa fa-chevron-left"></i>
					<i class="ace-icon fa fa-credit-card"> </i> &nbsp; 客户信息
				</a>
			</div>
			<div class="panel-collapse collapse in" id="faq-0-1">
				<div class="panel-body">
					<div class="row">
						<div class="col-md-12">
								<div class="row">
									<div class="form-group col-md-6">
										<label class="col-md-4 control-label">
											客户编号：
										</label>
										<div class="col-md-8">
											<button type="button" role="btn_customer" style="text-align:left; color: #478fca !important; text-shadow:none !important; background:transparent none !important; text-decoration: underline;" class="btn btn-sm form-control"></button>
										</div>
									</div>
									<div class="form-group col-md-6">
										<label class="col-md-4 control-label">
											客户类型：
										</label>
										<div class="col-md-8">
											 <select  name="customerType"  class="form-control" disabled="disabled">
													<option value=""></option>
													<dd:options codeType="CustomerType" />
											</select>
										</div>
									</div>
								</div>
								<div class="row">
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
					</div>
				</div>
			</div>
		</div>
		<!-- panel  -->
		<div class="panel panel-default" >
			<div class="panel-heading">
				<a href="#faq-1-1" data-parent="#faq-list-1" data-toggle="collapse" class="accordion-toggle">
					<i class="pull-right ace-icon fa fa-chevron-down" data-icon-hide="ace-icon fa fa-chevron-down"
					data-icon-show="ace-icon fa fa-chevron-left"></i>
					<i class="ace-icon fa fa-credit-card"> </i> &nbsp; 项目信息
				</a>
			</div>
			<div class="panel-collapse collapse in" id="faq-1-1">
				<div class="panel-body">
						<div class="row">
							<div class="form-group col-md-6">
								<label class="col-md-4 control-label">
									业务编号：
								</label>
								<!-- <div class="col-md-8">
									<input type="text" name="projectNo" class="form-control" readonly="readonly"/>
								</div> -->
								<div class="col-md-8">
									<button type="button" role="btn_projectNo" name="projectNo" style="text-align:left; color: #478fca !important; text-shadow:none !important; background:transparent none !important; text-decoration: underline;" class="btn btn-sm form-control"></button>
								</div>
							</div>
							<div class="form-group col-md-6">
								<label class="col-md-4 control-label">
									合同编号：
								</label>
								<div class="col-md-8">
									<input type="text" name="contractNum" class="form-control" readonly="readonly"/>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="form-group col-md-6">
								<label class="col-md-4 control-label">
									贷款产品：
								</label>
								<div class="col-md-8">
									<select class="form-control" name="productType" disabled="disabled">
										<c:forEach items="${products }" var="product">
											<option value="${product[0] }">
												${product[1] }
											</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="form-group col-md-6">
								<label class="col-md-4 control-label">
									合同金额：
								</label>
								<div class="col-md-8">
									<div class="input-group">
										<input type="text" name="contractAmt" class="form-control num_amt_2fixed" readonly="readonly"/>
										<span class="input-group-addon">元</span>
									</div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="form-group col-md-6">
								<label class="col-md-4 control-label">
									合同期限：
								</label>
								<div class="col-md-8">
									<div class="input-group">
										<input type="text" name="contractTermTotal" class="form-control" readonly="readonly"/>
										<span role="contractTermUnitTotal"  class="input-group-addon">单位</span>
									</div>
								</div>
							</div>
							<div class="form-group col-md-6">
								<label class="col-md-4 control-label">
									合同余额：
								</label>
								<div class="col-md-8">
									<div class="input-group">
										<input type="text" name="contractBalance" class="form-control num_amt_2fixed" readonly="readonly"/>
										<span class="input-group-addon">元</span>
									</div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="form-group col-md-6">
								<label class="col-md-4 control-label">
									担保方式：
								</label>
								<div class="col-md-8" >
									<dd:checkbox codeType="CdsGuarantMode" cbName="guaranteeMode"  aceStyle="true" codeVals="${guaranteeMode }" />
								</div>
							</div>
							<div class="form-group col-md-6">
								<label class="col-md-4 control-label">
									投放行业：
								</label>
								<div class="col-md-8">
									<input type="text" name="industryName" class="form-control" readonly="readonly"/>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="form-group col-md-6">
								<label class="col-md-4 control-label">
									是否涉农：
								</label>
								<div class="col-md-8">
									<select name="agricultureInd"  class="form-control" disabled="disabled">
										<dd:options codeType="CommonWhether" />
									</select>
								</div>
							</div>
							<div class="form-group col-md-6">
								<label class="col-md-4 control-label">
									贷款机构：
								</label>
								<div class="col-md-8">
									<input type="text" name="applyOrgName" class="form-control" readonly="readonly"/>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="form-group col-md-6">
								<label class="col-md-4 control-label">
									是否总部协同业务：
								</label>
								<div class="col-md-8">
									<select class="form-control" name="isheadcol" disabled="disabled">
										<dd:options codeType="CommonWhether" />
									</select>
								</div>
							</div>
							<div class="form-group col-md-6">
								<label class="col-md-4 control-label">
									还款方式：
								</label>
								<div class="col-md-8">
									<select class="form-control" name="approveRepayingMode" disabled="disabled">
										<dd:options codeType="RepaymentMode" />
									</select>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="form-group col-md-6">
								<label class="col-md-4 control-label">
									还款周期月数（月）：
								</label>
								<div class="col-md-8">
									<input type="text" name="repayPrincipalMonthes" class="form-control" readonly="readonly"/>
								</div>
							</div>
							<div class="form-group col-md-6">
								<label class="col-md-4 control-label">
									开户行名称：
								</label>
								<div class="col-md-8">
									<input type="text" name="bankName" class="form-control" readonly="readonly"/>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="form-group col-md-6">
								<label class="col-md-4 control-label">
									贷款账号：
								</label>
								<div class="col-md-8">
									<input type="text" name="loanNum" class="form-control" readonly="readonly"/>
								</div>
							</div>
							<div class="form-group col-md-6">
								<label class="col-md-4 control-label">
									提前还款违约比例：
								</label>
								<div class="col-md-8">
									<div class="input-group">
										<input type="text" name="preRepaymentRate" class="form-control num_2fixed" readonly="readonly"/>
										<span class="input-group-addon">%</span>
									</div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="form-group col-md-6">
								<label class="col-md-4 control-label">
									约定方式：
								</label>
								<div class="col-md-8">
									<select name="loanDateStyle" class="form-control" disabled="disabled">
										<dd:options codeType="LoanDateStyle" />
									</select>
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
									贷款用途详细描述：
								</label>
								<div class="col-md-8">
									<textarea name="purpose" class="form-control" rows="2" readonly="readonly"></textarea>
								</div>
							</div>
							<!-- add by wangpeng on 2015-07-28 start -->
							<div class="form-group col-md-6">
								<label class="col-md-4 control-label">
									协办客户经理：
								</label>
								<div class="col-md-8">
									<select class="form-control"  id="ass"  disabled style="width:393px;height:34px">
															<c:forEach items="${assistancers}"  var="assistancer">
																		<c:if test="${assistancer.isSelected == 'true' }">
																			<option value="${assistancer.personId}"  selected="selected">${assistancer.personName}</option>
																		</c:if>
																		<c:if test="${assistancer.isSelected == 'false'}" >
																			<option value="${assistancer.personId}" >${assistancer.personName}</option>
																		</c:if>
																	</c:forEach>
									</select>
								</div>
							</div>
						    <!-- add by wangpeng on 2015-07-28 end -->
						</div>
				</div>
			</div>
		</div>
		<!-- panel  end-->
		<!-- panel  -->
		<div class="panel panel-default" >
			<div class="panel-heading">
				<a href="#faq-2-1" data-parent="#faq-list-1" data-toggle="collapse" class="accordion-toggle " >
					<i class="pull-right ace-icon fa fa-chevron-down" data-icon-hide="ace-icon fa fa-chevron-down"
					data-icon-show="ace-icon fa fa-chevron-left"></i>
					<i class="ace-icon fa fa-credit-card"> </i> &nbsp; 保险信息
				</a>
			</div>
			<div class="panel-collapse collapse in" id="faq-2-1">
				<div class="panel-body">
					
						<h6 class="blue"> 业务申请保险信息</h6>
						<div class="row">
							<div class="form-group col-md-6">
								<label class="col-sm-4 control-label">
									是否有保险：
								</label>
								<div class="col-sm-8">
									<select class="form-control" name="ifInsure" disabled="disabled">
										<dd:options codeType="CommonWhether"/>
									</select>
								</div>
							</div>
						</div>
						<div class="row ifInsure" >
							<div class="form-group col-md-6">
								<label class="col-sm-4 control-label">
									保险机构：
								</label>
								<div class="col-sm-8">
									<select class="form-control" name="insuranceOrgId" disabled="disabled">
										<c:forEach items="${insuranceCompanys }" var="insuranceCompany">
											<option value="${insuranceCompany.id }">
												${insuranceCompany.customerName }
											</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="form-group col-md-6">
								<label class="col-sm-4 control-label">
									应收保费：
								</label>
								<div class="col-sm-8">
									<div class="input-group">
										<input type="text" name="prePremium" class="form-control num_amt_2fixed" readonly="readonly"/>
										<span class="input-group-addon">元</span>
									</div>
								</div>
							</div>
						</div>
						<h6 class="blue"> 放款保险信息</h6>
						<div class="row ">
							<div class="form-group col-md-6">
								<label class="col-sm-4 control-label">
									是否有保险：
								</label>
								<div class="col-sm-8">
									<select class="form-control" name="loanIfInsure" disabled="disabled">
										<dd:options codeType="CommonWhether" />
									</select>
								</div>
							</div>
						</div>
						<div class="row loanIfInsure">
							<div class="form-group col-md-6">
								<label class="col-sm-4 control-label">
									保险机构：
								</label>
								<div class="col-sm-8">
									<select class="form-control" name="insuranceOrgId" disabled="disabled">
										<c:forEach items="${insuranceCompanys }" var="insuranceCompany">
											<option value="${insuranceCompany.id }">
												${insuranceCompany.customerName }
											</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="form-group col-md-6">
								<label class="col-sm-4 control-label">
									应收保费：
								</label>
								<div class="col-sm-8">
									<div class="input-group">
										<input type="text" name="prePremium" class="form-control num_amt_2fixed" readonly="readonly"/>
										<span class="input-group-addon">元</span>
									</div>
								</div>
							</div>
						</div>
				</div>
			</div>
		</div>
		<!-- panel  end-->
		<!-- panel  -->
		<div class="panel panel-default" >
			<div class="panel-heading">
				<a href="#faq-3-1" data-parent="#faq-list-1" data-toggle="collapse" class="accordion-toggle " >
					<i class="pull-right ace-icon fa fa-chevron-down" data-icon-hide="ace-icon fa fa-chevron-down"
					data-icon-show="ace-icon fa fa-chevron-left"></i>
					<i class="ace-icon fa fa-credit-card"> </i> &nbsp; 最终利率
				</a>
			</div>
			<div class="panel-collapse collapse in" id="faq-3-1">
				<div class="panel-body">
						<div class="row">
							<div class="form-group col-md-6">
								<label class="col-md-4 control-label">
									利率类型：
								</label>
								<div class="col-md-8">
									 <select name="finalIrTypeCd"  class="form-control required" disabled="disabled">
											<dd:options codeType="InterestRateAdjustment"  />
									</select>
								</div>
							</div>
							<div class="form-group col-md-6">
								<label class="col-md-4 control-label">
									年利率：
								</label>
								<div class="col-md-8">
									<div class="input-group">
										<input type="text" name="finalRateValue" class="form-control num_2fixed" readonly="readonly"/>
										<span class="input-group-addon">%</span>
									</div>
								</div>
							</div>
						</div>
						<div class="row finalIrTypeCd">
							<div class="form-group col-md-6">
								<label class="col-md-4 control-label">
									调整周期：
								</label>
								<div class="col-md-8">
									 <input name="finalAdjustPeriod" class="form-control" readonly="readonly">
								</div>
							</div>
							<div class="form-group col-md-6">
								<label class="col-md-4 control-label">
									利率上浮比例：
								</label>
								<div class="col-md-8">
									<div class="input-group">
										<input type="text" name="finalFloatRate" class="form-control num_2fixed" readonly="readonly"/>
										<span class="input-group-addon">%</span>
									</div>
								</div>
							</div>
						</div>
						<!-- <div class="row">
							<div class="form-group" style="padding-right:30px;">
								<label class="col-md-2 control-label">
									利率调整原因：
								</label>
								<div class="col-md-10">
									<textarea class="form-control" rows="2" readonly="readonly"></textarea>
								</div>
							</div>
						</div> -->
				</div>
			</div>
		</div>
		<!-- panel  end-->
		<!-- panel  -->
		<div class="panel panel-default" >
			<div class="panel-heading">
				<a href="#faq-4-1" data-parent="#faq-list-1" data-toggle="collapse" class="accordion-toggle " >
					<i class="pull-right ace-icon fa fa-chevron-down" data-icon-hide="ace-icon fa fa-chevron-down"
					data-icon-show="ace-icon fa fa-chevron-left"></i>
					<i class="ace-icon fa fa-credit-card"> </i> &nbsp; 逾期利率
				</a>
			</div>
			<div class="panel-collapse collapse in" id="faq-4-1">
				<div class="panel-body">
					<div class="row">
						<div class="col-md-12">
								<div class="row">
									<div class="form-group col-md-6">
										<label class="col-md-4 control-label">
											逾期利率上浮比例：
										</label>
										<div class="col-md-8">
											<div class="input-group">
												<input type="text" name="ovdueIrNegoRate" class="form-control num_2fixed" readonly="readonly"/>
												<span class="input-group-addon">%</span>
											</div>
										</div>
									</div>
									<div class="form-group col-md-6">
										<label class="col-md-4 control-label">
											挪用利率上浮比例：
										</label>
										<div class="col-md-8">
											<div class="input-group">
												<input type="text" name="perculIrNegoRate" class="form-control num_2fixed" readonly="readonly"/>
												<span class="input-group-addon">%</span>
											</div>
										</div>
									</div>
								</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		</div>
	</div>
</form>
<div id="detail-modal" class="modal" tabindex="-1">
	<div class="modal-dialog" style="width:50%">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="blue bigger">
					<i class='ace-icon fa fa-search'></i>流程详细信息
				</h4>
			</div>
			<div class="modal-body">
				<div class="row">
					<div class="col-xs-12 col-sm-10 col-sm-offset-1">
						<div class="timeline-container">
							<div class="timeline-label">
								<span class="label label-primary arrowed-in-right label-lg">最新状态</span>
							</div>
							<div class="timeline-items" id="wfDetailWarp">
								<div class="timeline-item clearfix">
									<div class="timeline-info">
										<i class="timeline-indicator ace-icon fa fa-hand-o-right btn btn-success no-hover"></i>
									</div>
									<div class="widget-box transparent">
										<div class="widget-header widget-header-small">
											<h5 class="widget-title smaller"><span class="grey">录入业务申请信息</span></h5>
											<span class="widget-toolbar no-border">
												<i class="ace-icon fa fa-clock-o bigger-110"></i>2014-08-29 16:22
											</span>
										</div>
										<div class="widget-body">
											<div class="widget-main">无审批意见</div>
										</div>
									</div>
								</div>
							</div><!-- /.timeline-items -->
						</div><!-- /.timeline-container -->
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button class="btn btn-sm btn-default" data-dismiss="modal">
					<i class="ace-icon fa fa-times"></i> 取消
				</button>
			</div>
		</div>
	</div>
</div>