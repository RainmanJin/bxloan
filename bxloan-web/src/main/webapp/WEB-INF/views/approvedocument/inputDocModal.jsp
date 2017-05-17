<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
%>
	<%@ taglib uri="http://www.coamc-tech.com/taglibs/datadict" prefix="dd"
	%>
		<!-- 相关文档的模式窗口 -->
		<div id="showDocuments" class="modal fade" tabindex="-1" role="basic"
		aria-hidden="true" style="z-index: 1041;" data-backdrop="static">
			<div class="modal-dialog modal-full" style="width: 1100px;">
				<div class="modal-content">
					<div class="modal-body">
						<div class="col-xs-12 col-sm-12 widget-container-col ui-sortable">
							<div class="page-header" align="left">
								<h1>
									审批流程
									<small>
										<i class="ace-icon fa fa-angle-double-right">
										</i>
										相关文档查询
									</small>
								</h1>
							</div>
							<!-- 条件搜索 -->
							<div class="row" id="condSearchDiv" style="margin: auto; height: 40px;">
								<div class="col-xs-12" align='left'>
									<form id="document_search_form">
										查找：
										<span>
											文件名：
											<input id="query_documentName" name="query_documentName" placeholder="输入文档名称"
											/>
											内容类型：
											<input id="query_contentType" name="query_contentType" placeholder="输入内容类型"
											/>
										</span>
										<button id="btn-query" class="btn btn-sm btn-purple" type="button">
											<i class="ace-icon fa fa-search">
											</i>
											查询
										</button>
										 <button type="button" class="btn btn-sm btn-yellow" role="pdownloadWd">
                           				 <i class="ace-icon fa fa-download">
                            			</i>
                            				批量下载
                        				</button>
									</form>
								</div>
							</div>
							<!-- /.row -->
							<div class="row">
								<div class="col-xs-12">
									<div class="row">
										<div class="col-xs-12">
											<table id="tbWd" class="table table-striped table-hover">
												<thead>
													<tr>
														<th style="white-space:nowrap;">
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
										</div>
									</div>
								</div>
							</div>
							<!-- /.row -->
						</div>
						<div class="text-center" style="margin-top: 10px;">
							<button type="button" class="btn btn-default" data-dismiss="modal">
								关闭
							</button>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div id="input_doc_modal" class="modal" tabindex="-1" data-backdrop="static">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<form id="input_doc_form" action="" class="form-horizontal">
						<input type="hidden" name="partyId" value="" />
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">
								&times;
							</button>
							<h4 class="blue bigger">
								<i class='ace-icon fa fa-plus'>
								</i>
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
												<input type="text" id="custName" class="form-control"  readonly="readonly">
											</span>
										</div>
										<div class="help-block col-xs-6 col-sm-reset inline">
										</div>
									</div>
									<input type="hidden" name="documentType" id="documentType" />
									<div class="form-group">
										<label class="col-xs-3 control-label no-padding-right">
											<font color='red'>
												*
											</font>
											内容类型：
										</label>
										<div class="col-xs-7">
										<table id="tb_doc_selector" name="selector" class="table table-striped table-hover table-bordered table-condensed"
										style="margin-top:10px;">
											<thead>
												<th width="15%">
													选择
												</th>
												<th width="50%">
													上传内容
												</th>
												<th>
													数量
												</th>
											</thead>
											<tbody id="uploadTbody">
											</tbody>
										</table>
										</div>
									</div>
									<div class="form-group">
										<label class="col-xs-3 control-label no-padding-right">
											<font color='red'>
												*
											</font>
											选择文件：
										</label>
										<div class="col-xs-7 ">
											<span class="block input-icon input-icon-right">
												<input type="file" id="uploadFile" name="uploadFile" />
											</span>
										</div>
										<button id="btn_doc_upload" role="uploadBtn" class="btn btn-sm btn-info" type="button"
										data-loading-text="正在保存中...">
											<i class="ace-icon fa fa-upload">
											</i>
											上传
										</button>
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
												doc、docx、xls、xlsx、pdf、jpg、png、rar(请确保压缩包内的文件也符合此格式要求)
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