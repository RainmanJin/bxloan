define(function(require, exports, module) {

    var model = require("./model");
    var rm_plant = require("./rm_fn_cultivate");
    var rm_breed = require("./rm_fn_breed");
    var utils = require("utils");
    var formFunc = {};
    var trimStr = function(str) {
    	return str.replace(/(^\s*)|(\s*$)/g,"");
    }
    var getHidden = function(hiddenName) {
        return $("#hiddenField").find("input[name='" + hiddenName + "']").val();
    }
    var view = Backbone.View.extend({
        el: "body",
        events: {
        	"click #btn-fn-add-plant" : "addFnCultivate",
        	"click #btn-nh-add-breed" : "addFnBreed",
        	"click #submit-fn-cultivate" : "saveFnCultivate",
        	"click #submit-fn-breed" : "saveFnBreed"
        },
        initialize: function() {
        	this.isView=utils.parseBool($("#isView").val());//是否显示
			this.isEdit=utils.parseBool($("#isEdit").val());//是否是编辑
            this.model = new model();
            this.render();
        },
        render: function() {
            this.initFrist();
            this.initFnCultivateTable();
            this.initFnBreedTable();
            this.initCultivateForm();
            this.initBreedForm();
            this.initDelelteCultivateBtn();
            this.initDelelteBreedBtn();
            this.initEditCultivateBtn();
            this.initEditBreedBtn();
            this.initDetailCultivateBtn();
            this.initDetailBreedBtn();
        },
        initFrist: function() {
            formFunc = {
            	 caculateCount: function(selector, ignorants){
            		 $(selector).find("tfoot").html("");
            		var ig_flag = ignorants?ignorants.length>0:false;
            		var tr = $(selector).find("tr:gt(0)");
            		//所需遍历的行数
            		var tr_length = $(selector).find("tr").length-1;
            		if(tr_length == 1 && tr[0].innerHTML.indexOf("没有符合条件的记录")>0){return false;}
            		//列数
            		var td_length = $(tr[0]).find("td").length;
            		//结果集
            		var count_value = ["合计"];
            		
            		for(var i =0;i<tr_length; i++){
            			var $_tr = $(tr[i]);//当前行
            			for(var j=1; j<td_length; j++){
            				var text = trimStr($_tr.find("td:eq('"+ j +"')").text());
            				if(ig_flag && ($.inArray(j,ignorants)>=0)){
            					count_value[j] = "-";
            					continue;
            				}
            				if(/^[0-9]+.?[0-9]*$/.test(text)){
            					if(!count_value[j]){
                					count_value[j] = 0;
                				}
                				count_value[j] += parseFloat(text);
            				}else{
            					if(!count_value[j]){
                					count_value[j] = "-";
                				}
            				}
            			}
            		}
            		
            		for(var n=0;n<count_value.length;n++){
            			count_value[n] = "<td>"+ count_value[n] +"</td>";
            		}
            		
            		var countRow = "<tr>"+
            		count_value.join('') + 
            					   "</tr>";
            		
            		$(selector).find("tfoot").append(countRow);
            	 },
            	 disableForm : function(selector, r){
            		 $.each($(selector).find("input, select"), function() {
              			 $(this).val(r[$(this).attr("name")]);
              			 $(this).prop("disabled", true);
               		 });
            		 $(selector).find("button[id^='submit']").hide();
            	 },
            	 resetForm: function(selector, r){
            		 $.each($(selector).find("input, select"), function() {
              			 $(this).val(r[$(this).attr("name")]);
              			 $(this).prop("disabled", false);
               		 });
            		 $(selector).find("button[id^='submit']").show();
            	 }
            	 
            }
        },
        initFnCultivateTable: function() {
            var viewSelf = this;
            utils.dd.initDataDict(["AgroFnCultivateType"],
            function(dataDict) {
                var oTable = $("#cultivate_fn_tb").dataTable({
                    "sAjaxSource": $$ctx + "cultivateAndBreed/findfnCultivateBySearch",
                    "bFilter": false,
                    "bPaginate": false,
                    "bAutoWidth": true,
                    "bLengthChange": false,
                    "aoColumns": [{
                        "bVisible": false,
                        "mData": "id"
                    },
                    {
                        "mData": "cultivateType",
                        mRender: function(data, type, rowdata) {
                        	if(data){
                        		return dataDict.AgroFnCultivateType[data];
                        	}else{
                        		return "-";
                        	}
                        }
                    },
                    {
                        "mData": "cultivateContent"
                    },
                    { //与借款人关系
                        "mData": "cultivateArea"
                    },
                    {
                        "mData": "income"
                    },
                    {
                        "mData": "cost"
                    },
                    {
                        "mData": "cropValue"
                    },
                    {
                        "mData": "agricultureCapitalValue"
                    },
                    {
                        "mData": "id",
                        mRender: function(data, type, rowdata) {
                            /*var buttons = "<button type='button' role='edit_fnZz' data-id='" + rowdata.id + "' class='btn btn-xs btn-info' title='修改'><i class='ace-icon fa fa-edit' ></i></button> " 
                            			+ "<button type='button' role='detail_fnZz' data-id='" + rowdata.id + "' class='btn btn-xs btn-yellow' title='查看'><i class='ace-icon fa fa-eye' ></i></button> " 
                            			+ "<button type='button' role='delete_fnZz' data-id='" + rowdata.id + "' class='btn btn-xs btn-danger' title='删除'><i class='ace-icon fa fa-trash-o'></i></button>";
                            return buttons*/
                        	var html=[];
                        	if(viewSelf.isEdit){
                        		html.push("<button type='button' role='edit_fnZz' data-id='" + rowdata.id + "' class='btn btn-xs btn-info' title='修改'><i class='ace-icon fa fa-edit' ></i></button> "); 
                        	}
                        	html.push("<button type='button' role='detail_fnZz' data-id='" + rowdata.id + "' class='btn btn-xs btn-yellow' title='查看'><i class='ace-icon fa fa-eye' ></i></button> "); 
                        	if(viewSelf.isEdit){
                        		html.push("<button type='button' role='delete_fnZz' data-id='" + rowdata.id + "' class='btn btn-xs btn-danger' title='删除'><i class='ace-icon fa fa-trash-o'></i></button>");
                        	}
                        	return html.join('');
                        }
                    }],
                    "fnServerParams": function(aoData) {
                        aoData.push({
                            "name": "projectId",
                            "value": getHidden("projectId")
                        });
                    },
                    "fnDrawCallback": function(){
                    	formFunc.caculateCount(this);
                    	$(this).css({"white-space":"nowrap"});
                    	$(this).wrap("<div style='overflow-x: auto;overflow-y: hidden;'></div>")
                    }
                });
                viewSelf.oTable = oTable;
            });
        },
        initFnBreedTable: function() {
            var viewSelf = this;
            utils.dd.initDataDict(["AgroFnBreedType"],
            function(dataDict) {
                var oTable = $("#breed_fn_tb").dataTable({
                    "sAjaxSource": $$ctx + "cultivateAndBreed/findfnBreedBySearch",
                    "bFilter": false,
                    "bPaginate": false,
                    "bAutoWidth": true,
                    "bLengthChange": false,
                    "aoColumns": [{
                        "bVisible": false,
                        "mData": "id"
                    },
                    {
                        "mData": "breedType", //养殖种类
                        mRender: function(data, type, rowdata) {
                        	if(data){
                        		return dataDict.AgroFnBreedType[data];
                        	}else{
                        		return "-";
                        	}
                        }
                    },
                    {
                        "mData": "breedContent" //养殖内容
                    },
                    { //
                        "mData": "breedMode" //养殖方式
                    },
                    {
                        "mData": "breedScale" //养殖规模
                    },
                    {
                        "mData": "income" //年收入合计
                    },
                    {
                        "mData": "cost" //年成本合计
                    },
                    {
                        "mData": "investigateBreedScale" //调查时养殖规模
                    },
                    {
                        "mData": "breedStockValue" //养殖存量价值(元)
                    },
                    {
                        "mData": "forageValue" //饲料等存货价值(元)
                    },
                    {
                        "mData": "id",
                        mRender: function(data, type, rowdata) {
                            /*var buttons = "<button type='button' role='edit_fnYz' data-id='" + rowdata.id + "' class='btn btn-xs btn-info' title='修改'><i class='ace-icon fa fa-edit' ></i></button> " 
                            			+ "<button type='button' role='detail_fnYz' data-id='" + rowdata.id + "' class='btn btn-xs btn-yellow' title='查看'><i class='ace-icon fa fa-eye' ></i></button> " 
                            			+ "<button type='button' role='delete_fnYz' data-id='" + rowdata.id + "' class='btn btn-xs btn-danger' title='删除'><i class='ace-icon fa fa-trash-o'></i></button>";
                            return buttons;*/
                        	var html=[];
                        	if(viewSelf.isEdit){
                        		html.push("<button type='button' role='edit_fnYz' data-id='" + rowdata.id + "' class='btn btn-xs btn-info' title='修改'><i class='ace-icon fa fa-edit' ></i></button> "); 
                        	}
                        	html.push("<button type='button' role='detail_fnYz' data-id='" + rowdata.id + "' class='btn btn-xs btn-yellow' title='查看'><i class='ace-icon fa fa-eye' ></i></button> "); 
                        	if(viewSelf.isEdit){
                        		html.push("<button type='button' role='delete_fnYz' data-id='" + rowdata.id + "' class='btn btn-xs btn-danger' title='删除'><i class='ace-icon fa fa-trash-o'></i></button>");
                        	}
                            return html.join('');
                        }
                    }],
                    "fnServerParams": function(aoData) {
                        aoData.push({
                            "name": "projectId",
                            "value": getHidden("projectId")
                        });
                    },
                    "fnDrawCallback": function(){
                    	formFunc.caculateCount(this, [1,2,3,6]);
                    	$(this).wrap("<div style='overflow-x: auto;overflow-y: hidden;'></div>")
                    }
                });
                viewSelf.oTable = oTable;
            });
        },
        initCultivateForm: function(){
        	$("#fnCultivateForm_nd").validate({
                rules: rm_plant.rules,
                messages: rm_plant.messages,
                errorPlacement: function(error, element) {
                    if (element.is(":radio")){error.appendTo(element.parent());}
                    else if (element.is(":checkbox")){error.appendTo(element.parent().parent().parent().next());}
                    else if (element.next().is("span[class='input-group-addon']")) {
                    	error.appendTo(element.parent().parent().parent());
                    }
                    else {
                    	error.appendTo(element.parent());
                    } 
                }
            });
        },
        initBreedForm: function(){
        	$("#fnBreedForm_nd").validate({
                rules: rm_breed.rules,
                messages: rm_breed.messages,
                errorPlacement: function(error, element) {
                    if (element.is(":radio")){error.appendTo(element.parent());}
                    else if (element.is(":checkbox")){error.appendTo(element.parent().parent().parent().next());}
                    else if (element.next().is("span[class='input-group-addon']")) {
                    	error.appendTo(element.parent().parent().parent());
                    }
                    else {
                    	error.appendTo(element.parent());
                    } 
                }
            });
        },
        initDelelteCultivateBtn: function(){
        	var viewSelf = this;
            $(document).on("click", "button[role='delete_fnZz']",
            function(e) { // 动态绑定所有删除按钮的click事件
                var $this = $(this);
                if (bootbox.confirm({
                    message: "确定要删除此条数据吗 ?",
                    buttons: {
                        confirm: {
                            label: "<i class='ace-icon fa fa-trash-o bigger-110'></i> 确定",
                            className: "btn-danger btn-sm"
                        },
                        cancel: {
                            label: "<i class='ace-icon fa fa-times bigger-110'></i> 取消",
                            className: "btn-warning btn-sm"
                        }
                    },
                    callback: function(result) {
                        if (result) {
                            var id = $this.data("id");
                            viewSelf.model.deleteCultivate(id, function(data){
                            	if (data.success) {
                                    //bootbox.alert(_success + "<strong>删除成功!</strong>",function() {});
                                    var datatable = utils.datatable.fresh("#cultivate_fn_tb");
                                } else {
                                	utils.alert.warn("<strong>"+data.msg+"</strong>");
                                }
                            });
                        }
                    }
                }));
                return false;
            });
        },
        initDelelteBreedBtn: function(){
        	var viewSelf = this;
            $(document).on("click", "button[role='delete_fnYz']",
            function(e) { // 动态绑定所有删除按钮的click事件
                var $this = $(this);
                if (bootbox.confirm({
                    message: "确定要删除此条数据吗 ?",
                    buttons: {
                        confirm: {
                            label: "<i class='ace-icon fa fa-trash-o bigger-110'></i> 确定",
                            className: "btn-danger btn-sm"
                        },
                        cancel: {
                            label: "<i class='ace-icon fa fa-times bigger-110'></i> 取消",
                            className: "btn-warning btn-sm"
                        }
                    },
                    callback: function(result) {
                        if (result) {
                            var id = $this.data("id");
                            viewSelf.model.deleteBreed(id, function(data){
                            	if (data.success) {
                                    //bootbox.alert(_success + "<strong>删除成功!</strong>",function() {});
                                    var datatable = utils.datatable.fresh("#breed_fn_tb");
                                } else {
                                	utils.alert.warn("<strong>"+data.msg+"</strong>");
                                }
                            });
                        }
                    }
                }));
                return false;
            });
        },
        initEditCultivateBtn: function(){
        	var viewSelf = this;
            $(document).on("click", "button[role='edit_fnZz']", function(e){
           	 var $this = $(this); 
           	 viewSelf.model.findOneCultivate({
           		 "id" : $this.data('id')
           	 },function(r){
           		if(r){
           			formFunc.resetForm("#fnCultivateForm_nd", r);
           			$("#modal-formFnCultivate div.modal-header h4").html("<i class='ace-icon fa fa-edit'></i> 种植业");
           			$("#modal-formFnCultivate").modal("show");
           		}else{
           			utils.alert.warn("记录已不存在，请联系管理员");
           		}
           	 });
            });
        },
        initEditBreedBtn: function(){
        	var viewSelf = this;
            $(document).on("click", "button[role='edit_fnYz']", function(e){
           	 var $this = $(this); 
           	 viewSelf.model.findOneBreed({
           		 "id" : $this.data('id')
           	 },function(r){
           		 if(r){
           			 
           			 formFunc.resetForm("#fnBreedForm_nd", r);
           			 $("#modal-formFnBreed div.modal-header h4").html("<i class='ace-icon fa fa-edit'></i> 养殖业");
           			 $("#modal-formFnBreed").modal("show");
           		 }else{
            			utils.alert.warn("记录已不存在，请联系管理员");
           		 }
           	 });
            });
        },
        initDetailCultivateBtn: function(){
        	var viewSelf = this;
            $(document).on("click", "button[role='detail_fnZz']", function(e){
           	 var $this = $(this); 
           	 viewSelf.model.findOneCultivate({
           		 "id" : $this.data('id')
           	 },function(r){
           		if(r){
           			formFunc.disableForm("#fnCultivateForm_nd", r);
           			$("#modal-formFnCultivate div.modal-header h4").html("<i class='ace-icon fa fa-eye'></i> 种植业");
           			$("#modal-formFnCultivate").modal("show");
           		}else{
           			utils.alert.warn("记录已不存在，请联系管理员");
           		}
           	 });
            });
        },
        initDetailBreedBtn: function(){
        	var viewSelf = this;
            $(document).on("click", "button[role='detail_fnYz']", function(e){
           	 var $this = $(this); 
           	 viewSelf.model.findOneBreed({
           		 "id" : $this.data('id')
           	 },function(r){
           		 if(r){
           			 formFunc.disableForm("#fnBreedForm_nd", r);
           			 $("#modal-formFnBreed div.modal-header h4").html("<i class='ace-icon fa fa-eye'></i> 养殖业");
           			 $("#modal-formFnBreed").modal("show");
           		 }else{
            		 utils.alert.warn("记录已不存在，请联系管理员");
           		 }
           	 });
            });
        },
        addFnCultivate: function(){
        	 $("#fnCultivateForm_nd").resetForm();
        	 $("#fnCultivateForm_nd").find("input,select").prop("disabled",false);
        	 $("#fnCultivateForm_nd").find("button").show();
        	 $("#modal-formFnCultivate input[name='id']").val("");
        	 $("#modal-formFnCultivate input[name='projectId']").val(getHidden('projectId'));
        	 
        	 var viewSelf = this;
        	 viewSelf.model.findNecessaryMsg({
        		 "projectId": getHidden("projectId"),
        		 "type" : "1"
        	 }, function(r){
        		 if(r){
        			 for(var key in r){
        				 $("#fnCultivateForm_nd").find("input[name='"+ key+"']").val(r[key]);
        			 }
        		 }
        	 });
        	 
        	 $("#modal-formFnCultivate div.modal-header h4").html("<i class='ace-icon fa fa-plus'></i> 种植业");
             $("#modal-formFnCultivate").modal("show");
        },
        addFnBreed: function(){
        	 $("#fnBreedForm_nd").resetForm();
        	 $("#fnBreedForm_nd").find("input,select").prop("disabled",false);
        	 $("#fnBreedForm_nd").find("button").show();
        	 $("#modal-formFnBreed input[name='id']").val("");
        	 $("#modal-formFnBreed input[name='projectId']").val(getHidden('projectId'));
        	 
        	 var viewSelf = this;
        	 viewSelf.model.findNecessaryMsg({
        		 "projectId": getHidden("projectId"),
        		 "type" : "2"
        	 }, function(r){
        		 if(r){
        			 for(var key in r){
        				 $("#fnBreedForm_nd").find("input[name='"+ key+"']").val(r[key]);
        			 }
        		 }
        	 });
        	 
        	 $("#modal-formFnBreed div.modal-header h4").html("<i class='ace-icon fa fa-plus'></i> 养殖业");
             $("#modal-formFnBreed").modal("show");
        },
        saveFnCultivate: function(){
        	var viewSelf = this;
			if ($("#fnCultivateForm_nd").valid()) {
            	utils.button.ban("#submit-fn-cultivate");
            	$.ajax({
                    cache: false,
                    type: "POST",
                    url: $$ctx + "cultivateAndBreed/saveCultivate",
                    data: $('#fnCultivateForm_nd').serialize(),
                    async: false,
                    error: function(request) {
                        utils.alert.err("错误"+ request.status);
                    },
                    success: function(data) {
                    	utils.button.reset("#submit-fn-cultivate");
                    	if(data){
                    		if(data.success){
                    			$("#cultivate_fn_tb").dataTable().fnDraw();
                    			//$("#fnCultivateForm_nd").resetForm();
                    			
                                $("#modal-formFnCultivate").modal("hide");
                                $("#statisticsTable").dataTable().fnDraw();
                    		}
                    		else{
                    			utils.alert.warn("<strong>"+ data.msg +"</strong>");
                    		}
                    	}
                    }
                }); //ajax end
            }
        },
        saveFnBreed: function(){
        	var viewSelf = this;
			if ($("#fnBreedForm_nd").valid()) {
            	utils.button.ban("#submit-fn-breed");
            	$.ajax({
                    cache: false,
                    type: "POST",
                    url: $$ctx + "cultivateAndBreed/saveBreed",
                    data: $('#fnBreedForm_nd').serialize(),
                    async: false,
                    error: function(request) {
                        utils.alert.err("错误"+ request.status);
                    },
                    success: function(data) {
                    	utils.button.reset("#submit-fn-breed");
                    	if(data){
                    		if(data.success){
                    			$("#breed_fn_tb").dataTable().fnDraw();
                    			//$("#fnCultivateForm_nd").resetForm();
                    			
                                $("#modal-formFnBreed").modal("hide");
                                $("#statisticsTable").dataTable().fnDraw();
                    		}
                    		else{
                    			utils.alert.warn("<strong>"+ data.msg +"</strong>");
                    		}
                    	}
                    }
                }); //ajax end
            }
        }

    });
    module.exports = view;
});