<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="row">
		<div class="col-sx-12">
			<form id="form-doc-search" class="form-horizontal">
			
			 <div class="col-xs-12">
                <span>
                    	文件名：
                  	<input id="query_documentName" type="text" placeholder="输入文档名称">
                  	  内容类型：
					<input id="query_contentType" type="text" placeholder="输入内容类型">
                </span>
                <span style="float:right;" class="my-button-group">
                  	<button id="btn-query" type="button" class="btn btn-sm btn-purple">
						<i class="ace-icon fa fa-search"></i> 查询
					</button>
					<button id="addWd" type="button" class="btn btn-sm btn-success">
						<i class="ace-icon fa fa-plus"></i> 新增
					</button> 
					<button type="button" class="btn btn-sm btn-yellow" role="pdownloadWd">
                            <i class="ace-icon fa fa-download">
                            </i>
                            	批量下载
                    </button>
                </span>
            </div>
			
			</form>
		</div>
</div>


<table id="tbWd" class="table table-striped table-hover">
	<thead>
		<tr>
			<th><input type="checkbox" id="wdcb" title='选取本页所有文档' />选择</th>
			<th>文档名称</th>
			<th>内容类型</th>
			<th>文档类型</th>
			<th>创建人</th>
			<th>创建时间</th>
			<th>关联方式</th>
			<th>操作</th>
			<th>其他</th>
		</tr>
	</thead>
	<tbody>
	</tbody>
</table>




 <!-- 文档上传 -->
            <div id="add-modal-formWd" class="modal fade" tabindex="-1" role="basic"
            aria-hidden="true" style=";" data-backdrop="static">
              <div class="modal-dialog modal-lg">
                <div class="modal-content">
                  <form id="uploadDocumentForm" class="form-horizontal" role="form" method="post">
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
                          <input type="hidden" id="form-field-0" name="id" />
                          <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="accountName">
                              <font color='red'>
                                <font color="red">*</font>
                              </font>
                              	创建人：
                            </label>
                            <div class="col-sm-8">
                              <input type="text" id="documentUserName" name="name" class="form-control"
                               readonly="readonly" />
                            </div>
                          </div>
                         <%--  <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="documentType">
                              <font color='red'>
                                <font color="red">*</font>
                              </font>
                              	文档类型：
                            </label>
                            <div class="col-sm-8">
                              <span class="block input-icon input-icon-right">
                                <select name="documentType" id="documentType" class="form-control">
                                  <dd:options codeType="ElCustomerDocType" />
                                </select>
                              </span>
                            </div>
                          </div> --%>
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
											<tbody id="uploadTbody">
												
											</tbody>
										</table>
									  </div>
									</div>
                          <input type="hidden" name="documentType" id="documentType" />
                          <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="accountNum">
                              <font color='red'>
                                <font color="red">*</font>
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
                            <label class="col-sm-3 control-label no-padding-right" for="certificateCd">
                              <font color='red'>
                                <font color="red">*</font>
                              </font>
                              	文档要求：
                            </label>
                            <div class="col-sm-8">
                              <span class="block input-icon input-icon-right">
                                doc、docx、xls、xlsx、pdf、jpg、rar(请确保压缩包内的文件也符合此格式要求)
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
