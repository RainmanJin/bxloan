
define(function(require, exports, module) {
	var model = require("./model");
	var utils = require('utils');
	var rm = require("./rm");
	var view = Backbone.View.extend({
		el : "body",
		events : {
			"click a[role='addFnProductArea']":"fnAddProductArea"
		},

		initialize : function() {
			this.isView=utils.parseBool($("#isView").val());//是否显示
			this.isEdit=utils.parseBool($("#isEdit").val());//是否是编辑
			this.model = new model();
			this.render();
		},
		fnAddProductArea : function(){
			var seq = $("#news3").data("seq");
			if (seq = "addNew") {
				$("#fnProduceAreaForm").find("input[name='id']").val("");
			}
			$("#modal-productAreaInfo div.modal-header h4").html("<i class='ace-icon fa fa-plus'></i> 生产区域信息");
			$("#fnProduceAreaForm").resetForm();
			$("#fnProduceAreaForm").find("input,select").removeAttr("disabled");
			$("#submitbtn").show();
			$('#hid').hide();
			$("#modal-productAreaInfo").modal("show");
		},
		render : function() {
			this.initForm();
			this.initDataTables();
			this.delBtnLive();
			this.editBtnLive();
			this.seeBtnLive();
		},
		initDataTables : function() {/** 初始化DataTables */
			var viewSelf = this;
			utils.dd.initDataDict(["MyRentType"], function(dataDict) {
			var oTable = $("#produceTbl").dataTable({
				//获取数据url
				sAjaxSource:$$ctx + "addFnProduceAreaInfo/findBySearch",
				bFilter  : false,
				bPaginate: false,
				bInfo:false,
				aoColumns : [
				     {mData:"location"},
				     {mData:"area"},
				     {mData:"unit"},
				     {mData:"areaProperty",sWidth:"200px" , mRender : function(data, type, rowdata){
					    	// alert(data);
				    		 return dataDict.MyRentType[data];
				     }},
				     {mData:"rent"},
			         {mData:"id",  mRender : function(data, type, rowdata){//列值，状态（隐藏/显示），行值
			            /*var buttons = "<div class='btn-group'style='width:100px;'>"
			            +"<button type='button' role='edits' title='修改' data-id='" + data + "'  class='btn btn-xs btn-info'><i class='ace-icon fa fa-edit'></i></button> "
			            +"<button type='button' role='deletes' title='删除' data-id='" + data+ "'  class='btn btn-xs btn-danger'><i class='ace-icon fa fa-trash-o'></i></button>"
			            +"<button type='button' role='sees' title='查看' data-id='" + data+ "'  class='btn btn-xs btn-yellow'><i class='ace-icon fa fa-eye'></i></button></div> ";
			            return buttons;	*/
			        	 var html =[];
			        	 html.push("<div class='btn-group'style='width:100px;'>");
			        	 if(viewSelf.isEdit){
			        		 html.push("<button type='button' role='edits' title='修改' data-id='" + data + "'  class='btn btn-xs btn-info'><i class='ace-icon fa fa-edit'></i></button> ");
			        		 html.push("<button type='button' role='deletes' title='删除' data-id='" + data+ "'  class='btn btn-xs btn-danger'><i class='ace-icon fa fa-trash-o'></i></button>");
			        	 }
			        	 html.push("<button type='button' role='sees' title='查看' data-id='" + data+ "'  class='btn btn-xs btn-yellow'><i class='ace-icon fa fa-eye'></i></button> ");
			        	 html.push('</div>');
			        	 return html.join('');
			          }}
				 ], 
				 fnServerParams : function(aoData) {
						aoData.push({
							name : "type",
							value : "2"
						},{
							 name : "projectId",
							 value : $('#projectId').val()
						});
					}
			});
				viewSelf.oTable = oTable;//可以直接调
			});
		},
		delBtnLive: function(){/** 删除按钮的预绑定事件 */
			var viewSelf = this;
			$(document).on("click","button[role='deletes']", function(e){// 动态绑定所有删除按钮的click事件
				var btnSelf = $(this);
				bootbox.confirm({
					message : "您确定要删除此条记录吗？",
					buttons : {
						confirm:{
							label:"<i class='ace-icon fa fa-trash-o bigger-110'></i> 确定",
							className:"btn-danger btn-sm"
						},
						cancel:{
							label: "<i class='ace-icon fa fa-times bigger-110'></i> 取消",
							className: "btn-warning btn-sm"
						}
					},
					callback: function(result){
						if(result){
							viewSelf.model.delFnProduceAreaInfoById(btnSelf.data("id"), function(r){
								if(r.success){
									$("#produceTbl").dataTable().fnDraw(); // 重新加载表格中的数据
								} else {
									bootbox.alert(error + "<strong>该用户数据删除失败！<strong>");
								}
							});
						}
					}
				});
			});
		},
		editBtnLive:function(){/** 修改按钮的预绑定事件 */
			var viewSelf = this;
			$(document).on("click","button[role='edits']",function(e){
				var btnself = $(this);
				viewSelf.model.updateFnProduceAreaInfoById(btnself.data("id"),function(obj){
					
					$.each($("#modal-productAreaInfo").find("input, select, textarea"), function() {
						$(this).val(obj[$(this).attr("name")]);
					});
					$("#fnProduceAreaForm").find("input,select").removeAttr("disabled");
					$("#submitbtn").show();
					$("#fnProduceAreaForm").find("select[name='areaProperty']").bind("change", function(e) {
						if ($(this).val() == '1') {// 其他
							$('#hid').hide();
						}else{
							$('#hid').show();
						}
					});	
					$("#modal-productAreaInfo div.modal-header h4").html("<i class='ace-icon fa fa-edit'></i> 生产区域信息");
					$("#modal-productAreaInfo").modal("show");				
				});
			});
		},
		seeBtnLive:function(){/** 查看按钮的预绑定事件 */
			var viewSelf = this;
			$(document).on("click","button[role='sees']",function(e){
				var btnself = $(this);
				viewSelf.model.updateFnProduceAreaInfoById(btnself.data("id"),function(obj){
					
					$.each($("#modal-productAreaInfo").find("input, select, textarea"), function() {
						$(this).val(obj[$(this).attr("name")]);
					});
					$("#fnProduceAreaForm").find("input,select").attr("disabled","disabled");
					$("#submitbtn").hide();
					$("#fnProduceAreaForm").find("select[name='areaProperty']").bind("change", function(e) {
						if ($(this).val() == '1') {// 其他
							$('#hid').hide();
						}else{
							$('#hid').show();
						}
					});	
					$("#modal-productAreaInfo div.modal-header h4").html("<i class='ace-icon fa fa-eye'></i> 生产区域信息");
					$("#modal-productAreaInfo").modal("show");				
				});
			});

		},
		initForm:function(){//jquery的验证插件
			var viewSelf = this;
			$("#fnProduceAreaForm").validate({
				rules:rm.rules,//自定义规则
				submitHandler: function(form){//参名form是$()取得的表单信息  
					var _button = $("#fnProduceAreaForm").find("button");
					utils.button.ban(_button);//我改的禁用按钮
					var formSelf = $(form);
					viewSelf.model.submitForm(formSelf, function(r){//回调值r同理
						if(r.success){
							$("#modal-productAreaInfo").modal("hide");
							formSelf.resetForm();
							viewSelf.oTable.fnDraw(); // 重新加载(刷新)表格中的数据
						} else {
							bootbox.alert(tip + "<strong>" + r.msg + "</strong>");
						}
						utils.button.reset(_button);//启用按钮
					});
				}
			});
			//显示其他隐藏框
			$("#fnProduceAreaForm").find("select[name='areaProperty']").bind("change", function(e) {
				if ($(this).val() == '1') {// 其他
					$('#hid').hide();
				}else{
					$('#hid').show();
				}
			});	
		}
		
	});
	module.exports = view;
});