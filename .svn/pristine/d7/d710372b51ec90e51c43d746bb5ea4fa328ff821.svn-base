define(function(require, exports, module) {
	var utils = require("utils");
	var view = Backbone.View.extend({
		el: "body",
		events: {
			"click #btn-customer-export": "clickBtnDownloadExcel",
			"click #customerQuery" : "formQuery",
			"click #customerReset" : "formReset"
		},
		initialize: function() { /** 初始化 */
			this.render();
		},
		render:function(){
			var viewSelf=this;
			/*utils.dd.initDataDict(["ContractStatusCode","CdsGuarantMode"], function(dataDict){
				viewSelf.initDtTable(dataDict);//初始化表格
			});*/
			this.initDataTables();//初始化表格
			this.initCustomerType();//初始化客户类型
			this.initCustomerStatus();//初始化客户状态
			this.initEmploymentType();//初始化雇佣类型
			this.initCertificateType();//初始化证件类型
		},
		initCustomerType: function(){
			var viewSelf = this;
			viewSelf.initDataDict("CustomerType",".customerType");
		},
		initCustomerStatus: function(){
			var viewSelf = this;
			viewSelf.initDataDict("CustomerStatusCode",".customerStatus");
		},
		initEmploymentType: function(){
			var viewSelf = this;
			viewSelf.initDataDict("EmploymentType",".employmentType");
		},
		initCertificateType: function(){
			var viewSelf = this;
			viewSelf.initDataDict("CertificateType",".certificateType");
		},
		initDataDict: function(type,selector){
			var viewSelf = this;
			var dataDictType;
			$("<option value='' selected>全部</option>").appendTo(selector);
			utils.dd.initDataDict([type], function(dataDict){
				if(type == "CustomerType"){
					dataDictType = dataDict.CustomerType;
				}else if(type == "CdsGuarantMode"){
					dataDictType = dataDict.CdsGuarantMode;
				}else if(type == "CommonWhether"){
					dataDictType = dataDict.CommonWhether;
				}else if(type == "EmploymentType"){
					dataDictType = dataDict.EmploymentType;
				}else if(type == "CertificateType"){
					dataDictType = dataDict.CertificateType;
				}else if(type == "CustomerStatusCode"){
					dataDictType = dataDict.CustomerStatusCode;
				}
				$.each(dataDictType,function(index,item){
					$("<option value='"+index+"'>"+ item+"</option>").appendTo(selector);
				});
			});
		},
		clickBtnDownloadExcel:function(){
			var viewSelf = this;
			if($('#customerTable tbody tr td').html()== "没有符合条件的记录"){
    			utils.alert.warn("没有查找到记录，无法导出！请重新筛选查询条件！");
        	}else{
        		var params=[];
    			if(viewSelf.oTable.serverParamCache){
    				$.each(viewSelf.oTable.serverParamCache,function(i,v){
    					params.push(v.name+"="+v.value);
    				});
    			}
        		var url=$$ctx+'customerQuery/downloadExcel';
    			if(params&&params.length>0){
    				url+=('?'+params.join('&'));
    			}
    			window.location.href = url;
        	}
		},
		downloadFile:function(url){//下载文件
			var gotoLink = document.createElement('a');
			gotoLink.style.display = 'none';
			gotoLink.href = url;
		    document.body.appendChild(gotoLink);
		    gotoLink.click();
		    document.body.removeChild(gotoLink);
		},
		initDataTables: function() {
            /** 初始化DataTables */
            var viewSelf = this;
            var status = null;
            utils.dd.initDataDict(["CustomerType", "CertificateType","CustomerStatusCode","CustomerMarkType"], function(dataDict) {
	            var oTable = $("#customerTable").dataTable({
	                "sAjaxSource": $$ctx + "singleCustomer/findCustomerQuery",
	                "bFilter": false,
	                "bLengthChange": true,
	                "aoColumns": [null, null, null, null, null, null, null, null, null, null],
	                "aoColumnDefs" : [
                        {"bVisible": false, "aTargets": [0] },
                        {"aTargets" : [1], "mRender":function(data, type, full){
    						var linkToDetail = "<a title='点击查看客户详情' href='"+$$ctx + "customerQuery/detail?partyId="+full[0]+"'>"+data+"</a>";
    						return linkToDetail;
    					}},
                        {"aTargets" : [2],"mRender" : function(data, type, full){
                            return "<div class='ellipsis' style='width:100px;' title='"+ data +"'>"+ data +"</div>";
                        }},
                        {"aTargets" : [9],"mRender" : function(data, type, full){
                        	if(data == null || data == ""){
                        		return "数据未填写";
                        	}else if(data == "01,02"){
                        		return "借款人/担保人";
                        	}else{
                        		return dataDict.CustomerMarkType[data];
                        	}
                        }},
                        {"aTargets" : [3],"mRender" : function(data, type, full){
                            return dataDict.CustomerType[data];
                        }},
                        {"aTargets" : [4],"mRender" : function(data, type, full){
                            return dataDict.CertificateType[data];
                        }},
                        {"aTargets" : [6],"mRender" : function(data, type, full){
                        	status = data;
                        	if(data == null || data==""){
                        		return "数据未填写";
                        	}else{
                        		return dataDict.CustomerStatusCode[data];
                        	}
                        }},
                    ],
                    "fnServerParams": function(aoData) {
                    	if(!this.serverParamCache){
                    		this.refreshParamCache = function(){
                            	this.serverParamCache = [{
                                    "name": "customerName",
                                    "value": $('#customerName').val()
                                },
                                {
                                    "name": "customerType",
                                    "value": $('#customerType').val()
                                },
                                {
                                    "name": "certificateType",
                                    "value": $('#certificateType').val()
                                },
                                {
                                    "name": "certificateCode",
                                    "value": $('#certificateCode').val()
                                },
                                {
                                    "name": "markType",
                                    "value": null //$('#markType').val()
                                },
                                {
                                    "name": "orgId",
                                    "value": $('#orgId').val()
                                },
                                {
                                    "name": "customerCd",
                                    "value": $('#customerCd').val()
                                },
                                {
                                    "name": "custMng",
                                    "value": $('#custMng').val()
                                },
                                {
                                    "name": "employmentType",
                                    "value": $('#employmentType').val() 
                                },
                                {
                                    "name": "customerStatus",
                                    "value": $('#customerStatus').val()
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
            });
        },
		formQuery:function(){//查询
			var viewSelf = this;
			var datatable = viewSelf.oTable;
            datatable.fnSettings()._iDisplayStart = 0;
            this.oTable.refreshParamCache();
            datatable.fnDraw();
		},
		formReset:function(){//表单重置
			var viewSelf = this;
			$("form[role='customer_searchform']").resetForm();
			var datatable = viewSelf.oTable;
            datatable.fnSettings()._iDisplayStart = 0;
            this.oTable.refreshParamCache();
            datatable.fnDraw();
		},
	});
	module.exports = view;
});