define(function(require, exports, module) {
	//var model = require("../../main/model");
	var model = require("./model");
	var rm_other_biz_income = require("./rm_other_biz_income");
	var rm_other_income_common = require("./rm_other_income_common");
	var rm_family_consume_agr = require("./rm_family_consume_agr");
	var utils = require("utils");
	var getHidden = function(hiddenName) {
        return $("#hiddenField").find("input[name='" + hiddenName + "']").val();
    }
	//学杂费及每月支出的js模板
	var TbTemplate=function(config){
		var tplSelf=this;
		if(!config){
			return;
		}
		tplSelf._modal=$(config.modal);//弹出框
		var modal=tplSelf._modal;//弹出框
		var viewSelf=config.viewSelf;//当前视图
		tplSelf._objCode=config.objCode;//条目编号
		var objType='05';//家庭生活消费
		var projectId=$("#projectId").val();
		tplSelf.show=function(){
			//加载数据
			viewSelf.model.findEcfiList({
				'projectId' : projectId,//业务id
				'objCode' : tplSelf._objCode,
	   			'objType' :objType,
	   			'flag':'2'
			},function(r_data){
				if(r_data&&r_data.success){
					var $tbody=modal.find("table tbody");
					$tbody.empty();
					$.each(r_data.data,function(i,v){
						$tbody.append(viewSelf.editTable.fnDrawRow(v));
						if(viewSelf.viewMode.isEdit){//编辑时绑定事件
							tplSelf.bindRowDetail();//绑定事件
						}
					});
					viewSelf.editTable.fnDrawRowNum(modal.find("table"));
					viewSelf.editTable.fnDrawTotalAmtMoney(modal.find("table"));
				
				}
			});
			tplSelf._modal.modal('show');//显示
		};
		var reg_amtMoney =/^[0-9]+(\.[0-9]{1,2})?$/;//正整数或两位小数
		//绑定一行事件
		tplSelf.bindRowDetail=function(){
			var tplSelf=this;
			var $newAdd=modal.find('table tbody tr:last');
			$newAdd.find(':input[name="monthOfYear"]').datepicker({ format: "yyyy-mm", startView: 2, minViewMode: 1, todayBtn: "linked", clearBtn: true, autoclose: true}).on("show",function(){
				$(".datepicker").css("z-index","99999");
			});
			$newAdd.find(':input[name="amtMoney"]').change(function(){
				viewSelf.editTable.fnDrawTotalAmtMoney(modal.find("table"));
			});
			$newAdd.find('button[role="save_tuitionDetail"]').click(function(){
				var btnSelf=$(this);
				utils.button.ban(btnSelf);//禁用按钮
				var $tr=btnSelf.parents("tr");
				var monthOfYear=$tr.find(':input[name="monthOfYear"]').val();
				var amtMoney=$tr.find(':input[name="amtMoney"]').val();
				if(!monthOfYear||!amtMoney){
        			utils.alert.warn("年月、金额均为必填项",function(){
        				utils.button.reset(btnSelf);//启用按钮
        			});
    				return;
				}
				if(!reg_amtMoney.test(amtMoney)){
					utils.alert.warn("金额请输入数字，允许两位小数",function(){
						utils.button.reset(btnSelf);//启用按钮
					});
					return;
				}
				var data=[];
				var id=$tr.data("id");
				data.push("id="+(id?id:''));
				data.push("projectId="+projectId);
				data.push("objType="+objType);
				data.push("objCode="+tplSelf._objCode);
				data.push("monthOfYearStr="+monthOfYear);
				data.push("amtMoney="+amtMoney);
				data.push("incomeExpendFlag=2");
				data.push("objContent="+$tr.find(':input[name="objContent"]').val());
				viewSelf.model.saveEcfiDetail(data.join('&'),function(r_data){
					if(r_data&&r_data.success){
						utils.alert.suc("保存成功",function(){
							$tr.data('id',r_data.data);
						});
					}else{
						utils.alert.err("<b>" + r_data.msg + "</b>");
					}
					utils.button.reset(btnSelf);//启用按钮
				});
			});
			$newAdd.find('button[role="del_tuitionDetail"]').click(function(){
				var btnSelf=$(this);
				utils.button.ban(btnSelf);//禁用按钮
				var $tr=btnSelf.parents("tr");
				var id=$tr.data('id');
				if(!id){
					$tr.remove();//移除当前行
					viewSelf.editTable.fnDrawRowNum(modal.find("table"));//重新计算序号
					viewSelf.editTable.fnDrawTotalAmtMoney(modal.find("table"));
					utils.button.reset(btnSelf);//启用按钮
					return;
				}
				viewSelf.model.deleteEcfi(id,function(r_data){
					if(r_data&&r_data.success){
						utils.alert.suc("删除成功",function(){
							$tr.remove();//移除当前行
							viewSelf.editTable.fnDrawRowNum(modal.find("table"));//重新计算序号
							viewSelf.editTable.fnDrawTotalAmtMoney(modal.find("table"));
						});
					}else{
						utils.alert.err("<b>" + r_data.msg + "</b>");
					}
					utils.button.reset(btnSelf);//启用按钮
				});
			});
		}
		//
		tplSelf.addRowDetail=function(){
			modal.find("table tbody").append(viewSelf.editTable.fnDrawRow());
			viewSelf.editTable.fnDrawRowNum(modal.find("table"));
			var $newAdd=modal.find('table tbody tr:last');
			tplSelf.bindRowDetail();//绑定事件
		}
	}
	
	var view = Backbone.View.extend({
		el : "body",
		events : {
			"click #create_other_biz_income" : "create_other_biz_income",
			"click #create_other_income_common" : "create_other_income_common",
			"click button[role='tuition_detail']" : "showTuitionDetail",//打开学杂费明细
			"click button[role='addTuitionDetail']" : "fnAddTuitionDetail",//新增一条学杂费明细
			"click button[role='loanRepaymentDetail']" : "showLoanRepaymentDetail",//打开贷款还款明细
			"click button[role='addLoanRepaymentDetail']" : "fnAddLoanRepaymentDetail",//新增贷款还款明细
		    "click #btn-addEcfIncome-business" : "addIncomeBusiness",//增加工商业收入行
            "click #btn-addEcfConsume-business" : "addConsumeBusiness",//增加工商业支出行
            "click #submit-n-business_future" : "submitNBusinessFuture",//保存工商业表单
            "click .nfuture-close" : "nfutureClose"//关闭窗口时，跳到第一步
		},
		initialize : function() {
			this.model = new model();
			this.render();
			//显示方式
			this.viewMode={
					isView:utils.parseBool($("#isView").val()),//显示
					isEdit:utils.parseBool($("#isEdit").val())//编辑
			}
		},
		render : function() {
			this.initFrist();
			this.initWiazrdBusiness();
			this.initPage();
			this.initOtherBizIncomeForm();
			this.initOtherBizIncomeTable();
			this.initOtherIncomeCommonForm();
			this.initOtherIncomeCommonTable();
			this.initFamilyConsumeForm();
			this.initStatisticsTable();
			this.onChangeAndCount();
			this.futureOrPast();
			this.initTbTemplate();//初始化table模板
			
			this.findIncomeList();//查询收入列表
            this.findConsumeList();//查询支出列表
			this.incomeSummation();//收入合计
	        this.consumeSummation();//支出合计
			
			this.initEditBusinessBtn();//初始化编辑
			this.initViewBusinessBtn();//初始化详情
			this.initDeleteBusinessBtn();//初始化删除
			
			this.saveRowBtnLive();
			this.modifyRowBtnLive();
			this.deleteRowBtnLive();
		},
		initFrist : function(){
			var num =1;
        	var viewSelf = this;
            formFunc = {
        		addRow: function(btn, type){
		        	var time = "<input type='text' size='1' name='monthOfYear' class='form-control input-sm business-date-picker' data-date-format='yyyy-MM'/>";
		        	var inputMoney = "<input type='text' size='1' name='amtMoney' data-culway='business' class='form-control input-sm'/>";
		        	var inputName = "<input type='text' name='objContent' class='form-control input-sm' />";
		        	var button = "<button type='button' role='business_save_ecfi' data-type='"+ type +"'  class='btn btn-xs btn-info' title='保存'><i class='ace-icon fa fa-floppy-o' ></i></button> "  + "<button type='button' role='cancel_ecfi' title='删 除' data-id='' class='btn btn-xs btn-danger' ><i class='ace-icon fa fa-times'></i></button>";
		        	var newRow = "<tr class='odd'>" +
		        				 "<td>"+this.addSeqnum(btn)+"</td>"+
				     			 "<td>"+ time +"</td>" + //time
				     			 "<td>"+ inputMoney +"</td>" + //name
				     			 "<td>"+ inputName +"</td>" + //money
				     			 "<td>"+ button +"</td>" +
				     			 "</tr>";
		        	$(btn+" tbody tr:first").before(newRow);
		        	viewSelf.initDataPickers();
				},
				addSeqnum: function(btn){//自增序号
					var _count = parseInt($(btn+" tbody .odd").eq(0).children().html())+1;
					return $(btn+" tbody .odd").eq(0).length > 0 ? _count : 1;
				},
                disableForm: function(selector, r) {
                    $.each($(selector).find("input, select"),
                    function() {
                        $(this).val(r[$(this).attr("name")]);
                        $(this).prop("disabled", true);
                    });
                    $(selector).find("button[id^='submit']").hide();
                },
                setFormDisabled: function(selector){
                	 $.each($(selector).find("input,select"), function() {
                                 $(this).prop("disabled", true);
                     });
                },
                resetForm: function(selector, r) {
                    $.each($(selector).find("input, select"),
                    function() {
                        $(this).val(r[$(this).attr("name")]);
                        $(this).prop("disabled", false);
                    });
                    $(selector).find("button[id^='submit']").show();
                },
                removeDisabled: function(selector){
                	 $.each($(selector).find("input[type='text'],select"),function() {
                		 if($(this).attr('name') != 'yearIncomeTotal'){
                			 $(this).removeAttr("disabled");
                    		 $(this).removeAttr("readonly");
                		 }else{
                    		 $(this).attr("readonly",true);
                		 }
                		 
                     });
                }
            }
		},
		initPage : function() {
			// 将datepicker控件放在最顶层
			var dateOnTop = function() {
				$(".datepicker").css("z-index", "99999");
			};
			var defaultDateConf = {
				autoclose : true,
				clearBtn : true
			};
			var initDateP = function(selector, changeCallBack, config) {
				var _dateConf = config || defaultDateConf;
				$(selector).datepicker(_dateConf).on("changeDate",
						changeCallBack).on("click", dateOnTop);
			};
			initDateP($("#other_biz_income_form").find("input[name='businessStartDate']"));
			initDateP($("#nFutureCultivateForm_business").find("input[name='businessStartDate']"));
			initDateP($("#other_income_common_form").find("input[name='time']"));
		},
		submitNBusinessFuture: function() {
			$("#nFutureCultivateForm_business").submit();
            /*var viewSelf = this;
               utils.button.ban("#submit-n-business_future");
               if(false){
            	   $.ajax({
                       cache: false,
                       type: "POST",
                       url : $$ctx + 'industryBiz/saveOtherBizIncomeAgr',
                       data: $('#nFutureCultivateForm_business').serialize(),
                       async: false,
                       error: function(request) {
                           utils.alert.err("错误" + request.status);
                       },
                       success: function(data) {
                           utils.button.reset("#submit-n-business_future");
                           if (data) {
                               if (data.success) {
                               	utils.alert.suc("保存成功！");
                               	$("#submit-n-business_future").css("display","none");
                               	$("#modal-formNBusiness_future .wizard-actions .btn-next").attr("disabled",false);
                               	$("#income_form_business input[name='objId']").val(data.data);
                               	//第一步保存成功自动进入第二步骤
                               	viewSelf.skipStep('#business-fuelux-wizard',2);
                               } else {
                                   utils.alert.warn("<strong>" + data.msg + "</strong>");
                               }
                           }
                       }
                   }); //ajax end
               } */
        },
		initOtherBizIncomeForm : function() {
			var viewSelf = this;
			$("#other_biz_income_form").validate({
				errorPlacement : function(error, element) { // 指定错误信息位置
					if (element.is(':radio') || element.is(':checkbox')) { // 如果是radio或checkbox
						var eid = element.attr('name'); // 获取元素的name属性
						error.appendTo(element.parent().parent()); // 将错误信息添加当前元素的父结点后面
					} else {
						error.insertAfter(element);
					}
				},
				rules : rm_other_biz_income.rules,
				messages : rm_other_biz_income.messages,
				submitHandler : function(form) {
					utils.button.ban("#submit-n-business_future");
					$.ajax({
						type : 'POST',
						url : $$ctx + 'industryBiz/saveOtherBizIncomeAgr',
						data : $(form).serialize(),
						success : function(result) {
							utils.button.reset("#submit-n-business_future");
							if (result.success) {
								utils.alert.suc(result.msg);
								var futurePastType = $("#other_biz_income_form").find("input[name='futurePastType']").val();
								if(futurePastType=="1"){
									$("#other_biz_income_table_pass").dataTable().fnDraw();
								}else if(futurePastType=="2"){
									$("#other_biz_income_table_future").dataTable().fnDraw();
								}else{
									return false;
								}
								$("#statisticsTable").dataTable().fnDraw();
								$("#modal-formNBusiness_pass").modal("hide");
							} else {
								utils.alert.err(result.msg);
							}
						}
					});
				}
			});
			$("#nFutureCultivateForm_business").validate({
				errorPlacement : function(error, element) { // 指定错误信息位置
					if (element.is(':radio') || element.is(':checkbox')) { // 如果是radio或checkbox
						var eid = element.attr('name'); // 获取元素的name属性
						error.appendTo(element.parent().parent()); // 将错误信息添加当前元素的父结点后面
					} else {
						error.insertAfter(element);
					}
				},
				rules : rm_other_biz_income.rules,
				messages : rm_other_biz_income.messages,
				submitHandler : function(form) {
					$.ajax({
						type : 'POST',
						url : $$ctx + 'industryBiz/saveOtherBizIncomeAgr',
						data : $(form).serialize(),
						success : function(result) {
							if (result.success) {
								utils.alert.suc(result.msg);
								//第一步保存成功自动进入第二步骤
								viewSelf.skipStep('#business-fuelux-wizard',2);
                               	$("#submit-n-business_future").css("display","none");
                               	$("#modal-formNBusiness_future .wizard-actions .btn-next").attr("disabled",false);
                               	$("#income_form_business input[name='objId']").val(result.data);
                               	
							} else {
								utils.alert.err(result.msg);
							}
						}
					});
				}
			});
			var _this = this;
			$("#other_biz_income_form").find("select[name='futurePastType']").bind("change", function(e) {
				_this.futureOrPast("#other_biz_income_form");
			});
		},
		initOtherBizIncomeTable : function() {
			var viewSelf=this;
			$("#other_biz_income_table_pass").dataTable({
				bFilter : false,
				bLengthChange : false,
				bSort : false,
				sAjaxSource: $$ctx + "industryBiz/initOtherBizIncomeTable",
		    	aoColumns: [
		    	    {mData : "futurePastType", mRender: function(data, type, rowdata) {
		    	    	return data == "1" ? "过去12个月全年" : "未来12个月全年";
		    	    }},
		    	    {mData : "businessContent"},
		    	    {mData : "businessStartDate", mRender: function(data, type, rowdata) {
		    	    	return utils.date.formatDate(data);
		    	    }},
		    	    {mData : "businessPlace"},
		    	    {mData : "id", mRender: function(data, type, rowdata) {
		    	    	return "" +
		    	    	"<div class='btn-group' style='width:100px;'>" +
							"<button title='修改' type='button' class='btn btn-xs btn-info' role='edit_biz_income' data-type='"+rowdata.futurePastType+"' data-id='" + data + "' data-original-title='修改'>" +
								"<i class='ace-icon fa fa-edit'></i>" +
							"</button>" +
							"<button title='查看' type='button' class='btn btn-xs btn-yellow' role='view_biz_income' data-type='"+rowdata.futurePastType+"' data-id='" + data + "' data-original-title='查看'>" +
								"<i class='ace-icon fa fa-eye'></i>" +
							"</button>" +
							"<button title='删除' type='button' class='btn btn-xs btn-danger' role='delete_biz_income' data-type='"+rowdata.futurePastType+"' data-id='" + data + "'>" +
								"<i class='ace-icon fa fa-trash-o'></i>" +
							"</button>" +
						"</div>";
		    	    }},
		    	],
		    	fnServerParams : function(aoData) {
					aoData.push({
						name : "projectId",
						value : $('#projectId').val()
					}, {
						name : "type",
						value : "1"
					}, {
						name : "futurePastType",
						value : "1"
					});
				}
			});
			$("#other_biz_income_table_future").dataTable({
				bFilter : false,
				bLengthChange : false,
				bSort : false,
				sAjaxSource: $$ctx + "industryBiz/initOtherBizIncomeTable",
		    	aoColumns: [
		    	    {mData : "futurePastType", mRender: function(data, type, rowdata) {
		    	    	return data == "1" ? "过去12个月全年" : "未来12个月全年";
		    	    }},
		    	    {mData : "businessContent"},
		    	    {mData : "businessStartDate", mRender: function(data, type, rowdata) {
		    	    	return utils.date.formatDate(data);
		    	    }},
		    	    {mData : "businessPlace"},
		    	    {mData : "id", mRender: function(data, type, rowdata) {
		    	    	var html="";
						if(viewSelf.viewMode.isEdit){
							html+=("<button title='修改' type='button' class='btn btn-xs btn-info' role='edit_biz_income' data-type='"+rowdata.futurePastType+"' data-id='" + data + "' data-original-title='修改'>" +
									"<i class='ace-icon fa fa-edit'></i>" +
									"</button>");
						}
						html+=("<button title='查看' type='button' class='btn btn-xs btn-yellow' role='view_biz_income' data-type='"+rowdata.futurePastType+"' data-id='" + data + "' data-original-title='查看'>" +
							"<i class='ace-icon fa fa-eye'></i>" +
						"</button>");
						if(viewSelf.viewMode.isEdit){
							html+=("<button title='删除' type='button' class='btn btn-xs btn-danger' role='delete_biz_income' data-type='"+rowdata.futurePastType+"' data-id='" + data + "'>" +
									"<i class='ace-icon fa fa-trash-o'></i>" +
									"</button>");
						}
		    	    	return html;
		    	    	
		    	    	/*return "" +
		    	    	"<div class='btn-group' style='width:100px;'>" +
							"<button title='修改' type='button' class='btn btn-xs btn-info' role='edit_biz_income' data-type='"+rowdata.futurePastType+"' data-id='" + data + "' data-original-title='修改'>" +
								"<i class='ace-icon fa fa-edit'></i>" +
							"</button>" +
							"<button title='查看' type='button' class='btn btn-xs btn-yellow' role='view_biz_income' data-type='"+rowdata.futurePastType+"' data-id='" + data + "' data-original-title='查看'>" +
								"<i class='ace-icon fa fa-eye'></i>" +
							"</button>" +
							"<button title='删除' type='button' class='btn btn-xs btn-danger' role='delete_biz_income' data-type='"+rowdata.futurePastType+"' data-id='" + data + "'>" +
								"<i class='ace-icon fa fa-trash-o'></i>" +
							"</button>" +
						"</div>";*/
		    	    }},
		    	],
		    	fnServerParams : function(aoData) {
					aoData.push({
						name : "projectId",
						value : $('#projectId').val()
					}, {
						name : "type",
						value : "1"
					}, {
						name : "futurePastType",
						value : "2"
					});
				}
			});
		},
		initEditBusinessBtn : function(){
			var viewSelf = this;
			$(document).on("click", "button[role='edit_biz_income']", function(e) {
				var id = $(this).data("id");
				var $this = $(this);
				$.ajax({
					type : 'POST',
					url : $$ctx + 'industryBiz/findOneBizIncomeAgr',
					data : {
						'id' : id
					},
					success : function(r) {
						if(r && $this.data('type') =="1"){
							formFunc.resetForm("#other_biz_income_form", r);
							$("#modal-formNBusiness_pass div.modal-header h4").html("<i class='ace-icon fa fa-edit'></i>工商业（过去12个月全年）");
							$("#modal-formNBusiness_pass").find(".modal-footer").show();
							
							$("#other_biz_income_form").find("input[name='stockWhileSurveying']").addClass("required");
							$("#stockWhileSurveyingShowHide_pass").show();
							$("#modal-formNBusiness_pass").modal("show");
						}else if(r && $this.data('type') =="2"){
							formFunc.resetForm("#nFutureCultivateForm_business", r);
							$("#modal-formNBusiness_future div.modal-header h4").html("<i class='ace-icon fa fa-edit'></i>工商业（未来12个月全年）");
							
							$("#nFutureCultivateForm_business").find("input[name='stockWhileSurveying']").val("");
							$("#nFutureCultivateForm_business").find("input[name='stockWhileSurveying']").removeClass("required");
							$("#stockWhileSurveyingShowHide_future").hide();
	                        //显示下一步的图标
	                        $(".btn-last-step").html("下一步 <i class='ace-icon fa fa-arrow-right icon-on-right'></i>");
	                        //隐藏x关闭按钮
	                        $("#modal-formNBusiness_future div.modal-header button").remove();
	                        $("#modal-formNBusiness_future div.modal-header h4").before("<button data-dismiss='modal' class='close nfuture-close' type='button'>×</button>");
	                        
	                        //显示保存按钮
	                        $("#submit-n-business_future").removeAttr('style');
	                        $("#business-fuelux-wizard").data('operate','modify');
	                        //显示收入支出新增按钮
	                        $("#btn-addEcfIncome-business,#btn-addEcfConsume-business").removeAttr('style');
	                        
	                        //显示操作label
	                        $("#income_tb_business thead tr th").last().removeAttr('style');
	                        $("#out_tb_business thead tr th").last().removeAttr('style');
							$("#modal-formNBusiness_future").modal("show");
						}else{
							return false;
						}
					}
				});
			});

		},
		initViewBusinessBtn : function(){
			var viewSelf = this;
			$(document).on("click", "button[role='view_biz_income']", function(e) {
				var id = $(this).data("id");
				var $this = $(this);
				$.ajax({
					type : 'POST',
					url : $$ctx + 'industryBiz/findOneBizIncomeAgr',
					data : {
						'id' : id
					},
					success : function(r) {
						if(r && $this.data('type') =="1"){
							formFunc.resetForm("#other_biz_income_form", r);
							formFunc.setFormDisabled("#other_biz_income_form");
							viewSelf.futureOrPast("#other_biz_income_form");
							$("#modal-formNBusiness_pass div.modal-header h4").html("<i class='ace-icon fa fa-eye'></i>工商业（未来12个月全年）");
							$("#modal-formNBusiness_pass").modal("show");
							
						}else if(r && $this.data('type') == "2"){
							formFunc.resetForm("#nFutureCultivateForm_business", r);//回显
							formFunc.setFormDisabled("#nFutureCultivateForm_business");//禁用
							viewSelf.futureOrPast("#nFutureCultivateForm_business");
							$("#modal-formNBusiness_future div.modal-header h4").html("<i class='ace-icon fa fa-eye'></i>工商业（未来12个月全年）");
							 //隐藏内容
	                        viewSelf.hideContentBusiness();
	                        //设置收入支出明细不可修改
	                        formFunc.setFormDisabled("#income_tb_business,#out_tb_business")
	                        $("#modal-formNBusiness_future").modal("show");
						}else{
							return false;
						}
					}
				});
			});
		},
		initDeleteBusinessBtn : function(){
			$(document).on("click", "button[role='delete_biz_income']", function(e) {
				var id = $(this).data("id");
				bootbox.confirm({
					message: "您确定要删除此记录吗？",
					buttons: {
						confirm: {
						label: "<i class='ace-icon fa fa-trash-o bigger-110'></i> 确定",
							className: "btn-danger btn-sm"
						},
						cancel : {
							label: "<i class='ace-icon fa fa-times bigger-110'></i> 取消",
							className: "btn-warning btn-sm"
						}
					},
					callback: function(result) {
						if (result) {
							$.ajax({
								type : 'post',
								url : $$ctx + 'industryBiz/deleteBizIncome',
								data : {
									'id' : id
								},
								success : function(r) {
									if(r.success) {
										utils.alert.suc("<strong>"+r.msg+"</strong>",function(){});
										$("#other_biz_income_table_pass").dataTable().fnDraw();
										$("#other_biz_income_table_future").dataTable().fnDraw();
										$("#statisticsTable").dataTable().fnDraw();
									} else {
										utils.alert.err(r.msg);
									}
								}
							});
					    }
					}
				});
			});
		},
		create_other_biz_income : function() {
			var viewSelf = this;
			var seq = $("#n_bizGS_tab").find("li.active").data("seq");
            if (seq == "pass") {
            	viewSelf.futureOrPast("#other_biz_income_form");
            	$("#other_biz_income_form").resetForm();
            	//取消禁用状态
                formFunc.removeDisabled("#other_biz_income_form");
                $("#modal-formNBusiness_pass").find("input[name='futurePastType']").val("1");
                $("#modal-formNBusiness_pass div.modal-header h4").html("<i class='ace-icon fa fa-plus'></i>工商业（过去12个月全年）");
                $("#modal-formNBusiness_pass").find("input[name='type']").val("1");
                
                $("#other_biz_income_form").find("input[name='id']").val("");
                $("#other_biz_income_form").find("input[name='projectId']").val(getHidden("projectId"));
                
				$("#modal-formNBusiness_pass").modal("show");
                
            } else if (seq == "future") {
            	viewSelf.futureOrPast("#nFutureCultivateForm_business");
            	$("#nFutureCultivateForm_business").resetForm();
            	//取消禁用状态
                formFunc.removeDisabled("#nFutureCultivateForm_business");
                $("#modal-formNBusiness_future div.modal-header h4").html("<i class='ace-icon fa fa-plus'></i>工商业（未来12个月全年）");
               
                $("#nFutureCultivateForm_business").find("input[name='futurePastType']").val("2");
                $("#nFutureCultivateForm_business").find("input[name='type']").val("1");
                $("#nFutureCultivateForm_business").find("input[name='id']").val("");
                $("#nFutureCultivateForm_business").find("input[name='projectId']").val(getHidden("projectId"));
                $("#nFutureCultivateForm_business").find("input[name='stockWhileSurveying']").val("");
				$("#nFutureCultivateForm_business").find("input[name='stockWhileSurveying']").removeClass("required");
				//清除收入支出金额合计
                viewSelf.clearSum();
                //显示保存按钮
                $("#submit-n-business_future").removeAttr('style');
                $("#business-fuelux-wizard").data('operate','add');
                
                //显示下一步的图标
                $(".btn-last-step").html("下一步 <i class='ace-icon fa fa-arrow-right icon-on-right'></i>");
                $(".btn-last-step").attr('disabled', 'disabled')
                //隐藏x关闭按钮
                $("#modal-formNBusiness_future div.modal-header button").remove();
                $("#modal-formNBusiness_future div.modal-header h4").before("<button data-dismiss='modal' class='close nfuture-close' type='button'>×</button>");
                //显示操作label
                $("#income_tb_business thead tr th").last().removeAttr('style');
                $("#out_tb_business thead tr th").last().removeAttr('style');
                //显示收入支出新增按钮
                $("#btn-addEcfIncome-business,#btn-addEcfConsume-business").removeAttr('style');
                //在第二步和第三步清除收入支出列表缓存
            	$("#income_tb_business tbody tr,#out_tb_business tbody tr").not(".hj").remove();
            	$("#modal-formNBusiness_future").modal("show");
            } else {
                return false;
            }
		},
        clearSum: function(){//清除合计内容
        	var viewSelf = this;
        	 $("#income_tb_business tbody .hj .income-sum").html(0);
             $("#out_tb_business tbody .hj .consume-sum").html(0);
        },
		initDataPickers: function() {//初始化日历
			$('.business-date-picker').datepicker({ format: "yyyy-mm", startView: 0, minViewMode: 1,todayHighlight:true, autoclose: true}).on("show",function(){
						$(".datepicker").css("z-index","99999")});
		},
		addIncomeBusiness: function(){
        	formFunc.addRow("#income_tb_business", 1);//工商业收入
        },
        addConsumeBusiness: function(){
        	formFunc.addRow("#out_tb_business", 2);//工商业支出
        },
        skipStep: function(btn,step){//跳转到指定步骤
       	 var viewSelf = this;
       	 var wizard = $(btn).data('wizard')
            wizard.currentStep = step;
            wizard.setState();
        },
		initOtherIncomeCommonForm : function() {
			$("#other_income_common_form").validate({
				errorPlacement : function(error, element) { // 指定错误信息位置
					if (element.is(':radio') || element.is(':checkbox')) { // 如果是radio或checkbox
						var eid = element.attr('name'); // 获取元素的name属性
						error.appendTo(element.parent().parent()); // 将错误信息添加当前元素的父结点后面
					} else {
						error.insertAfter(element);
					}
				},
				rules : rm_other_income_common.rules,
				messages : rm_other_income_common.messages,
				submitHandler : function(form) {
//					var industries = "";
//					$(form).find("input:checkbox[name=industry]:checked").each(function(i, item) {
//						industries += $(item).val() + ",";
//					});
//					industries = industries.substring(0, industries.length - 1);
					$.ajax({
						type : 'POST',
						url : $$ctx + 'industryBiz/saveOtherIncomeCommon',
						data : $(form).serialize(),
						success : function(result) {
							if (result.success) {
								$("#other_income_common_modal").modal("hide");
								$("#other_income_common_table").dataTable().fnDraw();
								$("#overincome_n_cultivate_tb").dataTable().fnDraw();
								$("#overincome_n_breed_tb").dataTable().fnDraw();
								$("#statisticsTable").dataTable().fnDraw();
							} else {
								utils.alert.err(result.msg);
							}
						}
					});
				}
			});
		},
		initOtherIncomeCommonTable : function() {
			$("#other_income_common_table").dataTable({
				bFilter : false,
				bLengthChange : false,
				bSort : false,
				"bPaginate": false,
				sAjaxSource: $$ctx + "industryBiz/initOtherIncomeCommonTable",
		    	aoColumns: [
		    	    {mData : "time", mRender: function(data, type, rowdata) {
		    	    	return utils.date.formatDate(data);
		    	    }},
		    	    {mData : "content"},
		    	    {mData : "incomeCostType", mRender: function(data, type, rowdata) {
		    	    	return data == '1' ? "收入" : "支出";
		    	    }},
		    	    {mData : "amount", mRender: function(data, type, rowdata) {
		    	    	return data.toFixed(2);
		    	    }},
		    	    {mData : "id", mRender: function(data, type, rowdata) {
		    	    	return "" +
		    	    	"<div class='btn-group' style='width:100px;'>" +
							"<button title='修改' type='button' class='btn btn-xs btn-info' role='edit_other_income_common' data-id='" + data + "' data-original-title='修改'>" +
								"<i class='ace-icon fa fa-edit'></i>" +
							"</button>" +
							"<button title='查看' type='button' class='btn btn-xs btn-yellow' role='view_other_income_common' data-id='" + data + "' data-original-title='查看'>" +
								"<i class='ace-icon fa fa-eye'></i>" +
							"</button>" +
							"<button title='删除' type='button' class='btn btn-xs btn-danger' role='delete_other_income_common' data-id='" + data + "'>" +
								"<i class='ace-icon fa fa-trash-o'></i>" +
							"</button>" +
						"</div>";
		    	    }},
		    	],
		    	fnServerParams : function(aoData) {
					aoData.push({
						name : "projectId",
						value : $('#projectId').val()
					}, {
						name : "type",
						value : "3"
					});
				}
			});
			
			$(document).on("click", "button[role='edit_other_income_common']", function(e) {
				var id = $(this).data("id");
				$.ajax({
					type : 'POST',
					url : $$ctx + 'industryBiz/findOneOtherIncomeCommon',
					data : {
						'id' : id
					},
					success : function(result) {
						$.each($("#other_income_common_form").find("input[type=text], input[type=hidden], select, textarea"), function() {
							if ($(this).attr("name") == "time") {
								$(this).val(utils.date.formatDate(result[$(this).attr("name")]));
							} else {
								$(this).val(result[$(this).attr("name")]);
							}
							$(this).removeAttr("disabled");
						});
						$("#other_income_common_modal div.modal-header h3").html("<i class='ace-icon fa fa-edit'></i> 收支记录(工商业)");
						$("#other_income_common_form").find(".form-group:eq(1)").show();
						$("#other_income_common_modal").find(".modal-footer").show();
						$("#other_income_common_modal").modal("show");
					}
				});
			});
			$(document).on("click", "button[role='view_other_income_common']", function(e) {
				var id = $(this).data("id");
				$.ajax({
					type : 'POST',
					url : $$ctx + 'industryBiz/findOneOtherIncomeCommon',
					data : {
						'id' : id
					},
					success : function(result) {
						$.each($("#other_income_common_form").find("input[type=text], input[type=hidden], select, textarea"), function() {
							if ($(this).attr("name") == "time") {
								$(this).val(utils.date.formatDate(result[$(this).attr("name")]));
							} else {
								$(this).val(result[$(this).attr("name")]);
							}
							$(this).attr("disabled", "disabled");
						});
						$("#other_income_common_modal div.modal-header h3").html("<i class='ace-icon fa fa-eye'></i> 收支记录（工商业）");
						$("#other_income_common_form").find(".form-group:eq(1)").show();
						$("#other_income_common_modal").find(".modal-footer").hide();
						$("#other_income_common_modal").modal("show");
					}
				});
			});
			$(document).on("click", "button[role='delete_other_income_common']", function(e) {
				var id = $(this).data("id");
				bootbox.confirm({
					message: "您确定要删除此记录吗？",
					buttons: {
						confirm: {
						label: "<i class='ace-icon fa fa-trash-o bigger-110'></i> 确定",
							className: "btn-danger btn-sm"
						},
						cancel : {
							label: "<i class='ace-icon fa fa-times bigger-110'></i> 取消",
							className: "btn-warning btn-sm"
						}
					},
					callback: function(result) {
						if (result) {
							$.ajax({
								type : 'post',
								url : $$ctx + 'industryBiz/deleteOtherIncomeCommon',
								data : {
									'id' : id
								},
								success : function(r) {
									if(r.success) {
										$("#other_income_common_table").dataTable().fnDraw();
										$("#statisticsTable").dataTable().fnDraw();
									} else {
										utils.alert.err(r.msg);
									}
								}
							});
					    }
					}
				});
			});
		},
		create_other_income_common : function() {
			$("#other_income_common_modal div.modal-header h3").html("<i class='ace-icon fa fa-plus'></i> 收支记录（工商业）");
			var formSelector = "#other_income_common_form";
			var modalSelector = "#other_income_common_modal";
			$(formSelector).resetForm();
			$(formSelector).find(".form-group:eq(1)").show();
			$(formSelector).find("input[name='id']").val("");
			$(formSelector).find("input[name='type']").val("3");
			$(modalSelector).find(".modal-footer").show();
			$(modalSelector).find("input, select, textarea").removeAttr("disabled");
			$(modalSelector).modal("show");
		},
		initFamilyConsumeForm : function() {
			$("#family_consume_agr_form").validate({
				errorPlacement : function(error, element) { // 指定错误信息位置
					if (element.is(':radio') || element.is(':checkbox')) { // 如果是radio或checkbox
						var eid = element.attr('name'); // 获取元素的name属性
						error.appendTo(element.parent().parent()); // 将错误信息添加当前元素的父结点后面
					} else {
						error.insertAfter(element);
					}
				},
				rules : rm_family_consume_agr.rules,
				messages : rm_family_consume_agr.messages,
				submitHandler : function(form) {
					utils.button.ban($("#family_consume_form").find("button"));
					$.ajax({
						type : 'POST',
						url : $$ctx + 'industryBiz/saveFamilyConsume',
						data : $(form).serialize(),
						success : function(result) {
							utils.button.reset($("#family_consume_form").find("button"));
							if (result.success) {
								utils.alert.suc(result.msg);
								$(form).find("input[name='id']").val(result.data);
								$("#statisticsTable").dataTable().fnDraw();
							} else {
								utils.alert.err(result.msg);
							}
						}
					});
				}
			});
			
			$.ajax({
				type : 'POST',
				url : $$ctx + 'industryBiz/findOneFamilyConsumeByProIdAndType',
				data : {
					"projectId" : $("#projectId").val(),
					"type" : "1"
				},
				success : function(result) {
					var formSelector = "#family_consume_agr_form";
					if (typeof(result) != 'undefined' && $.trim(result) != '') {
						$.each($(formSelector).find("input[type=text], input[type=hidden], select, textarea"), function() {
							$(this).val(result[$(this).attr("name")]);
						});
					}
				}
			});
		},
		initStatisticsTable : function() {
			$("#statisticsTable").dataTable({
				bFilter : false,
				bLengthChange : false,
				bSort : false,
				bPaginate : false,
				bInfo : false,
				sAjaxSource: $$ctx + "industryBiz/initStatisticsTable",
		    	aoColumns: [
		    	    {mData : "typeName"},
		    	    {mData : "pastIncomeAmount", mRender: function(data, type, rowdata) {
		    	    	return data.toFixed(2);
		    	    }},
		    	    {mData : "pastIncomePercent", mRender: function(data, type, rowdata) {
		    	    	return (data * 100).toFixed(2) + "%";
		    	    }},
		    	    {mData : "predictableIncomeAmount", mRender: function(data, type, rowdata) {
		    	    	return data.toFixed(2);
		    	    }},
		    	    {mData : "predictableIncomePercent", mRender: function(data, type, rowdata) {
		    	    	return (data * 100).toFixed(2) + "%";
		    	    }},
		    	    {mData : "predictableGainAmount", mRender: function(data, type, rowdata) {
		    	    	return data.toFixed(2);
		    	    }},
		    	    {mData : "predictableGainPercent", mRender: function(data, type, rowdata) {
		    	    	return (data * 100).toFixed(2) + "%";
		    	    }},
		    	],
		    	fnServerParams : function(aoData) {
					aoData.push({
						name : "projectId",
						value : $('#projectId').val()
					});
				}
			});
		},
		onChangeAndCount : function() {
			var _this = this;
			
			// 年收入合计
			$(".change_yearIncomeTotal").bind("change", function(e) {
				if ($(this).valid()) {
					var formSelector = $(this).closest('form');
					var arr = new Array("month_slack", "month_peak", "monthlyIncome_slack", "monthlyIncome_peak");
					var newArr = _this.toLegal(arr, formSelector);
					var yearIncomeTotal = newArr[0] * newArr[2] + newArr[1] * newArr[3];
					var selector = $(formSelector).find("input[name='yearIncomeTotal']");
					$(selector).val(yearIncomeTotal);
					$(selector).change();
				}
			});
			
			// 总家庭支出
			$(".change_familyConsumeTotal").bind("change", function(e) {
				if ($(this).valid()) {
					var familyConsumeformSelector = "#family_consume_agr_form";
					var arr = new Array("lifeConsume", "tuition", "medical", "insurance", "others1", "others2", "others3");
					var newArr = _this.toLegal(arr, familyConsumeformSelector);
					var familyConsumeTotal = 0;
					$.each(newArr, function(i, item) {
						familyConsumeTotal += item;
					});
					$(familyConsumeformSelector).find("input[name='familyConsumeTotal']").val(familyConsumeTotal);
				}
			});
		},
		toLegal : function(arr, formSelector) {
			var newArr = [];
			$.each(arr, function(i, item) {
				var value = $(formSelector).find("input[name='" + item + "']").val();
				if (typeof(value) == 'undefined' || $.trim(value) == '') {
					value = 0;
				} else {
					value = parseFloat(value);
				}
				newArr.push(value);
			});
			return newArr;
		},
		futureOrPast : function(fselector) {
			var formSelector = fselector;
			var futurePastType = $(formSelector).find("input[name='futurePastType']").val();
			if (futurePastType == "1") {
				$(formSelector).find("input[name='stockWhileSurveying']").addClass("required");
				$("#stockWhileSurveyingShowHide_pass").show();
			} else {
				$(formSelector).find("input[name='stockWhileSurveying']").val("");
				$(formSelector).find("input[name='stockWhileSurveying']").removeClass("required");
				$("#stockWhileSurveyingShowHide_future").hide();
			}
		},
        nfutureClose: function(){
        	 var viewSelf = this;
        	 viewSelf.skipStep('#business-fuelux-wizard',1);
        	  $(".btn-last-step").attr('disabled', 'disabled');
        	  $("#other_biz_income_table_future").dataTable().fnDraw();
              $("#statisticsTable").dataTable().fnDraw();
        },
		initTbTemplate:function(){
			var viewSelf=this;
			var editTable={
					
			};
			editTable.toVal=function(val,defaultVal){
				return (val?val:(defaultVal?defaultVal:''));
			};
			//datas
			editTable.fnDrawRow=function(data){
				var id,monthOfYear,amtMoney,objContent;
				if(data){
					id=data.id;
					monthOfYear=data.monthOfYear;
					amtMoney=data.amtMoney;
					objContent=data.objContent;
				}
				var html=[];
				html.push('<tr ');
				html.push('data-id='+editTable.toVal(id));
				html.push(' >');
				html.push('<td></td>');
				html.push('<td><input type="text" size="1" name="monthOfYear" class="form-control input-sm" data-date-format="yyyy-MM" ');
				if(monthOfYear){
					html.push(' value="',new Date(monthOfYear).format('yyyy-MM'),'" ');
				}
				if(viewSelf.viewMode.isView){
					html.push(' readonly="readonly" ');
				}
				html.push('></td>');
				html.push('<td><input type="text" size="1" name="amtMoney" class="form-control input-sm"');
				html.push(' value="'+editTable.toVal(amtMoney)+'" ');
				if(viewSelf.viewMode.isView){
					html.push(' readonly="readonly" ');
				}
				html.push('></td>');
				html.push('<td><input type="text"  name="objContent" class="form-control input-sm"');
				html.push(' value="'+editTable.toVal(objContent)+'" ');
				if(viewSelf.viewMode.isView){
					html.push(' readonly="readonly" ');
				}
				html.push('></td>');
				html.push('<td>');
				if(viewSelf.viewMode.isEdit){//
					html.push('<button type="button" role="save_tuitionDetail" class="btn btn-xs btn-info" title="保存" data-loading-text="保存中...">');
					html.push('<i class="ace-icon fa fa-floppy-o"></i></button>');
					html.push('<button type="button" role="del_tuitionDetail" title="删 除" data-id="" class="btn btn-xs btn-danger" data-loading-text="删除中...">');
					html.push('<i class="ace-icon fa fa-times"></i></button>');
				}
				html.push('</td>');
				html.push('</tr>');
				return html.join('');
			}
			editTable.fnDrawRowNum=function(selector){//计算序号
				$(selector).find("tbody tr").each(function(i,v){
					$(v).find("td:first").text(i+1);
				});
			},
			editTable.fnDrawTotalAmtMoney=function(selector){//计算总和
				var totalAmtMoney=0;
				$(selector).find('tbody tr').each(function(i,v){
					var $inputVal=$(v).find(':input[name="amtMoney"]').val();
					if($inputVal){
						totalAmtMoney+=parseFloat($inputVal);
					}
				});
				totalAmtMoney=utils.number.commafy(editTable.toVal(totalAmtMoney,0));
				$(selector).find("tr[role='hj'] td:eq(2)").text(totalAmtMoney?totalAmtMoney:0);
			}
			viewSelf.editTable=editTable;
			viewSelf.tuitionDetail = new TbTemplate({
				modal : '#modal-formTuitionDetail',
				viewSelf : viewSelf,
				objCode : '0501'//学杂费
			});
			viewSelf.loanRepaymentDetail = new TbTemplate({
				modal : '#modal-formLoanRepaymentDetail',
				viewSelf : viewSelf,
				objCode : '0502'//其他贷款还款
			});
			
		},
		showTuitionDetail:function(){//学费明细显示
			var viewSelf=this;
			viewSelf.tuitionDetail.show();
		},
		fnAddTuitionDetail:function(){
			var viewSelf=this;
			viewSelf.tuitionDetail.addRowDetail();
		},
		showLoanRepaymentDetail:function(){
			var viewSelf=this;
			viewSelf.loanRepaymentDetail.show();
		},
		fnAddLoanRepaymentDetail:function(){
			var viewSelf=this;
			viewSelf.loanRepaymentDetail.addRowDetail();
		},
		
		//------------------------工商业---------------------------
		saveRowBtnLive: function(){
			var viewSelf = this;
            $(document).on("click", "button[role='business_save_ecfi']", function(e){
            	var $this = $(this);
            	var tr = $this.closest("tr");
            	var validate = function(){
            		 
            		var time =  $(tr).find("td input:eq(0)").val();
            		var money = $(tr).find("td input:eq(1)").val();
            		var remark = $(tr).find("td input:eq(2)").val();
            		
            		var reg =/^[0-9]+(\.[0-9]{1,2})?$/;//正整数或两位小数
            		if(time == ""||money == ""||remark == ""){
            			utils.alert.warn("年月、金额、内容均为必填项");
        				return false;
            		}else if(!reg.test(money)){
        				utils.alert.warn("金额请输入数字，允许两位小数");
        				return false;
        			}
        			return true;
            	}
            	
            	if(!validate()){
            		return false;
            	}
            	
            	utils.button.ban($this);
            	viewSelf.model.saveEcfiDetail({
            		"objId" : $("#income_form_business").find("input[name='objId']").val(),
            		"objContent" : $(tr).find("td input:eq(2)").val(),
            		"objType":$("#income_form_business").find("input[name='objType']").val(),
            		"objName":$("#nFutureCultivateForm_business").find("input[name='businessContent']").val(),
            		"monthOfYearStr" : $(tr).find("td input:eq(0)").val(),
            		"amtMoney" : $(tr).find("td input:eq(1)").val(),
            		"incomeExpendFlag" : $this.data('type'),
            		"projectId":$("#projectId").val()
            	}, function(r){
            		utils.button.reset($this);
            		if(r&&r.success){
            			$this.addClass("btn-success");
            			utils.alert.suc(r.msg);
            			$this.parent().children("button:last-child").attr("role","delete_bus_ecfi");
            			$this.attr("role","modify_bus_ecfi");
            			$this.parent().children("button:last-child").data("id",r.data);
            		}else{
            			$this.removeClass("btn-success");
            			utils.alert.warn(r.msg);
            		}
            	});
            });
		},
        modifyRowBtnLive: function(){
			var viewSelf = this;
            $(document).on("click", "button[role='modify_bus_ecfi']", function(e){
            	var $this = $(this);
            	var tr = $this.closest("tr");
            	var validate = function(){
            		 
            		var time =  $(tr).find("td input:eq(0)").val();
            		var money = $(tr).find("td input:eq(1)").val();
            		var remark = $(tr).find("td input:eq(2)").val();
            		
            		var reg =/^[0-9]+(\.[0-9]{1,2})?$/;//正整数或两位小数
            		if(time == ""||money == ""||remark == ""){
            			utils.alert.warn("年月、金额、内容均为必填项");
        				return false;
            		}else if(!reg.test(money)){
        				utils.alert.warn("金额请输入数字，允许两位小数");
        				return false;
        			}
        			return true;
            	}
            	
            	if(!validate()){
            		return false;
            	}
            	utils.button.ban($this);
            	viewSelf.model.modifyEcfiDetail({
            		"id" : $this.data("eid"),
            		"monthOfYearStr" : $(tr).find("td input:eq(0)").val(),
            		"amtMoney" : $(tr).find("td input:eq(1)").val(),
            		"objId" : $("#income_form_business").find("input[name='objId']").val(),
            		"objContent" : $(tr).find("td input:eq(2)").val(),
            		"projectId" : $("#nFutureCultivateForm_business").find(":input[name='projectId']").val(),
            		"objType" :$("#income_form_business").find(":input[name='objType']").val(),
            		"objName":$("#nFutureCultivateForm_business").find("input[name='businessContent']").val(),
            		"incomeExpendFlag" : $this.data('type'),//区分是收入还是支出
            	}, function(r){
            		utils.button.reset($this);
            		if(r&&r.success){
            			$this.addClass("btn-success");
            			utils.alert.suc(r.msg);
            		}else{
            			$this.removeClass("btn-success");
            			utils.alert.warn(r.msg);
            		}
            		
            	});
            });
		},
		deleteRowBtnLive: function(){
			var viewSelf = this;
            $(document).on("click", "button[role='delete_bus_ecfi']", function(e) { // 动态绑定所有删除按钮的click事件
                var $this = $(this);
                if (bootbox.confirm({
                    message: "确定要删除此条数据吗?",
                    buttons: {
                        confirm: {
                            label: "<i class='ace-icon fa fa-trash-o bigger-110'></i> 确定",
                            className: "btn-danger btn-sm"
                        },
                        cancel: {
                            label: "<i class='ace-icon fa fa-times bigger-110'></i> 取消",
                            className: "btn-sm"
                        }
                    },
                    callback: function(result) {
                        if (result) {
                            var id = $this.data("id");
                            viewSelf.model.deleteEcfi(id, function(data){
                            	if (data.success) {
                            		utils.alert.suc("删除成功！");
                            		$this.closest("tr").remove();
                            		                            		
                            		//重新刷新下合计
                            		viewSelf.initIncomeSummation();
                            		viewSelf.consumeSummation();
                                } else {
                                	utils.alert.warn("<strong>"+data.msg+"</strong>");
                                }
                            });
                        }
                    }
                }));
                return false;
            });
            return false;
        },
        cancelRowBtnLive: function(){
			var viewSelf = this;
            $(document).on("click", "button[role='cancel_ecfi']", function(e){
            	var $this = $(this);
            	var id = $("#modal-formEcf").find("input[name='id']").val();
            	if(!id){
            		$(this).closest("tr").remove();
            		//重新刷新下合计
            		viewSelf.initIncomeSummation();
            		viewSelf.consumeSummation();
            	}else{
            		$this.closest("table").dataTable().fnDraw();
            	}
            });
		},
		initWiazrdBusiness: function(){//工商业
        	var viewSelf = this;
        	var $validation = true;
        	$('#modal-formNBusiness_future .modal-header').ace_wizard().on('change' , function(e, info){
        		var operate= $(e.currentTarget).data('operate');
        		//当点击第一步骤的下一步按钮时
        		if(info.step == 1 && info.direction == "next"){
        			$(".btn-last-step").html("下一步 <i class='ace-icon fa fa-arrow-right icon-on-right'></i>");
        		}
        		//当点击第二步骤的上一步按钮时
        		if(info.step == 2 && info.direction == "previous" && operate != "detail"){
        			$("#submit-n-business_future").removeAttr('style');
					$(".btn-last-step").html("<i class='ace-icon fa fa-arrow-right icon-on-right'></i>");
				}
        		//当点击第二步骤的上一步按钮时
        		if(info.step == 3 && info.direction == "previous"){
					$(".btn-last-step").html("<i class='ace-icon fa fa-arrow-right icon-on-right'></i>");
				}
        		//当点击第二步骤的下一步按钮时
        		if(info.step == 2 && info.direction == "next"){
        			$("#submit-n-business_future").css("display","none");
        			$(".btn-last-step").html(" <i class='glyphicon glyphicon-remove'></i>");
        		}
        		//验证第一步的form表单
        		if(info.step == 1 && $validation) {
					if(!$('#nFutureCultivateForm_business').valid()){
						$("#submit-n-business_future").removeAttr('style');
						return false;
					}else{
						$("#submit-n-business_future").css("display","none");
						return true;
					}
				}
        		
        		
			}).on('finished', function(e) {//最后一步
			  $("#other_biz_income_table_future").dataTable().fnDraw();
              $("#modal-formNBusiness_future").modal("hide");
              $("#statisticsTable").dataTable().fnDraw();
              $("#submit-n-business_future").removeAttr("style");
              viewSelf.skipStep('#business-fuelux-wizard',1);
			});
        	
        	$('#modal-formNBusiness_future .wizard-actions .btn-next').attr('disabled',true);
        	$('#modal-formNBusiness_future .wizard-actions .btn[data-dismiss=modal]').removeAttr('disabled');
        },
        findIncomeList: function(){//收入列表
        	var viewSelf = this;
        	$(document).on("click","button[role='edit_biz_income'],button[role='view_biz_income']",function(e){
        		 var $this = $(this);
        		 viewSelf.model.findEcfiList({
        			 'projectId':$("#projectId").val(),
        			 'objId' : $this.data("id"),
        			 'objCode':null,
        			 'objType' :$this.closest(".panel").data("objid"),
        			 'flag':'1'
        		 },function(result){
        			 $("#income_tb_business tbody tr").not(".hj").remove();
        			 if(result.success && result.data.length > 0){
     		        	var newRow = "";
     		        	var _count = result.data.length;
     		        	for(var i =0; i< result.data.length; i++){
     		        		var date = utils.str.substringBeforeLast(result.data[i].monthOfYear,"-");
     		        		var time = "<input type='text' value='"+date+"' size='1' name='monthOfYear' class='form-control input-sm business-date-picker' data-date-format='yyyy-MM'/>";
         		        	var inputMoney = "<input type='text' value='"+result.data[i].amtMoney+"' size='1' name='amtMoney' data-culway='business' class='form-control input-sm'/>";
         		        	var inputName = "<input type='text' value='"+result.data[i].objContent+"' name='objContent' class='form-control input-sm' />";
         		        	var button = "<button type='button' role='modify_bus_ecfi' data-eid='"+result.data[i].id+"' data-type='1'  class='btn btn-xs btn-info' title='修 改'><i class='ace-icon fa fa-floppy-o' ></i></button> "  
         		        					+ "<button type='button' role='delete_bus_ecfi' data-id='"+result.data[i].id+"' title='删 除' data-id='' class='btn btn-xs btn-danger' ><i class='ace-icon fa fa-times'></i></button>";
         		        	newRow +="<tr class='odd'>" +
         		        		 "<td>"+(_count)+"</td>"+
         		        		 "<td>"+ time +"</td>" + //time
				     			 "<td>"+ inputMoney +"</td>" + //name
				     			 "<td>"+ inputName +"</td>" + //money
				     			 "<td>"+ button +"</td>" +"</tr>";
         		        	_count--;
     		        	}
     		        	$("#income_tb_business tbody .hj").before(newRow);
     		        	viewSelf.initDataPickers();
     		        	//清除总计
                        viewSelf.clearSum();
     		        	viewSelf.initIncomeSummation();
        			 }else{
        				 $("#income_tb_business tbody .hj .income-sum").html(0);
        			 }
        		 });
        	});
        },
        findConsumeList: function(){//支出列表
        	var viewSelf = this;
        	$(document).on("click","button[role='edit_biz_income'],button[role='view_biz_income']",function(e){
        		 var $this = $(this);
        		 viewSelf.model.findEcfiList({
        			 'projectId':$("#projectId").val(),
        			 'objId' : $this.data("id"),
        			 'objCode':null,
        			 'objType' :$this.closest(".panel").data("objid"),
        			 'flag':'2'
        		 },function(result){
        			 $("#out_tb_business tbody tr").not(".hj").remove();
        			 if(result.success && result.data.length > 0){
     		        	var newRow = "";
     		        	var _count = result.data.length;
     		        	var sumMoney;
     		        	for(var i =0; i< result.data.length; i++){
     		        		var date = utils.str.substringBeforeLast(result.data[i].monthOfYear,"-");
     		        		var time = "<input type='text' value='"+date+"' size='1' name='monthOfYear' class='form-control input-sm business-date-picker' data-date-format='yyyy-MM'/>";
         		        	var inputMoney = "<input type='text' value='"+result.data[i].amtMoney+"' size='1' name='amtMoney' data-culway='business' class='form-control input-sm'/>";
         		        	var inputName = "<input type='text' value='"+result.data[i].objContent+"' name='objContent' class='form-control input-sm' />";
         		        	var button = "<button type='button' role='modify_bus_ecfi' data-eid='"+result.data[i].id+"' data-type='2'  class='btn btn-xs btn-info' title='保存'><i class='ace-icon fa fa-floppy-o' ></i></button> "  
         		        					+ "<button type='button' role='delete_bus_ecfi' data-id='"+result.data[i].id+"' title='删 除' data-id='' class='btn btn-xs btn-danger' ><i class='ace-icon fa fa-times'></i></button>";
         		        	newRow +="<tr class='odd'>" +
         		        	 	 "<td>"+ (_count) +"</td>" + 
         		        		 "<td>"+ time +"</td>" + //time
				     			 "<td>"+ inputMoney +"</td>" + //name
				     			 "<td>"+ inputName +"</td>" + //money
				     			 "<td>"+ button +"</td>" +"</tr>";
         		        	_count--;
     		        	}
     		        	$("#out_tb_business tbody .hj").before(newRow);
     		        	viewSelf.initDataPickers();
     		        	//清除总计
                        viewSelf.clearSum();
     		        	viewSelf.initConsumeSummation();
        			 }else{
        				 $("#out_tb_business tbody .hj .consume-sum").html(0);
        			 }
        		 });
        	});
        },
        initIncomeSummation: function(){//初始化收入合计
        	var viewSelf = this;
        	var sum =0;
    		$("#income_tb_business tbody tr").not(".hj").each(function(){
    			var money;
    			 if($(this).children().eq(2).children().val() ==""){
    				 money = 0;
    			 }else{
    				 money = parseFloat($(this).children().eq(2).children().val());
    			 }
       			 sum+=money;
       		 });
    		$("#income_tb_business tbody .hj .income-sum").html(sum.toFixed(2));
        },
        initConsumeSummation: function(){//初始化支出合计
        	var viewSelf = this;
        	var sum =0;
    		$("#out_tb_business tbody tr").not(".hj").each(function(){
    			var money;
	   			 if($(this).children().eq(2).children().val() ==""){
	   				 money = 0;
	   			 }else{
	   				 money = parseFloat($(this).children().eq(2).children().val());
	   			 }
	      		 sum+=money;
       		 });
    		$("#out_tb_business tbody .hj .consume-sum").text(sum.toFixed(2));
        },
        incomeSummation: function(){//收入合计
        	var viewSelf = this;
        	$(document).on("change", "#income_tb_business input[name='amtMoney']",function(e){
        		$("#income_tb_business tbody .hj .income-sum").text(0);
        		viewSelf.initIncomeSummation();
        	});
        	
        },
        consumeSummation: function(){//支出合计
        	var viewSelf = this;
        	$(document).on("change", "#out_tb_business input[name='amtMoney']",function(e){
        		$("#income_tb_business tbody .hj .income-sum").text(0);
        		viewSelf.initConsumeSummation();
        	});
        },
        hideContentBusiness: function(){//隐藏工商业内容
        	var viewSelf = this;
        	$("#business-fuelux-wizard").data('operate','detail');
        	//显示关闭按钮
            $("#modal-formNBusiness_future div.modal-header button").remove();
            $("#modal-formNBusiness_future div.modal-header h4").before("<button data-dismiss='modal' class='close nfuture-close' type='button'>×</button>");
            //显示下一步的图标
            $(".btn-last-step").html("下一步 <i class='ace-icon fa fa-arrow-right icon-on-right'></i>");
            
            //隐藏保存按钮
            $("#submit-n-business_future").css("display","none");
            //显示下一步
            $("#modal-formNBusiness_future .wizard-actions .btn-next").attr("disabled",false);
            //隐藏添加收入/支出按钮
            $("#btn-addEcfIncome-business").css("display","none");
            $("#btn-addEcfConsume-business").css("display","none");
            //隐藏收入/支出操作按钮
            $("#income_tb_business thead tr th").last().css("display","none");
            $("#out_tb_business thead tr th").last().css("display","none");
            $("#income_tb_business tbody tr td,#out_tb_business tbody tr td").has('button').css("display","none");
        }
	});
	module.exports = view;
});