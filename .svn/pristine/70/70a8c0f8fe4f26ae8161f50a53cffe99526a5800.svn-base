<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.coamc-tech.com/taglibs/datadict" prefix="dd" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<form class="form-horizontal" action="" method="post" role="form"  id="form_basicInfo" >
	<input type="hidden" name="partyId" value="${corpCus.partyId }"/>
	<input type="hidden" id="consultLocation" value="${consultLocation }"/>
	<input type="hidden" id="form_basicInfo_submitType" value=""/>
	<div class="col-xs-12  ">
		<div class="form-group">
			<label class="col-xs-12 col-sm-2 control-label no-padding-right">
				客户类型<span class='red'>*</span> 
			</label>
			<div class="col-xs-12 col-sm-10">
				<c:if test="${consultLocation == 'businessAdd' }">
					<span class="block input-icon input-icon-right ">
	                    <label class="checkbox-inline">
							<input name="markType" type="checkbox" class="ace" value="01" />
							<span class="lbl">借款人</span>
						</label>
						<label class="checkbox-inline">
							<input type="checkbox" class="ace" checked='checked' disabled="disabled" />
							<span class="lbl">担保人</span>
						</label>
						<input type="hidden" name="markType" value="02">
					</span>
				</c:if>
				<c:if test="${consultLocation != 'businessAdd' }">
					<span class="block input-icon input-icon-right ">
	                    <label class="checkbox-inline">
							<input name="markType" type="checkbox" 
							class="ace" value="01" 
							
							<c:if test="${fn:contains(corpCus.markType,'01')}">
							checked='checked'
							</c:if>
							/>
							<span class="lbl">
						    	借款人
							</span>
						</label>
						<label class="checkbox-inline">
							<input name="markType" type="checkbox" 
							class="ace" value="02" 
							<c:if test="${fn:contains(corpCus.markType,'02')}">
							checked='checked'
							</c:if>
							/>
							<span class="lbl">
						    	担保人
							</span>
						</label>
					</span>
				</c:if>
			</div>
			<div class="help-block col-xs-12 col-sm-reset inline"></div>
		</div>       				
        <div class="form-group">
			<label class="col-xs-12 col-sm-2 control-label no-padding-right">
				客户名称<span class='red'>*</span>
			</label>
			<div class="col-xs-12 col-sm-3">
				<span class="block input-icon input-icon-right">
					<input type="text" name="customerName" id="customerName" 
					class="form-control" value="${corpCus.customerName }" />
				</span>
			</div>
			<div class="help-block col-xs-12 col-sm-reset inline"></div>
	    	<label class="col-xs-12 col-sm-2 control-label no-padding-right">
				客户编号
			</label>
			<div class="col-xs-12 col-sm-3">
				<span class="block input-icon input-icon-right">
					<input type="text"  
					id="customerNum" class="form-control"  readonly="readonly"
					value="${corpCus.customerNum }" />
				</span>
			</div>
			<div class="help-block col-xs-12 col-sm-reset inline"></div>
		</div>
		
                                				
        <div class="form-group">
			<label class="col-xs-12 col-sm-2 control-label no-padding-right">
				组织机构代码
			</label>
			<div class="col-xs-12 col-sm-3">
				<span class="block input-icon input-icon-right">
					<input type="text" 
					id="certificateNum" class="form-control"  readonly="readonly"
					value="${corpCus.certificateNum }" />
				</span>
			</div>
			<div class="help-block col-xs-12 col-sm-reset inline"></div>
		    <label class="col-xs-12 col-sm-2 control-label no-padding-right">
				客户来源<span class='red'>*</span>
			</label>
			<div class="col-xs-12 col-sm-3">
				<span class="block input-icon input-icon-right">
					<select  id="" name="customerSource" class="form-control" >
						<option value=""></option>
						<dd:options codeType="CustomerSource" selected="${corpCus.customerSource}"/>						
                    </select>
				</span>
			</div>
			<div class="help-block col-xs-12 col-sm-reset inline"></div>
		</div>
                                                
        <div class="form-group">
			<label class="col-xs-12 col-sm-2 control-label no-padding-right">
				行业类型<span class='red'>*</span>
			</label>
			<div class="col-xs-12 col-sm-3">
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
			<div class="help-block col-xs-12 col-sm-reset inline"></div>
		    <label class="col-xs-12 col-sm-2 control-label no-padding-right">
				营业执照号码<span class='red'>*</span>
			</label>
			<div class="col-xs-12 col-sm-3">
				<span class="block input-icon input-icon-right">
				 	<input type="text" name="businessLicenseNum" 
					id="businessLicenseNum" class="form-control"  
					value="${corpCus.businessLicenseNum }" />
				</span>
			</div>
			<div class="help-block col-xs-12 col-sm-reset inline"></div>
		</div>
		
		<div class="form-group">
			<label class="col-xs-12 col-sm-2 control-label no-padding-right">
				币种
			</label>
			<div class="col-xs-12 col-sm-3">
				<span class="block input-icon input-icon-right">
					<input type="text" class="form-control valid" 
						value="人民币" readonly="readonly" aria-invalid="false">
					<input type="hidden" name="investmentCurrencyType" value="156">
				</span>
			</div>
			<div class="help-block col-xs-12 col-sm-reset inline"></div>
	    	<label class="col-xs-12 col-sm-2 control-label no-padding-right">
				注册资本<span class='red'>*</span>
			</label>
			<div class="col-xs-12 col-sm-3">
				<span class="block input-icon input-icon-right">
				<div class="input-group">
					<input type="text" name="registeredCapital" 
					id="basic_registeredCapital" class="form-control" 
					value="${corpCus.registeredCapital }" />
					 <span class="input-group-addon">万元</span>
				</div>
				</span>
			</div>
			<div class="help-block col-xs-12 col-sm-reset inline"></div>
		</div>
		
		<div class="form-group">
			<label class="col-xs-12 col-sm-2 control-label no-padding-right">
				实收资本<span class='red'>*</span>
			</label>
			<div class="col-xs-12 col-sm-3">
				<span class="block input-icon input-icon-right">
					<div class="input-group">
					<input type="text" name="actualRevAmt" 
					id="basic_actualRevAmt" class="form-control" 
					value="${corpCus.actualRevAmt }" />
					 <span class="input-group-addon">万元</span>
					</div>
				</span>
			</div>
			<div class="help-block col-xs-12 col-sm-reset inline"></div>
	    	<label class="col-xs-12 col-sm-2 control-label no-padding-right">
				公司成立时间<span class='red'>*</span>
			</label>
			<div class="col-xs-12 col-sm-3">
				<span class="block input-icon input-icon-right">
					<input class="form-control date-picker" 
					id="basic_standedDate"  
					name="standedDate" 
					placeholder="" 
					value="<fmt:formatDate value="${corpCus.standedDate }" pattern="yyyy-MM-dd"/>"
					type="text" data-date-format="yyyy-mm-dd"/>
	
				</span>
			</div>
			<div class="help-block col-xs-12 col-sm-reset inline"></div>
		</div> 
		
		<div class="form-group">
			<label class="col-xs-12 col-sm-2 control-label no-padding-right">
				税务登记号(国税)<span class='red'>*</span>
			</label>
			<div class="col-xs-12 col-sm-3">
				<span class="block input-icon input-icon-right">
					<input type="text" name="nationalTaxRegistrationNum" 
					id="basic_nationalTaxRegistrationNum" class="form-control" 
					value="${corpCus.nationalTaxRegistrationNum }" />
				</span>
			</div>
			<div class="help-block col-xs-12 col-sm-reset inline"></div>
	    	<label class="col-xs-12 col-sm-2 control-label no-padding-right">
				税务登记号(地税)<span class='red'>*</span>
			</label>
			<div class="col-xs-12 col-sm-3">
				<span class="block input-icon input-icon-right">
					<input type="text" name="localTaxRegistrationCum" 
					id="basic_localTaxRegistrationCum" class="form-control" 
					value="${corpCus.localTaxRegistrationCum }" />
				</span>
			</div>
			<div class="help-block col-xs-12 col-sm-reset inline red" id="basic_tax_warn"></div>
		</div> 
		
		<div class="form-group">
			<label class="col-xs-12 col-sm-2 control-label no-padding-right">
				评级行业类型<span class='red'>*</span>
			</label>
			<div class="col-xs-12 col-sm-3">
				<span class="block input-icon input-icon-right">
					<select name="ratingIndustryType" id="" class="form-control">
						<dd:options codeType="RatingIndustryType" selected="${corpCus.ratingIndustryType }"/>
                    </select>
				</span>
			</div>
			<div class="help-block col-xs-12 col-sm-reset inline"></div>
	    	<label class="col-xs-12 col-sm-2 control-label no-padding-right">
				所有制类别<span class='red'>*</span>
			</label>
			<div class="col-xs-12 col-sm-3">
				<span class="block input-icon input-icon-right">
					<select name="orgTypeCd" id="" class="form-control">
						<dd:options codeType="OrgTypeCd" selected="${corpCus.orgTypeCd }"/>
                    </select>
				</span>
			</div>
			<div class="help-block col-xs-12 col-sm-reset inline"></div>
		</div>
		
		<div class="form-group">
			<label class="col-xs-12 col-sm-2 control-label no-padding-right">
				企业客户特征<span class='red'>*</span>
			</label>
			<div class="col-xs-12 col-sm-3">
				<span class="block input-icon input-icon-right">
					<select name="corCustomerFeature" id="" class="form-control">
						<dd:options codeType="CorporationCustomerFeature" 
						selected="${corpCus.corCustomerFeature}"/>
                    </select>
				</span>
			</div>
			<div class="help-block col-xs-12 col-sm-reset inline"></div>
	    	<label class="col-xs-12 col-sm-2 control-label no-padding-right">
				贷款卡号码
			</label>
			<div class="col-xs-12 col-sm-3">
				<span class="block input-icon input-icon-right">
					<input type="text" name="loanCardNum" 
					id="loanCardNum" class="form-control" 
					value="${corpCus.loanCardNum}" />
				</span>
			</div>
			<div class="help-block col-xs-12 col-sm-reset inline"></div>
		</div>
		
		<div class="form-group">
			<label class="col-xs-12 col-sm-2 control-label no-padding-right">
				资产规模<span class='red'>*</span>
			</label>
			<div class="col-xs-12 col-sm-3">
				<span class="block input-icon input-icon-right">
					<select name="capitalScale" id="capitalScale" class="form-control">
						<dd:options codeType="CapitalScale"
						selected="${corpCus.capitalScale}"/>
                    </select>
				</span>
			</div>
			<div class="help-block col-xs-12 col-sm-reset inline"></div>
	    	<label class="col-xs-12 col-sm-2 control-label no-padding-right">
				客户规模<span class='red'>*</span>
			</label>
			<div class="col-xs-12 col-sm-3">
				<span class="block input-icon input-icon-right">
					<select name="customerScale" id="customerScale" class="form-control">
						<dd:options codeType="CustomerScale"
						selected="${corpCus.customerScale}"/>
                    </select>
				</span>
			</div>
			<div class="help-block col-xs-12 col-sm-reset inline"></div>
		</div>
		<div class="form-group">
			<label class="col-xs-12 col-sm-2 control-label no-padding-right">
				员工人数
			</label>
			<div class="col-xs-12 col-sm-3">
				<span class="block input-icon input-icon-right">
					<div class="input-group">
					<input type="text" name="staffNum" 
					id="basic_staffNum" class="form-control" 
					value="${corpCus.staffNum }" />
					 <span class="input-group-addon">人</span>
					</div>
				</span>
			</div>
			<div class="help-block col-xs-12 col-sm-reset inline"></div>
		</div>
		
		<div class="form-group">
			<label class="col-xs-12 col-sm-2 control-label no-padding-right">
				结算账户开户行1
			</label>
			<div class="col-xs-12 col-sm-3">
				<span class="block input-icon input-icon-right">
					<input type="text" name="settlementAccountsOne" 
					id="settlementAccountsOne" class="form-control" 
					value="${corpCus.settlementAccountsOne }" />
				</span>
			</div>
			<div class="help-block col-xs-12 col-sm-reset inline"></div>
			<label class="col-xs-12 col-sm-2 control-label no-padding-right">
				结算帐户帐号1
			</label>
			<div class="col-xs-12 col-sm-3">
				<span class="block input-icon input-icon-right">
					<input type="text" name="settlementAccountsFirst" 
					id="settlementAccountsFirst" class="form-control" 
					value="${corpCus.settlementAccountsFirst }" />
				</span>
			</div>
			<div class="help-block col-xs-12 col-sm-reset inline"></div>
		</div>
		
		<div class="form-group">
			<label class="col-xs-12 col-sm-2 control-label no-padding-right">
				结算账户开户行2
			</label>
			<div class="col-xs-12 col-sm-3">
				<span class="block input-icon input-icon-right">
					<input type="text" name="settlementAccountsTwo" 
					id="settlementAccountsTwo" class="form-control" 
					value="${corpCus.settlementAccountsTwo }" />
				</span>
			</div>
			<div class="help-block col-xs-12 col-sm-reset inline"></div>
			<label class="col-xs-12 col-sm-2 control-label no-padding-right">
				结算帐户帐号2
			</label>
			<div class="col-xs-12 col-sm-3">
				<span class="block input-icon input-icon-right">
					<input type="text" name="settlementAccountsSecond" 
					id="settlementAccountsSecond" class="form-control" 
					value="${corpCus.settlementAccountsSecond }" />
				</span>
			</div>
			<div class="help-block col-xs-12 col-sm-reset inline"></div>
		</div>
		
		<div class="form-group">
			<label class="col-xs-12 col-sm-2 control-label no-padding-right">
				联系人名称<span class='red'>*</span>
			</label>
			<div class="col-xs-12 col-sm-3">
				<span class="block input-icon input-icon-right">
					<input type="text" name="linkmanName" 
					id="basic_linkmanName" class="form-control" 
					value="${corpCus.linkmanName }" />
				</span>
			</div>
			<div class="help-block col-xs-12 col-sm-reset inline"></div>
			<label class="col-xs-12 col-sm-2 control-label no-padding-right">
				联系人电话<span class='red'>*</span>
			</label>
			<div class="col-xs-12 col-sm-3">
				<span class="block input-icon input-icon-right">
					<input type="text" name="linkmanTel" 
					id="basic_linkmanTel" class="form-control" 
					value="${corpCus.linkmanTel}" />
				</span>
			</div>
			<div class="help-block col-xs-12 col-sm-reset inline"></div>
		</div>
		
		<div class="form-group">
			<label class="col-xs-12 col-sm-2 control-label no-padding-right">
				最新维护日期<span class='red'>*</span>
			</label>
			<div class="col-xs-12 col-sm-3">
				<span class="block input-icon input-icon-right">
					<input type="text" readonly="readonly"
					id="newlyMaintenanceDate" class="form-control" 
					value="<fmt:formatDate value="${corpCus.newlyMaintenanceDate}" pattern="yyyy-MM-dd HH:mm:ss"/>" />
				</span>
			</div>
			<div class="help-block col-xs-12 col-sm-reset inline"></div>
			<label class="col-xs-12 col-sm-2 control-label no-padding-right">
				最新维护人名称<span class='red'>*</span>
			</label>
			<div class="col-xs-12 col-sm-3">
				<span class="block input-icon input-icon-right">
					<input type="text"  
					id="lastUpdateUserNum" class="form-control" readonly="readonly"
					value="${userName}" />
				</span>
			</div>
			<div class="help-block col-xs-12 col-sm-reset inline"></div>
		</div>
		<hr/>
	</div>
	
	<c:if test="${consultLocation == '' || consultLocation == 'businessAdd' }">
		<div class="col-xs-12">
		  	<div class="wizard-actions center" >
		      	<span id="gkgzSpan">
		          	<button class="btn btn-sm btn-pink" id="tempSave" 
		          	type="submit" data-loading-text="已保存" >
		              	<i class="ace-icon fa fa-floppy-o"></i> 暂存
		          	</button>
		      	</span>
		      	<c:if test="${consultLocation != 'businessAdd' }">
			      	<button class="btn btn-sm btn-pre" id="goBackButton" data-last="Finish" type="button" onclick ="javascript:history.go(-1);">
			        			<i class="ace-icon fa fa-chevron-left"></i>
			            	返回
			        </button>
			    </c:if>
	    	</div>
		</div>
	</c:if>
</form>
