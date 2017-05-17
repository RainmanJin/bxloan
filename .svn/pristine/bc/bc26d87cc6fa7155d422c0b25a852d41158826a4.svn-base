<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../../commons/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<form class="form-horizontal" id="basicInfoForm">
			<input type="hidden" id="projectId" name="projectId"
				value="${projectApplication.projectId}" /> <input type="hidden"
				name="bizRateId" value="${bizRate.bizRateId }"> <input
				type="hidden" name="productCd" value="${productCd }">
			<div>
				<h4 class="blue">基本信息</h4>
			</div>
			<div class="form-group">
				<label class="col-xs-12 col-sm-2 control-label no-padding-right">
					客户名称： </label>
				<div class="col-xs-12 col-sm-3">
					<span class="block input-icon input-icon-right"> <input
						type="text" class="form-control" id="customerName"
						name="customerName" value="${projectApplication.customerName}"
						readonly="readonly" /> </span>
				</div>
				<div class="help-block col-xs-12 col-sm-reset inline"></div>

				<label class="col-xs-12 col-sm-2 control-label no-padding-right">
					业务编号 ：</label>
				<div class="col-xs-12 col-sm-3">
					<span class="block input-icon input-icon-right"> <input
						type="text" id="projectNo" name="projectNo" class="form-control"
						value="${projectApplication.projectNo}" readonly="readonly" /> </span>
				</div>
				<div class="help-block col-xs-12 col-sm-reset inline"></div>
			</div>

			<div class="form-group">
				<label class="col-xs-12 col-sm-2 control-label no-padding-right">
					婚姻状况 ：</label>
				<div class="col-xs-12 col-sm-3">
					<span class="block input-icon input-icon-right"> <select
						disabled="disabled" class="form-control" style="height: 34px;">
							<dd:options codeType="MaritalStatus"
								selected="${individual.marriageCd}" />
					</select> <input type="hidden" id="marriageCd" name="marriageCd"
							value="${individual.marriageCd }"> </span>
				</div>
				<div class="help-block col-xs-12 col-sm-reset inline"></div>

				<label class="col-xs-12 col-sm-2 control-label no-padding-right">
					最高学历 ：</label>
				<div class="col-xs-12 col-sm-3">
					<span class="block input-icon input-icon-right"> <select
						disabled="disabled" class="form-control" style="height: 34px;">
							<dd:options codeType="DegreeCode"
								selected="${individual.degreeCd}" />
					</select> </span>
				</div>
				<div class="help-block col-xs-12 col-sm-reset inline"></div>
			</div>

			<div class="form-group">
				<label class="col-xs-12 col-sm-2 control-label no-padding-right">
					雇佣类型： </label>
				<div class="col-xs-12 col-sm-3">
					<span class="block input-icon input-icon-right"> <select
						disabled="disabled" class="form-control" style="height: 34px;">
							<dd:options codeType="EmploymentType"
								selected="${individual.employmentType }" />
					</select> </span>
				</div>
				<div class="help-block col-xs-12 col-sm-reset inline"></div>

				<label class="col-xs-12 col-sm-2 control-label no-padding-right">
					客户来源： </label>
				<div class="col-xs-12 col-sm-3">
					<span class="block input-icon input-icon-right"> <select
						disabled="disabled" class="form-control" style="height: 34px;">
							<dd:options codeType="CustomerSource"
								selected="${individual.customerSource }" />
					</select> </span>
				</div>
				<div class="help-block col-xs-12 col-sm-reset inline"></div>
			</div>
			
			<div class="form-group">
				<label class="col-xs-12 col-sm-2 control-label no-padding-right">
					贷款产品： </label>
				<div class="col-xs-12 col-sm-3">
					<span class="block input-icon input-icon-right">
						<input class="form-control" value="${product.productName }" disabled="disabled">
					</span>
				</div>
				<div class="help-block col-xs-12 col-sm-reset inline"></div>
			</div>

			<br>
			<div class="form-group">
				<label class="col-xs-12 col-sm-2 control-label no-padding-right">
					<font color='red'> * </font>
					项目经理：
				</label>
				<div class="col-xs-12 col-sm-3">
						<span class="block input-icon input-icon-right">
							<input type="text" class="form-control" name="customerManagerName" value="${customerManagerName}" readonly="readonly">
						</span>
				</div>
				<div class="help-block col-xs-12 col-sm-reset inline"></div>

				<label class="col-xs-12 col-sm-2 control-label no-padding-right">
					<font color='red'> * </font>
					所属机构：
				</label>
				<div class="col-xs-12 col-sm-3">
						<span class="block input-icon input-icon-right">
							<input type="text" class="form-control" name="orgName" value="${orgName}" readonly="readonly">
						</span>
				</div>
				<div class="help-block col-xs-12 col-sm-reset inline"></div>
			</div>
			<div class="form-group">
				<label class="col-xs-12 col-sm-2 control-label no-padding-right">
					<font color='red'> * </font>申请日期： </label>
				<div class="col-xs-12 col-sm-3">
					<span class="block input-icon input-icon-right"> <input
						id="applyDate" name="applyDate" type="text"
						value="${projectApplication.applyDateStr }"
						data-date-format="yyyy-mm-dd" class="form-control required" /> </span>
				</div>
				<div class="help-block col-xs-12 col-sm-reset inline"></div>

				<label class="col-xs-12 col-sm-2 control-label no-padding-right">
					<font color='red'> * </font>申报金额（元）： </label>
				<div class="col-xs-12 col-sm-3">
					<span class="block input-icon input-icon-right"> <input
						type="text" name="applyAmt" id="applyAmt"
						value="${projectApplication.applyAmt }"
						class="form-control required isPositiveNumberTwoOfThousands" /> </span>
				</div>
				<div class="help-block col-xs-12 col-sm-reset inline"></div>
			</div>

			<div class="form-group">
				<c:if test="${productPrice.loanTerm == null}">
					<label class="col-xs-12 col-sm-2 control-label no-padding-right">
						<font color='red'> * </font>申请期限： </label>
					<div class="col-xs-12 col-sm-3">
						<span class="block input-icon input-icon-right">
							<input type="text" class="form-control required" id="applyTerm" name="applyTerm" value="${projectApplication.applyTerm }">
						</span>
					</div>
					<div class="help-block col-xs-12 col-sm-reset inline"></div>
				</c:if>
				<c:if test="${productPrice.loanTerm != null}">
					<label class="col-xs-12 col-sm-2 control-label no-padding-right">
						<font color='red'> * </font>申请期限： </label>
					<div class="col-xs-12 col-sm-3">
						<span class="block input-icon input-icon-right">
							<input type="text" class="form-control required" id="applyTerm" name="applyTerm" value="${productPrice.loanTerm }">
						</span>
					</div>
					<div class="help-block col-xs-12 col-sm-reset inline"></div>
				</c:if>

				<label class="col-xs-12 col-sm-2 control-label no-padding-right">
					<font color='red'>
						*
					</font>
					期限单位：
				</label>
				<div class="col-xs-12 col-sm-3">
					<select class="form-control" disabled="disabled">
						<dd:options codeType="TermUnitCd" selected="${productConfig.replyPeriodUnit }"/>
					</select>
					<input type="hidden" id="applyTermUnitForBiz" name="applyTermUnit" value="${productConfig.replyPeriodUnit }">
				</div>
				<div class="help-block col-xs-12 col-sm-reset inline"></div>
			</div>
			
			<div class="form-group">
				<label class="col-xs-12 col-sm-2 control-label no-padding-right">
					<font color='red'> * </font>投放行业： </label>
				<div class="col-xs-12 col-sm-3">
					<span class="block input-icon input-icon-right">
						<div class="input-group">
							<input type="text" id="industryCdMask"
								class="form-control required" readonly="readonly"> <input
								type="hidden" name="investmentIndustry" id="industryCd"
								readonly="readonly"> <span class="input-group-btn">
								<button id="btn-showTree" class="btn btn-sm btn-yellow"
									type="button">
									<i class="ace-icon fa fa-eye"></i>
								</button> </span> <input type="hidden" id="industryTypeCdField"
								value="${projectApplication.investmentIndustry}" />
						</div> </span> <span class="block input-icon input-icon-right">
						<div id="controlZTree" style="display:none;">
							<div class="col-xs-12"
								style="overflow-y: auto;max-height: 500px;position:absolute;z-index:999;background:#fff;border:1px solid #e3e3e3">
								<ul id="tree" class="ztree">
								</ul>
							</div>
						</div> </span>
				</div>
				<div class="help-block col-xs-12 col-sm-reset inline"></div>
			</div>

			<input type="hidden" name="currency" id="currency" value="156">



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
							<input type="text" id="replyPeriodNumInput"  class="form-control" value="${productConfig.repayPeriodNum }" disabled="disabled">
						</span>
					</div>
					<div class="help-block col-xs-12 col-sm-reset inline">
					</div>
				</span>
			</div>

			<%-- <c:if test="${productPrice.repaymentType == null}">

				<div class="form-group">
					<label class="col-xs-12 col-sm-2 control-label no-padding-right">
						<font color='red'> * </font>还款方式 ：</label>
					<div class="col-xs-12 col-sm-3">
						<span class="block input-icon input-icon-right"> <select
							name="repayingMode" id="repayingMode"
							class="form-control required">
								<dd:options codeType="RepaymentMode"
									selected="${projectApplication.repayingMode }" />
						</select> </span>
					</div>
					<div class="help-block col-xs-12 col-sm-reset inline"></div>

					<c:if
						test="${projectApplication.repayingMode != '2' && projectApplication.repayingMode != '3'}">
						<span id="hideReplyPeriodNum"> <label
							class="col-xs-12 col-sm-2 control-label no-padding-right">
								<font color='red'> * </font>还款周期月数： </label>
							<div class="col-xs-12 col-sm-3">
								<span class="block input-icon input-icon-right"> <input
									type="text" name="replyPeriodNum" id="replyPeriodNum"
									class="form-control required isIntPositive"
									value="${projectApplication.replyPeriodNum }" /> </span>
							</div>
							<div class="help-block col-xs-12 col-sm-reset inline"></div> </span>
					</c:if>
					<c:if
						test="${projectApplication.repayingMode == '2' || projectApplication.repayingMode == '3'}">
						<span id="hideReplyPeriodNum" style="display: none;"> <label
							class="col-xs-12 col-sm-2 control-label no-padding-right">
								<font color='red'> * </font>还款周期月数： </label>
							<div class="col-xs-12 col-sm-3">
								<span class="block input-icon input-icon-right"> <input
									type="text" name="replyPeriodNum" id="replyPeriodNum"
									class="form-control" value="hide" /> </span>
							</div>
							<div class="help-block col-xs-12 col-sm-reset inline"></div> </span>
					</c:if>
				</div>

			</c:if>

			<c:if test="${productPrice.repaymentType != null}">
				<div class="form-group">
					<label class="col-xs-12 col-sm-2 control-label no-padding-right">
						<font color='red'> * </font>还款方式 ：</label>
					<div class="col-xs-12 col-sm-3">
						<span class="block input-icon input-icon-right"> <select
							id="repayingMode" name="repayingMode"
							class="form-control required" style="height: 34px;">
								<c:forEach items="${repayingModes }" var="repayingMode">
									<c:if
										test="${projectApplication.repayingMode == repayingMode[0] }">
										<option value="${repayingMode[0] }" selected="selected">${repayingMode[1]
											}</option>
									</c:if>
									<c:if
										test="${projectApplication.repayingMode != repayingMode[0] }">
										<option value="${repayingMode[0] }">
											${repayingMode[1] }</option>
									</c:if>
								</c:forEach>
						</select> </span>
					</div>
					<div class="help-block col-xs-12 col-sm-reset inline"></div>

					<c:if
						test="${productPrice.repaymentType != '2' && productPrice.repaymentType != '3'}">
						<input type="hidden" id="replyPeriodNum" name="replyPeriodNum"
							value="${productPrice.repaymentCucle }" />
						<label class="col-xs-12 col-sm-2 control-label no-padding-right">
							<font color='red'> * </font>还款周期月数 ：</label>
						<div class="col-xs-12 col-sm-3">
							<span class="block input-icon input-icon-right"> <input
								type="text" disabled="disabled" name="replyPeriodNum"
								class="form-control required isIntPositive"
								value="${productPrice.repaymentCucle }" /> </span>
						</div>
						<div class="help-block col-xs-12 col-sm-reset inline"></div>
						<input type="hidden" id="replyPeriodNum"
							value="${productPrice.repaymentCucle }">
					</c:if>
					<c:if
						test="${productPrice.repaymentType == '2' || productPrice.repaymentType == '3'}">
						<input type="hidden" id="replyPeriodNum" value="hide">
					</c:if>
				</div>
			</c:if> --%>

			<div class="form-group">
				<label class="col-xs-12 col-sm-2 control-label no-padding-right">
					<font color='red'> * </font>贷款用途： </label>
				<div class="col-xs-12 col-sm-3">
					<span class="block input-icon input-icon-right"> <select
						name="purpose" id="purpose" class="form-control required"
						style="height: 34px;">
							<dd:options codeType="LoanPurpose"
								selected="${projectApplication.purpose }" />
					</select> </span>
				</div>
				<div class="help-block col-xs-12 col-sm-reset inline"></div>

				<label class="col-xs-12 col-sm-2 control-label no-padding-right">
					<font color='red'> * </font>贷款用途性质：</label>
				<div class="col-xs-12 col-sm-3">
					<span class="block input-icon input-icon-right"> <select
						name="loanPurposeKind" id="loanPurposeKind"
						class="form-control required" style="height: 34px;">
							<dd:options codeType="LoanPurposeKind"
								selected="${projectApplication.loanPurposeKind }" />
					</select> </span>
				</div>
				<div class="help-block col-xs-12 col-sm-reset inline"></div>
			</div>
			
			<div class="form-group">
				<label class="col-xs-12 col-sm-2 control-label no-padding-right">
					<font color='red'> * </font>配偶是否作为共同借款人： </label>
				<div class="col-xs-12 col-sm-3">
					<span class="block input-icon input-icon-right">
						<select class="form-control required" id="mateBorrower" name="mateBorrower">
							<dd:options codeType="CommonWhether" selected="${projectApplication.mateBorrower }" />
						</select>
					</span>
				</div>
				<div class="help-block col-xs-12 col-sm-reset inline"></div>
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
			
			<div>
				<h4 class="blue">正常利率</h4>
			</div>

			<div class="form-group">
				<input type="hidden" id="interestRateAdjustment"
					name="interestRateAdjustment" value="1"> <label
					class="col-xs-12 col-sm-2 control-label no-padding-right">
					<font color='red'> * </font>利率类型： </label>
				<div class="col-xs-12 col-sm-3">
					<span class="block input-icon input-icon-right"> <select
						class="form-control required" disabled="disabled">
							<dd:options codeType="InterestRateAdjustment"
								selected="${bizRate.irGistStyleCd }" />
					</select> </span>
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
						<input type="text" class="form-control required prePremiumValidate" id="rateValue" name="rateValue" value="${projectApplication.bizRate }">
					</span>
				</div>
				<div class="help-block col-xs-12 col-sm-reset inline"></div>
				<%-- <c:if test="${productPrice.rate != null }">
					<input type="hidden" id="rateValue" name="rateValue"
						value="${productPrice.rate }">
					<label class="col-xs-12 col-sm-2 control-label no-padding-right">
						<font color='red'> * </font>利率值（%）：</label>
					<div class="col-xs-12 col-sm-3">
						<span class="block input-icon input-icon-right"> <input
							type="text" name="rateValue" readonly="readonly"
							class="form-control required isPercentNumberFour"
							value="${productPrice.rate * 100 }" /> </span>
					</div>
					<div class="help-block col-xs-12 col-sm-reset inline"></div>
				</c:if>
				<c:if test="${productPrice.rate == null }">
					<label class="col-xs-12 col-sm-2 control-label no-padding-right">
						<font color='red'> * </font>利率值（%）：</label>
					<div class="col-xs-12 col-sm-3">
						<span class="block input-icon input-icon-right"> <input
							type="text" id="rateValue" name="rateValue"
							class="form-control required isPercentNumberFour"
							value="${projectApplication.bizRate }" /> </span>
					</div>
					<div class="help-block col-xs-12 col-sm-reset inline"></div>
				</c:if> --%>
			</div>

			<div>
				<h4 class="blue">逾期利率</h4>
			</div>
			<div class="form-group">
				<label class="col-xs-12 col-sm-2 control-label no-padding-right">
					<font color='red'>
						*
					</font>
					逾期利率上浮比例（%）：
				</label>
				<div class="col-xs-12 col-sm-3">
					<span class="block input-icon input-icon-right">
						<input type="text" class="form-control required isPercentNumberFour" id="overFloatRate"
						name="overFloatRate" value="${bizRate.ovdueIrNegoRate !=null ?bizRate.ovdueIrNegoRate :'50' }">
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
						<input type="text" class="form-control required isPercentNumberFour" id="divertFloatRate"
						name="divertFloatRate" value="${bizRate.perculIrNegoRate !=null ?bizRate.perculIrNegoRate :'50'}">
					</span>
				</div>
				<div class="help-block col-xs-12 col-sm-reset inline">
				</div>
			</div>
			<%-- <c:if test="${productPrice.overdueRate != null }">
				<input type="hidden" id="overFloatRate" name="overFloatRate"
					value="${productPrice.overdueRate }">
				<input type="hidden" id="divertFloatRate" name="divertFloatRate"
					value="${productPrice.perculNegoRate }">

				<div class="form-group">
					<label class="col-xs-12 col-sm-2 control-label no-padding-right">
						<font color='red'> * </font>逾期利率上浮比例（%） ：</label>
					<div class="col-xs-12 col-sm-3">
						<span class="block input-icon input-icon-right"> <input
							type="text" name="overFloatRate" disabled="disabled"
							class="form-control" value="${productPrice.overdueRate * 100 }" />
						</span>
					</div>
					<div class="help-block col-xs-12 col-sm-reset inline"></div>

					<label class="col-xs-12 col-sm-2 control-label no-padding-right">
						<font color='red'> * </font>挪用利率上浮比例（%）： </label>
					<div class="col-xs-12 col-sm-3">
						<span class="block input-icon input-icon-right"> <input
							type="text" name="divertFloatRate" disabled="disabled"
							class="form-control"
							value="${productPrice.perculNegoRate * 100 }" /> </span>
					</div>
					<div class="help-block col-xs-12 col-sm-reset inline"></div>
				</div>
			</c:if>
			<c:if test="${productPrice.overdueRate == null }">
				<div class="form-group">
					<label class="col-xs-12 col-sm-2 control-label no-padding-right">
						<font color='red'> * </font>逾期利率上浮比例（%）： </label>
					<div class="col-xs-12 col-sm-3">
						<span class="block input-icon input-icon-right"> <input
							type="text" id="overFloatRate" name="overFloatRate"
							class="form-control required isOverdueRate"
							value="${bizRate.ovdueIrNegoRate }" /> </span>
					</div>
					<div class="help-block col-xs-12 col-sm-reset inline"></div>

					<label class="col-xs-12 col-sm-2 control-label no-padding-right">
						<font color='red'> * </font>挪用利率上浮比例（%）： </label>
					<div class="col-xs-12 col-sm-3">
						<span class="block input-icon input-icon-right"> <input
							type="text" required="required" id="divertFloatRate"
							name="divertFloatRate"
							value="${bizRate.perculIrNegoRate }"
							class="form-control required isPerculNegoRate" /> </span>
					</div>
					<div class="help-block col-xs-12 col-sm-reset inline"></div>
				</div>
			</c:if> --%>

			<hr />

			<div class="row">
				<div class="col-md-6 col-md-offset-5">
					<c:if test="${consultLocation == null }">
						<c:if test="${judgeType != 'check' }">
							<button type="submit" id="btn-proapp"
								class="btn btn-sm btn-primary">
								<i class="ace-icon fa fa-floppy-o"></i> 保存
							</button>

							<button id="dkss" type="button" class="btn btn-sm btn-primary"
								disabled="disabled">
								<i class="ace-icon fa fa-cny"></i> 贷款试算
							</button>
						</c:if>
						<%-- <c:if test="${judgeType == 'check' }">
							<!-- <button type="button" name="backToDashboard"
								class="btn btn-sm btn-default">
								<i class="ace-icon fa fa-arrow-left"></i>返回1
							</button> -->
						</c:if> --%>
					</c:if>
				</div>
			</div>
		</form>
	</div>
</div>