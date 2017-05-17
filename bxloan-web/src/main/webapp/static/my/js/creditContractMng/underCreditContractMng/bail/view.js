define(function(require, exports, module) {
	var bailAData = undefined;
	var collateralAData = undefined;
	var reg = /^-?[0-9]*(\.\d*)?$|^-?d^(\.\d*)?$/;
	var integerReg =  /^\d+$/;
	var rm="";
	var utils = require("utils");
	var view = Backbone.View.extend({
		el : "body",
		events : {
			/** 新增保证人*/
			"click #addBail": "addBail",
			/** 返回普通列表*/
			"click #addBailBack": "addBailBack",
			/** 查看保证人信息*/
			"click button[role='checkBail']": "checkBail",
			/** 删除保证人*/
			"click button[role='deleteBail']": "deleteBail",
			/** 查询保证人*/
			"click button[id='searchCustomerForBail']": "searchCustomerForBail",
			/** 清空保证人搜索条件*/
			"click button[id='resetCustomerForBail']": "resetCustomerForBail",
			"click #openBailSureModal": "openBailSureModal",
			"click #addqyCustomer": "addqyCustomer",
			"click #addgrCustomer": "addgrCustomer"
		},
		initialize : function() {
			this.render();
		},
		render : function() {
			/** 保证人担保 */
			this.initBailTable();
			this.initCustomerTableForBail();
		},
		initBailTable : function() {
			var viewSelf = this;
			var oTable = $("#bailTable").dataTable({
				sAjaxSource : $$ctx + "underCreditContractMng/searchBailList",
				bFilter : false,
				bLengthChange : false,
				aoColumnDefs : [ {
					aTargets : [ 5 ],
					mRender : function(data, type, full) {
						var button = "<div class='btn-group'><button title='查看' type='button' role='checkBail' data-id='" + data + "' class='btn btn-xs btn-yellow'><i class='ace-icon fa fa-eye'></i></button>";
						if($('#type').val() != 'check' ){
							if($("#underProjectId").val()==full[6])
								button += "<button title='删除' type='button' role='deleteBail' data-id='" + data + "' class='btn btn-xs btn-danger'><i class='ace-icon fa fa-trash-o'></i></button></div>";
						}
						return button;
					}
				} ],
				fnServerParams : function(aoData) {
					aoData.push({
						name : "workflowId",
						value : $('#workflowId').val()
					});
				}
			});
			viewSelf.oTable = oTable;
		},
		checkBail:function(e){
			var viewSelf = this;
			var $btnSelf = $(e.currentTarget);
			var id = $btnSelf.data("id");
			$.ajax({
				type : 'post',
				url : $$ctx + 'underCreditContractMng/getPartyByProjectAssurerInfoId',
				data : {
					"projectAssurerInfoId" : id
				},
				success : function(party) {
					var url = '';
					if(party.partyTypeCd == '1')
						url = $$ctx + 'corpcustomer/showDetail/' +party.partyId+ '?consultLocation=business';
					else
						url = $$ctx + 'singleCustomer/backToForm?customerId=' + party.partyId +
						'&workCode=TODETAIL&customerSource=detail&consultLocation=contract';
					
					$('#bailIframe').attr('src' , url);
					$('#bailDetailDiv').modal('show');
				}
			});
		},
		deleteBail:function(e){
			var viewSelf = this;
			var $btnSelf = $(e.currentTarget);
			var id = $btnSelf.data("id"); 
			utils.button.confirm(null, function(result) {
				if (result) {
					$.ajax({
						type : 'post',
						url : $$ctx + 'underCreditContractMng/deleteAssurer',
						data : {
							'id' : id
						},
						success : function(r) {
							if(r.success) {
								utils.alert.suc("<strong>" + r.msg + "</strong>");
								$("#bailTable").dataTable().fnDraw();
								$("#commonBorrowerTable").dataTable().fnDraw();
							} else {
								utils.alert.err("<strong>" + r.msg + "</strong>");
							}
						}
					});
				}
			});
		},
		initCustomerTableForBail : function() {
			$('#customerNameForBail').val('');
			$('#customerNumForBail').val('');
			$('#certificateTypeCdForBail').val('');
			$('#certificateNumForBail').val('');
			
			/**
			 * 初始化客户列表
			 */
			var viewSelf = this;
			var oTable = $("#customerTableForBail").dataTable({//条件查询
				sAjaxSource : $$ctx + "underCreditContractMng/searchCustomerForBail",
				bFilter : false,
				bLengthChange : false,
				aoColumnDefs : [ {
					aTargets : [ 0 ],
					mRender : function(data, type, full) {
						return "<label class='checkbox-inline'><input type='radio' name='checkbox' " +
								"class='ace add_corp_Type form-control' /><span class='lbl'></span></label>";
					}
				} , {
					aTargets : [ 5 ],
					mRender : function(data, type, full) {
						if(data && data != '') {
							switch (data) {
							case "1":
								data = '未生效';
								break;
							case "2":
								data = '生效';
								break;
							default:
								data = '数据未填写';
							}
						}
						return data;
					}
				} , {
					aTargets : [ 6 ],
					mRender : function(data, type, full) {
						var datas = data.split(',');
						if(datas && datas.length > 1) {
							return '借款人 / 担保人';
						}else {
							switch (data) {
							case "01":
								data = '借款人';
								break;
							case "02":
								data = '担保人';
								break;
							}
						}
						return data;
					}
				} ],
				fnServerParams : function(aoData) {
					aoData.push({
						name : "customerName",
						value : $('#customerNameForBail').val()
					}, {
						name : "customerNum",
						value : $('#customerNumForBail').val()
					}, {
						name : "certificateTypeCd",
						value : $('#certificateTypeCdForBail').val()
					}, {
						name : "certificateNum",
						value : $('#certificateNumForBail').val()
					}, {
						name : "partyId",
						value : $('#creditPartyId').val()
					}, {
						name : "projectId",
						value : $('#creditProjectId').val()
					});
				},
				fnDrawCallback : function() {
					bailAData = undefined;
				}
			});
			viewSelf.oTable = oTable;
			
			/**
			 * 绑定客户列表行单击事件
			 */
			$(document).on("click", "#customerTableForBail tbody tr", function(e) {
				bailAData = oTable.fnGetData(this);
				for ( var i in bailAData) {
					if (bailAData[i] == null)
						bailAData[i] = '';
				}
				var b5 = '';
				var b6 = '';
				switch (bailAData[5]) {
				case "1":
					b5 = '未生效';
					break;
				case "2":
					b5 = '生效';
					break;
				default:
					b5 = '数据未填写';
				}
				var datas = bailAData[6].split(',');
				if(datas && datas.length > 1) {
					b6 = '借款人 / 担保人';
				}else {
					switch (bailAData[6]) {
					case "01":
						b6 = '借款人';
						break;
					case "02":
						b6 = '担保人';
						break;
					}
				}
				this.innerHTML="<td><label class='checkbox-inline'><input type='radio' checked='true' name='checkbox' " +
						"class='ace add_corp_Type form-control' /><span class='lbl'></span></label></td>"+
						"<td>"+bailAData[1]+"</td>"+"<td>"+bailAData[2]+"</td>"+"<td>"+bailAData[3]+"</td>"+
						"<td>"+bailAData[4]+"</td>"+"<td>"+b5+"</td>"+"<td>"+b6+"</td>";
			});
			/**
			 * 初始化bail表单
			 */
			$('#bailForm').validate({
				messages : rm.messages,
				submitHandler : function(form) {
					$("#sureBailForm").attr({
						"disabled" : "disabled"
					});
					utils.button.ban("#sureBailForm");//禁用按钮
					$.ajax({
						type : 'post',
						url : $$ctx + 'underCreditContractMng/saveProjectAssurerInfo',
						data : {
							'projectId' : $('#underProjectId').val(),
							'partyId' : bailAData[7],
							'guaranteeAmt' : $('#guaranteeAmtForBailForm').val(),
							'bailMateBorrower' : $("#bailMateBorrower").val()
						},
						success : function(result) {
							$("#sureBailForm").removeAttr("disabled");
							if (result.success) {
								utils.alert.suc("<b>" + result.msg + "</b>", function(){
		                        	utils.button.reset("#sureBailForm");//启用按钮
		                    	});
								$('#bailModal').modal('hide');
								$(form).resetForm();
								var table = $("#bailTable").dataTable();
								table.fnSettings()._iDisplayStart = 0;
								table.fnDraw();
								table = $("#customerTableForBail").dataTable();
								table.fnSettings()._iDisplayStart = 0;
								table.fnDraw();
								$("#commonBorrowerTable").dataTable().fnDraw();
								bailAData = undefined;
								$('#customerTableDiv').hide();
								$('#bailTableDiv').show();
								$('#addBail').show();
							} else {
								utils.alert.warn("<b>" + result.msg + "</b>", function(){
		                        	utils.button.reset("#sureBailForm");//启用按钮
		                    	});
							}
						}
					});
				}
			});
			/** 
			 * 创建企业客户
			 */
			$('#qyCustomerForm').validate({
				submitHandler: function(form) {
	                $.ajax({
	                    cache: false,
	                    type: "POST",
	                    url: $$ctx + "corpcustomer/addEntCustomer",
	                    data: $(form).serialize(),
	                    success: function(result) {
	                    	if(result.success){
	                    		var table = $("#customerTableForBail").dataTable();
                				table.fnSettings()._iDisplayStart = 0;
                				table.fnDraw();
                				$('#qyModal').modal('hide');
                				$('#corpCustomerDetailIframe').attr('src' , $$ctx + "corpcustomer/toEdit/" + result.data + "?consultLocation=businessAdd");
                                $('#corpCustomerDetailDiv').modal('show');
                                
                                $("#corpCustomerDetailIframe").load(function() {
                   					var clientHeight = $("#corpCustomerDetailIframe").contents().find("body").height();
                 	        	    $("#corpCustomerDetailIframe").attr("height",clientHeight+"px!important;");
                 	        	    
             	        	    	var timer = setInterval(function(){
             	        	    		var $iframe =  $($('#corpCustomerDetailIframe')[0].contentWindow.document.body); 
                 	        	    	var value = $iframe.find("#consultLocation").val();
                 	        	    	if(value == "close") {
                 	        	    		$("#customerTableForBail").dataTable().fnDraw();
                 	        	    		$('#corpCustomerDetailDiv').modal('hide');
                 	        	    		clearInterval(timer);
                 	        	    	}
                    	        	 },100);
	                   			});
	                    	}else{
	                        	utils.alert.err("<strong>" + result.msg + "</strong>");
	                        }
	                    }
	                });
	                return false;
	            }
			});
			/** 
			 * 创建个人客户
			 */
			$("#grCustomerForm").validate({
                rules: rm.rules,
                messages: rm.messages,
                submitHandler: function(form) {
                    $.ajax({
                        cache: false,
                        type: "POST",
                        url: $$ctx + "singleCustomer/saveSimple",
                        data: $(form).serialize(),
                        async: false,
                        error: function(request) {
                            alert("add-simple-submit报错" + request);
                        },
                        success: function(data) {
                            if (data == "CeritificateRepeatedError") {
                                utils.alert.warn("<strong>证件号码已被登记过！</strong>");
                            } 
                            else{
                            	$('#grModal').modal('hide');
                                $('#singleCustomerDetailIframe').attr('src' , $$ctx + data + '&business=business');
                                $('#singleCustomerDetailDiv').modal('show');
                                
                                $("#singleCustomerDetailIframe").load(function() {
                                	var clientHeight = $("#singleCustomerDetailIframe").contents().find("body").height();
              	        	    	$("#singleCustomerDetailIframe").attr("height",clientHeight+"px!important;");
              	        	    	
                    				var timer = setInterval(function(){
	              	        	    	var $iframe =  $($('#singleCustomerDetailIframe')[0].contentWindow.document.body); 
	              	        	    	var value = $iframe.find("#businessField").val();
	              	        	    	if(value == "close") {
	              	        	    		$("#customerTableForBail").dataTable().fnDraw();
	              	        	    		$('#singleCustomerDetailDiv').modal('hide');
	              	        	    		clearInterval(timer);
	              	        	    	}
                    	        	},100);
                    			});
                            }

                        }
                    });
                    return false;
                }
            });
		},
		searchCustomerForBail:function(e){
			var viewSelf = this;
			var $btnSelf = $(e.currentTarget);
			var table = $("#customerTableForBail").dataTable();
			table.fnSettings()._iDisplayStart = 0;
			table.fnDraw();
		},
		resetCustomerForBail:function(e){
			var viewSelf = this;
			var $btnSelf = $(e.currentTarget);
			$('#customerNameForBail').val('');
			$('#customerNumForBail').val('');
			$('#certificateTypeCdForBail').val('');
			$('#certificateNumForBail').val('');
			var table = $("#customerTableForBail").dataTable();
			table.fnSettings()._iDisplayStart = 0;
			table.fnDraw();
		},
		addBail : function() {
			$('#customerNameForBail').val('');
			$('#customerNumForBail').val('');
			$('#certificateTypeCdForBail').val('');
			$('#certificateNumForBail').val('');
			
			/**
			 * 初始化客户列表
			 */
			var table = $("#customerTableForBail").dataTable();
			table.fnSettings()._iDisplayStart = 0;
			table.fnDraw();
			
			$('#bailTableDiv').slideUp();
			$('#customerTableDiv').slideDown();
			$('#addBail').slideUp();
		},
		addBailBack : function() {
			$('#customerTableDiv').slideUp();
			$('#bailTableDiv').slideDown();
			$('#addBail').slideDown();
		},
		openBailSureModal : function() {
			if (bailAData) {
				$.ajax({
					type : "POST",
					url : $$ctx + "underCreditContractMng/isMarried",
					data : {
						partyId : bailAData[7]
					},
					success : function(result) {
						var selector = "#bailForm select[name='bailMateBorrower']";
						if(result.success) {
							$(selector).val("2");
							$(selector).removeAttr("disabled");
						} else {
							$(selector).val("2");
							$(selector).attr("disabled", true);
						}
						var applyAmt = utils.num.normal($('#applyAmt').val());
						$('#customerNumForBailForm').val(bailAData[2]);
						$('#customerNameForBailForm').val(bailAData[1]);
						$('#guaranteeAmtForBailForm').val(applyAmt);
						$('#partyIdForBail').val(bailAData[7]);
						$('#bailModal').modal('show');
					}
				});
				
			}
		},
		addqyCustomer : function() {
			$('#qyCustomerForm').resetForm();
			$('#qyModal').modal('show');
		},
		addgrCustomer : function() {
			$('#grCustomerForm').resetForm();
			$('#grModal').modal('show');
		}
	});
	module.exports = view;
});