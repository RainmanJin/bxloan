<!-- 
	基本项目信息
-->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../../commons/taglibs.jsp"%>

<div class="row">
	<div class="col-md-12">
		<form id="basicInfoForm" class="form-horizontal">
			<!-- hidden field begin -->
			<input type="hidden" id="bizRateId" name="bizRateId" value="${vo.bizRateId }">
			<input type="hidden" id="curUserOrgId" name="curUserOrgId" value="${vo.curUserOrgId }">
			<input type="hidden" name="productCd" value="${productCd }">
			<input type="hidden" name="projectId" value="${vo.projectId }" id="projectId">
			<input type="hidden" name="partyId" value="${vo.partyId }">
			<input type="hidden" name="workflowId" value="${vo.workflowId }">
			<input type="hidden" id="wfCode" value="${vo.wfCode }">
			<input type="hidden" id="taskStageCode" value="${vo.taskStageCode }">
			<input type="hidden" name="taskId" value="${vo.taskId }">
			<!-- hidden field begin -->
			<h4 class="blue">客户信息</h4>  
			<!-- 企业客户 -->
			<c:if test="${party.partyTypeCd == '1' }">
				<div class="form-group">
					<label class="col-xs-12 col-sm-2 control-label no-padding-right">
						客户名称：
					</label>
					<div class="col-xs-12 col-sm-3">
						<span class="block input-icon input-icon-right">
							<input type="text" class="form-control" name="customerName" disabled="disabled" value="${corporationCustomer.customerName }">
						</span>
					</div>
					<div class="help-block col-xs-12 col-sm-reset inline"></div>

					<label class="col-xs-12 col-sm-2 control-label no-padding-right">
						行业类型：
					</label>
					<div class="col-xs-12 col-sm-3">
						<span class="block input-icon input-icon-right">
							<input type="text" class="form-control" value="${industryName }" disabled="disabled">
						</span>
					</div>
					<div class="help-block col-xs-12 col-sm-reset inline"></div>
				</div>

				<div class="form-group">
					<label class="col-xs-12 col-sm-2 control-label no-padding-right">
						公司规模： 
					</label>
					<div class="col-xs-12 col-sm-3">
						<span class="block input-icon input-icon-right">
							<select class="form-control" disabled="disabled">
								<dd:options codeType="CustomerScale" selected="${corporationCustomer.customerScale }" />
							</select>
							<input type="hidden" name="customerScale" value="${corporationCustomer.customerScale }" disabled="disabled">
						</span>
					</div>
					<div class="help-block col-xs-12 col-sm-reset inline"></div>

					<label class="col-xs-12 col-sm-2 control-label no-padding-right">
						注册资本：
					</label>
					<div class="col-xs-12 col-sm-3">
						<span class="block input-icon input-icon-right">
							<div class="input-group">
								<input type="text" class="form-control num_amt_2fixed" name="registeredCapital" value="${corporationCustomer.registeredCapital }" disabled="disabled">
								<span class="input-group-addon">万元</span>
							</div>
						</span>
					</div>
					<div class="help-block col-xs-12 col-sm-reset inline"></div>
				</div>

				<div class="form-group">
					<label class="col-xs-12 col-sm-2 control-label no-padding-right">
						实收资本：
					</label>
					<div class="col-xs-12 col-sm-3">
						<span class="block input-icon input-icon-right">
							<div class="input-group">
								<input type="text" class="form-control num_amt_2fixed" name="actualRevAmt" value="${corporationCustomer.actualRevAmt }" disabled="disabled">
								<span class="input-group-addon">万元</span>
							</div>
						</span>
					</div>
					<div class="help-block col-xs-12 col-sm-reset inline"></div>

					<label class="col-xs-12 col-sm-2 control-label no-padding-right">
						所有制类别：
					</label>
					<div class="col-xs-12 col-sm-3">
						<span class="block input-icon input-icon-right">
							<select class="form-control" disabled="disabled">
								<dd:options codeType="OrgTypeCd" selected="${corporationCustomer.orgTypeCd }" />
							</select>
							<input type="hidden" name="orgTypeCd" value="${corporationCustomer.orgTypeCd }">
						</span>
					</div>
					<div class="help-block col-xs-12 col-sm-reset inline"></div>
				</div>
			</c:if>

			<!-- 个人客户 -->
			<c:if test="${party.partyTypeCd == '2' }">
				<div class="form-group">
					<label class="col-xs-12 col-sm-2 control-label no-padding-right">
						客户名称：
					</label>
					<div class="col-xs-12 col-sm-3">
						<span class="block input-icon input-icon-right">
							<input type="text" class="form-control" name="customerName" value="${individual.customerName }" disabled="disabled">
						</span>
					</div>
					<div class="help-block col-xs-12 col-sm-reset inline"></div>
					<label class="col-xs-12 col-sm-2 control-label no-padding-right">
						客户编号：
					</label>
					<div class="col-xs-12 col-sm-3">
						<span class="block input-icon input-icon-right">
							<input type="text" class="form-control" name="customerNum" value="${individual.customerNum }" disabled="disabled">
						</span>
					</div>
					<div class="help-block col-xs-12 col-sm-reset inline"></div>
				</div>

				<div class="form-group">
					<label class="col-xs-12 col-sm-2 control-label no-padding-right">
						客户经理：
					</label>
					<div class="col-xs-12 col-sm-3">
						<span class="block input-icon input-icon-right">
							<input type="text" class="form-control" name="customerManagerName" value="${customerManagerName}" disabled="disabled">
						</span>
					</div>
					<div class="help-block col-xs-12 col-sm-reset inline"></div>
					<label class="col-xs-12 col-sm-2 control-label no-padding-right">
						所属机构：
					</label>
					<div class="col-xs-12 col-sm-3">
						<span class="block input-icon input-icon-right">
							<input type="text" class="form-control" name="orgName" value="${orgName }" disabled="disabled">
						</span>
					</div>
					<div class="help-block col-xs-12 col-sm-reset inline"></div>
				</div>
				
				<div class="form-group">
					<label class="col-xs-12 col-sm-2 control-label no-padding-right">
						婚姻状况：
					</label>
					<div class="col-xs-12 col-sm-3">
						<span class="block input-icon input-icon-right">
							<select class="form-control" disabled="disabled">
								<dd:options codeType="MaritalStatus" selected="${individual.marriageCd }" />
							</select>
							<input type="hidden" name="marriageCd" id="marriageCd" value="${individual.marriageCd }">
						</span>
					</div>
					<div class="help-block col-xs-12 col-sm-reset inline"></div>
					<div class="help-block col-xs-12 col-sm-reset inline"></div>
					<label class="col-xs-12 col-sm-2 control-label no-padding-right">
						最高学历：
					</label>
					<div class="col-xs-12 col-sm-3">
						<span class="block input-icon input-icon-right">
							<select class="form-control" disabled="disabled">
								<dd:options codeType="DegreeCode" selected="${individual.degreeCd }" />
							</select>
							<input type="hidden" name="degreeCd" value="${individual.degreeCd }">
						</span>
					</div>
					<div class="help-block col-xs-12 col-sm-reset inline"></div>
				</div>
			</c:if>
			
			<h4 class="blue">项目信息</h4>
			
			<div class="form-group">
			<label class="col-xs-12 col-sm-2 control-label no-padding-right">
					<font color='red'> * </font>
					贷款产品：
				</label>
				<div class="col-xs-12 col-sm-3">
					<span class="block input-icon input-icon-right">
						<c:forEach items="${products }" var="product">
							<c:if test="${product[0] eq productCd }">
								<input type="text" class="form-control" value="${product[1] }" disabled="disabled">
								<input type="hidden" class="form-control" name="productType" value="${product[0] }">
							</c:if>
						</c:forEach>
					</span>
				</div>
				<div class="help-block col-xs-12 col-sm-reset inline"></div>

				<label class="col-xs-12 col-sm-2 control-label no-padding-right">
					<font color='red'> * </font>
					业务编号：
				</label>
				<div class="col-xs-12 col-sm-3">
					<span class="block input-icon input-icon-right">
						<input type="text" class="form-control required" id="projectNoShow" name="projectNoShow"
						value="${vo.projectNo}" disabled="disabled">
						<input type="hidden" id="projectNo" name="projectNo" value="${vo.projectNo}">
					</span>
				</div>
				<div class="help-block col-xs-12 col-sm-reset inline"></div>
			</div>

			<div class="form-group">
				<label class="col-xs-12 col-sm-2 control-label no-padding-right">
					<font color='red'> * </font>
					申报日期：
				</label>
				<div class="col-xs-12 col-sm-3">
					<span class="block input-icon input-icon-right">
						<input type="text" class="form-control required prePremiumValidate" id="applyDate" name="applyDate"
						data-date-format="yyyy-mm-dd" value="${vo.applyDate }">
					</span>
				</div>
				<div class="help-block col-xs-12 col-sm-reset inline"></div>

				<label class="col-xs-12 col-sm-2 control-label no-padding-right">
					<font color='red'> * </font>
					授信额度：
				</label>
				<div class="col-xs-12 col-sm-3">
					<span class="block input-icon input-icon-right">
						<div class="input-group">
							<input type="text" class="form-control required num_amt_2fixed isPositiveNumberTwoOfThousands" name="applyAmt" id="applyAmt" value="${vo.applyAmt }">
							<span class="input-group-addon">元</span>
						</div>
					</span>
				</div>
				<div class="help-block col-xs-12 col-sm-reset inline"></div>
			</div>

			<div class="form-group">
				<label class="col-xs-12 col-sm-2 control-label no-padding-right">
					<font color='red'>
						*
					</font>
					授信期限：
				</label>
				<div class="col-xs-12 col-sm-3">
					<span class="block input-icon input-icon-right">
						<input type="text" class="form-control required" id="applyTerm" name="applyTerm" value="${vo.applyTerm }">
					</span>
				</div>
				<div class="help-block col-xs-12 col-sm-reset inline">
				</div>

				<label class="col-xs-12 col-sm-2 control-label no-padding-right">
					<font color='red'> * </font>
					授信期限单位：
				</label>
				<div class="col-xs-12 col-sm-3">
					<select class="form-control prePremiumValidate" name="applyTermUnit" disabled="disabled" id="applyTermUnitSelect">
						<dd:options codeType="TermUnitCd" selected="${vo.applyTermUnit}" excludes="1" />
					</select>
					<input type="hidden" id="applyTermUnitForBiz" value="${vo.applyTermUnit }" class="">
					<input type="hidden" id="applyTermUnitFromProduct" value="${productConfig.replyPeriodUnit }">
				</div>
				<div class="help-block col-xs-12 col-sm-reset inline"></div>
			</div>


			<div class="form-group">
				<div class="row"></div>
			</div>

			<div class="form-group">
				<label class="col-xs-12 col-sm-2 control-label no-padding-right">
					<font color='red'> * </font>
					授信类型：
				</label>
				<div class="col-xs-12 col-sm-3">
					<span class="block input-icon input-icon-right">
						<select class="form-control required" name="creditType" id="creditType">
							<dd:options codeType="CreditType" selected="${vo.creditType }" />
						</select>
					</span>
				</div>
				<div class="help-block col-xs-12 col-sm-reset inline"></div>

				<label class="col-xs-12 col-sm-2 control-label no-padding-right">
					<font color='red'> * </font>投放行业： </label>
				<div class="col-xs-12 col-sm-3">
					<span class="block input-icon input-icon-right">
						<div class="input-group">
							<input type="text" id="industryCdMask_" name="industryCdMask" class="form-control required" readonly="readonly">
							<span class="input-group-btn">
								<button id="showTree_" class="btn btn-sm btn-yellow" type="button">
									<i class="ace-icon fa fa-eye">
									</i>
								</button>
							</span>
							<input type="hidden" id="industryTypeCdField_" name="investmentIndustry" value="${vo.investmentIndustry}" />
							<input type="hidden" id="industryCd_" name="industryCd" class="form-control" value="${vo.investmentIndustry}" >
						</div>
					</span>
					<span class="block input-icon input-icon-right">
						<div id="controlZTree" style="display:none;">
							<div class="col-xs-12" style="overflow-y: auto;max-height: 500px;position:absolute;z-index:999;background:#fff;border:1px solid #e3e3e3;">
								<ul id="tree_" class="ztree">
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
					<font color='red'>
						*
					</font>
					配偶是否作为共同借款人：
				</label>
				<div class="col-xs-12 col-sm-3">
					<span class="block input-icon input-icon-right">
						<select class="form-control required" name="mateBorrower" id="mateBorrower">
							<dd:options codeType="CommonWhether" selected="${vo.mateBorrower }" />
						</select>
					</span>
				</div>
				<div class="help-block col-xs-12 col-sm-reset inline"></div>

				<label class="col-xs-12 col-sm-2 control-label no-padding-right">
					<font color='red'>
						*
					</font>
					总部协同业务：
				</label>
				<div class="col-xs-12 col-sm-3">
					<label class="checkbox-inline" style="margin-left: 0px;">
						<input type="radio" id="isheadcol1" name="isheadcol" value="1" checked="checked"
						class="ace add_corp_Type form-control" />
						<span class='lbl'>
							是
						</span>
					</label>
					<label class="checkbox-inline">
						<input type="radio" id="isheadcol2" name="isheadcol" value="2" class="ace add_corp_Type form-control"
						/>
						<span class='lbl'>
							否
						</span>
					</label>
					<input type="hidden" id="isheadcol" value="${vo.isheadcol }">
				</div>
				<div class="help-block col-xs-12 col-sm-reset inline"></div>
			</div>
			
			<div class="form-group">
			<label class="col-xs-12 col-sm-2 control-label no-padding-right">
					<font color='red'> * </font>
					担保方式：
				</label>
				<div class="col-xs-12 col-sm-4">
					<dd:checkbox codeType="CdsGuarantMode" cbName="" aceStyle="true" codeVals="${productConfig.guaranteeMode }" />
					<input type="hidden" id="guaranteeMode" name="guaranteeMode" value="${vo.guaranteeMode }" class="prePremiumValidate">
				</div>
				<div class="help-block col-xs-12 col-sm-reset inline">
				</div>
				
			</div>
			
			<div class="form-group">
				<label class="col-xs-12 col-sm-2 control-label no-padding-right">
					<font color='red'> * </font>
					贷款用途描述：
				</label>
				<div class="col-xs-12 col-sm-9">
					<span class="block input-icon input-icon-right">
						<textarea class="form-control required isTextarea" rows="4" id="purpose" name="purpose" style="width: 89%;">${vo.purpose }</textarea>
					</span>
				</div>
				<div class="help-block col-xs-12 col-sm-reset inline"></div>
			</div>


			<h4 class="blue">利率信息</h4>
			<div class="form-group">
				<label class="col-xs-12 col-sm-2 control-label no-padding-right">
					<font color='red'> * </font>
					利率类型：
				</label>
				<div class="col-xs-12 col-sm-3">
					<span class="block input-icon input-icon-right">
						<select class="form-control required" name="irTypeCd" id="irTypeCd">
							<dd:options codeType="InterestRateAdjustment" selected="${vo.irTypeCd }" />
						</select>
					</span>
				</div>
				<div class="help-block col-xs-12 col-sm-reset inline"></div>

				<label class="col-xs-12 col-sm-2 control-label no-padding-right">
					<font color='red'>
						*
					</font>
					年利率（%）：
				</label>
				<div class="col-xs-12 col-sm-3">
					<span class="block input-icon input-icon-right">
						<input type="text" class="form-control required " id="bizRate" name="bizRate" value="${vo.bizRate }">
					</span>
				</div>
				<div class="help-block col-xs-12 col-sm-reset inline"></div>
			</div>

			<div class="form-group">
				<span id="adjustPeriodAndFloatRateShowHide" style="display: none;">
					<label class="col-xs-12 col-sm-2 control-label no-padding-right">
						<font color='red'> * </font>
						调整周期：
					</label>
					<div class="col-xs-12 col-sm-3">
						<span class="block input-icon input-icon-right">
							<select name="adjustPeriod" id="adjustPeriod" class="form-control">
								<dd:options codeType="AdjustPeriod" selected="${vo.adjustPeriod }" />
							</select>
						</span>
					</div>
					<div class="help-block col-xs-12 col-sm-reset inline"></div>
					
					<label class="col-xs-12 col-sm-2 control-label no-padding-right">
						<font color='red'> * </font>
						利率上浮比例（%）：
					</label>
					<div class="col-xs-12 col-sm-3">
						<span class="block input-icon input-icon-right">
							<input type="text" class="form-control" id="irNegoSymbCd" name="irNegoSymbCd" value="${vo.irNegoSymbCd }">
						</span>
					</div>
					<div class="help-block col-xs-12 col-sm-reset inline"></div>
				</span>
			</div>

			<hr />
			<c:if test="${type != 'check' }">
			<div class="row">
				<div class="col-md-6 col-md-offset-5">
					<button id="saveBasicInfo" type="submit" class="btn btn-primary" data-loading-text="正在保存中...">
						<i class="ace-icon fa fa-floppy-o"></i>
						保存
					</button>
				</div>
			</div>
			</c:if>
		</form>
	</div>
</div>
