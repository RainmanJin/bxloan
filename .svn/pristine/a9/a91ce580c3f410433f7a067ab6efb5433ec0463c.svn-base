define(function(require, exports, module) {
	var model = require("../main/model");
	var rm = require("../main/rm");
	var utils = require("utils");
	var wdBizUrl = "businessapplicationwd";
	var view = Backbone.View.extend({
		el : "body",
		events : {
			/** 文档上传有关 */
			"click #addWd": "addBizWd",
			"click #btn-query": "queryDocuments",
			"click #add-Wd-submit": "submitDocument",
			"click #uploadBizApply": "uploadLoanApply"
		},
		initialize : function() {
			this.model = new model();
			this.jumpSource=$("#jumpSource").val();//跳转来源
			this.render();
		},
		render : function() {
			/** 文档上传有关 */
			this.initDataTablesForDI();
		    this.downloadBtnLiveWd();
		    this.pdownloadBtnLive();
		    this.initCustDocTable();
            this.deleteBtnLiveWd();
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
		initDataTablesForDI: function() {
			  /** DI初始化DataTables */
            var viewSelf = this;
            //add by HWL 20150814 start 区分已办和其他，已办时，加载审批中的文档
            var docsListTbURL=$$ctx + (viewSelf.jumpSource&&viewSelf.jumpSource=='taskDone'?"contractMng/findDocuments":"businessapplicationwd/findDocuments");
            //add by HWL 20150814 end 
            /**
			 * 文档管理列表
			 * */
            utils.dd.initDataDict([/*"CustomerDocType"*/"CustProjectAllDocType", "DocumentType", "CreateType"],
            function(dataDict) {
                var oTable = $("#tbWd").dataTable({
                    //"sAjaxSource": $$ctx + "businessapplicationwd/findDocuments",
                	"sAjaxSource": docsListTbURL,
                    "bFilter": false,
                    "bAutoWidth": true,
                    "bLengthChange": false,
                    "aoColumns": [null, null, null, null, null, null, null, null, null],
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
                       	 	"value": $("#query_contentType").val()
                        },
                        {
                       	 	"name": "jumpSource",
                       	 	"value": viewSelf.jumpSource
                        });
                    },
                    "aoColumnDefs": [
                    {
                    	 "aTargets": [0],
                         mRender: function(data, type, rowdata) {
                         	var cb  = "<input type='checkbox' name='documentNums' value='"+ rowdata[8] +"' />";
                         	return cb;
                         }
                    },
                    {
                        "bVisible": false,
                        "aTargets": [4]
                    },
                    {
                        "aTargets": [1],
                        mRender: function(data, type, rowdata) {
                      	  if((data+"").length>24){
                      		  return data.substr(0,24)+"...";
                      	  }else{
                      		  return data;
                      	  }
                            
                        }
                    },
                    {
                        "aTargets": [2],
                        mRender: function(data, type, rowdata) {
                        	if(!data){
                        		return '';
                        	}
                        	var msg = dataDict.CustProjectAllDocType[data];
                       	 	if((msg+"").length>24){
                        		  return msg.substr(0,24)+"...";
                        	  }else{
                        		  return msg;
                        	  }
                        }
                    },
                    {
                        "aTargets": [3],
                        mRender: function(data, type, rowdata) {
                        	if(!data){
                        		return '';
                        	}
                            return dataDict.DocumentType[data];
                        }
                    },
                    {
                        "aTargets": [7],
                        mRender: function(data, type, rowdata) {
                        	if(!data){
                        		return '';
                        	}
                            return dataDict.CreateType[data];
                        }
                    },
                    {
                        "aTargets": [8],
                        "mRender": function(data, type, full) {
                        		var buttons = "<button type='button' role='download_wd' data-id='" + data + "'  class='btn btn-xs btn-yellow' title='调阅文档'><i class='ace-icon fa fa-download'></i></button> ";
                        		if($('#type').val() != 'check')
                        			buttons += "<button type='button' role='delete_wd' data-id='" + data + "'  class='btn btn-xs btn-danger' title='删除文档'><i class='ace-icon fa fa-trash-o'></i></button> ";
                                return buttons;

                        }
                    }],
                    "fnDrawCallback": function (oSettings) {
                    	 utils.upload.refreshSelectorTable(wdBizUrl+"/findDocumentCustDocTypes", {
         					"partyId" : $("#partyId").val(),
         					"projectId" : $("#projectId").val()
         				});
                    }
                });
                viewSelf.oTable = oTable;
            });
		},
		initCustDocTable: function(){
        	var viewSelf = this;
        	viewSelf.model.findCustDocTypes({
        		"partyId" : $("#partyId").val()
        	},function(r){
        		if(r!=null&&r.custMap){
        			var map = r.custMap;
        			var $tbody = $("#uploadTbody");
        			var count = 0;
        			var total = 0;
        			var html= "<tr>";
        			for (var key in map) {
        				count++;
        				total++;
        				var str = "<td>" +
        						   "<input type='radio' name='uploadDoc' value='"+ key +"' />" +
        				           "</td>"+
        				           "<td>" ;
        				     if(key=="44"||key=="45"){
        				    	 str+= "<font color='red'>*</font>"
        				     }
        				      str += map[key]+
        				           "</td>"+
        				           "<td><span name='custSpan_"+ key +"'" +">" +
        				           "</span></td>";
        				 html+=str;
        				if(count==3||r.count==total){
        					html+="</tr>";
        					$tbody.html($tbody.html()+html);
        					count=0; html="<tr>";
        				}
        	        }  
        			
        			var viewSelf = this;
                    $(document).on("click", "input[name='uploadDoc'][type='radio']", function(e) {
                    	 var $this = $(this);
                    	$("#documentType").val($this.val());
                    });
                    
                    $(document).on("click", "button[name='closeUpload']", function(e) {
                   	 var $this = $(this);
                   	$("#documentType").val("");
                   });	
        			
        		}
        	});
        },
		downloadBtnLiveWd: function() {
        	var viewSelf = this;
            $(document).on("click", "button[role='download_wd']", function(e) {
            	 var root_upload = $("#uploadPathField").val();
            	 var obj = null;
            	 var $this = $(this);
            	 $.ajax({
                     cache: true,
                     type: "POST",
                     url: "businessapplicationwd/findDocumentPath",
                     data:{ documentId: $this.data("id") },
                     // 
                     async: false,
                     error: function(request) {
                         alert("查找下载路径出错" + request);
                     },
                     success: function(data) {
                    	 obj = data;
                     }
                     });
            	 var fileNmae=encodeURIComponent(obj[1]);
            	 var route = root_upload+'/fileDownloadServlet.servlet?cmd=downloadfilejs&path='+ obj[0] + '&filename=' + fileNmae +"&SYS_FLAG=bxloan";
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
                            className: "btn-warning btn-sm"
                        }
                    },
                    callback: function(result) {
                        if (result) {
                            var id = $this.data("id");
                            $.ajax({
                                url: $$ctx + 'businessapplicationwd/delDocument/' + id,
                                dataType: 'HTML',
                                type: 'POST',
                                success: function(data) {
                                    if (data == "success") {
                                    	var a = utils.datatable.fresh("#tbWd");
                                    } else if (data == "createTypeCdEquals2Error") {
                                        utils.alert.err("<strong>该文档关联方式为引用，不允许删除，请联系管理员来操作!</strong>");
                                    } else {
                                        utils.alert.err("<strong>删除失败!</strong>");
                                    }
                                }
                            });
                        }
                    }
                }));
                return false;
            });
		},
		queryDocuments: function() {
			var oTable = $("#tbWd").dataTable();
			oTable.fnDraw();
		},
		submitDocument: function() {
            /**文件上传按钮*/
			if($("#documentType").val()!=""){
	            $("#uploadFile").uploadify("upload", "*"); // 批量上传
				}else{
					utils.alert.warn("<strong>请选择上传文件的种类！</strong>");
				}
        },
        addBizWd: function() {
			 var viewSelf = this;
	            /**上传文档弹窗*/
	            var formData = utils.upload.beforeUpload("businessapplicationwd/beforeUpdate",{
                    "partyId": $("#partyId").val(),
                    "projectId": $("#projectId").val(),
                    "uploadType": "documents"
                });
	            var onStart = function(file, data){
//	            	data.custDocType = $("input[name='uploadDoc'][type='radio']:checked").val();
	            	data.custDocType = $("#tb_doc_selector input[name='uploadDoc']:checked").val();
	            	data.allDocType = $("#allDocTypeField").val();//微贷业务
//	            	data.documentType = utils.upload.changeDocumentType(data.custDocType, data.allDocType);
	            	data.documentNum = $("#documentNumDI").val();
	   	        };
	            var onOneSuc = function(){
	            	utils.upload.beforeUpload("businessapplicationwd/beforeUpdate",{
	                    "partyId": $("#partyId").val(),
	                    "projectId": $("#projectId").val(),
	                    "uploadType": $("#uploadTypeCode").val()
	                });
	                $("#documentType").val("");
	                utils.upload.refreshSelectorTable(wdBizUrl+"/findDocumentCustDocTypes", {
						"partyId" : $("#partyId").val(),
						"projectId" : $("#projectId").val()
					});
	                var oTable = $("#tbWd").dataTable();
	                oTable.fnDraw();
	            };
	            utils.upload.initUploadify(formData,"#uploadPathField","#uploadFile", onStart, onOneSuc);
	            utils.upload.refreshSelectorTable(wdBizUrl+"/findDocumentCustDocTypes", {
					"partyId" : $("#partyId").val(),
					"projectId" : $("#projectId").val()
				});
           $("#uploadDocumentForm").resetForm();
           $("#documentNameDisplay").val(formData.createUserName);
           $("#add-modal-formWd div.modal-header h4").html("<i class='ace-icon fa fa-plus'></i> 添加文档");
           $("#add-modal-formWd").modal("show");
		},
        /*
        uploadLoanApply: function() {
			var viewSelf = this;
			$("#uploadTypeCode").val("loanApply");
			viewSelf.beforeUpload();
			viewSelf.initUploadify(viewSelf.initFormData());
			$("#uploadDocumentForm").resetForm();
			$("#documentUserName").val($("#userNameDI").val());
			$("#add-modal-formWd div.modal-header h4").html("<i class='ace-icon fa fa-plus'></i> 添加文档");
			$("#add-modal-formWd").modal("show");
        },*/
	});
	module.exports = view;
});