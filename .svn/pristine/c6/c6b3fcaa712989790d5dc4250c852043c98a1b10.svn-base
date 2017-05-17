<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../../commons/taglibs.jsp"%>
<div class="ace-settings-container" id="ace-settings-container">
	<div class="btn btn-app btn-xs btn-warning ace-settings-btn"
		id="ace-settings-btn">
		<i class="ace-icon fa fa-eye bigger-150"></i>
	</div>

	<div class="ace-settings-box clearfix" id="ace-settings-box">
		<div class="pull-left width-50">
		
			<div class="ace-settings-item" style="margin-top:10px;">
				<label class="lbl" for="ace-settings-navbar">
				<a style="text-decoration: none;cursor: pointer;" id="customerForFloatWindow"  target="_blank">
                             	客户信息
                            </a>
				 </label>
			</div>
			
			<div class="ace-settings-item">
				<label class="lbl" for="ace-settings-sidebar"> 
				<a style="text-decoration: none;cursor: pointer;" id="businessForFloatWindow"  target="_blank">
                             	业务信息
                            </a>
				</label>
			</div>
			
			
			<div class="ace-settings-item">
				<label class="lbl" for="ace-settings-breadcrumbs"> 
				<a style="text-decoration: none;cursor: pointer;" id="downloadForFloatWindow"  target="_blank">
                             	贷款申请表
                            </a>
				</label>
			</div>
			
			<div class="ace-settings-item">
				<label class="lbl" for="ace-settings-breadcrumbs">
				<a style="text-decoration: none;cursor: pointer;" id="documentsForFloatWindow"  target="_blank">
                             	相关文档
                            </a>
				 </label>
			</div>
			
			<div class="ace-settings-item">
				<label class="lbl" for="ace-settings-breadcrumbs"> 
					<a style="text-decoration: none;" id="loanTrialWindow"  target="_blank">贷款试算</a></label>
			</div>
			<c:if test="${taskStageCode eq '100311' or taskStageCode eq '100312' or taskStageCode eq '100313'}">
				<div class="ace-settings-item">
					<label class="lbl" for="ace-settings-breadcrumbs">
					<a style="text-decoration: none;cursor: pointer;" id="antiFakeFloatWindow"  target="_blank">
                              		 反欺诈情况
                             </a>
					</label>
				</div>
			</c:if>
			<c:if test="${taskStageCode eq '100312'}">
				<div class="ace-settings-item">
					<label class="lbl" for="ace-settings-breadcrumbs">
					<a style="text-decoration: none; cursor: pointer;" id="telNetInfoTableShow" >
                              		 电核网核情况
                             </a>
					</label>
				</div>
			</c:if>
		</div>
		<!-- /.pull-left -->
	</div>
	<!-- /.ace-settings-box -->
</div>
<!-- /.ace-settings-container -->