define(function(require, exports, module) {
		var utils = require("utils");
		var model = require("./model");
		var rm = require("./rm");
		var view = Backbone.View.extend({
			el: "#main_tabbable",
			events: {
				'click button[role="btn_customer"]':'fnOpenCustomerInfo'
			},
			initialize: function() { /** 初始化 */
				this.model=new model();
				this.render();
			},
			render:function(){
				var viewSelf=this;
				//客户信息初始化
				utils.dd.initDataDict(["TermUnitCd"], function(dataDict){
					viewSelf.initCustomer(dataDict);
				});
				this.initTree();
				//业务查询表格初始化
				this.initBusiDataTables();
				//合同台账表格初始化
				utils.dd.initDataDict(["ContractStatusCode","CdsGuarantMode"], function(dataDict){
					viewSelf.initContractDataTables(dataDict);
				});
			},
			initCustomer:function(dataDict){
				var viewSelf=this;
				var params=[];
				params.push('partyId='+$("#partyId").val());
				var $form=viewSelf.$el.find('form[role="customerInfo"]');
				var $btn=viewSelf.$el.find('[role="btn_customer"]');
				if($btn.text()==""){
					viewSelf.model.findCustomer(params.join('&'),function(r_data){
						if(r_data&&r_data.success){
							var data=r_data.data;
							//概况信息的反显
							utils.forms.putValueToForm(data,$form);
							//时间格式控制
							var birthday = viewSelf.$el.find('[id="birthday"]');
							var date = new Date(data.birthday);
                            var date_str = date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate();
                            birthday.val(date_str);
	                       /* $.each($form.find("input[type='text'], select, textarea"), function() {
	                        	if($(this).attr("name")=='birthday'){
	                        		var date = new Date(data[$(this).attr("name")]);
	                                var date_str = date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate();
	                        		$(this).val(date_str);
	                        	}else{
	                        		$(this).val(data[$(this).attr("name")]);
	                        	}
	                        });*/
	                        $btn.text(data.customerNum);
						}else{
							utils.alert.err( "<strong>因未知错误，获取信息失败！</strong>");
						}
					});
				}else{
					$form.find("div[id='faq-0-1']").hide();
					$form.find("div[id='faq-0-2']").show();
				}
				$form.find("input, select, textarea").attr("disabled","true");
				
				//客户类型字段反显
				viewSelf.model.findParty(params.join('&'),function(r_data){
					if(r_data&&r_data.success){
						var data=r_data.data;
                        var partyTypeCd=$form.find('[name="partyTypeCd"]');
                        partyTypeCd.val(data.partyTypeCd);
                        $btn.data('party-id',data.partyId);
						$btn.data('party-type',data.partyTypeCd);
					} else {
                    	utils.alert.err( "<strong>因未知错误，获取信息失败！</strong>");
                    }
				})
			},
			initTree: function() {
                /**初始化树*/
                var viewSelf = this;
                $.fn.zTree.init($("#industryTree"), {
                    async: {
                        enable: true,
                        url: $$ctx + "singleCustomer/getAllIndustry"
                    },
                    data: {
                        simpleData: {
                            enable: true,
                            idKey: "industryTypeCd",
                            pIdKey: "parentIndustryTypeCd"
                        },
                        key: {
                            name: "industryTypeName"
                        }
                    },
                    check: {
                        enable: true,
                        chkStyle: "radio",
                        radioType: "all"
                    },
                    callback: {
                        onClick: function(event, treeId, treeNode) {

                            if (treeNode != null && treeNode.children != null && treeNode.children.length != null && treeNode.children.length > 0) {
                                $("#industryLevelTwoCd").val("");
                                $("#industryCdMask").val("");
                                $("#industryCd").val("");
                                return false;
                            } else {
                                $("#industryLevelTwoCd").val(treeNode.industryTypeCd);
                                $("#industryCdMask").val(treeNode.industryTypeName);
                                $("#industryCd").val(treeNode.industryTypeCd);
                                var treeObj = $.fn.zTree.getZTreeObj(treeId);
                                var node = treeObj.getNodeByParam("industryTypeCd", treeNode.industryTypeCd, null);
                                treeObj.checkNode(node, true, true);
                                var parentNode = node.getParentNode();
                                treeObj.expandNode(parentNode, true, false);
                                $("#industryTreeWarp").toggle(300);
                            }
                            
                        },
                        beforeCheck: function(treeId, treeNode) {
                            return ! treeNode.isParent;
                        },
                        onAsyncSuccess: function(event, treeId, treeNode, msg) {
                            var treeObj = $.fn.zTree.getZTreeObj(treeId);
                            var industryTypeCd = $("#industryLevelTwoCd").val();
                            if (industryTypeCd !== "") {
                                var node = treeObj.getNodeByParam("industryTypeCd", industryTypeCd, null);
                                treeObj.checkNode(node, true, true);

                                var parentNode = node.getParentNode();
                                treeObj.expandNode(parentNode, true, false);

                                $("#industryCdMask").val(node.industryTypeName);
                                $("#industryCd").val(node.industryTypeCd);
                            }
                        }
                    }
                });
            },
			fnOpenCustomerInfo:function(e){
	        	  /*var $btn=$(e.currentTarget);
	        	  var partyType=$btn.data("party-type")+'';
	        	  var partyId=$btn.data("party-id");
	        	  var viewSelf = this;
	              viewSelf.model.openCustomerWindow(partyType,{
	                  customerId: partyId,
	                  workCode: "TODETAIL",
	                  customerSource: "detail",
	                  consultLocation: "contract"
	              },function(result) {
	              	$("#mainFrameOfContact").attr("src",  result);
	              	//viewSelf.initFrameHight();
	                $("#modalOfContact").modal("show");
	              });*/
				  var viewSelf = this;
				  var $btn=$(e.currentTarget);
	        	  var partyType=$btn.data("party-type")+'';
	        	  var partyId=$btn.data("party-id");
	        	  var url = "";
					switch (partyType) {
					case "1":
						url = $$ctx + "corpcustomer/showDetail/" + partyId + "?consultLocation=";
						window.location.href = url;
						break;
					case "2":
						url = $$ctx + "singleCustomer/modToForm?customerId="+partyId+"&workCode=TODETAIL&customerSource=detail"
						$.get(url).success(function(data) {
							window.location.href = $$ctx + data;
						}).error(function(){bootbox.alert("查询失败请稍后重试");});
						break;
					default:
						break;
					}
			},
//			fnOpenContractInfo:function(e){
//	        	  var $btn=$(e.currentTarget);
//	        	  var partyType=$btn.data("party-type")+'';
//	        	  var partyId=$btn.data("party-id");
//	        	  var viewSelf = this;
//	             
//			},
			initBusiDataTables : function() {
				$('select').val('');
				var viewSelf = this;
				var oTable = $("#table").dataTable({
					sAjaxSource : $$ctx + "customerQuery/findBusinessList",
					bFilter : false,
					bLengthChange : true,
					fnServerParams : function(aoData) {
						aoData.push({name:"partyId",value:$("input[name='partyId']").val()})
					},
					aoColumnDefs : [ 
					    {aTargets : [ 0 ],mRender : function(data, type, rowdata) {
					    	var linkToApply = "<a title='点击查看业务申请' href='"+ $$ctx + "bizapply/checkApplication/" + rowdata[7] + "/queryBusiness" +"'>"+ data +"</a>";
					    	return linkToApply;
//					    	跳转
//					    	if(data)
//					    		return "<a role='projectDetail' href='javascript:void(0)' sysCd="+ rowdata[9] + ">" + data + "</a>";
//					    	return "";
					    }},
					    {aTargets : [ 6 ],mRender : function(data, type, rowdata) {
							switch (data) {
								case '0': return "未提交";
								case '1': return "已提交";
								case '2': return "已批复";
								case '3': return "中止";
								case '4': return "否决";
								case '5': return "已签合同";
								case '6': return "已终止";
								default: return "";
							}
						}}, 
//						{aTargets : [ 7 ],mRender : function(data, type, rowdata) {
//							var operation = 
//								"<div class='btn-group' style='width:80px;'>"+
//									"<button data-workflowId='" + rowdata[8] + "' " + 
//									"data-status='" + rowdata.taskStatus + "' " + 
//									"class='btn btn-xs btn-info' role='showWorkDetail' " +
//									"data-toggle='tooltip' data-placement='bottom' title='查看流程进度'>" +
//									"<i class='ace-icon fa fa-eye'></i></button>" +
//								"</div>"
//							return operation;
//						}}
						],
					fnDrawCallback : function(){
	                     	utils.num.tableFormat(this);//对表格内的纯数字列加逗号
	                     },
	                
				});
//				$(document).on("click", "a[role='projectDetail']", function(e) {//跳转用到该段代码
//					var $this = $(this);
//					//modify by wangyawei 20150430 start 小贷业务暂不支持查看
//					var sysCd = $this.attr('sysCd');
//					if(utils.str.contains(sysCd, '1') || utils.str.contains(sysCd, '2')){
//						$("#projectIframe").attr("src", $$ctx + "/bizapply/showDetailByProjectNo?projectNo=" + $this.text());
//						$("#projectIframe").load(function() {
//							setInterval(function(){
//								var clientHeight = $("#projectIframe").contents().find("body").height();
//								$("#projectIframe").attr("height",clientHeight+"px!important;");
//							},100);
//						});
//						$("#projectModal").modal("show");
//					} else{
//						utils.alert.warn("此笔业务属于小贷业务,暂不支持查看");
//					}
//					//modify by wangyawei 20150430 end
//				});
				viewSelf.oTable = oTable;
			},
			initContractDataTables: function(dataDict){
				var viewSelf = this;
				var dt = $("#contractTable").dataTable({
					bFilter:false,
					bSort:false,
					sAjaxSource: $$ctx+"contractQuery/findContactListForCustomerQuery",
					fnServerParams: function(aoData){
						var $form=$("form[role='contract_searchform']");
			    		aoData.push({name:"orgId",value:$form.find("select[name='orgId']").val()});
			    		aoData.push({name:"partyId",value:$("input[name='partyId']").val()});
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
//							if(data)
//								return "<a role='contractDetail' href='javascript:void(0)' projectId="+ rowdata['projectId']+" contractId="+rowdata['contractId']+" partyId="+rowdata['partyId']+">" + data + "</a>";
//							return "";
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
//				$(document).on("click", "a[role='contractDetail']", function(e) {
//					var $this = $(this);
//					var url = $$ctx + "contractQuery/detail?projectId=" + $this.attr("projectId") +"&contractId="+$this.attr("contractId")+"&partyId="+$this.attr("partyId");
//					$("#contractIframe").attr("src", encodeURI(encodeURI(url)));
//					$("#contractIframe").load(function() {
//						setInterval(function(){
//							var clientHeight = $("#contractIframe").contents().find("body").height();
//							$("#contractIframe").attr("height",clientHeight+"px!important;");
//						},100);
//					});
//					$("#contractModal").modal("show");
//					
//					
//					/*var $this = $(this);
//		        	var projectId = $this.attr("projectId");
//		        	var contractId = $this.attr("contractId");
//		        	var partyId = $this.attr("partyId");
//		        	//var viewSelf = this;
//		            viewSelf.model.openContractWindow({
//		            	projectId: projectId,
//		            	contractId: contractId,
//		            	partyId: partyId,
//			            consultLocation: "contract"
//		            },function(result) {
//			            $("#mainFrameOfContact").attr("src",  result);
//			            //viewSelf.initFrameHight();
//			            $("#modalOfContact").modal("show");
//		            });*/
//				});
				
			}
		});
		module.exports = view;
});