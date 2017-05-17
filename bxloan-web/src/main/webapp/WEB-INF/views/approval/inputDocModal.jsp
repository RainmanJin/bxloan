<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.coamc-tech.com/taglibs/datadict" prefix="dd" %>
<div id="input_doc_modal" class="modal" tabindex="-1" data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content">
			<form id="input_doc_form" action="" class="form-horizontal" >
				<input type="hidden" name="partyId" value=""/>
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="blue bigger">
						<i class='ace-icon fa fa-plus'></i> 新增
					</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-md-12">
						<h4 class="blue">
	           				文档上传
	       				</h4>
				        <div class="form-group">
				        	<label class="col-xs-3 control-label no-padding-right">
								<font color='red'>*</font>创建人：
							</label>
							<div class="col-xs-7">
								<span class="block input-icon input-icon-right">
									<input type="text" name="userName" class="form-control" value="" readonly="readonly">
								</span>
							</div>
							<div class="help-block col-xs-6 col-sm-reset inline"></div>
						    
						</div>
						
						<input type="hidden" name="documentType" id="documentTypeCd" />
						
						<div class="form-group">
							<label class="col-xs-3 control-label no-padding-right">
								<font color='red'>*</font>选择文件：
							</label>
							<div class="col-xs-7 ">
								<span class="block input-icon input-icon-right">
								 	<input type="file" id="uploadFile" name="uploadFile" />
								</span>
							</div>
							<div class="help-block col-xs-6 col-sm-reset inline"></div>
						</div>
						<div class="form-group">
							<label class="col-xs-3 control-label no-padding-right">
								<font color='red'>*</font>文件要求：
							</label>
							<div class="col-xs-7 ">
								<span class="block input-icon input-icon-right">
								   doc、docx、xls、xlsx、pdf、jpg、rar(请确保压缩包内的文件也符合此格式要求)
								</span>
							</div>
							<div class="help-block col-xs-6 col-sm-reset inline"></div>
						</div> 
						</div>
					</div>
				
				</div>
				<div class="modal-footer">
					 <button id="btn_doc_upload" class="btn btn-sm btn-info"
					  type="button"  data-loading-text="正在保存中...">
	                      <i class="ace-icon fa fa-upload"></i>上传
	                  </button>
					<button type="button" class="btn btn-sm btn-default" data-dismiss="modal">
						<i class="ace-icon fa fa-times"></i> 取消
					</button>
				</div>
			</form>
		</div>
	</div>
</div>