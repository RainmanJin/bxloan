define(function(require, exports, module) {
	var utils = require("utils");
	var view = Backbone.View.extend({
		el: "body",
		events: {
			"click #btn-data-import": "dataimport",  //数据导入按钮事件
			"click #btn-template-download": "templatedownload",  //模板下载
			"click #btn-data-upload": "dataupload"  //数据上传
		},
		initialize: function() {
			this.render();
		},
		//渲染
		render: function() {
			this.initDtTable();  //初始化表格
			this.initUploadDataForm();//初始化上传弹框
		},
		//上传弹框
		initUploadDataForm: function() {
			$("#uploadFile").uploadify({
	            swf: $$ctx + "static/assets/js/uploadify/uploadify.swf",
	            uploader: $$ctx + "uploadFile/importTemplateData",
	            buttonText: "请选择文件",
	            auto: false,
	            formData: {'loginName':$("#loginName").val(),'orgId': $("#orgId").val()},
	            fileObjName: "file",
	            removeTimeout: 1,
	            queueSizeLimit: 1,
	            fileTypeExts: "*.xls; *.xlsx;",
	            fileTypeDesc: "请选择",
	            onSelectError: function(file, errorCode, errorMsg) {
	                switch (errorCode) {
	                case - 100 : utils.alert.warn("上传的文件数量已经超出系统限制");
	                    break;
	                case - 110 : utils.alert.warn("文件 大小超出系统限制的" + $('#uploadFile').uploadify('settings', 'fileSizeLimit') + "大小！");
	                    break;
	                case - 120 : utils.alert.warn("文件 [" + file.name + "] 大小异常！");
	                    break;
	                case - 130 : utils.alert.warn("文件 [" + file.name + "] 类型不正确！");
	                    break;
	                default:
	                    break;
	                }
	            },
	            onFallback: function() {
                    utils.alert.warn("您未安装FLASH控件！请安装FLASH控件后再试。");
                    return false;
                },
	            onUploadStart: function(file) {
	                $("#uploadFile").uploadify("disable", true);
	            },
	            onUploadSuccess: function(file, data, response) {
	                $("#uploadFile").uploadify("disable", false); // 按钮还原
	                var jsonobj = eval('('+data+')');
	                if (jsonobj.code == "success") {   
	                	utils.alert.suc(jsonobj.desc);
	                } else {
	                	utils.alert.warn(jsonobj.desc);
	                }
	                /**重绘表格*/
	                var oTable = $("#recordTable").dataTable();
                    oTable.fnDraw();
	            },
	            onQueueComplete: function(queueData) {
	                $("#uploadFile").uploadify('cancel', '*'); // 清空队列
	            },
	            onUploadError: function(file, errorCode, errorMsg, errorString) {
	            	console.log(errorMsg);
	                $("#uploadFile").uploadify("disable", false); // 按钮还原
	            }
	        });
		},  
		//数据导入按钮事件
		dataimport: function() { 
            $("#uploadDataForm").resetForm();
            $("#data-upload-form div.modal-header h4").html("<i class='ace-icon fa fa-arrow-up'></i> 数据导入");
            $("#data-upload-form").modal("show");
        },
        //数据上传
        dataupload: function() {
        	$("#uploadFile").uploadify("upload","*");
        },
		//模板下载
		templatedownload: function() {
			var url = $$ctx + 'dataTranSend/downloadTemplate';
			window.location.href = url;
		},
		//datatable start
		initDtTable: function() {  //查询导入的记录
			var viewSelf = this;
			var dt = $("#recordTable").dataTable({
				sAjaxSource : $$ctx + "dataTranSend/findImportRecord",
				bFilter : false,
				bSort:false,
				bLengthChange:false,
				aoColumns : [
			        {mData: "contractNum", mRender: function(data, type, rowdata) {
			        	if ($.trim(data) == '') {
			                 return "";
			            }
			        	return data;
			        }},
			        {mData: "repayAmt", mRender: function(data, type, rowdata) {
			        	/**对还款金额列进行格式化*/
			        	if ($.trim(data) == '') {
			                return "";
			            }
			        	return utils.number.toAmt(rowdata.repayAmt);
			        }},
			        {mData: "repayDate", mRender: function(data, type, rowdata) {
			        	if ($.trim(data) == '') {
			                return "";
			            }
			        	return utils.date.formatDate(data);
			        }},
	                {mData: "insertDate", mRender: function(data, type, rowdata) {
			        	if ($.trim(data) == '') {
			                return "";
			            }
			            return new Date(data).format("yyyy-MM-dd hh:mm:ss");
	                }},
	                {mData: null, mRender: function(data, type, rowdata) {
	                	if ($.trim(rowdata) == '') {
			                return "";
			            }
	                	var button = "<div class='btn-group'style='width:100px;'><button title='删除' type='button' role='deleteRecord' data-id='" + rowdata.excelInId + "' class='btn btn-xs btn-danger'><i class='ace-icon fa fa-trash-o'></i></button></div>";
						return button;  
				}}],
				/**传参-机构id*/
				fnServerParams : function(aoData) {
					aoData.push({
						name : "orgId",
						value : $('#orgId').val()
					});
				},
				/**对还款金额列进行格式化*/
                /*fnDrawCallback : function(data) {
                	console.log(data);
                	utils.num.colsFormat(this, [2]);
                }  */
			});
			viewSelf.dt = dt;
			/**删除记录*/
			$(document).on("click", "button[role='deleteRecord']", function(e) {
				var id = $(this).data("id");
				utils.button.confirm(null, function(result) {
					if (result) {
						$.ajax({
							type : 'post',
							url : $$ctx + 'dataTranSend/deleteRecord',
							data : {
								'id' : id
							},
							success : function(r) {
								if(r.success) {
									utils.alert.suc("<strong>" + r.msg + "</strong>");
									$("#recordTable").dataTable().fnDraw();
								} else {
									utils.alert.err("<strong>" + r.msg + "</strong>");
								}
							}
						});
					}
				});
			});
		}
		//datatable end
	});
	module.exports = view;
});