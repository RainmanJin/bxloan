define(function(require, exports, module) {
	var model = require("../main/model");
	var utils = require("utils");
	var view = Backbone.View.extend({
		el : "body",
		events : {
			/** 查询文档 */
			"click #btn-query": "queryDocuments",
			/** 新增文档 */
			"click #addBizWd": "addBizWd",
			/** 删除文档 */
			"click button[role='delete_wd']": "deleteDocument",
			/** 下载单一文档 */
			"click button[role='download_wd']": "downloadDocument",
			/** 选择所有文档 */
			"click #wdcb": "checkAllDocument",
			/** 批量下载文档 */
			"click button[role='pdownloadWd']": "batchDownloadDocument"
		},
		initialize : function() {
			this.model = new model();
			this.render();
		},
		render : function() {
			 var viewSelf = this;
			 /** 初始化文档列表 */
			 utils.dd.initDataDict(["CustProjectAllDocType", "DocumentType", "CreateType"], function(dataDict) {
				 viewSelf.initDataTablesForDI(dataDict);
			 });
		},
		initDataTablesForDI: function(dataDict) {
            var viewSelf = this;
            var oTable = $("#tbWd").dataTable({
                sAjaxSource: $$ctx + "creditContractMng/searchCustomerDocumentList",
                bFilter: false,
                bAutoWidth: true,
                bLengthChange: false,
                aoColumns: [
                    {
	                    mData: "partyId",
	                	mRender: function(data, type, rowdata) {
	                		var cb  = "<input type='checkbox' name='documentNums' value='"+ rowdata.documentId +"' />";
	                     	return cb;
	                	}
	                },
	                {
	                	mData: "documentName"
	                },
	                {
	                	mData: "custDocType",
	                	mRender: function(data, type, rowdata) {
                    		var msg =  dataDict.CustProjectAllDocType[data] + "";
	                    	if (msg.length > 24){
                    		  return msg.substr(0, 24) + "...";
	                    	} else{
                    		  return msg;
	                    	}
	                	}
	                },
	                {
	                	mData: "documentType",
	                	mRender: function(data, type, rowdata) {
	                		 return dataDict.DocumentType[data];
	                	}
	                },
	                {
	                	mData: "createUserName"
	                },
	                {
	                	bVisible: false,
	                	mData: "customerNum"
	                },
	                {
	                	mData: "createDate"
	                },     
	                {
	                	mData: "createTypeCd",
	                	mRender: function(data, type, rowdata) {
	                        return dataDict.CreateType[data];
	                    }
	                },     
	                {
	                	mData: null,
	                	mRender: function(data, type, rowdata) {
							var button = [];
							var documentId = rowdata.documentId;
		                    //拼接调阅文档按钮
		                    button.push("<button type='button' role='download_wd' data-id='");
		                    button.push( + documentId + "'");
		                    button.push("class='btn btn-xs btn-yellow' title='调阅文档'>");
		                    button.push("<i class='ace-icon fa fa-download'></i>");
		                    button.push("</button>");
		                    
		                    //关联方式为创建时拼接删除文档按钮
		                    var createTypeCd = rowdata.createTypeCd;
		                    if(createTypeCd == "1"){
		                    	button.push("<button type='button' role='delete_wd' data-id='");
		                    	button.push( + documentId + "'");
		                    	button.push("class='btn btn-xs btn-danger' title='删除文档'>");
		                    	button.push("<i class='ace-icon fa fa-trash-o'></i>");
		                    	button.push("</button>");
		                    }
	                    	return button.join("");
	                	}
	                }      
                ],
                fnServerParams: function(aoData) {
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
                }
            });
            viewSelf.oTable = oTable;
		},
		queryDocuments: function() {
            var oTable = $("#tbWd").dataTable();
            oTable.fnSettings()._iDisplayStart = 0;
            oTable.fnDraw();
	    },
        addBizWd: function() {
            var viewSelf = this;
            //上传文档前获取相关参数
            var formData = utils.upload.beforeUpload("contractMng/beforeUpdate",{
                "partyId": $("#partyIdField").val(),
                "projectId": $("#projectIdField").val(),
                /*"subcontractId": $("#subcontractIdField").val(),*/
                "uploadType": "documents"
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
        deleteDocument: function(e){
        	var $btnSelf = $(e.currentTarget);
        	utils.button.confirm(null, function(result) {
				if (result) {
					var id = $btnSelf.data("id");
					$.ajax({
                        url: $$ctx + 'contractMng/delDocument/' + id,
                        dataType: 'JSON',
                        type: 'POST',
                        error: function(request) {
                        	utils.alert.err( "<strong>删除文档信息出错</strong>");
                        },
                        success: function(data) {
                            if (data.success) {
                            	$("#tbWd").dataTable().fnDraw();
                            	$("#tbBzr").dataTable().fnDraw();
                            	$("#tbDzy").dataTable().fnDraw();
                            }  else {
                            	utils.alert.err("<strong>"+ data.msg +"</strong>");
                            }
                        }
                    });
				} 
			});
        },
        downloadDocument: function(e){
        	var $btnSelf = $(e.currentTarget);
        	var root_upload = $("#uploadPathField").val();
            var obj = null;
            $.ajax({
                 cache: true,
                 type: "POST",
                 url: $$ctx + "contractMng/findDocumentPath",
                 data: {
                     documentId: $btnSelf.data("id")
                 },
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
        },
        checkAllDocument: function(e){
        	var cbs = $("#tbWd").find(":checkbox[name='documentNums']");
    		if($("#wdcb").is(":checked")){
    			cbs.prop("checked",true);
    		} else{
    			cbs.prop("checked",false);
    		}
        },
        batchDownloadDocument: function(e){
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
    		 window.location.href = route;
        }
	});
	module.exports = view;
});