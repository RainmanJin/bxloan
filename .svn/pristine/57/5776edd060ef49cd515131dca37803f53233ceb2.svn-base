define(function(require, exports, module) {
	var model = require("./model");
	var utils = require("utils");
	var view = Backbone.View.extend({
		el : "body",
		events : {
			/** 上传合同文本 */
		    "click #uploadContractContent": "uploadContractContent",
		    /** 下载合同文本 */
            "click #downloadContractContent": "downloadContractContent",
            /** 提交 */
    	    "click #submitApply": "submitApply",
    	    /** 保存意见 */
    	    "click #saveSuggest": "saveSuggest",
    	    /** 提交下一环节 */
    	    "click #submitNextActivity": "submitNextActivity",
    	    /** 拒绝 */
    	    "click #dismissAppr": "dismissAppr"
		},
		initialize : function() {
			this.model = new model();
			this.render();
		},
		render : function() {
		
		},
		uploadContractContent: function() {
            var viewSelf = this;
            //上传文档前获取相关参数
            var formData = utils.upload.beforeUpload("contractMng/beforeUpdate",{
                "partyId": $("#partyIdField").val(),
                "projectId": $("#projectIdField").val(),
                "subcontractId": $("#subcontractIdField").val(),
                "uploadType": "loanApply"
            });
            var onStart = function(file, data){
            	formData.allDocType = utils.upload.changeAllDocType($("#taskStageCodeField").val());
                formData.custDocType = "55";
   	        };
   	        //上传文档成功后执行
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
            //上传文档队列结束后执行
            var onQueueEnd = function (){
            	$("#add-modal-formWd").modal("hide");
            	utils.alert.suc( "<strong>上传成功！</strong>");
                $("#uploadDocumentForm").resetForm();
            };
            //初始化上传控件
            utils.upload.initUploadify(formData,"#uploadPathField","#uploadFile", onStart, onOneSuc, onQueueEnd);
            //弹出上传文档页面
            $("#subcontractIdField").val("");
            $("#uploadDocumentForm").resetForm();
            $("#documentUserName").val(formData.createUserName);
            $("#add-modal-formWd div.modal-header h4").html("<i class='ace-icon fa fa-plus'></i> 添加文档");
            $("#add-modal-formWd").modal("show");
        },
        downloadContractContent: function() {
            var viewSelf = this;
            window.location.href = $$ctx+ "contractFile/downloadMainCreditContract/"+ $('#creditContractIdField').val();
        },
        submitApply: function() {
            var viewSelf = this;
        	var factors = $("#workflowIdField").val() + "/" + $("#taskIdField").val() + "/" + $("#taskStageCodeField").val(); 
        	//先检查合同是否具备提交下一步的条件，若具备，查出下一环节操作人
        	viewSelf.model.checkContractReady({
        		"projectId": $("#projectIdField").val(),
        		"partyId": $("#partyIdField").val(),
        		"comments" : $("#opinion").val(),
        		"instruction" : $("#fulfillInstructionCd").val()
        	},function(data){
        		if(data.success){
        			viewSelf.model.getNextAssigner(factors,function(data) {
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
        		} else{
        			utils.alert.warn( "<strong>"+ data.msg +"</strong>");
        		}
        	});
        	//保存意见
        	/*var flag = viewSelf.saveSuggest();
        	if(flag){
        		viewSelf.submitNextActivity();
        	}*/
        },
        submitNextActivity: function() {
        	utils.button.ban("#select_assigner_sure");
        	var $selectAssignerName= $("#selectAssignerName");
            $.ajax({
                cache: true,
                type: "POST",
                url: $$ctx + "creditContractMng/submitContract",
                data: {
                    "wfCode": $("#wfCodeField").val(),
                    "workflowId": $("#workflowIdField").val(),
                    "taskId": $("#taskIdField").val(),
                    "taskStageCode": $("#taskStageCodeField").val(),
                    "creditContractId": $("#creditContractIdField").val(),
                    "nextUser": $selectAssignerName.val(),
                    "nextUserOrgId": $selectAssignerName.find("option:selected").data("org-id"),
                    "instruction" : $("#fulfillInstructionCd").val(),
                    "comments" : $("#opinion").val()
                },
                async: false,
                error: function(request) {
                	utils.alert.err( "<strong>提交下一环节出错！</strong>");
                },
                success: function(data) {
                	$("#select_assigner").modal("hide");
                    if (data.success) {
                    	utils.alert.suc( "<strong>"+ data.msg +"</strong>",function(){
                    		utils.button.reset("#select_assigner_sure");
                    		window.location.href = $$ctx+"dashboard";
                        });
                    } else {
                    	utils.alert.warn(  "<strong>"+data.msg+"</strong>",function(){
                    		utils.button.reset("#select_assigner_sure");
                        });
                    }
                }
            });
        },
        dismissAppr: function() {
        	var viewSelf=this;
        	var _comments = $("textarea[name='opinion']").val();
            if($.trim(_comments) == "") {
             	utils.alert.warn("必须输入意见!");
                return;
            }
            if(bootbox.confirm({
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
                 	 utils.button.ban("#dismissAppr");
                     if (result) {
                    	//提交-退回
                     	viewSelf.model.dismissAppr({
                     		 wfCode: $("#wfCodeField").val(),
                             workflowId: $("#workflowIdField").val(),
                             taskId: $("#taskIdField").val(),
                             taskStageCode: $("#taskStageCodeField").val(),
                             comments: _comments
                         }, function(result){
                             if(result.success) {
                                utils.alert.suc("撤销成功!", function(){
                                	utils.button.reset("#dismissAppr");
                                    window.location.href = $$ctx + "dashboard";
                                });
                             }else {
                             	utils.alert.err("撤销失败,请稍后重试",function(){
                             		utils.button.reset("#dismissAppr");
                             	});
                             }
                         });
                     } else{
                    	 utils.button.reset("#dismissAppr");
                     }
                 }
             }));
        }
	});
	module.exports = view;
});