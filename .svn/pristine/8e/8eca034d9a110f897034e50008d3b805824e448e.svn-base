define(function(require, exports, module) {
	var model = require("./model");
	var utils = require("utils");
	var view = Backbone.View.extend({
		el : "body",
		events : {
			/** 点击上传文档 */
			"click #add-Wd-submit": "submitDocument",
			/** 下载抵质押从合同 */
			"click button[role='downloadCollaContr']": "downloadCollaContr",
			/** 下载质押从合同清单 */
			"click button[role='downloadPledContrList']": "downloadPledContrList",
			/** 下载保证从合同 */
			"click button[role='downloadGuarContr']": "downloadGuarContr"
		},
		initialize : function() {
			this.model = new model();
			this.render();
		},
		render : function() {
			/** 初始化从合同列表 */
			this.initSubContract();
			/** 从合同上传初始化 */
			this.uploadBtnLiveSubContract();
		},
		initSubContract: function(){
			var viewSelf = this;
			utils.dd.initDataDict(["CommonWhether", "CertificateType", "GuaranteeTypeCode"],function(dataDict) {
				/** 初始化保证人列表 */
				viewSelf.initDataTablesForAssureSubcontract(dataDict);
				/** 初始化抵质押列表 */
				viewSelf.initDataTablesForCollateralSubcontract(dataDict);
			});
		},
		initDataTablesForAssureSubcontract: function(dataDict){
       	 	var viewSelf = this;
            var oTable = $("#tbBzr").dataTable({
                sAjaxSource: $$ctx + "creditContractMng/searchAssureSubcontractList",
                bFilter: false,
                bLengthChange: false,
                aoColumns: [
                    {
	                    mData: "subcontractNum"
	                },
	                {
	                	mData: "customerName"
	                },
	                {
	                	mData: "certificateTypeCd",
	                	mRender: function(data, type, rowdata) {
	                		return dataDict.CertificateType[data];
	                	}
	                },
	                {
	                	mData: "certificateNum"
	                },
	                {
	                	mData: "actualGuaranteeAmt"
	                },
	                {
	                	mData: "isTransDocument",
	                	mRender: function(data, type, rowdata) {
	                		if(data==null){
		                		return "否";
		                	}
		                    return dataDict.CommonWhether[data];
	                	}
	                },     
	                {
	                	mData: null,
	                	mRender: function(data, type, rowdata) {
							var button = [];
							var subContractId = rowdata.subContractId;
		                    //拼接上传保证从合同按钮
		                    button.push("<button type='button' role='uploadGuarContr' data-subid='");
		                    button.push( + subContractId + "'");
		                    button.push("class='btn btn-xs btn-info' title='上传保证从合同'>");
		                    button.push("<i class='ace-icon fa fa-upload'></i>");
		                    button.push("</button>");
		                    
		                    //拼接下载保证从合同按钮
		                    button.push("<button type='button' role='downloadGuarContr' data-subid='");
		                    button.push( + subContractId + "'");
		                    button.push("class='btn btn-xs btn-yellow' title='下载保证从合同'>");
		                    button.push("<i class='ace-icon fa fa-download'></i>");
		                    button.push("</button>");
	                    	return button.join("");
	                	}
	                }      
                ],
                fnServerParams: function(aoData) {
                    aoData.push({
                        "name": "projectId",
                        "value": $('#projectIdField').val()
                    },
                    {
                        "name": "creditContractId",
                        "value": $('#creditContractIdField').val()
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
	            sAjaxSource: $$ctx + "creditContractMng/searchCollateralSubcontractList",
	            bFilter: false,
	            bLengthChange: false,
	            aoColumns: [ 
	                {
	                    mData: "subcontractNum"
	                },
	                {
	                	mData: "guarantyName"
	                },
	                {
	                	mData: "guarantorName"
	                },
	                {
	                	mData: "evalValue"
	                },
	                {
	                	mData: "setGuaranteeAmt"
	                },
	                {
	                	mData: "actualCreditAmount"
	                },
	                {
	                    mData: "guaranteeType",
	                    mRender: function(data, type, rowdata) {
	                    	return dataDict.GuaranteeTypeCode[data];
	                    }
	                },
	                {
	                	mData: "isTransDocument",
	                	mRender: function(data, type, rowdata) {
	                		if(data==null){
		                		return "否";
		                	}
		                    return dataDict.CommonWhether[data];
	                	}
	                },
	                {
	                    mData: null,
	                    mRender: function(data, type, rowdata) {
	                    	var arr =["0","1","2","3","4","17","18"];
		                    var button = [];
		                    var subContractId = rowdata.subContractId;
		                    //拼接上传抵质押从合同按钮
		                    button.push("<button type='button' role='upload_subcontract' data-subid='");
		                    button.push( + subContractId + "'");
		                    button.push("class='btn btn-xs btn-info' title='上传抵质押从合同'>");
		                    button.push("<i class='ace-icon fa fa-upload'></i>");
		                    button.push("</button>");
		                    
		                    //拼接下载抵质押从合同按钮
		                    button.push("<button type='button' role='downloadCollaContr' data-subid='");
		                    button.push( + subContractId + "'");
		                    button.push("class='btn btn-xs btn-yellow' title='下载抵质押从合同'>");
		                    button.push("<i class='ace-icon fa fa-download'></i>");
		                    button.push("</button>");
		                    
		                    //判断抵质押物类型是否为质押
		                    var flag = arr.indexOf(rowdata.guarantyTypeCd) < 0;
							if (flag) {
								button.push("<button type='button' role='downloadPledContrList' data-subid='");
								button.push( + subContractId + "'");
								button.push("class='btn btn-xs btn-success' title='下载质押从合同清单'>");
								button.push("<i class='ace-icon fa fa-download'></i>");
								button.push("</button>");
							} else {
								$("button[role='downloadPledContrList']").attr("disabled", "disabled");
								button.push("<button type='button' role='downloadPledContrList' data-subid='");
								button.push( + subContractId + "'");
								button.push("class='btn btn-xs btn-success' title='下载质押从合同清单' disabled>");
								button.push("<i class='ace-icon fa fa-download'></i>");
								button.push("</button>");
							}
		                    return button.join("");
	                	}
	                }
                ],
	            fnServerParams: function(aoData) {
	                aoData.push({
	                    "name": "projectId",
	                    "value": $('#projectIdField').val()
	                },
                    {
                        "name": "creditContractId",
                        "value": $('#creditContractIdField').val()
                    });
	            },
                fnDrawCallback : function(){
                	utils.num.colsFormat(this, [4,5,6]);
                }
	        });
	        viewSelf.oTable = oTable;
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
        },
        downloadCollaContr: function(e){
        	var viewSelf = this;
        	var $btnSelf = $(e.currentTarget);
        	var subid = $btnSelf.data("subid");
        	viewSelf.model.downloadCollaContr(subid, function(r_data){
     			 window.location.href = r_data;
     		});
        },
        downloadPledContrList: function(e){
        	var viewSelf = this;
        	var $btnSelf = $(e.currentTarget);
        	var subid = $btnSelf.data("subid");
        	viewSelf.model.verifyContrListDownload(subid, function(r_data){
				if(r_data.success){
					viewSelf.model.downloadPledContrList(subid, function(r_data){
		       			 //viewSelf.model.downloadFile(r_data);
		       			 window.location.href = r_data;
		       		});
				} else{
					utils.alert.warn( r_data.msg);
				}
      		 });
        },
        downloadGuarContr: function(e){
        	var viewSelf = this;
        	var $btnSelf = $(e.currentTarget);
        	var subid = $btnSelf.data("subid");
        	viewSelf.model.downloadGuarContr(subid, function(r_data){
          		window.location.href = r_data;
          	});
        }
	});
	module.exports = view;
});