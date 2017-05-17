define(function(require, exports, module) {
	var model = require("../main/model");
	var utils = require("utils");
	var view = Backbone.View.extend({
		el : "body",
		events : {
			/** 查询文档 */
			"click #btn-query": "queryDocuments",
			/** 新增文档 */
			"click #addBizWd": "addBizWd"
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
			 /** 批量下载 */
			 viewSelf.batchDownloadBtnLive();
			 /** 单一文档下载 */
			 viewSelf.downloadBtnLiveWd();
			 /** 单一文档删除 */
			 viewSelf.deleteBtnLiveWd();
		},
		initDataTablesForDI: function(dataDict) {
            var viewSelf = this;
            var oTable = $("#tbWd").dataTable({
                sAjaxSource: $$ctx + "contractMng/findDocuments",
                bFilter: false,
                bAutoWidth: true,
                bLengthChange: false,
                aoColumns: [null, null, null, null, null, null, null, null, null],
                aoColumnDefs: [{
                	 aTargets: [0],
                     mRender: function(data, type, rowdata) {
                     	var cb  = "<input type='checkbox' name='documentNums' value='"+ rowdata[8] +"' />";
                     	return cb;
                     }
                },
                {
                    aTargets:  [2],
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
                    aTargets:  [3],
                    mRender: function(data, type, rowdata) {
                    	 return dataDict.DocumentType[data];
                    }
                    
                },
                {
                    bVisible: false,
                    aTargets:  [4]
                },
                {
                    aTargets:  [1],
                    mRender: function(data, type, rowdata) {
                        if ((data + "").length > 24) {
                            return data.substr(0, 24) + "...";
                        } else {
                            return data;
                        }
                    }
                },
                {
                    aTargets:  [7],
                    mRender: function(data, type, rowdata) {
                        return dataDict.CreateType[data];
                    }
                },
                {
                    aTargets:  [8],
                    mRender: function(data, type, full) {
                        var buttons = "<button type='button' role='download_wd' data-id='" + data + "'  class='btn btn-xs btn-yellow'><i class='ace-icon fa fa-download' title='调阅文档'></i></button> ";
                        if ($("#workType").val() == ""||full[7]=="1") {
                            buttons += "<button type='button' role='delete_wd' data-id='" + data + "'  class='btn btn-xs btn-danger'><i class='ace-icon fa fa-trash-o' title='删除文档'></i></button> ";
                        }
                        return buttons;
                    }
                }],
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
        batchDownloadBtnLive: function(){
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
        deleteBtnLiveWd: function() {
            var viewSelf = this;
            $(document).on("click", "button[role='delete_wd']",
	            function(e){ 
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
	                                    	var a = utils.datatable.fresh("#tbWd");
	                                    }  else {
	                                    	utils.alert.err("<strong>"+ data.msg +"</strong>");
	                                    }
	                                }
	                            });
	                        }
	                    }
	                }));
	                return false;
            });
        }
	});
	module.exports = view;
});