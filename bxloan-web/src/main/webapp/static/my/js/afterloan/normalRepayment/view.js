define(function(require, exports, module) {
	var utils = require("utils");
	var NrFormModalView = require("./nrFormModalView");
	var view = Backbone.View.extend({
		el: "body",
		events: {
			'click button[role="query"]': "fnQueryOfBtn",
			'click button[role="query-reset"]': "fnQueryResetOfBtn",
			'click button[role="showProductTree"]': "toggleProductTree",
			'click button[role="clearProductCd"]': "clearProductCd",
			'click button[role="normalRepayment"]':'fnNormalRepayment'
		},
		initialize: function() { /** 初始化 */
			//this.model = new model();
			this.searchForm=$("form[role='searchForm']");
			this.nrFormModal=new NrFormModalView();
			this.render();
		},
		goBack: function(){
			history.go(-1);
		},
		render: function() { /** 页面渲染 */
			var viewSelf=this;
			this.initDateInput();
			this.initProductTree();
			utils.dd.initDataDict(["ContractSearchStatusCode"], function(dataDict){
				viewSelf.initDataTables(dataDict);
			});
		},
		initDateInput:function(){
			var viewSelf=this;
			var $form=$("form[role='searchForm']");
			var startDate=$form.find(":text[name='startDateStr']");
			var endDate=$form.find(":text[name='endDateStr']");
			//开始时间
			startDate.datepicker({clearBtn:true,autoclose:true}).on("changeDate",function(ev){
				endDate.datepicker("setStartDate",ev.date?ev.date:"");
			});
			//结束时间
			endDate.datepicker({clearBtn:true,autoclose:true}).on("changeDate",function(ev){
				startDate.datepicker("setEndDate",ev.date?ev.date:"");
			});
		},
		initDataTables: function(dataDict) { /** 初始化DataTables */
			var viewSelf = this;
			//默认结束日期(缓存)
			var defaultEndDate=$("form[role='searchForm']").find(":text[name='endDateStr']").val();
			var oTable = $("#tb_normalRepayment").dataTable({
				bFilter:false,
				bLengthChange:false,
				bSort:false,
		    	sAjaxSource: $$ctx+"afterLoan/normalRepayment/findPageData",
		    	fnServerParams:function(aoData){
		    		var dtSelf=this;
		    		//固定参数
		    		/*$form=$("form[role='searchForm']");
		    		aoData.push({name:"contractNum",value:$form.find(":text[name='contractNum']").val()});*/
		    		if(dtSelf.oSearchData){//增加查询参数
		    			$.each(dtSelf.oSearchData,function(i,v){
		    				aoData.push(v);
		    			})
		    		}else{
		    			//默认
		    			aoData.push({name:"endDateStr",value:defaultEndDate});
		    		}
		    	},
		    	aoColumns: [
		    	    {mData:"contractNum",mRender:function(data, type, rowdata){
		    	    	var html=[];
		    	    	html.push('<a title="点击查看合同详情" href="');
		    	    	var html_href=[];
		    	    	html_href.push($$ctx+'contractQuery/detail?partyId='+rowdata['partyId']);
		    	    	html_href.push('projectId='+rowdata['projectId']);
		    	    	html_href.push('contractId='+rowdata['contractId']);
		    	    	html.push(html_href.join('&'));
		    	    	html.push('">');
		    	    	html.push(data);
		    	    	html.push('</a>');
		    	    	return html.join('');
		    	    }},
		    	    {mData:"customerName",mRender:function(data, type, rowdata){
		    	    	var html=[];
		    	    	html.push('<a title="点击客户详情" href="');
		    	    	var html_href=[];
		    	    	html_href.push($$ctx+'singleCustomer/toDetail?partyId='+rowdata['partyId']);
		    	    	html.push(html_href.join('&'));
		    	    	html.push('">');
		    	    	html.push(data);
		    	    	html.push('</a>');
		    	    	return html.join('');
		    	    }},
		    	    /*{mData:"customerNum"},*/
		    	    {mData:"productName"},
		    	    {mData:"contractAmt",mRender:function(data, type, rowdata){
		    	    	return utils.number.toAmt(data);
		    	    }},
		    	    /*{mData:"currentPeriod"},*/
		    	    {mData:"currentEndDate",mRender:function(data, type, rowdata){
		    	    	return utils.date.formatDate(data);
		    	    	//return dataDict.ContractSearchStatusCode[data];
		    	    }},
		    	    {mData:"currentPrincipalInterest",mRender:function(data, type, rowdata){
		    	    	return utils.number.toAmt(data);
		    	    }},
		    	    {mData:"repayedImposeInterest",mRender:function(data, type, rowdata){
		    	    	return utils.number.toAmt(data);
		    	    }},
		    	    {mData:null,mRender: function(data, type, rowdata){
		    	    	return rowdata.repayedPeriod+"/"+rowdata.totalPeriod;
		    	    }},
		    	    {mData:"contactWay"},
		    	    {mData:null,mRender: function(data, type, rowdata){
		    	    	var html=[];
		    	    	html.push('<div class="btn-group">');
		    	    	html.push('<button title="正常还款" role="normalRepayment"');
		    	    	html.push(' data-party-id="'+rowdata.partyId+'"');
		    	    	html.push(' data-rp-id="'+rowdata.rpId+'"');
		    	    	html.push(' data-rpd-id="'+rowdata.rpdId+'"');
		    	    	html.push(' data-contract-id="'+rowdata.contractId+'"');
		    	    	html.push(' class="btn btn-xs btn-purple"><i class="ace-icon fa fa-eraser"></i></button>');
		    	    	html.push('</div>');
		    	    	return html.join('');
		    	    }}
		    	]
			});
			viewSelf.oTable = oTable;
		},
		fnQueryOfBtn:function(){
			var viewSelf=this;//查询参数
			var seachData=[];
			var $form=$("form[role='searchForm']");
			seachData.push({name:"customerName",value:$form.find(":text[name='customerName']").val()});
			seachData.push({name:"customerNum",value:$form.find(":text[name='customerNum']").val()});
			seachData.push({name:"contractNum",value:$form.find(":text[name='contractNum']").val()});
			seachData.push({name:"productCd",value:$form.find(":hidden[name='productCd']").val()});
    		var startDate=$form.find(":text[name='startDateStr']").val();
    		var endDate=$form.find(":text[name='endDateStr']").val();
    		if(startDate){
    			seachData.push({name:"startDateStr",value:startDate});
    		}
    		if(endDate){
    			seachData.push({name:"endDateStr",value:endDate});
    		}
			viewSelf.oTable.oSearchData=seachData;
			viewSelf.oTable.fnPageChange(0);//执行查询
		},
		fnQueryResetOfBtn:function(){//表单重置
			var viewSelf = this;
			viewSelf.oTable.oSearchData=null;
			viewSelf.oTable.fnPageChange(0);//执行查询
		},
		fnNormalRepayment:function(e){//正常还款
			var btnSelf=$(e.currentTarget);
			var contractId=btnSelf.data('contract-id');
			var partyId=btnSelf.data('party-id');
			var rpId=btnSelf.data('rp-id');
			var rpdId=btnSelf.data('rpd-id');
			//utils.alert.warn("正常还款 contractId:"+contractId+'rpdId:'+rpdId);
			this.nrFormModal.loadRepaymentInfo(contractId,partyId,rpId,rpdId);
			$("#input_repay_modal").modal('show');
		},
		initProductTree:function(){
			var viewSelf=this;
			var $form=$("form[role='searchForm']");
			var $productName=$form.find(':text[role="productName"]');
			var $productCd=$form.find(':hidden[name="productCd"]');
			$.fn.zTree.init($("#productTree"), {
                async: {
                    enable: true,
                    url: $$ctx + "bizapply/findEffectiveProduct?isDesignated=false"
                },
                data: {
                    simpleData: {
                        enable: true,
                        idKey: "productCd",
                        pIdKey: "parentProductCd"
                    },
                    key: {
                        name: "productName"
                    }
                },
                check: {
                    enable: true,
                    chkStyle: "radio",
                    radioType: "all"
                },
                callback: {
                    onClick: function(event, treeId, treeNode) {
                    	if(treeNode!=null&&treeNode.children!=null&&treeNode.children.length!=null&&treeNode.children.length>0){
                    		return false;
                    	}else{
                    		var productCd = treeNode.productCd;
                        	var treeObj = $.fn.zTree.getZTreeObj(treeId);
                        	var node = treeObj.getNodeByParam("productCd", productCd, null);
                        	treeObj.checkNode(node, true, true);
                        	var parentNode = node.getParentNode();
                            treeObj.expandNode(parentNode, true, false);
                            $productName.val(treeNode.productName);
                            $productCd.val(productCd);
                    	}
                    	viewSelf.toggleProductTree();
                    },
                    onCheck: function(event, treeId, treeNode) {
                    	console.log(treeNode);
                    	$productName.val(treeNode.productName);
                    	$productCd.val(treeNode.productCd);
                    },
                    beforeCheck: function(treeId, treeNode) {
                        return ! treeNode.isParent;
                    }
                }
            });
		},
		toggleProductTree:function(){//显示产品树
			var $productTreeDiv=$("#productTreeDiv");
			$productTreeDiv.toggle();
		},
		clearProductCd:function(){//重置产品搜索项
			var viewSelf=this;
			viewSelf.searchForm.find(':hidden[name="productCd"]').val('');
			viewSelf.searchForm.find(':text[role="productName"]').val('');
		}
	});
	module.exports = view;
});