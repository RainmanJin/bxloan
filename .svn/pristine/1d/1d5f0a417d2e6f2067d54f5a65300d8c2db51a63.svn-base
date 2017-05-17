<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../commons/taglibs.jsp"%>
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
<!-- <div id="customerDetailForCommonBorrowerDiv"
	style="display: none;width: 100%;height: 1300px;">
	<iframe id="iframeForCommonBorrower" width="100%" height="100%"
		style="border: 0px;"></iframe>
	<div class="wizard-actions">
		<button id="detailCommonBorrowerBack" type="button"
			class="btn btn-sm btn-default">
			<i class="ace-icon fa fa-arrow-left"></i> 返回111
		</button>
	</div>
</div> -->
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

<div id="newCommonBorrowerModal" class="modal fade"
	data-backdrop="static" tabindex="-1">
	<div class="modal-dialog modal-lg" style="width: 1000px;">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title">
					<i class='ace-icon fa fa-plus'></i> 新建共同借款人
				</h4>
			</div>
			<div class="modal-body" style="height: 100%;">
				<form id="newCommonBorrowerForm" class="form-horizontal">

					<input type="hidden" name="projectId" value="${vo.projectId }">

					<div class="form-group">
						<label class="col-xs-12 col-sm-2 control-label no-padding-right">
							<font color='red'> * </font>姓名：</label>
						<div class="col-xs-12 col-sm-3">
							<span class="block input-icon input-icon-right"> <input
								type="text" class="form-control required" id="cbcustomerName"
								name="customerName"> </span>
						</div>
						<div class="help-block col-xs-12 col-sm-reset inline"></div>

						<label class="col-xs-12 col-sm-3 control-label no-padding-right">
							<font color='red'> * </font>身份证号：</label>
						<div class="col-xs-12 col-sm-3">
							<span class="block input-icon input-icon-right"> <input
								type="text" class="form-control required isIDcard"
								id="cbcertificateNum" name="certificateNum"> </span>
						</div>
						<div class="help-block col-xs-12 col-sm-reset inline"></div>
					</div>

					<div class="form-group">
						<label class="col-xs-12 col-sm-2 control-label no-padding-right">
							<font color='red'> * </font>国家： </label>
						<div class="col-xs-12 col-sm-3">
							<span class="block input-icon input-icon-right"> <select
								id="nation" name="nation" class="form-control required">
									<option value="">请选择</option>
							</select> </span>
						</div>
						<div class="help-block col-xs-12 col-sm-reset inline"></div>
						<label class="col-xs-12 col-sm-3 control-label no-padding-right">
							<font color='red'> * </font>省份/自治区/直辖市： </label>
						<div class="col-xs-12 col-sm-3">
							<span class="block input-icon input-icon-right"> <select
								id="province" name="provinceCd" class="form-control required">
									<option value="">请选择</option>
							</select> </span>
						</div>
						<div class="help-block col-xs-12 col-sm-reset inline"></div>

					</div>

					<div class="form-group">
						<label class="col-xs-12 col-sm-2 control-label no-padding-right">
							<font color='red'> * </font>城市/自治州： </label>
						<div class="col-xs-12 col-sm-3">
							<span class="block input-icon input-icon-right"> <select
								id="city" name="cityCd" class="form-control required">
									<option value="">请选择</option>
							</select> </span>
						</div>
						<div class="help-block col-xs-12 col-sm-reset inline"></div>
						<label class="col-xs-12 col-sm-3 control-label no-padding-right">
							区/县： </label>
						<div class="col-xs-12 col-sm-3">
							<span class="block input-icon input-icon-right"> <select
								id="county" name="countyCd" class="form-control">
									<option value="">请选择</option>
							</select> </span>
						</div>
						<div class="help-block col-xs-12 col-sm-reset inline"></div>
					</div>

					<div class="form-group">
						<label class="col-xs-12 col-sm-2 control-label no-padding-right">
							<font color='red'> * </font>手机1：</label>
						<div class="col-xs-12 col-sm-3">
							<span class="block input-icon input-icon-right"> <input
								type="text" class="form-control required isMobile"
								id="cbmobileTel" name="mobileTel"> </span>
						</div>
						<div class="help-block col-xs-12 col-sm-reset inline"></div>

						<label class="col-xs-12 col-sm-3 control-label no-padding-right">
							手机2：</label>
						<div class="col-xs-12 col-sm-3">
							<span class="block input-icon input-icon-right"> <input
								type="text" class="form-control isMobile" id="cbtelphone2"
								name="telphone2"> </span>
						</div>
						<div class="help-block col-xs-12 col-sm-reset inline"></div>
					</div>

					<div class="form-group">
						<label class="col-xs-12 col-sm-2 control-label no-padding-right">
							E-mail：</label>
						<div class="col-xs-12 col-sm-3">
							<span class="block input-icon input-icon-right"> <input
								type="text" class="form-control isEmail" id="cbemail"
								name="email"> </span>
						</div>
						<div class="help-block col-xs-12 col-sm-reset inline"></div>

						<label class="col-xs-12 col-sm-3 control-label no-padding-right">
							QQ号：</label>
						<div class="col-xs-12 col-sm-3">
							<span class="block input-icon input-icon-right"> <input
								type="text" class="form-control isQq" id="cbqq" name="qq">
							</span>
						</div>
						<div class="help-block col-xs-12 col-sm-reset inline"></div>
					</div>

					<div class="form-group">
						<label class="col-xs-12 col-sm-2 control-label no-padding-right">
							微信号：</label>
						<div class="col-xs-12 col-sm-3">
							<span class="block input-icon input-icon-right"> <input
								type="text" class="form-control isMicro" id="cbmicroAccount"
								name="microAccount"> </span>
						</div>
						<div class="help-block col-xs-12 col-sm-reset inline"></div>

						<label class="col-xs-12 col-sm-3 control-label no-padding-right">
							<font color='red'> * </font>教育程度：</label>
						<div class="col-xs-12 col-sm-3">
							<span class="block input-icon input-icon-right"> <select
								id="cbdegreeCd" name="degreeCd" class="form-control required">
									<dd:options codeType="DegreeCode" />
							</select> </span>
						</div>
						<div class="help-block col-xs-12 col-sm-reset inline"></div>
					</div>

					<div class="form-group">
						<label class="col-xs-12 col-sm-2 control-label no-padding-right">
							<font color='red'> * </font>现居住地址：</label>
						<div class="col-xs-12 col-sm-9">
							<span class="block input-icon input-icon-right"> <input
								type="text" class="form-control required" id="cbfamilyAddress"
								name="familyAddress"> </span>
						</div>
						<div class="help-block col-xs-12 col-sm-reset inline"></div>
					</div>

					<div class="form-group">
						<label class="col-xs-12 col-sm-2 control-label no-padding-right">
							<font color='red'> * </font>单位名称：</label>
						<div class="col-xs-12 col-sm-3">
							<span class="block input-icon input-icon-right"> <input
								type="text" class="form-control required" id="cbworkCompany"
								name="workCompany"> </span>
						</div>
						<div class="help-block col-xs-12 col-sm-reset inline"></div>

						<label class="col-xs-12 col-sm-3 control-label no-padding-right">
							<font color='red'> * </font>职务：</label>
						<div class="col-xs-12 col-sm-3">
							<span class="block input-icon input-icon-right"> <input
								type="text" class="form-control required" id="cbprofessional"
								name="professional"> </span>
						</div>
						<div class="help-block col-xs-12 col-sm-reset inline"></div>
					</div>

					<div class="form-group">
						<label class="col-xs-12 col-sm-2 control-label no-padding-right">
							<font color='red'> * </font>单位电话：</label>
						<div class="col-xs-12 col-sm-3">
							<span class="block input-icon input-icon-right"> <input
								type="text" class="form-control required isTel"
								id="cbcompanyTel" name="companyTel"> </span>
						</div>
						<div class="help-block col-xs-12 col-sm-reset inline"></div>

						<label class="col-xs-12 col-sm-3 control-label no-padding-right">
							<font color='red'> * </font>雇佣类型：</label>
						<div class="col-xs-12 col-sm-3">
							<span class="block input-icon input-icon-right"> <select
								name="employmentType" id="cbemploymentType"
								class="form-control required">
									<dd:options codeType="EmploymentType" />
							</select> </span>
						</div>
						<div class="help-block col-xs-12 col-sm-reset inline"></div>
					</div>

					<div class="form-group">
						<label class="col-xs-12 col-sm-2 control-label no-padding-right">
							<font color='red'> * </font>单位地址：</label>
						<div class="col-xs-12 col-sm-9">
							<span class="block input-icon input-icon-right"> <input
								type="text" class="form-control required" id="cbcompanyAddress"
								name="companyAddress"> </span>
						</div>
						<div class="help-block col-xs-12 col-sm-reset inline"></div>
					</div>

					<div class="form-group">
						<label class="col-xs-12 col-sm-2 control-label no-padding-right">
							<font color='red'> * </font>本地工作年数：</label>
						<div class="col-xs-12 col-sm-3">
							<span class="block input-icon input-icon-right"> <input
								type="text" class="form-control required isFloatOneBit"
								id="cblocalWorkYear" name="localWorkYear"> </span>
						</div>
						<div class="help-block col-xs-12 col-sm-reset inline"></div>

						<label class="col-xs-12 col-sm-3 control-label no-padding-right">
							<font color='red'> * </font>现单位工作年数：</label>
						<div class="col-xs-12 col-sm-3">
							<span class="block input-icon input-icon-right"> <input
								type="text" class="form-control required isFloatOneBit"
								id="cbcurrCompanyWorkYears" name="currCompanyWorkYears">
							</span>
						</div>
						<div class="help-block col-xs-12 col-sm-reset inline"></div>
					</div>

					<div class="form-group">
						<label class="col-xs-12 col-sm-2 control-label no-padding-right">
							<font color='red'> * </font>是否转正：</label>
						<div class="col-xs-12 col-sm-3">
							<label class="checkbox-inline" style="margin-left: 6px;">
								<input type="radio" id="positiveInd1" name="positiveInd"
								value="1" checked="checked"
								class="ace add_corp_Type form-control" /> <span class='lbl'>是</span>
							</label> <label class="checkbox-inline" style="margin-left: 6px;">
								<input type="radio" id="positiveInd2" name="positiveInd"
								value="2" class="ace add_corp_Type form-control" /> <span
								class='lbl'>否</span> </label>
						</div>
						<div class="help-block col-xs-12 col-sm-reset inline"></div>

						<label class="col-xs-12 col-sm-3 control-label no-padding-right">
							<font color='red'> * </font>是否缴纳社保：</label>
						<div class="col-xs-12 col-sm-3">
							<label class="checkbox-inline" style="margin-left: 6px;">
								<input type="radio" id="paySocialSecurityInd1"
								name="paySocialSecurityInd" value="1" checked="checked"
								class="ace add_corp_Type form-control" /> <span class='lbl'>是</span>
							</label> <label class="checkbox-inline" style="margin-left: 6px;">
								<input type="radio" id="paySocialSecurityInd2"
								name="paySocialSecurityInd" value="2"
								class="ace add_corp_Type form-control" /> <span class='lbl'>否</span>
							</label>
						</div>
						<div class="help-block col-xs-12 col-sm-reset inline"></div>
					</div>

					<div class="form-group">
						<label class="col-xs-12 col-sm-2 control-label no-padding-right">
							<font color='red'> * </font>是否缴纳公积金：</label>
						<div class="col-xs-12 col-sm-3">
							<label class="checkbox-inline" style="margin-left: 6px;">
								<input type="radio" id="payFundInd1" name="payFundInd" value="1"
								checked="checked" class="ace add_corp_Type form-control" /> <span
								class='lbl'>是</span> </label> <label class="checkbox-inline"
								style="margin-left: 6px;"> <input type="radio"
								id="payFundInd2" name="payFundInd" value="2"
								class="ace add_corp_Type form-control" /> <span class='lbl'>否</span>
							</label>
						</div>
						<div class="help-block col-xs-12 col-sm-reset inline"></div>

						<label class="col-xs-12 col-sm-3 control-label no-padding-right">
							<font color='red'> * </font>发薪日：</label>
						<div class="col-xs-12 col-sm-3">
							<span class="block input-icon input-icon-right"> <input
								type="text" class="form-control required isDayInMonth"
								id="cbpayday" name="payday"> </span>
						</div>
						<div class="help-block col-xs-12 col-sm-reset inline"></div>
					</div>

					<hr>

					<div class="row">
						<div class="col-md-6 col-md-offset-10">
							<button id="saveNewCommonBorrower" type="submit"
								class="btn btn-sm btn-primary">
								<i class="ace-icon fa fa-floppy-o"></i> 保存
							</button>
						</div>
					</div>

				</form>
			</div>
		</div>
	</div>
</div>