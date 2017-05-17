<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../../commons/taglibs.jsp"%>
<div class="row">
	<div class="col-md-12">
		<form class="form-horizontal" id="family_consume_agr_form">
		
			<!-- 隐藏域 -->
			<input type="hidden" name="id">
			<input type="hidden" name="projectId" value="${vo.projectId }">
			<input type="hidden" name="type" value="1">
		
			<div class="form-group">
				<label class="col-xs-12 col-sm-2 control-label no-padding-right">
					日常生活支出：
				</label>
				<div class="col-xs-12 col-sm-3">
					<span class="block input-icon input-icon-right">
						<input type="text" class="form-control change_familyConsumeTotal" name="lifeConsume">
					</span>
				</div>
				<div class="help-block col-xs-12 col-sm-reset inline"></div>

				<label class="col-xs-12 col-sm-2 control-label no-padding-right">
					学杂费：
				</label>
				<div class="col-xs-12 col-sm-3">
					<span class="block input-icon input-icon-right">
						<div class="input-group">
							<input type="text" class="form-control change_familyConsumeTotal" name="tuition">
							 <div class="input-group-btn">
							 	<button role="tuition_detail" type="button" class="btn btn-sm btn-yellow">明细</button>
							 </div>
						</div>
					</span>
				</div>
				<div class="help-block col-xs-12 col-sm-reset inline"></div>
			</div>
			
			<div class="form-group">
				<label class="col-xs-12 col-sm-2 control-label no-padding-right">
					医疗：
				</label>
				<div class="col-xs-12 col-sm-3">
					<span class="block input-icon input-icon-right">
						<input type="text" class="form-control change_familyConsumeTotal" name="medical">
					</span>
				</div>
				<div class="help-block col-xs-12 col-sm-reset inline"></div>

				<label class="col-xs-12 col-sm-2 control-label no-padding-right">
					保险：
				</label>
				<div class="col-xs-12 col-sm-3">
					<span class="block input-icon input-icon-right">
						<input type="text" class="form-control change_familyConsumeTotal" name="insurance">
					</span>
				</div>
				<div class="help-block col-xs-12 col-sm-reset inline"></div>
			</div>
			
			<div class="form-group">
				<label class="col-xs-12 col-sm-2 control-label no-padding-right">
					其他：
				</label>
				<div class="col-xs-12 col-sm-3">
					<span class="block input-icon input-icon-right">
						<input type="text" class="form-control change_familyConsumeTotal" name="others1">
					</span>
				</div>
				<div class="help-block col-xs-12 col-sm-reset inline"></div>

				<label class="col-xs-12 col-sm-2 control-label no-padding-right">
					其他：
				</label>
				<div class="col-xs-12 col-sm-3">
					<span class="block input-icon input-icon-right">
						<input type="text" class="form-control change_familyConsumeTotal" name="others2">
					</span>
				</div>
				<div class="help-block col-xs-12 col-sm-reset inline"></div>
			</div>
			
			<div class="form-group">
				<label class="col-xs-12 col-sm-2 control-label no-padding-right">
					其他：
				</label>
				<div class="col-xs-12 col-sm-3">
					<span class="block input-icon input-icon-right">
						<input type="text" class="form-control change_familyConsumeTotal" name="others3">
					</span>
				</div>
				<div class="help-block col-xs-12 col-sm-reset inline"></div>

				<label class="col-xs-12 col-sm-2 control-label no-padding-right">
					总家庭支出：
				</label>
				<div class="col-xs-12 col-sm-3">
					<span class="block input-icon input-icon-right">
						<input type="text" class="form-control" name="familyConsumeTotal" readonly="readonly">
					</span>
				</div>
				<div class="help-block col-xs-12 col-sm-reset inline"></div>
			</div>
			
			<div class="form-group">
				<label class="col-xs-12 col-sm-2 control-label no-padding-right">
					年银行利息支出：
				</label>
				<div class="col-xs-12 col-sm-3">
					<span class="block input-icon input-icon-right">
						<div class="input-group">
						<input type="text" class="form-control" name="interestCost">
							 <div class="input-group-btn">
							 	<button role="loanRepaymentDetail" type="button" class="btn btn-sm btn-yellow">明细</button>
							 </div>
						 </div>
					</span>
				</div>
				<div class="help-block col-xs-12 col-sm-reset inline"></div>

				<label class="col-xs-12 col-sm-2 control-label no-padding-right">
					每月银行还款本息支出：
				</label>
				<div class="col-xs-12 col-sm-3">
					<span class="block input-icon input-icon-right">
						<input type="text" class="form-control" name="repaymentCost">
					</span>
				</div>
				<div class="help-block col-xs-12 col-sm-reset inline"></div>
			</div>
			
			<div class="form-group">
				<label class="col-xs-12 col-sm-2 control-label no-padding-right">
					备注：
				</label>
				<div class="col-xs-12 col-sm-3">
					<span class="block input-icon input-icon-right">
						<textarea rows="3" cols="102" name="remarks"></textarea>
					</span>
				</div>
				<div class="help-block col-xs-12 col-sm-reset inline"></div>
			</div>
			
			<!-- <div class="form-group">
				<label class="col-xs-12 col-sm-2 control-label no-padding-right">
					过去12个月债务变化说明：
				</label>
				<div class="col-xs-12 col-sm-3">
					<span class="block input-icon input-icon-right">
						<textarea rows="3" cols="102" name="debtChangeExplain"></textarea>
					</span>
				</div>
				<div class="help-block col-xs-12 col-sm-reset inline"></div>
			</div> -->
			
			<hr>
			
			<c:if test="${type != 'check' }">
				<span class="col-xs-12 col-sm-6 control-label no-padding-right">
					<button type="submit" class="btn btn-primary" data-loading-text="正在提交...">
						<i class="ace-icon fa fa-floppy-o"></i> 保存
					</button>
				</span>
			</c:if>
		</form>
	</div>
</div>
<!-- 学费明细 -->
<div id="modal-formTuitionDetail" class="modal fade" tabindex="-1" role="basic"
		aria-hidden="true" data-backdrop="static">
		<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
				        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				        <h4 class="modal-title blue bigger">学杂费明细</h4>
				      </div>
				      <div class="modal-body">
				      		<div class="pull-right">
				      			<c:if test="${isEdit }">
					      			<button role="addTuitionDetail" type="button" class="btn btn-xs btn-success">
										<i class="ace-icon fa fa-plus">
										</i>
									</button>
								</c:if>
				      		</div>
				      	<table class="table table-striped table-hover dataTable"
							style="white-space: nowrap;">
							<thead>
								<tr>
									<th class="sorting_disabled">序号</th>
									<th class="sorting_disabled">年月</th>
									<th class="sorting_disabled">金额（元）</th>
									<th class="sorting_disabled">备注</th>
									<th class="sorting_disabled" width="30px">操作</th>
								</tr>
							</thead>
							<tbody>
							</tbody>
							<tfoot>
								<tr role="hj"><td></td><td>合计（元）</td><td class="income-sum">0</td><td></td></tr>
							</tfoot>
						</table>
				      </div>
				      <div class="modal-footer">
				        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				      </div>
				</div>
		</div>
</div>
<div id="modal-formLoanRepaymentDetail" class="modal fade" tabindex="-1" role="basic"
		aria-hidden="true" data-backdrop="static">
		<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
				        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				        <h4 class="modal-title blue bigger">其他贷款还款明细</h4>
				      </div>
				      <div class="modal-body">
				      		<div class="pull-right">
				      			<c:if test="${isEdit }">
					      			<button role="addLoanRepaymentDetail" type="button" class="btn btn-xs btn-success">
										<i class="ace-icon fa fa-plus">
										</i>
									</button>
								</c:if>
				      		</div>
				      	<table class="table table-striped table-hover dataTable"
							style="white-space: nowrap;">
							<thead>
								<tr>
									<th class="sorting_disabled">序号</th>
									<th class="sorting_disabled">年月</th>
									<th class="sorting_disabled">金额（元）</th>
									<th class="sorting_disabled">备注</th>
									<th class="sorting_disabled" width="30px">操作</th>
								</tr>
							</thead>
							<tbody>
							</tbody>
							<tfoot>
								<tr role="hj"><td></td><td>合计（元）</td><td class="income-sum">0</td><td></td></tr>
							</tfoot>
						</table>
				      </div>
				      <div class="modal-footer">
				        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				      </div>
				</div>
		</div>
</div>

