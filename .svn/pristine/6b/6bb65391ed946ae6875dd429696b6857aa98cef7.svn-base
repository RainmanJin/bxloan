<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../commons/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="zh">
	<head>
		<%-- <base href="${ctx}/"> --%>
		<title>东方邦信融通微贷业务系统</title>
		<meta charset="utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		
		<meta name="description" content="User login page" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
		
		<c:import url="../commons/pre-include.jsp" charEncoding="UTF-8" />
		
		<!-- page specific plugin styles START -->
		<!-- page specific plugin styles END -->
		
		<!-- inline styles related to this page -->
		<link rel="stylesheet" href="${ctx}/static/my/css/login.css" />
		<script>var $$ctx = "${ctx}/";</script>
	</head>
	<body class="login-layout light-login">
		<div class="main-container">
			<div class="main-content">
				<div class="row">
					<div class="col-sm-10 col-sm-offset-1">
						<div class="login-container">
							<div class="center">
								<h1>
								<img src="${res}/images/logo-dfbx.png"/>
									<span class="grey" id="id-text2">微贷业务系统</span>
								</h1>
							</div>

							<div class="space-6"></div>
							
							<div id="error-content" class="alert alert-danger hide">
								<button type="button" class="close" data-dismiss="alert">
									<i class="ace-icon fa fa-times"></i>
								</button>
								<strong><i class="ace-icon fa fa-times"></i> <span id="error"></span><br>
							</div>
							
							<div class="position-relative">
								<div id="login-box" class="login-box visible widget-box no-border">
									<div class="widget-body">
										<div class="widget-main">
											<h4 class="header blue lighter bigger">
												<i class="ace-icon fa fa-coffee green"></i>
												请选择您的账户信息
											</h4>

											<div class="space-6"></div>

											<form id="loginForm" name="loginForm" action="login" method="post" >
												<fieldset>
												 <div class="form-group">
													<label class="block clearfix" for="logname">
														<span class="block input-icon input-icon-right">
															<select class="form-control" id="logname" name="logname" >
															</select>
														</span>
													</label>
												</div>
												<div class="form-group">
													<label class="block clearfix" for="orgid">
														<span class="block input-icon input-icon-right">
															<select class="form-control" id="orgid" name="orgid" >
															</select>
														</span>
													</label>
												</div>
													<div class="space"></div>

													<div class="clearfix">
														<div class="btn-group pull-right">
															<button id="btn-login"  class="btn btn-sm btn-primary" type="submit">
																<i class="ace-icon fa fa-arrow-right"></i>
																<span class="bigger-110">进入</span>
															</button>
														</div>
													</div>													
												</fieldset>
											</form>
											
										</div><!-- /.widget-main -->
									</div><!-- /.widget-body -->
								</div><!-- /.login-box -->
								<div class="center">
									<h5 class="blue" id="id-company-text">Copyright &copy; 2015 东方邦信融通控股股份有限公司</h5>
								</div>
							</div>
						</div><!-- /.login-container -->
					</div><!-- /.col -->
				</div><!-- /.row -->
			</div><!-- /.main-content -->
		</div><!-- /.main-container -->
		
		<c:import url="../commons/post-include.jsp" charEncoding="UTF-8" />
		
		<!-- page specific plugin scripts START -->
		<script src="${ctx}/static/assets/js/md5.js"></script>
		<!-- page specific plugin scripts END -->
	
		<!-- inline scripts related to this page -->
		<script>
			seajs.use('${ctx}/static/my/js/login/main');
		</script>
	</body>