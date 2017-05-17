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
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<c:import url="../../../commons/pre-include.jsp" charEncoding="UTF-8" />
<!-- page specific plugin styles START -->
<script>
	var $$ctx = "${ctx}/";
</script>
</head>

<body class="no-skin">
	<c:import url="../../../commons/navbar.jsp" charEncoding="UTF-8" />
	<div class="main-container" id="main-container">
		<script type="text/javascript">
			try {
				ace.settings.check('main-container', 'fixed')
			} catch (e) {
			}
		</script>
		<c:import url="../../../commons/sidebar.jsp" charEncoding="UTF-8" />
		<div class="main-content">
			<div class="breadcrumbs" id="breadcrumbs">
				<script type="text/javascript">
					try {
						ace.settings.check('breadcrumbs', 'fixed')
					} catch (e) {
					}
				</script>
				<ul class="breadcrumb">
					<li>
                                    <i class="ace-icon fa fa-home home-icon">
                                    </i>
                                    <a href="${ctx}">主页</a>
                                   
                                </li>
                                <li class="active">
                                        	贷前管理
                                </li>
                                 <li class="active">
                                        	贷款发放
                                </li>
				</ul>
				<!-- /.breadcrumb -->
				<!-- <div class="nav-search" id="nav-search">
                            <form class="form-search">
                            <span class="input-icon">
                            <input type="text" placeholder="Search ..." class="nav-search-input" id="nav-search-input" autocomplete="off" />
                            <i class="ace-icon fa fa-search nav-search-icon"></i>
                            </span>
                            </form>
                            </div> 
                            -->
			</div>
			<div class="page-content">
				<!-- 隐藏域 -->
				<div>
					<input type="hidden" id="projectIdCwField" value="" /> <input
						type="hidden" id="cuserIdField" value="${cuserId}" /> <input
						type="hidden" id="contractIdField" value="${contractId}" />
					
				</div>
				<%-- <c:import url="../commons/settings.jsp" charEncoding="UTF-8" /> --%>
				<div class="page-header">
					 <h1>
                                    	贷款发放
                                    <small>
                                        <i class="ace-icon fa fa-angle-double-right">
                                        </i>
                                        	新增放款
                                    </small>
                     </h1>
				</div>
				<!-- /.page-header -->
				
				<div class="row">
					<div class="col-xs-12">
						<div class="row">
							<div class="col-xs-12">
								<!-- 表单主体-->
								<form class="form-horizontal" id="form-fkxx">
									<div class="col-xs-12  ">
										<h4 class="blue">放款信息</h4>
										
										<input type="hidden" id="isMultipleLoan" name="isMultipleLoan" value="" />
										<input type="hidden" id="isAllowLoan" name="isAllowLoan" value="" />
										
										<div class="form-group">
											<label
												class="col-xs-12 col-sm-2 control-label no-padding-right">
												客户编号 </label>
											<div class="col-xs-12 col-sm-3">
												<span class="block input-icon input-icon-right"> <input
													type="text" name="customerNum" readonly="readonly"
													class="form-control"
													 /> </span>
											</div>
											<div class="help-block col-xs-12 col-sm-reset inline">
											</div>
											
											<label
												class="col-xs-12 col-sm-2 control-label no-padding-right">
												客户名称* </label>
											<div class="col-xs-12 col-sm-3">
												<span class="block input-icon input-icon-right"> <input
													type="text" name="customerName" class="form-control" readonly="readonly"
													 />
												</span>
											</div>
											<div class="help-block col-xs-12 col-sm-reset inline">
											</div>
										</div>
											
											
											<div class="form-group">
											<label
												class="col-xs-12 col-sm-2 control-label no-padding-right">
												客户经理 </label>
											<div class="col-xs-12 col-sm-3">
												<span class="block input-icon input-icon-right"> <input
													type="text" name="customerManager" readonly="readonly"
													class="form-control"
													 /> </span>
											</div>
											<div class="help-block col-xs-12 col-sm-reset inline">
											</div>
											
											<label
												class="col-xs-12 col-sm-2 control-label no-padding-right">
												贷款合同编号 </label>
											<div class="col-xs-12 col-sm-3">
												<span class="block input-icon input-icon-right"> <input
													type="text" name="contractNum" class="form-control" readonly="readonly"
													 />
												</span>
											</div>
											<div class="help-block col-xs-12 col-sm-reset inline">
											</div>
										</div>
											
											
											
											<div class="form-group">
											<label
												class="col-xs-12 col-sm-2 control-label no-padding-right">
												贷款产品 </label>
											<div class="col-xs-12 col-sm-3">
												<span class="block input-icon input-icon-right"> <input
													type="text" name="productTypeMask" disabled="disabled"
													class="form-control"
													 /> 
													<input
													type="hidden" name="productType" 
													class="form-control" 
													 />  
													 </span>
											</div>
											<div class="help-block col-xs-12 col-sm-reset inline">
											</div>
											
											<label
												class="col-xs-12 col-sm-2 control-label no-padding-right">
												放款编号 </label>
											<div class="col-xs-12 col-sm-3">
												<span class="block input-icon input-icon-right"> <input
													type="text" name="payLoanNum" class="form-control" readonly="readonly"
													 />
												</span>
											</div>
											<div class="help-block col-xs-12 col-sm-reset inline">
											</div>
										</div>
											
										
										<div class="form-group">
											<label
												class="col-xs-12 col-sm-2 control-label no-padding-right">
												贷款合同金额（元）</label>
											<div class="col-xs-12 col-sm-3">
												<span class="block input-icon input-icon-right"> <input
													type="text" name="contractAmt" readonly="readonly"
													class="form-control"
													 /> </span>
											</div>
											<div class="help-block col-xs-12 col-sm-reset inline">
											</div>
											
											<label
												class="col-xs-12 col-sm-2 control-label no-padding-right">
												贷款合同期限 </label>
											<div class="col-xs-12 col-sm-3">
												<span class="block input-icon input-icon-right"> <input
													type="text" name="contractTermUnit" class="form-control" readonly="readonly"
													 />
												</span>
											</div>
											<div class="help-block col-xs-12 col-sm-reset inline">
											</div>
										</div>	
											
											
											
										<div class="form-group">
											<label
												class="col-xs-12 col-sm-2 control-label no-padding-right">
												贷款年利率（%）</label>
											<div class="col-xs-12 col-sm-3">
												<span class="block input-icon input-icon-right"> <input
													type="text" name="contractRateMask"  readonly="readonly"
													class="form-control"
													 /> 
													 <input
													type="hidden" name="contractRate"  readonly="readonly"	
													
													 />
													 </span>
											</div>
											<div class="help-block col-xs-12 col-sm-reset inline">
											</div>
											
											<label
												class="col-xs-12 col-sm-2 control-label no-padding-right">
												还款方式 </label>
											<div class="col-xs-12 col-sm-3">
												<span class="block input-icon input-icon-right"> <input
													type="text" name="repayModeCdMask" class="form-control" readonly="readonly"
													 />
													 <input
													type="hidden" name="repayModeCd" class="form-control" readonly="readonly"
													 />
												</span>
											</div>
											<div class="help-block col-xs-12 col-sm-reset inline">
											</div>
										</div>		
										
										
										
											<div class="form-group">
											<label
												class="col-xs-12 col-sm-2 control-label no-padding-right">
												还款周期月数</label>
											<div class="col-xs-12 col-sm-3">
												<span class="block input-icon input-icon-right"> <input
													type="text" name="repayPrincipalMonthes" readonly="readonly"
													class="form-control"
													 /> </span>
											</div>
											<div class="help-block col-xs-12 col-sm-reset inline">
											</div>
											
											<label
												class="col-xs-12 col-sm-2 control-label no-padding-right">
												约定还款日 </label>
											<div class="col-xs-12 col-sm-3">
												<span class="block input-icon input-icon-right"> <input
													type="text" name="arrangeRepayDay" class="form-control" readonly="readonly"
													 />
												</span>
											</div>
											<div class="help-block col-xs-12 col-sm-reset inline">
											</div>
										</div>		
										
										
										<div class="form-group">
											<label
												class="col-xs-12 col-sm-2 control-label no-padding-right">
												开户行名称*</label>
											<div class="col-xs-12 col-sm-3">
												<span class="block input-icon input-icon-right"> 
												 <input id="bankName" type="text" name="bankName" class="form-control" readonly="readonly"/>
											</div>
											<div class="help-block col-xs-12 col-sm-reset inline">
											</div>
											
											<label
												class="col-xs-12 col-sm-2 control-label no-padding-right">
												贷款账号*</label>
											<div class="col-xs-12 col-sm-3">
												<span class="block input-icon input-icon-right"> <input
													type="text" name="loanNum" class="form-control" id="accountNum"
													readonly="readonly"  />
												</span>
											</div>
											<div class="help-block col-xs-12 col-sm-reset inline">
											</div>
										</div>	
										
										
										
										<div class="form-group">
											<label
												class="col-xs-12 col-sm-2 control-label no-padding-right">
												约定方式</label>
											<div class="col-xs-12 col-sm-3">
												<span class="block input-icon input-icon-right"> <input
													type="text" name="loanDateStyleMask"  disabled="disabled"
													class="form-control"
													 />
													 <input
													type="hidden" name="loanDateStyle"  
													class="form-control"
													 />
													  </span>
											</div>
											<div class="help-block col-xs-12 col-sm-reset inline">
											</div>
											
											<label
												class="col-xs-12 col-sm-2 control-label no-padding-right">
												本次可发放金额（元）</label>
											<div class="col-xs-12 col-sm-3">
												<span class="block input-icon input-icon-right"> <input
													type="text" name="contractAvailableAmt" id="contractAvailableAmt" class="form-control" readonly="readonly"
													 />
												</span>
											</div>
											<div class="help-block col-xs-12 col-sm-reset inline">
											</div>
										</div>	
										
										
										
										<div class="form-group">
											<label
												class="col-xs-12 col-sm-2 control-label no-padding-right">
												合同已发放金额（元）</label>
											<div class="col-xs-12 col-sm-3">
												<span class="block input-icon input-icon-right"> <input
													type="text" name="cumulativePayoutAmt" readonly="readonly"
													class="form-control"
													 /> </span>
											</div>
											<div class="help-block col-xs-12 col-sm-reset inline">
											</div>
											
											<label
												class="col-xs-12 col-sm-2 control-label no-padding-right">
												合同已还金额（元）</label>
											<div class="col-xs-12 col-sm-3">
												<span class="block input-icon input-icon-right"> <input
													type="text" name="cumulativeRepayAmt" class="form-control" readonly="readonly"
													 />
												</span>
											</div>
											<div class="help-block col-xs-12 col-sm-reset inline">
											</div>
										</div>	
										
										
										<div class="form-group">
											<label
												class="col-xs-12 col-sm-2 control-label no-padding-right">
												本次发放金额（元）*</label>
											<div class="col-xs-12 col-sm-3">
												<span class="block input-icon input-icon-right"> 
												<input type="text" name="loanAmt" readonly="readonly"
													class="form-control"
													 /> </span>
											</div>
											<div class="help-block col-xs-12 col-sm-reset inline">
											</div>
											
											<label
												class="col-xs-12 col-sm-2 control-label no-padding-right">
												放款日期*</label>
											<div class="col-xs-12 col-sm-3">
												<span class="block input-icon input-icon-right">
												<i class="ace-icon fa fa-calendar"></i>
												 <input
													type="text" name="loanRegistTime" id="loanRegistTime" class="form-control" data-date-format="yyyy-mm-dd"
													 />
												</span>
											</div>
											<div class="help-block col-xs-12 col-sm-reset inline">
											</div>
										</div>	
										
										
										
										<div class="form-group">
											<label
												class="col-xs-12 col-sm-2 control-label no-padding-right">
												累计收取手续费及佣金（元）</label>
											<div class="col-xs-12 col-sm-3">
												<span class="block input-icon input-icon-right"> <input
													type="text" name="freePayLoanAmtCnt" readonly="readonly"
													class="form-control"
													 /> </span>
											</div>
											<div class="help-block col-xs-12 col-sm-reset inline">
											</div>
											
											<label
												class="col-xs-12 col-sm-2 control-label no-padding-right">
												手续费及佣金收入（元）*</label>
											<div class="col-xs-12 col-sm-3">
												<span class="block input-icon input-icon-right"> <input
													type="text" name="sumAmt" id="sumAmt" class="form-control"
													 />
												</span>
											</div>
											<div class="help-block col-xs-12 col-sm-reset inline">
											</div>
										</div>	
										
										
										<div class="form-group">
											<label
												class="col-xs-12 col-sm-2 control-label no-padding-right">
												费用来源</label>
											<div class="col-xs-12 col-sm-3">
												<span class="block input-icon input-icon-right"> 
													 <select name="sourceType" id="sourceType" class="form-control">
													 <dd:options codeType="FundsSource"/>
													 </select>
													 </span>
											</div>
											<div class="help-block col-xs-12 col-sm-reset inline">
											</div>
											
											<label
												class="col-xs-12 col-sm-2 control-label no-padding-right">
												申请费用金额（元）</label>
											<div class="col-xs-12 col-sm-3">
												<span class="block input-icon input-icon-right"> <input
													type="text" name="freeamtcnt" class="form-control" readonly="readonly"
													 />
												</span>
											</div>
											<div class="help-block col-xs-12 col-sm-reset inline">
											</div>
										</div>	
										
										
										
										<div class="form-group">
											<label
												class="col-xs-12 col-sm-2 control-label no-padding-right">
												是否有保险</label>
											<div class="col-xs-12 col-sm-3">
												<span class="block input-icon input-icon-right"> 
												<select name="ifInsure" id="ifInsure" class="form-control" disabled="disabled">
                                                     <dd:options codeType="IfInsureType" />
                                                </select>
												 </span>
											</div>
											<div class="help-block col-xs-12 col-sm-reset inline">
											</div>
											
											<label
												class="col-xs-12 col-sm-2 control-label no-padding-right">
												</label>
											<div class="col-xs-12 col-sm-3">
												<span class="block input-icon input-icon-right"> 
												</span>
											</div>
											<div class="help-block col-xs-12 col-sm-reset inline">
											</div>
										</div>	
										
										
										<div class="form-group">
											<label
												class="col-xs-12 col-sm-2 control-label no-padding-right">
												经办日期</label>
											<div class="col-xs-12 col-sm-3">
												<span class="block input-icon input-icon-right"> 
												<i class="ace-icon fa fa-calendar"></i>
												<input
													type="text" name="createDate" id="createDate" class="form-control" readonly="readonly" data-date-format="yyyy-mm-dd"
													 />
												
												 </span>
											</div>
											<div class="help-block col-xs-12 col-sm-reset inline">
											</div>
											
											<label
												class="col-xs-12 col-sm-2 control-label no-padding-right">
												经办机构</label>
											<div class="col-xs-12 col-sm-3">
												<span class="block input-icon input-icon-right"> 
												<input
													type="text" name="applyOrgIdMask" class="form-control" disabled="disabled"
													 />
												<input
													type="hidden" name="applyOrgId" class="form-control" 
													 />
												</span>
											</div>
											<div class="help-block col-xs-12 col-sm-reset inline">
											</div>
										</div>	
										
										
										
										<div class="form-group">
											<label
												class="col-xs-12 col-sm-2 control-label no-padding-right">
												经办人</label>
											<div class="col-xs-12 col-sm-3">
												<span class="block input-icon input-icon-right"> 
												<input
													type="text" name="applyUserNumMask" class="form-control" disabled="disabled"
													 />
												<input
													type="hidden" name="applyUserNum" class="form-control" 
													 />
												 </span>
											</div>
											<div class="help-block col-xs-12 col-sm-reset inline">
											</div>
											
											<label
												class="col-xs-12 col-sm-2 control-label no-padding-right">
												</label>
											<div class="col-xs-12 col-sm-3">
												<span class="block input-icon input-icon-right"> 
												</span>
											</div>
											<div class="help-block col-xs-12 col-sm-reset inline">
											</div>
										</div>	
										
											<div class="form-group">
											<label
												class="col-xs-12 col-sm-2 control-label no-padding-right">
												备注*</label>
											<div class="col-xs-12 col-sm-8">
												<span class="block input-icon input-icon-right"> 
												<textarea rows="5" cols="40" name="remark" class="form-control"></textarea>
												 </span>
											</div>
											<div class="help-block col-xs-12 col-sm-reset inline">
											</div>
											
											
										</div>	
									</div>
								</form>
								<div class="wizard-actions" style="text-align: center;margin-top: 10px;">
								<button class="btn btn-sm btn-success" id="submitAndPrint"
								type="button" data-loading-text="正在提交..">
								<i class="ace-icon fa fa-plus"></i> 提交并打印借据
								</button>
								<button class="btn btn-sm btn-pre" id="goBackButton"
								data-last="Finish" type="button"
								onclick="javascript:history.go(-1);">
								<i class="ace-icon fa fa-chevron-left"></i> 返回
								</button>
								</div>
								
							</div>
						</div>
					</div>
				</div>
				<!-- /.row -->


			</div>
			<!-- /.page-content -->
		</div>
		<!-- /.main-content -->
		<c:import url="../../../commons/footer.jsp" charEncoding="UTF-8" />
	</div>
	<!-- /.main-container -->
	<c:import url="../../../commons/post-include.jsp" charEncoding="UTF-8" />
	<!-- page specific plugin scripts START -->
	<!-- page specific plugin scripts END -->
	<!-- inline scripts related to this page -->
	</script>
	<script>
		seajs.use('${ctx}/static/my/js/contract/records/add/main');
	</script>
</body>