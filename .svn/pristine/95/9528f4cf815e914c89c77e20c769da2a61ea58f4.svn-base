/**微贷审批流程*/
define(function(require, exports, module) {
    var model = require("./model");
    var utils = require("utils");
    var rm = require("./rm");
    var conditions = $.inArray($("#taskStageCode").val(),["100411","100412","100414","100416","100415"]);
    var view = Backbone.View.extend({
        el: "body",
        events: {
            "click #select_assigner_sure": "initApplyButton",
            "click #customerForFloatWindow": "openCustomerWindow",
            "click #businessForFloatWindow": "openBusinessWindow",
            "click #downloadForFloatWindow": "downloadApplyLoan",
            "click #loanTrialWindow": "openLoanTrialWindow",
            "click #antiFakeFloatWindow": "openAntiFakeWindow",
            "click #loanReply": "openLoanReply",
            
            "change #sample-form input[name='term']" : "floatRateOnChange",
			"change #sample-form input[name='termUnit']" : "floatRateOnChange",
			"change #sample-form input[name='approveFloatRateStr']" : "floatRateOnChange",
			//add by wangyawei 20150701 start 在审批过程中点击生成邦农贷财务报表时，下载邦农贷财务报表
			"click #bndFinancialStatements": "downloadFinancialStatementsForBnd"
			//add by wangyawei 20150701 end
        },
        initialize: function() {
            /** 初始化 */
            this.model = new model();
            this.render();
            this.initEvents();
            this.initDates();
        },
        render: function() {
            /** 页面渲染 */
        	this.initFirst();
        	this.initFrameHight();
            this.initTimeLine();
            this.initApprovalMsg();
            this.initButtons();
            this.initDismissBtn();
            this.initDrawBackBtn();
            this.initContractBtn();
            this.initCommentList();
            
            // 2014-12-15
            this.initUserDefinePlan();
        },
        initFirst: function() {
        	var _termUnit = $(".pf input[name='termUnit']");
        	var applyTermUnit = $(".pf input[name='applyTermUnitFromProduct']").val();
        	var _applyTermUnitSelect = $(".pf select[name='applyTermUnitSelect']");
        	
			if(applyTermUnit=="" || applyTermUnit==null){
				$("#applyTermUnitSelect").attr("disabled",false);
				//默认给隐藏域赋值
				var _selUnit = _applyTermUnitSelect.find("option:selected").val();
				_termUnit.val(_selUnit);
			}else{
				_termUnit.val(applyTermUnit);
			}
			
			
			$(document).on("change",".pf select[name='applyTermUnitSelect']", function(e){
				var $this = $(e.currentTarget);
				var _selUnit = $this.find("option:selected").val();
				_termUnit.val(_selUnit);
			});
        	/*if(conditions>=0){
        		 $("textarea[role='comments_content'][lang='zh']").closest(".row").hide();
        		 $(document).on("blur", "textarea[name='replyOpinion']",
        		 function() {
        			 $("textarea[role='comments_content'][lang='zh']")[0].innerHTML = $(this).val();
        		 });
        	}*/
        	var yearRate = $("#yearRate").val();
        	if(yearRate)
        		$("#yearRate").val(parseFloat(yearRate));
        	
        	var termShow = function(_contractTerm){
              	 var _array = /^([\d]{1,2})([\u4e00-\u9fa5]+)/g.exec(_contractTerm);
                   if(_array&&_array.length>1){
                   	 var _span = "<span class='input-group-addon'>"+ _array[2] +"</span>";
                        $("#sample-form").find("input[name='old_applyTerm']").val(_array[1]);
                        $("#sample-form").find("input[name='old_applyTerm']").parent().append(_span);
                   }
            }
        	termShow($("input[name='old_applyTerm']").val());
        },
        initTimeLine: function() {
            var stageCode = $("#taskStageCode").val();
            var pointerHtml = "<i class='ace-icon fa fa-hand-o-left grey bigger-125'></i>";

            var highLight = function(selector) {
                $(selector).addClass("purple").removeClass("green").parent().append($(pointerHtml));
                if($.inArray($("#taskStageCode").val(),["100418","100419","100420"])<0){
                	$("#content_title").html($(selector).html());
                }else{
                	$("#content_title").html($(selector).html()+"（合同情况）");
                }
            };
            highLight("span[taskTypeId='" + stageCode + "']");
        },
        initApprovalMsg:function() {
        	var viewSelf = this;
            $(document).on("click", "#timeline-2 div[class='widget-box transparent']",
            function() {
            	var $that = $(this);
            	var taskStageCode = $that.find("span").attr("taskTypeId");
            	if($.inArray(taskStageCode,["100413","100417","100410","100418","100419","100420", $("#taskStageCode").val()])>=0){
            		return false;
            	}else{
            		viewSelf.model.findApprovalMsg({
            			"taskStageCode": taskStageCode,
            			"projectId": $("#projectId").val()
            		},function(data){
            			if(data){
            				$("#showApprovalForm").resetForm();
            				$.each($("#showApprovalForm").find("input[type='text'], select, textarea"),
            	            function() {
            					$(this).val(data[$(this).attr("name")]);
            					$(this).prop("disabled", true);
            	            });
            				$("#showApprovalForm select[name='approveIrTypeCd']").change();
            				$("#showApprovalForm input[name='approveRateValueStr']").val(data['approveRateValue'] * 100);
            				$("#showApprovalForm input[name='approveFloatRateStr']").val(data['approveFloatRate'] * 100);
            				$("#showApprovalForm input[name='approveRateValueStr']").prop("disabled",true);
            				$("#showApprovalForm input[name='approveFloatRateStr']").prop("disabled",true);
            				$("#showApprovals div.modal-header h4").html("批复信息： <font color='red'>"+ $that.find("span").html()+"</font>");
            				var approveRepayingMode = $("#showApprovalForm select[name='approveRepayingMode']").val();
            				if(approveRepayingMode == '3') {// 自定义还款计划
            					$("#showApprovalForm [name='id']").val(data['id']);
            					$("#showApprovalForm [sign='repaymentPlanTable']").dataTable().fnDraw();
            					$("#showApprovalForm [sign='repaymentPlanDiv']").show();
            				}
            				//new add gph 201505271459 反显批复期限
            				$("#showApprovalForm input[name='applyTermUnitSelect']").val(data['termUnit']);
            				$("#showApprovals").modal('show');
            			}
            		});
            	}
            });
        },
        initApplyButton: function() {
            var viewSelf = this;
            var _comments = $("textarea[role='comments_content']").val();
           /* {
                taskStageCode: $("#taskStageCode").val(),
                workflowId: $("#workflowId").val(),
                taskId: $("#taskId").val(),
                comments: _comments,
                nextUser: $("#selectAssignerName").val()
            }*/
            var params=[];
            params.push("taskStageCode="+$("#taskStageCode").val());
            params.push("workflowId="+$("#workflowId").val());
            params.push("taskId="+$("#taskId").val());
            params.push("comments="+_comments);
            var $selectAssignerName=$("#selectAssignerName");
            params.push("nextUser="+$selectAssignerName.val());
            params.push("nextUserOrgId="+$selectAssignerName.find("option:selected").data("org-id"));//获取执行人机构id
            var formParams=$("#sample-form").serialize();
            var data=params.join("&")+"&"+formParams;
//            utils.button.ban("#button[role='submitAppr']");
            utils.button.ban("#select_assigner_sure");
            viewSelf.model.submitForm(data,
            function(result) {
            	utils.button.reset("button[role='submitAppr']");
            	utils.button.reset("#select_assigner_sure");
                if (result.success) {
                    $("#select_assigner").modal("hide");
                    var alertContent = $("#taskStageCode").val()=='100420'?"<b style='font-size:16px;'>流程结束!</b>":"提交成功!";
                    
                    utils.alert.suc(alertContent,
                    function() {
                        window.location.href = $$ctx + "dashboard";
                    });

                } else {
                	$("#select_assigner").modal("hide");
                	utils.alert.err(result.msg);
                }
            });
        },
        initContractBtn: function() {//签订合同
        	var viewSelf = this;
            $(document).on("click", "button[role='contractAppr']",
            function() {
                //批复验证
            	var projAppForm= $("#sample-form");
            	var valid= projAppForm.valid();
            	if(!valid){
            		return;
            	};
                var _comments = $("textarea[role='comments_content']").val();
                if ($.trim(_comments) == "") {
                	utils.alert.warn("必须输入意见!");
                    return;
                }
                /*{
            	    taskTypeId: $("#taskStageCode").val(),
                    workflowId: $("#workflowId").val(),
                    taskId: $("#taskId").val(),
                    comments: _comments
                }*/
                //批复参数
                var params=[];
                params.push("taskTypeId="+$("#taskStageCode").val());
                params.push("workflowId="+$("#workflowId").val());
                params.push("taskId="+$("#taskId").val());
                params.push("comments="+_comments);
                var formParams=projAppForm.serialize();
                var data=params.join("&")+(formParams?"&"+formParams:"");
                utils.button.ban("#button[role='contractAppr']");
                viewSelf.model.contractAppr(data, function(r){
                	 if(r.success){
                		 utils.button.reset("#button[role='contractAppr']");
                		 utils.alert.suc("<strong>提交成功！</strong>",function() {
                             window.location.href = $$ctx + "dashboard";
                         });
                	 }else{
                		 utils.alert.err("<strong>"+r.msg+"</strong>");
                	 }
                });
            });
        },
        initDrawBackBtn: function() {
        	var viewSelf = this;
            if ($("#taskStageCode").val() != '100415') {
                //初始化退回按钮
                $(document).on("click", "button[role='quitAppr']",
                function() {
                    var _comments = $("textarea[role='comments_content']").val();
                    if ($.trim(_comments) == "") {
                    	utils.alert.warn("必须输入意见!");
                        return;
                    }
                    if (bootbox.confirm({
                        message: "确定要退回吗 ?",
                        buttons: {
                            confirm: {
                                label: "<i class='ace-icon fa fa-trash-o bigger-110'></i> 确定",
                                className: "btn-danger btn-sm"
                            },
                            cancel: {
                                label: "<i class='ace-icon fa fa-times bigger-110'></i> 取消",
                                className: "btn btn-warning btn-sm"
                            }
                        },
                        callback: function(result) {
                            if (result) {
                           	//提交-退回
                            	utils.button.ban("#button[role='quitAppr']");
                            	viewSelf.model.quitAppr({
                                    taskStageCode: $("#taskStageCode").val(),
                                    workflowId: $("#workflowId").val(),
                                    taskId: $("#taskId").val(),
                                    comments: _comments
                                },
                                function(result) {
                                    if (result.success) {
                                    	utils.button.reset("#button[role='quitAppr']");
                                        utils.alert.suc("退回成功!",
                                        function() {
                                            window.location.href = $$ctx + "dashboard";
                                        });
                                    } else {
                                    	utils.alert.err("退回失败,请稍后重试");
                                    }
                                });
                            }
                        }
                    }));
                    
                });
            }
        },
        initDismissBtn: function() {
            var viewSelf = this;
            $(document).on("click", "button[role='dismissAppr']",
            function() {
                var _comments = $("textarea[role='comments_content']").val();
                if ($.trim(_comments) == "") {
                	utils.alert.warn("必须输入意见!");
                    return;
                }
                
                if (bootbox.confirm({
                    message: "确定要撤销吗 ?",
                    buttons: {
                        confirm: {
                            label: "<i class='ace-icon fa fa-trash-o bigger-110'></i> 确定",
                            className: "btn-danger btn-sm"
                        },
                        cancel: {
                            label: "<i class='ace-icon fa fa-times bigger-110'></i> 取消",
                            className: "btn btn-warning btn-sm"
                        }
                    },
                    callback: function(result) {
                    	utils.button.ban("#button[role='dismissAppr']");
                        if (result) {
                       	//提交-退回
                        	viewSelf.model.dismissAppr({
                                workflowId: $("#workflowId").val(),
                                taskId: $("#taskId").val(),
                                taskTypeId: $("#taskStageCode").val(),
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
        initButtons: function() {
            var viewSelf = this;
            //初始化提交按钮
            $(document).on("click", "button[role='submitAppr']",
            function(e) {
            	
            	var valid= $("#sample-form").valid();
            	if(!valid){
            		return;
            	};
                var _comments = $("textarea[role='comments_content']").val();
                if ($.trim(_comments) == "") {
                	utils.alert.warn("必须输入意见!");
                    return;
                }
                
                var _approveRepayingMode = $("#sample-form select[name = 'approveRepayingMode']").val();
                if (_approveRepayingMode == '3') {
                	var _approveAmt = $("input[name = 'approveAmt']").val();
                    var $tb = $("#tableDiv table[sign = 'repaymentPlanTable']");
                    var _total = 0 ; 
                    $.each($tb.find("tr:gt(0)"), function(i,e){
                    	_total += parseFloat($(e).find("td:eq(1)").text());
                    });

                    var flag = parseFloat(_approveAmt) == _total;
                    if(!flag){
                    	utils.alert.warn("批复金额与总额还款计划总额不相等！");
                    	return false;
                    }
                }
                
                if($("button[role='uploadCommentFile']")[0]){
                	viewSelf.model.checkUploadFiles({
                		"projectId" : $("#projectId").val(),
                		"taskStageCode" : $("#taskStageCode").val()
                	}, function(r){
                		if(!r.success){
                			utils.alert.warn(r.msg);
                			return;
                		}else{
                			viewSelf.getNextAssigner();
                		}
                	});
                }else{
                	viewSelf.getNextAssigner();
                }
                
               

            });

        },
        getNextAssigner: function(){
        	var viewSelf = this;
        	var factors = $("#workflowId").val() + "/" + $("#taskId").val() + "/" + $("#taskStageCode").val() ; //是提交审核而非提交签订合同
             viewSelf.model.getNextAssigner(factors,
             function(data) {
             
                 if (data != null) {
                     var sel = $("#selectAssignerName")[0];
                 	sel.innerHTML="";
                     for (var i = 0; i < data.length; i++) {
                         var option = new Option();
                         //新增人员机构
                         option.innerHTML = data[i].name+'--'+data[i].orgName;
                         option.value = data[i].logName;
                         sel.appendChild(option);
                         $(option).data("org-id",data[i].orgId);//机构id绑定data-org-id
                     }

                     var arr = [/*"100416",*/"100412","100420"];
                     var showContract = $("#showContract").val();
                     if((showContract&&showContract=="true") || ($.inArray($("#taskStageCode").val(),arr)<0)){
                    	 $("#select_assigner div.modal-header h4").html("分配任务");
                         $("#select_assigner").modal("show");
                     }else {
                    	 utils.button.ban("button[role='submitAppr']");
                         viewSelf.initApplyButton();
                     }
                 }
             });
        },
        initCommentList:function(){
        	var _view = this;
    		$("#wfDetailWarp").html("");
			var workflowId = $("#workflowId").val();
			
			_view.model.fetchCommentDetail({workflowId:workflowId},function(result){
				
				if(result.success){
					
					for ( var index in result.data) {
						wfDetail = result.data[index];
						var htmlContent = "";
						
						htmlContent += '<div class="timeline-item clearfix\">';
						htmlContent += '	<div class="timeline-info">';
						
						var isDone = wfDetail.taskStatus=='82';
						if(!isDone){
							continue;
						}
						
						var _actionName = wfDetail.stageNameCn?('操作:' + wfDetail.stageNameCn):'';
						if(isDone){
							htmlContent += '		<i class="timeline-indicator ace-icon fa fa-check-square-o btn btn-info no-hover"></i>';
						}else{
							htmlContent += '		<i class="timeline-indicator ace-icon fa fa-hand-o-right btn btn-warning no-hover"></i>';
						}
						htmlContent += '	</div>';

						htmlContent += '	<div class="widget-box '+(isDone?'transparent':'widget-color-orange')+'">';
						htmlContent += '		<div class="widget-header widget-header-small">';
						htmlContent += '			<h5 class="widget-title smaller">';
						htmlContent += '				<span class=""><b>'+ wfDetail.stageNameCn +(isDone?"":"(待处理)") + '</b>&nbsp;&nbsp;&nbsp;' +_actionName +'</span>';
						htmlContent += '			</h5>';

						htmlContent += '			<span class="widget-toolbar no-border">';
						htmlContent += '				<i class="ace-icon fa fa-clock-o bigger-110"></i>';
						htmlContent += wfDetail.createTime ;
						htmlContent += '			</span>';

						htmlContent += '		</div>';

						htmlContent += '		<div class="widget-body">';
						htmlContent += '			<div class="widget-main" style="overflow:hidden;word-warp:break-word;word-break:break-all;" >';
						
//						if(wfDetail.assigneerName){
//							htmlContent += '经办人:' + wfDetail.taskAssigneeCn + '<br/><br/>';
//						}
						htmlContent += '审批意见:' + (wfDetail.comments||'无审批意见');				
						htmlContent += '			</div>';
						htmlContent += '		</div>';
						htmlContent += '	</div>';
						htmlContent += '</div>';
						$(htmlContent).appendTo($("#wfDetailWarp"));
					}
					
					if(result.data.length<=0){
						$("暂无审批意见记录").appendTo($("#wfDetailWarp"));
					}
					
					$("#detail-modal").modal("show");
				}else{
					utils.alert.err("查看详细失败请稍后重试");
				}
				
			});
        },
        openCustomerWindow: function() {
            viewSelf = this;
            var partyType = $("#partyType").val();
            viewSelf.model.openCustomerWindow(partyType,{
                customerId: $("#partyId").val(),
                workCode: "TODETAIL",
                customerSource: "detail",
                consultLocation: "contract"
            },
            function(result) {
            	$("#mainFrameOfProjApp").attr("src",  result);
            	viewSelf.initFrameHight();
                $("#modalOfProjApp").modal("show");
            });
        },
        openBusinessWindow: function() {
            viewSelf = this;
            viewSelf.model.openBusinessWindow({
                projectId: $("#projectId").val(),
                consultLocation: "contract"
            },
            function(result) {
                $("#mainFrameOfProjApp").attr("src", $$ctx + result);
              //  viewSelf.initFrameHight();
                $("#modalOfProjApp").modal("show");
            });
        },
        openAntiFakeWindow: function() {
            viewSelf = this;
            $("#mainFrameOfProjApp").attr("src", $$ctx + "approval/antiFraud?businessNum=" + $("#projectNo").val());
           // viewSelf.initFrameHight();
            $("#modalOfProjApp").modal("show");
        },
        downloadApplyLoan: function() {
            viewSelf = this;
            var obj = null;
            var root_upload = $("#upload_path").val();
            $.post($$ctx + "approval/findDocuments", {
                projectId: $("#projectId").val(),
                documentType: "06"
            }).success(function(result) {
                if (result != null && result != "") {
                    var route = root_upload + '/fileDownloadServlet.servlet?cmd=downloadfilejs&path=' + result[0] + '&filename=' + result[1] + "&SYS_FLAG=bxloan";
                    location.href = route;
                } else {
                	utils.alert.warn("<strong>该业务没有上传过贷款申请表</strong>")
                }
            }).error(function() {
            	utils.alert.err("查询建议失败请稍后重试");
            });

        },
        openLoanTrialWindow: function() {
            viewSelf = this;
            $("#mainFrameOfProjApp").attr("src", $$ctx + "approval/loanTrial?projectId=" + $("#projectId").val());
           // viewSelf.initFrameHight();
            $("#modalOfProjApp").modal("show");
        },
        queryDocuments: function() {
            var oTable = $("#tbWd").dataTable();
            oTable.fnDraw();
        },
        initFrameHight: function(){
			$("#mainFrameOfProjApp").load(function() {
        	    setInterval(function(){
        	    	var clientHeight = $("#mainFrameOfProjApp").contents().find("body").height();
        	    	$("#mainFrameOfProjApp").attr("height",clientHeight+10+"px!important;");
        	    },100);
        	   
			});
		},
		initDates:function(){
			$(":text[name='approveDateStr']").datepicker({format: 'yyyy-mm-dd',todayBtn:"linked",autoclose:true}).on("click",function(){
			}).on("show",function(){
				$(".datepicker").css("z-index","99999");
			});
		},
		//2014-10-29 add
		initEvents:function(){
			$("#sample-form").validate({
				rules: rm.rules,
                messages: rm.messages,
                errorPlacement: function(error, element) {
                    if (element.is(":radio")){error.appendTo(element.parent().next().next());}
                    else if (element.is(":checkbox")){error.appendTo(element.parent().parent().parent().next());}
                    else if (element.next().is("span[class='input-group-addon']")) {
                    	error.appendTo(element.parent().parent().parent());
                    }
                    else if (element.is(":hidden")) {
                    	error.appendTo(element.parent().parent().parent());
                    }
                    else {
                    	error.appendTo(element.parent());
                    } 
                }
			});
			$("select[name='approveIrTypeCd']").change(function(){
				var inputSelf=$(this);
				var val=inputSelf.val();
				if(val==1){
					//固定利率
					$(".float_ir").find(":text").prop("disabled",true);
					$(".fixed_ir").find(":text").prop("disabled",false);
					$(".float_ir").hide();
					$(".fixed_ir").show();
				}else if(val==2){
					//浮动利率
					//$(".fixed_ir").find(":text").prop("disabled",true);
					$(".float_ir").find(":text").prop("disabled",false);
					$(".fixed_ir").hide();
					$(".float_ir").show();
				}
			});
			//
			$("#interestRateType").change();
		},
		initUserDefinePlan : function() {
			$("#sample-form [sign='repaymentPlanTable']").dataTable({
				sAjaxSource : $$ctx + "wdapprovalRepayPlan/searchRepaymentPlanList",
				bFilter : false,
				bLengthChange : false,
				iDisplayLength : 120,//修改分页大小为120：为临时解决批复金额与还款总额不相等的问题
				//bScrollInfinite:true,
				aoColumnDefs : [ {
					aTargets : [ 2 ],
					mRender : function(data, type, full) {
						return "<div class='btn-group'style='width:100px;'><button title='修改' type='button' role='editRepayPlan' data-id='" + data + "' class='btn btn-xs btn-info'><i class='ace-icon fa fa-edit'></i></button>"+
							" <button title='删除' type='button' role='deleteRepayPlan' data-id='" + data + "' class='btn btn-xs btn-danger'><i class='ace-icon fa fa-trash-o'></i></button></div>";
					}
				} ],
				fnServerParams : function(aoData) {
					aoData.push({
						name : "projectNo",
						value : $('#projectNo').val()
					});
				},
				fnInfoCallback: function(oSettings, iStart, iEnd, iMax, iTotal, sPre) {
					if(iTotal <= 0 ){//没有计划还款日列表则初始化按钮显示
						$('#batchInitRepayPlan').removeAttr('style');
					}else{
						$('#batchInitRepayPlan').css('display','none');
					}
				    return '显示第'+iStart +" - "+ iEnd+'，共'+iTotal+'条';
				} 
			});
			
			$("#showApprovalForm [sign='repaymentPlanTable']").dataTable({
				sAjaxSource : $$ctx + "wdapprovalRepayPlan/searchRepaymentPlanList",
				bFilter : false,
				bLengthChange : false,
				iDisplayLength : 15,
				aoColumnDefs : [ {
					aTargets : [ 2 ],
					mRender : function(data, type, full) {
						return "";
					}
				} ],
				fnServerParams : function(aoData) {
					aoData.push({
						name : "approvalId",
						value : $("#showApprovalForm [name='id']").val()
					});
				}
			});
			
			var approveRepayingMode = $("#sample-form select[name='approveRepayingMode']").val();
			if(approveRepayingMode == '3') {
				$('#startDate').val('');
				$('#monthDate').val('');
				$('#repayDateForCount').val('');
				var _sampleForm = $("#sample-form [sign='repaymentPlanTable'] tbody tr");
				setTimeout(function() {
					if(_sampleForm > 0 && _sampleForm.html().indexOf('没有符合条件的记录') <= 0)
						$("#batchInitRepayPlan").attr({
							"disabled" : "disabled"
						});
				}, 100);
				$("#sample-form [sign='repaymentPlanDiv']").show();
				$("#editableRepayPlan1").show();
				$("#editableRepayPlan2").show();
			} else {
				$("#sample-form [sign='repaymentPlanDiv']").hide();
				$("#editableRepayPlan1").hide();
				$("#editableRepayPlan2").hide();
			}
			
			$(document).on("change", "select[name='approveRepayingMode']", function() {
        		if ($(this).val() == 3) {// 自定义还款计划
        			$("#sample-form [sign='repaymentPlanDiv']").slideDown();
        			$("#editableRepayPlan1").show();
    				$("#editableRepayPlan2").show();
        		} else {
        			$("#sample-form [sign='repaymentPlanDiv']").slideUp();
        			$("#editableRepayPlan1").hide();
    				$("#editableRepayPlan2").hide();
        		}
        	});
			
			$(document).on("click", "button[id='addRepayPlan']", function(e) {
				$('#approvalHistoryRepayPlanId').val('');
				$('#repayPlanForm').resetForm();
				$('#repayPlanModal').modal('show');
			});
			
			$(document).on("click", "button[id='batchInitRepayPlan']", function(e) {
				var reg = /^-?[0-9]*(\.\d*)?$|^-?d^(\.\d*)?$/;
				var integerReg =  /^\d+$/;
				
				var startDate = $('#startDate').val();
				var monthDate = $('#monthDate').val();
				var repayDateForCount = $('#repayDateForCount').val();
				
				var applyAmt = $("#sample-form input[name='approveAmt']").val();
				var applyTerm = $("#sample-form input[name='term']").val();
				var applyTermUnit = $("#sample-form input[name='termUnit']").val();
				
				if(!startDate)
					utils.alert.warn("<strong>请选择开始日期！</strong>");
				else if(!monthDate)
					utils.alert.warn("<strong>请输入间隔月数！</strong>");
				else if(!repayDateForCount)
					utils.alert.warn("<strong>请输入每月还款日！</strong>");
				else if(monthDate.match(integerReg) == null)
					utils.alert.warn("<strong>间隔月数不合法！</strong>");
				else if(repayDateForCount.match(integerReg) == null)
					utils.alert.warn("<strong>每月还款日不合法！</strong>");
				else if(repayDateForCount <= 0 || repayDateForCount >= 31)
					utils.alert.warn("<strong>每月还款日不合法！</strong>");
				else if(!applyAmt)
					utils.alert.warn("<strong>请输入批复金额！</strong>");
				else if(applyAmt.match(reg) == null)
					utils.alert.warn("<strong>批复金额不合法！</strong>");
				else if(!applyTerm)
					utils.alert.warn("<strong>请输入批复期限！</strong>");
				else if(applyTerm.match(integerReg) == null)
					utils.alert.warn("<strong>批复期限不合法！</strong>");
				else if(!applyTermUnit)
					utils.alert.warn("<strong>请选择期限单位！</strong>");
				else {
					$("#batchInitRepayPlan").attr({
						"disabled" : "disabled"
					});
					$.ajax({
						type : 'post',
						url : $$ctx + 'wdapprovalRepayPlan/batchInitRepayPlan',
						data : {
							'projectId' : $('#projectId').val(),
							'applyAmt' : applyAmt,
							'applyTerm' : applyTerm,
							'applyTermUnit' : applyTermUnit,
							'startDate' : startDate,
							'monthDate' : monthDate,
							'repayDateForCount' : repayDateForCount
						},
						success : function(r) {
							if(r.success) {
								utils.alert.suc("<strong>" + r.msg + "</strong>");
								$("#sample-form [sign='repaymentPlanTable']").dataTable().fnDraw();
							} else {
								utils.alert.err("<strong>" + r.msg + "</strong>");
								$("#batchInitRepayPlan").removeAttr("disabled");
							}
						}
					});
				}
			});
			
			$(document).on("click", "button[role='editRepayPlan']", function(e) {
				$.ajax({
					type : 'post',
					url : $$ctx + 'wdapprovalRepayPlan/getRepayPlan',
					data : {
						'id' : $(this).data("id")
					},
					success : function(repayPlan) {
						$.each($("#repayPlanForm").find("input"), function() {
							$(this).val(repayPlan[$(this).attr("name")]);
						});
						$('#repayPlanModal').modal('show');
					}
				});
			});
			
			$(document).on("click", "button[role='deleteRepayPlan']", function(e) {
				var id = $(this).data("id");
				bootbox.confirm({
					message: "您确定要删除此记录吗？",
					buttons: {
						confirm: {
						label: "<i class='ace-icon fa fa-trash-o bigger-110'></i> 确定",
							className: "btn-danger btn-sm"
						},
						cancel : {
							label: "<i class='ace-icon fa fa-times bigger-110'></i> 取消",
							className: "btn btn-warning btn-sm"
						}
					},
					callback: function(result) {
						if (result) {
							$.ajax({
								type : 'post',
								url : $$ctx + 'wdapprovalRepayPlan/deleteRepayPlan',
								data : {
									'id' : id
								},
								success : function(r) {
									if(r.success) {
										utils.alert.suc("<strong>" + r.msg + "</strong>");
										var table = $("#sample-form [sign='repaymentPlanTable']").dataTable();
										table.fnSettings()._iDisplayStart = 0;
										table.fnDraw();
										setTimeout(function() {
											if($("#sample-form [sign='repaymentPlanTable'] tbody tr").html().indexOf('没有符合条件的记录') > 0)
												$("#batchInitRepayPlan").removeAttr('disabled');
										}, 100);
									} else {
										utils.alert.err("<strong>" + r.msg + "</strong>");
									}
								}
							});
						}
					}
				});
			});
			
			/**
			 * 初始化还款计划-开始日期,计划还款时间
			 */
			// 将datepicker控件放在最顶层
			var dateOnTop = function() {
				$(".datepicker").css("z-index", "99999");
			};
			var defaultDateConf = {
				autoclose : true,
				todayHighlight : true,
				clearBtn : true
			};
			var initDateP = function(selector, changeCallBack, config) {
				var _dateConf = config || defaultDateConf;
				$(selector).datepicker(_dateConf).on("changeDate",
						changeCallBack).on("click", dateOnTop);
			};
			initDateP("#repayDate");
			initDateP("#startDate");
			
			$('#repayPlanForm').validate({
				submitHandler : function(form) {
					$("#sureRepayPlanForm").attr({
						"disabled" : "disabled"
					});
					$.ajax({
						type : 'post',
						url : $$ctx + 'wdapprovalRepayPlan/saveRepayPlan',
						data : $(form).serialize(),
						success : function(result) {
							$("#sureRepayPlanForm").removeAttr("disabled");
							if (result.success) {
								utils.alert.suc("<strong>" + result.msg + "</strong>");
								$('#repayPlanModal').modal('hide');
								var table = $("#sample-form [sign='repaymentPlanTable']").dataTable();
								table.fnSettings()._iDisplayStart = 0;
								table.fnDraw();
								setTimeout(function() {
									if($("#sample-form [sign='repaymentPlanTable'] tbody tr").html().indexOf('没有符合条件的记录') <= 0)
										$("#batchInitRepayPlan").attr({
											"disabled" : "disabled"
										});
								}, 100);
							} else {
								utils.alert.err("<strong>" + result.msg + "</strong>");
							}
						}
					});
				}
			});
		},
		floatRateOnChange : function() {
			if($("select[name='approveIrTypeCd']").val() == "1"){
				return false;
			}
			var applyTerm = $("#sample-form input[name='term']").val();
			var applyTermUnit = $("#sample-form select[name='termUnit']").val();
			var irNegoSymbCd = $("#sample-form input[name='approveFloatRateStr']").val();
			
			var validateApplyTerm1 = /^[1-9]\d*$/.test(applyTerm);
			var validateApplyTerm2 = ((/^[1-9]\d*$/.test(applyTerm))&&(parseInt(applyTerm)>0&&parseInt(applyTerm)<=998));
			var validateIrNegoSymbCd = /^\d+(\.\d{1,4})?$/.test(irNegoSymbCd);
			if(!applyTerm || !applyTermUnit || !irNegoSymbCd || !validateApplyTerm1 || 
					!validateApplyTerm2 || !validateIrNegoSymbCd) {
			} else {
				$.ajax({
					type : 'POST',
					url : $$ctx + 'businessapplicationwd/countRateByFloatRate',
					data : {
						"applyTerm" : applyTerm,
						"applyTermUnit" : applyTermUnit,
						"floatRate" : irNegoSymbCd
					},
					success : function(bizRate) {
						$("#sample-form input[name='approveRateValueStr']").val(bizRate);
					}
				});
			}
		},
		//下载邦农贷财务报表
		downloadFinancialStatementsForBnd: function(){
			var viewSelf = this;
			var projId = $("#projectId").val();
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
    module.exports = view;
});