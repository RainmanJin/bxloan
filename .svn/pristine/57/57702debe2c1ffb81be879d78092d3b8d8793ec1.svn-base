/**
 * 新增放款记录页面的js
 * */


define(function(require, exports, module) {
    var model = require("./model");
    var utils = require("utils");
    var rm = require("./rm");

    var view = Backbone.View.extend({
        el: "body",
        events: {
            "change #bankName": "changeAccountNumDisplay",
            "click #submitAndPrint": "submitAndPrint"
        },
        initialize: function() {
            /** 初始化 */
            this.model = new model();
            this.render();
        },
        render: function() {
            /** 页面渲染 */
            this.initInputDate();
            this.initFormMessage();
          //  this.initCustomerAccount();
            this.initPayOutForm();
        },
        initInputDate: function() {
            $('#createDate').datepicker({
                clearBtn: true
            });
            $('#loanRegistTime').datepicker({
                clearBtn: true,
                constrainInput: true,
                autoclose: true
                //startDate: new Date()
            });
        },
        initFormMessage: function() {
            /**初始化表单的数据*/
            var viewSelf = this;
            viewSelf.model.queryMessages({
                contractId: $("#contractIdField").val(),
                cuserId: $("#cuserIdField").val()
            },
            function(data) {
                if (data == null) {
                	utils.alert.err("<strong>合同数据缺失！请联系管理员。</strong>");
                } else {
                    $.each($("#form-fkxx").find("input[type='text'], select, textarea"),
                    function() {

                        $(this).val(data[$(this).attr("name")]);
                    });
                    if(data["sumAmt"]==null||data["sumAmt"]==""){
                    	$("#sumAmt").val("0");
                    }
                    //viewSelf.changeAccountNumDisplay();
                }
            });
        },
        initCustomerAccount: function() {
            var viewSelf = this;
            var partyId = $("#partyIdField").val();
            viewSelf.model.getCustomerAccount({
                contractId: $("#contractIdField").val()
            },
            function(data) {
                if (data != null) {
                    var sel = $("#bankName")[0];
                    for (var i = 0; i < data.length; i++) {
                        var opt = new Option(data[i].bankAccount, data[i].accountId);
                        sel.appendChild(opt);
                    }
                    $("#accountNum").val(data[0].accountNum);
                    $("#bankValue").val(data[0].bankAccount);
                } else {
                	utils.alert.err("<strong>获取账户信息出错</strong>")
                }
            });
        },
        changeAccountNumDisplay: function() {
            var viewSelf = this;
            viewSelf.model.getAccountNum({
                accountId: $("#bankName").val()
            },
            function(data) {
                if (data != null) {
                    $("#accountNum").val(data[0]);
                    $("#bankValue").val(data[1]);
                } else {
                	utils.alert.err("<strong>获取账户号码出错</strong>")
                }
            });
        },
        initPayOutForm: function() {
            $("#form-fkxx").validate({
                rules: rm.rules,
                messages: rm.messages,
                errorPlacement: function(error, element) {
                    if (element.is(":radio")) error.appendTo(element.parent().next().next());
                    else if (element.is(":checkbox")) error.appendTo(element.next());
                    else error.appendTo(element.parent());
                }
            });
        },
        submitAndPrint: function(e) {
            var viewSelf = this;
            var btnSubmit=$(e.currentTarget);
            utils.button.ban(btnSubmit);//禁用按钮
            if (!$("#form-fkxx").valid()) {
            	utils.button.reset(btnSubmit);//启用按钮
            	return ;
            }
        	utils.button.confirm(btnSubmit,function(result){
				if(result){
					$.ajax({
                        cache: false,
                        type: "POST",
                        url: $$ctx + "contractList/submitAndPrint?contractId=" + $("#contractIdField").val(),
                        data: $('#form-fkxx').serialize(),
                        error: function(request) {
                            alert("保存报错" + request);
                            utils.button.reset(btnSubmit);//启用按钮
                        },
                        success: function(data) {
                            if (data.success) {
                            	window.open( $$ctx + 'contractList/testReport?payLoanId=' + data.data,"_blank");
                            	utils.alert.suc( "<strong>提交成功</strong>", function(){
                            	  window.location.href = $$ctx + "contractList/signList?contractId=" + $("#contractIdField").val() + "&cuserId=" + $("#cuserIdField").val();
                            	});
                            } else {
                            	utils.alert.warn( "<strong>"+ data.msg +"</strong>",function(){
                            		utils.button.reset(btnSubmit);//启用按钮
                            	});
                            }
                        }
					 }); //ajax end
				}else{
					utils.button.reset(btnSubmit);//启用按钮
				}
			},"您确定要提交并打印吗?");
        }
    });
    module.exports = view;
});