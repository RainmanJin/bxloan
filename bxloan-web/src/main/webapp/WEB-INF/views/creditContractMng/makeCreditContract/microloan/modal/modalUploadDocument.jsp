 <!-- 上传文档modal -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../../../../commons/taglibs.jsp" %>
<!-- 文档上传的专用隐藏域 -->
<div>
	<!-- <input type="hidden" id="userNameDI" />
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
	<input type="hidden" id="userIdDI" /> -->
	<input type="hidden" id="uploadTypeCode" />
</div>
 <!-- 上传文档modal start -->
<div id="add-modal-formWd" class="modal fade" tabindex="-1" role="basic" aria-hidden="true" style=";" data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content">
			<form id="uploadDocumentForm" class="form-horizontal" role="form" method="post">
				<!-- div modal-header start -->
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						&times;
					</button>
					<h4 class="blue bigger">
					</h4>
				</div>
				<!-- div modal-header end -->
				<!-- div modal-body start -->
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
									<input type="text" id="documentUserName" name="name" class="form-control" value="${requestScope.createUsername}" readonly="readonly" />
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
									<i class="ace-icon fa fa-upload"></i>上传
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
				<!-- div modal-body end -->
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
<!-- 上传文档modal end -->