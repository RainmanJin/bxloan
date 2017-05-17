<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../../commons/taglibs.jsp"%>
<table class="table table-bordered table-hover">
	<thead>
		<tr>
		</tr>
	</thead>
	<tbody>
	<c:forEach items="${tbForm.linegroupList }" var="lgroup">
		<c:forEach items="${lgroup.lines }" var="line" varStatus="lineStatus">
			<c:if test="${lineStatus.first }">
				<tr>
				<td colspan="${tbForm.columnCount*2 }"
				width="20%;" class="green">
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
								<c:when test="${input.inputType=='date' }">
									<input type="text" name="${cell.cell.cellInputName }"
										placeholder="${input.title }" value="${input.defaultValue }"
										class="ace col-xs-12 dy-from-date" readonly="readonly">
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