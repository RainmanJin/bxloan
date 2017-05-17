<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../../commons/taglibs.jsp"%>

<div class="modal fade" id="stock_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="false">
   <div class="modal-dialog modal-lg">
      <div class="modal-content">
	      <form id="stock_form" class="form-horizontal">
	         <div class="modal-header">
	            <button type="button" class="close" 
	               data-dismiss="modal" aria-hidden="true">
	                  &times;
	            </button>
	            <h3 class="modal-title blue">
	              	库存
	            </h3>
	         </div>
	         <div class="modal-body">
	         
	         	<!-- 隐藏域 -->
         		<input type="hidden" name="id">
         		<input type="hidden" name="projectId" value="${vo.projectId }">
	         
	         	<div class="form-group">
					<label class="col-xs-12 col-sm-2 control-label no-padding-right">
						存货类别： </label>
					<div class="col-xs-12 col-sm-4">
						<span class="block input-icon input-icon-right">
							<input type="text" class="form-control" name="type">
						</span>
					</div>
					<div class="help-block col-xs-12 col-sm-reset inline"></div>

					<label class="col-xs-12 col-sm-2 control-label no-padding-right">
						名称： </label>
					<div class="col-xs-12 col-sm-4">
						<span class="block input-icon input-icon-right">
							<input type="text" class="form-control" name="name">
						</span>
					</div>
					<div class="help-block col-xs-12 col-sm-reset inline"></div>
				</div>
				
				<div class="form-group">
					<label class="col-xs-12 col-sm-2 control-label no-padding-right">
						存货单价： </label>
					<div class="col-xs-12 col-sm-4">
						<span class="block input-icon input-icon-right">
							<input type="text" class="form-control" name="price">
						</span>
					</div>
					<div class="help-block col-xs-12 col-sm-reset inline"></div>

					<label class="col-xs-12 col-sm-2 control-label no-padding-right">
						存货计量单位： </label>
					<div class="col-xs-12 col-sm-4">
						<span class="block input-icon input-icon-right">
							<input type="text" class="form-control" name="unit">
						</span>
					</div>
					<div class="help-block col-xs-12 col-sm-reset inline"></div>
				</div>
				
				<div class="form-group">
					<label class="col-xs-12 col-sm-2 control-label no-padding-right">
						存货数量： </label>
					<div class="col-xs-12 col-sm-4">
						<span class="block input-icon input-icon-right">
							<input type="text" class="form-control" name="num">
						</span>
					</div>
					<div class="help-block col-xs-12 col-sm-reset inline"></div>

					<label class="col-xs-12 col-sm-2 control-label no-padding-right">
						存量价值： </label>
					<div class="col-xs-12 col-sm-4">
						<span class="block input-icon input-icon-right">
							<div class="input-group">
								<input type="text" class="form-control" name="total">
								<span class="input-group-addon">元</span>
							</div>
						</span>
					</div>
					<div class="help-block col-xs-12 col-sm-reset inline"></div>
				</div>
				
	         </div>
	         <div class="modal-footer">
	            <button type="submit" class="btn btn-primary">
	              	 <i class="ace-icon fa fa-floppy-o"></i> 保存
	            </button>
	            <button type="button" class="btn btn-warning" data-dismiss="modal">取消
	            </button>
	         </div>
	      </form>
      </div><!-- /.modal-content -->
</div><!-- /.modal -->
	
</div>