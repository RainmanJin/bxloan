<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../../../commons/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh">
<head>
	<base href="${ctx}/">
	<title>${title }</title>
	<meta charset="utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	
	<meta name="description" content="Dashboard page" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
	<c:import url="../../../commons/pre-include.jsp" charEncoding="UTF-8"/>
	<script>var $$ctx = "${ctx}/";</script>
	<style type="text/css">
	.chosen-container-multi .chosen-choices li.search-choice .search-choice-close{
		background-image: none;
	}
	</style>
</head>
<body class="no-skin">
	<c:import url="../../../commons/navbar.jsp" charEncoding="UTF-8" />
		<div class="main-container" id="main-container">
		<script type="text/javascript">
			try{ace.settings.check('main-container' , 'fixed')}catch(e){}
		</script>
	
		<c:import url="../../../commons/sidebar.jsp" charEncoding="UTF-8" />
		
		<div class="main-content">
			<div class="breadcrumbs" id="breadcrumbs">
				<script type="text/javascript">
					try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
				</script>

				<ul class="breadcrumb">
					<li>
						<a href="${ctx}">主页</a>
					</li>
					<li class="active">统计查询</li>
					<li class="active">客户查询</li>
				</ul><!-- /.breadcrumb -->
			</div>

	<div class="page-content">
		<div class="page-header">
			<h1>
				统计查询
				<small><i class="ace-icon fa fa-angle-double-right"></i> 客户查询</small>
				<span style="float:right;">
					<button class="btn btn-sm btn-purple" type="button" id="customerQuery">
						<i class="ace-icon fa fa-search bigger-110"></i>
							查询
						</button>
			
						<button class="btn btn-sm" type="reset" id="customerReset">
							<i class="ace-icon fa fa-undo bigger-110"></i>
							重置
					</button>
				</span>
			</h1>
		</div>
		<!-- Search start -->
		<div style="height: 0px;" class="row">
			<div class="col-xs-12">
			<form role="customer_searchform" class="form-horizontal">
					<!-- <h3 class="smaller lighter blue">查询条件</h3> -->
						<div class="form-group">
							<label class="control-label col-sm-1">所属机构：</label>
							<div class="col-sm-3">
								<%-- <c:if test="${orgLevel =='1'}">
									<select id="orgId" name="orgId" class="form-control col-sm-5 input-sm">
										<option value="">全部</option>
										<c:forEach items="${orgs}" var="org">
											<option value="${org.id }">${org.name}</option>
										</c:forEach>
									</select>
								</c:if>
								<c:if test="${orgLevel =='2'}">
									<select id="orgId" name="orgId" class="form-control col-sm-5 input-sm" disabled="disabled">
											<option value="${user.orgId }">${orgName}</option>
									</select>
								</c:if> --%>
								<c:choose>
									<c:when test="${orgLevel == 1}">
										<select id="orgId" name="orgId" class="form-control col-sm-5 input-sm">
											<option value="">全部</option>
											<c:forEach items="${orgs}" var="org">
												<option value="${org.id }">${org.name}</option>
											</c:forEach>
										</select>
									</c:when>
									<c:when test="${orgLevel == 2}">
										<select id="orgId" name="orgId" class="form-control col-sm-5 input-sm" disabled="disabled">
											<c:forEach items="${orgs}" var="org">
												<option value="${org.id }">${org.name}</option>
											</c:forEach>
										</select>
									</c:when>
									<c:otherwise>
										<select id="orgId" name="orgId" class="form-control col-sm-5 input-sm" disabled="disabled">
											<option value="1">${orgName}</option>
										</select>
									</c:otherwise>
								</c:choose>
							</div>
							<label class="control-label col-sm-1" for="customerName">客户名称：</label>
							<div class="col-sm-3">
								<input id="customerName" type="text" class="form-control col-sm-5 input-sm" placeholder="客户名称" name="customerName">
							</div>
							<label class="control-label col-sm-1">客户类型：</label>
							<div class="col-sm-3">
								<select id="customerType" name="customerType" class="form-control col-sm-5 input-sm customerType"></select>
							</div>
						</div>
						<div class="form-group">
							<label for="customerCd" class="control-label col-sm-1">客户编号：</label>
							<div class="col-sm-3">
								<input type="text" id="customerCd" class="form-control col-sm-5 input-sm" placeholder="客户编号" name="customerCd">
							</div>
							<label for="custMng" class="control-label col-sm-1">客户经理：</label>
							<div class="col-sm-3">
								<input type="text" id="custMng" class="form-control col-sm-5 input-sm" placeholder="客户经理" name="custMng">
							</div>
							<label for="employmentType" class="control-label col-sm-1">雇佣类型：</label>
							<div class="col-sm-3">
								<select id="employmentType" name="employmentType" class="form-control col-sm-5 input-sm employmentType"></select>
							</div>
						</div>
						<div class="form-group">
							<label for="contQx" class="control-label col-sm-1">证件类型：</label>
							<div class="col-xs-12 col-sm-3">
								<select id="certificateType" name="certificateType" class="form-control col-sm-5 input-sm certificateType"></select>
							</div>
							<label class="control-label col-sm-1">证件号码：</label>
							<div class="col-sm-3">
								<input type="text" id="certificateCode" name="certificateCode" class="form-control col-sm-5 input-sm" placeholder="证件号码" />
							</div>
							<label class="control-label col-sm-1">客户状态：</label>
							<div class="col-sm-3">
								<select id="customerStatus" name="customerStatus" class="form-control col-sm-5 input-sm customerStatus"></select>
							</div>
						</div>
				</form>
			</div>
		</div>
			
			<!-- searchResult start -->
			<div class="row">
				<div class="col-xs-12">
					<h3 class="smaller lighter blue">
						<!-- 查询结果 -->
						<span style="float:right">
							<button class="btn btn-sm btn-success" id="btn-customer-export" style="">
								<i class="ace-icon fa fa-arrow-down"></i> 全部导出
	                   		</button>
						</span>
					</h3>
					<table id="customerTable" class="table table-striped table-hover">
						<thead>
							<tr>
								<th>选择</th>
								<th>客户编号</th>
								<th>客户名称</th>
								<th>客户类型</th>
								<th>证件类型</th>
								<th>证件号码</th>
								<th>状态</th>
								<th>客户经理</th>
								<th>创建时间</th>
								<th>借款人/担保人 </th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
		<!-- searchResult end -->
		</div><!-- /.page-content -->
	</div><!-- /.main-content -->
	<c:import url="../../../commons/footer.jsp" charEncoding="UTF-8" />
		
	</div><!-- /.main-container -->
	<c:import url="../../../commons/post-include.jsp" charEncoding="UTF-8"/>
	<script>
		seajs.use('${ctx}/static/my/js/statistics/customerQuery/main/main');
	</script>
</body>
</html>
