/**
 * 客户列表
 * 
 * author: lijing
 * lastModified: lijing 2014-08-05 16:30:24
 */


define(function(require, exports, module) {
    var model = require("./model");
    var rm = require("./rm");
    var rm_corp = require("./rm_corp");
    var utils = require("utils");
    var controllerUrl = 'corpcustomer';
    var partyId = '';
    var view = Backbone.View.extend({
        el: "body",
        events: {
            "click #btn-query": "condQuery",
            "click #btn-add": "add",
            "click #show-createEntcus": "addOrgCustomer",
            "click #btn-reset": "resetSearchForm"
        },
        initialize: function() {
            /** 初始化 */
            this.model = new model();
            this.render();
        },
        render: function() {
            /** 页面渲染 */
        	//comment by wangyawei 20150505 start 取消客户列表自动刷新
            //this.perparations(); 
        	//comment by wangyawei 20150505 end
            this.initDataTables();
            this.editBtnLive();
            this.initDetailBtn();
            this.initEditBtn();
			this.detailBtnLive();
			this.bizBtnLive();
            this.initSubmitForm();
            this.initSubmitCorpCustomer();
            this.initEndOfAll();
            this.haveError();
        },
        add: function() { // 新增按钮事件
            $("#addCustomerSimpleForm").resetForm();
            $("#add-modal-form div.modal-header h4").html("<i class='ace-icon fa fa-plus'></i> 新增个人客戶");
            $("#add-modal-form").modal("show");
            return false;
        },
        addOrgCustomer: function() {//新增企业客户
        	  $("#model-create").resetForm();
              $("#model-create div.modal-header h4").html("<i class='ace-icon fa fa-plus'></i> 新增企业客戶");
              $("#model-create").modal("show");
              return false;
        },
        editBtnLive: function() { // 修改按钮事件
        	var viewSelf = this;
			$(document).on("click", "button[role='edit']", function(e) { // 动态绑定所有删除按钮的click事件
				var $this = $(this);
				var partyId = $this.data('id');
				$.ajax({
					cache: false,
	                type: "POST",
	                url: $$ctx + "bizapply/checkStatus",
	                data: {
	                	"partyId": partyId
	                },
	                async: true,
	                success: function(result){
	                	if(result.success){
	                		//没有发起业务，允许修改
		                		 $.ajax({
		                             cache: false,
		                             type: "POST",
		                             dataType: "HTML",
		                             url: $$ctx + "singleCustomer/modToForm",
		                             data: {
		                                 "customerId": $this.data("id"),
		                                 "workCode": "TOMOD",
		                                 "customerSource": "once",
		                                 "consultLocation": "customer"
		                             },
		                             // 
		                             async: false,
		                             error: function(request) {
		                             	utils.alert.err("<strong>因未知错误，不能完成跳转！</strong>");
		                             },
		                             success: function(data) {
		                             	if(data == "notBangxindaiCustomer"){
		                             		utils.alert.warn("非微贷系统客户，暂不支持修改");
		                             		//comment by wangyawei 20150428 start 暂时关闭非微贷客户的修改功能
		                                 	/*if (bootbox.confirm({
		                                         message: "非微贷客户，您确定要修改吗?",
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
		                                                     url: $$ctx + 'singleCustomer/modToForm',
		                                                     data: {
		                                                         "customerId": $this.data("id"),
		                                                         "workCode": "TOMOD",
		                                                         "customerSource": "twice",
		                                                         "consultLocation": "customer"
		                                                     },
		                                                     dataType: 'HTML',
		                                                     type: 'POST',
		                                                     error: function(request) {
		                                                     	utils.alert.warn("跳转出错");
		                                                     },
		                                                     success: function(data) {
		                                                     	window.location.href = $$ctx + data;
		                                                     }
	
		                                                 });
		                                             }
		                                         }
		                                     }));*/
		                             		//comment by wangyawei 20150428 end
		                                 }else{
		                                 	window.location.href = $$ctx + data;
		                                 }
		                             }
		                         });
	                	}else{
	                		//如果正在发起业务，则不允许修改
	                		utils.alert.warn("<strong>此客户有正在处理中的业务，不允许修改。</strong>");
	                	}
	                }
				});
			});
        },
		initEditBtn : function (){//初始化编辑按钮
			$(document).on("click","button[role='editCorpDetail']",function(e){
				var partyId = $(this).attr("data-partyId");
				//modify by wangyawei 20150428 start 暂时关闭非微贷客户的修改功能
				var sysCd = $(this).attr("data-sysCd");
				if(utils.str.notContains(sysCd, "2") && utils.str.notContains(sysCd, "3")){
					utils.alert.warn("非微贷系统客户，暂不支持修改");
					return;
				}
				//modify by wangyawei 20150428 end
				window.location.href=$$ctx + controllerUrl +"/toEdit/" + partyId+"?consultLocation=";
			});
		},
		initDetailBtn : function (){//初始化查看按钮
			$(document).on("click","button[role='showCorpDetail']",function(e){
				var partyId = $(this).attr("data-partyId");
				window.location.href=$$ctx + controllerUrl +"/showDetail/" + partyId+"?consultLocation=";
			});
		},
        detailBtnLive: function() { // 查看按钮事件
        	var viewSelf = this;
			$(document).on("click", "button[role='detail']", function(e) { // 动态绑定所有删除按钮的click事件
				var $this = $(this);
                $.ajax({
                    cache: false,
                    type: "POST",
                    url: $$ctx + "singleCustomer/modToForm",
                    data: {
                        "customerId": $this.data("id"),
                        "workCode": "TODETAIL",
                        "customerSource": "detail", 
                        "consultLocation": "customer"//查看位置
                    },
                    // 
                    async: true,
                    error: function(request) {
                    	utils.alert.err("<strong>因未知错误，不能完成跳转！</strong>");
                    },
                    success: function(data) {
                        window.location.href = $$ctx + data;
                    }
                });
                return false;
			});
        },
        bizBtnLive: function() {
        	$("#select_product_form").validate({
//    			errorPlacement: function (error, element) { //指定错误信息位置
//    				if (element.is(':radio') || element.is(':checkbox')) { //如果是radio或checkbox
//    					var eid = element.attr('name'); //获取元素的name属性
//    					error.appendTo(element.parent().parent()); //将错误信息添加当前元素的父结点后面
//    				} else {
//    					error.insertAfter(element);
//    				}
//    			},
    			submitHandler : function(form) {
    				var productCd = $('#productCd').val();
    				var bizType = $("#select_product_form").find("input:radio[name=bizType]:checked").val();
    				//bizType="biz";
    	        	if(productCd && bizType) {
    	        		$('#select_product_sure').attr('disabled', true);
    	    			window.location.href= $$ctx + "bizapply/"+partyId+"/"+productCd+"/"+bizType;
    	        	}
    			}
    		});
        	$(document).on("click", "button[role='biz']", function(e) { // 动态绑定所有发起新业务按钮的click事件
        		$("#select_product_form").find("input:radio[name=bizType]:checked").removeAttr("checked");
        		var $this = $(this);
        		//alert('test');
				partyId = $this.data('id');
				$('#productCd').val('');
				$('#parentProductCd').val('');
				$('#productDesc').html('');
				$.ajax({
	                cache: false,
	                type: "POST",
	                url: $$ctx + "bizapply/checkStatus",
	                data: {
	                	"partyId": partyId
	                },
	                async: true,
	                success: function(result) {
	                	if (result.success) {
	                		$.ajax({
	        	                type: "POST",
	        	                url: $$ctx + "bizapply/checkCreditAppStatus",
	        	                data: {
	        	                	"partyId": partyId
	        	                },
	        	                success: function(result) {
	        	                	if(result.success) {
	        	                		/**初始化树*/
	        	                        var viewSelf = this;
	        	                        $.fn.zTree.init($("#tree"), {
	        	                            async: {
	        	                                enable: true,
	        	                                url: $$ctx + "bizapply/findEffectiveProduct",
	        	                                otherParam: [
	             	                            	'partyId', result.data.partyId,
	             	                            	'isDesignated', false
	             	                            ]
	        	                            },
	        	                            data: {
	        	                                simpleData: {
	        	                                    enable: true,
	        	                                    idKey: "productCd",
	        	                                    pIdKey: "parentProductCd"
	        	                                },
	        	                                key: {
	        	                                    name: "productName"
	        	                                }
	        	                            },
	        	                            check: {
	        	                                enable: true,
	        	                                chkStyle: "radio",
	        	                                radioType: "all"
	        	                            },
	        	                            callback: {
	        	                                onClick: function(event, treeId, treeNode) {
	        	                                	if(treeNode!=null&&treeNode.children!=null&&treeNode.children.length!=null&&treeNode.children.length>0){
	        	                                		return false;
	        	                                	}else{
	        	                                		if(treeNode.productDesc)
	        	                                			$('#productDesc').html('　　' + treeNode.productDesc);
	        	                                		else
	        	                                			$('#productDesc').html('');
	        	                                		var productCd = treeNode.productCd;
	        	                                    	var treeObj = $.fn.zTree.getZTreeObj(treeId);
	        	                                    	var node = treeObj.getNodeByParam("productCd", productCd, null);
	        	                                    	treeObj.checkNode(node, true, true);
	        	                                    	var parentNode = node.getParentNode();
	        	                                        treeObj.expandNode(parentNode, true, false);
	        	                                        $('#productCd').val(treeNode.productCd);
	        	                                        $('#parentProductCd').val(treeNode.parentProductCd);
	        	                                	}
	        	                                },
	        	                                onCheck: function(event, treeId, treeNode) {
	        	                                	if(treeNode.productDesc)
	                                        			$('#productDesc').html('　　' + treeNode.productDesc);
	        	                                	else
	                                        			$('#productDesc').html('');
	        	                                	$('#productCd').val(treeNode.productCd);
	        	                                	$('#parentProductCd').val(treeNode.parentProductCd);
	        	                                },
	        	                                beforeCheck: function(treeId, treeNode) {
	        	                                    return ! treeNode.isParent;
	        	                                },
	        	                                onAsyncSuccess : function(){
	        		                            	var treeObj = $.fn.zTree.getZTreeObj("tree");
	        		     	        				treeObj.expandAll("true");
	        		                            }
	        	                            }
	        	                        });
	        	                        $("#select_product_modal").modal("show");
	        	                        $("#select_product_form div.modal-header h4").html("<i class='ace-icon fa fa-plus'></i> 选择产品");
	        	                	} else {
	        	                		utils.alert.warn(result.msg);
	        	                	}
	        	                }
	                		});
						}else{
							utils.alert.warn(result.msg);
						}
	                }
	            });
                return false;
			});
        	
        	$(document).on("click", "button[role='corpBizStart']", function(e) { // 动态绑定所有发起新业务按钮的click事件
        		$("#select_product_form").find("input:radio[name=bizType]:checked").removeAttr("checked");
        		var $this = $(this);
				partyId = $this.data('id');
				$('#productCd').val('');
				$('#parentProductCd').val('');
				$('#productDesc').html('');
				$.ajax({
	                cache: false,
	                type: "POST",
	                url: $$ctx + "corpcustomer/checkCorpCusBizStatus",
	                data: {
	                	"partyId": partyId
	                },
	                async: true,
	                success: function(result) {
	                	if (result.success) {
	                		/**初始化树*/
	                        var viewSelf = this;
	                        $.fn.zTree.init($("#tree"), {
	                            async: {
	                                enable: true,
	                                url: $$ctx + "bizapply/findEffectiveProduct",
	                                otherParam: [
     	                            	'partyId', result.data.partyId,
     	                            	'isDesignated', false
     	                            ]
	                            },
	                            data: {
	                                simpleData: {
	                                    enable: true,
	                                    idKey: "productCd",
	                                    pIdKey: "parentProductCd"
	                                },
	                                key: {
	                                    name: "productName"
	                                }
	                            },
	                            check: {
	                                enable: true,
	                                chkStyle: "radio",
	                                radioType: "all"
	                            },
	                            callback: {
	                                onClick: function(event, treeId, treeNode) {
	                                	if(treeNode!=null&&treeNode.children!=null&&treeNode.children.length!=null&&treeNode.children.length>0){
	                                		return false;
	                                	}else{
	                                		if(treeNode.productDesc)
	                                			$('#productDesc').html('　　' + treeNode.productDesc);
	                                		else
	                                			$('#productDesc').html('');
	                                		var productCd = treeNode.productCd;
	                                    	var treeObj = $.fn.zTree.getZTreeObj(treeId);
	                                    	var node = treeObj.getNodeByParam("productCd", productCd, null);
	                                    	treeObj.checkNode(node, true, true);
	                                    	var parentNode = node.getParentNode();
	                                        treeObj.expandNode(parentNode, true, false);
	                                        $('#productCd').val(treeNode.productCd);
	                                        $('#parentProductCd').val(treeNode.parentProductCd);
	                                	}
	                                },
	                                onCheck: function(event, treeId, treeNode) {
	                                	if(treeNode.productDesc)
                                			$('#productDesc').html('　　' + treeNode.productDesc);
	                                	else
                                			$('#productDesc').html('');
	                                	$('#productCd').val(treeNode.productCd);
	                                	$('#parentProductCd').val(treeNode.parentProductCd);
	                                },
	                                beforeCheck: function(treeId, treeNode) {
	                                	if(treeNode.productCd==283){//企业客户不能发邦易贷
	                                		return false;
	                                	}
	                                    return ! treeNode.isParent;
	                                },
	                                onAsyncSuccess : function(){
		                            	var treeObj = $.fn.zTree.getZTreeObj("tree");
		     	        				treeObj.expandAll("true");
		                            }
	                            }
	                        });
	                        $("#select_product_modal").modal("show");
	                        $("#select_product_form div.modal-header h4").html("<i class='ace-icon fa fa-plus'></i> 选择产品");
						}else{
							utils.alert.warn("<strong>" + result.msg + "</strong>");
						}
	                	
	                	
	                }
	            });
                return false;
			});
        },
        initSubmitForm: function() { // 基础信息的提交按钮

            $("#addCustomerSimpleForm").validate({
                rules: rm.rules,
                messages: rm.messages,
                submitHandler: function(form) {
                    $.ajax({
                        cache: false,
                        type: "POST",
                        url: $$ctx + "singleCustomer/saveSimple",
                        data: $('#addCustomerSimpleForm').serialize(),
                        async: false,
                        error: function(request) {
                            utils.alert.warn("add-simple-submit报错" + request);
                        },
                        success: function(data) {
                            if (data == "CeritificateRepeatedError") {
                            	utils.alert.warn("<strong>证件号码已被登记过！</strong>");
                            } 
                            else{
                                window.location.href = $$ctx + data;
                            }

                        }
                    });
                    return false;
                },
                errorPlacement: function(error, element) {
                    if (element.is(":radio")) error.appendTo(element.parent().next().next());
                    else if (element.is(":checkbox")) error.appendTo(element.next());
                   
                    else error.appendTo(element.parent());
                }
            });

        },
		initSubmitCorpCustomer : function(){
			
			var _alert = bootbox.alert;
			 $("#createEntForm").validate({
	                rules: rm_corp.rules,
	                messages: rm_corp.messages,
	                submitHandler: function(form) {
	                    $.ajax({
	                        cache: false,
	                        type: "POST",
	                        url: $$ctx + controllerUrl + "/addEntCustomer?consultLocation=",
	                        data: $('#createEntForm').serialize(),
	                        async: false,
	                        error: function(request) {
	                        	utils.alert.err("创建客户时出现错误");
	                        },
	                        success: function(result) {
	                        	if(result.success){
	                        		window.location.href = $$ctx + controllerUrl + "/toEdit/" + result.data + "?consultLocation=";
	                            }else{
	                            	utils.alert.warn(result.msg);
	                            }
	                        }
	                    });
	                    return false;
	                },
	                errorPlacement: function(error, element) {
	                	if (element.is(":checkbox")) error.appendTo(element.next());
	                     else error.appendTo(element.parent());
	                }
	            });
			
		},
        haveError: function() {
        	var error = $('#error').val();
        	var errorDateMessage = $("input[name='errorDateMessage']").val();
        	if(error)
        		utils.alert.warn(error);
        	if(errorDateMessage != '') {
        		utils.alert.warn("<strong>" + errorDateMessage + "</strong>");
        		$("input[name='errorDateMessage']").val("");
        		$("#certificateType").val(null);
        	}
        	try{history.pushState({},"",$$ctx+"singleCustomer");
        	}catch(e){}
        },
        perparations: function() {
//        	$("input[name='errorDateMessage']").each(function(index,element){
//        		if($(element).val()!=""){
//        			utils.alert.warn("<strong>"+$(element).val()+"</strong>");
//            		$(element).val("");
//        		}
//        	});
	       	var longPolling = function(){
	       		var count = /^(\u5171\s?)(\d+)(\s+\u6761)$/.exec($("#tbCustomers_info").text().split("\uff0c")[1]);
	        	$.ajax({
	                //cache: true,
	                type: "POST",
	                url: $$ctx + "singleCustomer/longAjax",
	                data: {
	                	"timed": new Date().getTime(),
	                	"queryCount": count?count[2]:null,
	                },
	                // 
	                async: true,
	                error: function(request,textStatus) {
	                	if (textStatus == "timeout") { // 请求超时
	                		longPolling(); // 递归调用
	                    // 其他错误，如网络错误等
	                    } else { 
	                    	longPolling();
	                    }
	                },
	                success: function(data,textStatus) {
	                	if(data&&data.success){
	                		$("#tbCustomers").dataTable().fnDraw();
	                	}
	                	if(textStatus=="success"){
	                			longPolling();
	                	}
	                }
	        	});
	        };
	        setTimeout(function(){
	        	longPolling();
	        },60000);
	        //$("#certificateType").val(null);
            /*$("#markType").val(null);*/
        },
        resetSearchForm: function() {
        	 $("#exec_search_form").resetForm();
            /* $("#markType").val(null);*/
             return false;
        },
        condQuery: function() { // 绑定精确查询按钮
            var datatable = $('#tbCustomers').dataTable();
            datatable.fnSettings()._iDisplayStart = 0;
            this.oTable.refreshParamCache();
            datatable.fnDraw();
            return false;
        },
        initDataTables: function() {
            /** 初始化DataTables */
            var viewSelf = this;
            var status = null;
            utils.dd.initDataDict(["CustomerType", "CertificateType","CustomerStatusCode","CustomerMarkType"], function(dataDict) {
            var oTable = $("#tbCustomers").dataTable({
                "sAjaxSource": $$ctx + "singleCustomer/findBySearch",
                "bFilter": false,
                "bLengthChange": false,
                "aoColumns": [null, null, null, null, null, null, null, null, null, null, null],
                "aoColumnDefs" : [
                                { "bVisible": false, "aTargets": [0] },
                                {
                                    "aTargets" : [2],
                                    "mRender" : function(data, type, full){
                                    	return "<div class='ellipsis' style='width:100px;' title='"+ data +"'>"+ data +"</div>";
                                    }
                                },
                                {
                                    "aTargets" : [9],
                                    "mRender" : function(data, type, full){
                                    	if(data == null || data == ""){
                                    		return "数据未填写";
                                    	}else if(data == "01,02"){
                                    		return "借款人/担保人";
                                    	}else{
                                    	return dataDict.CustomerMarkType[data];
                                    	}
                                    }
                                },
                                {
                                    "aTargets" : [3],
                                    "mRender" : function(data, type, full){
                                    	return dataDict.CustomerType[data];
                                    }
                                },
                                {
                                    "aTargets" : [4],
                                    "mRender" : function(data, type, full){
                                    	return dataDict.CertificateType[data];
                                    }
                                },
                                {
                                    "aTargets" : [6],
                                    "mRender" : function(data, type, full){
                                    	status = data;
                                    	
                                    	if(data == null || data==""){
                                    		return "数据未填写";
                                    	}else{
                                    		return dataDict.CustomerStatusCode[data];
                                    }
                                }
                                },
                                //{ "bVisible": false, "aTargets": [9] },
                                
                                {
                                    "aTargets" : [10],
                                    "mRender" : function(data, type, full){
                                    	var buttons = "";
                                    	//担保人不能发起业务 modify gph
                                    	var _available = false;//只要是类型是借款人，都可以发起业务
                                		if(full[9]){
                                			if(full[9] == "01" || full[9] == "01,02"){
                                				_available = true;
                                			}
                                		}
                                    	if(full[3]=="1"){
                                    		var edit = "<button data-partyid='"+full[0]+"' class='btn btn-xs btn-info' role='editCorpDetail' data-toggle='tooltip' data-placement='bottom' title='编辑' data-sysCd='"+full[12]+"'><i class='ace-icon fa fa-edit'></i></button>";
                                    		var view =  " <button type='button' role='showCorpDetail' data-partyid='"+full[0]+"' data-toggle='tooltip' data-placement='bottom' class='btn btn-xs btn-yellow' title='查看'><i class='ace-icon fa fa-eye'></i></button> ";
                                    		var service = "<button type='button' role='corpBizStart' data-id='" + full[0] + "'  class='btn btn-xs btn-info' title='发起业务' ><i class='ace-icon fa fa-send'></i></button> ";
                                    		if(full[11] == "01"){
                                    			buttons+= edit+view;
                                    		}else{
                                    			var $edit = $(edit).attr("disabled","disabled");
                                    			buttons+= $edit[0].outerHTML+view;
                                    		}
                                    		if(status == null || status==1||($.inArray(full[11],["01","02"])<0) || _available == false){
                                    			var $service = $(service).removeAttr("role").attr("disabled","disabled");
                                    			buttons += $service[0].outerHTML;
                                         		return buttons;
                                         	}else{
                                         		buttons += service;
                                         		return buttons;
                                         	}
                                    		buttons = "<div style='width:100px;'>"+buttons+"</div>";
//                                    		buttons = "<div style='display:flex'>"+buttons+"</div>";
                                    		return buttons;
                                    	}else if(full[3]=="2"){
                                    		 var edit = "<button type='button' role='edit' data-id='" + full[0] + "'  class='btn btn-xs btn-info' title='修改' ><i class='ace-icon fa fa-edit'></i></button> ";
                                     		 var view = " <button type='button' role='detail' data-id='" + full[0] + "'  class='btn btn-xs btn-yellow' title='查看'><i class='ace-icon fa fa-eye'></i></button> ";
                                    		 var service = "<button type='button' role='biz' data-id='" + full[0] + "'  class='btn btn-xs btn-info' title='发起业务'><i class='ace-icon fa fa-send'></i></button> ";
                                    		 if(full[11] == "01"){//只有管护权客户经理才可以修改信息
                                     			buttons+= edit+view;
                                     		}else{
                                     			var $edit = $(edit).attr("disabled","disabled");
                                     			buttons+= $edit[0].outerHTML+view;
                                     		}
                                    		 //客户状态为null || 未生效 || 不包含借款人的客户不能发起业务
                                     		if(status == null || status==1||($.inArray(full[11],["01","02"])<0) || _available == false){
                                     			var $service = $(service).removeAttr("role").attr("disabled","disabled");
                                     			buttons += $service[0].outerHTML;
                                     			buttons = "<div style='width:100px;'>"+buttons+"</div>";
                                        		return buttons;
                                          	}else{
                                          		buttons += service;
                                          		buttons = "<div style='width:100px;'>"+buttons+"</div>";
                                        		return buttons;
                                          	}
                                    	}
                                    }
                                },
                               { "bVisible": false, "aTargets": [11] ,
                                	  "mRender" : function(data, type, full){
                                		  return 0;
                                	  }
                                }
                            ],
                    "fnServerParams": function(aoData) {
                    	if(!this.serverParamCache){
                    		this.refreshParamCache = function(){
                            	this.serverParamCache = [{
                                    "name": "customerName",
                                    "value": $('#customerName').val()
                                },
                                {
                                    "name": "customerType",
                                    "value": $('#customerType').val()
                                },
                                {
                                    "name": "certificateType",
                                    "value": $('#certificateType').val()
                                },
                                {
                                    "name": "certificateCode",
                                    "value": $('#certificateCode').val()
                                },
                                {
                                    "name": "markType",
                                    "value": null //$('#markType').val()
                                }];
                            };
                            
                            this.refreshParamCache();
                    	}
                    	$(this.serverParamCache).each(function(i,d){
                    		aoData.push(d);
                    	});
                    }
            });
                
            
            viewSelf.oTable = oTable;
          });
            
           
        },
        initEndOfAll: function() {
        	
        	/*$('#tbCustomers tbody tr').click(function (event) {
        		alert("!");
            	$(this).find("input[type = 'radio' ]").attr("checked", true);
            });*/
        }

    });
    module.exports = view;
});