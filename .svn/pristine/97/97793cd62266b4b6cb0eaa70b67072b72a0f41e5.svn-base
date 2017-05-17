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
				<h1> 统计查询 <small> <i class="ace-icon fa fa-angle-double-right"></i> 客户查询 </small> 
				<div class="pull-right">
               		<button class="btn btn-sm btn-pre" data-last="Finish" type="button" onclick="javascript:history.go(-1);">
               			<i class="ace-icon fa fa-chevron-left"></i>
               			返回
              		 </button>
              		 <span style="float:right;">&nbsp;&nbsp;&nbsp;</span>
				</div>
				</h1>
				
			</div>
			<div class="row">
				<div class="col-xs-12">
					<div id="main_tabbable" class="tabbable">
						<ul class="nav nav-tabs padding-12 tab-color-blue">
						<!-- <ul class="nav nav-pills nav-stacked  padding-12 tab-color-blue"> -->
							<li class="active"><a data-toggle="tab" href="#_tab-1" >客户信息</a></li>
							<li><a data-toggle="tab" href="#_tab-2" >合同信息</a></li>
							<li><a data-toggle="tab" href="#_tab-3" ><i class="bigger-120"> </i>历史业务申请信息</a></li>
						</ul>
						<div class="tab-content no-border padding-24">
							<!--  客户id-->
						<input id="partyId" type="hidden" value="${partyId}">
						<input id="uploadPathField" name="uploadPathField" type="hidden" value="${uploadPath}">
							<!-- 客户信息 -->
							<div id="_tab-1" class="tab-pane fade in active">
								<jsp:include page="details/individual_detail.jsp" />
							</div>
							<!-- 合同台账 -->
							<div id="_tab-2" class="tab-pane fade">
								<jsp:include page="details/contract_list.jsp" />
							</div>
							<!-- 业务查询 -->
							<div id="_tab-3" class="tab-pane fade">
								<jsp:include page="details/businessApply_list.jsp" />
							</div>
						</div>
					</div>
				</div>
			</div>
			<%-- <div class="row">
				<div class="col-xs-12">
					<!--  客户id-->
					<input id="partyId" type="hidden" value="${partyId}">
					<input id="uploadPathField" name="uploadPathField" type="hidden" value="${uploadPath}">
					<!-- PAGE CONTENT BEGINS -->
					<div id="main_tabbable" class="tabbable">
						<div class="col-md-2" style="white-space:nowrap;z-index: 1049;">
							<ul class="nav nav-pills nav-stacked  padding-12 tab-color-blue">
								<li class="active">
									<a data-toggle="tab" href="#faq-tab-1" >
										<i class="bigger-120"> </i> 客户信息
									</a>
								</li>
								<li>
									<a data-toggle="tab" href="#faq-tab-2" >
										<i class="bigger-120"> </i> 合同台账
									</a>
								</li>
								
								<li>
									<a data-toggle="tab" href="#faq-tab-3" >
										<i class="bigger-120"> </i>	历史业务申请信息
									</a>
								</li>
							</ul>
						</div>
						<div class="col-md-10">
							<!-- 客户信息 -->
							<div id="faq-tab-1" class="tab-pane fade in active">
								<jsp:include page="details/individual_detail.jsp"></jsp:include>
							</div>
							<!-- 合同台账 -->
							<div id="faq-tab-2" class="tab-pane fade">
								<jsp:include page="details/contract_list.jsp"></jsp:include>
							</div>
							<!-- 历史业务申请信息-->
							<div id="faq-tab-3" class="tab-pane fade">
								<jsp:include page="details/businessApply_list.jsp"></jsp:include>
							</div>
						</div>
					</div>
					</div>
				</div> --%>
			</div><!-- /.page-content -->
		</div><!-- /.main-content -->
			
		<c:import url="../../../commons/footer.jsp" charEncoding="UTF-8" />
		
	</div><!-- /.main-container -->
	<!-- 浮窗链接的模式窗口 -->
	<div id="modalOfContact" class="modal fade text-center" tabindex="-1" role="basic" aria-hidden="true" style="z-index:1061;" data-backdrop="static">
		<div class="modal-dialog modal-lg" style="width: 1200px">
			<div class="modal-content ">
				<div class="modal-body ">
					<iframe src="" name="mainFrameOfContact" id="mainFrameOfContact" width="100%"
					height="700px" frameborder="0" marginheight="0" marginwidth="0" scrolling-x="no"
					scrolling-y="auto" style=" overflow-x:scroll; overflow-y:hidden; ">
					</iframe>
				</div>
				<div style="margin-bottom:10px;">
					<button type="button" class="btn btn-default" data-dismiss="modal">
						关闭
					</button>
				</div>
			</div>
		</div>
	</div>
	<c:import url="../../../commons/post-include.jsp" charEncoding="UTF-8"/>
	<script>
		seajs.use('${ctx}/static/my/js/statistics/customerQuery/detail/main');
	</script>
</body>
</html>