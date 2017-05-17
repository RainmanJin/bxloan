/**
 * 还款：正常还款 逾期还款 js
 * */
define(function(require, exports, module) {
	var model = require("../model");
	var rm_fr = require("../rm_fr.js");
	var utils = require("utils");
	var _alert = bootbox.alert;
	var _confirm = bootbox.confirm;
	var view = Backbone.View.extend({
		el: "body",
		events: {
			"click #btn-repaySubmit": "repaySubmit"
		},
		initialize: function() { /** 初始化 */
			this.model = new model();
			this.render();
		},
		render: function() { /** 页面渲染 */
			var viewSelf=this;
			viewSelf.initFirst();
			viewSelf.initNormalRepayForm();
			viewSelf.initNormalRepayBtn();
			viewSelf.initRepayDate();
		},
		initFirst: function(){
			$("#btn-repaySubmit").click(function(e){
				e.preventDefault();
			});
			$(document).on("click","button[class='close']",function(e){
				e.preventDefault();
				$(this).closest("form").resetForm();
			})
		},
		initRepayDate: function(){
			var viewSelf=this;
			//正常还款-实还日期
			$("#repayDate").datepicker({todayBtn:true,autoclose:true}).on("click",function(){
			}).on("changeDate",function(){
				viewSelf.changeOfRepayDate(this);
			}).on("show",function(){
				$(".datepicker").css("z-index","99999");
			});
			
			//逾期试算-实还日期
			$("#repayDate_trial").datepicker({todayBtn:true,autoclose:true}).on("click",function(){
			}).on("changeDate",function(){
				viewSelf.trialByRepayDate(this);
			}).on("show",function(){
				$(".datepicker").css("z-index","99999");
			});
		},
		initNormalRepayBtn: function() {
			var viewSelf = this;
			//正常还款
			$(document).on("click","a[role='normalRepay']",function(e){
				var btnSelf = $(this);
				e.preventDefault();
				viewSelf.model.normalRepay(btnSelf.data("id"),null,function(r_data){
					if(r_data.success){
						viewSelf.initRepayInfo(r_data.data, "#input_repay_form");
						$("#input_repay_modal").modal("show");
					}else{
						if(r_data.data){
							utils.alert.warn(r_data.data.desc);
						}else{
							utils.alert.warn(r_data.msg);
						}
					}
				});
			});
			//逾期还款
			$(document).on("click","a[role='overdueRepay']",function(e){
				var btnSelf = $(this);
				e.preventDefault();
				viewSelf.model.overdueRepay(btnSelf.data("id"),0,null,function(r_data){
					if(r_data.success){
						viewSelf.initRepayInfo(r_data.data, "#input_repay_form");
						$("#input_repay_modal").modal("show");
					}else{
						if(r_data.data){
							utils.alert.warn(r_data.data.desc);
						}else{
							utils.alert.warn(r_data.msg);
						}
					}
				});
			});
			
			//贷款试算
			$(document).on("click","a[role='overdueTrial']",function(e){
				e.preventDefault();
				var btnSelf = $(this);
				viewSelf.model.overdueRepayTrial(btnSelf.data("id"),function(r_data){
					if(r_data.success){
						viewSelf.initRepayInfo(r_data.data, "#repayTrialForm");
						$("#repayTrial_modal").modal("show");
					}else{
						if(r_data.data){
							utils.alert.warn(r_data.data.desc);
						}else{
							utils.alert.warn(r_data.msg);
						}
					}
				});
			});
		},
		initRepayInfo:function(data, selector){//初始化还款信息
			var $form=$(selector);
			for (var key in data) {
				if(data[key]||data[key]=='0'){
				$form.find(":hidden[name='"+key+"']").val(data[key]);
				$form.find(":text[name='"+key+"']").val(data[key]);
				}
			}
			if(data['contractInterestRate']){
			$("input[name='contractInterestRate']").val(data['contractInterestRate']*100);
			}
			if(data['contractTermUnit']&&data['contractTerm']){
				$form.find("input[name='contractTerm']").next().text(data['contractTermUnit']);
			}
			//正常转逾期显隐
			if(data.overdue==1){
				$form.find(":text[name='payableDateStr']").parent().hide();
			}else{
				$form.find(":text[name='payableDateStr']").show();
			}
			var $tb_tbody=$(selector + " table tbody");
			$tb_tbody.empty();//清空
			if(!data.repayItems||data.repayItems.length==0){
				return ;
			}
			var html=[];//还款期次
			$.each(data.repayItems,function(i,item){
				html.push("<tr>");
				html.push("<td>"+item.period+"</td>");
				html.push("<td>"+item.repayingDate+"</td>");
				html.push("<td>"+item.repayingPricipalInterest+"</td>");
				html.push("<td>"+item.repayingPricipal+"</td>");
				html.push("<td>"+item.repayingInterest+"</td>");
				html.push("<td>"+item.repayedPricipal+"</td>");
				html.push("<td>"+item.repayedInterest+"</td>");
				html.push("<td>"+item.notyetImposeInterest+"</td>");
				html.push("<td>"+item.overdueDay+"</td>");
				html.push("<td><span class='label label-danger'>"+item.status+"</span></td>");
				html.push("</tr>");
				
			})
			$tb_tbody.html(html.join(''));
			utils.num.tableFormat($form.find("table"));
			var form_width = $form.width();
			var width = $form.find("table td,th").css({
				"word-break"  : "keep-all",
				"white-space" : "nowrap"
			});
			$form.find("table").parent(".table-responsive").css({
					"overflow-x": "scroll",
					"overflow-y": "hidden",
					"width" : "100%"
			});
		},
		initNormalRepayForm:function(){//还款提交
			var viewSelf = this;
			var $form=$("#input_repay_form");
			$form.validate({
                rules: rm_fr.rules,
                messages: rm_fr.messages,
                errorPlacement: function(error, element) {
                    if (element.is(":radio")){error.appendTo(element.parent());}
                    else if (element.is(":checkbox")){error.appendTo(element.parent().parent().parent().next());}
                    else if (element.next().is("span[class='input-group-addon']")) {
                    	error.appendTo(element.parent().parent().parent());
                    }
                    else {
                    	error.appendTo(element.parent());
                    } 
                }
            });
		},
		repaySubmit: function(){
			var viewSelf = this;
			var data=[];
			var $form=$("#input_repay_form");
			if($form.valid()){
				viewSelf.model.repaySubmit({
					contractId: $form.find(":hidden[name='contractId']").val(),
					repayLoanNum: $form.find(":text[name='repayLoanNum']").val(),
					overdue : $form.find(":hidden[name='overdue']").val(),
					normal2Overdue : $form.find(":hidden[name='normal2Overdue']").val(),
					repayDate : $form.find(":text[name='repayDateStr']").val(),
					actualAmt : $form.find(":text[name='website']").val(),
					fundsSource : $form.find("select[name='addressTypeCd']").val(),
					repayingPlanId : $form.find(":hidden[name='repayingPlanId']").val(),
					repayingPlanDetailId : $form.find(":hidden[name='repayingPlanDetailId']").val(),
					remark : $form.find("textarea").val()
				},function(r_data){
					if(r_data.success){
							$("#input_repay_modal").modal("hide");
						utils.alert.suc("还款申请已提交",function(){
							$form.resetForm();
							$("#tbl").dataTable().fnDraw();
						});
					}else{
						if(r_data.data){
							utils.alert.warn(r_data.data.desc);
						}else{
							utils.alert.warn(r_data.msg);
						}
					}
				});
			}
		},
		changeOfRepayDate:function(input){
			var viewSelf=this;
			var inputSelf = $(input);
			var repayDate=inputSelf.val();
			var $form=$("#input_repay_modal");
			var payableDate=$form.find(":input[name=payableDateStr]").val();
			var overdue=$form.find(":hidden[name=overdue]").val();
			if(repayDate&&repayDate<payableDate){
				viewSelf.model.normalRepay($("#input_repay_modal :input[name=contractId]").val(),
						repayDate,function(r_data){
					if(r_data.success){
						viewSelf.initRepayInfo(r_data.data, "#input_repay_form");
						$("#input_repay_modal").modal("show");
					}else{
						utils.alert.warn(r_data.data.desc);
					}
				});
			}
			else if(overdue==1){
				//逾期还款
				viewSelf.model.overdueRepay($form.find(":input[name=contractId]").val(),
						$form.find(":hidden[name=normal2Overdue]").val(),
						repayDate,function(r_data){
					if(r_data.success){
						viewSelf.initRepayInfo(r_data.data, "#input_repay_form");
						$("#input_repay_modal").modal("show");
					}else{
						_alert(r_data.data.desc);
					}
				});
				return ;
			}
			//正常转逾期
			else if(repayDate&&repayDate>payableDate&&overdue==0){
				_confirm("确定要进行逾期还款吗?请确认！",function(cf_result){
					if(cf_result){
						viewSelf.model.overdueRepay($("#input_repay_modal :input[name=contractId]").val(),
								1,repayDate,function(r_data){
							if(r_data.success){
								viewSelf.initRepayInfo(r_data.data, "#input_repay_form");
								$("#input_repay_modal").modal("show");
							}else{
								_alert(r_data.data.desc);
							}
						});
						return ;
					}else{
						inputSelf.val(payableDate);//设置为应还日期
						return ;
					}
				});
			}
			
		},
		trialByRepayDate: function(input){
			var viewSelf=this;
			var $form = $("#repayTrialForm");
			var inputSelf = $(input);
			var repayDate=inputSelf.val();
			if(!$("#repayDate_trial").val()){
				utils.alert.warn("请选择实还日期！");
				return false;
			}
			viewSelf.model.overdueTrial({
				"lastRepayDate" : $form.find("input[name='lastRepayDate']").val(),
				"repayDate": $("#repayDate_trial").val(),
				"contractId" : $form.find("input[name='contractId']").val()
			},function(result){
				if(result.success){
					if(result.data){
						viewSelf.initRepayInfo(result.data, "#repayTrialForm");
					}else{
						utils.alert.warn("<strong>返回数据有误！</strong>");
					}
				}else{
					utils.alert.warn("<strong>"+ result.msg +"</strong>");
				}
			});
		}
		
	});
	module.exports = view;
});