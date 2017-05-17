/**
 * 贷款发放页面的js
 * @author lijing
 * 
 * */
define(function(require, exports, module) {
    var model = require("./model");
    var utils = require("utils");
    
    var view = Backbone.View.extend({
        el: "body",
        events: {
        	'click button[role="downloadRpd"]':"downloadRpd"
        },
        initialize: function() {
            /** 初始化 */
            this.model = new model();
            this.render();
        },
        render: function() {
            /** 页面渲染 */
            this.initDataTables();
            this.editBtnLive();
        },
        initDataTables: function() {
            /** 初始化DataTables */
            var viewSelf = this;
            var currentPrincipalInterestTotal = 0;
            var currentPrincipalTotal = 0;
            var currentInterestTotal = 0;
            utils.dd.initDataDict(["PlanStatus"],
            function(dataDict) {
                var oTable = $("#tbRepayLoan").dataTable({
                    "sAjaxSource": $$ctx + "repayPlan/findBySearch",
                    "bFilter": false,
                    "bPaginate": false,
                    "bAutoWidth": true,
                    "bLengthChange": false,
                    "aoColumns": [{
                        "bVisible": false,
                        mData: "repayingPlanDetailId"
                    },
                    {
                        mData: "currentPeriod",
                        mRender: function(data, type, rowdata) {
                            if (rowdata.repayingPlanDetailId == null) {
                                return "合计：";
                            } else {
                                return rowdata.currentPeriod;
                            }
                        }
                    },
                    {
                        mData: "currentEndDate",
                        mRender: function(data, type, rowdata) {
                            if (rowdata.repayingPlanDetailId != null) {
                                var date = new Date(data);
                                //return date.toLocaleDateString();
                                //解决部分浏览器日期显示问题
                                return date.getFullYear()+"/"+(date.getMonth()+1)+"/"+date.getDate();
                            } else {
                                return "";
                            }
                        }
                    },
                    {
                        mData: "currentPrincipalInterest"
                    },
                    {
                        mData: "currentPrincipal"
                    },
                    {
                        mData: "currentInterest"
                    },
                    {
                        mData: "status",
                        mRender: function(data, type, rowdata) {
                            if (data == null) {
                                return "";
                            } else {
                                return dataDict.PlanStatus[data];
                            }
                        }
                    },
                    {
                        mData: "repayingPlanId",
                        mRender: function(data, type, rowdata) {
                            if (data == null) {
                                return "";
                            } else {
                                var buttons = "-";
                               // buttons += "<button role='edit' data-id='" + rowdata.repayingPlanDetailId + "' class='btn btn-xs btn-info' title='修改'><i class='ace-icon fa fa-edit'></i></button>";
                                return buttons;
                            }
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
                    	utils.num.tableFormat(this);//对表格内的纯数字列加逗号
                    	//utils.num.colsFormat(this,[4,5,6]);//对表格内指定数字列加逗号，索引从1开始
                    }
                    /* "fnFooterCallback": function( node , aData, iStart, iEnd, aiDisplay ) {
                	for(var i=0; i<aData.length; i++) {
                		currentPrincipalInterestTotal += aData[i].currentPrincipalInterest;
                		currentPrincipalTotal += aData[i].currentPrincipal;
                		currentInterestTotal += aData[i].currentInterest;
                	}
                  }*/
                });
                viewSelf.oTable = oTable;
            });
        },
        editBtnLive: function() {
            var viewSelf = this;
            $(document).on("click", "button[role='edit']",
            function(e) { // 动态绑定所有role=sign按钮的click事件
                var $this = $(this);
            });
        },
        downloadRpd:function(){//下载还款计划
        	var contractId=$("#contractIdField").val();
        	utils.downloadFile($$ctx+"repayPlan/downloadRepayingPlan?contractId="+contractId);
        }
    });
    module.exports = view;
});