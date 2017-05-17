<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
%>
	<%@include file="../../../commons/taglibs.jsp" %>
		
		<ul id="n_breed_tab" class="nav nav-tabs">
			<li class="active" data-seq = "pass">
				<a href="#passBreed" data-toggle="tab">
					过去12个月全年
				</a>
			</li>
			<li data-seq = "future">
				<a href="#futureBreed" data-toggle="tab">
					未来12个月全年
				</a>
			</li>
			<div style="float:right;">
			<c:if test="${type != 'check' }">
				<button id="n_breed_add" type="button" class="btn btn-xs btn-success">
					<i class="ace-icon fa fa-plus">
					</i>
				</button>
			</c:if>
			</div>
		</ul>
		<div id="n_breed_Content" class="tab-content">
			<div class="tab-pane fade in active" id="passBreed">
				<table id="n_breed_pass_table" class="table table-striped table-hover"
				style="white-space: nowrap;">
					<thead>
						<tr>
							<th>
								占位
							</th>
							<th>
								养殖类型
							</th>
							<th>
								养殖内容
							</th>
							<th>
								养殖方式
							</th>
							<th>
								养殖规模
							</th>
							<th>
								年销售量
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
								存量初始规模
							</th>
							<th>
								养殖存量价值
							</th>
							<th>
								饲料等存货价值
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
			<div class="tab-pane fade" id="futureBreed">
				<table id="n_breed_future_table" class="table table-striped table-hover"
				style="white-space: nowrap;">
					<thead>
						<tr>
							<th>
								占位
							</th>
							<th>
								养殖类型
							</th>
							<th>
								养殖内容
							</th>
							<th>
								养殖方式
							</th>
							<th>
								预计总产量
							</th>
							<th>
								预测销售单价
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
		
		<div class="col-md-12 widget-container-col ui-sortable">
			<div class="widget-box ui-sortable-handle" style="opacity: 1; z-index: 0;">
				<div class="widget-header">
					<h4 class="blue widget-title">
						成本费用明细
					</h4>
					<a href="#" data-action="collapse">
						<i class="1 ace-icon fa bigger-125 fa-chevron-up">
						</i>
					</a>
					<div class="widget-toolbar">
						<c:if test="${type != 'check' }">
							<button id="btn-add-n-breed-future" type="button" class="btn btn-xs btn-success">
								<i class="ace-icon fa fa-plus">
								</i>
							</button>
						</c:if>
					</div>
				</div>
				<div class="widget-body">
					<table id="overincome_n_breed_tb" class="table table-striped table-hover"
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