/** 电话审查情况明细 */
define(function(require,exports,module){
	var model = require('../model');
	var utils = require('utils');
	var rm = require('./rm_phone_detail');
	var view = Backbone.View.extend({
		el: "body",
		events: {
			"click #btn-phone": "add",
			"click button[role='delete_phone']":"clickDelApprovalPhoneDetail",
			"click button[role='edit_phone']":"clickEditApprovalPhoneDetail"
		},
		initialize: function(){/** 初始化 */
			this.model = new model();
			this.render();
			
		},
		render: function(){/** 页面渲染 */
			this.initForm();
			this.initPhoneTable();
		},
		initForm:function(){
			var viewSelf=this;
			var $form = $("#phoneForm");
			//时间控件
			$form.find(":input.input-datepicker").datepicker({
				format : 'yyyy-mm-dd',
				todayHighlight : true,
				autoclose : true,
				clearBtn : true
			}).on("show", function() {
				$(".datepicker").css("z-index", "99999");
			});
			
			var wife_name = null;
			//核实人姓名
			viewSelf.model.findWifeNameByPartyId({
				"partyId" : $("#partyId").val()
			}, function(r) {
				if (r && r.success) {
					for ( var key in r.data) {
						wife_name = r.data[key];//配偶姓名
					}
				}
			});
			var customer_name = $("#inputError").val();//借款人姓名
			var _input = $form.find(':input[name="checkName"]').val(customer_name).prop("readonly", true);
			$form.find('select[name="checkType"]').change(
					function() {
						var selectVal = $(this).val();
						if (selectVal == 1) {
							_input.val(customer_name).prop("readonly", true);
						} else if (selectVal == 2) {
							_input.val(wife_name).prop("readonly", true);
						} else {
							_input.val("").prop("readonly", false);
						}
					});
			//校验规则
			$form.validate({
				rules: rm.rules,
				//messages: rm.messages,
				submitHandler: function(form) {
					viewSelf.saveApprovalPhoneDetail();
					return false; 
				}
			});
			var form=$form.get(0);
			if(form){
				form.reset();
			}
			//console.log($form.get(0));
			$form.find("select,input[name='checkName'],input[name='time']").val("");
			//$form.get(0).reset();
		},
		initPhoneTable: function(){
			var viewSelf = this;
            utils.dd.initDataDict(["CheckerType","CommonWhether"],
            function(dataDict){
    			var oTable = $("#phone_tbl").dataTable({
    				"sAjaxSource": $$ctx + "wdapproval/findApprovalPhoneDetailBySearch",
                    "bFilter": false,
                    "bPaginate": false,
                    "bAutoWidth": true,
                    "bLengthChange": false,
                    "aoColumns": [
                    {"mData": "checkType",
                      mRender: function(data, type, rowdata) {
                    	  if (data) {
                    		  return dataDict.CheckerType[data];
                          } else {
                              return "-";
                          }
                     }},
                    {"mData": "checkName"},
                    {"mData": "isPhone",
                      mRender: function(data, type, rowdata) {
                    	  if (data) {
                    		  return dataDict.CommonWhether[data];
                          } else {
                              return "-";
                          }
                     }},
                     {"mData": "isIncomeSource",
                         mRender: function(data, type, rowdata) {
                       	  if (data) {
                       		  return dataDict.CommonWhether[data];
                             } else {
                                 return "-";
                             }
                     }},
                     {"mData": "isApplyMoney",
                         mRender: function(data, type, rowdata) {
                       	  if (data) {
                       		  return dataDict.CommonWhether[data];
                             } else {
                                 return "-";
                             }
                      }},
                      {"mData": "isApplyTerm",
                    	  mRender: function(data, type, rowdata) {
                    		  if (data) {
                    			  return dataDict.CommonWhether[data];
                    		  } else {
                    			  return "-";
                    		  }
                      }},
                      {"mData": "isRate",
                    	  mRender: function(data, type, rowdata) {
                    		  if (data) {
                    			  return dataDict.CommonWhether[data];
                    		  } else {
                    			  return "-";
                    		  }
                      }},  
                      {"mData": "isBlameKnow",
                    	  mRender: function(data, type, rowdata) {
                    		  if (data) {
                    			  return dataDict.CommonWhether[data];
                    		  } else {
                    			  return "-";
                    		  }
                      }}, 
                      {"mData": "isBlameClear",
                    	  mRender: function(data, type, rowdata) {
                    		  if (data) {
                    			  return dataDict.CommonWhether[data];
                    		  } else {
                    			  return "-";
                    		  }
                      }},                       
                    {"mData": "callTime"},
                    {"mData": "id", mRender: function(data, type, rowdata) {	
                       var buttons = "<button type='button' role='edit_phone' data-id='" + rowdata.id + "' class='btn btn-xs btn-info' title='修改'><i class='ace-icon fa fa-edit' ></i></button> " 
                            	   + "<button type='button' role='delete_phone' data-id='" + rowdata.id + "' class='btn btn-xs btn-danger' title='删除'><i class='ace-icon fa fa-trash-o'></i></button>";
                        return buttons;
                    	}
                    }],
    				"fnServerParams": function(aoData) {
    					  aoData.push({
    						  "name": "projectId",
    						  "value": $("#projectId").val()
    					  });
    				}
    			});	
    			viewSelf.oTable = oTable;
			});
		},
		add: function(){
			var viewSelf = this;
			viewSelf.initForm();
			$("#modal-phoneFormInfo div.modal-header h4").html("<i class='ace-icon fa fa-plus'></i>电话审查情况明细");
			$("#modal-phoneFormInfo").modal("show");
		},
		saveApprovalPhoneDetail: function(){
			var viewSelf = this;
			var param = $('#phoneForm').serialize();
			utils.button.ban("#submit-phone");//禁用按钮
			viewSelf.model.saveApprovalPhoneDetail( param,function(data) {
				if(data){
					if(data.success){
						$("#phone_tbl").dataTable().fnDraw();
						$("#modal-phoneFormInfo").modal("hide");
					}
					else{
						utils.alert.warn("<strong>"+ data.msg +"</strong>");
					}
				}
				utils.button.reset("#submit-phone");//启用按钮
			});
		},
        clickDelApprovalPhoneDetail: function(e){
        	var viewSelf = this;
        	var btnSelf=$(e.currentTarget);
        	//console.log(btnSelf.data("id"));
        	utils.button.confirm(btnSelf,function(result){
				if(result){
					viewSelf.model.delApprovalPhoneDetail(btnSelf.data("id"),function(r_data){
						if(r_data.success){
							$("#phone_tbl").dataTable().fnDraw();
						}else{
							utils.alert.err("<b>" + r_data.msg + "</b>");
						}
					});
				}
			});
        },
        clickEditApprovalPhoneDetail:function(e){
			var viewSelf=this;
			var btnSelf=$(e.currentTarget);
			var $form = $("#modal-phoneFormInfo");
			//$form.get(0).reset();
			viewSelf.model.editApprovalPhoneDetail(btnSelf.data("id"),function(r_data){
				$.each($form.find("input, select"), function() {
					$(this).val(r_data[$(this).attr("name")]);
				});
				$form.find("input[name='time']").val(r_data.callTime);//转换时间
				$form.find("input[name='checkName']").prop("readonly",true);
				$("#modal-phoneFormInfo div.modal-header h4").html("<i class='ace-icon fa fa-plus'></i>电话审查情况明细");
				$form.modal("show");
			});
		}
	});
	module.exports = view;
});
