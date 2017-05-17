<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
%>
<%@include file="../../../commons/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="zh">
    
<head>
     <base href="${ctx}/">
	<title>${title }</title>
	<meta charset="utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<meta name="description" content="Dashboard page" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0"
	/>
	<c:import url="../../../commons/pre-include.jsp" charEncoding="UTF-8" />
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
     <c:import url="../../../commons/navbar.jsp" charEncoding="UTF-8" />
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
	                                  	<a style="text-decoration: none;" id="openCustomerWindow"  target="_blank">
	                                   		  客户信息
	                                  	</a>
                                  </label>
                              </div>
                              <div class="ace-settings-item">
                                  <label class="lbl" for="ace-settings-sidebar">
	                                   <a style="text-decoration: none;" id="openBusinessWindow"  target="_blank">
	                                     	  业务信息
	                                   </a>
                                  </label>
                              </div>
                              <div class="ace-settings-item">
                                  <label class="lbl" for="ace-settings-breadcrumbs">
	                                   <a style="text-decoration: none;" id="openSuggestionWindow"  target="_blank">
	                        				过程意见
	                       			   </a>
                                  </label>
                              </div>
                          </div>
                          <!-- /.pull-left -->
                      </div>
                      <!-- /.ace-settings-box -->
                 </div>
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
                  <!-- 隐藏域 -->
                 <div>
                 	   <input type="hidden" id="wfCode" value="${wfCode}" />
                       <input type="hidden" name="contractIdField" id="contractIdField" value="${vo.contractId}" />
                       <input type="hidden" name="projectIdField" id="projectIdField"  value="${vo.projectId}" />
                       <input type="hidden" name="projectNoField" id="projectNoField" value="${vo.projectNo}" />
                       <input type="hidden" name="workflowIdField" id="workflowIdField" value="${workflowId}" />
                       <input type="hidden" name="taskIdField" id="taskIdField" value="${taskId}" />
                       <input type="hidden" name="taskStageCodeField" id="taskStageCodeField" value="${taskStageCode}" />
                       <input type="hidden" name="partyIdField" id="partyIdField" value="${partyId}" />
                       <input type="hidden" name="consultLocation" id="consultLocation" value="${consultLocation}" />
                       <input type="hidden" name="workTypeField" id="workTypeField" value="${workType}" />
                       <input type="hidden" name="uploadPathField" id="uploadPathField" value="${uploadPath}" />
                       <input type="hidden" name="finalFloatRateField" id="finalFloatRateField" />
                       <input type="hidden" name="checkFinalRateField" id="checkFinalRateField" />
                       <input type="hidden" id="partyTypeField" value="${partyType}" />
                  </div>
                 <!-- 表单主体 -->
                 <!-- TAB页标题 -->
                 <div class="row">
                     <div class="col-xs-12">
                         <!-- PAGE CONTENT BEGINS -->
                         <div class="tabbable">
                             <ul class="nav nav-tabs padding-12 tab-color-blue " id="myTab">
                                 <li class="active">
                                     <a data-toggle="tab" href="#tab-1">
                                        	 主合同信息
                                     </a>
                                 </li>
                                 <li>
                                     <a data-toggle="tab" href="#tab-2">
                                         	从合同信息
                                     </a>
                                 </li>
                                 <li>
                                     <a data-toggle="tab" href="#tab-3">
                                         	费用列表
                                     </a>
                                 </li>
                                 <li>
                                     <a data-toggle="tab" href="#tab-4">
                                         	共同借款人
                                     </a>
                                 </li>
                                 <li>
                                     <a data-toggle="tab" href="#tab-5">
                                         	相关文档
                                     </a>
                                 </li>
                                 <li>
                                     <a data-toggle="tab" href="#tab-6">
                                         	意　　见
                                     </a>
                                 </li>
                             </ul>
                             <div class="tab-content no-border padding-24">
                                 <!-- 主合同信息 -->
								<div id="tab-1" class="tab-pane fade in active">
									<jsp:include page="underCreditContract.jsp" />
								</div>
								
								<!-- 从合同信息 -->
								<div id="tab-2" class="tab-pane fade">
									<jsp:include page="subContract.jsp" />
								</div>
								
								<!-- 费用列表 -->
								<div id="tab-3" class="tab-pane fade">
									<jsp:include page="expenseList.jsp" />
								</div>
								
								<!-- 共同借款人 -->
								<div id="tab-4" class="tab-pane fade">
									<jsp:include page="commonBorrower.jsp" />
								</div>
								
								<!-- 相关文档 -->
								<div id="tab-5" class="tab-pane fade">
									<jsp:include page="document.jsp" />
								</div>
								
								<!-- 意见-->
								<div id="tab-6" class="tab-pane fade">
									<jsp:include page="suggestion.jsp" />
								</div>
                             </div>
                         </div>
                         <!-- 客户信息/业务信息modal start -->
                         <div id="modalForView" class="modal fade text-center"  tabindex="-1"  role="basic" aria-hidden="true" style="" data-backdrop="static" >
                                <div class="modal-dialog modal-full" style="display: inline-block; width: 1100px; ">
                                    <div class="modal-content">
	                                   	<div class="modal-header">
											<h4 class="modal-title" align="center"><span role="modal-title" id="td_name">客户信息</span></h4>
										</div>
										<div class="modal-body">
											<iframe src="" name="mainFrame" id="mainFrame" height="700px" width="100%"  frameborder="0" marginheight="0" marginwidth="0" scrolling-x="no" > 
											</iframe>
										</div>
										<div style="margin-bottom:10px;">
											<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
										</div>
                                    </div>
                                </div>
                          </div>
                         <!-- 客户信息modal end -->
                         <!-- 上传文档modal start -->
                         <jsp:include page="modal/modalUploadDocument.jsp"></jsp:include>
                         <!-- 上传文档modal end -->
                         <!-- 提交下一环节取人modal start -->
                         <jsp:include page="modal/modalSelectAssigner.jsp"></jsp:include>
                         <!-- 提交下一环节取人modal end -->
                     </div>
                     <!-- row -->
                 </div>
                 <!-- /.page-content -->
             </div>
             <!-- /.main-content -->
            <c:import url="../../../commons/footer.jsp" charEncoding="UTF-8" />
       </div>
       <!-- /.main-container -->
       <c:import url="../../../commons/post-include.jsp" charEncoding="UTF-8" />
       <!-- page specific plugin scripts START -->
       <script type="text/javascript" src="${ctx}/static/assets/js/date-time/bootstrap-datetimepicker.min.js">
       </script>
       <script type="text/javascript" src="${ctx}/static/assets/js/uploadify/jquery.uploadify.min.js">
       </script>
       <!-- page specific plugin scripts END -->
       <!-- inline scripts related to this page -->
       </script>
       <script>
           seajs.use('${ctx}/static/my/js/creditContractMng/signCreditLoanContract/main/main');
       </script>
 </body>
</html>