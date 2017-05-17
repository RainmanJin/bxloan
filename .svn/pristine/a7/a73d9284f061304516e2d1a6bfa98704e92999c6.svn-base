define(function(require, exports, module) {
	var commonBorrowerAData = undefined;
	var utils = require("utils");
	var view = Backbone.View.extend({
		el : "body",
		events : {
			"click #addCommonBorrower": "addCommonBorrower",  //点击新建关联
			"click #addCommonBorrowerBack": "addCommonBorrowerBack", //点击返回
			"click #openCommonBorrowerSureModal": "openCommonBorrowerSureModal" //点击确定
		},
		initialize : function() {
			this.render();
		},
		render : function() {
			/** 共同借款人 */
			this.initCommonBorrowerTable();  //初始化共同借款人列表
			this.initCustomerTableForCommonBorrower(); //初始化客户列表
		},
		initCommonBorrowerTable : function() {  //查询共同借款人信息
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
			
			$(document).on("click", "button[role='checkCommonBorrower']", function(e) {  //点击查看
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
							$('#commonBorrowerDetailDiv').modal('show');  //显示共同借款人信息
						}
					}
				});
			});
			
			$(document).on("click", "button[role='deleteCommonBorrower']", function(e) {  //点击删除
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
			$(document).on("click", "button[id='searchForCommonBorrower']", function(e) {  //查询
				var table = $("#customerTableForCommonBorrower").dataTable();
				table.fnSettings()._iDisplayStart = 0;
				table.fnDraw();
			});
			$(document).on("click", "button[id='resetForCommonBorrower']", function(e) {  //重置
				$('#customerNameForCommonBorrower').val('');
				$('#customerNumForCommonBorrower').val('');
				$('#certificateTypeCdForCommonBorrower').val('');
				$('#certificateNumForCommonBorrower').val('');
				var table = $("#customerTableForCommonBorrower").dataTable();
				table.fnSettings()._iDisplayStart = 0;
				table.fnDraw();
			});
		},
		addCommonBorrower : function() { //显示客户列表
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
		},
		addCommonBorrowerBack : function() {  //点击返回
			$('#customerTableDivForCommonBorrower').slideUp();
			$('#commonBorrowerTableDiv').slideDown();
			$('#addCommonBorrower').slideDown();
		},
		openCommonBorrowerSureModal : function() {  //点击确定，保存共同借款人信息
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
						} else {
							utils.alert.err("<strong>" + result.msg + "</strong>");
						}
						$('#openCommonBorrowerSureModal').removeAttr('disabled');
					}
				});
			}
		}
	});
	module.exports = view;
});