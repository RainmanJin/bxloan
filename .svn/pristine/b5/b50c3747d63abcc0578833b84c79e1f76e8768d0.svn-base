define(function(require, exports, module) {
	var success = "<i class='ace-icon fa fa-check bigger-300' style='color: green;'>&nbsp</i>";
	var error = "<i class='ace-icon fa fa-times bigger-300' style='color: red;'>&nbsp</i>";
	var tip = "<i class='ace-icon fa fa-exclamation bigger-300' style='color: orange;'>&nbsp</i>";
	
	var model = require("./model");
	var rm = require("./rm");
	var utils = require("utils");
	var view = Backbone.View.extend({
		el: "body",
		events: {
			"click #btn-add": "add",
			"click #showTree" : "ToggleTree"
		},
		initialize: function() { /** 初始化 */
			this.model = new model();
			this.render();
		},
		add: function() { // 新增按钮事件
			$("#productForm").resetForm();
			$('#productForm').find("input[type='checkbox']").removeAttr('checked');
			$('#productForm').find("input[type='hidden'], select").val('');
			var treeObj = $.fn.zTree.getZTreeObj("tree");
			var nodes = treeObj.getCheckedNodes(true);
			if(nodes != '')
				treeObj.checkNode(nodes[0]);
			treeObj.expandAll(false);
			$("#controlZTree").attr("style","display:none;");
			$("#modal-productForm div.modal-header h4").html("<i class='ace-icon fa fa-plus'></i> 新增产品定价");
			$("#modal-productForm").modal("show");
		},
		render: function() { /** 页面渲染 */
			this.initPage();
			this.initDataTables();
			this.editBtnLive();
			this.delBtnLive();
			this.submitForm();
			this.initTree();
		},
		initPage : function() {
			
			$(document).on("click", "input[type=checkbox]", function(e) {
				if($(this).is(':checked'))
					$('#repaymentType').val($('#repaymentType').val() + $(this).val() + ',');
				else
					$('#repaymentType').val($('#repaymentType').val().replace($(this).val() + ',',''));
			});
		},
		initDataTables: function() { /** 初始化DataTables */
			var viewSelf = this;
			var oTable = $("#tbl").dataTable({
		    	sAjaxSource: $$ctx + "productPrice/findBySearch",
		    	bFilter : false,
				bLengthChange : false,
		    	aoColumnDefs : [
                      {
                          "aTargets" : [7],
                          "mRender" : function(data, type, full){
                          	var buttons = "<div class='btn-group'style='width:100px;'><button type='button' role='edit' title='修改' data-id='" + data + "'  class='btn btn-xs btn-info'><i class='ace-icon fa fa-edit'></i></button> "
                          				+ "<button type='button' role='delete' title='删除' data-id='" + data+ "'  class='btn btn-xs btn-danger'><i class='ace-icon fa fa-trash-o'></i></button></div> ";
                          				return buttons;
                          }
                      }
		    	]
			});
			viewSelf.oTable = oTable;
		},
		editBtnLive: function() { /** 修改按钮的预绑定事件 */
			$(document).on("click", "button[role='edit']", function(e) {
				
				$.ajax({
					type : 'post',
					url : $$ctx + 'productPrice/get',
					data : {
						'id' : $(this).data("id")
					},
					success : function(result) {
						
						setTimeout(function() {
							
							$.each($("#productForm").find("input[type=text], input[type=hidden], select"), function() {
								$(this).val(result[$(this).attr("name")]);
							});
							
							$('#productForm').find("input[type='checkbox']").removeAttr('checked');
							$.each($('#repaymentType').val().split(","), function(i, val) {
								var check = $('#productForm').find("input[type=checkbox][value='"+val+"']");
								if(check[0])
									check[0].checked = true;
							});
							
							$("#controlZTree").attr("style","display:none;");
							var treeObj = $.fn.zTree.getZTreeObj("tree");
							treeObj.reAsyncChildNodes(null, "refresh");
							
							$("#modal-productForm div.modal-header h4").html("<i class='ace-icon fa fa-edit'></i> 修改产品定价");
							$("#modal-productForm").modal("show");
						} , 60);
						
					}
				});
			});
		},
		delBtnLive: function() { /** 删除按钮的预绑定事件 */
			var viewSelf = this;
			$(document).on("click", "button[role='delete']", function(e) { // 动态绑定所有删除按钮的click事件
				var btnSelf = $(this);
				bootbox.confirm({
					message: "您确定要删除此记录吗？",
					buttons: {
						confirm: {
						label: "<i class='ace-icon fa fa-trash-o bigger-110'></i> 确定",
							className: "btn-danger btn-sm"
						},
						cancel : {
							label: "<i class='ace-icon fa fa-times bigger-110'></i> 取消",
							className: "btn-sm"
						}
					},
					callback: function(result) {
						if (result) {
							viewSelf.model.delProductPriceById(btnSelf.data("id"), function(r) {
							if (r.success) {
								bootbox.alert(success + "<strong>产品定价删除成功！<strong>");
								viewSelf.oTable.fnDraw(); // 重新加载表格中的数据
							} else {
								bootbox.alert(error + "<strong>产品定价删除失败！<strong>");
							}
						});
					 }
					}
				});
			});
	},
		submitForm: function() { /** 提交form */
			var viewSelf = this;
			$("#productForm").validate({
				rules: rm.rules,
 				submitHandler: function(form) {
 					if($('#repaymentType').val() == '') {
 						
 					} else {
 						var formSelf = $(form);
 						viewSelf.model.submitForm(formSelf, function(r) {
 							if (r.success) {
 								$("#modal-productForm").modal("hide");
 								$('#form-field-0').val('');
 								formSelf.resetForm();
 								viewSelf.oTable.fnDraw(); // 重新加载表格中的数据
 							} else {
 								bootbox.alert(tip + "<strong>" + r.msg + "</strong>");
 							}
 						});
 					}
				}
			});
		},
		initTree : function() {
			$.fn.zTree.init($("#tree"), {
                async: {
                    enable: true,
                    url: $$ctx + "bizapply/findEffectiveProduct?isDesignated=true"
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
                            $('#loanProduct').val(treeNode.productCd);
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
                    	$('#loanProduct').val(treeNode.productCd);
                    	$('#parentProductCd').val(treeNode.parentProductCd);
                    },
                    beforeCheck: function(treeId, treeNode) {
                        return ! treeNode.isParent;
                    },
					onAsyncSuccess : function(event, treeId, treeNode, msg) {
						var treeObj = $.fn.zTree.getZTreeObj(treeId);
						var loanProduct = $("#loanProduct").val();
						if (loanProduct) {
							var node = treeObj.getNodeByParam("productCd",
									loanProduct, null);
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