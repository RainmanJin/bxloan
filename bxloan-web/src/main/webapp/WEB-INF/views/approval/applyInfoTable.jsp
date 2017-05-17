<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row">
	<div class="col-xs-12">
		<form id="apply_info_form" >
		<div class="tabbable">
			<ul class="nav nav-tabs padding-12 tab-color-blue" >
		             	<li class="active">
		                      <a data-toggle="tab" href="#apply_info_form_tab_1">
		                         	电核
		                      </a>
		                  </li>
		                  <li>
		                      <a data-toggle="tab" href="#apply_info_form_tab_2">
		                         	网核
		                      </a>
		                  </li>
			</ul>
			
			<div class="tab-content no-border padding-24">
			
				<input type="hidden" name="subjectId" id="apply_info_form_subjectId" value="${projectId }"/>
				
                 <div id="apply_info_form_tab_1" class="tab-pane fade in active">
					<table class="table  table-bordered table-hover">
						<thead>
							<tr></tr>
						</thead>
						<tbody>
						
						<c:forEach items="${applyInfoTableTel.linegroupList }" var="lgroup">
							<c:forEach items="${lgroup.lines }" var="line" varStatus="lineStatus">
								<c:if test="${lineStatus.first }">
									<tr>
									<td colspan="${applyInfoTableTel.columnCount*2 }"
									width="20%;" class="">
									<b>${lgroup.title }</b>
									</td >
									</tr>
								</c:if>
								<tr>
									<c:forEach items="${line.cellList }" var="cell">
										<td 
										<c:if test="${ empty cell.cell.rowDescript  }">
										colspan="2"
										</c:if>
										>
											${cell.cell.rowDescript }
											<c:if test="${not empty cell.cell.rowDescript  }">
											</td>
											<td>
											</c:if>
										
											<div class="col-1" >
											<c:forEach items="${cell.inputList }" var="input">
												<c:choose>
													<c:when test="${input.inputType=='radio' }">
														<label class="checkbox-inline">
															<input type="radio" name="${cell.cell.cellInputName }" value="${input.defaultValue }" class="ace" />
															<span class='lbl'>${input.title }</span>
														</label>
													</c:when>
													<c:when test="${input.inputType=='text' }">
													<input type="text"
													name="${cell.cell.cellInputName }"
													placeholder="${input.title }"
													value="${input.defaultValue }"
													style="float: none;"
													 class="ace col-xs-12">
													</c:when>
													<c:otherwise>
													${input.title }
													<input type="${input.inputType }" 
														name="${cell.cell.cellInputName }"
														value="${input.defaultValue }"/>&nbsp;
													</c:otherwise>
												</c:choose>
												
											</c:forEach>
											</div>
										</td>
									</c:forEach>
								</tr>
							</c:forEach>
						</c:forEach>
						</tbody>
					</table>
				</div>
				
				<div id="apply_info_form_tab_2" class="tab-pane fade in">
					<table class="table table-bordered table-hover">
						<thead>
							<tr>
							</tr>
						</thead>
						<tbody>
						<c:forEach items="${applyInfoTableNet.linegroupList }" var="lgroup">
							<c:forEach items="${lgroup.lines }" var="line" varStatus="lineStatus">
								<c:if test="${lineStatus.first }">
									<tr>
									<td colspan="${applyInfoTableNet.columnCount*2 }"
									width="20%;" class="">
									<b>${lgroup.title }</b>
									</td >
									</tr>
								</c:if>
								<tr>
									<c:forEach items="${line.cellList }" var="cell">
										<td 
										<c:if test="${ empty cell.cell.rowDescript  }">
										colspan="2"
										</c:if>
										>
											${cell.cell.rowDescript }
											<c:if test="${not empty cell.cell.rowDescript  }">
											</td>
											<td>
											</c:if>
											<div class="col-1" >
											<c:forEach items="${cell.inputList }" var="input">
												<c:choose>
													<c:when test="${input.inputType=='radio' }">
														<label class="checkbox-inline">
															<input type="radio" name="${cell.cell.cellInputName }" value="${input.defaultValue }" class="ace" />
															<span class='lbl'>${input.title }</span>
														</label>
													</c:when>
													<c:when test="${input.inputType=='text' }">
													<input type="text"
													name="${cell.cell.cellInputName }"
													placeholder="${input.title }"
													value="${input.defaultValue }"
													style="float: none;"
													 class="ace col-xs-12">
													</c:when>
													<c:otherwise>
													${input.title }
													<input type="${input.inputType }" 
														name="${cell.cell.cellInputName }"
														value="${input.defaultValue }"/>&nbsp;
													</c:otherwise>
												</c:choose>
												
											</c:forEach>
											</div>
										</td>
									</c:forEach>
								</tr>
							</c:forEach>
						</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="text-center" >
					<div class="red" id="apply_info_err_msg_field"></div>
					<%-- <button type="submit" class="btn  btn-primary" 
						>
						<i class="ace-icon fa fa-floppy-o"></i> 保存
					</button> --%>
				</div>
			</div>
		</div>
		</form>
	</div>
</div>