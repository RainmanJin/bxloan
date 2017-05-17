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
				</ul><!-- /.breadcrumb -->
			</div>

			<div class="page-content">
				<div class="page-header">
				<h1> 统计查询 <small> <i class="ace-icon fa fa-angle-double-right"></i> 借款合同查询 </small>
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
					<!--  业务申请id-->
					<input id="projectId" type="hidden" value="${projectId}">
					<!--  合同id-->
					<input id="contractId" type="hidden" value="${contractId}">
					<!--  客户id-->
					<input id="partyId" type="hidden" value="${partyId}">
					<input id="uploadPathField" name="uploadPathField" type="hidden" value="${uploadPath}">
					<!-- PAGE CONTENT BEGINS -->
					<div id="main_tabbable" class="tabbable">
						<!-- <div class="pull-right">
							<button style="z-index: 1000" type="button" role="btn_customer" class="btn btn-sm btn-yellow">客户信息</button>
						</div> -->
						<ul class="nav nav-tabs padding-12 tab-color-blue">
							<li class="active">
								<a data-toggle="tab" href="#faq-tab-1" >
									<i class="bigger-120"> </i> 主合同信息
								</a>
							</li>
							
							<li>
								<a data-toggle="tab" href="#faq-tab-2" >
									<i class="bigger-120"> </i> 从合同信息
								</a>
							</li>
							
							<li>
								<a data-toggle="tab" href="#faq-tab-3" >
									<i class="bigger-120"> </i>共同借款人
								</a>
							</li>
							
							<li>
								<a data-toggle="tab" href="#faq-tab-4" >
									<i class="bigger-120"> </i> 放款情况
								</a>
							</li>
							<li>
								<a data-toggle="tab" href="#faq-tab-5" >
									<i class="bigger-120"> </i> 还款情况
								</a>
							</li>
							<li>
								<a data-toggle="tab" href="#faq-tab-6" >
									<i class="bigger-120"> </i> 相关文档
								</a>
							</li>
						</ul>
						<div class="tab-content no-border padding-24">
							<!-- 主合同信息 -->
							<div id="faq-tab-1" class="tab-pane fade in active">
								<jsp:include page="details/main_contract.jsp"></jsp:include>
							</div>
							<!-- 从合同信息 -->
							<div id="faq-tab-2" class="tab-pane fade">
								<jsp:include page="details/sub_contract_list.jsp"></jsp:include>
							</div>
							<!-- 共同借款人 -->
							<div id="faq-tab-3" class="tab-pane fade">
								<jsp:include page="details/commonBorrower.jsp"></jsp:include>
							</div>
							<!--  放款情况 -->
							<div id="faq-tab-4" class="tab-pane fade">
								<jsp:include page="details/payloan_list.jsp"></jsp:include>
							</div>
							<!-- 还款情况 -->
							<div id="faq-tab-5" class="tab-pane fade">
								<jsp:include page="details/repayPlan.jsp"></jsp:include>
							</div>
							<!-- 相关文档 -->
							<div id="faq-tab-6" class="tab-pane fade">
								<jsp:include page="details/document.jsp"></jsp:include>
							</div>
							
							
						</div>
					</div>
				</div>
				</div>
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
		seajs.use('${ctx}/static/my/js/statistics/contractQuery/detail/main');
	</script>
</body>
</html>