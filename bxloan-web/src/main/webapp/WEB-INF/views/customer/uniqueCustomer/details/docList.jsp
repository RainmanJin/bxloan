<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
%>
<%@include file="../../../../commons/taglibs.jsp" %>
    <!-- <h4 class="blue">
        文档管理
    </h4> -->
    <div class="space-8">
    </div>
    <div id="faq-list-5" class="panel-group accordion-style1 accordion-style2">
        <div class="step-pane active" id="step1">
            <!-- 条件搜索 -->
            <div class="col-xs-12">
                <span>
                    	查找文档：
                    <input id="query_documentName" name="query_documentName" placeholder="输入文档名称"
                    />
                  	  内容类型：
						<input id="query_contentType" name="query_contentType" placeholder="输入内容类型"
						/>
                </span>
                <span style="float:right;">
                    <button id="btn-query" class="btn btn-sm btn-purple" type="button">
                        <i class="ace-icon fa fa-search">
                        </i>
                        	查询
                    </button>
                  <span id="wdSpan">
                        <button type="button" class="btn btn-sm btn-success" id="addWd">
                            <i class="ace-icon fa fa-plus">
                            </i>
                            	新增文档
                        </button>
                    </span> 
                     <button type="button" class="btn btn-sm btn-yellow" role="pdownloadWd">
                            <i class="ace-icon fa fa-download">
                            </i>
                            	批量下载
                        </button>
                </span>
            </div>
                	<%--  <table id="tb_doc_selector" name="selector" class="table table-striped table-hover table-bordered table-condensed" style="margin-top:10px;">
                	 	<thead>
                	 		<th width="40%">上传内容</th>
                	 		<th width="10%">操作</th>
                	 		<th width="40%">上传内容</th>
                	 		<th width="10%">操作</th>
                	 	</thead>
                	 	<tbody>
                	        <tr>
                	 			<td>身份证-正面</td><td><button type='button' role='uploadDoc' data-type = '1'  class='btn btn-xs btn-info' title='上传'><i class='ace-icon fa fa-upload'></i></button></button></td>
                	 			<td>身份证-反面</td><td><button type='button' role='uploadDoc' data-type = '28'  class='btn btn-xs btn-info' title='上传'><i class='ace-icon fa fa-upload'></i></button></button></td>
                	 		</tr>
                	 		<tr>
                	 			<td>户口本</td><td><button type='button' role='uploadDoc' data-type = '2'  class='btn btn-xs btn-info' title='上传'><i class='ace-icon fa fa-upload'></i></button></button></td>
                	 			<td>个人征信报告</td><td><button type='button' role='uploadDoc' data-type = '4'  class='btn btn-xs btn-info' title='上传'><i class='ace-icon fa fa-upload'></i></button></button></td>
                	 		</tr>
                	 		<tr>
                	 			<td>单身证明</td><td><button type='button' role='uploadDoc' data-type = '29'  class='btn btn-xs btn-info' title='上传'><i class='ace-icon fa fa-upload'></i></button></button></td>
                	 			<td>结婚证</td><td><button type='button' role='uploadDoc' data-type = '3'  class='btn btn-xs btn-info' title='上传'><i class='ace-icon fa fa-upload'></i></button></button></td>
                	 		</tr>
                	 		<tr>
                	 		    <td>结清证明/还款证明</td><td><button type='button' role='uploadDoc' data-type = '5'  class='btn btn-xs btn-info' title='上传'><i class='ace-icon fa fa-upload'></i></button></button></td>
                	 			<td>购房合同+发票</td><td><button type='button' role='uploadDoc' data-type = '15'  class='btn btn-xs btn-info' title='上传'><i class='ace-icon fa fa-upload'></i></button></button></td>
                	 		</tr>
                	 		<c:if test="${requestScope.individual.employmentType == '1' }"> 
                	 		<tr>
                	 			<td>个人银行流水</td><td><button type='button' role='uploadDoc' data-type = '12'  class='btn btn-xs btn-info' title='上传'><i class='ace-icon fa fa-upload'></i></button></button></td>
                	 			<td>工作证</td><td><button type='button' role='uploadDoc' data-type = '30'  class='btn btn-xs btn-info' title='上传'><i class='ace-icon fa fa-upload'></i></button></button></td>
                	 		</tr>
                	 		<tr>
                	 			<td>劳动合同</td><td><button type='button' role='uploadDoc' data-type = '31'  class='btn btn-xs btn-info' title='上传'><i class='ace-icon fa fa-upload'></i></button></button></td>
                	 			<td>收入证明</td><td><button type='button' role='uploadDoc' data-type = '32'  class='btn btn-xs btn-info' title='上传'><i class='ace-icon fa fa-upload'></i></button></button></td>
                	 		</tr>
                	 		</c:if>
                	 		
                	 		<c:if test="${requestScope.individual.employmentType != '1' }"> 
                	 		<tr>
                	 			<td>营业执照</td><td><button type='button' role='uploadDoc' data-type = '6'  class='btn btn-xs btn-info' title='上传'><i class='ace-icon fa fa-upload'></i></button></button></td>
                	 			<td>特许经营许可证</td><td><button type='button' role='uploadDoc' data-type = '7'  class='btn btn-xs btn-info' title='上传'><i class='ace-icon fa fa-upload'></i></button></button></td>
                	 		</tr>
                	 		</c:if>
                	 		
                	 		<c:if test="${requestScope.individual.employmentType == '3' }"> 
                	 		<tr>
                	 			<td>租赁合同</td><td><button type='button' role='uploadDoc' data-type = '8'  class='btn btn-xs btn-info' title='上传'><i class='ace-icon fa fa-upload'></i></button></button></td>
                	 			<td>产权权属证明材料</td><td><button type='button' role='uploadDoc' data-type = '9'  class='btn btn-xs btn-info' title='上传'><i class='ace-icon fa fa-upload'></i></button></button></td>
                	 		</tr>
                	 		<tr>
                	 			<td>交租凭证</td><td><button type='button' role='uploadDoc' data-type = '10'  class='btn btn-xs btn-info' title='上传'><i class='ace-icon fa fa-upload'></i></button></button></td>
                	 			<td>物业水电煤气等凭证</td><td><button type='button' role='uploadDoc' data-type = '11'  class='btn btn-xs btn-info' title='上传'><i class='ace-icon fa fa-upload'></i></button></button></td>
                	 		</tr>
                	 		<tr>
                	 			<td>常用银行卡流水</td><td><button type='button' role='uploadDoc' data-type = '12'  class='btn btn-xs btn-info' title='上传'><i class='ace-icon fa fa-upload'></i></button></button></td>
                	 			<td>业务合同/订单</td><td><button type='button' role='uploadDoc' data-type = '13'  class='btn btn-xs btn-info' title='上传'><i class='ace-icon fa fa-upload'></i></button></button></td>
                	 		</tr>
                	 		</c:if>
                	 		
                	 		<c:if test="${requestScope.individual.employmentType == '2' }"> 
                	 	    <tr>
                	 			<td>土地承包证</td><td><button type='button' role='uploadDoc' data-type = '21'  class='btn btn-xs btn-info' title='上传'><i class='ace-icon fa fa-upload'></i></button></button></td>
                	 			<td>土地承包/承租协议</td><td><button type='button' role='uploadDoc' data-type = '22'  class='btn btn-xs btn-info' title='上传'><i class='ace-icon fa fa-upload'></i></button></button></td>
                	 		</tr>
                	 		<tr>
                	 			<td>公司章程/合伙经营协议</td><td><button type='button' role='uploadDoc' data-type = '23'  class='btn btn-xs btn-info' title='上传'><i class='ace-icon fa fa-upload'></i></button></button></td>
                	 			<td>验资报告</td><td><button type='button' role='uploadDoc' data-type = '24'  class='btn btn-xs btn-info' title='上传'><i class='ace-icon fa fa-upload'></i></button></button></td>
                	 		</tr>
                	 		<tr>
                	 			<td>企业变更登记说明</td><td><button type='button' role='uploadDoc' data-type = '25'  class='btn btn-xs btn-info' title='上传'><i class='ace-icon fa fa-upload'></i></button></button></td>
                	 			<td>其他证明材料</td><td><button type='button' role='uploadDoc' data-type = '26'  class='btn btn-xs btn-info' title='上传'><i class='ace-icon fa fa-upload'></i></button></button></td>
                	 		</tr> 
                	 		</c:if>
                	 		
                	 		<tr>
                	 			<td>车辆行驶证/登记证</td><td><button type='button' role='uploadDoc' data-type = '16'  class='btn btn-xs btn-info' title='上传'><i class='ace-icon fa fa-upload'></i></button></button></td>
                	 			<td>其他财产信息</td><td><button type='button' role='uploadDoc' data-type = '17'  class='btn btn-xs btn-info' title='上传'><i class='ace-icon fa fa-upload'></i></button></button></td>
                	 		</tr>
                	 		<tr>
                	 			<td>租赁合同/交租凭证</td><td><button type='button' role='uploadDoc' data-type = '18'  class='btn btn-xs btn-info' title='上传'><i class='ace-icon fa fa-upload'></i></button></button></td>
                	 			<td>物业水电煤气等收据</td><td><button type='button' role='uploadDoc' data-type = '19'  class='btn btn-xs btn-info' title='上传'><i class='ace-icon fa fa-upload'></i></button></button></td>
                	 		</tr>
                	 		<tr>
                	 			<td>家庭及工作场所影像资料</td><td><button type='button' role='uploadDoc' data-type = '20'  class='btn btn-xs btn-info' title='上传'><i class='ace-icon fa fa-upload'></i></button></button></td>
                	 			<td>其他</td><td><button type='button' role='uploadDoc' data-type = '27'  class='btn btn-xs btn-info' title='上传'><i class='ace-icon fa fa-upload'></i></button></button></td>
                	 		</tr> 
                	 		
                	 		
                	 		 
                	 		<c:if test="${requestScope.individual.employmentType == '3' }"> 
                	 		<tr>
                	 			<td>进销货单据或发票</td><td><button type='button' role='uploadDoc' data-type = '14'  class='btn btn-xs btn-info' title='上传'><i class='ace-icon fa fa-upload'></i></button></button></td>
                	 		</tr>
                	 		</c:if>
                	 		
                	 	</tbody>
                	 </table> --%>
                    <div style="" align="right">
                    </div>
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