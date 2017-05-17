define(function(require, exports, module) {
	var reg = /^-?[0-9]*(\.\d*)?$|^-?d^(\.\d*)?$/;
	var integerReg =  /^\d+$/;
	
	var model = require("./model");
	var utils = require("utils");
	var rm = require("./rm");
	var view = Backbone.View.extend({
		el : "body",
		events : {
			"click  #showTree_": "ToggleTree",
			"change #repayingMode" : "judgeRepayingMode",//根据还款方式是否显示还款计划
			"change #repaymentBiz" : "judgeRepaymentBiz",//在贷款试算中根据还款方式，显示按钮和表格
			"click  #dkss" : "dkss",//贷款试算
			"click  #resetCountRateForm": "resetCountRateForm",//贷款试算重置
			"change #irTypeCd" : "judgeIrTypeCd",
			"change #irNegoSymbCd" : "floatRateOnChange",
			"change #ifInsure" : "ifInsureOnChange",
			"click button[role='dkss_deleteRepayPlan']" : "dkssDelete",//贷款试算中删除还款计划
			"click button[role='dkss_editRepayPlan']" : "dkssEdit",//贷款试算中修改还款计划
		    "click #addUserDefindPlan" : "dkssAdd",//贷款试算中新增还款计划
		    "click button[id='addRepayPlan']" : "addRepayPlan",//新增还款计划
		    "click button[role='editRepayPlan']" : "editRepayPlan",//修改还款计划
		    "click button[role='deleteRepayPlan']" : "deleteRepayPlan",//删除还款计划
		    "click button[id='batchInitRepayPlan']" : "batchInitRepayPlan"//批量初始化
		},
		initialize : function() {
			this.model = new model();
			this.render();
			$(".disabled").prop('disabled',true);
		},
		render : function() {
			this.initTree();
			this.initBasicForm();
			this.judgeRepaymentBiz();
			this.initRepaymentPlanTable();
			this.initCountRateForm();
			this.judgeIfInsure();
			this.prePremiumValidate();
			/** 
			 * 页面初始化时，根据利率类型判断是否显示调整周期和利率上浮比例
			 * libingqing 2015-8-7 16:00
			 * */
			this.judgeIrTypeCd();
		},
		initBasicForm: function(){
			var viewSelf=this;
			var $form = $("#basicInfoForm");
			// 选定贷款产品
			$('#productType').val($('#productCd').val());
			// 选定保险机构
			$('#insuranceOrgId').val($('#insuranceOrg').val());
			// 选中担保方式
			var guaranteeMode = $('#guaranteeMode').val();
			$.each(guaranteeMode.split(","), function(i,val) {
				var check = $('#basicInfoForm').find("input[type='checkbox'][value='" + val + "']");
				if(check[0])
					check[0].checked = true;
			});
			if(guaranteeMode) {
				// 根据担保方式确定哪些“新增关联”按钮可用
    			if(guaranteeMode.indexOf('1') >= 0 || guaranteeMode.indexOf('2') >= 0) {// 选中抵、质押
    				$('#addPawn').removeAttr('disabled');
    			}
    			if(guaranteeMode.indexOf('3') >= 0) {// 选中保证
    				$('#addBail').removeAttr('disabled');
    			}
			}
			/**
			 * 绑定勾选担保方式事件
			 */
			$(document).on("click", "#basicInfoForm input[type=checkbox]", function(e) {
				if($(this).is(':checked'))
					$('#guaranteeMode').val($('#guaranteeMode').val() + $(this).val() + ',');
				else
					$('#guaranteeMode').val($('#guaranteeMode').val().replace($(this).val() + ',',''));
				if($('#guaranteeMode').val().indexOf('1') >= 0 || $('#guaranteeMode').val().indexOf('2') >= 0 || 
						$('#guaranteeMode').val().indexOf('3') >= 0) {
					$('#ifInsure').attr('required', true);
				} else {
					$('#ifInsure').val(2);
					$('#insuranceOrgId').val('');
					$('#prePremium').val('');
					$('#ifInsure').removeAttr('required');
					$('#insuranceOrgId').removeAttr('required');
					$('#prePremium').removeAttr('required');
					$('#insureDetailDiv').slideUp();
				}
				$('#guaranteeMode').change();
			});
			//回显申报日期处理
			var date = $form.find("input[name='applyDates']").val().substring(0,11);
			$("#applyDates").val(date);
			//时间控件
			$.each($form.find(":input.input-datepicker"),function(i,v){
				$(v).datepicker({
					format : 'yyyy-mm-dd',
					todayHighlight : true,
					autoclose : true,
					clearBtn : true
				}).on("show", function() {
					$(".datepicker").css("z-index", "99999");
				});
			});
			//有保险则显示应收保费
			$form.find('select[name="ifInsure"]').change(
					function() {
						var sel2 = $("#ifInsure").val();
						if(sel2 =="1"){
							$("#insureDetailDiv").show();
						}else{
							$("#insureDetailDiv").hide();
						}
		    });
			setTimeout(function() {
				/** 获取婚姻状况，决定“配偶是否作为共同还款人”是否可选 */
				if(!$('#basicInfoForm #marriageCd').val() || $('#basicInfoForm #marriageCd').val() != '20') {// 非个人客户或非已婚客户
					$('#mateBorrower').val('2');
					$('#mateBorrower').attr('disabled', true);
				}
			}, 100);
			//授信下借款保存時校驗
			$form.validate({
				rules: rm.rules,
				messages: rm.messages,
				submitHandler: function(form) {
					if(!$(form).valid()){
						return;
					}
					viewSelf.saveUnderCreditLoan();
					return false; 
				}
			});
					
		},
		judgeIfInsure : function() {//根据担保方式显示保险信息
			if(($('#guaranteeMode').val().indexOf('1') >= 0 || $('#guaranteeMode').val().indexOf('2') >= 0 || 
					$('#guaranteeMode').val().indexOf('3') >= 0) && $('#ifInsure').val() == '1') {
				$('#ifInsure').attr('required', true);
				$('#insuranceOrgId').attr('required', true);
				$('#prePremium').attr('required', true);
				$('#insureDetailDiv').slideDown();
			}
		},
		prePremiumValidate : function() {//计算保险费用
			$(".prePremiumValidate").bind("change", function(e) {
				var data = {};
				//担保方式只选择了信用
				if($('#guaranteeMode').val()==('4') 
						|| $('#guaranteeMode').val()==('4,') 
						|| $('#guaranteeMode').val()==(',4')) {
					return;
				}
				//判断是否有保险一项选择否
				if($("#ifInsure").val() == "2") return;
				$.each($(".prePremiumValidate"), function(i, item) {
					//验证输入框是否符合校验规则
					if (!$(item).valid()) return;
					data[item.name] = item.value;
					if(item.name=="applyAmt"){
						var applyAmtValue = item.value;
						if(applyAmtValue.indexOf(",")>0) {
							data[item.name] = applyAmtValue.replace(/,/g,"");
						}
					}
				});
				$.ajax({
					type : 'post',
					url : $$ctx + "underCreditContractMng/getPrePremium",
					data : data,
					success : function(result) {
						$("#prePremium").val(result.toFixed(2));
					}
				});
			});
		},
		initTree: function() {
            /**初始化树*/
            var viewSelf = this;
            $.fn.zTree.init($("#tree_"), {//投放行业
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
                            $("#industryTypeCdField_").val("");
                            $("#industryCdMask_").val("");
                            $("#industryCd_").val("");
                            return false;
                        } else {
                            $("#industryTypeCdField_").val(treeNode.industryTypeCd);
                            $("#industryCdMask_").val(treeNode.industryTypeName);
                            $("#industryCd_").val(treeNode.industryTypeCd);
                            var treeObj = $.fn.zTree.getZTreeObj(treeId);
                            var node = treeObj.getNodeByParam("industryTypeCd", treeNode.industryTypeCd, null);
                            treeObj.checkNode(node, true, true);
                            var parentNode = node.getParentNode();
                            treeObj.expandNode(parentNode, true, false);
                            $("#controlZTree").toggle(300,function() {});
                        }
                    },
                    beforeCheck: function(treeId, treeNode) {
                    	 $("#industryTypeCdField_").val(treeNode.industryTypeCd);
                         $("#industryCd_").val(treeNode.industryTypeCd);
                        return ! treeNode.isParent;
                    },
                    onAsyncSuccess: function(event, treeId, treeNode, msg) {
                        var treeObj = $.fn.zTree.getZTreeObj(treeId);
                        var industryTypeCd = $("#industryCd_").val();
                        if (industryTypeCd !== "") {
                            var node = treeObj.getNodeByParam("industryTypeCd", industryTypeCd, null);
                            treeObj.checkNode(node, true, true);

                            var parentNode = node.getParentNode();
                            treeObj.expandNode(parentNode, true, false);
                            $("#industryCdMask_").val(node.industryTypeName);
                            $("#industryCd_").val(node.industryTypeCd);
                        }
                    }
                }
            });
            $.fn.zTree.init($("#cus_tree"), {//所属行业
				async : {
					enable : true,
					url : $$ctx + "bizapply/getAllIndustry"
				},
				data : {
					simpleData : {
						enable : true,
						idKey : "industryTypeCd",
						pIdKey : "parentIndustryTypeCd"
					},
					key : {
						name : "industryTypeName"
					}
				},
				check : {
					enable : true,
					chkStyle : "radio",
					radioType : "all"
				},
				callback : {
					beforeCheck : function(treeId, treeNode) {
						return !treeNode.isParent;
					},
					onAsyncSuccess : function(event, treeId, treeNode, msg) {
						var treeObj = $.fn.zTree.getZTreeObj(treeId);
						var industryTypeCd = $("#cus_industryTypeCdField").val();
						if (industryTypeCd !== "") {
							var node = treeObj.getNodeByParam("industryTypeCd",
									industryTypeCd, null);
							treeObj.checkNode(node, true, true);
							var parentNode = node.getParentNode();
							treeObj.expandNode(parentNode, true, false);
							$("#cus_industryCdMask").val(node.industryTypeName);
							$("#cus_industryCd").val(node.industryTypeCd);
						}
					}
				}
			});
        },
        ToggleTree: function() {
            /**显示隐藏投放行业的树*/
            $("#controlZTree").toggle(300,
            function() {
                if (($("#_controlZTree").attr("style")) == "") {} else if (($("#_controlZTree").attr("style")) == "display: none;") {}
            });
        },
        saveUnderCreditLoan: function(){	
        	var viewSelf = this;
        	var $form = $('#basicInfoForm');
//        	var applyAmt=$form.find(':input[name="applyAmt"]').val();
//			applyAmt=applyAmt.split(',').join("");
        	var $amt = $('#applyAmt');
			var applyAmt = utils.num.normal($amt.val());
			$amt.val(applyAmt);
			var param = $form.serialize();
			utils.button.ban("#saveBasicInfo");//禁用按钮
			viewSelf.model.saveUnderCreditLoan( param,function(r_data) {
				if(r_data){
					if(r_data.success){
						/*$("#saveBasicInfo").removeAttr("disabled");*/
						utils.num.thousands('#applyAmt');
						utils.alert.suc("<b>" + r_data.msg + "</b>", function(){
							window.location.href = $$ctx + "underCreditContractMng/editApplication/" + "1008" + "/" + $("#workflowId").val() + "/" + $("#taskId").val()+"/"+"100810";
							//utils.button.reset("#saveBasicInfo");//启用按钮
						});
					}
					else{
						utils.alert.warn("<b>" + r_data.msg + "</b>", function(){
                        	utils.button.reset("#saveBasicInfo");//启用按钮
                    	});
					}
				}
			});
        },
        judgeIrTypeCd : function() {    //根据利率类型改变利率信息中相应内容
			var irTypeCd = $('#irTypeCd').val();
			if (irTypeCd == '1') {
				$('#adjustPeriod').val('');
				$('#irNegoSymbCd').val('');
				$('#bizRate').addClass('required');
				$('#bizRate').addClass('isRate');
				$('#adjustPeriod').removeClass('required');
				$('#irNegoSymbCd').removeClass('required');
				$('#irNegoSymbCd').removeClass('isPercentNumberFour');
				$('#bizRate').removeAttr("readonly");
				$('#adjustPeriodAndFloatRateShowHide').hide();
			} else {
				$('#adjustPeriod').addClass('required');
				$('#irNegoSymbCd').addClass('required');
				$('#irNegoSymbCd').addClass('isPercentNumberFour');  //上浮比例校验：输入大于0且小于100的正整数或四位小数
				$('#adjustPeriodAndFloatRateShowHide').show();
				$('#bizRate').attr("readonly", "readonly");
				
			}
		},
		floatRateOnChange : function() {   //根据贷款期限，贷款期限单位，利率上浮比例计算得到相应年利率
			var applyTerm = $("#basicInfoForm").find(':input[name="applyTerm"]').val();	
			var applyTermUnit = $('#applyTermUnitSelect').val();
			var irNegoSymbCd = $('#irNegoSymbCd').val();
			var validateApplyTerm1 = /^[1-9]\d*$/.test(applyTerm);
			var validateApplyTerm2 = ((/^[1-9]\d*$/.test(applyTerm))&&(parseInt(applyTerm)>0&&parseInt(applyTerm)<=998));
			var validateIrNegoSymbCd = /^\d+(\.\d{1,4})?$/.test(irNegoSymbCd);
			if(!applyTerm || !applyTermUnit || !irNegoSymbCd || !validateApplyTerm1 || 
					!validateApplyTerm2 || !validateIrNegoSymbCd) {
			} else {
				$.ajax({
					type : 'POST',
					url : $$ctx + 'businessapplicationwd/countRateByFloatRate',
					data : {
						"applyTerm" : applyTerm,
						"applyTermUnit" : applyTermUnit,
						"floatRate" : irNegoSymbCd
					}, 
					success : function(bizRate) {
						$('#bizRate').val(bizRate.toFixed(2));
						$('#bizRate').change(); 
					}
				});
			}
		},
		initRepaymentPlanTable : function() {
			if($('#type').val() != 'check') {
				$("#repaymentPlanTable").dataTable({
					sAjaxSource : $$ctx + "underCreditContractMng/searchRepaymentPlanList",
					bFilter : false,
					bLengthChange : false,
					iDisplayLength : 15,
					aoColumnDefs : [ {
						aTargets : [ 2 ],
						mRender : function(data, type, full) {
							return "<div class='btn-group'style='width:100px;'><button title='修改' type='button' role='editRepayPlan' data-id='" + data + "' class='btn btn-xs btn-info'><i class='ace-icon fa fa-edit'></i></button>"+
								" <button title='删除' type='button' role='deleteRepayPlan' data-id='" + data + "' class='btn btn-xs btn-danger'><i class='ace-icon fa fa-trash-o'></i></button></div>";
						}
					} ],
					fnServerParams : function(aoData) {
						aoData.push({
							name : "projectNo",
							value : $('#projectNo').val()
						});
					}
				});
			} else {
				$("#repaymentPlanTable").dataTable({
					sAjaxSource : $$ctx + "underCreditContractMng/searchRepaymentPlanList",
					bFilter : false,
					bLengthChange : false,
					fnServerParams : function(aoData) {
						aoData.push({
							name : "projectNo",
							value : $('#projectNo').val()
						});
					}
				});
			}
			
			/**
			 * 初始化还款计划-开始日期,计划还款时间
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
			initDateP("#repayDate");
			initDateP("#startDate");
			initDateP("#dkss_repayDate");
			/** 初始化贷款试算-贷款时间 */
			initDateP("#loanStartDateBiz");
			$('#repayPlanForm').validate({//保存还款计划
				messages : rm.messages,
				submitHandler : function(form) {
					$("#sureRepayPlanForm").attr({
						"disabled" : "disabled"
					});
					$.ajax({
						type : 'post',
						url : $$ctx + 'underCreditContractMng/saveRepayPlan',
						data : $(form).serialize(),
						success : function(result) {
							$("#sureRepayPlanForm").removeAttr("disabled");
							if (result.success) {
								utils.alert.suc("<strong>" + result.msg + "</strong>");
								$('#repayPlanModal').modal('hide');
								var table = $("#repaymentPlanTable").dataTable();
								table.fnSettings()._iDisplayStart = 0;
								table.fnDraw();
								$("#dkss_repaymentPlanTable").dataTable().fnDraw();
								setTimeout(function() {
									if($('#repaymentPlanTable tbody tr').html().indexOf('没有符合条件的记录') <= 0)
										$("#batchInitRepayPlan").attr({
											"disabled" : "disabled"
										});
								}, 100);
							} else {
								utils.alert.err("<strong>" + result.msg + "</strong>");
							}
						}
					});
				}
			});
			
			$("#dkss_repaymentPlanTable").dataTable({//贷款试算中计划还款表格
				sAjaxSource : $$ctx + "underCreditContractMng/searchRepaymentPlanTempList",
				bFilter : false,
				bLengthChange : false,
				fnServerParams : function(aoData) {
					aoData.push({
						name : "projectNo",
						value : $('#projectNo').val()
					});
				},
				iDisplayLength : 15,
				aoColumnDefs : [ {
					aTargets : [ 2 ],
					mRender : function(data, type, full) {
						return "<div class='btn-group'style='width:100px;'><button title='修改' type='button' role='dkss_editRepayPlan' data-id='" + data + "' class='btn btn-xs btn-info'><i class='ace-icon fa fa-edit'></i></button>"+
							" <button title='删除' type='button' role='dkss_deleteRepayPlan' data-id='" + data + "' class='btn btn-xs btn-danger'><i class='ace-icon fa fa-trash-o'></i></button></div>";
					}
				} ]
			});
			
			$('#dkss_repayPlanForm').validate({//贷款试算中保存还款计划
				submitHandler : function(form) {
					$("#dkss_sureRepayPlanForm").attr({
						"disabled" : "disabled"
					});
					$.ajax({
						type : 'post',
						url : $$ctx + 'underCreditContractMng/saveRepayPlanTemp',
						data : $(form).serialize(),
						success : function(result) {
							$("#dkss_sureRepayPlanForm").removeAttr("disabled");
							if (result.success) {
								utils.alert.suc("<strong>" + result.msg + "</strong>");
								$('#dkss_repayPlanModal').modal('hide');
								var table = $("#dkss_repaymentPlanTable").dataTable();
								table.fnSettings()._iDisplayStart = 0;
								table.fnDraw();
							} else {
								utils.alert.err("<strong>" + result.msg + "</strong>");
							}
						}
					});
				}
			});
		},
		batchInitRepayPlan : function(e){
			var $btnSelf = $(e.currentTarget);
			var startDate = $('#startDate').val();
			var monthDate = $('#monthDate').val();
			var repayDateForCount = $('#repayDateForCount').val();

			var applyAmt = utils.num.normal($('#applyAmt').val());
			var applyTerm = $('#applyTermForBiz').val();
			var applyTermUnit = $('#applyTermUnitSelect').val();

			if(!startDate)
				utils.alert.warn("<strong>请选择开始日期！</strong>");
			else if(!monthDate)
				utils.alert.warn("<strong>请输入间隔月数！</strong>");
			else if(!repayDateForCount)
				utils.alert.warn("<strong>请输入每月还款日！</strong>");
			else if(monthDate.match(integerReg) == null)
				utils.alert.warn("<strong>间隔月数不合法！</strong>");
			else if(repayDateForCount.match(integerReg) == null)
				utils.alert.warn("<strong>每月还款日不合法！</strong>");
			else if(repayDateForCount <= 0 || repayDateForCount >= 31)
				utils.alert.warn("<strong>每月还款日不合法！</strong>");
			else if(!applyAmt)
				utils.alert.warn("<strong>请输入申请金额！</strong>");
			else if(applyAmt + "".match(reg) == null)
				utils.alert.warn("<strong>申请金额不合法！</strong>");
			else if(!applyTerm)
				utils.alert.warn("<strong>请输入贷款期限！</strong>");
			else if(applyTerm.match(integerReg) == null)
				utils.alert.warn("<strong>贷款期限不合法！</strong>");
			else if(!applyTermUnit)
				utils.alert.warn("<strong>请选择期限单位！</strong>");
			else {
				$("#batchInitRepayPlan").attr({
					"disabled" : "disabled"
				});
				$.ajax({
					type : 'post',
					url : $$ctx + 'underCreditContractMng/batchInitRepayPlan',
					data : {
						'projectId' : $('#underProjectId').val(),
						'applyAmt' : applyAmt,
						'applyTerm' : applyTerm,
						'applyTermUnit' : applyTermUnit,
						'startDate' : startDate,
						'monthDate' : monthDate,
						'repayDateForCount' : repayDateForCount
					},
					success : function(r) {
						if(r.success) {
							utils.alert.suc("<strong>" + r.msg + "</strong>");
							$("#repaymentPlanTable").dataTable().fnDraw();
							$("#dkss_repaymentPlanTable").dataTable().fnDraw();
						} else {
							utils.alert.err("<strong>" + r.msg + "</strong>");
							$("#batchInitRepayPlan").removeAttr("disabled");
						}
					}
				});
			}
		},
		addRepayPlan : function(e){
			var $btnSelf = $(e.currentTarget);
			$('#approvalHistoryRepayPlanId').val('');
			$('#repayPlanForm').resetForm();
			$('#repayPlanModal').modal('show');
		},
		editRepayPlan : function(e){
			var $btnSelf = $(e.currentTarget);
			var id = $btnSelf.data("id");
			$.ajax({
				type : 'post',
				url : $$ctx + 'underCreditContractMng/getRepayPlan',
				data : {
					'id' : id
				},
				success : function(repayPlan) {
					$.each($("#repayPlanForm").find("input"), function() {
						$(this).val(repayPlan[$(this).attr("name")]);
					});
					$('#repayPlanModal').modal('show');
				}
			});
		},
		deleteRepayPlan : function(e){
			var $btnSelf = $(e.currentTarget);
			var id = $btnSelf.data("id");
			utils.button.confirm(null, function(result) {
				if (result) {
					$.ajax({
						type : 'post',
						url : $$ctx + 'underCreditContractMng/deleteRepayPlan',
						data : {
							'id' : id
						},
						success : function(r) {
							if(r.success) {
								utils.alert.suc("<strong>" + r.msg + "</strong>");
								var table = $("#repaymentPlanTable").dataTable();
								table.fnSettings()._iDisplayStart = 0;
								table.fnDraw();
								setTimeout(function() {
									if($('#repaymentPlanTable tbody tr').html().indexOf('没有符合条件的记录') > 0)
										$("#batchInitRepayPlan").removeAttr('disabled');
								}, 100);
							} else {
								utils.alert.err("<strong>" + r.msg + "</strong>");
							}
						}
					});
				}
			});
		},
		dkssAdd : function(e){
			var $btnSelf = $(e.currentTarget);
			$('#dkss_repayPlanId').val('');
			$('#dkss_repayPlanForm').resetForm();
			$('#dkss_repayPlanModal').modal('show');
		},
		dkssEdit : function(e){
			var $btnSelf = $(e.currentTarget);
			var id = $btnSelf.data("id");
			$.ajax({
				type : 'post',
				url : $$ctx + 'underCreditContractMng/getRepayPlanTemp',
				data : {
					'id' : id
				},
				success : function(repayPlan) {
					$.each($("#dkss_repayPlanForm").find("input"), function() {
						$(this).val(repayPlan[$(this).attr("name")]);
					});
					$('#dkss_repayPlanModal').modal('show');
				}
			});
		},
		dkssDelete : function(e){
			var $btnSelf = $(e.currentTarget);
			var id = $btnSelf.data("id");
			utils.button.confirm(null, function(result) {
				if (result) {
					$.ajax({
						type : 'post',
						url : $$ctx + 'underCreditContractMng/deleteRepayPlanTemp',
						data : {
							'id' : id
						},
						success : function(r) {
							if(r.success) {
								utils.alert.suc("<strong>" + r.msg + "</strong>");
								var table = $("#dkss_repaymentPlanTable").dataTable();
								table.fnSettings()._iDisplayStart = 0;
								table.fnDraw();
							} else {
								utils.alert.err("<strong>" + r.msg + "</strong>");
							}
						}
					});
				}
			});
		},
		//是否有保险
		ifInsureOnChange : function() {
			if($('#ifInsure').val() == '1') {
				$('#insuranceOrgId').attr('required', true);
				$('#prePremium').attr('required', true);
				$('#insureDetailDiv').slideDown();
			} else {
				$('#insuranceOrgId').val('');
				$('#prePremium').val('');
				$('#insuranceOrgId').removeAttr('required');
				$('#prePremium').removeAttr('required');
				$('#insureDetailDiv').slideUp();
			}
		},
		judgeRepayingMode : function() {//根据还款方式是否显示还款计划
			var repayingMode = $('#repayingMode').val();
			if (repayingMode == '2') {//本息一次付清
				$('#replyPeriodNum').val('');
				$('#replyPeriodNumShowHide').hide();
				$('#repaymentPlanDiv').hide();
			} else if(repayingMode == '3') {//自定义还款
				$('#replyPeriodNum').val('');
				$('#replyPeriodNumShowHide').hide();
				
				$('#startDate').val('');
				$('#monthDate').val('');
				$('#repayDateForCount').val('');
				setTimeout(function() {
					if($('#repaymentPlanTable tbody tr').html().indexOf('没有符合条件的记录') <= 0)
						$("#batchInitRepayPlan").attr({
							"disabled" : "disabled"
						});
				}, 100);
				$('#repaymentPlanDiv').show();
			} else {//其他
				$('#replyPeriodNum').val($('#replyPeriodNumInput').val());
				$('#replyPeriodNumShowHide').show();
				$('#repaymentPlanDiv').hide();
			}
		},
        judgeRepaymentBiz : function() {//在贷款试算中根据还款方式，显示按钮和表格
			var repayment = $('#repaymentBiz').val();
			if(repayment == '3') {//自定义还款
				$('#dkss_repaymentPlanTable_div').slideDown();
				$('#resetCountRateForm').slideUp();
				$('#addUserDefindPlan').slideDown();
				$('#repaymentDateBiz').removeClass('required');
				$('#repaymentNumberBiz').removeClass('required');
				$("#tbld").empty();
				$("#tbfoot").empty();
			} else {//其他
				$('#dkss_repaymentPlanTable_div').slideUp();
				$('#resetCountRateForm').slideDown();
				$('#addUserDefindPlan').slideUp();
				$('#repaymentDateBiz').addClass('required');
				$('#repaymentNumberBiz').addClass('required');
			}
		},
        dkss : function() {//贷款试算
			var applyAmt = utils.num.normal($('#applyAmt').val());
			$('#countRateForm').resetForm();
			$('#loanAmountBiz').val(applyAmt);
			$('#applyTermBiz').val($('#applyTermForBiz').val());
			$('#countRateForm select[name=applyTermUnit]').val($('#basicInfoForm select[name=applyTermUnit]').val());
			$('#repaymentBiz').val($('#repayingMode').val());
			$('#countRateForm input[name=rate]').val($('#basicInfoForm input[name=bizRate]').val());
			$('#repaymentNumberBiz').val($('#replyPeriodNum').val());
			$('#loanStartDateBiz').val($('#applyDate').val());
			$("#tbld").empty();
			$("#tbfoot").empty();
			this.judgeRepaymentBiz();
			$('#dkssModal').modal('show');
		},
		resetCountRateForm : function() {
			var applyAmt = utils.num.normal($('#applyAmt').val());
			$('#countRateForm').resetForm();
			$('#loanAmountBiz').val(applyAmt);
			$('#applyTermBiz').val($('#applyTermForBiz').val());
			$('#repaymentBiz').val($('#repayingMode').val());
			$('#rateBiz').val($('#bizRate').val());
			$('#repaymentNumberBiz').val($('#replyPeriodNum').val());
			$("#tbld").empty();
			$("#tbfoot").empty();
		},
		initCountRateForm : function() {
			$('#countRateForm').validate({
				messages : rm.messages,
				submitHandler : function(form) {
					$.ajax({
						type : 'post',
						url : $$ctx + 'bizapply/loanCal',
						data : $(form).serialize(),
						success : function(result) {
							if (result.success) {
								var list = result.data;
		                		$("tr").remove("#newAdd");
		                		var currentPricipalInterestTotal = 0;
		                		var currentPricipalTotal = 0;
		                		var currentInterestTotal = 0;
								for(var i=0;i<list.length;i++){
									currentPricipalInterestTotal += list[i].currentPricipalInterest;
									currentPricipalTotal += list[i].currentPricipal;
									currentInterestTotal += list[i].currentInterest;
									$("#tbld").append("<tr id='newAdd'>"+"<td>"+ list[i].repaymentNumber +"</td>"+"<td>"+ list[i].repaymentDate +"</td>"+
														 "<td>"+ list[i].currentPricipalInterest +"</td>"+"<td>"+list[i].currentPricipal +"</td>"+
														 "<td>"+ list[i].currentInterest +"</td>"+"<td>"+list[i].endCurrentPricipal +"</td>"+
														 "<td>"+list[i].endCurrentPrincipalBalance  +"</td>"+"</tr>");
								}
								$("#tbfoot").append("<tr id='newAdd'>" +
										"<th></th>" +
										"<th>总计：</th>" +
										"<th>"+currentPricipalInterestTotal.toFixed(2)+"</th>" +
										"<th>"+currentPricipalTotal.toFixed(2)+"</th>" +
										"<th>"+currentInterestTotal.toFixed(2)+"</th>" +
										"<th></th>" +
										"<th></th>" +
										"</tr>");
							} else {
								var msg = "<strong>因未知错误，计算失败！</strong>";
								if(result.msg)
									msg = result.msg;
								utils.alert.err(msg);
							}
						}
					});
				}
			});
		}
	});
	module.exports = view;
});