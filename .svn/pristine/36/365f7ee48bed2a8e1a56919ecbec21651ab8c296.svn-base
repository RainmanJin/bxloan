define(function(require, exports, module) {
	var utils = require("utils");
	var model = require("./model");
	var view = Backbone.View.extend({
		el: "#input_repay_modal",
		events: {
			
		},
		initialize: function() { /** 初始化 */
			this.model=new model();
			this.render();
			this.initInputDate();
		},
		render:function(){
			var viewSelf=this;
			var tb_repayment_info=viewSelf.$el.find('table[role="tb_repayment_info"]');
			var $tr_actual=tb_repayment_info.find('tbody tr').eq(1);
			//本期实还信息明细查看事件绑定
			$tr_actual.on('click',function(){
				$.each(tb_repayment_info.find('.actual_repayment'),function(i,v){
					var $tr=$(v);
					if($tr.is(':hidden')){
						$tr_actual.find('i').removeClass('fa-chevron-left').addClass('fa-chevron-down');
						$tr.show();
					}else{
						$tr_actual.find('i').removeClass('fa-chevron-down').addClass('fa-chevron-left');
						$tr.hide();
					}
				});
			});
			viewSelf.form=viewSelf.$el.find('form[role="input_repay_form"]');
		},
		initInputDate:function(){//初始化日期控件
			var viewSelf=this;
			var $form=viewSelf.form;
			$form.find(":text.dp-date").datepicker({
				format : 'yyyy-mm-dd',
				clearBtn : true,
				autoclose : true
			}).on("show", function() {
				$(".datepicker").css("z-index", "99999");
			});
		},
		loadRepaymentInfo:function(contractId,partyId,rpId,rpdId){//初始化还款信息
			var viewSelf=this;
			var data=[];
			data.push("contractId="+contractId);
			data.push("partyId="+partyId);
			data.push("rpId="+rpId);
			data.push("rpdId="+rpdId);
			this.model.getNormalRepaymentInfo(data.join('&'),function(r_data){
				if(r_data.success){
					utils.forms.putValueToForm(r_data.data,viewSelf.form);
					viewSelf.form.find('').val(r_data.data.contractTermUnit);
				}else{
					utils.alert.warn(r_data.msg);
				}
			});
		}
	});
	module.exports = view;
});