define(function(require, exports, module) {
	var rm = require("./rm");
	var utils = require("utils");
	var view = Backbone.View.extend({
		el : "body",
		events : {
			"change #expenseCollectionType" : "changeExpenseCollectionType",  //选择费用收费方式
			"click #addExpenseRate" : "addExpenseRate",  //点击新增按钮
			"change #expenseRate" : "changeExpenseRate"  //改变费率
		},
		initialize : function() {  //初始化
			this.render();
		},
		render : function() {  //渲染
			this.initExpenseForm(); //初始化form
			this.initBizExpenseRateList();  //初始化费用列表
			this.initBizExpenseRateButtons();  //初始化修改删除按钮
		},
		initExpenseForm : function() { //初始化form
			$("#expenseForm").validate({
				rules: rm.rules,
                messages: rm.messages,
				submitHandler: function(form) {  //保存事件
					$.ajax({
						type : 'post',
						url : $$ctx + 'bizapply/saveBizExpenseRate',
						data : $(form).serialize(),
						success : function(result) {
							if (result.success) {
								$("#expenseModal").modal("hide");            //隐藏费用信息弹框
								$("#bizExpenseTable").dataTable().fnDraw();  //页面表格重绘
							} else {
								utils.alert.err("<strong>" + result.msg + "</strong>");
							}
						}
					});
				}
			});
		},
		initBizExpenseRateList : function() {  //初始化费用列表
			if($('#type').val() != 'check') {  //页面不是通过查询事项进来，刚显示修改删除按钮
				var oTable = $("#bizExpenseTable").dataTable({
					sAjaxSource : $$ctx + "businessapplicationwd/searchBizExpenseRateList",
					bFilter : false,
					bLengthChange : false,
					aoColumnDefs : [{
						aTargets : [7],
						mRender : function(data, type, full) {
							return "<div class='btn-group'style='width:100px;'><button title='修改' type='button' role='edit' data-id='" + data + "' class='btn btn-xs btn-info'><i class='ace-icon fa fa-edit'></i></button>"
								+"<button title='删除' type='button' role='delete' data-id='" + data + "' class='btn btn-xs btn-danger'><i class='ace-icon fa fa-trash-o'></i></button></div>";
						}
					}],
					fnServerParams : function(aoData) {
						aoData.push({
							name : "projectNo",
							value : $('#projectNo').val()
						});
					}
				});
			} else { //页面通过查询事项进来，不显示修改删除按钮
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
		initBizExpenseRateButtons : function() {  //初始化修改删除按钮
			var viewSelf = this;
			//修改按钮
			$(document).on("click", "button[role='edit']", function(e) {
				var id = $(this).data("id");
				$.ajax({
					type : 'post',
					url : $$ctx + 'bizapply/searchBizExpenseRate/'+ id,
					success : function(r) {   //查询出费用信息，反显进行修改
						$.each($("#expenseForm").find("input, select"), function(i, item) {
							$(this).val(r.data[$(this).attr("name")]);
						});
						viewSelf.switchExsColType();  
						$("#expenseModal div.modal-header h4").html("<i class='ace-icon fa fa-edit'></i>费用信息");
						$("#expenseModal").modal("show");  //弹出费用信息弹框
					}
				});
			});
			//删除按钮
			$(document).on("click", "button[role='delete']", function(e) {
				var id = $(this).data("id");
				var viewSelf = this;
				utils.button.confirm(null, function(result) {
					if (result) {
						$.ajax({
							type : 'post',
							url : $$ctx + 'bizapply/deleteBizExpenseRate/'+ id,
							success : function(r) {  //删除费用信息
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
			this.switchExsColType();  //页面相关控件的属性及显示
		},
		switchExsColType : function() {  //根据费用收费方式来判断页面相关控件的属性及显示
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
		changeExpenseRate : function() {  //根据费率值计算实际收费
			var selector = "#expenseRate";
			if ($(selector).valid()) {
				var expenseRate = parseFloat($(selector).val());
				var applyAmt = utils.num.normal($('#applyAmt').val());
				var expenseAmt=applyAmt * expenseRate / 100;
				$("#expenseAmt").val(expenseAmt.toFixed(2));
			}
		},
		addExpenseRate : function() {  //点击新增按钮，弹出费用信息弹框
			$("#expenseForm").resetForm();
			$("#expenseForm").find("input[name='bizExpenseRateId']").val("");
			this.switchExsColType();
			$("#expenseModal div.modal-header h4").html("<i class='ace-icon fa fa-plus'></i>费用信息");
			$("#expenseModal").modal("show");
		}
	});
	module.exports = view;
});