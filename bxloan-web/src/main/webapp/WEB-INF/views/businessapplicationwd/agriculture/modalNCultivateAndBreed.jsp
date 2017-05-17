<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../../commons/taglibs.jsp" %>
<div id="modal-formNCultivate_pass" class="modal fade" tabindex="-1" role="basic"
		aria-hidden="true" style=";" data-backdrop="static">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<form id="nPassCultivateForm_nd" class="form-horizontal" role="form" method="post">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">
								&times;
							</button>
							<h4 class="blue bigger">
							</h4>
						</div>
						<div class="modal-body">
							<div class="row">
								<div class="col-xs-12">
									<input type="hidden" name="id" />
									<input type="hidden" name="projectId" />
									<input type="hidden" name="type" />
									<div class="form-group">
										    <div class="ie8">
												<label class="col-sm-2 control-label no-padding-right" for="form-field-1"> 
												<font color="red">*</font>从业年限： 
												 </label>
												<div class="col-sm-4">
													<div class="clearfix">
													<input type="text"  name="workingYears"
													class="form-control  "  />
													</div>
												</div>
											</div>
									</div>
									<div class="form-group">
										    <div class="ie8">
												<label class="col-sm-2 control-label no-padding-right" for="form-field-1"> 
												种植类型： 
												 </label>
												<div class="col-sm-4">
													<div class="clearfix">
													<select   name="cultivateType"
													class="form-control  "  >
														<dd:options codeType="AgroFnCultivateType"/>
													</select>
													</div>
												</div>
											</div>
											<div class="ie8">
												<label class="col-sm-2 control-label no-padding-right" for="form-field-1"> 
												<font color="red">*</font>种植内容： 
												 </label>
												<div class="col-sm-4">
													<div class="clearfix">
													<input type="text"  name="cultivateContent"
													class="form-control  "  />
													</div>
												</div>
											</div>
									</div>
									<div class="form-group">
											<div class="ie8">
												<label class="col-sm-2 control-label no-padding-right" for="form-field-1"> 
												<font color="red">*</font>种植规模：
												 </label>
												<div class="col-sm-4">
													<div class="clearfix">
													<div class="input-group">
														<input type="text" name="cultivateScale" class="form-control" style="width: 70%"/>
        												<input class="form-control" type="text" name="cultivateScaleUnit" style="width: 30%" placeholder="请输入单位"/>
													</div>
													
													</div>
												</div>
											</div>
											<div class="ie8">
												<label class="col-sm-2 control-label no-padding-right" for="form-field-1"> 
												<font color="red">*</font>年总产量： 
												 </label>
												<div class="col-sm-4">
													<div class="clearfix">
													<div class="input-group">
													<input type="text"  name="output" class="form-control" style="width: 70%"/>
													<%-- <dd:unit unitFieldName="outputUnit" codeType="OutputUnit" /> --%>
													<input class="form-control" type="text" name="outputUnit" style="width: 30%" placeholder="请输入单位"/>
													</div>
													</div>
												</div>
											</div>
									</div>
									<div class="form-group">
										    <div class="ie8">
												<label class="col-sm-2 control-label no-padding-right" for="form-field-1"> 
												<font color="red">*</font>家庭消耗量： 
												 </label>
												<div class="col-sm-4">
													<div class="clearfix">
													<input type="text"  name="familyConsume"
													class="form-control  "  />
													</div>
												</div>
											</div>
											<div class="ie8">
												<label class="col-sm-2 control-label no-padding-right" for="form-field-1"> 
												<font color="red">*</font>牲畜消耗量：
												 </label>
												<div class="col-sm-4">
													<div class="clearfix">
													<input type="text" name="livestockConsume"
													class="form-control  "  />
													</div>
												</div>
											</div>
									</div>
									<div class="form-group">
										    <div class="ie8">
												<label class="col-sm-2 control-label no-padding-right" for="form-field-1"> 
												销售数量： 
												 </label>
												<div class="col-sm-4">
													<div class="clearfix">
													<input type="text"  name="saleNum" readonly="readonly"
													class="form-control  "  />
													</div>
												</div>
											</div>
											<div class="ie8">
												<label class="col-sm-2 control-label no-padding-right" for="form-field-1"> 
												<font color="red">*</font>销售单价：
												 </label>
												<div class="col-sm-4">
													<div class="clearfix">
													<div class="input-group">
													<input type="text" name="salePrice" class="form-control" style="width:70%"/>
													<%-- <dd:unit unitFieldName="salePriceUnit" codeType="SalesPriceUnit" /> --%>
													<input class="form-control" type="text" name="salePriceUnit" style="width: 30%" placeholder="请输入单位"/>
													</div>
													</div>
												</div>
											</div>
									</div>
									<div class="form-group">
										    <div class="ie8">
												<label class="col-sm-2 control-label no-padding-right" for="form-field-1"> 
												销售收入合计： 
												 </label>
												<div class="col-sm-4">
													<div class="clearfix">
													<input type="text"  name="saleIncomeTotal"
													class="form-control  " readonly="readonly" />
													</div>
												</div>
											</div>
											<div class="ie8">
												<label class="col-sm-2 control-label no-padding-right" for="form-field-1"> 
												<font color="red">*</font>成本合计：
												 </label>
												<div class="col-sm-4">
													<div class="clearfix">
													<input type="text" name="costTotal"
													class="form-control  "  />
													</div>
												</div>
											</div>
									</div>
									<h4 class="blue widget-title">
									 单产预测
									</h4>
									<div class="form-group">
										    <div class="ie8">
												<label class="col-sm-2 control-label no-padding-right" for="form-field-1"> 
												<font color="red">*</font>单位： 
												 </label>
												<div class="col-sm-4">
													<div class="clearfix">
													<%-- <select name="singleProduceUnit" class="form-control">
														<dd:options codeType="SingleProduceUnit"/>
													</select> --%>
													<input type="text" name="singleProduceUnit" class="form-control"/>
													</div>
												</div>
											</div>
									</div>
									<div class="form-group">
										    <div class="ie8">
												<label class="col-sm-2 control-label no-padding-right" for="form-field-1"> 
												<font color="red">*</font>过去3年最大单产： 
												 </label>
												<div class="col-sm-4">
													<div class="clearfix">
													<input type="text"  name="maxSingleProduce"
													class="form-control  "  />
													</div>
												</div>
											</div>
											<div class="ie8">
												<label class="col-sm-2 control-label no-padding-right" for="form-field-1"> 
												<font color="red">*</font>曾经最小单产：
												 </label>
												<div class="col-sm-4">
													<div class="clearfix">
													<input type="text" name="minSingleProduce"
													class="form-control  "  />
													</div>
												</div>
											</div>
									</div>
									<div class="form-group">
										    <div class="ie8">
												<label class="col-sm-2 control-label no-padding-right" for="form-field-1"> 
												<font color="red">*</font>去年单产： 
												 </label>
												<div class="col-sm-4">
													<div class="clearfix">
													<input type="text"  name="lastYearSingleProduce"
													class="form-control  "  />
													</div>
												</div>
											</div>
											<div class="ie8">
												<label class="col-sm-2 control-label no-padding-right" for="form-field-1"> 
												<font color="red">*</font>预测：
												 </label>
												<div class="col-sm-4">
													<div class="clearfix">
													<input type="text" name="forecast" readonly="readonly"
													class="form-control  "  />
													</div>
												</div>
											</div>
									</div>
									<div class="form-group">
										    <div class="ie8">
												<label class="col-sm-2 control-label no-padding-right" for="form-field-1"> 
												<font color="red">*</font>调查时作物存量价值(元)： 
												 </label>
												<div class="col-sm-4">
													<div class="clearfix">
													<input type="text"  name="cropValue"
													class="form-control  "  />
													</div>
												</div>
											</div>
											<!-- <div class="ie8">
												<label class="col-sm-2 control-label no-padding-right" for="form-field-1"> 
												调查时农资存量价值(元)：
												 </label>
												<div class="col-sm-4">
													<div class="clearfix">
													<input type="text" name="agricultureCapitalValue"
													class="form-control  "  />
													</div>
												</div>
											</div> -->
									</div>
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<button id="submit-n-cultivate_pass" class="btn btn-sm btn-primary"
									type="button" data-loading-text="正在提交中...">
									<i class="ace-icon fa fa-arrow-right"> </i> 保存
							</button>
							<button class="btn btn-warning btn-sm" data-dismiss="modal" type="button">
								<i class="ace-icon fa fa-times">
								</i>
								取消
							</button>
						</div>
					</form>
				</div>
			</div>
</div>

<div id="modal-formNCultivate_future" class="modal fade modal-wizard" tabindex="-1" role="basic"
		aria-hidden="true" data-backdrop="static">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
						<div class="modal-header" id="fuelux-wizard" data-target="#modal-step-contents">
							<h4 class="blue bigger"><i class="ace-icon fa fa-plus"></i></h4>
							<ul class="wizard-steps">
								<li data-target="#modal-step1" class="active">
									<span class="step">1</span>
									<span class="title">种植业信息</span>
								</li>

								<li data-target="#modal-step2">
									<span class="step">2</span>
									<span class="title">收入信息</span>
								</li>

								<li data-target="#modal-step3">
									<span class="step">3</span>
									<span class="title">支出信息</span>
								</li>

							</ul>
						</div>
						<div class="modal-body step-content" id="modal-step-contents">
								<div class="step-pane active" id="modal-step1">
									<div class="center">
										<!-- 添加种植业信息开始 -->
										<form id="nFutureCultivateForm_cultivate" class="form-horizontal" role="form" method="post" >
											<input type="hidden" name="id" />
											<input type="hidden" name="projectId" />
											<input type="hidden" name="type" />
											<input type="hidden" name="_relativeId" />
											<div class="form-group">
											    <div class="ie8">
													<label class="col-sm-2 control-label no-padding-right" for="form-field-1"> 
													关联过去12月信息： 
													 </label>
													<div class="col-sm-4">
														<div class="clearfix">
														<select   name="relativeId"
														class="form-control  "  >
														</select>
														</div>
													</div>
												</div>
											</div>
											
											<div class="form-group">
										    <div class="ie8">
												<label class="col-sm-2 control-label no-padding-right" for="form-field-1"> 
												种植类型： 
												 </label>
												<div class="col-sm-4">
													<div class="clearfix">
													<select   name="cultivateType"
													class="form-control  "  >
														<dd:options codeType="AgroFnCultivateType"/>
													</select>
													</div>
												</div>
											</div>
											<div class="ie8">
												<label class="col-sm-2 control-label no-padding-right" for="form-field-1"> 
												<font color="red">*</font>种植内容： 
												 </label>
												<div class="col-sm-4">
													<div class="clearfix">
													<input type="text"  name="cultivateContent"
													class="form-control"  aria-required="true" readonly="readonly"/>
													</div>
												</div>
											</div>
									</div>
									<div class="form-group">
											<div class="ie8">
												<label class="col-sm-2 control-label no-padding-right" for="form-field-1"> 
												<font color="red">*</font>种植规模：
												 </label>
												<div class="col-sm-4">
													<div class="clearfix">
													<div class="input-group">
														<input type="text" name="cultivateScale"
														class="form-control" style="width: 70%"/>
														<input type="text" name="cultivateScaleUnit" class="form-control" style="width: 30%" placeholder="请输入单位"/>
													</div>
													
													</div>
												</div>
											</div>
											<div class="ie8">
												<label class="col-sm-2 control-label no-padding-right" for="form-field-1"> 
												<font color="red">*</font>预计单产产量： 
												 </label>
												<div class="col-sm-4">
													<div class="clearfix">
													<div class="input-group">
													<input type="text"  name="predictSingleProduce" readonly="readonly"
													class="form-control" style="width: 70%"/>
													<input type="text" name="predictSingleProduceUnit" class="form-control" style="width: 30%" placeholder="请输入单位"/>
													</div>
													</div>
												</div>
											</div>
									</div>
									<div class="form-group">
											<div class="ie8">
												<label class="col-sm-2 control-label no-padding-right" for="form-field-1"> 
												预计年总产量： 
												 </label>
												<div class="col-sm-4">
													<div class="clearfix">
													<div class="input-group">
													<input type="text"  name="predictTotalProduce" readonly="readonly"
													class="form-control" style="width: 70%"/>
													<input type="text" name="predictTotalProduceUnit" class="form-control" style="width: 30%" placeholder="请输入单位"/>
													</div>
													</div>
												</div>
											</div>
									</div>
									<div class="form-group">
										    <div class="ie8">
												<label class="col-sm-2 control-label no-padding-right" for="form-field-1"> 
												<font color="red">*</font>家庭消耗量： 
												 </label>
												<div class="col-sm-4">
													<div class="clearfix">
													<input type="text"  name="familyConsume"
													class="form-control  "  />
													</div>
												</div>
											</div>
											<div class="ie8">
												<label class="col-sm-2 control-label no-padding-right" for="form-field-1"> 
												<font color="red">*</font>牲畜消耗量：
												 </label>
												<div class="col-sm-4">
													<div class="clearfix">
													<input type="text" name="livestockConsume"
													class="form-control  "  />
													</div>
												</div>
											</div>
									</div>
									<div class="form-group">
										    <div class="ie8">
												<label class="col-sm-2 control-label no-padding-right" for="form-field-1"> 
												销售数量： 
												 </label>
												<div class="col-sm-4">
													<div class="clearfix">
													<input type="text"  name="saleNum" readonly="readonly"
													class="form-control  "  />
													</div>
												</div>
											</div>
											<div class="ie8">
												<label class="col-sm-2 control-label no-padding-right" for="form-field-1"> 
												<font color="red">*</font>销售单价：
												 </label>
												<div class="col-sm-4">
													<div class="clearfix">
													<div class="input-group">
													<input type="text" name="salePrice"
													class="form-control" style="width: 70%"/>
													<input type="text" name="salePriceUnit" class="form-control" style="width: 30%" placeholder="请输入单位"/>
													</div>
													</div>
												</div>
											</div>
									</div>
									<div class="form-group">
										    <div class="ie8">
												<label class="col-sm-2 control-label no-padding-right" for="form-field-1"> 
												年收入合计： 
												 </label>
												<div class="col-sm-4">
													<div class="clearfix">
													<input type="text"  name="saleIncomeTotal"
													class="form-control  "  readonly="readonly"/>
													</div>
												</div>
											</div>
											<div class="ie8">
												<label class="col-sm-2 control-label no-padding-right" for="form-field-1"> 
												<font color="red">*</font>预计成本合计：
												 </label>
												<div class="col-sm-4">
													<div class="clearfix">
													<input type="text" name="predictCostTotal"
													class="form-control  "  />
													</div>
												</div>
											</div>
									</div>
									
									<div class="form-group">
										    <div class="ie8">
												<label class="col-sm-2 control-label no-padding-right" for="form-field-1"> 
												<font color="red">*</font>预计销售时间： 
												 </label>
												<div class="col-sm-4">
													<div class="clearfix">
													<span class="block input-icon input-icon-right">
														<input name="predictSaleTime" id="predictSaleTime" type="text" class="form-control formdate" />
													</span>
													</div>
												</div>
											</div>
											</div>
										</form>
										<!-- 添加种植业信息结束 -->
									</div>
								</div>

								<div class="step-pane model-step2" id="modal-step2">
									<div class="center">
										<!-- 添加收入信息开始 -->
										<div class="widget-box ui-sortable-handle" style="opacity: 1; z-index: 0;">
											<div class="widget-header">
												<h4 class="blue widget-title">种植收入</h4>
												<div class="widget-toolbar">
													<button class="btn btn-xs btn-success" type="button" id="btn-addEcfIncome_nd">
														<i class="ace-icon fa fa-plus"></i>
													</button>
												</div>
											</div>
											<div class="widget-body">
												<form id="income_form_cultivate" action="" method="post">
												<input type="hidden" name="objId"/>
												<input type="hidden" name="objType" value="01"/>
													<table id="income_tb_cultivate" data-flag="1" class="table table-striped table-hover income_tb"
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

								<div class="step-pane" id="modal-step3">
									<div class="center">
										<!-- 添加支出信息开始 -->
											<div class="widget-box ui-sortable-handle" style="opacity: 1; z-index: 0;">
											<div class="widget-header">
												<h4 class="blue widget-title">种植支出</h4>
												<div class="widget-toolbar">
													<button class="btn btn-xs btn-success" type="button" id="btn-addEcfConsume_nd">
														<i class="ace-icon fa fa-plus"></i>
													</button>
												</div>
											</div>
											
											<div class="widget-body">
												<table id="out_tb_cultivate" data-flag="2" class="table table-striped table-hover out_tb"
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
								<button id="submit-n-cultivate_future" class="btn btn-sm btn-primary"
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


<div id="modal-formNBreed_pass" class="modal fade" tabindex="-1" role="basic"
		aria-hidden="true" style=";" data-backdrop="static">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<form id="nPassBreedForm_nd" class="form-horizontal" role="form" method="post">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">
								&times;
							</button>
							<h4 class="blue bigger">
							</h4>
						</div>
						<div class="modal-body">
							<div class="row">
								<div class="col-xs-12">
									<input type="hidden" name="id" />
									<input type="hidden" name="projectId" />
									<input type="hidden" name="type" />
									<div class="form-group">
										    <div class="ie8">
												<label class="col-sm-2 control-label no-padding-right" for="form-field-1"> 
												<font color="red">*</font>从业年限： 
												 </label>
												<div class="col-sm-4">
													<div class="clearfix">
													<input type="text"  name="workingYears"
													class="form-control  "  />
													</div>
												</div>
											</div>
									</div>
									<div class="form-group">
										    <div class="ie8">
												<label class="col-sm-2 control-label no-padding-right" for="form-field-1"> 
												养殖类型： 
												 </label>
												<div class="col-sm-4">
													<div class="clearfix">
													<select   name="breedType"
													class="form-control  "  >
														<dd:options codeType="AgroFnBreedType"/>
													</select>
													</div>
												</div>
											</div>
											<div class="ie8">
												<label class="col-sm-2 control-label no-padding-right" for="form-field-1"> 
												<font color="red">*</font>养殖内容： 
												 </label>
												<div class="col-sm-4">
													<div class="clearfix">
													<input type="text"  name="breedContent"
													class="form-control  "  />
													</div>
												</div>
											</div>
									</div>
									<div class="form-group">
											<div class="ie8">
												<label class="col-sm-2 control-label no-padding-right" for="form-field-1"> 
												<font color="red">*</font>养殖方式：
												 </label>
												<div class="col-sm-4">
													<div class="clearfix">
														<input type="text" name="breedMode"
														class="form-control  "  />
													</div>
												</div>
											</div>
											<div class="ie8">
												<label class="col-sm-2 control-label no-padding-right" for="form-field-1"> 
												<font color="red">*</font>养殖规模： 
												 </label>
												<div class="col-sm-4">
													<div class="clearfix">
													<div class="input-group">
													<input type="text"  name="breedScale"
													class="form-control" style="width: 70%"/>
													<input type="text" name="breedScaleUnit" class="form-control" style="width: 30%" placeholder="请输入单位"/>
													</div>
													</div>
												</div>
											</div>
									</div>
									<div class="form-group">
										    <div class="ie8">
												<label class="col-sm-2 control-label no-padding-right" for="form-field-1"> 
												<font color="red">*</font>年销售量： 
												 </label>
												<div class="col-sm-4">
													<div class="clearfix">
													<div class="input-group">
													<input type="text" name="saleNum" class="form-control" style="width: 70%"/>
												    <input type="text" name="saleNumUnit" class="form-control" style="width: 30%" placeholder="请输入单位"/> 
													</div>
													</div>
												</div>
											</div>
											<div class="ie8">
												<label class="col-sm-2 control-label no-padding-right" for="form-field-1"> 
												<font color="red">*</font>销售单价：
												 </label>
												<div class="col-sm-4">
													<div class="clearfix">
													<div class="input-group">
													<input type="text" name="salePrice"
													class="form-control" style="width: 70%"/>
													<input type="text" name="salePriceUnit" class="form-control" style="width: 30%" placeholder="请输入单位"/>
													</div>
													</div>
												</div>
											</div>
									</div>
									<div class="form-group">
										    <div class="ie8">
												<label class="col-sm-2 control-label no-padding-right" for="form-field-1"> 
												销售收入合计： 
												 </label>
												<div class="col-sm-4">
													<div class="clearfix">
													<input type="text"  name="saleIncomeTotal"
													class="form-control  " readonly="readonly" />
													</div>
												</div>
											</div>
											<div class="ie8">
												<label class="col-sm-2 control-label no-padding-right" for="form-field-1"> 
												<font color="red">*</font>成本合计：
												 </label>
												<div class="col-sm-4">
													<div class="clearfix">
													<input type="text" name="costTotal"
													class="form-control  "  />
													</div>
												</div>
											</div>
									</div>
									<h4 class="blue widget-title">
									 单产/成活率预测
									</h4>
									<div class="form-group">
										    <div class="ie8">
												<label class="col-sm-2 control-label no-padding-right" for="form-field-1"> 
												<font color="red">*</font>单位： 
												 </label>
												<div class="col-sm-4">
													<%-- <div class="clearfix">
													<select name="singleProduceUnit" class="form-control">
														<dd:options codeType="SingleProduceUnit"/>
													</select>
													</div> --%>
													<input type="text" name="singleProduceUnit" class="form-control"/>
												</div>
											</div>
									</div>
									<div class="form-group">
										    <div class="ie8">
												<label class="col-sm-2 control-label no-padding-right" for="form-field-1"> 
												<font color="red">*</font>曾经最高： 
												 </label>
												<div class="col-sm-4">
													<div class="clearfix">
													<div class="input-group">
													<input type="text"  name="highest"
													class="form-control  "  />
													<span class="input-group-addon">%</span>
													</div>
													</div>
												</div>
											</div>
											<div class="ie8">
												<label class="col-sm-2 control-label no-padding-right" for="form-field-1"> 
												<font color="red">*</font>曾经最低：
												 </label>
												<div class="col-sm-4">
													<div class="clearfix">
													<div class="input-group">
													<input type="text" name="lowest"
													class="form-control  "  />
													<span class="input-group-addon">%</span>
													</div>
													</div>
												</div>
											</div>
									</div>
									<div class="form-group">
										    <div class="ie8">
												<label class="col-sm-2 control-label no-padding-right" for="form-field-1"> 
												<font color="red">*</font>去年： 
												 </label>
												<div class="col-sm-4">
													<div class="clearfix">
													<div class="input-group">
													<input type="text"  name="lastyear"
													class="form-control  "  />
													<span class="input-group-addon">%</span>
													</div>
													</div>
												</div>
											</div>
											<div class="ie8">
												<label class="col-sm-2 control-label no-padding-right" for="form-field-1"> 
												<font color="red">*</font>预测：
												 </label>
												<div class="col-sm-4">
													<div class="clearfix">
													<div class="input-group">
													<input type="text" name="predict" readonly="readonly"
													class="form-control  "  />
													<span class="input-group-addon">%</span>
													</div>
													</div>
												</div>
											</div>
									</div>
									<div class="form-group">
										    <div class="ie8">
												<label class="col-sm-2 control-label no-padding-right" for="form-field-1"> 
												<font color="red">*</font>存量初始规模： 
												 </label>
												<div class="col-sm-4">
													<div class="clearfix">
													<div class="input-group">
													<input type="text"  name="stockInitScale"
													class="form-control" style="width: 70%"/>
													<input type="text" name="stockInitScaleUnit" class="form-control" style="width: 30%" placeholder="请输入单位"/>
													</div>
													</div>
												</div>
											</div>
											<div class="ie8">
												<label class="col-sm-2 control-label no-padding-right" for="form-field-1"> 
												<font color="red">*</font>养殖存量价值：
												 </label>
												<div class="col-sm-4">
													<div class="clearfix">
													<input type="text" name="breedStockValue"
													class="form-control  "  />
													</div>
												</div>
											</div>
									</div>
									<div class="form-group">
										<div class="ie8">
												<label class="col-sm-2 control-label no-padding-right" for="form-field-1"> 
												<font color="red">*</font>饲料等存货价值：
												 </label>
												<div class="col-sm-4">
													<div class="clearfix">
													<input type="text" name="forageValue"
													class="form-control  "  />
													</div>
												</div>
											</div>
									</div>
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<button id="submit-n-breed_pass" class="btn btn-sm btn-primary"
									type="button" data-loading-text="正在提交中...">
									<i class="ace-icon fa fa-arrow-right"> </i> 保存
							</button>
							<button class="btn btn-warning btn-sm" data-dismiss="modal" type="button">
								<i class="ace-icon fa fa-times">
								</i>
								取消
							</button>
						</div>
					</form>
				</div>
			</div>
</div>



<div id="modal-formNBreed_future" class="modal fade modal-wizard" tabindex="-1" role="basic"
		aria-hidden="true" data-backdrop="static">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
						<div class="modal-header" id="breed-fuelux-wizard" data-target="#breed-modal-step-contents">
							<h4 class="blue bigger"><i class="ace-icon fa fa-plus"></i></h4>
							<ul class="wizard-steps">
								<li data-target="#breed-modal-step1" class="active">
									<span class="step">1</span>
									<span class="title">养殖业信息</span>
								</li>

								<li data-target="#breed-modal-step2">
									<span class="step">2</span>
									<span class="title">收入信息</span>
								</li>

								<li data-target="#breed-modal-step3">
									<span class="step">3</span>
									<span class="title">支出信息</span>
								</li>

							</ul>
						</div>
						<div class="modal-body step-content" id="breed-modal-step-contents">
								<div class="step-pane active" id="breed-modal-step1">
									<div class="center">
										<!-- 添加养殖业信息开始 -->
										<form id="nFutureCultivateForm_breed" class="form-horizontal" role="form" method="post" >
											<input type="hidden" name="id" />
											<input type="hidden" name="projectId" />
											<input type="hidden" name="type" />
											<input type="hidden" name="_relativeId" />
											<div class="form-group">
											    <div class="ie8">
													<label class="col-sm-2 control-label no-padding-right" for="form-field-1"> 
													关联过去12月信息： 
													 </label>
													<div class="col-sm-4">
														<div class="clearfix">
														<select   name="relativeId"
														class="form-control  "  >
														</select>
														</div>
													</div>
												</div>
											</div>
											
											<div class="form-group">
										    <div class="ie8">
												<label class="col-sm-2 control-label no-padding-right" for="form-field-1"> 
												养殖类型： 
												 </label>
												<div class="col-sm-4">
													<div class="clearfix">
													<select   name="breedType"
													class="form-control  "  >
														<dd:options codeType="AgroFnBreedType"/>
													</select>
													</div>
												</div>
											</div>
											<div class="ie8">
												<label class="col-sm-2 control-label no-padding-right" for="form-field-1"> 
												<font color="red">*</font>养殖内容： 
												 </label>
												<div class="col-sm-4">
													<div class="clearfix">
													<input type="text"  name="breedContent"
													class="form-control"  aria-required="true"/>
													</div>
												</div>
											</div>
									</div>
									
									<div class="form-group">
											<div class="ie8">
												<label class="col-sm-2 control-label no-padding-right" for="form-field-1"> 
												<font color="red">*</font>养殖方式：
												 </label>
												<div class="col-sm-4">
													<div class="clearfix">
														<input type="text" name="breedMode"
														class="form-control" aria-required="true"/>
													</div>
													
												</div>
											</div>
											<div class="ie8">
												<label class="col-sm-2 control-label no-padding-right" for="form-field-1"> 
												<font color="red">*</font>预计总产量： 
												 </label>
												<div class="col-sm-4">
													<div class="clearfix">
													<div class="input-group">
													<input type="text"  name="predictProduceTotal" readonly="readonly"
													class="form-control" style="width:70%"/>
													<input type="text" class="form-control" name="predictProduceTotalUnit" style="width:30%" placeholder="请输入单位"/>
													</div>
													</div>
												</div>
											</div>
									</div>
									<div class="form-group">
											<div class="ie8">
												<label class="col-sm-2 control-label no-padding-right" for="form-field-1"> 
												<font color="red">*</font>预测销售单价：
												 </label>
												<div class="col-sm-4">
													<div class="clearfix">
													<div class="input-group">
													<input type="text" name="salePrice" class="form-control" style="width:70%"/>
													<input type="text" class="form-control" name="salePriceUnit" style="width:30%" placeholder="请输入单位"/>
													</div>
													</div>
												</div>
											</div>
											 <div class="ie8">
												<label class="col-sm-2 control-label no-padding-right" for="form-field-1"> 
												<font color="red">*</font>年收入合计： 
												 </label>
												<div class="col-sm-4">
													<div class="clearfix">
													<input type="text"  name="saleIncomeTotal"
													class="form-control  " readonly="readonly" />
													</div>
												</div>
											</div>
									</div>
									<div class="form-group">
											<div class="ie8">
												<label class="col-sm-2 control-label no-padding-right" for="form-field-1"> 
												<font color="red">*</font>预计成本合计：
												 </label>
												<div class="col-sm-4">
													<div class="clearfix">
													<input type="text" name="costTotal"
													class="form-control  "  />
													</div>
												</div>
											</div>
										    <div class="ie8">
												<label class="col-sm-2 control-label no-padding-right" for="form-field-1"> 
												<font color="red">*</font>预计销售时间： 
												 </label>
												<div class="col-sm-4">
													<div class="clearfix">
													<span class="block input-icon input-icon-right">
														<input name="predictSaleTime" id="predictSaleTime" type="text" class="form-control formdate" />
													</span>
													</div>
												</div>
											</div>
										</div>
										</form>
										<!-- 添加养殖业信息结束 -->
									</div>
								</div>

								<div class="step-pane model-step2" id="breed-modal-step2">
									<div class="center">
										<!-- 添加收入信息开始 -->
										<div class="widget-box ui-sortable-handle" style="opacity: 1; z-index: 0;">
											<div class="widget-header">
												<h4 class="blue widget-title">养殖收入</h4>
												<div class="widget-toolbar">
													<button class="btn btn-xs btn-success" type="button" id="btn-addEcfIncome-breed" title="新增">
														<i class="ace-icon fa fa-plus"></i>
													</button>
												</div>
											</div>
											
											<div class="widget-body">
												<form id="income_form_breed" action="" method="post">
												<input type="hidden" name="objId"/>
												<input type="hidden" name="objType" value="02"/>
													<table id="income_tb_breed" data-flag="1" class="table table-striped table-hover income_tb"
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

								<div class="step-pane" id="breed-modal-step3">
									<div class="center">
										<!-- 添加支出信息开始 -->
											<div class="widget-box ui-sortable-handle" style="opacity: 1; z-index: 0;">
											<div class="widget-header">
												<h4 class="blue widget-title">养殖支出</h4>
												<div class="widget-toolbar">
													<button class="btn btn-xs btn-success" type="button" id="btn-addEcfConsume-breed" title="新增">
														<i class="ace-icon fa fa-plus"></i>
													</button>
												</div>
											</div>
											
											<div class="widget-body">
												<table id="out_tb_breed"  data-flag="2" class="table table-striped table-hover out_tb"
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
								<button id="submit-n-breed_future" class="btn btn-sm btn-primary"
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
