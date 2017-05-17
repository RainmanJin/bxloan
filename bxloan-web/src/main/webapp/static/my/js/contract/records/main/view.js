/**
 * 放款记录页面的js
 * */


define(function(require, exports, module) {
    var model = require("./model");
    var utils = require("utils");

    var view = Backbone.View.extend({
        el: "body",
        events: {
            "click #add": "addPayRecord",
            "click #add-Wd-submit": "submitDocument"
        },
        initialize: function() {
            /** 初始化 */
            this.model = new model();
            this.render();
        },
        render: function() {
            /** 页面渲染 */
            this.initDataTables();
            this.downloadBtnLiveWd();
            this.uploadBtnLiveWd();
        },
        initDataTables: function() {
            /** 初始化DataTables */
            var viewSelf = this;
            utils.dd.initDataDict(["PayLoanStatus", "CommonWhether"],
            function(dataDict) {
                var oTable = $("#tbContracts").dataTable({
                    "sAjaxSource": $$ctx +"contractList/findContractsBySearch",
                    "bFilter": false,
                    "bAutoWidth": true,
                    "bLengthChange": false,
                    "aoColumns": [null, null, null, null, null, null, null, null, null, null],
                    "aoColumnDefs": [{
                        "bVisible": false,
                        "aTargets": [0]
                    },
                    {
                        "aTargets":  [7],
                        "mRender": function(data, type, full) {
                            if (data != null) {
                                switch (data) {
                                case "0":
                                    {
                                        return "否";
                                        break;
                                    }
                                case "1":
                                    {
                                        return "是";
                                        break;
                                    }

                                default:
                                    {
                                        return "未填写"
                                        break;
                                    }
                                }
                                // return dataDict.CommonWhether[data];
                            }
                            return "未填写";
                        }
                    },
                    {
                        "aTargets":  [8],
                        "mRender": function(data, type, full) {
                            return dataDict.PayLoanStatus[data];
                        }
                    },
                    {
                        "aTargets": [9],
                        "mRender": function(data, type, full) {
                            var buttons = "<button type='button' role='upload' class='btn btn-xs btn-info' title='上传借据' data-id='" + full[0] + "'><i class='ace-icon fa fa-upload'></i></button> " + "<button type='button' role='download' class='btn btn-xs btn-yellow' title='打印借据'data-id='" + full[0] + "' ><i class='ace-icon fa fa-download'></i></button>";
                            return buttons;
                        }
                    }
                    ],
                    "fnServerParams": function(aoData) {
                        aoData.push({
                            "name": "contractId",
                            "value": $('#contractIdField').val()
                        });
                        aoData.push({
                            "name": "cuserId",
                            "value": $('#cuserIdField').val()
                        });
                    },
                    "fnDrawCallback" : function(){
                    	//utils.num.tableFormat(this);//对表格内的纯数字列加逗号
                    	utils.num.colsFormat(this,[4,5]);//对表格内指定数字列加逗号，索引从1开始
                    }
                });

                viewSelf.oTable = oTable;
            });
        },
        addPayRecord: function() {
            var viewSelf = this;
            viewSelf.model.checkContractStatus({
                contractId: $("#contractIdField").val()
            },
            function(data) {
                if (data == "success") {
                    window.location.href = $$ctx + "contractList/addPayRecord?contractId=" + $("#contractIdField").val() + "&cuserId=" + $("#cuserIdField").val();
                } else if (data == "payLoanExecutingError") {
                	utils.alert.warn( "<strong>此合同正在放款中！</strong>");
                } else if (data == "amtCannotAffordError") {
                	utils.alert.warn( "<strong>合同可用金额小于合同金额,不允许多次放款,请确认！</strong>");
                } else if (data == "repayInAdvanceError") {
                	utils.alert.warn( "<strong>此合同正在提前还款中,请确认！</strong>");
                } else if (data == "repayModeNotAvaliableError") {
                	utils.alert.warn("<strong>目前只有周期付息,到期还本与一次性到期付本允许多次放款,请确认!</strong>");
                } else if (data == "contractNotPayOrCancelError") {
                	utils.alert.warn( "<strong>此合同未入账或正在冲销中,请确认!</strong>");
                } else if (data == "contractRenewExecutingError") {
                	utils.alert.warn("<strong>此合同正在展期中,请确认!</strong>");
                } else if (data == "againPayLoanError") {
                	utils.alert.warn("<strong>贷款逾期或计提了损失准备就不能继续进行放款操作,请确认!</strong>");
                }
            });
        },
        changeIsUpload: function(id) {
            var flag = false;
            var viewSelf = this;
            viewSelf.model.changeIsUpload(id.payLoanId,
            function(data) {
                if (data == "success") {
                    flag = true;
                }
            });
            return flag;
        },
        downloadBtnLiveWd: function() {
            var viewSelf = this;
            $(document).on("click", "button[role='download']",
            function(e) {
                var root_upload = $("#uploadPathField").val();
                var obj = null;
                var $this = $(this);
                window.location.href = $$ctx + 'contractList/testReport?payLoanId=' + $this.data("id");
            });
        },
        uploadBtnLiveWd: function() {
            var viewSelf = this;

            $(document).on("click", "button[role='upload']",
            function(e) {
                /**上传文档弹窗*/
                var $this = $(this);
                
                var formData = utils.upload.beforeUpload("contractList/beforeUpdate",{
                    "payLoanId": $this.data("id")
                });
                var onOneSuc = function(){
                	 var datatables = $("#tbContracts").dataTable();
                     datatables.fnDraw();
                };
                var onQueueEnd = function (){
                	var flag = viewSelf.changeIsUpload({
                        "payLoanId": $this.data("id")
                    });
                    if (flag) {
                    	utils.alert.suc("<strong>上传成功！</strong>");
                    } else {
                    	utils.alert.err( "<strong>系统错误！</strong>");
                    }
                    $("#add-modal-formWd").modal("hide");
                    $("#uploadDocumentForm").resetForm();
                };
                utils.upload.initUploadify(formData,"#uploadPathField","#uploadFile", null, onOneSuc, onQueueEnd, 1);
                //
                $("#uploadDocumentForm").resetForm();
                $("#documentUserName").val(formData.createUserName);
                $("#add-modal-formWd div.modal-header h4").html("<i class='ace-icon fa fa-plus'></i> 添加文档");
                $("#add-modal-formWd").modal("show");
            });
        },
        submitDocument: function() {
            /**文件上传按钮*/
            $("#uploadFile").uploadify("upload"); // 批量上传
        }
    });
    module.exports = view;
});