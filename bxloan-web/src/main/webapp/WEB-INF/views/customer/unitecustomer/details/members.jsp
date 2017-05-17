<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
%>
	<%@include file="../../../../commons/taglibs.jsp" %>
		<h4 class="blue">
			<!-- 联保体成员信息 -->
		</h4>
		<div id="faq-list-2" class="panel-group accordion-style1 accordion-style2">
			<form class="form-horizontal" id="form-members">
				<!-- 成员列表 -->
				<!-- <h4 class="blue">
				成员列表
				</h4> -->
				<div>
					<table id="tbUniteMg" class="table table-striped table-hover" style="width: 100%!important;">
						<thead>
							<tr>
								<th>
									选择
								</th>
								<th>
									联保体成员客户编号
								</th>
								<th>
									联保体成员名称
								</th>
								<th>
									成员状态
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
				<div class="col-sm-12 widget-container-col ui-sortable">
					<div class="widget-box transparent" id="widget-member" style="display: none;">
						<div class="widget-header">
							<h4 class="widget-title lighter">
								联保体成员
							</h4>
							<div class="widget-toolbar no-border">
								<a href="#" data-action="collapse">
									<i class="ace-icon fa fa-chevron-up">
									</i>
								</a>
								<a href="#" data-action="close">
									<i class="ace-icon fa fa-times">
									</i>
								</a>
							</div>
						</div>
						<div class="widget-body">
							<div class="widget-main  no-padding-left ">
									<div class="form-group">
										<label class="control-label col-sm-3">
											联保体成员客户编号：
										</label>
										<div class="col-sm-3">
										<div class="input-group">
											<input type="text" name="meb_customerNum" class="form-control required bgwhite" readonly="readonly" style="background-color:#FFF!important"/>
											<span class="input-group-btn">
											<button role="customer_btn" class="btn btn-sm btn-yellow"
											type="button">
											<i class="ace-icon fa fa-eye"></i>
											</button> 
											</span>
											<input type="hidden" name="meb_customerId" class="form-control required " />
											<input type="hidden" name="meb_id" class="form-control required " />
										</div>
										</div>
										<label class="control-label col-sm-3">
											联保体成员客户名称：
										</label>
										<div class="col-sm-3">
											<input type="text" name="meb_customerName" class="form-control required" readonly="readonly" style="background-color:#FFF!important"/>
										</div>
									</div>
							<div class="wizard-actions" style=" text-align:center;">
							<span id="member_btnSpan">
								<button class="btn btn-sm btn-primary" id="saveMember" type="button" data-action="" data-loading-text="正在保存">
									<i class="ace-icon fa fa-floppy-o">
									</i>
									保存
								</button>
							</span>
						</div>
							</div>
						</div>
					</div>
				</div>
			</form>
			<!-- step pane -->
			<!-- step content -->
		</div>