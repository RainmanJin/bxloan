<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../../commons/taglibs.jsp"%>
<table id="bizExpenseTable" class="table table-striped table-hover">
	<thead>
		<tr>
			<th>费用类型</th>
			<th>费用名称</th>
			<th>标准费率(%)</th>
			<th>实收费率(%)</th>
			<th>标准收费</th>
			<th>实际收费</th>
			<th>提交时间</th>
			<c:if test="${type != 'check' }">
				<th>操作</th>
			</c:if>
		</tr>
	</thead>
</table>

<div id="expenseModal" class="modal fade" tabindex="-1" aria-hidden="true" data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content">
			<form id="expenseForm" class="form-horizontal">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="blue bigger"></h4>
				</div>
				<input type="hidden" name="projectId" value="${vo.projectId}" />
				<input type="hidden" name="projectNo" value="${vo.projectNo}" />
				<input type="hidden" name="bizExpenseRateId" />
				<div class="modal-body">
					<div class="row">
						<div class="col-xs-12">
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right" for="expenseCollectionType">
									<font color='red'>
										*
									</font>
									费用收费方式
								</label>
								<div class="col-sm-8">
									<span class="block input-icon input-icon-right">
										<select name="expenseCollectionType" id="expenseCollectionType" class="form-control">
											<dd:options codeType="FeeUsedType" />
										</select>
									</span>
								</div>
								<div class="help-block col-xs-12 col-sm-reset inline"></div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right" for="expenseName">
									<font color='red'>
										*
									</font>
									费用名称 
								</label>
								<div class="col-sm-8">
									<span class="block input-icon input-icon-right">
										<select name="expenseName" id="expenseName" class="form-control">
											<dd:options codeType="FeeType" />
										</select>
									</span>
								</div>
								<div class="help-block col-xs-12 col-sm-reset inline"></div>
							</div>
							<div class="form-group hideExpenseRate">
								<label class="col-sm-3 control-label no-padding-right" for="expenseRate">
									<font color='red'>
										*
									</font>
									实际费率（%）
								</label>
								<div class="col-sm-8">
									<span class="block input-icon input-icon-right">
										<input type="text" class="form-control" id="expenseRate" name="expenseRate" placeholder="实际费率" />
									</span>
								</div>
								<div class="help-block col-xs-12 col-sm-reset inline"></div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right" for="expenseAmt">
									<font color='red'>
										*
									</font>
									实际收费
								</label>
								<div class="col-sm-8">
									<span class="block input-icon input-icon-right">
										<input type="text" readonly="readonly" id="expenseAmt" name="expenseAmt" class="form-control" placeholder="实际收费" />
									</span>
								</div>
								<div class="help-block col-xs-12 col-sm-reset inline"></div>
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button id="add-simple-submit" class="btn btn-sm btn-primary">
						<i class="ace-icon fa fa-save"></i> 保存
					</button>
					<button class="btn btn-warning btn-sm" data-dismiss="modal">
						<i class="ace-icon fa fa-times"></i> 取消
					</button>
				</div>
			</form>
		</div>
	</div>
</div>
