define(function (require, exports, module)
{
	var _success = "<i class='ace-icon fa fa-check bigger-300' style='color: green;'>&nbsp</i>";
	var _failure = "<i class='ace-icon fa fa-times bigger-300' style='color: red;'>&nbsp</i>";
	var _tip = "<i class='ace-icon fa fa-exclamation bigger-300' style='color: orange;'>&nbsp</i>";
	
    var model = require("./model");
    var utils = require("utils");
    var view = Backbone.View.extend(
        {
            el : "body",
            events :
            {	
            	"click button[role='submitAppr']":"submitAppr",
            	"click button[role='cancelAppr']":"cancelAppr",
            	"click #select_assigner_sure":"sureAssigner",
                "click #customerForFloatWindow" : "openCustomerWindow",
                "click #businessForFloatWindow" : "openBusinessWindow",
                "click #downloadForFloatWindow" : "downloadApplyLoan",
                "click #loanTrialWindow" : "openLoanTrialWindow",
                "click #antiFakeFloatWindow" : "openAntiFakeWindow"
            },
            initialize : function ()
            {
                /** 初始化 */
                this.model = new model();
                this.render();
            },
            render : function ()
            {
                /** 页面渲染 */
            	this.initFirst();
            	this.initFrameHight();
                this.initTimeLine();
                this.initButtons();
                this.initCommentList();
            },
            initFirst: function() {
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
            initFrameHight: function(){
    			$("#mainFrameOfProjApp").load(function() {
    				 setInterval(function(){
    	        	    	var clientHeight = $("#mainFrameOfProjApp").contents().find("body").height();
    	        	    	$("#mainFrameOfProjApp").attr("height",clientHeight+"px!important;");
    	        	 },100);
    			});
    		},
            initTimeLine : function ()
            {
                var stageCode = $("#taskStageCode").val();
                var pointerHtml = "<i class='ace-icon fa fa-hand-o-left grey bigger-125'></i>";
                
                var highLight = function (selector)
                {
                    $(selector).addClass("purple").removeClass("green").
                    parent().append($(pointerHtml));
                    $("#content_title").html($(selector).html());
                };
                highLight("span[taskTypeId='" + stageCode + "']");
            },
            initButtons : function (){
                var viewSelf = this;
                if($("#taskStageCode").val()!='100315'&&$("#taskStageCode").val()!='100316'){
                	//初始化退回按钮
	                $(document).on("click", "button[role='quitAppr']", function (){
	                	var _comments = $("textarea[role='comments_content']").val();
	                	utils.button.ban("button[role='quitAppr']");
	                	if($.trim(_comments)==""){
	                		bootbox.alert("必须输入意见!");
		                	utils.button.reset("button[role='quitAppr']");
	                		return ;
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
	                                 className: "btn-sm"
	                             }
	                         },
	                         callback: function(result) {
	                             if (result) {
	                            	//提交-退回
	                            	 viewSelf.model.quitAppr(
	                 	                    {
	                 	                        taskStageCode : $("#taskStageCode").val(),
	                 	                        workflowId : $("#workflowId").val(),
	                 	                        taskId : $("#taskId").val(),
	                 	                        comments : _comments
	                 	                    }, function (result)
	                 	                    {
	                 	                        if (result.success)
	                 	                        {
	                 	                        	$("button[role='quitAppr']").button("reset");
	                 	                        	utils.alert.suc("退回成功!",function(){
	                 	                        		window.location.href = $$ctx + "dashboard";
	                 	                        	});
	                 	                        }
	                 	                        else
	                 	                        {
	                 	                        	utils.alert.err("退回失败,请稍后重试");
	                 	                        }
	                 	                       utils.button.reset("button[role='quitAppr']");
	                 	                    }
	                 	                    );
	                             }else{
	                            	 
	                             }
	                         }
	                     }));
	                   
	                });
                }
            },
            sureAssigner:function(){//分配下一环节确定后提交
            	var viewSelf=this;
            	utils.button.ban("#select_assigner_sure");
            	var $selectAssignerName= $("#selectAssignerName");
            	var nextUser=$selectAssignerName.val();//下一环节执行人
            	var nextUserOrgId=$selectAssignerName.find("option:selected").data("org-id");//下一环节执行人机构id
            	viewSelf.submitApprSave(nextUser,nextUserOrgId);
            },
            submitAppr:function(){//审批提交
            	var viewSelf = this;
            	var stageCode = $("#taskStageCode").val();
            	var _comments = $("textarea[role='comments_content']").val();
            	if($.trim(_comments)==""){
            		bootbox.alert("必须输入意见!");
            		return ;
            	}
            	if(stageCode=='100315'){
            		viewSelf.model.checkDocIsUpload({
            		"projectId":$("#projectId").val(),
            		"docTypeKey":"S57"//主合同扫描件
            		},function(r){
            			if(!r.success){
            				utils.alert.warn("请上传<font color='red'>主合同扫描件</font>！");
            				return;
            			}else if(r.success){
            				viewSelf.showAssignerModal();
            			}
            		});
            	}
            	if($.inArray($("#taskStageCode").val(),['100315'])<0){//选择分配人
            		utils.button.ban("#button[role='submitAppr']");
            		viewSelf.submitApprSave();
            	}
            	
            },
            showAssignerModal:function(){
            	var viewSelf = this;
            	//选择分配人
            	viewSelf.model.findNextTaskAssigners($("#taskId").val(),$("#workflowId").val(),$("#taskStageCode").val(),function(r_data){
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
            },
            submitApprSave:function(nextUser,nextUserOrgId){//审批提交（保存）
            	var viewSelf = this;
            	var data=[];
            	data.push("taskStageCode="+$("#taskStageCode").val());
            	data.push("workflowId="+$("#workflowId").val());
            	data.push("taskId="+$("#taskId").val());
            	data.push("comments="+$("textarea[role='comments_content']").val());
            	if(nextUser){
            		data.push("nextUser="+nextUser);
            	}
            	if(nextUserOrgId){
            		data.push("nextUserOrgId="+nextUserOrgId);
            	}
            	utils.button.ban("#button[role='submitAppr']");
            	viewSelf.model.submitForm(data.join('&'), function (result){
            		var alertContent = ($("#taskStageCode").val() == '100316' ? 
            				"<b style='font-size:16px;'>流程结束!</b>": "提交成功!");
					if (result.success) {
						$("#select_assigner").modal("hide");
						utils.alert.suc(alertContent, function (){
							utils.button.reset("#button[role='submitAppr']");
							utils.button.reset("#select_assigner_sure");
                            window.location.href = $$ctx + "dashboard";
                        });
					} else {
						utils.alert.err("提交失败,请稍后重试");
					}
                    });
            },
            cancelAppr:function(){//取消
            	 window.location.href = $$ctx + "dashboard";
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
						utils.alert.err("<b>查看详细失败请稍后重试</b>");
					}
					
				});
            },
            openCustomerWindow : function ()
            {	
            	var partyType = $("#partyTypeField").val();
                viewSelf = this;
                viewSelf.model.openCustomerWindow(partyType,
                {
                    customerId : $("#partyId").val(),
                    workCode : "TODETAIL",
                    customerSource : "detail",
                    consultLocation : "contract"
                }, function (result)
                {	
                    $("#mainFrameOfProjApp").attr("src",  result);
                   // viewSelf.initFrameHight();
                    $("#modalOfProjApp").modal("show");
                }
                );
            },
            openBusinessWindow : function ()
            {
                viewSelf = this;
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
            },
            openAntiFakeWindow : function ()
            {
                viewSelf = this;
                $("#mainFrameOfProjApp").attr("src", $$ctx + "approval/antiFraud?businessNum=" + $("#projectNo").val());
                //viewSelf.initFrameHight();
                $("#modalOfProjApp").modal("show").css({}
                
                );
            },
            downloadApplyLoan : function ()
            {
                viewSelf = this;
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
                ).error(function ()
                {
                	utils.alert.err("查询建议失败请稍后重试");
                }
                );
                
            },
            openLoanTrialWindow : function ()
            {
                viewSelf = this;
                $("#mainFrameOfProjApp").attr("src", $$ctx + "approval/loanTrial?projectId=" + $("#projectId").val());
                //viewSelf.initFrameHight();
                $("#modalOfProjApp").modal("show").css({}
                
                );
            }
       
            
        }
        );
    module.exports = view;
}
);
