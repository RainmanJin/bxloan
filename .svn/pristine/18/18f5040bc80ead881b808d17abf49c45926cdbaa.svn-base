define(function(require, exports, module) {
	var model = require("./model");
	var utils = require("utils");
	var view = Backbone.View.extend({
		el : "body",
		events : {
			/** 点击上传文档 */
			"click #add-Wd-submit": "submitDocument"
		},
		initialize : function() {
			this.model = new model();
			this.render();
		},
		render : function() {
			/** 初始化从合同列表 */
			this.initSubContract();
			/** 从合同下载初始化 */
			this.downloadContrFileLiveWd();
			/** 从合同上传初始化 */
			this.uploadBtnLiveSubContract();
		},
		initSubContract: function(){
			var viewSelf = this;
			/** 初始化保证人列表 */
			utils.dd.initDataDict(["CommonWhether", "CertificateType"],function(dataDict) {
				 viewSelf.initDataTablesForAssureSubcontract(dataDict);
			});
			/** 初始化抵质押列表 */
			utils.dd.initDataDict(["CommonWhether", "GuaranteeTypeCode"],function(dataDict){
				 viewSelf.initDataTablesForCollateralSubcontract(dataDict);
			});
		},
		initDataTablesForAssureSubcontract: function(dataDict){
       	 	var viewSelf = this;
            var oTable = $("#tbBzr").dataTable({
                sAjaxSource: $$ctx + "signCreditLoanContract/searchAssureSubcontractList",
                bFilter: false,
                bLengthChange: false,
                aoColumns: [null,null,null,null,null,null,null,null,null],
                aoColumnDefs: [{
                	bVisible: false,
                	aTargets: [0]
                },
                {
                	aTargets: [3],
                	mRender: function(data, type, full) {
                        return dataDict.CertificateType[data];
                	}
                },
                {
                	aTargets: [6],
                	mRender: function(data, type, full) {
                		if(data==null){
                			return "否";
                		}
                		return dataDict.CommonWhether[data];
                	}
                },
                {
                    bVisible: false,
                    aTargets: [7]
                },
                {
                	aTargets: [8],
                	mRender: function(data, type, full) {
                        var button = "<button type='button' role='downloadGuarContr' data-subid='"+ full[0] +"' data-paId='" + data + "'  class='btn btn-xs btn-yellow' title='下载保证从合同'><i class='ace-icon fa fa-download'></i></button> ";
                        if($("#workTypeField").val()==""){
                        	button += "<button type='button' role='uploadGuarContr' data-subid='"+ full[0] +"' data-paId='" + data + "'  class='btn btn-xs btn-info' title='上传保证从合同'><i class='ace-icon fa fa-upload'></i></button></button>" ;
                        }
                    	return button;
                	}
                }],
                fnServerParams: function(aoData) {
                    aoData.push({
                        "name": "projectId",
                        "value": $('#projectIdField').val()
                    },
                    {
                        "name": "contractIdField",
                        "value": $('#contractIdField').val()
                    });
                },
                fnDrawCallback : function(){
                	utils.num.colsFormat(this, [5]);
                }
            });
            viewSelf.oTable = oTable;
       },
       initDataTablesForCollateralSubcontract: function(dataDict){
    	   var viewSelf = this;
	       var oTable = $("#tbDzy").dataTable({
	            sAjaxSource: $$ctx + "signCreditLoanContract/searchCollateralSubcontractList",
	            bFilter: false,
	            bLengthChange: false,
	            aoColumns: [null,null,null,null,null,null,null,null,null,null,null,null,null],
	            aoColumnDefs: [{
	                bVisible: false,
	                aTargets: [0]
	            },
	            {
	                aTargets: [7],
	                mRender: function(data, type, full) {
	                    return dataDict.GuaranteeTypeCode[data];
	                }
	            },
	            {
	                aTargets: [8],
	                mRender: function(data, type, full) {
	                	if(data==null){
	                		return "否";
	                	}
	                    return dataDict.CommonWhether[data];
	                }
	            },
	            {
	                bVisible: false,
	                aTargets: [9]
	            },
	            {
	                bVisible: false,
	                aTargets: [11]
	            },
	            {
	                bVisible: false,
	                aTargets: [12]
	            },
	            {
	                aTargets: [10],
	                mRender: function(data, type, full) {
	                	var arr =["0","1","2","3","4","17","18"];
	                    var button ="";
	                    if($("#workTypeField").val()==""){
	                    	button += "<button type='button' role='upload_subcontract' data-subid='"+ full[0] +"'  data-guid='"+ full[11] +"'  data-paId='" + data + "'  class='btn btn-xs btn-info' title='上传抵质押从合同'><i class='ace-icon fa fa-upload'></i></button></button>" ;
	                    }
	                    button += "<button type='button' role='downloadCollaContr' data-subid='"+ full[0] +"' data-guid='"+ full[11] +"'  data-paId='" + data + "'  data-guatype='"+ full[12] +"'  class='btn btn-xs btn-yellow' title='下载抵质押从合同'><i class='ace-icon fa fa-download'></i></button> ";
	                    if(arr.indexOf(full[12])<0){
	                    	button+= "<button type='button' role='downloadPledContrList' data-subid='"+ full[0] +"' data-guid='"+ full[11] +"'  data-paId='" + data + "' data-guatype='"+ full[12] +"'  class='btn btn-xs btn-success' title='下载质押从合同清单'><i class='ace-icon fa fa-download'></i></button> " ;
	                    }else{
	                    	button+= "<button type='button' role='downloadPledContrList' data-subid='"+ full[0] +"' data-guid='"+ full[11] +"'  data-paId='" + data + "' data-guatype='"+ full[12] +"'  class='btn btn-xs btn-success' title='下载质押从合同清单' disabled><i class='ace-icon fa fa-download'></i></button> " ;
	                    }
	                    return button;
	                }
	            }],
	            fnServerParams: function(aoData) {
	                aoData.push({
	                    "name": "projectId",
	                    "value": $('#projectIdField').val()
	                },
                    {
                        "name": "contractIdField",
                        "value": $('#contractIdField').val()
                    });
	            },
                fnDrawCallback : function(){
                	utils.num.colsFormat(this, [4,5,6]);
                }
	        });
	        viewSelf.oTable = oTable;
   		},
   		downloadContrFileLiveWd: function(){
        	var viewSelf = this;
        	//下载抵质押从合同
         	$(document).on( "click", "button[role='downloadCollaContr']", function(){
         		 var btnSelf = $(this);
         		 viewSelf.model.downloadCollaContr(btnSelf.data("subid"), function(r_data){
         			 window.location.href = r_data;
         		 });
         	});
         	//下载抵质押从合同清单
         	$(document).on( "click", "button[role='downloadPledContrList']", function(){
         		 var btnSelf = $(this);
         		 viewSelf.model.verifyContrListDownload(btnSelf.data("subid"), function(r_data){
         			if(r_data){
	  					if(r_data.success){
	  						viewSelf.model.downloadPledContrList(btnSelf.data("subid"),function(r_data){
	  			       			 //viewSelf.model.downloadFile(r_data);
	  			       			 window.location.href = r_data;
	  			       		 });
	  					} else{
	  						utils.alert.warn( r_data.msg);
	  					}
	  				}else{
	  					utils.alert.err( "查询失败请稍后重试");
	  				}
         		 });
         	});
         	//下载保证从合同
            $(document).on( "click", "button[role='downloadGuarContr']",function(){
	          	 var btnSelf = $(this);
	          	 viewSelf.model.downloadGuarContr(btnSelf.data("subid"),function(r_data){
	          		 window.location.href = r_data;
	          	 });
            });
       },
       uploadBtnLiveSubContract:function(){
	     	var viewSelf = this;
	        $(document).on("click", "button[role='uploadGuarContr'], button[role='upload_subcontract']", function(e) {
	               var $this = $(this);
	               $("#uploadTypeCode").val("subcontract");
	               $("#subcontractIdField").val($this.data("subid"));
	               //上传文档前获取相关参数
	               var formData = utils.upload.beforeUpload("contractMng/beforeUpdate",{
	                   "partyId": $("#partyIdField").val(),
	                   "projectId": $("#projectIdField").val(),
	                   "subcontractId": $("#subcontractIdField").val(),
	                   "uploadType": "subcontract"
	               });
	               var onStart = function(file, data){
		               	formData.allDocType = utils.upload.changeAllDocType("100314");
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
	               utils.upload.initUploadify(formData, "#uploadPathField", "#uploadFile", onStart, onOneSuc, onQueueEnd);
	               
	               $("#subcontractIdField").val("");
	               $("#uploadDocumentForm").resetForm();
	               $("#documentUserName").val(formData.createUserName);
	               $("#add-modal-formWd div.modal-header h4").html("<i class='ace-icon fa fa-plus'></i> 添加文档");
	               $("#add-modal-formWd").modal("show");
           	});
       	},
        submitDocument: function() {
            $("#uploadFile").uploadify("upload", "*");
        }
	});
	module.exports = view;
});