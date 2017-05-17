define(function(require, exports, module) {
	var model = require("./model");
	var view = Backbone.View.extend({
		el: "body",
		events: {
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
		},
		initDataTables: function() { /** 初始化DataTables */
			var viewSelf = this;
			var detailTable = $("#tbl_detail").dataTable({
				bFilter: false,
				bLengthChange:false,
				bServerSide: true,
				bAutoWidth: false,  
				sAjaxSource : $$ctx + "approval/antiFraud/findDetailBySearch",
		    	fnServerParams: function ( aoData ) {
				      aoData.push( { "name": "businessNum", "value": $("#businessNum").val()} );
				 },
				 aoColumns: [
					    	    {aTargets: [0]}, // 序号(被fnCreateRow重写)
					    	    {aTargets: [1]},
					    	    {aTargets: [2], mRender: function(data, type, rowdata) {
					    	    	if (rowdata[2] == "1") {
					    	    		return "固定规则";
					    	    	} else if (rowdata[2] == "2") {
					    	    		return "配置规则";
					    	    	} else {
					    	    		return "";
					    	    	}
						    	}},
					    	    {aTargets: [3],mRender: function(data, type, rowdata) {
					    	    	if (rowdata[3] == "1") {
					    	    		return "企业";
					    	    	} else if (rowdata[3] == "2") {
					    	    		return "个人";
					    	    	} else {
					    	    		return "";
					    	    	}
						    	}},
					    	    {aTargets: [4],mRender: function(data, type, rowdata) {
					    	    	var statusClass = "";
					    	    	var statusValue = "";
					    	    	if (rowdata[4] == "1") {
					    	    		statusClass = "badge badge-pink";
					    	    		statusValue = "强制退回";
					    	    	} else if (rowdata[4] == "2") {
					    	    		statusClass = "badge badge-warning";
					    	    		statusValue = "推荐审查";
					    	    	} else if (rowdata[4] == "3") {
					    	    		statusClass = "badge badge-success";
					    	    		statusValue = "通过";
					    	    	}
					    	    	var operation ="<span class='" + statusClass+"'>" + statusValue + "</span>"; 
					    	    	return operation;
						    	}},
						    	{aTargets: [5]}
				  ],
				fnCreatedRow: function(nRow, aData, iDataIndex) {
					 $('td:eq(0)', nRow).html(iDataIndex + 1);
				},
				fnDrawCallback:function(){
					try {
						//设置父页面iframe高度
//						$(window.parent.document
//								.getElementById('mainFrameOfProjApp'))
//								.height($("body").height())
//								.css("max-height", 'none');
					} catch (e) {
						// TODO: handle exception
					}
				}
			});
			viewSelf.detailTable = detailTable;
		}
	});
	module.exports = view;
});