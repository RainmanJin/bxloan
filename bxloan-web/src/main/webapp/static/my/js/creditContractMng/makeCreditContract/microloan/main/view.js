/**
 * 授信业务申请-微贷-制定电子合同主页面view.js
 * @author wangyawei
 * @createDate 2015年7月10日
 */
define(function(require, exports, module) {
    var model = require("./model");
    var utils = require("utils");
    var format_functions = {};
    var view = Backbone.View.extend({
        el: "body",
        events: {
        	/** 保存授信合同信息 */
            "click #saveCreditContractInfo": "saveCreditContractInfo",
            /** 查询客户信息 */
            "click #openCustomerWindow": "openCustomerWindow",
            /** 查询授信业务信息 */
            "click #openBusinessWindow": "openBusinessWindow",
            /** 查询过程意见 */
            "click #openSuggestionWindow": "openSuggestionWindow"
        },
        initialize: function() {
            this.model = new model();
            this.render()
        },
        render: function() {
        	var viewSelf = this;
        	viewSelf.initFirstOfAll();
        	/** 初始化主合同信息 */
        	viewSelf.initContractInfoForm();
        	
			utils.dd.initDataDict(["GuaranteeTypeCode", "CertificateType"], function(dataDict) {
				/** 初始化主合同信息-抵质押物列表 */
				viewSelf.initDataTablesForCollateral(dataDict);
				/** 初始化主合同信息-保证人列表 */
				viewSelf.initAssureTable(dataDict);
			});
        	/** 初始化iframe高度 */
			viewSelf.initFrameHight();
        },
        initFirstOfAll: function() {
        	format_functions = {
        			checkFinalRateReason: function (obj) {
        				var rate = obj.value;
        				if(rate == $("#finalFloatRateField").val()) {
        					$("#checkFinalRateField").val(0);
        				}else {
        					$("#checkFinalRateField").val(1);
        				}
        			},
        			//检查利率类型，类型为"固定利率"，显示年利率;类型为"浮动利率"，显示利率上浮比例等信息
        			checkRateType: function(obj){
        				var type = $(obj).val();
    					if(type == "2"){
    						$("#finalRateValueDiv").hide();
    						$("#finalAdjustDiv").show();
    					} else{
    						$("#finalRateValueDiv").show();
    						$("#finalAdjustDiv").hide();
    					}
        			}
        	};
        },
        initContractInfoForm: function() {
        	 var viewSelf = this;
             //授信贷款期限拼接
             var contractTermShow = function(_contractTerm){
             	 var _array = /^([\d]+)([\u4e00-\u9fa5]+)/g.exec(_contractTerm);
                  if(_array && _array.length > 1){
                  	 var _span = "<span class='input-group-addon'>"+ _array[2] +"</span>";
                  	 $("#form-contractInfo").find("input[name='creditContractTerm']").val(_array[1]);
                  	 $("#form-contractInfo").find("input[name='creditContractTerm']").parent().append(_span);
                  }
             };
             contractTermShow($("#creditContractTerm").val());
             //检查利率类型，类型为"固定利率"，显示年利率;类型为"浮动利率"，显示利率上浮比例等信息
             $("#finalFloatRateField").val($("#finalFloatRate").val());
             format_functions.checkRateType("#finalIrTypeCd"); 
             var $form = $("#form-contractInfo");
             //格式化金额
             $.each($form.find('.num_amt_2fixed'), function(){
            	 $(this).val(utils.number.toAmt($(this).val()));
             });
             //格式化利率
             $.each($form.find('.num_2fixed'),function(){
				$(this).val(utils.number.toFixed($(this).val(),2));
			 });
        },
        initDataTablesForCollateral: function(dataDict) {
        	var viewSelf = this;
            var oTable = $("#tbCollateral").dataTable({
                sAjaxSource: $$ctx + "contractMng/searchCollateralList",
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
                        "name": "projectId",
                        "value": $('#projectIdField').val()
                    });
                },
                fnDrawCallback : function(){
                	utils.num.colsFormat(this, [3,5]);
                }
            });
            viewSelf.oTable = oTable;
        },
        initAssureTable : function(dataDict) {
			var viewSelf = this;
			var oTable = $("#bailTable").dataTable({
				sAjaxSource : $$ctx + "creditContractMng/searchBailList",
				bFilter : false,
				bLengthChange : false,
				 aoColumns: [
                     {
	                   	  mData: "customerName"
                     },
                     {
	                   	  mData: "certificateTypeCd", 
	                   	  mRender: function(data, type, rowdata) {
	                          return dataDict.CertificateType[data];
	                   	  }
                     },
                     {
                    	  mData: "certificateNum"
                     },
                     {
                   	  	  mData: "currency",
                   	  	  mRender: function(data, type, rowdata) {
               	  			  return "人民币";
                   	  	  }
                     },
                     {
                    	  mData: "guaranteeAmt"
                     },
                     {
                    	 mData: "actualGuaranteeAmt"
                     }
                ],
				fnServerParams : function(aoData) {
					aoData.push({
						name : "projectId",
						value : $('#projectIdField').val()
					});
				},
                fnDrawCallback : function(){
                	utils.num.colsFormat(this, [5, 6]);
	            }
			});
			viewSelf.oTable = oTable;
		},
		saveCreditContractInfo: function() {
            if ($("#form-contractInfo").valid()) {
            	utils.button.ban("#saveCreditContractInfo");
                $.ajax({
                    type: "POST",
                    url: $$ctx + "creditContractMng/saveCreditSubContract?projectId=" + $("#projectIdField").val(),
                    data: $("#form-contractInfo").serialize(),
                    async: true,
                    cache: true,
                    error: function(request) {
                    	utils.alert.err( "<strong>获取授信合同信息出错</strong>");
                    },
                    success: function(result) {
                        if (result.success) {
                        	$("#tbDzy").dataTable().fnDraw();
                        	$("#tbBzr").dataTable().fnDraw();
                        	utils.alert.suc("<strong>"+result.msg+"</strong>", function(){
                            	utils.button.reset("#saveCreditContractInfo");
                        	});
                        } else {
                        	utils.alert.err("<strong>"+result.msg+"</strong>", function(){
                            	utils.button.reset("#saveCreditContractInfo");
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
                /*$modal.find('span[role="modal-title"]').text('客户信息');*/
                $modal.modal("show").css({});
            });
        },
        openBusinessWindow: function() {
            viewSelf = this;
            viewSelf.model.openBusinessWindow({
            	workflowId: $("#workflowIdField").val(),
                consultLocation: "contract"
            }, function(result) {
            	$("#mainFrame").attr("src", $$ctx + result);
            	var $modal=$("#modalForView");
               /* $modal.find('span[role="modal-title"]').text('业务信息');*/
                $modal.modal("show").css({});
            });
        },
        openSuggestionWindow: function() {
            viewSelf = this;
            $("#mainFrame").attr("src", $$ctx + "approval/processSuggestion?projectId=" + $("#projectIdField").val() + "&workflowId=" + $("#workflowIdField").val());
            var $modal=$("#modalForView");
//            $modal.find('span[role="modal-title"]').text('过程意见');
            $modal.modal("show").css({});
        },
        initFrameHight: function(){
			$("#mainFrame").load(function() {
        	    setInterval(function(){
        	    	var clientHeight = $("#mainFrame").contents().find("body").height();
        	    	$("#mainFrame").attr("height",clientHeight+"px!important;");
        	    },100);
			});
		}
    });
    module.exports = view;
});