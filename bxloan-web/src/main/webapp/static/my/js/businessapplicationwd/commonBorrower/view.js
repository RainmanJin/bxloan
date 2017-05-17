define(function(require, exports, module) {
	var commonBorrowerAData = undefined;
	
	var model = require("../main/model");
	var rm = require("../main/rm");
	var utils = require("utils");
	var view = Backbone.View.extend({
		el : "body",
		events : {
			"click #addCommonBorrower": "addCommonBorrower",
			"click #addCommonBorrowerBack": "addCommonBorrowerBack",
			"click #newCommonBorrower": "newCommonBorrower",
			"click #detailCommonBorrowerBack": "detailCommonBorrowerBack",
			"click #openCommonBorrowerSureModal": "openCommonBorrowerSureModal"
		},
		initialize : function() {
			this.model = new model();
			this.render();
		},
		render : function() {
			/** 共同借款人 */
			this.initCommonBorrowerTable();
			this.initCustomerTableForCommonBorrower();
			this.initNewCommonBorrowerForm();
            this.initNationAreaCascade();
		},
		initCommonBorrowerTable : function() {
			var viewSelf = this;
			var oTable = $("#commonBorrowerTable").dataTable({
				sAjaxSource : $$ctx + "businessapplicationwd/searchCommonBorrowerList",
				bFilter : false,
				bLengthChange : false,
				aoColumnDefs : [ {
					aTargets : [ 6 ],
					mRender : function(data, type, full) {
						if (full[7] == 1)// 配偶
							return "<div class='btn-group'style='width:100px;'><button title='查看' type='button' role='checkCommonBorrower' data-id='" + data + "," + full[7] + "' class='btn btn-xs btn-yellow'><i class='ace-icon fa fa-eye'></i></button></div>";
						else
							var button = "<div class='btn-group'style='width:100px;'><button title='查看' type='button' role='checkCommonBorrower' data-id='" + data + "' class='btn btn-xs btn-yellow'><i class='ace-icon fa fa-eye'></i></button>";
							if($('#type').val() != 'check')
								button += "<button title='删除' type='button' role='deleteCommonBorrower' data-id='" + data + "' class='btn btn-xs btn-danger'><i class='ace-icon fa fa-trash-o'></i></button></div>";
							return button;
					}
				}, {
					aTargets : [ 7 ],
					bVisible : false
				} ],
				fnServerParams : function(aoData) {
					aoData.push({
						name : "projectId",
						value : $('#projectId').val()
					});
				}
			});
			viewSelf.oTable = oTable;
			
			$(document).on("click", "button[role='checkCommonBorrower']", function(e) {
				var url = $$ctx + 'businessapplicationwd/';
				var data = {};
				var id = $(this).data("id") + '';
				if (id.indexOf(',') > 0) {// 配偶
					url += 'getSpouseByCommonBorrowerId';
					data = {"commonBorrowerId" : id.split(',')[0]};
				} else {
					url += 'getPartyByCommonBorrowerId';
					data = {"commonBorrowerId" : id};
				}
				$.ajax({
					type : 'post',
					url : url,
					data : data,
					success : function(person) {
						if (id.indexOf(',') > 0) {// 配偶
							$.ajax({
			                    cache: true,
			                    type: "POST",
			                    url: $$ctx + "singleCustomer/findOneFamilyFriend",
			                    data: {
			                        "correlativeRelationsId": person.correlativeRelationsId
			                    },
			                    async: true,
			                    error: function(request) {
			                        alert("查找单一账户信息报错" + request);
			                    },
			                    success: function(data) {
			                        if (data != null) {
			                            $("#hiddenForLxrWorkType").val("MOD");
			                            $("#modFamilyFriendForm select[name='relationsType']").val(data["relationsType"]);
		                                var content = $("#peiou").html();
		                                $("#mod-formContent")[0].innerHTML = content;
			                            $.each($("#modFamilyFriendForm").find("input[type='text'], select, textarea"),
			                            function() {
			                                $(this).val(data[$(this).attr("name")]);
			                                $(this).attr("readonly", "readonly");
			                            });
			                            $.each($("#modFamilyFriendForm").find("input[type='radio'], select, textarea"),
			                            function() {
			                                $(this).attr("disabled", "disabled");
			                            });
			                            $("#modLxrSpan").hide();
			                            $("#mod-modal-formLxr div.modal-header h4").html("<i class='ace-icon fa fa-plus'></i> 配偶信息");
			                            $("#mod-modal-formLxr").modal("show");
			                        } else {
			                            utils.alert.err("<strong>因未知错误，获取信息失败！</strong>");
			                        }
			                    }
			                });
						} else {
							var src = '';
							if(person.partyTypeCd == '1')// 企业客户
								src = $$ctx + 'corpcustomer/showDetail/' +person.partyId+ '?consultLocation=business';
							else
								src = $$ctx + 'singleCustomer/backToForm?customerId=' + person.partyId +
										'&workCode=TODETAIL&customerSource=detail&consultLocation=contract';
							
							$('#commonBorrowerIframe').attr('src' , src);
							$('#commonBorrowerDetailDiv').modal('show');
						}
					}
				});
			});
			
			$(document).on("click", "button[role='deleteCommonBorrower']", function(e) {
				var id = $(this).data("id");
				utils.button.confirm(null, function(result) {
					if (result) {
						$.ajax({
							type : 'post',
							url : $$ctx + 'businessapplicationwd/deleteCommonBorrow',
							data : {
								'id' : id
							},
							success : function(r) {
								if(r.success) {
									utils.alert.suc("<strong>" + r.msg + "</strong>");
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
		initCustomerTableForCommonBorrower : function() {
			$('#customerNameForCommonBorrower').val('');
			$('#customerNumForCommonBorrower').val('');
			$('#certificateTypeCdForCommonBorrower').val('');
			$('#certificateNumForCommonBorrower').val('');
			
			var viewSelf = this;
			var oTable = $("#customerTableForCommonBorrower").dataTable({
				sAjaxSource : $$ctx + "businessapplicationwd/searchCustomerForCommonBorrower",
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
						value : $('#customerNameForCommonBorrower').val()
					}, {
						name : "customerNum",
						value : $('#customerNumForCommonBorrower').val()
					}, {
						name : "certificateTypeCd",
						value : $('#certificateTypeCdForCommonBorrower').val()
					}, {
						name : "certificateNum",
						value : $('#certificateNumForCommonBorrower').val()
					}, {
						name : "partyId",
						value : $('#partyId').val()
					}, {
						name : "projectId",
						value : $('#projectId').val()
					});
				},
				fnDrawCallback : function() {
					commonBorrowerAData = undefined;
				}
			});
			viewSelf.oTable = oTable;
			
			/**
			 * 绑定客户列表行单击事件
			 */
			$(document).on("click", "#customerTableForCommonBorrower tbody tr", function(e) {
				commonBorrowerAData = oTable.fnGetData(this);
				for ( var i in commonBorrowerAData) {
					if (commonBorrowerAData[i] == null)
						commonBorrowerAData[i] = '';
				}
				var b5 = '';
				var b6 = '';
				switch (commonBorrowerAData[5]) {
				case "1":
					b5 = '未生效';
					break;
				case "2":
					b5 = '生效';
					break;
				default:
					b5 = '数据未填写';
				}
				var datas = commonBorrowerAData[6].split(',');
				if(datas && datas.length > 1) {
					b6 = '借款人 / 担保人';
				}else {
					switch (commonBorrowerAData[6]) {
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
						"<td>"+commonBorrowerAData[1]+"</td>"+"<td>"+commonBorrowerAData[2]+"</td>"+"<td>"+commonBorrowerAData[3]+"</td>"+
						"<td>"+commonBorrowerAData[4]+"</td>"+"<td>"+b5+"</td>"+"<td>"+b6+"</td>";
			});
			
			/**
			 * 绑定查询、重置、新增抵质押按钮
			 */
			$(document).on("click", "button[id='searchForCommonBorrower']", function(e) {
				var table = $("#customerTableForCommonBorrower").dataTable();
				table.fnSettings()._iDisplayStart = 0;
				table.fnDraw();
			});
			$(document).on("click", "button[id='resetForCommonBorrower']", function(e) {
				$('#customerNameForCommonBorrower').val('');
				$('#customerNumForCommonBorrower').val('');
				$('#certificateTypeCdForCommonBorrower').val('');
				$('#certificateNumForCommonBorrower').val('');
				var table = $("#customerTableForCommonBorrower").dataTable();
				table.fnSettings()._iDisplayStart = 0;
				table.fnDraw();
			});
		},
		addCommonBorrower : function() {
			$('#customerNameForCommonBorrower').val('');
			$('#customerNumForCommonBorrower').val('');
			$('#certificateTypeCdForCommonBorrower').val('');
			$('#certificateNumForCommonBorrower').val('');
			
			/**
			 * 初始化客户列表
			 */
			var table = $("#customerTableForCommonBorrower").dataTable();
			table.fnSettings()._iDisplayStart = 0;
			table.fnDraw();
			
			$('#commonBorrowerTableDiv').slideUp();
			$('#customerTableDivForCommonBorrower').slideDown();
			$('#addCommonBorrower').slideUp();
			$('#newCommonBorrower').slideUp();
		},
		addCommonBorrowerBack : function() {
			$('#customerTableDivForCommonBorrower').slideUp();
			$('#commonBorrowerTableDiv').slideDown();
			$('#addCommonBorrower').slideDown();
			$('#newCommonBorrower').slideDown();
		},
		newCommonBorrower : function() {
			$('#addCertificateType').val('100');
			$('#cbdegreeCd').val('');
			$('#cbemploymentType').val('');
			$('#newCommonBorrowerModal').modal('show');
		},
		detailCommonBorrowerBack : function() {
			$('#customerDetailForCommonBorrowerDiv').hide();
			$('#addCommonBorrower').show();
			$('#commonBorrowerTableDiv').show();
		},
		openCommonBorrowerSureModal : function() {
			if (commonBorrowerAData) {
				$("#openCommonBorrowerSureModal").attr({
					"disabled" : "disabled"
				});
				$.ajax({
					type : 'post',
					url : $$ctx + 'businessapplicationwd/saveCommonBorrower',
					data : {
						'projectId' : $('#projectId').val(),
						'partyId' : commonBorrowerAData[7]
					},
					success : function(result) {
						if (result.success) {
							var table = $("#commonBorrowerTable").dataTable();
							table.fnSettings()._iDisplayStart = 0;
							table.fnDraw();
							table = $("#customerTableForCommonBorrower").dataTable();
							table.fnSettings()._iDisplayStart = 0;
							table.fnDraw();
							$('customerTableForCommonBorrower input[type=radio]').removeAttr('checked');
							commonBorrowerAData = undefined;
							$('#customerTableDivForCommonBorrower').hide();
							$('#commonBorrowerTableDiv').show();
							$('#addCommonBorrower').show();
							$('#newCommonBorrower').show();
						} else {
							utils.alert.err("<strong>" + result.msg + "</strong>");
						}
						$('#openCommonBorrowerSureModal').removeAttr('disabled');
					}
				});
			}
		},
		/** 新建共同借款人功能 */
		initNewCommonBorrowerForm : function() {
			$('#newCommonBorrowerForm').validate({
				rules: rm.rules,
				submitHandler : function(form) {
					$("#saveNewCommonBorrower").attr({
						"disabled" : "disabled"
					});
					$.ajax({
						type : 'post',
						url : $$ctx + 'businessapplicationwd/saveNewCommonBorrower',
						data : $(form).serialize(),
						success : function(result) {
							$("#saveNewCommonBorrower").removeAttr("disabled");
							if (result.success) {
								utils.alert.suc("<strong>" + result.msg + "</strong>");
								$("#commonBorrowerTable").dataTable().fnDraw();
								$('#newCommonBorrowerModal').modal('hide');
							} else {
								utils.alert.err("<strong>" + result.msg + "</strong>");
							}
						}
					});
				}
			});
		},
        initNationAreaCascade : function() {//省市区级联初始化
        	var viewSelf = this;
        	var nation = $("#nation");
        	var province = $("#province");
        	var city = $("#city");
        	var county = $("#county");
        	var isAll = false;//true 全部国家；false 只有中国
        	if(isAll) {
        		viewSelf.model.loadNationAreaData("root", function(r_data) {
        			$.each(r_data,function(index, val) {
        				nation.append("<option value='" + val.code + "'>" + val.name + "</option>");
        			});
        		});
        	} else {
        		nation.append("<option value='156'>中国</option>");
        	}
        	viewSelf.initSelectCascade(nation, province);
        	viewSelf.initSelectCascade(province, city);
        	viewSelf.initSelectCascade(city, county);
        },
        initSelectCascade : function($select1, $select2) {//绑定change，实现级联
        	var viewSelf = this;
        	$select1.change(function() {
        		var selectSelf = $(this);
        		viewSelf.initSelectOptions($select2, selectSelf.val());
        	});
        },
        initSelectOptions : function($select, code) {//构建options
        	var viewSelf = this;
        	$select.get(0).options.length = 1;//清除多余options
        	if($select.attr("id")=="province") {
        		$("#city").get(0).options.length = 1;
            	$("#county").get(0).options.length = 1;
        	} else if($select.attr("id") == "city") {
            	$("#county").get(0).options.length = 1;
        	}
    		if(code){
    			viewSelf.model.loadNationAreaData(code, function(r_data) {
    				$.each(r_data, function(index, val) {
    					$select.append("<option value='" + val.code + "'>" + val.name + "</option>");
    				});
    			});
    		}
        }
	});
	module.exports = view;
});