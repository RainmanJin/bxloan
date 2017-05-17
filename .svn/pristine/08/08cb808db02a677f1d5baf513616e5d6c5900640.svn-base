<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../commons/taglibs.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
<style type="text/css">
body,html,#allmap {
	width: 100%;
	height: 100%;
	overflow: hidden;
	margin: 0;
	font-family: "微软雅黑";
}
</style>
<c:import url="../../commons/pre-include.jsp" charEncoding="UTF-8" />
<c:import url="../../commons/post-include.jsp" charEncoding="UTF-8" />
<script type="text/javascript">
	function initialize() {
		var map = new BMap.Map("map");
		var point = new BMap.Point($("#longitude").val(), $("#latitude").val());
		map.centerAndZoom(point, 15);
		map.enableScrollWheelZoom();
		var marker = new BMap.Marker(point);
		map.addOverlay(marker);
	}
	function loadScript() {
		var script = document.createElement("script");
		script.src = "http://api.map.baidu.com/api?v=2.0&ak=KTMz0OaG8YDpb8SD1McSoUhd&callback=initialize";
		document.body.appendChild(script);
	}

	$(function() {
		window.onload = loadScript;
	});
</script>
</head>
<body>
	<div id="map" style="width:100%;height:100%"></div>
	<input type="hidden" id="longitude">
	<input type="hidden" id="latitude">
</body>
</html>