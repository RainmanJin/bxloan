<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
%>
	<%@include file="../../../commons/taglibs.jsp" %>
		<div class="row">
			<div class="col-md-12">
				<form id="basicInfoForm_nd">
					<h4 class="blue">
						基本信息
					</h4>
					<div class="row">
						<input name="partyId" type="hidden" />
						<div class="form-group col-md-5">
							<label class="col-xs-12 col-sm-6 control-label no-padding-right">
								借款人姓名：
							</label>
							<div class="col-xs-12 col-sm-6">
								<input type="text" name="customerName" class="form-control input-sm required" readonly="readonly"
								/>
							</div>
						</div>
						<div class="form-group col-md-5">
							<label class="col-xs-12 col-sm-6 control-label no-padding-right">
								身份证号码：
							</label>
							<div class="col-xs-12 col-sm-6">
								<span class="block input-icon input-icon-right">
									<input type="hidden" name="idCardNum_type" class="form-control input-sm"
									value="100" />
									<input type="text" name="idCardNum" class="form-control input-sm required" />
								</span>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="form-group col-md-5">
							<label class="col-xs-12 col-sm-6 control-label no-padding-right">
								年龄：
							</label>
							<div class="col-xs-12 col-sm-6">
								<input type="text" name="age" class="form-control input-sm required" 
								/>
							</div>
						</div>
						<div class="form-group col-md-5">
							<label class="col-xs-12 col-sm-6 control-label no-padding-right">
								性别：
							</label>
							<div class="col-xs-12 col-sm-6">
								<select name="genderCd" id="genderCd" class="form-control required">
									<dd:options codeType="SexTypeCD" />
								</select>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="form-group col-md-5">
							<label class="col-xs-12 col-sm-6 control-label no-padding-right">
								户籍地址：
							</label>
							<div class="col-xs-12 col-sm-6">
								<input type="text" name="permanentAddress" class="form-control input-sm required"
								/>
							</div>
						</div>
						<div class="form-group col-md-5">
							<label class="col-xs-12 col-sm-6 control-label no-padding-right">
								婚姻状况：
							</label>
							<div class="col-xs-12 col-sm-6">
								<select name="marriageCd" id="marriageCd" class="form-control required">
									<dd:options codeType="MaritalStatus" />
								</select>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="form-group col-md-5">
							<label class="col-xs-12 col-sm-6 control-label no-padding-right">
								所属行业：
							</label>
							<div class="col-xs-12 col-sm-6">
								<span class="block input-icon input-icon-right">
									<div class="input-group">
										<input type="text" id="_industryCdMask" name="industryCdMask" class="form-control required"
										readonly="readonly">
										<c:if test="${isEdit }">
											<span class="input-group-btn">
												<button id="show-tree" class="btn btn-sm btn-yellow" type="button">
														<i class="ace-icon fa fa-eye"></i>
												</button>
											</span>
										</c:if>
									</div>
									<input type="hidden" name="industryCd" id="_industryCd" readonly="readonly">
								</span>
								<span class="block input-icon input-icon-right">
									<div id="_controlZTree" style="display:none;">
										<div class="col-xs-12" style="overflow:auto;max-height:330px; position:absolute;z-index:999;background:#fff;border:1px solid #e3e3e3">
											<ul id="_treeDemo" class="ztree">
											</ul>
										</div>
									</div>
								</span>
							</div>
						</div>
						<div class="form-group col-md-5">
							<label class="col-xs-12 col-sm-6 control-label no-padding-right">
								从事本行业年份：
							</label>
							<div class="col-xs-12 col-sm-6">
								<input type="text" name="yearsInIndustry" class="form-control input-sm required"
								/>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="form-group col-md-5">
							<label class="col-xs-12 col-sm-6 control-label no-padding-right">
								本地居住年限：
							</label>
							<div class="col-xs-12 col-sm-6">
								<input type="text" name="localInhabitancyYears" class="form-control input-sm required"
								 />
							</div>
						</div>
						<div class="form-group col-md-5">
							<label class="col-xs-12 col-sm-6 control-label no-padding-right">
								农业人口：
							</label>
							<div class="col-xs-12 col-sm-6">
								<select name="agroPopulation" id="agroPopulation" class="form-control required">
									<dd:options codeType="CommonWhether" />
								</select>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="form-group col-md-5">
							<label class="col-xs-12 col-sm-6 control-label no-padding-right">
								电话：
							</label>
							<div class="col-xs-12 col-sm-6">
								<input type="text" name="mobileTel" class="form-control input-sm required" />
							</div>
						</div>
						<%-- <div class="form-group col-md-5">
							<label class="col-xs-12 col-sm-6 control-label no-padding-right">
								客户类型：
							</label>
							<div class="col-xs-12 col-sm-6">
								<select name="agroBizType" id="agroBizType" class="form-control required">
									<dd:options codeType="AgroBizType" />
								</select>
							</div>
						</div> --%>
					</div>
					<h4 class="blue">
						家庭情况
					</h4>
					<div class="row">
						<div class="form-group col-md-5">
							<label class="col-xs-12 col-sm-6 control-label no-padding-right">
								本地居住状况：
							</label>
							<div class="col-xs-12 col-sm-6">
								<select name="inhabitancyStatus" id="inhabitancyStatus" class="form-control required">
									<dd:options codeType="InhabitancyStatus" />
								</select>
							</div>
						</div>
						<div class="form-group col-md-5">
							<label class="col-xs-12 col-sm-6 control-label no-padding-right">
								居住面积(㎡)：
							</label>
							<div class="col-xs-12 col-sm-6">
								<span class="block input-icon input-icon-right">
									<input type="text" name="livingArea" class="form-control input-sm required" />
								</span>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="form-group col-md-5">
							<label class="col-xs-12 col-sm-6 control-label no-padding-right">
								居住地址：
							</label>
							<div class="col-xs-12 col-sm-6">
								<input type="text" name="familyAddress" class="form-control input-sm required"
								/>
							</div>
						</div>
						<div class="form-group col-md-5">
							<label class="col-xs-12 col-sm-6 control-label no-padding-right">
								家电情况：
							</label>
							<div class="col-xs-12 col-sm-6">
								<select name="appliancesCase" id="appliancesCase" class="form-control required">
									<dd:options codeType="AppliancesCase" />
								</select>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="form-group col-md-5">
							<label class="col-xs-12 col-sm-6 control-label no-padding-right">
								配偶：
							</label>
							<div class="col-xs-12 col-sm-6">
								<select name="spouseCase" id="spouseCase" class="form-control required">
									<dd:options codeType="SpouseCase" />
								</select>
							</div>
						</div>
						<div class="form-group col-md-5">
							<label class="col-xs-12 col-sm-6 control-label no-padding-right">
								配偶就业情况：
							</label>
							<div class="col-xs-12 col-sm-6">
								<select name="spouseEmployment" id="spouseEmployment" class="form-control required">
									<dd:options codeType="SpouseEmployment" />
								</select>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="form-group col-md-5">
							<label class="col-xs-12 col-sm-6 control-label no-padding-right">
								家庭人口：
							</label>
							<div class="col-xs-12 col-sm-6">
								<input type="text" name="familySize" class="form-control input-sm required" />
							</div>
						</div>
						<div class="form-group col-md-5">
							<label class="col-xs-12 col-sm-6 control-label no-padding-right">
								劳动人口：
							</label>
							<div class="col-xs-12 col-sm-6">
								<input type="text" name="workForce" class="form-control input-sm required" />
							</div>
						</div>
					</div>
					<div class="row">
						<div class="form-group col-md-5">
							<label class="col-xs-12 col-sm-6 control-label no-padding-right ">
								供/赡养人口：
							</label>
							<div class="col-xs-12 col-sm-6">
								<input type="text" name="dependentPopulation" class="form-control input-sm required"
								/>
							</div>
						</div>
						<div class="form-group col-md-5">
							<label class="col-xs-12 col-sm-6 control-label no-padding-right">
								供养/家庭人口比：
							</label>
							<div class="col-xs-12 col-sm-6">
								<div class="input-group">
								<input type="text" name="dependentPopulationRate" class="form-control input-sm required"
								/>
								<span class="input-group-addon">%</span>
								</div>
							</div>
						</div>
					</div>
					<h4 class="blue widget-title">
						家庭成员信息
					</h4>
					<c:if test="${isEdit }">
					<div align="right" style="margin-left:45px;">
						<span id="lxrSpan">
							<button class="btn btn-sm btn-success" id="btn-family" type="button">
								<i class="ace-icon fa fa-plus"> </i> 新增
							</button> 
						</span>
					</div>
					</c:if>
					<table id="familyTb" class="table table-striped table-hover" style="white-space: nowrap;">
						<thead>
							<tr>
								<th>
									占位
								</th>
								<th>
									姓名
								</th>
								<th>
									与借款人关系
								</th>
								<th>
									年龄
								</th>
								<th>
									状态
								</th>
								<th>
									工作单位/状况
								</th>
								<th>
									月收入
								</th>
								<th>
									教育程度
								</th>
								<th>
									联系电话
								</th>
								<th>
									操作
								</th>
							</tr>
						</thead>
						<tbody>
							
						</tbody>
					</table>
					
					<h4 class="blue widget-title">
						核实人信息
					</h4>
					<c:if test="${isEdit }">
					<div align="right" style="margin-left:45px;">
						<span id="hsrSpan">
							<button class="btn btn-sm btn-success" id="btn-verificatPerson" type="button">
								<i class="ace-icon fa fa-plus"> </i> 新增
							</button> 
						</span>
					</div>
					</c:if>
					<table id="verificatPersonTb" class="table table-striped table-hover" style="white-space: nowrap;">
						<thead>
							<tr>
								<th>
									占位
								</th>
								<th>
									姓名
								</th>
								<th>
									与借款人关系
								</th>
								<th>
									家庭地址
								</th>
								<th>
									联系电话
								</th>
								<th>
									备注
								</th>
								<th>
									操作
								</th>
							</tr>
						</thead>
						<tbody>
							
						</tbody>
					</table>
				</form>
			</div>
		</div>
		<div align="center" style="margin-top: 10px;">
			<button type="button" class="btn btn-sm btn-primary" id="submitBasicForm"
			data-loading-text="正在保存...">
				<i class="ace-icon fa fa-floppy-o">
				</i>
				保存
			</button>
			<c:if test="${isEdit }">
				<button class="btn btn-warning btn-sm" type="button" onclick="javascript:history.go(-1);">
					<i class="ace-icon fa fa-times">
					</i>
					返回
				</button>
			</c:if>
		</div>
		<div>
		<jsp:include page="modalLxr.jsp"></jsp:include>
		<jsp:include page="modalVerificatPerson.jsp"></jsp:include>
		</div>
		</div>