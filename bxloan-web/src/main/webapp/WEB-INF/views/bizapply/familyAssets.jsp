<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../../commons/taglibs.jsp"%>
<form class="form-horizontal" id="familyAssetsForm">

	<input type="hidden" name="projectId" value="${projectApplication.projectId}" />
	<input type="hidden" name="id" />

	<h4 class="blue">按揭房产</h4>
	
	<div id="houseone">

	<div class="form-group">
		<label class="col-xs-12 col-sm-2 control-label no-padding-right">
			房产证号：</label>
		<div class="col-xs-12 col-sm-3">
			<span class="block input-icon input-icon-right"> <input
				type="text" class="form-control"
				id="premisesPermitHouseone" name="premisesPermitHouseone"> </span>
		</div>
		<div class="help-block col-xs-12 col-sm-reset inline"></div>

		<label class="col-xs-12 col-sm-2 control-label no-padding-right">
			面积（平米）： </label>
		<div class="col-xs-12 col-sm-3">
			<span class="block input-icon input-icon-right"> <input
				type="text" class="form-control isIntThreeFloatOneBit"
				id="areaHouseone" name="areaHouseone"> </span>
		</div>
		<div class="help-block col-xs-12 col-sm-reset inline"></div>
	</div>

	<div class="form-group">
		<label class="col-xs-12 col-sm-2 control-label no-padding-right">
			估价（元）： </label>
		<div class="col-xs-12 col-sm-3">
			<span class="block input-icon input-icon-right"> <input
				type="text" class="form-control isNotNegative"
				id="evalValueHouseone" name="evalValueHouseone"> </span>
		</div>
		<div class="help-block col-xs-12 col-sm-reset inline"></div>

		<label class="col-xs-12 col-sm-2 control-label no-padding-right">
			类型： </label>
		<div class="col-xs-12 col-sm-3">
			<span class="block input-icon input-icon-right"> <input
				type="text" class="form-control"
				id="typeHouseone" name="typeHouseone"> </span>
		</div>
		<div class="help-block col-xs-12 col-sm-reset inline"></div>
	</div>

	<div class="form-group">
		<label class="col-xs-12 col-sm-2 control-label no-padding-right">
			房屋座落：</label>
		<div class="col-xs-12 col-sm-3">
			<span class="block input-icon input-icon-right"> <input
				type="text" class="form-control"
				id="landSitHouseone" name="landSitHouseone"> </span>
		</div>
		<div class="help-block col-xs-12 col-sm-reset inline"></div>

		<label class="col-xs-12 col-sm-2 control-label no-padding-right">
			贷款余额（元）： </label>
		<div class="col-xs-12 col-sm-3">
			<span class="block input-icon input-icon-right"> <input
				type="text" class="form-control isNotNegative"
				id="loanBalanceHouseone" name="loanBalanceHouseone"> </span>
		</div>
		<div class="help-block col-xs-12 col-sm-reset inline"></div>
	</div>

	<div class="form-group">
		<label class="col-xs-12 col-sm-2 control-label no-padding-right">
			房屋所有人： </label>
		<div class="col-xs-12 col-sm-3">
			<span class="block input-icon input-icon-right"> <input
				type="text" class="form-control"
				id="ownerHouseone" name="ownerHouseone">
			</span>
		</div>
		<div class="help-block col-xs-12 col-sm-reset inline"></div>

	</div>
	
	</div>

	<h4 class="blue">房产二</h4>
	
	<div id="housetwo">

	<div class="form-group">
		<label class="col-xs-12 col-sm-2 control-label no-padding-right">
			房产证号： </label>
		<div class="col-xs-12 col-sm-3">
			<span class="block input-icon input-icon-right"> <input
				type="text" class="form-control"
				id="premisesPermitHousetwo" name="premisesPermitHousetwo"> </span>
		</div>
		<div class="help-block col-xs-12 col-sm-reset inline"></div>

		<label class="col-xs-12 col-sm-2 control-label no-padding-right">
			面积（平米）：</label>
		<div class="col-xs-12 col-sm-3">
			<span class="block input-icon input-icon-right"> <input
				type="text" class="form-control isIntThreeFloatOneBit"
				id="areaHousetwo" name="areaHousetwo">
			</span>
		</div>
		<div class="help-block col-xs-12 col-sm-reset inline"></div>
	</div>

	<div class="form-group">
		<label class="col-xs-12 col-sm-2 control-label no-padding-right">
			估价（元）： </label>
		<div class="col-xs-12 col-sm-3">
			<span class="block input-icon input-icon-right"> <input
				type="text" class="form-control isNotNegative"
				id="evalValueHousetwo" name="evalValueHousetwo"> </span>
		</div>
		<div class="help-block col-xs-12 col-sm-reset inline"></div>

		<label class="col-xs-12 col-sm-2 control-label no-padding-right">
			类型： </label>
		<div class="col-xs-12 col-sm-3">
			<span class="block input-icon input-icon-right"> <input
				type="text" class="form-control"
				id="typeHousetwo" name="typeHousetwo"> </span>
		</div>
		<div class="help-block col-xs-12 col-sm-reset inline"></div>
	</div>

	<div class="form-group">
		<label class="col-xs-12 col-sm-2 control-label no-padding-right">
			房屋座落： </label>
		<div class="col-xs-12 col-sm-3">
			<span class="block input-icon input-icon-right"> <input
				type="text" class="form-control"
				id="landSitHousetwo" name="landSitHousetwo"> </span>
		</div>
		<div class="help-block col-xs-12 col-sm-reset inline"></div>

		<label class="col-xs-12 col-sm-2 control-label no-padding-right">
			贷款余额（元）： </label>
		<div class="col-xs-12 col-sm-3">
			<span class="block input-icon input-icon-right"> <input
				type="text" class="form-control isNotNegative"
				id="loanBalanceHousetwo" name="loanBalanceHousetwo"> </span>
		</div>
		<div class="help-block col-xs-12 col-sm-reset inline"></div>
	</div>

	<div class="form-group">
		<label class="col-xs-12 col-sm-2 control-label no-padding-right">
			房屋所有人： </label>
		<div class="col-xs-12 col-sm-3">
			<span class="block input-icon input-icon-right"> <input
				type="text" class="form-control"
				id="ownerHousetwo" name="ownerHousetwo"> </span>
		</div>		
		<div class="help-block col-xs-12 col-sm-reset inline"></div>
	</div>
	
	</div>

	<h4 class="blue">车产一</h4>
	
	<div id="carone">

	<div class="form-group">
		<label class="col-xs-12 col-sm-2 control-label no-padding-right">
			车型： </label>
		<div class="col-xs-12 col-sm-3">
			<span class="block input-icon input-icon-right"> <input
				type="text" class="form-control" id="typeCarone"
				name="typeCarone"> </span>
		</div>
		<div class="help-block col-xs-12 col-sm-reset inline"></div>
		
		<label class="col-xs-12 col-sm-2 control-label no-padding-right">
			车牌号： </label>
		<div class="col-xs-12 col-sm-3">
			<span class="block input-icon input-icon-right"> <input
				type="text" class="form-control" id="plateNumberCarone"
				name="plateNumberCarone"> </span>
		</div>
		<div class="help-block col-xs-12 col-sm-reset inline"></div>
	</div>
	
	<div class="form-group">
		<label class="col-xs-12 col-sm-2 control-label no-padding-right">
			购买年限： </label>
		<div class="col-xs-12 col-sm-3">
			<span class="block input-icon input-icon-right"> <input
				type="text" class="form-control isFloatOneBit" id="boughtYearCarone"
				name="boughtYearCarone"> </span>
		</div>
		<div class="help-block col-xs-12 col-sm-reset inline"></div>
		
		<label class="col-xs-12 col-sm-2 control-label no-padding-right">
			估价（元）： </label>
		<div class="col-xs-12 col-sm-3">
			<span class="block input-icon input-icon-right"> <input
				type="text" class="form-control isNotNegative" id="evalValueCarone"
				name="evalValueCarone"> </span>
		</div>
		<div class="help-block col-xs-12 col-sm-reset inline"></div>
	</div>
	
	<div class="form-group">
		<label class="col-xs-12 col-sm-2 control-label no-padding-right">
			所有人： </label>
		<div class="col-xs-12 col-sm-3">
			<span class="block input-icon input-icon-right"> <input
				type="text" class="form-control" id="ownerCarone"
				name="ownerCarone"> </span>
		</div>
		<div class="help-block col-xs-12 col-sm-reset inline"></div>
		
		<label class="col-xs-12 col-sm-2 control-label no-padding-right">
			贷款余额（元）： </label>
		<div class="col-xs-12 col-sm-3">
			<span class="block input-icon input-icon-right"> <input
				type="text" class="form-control isNotNegative" id="loanBalanceCarone"
				name="loanBalanceCarone"> </span>
		</div>
		<div class="help-block col-xs-12 col-sm-reset inline"></div>
	</div>
	
	</div>
	
	<c:if test="${consultLocation == null }">
		<hr>
		<div class="row">
			<div class="col-md-6 col-md-offset-5">
				<c:if test="${judgeType != 'check' }">
					<button type="submit" id="saveFamilyAssets" class="btn btn-sm btn-primary">
						<i class="ace-icon fa fa-floppy-o"></i> 保存
					</button>
				</c:if>
				<%-- <c:if test="${judgeType == 'check' }">
					<!-- <button type="button" name="backToDashboard"
						class="btn btn-sm btn-default">
						<i class="ace-icon fa fa-arrow-left"></i>返回
					</button> -->
				</c:if> --%>
			</div>
		</div>
	</c:if>

</form>