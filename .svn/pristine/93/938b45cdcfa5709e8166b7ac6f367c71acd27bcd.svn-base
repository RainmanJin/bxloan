<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../../../commons/taglibs.jsp"%>
<!-- --------------过去12个月利润----------------- -->
<div class="col-md-12 widget-container-col ui-sortable">
	<div class="widget-box ui-sortable-handle" style="opacity: 1; z-index: 0;">
		<div class="widget-header">
			<h4 class="blue widget-title">过去12个月利润分配</h4>
			<div class="widget-toolbar">
				<c:if test="${type != 'check' }">
				<a href="javascript:void(0);" role="nyGain" title="新增"><i id="new1" data-seq="addNew" class="ace-icon fa fa-plus"></i></a>
				</c:if>
				&nbsp;
				<a href="javascript:void(0);" title="折叠|展开" data-action="collapse"><i class="ace-icon fa fa-chevron-up bigger-130"></i></a>
			</div>
		</div>
		<div class="widget-body">
			<table id="nyGainTbl" class="table table-striped">
				<thead>
				<tr>
					<th>资产类型</th>
					<th>时间</th>
					<th>内容</th>
					<th>金额</th>
					<th>操作</th>
				</tr>
				</thead>
				<tbody></tbody>
			</table>
		</div>
	</div>
</div>
<!-- ---------------过去12个月债务变化说明-------------------- -->
<div class="row">
<div class="col-md-12">
<form class="form-horizontal" id="common_debt_info_form">
	<input type="hidden" name="id">
	<input type="hidden" name="projectId" value="${vo.projectId }">
	<input type="hidden" name="type" value="1">
	<div class="form-group">
		<div class="help-block col-xs-12 col-sm-reset inline"></div>
		<label class="col-xs-12 col-sm-2 control-label no-padding-right">过去12个月债务变化说明：</label>
		<div class="col-xs-12 col-sm-3">
			<span class="block input-icon input-icon-right">
				<textarea rows="3" cols="102" name="remarks2"></textarea>
			</span>
		</div>
		<div class="help-block col-xs-12 col-sm-reset inline"></div>
	</div>
	<c:if test="${type != 'check' }">
		<span class="col-xs-12 col-sm-6 control-label no-padding-right">
			<button type="submit" class="btn btn-primary">
				<i class="ace-icon fa fa-floppy-o"></i> 保存
			</button>
		</span>
	</c:if>
</form>
</div>
</div>
<!-- ---------------过去12个月利润-------------------- -->
<div id="modal-nydistribution" class="modal" data-backdrop="static"
	tabindex="-1">
	<div class="modal-dialog">
		<div class="modal-content">
			<form id="DistributionForm" action="nyGainDistribution/save" class="form-horizontal"
				role="form" method="post">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="blue bigger"></h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-xs-12">
							<!--隐藏域      区分save和update -->
							<input type="hidden" id="form-my-220" name="id"/>
							<input type="hidden" name="projectId" value="${vo.projectId }">
							<input type="hidden" id="type" name="type" value="1"/>
							
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right"
									for="addCustomerName"> <font color='red'>*</font>资产类型：
								</label>
								<div class="col-sm-7">
									<span class="block input-icon input-icon-right">
										<select name="gainDisType" id="gainDisType" class="form-control">
											<dd:options codeType="FundType"/>
										</select>
									</span>
								</div>
							</div>
					        <div id="others2" class="form-group">
								<label class="col-sm-3 control-label no-padding-right">
									 <font color='red'>*</font>资产名称：
								</label>
								<div class="col-sm-7">
									<input type="text" name="name" id="name" class="form-control"/>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right"
									for="addCustomerName"> <font color='red'>*</font>时间：
								</label>
								<div class="col-sm-7">
									<input type="text" id="time" name="time" class="form-control" data-date-format="yyyy-mm-dd"/>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right"
									for="addCustomerName"> <font color='red'>*</font>内容：
								</label>
								<div class="col-sm-7">
									<input type="text" id="content" name="content" class="form-control"/>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right"
									for="addCustomerName"> <font color='red'>*</font>金额：
								</label>
								<div class="col-sm-7">
									<input type="text" id="amount" name="amount" class="form-control"/>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button id="nyGainBtn" type="submit" class="btn btn-sm btn-primary" data-loading-text="保存中...">
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