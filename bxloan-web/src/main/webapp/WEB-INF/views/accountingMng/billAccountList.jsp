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
	<style type="text/css">
	.table th,.table td{
		white-space:nowrap; 
	}
	</style>
</head>
<body class="no-skin">
		<div class="main-container" id="main-container">
			<script type="text/javascript">
				try{ace.settings.check('main-container' , 'fixed')}catch(e){}
			</script>
			<div class="page-content" style="height:700px; overflow-x: auto;">
				<table class="table table-bordered  table-striped table-hover">
					<thead>
						<tr>
							<th>分录序号</th>
							<th>分录摘要</th>
							<th>规则类型</th>
							<th>科目编号/金额类型编码</th>
							<th>科目名称/金额类型名称</th>
							<th>币种编码</th>
							<th>借贷方向</th>
							<th>金额</th>
							<th>核算编码1</th>
							<th>核算名称1</th>
							<th>编码1</th>
							<th>名称1</th>
							<th>核算编码2</th>
							<th>核算名称2</th>
							<th>编码2</th>
							<th>名称2</th>
							<th>核算编码3</th>
							<th>核算名称3</th>
							<th>编码3</th>
							<th>名称3</th>
							<th>现金流量分录号</th>
							<th>现金流量项目编号</th>
							<th>现金流量项目名称</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${voList }" var="vo">
						<tr>
							<td>${vo.tcd.recNo}</td>
							<td>${vo.tcd.recExp}</td>
							<td>${vo.tcd.ruleTyp}</td>
							<td>${vo.tcd.actCd }</td>
							<td>${vo.tcd.actNm }</td>
							<td>${vo.tcd.curyCd }</td>
							<td>${vo.tcd.blcDir==1?'借':'贷' }</td>
							<td>${vo.tcd.recAmt }</td>
							<td>${vo.tcd.astTypCd1 }</td>
							<td>${vo.tcd.astTypNm1 }</td>
							<td>${vo.tcd.astCd1 }</td>
							<td>${vo.tcd.astNm1 }</td>
							<td>${vo.tcd.astTypCd2 }</td>
							<td>${vo.tcd.astTypNm2 }</td>
							<td>${vo.tcd.astCd2 }</td>
							<td>${vo.tcd.astNm2 }</td>
							<td>${vo.tcd.astTypCd3 }</td>
							<td>${vo.tcd.astTypNm3 }</td>
							<td>${vo.tcd.astCd3 }</td>
							<td>${vo.tcd.astNm3 }</td>
							<td>${vo.tcd.cashRec }</td>
							<td>${vo.tcd.cashCdM }</td>
							<td>${vo.tcd.cashNmM }</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
</body>
</html>