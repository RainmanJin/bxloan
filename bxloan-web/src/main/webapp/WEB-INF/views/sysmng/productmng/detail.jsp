<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../../../commons/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<base href="${ctx}/">
<title>${title }</title>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

<meta name="description" content="Dashboard page" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />


<c:import url="../../../commons/pre-include.jsp" charEncoding="UTF-8" />

<script>
	var $$ctx = "${ctx}/";
</script>
<style type="text/css">
.checkbox-inline {
	margin-left: -20px;
}
.chosen-container-multi .chosen-choices li.search-choice .search-choice-close{
		background-image: none;
}
</style>
</head>
<body class="no-skin">

	<!-- 隐藏域 -->
	<input type="hidden" id="customerType" value="${productConfig.customerType }">

	<c:import url="../../../commons/navbar.jsp" charEncoding="UTF-8" />
	<div class="main-container" id="main-container">
		<c:import url="../../../commons/sidebar.jsp" charEncoding="UTF-8" />
		<div class="main-content">
			<div class="breadcrumbs" id="breadcrumbs">
				<ul class="breadcrumb">
					<li>
						<i class="ace-icon fa fa-home home-icon"></i>
						<a href="${ctx}">主页</a>
					</li>
					<li>系统管理</li>
					<li>产品管理</li>
				</ul>
			</div>

			<div class="page-content">
				<div class="page-header">
					<h1>
						系统管理
						<small>
							<i class="ace-icon fa fa-angle-double-right"></i>
							产品管理
						</small>
					</h1>
				</div>

				<div class="row">
						
					<div class="col-md-12">
					
						<form class="form-horizontal" id="imputForm">
						
						<input type="hidden" id="viewOrEdit" value="${viewOrEdit }">
							<div class="form-group">
							<label class="col-xs-12 col-sm-2 control-label no-padding-right">
									<font color='red'> * </font>
									一级产品：
								</label>
								<div class="col-xs-12 col-sm-3">
									<span class="block input-icon input-icon-right">
										<select class="form-control" name="parentProductCd">
											<dd:options codeType="FirstProdcutCode" selected="${product.parentProductCd }"/>
										</select>
									</span>
								</div>
								<div class="help-block col-xs-12 col-sm-reset inline"></div>
								
								<label class="col-xs-12 col-sm-2 control-label no-padding-right">
									<font color='red'> * </font>
									产品名称：
								</label>
								<div class="col-xs-12 col-sm-3">
									<span class="block input-icon input-icon-right">
										<input type="hidden" name="productCd" id="productCd" value="${product.productCd }">
										<input type="text" class="form-control" name="productName" value="${product.productName }">
									</span>
								</div>
								<div class="help-block col-xs-12 col-sm-reset inline"></div>
			
							</div>
							
							<div class="form-group">
								<label class="col-xs-12 col-sm-2 control-label no-padding-right">
									<font color='red'> * </font>
									客户类型：
								</label>
								<div class="col-xs-12 col-sm-3">
									<span class="block input-icon input-icon-right">
										<dd:checkbox codeType="CustomerTypeProduct" cbName="customerType" aceStyle="true"/>
									</span>
								</div>
								<div class="help-block col-xs-12 col-sm-reset inline"></div>
								<label class="col-xs-12 col-sm-2 control-label no-padding-right">
								<font color='red'> * </font>
									适用机构：
								</label>
								<div class="col-xs-12 col-sm-3">
									<span class="block input-icon input-icon-right">
										<select  multiple="multiple" class="form-control" name="orgId" id="orgId" data-placeholder="请选择">
											<c:forEach items="${orgDepartment}" var="obj">
												<option value="${obj.id}" <c:if test="${obj.isSelected}">selected="selected"</c:if> >${obj.name }</option>
											</c:forEach>
										</select>
									</span>
								</div>
								<div class="help-block col-xs-12 col-sm-reset inline"></div>
							</div>
							
							<div class="form-group showHideCusProp">
								<label class="col-xs-12 col-sm-2 control-label no-padding-right">
								<font color='red'> * </font>
									客户性质：
								</label>
								<div class="col-xs-12 col-sm-3">
									<span class="block input-icon input-icon-right">
										<dd:checkbox codeType="EmploymentType" cbName="customerProperty" checked="${productConfig.customerProperty }" aceStyle="true"/>
									</span>
								</div>
								<div class="help-block col-xs-12 col-sm-reset inline"></div>
							</div>
							
							<div class="form-group">
								<label class="col-xs-12 col-sm-2 control-label no-padding-right">
									贷款期限模式：
								</label>
								<div class="col-xs-12 col-sm-3">
									<span class="block input-icon input-icon-right">
										<select id="loanTermMode" name="loanTermMode" class="form-control">
											<dd:options codeType="LoanTermMode" selected="${productConfig.loanTermMode }"/>
										</select>
										<input type="hidden" name="loanTermModeHidden" value="${productConfig.loanTermMode }">
									</span>
								</div>
								<div class="help-block col-xs-12 col-sm-reset inline"></div>
			
								<span id="term1div" style="display: none;">
									<label class="col-xs-12 col-sm-2 control-label no-padding-right">
									<font color='red'> * </font>
										贷款期限：
									</label>
									<div class="col-xs-12 col-sm-3">
										<span class="block input-icon input-icon-right">
											<input type="text" style="width: 80%;" id="minLoanTerm1" name="minLoanTerm1" value="${productConfig.minLoanTerm }">
											<select style="width: 20%;height: 33.5px;margin-left: -4px;" id="minLoanTerm1Unit" name="replyPeriodUnit">
												<dd:options codeType="TermUnitCd" selected="${productConfig.replyPeriodUnit }" excludes="1"/>
											</select>
										</span>
									</div>
									<div class="help-block col-xs-12 col-sm-reset inline"></div>
								</span>
								<span id="term2div" style="display: none;">
									<label class="col-xs-12 col-sm-2 control-label no-padding-right">
									<font color='red'> * </font>
										贷款期限区间：
									</label>
									<div class="col-xs-12 col-sm-3 group-inline">
										<span class="block input-icon input-icon-right">
											<div class="input-group col-sm-10"  style="float: left;">
												<input type="text" class="form-control" id="minLoanTerm" name="minLoanTerm" value="${productConfig.minLoanTerm }">
												<span class="input-group-addon">至</span>
												<input type="text" class="form-control"  id="maxLoanTerm"  name="maxLoanTerm" value="${productConfig.maxLoanTerm }">
											</div>
												<select id="termUnit" style="min-width: 17%;height: 33.5px;margin-left: -4px;" name="replyPeriodUnit">
													<dd:options codeType="TermUnitCd"  selected="${productConfig.replyPeriodUnit }" excludes="1"/>
												</select>
										</span>
									</div>
									<div class="help-block col-xs-12 col-sm-reset inline"></div>
								</span>
								<span id="term3div" style="display: none">
									<label class="col-xs-12 col-sm-2 control-label no-padding-right">
									<font color='red'> * </font>
										贷款期限：
									</label>
									<div class="col-xs-12 col-sm-3">
										<span class="block input-icon input-icon-right">
											<select  multiple="multiple" class="form-control chosen-select2" name="specialLoanTerm" id="specialLoanTerm" data-placeholder="请选择">
												<dd:options codeType="SpecialLoanTerm" selected="${productConfig.specialLoanTerm }"/>
											</select>
										</span>
									</div>
									<div class="help-block col-xs-12 col-sm-reset inline"></div>
								</span>
							</div>
							
							<div class="form-group">
							<label class="col-xs-12 col-sm-2 control-label no-padding-right">
									金额区间：
								</label>
								<div class="col-xs-12 col-sm-3">
									<span class="block input-icon input-icon-right">
										<div class="input-group">
											<input type="text" class="form-control" name="minApplyAmt" id="minApplyAmt" value="${productConfig.minApplyAmt }">
											<span class="input-group-addon">至</span>
											<input type="text" class="form-control" name="maxApplyAmt" id="maxApplyAmt" value="${productConfig.maxApplyAmt }">
											<span class="input-group-addon">元</span>
										</div>
									</span>
								</div>
								<div class="help-block col-xs-12 col-sm-reset inline"></div>
								<label class="col-xs-12 col-sm-2 control-label no-padding-right">
									利率：
								</label>
								<div class="col-xs-12 col-sm-3">
									<span class="block input-icon input-icon-right">
										<div class="input-group">
											<input type="text" class="form-control" id="minRate" name="minRate" value="${productConfig.minRate }">
											<span class="input-group-addon">至</span>
											<input type="text" class="form-control" id="maxRate" name="maxRate" value="${productConfig.maxRate }">
											<span class="input-group-addon">%</span>
										</div>
									</span>
								</div>
								<div class="help-block col-xs-12 col-sm-reset inline"></div>
			
							</div>
							
							<div class="form-group">
							<label class="col-xs-12 col-sm-2 control-label no-padding-right">
									逾期利率上浮比例：
								</label>
								<div class="col-xs-12 col-sm-3">
									<span class="block input-icon input-icon-right">
										<div class="input-group">
											<input type="text" class="form-control" id="minOverdueRate" name="minOverdueRate"  value="${productConfig.minOverdueRate }">
											<span class="input-group-addon">至</span>
											<input type="text" class="form-control" id="maxOverdueRate" name="maxOverdueRate"  value="${productConfig.maxOverdueRate }">
											<span class="input-group-addon">%</span>
										</div>
									</span>
								</div>
								<div class="help-block col-xs-12 col-sm-reset inline"></div>
							
								<label class="col-xs-12 col-sm-2 control-label no-padding-right">
									挪用利率上浮比例：
								</label>
								<div class="col-xs-12 col-sm-3">
									<span class="block input-icon input-icon-right">
										<div class="input-group">
											<input type="text" class="form-control"  id="minPerculNegoRate" name="minPerculNegoRate" value="${productConfig.minPerculNegoRate }">
											<span class="input-group-addon">至</span>
											<input type="text" class="form-control"  id="maxPerculNegoRate" name="maxPerculNegoRate" value="${productConfig.maxPerculNegoRate }">
											<span class="input-group-addon">%</span>
										</div>
									</span>
								</div>
								<div class="help-block col-xs-12 col-sm-reset inline"></div>
							</div>
							
							<div class="form-group">
							<label class="col-xs-12 col-sm-2 control-label no-padding-right">
									提前还款违约金比例：
								</label>
								<div class="col-xs-12 col-sm-3">
									<span class="block input-icon input-icon-right">
										<div class="input-group">
											<input type="text" class="form-control" id="minPreRepaymentRate" name="minPreRepaymentRate" value="${productConfig.minPreRepaymentRate }">
											<span class="input-group-addon">至</span>
											<input type="text" class="form-control" id="maxPreRepaymentRate" name="maxPreRepaymentRate" value="${productConfig.maxPreRepaymentRate }">
											<span class="input-group-addon">%</span>
										</div>
									</span>
								</div>
								<div class="help-block col-xs-12 col-sm-reset inline"></div>
							
								<label class="col-xs-12 col-sm-2 control-label no-padding-right">
									还款方式：
								</label>
								<div class="col-xs-12 col-sm-3">
									<span class="block input-icon input-icon-right">
										<select multiple="multiple" class="form-control" name="repayingMode" id="repayingMode" data-placeholder="请选择">
											<dd:options codeType="RepaymentMode" selected="${productConfig.repayingMode }"/>
										</select>
									</span>
									<input type="hidden" id="repaymentType" name="repaymentType">
								</div>
								<div class="help-block col-xs-12 col-sm-reset inline"></div>
							</div>
							
							<div class="form-group">
							<label class="col-xs-12 col-sm-2 control-label no-padding-right">
									还款周期(月数)：
								</label>
								<div class="col-xs-12 col-sm-3">
									<span class="block input-icon input-icon-right">
										<input type="text" class="form-control"  name="repayPeriodNum" value="${productConfig.repayPeriodNum }">
									</span>
								</div>
								<div class="help-block col-xs-12 col-sm-reset inline"></div>
								<label class="col-xs-12 col-sm-2 control-label no-padding-right">
									担保方式：
								</label>
								<div class="col-xs-12 col-sm-3">
									<span class="block input-icon input-icon-right">
										<dd:checkbox codeType="CdsGuarantMode" cbName="guaranteeMode" aceStyle="true" checked="${productConfig.guaranteeMode }"/>
									</span>
								</div>
								<div class="help-block col-xs-12 col-sm-reset inline"></div>
							</div>
							
							<div class="form-group">
							<label class="col-xs-12 col-sm-2 control-label no-padding-right">
									是否批量：
								</label>
								<div class="col-xs-12 col-sm-3">
									<span class="block input-icon input-icon-right" id="isBatch">
										<dd:radio codeType="CommonWhether" cbName="isBatch" aceStyle="true" checked="${productConfig.isBatch }"/>
									</span>
								</div>
								<div class="help-block col-xs-12 col-sm-reset inline"></div>
			
								<label class="col-xs-12 col-sm-2 control-label no-padding-right">
										批量额度：
									</label>
									<div class="col-xs-12 col-sm-3">
									<span class="block input-icon input-icon-right">
										<input type="text" class="form-control" name="batchLimit" id="batchLimit" value="${productConfig.batchLimit }">
									</span>
								</div>
								<div class="help-block col-xs-12 col-sm-reset inline"></div>
								<%-- <label class="col-xs-12 col-sm-2 control-label no-padding-right">
									审批流程：
								</label>
								<div class="col-xs-12 col-sm-3">
									<span class="block input-icon input-icon-right">
										<select class="form-control" name="wfCode">
											<dd:options codeType="ApproveWorkFlowCode" selected="${productConfig.wfCode }"/>
										</select>
									</span>
								</div>
								<div class="help-block col-xs-12 col-sm-reset inline"></div> --%>
							</div>
							
							<div class="form-group">
								<label class="col-xs-12 col-sm-2 control-label no-padding-right">
									<font color='red'> * </font>
									是否启用：
								</label>
								<div class="col-xs-12 col-sm-3">
									<span class="block input-icon input-icon-right">
										<dd:radio codeType="ProductControlTypeCode" cbName="isStart"  aceStyle="true" checked="${product.productControlTypeCd }"/>
									</span>
								</div>
								<div class="help-block col-xs-12 col-sm-reset inline"></div>
			
							</div>
							
							<div class="form-group">
								<label class="col-xs-12 col-sm-2 control-label no-padding-right">
									备注： 
								</label>
								<div class="col-xs-12 col-sm-3">
									<span class="block input-icon input-icon-right">
										<textarea class="form-control isTextarea" rows="4" name="remarks">${productConfig.remarks }</textarea>
									</span>
								</div>
								<div class="help-block col-xs-12 col-sm-reset inline"></div>
			
								<label class="col-xs-12 col-sm-2 control-label no-padding-right">
									产品描述：
								</label>
								<div class="col-xs-12 col-sm-3">
									<span class="block input-icon input-icon-right">
										<textarea class="form-control isTextarea" rows="4" name="productDesc">${productConfig.productDesc }</textarea>
									</span>
								</div>
								<div class="help-block col-xs-12 col-sm-reset inline"></div>
							</div>
							<div class="row">
							<hr>
								<div class="col-md-4 col-md-offset-5">
									<button type="submit" class="btn btn-primary" id="save" data-loading-text="保存中...">
										<i class="ace-icon fa fa-floppy-o"></i> 保存
									</button>
									<button type="button" class="btn btn-primary" id="back">
										<i class="ace-icon fa fa-reply"></i> 返回
									</button>
								</div>
							</div>
						</form>
					</div>
				</div>

			</div>
		</div>
		<c:import url="../../../commons/footer.jsp" charEncoding="UTF-8" />
	</div>
</body>

<c:import url="../../../commons/post-include.jsp" />
<script type="text/javascript">
	seajs.use('${ctx}/static/my/js/sysmng/productmng/input/main');
</script>
</body>
</html>