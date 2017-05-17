<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../commons/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SSO失败</title>
<script src="${res}/js/jquery1x.min.js"></script>
</head>
<body>
	<c:if test="${errCode == '1'}">
		<script type="text/javascript">
			$.get("http://localhost:8080/ac/logout", function() {
				window.location.href = "http://localhost:8080/portal";
			});
		</script>
	</c:if>
</body>
</html>