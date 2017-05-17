<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../../commons/taglibs.jsp"%>
<!-- 征信情况 -->
<div id="drCreditInfo">
<!-- Form  -->
<h4 class="blue">征信情况</h4>
	<form class="form-horizontal" id="form_dr_credit" onsubmit="return false;">
			<input type="hidden" name="projectId" value="${projectApplication.projectId}"/>
			<div id="form_dr_credit_item">
			 <!-- 动态form -->
			</div>
			<c:if test="${judgeType !='check' }">
			<div class="row">
				<div class="col-md-6 col-md-offset-5">
					<button type="submit" class="btn btn-sm btn-primary">
						<i class="ace-icon fa fa-floppy-o"></i> 保存
					</button>
				</div>
			</div>
			</c:if>
	</form>
<h4 class="blue">借款人及其配偶负债明细</h4>
<c:if test="${judgeType !='check' }">
<div class="wizard-actions">
	<button type="button" role="drCredit_drCld_Add" class="btn btn-sm btn-success">
		<i class="ace-icon fa fa-plus"></i> 新增
	</button>
</div>
</c:if>
<table id="tb_drCldList" class="table table-striped table-hover">
	<thead>
		<tr>
			<th>借款人</th>
			<th>金融机构</th>
			<th>贷款金额（元）</th>
			<th>贷款起止日期</th>
			<th>抵押物</th>
			<th>贷款余额（元）</th>
			<th>还款方式</th>
			<th>折合月均还款额</th>
			<th>是否在征信记录中</th>
			<th>操作</th>
		</tr>
	</thead>
</table>
<!--  -->
<div id="dr_credit_modal" class="modal" tabindex="-1" data-backdrop="static">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<form id="dr_credit_drCld_form" class="form-horizontal" onsubmit="return false;">
				
				<input type="hidden" name="drCreditLiabilityDetailId" value=""/>
				<input type="hidden" name="projectId" value="${projectApplication.projectId }"/>
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 id="addr_form_title" class="blue bigger">
						<i class='ace-icon fa fa-plus'></i> 新增负债明细
					</h4>
				</div>
				<div class="modal-body">
					<div class="row">
				        <div class="form-group col-md-6">
				        	<label class="col-xs-12 col-md-4 control-label no-padding-right">
								是否在征信记录中：
							</label>
							<div class="col-xs-12 col-md-8">
								<input type="radio" name="isShow" class="ace" checked="checked" value="1">
								<span class="lbl">是</span>
								<input type="radio" name="isShow" class="ace" value="0">
								<span class="lbl">否</span>
							</div>
						</div>
					</div>
					<div class="row">
				        <div class="form-group col-md-6">
				        	<label class="col-xs-12 col-md-4 control-label no-padding-right">
								借款人：
							</label>
							<div class="col-xs-12 col-md-8">
								<input type="text" name="drName" class="form-control">
							</div>
						</div>
						<div class="form-group col-md-6">
							<label class="col-xs-12 col-md-4 control-label no-padding-right">
								金融机构：
							</label>
							<div class="col-xs-12 col-md-8">
								<input type="text" name="financialOrgName" class="form-control">
							</div>
						</div>
					</div>
					<div class="row">
				        <div class="form-group col-md-6">
				        	<label class="col-xs-12 col-md-4 control-label no-padding-right">
								贷款金额（元）：
							</label>
							<div class="col-xs-12 col-md-8">
								<input type="text" name="loanAmtStr" class="form-control">
							</div>
						</div>
				        <div class="form-group col-md-6">
				        	<label class="col-xs-12 col-md-4 control-label no-padding-right">
								贷款开始日期：
							</label>
							<div class="col-xs-12 col-md-8">
								<input type="text" name="loanStartDateStr" class="form-control">
							</div>
						</div>
					</div>
					<div class="row">
				        <div class="form-group col-md-6">
				        	<label class="col-xs-12 col-md-4 control-label no-padding-right">
								贷款余额（元）：
							</label>
							<div class="col-xs-12 col-md-8">
								<input type="text" name="loanOverAmtStr" class="form-control">
							</div>
						</div>
						<div class="form-group col-md-6">
							<label class="col-xs-12 col-md-4 control-label no-padding-right">
								还款方式：
							</label>
							<div class="col-xs-12 col-md-8">
								<select class="form-control" name="repaymentModeCd">
									<option value="">请选择</option>
									<dd:options codeType="RepaymentMode"/>
								</select>
							</div>
						</div>
					</div>
					<div class="row">
				        <div class="form-group col-md-6">
				        	<label class="col-xs-12 col-md-4 control-label no-padding-right">
								折合月均还款额（元）：
							</label>
							<div class="col-xs-12 col-md-8">
								<input type="text" name="monthAvgRepayAmtStr" class="form-control">
							</div>
						</div>
						<div class="form-group col-md-6">
							<label class="col-xs-12 col-md-4 control-label no-padding-right">
								抵押物：
							</label>
							<div class="col-xs-12 col-md-8">
								<input type="checkbox" name="pawn" class="ace" value="1">
								<span class="lbl">房</span>
								<input type="checkbox" name="pawn" class="ace" value="2">
								<span class="lbl">车</span>
								<input type="checkbox" name="pawn" class="ace" value="3">
								<span class="lbl">其他</span>
								<input type="checkbox" name="pawn" class="ace" value="0">
								<span class="lbl">无</span>
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
		</div>
	</div>
</div>
</div><!--/ 征信情况 -->