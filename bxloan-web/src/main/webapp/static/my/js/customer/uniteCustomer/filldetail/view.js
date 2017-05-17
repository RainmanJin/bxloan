/**
 * 联保体客户详细
 * 
 * author: lijing
 * lastModified: lijing 2014-08-05 16:30:24
 */

define(function(require, exports, module) {
    var model = require("./model");
    var modalModule = require("./modalfunc");
    var rm = require("./rm");
    var utils = require("utils");
    var controllerUrl = $$ctx + 'unitecustomer/';
    var $hidden = $("#hiddenParams");
    var view = Backbone.View.extend({
        el: "body",
        events: {
        	"click #saveMember" : "saveMember",
        	"click #btn-add" : "addUgnMember",
        	"click button[role='customerSure']":"addMembersOfBatch",
        	"change :input[name='all_cust']":"fnChangeAllCust",
        	"click a[role='custDetail']":'fnShowCustDetail'
        },
        initialize: function() {
            /** 初始化 */
        	var viewMode={
        			isEdit:utils.parseBool($("#isEdit").val()),
        			isView:utils.parseBool($("#isView").val())
        	}
        	this.viewMode=viewMode;//显示方式
            this.model = new model();
            this.render();
        },
        render: function() {
            /** 页面渲染 */
            this.initFirstOfAll();
            this.initDateInput();
            this.initUgnMessage();
            this.initBasicInfoForm();
            this.initMembersDataTable();
            this.initMemberButtons();
            this.initMemberForm();
            this.initProtocalBtn();
            //this.initEndOfAll();
        },
        initFirstOfAll: function() {
            /**所有渲染开始之前所做的操作*/
        	$("#hiddenParams input[name='nowDate']").val(utils.date.formatDate(new Date().getTime()));
            $("form").find("div.form-group:has('.required')").find("label").prepend("<font color='red'>*</font>");
            this.controlButtons();
        },
        initDateInput: function() {
            var viewSelf = this;
            var $form = $("#form-basicInfo");
            $form.find(":text[name='begDateStr']").datepicker({
                clearBtn: true,autoclose:true
            });
            $form.find(":text[name='endDateStr']").datepicker({
                clearBtn: true,autoclose:true
            });
            $form.find(":text[name='unSigeDateStr']").datepicker({
                clearBtn: true,autoclose:true
            });
            $form.find(":text[name='segiDateStr']").datepicker({
                clearBtn: true,autoclose:true
            });
            $form.find(":text[name$='DateStr']").datepicker({
                todayBtn: true,
                autoclose: true
            }).attr("style", "background-color:#FFF!important").on("show",
            function() {
                $(".datepicker").css("z-index", "99999");
            }).on("changeDate",function(e){
            	$("#form-basicInfo").validate().element($(this));
			}).prop("readonly", true);
        },
        initUgnMessage: function() {
            var viewSelf = this;
            var id =$hidden.find(":hidden[name='id']").val();//联保体id
            var $form=$("#form-basicInfo");
            viewSelf.model.getUgnInfo(id,function(r) {	
                if (r.data) {
                	var result = r.data;
                	utils.forms.putValueToForm(result,$form);
                    
                    if($hidden.find(":hidden[name='action']").val()=="view"){
                    	//查看
                    	$form.find("input, select, textarea").prop("disabled", true).removeAttr("style");
                    	$("button[role='custManager_btn']").prop("disabled", true);
                    	$("#saveBasicInfo").remove();
                    }else{
                    	//新增，修改
                    	modalModule.createAliveBtn();
                    	modalModule.initCustManagerDatatable();
                    	modalModule.initCustFormBtn();
                    	$("button[role='custManager_btn']").on("click", function(e){
                    		$("div[role='custManagerModal'] h4.modal-title").text("选择客户经理");
                    		$("div[role='custManagerModal']").modal("show");
                    	});
                    }
                }
            });
        },
        initBasicInfoForm: function() {
        	var viewSelf=this;
        	 $("#form-basicInfo").validate({
                 rules: rm.rules,
                 messages: rm.messages,
                 errorPlacement: function(error, element) {
                     if (element.is(":radio")) error.appendTo(element.parent().next().next());
                     else if (element.is(":checkbox")) error.appendTo(element.next());
                     else if (element.next().is("span[class='input-group-btn']")) {
                    	 error.appendTo(element.closest("span"));
                     }
                     else error.appendTo(element.parent());
                 },
                 submitHandler: function(form) {
	            	var $form=$(form);
	            	var formSubmit=$form.find(':button[role="saveBasicInfo"]');
	            	utils.button.ban(formSubmit);
	             	viewSelf.model.saveBasicInfo($form.serialize(),function(data){
                 		if(data&&data.success){
                 			utils.alert.suc("<strong>"+ data.msg +"</strong>",function(){
                 				utils.button.reset(formSubmit);
                 			});
                 		}else{
                 			utils.alert.warn("<strong>"+ data.msg +"</strong>",function(){
                 				utils.button.reset(formSubmit);
                 			});
                 		}
	             	});
	             	return false;
                 }
             });
        },
        initMembersDataTable: function(){
        	
            	/**
            	 * 初始化成员列表
            	 * */
            	 var viewSelf = this;
            	 utils.dd.initDataDict(["CustomerStatusCode"], function(dataDict) {
                 var oTable = $("#tbUniteMg").dataTable({
                     "sAjaxSource": controllerUrl + "findUniteMembersBySearch",
                     "bFilter": false,
                     "bAutoWidth": true,
                     "bLengthChange": false,
                     "aoColumns": [
                     {	
                    	 "bVisible":false,
                         "mData": "id"
                     },
                     {
                         "mData": "customerNum",
                         mRender: function(data, type, rowdata) {
                        	 var html=[];
                        	 html.push('<a role="custDetail" href="javascript:void(0);" data-party-id="'+rowdata.partyId+'">');
                        	 html.push(data);
                        	 html.push('</a>');
                        	 return html.join('');
                         }
                     },
                     {
                         "mData": "customerName",
                     },
                     {
                         "mData": "status",
                         mRender: function(data, type, rowdata) {
                        	 if(data){
                        		 return dataDict.CustomerStatusCode[data];
                        	 }else{
                        		 return "";
                        	 }
                         }
                     },
                     {
                         "mData": "partyId",
                         mRender: function(data, type, rowdata) {
                        	 var status = $("#form-basicInfo").find("input[name='unStatus']").val();
                        	 var html=[];
                        	 /*html.push("<button type='button' role='editMember' data-id='" 
                          	        + rowdata.id + "' data-partyid = '"+ data +"' class='btn btn-xs btn-info'><i class='ace-icon fa fa-edit' title='修改'></i></button> ");*/
                        	 //html.push("<button type='button' role='detailMember'  data-id='" + rowdata.id + "' data-partyid = '"+ data +"' class='btn btn-xs btn-yellow' title='查看' ><i class='ace-icon fa fa-eye'></i></button> ");
                        	 
                        	 if(viewSelf.viewMode.isEdit){
                        		 if(status=="1"||status=="3"){
                        			 //预生效或失效， 删除按钮
                        			 html.push("<button type='button' role='deleteMember' data-id='" + rowdata.id + "' data-partyid = '"+ data +"' class='btn btn-xs btn-danger'><i class='ace-icon fa fa-trash-o' title='删除'></i></button>");
                        		 }else if(status=="2"){
                        			 //生效， 成员退出
                        			 html.push("<button type='button' role='exitMember' data-id='" + rowdata.id + "' data-partyid = '"+ data +"' class='btn btn-xs btn-danger'><i class='ace-icon fa fa-trash-o' title='成员退出'></i></button>");
                        		 }
                        	 }
                        	 
                         	return html.join('');
                         }
                     }
                    ],
                     "fnServerParams": function(aoData) {
                         aoData.push({
                             "name": "unGuId",
                             "value": $("#hiddenParams").find("input[name='unGuId']").val()
                         });
                     },
                     "fnDrawCallback": function(){
                    	 if($("#hiddenParams").find("input[name='action']").val()=="view"){
                    		 $("#tbUniteMg").find("button[role='exitMember']").remove();
                    		 $("#tbUniteMg").find("button[role='editMember']").remove();
                    	 }
                     }
                 });
                 viewSelf.oTable = oTable;
            	});
        },
        addUgnMember:function(){//新增联保体成员
        	var viewSelf=this;
			var $custSelTable=$("#custSelTable").dataTable();//客户选取列表
			$custSelTable.fnSettings()._iDisplayStart = 0;
			$custSelTable.fnDraw();
			var customerModal= $("div[role='customerModal']");
			customerModal.find(":input[name='all_cust']").prop("checked",false).change();//重置全选
			customerModal.modal("show");
        	/*$("#form-members").resetForm();
    		viewSelf.hideButtons(false);
    		$("#saveMember").data("action","add");
    		$("#widget-member").show();*/
        },
        initMemberButtons: function(){
        	var viewSelf = this;
        	//新增成员
        	/*$(document).on("click","#btn-add", function(e){
        		$("#form-members").resetForm();
        		viewSelf.hideButtons(false);
        		$("#saveMember").data("action","add");
        		$("#widget-member").show();
        	});*/
        	//修改成员
        	$(document).on("click", "button[role='editMember']",function(e) {
        	    var $this = $(this);
        	    $("#form-members").find("input[name='meb_customerId']").val($this.data('partyid'));
        	    $("#form-members").find("input[name='meb_customerNum']").val($this.closest("tr").find("td:eq(0)").text());
        	    $("#form-members").find("input[name='meb_customerName']").val($this.closest("tr").find("td:eq(1)").text());
        	    $("#form-members").find("input[name='meb_id']").val($this.data('id'));
          	  
        	    $("#custSelTable").find("input[type='radio']").prop("checked",false);
        		$("#saveMember").data("action","update");
        		viewSelf.hideButtons(false);
        		$("#widget-member").show();
        		
        	});
        	//查看成员
        	$(document).on("click", "button[role='detailMember']",function(e) {
        	    var $this = $(this);
        	    $("#form-members").find("input[name='meb_customerNum']").val($this.closest("tr").find("td:eq(0)").text());
        	    $("#form-members").find("input[name='meb_customerName']").val($this.closest("tr").find("td:eq(1)").text());
          	  
        		$("#saveMember").data("action","view");
        		viewSelf.hideButtons(true);
        		$("#widget-member").show();
        	});
        	//删除成员
        	$(document).on("click", "button[role='deleteMember']",function(e) {
        	    var $this = $(this);
        	    var id = $this.data('id');
        	    $("#widget-member").hide();
        	    utils.button.confirm(this, function(result){
        	    	if (result) {
                        viewSelf.model.delMember(id,
                        function(r) {
                            if (r.success) {
                                utils.datatable.fresh("#tbUniteMg");
                                $("#custSelTable").dataTable().fnDraw();
                                utils.alert.suc("删除成功！");
                            } else {
                            	utils.alert.err("<strong>"+ r.msg +"</strong>");
                            }
                        });
                    }
        	    });
        	});
        	//成员退出
        	$(document).on("click", "button[role='exitMember']", function(e){
        		var $this = $(this);
        	    var id = $this.data('id');
        	    $("#widget-member").hide();
        	    utils.button.confirm(this, function(result){
        	    	if (result) {
                        viewSelf.model.exitMember({
                        	"id" : $this.data('id'),
                        	"unGuId" : $("#hiddenParams").find("input[name='unGuId']").val()
                        },
                        function(r) {
                            if (r.success) {
                                utils.datatable.fresh("#tbUniteMg");
                                $("#custSelTable").dataTable().fnDraw();
                                utils.alert.suc("退出成功！");
                            } else {
                            	utils.alert.err("<strong>"+ r.msg +"</strong>");
                            }
                        });
                    }
        	    });
        	});
        },
        initMemberForm: function(){
        	var viewSelf = this;
        	 $("#form-members").validate({
        		 rules: {
        			 meb_customerNum : {
        				 required:true 
        			 },
        			 meb_customerName : {
        				 required:true 
        			 }
        		 },
                 errorPlacement: function(error, element) {
                     if (element.is(":radio")) error.appendTo(element.parent().next().next());
                     else if (element.is(":checkbox")) error.appendTo(element.next());
                     else if (element.next().is("span[class='input-group-btn']")) {
                    	 error.appendTo(element.closest("div.col-sm-3"));
                     }
                     else error.appendTo(element.parent());
                 }
             });
        },
        saveMember: function(){
        	var viewSelf = this;
        	if ($("#form-members").valid()) {
        		var action = $("#saveMember").data("action");
            	utils.button.ban("#saveMember");
            	viewSelf.model.saveMember($('#form-members').serialize() + "&action=" + action + "&unGuId=" + $("#hiddenParams").find("input[name='unGuId']").val(), 
            		function(data){
            		utils.button.reset("#saveMember");
                	if(data){
                		if(data.success){
                		$("#tbUniteMg").dataTable().fnDraw();
                		$("#custSelTable").dataTable().fnDraw();
                		utils.alert.suc("<strong>"+ data.msg +"</strong>");}
                		else{
                			utils.alert.warn("<strong>"+ data.msg +"</strong>");
                		}
                	}
            	});
            }
        },
        initProtocalBtn : function(){
        	/**协议生效*/
        	var viewSelf = this;
        	$(document).on("click", "#changeProtocol", function(e){
        		e.stopPropagation();
        		var $this = $(this);
        		viewSelf.model.changeProtocol({
        			"unGuId" : $("#form-basicInfo").find("input[name='unGuId']").val(),
        			"action" : $this.data("status")
        		},function(data){
        			if(data&&data.success){
        				utils.alert.suc("操作成功", function(e){
        					window.location.href = controllerUrl;
        				});
        			}else{
        				utils.alert.warn(data.msg);
        			}
        		});
        	});
        },
        hideButtons : function(flag){
        	if(flag){
        		$("button[role='customer_btn']").prop("disabled",true);
        		$("#saveMember").prop("disabled",true);
        	}else{
        		$("button[role='customer_btn']").prop("disabled",false);
        		$("#saveMember").prop("disabled",false);
        	}
        },
        controlButtons: function(){//联保体成员
        	var action = $("#hiddenParams").find("input[name='action']").val();
        	if(action!="view"){
        		$("#myTab li:contains('联保体成员信息')").on("click", function(e){
        			if(!$("#showAlive #btn-add")[0]){
        				var $this = $(this);
        				var addButton = " <button id='btn-add' class='btn btn-sm btn-success'><i class='ace-icon fa fa-plus'></i>添加成员</button>";
        				$("#showAlive").html($("#showAlive").html()+addButton);	
        			}
        		});
        		$("#myTab li:not(:contains('联保体成员信息'))").on("click", function(e){
        			$("#showAlive #btn-add").remove();
        		});
        	}
        	$("#widget-member div.widget-header a[data-action='close']").on("click",function(e){
        		e.stopPropagation();
        		e.preventDefault();
        		$("#widget-member").hide();
        	});
        	modalModule.initCustomersDatatables();
        	modalModule.initCustomerFormBtn();
        	$("button[role='customer_btn']").on("click", function(e){
        		$("div[role='customerModal']").modal("show");
        	});
        	//modalModule.initCustFormBtn();
        },
        addMembersOfBatch:function(e){//批量添加成员
        	var viewSelf=this;
        	e.stopPropagation();
        	var $this=$(e.currentTarget);
        	utils.button.ban($this);//禁用
    		var custIds =[];//所选客户id
    		$.each($(":input[name='sel_cust_id']:checked"),function(i,v){
    			custIds.push($(v).val());
    		});
    		if(custIds.length==0){
    			utils.alert.warn("<strong>请选择客户</strong>",function(){
    				utils.button.reset($this);//启用
    			});
    			return;
    		}
    		var params=[];
    		params.push("ugnId="+$("#ugnId").val());//联保体id
    		params.push("custIds="+custIds.join(','));//客户id集合
    		viewSelf.model.addMembersOfBatch(params.join('&'),function(r_data){
    			if(r_data&&r_data.success){
         			utils.alert.suc("<strong>"+ r_data.msg +"</strong>",function(){
         				var $tbUniteMg=$("#tbUniteMg").dataTable();
         				$tbUniteMg.fnSettings()._iDisplayStart = 0;
         				$tbUniteMg.fnDraw();
         				var $custSelTable=$("#custSelTable").dataTable();//客户选取列表
         				$custSelTable.fnSettings()._iDisplayStart = 0;
         				$custSelTable.fnDraw();
         				
         				utils.button.reset($this);
         			});
         		}else{
         			utils.alert.warn("<strong>"+ r_data.msg +"</strong>",function(){
         				utils.button.reset($this);
         			});
         		}
    		});
        },
        fnChangeAllCust:function(e){//选择客户列表全选按钮
        	var $this=$(e.currentTarget);
        	var $custs=$this.parents("table").find("tbody ").find("tr").find("td :input:first");
        	var val=$this.is(":checked");
        	$.each($custs,function(i,v){
        		$(v).prop("checked",val);
        	});
        },
        fnShowCustDetail:function(e){//查看客户信息
        	//console.log(e);
        	var viewSelf=this;
        	var btnSelf=$(e.currentTarget);
        	var partyId=btnSelf.data('party-id');
        	var params=[];
        	params.push("partyId="+partyId);
        	window.location.href =$$ctx + "singleCustomer/toDetail?"+params.join('&');
        }
    });
    module.exports = view;
});