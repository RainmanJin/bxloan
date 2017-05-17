define(function(require, exports, module) {
	var rm = require("./rm");
	var utils = require("utils");
	var view = Backbone.View.extend({
		el : "body",
		events : {
			/** 收费方式改变时，置空表单 */
			"change #expenseCollectionType" : "changeExpenseCollectionType",
			/** 根据费率计算实际费用 */
			"change #expenseRate" : "changeExpenseRate",
			/** 增加费用列表 */
			"click #addExpenseRate" : "addExpenseRate",
			/** 修改费用列表 */
			"click button[role='edit']" : "editExpenseRate",
			/** 删除费用列表 */
			"click button[role='delete']" : "deleteExpenseRate"
		},
		initialize : function() {
			this.render();
		},
		render : function() {
			this.initExpenseForm();
			this.initBizExpenseRateList();
		},
		initExpenseForm : function() {
			$("#expenseForm").validate({
				rules: rm.rules,
                messages: rm.messages,
				submitHandler: function(form) {
					utils.button.ban("#add-simple-submit");//禁用按钮
					$.ajax({
						type : 'post',
						url : $$ctx + 'underCreditContractMng/saveBizExpenseRate',
						data : $(form).serialize(),
						success : function(result) {
							if (result.success) {
								$("#expenseModal").modal("hide");
								$("#bizExpenseTable").dataTable().fnDraw();
								utils.alert.suc("<b>" + result.msg + "</b>", function(){
		                        	utils.button.reset("#add-simple-submit");//启用按钮
		                    	});
							} else {
								utils.alert.err("<strong>" + result.msg + "</strong>", function() {
									utils.button.reset("#add-simple-submit");
								});
							}
						}
					});
				}
			});
		},
		initBizExpenseRateList : function() {
			var oTable = $("#bizExpenseTable").dataTable({
				sAjaxSource : $$ctx + "underCreditContractMng/searchBizExpenseRateList",
				bFilter : false,
				bLengthChange : false,
				aoColumnDefs : [
				    {
					aTargets : [ 7 ],
					mRender : function(data, type, full) {
						//console.log($("#projectNo").val()+"==="+full[8]);
						if($('#type').val() != 'check'){
							if($("#projectNo").val()==full[8]){
								var button = "<div class='btn-group'style='width:100px;'><button title='查看' type='button' role='edit' data-id='" + data + "' class='btn btn-xs btn-info'><i class='ace-icon fa fa-edit'></i></button>";
								button += "<button title='删除' type='button' role='delete' data-id='" + data + "' class='btn btn-xs btn-danger'><i class='ace-icon fa fa-trash-o'></i></button></div>";
								return button;
							}
						}
						return "";
					}
				} ],
				fnServerParams : function(aoData) {
					aoData.push({
						name : "creditProjectNo",
						value : $("#creditProjectNo").val()//'Y123011211050001'
					},{
						name : "underProjectNo",
						value : $("#projectNo").val()
					});
				}
			});
		},
		editExpenseRate :function(e){
			var viewSelf = this;
			var $btnSelf = $(e.currentTarget);
			var id = $btnSelf.data("id");
			$.ajax({
				type : 'post',
				url : $$ctx + 'underCreditContractMng/searchBizExpenseRate/'+ id,
				success : function(r) {
					$.each($("#expenseForm").find("input, select"), function(i, item) {
						$(this).val(r.data[$(this).attr("name")]);
					});
					viewSelf.switchExsColType();
					$("#expenseModal div.modal-header h4").html("<i class='ace-icon fa fa-edit'></i>费用信息");
					$("#expenseModal").modal("show");
				}
			});
		},
		deleteExpenseRate :function(e){
			var $btnSelf = $(e.currentTarget);
			var id = $btnSelf.data("id");
			utils.button.confirm(null, function(result) {
				if (result) {
					$.ajax({
						type : 'post',
						url : $$ctx + 'underCreditContractMng/deleteBizExpenseRate/'+ id,
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