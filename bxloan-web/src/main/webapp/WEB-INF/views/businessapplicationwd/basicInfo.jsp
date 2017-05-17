<!-- 基本项目信息 -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
%>
	<%@ include file="../../commons/taglibs.jsp" %>
		<!--[if IE 8]>
			<style>
				.ie8{ width:50%; float:left;} .margin-ie8{margin-left:0;}
			</style>
		<![endif]-->
		<div class="row">
			<div class="col-md-12">
				<form id="basicInfoForm" class="form-horizontal" onsubmit="return false;">
					<!-- hidden field begin -->
					<input type="hidden" id="bizRateId" name="bizRateId" value="${vo.bizRateId }">
					<input type="hidden" name="productCd" value="${productCd }">
					<input type="hidden" name="projectId" value="${vo.projectId }" id="projectId">
					<input type="hidden" name="partyId" value="${vo.partyId }">
					<input type="hidden" name="workflowId" value="${vo.workflowId }">
					<input type="hidden" name="taskId" value="${vo.taskId }">
					<input type="hidden" name="customerNum" value="${party.customerNum}"/>
					<!-- hidden field begin -->
					<h4 class="blue">
						客户信息
					</h4>
					<!-- 企业客户 -->
					<c:if test="${party.partyTypeCd == '1' }">
						<div class="form-group">
							<label class="col-xs-12 col-sm-2 control-label no-padding-right">
								客户名称：
							</label>
							<div class="col-xs-12 col-sm-3">
								<span class="block input-icon input-icon-right">
									<input type="text" class="form-control" id="customerName" name="customerName"
									value="${vo.customerName }" readonly="readonly">
								</span>
							</div>
							<div class="help-block col-xs-12 col-sm-reset inline">
							</div>
							<label class="col-xs-12 col-sm-2 control-label no-padding-right">
								行业类型：
							</label>
							<div class="col-xs-12 col-sm-3">
								<span class="block input-icon input-icon-right">
									<input type="text" id="cus_industryCdMask" name="cus_industryCdMask" class="form-control"
									readonly="readonly">
									<input type="hidden" id="cus_industryCd" name="cus_industryCd" class="form-control">
									<input type="hidden" id="cus_industryTypeCdField" name="industryCd" value="${vo.industryLevelTwoCd}"
									/>
								</span>
								<div id="cus_controlZTree" style="display:none;">
									<div class="col-xs-12" style="position:absolute;z-index:999;background:#fff;width:80%;border:1px solid #e3e3e3">
										<ul id="cus_tree" class="ztree">
										</ul>
									</div>
								</div>
							</div>
							<div class="help-block col-xs-12 col-sm-reset inline">
							</div>
						</div>
						<div class="form-group">
							<label class="col-xs-12 col-sm-2 control-label no-padding-right">
								公司规模：
							</label>
							<div class="col-xs-12 col-sm-3">
								<span class="block input-icon input-icon-right">
									<select style="width: 100%;height: 36px;" disabled="disabled">
										<dd:options codeType="CustomerScale" selected="${vo.customerScale }" />
									</select>
									<input type="hidden" id="customerScale" name="customerScale" value="${vo.customerScale }"
									readonly="readonly">
								</span>
							</div>
							<div class="help-block col-xs-12 col-sm-reset inline">
							</div>
							<label class="col-xs-12 col-sm-2 control-label no-padding-right">
								注册资本（万）：
							</label>
							<div class="col-xs-12 col-sm-3">
								<span class="block input-icon input-icon-right">
									<input type="text" class="form-control" id="registeredCapital" name="registeredCapital"
									value="${vo.registeredCapital }" readonly="readonly">
								</span>
							</div>
							<div class="help-block col-xs-12 col-sm-reset inline">
							</div>
						</div>
						<div class="form-group">
							<label class="col-xs-12 col-sm-2 control-label no-padding-right">
								实收资本(万)：
							</label>
							<div class="col-xs-12 col-sm-3">
								<span class="block input-icon input-icon-right">
									<input type="text" class="form-control" id="actualRevAmt" name="actualRevAmt"
									value="${vo.actualRevAmt }" readonly="readonly">
								</span>
							</div>
							<div class="help-block col-xs-12 col-sm-reset inline">
							</div>
							<label class="col-xs-12 col-sm-2 control-label no-padding-right">
								所有制类别：
							</label>
							<div class="col-xs-12 col-sm-3">
								<span class="block input-icon input-icon-right">
									<select style="width: 100%;height: 36px;" disabled="disabled">
										<dd:options codeType="OrgTypeCd" selected="${vo.orgTypeCd }" />
									</select>
									<input type="hidden" id="orgTypeCd" name="orgTypeCd" value="${vo.orgTypeCd }">
								</span>
							</div>
							<div class="help-block col-xs-12 col-sm-reset inline">
							</div>
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
									<input type="text" class="form-control" id="customerName" name="customerName"
									value="${vo.customerName }" readonly="readonly">
								</span>
							</div>
							<div class="help-block col-xs-12 col-sm-reset inline">
							</div>
							<label class="col-xs-12 col-sm-2 control-label no-padding-right">
								婚姻状况：
							</label>
							<div class="col-xs-12 col-sm-3">
								<span class="block input-icon input-icon-right">
									<select style="width: 100%;height: 36px;" disabled="disabled" class="form-control">
										<dd:options codeType="MaritalStatus" selected="${vo.marriageCd }" />
									</select>
									<input type="hidden" id="marriageCd" name="marriageCd" value="${vo.marriageCd }">
								</span>
							</div>
							<div class="help-block col-xs-12 col-sm-reset inline">
							</div>
						</div>
						<div class="form-group">
							<label class="col-xs-12 col-sm-2 control-label no-padding-right">
								最高学历：
							</label>
							<div class="col-xs-12 col-sm-3">
								<span class="block input-icon input-icon-right">
									<select style="width: 100%;height: 36px;" disabled="disabled" class="form-control">
										<dd:options codeType="DegreeCode" selected="${vo.degreeCd }" />
									</select>
									<input type="hidden" id="degreeCd" name="degreeCd" value="${vo.degreeCd }">
								</span>
							</div>
							<div class="help-block col-xs-12 col-sm-reset inline">
							</div>
							<label class="col-xs-12 col-sm-2 control-label no-padding-right">
								所属行业：
							</label>
							<div class="col-xs-12 col-sm-3">
								<span class="block input-icon input-icon-right">
									<input type="text" id="cus_industryCdMask" name="cus_industryCdMask" class="form-control"
									readonly="readonly">
									<input type="hidden" id="cus_industryCd" name="cus_industryCd" class="form-control">
									<input type="hidden" id="cus_industryTypeCdField" name="industryCd" value="${vo.industryCd}"
									/>
								</span>
								<div id="cus_controlZTree" style="display:none;">
									<div class="col-xs-12" style="position:absolute;z-index:999;background:#fff;width:80%;border:1px solid #e3e3e3">
										<ul id="cus_tree" class="ztree">
										</ul>
									</div>
								</div>
							</div>
							<div class="help-block col-xs-12 col-sm-reset inline">
							</div>
						</div>
						<%-- <div class="form-group">
							<label class="col-xs-12 col-sm-2 control-label no-padding-right">
								家庭实际月收入（元）：
							</label>
							<div class="col-xs-12 col-sm-3">
								<span class="block input-icon input-icon-right">
									<input type="text" class="form-control" id="familyMonIncome" name="familyMonIncome"
									value="${vo.familyMonIncome }" readonly="readonly">
								</span>
							</div>
							<div class="help-block col-xs-12 col-sm-reset inline">
							</div>
							<label class="col-xs-12 col-sm-2 control-label no-padding-right">
								供养人数：
							</label>
							<div class="col-xs-12 col-sm-3">
								<span class="block input-icon input-icon-right">
									<input type="text" class="form-control" id="maintainPersonNum" name="maintainPersonNum"
									value="${vo.maintainPersonNum }" readonly="readonly">
								</span>
							</div>
							<div class="help-block col-xs-12 col-sm-reset inline">
							</div>
			</div>
			--%>
			</c:if>
			<h4 class="blue">
				项目信息
			</h4>
			<div class="form-group">
			
				<label class="col-xs-12 col-sm-2 control-label no-padding-right">
					<font color='red'>
						*
					</font>
					项目经理：
				</label>
				<div class="col-xs-12 col-sm-3">
								<span class="block input-icon input-icon-right">
									<input type="text" class="form-control" id="customerManagerName" name="customerManagerName"
									value="${customerManagerName}" readonly="readonly">
								</span>
				</div>
				<div class="help-block col-xs-12 col-sm-reset inline">
				</div>
				<label class="col-xs-12 col-sm-2 control-label no-padding-right">
					<font color='red'>
						*
					</font>
					所属机构：
				</label>
				<div class="col-xs-12 col-sm-3">
								<span class="block input-icon input-icon-right">
									<input type="text" class="form-control" id="orgName" name="orgName"
									value="${orgName}" readonly="readonly">
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
					申报日期：
				</label>
				<div class="col-xs-12 col-sm-3">
					<span class="block input-icon input-icon-right">
						<input type="text" class="form-control required prePremiumValidate" id="applyDate" name="applyDate"
						data-date-format="yyyy-mm-dd" value="${vo.applyDate }">
					</span>
				</div>
				<div class="help-block col-xs-12 col-sm-reset inline">
				</div>
				<label class="col-xs-12 col-sm-2 control-label no-padding-right">
					<font color='red'>
						*
					</font>
					业务编号：
				</label>
				<div class="col-xs-12 col-sm-3">
					<span class="block input-icon input-icon-right">
						<input type="text" class="form-control required" id="projectNo" name="projectNo"
						value="${vo.projectNo }" readonly="readonly">
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
					贷款产品：
				</label>
				<div class="col-xs-12 col-sm-3">
					<span class="block input-icon input-icon-right">
						<select name="productType" id="productType" style="width: 100%;height: 36px;" class="form-control"
						disabled="disabled">
							<c:forEach items="${products }" var="product">
								<option value="${product[0] }">
									${product[1] }
								</option>
							</c:forEach>
						</select>
						<input type="hidden" name="productType" value="${vo.productType }">
					</span>
				</div>
				<div class="help-block col-xs-12 col-sm-reset inline">
				</div>
				<label class="col-xs-12 col-sm-2 control-label no-padding-right">
					<font color='red'>
						*
					</font>
					申报金额（元）：
				</label>
				<div class="col-xs-12 col-sm-3">
					<span class="block input-icon input-icon-right">
						<input type="text" class="form-control required isPositiveNumberTwoOfThousands isInScope prePremiumValidate"
						id="applyAmt" name="applyAmt" value="${vo.applyAmt }">
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
					贷款期限：
				</label>
				<div class="col-xs-12 col-sm-3">
					<span class="block input-icon input-icon-right">
						<input type="text" class="form-control required prePremiumValidate" id="applyTermForBiz" name="applyTerm" value="${vo.applyTerm }">
					</span>
				</div>
				<div class="help-block col-xs-12 col-sm-reset inline">
				</div>
				<label class="col-xs-12 col-sm-2 control-label no-padding-right">
					<font color='red'>
						*
					</font>
					期限单位：
				</label>
				<div class="col-xs-12 col-sm-3">
					<select class="form-control prePremiumValidate" name="applyTermUnit" disabled="disabled" id="applyTermUnitSelect">
						<dd:options codeType="TermUnitCd" selected="${vo.applyTermUnit}" excludes="1"/>
					</select>
					<input type="hidden" id="applyTermUnitForBiz" value="${vo.applyTermUnit }" class="">
					<input type="hidden" id="applyTermUnitFromProduct" value="${productConfig.replyPeriodUnit }">
				</div>
				<%-- <div class="col-xs-12 col-sm-3">
					<select class="form-control" disabled="disabled">
						<dd:options codeType="TermUnitCd" selected="${productConfig.replyPeriodUnit }"/>
					</select>
					<input type="hidden" id="applyTermUnitForBiz" value="${productConfig.replyPeriodUnit }" class="prePremiumValidate" name="applyTermUnit">
				</div> --%>
				<div class="help-block col-xs-12 col-sm-reset inline">
				</div>
			</div>
			<div class="form-group">
				<label class="col-xs-12 col-sm-2 control-label no-padding-right">
					<font color='red'>
						*
					</font>
					还款方式：
				</label>
				<div class="col-xs-12 col-sm-3">
					<select class="form-control required" name="repayingMode" id="repayingMode">
						<dd:options codeType="RepaymentMode" selected="${vo.repayingMode }" codeVals="${productConfig.repayingMode }"
						/>
					</select>
				</div>
				<div class="help-block col-xs-12 col-sm-reset inline">
				</div>
				<span id="replyPeriodNumShowHide" style="display: none;">
					<label class="col-xs-12 col-sm-2 control-label no-padding-right">
						<font color='red'>
							*
						</font>
						还款周期月数：
					</label>
					<div class="col-xs-12 col-sm-3">
						<input type="hidden" id="replyPeriodNum" name="replyPeriodNum" value="${vo.replyPeriodNum }">
						<span class="block input-icon input-icon-right">
							<input type="text" id="replyPeriodNumInput" class="form-control" value="${productConfig.repayPeriodNum }" disabled="disabled">
						</span>
					</div>
					<div class="help-block col-xs-12 col-sm-reset inline">
					</div>
				</span>
			</div>
			<div class="form-group">
				<label class="col-xs-12 col-sm-2 control-label no-padding-right">
					<font color='red'>
						*
					</font>
					是否涉农：
				</label>
				<div class="col-xs-12 col-sm-3">
					<span class="block input-icon input-icon-right">
						<select class="required form-control" name="agricultureInd" id="agricultureInd" style="width: 100%;height: 36px;">
							<dd:options codeType="CommonWhether" selected="${vo.agricultureInd }"
							/>
						</select>
					</span>
				</div>
				<div class="help-block col-xs-12 col-sm-reset inline">
				</div>
				<label class="col-xs-12 col-sm-2 control-label no-padding-right">
					<font color='red'>
						*
					</font>
					币种：
				</label>
				<div class="col-xs-12 col-sm-3">
					<span class="block input-icon input-icon-right">
						<input type="text" class="form-control" id="currency" name="currency"
						value="人民币" readonly="readonly">
					</span>
				</div>
				<div class="help-block col-xs-12 col-sm-reset inline">
				</div>
			</div>
			<div class="form-group">
				<div class="row">
				</div>
			</div>
			<div class="form-group">
				<label class="col-xs-12 col-sm-2 control-label no-padding-right">
					<font color='red'>
						*
					</font>
					担保方式：
				</label>
				<div class="col-xs-12 col-sm-4">
					<dd:checkbox codeType="CdsGuarantMode" cbName="" aceStyle="true" codeVals="${productConfig.guaranteeMode }" />
					<input type="hidden" id="guaranteeMode" name="guaranteeMode" value="${vo.guaranteeMode }" class="prePremiumValidate">
				</div>
				<div class="help-block col-xs-12 col-sm-reset inline">
				</div>
				<label class="col-xs-12 col-sm-1 control-label no-padding-right">
					<font color='red'>
						*
					</font>
					投放行业：
				</label>
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
				<div class="help-block col-xs-12 col-sm-reset inline">
				</div>
				<label class="col-xs-12 col-sm-2 control-label no-padding-right">
					<font color='red'>
						*
					</font>
					提前还款违约金比例（%）：
				</label>
				<div class="col-xs-12 col-sm-3">
					<span class="block input-icon input-icon-right">
						<input type="text" class="form-control required" id="preRepaymentRate"
						name="preRepaymentRate" value="${vo.preRepaymentRate }">
					</span>
				</div>
				<div class="help-block col-xs-12 col-sm-reset inline">
				</div>
			</div>
			<!-- 联保体 -->
			<div class="form-group">
				<label class="col-xs-12 col-sm-2 control-label no-padding-right">
					<font color='red'>*</font>是否为联保体：
				</label>
				<div class="col-xs-12 col-sm-3">
					<span class="block input-icon input-icon-right">
						<select class="required form-control" name="isUniteCustomer" id="isUniteCustomer" style="width: 100%;height: 36px;">
							<dd:options codeType="CommonWhether"  />
						</select>
					</span>
				</div>
				<div class="help-block col-xs-12 col-sm-reset inline"></div>
				<label class="col-xs-12 col-sm-2 control-label no-padding-right">
					<font color='red'>*</font>联保体：
				</label>
				<div class="col-xs-12 col-sm-3">
					<span class="input-icon block input-icon-right">
						<select name="unId" id="unId" class="form-control required" >
							<c:forEach items="${unite }" var="un">
								<option value="${un.id }">${un.unGroupName }</option>
							</c:forEach>
						</select>
						<input type="hidden" id="unId_input" value="${vo.unId }"><!-- 回显 main/view.js -->
					</span>
				</div>
			</div>
			<!-- 联保体 end -->
			<div class="form-group">
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
				<div class="help-block col-xs-12 col-sm-reset inline">
				</div>
				<!-- add by wangpeng on 2015-07-27 start -->
			<label class="col-xs-12 col-sm-2 control-label no-padding-right">
					<font color='red'> * </font>协办客户经理：</label>
					<div class="col-xs-12 col-sm-3">
					<span class="block input-icon input-icon-right">
						<select class="form-control required"  id="assistancer"  name="assistancer">
										<c:forEach items="${assistancers}"  var="assistancer">
											<c:if test="${assistancer.isSelected == 'true' }">
												<option value="${assistancer.personId}"  selected="selected">${assistancer.personName}</option>
											</c:if>
											<c:if test="${assistancer.isSelected == 'false'}" >
												<option value="${assistancer.personId}" >${assistancer.personName}</option>
											</c:if>
										</c:forEach>
								</select>
					</span>
			</div>
			<div class="help-block col-xs-12 col-sm-reset inline"></div>
			<!-- add by wangpeng on 2015-07-27 end -->
			</div>
			<c:if test="${agriculturalLoan }">
				<div class="form-group">
						<label class="col-xs-12 col-sm-2 control-label no-padding-right">
							<font color='red'>
								*
							</font>
							业务类型：
						</label>
						<div class="col-xs-12 col-sm-3">
							<span class="block input-icon input-icon-right">
								<select name="argoBizType" class="form-control required">
									<option value="1">
										农业收入为主
									</option>
									<option value="2">
										非农业收入为主
									</option>
								</select>
							</span>
						</div>
						<div class="help-block col-xs-12 col-sm-reset inline">
						</div>
				</div>
			</c:if>
			<div class="form-group">
				<label class="col-xs-12 col-sm-2 control-label no-padding-right">
					<font color='red'>
						*
					</font>
					贷款用途详细描述：
				</label>
				<div class="col-xs-12 col-sm-9">
					<span class="block input-icon input-icon-right">
						<textarea class="form-control required isTextarea" rows="4" id="purpose"
						name="purpose" style="width: 89%;">${vo.purpose }</textarea>
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
					还款来源：
				</label>
				<div class="col-xs-12 col-sm-9">
					<span class="block input-icon input-icon-right">
						<textarea class="form-control required isTextarea" rows="2" id="payment"
						name="payment" style="width: 89%;">${vo.payment }</textarea>
					</span>
				</div>
				<div class="help-block col-xs-12 col-sm-reset inline">
				</div>
			</div>
			<h4 class="blue">
				正常利率
			</h4>
			<div class="form-group">
				<label class="col-xs-12 col-sm-2 control-label no-padding-right">
					<font color='red'>
						*
					</font>
					利率类型：
				</label>
				<div class="col-xs-12 col-sm-3">
					<span class="block input-icon input-icon-right">
						<select class="required prePremiumValidate form-control" name="irTypeCd" id="irTypeCd" style="width: 100%;height: 36px;">
							<dd:options codeType="InterestRateAdjustment" selected="${vo.irTypeCd }"
							/>
						</select>
					</span>
				</div>
				<div class="help-block col-xs-12 col-sm-reset inline">
				</div>
				<label class="col-xs-12 col-sm-2 control-label no-padding-right">
					<font color='red'>
						*
					</font>
					年利率（%）：
				</label>
				<div class="col-xs-12 col-sm-3">
					<span class="block input-icon input-icon-right">
						<input type="text" class="form-control prePremiumValidate" id="bizRate" name="bizRate" value="${vo.bizRate }">
					</span>
				</div>
				<div class="help-block col-xs-12 col-sm-reset inline"></div>
			</div>
			<div class="form-group">
				<span id="adjustPeriodAndFloatRateShowHide" style="display: none;">
					<label class="col-xs-12 col-sm-2 control-label no-padding-right">
						<font color='red'>
							*
						</font>
						调整周期：
					</label>
					<div class="col-xs-12 col-sm-3">
						<span class="block input-icon input-icon-right">
							<select name="adjustPeriod" id="adjustPeriod" style="width: 100%;height: 36px;">
								<dd:options codeType="AdjustPeriod" selected="${vo.adjustPeriod }" />
							</select>
						</span>
					</div>
					<div class="help-block col-xs-12 col-sm-reset inline">
					</div>
					<label class="col-xs-12 col-sm-2 control-label no-padding-right">
						<font color='red'>
							*
						</font>
						利率上浮比例（%）：
					</label>
					<div class="col-xs-12 col-sm-3">
						<span class="block input-icon input-icon-right">
							<input type="text" class="form-control" id="irNegoSymbCd" name="irNegoSymbCd"
							value="${vo.irNegoSymbCd }">
						</span>
					</div>
					<div class="help-block col-xs-12 col-sm-reset inline">
					</div>
				</span>
			</div>
			<h4 class="blue">
				逾期利率
			</h4>
			<div class="form-group">
				<label class="col-xs-12 col-sm-2 control-label no-padding-right">
					<font color='red'>
						*
					</font>
					逾期利率上浮比例（%）：
				</label>
				<div class="col-xs-12 col-sm-3">
					<span class="block input-icon input-icon-right">
						<input type="text" class="form-control required isPercentNumberFour" id="ovdueRate"
						name="ovdueRate" value="${vo.ovdueRate !=null ?vo.ovdueRate :'50' }">
					</span>
				</div>
				<div class="help-block col-xs-12 col-sm-reset inline">
				</div>
				<label class="col-xs-12 col-sm-2 control-label no-padding-right">
					<font color='red'>
						*
					</font>
					挪用利率上浮比例（%）：
				</label>
				<div class="col-xs-12 col-sm-3">
					<span class="block input-icon input-icon-right">
						<input type="text" class="form-control required isPercentNumberFour" id="perculNegoRate"
						name="perculNegoRate" value="${vo.perculNegoRate !=null ?vo.perculNegoRate :'50'}">
					</span>
				</div>
				<div class="help-block col-xs-12 col-sm-reset inline">
				</div>
			</div>
			<div id="insureDiv">
				<h4 class="blue">
					保险信息
				</h4>
				<div class="form-group">
					<label class="col-xs-12 col-sm-2 control-label no-padding-right">
						<font color='red'>
							*
						</font>
						是否有保险：
					</label>
					<div class="col-xs-12 col-sm-3">
						<span class="block input-icon input-icon-right">
							<select id="ifInsure" name="ifInsure" class="form-control">
								<dd:options codeType="CommonWhether" selected="${vo.ifInsure == null ? 2 : vo.ifInsure }"
								/>
							</select>
						</span>
					</div>
					<div class="help-block col-xs-12 col-sm-reset inline">
					</div>
				</div>
				<div id="insureDetailDiv" style="display: none;">
					<div class="form-group">
						<label class="col-xs-12 col-sm-2 control-label no-padding-right">
							<font color='red'>
								*
							</font>
							保险机构：
						</label>
						<div class="col-xs-12 col-sm-3">
							<span class="block input-icon input-icon-right">
								<select id="insuranceOrgId" name="insuranceOrgId" class="form-control prePremiumValidate">
									<c:forEach items="${insuranceCompanys }" var="insuranceCompany">
										<option value="${insuranceCompany.id }">
											${insuranceCompany.customerName }
										</option>
									</c:forEach>
								</select>
							</span>
						</div>
						<div class="help-block col-xs-12 col-sm-reset inline">
						</div>
						<label class="col-xs-12 col-sm-2 control-label no-padding-right">
							<font color='red'>
								*
							</font>
							应收保费（元）：
						</label>
						<div class="col-xs-12 col-sm-3">
							<span class="block input-icon input-icon-right">
								<input type="text" class="form-control isPositiveNumberTwo" maxlength="15" id="prePremium" name="prePremium"
								value="${vo.prePremium }">
							</span>
						</div>
						<div class="help-block col-xs-12 col-sm-reset inline">
						</div>
					</div>
				</div>
			</div>
			<div id="repaymentPlanDiv" style="display: none;">
				<h4 class="blue">
					还款计划
				</h4>
				<c:if test="${type != 'check' }">
					<div class="form-group">
						<div class="row">
							<div class="col-md-3 col-md-offset-1 ie8">
								<label class="col-sm-4 control-label" for="startDate">
									开始日期：
								</label>
								<div class="col-sm-8">
									<input type="text" class="form-control" id="startDate" name="startDate"
									data-date-format="yyyy-mm-dd">
								</div>
							</div>
							<div class="col-md-3 ie8">
								<label class="col-sm-4 control-label" for="monthDate">
									间隔月数：
								</label>
								<div class="col-sm-8">
									<input type="text" class="form-control" id="monthDate" name="monthDate">
								</div>
							</div>
							<div class="col-md-3 ie8">
								<label class="col-sm-5 control-label" for="repayDateForCount">
									每月还款日：
								</label>
								<div class="col-sm-7">
									<input type="text" class="form-control" id="repayDateForCount" name="repayDateForCount">
								</div>
							</div>
						</div>
					</div>
				</c:if>
				<div class="row">
					<div class="col-md-10">
						<c:if test="${type != 'check' }">
							<table style="width: 100%;">
								<tr>
									<td align="right">
										<button id="addRepayPlan" type="button" class="btn btn-sm btn-success">
											<i class="ace-icon fa fa-plus">
											</i>
											新增
										</button>
										<button id="batchInitRepayPlan" type="button" class="btn btn-sm btn-success">
											<i class="ace-icon fa fa-plus">
											</i>
											批量初始化
										</button>
									</td>
								</tr>
							</table>
						</c:if>
						<table id="repaymentPlanTable" class="table table-striped table-hover">
							<thead>
								<tr>
									<th>
										计划还款时间
									</th>
									<th>
										计划还款本金金额（元）
									</th>
									<c:if test="${type != 'check' }">
										<th>
											操作
										</th>
									</c:if>
								</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>
			<hr />
			<div class="row">
				<div class="col-md-4 col-md-offset-5">
					<c:if test="${type != 'check' }">
						<button id="saveBasicInfo" type="submit" class="btn btn-primary">
							<i class="ace-icon fa fa-floppy-o">
							</i>
							保存
						</button>
						<button id="dkss" type="button" class="btn btn-primary" disabled="disabled">
							<i class="ace-icon fa fa-cny">
							</i>
							贷款试算
						</button>
					</c:if>
				</div>
			</div>
			</form>
		</div>
		</div>
		<div id="dkssModal" class="modal fade" data-backdrop="static" tabindex="-1">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">
								&times;
							</span>
							<span class="sr-only">
								Close
							</span>
						</button>
						<h4 class="modal-title">
							贷款试算
						</h4>
					</div>
					<div class="modal-body">
						<form class="form-horizontal" id="countRateForm">
							<input type="hidden" name="projectId" value="${vo.projectId }">
							<div class="row">
								<div class="form-group">
									<label class="col-xs-12 col-sm-2 control-label no-padding-right">
										贷款金额（元）：
									</label>
									<div class="col-xs-12 col-sm-3">
										<span class="block input-icon input-icon-right">
											<input type="text" id="loanAmountBiz" name="loanAmount" class="form-control required isNumber">
										</span>
									</div>
									<label class="col-xs-12 col-sm-3 control-label no-padding-right">
										贷款时间：
									</label>
									<div class="col-xs-12 col-sm-3">
										<span class="block input-icon input-icon-right">
											<input type="text" id="loanStartDateBiz" name="loanStartDate" data-date-format="yyyy-mm-dd"
											class="form-control required">
										</span>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="form-group">
									<label class="col-xs-12 col-sm-2 control-label no-padding-right">
										期限：
									</label>
									<div class="col-xs-12 col-sm-3">
										<span class="block input-icon input-icon-right">
											<input type="text" id="applyTermBiz" name="applyTerm" class="required isIntPositive" style="width: 68%;">
											<select name="applyTermUnit" style="width: 30%;height: 33.5px;">
												<dd:options codeType="TermUnitCd" />
											</select>
										</span>
									</div>
									<label class="col-xs-12 col-sm-3 control-label no-padding-right">
										还款方式：
									</label>
									<div class="col-xs-12 col-sm-3">
										<span class="input-icon block input-icon-right">
											<select name="repayment" id="repaymentBiz" class="form-control required">
												<dd:options codeType="RepaymentMode" />
											</select>
										</span>
									</div>
									<div class="help-block col-xs-12 col-sm-reset inline">
									</div>
								</div>
							</div>
							<div class="row">
								<div class="form-group">
									<label class="col-xs-12 col-sm-2 control-label no-padding-right">
										还款日：
									</label>
									<div class="col-xs-12 col-sm-3">
										<span class="block input-icon input-icon-right">
											<input type="text" id="repaymentDateBiz" name="repaymentDate" class="form-control required isDayInMonth" >
										</span>
									</div>
									<label class="col-xs-12 col-sm-3 control-label no-padding-right">
										年利率（%）：
									</label>
									<div class="col-xs-12 col-sm-3">
										<span class="block input-icon input-icon-right">
											<input type="text" id="rateBiz" name="rate" class="form-control required isPercentNumberFour" >
										</span>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="form-group">
									<label class="col-xs-12 col-sm-2 control-label no-padding-right">
										还款周期月数：
									</label>
									<div class="col-xs-12 col-sm-3">
										<span class="block input-icon input-icon-right">
											<input type="text" id="repaymentNumberBiz" name="repaymentNumber" class="form-control required isDayInMonth" >
										</span>
									</div>
								</div>
							</div>
							<div id="dkss_repaymentPlanTable_div" style="display: none;">
								<table id="dkss_repaymentPlanTable" class="table table-striped table-hover">
									<thead>
										<tr>
											<th>
												计划还款时间
											</th>
											<th>
												计划还款本金金额（元）
											</th>
											<th>
												操作
											</th>
										</tr>
									</thead>
									<tbody id="dkss_tbody">
									</tbody>
									<tfoot id="dkss_tfoot">
									</tfoot>
								</table>
							</div>
							<br>
							<div class="row" align="right">
								<div>
									<button id="addUserDefindPlan" type="button" class="btn btn-sm btn-success"
									style="display: none;">
										<i class="ace-icon fa fa-plus">
										</i>
										新增还款计划
									</button>
									<button id="countRate" type="submit" class="btn btn-sm btn-primary">
										<i class="ace-ico fa fa-cny">
										</i>
										计算
									</button>
									<button id="resetCountRateForm" type="button" class="btn btn-sm btn-default">
										<i class="ace-icon fa fa-undo">
										</i>
										重置
									</button>
								</div>
							</div>
							<br>
							<br>
							<div>
								<span>
									<h7 class="blue" style="font-size:20px">
										<i class="orange ace-icon fa fa-credit-card bigger-110">
										</i>
										还款计划
									</h7>
								</span>
							</div>
							<table id="tbl" class="table table-striped table-hover">
								<thead>
									<tr>
										<th>
											期次
										</th>
										<th>
											计划还款日
										</th>
										<th>
											本期应还金额(元)
										</th>
										<th>
											应还本金(元)
										</th>
										<th>
											应还利息(元)
										</th>
										<th>
											累计还本(元)
										</th>
										<th>
											贷款余额(元)
										</th>
									</tr>
								</thead>
								<tbody id="tbld">
								</tbody>
								<tfoot id="tbfoot">
								</tfoot>
							</table>
						</form>
					</div>
				</div>
			</div>
		</div>
		<div id="repayPlanModal" class="modal fade" data-backdrop="static" tabindex="-1">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">
								&times;
							</span>
							<span class="sr-only">
								Close
							</span>
						</button>
						<h4 class="modal-title">
							还款计划
						</h4>
					</div>
					<div class="modal-body">
						<form id="repayPlanForm" class="form-horizontal">
							<div class="modal-body">
								<div class="row">
									<div class="col-xs-12">
										<div class="form-group">
											<label class="col-sm-5 control-label no-padding-right" for="repayDate">
												<font color='red'>
													*
												</font>
												计划还款时间：
											</label>
											<div class="col-sm-7">
												<span class="block input-icon input-icon-right">
													<input type="text" class="form-control required" id="repayDate" name="repayDateStr"
													data-date-format="yyyy-mm-dd">
												</span>
											</div>
											<div class="help-block col-xs-12 col-sm-reset inline">
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-5 control-label no-padding-right" for="repayAmt">
												<font color='red'>
													*
												</font>
												计划还款本金金额（元）：
											</label>
											<div class="col-sm-7">
												<span class="block input-icon input-icon-right">
													<input type="text" class="form-control required isPositiveNumberTwo" id="repayAmt"
													name="repayAmt" />
												</span>
											</div>
											<div class="help-block col-xs-12 col-sm-reset inline">
											</div>
										</div>
										<input type="hidden" id="approvalHistoryRepayPlanId" name="approvalHistoryRepayPlanId">
										<input type="hidden" name="projectNo" value="${vo.projectNo }">
										<input type="hidden" name="projectId" value="${vo.projectId }">
										<input type="hidden" name="customerId" value="${vo.partyId }">
									</div>
								</div>
							</div>
							<hr>
							<div class="row" align="right">
								<button id="sureRepayPlanForm" type="submit" class="btn btn-primary">
									确定
								</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		<div id="dkss_repayPlanModal" class="modal fade" data-backdrop="static"
		tabindex="-1">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">
								&times;
							</span>
							<span class="sr-only">
								Close
							</span>
						</button>
						<h4 class="modal-title">
							还款计划
						</h4>
					</div>
					<div class="modal-body">
						<form id="dkss_repayPlanForm" class="form-horizontal">
							<div class="modal-body">
								<div class="row">
									<div class="col-xs-12">
										<div class="form-group">
											<label class="col-sm-5 control-label no-padding-right" for="repayDate">
												<font color='red'>
													*
												</font>
												计划还款时间：
											</label>
											<div class="col-sm-7">
												<span class="block input-icon input-icon-right">
													<input type="text" class="form-control required" id="dkss_repayDate" name="repayDateStr"
													data-date-format="yyyy-mm-dd">
												</span>
											</div>
											<div class="help-block col-xs-12 col-sm-reset inline">
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-5 control-label no-padding-right" for="repayAmt">
												<font color='red'>
													*
												</font>
												计划还款本金金额（元）：
											</label>
											<div class="col-sm-7">
												<span class="block input-icon input-icon-right">
													<input type="text" class="form-control required isPositiveNumberTwo" id="dkss_repayAmt"
													name="repayAmt" />
												</span>
											</div>
											<div class="help-block col-xs-12 col-sm-reset inline">
											</div>
										</div>
										<input type="hidden" id="dkss_repayPlanId" name="repayPlanId">
										<input type="hidden" name="projectNo" value="${vo.projectNo }">
										<input type="hidden" name="projectId" value="${vo.projectId }">
										<input type="hidden" name="customerId" value="${vo.partyId }">
									</div>
								</div>
							</div>
							<hr>
							<div class="row" align="right">
								<button id="dkss_sureRepayPlanForm" type="submit" class="btn btn-primary">
									确定
								</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>