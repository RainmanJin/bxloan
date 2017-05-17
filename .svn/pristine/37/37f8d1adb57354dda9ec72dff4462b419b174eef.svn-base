define(function(require, exports, module) {
	var model = require("../main/model");
	var utils = require("utils");
	var view = Backbone.View.extend({
		el : "body",
		events : {
			/** 查看客户/配偶详情，弹出modal */
			"click button[role='detail_borrower']" : "openBorrowerDetail"
		},
		initialize : function() {
			this.model = new model();
			this.render();
		},
		render : function() {
			var viewSelf = this;
			/** 初始化共同借款人列表 */
			utils.dd.initDataDict(["CertificateType"], function(dataDict) {
				viewSelf.initCommonBorrowerTable(dataDict);
			});
		},
		initCommonBorrowerTable : function(dataDict) {
			var viewSelf = this;
            var oTable = $("#tbCoborrower").dataTable({
            	sAjaxSource: $$ctx + "creditContractMng/searchBorrowerList",
                bFilter: false,
                bLengthChange: false,
                aoColumns: [  
	                {
	                	bVisible: false,
	                    mData: "commonBorrowId"
	                },
	                {
	                    mData: "customerName"
	                },
	                {
	                    mData: "certificateTypeCd",
	                    mRender: function(data, type, rowdata) {
	                    	if(data){
	                    		return dataDict.CertificateType[data];
	                    	} else{
	                    		return "";
	                    	}
	                    }
	                },
	                {
	                    mData: "certificateNum"
	                },
	                {
	                    mData: "mobilePhone"
	                },
	                {
	                    mData: "phone"
	                },
	                {
	                    mData: "address"
	                },
	                {
	                	bVisible: false,
	                    mData: "spouseflag"
	                },
	                {
	                	bVisible: false,
	                	mData: "projectId"
	                },
	                {
	                	bVisible: false,
	                	mData: "partyId"
	                },
	                {
	                	bVisible: false,
	                	mData: "partyTypeCd"
	                },
	                {
	                    mData: null,
	                    mRender: function(data, type, rowdata) {
	                    	/*var button = "<button type='button' role='detail_borrower' data-type='"
									+ rowdata.partyTypeCd
									+ "' data-paid='"
									+ rowdata.projectId
									+ "' data-flag='"
									+ rowdata.spouseflag
									+ "' data-partyid='"
									+ rowdata.partyId
									+ "' class='btn btn-xs btn-yellow' title='查看'><i class='ace-icon fa fa-eye'></i></button>";
							return button; */
							return Mustache.render($("#dt-row-commonBorrow-operation").html(), {
										type : rowdata.partyTypeCd,
										paid : rowdata.projectId,
										flag : rowdata.spouseflag,
										partyid : rowdata.partyId
									});
	                    }
	                }
                ],
                fnServerParams: function(aoData) {
                    aoData.push({
                        "name": "projectId",
                        "value": $('#projectIdField').val()
                    });
                }
            });
            viewSelf.oTable = oTable;
        },
        openBorrowerDetail: function(e){
        	var viewSelf = this;
        	var $btnSelf = $(e.currentTarget);
        	var partyid = $btnSelf.data("partyid");
        	if($btnSelf.data('flag') == 1){//配偶
        		var data = {
    				correlativeRelationsId : partyid	
        		};
        		viewSelf.model.openSpouseWindow(data, function(r_data){
        			utils.forms.putValueToForm(r_data, $("#form-familyFriend"));
        			$("#modal-formLxr").modal('show');
        		});
        	} else{//客户
        		var partyType = $btnSelf.data("type") + "";//客户类型
        		var data = {
    				customerId : partyid,
    				workCode : "TODETAIL",
    				customerSource : "detail",
    				consultLocation : "contract"
        		};
        		viewSelf.model.openCustomerWindow(partyType, data, function (result){
        			$("#mainFrame").attr("src", result);
                	var $modal = $("#modalForView");
                    /*$modal.find('span[role="modal-title"]').text('客户信息');*/
                    $modal.modal("show");
        		});
        	}
        }
	});
	module.exports = view;
});