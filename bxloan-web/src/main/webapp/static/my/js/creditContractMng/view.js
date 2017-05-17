define(function(require, exports, module) {
	var model = require("./model");
	var utils = require("utils");
	var view = Backbone.View.extend({
		el : "body",
		events : {
			"click #creditSearch" : "formQuery",
			"click #contractReset" : "formReset"
		},
		initialize : function() {
			this.model = new model();
			this.render();
		},
		render : function() {
			var viewSelf = this;
			viewSelf.initCreditType();
			utils.dd.initDataDict(["CreditType","CdsGuarantMode"], function(dataDict){
				viewSelf.initCreditContractMngTable(dataDict);//初始化表格
			});
			viewSelf.initCreditContractMngButton();
		},
		formQuery : function(){//查询
			var datatable = $('#creditContractTable').dataTable();
            datatable.fnSettings()._iDisplayStart = 0;
            this.oTable.refreshParamCache();
            datatable.fnDraw();
            return false;
		},
		formReset : function(){//重置
			var viewSelf = this;
			$("#searchForm").resetForm();
			viewSelf.dt.fnPageChange(0);
		},
		initCreditType : function(){//初始化授信类型
			$("<option value='' selected>全部</option>").appendTo('.creditType');
			utils.dd.initDataDict(['CreditType'], function(dataDict){
				$.each(dataDict.CreditType,function(index,item){
					$("<option value='"+index+"'>"+ item+"</option>").appendTo('.creditType');
				});
			});
		},
		initCreditContractMngTable : function(dataDict) {//初始化表格
			var viewSelf = this;
			var oTable = $("#creditContractTable").dataTable({
				bFilter:false,
				bSort:false,
				sAjaxSource : $$ctx + "creditContractMng/creditContractList",
				aoColumns: [
				    //合同编号
					{mData: "contractNum", mRender : function(data, type, rowdata){
						var linkToDetail = "<button title='点击查看合同详情' class='btn btn-link btn-sm'>"+data+"</button>";
						return linkToDetail;
					}},
					//客户名称
					{mData: "customerName"},
					//客户编号
					{mData: "customerNum"},
					//贷款产品
					{mData: "productName"},
					//授信额度
					{mData: "contractAmt", mRender : function(data, type, rowdata){
						return utils.num.thousandsFormat(data+'');
					}},
					//授信合同期限
					{mData: "contractTerm", mRender : function(data, type, rowdata){
						if(!data){return ''}
						if(rowdata.contractTermUnit == '1'){//年
							return data + '年';
						}else if(rowdata.contractTermUnit == '2'){//月
							return data + '月';
						}else if(rowdata.contractTermUnit == '3'){//日
							return data + '日';
						}
					}},
					//授信合同利率
					{mData: "bizRate", mRender : function(data, type, rowdata){
						if(!data){return ''}
						return data*100;
					}},
					//授信类型
					{mData: "creditType", mRender : function(data, type, rowdata){
						if(!data){return ''}
						return dataDict.CreditType[data];
					}},
					//授信类型
					{mData: "contractStatusCd", mRender : function(data, type, rowdata){
						return data == '2'?'已生效':'';
					}},
					//操作
					{mData: null, mRender : function(data, type, rowdata){
						return Mustache.render($("#dt-row-operation").html(), {cid: rowdata.creditContractId,pid: rowdata.partyId});
					}}
					
				],
				fnServerParams : function(aoData) {
					if(!this.serverParamCache){
                		this.refreshParamCache = function(){
                        	this.serverParamCache = [{
                                "name": "contractNum",
                                "value": $("#searchForm :input[name='contractNum']").val()
                            },
                            {
                                "name": "customerName",
                                "value": $("#searchForm :input[name='customerName']").val()
                            },
                            {
                                "name": "creditType",
                                "value": $("#searchForm select[name='creditType']").val()
                            }];
                        };
                        
                        this.refreshParamCache();
                	}
                	$(this.serverParamCache).each(function(i,d){
                		aoData.push(d);
                	});
				}
			});
			viewSelf.oTable = oTable;
		},

		initCreditContractMngButton: function(){
	       	$(document).on("click", "button[role='apply']", function(e) { // 动态绑定所有发起新业务按钮的click事件
        		var $this = $(this);
        		var creditContractId = $this.data('cid');
        		var partyId = $this.data('pid');
				$.ajax({
	                cache: false,
	                type: "POST",
	                url: $$ctx + "bizapply/checkStatus",
	                data: {
	                	"partyId": partyId
	                },
	                async: true,
	                success: function(result) {
	                	if (result.success) {
	                		console.info(creditContractId);
	        	           window.location.href= $$ctx + "underCreditContractMng/"+partyId+"/"+creditContractId;
	        	              
						}else{
							utils.alert.warn(result.msg);
						}
	                }
	            });
                return false;
			});
		}
	});
	module.exports = view;
});