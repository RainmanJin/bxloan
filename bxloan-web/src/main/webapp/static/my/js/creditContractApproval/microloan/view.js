/**微贷审批流程*/
define(function(require, exports, module) {
	var reg = /^-?[0-9]*(\.\d*)?$|^-?d^(\.\d*)?$/;
    var model = require("./model");
    var utils = require("utils");
    var rm = require("./rm");
    var view = Backbone.View.extend({
        el: "body",
        events: {
        	/** 点击右侧眼睛中客户信息 */
            "click #customerForFloatWindow": "openCustomerWindow",  
            /** 点击右侧眼睛中业务信息 */
            "click #businessForFloatWindow": "openBusinessWindow",  
            /** 点击右侧眼睛中贷款申请表 */
            "click #downloadForFloatWindow": "downloadApplyLoan", 
            /** 点击右侧眼睛中反欺诈情况 */
            /*"click #antiFakeFloatWindow": "openAntiFakeWindow",*/ 
            /**利率上浮比例修改触发利率修改*/
            "change #approveFloatRateStr" : "floatRateOnChange",  
            /**授信期限单位修改触发利率修改*/
			"change #applyTermUnitSelect" : "floatRateOnChange", 
			/**授信期限修改触发利率修改*/
			"change #applyTerm" : "floatRateOnChange",           
            /** 初始化选择下一步的任务执行者窗口 */
			"click #select_assigner_sure": "submitNextActivity" ,
			/** 点击流程图中对应环节弹出对应历史批复信息弹窗 */
			"click #timeline-2 div[class='widget-box transparent']": "openApprovalMsg",
			/** 点击提交 */
			"click button[role='submitAppr']": "submit",
			/** 点击拒绝 */
			"click button[role='dismissAppr']": "dismissBtn",
			/** 点击退回 */
			"click button[role='quitAppr']": "quitBtn",
			/** 点击制定电子合同 */
			"click button[role='contractAppr']": "makeContract"
        },
        initialize: function() {
            /** 初始化 */
            this.model = new model();
            this.render();
            this.initEvents();  	//初始化事件
            this.initDates();   	//初始化批复日期控件
        },
        render: function() {
            /** 页面渲染 */
        	this.initFirst();       //初始化
        	this.initFrameHight();  //初始化弹窗
            this.initTimeLine();    //初始化流程图和标题
            this.initCommentList(); //初始化过程意见
        },
        initFirst: function() {  //初始化
        	//格式化金额
            $.each($('#sample-form').find('.num_amt_2fixed'), function() {
	           	$(this).val(utils.number.toAmt($(this).val()));
            });
            //格式化利率
            $.each($('#sample-form').find('.num_4fixed'),function() {
            	var val = $.trim($(this).val());
            	if(val){
            		$(this).val(utils.number.toFixed(val, 4));
            	}
			});
        	var _termUnit = $(".pf input[name='termUnit']");  //期限单位隐藏域
        	var applyTermUnit = $(".pf input[name='applyTermUnitFromProduct']").val();  //产品配置的期限单位
        	var _applyTermUnitSelect = $(".pf select[name='applyTermUnitSelect']");  //期限单位
			if (applyTermUnit == "" || applyTermUnit == null) {   //若产品配置没有配置期限单位，期限单位为空，则可以自己选择期限单位
				$("#applyTermUnitSelect").attr("disabled",false);
				//默认给隐藏域赋值
				var _selUnit = _applyTermUnitSelect.find("option:selected").val();
				_termUnit.val(_selUnit);
			} else {
				_termUnit.val(applyTermUnit);
			}
			/**选择不同的期限单位，给期限单位的隐藏域赋值*/
			$(document).on("change",".pf select[name='applyTermUnitSelect']", function(e) {
				var $this = $(e.currentTarget);
				var _selUnit = $this.find("option:selected").val();
				_termUnit.val(_selUnit);
			});
			
        	var termShow = function(_contractTerm){  //显示授信期限，包括期限和单位
              	 var _array = /^([\d]{1,3})([\u4e00-\u9fa5]+)/g.exec(_contractTerm);  //匹配检索授信期限返回数组
                 if (_array && _array.length>1) { 
                   	 var _span = "<span class='input-group-addon'>"+ _array[2] +"</span>";  //期限单位
                     $("#sample-form").find("input[name='old_applyTerm']").val(_array[1]);  //期限
                     $("#sample-form").find("input[name='old_applyTerm']").parent().append(_span);
                 }
            }
        	termShow($("input[name='old_applyTerm']").val()); //显示授信期限
        	$(document).on("blur", "#approveAmt, #yearRate, #approveFloatRateStr", function(e) { //去掉在数字文本框内输入的002,010等无效0
				var value = $(this).val();
				var id = $(this).attr('id');
				if ($(this).attr('id') == 'approveAmt' && $("#approveAmt").valid()) {  //授信额度千分位显示
					$(this).val(utils.number.toAmt($(this).val()));
				} else if(value && value.match(reg) != null) {
					$(this).val(parseFloat(value));
				}
			});
        },
        initTimeLine: function() {  //初始化流程图和标题
            var stageCode = $("#taskStageCode").val();
            var pointerHtml = "<i class='ace-icon fa fa-hand-o-left grey bigger-125'></i>";
            var highLight = function(selector) {
                $(selector).addClass("purple").removeClass("green").parent().append($(pointerHtml)); //将当前流程颜色设置为紫色
                if ($.inArray($("#taskStageCode").val(),["100718"]) < 0) {  //根据流程判断是否在标题后方加入合同情况字样
                	$("#content_title").html($(selector).html());
                } else {
                	$("#content_title").html($(selector).html()+"（合同情况）");
                }
            };
            highLight("span[taskTypeId='" + stageCode + "']");  //初始化左侧流程图
        },
        openApprovalMsg:function(e) {  
        	var viewSelf = this;
        	var $btnSelf = $(e.currentTarget);
        	var taskStageCode = $btnSelf.find("span").attr("taskTypeId");
        	if ($.inArray(taskStageCode,["100713","100716","100717","100718", $("#taskStageCode").val()])>=0) { //在这些环节时不显示批复信息
        		return false;
        	} else{
        		viewSelf.model.findApprovalMsg({
        			"taskStageCode": taskStageCode,
        			"projectId": $("#projectId").val()
        		},function(data) {
        			if (data) {
        				$("#showApprovalForm").resetForm();
        				$.each($("#showApprovalForm").find("input[type='text'], select, textarea"),
        	            function() {
        					$(this).val(data[$(this).attr("name")]);
        					$(this).prop("disabled", true);
        	            });
        				$("#showApprovalForm select[name='approveIrTypeCd']").change();
        				$("#showApprovalForm input[name='approveRateValueStr']").val(data['approveRateValue'] * 100);
        				$("#showApprovalForm input[name='approveFloatRateStr']").val(data['approveFloatRate'] * 100);
        				$("#showApprovalForm select[name='applyTermUnitSelect']").val(data['termUnit']);
        				$("#showApprovalForm select[name='approveCreditType']").val(data['creditType']);
        				$("#showApprovalForm input[name='approveRateValueStr']").prop("disabled",true);
        				$("#showApprovalForm input[name='approveFloatRateStr']").prop("disabled",true);
        				//格式化金额
        	            $.each($('#showApprovalForm').find('.num_amt_2fixed'), function() {
        		           	$(this).val(utils.number.toAmt($(this).val()));
        	            });
        	            //格式化利率
        	            $.each($('#showApprovalForm').find('.num_4fixed'),function() {
        	            	$(this).val(utils.number.toFixed($(this).val(),4));
        				});
        	            $.each($('#showApprovalForm').find('.num_2fixed'),function() {
        	            	$(this).val(utils.number.toFixed($(this).val(),2));
        				});
        				$("#showApprovals div.modal-header h4").html("批复信息： <font color='red'>"+ $btnSelf.find("span").html()+"</font>");
        				$("#showApprovalForm input[name='applyTermUnitSelect']").val(data['termUnit']);
        				$("#showApprovals").modal('show');
        			}
        		});
        	}
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
    				$("#showApprovalForm select[name='applyTermUnitSelect']").val(data['termUnit']);
    				$("#showApprovalForm select[name='approveCreditType']").val(data['creditType']);
    				$("#showApprovalForm input[name='approveRateValueStr']").prop("disabled",true);
    				$("#showApprovalForm input[name='approveFloatRateStr']").prop("disabled",true);
    				//格式化金额
    	            $.each($('#showApprovalForm').find('.num_amt_2fixed'), function(){
    		           	$(this).val(utils.number.toAmt($(this).val()));
    	            });
    	            //格式化利率
    	            $.each($('#showApprovalForm').find('.num_4fixed'),function(){
    	            	$(this).val(utils.number.toFixed($(this).val(),4));
    				});
    	            $.each($('#showApprovalForm').find('.num_2fixed'),function(){
    	            	$(this).val(utils.number.toFixed($(this).val(),2));
    				});
    				$("#showApprovals div.modal-header h4").html("批复信息： <font color='red'>"+ $btnSelf.find("span").html()+"</font>");
    				$("#showApprovalForm input[name='applyTermUnitSelect']").val(data['termUnit']);
    				$("#showApprovals").modal('show');
    			}
    		});
        },
        makeContract: function() {   
        	var viewSelf = this;
            //批复验证
        	var projAppForm= $("#sample-form");
        	var valid= projAppForm.valid();
        	if (!valid) {
        		return;
        	};
            var _comments = $("textarea[role='comments_content']").val();
            if ($.trim(_comments) == "") {
            	utils.alert.warn("必须输入意见!");
                return;
            }
            //批复参数
            var params=[];
            params.push("wfCode=" + $("#wfCode").val());
            params.push("workflowId=" + $("#workflowId").val());
            params.push("taskId=" + $("#taskId").val());
            params.push("taskStageCode=" + $("#taskStageCode").val());
            params.push("comments=" + _comments);
            $('#approveAmt').val(utils.num.normal($('#approveAmt').val()));  //对金额进行特殊处理
            var formParams = projAppForm.serialize();
            var data = params.join("&") + (formParams?"&"+formParams:"");
            utils.button.ban("#button[role='contractAppr']");
            viewSelf.model.contractAppr(data, function(r){
            	 if (r.success) {
            		 utils.button.reset("#button[role='contractAppr']");
            		 utils.alert.suc("<strong>提交成功！</strong>",function() {
                         window.location.href = $$ctx + "dashboard";
                     });
            	 } else {
            		 utils.alert.err("<strong>"+r.msg+"</strong>");
            	 }
            });
        },
        quitBtn: function() {  
        	var viewSelf = this;
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
                	utils.button.ban("#button[role='quitAppr']");
                    if (result) {
                    	viewSelf.model.quitAppr({
                    		wfCode: $("#wfCode").val(),
                    		workflowId: $("#workflowId").val(),
                    		taskId: $("#taskId").val(),
                            taskStageCode: $("#taskStageCode").val(),
                            comments: _comments
                        }, function(result) {
                            if (result.success) {
                                utils.alert.suc("退回成功!",function() {
                                	utils.button.reset("#button[role='quitAppr']");
                                    window.location.href = $$ctx + "dashboard";
                                });
                            } else {
                            	utils.alert.err("退回失败,请稍后重试", function() {
                                	utils.button.reset("#button[role='quitAppr']");
                                });
                            }
                        });
                    } else{
                    	utils.button.reset("#button[role='quitAppr']");
                    }
                }
           }));
        },
        dismissBtn: function() {  
            var viewSelf = this;
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
                    	viewSelf.model.dismissAppr({
                    		wfCode: $("#wfCode").val(),
                    		workflowId: $("#workflowId").val(),
                    		taskId: $("#taskId").val(),
                            taskStageCode: $("#taskStageCode").val(),
                            comments: _comments
                        }, function(result) {
                            if (result.success) {
                                utils.alert.suc("撤销成功!", function() {
                                	utils.button.reset("#button[role='dismissAppr']");
                                    window.location.href = $$ctx + "dashboard";
                                });
                            } else {
                            	utils.alert.err("撤销失败,请稍后重试", function() {
                            		utils.button.reset("#button[role='dismissAppr']");
                                });
                            }
                        });
                    } else {
                    	utils.button.reset("#button[role='dismissAppr']");
                    }
                }
            }));
        },
        submit: function() {  //初始化按钮
            var viewSelf = this;
        	var valid= $("#sample-form").valid();
        	if (!valid) {
        		return;
        	};
            var _comments = $("textarea[role='comments_content']").val();
            if ($.trim(_comments) == "") {
            	utils.alert.warn("必须输入意见!");
                return;
            }
            if ($("button[role='uploadCommentFile']")[0]) {
            	viewSelf.model.checkUploadFiles({  //校验上传文档的数量和类型是否正确
            		"wfCode": $("#wfCode").val(),
            		"projectId" : $("#projectId").val(),
            		"taskStageCode" : $("#taskStageCode").val()
            	}, function(r) {
            		if(!r.success){
            			utils.alert.warn(r.msg);
            			return;
            		}else{
            			viewSelf.getNextAssigner();
            		}
            	});
            } else {
            	viewSelf.getNextAssigner();
            }
        },
        getNextAssigner: function(){  //获取下一环节所有人
        	var viewSelf = this;
        	var factors = $("#workflowId").val() + "/" + $("#taskId").val() + "/" + $("#taskStageCode").val() ; //是提交审核而非提交签订合同
            viewSelf.model.getNextAssigner(factors, function(data) {
	                 if (data != null) {
	                     var sel = $("#selectAssignerName")[0];
	                 	 sel.innerHTML="";
	                     for (var i = 0; i < data.length; i++) {
	                         var option = new Option();
	                         //新增人员机构
	                         option.innerHTML = data[i].name+'--'+data[i].orgName;  //以名称--机构名显示
	                         option.value = data[i].logName;
	                         sel.appendChild(option);
	                         $(option).data("org-id",data[i].orgId);//机构id绑定data-org-id
	                     }
	                     var arr = ["100712", "100718"];
	                     var showContract = $("#showContract").val();
	                     //初审环节需提交到制定电子合同环节时弹出分配任务窗口；除初审环节外，其他环节均弹出分配任务窗口
	                     if ((showContract&&showContract=="true") || ($.inArray($("#taskStageCode").val(),arr)<0)) {
	                    	 $("#select_assigner div.modal-header h4").html("分配任务");
	                         $("#select_assigner").modal("show");
	                     } else {
	                    	//禁用提交按钮
	                    	 utils.button.ban("button[role='submitAppr']");
	                         viewSelf.submitNextActivity();
	                     }
	               }
             });
        },
        submitNextActivity: function() {  //初始化选择下一步的任务执行者窗口并提交
            var viewSelf = this;
            var _comments = $("textarea[role='comments_content']").val();
            var $selectAssignerName=$("#selectAssignerName");
            $('#approveAmt').val(utils.num.normal($('#approveAmt').val()));  //对金额进行特殊处理
            //组装参数
            var params = [];
            params.push("wfCode="+$("#wfCode").val());
            params.push("workflowId="+$("#workflowId").val());
            params.push("taskId="+$("#taskId").val());
            params.push("taskStageCode="+$("#taskStageCode").val());
            params.push("comments="+_comments);
            params.push("nextUser="+$selectAssignerName.val());
            params.push("nextUserOrgId="+$selectAssignerName.find("option:selected").data("org-id"));//获取执行人机构id
            var formParams = $("#sample-form").serialize();
            var data = params.join("&")+"&"+formParams;
            //禁用选人提交按钮
            utils.button.ban("#select_assigner_sure");
            //提交一下环节
            viewSelf.model.submitApproval(data, function(result) {
	                if (result.success) {
	                	$("#select_assigner").modal("hide");
	                    var alertContent = $("#taskStageCode").val()=='100718'?"<b style='font-size:16px;'>流程结束!</b>":"提交成功!";
	                    utils.alert.suc(alertContent, function() {
	                    	utils.button.reset("button[role='submitAppr']");
	                    	utils.button.reset("#select_assigner_sure");
	                        window.location.href = $$ctx + "dashboard";
	                    });
	
	                } else {
	                	$("#select_assigner").modal("hide");
	                	utils.alert.err("<strong>" + result.msg + "</strong>", function() {
	                		utils.button.reset("button[role='submitAppr']");
	                    	utils.button.reset("#select_assigner_sure");
	                    });
	                }
            });
        },
        initCommentList:function(){  //初始化过程意见
        	var _view = this;
    		$("#wfDetailWarp").html("");
			var workflowId = $("#workflowId").val();
			_view.model.fetchCommentDetail({workflowId:workflowId},function(result) {
				if (result.success) {
					for ( var index in result.data) {
						wfDetail = result.data[index];
						var htmlContent = "";
						
						htmlContent += '<div class="timeline-item clearfix\">';
						htmlContent += '	<div class="timeline-info">';
						
						var isDone = wfDetail.taskStatus=='82';
						if (!isDone) {
							continue;
						}
						
						var _actionName = wfDetail.stageNameCn?('操作:' + wfDetail.stageNameCn):'';
						if (isDone) {
							htmlContent += '		<i class="timeline-indicator ace-icon fa fa-check-square-o btn btn-info no-hover"></i>';
						} else {
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
					
					if (result.data.length<=0) {
						$("暂无审批意见记录").appendTo($("#wfDetailWarp"));
					}
					
					$("#detail-modal").modal("show");
				} else {
					utils.alert.err("查看详细失败请稍后重试");
				}
				
			});
        },
        openCustomerWindow: function() {  //客户信息弹窗
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
        openBusinessWindow: function() {  //业务信息弹窗
            viewSelf = this;
            viewSelf.model.openBusinessWindow({  
            	workflowId: $("#workflowId").val(),
                consultLocation : "hide"       
            },
            function(result) {
                $("#mainFrameOfProjApp").attr("src", $$ctx + result);
                $("#modalOfProjApp").modal("show");
            });
        },
        openAntiFakeWindow: function() {  //反欺诈情况
            viewSelf = this;
            $("#mainFrameOfProjApp").attr("src", $$ctx + "approval/antiFraud?businessNum=" + $("#projectNo").val());
            $("#modalOfProjApp").modal("show");
        },
        floatRateOnChange : function() {   //根据授信期限，授信期限单位，利率上浮比例计算得到相应年利率
        	var applyTerm = $("#sample-form").find(':input[name="term"]').val();
			var applyTermUnit = $('#applyTermUnitSelect').val();
			var irNegoSymbCd = $('#approveFloatRateStr').val();
			var validateApplyTerm1 = /^[1-9]\d*$/.test(applyTerm);
//			var validateApplyTerm2 = ((/^[1-9]\d*$/.test(applyTerm))&&(parseInt(applyTerm)>0&&parseInt(applyTerm)<=998));
			var validateIrNegoSymbCd = /^\d+(\.\d{1,4})?$/.test(irNegoSymbCd);
//			if(!applyTerm || !applyTermUnit || !irNegoSymbCd || !validateApplyTerm1 || 
//					  !validateIrNegoSymbCd) {
			if(validateApplyTerm1 && validateIrNegoSymbCd) {
				$.ajax({
					type : 'POST',
					url : $$ctx + 'businessapplicationwd/countRateByFloatRate',
					data : {
						"applyTerm" : applyTerm,
						"applyTermUnit" : applyTermUnit,
						"floatRate" : irNegoSymbCd
					}, 
					success : function(bizRate) {
						$('#yearRate').val(bizRate.toFixed(2));
						$('#yearRate').change(); 
					}
				});
			}
		},
        downloadApplyLoan: function() {  //下载贷款申请表
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
		initFrameHight: function() {  //初始化弹窗
			$("#mainFrameOfProjApp").load(function() {
        	    setInterval(function(){
        	    	var clientHeight = $("#mainFrameOfProjApp").contents().find("body").height();
        	    	$("#mainFrameOfProjApp").attr("height",clientHeight+10+"px!important;");
        	    },100);
			});
		},
		initDates:function() { //初始化批复日期控件
			$(":text[name='approveDateStr']").datepicker({format: 'yyyy-mm-dd',todayBtn:"linked",autoclose:true}).on("click",function() {
			}).on("show",function() {
				$(".datepicker").css("z-index","99999");
			});
		},
		initEvents:function() {  //初始化事件
			$("#sample-form").validate({  //页面校验
				rules: rm.rules,
                messages: rm.messages,
                errorPlacement: function(error, element) {
                    if (element.is(":radio")) {
                    	error.appendTo(element.parent().next().next());
                    } else if (element.is(":checkbox")) {
                    	error.appendTo(element.parent().parent().parent().next());
                    } else if (element.next().is("span[class='input-group-addon']")) {
                    	error.appendTo(element.parent().parent().parent());
                    } else if (element.is(":hidden")) {
                    	error.appendTo(element.parent().parent().parent());
                    } else {
                    	error.appendTo(element.parent());
                    } 
                }
			});
			$("select[name='approveIrTypeCd']").change(function() {  //选择不同的利率类型触发不同的控件显示
				var inputSelf = $(this);
				var val = inputSelf.val();
				if (val == 1) {
					//固定利率
					$(".float_ir").find(":text").prop("disabled",true);
					$(".fixed_ir").find(":text").prop("disabled",false);
					$(".float_ir").hide();
					$(".fixed_ir").show();
				} else if(val == 2) {
					$(".float_ir").find(":text").prop("disabled",false);
//					$(".fixed_ir").hide();
					$(".float_ir").show();
				}
			});
			$("#interestRateType").change();
		}
    });
    module.exports = view;
});