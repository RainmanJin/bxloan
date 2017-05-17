<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
%>
	<%@include file="../../../../commons/taglibs.jsp" %>
		<!-- <h4 class="blue">
			基本信息
		</h4> -->
		<div id="faq-list-1" class="panel-group accordion-style1 accordion-style2">
			<div class="step-content pos-rel" id="step-container">
				<div class="step-pane active" id="step1">
					<form class="form-horizontal" id="form-gkxx">
						<div class="col-xs-12  ">
							<input type="hidden"  name="sysUpdateDate" value="${requestScope.sysUpdateDate}"
							/>
							<div class="form-group">
								<label class="col-xs-12 col-sm-2 control-label no-padding-right">
									请勾选<font color="red">*</font>
								</label>
								<div class="col-xs-12 col-sm-8" style="text-align: center;">
									<span class="block input-icon input-icon-right">
										<dd:checkbox codeType="CustomerMarkType" cbName="markType" aceStyle="true" codeVals="01,02"/>
									</span>
								</div>
								<div class="help-block col-xs-12 col-sm-reset inline">
								</div>
							</div>
							<div class="form-group">
								<label class="col-xs-12 col-sm-2 control-label no-padding-right">
									客户名称<font color="red">*</font>
								</label>
								<div class="col-xs-12 col-sm-3">
									<span class="block input-icon input-icon-right">
										<input type="text" name="customerName" class="form-control" value="${requestScope.party.partyName}"
										/>
									</span>
								</div>
								<div class="help-block col-xs-12 col-sm-reset inline">
								</div>
								<label class="col-xs-12 col-sm-2 control-label no-padding-right">
									客户编号
								</label>
								<div class="col-xs-12 col-sm-3">
									<span class="block input-icon input-icon-right">
										<input type="text" name="customerNum" id="csnm" class="form-control" value="${requestScope.party.customerNum}"
										readonly="readonly" />
									</span>
								</div>
								<div class="help-block col-xs-12 col-sm-reset inline">
								</div>
							</div>
							<div class="form-group">
								<label class="col-xs-12 col-sm-2 control-label no-padding-right">
									证件类型<font color="red">*</font>
								</label>
								<div class="col-xs-12 col-sm-3">
									<span class="block input-icon input-icon-right">
										<select name="certificateTypeCd" id="certificateTypeCd" class="form-control"
										readonly="readonly">
											<dd:options codeType="PCertificateType" />
										</select>
									</span>
								</div>
								<div class="help-block col-xs-12 col-sm-reset inline">
								</div>
								<label class="col-xs-12 col-sm-2 control-label no-padding-right">
									证件号码<font color="red">*</font>
								</label>
								<div class="col-xs-12 col-sm-3">
									<span class="block input-icon input-icon-right">
										<input type="text" name="certificateNum" class="form-control" value="${requestScope.party.certificateNum }"
										readonly="readonly" />
									</span>
								</div>
								<div class="help-block col-xs-12 col-sm-reset inline">
								</div>
							</div>
							<div class="form-group">
								<label class="col-xs-12 col-sm-2 control-label no-padding-right">
									性 别<font color="red">*</font>
								</label>
								<div class="col-xs-12 col-sm-3">
									<span class="block input-icon input-icon-right">
										<select name="genderCd" id="genderCd" class="form-control">
											<dd:options codeType="SexTypeCD" />
										</select>
									</span>
								</div>
								<div class="help-block col-xs-12 col-sm-reset inline">
								</div>
								<label class="col-xs-12 col-sm-2 control-label no-padding-right">
									婚姻状况<font color="red">*</font>
								</label>
								<div class="col-xs-12 col-sm-3">
									<span class="block input-icon input-icon-right">
										<select name="marriageCd" id="marriageCd" class="form-control" >
											<dd:options codeType="MaritalStatus" />
										</select>
									</span>
								</div>
								<div class="help-block col-xs-12 col-sm-reset inline">
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-xs-12 col-sm-2 control-label no-padding-right">
									出生年月<font color="red">*</font>
								</label>
								<div class="col-xs-12 col-sm-3">
									<span class="block input-icon input-icon-right">
										<input type="text" id="birthday" name="birthday" class="form-control required"
											data-date-format="yyyy/mm/dd" value="${birthdayStr}" />
									</span>
								</div>
								<div class="help-block col-xs-12 col-sm-reset inline">
								</div>
								<label class="col-xs-12 col-sm-2 control-label no-padding-right">
									年龄<font color="red">*</font>
								</label>
								<div class="col-xs-12 col-sm-3">
									<span class="block input-icon input-icon-right">
										<input type="text" name="age" class="form-control" />
									</span>
								</div>
								<div class="help-block col-xs-12 col-sm-reset inline">
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-xs-12 col-sm-2 control-label no-padding-right">
									教育程度<font color="red">*</font>
								</label>
								<div class="col-xs-12 col-sm-3">
									<span class="block input-icon input-icon-right">
										<select name="degreeCd" id="degreeCd" class="form-control">
											<dd:options codeType="DegreeCode" />
										</select>
									</span>
								</div>
								<div class="help-block col-xs-12 col-sm-reset inline">
								</div>
								<label class="col-xs-12 col-sm-2 control-label no-padding-right">
									手机1<font color="red">*</font>
								</label>
								<div class="col-xs-12 col-sm-3">
									<span class="block input-icon input-icon-right">
										<input type="text" id="mobileTel" name="mobileTel" class="form-control  "
										value="" />
									</span>
								</div>
								<div class="help-block col-xs-12 col-sm-reset inline">
								</div>
							</div>
							<div class="form-group">
								<label class="col-xs-12 col-sm-2 control-label no-padding-right">
									手机2
								</label>
								<div class="col-xs-12 col-sm-3">
									<span class="block input-icon input-icon-right">
										<input type="text" id="telphone2" name="telphone2" class="width-100" value=""
										/>
									</span>
								</div>
								<div class="help-block col-xs-12 col-sm-reset inline">
								</div>
								<label class="col-xs-12 col-sm-2 control-label no-padding-right">
									QQ
								</label>
								<div class="col-xs-12 col-sm-3">
									<span class="block input-icon input-icon-right">
										<input type="text" id="qq" name="qq" class="width-100" value="" />
									</span>
								</div>
								<div class="help-block col-xs-12 col-sm-reset inline">
								</div>
							</div>
							<div class="form-group">
								<label class="col-xs-12 col-sm-2 control-label no-padding-right">
									微信
								</label>
								<div class="col-xs-12 col-sm-3">
									<span class="block input-icon input-icon-right">
										<input type="text" id="microAccount" name="microAccount" class="width-100"
										value="" />
									</span>
								</div>
								<div class="help-block col-xs-12 col-sm-reset inline">
								</div>
								<label class="col-xs-12 col-sm-2 control-label no-padding-right">
									Email
								</label>
								<div class="col-xs-12 col-sm-3">
									<span class="block input-icon input-icon-right">
										<input type="text" id="email" name="email" class="width-100" value=""
										/>
									</span>
								</div>
								<div class="help-block col-xs-12 col-sm-reset inline">
								</div>
							</div>
							<div class="form-group">
								<label class="col-xs-12 col-sm-2 control-label no-padding-right">
									单位电话
								</label>
								<div class="col-xs-12 col-sm-3">
									<span class="block input-icon input-icon-right">
										<input type="text" id="companyTel" name="companyTel" class="form-control  "
										value=""  />
									</span>
								</div>
								<div class="help-block col-xs-12 col-sm-reset inline">
								</div>
								<label class="col-xs-12 col-sm-2 control-label no-padding-right">
									子女人数
								</label>
								<div class="col-xs-12 col-sm-3">
									<span class="block input-icon input-icon-right">
									<div class="input-group">
										<input type="text" id="chiledNum" name="chiledNum" class="width-100" value=""
										/>
										 <span class="input-group-addon">人</span>
									</div>
									</span>
								</div>
								<div class="help-block col-xs-12 col-sm-reset inline">
								</div>
							</div>
							<div class="form-group">
								<label class="col-xs-12 col-sm-2 control-label no-padding-right">
									户籍类型<font color="red">*</font>
								</label>
								<div class="col-xs-12 col-sm-3">
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
								<div class="help-block col-xs-12 col-sm-reset inline">
								</div>
								<label class="col-xs-12 col-sm-2 control-label no-padding-right">
									客户来源<font color="red">*</font>
								</label>
								<div class="col-xs-12 col-sm-3">
									<span class="block input-icon input-icon-right">
										<select  id="customerSource" name="customerSource" class="form-control" >
											<dd:options codeType="CustomerSource" />						
       									 </select>
									</span>
								</div>
								<div class="help-block col-xs-12 col-sm-reset inline">
								</div>
							</div>
							
							
							<!-- 四级联动 户籍地 -->
							<select name="nationalityCd" id="nation" class="form-control" onchange="changePermanentAddress(this,1)" style="display:none;">
							</select>
						<div class="form-group">
							
							<label class="col-xs-12 col-sm-2 control-label no-padding-right">
								省份/自治区/直辖市
							</label>
							<div class="col-xs-12 col-sm-3">
								<span class="block input-icon input-icon-right">
									<select name="provinceCd" id="province" class="form-control" onchange="changePermanentAddress(this,2)">
										<option  value="">请选择</option>
									</select>
								</span>
							</div>
							
							<label class="col-xs-12 col-sm-2 control-label no-padding-right">
								城市/自治州
							</label>
							<div class="col-xs-12 col-sm-3">
								<span class="block input-icon input-icon-right">
									<select name="cityCd" id="city" class="form-control" onchange="changePermanentAddress(this,3)">
										<option  value="">请选择</option>
									</select>
								</span>
							</div>
						</div>
						<div class="form-group">
							<label class="col-xs-12 col-sm-2 control-label no-padding-right">
								区/县
							</label>
							<div class="col-xs-12 col-sm-3">
								<span class="block input-icon input-icon-right">
									<select name="countyCd" id="county" class="form-control" onchange="changePermanentAddress(this,4)">
										<option value="">请选择</option>
									</select>
								</span>
							</div>
							<label class="col-xs-12 col-sm-2 control-label no-padding-right">
								雇佣类型
							</label>
							<div class="col-xs-12 col-sm-3">
								<span class="block input-icon input-icon-right">
									<select name="employmentType" id="employmentType" class="form-control" disabled="disabled">
										<dd:options codeType="EmploymentType" />
									</select>
								</span>
							</div>
							<div class="help-block col-xs-12 col-sm-reset inline">
							</div>
						</div>
						<div class="form-group">
							<label class="col-xs-12 col-sm-2 control-label no-padding-right">
								户籍地<font color="red">*</font>
							</label>
							<div class="col-xs-12 col-sm-8">
								<span class="block input-icon input-icon-right">
									<input type="text" id="permanentAddress" name="permanentAddress" class="form-control  "
									value="" />
								</span>
							</div>
							<div class="help-block col-xs-12 col-sm-reset inline">
							</div>
						</div>
						<!--END 四级联动 户籍地 -->
							
							
							<div class="form-group">
								<label class="col-xs-12 col-sm-2 control-label no-padding-right">
									本地有无房产<font color="red">*</font>
								</label>
								<div class="col-xs-12 col-sm-3">
									<span class="block input-icon input-icon-right">
										<select id="houseCondition" name="houseCondition" class="form-control" onchange="changeHouseCondition(this)">
										 <dd:options codeType="CommonWhether"/>
										</select>
									</span>
								</div>
								<div class="help-block col-xs-12 col-sm-reset inline">
								</div>
								<label class="col-xs-12 col-sm-2 control-label no-padding-right">
									居住状况<font color="red">*</font>
								</label>
								<div class="col-xs-12 col-sm-3">
									<span class="block input-icon input-icon-right">
										<select name="inhabitancyStatus" id="inhabitancyStatus" class="form-control" >
											<dd:options codeType="InhabitancyStatus" />
										</select>
									</span>
								</div>
								<div class="help-block col-xs-12 col-sm-reset inline">
								</div>
							</div>
							<div class="form-group">
								<label class="col-xs-12 col-sm-2 control-label no-padding-right">
									居住地址<font color="red">*</font>
								</label>
								<div class="col-xs-12 col-sm-3">
									<span class="block input-icon input-icon-right">
										<input type="text" id="familyAddress" name="familyAddress" class="form-control  "
										value="" />
									</span>
								</div>
								<div class="help-block col-xs-12 col-sm-reset inline">
								</div>
								<label class="col-xs-12 col-sm-2 control-label no-padding-right">
								 居住面积(㎡)<font color="red">*</font>
								</label>
								<div class="col-xs-12 col-sm-3">
									<span class="block input-icon input-icon-right">
										<input type="text" name="livingArea" class="form-control input-sm " />
									</span>
								</div>
								<div class="help-block col-xs-12 col-sm-reset inline">
								</div>
							</div>
							
						<div id="agroFormDiv" style="display:none;">
							<div class="form-group">
								<label class="col-xs-12 col-sm-2 control-label no-padding-right">
									本地居住年限<font color="red">*</font>
								</label>
								<div class="col-xs-12 col-sm-3">
									<span class="block input-icon input-icon-right">
										<input type="text" name="localInhabitancyYears" class="form-control"/>
									</span>
								</div>
								<div class="help-block col-xs-12 col-sm-reset inline">
								</div>
								<label class="col-xs-12 col-sm-2 control-label no-padding-right">
								 从事本行业年份<font color="red">*</font>
								</label>
							<div class="col-xs-12 col-sm-3">
								<span class="block input-icon input-icon-right">
									<input type="text" name="yearsInIndustry" class="form-control"
								/>
								</span>
							</div>
							<div class="help-block col-xs-12 col-sm-reset inline">
							</div>
							</div>
							<div class="form-group">
								<label class="col-xs-12 col-sm-2 control-label no-padding-right">
								 农业人口<font color="red">*</font>
								</label>
							<div class="col-xs-12 col-sm-3">
								<span class="block input-icon input-icon-right">
									<select name="agroPopulation" id="agroPopulation" class="form-control">
										<dd:options codeType="CommonWhether" />
									</select>
								</span>
							</div>
							<div class="help-block col-xs-12 col-sm-reset inline">
							</div>
							<label class="col-xs-12 col-sm-2 control-label no-padding-right">
									家电情况<font color="red">*</font>
								</label>
								<div class="col-xs-12 col-sm-3">
									<span class="block input-icon input-icon-right">
										<select name="appliancesCase" id="appliancesCase" class="form-control">
											<dd:options codeType="AppliancesCase" />
										</select>
									</span>
								</div>
								<div class="help-block col-xs-12 col-sm-reset inline">
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-xs-12 col-sm-2 control-label no-padding-right">
									配偶<font color="red">*</font>
								</label>
								<div class="col-xs-12 col-sm-3">
									<span class="block input-icon input-icon-right">
										<select name="spouseCaseShow" id="spouseCase" class="form-control">
											<dd:options codeType="SpouseCase" />
										</select>
										<input type="hidden" value="" name="spouseCase"/>
									</span>
								</div>
								<div class="help-block col-xs-12 col-sm-reset inline">
								</div>
								<label class="col-xs-12 col-sm-2 control-label no-padding-right">
								  配偶就业情况<font color="red">*</font>
								</label>
							<div class="col-xs-12 col-sm-3">
								<span class="block input-icon input-icon-right">
									<select name="spouseEmploymentShow" id="spouseEmployment" class="form-control">
										<dd:options codeType="SpouseEmployment" />
									</select>
									<input type="hidden" value="" name="spouseEmployment"/>
								</span>
							</div>
							<div class="help-block col-xs-12 col-sm-reset inline">
							</div>
							</div>
							<div class="form-group">
								<label class="col-xs-12 col-sm-2 control-label no-padding-right">
								  家庭人口<font color="red">*</font>
								</label>
								<div class="col-xs-12 col-sm-3">
									<span class="block input-icon input-icon-right">
										<input type="text" name="familySize" class="form-control" />
									</span>
								</div>
								<div class="help-block col-xs-12 col-sm-reset inline">
								</div>
								<label class="col-xs-12 col-sm-2 control-label no-padding-right">
									劳动人口<font color="red">*</font>
								</label>
								<div class="col-xs-12 col-sm-3">
									<span class="block input-icon input-icon-right">
										<input type="text" name="workForce" class="form-control" />
									</span>
								</div>
								<div class="help-block col-xs-12 col-sm-reset inline">
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-xs-12 col-sm-2 control-label no-padding-right">
								  供/赡养人口<font color="red">*</font>
								</label>
								<div class="col-xs-12 col-sm-3">
								<span class="block input-icon input-icon-right">
									<input type="text" name="dependentPopulation" class="form-control "
								/>
								</span>
								</div>
								<div class="help-block col-xs-12 col-sm-reset inline">
								</div>
								<label class="col-xs-12 col-sm-2 control-label no-padding-right">
									供养/家庭人口比<font color="red">*</font>
								</label>
								<div class="col-xs-12 col-sm-3">
									<div class="input-group">
										<input type="text" name="dependentPopulationRate" class="form-control "
								/>
									<span class="input-group-addon">%</span>
									</div>
								</div>
								<div class="help-block col-xs-12 col-sm-reset inline">
								</div>
								<div class="help-block col-xs-12 col-sm-reset inline">
								</div> 
							</div>
						</div>
							
						</div>
						<!-- 表单完 -->
						<div class="col-xs-12">
							<div class="wizard-actions" style=" text-align:center;">
								<hr/>
								<span id="gkgzSpan">
									<button class="btn btn-sm btn-primary" id="saveGKXX" type="button" data-loading-text="正在保存">
										<i class="ace-icon fa fa-floppy-o">
										</i>
										保 存
									</button>
								</span>
								<c:if test="${requestScope.business != 'business' }">
									<button class="btn btn-sm btn-pre" id="goBackButton" data-last="Finish"
									type="button" onclick="javascript:history.go(-1);">
										<i class="ace-icon fa fa-chevron-left">
										</i>
										返回
									</button>
								</c:if>
							</div>
						</div>
					</form>
				</div>
				<!-- step pane -->
			</div>
			<!-- step content -->
		</div>