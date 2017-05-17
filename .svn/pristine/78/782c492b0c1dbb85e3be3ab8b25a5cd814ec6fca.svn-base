
define(function (require, exports, module)
{
    var model = require("./model");
    var utils = require("utils");
    var _alert = bootbox.alert;
    var controllerUrl = "cusmanager";
    var rm = require("./rm");
      
    var view = Backbone.View.extend({
            el: "body",
            events:{},
            initialize: function (){
                this.model = new model();
                this.render();
            },
            render: function () {
            	this.initCusManagerTable();
            	this.initClickFunction();
            	this.initUserTable();
            	this.initForm();
            	this.initPrivilege();
            },
            initCusManagerTable : function(){
            	var viewSelf = this;
            	var partyId = $("#cusmanager_partyId_corp").val()||$("#cusmanager_partyId_person").val();
    			var cusManagerTable = $("#tbl_cusmanager").dataTable({
    				bServerSide: true,
    				bFilter: false,
    				bLengthChange: false,
    		    	sAjaxSource: $$ctx + controllerUrl + "/list",
    		    	fnServerParams: function (aoData) {
  				      aoData.push({"name": "partyId", "value":partyId});
    		    	},
    		    	aoColumns: [
    		    	    {mData: "id", mRender : function(data, type, rowdata) {
    		    	    	return "<input type='checkbox' value='" + rowdata.id + "' name='cusmanager_ck_id' />";
    		    	    }},
    		    	    {mData: "name"},//客户经理名称
    		    	    {mData: "managerTypeName"},//管理类型
    		    	    {mData: "orgName"},//角色所在机构
    		    	    {mData: "id", mRender: function(data, type, rowdata) {
    		    	    	var operation = 
							"<div class='btn-group'>" + 
								"<button data-id='" + rowdata.id + "' class='btn btn-xs btn-info' role='editMngPrivilege' data-toggle='tooltip' data-placement='bottom' title='编辑'>" +
									"<i class='ace-icon fa fa-edit'></i></button>" +
    							"<button data-id='" + rowdata.id + "' class='btn btn-xs btn-yellow' role='detailMngPrivilege' data-toggle='tooltip' data-placement='bottom' title='查看'>" +
    								"<i class='ace-icon fa fa-eye'></i></button>" +
    							"<button data-id='" + rowdata.id + "' class='btn btn-xs btn-danger' role='removeMngPrivilege' data-toggle='tooltip' data-placement='bottom' title='删除'>" +
    								"<i class='ace-icon fa fa-trash-o' title='删除'></i>" +
    						"</div>";
    		    	    	return operation;
    		    		}}
    		    	],
    		        fnInitComplete: function(oSettings, json) {
    		        	viewSelf.initPrivilege();
    		        }
    			});
    			viewSelf.cusManagerTable = cusManagerTable;
    			
    			$(document).on("click","#cusmanager_ck_all",function(e){
					$("input[name='cusmanager_ck_id']").each(function(){
						$(this)[0].checked = $("#cusmanager_ck_all")[0].checked;
					});
    			});
            },
            initForm: function(operate) {
    			$("#addCustomerNanagerTeam").validate({
    				rules: rm.rules,
    				messages: rm.messages
    			});
    		},
            initClickFunction:function() {
            	var viewSelf = this;
            	/** 客户移交*/
            	var uc_aData = null;
            	var cmTeamId = "";
    			$(document).on("click", "button[role='handOverMngPrivilege']", function(e) {
    				var cmtIds = "";
    				$("input[name='cusmanager_ck_id']:checked").each(function() {
    					cmtIds += $(this).val() +',';
    				});
    				if(cmtIds === null || cmtIds === '') {
    					utils.alert.warn( "请选择要移交的用户");
    					return "";
    				}
    				if(cmtIds.lastIndexOf(",", cmtIds.length)){
    					cmtIds = cmtIds.substring(0, cmtIds.length-1);
    				}
    				//移交校验
    				viewSelf.model.handOverMngPrivilege({cmtIds: cmtIds}, function(result){
    					if(result.success){
    						//显示选人
    						cmTeamId = cmtIds;
    						$("#choice-user div.modal-header h4").html("<i class='ace-icon fa fa-plus'></i> 选择移交经理");
    	    	            $("#choice-user").modal("show");
    					} else {
    						utils.alert.warn(result.msg);
    					}
    				});
    			});
    			$(document).on("click", "button[role='sureHandOver']", function(e) {
    				if(uc_aData === null){
    					utils.alert.warn( "请选择要移交的目标用户");
    					return "";
    				}
    				if(cmTeamId === null){
    					utils.alert.warn("没有要移交的源用户");
    					return "";
    				}
    				bootbox.confirm("确定移交吗?交换成功后刷新当前页", function(confirm) {
    					if(confirm) {
    						var person = {cmTeamIds: cmTeamId, userNum: uc_aData[0],logname: uc_aData[1],deptCd: uc_aData[7],userName: uc_aData[2]};
    						viewSelf.model.sureHandOver(person, function(result){
    	    					if(result.success){
    	    						viewSelf.refreshTables();
    	    						bootbox.confirm("移交成功!", function(confirm) {
    	    							uc_aData = null;
    	    							$("#choice-user").modal("hide");
    	    							//移交的权限是否是管护权
    	    							viewSelf.model.isManageCustomerPrivilege({customerNum: $("#cm_customerNum").val(), partyId: $("#cm_partyId").val()}, function(result){
    	    		    					if(result.success){
    	    		    						if (result.data) {//没有管户权
    	    		    							if (!$("#cusmanager_partyId_person").val()) {
    	    		    								window.location.href = $$ctx + "corpcustomer/showDetail/" + $("#cusmanager_partyId_corp").val() + "?consultLocation=";
        	    	    							} else if (!$("#cusmanager_partyId_corp").val()) {
        	    	    								window.location.href = $$ctx + "singleCustomer/backToForm?customerId=" + $("#cusmanager_partyId_person").val() + "&workCode=TODETAIL&consultLocation=customer";
        	    	    							} else {
        	    	    								viewSelf.refreshTables();
        	    	    								viewSelf.chanageButtons(result.data);
        	    	    							}
    	    		    						}
    	    		    					} else {
    	    		    						utils.alert.err( "权限读取失败.默认为查看权限");
    	    		    						viewSelf.chanageButtons(true);
    	    		    					}
    	    		    				});
        		    				});
    	    						
    	    						
    	    					} else {
    	    						utils.alert.err("移交失败.请稍后再试");
    	    					}
    	    				});
    					}
    				});
    			});
    			/** 客户移交*/
    			/**新增*/
    			$(document).on("click", "button[role='add_cusmanager']", function(e) {
    				viewSelf.chanageButtons("show");
    				$("#addCustomerNanagerTeam").resetForm();
    				$('input:radio[name=choice-user-radio-id]:checked').attr('checked',false);
    	            $("#add_CustomerManagerTeam div.modal-header h4").html("<i class='ace-icon fa fa-plus'></i> 新增管户权");
    	            $("#add_CustomerManagerTeam").modal("show");
    	            //modify by wangyawei 20150429 start 将管理类型单选按钮修改为下拉列表
    	            $("#managerType").attr("disabled", false);
    	            //管理类型默认为"业务权客户经理","查询权客户经理"
    	            $("#managerType").empty();
    	            $("#managerType").append("<option value=''>请选择</option>"); 
    	            utils.dd.initDataDict(["ManagementType"], function(dataDict) {
    	            	$.each(dataDict.ManagementType, function(index, content) {
    	            		if(utils.str.notContains(index, '01')){
    	            			$("#managerType").append("<option value='"+ index +"'>"+ content+ "</option>"); 
    	            		}
                        });
    	            }) ;
    	            //modify by wangyawei 20150429 end
    			});
    			$(document).on("click", "#btn-select-user", function(e) {
    	            $("#userModal").toggle(300);
    			});
    			$(document).on("click", "#saveCustomerManagerTeam", function(e) {
    				//校验并提交form
    				if($("#addCustomerNanagerTeam").valid()) {
    					$("#saveCustomerManagerTeam").button("loading");
    					$("#addCustomerNanagerTeam").attr("action",$$ctx + controllerUrl + "/save");
    					viewSelf.model.submitForm($("#addCustomerNanagerTeam"), function(r) {
    						$("#saveCustomerManagerTeam").button("reset");
    						if (r.success) {
    							viewSelf.refreshTables();
    							$("#add_CustomerManagerTeam").modal("hide");
    							/*bootbox.alert("保存成功！",function() {
    							});*/    						
    							} else utils.alert.warn(r.msg);
    					});
    				}
    			});
    			/*绑定客户列表行单击事件*/
    			$(document).on("click", "#ueTable tbody tr", function(e) {
    				var ue_aData = viewSelf.ueTable.fnGetData(this);
    				//校验
    				if(ue_aData[7] === null || ue_aData[7] === '' || ue_aData[9] === null || ue_aData[9] === '') {
    					utils.alert.warn("请录入角色信息！");
    					return "";
    				}
    				$(this).find(":radio[name='user-radio-id']")[0].checked = true;
    				//指定赋值
    				$("#orgCd").val(ue_aData[6]);//机构编号
    				$("#deptCd").val(ue_aData[7]);//部门编号
    				$("#userNum").val(ue_aData[0]);//用户ID
    				$("#roleCd").val(ue_aData[9]);//角色编号
    				
    				$("#orgName").val(ue_aData[2]);//机构名称
    				$("#depName").val(ue_aData[3]);//部门名称
    				$("#userName").val(ue_aData[5]);//用户名称
    				$("#userLogonName").val(ue_aData[1]);//用户登录名
    			});
    			$(document).on("click", "#ucTable tbody tr", function(e) {
    				$(this).find(":radio[name='choice-user-radio-id']")[0].checked = true;
    				uc_aData = viewSelf.ucTable.fnGetData(this);
    			});
    			/**新增结束*/
    			/**编辑开始 */
				$(document).on("click", "#tbl_cusmanager button[role='editMngPrivilege']", function(e) {
					var btn = $(this);
					//modify by wangyawei 20150429 start 将管理类型单选按钮修改为下拉列表
					/*$('input:radio[name=choice-user-radio-id]').each(function(){
						if ($(this)[0].checked == true) {
							$(this)[0].checked = false ;
						}
					});*/
    	            $("#managerType").attr("disabled", false);
    	            //管理类型默认为"业务权客户经理","查询权客户经理"
    	            $("#managerType").empty();
    	            $("#managerType").append("<option value=''>请选择</option>"); 
    	            utils.dd.initDataDict(["ManagementType"], function(dataDict) {
    	            	$.each(dataDict.ManagementType, function(index, content) {
    	            		if(utils.str.notContains(index, '01')){
    	            			$("#managerType").append("<option value='"+ index +"'>"+ content+ "</option>"); 
    	            		}
                        });
    	            }) ;
					//读取数据
					viewSelf.model.getCustomerManagerTeamById({cmtId: btn.data("id")}, function(result){
    					if(result.success){
    						var obj = result.data;
    						if("01" == obj.managerType) {
    							utils.alert.warn("管护权客户经理不可修改!");
    							return "";
    						}
    						$.each($("#addCustomerNanagerTeam").find("input[type='text'], select, textarea"), function() {
    							$(this).val(obj[$(this).attr("name")]);
    						});
    						/*$('input[name="managerType"]').each(function(index,item) {
    							if($(this).val() == obj.managerType) {
    								$(this)[0].checked = true ;
    							}else {
    								$(this)[0].checked = false ;
    							}
    						});*/
    						//初始化按钮
    						viewSelf.chanageButtons('show');
    						//show window
    	    	            $("#add_CustomerManagerTeam div.modal-header h4").html("<i class='ace-icon fa fa-plus'></i> 编辑管户权");
    	    	            $("#add_CustomerManagerTeam").modal("show");
    					} else {
    						utils.alert.err("查询失败.请稍后再试");
    					}
    				});
					//modify by wangyawei 20150429 end
    			});
				/**编辑结束 */
				/**删除开始 */
				$(document).on("click", "#tbl_cusmanager button[role='removeMngPrivilege']", function(e) {
					var btn = $(this);
    				bootbox.confirm("确定删除吗?", function(_confirm) {
    					if(_confirm) {
    						viewSelf.model.removeMngPrivilege({cmtId: btn.data("id")}, function(result){
    	    					if(result.success){
    	    						viewSelf.refreshTables();
    	    						/*bootbox.alert("删除成功!",function() {
    	    						});*/
    	    					} else {
    	    						utils.alert.warn(result.msg);
    	    					}
    	    				});
    					}
    				});
    			});
				/**删除结束 */
				/**查看开始 */
				$(document).on("click", "#tbl_cusmanager button[role='detailMngPrivilege']", function(e) {
					var btn = $(this);
					//读取数据
					$("#userModal").hide();
					//modify by wangyawei 20150429 start 将管理类型单选按钮修改为下拉列表
					$("#managerType").empty();
    	            $("#managerType").append("<option value=''>请选择</option>"); 
    	            utils.dd.initDataDict(["ManagementType"], function(dataDict) {
    	            	$.each(dataDict.ManagementType, function(index, content) {
	            			$("#managerType").append("<option value='"+ index +"'>"+ content+ "</option>"); 
                        });
    	            }) ;
					viewSelf.model.getCustomerManagerTeamById({cmtId: btn.data("id")}, function(result){
    					if(result.success){
    						var obj = result.data;
    						/*$.each($("#addCustomerNanagerTeam").find("input").not('input[name=managerType]'), function() {
    							$(this).val(obj[$(this).attr("name")]);
    						});
    						$('input[name="managerType"]').each(function(index,item) {
    							if($(this).val() == obj.managerType) {
    								$(this)[0].checked = true ;
    							}else {
    								$(this)[0].checked = false ;
    							}
    						});*/
    						$.each($("#addCustomerNanagerTeam").find("input[type='text'], select, textarea"),function() {
								$(this).val(obj[$(this).attr("name")]);
                                $(this).attr("disabled", "disabled");
                            });
    					} else {
    						utils.alert.err("查询失败.请稍后再试");
    					}
    				});
					//modify by wangyawei 20150429 end
    				//初始化按钮
					viewSelf.chanageButtons('hide');
					//show window
    	            $("#add_CustomerManagerTeam div.modal-header h4").html("<i class='ace-icon fa fa-plus'></i> 查看详细");
    	            $("#add_CustomerManagerTeam").modal("show");
    			});
				/**查看结束 */
				/** 角色互换*/
    			$(document).on("click", "button[role='transfer_role_cusmanager']", function(e) {
    				var count = 0;
    				var pCmtIds = '';
    				$("input[name='cusmanager_ck_id']:checked").each(function() {
    					pCmtIds += $(this).val() +',';
    					count++;
    				});
    				if(count != 2) {
    					utils.alert.warn("请选择两个客户经理！");
    					return "";
    				}
    				if(pCmtIds.lastIndexOf(",", pCmtIds.length)){
    					pCmtIds = pCmtIds.substring(0, pCmtIds.length-1);
    				}
    				bootbox.confirm("确定角色互换吗?交换成功后刷新当前页", function(confirm) {
    					if(confirm) {
    						viewSelf.model.roleTransfer({cmtIds: pCmtIds}, function(result){
    	    					if(result.success){
    	    						viewSelf.refreshTables();
    	    						bootbox.confirm("角色互换成功!", function(confirm) {
    	    							//alert($("#cusmanager_partyId_corp").val());
    	    							//alert($("#cusmanager_partyId_person").val());
    	    							//交换的权限是否是交换了管护权
    	    							viewSelf.model.isManageCustomerPrivilege({customerNum: $("#cm_customerNum").val(), partyId: $("#cm_partyId").val()}, function(result){
    	    		    					if(result.success){
    	    		    						if (result.data) {//没有管户权
    	    		    							if (!$("#cusmanager_partyId_person").val()) {
    	    		    								window.location.href = $$ctx + "corpcustomer/showDetail/" + $("#cusmanager_partyId_corp").val() + "?consultLocation=";
        	    	    							} else if (!$("#cusmanager_partyId_corp").val()) {
        	    	    								window.location.href = $$ctx + "singleCustomer/backToForm?customerId=" + $("#cusmanager_partyId_person").val() + "&workCode=TODETAIL&consultLocation=customer";
        	    	    							} else {
        	    	    								viewSelf.refreshTables();
        	    	    								viewSelf.chanageButtons(result.data);
        	    	    							}
    	    		    						}
    	    		    					} else {
    	    		    						utils.alert.err("权限读取失败.默认为查看权限");
    	    		    						viewSelf.chanageButtons(true);
    	    		    					}
    	    		    				});
    	    							//个人客户查看跳转
    	    							
    	    							
        		    				});
    	    					} else {
    	    						utils.alert.warn(result.msg);
    	    					}
    	    				});
    					}
    				});
    			});
    			$(document).on("click", "#btn-query-user", function(e) {
    				viewSelf.ueTable.fnDraw();
    			});
    			$(document).on("click", "#btn-query-user2", function(e) {
    				viewSelf.ucTable.fnDraw();
    			});
            },
            initUserTable:function(){
    			var viewSelf=this;
    			var ueTable = $("#ueTable").dataTable({
    				sAjaxSource : $$ctx + controllerUrl + "/findUserBySearch",
		    		bFilter: false,
		    		bLengthChange : false,
			    	fnServerParams : function(aoData) {
    					aoData.push({
    						name : "name",
    						value : $('#search_userName').val()
    					});
    				},
			    	aoColumns: [
						{aTargets: [0], mRender: function(data, type, role) {
							var returnVal = 
							'<label>' +
								'<input class="ace" type="radio" name="user-radio-id" value="' + data + '" >' +
								'<span class="lbl"></span>' +
							'</label>';
							return returnVal;
						}},
			    	    {aTargets: [1]},
			    	    {aTargets: [2]},
			    	    {aTargets: [3]},
			    	    {aTargets: [4], mRender: function(data, type, role) {
							var returnVal = "未生效";
							if(data == '1') {
								returnVal = "生效";
							}
							return returnVal;
						}},
			    	    {aTargets: [5], bVisible: false},
			    	    {aTargets: [6], bVisible: false},
			    	    {aTargets: [7], bVisible: false},
			    	    {aTargets: [8], bVisible: false},
			    	    {aTargets: [9], bVisible: false},
			    	    {aTargets: [10], bVisible: false},
			    	    {aTargets: [11], bVisible: false},
			    	    {aTargets: [12], bVisible: false},
			    	    {aTargets: [13], bVisible: false}
			    	]
    			});
    			
    			var ucTable = $("#ucTable").dataTable({
    				sAjaxSource : $$ctx + controllerUrl + "/findUserBySearch",
		    		bFilter: false,
		    		bLengthChange : false,
			    	fnServerParams : function(aoData) {
    					aoData.push({
    						name : "name",
    						value : $('#search_userName2').val()
    					});
    				},
			    	aoColumns: [
						{aTargets: [0], mRender: function(data, type, role) {
							var returnVal = 
							'<label>' +
								'<input class="ace" type="radio" name="choice-user-radio-id" value="' + data + '" >' +
								'<span class="lbl"></span>' +
							'</label>';
							return returnVal;
						}},
			    	    {aTargets: [1]},
			    	    {aTargets: [2]},
			    	    {aTargets: [3]},
			    	    {aTargets: [4], mRender: function(data, type, role) {
							var returnVal = "未生效";
							if(data == '1') {
								returnVal = "生效";
							}
							return returnVal;
						}},
			    	    {aTargets: [5], bVisible: false},
			    	    {aTargets: [6], bVisible: false},
			    	    {aTargets: [7], bVisible: false},
			    	    {aTargets: [8], bVisible: false},
			    	    {aTargets: [9], bVisible: false},
			    	    {aTargets: [10], bVisible: false},
			    	    {aTargets: [11], bVisible: false},
			    	    {aTargets: [12], bVisible: false},
			    	    {aTargets: [13], bVisible: false}
			    	]
    			});
    			viewSelf.ueTable = ueTable;
    			viewSelf.ucTable = ucTable;
    		},
    		initPrivilege: function() {
    			var viewSelf = this;
    			var isView = ($("#workTypeField").val() == "TODETAIL");
    			if(!isView){
    				viewSelf.model.isManageCustomerPrivilege({customerNum: $("#cm_customerNum").val(), partyId: $("#cm_partyId").val()}, function(result){
    					if(result.success){
    						viewSelf.chanageButtons(result.data);
    					} else {
    						utils.alert.err("权限读取失败.默认为查看权限");
    						viewSelf.chanageButtons(true);
    					}
    				});
    			} else {
    				viewSelf.chanageButtons(true);
    			}
    		},
            refreshTables : function() {
            	var viewSelf = this;
            	viewSelf.cusManagerTable.fnDraw();
            },
            chanageButtons: function(stat) {
            	$("#add_CustomerManagerTeam").modal("hide");
            	if('show' == stat) {
            		$("#btn-select-user").attr("disabled",false);
            		$("input[name='managerType']").attr("disabled",false);
            		$("#saveCustomerManagerTeam").show();
            	} else if('hide' == stat) {
            		$("#btn-select-user").attr("disabled",true);
            		$("input[name='managerType']").attr("disabled",true);
            		$("#saveCustomerManagerTeam").hide();
            	} else if(true == stat) {//如果是非管护权经理，只显示查看按钮
					//$('button',$('#tbl_cusmanager')).not('button[role="detailMngPrivilege"]').hide();
					$("button[role='removeMngPrivilege']").hide();
					$("button[role='editMngPrivilege']").hide();
					$("button[role='add_cusmanager']").hide();
					$("button[role='transfer_role_cusmanager']").hide();
					$("button[role='handOverMngPrivilege']").hide();
            	}
            }
        }
    );
    module.exports = view;
}
);
