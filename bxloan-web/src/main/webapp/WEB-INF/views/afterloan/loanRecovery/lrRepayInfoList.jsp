<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.coamc-tech.com/taglibs/datadict" prefix="dd" %>
<div id="repay_info_modal" class="modal" tabindex="-1" data-backdrop="static">
	<div class="modal-dialog" style="width:70%">
		<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 id="repay_info_title" class="blue bigger">
						<i class='ace-icon fa fa-eye'></i>&nbsp;查询还款记录
					</h4>
				</div>
				<div class="modal-body">
				<div class="row">
					<div class="col-xs-12">
						<table id="tb_repay_info" class="table table-striped table-hover">
							<thead>
								<tr>
								<th>还款编号</th>
								<th>还款日期</th>
								<th>还款金额（元）</th>
								<th>资金来源</th>
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