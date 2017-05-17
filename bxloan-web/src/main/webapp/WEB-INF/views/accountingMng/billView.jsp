<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../../commons/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="zh">
<head>
	<base href="${ctx}/">
	<title>邦信微贷-账务冲正-单据详细</title>
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
				<form class="form-horizontal" action="">
					<div class="form-group">
					    <label class="col-xs-3 col-sm-2 control-label no-padding-right">机构编号：</label>
					  	<div class="col-sm-3">
							<input class="form-control" type="text" value="${vo.tc.orgCd }" readonly="readonly">
						</div>
						<label class="col-xs-3 col-sm-2 control-label no-padding-right">单据编号：</label>
					  	<div class="col-sm-3">
							<input type="text" class="form-control" value="${vo.tc.billCd }" readonly="readonly">
						</div>
					 </div>
					<div class="form-group">
					    <label class="col-xs-3 col-sm-2 control-label no-padding-right">单据描述：</label>
					  	<div class="col-sm-3">
							<input class="form-control" type="text" value="${vo.tc.billDesc }" readonly="readonly">
						</div>
					 </div>
					<div class="form-group">
					    <label class="col-xs-3 col-sm-2 control-label no-padding-right">客户编号：</label>
					  	<div class="col-sm-3">
							<input class="form-control" type="text" value="${vo.tc.custCd }" readonly="readonly">
						</div>
						<label class="col-xs-3 col-sm-2 control-label no-padding-right">合同编号：</label>
					  	<div class="col-sm-3">
							<input type="text" class="form-control" value="${vo.tc.contCd }" readonly="readonly">
						</div>
					 </div>
					<div class="form-group">
					    <label class="col-xs-3 col-sm-2 control-label no-padding-right">单据状态：</label>
					  	<div class="col-sm-3">
							<input class="form-control" type="text" value="${vo.tc.orgCd }" readonly="readonly">
						</div>
						<label class="col-xs-3 col-sm-2 control-label no-padding-right">业务类型：</label>
					  	<div class="col-sm-3">
							<input type="text" class="form-control" value="${vo.tc.busiTypNm }" readonly="readonly">
						</div>
					 </div>
					<div class="form-group">
					    <label class="col-xs-3 col-sm-2 control-label no-padding-right">系统来源：</label>
					  	<div class="col-sm-3">
							<input class="form-control" type="text" value="${vo.tc.sysNm }" readonly="readonly">
						</div>
					 </div>
					<div class="form-group">
					    <label class="col-xs-3 col-sm-2 control-label no-padding-right">是否财务确认业务日期：</label>
					  	<div class="col-sm-3">
							<input class="form-control" type="text" value="${vo.tc.ifFeed==1?'是':'否' }" readonly="readonly">
						</div>
						<label class="col-xs-3 col-sm-2 control-label no-padding-right">财务是否可退单：</label>
					  	<div class="col-sm-3">
							<input type="text" class="form-control" value="${vo.tc.canRef==1?'是':'否' }" readonly="readonly">
						</div>
					 </div>
					<div class="form-group">
					    <label class="col-xs-3 col-sm-2 control-label no-padding-right">项目经理：</label>
					  	<div class="col-sm-3">
							<input class="form-control" type="text" value="${vo.tc.pmPrn }" readonly="readonly">
						</div>
					 </div>
					<div class="form-group">
						<label class="col-xs-3 col-sm-2 control-label no-padding-right">发送人：</label>
					  	<div class="col-sm-3">
							<input type="text" class="form-control" value="${vo.tc.sndPrn }" readonly="readonly">
						</div>
					    <label class="col-xs-3 col-sm-2 control-label no-padding-right">发送日期：</label>
					  	<div class="col-sm-3">
							<input class="form-control" type="text" value='<fmt:formatDate value="${vo.tc.sndDt }" pattern="yyyy-MM-dd"/>' readonly="readonly">
						</div>
					 </div>
					<div class="form-group">
						<label class="col-xs-3 col-sm-2 control-label no-padding-right">入账人：</label>
					  	<div class="col-sm-3">
							<input type="text" class="form-control" value="${vo.tc.accPrn }" readonly="readonly">
						</div>
					    <label class="col-xs-3 col-sm-2 control-label no-padding-right">入账日期：</label>
					  	<div class="col-sm-3">
							<input class="form-control" type="text" value="<fmt:formatDate value='${vo.tc.accDt }' pattern='yyyy-MM-dd'/>" readonly="readonly">
						</div>
					 </div>
					<div class="form-group">
						<label class="col-xs-3 col-sm-2 control-label no-padding-right">退单人：</label>
					  	<div class="col-sm-3">
							<input type="text" class="form-control" value="${vo.tc.rejPrn }" readonly="readonly">
						</div>
					    <label class="col-xs-3 col-sm-2 control-label no-padding-right">退单日期：</label>
					  	<div class="col-sm-3">
							<input class="form-control" type="text" value="<fmt:formatDate value='${vo.tc.rejDt }' pattern='yyyy-MM-dd'/>" readonly="readonly">
						</div>
					 </div>
					<div class="form-group">
						<label class="col-xs-3 col-sm-2 control-label no-padding-right">冲销发送人：</label>
					  	<div class="col-sm-3">
							<input type="text" class="form-control" value="${vo.tc.sndPrnX }" readonly="readonly">
						</div>
					    <label class="col-xs-3 col-sm-2 control-label no-padding-right">冲销发送日期：</label>
					  	<div class="col-sm-3">
							<input class="form-control" type="text" value="<fmt:formatDate value='${vo.tc.sndDtX }' pattern='yyyy-MM-dd'/>" readonly="readonly">
						</div>
					 </div>
					<div class="form-group">
						<label class="col-xs-3 col-sm-2 control-label no-padding-right">冲销入账人：</label>
					  	<div class="col-sm-3">
							<input type="text" class="form-control" value="${vo.tc.accPrnX }" readonly="readonly">
						</div>
					    <label class="col-xs-3 col-sm-2 control-label no-padding-right">冲销入账日期：</label>
					  	<div class="col-sm-3">
							<input class="form-control" type="text" value="<fmt:formatDate value='${vo.tc.accDtX }' pattern='yyyy-MM-dd'/>" readonly="readonly">
						</div>
					 </div>
					<div class="form-group">
						<label class="col-xs-3 col-sm-2 control-label no-padding-right">冲销退单人：</label>
					  	<div class="col-sm-3">
							<input type="text" class="form-control" value="${vo.tc.rejPrnX }" readonly="readonly">
						</div>
					    <label class="col-xs-3 col-sm-2 control-label no-padding-right">冲销退单日期：</label>
					  	<div class="col-sm-3">
							<input class="form-control" type="text" value="<fmt:formatDate value='${vo.tc.rejDtX }' pattern='yyyy-MM-dd'/>" readonly="readonly">
						</div>
					 </div>
				</form>
			</div>
		</div>
</body>
</html>