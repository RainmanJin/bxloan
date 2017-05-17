<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../../commons/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="zh">

<head>
<base href="${ctx}/">
<title>${title }</title>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta name="description" content="Dashboard page" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<c:import url="../../commons/pre-include.jsp" charEncoding="UTF-8" />
<!-- page specific plugin styles START -->
<script>
	var $$ctx = "${ctx}/";
</script>
<style type="text/css">
.nav-tabs {border-bottom:0px!important}
.form-control[disabled],.form-control[readonly] {background-color: white!important}
</style>
</head>

<body class="no-skin">
	<c:import url="../../commons/navbar.jsp" charEncoding="UTF-8" />
	<div class="main-container" id="main-container">
		<script type="text/javascript">
			try {
				ace.settings.check('main-container', 'fixed')
			} catch (e) {
			}
		</script>
		<c:import url="../../commons/sidebar.jsp" charEncoding="UTF-8" />
		<div class="main-content">
			<div class="breadcrumbs" id="breadcrumbs">
				<script type="text/javascript">
					try {
						ace.settings.check('breadcrumbs', 'fixed')
					} catch (e) {
					}
				</script>
				<ul class="breadcrumb">
					<li><i class="ace-icon fa fa-home home-icon"> </i> <a
						href="${ctx}"> 员工管理 </a></li>
					<li class="active">员工详情</li>
				</ul>
				<!-- /.breadcrumb -->
				<!-- <div class="nav-search" id="nav-search">
              <form class="form-search">
              <span class="input-icon">
              <input type="text" placeholder="Search ..." class="nav-search-input" id="nav-search-input" autocomplete="off" />
              <i class="ace-icon fa fa-search nav-search-icon"></i>
              </span>
              </form>
              </div> 
              -->
			</div>
			<div class="page-content">
				<!-- 隐藏域 -->
				<div>
					<input type="hidden" id="curUserId" value="${curUserId}" /> 
					<input type="hidden" id="error" value="${error }"> 
					<input type="hidden" id="orgIdField" value="${orgIdValue}" />
 				</div>
				<%-- <c:import url="../../commons/settings.jsp" charEncoding="UTF-8"
              />
              --%>
				<div class="page-header">
					<h1>
						员工管理 <small> <i class="ace-icon fa fa-angle-double-right">
						</i> 员工详情 </small>
					</h1>
				</div>
				<!-- /.page-header -->

				<div>
				<!-- 隐藏域 -->
				<input type="hidden" id="deptNameField" />
				<!-- END 隐藏域 -->
					<div id="user-profile-2" class="user-profile">
						<div class="tabbable">
									<ul class="nav nav-tabs padding-18" style="height: 50px;">
											<li class="active">
												<a data-toggle="tab" href="#home">
													<i class="green ace-icon fa fa-user bigger-120"></i>
													人员信息
												</a>
											</li>

											<li class="">
												<a data-toggle="tab" href="#role">
													<i class="orange ace-icon fa fa-rss bigger-120"></i>
													角色信息
												</a>
											</li>

											<!-- <li class="">
												<a data-toggle="tab" href="#permission">
													<i class="blue ace-icon fa fa-users bigger-120"></i>
													查询权限级别配置
												</a>
											</li> -->

										</ul>

							<div class="tab-content no-border padding-24">
								<div id="home" class="tab-pane active">
								  <form id="form-userinfo">
									<div class="row">
										<div class="col-xs-12 col-sm-3 center">
											<span class="profile-picture"> 
											<img class="editable img-responsive" id="avatar2"
												src="${ctx }/static/assets/avatars/profile-pic.jpg"> </span>

											<div class="space space-4"></div>
											上次登录时间：<span id="lastlogtime"></span>
											<a  class="btn btn-sm btn-block btn-pre" href="javascript:history.go(-1)"> 
											<i class="ace-icon fa fa-chevron-left"></i>
											<span  class="bigger-110">返回前页</span> </a>
											<!--  <a  class="btn btn-sm btn-block btn-primary" id="btn-accept"> 
											<i class="ace-icon fa fa-envelope-o bigger-110"></i> 
											<span  class="bigger-110">确认保存</span> </a> -->
										</div>
										<!-- /.col -->

										<div class="col-xs-12 col-sm-9">
											<h4 class="blue">
												<span class="middle">用户编号:<span name="usernum"></span></span> 
												<span class="label label-purple arrowed-in-right"> <i
													class="ace-icon fa fa-circle smaller-80 align-middle"></i>
													在线 </span>
											</h4>
											
											<input type="hidden" name="beforeLogName"/>
											<input type="hidden" name="beforeCertificateNum"/>
											
											<div class="profile-user-info">
												<div class="profile-info-row">
													<div class="profile-info-name">登录名称</div>
													
													<div class="profile-info-value">
														<input type="text" name="logname" id="logname" class="form-control" />
													</div>
												</div>

												<div class="profile-info-row">
													<div class="profile-info-name">用户姓名</div>

													<div class="profile-info-value">
														<input type="text" name="name" class="form-control" />
													</div>
												</div>

												<div class="profile-info-row">
													<div class="profile-info-name">用户状态</div>
																	
													<div class="profile-info-value">
													 <select name="state" id="state" class="form-control" disabled="disabled">
													  <dd:options codeType="UserStatusCode"/>
													 </select>
													</div>
												</div>

												<div class="profile-info-row">
													<div class="profile-info-name">证件类型</div>

													<div class="profile-info-value">
													<select name="certificateTypeCd" id="certificateTypeCd" class="form-control" disabled="disabled">
													 <dd:options codeType="CertificateType"/>
													 </select>
													</div>
												</div>
												
												<div class="profile-info-row">
													<div class="profile-info-name">证件号码</div>

													<div class="profile-info-value">
													<input type="text" name="certificateNum" id="certificateNum" class="form-control" />
													</div>
												</div>
												
												<div class="profile-info-row">
													<div class="profile-info-name">单位电话</div>

													<div class="profile-info-value">
													<input type="text" name="fixedphone" id="fixedphone" class="form-control" />
													</div>
												</div>
												
												
											</div>

											<div class="hr hr-8 dotted"></div>

											<div class="profile-user-info">
												<div class="profile-info-row">
													<div class="profile-info-name">手机号码</div>

													<div class="profile-info-value">
													<input type="text" name="mobilephone" id="mobilephone" class="form-control" />
													</div>
												</div>

												<div class="profile-info-row">
													<div class="profile-info-name">电子邮箱</div>
	
													<div class="profile-info-value">
														<input type="text" name="email" id="email" class="form-control" />
													</div>
												</div>

												<div class="profile-info-row">
													<div class="profile-info-name">所属机构
													</div>

													<div class="profile-info-value">
                                                                                <input type="text" id="deptNameMask" name="deptNameMask" class="form-control"
                                                                                disabled="disabled" />
                                                                                <!-- <span class="input-group-btn">
                                                                                     <button id="show-tree" class="btn btn-sm btn-yellow" type="button">
                                                                                        <i class="ace-icon fa fa-eye">
                                                                                        </i>
                                                                                    </button> 
                                                                                </span> -->
                                                                            <input type="hidden" name="deptName" id="deptName" disabled="disabled" />
                                                                            <div id="controlZTree" style="display:none;">
                                                                                <div class="col-xs-12" >
                                                                                    <ul id="treeDemo" class="ztree">
                                                                                    </ul>
                                                                                </div>
                                                          </div>
													</div>
												</div>

												<div class="profile-info-row">
													<div class="profile-info-name">单位地址
													</div>

													<div class="profile-info-value">
													<input type="text" name="address" id="address" class="form-control" />
													</div>
												</div>
												
												<div class="profile-info-row">
													<div class="profile-info-name">单位职务
													</div>

													<div class="profile-info-value">
													<select  name="unitPosition" id="unitPosition" class="form-control" disabled="disabled"/>
													<dd:options codeType="StationCd"/>
													</select>
													</div>
												</div>
											</div>
										</div>
										<!-- /.col -->
									</div>
									</form>
									<!-- /.row -->
									</div>
									<!-- /#home -->
									<div id="role" class="tab-pane">
										<div class="row">
                     							<table id="tbRoles" class="table table-striped table-hover col-xs-12" style="width:100%!important;">
                      								 <thead>
                         								<tr>
                          								 <th>
                             							 序号
                          								 </th>
                           							<th >
                             							机构
                           							</th>
                           							<th >
                             							部门
                           							</th>
                           							<th >
                             							角色
                           							</th>
                           							<th >
                             							授权人
                           							</th>
                           							<th >
                             							状态
                           							</th>
                           							<th>
                           							备注
                           							</th>
                           							<th>隐藏域</th>
                         								</tr>
                       							</thead>
                       							<tbody>
                       							</tbody>
                    							</table>
										
										</div>
									
									</div>
									<!-- <div id="permission" class="tab-pane"></div> -->
							</div>
						</div> <!--  END tabtable -->
					</div>
					</div>
					
				</div>
				<!-- /.page-content -->
			</div>
			<!-- /.main-content -->
			<c:import url="../../commons/footer.jsp" charEncoding="UTF-8" />
		</div>
		<!-- /.main-container -->
		<c:import url="../../commons/post-include.jsp" charEncoding="UTF-8" />
		<!-- page specific plugin scripts START -->
		<!-- page specific plugin scripts END -->
		<!-- inline scripts related to this page -->
		</script>

		<script>
			seajs.use('${ctx}/static/my/js/dashboard/personInfo/main');
		</script>
</body>

</html>