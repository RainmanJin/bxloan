<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
%>
    <%@include file="../../../../commons/taglibs.jsp" %>
        <!DOCTYPE html>
        <html lang="zh">
            
            <head>
                <base href="${ctx}/">
                <title>
                    	${title }
                </title>
                <meta charset="utf-8" />
                <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
                <meta name="description" content="Dashboard page" />
                <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0"
                />
                <c:import url="../../../../commons/pre-include.jsp" charEncoding="UTF-8" />
                <!-- page specific plugin styles START -->
                <!-- <link href="${ctx}/static/assets/css/bootstrap-combined.min.css" rel="stylesheet"> -->
                <link rel="stylesheet" type="text/css" href="${ctx}/static/assets/css/bootstrap-datetimepicker.css">
                <link rel="stylesheet" href="${ctx}/static/assets/js/uploadify/uploadify.css"
                />
                <!-- page specific plugin styles END -->
                <!-- inline styles related to this page -->
                <script>
                    var $$ctx = "${ctx}/";
                </script>
                <style>
     			.ace-settings-box.open {
				height:100%!important;
				margin-top:0px!important;
				}

                </style>
            </head>
            
            <body class="no-skin">
            	<c:if test="${showBar != false }">
                	<c:import url="../../../../commons/navbar.jsp" charEncoding="UTF-8" />
                </c:if>
                <div class="main-container" id="main-container">
                    <script type="text/javascript">
                        try {
                            ace.settings.check('main-container', 'fixed')
                        } catch(e) {}
                    </script>
                    <c:if test="${showBar != false }">
                    	<c:import url="../../../../commons/sidebar.jsp" charEncoding="UTF-8" />
                    </c:if>
                    <div class="main-content">
                    	<c:if test="${showBar != false }">
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
	                                    <a href="${ctx}">
	                                        	主页
	                                    </a>
	                                </li>
	                                <li class="active">
	                                    	工作面板
	                                </li>
	                                <li class="active">
	                                    	集中审批
	                                </li>
	                            </ul>
	                            <!-- /.breadcrumb -->
	                        </div>
	                    </c:if>
                        <div class="page-content">
                            <div class="ace-settings-container" id="ace-settings-container">
                                <div class="btn btn-app btn-xs btn-warning ace-settings-btn" id="ace-settings-btn">
                                    <i class="ace-icon fa fa-eye bigger-150">
                                    </i>
                                </div>
                                <div class="ace-settings-box clearfix" id="ace-settings-box">
                                    <div class="pull-left width-50">
                                        <div class="ace-settings-item" style="margin-top:10px;">
                                            <label class="lbl" for="ace-settings-navbar">
                                            	<a style="text-decoration: none;" id="customerForFloatWindow"   target="_blank">
                                              	  客户信息
                                              	  </a>
                                            </label>
                                        </div>
                                        <div class="ace-settings-item">
                                            <label class="lbl" for="ace-settings-sidebar">
                                            <a style="text-decoration: none;" id="businessForFloatWindow"  target="_blank">
                                              	  业务信息
                                            </a>
                                            </label>
                                        </div>
                                        <div class="ace-settings-item">
                                            <label class="lbl" for="ace-settings-breadcrumbs">
                                            <a style="text-decoration: none;" id="suggestionForFloatWindow"  target="_blank">
                                 			过程意见
                                			</a>
                                            </label>
                                        </div>
                                        <!-- <div class="ace-settings-item">
                                            <label class="lbl" for="ace-settings-breadcrumbs">
                                                	尽职调查表
                                            </label>
                                        </div>
                                        <div class="ace-settings-item">
                                            <label class="lbl" for="ace-settings-breadcrumbs">
                                                	业务批复结论
                                            </label>
                                        </div>
                                        <div class="ace-settings-item">
                                            <label class="lbl" for="ace-settings-breadcrumbs">
                                                	打印借据
                                            </label>
                                        </div> -->
                                        <div class="ace-settings-item">
                                            <label class="lbl" for="ace-settings-breadcrumbs">
                                            <a style="text-decoration: none;" id="loanTrialWindow"  target="_blank">
                                              	贷款试算
                                            </a>
                                            </label>
                                        </div>
                                        <c:if test="${agriculturalLoan}">
											<div class="ace-settings-item">
												<label class="lbl" for="ace-settings-breadcrumbs">
													<a style="text-decoration: none;" id="bndFinancialStatements" target="_blank">
														邦农贷财务报表
													</a>
												</label>
											</div>
										</c:if>
                                    </div>
                                    <!-- /.pull-left -->
                                </div>
                                <!-- /.ace-settings-box -->
                            </div>
                            <%-- <c:import url="../../../../commons/settings.jsp" charEncoding="UTF-8" />
                            --%>
                            <div class="page-header">
                                <h1>
                                    	工作面板
                                    <small>
                                        <i class="ace-icon fa fa-angle-double-right">
                                        </i>
                                        	签订合同
                                    </small>
                                    <span style="float:right;">
                             		<button class="btn btn-sm btn-pre" data-last="Finish" type="button" onclick="javascript:history.go(-1);">
                             			<i class="ace-icon fa fa-chevron-left"></i>
                             			返回
                            		 </button>
                            		 <span style="float:right;">&nbsp;&nbsp;&nbsp;</span>
                                    </span>
                                </h1>
                            </div>
                            <!-- /.page-header -->
                            <!-- 表单主体 -->
                            <!-- TAB页标题 -->
                            <div class="row">
                                <div class="col-xs-12">
                                    <!-- PAGE CONTENT BEGINS -->
                                    <div class="tabbable">
                                        <ul class="nav nav-tabs padding-12 tab-color-blue " id="myTab">
                                            <li class="active">
                                                <a data-toggle="tab" href="#faq-tab-1">
                                                   	 合同信息
                                                </a>
                                            </li>
                                            <li>
                                                <a data-toggle="tab" href="#faq-tab-4">
                                                    	费用列表
                                                </a>
                                            </li>
                                            <li>
                                                <a data-toggle="tab" href="#faq-tab-2">
                                                    	相关文档
                                                </a>
                                            </li>
                                            <li>
                                                <a data-toggle="tab" href="#faq-tab-5">
                                                    	从合同信息
                                                </a>
                                            </li>
                                            <li>
                                                <a data-toggle="tab" href="#faq-tab-6">
                                                    	共同借款人
                                                </a>
                                            </li>
                                            <li>
                                                <a data-toggle="tab" href="#faq-tab-3">
                                                    	意　　见
                                                </a>
                                            </li>
                                        </ul>
                                        <div class="tab-content no-border padding-24">
                                            <!-- 隐藏域 -->
                                            <div>
                                                <input type="hidden" name="contractIdField" id="contractIdField" class="form-control"
                                                value="${requestScope.contractId}" />
                                                <input type="hidden" name="projectIdField" id="projectIdField" class="form-control"
                                                value="${requestScope.projectId}" />
                                                <input type="hidden" name="workflowIdField" id="workflowIdField" class="form-control"
                                                value="${requestScope.workflowId}" />
                                                <input type="hidden" name="taskIdField" id="taskIdField" class="form-control"
                                                value="${requestScope.taskId}" />
                                                 <input type="hidden" name="taskStageCodeField" id="taskStageCodeField" class="form-control"
                                                value="${requestScope.taskStageCode}" />
                                                <input type="hidden" name="partyIdField" id="partyIdField" class="form-control"
                                                value="${requestScope.partyId}" />
                                                <input type="hidden" name="projectNoField" id="projectNoField" class="form-control"
                                                value="${requestScope.projectNo}" />
                                                <input type="hidden" name="consultLocation" id="consultLocation" class="form-control"
                                                value="${requestScope.consultLocation}" />
                                                 <input type="hidden" name="workTypeField" id="workTypeField" class="form-control"
                                                value="${requestScope.workType}" />
                                                 <input type="hidden" name="uploadPathField" id="uploadPathField" class="form-control"
                                                value="${requestScope.uploadPath}" />
                                                 <input type="hidden" name="finalFloatRateField" id="finalFloatRateField" class="form-control"
                                                 />
                                                 <input type="hidden" name="checkFinalRateField" id="checkFinalRateField" class="form-control"
                                                 />
                                                 <input type="hidden" id="partyTypeField" value="${requestScope.partyType}" />
                                               	 <!-- add by wangyawei 20150701 start 增加邦农贷产品标识，若贷款产品为邦农贷，标识为true；否则，标识为false -->
												 <input type="hidden" id="agriculturalLoan" value="${requestScope.agriculturalLoan}">
												 <!-- add by wangyawei 20150701 end -->
                                            </div>
                                            <!-- 合同信息 -->
                                            <div id="faq-tab-1" class="tab-pane fade in active">
                                            	<jsp:include page="details/contractInfo.jsp"></jsp:include>
                                            </div>
                                            
                                            
                                            <!-- 相关文档 -->
                                            <div id="faq-tab-2" class="tab-pane fade">
                                            	 <jsp:include page="details/documents.jsp"></jsp:include>    
                                            </div>
                                            <!-- 意见 -->
                                            <div id="faq-tab-3" class="tab-pane fade">
                                            	 <jsp:include page="details/suggestion.jsp"></jsp:include>    
                                            </div>
                                                <!-- 费用列表 -->
                                            <div id="faq-tab-4" class="tab-pane fade">
                                               <jsp:include page="details/expense.jsp"></jsp:include>    
                                            </div>
                                                 <!-- 从合同信息 -->
                                            <div id="faq-tab-5" class="tab-pane fade">
                                               <jsp:include page="details/subcontract.jsp"></jsp:include>    
                                            </div>    
                                                  <!-- 共同借款人信息 -->
                                            <div id="faq-tab-6" class="tab-pane fade">
                                               <jsp:include page="details/coborrower.jsp"></jsp:include>    
                                            </div>       
                                            </div>
                                        </div>
                                    </div>
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
		<input type="hidden" id="uploadTypeCode" />
	</div>
	<!-- 文档上传 -->
	<div id="add-modal-formWd" class="modal fade" tabindex="-1" role="basic"
	aria-hidden="true" style=";" data-backdrop="static">
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
										value="${requestScope.createUsername}" readonly="readonly" />
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
									<button id="add-Wd-submit" class="btn btn-sm btn-info" type="button" data-loading-text="正在保存中...">
							<i class="ace-icon fa fa-upload">
							</i>
							上传
						</button>
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
                                
                                <div id="modalForView" class="modal fade text-center"  tabindex="-1"  role="basic" aria-hidden="true" style=";" data-backdrop="static" >
                                    <div class="modal-dialog modal-full" style="display: inline-block; width: 1100px; ">
                                        <div class="modal-content">
                                        	<div class="modal-header">
												<h4 class="modal-title" align="center"><span role="modal-title" id="td_name">客户信息</span></h4>
											</div>
											<div class="modal-body">
												<iframe src="" name="mainFrame" id="mainFrame"  width="100%"  frameborder="0"
													marginheight="0" marginwidth="0" scrolling-x="no"  
													> </iframe>
											</div>
											<div style="margin-bottom:10px;">
											<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
											</div>
                                        </div>
                                    </div>
                                </div>
                                
                                <!-- 选择下一步的任务执行者 -->
				 			<div id="select_assigner" class="modal fade" data-backdrop="static">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal">&times;</button>
											<h4 class="blue bigger"></h4>
										</div>
										<div class="modal-body"  >
											<label>
											选择将任务委派给：
											</label>
	                                   		<div>
	                                   		<select id="selectAssignerName" class="form-control"></select>
	                                   		</div>
										<div class="modal-footer" style="background-color:#fff!important;">
											<button id="select_assigner_sure"  class="btn btn-sm btn-primary">
												<i class="ace-icon fa fa-save"></i> 确定
											</button>
											<button class="btn btn-sm" data-dismiss="modal">
												<i class="ace-icon fa fa-times"></i> 取消
											</button>
										</div>
									</div>
								</div>
							</div>
						</div>
                                
                                <!-- row -->
                            </div>
                            <!-- /.page-content -->
                        </div>
                        <!-- /.main-content -->
                        <c:if test="${showBar != false }">
                        	<c:import url="../../../../commons/footer.jsp" charEncoding="UTF-8" />
                        </c:if>
                    </div>
                    <!-- /.main-container -->
                    <c:import url="../../../../commons/post-include.jsp" charEncoding="UTF-8" />
                    <!-- page specific plugin scripts START -->
                    <script type="text/javascript" src="${ctx}/static/assets/js/date-time/bootstrap-datetimepicker.min.js">
                    </script>
                    <script type="text/javascript" src="${ctx}/static/assets/js/uploadify/jquery.uploadify.min.js">
                    </script>
                    <!-- page specific plugin scripts END -->
                    <!-- inline scripts related to this page -->
                    
                   
                    </script>
                    <script>
                        seajs.use('${ctx}/static/my/js/contract/edit_detail/microloan/main');
                    </script>
            </body>
        
        </html>