<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../../commons/taglibs.jsp"%>

<div class="modal fade" id="other_income_common_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="false">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<form id="other_income_common_form" class="form-horizontal">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h3 class="modal-title blue"></h3>
				</div>
				<div class="modal-body">

					<!-- 隐藏域 -->
					<input type="hidden" name="id">
					<input type="hidden" name="projectId" value="${vo.projectId }">
					<input type="hidden" name="type">
					
					<div class="form-group">
						<label class="col-xs-12 col-sm-2 control-label no-padding-right">
							时间：
						</label>
						<div class="col-xs-12 col-sm-3">
							<span class="block input-icon input-icon-right">
								<input type="text" class="form-control" name="time" data-date-format="yyyy-mm-dd">
							</span>
						</div>
						<div class="help-block col-xs-12 col-sm-reset inline"></div>
	
						<label class="col-xs-12 col-sm-2 control-label no-padding-right">
							金额：
						</label>
						<div class="col-xs-12 col-sm-3">
							<span class="block input-icon input-icon-right">
								<input type="text" class="form-control" name="amount">
							</span>
						</div>
						<div class="help-block col-xs-12 col-sm-reset inline"></div>
					</div>
					
					<div class="form-group">
						<label class="col-xs-12 col-sm-2 control-label no-padding-right">
							类型：
						</label>
						<div class="col-xs-12 col-sm-3">
							<span class="block input-icon input-icon-right">
								<select name="incomeCostType" class="form-control">
									<option value="1">收入</option>
									<option value="2">支出</option>
								</select>
							</span>
						</div>
						<div class="help-block col-xs-12 col-sm-reset inline"></div>
					</div>
					
					<div class="form-group">
						<label class="col-xs-12 col-sm-2 control-label no-padding-right">
							内容说明：
						</label>
						<div class="col-xs-12 col-sm-3">
							<span class="block input-icon input-icon-right">
								<textarea rows="3" cols="77" name="content"></textarea>
							</span>
						</div>
						<div class="help-block col-xs-12 col-sm-reset inline"></div>
					</div>

				</div>
				<div class="modal-footer">
					<button type="submit" class="btn btn-primary">
						<i class="ace-icon fa fa-floppy-o"></i> 保存
					</button>
					<button type="button" class="btn btn-warning" data-dismiss="modal">取消</button>
				</div>
			</form>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal -->
</div>