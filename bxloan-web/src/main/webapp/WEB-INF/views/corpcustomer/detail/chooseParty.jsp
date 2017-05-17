<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="dd" uri="http://www.coamc-tech.com/taglibs/datadict" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="existing_party_modal" class="modal" tabindex="-1" data-backdrop="static">
	<div class="modal-dialog" style="width:70%">
		<div class="modal-content">
			<form id="existing_party_form" action="" class="form-horizontal" >
			
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="blue bigger">
						<i class='ace-icon fa fa-search'></i> 请选择
					</h4>
				</div>
				
				<div class="modal-body">
				
					<div class="row">
						<div class="col-md-12">
							<div class="form-group">
								<label class="col-xs-12 col-sm-1 control-label no-padding-right">
									客户名称
								</label>
								<div class="col-xs-12 col-sm-2">
									<span class="block input-icon input-icon-right">
										<input type="text" class="form-control"
										name="partyName" 
										id="existing_party_partyName"/>
									</span>
								</div>
								<div class="help-block col-xs-12 col-sm-reset inline"></div>
								
								<span id="existing_party_certificatetype_warp">
								<label class="col-xs-12 col-sm-1 control-label no-padding-right">
									证件类型
								</label>
								<div class="col-xs-12 col-sm-2">
									<span class="block input-icon input-icon-right">
										<select name="partyTypeCd" 
										id="existing_party_certificatetype" class="form-control">
											<option value=" ">全部</option>
											<dd:options codeType="PCertificateType"/>
					                    </select>
					                    <input type="hidden" id="existing_party_certificatetype_value" />
									</span>
								</div>
								
								<div class="help-block col-xs-12 col-sm-reset inline"></div>
								</span>
								
								<label class="col-xs-12 col-sm-1 control-label no-padding-right">
									证件号码
								</label>
								<div class="col-xs-12 col-sm-2">
									<span class="block input-icon input-icon-right">
										<input type="text" class="form-control"
										name="licence" 
										id="existing_party_licence"/>
									</span>
								</div>
								<div class="help-block col-xs-12 col-sm-reset inline"></div>
								<button type="button" class="btn btn-sm btn-purple" 
									id="existing_party_btn_search" 
									style="float:right">
									<i class="ace-icon fa fa-search"></i> 查询
								</button>
							</div>
							
							<table id="exist_party_tbl" class="table table-striped table-hover">
								<thead>
									<tr>
										<th>&nbsp;</th>
										<th>客户编号</th>
										<th>客户名称</th>
										<th>证件类型</th>
										<th>证件号码</th>
										<th>状态</th>
										<th>客户经理</th>
									</tr>
								</thead>
								<tbody>
								<%--  --%>
								</tbody>
							</table>
							
						</div>
					</div>
				
				</div>
				<div class="modal-footer center">
					<button type="button" class="btn btn-sm btn-primary" 
						id="existing_party_btn_confirm" partyType="">
						<i class="ace-icon fa fa-floppy-o"></i> 确定
					</button>
					<button class="btn btn-sm btn-default" data-dismiss="modal">
						<i class="ace-icon fa fa-times"></i> 取消
					</button>
				</div>
				
			</form>
		</div>
	</div>
</div>