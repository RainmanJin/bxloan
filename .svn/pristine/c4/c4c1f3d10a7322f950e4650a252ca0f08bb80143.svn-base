<!-- 抵质押担保 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../commons/taglibs.jsp"%>
<div id="projectPawnInfoTableDiv" style="display: block;">
	<table id="projectPawnInfoTable"
		class="table table-striped table-hover">
		<thead>
			<tr>
				<th>抵质押物名称</th>
				<th>抵质押人名称</th>
				<th>价值评估</th>
				<th>币种</th>
				<th>本次申请担保债权金额</th>
				<th>已设定担保额</th>
				<th>担保率（%）</th>
				<th>抵质押类型</th>
				<th>状态</th>
				<th>操作</th>
			</tr>
		</thead>
	</table>
</div>
<div id="collateralTableDiv" style="display: none;">
	<div class="row">
		<div class="col-md-12">
			<form class="form-inline">
				<div class="form-group">
					<label class="sr-only" for="guarantorName"></label> 抵质押人名称：<input
						type="text" class="form-control" id="guarantorName">　
				</div>

				<div class="form-group">
					<label class="sr-only" for="guarantyName"></label> 抵质押品名称：<input
						type="text" class="form-control" id="guarantyName">　
				</div>

				<div class="form-group">
					<label class="sr-only" for="guarantyStatusCd"></label> 抵质押物状态： <select
						id="guarantyStatusCd" style="width: 162px;height: 34px;">
						<dd:options codeType="RevGuaranteeStatusCd" />
					</select>　
				</div>
				
				<div class="form-group">
					<label class="sr-only" for="guaranteeType"></label> 担保类型： <select
						id="guaranteeType" style="width: 162px;height: 34px;">
						<dd:options codeType="MortgageType" />
					</select>　
				</div>

				<button type="button" class="btn btn-sm btn-purple"
					id="searchCollateral">
					<i class="ace-icon fa fa-search"></i>查询
				</button>

				<button type="button" class="btn btn-sm btn-warning"
					id="resetCollateral">
					<i class="ace-icon fa fa-undo"></i>重置
				</button>

				<button type="button" class="btn btn-sm btn-success"
					id="addCollateral">
					<i class="ace-icon fa fa-plus"></i> 新增
				</button>
			</form>

			<div class="row">
				<div class="col-md-12">
					<table id="table" class="table table-striped table-hover">
						<thead>
							<tr>
								<th></th>
								<th>抵质押物编号</th>
								<th>抵质押人名称</th>
								<th>抵质押物名称</th>
								<th>抵质押物类型</th>
								<th>评估价值</th>
								<th>已设定担保金额</th>
								<th>抵质押物状态</th>
								<th>担保类型</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>

			<hr>

			<div class="row">
				<div class="col-md-6 col-md-offset-5">
					<button id="openPawnSureModal" type="button"
						class="btn btn-sm btn-primary">
						<i class="ace-icon fa fa-floppy-o"></i> 确定
					</button>
					<button id="addPawnBack" type="button"
						class="btn btn-warning btn-sm">
						<i class="ace-icon fa fa-arrow-left"></i> 返回
					</button>
				</div>
			</div>

			<div id="pawnModal" class="modal fade" data-backdrop="static"
				tabindex="-1">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<h4 class="modal-title">基本表单</h4>
						</div>
						<form id="pawnForm" class="form-horizontal">
							<div class="modal-body">
								<div class="row">
									<div class="col-xs-12">
										<div class="form-group">
											<label class="col-sm-5 control-label no-padding-right"
												for="evalValue"> <font color='red'> * </font> 评估价值：
											</label>
											<div class="col-sm-6">
												<span class="block input-icon input-icon-right"> <input
													type="text" class="form-control" id="evalValue"
													name="evalValue" readonly="readonly" /> </span>
											</div>
											<div class="help-block col-xs-12 col-sm-reset inline"></div>
										</div>

										<div class="form-group">
											<label class="col-sm-5 control-label no-padding-right"
												for="setGuarantyAmt"> <font color='red'> * </font>
												已设定担保金额（元）： </label>
											<div class="col-sm-6">
												<span class="block input-icon input-icon-right"> <input
													type="text" class="form-control" id="setGuarantyAmt"
													name="setGuarantyAmt" readonly="readonly" /> </span>
											</div>
											<div class="help-block col-xs-12 col-sm-reset inline"></div>
										</div>

										<div class="form-group">
											<label class="col-sm-5 control-label no-padding-right"
												for="appGuaDebtInterAmt"> <font color='red'>
													* </font> 本次申请担保债权金额（元）： </label>
											<div class="col-sm-6">
												<span class="block input-icon input-icon-right"> <input
													type="text" class="form-control required isPositiveNumberTwo"
													id="appGuaDebtInterAmt" name="appGuaDebtInterAmt" /> </span>
											</div>
											<div class="help-block col-xs-12 col-sm-reset inline"></div>
										</div>

										<div class="form-group">
											<label class="col-sm-5 control-label no-padding-right"
												for="addCustomerName"> <font color='red'> * </font>
												担保率（%）： </label>
											<div class="col-sm-6">
												<span class="block input-icon input-icon-right"> <input
													type="text" class="form-control"
													id="pawnRatio" name="pawnRatio" readonly="readonly" /> </span>
											</div>
											<div class="help-block col-xs-12 col-sm-reset inline"></div>
										</div>
									</div>
								</div>

								<input type="hidden" id="guarantyId" name="guarantyId">
							</div>
							<div class="modal-footer">
								<button type="submit" class="btn btn-primary">确定</button>
								<button type="button" class="btn btn-warning"
									data-dismiss="modal">取消</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<div id="addEditDetailDiv" class="modal" data-backdrop="static"
	tabindex="-1">
	<div class="modal-dialog modal-lg" style="">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title">抵质押信息</h4>
			</div>

			<div class="modal-body" style="height: 100%;">
				<div class="row">
					<div class="col-md-12">
						<form id="form_addEditDetailDiv" class="form-horizontal">
							<h6 class="blue">抵质押信息</h6>
							<div class="form-group">
								<div class="row">
									<div class="col-md-6">
										<label class="col-sm-4 control-label"
											for="guarantorName_addEditDetailDiv"> <font
											color="red">* </font> 抵质押人名称： </label>
										<div class="col-sm-8">
											<input type="text" class="form-control required"
												id="guarantorName_addEditDetailDiv" name="guarantorName">
										</div>
									</div>

									<div class="col-md-6" style=" ">
										<label class="col-sm-4 control-label" for="guarantyNum">
											抵质押物编号： </label>
										<div class="col-sm-8">
											<input type="text" class="form-control" id="guarantyNum"
												name="guarantyNum" readonly="readonly">
										</div>
									</div>
								</div>
							</div>

							<div class="form-group">
								<div class="row">
									<div class="col-md-6">
										<label class="col-sm-4 control-label" for="guarantorTypeCd">
											<font color="red">* </font> 抵质押人证件类型： </label>
										<div class="col-sm-8">
											<select class="form-control required" id="guarantorTypeCd"
												name="guarantorTypeCd" style="width: 100%;height: 34px;">
												<dd:options codeType="CertificateType" />
											</select>
										</div>
									</div>

									<div class="col-md-6" style=" ">
										<label class="col-sm-4 control-label"
											for="guarantorCertificateNum"> <font color="red">*
										</font> 抵质押人证件号码： </label>
										<div class="col-sm-8">
											<input type="text" class="form-control required"
												id="guarantorCertificateNum" name="guarantorCertificateNum">
										</div>
									</div>
								</div>
							</div>

							<div class="form-group">
								<div class="row">
									<div class="col-md-6">
										<label class="col-sm-4 control-label" for="guarantyName">
											<font color="red">* </font> 抵质押物名称： </label>
										<div class="col-sm-8">
											<input type="text" class="form-control required"
												id="guarantyName" name="guarantyName">
										</div>
									</div>

									<div class="col-md-6" style=" ">
										<label class="col-sm-4 control-label" for="guarantyTypeCd">
											<font color="red">* </font> 抵质押物类型： </label>
										<div class="col-sm-8">
											<select class="form-control required" id="guarantyTypeCd"
												name="guarantyTypeCd" style="width: 100%;height: 34px;">
												<dd:options codeType="ResvAssetTypeCode" />
											</select>
										</div>
									</div>
								</div>
							</div>

							<div class="form-group">
								<div class="row">
									<div class="col-md-6">
										<label class="col-sm-4 control-label"
											for="rightCertificationNum"> <font color="red">*
										</font> 权利证明编号： </label>
										<div class="col-sm-8">
											<input type="text" class="form-control required"
												id="rightCertificationNum" name="rightCertificationNum">
										</div>
									</div>

									<div class="col-md-6" style=" ">
										<label class="col-sm-4 control-label" for="rightCertTypeCd">
											<font color="red">* </font> 权利证明类型： </label>
										<div class="col-sm-8">
											<select class="form-control required" id="rightCertTypeCd"
												name="rightCertTypeCd" style="width: 100%;height: 34px;">
												<dd:options codeType="GuarantyCertificate" />
											</select>
										</div>
									</div>
								</div>
							</div>

							<div class="form-group">
								<div class="row">
									<div class="col-md-6">
										<label class="col-sm-4 control-label"
											for="acquireWayOfGuarantyCd"> 抵质押物取得途径： </label>
										<div class="col-sm-8">
											<select id="acquireWayOfGuarantyCd"
												name="acquireWayOfGuarantyCd"
												style="width: 100%;height: 34px;">
												<dd:options codeType="LandAcquiring" />
											</select>
										</div>
									</div>

									<div class="col-md-6" style=" ">
										<label class="col-sm-4 control-label" for="sysUpdateDate">
											系统更新时间： </label>
										<div class="col-sm-8">
											<input type="text" class="form-control" id="sysUpdateDate"
												name="sysUpdateDate" readonly="readonly"
												data-date-format="yyyy-mm-dd">
										</div>
									</div>
								</div>
							</div>

							<h6 class="blue">价值信息</h6>
							<div class="form-group">
								<div class="row">
									<div class="col-md-6">
										<label class="col-sm-4 control-label" for="marketValue">
											<font color="red">* </font> 市场价值（元）： </label>
										<div class="col-sm-8">
											<input type="text" class="form-control required isNumber"
												id="marketValue" name="marketValue">
										</div>
									</div>

									<div class="col-md-6" style=" ">
										<label class="col-sm-4 control-label" for="evalValue">
											<font color="red">* </font> 评估价值（元）： </label>
										<div class="col-sm-8">
											<input type="text" class="form-control required isNumber"
												id="evalValue" name="evalValue">
										</div>
									</div>
								</div>
							</div>

							<div class="form-group">
								<div class="row">
									<div class="col-md-6">
										<label class="col-sm-4 control-label" for="commonAssetsInd">
											<font color="red">* </font> 是否共同财产： </label>
										<div class="col-sm-8">
											<input type="hidden" id="commonAssetsInd"> <label
												class="radio-inline"> <input type="radio"
												id="commonAssetsInd1" name="commonAssetsInd" value="1"
												checked="checked" class='ace add_corp_Type form-control'><span
												class='lbl'>是</span> </label> <label class="radio-inline"> <input
												type="radio" id="commonAssetsInd2" name="commonAssetsInd"
												value="2" class='ace add_corp_Type form-control'><span
												class='lbl'>否</span> </label>
										</div>
									</div>

									<div class="col-md-6" style=" ">
										<label class="col-sm-4 control-label" for="guarantySetupInd">
											<font color="red">* </font> 是否已设定抵质押： </label>
										<div class="col-sm-8">
											<input type="hidden" id="guarantySetupInd"> <label
												class="radio-inline"> <input type="radio"
												id="guarantySetupInd1" name="guarantySetupInd" value="1"
												checked="checked" class='ace add_corp_Type form-control'><span
												class='lbl'>是</span> </label> <label class="radio-inline"> <input
												type="radio" id="guarantySetupInd2" name="guarantySetupInd"
												value="2" class='ace add_corp_Type form-control'><span
												class='lbl'>否</span> </label>
										</div>
									</div>
								</div>
							</div>

							<div class="form-group">
								<div class="row">
									<div class="col-md-6">
										<label class="col-sm-4 control-label" for="partOwnerName">
											共有人名称： </label>
										<div class="col-sm-8">
											<input type="text" class="form-control" id="partOwnerName"
												name="partOwnerName">
										</div>
									</div>

									<div class="col-md-6" style=" ">
										<label class="col-sm-4 control-label" for="setGuarantyAmt">
											已设定担保额： </label>
										<div class="col-sm-8">
											<input type="text" class="form-control isNumber"
												id="setGuarantyAmt" name="setGuarantyAmt">
										</div>
									</div>
								</div>
							</div>

							<div class="form-group">
								<div class="row">
									<div class="col-md-6">
										<label class="col-sm-4 control-label" for="otherNote">
											其他说明： </label>
										<div class="col-sm-8">
											<textarea class="form-control" rows="3" id="otherNote"
												name="otherNote"></textarea>
										</div>
									</div>
								</div>
							</div>

							<div id="house" style="display: none;">
								<h6 class="blue">房产详细信息</h6>

								<div class="form-group">
									<div class="row">
										<div class="col-md-6">
											<label class="col-sm-4 control-label" for="constructedDate">
												<font color="red">* </font> 建成日期： </label>
											<div class="col-sm-8">
												<input type="text" class="form-control" id="constructedDate"
													name="constructedDate" data-date-format="yyyy-mm-dd">
											</div>
										</div>

										<div class="col-md-6" style=" ">
											<label class="col-sm-4 control-label" for="houseLicenseOwner">
												<font color="red">* </font> 房产权利人名称： </label>
											<div class="col-sm-8">
												<input type="text" class="form-control"
													id="houseLicenseOwner" name="houseLicenseOwner">
											</div>
										</div>
									</div>
								</div>

								<div class="form-group">
									<div class="row">
										<div class="col-md-6">
											<label class="col-sm-4 control-label" for="buildAllArea">
												<font color="red">* </font> 总建筑面积（平方米）： </label>
											<div class="col-sm-8">
												<input type="text" class="form-control" id="buildAllArea"
													name="buildAllArea">
											</div>
										</div>

										<div class="col-md-6" style=" ">
											<label class="col-sm-4 control-label" for="propertyTerm">
												<font color="red">* </font> 产权年限（年）： </label>
											<div class="col-sm-8">
												<input type="text" class="form-control" id="propertyTerm"
													name="propertyTerm">
											</div>
										</div>
									</div>
								</div>

								<div class="form-group">
									<div class="row">
										<div class="col-md-6">
											<label class="col-sm-4 control-label"
												for="landAcquiringWayCd"> <font color="red">*
											</font> 土地取得方式： </label>
											<div class="col-sm-8">
												<select class="form-control" id="landAcquiringWayCd"
													name="landAcquiringWayCd" style="width: 100%;height: 34px;">
													<dd:options codeType="landAcquiringWayCd" />
												</select>
											</div>
										</div>

										<div class="col-md-6" style=" ">
											<label class="col-sm-4 control-label" for="utilizationType">
												<font color="red">* </font> 使用类型： </label>
											<div class="col-sm-8">
												<select class="form-control" id="utilizationType"
													name="utilizationType" style="width: 100%;height: 34px;">
													<dd:options codeType="LandUtilization" />
												</select>
											</div>
										</div>
									</div>
								</div>

								<div class="form-group">
									<div class="row">
										<div class="col-md-6">
											<label class="col-sm-4 control-label"
												for="houseQuitclaimCode"> <font color="red">*
											</font> 房屋产权证号： </label>
											<div class="col-sm-8">
												<input type="text" class="form-control"
													id="houseQuitclaimCode" name="houseQuitclaimCode">
											</div>
										</div>

										<div class="col-md-6" style=" ">
											<label class="col-sm-4 control-label" for="landArea">
												<font color="red">* </font> 使用面积（平方米）： </label>
											<div class="col-sm-8">
												<input type="text" class="form-control" id="landArea"
													name="landArea">
											</div>
										</div>
									</div>
								</div>

								<div class="form-group">
									<div class="row">
										<div class="col-md-6">
											<label class="col-sm-4 control-label" for="totleFloor">
												<font color="red">* </font> 总层数： </label>
											<div class="col-sm-8">
												<input type="text" class="form-control" id="totleFloor"
													name="totleFloor">
											</div>
										</div>
									</div>
								</div>
							</div>

							<div id="land" style="display: none;">
								<h6 class="blue">地产详细信息</h6>

								<div class="form-group">
									<div class="row">
										<div class="col-md-6">
											<label class="col-sm-4 control-label" for="abstractEmissarg">
												<font color="red">* </font> 产权、使用权人： </label>
											<div class="col-sm-8">
												<input type="text" class="form-control"
													id="abstractEmissarg" name="abstractEmissarg">
											</div>
										</div>

										<div class="col-md-6" style=" ">
											<label class="col-sm-4 control-label" for="terraNumber">
												<font color="red">* </font> 地号： </label>
											<div class="col-sm-8">
												<input type="text" class="form-control" id="terraNumber"
													name="terraNumber">
											</div>
										</div>
									</div>
								</div>

								<div class="form-group">
									<div class="row">
										<div class="col-md-6">
											<label class="col-sm-4 control-label" for="landEsplees">
												<font color="red">* </font> 土地使用权证号： </label>
											<div class="col-sm-8">
												<input type="text" class="form-control" id="landEsplees"
													name="landEsplees">
											</div>
										</div>

										<div class="col-md-6" style=" ">
											<label class="col-sm-4 control-label" for="accessType">
												<font color="red">* </font> 使用权类型： </label>
											<div class="col-sm-8">
												<input type="text" class="form-control" id="accessType"
													name="accessType">
											</div>
										</div>
									</div>
								</div>

								<div class="form-group">
									<div class="row">
										<div class="col-md-6">
											<label class="col-sm-4 control-label" for="particularArea">
												独有面积（平方米）： </label>
											<div class="col-sm-8">
												<input type="text" class="form-control" id="particularArea"
													name="particularArea">
											</div>
										</div>

										<div class="col-md-6" style=" ">
											<label class="col-sm-4 control-label" for="landSit">
												<font color="red">* </font> 坐落： </label>
											<div class="col-sm-8">
												<input type="text" class="form-control" id="landSit"
													name="landSit">
											</div>
										</div>
									</div>
								</div>

								<div class="form-group">
									<div class="row">
										<div class="col-md-6">
											<label class="col-sm-4 control-label" for="geographyPurpose">
												<font color="red">* </font> 地类（用途）： </label>
											<div class="col-sm-8">
												<input type="text" class="form-control"
													id="geographyPurpose" name="geographyPurpose">
											</div>
										</div>

										<div class="col-md-6" style=" ">
											<label class="col-sm-4 control-label" for="landEndDate">
												<font color="red">* </font> 土地终止日期： </label>
											<div class="col-sm-8">
												<input type="text" class="form-control" id="landEndDate"
													data-date-format="yyyy-mm-dd" name="landEndDate">
											</div>
										</div>
									</div>
								</div>

								<div class="form-group">
									<div class="row">
										<div class="col-md-6">
											<label class="col-sm-4 control-label" for="landUseArea">
												<font color="red">* </font> 使用权面积（平方米）： </label>
											<div class="col-sm-8">
												<input type="text" class="form-control" id="landUseArea"
													name="landUseArea">
											</div>
										</div>

										<div class="col-md-6" style=" ">
											<label class="col-sm-4 control-label" for="apportionArea">
												分摊面积（平方米）： </label>
											<div class="col-sm-8">
												<input type="text" class="form-control" id="apportionArea"
													name="apportionArea">
											</div>
										</div>
									</div>
								</div>
							</div>

							<div id="machine" style="display: none;">
								<h6 class="blue">机械设备详细信息</h6>

								<div class="form-group">
									<div class="row">
										<div class="col-md-6">
											<label class="col-sm-4 control-label" for="modelType">
												<font color="red">* </font> 规格型号： </label>
											<div class="col-sm-8">
												<input type="text" class="form-control" id="modelType"
													name="modelType">
											</div>
										</div>

										<div class="col-md-6" style=" ">
											<label class="col-sm-4 control-label" for="equipMount">
												数量： </label>
											<div class="col-sm-8">
												<input type="text" class="form-control" id="equipMount"
													name="equipMount">
											</div>
										</div>
									</div>
								</div>

								<div class="form-group">
									<div class="row">
										<div class="col-md-6">
											<label class="col-sm-4 control-label" for="safeCheckInd">
												<font color="red">* </font> 有无安全检查证明： </label>
											<div class="col-sm-8">
												<input type="text" class="form-control" id="safeCheckInd"
													name="safeCheckInd">
											</div>
										</div>

										<div class="col-md-6" style=" ">
											<label class="col-sm-4 control-label" for="deviceUseLife">
												<font color="red">* </font> 设备使用寿命： </label>
											<div class="col-sm-8">
												<input type="text" class="form-control" id="deviceUseLife"
													name="deviceUseLife">
											</div>
										</div>
									</div>
								</div>

								<div class="form-group">
									<div class="row">
										<div class="col-md-6">
											<label class="col-sm-4 control-label" for="fireCheckInd">
												<font color="red">* </font> 有无消防检查证明： </label>
											<div class="col-sm-8">
												<input type="text" class="form-control" id="fireCheckInd"
													name="fireCheckInd">
											</div>
										</div>

										<div class="col-md-6" style=" ">
											<label class="col-sm-4 control-label" for="eliLicenseInd">
												<font color="red">* </font> 有无产品合格证： </label>
											<div class="col-sm-8">
												<input type="text" class="form-control" id="eliLicenseInd"
													name="eliLicenseInd">
											</div>
										</div>
									</div>
								</div>

								<div class="form-group">
									<div class="row">
										<div class="col-md-6">
											<label class="col-sm-4 control-label" for="utilizedYears">
												<font color="red">* </font> 已使用年限： </label>
											<div class="col-sm-8">
												<input type="text" class="form-control" id="utilizedYears"
													name="utilizedYears">
											</div>
										</div>

										<div class="col-md-6" style=" ">
											<label class="col-sm-4 control-label" for="oriPurPrice">
												<font color="red">* </font> 原购置价： </label>
											<div class="col-sm-8">
												<input type="text" class="form-control" id="oriPurPrice"
													name="oriPurPrice">
											</div>
										</div>
									</div>
								</div>

								<div class="form-group">
									<div class="row">
										<div class="col-md-6">
											<label class="col-sm-4 control-label" for="purpose">
												<font color="red">* </font> 具体用途： </label>
											<div class="col-sm-8">
												<textarea class="form-control" rows="3" id="purpose"
													name="purpose"></textarea>
											</div>
										</div>
									</div>
								</div>
							</div>

							<div id="vehicle" style="display: none;">
								<h6 class="blue">机动车详细信息</h6>

								<div class="form-group">
									<div class="row">
										<div class="col-md-6">
											<label class="col-sm-4 control-label" for="registerDate">
												<font color="red">* </font> 购置日期： </label>
											<div class="col-sm-8">
												<input type="text" class="form-control" id="registerDate"
													name="registerDate" data-date-format="yyyy-mm-dd">
											</div>
										</div>

										<div class="col-md-6" style=" ">
											<label class="col-sm-4 control-label" for="modelStyle">
												<font color="red">* </font> 车辆型号： </label>
											<div class="col-sm-8">
												<input type="text" class="form-control" id="modelStyle"
													name="modelStyle">
											</div>
										</div>
									</div>
								</div>

								<div class="form-group">
									<div class="row">
										<div class="col-md-6">
											<label class="col-sm-4 control-label" for="launchPlaneNumber">
												<font color="red">* </font> 发动机号： </label>
											<div class="col-sm-8">
												<input type="text" class="form-control"
													id="launchPlaneNumber" name="launchPlaneNumber">
											</div>
										</div>

										<div class="col-md-6" style=" ">
											<label class="col-sm-4 control-label" for="machineSteamCard">
												<font color="red">* </font> 机动车行驶证号： </label>
											<div class="col-sm-8">
												<input type="text" class="form-control"
													id="machineSteamCard" name="machineSteamCard">
											</div>
										</div>
									</div>
								</div>

								<div class="form-group">
									<div class="row">
										<div class="col-md-6">
											<label class="col-sm-4 control-label" for="frameNumber">
												<font color="red">* </font> 车架号： </label>
											<div class="col-sm-8">
												<input type="text" class="form-control" id="frameNumber"
													name="frameNumber">
											</div>
										</div>

										<div class="col-md-6" style=" ">
											<label class="col-sm-4 control-label" for="vehicleNumber">
												<font color="red">* </font> 车牌号： </label>
											<div class="col-sm-8">
												<input type="text" class="form-control" id="vehicleNumber"
													name="vehicleNumber">
											</div>
										</div>
									</div>
								</div>

								<div class="form-group">
									<div class="row">
										<div class="col-md-6"> 
											<label class="col-sm-4 control-label" for="machineEnrol">
												<font color="red">* </font> 机动车登记证号： </label>
											<div class="col-sm-8">
												<input type="text" class="form-control" id="machineEnrol"
													name="machineEnrol">
											</div>
										</div>

										<div class="col-md-6" style=" ">
											<label class="col-sm-4 control-label" for="runnedKm">
												<font color="red">* </font> 里程数（公里）： </label>
											<div class="col-sm-8">
												<input type="text" class="form-control" id="runnedKm"
													name="runnedKm">
											</div>
										</div>
									</div>
								</div>

								<div class="form-group">
									<div class="row">
										<div class="col-md-6">
											<label class="col-sm-4 control-label" for="troubleRecords">
												<font color="red">* </font> 事故记录： </label>
											<div class="col-sm-8">
												<textarea class="form-control" rows="3" id="troubleRecords"
													name="troubleRecords"></textarea>
											</div>
										</div>
									</div>
								</div>
							</div>

						</form>

					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<div id="collateralDetailDiv" class="modal" data-backdrop="static"
	tabindex="-1">
	<div class="modal-dialog modal-lg" style="width: 1100px;">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title">新增抵质押</h4>
			</div>
			<div class="modal-body">
				<iframe id="collateralIframe" width="100%" height="100%"
					style="border: 0px;"></iframe>
			</div>
		</div>
	</div>
</div>