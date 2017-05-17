<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../../commons/taglibs.jsp"%>
<c:set var="hideOfBizAdd"
	value="${!(source!=null&&type=='add'&&source=='business') }" />
<c:if test="${hideOfBizAdd}">
	<div class="breadcrumbs" id="breadcrumbs">
		<ul class="breadcrumb">
			<li><i class="ace-icon fa fa-home home-icon"></i> <a
				href="${ctx}">主页</a>
			</li>
			<li>贷前管理</li>
			<li>抵质押管理</li>
		</ul>
	</div>
</c:if>

<div class="page-content">
	<c:if test="${hideOfBizAdd}">
		<div class="page-header">
			<h1>
				抵质押管理 
				<small>
				<i class="ace-icon fa fa-angle-double-right"></i>
				维护
				</small>
			</h1>
		</div>
	</c:if>
	<div class="row">
		<div class="col-md-12">
			<div class="tabbable">
				<ul class="nav nav-tabs padding-12 tab-color-blue">
					<li class="active">
						<a data-toggle="tab" href="#tab-1">
							抵质押管理
						</a>
					</li>
					<li>
						<a data-toggle="tab" href="#tab-2">
							已关联业务历史
						</a>
					</li>
					<!-- <li>
						<a data-toggle="tab" href="#tab-3">
							文档管理
						</a>
					</li> -->
				</ul>

				<div class="tab-content no-border padding-24">

					<div id="tab-1" class="tab-pane fade in active">
						<jsp:include page="collateral.jsp" />
					</div>

					<div id="tab-2" class="tab-pane fade">
						<jsp:include page="historyList.jsp" />
					</div>
					
					<div id="tab-3" class="tab-pane fade">
						<jsp:include page="docList.jsp" />
					</div>
				</div>
			</div>
		</div>
	</div>

</div>

