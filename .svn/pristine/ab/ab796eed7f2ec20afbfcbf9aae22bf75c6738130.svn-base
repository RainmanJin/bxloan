<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
%>
	<%@include file="../../../../commons/taglibs.jsp" %>
	<h4 class="blue">
		基本信息
	</h4>
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
								缴纳公积金<font color="red">*</font>
							</label>
							<div class="col-xs-12 col-sm-3">
								<span class="block input-icon input-icon-right">
									<dd:radio codeType="CommonWhether" cbName="payFundInd" aceStyle="true"/>
								</span>
							</div>
							<div class="help-block col-xs-12 col-sm-reset inline">
							</div>
							<label class="col-xs-12 col-sm-2 control-label no-padding-right">
								缴纳社保<font color="red">*</font>
							</label>
							<div class="col-xs-12 col-sm-3">
								<span class="block input-icon input-icon-right">
									<dd:radio codeType="CommonWhether" cbName="paySocialSecurityInd" aceStyle="true" />
								</span>
							</div>
							<div class="help-block col-xs-12 col-sm-reset inline">
							</div>
						</div>
						<div class="form-group">
							<label class="col-xs-12 col-sm-2 control-label no-padding-right">
								社会保障号码
							</label>
							<div class="col-xs-12 col-sm-3">
								<span class="block input-icon input-icon-right">
									<input type="text" id="socialSecurityCode" name="socialSecurityCode" class="width-100"
									value="" />
								</span>
							</div>
							<div class="help-block col-xs-12 col-sm-reset inline">
							</div>
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
						</div>
						<div class="form-group">
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
							<label class="col-xs-12 col-sm-2 control-label no-padding-right">
								渠道单位名称 或<br>
								推荐人姓名
							</label>
							<div class="col-xs-12 col-sm-3">
								<span class="block input-icon input-icon-right">
									<input type="text" name="channelName" id="channelName" class="width-100"
									value="" />
								</span>
							</div>
							<div class="help-block col-xs-12 col-sm-reset inline">
							</div>
						</div>
						
						<!-- 四级联动 户籍地 -->
						
							<select name="nationalityCd" id="nation" class="form-control"  onchange="changePermanentAddress(this,1)" style="display:none;">
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
									value=""/>
								</span>
							</div>
							<div class="help-block col-xs-12 col-sm-reset inline">
							</div>
						</div>
						<!--END 四级联动 户籍地 -->
						
						<div class="form-group">
							<label class="col-xs-12 col-sm-2 control-label no-padding-right">
								居住状况<font color="red">*</font>
							</label>
							<div class="col-xs-12 col-sm-3">
								<span class="block input-icon input-icon-right">
									<select name="inhabitancyStatus" id="inhabitancyStatus" class="form-control  ">
										<dd:options codeType="InhabitancyStatus" />
									</select>
								</span>
							</div>
							<div class="help-block col-xs-12 col-sm-reset inline">
							</div>
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
						</div>
						<div class="form-group">
							<label class="col-xs-12 col-sm-2 control-label no-padding-right">
								本地有无房产<font color="red">*</font>
							</label>
							<div class="col-xs-12 col-sm-3">
								<span class="block input-icon input-icon-right">
									<select id="houseCondition" name="houseCondition" class="form-control" onchange="changeHouseCondition(this.value)">
										 <dd:options codeType="CommonWhether"/>
									</select>
								</span>
							</div>
							<div class="help-block col-xs-12 col-sm-reset inline">
							</div>
							<label class="col-xs-12 col-sm-2 control-label no-padding-right">
								房产地址
							</label>
							<div class="col-xs-12 col-sm-3">
								<span class="block input-icon input-icon-right">
									<input type="text" id="position" name="position" class="width-100" value=""
									/>
								</span>
							</div>
							<div class="help-block col-xs-12 col-sm-reset inline">
							</div>
						</div>
						<div class="form-group">
							<label class="col-xs-12 col-sm-2 control-label no-padding-right">
								是否共有<font color="red">*</font>
							</label>
							<div class="col-xs-12 col-sm-3">
								<span class="block input-icon input-icon-right">
									<input name="houseShareCondition" type="radio" class="ace" value="1" onclick="changeHouseShareCondition(this)"/>
									<span class="lbl">
										是
									</span>
									<input name="houseShareCondition" type="radio" class="ace" value="2" onclick="changeHouseShareCondition(this)"/>
									<span class="lbl">
										否
									</span>
								</span>
							</div>
							<div class="help-block col-xs-12 col-sm-reset inline">
							</div>
							<label class="col-xs-12 col-sm-2 control-label no-padding-right">
								共有人数<span id="houseShareNum_span"><font color="red">*</font></span>
							</label>
							<div class="col-xs-12 col-sm-3">
								<span class="block input-icon input-icon-right">
									<div class="input-group">
									<input type="text" id="houseShareNum" name="houseShareNum" class="form-control"
									value="" />
								     <span class="input-group-addon">人</span>
									</div>
								</span>
							</div>
							<div class="help-block col-xs-12 col-sm-reset inline">
							</div>
						</div>
					</div>
					<!-- 工作资料 -->
					<div class="col-xs-12 col-sm-22 ">
						<h4 class="blue">
							工作资料
						</h4>
						<div class="space-8">
						</div>
						
						
						<div class="form-group">
							<label class="col-xs-12 col-sm-2 control-label no-padding-right">
								单位名称<font color="red">*</font>
							</label>
							<div class="col-xs-12 col-sm-3">
								<span class="block input-icon input-icon-right">
									<input type="text" id="workCompany" name="workCompany" class="form-control  "
									 />
								</span>
							</div>
							<div class="help-block col-xs-12 col-sm-reset inline">
							</div>
							<label class="col-xs-12 col-sm-2 control-label no-padding-right">
								单位类型<font color="red">*</font>
							</label>
							<div class="col-xs-12 col-sm-3">
								<span class="block input-icon input-icon-right">
									<select name="companyType" id="companyType" class="form-control  ">
										<dd:options codeType="CompanyType" />
									</select>
								</span>
							</div>
							<div class="help-block col-xs-12 col-sm-reset inline">
							</div>
						</div>
						<div class="form-group">
							<label class="col-xs-12 col-sm-2 control-label no-padding-right">
								职务
							</label>
							<div class="col-xs-12 col-sm-3">
								<span class="block input-icon input-icon-right">
									<select name="professional" id="professional" class="form-control">
										<dd:options codeType="Professional" />
									</select>
								</span>
							</div>
							<div class="help-block col-xs-12 col-sm-reset inline">
							</div>
							<!-- 行业分类-树 -->
							<label class="col-xs-12 col-sm-2 control-label no-padding-right">
								所属行业<font color="red">*</font>
							</label>
							<div class="col-xs-12 col-sm-3">
								<span class="block input-icon input-icon-right">
									<div class="input-group">
										<input type="text" id="industryCdMask" name="industryCdMask" class="form-control"
										readonly="readonly">
										<span class="input-group-btn">
											<button id="show-tree" class="btn btn-sm btn-yellow" type="button">
												<i class="ace-icon fa fa-eye">
												</i>
											</button>
										</span>
									</div>
									<input type="hidden" name="industryCd" id="industryCd" readonly="readonly">
								</span>
								<span class="block input-icon input-icon-right">
									<div id="controlZTree" style="display:none;">
										<div class="col-xs-12" style="overflow:auto;max-height:330px; position:absolute;z-index:999;background:#fff;border:1px solid #e3e3e3">
											<ul id="treeDemo" class="ztree">
											</ul>
										</div>
									</div>
								</span>
							</div>
							<div class="help-block col-xs-12 col-sm-reset inline">
							</div>
						</div>
						<div class="form-group">
							<label class="col-xs-12 col-sm-2 control-label no-padding-right">
								职位名称
							</label>
							<div class="col-xs-12 col-sm-3">
								<span class="block input-icon input-icon-right">
									<input type="text" id="metier" name="metier" class="width-100" placeholder="请输入职业名称"
									value="" />
								</span>
							</div>
							<div class="help-block col-xs-12 col-sm-reset inline">
							</div>
							<label class="col-xs-12 col-sm-2 control-label no-padding-right">
								单位地址<font color="red">*</font>
							</label>
							<div class="col-xs-12 col-sm-3">
								<span class="block input-icon input-icon-right">
									<input type="text" id="companyAddress" name="companyAddress" class="form-control  "
									value="" placeholder="请输入单位地址" />
								</span>
							</div>
							<div class="help-block col-xs-12 col-sm-reset inline">
							</div>
						</div>
						<div class="form-group">
							<label class="col-xs-12 col-sm-2 control-label no-padding-right">
								单位电话<font color="red">*</font>
							</label>
							<div class="col-xs-12 col-sm-3">
								<span class="block input-icon input-icon-right">
									<input type="text" id="companyTel" name="companyTel" class="form-control  "
									value="" placeholder="请输入单位电话" />
								</span>
							</div>
							<div class="help-block col-xs-12 col-sm-reset inline">
							</div>
							<label class="col-xs-12 col-sm-2 control-label no-padding-right">
								单位传真
							</label>
							<div class="col-xs-12 col-sm-3">
								<span class="block input-icon input-icon-right">
									<input type="text" id="companyFax" name="companyFax" class="width-100"
									value="" placeholder="请输入单位传真" />
								</span>
							</div>
							<div class="help-block col-xs-12 col-sm-reset inline">
							</div>
						</div>
						<div class="form-group">
							<label class="col-xs-12 col-sm-2 control-label no-padding-right">
								备注
							</label>
							<div class="col-xs-12 col-sm-3">
								<span class="block input-icon input-icon-right">
									<input type="text" id="workRemark" name="workRemark" class="width-100"
									value="" placeholder="请输入备注" />
								</span>
							</div>
							<div class="help-block col-xs-12 col-sm-reset inline">
							</div>
							<label class="col-xs-12 col-sm-2 control-label no-padding-right">
								本地工作年限<font color="red">*</font>
							</label>
							<div class="col-xs-12 col-sm-3">
								<span class="block input-icon input-icon-right">
								<div class="input-group">
									<input type="text" id="localWorkYear" class="width-100" value="" name="localWorkYear"
									/>
									 <span class="input-group-addon">年</span>
								</div>
								</span>
							</div>
							<div class="help-block col-xs-12 col-sm-reset inline">
							</div>
						</div>
						<div class="form-group">
							<label class="col-xs-12 col-sm-2 control-label no-padding-right">
								现单位工作年限<font color="red">*</font>
							</label>
							<div class="col-xs-12 col-sm-3">
								<span class="block input-icon input-icon-right">
									<div class="input-group">
									<input type="text" id="currCompanyWorkYears" name="currCompanyWorkYears"
									class="width-100" value="" />
									 <span class="input-group-addon">年</span>
									</div>
								</span>
							</div>
							<div class="help-block col-xs-12 col-sm-reset inline">
							</div>
							<label class="col-xs-12 col-sm-2 control-label no-padding-right">
								是否转正<font color="red">*</font>
							</label>
							<div class="col-xs-12 col-sm-3">
								<span class="block input-icon input-icon-right">
									<input name="positiveInd" type="radio" class="ace" value="1" />
									<span class="lbl">
										是
									</span>
									<input name="positiveInd" type="radio" class="ace" value="2" />
									<span class="lbl">
										否
									</span>
								</span>
							</div>
							<div class="help-block col-xs-12 col-sm-reset inline">
							</div>
						</div>
						
					</div>
					<div class="col-xs-12">
						<div class="wizard-actions" style=" text-align:center;">
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