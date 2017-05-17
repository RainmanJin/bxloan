<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../commons/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<base href="${ctx}/">
<title>${title }</title>
<script>
	var $$ctx = "${ctx}/";
</script>
<c:import url="../../commons/pre-include.jsp" charEncoding="UTF-8" />
<link rel="stylesheet" href="${ctx}/static/assets/js/uploadify/uploadify.css"
/>
</head> 

<body class="no-skin">

	<input type="hidden" id="type" value="${type }" />
	<input type="hidden" id="allDocType" value="${allDocType}" />
	<input type="hidden" id="uploadPath" value="${uploadPath}" />
	<c:set var="hideOfBizAdd" value="${!(source!=null&&type=='add'&&source=='business') }"/>
	<input type="hidden" id="jumpOfSave" value="${hideOfBizAdd }">
	<c:if test="${hideOfBizAdd}">
		<c:import url="../../commons/navbar.jsp" charEncoding="UTF-8" />
	</c:if>
	<div class="main-container" id="main-container">
		<c:if test="${hideOfBizAdd}">
		<c:import url="../../commons/sidebar.jsp" charEncoding="UTF-8" />
		</c:if>
		<c:choose>
			<c:when test="${hideOfBizAdd}">
				<div class="main-content">
					<jsp:include page="input/inputContent.jsp"></jsp:include>
				</div>
			</c:when>
			<c:otherwise>
				<jsp:include page="input/inputContent.jsp"></jsp:include>
			</c:otherwise>
		</c:choose>
		<c:if test="${hideOfBizAdd}">
		<c:import url="../../commons/footer.jsp" charEncoding="UTF-8" />
		</c:if>
	</div>
	<c:import url="../../commons/post-include.jsp" charEncoding="UTF-8" />
	<script type="text/javascript" src="${res}/js/uploadify/jquery.uploadify.min.js">
	</script>
	<script>
		seajs.use('${ctx}/static/my/js/collateral/input/main');
	</script>
</body>
</html>