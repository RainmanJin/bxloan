<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../commons/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="zh">
	<head>
		<base href="${ctx}/">
		<title>${title }</title>
		<meta charset="utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		
		<meta name="description" content="Dashboard page" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
		<c:import url="../../commons/pre-include.jsp" charEncoding="UTF-8"/>
		<script>var $$ctx = "${ctx}/";</script>
	</head>
<body class="no-skin">
	<div class="main-container" id="main-container">
		<script type="text/javascript">
			try{ace.settings.check('main-container' , 'fixed')}catch(e){}
		</script>
		<div class="page-content">
			<div class="page-header">
				<h1>
					贷款试算
				</h1>
			</div>
			<form id="hidden_form">
				<input name="" type="hidden" value="">
				<input name="" type="hidden" value="">
				<input name="" type="hidden" value="">
				<input name="" type="hidden" value="">
				<input name="" type="hidden" value="">
			</form>
			<!-- form start-->
			<form class="form-horizontal" id="interesForm" method="post" role="form">									
				<div id="faq-list-5" class="panel-group accordion-style1 accordion-style2">
					<div class="step-content pos-rel" id="step-container">
					<div class="step-pane active" id="step1">
					  <div class="row">
					      <div class="form-group">
							<label class="col-xs-12 col-sm-2 control-label no-padding-right">贷款金额(元)：</label>
							<div class="col-xs-12 col-sm-3">
								<span class="block input-icon input-icon-right">
									<input type="text" id="loanAmount" name="loanAmount" class="form-control" value="${projApp.applyAmt }">
								</span>														
							</div>
							<label class="col-xs-12 col-sm-2 control-label no-padding-right">贷款时间：</label>
							<div class="col-xs-12 col-sm-3">
								<span class="block input-icon input-icon-right">
									<i class="ace-icon fa fa-calendar"></i>
									<input type="text" id="loanStartDate" name="loanStartDate" data-date-format="yyyy-mm-dd" class="form-control" readonly="readonly">
								</span>														
							</div>
						</div>
					  </div>
					 <div class="row">
					      <div class="form-group">
							<!-- <div class="help-block col-xs-12 col-sm-reset inline"> </div> -->
							<label class="col-xs-12 col-sm-2 control-label no-padding-right">期限(月)：</label>
							<div class="col-xs-12 col-sm-3">
								<span class="block input-icon input-icon-right">
									<input type="text" id="applyTerm" name="applyTerm" value="${projApp.applyTerm }" class="form-control">
								</span>														
							</div>
								<label class="col-xs-12 col-sm-2 control-label no-padding-right">还款方式：</label>
								<div class="col-xs-12 col-sm-3">
									<span class="input-icon block input-icon-right">
										<select id="repayment" name="repayment" style="height: 34px;" class="form-control">
											<option value="1" ${projApp.repayingMode==1?'selected="selected"':''}>按固定周期付息、到期还本</option>
											<option value="2" ${projApp.repayingMode==2?'selected="selected"':''}>期末本息一次付清</option>
											<option value="3" ${projApp.repayingMode==3?'selected="selected"':''}>按自定义还款计划还本付息</option>
											<option value="4" ${projApp.repayingMode==4?'selected="selected"':''}>等额本息还款</option>
											<option value="5" ${projApp.repayingMode==5?'selected="selected"':''}>等额本金还款</option>
											<option value="6" ${projApp.repayingMode==6?'selected="selected"':''}>等本等息</option>
										</select>
									</span>
								</div>
								<div class="help-block col-xs-12 col-sm-reset inline"></div>
						    </div>
						  </div>
						  <div class="row">
						      <div class="form-group">
							    <label class="col-xs-12 col-sm-2 control-label no-padding-right">还款日：</label>
								<div class="col-xs-12 col-sm-3">
									<span class="block input-icon input-icon-right">
										<input type="text" id="repaymentDate" name="repaymentDate" class="form-control" >
									</span>														
								</div>
								<label class="col-xs-12 col-sm-2 control-label no-padding-right">年利率(%)：</label>
								<div class="col-xs-12 col-sm-3">
									<span class="block input-icon input-icon-right">
										<input type="text" id="rate" name="rate" class="form-control" value="${projApp.bizRate*100 }">
									</span>														
								</div>
							</div>
						  </div>
						  <div class="row">
						      <div class="form-group">
								<label class="col-xs-12 col-sm-2 control-label no-padding-right">还款周期月数：</label>
								<div class="col-xs-12 col-sm-3">
									<span class="block input-icon input-icon-right">
										<input type="text" id="repaymentNumber" name="repaymentNumber" class="form-control" value="${projApp.replyPeriodNum }" >
									</span>														
								</div>
						       </div>
						  </div>
						  <div class="row" align="right">
						       <div>
						       <button id="countRate" type="button" class="btn btn-sm btn-primary">
						       <i class="ace-ico fa fa-cny"></i>
						                     计算</button>
						       <button id="resetForm" type="button" class="btn btn-sm btn-default">
						       <i class="ace-icon fa fa-undo"></i>
						                     重置</button>
						       </div>
						  </div><br><br>
						  <div>
						     <span class="blue" style="font-size:20px">
								<i class="orange ace-icon fa fa-credit-card bigger-110"></i>
								还款计划
						     </span>
						  </div>
						   <table id="tbl" class="table table-striped table-hover">
								    <thead>
										<tr>
											<th>期次</th>
											<th>计划还款日</th>
											<th>本期应还金额(元)</th>
											<th>应还本金(元)</th>
											<th>应还利息(元)</th>
											<th>累计还本(元)</th>
											<th>贷款余额(元)</th>
										</tr>
								     </thead>
								  <tbody id="tbld">
								  </tbody>
							 </table>
						</div>
						</div>
					</div>
				</form>
			<!-- form end-->
		</div>
	</div>
	<c:import url="../../commons/post-include.jsp" charEncoding="UTF-8"/>
	<script>
		seajs.use('${ctx}/static/my/js/approval/loanTrial/main');
	</script>
</body>
</html>