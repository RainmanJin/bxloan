define(function(require, exports, module) {
	var model = require("./model");
	var rm = require("./rm");
	var utils = require("utils");
	var view = Backbone.View.extend({
		el : "body",
		events : {
			"click #back" : "back"
		},
		initialize : function() {
			this.model = new model();
			this.render();
		},
		render : function() {
			this.initPage();
			this.switchCusType();
			this.initForm();
		},
		initPage:function(){
			var formSelector = "#imputForm";
			var selector = "#imputForm select[name='loanTermMode']";
			if($(formSelector).find("input[name='loanTermModeHidden']").val()=="") {
				$(selector).val("");
			}else{
				$(formSelector).find("input[name=minLoanTerm1]").addClass("required");
				$(formSelector).find("input[name=minLoanTerm]").addClass("required");
				$(formSelector).find("input[name=maxLoanTerm]").addClass("required");
				$(formSelector).find("input[name=specialLoanTerm]").addClass("required");
			}
			$(selector).bind("change", function(e) {
				var value = $(this).val();
				if (value == "1") {
					$(formSelector).find("input[name=minLoanTerm1]").addClass("required");
				} else if (value == "2") {
					$(formSelector).find("input[name=minLoanTerm]").addClass("required");
					$(formSelector).find("input[name=maxLoanTerm]").addClass("required");
				} else {
					$(formSelector).find("input[name=specialLoanTerm]").addClass("required");
				}
			});
		},
		disAb:function(){
			if($("#isBatch > :radio:checked").val()==2){
				$("#batchLimit").attr("disabled",true);
			}else{
				$("#batchLimit").removeAttr("disabled");
			}
		},
		initForm : function() {
			var viewSelf = this;
			
			var customerType = $("#customerType").val();
			$.each(customerType.split(","), function(i, val) {
				var check = $('#imputForm').find("input[type=checkbox][name=customerType][value='"+val+"']");
				if(check[0]) {
					check[0].checked = true;
				}
			});
			
			//是查看信息
			if($("#viewOrEdit").val()=="view"){
				$("#imputForm input,#imputForm select,#imputForm textarea").attr("disabled",true);
				$("#save").remove();
			}
			
			//不是批量不用填批量额度
			$("#isBatch").bind("change",function(){
				viewSelf.disAb();
			});
			viewSelf.disAb();
			
			//适用机构
			$("#orgId").addClass('tag-input-style').chosen({allow_single_deselect:true});
			
			//还款方式样式控制
			$("#repayingMode").addClass('tag-input-style').chosen({allow_single_deselect:true});
			
			//修改适用机构和还款方式下拉样式
			$("#imputForm .chosen-choices li input").css("height","30px");
			
			//贷款期限模式改变及初始化
			$("#term"+$("#loanTermMode").val()+"div").show();
			if($("#loanTermMode").val()=="1"){
				$("#minLoanTerm").val("")
				$("#term2div input,#term2div select").attr("disabled",true);
				$("#term3div select").attr("disabled",true);
			}else if($("#loanTermMode").val()=="2"){
				$("#minLoanTerm1").val("")
				$("#term1div input,#term1div select").attr("disabled",true);
				$("#term3div select").attr("disabled",true);
			}else{
				//特殊贷款期限
				$("#specialLoanTerm").addClass('tag-input-style')
									 .chosen({allow_single_deselect:true});
				$("#term1div input").attr("disabled",true);
				$("#term2div input").attr("disabled",true);
			}
			$(document).on("change", "#loanTermMode", function(e) {
				var termMode = $(this).val();
				switch (parseInt(termMode)) {
				case 1:
					$("#term1div input,#term1div select").removeAttr("disabled");
					$("#term1div").show();
					$("#term2div").hide();
					$("#term2div input,#term2div select").attr("disabled",true);
					$("#term3div").hide();
					$("#term3div select").attr("disabled",true);
					break;
				case 2:
					$("#term1div").hide();
					$("#term1div input,#term1div select").attr("disabled",true);
					$("#term2div input,#term2div select").removeAttr("disabled");
					$("#term2div").show();
					$("#term3div").hide();
					$("#term3div select").attr("disabled",true);
					break;
				case 3:
					$("#term1div").hide();
					$("#term1div input,#term1div select").attr("disabled",true);
					$("#term2div").hide();
					$("#term2div input,#term2div select").attr("disabled",true);
					$("#term3div").show();
					$("#term3div select").removeAttr("disabled");
					$("#specialLoanTerm").chosen("destroy").addClass('tag-input-style')
											.chosen({allow_single_deselect:true})
											.trigger("chosen:updated");
					break;
				default:
					$("#term1div").hide();
					$("#term1div input").attr("disabled",true).val("");
					$("#term2div").hide();
					$("#term2div input").attr("disabled",true).val("");
					$("#term3div").hide();
					$("#term3div select").attr("disabled",true).val("");
					break;
				}
			});
			
			//表单验证
			$('#imputForm').validate({
				rules : rm.rules,
				messages : rm.messages,
				submitHandler : function(form) {
					var $btnSave=$("#save");
					utils.button.ban($btnSave);//禁用按钮
					viewSelf.model.save($('#imputForm').serialize(),function(r_data){
						if(r_data.success){
							//window.location.href = $$ctx+"productMng";
							$("#productCd").val(r_data.data);
							utils.alert.suc("<strong>" + r_data.msg + "</strong>");
						}else{
							utils.alert.err("<strong>" + r_data.msg + "</strong>");
						}
						utils.button.reset($btnSave);//启用按钮
					});
				}
			});
		},
		back : function() {
			window.location.href = $$ctx + 'productMng';
		},
		switchCusType : function() {
			var selector = "#imputForm input[type='checkbox'][name='customerType']";
			setTimeout(function() {
				var check = $(selector + "[value='2'].ace:checked");
				if (!check[0]) {
					$(".showHideCusProp").hide();
				} else {
					$("#imputForm").find("input[name='customerProperty']").addClass("required");
				}
			}, 100);
			
			$(document).on("click", selector, function(e) {
				var check = $(selector + "[value='2'].ace:checked");
				if (check[0]) {
					$(".showHideCusProp").slideDown();
					$("#imputForm").find("input[name='customerProperty']").addClass("required");
				} else {
					$(".showHideCusProp").slideUp();
					$("#imputForm").find("input[name='customerProperty']").removeClass("required");
				}
			});
		}
	});
	module.exports = view;
});