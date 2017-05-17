<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
%>
	<%@include file="../../../commons/taglibs.jsp" %>
		<div class="row">
			<div class="col-md-12">
				<h4 class="blue">预期现金流量表
					<button id="btn-expectCash" type="button" class="btn btn-xs btn-success" style="float:right;">
						<i class="ace-icon fa fa-plus">
						</i>
					</button></h4>
				<div class="row">
					<table id="cashFlowTb_nd" class="table table-striped table-hover" style="white-space: nowrap;">
						<thead>
							<tr>
								<th>
									序号
								</th>
								<th>
									实际月份
								</th>
								<th>
									-家庭支出
								</th>
								<th>
									-其他贷款还款
								</th>
								<th>
									支出现金流出合计
								</th>
								<th>
									月末现金流余额
								</th>
								<th>
									贷款前现金流余额
								</th>
								<th>
									+贷款资金流入
								</th>
								<th>
									-贷款还款现金流出
								</th>
								<th>
									贷款后现金流余额
								</th>
								<th>
									操作
								</th>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
				</div>
				<div class="row" id="showAddEcfDiv" style="margin-top: 10px;display:none;">
				<h4 class="blue">
					新增月度现金流量
				</h4>
					<form id="simple_ecfForm_nd">
						<input type="hidden" name="id" />
						<div class="form-group col-md-5">
							<label class="col-xs-12 col-sm-6 control-label no-padding-right">
								实际月份：
							</label>
							<div class="col-xs-12 col-sm-6">
								<input type="text" name="month" class="form-control required " />
							</div>
						</div>
						<div class="form-group col-md-5">
							<label class="col-xs-12 col-sm-6 control-label no-padding-right">
								-家庭支出：
							</label>
							<div class="col-xs-12 col-sm-6">
								<span class="block input-icon input-icon-right">
									<input type="text" name="familyCost" class="form-control required " />
								</span>
							</div>
						</div>
						<div class="form-group col-md-5">
							<label class="col-xs-12 col-sm-6 control-label no-padding-right">
								+贷款资金流入：
							</label>
							<div class="col-xs-12 col-sm-6">
								<input type="text" name="income" class="form-control  required" />
							</div>
						</div>
						<div class="form-group col-md-5">
							<label class="col-xs-12 col-sm-6 control-label no-padding-right">
								-贷款还款现金流出：
							</label>
							<div class="col-xs-12 col-sm-6">
								<span class="block input-icon input-icon-right">
									<input type="text" name="cost" class="form-control required " />
								</span>
							</div>
						</div>
						<div class="form-group col-md-5">
							<label class="col-xs-12 col-sm-6 control-label no-padding-right">
								-其他贷款还款：
							</label>
							<div class="col-xs-12 col-sm-6">
								<span class="block input-icon input-icon-right">
									<input type="text" name="otherLoanRepayment" class="form-control required " />
								</span>
							</div>
						</div>
						<div class="form-group col-md-12" align="center">
							<button type="button" class="btn btn-sm btn-primary" id="submit_Ecf_simple"
							data-loading-text="正在保存...">
								<i class="ace-icon fa fa-floppy-o">
								</i>
							管理详细
							</button>
							<button class="btn btn-sm btn-warning" type="button" onclick="javascript:$('#showAddEcfDiv').fadeOut();">
								<i class="ace-icon fa fa-times">
								</i>
							取消
							</button>
						</div>
						
					</form>
				</div>
			</div>
		</div>
		<div>
			<jsp:include page="modalEcf.jsp"></jsp:include>
		</div>