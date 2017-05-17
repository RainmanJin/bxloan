<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../../../commons/taglibs.jsp"%>

<div class="col-md-12 widget-container-col ui-sortable">
	<div class="widget-box ui-sortable-handle"
		style="opacity: 1; z-index: 0;">
		<div class="widget-header">
			<h4 class="blue widget-title">生产区域信息</h4>
			<div class="widget-toolbar">
				<c:if test="${type != 'check' }">
					<a href="javascript:void(0);" role="addFnProductArea" title="新增"><i
						id="news3" data-seq="addNew" class="ace-icon fa fa-plus"></i></a> &nbsp; <a
						href="javascript:void(0);" title="折叠|展开" data-action="collapse"><i
						class="ace-icon fa fa-chevron-up bigger-130"></i></a>
				</c:if>
			</div>
		</div>

		<div class="widget-body">
			<div class="row">
				<div class="col-md-12">
					<table id="produceTbl" class="table table-striped table-hover">
						<thead>
							<tr>
								<th>地点</th>
								<th>面积</th>
								<th>单位</th>
								<th>生产区域性质</th>
								<th>租金（元/年）</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>

<jsp:include page="nd_agro_plant_cultivate.jsp"></jsp:include>
		
<div id="cultivateModel" class="modal fade" data-backdrop="static" tabindex="-1">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title blue">种植业</h4>
			</div>

			<div class="modal-body">
				<form id="repayPlanForm" class="form-horizontal">
					<div class="modal-body">
						<div class="row">
							<div class="col-xs-12">
								<div class="form-group">
									<label class="col-xs-12 col-sm-2 control-label no-padding-right">
										<font color='red'> * </font>从业年限：</label>
									<div class="col-xs-12 col-sm-3">
										<span class="block input-icon input-icon-right"> <input
											type="text" class="form-control required" id="cbcustomerName"
											name="customerName"> </span>
									</div>
									<div class="help-block col-xs-12 col-sm-reset inline"></div>
			
									<label class="col-xs-12 col-sm-3 control-label no-padding-right">
										<font color='red'> * </font>启动资金：</label>
									<div class="col-xs-12 col-sm-3">
										<span class="block input-icon input-icon-right"> <input
											type="text" class="form-control required isIDcard"
											id="cbcertificateNum" name="certificateNum"> </span>
									</div>
									<div class="help-block col-xs-12 col-sm-reset inline"></div>
								</div>
								
								<div class="form-group">
									<label class="col-xs-12 col-sm-2 control-label no-padding-right">
										<font color='red'> * </font>种植内容：</label>
									<div class="col-xs-12 col-sm-3">
										<span class="block input-icon input-icon-right"> <input
											type="text" class="form-control required" id="cbcustomerName"
											name="customerName"> </span>
									</div>
									<div class="help-block col-xs-12 col-sm-reset inline"></div>
			
									<label class="col-xs-12 col-sm-3 control-label no-padding-right">
										<font color='red'> * </font>种植面积：</label>
									<div class="col-xs-12 col-sm-3">
										<span class="block input-icon input-icon-right"> <input
											type="text" class="form-control required isIDcard"
											id="cbcertificateNum" name="certificateNum"> </span>
									</div>
									<div class="help-block col-xs-12 col-sm-reset inline"></div>
								</div>
								
								<div class="form-group">
									<label class="col-xs-12 col-sm-2 control-label no-padding-right">
										<font color='red'> * </font>年销售收入：</label>
									<div class="col-xs-12 col-sm-3">
										<span class="block input-icon input-icon-right"> <input
											type="text" class="form-control required" id="cbcustomerName"
											name="customerName"> </span>
									</div>
									<div class="help-block col-xs-12 col-sm-reset inline"></div>
			
									<label class="col-xs-12 col-sm-3 control-label no-padding-right">
										<font color='red'> * </font>总成本：</label>
									<div class="col-xs-12 col-sm-3">
										<span class="block input-icon input-icon-right"> <input
											type="text" class="form-control required isIDcard"
											id="cbcertificateNum" name="certificateNum"> </span>
									</div>
									<div class="help-block col-xs-12 col-sm-reset inline"></div>
								</div>
								
								<div class="form-group">
									<label class="col-xs-12 col-sm-2 control-label no-padding-right">
										<font color='red'> * </font>调查时作物存量价值：</label>
									<div class="col-xs-12 col-sm-3">
										<span class="block input-icon input-icon-right"> <input
											type="text" class="form-control required" id="cbcustomerName"
											name="customerName"> </span>
									</div>
									<div class="help-block col-xs-12 col-sm-reset inline"></div>
			
									<label class="col-xs-12 col-sm-3 control-label no-padding-right">
										<font color='red'> * </font>调查时农资存量价值：</label>
									<div class="col-xs-12 col-sm-3">
										<span class="block input-icon input-icon-right"> <input
											type="text" class="form-control required isIDcard"
											id="cbcertificateNum" name="certificateNum"> </span>
									</div>
									<div class="help-block col-xs-12 col-sm-reset inline"></div>
								</div>

							</div>
						</div>
					</div>
					<hr>
					<div class="row" align="right">
						<button class="btn btn-primary">保存</button>
						<button class="btn btn-warning" data-dismiss="modal" type="button">
							<i class="ace-icon fa fa-times"></i>取消
						</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>

<div id="breedModel" class="modal fade" data-backdrop="static" tabindex="-1">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title blue">种植业</h4>
			</div>

			<div class="modal-body">
				<form id="repayPlanForm" class="form-horizontal">
					<div class="modal-body">
						<div class="row">
							<div class="col-xs-12">
								<div class="form-group">
									<label class="col-xs-12 col-sm-2 control-label no-padding-right">
										<font color='red'> * </font>从业年限：</label>
									<div class="col-xs-12 col-sm-3">
										<span class="block input-icon input-icon-right"> <input
											type="text" class="form-control required" id="cbcustomerName"
											name="customerName"> </span>
									</div>
									<div class="help-block col-xs-12 col-sm-reset inline"></div>
			
									<label class="col-xs-12 col-sm-3 control-label no-padding-right">
										<font color='red'> * </font>启动资金：</label>
									<div class="col-xs-12 col-sm-3">
										<span class="block input-icon input-icon-right"> <input
											type="text" class="form-control required isIDcard"
											id="cbcertificateNum" name="certificateNum"> </span>
									</div>
									<div class="help-block col-xs-12 col-sm-reset inline"></div>
								</div>
								
								<div class="form-group">
									<label class="col-xs-12 col-sm-2 control-label no-padding-right">
										<font color='red'> * </font>养殖内容：</label>
									<div class="col-xs-12 col-sm-3">
										<span class="block input-icon input-icon-right"> <input
											type="text" class="form-control required" id="cbcustomerName"
											name="customerName"> </span>
									</div>
									<div class="help-block col-xs-12 col-sm-reset inline"></div>
			
									<label class="col-xs-12 col-sm-3 control-label no-padding-right">
										<font color='red'> * </font>养殖方式：</label>
									<div class="col-xs-12 col-sm-3">
										<span class="block input-icon input-icon-right"> <input
											type="text" class="form-control required isIDcard"
											id="cbcertificateNum" name="certificateNum"> </span>
									</div>
									<div class="help-block col-xs-12 col-sm-reset inline"></div>
								</div>
								
								<div class="form-group">
									<label class="col-xs-12 col-sm-2 control-label no-padding-right">
										<font color='red'> * </font>年出栏量/养殖规模：</label>
									<div class="col-xs-12 col-sm-3">
										<span class="block input-icon input-icon-right"> <input
											type="text" class="form-control required" id="cbcustomerName"
											name="customerName"> </span>
									</div>
									<div class="help-block col-xs-12 col-sm-reset inline"></div>
			
									<label class="col-xs-12 col-sm-3 control-label no-padding-right">
										<font color='red'> * </font>年收入合计：</label>
									<div class="col-xs-12 col-sm-3">
										<span class="block input-icon input-icon-right"> <input
											type="text" class="form-control required isIDcard"
											id="cbcertificateNum" name="certificateNum"> </span>
									</div>
									<div class="help-block col-xs-12 col-sm-reset inline"></div>
								</div>
								
								<div class="form-group">
									<label class="col-xs-12 col-sm-2 control-label no-padding-right">
										<font color='red'> * </font>年成本合计：</label>
									<div class="col-xs-12 col-sm-3">
										<span class="block input-icon input-icon-right"> <input
											type="text" class="form-control required" id="cbcustomerName"
											name="customerName"> </span>
									</div>
									<div class="help-block col-xs-12 col-sm-reset inline"></div>
			
									<label class="col-xs-12 col-sm-3 control-label no-padding-right">
										<font color='red'> * </font>调查时养殖规模：</label>
									<div class="col-xs-12 col-sm-3">
										<span class="block input-icon input-icon-right"> <input
											type="text" class="form-control required isIDcard"
											id="cbcertificateNum" name="certificateNum"> </span>
									</div>
									<div class="help-block col-xs-12 col-sm-reset inline"></div>
								</div>
								
								<div class="form-group">
									<label class="col-xs-12 col-sm-2 control-label no-padding-right">
										<font color='red'> * </font>养殖存量价值：</label>
									<div class="col-xs-12 col-sm-3">
										<span class="block input-icon input-icon-right"> <input
											type="text" class="form-control required" id="cbcustomerName"
											name="customerName"> </span>
									</div>
									<div class="help-block col-xs-12 col-sm-reset inline"></div>
			
									<label class="col-xs-12 col-sm-3 control-label no-padding-right">
										<font color='red'> * </font>饲料等存货价值：</label>
									<div class="col-xs-12 col-sm-3">
										<span class="block input-icon input-icon-right"> <input
											type="text" class="form-control required isIDcard"
											id="cbcertificateNum" name="certificateNum"> </span>
									</div>
									<div class="help-block col-xs-12 col-sm-reset inline"></div>
								</div>

							</div>
						</div>
					</div>
					<hr>
					<div class="row" align="right">
						<button class="btn btn-primary">保存</button>
						<button class="btn btn-warning" data-dismiss="modal" type="button">
							<i class="ace-icon fa fa-times"></i>取消
						</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
<!-- ----------------------生产区域信息 ----------------------------------->
<div id="modal-productAreaInfo" class="modal fade" data-backdrop="static" tabindex="-1">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title blue">生产区域信息</h4>
			</div>

			<div class="modal-body">
				<form id="fnProduceAreaForm" action="addFnProduceAreaInfo/save" 
					 method="post" class="form-horizontal">
					<div class="modal-body">
						<div class="row">
							<div class="col-xs-12">
								<!-- 隐藏域 -->
								<input type="hidden" id="form-field-0" name="id" />
								<input type="hidden" name="projectId" value="${vo.projectId }">
								<input type="hidden" id="form-field-type" name="type" value="2"/>
								
								<div class="form-group">
									<label class="col-xs-12 col-sm-4 control-label no-padding-right">
										<font color='red'> * </font>地点：</label>
									<div class="col-xs-12 col-sm-5">
										<span class="block input-icon input-icon-right"> <input
											type="text" class="form-control required" id="location"
											name="location"> </span>
									</div>
									<div class="help-block col-xs-12 col-sm-reset inline"></div>
								</div>
								
								<div class="form-group">
									<label class="col-xs-12 col-sm-4 control-label no-padding-right">
										<font color='red'> * </font>面积：</label>
									<div class="col-xs-12 col-sm-5">
										<span class="block input-icon input-icon-right"> <input
											type="text" class="form-control required" id="area"
											name="area"> </span>
									</div>
									<div class="help-block col-xs-12 col-sm-reset inline"></div>
								</div>
								
								<div class="form-group">
									<label class="col-xs-12 col-sm-4 control-label no-padding-right">
										<font color='red'> * </font>单位：</label>
									<div class="col-xs-12 col-sm-5">
										<span class="block input-icon input-icon-right"> <input
											type="text" class="form-control required" id="unit"
											name="unit"> </span>
									</div>
									<div class="help-block col-xs-12 col-sm-reset inline"></div>
								</div>
								
								<div class="form-group">
									<label class="col-xs-12 col-sm-4 control-label no-padding-right">
										<font color='red'> * </font>生产区域性质：</label>
									<div class="col-xs-12 col-sm-5">
										<span class="block input-icon input-icon-right"> <select
													name="areaProperty" id="areaProperty" class="form-control">
													<dd:options codeType="MyRentType"/>
										</select></span>
									</div>
									<div class="help-block col-xs-12 col-sm-reset inline"></div>
								</div>
	
								<div id="hid" class="form-group">
									<label class="col-xs-12 col-sm-4 control-label no-padding-right">
										<font color='red'> * </font>租金（元/年）：</label>
									<div class="col-xs-12 col-sm-5">
										<span class="block input-icon input-icon-right"> 
										<input  type="text" class="form-control " id="rent"
											name="rent"> </span>
									</div>
									<div class="help-block col-xs-12 col-sm-reset inline"></div>
								</div>

							</div>
						</div>
					</div>
					<hr>
					<div id="submitbtn" class="row" align="right">
						<button type="submit" class="btn btn-primary" data-loading-text="保存中...">保存</button>
						<button class="btn btn-warning" data-dismiss="modal">
							<i class="ace-icon fa fa-times"></i>取消
						</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>