define(function(require, exports, module) {
	var pawnAData = undefined;
	var collateralAData = undefined;
	var reg = /^-?[0-9]*(\.\d*)?$|^-?d^(\.\d*)?$/;
	var integerReg =  /^\d+$/;
	
	var model = require("../main/model");
	var rm = require("../main/rm");
	var utils = require("utils");
	var view = Backbone.View.extend({
		el : "body",
		events : {
			"click #addPawn": "addPawn",
			"click #addPawnBack": "addPawnBack",
			"click #openPawnSureModal": "openPawnSureModal",
			"change #appGuaDebtInterAmt" : "appGuaDebtInterAmtOnChange",
			/** 新增抵质押 */
			"change #guarantyTypeCd" : "judgeGuarantyTypeCd",
			"click #guarantorName_addEditDetailDiv" : "showCustomerModal",
			"click #sure_addEditDetailDiv" : "sure_addEditDetailDiv",
			"click #search_addEditDetailDiv" : "search_addEditDetailDiv",
			"click #reset_addEditDetailDiv" : "reset_addEditDetailDiv"
		},
		initialize : function() {
			this.model = new model();
			this.render();
		},
		render : function() {
			/** 抵质押担保 */
			this.initProjectPawnInfoTable();
			this.initCollateralTable();
		},
		initProjectPawnInfoTable : function() {
			var oTable = $("#projectPawnInfoTable").dataTable({
				sAjaxSource : $$ctx + "businessapplicationwd/searchProjectPawnInfoList",
				bFilter : false,
				bLengthChange : false,
				aoColumnDefs : [ {
					aTargets : [ 3 ],
					mRender : function(data, type, full) {
						return "人民币";
					}
				} , 
				{//修改担保率显示问题     libingqing
					aTargets : [ 6 ],
					mRender: function(data, type, rowdata) {
						var out = (data*100).toFixed(2) + "";
						if(out.length > 10){
							return out.substring(0, 10);
						}
						return out;
					}
				} ,//end
				{
					aTargets : [ 9 ],
					mRender : function(data, type, full) {
						var button = "<div class='btn-group'style='width:100px;'><button title='查看' type='button' role='checkPawn' data-id='" + data + "' class='btn btn-xs btn-yellow'><i class='ace-icon fa fa-eye'></i></button>";
						if($('#type').val() != 'check')
							button += "<button title='删除' type='button' role='deletePawn' data-id='" + data + "' class='btn btn-xs btn-danger'><i class='ace-icon fa fa-trash-o'></i></button></div>";
						return button;
					}
				} ],
				fnServerParams : function(aoData) {
					aoData.push({
						name : "projectId",
						value : $('#projectId').val()
					});
				}
			});
			
			$(document).on("click", "button[role='checkPawn']", function(e) {
				$.ajax({
					type : 'post',
					url : $$ctx + 'businessapplicationwd/checkPawn',
					data : {
						'id' : $(this).data("id")
					},
					success : function(r) {
						
						$.each($("#form_addEditDetailDiv").find("input[type!=radio], select, textarea"), function() {
							$(this).val(r[$(this).attr("name")]);
							$(this).attr('disabled', true);
						});
						$("#form_addEditDetailDiv input[type=radio]").attr('disabled', true);
						$('#sysUpdateDate').val(r['sysUpdateDateStr']);
						$('#constructedDate').val(r['constructedDateStr']);
						$('#landEndDate').val(r['landEndDateStr']);
						$('#registerDate').val(r['registerDateStr']);
						
						$('#form_addEditDetailDiv input[type=radio][name=commonAssetsInd]' +
								'[value=' + r.commonAssetsInd + ']')[0].checked = true;
						$('#form_addEditDetailDiv input[type=radio][name=guarantySetupInd]' +
								'[value=' + r.guarantySetupInd + ']')[0].checked = true;
						
						$('#guarantyTypeCd').change();
						
						$('#addEditDetailDiv').modal('show');
					}
				});
			});
			
			$(document).on("click", "button[role='deletePawn']", function(e) {
				var id = $(this).data("id");
				utils.button.confirm(null, function(result) {
					if (result) {
						$.ajax({
							type : 'post',
							url : $$ctx + 'businessapplicationwd/deletePawn',
							data : {
								'id' : id
							},
							success : function(r) {
								utils.alert.suc("<strong>" + r.msg + "</strong>");
								$("#projectPawnInfoTable").dataTable().fnDraw();
							}
						});
					}
				});
			});
		},
		initCollateralTable : function() {
			
			/**
			 * 清空查询框
			 */
			$('#guarantorName').val('');
			$('#guarantyName').val('');
			$('#guarantyStatusCd').val('');
			$('#guaranteeType').val('');
			
			/**
			 * 初始化抵质押列表
			 */
			var viewSelf = this;
			var oTable = $("#table").dataTable({
				sAjaxSource : $$ctx + "businessapplicationwd/findCollateralBySearch",
				bFilter : false,
				bLengthChange : false,
				aoColumnDefs : [ {
					aTargets : [ 0 ],
					mRender : function(data, type, full) {
						return "<label class='checkbox-inline'><input type='radio' name='checkbox'" +
								" class='ace add_corp_Type form-control' /><span class='lbl'></span></label>";
					}
				} ],
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
					}, {
						name : "guaranteeType",
						value : $('#guaranteeType').val()
					}, {
						name : "guaranteeMode",
						value : !$('#guaranteeMode').val() ? 'temp' : $('#guaranteeMode').val()
					}, {
						name : "projectId",
						value : $('#projectId').val()
					});
				},
				fnDrawCallback : function() {
					pawnAData = undefined;
				}
			});
			viewSelf.oTable = oTable;
			
			/**
			 * 绑定抵质押列表行单击事件
			 */
			$(document).on("click", "#table tbody tr", function(e) {
				pawnAData = $("#table").dataTable().fnGetData(this);
				for ( var i in pawnAData) {
					if (pawnAData[i] == null)
						pawnAData[i] = '';
				}
				this.innerHTML="<td><label class='checkbox-inline'><input type='radio' name='checkbox' checked='true' " +
						"class='ace add_corp_Type form-control' /><span class='lbl'></span></label></td>"+
						"<td>"+pawnAData[1]+"</td>"+"<td>"+pawnAData[2]+"</td>"+"<td>"+pawnAData[3]+"</td>"+
						"<td>"+pawnAData[4]+"</td>"+"<td>"+pawnAData[5]+"</td>"+"<td>"+pawnAData[6]+"</td>"+
						"<td>"+pawnAData[7]+"</td>"+"<td>"+pawnAData[8]+"</td>";
			});
			
			/**
			 * 绑定查询、重置、新增抵质押按钮
			 */
			$(document).on("click", "button[id='searchCollateral']", function(e) {
				var table = $("#table").dataTable();
				table.fnSettings()._iDisplayStart = 0;
				table.fnDraw();
			});
			$(document).on("click", "button[id='resetCollateral']", function(e) {
				$('#guarantorName').val('');
				$('#guarantyName').val('');
				$('#guarantyStatusCd').val('');
				$('#guaranteeType').val('');
				var table = $("#table").dataTable();
				table.fnSettings()._iDisplayStart = 0;
				table.fnDraw();
			});
			
			/**
			 * 初始化客户列表
			 */
			var oTable = $("#customerTable_addEditDetailDiv").dataTable({
				sAjaxSource : $$ctx + "businessapplicationwd/findCustomersBySearchForCollateral",
				bFilter : false,
				bLengthChange : false,
				aoColumnDefs : [ {
					aTargets : [ 0 ],
					mRender : function(data, type, full) {
						return "<label class='checkbox-inline'><input type='radio' name='checkbox' " +
								"class='ace add_corp_Type form-control' /><span class='lbl'></span></label>";
					}
				}, {
					bVisible : false,
					aTargets : [ 7 ]
				} ],
				fnServerParams : function(aoData) {
					aoData.push({
						name : "customerName",
						value : $('#customerName_addEditDetailDiv').val()
					}, {
						name : "certificateTypeCd",
						value : $('#certificateTypeCd_addEditDetailDiv').val()
					}, {
						name : "certificateNum",
						value : $('#certificateNum_addEditDetailDiv').val()
					});
				}
			});

			/**
			 * 绑定客户列表行单击事件
			 */
			$(document).on("click", "#customerTable_addEditDetailDiv tbody tr", function(e) {
				collateralAData = oTable.fnGetData(this);
				for ( var i in collateralAData) {
					if (collateralAData[i] == null)
						collateralAData[i] = '';
				}
				this.innerHTML="<td><label class='checkbox-inline'><input type='radio' checked='true' name='checkbox' " +
						"class='ace add_corp_Type form-control' /><span class='lbl'></span></label></td>"+
						"<td>"+collateralAData[1]+"</td>"+"<td>"+collateralAData[2]+"</td>"+"<td>"+collateralAData[3]+"</td>"+
						"<td>"+collateralAData[4]+"</td>"+"<td>"+collateralAData[5]+"</td>"+"<td>"+collateralAData[6]+"</td>";
			});
			
			$(document).on("click", "button[id='addCollateral']", function(e) {
				$('#collateralIframe').attr('src' , $$ctx + 'collateral/add?source=business');
				$('#collateralDetailDiv div.modal-header h4').html("<i class='ace-icon fa fa-plus'></i>&nbsp;抵质押");
				$('#collateralDetailDiv').modal('show');
				
				$("#collateralIframe").load(function() {
                	var clientHeight = $("#collateralIframe").contents().find("body").height();
        	    	$("#collateralIframe").attr("height",clientHeight+"px!important;");
	        	    	
    				var timer = setInterval(function(){
  	        	    	var $iframe =  $($('#collateralIframe')[0].contentWindow.document.body); 
  	        	    	var value = $iframe.find("#jumpOfSave").val();
  	        	    	if(value == "close") {
  	        	    		$("#table").dataTable().fnDraw();
  	        	    		$('#collateralDetailDiv').modal('hide');
  	        	    		clearInterval(timer);
  	        	    	}
    	        	},100);
				});
			});
			
			/**
			 * 初始化pawn表单
			 */
			$('#pawnForm').validate({
				messages : rm.messages,
				submitHandler : function(form) {
					//modify for HWL 20150713 抵质押担保率校验
					var $form=$(form);
					var appGuaDebtInterAmt = $('#appGuaDebtInterAmt').val();//本次申请担保债权金额（元）
					var applyAmt = utils.num.normal($('#applyAmt').val());//申请金额
					if(appGuaDebtInterAmt > applyAmt) {
						utils.alert.warn("<strong>本次申请担保债权金额不能大于申请金额！</strong>");
						return false;
					}
					var evalValue = $form.find(':input[name="evalValue"]').val();//评估价值
					var setGuarantyAmt = $form.find(':input[name="setGuarantyAmt"]').val();//已设定担保金额（元）
					var availableAmt=parseFloat(evalValue?evalValue:0)-parseFloat(setGuarantyAmt?setGuarantyAmt:0);//可用金额
					if(appGuaDebtInterAmt > availableAmt) {
	 					utils.alert.warn("<strong>本次申请担保债权金额不能大于可用金额！</strong>");
	 					return ;
	 				}
					var pawnRatio=$form.find(':input[name="pawnRatio"]').val();//担保率
					pawnRatio=parseFloat(pawnRatio/100).toFixed(6);//利率变为小数
					//提交数据
					$("#sure").prop("disabled",true);
					$.ajax({
						type : 'post',
						url : $$ctx + 'businessapplicationwd/saveProjectPawnInfo',
						data : {
							"guarantyId" : $('#guarantyId').val(),
							"appGuaDebtInterAmt" : $('#appGuaDebtInterAmt').val(),
							"pawnRatio" : pawnRatio,
							"projectId" : $('#projectId').val()
						},
						success : function(result) {
							$('#sure').prop("disabled",false);
							if (result.success) {
								$('#pawnModal').modal('hide');
								$(form).resetForm();
								var table = $("#projectPawnInfoTable").dataTable().fnDraw();
								table = $("#table").dataTable().fnDraw();
								pawnAData = undefined;
								$('#collateralTableDiv').hide();
								$('#projectPawnInfoTableDiv').show();
								$('#addPawn').show();
							} else {
								utils.alert.err("<strong>" + result.msg + "</strong>");
							}
						}
					});
					//modify for HWL 20150713 抵质押担保率校验 end
				}
			});
		},
		addPawn : function() {
			$('#guarantorName').val('');
			$('#guarantyName').val('');
			$('#guarantyStatusCd').val('');
			
			/**
			 * 初始化抵质押列表
			 */
			var table = $("#table").dataTable();
			table.fnSettings()._iDisplayStart = 0;
			table.fnDraw();
			
			$('#addPawn').slideUp();
			$('#projectPawnInfoTableDiv').slideUp();
			$('#collateralTableDiv').slideDown();
		},
		addPawnBack : function() {
			$('#projectPawnInfoTableDiv').slideDown();
			$('#collateralTableDiv').slideUp();
			$('#addPawn').slideDown();
		},
		openPawnSureModal : function() {
			if (pawnAData[5] <= pawnAData[6])
				utils.alert.warn("<strong>该抵质押物的已设定担保金额大于等于评估价值，不能关联</strong>");
			else if (pawnAData) {
				$('#pawnForm').resetForm();
				$('#evalValue').val(pawnAData[5]);
				$('#setGuarantyAmt').val(pawnAData[6]);
				$('#guarantyId').val(pawnAData[9]);
				$('#pawnModal').modal('show');
			}
		},
		judgeGuarantyTypeCd : function() {
			var guarantyTypeCd = parseInt($('#guarantyTypeCd').val());
			switch (guarantyTypeCd) {
			case 0:// 房产
				this.landHide();
				this.machineHide();
				this.vehicleHide();
				this.houseShow();
				break;
			case 1:// 地产
				this.machineHide();
				this.vehicleHide();
				this.houseHide();
				this.landShow();
				break;
			case 2:// 机器设备
				this.vehicleHide();
				this.houseHide();
				this.landHide();
				this.machineShow();
				break;
			case 3:// 机动车
				this.houseHide();
				this.landHide();
				this.machineHide();
				this.vehicleShow();
				break;
			default:
				this.houseHide();
				this.landHide();
				this.machineHide();
				this.vehicleHide();
				break;
			}
		},
		houseShow : function() {
			$('#constructedDate').attr('required', true);
			$('#houseLicenseOwner').attr('required', true);
			$('#buildAllArea').attr('required', true);
			$('#buildAllArea').attr('isNumber', true);
			$('#propertyTerm').attr('required', true);
			$('#propertyTerm').attr('isIntNotNegative', true);
			$('#landAcquiringWayCd').attr('required', true);
			$('#utilizationType').attr('required', true);
			$('#houseQuitclaimCode').attr('required', true);
			$('#landArea').attr('required', true);
			$('#landArea').attr('isNumber', true);
			$('#totleFloor').attr('required', true);
			$('#totleFloor').attr('isIntNotNegative', true);

			/**
			 * 初始化建成日期
			 * 
			 * @returns
			 */
			// 将datepicker控件放在最顶层
			var dateOnTop = function() {
				$(".datepicker").css("z-index", "99999");
			};
			var defaultDateConf = {
				autoclose : true,
				todayHighlight : true,
				clearBtn : true,
				endDate : 'd'
			};
			var initDateP = function(selector, changeCallBack, config) {
				var _dateConf = config || defaultDateConf;
				$(selector).datepicker(_dateConf).on("changeDate",
						changeCallBack).on("click", dateOnTop);
			};
			initDateP("#constructedDate");

			$('#house').show();
		},
		houseHide : function() {
			$('#constructedDate').attr('required', false);
			$('#houseLicenseOwner').attr('required', false);
			$('#buildAllArea').attr('required', false);
			$('#buildAllArea').attr('isNumber', false);
			$('#propertyTerm').attr('required', false);
			$('#propertyTerm').attr('isIntNotNegative', false);
			$('#landAcquiringWayCd').attr('required', false);
			$('#utilizationType').attr('required', false);
			$('#houseQuitclaimCode').attr('required', false);
			$('#landArea').attr('required', false);
			$('#landArea').attr('isNumber', false);
			$('#totleFloor').attr('required', false);
			$('#totleFloor').attr('isIntNotNegative', false);
			$('#house').hide();
		},
		landShow : function() {
			$('#abstractEmissarg').attr('required', true);
			$('#terraNumber').attr('required', true);
			$('#landEsplees').attr('required', true);
			$('#accessType').attr('required', true);
			$('#particularArea').attr('isNumber', true);
			$('#landSit').attr('required', true);
			$('#geographyPurpose').attr('required', true);
			$('#landEndDate').attr('required', true);
			$('#landUseArea').attr('required', true);
			$('#landUseArea').attr('isNumber', true);
			$('#apportionArea').attr('isNumber', true);

			/**
			 * 初始化土地终止日期
			 * 
			 * @returns
			 */
			// 将datepicker控件放在最顶层
			var dateOnTop = function() {
				$(".datepicker").css("z-index", "99999");
			};
			var defaultDateConf = {
				autoclose : true,
				todayHighlight : true,
				clearBtn : true
			};
			var initDateP = function(selector, changeCallBack, config) {
				var _dateConf = config || defaultDateConf;
				$(selector).datepicker(_dateConf).on("changeDate",
						changeCallBack).on("click", dateOnTop);
			};
			initDateP("#landEndDate");

			$('#land').show();
		},
		landHide : function() {
			$('#abstractEmissarg').attr('required', false);
			$('#terraNumber').attr('required', false);
			$('#landEsplees').attr('required', false);
			$('#accessType').attr('required', false);
			$('#particularArea').attr('isNumber', false);
			$('#landSit').attr('required', false);
			$('#geographyPurpose').attr('required', false);
			$('#landEndDate').attr('required', false);
			$('#landUseArea').attr('required', false);
			$('#landUseArea').attr('isNumber', false);
			$('#apportionArea').attr('isNumber', false);
			$('#land').hide();
		},
		machineShow : function() {
			$('#modelType').attr('required', true);
			$('#equipMount').attr('isIntNotNegative', true);
			$('#deviceUseLife').attr('required', true);
			$('#deviceUseLife').attr('isIntNotNegative', true);
			$('#utilizedYears').attr('required', true);
			$('#utilizedYears').attr('isIntNotNegative', true);
			$('#oriPurPrice').attr('required', true);
			$('#oriPurPrice').attr('isNumber', true);
			$('#purpose').attr('required', true);
			$('#machine').show();
		},
		machineHide : function() {
			$('#modelType').attr('required', false);
			$('#equipMount').attr('isIntNotNegative', false);
			$('#deviceUseLife').attr('required', false);
			$('#deviceUseLife').attr('isIntNotNegative', false);
			$('#utilizedYears').attr('required', false);
			$('#utilizedYears').attr('isIntNotNegative', false);
			$('#oriPurPrice').attr('required', false);
			$('#oriPurPrice').attr('isNumber', false);
			$('#purpose').attr('required', false);
			$('#machine').hide();
		},
		vehicleShow : function() {
			$('#registerDate').attr('required', true);
			$('modelStyle').attr('required', true);
			$('#launchPlaneNumber').attr('required', true);
			$('#machineSteamCard').attr('required', true);
			$('#frameNumber').attr('required', true);
			$('#vehicleNumber').attr('required', true);
			$('#machineEnrol').attr('required', true);
			$('#runnedKm').attr('required', true);
			$('#runnedKm').attr('isNumber', true);
			$('#troubleRecords').attr('required', true);

			/**
			 * 初始化购置日期
			 * 
			 * @returns
			 */
			// 将datepicker控件放在最顶层
			var dateOnTop = function() {
				$(".datepicker").css("z-index", "99999");
			};
			var defaultDateConf = {
				autoclose : true,
				todayHighlight : true,
				clearBtn : true,
				endDate : 'd'
			};
			var initDateP = function(selector, changeCallBack, config) {
				var _dateConf = config || defaultDateConf;
				$(selector).datepicker(_dateConf).on("changeDate",
						changeCallBack).on("click", dateOnTop);
			};
			initDateP("#registerDate");

			$('#vehicle').show();
		},
		vehicleHide : function() {
			$('#registerDate').attr('required', false);
			$('modelStyle').attr('required', false);
			$('#launchPlaneNumber').attr('required', false);
			$('#machineSteamCard').attr('required', false);
			$('#frameNumber').attr('required', false);
			$('#vehicleNumber').attr('required', false);
			$('#machineEnrol').attr('required', false);
			$('#runnedKm').attr('required', false);
			$('#runnedKm').attr('isNumber', false);
			$('#troubleRecords').attr('required', false);
			$('#vehicle').hide();
		},
		showCustomerModal : function() {
			$('#customerForm_addEditDetailDiv input').val('');
			$('#customerForm_addEditDetailDiv select').val('');
			var table = $("#customerTable_addEditDetailDiv").dataTable();
			table.fnSettings()._iDisplayStart = 0;
			table.fnDraw();
			$('#customerModal').modal('show');
		},
		sure_addEditDetailDiv : function() {
			if (collateralAData) {
				$('#form_addEditDetailDiv input[name=guarantorName]').val(collateralAData[2]);
				$('#form_addEditDetailDiv select[name=guarantorTypeCd]').val(collateralAData[7]);
				$('#form_addEditDetailDiv input[name=guarantorCertificateNum]').val(collateralAData[5]);
				$('#customerModal').modal('hide');
			}
		},
		search_addEditDetailDiv : function() {
			var table = $("#customerTable_addEditDetailDiv").dataTable();
			table.fnSettings()._iDisplayStart = 0;
			table.fnDraw();
		},
		reset_addEditDetailDiv : function() {
			$('#customerForm_addEditDetailDiv input').val('');
			$('#customerForm_addEditDetailDiv select').val('');
			var table = $("#customerTable_addEditDetailDiv").dataTable();
			table.fnSettings()._iDisplayStart = 0;
			table.fnDraw();
		},
		appGuaDebtInterAmtOnChange : function() {
			var evalValue = $('#evalValue').val();
			var setGuarantyAmt = $('#setGuarantyAmt').val();
			var appGuaDebtInterAmt = $('#appGuaDebtInterAmt').val();
			if(appGuaDebtInterAmt.match(reg) != null) {
				var pawnRatio = appGuaDebtInterAmt / (evalValue - setGuarantyAmt);
				$('#pawnRatio').val(parseFloat(pawnRatio*100).toFixed(2));
			}
		}
	});
	module.exports = view;
});