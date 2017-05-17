define(function(require, exports, module) {
	var aData = undefined;
	var model = require("./model");
	var utils = require("utils");
	var view = Backbone.View.extend({
		el : "body",
		events : {
			"click #search" : "search",
			"click #reset" : "reset"
		},
		initialize : function() {
			this.model = new model();
			this.render();
		},
		render : function() {
			var viewSelf=this;
			utils.dd.initDataDict(["ResvAssetTypeCode","RevGuaranteeStatusCd"], function(dataDict){
				viewSelf.initDataTables(dataDict);
			});
			this.initButtons();
		},
		initDataTables : function(dataDict) {
			var viewSelf = this;
			var oTable = $("#table").dataTable({
				sAjaxSource : $$ctx + "collateral/findBySearch",
				bFilter : false,
				bLengthChange : false,
				aoColumns: [
			            {mData:"guarantyNum"},
			    	    {mData:"guarantorName"},
			    	    {mData:"guarantyName"},
			    	    {mData:"guarantyTypeCd",mRender:function(data, type, rowdata){
			    	    	if(!data){
			    	    		return "";
			    	    	}
			    	    	return dataDict.ResvAssetTypeCode[data];
			    	    }},
			    	    {mData:"evalValue"},
			    	    {mData:"setGuarantyAmt"},
			    	    {mData:"guarantyStatusCd",mRender:function(data, type, rowdata){
			    	    	return dataDict.RevGuaranteeStatusCd[data];
			    	    }},
			    	    {mData:null,mRender:function(data, type, rowdata){
			    	    	var html=[];
			    	    	html.push("<div class='btn-group'style='width:100px;'>");
			    	    	html.push('<button type="button" title="修改" role="edit" data-id=' + rowdata.guarantyId+' data-status=' + rowdata.guarantyStatusCd+ ' class="btn btn-xs btn-info"><i class="ace-icon fa fa-edit"></i></button>');
			    	    	html.push('<button type="button" title="查看" role="detail" data-id=' + rowdata.guarantyId+ ' class="btn btn-xs btn-yellow"><i class="ace-icon fa fa-eye"></i></button>');
			    	    	html.push('<button type="button" title="删除" role="delete" data-id=' + rowdata.guarantyId+ ' class="btn btn-xs btn-danger"><i class="ace-icon fa fa-trash-o"></i></button>');
			    	    	html.push("</div>");
			    	    	return html.join("");
			    	    }}],
				fnServerParams : function(aoData) {
					aoData.push({
						name : "guarantorName",
						value : $('#guarantorName').val()
					}, {
						name : "guarantyName",
						value : $('#guarantyName').val()
					}, {
						name : "guarantyStatusCd",
						value : $('#guarantyStatusCd').val()
					});
				}
			});
			viewSelf.oTable = oTable;
			
		},
		initButtons : function() {
			var viewSelf=this;
			//新增
			$(document).on("click", "button[role='add']", function(e) {
				window.location.href = $$ctx + 'collateral/add';
			});
			
			//修改
			$(document).on("click", "button[role='edit']", function(e) {
				var btnSelf=$(this);
				
				var status=btnSelf.data("status");
				if(status&&status=='2'){
					utils.alert.warn("<strong>该抵质押物已关联，不允许进行修改。</strong>");
					return ;
				}else{
					window.location.href = $$ctx + 'collateral/edit/' + $(this).data("id");
				}
			});
			
			//查看
			$(document).on("click", "button[role='detail']", function(e) {
				window.location.href = $$ctx + 'collateral/detail/' + $(this).data("id");
			});
			
			//删除
			$(document).on("click", "button[role='delete']", function(e) {
				var btnSelf = $(this);
				bootbox.confirm({
					message: "您确定要删除此记录吗？",
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
							viewSelf.model.delById(btnSelf.data("id"),function(r_data){
								if (r_data.success) {
									utils.alert.suc("<strong>" + r_data.msg + "</strong>");
									viewSelf.oTable.fnDraw();
								} else {
									utils.alert.err("<strong>" + r_data.msg + "</strong>");
								}
							});
						}
					}
				});
			});
		},
		search : function() {
			var viewSelf=this;
			viewSelf.oTable.fnSettings()._iDisplayStart = 0;
			viewSelf.oTable.fnDraw();
		},
		reset : function() {
			var viewSelf=this;
			$('form input').val('');
			$('form select').val('all');
			var table = $("#table").dataTable();
			viewSelf.oTable.fnSettings()._iDisplayStart = 0;
			viewSelf.oTable.fnDraw();
		}
	});
	module.exports = view;
});