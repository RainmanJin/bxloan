define(function(require, exports, module) {
	var bailAData = undefined;
	var collateralAData = undefined;
	var reg = /^-?[0-9]*(\.\d*)?$|^-?d^(\.\d*)?$/;
	var integerReg =  /^\d+$/;
	
	var model = require("../main/model");
	var rm = require("../main/rm");
	var utils = require("utils");
	var view = Backbone.View.extend({
		el : "body",
		events : {
			"click #addBail": "addBail",
			"click #addBailBack": "addBailBack",
			"click #openBailSureModal": "openBailSureModal",
			"click #addqyCustomer": "addqyCustomer",
			"click #addgrCustomer": "addgrCustomer"
		},
		initialize : function() {
			this.model = new model();
			this.render();
		},
		render : function() {
			/** 保证人担保 */
			this.initBailTable();
			this.initCustomerTableForBail();
		},
		initBailTable : function() {
			var viewSelf = this;
			var assurerSources=['03','04'];
			var unIds = $("#unIds").val();
			var oTable = $("#bailTable").dataTable({
				sAjaxSource : $$ctx + "businessapplicationwd/searchBailList",
				bFilter : false,
				bLengthChange : false,
				aoColumnDefs : [ {
					aTargets : [ 5 ],
					mRender : function(data, type, full) {
						var button = "<div class='btn-group'style='width:100px;'><button title='查看' type='button' role='checkBail' data-id='" + data + "' class='btn btn-xs btn-yellow'><i class='ace-icon fa fa-eye'></i></button>";
						if($('#type').val() != 'check'&&$.inArray(full[6], assurerSources)<0)
							button += "<button title='删除' type='button' role='deleteBail' data-id='" + data + "' class='btn btn-xs btn-danger'><i class='ace-icon fa fa-trash-o'></i></button></div>";
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
			viewSelf.oTable = oTable;
			
			$(document).on("click", "button[role='checkBail']", function(e) {
				var id = $(this).data("id");
				$.ajax({
					type : 'post',
					url : $$ctx + 'businessapplicationwd/getPartyByProjectAssurerInfoId',
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
			});
			
			$(document).on("click", "button[role='deleteBail']", function(e) {
				var id = $(this).data("id");
				utils.button.confirm(null, function(result) {
					if (result) {
						$.ajax({
							type : 'post',
							url : $$ctx + 'businessapplicationwd/deleteAssurer',
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
			var oTable = $("#customerTableForBail").dataTable({
				sAjaxSource : $$ctx + "businessapplicationwd/searchCustomerForBail",
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
						value : $('#partyId').val()
					}, {
						name : "projectId",
						value : $('#projectId').val()
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
			 * 绑定查询、重置、新增抵质押按钮
			 */
			$(document).on("click", "button[id='searchCustomerForBail']", function(e) {
				var table = $("#customerTableForBail").dataTable();
				table.fnSettings()._iDisplayStart = 0;
				table.fnDraw();
			});
			$(document).on("click", "button[id='resetCustomerForBail']", function(e) {
				$('#customerNameForBail').val('');
				$('#customerNumForBail').val('');
				$('#certificateTypeCdForBail').val('');
				$('#certificateNumForBail').val('');
				var table = $("#customerTableForBail").dataTable();
				table.fnSettings()._iDisplayStart = 0;
				table.fnDraw();
			});
			$(document).on("click", "button[id='addCustomerForBail']", function(e) {
				
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
					$.ajax({
						type : 'post',
						url : $$ctx + 'businessapplicationwd/saveProjectAssurerInfo',
						data : {
							'projectId' : $('#projectId').val(),
							'partyId' : bailAData[7],
							'guaranteeAmt' : $('#guaranteeAmtForBailForm').val(),
							'bailMateBorrower' : $("#bailMateBorrower").val()
						},
						success : function(result) {
							$("#sureBailForm").removeAttr("disabled");
							if (result.success) {
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
								utils.alert.err("<strong>" + result.msg + "</strong>");
							}
						}
					});
				}
			});
			
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
					url : $$ctx + "businessapplicationwd/isMarried",
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