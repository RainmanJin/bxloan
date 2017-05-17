define(function(require, exports, module) {
	
	var model = require("./model");
	var rm = require("./rm");
	var rm_lxr = require("./rm_lxr");
	var rm_VerificatPersonInfo = require("./rm_VerificatPersonInfo");
	var utils = require("utils");
	var formFunc = {};
	var getHidden = function(hiddenName){
		return $("#hiddenField").find("input[name='"+ hiddenName +"']").val();
	}
	var view = Backbone.View.extend({
		el : "body",
		events : {
			"click #show-tree": "ToggleTree",
			"click #submitBasicForm" : "submitBasicForm",
			"click #btn-family" : "addLxr",
			"click #add-Lxr-submit" : "submitLxr",
			"click #btn-verificatPerson" : "addVerificatPerson",
			"click #add-VerificatPerson-submit" : "submitVerificatPerson"
		},
		initialize : function() {
			this.viewObj={};
			this.viewObj.isView=utils.parseBool($("#isView").val());//是否是查看
			this.viewObj.isEdit=utils.parseBool($("#isEdit").val());//是否是编辑
			this.model = new model();
			this.render();
		},
		render : function() {
			this.initFrist();
			this.initTree();
			this.getCustomerMessage();
			this.initbasicInfoForm_nd();
			this.initFamilyFriendForm();
			this.initFamilyFriendTable();
			this.deleteBtnLiveLxr();
			this.editBtnLiveLxr();
			this.initVerificatPersonForm();
			this.initVerificatPersonInfoTable();
			this.editBtnLiveVerificatPerson();
			this.deleteBtnLiveVerificatPerson();
			
		},
		initFrist: function(){
			var viewSelf=this;
			$("#basicInfoForm_nd").find("input.required,select.required").closest("div.form-group").find("label").prepend("<font color='red'>*</font>");
			formFunc = {
				marriageChange: function(){
					$(document).on("change", "#marriageCd", function(e){
						if($(this).val()=="20"){
							$("#spouseCase").prop("disabled",false);
							$("#spouseEmployment").prop("disabled",false);
						}else{
							$("#spouseCase").prop("disabled",true);
							$("#spouseEmployment").prop("disabled",true);
						}
					});
				},
				caculateBasicInfo: function(){
					var populationRate = $("#basicInfoForm_nd").find("input[name='dependentPopulation'],input[name='familySize']");
                    $.each(populationRate,
                    function(i, e) {
                        var $this = $(this);
                        $this.on("change",
                        function() {
                            if (populationRate[0].value && populationRate[1].value) {
                                var $rate = $("#basicInfoForm_nd").find("input[name='dependentPopulationRate']");
                                $rate.val((parseFloat(populationRate[1].value)/parseFloat(populationRate[0].value)*100).toFixed(1));
                            }
                        });
                    });
				}
			}
			if(viewSelf.viewObj.isEdit){
				formFunc.marriageChange();
			}
			formFunc.caculateBasicInfo();
		},
		getCustomerMessage: function(){
			var viewSelf = this;
			viewSelf.model.getCustomerMessage({
				"customerId" : getHidden("customerId")
			}, function(r){
				if(r){
					$.each($("#basicInfoForm_nd").find("input, select, textarea"), function() {
	                   $(this).val(r[$(this).attr("name")]);
	                });
					$("#marriageCd").change();
					$("input:hidden[name='idCardNum_type']").val(100);
				}
			});
		},
		initbasicInfoForm_nd: function(){
			 $("#basicInfoForm_nd").validate({
	                rules: rm.rules,
	                messages: rm.messages,
	                errorPlacement: function(error, element) {
	                    if (element.is(":radio")){error.appendTo(element.parent());}
	                    else if (element.is(":checkbox")){error.appendTo(element.parent().parent().parent().next());}
	                    else if (element.is("input[name='industryCdMask']")) {
	                        error.appendTo(element.parent().parent());
	                    }
	                    else if (element.next().is("span[class='input-group-addon']")) {
	                    	error.appendTo(element.parent().parent());
	                    }
	                    else {
	                    	error.appendTo(element.parent());
	                    } 
	                }
	            });
		},
		initFamilyFriendForm: function(){
			 $("#familyFriendForm").validate({
	                rules: rm_lxr.rules,
	                messages: rm_lxr.messages,
	                errorPlacement: function(error, element) {
	                    if (element.is(":radio")){error.appendTo(element.parent());}
	                    else if (element.is(":checkbox")){error.appendTo(element.parent().parent().parent().next());}
	                    else if (element.is("input[name='industryCdMask']")) {
	                        error.appendTo(element.parent().parent());
	                    }
	                    else if (element.next().is("span[class='input-group-addon']")) {
	                    	error.appendTo(element.parent().parent());
	                    }
	                    else {
	                    	error.appendTo(element.parent());
	                    } 
	                }
	            });
		},
		initFamilyFriendTable: function(){
			 var viewSelf = this;
			 utils.dd.initDataDict(["WdCorrelativeCustTypeCd", "FamiliyMemberStatus", "DegreeCode"],
			            function(dataDict) {
			                var oTable = $("#familyTb").dataTable({
			                    "sAjaxSource": $$ctx + "singleCustomer/findCustFamilyAgroBySearch",
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
			                    { //与借款人关系
			                        "mData": "familyFriendType",
			                        mRender: function(data, type, rowdata) {
			                        	if(data){
			                        		return dataDict.WdCorrelativeCustTypeCd[data];
			                        	}
			                        	return "-";
			                        }
			                    },
			                    {
			                        "mData": "age"
			                    },
			                    {
			                        "mData": "personStatus",
			                        mRender: function(data, type, rowdata) {
			                        	if(data){
			                        		return dataDict.FamiliyMemberStatus[data];
			                        	}
			                        	return "-";
			                        }
			                    },
			                    {
			                        "mData": "workCompany"
			                    },
			                    {
			                        "mData": "monthlyIncome"
			                    },
			                    {
			                        "mData": "degreeCd",
			                        mRender: function(data, type, rowdata) {
			                        	if(data){
			                            return dataDict.DegreeCode[data];
			                        	}
			                        	return "-";
			                        }
			                    },
			                    {
			                        "mData": "telphone"
			                    },
			                    {
			                        "mData": "partyId",
			                        mRender: function(data, type, rowdata) {
			                        		if(viewSelf.viewObj.isView){
			                        			return "";
			                        		}
			                                var buttons = "<button type='button' role='edit_lxr' data-id='" + rowdata.correlativeRelationsId + "' class='btn btn-xs btn-info' title='修改'><i class='ace-icon fa fa-edit' ></i></button> "  + "<button type='button' role='delete_lxr' data-id='" + rowdata.correlativeRelationsId + "' class='btn btn-xs btn-danger' title='删除'><i class='ace-icon fa fa-trash-o'></i></button>";
			                                return buttons
			                        }
			                    }],
			                    "fnServerParams": function(aoData) {
			                        aoData.push({
			                            "name": "customerId",
			                            "value": getHidden("customerId")
			                        });
			                    }
			                });
			                viewSelf.oTable = oTable;
			            });
		},
		initVerificatPersonForm: function(){
			 $("#verificatPersonForm").validate({
	                rules: rm_VerificatPersonInfo.rules,
	                messages: rm_VerificatPersonInfo.messages,
	                errorPlacement: function(error, element) {
	                    if (element.is(":radio")){error.appendTo(element.parent());}
	                    else if (element.is(":checkbox")){error.appendTo(element.parent().parent().parent().next());}
	                    else if (element.is("input[name='industryCdMask']")) {
	                        error.appendTo(element.parent().parent());
	                    }
	                    else if (element.next().is("span[class='input-group-addon']")) {
	                    	error.appendTo(element.parent().parent());
	                    }
	                    else {
	                    	error.appendTo(element.parent());
	                    } 
	                }
	            });
			/* var vpif = $("#verificatPersonForm");
			 vpif.get(0).reset();*/
		},
		initVerificatPersonInfoTable: function(){
			 var viewSelf = this;
			 utils.dd.initDataDict(["VerificatPersonType"],
			            function(dataDict) {
			                var oTable = $("#verificatPersonTb").dataTable({
			                    "sAjaxSource": $$ctx + "singleCustomer/findVerificatPersonInfo?partyId=" + getHidden("customerId"),
			                    "bFilter": false,
			                    "bAutoWidth": true,
			                    "bLengthChange": false,
			                    "aoColumns": [{
			                        "bVisible": false,
			                        "aTargets": [0]
			                    },
			                    {
			                    	"aTargets": [1]
			                    },
			                    { //与借款人关系
			                    	"aTargets": [2],
			                        mRender: function(data, type, rowdata) {
			                        	if(data){
			                        		return dataDict.VerificatPersonType[data];
			                        	}
			                        	return "-";
			                        }
			                    },
			                    {
			                    	"aTargets": [3]
			                    },
			                    {
			                    	"aTargets": [4]
			                    },
			                    {
			                    	"aTargets": [5]
			                    },
			                    {
			                    	"aTargets": [6],
			                        mRender: function(data, type, full) {
			                        		if(viewSelf.viewObj.isView){
			                        			return "";
			                        		}
			                        		data = full[0];
			                                var buttons = "<button type='button' role='edit_VerificatPerson' data-id='" + data + "' class='btn btn-xs btn-info' title='修改'><i class='ace-icon fa fa-edit' ></i></button> "  + "<button type='button' role='delete_VerificatPerson' data-id='" + data + "' class='btn btn-xs btn-danger' title='删除'><i class='ace-icon fa fa-trash-o'></i></button>";
			                                return buttons;
			                        }
			                    }]
			                });
			                viewSelf.oTable = oTable;
			            });
		},
		submitBasicForm: function(){
			var viewSelf = this;
            if ($("#basicInfoForm_nd").valid()) {
            	utils.button.ban("#submitBasicForm");
            	$.ajax({
                    cache: false,
                    type: "POST",
                    url: $$ctx + "singleCustomer/updateIndividualForAgro?partyId=" + getHidden("customerId"),
                    data: $('#basicInfoForm_nd').serialize(),
                    async: false,
                    error: function(request) {
                        utils.alert.err("错误"+ request.status);
                    },
                    success: function(data) {
                    	utils.button.reset("#submitBasicForm");
                    	if(data){
                    		if(data.success){
                    			utils.alert.suc("<strong>"+ data.msg +"</strong>", function(){
                    				window.location.href = $$ctx + "bizapply/editApplication/" + $("#projectId").val() + "/" + $("#workflowId").val() + "/" + $("#taskId").val();
                    			});
                    		}
                    		else{
                    			utils.alert.warn("<strong>"+ data.msg +"</strong>");
                    		}
                    	}
                    }
                }); //ajax end
            }
		},
		initTree: function() {
            /**初始化树*/
            var viewSelf = this;
            $.fn.zTree.init($("#_treeDemo"), {
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
                            $("#_industryCdMask").val("");
                            $("#_industryCd").val("");
                            return false;
                        } else {
                            $("#industryTypeCdField").val(treeNode.industryTypeCd);
                            $("#_industryCdMask").val(treeNode.industryTypeName);
                            $("#_industryCd").val(treeNode.industryTypeCd);
                            var treeObj = $.fn.zTree.getZTreeObj(treeId);
                            var node = treeObj.getNodeByParam("industryTypeCd", treeNode.industryTypeCd, null);
                            treeObj.checkNode(node, true, true);
                            var parentNode = node.getParentNode();
                            treeObj.expandNode(parentNode, true, false);
                            $("#_controlZTree").toggle(300,
                            function() {});
                        }
                    },
                    beforeCheck: function(treeId, treeNode) {
                    	 $("#industryTypeCdField").val(treeNode.industryTypeCd);
                         $("#_industryCd").val(treeNode.industryTypeCd);
                        return ! treeNode.isParent;
                    },
                    onAsyncSuccess: function(event, treeId, treeNode, msg) {
                        var treeObj = $.fn.zTree.getZTreeObj(treeId);
                        var industryTypeCd = $("#_industryCd").val();
                        if (industryTypeCd !== "") {
                            var node = treeObj.getNodeByParam("industryTypeCd", industryTypeCd, null);
                            treeObj.checkNode(node, true, true);

                            var parentNode = node.getParentNode();
                            treeObj.expandNode(parentNode, true, false);
                            $("#_industryCdMask").val(node.industryTypeName);
                            $("#_industryCd").val(node.industryTypeCd);
                        }
                    }
                }
            });
        },
        ToggleTree: function() {
            /**显示隐藏行业的树*/
            $("#_controlZTree").toggle(300,
            function() {
                if (($("#_controlZTree").attr("style")) == "") {} else if (($("#_controlZTree").attr("style")) == "display: none;") {}
            });
        },
        addLxr: function(){
        	$("#familyFriendForm").resetForm();
        	utils.validate.removeCss("#familyFriendForm");
        	$("#familyFriendForm").find("input:hidden[name='correlativeRelationsId']").val("");
        	$("#modal-formLxr div.modal-header h4").html("<i class='ace-icon fa fa-plus'></i>家庭成员信息");
            $("#modal-formLxr").modal("show");
        },
        submitLxr: function(){
        	var viewSelf = this;
            if ($("#familyFriendForm").valid()) {
            	utils.button.ban("#add-Lxr-submit");
            	$.ajax({
                    cache: false,
                    type: "POST",
                    url: $$ctx + "singleCustomer/addFamilyFriendForAgro?partyId=" + getHidden("customerId") + 
                    	"&marriageCd=" + $("#marriageCd").val(),
                    data: $('#familyFriendForm').serialize(),
                    async: false,
                    error: function(request) {
                        utils.alert.err("错误"+ request.status);
                    },
                    success: function(data) {
                    	utils.button.reset("#add-Lxr-submit");
                    	if(data){
                    		if(data.success){
                    			utils.alert.suc("<strong>"+ data.msg +"</strong>");
                    			$("#familyTb").dataTable().fnDraw();
                    			$("#modal-formLxr").modal("hide");
                    		}
                    		else{
                    			utils.alert.warn("<strong>"+ data.msg +"</strong>");
                    		}
                    	}
                    }
                }); //ajax end
            }
        },
        editBtnLiveLxr: function() {
        	 var viewSelf = this;
             $(document).on("click", "button[role='edit_lxr']", function(e){
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
                         alert("错误" + request.status);
                     },
                     success: function(data) {
                         if (data) {
                        		 $("#familyFriendForm").resetForm();
                        		 $.each($("#familyFriendForm").find("input, select"),
                        				 function() {
                        			 $(this).val(data[$(this).attr("name")]);
                        		 });
                        		 $("#modal-formLxr div.modal-header h4").html("<i class='ace-icon fa fa-edit'></i>家庭成员信息");
                                 $("#modal-formLxr").modal("show");
                                 $("#familyMembers").attr("value",'1');
                         } else {
                         	utils.alert.err("<strong>因未知错误，获取信息失败！</strong>");
                         }
                     }
                 });
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
                            className: "btn-warning btn-sm"
                        }
                    },
                    callback: function(result) {
                        if (result) {
                            var id = $this.data("id");
                            viewSelf.model.deleteLxr(id, function(data){
                            	if (data.success) {
                                    //bootbox.alert(_success + "<strong>删除成功!</strong>",function() {});
                                    var datatable = utils.datatable.fresh("#familyTb");
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
        addVerificatPerson: function(){
        	$("#verificatPersonForm").resetForm();
        	utils.validate.removeCss("#verificatPersonForm");
        	$("#verificatPersonForm").find("input:hidden[name='correlativeRelationsId']").val("");
        	$("#modal-verificatPersonForm div.modal-header h4").html("<i class='ace-icon fa fa-plus'></i>核实人信息");
            $("#modal-verificatPersonForm").modal("show");
        },
        submitVerificatPerson: function(){
        	var viewSelf = this;
            if ($("#verificatPersonForm").valid()) {
            	utils.button.ban("#add-VerificatPerson-submit");
            	var formParams=$('#verificatPersonForm').serialize();
            	var params=[];
            	params.push(formParams);
            	params.push('partyId='+ $("#partyId").val());
            	params.push('projectId='+ $("#projectId").val());
            	$.ajax({
                    cache: false,
                    type: "POST",
                    url: $$ctx + "singleCustomer/addVerificatPerson",
                    data: params.join("&"),
                    async: false,
                    error: function(request) {
                        utils.alert.err("错误"+ request.status);
                    },
                    success: function(data) {
                    	utils.button.reset("#add-VerificatPerson-submit");
                    	if(data){
                    		if(data.success){
                    			utils.alert.suc("<strong>"+ data.msg +"</strong>");
                    			$("#verificatPersonTb").dataTable().fnDraw();
                    			$("#modal-verificatPersonForm").modal("hide");
                    		}
                    		else{
                    			utils.alert.warn("<strong>"+ data.msg +"</strong>");
                    		}
                    	}
                    }
                }); //ajax end
            }
        },
        editBtnLiveVerificatPerson: function() {
        	 var viewSelf = this;
             $(document).on("click", "button[role='edit_VerificatPerson']", function(e){
            	 var $this = $(this); 
            	 $.ajax({
                     cache: true,
                     type: "POST",
                     url: $$ctx + "singleCustomer/findOneVerificatPerson",
                     data: {
                         "id": $this.data("id")
                     },
                     async: true,
                     error: function(request) {
                         alert("错误" + request.status);
                     },
                     success: function(data) {
                         if (data) {
                        		 $("#verificatPersonForm").resetForm();
                        		 $.each($("#verificatPersonForm").find("input, select"),
                        				 function() {
                        			 $(this).val(data[$(this).attr("name")]);
                        		 });
                        		 $("#modal-verificatPersonForm div.modal-header h4").html("<i class='ace-icon fa fa-edit'></i>审核人信息");
                                 $("#modal-verificatPersonForm").modal("show");
                         } else {
                         	utils.alert.err("<strong>因未知错误，获取信息失败！</strong>");
                         }
                     }
                 });
             });
        },
        deleteBtnLiveVerificatPerson: function() {
            var viewSelf = this;
            $(document).on("click", "button[role='delete_VerificatPerson']",
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
                            className: "btn-warning btn-sm"
                        }
                    },
                    callback: function(result) {
                        if (result) {
                            var id = $this.data("id");
                            viewSelf.model.deleteVerificatPerson(id, function(data){
                            	if (data.success) {
                                    //bootbox.alert(_success + "<strong>删除成功!</strong>",function() {});
                                    var datatable = utils.datatable.fresh("#verificatPersonTb");
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
        }
        
	});
	module.exports = view;
});