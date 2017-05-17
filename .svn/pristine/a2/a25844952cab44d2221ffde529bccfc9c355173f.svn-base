define(function(require, exports, module) {
	
	var model = require("./model");
	var rm = require("./rm");
	var utils = require("utils");
	var formFunc = {};
	var getHidden = function(hiddenName){
		return $("#hiddenField").find("input[name='"+ hiddenName +"']").val();
	}
	var view = Backbone.View.extend({
		el : "body",
		events : {
			"click #submit_Ecf_simple": "addDetail",
			"click #btn-expectCash": "addEcf",
			"click #btn-addEcfIncome" : "addIncome",
			"click #btn-addEcfConsume" : "addConsume"
		},
		initialize : function() {
			this.model = new model();
			this.render();
		},
		render : function() {
			this.initFrist();
			this.initExpectFlowTable();
			this.initExpectFlowDetailIncomeTable();
			this.initExpectFlowDetailConsumeTable();
			this.initSimpleEcfForm();
			this.editEcfBtnLive();
			this.deleteEcfBtnLive();
			this.cancelRowBtnLive();
			this.saveRowBtnLive();
			this.deleteRowBtnLive();
			this.editRowBtnLive();
		},
		initFrist: function(){
			var viewSelf = this;
			$("#simple_ecfForm_nd input.required").closest("div.form-group").find("label").prepend("<font color='red'>*</font>");
			formFunc = {
				addRow: function(btn, type){
					$("#modal-formEcf").find("input[name='id']").val("");
		        	var inputName = "<input type='text' name='name' class='form-control input-sm' />";
		        	var inputMoney = "<input type='text' name='money' class='form-control input-sm'/>";
		        	var button = "<button type='button' role='save_ecfd' data-type='"+ type +"'  class='btn btn-xs btn-info' title='保存'><i class='ace-icon fa fa-floppy-o' ></i></button> "  + "<button type='button' role='cancel_ecfd'  class='btn btn-xs btn-danger' title='取消'><i class='ace-icon fa fa-times'></i></button>";
		        	var newRow = "<tr class='odd'>" +
				     			 "<td>"+ inputName +"</td>" + //name
				     			 "<td>"+ inputMoney +"</td>" + //money
				     			 "<td>"+ button +"</td>" +
				     			 "</tr>";
		        	$(btn).closest("div.widget-box").find("tbody tr:last").before(newRow);
				},
				editRow: function(btn){
					var $this = $(btn);
	            	var tr = $this.closest("tr");
	            	$("#modal-formEcf").find("input[name='id']").val($this.data('id'));
	            	var text_money = $(tr).find("td:eq(1)").text();
	            	if($this.data('type') == "2"){//如果是支出
	            		text_money = text_money.substring(1);
	            	}
	            	var inputName = "<input type='text' name='name' class='form-control input-sm' value='"+ $(tr).find("td:eq(0)").text() +"'/>";
	            	var inputMoney = "<input type='text' name='money' class='form-control input-sm' value='"+ text_money +"'/>";
	            	var button = "<button type='button' role='save_ecfd' data-type='"+ $this.data('type') +"'  class='btn btn-xs btn-info' title='保存'><i class='ace-icon fa fa-floppy-o' ></i></button> "  + "<button type='button' role='cancel_ecfd'  class='btn btn-xs btn-danger' title='取消'><i class='ace-icon fa fa-times'></i></button>";
	            	var newRow = "<tr class='odd'>" +
	    		     			 "<td>"+ inputName +"</td>" + //name
	    		     			 "<td>"+ inputMoney +"</td>" + //money
	    		     			 "<td>"+ button +"</td>" +
	    		     			 "</tr>";
	    			$(tr)[0].innerHTML = newRow;
				},
				freshCount: function(){
					viewSelf.initCalculateEcf($("#modal-formEcf input[name='expectCashFlowId']").val());
				}
			}
		},
		initExpectFlowTable: function(){
			 var viewSelf = this;
			 utils.dd.initDataDict(["DegreeCode"],
			            function(dataDict) {
			                var oTable = $("#cashFlowTb_nd").dataTable({
			                    "sAjaxSource": $$ctx + "expectCashFlow/findBySearch",
			                    "bFilter": false,
			                    "bAutoWidth": true,
			                    "bLengthChange": false,
			                    "aoColumns": [{
			                        "bVisible": false,
			                        "mData": "projectId"
			                    },
			                    {
			                        "mData": "month"
			                    },
			                    { 
			                        "mData": "familyCost"/*,
			                        mRender: function(data, type, rowdata) {
			                        	if(data){
			                        		return dataDict.WdCorrelativeCustTypeCd[data];
			                        	}
			                        	return "-";
			                        }*/
			                    },
			                    {
			                        "mData": "otherLoanRepayment"
			                    },
			                    {
			                        "mData": "costTotal"
			                      
			                    },
			                    {
			                        "mData": "balance"
			                    },
			                    {
			                        "mData": "balanceBeforeLoan"
			                    },
			                    {
			                        "mData": "income"
			                    },
			                    {
			                        "mData": "cost"
			                    },
			                    {
			                        "mData": "balanceAfterLoan"
			                    },
			                    {
			                        "mData": "id",
			                        mRender: function(data, type, rowdata) {
			                                var buttons = "<button type='button' role='edit_ecf' data-id='" + rowdata.id + "' class='btn btn-xs btn-info' title='修改'><i class='ace-icon fa fa-edit' ></i></button> "  + "<button type='button' role='delete_ecf' data-id='" + rowdata.id + "' class='btn btn-xs btn-danger' title='删除'><i class='ace-icon fa fa-trash-o'></i></button>";
			                                return buttons
			                        }
			                    }],
			                    "fnServerParams": function(aoData) {
			                        aoData.push({
			                            "name": "projectId",
			                            "value": getHidden("projectId")
			                        });
			                    }
			                });
			                viewSelf.oTable = oTable;
			            });
		},
		initExpectFlowDetailIncomeTable: function(){
			 var viewSelf = this;
			 utils.dd.initDataDict(["DegreeCode"],
			            function(dataDict) {
			                var oTable = $("#cashFlowDetail_income_tb").dataTable({
			                    "sAjaxSource": $$ctx + "expectCashFlow/findDetailsBySearch",
			                    "bFilter": false,
			                    "bPaginate": false,
			                    "bAutoWidth": true,
			                    "bLengthChange": false,
			                    "aoColumns": [{
			                        "bVisible": false,
			                        "mData": "id"
			                    },
			                    {
			                        "mData": "name"
			                    },
			                    {
			                        "mData": "money"
			                    },
			                    {
			                        "mData": "id",
			                        mRender: function(data, type, rowdata) {
			                        	if(!rowdata.count){
			                                var buttons = "<button type='button' role='edit_ecfd' data-type='"+ rowdata.type +"' data-id='" + rowdata.id + "' class='btn btn-xs btn-info' title='修改'><i class='ace-icon fa fa-edit' ></i></button> "  + "<button type='button' role='delete_ecfd' data-id='" + rowdata.id + "' data-type='"+ rowdata.type +"' class='btn btn-xs btn-danger' title='删除'><i class='ace-icon fa fa-trash-o'></i></button>";
			                                return buttons;
			                        	}else{
			                        		return "单位：元";
			                        	}
			                        }
			                    }],
			                    "fnServerParams": function(aoData) {
			                        aoData.push({
			                            "name": "expectCashFlowId",
			                            "value": $("#modal-formEcf input[name='expectCashFlowId']").val()
			                        });
			                        aoData.push({
			                            "name": "ecfType",
			                            "value": "1" //收入类
			                        });
			                    }
			                });
			                viewSelf.oTable = oTable;
			            });
		},
		initExpectFlowDetailConsumeTable: function(){
			var viewSelf = this;
			 utils.dd.initDataDict(["DegreeCode"],
			            function(dataDict) {
			                var oTable = $("#cashFlowDetail_consume_tb").dataTable({
			                    "sAjaxSource": $$ctx + "expectCashFlow/findDetailsBySearch",
			                    "bFilter": false,
			                    "bPaginate": false,
			                    "bAutoWidth": true,
			                    "bLengthChange": false,
			                    "aoColumns": [{
			                        "bVisible": false,
			                        "mData": "id"
			                    },
			                    {
			                        "mData": "name"
			                    },
			                    {
			                        "mData": "money",
			                        mRender: function(data, type, rowdata) {
			                        	return (data+"").replace("-","<i class='ionicons ion-android-arrow-dropleft'></i>");
			                        }
			                    },
			                    {
			                        "mData": "id",
			                        mRender: function(data, type, rowdata) {
			                        	if(!rowdata.count){
			                                var buttons = "<button type='button' role='edit_ecfd' data-id='" + rowdata.id + "' data-type='"+ rowdata.type +"' class='btn btn-xs btn-info' title='修改'><i class='ace-icon fa fa-edit' ></i></button> "  + "<button type='button' role='delete_ecfd' data-id='" + rowdata.id + "' data-type='"+ rowdata.type +"' class='btn btn-xs btn-danger' title='删除'><i class='ace-icon fa fa-trash-o'></i></button>";
			                                return buttons;
			                        	}else{
			                        		return "单位：元";
			                        	}
			                        }
			                    }],
			                    "fnServerParams": function(aoData) {
			                        aoData.push({
			                            "name": "expectCashFlowId",
			                            "value": $("#modal-formEcf input[name='expectCashFlowId']").val()
			                        });
			                        aoData.push({
			                            "name": "ecfType",
			                            "value": "2" //支出类
			                        });
			                    }
			                });
			                viewSelf.oTable = oTable;
			            });
		},
		initSimpleEcfForm: function(){
			$("#simple_ecfForm_nd").validate({
                rules: rm.rules,
                messages: rm.messages,
                errorPlacement: function(error, element) {
                    if (element.is(":radio")){error.appendTo(element.parent());}
                    else if (element.is(":checkbox")){error.appendTo(element.parent().parent().parent().next());}
                    else if (element.next().is("span[class='input-group-addon']")) {
                    	error.appendTo(element.parent().parent().parent());
                    }
                    else {
                    	error.appendTo(element.parent());
                    } 
                }
            });
		},
		addEcf: function(){
			$("#showAddEcfDiv").fadeIn();
			$("#simple_ecfForm_nd ").resetForm();
			$("#simple_ecfForm_nd input[name='id'] ").val("");
		},
		addDetail: function(){
			var viewSelf = this;
			if ($("#simple_ecfForm_nd").valid()) {
            	utils.button.ban("#submit_Ecf_simple");
            	$.ajax({
                    cache: false,
                    type: "POST",
                    url: $$ctx + "expectCashFlow/saveSimpleEcf?projectId=" + getHidden("projectId"),
                    data: $('#simple_ecfForm_nd').serialize(),
                    async: false,
                    error: function(request) {
                        utils.alert.err("错误"+ request.status);
                    },
                    success: function(data) {
                    	utils.button.reset("#submit_Ecf_simple");
                    	if(data){
                    		if(data.success){
                    			$("#cashFlowTb_nd").dataTable().fnDraw();
                    			
                    			$("#ecfForm_nd").resetForm();
                    			$("#modal-formEcf input[name='expectCashFlowId']").val(data.data)
                    			
                    			$("#cashFlowDetail_income_tb").dataTable().fnDraw();
                    			$("#cashFlowDetail_consume_tb").dataTable().fnDraw();
                    			viewSelf.initCalculateEcf(data.data);
                    			
                    			$('#showAddEcfDiv').fadeOut();//关闭
                    		}
                    		else{
                    			utils.alert.warn("<strong>"+ data.msg +"</strong>");
                    		}
                    	}
                    }
                }); //ajax end
            }
		},
		initCalculateEcf: function(ecfId){
			var viewSelf = this;
			viewSelf.model.findCalculateEcf({
				"ecfId" : ecfId
			}, function(r){
				if(r&&r.success){
					 $.each($("#caculateResultDiv").find("button span[name]"), function() {
                         if(r.data[$(this).attr("name")]){
                        	 if(r.data[$(this).attr("name")]>=0){
                        		 $(this).text(r.data[$(this).attr("name")]);
                        	 }else{
                        		 $(this).text(r.data[$(this).attr("name")]);
                        	 }
                         }else{
                        	 $(this).text(0);
                         }
                     });
					$("#modal-formEcf div.modal-header h4").html("<i class='ace-icon fa fa-plus'></i> 管理现金流量");
                    $("#modal-formEcf").modal("show");
				}else{
					utils.alert.err("<strong>"+ r.msg +"</strong>");
				}
			});
		},
		editEcfBtnLive: function(){
			 var viewSelf = this;
             $(document).on("click", "button[role='edit_ecf']", function(e){
            	 var $this = $(this); 
            	 $.ajax({
                     cache: true,
                     type: "POST",
                     url: $$ctx + "expectCashFlow/findSimpleEcf",
                     data: {
                         "ecfId": $this.data("id")
                     },
                     async: true,
                     error: function(request) {
                         alert("错误" + request.status);
                     },
                     success: function(data) {
                         if (data&&data.success) {
                        		 $.each($("#simple_ecfForm_nd").find("input, select"),
                        				 function() {
                        			 $(this).val(data.data[$(this).attr("name")]);
                        		 });
                                 $("#showAddEcfDiv").fadeIn();
                         } else {
                         	utils.alert.err("<strong>"+ data.msg +"</strong>");
                         }
                     }
                 });
             });
		},
		deleteEcfBtnLive: function(){
			var viewSelf = this;
            $(document).on("click", "button[role='delete_ecf']",
            function(e) { // 动态绑定所有删除按钮的click事件
                var $this = $(this);
                if (bootbox.confirm({
                    message: "确定要删除此条数据吗 ?",
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
                            viewSelf.model.deleteEcf(id, function(data){
                            	if (data.success) {
                                    //bootbox.alert(_success + "<strong>删除成功!</strong>",function() {});
                                    var datatable = utils.datatable.fresh("#cashFlowTb_nd");
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
        addIncome: function(){
        	formFunc.addRow("#btn-addEcfIncome", 1);//收入
        },
        addConsume: function(){
        	formFunc.addRow("#btn-addEcfConsume", 2);//支出
        },
        editRowBtnLive: function(){
        	var viewSelf = this;
            $(document).on("click", "button[role='edit_ecfd']", function(e){
            	formFunc.editRow(this);
            });
		},
		deleteRowBtnLive: function(){
			var viewSelf = this;
            $(document).on("click", "button[role='delete_ecfd']",
            function(e) { // 动态绑定所有删除按钮的click事件
                var $this = $(this);
                if (bootbox.confirm({
                    message: "确定要删除此条数据吗 ?",
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
                            viewSelf.model.deleteEcfd(id, function(data){
                            	if (data.success) {
                                    //bootbox.alert(_success + "<strong>删除成功!</strong>",function() {});
                                    $this.closest("table").dataTable().fnDraw();
                                    formFunc.freshCount();
                                    $("#cashFlowTb_nd").dataTable().fnDraw();
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
            $(document).on("click", "button[role='cancel_ecfd']", function(e){
            	var $this = $(this);
            	var id = $("#modal-formEcf").find("input[name='id']").val();
            	if(!id){
            		$(this).closest("tr").remove();
            	}else{
            		$this.closest("table").dataTable().fnDraw();
            	}
            });
		},
		saveRowBtnLive: function(){
			var viewSelf = this;
            $(document).on("click", "button[role='save_ecfd']", function(e){
            	var $this = $(this);
            	var tr = $this.closest("tr");
            	
            	var validate = function(){
            		 
            		var name =  $(tr).find("input:eq(0)").val();
            		var money = $(tr).find("input:eq(1)").val();
            		if(!(name&&money)){
            			utils.alert.warn("名称与金额均为必填项");
        				return false;
            		}else if(!(/^[1-9]\d*$/.test(money) || /^\d+(\.\d{1,2})?$/.test(money))){
        				utils.alert.warn("金额请输入数字，允许两位小数");
        				return false;
        			}
        			return true;
            	}
            	
            	if(!validate()){
            		return false;
            	}
            	
            	viewSelf.model.saveEcfDetail({
            		"id" : $("#modal-formEcf").find("input[name='id']").val(),
            		"expectCashFlowId" : $("#modal-formEcf").find("input[name='expectCashFlowId']").val(),
            		"name" : $(tr).find("input[name='name']").val(),
            		"money" : $(tr).find("input[name='money']").val(),
            		"type" : $this.data('type')
            	}, function(r){
            		if(r&&r.success){
            			$this.closest("table").dataTable().fnDraw();
            			formFunc.freshCount();
            			$("#cashFlowTb_nd").dataTable().fnDraw();
            		}else{
            			utils.alert.warn("保存失败！");
            		}
            	});
            });
		}
        
	});
	module.exports = view;
});