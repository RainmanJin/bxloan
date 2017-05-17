define(function(require, exports, module) {
	var aData = undefined;//选中行数据
	var model = require("./model");
	var rm = require("./rm");
	var utils = require("utils");
	var view = Backbone.View.extend({
		el : "body",
		events : {
			"click #back" : "back",
			"change #guarantyTypeCd" : "judgeGuarantyTypeCd",
			"click #showCustomerModal" : "showCustomerModal",
			"click #search" : "search",
			"click #reset" : "reset",
			"click #sure" : "sure"
		},
		initialize : function() {
			this.model = new model();
			this.render();
		},
		render : function() {
			this.initDateInput();
			this.initPage();
			this.initForm();
			this.initChangeEvent();
			
			//2014-12-26
			this.initHistoryTable();
		},
		initPage : function() {
			var viewSelf=this;
			var commonAssetsInd = $('#commonAssetsInd').val();
			var guarantySetupInd = $('#guarantySetupInd').val();
			if (commonAssetsInd == '1') {
				$('#commonAssetsInd1').attr('checked', true);
				$('#commonAssetsInd2').removeAttr('checked');
			} else if (commonAssetsInd == '2') {
				$('#commonAssetsInd2').attr('checked', true);
				$('#commonAssetsInd1').removeAttr('checked');
				$("#partOwnerName").attr("readonly",true);
			}
			if (guarantySetupInd == '1') {
				$('#guarantySetupInd1').attr('checked', true);
				$('#guarantySetupInd2').removeAttr('checked');
			} else if (guarantySetupInd == '2') {
				$('#guarantySetupInd2').attr('checked', true);
				$('#guarantySetupInd1').removeAttr('checked');
				$("#setGuarantyAmt").attr("readonly",true);
			}
			//有无安全检查证明
			var safeCheckIndVal=$("#safeCheckInd").val();
			$("input[name='safeCheckInd'][value="+(safeCheckIndVal?safeCheckIndVal:'1')+"]").attr("checked",true); 
			//有无消防检查证明
			var fireCheckIndVal=$("#fireCheckInd").val();
			$("input[name='fireCheckInd'][value="+(fireCheckIndVal?fireCheckIndVal:'1')+"]").attr("checked",true); 
			//有无产品合格证
			var eliLicenseIndVal=$("#eliLicenseInd").val();
			$("input[name='eliLicenseInd'][value="+(eliLicenseIndVal?eliLicenseIndVal:'1')+"]").attr("checked",true); 
			
			//
			var type = $('#type').val();
			if (type == 'add') {
				$('#form select').val('');
			}
			this.judgeGuarantyTypeCd();
			if (type == 'detail') {
				$('#showCustomerModal').attr('disabled', true);
				$('#form input').attr('disabled', true);
				$('#form select').attr('disabled', true);
				$('#form textarea').attr('disabled', true);
			}

			/**
			 * 初始化客户列表
			 */
			utils.dd.initDataDict(["CustomerType","CertificateType","CustomerStatusCode"], function(dataDict){
				viewSelf.initCustomerTable(dataDict);
			});
			/**
			 * 绑定客户列表行单击事件
			 */
			$(document).on("click", "#table tbody tr", function(e) {
				$(this).find(":radio[name='checkbox']")[0].checked = true;
				aData = viewSelf.oTable.fnGetData(this);
				for ( var i in aData) {
					if (aData[i] == null)
						aData[i] = '';
				}
			});
		},
		initCustomerTable:function(dataDict){
			var viewSelf=this;
			var oTable = $("#table").dataTable({
				sAjaxSource : $$ctx+"collateral/findCustomersBySearch",
				bFilter : false,
				bLengthChange : false,
				aoColumnDefs : [ {
					aTargets : [ 0 ],
					mRender : function(data, type, full) {
						return "<input type='radio' name='checkbox' /> ";
					}
				},{
					aTargets : [ 2 ],
					mRender : function(data, type, full) {
						return "<span style='width:200px;display: block;overflow: hidden;text-overflow: ellipsis;white-space: nowrap;'>"+data+"</span>";
					}
					
				},{
					aTargets : [ 3 ],
					mRender : function(data, type, full) {
						var val=data?dataDict.CustomerType[data]:"";
						return val?val:"";
					}
				},{
					aTargets : [ 4 ],
					mRender : function(data, type, full) {
						var val=data?dataDict.CertificateType[data]:"";
						return val?val:"";
					}
				},{
					aTargets : [ 6 ],
					mRender : function(data, type, full) {
						var val=data?dataDict.CustomerStatusCode[data]:"";
						return val?val:"";
					}
				},{
					"bVisible": false,
					aTargets : [ 7 ]
				}],
				fnServerParams : function(aoData) {
					aoData.push({
						name : "customerName",
						value : $('#customerName').val()
					}, {
						name : "certificateTypeCd",
						value : $('#certificateTypeCd').val()
					}, {
						name : "certificateNum",
						value : $('#certificateNum').val()
					});
				}
			});
			viewSelf.oTable=oTable;
		},
		initForm : function() {
			var viewSelf=this;
			$('#form').validate({
				rules : rm.rules,
				messages : rm.messages,
				errorPlacement: function(error, element) {
                    if (element.is(":radio")) error.appendTo(element.parent().next().next());
                    else if (element.is(":checkbox")) error.appendTo(element.next());
                    else if (element.is(":input[name='guarantorName']")) {
                        error.appendTo(element.parent().parent());
                    } else error.appendTo(element.parent());
                },
				submitHandler : function(form) {
					$("#save").attr("disabled",true);
					viewSelf.model.save($('#form').serialize(),function(r_data){
						var jumpOfSave=$("#jumpOfSave").val();//是否跳转
						if (r_data.success) {
							if (jumpOfSave=='true') {
								if ($('#projectId').val()) {
									utils.alert.suc("<strong>" + r_data.msg + "</strong>", function() {
										window.location.href = $$ctx + 'businessapplicationwd/addPawn/' + $('#projectId').val();										
									});
								} else {
									utils.alert.suc("<strong>" + r_data.msg + "</strong>", function() {
										window.location.href = $$ctx + 'collateral/edit/' + r_data.data;
									});
								}
							} else {
								utils.alert.suc("<strong>" + r_data.msg + "</strong>");
								$("#jumpOfSave").val("close");
							}
						} else {
							utils.alert.err("<strong>" + r_data.msg + "</strong>");
						}
						$("#save").removeAttr("disabled");
					});
				}
			});
		},
		back : function() {
			if ($('#projectId').val()) {
				window.location.href = $$ctx + 'businessapplicationwd/addPawn/' + $('#projectId').val();
			} else {
				window.location.href = $$ctx + 'collateral';
			}
		},
		judgeGuarantyTypeCd : function() {
			var guarantyTypeCd = parseInt($('#guarantyTypeCd').val());
			switch (guarantyTypeCd) {
			case 0:// 房产
				this.machineFormDiv(false);
				this.landFormDiv(false);
				this.vehicleFormDiv(false);
				this.houseFormDiv(true);
				break;
			case 1:// 地产
				this.machineFormDiv(false);
				this.vehicleFormDiv(false);
				this.houseFormDiv(false);
				this.landFormDiv(true);
				break;
			case 2:// 机器设备
				this.landFormDiv(false);
				this.vehicleFormDiv(false);
				this.houseFormDiv(false);
				this.machineFormDiv(true);
				break;
			case 3:// 机动车
				this.machineFormDiv(false);
				this.landFormDiv(false);
				this.houseFormDiv(false);
				this.vehicleFormDiv(true);
				break;
			default:
				this.machineFormDiv(false);
				this.landFormDiv(false);
				this.houseFormDiv(false);
				this.vehicleFormDiv(false);
				break;
			}
		},
		showCustomerModal : function() {
			$('#customerForm input').val('');
			$('#customerForm select').val('');
			this.initTable();
			$('#customerModal').modal('show');
		},
		hideCustomerModal:function(name,typeCd,certificateNum){
			$('#guarantorName').val(name).blur();
			$('#guarantorTypeCd').val(typeCd).blur();
			$('#guarantorCertificateNum').val(certificateNum).blur();
			$('#customerModal').modal('hide');
			//当guarantorName是选择时，证件类型和证件号为只读
			var isSelect=$("#isSelectOfGname");
			if(isSelect!=1){
				isSelect.val(1);
			}
			$("#guarantorTypeCd").attr("readonly",true);
			$("#guarantorCertificateNum").attr("readonly",true);
		},
		search : function() {
			this.initTable();
		},
		reset : function() {
			$('#customerForm input').val('');
			$('#customerForm select').val('');
			this.initTable();
		},
		sure : function() {//确定后，回填数据
			var viewSelf=this;
			if (aData) {
				viewSelf.hideCustomerModal(aData[2],aData[4],aData[5]);
			}
		},
		houseFormDiv:function(isShow){
			var disable=false;
			if(!isShow){
				disable=true;
			}
			$("#house input").attr('disabled', disable);
			$("#house select").attr('disabled', disable);
			if(isShow){
				$('#house').show();
			}else{
				$('#house').hide();
			}
		},
		landFormDiv:function(isShow){
			var disable=false;
			if(!isShow){
				disable=true;
			}
			$("#land input").attr('disabled', disable);
			$("#land select").attr('disabled', disable);
			if(isShow){
				$('#land').show();
			}else{
				$('#land').hide();
			}
		},
		machineFormDiv:function(isShow){
			var disable=false;
			if(!isShow){
				disable=true;
			}
			$("#machine input").attr('disabled', disable);
			$("#machine select").attr('disabled', disable);
			$("#machine textarea").attr('disabled', disable);
			if(isShow){
				$('#machine').show();
			}else{
				$('#machine').hide();
			}
		},
		vehicleFormDiv:function(isShow){
			var disable=false;
			if(!isShow){
				disable=true;
			}
			$("#vehicle input").attr('disabled', disable);
			$("#vehicle select").attr('disabled', disable);
			$("#vehicle textarea").attr('disabled', disable);
			if(isShow){
				$('#vehicle').show();
			}else{
				$('#vehicle').hide();
			}
		},
		initTable : function() {
			var viewSelf = this;
			viewSelf.oTable.fnSettings()._iDisplayStart = 0;
			viewSelf.oTable.fnDraw();
		},
		initChangeEvent:function(){
			//如果名字是选择的，则证件类型和证件号不允许修改
			$("#guarantorName").change(function(){
				var $this=$(this);
				var isSelect=$("#isSelectOfGname");
				if(isSelect.val()==1){
					$("#guarantorTypeCd").attr("readonly",false).find('option:first').prop("selected", 'selected');
					$("#guarantorCertificateNum").attr("readonly",false).val('');
					isSelect.val(0);
				}
			});
			//如果共同财产为否，则不允许共有人名称不可更改
			$("#form :radio[name='commonAssetsInd']").change(function(){
				var self=$(this);
				var other=$("#partOwnerName");
				if(self.val()==2){
					other.data("val",other.val());
					other.val("");
					other.attr("readonly",true);
				}else{
					var val=other.data("val");
					other.val(val?val:"");
					other.attr("readonly",false);
				}
			});
			//如果已设定抵质押为否，则不允许已设定担保额不可更改
			$("#form :radio[name='guarantySetupInd']").change(function(){
				var self=$(this);
				var other=$("#setGuarantyAmt");
				if(self.val()==2){
					other.data("val",other.val());
					other.val("");
					other.attr("readonly",true);
				}else{
					var val=other.data("val");
					other.val(val?val:"");
					other.attr("readonly",false);
				}
			});
		},initDateInput:function(){
			$("#constructedDate").datepicker({todayBtn:true,autoclose:true}).on("click",function(){
			}).on("show",function(){
				$(".datepicker").css("z-index","99999");
			});
			$("#landEndDate").datepicker({todayBtn:true,autoclose:true}).on("click",function(){
			}).on("show",function(){
				$(".datepicker").css("z-index","99999");
			});
			$("#registerDate").datepicker({todayBtn:true,autoclose:true}).on("click",function(){
			}).on("show",function(){
				$(".datepicker").css("z-index","99999");
			});
		},
		
		//2014-12-26
		initHistoryTable : function() {
			$("#historyTable").dataTable({
				sAjaxSource : $$ctx + "collateral/findHistoryBySearch",
				bFilter : false,
				bLengthChange : false,
				aoColumnDefs : [ {
					aTargets : [ 1 ],
					mRender : function(data, type, full) {
						if(data)
							return "<a role='projectDetail' href='javascript:void(0)' sysCd="+ full[6] + ">" + data + "</a>";
						return "";
					}
				} , {
					aTargets : [ 3 ],
					mRender : function(data, type, full) {
						if(data)
							return "<a role='contractDetail' href='javascript:void(0)' sysCd="+ full[6] + ">" + data + "</a>";
						return "";
					}
				}, {
					aTargets : [ 6 ],
					bVisible : false, 
					mRender : function(data, type, full) {
						return data;
					}
				}],
				fnServerParams : function(aoData) {
					aoData.push({
						name : "guarantyId",
						value : $('#guarantyId').val()
					});
				}
			});
			
			$(document).on("click", "a[role='projectDetail']", function(e) {
				var $this = $(this);
				//modify by wangyawei 20150430 start 小贷业务暂不支持查看
				var sysCd = $this.attr('sysCd');
				if(utils.str.contains(sysCd, '1') || utils.str.contains(sysCd, '2')){
					$("#projectIframe").attr("src", $$ctx + "/bizapply/showDetailByProjectNo?projectNo=" + $this.text());
					$("#projectIframe").load(function() {
						setInterval(function(){
							var clientHeight = $("#projectIframe").contents().find("body").height();
							$("#projectIframe").attr("height",clientHeight+"px!important;");
						},100);
					});
					$("#projectModal").modal("show");
				} else{
					utils.alert.warn("此笔业务属于小贷业务,暂不支持查看");
				}
				//modify by wangyawei 20150430 end
			});
			$(document).on("click", "a[role='contractDetail']", function(e) {
				var $this = $(this);
				//modify by wangyawei 20150430 start 小贷业务暂不支持查看
				var sysCd = $this.attr('sysCd');
				if(utils.str.contains(sysCd, '1') || utils.str.contains(sysCd, '2')){
					var url = $$ctx + "/contractMng/viewDetailByContractNum?contractNum=" + $this.text();
					$("#contractIframe").attr("src", encodeURI(encodeURI(url)));
					$("#contractIframe").load(function() {
						setInterval(function(){
							var clientHeight = $("#contractIframe").contents().find("body").height();
							$("#contractIframe").attr("height",clientHeight+"px!important;");
						},100);
					});
					$("#contractModal").modal("show");
				} else{
					utils.alert.warn("此笔业务属于小贷业务,暂不支持查看");
				}
				//modify by wangyawei 20150430 end
			});
		}
	});
	module.exports = view;
});