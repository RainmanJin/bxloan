<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../commons/taglibs.jsp"%>

<div id="bizFamilyAssetsForm">
	<div id="faq-list-1" class="panel-group accordion-style1 accordion-style2">
		<c:if test="${!empty bizType && bizType =='1' }">
		<div class="panel panel-default" role="div_produceArea">
			<div class="panel-heading">
				<a href="#faq-1-a" data-parent="#faq-list-1" data-toggle="collapse"
					class="accordion-toggle collapsed"> 
					<i class="pull-right ace-icon fa fa-chevron-left"
					data-icon-hide="ace-icon fa fa-chevron-down"
					data-icon-show="ace-icon fa fa-chevron-left"></i>
					<c:if test="${isEdit}">
						&nbsp;<i role="addProductArea" title="新增"
						class="pull-right ace-icon fa fa-plus"></i>
					</c:if>
					&nbsp; 生产区域信息	
				</a> 
			</div>
			<div class="panel-collapse collapse" id="faq-1-a" style="height: 0px;">
				<div class="panel-body">
					<table role="tb_produceArea" class="table table-striped table-hover col-xs-12 dataTable">
						<thead>
						<tr>
							<th class="sorting_disabled">地点</th>
							<th class="sorting_disabled">面积/亩</th>
							<th class="sorting_disabled">生产区域性质</th>
							<th class="sorting_disabled">未来12个月是否生产</th>
							<th class="sorting_disabled">生产内容</th>
							<th class="sorting_disabled">设施情况</th>
							<th class="sorting_disabled">租金(元/年)</th>
							<th class="sorting_disabled">租期</th>
							<th class="sorting_disabled">租金支付时间</th>
							<th class="sorting_disabled">是否有租赁协议</th>					
							<th class="sorting_disabled">操作</th>
						</tr>
					</thead>
						<tbody>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		</c:if>	
		
		<div class="panel panel-default" role="div_produceArea">
			<div class="panel-heading">
				<a href="#faq-1-c" data-parent="#faq-list-1" data-toggle="collapse"
					class="accordion-toggle collapsed"> 
					<i class="pull-right ace-icon fa fa-chevron-left"
					data-icon-hide="ace-icon fa fa-chevron-down"
					data-icon-show="ace-icon fa fa-chevron-left"></i>
					<c:if test="${isEdit}">
						&nbsp;<i role="addFixedAssets" title="新增"
						class="pull-right ace-icon fa fa-plus"></i>
					</c:if>
					&nbsp; 固定资产	
				</a> 
			</div>
			<div class="panel-collapse collapse" id="faq-1-c" style="height: 0px;">
				<div class="panel-body">
			<table role="tb_fixedAsset" id="tbCw" class="table table-striped table-hover col-xs-12 dataTable">
				<thead>
				<tr>
					<th class="sorting_disabled">类别</th>
					<th class="sorting_disabled">品名</th>
					<th class="sorting_disabled">估计现价/金额(元)</th>
					<th class="sorting_disabled">原始购置价值(元)</th>
					<th class="sorting_disabled">面积/数量</th>
					<th class="sorting_disabled">购买/建造年份</th>
					<th class="sorting_disabled">结构/描述</th>
					<th class="sorting_disabled">操作</th>			
				</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
				</div>
			</div>
		</div>
		
		<div class="panel panel-default" role="div_produceArea">
			<div class="panel-heading">
				<a href="#faq-1-b" data-parent="#faq-list-1" data-toggle="collapse"
					class="accordion-toggle collapsed"> 
					<i class="pull-right ace-icon fa fa-chevron-left"
					data-icon-hide="ace-icon fa fa-chevron-down"
					data-icon-show="ace-icon fa fa-chevron-left"></i>&nbsp; 
					&nbsp; 非固定资产及负债状况	
				</a> 
			</div>
			<div class="panel-collapse collapse" id="faq-1-b" style="height: 0px;">
				<div class="panel-body">
		<table role="tb_noFixedAsset" class="table table-striped table-hover col-xs-12 dataTable">
			<thead>
				<tr>
					<th class="sorting_disabled">类别</th>
					<th class="sorting_disabled">科目</th>
					<th class="sorting_disabled" style="width:160px;">金额（元）</th>
					<th class="sorting_disabled">备注</th>
				</tr>
			</thead>
			<tbody>
			</tbody>
			<tfoot>
			<tr>
				<td colspan="4" align="center">
				<button role="saveNoFixedAssetLiab" type="button" class="btn btn-sm btn-primary" data-loading-text="保存中...">
					<i class="ace-icon fa fa-floppy-o"></i> 保存
				</button></td>
			</tr>
			</tfoot>
		</table>
				</div>
			</div>
		</div>
  </div>

<jsp:include page="bizFamilyAssets_form.jsp" />
</div>
<!-- ---------------------------------------- -->
