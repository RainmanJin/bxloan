define(function(require, exports, module) {
	var model = require("./model");
	var rm = require("./rm");
	var utils = require("utils");
	var view = Backbone.View.extend({
		el : "body",
		events : {
			"click #search" : "search",
			"click #reset" : "reset",
			"click #showTree" : "ToggleTree"
		},
		initialize : function() {
			this.model = new model();
			this.render();
		},
		render : function() {
			this.initSearchForm();
			this.initDataTables();
			//初始化日期
			this.initApplyDate();
			//流程详细信息
			this.initDetailBtn();
			this.initTree();
		},
		initSearchForm : function() {
			$("#searchForm").validate({
				rules: rm.rules,
                messages: rm.messages,
                errorPlacement : function(error, element) { // 指定错误信息位置
					if (element.is(':radio') || element.is(':checkbox')) { // 如果是radio或checkbox
						var eid = element.attr('name'); // 获取元素的name属性
						error.appendTo(element.parent().parent()); // 将错误信息添加当前元素的父结点后面
					} else {
						error.insertAfter(element);
					}
				},
			});
			$("#searchForm").find("input").bind("change", function(e) {
				$(this).valid();
			});
//			$("#searchForm").removeAttr("novalidate");
//			$("#searchForm").removeAttr("class");
		},
		initDataTables : function() {
			$('#projectStatus').val('');
			var viewSelf = this;
			var oTable = $("#table").dataTable({
				sAjaxSource : $$ctx + "querybusiness/findBySearch",
				bFilter : false,
				bLengthChange : false,
				fnServerParams : function(aoData) {
					if ($('#searchForm').valid()) {
						var $form=$("form[role='contract_searchform']");
						aoData.push(
					    	{name:"orgId",value:$form.find("select[name='orgId']").val()},
							{name : "projectNo",value : $('#projectNo').val()}, 
							{name : "productType",value : $('#productType').val()}, 
							{name : "customerName",value : $('#customerName').val()}, 
							{name : "applyDateStart",value : $('#applyDateStart').val()}, 
							{name : "applyDateEnd",value : $('#applyDateEnd').val()}, 
							{name : "projectStatus",value : $('#projectStatus').val()}, 
							{name : "applyAmtMin",value : $('#applyAmtMin').val()}, 
							{name : "applyAmtMax",value : $('#applyAmtMax').val()}
						);
					}
				},
				aoColumnDefs : [ 
				    {aTargets : [ 0 ],mRender : function(data, type, rowdata) {
				    	var linkToApply = "<a title='点击查看业务申请' href='"+ $$ctx + "bizapply/checkBusinessApplication/" + rowdata[9] + "/querybusiness" +"'>"+ data +"</a>";
				    	return linkToApply;
				    }},
				    {aTargets : [ 7 ],mRender : function(data, type, rowdata) {
						switch (data) {
							case '0': return "未提交";
							case '1': return "已提交";
							case '2': return "已批复";
							case '3': return "中止";
							case '4': return "否决";
							case '5': return "已签合同";
							case '6': return "已终止";
							default: return "";
						}
					}}, 
					{aTargets : [ 8 ],mRender : function(data, type, rowdata) {
						
						var _template = $("#dt-row-operation-02").html();
		    	    	return Mustache.render(_template,{
		    	    		workflowId : rowdata[9]//流程编号
		    	    		/*,
		    	    		workflowCode : rowdata.workflowTypeCode,//流程类型。例如：易贷1003；微贷1004
		    	    		taskStatus : rowdata.taskStatus,//任务状态。例如：待处理80；
		    	    		nodeId : rowdata.nodeId,//环节ID。例如：初审100412
		    	    		taskId : rowdata.taskId,//任务ID。*/
		    	    	});
					}}],
				fnDrawCallback : function(){
                     	utils.num.tableFormat(this);//对表格内的纯数字列加逗号
                     }
			});
			viewSelf.oTable = oTable;
		},
		initDetailBtn : function() {
			var _view = this;
			$(document).on("click","button[role='showWorkDetail']",function(e){
				$("#wfDetailWarp").html("");
				var workflowId = $(this).data("workflowid");
				var nids = [];//审批流程节点id
				nids.push('100311');//易贷流程评审岗
				nids.push('100312');//易贷流程审批岗
				nids.push('100413');//微贷流程分配岗
				nids.push('100414');//微贷流程一级审批岗
				nids.push('100416');//微贷流程二级审批岗
				//add by wangyawei 20150812 start 增加授信审批流程-特殊环节处理环节ID
				nids.push('100713');//授信审批流程任务分配岗
				nids.push('100714');//授信审批流程一级审批岗
				nids.push('100715');//授信审批流程二级审批岗
				//add by wangyawei 20150812 end
				_view.model.fetchDetail({workflowId:workflowId},function(result){
					if(result.success){
						for ( var index in result.data) {
							wfDetail = result.data[index];
							var workStatus = wfDetail.taskStatus;
							var htmlContent = "";
							var isDone = wfDetail.taskStatus=='82';//已处理完成
							
							htmlContent += '<div class="timeline-item clearfix\">';
							htmlContent += '	<div class="timeline-info">';
							
							if(isDone){
								if(index ==0 && workStatus=='82'){
									htmlContent += '		<i class="timeline-indicator ace-icon fa fa-stop btn btn-purple no-hover"></i>';
								}else{
									htmlContent += '		<i class="timeline-indicator ace-icon fa fa-check-square-o btn btn-info no-hover"></i>';
								}
							}else{
								htmlContent += '		<i class="timeline-indicator ace-icon fa fa-hand-o-right btn btn-warning no-hover"></i>';
							}
							
							htmlContent += '	</div>';
							
							if(isDone){
								if(index ==0 && workStatus=='82'){
									htmlContent += '	<div class="widget-box widget-color-purple">';
								}else{
									htmlContent += '	<div class="widget-box transparent ">';
								}
							}else{
								htmlContent += '	<div class="widget-box widget-color-orange ">';
							}
							
							htmlContent += '		<div class="widget-header widget-header-small">';
							htmlContent += '			<h5 class="widget-title smaller">';
							
							if(isDone){
								if(index ==0 && workStatus=='82'){
									htmlContent += '				<span class="">'+ wfDetail.stageNameCn +'(流程已结束)</span>';
								}else{
									htmlContent += '				<span class="">'+ wfDetail.stageNameCn +'</span>';
								}
								
							}else{
								htmlContent += '				<span class="">'+ wfDetail.stageNameCn +'(待处理)</span>';
							}
							
							htmlContent += '			</h5>';
							
							htmlContent += '			<span class="widget-toolbar no-border">';
							htmlContent += '				<i class="ace-icon fa fa-clock-o bigger-110"></i>';
							htmlContent += wfDetail.createTime ;
							htmlContent += '			</span>';
	
							htmlContent += '		</div>';
	
							htmlContent += '		<div class="widget-body">';
							htmlContent += '			<div class="widget-main" style="overflow:hidden;word-warp:break-word;word-break:break-all;">';
							if(wfDetail.actionNameCn){
								htmlContent += '操作:' + wfDetail.actionNameCn + '<br/><br/>';
							}
							if (wfDetail.taskAssigneeCn && (result.msg == '10001' || $.inArray(wfDetail.nodeId,nids) < 0)) {
								htmlContent += '经办人:' + wfDetail.taskAssigneeCn + '(' + wfDetail.taskAssignee + ')<br/><br/>';
							}
							
							htmlContent += '审批意见:' + (wfDetail.comments||'无审批意见');				
							htmlContent += '			</div>';
							htmlContent += '		</div>';
							htmlContent += '	</div>';
							htmlContent += '</div>';
							$(htmlContent).appendTo($("#wfDetailWarp"));
						}
						
						$("#detail-modal").modal("show");
					}else{
						bootbox.alert("查看详细失败请稍后重试");
					}
					
				});
			});
		},
		initApplyDate : function() {
			// 将datepicker控件放在最顶层
			var dateOnTop = function() {
				$(".datepicker").css("z-index", "99999");
			};
			var defaultDateConf = {
				autoclose : true,
				todayHighlight : true,
				clearBtn : true,
				endDate : 'd'
			};

			var initDateP = function(selector, changeCallBack, config) {
				var _dateConf = config || defaultDateConf;
				$(selector).datepicker(_dateConf).on("changeDate",
						changeCallBack).on("click", dateOnTop);
			};

			initDateP("#applyDateStart");
			initDateP("#applyDateEnd");
		},
		search : function() {
			var table = $("#table").dataTable();
			table.fnSettings()._iDisplayStart = 0;
			table.fnDraw();
		},
		reset : function() {
			/*$('form input').val('');
			$('form select').val('');*/
			var $form=$("#searchForm");
			$form.get(0).reset();
			var treeObj = $.fn.zTree.getZTreeObj("tree");
			var nodes = treeObj.getCheckedNodes(true);
			if(nodes != '')
				treeObj.checkNode(nodes[0]);
			treeObj.expandAll(false);
			$("#productType").val('');//产品编号重置
			$("#parentProductCd").val('');//父级产品编号重置
			var table = $("#table").dataTable();
			table.fnSettings()._iDisplayStart = 0;
			table.fnDraw();
		},
		initTree : function() {
			$.fn.zTree.init($("#tree"), {
                async: {
                    enable: true,
                    url: $$ctx + "bizapply/findEffectiveProduct?isDesignated=false"
                },
                data: {
                    simpleData: {
                        enable: true,
                        idKey: "productCd",
                        pIdKey: "parentProductCd"
                    },
                    key: {
                        name: "productName"
                    }
                },
                check: {
                    enable: true,
                    chkStyle: "radio",
                    radioType: "all"
                },
                callback: {
                    onClick: function(event, treeId, treeNode) {
                    	if(treeNode!=null&&treeNode.children!=null&&treeNode.children.length!=null&&treeNode.children.length>0){
                    		return false;
                    	}else{
                    		var productCd = treeNode.productCd;
                        	var treeObj = $.fn.zTree.getZTreeObj(treeId);
                        	var node = treeObj.getNodeByParam("productCd", productCd, null);
                        	treeObj.checkNode(node, true, true);
                        	var parentNode = node.getParentNode();
                            treeObj.expandNode(parentNode, true, false);
                            $('#productCdMask').val(treeNode.productName);
                            $('#productType').val(treeNode.productCd);
                            $('#parentProductCd').val(treeNode.parentProductCd);
                            $("#controlZTree").toggle(300,
                            function() {
                                if (($("#controlZTree").attr("style")) == "") {
                                    $("#showTree")[0].innerHTML = "<i class='ace-icon fa fa-eye'></i>";
                                } else if (($("#controlZTree").attr("style")) == "display: none;") {
                                    $("#showTree")[0].innerHTML = "<i class='ace-icon fa fa-eye'></i>";
                                } else {
                                    $("#showTree")[0].innerHTML = "<i class='ace-icon fa fa-eye'></i>";
                                }
                            });
                    	}
                    },
                    onCheck: function(event, treeId, treeNode) {
                    	$('#productType').val(treeNode.productCd);
                    	$('#parentProductCd').val(treeNode.parentProductCd);
                    },
                    beforeCheck: function(treeId, treeNode) {
                    	$('#productCdMask').val(treeNode.productName);
                        return ! treeNode.isParent;
                    },
					onAsyncSuccess : function(event, treeId, treeNode, msg) {
						var treeObj = $.fn.zTree.getZTreeObj(treeId);
						var productType = $("#productType").val();
						if (productType) {
							var node = treeObj.getNodeByParam("productCd",
									productType, null);
							treeObj.checkNode(node, true, true);
							var parentNode = node.getParentNode();
							treeObj.expandNode(parentNode, true, false);
							$("#productCdMask").val(node.productName);
						}
					}
                }
            });
		},
		ToggleTree : function() {
			$("#controlZTree").toggle(300, function() {
				if (($("#controlZTree").attr("style")) == "") {
                    $("#showTree")[0].innerHTML = "<i class='ace-icon fa fa-eye'></i>";
                } else if (($("#controlZTree").attr("style")) == "display: none;") {
                    $("#showTree")[0].innerHTML = "<i class='ace-icon fa fa-eye'></i>";
                } else {
                    $("#showTree")[0].innerHTML = "<i class='ace-icon fa fa-eye'></i>";
                }
			});
		}
	});
	module.exports = view;
});