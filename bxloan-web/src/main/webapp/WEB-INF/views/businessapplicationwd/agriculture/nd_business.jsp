<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../../commons/taglibs.jsp"%>

<div class="row nd_business">
	<div class="col-md-12">
		<form id="industry_biz_form" class="form-horizontal">
		
			<!-- 隐藏域 -->
			<input type="hidden" name="industryBizId">
			<input type="hidden" name="transportId">
			<input type="hidden" name="projectId" value="${vo.projectId }">
		
			<div class="form-group" style="margin-left: -9%;">
				<label class="col-xs-12 col-sm-2 control-label no-padding-right">所处行业:</label>
				<div class="col-xs-12 col-sm-10">
					<span class="block input-icon input-icon-right">
						<dd:checkbox codeType="AgricultureIndustry" cbName="industry" aceStyle="true"/>
					</span>
				</div>
				<div class="help-block col-xs-12 col-sm-reset inline"></div>
			</div>
			
			<div class="form-group">
				<label class="col-xs-12 col-sm-1 control-label no-padding-right">主要经营项目:</label>
				<div class="col-xs-12 col-sm-2">
					<span class="block input-icon input-icon-right">
						<input type="text" class="form-control" name="primaryProject">
					</span>
				</div>
				<div class="help-block col-xs-12 col-sm-reset inline"></div>
				
				<label class="col-xs-12 col-sm-2 control-label no-padding-right">存在淡旺季:</label>
				<div class="col-xs-12 col-sm-2">
					<span class="block input-icon input-icon-right">
						<select name="slackPeakSeason" class="form-control">
							<dd:options codeType="CommonWhether"/>
						</select>
					</span>
				</div>
				<div class="help-block col-xs-12 col-sm-reset inline"></div>
				
				<label class="col-xs-12 col-sm-2 control-label no-padding-right">需要固定场所:</label>
				<div class="col-xs-12 col-sm-2">
					<span class="block input-icon input-icon-right">
						<select name="needFixedPlace" class="form-control">
							<dd:options codeType="CommonWhether"/>
						</select>
					</span>
				</div>
				<div class="help-block col-xs-12 col-sm-reset inline"></div>
			</div>
			
			<h5 class="blue">自有场所</h5>
			<div class="form-group">
				<label class="col-xs-12 col-sm-1 control-label no-padding-right">面积/㎡：</label>
				<div class="col-xs-12 col-sm-2">
					<span class="block input-icon input-icon-right">
						<input type="text" class="form-control" name="areaFreedomPlace">
					</span>
				</div>
				<div class="help-block col-xs-12 col-sm-reset inline"></div>
				
				<label class="col-xs-12 col-sm-2 control-label no-padding-right">购买/修建时间：</label>
				<div class="col-xs-12 col-sm-2">
					<span class="block input-icon input-icon-right">
						<input type="text" class="form-control" name="boughtBuiltDate" data-date-format="yyyy-mm-dd">
					</span>
				</div>
				<div class="help-block col-xs-12 col-sm-reset inline"></div>
				
				<label class="col-xs-12 col-sm-2 control-label no-padding-right">购买/建造价值：</label>
				<div class="col-xs-12 col-sm-2">
					<span class="block input-icon input-icon-right">
						<input type="text" class="form-control" name="boughtBuiltValue">
					</span>
				</div>
				<div class="help-block col-xs-12 col-sm-reset inline"></div>
			</div>
			
			<h5 class="blue">租赁场所</h5>
			<div class="form-group">
				<label class="col-xs-12 col-sm-1 control-label no-padding-right">面积/㎡：</label>
				<div class="col-xs-12 col-sm-2">
					<span class="block input-icon input-icon-right">
						<input type="text" class="form-control" name="areaRentPlace">
					</span>
				</div>
				<div class="help-block col-xs-12 col-sm-reset inline"></div>
				
				<label class="col-xs-12 col-sm-2 control-label no-padding-right">租赁起止期限：</label>
				<div class="col-xs-12 col-sm-2">
					<span class="block input-icon input-icon-right">
						<input type="text" class="form-control" name="rentDate">
					</span>
				</div>
				<div class="help-block col-xs-12 col-sm-reset inline"></div>
			</div>
			
			<div id="faq-list-1" class="panel-group accordion-style1 accordion-style2">
				<div class="panel panel-default">
					<div class="panel-heading">
						<a href="#faq-1-1" data-parent="#faq-list-1" data-toggle="collapse" class="accordion-toggle collapsed">
							<i class="pull-right ace-icon fa fa-chevron-left" data-icon-hide="ace-icon fa fa-chevron-down" data-icon-show="ace-icon fa fa-chevron-left"></i>
							<i class="ace-icon fa fa-car bigger-130"></i>
							&nbsp; 运输业
						</a>
					</div>
			
					<div class="panel-collapse collapse" id="faq-1-1" style="height: 0px;">
						<div class="panel-body">
							<div class="form-group col-md-4">
								<label class="col-xs-12 col-sm-6 control-label no-padding-right">经营起始年月：
								</label>
								<div class="col-xs-12 col-sm-6">
									<input type="text" class="form-control input-sm" name="businessStartDate" data-date-format="yyyy-mm-dd"/>
								</div>
							</div>
							<div class="form-group col-md-4">
								<label class="col-xs-12 col-sm-6 control-label no-padding-right">盈利模式：
								</label>
								<div class="col-xs-12 col-sm-6">
									<select name="profitMode" class="form-control input-sm">
										<dd:options codeType="ProfitMode"/>
									</select>
								</div>
							</div>
							<div class="form-group col-md-4">
								<label class="col-xs-12 col-sm-6 control-label no-padding-right">车辆数量：
								</label>
								<div class="col-xs-12 col-sm-6">
									<input type="text" class="form-control input-sm" name="carsNum"/>
								</div>
							</div>
							<div class="form-group col-md-4">
								<label class="col-xs-12 col-sm-6 control-label no-padding-right">运输内容：
								</label>
								<div class="col-xs-12 col-sm-6">
									<input type="text" class="form-control input-sm" name="transportContent"/>
								</div>
							</div>
							<div class="form-group col-md-4">
								<label class="col-xs-12 col-sm-6 control-label no-padding-right">车辆类型：
								</label>
								<div class="col-xs-12 col-sm-6">
									<input type="text" class="form-control input-sm" name="carsType"/>
								</div>
							</div>
							<div class="form-group col-md-4">
								<label class="col-xs-12 col-sm-6 control-label no-padding-right">启动资金：
								</label>
								<div class="col-xs-12 col-sm-6">
									<input type="text" class="form-control input-sm" name="initialCapital"/>
								</div>
							</div>
							<div class="form-group col-md-4">
								<label class="col-xs-12 col-sm-6 control-label no-padding-right">车辆保险：
								</label>
								<div class="col-xs-12 col-sm-6">
									<select name="carsInsurance" class="form-control input-sm">
										<dd:options codeType="CarsInsurance"/>
									</select>
								</div>
							</div>
							<div class="form-group col-md-4">
								<label class="col-xs-12 col-sm-6 control-label no-padding-right">结算方式：
								</label>
								<div class="col-xs-12 col-sm-6">
									<input type="text" class="form-control input-sm" name="settlementMode"/>
								</div>
							</div>
							<div class="form-group col-md-4">
								<label class="col-xs-12 col-sm-6 control-label no-padding-right">路线：
								</label>
								<div class="col-xs-12 col-sm-6">
									<input type="text" class="form-control input-sm" name="route"/>
								</div>
							</div>
							<div class="form-group col-md-4">
								<label class="col-xs-12 col-sm-6 control-label no-padding-right">合伙经营：
								</label>
								<div class="col-xs-12 col-sm-6">
									<input type="text" class="form-control input-sm" name="pool"/>
								</div>
							</div>
							<div class="form-group col-md-4">
								<label class="col-xs-12 col-sm-6 control-label no-padding-right">客户占股比例：
								</label>
								<div class="col-xs-12 col-sm-6">
									<input type="text" class="form-control input-sm" name="customerPercentage"/>
								</div>
							</div>
							<div class="form-group col-md-4">
								<label class="col-xs-12 col-sm-6 control-label no-padding-right">结算周期：
								</label>
								<div class="col-xs-12 col-sm-6">
									<input type="text" class="form-control input-sm" name="settlementPeriod"/>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</form>	
			<div id="" class="panel-group accordion-style1 accordion-style2">
				<div class="panel panel-default">
					<div class="panel-heading">
						<a href="#faq-1-2" data-parent="#faq-list-1" data-toggle="collapse" class="accordion-toggle collapsed">
							<i class="ace-icon fa fa-chevron-left pull-right" data-icon-hide="ace-icon fa fa-chevron-down" data-icon-show="ace-icon fa fa-chevron-left"></i>
							<i class="ace-icon fa fa-sort-amount-desc"></i>
							&nbsp; 其他工商收入 
						</a>
					</div>
			
					<div class="panel-collapse collapse" id="faq-1-2">
						<div class="panel-body">
							<c:if test="${type != 'check' }">
								<a id="create_other_income" title="新增" href="javascript:void(0)">
									<button class="btn btn-xs btn-success" style="float:right;">
										<i class="ace-icon fa fa-plus white"></i>
									</button>
								</a>
							</c:if>
							<table id="other_biz_income_table" class="table table-striped table-hover">
								<thead>
									<tr>
										<th>经营起始年月</th>
										<th>启动资金</th>
										<th>经营范围</th>
										<th>结算周期</th>
										<th>客户占股比例</th>
										<th>年固定成本合计</th>
										<th>年度净利润合计</th>
										<th>操作</th>
									</tr>
								</thead>
							</table>
						</div>
					</div>
				</div>
			
				<div class="panel panel-default">
					<div class="panel-heading">
						<a href="#faq-1-3" data-parent="#faq-list-1" data-toggle="collapse"
							class="accordion-toggle collapsed"> <i
							class="ace-icon fa fa-chevron-left pull-right"
							data-icon-hide="ace-icon fa fa-chevron-down"
							data-icon-show="ace-icon fa fa-chevron-left"></i> <i
							class="ace-icon fa fa-credit-card bigger-130"></i> &nbsp;
							库存情况 </a>
					</div>
			
					<div class="panel-collapse collapse" id="faq-1-3">
						<div class="panel-body">
							<c:if test="${type != 'check' }">
								<a id="create_stock" title="新增" href="javascript:void(0)">
									<button class="btn btn-xs btn-success" style="float:right;">
									  <i class="ace-icon fa fa-plus white"></i>
									</button>
								</a>
							</c:if>
							<table id="stock_table" class="table table-striped table-hover">
								<thead>
									<tr>
										<th>存货类别</th>
										<th>名称</th>
										<th>存货单价</th>
										<th>存货计量单位</th>
										<th>库存数量</th>
										<th>存量价值（元）</th>
										<th>操作</th>
									</tr>
								</thead>
							</table>
						</div>
					</div>
				</div>
			</div>
			
			<hr>
			
			<c:if test="${type != 'check' }">
				<span class="col-xs-12 col-sm-6 control-label no-padding-right">
					<button type="submit" class="btn btn-primary" id="save_IndustryBiz_BasicInfo">
						<i class="ace-icon fa fa-floppy-o"></i> 保存
					</button>
				</span>
			</c:if>
			
		
	</div>

</div>

