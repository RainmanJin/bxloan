define(function(require, exports, module){
	var model = require('./model');
	var rm = require('./rm');
	var utils = require('utils');
	var view = Backbone.View.extend({
		el: "body",
		events: {
			"click a[role='gain']":"fnGain"
		},
		
		initialize: function(){
			this.isView=utils.parseBool($("#isView").val());//是否显示
			this.isEdit=utils.parseBool($("#isEdit").val());//是否是编辑
			this.model=new model();
			this.render();
		},
		fnGain: function(){
			var seq = $("#news2").data("seq");
			if (seq == "addNew") {
				$("#FnDistributionForm").find("input[name='id']").val("");
			}
			$("#modal-distribution div.modal-header h4").html("<i class='ace-icon fa fa-plus'></i>过去12个月利润分配");
			$("#FnDistributionForm").resetForm();
			$("#FnDistributionForm").find("input,select,textarea").removeAttr("disabled");
			$("#gainBtn").show();
			$('#others').hide();
			$("#modal-distribution").modal("show");
		},
		render: function(){
			this.initTime();
			this.initDataTables();
			this.submitForm();
			this.delBtnLive();
			this.editBtnLive();
			this.seeBtnLive();
		},
		initTime : function(){
			var dateOnTop = function() {
				$(".datepicker").css("z-index", "99999");
			};
			var defaultDateConf = {
				autoclose : true,
				todayHighlight : true,
				todayBtn: "linked",
				clearBtn : true,
				endDate : 'd'
			};
			var initDateP = function(selector, changeCallBack, config) {
				var _dateConf = config || defaultDateConf;
				$(selector).datepicker(_dateConf).on("changeDate",
						changeCallBack).on("show",function(){
							$(".datepicker").css("z-index","99999");
						});;
			};
			initDateP("#time");
			
		},
		initDataTables : function() {/** 初始化DataTables */
			var viewSelf = this;
			utils.dd.initDataDict(["FundType"], function(dataDict) {
				var oTable = $("#gainTbl").dataTable({
					//获取数据url
					sAjaxSource:$$ctx + "gainDistribution/findBySearch",
	
					bFilter  : false,
					bPaginate: false,
					bInfo:false,
					aoColumns : [
					     {mData:"gainDisType",sWidth:"150px",  mRender : function(data, type, rowdata){
					    	// alert(data);
					    	 if(parseInt(data)<4){
					    		 return dataDict.FundType[data];
					    	 }else{
					    		 return rowdata.name;
					    	 }
					     }},
					     {mData:"time"},
					     {mData:"content"},
					     {mData:"amount"},
				         {mData:"id",  mRender : function(data, type, rowdata){//列值，状态（隐藏/显示），行值
				            var html =[];
				            html.push("<div class='btn-group'style='width:100px;'>");
			            	if(viewSelf.isEdit){
			            		html.push("<button type='button' role='editGain' title='修改' data-id='" + data + "'  class='btn btn-xs btn-info'><i class='ace-icon fa fa-edit'></i></button> ");
			            		html.push("<button type='button' role='deleteGain' title='删除' data-id='" + data+ "'  class='btn btn-xs btn-danger'><i class='ace-icon fa fa-trash-o'></i></button>");
			            	}
			            	html.push("<button type='button' role='seeGain' title='查看' data-id='" + data+ "'  class='btn btn-xs btn-yellow'><i class='ace-icon fa fa-eye'></i></button>");
			            	html.push('</div> ');
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
			$(document).on("click","button[role='deleteGain']", function(e){// 动态绑定所有删除按钮的click事件
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
							viewSelf.model.delGainById(btnSelf.data("id"), function(r){
								if(r.success){
									$("#gainTbl").dataTable().fnDraw(); // 重新加载表格中的数据
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
			$(document).on("click","button[role='editGain']",function(e){
				var btnself = $(this);
				viewSelf.model.updateGainById(btnself.data("id"),function(obj){
					
					$("#FnDistributionForm").find("input,select,textarea").removeAttr("disabled");
					$.each($("#FnDistributionForm").find("input, select, textarea"), function() {
						$(this).val(obj[$(this).attr("name")]);
					});
					$("#gainBtn").show();
					if ($("#gainDisType").val() == "4") {
						$('#others').show();
					} else {
						$('#others').hide();
					}
					$("#modal-distribution div.modal-header h4").html("<i class='ace-icon fa fa-edit'></i> 过去12个月利润分配");				
					$("#modal-distribution").modal("show");				
				});
			});
		},
		seeBtnLive:function(){/** 查看按钮的预绑定事件 */
			var viewSelf = this;
			$(document).on("click","button[role='seeGain']",function(e){
				var btnself = $(this);
				viewSelf.model.updateGainById(btnself.data("id"),function(obj){
					
					$.each($("#modal-distribution").find("input, select, textarea"), function() {
						$(this).val(obj[$(this).attr("name")]);
					});
					$("#FnDistributionForm").find("input,select,textarea").attr("disabled","disabled","disabled");
					$("#gainBtn").hide();
					if ($("#gainDisType").val() == "4") {
						$('#others').show();
					} else {
						$('#others').hide();
					}
					$("#modal-distribution div.modal-header h4").html("<i class='ace-icon fa fa-eye'></i> 过去12个月利润分配");
					$("#modal-distribution").modal("show");				
				});
			});

		},
		submitForm:function(){//jquery的验证插件
			var viewSelf = this;
			//var btnSelf=$(e.currentTarget);
			//utils.button.ban(btnSelf);//禁用按钮
			//var formSelector = "#FnDistributionForm";
			$("#FnDistributionForm").validate({
				rules:rm.rules,//自定义规则
				submitHandler: function(form){//参名form是$()取得的表单信息   
					var _button = $("#FnDistributionForm").find("button");
					utils.button.ban(_button);
					var formSelf =$(form);
					viewSelf.model.submitForm(formSelf, function(r){//回调值r同理
						if(r.success){
							$("#modal-distribution").modal("hide");
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
			$("#FnDistributionForm").find("select[name='gainDisType']").bind("change", function(e) {
				if ($(this).val() == '4') {// 其他
					$('#others').show();
				}else{
					$('#others').hide();
				}
			});
		}
		
	});
	module.exports = view;
});