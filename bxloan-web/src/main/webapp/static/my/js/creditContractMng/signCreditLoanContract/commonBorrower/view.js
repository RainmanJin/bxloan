define(function(require, exports, module) {
	var model = require("../main/model");
	var utils = require("utils");
	var view = Backbone.View.extend({
		el : "body",
		events : {
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
			viewSelf.borrowerDetailLiveBtn();
		},
		initCommonBorrowerTable : function(dataDict) {
			var viewSelf = this;
            var oTable = $("#tbCoborrower").dataTable({
            	sAjaxSource: $$ctx + "contractMng/searchBorrowerList",
                bFilter: false,
                bLengthChange: false,
                aoColumns: [null,null,null,null,null,null,null,null,null,null],
                aoColumnDefs: [{
                    bVisible: false,
                    aTargets: [0]
                },
                {
                    bVisible: false,
                    aTargets: [9]
                },
                {
                    bVisible: false,
                    aTargets: [8]
                },
                {
                    aTargets: [2],
                    mRender: function(data, type, full) {
                    	if(data){
                    		return dataDict.CertificateType[data];
                    	} else{
                    		return "";
                    	}
                    }
                },
                {
                    aTargets: [7],
                    mRender: function(data, type, full) {
                        var button = "<button type='button' role='detail_borrower' data-type='"+ full[10] +"' data-paId='" + data + "' data-flag='" + full[8] + "' data-partyid='" + full[9] + "' class='btn btn-xs btn-yellow' title='查看'><i class='ace-icon fa fa-eye'></i></button>";
                    	return button;
                    }
                }],
                fnServerParams: function(aoData) {
                    aoData.push({
                        "name": "projectId",
                        "value": $('#projectIdField').val()
                    });
                }
            });
            viewSelf.oTable = oTable;
        },
        borrowerDetailLiveBtn: function() {
            var viewSelf = this;
            $(document).on("click", "button[role='detail_borrower']",
	            function(e) {
	            	var $this = $(this);
	            	var partyid = $this.data("partyid");
	            	if($this.data('flag') == 1){//配偶
	            		var data = {
            				correlativeRelationsId : partyid	
	            		};
	            		viewSelf.model.openSpouseWindow(data, function(r_data){
	            			utils.forms.putValueToForm(r_data, $("#form-familyFriend"));
	            			$("#modal-formLxr").modal('show');
	            		});
	            		return ;
	            	} else{//客户
	            		var partyType = $this.data("type") + "";//客户类型
	            		var data = {
            				customerId : partyid,
            				workCode : "TODETAIL",
            				customerSource : "detail",
            				consultLocation : "contract"
	            		};
	            		viewSelf.model.openCustomerWindow(partyType, data, function (result){
	            			console.log(result);
	            			$("#mainFrame").attr("src", $$ctx + result);
	                    	var $modal=$("#modalForView");
	                        $modal.find('span[role="modal-title"]').text('客户信息');
	                        $modal.modal("show");
	            		});
	            	}
            });
        }
	});
	module.exports = view;
});