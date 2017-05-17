<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../../commons/taglibs.jsp"%>
<!-- 工商业过去 -->
<div id="modal-formNBusiness_pass" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="false">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<form id="other_biz_income_form" class="form-horizontal">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="blue bigger">
					</h4>
				</div>
				<div class="modal-body">

					<!-- 隐藏域 -->
					<input type="hidden" name="id">
					<input type="hidden" name="projectId" value="${vo.projectId }">
					<input type="hidden" name="type" />
					<input type="hidden" name="futurePastType" />

					<div class="row">
						<div class="form-group col-md-4">
							<label class="col-xs-12 col-sm-6 control-label no-padding-right"><font color="red">*</font>经营内容：
							</label>
							<div class="col-xs-12 col-sm-6">
								<input type="text" class="form-control input-sm" name="businessContent" />
							</div>
						</div>
						<div class="form-group col-md-4">
							<label class="col-xs-12 col-sm-6 control-label no-padding-right"><font color="red">*</font>经营起始年月：
							</label>
							<div class="col-xs-12 col-sm-6">
								<input type="text" class="form-control input-sm" name="businessStartDate" data-date-format="yyyy-mm-dd" />
							</div>
						</div>
						<div class="form-group col-md-4">
							<label class="col-xs-12 col-sm-6 control-label no-padding-right">经营场所：
							</label>
							<div class="col-xs-12 col-sm-6">
								<select name="businessPlace" class="form-control input-sm">
									<dd:options codeType="BusinessPlace"/>
								</select>
							</div>
						</div>
					</div>
					
					<div class="form-group">
						<span id="stockWhileSurveyingShowHide_pass">
							<label class="col-xs-12 col-sm-2 control-label no-padding-right"><font color="red">*</font>调查时库存：</label>
							<div class="col-xs-12 col-sm-3">
								<span class="block input-icon input-icon-right">
									<input type="text" class="form-control" name="stockWhileSurveying" />
								</span>
							</div>
							<div class="help-block col-xs-12 col-sm-reset inline"></div>
						</span>
					</div>

					<table style="width: 100%;">
						<tr>
							<td align="center"><h5 class="green">旺季</h5>
							</td>
							<td align="center"><h5 class="green">淡季</h5>
							</td>
						</tr>
					</table>
					<div class="form-group">
						<label class="col-xs-12 col-sm-2 control-label no-padding-right"><font color="red">*</font>月数（月）：</label>
						<div class="col-xs-12 col-sm-4">
							<span class="block input-icon input-icon-right"> <input
								type="text" class="form-control change_yearIncomeTotal" name="month_peak" /> </span>
						</div>
						<div class="help-block col-xs-12 col-sm-reset inline"></div>

						<label class="col-xs-12 col-sm-1 control-label no-padding-right"></label>
						<div class="col-xs-12 col-sm-4">
							<span class="block input-icon input-icon-right"> <input
								type="text" class="form-control change_yearIncomeTotal" name="month_slack" /> </span>
						</div>
						<div class="help-block col-xs-12 col-sm-reset inline"></div>
					</div>
					<div class="form-group">
						<label class="col-xs-12 col-sm-2 control-label no-padding-right"><font color="red">*</font>每月收入：</label>
						<div class="col-xs-12 col-sm-4">
							<span class="block input-icon input-icon-right"> <input
								type="text" class="form-control change_yearIncomeTotal" name="monthlyIncome_peak" /> </span>
						</div>
						<div class="help-block col-xs-12 col-sm-reset inline"></div>

						<label class="col-xs-12 col-sm-1 control-label no-padding-right"></label>
						<div class="col-xs-12 col-sm-4">
							<span class="block input-icon input-icon-right"> <input
								type="text" class="form-control change_yearIncomeTotal" name="monthlyIncome_slack" /> </span>
						</div>
						<div class="help-block col-xs-12 col-sm-reset inline"></div>
					</div>
					<div class="form-group">
						<label class="col-xs-12 col-sm-2 control-label no-padding-right">年收入合计：</label>
						<div class="col-xs-12 col-sm-9">
							<span class="block input-icon input-icon-right">
								<input type="text" class="form-control" name="yearIncomeTotal" readonly="readonly"/>
							</span>
						</div>
						<div class="help-block col-xs-12 col-sm-reset inline"></div>
					</div>
					<div class="form-group">
						<label class="col-xs-12 col-sm-2 control-label no-padding-right"><font color="red">*</font>年成本合计：</label>
						<div class="col-xs-12 col-sm-9">
							<span class="block input-icon input-icon-right">
								<input type="text" class="form-control" name="yearChangeableCostTotal"/>
							</span>
						</div>
						<div class="help-block col-xs-12 col-sm-reset inline"></div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="submit" class="btn btn-primary">
						<i class="ace-icon fa fa-floppy-o"></i> 保存
					</button>
					<button type="button" class="btn btn-warning" data-dismiss="modal">取消</button>
				</div>
			</form>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal -->
</div>

<!-- 工商业未来 -->
<div id="modal-formNBusiness_future" class="modal fade modal-wizard" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
		aria-hidden="true" data-backdrop="static">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
						<div class="modal-header" id="business-fuelux-wizard" data-target="#business-modal-step-contents">
							<h4 class="blue bigger"><i class="ace-icon fa fa-plus"></i></h4>
							<ul class="wizard-steps">
								<li data-target="#business-modal-step1" class="active">
									<span class="step">1</span>
									<span class="title">工商业业信息</span>
								</li>

								<li data-target="#business-modal-step2">
									<span class="step">2</span>
									<span class="title">收入信息</span>
								</li>

								<li data-target="#business-modal-step3">
									<span class="step">3</span>
									<span class="title">支出信息</span>
								</li>

							</ul>
						</div>
						<div class="modal-body step-content" id="business-modal-step-contents">
								<div class="step-pane active" id="business-modal-step1">
									<div class="center">
										<!-- 添加工商业信息开始 -->
										<form id="nFutureCultivateForm_business" action="#" class="form-horizontal" role="form" method="post" onsubmit="return false;">
											<input type="hidden" name="id" />
											<input type="hidden" name="projectId" />
											<input type="hidden" name="type" />
											<input type="hidden" name="futurePastType" />
											<div class="form-group col-md-4">
												<label
													class="col-xs-12 col-sm-6 control-label no-padding-right"><font color="red">*</font>经营内容：
												</label>
												<div class="col-xs-12 col-sm-6">
													<input type="text" class="form-control input-sm"
														name="businessContent" />
												</div>
											</div>
											
											<div class="form-group col-md-4">
												<label
													class="col-xs-12 col-sm-6 control-label no-padding-right"><font color="red">*</font>经营起始年月：
												</label>
												<div class="col-xs-12 col-sm-6">
													<input type="text" class="form-control input-sm"
														name="businessStartDate" data-date-format="yyyy-mm-dd" />
												</div>
											</div>
											
											<div class="form-group col-md-4">
												<label
													class="col-xs-12 col-sm-6 control-label no-padding-right">经营场所：
												</label>
												<div class="col-xs-12 col-sm-6">
													<select name="businessPlace" class="form-control input-sm">
														<dd:options codeType="BusinessPlace" />
													</select>
												</div>
											</div>
											
											<div class="form-group">
												<span id="stockWhileSurveyingShowHide_future"> <label
													class="col-xs-12 col-sm-2 control-label no-padding-right">调查时库存：</label>
													<div class="col-xs-12 col-sm-3">
														<span class="block input-icon input-icon-right"> <input
															type="text" class="form-control" name="stockWhileSurveying" />
														</span>
													</div>
													<div class="help-block col-xs-12 col-sm-reset inline"></div>
												</span>
											</div>
											
											<table style="width: 100%;">
												<tr>
													<td align="center"><h5 class="green">旺季</h5></td>
													<td align="center"><h5 class="green">淡季</h5></td>
												</tr>
											</table>
											
											<div class="form-group">
												<label
													class="col-xs-12 col-sm-2 control-label no-padding-right"><font color="red">*</font>月数（月）：</label>
												<div class="col-xs-12 col-sm-4">
													<span class="block input-icon input-icon-right"> <input
														type="text" class="form-control change_yearIncomeTotal"
														name="month_peak" />
													</span>
												</div>
												<div class="help-block col-xs-12 col-sm-reset inline"></div>
			
												<label
													class="col-xs-12 col-sm-1 control-label no-padding-right"></label>
												<div class="col-xs-12 col-sm-4">
													<span class="block input-icon input-icon-right"> <input
														type="text" class="form-control change_yearIncomeTotal"
														name="month_slack" />
													</span>
												</div>
												<div class="help-block col-xs-12 col-sm-reset inline"></div>
											</div>
											
											<div class="form-group">
												<label
													class="col-xs-12 col-sm-2 control-label no-padding-right"><font color="red">*</font>每月收入：</label>
												<div class="col-xs-12 col-sm-4">
													<span class="block input-icon input-icon-right"> <input
														type="text" class="form-control change_yearIncomeTotal"
														name="monthlyIncome_peak" />
													</span>
												</div>
												<div class="help-block col-xs-12 col-sm-reset inline"></div>
			
												<label
													class="col-xs-12 col-sm-1 control-label no-padding-right"></label>
												<div class="col-xs-12 col-sm-4">
													<span class="block input-icon input-icon-right"> <input
														type="text" class="form-control change_yearIncomeTotal"
														name="monthlyIncome_slack" />
													</span>
												</div>
												<div class="help-block col-xs-12 col-sm-reset inline"></div>
											</div>
											
											<div class="form-group">
												<label
													class="col-xs-12 col-sm-2 control-label no-padding-right">年收入合计：</label>
												<div class="col-xs-12 col-sm-9">
													<span class="block input-icon input-icon-right"> <input
														type="text" class="form-control" name="yearIncomeTotal"
														readonly="readonly" />
													</span>
												</div>
												<div class="help-block col-xs-12 col-sm-reset inline"></div>
											</div>
											<div class="form-group">
												<label
													class="col-xs-12 col-sm-2 control-label no-padding-right"><font color="red">*</font>年成本合计：</label>
												<div class="col-xs-12 col-sm-9">
													<span class="block input-icon input-icon-right"> <input
														type="text" class="form-control"
														name="yearChangeableCostTotal" />
													</span>
												</div>
												<div class="help-block col-xs-12 col-sm-reset inline"></div>
											</div>
										</form>
										<!-- 工商业表单信息结束 -->
									</div>
								</div>

								<div class="step-pane model-step2" id="business-modal-step2">
									<div class="center">
										<!-- 添加收入信息开始 -->
										<div class="widget-box ui-sortable-handle" style="opacity: 1; z-index: 0;">
											<div class="widget-header">
												<h4 class="blue widget-title">工商业收入</h4>
												<div class="widget-toolbar">
													<button class="btn btn-xs btn-success" type="button" id="btn-addEcfIncome-business" title="新增">
														<i class="ace-icon fa fa-plus"></i>
													</button>
												</div>
											</div>
											
											<div class="widget-body">
												<form id="income_form_business" action="" method="post">
													<input type="hidden" name="objId"/>
													<input type="hidden" name="objType" value="03"/>
													<table id="income_tb_business" data-flag="1" class="table table-striped table-hover income_tb"
													style="white-space: nowrap;">
														<thead>
															<tr>
																<th>序号</th>
																<th>年月</th>
																<th>金额</th>
																<th>备注</th>
																<th width="30px">操作</th>
															</tr>
														</thead>
														<tbody>
															<tr class="hj"><td></td><td class="">合计</td><td class="income-sum">0</td><td class="income-dw">单位：元</td></tr>
														</tbody>
													</table>
												</form>
											</div>
										</div>
										<!-- 添加收入信息结束 -->
									</div>
								</div>

								<div class="step-pane" id="business-modal-step3">
									<div class="center">
										<!-- 添加支出信息开始 -->
											<div class="widget-box ui-sortable-handle" style="opacity: 1; z-index: 0;">
											<div class="widget-header">
												<h4 class="blue widget-title">工商业支出</h4>
												<div class="widget-toolbar">
													<button class="btn btn-xs btn-success" type="button" id="btn-addEcfConsume-business" title="新增">
														<i class="ace-icon fa fa-plus"></i>
													</button>
												</div>
											</div>
											
											<div class="widget-body">
												<table id="out_tb_business"  data-flag="2" class="table table-striped table-hover out_tb"
												style="white-space: nowrap;">
													<thead>
														<tr>
															<th>序号</th>
															<th>年月</th>
															<th>金额</th>
															<th>备注</th>
															<th width="30px">操作</th>
														</tr>
													</thead>
													<tbody>
														<tr class="hj"><td></td><td class="">合计</td><td class="consume-sum">0</td><td class="income-dw">单位：元</td></tr>
													</tbody>
												</table>
											</div>
										</div>
										<!-- 添加支出信息结束 -->
									</div>
								</div>
							</div>
							 <div class="modal-footer wizard-actions">
								<button class="btn btn-sm btn-prev btn-info">
									<i class="ace-icon fa fa-arrow-left"></i>上一步
								</button>
								<button id="submit-n-business_future" class="btn btn-sm btn-primary"
									type="button" data-loading-text="正在提交中...">
										<i class="ace-icon fa fa-floppy-o"></i> 保存
								</button>
								<button class="btn btn-success btn-sm btn-next btn-last-step" data-last="关 闭">
									下一步<i class="ace-icon fa fa-arrow-right icon-on-right"></i>
								</button>
							</div> 
				</div>
			</div>
</div>