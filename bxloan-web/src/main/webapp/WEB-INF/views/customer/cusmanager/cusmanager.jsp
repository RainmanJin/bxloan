<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.coamc-tech.com/taglibs/datadict" prefix="dd" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<input type="hidden" id="cusmanager_partyId_corp" value="${corpCus.partyId }"/>
<input type="hidden" id="cusmanager_partyId_person" value="${individual.partyId}"/>

<span class="my-button-group">
	<button role="add_cusmanager" class="btn btn-sm btn-success">
		<i class="ace-icon fa fa-plus"></i> 新增
	</button>
	<button role="transfer_role_cusmanager" class="btn btn-sm btn-success">
		<i class="ace-icon fa fa-exchange"></i> 角色互换
	</button>
	<button role='handOverMngPrivilege' class="btn btn-sm btn-success">
		<i class="ace-icon fa fa-stack-exchange"></i> 客户移交
	</button>
</span>
<table id="tbl_cusmanager" class="table table-striped table-hover">
	<thead>
		<tr>
			<th style="width: 30px;"><input type="checkbox" id="cusmanager_ck_all" /></th>
			<th style="width: 100px;">客户经理名称</th>
			<th style="width: 100px;">管理类型</th>
			<th style="width: 150px;">角色所在机构</th>
			<th style="width: 100px;">操作</th>
		</tr>
	</thead>
	<tbody>
	</tbody>
</table>

<!-- 编辑管户权弹出框 -->
<div id="add_CustomerManagerTeam" class="modal fade" data-backdrop="static">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">选择用户</h4>
			</div>
			<div class="modal-body">
				<div class="row">
					<div class="col-xs-12">
						<form class="form-horizontal" id="addCustomerNanagerTeam" name="addCustomerNanagerTeam" action="" method="post">
							<input type="hidden" id="cm_partyId" name="partyId" value="${individual.partyId }">
							<input type="hidden" id="cm_customerNum" name="customerNum" value="${individual.customerNum }">
							
							<input type="hidden" id="cmTeamId" name="cmTeamId">
							<input type="hidden" id="roleCd" name="roleCd"/>
							<div class="form-group">
								<div class="ie8">
									<label class="col-sm-2 control-label no-padding-right" for="form-field-1"> 所在机构名称： </label>
									<div class="col-sm-4">
										<div class="clearfix">
											<input type="text" id="orgName" name="orgName" class="form-control" readonly="readonly"/>
											<input type="hidden" id="orgCd" name="orgCd"/>
										</div>
									</div>
								</div>
								<div class="ie8">
								<label class="col-sm-2 control-label no-padding-right" >所在部门名称：</label>
								<div class="col-sm-4">
									<input type="text" id="depName" name="depName" class="form-control" readonly="readonly"/>
									<input type="hidden" id="deptCd" name="deptCd"/>
								</div>
								</div>
							</div>
							
							<div class="form-group">
								<div class="ie8">
									<label class="col-sm-2 control-label no-padding-right" for="form-field-1"> 用户名称： </label>
									<div class="col-sm-4">
										<div class="clearfix">
											<input type="text" id="userName" name="userName" class="form-control" readonly="readonly"/>
											<input type="hidden" id="userNum" name="userNum"/>
										</div>
									</div>
								</div>
								<div class="ie8">
									<label class="col-sm-2 control-label no-padding-right" >管理类型：</label>
									<div class="col-sm-4">
										<select name="managerType" id="managerType" class="form-control">
											<option  value="">请选择</option>
											<dd:options codeType="ManagementType" />
										</select>  
										<!--<div class="radio"> <label>
											<input class="ace" type = "radio" id="radio1" name="managerType" value="02" >
											<span class="lbl"> 业务权客户经理</span>
										</label>
										<label>
											<input class="ace" type = "radio" id="radio2" name="managerType" value="04" >
											<span class="lbl"> 查询权客户经理 </span>
										</label> </div>
									-->
									</div>
								</div>
							</div>
							
							<div class="form-group">
								<div class="ie8">
									<label class="col-sm-2 control-label no-padding-right" for="form-field-1"> 用户登录名： </label>
									<div class="col-sm-4">
										<span class="block input-icon input-icon-right">
											<div class="input-group">
												<input type="text" id="userLogonName" name="userLogonName" readonly="readonly" class="form-control"/>
												<span class="input-group-btn">
													<button id="btn-select-user" class="btn btn-sm btn-yellow" type="button">
														<i class="ace-icon fa fa-eye"></i>
													</button>
												</span>
											</div>
										</span>
									</div>
								</div>
							</div>
						</form>
						<div class="space-4"></div>
						<div class="space-4"></div>
						<div class="space-4"></div>
						<div class="form-group" style="display: none;" id="userModal">
							<div class="page-content">
								<form id="customerForm" class="form-inline">
									<div class="form-group" style="float: right;">
										<label class="sr-only" for="userName"></label> 
										用户名称： <input type="text" class="form-control" id="search_userName" />
										<button id="btn-query-user" type="button" class="btn btn-sm btn-purple"><i class="ace-icon fa fa-search"></i> 查询</button>
									</div>
								</form>
								<div class="row">
									<div class="col-md-12">
										<table id="ueTable" class="table table-striped table-hover">
											<thead>
												<tr>
													<th style="width: 10px;"></th>
													<th style="width: 100px;">登录名</th>
													<th style="width: 100px;">用户姓名</th>
													<th style="width: 200px;">所在机构</th>
													<th style="width: 80px;">状态</th>
												</tr>
											</thead>
										</table>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button id="saveCustomerManagerTeam" type="button" class="btn btn-sm btn-primary">确定</button>
				<button type="button" class="btn btn-sm btn-default" data-dismiss="modal">取消</button>
			</div>
		</div>
	</div>
</div>


<div id="choice-user" class="modal fade" data-backdrop="static">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title"></h4>
			</div>
			<div class="modal-body">
				<div class="row">
					<div class="col-xs-12">
						<div class="form-group">
							<div class="page-content">
								<form id="customerForm" class="form-inline">
									<div class="form-group" style="float: right;">
										<label class="sr-only" for="userName"></label> 
										用户名称： <input type="text" class="form-control" id="search_userName2"/>
										<button id="btn-query-user2" type="button" class="btn btn-sm btn-success"><i class="ace-icon fa fa-search"></i> 查询</button>
									</div>
								</form>
								<div class="row">
									<div class="col-md-12">
										<table id="ucTable" class="table table-striped table-hover">
											<thead>
												<tr>
													<th style="width: 10px;"></th>
													<th style="width: 100px;">登录名</th>
													<th style="width: 100px;">用户姓名</th>
													<th style="width: 200px;">所在机构</th>
													<th style="width: 80px;">状态</th>
												</tr>
											</thead>
										</table>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-sm btn-primary" role="sureHandOver">选择</button>
				<button type="button" class="btn btn-sm btn-default" data-dismiss="modal">取消</button>
			</div>
		</div>
	</div>
</div>