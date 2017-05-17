define(function(require, exports, module) {
	var model = require("./model");
	var utils = require("utils");
	var view = Backbone.View.extend({
		el : "body",
		events : {
			"click #btn-query" : "queryDocuments",
			"click #addWd" : "addWd",
			"click #add-Wd-submit" : "submitDocument"
		},
		initialize : function() {
			this.model = new model();
			this.render();
		},
		render : function() {
			 this.initDocumentsDatatables();
			 this.pdownloadBtnLive();
			 this.initCustDocTable();
			 this.downloadBtnLiveWd();
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
	        		var root_upload = $("#uploadPath").val();
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
		queryDocuments : function (){
            var oTable = $("#tbWd").dataTable();
            oTable.fnDraw();
        },
		initDocumentsDatatables : function ()
        {		
            viewSelf = this;
            utils.dd.initDataDict([/*"CustomerDocType"*/ "CustProjectAllDocType", "DocumentType", "CreateType"],
                function (dataDict)
            {
                var oTable = $("#tbWd").dataTable(
                    {
                        "sAjaxSource" : $$ctx + "collateral/findDocuments",
                        "bFilter" : false,
                        "bAutoWidth" : true,
                        "bLengthChange" : false,
                        "aoColumns" : [null, null, null, null, null, null, null, null, null],
                        "fnServerParams" : function (aoData)
                        {
                            aoData.push(
                            {
                                "name" : "guarantyNum",
                                "value" : $('#guarantyNum').val()
                            },
                            {
                                "name" : "query_documentName",
                                "value" : $('#query_documentName').val()
                            },
                            {
                                "name" : "query_contentType",
                                "value" : $('#query_contentType').val()
                            }
                            );
                        },
                        "aoColumnDefs" : [
                            {
                                "aTargets": [0],
                                mRender: function(data, type, rowdata) {
                                	var cb  = "<input type='checkbox' name='documentNums' value='"+ rowdata[7] +"' />";
                                	return cb;
                                }
                            },
                            {
                                "aTargets": [2],
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
                                "aTargets" : [6],
                                mRender : function (data, type, rowdata)
                                {
                                    return dataDict.CreateType[data];
                                }
                            },
                            {
                                "aTargets" : [7],
                                "mRender" : function (data, type, full)
                                {
                                    var buttons = "<button type='button' role='download_wd' data-id='" + data + "'  class='btn btn-xs btn-yellow' title='调阅文档'><i class='ace-icon fa fa-download'></i></button> ";
                                    if(full[6]=='1'){
                                    	buttons += "<button type='button' role='delete_wd' data-id='" + data + "'  class='btn btn-xs btn-danger'><i class='ace-icon fa fa-trash-o' title='删除文档'></i></button> ";
                                    }
                                    return buttons;
                                    
                                }
                            },
                            {	
                            	"bVisible":false,
                                "aTargets":  [8]
                            }
                        ],
                        "fnDrawCallback" : function(){
                        	//utils.num.colsFormat(this,[4,5,6]);//对表格内指定数字列加逗号，索引从1开始
                        	$(this).find("tr,th").attr("style","white-space:nowrap;");
                        	$(this).wrap("<div style='overflow-x: auto;overflow-y: hidden;'></div>")
                        }
                    }
                    );
                viewSelf.oTable = oTable;
            }
            );
        },
        initCustDocTable: function(){
        	var viewSelf = this;
        	var guId = $("#guarantyId").val()
        	if(guId){
        		viewSelf.model.findCustDocTypes({
        			"guId" : $("#guarantyId").val()
        		},function(r){
        			if(r!=null){
        				utils.upload.createThead(r);  
        				
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
        	}
        },
        addWd: function(){//TODO
        	var viewSelf = this;
        	var guId = $("#guarantyId").val();
        	if(guId){
        		
        		var formData = utils.upload.beforeUpload("collateral/beforeUpdate",{"guId": guId} );
        		var onStart = function(file, data){
        			data.custDocType = $("#tb_doc_selector input[name='uploadDoc']:checked").val();
        			data.allDocType = $("#allDocType").val();//联保体类
        		};
        		var onOneSuc = function(){
        			utils.upload.beforeUpload("collateral/beforeUpdate",{"guId": guId} );
        			$("#documentType").val("");
        			utils.upload.refreshSelectorTable("collateral/findDocumentCustDocTypes", {"guId" : guId} );
        			var oTable = $("#tbWd").dataTable();
        			oTable.fnDraw();
        		};
        		utils.upload.initUploadify(formData,"#uploadPath","#uploadFile", onStart, onOneSuc);
        		utils.upload.refreshSelectorTable("collateral/findDocumentCustDocTypes", {"guId" : guId} );
        		$("#uploadDocumentForm").resetForm();
        		$("#add-modal-formWd").find("#documentUserName").val(formData.createUserName);
        		$("#add-modal-formWd div.modal-header h4").html("<i class='ace-icon fa fa-plus'></i> 添加文档");
        		$("#add-modal-formWd").modal("show");
        	}else{
        		utils.alert.warn("<strong>请先保存抵质押基本信息！</strong>");
        	}
        },
        submitDocument: function() {
            /**文件上传按钮*/
        	if($("#tb_doc_selector input[name='uploadDoc']:checked")[0]){
        		$("#uploadFile").uploadify("upload","*"); // 批量上传
        	}else{
        		utils.alert.warn("<strong>请选择上传文档的种类！</strong>");
        	}
        },
        downloadBtnLiveWd: function() {
            var viewSelf = this;
            $(document).on("click", "button[role='download_wd']",
            function(e) {
                var root_upload = $("#uploadPath").val();
                var obj = null;
                var $this = $(this);
                $.ajax({
                    cache: true,
                    type: "POST",
                    url: $$ctx + "singleCustomer/findDocumentPath",
                    data: {
                        documentId: $this.data("id")
                    },
                    // 
                    async: false,
                    error: function(request) {
                        utils.alert.err("查找下载路径出错,错误" + request.status);
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
             utils.button.confirm(this, function(result){
            	  if (result) {
                      var id = $this.data("id");
                      $.ajax({
                          url: $$ctx + 'contractMng/delDocument/' + id,
                          dataType: 'JSON',
                          type: 'POST',
                          success: function(r) {
                              if (r.success) {
                                  var a = utils.datatable.fresh("#tbWd");
                              } else {
                              	utils.alert.warn("<strong>"+ r.msg +"</strong>");
                              }
                          }
                      });
                  } 
             });
            });
               
        }
		
	});
	module.exports = view;
});