
define(function (require, exports, module){
	var model = require("./model");
	var rm = require("./rm");
	 var utils = require("utils");
	 var view = Backbone.View.extend({
            el : "body",
            events :{
            	"click button[role='submitProjAppl']":"submitProjAppl",
            	"click button[role='quitProjAppl']":"quitProjAppl",
            	"click button[role='cancelProjAppl']":"cancelProjAppl",
            	"click #telNetInfoTableShow" :"showInfoTableModal",
            	"click #select_assigner_sure" : "submitProjApplSave"
            },
            initialize : function (){
                /** 初始化 */
            	this.model=new model();
                this.render();
            },
            render : function (){
                /** 页面渲染 */
                this.initTimeLine();
                this.initForm();
               
                this.initDismissBtn();
                this.initFloatNavBar();
                this.initCommentList();
                this.initFrameHight();
            },
            initFrameHight: function(){
    			$("#mainFrameOfProjApp").load(function() {
            	    setInterval(function(){
            	    	var clientHeight = $("#mainFrameOfProjApp").contents().find("body").height();
            	    	$("#mainFrameOfProjApp").attr("height",clientHeight+"px!important;");
            	    },100);
    			});
    		},
            showInfoTableModal : function(){
            	$("#apply_info_tables_modal").modal("show");
            },
            initTimeLine : function (){
                var stageCode = $("#taskStageCode").val();
                var pointerHtml = "<i class='ace-icon fa fa-hand-o-left grey bigger-125'></i>";
                
                var highLight = function (selector){
                    $(selector).addClass("purple").removeClass("green").
                    parent().append($(pointerHtml));
                    $("#content_title").html($(selector).html());
                };
                highLight("span[taskTypeId='" + stageCode + "']");
            },
            initForm:function(){
            	$("#form-projAppli").validate({
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
            },
            submitProjAppl:function(){
            	var viewSelf=this;
            	//验证
            	var projAppliForm=$("#form-projAppli");
            	if(!projAppliForm.valid()){
    				return;
    			}
            	viewSelf.model.checkUploadFile($("#projectId").val(),"S52",function(r_data){
            		if (r_data.success) {
            			//选择分配人
            			viewSelf.model.findNextTaskAssigners(
            					projAppliForm.find(":hidden[name='taskId']").val(),
            					projAppliForm.find(":hidden[name='workflowId']").val(),
            					projAppliForm.find(":hidden[name='taskStageCode']").val(),
            					function(r_data){
            				if(r_data){
            					var sel = $("#selectAssignerName")[0];
            					sel.innerHTML="";
            					for (var i = 0; i < r_data.length; i++) {
            						var option = new Option();
            						//新增人员机构
                                    option.innerHTML = r_data[i].name+'--'+r_data[i].orgName;
                                    option.value = r_data[i].logName;
                                    sel.appendChild(option);
                                    $(option).data("org-id",r_data[i].orgId);//机构id绑定data-org-id
            					}
            					$("#select_assigner div.modal-header h4").html("分配任务");
            					$("#select_assigner").modal("show");
            				}
            			});
					} else {
						//bootbox.alert("电核、网核审查意见表，未上传，请确认！");
						utils.alert.warn("未上传<font color='red'>审批意见表</font>，请确认！");
					}
            	});
            },
            submitProjApplSave:function(){
            	var viewSelf=this;
            	var $form=$("#form-projAppli");
            	var $selectAssignerName=$("#selectAssignerName");
            	$form.find(":hidden[name='nextUser']").val($selectAssignerName.val());
            	$form.find(":hidden[name='nextUserOrgId']").val($selectAssignerName.find("option:selected").data("org-id"));
            	//提交
            	utils.button.ban("#select_assigner_sure");
            	viewSelf.model.submitAppr($form.serialize(),function(r_data){
            		if (r_data.success) {
            			$("#select_assigner").modal("hide");
						utils.alert.suc("提交成功!", function() {
							window.location.href = $$ctx + "dashboard";
						});
					} else {
						utils.button.reset("#select_assigner_sure");
						$("#select_assigner").modal("hide");
						utils.alert.err(r_data.msg);
					}
            	});
            },
            initDismissBtn: function(){
            	var viewSelf = this;
                $(document).on("click", "button[role='dismissAppr']",
                function() {
                    var _comments = $("textarea[name='comments']").val();
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
                                className: "btn-sm"
                            }
                        },
                        callback: function(result) {
                        	utils.button.ban("#button[role='dismissAppr']");
                            if (result) {
                           	//提交-退回
                            	viewSelf.model.dismissAppr({
                                    workflowId: $("#workflowId").val(),
                                    taskId: $("#taskId").val(),
                                    taskStageCode: $("#taskStageCode").val(),
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
            quitProjAppl:function(){
            	var viewSelf = this;
            	//验证
            	var projAppliForm=$("#form-projAppli");
            	var projAppliFormValid=projAppliForm.validate();
            	var r_valid=projAppliFormValid.element("textarea[name='comments']");
            	if(!r_valid){
            		return ;
            	}
            	utils.button.ban("#button[role='quitAppr']");
            	
            	 if (bootbox.confirm({
                     message: "确定要退回吗 ?",
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
                         	var data={
             					taskStageCode : $("#taskStageCode").val(),
             					workflowId : $("#workflowId").val(),
             					taskId : $("#taskId").val(),
             					comments : projAppliForm.find("textarea[name='comments']").val()
             				}
                         	
             				viewSelf.model.quitAppr(data, function(result) {
             						if (result.success) {
             							utils.alert.suc("退回成功!", function() {
             								window.location.href = $$ctx + "dashboard";
             							});
             						} else {
             							utils.alert.err("退回失败,请稍后重试");
             						}
             						utils.button.reset("#button[role='quitAppr']");
             				});
                         }
                     }
                 }));
            	
            	
            },
            cancelProjAppl:function(){
            	 window.location.href = $$ctx + "dashboard";
            },
            initFloatNavBar:function(){//浮动导航
            	var viewSelf = this;
            	//客户
            	$(document).on("click", "#customerForFloatWindow", function (){
            		var partyType = $("#partyTypeField").val();
                    viewSelf.model.openCustomerWindow(partyType,
                    {
                        customerId : $("#partyId").val(),
                        workCode : "TODETAIL",
                        customerSource : "detail",
                        consultLocation : "contract"
                    }, function (result)
                    {	
                        $("#mainFrameOfProjApp").attr("src", $$ctx + result);
                       // viewSelf.initFrameHight();
                        $("#modalOfProjApp").modal("show");
                    });
                });
            	//业务申请
            	$(document).on("click", "#businessForFloatWindow", function (){
                     var productType = $("#productTypeCd").val();
                     viewSelf.model.openBusinessWindow(productType,
                     {
                         projectId : $("#projectId").val(),
                         consultLocation : "contract"
                     }, function (result)
                     {
                         $("#mainFrameOfProjApp").attr("src", $$ctx + result);
                         //viewSelf.initFrameHight();
                         $("#modalOfProjApp").modal("show").css({}
                         
                         );
                     }
                     );
            	});
            	
            	//贷款申请表
            	$(document).on("click", "#downloadForFloatWindow", function (){
                     var obj = null;
                     var root_upload = $("#upload_path").val();
                     
                     $.post($$ctx + "approval/findDocuments",
                     {
                         projectId : $("#projectId").val(),
                         documentType : "06"
                     }
                     ).success(function (result)
                     {
                         if (result != null && result != "")
                         {
                             var route = root_upload + '/fileDownloadServlet.servlet?cmd=downloadfilejs&path=' + result[0] + '&filename=' + result[1] + "&SYS_FLAG=bxloan";
                             location.href = route;
                         }
                         else
                         {
                        	 utils.alert.warn("<strong>该业务没有上传过贷款申请表</strong>")
                         }
                     }
                     ).error(function (){
                    	 utils.alert.err("查询建议失败请稍后重试");
                     });
            	});
            	//贷款试算
            	$(document).on("click", "#loanTrialWindow", function (){
            		$("#mainFrameOfProjApp").attr("src", $$ctx + "approval/loanTrial?projectId=" + $("#projectId").val());
                    $("#modalOfProjApp").modal("show");
            	});
            	//反欺诈情况
            	$(document).on("click", "#antiFakeFloatWindow", function (){
            		 $("#mainFrameOfProjApp").attr("src", $$ctx + "approval/antiFraud?businessNum=" + $("#projectNo").val());
                     $("#modalOfProjApp").modal("show");
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
							
//							if(wfDetail.assigneerName){
//								htmlContent += '经办人:' + wfDetail.taskAssigneeCn + '<br/><br/>';
//							}
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
            }
        });
	 module.exports = view;
});