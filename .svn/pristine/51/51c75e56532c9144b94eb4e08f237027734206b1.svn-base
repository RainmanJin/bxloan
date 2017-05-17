define(function(require, exports, module){
	var model = require('./model');
	var utils = require("utils");
	var rm = require('./rm');
	var view = Backbone.View.extend({
		el: "body",
		events: {
			"click a[role='btnFnIncome']":"fnIncome"
		},
		
		initialize: function(){
			var viewSelf=this;
        	viewSelf.isEdit=utils.parseBool($("#isEdit").val());//是否是编辑
			this.model=new model();
			this.render();
		},
		fnIncome: function(){
			var seq = $("#new2").data("seq");
			if (seq == "addNew") {
				$("#FnOtherIncomeForm").find("input[name='id']").val("");
			}
			$("#modal-FnOtherIncome div.modal-header h4").html("<i class='ace-icon fa fa-plus'></i>其他收入");
			$("#FnOtherIncomeForm").resetForm();
			$("#FnOtherIncomeForm").find("input,select,textarea").removeAttr("disabled");
			$("#subBtn").show();
			$('#others2').hide();
			$("#modal-FnOtherIncome").modal("show");
		},
		render: function(){
			this.initDataTables();
			this.submitForm();
			this.delBtnLive();
			this.editBtnLive();
			this.seeBtnLive();
		},
		
		initDataTables : function() {/** 初始化DataTables */
			var viewSelf = this;
			//var projectId = $("#projectId").val();
			utils.dd.initDataDict(["IncomeType"], function(dataDict) {
				var oTable = $("#fnOtherTbl").dataTable({
					//获取数据url
					sAjaxSource:$$ctx + "addFnOtherIncome/findBySearch",
					bFilter  : false,
					bPaginate: false,
					bInfo:false,
					aoColumns : [
					             {mData:"otherIncomeType",sWidth:"150px", mRender : function(data,type,rowdata){
					            	 //alert(data);
					            	 if(parseInt(data)==1){
					            		 return dataDict.IncomeType[data];
					            	 }else{
					            		 return rowdata.name;
					            	 }
					             }},
					             {mData:"yearIncome"},
					             {mData:"remarks"},
					             {mData:"id",  mRender : function(data, type, rowdata){//列值，状态（隐藏/显示），行值
					            	 /*var buttons = "<div class='btn-group'style='width:100px;'>"
					            		 +"<button type='button' role='editFn' title='修改' data-id='" + data + "'  class='btn btn-xs btn-info'><i class='ace-icon fa fa-edit'></i></button> "
					            		 +"<button type='button' role='deleteFn' title='删除' data-id='" + data+ "'  class='btn btn-xs btn-danger'><i class='ace-icon fa fa-trash-o'></i></button>"
					            		 +"<button type='button' role='seeFn' title='查看' data-id='" + data+ "'  class='btn btn-xs btn-yellow'><i class='ace-icon fa fa-eye'></i></button></div> ";
					            	 return buttons;*/	
					            	//modify by LBQ 20150709 Start 修改权限控制
			                        	var html=[];
			                            if(viewSelf.isEdit){
			                            	html.push("<button type='button' role='editFn' data-id='");
			                            	html.push(rowdata.id);
			                            	html.push("' data-type='");
			                            	html.push(rowdata.type);
			                            	html.push("' class='btn btn-xs btn-info' title='修改'><i class='ace-icon fa fa-edit' ></i></button> ");
			                            }
			                            
			                            html.push("<button type='button' role='seeFn' data-id='");
			                            html.push(rowdata.id);
			                            html.push("' data-type='");
			                            html.push(rowdata.type);
			                            html.push("' class='btn btn-xs btn-yellow' title='查看'><i class='ace-icon fa fa-eye' ></i></button> ");
			                            
			                            if(viewSelf.isEdit){
				                            html.push("<button type='button' role='deleteFn' data-id='");
				                            html.push(rowdata.id);
				                            html.push("' class='btn btn-xs btn-danger' data-type='");
				                            html.push(rowdata.type);
				                            html.push("' title='删除'><i class='ace-icon fa fa-trash-o'></i></button>");
			                            }
			                            //modify by LBQ 20150709 End 修改权限控制
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
			$(document).on("click","button[role='deleteFn']", function(e){// 动态绑定所有删除按钮的click事件
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
							viewSelf.model.delOtherFnIncomeById(btnSelf.data("id"), function(r){
								if(r.success){
									$("#fnOtherTbl").dataTable().fnDraw(); // 重新加载表格中的数据
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
			$(document).on("click","button[role='editFn']",function(e){
				var btnself = $(this);
				viewSelf.model.updateFnOtherIncomeById(btnself.data("id"),function(obj){
					
					$.each($("#modal-FnOtherIncome").find("input, select, textarea"), function() {
						$(this).val(obj[$(this).attr("name")]);
					});
					$("#FnOtherIncomeForm").find("input,select,textarea").removeAttr("disabled");
					$("#subBtn").show();
					if ($("#otherIncomeType").val() == "2") {
						$('#others2').show();
					} else {
						$('#others2').hide();
					}
					$("#modal-FnOtherIncome div.modal-header h4").html("<i class='ace-icon fa fa-edit'></i> 其他收入");
					$("#modal-FnOtherIncome").modal("show");				
				});
			});
		},
		seeBtnLive:function(){/** 查看按钮的预绑定事件 */
			var viewSelf = this;
			$(document).on("click","button[role='seeFn']",function(e){
				var btnself = $(this);
				viewSelf.model.updateFnOtherIncomeById(btnself.data("id"),function(obj){
					
					$.each($("#modal-FnOtherIncome").find("input, select, textarea"), function() {
						$(this).val(obj[$(this).attr("name")]);
					});
					$("#FnOtherIncomeForm").find("input,select,textarea").attr("disabled","disabled","disabled");
					$("#subBtn").hide();
					if ($("#otherIncomeType").val() == "2") {
						$('#others2').show();
					} else {
						$('#others2').hide();
					}
					$("#modal-FnOtherIncome div.modal-header h4").html("<i class='ace-icon fa fa-eye'></i> 其他收入");
					$("#modal-FnOtherIncome").modal("show");				
				});
			});

		},
		submitForm:function(){//jquery的验证插件
			var viewSelf = this;
			$("#FnOtherIncomeForm").validate({
				rules:rm.rules,//自定义规则
				submitHandler: function(form){//参名form是$()取得的表单信息   
					var _button = $("#FnOtherIncomeForm").find("button");
					utils.button.ban(_button);//我改的禁用按钮
					var formSelf =$(form);
					viewSelf.model.submitForm(formSelf, function(r){//回调值r同理
						if(r.success){
							$("#modal-FnOtherIncome").modal("hide");
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
			$("#FnOtherIncomeForm").find("select[name='otherIncomeType']").bind("change", function(e) {
				if ($(this).val() == '2') {// 其他
					$('#others2').show();
				}else{
					$('#others2').hide();
				}
			});		
		}
		
	});
	module.exports = view;
});