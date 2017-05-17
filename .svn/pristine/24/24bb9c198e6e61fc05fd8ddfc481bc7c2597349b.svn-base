define(function(require, exports, module) {
	var rm_dr_cld=require('./rm_dr_cld');
	var rm_drc=require("../../../../../../drCreditInfo/rm");
	//var rm_drc=require("./rm_drc");
	var model=require('./model');
	var utils=require('utils');
	var view = Backbone.View.extend({
		el:"#drCreditInfo",
		initialize: function() { /** 初始化 */
			this.model=new model();
			this.render();
		},
		events: {
			"click button[role='drCredit_drCld_Add']":"fnDrCldAdd"
		},
		render: function() {
			var viewSelf=this;
			viewSelf.initFormDrCredit();
		   //渲染页面
			utils.dd.initDataDict(["RepaymentMode"], function(dataDict){
				viewSelf.initDrCldDataTable(dataDict);
			});
			viewSelf.initDrCldForm();
			viewSelf.initBtnLive();
		},
		initFormDrCredit:function(){//初始化动态form
			var viewSelf=this;
			viewSelf.model.getDrCreditFormTemplate(function(r_data){
				$("#form_dr_credit_item").html(r_data);
				//初始化form
				var drcForm=$("#form_dr_credit");
				
				var judgeType=$("#judgeType").val();
				if(judgeType&&judgeType=='check'){
					utils.forms.disableForm(drcForm);
				}else{
					// 绑定日历时间
					drcForm.find(".dy-from-date").datepicker({
						format:"yyyy-mm-dd",
						clearBtn:true,
						autoclose:true
					}).on("click",function(){
					}).on("changeDate",function(){
						drcForm.validate().element(this);
					}).on("show",function(){
						$(".datepicker").css("z-index","99999");
					});
					
				}
				//form验证
				drcForm.validate({
					rules: rm_drc.rules,
	                messages: rm_drc.messages,
	                submitHandler: function(form) {
	                	viewSelf.model.saveDrCreditInfo(drcForm.serialize(),function(r_data){
	                		if(r_data.success){
	                			utils.alert.suc("<strong>保存成功！</strong>");
	                		}else{
	                			utils.alert.err("<strong>" + r_data.msg + "</strong>");
	                		}
	                	});
	                    return false;
	                }
				});
				// 初始化FormValues
				var projectId=$("#projectId").val();
				if(projectId&&projectId>0){
					viewSelf.model.findDrCreditInfo($("#projectId").val(),function(r_data){
						if(r_data.success){
							utils.forms.putValueToForm(r_data.data,drcForm);
						}else{
							utils.alert.err("<strong>" + r_data.msg + "</strong>");
						}
					});
				}
				
			})
		},
		initDrCldDataTable:function(dataDict){//初始化借款人及其配偶负债明细
			var viewSelf=this;
			var oTable = $("#tb_drCldList").dataTable({
				bFilter:false,
				bLengthChange:false,
				bSort:false,
		    	sAjaxSource: $$ctx+"drCreditInfo/findDrCldList",
		    	fnServerParams:function(aoData){
		    		aoData.push({name:"projectId",value:$("#projectId").val()});
		    	},
		    	aoColumns: [
		    	    {mData:"drName"},
		    	    {mData:"financialOrgName"},
		    	    {mData:"loanAmt"},
		    	    {mData:"loanStartDate"},
		    	    {mData:"pawns",mRender:function(data, type, rowdata){
		    	    	var result=[];
		    	    	if(data){
							var t_pawns=data.split(",");
							var pawnNames=['无','房','车','其他'];
							$.each(t_pawns,function(i,val){
								result.push(pawnNames[val]);
							});
						}
		    	    	return result.join(",");
		    	    }},
		    	    {mData:"loanOverAmt"},
		    	    {mData:"repaymentModeCd",mRender:function(data, type, rowdata){
		    	    	return dataDict.RepaymentMode[data];
		    	    }},
		    	    {mData:"monthAvgRepayAmt"},
		    	    {mData:"isShow",mRender:function(data, type, rowdata){
		    	    	return data=="1"?"是":"否";
		    	    }},
		    	    {mData:null,mRender: function(data, type, rowdata){
		    	    	if($('#judgeType').val() != 'check') {
		    	    		var html=[];
			    	    	html.push("<div class='btn-group'style='width:108px;'>");
			    	    	html.push("<button title='修改' role='drCredit_drCld_edit' data-id='" + rowdata.drCreditLiabilityDetailId + "' class='btn btn-xs btn-info'><i class='ace-icon fa fa-edit'></i></button>");
			    	    	html.push("<button title='删除' role='drCredit_drCld_delete' data-id='" + rowdata.drCreditLiabilityDetailId + "' class='btn btn-xs btn-danger'><i class='ace-icon fa fa-trash-o'></i></button>");
			    	    	html.push("</div>");
			    	    	return html.join("");
		    	    	}
		    	    	return "";
		    	    }}
		    	]
			});
			viewSelf.drCldTable = oTable;
		},
		initDrCldForm:function(){
			var viewSelf=this;
			var drCLdForm=$("#dr_credit_drCld_form");
			drCLdForm.find(":text[name='loanStartDateStr']").datepicker({
				format:"yyyy-mm-dd",
				todayBtn:true,
				autoclose:true
				}).on("click",function(){
			}).on("changeDate",function(){
				drCLdForm.validate().element(":text[name='loanStartDateStr']");
			}).on("show",function(){
				$(".datepicker").css("z-index","99999");
			});
			drCLdForm.find(":checkbox[name='pawn']").change(function(){
				var pawns=drCLdForm.find(":checkbox[name='pawn']:checked");
				var curVal=$(this).val();
				var pawnVals=[];
				 $(pawns).each(function(){
					 pawnVals.push($(this).val());
				 });
				var pawn0=drCLdForm.find(":checkbox[name='pawn'][value='0']")
				if($.inArray(pawn0.val(),pawnVals)>=0){
					if(curVal==pawn0.val()){
						pawn0.siblings(":checkbox[name='pawn']").removeAttr("checked");
					}else{
						pawn0.removeAttr("checked");
					}
				}
			});
			drCLdForm.validate({
				rules: rm_dr_cld.rules,
                messages: rm_dr_cld.messages,
                errorPlacement: function(error, element) {
                    error.appendTo(element.parent());
                },
                submitHandler: function(form) {
                	viewSelf.model.saveDrCld(drCLdForm.serialize(),function(r_data){
                		if(r_data.success){
                			$("#dr_credit_modal").modal("hide");
            				viewSelf.drCldTable.fnDraw();
                		}else{
                			utils.alert.err("<strong>" + r_data.msg + "</strong>");
                		}
                	});
                    return false;
                }
			});
		},
		initBtnLive:function(){
			var viewSelf=this;
			$(document).on("click","button[role='drCredit_drCld_edit']",function(){
				var btnSelf=$(this);
				viewSelf.model.findDrCld(btnSelf.data("id"),function(r_data){
					if(r_data.success){
						viewSelf.fnDrCldFormReSet(r_data.data);
						$("#dr_credit_modal").modal("show");
					}
				});
			});
			$(document).on("click","button[role='drCredit_drCld_delete']",function(){
				var btnSelf=$(this);
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
							viewSelf.model.delDrCld(btnSelf.data("id"),function(r_data){
								if(r_data.success){
									utils.alert.suc("<strong>删除成功！</strong>");
									viewSelf.drCldTable.fnDraw();
								}else{
									utils.alert.err("<strong>" + r_data.msg + "</strong>");
								}
							});
						}
					}
				});
			});
		},
		fnDrCldAdd:function(){
			var viewSelf=this;
			viewSelf.fnDrCldFormReSet();
			$("#dr_credit_modal").modal("show");
		},fnDrCldFormReSet:function(data){
			var $form=$("#dr_credit_drCld_form");
			$form.validate().resetForm();
			if(data){
				 $(data).each(function(index) {
					 for ( var key in data) {
						 if($.inArray(key,['isShow'])<0){
							 $form.find(":hidden[name='"+key+"']").val(data[key]);
							 $form.find(":text[name='"+key+"']").val(data[key]);
							 
						 }
					}
					$form.find(":radio[name='isShow'][value="+data.isShow+"]").prop("checked",true);
					$form.find(":text[name='loanStartDateStr']").val(data.loanStartDate);
					$form.find(":text[name='loanAmtStr']").val(data.loanAmt);
					$form.find(":text[name='loanOverAmtStr']").val(data.loanOverAmt);
					$form.find(":text[name='monthAvgRepayAmtStr']").val(data.monthAvgRepayAmt);
					if(data.pawns){
						var t_pawns=data.pawns.split(",");
						$.each(t_pawns,function(i,val){
							$form.find(":checkbox[name='pawn'][value="+val+"]").prop("checked",true);
						})
					}
				 });
			}else{
				$form.find(":hidden[name='drCreditLiabilityDetailId']").val('');
			}
		}
	});
	module.exports = view;
})