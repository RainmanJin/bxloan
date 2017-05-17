<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="taglibs.jsp" %>
<div id="sidebar" class="sidebar responsive">
<script type="text/javascript">
	try{ace.settings.check('sidebar' , 'fixed')}catch(e){}
</script>
<style>
.no-padding-right {text-align:right;margin-bottom: 15px;}
</style>
<div class="sidebar-shortcuts" id="sidebar-shortcuts">
	<div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
	
		<button class="btn btn-success" type="button" title="首页" id="btn-dashboard">
			<i class="ace-icon fa fa-signal"></i>
		</button>

		<button id="side_bar_userinfo" title="用户信息" class="btn btn-warning" type="button">
			<i class="ace-icon fa fa-cogs"></i>
		</button>

		<button id="side_bar_calculatorLoan" title="贷款试算" class="btn btn-info" type="button">
			<i class="ace-icon fa fa-keyboard-o"></i>
		</button>

		<button class="btn btn-danger" type="button" role="helpInfo" title='帮助信息'>
			<i class="glyphicon glyphicon-info-sign"></i>
		</button>
	</div>

	<div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
		<span class="btn btn-success"></span>

		<span class="btn btn-info"></span>

		<span class="btn btn-warning"></span>

		<span class="btn btn-danger"></span>
	</div>
</div><!-- /.sidebar-shortcuts -->

<ul class="nav nav-list" id="menuBar" >
	<li class="">
		<a href="${ctx}/dashboard">
			<i class="menu-icon fa fa-tachometer"></i>
			<span class="menu-text"> 工作面板 </span>
		</a>
		<b class="arrow"></b>
	</li>
	<!-- for test -->
	
</ul><!-- /.nav-list -->

<div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
	<i class="ace-icon fa fa-angle-double-left" data-icon1="ace-icon fa fa-angle-double-left" data-icon2="ace-icon fa fa-angle-double-right"></i>
</div>



<!-- 帮助信息 -->
<div id="side_bar_help_info_modal" class="modal fade text-center" tabindex="-1"  role="basic" aria-hidden="true" style="z-index:1061;" data-backdrop="static" >
	<div class="modal-dialog modal-full" style="display: inline-block; width: 1100px; ">
		<div class="modal-content">
			<div class="modal-header">
				<h4></h4>
			</div>
			<div class="modal-body">
				<iframe name="side_bar_help_info_mainFrame" 
				id="side_bar_help_info_mainFrame"  
				width="100%"  frameborder="0"
				marginheight="0" marginwidth="0" 
				scrolling-x="no" scrolling-y="auto" 
				style="max-height:500px; overflow-x:scroll; overflow-y:hidden; ">
				</iframe>
			</div>
			<div style="margin-bottom:10px;">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
			</div>
		</div>
	</div>
</div>
<!-- 帮助信息 END -->
<script type="text/javascript">
	try{ace.settings.check('sidebar' , 'collapsed')}catch(e){}
</script>

</div>
<!-- 贷款试算 -->
<jsp:include page="loancalcus.jsp" ></jsp:include>
<!-- 贷款试算 -->
<jsp:include page="userinfomodal.jsp"></jsp:include>