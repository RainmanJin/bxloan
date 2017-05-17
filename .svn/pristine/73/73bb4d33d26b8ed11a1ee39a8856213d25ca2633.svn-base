<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.coamc-tech.com/taglibs/datadict" prefix="dd" %>
<div id="add_person_modal" class="modal" tabindex="-1" data-backdrop="static">
	<div class="modal-dialog" style="width:70%">
		<div class="modal-content">
			<form id="add_person_form" action="" class="form-horizontal" >
				
				<input id="add_person_partyId" type="hidden" name="partyId" value="${corpCus.partyId }"/>
				<input type="hidden" id="add_person_person_id" name="id" value="" />
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="blue bigger">
						<i class='ace-icon fa fa-plus'></i> 新增
					</h4>
				</div>
				<div class="modal-body">
				
				<div class="row">
					<div class="col-md-12">

							<div class="tabbable">
								<ul class="nav nav-tabs padding-12 tab-color-blue" id="add_person_info_tab">
									<li class="active" >
										<a data-toggle="tab" id="personinfo_li_1" href="#personinfo-tab-1">概况信息 </a>
									</li>
									<li>
										<a data-toggle="tab" href="#personinfo-tab-2">工作资料</a>
									</li>
									<li>
										<a data-toggle="tab" href="#personinfo-tab-3">经济状况</a>
									</li>
								</ul>
								<div class="tab-content no-border padding-24">
									<div id="personinfo-tab-1" class="tab-pane fade in active">
										<div class="form-group">
										<label class="col-xs-12 col-sm-2 control-label no-padding-right ">
											选择类型* 
										</label>
										<div class="col-xs-12 col-sm-10 ">
											<span class="block input-icon input-icon-right ">
												<label class="checkbox-inline">
													<input name="personType" id="personActController" type="checkbox" 
													readonly="readonly"
													class="ace add_person_personType" value="3" />
													<span class="lbl">
												    	实际控制人
													</span>
												</label>
												
												<label class="checkbox-inline">
													<input name="personType" type="checkbox" 
													id="add_person_stock_check"
													class="ace add_person_personType" value="1" />
													<span class="lbl">
												    	股东
													</span>
												</label>
												
												<label class="checkbox-inline">
													<input name="personType" type="checkbox" 
													id="add_person_high_mana_check"
													class="ace add_person_personType" value="2" />
													<span class="lbl">
												    	高管
													</span>
												</label>
												
												<label class="checkbox-inline">
													<input name="personType" type="checkbox" 
													id="add_person_legal_rep_check"
													disabled="disabled" 
													class="ace add_person_personType" value="4" />
													<span class="lbl">
												    	法定代表人
													</span>
												</label>
												
											</span>
										</div>
										<div class="help-block col-xs-12 col-sm-reset inline"></div>
									</div> 
									
									<div class="form-group">
										<label class="col-xs-12 col-sm-2 control-label no-padding-right">
											客户名称*
										</label>
										<div class="col-xs-12 col-sm-3">
											<div class="input-group">
												<span class="block input-icon input-icon-right">
													<input type="text" name="name" id="add_person_name" 
													class="form-control" value="" 
													readonly="readonly"/>
												</span>
												<span class="input-group-btn">
										        	<button role="showExistingParty" 
										        	partyType="2" 
										        	disabled="disabled" 
										        	class="btn btn-sm btn-yellow" type="button">
										        		<i class="ace-icon fa fa-eye"></i>
										        	</button>
										      	</span>
											</div>
										</div>
										<div class="help-block col-xs-12 col-sm-reset inline"></div>
								    	<label class="col-xs-12 col-sm-2 control-label no-padding-right">
											性别
										</label>
										<div class="col-xs-12 col-sm-3">
											<span class="block input-icon input-icon-right">
												<select name="sexCd"
												id="add_person_sexCd" class="form-control" >
												<dd:options codeType="SexTypeCD"/>
												</select>
											</span>
										</div>
										<div class="help-block col-xs-12 col-sm-reset inline"></div>
									</div>
									
									<div class="form-group">
										<label class="col-xs-12 col-sm-2 control-label no-padding-right">
											证件类型*
										</label>
										<div class="col-xs-12 col-sm-3">
											<span class="block input-icon input-icon-right">
												<select name="certificateTypeCd"
												id="add_person_certificateTypeCd" class="form-control" >
												<dd:options codeType="PCertificateType"/>
												</select>
												
											</span>
										</div>
										<div class="help-block col-xs-12 col-sm-reset inline"></div>
								    	<label class="col-xs-12 col-sm-2 control-label no-padding-right">
											证件号码*
										</label>
										<div class="col-xs-12 col-sm-3">
											<span class="block input-icon input-icon-right">
												<input type="text" name="certificateCd" 
												id="add_person_certificateCd" 
												class="form-control" value="" />
											</span>
										</div>
										<div class="help-block col-xs-12 col-sm-reset inline"></div>
									</div>
									
									
									<div class="form-group">
										<label class="col-xs-12 col-sm-2 control-label no-padding-right">
											出生日期*
										</label>
										<div class="col-xs-12 col-sm-3">
											<span class="block input-icon input-icon-right">
												<input class="form-control date-picker" 
												id="add_person_birthDate"  
												name="birthDate" 
												type="text" data-date-format="yyyy-mm-dd"/>
											</span>
										</div>
										<div class="help-block col-xs-12 col-sm-reset inline"></div>
								    	<label class="col-xs-12 col-sm-2 control-label no-padding-right">
											民族
										</label>
										<div class="col-xs-12 col-sm-3">
											<span class="block input-icon input-icon-right">
												<select name="nation"
												id="add_person_nation" class="form-control" >
												<dd:options codeType="Nation"/>
												</select>
											</span>
										</div>
										<div class="help-block col-xs-12 col-sm-reset inline"></div>
									</div>
									
									<div class="form-group">
										<label class="col-xs-12 col-sm-2 control-label no-padding-right">
											婚姻状况
										</label>
										<div class="col-xs-12 col-sm-3">
											<span class="block input-icon input-icon-right">
												<span class="block input-icon input-icon-right">
												<select name="marriageCd"
												id="add_person_marriageCd" class="form-control" >
												<dd:options codeType="MaritalStatus"/>
												</select>
											</span>
											</span>
										</div>
										<div class="help-block col-xs-12 col-sm-reset inline"></div>
								    	<label class="col-xs-12 col-sm-2 control-label no-padding-right">
											最高学历
										</label>
										<div class="col-xs-12 col-sm-3">
											<span class="block input-icon input-icon-right">
												<select name="degreeCd"
												id="add_person_degreeCd" class="form-control" >
												<dd:options codeType="DegreeCode"/>
												</select>
											</span>
										</div>
										<div class="help-block col-xs-12 col-sm-reset inline"></div>
									</div>
									
									<div class="form-group">
										<label class="col-xs-12 col-sm-2 control-label no-padding-right">
											联系电话
										</label>
										<div class="col-xs-12 col-sm-3">
											<span class="block input-icon input-icon-right">
												<input type="text" name="contactTelNum" 
												id="add_person_contactTelNum" 
												class="form-control" value="" />
											</span>
										</div>
										<div class="help-block col-xs-12 col-sm-reset inline"></div>
								    	<label class="col-xs-12 col-sm-2 control-label no-padding-right">
											家庭电话
										</label>
										<div class="col-xs-12 col-sm-3">
											<span class="block input-icon input-icon-right">
												<input type="text" name="familyTelNum" 
												id="add_person_familyTelNum" 
												class="form-control" value="" />
											</span>
										</div>
										<div class="help-block col-xs-12 col-sm-reset inline"></div>
									</div>
									
									<div class="form-group">
										<label class="col-xs-12 col-sm-2 control-label no-padding-right">
											居住类型
										</label>
										<div class="col-xs-12 col-sm-3">
											<span class="block input-icon input-icon-right">
												<select name="inhabitancyStatus"
												id="add_person_inhabitancyStatus" class="form-control" >
												<dd:options codeType="InhabitancyStatus" selected="6"/>
												</select>
											</span>
										</div>
										<div class="help-block col-xs-12 col-sm-reset inline"></div>
								    	<label class="col-xs-12 col-sm-2 control-label no-padding-right">
											邮箱/QQ
										</label>
										<div class="col-xs-12 col-sm-3">
											<span class="block input-icon input-icon-right">
												<input type="text" name="mailQq" 
												id="add_person_mailQq" 
												class="form-control" value="" />
											</span>
										</div>
										<div class="help-block col-xs-12 col-sm-reset inline"></div>
									</div>
									
									<div class="form-group add_person_residence" >
										<label class="col-xs-12 col-sm-2 control-label no-padding-right">
											位置
										</label>
										<div class="col-xs-12 col-sm-3">
											<span class="block input-icon input-icon-right">
												<input type="text" name="residencePosition"
												id="add_person_residencePosition" class="form-control" />
											</span>
										</div>
										<div class="help-block col-xs-12 col-sm-reset inline"></div>
								    	<label class="col-xs-12 col-sm-2 control-label no-padding-right">
											面积
										</label>
										<div class="col-xs-12 col-sm-3">
											<span class="block input-icon input-icon-right">
											<div class="input-group">
												<input type="text" name="residenceArea" 
												id="add_person_residenceArea" 
												class="form-control" value="" />
												 <span class="input-group-addon">平方米</span>
												</div>
											</span>
										</div>
										<div class="help-block col-xs-12 col-sm-reset inline"></div>
									</div>
									
									<div class="form-group add_person_residence" >
										<label class="col-xs-12 col-sm-2 control-label no-padding-right">
											原价
										</label>
										<div class="col-xs-12 col-sm-3">
											<span class="block input-icon input-icon-right">
											<div class="input-group">
												<input type="text" name="residencePrimaryPrice"
												id="add_person_residencePrimaryPrice" class="form-control" />
												 <span class="input-group-addon">元</span>
											</div>
											</span>
										</div>
										<div class="help-block col-xs-12 col-sm-reset inline"></div>
								    	<label class="col-xs-12 col-sm-2 control-label no-padding-right">
											现值
										</label>
										<div class="col-xs-12 col-sm-3">
											<span class="block input-icon input-icon-right">
											<div class="input-group">
												<input type="text" name="residenceNowPrice" 
												id="add_person_residenceNowPrice" 
												class="form-control" value="" />
												 <span class="input-group-addon">元</span>
											</div>
											</span>
										</div>
										<div class="help-block col-xs-12 col-sm-reset inline"></div>
									</div>
									
									<div class="form-group ">
										<label class="col-xs-12 col-sm-2 control-label no-padding-right">
											家庭详细住址
										</label>
										<div class="col-xs-12 col-sm-3">
											<span class="block input-icon input-icon-right">
												<input type="text" name="familyAddress"
												id="add_person_familyAddress" class="form-control" />
											</span>
										</div>
										<div class="help-block col-xs-12 col-sm-reset inline"></div>
								    	<label class="col-xs-12 col-sm-2 control-label no-padding-right">
											户籍所在地
										</label>
										<div class="col-xs-12 col-sm-3">
											<span class="block input-icon input-icon-right">
												<input type="text" name="permanentAddress" 
												id="add_person_permanentAddress" 
												class="form-control" value="" />
											</span>
										</div>
										<div class="help-block col-xs-12 col-sm-reset inline"></div>
									</div>
									
									<div class="form-group add_person_invesment"  >
										<label class="col-xs-12 col-sm-2 control-label no-padding-right ">
											出资方式* 
										</label>
										<div class="col-xs-12 col-sm-10 ">
											<span class="block input-icon input-icon-right ">
												<dd:each var="codePerson" codeType="InvestmentTypeCdarray">
													<label class="checkbox-inline">
														<input name="investmentTypeCd" type="checkbox" 
														class="ace" value="${codePerson.codeValue}" 
														disabled="disabled" />
														<span class="lbl">
													    	${codePerson.codeName }
														</span>
													</label>
												</dd:each>
											</span>
										</div>
										<div class="help-block col-xs-12 col-sm-reset inline"></div>
									</div>
									
									<div class="form-group add_person_invesment" style="display:none;">
										<label class="col-xs-12 col-sm-2 control-label no-padding-right">
											投资金额*
										</label>
										<div class="col-xs-12 col-sm-3">
											<span class="block input-icon input-icon-right">
											<div class="input-group">
												<input type="text" name="investmentAmt"
												disabled="disabled"
												id="add_person_investmentAmt" class="form-control" />
												 <span class="input-group-addon">万元</span>
											</div>
											</span>
										</div>
										<div class="help-block col-xs-12 col-sm-reset inline"></div>
								    	<label class="col-xs-12 col-sm-2 control-label no-padding-right">
											持股比例*
										</label>
										<div class="col-xs-12 col-sm-3">
											<span class="block input-icon input-icon-right">
											<div class="input-group">
												<input type="text" name="stockProportion" 
												disabled="disabled"
												id="add_person_stockProportion" 
												class="form-control" value="" />
												 <span class="input-group-addon">%</span>
											</div>
											</span>
										</div>
										<div class="help-block col-xs-12 col-sm-reset inline"></div>
									</div>
									
									<div class="form-group ">
										<label class="col-xs-12 col-sm-2 control-label no-padding-right">
											币种
										</label>
										<div class="col-xs-12 col-sm-3">
											<span class="block input-icon input-icon-right">
												<input type="text"  id="add_person_investmentCurrencyType_text"
												class="form-control" value="人民币" readonly="readonly"/>
												<input type="hidden" name="investmentCurrencyType" value="156" />
												<%--<dd:options codeType="CurrencyType" selected="156"/> --%>
											</span>
										</div>
										<div class="help-block col-xs-12 col-sm-reset inline"></div>
										<div class="help-block col-xs-12 col-sm-reset inline"></div>
									</div>
									</div>
									<div id="personinfo-tab-2" class="tab-pane">
										<div class="form-group ">
											<label class="col-xs-12 col-sm-2 control-label no-padding-right">
												工作单位
											</label>
											<div class="col-xs-12 col-sm-3">
												<span class="block input-icon input-icon-right">
													<input type="text" name="workCompany" 
													id="add_person_workCompany" 
													class="form-control" value="" />
												</span>
											</div>
											<div class="help-block col-xs-12 col-sm-reset inline"></div>
											<label class="col-xs-12 col-sm-2 control-label no-padding-right">
												职务
											</label>
											<div class="col-xs-12 col-sm-3">
												<span class="block input-icon input-icon-right">
													<select name="positionCd"
													id="add_person_positionCd" class="form-control" >
													<dd:options codeType="PositionCode" selected="0"/>
													</select>
												</span>
											</div>
											<div class="help-block col-xs-12 col-sm-reset inline"></div>
										</div>
										
										<div class="form-group ">
											<label class="col-xs-12 col-sm-2 control-label no-padding-right">
												单位类型
											</label>
											<div class="col-xs-12 col-sm-3">
												<span class="block input-icon input-icon-right">
													<select name="workCompanyType"
													id="add_person_profess" class="form-control" >
													<dd:options codeType="CompanyType" selected="9"/>
													</select>
												</span>
											</div>
											<div class="help-block col-xs-12 col-sm-reset inline"></div>
											<label class="col-xs-12 col-sm-2 control-label no-padding-right">
												单位电话
											</label>
											<div class="col-xs-12 col-sm-3">
												<span class="block input-icon input-icon-right">
													<span class="block input-icon input-icon-right">
													<input type="text" name="workCompanyTel" 
														id="add_person_workCompanyTel" 
														class="form-control" value="" />
													</span>
													
												</span>
											</div>
											<div class="help-block col-xs-12 col-sm-reset inline"></div>
										</div>
										
										<div class="form-group ">
											<label class="col-xs-12 col-sm-2 control-label no-padding-right">
												岗位
											</label>
											<div class="col-xs-12 col-sm-3">
												<span class="block input-icon input-icon-right">
													<select name="professional"
													id="add_person_professional" class="form-control" >
													<dd:options codeType="Professional" selected="7"/>
													</select>
												</span>
											</div>
											<div class="help-block col-xs-12 col-sm-reset inline"></div>
											<label class="col-xs-12 col-sm-2 control-label no-padding-right">
												单位详细地址
											</label>
											<div class="col-xs-12 col-sm-3">
												<span class="block input-icon input-icon-right">
													<span class="block input-icon input-icon-right">
													<input type="text" name="companyAddress" 
														id="add_person_companyAddress" 
														class="form-control" value="" />
													</span>
												</span>
											</div>
											<div class="help-block col-xs-12 col-sm-reset inline"></div>
										</div>
									</div>
									<div id="personinfo-tab-3" class="tab-pane">
										<div class="form-group ">
											<label class="col-xs-12 col-sm-2 control-label no-padding-right">
												基本工资
											</label>
											<div class="col-xs-12 col-sm-3">
												<span class="block input-icon input-icon-right">
												<div class="input-group">
													<input type="text" name="incomeYear" 
														id="add_person_incomeYear" 
														class="form-control" value="" />
														<span class="input-group-addon">元/年</span>
														</div>
												</span>
											</div>
											<div class="help-block col-xs-12 col-sm-reset inline"></div>
											<label class="col-xs-12 col-sm-2 control-label no-padding-right">
												奖金及其他收入
											</label>
											<div class="col-xs-12 col-sm-3">
												<span class="block input-icon input-icon-right">
													<span class="block input-icon input-icon-right">
														<div class="input-group">
													<input type="text" name="incomeBonusYear" 
														id="add_person_incomeBonusYear" 
														class="form-control" value="" />
														<span class="input-group-addon">元/年</span>
														</div>
													</span>
												</span>
											</div>
											<div class="help-block col-xs-12 col-sm-reset inline"></div>
										</div>
										
										<div class="form-group ">
											<label class="col-xs-12 col-sm-2 control-label no-padding-right">
												实际月收入
											</label>
											<div class="col-xs-12 col-sm-3">
												<span class="block input-icon input-icon-right">
												<div class="input-group">
													<input type="text" name="incomeMonthFact" 
														id="add_person_incomeMonthFact" 
														class="form-control" value="" />
														 <span class="input-group-addon">平均/元</span>
												</div>
												</span>
											</div>
											<div class="help-block col-xs-12 col-sm-reset inline"></div>
											<div class="help-block col-xs-12 col-sm-reset inline"></div>
										</div>
										
										<div class="form-group ">
											<label class="col-xs-12 col-sm-2 control-label no-padding-right">
												家庭总资产
											</label>
											<div class="col-xs-12 col-sm-3">
												<span class="block input-icon input-icon-right">
													<span class="block input-icon input-icon-right">
													<div class="input-group">
													<input type="text" name="familyTotalAsset" 
														id="add_person_familyTotalAsset" 
														class="form-control" value="" />
														 <span class="input-group-addon">元</span>
														</div>
													</span>
												</span>
											</div>
											<div class="help-block col-xs-12 col-sm-reset inline"></div>
											<label class="col-xs-12 col-sm-2 control-label no-padding-right">
												家庭总负债
											</label>
											<div class="col-xs-12 col-sm-3">
												<span class="block input-icon input-icon-right">
												<div class="input-group">
													<input type="text" name="familyTotalOwes" 
														id="add_person_familyTotalOwes" 
														class="form-control" value="" />
														 <span class="input-group-addon">元</span>
												</div>
												</span>
											</div>
											<div class="help-block col-xs-12 col-sm-reset inline"></div>
										</div>
										
										<div class="form-group ">
											
											<label class="col-xs-12 col-sm-2 control-label no-padding-right">
												家庭月均总收入
											</label>
											<div class="col-xs-12 col-sm-3">
												<span class="block input-icon input-icon-right">
													<span class="block input-icon input-icon-right">
													<div class="input-group">
													<input type="text" name="familyMonthlyTotalAmt" 
														id="add_person_familyMonthlyTotalAmt" 
														class="form-control" value="" />
														 <span class="input-group-addon">元</span>
													</div>
													</span>
												</span>
											</div>
											<div class="help-block col-xs-12 col-sm-reset inline"></div>
											<label class="col-xs-12 col-sm-2 control-label no-padding-right">
												家庭月均总支出
											</label>
											<div class="col-xs-12 col-sm-3">
												<span class="block input-icon input-icon-right">
												<div class="input-group">
													<input type="text" name="familyMonthlyTotalPayout" 
														id="add_person_familyMonthlyTotalPayout" 
														class="form-control" value="" />
														 <span class="input-group-addon">元</span>
												</div>
												</span>
											</div>
											<div class="help-block col-xs-12 col-sm-reset inline"></div>
										</div>
											
										<div class="form-group ">
											<label class="col-xs-12 col-sm-2 control-label no-padding-right">
												备注
											</label>
											<div class="col-xs-12 col-sm-3">
												<span class="block input-icon input-icon-right">
													<span class="block input-icon input-icon-right">
													<input type="text" name="creditRemark" 
														id="add_person_creditRemark" 
														class="form-control" value="" />
													</span>
												</span>
											</div>
											<div class="help-block col-xs-12 col-sm-reset inline"></div>
											<div class="help-block col-xs-12 col-sm-reset inline"></div>
										</div>
										
										<div class="form-group ">
											<label class="col-xs-12 col-sm-2 control-label no-padding-right">
												信贷记录
											</label>
											<div class="col-xs-12 col-sm-3">
												<span class="block input-icon input-icon-right">
													<select name="creditRecord"
													id="add_person_creditRecord" class="form-control" >
													<option value="9">未选择</option>
													<option value="1">有</option>
													<option value="0">无</option>
													</select>
												</span>
											</div>
											<div class="help-block col-xs-12 col-sm-reset inline"></div>
											<span id="add_person_creditRecordOverdue_warp" class="hide">
												<label class="col-xs-12 col-sm-2 control-label no-padding-right">
													以前信贷历史
												</label>
												<div class="col-xs-12 col-sm-3">
													<span class="block input-icon input-icon-right">
														<select name="creditRecordOverdue"
														id="add_person_creditRecordOverdue" 
														class="form-control" >
														<option value="9">未选择</option>
														<dd:options codeType="CreditHistoryType"/>
														</select>
													</span>
												</div>
											</span>
											<div class="help-block col-xs-12 col-sm-reset inline"></div>
										</div>
									</div>
								</div>
							</div>
					
					
					
					
					
					</div>
				</div>
				
				</div>
				<div class="modal-footer center">
					<div id="err_msg_field__add_person" class="red"></div>
					<button type="submit" class="btn btn-sm btn-primary" 
						id="btn_add_person">
						<i class="ace-icon fa fa-floppy-o"></i> 保存
					</button>
					<button class="btn btn-sm btn-default" data-dismiss="modal">
						<i class="ace-icon fa fa-times"></i> 取消
					</button>
				</div>
			</form>
		</div>
	</div>
</div>