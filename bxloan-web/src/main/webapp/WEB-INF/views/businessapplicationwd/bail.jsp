<!-- 保证人担保 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../commons/taglibs.jsp"%>
<div id="bailTableDiv" style="display: block;">
	<table id="bailTable" class="table table-striped table-hover">
		<thead>
			<tr>
				<th>保证人姓名</th>
				<th>证件类型</th>
				<th>证件号码</th>
				<th>币种</th>
				<th>本次申请保证债权金额</th>
				<th>操作</th>
			</tr>
		</thead>
	</table>
</div>
<div id="customerTableDiv" style="display: none;">
	<div class="row">
		<form class="form-inline">
			<label class="sr-only" for="customerNameForBail"></label> 客户名称：<input
				type="text" class="form-control" id="customerNameForBail"
				style="width: 12%;"> <label class="sr-only"
				for="customerNumForBail"></label> 客户编号：<input type="text"
				class="form-control" id="customerNumForBail" style="width: 12%;">
			<label class="sr-only" for="certificateTypeCdForBail"></label> 证件类型：
			<select id="certificateTypeCdForBail"
				style="width: 12%;height: 34px;">
				<dd:options codeType="CertificateType" />
			</select> <label class="sr-only" for="certificateNumForBail"></label> 证件号码：<input
				type="text" class="form-control" id="certificateNumForBail"
				style="width: 12%;">

			<button type="button" class="btn btn-sm btn-purple"
				id="searchCustomerForBail">
				<i class="ace-icon fa fa-search"></i>查询
			</button>

			<button type="button" class="btn btn-sm btn-warning"
				id="resetCustomerForBail">
				<i class="ace-icon fa fa-undo"></i>重置
			</button>

			<button type="button" class="btn btn-sm btn-success"
				id="addqyCustomer">
				<i class="ace-icon fa fa-plus"></i> 创建企业客户
			</button>

			<button type="button" class="btn btn-sm btn-success"
				id="addgrCustomer">
				<i class="ace-icon fa fa-plus"></i> 创建个人客户
			</button>

		</form>

	</div>

	<div class="row">
		<table id="customerTableForBail"
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

		<br>

		<div class="row">
			<div class="col-md-6 col-md-offset-5">
				<button id="openBailSureModal" type="button"
					class="btn btn-sm btn-primary">
					<i class="ace-icon fa fa-floppy-o"></i> 确定
				</button>
				<button id="addBailBack" type="button"
					class="btn btn-sm btn-warning">
					<i class="ace-icon fa fa-arrow-left"></i> 返回
				</button>
			</div>
		</div>
	</div>

	<div id="bailModal" class="modal fade" data-backdrop="static"
		tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">基本表单</h4>
				</div>
				<form id="bailForm" class="form-horizontal">
					<div class="modal-body">
						<div class="row">
							<div class="col-xs-12">
								<div class="form-group">
									<label class="col-sm-5 control-label no-padding-right"
										for="addCustomerName"> <font color='red'> * </font>
										保证人编号： </label>
									<div class="col-sm-7">
										<input type="text" class="form-control"
											id="customerNumForBailForm" name="customerNum"
											readonly="readonly" />
									</div>
								</div>

								<div class="form-group">
									<label class="col-sm-5 control-label no-padding-right"
										for="addCustomerName"> <font color='red'> * </font>
										保证人名称： </label>
									<div class="col-sm-7">
										<input type="text" class="form-control"
											id="customerNameForBailForm" name="customerName"
											readonly="readonly" />
									</div>
								</div>

								<div class="form-group">
									<label class="col-sm-5 control-label no-padding-right"
										for="guaranteeAmtForBailForm"> <font color='red'>
											* </font> 本次申请保证债权金额（元）： </label>
									<div class="col-sm-7">
										<input type="text" class="form-control required isNumber"
											id="guaranteeAmtForBailForm" name="guaranteeAmt"
											readonly="readonly" />
									</div>
								</div>
								
								<div class="form-group">
									<label class="col-sm-5 control-label no-padding-right" for="bailMateBorrower">
										<font color='red'>* </font> 保证人配偶是否作为保证人： </label>
									<div class="col-sm-7">
										<select class="form-control" id="bailMateBorrower" name="bailMateBorrower">
											<dd:options codeType="CommonWhether"/>
										</select>
									</div>
								</div>

								<input type="hidden" id="partyIdForBail" name="partyIdForBail">
							</div>
						</div>

					</div>
					<div class="modal-footer">
						<button id="sureBailForm" type="submit" class="btn btn-primary">确定</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					</div>
				</form>
			</div>
		</div>
	</div>

	<div id="qyModal" class="modal fade" data-backdrop="static">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">创建企业客户</h4>
				</div>
				<form id="qyCustomerForm" class="form-horizontal">
					<div class="modal-body">
						<div class="row">
							<div class="col-xs-12">
								<div class="form-group">
									<label
										class="col-xs-12 col-sm-3 control-label no-padding-right">
										<font color='red'> * </font>组织机构代码： </label>
									<div class="col-xs-12 col-sm-8">
										<span class="block input-icon input-icon-right"> <input
											type="text" class="form-control required isOrgCodeAndLicence"
											id="certificateNumForBailForm" name="certificateNum" /> </span>
									</div>
									<div class="help-block col-xs-12 col-sm-reset inline"></div>
								</div>

								<div class="form-group">
									<label
										class="col-xs-12 col-sm-3 control-label no-padding-right">
										<font color='red'> * </font>客户名称： </label>
									<div class="col-xs-12 col-sm-8">
										<span class="block input-icon input-icon-right"> <input id="customerName_bail"
											type="text" class="form-control" name="customerName" />
										</span>
									</div>
									<div class="help-block col-xs-12 col-sm-reset inline"></div>
								</div>

								<div class="form-group">
									<label
										class="col-xs-12 col-sm-3 control-label no-padding-right">
										<font color='red'> * </font>营业执照： </label>
									<div class="col-xs-12 col-sm-8">
										<span class="block input-icon input-icon-right"> <input
											type="text" class="form-control required"
											id="businessLicenseNumForBailForm" name="businessLicenseNum" />
										</span>
									</div>
									<div class="help-block col-xs-12 col-sm-reset inline"></div>
								</div>
							</div>
						</div>

					</div>
					<div class="modal-footer">
						<button id="sureBailForm" type="submit"
							class="btn btn-sm btn-primary">
							<i class="ace-icon fa fa-arrow-right"></i> 提交
						</button>
						<button type="button" class="btn btn-sm btn-default"
							data-dismiss="modal">
							<i class="ace-icon fa fa-times"></i> 取消
						</button>
					</div>
				</form>
			</div>
		</div>
	</div>

	<div id="grModal" class="modal fade" data-backdrop="static">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">创建个人客户</h4>
				</div>
				<form id="grCustomerForm" action="singleCustomer/saveSimple"
					class="form-horizontal" method="post">
					<div class="modal-body">
						<div class="row">
							<div class="col-xs-12">
								<div class="form-group">
									<label
										class="col-xs-12 col-sm-3 control-label no-padding-right">
										<font color='red'> * </font>客户名称： </label>
									<div class="col-xs-12 col-sm-8">
										<span class="block input-icon input-icon-right"> <input
											type="text" id="addCustomerName" name="customerName"
											class="form-control required" /> </span>
									</div>
									<div class="help-block col-xs-12 col-sm-reset inline"></div>
								</div>
								<div class="form-group">
									<label
										class="col-xs-12 col-sm-3 control-label no-padding-right">
										<font color='red'> * </font>证件类型： </label>
									<div class="col-xs-12 col-sm-8">
										<span class="block input-icon input-icon-right"> <select
											name="certificateTypeCd" id="addCertificateType"
											class="form-control required">
												<dd:options codeType="PCertificateType" />
										</select> </span>
									</div>
									<div class="help-block col-xs-12 col-sm-reset inline"></div>
								</div>
								<div class="form-group">
									<label
										class="col-xs-12 col-sm-3 control-label no-padding-right">
										<font color='red'> * </font>证件号码： </label>
									<div class="col-xs-12 col-sm-8">
										<span class="block input-icon input-icon-right"> <input
											type="text" id="addCertificateNum" name="certificateNum"
											class="form-control required" /> </span>
									</div>
									<div class="help-block col-xs-12 col-sm-reset inline"></div>
								</div>
								<div class="form-group">
									<label
										class="col-xs-12 col-sm-3 control-label no-padding-right">
										<font color='red'> * </font>雇佣类型： </label>
									<div class="col-xs-12 col-sm-8">
										<span class="block input-icon input-icon-right"> <select
											name="employmentType" id="addEmploymentType"
											class="form-control required">
												<dd:options codeType="EmploymentType" />
										</select> </span>
									</div>
									<div class="help-block col-xs-12 col-sm-reset inline"></div>
								</div>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button id="add-simple-submit" class="btn btn-sm btn-primary"
							type="submit" data-loading-text="正在保存中...">
							<i class="ace-icon fa fa-arrow-right"></i> 提交
						</button>
						<button class="btn btn-sm btn-default" data-dismiss="modal"
							type="button">
							<i class="ace-icon fa fa-times"></i> 取消
						</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>

<div id="bailDetailDiv" class="modal" data-backdrop="static"
	tabindex="-1">
	<div class="modal-dialog modal-lg" style="width: 900px;">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title">保证人信息</h4>
			</div>
			<div class="modal-body" style="height: 1000px;">
				<iframe id="bailIframe" width="100%" height="100%"
					style="border: 0px;"></iframe>
			</div>
		</div>
	</div>
</div>
<div id="corpCustomerDetailDiv" class="modal" data-backdrop="static"
	tabindex="-1">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title">完善企业客户信息</h4>
			</div>
			<div class="modal-body" style="height: 100%;">
				<iframe id="corpCustomerDetailIframe" width="100%" height="100%"
					src="" frameborder="0" marginheight="0" marginwidth="0"
					scrolling-x="no" scrolling-y="auto"
					style="overflow-x:scroll; overflow-y:hidden;"></iframe>
			</div>
		</div>
	</div>
</div>
<div id="singleCustomerDetailDiv" class="modal" data-backdrop="static"
	tabindex="-1">
	<div class="modal-dialog modal-lg" style="width: 1000px;">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title">完善个人客户信息</h4>
			</div>
			<div class="modal-body" style="height: 100%;">
				<iframe id="singleCustomerDetailIframe" width="100%" height="100%"
					src="" frameborder="0" marginheight="0" marginwidth="0"
					scrolling-x="no" scrolling-y="auto"
					style="overflow-x:scroll; overflow-y:hidden;"></iframe>
			</div>
		</div>
	</div>
</div>