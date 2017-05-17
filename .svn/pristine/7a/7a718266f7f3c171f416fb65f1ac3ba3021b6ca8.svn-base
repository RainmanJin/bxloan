<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../../commons/taglibs.jsp"%>
<!-- 条件搜索 -->
<div class="row" id="condSearchDiv">
	<div class="col-xs-12">
		<span> 查找文档： <input id="query_documentName"
			name="query_documentName" placeholder="输入文档名称" /> </span> <span
			style="float:right;">
			<button id="btn-query" class="btn btn-sm btn-purple" type="button">
				<i class="ace-icon fa fa-search"></i> 查询
			</button> <c:if test="${judgeType != 'check' }">
				<button type="button" class="btn btn-sm btn-success" id="addBizWd">
					<i class="ace-icon fa fa-plus"> </i> 新增文档
				</button>
			</c:if> </span>
	</div>
</div>
<!-- 相关文档table -->
<table id="tbWd" class="table table-striped table-hover"
	style="width:100%!important;">
	<thead>
		<tr>
			<th>序号</th>
			<th>文档名称</th>
			<th>文档类型</th>
			<th>客户名称</th>
			<th>创建人</th>
			<th>创建日期</th>
			<th>关联方式</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
	</tbody>
</table>
<!-- 相关文档table -->