<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../../commons/taglibs.jsp"%>
<%@ taglib uri="http://www.coamc-tech.com/taglibs/datadict" prefix="dd"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<div class="row">
	<h4 class="blue">电话审查情况明细</h4>
	<div align="right" style="margin-left:45px;">
		<button class="btn btn-sm btn-success" id="btn-phone" type="button">
			<i class="ace-icon fa fa-plus"></i> 新增
		</button>
	</div>
	<table id="phone_tbl" class="table table-striped table-hover"
		style="white-space: nowrap;">
		<thead>
			<tr>
				<th>核实人对象</th>
				<th>姓名</th>
				<th>电话号码是否正确</th>
				<th>职业(收入来源)</th>
				<th>知悉申请金额</th>
				<th>知悉申请期限</th>
				<th>知悉利率</th>
				<th>联保责任知悉</th>
				<th>联保责任明确</th>
				<th>拨打时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		</tbody>
	</table>
</div>
<!-- 电话审查情况明细提交表单 -->
<div id="modal-phoneFormInfo" class="modal fade" tabindex="-1" 
	role="basic" aria-hidden="true" style=";" data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content">
			<form id="phoneForm" class="form-horizontal" onsubmit="return false;">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="blue bigger"></h4>
				</div>
				
				<div class="modal-body">
                      <div class="row">
                        <div class="col-xs-12">
                        	<!-- 隐藏提交数据 -->
                           <input type="hidden" id="filed-phone-0" name="id"/>
                           <input type="hidden" id="projectId" name="projectId" value="${projApp.projectId}"/>  
                           <input type="hidden" id="partyId" name="partyId" value="${approvalContent.partyId}"/>                          
						  <div class="form-group">
							<label class="col-sm-3 control-label no-padding-right"> 
								<font color="red">*</font>核实对象：
							</label>
							<div class="col-sm-8">
								<span class="block input-icon input-icon-right">
									<select name="checkType" id="checkType" class="form-control">
										<dd:options codeType="CheckerType" />
									</select>
								</span>
						     </div>
							</div>
							<div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" >
                                <font color="red">*</font>姓名：
                            </label>
                            <div class="col-sm-8">
                              <span class="block input-icon input-icon-right" >
                                <input type="text" name="checkName" id="checkName"  
                                 class="form-control input-sm" readonly="readonly"/>
                              </span>
                            </div>
                          </div>
                          <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" >
                                <font color="red">*</font>电话号码是否正确：
                            </label>
                            <div class="col-sm-8">
								<span class="block input-icon input-icon-right">
									<select name="isPhone" id="isPhone" class="form-control">
										<dd:options codeType="CommonWhether" />
									</select>
								</span>
						     </div>
                          </div>
                          <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" >
                                <font color="red">*</font>职业(收入来源)：
                            </label>
                            <div class="col-sm-8">
								<span class="block input-icon input-icon-right">
									<select name="isIncomeSource" id="isIncomeSource" class="form-control">
										<dd:options codeType="CommonWhether" />
									</select>
								</span>
						     </div>
                          </div>
                          <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" >
                              	<font color="red">*</font>知悉申请金额：
                            </label>
                            <div class="col-sm-8">
								<span class="block input-icon input-icon-right">
									<select name="isApplyMoney" id="isApplyMoney" class="form-control">
										<dd:options codeType="CommonWhether" />
									</select>
								</span>
						     </div>
                          </div>
                          <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" >
                              	<font color="red">*</font>知悉申请期限：
                            </label>
                            <div class="col-sm-8">
								<span class="block input-icon input-icon-right">
									<select name="isApplyTerm" id="isApplyTerm" class="form-control">
										<dd:options codeType="CommonWhether"/>
									</select>
								</span>
						     </div>
                          </div>
                          <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" >
                              	<font color="red">*</font>知悉利率：
                            </label>
                            <div class="col-sm-8">
								<span class="block input-icon input-icon-right">
									<select name="isRate" id="isRate" class="form-control">
										<dd:options codeType="CommonWhether" />
									</select>
								</span>
						     </div>
                          </div>
                          <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" >
                                <font color="red">*</font>联保责任知悉：
                            </label>
                            <div class="col-sm-8">
								<span class="block input-icon input-icon-right">
									<select name="isBlameKnow" id="isBlameKnow" class="form-control">
										<dd:options codeType="CommonWhether" />
									</select>
								</span>
						     </div>
                          </div>
                          <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" >
                                <font color="red">*</font>联保责任明确：
                            </label>
                            <div class="col-sm-8">
								<span class="block input-icon input-icon-right">
									<select name="isBlameClear" id="isBlameClear" class="form-control">
										<dd:options codeType="CommonWhether" />
									</select>
								</span>
						     </div>
                          </div>
                          <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">
                                <font color="red">*</font>拨打时间：
                            </label>
                            <div class="col-sm-8">
                              <span class="block input-icon input-icon-right" >
                                <input id="time"  name="time"  
                                class="form-control input-datepicker" date-format="yyyy-MM-dd"/>
                              </span>
                            </div>
                          </div>
                          <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">
                                <font color="red">*</font>审查人员签字：
                            </label>
                            <div class="col-sm-8">
                              <span class="block input-icon input-icon-right" >
                                <input id="approvalUserName" name="approvalUserName" 
                                value="<shiro:principal/>" class="form-control" disabled="disabled"/>
                              </span>
                            </div>
                          </div>
                          <div class="form-group">
                            <label class="col-sm-6 control-label no-padding-right">
                               <small>标注：
                               	 <font color="red">操作人对当前审核内容负责</font>
                               </small>
                            </label>
                          </div>
                        </div>
                      </div>
                    </div>
                    <!-- /.modal-body -->
                    
				<div class="modal-footer">
					<button id="submit-phone" type="submit" class="btn btn-sm btn-primary" data-loading-text="保存中...">
						<i class="ace-icon fa fa-floppy-o"></i> 保存
					</button>
					<button type="button" class="btn btn-sm btn-warning" data-dismiss="modal">
						<i class="ace-icon fa fa-times"></i>取消
					</button>
				</div>
			</form>
		</div>
	</div>
</div>
