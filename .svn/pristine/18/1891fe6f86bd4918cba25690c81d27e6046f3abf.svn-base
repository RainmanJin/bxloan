define(function(require, exports, module) {
		var utils = require("utils");
		var model = require("./model");
		var view = Backbone.View.extend({
			el: "#main_tabbable",
			events: {
				'click button[role="payLoanPrint"]':'fnPayLoanPrint',
				'click button[role="btn_customer"]':'fnOpenCustomerInfo',
				'click button[role="btn_projectNo"]':'fnOpenBusinessInfo',
				'click a[role="a-customerNum"]':'fnOpenCustomerInfo',
				"click #btn-query": "queryDocuments",
				"click #addBizWd": "addBizWd",
			},
			initialize: function() { /** 初始化 */
				this.model=new model();
				this.render();
			},
			render:function(){
				var viewSelf=this;
				//主合同初始化
				utils.dd.initDataDict(["TermUnitCd"], function(dataDict){
					viewSelf.initMainContract(dataDict);
				});
				//从合同初始化
				viewSelf.initSubContract();
				//从合同下载初始化
				viewSelf.downloadContrFileLiveWd();
				//初始化放款列表
				utils.dd.initDataDict(["PayLoanStatus"], function(dataDict){
					viewSelf.initPayloanInfo(dataDict);
				});
				this.pdownloadBtnLive();
				this.deleteBtnLiveWd();
				this.initDataTablesForDI();
				this.initDataTablesForBorrowers();
				//初始化还款情况参数
				utils.dd.initDataDict(["ContractStatusCode"], function(dataDict){
					viewSelf.initRepayPlanParams(dataDict);
				});
				
				this.initDataTablesForRepayPlan();//还款计划初情况
				this.uploadBtnLiveSubContract();
				this.downloadBtnLiveWd();//相关文档-单一文档下载
				this.borrowerDetailLiveBtn();//共同借款人-查看客户详情
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
			hideFileds: function() {
	            $("#saveContractInfo").hide();
	            $("#addBizWd").hide();
	            $("#uploadContractContent").hide();
	            $("#submitThisContract").hide();
	            $("button[role='dismissAppr']").hide();
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
			queryDocuments: function() {
	            var oTable = $("#tbWd").dataTable();
	            oTable.fnSettings()._iDisplayStart = 0;
	            oTable.fnDraw();
	        },
			initMainContract:function(dataDict){
				var viewSelf=this;
				var params=[];
				params.push('projectId='+$("#projectId").val());
				params.push('contractId='+$("#contractId").val());
				params.push('partyId='+$("#partyId").val());
				var $form=viewSelf.$el.find('form[role="projectInfo"]');
				//担保方式禁用
				$.each($form.find(':checkbox[name="guaranteeMode"]'),function(i,v){
					$(v).prop('disabled',true);
				});
				//业务申请保险信息
				$form.find('select[name="ifInsure"]').change(function(){
					var $this=$(this);
					if($this.val()=='1'){//是
						$form.find('.ifInsure').show();
					}else{
						$form.find('.ifInsure').hide();
					}
				});
				//放款保险信息
				$form.find('select[name="loanIfInsure"]').change(function(){
					var $this=$(this);
					if($this.val()=='1'){//是
						$form.find('.loanIfInsure').show();
					}else{
						$form.find('.loanIfInsure').hide();
					}
				});
				//固定利率
				$form.find('select[name="finalIrTypeCd"]').change(function(){
					var $this=$(this);
					if($this.val()=='2'){
						$form.find('.finalIrTypeCd').show();
					}else{
						$form.find('.finalIrTypeCd').hide();
					}
				});
				viewSelf.model.findMainContract(params.join('&'),function(r_data){
					if(r_data&&r_data.success){
						var data=r_data.data;
						//贷款机构
						var applyOrgName = data.applyOrgName;
						$("input[name='applyOrgName']").val(applyOrgName);
						
						utils.forms.putValueToForm(data,$form);
						$form.find('[role="contractTermUnitTotal"]').text(dataDict.TermUnitCd[data.contractTermUnitTotal]);
						var $btn=viewSelf.$el.find('[role="btn_customer"]');
						var $btn_Busi=viewSelf.$el.find('[role="btn_projectNo"]');
						if(!data.loanIfInsure){
							$form.find('select[name="loanIfInsure"]').val('2');
						}
						if(!data.ifInsure){
							$form.find('select[name="ifInsure"]').val('2');
						}
						if(!data.isheadcol){
							$form.find('select[name="isheadcol"]').val('2');
						}
						if(!data.agricultureInd){//是否涉农
							$form.find('select[name="agricultureInd"]').val('2');
						}
						$.each($form.find('.num_2fixed'),function(){
							$(this).val(utils.number.toFixed($(this).val(),2));
						});
						$.each($form.find('.num_amt_2fixed'),function(){
							$(this).val(utils.number.toAmt($(this).val()));
						});
						$btn.text(data.customerNum);
						$btn.data('party-id',data.partyId);//客户Id
						$btn.data('party-type',data.customerType);//客户类型
						$form.find('select[name="loanIfInsure"]').change();
						$form.find('select[name="ifInsure"]').change();
						$form.find('select[name="finalIrTypeCd"]').change();
						
						$btn_Busi.text(data.projectNo);
						$btn_Busi.data('workflow-id',data.workflowId);
//						$btn_Busi.data('workflow-status',data.taskStatus);
					}
				})
			},
			//弹出审批流程窗口
	        fnOpenBusinessInfo:function(e){
	        	var $btn_Busi=$(e.currentTarget);
	        	var workflowId=$btn_Busi.data("workflow-id");//流程Id
	        	var _view = this;
	        	$("#wfDetailWarp").html("");
	        	var nids = [];//审批流程节点id
				nids.push('100311');//易贷流程评审岗
				nids.push('100312');//易贷流程审批岗
				nids.push('100413');//微贷流程分配岗
				nids.push('100414');//微贷流程一级审批岗
				nids.push('100416');//微贷流程二级审批岗
				_view.model.fetchDetail({workflowId:workflowId},function(result){
					if(result.success){
						for ( var index in result.data) {
							wfDetail = result.data[index];
							var workStatus = wfDetail.taskStatus;
							var htmlContent = "";
							var isDone = wfDetail.taskStatus=='82';//已处理完成
							
							htmlContent += '<div class="timeline-item clearfix\">';
							htmlContent += '	<div class="timeline-info">';
							
							if(isDone){
								if(index ==0 && workStatus=='82'){
									htmlContent += '		<i class="timeline-indicator ace-icon fa fa-stop btn btn-purple no-hover"></i>';
								}else{
									htmlContent += '		<i class="timeline-indicator ace-icon fa fa-check-square-o btn btn-info no-hover"></i>';
								}
							}else{
								htmlContent += '		<i class="timeline-indicator ace-icon fa fa-hand-o-right btn btn-warning no-hover"></i>';
							}
							
							htmlContent += '	</div>';
							
							if(isDone){
								if(index ==0 && workStatus=='82'){
									htmlContent += '	<div class="widget-box widget-color-purple">';
								}else{
									htmlContent += '	<div class="widget-box transparent ">';
								}
							}else{
								htmlContent += '	<div class="widget-box widget-color-orange ">';
							}
							
							htmlContent += '		<div class="widget-header widget-header-small">';
							htmlContent += '			<h5 class="widget-title smaller">';
							
							if(isDone){
								if(index ==0 && workStatus=='82'){
									htmlContent += '				<span class="">'+ wfDetail.stageNameCn +'(流程已结束)</span>';
								}else{
									htmlContent += '				<span class="">'+ wfDetail.stageNameCn +'</span>';
								}
								
							}else{
								htmlContent += '				<span class="">'+ wfDetail.stageNameCn +'(待处理)</span>';
							}
							
							htmlContent += '			</h5>';
							
							htmlContent += '			<span class="widget-toolbar no-border">';
							htmlContent += '				<i class="ace-icon fa fa-clock-o bigger-110"></i>';
							htmlContent += wfDetail.createTime ;
							htmlContent += '			</span>';
	
							htmlContent += '		</div>';
	
							htmlContent += '		<div class="widget-body">';
							htmlContent += '			<div class="widget-main" style="overflow:hidden;word-warp:break-word;word-break:break-all;">';
							if(wfDetail.actionNameCn){
								htmlContent += '操作:' + wfDetail.actionNameCn + '<br/><br/>';
							}
							if (wfDetail.taskAssigneeCn && (result.msg == '10001' || $.inArray(wfDetail.nodeId,nids) < 0)) {
								htmlContent += '经办人:' + wfDetail.taskAssigneeCn + '(' + wfDetail.taskAssignee + ')<br/><br/>';
							}
							
							htmlContent += '审批意见:' + (wfDetail.comments||'无审批意见');				
							htmlContent += '			</div>';
							htmlContent += '		</div>';
							htmlContent += '	</div>';
							htmlContent += '</div>';
							$(htmlContent).appendTo($("#wfDetailWarp"));
						}
						
						$("#detail-modal").modal("show");
					}else{
						bootbox.alert("查看详细失败请稍后重试");
					}
				});
	        },
			initSubContract:function(){//从合同
				var viewSelf=this;
				 utils.dd.initDataDict(["CommonWhether", "CertificateType"],
	        	            function(dataDict) {
					 viewSelf.initDataTablesForSubContract(dataDict);
				 });
				 utils.dd.initDataDict(["CommonWhether", "GuaranteeTypeCode"],function(dataDict){
					 viewSelf.initDataTablesForPresentCollateral(dataDict);
				 })
			},
			initDataTablesForSubContract: function(dataDict){//保证从合同
	        	 var viewSelf = this;
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
	                        "aTargets": [1],
	                        "mRender": function(data, type, full) {
	                        	var html=[];//html
	                        	html.push('<a href="javascript:void(0);" role="a-customerNum" data-party-id=');
	                        	html.push(full[9]);
	                        	html.push(' data-party-type=');
	                        	html.push(full[10]);
	                        	html.push(' >');
	                        	html.push(data);
	                        	html.push('</a>');
	                            return html.join('');
	                        }
	                    },{
   	                        "aTargets": [3],
   	                        "mRender": function(data, type, full) {
   	                            return dataDict.CertificateType[data];
   	                        }
   	                    },
   	                    {
   	                        "aTargets": [5],
   	                        "mRender": function(data, type, full) {
   	                            return utils.number.toAmt(data);
   	                        }
   	                    },{
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
	                        	return button;
	                        }
	                    }],
	                    "fnServerParams": function(aoData) {
	                        aoData.push({
	                            "name": "projectId",
	                            "value": $('#projectId').val()
	                        });
	                    }
	                });
	                viewSelf.oTable = oTable;
	        },
	        initDataTablesForPresentCollateral: function(dataDict){//抵质押从合同
	          	 var viewSelf = this;
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
                        "aTargets": [1],
                        "mRender": function(data, type, full) {
                        	/*var html=[];//html
                        	html.push('<a href="javascript:void(0);" role="a-collateral">');
                        	html.push(data);
                        	html.push('</a>');*/
                            return data;
                        }
                    },
                    {
                        "aTargets": [4],
                        "mRender": function(data, type, full) {
                            return utils.number.toAmt(data);
                        }
                    },{
                        "aTargets": [5],
                        "mRender": function(data, type, full) {
                            return utils.number.toAmt(data);
                        }
                    },{
                        "aTargets": [6],
                        "mRender": function(data, type, full) {
                            return utils.number.toAmt(data);
                        }
                    },{
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
                            button += "<button type='button' role='downloadCollaContr' data-subid='"+ full[0] +"' data-guid='"+ full[11] +"'  data-paId='" + data + "'  data-guatype='"+ full[12] +"'  class='btn btn-xs btn-yellow' title='下载抵质押从合同'><i class='ace-icon fa fa-download'></i></button> ";
                            if(arr.indexOf(full[12])<0){
                            	button+= "<button type='button' role='downloadPledContrList' data-subid='"+ full[0] +"' data-guid='"+ full[11] +"'  data-paId='" + data + "' data-guatype='"+ full[12] +"'  class='btn btn-xs btn-success' title='下载抵质押从合同清单'><i class='ace-icon fa fa-download'></i></button> " ;
                            }else{
                            	//button+= "<button type='button' role='downloadPledContrList' data-subid='"+ full[0] +"' data-guid='"+ full[11] +"'  data-paId='" + data + "' data-guatype='"+ full[12] +"'  class='btn btn-xs btn-success' title='下载抵质押从合同清单' disabled><i class='ace-icon fa fa-download'></i></button> " ;
                            }
                            return button;
                        }
                    }],
                    "fnServerParams": function(aoData) {
                        aoData.push({
                            "name": "projectId",
                            "value": $('#projectId').val()
                        });
                    }
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
	                      "aoColumns": [null, null, null, null, null, null, null, null],
	                      "fnServerParams": function(aoData) {
	                          aoData.push({
	                              "name": "partyId",
	                              "value": $('#partyId').val()
	                          },
	                          {
	                              "name": "projectId",
	                              "value": $('#projectId').val()
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
	                              return buttons;
	                          }
	                      }]
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
	         	                            var button = "<button type='button' role='detail_borrower' data-type='"+ full[10] +"' data-paId='" + data + "' data-flag='" + full[8] + "' data-partyid='" + full[9] + "' class='btn btn-xs btn-yellow' title='查看'><i class='ace-icon fa fa-eye'></i></button>";
	         	                        	return button;
	         	                        }
	         	                    }],
	         	                    "fnServerParams": function(aoData) {
	         	                        aoData.push({
	         	                            "name": "projectId",
	         	                            "value": $('#projectId').val()
	         	                        });
	         	                    }
	         	                });
	         	                viewSelf.oTable = oTable;
	         	            });
	         },
	         initRepayPlanParams: function(dataDict) {
	        	var viewSelf = this;
	        	$.ajax({
	        		type:'POST',
	        		url:$$ctx + "repayPlan/findRepayPlanParams",
	        		data:{
	        			contractId : $("#contractIdField").val()
	        		},
	        		success:function(result){
	        			if(result.data){
	        				//放款日期
	        				if(result.data[0].payLoanDate == null) {
	        					return "";
	        				} else {
	        					var formatDate = utils.date.timestampToDate(result.data[0].payLoanDate,'yyyy-MM-dd');
	        					$("#repayPlanParams :input[name='payLoanDate']").val(formatDate);
	        				}
	        				//合同期限
	        				var contractTermTotal = result.data[0].contractTermTotal;
	        				var contractTermUnitTotal = result.data[0].contractTermUnitTotal;
	        				switch(contractTermUnitTotal) {
	        					case '1' : contractTermUnitTotal = '年';break;
	        					case '2' : contractTermUnitTotal = '月';break;
	        					case '3' : contractTermUnitTotal = '天';break;
	        				}
				        	var termUnit =  contractTermTotal + contractTermUnitTotal;
	        				$("#repayPlanParams :input[name='contractTermTotal']").val(termUnit);
	        				
	        				//合同利率
	        				var contractRate = result.data[0].contractRate;
	        				$("#repayPlanParams :input[name='contractRate']").val(contractRate);
	        				
	        				//还款情况
	        				var contractStatusCd = result.data[0].contractStatusCd;
	        				var contractStatus = dataDict.ContractStatusCode[contractStatusCd];
	        				$("#repayPlanParams :input[name='payLoanStatus']").val(contractStatus);
	        				
	        			}
	        		}
	        	});
	         },
	         initDataTablesForRepayPlan: function(dataDict) {
	        	//用于前端计算“合计”
//	             var viewSelf = this;
//	             var currentPrincipalInterestTotal = 0;
//	             var currentPrincipalTotal = 0;
//	             var currentInterestTotal = 0;
//	             var repayedPrincipalTotal = 0;
//	             var repayedInterestTotal = 0;
//	             var repayedImposeInterestTotal = 0;
//	             var overdueInterestTotal = 0;
	        	 var viewSelf=this;
	            var $tbRepayLoan=$("#tbRepayLoan");
	            /** 初始化DataTables */
	             utils.dd.initDataDict(["PlanStatus"],function(dataDict) {
	                 var oTable = $tbRepayLoan.dataTable({
	                	 "bFilter": false,
	                     "bPaginate": false,//是否开启分页
	                     "bAutoWidth": true,
	                     "bLengthChange": false,
	                     "sAjaxSource": $$ctx + "repayPlan/findByConditionNotPaging",
	                     "fnServerParams": function(aoData) {
	                         aoData.push({"name": "contractId","value": $('#contractIdField').val()});
	                         aoData.push({"name": "cuserId","value": $('#cuserIdField').val()});
	                     },
	                     "aoColumns": [
	                         {mData: "repayingPlanDetailId","bVisible": false},
	                         {mData: "currentPeriod",mRender: function(data, type, rowdata) {
	                             if (rowdata.repayingPlanDetailId == null) {
	                                 return "合计：";
	                             } else {
	                                 return rowdata.currentPeriod;
	                             }
	                         }},
	                         {mData: "currentEndDate",mRender: function(data, type, rowdata) {
	                        	 if (rowdata.repayingPlanDetailId != null) {
	                                 var date = new Date(data);
	                                 //解决部分浏览器日期显示问题
	                                 return date.getFullYear()+"/"+(date.getMonth()+1)+"/"+date.getDate();
	                             } else {
	                                 return "";
	                             }
	                         }},
	                         {mData: "currentPrincipalInterest", mRender: function(data, type, rowdata) {
	                             if (rowdata.repayingPlanDetailId == null) {
	                                 return utils.number.commafy(rowdata.currentPrincipalInterest);//数据加,分隔
	                             } else {
	                                 return rowdata.currentPrincipalInterest;
	                             }
	                         }},//应还本息
	                         {mData: "currentPrincipal"},//应还本金
	                         {mData: "currentInterest"},//应还利息
	                         {mData: "repayingDate",mRender: function(data, type, rowdata) {
	                        	 if (rowdata.repayingPlanDetailId != null) {
	                                 var date = new Date(data);
	                                 return date.getFullYear()+"/"+(date.getMonth()+1)+"/"+date.getDate();
	                             } else {
	                                 return "";
	                             }
	                         }},
	                         {mData: "repayedPrincipal"},//实还本金
	                         {mData: "repayedInterest"},//实还利息
	                         {mData: "repayedImposeInterest"},//实还逾期利息
	                         {mData: "dueDay"},//本期逾期天数
	                         {mData: "sumod"},//逾期利息
	                         {mData: "contractBalance"},//合同余额
	                         {mData: "contractStatus",mRender: function(data, type, rowdata) {
	                             if (data == null) {
	                                 return "";
	                             } else {
	                                 return dataDict.PlanStatus[data];
	                             }
	                         }},
	                         {mData: null,mRender: function(data, type, rowdata) {
	                             if (data != null) {
	                                 return "";
	                             } else {
	                                 var buttons = "-";
	                                // buttons += "<button role='edit' data-id='" + rowdata.repayingPlanDetailId + "' class='btn btn-xs btn-info' title='修改'><i class='ace-icon fa fa-edit'></i></button>";
	                                 return buttons;
	                             }
	                         }},
	                     ],
	                     
	                     "fnDrawCallback" : function(){
	                     	utils.num.tableFormat(this);//对表格内的纯数字列加逗号
	                     	$tbRepayLoan.find("tbody tr:last").css({ "color": "#478fca","font-weight": "bold" });//给最后一行加style
	                     	//utils.num.colsFormat(this,[4,5,6]);//对表格内指定数字列加逗号，索引从1开始
	                     },
	                     /*fnFooterCallback: function( node , aData, iStart, iEnd, aiDisplay ) {//用于前端计算“合计”
	                    	 console.log(node);
	                    	 for(var i=0; i<aData.length; i++) {
		                 		currentPrincipalInterestTotal += aData[i].currentPrincipalInterest;
		                 		currentPrincipalTotal += aData[i].currentPrincipal;
		                 		currentInterestTotal += aData[i].currentInterest;
		                 		
		                 		repayedPrincipalTotal += aData[i].repayedPrincipal;
		        	            repayedInterestTotal += aData[i].repayedInterest;
		        	            repayedImposeInterestTotal += aData[i].repayedImposeInterest;
		        	            overdueInterestTotal += aData[i].sumod;
		                 	}
	                   }*/
	                 });
	                 viewSelf.oTable = oTable;
	             });
	         },
	         
	          initPayloanInfo:function(dataDict){
	        	  var viewSelf = this;
	               var oTable = $("#tb_payloanInfo").dataTable({
	            	   "sAjaxSource": $$ctx + "contractQuery/findPayLoanRecordSearch",
	                    "bFilter": false,
	                    "bLengthChange": false,
	                    "fnServerParams": function(aoData) {
	                        aoData.push({
	                            "name": "contractId",
	                            "value": $('#contractId').val()
	                        });
	                    },
	                    "aoColumns":[{mData: "payLoanNum"},
	                                 {mData: "loanRegistTime"},
	                                 {mData: "loanAmt",mRender: function(data, type, rowdata) {
	                                	 return utils.number.commafy(data);
                                	 }},
	                                 {mData: "freePayLoanAmtCnt"},
	                                 /*{mData: "payLoanNum"},*/
	                                 {mData: "payStatusCd",mRender: function(data, type, rowdata) {
	                                	 return dataDict.PayLoanStatus[data];
	                                	 }
	                                 },
	                                 {mData: "loanActulTime"},
	                                 {mData: "applyUserNumMask"},
	                                 {mData: "createDate"},
	                                 {mData: "applyOrgIdMask"},
	                                 {mData: "custMangerName"},
	                                 {mData: null, mRender: function(data, type, rowdata) {
	             		    	    	var operation = 
	             		    	    		"<div class='btn-group'>" +
	             		    	    			"<button title='打印借据' role='payLoanPrint' data-payloan-id='" + rowdata.payLoanId + "' class='btn btn-xs btn-purple'><i class='ace-icon fa fa-print'></i></button>" +
	             			    			"</div>";
	             		    	    	return operation;
	             		    		}}]
	                    
	               });
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
	          fnPayLoanPrint:function(e){
	        	  var $btn=$(e.currentTarget);
	        	  var payloanId=$btn.data('payloan-id');
	        	  if(payloanId){
	        		  window.open( $$ctx + 'contractList/testReport?payLoanId=' + payloanId,"_blank");
	        	  }
	          },
	          //相关文档-单一文档下载
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
	              });
	          },
	          //查看共同借款人详情
	          borrowerDetailLiveBtn: function() {
	              var viewSelf = this;
	              $(document).on("click", "button[role='detail_borrower']",
	              function(e) {
	              	var $this = $(this);
	              	var partyId=$this.data("partyid");
	            	if($(this).data('flag')==1){//配偶
	            		viewSelf.model.getSpouseInfo(partyId,function(r_data){
	            			utils.forms.putValueToForm(r_data,$("#form-familyFriend"));
	            			$("#modal-formLxr").modal('show');
	            		});
	            		return ;
	            	}else{//客户
	            		var partyType = $this.data("type")+"";
	            		viewSelf.model.openCustomerWindow(partyType,{
	            			customerId: partyId,
	            			workCode: "TODETAIL",
	            			customerSource: "detail",
	            			consultLocation: "contract"
	            		},function(result) {
	            			$("#modalForView div.modal-header h4").html("<i class='ace-icon fa fa-eye'></i> 客户信息");
	            			$("#mainFrameOfContact").attr("src", $$ctx + result);
	            			//viewSelf.initFrameHight();
	            			$("#modalOfContact").modal("show");
	            		});
	            	}
	              });
	          },
	          fnOpenCustomerInfo:function(e){
	        	  var $btn=$(e.currentTarget);
	        	  var partyType=$btn.data("party-type")+'';//客户类型
	        	  var partyId=$btn.data("party-id");//客户Id
	        	  var viewSelf = this;
	              viewSelf.model.openCustomerWindow(partyType,{
	                  customerId: partyId,
	                  workCode: "TODETAIL",
	                  customerSource: "detail",
	                  consultLocation: "contract"
	              },function(result) {
	              	$("#mainFrameOfContact").attr("src",  result);
	              	//viewSelf.initFrameHight();
	                $("#modalOfContact").modal("show");
	              });
	          }
		});
		module.exports = view;
});