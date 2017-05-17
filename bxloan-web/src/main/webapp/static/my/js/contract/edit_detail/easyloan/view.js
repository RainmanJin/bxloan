/**
 * 合同表单界面的view.js  易贷
 * */

define(function(require, exports, module) {
    var model = require("./model");
    var rm = require("./rm");
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
            "click #select_assigner_sure": "sureAssigner"
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
            this.downloadBtnLiveWd();
            this.deleteBtnLiveWd();
            this.initDismissBtn();
            this.initDataTablesForExpense();
            this.initDataTablesForDI(),
            this.initEndOfAll()
        },
        initFirstOfAll: function() {
        	format_functions = {
        		checkFinalRateReason: function(obj) {
        				var rate = obj.value;
        				if(rate==$("#finalFloatRateField").val()) {
        					$("#checkFinalRateField").val(0);
        				}else {
        					$("#checkFinalRateField").val(1);
        				}
        		}	
        	};
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
                	utils.alert.err( "获取合同信息出错" + request)
                },
                success: function(data) {
                    if (data != null) {
                        var workType = $("#workTypeField").val();
                        if (workType != "") {
                            viewSelf.hideFileds();
                        }
                        $.each($("#form-contractInfo").find("input[type='text'],input[type='hidden'], select, textarea"),
                        function() {
                            $(this).val(data[$(this).attr("name")]);
                            if (workType != "") {
                                $(this).attr("disabled", "disabled");
                            }
                        });
                        var termShow = function(_contractTerm){
                       	 var _array = /^([\d]{1,2})([\u4e00-\u9fa5]+)/g.exec(_contractTerm);
                            if(_array&&_array.length>1){
                            	 var _span = "<span class='input-group-addon'>"+ _array[2] +"</span>";
                                 $("#form-contractInfo").find("input[name='contractTerm']").val(_array[1]);
                                 $("#form-contractInfo").find("input[name='contractTerm']").parent().append(_span);
                            }
                       }
                       termShow(data['contractTerm']);
                        if (document.getElementById("arrangeRepayDay").value != "null") {} else {
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

                    } else {
                    	utils.alert.err("<strong>获取合同信息出错</strong>")
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
                    	utils.alert.err("获取账户号码出错" + request)
                    },
                    success: function(result) {
                        if (result.success) {
                        	utils.alert.suc( "<strong>"+result.msg+"</strong>")
                        } else {
                        	utils.alert.err( "<strong>"+result.msg+"</strong>")
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
                	utils.alert.err( "存储意见出错" + request)
                },
                success: function(data) {
                    if (data == "success") {
                        flag = true;
                    } else {
                    	utils.alert.err("<strong>存储意见出错！</strong>")
                    }
                }
            });
            return flag;
        },
        initDismissBtn: function(){
      	  var viewSelf = this;
            $(document).on("click", "button[role='dismissAppr']",
            function() {
            	utils.button.ban("button[role='dismissAppr']");
                var _comments = $("textarea[name='opinion']").val();
                if ($.trim(_comments) == "") {
                	utils.alert.warn("必须输入意见!");
                	utils.button.reset("button[role='dismissAppr']");
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
                        if (result) {
                       	//提交-退回
                        	viewSelf.model.dismissAppr({
                        		workflowId: $("#workflowIdField").val(),
                                taskId: $("#taskIdField").val(),
                                taskStageCode: $("#taskStageCodeField").val(),
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
                                	utils.button.reset("button[role='dismissAppr']");
                                	utils.alert.err("撤销失败,请稍后重试");
                                }
                            })
                        }else{
                        	utils.button.reset("button[role='dismissAppr']");
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
                    "comments": $("#opinion").val()
                },
                async: false,
                error: function(request) {
                	utils.alert.err("提交合同出错" + request)
                },
                success: function(data) {
                    $("#select_assigner").modal("hide");
                    if (data.success) {
                    	utils.alert.suc("<strong>"+ data.msg +"</strong>", function(){
                        	 window.location.href = $$ctx+"dashboard";
                        });
                    } else {
                    	utils.button.reset("#select_assigner_sure");
                    	utils.alert.warn("<strong>"+ data.msg +"</strong>");
                    }
                }
            });
        },
        submitThisContract: function() {
            var viewSelf = this;

            var flag = viewSelf.saveSuggest();
            if (flag) {
                viewSelf.model.checkContractReady({
                    "contractId": $("#contractIdField").val(),
                    "workflowId": $("#workflowIdField").val(),
                    "taskId": $("#taskIdField").val(),
                    "comments": $("#opinion").val()
                },
                function(data) {
                    if (data.success) {
                    	viewSelf.showAssignerModal();
                    	/*return;
                        viewSelf.submitAppr();*/
                    } else {
                    	utils.alert.warn( "<strong>" + data.msg + "</strong>");
                    }
                });
            }
        },
        showAssignerModal:function(){//显示下一环节任务执行者选择器
        	var viewSelf = this;
        	viewSelf.model.findNextTaskAssigners($("#taskIdField").val(),$("#workflowIdField").val(),$("#taskStageCodeField").val(),
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
        },
        sureAssigner:function(){//下一环节任务执行者选择后确定
        	var viewSelf = this;
        	viewSelf.submitAppr();
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
                    var fileNmae = encodeURIComponent(obj[1]);
                    var route = root_upload + '/fileDownloadServlet.servlet?cmd=downloadfilejs&path=' + obj[0] + '&filename=' + fileNmae + "&SYS_FLAG=bxloan";
                    location.href = route;
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
                                            /*bootbox.alert("<strong>删除成功!</strong>",
                                            function() {});*/
                                        	var a = utils.datatable.fresh("#tbWd");
                                        } else {
                                        	utils.alert.err("<strong>"+data.msg+"</strong>");
                                        }
                                    }
                                });
                            }
                        }
                    }));
                    return false;
                });
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
                            }
                            );
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
                                if ($("#workTypeField").val() == "" && full[7] == "1") {
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
            	var viewSelf = this;
                /**上传文档弹窗*/
            	var formData = utils.upload.beforeUpload("contractMng/beforeUpdate",{
                    "partyId": $("#partyIdField").val(),
                    "projectId": $("#projectIdField").val(),
                    "uploadType": "documents"
                });
	            var onStart = function(file, data){
	            	formData.allDocType = utils.upload.changeAllDocType($("#taskStageCodeField").val())//合同
                    formData.custDocType = "55"
	            };
	            var onOneSuc = function(){
	                var oTable = $("#tbWd").dataTable();
	                oTable.fnDraw();
	            };
	            var onQueueEnd = function (){
	            	$("#add-modal-formWd").modal("hide");
	            	utils.alert.suc( "<strong>上传成功！</strong>");
                    $("#uploadDocumentForm").resetForm();
	            };
	            utils.upload.initUploadify(formData,"#uploadPathField","#uploadFile", onStart, onOneSuc,onQueueEnd);
            	//
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
                    "uploadType": "loanApply"
                });
	            var onStart = function(file, data){
	            	formData.allDocType = utils.upload.changeAllDocType($("#taskStageCodeField").val())//合同
                    formData.custDocType = "55"
	            };
	            var onOneSuc = function(){
	                var oTable = $("#tbWd").dataTable();
	                oTable.fnDraw();
	            };
	            var onQueueEnd = function (){
	            	$("#add-modal-formWd").modal("hide");
	            	utils.alert.suc( "<strong>上传成功！</strong>");
                    $("#uploadDocumentForm").resetForm();
	            };
	            utils.upload.initUploadify(formData,"#uploadPathField","#uploadFile", onStart, onOneSuc, onQueueEnd);
            	//
                $("#uploadDocumentForm").resetForm();
                $("#documentUserName").val(formData.createUserName);
                $("#add-modal-formWd div.modal-header h4").html("<i class='ace-icon fa fa-plus'></i> 添加文档");
                $("#add-modal-formWd").modal("show");
            },
            downloadLoanApply: function() {
                var viewSelf = this;
                window.location.href = $$ctx+ "contractFile/downloadMainContract/"
				+ $('#contractIdField').val();
               /* window.location.href = $$ctx + "contractMng/downloadLoanApply?contractId=" + $('#contractIdField').val() + "&projectId=" + $('#projectIdField').val() + "&partyId=" + $('#partyIdField').val();
                */
            },
            openCustomerWindow: function() {
                var partyType = $("#partyTypeField").val();
                viewSelf = this;
                viewSelf.model.openCustomerWindow(partyType, {
                    customerId: $("#partyIdField").val(),
                    workCode: "TODETAIL",
                    customerSource: "detail",
                    consultLocation: "contract"
                },
                function(result) {
                    $("#mainFrame").attr("src", $$ctx + result);
                    //viewSelf.initFrameHight();
                    var $modal=$("#modalForView");
                    $modal.find('span[role="modal-title"]').text('客户信息');
                    $modal.modal("show").css({});
                });
            },
            openBusinessWindow: function() {
                viewSelf = this;
                var path =   "bizapply/checkApplicationWindow";

                viewSelf.model.openBusinessWindow(path, {
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
                $("#mainFrame").attr("src", $$ctx + "approval/loanTrial?projectId=" + $("#projectIdField").val());
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
            initFrameHight: function() {
                $("#mainFrame").load(function() {
                    setInterval(function() {
                        var clientHeight = $("#mainFrame").contents().find("body").height();
                        $("#mainFrame").attr("height", clientHeight + "px!important;");
                    },
                    100);

                });
            }
        });
        module.exports = view
    });