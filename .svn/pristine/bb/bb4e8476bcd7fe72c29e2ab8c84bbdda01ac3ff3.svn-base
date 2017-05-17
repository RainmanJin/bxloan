define(function(require, exports, module) {
	var pawnAData = undefined;
	var reg = /^-?[0-9]*(\.\d*)?$|^-?d^(\.\d*)?$/;
	
	var utils = require("utils");
	var view = Backbone.View.extend({
		el : "body",
		events : {
			"click #addPawn": "addPawn",    //点击新建关联按钮
			"click #addPawnBack": "addPawnBack",  //点击返回
			"click #openPawnSureModal": "openPawnSureModal", //点击确定
			"change #appGuaDebtInterAmt" : "appGuaDebtInterAmtOnChange", //修改本次申请担保债权金额
			"change #guarantyTypeCd" : "judgeGuarantyTypeCd",  //不同抵质押类型，查看的页面不同
		},
		initialize : function() {  //初始化
			this.render();
		},
		render : function() { //渲染
			this.initProjectPawnInfoTable();  //初始化抵质押信息列表
			this.initCollateralTable();    //初始化弹出抵质押担保表格
		},
		initProjectPawnInfoTable : function() {
			var oTable = $("#projectPawnInfoTable").dataTable({  //查询当前业务的抵质押信息
				sAjaxSource : $$ctx + "businessapplicationwd/searchProjectPawnInfoList",
				bFilter : false,
				bLengthChange : false,
				aoColumnDefs : [ {
					aTargets : [3],
					mRender : function(data, type, full) {
						return "人民币";
					}
				} , {
					aTargets : [9],
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
			
			$(document).on("click", "button[role='checkPawn']", function(e) {  //点击查看
				$.ajax({
					type : 'post',
					url : $$ctx + 'businessapplicationwd/checkPawn',
					data : {
						'id' : $(this).data("id")
					},
					success : function(r) { //控件设置为不可修改，并赋值
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
						
						$('#addEditDetailDiv').modal('show');  //显示抵质押信息框窗口
					}
				});
			});
			
			$(document).on("click", "button[role='deletePawn']", function(e) {  //点击删除
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
								$("#projectPawnInfoTable").dataTable().fnDraw();   //当前业务抵质押信息表格重绘
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
			$(document).on("click", "button[id='searchCollateral']", function(e) {  //查询
				var table = $("#table").dataTable();
				table.fnSettings()._iDisplayStart = 0;
				table.fnDraw();
			});
			$(document).on("click", "button[id='resetCollateral']", function(e) {   //重置
				$('#guarantorName').val('');
				$('#guarantyName').val('');
				$('#guarantyStatusCd').val('');
				$('#guaranteeType').val('');
				var table = $("#table").dataTable();
				table.fnSettings()._iDisplayStart = 0;
				table.fnDraw();
			});
			
			
			$(document).on("click", "button[id='addCollateral']", function(e) {  //新增
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
				submitHandler : function(form) {  //基本表单信息确定
					var appGuaDebtInterAmt = $('#appGuaDebtInterAmt').val();
					var applyAmt = utils.num.normal($('#applyAmt').val());
					if(appGuaDebtInterAmt > applyAmt) {
						utils.alert.warn("<strong>本次申请担保债权金额不能大于申请金额！</strong>");
					} else {
						$("#sure").attr({
							"disabled" : "disabled"
						});
						$.ajax({
							type : 'post',
							url : $$ctx + 'businessapplicationwd/saveProjectPawnInfo',  //保存当前业务抵质押信息
							data : {
								"guarantyId" : $('#guarantyId').val(),
								"appGuaDebtInterAmt" : $('#appGuaDebtInterAmt').val(),
								"pawnRatio" : $('#pawnRatio').val(),
								"projectId" : $('#projectId').val()
							},
							success : function(result) {
								$('#sure').removeAttr('disabled');
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
					}
				}
			});
		},
		addPawn : function() {  //点击新建关联按钮
			$('#guarantorName').val('');
			$('#guarantyName').val('');
			$('#guarantyStatusCd').val('');
			/**
			 * 初始化抵质押列表
			 */
			var table = $("#table").dataTable();
			table.fnSettings()._iDisplayStart = 0;
			table.fnDraw();
			
			$('#addPawn').slideUp();  //隐藏新建关联按钮
			$('#projectPawnInfoTableDiv').slideUp();  //隐藏抵质押信息列表
			$('#collateralTableDiv').slideDown(); //显示抵质押信息表格
		},
		addPawnBack : function() {  //点击返回
			$('#projectPawnInfoTableDiv').slideDown();  //显示抵质押列表
			$('#collateralTableDiv').slideUp();  //隐藏抵质押信息表格
			$('#addPawn').slideDown();  //显示新建关联按钮
		},
		openPawnSureModal : function() {  //点击确定
			if (pawnAData[5] <= pawnAData[6])
				utils.alert.warn("<strong>该抵质押物的已设定担保金额大于等于评估价值，不能关联</strong>");
			else if (pawnAData) {  //弹出基本表单弹框
				$('#pawnForm').resetForm();
				$('#evalValue').val(pawnAData[5]);
				$('#setGuarantyAmt').val(pawnAData[6]);
				$('#guarantyId').val(pawnAData[9]);
				$('#pawnModal').modal('show');
			}
		},
		judgeGuarantyTypeCd : function() {   //不同抵质押类型，查看页面控件显示不同
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
		appGuaDebtInterAmtOnChange : function() {  //修改本次申请担保债权金额
			var evalValue = $('#evalValue').val();
			var setGuarantyAmt = $('#setGuarantyAmt').val();
			var appGuaDebtInterAmt = $('#appGuaDebtInterAmt').val();
			if(appGuaDebtInterAmt.match(reg) != null) {
				var pawnRatio = appGuaDebtInterAmt / (evalValue - setGuarantyAmt);
				$('#pawnRatio').val(parseFloat(pawnRatio).toFixed(4));  //担保率保存四位小数
			}
		}
	});
	module.exports = view;
});