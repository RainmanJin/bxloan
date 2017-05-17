define(function(require, exports, module){
	var model = require('./model');
	var rm = require('./rm');
	var utils = require('utils');
	var view = Backbone.View.extend({
		el: "body",
		events: {
			"click a[role='nyGain']":"gain"
		},
		
		initialize: function(){
			this.model=new model();
			this.isEdit=utils.parseBool($("#isEdit").val());//是否是编辑
			this.render();
		},
		gain: function(){
			var seq = $("#new1").data("seq");
			if (seq == "addNew") {
				$("#DistributionForm").find("input[name='id']").val("");
			}
			$("#modal-nydistribution div.modal-header h4").html("<i class='ace-icon fa fa-plus'></i>过去12个月利润分配");
			$("#DistributionForm").resetForm();
			$("#DistributionForm").find("input,select,textarea").removeAttr("disabled");
			$("#nyGainBtn").show();
			$('#others2').hide();
			$("#modal-nydistribution").modal("show");
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
				var oTable = $("#nyGainTbl").dataTable({
					//获取数据url
					sAjaxSource:$$ctx + "nyGainDistribution/findBySearch",
	
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
				            var buttons = "<div class='btn-group'style='width:100px;'>";
				            if(viewSelf.isEdit){
				            	buttons+=("<button type='button' role='editny' title='修改' data-id='" + data + "'  class='btn btn-xs btn-info'><i class='ace-icon fa fa-edit'></i></button> ");
				            	buttons+=("<button type='button' role='deleteny' title='删除' data-id='" + data+ "'  class='btn btn-xs btn-danger'><i class='ace-icon fa fa-trash-o'></i></button>");
				            }
				            buttons+=("<button type='button' role='seeny' title='查看' data-id='" + data+ "'  class='btn btn-xs btn-yellow'><i class='ace-icon fa fa-eye'></i></button></div> ");
				            return buttons;			          
				          }}
					 ],
					 fnServerParams : function(aoData) {
						 //农业为主
							aoData.push({
								name : "type",
								value : "1"
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
			$(document).on("click","button[role='deleteny']", function(e){// 动态绑定所有删除按钮的click事件
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
									$("#nyGainTbl").dataTable().fnDraw(); // 重新加载表格中的数据
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
			$(document).on("click","button[role='editny']",function(e){
				var btnself = $(this);
				viewSelf.model.updateGainById(btnself.data("id"),function(obj){
					
					$("#DistributionForm").find("input,select,textarea").removeAttr("disabled");
					$.each($("#DistributionForm").find("input, select, textarea"), function() {
						$(this).val(obj[$(this).attr("name")]);
					});
					$("#nyGainBtn").show();
					if ($("#gainDisType").val() == "4") {
						$('#others2').show();
					} else {
						$('#others2').hide();
					}
					$("#modal-nydistribution div.modal-header h4").html("<i class='ace-icon fa fa-edit'></i> 过去12个月利润分配");				
					$("#modal-nydistribution").modal("show");				
				});
			});
		},
		seeBtnLive:function(){/** 查看按钮的预绑定事件 */
			var viewSelf = this;
			$(document).on("click","button[role='seeny']",function(e){
				var btnself = $(this);
				viewSelf.model.updateGainById(btnself.data("id"),function(obj){
					
					$.each($("#modal-nydistribution").find("input, select, textarea"), function() {
						$(this).val(obj[$(this).attr("name")]);
					});
					$("#DistributionForm").find("input,select,textarea").attr("disabled","disabled","disabled");
					$("#nyGainBtn").hide();
					if ($("#gainDisType").val() == "4") {
						$('#others2').show();
					} else {
						$('#others2').hide();
					}
					$("#modal-nydistribution div.modal-header h4").html("<i class='ace-icon fa fa-eye'></i> 过去12个月利润分配");
					$("#modal-nydistribution").modal("show");				
				});
			});

		},
		submitForm:function(){//jquery的验证插件
			var viewSelf = this;
			//var formSelector = "#FnDistributionForm";
			$("#DistributionForm").validate({
				rules:rm.rules,//自定义规则
				submitHandler: function(form){//参名form是$()取得的表单信息   
					var _button = $("#DistributionForm").find("button");
					utils.button.ban(_button);//我改的禁用按钮
					var formSelf =$(form);
					viewSelf.model.submitForm(formSelf, function(r){//回调值r同理
						if(r.success){
							$("#modal-nydistribution").modal("hide");
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
			$("#DistributionForm").find("select[name='gainDisType']").bind("change", function(e) {
				if ($(this).val() == '4') {// 其他
					$('#others2').show();
				}else{
					$('#others2').hide();
				}
			});
		}
		
	});
	module.exports = view;
});