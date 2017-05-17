<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
%>
	<%@include file="../../../../commons/taglibs.jsp" %>
	<h4 class="blue">
		<!-- 联保体协议信息 -->
	</h4>
	<div id="faq-list-1" class="panel-group accordion-style1 accordion-style2">
		<div class="step-content pos-rel" id="step-container">
			<div class="step-pane active" id="step1">
				<form class="form-horizontal" id="form-basicInfo" onsubmit="return false;">
					<div class="col-xs-12">
						<input type="hidden" name="id" />
						<input type="hidden" name="unStatus" />
						<div class="form-group">
							<label class="col-xs-12 col-sm-2 control-label no-padding-right">
								联保体协议编号:
							</label>
							<div class="col-xs-12 col-sm-3">
								<span class="block input-icon input-icon-right">
									<input type="text" name="unGuId" class="form-control required" readonly="readonly"
									/>
								</span>
							</div>
							<div class="help-block col-xs-12 col-sm-reset inline">
							</div>
							<label class="col-xs-12 col-sm-2 control-label no-padding-right">
								联保体小组名称:
							</label>
							<div class="col-xs-12 col-sm-3">
								<span class="block input-icon input-icon-right">
									<input type="text" name="unGroupName"  class="form-control required" 
									 />
								</span>
							</div>
							<div class="help-block col-xs-12 col-sm-reset inline">
							</div>
						</div>
					
						<div class="form-group">
							<label class="col-xs-12 col-sm-2 control-label no-padding-right">
								联保体类型:
							</label>
							<div class="col-xs-12 col-sm-3">
								<span class="block input-icon input-icon-right">
									<select  name="unType" class="form-control required" >
									 <dd:options codeType="UnType"/>
									</select>
								</span>
							</div>
							<div class="help-block col-xs-12 col-sm-reset inline">
							</div>
							<label class="col-xs-12 col-sm-2 control-label no-padding-right">
								保证方式:
							</label>
							<div class="col-xs-12 col-sm-3">
								<span class="block input-icon input-icon-right">
									<select  name="guaranWay" class="form-control required" >
									 <dd:options codeType="CdsGuarantMode"/>
									</select>
								</span>
							</div>
							<div class="help-block col-xs-12 col-sm-reset inline">
							</div>
						</div>
					
						<div class="form-group">
							<label class="col-xs-12 col-sm-2 control-label no-padding-right">
								联保体成员数量:
							</label>
							<div class="col-xs-12 col-sm-3">
								<span class="block input-icon input-icon-right">
									<input type="text" name="customerQuantity" class="form-control required" 
									/>
								</span>
							</div>
							<div class="help-block col-xs-12 col-sm-reset inline">
							</div>
							<label class="col-xs-12 col-sm-2 control-label no-padding-right">
								联保协议起始时间:
							</label>
							<div class="col-xs-12 col-sm-3">
								<span class="block input-icon input-icon-right">
									<input type="text" name="begDateStr"  class="form-control required"  data-date-format="yyyy-mm-dd"
									 />
								</span>
							</div>
							<div class="help-block col-xs-12 col-sm-reset inline">
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-xs-12 col-sm-2 control-label no-padding-right">
								联保协议到期时间:
							</label>
							<div class="col-xs-12 col-sm-3">
								<span class="block input-icon input-icon-right">
									<input type="text" name="endDateStr" class="form-control required"  data-date-format="yyyy-mm-dd"
									/>
								</span>
							</div>
							<div class="help-block col-xs-12 col-sm-reset inline">
							</div>
							<label class="col-xs-12 col-sm-2 control-label no-padding-right">
								联保协议签订日期:
							</label>
							<div class="col-xs-12 col-sm-3">
								<span class="block input-icon input-icon-right">
									<input type="text" name="unSigeDateStr"  class="form-control required"  data-date-format="yyyy-mm-dd"
									 />
								</span>
							</div>
							<div class="help-block col-xs-12 col-sm-reset inline">
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-xs-12 col-sm-2 control-label no-padding-right">
								客户经理:
							</label>
							<div class="col-xs-12 col-sm-3">
								<span class="block input-icon input-icon-right">
								<div class="input-group">
									<input type="text" name="custManager" class="form-control required bgwhite" readonly="readonly" style="background-color:#FFF!important"
									/>
									<span class="input-group-btn">
										<button role="custManager_btn" class="btn btn-sm btn-yellow"
										type="button">
											<i class="ace-icon fa fa-eye"></i>
										</button> 
									</span>
								</div>
									<input type="hidden" name="managerId" />
								</span>
							</div>
							<div class="help-block col-xs-12 col-sm-reset inline">
							</div>
							<label class="col-xs-12 col-sm-2 control-label no-padding-right">
								登记日期:
							</label>
							<div class="col-xs-12 col-sm-3">
								<span class="block input-icon input-icon-right">
									<input type="text" name="segiDateStr" class="form-control "  data-date-format="yyyy-mm-dd" 
									/>
								</span>
							</div>
							<div class="help-block col-xs-12 col-sm-reset inline">
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-xs-12 col-sm-2 control-label no-padding-right">
								登记人:
							</label>
							<div class="col-xs-12 col-sm-3">
								<span class="block input-icon input-icon-right">
									<input type="text" name="segiMan"  class="form-control" readonly="readonly"/>
								</span>
							</div>
							<div class="help-block col-xs-12 col-sm-reset inline">
							</div>
							<label class="col-xs-12 col-sm-2 control-label no-padding-right">
								登记机构:
							</label>
							<div class="col-xs-12 col-sm-3">
								<span class="block input-icon input-icon-right">
									<input type="text" name="segiOrgName"  class="form-control"  readonly="readonly"/>
									 <input type="hidden" name="segiOrg" />
								</span>
							</div>
							<div class="help-block col-xs-12 col-sm-reset inline">
							</div>
						</div>
						<hr/>
						<div class="wizard-actions" style=" text-align:center;">
							<span id="basic_btnSpan">
								<c:if test="${isEdit }">
									<button class="btn btn-sm btn-primary" role="saveBasicInfo" type="submit" data-loading-text="正在保存">
										<i class="ace-icon fa fa-floppy-o">
										</i>
										保存
									</button>
								</c:if>
								<button class="btn btn-sm btn-pre" id="goBackButton" data-last="Finish"
									type="button" onclick="javascript:history.go(-1);">
										<i class="ace-icon fa fa-chevron-left">
										</i>
										返回
								</button>
							</span>
						</div>
						
					</div>
				</form>
			</div>
			<!-- step pane -->
		</div>
		<!-- step content -->
	</div>
	
	