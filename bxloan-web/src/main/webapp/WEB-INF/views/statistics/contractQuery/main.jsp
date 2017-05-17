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
					<li class="active">借款合同查询</li>
				</ul>
			</div>

	<div class="page-content">
		<div class="page-header">
			<h1>
				统计查询
				<small><i class="ace-icon fa fa-angle-double-right"></i> 借款合同查询</small>
				<span style="float:right;">
					<button class="btn btn-sm btn-purple" type="button" id="contractQuery">
						<i class="ace-icon fa fa-search bigger-110"></i>
							查询
					</button>
					<button class="btn btn-sm" type="reset" id="contractReset">
						<i class="ace-icon fa fa-undo bigger-110"></i>
							重置
					</button>
				</span>
			</h1>
		</div>
		<!-- Search start -->
		<div style="height: 0px;" class="row">
			<div class="col-xs-12">
			<form role="contract_searchform" class="form-horizontal">
					<!-- <h3 class="smaller lighter blue">查询条件</h3> -->
						<div class="form-group">
							<label class="control-label col-sm-1">所属机构：</label>
							<div class="col-sm-3">
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
							<label for="contId" class="control-label col-sm-1">合同编号：</label>
							<div class="col-sm-3">
								<input type="text" id="contId" class="form-control col-sm-5 input-sm" placeholder="合同编号" name="contNum">
							</div>
						</div>
						<div class="form-group">
							<label for="contQx" class="control-label col-sm-1">合同期限：</label>
							<div class="col-xs-12 col-sm-3">
								 <span class="block input-icon input-icon-right">
									<div class="input-group">
									  <input type="text" id="contQx" class="form-control col-sm-5 input-sm" placeholder="合同期限" name="contTerm">
								      <span class="input-group-addon input-sm">单位</span>
								      <select class="form-control col-sm-5 input-sm" name="contTermUnit">
								      		<option value="">全部</option>
								      		<option value="1">年</option>
								      		<option value="2">月</option>
								      		<option value="3">日</option>
								      </select>
								    </div>
								 </span>
							</div>
							<label class="control-label col-sm-1">担保方式：</label>
							<div class="col-sm-3">
								<select id="cdsGuarantMode" name="cdsGuarantMode" class="form-control col-sm-5 input-sm cdsGuarantMode"></select>
							</div>
							<label class="control-label col-sm-1">合同状态：</label>
							<div class="col-sm-3">
								<select id="contStatus" name="contStatus" class="form-control col-sm-5 input-sm contStatus"></select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-1 control-label no-padding-right">是否有保险：</label>
							<div class="col-sm-3">
								<select id="is_insure" name="isInsure" class="form-control col-sm-5 input-sm isInsure"></select>
							</div>
							<label class="col-sm-1 control-label no-padding-right">是否总部协同业务：</label>
							<div class="col-sm-3">
								<select id="is_headcol" name="isHeadcol" class="form-control col-sm-5 input-sm">
									<option value="">全部</option>
									<option value="1">是</option>
									<option value="2">否</option>
								</select>
							</div>
							<label class="control-label col-sm-1">放款日期：</label>
							<div class="col-xs-12 col-sm-3">
								<span class="block input-icon input-icon-right">
									<div class="input-group">
										<input type="text" class="form-control loanStartTime input-sm" id="loanStartTime" name="loanStartTime" placeholder="开始日期" data-date-format="yyyy-mm-dd">
										<span class="input-group-addon">至</span>
										<input type="text" class="form-control loanEndTime input-sm" id="loanEndTime" name="loanEndTime" placeholder="结束日期" data-date-format="yyyy-mm-dd">
									</div>
								</span>
							</div>
						</div>
				</form>
			</div>
		</div>
			
			<!-- searchResult start -->
			<div class="row">
				<div class="col-md-12">
					<h3 class="smaller lighter blue">
						<!-- 查询结果 -->
						<span style="float:right">
							<button class="btn btn-sm btn-success" id="btn-contract-export" style="">
								<i class="ace-icon fa fa-arrow-down"></i> 全部导出
	                   		</button>
						</span>
					</h3>
					<table id="contractTable" class="table table-striped table-hover">
						<thead>
							<tr>
								<th>合同编号</th>
								<th>客户名称</th>
								<th>贷款产品</th>
								<th>贷款机构</th>
								<th>合同状态</th>
								<th>合同金额（元）</th>
								<th>合同期限</th>
								<th>合同余额（元）</th>
								<th>担保方式</th>
								<th><i class="ace-icon fa fa-clock-o bigger-110 hidden-480"></i>放款日期</th>
								<th><i class="ace-icon fa fa-clock-o bigger-110 hidden-480"></i>合同到期日期</th>
								<th>客户经理</th>
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
		seajs.use('${ctx}/static/my/js/statistics/contractQuery/main/main');
	</script>
</body>
</html>
