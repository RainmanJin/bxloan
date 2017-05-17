<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../../commons/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<form id="form" class="form-horizontal">
			<input type="hidden" id="guarantyId" name="guarantyId"
				value="${collateral.guarantyId }"> <input type="hidden"
				id="isSelectOfGname" value="0">
			<h6 class="blue">抵质押信息</h6>
			<div class="row">
				<div class="form-group col-md-6">
					<label class="col-sm-5 control-label" for="guarantorName">
						<font color="red">* </font> 抵质押人名称： </label>
					<div class="col-sm-6">
						<div class="input-group">
							<input type="text" class="form-control" id="guarantorName"
								name="guarantorName" value="${collateral.guarantorName }">
							<span class="input-group-btn">
								<button id="showCustomerModal" class="btn btn-sm btn-yellow"
									type="button">
									<i class="ace-icon fa fa-eye"></i>
								</button> </span>
						</div>
					</div>
				</div>
				<div class="form-group col-md-6">
					<label class="col-sm-5 control-label" for="guarantyNum">
						抵质押物编号： </label>
					<div class="col-sm-6">
						<input type="text" class="form-control" id="guarantyNum"
							name="guarantyNum" readonly="readonly"
							value="${collateral.guarantyNum }">
					</div>
				</div>
			</div>
			<div class="row">
				<div class="form-group col-md-6">
					<label class="col-sm-5 control-label" for="guarantorTypeCd">
						<font color="red">* </font> 抵质押人证件类型： </label>
					<div class="col-sm-6">
						<select class="form-control" id="guarantorTypeCd"
							name="guarantorTypeCd">
							<dd:options codeType="CertificateType"
								selected="${collateral.guarantorTypeCd }" />
						</select>
					</div>
				</div>
				<div class="form-group col-md-6">
					<label class="col-sm-5 control-label" for="guarantorCertificateNum">
						<font color="red">* </font> 抵质押人证件号码： </label>
					<div class="col-sm-6">
						<input type="text" class="form-control"
							id="guarantorCertificateNum" name="guarantorCertificateNum"
							value="${collateral.guarantorCertificateNum }">
					</div>
				</div>
			</div>
			<div class="row">
				<div class="form-group col-md-6">
					<label class="col-sm-5 control-label" for="guarantyName"> <font
						color="red">* </font> 抵质押物名称： </label>
					<div class="col-sm-6">
						<input type="text" class="form-control" id="guarantyName"
							name="guarantyName" value="${collateral.guarantyName }">
					</div>
				</div>
				<div class="form-group col-md-6">
					<label class="col-sm-5 control-label" for="guarantyTypeCd">
						<font color="red">* </font> 抵质押物类型： </label>
					<div class="col-sm-6">
						<select class="form-control" id="guarantyTypeCd"
							name="guarantyTypeCd">
							<dd:options codeType="ResvAssetTypeCode"
								selected="${collateral.guarantyTypeCd }" />
						</select>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="form-group col-md-6">
					<label class="col-sm-5 control-label" for="rightCertificationNum">
						<font color="red">* </font> 权利证明编号： </label>
					<div class="col-sm-6">
						<input type="text" class="form-control" id="rightCertificationNum"
							name="rightCertificationNum"
							value="${collateral.rightCertificationNum }">
					</div>
				</div>
				<div class="form-group col-md-6">
					<label class="col-sm-5 control-label" for="rightCertTypeCd">
						<font color="red">* </font> 权利证明类型： </label>
					<div class="col-sm-6">
						<select class="form-control" id="rightCertTypeCd"
							name="rightCertTypeCd">
							<dd:options codeType="GuarantyCertificate"
								selected="${collateral.rightCertTypeCd }" />
						</select>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="form-group col-md-6">
					<label class="col-sm-5 control-label" for="acquireWayOfGuarantyCd">
						抵质押物取得途径： </label>
					<div class="col-sm-6">
						<select class="form-control" id="acquireWayOfGuarantyCd"
							name="acquireWayOfGuarantyCd">
							<dd:options codeType="LandAcquiring"
								selected="${collateral.acquireWayOfGuarantyCd }" />
						</select>
					</div>
				</div>
				<div class="form-group col-md-6">
					<label class="col-sm-5 control-label" for="sysUpdateDate">
						系统更新时间： </label>
					<div class="col-sm-6">
						<input type="text" class="form-control" id="sysUpdateDate"
							name="sysUpdateDate" readonly="readonly"
							data-date-format="yyyy-mm-dd"
							value="${collateral.sysUpdateDateStr }">
					</div>
				</div>
			</div>
			<!-- 价值信息 -->
			<h6 class="blue">价值信息</h6>
			<div class="row">
				<div class="form-group col-md-6">
					<label class="col-sm-5 control-label" for="marketValue"> <font
						color="red">* </font> 市场价值（元）： </label>
					<div class="col-sm-6">
						<input type="text" class="form-control isNumber" id="marketValue"
							name="marketValue" value="${collateral.marketValue }">
					</div>
				</div>

				<div class="form-group col-md-6">
					<label class="col-sm-5 control-label" for="evalValue"> <font
						color="red">* </font> 评估价值（元）： </label>
					<div class="col-sm-6">
						<input type="text" class="form-control isNumber" id="evalValue"
							name="evalValue" value="${collateral.evalValue }">
					</div>
				</div>
			</div>

			<div class="row">
				<div class="form-group col-md-6">
					<label class="col-sm-5 control-label" for="commonAssetsInd">
						<font color="red">* </font> 是否共同财产： </label>
					<div class="col-sm-6">
						<input type="hidden" id="commonAssetsInd"
							value="${collateral.commonAssetsInd }"> <label
							class="radio-inline"> <input type="radio"
							id="commonAssetsInd1" name="commonAssetsInd" value="1"
							checked="checked">是 </label> <label class="radio-inline">
							<input type="radio" id="commonAssetsInd2" name="commonAssetsInd"
							value="2">否 </label>
					</div>
				</div>
				<div class="form-group col-md-6">
					<label class="col-sm-5 control-label" for="guarantySetupInd">
						<font color="red">* </font> 是否已设定抵质押： </label>
					<div class="col-sm-6">
						<input type="hidden" id="guarantySetupInd"
							value="${collateral.guarantySetupInd }"> <label
							class="radio-inline"> <input type="radio"
							id="guarantySetupInd1" name="guarantySetupInd" value="1"
							checked="checked">是 </label> <label class="radio-inline">
							<input type="radio" id="guarantySetupInd2"
							name="guarantySetupInd" value="2">否 </label>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="form-group col-md-6">
					<label class="col-sm-5 control-label" for="partOwnerName">
						共有人名称： </label>
					<div class="col-sm-6">
						<input type="text" class="form-control" id="partOwnerName"
							name="partOwnerName" value="${collateral.partOwnerName }">
					</div>
				</div>
				<div class="form-group col-md-6">
					<label class="col-sm-5 control-label" for="setGuarantyAmt">
						已设定担保额： </label>
					<div class="col-sm-6">
						<input type="text" class="form-control isNumber"
							id="setGuarantyAmt" name="setGuarantyAmt"
							value="${collateral.setGuarantyAmt }">
					</div>
				</div>
			</div>

			<div class="row">
				<div class="form-group col-md-6">
					<label class="col-sm-5 control-label" for="otherNote">
						其他说明： </label>
					<div class="col-sm-6">
						<textarea class="form-control" rows="3" id="otherNote"
							name="otherNote">${collateral.otherNote }</textarea>
					</div>
				</div>
			</div>

			<div id="house" style="display: none;">
				<h6 class="blue">房产详细信息</h6>
				<div class="row">
					<div class="form-group col-md-6">
						<label class="col-sm-5 control-label" for="constructedDate">
							<font color="red">* </font> 建成日期： </label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="constructedDate"
								name="constructedDate" data-date-format="yyyy-mm-dd"
								value="${realEstateMortgage.constructedDateStr }">
						</div>
					</div>

					<div class="form-group col-md-6">
						<label class="col-sm-5 control-label" for="houseLicenseOwner">
							<font color="red">* </font> 房产权利人名称： </label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="houseLicenseOwner"
								name="houseLicenseOwner"
								value="${realEstateMortgage.houseLicenseOwner }">
						</div>
					</div>
				</div>

				<div class="row">
					<div class="form-group col-md-6">
						<label class="col-sm-5 control-label" for="buildAllArea">
							<font color="red">* </font> 总建筑面积（平方米）： </label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="buildAllArea"
								name="buildAllArea" value="${realEstateMortgage.buildAllArea }">
						</div>
					</div>

					<div class="form-group col-md-6">
						<label class="col-sm-5 control-label" for="propertyTerm">
							<font color="red">* </font> 产权年限（年）： </label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="propertyTerm"
								name="propertyTerm" value="${realEstateMortgage.propertyTerm }">
						</div>
					</div>
				</div>

				<div class="row">
					<div class="form-group col-md-6">
						<label class="col-sm-5 control-label" for="landAcquiringWayCd">
							<font color="red">* </font> 土地取得方式： </label>
						<div class="col-sm-6">
							<select class="form-control" id="landAcquiringWayCd"
								name="landAcquiringWayCd" style="width: 100%;height: 34px;">
								<dd:options codeType="landAcquiringWayCd"
									selected="${realEstateMortgage.landAcquiringWayCd }" />
							</select>
						</div>
					</div>

					<div class="form-group col-md-6">
						<label class="col-sm-5 control-label" for="utilizationType">
							<font color="red">* </font> 使用类型： </label>
						<div class="col-sm-6">
							<select class="form-control" id="utilizationType"
								name="utilizationType" style="width: 100%;height: 34px;">
								<dd:options codeType="LandUtilization"
									selected="${realEstateMortgage.utilizationType }" />
							</select>
						</div>
					</div>
				</div>

				<div class="row">
					<div class="form-group col-md-6">
						<label class="col-sm-5 control-label" for="houseQuitclaimCode">
							<font color="red">* </font> 房屋产权证号： </label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="houseQuitclaimCode"
								name="houseQuitclaimCode"
								value="${realEstateMortgage.houseQuitclaimCode }">
						</div>
					</div>

					<div class="form-group col-md-6">
						<label class="col-sm-5 control-label" for="landArea"> <font
							color="red">* </font> 使用面积（平方米）： </label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="landArea"
								name="landArea" value="${realEstateMortgage.landArea }">
						</div>
					</div>
				</div>

				<div class="row">
					<div class="form-group col-md-6">
						<label class="col-sm-5 control-label" for="totleFloor"> <font
							color="red">* </font> 总层数： </label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="totleFloor"
								name="totleFloor" value="${realEstateMortgage.totleFloor }">
						</div>
					</div>
				</div>
			</div>

			<div id="land" style="display: none;">
				<h6 class="blue">地产详细信息</h6>

				<div class="row">
					<div class="form-group col-md-6">
						<label class="col-sm-5 control-label" for="abstractEmissarg">
							<font color="red">* </font> 产权、使用权人： </label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="abstractEmissarg"
								name="abstractEmissarg" value="${landProduce.abstractEmissarg }">
						</div>
					</div>

					<div class="form-group col-md-6">
						<label class="col-sm-5 control-label" for="terraNumber"> <font
							color="red">* </font> 地号： </label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="terraNumber"
								name="terraNumber" value="${landProduce.terraNumber }">
						</div>
					</div>
				</div>

				<div class="row">
					<div class="form-group col-md-6">
						<label class="col-sm-5 control-label" for="landEsplees"> <font
							color="red">* </font> 土地使用权证号： </label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="landEsplees"
								name="landEsplees" value="${landProduce.landEsplees }">
						</div>
					</div>

					<div class="form-group col-md-6">
						<label class="col-sm-5 control-label" for="accessType"> <font
							color="red">* </font> 使用权类型： </label>
						<div class="col-sm-6">
							<select class="form-control" id="accessType" name="accessType">
								<dd:options codeType="AccessTypeCodeCD"
									selected="${landProduce.accessType }" />
							</select>
						</div>
					</div>
				</div>

				<div class="row">
					<div class="form-group col-md-6">
						<label class="col-sm-5 control-label" for="particularArea">
							独有面积（平方米）： </label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="particularArea"
								name="particularArea" value="${landProduce.particularArea }">
						</div>
					</div>

					<div class="form-group col-md-6">
						<label class="col-sm-5 control-label" for="landSit"> <font
							color="red">* </font> 坐落： </label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="landSit"
								name="landSit" value="${landProduce.landSit }">
						</div>
					</div>
				</div>

				<div class="row">
					<div class="form-group col-md-6">
						<label class="col-sm-5 control-label" for="geographyPurpose">
							<font color="red">* </font> 地类（用途）： </label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="geographyPurpose"
								name="geographyPurpose" value="${landProduce.geographyPurpose }">
						</div>
					</div>

					<div class="form-group col-md-6">
						<label class="col-sm-5 control-label" for="landEndDate"> <font
							color="red">* </font> 土地终止日期： </label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="landEndDate"
								data-date-format="yyyy-mm-dd" name="landEndDate"
								value="${landProduce.landEndDateStr }">
						</div>
					</div>
				</div>

				<div class="row">
					<div class="form-group col-md-6">
						<label class="col-sm-5 control-label" for="landUseArea"> <font
							color="red">* </font> 使用权面积（平方米）： </label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="landUseArea"
								name="landUseArea" value="${landProduce.landUseArea }">
						</div>
					</div>

					<div class="form-group col-md-6">
						<label class="col-sm-5 control-label" for="apportionArea">
							分摊面积（平方米）： </label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="apportionArea"
								name="apportionArea" value="${landProduce.apportionArea }">
						</div>
					</div>
				</div>
			</div>

			<div id="machine" style="display: none;">
				<h6 class="blue">机械设备详细信息</h6>

				<div class="row">
					<div class="form-group col-md-6">
						<label class="col-sm-5 control-label" for="modelType"> <font
							color="red">* </font> 规格型号： </label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="modelType"
								name="modelType" value="${machineEquipmentMortgage.modelType }">
						</div>
					</div>

					<div class="form-group col-md-6">
						<label class="col-sm-5 control-label" for="equipMount">
							数量： </label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="equipMount"
								name="equipMount"
								value="${machineEquipmentMortgage.equipMount }">
						</div>
					</div>
				</div>

				<div class="row">
					<div class="form-group col-md-6">
						<label class="col-sm-5 control-label" for="safeCheckInd">
							<font color="red">* </font> 有无安全检查证明： </label>
						<div class="col-sm-6">
							<input type="hidden" id="safeCheckInd"
								value="${machineEquipmentMortgage.safeCheckInd }"> <label
								class="radio-inline"> <input type="radio"
								name="safeCheckInd" value="1" checked="checked">有</label> <label
								class="radio-inline"> <input type="radio"
								name="safeCheckInd" value="2">无</label>
						</div>
					</div>

					<div class="form-group col-md-6">
						<label class="col-sm-5 control-label" for="deviceUseLife">
							<font color="red">* </font> 设备使用寿命（年）： </label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="deviceUseLife"
								name="deviceUseLife"
								value="${machineEquipmentMortgage.deviceUseLife }">
						</div>
					</div>
				</div>

				<div class="row">
					<div class="form-group col-md-6">
						<label class="col-sm-5 control-label" for="fireCheckInd">
							<font color="red">* </font> 有无消防检查证明： </label>
						<div class="col-sm-6">
							<input type="hidden" id="fireCheckInd"
								value="${machineEquipmentMortgage.fireCheckInd }"> <label
								class="radio-inline"> <input type="radio"
								name="fireCheckInd" value="1" checked="checked">有</label> <label
								class="radio-inline"> <input type="radio"
								name="fireCheckInd" value="2">无</label>
						</div>
					</div>

					<div class="form-group col-md-6">
						<label class="col-sm-5 control-label" for="eliLicenseInd">
							<font color="red">* </font> 有无产品合格证： </label>
						<div class="col-sm-6">
							<input type="hidden" id="eliLicenseInd"
								value="${machineEquipmentMortgage.eliLicenseInd }"> <label
								class="radio-inline"> <input type="radio"
								name="eliLicenseInd" value="1" checked="checked">有</label> <label
								class="radio-inline"> <input type="radio"
								name="eliLicenseInd" value="2">无</label>
						</div>
					</div>
				</div>

				<div class="row">
					<div class="form-group col-md-6">
						<label class="col-sm-5 control-label" for="utilizedYears">
							<font color="red">* </font> 已使用年限： </label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="utilizedYears"
								name="utilizedYears"
								value="${machineEquipmentMortgage.utilizedYears }">
						</div>
					</div>

					<div class="form-group col-md-6">
						<label class="col-sm-5 control-label" for="oriPurPrice"> <font
							color="red">* </font> 原购置价： </label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="oriPurPrice"
								name="oriPurPrice"
								value="${machineEquipmentMortgage.oriPurPrice }">
						</div>
					</div>
				</div>

				<div class="row">
					<div class="form-group col-md-6">
						<label class="col-sm-5 control-label" for="purpose"> <font
							color="red">* </font> 具体用途： </label>
						<div class="col-sm-6">
							<textarea class="form-control" rows="3" id="purpose"
								name="purpose">${machineEquipmentMortgage.purpose }</textarea>
						</div>
					</div>
				</div>
			</div>

			<div id="vehicle" style="display: none;">
				<h6 class="blue">机动车详细信息</h6>

				<div class="row">
					<div class="form-group col-md-6">
						<label class="col-sm-5 control-label" for="registerDate">
							<font color="red">* </font> 购置日期： </label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="registerDate"
								name="registerDate" data-date-format="yyyy-mm-dd"
								value="${trafficCar.registerDateStr }">
						</div>
					</div>

					<div class="form-group col-md-6">
						<label class="col-sm-5 control-label" for="modelStyle"> <font
							color="red">* </font> 车辆型号： </label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="modelStyle"
								name="modelStyle" value="${trafficCar.modelStyle }">
						</div>
					</div>
				</div>

				<div class="row">
					<div class="form-group col-md-6">
						<label class="col-sm-5 control-label" for="launchPlaneNumber">
							<font color="red">* </font> 发动机号： </label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="launchPlaneNumber"
								name="launchPlaneNumber"
								value="${trafficCar.launchPlaneNumber }">
						</div>
					</div>

					<div class="form-group col-md-6">
						<label class="col-sm-5 control-label" for="machineSteamCard">
							<font color="red">* </font> 机动车行驶证号： </label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="machineSteamCard"
								name="machineSteamCard" value="${trafficCar.machineSteamCard }">
						</div>
					</div>
				</div>

				<div class="row">
					<div class="form-group col-md-6">
						<label class="col-sm-5 control-label" for="frameNumber"> <font
							color="red">* </font> 车架号： </label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="frameNumber"
								name="frameNumber" value="${trafficCar.frameNumber }">
						</div>
					</div>

					<div class="form-group col-md-6">
						<label class="col-sm-5 control-label" for="vehicleNumber">
							<font color="red">* </font> 车牌号： </label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="vehicleNumber"
								name="vehicleNumber" value="${trafficCar.vehicleNumber }">
						</div>
					</div>
				</div>

				<div class="row">
					<div class="form-group col-md-6">
						<label class="col-sm-5 control-label" for="machineEnrol">
							<font color="red">* </font> 机动车登记证号： </label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="machineEnrol"
								name="machineEnrol" value="${trafficCar.machineEnrol }">
						</div>
					</div>

					<div class="form-group col-md-6">
						<label class="col-sm-5 control-label" for="runnedKm"> <font
							color="red">* </font> 里程数（公里）： </label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="runnedKm"
								name="runnedKm" value="${trafficCar.runnedKm }">
						</div>
					</div>
				</div>

				<div class="row">
					<div class="form-group col-md-6">
						<label class="col-sm-5 control-label" for="troubleRecords">
							<font color="red">* </font> 事故记录： </label>
						<div class="col-sm-6">
							<textarea class="form-control" rows="3" id="troubleRecords"
								name="troubleRecords">${trafficCar.troubleRecords }</textarea>
						</div>
					</div>
				</div>
			</div>

			<hr />

			<div class="row">
				<div class="col-md-6 col-md-offset-5">
					<c:if test="${type != 'detail' }">
						<button id="save" type="submit" class="btn btn-sm btn-primary">
							<i class="ace-icon fa fa-floppy-o"></i> 保存
						</button>
					</c:if>
					<c:if test="${hideOfBizAdd}">
						<button id="back" type="button" class="btn btn-sm btn-default">
							<i class="ace-icon fa fa-arrow-left"></i> 返回
						</button>
					</c:if>
				</div>
			</div>
		</form>

	</div>
</div>
<!-- 客户选择弹出框 -->
<div id="customerModal" class="modal fade" data-backdrop="static">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">选择客户</h4>
			</div>
			<div class="modal-body">
				<div class="row">
					<div class="col-md-12">
						<form id="customerForm" class="form-inline" role="form">
							<div class="form-group">
								<label class="sr-only" for="customerName"></label> 客户名称： <input
									type="text" class="form-control" id="customerName" />
							</div>

							<div class="form-group">
								<label class="sr-only" for="certificateTypeCd"></label> 证件类型： <select
									id="certificateTypeCd" style="width: 162px;height: 34px;">
									<dd:options codeType="PCertificateType" />
								</select>
							</div>

							<div class="form-group">
								<label class="sr-only" for="certificateNum"></label> 证件号码： <input
									type="text" class="form-control" id="certificateNum" />
							</div>

							<button type="button" class="btn btn-sm btn-purple" id="search">
								<i class="ace-icon fa fa-search"></i>查询
							</button>

							<button type="button" class="btn btn-sm btn-default" id="reset">
								<i class="ace-icon fa fa-undo"></i>重置
							</button>

						</form>

						<div class="row">
							<div class="col-md-12">
								<table id="table" class="table table-striped table-hover">
									<thead>
										<tr>
											<th></th>
											<th>客户编号</th>
											<th>客户名称</th>
											<th>客户类型</th>
											<th>证件类型</th>
											<th>证件号码</th>
											<th>状态</th>
											<td hidden="true">证件类型代码</td>
										</tr>
									</thead>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button id="sure" type="button" class="btn btn-primary">确定</button>
				<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
			</div>
		</div>
	</div>
</div>