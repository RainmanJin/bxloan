define(function(require, exports, module) {
	var model = require("./model");
	var view = Backbone.View.extend({
		el: "body",
		events: {
			"click #btn-query": "clickBtnQuery"
		},
		initialize: function() { /** 初始化 */
			this.model = new model();
			this.render();
		},
		goBack: function(){
			history.go(-1);
		},
		render: function() { /** 页面渲染 */
			this.initDataTables();
			this.initFlushesBtnLive();
			this.initViewBtnLive();
			this.initBillBizViewBtnLive();
			this.initBillAccountViewBtnLive();
		},
		initDataTables: function() { /** 初始化DataTables */
			var viewSelf = this;
			var oTable = $("#tbl").dataTable({
				bFilter:false,
				bLengthChange:false,
				bSort:false,
		    	sAjaxSource: $$ctx + "accountingFlushes/findBySearch",
		    	fnServerParams:function(aoData){
		    		var $form=$("form[role='searchForm']");
		    		aoData.push({name:"customerName",value:$form.find(":text[name='customerName']").val()});
		    		aoData.push({name:"billState",value:$form.find("select[name='billState']").val()});
		    		aoData.push({name:"contCd",value:$form.find(":text[name='contCd']").val()});
		    		aoData.push({name:"custCd",value:$form.find(":text[name='custCd']").val()});
		    		//aoData.push({name:"busiTypCd",value:'001'});
		    		aoData.push({name:"busiTypCd",value:$form.find("select[name='busiTypCd']").val()});
		    	},
		    	aoColumns: [
		    	    {mData:"contCd"},
		    	    {mData:"billCd"},
		    	    {mData:"billDesc"},
		    	    {mData:"billState",mRender: function(data, type, rowdata) {
		    	    	switch (data) {
						case "047002":
							return "发送未入账";
						case "047003":
							return "已入账";
						case "047004":
							return "已退单";
						case "047005":
							return "冲销未入账";
						case "047006":
							return "冲销已入账";
						default:
							return data;
						}
		    	    }},
		    	    {mData:"busiTypNm"},
		    	    {mData:"busiDtStr"},
		    	    {mData: null, mRender: function(data, type, rowdata) {
		    	    	var operation = 
		    	    		"<div class='btn-group'style='width:108px;'>" +
		    	    			"<button title='发起冲销' role='flushes' data-id='" + rowdata.tcId + "' class='btn btn-xs btn-purple'><i class='ace-icon fa fa-reply'></i></button>" +
			    				"<button title='查看详情' role='view' data-id='" + rowdata.tcId + "' class='btn btn-xs btn-info'><i class='ace-icon fa fa-eye'></i></button>" +
			    				"<button title='查看单据业务信息' role='billBizView' data-id='" + rowdata.tcId + "' data-bill_url='" + rowdata.billUrl + "' class='btn btn-xs btn-success'><i class='ace-icon fa fa-eye'></i></button>" +
			    				"<button title='查看单据账务信息' role='billAccountView' data-billcd='" + rowdata.billCd + "' class='btn btn-xs btn-danger'><i class='ace-icon fa fa-eye'></i></button>" +
			    			"</div>";
		    	    	return operation;
		    		}}
		    	]
			});
			viewSelf.oTable = oTable;
		},
		initFlushesBtnLive:function(){
			var viewSelf = this;
			$(document).on("click", "button[role='flushes']", function(e) { // 动态绑定所有删除按钮的click事件
				var btnSelf=$(this);
				bootbox.confirm({
					message: "确认发起冲销？",
					buttons: {
						confirm: {
							label: "<i class='ace-icon fa fa-trash-o bigger-110'></i> 确定",
							className: "btn-danger btn-sm"
						},
						cancel : {
							label: "<i class='ace-icon fa fa-times bigger-110'></i> 取消",
							className: "btn-sm"
						}
					},
					callback: function(result) {
						if (result) {
							viewSelf.model.flushes(btnSelf.data("id"), function(r_data) {
								if (r_data.success) {
									viewSelf.oTable.fnDraw(); // 重新加载表格中的数据
								}else{
									bootbox.alert(r_data.data.desc);
								}
							});
						}
					}
				});
			});
		},
		initViewBtnLive:function(){
			var viewSelf = this;
			$(document).on("click", "button[role='view']", function(e) {
				var btnSelf=$(this);
				viewSelf.model.getView(btnSelf.data("id"),function(r_data){
					viewSelf.funcModal("单据详细",r_data);
				});
			});
		},
		initBillBizViewBtnLive:function(){
			var viewSelf = this;
			$(document).on("click", "button[role='billBizView']", function(e) {
				var btnSelf=$(this);
				var tcId=btnSelf.data("id");
				viewSelf.model.getBizView(btnSelf.data("id"),function(r_data){
					viewSelf.funcModal("单据业务详细",r_data);
				});
				/*var url=btnSelf.data("bill_url");
				if(url){
					//打开新标签页
					var a = $("<a href='"+url+"' target='_blank'></a>").get(0);
					a.click();
				}else{
					bootbox.alert("链接有误，无法找到页面！");
				}*/
			});
		},
		initBillAccountViewBtnLive:function(){
			var viewSelf = this;
			$(document).on("click", "button[role='billAccountView']", function(e) {
				var btnSelf=$(this);
				viewSelf.model.getTcAccountView(btnSelf.data("billcd"),function(r_data){
					viewSelf.funcModal("单据账务详细列表",r_data);
				});
			});
		},
		clickBtnQuery:function() { //查询按钮
			var viewSelf = this;
            viewSelf.oTable.fnPageChange(0);;
            return false;
        },
        funcModal:function(title,url){//打开模态窗口
        	var modal=$('#viewModal');
        	$("#viewModalLabel").text(title);
			modal.find("iframe[name=mainFrame]").attr("src",url);
			modal.modal('toggle');
        }
	});
	module.exports = view;
});