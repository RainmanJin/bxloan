/**
 * 合同表单界面的view.js 微贷
 * */

define(function(require, exports, module) {
    var model = require("./model");
    var rm = require("./rm");
    var rm_co = require("./rm_collateral");
    var utils = require("utils");
    var format_functions = {};
    var view = Backbone.View.extend({
        el: "body",
        events: {
            "click #saveContractInfo": "saveContractInfo",
            "click #saveSuggest": "saveSuggest",
            "click #submitThisContract": "submitThisContract",
            "change #loanDateStyle": "changeLoanDateStyle",
            "click #add-Wd-submit": "submitDocument",
            "click #uploadContractContent": "uploadLoanApply",
            "click #downloadContractContent": "downloadLoanApply",
            "click #addBizWd": "addBizWd",
            "click #btn-query": "queryDocuments",
            "click #customerForFloatWindow": "openCustomerWindow",
            "click #businessForFloatWindow": "openBusinessWindow",
            "click #suggestionForFloatWindow": "openSuggestionWindow",
            "click #loanTrialWindow": "openLoanTrialWindow",
            "click #select_assigner_sure": "submitAppr",
            "click #modCollateral_sure": "modifyCollateral",
            //"change input[name='actualCreditAmount']" : "changeActualGuaranteeRate",
            //add by wangyawei 20150701 start 在审批过程中点击生成邦农贷财务报表时，下载邦农贷财务报表
			"click #bndFinancialStatements": "downloadFinancialStatementsForBnd"
			//add by wangyawei 20150701 end
        },
        initialize: function() {
            this.model = new model();
            this.render()
        },
        render: function() {
        	this.initFirstOfAll();
        	this.initFrameHight();
        	this.pdownloadBtnLive();
            this.initContractMessages();
            this.initContractInfoForm();
            this.initCollateralInfoForm();
            this.downloadBtnLiveWd();
            this.deleteBtnLiveWd();
            this.editBtnLiveCollateral();
            this.initDismissBtn();
            this.borrowerDetailLiveBtn();
            this.initDataTablesForCollateral();
            this.initDataTablesForExpense();
            this.initDataTablesForDI();
            this.initDataTablesForBorrowers();
            
            this.initDataTablesForPresentCollateral();
            this.initDataTablesForSubContract();
            this.uploadBtnLiveSubContract();
            this.initEndOfAll();
            
            
            this.downloadContrFileLiveWd();
        },
        initFirstOfAll: function() {
        	format_functions = {
        			checkFinalRateReason: function (obj) {
        				var rate = obj.value;
        				if(rate==$("#finalFloatRateField").val()) {
        					$("#checkFinalRateField").val(0);
        				}else {
        					$("#checkFinalRateField").val(1);
        				}
        			},
        			 checkRateType:function(obj){
        				var type = $(obj).val();
        				if(type=="2"){
        					$("#finalRateValueDiv").hide();
        					$("#finalAdjustDiv").show();
        				}else{
        					$("#finalRateValueDiv").show();
        					$("#finalAdjustDiv").hide();
        				}
        			}
        	};
        },
        pdownloadBtnLive: function(){
        	var viewSelf = this;
        	$(document).on("click","#wdcb",function(e){
        		var cbs = $("#tbWd").find(":checkbox[name='documentNums']");
        		if($("#wdcb").is(":checked")){
        			cbs.prop("checked",true);
        		}else{
        			cbs.prop("checked",false);
        		}
        	});
        	$(document).on("click","button[role='pdownloadWd']",function(e){
        		e.stopPropagation();
        		var root_upload = $("#uploadPathField").val();
        		var cks = $("#tbWd input[name='documentNums']:checked");
        		var choice = [];
        		$.each(cks, function(i,e){
        			choice.push(e.value);
        		});
        		if(choice.length<1){
        			utils.alert.warn("必须选择一个文件才能进行下载");
        			return false;
        		}
        		 var fileName = "BatchFiles";
        		 var route = root_upload + '/downloadFileAction.action?cmd=downloadfilejs&DocumentNums=' + choice.join(",") +  "&filename="+ fileName +"&SYS_FLAG=bxloan";
        		 location.href = route;
        	});
        },
        initContractInfoForm: function() {
            $("#form-contractInfo").validate({
                rules: rm.rules,
                messages: rm.messages,
                errorPlacement: function(error, element) {
                    if (element.is(":radio")) error.appendTo(element.parent().next().next());
                    else if (element.is(":checkbox")) error.appendTo(element.next());
                    else error.appendTo(element.parent());
                }
            });
        },
        initCollateralInfoForm:function(){
        	$("#form-collateral").validate({
                rules: rm_co.rules,
                messages: rm_co.messages,
                errorPlacement: function(error, element) {
                    if (element.is(":radio")) error.appendTo(element.parent().next().next());
                    else error.appendTo(element.parent());
                }
            });
        },
        initDataTablesForCollateral: function() {
        	var viewSelf = this;
        	 utils.dd.initDataDict(["GuaranteeTypeCode"],
     	            function(dataDict) {
     	                var oTable = $("#tbCollateral").dataTable({
     	                    "sAjaxSource": $$ctx + "contractMng/searchCollateralList",
     	                    "bFilter": false,
     	                    "bLengthChange": false,
     	                   "aoColumns": [{"bVisible": false,
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
     	                    	  var out = (data*100).toFixed(3)+"";
  	                        	   if(out.length>10){
  	                        		   return out.substring(0,10);
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
   	                        	   var out = (data*100).toFixed(3)+"";
   	                        	   if(out.length>10){
   	                        		   return out.substring(0,10);
   	                        	   }
   	                        	   return out;
      	                        }
     	                    },
     	                    {
     	                        mData: "projectPawnInfoId",
     	                        mRender: function(data, type, rowdata) {
     	                        var workType = $("#workTypeField").val();
     	                        var button = "－";
     	                        	if(workType!=""){
     	                        		return button;
     	                        	}else{
     	                        		return "<button type='button' role='edit_collateral' data-id='" + data + "' class='btn btn-xs btn-info'><i class='ace-icon fa fa-edit' title='修改'></i></button>";
     	                        	}
     	                        }
     	                    }
     	                    ],
     	                    "fnServerParams": function(aoData) {
     	                        aoData.push({
     	                            "name": "projectId",
     	                            "value": $('#projectIdField').val()
     	                        });
     	                    }
     	                });
     	                viewSelf.oTable = oTable;
     	            });
        },
        initDataTablesForSubContract: function(){
        	 var viewSelf = this;
        	 utils.dd.initDataDict(["CommonWhether", "CertificateType"],
        	            function(dataDict) {
        	                var oTable = $("#tbBzr").dataTable({
        	                    "sAjaxSource": $$ctx + "contractMng/searchSubContractList",
        	                    "bFilter": false,
        	                    "bLengthChange": false,
        	                    "aoColumns": [null,null,null,null,null,null,null,null,null],
        	                    "aoColumnDefs": [{
        	                        "bVisible": false,
        	                        "aTargets": [0]
        	                    },
        	                    {
           	                        "aTargets": [3],
           	                        "mRender": function(data, type, full) {
           	                            return dataDict.CertificateType[data];
           	                        }
           	                    },
        	                    {
           	                        "aTargets": [6],
           	                        "mRender": function(data, type, full) {
           	                        	if(data==null){
           	                        		return "否";
           	                        	}
           	                            return dataDict.CommonWhether[data];
           	                        }
           	                    },
           	                    {
           	                        "bVisible": false,
           	                        "aTargets": [7]
           	                    },
        	                    {
        	                        "aTargets": [8],
        	                        "mRender": function(data, type, full) {
        	                            var button = "<button type='button' role='downloadGuarContr' data-subid='"+ full[0] +"' data-paId='" + data + "'  class='btn btn-xs btn-yellow' title='下载保证从合同'><i class='ace-icon fa fa-download'></i></button> ";
        	                            if($("#workTypeField").val()==""){
        	                            	button += "<button type='button' role='uploadGuarContr' data-subid='"+ full[0] +"' data-paId='" + data + "'  class='btn btn-xs btn-info' title='上传保证从合同'><i class='ace-icon fa fa-upload'></i></button></button>" ;
        	                            }
        	                        	return button;
        	                        }
        	                    }],
        	                    "fnServerParams": function(aoData) {
        	                        aoData.push({
        	                            "name": "projectId",
        	                            "value": $('#projectIdField').val()
        	                        });
        	                    }
        	                });
        	                viewSelf.oTable = oTable;
        	            });
        },
        initDataTablesForPresentCollateral: function(){
       	 var viewSelf = this;
       	 utils.dd.initDataDict(["CommonWhether", "GuaranteeTypeCode"],
       	            function(dataDict) {
       	                var oTable = $("#tbDzy").dataTable({
       	                    "sAjaxSource": $$ctx + "contractMng/searchPresentCollateralList",
       	                    "bFilter": false,
       	                    "bLengthChange": false,
       	                    "aoColumns": [null,null,null,null,null,null,null,null,null,null,null,null,null],
       	                    "aoColumnDefs": [{
       	                        "bVisible": false,
       	                        "aTargets": [0]
       	                    },
       	                    {
       	                        "aTargets": [7],
       	                        "mRender": function(data, type, full) {
       	                            return dataDict.GuaranteeTypeCode[data];
       	                        }
       	                    },
       	                    {
       	                        "aTargets": [8],
       	                        "mRender": function(data, type, full) {
       	                        	if(data==null){
       	                        		return "否";
       	                        	}
       	                            return dataDict.CommonWhether[data];
       	                        }
       	                    },
       	                    {
       	                        "bVisible": false,
       	                        "aTargets": [9]
       	                    },
       	                    {
       	                        "bVisible": false,
       	                        "aTargets": [11]
       	                    },
       	                    {
       	                        "bVisible": false,
       	                        "aTargets": [12]
       	                    },
       	                    {
       	                        "aTargets": [10],
       	                        "mRender": function(data, type, full) {
       	                        	
       	                        	var arr =["0","1","2","3","4","17","18"];
       	                            var button ="";
       	                            if($("#workTypeField").val()==""){
       	                            	button += "<button type='button' role='upload_subcontract' data-subid='"+ full[0] +"'  data-guid='"+ full[11] +"'  data-paId='" + data + "'  class='btn btn-xs btn-info' title='上传抵质押从合同'><i class='ace-icon fa fa-upload'></i></button></button>" ;
       	                            }
       	                            button += "<button type='button' role='downloadCollaContr' data-subid='"+ full[0] +"' data-guid='"+ full[11] +"'  data-paId='" + data + "'  data-guatype='"+ full[12] +"'  class='btn btn-xs btn-yellow' title='下载抵质押从合同'><i class='ace-icon fa fa-download'></i></button> ";
       	                            if(arr.indexOf(full[12])<0){
       	                            	button+= "<button type='button' role='downloadPledContrList' data-subid='"+ full[0] +"' data-guid='"+ full[11] +"'  data-paId='" + data + "' data-guatype='"+ full[12] +"'  class='btn btn-xs btn-success' title='下载抵质押从合同清单'><i class='ace-icon fa fa-download'></i></button> " ;
       	                            }else{
       	                            	button+= "<button type='button' role='downloadPledContrList' data-subid='"+ full[0] +"' data-guid='"+ full[11] +"'  data-paId='" + data + "' data-guatype='"+ full[12] +"'  class='btn btn-xs btn-success' title='下载抵质押从合同清单' disabled><i class='ace-icon fa fa-download'></i></button> " ;
       	                            }
       	                            			
       	                            			
       	                            return button;
       	                        }
       	                    }],
       	                    "fnServerParams": function(aoData) {
       	                        aoData.push({
       	                            "name": "projectId",
       	                            "value": $('#projectIdField').val()
       	                        });
       	                    }
       	                });
       	                viewSelf.oTable = oTable;
       	            });
       },
        initDataTablesForBorrowers: function() {
        	 var viewSelf = this;
        	 utils.dd.initDataDict(["CertificateType"],
        	            function(dataDict) {
        	                var oTable = $("#tbCoborrower").dataTable({
        	                    "sAjaxSource": $$ctx + "contractMng/searchBorrowerList",
        	                    "bFilter": false,
        	                    "bLengthChange": false,
        	                    "aoColumns": [null,null,null,null,null,null,null,null,null,null],
        	                    "aoColumnDefs": [{
        	                        "bVisible": false,
        	                        "aTargets": [0]
        	                    },
        	                    {
        	                        "bVisible": false,
        	                        "aTargets": [9]
        	                    },
        	                    {
        	                        "bVisible": false,
        	                        "aTargets": [8]
        	                    },
        	                    {
        	                        "aTargets": [2],
        	                        "mRender": function(data, type, full) {
        	                        	if(data){
        	                        		return dataDict.CertificateType[data];
        	                        	}else{
        	                        		return "";
        	                        	}
        	                        }
        	                    },
        	                    {
        	                        "aTargets": [7],
        	                        "mRender": function(data, type, full) {
        	                            var button = "<button type='button' role='detail_borrower' data-type='"+ full[2] +"' data-paId='" + data + "' data-flag='" + full[8] + "' data-partyid='" + full[9] + "' class='btn btn-xs btn-yellow' title='查看'><i class='ace-icon fa fa-eye'></i></button>";
        	                        	return button;
        	                        }
        	                    }],
        	                    "fnServerParams": function(aoData) {
        	                        aoData.push({
        	                            "name": "projectId",
        	                            "value": $('#projectIdField').val()
        	                        });
        	                    }
        	                });
        	                viewSelf.oTable = oTable;
        	            });
        },
        initDataTablesForExpense: function() {
            var viewSelf = this;
            var id = null;
            utils.dd.initDataDict(["FeeUsedType", "FeeType"],
            function(dataDict) {
                var oTable = $("#tbExpenseRate").dataTable({
                    "sAjaxSource": $$ctx + "bizapply/searchBizExpenseRateList",
                    "bFilter": false,
                    "bLengthChange": false,
                    /*"sPaginationType": "full_numbers",*/
                    "aoColumns": [{
                        mData: "bizExpenseRateId"
                    },
                    {
                        mData: "expenseCollectionType"
                    },
                    {
                        mData: "expenseName"
                    },
                    {
                        mData: "standardExpenseRate"
                    },
                    {
                        mData: "expenseRate"
                    },
                    {
                        mData: "standaredAmt"
                    },
                    {
                        mData: "expenseAmt"
                    },
                    {
                        mData: "sysCreateDateStr"
                    },
                    {
                        mData: "sysCreateDate"
                    },
                    ],
                    "aoColumnDefs": [{
                        "bVisible": false,
                        "aTargets": [0]
                    },
                    {
                        "aTargets":  [0],
                        "mRender": function(data, type, full) {
                            id = data;
                            return data;
                        }
                    },
                    {
                        "aTargets":  [1],
                        "mRender": function(data, type, full) {
                            return dataDict.FeeUsedType[data];
                        }
                    },
                    {
                        "aTargets":  [2],
                        "mRender": function(data, type, full) {
                            return dataDict.FeeType[data];
                        }
                    },
                    {
                        "bVisible": false,
                        "aTargets":  [8]
                    }],
                    "fnServerParams": function(aoData) {
                        aoData.push({
                            "name": "projectNo",
                            "value": $('#projectNoField').val()
                        });
                    }
                });
                viewSelf.oTable = oTable;
            });
        },
        initContractMessages: function() {
            var viewSelf = this;
            var contractId = $("#contractIdField").val();
            var projectId = $("#projectIdField").val();
            $.ajax({
                cache: true,
                type: "POST",
                url: $$ctx + "contractMng/getContractInfo",
                data: {
                    "projectId": projectId,
                    "contractId": contractId
                },
                async: false,

                error: function(request) {
                    alert("获取合同信息出错" + request)
                },
                success: function(data) {
                    if (data != null) {
                        var workType = $("#workTypeField").val();
                        if (workType != "") {
                            viewSelf.hideFileds();
                            
                        }
                        $.each($("#form-contractInfo").find("input[type='text'], select, textarea"),
                        function() {
                            $(this).val(data[$(this).attr("name")]);
                            if (workType != "") {
                                $(this).attr("disabled", "disabled");
                            }
                        });
                        var termShow = function(_contractTerm){
                        	 var _array = /^([\d]+)([\u4e00-\u9fa5]+)/g.exec(_contractTerm);
                             if(_array&&_array.length>1){
                             	 var _span = "<span class='input-group-addon'>"+ _array[2] +"</span>";
                                  $("#form-contractInfo").find("input[name='contractTerm']").val(_array[1]);
                                  $("#form-contractInfo").find("input[name='contractTerm']").parent().append(_span);
                             }
                        }
                        termShow(data['contractTerm']);
                       
                        if (document.getElementById("arrangeRepayDay").value != "null") {
                        } else {
                            $("#arrangeRepayDay").val("");
                        }
                        $.each($("#form-suggestion").find("textarea"),
                        function() {
                            $(this).val(data[$(this).attr("name")]);
                            if (workType != "") {
                                $(this).attr("disabled", "disabled");
                                //反显时，如果是意见隐藏
                                if($(this).attr("name") == 'opinion'){
                                	$("#form-suggestion .pre-opinion").css("display","none");
                                }
                            }
                        });
                        if ($("#loanDateStyle").val() == null) {
                            $("#loanDateStyle").val("2");
                        }
                        viewSelf.changeLoanDateStyle();
                        $("#finalFloatRateField").val($("#finalRateValue").val());
                        format_functions.checkRateType("#finalIrTypeCd"); 
                    } else {
                    	utils.alert.err( "<strong>获取合同信息出错</strong>")
                    }
                }
            })
        },
        initEndOfAll: function() {},
        saveContractInfo: function() {
            if ($("#form-contractInfo").valid()) {
                $.ajax({
                    cache: true,
                    type: "POST",
                    url: $$ctx + "contractMng/saveContractInfo?contractId=" + $("#contractIdField").val(),
                    data: $("#form-contractInfo").serialize(),
                    async: true,
                    error: function(request) {
                        alert("获取账户号码出错" + request)
                    },
                    success: function(result) {
                        if (result.success) {
                        	$("#tbDzy").dataTable().fnDraw();
                        	$("#tbBzr").dataTable().fnDraw();
                        	utils.alert.suc( "<strong>"+result.msg+"</strong>")
                        } else {
                        	utils.alert.err(  "<strong>"+result.msg+"</strong>")
                        }
                    }
                })
            }
        },
        saveSuggest: function() {
            var flag = false;
            $.ajax({
                cache: true,
                type: "POST",
                url: $$ctx + "contractMng/saveSuggest?contractId=" + $("#contractIdField").val(),
                data: $("#form-suggestion").serialize(),
                async: false,
                error: function(request) {
                    alert("存储意见出错" + request)
                },
                success: function(data) {
                    if (data == "success") {
                        flag = true;
                    } else {
                    	utils.alert.err( "<strong>存储意见出错！</strong>")
                    }
                }
            });
            return flag;
        },
        initDismissBtn: function(){
        	  var viewSelf = this;
              $(document).on("click", "button[role='dismissAppr']",
              function() {
                  var _comments = $("textarea[name='opinion']").val();
                  if ($.trim(_comments) == "") {
                  	utils.alert.warn("必须输入意见!");
                      return;
                  }
                  
                  if (bootbox.confirm({
                      message: "确定要拒绝吗 ?",
                      buttons: {
                          confirm: {
                              label: "<i class='ace-icon fa fa-trash-o bigger-110'></i> 确定",
                              className: "btn-danger btn-sm"
                          },
                          cancel: {
                              label: "<i class='ace-icon fa fa-times bigger-110'></i> 取消",
                              className: "btn-sm"
                          }
                      },
                      callback: function(result) {
                      	utils.button.ban("#button[role='dismissAppr']");
                          if (result) {
                         	//提交-退回
                          	viewSelf.model.dismissAppr({
                                  workflowId: $("#workflowIdField").val(),
                                  taskId: $("#taskIdField").val(),
                                  taskTypeId: $("#taskStageCodeField").val(),
                                  comments: _comments
                              },
                              function(result) {
                                  if (result.success) {
                                  	utils.button.reset("#button[role='dismissAppr']");
                                      utils.alert.suc("撤销成功!",
                                      function() {
                                          window.location.href = $$ctx + "dashboard";
                                      });
                                  } else {
                                  	utils.alert.err("撤销失败,请稍后重试");
                                  }
                              })
                          }
                      }
                  }));
              });
        },
        submitAppr: function() {
        	utils.button.ban("#select_assigner_sure");
        	var $selectAssignerName= $("#selectAssignerName");
            $.ajax({
                cache: true,
                type: "POST",
                url: $$ctx + "contractMng/submitContract",
                data: {
                    "contractId": $("#contractIdField").val(),
                    "workflowId": $("#workflowIdField").val(),
                    "taskId": $("#taskIdField").val(),
                    "nextUser": $selectAssignerName.val(),
                    "nextUserOrgId": $selectAssignerName.find("option:selected").data("org-id"),
                    "instruction" : $("#fulfillInstructionCd").val(),
                    "comments" : $("#opinion").val()
                },
                async: false,
                error: function(request) {
                    alert("提交合同出错" + request)
                },
                success: function(data) {
                	utils.button.reset("#select_assigner_sure");
                	$("#select_assigner").modal("hide");
                    if (data.success) {
                    	utils.alert.suc( "<strong>"+ data.msg +"</strong>",function(){
                        	  window.location.href = $$ctx+"dashboard";
                        });
                      
                    }  else {
                    	utils.alert.warn(  "<strong>"+data.msg+"</strong>");
                    }
                }
            });
        },
        submitThisContract: function() {
            var viewSelf = this;
            if ($("#taskStageCodeField").val()  == "100417") {
                var factors = $("#workflowIdField").val() + "/" + $("#taskIdField").val() + "/" + $("#taskStageCodeField").val(); //是提交审核而非提交签订合同
                viewSelf.model.checkContractReady(
                  { "contractId": $("#contractIdField").val(),
                    "workflowId": $("#workflowIdField").val(),
                    "taskId": $("#taskIdField").val(),
                    "comments" : $("#opinion").val()}, function(data){
                    	if(data.success){
                    		  viewSelf.model.getNextAssigner(factors,
                    	                function(data) {
                    	                    if (data != null) {
                    	                        var sel = $("#selectAssignerName")[0];
                    	                        sel.innerHTML = "";
                    	                        for (var i = 0; i < data.length; i++) {
                    	                            var option = new Option();
                    	                            //新增人员机构
                    	                            option.innerHTML = data[i].name+'--'+data[i].orgName;
                    	                            option.value = data[i].logName;
                    	                            sel.appendChild(option);
                    	                            $(option).data("org-id",data[i].orgId);//机构id绑定data-org-id
                    	                        }
                    	                        $("#select_assigner div.modal-header h4").html("分配任务");
                    	                        $("#select_assigner").modal("show");
                    	                    }
                    	                });
                    	}else{
                    		utils.alert.warn( "<strong>"+ data.msg +"</strong>");
                    	}
                    });
              
            } else {
                var flag = viewSelf.saveSuggest();
                if (flag) {
                    viewSelf.submitAppr();
                }
            }
        },
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
        },
        downloadBtnLiveWd: function() {
            var viewSelf = this;
            $(document).on("click", "button[role='download_wd']",
            function(e) {
                var root_upload = $("#uploadPathField").val();
                var obj = null;
                var $this = $(this);
                $.ajax({
                    cache: true,
                    type: "POST",
                    url: $$ctx + "contractMng/findDocumentPath",
                    data: {
                        documentId: $this.data("id")
                    },
                    // 
                    async: false,
                    error: function(request) {
                        alert("查找下载路径出错" + request);
                    },
                    success: function(data) {
                        obj = data;
                    }
                });
                var fileName = encodeURIComponent(obj[1]);
                var route = root_upload + '/fileDownloadServlet.servlet?cmd=downloadfilejs&path=' + obj[0] + '&filename=' + fileName + "&SYS_FLAG=bxloan";
                window.location.href = route;
               // viewSelf.model.downloadFile(route);
               /* var link = document.createElement("a");
                $(link).attr("style", "display:none");
                $(link).attr("href", route);
                document.body.appendChild(link);
                link.click();
                $(link).remove();*/
                //location.href = route;
            });
        },
        deleteBtnLiveWd: function() {
            var viewSelf = this;
            $(document).on("click", "button[role='delete_wd']",
            function(e) { // 动态绑定所有删除按钮的click事件
                var $this = $(this);
                if (bootbox.confirm({
                    message: "确定要删除此条数据吗 ?",
                    buttons: {
                        confirm: {
                            label: "<i class='ace-icon fa fa-trash-o bigger-110'></i> 确定",
                            className: "btn-danger btn-sm"
                        },
                        cancel: {
                            label: "<i class='ace-icon fa fa-times bigger-110'></i> 取消",
                            className: "btn-sm"
                        }
                    },
                    callback: function(result) {
                        if (result) {
                            var id = $this.data("id");
                            $.ajax({
                                url: $$ctx + 'contractMng/delDocument/' + id,
                                dataType: 'JSON',
                                type: 'POST',
                                success: function(data) {
                                    if (data.success) {
                                       /* bootbox.alert("<strong>删除成功!</strong>",
                                        function() {});*/
                                    	var a = utils.datatable.fresh("#tbWd");
                                    	var b = utils.datatable.fresh("#tbDzy");
                                    	var c = utils.datatable.fresh("#tbBzr");
                                    }  else {
                                    	utils.alert.err(  "<strong>"+ data.msg +"</strong>");
                                    }
                                }
                            });
                        }
                    }
                }));
                return false;
            });
        },
        editBtnLiveCollateral: function() {
        	   var viewSelf = this;
               $(document).on("click", "button[role='edit_collateral']",
               function(e) {
            	   var $this = $(this);
               	viewSelf.model.getCollateralInfo(
                           {
                               projectId : $("#projectIdField").val(),
                               projectPawnInfoId : $this.data("id")
                           }, function (data)
                           {
                        	   if (data != null) {
                        		   var workType = $("#workTypeField").val();
                                   $.each($("#form-collateral").find("input[type='text'],input[type='hidden'], select, textarea"),
                                   function() {
                                       $(this).val(data[$(this).attr("name")]);
                                       if (workType != "") {
                                           $(this).attr("disabled", "disabled");
                                       }
                                   });
                                   $("input[name='actualGuaranteeRate']").val((data['actualGuaranteeRate']).toFixed(3));
                                   $("input[name='pawnRatio']").val((data['pawnRatio']*100).toFixed(3));
                                   $("#modCollateral div.modal-header h4").html("修改抵质押信息");
                                   $("#modCollateral").modal("show");
                               } else {
                            	   utils.alert.err( "<strong>获取抵质押信息出错</strong>")
                               }
                           });
               });
        },
        borrowerDetailLiveBtn: function() {
            var viewSelf = this;
            $(document).on("click", "button[role='detail_borrower']",
            function(e) {
            	var $this = $(this);
            	var partyid=$this.data("partyid");
            	if($(this).data('flag')==1){//配偶
            		viewSelf.model.getSpouseInfo(partyid,function(r_data){
            			utils.forms.putValueToForm(r_data,$("#form-familyFriend"));
            			$("#modal-formLxr").modal('show');
            		});
            		return ;
            	}else{//客户
            		var partyType= $this.data("type");//客户类型
            		var data ={customerId : partyid,
            				workCode : "TODETAIL",
            				customerSource : "detail",
            				consultLocation : "contract"
            		};
            		viewSelf.model.openCustomerWindow(partyType,data, function (result){
            			$("#modalForView div.modal-header h4").html("<i class='ace-icon fa fa-eye'></i> 客户信息");
            			$("#mainFrame").attr("src", $$ctx + result);
            			//viewSelf.initFrameHight();
            			$("#modalForView").modal("show");
            		});
            	}
            });
        },
        modifyCollateral: function(){
        	 var viewSelf = this;
        	 /** 修改实际担保债权金额校验（签订合同环节） 李冰青  */
        	 var reg =  new RegExp("^[0-9]*$");
 			 var appGuaDebtInterAmt =$("input[name='actualCreditAmount']").val();
 			 var evalValue = $("#evalValue").val();
 			 var setGuaranteeAmt = $("#setGuaranteeAmt").val();
 			 if(appGuaDebtInterAmt.match(reg) != null) {
 				if(appGuaDebtInterAmt > (evalValue - setGuaranteeAmt)) {
 					utils.alert.warn("<strong>本次申请担保债权金额不能大于可用金额！</strong>");
 					return ;
 				} else if(appGuaDebtInterAmt > parseFloat($('#contractAmt').val())) {
 					utils.alert.warn("<strong>本次申请担保债权金额不能大于申请金额！</strong>");
 					return ;
 				}else {
	 				var pawnRatio = appGuaDebtInterAmt / (evalValue - setGuaranteeAmt);
	 				$('#actualGuaranteeRate').val(parseFloat(pawnRatio).toFixed(4));
 				}
 			 }
 			/** **********end********** */
        	 if ($("#form-collateral").valid()) {
                 $.ajax({
                     cache: false,
                     type: "POST",
                     url: $$ctx + "collateral/updateCollateral?contractAmt="+$("#contractAmt").val(),
                     										/*+"&actualGuaranteeRate="+$("#actualGuaranteeRate").val(),*/
                     data: $('#form-collateral').serialize(),
                     // 
                     async: false,
                     error: function(request) {
                         alert("保存信息报错" + request);
                     },
                     success: function(data) {
                         if (data.success) {
                        	 $("#modCollateral").modal("hide");
                        	 $("#tbCollateral").dataTable().fnDraw();
                        	 $("#tbDzy").dataTable().fnDraw();
                             //bootbox.alert("<strong>保存成功！</strong>");
                         }  else {
                        	 utils.alert.warn( "<strong>"+data.msg+"</strong>");
                         }
                     }
                 }); //ajax end
             }
        },
        initDataTablesForDI: function() {
            /** DI初始化DataTables */
            var viewSelf = this;
            /**
			 * 文档管理列表
			 * */
            utils.dd.initDataDict([/*"CustomerDocType"*/"CustProjectAllDocType", "DocumentType", "CreateType"],
            function(dataDict) {
                var oTable = $("#tbWd").dataTable({
                    "sAjaxSource": $$ctx + "contractMng/findDocuments",
                    "bFilter": false,
                    "bAutoWidth": true,
                    "bLengthChange": false,
                    "aoColumns": [null, null, null, null, null, null, null, null, null],
                    "fnServerParams": function(aoData) {
                        aoData.push({
                            "name": "partyId",
                            "value": $('#partyIdField').val()
                        },
                        {
                            "name": "projectId",
                            "value": $('#projectIdField').val()
                        },
                        {
                            "name": "query_documentName",
                            "value": $('#query_documentName').val()
                        },
                        {
                            "name": "query_contentType",
                            "value": $('#query_contentType').val()
                        });
                    },
                    "aoColumnDefs": [{
                    	 "aTargets": [0],
                         mRender: function(data, type, rowdata) {
                         	var cb  = "<input type='checkbox' name='documentNums' value='"+ rowdata[8] +"' />";
                         	return cb;
                         }
                    },
                    {
                        "aTargets":  [2],
                        mRender: function(data, type, rowdata) {
                        	if(data){
                        		var msg =  dataDict.CustProjectAllDocType[data];
                        	if((msg+"").length>24){
                        		  return msg.substr(0,24)+"...";
                        	  }else{
                        		  return msg;
                        	  }
                        	}
                        	return data;
                        }
                    },
                    {
                        "aTargets":  [3],
                        mRender: function(data, type, rowdata) {
                        	 return dataDict.DocumentType[data];
                        }
                        
                    },
                    {
                        "bVisible": false,
                        "aTargets":  [4]
                    },
                    {
                        "aTargets":  [1],
                        mRender: function(data, type, rowdata) {
                            if ((data + "").length > 24) {
                                return data.substr(0, 24) + "...";
                            } else {
                                return data;
                            }
                        }
                    },
                    {
                        "aTargets":  [7],
                        mRender: function(data, type, rowdata) {
                            return dataDict.CreateType[data];
                        }
                    },
                    {
                        "aTargets":  [8],
                        "mRender": function(data, type, full) {
                            var buttons = "<button type='button' role='download_wd' data-id='" + data + "'  class='btn btn-xs btn-yellow'><i class='ace-icon fa fa-download' title='调阅文档'></i></button> ";
                            if ($("#workType").val() == ""||full[7]=="1") {
                                buttons += "<button type='button' role='delete_wd' data-id='" + data + "'  class='btn btn-xs btn-danger'><i class='ace-icon fa fa-trash-o' title='删除文档'></i></button> ";
                            }
                            return buttons;
                        }
                    }]
                });
                viewSelf.oTable = oTable;
            });
        },
        queryDocuments: function() {
            var oTable = $("#tbWd").dataTable();
            oTable.fnSettings()._iDisplayStart = 0;
            oTable.fnDraw();
        },
        submitDocument: function() {
            /**文件上传按钮*/
            $("#uploadFile").uploadify("upload", "*"); // 批量上传
        },
        addBizWd: function() {
            /**上传文档弹窗*/
            var viewSelf = this;
            var formData = utils.upload.beforeUpload("contractMng/beforeUpdate",{
                "partyId": $("#partyIdField").val(),
                "projectId": $("#projectIdField").val(),
                "subcontractId": $("#subcontractIdField").val(),
                "uploadType": "documents"
            });
            var onStart = function(file, data){
            	formData.allDocType = utils.upload.changeAllDocType($("#taskStageCodeField").val())//合同
                formData.custDocType = "55"
   	        };
            var onOneSuc = function(){
            	utils.upload.beforeUpload("contractMng/beforeUpdate",{
                    "partyId": $("#partyIdField").val(),
                    "projectId": $("#projectIdField").val(),
                    "subcontractId": $("#subcontractIdField").val(),
                    "uploadType": "documents"
                });
                $("#documentType").val("");
                var oTable = "";
                oTable = $("#tbDzy").dataTable();
                oTable.fnDraw();
                oTable = $("#tbBzr").dataTable();
                oTable.fnDraw();
                oTable = $("#tbWd").dataTable();
                oTable.fnDraw();
            };
            var onQueueEnd = function (){
            	$("#add-modal-formWd").modal("hide");
            	utils.alert.suc( "<strong>上传成功！</strong>");
                $("#uploadDocumentForm").resetForm();
            };
            utils.upload.initUploadify(formData,"#uploadPathField","#uploadFile", onStart, onOneSuc, onQueueEnd);
            
            //
            $("#subcontractIdField").val("");
            $("#uploadDocumentForm").resetForm();
            $("#documentUserName").val(formData.createUserName);
            $("#add-modal-formWd div.modal-header h4").html("<i class='ace-icon fa fa-plus'></i> 添加文档");
            $("#add-modal-formWd").modal("show");
        },
        uploadLoanApply: function() {
            var viewSelf = this;
            
            var formData = utils.upload.beforeUpload("contractMng/beforeUpdate",{
                "partyId": $("#partyIdField").val(),
                "projectId": $("#projectIdField").val(),
                "subcontractId": $("#subcontractIdField").val(),
                "uploadType": "loanApply"
            });
            var onStart = function(file, data){
            	formData.allDocType = utils.upload.changeAllDocType($("#taskStageCodeField").val())//合同
                formData.custDocType = "55"
   	        };
            var onOneSuc = function(){
            	utils.upload.beforeUpload("contractMng/beforeUpdate",{
                    "partyId": $("#partyIdField").val(),
                    "projectId": $("#projectIdField").val(),
                    "subcontractId": $("#subcontractIdField").val(),
                    "uploadType": "loanApply"
                });
                $("#documentType").val("");
                var oTable = "";
                oTable = $("#tbDzy").dataTable();
                oTable.fnDraw();
                oTable = $("#tbBzr").dataTable();
                oTable.fnDraw();
                oTable = $("#tbWd").dataTable();
                oTable.fnDraw();
            };
            var onQueueEnd = function (){
            	$("#add-modal-formWd").modal("hide");
            	utils.alert.suc( "<strong>上传成功！</strong>");
                $("#uploadDocumentForm").resetForm();
            };
            utils.upload.initUploadify(formData,"#uploadPathField","#uploadFile", onStart, onOneSuc, onQueueEnd);
            $("#subcontractIdField").val("");
            $("#uploadDocumentForm").resetForm();
            $("#documentUserName").val(formData.createUserName);
            $("#add-modal-formWd div.modal-header h4").html("<i class='ace-icon fa fa-plus'></i> 添加文档");
            $("#add-modal-formWd").modal("show");
        },
        downloadLoanApply: function() {
            var viewSelf = this;
            window.location.href = $$ctx+ "contractFile/downloadMainContract/"
            					+ $('#contractIdField').val();
        },
        openCustomerWindow: function() {
        	var partyType = $("#partyTypeField").val();
            viewSelf = this;
            viewSelf.model.openCustomerWindow(partyType,
            {
                customerId : $("#partyIdField").val(),
                workCode : "TODETAIL",
                customerSource : "detail",
                consultLocation : "contract"
            }, function (result)
            {
            	$("#mainFrame").attr("src", $$ctx + result);
            	//viewSelf.initFrameHight();
            	var $modal=$("#modalForView");
                $modal.find('span[role="modal-title"]').text('客户信息');
            	$("#modalForView").modal("show").css({}
                
                );
            }
            );
        },
        openBusinessWindow: function() {
            viewSelf = this;
            var path = "bizapply/checkApplicationWindow";
          
            viewSelf.model.openBusinessWindow(path,{
                projectId: $("#projectIdField").val(),
                consultLocation: "contract"
            },
            function(result) {
            	$("#mainFrame").attr("src", $$ctx + result);
            	//viewSelf.initFrameHight();
            	var $modal=$("#modalForView");
                $modal.find('span[role="modal-title"]').text('业务信息');
                $modal.modal("show").css({});
            });
        },
        openSuggestionWindow: function() {
            viewSelf = this;
            $("#mainFrame").attr("src", $$ctx + "approval/processSuggestion?projectId=" + $("#projectId").val() + "&workflowId=" + $("#workflowIdField").val());
            //viewSelf.initFrameHight();
            var $modal=$("#modalForView");
            $modal.find('span[role="modal-title"]').text('过程意见');
            $modal.modal("show").css({});
        },
        openLoanTrialWindow: function() {
            viewSelf = this;
            $("#mainFrame").attr("src", $$ctx +"approval/loanTrial?projectId=" + $("#projectIdField").val());
            //viewSelf.initFrameHight();
            var $modal=$("#modalForView");
            $modal.find('span[role="modal-title"]').text('贷款试算');
            $modal.modal("show").css({});
        },
        hideFileds: function() {
            $("#saveContractInfo").hide();
            $("#addBizWd").hide();
            $("#uploadContractContent").hide();
            $("#submitThisContract").hide();
            $("button[role='dismissAppr']").hide();
        },
        downloadContrFileLiveWd:function(){
        	var viewSelf = this;
          	 $(document).on( "click", "button[role='downloadCollaContr']",function(){
          		 var btnSelf=$(this);
          		 viewSelf.model.downloadCollaContr(btnSelf.data("subid"),function(r_data){
          			 viewSelf.model.downloadFile(r_data);
          		 });
          	 });
          	 $(document).on( "click", "button[role='downloadPledContrList']",function(){
          		 var btnSelf=$(this);
          		 viewSelf.model.verifyContrListDownload(btnSelf.data("subid"),function(r_data){
          			if(r_data){
   					if(r_data.success){
   						viewSelf.model.downloadPledContrList(btnSelf.data("subid"),function(r_data){
   			       			 viewSelf.model.downloadFile(r_data);
   			       		 });
   					}else{
   						utils.alert.warn( r_data.msg);
   					}
   				}else{
   					utils.alert.err( "查询失败请稍后重试");
   				}
          		 });
          	 });
            $(document).on( "click", "button[role='downloadGuarContr']",function(){
           	 var btnSelf=$(this);
           	 viewSelf.model.downloadGuarContr(btnSelf.data("subid"),function(r_data){
           		 window.location.href=r_data;
           	 });
            });
        },
        uploadBtnLiveSubContract:function(){
        	  var viewSelf = this;
              $(document).on("click", "button[role='uploadGuarContr'],button[role='upload_subcontract']",
              function(e) {
                  /**上传文档弹窗*/
                  var $this = $(this);
                  $("#uploadTypeCode").val("subcontract");
                  $("#subcontractIdField").val($this.data("subid"));
                  var formData = utils.upload.beforeUpload("contractMng/beforeUpdate",{
                      "partyId": $("#partyIdField").val(),
                      "projectId": $("#projectIdField").val(),
                      "subcontractId": $("#subcontractIdField").val(),
                      "uploadType": "subcontract"
                  });
                  var onStart = function(file, data){
                  	formData.allDocType = utils.upload.changeAllDocType($("#taskStageCodeField").val())//合同
                    formData.custDocType = "55"
         	        };
                  var onOneSuc = function(){
                      $("#documentType").val("");
                      var oTable = "";
                      oTable = $("#tbDzy").dataTable();
                      oTable.fnDraw();
                      oTable = $("#tbBzr").dataTable();
                      oTable.fnDraw();
                      oTable = $("#tbWd").dataTable();
                      oTable.fnDraw();
                  };
                  var onQueueEnd = function (){
                  	$("#add-modal-formWd").modal("hide");
                  	utils.alert.suc( "<strong>上传成功！</strong>");
                      $("#uploadDocumentForm").resetForm();
                  };
                  utils.upload.initUploadify(formData,"#uploadPathField","#uploadFile", onStart, onOneSuc, onQueueEnd);
                  $("#subcontractIdField").val("");
                  $("#uploadDocumentForm").resetForm();
                  $("#documentUserName").val(formData.createUserName);
                  $("#add-modal-formWd div.modal-header h4").html("<i class='ace-icon fa fa-plus'></i> 添加文档");
                  $("#add-modal-formWd").modal("show");
              });
        },
        changeIsUpload: function() {
            var flag = false;
            var viewSelf = this;
            var subject = "";
            var objectId = "";
             if($("#subcontractIdField").val()!= ""){
            	objectId = $("#subcontractIdField").val();
            	viewSelf.model.changeIsUpload(objectId,
            			function(data) {
            		if (data == "success") {
            			flag = true;
            		}
            	});
             }
            return flag;
        },
        initFrameHight: function(){
			$("#mainFrame").load(function() {
        	    setInterval(function(){
        	    	var clientHeight = $("#mainFrame").contents().find("body").height();
        	    	$("#mainFrame").attr("height",clientHeight+"px!important;");
        	    },100);
        	   
			});
		},
		/*changeActualGuaranteeRate:function(){

			var reg =  new RegExp("^[0-9]*$");
			var appGuaDebtInterAmt =$("input[name='actualCreditAmount']").val();
			var evalValue = $("#evalValue").val();
			var setGuaranteeAmt = $("#setGuaranteeAmt").val();
			
			if(appGuaDebtInterAmt.match(reg) != null) {
				if(appGuaDebtInterAmt > (evalValue - setGuaranteeAmt)) {
					utils.alert.warn("<strong>本次申请担保债权金额不能大于可用金额！</strong>");
					return ;
				} else if(appGuaDebtInterAmt > parseFloat($('#contractAmt').val())) {
					utils.alert.warn("<strong>本次申请担保债权金额不能大于申请金额！</strong>");
					return ;
				}else {
				var pawnRatio = appGuaDebtInterAmt / (evalValue - setGuaranteeAmt);
				$('#actualGuaranteeRate').val(parseFloat(pawnRatio).toFixed(4));
				}
			}
		},*/
		//下载邦农贷财务报表
		downloadFinancialStatementsForBnd: function(){
			var viewSelf = this;
			var projId = $("#projectIdField").val();
			//下载邦农贷财务报表之前进行信息校验
			viewSelf.model.downloadEcfiBefore(projId, function(r_data){
				if(r_data && r_data.success){
					location.href = $$ctx+'expectCashFlowInfo/downloadEcfi?projId='+projId;
				} else{
					utils.alert.err("<strong>"+r_data.msg+"</strong>");
				}
			});
		}
    });
    module.exports = view
});