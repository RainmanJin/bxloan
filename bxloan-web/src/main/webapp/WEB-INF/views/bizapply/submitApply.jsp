<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../../commons/taglibs.jsp"%>

<%-- <div class="row">
协办客户经理： <select id="ass"style="width: 20%;height: 34px;">
							<c:forEach items="${assistancers}"  var="assistancer">
							<c:when test="${assistancer.isSelected == 'true' }"><option value="${assistancer.personId}"  select="selected">${assistancer.personName}</option></c:when>
						    <c:otherwise><option value="${assistancer.personId}" >${assistancer.personName}</option></c:otherwise>
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
	<textarea class="col-md-1 form-control" placeholder="请录入意见" rows="4"
		cols="50" id="opinion" name="opinion"></textarea>
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