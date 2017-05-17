/**
 * 还款：费用js
 * */
define(function(require, exports, module) {
	var model = require("../model");
	var rm_fee = require("./rm_fee.js");
	var utils = require("utils");
	var _alert = bootbox.alert;
	var _confirm = bootbox.confirm;
	var view = Backbone.View.extend({
		el: "body",
		events: {
		},
		initialize: function() { /** 初始化 */
			this.model = new model();
			this.render();
		},
		render: function() { /** 页面渲染 */
			var viewSelf=this;
			this.initFeeRegistBtnLive();
			this.initFeeRegisterForm();
		},
		initFeeRegistBtnLive:function(){
			var viewSelf = this;
			//费用登记
			$(document).on("click","a[role='feeRegister']",function(e){
				var btnSelf = $(this);
				e.preventDefault();
				viewSelf.model.findFeeRegisterInfo(btnSelf.data("id"),function(r_data){
					var $form=$("#input_fee_register_form");
					var data=r_data.data;
					$form[0].reset();
					for ( var key in data) {
						$form.find(":hidden[name='"+key+"']").val(data[key]);
						$form.find(":text[name='"+key+"']").val(data[key]);
					}
					$form.find("input[name='contractTerm']").next().text(data["contractTermUnit"]);
					$form.find("input[name='repayDateStr']").val(data['repayDate']);
					$("#input_fee_register_modal").modal("show");
				});
			});
		},
		initFeeRegisterForm:function(){
			var viewSelf=this;
			$('#input_fee_register_form').validate({
				rules : rm_fee.rules,
				messages : rm_fee.messages,
				errorPlacement: function(error, element) {
                    if (element.is(":radio")) error.appendTo(element.parent().next().next());
                    else if (element.is(":checkbox")) error.appendTo(element.next());
                    else if (element.is(":input[name='guarantorName']")) {
                        error.appendTo(element.parent().parent());
                    } else error.appendTo(element.parent());
                },
				submitHandler : function(form) {
					var $form=$(form);
					var repayDate = $form.find("input[name='repayDateStr']").val();
					viewSelf.model.saveFeeRegisterInfo($form.serialize()+"&repayDate="+repayDate, function(r_data){
								if(r_data.success){
									utils.alert.suc(r_data.msg);
									$("#input_fee_register_modal").modal("hide");
									$form[0].reset();
								}else{
									utils.alert.warn(r_data.msg);
								}
					});
					return false;
				},
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
		}
	});
	module.exports = view;
});