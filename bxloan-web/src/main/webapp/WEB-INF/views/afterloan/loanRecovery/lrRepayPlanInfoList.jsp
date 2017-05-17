<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.coamc-tech.com/taglibs/datadict" prefix="dd" %>
<div id="repay_plan_modal" class="modal" tabindex="-1" data-backdrop="static">
	<div class="modal-dialog" style="width:70%">
		<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 id="repay_plan_title" class="blue bigger">
						<i class='ace-icon fa fa-eye'></i>&nbsp;查询还款计划明细
					</h4>
				</div>
				<div class="modal-body">
				<div class="row">
					<div class="col-xs-12">
						<table id="tb_repay_plan" class="table table-striped table-hover">
							<thead>
								<tr>
								<th>期次</th>
								<th>计划还款日</th>
								<th>应还本金（元）</th>
								<th>应还利息（元）</th>
								<th>本期应还金额（元）</th>
								<th>状态</th>
								</tr>
							</thead>
							<tbody>
							</tbody>
						</table>
					</div>
				</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-sm btn-default" data-dismiss="modal">
						<i class="ace-icon fa fa-times"></i> 取消
					</button>
				</div>
		</div>
	</div>
</div>