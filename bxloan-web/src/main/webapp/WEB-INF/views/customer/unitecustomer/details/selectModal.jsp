<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.coamc-tech.com/taglibs/datadict" prefix="dd" %>
<div role="custManagerModal" class="modal fade" data-backdrop="static">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title"></h4>
			</div>
			<div class="modal-body">
				<div class="row">
					<div class="col-md-12">
						<form id="custManagerForm" class="form-inline" role="form">
							<div class="form-group">
								<label class="sr-only" for="name"></label> 
								经理名称： <input type="text" class="form-control" name="custManager" />
							</div>

						

							<button type="button" class="btn btn-sm btn-purple" role="search">
								<i class="ace-icon fa fa-search"></i>查询
							</button>

							<button type="button" class="btn btn-sm btn-default" role="reset">
								<i class="ace-icon fa fa-undo"></i>重置
							</button>

						</form>

						<div class="row">
							<div class="col-md-12">
								<table role="table" class="table table-striped table-hover">
									<thead>
										<tr>
											<th>选择</th>
											<th>经理名称</th>
											<th>所属机构</th>
											<th>状态</th>
										</tr>
									</thead>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button role="sure" type="button" class="btn btn-primary">选择</button>
				<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
			</div>
		</div>
	</div>
</div>



<div role="customerModal" class="modal fade" data-backdrop="static">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">选择客户</h4>
			</div>
			<div class="modal-body">
				<div class="row">
					<div class="col-md-12">
						<form id="customerSearchForm" class="form-inline" role="form">
							<div class="form-group">
								<label class="sr-only" for="customerName"></label> 
								客户名称： <input type="text" class="form-control" name="customerName" />
								<label class="sr-only" for="certificateCd"></label> 
								证件类型： <select class="form-control" name="certificateCd" >
										<option value="">全部</option>
										<dd:options codeType="CertificateType"/>
										</select>
								<label class="sr-only" for="certificateNum"></label> 
								证件号码： <input type="text" class="form-control" name="certificateNum" />
							</div>

						

							<button type="button" class="btn btn-sm btn-purple" role="search">
								<i class="ace-icon fa fa-search"></i>查询
							</button>

							<button type="button" class="btn btn-sm btn-default" role="reset">
								<i class="ace-icon fa fa-undo"></i>重置
							</button>

						</form>

						<div class="row">
							<div class="col-md-12">
								<table role="tb_customer" class="table table-striped table-hover" id="custSelTable">
									<thead>
										<tr>
											<th><input type="checkbox" name="all_cust"> </th>
											<th>客户编号</th>
											<th>客户名称</th>
											<th>客户标识</th>
											<th>证件类型</th>
											<th>证件号码</th>
											<th>状态</th>
											<th>客户经理</th>
										</tr>
									</thead>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button role="customerSure"  type="button" class="btn btn-primary" data-loading-text="保存中...">保存</button>
				<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
			</div>
		</div>
	</div>
</div>


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
                              value="${requestScope.createUsername}" readonly="readonly" />
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