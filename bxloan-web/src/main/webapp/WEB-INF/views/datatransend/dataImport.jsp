<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../../commons/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="zh">
<head>
	<base href="${ctx}/">
	<title>${title }</title>
	<meta charset="utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<meta name="description" content="Dashboard page" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
	<c:import url="../../commons/pre-include.jsp" charEncoding="UTF-8"/>
	<link rel="stylesheet" href="${ctx}/static/assets/js/uploadify/uploadify.css"/>   
	<script>var $$ctx = "${ctx}/";</script>
	<style type="text/css">
		.chosen-container-multi .chosen-choices li.search-choice .search-choice-close{
			background-image: none;
		}
	</style>
</head>
<body class="no-skin">
	<c:import url="../../commons/navbar.jsp" charEncoding="UTF-8" />
	<div class="main-container" id="main-container">
	<script type="text/javascript">
		try{ace.settings.check('main-container' , 'fixed')}catch(e){}
	</script>

	<c:import url="../../commons/sidebar.jsp" charEncoding="UTF-8" />
	
	<div class="main-content">
		<div class="breadcrumbs" id="breadcrumbs">
			<script type="text/javascript">
				try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
			</script>

			<ul class="breadcrumb">
				<li>
					<a href="${ctx}">主页</a>
				</li>
				<li class="active">数据报送</li>
				<li class="active">上海金融办</li>
			</ul>
		</div>
		<div class="page-content">
			<div class="page-header">
				<h1>
					数据报送
					<small><i class="ace-icon fa fa-angle-double-right"></i> 数据导入</small>
					<div class="pull-right">
	               		<button class="btn btn-sm btn-pre"  type="button" onclick="javascript:history.go(-1);">
	               			<i class="ace-icon fa fa-chevron-left"></i>
	               			返回
	              		 </button>
              			 <span style="float:right;">&nbsp;&nbsp;&nbsp;</span>
					</div>
				</h1>
			</div>
			
			
			<div class="row">
				<div class="col-md-12">
					<h3 class="smaller lighter blue">
						<span style="float:right">
							<button class="btn btn-sm btn-success" id="btn-data-import" style="">
									<i class="ace-icon fa fa-arrow-up"></i> 数据导入
		                 	</button>
	                 	</span>
					</h3>
					<table id="recordTable" class="table table-striped table-hover">
						<thead>
							<tr>
							    <th>合同编号</th>
							    <th>还款金额</th>
								<th><i class="ace-icon fa fa-clock-o bigger-110 hidden-480"></i>还款日期</th>
								<th><i class="ace-icon fa fa-clock-o bigger-110 hidden-480"></i>导入时间</th>
							    <th>操作</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
			<!-- 上传文档框 start -->
			<div id="data-upload-form" class="modal fade" tabindex="-1" role="basic"
            aria-hidden="true" style=";" data-backdrop="static">
              <div class="modal-dialog modal-lg" style="width:450px;margin:30px auto\0;">
                <div class="modal-content">
                  <form id="uploadDataForm" class="form-horizontal" role="form" method="post">
                  <input type="hidden" id="loginName" value="${loginName}">
                  <input type="hidden" id="orgId" value="${orgId}">
                    <div class="modal-header">
                    	<button type="button" class="close" data-dismiss="modal" name="closeUpload">
								&times;
						</button>
                      <h4 class="blue bigger">
                      </h4>
                    </div>
                    <div class="modal-body">
                      <div class="row">
                        <div class="col-xs-12">
                          <div class="form-group">
                          <div class="col-sm-1"></div>
                            <label class="col-sm-4 control-label no-padding-right">
                              <font color='red'>
                                <font color="red">*</font>
                              </font>
                              	选择文件：
                            </label>
                            <div class="col-sm-4">
                              <input type="file" id="uploadFile" name="uploadFile" />
                            </div>
                          </div>
                        </div>
                        <div class="col-xs-12">
                          <div class="form-group">
                              <div class="col-sm-2"></div>
	                          <div class="col-sm-4">
	                             <button id="btn-template-download" class="btn btn-sm btn-info" type="button">
	                        	<i class="ace-icon fa fa-download">
	                        	</i>
	                        		模板下载
	                      		</button>
	                      	  </div>
	                      	  <div class="col-sm-4">
	                             <button id="btn-data-upload" class="btn btn-sm btn-info" type="button">
	                        	<i class="ace-icon fa fa-upload">
	                        	</i>  
	                        		数据上传
	                      		</button>
	                      	  </div>
                          </div>
                        </div>
                      </div>
                    </div>
                  </form>
                </div>     
              </div>
            </div>
        </div>
		<!-- searchResult end -->
		</div><!-- /.page-content -->
	</div><!-- /.main-content -->
	<c:import url="../../commons/footer.jsp" charEncoding="UTF-8" />
		
	</div><!-- /.main-container -->
	<c:import url="../../commons/post-include.jsp" charEncoding="UTF-8"/>
	<script type="text/javascript" src="${res}/js/uploadify/jquery.uploadify.min.js">
	</script>
	<script>
		seajs.use('${ctx}/static/my/js/datatransend/dataimport/main');
	</script>
</body>
</html>
