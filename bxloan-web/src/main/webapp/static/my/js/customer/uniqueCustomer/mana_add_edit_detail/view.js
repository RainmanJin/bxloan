/**
 * 客户表单-经营
 * 
 * author: lijing
 * lastModified: lijing 2014-08-05 16:30:24
 */


define(function(require, exports, module) {
    var model = require("./model");
    var rmgk = require("./rm_gk");
    var rmlxr = require("./rm_lxr");
    var rmjdcw = require("./rm_jdcw");
    var rmjyxx = require("./rm_jyxx");
    var utils = require("utils");

    var view = Backbone.View.extend({
        el: "body",
        events: {
            "click #btn-query": "condQueryWd",
            "click #addLxr": "addLxr",
            "click #comeToAlive": "beAlive",
            "click #addWd" : "initUploadBtn",
            "change #add-relationsType": "changeFamilyFriendForm",
            "click #add-Wd-submit": "submitDocument",
            "click #saveGKXX": "gkgzFormSubimt"
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
            this.editBtnLiveLxr();
            this.detailBtnLiveLxr();
            this.detailBtnLivePcw();
            this.deleteBtnLiveLxr();
            this.downloadBtnLiveWd();
            //this.initUploadBtn();
            this.initCustDocTable();
            this.deleteBtnLiveWd();
            this.initWorkType();
            this.initDataTablesForLxr();
            this.initDataTablesForDI();
            this.initDataTablesForCw();
            this.initJDCWForm();
            this.initJYXXForm();
            this.initGKGZForm();
            this.initLxrForm();
            //this.initEndOfAll();
            this.initBirthday();
        },
        initFirstOfAll: function() {
            /**所有渲染开始之前所做的操作*/
            if ($("#customerIdField").val() != "" && $("#customerIdField").val() != null) {
                $("#partyIdField").val($("#customerIdField").val());
            }
            if ($("#hiddenForConsultLocation").val() == "contract") {
                $("#goBackButton").hide();
            }
            if($("#employmentTypeField").val()=="2"){
            	$("#agroFormDiv").show();
            	$("div[id$='-lxrForm-nh']").show();
            }
            $("#hiddenForFamilyCertificateType").val("100");
            
            var caculateBasicInfo = function(){
				var populationRate = $("#form-gkxx").find("input[name='dependentPopulation'],input[name='familySize']");
                $.each(populationRate,
                function(i, e) {
                    var $this = $(this);
                    $this.on("change",
                    function() {
                        if (populationRate[0].value && populationRate[1].value) {
                            var $rate = $("#form-gkxx").find("input[name='dependentPopulationRate']");
                            $rate.val((parseFloat(populationRate[1].value)/parseFloat(populationRate[0].value)*100).toFixed(1));
                        }
                    });
                });
			}
            caculateBasicInfo();
        },
        initDataTablesForCw: function() {
            /** CW初始化DataTables */
            var viewSelf = this;
            /**
  			 * 财务查看列表
  			 * */
            var oTable = $("#tbProjectCw").dataTable({
                "sAjaxSource": $$ctx + "singleCustomer/findProjectFinalcials",
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
            /** LXR初始化DataTables */
            var viewSelf = this;
            /**
			 * 联系人管理列表
			 * */
            utils.dd.initDataDict(["WdCorrelativeCustTypeCd", "RelationsType"],
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
                    { //联系人类型
                        "mData": "relationsType",
                        mRender: function(data, type, rowdata) {
                            return dataDict.RelationsType[data];
                        }
                    },
                    { //与借款人关系
                        "mData": "familyFriendType",
                        mRender: function(data, type, rowdata) {
                            return dataDict.WdCorrelativeCustTypeCd[data];
                        }
                    },
                    {
                        "mData": "telphone"
                    },
                    {
                        "mData": "partyId",
                        mRender: function(data, type, rowdata) {
                            if ($("#workTypeField").val() == "TODETAIL") {
                                return "<button type='button' role='detail_lxr' data-id='" + rowdata.correlativeRelationsId + "' class='btn btn-xs btn-yellow' title='查看'><i class='ace-icon fa fa-eye'></i></button> ";
                            } else {
                                var buttons = "<button type='button' role='edit_lxr' data-id='" + rowdata.correlativeRelationsId + "' class='btn btn-xs btn-info' title='修改'><i class='ace-icon fa fa-edit' ></i></button> " + "<button type='button' role='detail_lxr' data-id='" + rowdata.correlativeRelationsId + "' class='btn btn-xs btn-yellow' title='查看'><i class='ace-icon fa fa-eye'></i></button> " + "<button type='button' role='delete_lxr' data-id='" + rowdata.correlativeRelationsId + "' class='btn btn-xs btn-danger' title='删除'><i class='ace-icon fa fa-trash-o'></i></button>";
                                return buttons
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
            utils.dd.initDataDict([/*"CustomerDocType"*/ "CustProjectAllDocType", "DocumentType", "CreateType"],
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
                        });
                    },
                    "aoColumnDefs": [{
                        "aTargets": [0],
                        mRender: function(data, type, rowdata) {
                        	var cb  = "<input type='checkbox' name='documentNums' value='"+ rowdata[8] +"' />";
                        	return cb;
                        }
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
                        "aTargets":  [3],
                        mRender: function(data, type, rowdata) {
                            if (data == null) {
                                return "未填写";
                            } else {
                                return dataDict.DocumentType[data];
                            }

                        }
                    },
                    {	"bVisible":false,
                        "aTargets":  [4]
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
                        $("input[name='customerNum'][id='csnm']").attr("readonly", "readonly");
                        $("#form-gkxx input[name='certificateNum']").attr("readonly", "readonly");
                        $("select[name='certificateTypeCd'][id='certificateTypeCd']").attr("readonly", "readonly");
                        $("input[id='industryCdMask']").attr("readonly", "readonly");
                        $("input[name='genderCd'][value=" + data.genderCd + "]").attr("checked", true);
                        $("input[name='payFundInd'][value=" + data.payFundInd + "]").attr("checked", true);
                        $("input[name='paySocialSecurityInd'][value=" + data.paySocialSecurityInd + "]").attr("checked", true);
                        $("input[name='censusType'][value=" + data.censusType + "]").attr("checked", true);
                        $("input[name='houseCondition'][value=" + data.houseCondition + "]").attr("checked", true);
                        $("input[name='houseShareCondition'][value=" + data.houseShareCondition + "]").attr("checked", true);
                        $("input[name='positiveInd'][value=" + data.positiveInd + "]").attr("checked", true);
                        
                        //modify by HWL 20150715 配偶&配偶就业情况赋值
                        $("select[name='spouseEmploymentShow']").val(data.spouseEmployment);
                        $("input[name='spouseEmployment']").val(data.spouseEmployment);
                        $("select[name='spouseCaseShow']").val(data.spouseCase);
                        $("input[name='spouseCase']").val(data.spouseCase);
                        //modify by HWL 20150715 配偶&配偶就业情况赋值
                        
                        
                        //婚姻状况反显控制，如果不是已婚，配偶和配偶就业情况置灰
                        var spouseCase = $("#form-gkxx select[name='spouseCaseShow']");
                    	var spouseEmployment = $("#form-gkxx select[name='spouseEmploymentShow']");
                        if(data.marriageCd && data.marriageCd !=20){
                    		spouseCase.val(3).attr('readonly',true).attr('disabled',true);
                    		spouseEmployment.val(6).attr('readonly',true).attr('disabled',true);
                    	}

                        $("select[name='nationalityCd']").val(data.nationalityCd);
                        $("#nation").change();//更新下一级级联
                        $("select[name='provinceCd']").val(data.provinceCd);
                    	$("#province").change();//更新下一级级联
                    	$("select[name='cityCd']").val(data.cityCd);
                    	$("#city").change();//更新下一级级联
                    	$("select[name='countyCd']").val(data.countyCd);
                    	$(":input[name='permanentAddress']").val(data.permanentAddress);
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
                    	utils.alert.err("<strong>因未知错误，获取信息失败！</strong>");
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
                        $.each($("#form-cwxx").find("input[type='text'], select, textarea"),
                        function() {
                            $(this).val(data[$(this).attr("name")]);
                            $(this).removeAttr("readonly")
                        });
                        $("#industryCd").attr("readonly", "readonly");
                        $("#cwxxSpan").show();
                        //houseConditionChange($("#localHouseCondition")[0]);
                        carConditionChange($("#carCondition")[0]);
                        houseConditionChange($("#localHouseCondition")[0]);
                    } else {
                    	utils.alert.err( "<strong>因未知错误，获取信息失败！</strong>");
                    }
                });
                //经营信息的反显
                viewSelf.model.findOneBusiness({
                    customerId: customerId
                },
                function(data) {
                    if (data != null) {
                        //经营信息的反显
                        $.each($("#form-jyxx").find("input[type='text'], select"),
                        function() {
                            $(this).val(data[$(this).attr("name")]);
                            $(this).removeAttr("readonly")
                        });
                        $("#jyxxSpan").show();
                    } else {
                    	utils.alert.err("<strong>因未知错误，获取信息失败！</strong>");
                    }
                });
            } //else if end
            else if (workType == "TODETAIL") {
                $("#cwglSpan").hide();
                $("#wdSpan").hide();
                $("#lxrSpan").hide();
                //$("button[role='uploadDoc'] ").hide();
                $("#tb_doc_selector").hide();
                
                viewSelf.model.findOneCustomer({
                    customerId: customerId
                },
                function(data) {
                    if (data != null) {
                        //概况信息与工作资料的反显
                        $.each($("#form-gkxx").find("input[type='text'], select, textarea"),
                        function() {
                        	if($(this).attr("name")=='birthday'){
                        		$(this).val(utils.date.timestampToDate(data[$(this).attr("name")],'yyyy/MM/dd'));
                        	}else{
                        		$(this).val(data[$(this).attr("name")]);
                        	}
                            $(this).attr("readonly", "readonly");
                        });
                        
                        var marktype = data["markType"];
                        var strs = marktype.split(",");
                        $("input[type='checkbox'][name='markType']").val(strs);
                        
                        $.each($("#form-gkxx").find("input[type='radio'], select, textarea"),
                        function() {
                            $(this).attr("disabled", "disabled");
                        });
                        $("#btn-showTree").remove();
                        $("input[name='markType']").prop("disabled",true);
                        $("input[name='genderCd'][value=" + data.genderCd + "]").attr("checked", true);
                        $("input[name='payFundInd'][value=" + data.payFundInd + "]").attr("checked", true);
                        $("input[name='paySocialSecurityInd'][value=" + data.paySocialSecurityInd + "]").attr("checked", true);
                        $("input[name='censusType'][value=" + data.censusType + "]").attr("checked", true);
                        $("input[name='houseCondition'][value=" + data.houseCondition + "]").attr("checked", true);
                        $("input[name='houseShareCondition'][value=" + data.houseShareCondition + "]").attr("checked", true);
                        $("input[name='positiveInd'][value=" + data.positiveInd + "]").attr("checked", true);
                       
                        //modify by HWL 20150715 配偶&配偶就业情况赋值
                        $("select[name='spouseEmploymentShow']").val(data.spouseEmployment);
                        $("input[name='spouseEmployment']").val(data.spouseEmployment);
                        $("select[name='spouseCaseShow']").val(data.spouseCase);
                        $("input[name='spouseCase']").val(data.spouseCase);
                        //modify by HWL 20150715 配偶&配偶就业情况赋值
                        
                      //婚姻状况反显控制，如果不是已婚，配偶和配偶就业情况置灰
                        var spouseCase = $("#form-gkxx select[name='spouseCaseShow']");
                    	var spouseEmployment = $("#form-gkxx select[name='spouseEmploymentShow']");
                        if(data.marriageCd && data.marriageCd !=20){
                    		spouseCase.val(3).attr('readonly',true).attr('disabled',true);
                    		spouseEmployment.val(6).attr('readonly',true).attr('disabled',true);
                    	}

                        $("select[name='nationalityCd']").val(data.nationalityCd);
                        $("#nation").change();//更新下一级级联
                        $("select[name='provinceCd']").val(data.provinceCd);
                    	$("#province").change();//更新下一级级联
                    	$("select[name='cityCd']").val(data.cityCd);
                    	$("#city").change();//更新下一级级联
                    	$("select[name='countyCd']").val(data.countyCd);
                    	$(":input[name='permanentAddress']").val(data.permanentAddress);
                        	
                        $("#gkgzSpan").hide();

                    } else {
                    	utils.alert.err("<strong>因未知错误，获取信息失败！</strong>");
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
                    	utils.alert.err("<strong>因未知错误，获取信息失败！</strong>");
                    }
                });
                viewSelf.model.findOneBusiness({
                    customerId: customerId
                },
                function(data) {
                    if (data != null) {
                        //经营信息的反显
                        $.each($("#form-jyxx").find("input[type='text'], select"),
                        function() {
                            $(this).val(data[$(this).attr("name")]);
                            $(this).attr("disabled", "disabled");
                        });
                        $("#jyxxSpan").hide();
                    } else {
                    	utils.alert.err("<strong>因未知错误，获取信息失败！</strong>");
                    }
                });
            }
        },
        condQueryWd: function() {
            var oTable = $("#tbWd").dataTable();
            oTable.fnSettings()._iDisplayStart = 0;
            oTable.fnDraw();
        },
        addLxr: function() {
        	var viewSelf = this;
            $("#addFamilyFriendForm").resetForm();
            $("#hiddenForLxrWorkType").val("ADD");
            viewSelf.changeFamilyFriendForm();
            $("#addFamilyFriendForm select[name='certificateTypeCd']").removeAttr("readonly");
            $("#add-modal-formLxr div.modal-header h4").html("<i class='ace-icon fa fa-plus'></i> 新增联系人");
            $("#add-modal-formLxr").modal("show");
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
                        utils.alert.err("查找单一联系人信息报错" + request);
                    },
                    success: function(data) {
                        if (data != null) {
                        	$("#addFamilyFriendForm").resetForm();
                            $("#hiddenForLxrWorkType").val("MOD");
                            $("#hiddenForFamilyCertificateType").val(data['certificateTypeCd']);
                            $("#modFamilyFriendForm select[name='relationsType']").val(data["relationsType"]);
                            $("#modFamilyFriendForm input[name='relationsType']").val(data["relationsType"]);
                            viewSelf.changeFamilyFriendForm();
                            $.each($("#modFamilyFriendForm").find("input[type='text'], select"),
                            function() {
                                $(this).val(data[$(this).attr("name")]);
                                
                                $(this).removeAttr("readonly");
                                $(this).removeAttr("disabled");
                            });
                            $("#modFamilyFriendForm select[name='relationsType']").attr("disabled", "disabled");
                            $("#modFamilyFriendForm select[name='certificateTypeCd']").removeAttr("readonly");
                            $("#mod-modal-formLxr div.modal-header h4").html("<i class='ace-icon fa fa-plus'></i> 修改联系人");
                            $("#mod-modal-formLxr").modal("show");
                            //选择是否家庭成员
                            $("input[name='familyMembers'][value=" + data.familyMembers + "]").prop("checked", true);
                            $("input[name='familyMembers']").attr("disabled", false);
                            $("#modLxrSpan").show();
                            
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
                        utils.alert.err("查找单一财务信息报错" + request);
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
                        	utils.alert.err( "<strong>因未知错误，获取信息失败！</strong>");
                        }
                    }
                });
                return false;
            });
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
                    async: true,
                    error: function(request) {
                        utils.alert.err("查找单一账户信息报错" + request);
                    },
                    success: function(data) {
                        if (data != null) {
                            $("#hiddenForLxrWorkType").val("MOD");
                            var $form=$("#modFamilyFriendForm");
                            $form.find("select[name='relationsType']").val(data["relationsType"]);
                            viewSelf.changeFamilyFriendForm();
                            $.each($form.find("input[type='text'], select, textarea"),function() {
                                $(this).val(data[$(this).attr("name")]).prop("readonly", true);
                            });
                            $form.find("input[name='familyMembers'][value=" + data.familyMembers + "]").prop("checked", true);
                            $.each($form.find("input[type='radio'], select, textarea"),function() {
                                $(this).prop("disabled",true);
                            });
                            $("#modLxrSpan").hide();
                            $("#mod-modal-formLxr div.modal-header h4").html("<i class='ace-icon fa fa-plus'></i> 查看联系人");
                            $("#mod-modal-formLxr").modal("show");
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
                    url:  $$ctx + "singleCustomer/findDocumentPath",
                    data: {
                        documentId: $this.data("id")
                    },
                    async: false,
                    error: function(request) {
                        utils.alert.err("查找下载路径出错" + request);
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
                                    	utils.alert.warn( "<strong>该文档关联方式为引用，不允许删除，请联系管理员来操作!</strong>");
                                    } else if (data == "createTypeCdEquals1AndIsCustomerError") {
                                    	utils.alert.warn( "<strong>该文档为客户文档，不允许在此处删除，请在客户管理下进行删除操作！</strong>");
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
        	var viewSelf = this;
        	var _rules = $("#employmentTypeField").val()== "2"?rmlxr.nh_rules:rmlxr.jy_rules;
            $("#addFamilyFriendForm").validate({
                rules: _rules,
                messages: rmlxr.messages,
                submitHandler: function(form) {
                    var str = $("#partyIdField").val();
                    $.ajax({
                        cache: true,
                        type: "POST",
                        url: $$ctx + "singleCustomer/addFamilyFriend?partyId=" + str,
                        data: $('#addFamilyFriendForm').serialize(),
                        // 
                        async: false,
                        error: function(request) {
                            utils.alert.err("添加联系人信息报错" + request);
                        },
                        success: function(data) {
                            if (data == "success") {
                               // bootbox.alert("<strong>保存成功！</strong>");
                                $("#add-modal-formLxr").modal("hide");
                                var oTable = $("#tbLxr").dataTable();
                                oTable.fnDraw();
                                viewSelf.actAliveBtn();
                            } else if (data == "telephoneNumSameError") {
                            	utils.alert.warn( "<strong>联系人的主要手机号不能与客户本人相同！</strong>");
                            } else if (data == "certificateNumSameError") {
                            	utils.alert.warn("<strong>联系人的证件号不能与客户本人相同！</strong>");
                            } else if (data == "customerConditionNotSaveError") {
                            	utils.alert.warn( "<strong>请先填写客户的概况信息！</strong>");
                            } else if (data == "customerNotMarryingError") {
                            	utils.alert.warn("<strong>非已婚状态不可添加配偶的信息！</strong>");
                            } else if (data == "coupleHasBeenRegistedError") {
                            	utils.alert.warn( "<strong>配偶已经登入过了！</strong>");
                            } else {
                            	utils.alert.err("<strong>因未知错误，保存失败！</strong>");
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
            $("#modFamilyFriendForm").validate({
                rules: _rules,
                messages: rmlxr.messages,
                submitHandler: function(form) {
                    var str = $("#partyIdField").val();
                    var correlativeRelations = $("#correlativeRelationsIdCwField").val();
                    $.ajax({
                        cache: true,
                        type: "POST",
                        url: $$ctx + "singleCustomer/modFamilyFriend?partyId=" + str + "&correlativeRelationsId=" + correlativeRelations,
                        data: $('#modFamilyFriendForm').serialize(),
                        // 
                        async: false,
                        error: function(request) {
                            utils.alert.err("修改联系人信息报错" + request.status);
                        },
                        success: function(data) {
                            if (data == "success") {
                                //bootbox.alert("<strong>保存成功！</strong>");
                                var oTable = $("#tbLxr").dataTable();
                                oTable.fnDraw();
                                $("#mod-modal-formLxr").modal("hide");
                                viewSelf.actAliveBtn();
                            } else if (data == "telephoneNumSameError") {
                            	utils.alert.warn( "<strong>联系人的主要手机号不能与客户本人相同！</strong>");
                            } else if (data == "certificateNumSameError") {
                            	utils.alert.warn("<strong>联系人的证件号不能与客户本人相同！</strong>");
                            } else if (data == "customerConditionNotSaveError") {
                            	utils.alert.warn("<strong>请先填写客户的概况信息！</strong>");
                            } else if (data == "customerNotMarryingError") {
                            	utils.alert.warn("<strong>非已婚状态不可添加配偶的信息！</strong>");
                            } else if (data == "coupleHasBeenRegistedError") {
                            	utils.alert.warn("<strong>配偶已经登入过了！</strong>");
                            } else {
                            	utils.alert.err( "<strong>因未知错误，保存失败！</strong>");
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
            /**概况的表单提交*/
        	var _rules = $("#employmentTypeField").val()== "2"?rmgk.nh_rules:rmgk.jy_rules;
        	$("#form-gkxx").validate({
                rules: _rules,
                messages: rmgk.messages,
                errorPlacement: function(error, element) {
                    if (element.is(":checkbox")) error.appendTo(element.parent());
                    else if (element.next().is("span[class='input-group-addon']")) {
                    	error.appendTo(element.parent().parent());
                    }
                    else error.appendTo(element.parent());
                }
            });
        	
        	//初始化婚姻状况change事件
            $(document).on("change","#form-gkxx select[name='marriageCd']",function(e){
            	var $this = $(this).val();
            	var spouseCase = $("#form-gkxx select[name='spouseCaseShow']");
            	var spouseEmployment = $("#form-gkxx select[name='spouseEmploymentShow']");
            	if($this && $this !=20){
            		spouseCase.val(3).attr('readonly',true).attr('disabled',true);
            		spouseEmployment.val(6).attr('readonly',true).attr('disabled',true);
            		
            		//清除错误信息提示
            		spouseCase.closest('div.form-group').removeClass('has-error');
            		spouseCase.closest('span').children('div.help-block').remove();
            		
            		spouseEmployment.closest('div.form-group').removeClass('has-error');
            		spouseEmployment.closest('span').children('div.help-block').remove();
            	}else{
            		spouseCase.val('').removeAttr('readonly').removeAttr('disabled');
            		spouseEmployment.val('').removeAttr('readonly').removeAttr('disabled');
            	}
            });
            
            //初始化配偶及配偶就业情况change事件
            $(document).on("change","#form-gkxx select[name='spouseCaseShow'],#form-gkxx select[name='spouseEmploymentShow']",function(e){
            	if($(this).attr('name') == 'spouseCaseShow'){//配偶下拉框name属性
            		$("#form-gkxx :input[name='spouseCase']").val($(this).val());
            	}else{										//配偶就业情况下拉框name属性
            		$("#form-gkxx :input[name='spouseEmployment']").val($(this).val());
            	}
            });
        },
        initJYXXForm: function() {
            /**经营信息的表单提交*/
            $("#form-jyxx").validate({
                rules: rmjyxx.rules,
                messages: rmjyxx.messages,
                submitHandler: function(form) {
                	utils.button.ban("#form-jyxx button[type='submit']");
                    $.ajax({
                        cache: false,
                        type: "POST",
                        url: $$ctx + "singleCustomer/updateBusinessInfo?partyId=" + $("#partyIdField").val(),
                        data: $('#form-jyxx').serialize(),
                        // 
                        async: true,
                        error: function(request) {
                            utils.alert.err("保存经营信息报错" + request);
                        },
                        success: function(data) {
                        	utils.button.reset("#form-jyxx button[type='submit']");
                            if (data == "success") {
                            	utils.alert.suc("<strong>保存成功！</strong>");
                            } else {
                            	utils.alert.suc( "<strong>因未知错误，保存失败！</strong>");
                            }
                        }
                    }); //ajax end
                },
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
                submitHandler: function(form) {
                	utils.button.ban("#form-cwxx button[type='submit']");
                    $.ajax({
                        cache: false,
                        type: "POST",
                        url: $$ctx + "singleCustomer/updateFinance?partyId=" + $("#partyIdField").val(),
                        data: $('#form-cwxx').serialize(),
                        // 
                        async: true,
                        error: function(request) {
                            utils.alert.err("保存财务信息报错" + request);
                        },
                        success: function(data) {
                        	utils.button.reset("#form-cwxx button[type='submit']");
                            if (data == "success") {
                            	utils.alert.suc("<strong>保存成功！</strong>");
                            } else {
                            	utils.alert.err("<strong>因未知错误，保存失败！</strong>");
                            }
                        }
                    }); //ajax end
                },
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
        beAlive: function() {
            /**客戶生效*/
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
                    utils.alert.err("客戶生效报错" + request);
                },
                success: function(data) {
                	utils.button.reset("#comeToAlive");
                	if (data.success) {
                		utils.alert.suc( "<strong>此客戶已生效！点击确定跳转至客户管理界面。</strong>", function(result){
                        	window.location.href = $$ctx+"singleCustomer";
                        });
                    } else {
                    	utils.alert.warn( "<strong>"+ data.msg +"</strong>");
                    }
                }
            }); //ajax end
        	}else{
        		utils.alert.warn( "<strong>请确保家庭资产信息与概况信息填写一致！</strong>");
        	}
            return false;
        },
        changeFamilyFriendForm: function() {
            var hidden = $("#hiddenForLxrWorkType").val();
            var sel = null;
            if (hidden == "ADD") {
                sel = $("#add-relationsType").val();
                if (sel == "1") {
                    content = $("#peiou").html();
                    $("#add-formContent")[0].innerHTML = content;
                    if($("#employmentTypeField").val()== "2"){
                    $("#add-formContent").find("div.form-group[name='workcompany_div']").remove();
                   }
                } else if (sel == "3") {
                    content = $("#qinshu").html();
                    $("#add-formContent")[0].innerHTML = content;
                } else if (sel == "4") {
                    content = $("#feiqinshu").html();
                    $("#add-formContent")[0].innerHTML = content;
                }
            } else if (hidden == "MOD") {
                sel = $("#mod-relationsType").val();
                if (sel == "1") {
                    content = $("#peiou").html();
                    $("#mod-formContent")[0].innerHTML = content;
                    if($("#employmentTypeField").val()== "2"){
                    	$("#mod-formContent").find("div.form-group[name='workcompany_div']").remove();
                    }
                } else if (sel == "3") {
                    content = $("#qinshu").html();
                    $("#mod-formContent")[0].innerHTML = content;
                } else if (sel == "4") {
                    content = $("#feiqinshu").html();
                    $("#mod-formContent")[0].innerHTML = content;
                }
            }
        },
        gkgzFormSubimt: function() {
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
                        utils.alert.err("保存概况信息报错" + request);
                    },
                    success: function(data) {
                    	utils.button.reset("#saveGKXX");
                            //	$("#form-gkxx").resetForm();//防止多次提交，重置表单
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
        submitDocument: function() {
            /**文件上传按钮*/
            $("#uploadFile").uploadify("upload", "*"); // 批量上传
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
        initUploadBtn:function(){
        	//打开文档模态窗口
        	 var viewSelf = this;
             /**上传文档弹窗*/
             var formData = utils.upload.beforeUpload("singleCustomer/beforeUpdate",{"partyId": $("#partyIdField").val()} );
             var onStart = function(file, data){
            	 data.custDocType = $("#tb_doc_selector input[name='uploadDoc']:checked").val();
                 data.documentNum = $("#documentNumDI").val();
                   data.allDocType = $("#allDocTypeField").val();//经营类
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