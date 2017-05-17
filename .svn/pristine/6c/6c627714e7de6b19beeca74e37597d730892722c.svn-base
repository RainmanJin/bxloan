<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../commons/taglibs.jsp"%>
<%-- <div class="row">
协办客户经理： <select id="ass"style="width: 20%;height: 34px;">
					<c:forEach items="${assistancers}"  var="assistancer">
								<c:if test="${assistancer.isSelected == 'true' }">
									<option value="${assistancer.personId}"  selected="selected">${assistancer.personName}</option>
								</c:if>
								<c:if test="${assistancer.isSelected == 'false'}" >
									<option value="${assistancer.personId}" >${assistancer.personName}</option>
								</c:if>
							</c:forEach>
			</select>
</div> --%>
<div class="row">
	<textarea class="col-md-1 form-control" placeholder="请录入意见" id="opinion" name="opinion"   rows="5"  style="width: 100%;"></textarea>
</div>
<br>
<div class="row">
	提交至 ： <select id="nextTaskReceiver" style="width: 20%;height: 34px;">
		<c:forEach items="${vo.receivers }" var="nextTaskReceiver">
			<option value="${nextTaskReceiver.logName },${nextTaskReceiver.orgId }">${nextTaskReceiver.name }--${nextTaskReceiver.orgName }</option>
		</c:forEach>
	</select>
</div>
<br>
<div class="row">
	<div class="col-md-6 col-md-offset-4">
		<!-- <button type="button" class="btn btn-sm btn-yellow"
			id="downloadBizApply">
			<i class="ace-icon fa fa-download"></i>下载贷款申请
		</button> -->
		<!-- <button type="button" class="btn btn-sm btn-info" id="uploadBizApply">
			<i class="ace-icon fa fa-upload"></i>上传贷款申请表
		</button> -->
		<!-- comment by wangyawei 20150701 start 将生成邦农贷财务报表功能修改到贷款审批过程中右侧相关链接-->
		<!-- <c:if test="${agriculturalLoan }">
			<button role="bndFinancialStatements" type="button" class="btn btn-sm btn-info">
				<i class="ace-icon fa fa-download"></i>生成邦农贷财务报表
			</button>
		</c:if> -->
		<!-- comment by wangyawei 20150701 end -->
		<c:forEach items="${vo.buttons }" var="button">
			<c:if test="${button.actionCode == '1' }">
				<button id="submitApply" value="${button.actionCode }" type="submit" class="btn btn-sm btn-primary" data-loading-text="正在保存..">
					<i class="ace-icon fa fa-arrow-right"></i>${button.actionNameCn }
				</button>
			</c:if>
			<c:if test="${button.actionCode == '3' }">
				<button id="cancelApply" value="${button.actionCode }" type="submit" class="btn btn-warning btn-sm btn-default">
					<i class="ace-icon fa fa-times"></i>${button.actionNameCn }
				</button>
			</c:if>
		</c:forEach>
	</div>
</div>

<div id="progressDiv" class="modal" data-backdrop="static" tabindex="-1">
	<div class="col-md-5 col-md-offset-3">
		<div class="progress">
			<div id="submitProgress" class="progress-bar" role="progressbar"
				aria-valuenow="0" aria-valuemin="0" aria-valuemax="100">0%</div>
		</div>
	</div>
</div>