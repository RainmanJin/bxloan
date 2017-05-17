define(function(require, exports, module) {
	var model = require("./model");
	var utils = require("utils");
	
	var view = Backbone.View.extend({
		el: "body",
		events: {
			"click #btn-add": "add",
			"click #btn-getSelectedNode": "getSelectedNode"
		},
		initialize: function() { /** 初始化 */
			this.model = new model();
			this.render();
		},
		render: function() { /** 页面渲染 */
			this.initDataTables();
			this.editBtnLive();
			this.delBtnLive();
			this.submitForm();
			this.initTree();
			this.test();
		},
		add: function() { // 新增按钮事件
			$("#modal-form div.modal-header h4").html("<i class='ace-icon fa fa-plus'></i> 新增用户");
			$("#modal-form").modal("show");
		},
		test: function(){
			utils.dd.translateDict("CustomerType","1",function(dictName){
			})
		},
		initDataTables: function() { /** 初始化DataTables */
			var viewSelf = this;
			utils.dd.initDataDict(["RoleTypeForTest", "GuaranteeTypeCode"], function(dataDict) {
				var oTable = $("#tbl").dataTable({
			    	sAjaxSource: "userMng/findBySearch",
			    	aoColumns: [
			    	    {mData: "id"}, // 序号(被fnCreateRow重写)
			    	    {mData: "username"},
			    	    {mData: "name"},
			    	    {mData: "role", mRender: function(data, type, rowdata) {
			    	    	return dataDict.RoleTypeForTest[data];
			    	    }},
			    	    {mData: "operation", mRender: function(data, type, rowdata) {
			    	    	var operation = 
			    	    		"<div class='btn-group'>" +
				    				"<button role='edit' data-id='" + rowdata.id + "' class='btn btn-xs btn-info'><i class='ace-icon fa fa-edit'></i></button>" +
				    				"<button role='delete' data-id='" + rowdata.id + "' class='btn btn-xs btn-danger'><i class='ace-icon fa fa-minus'></i></button>" +
				    			"</div>";
			    	    	return operation;
			    		}}
			    	],
			    	fnCreatedRow: function(nRow, aData, iDataIndex) {
			    		$('td:eq(0)', nRow).html(iDataIndex + 1);
			        }
				});
				viewSelf.oTable = oTable;
			});
		},
		editBtnLive: function() { /** 修改按钮的预绑定事件 */
			var viewSelf = this;
			$(document).on("click", "button[role='edit']", function(e) { // 动态绑定所有删除按钮的click事件
				var $this = $(this);
				viewSelf.model.getUserById($this.data("id"), function(obj) {
					$.each($("#modal-form").find("input, select, textarea"), function() {
						$(this).val(obj[$(this).attr("name")]);
					});
					$("#modal-form div.modal-header h4").html("<i class='ace-icon fa fa-edit'></i> 修改用户");
					$("#modal-form").modal("show");
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
							viewSelf.model.delUserById(btnSelf.data("id"), function(r) {
								if (r.success) {
									bootbox.alert("用户删除成功！");
									viewSelf.oTable.fnDraw(); // 重新加载表格中的数据
								}
							});
						}
					}
				});
			});
		},
		submitForm: function() { /** 提交form */
			var viewSelf = this;
			$("#userForm").validate({
				submitHandler: function(form) {
					var formSelf = $(form);
					viewSelf.model.submitForm(formSelf, function(r) {
						if (r.success) {
							bootbox.alert("用户保存成功！");
							$("#modal-form").modal("hide");
							formSelf.resetForm();
							viewSelf.oTable.fnDraw(); // 重新加载表格中的数据
						}
					});
				}
			});
		},
		initTree: function() {
			var viewSelf = this;
			viewSelf.model.getIndustryType(function(zNodes) {
				$.fn.zTree.init($("#treeDemo"), {
					data: {
						simpleData: { enable: true, idKey: "industryTypeCd", pIdKey: "parentIndustryTypeCd" },
						key: { name: "industryTypeName" }
					},
					check: { enable: true, chkStyle: "radio", radioType: "all" },
					callback: {
						beforeCheck: function(treeId, treeNode) {
							return !treeNode.isParent;
						}
					}
				}, zNodes);
			});
		},
		getSelectedNode: function() {
			var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
			var nodes = treeObj.getCheckedNodes();
			if (nodes.length == 0) {
				alert("看看你选了吗");
			} else {
				$.each(nodes, function(i, node) {
					alert(node.industryTypeCd + " - " + node.industryTypeName);
				});
			}
		}
	});
	module.exports = view;
});