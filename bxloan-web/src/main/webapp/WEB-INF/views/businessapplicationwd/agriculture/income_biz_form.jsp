<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../../commons/taglibs.jsp"%>

<div class="modal fade" id="other_biz_income_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="false">
  <div class="modal-dialog modal-lg">
     <div class="modal-content">
      <form id="other_biz_income_form" class="form-horizontal">
         <div class="modal-header">
            <button type="button" class="close" 
               data-dismiss="modal" aria-hidden="true">
                  &times;
            </button>
            <h3 class="modal-title blue">
              	其他工商收入
            </h3>
         </div>
         <div class="modal-body">
         		
         		<!-- 隐藏域 -->
         		<input type="hidden" name="id">
         		<input type="hidden" name="projectId" value="${vo.projectId }">
         		<input type="hidden" name="type" value="2">
         		
         		<div class="row">
	         		<div class="form-group col-md-4">
						<label class="col-xs-12 col-sm-6 control-label no-padding-right">经营起始年月：
						</label>
						<div class="col-xs-12 col-sm-6">
							<input type="text" class="form-control input-sm" name="businessStartDate" data-date-format="yyyy-mm-dd"/>
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
						<label class="col-xs-12 col-sm-6 control-label no-padding-right">经营范围：
						</label>
						<div class="col-xs-12 col-sm-6">
							<input type="text" class="form-control input-sm" name="businessScope"/>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="form-group col-md-4">
						<label class="col-xs-12 col-sm-6 control-label no-padding-right">规模-面积：
						</label>
						<div class="col-xs-12 col-sm-6">
							<input type="text" class="form-control input-sm" name="scaleArea"/>
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
						<label class="col-xs-12 col-sm-6 control-label no-padding-right">规模-工人：
						</label>
						<div class="col-xs-12 col-sm-6">
							<input type="text" class="form-control input-sm" name="scaleWorker"/>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="form-group col-md-4">
						<label class="col-xs-12 col-sm-6 control-label no-padding-right">结算周期：
						</label>
						<div class="col-xs-12 col-sm-6">
							<input type="text" class="form-control input-sm" name="settlementPeriod"/>
						</div>
					</div>
					<div class="form-group col-md-4">
						<label class="col-xs-12 col-sm-6 control-label no-padding-right">规模-其他：
						</label>
						<div class="col-xs-12 col-sm-6">
							<input type="text" class="form-control input-sm" name="scaleOther"/>
						</div>
					</div>
					<div class="form-group col-md-4">
						<label class="col-xs-12 col-sm-6 control-label no-padding-right">合伙经营：
						</label>
						<div class="col-xs-12 col-sm-6">
							<input type="text" class="form-control input-sm" name="pool"/>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="form-group col-md-4">
						<label class="col-xs-12 col-sm-6 control-label no-padding-right">客户占股比例：
						</label>
						<div class="col-xs-12 col-sm-6">
							<input type="text" class="form-control input-sm" name="customerPercentage"/>
						</div>
					</div>
				</div>
				
				<table style="width: 100%;">
					<tr>
						<td align="center"><h5 class="green">旺季</h5></td>
						<td align="center"><h5 class="green">淡季</h5></td>
					</tr>
				</table>
				<div class="form-group">
					<label class="col-xs-12 col-sm-2 control-label no-padding-right">月数（月）：</label>
					<div class="col-xs-12 col-sm-4">
						<span class="block input-icon input-icon-right">
							<input type="text" class="form-control change_yearIncomeTotal change_yearChangeableCostTotal" name="month_peak"/>
						</span>
					</div>
					<div class="help-block col-xs-12 col-sm-reset inline"></div>
					
					<label class="col-xs-12 col-sm-1 control-label no-padding-right"></label>
					<div class="col-xs-12 col-sm-4">
						<span class="block input-icon input-icon-right">
							<input type="text" class="form-control change_yearIncomeTotal change_yearChangeableCostTotal" name="month_slack"/>
						</span>
					</div>
					<div class="help-block col-xs-12 col-sm-reset inline"></div>
				</div>
				<div class="form-group">
					<label class="col-xs-12 col-sm-2 control-label no-padding-right">每天收入（元）：</label>
					<div class="col-xs-12 col-sm-4">
						<span class="block input-icon input-icon-right">
							<input type="text" class="form-control change_dailyGain_peak" name="dailyIncome_peak"/>
						</span>
					</div>
					<div class="help-block col-xs-12 col-sm-reset inline"></div>
					
					<label class="col-xs-12 col-sm-1 control-label no-padding-right"></label>
					<div class="col-xs-12 col-sm-4">
						<span class="block input-icon input-icon-right">
							<input type="text" class="form-control change_dailyGain_slack" name="dailyIncome_slack"/>
						</span>
					</div>
					<div class="help-block col-xs-12 col-sm-reset inline"></div>
				</div>
				<div class="form-group">
					<label class="col-xs-12 col-sm-2 control-label no-padding-right">每天可变成本：</label>
					<div class="col-xs-12 col-sm-4">
						<span class="block input-icon input-icon-right">
							<input type="text" class="form-control change_dailyGain_peak" name="dailyChangeableCost_peak"/>
						</span>
					</div>
					<div class="help-block col-xs-12 col-sm-reset inline"></div>
					
					<label class="col-xs-12 col-sm-1 control-label no-padding-right"></label>
					<div class="col-xs-12 col-sm-4">
						<span class="block input-icon input-icon-right">
							<input type="text" class="form-control change_dailyGain_slack" name="dailyChangeableCost_slack"/>
						</span>
					</div>
					<div class="help-block col-xs-12 col-sm-reset inline"></div>
				</div>
				<div class="form-group">
					<label class="col-xs-12 col-sm-2 control-label no-padding-right">每天利润：</label>
					<div class="col-xs-12 col-sm-4">
						<span class="block input-icon input-icon-right">
							<input type="text" class="form-control" name="dailyGain_peak" readonly="readonly"/>
						</span>
					</div>
					<div class="help-block col-xs-12 col-sm-reset inline"></div>
					
					<label class="col-xs-12 col-sm-1 control-label no-padding-right"></label>
					<div class="col-xs-12 col-sm-4">
						<span class="block input-icon input-icon-right">
							<input type="text" class="form-control" name="dailyGain_slack" readonly="readonly"/>
						</span>
					</div>
					<div class="help-block col-xs-12 col-sm-reset inline"></div>
				</div>
				<div class="form-group">
					<label class="col-xs-12 col-sm-2 control-label no-padding-right">每月经营天数：</label>
					<div class="col-xs-12 col-sm-4">
						<span class="block input-icon input-icon-right">
							<input type="text" class="form-control" name="businessDay_peak"/>
						</span>
					</div>
					<div class="help-block col-xs-12 col-sm-reset inline"></div>
					
					<label class="col-xs-12 col-sm-1 control-label no-padding-right"></label>
					<div class="col-xs-12 col-sm-4">
						<span class="block input-icon input-icon-right">
							<input type="text" class="form-control" name="businessDay_slack"/>
						</span>
					</div>
					<div class="help-block col-xs-12 col-sm-reset inline"></div>
				</div>
				<div class="form-group">
					<label class="col-xs-12 col-sm-2 control-label no-padding-right">每月收入：</label>
					<div class="col-xs-12 col-sm-4">
						<span class="block input-icon input-icon-right">
							<input type="text" class="form-control change_yearIncomeTotal" name="monthlyIncome_peak"/>
						</span>
					</div>
					<div class="help-block col-xs-12 col-sm-reset inline"></div>
					
					<label class="col-xs-12 col-sm-1 control-label no-padding-right"></label>
					<div class="col-xs-12 col-sm-4">
						<span class="block input-icon input-icon-right">
							<input type="text" class="form-control change_yearIncomeTotal" name="monthlyIncome_slack"/>
						</span>
					</div>
					<div class="help-block col-xs-12 col-sm-reset inline"></div>
				</div>
				<div class="form-group">
					<label class="col-xs-12 col-sm-2 control-label no-padding-right">每月可变成本：</label>
					<div class="col-xs-12 col-sm-4">
						<span class="block input-icon input-icon-right">
							<input type="text" class="form-control change_yearChangeableCostTotal" name="monthlyChangeableCost_peak"/>
						</span>
					</div>
					<div class="help-block col-xs-12 col-sm-reset inline"></div>
					
					<label class="col-xs-12 col-sm-1 control-label no-padding-right"></label>
					<div class="col-xs-12 col-sm-4">
						<span class="block input-icon input-icon-right">
							<input type="text" class="form-control change_yearChangeableCostTotal" name="monthlyChangeableCost_slack"/>
						</span>
					</div>
					<div class="help-block col-xs-12 col-sm-reset inline"></div>
				</div>
				<div class="form-group">
					<label class="col-xs-12 col-sm-2 control-label no-padding-right">年收入合计：</label>
					<div class="col-xs-12 col-sm-9">
						<span class="block input-icon input-icon-right">
							<input type="text" class="form-control change_yearGainTotal" name="yearIncomeTotal" readonly="readonly"/>
						</span>
					</div>
					<div class="help-block col-xs-12 col-sm-reset inline"></div>
				</div>
				<div class="form-group">
					<label class="col-xs-12 col-sm-2 control-label no-padding-right">年可变成本合计：</label>
					<div class="col-xs-12 col-sm-9">
						<span class="block input-icon input-icon-right">
							<input type="text" class="form-control change_yearGainTotal" name="yearChangeableCostTotal" readonly="readonly"/>
						</span>
					</div>
					<div class="help-block col-xs-12 col-sm-reset inline"></div>
				</div>
				<div class="form-group">
					<label class="col-xs-12 col-sm-2 control-label no-padding-right">年毛利润合计：</label>
					<div class="col-xs-12 col-sm-9">
						<span class="block input-icon input-icon-right">
							<input type="text" class="form-control change_gainTotal" name="yearGainTotal" readonly="readonly"/>
						</span>
					</div>
					<div class="help-block col-xs-12 col-sm-reset inline"></div>
				</div>
				<div class="form-group">
					<label class="col-xs-12 col-sm-2 control-label no-padding-right">客户口述毛利润率：</label>
					<div class="col-xs-12 col-sm-9">
						<span class="block input-icon input-icon-right">
							<input type="text" class="form-control" name="customerDictateGainRatio"/>
						</span>
					</div>
					<div class="help-block col-xs-12 col-sm-reset inline"></div>
				</div>
				<div class="form-group">
					<label class="col-xs-12 col-sm-2 control-label no-padding-right">可变成本说明：</label>
					<div class="col-xs-12 col-sm-8">
						<span class="block input-icon input-icon-right">
							<textarea rows="3" cols="87" name="changeableRemarks"></textarea>
						</span>
					</div>
					<div class="help-block col-xs-12 col-sm-reset inline"></div>
				</div>
				
				<h5 class="blue">年固定成本合计</h5>
				
				<div class="row">
	         		<div class="form-group col-md-4">
						<label class="col-xs-12 col-sm-6 control-label no-padding-right">工资：
						</label>
						<div class="col-xs-12 col-sm-6">
							<input type="text" class="form-control input-sm change_costTotal" name="salary"/>
						</div>
					</div>
					<div class="form-group col-md-4">
						<label class="col-xs-12 col-sm-6 control-label no-padding-right">租金：
						</label>
						<div class="col-xs-12 col-sm-6">
							<input type="text" class="form-control input-sm change_costTotal" name="rent"/>
						</div>
					</div>
					<div class="form-group col-md-4">
						<label class="col-xs-12 col-sm-6 control-label no-padding-right">招待费：
						</label>
						<div class="col-xs-12 col-sm-6">
							<input type="text" class="form-control input-sm change_costTotal" name="hospitality"/>
						</div>
					</div>
				</div>
				<div class="row">
	         		<div class="form-group col-md-4">
						<label class="col-xs-12 col-sm-6 control-label no-padding-right">交通费：
						</label>
						<div class="col-xs-12 col-sm-6">
							<input type="text" class="form-control input-sm change_costTotal" name="tranffic"/>
						</div>
					</div>
					<div class="form-group col-md-4">
						<label class="col-xs-12 col-sm-6 control-label no-padding-right">水电费：
						</label>
						<div class="col-xs-12 col-sm-6">
							<input type="text" class="form-control input-sm change_costTotal" name="waterElectric"/>
						</div>
					</div>
					<div class="form-group col-md-4">
						<label class="col-xs-12 col-sm-6 control-label no-padding-right">通讯费：
						</label>
						<div class="col-xs-12 col-sm-6">
							<input type="text" class="form-control input-sm change_costTotal" name="communication"/>
						</div>
					</div>
				</div>
				<div class="row">
	         		<div class="form-group col-md-4">
						<label class="col-xs-12 col-sm-6 control-label no-padding-right">维修费：
						</label>
						<div class="col-xs-12 col-sm-6">
							<input type="text" class="form-control input-sm change_costTotal" name="repair"/>
						</div>
					</div>
					<div class="form-group col-md-4">
						<label class="col-xs-12 col-sm-6 control-label no-padding-right">税收：
						</label>
						<div class="col-xs-12 col-sm-6">
							<input type="text" class="form-control input-sm change_costTotal" name="tax"/>
						</div>
					</div>
					<div class="form-group col-md-4">
						<label class="col-xs-12 col-sm-6 control-label no-padding-right">其他：
						</label>
						<div class="col-xs-12 col-sm-6">
							<input type="text" class="form-control input-sm change_costTotal" name="others1"/>
						</div>
					</div>
				</div>
				<div class="row">
	         		<div class="form-group col-md-4">
						<label class="col-xs-12 col-sm-6 control-label no-padding-right">其他：
						</label>
						<div class="col-xs-12 col-sm-6">
							<input type="text" class="form-control input-sm change_costTotal" name="others2"/>
						</div>
					</div>
					<div class="form-group col-md-4">
						<label class="col-xs-12 col-sm-6 control-label no-padding-right">其他：
						</label>
						<div class="col-xs-12 col-sm-6">
							<input type="text" class="form-control input-sm change_costTotal" name="others3"/>
						</div>
					</div>
					<div class="form-group col-md-4">
						<label class="col-xs-12 col-sm-6 control-label no-padding-right">其他：
						</label>
						<div class="col-xs-12 col-sm-6">
							<input type="text" class="form-control input-sm change_costTotal" name="others4"/>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-12 col-sm-2 control-label no-padding-right">年固定成本合计：</label>
					<div class="col-xs-12 col-sm-9">
						<span class="block input-icon input-icon-right">
							<input type="text" class="form-control change_gainTotal" name="costTotal" readonly="readonly"/>
						</span>
					</div>
					<div class="help-block col-xs-12 col-sm-reset inline"></div>
				</div>
				<div class="form-group">
					<label class="col-xs-12 col-sm-2 control-label no-padding-right">年度净利润合计：</label>
					<div class="col-xs-12 col-sm-9">
						<span class="block input-icon input-icon-right">
							<input type="text" class="form-control" name="gainTotal" readonly="readonly"/>
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