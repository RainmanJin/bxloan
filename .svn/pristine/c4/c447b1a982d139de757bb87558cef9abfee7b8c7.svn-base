define(function(require, exports, module) {
	var utils = require("utils");
	var view = Backbone.View.extend({
		el: "body",
		events: {
			"click #btn-query": "clickBtnQuery",
			"click #btn-reset": "clickBtnReset",
			"click #btn-download": "clickBtnDownloadExcel"
		},
		initialize: function() { /** 初始化 */
			//this.model = new model();
			this.render();
		},
		render:function(){
			this.initDtTable();
			this.initInputDate();
			this.initBindEvent();
		},
		initDtTable:function(){
			var bizTypes={
					"1":"普通业务",
					"2":"授信业务"
			}
			//绑定dataTable
			var dtTable=$("#tbl").dataTable({
				bFilter:false,
				bLengthChange:false,
				bSort:false,
				sAjaxSource: $$ctx + "bizApproveAccount/findBySearch",
		    	fnServerParams:function(aoData){
		    		if(this.aoQueryParams){
		    			Array.prototype.push.apply(aoData, this.aoQueryParams);//合并参数
		    		}
		    	},
		    	aoColumns: [
		    	    {mData:"projectNo"},
		    	    {mData:"customerName"},
		    	    {mData:"bizType", mRender: function(data, type, rowdata) {
		    	    	var bizType=rowdata.bizType;
		    	    	if(!bizType){
		    	    		bizType='1';
		    	    	}
		    	    	return bizTypes[bizType];
		    	    }},
		    	    {mData:"productTypeName"},
		    	    {mData:"productName"},
		    	    {mData:"applyAmt"},
		    	    {mData:"createTimeStr"},
		    	    {mData:"customerManagerName"},
		    	    {mData:"orgDesc"}
		    	]
			});
			this.oTable=dtTable;
		},
		initInputDate:function(){
			$(".input-datepicker").datepicker({format : 'yyyy-mm-dd',todayHighlight : true,autoclose:true,
				clearBtn : true}).on("click",function(){
			}).on("show",function(){
				$(".datepicker").css("z-index","99999");
			});
		},
		initBindEvent:function(){
			$(".chosen-select2").addClass('tag-input-style').chosen({allow_single_deselect:true,width:"100px"}).change(function(){
				var $this=$(this);
				var orgIds=$this.val();
				var all='all';//全部
				if(orgIds){
					var index=orgIds.indexOf(all);
					if(index>=0&&orgIds.length>1){
						orgIds.splice(index, 1);
						$this.val(orgIds);
						$(this).trigger("chosen:updated");
					}
				}else{
					$this.val([all]);
					$(this).trigger("chosen:updated");
				}
			});
		},
		clickBtnDownloadExcel:function(){
			var viewSelf = this;
			var params=[];
			if(viewSelf.oTable.aoQueryParams){
				$.each(viewSelf.oTable.aoQueryParams,function(i,v){
					params.push(v.name+"="+v.value);
				});
			}
			var url=$$ctx+'bizApproveAccount/downloadExcel';
			if(params&&params.length>0){
				url+=('?'+params.join('&'));
			}
			this.downloadFile(url);
		},
		clickBtnQuery:function(){//查询
			var viewSelf = this;
			var $form =$("form[role='searchForm']");
			var startDate=$form.find(":text[name='startDate']").val();
			var endDate=$form.find(":text[name='endDate']").val();
			if(startDate&&endDate&&startDate>endDate){
				utils.alert.err("开始时间要小于结束时间");
				return false;
			}
			var params=[];//搜索条件参数格式
			params.push({name:'startDate',value:startDate});
			params.push({name:'endDate',value:endDate});
			var orgIds=$form.find("select[name='orgIds']").val();
			if(orgIds){
				var index=orgIds.indexOf('all');
				if(index>=0){
					orgIds.splice(index, 1);
				}else{
					params.push({name:'orgIds',value:orgIds.join(',')});
				}
			}
			viewSelf.oTable.aoQueryParams=params;//缓存搜索条件
            viewSelf.oTable.fnPageChange(0);//执行查询
            return false;
		},
		clickBtnReset:function(){//重置
			var viewSelf = this;
			var $form =$("form[role='searchForm']");
			$form.find(":text[name='startDate']").val('');
			$form.find(":text[name='endDate']").val('');
			$form.find("select[name='orgIds']").val('all').trigger("chosen:updated");
			viewSelf.oTable.aoQueryParams=null;
            viewSelf.oTable.fnPageChange(0);
            return false;
		},
		downloadFile:function(url){//下载文件
			var gotoLink = document.createElement('a');
			gotoLink.style.display = 'none';
			gotoLink.href = url;
		    document.body.appendChild(gotoLink);
		    gotoLink.click();
		    document.body.removeChild(gotoLink);
		}
	});
	module.exports = view;
});