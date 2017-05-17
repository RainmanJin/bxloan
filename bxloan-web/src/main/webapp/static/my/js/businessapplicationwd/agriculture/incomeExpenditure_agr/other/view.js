define(function(require, exports, module){
	var model = require('./model');
	var utils = require('utils');
	var rm = require('./rm');
	var view = Backbone.View.extend({
		el: "body",
		events: {
			"click a[role='btnIncome']":"income"
		},
		
		initialize: function(){
			var viewSelf=this;
        	viewSelf.isEdit=utils.parseBool($("#isEdit").val());//是否是编辑
			this.model=new model();
			this.render();
		},
		income: function(){		
			var seq = $("#n_other_tab").find("li.active").data("seq");
			if (seq == "pass") {
				$("#otherIncomeForm").find("input[name='id']").val("");
				$("#otherIncomeForm").find("input[name='futurePastType']").val("1");
				$("#modal-otherIncome div.modal-header h4").html("<i class='ace-icon fa fa-plus'></i>其他收入（过去12个月）");
			} else if (seq == "future") {
				$("#otherIncomeForm").find("input[name='id']").val("");
				$("#modal-otherIncome div.modal-header h4").html("<i class='ace-icon fa fa-plus'></i>其他收入（未来12个月）");
				$("#otherIncomeForm").find("input[name='futurePastType']").val("2");
			} else {
                return false;
            }
			$("#otherIncomeForm").resetForm();
			$("#otherIncomeForm").find("input,select,textarea").removeAttr("disabled");
			$("#submit").show();
			$("#others3").hide();
			$("#modal-otherIncome").modal("show");
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
			utils.dd.initDataDict(["IncomeType"], function(dataDict) {
			var oTable = $("#otherTbl").dataTable({
				//获取数据url
				sAjaxSource:$$ctx + "addOtherIncome/findBySearch",
				bFilter  : false,
				bPaginate: false,
				bInfo:false,
				aoColumns : [
				     {mData:"futurePastType",sWidth:"150px",mRender : function(data, type, rowdata){
				    	 if(parseInt(data)==1){
				    		 return "过去12个月";
				    	 } else {
				    		 return "未来12个月";
				    	 }
				     }},
				     {mData:"otherIncomeType",sWidth:"150px",  mRender : function(data, type, rowdata){
					    	// alert(data);
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
			            +"<button type='button' role='editx' title='修改' data-id='" + data + "'  class='btn btn-xs btn-info'><i class='ace-icon fa fa-edit'></i></button> "
			            +"<button type='button' role='deletex' title='删除' data-id='" + data+ "'  class='btn btn-xs btn-danger'><i class='ace-icon fa fa-trash-o'></i></button>"
			            +"<button type='button' role='seex' title='查看' data-id='" + data+ "'  class='btn btn-xs btn-yellow'><i class='ace-icon fa fa-eye'></i></button></div> ";
			            return buttons;*/
			        	//modify by LBQ 20150709 Start 修改权限控制
                     	var html=[];
                         if(viewSelf.isEdit){
                         	html.push("<button type='button' role='editx' data-id='");
                         	html.push(rowdata.id);
                         	html.push("' data-type='");
                         	html.push(rowdata.type);
                         	html.push("' class='btn btn-xs btn-info' title='修改'><i class='ace-icon fa fa-edit' ></i></button> ");
                         }
                         
                         html.push("<button type='button' role='seex' data-id='");
                         html.push(rowdata.id);
                         html.push("' data-type='");
                         html.push(rowdata.type);
                         html.push("' class='btn btn-xs btn-yellow' title='查看'><i class='ace-icon fa fa-eye' ></i></button> ");
                         
                         if(viewSelf.isEdit){
	                            html.push("<button type='button' role='deletex' data-id='");
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
					 	//向后台提交数据
						aoData.push({
							name : "type",
							value : "1"
						},{
							name : "projectId",
							value : $('#projectId').val()
						},{
							name : "futurePastType",
							value : "1"
						});
				}
			});
			viewSelf.oTable = oTable;//可以直接调
			/**   *****************************     */
			var oTable = $("#otherTbl_future").dataTable({
				//获取数据url
				sAjaxSource:$$ctx + "addOtherIncome/findBySearch",
				bFilter  : false,
				bPaginate: false,
				bInfo:false,
				aoColumns : [
				     {mData:"futurePastType",sWidth:"150px",mRender : function(data, type, rowdata){
				    	 if(parseInt(data)==1){
				    		 return "过去12个月";
				    	 } else {
				    		 return "未来12个月";
				    	 }
				     }},
				     {mData:"otherIncomeType",sWidth:"150px",  mRender : function(data, type, rowdata){
					    	// alert(data);
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
			            +"<button type='button' role='editx' title='修改' data-id='" + data + "'  class='btn btn-xs btn-info'><i class='ace-icon fa fa-edit'></i></button> "
			            +"<button type='button' role='deletex' title='删除' data-id='" + data+ "'  class='btn btn-xs btn-danger'><i class='ace-icon fa fa-trash-o'></i></button>"
			            +"<button type='button' role='seex' title='查看' data-id='" + data+ "'  class='btn btn-xs btn-yellow'><i class='ace-icon fa fa-eye'></i></button></div> ";
			            return buttons;	*/
			          //modify by LBQ 20150709 Start 修改权限控制
                     	var html=[];
                         if(viewSelf.isEdit){
                         	html.push("<button type='button' role='editx' data-id='");
                         	html.push(rowdata.id);
                         	html.push("' data-type='");
                         	html.push(rowdata.type);
                         	html.push("' class='btn btn-xs btn-info' title='修改'><i class='ace-icon fa fa-edit' ></i></button> ");
                         }
                         
                         html.push("<button type='button' role='seex' data-id='");
                         html.push(rowdata.id);
                         html.push("' data-type='");
                         html.push(rowdata.type);
                         html.push("' class='btn btn-xs btn-yellow' title='查看'><i class='ace-icon fa fa-eye' ></i></button> ");
                         
                         if(viewSelf.isEdit){
	                            html.push("<button type='button' role='deletex' data-id='");
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
							value : "1"
						},{
							name : "projectId",
							value : $('#projectId').val()
						},{
							name : "futurePastType",
							value : "2"
						});
				}
			});
			viewSelf.oTable = oTable;//可以直接调
			
			/**   *****************************     */
			});
		},
		delBtnLive: function(){/** 删除按钮的预绑定事件 */
			var viewSelf = this;
			$(document).on("click","button[role='deletex']", function(e){// 动态绑定所有删除按钮的click事件
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
							viewSelf.model.delOtherIncomeById(btnSelf.data("id"), function(r){
								if(r.success){
									bootbox.alert("<strong>该条数据删除成功！<strong>");
									$("#otherTbl").dataTable().fnDraw(); // 重新加载表格中的数据
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
			$(document).on("click","button[role='editx']",function(e){
				var btnself = $(this);
				viewSelf.model.updateOtherIncomeById(btnself.data("id"),function(obj){
					
					$.each($("#modal-otherIncome").find("input, select, textarea"), function() {
						$(this).val(obj[$(this).attr("name")]);
					});
					$("#otherIncomeForm").find("input,select,textarea").removeAttr("disabled");
					$("#submit").show();
					if ($("#otherIncomeType").val() == "2") {
						$('#others3').show();
					} else {
						$('#others3').hide();
					}
					$("#modal-otherIncome div.modal-header h4").html("<i class='ace-icon fa fa-edit'></i>其他收入");
					$("#modal-otherIncome").modal("show");				
				});
			});
		},
		seeBtnLive:function(){/** 查看按钮的预绑定事件 */
			var viewSelf = this;
			$(document).on("click","button[role='seex']",function(e){
				var btnself = $(this);
				viewSelf.model.updateOtherIncomeById(btnself.data("id"),function(obj){
					
					$.each($("#modal-otherIncome").find("input, select, textarea"), function() {
						$(this).val(obj[$(this).attr("name")]);
					});
					$("#otherIncomeForm").find("input,select,textarea").attr("disabled","disabled","disabled");
					$("#submit").hide();
					if ($("#otherIncomeType").val() == "2") {
						$('#others3').show();
					} else {
						$('#others3').hide();
					}
					$("#modal-otherIncome div.modal-header h4").html("<i class='ace-icon fa fa-eye'></i>其他收入");
					$("#modal-otherIncome").modal("show");				
				});
			});

		},
		submitForm:function(){//jquery的验证插件
			var viewSelf = this;
			$("#otherIncomeForm").validate({
				rules:rm.rules,//自定义规则
				submitHandler: function(form){//参名form是$()取得的表单信息   
					var _button = $("#otherIncomeForm").find("button");
					utils.button.ban(_button);//我改的禁用按钮
					var formSelf =$(form);
					viewSelf.model.submitForm(formSelf, function(r){//回调值r同理
						if(r.success){
							//bootbox.alert("用户保存成功！");//
							utils.alert.suc("<b>保存成功！</b>");
							$("#modal-otherIncome").modal("hide");
							formSelf.resetForm();
							var type = $("#otherIncomeForm").find("input[name='futurePastType']").val();
							if(type=="1"){ // 重新加载(刷新)表格中的数据
								$("#otherTbl").dataTable().fnDraw();
							}else if(type=="2"){
								$("#otherTbl_future").dataTable().fnDraw();
							}else{
								return false;
							}
						} else {
							utils.alert.err("<b>" + r.msg + "</b>");
						}
						utils.button.reset(_button);//启用按钮
					});
				}
			});
			//显示其他隐藏框
			$("#otherIncomeForm").find("select[name='otherIncomeType']").bind("change", function(e) {
				if ($(this).val() == '2') {// 其他
					$('#others3').show();
				}else{
					$('#others3').hide();
				}
			});	
		}
		
	});
	module.exports = view;
});