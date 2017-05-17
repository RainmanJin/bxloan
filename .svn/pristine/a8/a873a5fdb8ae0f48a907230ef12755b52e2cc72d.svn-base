/**
 * 客户管理-联保体客户
 * 
 * author: lijing
 * lastModified: lijing 2014-08-05 16:30:24
 */


define(function(require, exports, module) {
    var model = require("./model");
    var rm = require("./rm");
    var utils = require("utils");
    var controllerUrl = $$ctx + 'unitecustomer/';
    var partyId = '';
    var view = Backbone.View.extend({
        el: "body",
        events: {
        	"click #btn-add" : "addModalShow"
        },
        initialize: function() {
            /** 初始化 */
            this.model = new model();
            this.render();
            this.$modalDiv=$("#modalDiv");
        },
        render: function() {
            /** 页面渲染 */
        	this.initDataTables();
        	this.initSearchBtn();
        	this.deleteBtnLive();
        	this.editBtnLive();
        	this.detailBtnLive();
        	this.initAddForm();
        },
        initDataTables: function() {
            /** 初始化DataTables */
            var viewSelf = this;
            var status = null;
            utils.dd.initDataDict(["UnType", "UnStatus","CdsGuarantMode"], function(dataDict) {
            var oTable = $("#tbUniteCustomer").dataTable({
                "sAjaxSource": controllerUrl + "findBySearch",
                "bFilter": false,
                "bLengthChange": false,
                "aoColumns": [
                {
                    "bVisible": false,
                    "mData": "id"
                },
                {
                    "mData": "unGuId"
                },
                {
                    "mData": "unGroupName"
                },
                {
                    "mData": "unType",
                    mRender: function(data, type, rowdata) {
                    	if(data){
                    	  return dataDict.UnType[data];
                    	}else{
                    	  return "-";
                    	}
                    }
                    	
                },
                {
                    "mData": "begDate",
                    mRender: function(data, type, rowdata) {
                    	if(data){
                    		return (new Date(data)).toLocaleDateString();
                    	}return "";
                    }
                },
                {
                    "mData": "endDate",
                    mRender: function(data, type, rowdata) {
                    	if(data){
                    		return (new Date(data)).toLocaleDateString();
                    	}return "";
                    }
                },
                {
                    "mData": "custManager"
                },
                {
                    "mData": "customerQuantity"
                },
                {
                    "mData": "guaranWay",
                    mRender: function(data, type, rowdata) {
                    	if(data){
                    		return dataDict.CdsGuarantMode[data];
                    	}else{
                    	  return "-";
                    	}
                    }
                },
                {
                    "mData": "unStatus",
                    mRender: function(data, type, rowdata) {
                    	if(data){
                    	  return dataDict.UnStatus[data];
                    	}
                    }
                    
                },
                {  //操作 
                    "mData": "customerQuantity",
                    mRender: function(data, type, rowdata) {
                    	var html=[];
                    	html.push("<button type='button' role='detail' data-id='" + rowdata.id + "' class='btn btn-xs btn-yellow' title='查看' ><i class='ace-icon fa fa-eye'></i></button> ");
                    	if(rowdata.editFlag){
                    		html.push("<button type='button' role='edit' data-id='"+ rowdata.id + "' data-sys-cd='"+ rowdata.sysCd + "' class='btn btn-xs btn-info'><i class='ace-icon fa fa-edit' title='修改'></i></button> ");
                    	}
                    	if(rowdata.editFlag){
                    		html.push("<button type='button' role='delete' data-id='" + rowdata.id + "' data-sys-cd='"+ rowdata.sysCd + "' class='btn btn-xs btn-danger'><i class='ace-icon fa fa-trash-o' title='删除'></i></button>");
                    	}
                    	return html.join('');
                    }
                },
                {	
                	"bVisible": false,
                    "mData": "unTrustAllAmt"
                }],
                "fnServerParams": function(aoData) {
                    	if(!this.serverParamCache){
                    		this.refreshParamCache = function(){
                    			var $form = $("#searchForm");
                            	this.serverParamCache = [{
                                    "name": "unGuId",
                                    "value": $form.find("input[name='unGuId']").val()
                                },
                                {
                                    "name": "unGroupName",
                                    "value": $form.find("input[name='unGroupName']").val()
                                },
                                {
                                    "name": "customerNum",
                                    "value": $form.find("input[name='customerNum']").val()
                                },
                                {
                                    "name": "customerName",
                                    "value": $form.find("input[name='customerName']").val()
                                },
                                {
                                    "name": "certificateTypeCd",
                                    "value": $form.find("select[name='certificateTypeCd']").val()
                                },
                                {
                                    "name": "certificateNum",
                                    "value": $form.find("input[name='certificateNum']").val()
                                },
                                {
                                    "name": "unStatus",
                                    "value": $form.find("select[name='unStatus']").val()
                                },
                                {
                                    "name": "custManager",
                                    "value": $form.find("input[name='custManager']").val()
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
        initAddForm: function() {
        	var viewSelf=this;
        	var $btn_submit=$('#add-simple-submit');
        	 $("#simpleForm").validate({
                 rules: rm.rules,
                 messages: rm.messages,
                 submitHandler: function(form) {
                	 utils.button.ban($btn_submit);//禁用按钮
                	 viewSelf.model.preAddUniteGuNego($(form).serialize(),function(r){
                		 viewSelf.$modalDiv.modal("hide");//隐藏联保体formModal框
                		 if (r.success) {
                			 utils.alert.suc("<strong>"+ r.msg +"</strong>",function(){
                				 utils.datatable.fresh("#tbUniteCustomer");//table
                				 window.location.href = controllerUrl+"findDetail?action=add&id="+r.data;
                			 });
                         }else{
                        	 utils.alert.warn("<strong>"+ r.msg +"</strong>"); 
                         }
                		 utils.button.reset($btn_submit);//启用按钮
                	 });
                     /*$.ajax({
                         cache: false,
                         type: "POST",
                         url: controllerUrl + "saveSimple",
                         data: $('#simpleForm').serialize(),
                         async: false,
                         error: function(request) {
                        	 utils.alert.err("错误"+request.status);
                         },
                         success: function(r) {
                             if (r.success) {
                            	 window.location.href = controllerUrl + r.data;
                             } 
                             else{
                            	 utils.alert.warn("<strong>"+ r.msg +"</strong>"); 
                             }

                         }
                     });*/
                     return false;
                 },
                 errorPlacement: function(error, element) {
                     if (element.is(":radio")) error.appendTo(element.parent().next().next());
                     else if (element.is(":checkbox")) error.appendTo(element.next());
                    
                     else error.appendTo(element.parent());
                 }
             });
        },
        initSearchBtn: function() {
        	/**查询和重置*/
        	var viewSelf = this;
            $(document).on("click","#btn-query", function(e){
            	var datatable = $("#tbUniteCustomer").dataTable();
            	datatable.fnSettings()._iDisplayStart = 0;
            	viewSelf.oTable.refreshParamCache();
            	datatable.fnDraw();
            });
            $(document).on("click","#btn-reset", function(e){
            	e.stopPropagation();
            	$("#searchForm").resetForm();
            });
        },
        addModalShow: function() {
        	/**新增联保体，显示弹窗*/
        	var viewSelf = this;
        	$("#simpleForm").resetForm();
            $("#modalDiv div.modal-header h4").html("<i class='ace-icon fa fa-plus'></i> 新增联保体");
            viewSelf.model.calculateGuNum(function(r){
            	if(r){
            		$("#simpleForm").find(":input[name='unGuId']").val(r).prop("readonly", true);
            		$("#modalDiv").modal("show");
            	}else{
            		utils.alert.warn("数据错误，请再次尝试");
            	}
            });
            return false;
        },
        editBtnLive: function() {
        	/**修改按钮事件*/
        	 var viewSelf = this;
             $(document).on("click", "button[role='edit']",
             function(e) {
            	 var $this = $(this);
            	 var ugnId=$this.data("id");
            	 var sysCd=$this.data("sys-cd")+'';
            	 if($.inArray(sysCd,['2','3'])<0){
            		 utils.alert.warn("<strong>非微贷联保体客户不可修改！</strong>");
            		 return false;
            	 }
            	 viewSelf.model.checkAllBizStatus(ugnId,function(r){
            		 if(!r.success){
            			 utils.alert.warn("<strong>"+r.msg+"</strong>");
            			 return false;
            		 }
            		 window.location.href = controllerUrl + "findDetail?action=edit&id=" + ugnId;
            	 });
             });
        },
        detailBtnLive: function() {
        	/**查看按钮事件*/
        	 var viewSelf = this;
             $(document).on("click", "button[role='detail']",
             function(e) {
            	 var $this = $(this);
            	 window.location.href = controllerUrl + "findDetail?action=view&id=" + $this.data("id");
             });
        },
        deleteBtnLive: function() {
        	/**删除按钮事件*/
        	 var viewSelf = this;
        	 var sysCds=['2','3'];//微贷、小贷
             $(document).on("click", "button[role='delete']",
             function(e) { // 动态绑定所有删除按钮的click事件
                 var $this = $(this);
                 var sysCd=$this.data("sys-cd")+'';
            	 if($.inArray(sysCd,sysCds)<0){
            		 utils.alert.warn("<strong>非微贷联保体客户不可删除！</strong>");
            		 return false;
            	 }
            	 utils.button.confirm(null,function(result){
            		 if (result) {
                         var id = $this.data("id");
                         viewSelf.model.delUniteCustomer(id,
                         function(r) {
                             if (r.success) {
                                 utils.datatable.fresh("#tbUniteCustomer");
                                 utils.alert.suc("删除成功！");
                             } else {
                             	utils.alert.err("<strong>"+ r.msg +"</strong>");
                             }
                         });
                     }
            	 });
             });
        }

    });
    module.exports = view;
});