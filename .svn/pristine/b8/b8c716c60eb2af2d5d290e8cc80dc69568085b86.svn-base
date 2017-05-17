define(function(require, exports, module) {
	var model = require("../../main/model");
	var rm_industry_biz = require("./rm_industry_biz");
	var rm_other_biz_income = require("./rm_other_biz_income");
	var rm_stock = require("./rm_stock");
	var rm_family_consume = require("./rm_family_consume");
	var utils = require("utils");
	var view = Backbone.View.extend({
		el : "body",
		events : {
			"click #create_other_income" : "create_other_biz_income",
			"click #create_stock" : "create_stock",
			"click #save_IndustryBiz_BasicInfo" : "save_IndustryBiz_BasicInfo"
		},
		initialize : function() {
			this.model = new model();
			this.render();
		},
		render : function() {
			this.isView=utils.parseBool($("#isView").val());//是否显示
			this.isEdit=utils.parseBool($("#isEdit").val());//是否是编辑
			this.initPage();
			this.initBasicForm();
			this.initOtherBizIncomeForm();
			this.initOtherBizIncomeTable();
			this.initStockTable();
			this.initStockForm();
			this.initFamilyConsumeForm();
			this.initStatisticsTable();
			this.onChangeAndCount();
		},
		initPage : function() {
			// 将datepicker控件放在最顶层
			var dateOnTop = function() {
				$(".datepicker").css("z-index", "99999");
			};
			var defaultDateConf = {
				autoclose : true,
				clearBtn : true
			};
			var initDateP = function(selector, changeCallBack, config) {
				var _dateConf = config || defaultDateConf;
				$(selector).datepicker(_dateConf).on("changeDate",
						changeCallBack).on("click", dateOnTop);
			};
			initDateP($("#industry_biz_form").find("input[name='boughtBuiltDate']"));
			initDateP($("#industry_biz_form").find("input[name='businessStartDate']"));
			initDateP($("#other_biz_income_form").find("input[name='businessStartDate']"));
		},
		initBasicForm : function() {
			$("#industry_biz_form").validate({
				errorPlacement : function(error, element) { // 指定错误信息位置
					if (element.is(':radio') || element.is(':checkbox')) { // 如果是radio或checkbox
						var eid = element.attr('name'); // 获取元素的name属性
						error.appendTo(element.parent().parent()); // 将错误信息添加当前元素的父结点后面
					} else {
						error.insertAfter(element);
					}
				},
				rules : rm_industry_biz.rules,
				messages : rm_industry_biz.messages
				/*submitHandler : function(form) {
//					var industries = "";
//					$(form).find("input:checkbox[name=industry]:checked").each(function(i, item) {
//						industries += $(item).val() + ",";
//					});
//					industries = industries.substring(0, industries.length - 1);
					$.ajax({
						type : 'POST',
						url : $$ctx + 'industryBiz/saveBasicInfo',
						data : $(form).serialize(),
						success : function(result) {
							if (result.success) {
								utils.alert.suc(result.msg);
								$(form).find("input[name='industryBizId']").val(result.data.industryBizId);
								$(form).find("input[name='transportId']").val(result.data.transportId);
								$("#statisticsTable").dataTable().fnDraw();
							} else {
								utils.alert.err(result.msg);
							}
						}
					});
				}*/
			});
			
			$.ajax({
				type : 'POST',
				url : $$ctx + 'industryBiz/findOneIndustryBizVOByProjectId',
				data : {
					'projectId' : $("#projectId").val()
				},
				success : function(result) {
					if (typeof(result) != 'undefined' && $.trim(result) != '') {
						var formSelector = "#industry_biz_form";
						$.each($(formSelector).find("input[type=text], input[type=hidden], select, textarea"), function() {
							$(this).val(result[$(this).attr("name")]);
						});
						$(formSelector).find("input[type='checkbox']").removeAttr('checked');
						$.each(result.industry.split(","), function(i, val) {
							var check = $(formSelector).find("input[type=checkbox][value='"+val+"']");
							if(check[0]) {
								check[0].checked = true;
							}
						});
					}
				}
			});
		},
		initOtherBizIncomeForm : function() {
			$("#other_biz_income_form").validate({
				errorPlacement : function(error, element) { // 指定错误信息位置
					if (element.is(':radio') || element.is(':checkbox')) { // 如果是radio或checkbox
						var eid = element.attr('name'); // 获取元素的name属性
						error.appendTo(element.parent().parent()); // 将错误信息添加当前元素的父结点后面
					} else {
						error.insertAfter(element);
					}
				},
				rules : rm_other_biz_income.rules,
				messages : rm_other_biz_income.messages,
				submitHandler : function(form) {
//					var industries = "";
//					$(form).find("input:checkbox[name=industry]:checked").each(function(i, item) {
//						industries += $(item).val() + ",";
//					});
//					industries = industries.substring(0, industries.length - 1);
					$.ajax({
						type : 'POST',
						url : $$ctx + 'industryBiz/saveOtherBizIncome',
						data : $(form).serialize(),
						success : function(result) {
							if (result.success) {
								$("#statisticsTable").dataTable().fnDraw();
								$("#other_biz_income_modal").modal("hide");
								$("#other_biz_income_table").dataTable().fnDraw();
							} else {
								utils.alert.err(result.msg);
							}
						}
					});
				}
			});
		},
		initOtherBizIncomeTable : function() {
			var viewSelf=this;
			$("#other_biz_income_table").dataTable({
				bFilter : false,
				bLengthChange : false,
				bSort : false,
				sAjaxSource: $$ctx + "industryBiz/initOtherBizIncomeTable",
		    	aoColumns: [
		    	    {mData : "businessStartDate", mRender: function(data, type, rowdata) {
		    	    	return utils.date.formatDate(data);
		    	    }},
		    	    {mData : "initialCapital"},
		    	    {mData : "businessScope"},
		    	    {mData : "settlementPeriod"},
		    	    {mData : "customerPercentage"},
		    	    {mData : "gainTotal"},
		    	    {mData : "costTotal"},
		    	    {mData : null, mRender: function(data, type, rowdata) {
		    	    	var html=[];
		    	    	html.push("<div class='btn-group' style='width:100px;'>");
		    	    	if(viewSelf.isEdit){
		    	    		html.push("<button title='修改' type='button' class='btn btn-xs btn-info' role='edit_biz_income' data-id='" + data + "' data-original-title='修改'>");
		    	    		html.push("<i class='ace-icon fa fa-edit'></i>");
		    	    		html.push("</button>");
		    	    	}
		    	    	html.push("<button title='查看' type='button' class='btn btn-xs btn-yellow' role='view_biz_income' data-id='" + data + "' data-original-title='查看'>");
		    	    	html.push("<i class='ace-icon fa fa-eye'></i>");
		    	    	html.push("</button>");
		    	    	if(viewSelf.isEdit){
			    	    	html.push("<button title='删除' type='button' class='btn btn-xs btn-danger' role='delete_biz_income' data-id='" + data + "'>");
			    	    	html.push("<i class='ace-icon fa fa-trash-o'></i>");
			    	    	html.push("</button>");
		    	    	}
		    	    	html.push("</div>");
		    	    	return html.join('');
		    	    	/*return "" +
		    	    	"<div class='btn-group' style='width:100px;'>" +
							"<button title='修改' type='button' class='btn btn-xs btn-info' role='edit_biz_income' data-id='" + data + "' data-original-title='修改'>" +
								"<i class='ace-icon fa fa-edit'></i>" +
							"</button>" +
							"<button title='查看' type='button' class='btn btn-xs btn-yellow' role='view_biz_income' data-id='" + data + "' data-original-title='查看'>" +
								"<i class='ace-icon fa fa-eye'></i>" +
							"</button>" +
							"<button title='删除' type='button' class='btn btn-xs btn-danger' role='delete_biz_income' data-id='" + data + "'>" +
								"<i class='ace-icon fa fa-trash-o'></i>" +
							"</button>" +
						"</div>";*/
		    	    }},
		    	],
		    	fnServerParams : function(aoData) {
					aoData.push({
						name : "projectId",
						value : $('#projectId').val()
					}, {
						name : "type",
						value : "2"
					});
				}
			});
			
			$(document).on("click", "button[role='edit_biz_income']", function(e) {
				var id = $(this).data("id");
				$.ajax({
					type : 'POST',
					url : $$ctx + 'industryBiz/findOneBizIncome',
					data : {
						'id' : id
					},
					success : function(result) {
						$.each($("#other_biz_income_form").find("input[type=text], input[type=hidden], select, textarea"), function() {
							$(this).val(result[$(this).attr("name")]);
							$(this).removeAttr("disabled");
						});
						$("#other_biz_income_modal div.modal-header h3").html("<i class='ace-icon fa fa-edit'></i> 其他工商收入");
						$("#other_biz_income_modal").find(".modal-footer").show();
						$("#other_biz_income_modal").modal("show");
					}
				});
			});
			$(document).on("click", "button[role='view_biz_income']", function(e) {
				var id = $(this).data("id");
				$.ajax({
					type : 'POST',
					url : $$ctx + 'industryBiz/findOneBizIncome',
					data : {
						'id' : id
					},
					success : function(result) {
						$.each($("#other_biz_income_form").find("input[type=text], input[type=hidden], select, textarea"), function() {
							$(this).val(result[$(this).attr("name")]);
							$(this).attr("disabled", "disabled");
						});
						$("#other_biz_income_modal div.modal-header h3").html("<i class='ace-icon fa fa-eye'></i> 其他工商收入");
						$("#other_biz_income_modal").find(".modal-footer").hide();
						$("#other_biz_income_modal").modal("show");
					}
				});
			});
			$(document).on("click", "button[role='delete_biz_income']", function(e) {
				var id = $(this).data("id");
				bootbox.confirm({
					message: "您确定要删除此记录吗？",
					buttons: {
						confirm: {
						label: "<i class='ace-icon fa fa-trash-o bigger-110'></i> 确定",
							className: "btn-danger btn-sm"
						},
						cancel : {
							label: "<i class='ace-icon fa fa-times bigger-110'></i> 取消",
							className: "btn-warning btn-sm"
						}
					},
					callback: function(result) {
						if (result) {
							$.ajax({
								type : 'post',
								url : $$ctx + 'industryBiz/deleteBizIncome',
								data : {
									'id' : id
								},
								success : function(r) {
									if(r.success) {
										utils.alert.suc(r.msg);
										$("#other_biz_income_table").dataTable().fnDraw();
										$("#statisticsTable").dataTable().fnDraw();
									} else {
										utils.alert.err(r.msg);
									}
								}
							});
					    }
					}
				});
			});
		},
		create_other_biz_income : function() {
			$("#other_biz_income_modal div.modal-header h3").html("<i class='ace-icon fa fa-plus'></i> 其他工商收入");
			$("#other_biz_income_form").resetForm();
			$("#other_biz_income_modal").find(".modal-footer").show();
			$("#other_biz_income_modal").find("input, select").removeAttr("disabled");
			$("#other_biz_income_modal").modal("show");
		},
		initStockTable : function() {
			var viewSelf=this;
			$("#stock_table").dataTable({
				bFilter : false,
				bLengthChange : false,
				bSort : false,
				sAjaxSource: $$ctx + "industryBiz/initStockTable",
		    	aoColumns: [
		    	    {mData : "type"},
		    	    {mData : "name"},
		    	    {mData : "price"},
		    	    {mData : "unit"},
		    	    {mData : "num"},
		    	    {mData : "total"},
		    	    {mData : "id", mRender: function(data, type, rowdata) {
		    	    	var html=[];
		    	    	html.push("<div class='btn-group' style='width:100px;'>");
		    	    	if(viewSelf.isEdit){
		    	    		html.push("<button title='修改' type='button' class='btn btn-xs btn-info' role='edit_stock' data-id='" + data + "' data-original-title='修改'>");
		    	    		html.push("<i class='ace-icon fa fa-edit'></i>");
		    	    		html.push("</button>");
		    	    	}
		    	    	html.push("<button title='查看' type='button' class='btn btn-xs btn-yellow' role='view_stock' data-id='" + data + "' data-original-title='查看'>");
		    	    	html.push("<i class='ace-icon fa fa-eye'></i>");
		    	    	html.push("</button>");
		    	    	if(viewSelf.isEdit){
		    	    		html.push("<button title='删除' type='button' class='btn btn-xs btn-danger' role='delete_stock' data-id='" + data + "'>");
		    	    		html.push("<i class='ace-icon fa fa-trash-o'></i>");
		    	    		html.push("</button>");
		    	    	}
		    	    	html.push("</div>");
		    	    	return html.join('');
		    	    	/*return "" +
		    	    	"<div class='btn-group' style='width:100px;'>" +
		    	    	"<button title='修改' type='button' class='btn btn-xs btn-info' role='edit_stock' data-id='" + data + "' data-original-title='修改'>" +
		    	    	"<i class='ace-icon fa fa-edit'></i>" +
		    	    	"</button>" +
		    	    	"<button title='查看' type='button' class='btn btn-xs btn-yellow' role='view_stock' data-id='" + data + "' data-original-title='查看'>" +
		    	    	"<i class='ace-icon fa fa-eye'></i>" +
		    	    	"</button>" +
		    	    	"<button title='删除' type='button' class='btn btn-xs btn-danger' role='delete_stock' data-id='" + data + "'>" +
		    	    	"<i class='ace-icon fa fa-trash-o'></i>" +
		    	    	"</button>" +
		    	    	"</div>";*/
		    	    }},
		    	],
		    	fnServerParams : function(aoData) {
					aoData.push({
						name : "projectId",
						value : $('#projectId').val()
					});
				}
			});
			
			$(document).on("click", "button[role='edit_stock']", function(e) {
				var id = $(this).data("id");
				$.ajax({
					type : 'POST',
					url : $$ctx + 'industryBiz/findOneStock',
					data : {
						'id' : id
					},
					success : function(result) {
						$.each($("#stock_form").find("input[type=text], input[type=hidden], select, textarea"), function() {
							$(this).val(result[$(this).attr("name")]);
							$(this).removeAttr("disabled");
						});
						$("#stock_modal div.modal-header h3").html("<i class='ace-icon fa fa-edit'></i> 库存");
						$("#stock_modal").find(".modal-footer").show();
						$("#stock_modal").modal("show");
					}
				});
			});
			$(document).on("click", "button[role='view_stock']", function(e) {
				var id = $(this).data("id");
				$.ajax({
					type : 'POST',
					url : $$ctx + 'industryBiz/findOneStock',
					data : {
						'id' : id
					},
					success : function(result) {
						$.each($("#stock_form").find("input[type=text], input[type=hidden], select, textarea"), function() {
							$(this).val(result[$(this).attr("name")]);
							$(this).attr("disabled", "disabled");
						});
						$("#stock_modal div.modal-header h3").html("<i class='ace-icon fa fa-eye'></i> 库存");
						$("#stock_modal").find(".modal-footer").hide();
						$("#stock_modal").modal("show");
					}
				});
			});
			$(document).on("click", "button[role='delete_stock']", function(e) {
				var id = $(this).data("id");
				bootbox.confirm({
					message: "您确定要删除此记录吗？",
					buttons: {
						confirm: {
						label: "<i class='ace-icon fa fa-trash-o bigger-110'></i> 确定",
							className: "btn-danger btn-sm"
						},
						cancel : {
							label: "<i class='ace-icon fa fa-times bigger-110'></i> 取消",
							className: "btn-warning btn-sm"
						}
					},
					callback: function(result) {
						if (result) {
							$.ajax({
								type : 'post',
								url : $$ctx + 'industryBiz/deleteStock',
								data : {
									'id' : id
								},
								success : function(r) {
									if(r.success) {
										utils.alert.suc(r.msg);
										$("#stock_table").dataTable().fnDraw();
										$("#statisticsTable").dataTable().fnDraw();
									} else {
										utils.alert.err(r.msg);
									}
								}
							});
					    }
					}
				});
			});
		},
		initStockForm : function() {
			$("#stock_form").validate({
				errorPlacement : function(error, element) { // 指定错误信息位置
					if (element.is(':radio') || element.is(':checkbox')) { // 如果是radio或checkbox
						var eid = element.attr('name'); // 获取元素的name属性
						error.appendTo(element.parent().parent()); // 将错误信息添加当前元素的父结点后面
					} else {
						error.insertAfter(element);
					}
				},
				rules : rm_stock.rules,
				messages : rm_stock.messages,
				submitHandler : function(form) {
//					var industries = "";
//					$(form).find("input:checkbox[name=industry]:checked").each(function(i, item) {
//						industries += $(item).val() + ",";
//					});
//					industries = industries.substring(0, industries.length - 1);
					$.ajax({
						type : 'POST',
						url : $$ctx + 'industryBiz/saveStock',
						data : $(form).serialize(),
						success : function(result) {
							if (result.success) {
								$("#stock_modal").modal("hide");
								$("#stock_table").dataTable().fnDraw();
								$("#statisticsTable").dataTable().fnDraw();
							} else {
								utils.alert.err(result.msg);
							}
						}
					});
				}
			});
		},
		create_stock : function() {
			$("#stock_modal div.modal-header h3").html("<i class='ace-icon fa fa-plus'></i> 库存");
			$("#stock_form").resetForm();
			$("#stock_form").find("input[name='id']").val("");
			$("#stock_modal").find(".modal-footer").show();
			$("#stock_modal").find("input, select").removeAttr("disabled");
			$("#stock_modal").modal("show");
		},
		initFamilyConsumeForm : function() {
			$("#family_consume_form").validate({
				errorPlacement : function(error, element) { // 指定错误信息位置
					if (element.is(':radio') || element.is(':checkbox')) { // 如果是radio或checkbox
						var eid = element.attr('name'); // 获取元素的name属性
						error.appendTo(element.parent().parent()); // 将错误信息添加当前元素的父结点后面
					} else {
						error.insertAfter(element);
					}
				},
				rules : rm_family_consume.rules,
				messages : rm_family_consume.messages,
				submitHandler : function(form) {
					utils.button.ban($("#family_consume_form").find("button"));
					$.ajax({
						type : 'POST',
						url : $$ctx + 'industryBiz/saveFamilyConsume',
						data : $(form).serialize(),
						success : function(result) {
							utils.button.reset($("#family_consume_form").find("button"));
							if (result.success) {
								utils.alert.suc(result.msg);
								$(form).find("input[name='id']").val(result.data);
								$("#statisticsTable").dataTable().fnDraw();
							} else {
								utils.alert.err(result.msg);
							}
						}
					});
				}
			});
			
			$.ajax({
				type : 'POST',
				url : $$ctx + 'industryBiz/findOneFamilyConsumeByProIdAndType',
				data : {
					"projectId" : $("#projectId").val(),
					"type" : "2"
				},
				success : function(result) {
					var formSelector = "#family_consume_form";
					if (typeof(result) != 'undefined' && $.trim(result) != '') {
						$.each($(formSelector).find("input[type=text], input[type=hidden], select, textarea"), function() {
							$(this).val(result[$(this).attr("name")]);
						});
					}
				}
			});
		},
		initStatisticsTable : function() {
			$("#statisticsTable").dataTable({
				bFilter : false,
				bLengthChange : false,
				bSort : false,
				bPaginate : false,
				bInfo : false,
				sAjaxSource: $$ctx + "industryBiz/initStatisticsTable",
		    	aoColumns: [
		    	    {mData : "typeName"},
		    	    {mData : "incomeAmount", mRender: function(data, type, rowdata) {
		    	    	return data.toFixed(2);
		    	    }},
		    	    {mData : "incomePercent", mRender: function(data, type, rowdata) {
		    	    	return (data * 100).toFixed(2) + "%";
		    	    }},
		    	    {mData : "costAmount", mRender: function(data, type, rowdata) {
		    	    	return data.toFixed(2);
		    	    }},
		    	    {mData : "costPercent", mRender: function(data, type, rowdata) {
		    	    	return (data * 100).toFixed(2) + "%";
		    	    }},
		    	    {mData : "gainAmount", mRender: function(data, type, rowdata) {
		    	    	return data.toFixed(2);
		    	    }},
		    	    {mData : "gainPercent", mRender: function(data, type, rowdata) {
		    	    	return (data * 100).toFixed(2) + "%";
		    	    }},
		    	    {mData : "stock", mRender: function(data, type, rowdata) {
		    	    	return data.toFixed(2);
		    	    }}
		    	],
		    	fnServerParams : function(aoData) {
					aoData.push({
						name : "projectId",
						value : $('#projectId').val()
					});
				}
			});
		},
		onChangeAndCount : function() {
			var _this = this;
			var formSelector = "#other_biz_income_form";
			// 每天利润-淡季
			$(".change_dailyGain_slack").bind("change", function(e) {
				if ($(this).valid()) {
					var arr = new Array("dailyIncome_slack", "dailyChangeableCost_slack");
					var newArr = _this.toLegal(arr, formSelector);
					var dailyGain_slack = newArr[0] - newArr[1];
					$(formSelector).find("input[name='dailyGain_slack']").val(dailyGain_slack);
				}
			});
			
			// 每天利润-旺季
			$(".change_dailyGain_peak").bind("change", function(e) {
				if ($(this).valid()) {
					var arr = new Array("dailyIncome_peak", "dailyChangeableCost_peak");
					var newArr = _this.toLegal(arr, formSelector);
					var dailyGain_peak = newArr[0] - newArr[1];
					$(formSelector).find("input[name='dailyGain_peak']").val(dailyGain_peak);
				}
			});
			
			// 年收入合计
			$(".change_yearIncomeTotal").bind("change", function(e) {
				if ($(this).valid()) {
					var arr = new Array("month_slack", "month_peak", "monthlyIncome_slack", "monthlyIncome_peak");
					var newArr = _this.toLegal(arr, formSelector);
					var yearIncomeTotal = newArr[0] * newArr[2] + newArr[1] * newArr[3];
					var selector = $(formSelector).find("input[name='yearIncomeTotal']");
					$(selector).val(yearIncomeTotal);
					$(selector).change();
				}
			});
			
			// 年可变成本合计
			$(".change_yearChangeableCostTotal").bind("change", function(e) {
				if ($(this).valid()) {
					var arr = new Array("month_slack", "month_peak", "monthlyChangeableCost_slack", "monthlyChangeableCost_peak");
					var newArr = _this.toLegal(arr, formSelector);
					var yearChangeableCostTotal = newArr[0] * newArr[2] + newArr[1] * newArr[3];
					var selector = $(formSelector).find("input[name='yearChangeableCostTotal']");
					$(selector).val(yearChangeableCostTotal);
					$(selector).change();
				}
			});
			
			// 年毛利润合计
			$(".change_yearGainTotal").bind("change", function(e) {
				if ($(this).valid()) {
					var arr = new Array("yearIncomeTotal", "yearChangeableCostTotal");
					var newArr = _this.toLegal(arr, formSelector);
					var yearGainTotal = newArr[0] - newArr[1];
					var selector = $(formSelector).find("input[name='yearGainTotal']");
					$(selector).val(yearGainTotal);
					$(selector).change();
				}
			});
			
			// 年固定成本合计
			$(".change_costTotal").bind("change", function(e) {
				if ($(this).valid()) {
					var arr = new Array("salary", "rent", "hospitality", "tranffic", "waterElectric", "communication", "repair", "tax", "others1", "others2", "others3", "others4");
					var newArr = _this.toLegal(arr, formSelector);
					var costTotal = 0;
					$.each(newArr, function(i, item) {
						costTotal += item;
					});
					var selector = $(formSelector).find("input[name='costTotal']");
					$(selector).val(costTotal);
					$(selector).change();
				}
			});
			
			// 年度净利润合计
			$(".change_gainTotal").bind("change", function(e) {
				if ($(this).valid()) {
					var arr = new Array("yearGainTotal", "costTotal");
					var newArr = _this.toLegal(arr, formSelector);
					var gainTotal = newArr[0] - newArr[1];
					$(formSelector).find("input[name='gainTotal']").val(gainTotal);
				}
			});
			
			// 总家庭支出
			$(".change_familyConsumeTotal").bind("change", function(e) {
				if ($(this).valid()) {
					var familyConsumeformSelector = "#family_consume_form";
					var arr = new Array("lifeConsume", "tuition", "medical", "insurance", "others1", "others2", "others3");
					var newArr = _this.toLegal(arr, familyConsumeformSelector);
					var familyConsumeTotal = 0;
					$.each(newArr, function(i, item) {
						familyConsumeTotal += item;
					});
					$(familyConsumeformSelector).find("input[name='familyConsumeTotal']").val(familyConsumeTotal);
				}
			});
		},
		toLegal : function(arr, formSelector) {
			var newArr = [];
			$.each(arr, function(i, item) {
				var value = $(formSelector).find("input[name='" + item + "']").val();
				if (typeof(value) == 'undefined' || $.trim(value) == '') {
					value = 0;
				} else {
					value = parseFloat(value);
				}
				newArr.push(value);
			});
			return newArr;
		},
		save_IndustryBiz_BasicInfo:function(){
			if($("#industry_biz_form").valid()){
				$.ajax({
					type : 'POST',
					url : $$ctx + 'industryBiz/saveBasicInfo',
					data : $("#industry_biz_form").serialize(),
					success : function(result) {
						if (result.success) {
							utils.alert.suc(result.msg);
							$(form).find("input[name='industryBizId']").val(result.data.industryBizId);
							$(form).find("input[name='transportId']").val(result.data.transportId);
							$("#statisticsTable").dataTable().fnDraw();
						} else {
							utils.alert.err(result.msg);
						}
					}
				});
			}else{
				utils.alert.warn("请输入完整的基础信息和运输业信息！");
			}
			
		}
	});
	module.exports = view;
});