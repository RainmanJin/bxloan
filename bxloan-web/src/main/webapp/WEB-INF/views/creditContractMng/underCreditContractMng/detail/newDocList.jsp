<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
%>
<%@ include file="../../../../commons/taglibs.jsp"%>
		<!-- <h4 class="blue">
			文档管理
		</h4> -->
		<div class="space-8">
		</div>
		<div id="faq-list-5" class="panel-group accordion-style1 accordion-style2">
			<div class="step-pane active" id="step1">
				<!-- 条件搜索 -->
					<span>
						查找文档：
						<input id="query_documentName" name="query_documentName" placeholder="输入文档名称"/>
						内容类型：
						<input id="query_contentType" name="query_contentType" placeholder="输入内容类型"/>
					</span>
					<span style="float:right;">
						<button id="btn-query" class="btn btn-sm btn-purple" type="button">
							<i class="ace-icon fa fa-search">
							</i>
							查询
						</button>
						<c:if test="${type != 'check' }">
							<span id="wdSpan">
								<button type="button" class="btn btn-sm btn-success" id="addWd">
									<i class="ace-icon fa fa-plus">
									</i>
									新增文档
								</button>
							</span>
						</c:if>
						<button type="button" class="btn btn-sm btn-yellow" role="pdownloadWd">
                            <i class="ace-icon fa fa-download">
                            </i>
                            	批量下载
                        </button>
					</span>
								<table id="tbWd" class="table table-striped table-hover">
									<thead>
										<tr>
											<th>
												<input type="checkbox" id="wdcb" title='选取本页所有文档' />选择
											</th>
											<th>
												文档名称
											</th>
											<th>
												内容类型
											</th>
											<th>
												文档类型
											</th>
											<th>
												客户名称
											</th>
											<th>
												创建人
											</th>
											<th>
												创建日期
											</th>
											<th>
												关联方式
											</th>
											<th>
												操作
											</th>
										</tr>
									</thead>
									<tbody>
									</tbody>
								</table>
						<!-- /.row -->
			</div>
		</div>
		<div>
			<!-- 文档上传的专用隐藏域 -->
			<input type="hidden" id="allDocTypeField" />
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
			<input type="hidden" name="uploadPathField" id="uploadPathField" value="${uploadPath}" />
		</div>
		<!-- 文档上传 新-->
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
												<font color="red">
													*
												</font>
											</font>
											创建人：
										</label>
										<div class="col-sm-8">
											<input type="text" id="documentNameDisplay" class="form-control" readonly="readonly"
											/>
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
														数量
													</th>
													<th width="5%">
														选择
													</th>
													<th width="23%">
														上传内容
													</th>
													<th width="5%">
														数量
													</th>
													<th width="5%">
														选择
													</th>
													<th width="23%">
														上传内容
													</th>
													<th width="5%">
														数量
													</th>
												</thead>
												<tbody id="uploadTbody">
												</tbody>
											</table>
										</div>
									</div>
									<input type="hidden" name="documentType" id="documentType" />
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="accountNum">
											<font color='red'>
												<font color="red">
													*
												</font>
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
										<label class="col-sm-3 control-label no-padding-right" for="certificateCd">
											<font color='red'>
												<font color="red">
													*
												</font>
											</font>
											文档要求：
										</label>
										<div class="col-sm-8">
											<span class="block input-icon input-icon-right">
												doc、docx、xls、xlsx、pdf、jpg、rar(请确保压缩包内的文件也符合此格式要求)
											</span>
										</div>
									</div>
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>