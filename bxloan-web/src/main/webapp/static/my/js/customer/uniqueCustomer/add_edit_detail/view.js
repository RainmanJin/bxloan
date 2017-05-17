/**
 * 客户表单-受薪
 * 
 * author: lijing
 * lastModified: lijing 2014-08-05 16:30:24
 */


define(function(require, exports, module) {
    var model = require("./model");
    var rmgk = require("./rm_gk");
    var rmcw = require("./rm_cw");
    var rmlxr = require("./rm_lxr");
    var rmjdcw = require("./rm_jdcw");
    var utils = require("utils");

    var view = Backbone.View.extend({
        el: "body",
        events: {
            "click #btn-query": "condQueryWd",
            "click #btn-getSelectedNode": "getSelectedNode",
            "click #addCw": "addCw",
            "click #addLxr": "addLxr",
            "click #comeToAlive": "beAlive",
            "click #show-tree": "ToggleTree",
            "click #addWd" : "initUploadBtn",
            "change #familyFriendType": "changeFamilyFriendForm",
            "click #add-Lxr-submit": "addFamily",
            "click #mod-Lxr-submit": "modFamily",
            "click #add-Wd-submit": "submitDocument",
            "click #saveGKXX": "gkgzFormSubmit",
            "click #saveJYCW": "jdcwFormSubmit"
        },
        initialize: function() {
            /** 初始化 */
            this.model = new model();
            this.render();
        },
        render: function() {
            /** 页面渲染 */
            this.initFirstOfAll();
            this.initNationAreaCascade();
            this.pdownloadBtnLive();
            this.editBtnLiveCw();
            this.editBtnLiveLxr();
            this.detailBtnLivePcw();
            this.detailBtnLiveCw();
            this.detailBtnLiveLxr();
            this.deleteBtnLiveCw();
            //this.initUploadBtn();
            this.deleteBtnLiveLxr();
            this.downloadBtnLiveWd();
            this.deleteBtnLiveWd();
            this.initWorkType();
            this.initDataTablesForCw();
            this.initDataTablesForPcw();
            this.initDataTablesForLxr();
            this.initDataTablesForDI();
            this.initCustDocTable();
            this.initTree();
            this.initJDCWForm();
            this.initGKGZForm();
            this.initCwForm();
            this.initLxrForm();
            //this.initEndOfAll();
            this.initBirthday();
        },
        initFirstOfAll: function() {
            /**所有渲染开始之前所做的操作*/
            if ($("#customerIdField").val() != "" && $("#customerIdField").val() != null) {
                $("#partyIdField").val($("#customerIdField").val());
            }

            //$("#hiddenForFamilyCertificateType").val("100");

            if ($("#hiddenForConsultLocation").val() == "contract") {
                $("#goBackButton").hide();
            }
            //utils.num.numberFormat();
            
        },
        initDataTablesForCw: function() {
            /** CW初始化DataTables */
            var viewSelf = this;
            /**
			 * 财务管理列表
			 * */
            var oTable = $("#tbCw").dataTable({
                "sAjaxSource": $$ctx + "singleCustomer/findAccount",
                "bFilter": false,
                "bAutoWidth": true,
                "bLengthChange": false,
                "aoColumns": [{
                    "bVisible": false,
                    "mData": "accountId"
                },
                {
                    "mData": "accountName"
                },
                {
                    "mData": "bankAccount"
                },
                {
                    "mData": "accountNum"
                },
                {
                    "mData": "accountPhone"
                },
                {
                    "mData": "accountStatus",
                    mRender: function(data, type, rowdata) {
                        switch (rowdata.accountStatus) {
                        case 1:
                            {
                                return "使用"
                                break;
                            }
                        case 2:
                            {
                                return "未使用"
                                break;
                            }
                        default:
                            {
                                return "显示错误"
                                break;
                            }
                        }
                    }
                },
                {
                    "mData": "partyId",
                    mRender: function(data, type, rowdata) {
                        if ($("#workTypeField").val() == "TODETAIL") {
                            return  "—";//"<button type='button' role='detail_cw' data-id='" + rowdata.accountId + "' class='btn btn-xs btn-yellow'><i class='ace-icon fa fa-eye' title='查看'></i></button> ";
                        } else {
                            var buttons = "<button type='button' role='edit_cw' data-id='" 
                            	        + rowdata.accountId + "' class='btn btn-xs btn-info'><i class='ace-icon fa fa-edit' title='修改'></i></button> " 
                            	        //+ "<button type='button' role='detail_cw' data-id='" + rowdata.accountId + "' class='btn btn-xs btn-yellow' title='查看' ><i class='ace-icon fa fa-eye'></i></button> " 
                            	        + "<button type='button' role='delete_cw' data-id='" + rowdata.accountId + "' class='btn btn-xs btn-danger'><i class='ace-icon fa fa-trash-o' title='删除'></i></button>";
                            return buttons
                        }

                    }
                }],
                "fnServerParams": function(aoData) {
                    aoData.push({
                        "name": "partyId",
                        "value": $('#partyIdField').val()
                    });
                },
                "oLanguage": {
                    "oPaginate": {
                        "sFirst": " <首页> ",
                        "sPrevious": " <前一页> ",
                        "sNext": " <后一页> ",
                        "sLast": " <尾页> "
                    }
                }
            });
            viewSelf.oTable = oTable;
        },
        initDataTablesForPcw: function() {
            /** CW初始化DataTables */
            var viewSelf = this;
            /**
  			 * 财务查看列表
  			 * */
            var oTable = $("#tbProjectCw").dataTable({
                "sAjaxSource":$$ctx + "singleCustomer/findProjectFinalcials",
                "bFilter": false,
                "bAutoWidth": true,
                "bLengthChange": false,
                "aoColumns": [null, null, null, null, null, null],
                "aoColumnDefs": [{
                    "bVisible": false,
                    "aTargets": [0]
                },
                {
                    "aTargets": [5],
                    "mRender": function(data, type, full) {
                        var buttons = "<button type='button' role='detail_pcw' data-id='" + full[0] + "'  class='btn btn-xs btn-yellow' title='查看'><i class='ace-icon fa fa-eye'></i></button> ";
                        return buttons;
                    }
                }
                ],
                "fnServerParams": function(aoData) {
                    aoData.push({
                        "name": "partyId",
                        "value": $('#partyIdField').val()
                    });
                },
                "fnDrawCallback": function (oSettings) { 
                	if($('#tbProjectCw tbody tr td').html()== "没有符合条件的记录"){
            			$('#tbProjectCw tbody tr td').html("<p class='text-warning'>没有查找到记录。若客户之前录入过财务信息，该表会有所显示，无需信贷员填写</p>") ;
                	}
                }
            });
            viewSelf.oTable = oTable;
        },
        initDataTablesForLxr: function() {
            /** CW初始化DataTables */
            var viewSelf = this;
            /**
			 * 联系人管理列表
			 * */
            utils.dd.initDataDict(["CorrelativeCustomerTypeCd", "LPCertificateType"],
            function(dataDict) {
                var oTable = $("#tbLxr").dataTable({
                    "sAjaxSource": $$ctx + "singleCustomer/findFamilyFriends",
                    "bFilter": false,
                    "bAutoWidth": true,
                    "bLengthChange": false,
                    "aoColumns": [{
                        "bVisible": false,
                        "mData": "correlativeRelationsId"
                    },
                    {
                        "mData": "name"
                    },
                    {
                        "mData": "familyFriendType",
                        mRender: function(data, type, rowdata) {
                            return dataDict.CorrelativeCustomerTypeCd[data];
                        }
                    },
                    {
                        "mData": "certificateTypeCd",
                        mRender: function(data, type, rowdata) {
                        	if(data!=""&&data!=null){
                        		return dataDict.LPCertificateType[data];
                        	}else{
                        		return "";
                        	}
                        }
                    },
                    {
                        "mData": "certificateCd"
                    },
                    {
                        "mData": "nowLiveAddress"
                    },
                    {
                        "mData": "telphone"
                    },
                    {
                        "mData": "companyTel"
                    },
                    {
                        "mData": "workCompany"
                    },
                    {
                    	"bVisible": false,
                        "mData": "companyAddress"
                    },
                    {
                        "mData": "partyId",
                        mRender: function(data, type, rowdata) {
                            if ($("#workTypeField").val() == "TODETAIL") {
                                return "<button type='button' role='detail_lxr' data-id='" + rowdata.correlativeRelationsId + "' class='btn btn-xs btn-yellow' title='查看'><i class='ace-icon fa fa-eye'></i></button> ";
                            } else {
                                var buttons = "<button type='button' role='edit_lxr' data-id='" + rowdata.correlativeRelationsId + "' class='btn btn-xs btn-info' title='修改'><i class='ace-icon fa fa-edit' ></i></button> " + "<button type='button' role='detail_lxr' data-id='" + rowdata.correlativeRelationsId + "' class='btn btn-xs btn-yellow' title='查看'><i class='ace-icon fa fa-eye'></i></button> " + "<button type='button' role='delete_lxr' data-id='" + rowdata.correlativeRelationsId + "' class='btn btn-xs btn-danger' title='删除'><i class='ace-icon fa fa-trash-o'></i></button>";
                                return buttons;
                            }
                        }
                    }],
                    "fnServerParams": function(aoData) {
                        aoData.push({
                            "name": "partyId",
                            "value": $('#partyIdField').val()
                        });
                    }
                });
                viewSelf.oTable = oTable;
            });
        },
        initDataTablesForDI: function() {
            /** DI初始化DataTables */
            var viewSelf = this;
            /**
			 * 文档管理列表
			 * */
            utils.dd.initDataDict([/*"CustomerDocType"*/ "CustProjectAllDocType","DocumentType", "CreateType"],
            function(dataDict) {
                var oTable = $("#tbWd").dataTable({
                    "sAjaxSource": $$ctx + "singleCustomer/findDocuments",
                    "bFilter": false,
                    "bAutoWidth": true,
                    "bLengthChange": false,
                    "aoColumns": [null, null, null, null, null, null, null, null, null],
                    "fnServerParams": function(aoData) {
                        aoData.push({
                            "name": "partyId",
                            "value": $('#partyIdField').val()
                        },
                        {
                            "name": "query_documentName",
                            "value": $('#query_documentName').val()
                        },
                        {
                       	 	"name": "query_contentType",
                       	 	"value": $("#query_contentType").val()
                        }
                        );
                    },
                    "aoColumnDefs": [{
                        "aTargets": [0],
                        mRender: function(data, type, rowdata) {
                        	var cb  = "<input type='checkbox' name='documentNums' value='"+ rowdata[8] +"' />";
                        	return cb;
                        }
                    },
                    {
                        "bVisible": false,
                        "aTargets": [4]
                    },
                    {
                        "aTargets":  [1],
                        mRender: function(data, type, rowdata) {
                            if ((data + "").length > 24) {
                                return data.substr(0, 24) + "...";
                            } else {
                                return data;
                            }

                        }
                    },
                    {
                        "aTargets":  [3],
                        mRender: function(data, type, rowdata) {
                            if (data == null) {
                                return "未填写";
                            } else {
                                return dataDict.DocumentType[data];
                            }

                        }
                    },
                    {
                        "aTargets":  [2],
                        mRender: function(data, type, rowdata) {
                            if (data == null) {
                                return "未填写";
                            } else {
                                return dataDict.CustProjectAllDocType[data];
                            }

                        }
                    },
                    {
                        "aTargets":  [7],
                        mRender: function(data, type, rowdata) {
                            return dataDict.CreateType[data];
                        }
                    },
                    {
                        "aTargets":  [8],
                        "mRender": function(data, type, full) {
                            if ($("#workTypeField").val() == "TODETAIL") {
                                return "<button type='button' role='download_wd' data-id='" + data + "'  class='btn btn-xs btn-yellow' title='调阅文档'><i class='ace-icon fa fa-download'></i></button> ";
                            } else {
                                var buttons = "<button type='button' role='download_wd' data-id='" + data + "'  class='btn btn-xs btn-yellow' title='调阅文档'><i class='ace-icon fa fa-download'></i></button> " + "<button type='button' role='delete_wd' data-id='" + data + "'  class='btn btn-xs btn-danger'><i class='ace-icon fa fa-trash-o' title='删除文档'></i></button> ";
                                return buttons;
                            }
                        }
                    }]
                });
                viewSelf.oTable = oTable;
            });
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
        		var root_upload = $("#uploadPathField").val();
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
        initWorkType: function() {
            /** 客户列表的修改与查看会进入此方法 */
            var viewSelf = this;
            var customerId = $("#partyIdField").val();
            var workType = $("#workTypeField").val();
            if (workType == "" || workType == null) {
                return false;
            } else if (workType == "TOMOD" || workType == "TOADD") { //针对于添加和修改操作的回显
                $("#cwglSpan").show();
                $("#wdSpan").show();
                $("#lxrSpan").show();
               
                viewSelf.model.findOneCustomer({
                    customerId: customerId
                },
                function(data) {
                    if (data != null) {
                        //概况信息与工作资料的反显
                        $.each($("#form-gkxx").find("input[type='text'], select, textarea"),
                        function() {
                        	if($(this).attr("name")=='birthday'){
                        		//var date = new Date(data[$(this).attr("name")]);
                                //var date_str = date.getFullYear()+"/"+(date.getMonth()+1)+"/"+date.getDate();
                        		//$(this).val(date_str);
                        	}else{
                        		$(this).val(data[$(this).attr("name")]);
                        	}
                        });
                        
                        //如果证件类型是身份证，出生日期反显
                        if(data["certificateTypeCd"] == 100){
                        	var _idcard = data['certificateNum'];
                        	var _birth = _idcard.substring(6, 10) + "/" + _idcard.substring(10, 12) + "/" + _idcard.substring(12, 14); 
                        	$("#form-gkxx input[name='birthday']").val(_birth);
                        }
                        //雇佣类型修改 modify by HWL start 20150609
                        if(data["employmentType"]){
                        	$("#employmentType").val(data["employmentType"]);
                        }else{
                        	 $("#employmentType").val("");
                        }
                        /*if (data["employmentType"] == 1) {
                            $("#employmentType").val("1");
                        } else if (data["employmentType"] == 2) {
                            $("#employmentType").val("2");
                        } else if (data["employmentType"] == 4) {
                            $("#employmentType").val("4");
                        }else {
                            $("#employmentType").val(null);
                        }*/
                      //雇佣类型修改 modify by HWL end 20150609
                        var marktype = data["markType"];
                       
                        var strs = marktype.split(",");
                        $("input[type='checkbox'][name='markType']").val(strs);
                        
                        var checked =  $("#businessField").val();
                        if(checked == "business"){
                        	$("input[type='checkbox'][name='markType']").prop("checked",false);
                        	$("input[type='checkbox'][name='markType'][value='02']")[0].checked = "checked";
                        	$("input[type='checkbox'][name='markType'][value='02']").attr("onclick","return false");
                        }
                        
                        //下拉列表不可选，但能看到下拉项
                        var sel = document.getElementById("certificateTypeCd");
                        for (var i = 0; i < sel.options.length; i++) {
                            if (sel.options[i].value == $("#certificateTypeCd option:selected").val()) {
                                var opt = "<option value='"+ sel.options[i].value +"'>"+ sel.options[i].text +"</option>";
                                
                            	var selValue = sel.options[i].value;
                                var selText = sel.options[i].text;
                                sel.innerHTML = "";
                                sel.options[0] = new Option(selText, selValue);
                                //sel.appendChild(opt);
                            }
                        }
                        //$("select[id='certificateTypeCd']").attr("disabled", "disabled");
                        $("input[name='customerNum'][id='csnm']").attr("readonly", "readonly");
                        $("#form-gkxx input[name='certificateNum']").attr("readonly", "readonly");
                        $("select[name='certificateTypeCd'][id='certificateTypeCd']").attr("readonly", "readonly");
                        $("input[id='industryCdMask']").attr("readonly", "readonly");
                        $("input[name='genderCd'][value=" + data.genderCd + "]").prop("checked", true);
                        $("input[name='payFundInd'][value=" + data.payFundInd + "]").prop("checked", true);
                        $("input[name='paySocialSecurityInd'][value=" + data.paySocialSecurityInd + "]").prop("checked", true);
                        $("input[name='censusType'][value=" + data.censusType + "]").prop("checked", true);
                        $("select[name='houseCondition'][value=" + data.houseCondition + "]").prop("checked", true);
                        $("input[name='houseShareCondition'][value=" + data.houseShareCondition + "]").prop("checked", true);
                        $("input[name='positiveInd'][value=" + data.positiveInd + "]").prop("checked", true);
                       
                        $("select[name='nationalityCd']").val(data.nationalityCd);
                        $("#nation").change();//更新下一级级联
                        $("select[name='provinceCd']").val(data.provinceCd);
                    	$("#province").change();//更新下一级级联
                    	$("select[name='cityCd']").val(data.cityCd);
                    	$("#city").change();//更新下一级级联
                    	$("select[name='countyCd']").val(data.countyCd);
                    	$(":input[name='permanentAddress']").val(data.permanentAddress);
                        // changeEmploymentType($("#employmentType")[0]);
                        try{changeHouseShareCondition();}catch(e){}
                        try{changeHouseCondition(data["houseCondition"]);}catch(e){}
                        $("#gkgzSpan").show();
                        if (data.status != "2") {
                        	 $("#showAlive").html(
                                     "<button class='btn btn-sm btn-primary' id='comeToAlive' type='button'>"
                     				+"<i class='ace-icon fa fa-arrow-right'>"
                     				+"</i>"
                     				+"生 效"
                     				+"</button>");
                        }
                    } else {
                    	utils.alert.err( "<strong>因未知错误，获取信息失败！</strong>");
                    }
                });
                //回显概况、工作资料 end
                //简单财务信息的反显
                viewSelf.model.findOneFinace({
                    customerId: customerId
                },
                function(data) {
                    if (data != null) {
                        //简单财务信息的反显
                        $.each($("#form-cwxx").find("input[type='text'], input[type='hidden'], select, textarea"),
                        function() {
                            $(this).val(data[$(this).attr("name")]);
                            $(this).removeAttr("readonly")
                        });
                        $("#industryCd").attr("readonly", "readonly");
                        $("#cwxxSpan").show();
                        //houseConditionChange($("#localHouseCondition")[0]);
                        try{carConditionChange($("#carCondition")[0]);
                       houseConditionChange($("#localHouseCondition")[0]);}catch(e){}
                    } else {
                    	utils.alert.err( "<strong>因未知错误，获取信息失败！</strong>");
                    }
                });
            } //else if end
            else if (workType == "TODETAIL") {
                $("#cwglSpan").hide();
                $("#wdSpan").hide();
                $("#lxrSpan").hide();
               
                viewSelf.model.findOneCustomer({
                    customerId: customerId
                },
                function(data) {
                    if (data != null) {
                        //概况信息与工作资料的反显
                        $.each($("#form-gkxx").find("input[type='text'], select, textarea"),
                        function() {
                            if($(this).attr("name")=='birthday'){
                        		//var date = new Date(data[$(this).attr("name")]);
                                //var date_str = date.getFullYear()+"/"+(date.getMonth()+1)+"/"+date.getDate();
                        		//$(this).val(date_str);
                        	}else{
                        		$(this).val(data[$(this).attr("name")]);
                        	}
                            $(this).attr("readonly", "readonly");
                        });
                        $.each($("#form-gkxx").find("input[type='radio'], select, textarea"),
                        function() {
                            $(this).attr("disabled", "disabled");
                        });
                        
                        var marktype = data["markType"];
                        var strs = marktype.split(",");
                        $("input[type='checkbox'][name='markType']").val(strs);
                        $("input[type='checkbox'][name='markType']").attr("onclick", "return false");
                        
                        
                        $("#btn-showTree").remove();
                        $("input[name='markType']").prop("disabled",true);
                        $("input[name='genderCd'][value=" + data.genderCd + "]").prop("checked", true);
                        $("input[name='payFundInd'][value=" + data.payFundInd + "]").prop("checked", true);
                        $("input[name='paySocialSecurityInd'][value=" + data.paySocialSecurityInd + "]").prop("checked", true);
                        $("input[name='censusType'][value=" + data.censusType + "]").prop("checked", true);
                        $("select[name='houseCondition'][value=" + data.houseCondition + "]").prop("checked", true);
                        $("input[name='houseShareCondition'][value=" + data.houseShareCondition + "]").prop("checked", true);
                        $("input[name='positiveInd'][value=" + data.positiveInd + "]").prop("checked", true);
                        $("#gkgzSpan").hide();
                        //changeEmploymentType($("#employmentType")[0]);
                        
                        $("select[name='nationalityCd']").val(data.nationalityCd);
                        $("#nation").change();//更新下一级级联
                        $("select[name='provinceCd']").val(data.provinceCd);
                    	$("#province").change();//更新下一级级联
                    	$("select[name='cityCd']").val(data.cityCd);
                    	$("#city").change();//更新下一级级联
                    	$("select[name='countyCd']").val(data.countyCd);
                    	$(":input[name='permanentAddress']").val(data.permanentAddress);
                    	$("#show-tree").prop("disabled", true);
                    	$("#lxrSpan_add").hide();
                    } else {
                    	utils.alert.err( "<strong>因未知错误，获取信息失败！</strong>");
                    }
                });
                viewSelf.model.findOneFinace({
                    customerId: customerId
                },
                function(data) {
                    if (data != null) {
                        //简单财务信息的反显
                        $.each($("#form-cwxx").find("input[type='text'], select, textarea"),
                        function() {
                            $(this).val(data[$(this).attr("name")]);
                            $(this).attr("readonly", "readonly");
                        });
                        $.each($("#form-cwxx").find("input[type='radio'], select, textarea"),
                        function() {
                            $(this).attr("disabled", "disabled");
                        });
                        $("#cwxxSpan").hide();
                    } else {
                    	utils.alert.err( "<strong>因未知错误，获取信息失败！</strong>");
                    }
                });
            }
        },
        initTree: function() {
            /**初始化树*/
            var viewSelf = this;
            $.fn.zTree.init($("#treeDemo"), {
                async: {
                    enable: true,
                    url: $$ctx + "singleCustomer/getAllIndustry"
                },
                data: {
                    simpleData: {
                        enable: true,
                        idKey: "industryTypeCd",
                        pIdKey: "parentIndustryTypeCd"
                    },
                    key: {
                        name: "industryTypeName"
                    }
                },
                check: {
                    enable: true,
                    chkStyle: "radio",
                    radioType: "all"
                },
                callback: {
                    onClick: function(event, treeId, treeNode) {

                        if (treeNode != null && treeNode.children != null && treeNode.children.length != null && treeNode.children.length > 0) {
                            $("#industryTypeCdField").val("");
                            $("#industryCdMask").val("");
                            $("#industryCd").val("");
                            return false;
                        } else {
                            $("#industryTypeCdField").val(treeNode.industryTypeCd);
                            $("#industryCdMask").val(treeNode.industryTypeName);
                            $("#industryCd").val(treeNode.industryTypeCd);
                            var treeObj = $.fn.zTree.getZTreeObj(treeId);
                            var node = treeObj.getNodeByParam("industryTypeCd", treeNode.industryTypeCd, null);
                            treeObj.checkNode(node, true, true);
                            var parentNode = node.getParentNode();
                            treeObj.expandNode(parentNode, true, false);
                            $("#controlZTree").toggle(300,
                            function() {});
                        }
                    },
                    beforeCheck: function(treeId, treeNode) {
                    	  $("#industryTypeCdField").val(treeNode.industryTypeCd);
                    	  $("#industryCdMask").val(treeNode.industryTypeName);
                          $("#industryCd").val(treeNode.industryTypeCd);
                        return ! treeNode.isParent;
                    },
                    onAsyncSuccess: function(event, treeId, treeNode, msg) {
                        var treeObj = $.fn.zTree.getZTreeObj(treeId);
                        var industryTypeCd = $("#industryTypeCdField").val();
                        if (industryTypeCd !== "") {
                            var node = treeObj.getNodeByParam("industryTypeCd", industryTypeCd, null);
                            treeObj.checkNode(node, true, true);

                            var parentNode = node.getParentNode();
                            treeObj.expandNode(parentNode, true, false);

                            $("#industryCdMask").val(node.industryTypeName);
                            $("#industryCd").val(node.industryTypeCd);
                        }
                    }
                }
            });
        },
        addCw: function() {
            $("#addAccountForm").resetForm();
            $("#add-modal-formCw div.modal-header h4").html("<i class='ace-icon fa fa-plus'></i> 新增账户");
            $("#add-modal-formCw").modal("show");
//            $("#add-modal-formCw .modal-content").draggable({
//            	scroll : false,
//            	revert : true
//            });
        },
        editBtnLiveCw: function() {
            var viewSelf = this;
            $(document).on("click", "button[role='edit_cw']",
            function(e) { // 动态绑定所有删除按钮的click事件
                var $this = $(this);
                $("#accountIdCwField").val($this.data("id"));
                viewSelf.model.findOneAccount({
                    accountId: $this.data("id")
                },
                function(data) {
                    if (data != null) {
                        $.each($("#modAccountForm").find("input[type='text'], select, textarea"),
                        function() {
                            $(this).val(data[$(this).attr("name")]);
                            $(this).removeAttr("readonly");

                        });
                        $("input[name='accountStatus']").removeAttr("disabled");
                        $("input[name='accountStatus'][value=" + data.accountStatus + "]").attr("checked", true);
                        $("#modCwSpan").show();
                        $("#mod-modal-formCw div.modal-header h4").html("<i class='ace-icon fa fa-plus'></i> 修改账户");
                        $("#mod-modal-formCw").modal("show");
                    } else {
                    	utils.alert.err("<strong>因未知错误，获取信息失败！</strong>");
                    }
                });
            });
        },
        detailBtnLivePcw: function() {
            var viewSelf = this;
            $(document).on("click", "button[role='detail_pcw']",
            function(e) { // 动态绑定所有删除按钮的click事件
                var $this = $(this);
                $.ajax({
                    cache: true,
                    type: "POST",
                    url: $$ctx + "singleCustomer/findOneSalaCustFinace",
                    data: {
                        "id": $this.data("id")
                    },
                    async: true,
                    error: function(request) {
                        alert("查找单一财务信息报错" + request);
                    },
                    success: function(data) {
                        if (data != null) {
                            $.each($("#detailProjectFinaceForm").find("input[type='text'], select, textarea"),
                            function() {
                                $(this).val(data[$(this).attr("name")]);
                                $(this).attr("readonly", "readonly");
                            });
                            $("#projectFinace_modal div.modal-header h4").html("<i class='ace-icon fa fa-plus'></i> 查看财务信息");
                            $("#projectFinace_modal").modal("show");
                        } else {
                        	utils.alert.err("<strong>因未知错误，获取信息失败！</strong>");
                        }
                    }
                });
                return false;
            });
        },
        detailBtnLiveCw: function() {
            /**财务查看按钮*/
            var viewSelf = this;
            $(document).on("click", "button[role='detail_cw']",
            function(e) { // 动态绑定所有删除按钮的click事件
                var $this = $(this);
                $("#accountIdCwField").val($this.data("id"));
                viewSelf.model.findOneAccount({
                    accountId: $this.data("id")
                },
                function(data) {
                    if (data != null) {
                        $.each($("#modAccountForm").find("input[type='text'], select, textarea"),
                        function() {
                            $(this).val(data[$(this).attr("name")]);
                            $(this).attr("readonly", "readonly");
                        });
                        $.each($("#modAccountForm").find("input[type='radio'], select, textarea"),
                        function() {
                            $(this).attr("disabled", "disabled");
                        });
                        $("input[name='accountStatus'][value=" + data.accountStatus + "]").attr("checked", true);
                        $("#modCwSpan").hide();
                        $("#mod-modal-formCw div.modal-header h4").html("<i class='ace-icon fa fa-plus'></i> 查看账户");
                        $("#mod-modal-formCw").modal("show");
                    } else {
                    	utils.alert.err("<strong>因未知错误，获取信息失败！</strong>");
                    }
                });
                return false;
            });
        },
        deleteBtnLiveCw: function() {
            var viewSelf = this;
            $(document).on("click", "button[role='delete_cw']",
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
                            viewSelf.model.delAccount(id,
                            function(data) {
                                if (data == "success") {
                                    var datatable = $("#tbCw").dataTable();
                                    datatable.fnSettings()._iDisplayStart = 0;
                                    datatable.fnDraw();
                                } else {
                                	utils.alert.err("<strong>删除失败!</strong>");
                                }
                            });
                        }
                    }
                }));
            });
        },
        initCwForm: function() {
        	var viewSelf = this;
            $("#addAccountForm").validate({
                rules: rmcw.rules,
                messages: rmcw.messages,
                submitHandler: function(form) {
                    /**财务添加按钮的提交方法*/
                    var str = $("#partyIdField").val();
                    $.ajax({
                        cache: true,
                        type: "POST",
                        url: $$ctx + "singleCustomer/addCustomerAccount?partyId=" + str,
                        data: $('#addAccountForm').serialize(),
                        async: false,
                        error: function(request) {
                            alert("添加账户信息报错" + request);
                        },
                        success: function(data) {
                            if (data == "success") {
                                //bootbox.alert("<strong>保存成功！</strong>");
                                $("#add-modal-formCw").modal("hide");
                                var oTable = $("#tbCw").dataTable();
                                oTable.fnDraw();
                            } else if (data == "accountHasRegistedError") {
                            	utils.alert.warn("<strong>该用户的账户已被添加过！</strong>")
                            } else {
                            	utils.alert.err( "<strong>因未知错误，保存失败！</strong>");
                            }
                        }
                    });
                },
                errorPlacement: function(error, element) {
                    if (element.is(":radio")) error.appendTo(element.parent());
                    else if (element.is(":checkbox")) error.appendTo(element.next());
                    else error.appendTo(element.parent());
                }
            });
            /**财务修改按钮的提交方法*/
            $("#modAccountForm").validate({
                rules: rmcw.rules,
                messages: rmcw.messages,
                submitHandler: function(form) {
                    var str = $("#partyIdField").val();
                    var account = $("#accountIdCwField").val();
                    $.ajax({
                        cache: true,
                        type: "POST",
                        url: $$ctx + "singleCustomer/modCustomerAccount?partyId=" + str + "&accountId=" + account,
                        data: $('#modAccountForm').serialize(),
                        // 
                        async: false,
                        error: function(request) {
                            alert("修改账户信息报错" + request);
                        },
                        success: function(data) {
                            if (data == "success") {
                                //bootbox.alert("<strong>保存成功！</strong>");
                                $("#mod-modal-formCw").modal("hide");
                                var oTable = $("#tbCw").dataTable();
                                oTable.fnDraw();
                                viewSelf.actAliveBtn();
                            } else {
                            	utils.alert.err("<strong>因未知错误，保存失败！</strong>");
                            }
                        }
                    });
                },
                errorPlacement: function(error, element) {
                    if (element.is(":radio")) error.appendTo(element.parent().next().next());
                    else if (element.is(":checkbox")) error.appendTo(element.next());
                    else error.appendTo(element.parent());
                }
            });
        },
        showTree: function() {
            $("#treeForIndustryCd").show();
        },
        condQueryWd: function() {
            var oTable = $("#tbWd").dataTable();
            oTable.fnSettings()._iDisplayStart = 0;
            oTable.fnDraw();
        },
        getSelectedNode: function() {
            /**选择树*/
            var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
            var nodes = treeObj.getCheckedNodes();
            if (nodes.length == 0) {
                alert("请选择一个行业！");
                return false;
            } else {
                $.each(nodes,
                function(i, node) {
                    $("#industryCdMask").val(node.industryTypeName);
                    $("#industryCd").val(node.industryTypeCd);
                    //					alert(node.industryTypeCd + " - " + node.industryTypeName);
                });
                return false;
            }
        },
        addLxr: function() {
            $("#familyFriendForm").resetForm();
            //$("#hiddenForLxrWorkType").val("ADD");
            $("#lxrSpan_mod").hide();
            $("#lxrSpan_add").show();
            $.each($("#familyFriendForm").find("input[type='text'], select, textarea"),
              function() {
               $(this).removeAttr("disabled").removeAttr("readonly");;
            });
            var viewSelf = this;
            viewSelf.changeFamilyFriendForm();
            $("#modal-formLxr div.modal-header h4").html("<i class='ace-icon fa fa-plus'></i> 新增联系人");
            $("#modal-formLxr").modal("show");
        },
        editBtnLiveLxr: function() {
            var viewSelf = this;
            $(document).on("click", "button[role='edit_lxr']",
            function(e) { // 动态绑定所有修改按钮的click事件
                var $this = $(this);
                $("#correlativeRelationsIdCwField").val($this.data("id"));
                $.ajax({
                    cache: true,
                    type: "POST",
                    url: $$ctx + "singleCustomer/findOneFamilyFriend",
                    data: {
                        "correlativeRelationsId": $this.data("id")
                    },
                    async: true,
                    error: function(request) {
                        alert("查找单一联系人信息报错" + request);
                    },
                    success: function(data) {
                        if (data != null) {
                            $.each($("#familyFriendForm").find("input[type='text'], select, textarea"),
                            function() {
                                $(this).val(data[$(this).attr("name")]);
                                $(this).removeAttr("disabled").removeAttr("readonly");;
                            });
                            //$("#hiddenForLxrWorkType").val("MOD");
                            $("#lxrSpan_mod").show();
                            $("#lxrSpan_add").hide();
                            viewSelf.changeFamilyFriendForm();
                            $("#lxrSpan").show();
                            $("#modal-formLxr div.modal-header h4").html("<i class='ace-icon fa fa-plus'></i> 修改联系人");
                            $("#modal-formLxr").modal("show");
                        } else {
                        	utils.alert.err("<strong>因未知错误，获取信息失败！</strong>");
                        }
                    }
                });
                return false;
            });
        },
        deleteBtnLiveLxr: function() {
            var viewSelf = this;
            $(document).on("click", "button[role='delete_lxr']",
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
                            viewSelf.model.deleteLxr(id, function(data){
                            	if (data.success) {
                                    //bootbox.alert(_success + "<strong>删除成功!</strong>",function() {});
                                    var datatable = utils.datatable.fresh("#tbLxr");
                                    viewSelf.actAliveBtn();
                                } else {
                                	utils.alert.warn("<strong>"+data.msg+"</strong>");
                                }
                            });
                        }
                    }
                }));
                return false;
            });
            return false;
        },
        detailBtnLiveLxr: function() {
            var viewSelf = this;
            $(document).on("click", "button[role='detail_lxr']",
            function(e) { // 动态绑定所有删除按钮的click事件
                var $this = $(this);
                $.ajax({
                    cache: true,
                    type: "POST",
                    url: $$ctx + "singleCustomer/findOneFamilyFriend",
                    data: {
                        "correlativeRelationsId": $this.data("id")
                    },
                    // 
                    async: true,
                    error: function(request) {
                        alert("查找单一账户信息报错" + request);
                    },
                    success: function(data) {
                        if (data != null) {
                            $.each($("#familyFriendForm").find("input[type='text'], select, textarea"),
                            function() {
                                $(this).val(data[$(this).attr("name")]);
                                $(this).attr("readonly", "readonly");
                            });
                            $.each($("#familyFriendForm").find("input[type='radio'], select, textarea"),
                            function() {
                                $(this).attr("disabled", "disabled");
                            });
                            //$("#lxrSpan").hide();
                            $("#modal-formLxr div.modal-header h4").html("<i class='ace-icon fa fa-plus'></i> 查看联系人");
                            $("#modal-formLxr").modal("show");
                        } else {
                        	utils.alert.err("<strong>因未知错误，获取信息失败！</strong>");
                        }
                    }
                });
                return false;
            });
        },
        downloadBtnLiveWd: function() {
            var viewSelf = this;
            $(document).on("click", "button[role='download_wd']",
            function(e) {
                var root_upload = $("#uploadPathField").val();
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
                        alert("查找下载路径出错" + request);
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
                                url: $$ctx + 'singleCustomer/delDocument/' + id,
                                dataType: 'HTML',
                                type: 'POST',
                                success: function(data) {
                                    if (data == "success") {
                                        //bootbox.alert("<strong>删除成功!</strong>",function() {});
                                        var a = utils.datatable.fresh("#tbWd");
                                        viewSelf.actAliveBtn();
                                    } else if (data == "createTypeCdEquals2Error") {
                                    	utils.alert.warn("<strong>该文档关联方式为引用，不允许删除，请联系管理员来操作!</strong>");
                                    } else if (data == "createTypeCdEquals1AndIsCustomerError") {
                                    	utils.alert.warn("<strong>该文档为客户文档，不允许在此处删除，请在客户管理下进行删除操作！</strong>");
                                    } else {
                                    	utils.alert.err("<strong>删除失败!</strong>");
                                    }
                                }
                            });
                        }
                    }
                }));
                return false;
            });
        },
        initLxrForm: function() {
            /**联系人表单的初始化*/
            $("#familyFriendForm").validate({
                rules: rmlxr.rules,
                messages: rmlxr.messages,
                errorPlacement: function(error, element) {
                    if (element.is(":radio")) error.appendTo(element.parent().next().next());
                    else if (element.is(":checkbox")) error.appendTo(element.next());
                    else error.appendTo(element.parent());
                }
            });
       },
       addFamily: function(){
    	   var viewSelf = this;
    	   if($("#familyFriendForm").valid()){
    		   var str = $("#partyIdField").val();
               $.ajax({
                   cache: true,
                   type: "POST",
                   url: $$ctx + "singleCustomer/addFamilyFriend?partyId=" + str,
                   data: $('#familyFriendForm').serialize(),
                   // 
                   async: false,
                   error: function(request) {
                       alert("添加联系人信息报错" + request);
                   },
                   success: function(data) {
                       if (data == "success") {
                          // bootbox.alert("<strong>保存成功！</strong>");
                           $("#modal-formLxr").modal("hide");
                           var oTable = $("#tbLxr").dataTable();
                           oTable.fnDraw();
                           viewSelf.actAliveBtn();
                       } else if (data == "telephoneNumSameError") {
                    	   utils.alert.warn("<strong>联系人的主要手机号不能与客户本人相同！</strong>");
                       } else if (data == "certificateNumSameError") {
                    	   utils.alert.warn("<strong>联系人的证件号不能与客户本人相同！</strong>");
                       } else if (data == "customerConditionNotSaveError") {
                    	   utils.alert.warn("<strong>请先填写客户的概况信息！</strong>");
                       } else if (data == "customerNotMarryingError") {
                    	   utils.alert.warn("<strong>非已婚状态不可添加配偶的信息！</strong>");
                       } else if (data == "coupleHasBeenRegistedError") {
                    	   utils.alert.warn("<strong>配偶已经登入过了！</strong>");
                       } else {
                    	   utils.alert.err("<strong>因未知错误，保存失败！</strong>");
                       }
                   }
               });
    	   }
       },
       modFamily: function(){
    	   var viewSelf = this;
    	   if($("#familyFriendForm").valid()){
    		   var str = $("#partyIdField").val();
               var correlativeRelations = $("#correlativeRelationsIdCwField").val();
               $.ajax({
                   cache: true,
                   type: "POST",
                   url: $$ctx + "singleCustomer/modFamilyFriend?partyId=" + str + "&correlativeRelationsId=" + correlativeRelations,
                   data: $('#familyFriendForm').serialize(),
                   // 
                   async: false,
                   error: function(request) {
                       alert("修改联系人信息报错" + request);
                   },
                   success: function(data) {
                       if (data == "success") {
                           //bootbox.alert("<strong>保存成功！</strong>");
                           var oTable = $("#tbLxr").dataTable();
                           oTable.fnDraw();
                           $("#modal-formLxr").modal("hide");
                           viewSelf.actAliveBtn();
                       } else if (data == "telephoneNumSameError") {
                    	   utils.alert.warn("<strong>联系人的主要手机号不能与客户本人相同！</strong>");
                       } else if (data == "certificateNumSameError") {
                    	   utils.alert.warn("<strong>联系人的证件号不能与客户本人相同！</strong>");
                       } else if (data == "customerConditionNotSaveError") {
                    	   utils.alert.warn("<strong>请先填写客户的概况信息！</strong>");
                       } else if (data == "customerNotMarryingError") {
                    	   utils.alert.warn("<strong>非已婚状态不可添加配偶的信息！</strong>");
                       } else if (data == "coupleHasBeenRegistedError") {
                    	   utils.alert.warn("<strong>配偶已经登入过了！</strong>");
                       } else {
                    	   utils.alert.err("<strong>因未知错误，保存失败！</strong>");
                       }
                   }
               });
    	   }
       },
       initBirthday:function(){
			//将datepicker控件放在最顶层
			var dateOnTop = function(){$(".datepicker").css("z-index","99999");};
			var defaultDateConf = {
				autoclose : true,
				todayHighlight : true,
				clearBtn : true,
				endDate : 'd',
				defaultDate: $("#birthday").val()
			};
			
			var initDateP = function(selector,changeCallBack,config){
				var _dateConf = config || defaultDateConf;
				$(selector).datepicker(_dateConf)
				.on("changeDate",changeCallBack)
				.on("click",dateOnTop);
			};
			
			initDateP("#birthday");
		},
        initGKGZForm: function() {
            /**概况、工作资料的表单提交*/
            $("#form-gkxx").validate({
                rules: rmgk.rules,
                messages: rmgk.messages,
                errorPlacement: function(error, element) {
                    if (element.is(":radio")){error.appendTo(element.parent());}
                    else if (element.is(":checkbox")){error.appendTo(element.parent().parent().parent().next());}
                    else if (element.is("input[name='industryCdMask']")) {
                        error.appendTo(element.parent().parent());
                    }
                    else if (element.next().is("span[class='input-group-addon']")) {
                    	error.appendTo(element.parent().parent().parent());
                    }
                    else {
                    	error.appendTo(element.parent());
                    } 
                }
            });
        },
        initJDCWForm: function() {
            /**初始化表单*/
            /**简单财务信息的表单提交验证*/
            $("#form-cwxx").validate({
                rules: rmjdcw.rules,
                messages: rmjdcw.messages,
                errorPlacement: function(error, element) {
                    if (element.is(":radio")){error.appendTo(element.parent().next().next());}
                    else if (element.is(":checkbox")){error.appendTo(element.parent().parent().parent().next());}
                    else if (element.next().is("span[class='input-group-addon']")) {
                    	error.appendTo(element.parent().parent().parent());
                    }
                    else if (element.is(":hidden")) {
                    	error.appendTo(element.parent().parent().parent());
                    }
                    else {
                    	error.appendTo(element.parent());
                    } 
                }
            });
        },
        beAlive: function() {/**客戶生效*/
        	if($("#form-cwxx").valid()){
        		utils.button.ban("#comeToAlive");
        		$.ajax({
            		cache: true,
            		type: "POST",
            		url: $$ctx + "singleCustomer/takeEffect",
            		data: {
            			"partyId": $("#partyIdField").val()
            		},
            		async: false,
            		error: function(request) {
            			alert("客戶生效报错" + request);
            		},
            		success: function(data) {
            			utils.button.reset("#comeToAlive");
            			if (data.success) {
            				utils.alert.suc("<strong>此客戶已生效！点击确定跳转至客户管理界面。</strong>", function(result){
            					if($('#businessField').val() == 'business') {
            						$('#businessField').val('close');
            					} else {
            						window.location.href = $$ctx+"singleCustomer";
            					}
            				});
            			} else {
            				utils.alert.warn( "<strong>"+ data.msg +"</strong>");
            			}
            		}
            	}); //ajax end
        	}else{
        		utils.alert.warn("<strong>请确保财务资产信息与概况信息填写一致！</strong>");
        	}
        	return false;
        },
        changeFamilyFriendForm: function() {
            var viewSelf = this;
                if ($("#_familyFriendType").val() == "1") {
                    $("#add-schoolName").hide();
                    $("#add-jnsb").show();
                    $("#add-jngjj").show();
                } else if ($("#_familyFriendType").val() == "5") {
                    $("#add-jnsb").hide();
                    $("#add-jngjj").hide();
                    $("#add-schoolName").show();
                    $("#add-schoolAddress").show();
                } else {
                    $("#add-jnsb").show();
                    $("#add-jngjj").show();
                    $("#add-schoolName").show();
                    $("#add-schoolAddress").show();
                    return false;
                }
            
        },
        ToggleTree: function() {
            /**显示隐藏行业的树*/
            $("#controlZTree").toggle(300,
            function() {
                if (($("#controlZTree").attr("style")) == "") {} else if (($("#controlZTree").attr("style")) == "display: none;") {}
            });
        },
        gkgzFormSubmit: function() {
        	var viewSelf = this;
            if ($("#form-gkxx").valid()) {
            	utils.button.ban("#saveGKXX");
                $.ajax({
                    cache: false,
                    type: "POST",
                    url: $$ctx + "singleCustomer/updateIndividual?partyId=" + $("#partyIdField").val(),
                    data: $('#form-gkxx').serialize(),
                    // 
                    async: false,
                    error: function(request) {
                        alert("保存概况信息报错" + request);
                    },
                    success: function(data) {
                    	utils.button.reset("#saveGKXX");
                    	if(data!=null){
                    		if(data.success){
                    			if(data.data){
                    				utils.alert.suc("<strong>"+ data.msg +"</strong>", function(){
                    					viewSelf.model.deleteLxr(data.data, function(r){
                    						if(r.success){
                    							utils.alert.suc("<strong>"+ r.msg +"</strong>", function(){
                    								utils.datatable.fresh("#tbLxr");
                        							viewSelf.actAliveBtn();
                    							});
                    						}else{
                    							utils.alert.warn("<strong>"+ r.msg +"</strong>");	
                    						}
                    					});
                    				});
                    			}else{
                    				utils.alert.suc("<strong>"+ data.msg +"</strong>");	
                    			}
                    		}
                    		else{
                    			if(data.data&&data.data=="refresh"){
                    				utils.alert.warn("<strong>"+ data.msg +"</strong>", function(){
                    					//window.location.href = $$ctx + "/singleCustomer/backToForm?customerId="+$("#customerIdField").val()+"&workCode=TOMOD&consultLocation=customer"
                    					history.go(0);
                    				});
                    			}else{
                    				utils.alert.warn("<strong>"+ data.msg +"</strong>");
                    			}
                    		}
                    	}
                    }
                }); //ajax end
            }
        },
        jdcwFormSubmit: function() {
            if ($("#form-cwxx").valid()) {
            	utils.button.ban("#saveJYCW");
                $.ajax({
                    cache: false,
                    type: "POST",
                    url: $$ctx + "singleCustomer/updateFinance?partyId=" + $("#partyIdField").val(),
                    data: $('#form-cwxx').serialize(),
                    // 
                    async: true,
                    error: function(request) {
                        alert("保存财务信息报错" + request);
                    },
                    success: function(data) {
                    	utils.button.reset("#saveJYCW");
                        if (data == "success") {
                        	utils.alert.suc( "<strong>保存成功！</strong>");
                        } else {
                        	utils.alert.err("<strong>因未知错误，保存失败！</strong>");
                        }
                    }
                }); //ajax end
            }
        },
        initCustDocTable: function(){
        	var viewSelf = this;
        	viewSelf.model.findCustDocTypes({
        		"partyId" : $("#partyIdField").val()
        	},function(r){
        		if(r!=null){
        			var $tbody = $("#uploadTbody");
        			var count = 0;
        			var total = 0;
        			var html= "<tr>";
        			var map = r.custMap;
        			for (var key in map) {
        				total++;
        				count++;
        				var str = "<td>" +
        						   "<input type='radio' name='uploadDoc' value='"+ key +"' />" +
        				           "</td>"+
        				           "<td>" + 
        				           map[key]+
        				           "</td>"+
        				           "<td><span name='custSpan_"+ key +"'" +">" +
        				           "</span></td>";
        				 html+=str;
        				if(count==3||total==r.count){
        					html+="</tr>";
        					$tbody.html($tbody.html()+html);
        					count=0; html="<tr>";
        				}
        	        }  
        			
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
        submitDocument: function() {
            /**文件上传按钮*/
        	if($("#documentType").val()!=""){
        		$("#uploadFile").uploadify("upload","*"); // 批量上传
        	}else{
        		utils.alert.warn("<strong>请选择上传文档的种类！</strong>");
        	}
        },
        initUploadBtn: function() {
   		 var viewSelf = this;
            /**上传文档弹窗*/
            var formData = utils.upload.beforeUpload("singleCustomer/beforeUpdate",{"partyId": $("#partyIdField").val()} );
            var onStart = function(file, data){
            	data.custDocType = $("#tb_doc_selector input[name='uploadDoc']:checked").val();
            	data.documentNum = $("#documentNumDI").val();
            	data.allDocType = $("#allDocTypeField").val();//受薪类
            };
            var onOneSuc = function(){
            	utils.upload.beforeUpload("singleCustomer/beforeUpdate",{"partyId": $("#partyIdField").val()} );
                $("#documentType").val("");
                utils.upload.refreshSelectorTable("singleCustomer/findDocumentCustDocTypes", {"partyId" : $("#partyIdField").val()} );
               var oTable = $("#tbWd").dataTable();
               oTable.fnDraw();
            };
            utils.upload.initUploadify(formData,"#uploadPathField","#uploadFile", onStart, onOneSuc);
            utils.upload.refreshSelectorTable("singleCustomer/findDocumentCustDocTypes", {"partyId" : $("#partyIdField").val()} );
            $("#uploadDocumentForm").resetForm();
            $("#add-modal-formWd div.modal-header h4").html("<i class='ace-icon fa fa-plus'></i> 添加文档");
            $("#add-modal-formWd").modal("show");
        },
        initNationAreaCascade:function(){//省市区级联初始化
        	var viewSelf = this;
        	var nation=$("#nation");
        	var province= $("#province");
        	var city=$("#city");
        	var county= $("#county");
        	var isAll=false;//true 全部国家；false 只有中国
        	if(isAll){
        		viewSelf.model.loadNationAreaData("root",function(r_data){
        			$.each(r_data,function(index, val){
        				nation.append("<option value='"+val.code+"'>"+val.name+"</option>");
        			});
        		});
        	}else{
        		nation.append("<option value='156'>中国</option>");
        	}
        	viewSelf.initSelectCascade(nation,province);
        	viewSelf.initSelectCascade(province,city);
        	viewSelf.initSelectCascade(city,county);
        },
        initSelectCascade:function($select1,$select2){//绑定change，实现级联
        	var viewSelf = this;
        	$select1.change(function(){
        		var selectSelf=$(this);
        		viewSelf.initSelectOptions($select2,selectSelf.val());
        	});
        },
        initSelectOptions:function($select,code){//构建options
        	var viewSelf = this;
        	$select.get(0).options.length=1;//清除多余options
        	if($select.attr("id")=="province"){
        		$("#city").get(0).options.length=1;
            	$("#county").get(0).options.length=1;
        	}else if($select.attr("id")=="city"){
            	$("#county").get(0).options.length=1;
        	}
    		if(code){
    			viewSelf.model.loadNationAreaData(code,function(r_data){
    				$.each(r_data,function(index, val){
    					$select.append("<option value='"+val.code+"'>"+val.name+"</option>");
    				});
    			});
    		}
        },
        actAliveBtn: function(){
        	var viewSelf = this;
        	viewSelf.model.actAliveBtn({
        		"partyId" :$("#partyIdField").val()
        	}, function(r){
        		if(!r.success){
        			$("#showAlive").html(
                            "<button class='btn btn-sm btn-primary' id='comeToAlive' type='button' data-loading-text='正在执行...'>"
            				+"<i class='ace-icon fa fa-arrow-right'>"
            				+"</i>"
            				+"生 效"
            				+"</button>");
        		}else{
        			$("#showAlive").hide();
        		}
        	});
        }
    });
    module.exports = view;
});