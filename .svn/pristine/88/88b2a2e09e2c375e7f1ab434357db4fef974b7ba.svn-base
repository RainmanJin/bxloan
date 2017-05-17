 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
%>
	<%@include file="../../../../commons/taglibs.jsp" %>

 			<!-- <h4 class="blue">联系人管理</h4> -->
								<div class="space-8"></div>
								
								<input type="hidden" id="hiddenForFamilyFriendType" />
								
								<div id="faq-list-2"
									class="panel-group accordion-style1 accordion-style2">
									<div class="step-pane active" id="step5">
										<div align="right" style="margin-left:45px;">
											<span id="lxrSpan">
												<button class="btn btn-sm btn-success" id="addLxr"
													type="button">
													<i class="ace-icon fa fa-plus"> </i> 新增
												</button> </span>
										</div>
										<div class="row">
											<table id="tbLxr" class="table table-striped table-hover col-xs-12">
												<thead>
													<tr>
														<th>选择</th>
														<th>姓名</th>
														<th>与借款人关系</th>
														<th>证件类型</th>
														<th>证件号码</th>
														<th>户籍所在地</th>
														<th>手机</th>
														<th>单位电话</th>
														<th>工作单位</th>
														<th>单位地址</th>
														<th>操作</th>
													</tr>
												</thead>
												<tbody>
												</tbody>
											</table>	
										</div>
									</div>
								</div>
								<!-- faq-tab-4 end -->
  <!-- 新增联系人管理 -->
          <div id="modal-formLxr" class="modal fade" tabindex="-1"
				role="basic" aria-hidden="true" style=";" data-backdrop="static">
				<div class="modal-dialog modal-lg">
					<div class="modal-content">
						<form id="familyFriendForm" class="form-horizontal" role="form"
							method="post">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">
									&times;</button>
								<h4 class="blue bigger"></h4>
							</div>
							<div class="modal-body">
								<div class="row">
									<div class="col-xs-12">
										<input type="hidden" id="form-field-0" name="id" />
										<div class="form-group">
											<div class="ie8">
												<label class="col-sm-2 control-label no-padding-right" for="form-field-1"> 
												 <font color="red">*</font> 姓名： </label>
												 
												<div class="col-sm-4">
													<div class="clearfix">
														<input type="text" id="name" name="name" class="form-control  "  />
													</div>
												</div>
											</div>
											<div class="ie8">
											<label class="col-sm-2 control-label no-padding-right" >
											 <font color="red">*</font>
											</font> 与借款人关系： </label>
											</label>
											<div class="col-sm-4">
														<span class="block input-icon input-icon-right"
													id="familyFriendType"> <select
													name="familyFriendType" id="_familyFriendType" onchange="changeFamilyFriendType(this)" 
													class="form-control">
														<dd:options codeType="CorrelativeCustomerTypeCd" />
												</select> </span>
											</div>
											</div>
										</div>
										
										<div class="form-group">
											<div class="ie8">
												<label class="col-sm-2 control-label no-padding-right" for="form-field-1"> 
											           证件类型： 
												 </label>
												<div class="col-sm-4">
													<div class="clearfix">
														<span class="block input-icon input-icon-right"
													id="spanForCertificateTypeCd"> <select
													name="certificateTypeCd" id="certificateTypeCdForFamily"
													class="form-control" onchange="putValueInHidden(this)">
														<option></option>
														<dd:options codeType="LPCertificateType" />
												</select> </span>
													</div>
												</div>
											</div>
											<div class="ie8">
												<label class="col-sm-2 control-label no-padding-right" for="form-field-1"> 
												证件号码：
												 </label>
												<div class="col-sm-4">
													<div class="clearfix">
														<input type="text" id="certificateCd" name="certificateCd"
													class="form-control  " />
													</div>
												</div>
											</div>
										</div>
										<div class="form-group">
											<div class="ie8">
												<label class="col-sm-2 control-label no-padding-right" for="form-field-1"> 
												户籍所在地：
												 </label>
												<div class="col-sm-4">
													<div class="clearfix">
													<input type="text" id="censusRegister" name="censusRegister"
													class="form-control  " />
													</div>
												</div>
											</div>
											<div class="ie8">
												<label class="col-sm-2 control-label no-padding-right" for="form-field-1"> 
										                    教育程度：
												 </label>
												<div class="col-sm-4">
													<div class="clearfix">
													<span class="block input-icon input-icon-right"
													id="receiveEducation"> <select
													name="receiveEducation" id="receiveEducation"
													class="form-control">
													<option></option>
														<dd:options codeType="DegreeCode" />
												</select> </span>
													</div>
												</div>
											</div>
										</div>
										
										
										
										<div class="form-group">
											<div class="ie8">
												<label class="col-sm-2 control-label no-padding-right" for="form-field-1"> 
												<font color="red">*</font> 手机1：
												 </label>
												<div class="col-sm-4">
													<div class="clearfix">
													<input type="text" id="telphone" name="telphone"
													class="form-control  "  />
													</div>
												</div>
											</div>
											<div class="ie8">
												<label class="col-sm-2 control-label no-padding-right" for="form-field-1"> 
												手机2： 
												 </label>
												<div class="col-sm-4">
													<div class="clearfix">
													<input type="text" id="mobileTel" name="mobileTel"
													class="form-control  " />
													</div>
												</div>
											</div>
										</div>
										<div class="form-group" id="add-schoolName">
										    <div class="ie8">
												<label class="col-sm-2 control-label no-padding-right" for="form-field-1"> 
												 学校名称：
												 </label>
												<div class="col-sm-4">
													<div class="clearfix">
													<input type="text" id="school" name="school"
													class="form-control  " />
													</div>
												</div>
											</div>
											 <div class="ie8">
												<label class="col-sm-2 control-label no-padding-right" for="form-field-1"> 
												 学校住地：
												 </label>
												<div class="col-sm-4">
													<div class="clearfix">
													<input type="text" id="schoolAddress" name="schoolAddress"
													class="form-control  " />
													</div>
												</div>
											</div>
										</div>
										<div class="form-group">
										    <div class="ie8">
												<label class="col-sm-2 control-label no-padding-right" for="form-field-1"> 
												现居住地：
												 </label>
												<div class="col-sm-4">
													<div class="clearfix">
													<input type="text" id="nowLiveAddress" name="nowLiveAddress"
													class="form-control  " />
													</div>
												</div>
											</div>
											  <div class="ie8">
												<label class="col-sm-2 control-label no-padding-right" for="form-field-1"> 
												工作单位： 
												 </label>
												<div class="col-sm-4">
													<div class="clearfix">
													<input type="text" id="workCompany" name="workCompany"
													class="form-control  " />
													</div>
												</div>
											</div>
										</div>
										<div class="form-group">
										    <div class="ie8">
												<label class="col-sm-2 control-label no-padding-right" for="form-field-1"> 
												单位地址： 
												 </label>
												<div class="col-sm-4">
													<div class="clearfix">
													<input type="text" id="companyAddress" name="companyAddress"
													class="form-control  "  />
													</div>
												</div>
											</div>
											<div class="ie8">
												<label class="col-sm-2 control-label no-padding-right" for="form-field-1"> 
												职务：
												 </label>
												<div class="col-sm-4">
													<div class="clearfix">
													<input type="text" id="metier" name="metier"
													class="form-control  "  />
													</div>
												</div>
											</div>
										</div>
										
										
										<div class="form-group">
											<div class="ie8">
												<label class="col-sm-2 control-label no-padding-right" for="form-field-1"> 
												单位电话：
												 </label>
												<div class="col-sm-4">
													<div class="clearfix">
													<input type="text" id="companyTel" name="companyTel"
													class="form-control  " />
													</div>
												</div>
											</div>
											
										</div>
										
										
										
									</div>
								</div>
							</div>
							<div class="modal-footer">
								<span id="lxrSpan_add">
								<button id="add-Lxr-submit" class="btn btn-sm btn-primary"
									type="button" data-loading-text="正在提交中...">
									<i class="ace-icon fa fa-arrow-right"> </i> 提交
								</button>
								</span>
								<span id="lxrSpan_mod" style="display: none;">
								<button id="mod-Lxr-submit" class="btn btn-sm btn-primary"
									type="button" data-loading-text="正在提交中...">
									<i class="ace-icon fa fa-arrow-right"> </i> 保存
								</button>
								</span>
								<button class="btn btn-sm btn-default" data-dismiss="modal"
									type="button">
									<i class="ace-icon fa fa-times"> </i> 取消
								</button>
							</div>
						</form>
					</div>
				</div>
			</div>
			