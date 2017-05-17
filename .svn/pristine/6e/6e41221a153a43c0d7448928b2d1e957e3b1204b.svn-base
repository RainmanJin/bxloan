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
			utils.dd.initDataDict(["ProductControlTypeCode"], function(dataDict){
				viewSelf.initDataTables(dataDict);
			});
			this.initButtons();
		},
		initOrg:function(){
			var viewSelf = this;
			this.model.getSelectOrg(function(data){
				if (data.success) {
					$.each(data.data,function(i,item){
						$("<option value='"+item.id+"'>"+ item.name+"</option>").appendTo("#orgId");
					});
					$('#orgId').chosen({allow_single_deselect:true}); 
					$('#orgId').next().css({'width':'200px'});
					$('#orgId').addClass('tag-input-style');
				}
			})
		},
		initDataTables : function(dataDict) {
			var viewSelf = this;
			var oTable = $("#table").dataTable({
				sAjaxSource : $$ctx + "productMng/findBySearch",
				bFilter : false,
				bLengthChange : false,
				aoColumns: [
				            {mData:"customerType",mRender:function(data, type, rowdata){
				            	var name = "";
				    	    	if(data!=null && data!=""){
				    	    		utils.dd.translateDict("CustomerTypeProduct",data,function(codeName){
				    	    			name = codeName;
				    	    		});
				    	    		return name;
				    	    	}else{
				    	    		return "";
				    	    	}}},
				            {mData:"productName"},
				    	    {mData:"departmenNames"},
				    	    {mData:"isStart",mRender:function(data, type, rowdata){
				    	    	if(data!=null && data!=""){
				    	    		return dataDict.ProductControlTypeCode[data];
				    	    	}else{
				    	    		return "";
				    	    	}
				    	    }},
				    	    {mData:null,mRender:function(data, type, rowdata){
				    	    	var html=[];
				    	    	html.push("<div class='btn-group'style='width:100px;'>");
				    	    	html.push('<button type="button" title="修改" role="edit" data-id=' + rowdata.productCd+' class="btn btn-xs btn-info"><i class="ace-icon fa fa-edit"></i></button>');
				    	    	html.push('<button type="button" title="查看" role="detail" data-id=' + rowdata.productCd+ ' class="btn btn-xs btn-yellow"><i class="ace-icon fa fa-eye"></i></button>');
				    	    	html.push("</div>");
				    	    	return html.join("");
				    	    }}],
	    	    fnServerParams : function(aoData) {
					aoData.push({
						name : "orgIds",
						value : $('#orgId').val()
					}, {
						name : "productName",
						value : $('#productName').val()
					});
				},
				fnInitComplete:function(){
					//修改
					$(document).on("click", "button[role='edit']", function(e) {
						utils.alert.warn("暂不支持修改！");
						/*window.location.href = $$ctx + "productMng/edit/"+$(this).data("id");*/
					});
					
					//查看
					$(document).on("click", "button[role='detail']", function(e) {
						window.location.href = $$ctx + "productMng/detail/"+$(this).data("id");
					});
				}
			});
			viewSelf.oTable = oTable;
			
		},
		initButtons : function() {
			var viewSelf=this;
			//新增
			$(document).on("click", "button[role='add']", function(e) {
				window.location.href = $$ctx + "productMng/add";
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