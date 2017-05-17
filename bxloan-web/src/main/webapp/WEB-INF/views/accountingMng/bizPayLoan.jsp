<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../../commons/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="zh">
<head>
	<base href="${ctx}/">
	<title>邦信微贷-账务冲正-单据业务详细</title>
	<meta charset="utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<meta name="description" content="Dashboard page" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
	<c:import url="../../commons/pre-include.jsp" charEncoding="UTF-8"/>
	<!-- inline styles related to this page -->
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
						放款信息
					</h1>
				</div>
				<form class="form-horizontal" action="">
					<div class="form-group">
					    <label class="col-xs-3 col-sm-2 control-label no-padding-right">放款编号：</label>
					  	<div class="col-sm-3">
							<input class="form-control" type="text" value="${vo.payLoanNum }" readonly="readonly">
						</div>
					 </div>
					<div class="form-group">
						<label class="col-xs-3 col-sm-2 control-label no-padding-right">客户编号：</label>
					  	<div class="col-sm-3">
							<input type="text" class="form-control" value="${vo.customerNum }" readonly="readonly">
						</div>
					    <label class="col-xs-3 col-sm-2 control-label no-padding-right">客户名称：</label>
					  	<div class="col-sm-3">
							<input class="form-control" type="text" value="${vo.customerName }" readonly="readonly">
						</div>
					 </div>
					 <div class="form-group">
					    <label class="col-xs-3 col-sm-2 control-label no-padding-right">开户行名称：</label>
					  	<div class="col-sm-3">
							<input class="form-control" type="text" value="${vo.bankName }" readonly="readonly">
						</div>
						<label class="col-xs-3 col-sm-2 control-label no-padding-right">贷款账号：</label>
					  	<div class="col-sm-3">
							<input type="text" class="form-control" value="${vo.bankAccountNum }" readonly="readonly">
						</div>
					 </div>
					<div class="form-group">
						<label class="col-xs-3 col-sm-2 control-label no-padding-right">本次放款金额：</label>
					  	<div class="col-sm-3">
							<input type="text" class="form-control" value="${vo.payLoanAmt }" readonly="readonly">
						</div>
					    <label class="col-xs-3 col-sm-2 control-label no-padding-right">手续费及佣金：</label>
					  	<div class="col-sm-3">
							<input class="form-control" type="text" value="${vo.feesComsn }" readonly="readonly">
						</div>
					 </div>
					<div class="form-group">
					    <label class="col-xs-3 col-sm-2 control-label no-padding-right">放款日期：</label>
					  	<div class="col-sm-3">
						<input class="form-control" type="text"
							value="<fmt:formatDate value="${vo.payLoanDate }" pattern="yyyy-MM-dd"/>"
							readonly="readonly">
						</div>
						<label class="col-xs-3 col-sm-2 control-label no-padding-right">实际放款日期：</label>
					  	<div class="col-sm-3">
					  		<input class="form-control" type="text"
							value="<fmt:formatDate value="${vo.actualPayLoanDate  }" pattern="yyyy-MM-dd"/>"
							readonly="readonly">
						</div>
					 </div>
					<div class="form-group">
					    <label class="col-xs-3 col-sm-2 control-label no-padding-right">经办机构：</label>
					  	<div class="col-sm-3">
							<input class="form-control" type="text" value="${vo.handleOrgName }" readonly="readonly">
						</div>
						<label class="col-xs-3 col-sm-2 control-label no-padding-right">经办人：</label>
					  	<div class="col-sm-3">
							<input type="text" class="form-control" value="${vo.handlePersonName }" readonly="readonly">
						</div>
					 </div>
					<div class="form-group">
					    <label class="col-xs-3 col-sm-2 control-label no-padding-right">备注：</label>
					  	<div class="col-sm-8">
							<textarea  class="form-control" rows="3" disabled="disabled" readonly="readonly">${vo.remark }</textarea>
						</div>
					 </div>
					 <div class="form-group">
					    <label class="col-xs-3 col-sm-2 control-label no-padding-right">合同编号：</label>
					  	<div class="col-sm-3">
							<input class="form-control" type="text" value="${vo.contrNum }" readonly="readonly">
						</div>
						<label class="col-xs-3 col-sm-2 control-label no-padding-right">贷款产品：</label>
					  	<div class="col-sm-3">
							<input type="text" class="form-control" value="${vo.productTypeName }" readonly="readonly">
						</div>
					 </div>
					 <div class="form-group">
					    <label class="col-xs-3 col-sm-2 control-label no-padding-right">合同金额：</label>
					  	<div class="col-sm-3">
							<input class="form-control" type="text" value="${vo.contrAmt }" readonly="readonly">
						</div>
						<label class="col-xs-3 col-sm-2 control-label no-padding-right">币种：</label>
					  	<div class="col-sm-3">
							<input type="text" class="form-control" value="${vo.currencyStr }" readonly="readonly">
						</div>
					 </div>
					 <div class="form-group">
					    <label class="col-xs-3 col-sm-2 control-label no-padding-right">贷款利率：</label>
					  	<div class="col-sm-3">
							<input class="form-control" type="text" value="${vo.contrIr*100 }%" readonly="readonly">
						</div>
						<label class="col-xs-3 col-sm-2 control-label no-padding-right">贷款合同期限：</label>
					  	<div class="col-sm-3">
							<input type="text" class="form-control" value="${vo.contrTerm }${vo.contrTermUnitStr }" readonly="readonly">
						</div>
					 </div>
					 <div class="form-group">
					    <label class="col-xs-3 col-sm-2 control-label no-padding-right">还款方式：</label>
					  	<div class="col-sm-3">
							<input class="form-control" type="text" value="${vo.contrRepaymentModeName }" readonly="readonly">
						</div>
						<label class="col-xs-3 col-sm-2 control-label no-padding-right">还款周期月数：</label>
					  	<div class="col-sm-3">
							<input type="text" class="form-control" value="${vo.repayPrincipalMonthes }" readonly="readonly">
						</div>
					 </div>
					 <div class="form-group">
					    <label class="col-xs-3 col-sm-2 control-label no-padding-right">约定还款日：</label>
					  	<div class="col-sm-3">
							<input class="form-control" type="text" value="${vo.promiseRepayDay }" readonly="readonly">
						</div>
					 </div>
				</form>
			</div>
		</div>
</body>
</html>