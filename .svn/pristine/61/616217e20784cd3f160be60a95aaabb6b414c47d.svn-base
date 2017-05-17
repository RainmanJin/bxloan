<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
%>
    <%@include file="../../../commons/taglibs.jsp" %>
        <!DOCTYPE html>
        <html lang="zh">
            
            <head>
                <base href="${ctx}/">
                <title>
                    	邦信微贷
                </title>
                <meta charset="utf-8" />
                <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
                <meta name="description" content="Dashboard page" />
                <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0"
                />
                <c:import url="../../../commons/pre-include.jsp" charEncoding="UTF-8" />
                <!-- page specific plugin styles START -->
                <script>var $$ctx = "${ctx}/";</script>
                <link rel="stylesheet" href="${ctx}/static/assets/js/uploadify/uploadify.css"
                />
            </head>
            
            <body class="no-skin">
                <c:import url="../../../commons/navbar.jsp"  charEncoding="UTF-8" />
                <div class="main-container" id="main-container">
                    <script type="text/javascript">
                        try {
                            ace.settings.check('main-container', 'fixed')
                        } catch(e) {}
                    </script>
                    <c:import url="../../../commons/sidebar.jsp" charEncoding="UTF-8" />
                    <div class="main-content">
                        <div class="breadcrumbs" id="breadcrumbs">
                            <script type="text/javascript">
                                try {
                                    ace.settings.check('breadcrumbs', 'fixed')
                                } catch(e) {}
                            </script>
                            <ul class="breadcrumb">
                                <li>
                                    <i class="ace-icon fa fa-home home-icon">
                                    </i>
                                    <a href="${ctx}">主页</a>
                                   
                                </li>
                                <li class="active">
                                        	贷前管理
                                </li>
                                 <li class="active">
                                        	贷款发放
                                </li>
                               
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
                            <input type="hidden" id="projectIdCwField" value="" />
                            <input type="hidden" id="cuserIdField" value="${cuserId}" />
                            <input type="hidden" id="contractIdField" value="${contractId}" />
                            <input type="hidden" id="uploadPathField" value="${uploadPath}" />
                            <input type="hidden" id="payLoanIdField" value="" />
                            </div>
                            <div>
                                    <!-- 文档上传的专用隐藏域 -->
                                    <input type="hidden" id="userNameDI" />
                                    <input type="hidden" id="customerNumDI" />
                                    <input type="hidden" id="userNumDI" />
                                    <input type="hidden" id="userDateDI" />
                                    <input type="hidden" id="documentNumDI" />
                                    <input type="hidden" id="bizIdDI" />
                                    <input type="hidden" id="documentTypeDI" />
                                    <input type="hidden" id="bizNumDI" />
                                    <input type="hidden" id="userOrgDI" />
                                    <input type="hidden" id="createTypeDI" />
                                    <input type="hidden" id="fileTypeDI" />
                                    <input type="hidden" id="userIdDI" />
                                    <input type="hidden" id="partyIdField" />
                             </div>
                            
                            <%-- <c:import url="../commons/settings.jsp" charEncoding="UTF-8" /> --%>
                            <div class="page-header">
                                <h1>
                                    	贷款发放
                                    <small>
                                        <i class="ace-icon fa fa-angle-double-right">
                                        </i>
                                        	放款记录
                                    </small>
                             	 <span  style="float:right;">
                            	 <button class="btn btn-sm btn-success" id="add" type="button">
                            	 <i class="ace-icon fa fa-plus"></i> 
                                                                                	新增放款
                             	</button>
                             	<button class="btn btn-sm btn-pre" data-last="Finish" type="button" onclick="javascript:window.location.href='${ctx}/contractList'">
                             	<i class="ace-icon fa fa-chevron-left"></i>
                             		返回
                             	</button>
                             	</span>
                                </h1>
                            </div>
                            <!-- /.page-header -->
                            <!-- 条件搜索 -->
                           
                            <div class="row">
                                <div class="col-xs-12">
                                    <div class="row">
                                     	<div class="col-xs-12">
                                            <table id="tbContracts" class="table table-striped table-hover" style="width:100%!important;">
                                                <thead>
                                                    <tr>
                                                        <th width="0%">
                                                            	选择
                                                        </th>
                                                        <th>
                                                            	合同编号
                                                        </th>
                                                        <th>
                                                            	客户名称
                                                        </th>
                                                        <th>
                                                                                                                                                                贷款产品
                                                        </th>
                                                        <th>
                                                           		 合同金额（元）
                                                        </th>
                                                        <th>
                                                            	放款金额（元）
                                                        </th>
                                                        <th>
                                                            	放款日期
                                                        </th>
                                                        <th>
                                                            	是否上传借据
                                                        </th>
                                                        <th>
                                                            	单据状态
                                                        </th>
                                                        <th width="10%">
                                                            	操作
                                                        </th>
                                                    </tr>
                                                    
                                                </thead>
                                                <tbody>
                                                </tbody>
                                            </table>
                                            </div>
                                        </div>
                                    </div>
                                    </div>
                                    <!-- /.row -->
                           			
                           			
                           			 <!-- 文档上传 -->
                                <div id="add-modal-formWd" class="modal fade" tabindex="-1"  role="basic" aria-hidden="true" style=";" data-backdrop="static">
                                    <div class="modal-dialog">
                                        <div class="modal-content">
                                            <form id="uploadDocumentForm" class="form-horizontal" role="form" method="post">
                                                <div class="modal-header">
                                                    <button type="button" class="close" data-dismiss="modal">
                                                        &times;
                                                    </button>
                                                    <h4 class="blue bigger">
                                                    </h4>
                                                </div>
                                                <div class="modal-body">
                                                    <div class="row">
                                                        <div class="col-xs-12">
                                                            <input type="hidden" id="form-field-0" name="id" />
                                                            <div class="form-group">
                                                                <label class="col-sm-2 control-label no-padding-right" for="accountName">
                                                                    <font color='red'>
                                                                        *
                                                                    </font>
                                                                    	创建人：
                                                                </label>
                                                                <div class="col-sm-8">
                                                                    <input type="text" id="documentUserName" name="name" class="form-control"
                                                                    value="${currentUserName}" readonly="readonly" />
                                                                </div>
                                                            </div>
                                                            <div class="form-group">
                                                                <label class="col-sm-2 control-label no-padding-right" for="accountNum">
                                                                    <font color='red'>
                                                                        *
                                                                    </font>
                                                                    	选择文件：
                                                                </label>
                                                                <div class="col-sm-8">
                                                                    <input type="file" id="uploadFile" name="uploadFile" />
                                                                </div>
                                                                <span style="float:left;">
                                                                <button id="add-Wd-submit" class="btn btn-sm btn-info" type="button" data-loading-text="正在保存中...">
                                                        			<i class="ace-icon fa fa-upload">
                                                       				 </i>
                                                        			上传
                                                    			</button>
                                                                </span>
                                                            </div>
                                                            <div class="form-group">
                                                                <label class="col-sm-2 control-label no-padding-right" for="certificateCd">
                                                                    <font color='red'>
                                                                        *
                                                                    </font>
                                                                    	文档要求：
                                                                </label>
                                                                <div class="col-sm-8">
                                                                    <span class="block input-icon input-icon-right">
                                                                        doc、docx、xls、xlsx、pdf、jpg
                                                                        <%-- <select name="fileType" id="fileType" class="form-control">
                                                                            <dd:options codeType="FileType" />
                                                                            </select>
                                                                            --%>
                                                                    </span>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="modal-footer">
                                                    
                                                    <!-- <button class="btn btn-sm btn-default" data-dismiss="modal" type="button"
                                                    onclick="javascript:$('#uploadFile').uloadifyClearQueue();">
                                                    <i class="ace-icon fa fa-times"></i>
                                                    	取消
                                                    </button> -->
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                           			
                           			
                           			
                            
                        </div>
                        <!-- /.page-content -->
                    </div>
                    <!-- /.main-content -->
                    <c:import url="../../../commons/footer.jsp" charEncoding="UTF-8" />
                </div>
                <!-- /.main-container -->
                <c:import url="../../../commons/post-include.jsp" charEncoding="UTF-8" />
                <!-- page specific plugin scripts START -->
                <script type="text/javascript" src="${ctx}/static/assets/js/uploadify/jquery.uploadify.min.js">
                </script>
                <!-- page specific plugin scripts END -->
                <!-- inline scripts related to this page -->
                 
                <script>
                    seajs.use('${ctx}/static/my/js/contract/records/main/main');
                </script>
            </body>
        
