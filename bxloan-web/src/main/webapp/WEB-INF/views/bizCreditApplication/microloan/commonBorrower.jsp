<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../../commons/taglibs.jsp"%>
<div id="commonBorrowerTableDiv" style="display: block;">
	<table id="commonBorrowerTable" class="table table-striped table-hover">
		<thead>
			<tr>
				<th>客户名称</th>
				<th>证件类型</th>
				<th>证件号码</th>
				<th>手机</th>
				<th>联系电话</th>
				<th>单位地址</th>
				<th>操作</th>
			</tr>
		</thead>
	</table>
</div>
<div id="customerTableDivForCommonBorrower" style="display: none;">
	<div class="row">
		<div class="col-md-12">
			<form class="form-inline">
				<label class="sr-only" for="customerNameForCommonBorrower"></label>
				客户名称：<input type="text" class="form-control"
					id="customerNameForCommonBorrower"> <label class="sr-only"
					for="customerNumForCommonBorrower"></label> 客户编号：<input type="text"
					class="form-control" id="customerNumForCommonBorrower"> <label
					class="sr-only" for="certificateTypeCdForCommonBorrower"></label>
				证件类型： <select id="certificateTypeCdForCommonBorrower"
					style="width: 162px;height: 34px;">
					<dd:options codeType="CertificateType" />
				</select> <label class="sr-only" for="certificateNumForCommonBorrower"></label>
				证件号码：<input type="text" class="form-control"
					id="certificateNumForCommonBorrower">

				<button type="button" class="btn btn-sm btn-purple"
					id="searchForCommonBorrower">
					<i class="ace-icon fa fa-search"></i>查询
				</button>

				<button type="button" class="btn btn-sm btn-warning"
					id="resetForCommonBorrower">
					<i class="ace-icon fa fa-undo"></i>重置
				</button>

			</form>

			<div class="row">
				<div class="col-md-12">
					<table id="customerTableForCommonBorrower"
						class="table table-striped table-hover">
						<thead>
							<tr>
								<th></th>
								<th>客户名称</th>
								<th>客户编号</th>
								<th>证件类型</th>
								<th>证件号码</th>
								<th>状态</th>
								<th>借款人 / 担保人</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>

			<hr>

			<div class="row">
				<div class="col-md-6 col-md-offset-5">
					<button id="openCommonBorrowerSureModal" type="button"
						class="btn btn-sm btn-primary">
						<i class="ace-icon fa fa-floppy-o"></i> 确定
					</button>
					<button id="addCommonBorrowerBack" type="button"
						class="btn btn-sm btn-warning">
						<i class="ace-icon fa fa-arrow-left"></i> 返回
					</button>
				</div>
			</div>
		</div>
	</div>
</div>
<div id="mod-modal-formLxr" class="modal" data-backdrop="static"
	tabindex="-1">
	<div class="modal-dialog">
		<div class="modal-content">
			<form id="modFamilyFriendForm" class="form-horizontal">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						&times;</button>
					<h4 class="blue bigger"></h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-xs-12">
							<input type="hidden" id="form-field-0" name="id" />
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right"
									for="familyFriendType"> <font color='red'> * </font>
									联系人类型为： </label>
								<div class="col-sm-8">
									<span class="block input-icon input-icon-right" id="fftype">
										<select name="relationsType" id="mod-relationsType"
										class="form-control" disabled="disabled">
											<dd:options codeType="RelationsType" />
									</select> <input name="relationsType" type='hidden' /> </span>
								</div>
							</div>
							<div id="mod-formContent"></div>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
<div id="peiou" style="display:none;">
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="name">
			<font color='red'> * </font> 配偶姓名： </label>
		<div class="col-sm-8">
			<input type="text" name="name" class="form-control  "
				placeholder="输入姓名" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right"
			for="certificateCd"> <font color='red'> * </font> 身份证号： </label>
		<div class="col-sm-8">
			<input type="text" name="certificateCd" class="form-control"
				placeholder="输入证件号码" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right"
			for="censusRegister"> <font color='red'> * </font> 户籍所在地： </label>
		<div class="col-sm-8">
			<input type="text" name="censusRegister" class="form-control  "
				placeholder="输入户籍所在地" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right"
			for="receiveEducation"> 教育程度： </label>
		<div class="col-sm-8">
			<span class="block input-icon input-icon-right" id="receiveEducation">
				<select name="receiveEducation" class="form-control">
					<dd:options codeType="DegreeCode" />
			</select> </span>
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="telphone">
			<font color='red'> * </font> 手机： </label>
		<div class="col-sm-8">
			<input type="text" name="telphone" class="form-control  "
				placeholder="输入手机号码" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="mobileTel">
			手机2： </label>
		<div class="col-sm-8">
			<input type="text" name="mobileTel" class="form-control  "
				placeholder="输入手机号码" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right"
			for="workCompany"> 单位名称： </label>
		<div class="col-sm-8">
			<input type="text" name="workCompany" class="form-control  "
				placeholder="输入工作单位" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="metier">
			职务： </label>
		<div class="col-sm-8">
			<input type="text" name="metier" class="form-control  "
				placeholder="输入职务" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right"
			for="companyTel"> 单位电话： </label>
		<div class="col-sm-8">
			<input type="text" name="companyTel" class="form-control  "
				placeholder="输入单位电话" />
		</div>
	</div>
</div>

<div id="commonBorrowerDetailDiv" class="modal" data-backdrop="static"
	tabindex="-1">
	<div class="modal-dialog modal-lg" style="width: 900px;">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title">共同借款人信息</h4>
			</div>
			<div class="modal-body" style="height: 1000px;">
				<iframe id="commonBorrowerIframe" width="100%" height="100%"
					style="border: 0px;"></iframe>
			</div>
		</div>
	</div>
</div>

