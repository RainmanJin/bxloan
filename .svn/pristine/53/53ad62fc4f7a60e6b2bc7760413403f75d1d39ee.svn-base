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
                <!-- <link rel="stylesheet" href="${ctx}/static/assets/js/uploadify/uploadify.css"
                /> -->
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
                            <input type="hidden" id="payLoanIdField" value="" />
                            </div>
                            <%-- <c:import url="../commons/settings.jsp" charEncoding="UTF-8" /> --%>
                            <div class="page-header">
                                <h1>
                                    	贷款发放
                                    <small>
                                        <i class="ace-icon fa fa-angle-double-right">
                                        </i>
                                        	查看还款计划
                                    </small>
                                    <span style="float:right;">
                             
                              		<button class="btn btn-sm btn-yellow" role="downloadRpd" type="button">
                             		 <i class="ace-icon fa fa-download"></i>
                             			下载还款计划
                             			</button>
                             		<button class="btn btn-sm btn-pre" data-last="Finish" type="button" onclick="javascript:history.go(-1);">
                             			<i class="ace-icon fa fa-chevron-left"></i>
                             			返回
                            		 </button>
                                    </span>
                                </h1>
                            </div>
                            <!-- /.page-header -->
                           
                            <div class="row">
                                <div class="col-xs-12">
                                    <div class="row">
                                     	<div class="col-xs-12">
                                            <table id="tbRepayLoan" class="table table-striped table-hover" style="width:100%!important;">
                                                <thead>
                                                    <tr>
                                                        <th width="0%">
                                                            	选择
                                                        </th>
                                                        <th>
                                                            	期次
                                                        </th>
                                                        <th>
                                                            	计划还款日
                                                        </th>
                                                        <th>
                                                                                                                                                               本期应还金额（元）
                                                        </th>
                                                        <th>
                                                           		 应还本金（元）
                                                        </th>
                                                        <th>
                                                            	应还利息（元）
                                                        </th>
                                                        <th>
                                                            	状态
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
                        </div>
                        <!-- /.page-content -->
                    </div>
                    <!-- /.main-content -->
                    <c:import url="../../../commons/footer.jsp" charEncoding="UTF-8" />
                </div>
                <!-- /.main-container -->
                <c:import url="../../../commons/post-include.jsp" charEncoding="UTF-8" />
                <!-- page specific plugin scripts START -->
                <!-- <script type="text/javascript" src="${ctx}/static/assets/js/uploadify/jquery.uploadify.min.js">
                </script> -->
                <!-- page specific plugin scripts END -->
                <!-- inline scripts related to this page -->
                 
                <script>
                    seajs.use('${ctx}/static/my/js/contract/loangrant/edit/main');
                </script>
            </body>
        
