/**
 * 联保体客户详细
 * 
 * author: lijing
 * lastModified: lijing 2014-08-05 16:30:24
 */

define(function(require, exports, module) {
    var model = require("./model");
    var modalModule = require("./modalfunc");
    var utils = require("utils");
    var controllerUrl = $$ctx + 'unitecustomer/';
    var $hidden = $("#hiddenParams");
    var view = Backbone.View.extend({
        el: "body",
        events: {
        	"click #btn_doc_search" : "queryDocument",
        	"click #btn_doc_add" : "addWd",
        	"click #add-Wd-submit": "submitDocument"
        },
        initialize: function() {
            /** 初始化 */
            this.model = new model();
            this.render();
        },
        render: function() {
            /** 页面渲染 */
            this.initFirstOfAll();
            this.pdownloadBtnLive();
            this.initDocDataTable();
            this.downloadBtnLiveWd();
            this.deleteBtnLiveWd();
            this.initCustDocTable();
        },
        initFirstOfAll: function() {
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
        		var root_upload = $("#hiddenParams").find("input[name='upload_path']").val();
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
        initDocDataTable: function(){
            	/**
            	 * 初始化文档列表
            	 * */
            	 var viewSelf = this;
            	 utils.dd.initDataDict(["DocumentType","CustProjectAllDocType","CreateType"], function(dataDict) {
                 var oTable = $("#tbWd").dataTable({
                     "sAjaxSource": controllerUrl + "findDocuments",
                     "bFilter": false,
                     "bAutoWidth": true,
                     "bLengthChange": false,
                     "aoColumns": [
                     {	
                         "mData": "documentId",
                         mRender: function(data, type, rowdata) {
                             	var cb  = "<input type='checkbox' name='documentNums' value='"+ data +"' />";
                             	return cb;
                         }
                     },
                     {
                         "mData": "documentName"
                     },
                     {
                         "mData": "custDocType",
                         mRender: function(data, type, rowdata) {
                        	 if(data){
                        		 return dataDict.CustProjectAllDocType[data];
                        	 }else{
                        		 return "";
                        	 }
                         }
                     },
                     {
                         "mData": "documentType",
                         mRender: function(data, type, rowdata) {
                        	 if(data){
                        		 return dataDict.DocumentType[data];
                        	 }else{
                        		 return "-";
                        	 }
                         }
                     },
                     {
                         "mData": "unGroupName"
                     },
                     {
                         "mData": "custManagerName"
                     },
                     {
                         "mData": "createDate"
                     },
                     {
                         "mData": "createTypeCd",
                         mRender: function(data, type, rowdata) {
                        	 if(data){
                        		 return dataDict.CreateType[data];
                        	 }else{
                        		 return "-";
                        	 }
                         }
                     },
                     {
                         "mData": "unCustTabId",
                         mRender: function(data, type, rowdata) {
//                        	  "<button type='button' role='uploadDoc' data-id='" 
//                     	        + rowdata.documentId + "' data-unid = '"+ rowdata.unCustTabId +"' class='btn btn-xs btn-info'><i class='ace-icon fa fa-upload' title='上传'></i></button> " 
                        	 var buttons =  "<button type='button' role='downloadDoc'  data-id='" + rowdata.documentId + "' data-unid = '"+ rowdata.unCustTabId +"' class='btn btn-xs btn-yellow' title='下载' ><i class='ace-icon fa fa-download'></i></button> " 
                     	        + "<button type='button' role='deleteDoc' data-id='" + rowdata.documentId + "' data-unid = '"+ rowdata.unCustTabId +"' class='btn btn-xs btn-danger'><i class='ace-icon fa fa-trash-o' title='删除'></i></button>";
                         	return buttons
                         }
                     }
                    ],
                     "fnServerParams": function(aoData) {
                         aoData.push({
                             "name": "unGuId",
                             "value": $("#hiddenParams").find("input[name='unGuId']").val()
                         });
                         aoData.push({
                             "name": "query_documentName",
                             "value": $("#form-doc-search").find("input[name='docName']").val()
                         });
                         aoData.push({
                             "name": "query_contentType",
                             "value": $("#form-doc-search").find("input[name='docContentType']").val()
                         });
                     },
                     "fnDrawCallback": function(){
                    	 if($("#hiddenParams").find("input[name='action']").val()=="view"){
                    		 $("#tbWd").find("button[role='deleteDoc']").remove();
                    		 $("#btn_doc_add").remove();
                    	 }
                     }
                 });
                 viewSelf.oTable = oTable;
            	});
        },
        queryDocument: function(){
        	$("#tbWd").dataTable().fnDraw();
        },
        initCustDocTable: function(){
        	var viewSelf = this;
        	viewSelf.model.findCustDocTypes({
        		"unGuId" : $("#hiddenParams").find("input[name='unGuId']").val()
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
        },
        addWd: function(){//TODO
        	var viewSelf = this;
        	var unGuId = $("#hiddenParams").find("input[name='unGuId']").val();
        	
        	 var formData = utils.upload.beforeUpload("unitecustomer/beforeUpdate",{"unGuId": unGuId} );
             var onStart = function(file, data){
             	data.custDocType = $("#tb_doc_selector input[name='uploadDoc']:checked").val();
             	data.allDocType = $("#allDocTypeField").val();//联保体类
             };
             var onOneSuc = function(){
             	utils.upload.beforeUpload("unitecustomer/beforeUpdate",{"unGuId": unGuId} );
                 $("#documentType").val("");
                 utils.upload.refreshSelectorTable("unitecustomer/findDocumentCustDocTypes", {"unGuId" : unGuId} );
                var oTable = $("#tbWd").dataTable();
                oTable.fnDraw();
             };
             utils.upload.initUploadify(formData,"#hiddenParams input[name='upload_path']","#uploadFile", onStart, onOneSuc);
             utils.upload.refreshSelectorTable("unitecustomer/findDocumentCustDocTypes", {"unGuId" : unGuId} );
             $("#uploadDocumentForm").resetForm();
             $("#add-modal-formWd").find("#documentUserName").val(formData.createUserName);
             $("#add-modal-formWd div.modal-header h4").html("<i class='ace-icon fa fa-plus'></i> 添加文档");
             $("#add-modal-formWd").modal("show");
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
            $(document).on("click", "button[role='downloadDoc']",
            function(e) {
                var root_upload = $("#hiddenParams").find("input[name='upload_path']").val();
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
            $(document).on("click", "button[role='deleteDoc']",
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