<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../../commons/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<base href="${ctx}/">
<title>${title }</title>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

<meta name="description" content="Dashboard page" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<link rel="stylesheet" href="${res }/css/chosen.css" />
<style type="text/css">
.checkbox-inline {
	margin-left: -20px;
}
</style>

<c:import url="../../commons/pre-include.jsp" charEncoding="UTF-8" />

<script>
	var $$ctx = "${ctx}/";
</script>
</head>
<body class="no-skin">
	<c:import url="../../commons/navbar.jsp" charEncoding="UTF-8" />

	<div class="main-container" id="main-container">
		<script type="text/javascript">
			try {
				ace.settings.check('main-container', 'fixed')
			} catch (e) {
			}
		</script>

		<c:import url="../../commons/sidebar.jsp" charEncoding="UTF-8" />

		<div class="main-content">
			<div class="breadcrumbs" id="breadcrumbs">
				<ul class="breadcrumb">
					<li>
						<i class="ace-icon fa fa-home home-icon"></i>
						<a href="${ctx}">主页</a>
					</li>
					<li>系统管理</li>
					<li>产品管理</li>
				</ul>
			</div>

			<div class="page-content">
				<div class="page-header">
					<table style="width: 100%;">
						<tr>
							<td>
								<h1>
									系统管理
									<small>
										<i class="ace-icon fa fa-angle-double-right"></i>
										产品管理
									</small>
								</h1>
							</td>
							<td width="20%">
								<span>
									<button id="btn-add" class="btn btn-sm btn-success">
										<i class="ace-icon fa fa-plus"></i> 添加同级产品
									</button>
									<button id="btn-add" class="btn btn-sm btn-success">
										<i class="ace-icon fa fa-plus"></i> 添加下级产品
									</button>
								</span>
							</td>
						</tr>
					</table>
				</div>

				<div class="row">
					<div class="col-md-3 col-md-offset-1">
						<ul id="tree" class="ztree">
		<li id="tree_1" class="level0" tabindex="0" hidefocus="true"
			treenode=""><span id="tree_1_switch" title=""
			class="button level0 switch roots_open" treenode_switch=""></span><span
			id="tree_1_check" class="button chk radio_false_part"
			treenode_check=""></span><a id="tree_1_a" class="level0"
			treenode_a="" onclick="" target="_blank" style="" title="邦微贷"><span
				id="tree_1_ico" title="" treenode_ico="" class="button ico_open"
				style=""></span><span id="tree_1_span">邦微贷</span>
		</a>
		<ul id="tree_1_ul" class="level0 line" style="">
				<li id="tree_2" class="level1" tabindex="0" hidefocus="true"
					treenode=""><span id="tree_2_switch" title=""
					class="button level1 switch center_docu" treenode_switch=""></span><span
					id="tree_2_check" class="button chk radio_true_full"
					treenode_check=""></span><a id="tree_2_a"
					class="level1 curSelectedNode" treenode_a="" onclick=""
					target="_blank" style="" title="一般经营类"><span id="tree_2_ico"
						title="" treenode_ico="" class="button ico_docu" style=""></span><span
						id="tree_2_span">一般经营类</span>
				</a>
				</li>
				<li id="tree_4" class="level1" tabindex="0" hidefocus="true"
					treenode=""><span id="tree_4_switch" title=""
					class="button level1 switch center_docu" treenode_switch=""></span><span
					id="tree_4_check" class="button chk radio_false_full"
					treenode_check=""></span><a id="tree_4_a" class="level1"
					treenode_a="" onclick="" target="_blank" style="" title="工薪类"><span
						id="tree_4_ico" title="" treenode_ico="" class="button ico_docu"
						style=""></span><span id="tree_4_span">工薪类</span>
				</a>
				</li>
			</ul>
		</li>
		<li id="tree_14" class="level0" tabindex="0" hidefocus="true"
			treenode=""><span id="tree_14_switch" title=""
			class="button level0 switch bottom_open" treenode_switch=""></span><span
			id="tree_14_check" class="button chk radio_false_full"
			treenode_check=""></span><a id="tree_14_a" class="level0"
			treenode_a="" onclick="" target="_blank" style="" title="邦易贷"><span
				id="tree_14_ico" title="" treenode_ico="" class="button ico_open"
				style=""></span><span id="tree_14_span">邦易贷</span>
		</a>
		<ul id="tree_14_ul" class="level0 " style="">
				<li id="tree_15" class="level1" tabindex="0" hidefocus="true"
					treenode=""><span id="tree_15_switch" title=""
					class="button level1 switch bottom_docu" treenode_switch=""></span><span
					id="tree_15_check" class="button chk radio_false_full"
					treenode_check=""></span><a id="tree_15_a" class="level1"
					treenode_a="" onclick="" target="_blank" style="" title="邦房贷"><span
						id="tree_15_ico" title="" treenode_ico="" class="button ico_docu"
						style=""></span><span id="tree_15_span">邦房贷</span>
				</a>
				</li>
			</ul>
		</li>
		<li id="tree_14" class="level0" tabindex="0" hidefocus="true"
			treenode=""><span id="tree_14_switch" title=""
			class="button level0 switch bottom_open" treenode_switch=""></span><span
			id="tree_14_check" class="button chk radio_false_full"
			treenode_check=""></span><a id="tree_14_a" class="level0"
			treenode_a="" onclick="" target="_blank" style="" title="邦易贷"><span
				id="tree_14_ico" title="" treenode_ico="" class="button ico_open"
				style=""></span><span id="tree_14_span">邦小贷</span>
		</a>
		<ul id="tree_14_ul" class="level0 " style="">
				<li id="tree_15" class="level1" tabindex="0" hidefocus="true"
					treenode=""><span id="tree_15_switch" title=""
					class="button level1 switch bottom_docu" treenode_switch=""></span><span
					id="tree_15_check" class="button chk radio_false_full"
					treenode_check=""></span><a id="tree_15_a" class="level1"
					treenode_a="" onclick="" target="_blank" style="" title="邦房贷"><span
						id="tree_15_ico" title="" treenode_ico="" class="button ico_docu"
						style=""></span><span id="tree_15_span">邦通贷</span>
				</a>
				</li>
			</ul>
		</li>
	</ul>
					</div>
					<div class="col-md-8">
						
						<form class="form-horizontal">
							<div class="form-group">
								<label class="col-xs-12 col-sm-3 control-label no-padding-right">
									<font color='red'> * </font>
									产品名称：
								</label>
								<div class="col-xs-12 col-sm-4">
									<span class="block input-icon input-icon-right">
										<input type="text" class="form-control" name="projectNo" value="一般经营类" readonly="readonly">
									</span>
								</div>
								<div class="help-block col-xs-12 col-sm-reset inline"></div>
							</div>
							<div class="form-group">
								<label class="col-xs-12 col-sm-3 control-label no-padding-right">
									<font color='red'> * </font>
									适用机构：
								</label>
								<div class="col-xs-12 col-sm-4">
									<span class="block input-icon input-icon-right">
										<input type="text" class="form-control" name="projectNo" value="东方邦信融通控股股份有限公司" readonly="readonly">
									</span>
								</div>
								<div class="help-block col-xs-12 col-sm-reset inline"></div>
							</div>
							<div class="form-group">
								<label class="col-xs-12 col-sm-3 control-label no-padding-right">
									<font color='red'> * </font>
									客户类型：
								</label>
								<div class="col-xs-12 col-sm-4">
									<span class="block input-icon input-icon-right">
										<label class="checkbox-inline">
											<input class="ace add_corp_Type form-control" type="checkbox" value="">
											<span class="lbl">企业客户</span>
										</label>
										<label class="checkbox-inline">
											<input class="ace add_corp_Type form-control" type="checkbox" value="">
											<span class="lbl">个人客户</span>
										</label>
									</span>
								</div>
								<div class="help-block col-xs-12 col-sm-reset inline"></div>
							</div>
							<div class="form-group">
								<label class="col-xs-12 col-sm-3 control-label no-padding-right">
									<font color='red'> * </font>
									金额区间：
								</label>
								<div class="col-xs-12 col-sm-4">
									<span class="block input-icon input-icon-right">
										<div class="input-group">
											<input type="text" class="form-control">
											<span class="input-group-addon">至</span>
											<input type="text" class="form-control">
											<span class="input-group-addon">元</span>
										</div>
									</span>
								</div>
								<div class="help-block col-xs-12 col-sm-reset inline"></div>
							</div>
							<div class="form-group">
								<label class="col-xs-12 col-sm-3 control-label no-padding-right">
									<font color='red'> * </font>
									贷款期限模式：
								</label>
								<div class="col-xs-12 col-sm-4">
									<span class="block input-icon input-icon-right">
										<select id="termMode" class="form-control">
											<option value="1">固定</option>
											<option value="2">区间</option>
											<option value="3">特殊</option>
										</select>
									</span>
								</div>
								<div class="help-block col-xs-12 col-sm-reset inline"></div>
							</div>
							<div id="term1div" class="form-group" style="display: none;">
								<label class="col-xs-12 col-sm-3 control-label no-padding-right">
									<font color='red'> * </font>
									贷款期限：
								</label>
								<div class="col-xs-12 col-sm-4">
									<span class="block input-icon input-icon-right">
										<input type="text" class="form-control">
									</span>
								</div>
								<div class="help-block col-xs-12 col-sm-reset inline"></div>
							</div>
							<div id="term2div" class="form-group" style="display: none;">
								<label class="col-xs-12 col-sm-3 control-label no-padding-right">
									<font color='red'> * </font>
									贷款期限区间：
								</label>
								<div class="col-xs-12 col-sm-4">
									<span class="block input-icon input-icon-right">
										<div class="input-group">
											<input type="text" class="form-control">
											<span class="input-group-addon">至</span>
											<input type="text" class="form-control">
										</div>
									</span>
								</div>
								<div class="help-block col-xs-12 col-sm-reset inline"></div>
							</div>
							<div id="term3div" class="form-group" style="display: none;">
								<label class="col-xs-12 col-sm-3 control-label no-padding-right">
									<font color='red'> * </font>
									贷款期限：
								</label>
								<div class="col-xs-12 col-sm-4">
									<select multiple="multiple" class="chosen-select tag-input-style" id="form-field-select-4" data-placeholder="Choose a State..." style="display: none;">
										<option value="3">3月</option>
										<option value="6">6月</option>
										<option value="9">9月</option>
										<option value="12">12月</option>
										<option value="18">18月</option>
									</select>
								</div>
								<div class="help-block col-xs-12 col-sm-reset inline"></div>
							</div>
							<div class="form-group">
								<label class="col-xs-12 col-sm-3 control-label no-padding-right">
									<font color='red'> * </font>
									利率：
								</label>
								<div class="col-xs-12 col-sm-4">
									<span class="block input-icon input-icon-right">
										<div class="input-group">
											<input type="text" class="form-control">
											<span class="input-group-addon">至</span>
											<input type="text" class="form-control">
											<span class="input-group-addon">%</span>
										</div>
									</span>
								</div>
								<div class="help-block col-xs-12 col-sm-reset inline"></div>
							</div>
							<div class="form-group">
								<label class="col-xs-12 col-sm-3 control-label no-padding-right">
									<font color='red'> * </font>
									逾期利率上浮比例：
								</label>
								<div class="col-xs-12 col-sm-4">
									<span class="block input-icon input-icon-right">
										<div class="input-group">
											<input type="text" class="form-control">
											<span class="input-group-addon">至</span>
											<input type="text" class="form-control">
											<span class="input-group-addon">%</span>
										</div>
									</span>
								</div>
								<div class="help-block col-xs-12 col-sm-reset inline"></div>
							</div>
							<div class="form-group">
								<label class="col-xs-12 col-sm-3 control-label no-padding-right">
									<font color='red'> * </font>
									挪用利率上浮比例：
								</label>
								<div class="col-xs-12 col-sm-4">
									<span class="block input-icon input-icon-right">
										<div class="input-group">
											<input type="text" class="form-control">
											<span class="input-group-addon">至</span>
											<input type="text" class="form-control">
											<span class="input-group-addon">%</span>
										</div>
									</span>
								</div>
								<div class="help-block col-xs-12 col-sm-reset inline"></div>
							</div>
							<div class="form-group">
								<label class="col-xs-12 col-sm-3 control-label no-padding-right">
									<font color='red'> * </font>
									提前还款违约金比例：
								</label>
								<div class="col-xs-12 col-sm-4">
									<span class="block input-icon input-icon-right">
										<div class="input-group">
											<input type="text" class="form-control">
											<span class="input-group-addon">至</span>
											<input type="text" class="form-control">
											<span class="input-group-addon">%</span>
										</div>
									</span>
								</div>
								<div class="help-block col-xs-12 col-sm-reset inline"></div>
							</div>
							<div class="form-group">
								<label class="col-xs-12 col-sm-3 control-label no-padding-right">
									<font color='red'> * </font>
									还款方式：
								</label>
								<div class="col-xs-12 col-sm-4">
									<span class="block input-icon input-icon-right">
										<c:forEach items="${repaymentModes }" var="repaymentMode">
											<label class="checkbox-inline">
												<input type="checkbox" class="ace add_corp_Type form-control" value="${repaymentMode.codeValue }" />
												<span class="lbl">${repaymentMode.codeName }</span>
											</label>
											<br>
										</c:forEach>
									</span>
									<input type="hidden" id="repaymentType" name="repaymentType">
								</div>
								<div class="help-block col-xs-12 col-sm-reset inline"></div>
							</div>
							<div class="form-group">
								<label class="col-xs-12 col-sm-3 control-label no-padding-right">
									<font color='red'> * </font>
									还款周期：
								</label>
								<div class="col-xs-12 col-sm-4">
									<span class="block input-icon input-icon-right">
										<input type="text" class="required" style="width: 80%;">
										<select class="required" style="width: 20%;height: 33.5px;margin-left: -4px;">
											<dd:options codeType="TermUnitCd"/>
										</select>
									</span>
								</div>
								<div class="help-block col-xs-12 col-sm-reset inline"></div>
							</div>
							<div class="form-group">
								<label class="col-xs-12 col-sm-3 control-label no-padding-right">
									<font color='red'> * </font>担保方式： </label>
								<div class="col-xs-12 col-sm-4">
									<span class="block input-icon input-icon-right" id="guaranteeModeSpan">
										<dd:checkbox codeType="GuaranteeTypeCode" cbName="guaranteeMode" aceStyle="true"/>
									</span>
								</div>
								<div class="help-block col-xs-12 col-sm-reset inline"></div>
							</div>
							<div class="form-group">
								<label class="col-xs-12 col-sm-3 control-label no-padding-right">
									<font color='red'> * </font>
									是否批量：
								</label>
								<div class="col-xs-12 col-sm-4">
									<span class="block input-icon input-icon-right">
										<label class="checkbox-inline">
											<input class="ace add_corp_Type form-control" type="radio" name="isStart">
											<span class="lbl">是</span>
										</label>
										<label class="checkbox-inline">
											<input class="ace add_corp_Type form-control" type="radio" name="isStart">
											<span class="lbl">否</span>
										</label>
									</span>
								</div>
								<div class="help-block col-xs-12 col-sm-reset inline"></div>
							</div>
							<div class="form-group">
								<label class="col-xs-12 col-sm-3 control-label no-padding-right">
									<font color='red'> * </font>
									批量额度：
								</label>
								<div class="col-xs-12 col-sm-4">
									<span class="block input-icon input-icon-right">
										<input type="text" class="form-control">
									</span>
								</div>
								<div class="help-block col-xs-12 col-sm-reset inline"></div>
							</div>
							<div class="form-group">
								<label class="col-xs-12 col-sm-3 control-label no-padding-right">
									<font color='red'> * </font>
									审批流程：
								</label>
								<div class="col-xs-12 col-sm-4">
									<span class="block input-icon input-icon-right">
										<input type="text" class="form-control">
									</span>
								</div>
								<div class="help-block col-xs-12 col-sm-reset inline"></div>
							</div>
							
							<div class="form-group">
								<label class="col-xs-12 col-sm-3 control-label no-padding-right">
									<font color='red'> * </font>
									是否启用：
								</label>
								<div class="col-xs-12 col-sm-4">
									<span class="block input-icon input-icon-right">
										<label class="checkbox-inline">
											<input class="ace add_corp_Type form-control" type="radio" name="isStart">
											<span class="lbl">是</span>
										</label>
										<label class="checkbox-inline">
											<input class="ace add_corp_Type form-control" type="radio" name="isStart">
											<span class="lbl">否</span>
										</label>
									</span>
								</div>
								<div class="help-block col-xs-12 col-sm-reset inline"></div>
							</div>
							<div class="form-group">
								<label class="col-xs-12 col-sm-3 control-label no-padding-right">
									<font color='red'> * </font>产品描述： </label>
								<div class="col-xs-12 col-sm-4">
									<span class="block input-icon input-icon-right">
										<textarea class="form-control required isTextarea" rows="4"></textarea>
									</span>
								</div>
								<div class="help-block col-xs-12 col-sm-reset inline"></div>
							</div>
							<div class="form-group">
								<label class="col-xs-12 col-sm-3 control-label no-padding-right">
									<font color='red'> * </font>备注： </label>
								<div class="col-xs-12 col-sm-4">
									<span class="block input-icon input-icon-right">
										<textarea class="form-control required isTextarea" rows="4"></textarea>
									</span>
								</div>
								<div class="help-block col-xs-12 col-sm-reset inline"></div>
							</div>
							<hr>
							<div class="row">
								<div class="col-md-4 col-md-offset-5">
									<button type="submit" class="btn btn-primary">
										<i class="ace-icon fa fa-floppy-o"></i> 提交
									</button>
								</div>
							</div>
						</form>
					</div>
				</div>

			</div>
		</div>

		<c:import url="../../commons/footer.jsp" charEncoding="UTF-8" />

	</div>

</body>

<c:import url="../../commons/post-include.jsp" />
<script type="text/javascript">
	jQuery(function($) {
	
		$('.chosen-select').chosen({allow_single_deselect:true}); 
		//resize the chosen on window resize
	
		$(window)
		.off('resize.chosen')
		.on('resize.chosen', function() {
			$('.chosen-select').each(function() {
				 var $this = $(this);
				 $this.next().css({'width': '100%'});
			});
		}).trigger('resize.chosen');
	
		$('#chosen-multiple-style').on('click', function(e){
			var target = $(e.target).find('input[type=radio]');
			var which = parseInt(target.val());
			if(which == 2) $('#form-field-select-4').addClass('tag-input-style');
			 else $('#form-field-select-4').removeClass('tag-input-style');
		});
		
		$(document).on("change", "#termMode", function(e) {
			var termMode = $(this).val();
			switch (parseInt(termMode)) {
			case 1:
				$("#term1div").slideDown();
				$("#term2div").slideUp();
				$("#term3div").slideUp();
				break;
			case 2:
				$("#term1div").slideUp();
				$("#term2div").slideDown();
				$("#term3div").slideUp();
				break;
			case 3:
				$("#term1div").slideUp();
				$("#term2div").slideUp();
				$("#term3div").slideDown();
				break;
			default:
				$("#term1div").slideUp();
				$("#term2div").slideUp();
				$("#term3div").slideUp();
				break;
			}
		});
	
	});
</script>
</body>
</html>