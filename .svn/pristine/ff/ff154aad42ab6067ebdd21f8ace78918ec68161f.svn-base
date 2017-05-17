define(function(require, exports, module) {

    var model = require("./model");
    var rm_plant = require("./rm_fn_cultivate");
    var rm_breed = require("./rm_fn_breed");
    var rm_common_info = require("./rm_fn_common_info");
    var utils = require("utils");
    var formFunc = {};
    var trimStr = function(str) {
        return str.replace(/(^\s*)|(\s*$)/g, "");
    }
    var getHidden = function(hiddenName) {
        return $("#hiddenField").find("input[name='" + hiddenName + "']").val();
    }
    var view = Backbone.View.extend({
        el: "body",
        events: {
            "click #n_culitivate_add": "showCultivateModal",//种植业弹出框
            "click #n_breed_add": "showBreedModal",//养殖业弹出框
            
            "click #submit-n-cultivate_pass": "submitNCultivatePass",//种植业过去
            "click #submit-n-cultivate_future": "submitNCultivateFuture",//种植业未来
            "click #submit-n-breed_pass": "submitNBreedPass",//养殖业过去
            "click #submit-n-breed_future": "submitNBreedFuture",//养殖业未来

            "click #btn-add-n-cultivate-future": "addIncomeCommonCul",
            "click #btn-add-n-breed-future": "addIncomeCommonBreed",
            
            "click #btn-addEcfIncome_nd" : "addIncomeCultivate",//增加种植收入行
            "click #btn-addEcfConsume_nd" : "addConsumeCultivate",//增加种植支出行
            "click #btn-addEcfIncome-breed" : "addIncomeBreed",//增加养殖收入行
            "click #btn-addEcfConsume-breed" : "addConsumeBreed",//增加养殖支出行
            
            "click .nfuture-close" : "nfutureClose"//种植业查看关闭窗口
        },
        initialize: function() {
        	var viewSelf=this;
        	viewSelf.isEdit=utils.parseBool($("#isEdit").val());//是否是编辑
            this.model = new model();
            this.render();
        },
        render: function() {
            this.initFrist();
            this.initWiazrdCultivate();//种植业
            this.initWiazrdBreed();//养殖业
            this.initFbCultivateTable();
            this.initFbBreedTable();
            this.initOverIncomeTable();
            this.initCultivateForm();
            this.initBreedForm();
            this.initDeleteCultivateBtn();
            this.initEditCultivateBtn();

            this.initDeleteBreedBtn();
            this.initEditBreedBtn();

            this.initCommonInfoForm();
            this.initCommonDebtInfoForm();
            
            this.saveRowBtnLive();//保存
            this.modifyRowBtnLive();//修改
            
            this.deleteRowBtnLive();
            this.cancelRowBtnLive();
            
            this.findIncomeList();//查询收入列表
            this.findConsumeList();//查询支出列表
            
            this.incomeSummation();//收入合计
            this.consumeSummation();//支出合计
            
            this.initDetailCultivateBtn();//种植业查看详情
            this.initDetailBreedBtn();//种植业查看详情
        },
        initFrist: function() {
        	var num =1;
        	var viewSelf = this;
            formFunc = {
        		addRow: function(btn, type){
        			var objWay = utils.str.substringAfterLast(btn,"_");
		        	var time = "<input type='text' size='1' name='monthOfYear' class='form-control input-sm date-picker' data-date-format='yyyy-MM'/>";
		        	var inputMoney = "<input type='text' size='1' name='amtMoney' data-culway='"+objWay+"' class='form-control input-sm'/>";
		        	var inputName = "<input type='text' name='objContent' class='form-control input-sm' />";
		        	var button = "<button type='button' role='save_ecfi_"+objWay+"' data-type='"+ type +"'  class='btn btn-xs btn-info' title='保存'><i class='ace-icon fa fa-floppy-o' ></i></button> "  + "<button type='button' role='cancel_ecfi' title='删 除' class='btn btn-xs btn-danger' ><i class='ace-icon fa fa-times'></i></button>";
		        	var newRow = "<tr class='odd'>" +
		        				 "<td>"+this.addSeqnum(btn)+"</td>"+
				     			 "<td>"+ time +"</td>" + //time
				     			 "<td>"+ inputMoney +"</td>" + //name
				     			 "<td>"+ inputName +"</td>" + //money
				     			 "<td>"+ button +"</td>" +
				     			 "</tr>";
		        	$(btn+" tbody tr:first").before(newRow);
		        	viewSelf.initDataPickers();
				},
				addSeqnum: function(btn){//自增序号
					var _count = parseInt($(btn+" tbody .odd").eq(0).children().html())+1;
					return $(btn+" tbody .odd").eq(0).length > 0 ? _count : 1;
				},
				editRow: function(btn){
					var $this = $(btn);
	            	var tr = $this.closest("tr");
	            	$("#modal-formEcf").find("input[name='id']").val($this.data('id'));
	            	var text_money = $(tr).find("td:eq(1)").text();
	            	if($this.data('type') == "2"){//如果是支出
	            		text_money = text_money.substring(1);
	            	}
	            	var inputName = "<input type='text' name='name' class='form-control input-sm' value='"+ $(tr).find("td:eq(0)").text() +"'/>";
	            	var inputMoney = "<input type='text' name='money' class='form-control input-sm' value='"+ text_money +"'/>";
	            	var button = "<button type='button' role='save_ecfd' data-type='"+ $this.data('type') +"'  class='btn btn-xs btn-info' title='保存'><i class='ace-icon fa fa-floppy-o' ></i></button> "  + "<button type='button' role='cancel_ecfd'  class='btn btn-xs btn-danger' title='取消'><i class='ace-icon fa fa-times'></i></button>";
	            	var newRow = "<tr class='odd'>" +
	    		     			 "<td>"+ inputName +"</td>" + //name
	    		     			 "<td>"+ inputMoney +"</td>" + //money
	    		     			 "<td>"+ button +"</td>" +
	    		     			 "</tr>";
	    			$(tr)[0].innerHTML = newRow;
				},
                caculateCount: function(selector) {
                    $(selector).find("tfoot").html("");

                    var tr = $(selector).find("tr:gt(0)");
                    //所需遍历的行数
                    var tr_length = $(selector).find("tr").length - 1;
                    if (tr_length == 1 && tr[0].innerHTML.indexOf("没有符合条件的记录") > 0) {
                        return false;
                    }
                    //列数
                    var td_length = $(tr[0]).find("td").length;
                    //结果集
                    var count_value = ["合计"];

                    for (var i = 0; i < tr_length; i++) {
                        var $_tr = $(tr[i]); //当前行
                        for (var j = 1; j < td_length; j++) {
                            var text = trimStr($_tr.find("td:eq('" + j + "')").text());
                            if (/^[0-9]+.?[0-9]*$/.test(text)) {
                                if (!count_value[j]) {
                                    count_value[j] = 0;
                                }
                                count_value[j] += parseFloat(text);
                            } else {
                                if (!count_value[j]) {
                                    count_value[j] = "-";
                                }
                            }
                        }
                    }

                    for (var n = 0; n < count_value.length; n++) {
                        count_value[n] = "<td>" + count_value[n] + "</td>";
                    }

                    var countRow = "<tr>" + count_value.join('') + "</tr>";

                    $(selector).find("tfoot").append(countRow);
                },
                disableForm: function(selector, r) {
                    $.each($(selector).find("input, select"),
                    function() {
                        $(this).val(r[$(this).attr("name")]);
                        $(this).prop("disabled", true);
                    });
                    $(selector).find("button[id^='submit']").hide();
                },
                setFormDisabled: function(selector){
                	 $.each($(selector).find("input,select"), function() {
                          $(this).prop("disabled", true);
                     });
                },
                resetForm: function(selector, r) {
                    $.each($(selector).find("input, select"),
                    function() {
                        $(this).val(r[$(this).attr("name")]);
                        $(this).prop("disabled", false);
                    });
                    $(selector).find("button[id^='submit']").show();
                },
                removeDisabled: function(selector){
                	 $.each($(selector).find("input[type='text'],select"),function() {
                		 var $inputName = $(this).attr('name');
                		 if($inputName != 'predictTotalProduce' && $inputName != 'saleNum' && $inputName != 'saleIncomeTotal'){
                			 $(this).removeAttr("disabled");
                    		 $(this).removeAttr("readonly");
                		 }else{
                			 $(this).attr("readonly",true);
                		 }
                     });
                },
                initUnitField: function() {
                    var uls = $("div.input-group-btn ul.dropdown-menu li");
                    $.each(uls,
                    function(i, e) {
                        var $this = $(this);
                        $this.on("click",
                        function() {
                            var val = $this.data("value");
                            var text = $this.text();
                            $this.closest("div.input-group-btn").find("button:eq(0)").text(text);
                            $this.closest("div.input-group").find("input:hidden").val(val);
                        });
                    });
                },
                caculateCultivate: function() {
                    var nPassCultivate = $("#nPassCultivateForm_nd").find("input[name='output'],input[name='familyConsume'],input[name='livestockConsume']");
                    var nFutureCultivate = $("#nFutureCultivateForm_cultivate").find("input[name='predictTotalProduce'],input[name='familyConsume'],input[name='livestockConsume']");
                    var nFutureCultivateTotal = $("#nFutureCultivateForm_cultivate").find("input[name='predictSingleProduce'],input[name='cultivateScale']");
                    var nFutureCultivateSaleNum = $("#nFutureCultivateForm_cultivate").find("input[name='saleNum'],input[name='salePrice']");
                    var incomePassCultivate = $("#nPassCultivateForm_nd").find("input[name='saleNum'],input[name='salePrice']");
                    var predictCultivate = $("#nPassCultivateForm_nd").find("input[name='maxSingleProduce'],input[name='minSingleProduce'],input[name='lastYearSingleProduce']");
                    $.each(nPassCultivate,
                    function(i, e) {
                        var $this = $(this);
                        $this.on("change",
                        function() {
                            if (nPassCultivate[0].value && nPassCultivate[1].value && nPassCultivate[2].value) {
                                var $saleNum = $("#nPassCultivateForm_nd").find("input[name='saleNum']");
                                $saleNum.val(nPassCultivate[0].value - nPassCultivate[1].value - nPassCultivate[2].value);
                                $saleNum.change();
                            }
                        });
                    });
                    $.each(nFutureCultivate,
                    function(i, e) {
                        var $this = $(this);
                        $this.on("change",
                        function() {
                        	var saleNum=nFutureCultivate[0].value - nFutureCultivate[1].value - nFutureCultivate[2].value;
                        	var $saleNum=$("#nFutureCultivateForm_cultivate").find("input[name='saleNum']");
                        	$saleNum.val(utils.number.toFixed(saleNum,2));
                        	$saleNum.change();
                        });
                    });
                    $.each(nFutureCultivateTotal,
                    function(i, e) {
                        var $this = $(this);
                        $this.on("change",
                        function() {
                            if (nFutureCultivateTotal[0].value && nFutureCultivateTotal[1].value) {
                            	var predictTotalProduce=nFutureCultivateTotal[0].value * nFutureCultivateTotal[1].value;
                            	var $predictTotalProduce=$("#nFutureCultivateForm_cultivate").find("input[name='predictTotalProduce']");
                            	$predictTotalProduce.val(utils.number.toFixed(predictTotalProduce,2));
                            	$predictTotalProduce.change();
                            }
                        });
                    });
                    $.each(incomePassCultivate,
                    function(i, e) {
                        var $this = $(this);
                        $this.on("change",
                        function() {
                            if (incomePassCultivate[0].value && incomePassCultivate[1].value) {
                            	var saleIncomeTotal=incomePassCultivate[0].value * incomePassCultivate[1].value;
                            	var  $saleIncomeTotal=$("#nPassCultivateForm_nd").find("input[name='saleIncomeTotal']");
                            	$saleIncomeTotal.val(utils.number.toFixed(saleIncomeTotal,2));
                            	$saleIncomeTotal.change();
                            }
                        });
                    });
                    $.each(nFutureCultivateSaleNum,
                    function(i, e) {
                        var $this = $(this);
                        $this.on("change",
                        function() {
                            if (nFutureCultivateSaleNum[0].value && nFutureCultivateSaleNum[1].value) {
                            	var saleIncomeTotal=nFutureCultivateSaleNum[0].value * nFutureCultivateSaleNum[1].value;
                            	var $saleIncomeTotal=$("#nFutureCultivateForm_cultivate").find("input[name='saleIncomeTotal']");
                            	$saleIncomeTotal.val(utils.number.toFixed(saleIncomeTotal,2));
                            	$saleIncomeTotal.change();
                            }
                        });
                    });
                    $.each(predictCultivate,
                    function(i, e) {
                        var $this = $(this);
                        $this.on("change",
                        function() {
                            if (predictCultivate[0].value && predictCultivate[1].value && predictCultivate[2].value) {
                            	var forecast=(parseFloat(predictCultivate[1].value * 2) + parseFloat(predictCultivate[2].value) + parseFloat(predictCultivate[0].value)) / 4;
                                $("#nPassCultivateForm_nd").find("input[name='forecast']").val(utils.number.toFixed(forecast,2));
                            }
                        });
                    });
                },
                caculateBreed: function() {
                    var nPassBreed = $("#nPassBreedForm_nd").find("input[name='saleNum'],input[name='salePrice']");
                    var nFutureBreed = $("#nFutureCultivateForm_breed").find("input[name='predictProduceTotal'],input[name='salePrice']");
                    var predictPassBreed = $("#nPassBreedForm_nd").find("input[name='highest'],input[name='lowest'],input[name='lastyear']");
                    $.each(nPassBreed,
                    function(i, e) {
                        var $this = $(this);
                        $this.on("change",
                        function() {
                            if (nPassBreed[0].value && nPassBreed[1].value) {
                            	var saleTotal=nPassBreed[0].value * nPassBreed[1].value;
                                var $saleTotal = $("#nPassBreedForm_nd").find("input[name='saleIncomeTotal']");
                                $saleTotal.val(utils.number.toFixed(saleTotal,2));
                                $saleTotal.change();
                            }
                        });
                    });
                    $.each(nFutureBreed,
                    function(i, e) {
                        var $this = $(this);
                        $this.on("change",
                        function() {
                            if (nFutureBreed[0].value && nFutureBreed[1].value) {
                            	var saleTotal=nFutureBreed[0].value * nFutureBreed[1].value;
                                var $saleTotal = $("#nFutureCultivateForm_breed").find("input[name='saleIncomeTotal']");
                                $saleTotal.val(utils.number.toFixed(saleTotal,2));
                                $saleTotal.change();
                            }
                        });
                    });
                    $.each(predictPassBreed,
                    function(i, e) {
                        var $this = $(this);
                        $this.on("change",
                        function() {
                            if (predictPassBreed[0].value && predictPassBreed[1].value && predictPassBreed[2].value) {
                                var $predict = $("#nPassBreedForm_nd").find("input[name='predict']");
                                var predict=(parseFloat(predictPassBreed[0].value) + predictPassBreed[1].value * 2 + parseFloat(predictPassBreed[2].value)) / 4;
                                $predict.val(utils.number.toFixed(predict,2));
                            }
                        });
                    });
                },
                initChangeRelative: function(){
                	$(document).on("click","#nFutureCultivateForm_cultivate select[name='relativeId']",function(e){
                		e.stopPropagation();
                		var relativeId = $(this).val();
                		if($(this).val()){
                			$("#nFutureCultivateForm_cultivate").find("input[name='predictSingleProduce']").prop("readonly",true);
                			$.ajax({
                				url: $$ctx + 'cultivateAndBreed/' + 'findRelativeCultivateMsg/' + relativeId,
                				dataType: 'JSON',
                				type: 'POST',
                				success: function(data) {
                					if(data&&data.success){
                						$("#nFutureCultivateForm_cultivate").find("input[name='predictSingleProduce']").val(data.data);
                					}else{
                						$("#nFutureCultivateForm_cultivate").find("input[name='predictSingleProduce']").val("0");
                					}
                					$("#nFutureCultivateForm_cultivate").find("input[name='predictSingleProduce']").change();
                				}
                			});
                		}else{
                			$("#nFutureCultivateForm_cultivate").find("input[name='predictSingleProduce']").val("0");
                			$("#nFutureCultivateForm_cultivate").find("input[name='predictSingleProduce']").prop("readonly",false);
                			$("#nFutureCultivateForm_cultivate").find("input[name='predictSingleProduce']").change();
                		}
                	});
                	$(document).on("click","#nFutureCultivateForm_breed select[name='relativeId']",function(e){
                		e.stopPropagation();
                		var relativeId = $(this).val();
                		if($(this).val()){
                			$("#nFutureCultivateForm_breed").find("input[name='predictProduceTotal']").prop("readonly",true);
                			$.ajax({
                				url: $$ctx + 'cultivateAndBreed/' + 'findRelativeBreedMsg/' + relativeId,
                				dataType: 'JSON',
                				type: 'POST',
                				success: function(data) {
                					if(data&&data.success){
                						$("#nFutureCultivateForm_breed").find("input[name='predictProduceTotal']").val(data.data);
                					}else{
                						$("#nFutureCultivateForm_breed").find("input[name='predictProduceTotal']").val("0");
                					}
                					$("#nFutureCultivateForm_breed").find("input[name='predictProduceTotal']").change();
                				}
                			});
                		}else{
                			$("#nFutureCultivateForm_breed").find("input[name='predictProduceTotal']").val("0");
                			$("#nFutureCultivateForm_breed").find("input[name='predictProduceTotal']").prop("readonly",false);
                			$("#nFutureCultivateForm_breed").find("input[name='predictProduceTotal']").change();
                		}
                	});
                },
                resetUnitField: function() {
                    $("div.input-group-btn").find("button:eq(0)").text("单位");
                    $("div.input-group-btn").siblings("input:hidden").val("");
                },
                copyUnitField: function() {
                    var hiddens = $("div.input-group").find("input:hidden[name$='Unit']");
                    $.each(hiddens,
                    function(i, e) {
                        var $this = $(this);
                        var li = $this.closest("div.input-group").find("li");
                        for (var i = 0; i < li.length; i++) {
                            if ($(li[i]).data('value') == $this.val()) {
                                $this.closest("div.input-group").find("button:eq(0)").text($(li[i]).text());
                            }
                        }
                    });
                },
                validateUnit: function(form) {
                    var units = $(form).find("input:hidden[name$='Unit']");
                    var flag = true;
                    $.each(units,
                    function(i, e) {
                        var $this = $(this);
                        if (!$this.val() || $this.val() == "0") {
                            flag = false;
                        }
                    });
                    if (!flag) {
                        utils.alert.warn("请选择输入内容对应的单位！");
                    }
                    return flag;
                }

            };
            formFunc.initUnitField();
            formFunc.caculateCultivate();
            formFunc.caculateBreed();
            formFunc.initChangeRelative();
        },
        
        
        initOverIncomeTable: function() {
            var viewSelf = this;
            var oTable = $("#overincome_n_cultivate_tb").dataTable({
                "sAjaxSource": $$ctx + "industryBiz/initOtherIncomeCommonTable",
                "bFilter": false,
                "bAutoWidth": true,
                "bLengthChange": false,
                "bPaginate": false,   
               // "bInfo":false,
                "aoColumns": [{
                    "bVisible": false,
                    "mData": "id"
                },
                {
                    "mData": "time",
                    mRender: function(data, type, rowdata) {
                        if (data) {
                            return utils.date.formatDate(data);
                        } else {
                            return "-";
                        }
                    }
                },
                {
                    "mData": "content"
                },
                {
                    "mData": "amount",
                    mRender: function(data, type, rowdata) {
                        return data.toFixed(2);
                    }
                },
                {
                    "mData": "id",
                    mRender: function(data, type, rowdata) {
                        var buttons = "<button type='button' role='edit_NIncome_cul' data-id='" + rowdata.id + "' data-type='" + rowdata.type + "' class='btn btn-xs btn-info' title='修改'><i class='ace-icon fa fa-edit' ></i></button> " + "<button type='button' role='detail_NIncome_cul' data-id='" + rowdata.id + "' data-type='" + rowdata.type + "' class='btn btn-xs btn-yellow' title='查看'><i class='ace-icon fa fa-eye' ></i></button> " + "<button type='button' role='delete_NIncome_cul' data-id='" + rowdata.id + "' class='btn btn-xs btn-danger' data-type='" + rowdata.type + "' title='删除'><i class='ace-icon fa fa-trash-o'></i></button>";
                        return buttons;
                    }
                }],
                "fnServerParams": function(aoData) {
                    aoData.push({
                        "name": "projectId",
                        "value": getHidden("projectId")
                    },
                    {
                        "name": "type",
                        "value": "1"
                    });
                },
                "fnDrawCallback": function() {
                    formFunc.caculateCount(this);
                    $(this).wrap("<div style='overflow-x: auto;overflow-y: hidden;'></div>");
                }
            });
            viewSelf.oTable = oTable;

            var oTable = $("#overincome_n_breed_tb").dataTable({
                "sAjaxSource": $$ctx + "industryBiz/initOtherIncomeCommonTable",
                "bFilter": false,
                "bAutoWidth": true,
                "bLengthChange": false,
                "bPaginate": false,  
                "aoColumns": [{
                    "bVisible": false,
                    "mData": "id"
                },
                {
                    "mData": "time",
                    mRender: function(data, type, rowdata) {
                        if (data) {
                            return utils.date.formatDate(data);
                        } else {
                            return "-";
                        }
                    }
                },
                {
                    "mData": "content"
                },
                {
                    "mData": "amount",
                    mRender: function(data, type, rowdata) {
                        return data.toFixed(2);
                    }
                },
                {
                    "mData": "id",
                    mRender: function(data, type, rowdata) {
                        var buttons = "<button type='button' role='edit_NIncome_breed' data-id='" + rowdata.id + "' data-type='" + rowdata.type + "' class='btn btn-xs btn-info' title='修改'><i class='ace-icon fa fa-edit' ></i></button> " + "<button type='button' role='detail_NIncome_breed' data-id='" + rowdata.id + "' data-type='" + rowdata.type + "' class='btn btn-xs btn-yellow' title='查看'><i class='ace-icon fa fa-eye' ></i></button> " + "<button type='button' role='delete_NIncome_breed' data-id='" + rowdata.id + "' class='btn btn-xs btn-danger' data-type='" + rowdata.type + "' title='删除'><i class='ace-icon fa fa-trash-o'></i></button>";
                        return buttons;
                    }
                }],
                "fnServerParams": function(aoData) {
                    aoData.push({
                        "name": "projectId",
                        "value": getHidden("projectId")
                    },
                    {
                        "name": "type",
                        "value": "2"
                    });
                },
                "fnDrawCallback": function() {
                    formFunc.caculateCount(this);
                    $(this).wrap("<div style='overflow-x: auto;overflow-y: hidden;'></div>");
                }
            });
            viewSelf.oTable = oTable;

            $(document).on("click", "button[role='edit_NIncome_cul']",
            function(e) {
                var id = $(this).data("id");
                $.ajax({
                    type: 'POST',
                    url: $$ctx + 'industryBiz/findOneOtherIncomeCommon',
                    data: {
                        'id': id
                    },
                    success: function(result) {
                        $.each($("#other_income_common_form").find("input[type=text], input[type=hidden], select, textarea"),
                        function() {
                            if ($(this).attr("name") == "time") {
                                $(this).val(utils.date.formatDate(result[$(this).attr("name")]));
                            } else {
                                $(this).val(result[$(this).attr("name")]);
                            }
                            $(this).removeAttr("disabled");
                        });
                        $("#other_income_common_modal div.modal-header h3").html("<i class='ace-icon fa fa-edit'></i> 成本费用明细（种植业）");
                        $("#other_income_common_form").find(".form-group:eq(1)").hide();
                        $("#other_income_common_modal").find(".modal-footer").show();
                        $("#other_income_common_modal").modal("show");
                    }
                });
            });
            $(document).on("click", "button[role='detail_NIncome_cul']",
            function(e) {
                var id = $(this).data("id");
                $.ajax({
                    type: 'POST',
                    url: $$ctx + 'industryBiz/findOneOtherIncomeCommon',
                    data: {
                        'id': id
                    },
                    success: function(result) {
                        $.each($("#other_income_common_form").find("input[type=text], input[type=hidden], select, textarea"),
                        function() {
                            if ($(this).attr("name") == "time") {
                                $(this).val(utils.date.formatDate(result[$(this).attr("name")]));
                            } else {
                                $(this).val(result[$(this).attr("name")]);
                            }
                            $(this).attr("disabled", "disabled");
                        });
                        $("#other_income_common_modal div.modal-header h3").html("<i class='ace-icon fa fa-eye'></i> 成本费用明细（种植业）");
                        $("#other_income_common_form").find(".form-group:eq(1)").hide();
                        $("#other_income_common_modal").find(".modal-footer").hide();
                        $("#other_income_common_modal").modal("show");
                    }
                });
            });
            $(document).on("click", "button[role='delete_NIncome_cul']",
            function(e) {
                var id = $(this).data("id");
                bootbox.confirm({
                    message: "您确定要删除此记录吗？",
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
                            $.ajax({
                                type: 'post',
                                url: $$ctx + 'industryBiz/deleteOtherIncomeCommon',
                                data: {
                                    'id': id
                                },
                                success: function(r) {
                                    if (r.success) {
                                        utils.alert.suc(r.msg);
                                        $("#overincome_n_cultivate_tb").dataTable().fnDraw();
                                        $("#statisticsTable").dataTable().fnDraw();
                                    } else {
                                        utils.alert.err(r.msg);
                                    }
                                }
                            });
                        }
                    }
                });
            });
            $(document).on("click", "button[role='edit_NIncome_breed']",
            function(e) {
                var id = $(this).data("id");
                $.ajax({
                    type: 'POST',
                    url: $$ctx + 'industryBiz/findOneOtherIncomeCommon',
                    data: {
                        'id': id
                    },
                    success: function(result) {
                        $.each($("#other_income_common_form").find("input[type=text], input[type=hidden], select, textarea"),
                        function() {
                            if ($(this).attr("name") == "time") {
                                $(this).val(utils.date.formatDate(result[$(this).attr("name")]));
                            } else {
                                $(this).val(result[$(this).attr("name")]);
                            }
                            $(this).removeAttr("disabled");
                        });
                        $("#other_income_common_modal div.modal-header h3").html("<i class='ace-icon fa fa-edit'></i> 成本费用明细（养殖业）");
                        $("#other_income_common_form").find(".form-group:eq(1)").hide();
                        $("#other_income_common_modal").find(".modal-footer").show();
                        $("#other_income_common_modal").modal("show");
                    }
                });
            });
            $(document).on("click", "button[role='detail_NIncome_breed']",
            function(e) {
                var id = $(this).data("id");
                $.ajax({
                    type: 'POST',
                    url: $$ctx + 'industryBiz/findOneOtherIncomeCommon',
                    data: {
                        'id': id
                    },
                    success: function(result) {
                        $.each($("#other_income_common_form").find("input[type=text], input[type=hidden], select, textarea"),
                        function() {
                            if ($(this).attr("name") == "time") {
                                $(this).val(utils.date.formatDate(result[$(this).attr("name")]));
                            } else {
                                $(this).val(result[$(this).attr("name")]);
                            }
                            $(this).attr("disabled", "disabled");
                        });
                        $("#other_income_common_modal div.modal-header h3").html("<i class='ace-icon fa fa-eye'></i> 成本费用明细（养殖业）");
                        $("#other_income_common_form").find(".form-group:eq(1)").hide();
                        $("#other_income_common_modal").find(".modal-footer").hide();
                        $("#other_income_common_modal").modal("show");
                    }
                });
            });
            $(document).on("click", "button[role='delete_NIncome_breed']",
            function(e) {
                var id = $(this).data("id");
                bootbox.confirm({
                    message: "您确定要删除此记录吗？",
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
                            $.ajax({
                                type: 'post',
                                url: $$ctx + 'industryBiz/deleteOtherIncomeCommon',
                                data: {
                                    'id': id
                                },
                                success: function(r) {
                                    if (r.success) {
                                        utils.alert.suc(r.msg);
                                        $("#overincome_n_breed_tb").dataTable().fnDraw();
                                        $("#statisticsTable").dataTable().fnDraw();
                                    } else {
                                        utils.alert.err(r.msg);
                                    }
                                }
                            });
                        }
                    }
                });
            });
        },
        initFbCultivateTable: function() {
            var viewSelf = this;
            utils.dd.initDataDict(["AgroFnCultivateType"],
            function(dataDict) {
                var oTable = $("#n_culitivate_pass_table").dataTable({
                    "sAjaxSource": $$ctx + "cultivateAndBreed/findNongPassCultivateBySearch",
                    "bFilter": false,
                    "bPaginate": false,
                    "bAutoWidth": true,
                    "bLengthChange": false,
                    "oLanguage": {
                        "sInfo": "",
                    },
                    "aoColumns": [{
                        "bVisible": false,
                        "mData": "id"
                    },
                    {
                        "mData": "cultivateType",
                        mRender: function(data, type, rowdata) {
                            if (data) {
                                return dataDict.AgroFnCultivateType[data];
                            } else {
                                return "-";
                            }
                        }
                    },
                    {
                        "mData": "cultivateContent"
                    },
                    {
                        "mData": "cultivateScale"
                    },
                    { //年总产量
                        "mData": "output"
                    },
                    { //家庭消耗
                        "mData": "familyConsume"
                    },
                    { //牲畜消耗量
                        "mData": "livestockConsume"
                    },
                    { //销售数量
                        "mData": "saleNum"
                    },
                    { //销售单价
                        "mData": "salePrice"
                    },
                    { //销售收入合计
                        "mData": "saleIncomeTotal"
                    },
                    { //成本合计
                        "mData": "costTotal"
                    },
                    { //成本合计
                        "mData": "cropValue"
                    },
                    { //成本合计
                        "bVisible": false,
                        "mData": "agricultureCapitalValue"
                    },
                    {
                        "mData": "id",
                        mRender: function(data, type, rowdata) {
                            /*var buttons = "<button type='button' role='edit_fbpZz' data-id='" + rowdata.id + "' data-type='" + rowdata.type + "' class='btn btn-xs btn-info' title='修改'><i class='ace-icon fa fa-edit' ></i></button> " + "<button type='button' role='detail_fbpZz' data-id='" + rowdata.id + "' data-type='" + rowdata.type + "' class='btn btn-xs btn-yellow' title='查看'><i class='ace-icon fa fa-eye' ></i></button> " + "<button type='button' role='delete_fbpZz' data-id='" + rowdata.id + "' class='btn btn-xs btn-danger' data-type='" + rowdata.type + "' title='删除'><i class='ace-icon fa fa-trash-o'></i></button>";
                            return buttons;*/
                        	//modify by LBQ 20150709 Start 修改权限控制
                            var html=[];
                            if(viewSelf.isEdit){
                            	html.push("<button type='button' role='edit_fbpZz' data-id='");
                            	html.push(rowdata.id);
                            	html.push("' data-type='");
                            	html.push(rowdata.type);
                            	html.push("' class='btn btn-xs btn-info' title='修改'><i class='ace-icon fa fa-edit' ></i></button> ");
                            }
                            
                            html.push("<button type='button' role='detail_fbqZz' data-id='");
                            html.push(rowdata.id);
                            html.push("' data-type='");
                            html.push(rowdata.type);
                            html.push("' class='btn btn-xs btn-yellow' title='查看'><i class='ace-icon fa fa-eye' ></i></button> ");
                            
                            if(viewSelf.isEdit){
	                            html.push("<button type='button' role='delete_fbqZz' data-id='");
	                            html.push(rowdata.id);
	                            html.push("' class='btn btn-xs btn-danger' data-type='");
	                            html.push(rowdata.type);
	                            html.push("' title='删除'><i class='ace-icon fa fa-trash-o'></i></button>");
                            }
                            //modify by LBQ 20150709 end 修改权限控制
                            return html.join('');
                        }
                    }],
                    "fnServerParams": function(aoData) {
                        aoData.push({
                            "name": "projectId",
                            "value": getHidden("projectId")
                        });
                    },
                    "fnDrawCallback": function() {
                        formFunc.caculateCount(this);
                        $(this).wrap("<div style='overflow-x: auto;overflow-y: hidden;'></div>")
                    }
                });
                viewSelf.oTable = oTable;
            });

            utils.dd.initDataDict(["AgroFnCultivateType"],
            function(dataDict) {
                var oTable = $("#n_culitivate_future_table").dataTable({
                    "sAjaxSource": $$ctx + "cultivateAndBreed/findNongFutureCultivateBySearch",
                    "bFilter": false,
                    "bPaginate": false,
                    "bAutoWidth": true,
                    "bLengthChange": false,
                    "oLanguage": {
                        "sInfo": "",
                    },
                    "aoColumns": [{
                        "bVisible": false,
                        "mData": "id"
                    },
                    {
                        "mData": "cultivateType",
                        mRender: function(data, type, rowdata) {
                            if (data) {
                                return dataDict.AgroFnCultivateType[data];
                            } else {
                                return "-";
                            }
                        }
                    },
                    {
                        "mData": "cultivateContent"
                    },
                    {
                        "mData": "cultivateScale"
                    },
                    { //预计单产产量
                        "mData": "predictSingleProduce"
                    },
                    { //预计年总产量
                        "mData": "predictTotalProduce"
                    },
                    { //牲畜消耗量
                        "mData": "familyConsume"
                    },
                    { //销售数量
                        "mData": "livestockConsume"
                    },
                    { //销售单价
                        "mData": "saleNum"
                    },
                    { //销售收入合计
                        "mData": "salePrice"
                    },
                    { //成本合计
                        "mData": "saleIncomeTotal"
                    },
                    { //成本合计
                        "mData": "predictCostTotal"
                    },
                    { //成本合计
                        "mData": "predictSaleTime"
                    },
                    {
                        "mData": "id",
                        mRender: function(data, type, rowdata) {
                            /*var buttons = "<button type='button' role='edit_fbfZz' data-id='" + rowdata.id + "' data-type='" + rowdata.type + "' class='btn btn-xs btn-info' title='修改'><i class='ace-icon fa fa-edit' ></i></button> " + "<button type='button' role='detail_fbfZz' data-id='" + rowdata.id + "' data-type='" + rowdata.type + "' class='btn btn-xs btn-yellow' title='查看'><i class='ace-icon fa fa-eye' ></i></button> " + "<button type='button' role='delete_fbfZz' data-id='" + rowdata.id + "' class='btn btn-xs btn-danger' data-type='" + rowdata.type + "' title='删除'><i class='ace-icon fa fa-trash-o'></i></button>";*/
                            //modify by HWL 20150706 Start 修改权限控制
                            var html=[];
                            if(viewSelf.isEdit){
                            	html.push("<button type='button' role='edit_fbfZz' data-id='");
                            	html.push(rowdata.id);
                            	html.push("' data-type='");
                            	html.push(rowdata.type);
                            	html.push("' class='btn btn-xs btn-info' title='修改'><i class='ace-icon fa fa-edit' ></i></button> ");
                            }
                            
                            html.push("<button type='button' role='detail_fbfZz' data-id='");
                            html.push(rowdata.id);
                            html.push("' data-type='");
                            html.push(rowdata.type);
                            html.push("' class='btn btn-xs btn-yellow' title='查看'><i class='ace-icon fa fa-eye' ></i></button> ");
                            
                            if(viewSelf.isEdit){
	                            html.push("<button type='button' role='delete_fbfZz' data-id='");
	                            html.push(rowdata.id);
	                            html.push("' class='btn btn-xs btn-danger' data-type='");
	                            html.push(rowdata.type);
	                            html.push("' title='删除'><i class='ace-icon fa fa-trash-o'></i></button>");
                            }
                            //modify by HWL 20150706 end 修改权限控制
                            return html.join('');
                        }
                    }],
                    "fnServerParams": function(aoData) {
                        aoData.push({
                            "name": "projectId",
                            "value": getHidden("projectId")
                        });
                    },
                    "fnDrawCallback": function() {
                        formFunc.caculateCount(this);
                        $(this).wrap("<div style='overflow-x: auto;overflow-y: hidden;'></div>")
                    }
                });
                viewSelf.oTable = oTable;
            });
        },
        initFbBreedTable: function() {
            var viewSelf = this;
            utils.dd.initDataDict(["AgroFnBreedType"],
            function(dataDict) {
                var oTable = $("#n_breed_pass_table").dataTable({
                    "sAjaxSource": $$ctx + "cultivateAndBreed/findNongPassBreedBySearch",
                    "bFilter": false,
                    "bPaginate": false,
                    "bAutoWidth": true,
                    "bLengthChange": false,
                    "oLanguage": {
                        "sInfo": "",
                    },
                    "aoColumns": [{
                        "bVisible": false,
                        "mData": "id"
                    },
                    {
                        "mData": "breedType",
                        mRender: function(data, type, rowdata) {
                            if (data) {
                                return dataDict.AgroFnBreedType[data];
                            } else {
                                return "-";
                            }
                        }
                    },
                    {
                        "mData": "breedContent"
                    },
                    {
                        "mData": "breedMode"
                    },
                    { //预计单产产量
                        "mData": "breedScale"
                    },
                    { //预计年总产量
                        "mData": "saleNum"
                    },
                    { //牲畜消耗量
                        "mData": "salePrice"
                    },
                    { //销售数量
                        "mData": "saleIncomeTotal"
                    },
                    { //销售单价
                        "mData": "costTotal"
                    },
                    { //销售收入合计
                        "mData": "stockInitScale"
                    },
                    { //成本合计
                        "mData": "breedStockValue"
                    },
                    { //成本合计
                        "mData": "forageValue"
                    },
                    {
                        "mData": "id",
                        mRender: function(data, type, rowdata) {
                            /*var buttons = "<button type='button' role='edit_fbpYz' data-id='" + rowdata.id + "' data-type='" + rowdata.type + "' class='btn btn-xs btn-info' title='修改'><i class='ace-icon fa fa-edit' ></i></button> " + "<button type='button' role='detail_fbpYz' data-id='" + rowdata.id + "' data-type='" + rowdata.type + "' class='btn btn-xs btn-yellow' title='查看'><i class='ace-icon fa fa-eye' ></i></button> " + "<button type='button' role='delete_fbpYz' data-id='" + rowdata.id + "' class='btn btn-xs btn-danger' data-type='" + rowdata.type + "' title='删除'><i class='ace-icon fa fa-trash-o'></i></button>";
                            return buttons;*/
                        	//modify by LBQ 20150709 Start 修改权限控制
                        	var html=[];
                            if(viewSelf.isEdit){
                            	html.push("<button type='button' role='edit_fbpYz' data-id='");
                            	html.push(rowdata.id);
                            	html.push("' data-type='");
                            	html.push(rowdata.type);
                            	html.push("' class='btn btn-xs btn-info' title='修改'><i class='ace-icon fa fa-edit' ></i></button> ");
                            }
                            
                            html.push("<button type='button' role='detail_fbpYz' data-id='");
                            html.push(rowdata.id);
                            html.push("' data-type='");
                            html.push(rowdata.type);
                            html.push("' class='btn btn-xs btn-yellow' title='查看'><i class='ace-icon fa fa-eye' ></i></button> ");
                            
                            if(viewSelf.isEdit){
	                            html.push("<button type='button' role='delete_fbpYz' data-id='");
	                            html.push(rowdata.id);
	                            html.push("' class='btn btn-xs btn-danger' data-type='");
	                            html.push(rowdata.type);
	                            html.push("' title='删除'><i class='ace-icon fa fa-trash-o'></i></button>");
                            }
                            //modify by LBQ 20150709 End 修改权限控制
                            return html.join('');
                        }
                    }],
                    "fnServerParams": function(aoData) {
                        aoData.push({
                            "name": "projectId",
                            "value": getHidden("projectId")
                        });
                    },
                    "fnDrawCallback": function() {
                        formFunc.caculateCount(this);
                        $(this).wrap("<div style='overflow-x: auto;overflow-y: hidden;'></div>")
                    }
                });
                viewSelf.oTable = oTable;
            });

            utils.dd.initDataDict(["AgroFnBreedType"],
            function(dataDict) {
                var oTable = $("#n_breed_future_table").dataTable({
                    "sAjaxSource": $$ctx + "cultivateAndBreed/findNongFutureBreedBySearch",
                    "bFilter": false,
                    "bPaginate": false,
                    "bAutoWidth": true,
                    "bLengthChange": false,
                    "oLanguage": {
                        "sInfo": "",
                    },
                    "aoColumns": [{
                        "bVisible": false,
                        "mData": "id"
                    },
                    {
                        "mData": "breedType",
                        mRender: function(data, type, rowdata) {
                            if (data) {
                                return dataDict.AgroFnBreedType[data];
                            } else {
                                return "-";
                            }
                        }
                    },
                    {
                        "mData": "breedContent"
                    },
                    {
                        "mData": "breedMode"
                    },
                    { //预计单产产量
                        "mData": "predictProduceTotal"
                    },
                    { //预计年总产量
                        "mData": "salePrice"
                    },
                    { //销售数量
                        "mData": "saleIncomeTotal"
                    },
                    { //销售单价
                        "mData": "costTotal"
                    },
                    { //销售收入合计
                        "mData": "predictSaleTime"
                    },
                    {
                        "mData": "id",
                        mRender: function(data, type, rowdata) {
                            /*var buttons = "<button type='button' role='edit_fbfYz' data-id='" + rowdata.id + "' data-type='" + rowdata.type + "' class='btn btn-xs btn-info' title='修改'><i class='ace-icon fa fa-edit' ></i></button> " + "<button type='button' role='detail_fbfYz' data-id='" + rowdata.id + "' data-type='" + rowdata.type + "' class='btn btn-xs btn-yellow' title='查看'><i class='ace-icon fa fa-eye' ></i></button> " + "<button type='button' role='delete_fbfYz' data-id='" + rowdata.id + "' class='btn btn-xs btn-danger' data-type='" + rowdata.type + "' title='删除'><i class='ace-icon fa fa-trash-o'></i></button>";*/
                        	//modify by HWL 20150706 Start 修改权限控制
                        	var html=[];
                            if(viewSelf.isEdit){
                            	html.push("<button type='button' role='edit_fbfYz' data-id='");
                            	html.push(rowdata.id);
                            	html.push("' data-type='");
                            	html.push(rowdata.type);
                            	html.push("' class='btn btn-xs btn-info' title='修改'><i class='ace-icon fa fa-edit' ></i></button> ");
                            }
                            
                            html.push("<button type='button' role='detail_fbfYz' data-id='");
                            html.push(rowdata.id);
                            html.push("' data-type='");
                            html.push(rowdata.type);
                            html.push("' class='btn btn-xs btn-yellow' title='查看'><i class='ace-icon fa fa-eye' ></i></button> ");
                            
                            if(viewSelf.isEdit){
	                            html.push("<button type='button' role='delete_fbfYz' data-id='");
	                            html.push(rowdata.id);
	                            html.push("' class='btn btn-xs btn-danger' data-type='");
	                            html.push(rowdata.type);
	                            html.push("' title='删除'><i class='ace-icon fa fa-trash-o'></i></button>");
                            }
                            //modify by HWL 20150706 End 修改权限控制
                            return html.join('');
                        }
                    }],
                    "fnServerParams": function(aoData) {
                        aoData.push({
                            "name": "projectId",
                            "value": getHidden("projectId")
                        });
                    },
                    "fnDrawCallback": function() {
                        formFunc.caculateCount(this);
                        $(this).wrap("<div style='overflow-x: auto;overflow-y: hidden;'></div>")
                    }
                });
                viewSelf.oTable = oTable;
            });
        },
        initCultivateForm: function() {
            $("#nFutureCultivateForm_cultivate").validate({
                rules: rm_plant.futureRules,
                messages: rm_plant.messages,
                errorPlacement: function(error, element) {
                    if (element.is(":radio")) {
                        error.appendTo(element.parent());
                    } else if (element.is(":checkbox")) {
                        error.appendTo(element.parent().parent().parent().next());
                    } else if (element.next().is("span[class='input-group-addon']")) {
                        error.appendTo(element.parent().parent().parent());
                    } else if (element.next().next().is("div[class='input-group-btn']")) {
                        element.parent().after(error);
                    } else {
                        error.appendTo(element.parent());
                    }
                }
            });
            $("#nPassCultivateForm_nd").validate({
                rules: rm_plant.passRules,
                messages: rm_plant.messages,
                errorPlacement: function(error, element) {
                    if (element.is(":radio")) {
                        error.appendTo(element.parent());
                    } else if (element.is(":checkbox")) {
                        error.appendTo(element.parent().parent().parent().next());
                    } else if (element.next().is("span[class='input-group-addon']")) {
                        error.appendTo(element.parent().parent().parent());
                    } else if (element.next().next().is("div[class='input-group-btn']")) {
                        element.parent().after(error);
                    } else {
                        error.appendTo(element.parent());
                    }
                }
            });
        },
        initBreedForm: function() {
            $("#nFutureCultivateForm_breed").validate({
                rules: rm_breed.futureRules,
                messages: rm_breed.messages,
                errorPlacement: function(error, element) {
                    if (element.is(":radio")) {
                        error.appendTo(element.parent());
                    } else if (element.is(":checkbox")) {
                        error.appendTo(element.parent().parent().parent().next());
                    } else if (element.next().is("span[class='input-group-addon']")) {
                        error.appendTo(element.parent().parent().parent());
                    } else if (element.next().next().is("div[class='input-group-btn']")) {
                        element.parent().after(error);
                    } else {
                        error.appendTo(element.parent());
                    }
                }
            });
            $("#nPassBreedForm_nd").validate({
                rules: rm_breed.passRules,
                messages: rm_breed.messages,
                errorPlacement: function(error, element) {
                    if (element.is(":radio")) {
                        error.appendTo(element.parent());
                    } else if (element.is(":checkbox")) {
                        error.appendTo(element.parent().parent().parent().next());
                    } else if (element.next().is("span[class='input-group-addon']")) {
                        error.appendTo(element.parent().parent().parent());
                    } else if (element.next().next().is("div[class='input-group-btn']")) {
                        element.parent().after(error);
                    } else {
                        error.appendTo(element.parent());
                    }
                }
            });
        },
        initEditCultivateBtn: function() {
            var viewSelf = this;
            $(document).on("click", "button[role='edit_fbpZz'],button[role='edit_fbfZz']",
            function(e) {
                var $this = $(this);
                viewSelf.model.findOneCultivate({
                    "id": $this.data('id')
                },
                function(r) {
                    if (r && $this.data('type') == "1") {
                        formFunc.resetForm("#nPassCultivateForm_nd", r);
                        formFunc.copyUnitField();
                        $("#modal-formNCultivate_pass").find("button.btn-sm[tabindex='-1']").prop("disabled", false);
                        $("#modal-formNCultivate_pass div.modal-header h4").html("<i class='ace-icon fa fa-edit'></i> 种植业");
                        $("#modal-formNCultivate_pass").modal("show");
                    } else if (r && $this.data('type') == "2") {
                        viewSelf.loadRelative("cultivate", $this.data('id'));
                        formFunc.resetForm("#nFutureCultivateForm_cultivate", r);
                        $("nFutureCultivateForm_nd select[name='relativeId']").val(r["relativeId"]);
                        formFunc.copyUnitField();
                        $("#modal-formNCultivate_future").find("button.btn-sm[tabindex='-1']").prop("disabled", false);
                        $("#modal-formNCultivate_future div.modal-header h4").html("<i class='ace-icon fa fa-edit'></i> 种植业（未来12个月全年）");
                        
                        //显示下一步的图标
                        $(".btn-last-step").html("下一步 <i class='ace-icon fa fa-arrow-right icon-on-right'></i>");
                        //隐藏x关闭按钮
                        $("#modal-formNCultivate_future div.modal-header button").remove();
                        $("#modal-formNCultivate_future div.modal-header h4").before("<button data-dismiss='modal' class='close nfuture-close' type='button'>×</button>");
                        //显示保存按钮
                        $("#submit-n-cultivate_future").removeAttr('style');
                        $('#fuelux-wizard').data('operate','modify');
                        //显示添加收入/支出按钮
                        $("#btn-addEcfIncome_nd").removeAttr("style");
                        $("#btn-addEcfConsume_nd").removeAttr("style");
                        //显示操作label
                        $("#income_tb_cultivate thead tr th").last().removeAttr('style');
                        $("#out_tb_cultivate thead tr th").last().removeAttr('style');
                        
//                        $("#income_tb_cultivate :input[name='objId']").val($this.data('id'));
                        
                        $("#modal-formNCultivate_future").modal("show");
                    } else {
                        utils.alert.warn("记录已不存在，请联系管理员");
                    }
                });
            });
        },
        initEditBreedBtn: function() {
            var viewSelf = this;
            $(document).on("click", "button[role='edit_fbpYz'],button[role='edit_fbfYz']",
            function(e) {
                var $this = $(this);
                viewSelf.model.findOneBreed({
                    "id": $this.data('id')
                },
                function(r) {
                    if (r && $this.data('type') == "1") {
                        formFunc.resetForm("#nPassBreedForm_nd", r);
                        formFunc.copyUnitField();
                        $("#modal-formNBreed_pass").find("button.btn-sm[tabindex='-1']").prop("disabled", false);
                        $("#modal-formNBreed_pass div.modal-header h4").html("<i class='ace-icon fa fa-edit'></i> 养殖业");
                        $("#modal-formNBreed_pass").modal("show");
                    } else if (r && $this.data('type') == "2") {
                        viewSelf.loadRelative("breed", $this.data('id'));
                        formFunc.resetForm("#nFutureCultivateForm_breed", r);
                        formFunc.copyUnitField();
                        $("#modal-formNBreed_future").find("button.btn-sm[tabindex='-1']").prop("disabled", false);
                        $("#modal-formNBreed_future div.modal-header h4").html("<i class='ace-icon fa fa-edit'></i> 养殖业");
                        
                        //显示下一步的图标
                        $(".btn-last-step").html("下一步 <i class='ace-icon fa fa-arrow-right icon-on-right'></i>");
                        //隐藏x关闭按钮
                        $("#modal-formNBreed_future div.modal-header button").remove();
                        $("#modal-formNBreed_future div.modal-header h4").before("<button data-dismiss='modal' class='close nfuture-close' type='button'>×</button>");
                        
                        //显示保存按钮
                        $("#submit-n-cultivate_breed").removeAttr('style');
                        $("#breed-fuelux-wizard").data('operate','modify');
                        //显示添加收入/支出按钮
                        $("#btn-addEcfIncome-breed").removeAttr("style");
                        $("#btn-addEcfConsume-breed").removeAttr("style");
                        //显示操作label
                        $("#income_tb_breed thead tr th").last().removeAttr('style');
                        $("#out_tb_breed thead tr th").last().removeAttr('style');
                        
                        $("#modal-formNBreed_future").modal("show");
                    } else {
                        utils.alert.warn("记录已不存在，请联系管理员");
                    }
                });
            });
        },
        initDetailCultivateBtn: function() {
            var viewSelf = this;
            $(document).on("click", "button[role='detail_fbpZz'],button[role='detail_fbfZz']",
            function(e) {
                var $this = $(this);
                viewSelf.model.findOneCultivate({
                    "id": $this.data('id')
                },
                function(r) {
                    if (r && $this.data('type') == "1") {
                        formFunc.disableForm("#nPassCultivateForm_nd", r);
                        formFunc.copyUnitField();
                        $("#modal-formNCultivate_pass").find("button.btn-sm[tabindex='-1']").prop("disabled", true);
                        $("#modal-formNCultivate_pass div.modal-header h4").html("<i class='ace-icon fa fa-eye'></i> 种植业");
                        $("#modal-formNCultivate_pass").modal("show");
                    } else if (r && $this.data('type') == "2") {
                        viewSelf.loadRelative("cultivate", $this.data('id'));
                        formFunc.disableForm("#nFutureCultivateForm_cultivate", r);
                        
                        formFunc.copyUnitField();
                        $("#modal-formNCultivate_future").find("button.btn-sm[tabindex='-1']").prop("disabled", true);
                        $("#modal-formNCultivate_future div.modal-header h4").html("<i class='ace-icon fa fa-eye'></i> 种植业");
                        //隐藏内容
                        viewSelf.hideContentCultivate();
                        //设置收入支出明细中不可修改
                        formFunc.setFormDisabled("#income_tb_cultivate,#out_tb_cultivate");
                        $("#modal-formNCultivate_future").modal("show");
                    } else {
                        utils.alert.warn("记录已不存在，请联系管理员");
                    }
                });
            });
        },
        hideContentCultivate:function(){//隐藏种植业内容
        	var viewSelf = this;
        	$('#fuelux-wizard').data('operate','detail');
        	//显示关闭按钮
            $("#modal-formNCultivate_future div.modal-header button").remove();
            $("#modal-formNCultivate_future div.modal-header h4").before("<button data-dismiss='modal' class='close nfuture-close' type='button'>×</button>");
            //显示下一步的图标
            $(".btn-last-step").html("下一步 <i class='ace-icon fa fa-arrow-right icon-on-right'></i>");
            
            //隐藏保存按钮
            $("#submit-n-cultivate_future").css("display","none");
            //显示下一步
            $("#modal-formNCultivate_future .wizard-actions .btn-next").attr("disabled",false);
            //隐藏添加收入/支出按钮
            $("#btn-addEcfIncome_nd").css("display","none");
            $("#btn-addEcfConsume_nd").css("display","none");
            //隐藏收入/支出操作按钮
            $("#income_tb_cultivate thead tr th").last().css("display","none");
            $("#out_tb_cultivate thead tr th").last().css("display","none");
            $("#income_tb_cultivate tbody tr td,#out_tb_cultivate tbody tr td").has('button').css("display","none");
            
        },
        hideContentBreed: function(){//隐藏养殖业内容
        	var viewSelf = this;
        	$("#breed-fuelux-wizard").data('operate','detail');
        	//显示关闭按钮
            $("#modal-formNBreed_future div.modal-header button").remove();
            $("#modal-formNBreed_future div.modal-header h4").before("<button data-dismiss='modal' class='close nfuture-close' type='button'>×</button>");
            //显示下一步的图标
            $(".btn-last-step").html("下一步 <i class='ace-icon fa fa-arrow-right icon-on-right'></i>");
            
            //隐藏保存按钮
            $("#submit-n-breed_future").css("display","none");
            
            //显示下一步
            $("#modal-formNBreed_future .wizard-actions .btn-next").attr("disabled",false);
            //隐藏添加收入/支出按钮
            $("#btn-addEcfIncome-breed").css("display","none");
            $("#btn-addEcfConsume-breed").css("display","none");
            //隐藏收入/支出操作按钮
            $("#income_tb_breed thead tr th").last().css("display","none");
            $("#out_tb_breed thead tr th").last().css("display","none");
            $("#income_tb_breed tbody tr td,#out_tb_breed tbody tr td").has('button').css("display","none");
        },
        nfutureClose: function(){
        	 var viewSelf = this;
        	 viewSelf.skipStep('#fuelux-wizard',1);
        	 viewSelf.skipStep('#breed-fuelux-wizard',1);
        	 
        	 //关闭x重新刷新表格
        	 $("#n_culitivate_future_table").dataTable().fnDraw();//种植业
        	 $("#n_breed_future_table").dataTable().fnDraw();//养殖业
             $("#statisticsTable").dataTable().fnDraw();//汇总表
             
             //设置下一步禁用
        	 $(".btn-last-step").attr('disabled', 'disabled')
        	 
        },
        skipStep: function(btn,step){//调到指定步骤
        	 var viewSelf = this;
        	 var wizard = $(btn).data('wizard')
             wizard.currentStep = step;
             wizard.setState();
        },
        initDetailBreedBtn: function() {
            var viewSelf = this;
            $(document).on("click", "button[role='detail_fbpYz'],button[role='detail_fbfYz']",
            function(e) {
                var $this = $(this);
                viewSelf.model.findOneBreed({
                    "id": $this.data('id')
                },
                function(r) {
                    if (r && $this.data('type') == "1") {
                        formFunc.disableForm("#nPassBreedForm_nd", r);
                        formFunc.copyUnitField();
                        $("#modal-formNBreed_pass").find("button.btn-sm[tabindex='-1']").prop("disabled", true);
                        $("#modal-formNBreed_pass div.modal-header h4").html("<i class='ace-icon fa fa-eye'></i> 养殖业");
                        $("#modal-formNBreed_pass").modal("show");
                    } else if (r && $this.data('type') == "2") {
                        viewSelf.loadRelative("breed", $this.data('id'));
                        formFunc.disableForm("#nFutureCultivateForm_breed", r);
                        
                        formFunc.copyUnitField();
                        $("#modal-formNBreed_future").find("button.btn-sm[tabindex='-1']").prop("disabled", true);
                        $("#modal-formNBreed_future div.modal-header h4").html("<i class='ace-icon fa fa-eye'></i> 养殖业");
                        //隐藏内容
                        viewSelf.hideContentBreed();
                        //设置收入支出明细不可修改
                        formFunc.setFormDisabled("#income_tb_breed,#out_tb_breed")
                        $("#modal-formNBreed_future").modal("show");
                    } else {
                        utils.alert.warn("记录已不存在，请联系管理员");
                    }
                });
            });
        },
        initDeleteCultivateBtn: function() {
            var viewSelf = this;
            $(document).on("click", "button[role='delete_fbfZz'],button[role='delete_fbpZz']",
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
                            viewSelf.model.deleteCultivate(id,
                            function(data) {
                                if (data.success) {
                                    utils.alert.suc("<strong>删除成功!</strong>",function() {});
                                	$("#n_culitivate_pass_table").dataTable().fnDraw();
                                	$("#n_culitivate_future_table").dataTable().fnDraw();
                                    var datatable = utils.datatable.fresh("#" + $this.closest('table').attr('id'));
                                } else {
                                    utils.alert.warn("<strong>" + data.msg + "</strong>");
                                }
                            });
                        }
                    }
                }));
                return false;
            });
        },
        initDeleteBreedBtn: function() {
            var viewSelf = this;
            $(document).on("click", "button[role='delete_fbfYz'],button[role='delete_fbpYz']",
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
                            viewSelf.model.deleteBreed(id,
                            function(data) {
                                if (data.success) {
                                	utils.alert.suc("<strong>"+data.msg+"</strong>",function() {});
                                    var datatable = utils.datatable.fresh("#" + $this.closest('table').attr('id'));
                                } else {
                                    utils.alert.warn("<strong>" + data.msg + "</strong>");
                                }
                            });
                        }
                    }
                }));
                return false;
            });
        },
        findIncomeList: function(){//收入列表
        	var viewSelf = this;
        	$(document).on("click","button[role='edit_fbfZz'],button[role='detail_fbfZz'],button[role='edit_fbfYz'],button[role='detail_fbfYz']",function(e){
        		 var $this = $(this);
        		 var objWay = $this.closest(".panel").data("objtype");
        		 viewSelf.model.findEcfiList({
        			 'projectId':$("#projectId").val(),
        			 'objId' : $this.data("id"),
        			 'objCode':null,
        			 'objType' :$this.closest(".panel").data("objid"),
        			 'flag':'1'
        		 },function(result){
        			 $("#income_tb_"+objWay+" tbody tr").not(".hj").remove();
        			 if(result.success && result.data.length > 0){
     		        	var newRow = "";
     		        	var _count = result.data.length;
     		        	for(var i =0; i< result.data.length; i++){
     		        		var date = utils.str.substringBeforeLast(result.data[i].monthOfYear,"-");
     		        		var time = "<input type='text' value='"+date+"' size='1' name='monthOfYear' class='form-control input-sm date-picker' data-date-format='yyyy-MM'/>";
         		        	var inputMoney = "<input type='text' value='"+result.data[i].amtMoney+"' size='1' name='amtMoney' data-culway='"+objWay+"' class='form-control input-sm'/>";
         		        	var inputName = "<input type='text' value='"+result.data[i].objContent+"' name='objContent' class='form-control input-sm' />";
         		        	var button = "<button type='button' role='modify_ecfi_"+objWay+"' data-eid='"+result.data[i].id+"' data-type='1'  class='btn btn-xs btn-info' title='修 改'><i class='ace-icon fa fa-floppy-o' ></i></button> "  
         		        					+ "<button type='button' role='delete_ecfi' data-id='"+result.data[i].id+"' title='删 除' data-id='' class='btn btn-xs btn-danger' ><i class='ace-icon fa fa-times'></i></button>";
         		        	newRow +="<tr class='odd'>" +
         		        		 "<td>"+(_count)+"</td>"+
         		        		 "<td>"+ time +"</td>" + //time
				     			 "<td>"+ inputMoney +"</td>" + //name
				     			 "<td>"+ inputName +"</td>" + //money
				     			 "<td>"+ button +"</td>" +"</tr>";
         		        	_count--;
     		        	}
     		        	$("#income_tb_"+objWay+" tbody .hj").before(newRow);
     		        	viewSelf.initDataPickers();
     		        	//清除总计
     		        	$("#income_tb_"+objWay+" tbody .hj .income-sum").text(0);
     		        	viewSelf.initIncomeSummation(objWay);
        			 }else{
        				 $("#income_tb_"+objWay+" tbody .hj .income-sum").text(0);
        			 }
        		 });
        	});
        },
        findConsumeList: function(){//支出列表
        	var viewSelf = this;
        	$(document).on("click","button[role='edit_fbfZz'],button[role='detail_fbfZz'],button[role='edit_fbfYz'],button[role='detail_fbfYz']",function(e){
        		 var $this = $(this);
        		 var objWay = $this.closest(".panel").data("objtype");
        		 viewSelf.model.findEcfiList({
        			 'projectId':$("#projectId").val(),
        			 'objId' : $this.data("id"),
        			 'objCode':null,
        			 'objType' :$this.closest(".panel").data("objid"),
        			 'flag':'2'
        		 },function(result){
        			 $("#out_tb_"+objWay+" tbody tr").not(".hj").remove();
        			 if(result.success && result.data.length > 0){
     		        	var newRow = "";
     		        	var _count = result.data.length;
     		        	var sumMoney;
     		        	for(var i =0; i< result.data.length; i++){
     		        		var date = utils.str.substringBeforeLast(result.data[i].monthOfYear,"-");
     		        		var time = "<input type='text' value='"+date+"' size='1' name='monthOfYear' class='form-control input-sm date-picker' data-date-format='yyyy-MM'/>";
         		        	var inputMoney = "<input type='text' value='"+result.data[i].amtMoney+"' size='1' name='amtMoney' data-culway='"+objWay+"' class='form-control input-sm'/>";
         		        	var inputName = "<input type='text' value='"+result.data[i].objContent+"' name='objContent' class='form-control input-sm' />";
         		        	var button = "<button type='button' role='modify_ecfi_"+objWay+"' data-eid='"+result.data[i].id+"' data-type='2'  class='btn btn-xs btn-info' title='修改'><i class='ace-icon fa fa-floppy-o' ></i></button> "  
         		        					+ "<button type='button' role='delete_ecfi' data-id='"+result.data[i].id+"' title='删 除' data-id='' class='btn btn-xs btn-danger' ><i class='ace-icon fa fa-times'></i></button>";
         		        	newRow +="<tr class='odd'>" +
         		        	 	 "<td>"+ (_count) +"</td>" + 
         		        		 "<td>"+ time +"</td>" + //time
				     			 "<td>"+ inputMoney +"</td>" + //name
				     			 "<td>"+ inputName +"</td>" + //money
				     			 "<td>"+ button +"</td>" +"</tr>";
         		        	_count--;
     		        	}
     		        	$("#out_tb_"+objWay+" tbody tr:first").before(newRow);
     		        	viewSelf.initDataPickers();
     		        	//清除总计
     		        	$("#out_tb_"+objWay+" tbody .hj .consume-sum").text(0);
     		        	viewSelf.initConsumeSummation(objWay);
        			 }else{
        				 $("#out_tb_"+objWay+" tbody .hj .consume-sum").text(0);
        			 }
        		 });
        	});
        },
        initIncomeSummation: function(type){//初始化收入合计
        	var viewSelf = this;
        	var sum =0;
        	if(type == null){
        		type ="cultivate"//默认显示第一个
        	}
    		$("#income_tb_"+type+" tbody tr").not(".hj").each(function(){
    			var money;
    			 if($(this).children().eq(2).children().val() ==""){
    				 money = 0;
    			 }else{
    				 money = parseFloat($(this).children().eq(2).children().val());
    			 }
       			 sum+=money;
       		 });
    		$("#income_tb_"+type+" tbody .hj .income-sum").text(sum.toFixed(2));
        },
        initConsumeSummation: function(type){//初始化支出合计
        	var viewSelf = this;
        	var sum =0;
        	if(type == null){
        		type ="cultivate"//默认显示第一个
        	}
        	
    		$("#out_tb_"+type+" tbody tr").not(".hj").each(function(){
    			var money;
	   			 if($(this).children().eq(2).children().val() ==""){
	   				 money = 0;
	   			 }else{
	   				 money = parseFloat($(this).children().eq(2).children().val());
	   			 }
	      		 sum+=money;
       		 });
    		$("#out_tb_"+type+" tbody .hj .consume-sum").text(sum.toFixed(2));
        },
        incomeSummation: function(){//收入合计
        	var viewSelf = this;
        	$(document).on("change", "input[name='amtMoney']",function(e){
        		var objway = $(this).data("culway");
        		viewSelf.initIncomeSummation(objway);
        	});
        	
        },
        consumeSummation: function(){//支出合计
        	var viewSelf = this;
        	$(document).on("change", "input[name='amtMoney']",function(e){
        		var objway = $(this).data("culway");
        		viewSelf.initConsumeSummation(objway);
        	});
        },
		deleteRowBtnLive: function(){
			var viewSelf = this;
            $(document).on("click", "button[role='delete_ecfi']",
            function(e) { // 动态绑定所有删除按钮的click事件
                var $this = $(this);
                var objway = utils.str.substringAfterLast($this.closest("table").attr('id'),"_");
                if (bootbox.confirm({
                    message: "确定要删除此条数据吗 ?",
                    buttons: {
                        confirm: {
                            label: "<i class='ace-icon fa fa-trash-o bigger-110'></i> 确定",
                            className: "btn-danger btn-sm"
                        },
                        cancel: {
                            label: "<i class='ace-icon fa fa-times bigger-110'></i> 取消",
                            className: "btn-sm"
                        }
                    },
                    callback: function(result) {
                        if (result) {
                            var id = $this.data("id");
                            viewSelf.model.deleteEcfi(id, function(data){
                            	if (data.success) {
                            		utils.alert.suc("删除成功！");
                            		$this.closest("tr").remove();
                            		                            		
                            		//重新刷新下合计
                            		viewSelf.initIncomeSummation(objway);
                            		viewSelf.consumeSummation(objway);
                                } else {
                                	utils.alert.warn("<strong>"+data.msg+"</strong>");
                                }
                            });
                        }
                    }
                }));
                return false;
            });
            return false;
        },
		cancelRowBtnLive: function(){
			var viewSelf = this;
            $(document).on("click", "button[role='cancel_ecfi']", function(e){
            	var $this = $(this);
            	var id = $("#modal-formEcf").find("input[name='id']").val();
            	if(!id){
            		$(this).closest("tr").remove();
            		var objway = utils.str.substringAfterLast($this.closest("table").attr('id'),"_");
            		//重新刷新下合计
            		viewSelf.initIncomeSummation(objway);
            		viewSelf.consumeSummation(objway);
            	}else{
            		$this.closest("table").dataTable().fnDraw();
            	}
            });
		},
        submitNCultivatePass: function() {
            var viewSelf = this;
            if ($("#nPassCultivateForm_nd").valid()) {
                if (formFunc.validateUnit("#nPassCultivateForm_nd")) {
                    utils.button.ban("#submit-n-cultivate_pass");
                    $.ajax({
                        cache: false,
                        type: "POST",
                        url: $$ctx + "cultivateAndBreed/saveNongCultivate",
                        data: $('#nPassCultivateForm_nd').serialize(),
                        async: false,
                        error: function(request) {
                            utils.alert.err("错误" + request.status);
                        },
                        success: function(data) {
                            utils.button.reset("#submit-n-cultivate_pass");
                            if (data) {
                                if (data.success) {
                                    $("#n_culitivate_pass_table").dataTable().fnDraw();
                                    //$("#fnCultivateForm_nd").resetForm();
                                    $("#modal-formNCultivate_pass").modal("hide");
                                    $("#statisticsTable").dataTable().fnDraw();
                                } else {
                                    utils.alert.warn("<strong>" + data.msg + "</strong>");
                                }
                            }
                        }
                    }); //ajax end
                }
            }
        },
        submitNCultivateFuture: function() {
            var viewSelf = this;
            if ($("#nFutureCultivateForm_cultivate").valid()) {
                if (formFunc.validateUnit("#nFutureCultivateForm_cultivate")) {

                    utils.button.ban("#submit-n-cultivate_future");
                    $.ajax({
                        cache: false,
                        type: "POST",
                        url: $$ctx + "cultivateAndBreed/saveNongCultivate",
                        data: $('#nFutureCultivateForm_cultivate').serialize(),
                        async: false,
                        error: function(request) {
                            utils.alert.err("错误" + request.status);
                        },
                        success: function(data) {
                            utils.button.reset("#submit-n-cultivate_future");
                            if (data) {
                                if (data.success) {
                                	utils.alert.suc("保存成功！");
                                	$("#submit-n-cultivate_future").css("display","none");
                                	$("#modal-formNCultivate_future .wizard-actions .btn-next").attr("disabled",false);
                                	$("#income_form_cultivate input[name='objId']").val(data.data);
                                	//第一步保存成功自动进入第二步骤
                                	viewSelf.skipStep('#fuelux-wizard',2);
                                } else {
                                    utils.alert.warn("<strong>" + data.msg + "</strong>");
                                }
                            }
                        }
                    }); //ajax end
                }
            }
        },
        submitNBreedPass: function() {
            var viewSelf = this;
            if ($("#nPassBreedForm_nd").valid()) {
                if (formFunc.validateUnit("#nPassBreedForm_nd")) {
                    utils.button.ban("#submit-n-breed_pass");
                    $.ajax({
                        cache: false,
                        type: "POST",
                        url: $$ctx + "cultivateAndBreed/saveNongBreed",
                        data: $('#nPassBreedForm_nd').serialize(),
                        async: false,
                        error: function(request) {
                            utils.alert.err("错误" + request.status);
                        },
                        success: function(data) {
                            utils.button.reset("#submit-n-breed_pass");
                            if (data) {
                                if (data.success) {
                                    $("#n_breed_pass_table").dataTable().fnDraw();
                                    //$("#fnCultivateForm_nd").resetForm();
                                    $("#modal-formNBreed_pass").modal("hide");
                                    $("#statisticsTable").dataTable().fnDraw();
                                } else {
                                    utils.alert.warn("<strong>" + data.msg + "</strong>");
                                }
                            }
                        }
                    }); //ajax end
                }
            }
        },
        submitNBreedFuture: function() {
            var viewSelf = this;
            if ($("#nFutureCultivateForm_breed").valid()) {
                if (formFunc.validateUnit("#nFutureCultivateForm_breed")) {
                    utils.button.ban("#submit-n-breed_future");
                    $.ajax({
                        cache: false,
                        type: "POST",
                        url: $$ctx + "cultivateAndBreed/saveNongBreed",
                        data: $('#nFutureCultivateForm_breed').serialize(),
                        async: false,
                        error: function(request) {
                            utils.alert.err("错误" + request.status);
                        },
                        success: function(data) {
                            utils.button.reset("#submit-n-breed_future");
                            if (data) {
                            	if(data.success){
                            	utils.alert.suc("保存成功！");
                            	$("#submit-n-breed_future").css("display","none");
                            	$("#modal-formNBreed_future .wizard-actions .btn-next").attr("disabled",false);
                            	$("#income_form_breed input[name='objId']").val(data.data);
                            	//第一步保存成功自动进入第二步骤
                            	viewSelf.skipStep('#breed-fuelux-wizard',2);
            					
                            } else {
                                utils.alert.warn("<strong>" + data.msg + "</strong>");
                            }
                        	}
                        }
                    }); //ajax end
                }
            }
        },
        addIncomeCommonCul: function() {
        	$("#other_income_common_modal div.modal-header h3").html("<i class='ace-icon fa fa-plus'></i> 成本费用明细（种植业）");
            this.resetIncomeCommonFormAndShowModal("1");
        },
        addIncomeCommonBreed: function() {
        	$("#other_income_common_modal div.modal-header h3").html("<i class='ace-icon fa fa-plus'></i> 成本费用明细（养殖业）");
            this.resetIncomeCommonFormAndShowModal("2");
        },
        resetIncomeCommonFormAndShowModal: function(type) {
            var formSelector = "#other_income_common_form";
            var modalSelector = "#other_income_common_modal";
            $(formSelector).resetForm();
            $(formSelector).find(".form-group:eq(1)").hide();
            $(formSelector).find("input[name='id']").val("");
            $(formSelector).find("input[name='type']").val(type);
            $(modalSelector).find(".modal-footer").show();
            $(modalSelector).find("input, select, textarea").removeAttr("disabled");
            $(modalSelector).modal("show");
        },
        showCultivateModal: function() {
            var viewSelf = this;
//            formFunc.resetUnitField();
            var seq = $("#n_culitivate_tab").find("li.active").data("seq");
            if (seq == "pass") {
                $("#nPassCultivateForm_nd").resetForm();
                $("#nPassCultivateForm_nd").find("input[name='id']").val("");
                $("#nPassCultivateForm_nd").find("input[name='projectId']").val(getHidden("projectId"));
                $("#nPassCultivateForm_nd").find("input[name='type']").val("1");

                $("#modal-formNCultivate_pass div.modal-header h4").html("<i class='ace-icon fa fa-plus'></i> 种植业（过去12个月全年）");
                $("#modal-formNCultivate_pass").modal("show");
            } else if (seq == "future") {
            	//重置表单
                $("#nFutureCultivateForm_cultivate").resetForm();
                //取消禁用状态
                formFunc.removeDisabled("#nFutureCultivateForm_cultivate");
                //设置关联
                viewSelf.loadRelative("cultivate", null);
                //设置默认属性值
                $("#nFutureCultivateForm_cultivate").find("input[name='id']").val("");
                $("#nFutureCultivateForm_cultivate").find("input[name='projectId']").val(getHidden("projectId"));
                $("#nFutureCultivateForm_cultivate").find("input[name='type']").val("2");
                //设置标题
                $("#modal-formNCultivate_future div.modal-header h4").html("<i class='ace-icon fa fa-plus'></i> 种植业（未来12个月全年）");
                //清除收入支出金额合计
                viewSelf.clearSum();
                //显示关闭按钮
                $("#modal-formNCultivate_future div.modal-header button").remove();
                $("#modal-formNCultivate_future div.modal-header h4").before("<button data-dismiss='modal' class='close nfuture-close' type='button'>×</button>");
                //显示保存按钮
                $("#submit-n-cultivate_future").removeAttr('style');
                $('#fuelux-wizard').data('operate','add');
                //显示下一步的图标
                $(".btn-last-step").html("下一步 <i class='ace-icon fa fa-arrow-right icon-on-right'></i>");
                $(".btn-last-step").attr('disabled', 'disabled')
                //隐藏x关闭按钮
//                $("#modal-formNCultivate_future div.modal-header button").remove();
                //显示操作label
                $("#income_tb_cultivate thead tr th").last().removeAttr('style');
                $("#out_tb_cultivate thead tr th").last().removeAttr('style');
                //显示收入支出新增按钮
                $("#btn-addEcfIncome_nd,#btn-addEcfConsume_nd").removeAttr('style');
                //在第二步和第三步清除收入支出列表缓存
            	$("#income_tb_cultivate tbody tr,#out_tb_cultivate tbody tr").not(".hj").remove();
                
                $("#modal-formNCultivate_future").modal("show");
            } else {
                return false;
            }

        },
        clearSum: function(){//清除合计内容
        	var viewSelf = this;
        	$(".income_tb tbody .hj .income-sum").text(0);
            $(".out_tb tbody .hj .consume-sum").text(0);
        },
		initDataPickers: function() {//初始化日历
			$('.date-picker').datepicker({ format: "yyyy-mm", startView: 0, minViewMode: 1,todayHighlight:true, autoclose: true}).on("show",function(){
						$(".datepicker").css("z-index","99999")});;
		},
        addIncomeCultivate: function(){
        	formFunc.addRow("#income_tb_cultivate", 1);//种植收入
        },
        addConsumeCultivate: function(){
        	formFunc.addRow("#out_tb_cultivate", 2);//种植支出
        },
        addIncomeBreed: function(){
        	formFunc.addRow("#income_tb_breed", 1);//养殖收入
        },
        addConsumeBreed: function(){
        	formFunc.addRow("#out_tb_breed", 2);//养殖支出
        },
        initWiazrdCultivate: function(){//种植业
        	var viewSelf = this;
        	var $validation = true;
        	$('#modal-formNCultivate_future .modal-header').ace_wizard().on('change' , function(e, info){
        		var operate= $(e.currentTarget).data('operate');
        		//当点击第一步骤的下一步按钮时
        		if(info.step == 1 && info.direction == "next"){
        			$(".btn-last-step").html("下一步 <i class='ace-icon fa fa-arrow-right icon-on-right'></i>");
        		}
        		//当点击第二步骤的上一步按钮时
        		if(info.step == 2 && info.direction == "previous" && operate != "detail"){
					$("#submit-n-cultivate_future").removeAttr("style");
					$(".btn-last-step").html("<i class='ace-icon fa fa-arrow-right icon-on-right'></i>");
				}
        		//当点击第二步骤的上一步按钮时
        		if(info.step == 3 && info.direction == "previous"){
					$(".btn-last-step").html("<i class='ace-icon fa fa-arrow-right icon-on-right'></i>");
				}
        		//当点击第二步骤的下一步按钮时
        		if(info.step == 2 && info.direction == "next"){
        			$("#submit-n-cultivate_future").css("display","none");
        			$(".btn-last-step").html(" <i class='glyphicon glyphicon-remove'></i>");
        		}
        		//验证第一步的form表单
        		if(info.step == 1 && $validation) {
					if(!$('#nFutureCultivateForm_cultivate').valid()){
						$("#submit-n-cultivate_future").removeAttr('style');
						return false;
					}else{
						$("#submit-n-cultivate_future").css("display","none");
						return true;
					}
				}
			}).on('finished', function(e) {//最后一步
			  $("#n_culitivate_future_table").dataTable().fnDraw();
              $("#modal-formNCultivate_future").modal("hide");
              $("#statisticsTable").dataTable().fnDraw();
              $("#submit-n-cultivate_future").removeAttr("style");
              viewSelf.skipStep('#fuelux-wizard',1);
			});
        	
        	$('#modal-formNCultivate_future .wizard-actions .btn-next').attr('disabled',true);
        	$('#modal-formNCultivate_future .wizard-actions .btn[data-dismiss=modal]').removeAttr('disabled');
        },
        initWiazrdBreed: function(){//养殖业
        	var viewSelf = this;
        	var $validation = true;
        	$('#modal-formNBreed_future .modal-header').ace_wizard().on('change' , function(e, info){
        		var operate = $(e.currentTarget).data('operate');
        		//当点击第一步骤的下一步按钮时
        		if(info.step == 1 && info.direction == "next"){
        			$(".btn-last-step").html("下一步 <i class='ace-icon fa fa-arrow-right icon-on-right'></i>");
        		}
        		//当点击第二步骤的上一步按钮时
        		if(info.step == 2 && info.direction == "previous" && operate != 'detail'){
        			$("#submit-n-breed_future").removeAttr('style');
					$(".btn-last-step").html("<i class='ace-icon fa fa-arrow-right icon-on-right'></i>");
				}
        		//当点击第二步骤的上一步按钮时
        		if(info.step == 3 && info.direction == "previous"){
					$(".btn-last-step").html("<i class='ace-icon fa fa-arrow-right icon-on-right'></i>");
				}
        		//当点击第二步骤的下一步按钮时
        		if(info.step == 2 && info.direction == "next"){
        			$("#submit-n-breed_future").css("display","none");
        			$(".btn-last-step").html(" <i class='glyphicon glyphicon-remove'></i>");
        		}
        		//验证第一步的form表单
        		if(info.step == 1 && $validation) {
					if(!$('#nFutureCultivateForm_breed').valid()){
						$("#submit-n-breed_future").removeAttr('style');
						return false;
					}else{
						$("#submit-n-breed_future").css("display","none");
						return true;
					}
				}
			}).on('finished', function(e) {//最后一步
			  $("#n_breed_future_table").dataTable().fnDraw();
              $("#modal-formNBreed_future").modal("hide");
              $("#statisticsTable").dataTable().fnDraw();
              $("#submit-n-breed_future").removeAttr("style");
              viewSelf.skipStep('#breed-fuelux-wizard',1);
			});
        	
        	$('#modal-formNBreed_future .wizard-actions .btn-next').attr('disabled',true);
        	$('#modal-formNBreed_future .wizard-actions .btn[data-dismiss=modal]').removeAttr('disabled');
        },
        showBreedModal: function() {
            var viewSelf = this;
//            formFunc.resetUnitField();
            var seq = $("#n_breed_tab").find("li.active").data("seq");
           
            if (seq == "pass") {
                $("#nPassBreedForm_nd").resetForm();
                $("#nPassBreedForm_nd").find("input[name='id']").val("");
                $("#nPassBreedForm_nd").find("input[name='projectId']").val(getHidden("projectId"));
                $("#nPassBreedForm_nd").find("input[name='type']").val("1");

                $("#modal-formNBreed_pass div.modal-header h4").html("<i class='ace-icon fa fa-plus'></i>养殖业（过去12个月全年）");
                $("#modal-formNBreed_pass").modal("show");
            } else if (seq == "future") {
            	//重置表单
                $("#nFutureCultivateForm_breed").resetForm();
                //取消禁用状态
                formFunc.removeDisabled("#nFutureCultivateForm_breed");
                viewSelf.loadRelative("breed", null);
                $("#nFutureCultivateForm_breed").resetForm();
                $("#nFutureCultivateForm_breed").find("input[name='id']").val("");
                $("#nFutureCultivateForm_breed").find("input[name='projectId']").val(getHidden("projectId"));
                $("#nFutureCultivateForm_breed").find("input[name='type']").val("2");
              //设置标题
                $("#modal-formNBreed_future div.modal-header h4").html("<i class='ace-icon fa fa-plus'></i>养殖业（未来12个月全年）");
                //清除收入支出金额合计
                viewSelf.clearSum();
                //显示保存按钮
                $("#submit-n-breed_future").removeAttr('style');
                $("#breed-fuelux-wizard").data('operate','add');
                //显示下一步的图标
                $(".btn-last-step").html("下一步 <i class='ace-icon fa fa-arrow-right icon-on-right'></i>");
                $(".btn-last-step").attr('disabled', 'disabled')
                //隐藏x关闭按钮
                $("#modal-formNBreed_future div.modal-header button").remove();
                $("#modal-formNBreed_future div.modal-header h4").before("<button data-dismiss='modal' class='close nfuture-close' type='button'>×</button>");
                //显示操作label
                $("#income_tb_breed thead tr th").last().removeAttr('style');
                $("#out_tb_breed thead tr th").last().removeAttr('style');
                //显示收入支出新增按钮
                $("#btn-addEcfIncome-breed,#btn-addEcfConsume-breed").removeAttr('style');
                //在第二步和第三步清除收入支出列表缓存
            	$("#income_tb_breed tbody tr,#out_tb_breed tbody tr").not(".hj").remove();
                $("#modal-formNBreed_future").modal("show");
            } else {
                return false;
            }

        },
        loadRelative: function(type, id) {
            var viewSelf = this;
            if (type == "cultivate") {
                viewSelf.model.findRelativeCultivateList({
                    "projectId": getHidden("projectId"),
                    "id": id
                },
                function(r) {
                    var sel = $("#nFutureCultivateForm_cultivate").find("select[name='relativeId']");
                    $(sel).html("");
                    if (r && r.success) {
                        for (var key in r.data) {
                            var opt = "<option value='" + key + "'>" + r.data[key] + "</option>";
                            $(sel).append(opt);
                        }
                    }
                    $(sel).prepend("<option value=''>不关联</option>");
                    /** ---------------------------反显内容start--------------------------------*/
                    var name = $(sel).find("option:selected").text();
                    name = name.substring(name.indexOf("-")+1);
                    var $input = $("#nFutureCultivateForm_cultivate").find("input[name='cultivateContent']");
                    if($(sel).find("option:selected").val() ==''){
                    	$input.attr('readonly',false);
                    }else{
                    	$input.val(name);
                    	$input.attr('readonly',true);
                    }
                    $(sel).bind("change", function(e) {
                    	  var $text = $(this).find("option:selected").text();
                    	  $text = $text.substring($text.indexOf("-")+1);
                    	if ($(this).val()) {
                    		$input.val($text);
                    	} else {
                    		$input.val("");
                    		$input.attr('readonly',false);
                    	}
                    });
                    /** ---------------------------反显内容end--------------------------------*/
                });
            } else if (type == "breed") {
                viewSelf.model.findRelativeBreedList({
                    "projectId": getHidden("projectId"),
                    "id": id
                },
                function(r) {
                    var sel = $("#nFutureCultivateForm_breed").find("select[name='relativeId']");
                    $(sel).html("");

                    if (r && r.success) {
                        for (var key in r.data) {
                            var opt = "<option value='" + key + "'>" + r.data[key] + "</option>";
                            $(sel).append(opt);
                        }
                    }
                    $(sel).prepend("<option value=''>不关联</option>");
                    /** ---------------------------反显内容start--------------------------------*/
                    var name = $(sel).find("option:selected").text();
                    name = name.substring(name.indexOf("-")+1);
                    var $input = $("#nFutureCultivateForm_breed").find(":input[name='breedContent']");
                    if($(sel).find("option:selected").val() ==''){
//                    	if($input){
//                    		$input.attr('readonly',false);
//                    	}else{
//                    		$input.attr('readonly',true);
//                    	}
                    	$input.attr('readonly',false);
                    }else{
                    	$input.val(name);
                    	$input.attr('readonly',true);
                    }
                    $(sel).bind("change", function(e) {
                    	  var $text = $(this).find("option:selected").text();
                    	  $text = $text.substring($text.indexOf("-")+1);
                    	if ($(this).val()) {
                    		$input.val($text);
                    		$input.attr('readonly',true);
                    	} else {
                    		$input.val("");
                    		$input.attr('readonly',false);
                    	}
                    });
                    /** ---------------------------反显内容end--------------------------------*/
                })
            } else {
                return false;
            }
        },
        initCommonInfoForm: function() {
            $("#common_info_form").validate({
                rules: rm_common_info.rules,
                messages: rm_common_info.messages,
                submitHandler: function(form) {
                    $.ajax({
                        type: 'POST',
                        url: $$ctx + 'industryBiz/saveCommonInfo',
                        data: $(form).serialize(),
                        success: function(result) {
                            if (result.success) {
                                utils.alert.suc(result.msg);
                                $(form).find("input[name='id']").val(result.data);
                                $("#statisticsTable").dataTable().fnDraw();
                            } else {
                                utils.alert.err(result.msg);
                            }
                        }
                    });
                }
            });

            $.ajax({
                type: 'POST',
                url: $$ctx + 'industryBiz/findCommonInfoByProIdAndType',
                data: {
                    'projectId': $("#projectId").val(),
                    'type': "1"
                },
                success: function(result) {
                    if (typeof(result) != 'undefined' && $.trim(result) != '') {
                        result = result[0];
                        var formSelector = "#common_info_form";
                        $.each($(formSelector).find("input[type=text], input[type=hidden], select, textarea"),
                        function() {
                            $(this).val(result[$(this).attr("name")]);
                        });
                    }
                }
            });
    	},
        initCommonDebtInfoForm: function() {
            $("#common_debt_info_form").validate({
                submitHandler: function(form) {
                    $.ajax({
                        type: 'POST',
                        url: $$ctx + 'industryBiz/saveCommonInfo',
                        data: $(form).serialize(),
                        success: function(result) {
                            if (result.success) {
                                utils.alert.suc(result.msg);
                                $(form).find("input[name='id']").val(result.data);
                                $("#statisticsTable").dataTable().fnDraw();
                            } else {
                                utils.alert.err(result.msg);
                            }
                        }
                    });
                }
            });

            $.ajax({
                type: 'POST',
                url: $$ctx + 'industryBiz/findCommonInfoByProIdAndType',
                data: {
                    'projectId': $("#projectId").val(),
                    'type': "1"
                },
                success: function(result) {
                    if (typeof(result) != 'undefined' && $.trim(result) != '') {
                        result = result[0];
                        var formSelector = "#common_debt_info_form";
                        $.each($(formSelector).find("input[type=text], input[type=hidden], select, textarea"),
                        function() {
                            $(this).val(result[$(this).attr("name")]);
                        });
                    }
                }
            });
        },
        saveRowBtnLive: function(){
			var viewSelf = this;
            $(document).on("click", "button[role^='save_ecfi_']", function(e){
            	var $this = $(this);
            	var objWay = utils.str.substringAfterLast($this.attr('role'),"_");//种植业、养殖业等标识
            	var tr = $this.closest("tr");
            	var validate = function(){
            		 
            		var time =  $(tr).find("td input:eq(0)").val();
            		var money = $(tr).find("td input:eq(1)").val();
            		var remark = $(tr).find("td input:eq(2)").val();
            		
            		var reg =/^[0-9]+(\.[0-9]{1,2})?$/;//正整数或两位小数
            		if(time == ""||money == ""||remark == ""){
            			utils.alert.warn("年月、金额、内容均为必填项");
        				return false;
            		}else if(!reg.test(money)){
        				utils.alert.warn("金额请输入数字，允许两位小数");
        				return false;
        			}
        			return true;
            	}
            	
            	if(!validate()){
            		return false;
            	}
            	
            	utils.button.ban($this);
            	viewSelf.model.saveEcfiDetail({
            		"objId" : $("#income_form_"+objWay).find("input[name='objId']").val(),
            		"objContent" : $(tr).find("td input:eq(2)").val(),
            		"objType":$("#income_form_"+objWay).find("input[name='objType']").val(),
            		"objName":$("#nFutureCultivateForm_"+objWay).find("input[name='"+objWay+"Content']").val(),
            		"monthOfYearStr" : $(tr).find("td input:eq(0)").val(),
            		"amtMoney" : $(tr).find("td input:eq(1)").val(),
            		"incomeExpendFlag" : $this.data('type'),
            		"projectId":$("#projectId").val()
            	}, function(r){
            		utils.button.reset($this);
            		if(r&&r.success){
            			$this.addClass("btn-success");
            			utils.alert.suc(r.msg);
            			$this.parent().children("button:last-child").attr("role","delete_ecfi");
            			$this.attr("role","modify_ecfi_"+objWay)
            			$this.data("eid",r.data)
            			$this.parent().children("button:last-child").data("id",r.data);
            		}else{
            			$this.removeClass("btn-success");
            			utils.alert.warn(r.msg);
            		}
            	});
            });
		},
        modifyRowBtnLive: function(){
			var viewSelf = this;
            $(document).on("click", "button[role^='modify_ecfi']", function(e){
            	var $this = $(this);
            	var objWay = utils.str.substringAfterLast($this.attr('role'),"_");//种植业、养殖业等标识
            	var tr = $this.closest("tr");
            	var validate = function(){
            		var time =  $(tr).find("td input:eq(0)").val();
            		var money = $(tr).find("td input:eq(1)").val();
            		var remark = $(tr).find("td input:eq(2)").val();
            		
            		var reg =/^[0-9]+(\.[0-9]{1,2})?$/;//正整数或两位小数
            		if(time == ""||money == ""||remark == ""){
            			utils.alert.warn("年月、金额、内容均为必填项");
        				return false;
            		}else if(!reg.test(money)){
        				utils.alert.warn("金额请输入数字，允许两位小数");
        				return false;
        			}
        			return true;
            	}
            	
            	if(!validate()){
            		return false;
            	}
            	utils.button.ban($this);
            	viewSelf.model.modifyEcfiDetail({
            		"id" : $this.data("eid"),
            		"monthOfYearStr" : $(tr).find("td input:eq(0)").val(),
            		"amtMoney" : $(tr).find("td input:eq(1)").val(),
            		"objContent" : $(tr).find("td input:eq(2)").val(),
            		"incomeExpendFlag" : $this.data('type'),//区分是收入还是支出
            		"objType":$("#income_form_"+objWay).find("input[name='objType']").val(),
            		"objName":$("#nFutureCultivateForm_"+objWay).find("input[name='"+objWay+"Content']").val(),
            		"projectId":$("#projectId").val(),
            		"objId" : $("#income_form_"+objWay).find("input[name='objId']").val()
            	}, function(r){
            		utils.button.reset($this);
            		if(r&&r.success){
            			$this.addClass("btn-success");
            			utils.alert.suc(r.msg);
            		}else{
            			$this.removeClass("btn-success");
            			utils.alert.warn(r.msg);
            		}
            		
            	});
            });
		}

    });
    module.exports = view;
});