<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../../commons/taglibs.jsp"%>
<jsp:include page="nd_otherfn.jsp"></jsp:include>
<div class="row">
	<div class="col-md-12">
		<h4 class="blue">年家庭生活消费（单位：元）</h4>
		
		<form class="form-horizontal" id="family_consume_form">
		
			<!-- 隐藏域 -->
			<input type="hidden" name="id">
			<input type="hidden" name="projectId" value="${vo.projectId }">
			<input type="hidden" name="type" value="2">
		
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
						<input type="text" class="form-control change_familyConsumeTotal" name="tuition">
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
						<input type="text" class="form-control" name="interestCost">
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
			
			<div class="form-group">
				<label class="col-xs-12 col-sm-2 control-label no-padding-right">
					过去12个月债务变化说明：
				</label>
				<div class="col-xs-12 col-sm-3">
					<span class="block input-icon input-icon-right">
						<textarea rows="3" cols="102" name="debtChangeExplain"></textarea>
					</span>
				</div>
				<div class="help-block col-xs-12 col-sm-reset inline"></div>
			</div>
			
			<hr>
			
			<c:if test="${type != 'check' }">
			<span class="col-xs-12 col-sm-6 control-label no-padding-right">
				<button type="submit" class="btn btn-primary" data-loading-text="正在提交...">
					<i class="ace-icon fa fa-floppy-o"></i>保存
				</button>
			</span>
			</c:if>			
		</form>
	</div>
</div>