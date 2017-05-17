<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
%>
	<%@ taglib uri="http://www.coamc-tech.com/taglibs/datadict" prefix="dd"
	%>
		<div id="input_doc_modal" class="modal" tabindex="-1" data-backdrop="static">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<form id="input_doc_form" action="" class="form-horizontal">
						<input type="hidden" name="partyId" value="" />
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" name="closeUpload">
												&times;
							</button>
							<h4 class="blue bigger">
							新增
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
											<font color='red'>
												*
											</font>
											创建人：
										</label>
										<div class="col-xs-7">
											<span class="block input-icon input-icon-right">
												<input type="text" name="userName" class="form-control" value="" readonly="readonly">
											</span>
										</div>
										<div class="help-block col-xs-6 col-sm-reset inline">
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="documentType">
											<font color="red">
												*
												</font>
												请选择文档类型：
										</label>
										<div class="col-sm-12">
										<table id="tb_doc_selector" name="selector" class="table table-striped table-hover table-bordered table-condensed"
										style="margin-top:10px;">
											<thead>
												<th width="5%">
													选择
												</th>
												<th width="23%">
													上传内容
												</th>
												<th width="5%">
													是否上传
												</th>
												<th width="5%">
													选择
												</th>
												<th width="23%">
													上传内容
												</th>
												<th width="5%">
													是否上传
												</th>
												<th width="5%">
													选择
												</th>
												<th width="23%">
													上传内容
												</th>
												<th width="5%">
													是否上传
												</th>
											</thead>
											<tbody id="uploadTbody">
												
											</tbody>
										</table>
									  </div>
									</div>
									<input type="hidden" name="documentType" id="documentType" />
									<div class="form-group">
										<label class="col-xs-3 control-label no-padding-right">
											<font color='red'>
												*
											</font>
											选择文件：
										</label>
										 <div class="col-sm-8">
												<input type="file" id="uploadFile" name="uploadFile" />
										</div>
												  <span style="float:left;">
												<button id="btn_doc_upload" class="btn btn-sm btn-info" type="button"
												data-loading-text="正在保存中...">
												<i class="ace-icon fa fa-upload">
												</i>
												上传
												</button>
												</span>
										<div class="help-block col-xs-6 col-sm-reset inline">
										</div>
									</div>
									<div class="form-group">
										<label class="col-xs-3 control-label no-padding-right">
											<font color='red'>
												*
											</font>
											文件要求：
										</label>
										<div class="col-xs-7 ">
											<span class="block input-icon input-icon-right">
												doc、docx、xls、xlsx、pdf、jpg、rar(请确保压缩包内的文件也符合此格式要求)
											</span>
										</div>
										<div class="help-block col-xs-6 col-sm-reset inline">
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="modal-footer">
							
							<button type="button" class="btn btn-sm btn-default" data-dismiss="modal">
								<i class="ace-icon fa fa-times">
								</i>
								取消
							</button>
						</div>
					</form>
				</div>
			</div>
		</div>