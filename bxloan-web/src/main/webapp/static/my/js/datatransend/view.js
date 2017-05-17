define(function(require, exports, module) {
	var utils = require("utils");
    var rm = require("./rm");
    var model = require("./model");
	var view = Backbone.View.extend({
		el: "body",
		events: {
			"click #btn-data-export": "clickBtnDownloadExcel",  //数据导出
			"click #btn-data-import": "dataimport",   //数据导入
		},
		//初始化
		initialize: function() {
			this.model=new model();
			this.render();
		},
		//渲染
		render: function() {
			this.initDtTable();  //初始化表格
			this.initForm();     //页面校验
			this.initInputDate();//初始化日历控件
		},
		//数据导入按钮事件
		dataimport: function() { 
			var url = $$ctx + 'dataTranSend/dataImport';
			window.location.href = url;
        },
        //初始化日历控件
		initInputDate: function() {
			$('.startTime').datepicker({
				format : 'yyyy-mm-dd',
//				todayHighlight : true,
				clearBtn : true,
				autoclose:true
			}).on("changeDate",function(ev) {
				$(".endTime").datepicker("setStartDate",ev.date?ev.date:"");
			});
			$('.endTime').datepicker({
				format : 'yyyy-mm-dd',
//				todayBtn:'linked',
				clearBtn : true,
				autoclose:true
			}).on("changeDate",function(ev) {
				$(".startTime").datepicker("setEndDate",ev.date?ev.date:"");
			});
		},
		//页面校验
		initForm: function(operate) {
			$("#dataexport_form").validate({
				rules: rm.rules,
				messages: rm.messages
			});
		},
		//数据导出
		clickBtnDownloadExcel: function() {
			var viewSelf = this;
			var $form = $("form[role='dataexport_form']");
			var startDate = $form.find(":input[name='startTime']").val();
    		var endDate = $form.find(":input[name='endTime']").val();
    		var url = $$ctx + 'dataTranSend/checkDownload';
    		var params = [];
			params.push("startDate" + "=" +startDate);
			params.push("endDate" + "=" + endDate);
			if(params && params.length > 0) {
				url += ('?'+params.join('&'));
			}
			if($("#dataexport_form").valid()) {
				$.ajax({
					type:"get", 
					url: url,
					data : params,
					success:function(result) {
						if(result.success)  {
							url = $$ctx + 'dataTranSend/downloadExcel';
							if(params && params.length > 0) {
								url += ('?'+params.join('&'));
							}
							window.location.href = url;
						}else {
							utils.alert.warn(result.msg);
						}
					}
			    });
			}
		},
		//datatable start
		initDtTable: function() {
			var viewSelf = this;
			var dt = $("#recordTable").dataTable({
				bFilter:false,
				bSort:false,
				bLengthChange:false,
				sAjaxSource: $$ctx+"dataTranSend/findOperateRecord",
				aoColumns: [
				    {mData: "startDate", mRender: function(data, type, rowdata) {
				    	if ($.trim(data) == ''){
			                return "";
			            }
			        	return utils.date.formatDate(data);
			        }},
			        {mData: "endDate", mRender: function(data, type, rowdata) {
			        	if ($.trim(data) == ''){
			                return "";
			            }
			        	return utils.date.formatDate(data);
			        }},
			        {mData: "operatorName", mRender: function(data, type, rowdata) {
			        	if ($.trim(data) == '') {
			                 return "";
			            }
			        	return data;
			        }},
			        {mData: "logOrgName", mRender: function(data, type, rowdata) {
			        	if ($.trim(data) == '') {
			                 return "";
			            }
			        	return data;
			        }},
			        {mData: "operateTime", mRender: function(data, type, rowdata) {
			        	 if ($.trim(data) == '') {
			                 return "";
			             }
			             return new Date(data).format("yyyy-MM-dd hh:mm:ss");
			        }}
			    ]
			});
			viewSelf.dt = dt;
		}
		//datatable end
	});
	module.exports = view;
});