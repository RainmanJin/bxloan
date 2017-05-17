define(function(require, exports, module) {
	var model = require("./model");
	var rmgk = require("./rm_gk");
	var utils = require("utils");
	var view = Backbone.View.extend({
		el: "body",
		events: {
			"click #show-tree": "ToggleTree",
			//"click #btn-accept": "acceptUserForm"
		},
		initialize: function() { /** 初始化 */
			this.model = new model();
			this.render();
		},
		render: function() { /** 页面渲染 */
			this.initPersonForm();
			this.initTree();
			this.initDatatables();
		},
		ToggleTree: function() {
	            /**显示隐藏行业的树*/
	        $("#controlZTree").toggle(300, function() {
	            if (($("#controlZTree").attr("style")) == "") {
	            } else if (($("#controlZTree").attr("style")) == "display: none;") {}
	        });
	    },
	    initTree: function() {
            /**初始化树*/
            var viewSelf = this;
            $.fn.zTree.init($("#treeDemo"), {
                async: {
                    enable: true,
                    url: $$ctx + "personMng/getAllDept"
                },
                data: {
                    simpleData: {
                        enable: true,
                        idKey: "id",
                        pIdKey: "parentdepartmentid"
                    },
                    key: {
                        name: "name"
                    }
                },
                check: {
                    enable: true,
                    chkStyle: "radio",
                    radioType: "all"
                },
                callback: {
                    beforeCheck: function(treeId, treeNode) {
                        return ! treeNode.isParent;
                    },
                    onAsyncSuccess: function(event, treeId, treeNode, msg) {
                        var treeObj = $.fn.zTree.getZTreeObj(treeId);
                        var id = $("#orgIdField").val();
                        if (id != "") {
                            var node = treeObj.getNodeByParam("id", id, null);
                          /*  treeObj.checkNode(node, true, true);
                            var parentNode = node.getParentNode();
                            treeObj.expandNode(parentNode, true, false);
                          */
                            $("#deptNameMask").val(node.name);
                            $("#deptName").val(node.id);
                        }
                    }
                }
            });
        },
		initPersonForm: function() {/**初始化基本信息表单*/
			var viewSelf = this;
			var personId = $("#curUserId").val();
			if(personId!=null&&personId!=""){
				viewSelf.model.getPersonData(personId,function(data){
					if(data!=null){
						 $("#deptNameField").val(data["deptName"]);
						 $("#form-userinfo").find("span[name='usernum']").html(data["usernum"]);
						 $.each($("#form-userinfo").find("input, select, textarea"),
			              function() {
			                $(this).val(data[$(this).attr("name")]);
			                $(this).attr("readonly","readonly");
			             });
						 $("#lastlogtime")[0].innerHTML = data["lastlogtime"];
					}else{
						bootbox.alert("<strong>查询用户信息失败！</strong>");
					}
				});
			}else{
				bootbox.alert("<strong>未能获取用户主键！</strong>");
			}
		},
		initUserForm: function() {/**初始化用户表单*/
            $("#form-userinfo").validate({
                rules: rmgk.rules,
                messages: rmgk.messages,
                errorPlacement: function(error, element) {
                    if (element.is(":radio")) error.appendTo(element.parent().next().next());
                    else if (element.is(":checkbox")) error.appendTo(element.next());
                    else if (element.is("input[name='deptNameMask']")) {
                    	error.appendTo(element.parent().parent());
                    }
                    else error.appendTo(element.parent());
                }
            });
		},
		acceptUserForm: function() {/**确认提交用户信息*/
			var viewSelf = this;
			if ($("#form-userinfo").valid()) {
                $.ajax({
                    cache: false,
                    type: "POST",
                    url: $$ctx + "personMng/updatePerson?curUserId=" + $("#curUserId").val(),
                    data: $('#form-userinfo').serialize(),
                    async: false,
                    error: function(request) {
                        alert("保存信息报错");
                    },
                    success: function(data) {
                        if (data == "success") {
                        	utils.alert.suc("<strong>保存成功！</strong>");
                            viewSelf.initPersonForm();
                        } else if (data == "recordIsNotNewError") {
                        	utils.alert.warn("<strong>不能保存数据，因为您看到的记录已不是最新，请刷新！</strong>");
                        } else if (data == "certificateNumHasRegistedError") {
                        	utils.alert.warn("<strong>证件号码已被占用，请重新输入！</strong>");
                        } else {
                        	utils.alert.err("<strong>因未知错误，保存失败！</strong>");
                        }
                    }
                }); //ajax end
            }
		},
		initDatatables: function(){/**初始化角色的表格*/
			 var viewSelf = this;
			 utils.dd.initDataDict(["UserStatusCode"], function(dataDict) {
	            var oTable = $("#tbRoles").dataTable({
	                "sAjaxSource": $$ctx + "personMng/findRolesBySearch",
	                "bFilter": false,
	                "bAutoWidth": true,
	                "bLengthChange": false,
	                "aoColumns": [null,null,null,null,null,null,null,null],
	                "aoColumnDefs" : [
	                                  { "bVisible": false, "aTargets": [0] },
	                                  { "bVisible": false, "aTargets": [4] },
	                                  {
	                                      "aTargets" :[5],
	                                      "mRender" : function(data, type, full){
	                                      	return dataDict.UserStatusCode[data];
	                                      }
	                                  },
	                                  { 
	                                	  "aTargets": [6],
	                                	  "mRender" : function(data, type, full){
	                                		 if((data+"").length>12){
	                                			 return data.substring(0,11)+"...";
	                                		 }else{
	                                			 return data;
	                                		 }
	                                	  }
	                                  },
	                                  { "bVisible": false, "aTargets": [7],
	                                	  "mRender" : function(data, type, full){
	                                		  var hiddens = "<input type='hidden' name='roleid' />"
	                                		return hiddens;
	                                	  }
	                                  }
	                              ],
	                "fnServerParams": function(aoData) {
	                    aoData.push({
	                        "name": "personId",
	                        "value": $('#curUserId').val()
	                    });
	                }
	            });
	            viewSelf.oTable = oTable;
			 });
		}
		
	});
	module.exports = view;
});