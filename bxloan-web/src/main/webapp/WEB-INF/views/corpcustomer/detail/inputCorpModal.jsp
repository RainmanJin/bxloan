<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.coamc-tech.com/taglibs/datadict" prefix="dd" %>
<div id="add_corp_modal" class="modal" tabindex="-1" data-backdrop="static">
	<div class="modal-dialog" style="width:70%">
		<div class="modal-content">
			<form id="add_corp_form" action="" class="form-horizontal" >
			
				<input id="add_corp_partyId" type="hidden" name="partyId" value="${corpCus.partyId }"/>
				
				<input type="hidden" name="id" id="add_corp_corp_id" value="" />
				
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="blue bigger">
						<i class='ace-icon fa fa-plus'></i> 新增
					</h4>
				</div>
				<div class="modal-body">
				
				<div class="row">
					<div class="col-md-12">
					<h4 class="blue">
           				概况信息
       				</h4>
					
					<div class="form-group">
						<label class="col-xs-12 col-sm-2 control-label no-padding-right ">
							选择类型* 
						</label>
						<div class="col-xs-12 col-sm-10 ">
							<span class="block input-icon input-icon-right ">
								<label class="checkbox-inline">
									<input name="corpType" id="corpPersonActController" type="checkbox" 
									class="ace add_corp_Type" value="3" />
									<span class="lbl">
								    	实际控制人
									</span>
								</label>
								
								<label class="checkbox-inline" >
									<input name="corpType" type="checkbox" 
									id="add_corp_stock_check"
									class="ace add_corp_Type" value="1" />
									<span class="lbl">
								    	股东
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
									<input type="text" name="corpName" id="add_corp_corpName" 
									class="form-control" value="" />
								</span>
								<span class="input-group-btn">
						        	<button role="showExistingParty" 
					        		partyType="1" disabled="disabled"
						        	class="btn btn-sm btn-yellow" 
						        	type="button">
						        		<i class="ace-icon fa fa-eye"></i>
						        	</button>
						      	</span>
							</div>
						</div>
						<div class="help-block col-xs-12 col-sm-reset inline"></div>
						<label class="col-xs-12 col-sm-2 control-label no-padding-right">
							组织机构代码*
						</label>
						<div class="col-xs-12 col-sm-3">
							<span class="block input-icon input-icon-right">
								<input type="text" name="certificateCd"
								id="add_corp_certificateCd" class="form-control" 
								value="" />
							</span>
						</div>
						<input id="add_corp_certificate_type" value="210" type="hidden"/>
						<div class="help-block col-xs-12 col-sm-reset inline"></div>
					</div>
					
			        <div class="form-group">
						<label class="col-xs-12 col-sm-2 control-label no-padding-right">
							组织机构类型*
						</label>
						<div class="col-xs-12 col-sm-3">
							<span class="block input-icon input-icon-right">
								<select name="certificateTypeCd" 
								id="add_corp_certificateTypeCd" class="form-control">
									<dd:options codeType="OrganizationType"/>
			                    </select>
							</span>
						</div>
						<div class="help-block col-xs-12 col-sm-reset inline"></div>
					    <label class="col-xs-12 col-sm-2 control-label no-padding-right">
							营业执照号码*
						</label>
						<div class="col-xs-12 col-sm-3">
							<span class="block input-icon input-icon-right">
							 	<input type="text" name="businessLicenseNum" 
								id="add_corp_businessLicenseNum" class="form-control"  
								value="" />
							</span>
						</div>
						<div class="help-block col-xs-12 col-sm-reset inline"></div>
					</div>
					
					<div class="form-group">
						<label class="col-xs-12 col-sm-2 control-label no-padding-right">
							税务登记号(国税)*
						</label>
						<div class="col-xs-12 col-sm-3">
							<span class="block input-icon input-icon-right">
								<input type="text" name="taxRegistrationNumNational" 
								id="add_corp_taxRegistrationNumNational" class="form-control" 
								value="" />
							</span>
						</div>
						<div class="help-block col-xs-12 col-sm-reset inline"></div>
				    	<label class="col-xs-12 col-sm-2 control-label no-padding-right">
							税务登记号(地税)*
						</label>
						<div class="col-xs-12 col-sm-3">
							<span class="block input-icon input-icon-right">
								<input type="text" name="taxRegistrationNumLocal" 
								id="add_corp_taxRegistrationNumLocal" class="form-control" 
								value="" />
							</span>
						</div>
						<div class="help-block col-xs-12 col-sm-reset inline"></div>
					</div> 
					
					<div class="form-group">
						<label class="col-xs-12 col-sm-2 control-label no-padding-right">
							所有制类型*
						</label>
						<div class="col-xs-12 col-sm-3">
							<span class="block input-icon input-icon-right">
								<select name="orgTypeCd" id="add_corp_orgTypeCd" class="form-control">
									<dd:options codeType="CompanyType" selected="9"/>
			                    </select>
							</span>
						</div>
						<div class="help-block col-xs-12 col-sm-reset inline"></div>
				    	<label class="col-xs-12 col-sm-2 control-label no-padding-right">
							币种
						</label>
						<div class="col-xs-12 col-sm-3">
							<span class="block input-icon input-icon-right">
								<input type="text"  class="form-control" id="add_corp_currencyCd_text" 
								value="人民币" readonly="readonly" />
								<input type="hidden" name="currencyCd" value="100"/>
							</span>
						</div>
						<div class="help-block col-xs-12 col-sm-reset inline"></div>
					</div>
					<div class="form-group">
						<label class="col-xs-12 col-sm-2 control-label no-padding-right">
							实收资本*
						</label>
						<div class="col-xs-12 col-sm-3">
							<span class="block input-icon input-icon-right">
							<div class="input-group">
								<input type="text" name="actualRevAmt" 
								id="add_corp_actualRevAmt" class="form-control" 
								value="" />
								 <span class="input-group-addon">万元</span>
							</div>
							</span>
						</div>
						<div class="help-block col-xs-12 col-sm-reset inline"></div>
				    	<label class="col-xs-12 col-sm-2 control-label no-padding-right">
							注册资本*
						</label>
						<div class="col-xs-12 col-sm-3">
							<span class="block input-icon input-icon-right">
							<div class="input-group">
								<input type="text" name="registeredCapital" 
								id="add_corp_registeredCapital" class="form-control" 
								value="" />
								 <span class="input-group-addon">万元</span>
							</div>
							</span>
						</div>
						<div class="help-block col-xs-12 col-sm-reset inline"></div>
					</div>
					
					<div class="form-group">
						<label class="col-xs-12 col-sm-2 control-label no-padding-right">
							联系人名称*
						</label>
						<div class="col-xs-12 col-sm-3">
							<span class="block input-icon input-icon-right">
								<input type="text" name="contactorName" 
								id="add_corp_contactorName" class="form-control" 
								value="" />
							</span>
						</div>
						<div class="help-block col-xs-12 col-sm-reset inline"></div>
						<label class="col-xs-12 col-sm-2 control-label no-padding-right">
							联系人电话*
						</label>
						<div class="col-xs-12 col-sm-3">
							<span class="block input-icon input-icon-right">
								<input type="text" name="contactorTelNum" 
								id="add_corp_contactorTelNum" class="form-control" 
								value="" />
							</span>
						</div>
						<div class="help-block col-xs-12 col-sm-reset inline"></div>
					</div>
					
					<div class="form-group">
						<label class="col-xs-12 col-sm-2 control-label no-padding-right">
							贷款卡号
						</label>
						<div class="col-xs-12 col-sm-3">
							<span class="block input-icon input-icon-right">
								<input type="text" name="loanCardNum" 
								id="add_corp_loanCardNum" class="form-control" 
								value="" />
							</span>
						</div>
						<div class="help-block col-xs-12 col-sm-reset inline"></div>
				    	<label class="col-xs-12 col-sm-2 control-label no-padding-right">
							公司成立时间*
						</label>
						<div class="col-xs-12 col-sm-3">
							<span class="block input-icon input-icon-right">
								<input type="text" name="corpEstablishDate" 
								id="add_corp_corpEstablishDate" 
								class="form-control date-picker" 
								data-date-format="yyyy-mm-dd"
								value="" />
							</span>
						</div>
						<div class="help-block col-xs-12 col-sm-reset inline"></div>
					</div> 
					
					<div class="form-group">
						<label class="col-xs-12 col-sm-2 control-label no-padding-right">
							注册地址
						</label>
						<div class="col-xs-12 col-sm-3">
							<span class="block input-icon input-icon-right">
								<input type="text" name="addressRegist" 
								id="add_corp_addressRegist" class="form-control" 
								value="" />
							</span>
						</div>
						<div class="help-block col-xs-12 col-sm-reset inline"></div>
						<label class="col-xs-12 col-sm-2 control-label no-padding-right">
							通讯地址*
						</label>
						<div class="col-xs-12 col-sm-3">
							<span class="block input-icon input-icon-right">
								<input type="text" name="addressContact" 
								id="add_corp_addressContact" class="form-control" 
								value="" />
							</span>
						</div>
						<div class="help-block col-xs-12 col-sm-reset inline"></div>
					</div>
					<hr/>
					<div class="form-group add_corp_investment" style="display: none;" >
						<label class="col-xs-12 col-sm-2 control-label no-padding-right ">
							出资方式* 
						</label>
						<div class="col-xs-12 col-sm-10 ">
							<span class="block input-icon input-icon-right ">
								<dd:each var="codeCorp" codeType="InvestmentTypeCdarray">
									<label class="checkbox-inline">
										<input name="investmentTypeCd" type="checkbox" 
										disabled="disabled"
										class="ace" value="${codeCorp.codeValue }" />
										<span class="lbl">
									    	${codeCorp.codeName }
										</span>
									</label>
								</dd:each>
							</span>
						</div>
						<div class="help-block col-xs-12 col-sm-reset inline"></div>
					</div>
					
					<div class="form-group add_corp_investment" style="display: none;" >
						<label class="col-xs-12 col-sm-2 control-label no-padding-right">
							投资金额*
						</label>
						<div class="col-xs-12 col-sm-3">
							<span class="block input-icon input-icon-right">
							<div class="input-group">
								<input type="text" name="investmentAmt"
								disabled="disabled"
								id="add_corp_investmentAmt" class="form-control" />
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
								id="add_corp_stockProportion" 
								disabled="disabled"
								class="form-control" value="" />
								 <span class="input-group-addon">%</span>
								</div>
							</span>
						</div>
						<div class="help-block col-xs-12 col-sm-reset inline"></div>
					</div>
					
					
					</div>
				</div>
				
				</div>
				<div class="modal-footer center">
					<button type="submit" class="btn btn-sm btn-primary" 
						id="btn_add_corp">
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