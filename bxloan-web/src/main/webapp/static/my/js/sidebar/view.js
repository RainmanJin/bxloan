define(function(require, exports, module) {
	var model = require("./model");
	var utils = require("utils");
    var rm = require("./rm");
    var rmCalcu = require("./rm_loancalcu.js");
	var i = true;
	var view = Backbone.View.extend({
		el: "body",
		events: {
			"click #side_bar_calculatorLoan" : "calculatorLoan",// 计算div
			"click #resetCalculatorLoan" : "resetCalculatorLoan",// 计算
			"click #btn-dashboard" : "gotoDashBoard",
			"click #side_bar_userinfo" : "openUserInfoForm"
				
		},
		initialize: function() { /** 初始化 */
			this.model = new model();
			this.render();
		},
		render: function() { /** 页面渲染 */
			this.initMenuTree(); //初始化菜单
			this.checkDatePicker();
			this.initFrameHight();
			this.initHelpBtn();
			this.initUserInfoForm();
			this.initCalcutor();
		},
		/**
		 * 用户信息
		 */
	/*	openUserInfoForm : function(){
        	var formSelector = "#user_setting_form";
        	var _view = this;
        	_view.model.fetchPersonDetail(function(result){
        		if(result.success){
        			utils.forms.putValueToForm(result.data,formSelector);
        		}else{
        			utils.alert.err("获取数据失败");
        		}
        	});
        	$("#side_bar_userinfo_modal").modal("show");
        },*/
		openUserInfoForm: function(){
			//history.pushState(null,$$ctx + 'dashboard/personInfo');
			window.location.href = $$ctx + 'dashboard/personInfo';
		},
        initUserInfoForm : function(){
        	var controllerUrl = 'userinfo';
        	var formSelector = "#user_setting_form";
        	var _view = this;
        	var _alert = bootbox.alert;
        	$("#user_setting_form").validate({
        		rules: rm.rules,
                messages: rm.messages,
        		submitHandler : function(form){
        			var _toMd5 = function(selector){
        				if($(selector).val() !== ''){
        					$(selector).val(hex_md5($(selector).val()));
        				}
        			};
        			_toMd5("#side_bar_user_setting_logpass");
        			_toMd5("#side_bar_user_setting_new_pass");
        			_toMd5("#side_bar_user_setting_repeat_pass");
        			if($("#side_bar_user_setting_logpass").val() !== '' && $("#side_bar_user_setting_new_pass").val() === ''){
        				_alert("请输入新密码");
        				return false;
        			}
        			
        			$.ajax({
                        cache: false,
                        type: "POST",
                        url: $$ctx + controllerUrl + "/update",
                        data: $(formSelector).serialize(),
                        async: false,
                        error: function(request) {
                        	utils.alert.err("提交时发生异常" + request);
                        },
                        success: function(result) {
                        	if(result.success){
                        		utils.alert.suc("修改成功",function(){
                            		$("#side_bar_userinfo_modal").modal("hide");
                            		$("#user_setting_form").validate().resetForm();
                            	});
                            }else{
                            	utils.alert.err(result.msg);
                            }
                        }
                    });
        			
                    return false;
        		}
        	});
        },
		initFrameHight: function(){
			$("#side_bar_help_info_mainFrame").load(function() {
        	    var clientHeight = $("#side_bar_help_info_mainFrame").contents().find("body").height();
        	    $(this).height(clientHeight);
			});
		},
		initHelpBtn: function(){
			var viewSelf = this;
			$(document).on("click", "button[role='helpInfo']", function (){
				 $("#side_bar_help_info_modal div.modal-header h4").html("<i class='glyphicon glyphicon-info-sign'></i> 帮助信息");
				 var result = $$ctx + "dashboard/helpinfo";
				 var setIframeSrc = function () {
					    var s = result;
					    var iframe1 = document.getElementById('side_bar_help_info_mainFrame');
//					    if (-1 == navigator.userAgent.indexOf("MSIE")) {
					        iframe1.src = s;
//					    } else {
//					        iframe1.src = s;
//					    }
					}
				 setIframeSrc();
//				 setTimeout(setIframeSrc, 5);
				 //$("#side_bar_help_info_mainFrame").get(0).setAttribute("src", );
                 $("#side_bar_help_info_modal").modal("show");
	        });
		},
		gotoDashBoard : function(){
			window.location.href= $$ctx + "dashboard"
		},
		initMenuTree: function(){
			var viewSelf = this;
			viewSelf.model.getMenus(function(r) {
				if (r.success) {
					viewSelf.createMenus(r.data,$("#menuBar"));
					var url = '';
					if ($$moduleName !== '') {
						url = $$moduleName;
					} else {
						url = window.location.href;
						url = url.substring(url.lastIndexOf("/") + 1, url.length);
					}
					var subMenu = $("ul.nav a[href='" + $$ctx +url + "']").parent();
					subMenu.addClass("active");// 子菜单选中
					subMenu.parents("ul li:eq(0)").addClass("active open");
				}
			});
		},
		createMenus: function(menus,parent){
			var viewSelf = this;
			$.each(menus, function(i, item) {
				if (item.leaf == 0) {
					var liStr = "<li>";
					liStr += "       <a href='#' class='dropdown-toggle'>";
					liStr += "           <i class='menu-icon "+item.authorityscript+"'></i>";
					liStr += "           <span class='menu-text'>"+item.name+"</span>";
					liStr += "           <b class='arrow fa fa-angle-down'></b>";
					liStr += "       </a>";
					liStr += "       <b class='arrow'></b>";
					liStr += "       <ul class='submenu'></ul>";
					liStr += "   </li>";
				    var li = $(liStr);
				    $(li).appendTo(parent);
				    viewSelf.createMenus(item.childs, $(li).children().eq(2));
				}else {
					var liStr = "<li class=''>";
					liStr += "       <a href='"+$$ctx+item.authorityscript+"'>";
					liStr += "           <i class='menu-icon fa fa-caret-right'></i>";
					liStr += item.name;
					liStr += "       </a>";
					liStr += "       <b class='arrow'></b>";
					liStr += "   </li>";
					var li = $(liStr);
				    $(li).appendTo(parent);
				 }
			});
		},//以下是贷款试算
		checkDatePicker:function(){
			$("#loan_cal_loan_start_date").datepicker({
				autoclose : true,
				todayHighlight : true,
				clearBtn : true,
				endDate : 'd'
			}).on("changeDate",function(){$(".datepicker").css("z-index","99999");})
			.on("show",function(){$(".datepicker").css("z-index","99999");});
		},
		calculatorLoan : function() {
			if(i) {
				$("#cal_dkss_repaymentPlanTable").dataTable({
					//sAjaxSource : $$ctx + "businessapplicationwd/searchCalRepaymentPlanList",
					sAjaxSource : $$ctx + "businessapplicationwd/searchRepaymentPlanTempList",
					bFilter : false,
					bLengthChange : false,
					iDisplayLength : 15,
					aoColumnDefs : [ {
						aTargets : [ 2 ],
						mRender : function(data, type, full) {
							return "<div class='btn-group'style='width:100px;'><button title='修改' type='button' role='editRepayPlan' data-id='" + data + "' class='btn btn-xs btn-info'><i class='ace-icon fa fa-edit'></i></button>"+
								" <button title='删除' type='button' role='deleteRepayPlan' data-id='" + data + "' class='btn btn-xs btn-danger'><i class='ace-icon fa fa-trash-o'></i></button></div>";
						}
					} ]
				});
				i = false;
			} else {
				$("#cal_dkss_repaymentPlanTable").dataTable().fnDraw();
			}
			
			var formSelector = "#side_bar_calcu_form";
			$(formSelector).validate().resetForm();
			this.resetCalculatorLoan();
			$('#cal_dkss_repaymentPlanTable_div').slideUp();
			$('#cal_addUserDefindPlan').slideUp();
			$("#side_bar_calcu_modal div.modal-header h4").html(
					"<i class='ace-icon fa fa-plus'></i> 计算器");
			$("#side_bar_calcu_modal").modal("show");
		},
		initCalcutor : function (){
			var formSelector = "#side_bar_calcu_form";
			$(formSelector).validate({
        		rules: rmCalcu.rules,
                messages: rmCalcu.messages,
        		submitHandler : function(form){
        			
        			$.ajax({
                        type: "POST",
                        url: $$ctx + "calcu/loanCalc",
                        data: $(formSelector).serialize(),
                        success: function(result) {
                        	if (result.success) {
								var list = result.data;
		                		$("tr").remove("#newAdd");
		                		var currentPricipalInterestTotal = 0;
		                		var currentPricipalTotal = 0;
		                		var currentInterestTotal = 0;
								for(var i=0;i<list.length;i++){
									currentPricipalInterestTotal += list[i].currentPricipalInterest;
									currentPricipalTotal += list[i].currentPricipal;
									currentInterestTotal += list[i].currentInterest;
									$("#tbld_cal").append("<tr id='newAdd'>"+"<td>"+ list[i].repaymentNumber +"</td>"+"<td>"+ list[i].repaymentDate +"</td>"+
														 "<td>"+ list[i].currentPricipalInterest +"</td>"+"<td>"+list[i].currentPricipal +"</td>"+
														 "<td>"+ list[i].currentInterest +"</td>"+"<td>"+list[i].endCurrentPricipal +"</td>"+
														 "<td>"+list[i].endCurrentPrincipalBalance  +"</td>"+"</tr>");
								}
								$("#tbfoot_cal").append("<tr id='newAdd'>" +
										"<th></th>" +
										"<th>总计：</th>" +
										"<th>"+currentPricipalInterestTotal.toFixed(2)+"</th>" +
										"<th>"+currentPricipalTotal.toFixed(2)+"</th>" +
										"<th>"+currentInterestTotal.toFixed(2)+"</th>" +
										"<th></th>" +
										"<th></th>" +
										"</tr>");
							} else {
								utils.alert.err("<strong>"+result.msg+"</strong>");
							}
        				}
                    });
        			
                    return false;
        		}
        	});
			
			$(document).on("change", "#repayment", function() {
				var _this = $(this).val();
				if(_this == '3') {
					$.ajax({
						type : 'post',
						url : $$ctx + 'businessapplicationwd/deleteCalRepaymentPlanList',
						success : function(result) {
							if(result.success) {
								$('#cal_dkss_repaymentPlanTable_div').slideDown();
								$('#cal_addUserDefindPlan').slideDown();
								$('#repaymentDate').removeClass('required');
								$('#repaymentNumber').removeClass('required');
								$("#cal_dkss_tbody").empty();
								$("#cal_dkss_tfoot").empty();
							} else {
								$('#repayment').val('1');
								utils.alert.warn("<strong>初始化自定义还款计划列表失败！请重试</strong>");
							}
						}
					});
				} else {
					$('#cal_dkss_repaymentPlanTable_div').slideUp();
					$('#cal_addUserDefindPlan').slideUp();
					$('#repaymentDate').addClass('required');
					$('#repaymentNumber').addClass('required');
				}
			});
			
			$(document).on("click", "#cal_addUserDefindPlan", function() {
				$('#cal_repayPlanId').val('');
				$('#cal_repayPlanForm').resetForm();
				$('#cal_repayPlanModal').modal('show');
			});
			
			$('#cal_repayPlanForm').validate({
				submitHandler : function(form) {
					$("#cal_sureRepayPlanForm").attr({
						"disabled" : "disabled"
					});
					$.ajax({
						type : 'post',
						//url : $$ctx + 'businessapplicationwd/saveRepayPlan',
						url : $$ctx + 'businessapplicationwd/saveRepayPlanTemp',
						data : $(form).serialize(),
						success : function(result) {
							$("#cal_sureRepayPlanForm").removeAttr("disabled");
							if (result.success) {
								utils.alert.suc("<strong>" + result.msg + "</strong>");
								$('#cal_repayPlanModal').modal('hide');
								var table = $("#cal_dkss_repaymentPlanTable").dataTable();
								table.fnSettings()._iDisplayStart = 0;
								table.fnDraw();
							} else {
								utils.alert.err("<strong>" + result.msg + "</strong>");
							}
						}
					});
				}
			});
			
			var dateOnTop = function() {
				$(".datepicker").css("z-index", "99999");
			};
			var defaultDateConf = {
				autoclose : true,
				todayHighlight : true,
				clearBtn : true
			};
			var initDateP = function(selector, changeCallBack, config) {
				var _dateConf = config || defaultDateConf;
				$(selector).datepicker(_dateConf).on("changeDate",
						changeCallBack).on("click", dateOnTop);
			};
			initDateP("#cal_repayDate");
			
			$(document).on("click", "#side_bar_calcu_form button[role='editRepayPlan']", function(e) {
				$.ajax({
					type : 'post',
					url : $$ctx + 'businessapplicationwd/getRepayPlan',
					data : {
						'id' : $(this).data("id")
					},
					success : function(repayPlan) {
						$.each($("#cal_repayPlanForm").find("input"), function() {
							$(this).val(repayPlan[$(this).attr("name")]);
						});
						$('#cal_repayPlanModal').modal('show');
					}
				});
			});
			
			$(document).on("click", "#side_bar_calcu_form button[role='deleteRepayPlan']", function(e) {
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
							className: "btn-sm"
						}
					},
					callback: function(result) {
						if (result) {
							$.ajax({
								type : 'post',
								url : $$ctx + 'businessapplicationwd/deleteRepayPlan',
								data : {
									'id' : id
								},
								success : function(r) {
									if(r.success) {
										utils.alert.suc("<strong>" + r.msg + "</strong>");
										var table = $("#cal_dkss_repaymentPlanTable").dataTable();
										table.fnSettings()._iDisplayStart = 0;
										table.fnDraw();
									} else {
										utils.alert.err("<strong>" + r.msg + "</strong>");
									}
								}
							});
						}
					}
				});
			});
		},
		
		/**
		 * 重置
		 */
		resetCalculatorLoan : function() {
			$.ajax({
				type : 'post',
				url : $$ctx + 'businessapplicationwd/deleteCalRepaymentPlanList',
				success : function(result) {
					if(result.success) {
						$('#cal_dkss_repaymentPlanTable').dataTable().fnDraw();
						$('#cal_dkss_repaymentPlanTable_div').slideUp();
						$('#cal_addUserDefindPlan').slideUp();
						$('#side_bar_calcu_form').resetForm();
						$("#tbld_cal").empty();
						$("#tbfoot_cal").empty();
					} else {
						$('#repayment').val('1');
						utils.alert.warn("<strong>初始化自定义还款计划列表失败！请重试</strong>");
					}
				}
			});
			
		}
		
	});
	module.exports = view;
});