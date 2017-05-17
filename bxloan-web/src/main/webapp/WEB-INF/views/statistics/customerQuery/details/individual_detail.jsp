<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../../../commons/taglibs.jsp" %>
	

<form role="customerInfo" class="form-horizontal">
	<div id="faq-list-1" class="panel-group accordion-style1 accordion-style2">
		<div >
		<div class="panel panel-default" >
			<div class="panel-heading">
				<a href="#faq-0-1" data-parent="#faq-list-1" data-toggle="collapse" class="accordion-toggle" >
					<i class="pull-right ace-icon fa fa-chevron-down" data-icon-hide="ace-icon fa fa-chevron-down"
					data-icon-show="ace-icon fa fa-chevron-left"></i>
					<i class="ace-icon fa fa-credit-card"> </i> &nbsp; 基本信息
				</a>
			</div>
			<div class="panel-collapse collapse in" id="faq-0-1">
				<div class="panel-body">
					<div class="row">
						<div class="col-md-12">
								<div class="row">
									<div class="form-group col-md-6">
										<label class="col-md-4 control-label">
											客户编号<font color="red">*</font>
										</label>
										<div class="col-md-8">
											<button type="button" role="btn_customer" style="text-align:left; color: #478fca !important; text-shadow:none !important; background:transparent none !important; text-decoration: underline;" class="btn btn-sm form-control"></button>
										</div>
									</div>
									<div class="form-group col-md-6">
										<label class="col-md-4 control-label">
											客户类型<font color="red">*</font>
										</label>
										<div class="col-md-8">
											 <select name="partyTypeCd"  class="form-control" disabled="disabled">
												<option value=""></option>
												<dd:options codeType="CustomerType" />
											</select>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="form-group col-md-6">
										<label class="col-md-4 control-label">
											客户名称<font color="red">*</font>
										</label>
										<div class="col-md-8">
											<input type="text" name="customerName" class="form-control" readonly="readonly"/>
										</div>
									</div>
									<div class="form-group col-md-6">
										<label class="col-md-4 control-label">
											雇佣类型<font color="red">*</font>
										</label>
										<div class="col-md-8">
											<span class="block input-icon input-icon-right">
												<select name="employmentType" id="employmentType" class="form-control" disabled="disabled">
													<dd:options codeType="EmploymentType" />
												</select>
											</span>
										</div>
									</div>
										
								</div>
								<div class="row">
									<div class="form-group col-md-6">
										<label class="col-md-4 control-label">
											证件类型<font color="red">*</font>
										</label>
										<div class="col-md-8">
											<span class="block input-icon input-icon-right">
												<select name="certificateTypeCd" id="certificateTypeCd" class="form-control"
												readonly="readonly">
													<dd:options codeType="PCertificateType" />
												</select>
											</span>
										</div>
									</div>
									<div class="form-group col-md-6">
										<label class="col-md-4 control-label  ">
											证件号码<font color="red">*</font>
										</label>
										<div class="col-md-8">
											<span class="block input-icon input-icon-right">
												<input type="text" name="certificateNum" class="form-control" value="${requestScope.party.certificateNum }"
												readonly="readonly" />
											</span>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="form-group col-md-6">
										<label class="col-md-4 control-label  ">
											出生年月<font color="red">*</font>
										</label>
										<div class="col-md-8">
											<span class="block input-icon input-icon-right">
												<input type="text" id="birthday" name="birthday" class="form-control required"
													data-date-format="yyyy/mm/dd" value="${birthdayStr}" />
											</span>
										</div>
									</div>
									<div class="form-group col-md-6">
										<label class="col-md-4 control-label  ">
											性 别<font color="red">*</font>
										</label>
										<div class="col-md-8">
											<span class="block input-icon input-icon-right">
												<select name="genderCd" id="genderCd" class="form-control">
													<dd:options codeType="SexTypeCD" />
												</select>
											</span>
										</div>
									</div>
								</div>
									
									
								<div class="row">
									<div class="form-group col-md-6">
										<label class="col-md-4 control-label  ">
											教育程度<font color="red">*</font>
										</label>
										<div class="col-md-8">
											<span class="block input-icon input-icon-right">
												<select name="degreeCd" id="degreeCd" class="form-control">
													<dd:options codeType="DegreeCode" />
												</select>
											</span>
										</div>
									</div>
									<div class="form-group col-md-6">
										<label class="col-md-4 control-label  ">
											手机1<font color="red">*</font>
										</label>
										<div class="col-md-8">
											<span class="block input-icon input-icon-right">
												<input type="text" id="mobileTel" name="mobileTel" class="form-control  "
												value="" />
											</span>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="form-group col-md-6">
										<label class="col-md-4 control-label  ">
											手机2
										</label>
										<div class="col-md-8">
											<span class="block input-icon input-icon-right">
												<input type="text" id="telphone2" name="telphone2" class="width-100" value=""
												/>
											</span>
										</div>
									</div>
									<div class="form-group col-md-6">
										<label class="col-md-4 control-label  ">
											QQ
										</label>
										<div class="col-md-8">
											<span class="block input-icon input-icon-right">
												<input type="text" id="qq" name="qq" class="width-100" value="" />
											</span>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="form-group col-md-6">
										<label class="col-md-4 control-label  ">
											微信
										</label>
										<div class="col-md-8">
											<span class="block input-icon input-icon-right">
												<input type="text" id="microAccount" name="microAccount" class="width-100"
												value="" />
											</span>
										</div>
									</div>
									<div class="form-group col-md-6">
										<label class="col-md-4 control-label  ">
											Email
										</label>
										<div class="col-md-8">
											<span class="block input-icon input-icon-right">
												<input type="text" id="email" name="email" class="width-100" value=""
												/>
											</span>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="form-group col-md-6">
										<label class="col-md-4 control-label  ">
											子女人数
										</label>
										<div class="col-md-8">
											<span class="block input-icon input-icon-right">
											<div class="input-group">
												<input type="text" id="chiledNum" name="chiledNum" class="width-100" value=""
												/>
												<span class="input-group-addon">人</span>
											</div>
											</span>
										</div>
									</div>
									<div class="form-group col-md-6">
										<label class="col-md-4 control-label  ">
											婚姻状况<font color="red">*</font>
										</label>
										<div class="col-md-8">
											<span class="block input-icon input-icon-right">
												<select name="marriageCd" id="marriageCd" class="form-control" >
													<dd:options codeType="MaritalStatus" />
												</select>
											</span>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="form-group col-md-6">
										<label class="col-md-4 control-label  ">
											社会保障号码
										</label>
										<div class="col-md-8">
											<span class="block input-icon input-icon-right">
												<input type="text" id="socialSecurityCode" name="socialSecurityCode" class="width-100"
												value="" />
											</span>
										</div>
									</div>
									<div class="form-group col-md-6">
										<label class="col-md-4 control-label  ">
											户籍类型<font color="red">*</font>
										</label>
										<div class="col-md-8">
											<span class="block input-icon input-icon-right">
												<input name="censusType" type="radio" class="ace" value="1" />
												<span class="lbl ">
													本地户口
												</span>
												<input name="censusType" type="radio" class="ace" value="2" />
												<span class="lbl">
													非本地户口
												</span>
											</span>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="form-group col-md-6">
									   <label class="col-md-4 control-label  ">
											客户来源<font color="red">*</font>
										</label>
										<div class="col-md-8">
											<span class="block input-icon input-icon-right">
												<select  id="customerSource" name="customerSource" class="form-control" >
													<dd:options codeType="CustomerSource" />						
			        							</select>
											</span>
										</div>
									</div>
									<div class="form-group col-md-6">
										<label class="col-md-4 control-label  ">
											渠道单位名称 或<br>
											推荐人姓名
										</label>
										<div class="col-md-8">
											<span class="block input-icon input-icon-right">
												<input type="text" name="channelName" id="channelName" class="width-100"
												value="" />
											</span>
										</div>
									</div>
								</div>
								
						</div>
					</div>
				</div>
			</div>
			
			<!-- 企业信息 -->
			<div class="panel-collapse collapse in" id="faq-0-2" style="display:none">
				<div class="panel-body">
					<div class="row">
						<div class="col-md-12">
								<div class="row">
									<div class="form-group col-md-6">
										<label class="col-md-4 control-label">
											客户编号<font color="red">*</font>
										</label>
										<div class="col-md-8">
											<button type="button" role="btn_customer" style="text-align:left; color: #478fca !important; text-shadow:none !important; background:transparent none !important; text-decoration: underline;" class="btn btn-sm form-control">${corpCus.customerNum }</button>
										</div>
									</div>
									<div class="form-group col-md-6">
										<label class="col-md-4 control-label">
											客户类型<font color="red">*</font>
										</label>
										<div class="col-md-8">
											 <select name="partyTypeCd"  class="form-control" disabled="disabled">
												<option value=""></option>
												<dd:options codeType="CustomerType" />
											</select>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="form-group col-md-6">
										<label class="col-md-4 control-label">
											客户名称<font color="red">*</font>
										</label>
										<div class="col-md-8">
											<span class="block input-icon input-icon-right">
												<input type="text" name="customerName" id="customerName" 
												class="form-control" value="${corpCus.customerName }" />
											</span>
										</div>
									</div>
									<div class="form-group col-md-6">
										<label class="col-md-4 control-label">
											员工人数
										</label>
										<div class="col-md-8">
											<span class="block input-icon input-icon-right">
												<div class="input-group">
												<input type="text" name="staffNum" 
												id="basic_staffNum" class="form-control" 
												value="${corpCus.staffNum }" />
												 <span class="input-group-addon">人</span>
												</div>
											</span>
										</div>
									</div>
										
								</div>
								<div class="row">
									<div class="form-group col-md-6">
										<label class="col-md-4 control-label">
											组织机构代码<font color="red">*</font>
										</label>
										<div class="col-md-8">
											<span class="block input-icon input-icon-right">
												<input type="text" 
												id="certificateNum" class="form-control"  readonly="readonly"
												value="${corpCus.certificateNum }" />
											</span>
										</div>
									</div>
									<div class="form-group col-md-6">
										<label class="col-md-4 control-label  ">
											客户来源<font color="red">*</font>
										</label>
										<div class="col-md-8">
											<span class="block input-icon input-icon-right">
												<select  id="" name="customerSource" class="form-control" >
													<option value=""></option>
													<dd:options codeType="CustomerSource" selected="${corpCus.customerSource}"/>						
							                    </select>
											</span>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="form-group col-md-6">
										<label class="col-md-4 control-label  ">
											行业类型<font color="red">*</font>
										</label>
										<div class="col-md-8">
											<span class="block input-icon input-icon-right">
												<div class="input-group">
											      	<input type="text" id="industryCdMask"  name="industryCdMask"
											      	 class="form-control" 
											      	readonly="readonly">
											      	<span class="input-group-btn">
											        	<button id="show-tree" class="btn btn-sm btn-yellow" type="button">
											        		<i class="ace-icon fa fa-eye"></i>
											        	</button>
											      	</span>
											      	<input type="hidden" id="industryLevelTwoCd" value="${corpCus.industryLevelTwoCd }" name="industryLevelTwoCd" />
											      	<input type="hidden" id="industryCd"  />
											    </div>
							                </span>
							                
							                <span class="block input-icon input-icon-right">
								                <div id="industryTreeWarp" style="display:none; ">
								                	<div class="col-xs-12" style="overflow-y: auto;max-height: 500px;position:absolute;z-index:999;background:#fff;border:1px solid #e3e3e3">
								                      	<ul id="industryTree" class="ztree">
								                      	</ul>
								                  	</div>
								                </div>
											</span>
										</div>
									</div>
									<div class="form-group col-md-6">
										<label class="col-md-4 control-label  ">
											营业执照号码<font color="red">*</font>
										</label>
										<div class="col-md-8">
											<span class="block input-icon input-icon-right">
											 	<input type="text" name="businessLicenseNum" 
												id="businessLicenseNum" class="form-control"  
												value="${corpCus.businessLicenseNum }" />
											</span>
										</div>
									</div>
								</div>
									
									
								<div class="row">
									<div class="form-group col-md-6">
										<label class="col-md-4 control-label  ">
											币种
										</label>
										<div class="col-md-8">
											<span class="block input-icon input-icon-right">
												<input type="text" class="form-control valid" 
													value="人民币" readonly="readonly" aria-invalid="false">
												<input type="hidden" name="investmentCurrencyType" value="156">
											</span>
										</div>
									</div>
									<div class="form-group col-md-6">
										<label class="col-md-4 control-label  ">
											注册资本<font color="red">*</font>
										</label>
										<div class="col-md-8">
											<span class="block input-icon input-icon-right">
												<div class="input-group">
													<input type="text" name="registeredCapital" 
													id="basic_registeredCapital" class="form-control" 
													value="${corpCus.registeredCapital }" />
													 <span class="input-group-addon">万元</span>
												</div>
											</span>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="form-group col-md-6">
										<label class="col-md-4 control-label  ">
											实收资本<span class='red'>*</span>
										</label>
										<div class="col-md-8">
											<span class="block input-icon input-icon-right">
												<div class="input-group">
												<input type="text" name="actualRevAmt" 
												id="basic_actualRevAmt" class="form-control" 
												value="${corpCus.actualRevAmt }" />
												 <span class="input-group-addon">万元</span>
												</div>
											</span>
										</div>
									</div>
									<div class="form-group col-md-6">
										<label class="col-md-4 control-label  ">
											公司成立时间<span class='red'>*</span>
										</label>
										<div class="col-md-8">
											<span class="block input-icon input-icon-right">
												<input class="form-control date-picker" 
												id="basic_standedDate"  
												name="standedDate" 
												placeholder="" 
												value="<fmt:formatDate value="${corpCus.standedDate }" pattern="yyyy-MM-dd"/>"
												type="text" data-date-format="yyyy-mm-dd"/>
								
											</span>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="form-group col-md-6">
										<label class="col-md-4 control-label  ">
											税务登记号(国税)<span class='red'>*</span>
										</label>
										<div class="col-md-8">
											<span class="block input-icon input-icon-right">
												<input type="text" name="nationalTaxRegistrationNum" 
												id="basic_nationalTaxRegistrationNum" class="form-control" 
												value="${corpCus.nationalTaxRegistrationNum }" />
											</span>
										</div>
									</div>
									<div class="form-group col-md-6">
										<label class="col-md-4 control-label  ">
											税务登记号(地税)<span class='red'>*</span>
										</label>
										<div class="col-md-8">
											<span class="block input-icon input-icon-right">
												<input type="text" name="localTaxRegistrationCum" 
												id="basic_localTaxRegistrationCum" class="form-control" 
												value="${corpCus.localTaxRegistrationCum }" />
											</span>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="form-group col-md-6">
										<label class="col-md-4 control-label  ">
											评级行业类型<span class='red'>*</span>
										</label>
										<div class="col-md-8">
											<span class="block input-icon input-icon-right">
												<select name="ratingIndustryType" id="" class="form-control">
													<dd:options codeType="RatingIndustryType" selected="${corpCus.ratingIndustryType }"/>
							                    </select>
											</span>
										</div>
									</div>
									<div class="form-group col-md-6">
										<label class="col-md-4 control-label  ">
											所有制类别<span class='red'>*</span>
										</label>
										<div class="col-md-8">
											<span class="block input-icon input-icon-right">
												<select name="orgTypeCd" id="" class="form-control">
													<dd:options codeType="OrgTypeCd" selected="${corpCus.orgTypeCd }"/>
							                    </select>
											</span>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="form-group col-md-6">
										<label class="col-md-4 control-label  ">
											企业客户特征<span class='red'>*</span>
										</label>
										<div class="col-md-8">
											<span class="block input-icon input-icon-right">
												<select name="corCustomerFeature" id="" class="form-control">
													<dd:options codeType="CorporationCustomerFeature" 
													selected="${corpCus.corCustomerFeature}"/>
							                    </select>
											</span>
										</div>
									</div>
									<div class="form-group col-md-6">
										<label class="col-md-4 control-label  ">
											贷款卡号码
										</label>
										<div class="col-md-8">
											<span class="block input-icon input-icon-right">
												<input type="text" name="loanCardNum" 
												id="loanCardNum" class="form-control" 
												value="${corpCus.loanCardNum}" />
											</span>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="form-group col-md-6">
									   <label class="col-md-4 control-label  ">
											资产规模<span class='red'>*</span>
										</label>
										<div class="col-md-8">
											<span class="block input-icon input-icon-right">
												<select name="capitalScale" id="capitalScale" class="form-control">
													<dd:options codeType="CapitalScale"
													selected="${corpCus.capitalScale}"/>
							                    </select>
											</span>
										</div>
									</div>
									<div class="form-group col-md-6">
										<label class="col-md-4 control-label  ">
											客户规模<span class='red'>*</span>
										</label>
										<div class="col-md-8">
											<span class="block input-icon input-icon-right">
												<select name="customerScale" id="customerScale" class="form-control">
													<dd:options codeType="CustomerScale"
													selected="${corpCus.customerScale}"/>
							                    </select>
											</span>
										</div>
									</div>
								</div>
								
						</div>
					</div>
				</div>
			</div>
			
			
		</div>
		</div>
	</div>
</form>