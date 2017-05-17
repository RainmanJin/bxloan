define(function(require, exports, module) {
	var model = require("./model");
	 var rm = require("./rm");
	var view = Backbone.View.extend({
		el: "body",
		events: {
			"click #countRate": "countRate",
			"click #resetForm": "resetForm"
		},
		initialize: function() { /** 初始化 */
			this.model = new model();
			this.render();
			this.initFormValidate();
		},
		goBack: function(){
			history.go(-1);
		},
		render: function() { /** 页面渲染 */
			this.initInputDate();
		},
		initInputDate:function(){
			$('#loanStartDate').datepicker({clearBtn:true,autoclose:true});
			//删除自定义还款计划一项
			$("#repayment option[value='3']").remove();
		},
		initFormValidate:function(){
			$("#interesForm").validate({
				rules: rm.rules,
                messages: rm.messages,
                errorPlacement: function(error, element) {
                    error.appendTo(element.parent());
                }
			});
		},
		countRate: function(){
			var viewSelf = this;
			if(!$("#interesForm").valid()){
				return;
			}
			var data={
                	"loanAmount" :  $('#loanAmount').val(),
					"applyTerm" : $('#applyTerm').val(),
					"applyTermUnit":2,//月份
					"repayment" : $('#repayment').val(),
					"rate" : $('#rate').val(),
					"repaymentNumber" : $('#repaymentNumber').val(),
					"loanStartDate" : $('#loanStartDate').val(),
					"repaymentDate" :$('#repaymentDate').val()
                };
			viewSelf.model.loanTrial(data,function(r_data) {
            	if (r_data.success) {
            		var list = r_data.data;
            		$("tr").remove("#newAdd");
					for(var i=0;i<list.length;i++){
						$("#tbld").append("<tr id='newAdd'>"+"<th>"+ list[i].repaymentNumber +"</th>"+"<th>"+ list[i].repaymentDate +"</th>"+
												 "<th>"+ list[i].currentPricipalInterest +"</th>"+"<th>"+list[i].currentPricipal +"</th>"+
												 "<th>"+ list[i].currentInterest +"</th>"+"<th>"+list[i].endCurrentPricipal +"</th>"+
												 "<th>"+list[i].endCurrentPrincipalBalance  +"</th>"+"</tr>");
					}
				}else{
					bootbox.alert("<strong>因未知错误，保存失败！</strong>");
				}
            });
            return false;
	},
	resetForm: function(){
		$("#interesForm").get(0).reset();
	}
	});
	module.exports = view;
});