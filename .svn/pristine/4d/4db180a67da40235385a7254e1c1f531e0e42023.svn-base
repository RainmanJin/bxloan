//审批流程文档

define(function (require, exports, module)
{
	
    var model = require("./model");
    var utils = require("utils");
    var view = Backbone.View.extend(
        {
            el : "body",
            events :
            {	
            	/**文档查询*/
                "click #btn-query" : "queryDocuments",    
                /**点击右侧眼睛中的相关文档*/
                "click #documentsForFloatWindow" : "openDocumentsWindow",
                /**点击上传文件*/
                "click button[role='uploadCommentFile']": "showUploadModal",
                /**点击下载文件*/
                "click button[role='downloadCommentFile']": "downloadCommonFile",
                /**点击上传*/
                "click button[role='uploadBtn']": "uploadCommentFile"
            },
            initialize : function ()
            {	
                /** 初始化 */
                this.model = new model();
                //this.render();
                this.product={};
                this.product.unityLoanProductMark=utils.parseBool($("#unityLoanProductMark").val());//团结贷标示
            },
            /**贷款审查、初审、签订合同环节*/
            render : function ()
            {
                /** 初始化相关文档窗口 */
                this.initDocumentsDatatables();  
                /**单个文档下载*/
                this.downloadBtnLiveWd();
                /**初始化上传文档窗口*/
                this.initCustDocTable();
                /**批量下载*/
                this.pdownloadBtnLive();
                /**文档删除*/
                this.deleteBtnLiveWd();
            },
            /**其他环节*/
            commonRender: function(){
            	 this.initDocumentsDatatables();
                 this.downloadBtnLiveWd();
                 this.pdownloadBtnLive();
                 $("button[role='delete_wd']").prop("disabled", true);
            },
            /**批量下载*/
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
	        		var root_upload = $("#upload_path").val();
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
	        /**初始化上传文档窗口*/
            initCustDocTable: function(){
	        	var viewSelf = this;
	        	var docContentTypesOfULP=['74','75'];//团结贷显示文档内容类型
	        	viewSelf.model.findCustDocTypes({
	        		"taskStageCode" : $("#taskStageCode").val(),
	        		"projectId" : $("#projectId").val()
	        	},function(r){
	        		if(r!=null&&r.custMap){
	        			var map = r.custMap;
	        			var $tbody = $("#uploadTbody");
	        			var count = 0;
	        			var total = 0;
	        			var html= "<tr>";
	        			for (var key in map) {
	        				count++;
	        				var str = "<td>" +
	        						   "<input type='radio' name='uploadDoc' value='"+ key +"' />" +
	        				           "</td>"+
	        				           "<td>" ;
	        				//团结贷时显示
	        				if(key=="51"||key=="52"||key=="53"||key=="57"
	        					||(viewSelf.product.unityLoanProductMark&&$.inArray(key,docContentTypesOfULP)>-1)){
	        					str+= "<font color='red'>*</font>";
	        				} 		
	        				        str += map[key]+
	        				           "</td>"+
	        				           "<td><span name='custSpan_"+ key +"'" +">" +
	        				           "</span></td>";
	        				 html+=str;
	        				if(count==1||r.count==total){
	        					html+="</tr>";
	        					$tbody.html($tbody.html()+html);
	        					count=0; html="<tr>";
	        				}
	        	        }  
	        			
	                    $(document).on("click", "input[name='uploadDoc'][type='radio']", function(e) {
	                    	 var $this = $(this);
	                    	$("#documentType").val($this.val());
	                    });
	                    
	                    $(document).on("click", "#input_doc_form button[class='close']", function(e) {
	                   	 var $this = $(this);
	                   	$("#documentType").val("");
	                   });	
	        			
	        		}
	        	});
            },
            /** 初始化相关文档窗口 */
            initDocumentsDatatables : function ()
            {		
                viewSelf = this;
                utils.dd.initDataDict([/*"CustomerDocType"*/ "CustProjectAllDocType", "DocumentType", "CreateType"],
                    function (dataDict)
                {
                    var oTable = $("#tbWd").dataTable(
                        {
                            "sAjaxSource" : $$ctx + "contractMng/findDocuments",
                            "bFilter" : false,
                            "bAutoWidth" : true,
                            "bLengthChange" : false,
                            "aoColumns" : [null, null, null, null, null, null, null, null, null],
                            "fnServerParams" : function (aoData)
                            {
                                aoData.push(
                                {
                                    "name" : "partyId",
                                    "value" : $('#partyId').val()
                                },
                                {
                                    "name" : "projectId",
                                    "value" : $('#projectId').val()
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
                                    	var cb  = "<input type='checkbox' name='documentNums' value='"+ rowdata[8] +"' />";
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
                                    "bVisible": false,
                                    "aTargets":  [4]
                                },
                                {
                                    "aTargets" : [7],
                                    mRender : function (data, type, rowdata)
                                    {
                                        return dataDict.CreateType[data];
                                    }
                                },
                                {
                                    "aTargets" : [8],
                                    "mRender" : function (data, type, full)
                                    {
                                        var buttons = "<button type='button' role='download_wd' data-id='" + data + "'  class='btn btn-xs btn-yellow' title='调阅文档'><i class='ace-icon fa fa-download'></i></button> ";
                                        if(full[7]=='1'){
                                        	buttons += "<button type='button' role='delete_wd' data-id='" + data + "'  class='btn btn-xs btn-danger'><i class='ace-icon fa fa-trash-o' title='删除文档'></i></button> ";
                                        }
                                        return buttons;
                                        
                                    }
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
            /**文档删除*/
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
                                            /*bootbox.alert("<strong>删除成功!</strong>",
                                            function() {});*/
                                        	var a = utils.datatable.fresh("#tbWd");
                                        } else {
                                        	utils.alert.err("<strong>"+data.msg+"</strong>");
                                        }
                                    }
                                });
                            }
                        }
                    }));
                    return false;
                });
            },
            /**单个文档下载*/
            downloadBtnLiveWd : function ()
            {
                var viewSelf = this;
                $(document).on("click", "button[role='download_wd']", function (e)
                {
                    var root_upload = $("#upload_path").val();
                    var obj = null;
                    var $this = $(this);
                    $.ajax(
                    {
                        cache : true,
                        type : "POST",
                        url : $$ctx + "contractMng/findDocumentPath",
                        data :
                        {
                            documentId : $this.data("id")
                        },
                        //
                        async : false,
                        error : function (request)
                        {
                            alert("查找下载路径出错" + request);
                        },
                        success : function (data)
                        {
                            obj = data;
                        }
                    }
                    );
                    var route = root_upload + '/fileDownloadServlet.servlet?cmd=downloadfilejs&path=' + obj[0] + '&filename=' + obj[1] + "&SYS_FLAG=bxloan";
                    location.href = route;
                }
                );
            },
            /**点击眼睛中的相关文档，弹出相关文档弹窗*/
            openDocumentsWindow : function ()
            {
                viewSelf = this;
                $("#query_documentName").val("");
                $("#query_contentType").val("");
                $("#showDocuments").modal("show").css({});
            },
            /**文档查询*/
            queryDocuments : function ()
            {
                var oTable = $("#tbWd").dataTable();
                oTable.fnDraw();
            },
            /**点击上传文件，弹出上传文档弹窗*/
            showUploadModal : function(){
            	var viewSelf = this;
//            	 var docTypeKey = "S40";//40电核网核审查意见表
//				    				    //41审批意见表
            	/**初始化上传文档弹窗基本信息，如文档类型，创建人等*/
            	var formData = utils.upload.beforeUpload("approval/beforeUpload",{
     				 "partyId" : $("#partyId").val(),
     				 "projectId" :$("#projectId").val()
     			});
 	            var onStart = function(file, data){
 	            	/**选择要上传的文件内容*/
 	            	data.custDocType = $("input[name='uploadDoc'][type='radio']:checked").val();
 	            	/**根据环节得到文档类型*/
 	            	data.allDocType = utils.upload.changeAllDocType($("#taskStageCode").val());
 	             	/**文档类型*/
 	            	data.documentType = utils.upload.changeDocumentType(data.custDocType, data.allDocType);
 	            };
 	            var onOneSuc = function(){
// 	            	utils.upload.beforeUpload("approval/beforeUpload",{
// 	     				 "partyId" : $("#partyId").val(),
// 	     				 "projectId" :$("#projectId").val()
// 	     			 });
 	                $("#documentType").val("");
 	                /**获取已经上传的文档种类及数量，重绘页面*/
 	                utils.upload.refreshSelectorTable("approval/findDocumentCustDocTypes", {
 	    				"projectId" : $("#projectId").val(),
 		        		"taskStageCode" : $("#taskStageCode").val()
 	    			});
 	                var oTable = $("#tbWd").dataTable();
 	                oTable.fnDraw();
 	            };
 	            /**初始化上传文档插件配置信息*/
 	            utils.upload.initUploadify(formData,"#upload_path","#uploadFile", onStart, onOneSuc, null, "#input_doc_modal");
 	            utils.upload.refreshSelectorTable("approval/findDocumentCustDocTypes", {
    				"projectId" : $("#projectId").val(),
	        		"taskStageCode" : $("#taskStageCode").val()
    			});
 	            /**上传文档弹窗*/
				var doc_form=$("#input_doc_form");
				$("#input_doc_form").resetForm();
				$("#custName").val(formData.createUserName);
				$("#input_doc_modal div.modal-header h4").html("<i class='ace-icon fa fa-plus'></i> 添加文档");
				$("#input_doc_modal").modal("show");
            },
            /**上传文档*/
            uploadCommentFile:function(){
            	if($("#documentType").val() != ""){
                    $("#uploadFile").uploadify("upload", "*");   //批量上传
    			}else{
    				utils.alert.warn("<strong>请选择上传文件的种类！</strong>");
    			}
            },
            /**下载文档*/
            downloadCommonFile: function(){ 
            	var params=[];
            	params.push("projectId="+$("#projectId").val());
            	params.push($("#sample-form").serialize());
            	params=encodeURI(params.join('&'));
            	/**贷款审查环节下载贷款审查表*/
            	if($("#taskStageCode").val() == "100411" || $("#taskStageCode").val() == '100711' || $("#taskStageCode").val() == '100811' ){  
            		window.location.href = $$ctx + "approvalFile/downloadDKSCInfo?"+params;
            	}
            	/**初审环节下载审批意见表*/
            	else if($("#taskStageCode").val()== "100412" || $("#taskStageCode").val() == '100712' || $("#taskStageCode").val() == '100812'){  
            		window.location.href = $$ctx + "approvalFile/downloadCSInfo?"+params;
            	}else{
            		utils.alert.warn("该环节不提供下载审查表的功能");
            		return false;
            	}
            }
            
        }
        );
    module.exports = view;
});