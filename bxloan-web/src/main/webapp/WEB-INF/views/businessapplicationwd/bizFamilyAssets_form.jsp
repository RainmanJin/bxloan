<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../commons/taglibs.jsp"%>
<!-- ---------------固定资产---------------- -->
<div id="modal-fixedAssetOfBizFa" class="modal" data-backdrop="static" tabindex="-1">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<form id="form-fixedAssetOfBizFa" class="form-horizontal"
				role="form" method="post"  onsubmit="return false;">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="blue bigger" role="form-title"><i class="ace-icon fa fa-plus"></i>&nbsp;新增</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-xs-12">
							<!--隐藏框区分   save和update -->
							<input type="hidden" name="id" />
									<!-- ---------------start1----------------- -->								
										<div class="form-group">
											<label
												class="col-xs-12 col-sm-3 control-label no-padding-right">
												<font color="red">*</font>类别：
											</label>
											<div class="col-xs-12 col-sm-3">
												<span class="block input-icon input-icon-right"> <select
													name="assetType"class="form-control">
														<!-- <option value="1">房产</option>
														<option value="2">交通工具</option>
														<option value="3">机械设备</option>
														<option value="4">农用设施</option>
														<option value="5">表外资产</option>
														<option value="6">其他设备</option> -->
												</select>
												</span>
											</div>
											<div class="help-block col-xs-12 col-sm-reset inline"></div>
										</div>
										<div class="form-group">
											<label
												class="col-xs-12 col-sm-3 control-label no-padding-right">
												<font color="red">*</font>品名：
											</label>
											<div class="col-xs-12 col-sm-3">
												<span class="block input-icon input-icon-right"> <select
													name="assetNameCode" class="form-control">
														<!-- <option value="1">住房</option>
														<option value="2">畜禽圈舍</option>
														<option value="3">经营用房</option>
														<option value="4">轿车</option>
														<option value="5">摩托</option>
														<option value="6">小货车</option>
														<option value="0">其他</option> -->
												</select>
												</span>
											</div>
											<div class="help-block col-xs-12 col-sm-reset inline"></div>
											<label
												class="col-xs-12 col-sm-3 control-label no-padding-right">
												<font color="red">*</font>名称：
											</label>
											<div class="col-xs-12 col-sm-3">
												<span class="block input-icon input-icon-right">
												<input type="text" name="assetName" class="width-100">
												</span>
											</div>
											<div class="help-block col-xs-12 col-sm-reset inline"></div>
										</div>

										<div class="form-group">
											<label
												class="col-xs-12 col-sm-3 control-label no-padding-right">
												<font color="red">*</font>估计现价/金额(元)：
											</label>
											<div class="col-xs-12 col-sm-3">
												<span class="block input-icon input-icon-right"> <input
													type="text" name="estimatedPrice"
													class="width-100" />
												</span> 
											</div>
											<div class="help-block col-xs-12 col-sm-reset inline"></div>
											<label
												class="col-xs-12 col-sm-3 control-label no-padding-right">
												<font color="red">*</font>原始购置价值(元)：
											</label>
											<div class="col-xs-12 col-sm-3">
												<span class="block input-icon input-icon-right"> <input
													type="text" name="originalAcquisitionPrice"
													class="width-100" />
												</span>
											</div>
											<div class="help-block col-xs-12 col-sm-reset inline"></div>
										</div>

										<div class="form-group">
											<label
												class="col-xs-12 col-sm-3 control-label no-padding-right">
												<font color="red">*</font>面积/数量：
											</label>
											<div class="col-xs-12 col-sm-3">
												<span class="block input-icon input-icon-right"><input
													type="text" name="sizeOrQuantity"
													class="width-100" /> </span>
											</div>
											<div class="help-block col-xs-12 col-sm-reset inline"></div>
											<label
												class="col-xs-12 col-sm-3 control-label no-padding-right">
												<font color="red">*</font>购买/建造年份：
											</label>
											<div class="col-xs-12 col-sm-3">
												<span class="block input-icon input-icon-right"> <input
													type="text" name="buyOrBuildYearStr"
													class="input-datepicker-year width-100" readonly="readonly"/>
												</span>
											</div>
											<div class="help-block col-xs-12 col-sm-reset inline"></div>
										</div>
										<div role="select-structure" class="form-group">
											<label
												class="col-xs-12 col-sm-3 control-label no-padding-right">
												<font color="red">*</font>结构：
											</label>
											<div class="col-xs-12 col-sm-3">
												<span class="block input-icon input-icon-right">
												<select name="structure" class="form-control">
														<dd:options codeType="MyFixedAssetHousingStructure"/>
												</select>
												</span>
											</div>
											<div class="help-block col-xs-12 col-sm-reset inline"></div>
										</div>
										<div class="form-group">
											<label class="col-sm-3 control-label no-padding-right"
												for="description"> <font color='red'>*</font>描述：
											</label>
											<div class="col-sm-7">
												<textarea name="description" rows="2" cols="54"></textarea>
											</div>
										</div>						
									<!-- --------------end------------------- -->						
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button role="submit" type="submit" class="btn btn-sm btn-primary" data-loading-text="保存中...">
						<i class="ace-icon fa fa-save"></i> 保存
					</button>
					<button class="btn btn-warning btn-sm" data-dismiss="modal" type="button">
						<i class="ace-icon fa fa-times"></i>取消
					</button>
				</div>
			</form>
		</div>
	</div>
</div>

<!-- ---------------生产区域-------------------- -->
<div id="modal-productAreaOfBizFa" class="modal" data-backdrop="static"
	tabindex="-1">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<form id="form-productionAreaOfBizFa" class="form-horizontal" 
			method="post" onsubmit="return false;">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="blue bigger" role="form-title"><i class="ace-icon fa fa-plus"></i>&nbsp;新增</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-xs-12">
							<!--隐藏框区分   save和update -->
							<input type="hidden" name="id" />
							<div class="row">
								<div class="form-group col-md-6">
									<label class="col-xs-12 col-sm-4 control-label no-padding-right">
										<font color="red">*</font>地点：
									</label>
									<div class="col-xs-12 col-sm-8">
										<span class="block input-icon input-icon-right">
										<input type="text" name="location" class="form-control" />
										</span>
									</div>
									<div class="help-block col-xs-12 col-sm-reset inline"></div>
								</div>
								<div class="form-group col-md-6">
									<label
										class="col-xs-12 col-sm-4 control-label no-padding-right">
										<font color="red">*</font>面积：
									</label>
									<div class="col-xs-12 col-sm-8">
										<div class="input-group">
											<input type="text"  name="area" class="form-control"/>
											<span class="input-group-addon">亩</span>
										</div>
									</div>
									<div class="help-block col-xs-12 col-sm-reset inline"></div>
								</div>
							</div>
							<div class="row">
								<div class="form-group col-md-6">
									<label
										class="col-xs-12 col-sm-4 control-label no-padding-right">
										<font color="red">*</font>生产区域性质：
									</label>
									<div class="col-xs-12 col-sm-8">
										<span class="block input-icon input-icon-right">
											<select name="areaProperty" class="form-control">
												<option value="">请选择</option>
												<dd:options codeType="MyRentType"/>
											</select>
										</span>
									</div>
									<div class="help-block col-xs-12 col-sm-reset inline"></div>
								</div>
								<div class="form-group col-md-6">
									<label class="col-xs-12 col-sm-4 control-label no-padding-right">
										<font color="red">*</font>未来12个月是否生产：
									</label>
									<div class="col-xs-12 col-sm-8">
										<span class="block input-icon input-icon-right">
											<%-- <dd:radio codeType="MyWillProduceFuture" cbName="willProduceFuture"/> --%>
										<select name="willProduceFuture" class="form-control">
												<dd:options codeType="MyWillProduceFuture"/>
										</select>
										</span>
									</div>
									<div class="help-block col-xs-12 col-sm-reset inline"></div>
								</div>
							</div>	
							<div class="row">
								<div class="form-group col-md-12">
								<label
									class="col-xs-12 col-sm-2 control-label no-padding-right">
									<font color="red">*</font>生产内容：
								</label>
								<div class="col-xs-12 col-sm-10" style="padding-right: 40px;">
									<span class="block input-icon input-icon-right">
									<textarea name="produceContent" class="form-control" rows="3"></textarea>
									</span>
								</div>
								<div class="help-block col-xs-12 col-sm-reset inline"></div>
								</div>
							</div>
							<div class="row">
								<div class="form-group col-md-6">
									<label class="col-xs-12 col-sm-4 control-label no-padding-right">
									种养类型：
									</label>
									<div class="col-xs-12 col-sm-8">
										<span class="block input-icon input-icon-right">
											<select name="cultivateBreedType" class="form-control">
													<option value="">请选择</option>
													<dd:options codeType="MyCultivateBreedType"/>
											</select>
										</span>
									</div>
									<div class="help-block col-xs-12 col-sm-reset inline"></div>
								</div>
								<div class="form-group col-md-6">
									<label class="col-xs-12 col-sm-4 control-label no-padding-right">
										设施情况：
									</label>
									<div class="col-xs-12 col-sm-8">
										<span class="block input-icon input-icon-right">
										<input type="text" name="equipmentSituation"
											class="form-control" />
											</span>
									</div>
									<div class="help-block col-xs-12 col-sm-reset inline"></div>
								</div>
							</div>
							<div class="row">
								<div class="form-group col-md-6">
									<label
										class="col-xs-12 col-sm-4 control-label no-padding-right">
										租期：
									</label>
									<div class="col-xs-12 col-sm-8">
										<div class="input-group">
										  <input type="text" class="form-control" name="tenancy">
										  <input type="hidden" class="form-control" name="tenancyUnit">
										 <!--  tenancy_unit -->
										  <div class="input-group-btn">
								            <button type="button" class="btn btn-default btn-sm"  role="tenancyUnit">单位</button>
								            <button role="btn-dropDown" type="button" class="btn btn-default btn-sm dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
								              <span class="caret"></span>
								              <span class="sr-only">下拉</span>
								            </button>
								            <ul class="dropdown-menu" role="menu">
								              <li data-value='1'><a href="javascript:void(0);">年</a></li>
								              <li data-value='2'><a href="javascript:void(0);">月</a></li>
								              <li data-value='3'><a href="javascript:void(0);">日</a></li>
								            </ul>
								          </div>
										</div>
									</div>
									<div class="help-block col-xs-12 col-sm-reset inline"></div>
								</div>
								<div class="form-group col-md-6">
									<label class="col-xs-12 col-sm-4 control-label no-padding-right">
									租金(元/年)：
									</label>
									<div class="col-xs-12 col-sm-8">
										<span class="block input-icon input-icon-right">
										<input type="text"  name="rent" class="form-control"/>
										</span>
									</div>
									<div class="help-block col-xs-12 col-sm-reset inline"></div>
								</div>
							</div>
							<div class="row">
								<div class="form-group col-md-6">
									<label class="col-xs-12 col-sm-4 control-label no-padding-right">
									租金支付时间：
									</label>
									<div class="col-xs-12 col-sm-8">
										<span class="block input-icon input-icon-right">
										<input type="text"  name="rentDateStr" class="form-control input-datepicker" />
										</span>
									</div>
									<div class="help-block col-xs-12 col-sm-reset inline"></div>
								</div>
								<div class="form-group col-md-6">
									<label class="col-xs-12 col-sm-4 control-label no-padding-right"
										for="addCustomerName">是否有租赁协议：
									</label>
									<div class="col-xs-12 col-sm-8">
										<span class="block input-icon input-icon-right">
											<dd:radio codeType="MyHaveLease" cbName="haveLease"/>
										</span>
									</div>
									<div class="help-block col-xs-12 col-sm-reset inline"></div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button role="submit" type="submit" class="btn btn-sm btn-primary" data-loading-text="保存中...">
						<i class="ace-icon fa fa-save"></i> 保存
					</button>
					<button class="btn btn-warning btn-sm" data-dismiss="modal" type="button">
						<i class="ace-icon fa fa-times"></i>取消
					</button>
				</div>
			</form>
		</div>
	</div>
</div>