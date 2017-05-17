<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<%@ taglib uri="http://www.coamc-tech.com/taglibs/datadict" prefix="dd" %>
<%@ taglib uri="http://www.coamc-tech.com/taglibs/auth" prefix="myauth" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="request" />
<c:set var="res" value="${ctx}/static/assets" scope="request" />
<c:set var="my" value="${ctx}/static/my" scope="request" />
<c:set var="title" value="微贷业务系统" />