define(function(require, exports, module) {
	var utils = require("utils");
	var view = Backbone.View.extend({
		el: "body",
		events: {
			"click #btn-contract-export": "clickBtnDownloadExcel",
			"click #contractQuery" : "formQuery",
			"click #contractReset" : "formReset"
		},
		initialize: function() { /** 初始化 */
			this.render();
		},
		render:function(){
			var viewSelf=this;
			utils.dd.initDataDict(["ContractStatusCode","CdsGuarantMode"], function(dataDict){
				viewSelf.initDtTable(dataDict);//初始化表格
			});
			this.initInputDate();//初始化日历控件
			this.initCustomerType();//初始化客户类型
			this.initCdsGuarantMode();//初始化担保方式
			this.initContractStatusCode();//初始化合同状态
			this.initCommonWhether();//初始化是否有保险
		},
		initCustomerType: function(){
			var viewSelf = this;
			viewSelf.initDataDict("CustomerType",".customerType");
		},
		initCdsGuarantMode: function(){
			var viewSelf = this;
			viewSelf.initDataDict("CdsGuarantMode",".cdsGuarantMode");
		},
		initContractStatusCode: function(){
			var viewSelf = this;
			$("<option value='' selected>全部</option>").appendTo('.contStatus');
			utils.dd.initDataDict(['ContractStatusCode'], function(dataDict){
				$.each(dataDict.ContractStatusCode,function(index,item){
					if(index != '331' && index != '425' && index != '500' && index != '521'){
						$("<option value='"+index+"'>"+ item+"</option>").appendTo('.contStatus');
					}
				});
			});
		},
		initCommonWhether: function(){
			var viewSelf = this;
			viewSelf.initDataDict("CommonWhether",".isInsure");
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
				}
				$.each(dataDictType,function(index,item){
					$("<option value='"+index+"'>"+ item+"</option>").appendTo(selector);
				});
			});
		},
		initInputDate: function(){
			$('.loanStartTime').datepicker({
				format : 'yyyy-mm-dd',
				todayHighlight : true,
				autoclose:true
			}).on("changeDate",function(ev){
				$(".loanEndTime").datepicker("setStartDate",ev.date?ev.date:"");
			});
			
			$('.loanEndTime').datepicker({
				format : 'yyyy-mm-dd',
				todayBtn:'linked',
				autoclose:true
			}).on("changeDate",function(ev){
				$(".loanStartTime").datepicker("setEndDate",ev.date?ev.date:"");
			});
		},
		clickBtnDownloadExcel:function(){
			var viewSelf = this;
			//var table = $('#contractTable').DataTable();
			//alert(table.column(0).data().length);
			//return;
			var params=[];
			if(viewSelf.aoQueryParams){
				$.each(viewSelf.aoQueryParams,function(i,v){
					params.push(v.name+"="+v.value);
				});
			}
			var url=$$ctx+'contractQuery/checkDownload';
			if(params&&params.length>0){
				url+=('?'+params.join('&'));
			}
			$.ajax({
				type:"get",
				url: url,
				data : params,
				success:function(result){
					if(result.success){
						url=$$ctx+'contractQuery/downloadExcel';
						if(params&&params.length>0){
							url+=('?'+params.join('&'));
						}
						window.location.href = url;
					}else{
						utils.alert.warn(result.msg);
					}
					/*if (result == "") {
						url=$$ctx+'contractQuery/downloadExcel';
						if(params&&params.length>0){
							url+=('?'+params.join('&'));
						}
						window.location.href = url;
					} else {
						//bootbox.alert("<span style='font-size:16px;'>"+result+"</span>");
						utils.alert.warn(result);
					}*/
				}
			});
			//this.downloadFile(url);
			//window.location.href = url;
		},
		downloadFile:function(url){//下载文件
			var gotoLink = document.createElement('a');
			gotoLink.style.display = 'none';
			gotoLink.href = url;
		    document.body.appendChild(gotoLink);
		    gotoLink.click();
		    document.body.removeChild(gotoLink);
		},
		initDtTable: function(dataDict){
			var viewSelf = this;
			var dt = $("#contractTable").dataTable({
				bFilter:false,
				bSort:false,
				sAjaxSource: $$ctx+"contractQuery/findContactList",
				fnServerParams: function(aoData){
					var $form=$("form[role='contract_searchform']");
		    		aoData.push({name:"orgId",value:$form.find("select[name='orgId']").val()});
		    		aoData.push({name:"customerName",value:$.trim($form.find("input[name='customerName']").val())});
		    		aoData.push({name:"customerType",value:$form.find(":input[name='customerType']").val()});
		    		aoData.push({name:"customerNum",value:$.trim($form.find(":input[name='customerCd']").val())});
		    		aoData.push({name:"customerMng",value:$.trim($form.find(":input[name='custMng']").val())});
		    		aoData.push({name:"contractNum",value:$.trim($form.find(":input[name='contNum']").val())});
		    		aoData.push({name:"contractTerm",value:$.trim($form.find(":input[name='contTerm']").val())});
		    		aoData.push({name:"contractTermUnit",value:$form.find("select[name='contTermUnit']").val()});
		    		aoData.push({name:"contractStatus",value:$form.find("select[name='contStatus']").val()});
		    		aoData.push({name:"cdsGuarantMode",value:$form.find("select[name='cdsGuarantMode']").val()});
		    		aoData.push({name:"isInsure",value:$form.find("select[name='isInsure']").val()});
		    		aoData.push({name:"isHeadcol",value:$form.find("select[name='isHeadcol']").val()});
					
					var startDate=$form.find(":input[name='loanStartTime']").val();
		    		var endDate=$form.find(":input[name='loanEndTime']").val();
					if(startDate){
						aoData.push({name:"loanStartDate",value:startDate});
					}
					if(endDate){
						aoData.push({name:"loanEndDate",value:endDate});
					}
					viewSelf.aoQueryParams=aoData;//缓存搜索条件
				},
				aoColumns: [
					{mData: "contractNum", mRender:function(data, type, rowdata){
						var linkToDetail = "<a title='点击查看合同详情' href='"+$$ctx + "contractQuery/detail?projectId="+rowdata['projectId'] +"&contractId="+rowdata['contractId']+"&partyId="+rowdata['partyId']+"'>"+data+"</a>";
						return linkToDetail;
					}},
			        {mData: "customerName"},
			        {mData: "productName"},
			        {mData: "applyOrgName"},
			        {mData: "contractStatus", mRender:function(data, type, rowdata){
			        	return dataDict.ContractStatusCode[data];
			        }},
			        {mData: "contractAmt", mRender:function(data, type, rowdata){
			        	return utils.num.thousandsFormat(data+'');
			        }},
			        {mData: "contractTerm", mRender:function(data, type, rowdata){
			        	var termUnit;
			        	if(rowdata.contractTermUnit == '1'){
			        		termUnit = '年';
			        	}else if(rowdata.contractTermUnit == '2'){
			        		termUnit = '月';
			        	}else{
			        		termUnit = '天';
			        	}
			        	return data+termUnit;
			        }},
			        {mData: "contractBalance", mRender:function(data, type, rowdata){
			        	if(!data){
			        		return 0;
			        	}
			        	return utils.num.thousandsFormat(data+'');
			        }},
			        {mData: "cdsGuarantMode", mRender:function(data, type, rowdata){
			        	var cdsGuarantMode ="";
			        	var arrays = data.split(",");
			        	for(var i=0; i<arrays.length;i++){
			        		if(arrays[i]&&dataDict.CdsGuarantMode[arrays[i]]){
			        			cdsGuarantMode+=","+dataDict.CdsGuarantMode[arrays[i]];
			        		}
			        	}
			        	return utils.str.substringAfter(cdsGuarantMode,",");
			        }},
			        {mData: "loanTime", mRender:function(data, type, rowdata){
			        	return utils.date.formatDate(data);
			        }},
			        {mData: "contractDueTime", mRender:function(data, type, rowdata){
			        	return utils.date.formatDate(data);
			        }},
			        {mData: "customerMng"}
			    ],
			    fnDrawCallback:function(){
		    		$("#btn-contract-export").show();
		    	}
			});
			viewSelf.dt = dt;
		},
		formQuery:function(){//查询
			var viewSelf = this;
			var $form=$("form[role='contract_searchform']");
			var contTerm = $form.find(":input[name='contTerm']").val();
			var contTermUnit = $form.find("select[name='contTermUnit']").val();
			//如果合同期限不是数字，则提示
			if(contTerm && utils.number.commafy(contTerm) == undefined){
				utils.alert.warn("合同期限输入不合法，必须为数字！");
				return false;
			}
			//如果合同期限没有选择单位，则提示
			if(contTerm != '' && contTermUnit == ''){
				utils.alert.warn("请选择合同期限单位！");
				return false;
			}
			
			var loanStartTime = $form.find(":input[name='loanStartTime']").val();
			var loanEndTime = $form.find(":input[name='loanEndTime']").val();
			//如果放款开始日期大于结束日期，则提示
			if(loanStartTime && loanEndTime && loanStartTime > loanEndTime){
				utils.alert.warn("放款【开始日期】不能大于【结束日期】！");
				return false;
			}
			viewSelf.dt.fnPageChange(0);
		},
		formReset:function(){//表单重置
			var viewSelf = this;
			$("form[role='contract_searchform']").resetForm();
			viewSelf.dt.fnPageChange(0);
		},
	});
	module.exports = view;
});