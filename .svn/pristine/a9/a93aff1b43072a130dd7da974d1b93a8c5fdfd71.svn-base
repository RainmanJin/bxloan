<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
%>
	<%@include file="../../../../commons/taglibs.jsp" %>
		<!-- <h4 class="blue">
			联系人管理
		</h4> -->
		<div class="space-8">
		</div>
		
		<input type="hidden" id="hiddenForFamilyFriendType" />
		
		<div id="faq-list-2" class="panel-group accordion-style1 accordion-style2">
			<div class="step-pane active" id="step5">
				<div align="right" style="margin-left:45px;">
					<span id="lxrSpan">
						<button class="btn btn-sm btn-success" id="addLxr" type="button">
							<i class="ace-icon fa fa-plus">
							</i>
							新增
						</button>
					</span>
				</div>
								<table id="tbLxr" class="table table-striped table-hover">
									<thead>
										<tr>
											<th>
												选择
											</th>
											<th>
												联系人姓名
											</th>
											<th>
												类型
											</th>
											<th>
												与借款人关系
											</th>
											<th>
												手机
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
		
		
		
		
		 <!-- 新增联系人管理 -->
            <div id="add-modal-formLxr" class="modal fade" tabindex="-1" role="basic"
            aria-hidden="true" style=";" data-backdrop="static">
              <div class="modal-dialog">
                <div class="modal-content">
                  <form id="addFamilyFriendForm" class="form-horizontal" role="form" method="post">
                    <div class="modal-header">
                      <button type="button" class="close" data-dismiss="modal">
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
                            <label class="col-sm-3 control-label no-padding-right" for="familyFriendType">
                              <font color='red'>
                                <font color="red">*</font>
                              </font>
                              	联系人类型：
                            </label>
                            <div class="col-sm-8">
                              <span class="block input-icon input-icon-right" id="fftype">
                                <select name="relationsType" id="add-relationsType" class="form-control">
                                  <dd:options codeType="RelationsType" />
                                </select>
                              </span>
                            </div>
                          </div>
                          <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="familyFriendType">
                              <font color='red'>
                                <font color="red">*</font>
                              </font>
                              	是否家庭成员：
                            </label>
                            <div class="col-xs-12 col-sm-3">
								<span class="block input-icon input-icon-right">
									<input name="familyMembers" type="radio" class="ace" value="1" />
									<span class="lbl ">
										是
									</span>
									<input name="familyMembers" type="radio" class="ace" value="2" />
									<span class="lbl">
										否
									</span>
								</span>
							</div>
                          </div>
                          <div id="add-formContent">
                          </div>
                          <div id="add-lxrForm-nh" style="display:none;">
                           <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="familyFriendType">
                              <!-- <font color='red'>
                                <font color="red">*</font>
                              </font> -->
                              	年龄：
                            </label>
                            <div class="col-sm-8">
                              <span class="block input-icon input-icon-right" >
                                <input name="age" type='text' class="form-control" />
                              </span>
                            </div>
                          </div>
                          <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="familyFriendType">
                              <!-- <font color='red'>
                                <font color="red">*</font>
                              </font> -->
                              	状态：
                            </label>
                            <div class="col-sm-8">
                              <span class="block input-icon input-icon-right" >
                                <select name="personStatus"  class="form-control">
                                  <dd:options codeType="FamiliyMemberStatus" />
                                </select>
                              </span>
                            </div>
                          </div>
                          <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="familyFriendType">
                              <!-- <font color='red'>
                                <font color="red">*</font>
                              </font> -->
                              	工作单位/状况：
                            </label>
                            <div class="col-sm-8">
                              <span class="block input-icon input-icon-right" >
                                <input name="workCompany" type='text' class="form-control" />
                              </span>
                            </div>
                          </div>
                          <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="familyFriendType">
                              <!-- <font color='red'>
                                <font color="red">*</font>
                              </font> -->
                              	月收入：
                            </label>
                            <div class="col-sm-8">
                              <span class="block input-icon input-icon-right" >
                                <input name="monthlyIncome" type='text' class="form-control" />
                              </span>
                            </div>
                          </div>
                          <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="familyFriendType">
                              <!-- <font color='red'>
                                <font color="red">*</font>
                              </font> -->
                              	教育程度：
                            </label>
                            <div class="col-sm-8">
                              <span class="block input-icon input-icon-right" >
                                <select name="degreeCd"  class="form-control">
                                  <dd:options codeType="DegreeCode" />
                                </select>
                              </span>
                            </div>
                          </div>
                          </div>
                        </div>
                      </div>
                    </div>
                    <div class="modal-footer">
                      <button id="add-Lxr-submit" class="btn btn-sm btn-primary" type="submit"
                      data-loading-text="正在提交中...">
                        <i class="ace-icon fa fa-arrow-right">
                        </i>
                        	提交
                      </button>
                      <button class="btn btn-sm btn-default" data-dismiss="modal" type="button">
                        <i class="ace-icon fa fa-times">
                        </i>
                        	取消
                      </button>
                    </div>
                  </form>
                </div>
              </div>
            </div>
            <!-- 替换表单 -->
            <!-- 配偶 -->
            <div id="peiou" style="display:none;">
              <input type="hidden" name="familyFriendType" id="add-familyFriendType"
              value="1" />
              <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="name">
                  <font color='red'>
                    <font color="red">*</font>
                  </font>
                  	配偶姓名：
                </label>
                <div class="col-sm-8">
                  <input type="text" id="name" name="name" class="form-control  "
                  />
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="certificateCd">
                  <font color='red'>
                    <font color="red">*</font>
                  </font>
                  	证件类型：
                </label>
                <div class="col-sm-8">
                  <select id="certificateTypeCd" name="certificateTypeCd" class="form-control " onchange="javascript:$('#hiddenForFamilyCertificateType').val(this.value);">
                  	<dd:options codeType="LPCertificateType"/>
                  </select>
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="certificateCd">
                  <font color='red'>
                    <font color="red">*</font>
                  </font>
                  	证件证号：
                </label>
                <div class="col-sm-8">
                  <input type="text" id="certificateCd" name="certificateCd" class="form-control  "
                 />
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="censusRegister">
                  <font color='red'>
                    <font color="red">*</font>
                  </font>
                  	户籍所在地：
                </label>
                <div class="col-sm-8">
                  <input type="text" id="censusRegister" name="censusRegister" class="form-control  "
                   />
                </div>
              </div>
              <%-- <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="receiveEducation">
                  	教育程度：
                </label>
                <div class="col-sm-8">
                  <span class="block input-icon input-icon-right" id="receiveEducation">
                    <select name="receiveEducation" id="receiveEducation" class="form-control">
                      <dd:options codeType="DegreeCode" />
                    </select>
                  </span>
                </div>
              </div> --%>
              <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="telphone">
                  <font color='red'>
                    <font color="red">*</font>
                  </font>
                  	手机：
                </label>
                <div class="col-sm-8">
                  <input type="text" id="telphone" name="telphone" class="form-control  "
                   />
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="mobileTel">
                  	手机2：
                </label>
                <div class="col-sm-8">
                  <input type="text" id="mobileTel" name="mobileTel" class="form-control  "
                 />
                </div>
              </div>
              <div class="form-group" name="workcompany_div">
                <label class="col-sm-3 control-label no-padding-right" for="workCompany">
                  	单位名称：
                </label>
                <div class="col-sm-8">
                  <input type="text" id="workCompany" name="workCompany" class="form-control  "
                  />
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="metier">
                  	职务：
                </label>
                <div class="col-sm-8">
                  <input type="text" id="metier" name="metier" class="form-control  " 
                  />
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="companyTel">
                  	单位电话：
                </label>
                <div class="col-sm-8">
                  <input type="text" id="companyTel" name="companyTel" class="form-control  "
                 />
                </div>
              </div>
            </div>
            <!-- 配偶end -->
            <!-- 亲属 -->
            <div id="qinshu" style="display:none;">
              <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="name">
                  <font color='red'>
                    <font color="red">*</font>
                  </font>
                  	亲属姓名：
                </label>
                <div class="col-sm-8">
                  <input type="text" id="name" name="name" class="form-control  " 
                  />
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="familyFriendType">
                  <font color='red'>
                    <font color="red">*</font>
                  </font>
                  	与借款人关系：
                </label>
                <div class="col-sm-8">
                  <span class="block input-icon input-icon-right" id="familyFriendType">
                    <select name="familyFriendType" id="familyFriendType" class="form-control">
                      <dd:options codeType="RelativesType" />
                    </select>
                  </span>
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="telphone">
                  <font color='red'>
                    <font color="red">*</font>
                  </font>
                  	手机：
                </label>
                <div class="col-sm-8">
                  <input type="text" id="telphone" name="telphone" class="form-control  "
                   />
                </div>
              </div>
            </div>
            <!-- 非亲属 -->
            <div id="feiqinshu" style="display:none;">
              <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="name">
                  <font color='red'>
                    <font color="red">*</font>
                  </font>
                  	非亲属姓名：
                </label>
                <div class="col-sm-8">
                  <input type="text" id="name" name="name" class="form-control  " 
                  />
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="familyFriendType">
                  <font color='red'>
                    <font color="red">*</font>
                  </font>
                  	与借款人关系：
                </label>
                <div class="col-sm-8">
                  <span class="block input-icon input-icon-right" id="familyFriendType">
                    <select name="familyFriendType" id="familyFriendType" class="form-control">
                      <dd:options codeType="UnRelativesType" />
                    </select>
                  </span>
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-3 control-label no-padding-right" for="telphone">
                  <font color='red'>
                    <font color="red">*</font>
                  </font>
                  	手机：
                </label>
                <div class="col-sm-8">
                  <input type="text" id="telphone" name="telphone" class="form-control  "
                   />
                </div>
              </div>
            </div>
            <!-- 其他家庭成员 -->
            <!-- 修改联系人管理 -->
            <div id="mod-modal-formLxr" class="modal fade" tabindex="-1" role="basic"
            aria-hidden="true" style=";" data-backdrop="static">
              <div class="modal-dialog">
                <div class="modal-content">
                  <form id="modFamilyFriendForm" class="form-horizontal" role="form" method="post">
                    <div class="modal-header">
                      <button type="button" class="close" data-dismiss="modal">
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
                            <label class="col-sm-3 control-label no-padding-right" for="familyFriendType">
                              <font color='red'>
                                <font color="red">*</font>
                              </font>
                              	联系人类型：
                            </label>
                            <div class="col-sm-8">
                              <span class="block input-icon input-icon-right" id="fftype">
                                <select name="relationsType" id="mod-relationsType" class="form-control"
                                disabled="disabled">
                                  <dd:options codeType="RelationsType" />
                                </select>
                                <input name="relationsType" type='hidden' />
                              </span>
                            </div>
                          </div>
                          <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="familyFriendType">
                              <font color='red'>
                                <font color="red">*</font>
                              </font>
                              	是否家庭成员：
                            </label>
                            <div class="col-xs-12 col-sm-3">
								<span class="block input-icon input-icon-right">
									<input name="familyMembers" type="radio" class="ace" value="1" />
									<span class="lbl ">
										是
									</span>
									<input name="familyMembers" type="radio" class="ace" value="2" />
									<span class="lbl">
										否
									</span>
								</span>
							</div>
                          </div>
                          <div id="mod-formContent">
                          </div>
                          <div id="mod-lxrForm-nh" style="display:none;">
                           <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="familyFriendType">
                              <!-- <font color='red'>
                                <font color="red">*</font>
                              </font> -->
                              	年龄：
                            </label>
                            <div class="col-sm-8">
                              <span class="block input-icon input-icon-right" >
                                <input name="age" type='text' class="form-control" />
                              </span>
                            </div>
                          </div>
                          <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="familyFriendType">
                              <!-- <font color='red'>
                                <font color="red">*</font>
                              </font> -->
                              	状态：
                            </label>
                            <div class="col-sm-8">
                              <span class="block input-icon input-icon-right" >
                                <select name="personStatus"  class="form-control">
                                  <dd:options codeType="FamiliyMemberStatus" />
                                </select>
                              </span>
                            </div>
                          </div>
                          <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="familyFriendType">
                              <!-- <font color='red'>
                                <font color="red">*</font>
                              </font> -->
                              	工作单位/状况：
                            </label>
                            <div class="col-sm-8">
                              <span class="block input-icon input-icon-right" >
                                <input name="workCompany" type='text' class="form-control" />
                              </span>
                            </div>
                          </div>
                          <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="familyFriendType">
                              <!-- <font color='red'>
                                <font color="red">*</font>
                              </font> -->
                              	月收入：
                            </label>
                            <div class="col-sm-8">
                              <span class="block input-icon input-icon-right" >
                                <input name="monthlyIncome" type='text' class="form-control" />
                              </span>
                            </div>
                          </div>
                          <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="familyFriendType">
                              <!-- <font color='red'>
                                <font color="red">*</font>
                              </font> -->
                              	教育程度：
                            </label>
                            <div class="col-sm-8">
                              <span class="block input-icon input-icon-right" >
                                <select name="degreeCd"  class="form-control">
                                  <dd:options codeType="DegreeCode" />
                                </select>
                              </span>
                            </div>
                          </div>
                          </div>
                        </div>
                      </div>
                    </div>
                    <div class="modal-footer">
                      <span id="modLxrSpan">
                        <button id="mod-Lxr-submit" class="btn btn-sm btn-primary" type="submit"
                        data-loading-text="正在提交中...">
                          <i class="ace-icon fa fa-arrow-right">
                          </i>
                         	 提交
                        </button>
                      </span>
                      <button class="btn btn-sm btn-default" data-dismiss="modal" type="button">
                        <i class="ace-icon fa fa-times">
                        </i>
                        	取消
                      </button>
                    </div>
                  </form>
                </div>
              </div>
            </div>
            
		
		<!-- faq-tab-4 end -->