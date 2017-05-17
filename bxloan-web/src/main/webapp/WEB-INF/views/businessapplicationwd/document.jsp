<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../commons/taglibs.jsp"%>
<link rel="stylesheet" href="static/assets/js/uploadify/uploadify.css" />
<div class="row" id="condSearchDiv">
	<div class="col-xs-12">
		<span> 查找文档： <input id="query_documentName"
			name="query_documentName" placeholder="输入文档名称" /> </span> <span
			style="float:right;">
			<button id="btn-query" class="btn btn-sm btn-purple" type="button">
				<i class="ace-icon fa fa-search"></i> 查询
			</button> <c:if test="${type != 'check' }">
				<button type="button" class="btn btn-sm btn-success" id="addBizWd">
					<i class="ace-icon fa fa-plus"> </i> 新增文档
				</button>
			</c:if> </span>
	</div>
</div>
				<table id="tbWd" class="table table-striped table-hover"
					style="width:100%!important;">
					<thead>
						<tr>
							<th>序号</th>
							<th>文档名称</th>
							<th>内容类型</th>
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
<div>
	<!-- 文档上传的专用隐藏域 -->
	<input type="hidden" id="userNameDI" /> <input type="hidden"
		id="customerNumDI" /> <input type="hidden" id="userNumDI" /> <input
		type="hidden" id="userDateDI" /> <input type="hidden"
		id="documentNumDI" /> <input type="hidden" id="bizIdDI" /> <input
		type="hidden" id="documentTypeDI" /> <input type="hidden"
		id="bizNumDI" /> <input type="hidden" id="userOrgDI" /> <input
		type="hidden" id="createTypeDI" /> <input type="hidden"
		id="fileTypeDI" /> <input type="hidden" id="userIdDI" /> <input
		type="hidden" id="uploadTypeCode" /><input type="hidden"
		name="uploadPathField" id="uploadPathField" value="${uploadPath}" />
</div>
