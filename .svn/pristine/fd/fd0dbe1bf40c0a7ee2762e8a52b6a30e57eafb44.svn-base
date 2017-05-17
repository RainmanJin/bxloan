/**
 * 授信下借款业务申请-签订合同主页面view.js
 * @author 
 * @createDate 2015年7月10日
 */
define(function(require, exports, module) {
	var rm = require("./rm");
    var model = require("./model");
    var utils = require("utils");
    var view = Backbone.View.extend({
        el: "body",
        events: {
        	/** 保存授信下借款合同信息 */
            "click #saveUCContractInfo": "saveUCContractInfo",
            /** 查询客户信息 */
            "click #openCustomerWindow": "openCustomerWindow",
            /** 查询授信业务信息 */
            "click #openBusinessWindow": "openBusinessWindow",
            /** 查询过程意见 */
            "click #openSuggestionWindow": "openSuggestionWindow",
            "click #loanDateStyle": "changeLoanDateStyle"
        },
        initialize: function() {
            this.model = new model();
            this.render()
        },
        render: function() {
        	/** 初始化主合同信息 */
        	this.initContractInfoForm();
        	/** 初始化主合同信息-抵质押物列表 */
        	this.initDataTablesForCollateral();
        	/** 初始化主合同信息-保证人列表 */
        	this.initAssureTable();
        },
        initContractInfoForm: function() {
        	 var viewSelf = this;
        	    $("#form-contractInfo").validate({
                    rules: rm.rules,
                    messages: rm.messages,
                    errorPlacement: function(error, element) {
                        if (element.is(":radio")) error.appendTo(element.parent().next().next());
                        else if (element.is(":checkbox")) error.appendTo(element.next());
                        else error.appendTo(element.parent());
                    }
                });
             //期限拼接
             var contractTermShow = function(_contractTerm){
             	 var _array = /^([\d]+)([\u4e00-\u9fa5]+)/g.exec(_contractTerm);
                  if(_array && _array.length > 1){
                  	 var _span = "<span class='input-group-addon'>"+ _array[2] +"</span>";
                  	 $("#form-contractInfo").find("input[name='contractTerm']").val(_array[1]);
                  	 $("#form-contractInfo").find("input[name='contractTerm']").parent().append(_span);
                  }
             };
             contractTermShow($("#contractTerm").val());
     		//检查利率类型，类型为"固定利率"，显示年利率;类型为"浮动利率"，显示利率上浮比例等信息
     		var	checkRateType=function(type){
 					if(type == "2"){
 						$("#finalRateValueDiv").hide();
 						$("#finalAdjustDiv").show();
 					} else{
 						$("#finalRateValueDiv").show();
 						$("#finalAdjustDiv").hide();
 						$("#finalFloatRate").attr("disabled","disabled")
 					}
     			};
     		 checkRateType($("#finalIrTypeCd").val());
             //检查利率类型，类型为"固定利率"，显示年利率;类型为"浮动利率"，显示利率上浮比例等信息
            // $("#finalFloatRateField").val($("#finalFloatRate").val());
             
             var $form = $("#form-contractInfo");
             //格式化金额
             $.each($form.find('.num_amt_2fixed'), function(){
            	 $(this).val(utils.number.toAmt($(this).val()));
             });
             //格式化利率
             $.each($form.find('.num_2fixed'),function(){
				$(this).val(parseFloat($(this).val()));
			 });
        },
        initDataTablesForCollateral: function() {
        	var viewSelf = this;
        	utils.dd.initDataDict(["GuaranteeTypeCode"],
     	            function(dataDict) {
     	                var oTable = $("#tbCollateral").dataTable({
     	                    sAjaxSource: $$ctx + "signCreditLoanContract/searchCollateralList",
     	                    bFilter: false,
     	                    bLengthChange: false,
     	                    aoColumns: [
                              {
                            	  bVisible: false,
                            	  mData: "projectPawnInfoId"
                              },
                              {
                            	  mData: "guarantyName"
                              },
                              {
                            	  mData: "guaranteeType", 
                            	  mRender: function(data, type, rowdata) {
     	                        	 return dataDict.GuaranteeTypeCode[data];
                            	  }
                              },
                              {
                            	  mData: "appGuaDebtInterAmt"
                              },
                              {
                            	  mData: "pawnRatio",
                            	  mRender: function(data, type, rowdata) {
                            		  var out = (data * 100).toFixed(2) + "";
	  	                        	  if(out.length > 10){
	  	                        		  return out.substring(0, 10);
	  	                        	  }
	  	                        	  return out;
      	                          }
                              },
                              {
                            	  mData: "actualCreditAmount"
                              },
                              {
                            	  mData: "actualGuaranteeRate",
                            	  mRender: function(data, type, rowdata) {
   	                        	   	   var out = (data * 100).toFixed(2) + "";
   	                        	   	   if(out.length > 10){
   	                        	   		    return out.substring(0, 10);
   	                        	   	   }
   	                        	   	return out;
  	                        	  }
                              }/*,
                              {
                            	  mData: "projectPawnInfoId",
                            	  mRender: function(data, type, rowdata) {
                            		  var workType = $("#workTypeField").val();
                            		  var button = "－";
                            		  if(workType != "" ){
                            			  return button;
                            		  } else{
                            			  return "<button type='button' role='edit_collateral' data-id='" + data + "' class='btn btn-xs btn-info'><i class='ace-icon fa fa-edit' title='修改'></i></button>";
                            		  }
                            	  }
                              }*/
     	                    ],
     	                    fnServerParams: function(aoData) {
	     	                   	aoData.push({
	     							name : "workflowId",
	     							value : $('#workflowIdField').val()
	     						});
     	                    },
     	                    fnDrawCallback : function(){
	     	                	utils.num.colsFormat(this, [3,5]);
	     	                }
     	                });
     	                viewSelf.oTable = oTable;
 	            });
        },
        initAssureTable : function() {
			var viewSelf = this;
			var oTable = $("#bailTable").dataTable({
				sAjaxSource : $$ctx + "underCreditContractMng/searchBailList",
				bFilter : false,
				bLengthChange : false,
				aoColumnDefs : [/* {
					aTargets : [ 5 ],
					mRender : function(data, type, full) {
						var button = "<div class='btn-group'style='width:100px;'><button title='查看' type='button' role='checkBail' data-id='" + data + "' class='btn btn-xs btn-yellow'><i class='ace-icon fa fa-eye'></i></button>";
						return button;
					}
				} */],
				fnServerParams : function(aoData) {
					aoData.push({
						name : "workflowId",
						value : $('#workflowIdField').val()
					});
				},
                fnDrawCallback : function(){
                	utils.num.colsFormat(this, [5]);
	            }
			});
			viewSelf.oTable = oTable;
		},
		saveUCContractInfo: function() {
            if ($("#form-contractInfo").valid()) {
            	utils.button.ban("#saveUCContractInfo");
                $.ajax({
                    type: "POST",
                    url: $$ctx + "signCreditLoanContract/saveUCCreditSubContract?workflowId=" + $("#workflowIdField").val(),
                    data: $("#form-contractInfo").serialize(),
                    async: true,
                    cache: true,
                    error: function(request) {
                    	utils.alert.err( "<strong>获取授信借款合同信息出错</strong>");
                    },
                    success: function(result) {
                        if (result.success) {
                        	$("#tbDzy").dataTable().fnDraw();
                        	$("#tbBzr").dataTable().fnDraw();
                        	utils.alert.suc("<strong>"+result.msg+"</strong>", function(){
                            	utils.button.reset("#saveUCContractInfo");
                        	});
                        } else {
                        	utils.alert.err("<strong>"+result.msg+"</strong>", function(){
                            	utils.button.reset("#saveUCContractInfo");
                        	});
                        }
                    }
                });
            }
        },
        openCustomerWindow: function() {
        	//客户类型
        	var partyType = $("#partyTypeField").val() + ''; 
        	//参与人ID
        	var partyId = $("#partyIdField").val();
            viewSelf = this;
            viewSelf.model.openCustomerWindow(partyType,{
                customerId : partyId,
                workCode : "TODETAIL",
                customerSource : "detail",
                consultLocation : "contract"
            }, function (result){
            	$("#mainFrame").attr("src", result);
            	var $modal=$("#modalForView");
                $modal.find('span[role="modal-title"]').text('客户信息');
                $modal.modal("show").css({});
            });
        },
        openBusinessWindow: function() {
            viewSelf = this;
            var consultLocation = "contract";
            var url = $$ctx + "underCreditContractMng/checkApplicationWindow/"+$("#workflowIdField").val()+"/"+consultLocation;
            $("#mainFrame").attr("src", url);
            $("#modalForView").modal("show");
        },
        openSuggestionWindow: function() {
            viewSelf = this;
            $("#mainFrame").attr("src", $$ctx + "approval/processSuggestion?projectId=" + $("#projectIdField").val() + "&workflowId=" + $("#workflowIdField").val());
            var $modal=$("#modalForView");
            $modal.find('span[role="modal-title"]').text('过程意见');
            $modal.modal("show").css({});
        },
      //libingqing 2015-8-5 start
        changeLoanDateStyle: function() {
            if ($("#loanDateStyle").val() == "1") {
                $("#arrangeRepayDay").removeAttr("readonly");
            } else if ($("#loanDateStyle").val() == "2") {
            	$("#arrangeRepayDay").val("");
                $("#arrangeRepayDay").attr("readonly", "readonly");
            } else {
            	$("#arrangeRepayDay").val("");
                $("#arrangeRepayDay").attr("readonly", "readonly");
                return false;
            }
        }
        //libingqing 2015-8-5 end
    });
    module.exports = view;
});