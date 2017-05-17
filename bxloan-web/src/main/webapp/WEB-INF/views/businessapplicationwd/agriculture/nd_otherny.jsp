<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../../../commons/taglibs.jsp"%>
<!-- ----------------其他收入----------------- -->
<ul id="n_other_tab" class="nav nav-tabs">
	<li class="active" data-seq="pass"><a href="#passOther"
		data-toggle="tab"> 过去12个月全年 </a></li>
	<li data-seq="future"><a href="#futureOther" data-toggle="tab">
			未来12个月全年 </a></li>
	<div style="float:right;">
		<c:if test="${type != 'check' }">
			<a href="javascript:void(0);" role="btnIncome" title="新增">
			 <button class="btn btn-xs btn-success">
			 	<i class="ace-icon fa fa-plus"></i>
			 </button>
			</a>
		</c:if>
	</div>
</ul>
<div id="n_other_Content" class="tab-content">
	<div class="tab-pane fade in active" id="passOther">
		<table id="otherTbl" class="table table-striped table-hover"
			style="white-space: nowrap;">
			<thead>
				<tr>
					<th>时间分类</th>
					<th>收入类型</th>
					<th>年收入(元)</th>
					<th>备注</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody></tbody>
		</table>
	</div>
	<div class="tab-pane fade" id="futureOther">
		<table id="otherTbl_future" class="table table-striped table-hover"
			style="white-space: nowrap;">
			<thead>
				<tr>
					<th>时间分类</th>
					<th>收入类型</th>
					<th>年收入(元)</th>
					<th>备注</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody></tbody>
		</table>
	</div>
</div>
<!-- --------------过去12个月利润----------------- -->
<%-- <div class="col-md-12 widget-container-col ui-sortable">
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
</div> --%>
<!-- ---------------其他收入-------------------- -->
<div id="modal-otherIncome" class="modal" data-backdrop="static"
	tabindex="-1">
	<div class="modal-dialog">
		<div class="modal-content">
			<form id="otherIncomeForm" action="addOtherIncome/save" class="form-horizontal"
				role="form" method="post">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="blue bigger"></h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-xs-12">
							<!--隐藏框区分   save和update -->
							<input type="hidden" id="form-my-007" name="id" />
							<input type="hidden" name="projectId" value="${vo.projectId }">
							<input type="hidden" id="form-field-income" name="type" value="1" />
							<input type="hidden" id="futurePastType" name="futurePastType"/>
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right"
									for="addCustomerName"> <font color='red'>*</font>收入类型：
								</label>
								<div class="col-sm-7">
								    <select name="otherIncomeType" id="otherIncomeType" class="form-control">
											<dd:options codeType="IncomeType"/>
									</select>
								</div>
							</div>
							<div id="others3" class="form-group">
								<label class="col-sm-3 control-label no-padding-right">
									 <font color='red'>*</font>收入名称：
								</label>
								<div class="col-sm-7">
									<input type="text" name="name" id="name" class="form-control"/>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right"
									for="addCustomerName"> <font color='red'>*</font>年收入（元）：
								</label>
								<div class="col-sm-7">
									<input type="text" id="yearIncome" name="yearIncome"
										class="form-control" placeholder="输入名称" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right"
									for="addCustomerName">备注：
								</label>
								<div class="col-sm-7">
									<textarea id="remarks" name="remarks" rows="2" cols="41"></textarea>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button id="submit" type="submit" class="btn btn-sm btn-primary" data-loading-text="保存中...">
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

<!-- ---------------过去12个月利润-------------------- -->
<%-- <div id="modal-nydistribution" class="modal" data-backdrop="static"
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
							<input type="hidden" id="type" name="type" value="2"/>
							
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
</div> --%>