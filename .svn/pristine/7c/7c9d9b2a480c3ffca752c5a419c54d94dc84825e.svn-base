/**
 * 贷款发放页面的js
 * */


define(function(require, exports, module) {
    var model = require("./model");
    var utils = require("utils");

    var view = Backbone.View.extend({
        el: "body",
        events: {
            "click #btn-query": "conditionQuery",
            "click #btn-reset": "resetSearchForm"
        },
        initialize: function() {
            /** 初始化 */
            this.model = new model();
            this.render();
        },
        render: function() {
            /** 页面渲染 */
            this.perparations();
            this.initDataTables();
            this.signBtnLive();
            this.trashBtnLive();
            this.detailBtnLive();
            this.updateBtnLive();
        },
        perparations: function() {
            $("#hasUploadIou").val(null);
            $("#payloanStatus").val(null);
        },
        resetSearchForm: function() {
            $("#exec_search_form").resetForm();
            $("#hasUploadIou").val(null);
            $("#payloanStatus").val("");
        },
        initDataTables: function() {
            /** 初始化DataTables */
            var viewSelf = this;
            var oTable = $("#tbContracts").dataTable({
                "sAjaxSource": "contractMng/findBySearch",
                "bFilter": false,
                "bAutoWidth": true,
                //"sScrollX":'30%',
                "bLengthChange": false,
                "aoColumns": [null, null, null, null, null, null, null, null, null, null],
                "aoColumnDefs": [{
                    "bVisible": false,
                    "aTargets": [0]
                },
                {
                    "aTargets":  [9],
                    "mRender": function(data, type, full) {
                        var buttons = "";
                        if (full[10] == "300" || full[10] == "316") {
                            buttons = "<button type='button' role='sign' data-id='" + full[0] + "' class='btn btn-xs btn-success' title='登记放款'><i class='ace-icon fa fa-plus'></i></button> " + "<button type='button' role='edit' data-id='" + full[0] + "' class='btn btn-xs btn-yellow' title='查看还款计划'><i class='ace-icon fa fa-eye'></i></button> ";
                        } else {
                            buttons = "<button type='button' role='sign' data-id='" + full[0] + "' class='btn btn-xs btn-success' title='登记放款' disabled='disabled'><i class='ace-icon fa fa-plus'></i></button> " + "<button type='button' role='edit' data-id='" + full[0] + "' class='btn btn-xs btn-yellow' title='查看还款计划' disabled='disabled'><i class='ace-icon fa fa-eye'></i></button> ";
                        }

                        buttons += "<button type='button' role='detail' data-id='" + full[0] + "' class='btn btn-xs btn-yellow' title='查看详情'><i class='ace-icon fa fa-eye'></i></button> " + "<button type='button' role='trash' data-id='" + full[0] + "' class='btn btn-xs btn-danger' title='作废合同'><i class='ace-icon fa fa-trash-o'></i></button>";

                        buttons += "<input type='hidden' value='" + data + "'  />"

                        return buttons;
                    }
                },
                {
                    "bVisible": false,
                    "aTargets": [10]
                }],
                "fnServerParams": function(aoData) {
                    aoData.push({
                        "name": "cuserId",
                        "value": $('#cuserIdField').val()
                    });
                    aoData.push({
                        "name": "customerName",
                        "value": $('#customerName').val()
                    });
                    aoData.push({
                        "name": "payloanStatus",
                        "value": $('#payloanStatus').val()
                    });
                    aoData.push({
                        "name": "hasUploadIou",
                        "value": $('#hasUploadIou').val()
                    });
                },
                "fnDrawCallback" : function(){
                	var count = $("#tbContracts tr").length;
                	$("#tbContracts tr:gt("+ parseInt(count/2) +")").find("div.btn-group").addClass("dropup");
                	//utils.num.tableFormat(this);//对表格内的纯数字列加逗号
                	utils.num.colsFormat(this,[4,5,6]);//对表格内指定数字列加逗号，索引从1开始
                	$(this).find("tr,th").attr("style","white-space:nowrap;");
                	$(this).wrap("<div style='overflow-x: auto;overflow-y: hidden;'></div>")
                }
               
            });

            viewSelf.oTable = oTable;

        },
        conditionQuery: function() {
            /**条件查询按钮点击的事件*/
            var oTable = $("#tbContracts").dataTable();
            oTable.fnSettings()._iDisplayStart = 0;
            oTable.fnDraw();
        },
        signBtnLive: function() {
            var viewSelf = this;
            $(document).on("click", "button[role='sign']",
            function(e) { // 动态绑定所有role=sign按钮的click事件
            	e.preventDefault();
                var $this = $(this);
                window.location.href = "contractList/signList?contractId=" + $this.data("id") + "&cuserId=" + $("#cuserIdField").val();
            });
        },
        updateBtnLive: function() {
            var viewSelf = this;
            $(document).on("click", "button[role='edit']",
            function(e) { // 动态绑定所有role=sign按钮的click事件
                var $this = $(this);
                e.preventDefault();
                viewSelf.model.updateContract({
                    contractId: $this.data("id")
                },
                function(data) {

                    if (data == "error") {
                    	utils.alert.warn( "<strong>此合同状态并非已放款！</strong>");
                    } else {
                        window.location.href = data;
                    }
                });
            });
        },
        trashBtnLive: function() { // 查看按钮事件
            var viewSelf = this;
            $(document).on("click", "button[role='trash']",
            function(e) { // 动态绑定所有删除按钮的click事件
                var $this = $(this);
                e.preventDefault();
                if (bootbox.confirm({
                    message: "确定要作废此合同吗 ?",
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
                            viewSelf.model.abolishContract({
                                contractId: $this.data("id")
                            },
                            function(data) {
                                if (data == "success") {
                                	var oTable = utils.datatable.fresh("#tbContracts");
                                    //bootbox.alert(_success + "<strong>合同废除成功！</strong>");
                                } else if (data == "contractAlreadyAbolishedError") {
                                	utils.alert.warn("<strong>该合同已经被废除过了！</strong>");
                                } else if (data == "contractNotSignedError") {
                                	utils.alert.warn("<strong>此合同不是已签订,请重新选择合同！</strong>");
                                }
                            });
                        }
                    }
                })){}
            });
        },
        detailBtnLive: function() { // 查看按钮事件
            var viewSelf = this;
            $(document).on("click", "button[role='detail']",
            function(e) { // 动态绑定所有删除按钮的click事件
            	e.preventDefault();
                var $this = $(this);
                var nRow = $this.parents('tr')[0];
                var idInput = $(nRow).find('input[type="hidden"]');
                var projectId = idInput[0].value;
                viewSelf.model.checkWorkflowType({
                	"projectId" : projectId	
                },function(r){
                	if(r.success){
                		window.location.href = $$ctx + "contractMng/viewDetail?projectId=" + projectId;
                	}else{
                		utils.alert.warn("<strong>"+r.msg+"</strong>");
                	}
                });
            });
        }
    });
    module.exports = view;
});