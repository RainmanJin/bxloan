define(function(require, exports, module) {
	var model = require("../main/model");
	var rm = require("./rm");
	var utils = require("utils");
	var view = Backbone.View.extend({
		el : "body",
		events : {
			"change #expenseCollectionType" : "changeExpenseCollectionType",
			"click #addExpenseRate" : "addExpenseRate",
			"change #expenseRate" : "changeExpenseRate"
		},
		initialize : function() {
			this.model = new model();
			this.render();
		},
		render : function() {
			this.initExpenseForm();
			this.initBizExpenseRateList();
			this.initBizExpenseRateButtons();
		},
		initExpenseForm : function() {
			$("#expenseForm").validate({
				rules: rm.rules,
                messages: rm.messages,
				submitHandler: function(form) {
					$.ajax({
						type : 'post',
						url : $$ctx + 'bizapply/saveBizExpenseRate',
						data : $(form).serialize(),
						success : function(result) {
							if (result.success) {
								$("#expenseModal").modal("hide");
								$("#bizExpenseTable").dataTable().fnDraw();
							} else {
								utils.alert.err("<strong>" + result.msg + "</strong>");
							}
						}
					});
				}
			});
		},
		initBizExpenseRateList : function() {
			if($('#type').val() != 'check') {
				var oTable = $("#bizExpenseTable").dataTable({
					sAjaxSource : $$ctx + "businessapplicationwd/searchBizExpenseRateList",
					bFilter : false,
					bLengthChange : false,
					aoColumnDefs : [ {
						aTargets : [ 7 ],
						mRender : function(data, type, full) {
							return "<div class='btn-group'style='width:100px;'><button title='修改' type='button' role='edit' data-id='" + data + "' class='btn btn-xs btn-info'><i class='ace-icon fa fa-edit'></i></button>"
								+"<button title='删除' type='button' role='delete' data-id='" + data + "' class='btn btn-xs btn-danger'><i class='ace-icon fa fa-trash-o'></i></button></div>";
						}
					} ],
					fnServerParams : function(aoData) {
						aoData.push({
							name : "projectNo",
							value : $('#projectNo').val()
						});
					}
				});
			} else {
				var oTable = $("#bizExpenseTable").dataTable({
					sAjaxSource : $$ctx + "businessapplicationwd/searchBizExpenseRateList",
					bFilter : false,
					bLengthChange : false,
					fnServerParams : function(aoData) {
						aoData.push({
							name : "projectNo",
							value : $('#projectNo').val()
						});
					}
				});
			}
		},
		initBizExpenseRateButtons : function() {
			var viewSelf = this;
			$(document).on("click", "button[role='edit']", function(e) {
				var id = $(this).data("id");
				$.ajax({
					type : 'post',
					url : $$ctx + 'bizapply/searchBizExpenseRate/'+ id,
					success : function(r) {
						$.each($("#expenseForm").find("input, select"), function(i, item) {
							$(this).val(r.data[$(this).attr("name")]);
						});
						viewSelf.switchExsColType();
						$("#expenseModal div.modal-header h4").html("<i class='ace-icon fa fa-edit'></i>费用信息");
						$("#expenseModal").modal("show");
					}
				});
			});
			
			$(document).on("click", "button[role='delete']", function(e) {
				var id = $(this).data("id");
				var viewSelf = this;
				utils.button.confirm(null, function(result) {
					if (result) {
						$.ajax({
							type : 'post',
							url : $$ctx + 'bizapply/deleteBizExpenseRate/'+ id,
							success : function(r) {
								if(r.success) {
									utils.alert.suc("<strong>费用信息删除成功！</strong>");
									$("#bizExpenseTable").dataTable().fnDraw();
								} else {
									utils.alert.err("<strong>费用信息删除失败！</strong>");
								}
							}
						});
					}
				});
			});
		},
		changeExpenseCollectionType : function() {
			$("#expenseForm").find("input[type!=hidden]").val("");
			this.switchExsColType();
		},
		switchExsColType : function() {
			var type = $("#expenseCollectionType").val();
			if (type == "1") {// 比例
				$(".hideExpenseRate").slideDown();
				$("#expenseAmt").attr("readonly", "readonly");
				$("#expenseRate").addClass("required");
			} else {// 金额
				$(".hideExpenseRate").slideUp();
				$("#expenseAmt").removeAttr("readonly");
				$("#expenseRate").removeClass("required");
			}
		},
		changeExpenseRate : function() {
			var selector = "#expenseRate";
			if ($(selector).valid()) {
				var expenseRate = parseFloat($(selector).val());
				var applyAmt = utils.num.normal($('#applyAmt').val());
				var expenseAmt=applyAmt * expenseRate / 100;
				$("#expenseAmt").val(expenseAmt.toFixed(2));
			}
		},
		addExpenseRate : function() {
			$("#expenseForm").resetForm();
			$("#expenseForm").find("input[name='bizExpenseRateId']").val("");
			this.switchExsColType();
			$("#expenseModal div.modal-header h4").html("<i class='ace-icon fa fa-plus'></i>费用信息");
			$("#expenseModal").modal("show");
		}
	});
	module.exports = view;
});