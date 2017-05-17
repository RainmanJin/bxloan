<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../../commons/taglibs.jsp" %>
		
		<ul id="n_culitivate_tab" class="nav nav-tabs">
			<li class="active" data-seq = "pass">
				<a href="#passCultivate" data-toggle="tab">
					过去12个月全年
				</a>
			</li>
			<li data-seq = "future">
				<a href="#futureCultivate" data-toggle="tab">
					未来12个月全年
				</a>
			</li>
		<div style="float:right;">
			<c:if test="${type != 'check' }">
				<button id="n_culitivate_add" type="button" class="btn btn-xs btn-success">
					<i class="ace-icon fa fa-plus">
					</i>
				</button>
			</c:if>
		</div>
		</ul>
		<div id="n_culitivate_Content" class="tab-content">
			<div class="tab-pane fade in active" id="passCultivate">
				<table id="n_culitivate_pass_table" class="table table-striped table-hover"
				style="white-space: nowrap;">
					<thead>
						<tr>
							<th>
								占位
							</th>
							<th>
								种植类型
							</th>
							<th>
								种植内容
							</th>
							<th>
								种植规模
							</th>
							<th>
								年总产量
							</th>
							<th>
								家庭消耗量
							</th>
							<th>
								牲畜消耗量
							</th>
							<th>
								销售数量
							</th>
							<th>
								销售单价
							</th>
							<th>
								销售收入合计
							</th>
							<th>
								成本合计
							</th>
							<th>
								调查时作物存量价值
							</th>
							<th>
								调查时农资存量价值
							</th>
							<th>
								操作
							</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
			</div>
			<div class="tab-pane fade" id="futureCultivate">
				<table id="n_culitivate_future_table" class="table table-striped table-hover"
				style="white-space: nowrap;">
					<thead>
						<tr>
							<th>
								占位
							</th>
							<th>
								种植类型
							</th>
							<th>
								种植内容
							</th>
							<th>
								种植规模
							</th>
							<th>
								预计单产产量
							</th>
							<th>
								预计年总产量
							</th>
							<th>
								家庭消耗量
							</th>
							<th>
								牲畜消耗量
							</th>
							<th>
								销售数量
							</th>
							<th>
								销售单价
							</th>
							<th>
								年收入合计
							</th>
							<th>
								预计成本合计
							</th>
							<th>
								预计销售时间
							</th>
							<th>
								操作
							</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
			</div>
		</div>
		
		<br/>
		
		<form class="form-horizontal" id="common_info_form">
			<input type="hidden" name="id">
			<input type="hidden" name="projectId" value="${vo.projectId }">
			<input type="hidden" name="type" value="1">
			<div class="form-group">
				<label class="col-xs-12 col-sm-2 control-label no-padding-right">调查时农资存量价值（元）：</label>
				<div class="col-xs-12 col-sm-3">
					<span class="block input-icon input-icon-right">
						<input type="text" class="form-control change_yearIncomeTotal" name="price1" />
					</span>
				</div>
				<div class="help-block col-xs-12 col-sm-reset inline"></div>
				
				<label class="col-xs-12 col-sm-1 control-label no-padding-right">备注：</label>
				<div class="col-xs-12 col-sm-3">
					<span class="block input-icon input-icon-right">
						<textarea rows="1" cols="60" name="remarks1"></textarea>
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
		
		<div class="col-md-12 widget-container-col ui-sortable">
										<div class="widget-box ui-sortable-handle" style="opacity: 1; z-index: 0;">
											<div class="widget-header">
												<h4 class="blue widget-title">
													成本费用明细
												</h4>
												<div class="widget-toolbar">
													<c:if test="${type != 'check' }">
														<a id="btn-add-n-cultivate-future" data-action="setting" href="javascript:void(0);" title="新增">
															<i class="ace-icon fa fa-plus">
															</i>
														</a> | 
													</c:if>
													<a href="#" data-action="collapse">
														<i class="1 ace-icon fa bigger-125 fa-chevron-up"></i>
													</a>
												</div>
											</div>
											<div class="widget-body">
												<table id="overincome_n_cultivate_tb" class="table table-striped table-hover"
												style="white-space: nowrap;">	
													<thead>
														<tr>
															<th>
																序号
															</th>
															<th>
																时间
															</th>
															<th>
																成本
															</th>
															<th>
																金额
															</th>
															<th width="30px">
																操作
															</th>
														</tr>
													</thead>
													<tbody>
													</tbody>
												</table>
											</div>
										</div>
									</div>