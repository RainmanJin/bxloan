<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../../commons/taglibs.jsp" %>
		<div class="row">
			<div class="col-md-12">
				<div id="faq-list-1" class="panel-group accordion-style1 accordion-style2">
					<div class="panel panel-default" data-objid="01" data-objtype="cultivate">
						<div class="panel-heading">
							<a href="#faq-1-1" data-parent="#faq-list-1" data-toggle="collapse" class="accordion-toggle collapsed">
								<i class="pull-right ace-icon fa fa-chevron-left" data-icon-hide="ace-icon fa fa-chevron-down"
								data-icon-show="ace-icon fa fa-chevron-left">
								</i>
								<i class="ace-icon fa fa-tree bigger-130">
								</i>
								&nbsp; 种植业
							</a>
						</div>
						<div class="panel-collapse collapse" id="faq-1-1" style="height: 0px;">
							<div class="panel-body">
								<jsp:include page="cultivate_n_agr.jsp"></jsp:include>
							</div>
						</div>
					</div>
					<div class="panel panel-default" data-objid="02" data-objtype="breed">
						<div class="panel-heading">
							<a href="#faq-1-2" data-parent="#faq-list-1" data-toggle="collapse" class="accordion-toggle collapsed">
								<i class="ace-icon fa fa-chevron-left pull-right" data-icon-hide="ace-icon fa fa-chevron-down"
								data-icon-show="ace-icon fa fa-chevron-left">
								</i>
								<i class="ace-icon fa fa-sort-amount-desc">
								</i>
								&nbsp; 养殖业
							</a>
						</div>
						<div class="panel-collapse collapse" id="faq-1-2">
							<div class="panel-body">
								<jsp:include page="breed_n_agr.jsp"></jsp:include>
							</div>
						</div>
					</div>
					<div class="panel panel-default" data-objtype="business" data-objid="03">
						<div class="panel-heading">
							<a href="#faq-1-3" data-parent="#faq-list-1" data-toggle="collapse" class="accordion-toggle collapsed">
								<i class="ace-icon fa fa-chevron-left pull-right" data-icon-hide="ace-icon fa fa-chevron-down"
								data-icon-show="ace-icon fa fa-chevron-left">
								</i>
								<i class="ace-icon fa fa-credit-card bigger-130">
								</i>
								&nbsp; 工商业
							</a>
						</div>
						<div class="panel-collapse collapse" id="faq-1-3">
							<div class="panel-body">
								<ul id="n_bizGS_tab" class="nav nav-tabs">
									<li class="active" data-seq="pass">
										<a href="#passBizGs" data-toggle="tab">
											过去12个月全年
										</a>
									</li>
									<li data-seq="future">
										<a href="#futureBizGs" data-toggle="tab">
											未来12个月全年
										</a>
									</li>
									<div style="float:right;">
										<c:if test="${type != 'check' }">
											<a id="create_other_biz_income" title="新增" href="javascript:void(0)">
												<button class="btn btn-xs btn-success">
													<i class="ace-icon fa fa-plus white"></i>
												</button>
											</a>
											</button>
										</c:if>
									</div>
								</ul>
								<div id="n_bizGs_Content" class="tab-content">
									<div class="tab-pane fade in active" id="passBizGs">
										<table id="other_biz_income_table_pass" class="table table-striped table-hover">
											<thead>
												<tr>
													<th>
														类型
													</th>
													<th>
														经营内容
													</th>
													<th>
														经营起始年月
													</th>
													<th>
														经营场所
													</th>
													<th>
														操作
													</th>
												</tr>
											</thead>
										</table>
									</div>
									<div class="tab-pane fade" id="futureBizGs">
										<table id="other_biz_income_table_future" class="table table-striped table-hover">
											<thead>
												<tr>
													<th>
														类型
													</th>
													<th>
														经营内容
													</th>
													<th>
														经营起始年月
													</th>
													<th>
														经营场所
													</th>
													<th>
														操作
													</th>
												</tr>
											</thead>
										</table>
									</div>
								</div>
								<br/>
								<div style="float:right;">
								<c:if test="${type != 'check' }">
									<a id="create_other_income_common" title="新增" href="javascript:void(0)">
										<button class="btn btn-xs btn-success">
											<i class="ace-icon fa fa-plus white"></i>
										</button>
									</a>
								</c:if>
								</div>
								<table id="other_income_common_table" class="table table-striped table-hover">
									<thead>
										<tr>
											<th>
												时间
											</th>
											<th>
												说明
											</th>
											<th>
												类型
											</th>
											<th>
												金额
											</th>
											<th>
												操作
											</th>
										</tr>
									</thead>
								</table>
							</div>
						</div>
					</div>
					<div class="panel panel-default" data-objid="04">
						<div class="panel-heading">
							<a href="#faq-1-4" data-parent="#faq-list-1" data-toggle="collapse" class="accordion-toggle collapsed">
								<i class="ace-icon fa fa-chevron-left pull-right" data-icon-hide="ace-icon fa fa-chevron-down"
								data-icon-show="ace-icon fa fa-chevron-left">
								</i>
								<i class="ace-icon fa fa-credit-card bigger-130">
								</i>
								&nbsp; 其他收入
							</a>
						</div>
						<div class="panel-collapse collapse" id="faq-1-4">
							<div class="panel-body">
								<jsp:include page="nd_otherny.jsp"></jsp:include>
							</div>
						</div>
					</div>
					<div class="panel panel-default">
						<div class="panel-heading">
							<a href="#faq-1-5" data-parent="#faq-list-1" data-toggle="collapse" class="accordion-toggle collapsed">
								<i class="ace-icon fa fa-chevron-left pull-right" data-icon-hide="ace-icon fa fa-chevron-down"
								data-icon-show="ace-icon fa fa-chevron-left">
								</i>
								<i class="ace-icon fa fa-credit-card bigger-130">
								</i>
								&nbsp; 利润分配
							</a>
						</div>
						<div class="panel-collapse collapse" id="faq-1-5">
							<div class="panel-body">
								<jsp:include page="nd_otherpd.jsp"></jsp:include>
							</div>
						</div>
					</div>
					<div class="panel panel-default" data-objid="05">
						<div class="panel-heading">
							<a href="#faq-1-6" data-parent="#faq-list-1" data-toggle="collapse" class="accordion-toggle collapsed">
								<i class="ace-icon fa fa-chevron-left pull-right" data-icon-hide="ace-icon fa fa-chevron-down"
								data-icon-show="ace-icon fa fa-chevron-left">
								</i>
								<i class="ace-icon fa fa-credit-card bigger-130">
								</i>
								&nbsp; 年家庭生活消费（单位：元）
							</a>
						</div>
						<div class="panel-collapse collapse" id="faq-1-6">
							<div class="panel-body">
								<jsp:include page="familyConsume_agr.jsp"></jsp:include>
							</div>
						</div>
					</div>
					<div class="panel panel-default">
						<div class="panel-heading">
							<a href="#faq-1-7" data-parent="#faq-list-1" data-toggle="collapse" class="accordion-toggle collapsed">
								<i class="ace-icon fa fa-chevron-left pull-right" data-icon-hide="ace-icon fa fa-chevron-down"
								data-icon-show="ace-icon fa fa-chevron-left">
								</i>
								<i class="ace-icon fa fa-credit-card bigger-130">
								</i>
								&nbsp; 汇总
							</a>
						</div>
						<div class="panel-collapse collapse" id="faq-1-7">
							<div class="panel-body">
								<jsp:include page="nd_huizong_agr.jsp"></jsp:include>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<jsp:include page="modalNCultivateAndBreed.jsp"></jsp:include>