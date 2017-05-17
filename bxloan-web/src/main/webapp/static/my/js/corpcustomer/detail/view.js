define(function (require, exports, module)
{
    var model = require("./model");
    var utils = require("utils");
    var rm_basicInfo = require("./rm_basicInfo");
    var rm_addr = require("./rm_addr");
    var _alert = bootbox.alert;
    var controllerUrl = 'corpcustomer';
    var rm_person = require("./rm_person");
    var rm_corp = require("./rm_corp");
    var myClickTab = "";
      
    var view = Backbone.View.extend(
        {
            el : "body",
            events :
            {
            	"click #show-tree" : "toggleIndustryTree",
            	"click button[role='add_rela_person']" : "openAddActConPerson",
            	"click button[role='add_rela_corp']" : "openAddCorp",
            	"click #addr_add" : "openAddrAdd",
            	"click #btn_doc_search" : "searchDocOfClick",
            	"click #btn_doc_upload" : "addDocUpload",
            	"click #basic_commit" : "commitCorpBasicInfo",
            	"click #btn_doc_add" : "initUploadBtn"
            },
            initialize : function ()
            {
                /** 初始化 */
                this.model = new model();
                this.render();
            },
            render : function ()
            {
            	var viewType = $("#detail_view_type").val();
            	if(viewType=='showDetail'){
            		this.renderShow();
            	}else{
            		this.renderEdit();
            	}
            },
            
            renderEdit:function(){
            	this.initBasicInfoFormValidat();
            	this.initTree();
            	this.initDates();
            	this.initResidence();
            	this.initActconTable();
            	this.initHighManagerTable();
            	this.initStockHolderTable();
            	this.initAddPersonForm();
            	this.initAddRelaCorpForm();
            	
            	this.pdownloadBtnLive();
            	
            	this.initCustDocTable();
            	
            	this.initRemoveRela();
            	this.initDetailRealBtn();
            	this.initEditBtn();
            	
            	//2014-09-22
            	this.initNationAreaCascade();
            	this.initAddrFormValid();
            	this.initAddrTable();
            	this.initAddrBtnLive();
            	this.initDocsTable();
            	this.initDocBtnLive();
            	this.initDataTablesForPcw();
            	this.detailBtnLivePcw();
            	this.clickfunction();
            	
            	this.initConfirmPartyChoose();
            	this.initChooseExistingPartyModal();
            },
            renderShow : function(){
            	this.disableAllInputs();
            	$("#tb_doc_selector").hide();
            	$("#show-tree").attr("disabled","disabled");
            	$("#basic_commit").hide();
            	
            	this.initTree();
            	
            	this.initActconTable();
            	this.initHighManagerTable();
            	this.initStockHolderTable();
            	this.initAddrTable();
            	this.initDocsTable();
            	
            	this.pdownloadBtnLive();
            	 
            	this.initAddPersonForm();
            	this.initAddRelaCorpForm();
            	this.initDetailRealBtn();
            	this.initDocBtnLive();
            	
            	this.hideActionBtns();
            	this.initDataTablesForPcw();
            	this.detailBtnLivePcw();
            	this.clickfunction();
            	this.initNationAreaCascade();
            	this.initAddrBtnLive();
            },
            
            
            disableAllInputs : function(){
            	this.disableForm("#form_basicInfo");
            },
            hideActionBtns : function (){
            	$("#addr_add").hide();
            	$("#btn_doc_add").hide();
            	$("button[role='add_rela_person']").hide();
            	$("button[role='add_rela_corp']").hide();
            	$("<style type='text/css'>"+
				"#tbl_addrList .btn-danger,#tbl_addrList .btn-info{"+
				"	display: none;"+
				"}"+
				"#tbl_actcontroller .btn-danger,#tbl_actcontroller .btn-info{"+
				"	display: none;"+
				"}"+
				"#tbl_stockholder .btn-danger,#tbl_stockholder .btn-info{"+
				"	display: none;"+
				"}"+
				"#tbl_highmanager .btn-danger,#tbl_highmanager .btn-info{"+
				"	display: none;"+
				"}"+
				"#tbWd .btn-danger{"+
				"	display: none;"+
				"}"+
				"</style>").appendTo($("head"));
            },
            
            initConfirmPartyChoose : function(){
            	var viewSelf = this;
            	$(document).on("click","#existing_party_btn_confirm",function(){
            		var partyType = $(this).attr("partyType");
            		var datas = $("#existing_party_form").serializeArray();
            		if(partyType && datas){
            			var partyId = null;
            			
            			for(var index in datas){
            				var nvpair = datas[index];
            				if(nvpair.name&&nvpair.name=='partyId'){
            					partyId = nvpair.value;
            				}
            			}
            			
            			if(!partyId){
            				_alert("请先选择一位客户");
            				return;
            			}
            			
            			var targetUrl = "";
            			var targetFormSelector = "";
            			if(partyType=='2'){//自然人existingPersonDetail
            				targetUrl = $$ctx + "corpcustomer/existingPersonDetail";
            				targetFormSelector = "#add_person_form"; 
            			}else{//法人existingCorpDetail
            				targetUrl = $$ctx + "corpcustomer/existingCorpDetail";
            				targetFormSelector = "#add_corp_form"; 
            			}
            			
            			$.ajax({
	                        cache: false,
	                        type: "POST",
	                        url: targetUrl,
	                        data: {partyId:partyId},
	                        async: false,
	                        error: function(request) {
	                        	utils.alert.err("<b>获取信息时发生错误</b>");
	                        },
	                        success: function(result) {
	                        	if(result.success){
	                        		utils.forms.putValueToForm(result.data,targetFormSelector);
	                        		$("#existing_party_modal").modal("hide");
	                        	}else{
	                        		utils.alert.err("<b>" + result.msg + "</b>");
	                            }
	                        }
	                    });
            		}
            	});
            },
            initChooseExistingPartyModal : function(){
            	
            	var viewSelf = this;
            	
            	$(document).on("click","#exist_party_tbl tbody tr",function(){
            		$("#exist_party_tbl tbody tr").removeClass("info");
            		$(this).addClass("info");
            		$(this).find("input[type='radio']")[0].click();
            	});
            	
            	$(document).on("change","#existing_party_certificatetype",function(){
            		$("#existing_party_certificatetype_value")
            		.val($("#existing_party_certificatetype").val());
            	});
            	
            	$(document).on("click","button[id='existing_party_btn_search']",function(){
            		viewSelf.existPartyTbl.refreshParamCache();
            		viewSelf.existPartyTbl.fnPageChange(0);
            	});
            	
            	$(document).on("click","button[role='showExistingParty']",function(){
            		
            		var partyType = $(this).attr("partyType");
            		$("#existing_party_partyName").val("");   
            		$("#existing_party_licence").val("");
            		
            		var tableSelector = "#exist_party_tbl";
            		if($(tableSelector).hasClass("dataTable")){
            			$(tableSelector).dataTable().fnDestroy();
            			$(tableSelector).attr("style","");
            		}
            		if(partyType=='1'){//法人
            			$("#existing_party_certificatetype_value").val("");
            			$("#existing_party_certificatetype_warp").hide();
            		}else{
            			$("#existing_party_certificatetype_warp").show();
            		}
    				
    				var existPartyTbl = $(tableSelector).dataTable({
        				bServerSide: true,
        				bFilter: false,
        				bLengthChange: false,
        		    	sAjaxSource: $$ctx + controllerUrl + "/findExistingParty",
        		    	fnServerParams: function (aoData) {
        		    		if(!this.serverParamCache){
                        		this.refreshParamCache = function(){
                                	this.serverParamCache = [{
										"name" : "partyTypeCd",
										"value" : partyType
									},
									{
										"name" : "certificatetypeCd",
										"value" : $("#existing_party_certificatetype_value").val()
									},
									{
										"name" : "partyName",
										"value" : $("#existing_party_partyName").val()
									},
									{
										"name" : "licence",
										"value" : $("#existing_party_licence").val()
									} ];
                                };
                                
                                this.refreshParamCache();
                        	}
                        	$(this.serverParamCache).each(function(i,d){
                        		aoData.push(d);
                        	});
        		    	},
        		    	aoColumns: [
        		    	            /****
        		    	            <th>&nbsp;</th>
									<th>客户编号</th>
									<th>客户名称</th>
									<th>证件类型</th>
									<th>证件号码</th>
									<th>状态</th>
									<th>客户经理</th>
        		    	             */
        		    	            
        		    	    {mData: "existingPartyId",mRender : function(data, type, rowdata){
        		    	    	var radio = 
        		    	    	"<div class='radio'>" +
								"	<label>" +
								"		<input name='partyId' type='radio'" +
								"		 value='" + rowdata.existingPartyId + "' class='ace' />" +
								"		<span class='lbl'>&nbsp;</span>"+	
								"	</label>" +
								"</div>";
        		    	    	return radio;
        		    	    }},
        		    	    {mData: "customerNum"},//客户编号
        		    	    {mData: "partyName"},//客户名称
        		    	    {mData: "certificateTypeStr"},//证件类型
        		    	    {mData: "certificateNum"},//证件号码
        		    	    {mData: "statusStr"},//状态
        		    	    {mData: "cusManaName"}//客户经理
        		    	]
        			});
        			viewSelf.existPartyTbl = existPartyTbl;
        			
        			$("#existing_party_btn_confirm").attr("partyType",partyType);
            		
            		$("#existing_party_modal").modal("show");
            	});
            	
            },
    		initBasicInfoFormValidat : function(){
    			
    			$("#form_basicInfo").validate({
	                rules: rm_basicInfo.rules,
	                messages: rm_basicInfo.messages,
	                submitHandler: function(form) {
	                	var submitType = $("#form_basicInfo_submitType").val();
	        			var commitUrl = submitType=='enable'?"/enableCorpCus":"/saveCorpCus";
	                    $.ajax({
	                        cache: false,
	                        type: "POST",
	                        url: $$ctx + controllerUrl + commitUrl,
	                        data: $('#form_basicInfo').serialize(),
	                        async: false,
	                        error: function(request) {
	                        	utils.alert.warn("<b>保存时发生错误</b>");
	                        },
	                        success: function(result) {
	                        	//请返回注册资本 或者从页面提取set到隐藏域中
	                        	if(result.success){
	                        		if(submitType == 'enable'){
	                        			utils.alert.suc("<b>此客戶已生效！</b>",function(){
	                        				var consultLocation = $('#consultLocation').val();
	                        				if(consultLocation == 'businessAdd') {
	                        					$('#consultLocation').val('close');
	                        				} else {
	                        					window.location.href = $$ctx + "singleCustomer";
	                        				}
	                        			});
		                        	}else {
			                        	$("#datail_corp_registeredCapital").val($("#basic_registeredCapital").val());
			                        	utils.alert.suc("<b>保存成功</b>");
		                        	}
	                            }else{
	                            	utils.alert.err("<b>" + result.msg + "</b>");
	                            }
	                        }
	                    });
	                    
	                    $("#form_basicInfo_submitType").val("");
	                    return false;
	                }
	            });
    			$(document).on("change","#basic_standedDate",function(){
    				$("#form_basicInfo").validate().element("#basic_standedDate");
    			});
    		},
    		commitCorpBasicInfo : function(){//提交基础信息
    			$("#form_basicInfo_submitType").val('enable');
            	var formSelector = '#form_basicInfo';
            	$(formSelector).submit();
            },
    		initTree: function() {
                /**初始化树*/
                var viewSelf = this;
                $.fn.zTree.init($("#industryTree"), {
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
                                $("#industryLevelTwoCd").val("");
                                $("#industryCdMask").val("");
                                $("#industryCd").val("");
                                return false;
                            } else {
                                $("#industryLevelTwoCd").val(treeNode.industryTypeCd);
                                $("#industryCdMask").val(treeNode.industryTypeName);
                                $("#industryCd").val(treeNode.industryTypeCd);
                                var treeObj = $.fn.zTree.getZTreeObj(treeId);
                                var node = treeObj.getNodeByParam("industryTypeCd", treeNode.industryTypeCd, null);
                                treeObj.checkNode(node, true, true);
                                var parentNode = node.getParentNode();
                                treeObj.expandNode(parentNode, true, false);
                                $("#industryTreeWarp").toggle(300);
                            }
                            
                        },
                        beforeCheck: function(treeId, treeNode) {
                        	 $("#industryLevelTwoCd").val(treeNode.industryTypeCd);
                        	 $("#industryCdMask").val(treeNode.industryTypeName);
                             $("#industryCd").val(treeNode.industryTypeCd);
                            return ! treeNode.isParent;
                        },
                        onAsyncSuccess: function(event, treeId, treeNode, msg) {
                            var treeObj = $.fn.zTree.getZTreeObj(treeId);
                            var industryTypeCd = $("#industryLevelTwoCd").val();
                            if (industryTypeCd !== "") {
                                var node = treeObj.getNodeByParam("industryTypeCd", industryTypeCd, null);
                                treeObj.checkNode(node, true, true);

                                var parentNode = node.getParentNode();
                                treeObj.expandNode(parentNode, true, false);

                                $("#industryCdMask").val(node.industryTypeName);
                                $("#industryCd").val(node.industryTypeCd);
                            }
                        }
                    }
                });
            },
    		toggleIndustryTree: function() {
                /**显示隐藏行业的树*/
                $("#industryTreeWarp").toggle(300);
            },
            openAddActConPerson : function(){
            	this.resetPersonForm();
            	if ("#faq-tab-3" == myClickTab) {
            		//实际控制人
            		$("#personActController")[0].click();
            	} else if ("#faq-tab-4" == myClickTab) {
            		//股东
            		$("#add_person_stock_check")[0].click();
            	} else if ("#faq-tab-5" == myClickTab) {
            		//高管
            		$("#add_person_high_mana_check")[0].click();
            	}
            	$("button[role='showExistingParty']").removeAttr("disabled");
            	$("#add_person_modal").modal("show");
            },
            openAddCorp:function(){
            	this.resetCorpForm();
            	if ("#faq-tab-3" == myClickTab) {
            		//实际控制人
            		$("#corpPersonActController")[0].click();
            	} else if ("#faq-tab-4" == myClickTab) {
            		//股东
            		$("#add_corp_stock_check")[0].click();
            	}
            	$("button[role='showExistingParty']").removeAttr("disabled");
            	$("#add_corp_modal").modal("show");
            },
            initResidence : function(){//初始化住宅类型
            	var targetSelector = ".add_person_residence";
            	$(targetSelector).hide();
            	$("#add_person_inhabitancyStatus").change(function(){
            		if($(this).val()=='1'){
            			$(targetSelector).slideDown();
            		}else{
            			$(targetSelector).slideUp();
            		}
            	});
            },
            calStockProportion :function(invesmentAmt,target){
            	var totalAmt = $("#datail_corp_registeredCapital").val();
            	if(totalAmt === null 
        			|| totalAmt === '' 
            		|| totalAmt == 'undefined' 
        			|| totalAmt === 0) {
            		
            		bootbox.alert("请先暂存企业客户基本信息");
            		$("#add_person_investmentAmt").val("");
            		return ;
            	}
            	if(!isNaN(invesmentAmt)){
            		$(target).val(((invesmentAmt/totalAmt)*100).toFixed(2));
            	}else{
            		_alert("<b>请先输入注册资本</b>");
            	}
            },
           
            initActconTable :function(){
            	var viewSelf = this;
    			var actconTable = $("#tbl_actcontroller").dataTable({
    				bServerSide: true,
    				bLengthChange: false,
    				bFilter: false,
    		    	sAjaxSource: $$ctx + controllerUrl + "/actconList",
    		    	fnServerParams: function (aoData) {
  				      aoData.push({"name": "partyId", "value": $("#detail_party_id").val()});
    		    	},
    		    	aoColumns: [
    		    	            /****
    		    	            <th>姓名</th>
								<th>证件类型</th>
								<th>证件号码</th>
								<th>家庭电话</th>
								<th>联系电话</th>
								<th>地址</th>
								<th>操作</th>
    		    	             */
    		    	    {mData: "name"},//姓名
    		    	    {mData: "certificateTypeCd"},//证件类型
    		    	    {mData: "certificateCd"},//证件号码
    		    	    {mData: "familyTelNum"},//家庭电话
    		    	    {mData: "contactTelNum"},//联系电话
    		    	    {mData: "familyAddress"},//地址
    		    	    {mData: "familyAddress",mRender: function(data, type, rowdata) {
    		    	    	var operation = 
    							"<div class='btn-group' style='width:80px;'>"+
    							
    							"<button data-relatype='" + rowdata.relaType + "' " +
    							"data-relaid='" + rowdata.id + "' " + 
    							"data-partyid='" + rowdata.partyId + "' " + 
    							"class='btn btn-xs btn-yellow' role='detailRela' " +
    							"data-toggle='tooltip' data-placement='bottom' title='查看详细'>" +
    							"<i class='ace-icon fa fa-eye'></i></button>" +
    							
    							"<button data-relatype='" + rowdata.relaType + "' " +
    							"data-relaid='" + rowdata.id + "' " + 
    							"data-partyid='" + rowdata.partyId + "' " + 
    							"class='btn btn-xs btn-danger' role='removeRela' " +
    							"data-toggle='tooltip' data-placement='bottom' title='删除'>" +
    							"<i class='ace-icon fa fa-trash-o'></i></button>" +
    							
    							"<button data-relatype='" + rowdata.relaType + "' " +
    							"data-relaid='" + rowdata.id + "' " + 
    							"data-partyid='" + rowdata.partyId + "' " + 
    							"class='btn btn-xs btn-info' role='editRela' " +
    							"data-toggle='tooltip' data-placement='bottom' title='编辑'>" +
    							"<i class='ace-icon fa fa-edit'></i></button>" +
    							
    						"</div>";
    		    	    	return operation;
    		    		}}
    		    	]
    			});
    			viewSelf.actconTable = actconTable;
            },
            initStockHolderTable :function(){
            	var viewSelf = this;
            	var stockHolderTable = $("#tbl_stockholder").dataTable({
            		bServerSide: true,
            		bFilter: false,
            		bLengthChange: false,
            		sAjaxSource: $$ctx + controllerUrl + "/stockHolderList",
            		fnServerParams: function (aoData) {
            			aoData.push({"name": "partyId", "value": $("#detail_party_id").val()});
            		},
            		aoColumns: [
    		            /****
	    	            <th>姓名</th>
						<th>证件类型</th>
						<th>证件号码</th>
						<th>出资方式</th>
						<th>持股比例(%)</th>
						<th>联系电话</th>
						<th>操作</th>
    		             */
    		            {mData: "name"},//姓名
    		            {mData: "certificateTypeCd"},//证件类型
    		            {mData: "certificateCd"},//证件号码
    		            {mData: "investmentType"},//出资方式
    		            {mData: "stockProportion"},//持股比例(%)
    		            {mData: "contactTelNum"},//联系电话
    		            {mData: "id",mRender: function(data, type, rowdata) {
    		            	
    		            	var operation = 
    							"<div class='btn-group' style='width:80px;'>"+
    							
    							"<button data-relatype='" + rowdata.relaType + "' " +
    							"data-relaid='" + rowdata.id + "' " + 
    							"data-partyid='" + rowdata.partyId + "' " + 
    							"class='btn btn-xs btn-yellow' role='detailRela' " +
    							"data-toggle='tooltip' data-placement='bottom' title='查看详细'>" +
    							"<i class='ace-icon fa fa-eye'></i></button>" +
    							
    							"<button data-relatype='" + rowdata.relaType + "' " +
    							"data-relaid='" + rowdata.id + "' " + 
    							"data-partyid='" + rowdata.partyId + "' " + 
    							"class='btn btn-xs btn-danger' role='removeRela' " +
    							"data-toggle='tooltip' data-placement='bottom' title='删除'>" +
    							"<i class='ace-icon fa fa-trash-o'></i></button>" +
    							
    							"<button data-relatype='" + rowdata.relaType + "' " +
    							"data-relaid='" + rowdata.id + "' " + 
    							"data-partyid='" + rowdata.partyId + "' " + 
    							"class='btn btn-xs btn-info' role='editRela' " +
    							"data-toggle='tooltip' data-placement='bottom' title='编辑'>" +
    							"<i class='ace-icon fa fa-edit'></i></button>" +
    							
    						"</div>";
    		            	return operation;
    		            }}
            		]
            	});
            	viewSelf.stockHolderTable = stockHolderTable;
            },
            initHighManagerTable :function(){
            	var viewSelf = this;
            	var highManagerTable = $("#tbl_highmanager").dataTable({
            		bServerSide: true,
            		bFilter: false,
            		bLengthChange: false,
            		sAjaxSource: $$ctx +  controllerUrl + "/highManagerList",
            		fnServerParams: function (aoData) {
            			aoData.push({"name": "partyId", "value": $("#detail_party_id").val()});
            		},
            		aoColumns: [
    		            /****
	    	            <th>姓名</th>
						<th>职务</th>
						<th>是否法人</th>
						<th>证件类型</th>
						<th>证件号码</th>
						<th>家庭电话</th>
						<th>联系电话</th>
						<th>操作</th>
    		             */
    		            {mData: "name"},//姓名
    		            {mData: "positionCd"},//职务
    		            {mData: "personIsLegalRepresent"},//是否法人
    		            {mData: "certificateTypeCd"},//证件类型
    		            {mData: "certificateCd"},//证件号码
    		            {mData: "familyTelNum"},//家庭电话
    		            {mData: "contactTelNum"},//联系电话
    		            {mData: "id",mRender: function(data, type, rowdata) {
    		            	
    		            	var operation = 
    							"<div class='btn-group' style='width:80px;'>"+
    							
    							"<button data-relatype='p' " +
    							"data-relaid='" + rowdata.id + "' " + 
    							"data-partyid='" + rowdata.partyId + "' " + 
    							"class='btn btn-xs btn-yellow' role='detailRela' " +
    							"data-toggle='tooltip' data-placement='bottom' title='查看详细'>" +
    							"<i class='ace-icon fa fa-eye'></i></button>" +
    							
    							"<button data-relatype='p' " +
    							"data-relaid='" + rowdata.id + "' " + 
    							"data-partyid='" + rowdata.partyId + "' " + 
    							"class='btn btn-xs btn-danger' role='removeRela' " +
    							"data-toggle='tooltip' data-placement='bottom' title='删除'>" +
    							"<i class='ace-icon fa fa-trash-o'></i></button>" +
    							
    							"<button data-relatype='p' " +
    							"data-relaid='" + rowdata.id + "' " + 
    							"data-partyid='" + rowdata.partyId + "' " + 
    							"class='btn btn-xs btn-info' role='editRela' " +
    							"data-toggle='tooltip' data-placement='bottom' title='编辑'>" +
    							"<i class='ace-icon fa fa-edit'></i></button>" +
    							
    						"</div>";
    		            	return operation;
    		            }}
            		]
            	});
            	viewSelf.highManagerTable = highManagerTable;
            },
            initDates : function(){
            	var dateOnTop = function(){$(".datepicker").css("z-index","99999");};
    			var defaultDateConf = {
    				autoclose : true,
    				todayHighlight : true,
    				clearBtn : true,
    				endDate : 'd'
    			};
    			
    			var initDateP = function(selector,config,changeCallBack){
    				var _dateConf = config || defaultDateConf;
    				$(selector).datepicker(_dateConf)
    				.on("changeDate",changeCallBack)
    				.on("show",dateOnTop);
    			};
    			initDateP("#basic_standedDate");
    			initDateP("#add_person_birthDate");
    			initDateP("#add_corp_corpEstablishDate");
            },
            refreshTables : function(){
            	var _view = this;
            	_view.actconTable.fnDraw();
        		_view.stockHolderTable.fnDraw();
        		_view.highManagerTable.fnDraw();
            },
            initRemoveRela:function(){
            	
            	var _view = this;
    			$(document).on("click","button[role='removeRela']",function(e){
    				var _btn = $(this);
    				bootbox.confirm("确定删除吗?",function(_confirm){
    					if(_confirm){
    						_view.model.removeRela({
    	    					partyId:_btn.data("partyid"),
    	    					relaId :_btn.data("relaid"),
    	    					relaType : _btn.data("relatype")
    	    				},function(result){
    	    					if(result.success){
    	    						bootbox.alert("删除成功!",function(){
    	    							_view.refreshTables();
    	    						});
    	    					}else{
    	    						bootbox.alert("删除失败.请稍后再试");
    	    					}
    	    				});
    					}
    				});
    				
    			});
            },
            resetPersonForm :function(){
            	this.enableForm("#add_person_form");
            	$(".add_person_residence").hide();
            	$(".add_person_invesment").hide();
            	$("#add_person_investmentCurrencyType_text").attr("readonly","readonly");
            	var _checkBoxes = $(".add_person_invesment").find("input[name='investmentTypeCd']");
				_checkBoxes.attr("disabled","disabled");
            	$("#add_person_legal_rep_check").attr("disabled","disabled");
        		$("#add_person_investmentAmt").attr("disabled","disabled");
        		$("#add_person_stockProportion").attr("disabled","disabled");
        		$("#add_person_person_id").val("");
        		$("#err_msg_field__add_person").html("");
        		$("#personinfo_li_1")[0].click();
            },
            resetCorpForm :function(){
            	this.enableForm("#add_corp_form");
            	$(".add_corp_investment").hide();
            	$("#add_corp_currencyCd_text").attr("readonly","readonly");
            	var _checkBoxes = $(".add_corp_investment").find("input[name='investmentTypeCd']");
        		var stockPro = $("#add_corp_stockProportion");
        		var invAmt = $("#add_corp_investmentAmt");
        		_checkBoxes.attr("disabled","disabled");
    			stockPro.attr("disabled","disabled");
    			invAmt.attr("disabled","disabled");
    			$("#add_corp_corp_id").val("");
            },
            enableForm :function(formSelector){
            	$(formSelector).validate().resetForm();
            	$(formSelector).find("input[type='text']").removeAttr("readonly");
            	$(formSelector).find("input[type='checkbox']").removeAttr("disabled");
            	$(formSelector).find("input[type='checkbox']").removeAttr("checked");
            	$(formSelector).find("select").removeAttr("disabled");
            	$(formSelector).find("button[type='submit']").show();
            },
            disableForm : function(formSelector){
        		$(formSelector).find("input[type='text']").attr("readonly","readonly");
            	$(formSelector).find("input[type='checkbox']").attr("disabled","disabled");
            	$(formSelector).find("select").attr("disabled","disabled");
            	$(formSelector).find("button[type='submit']").hide();
        	},
        	putValueToForm : function(dataToPut,formSelector){//将json数据填充到表单
        		utils.forms.putValueToForm(dataToPut,formSelector);
            },
            initAddPersonForm : function(){
            	var _view = this;
            	
            	$("#add_person_investmentAmt").on("blur",function(){
            		_view.calStockProportion($(this).val(),"#add_person_stockProportion");
            	});
            	$(document).on("change","#add_person_stock_check",function(e){
            		var targerSelector = '.add_person_invesment';
            		var _checkBoxes = $(targerSelector).find("input[name='investmentTypeCd']");
            		var invAmt = $("#add_person_investmentAmt");
            		var stockPro = $("#add_person_stockProportion");
            		
            		if($(this)[0].checked){
            			invAmt.removeAttr("disabled");
            			stockPro.removeAttr("disabled");
            			_checkBoxes.removeAttr("disabled");
            			$(targerSelector).slideDown();
            		}else{
            			invAmt.val("");
            			stockPro.val("");
            			invAmt.attr("disabled","disabled");
            			stockPro.attr("disabled","disabled");
            			_checkBoxes.removeAttr("checked");
            			_checkBoxes.attr("disabled","disabled");
            			$(targerSelector).slideUp();
            		}
            	});
            	
            	
            	$("#add_person_high_mana_check").change(function(){
            		var targetSelector = "#add_person_legal_rep_check";
            		if( $(this)[0].checked){
            			$(targetSelector).removeAttr("disabled");
            		}else{
            			$(targetSelector).removeAttr("checked");
            			$(targetSelector).attr("disabled","disabled");
            		}
            	});
            	
            	$("#add_person_creditRecord").change(function(){
            		var target = $("#add_person_creditRecordOverdue");
            		var targetWarp = $("#add_person_creditRecordOverdue_warp");
            		if($(this).val()=='1'){
            			target.removeAttr("disabled");
            			targetWarp.removeClass("hide");
            			targetWarp.fadeIn(200);
            		}else{
            			target.attr("disabled","disabled");
            			targetWarp.fadeOut(200);
            		}
            	});
            	
            	var formSelector = '#add_person_form';
            	
            	$(formSelector).validate({
	                rules: rm_person.rules,
	                messages: rm_person.messages,
	                errorPlacement: function(error, element) {
	                    if (element.is(":radio")){error.appendTo(element.parent().next().next());}
	                    else if (element.is(":checkbox")){error.appendTo(element.parent().parent().parent().next());}
	                    else if (element.is("input[name='name']")) {
	                        error.appendTo(element.parent().parent().parent());
	                    }
	                    else if (element.next().is("span[class='input-group-addon']")) {
	                    	error.appendTo(element.parent().parent().parent());
	                    }
	                    else {
	                    	error.appendTo(element.parent());
	                    } 
	                },
	                highlight: function( element, errorClass, validClass ) {
	        			if ( element.type === "radio" ) {
	        				this.findByName( element.name ).addClass( errorClass ).removeClass( validClass );
	        			} else {
	        				$( element ).addClass( errorClass ).removeClass( validClass );
	        			}
	        			$("#err_msg_field__add_person").html("信息填写有误,请检查.");
	        		},
	        		unhighlight: function( element, errorClass, validClass ) {
	        			if ( element.type === "radio" ) {
	        				this.findByName( element.name ).removeClass( errorClass ).addClass( validClass );
	        			} else {
	        				$( element ).removeClass( errorClass ).addClass( validClass );
	        			}
	        			if(this.errorList&&this.errorList.length>0){
	        				/////errorList是validator存储错误的容器(数组)
	        			}else{
	        				$("#err_msg_field__add_person").html("");
	        			}
	        		},
	                submitHandler: function(form) {
	                    $.ajax({
	                        cache: false,
	                        type: "POST",
	                        url: $$ctx + controllerUrl + "/savePerson",
	                        data: $(formSelector).serialize(),
	                        async: false,
	                        error: function(request) {
	                        	utils.alert.err("<b>保存时发生错误" + request + "</b>");
	                        },
	                        success: function(result) {
	                        	if(result.success){
                            		$("#add_person_modal").modal('hide');
                            		$("#add_person_person_id").val(result.data);
                            		_view.refreshTables();
	                            }else{
	                            	utils.alert.err("<b>" + result.msg + "</b>");
	                            }
	                        }
	                    });
	                    return false;
	                }
	            });
            },
            initAddRelaCorpForm : function(){
            	var _view = this;
            	
            	$("#add_corp_investmentAmt").on("blur",function(){
            		_view.calStockProportion($(this).val(),"#add_corp_stockProportion");
            	});
            	$("#add_corp_stock_check").change(function(){
            		var targerSelector = '.add_corp_investment';
            		var _checkBoxes = $(targerSelector).find("input[name='investmentTypeCd']");
            		var stockPro = $("#add_corp_stockProportion");
            		var invAmt = $("#add_corp_investmentAmt");
            		
            		if($(this)[0].checked){
            			_checkBoxes.removeAttr("disabled");
            			stockPro.removeAttr("disabled");
            			invAmt.removeAttr("disabled");
            			$(targerSelector).slideDown();
            		}else{
            			invAmt.val("");
            			invAmt.attr("disabled","disabled");
            			stockPro.val("");
            			stockPro.attr("disabled","disabled");
            			_checkBoxes.removeAttr("checked");
            			_checkBoxes.attr("disabled","disabled");
            			$(targerSelector).slideUp();
            		}
            	});
            	
            	var formSelector = '#add_corp_form';
            	
            	$(formSelector).validate({
	                rules: rm_corp.rules,
	                messages: rm_corp.messages,
	                submitHandler: function(form) {
	                	
	                    $.ajax({
	                        cache: false,
	                        type: "POST",
	                        url: $$ctx + controllerUrl + "/saveRelaCorp",
	                        data: $(formSelector).serialize(),
	                        async: false,
	                        error: function(request) {
	                        	utils.alert.err("<b>add_corp_form报错" + request + "</b>");
	                        },
	                        success: function(result) {
	                        	if(result.success){
                            		$("#add_corp_corp_id").val(result.data);
                            		_view.refreshTables();
                            		$("#add_corp_modal").modal("hide");
	                            }else{
	                            	utils.alert.err("<b>" + result.msg + "</b>");
	                            }
	                        }
	                    });
	                    return false;
	                },
	                errorPlacement: function(error, element) {
	                    if (element.is(":radio")){error.appendTo(element.parent().next().next());}
	                    else if (element.is(":checkbox")){error.appendTo(element.parent().parent().parent().next());}
	                    else if (element.is("input[name='corpName']")) {
	                        error.appendTo(element.parent().parent().parent());
	                    }
	                    else if (element.next().is("span[class='input-group-addon']")) {
	                    	error.appendTo(element.parent().parent().parent());
	                    }
	                    else {
	                    	error.appendTo(element.parent());
	                    } 
	                },
	            });
            },
            initDetailRealBtn : function(){
            	var _view = this;
            	var disableForm = this.disableForm;
            	$(document).on("click","button[role='detailRela']",function(e){
            		$("button[role='showExistingParty']").attr("disabled","disabled");
            		var _btn = $(this);
            		if(_btn.data("relatype")=='p'){
            			_view.model.detailRelaPerson(
        					{
        						personId : _btn.data("relaid")
        					}
        					,function(result){
        						if(result.success){
        							var _data = result.data;
	    							_view.resetPersonForm();
	    							_view.putValueToForm(_data,"#add_person_form");
	    							$("#add_person_birthDate").val(_data.birthDateStr);
	    							
	    							if($("#add_person_stock_check")[0].checked){
	    								$(".add_person_invesment").show();
	    							}
	    							
	    							
	    							if($("#add_person_creditRecord")[0].checked){
	    								$("#add_person_creditRecordOverdue_warp").show();
	    							}
	    							
	    							if($("#add_person_inhabitancyStatus").val()=='1'){
	    								$(".add_person_residence").show();
	    							}
        							disableForm("#add_person_form");
        			            	$("#add_person_modal").modal("show");
        						}else{
        							utils.alert.err("<b>查看详细时发生错误</b>");
        						}
        						
        					}
    					);
            		}else if(_btn.data("relatype")=='c'){
            			_view.model.detailRelaCorp(
        					{
        						corpId : _btn.data("relaid")
        					},function(result){
		        				if(result.success){
		        					var _data = result.data;
	    							_view.resetCorpForm();
	    							_view.putValueToForm(_data,"#add_corp_form");
	    							$("#add_corp_corpEstablishDate").val(_data.corpEstablishDateStr);
	    			            	if($("#add_corp_stock_check")[0].checked){
	    			            		$(".add_corp_investment").show();
	    			            	}
					            	disableForm("#add_corp_form");
					            	$("#add_corp_modal").modal("show");
								}else{
									utils.alert.err("<b>查看详细时发生错误</b>");
								}
        					}
        				);
            		}
            		
            	});
            },
            initEditBtn : function(){
            	var _view = this;
            	$(document).on("click","button[role='editRela']",function(e){
            		$("button[role='showExistingParty']").removeAttr("disabled");
            		var _btn = $(this);
            		if(_btn.data("relatype")=='p'){
            			_view.model.detailRelaPerson(
            				{
	        					personId:_btn.data("relaid")
	        				},
	        				function(result){
	    						if(result.success){
	    							var _data = result.data;
	    							_view.resetPersonForm();
	    							_view.putValueToForm(_data,"#add_person_form");
	    							$("#add_person_birthDate").val(_data.birthDateStr);
	    							
	    							if($("#add_person_stock_check")[0].checked){
	    								$(".add_person_invesment").show();
	    								var _checkBoxes = $(".add_person_invesment").find("input[name='investmentTypeCd']");
	    								_checkBoxes.removeAttr("disabled");
	    			            		var invAmt = $("#add_person_investmentAmt");
	    			            		var stockPro = $("#add_person_stockProportion");
	    			            		invAmt.removeAttr("disabled");
	    			        			stockPro.removeAttr("disabled");
	    							}
	    							
	    							if($("#add_person_high_mana_check")[0].checked){
	    								$("#add_person_legal_rep_check").removeAttr("disabled");
	    							}
	    							
	    							if($("#add_person_creditRecord")[0].checked){
	    								$("#add_person_creditRecordOverdue").removeAttr("disabled");
	    								$("#add_person_creditRecordOverdue_warp").show();
	    							}
	    							
	    							if($("#add_person_inhabitancyStatus").val()=='1'){
	    								$(".add_person_residence").show();
	    							}
	    							
	    			            	$("#add_person_modal").modal("show");
	    						}
	        				}
        				);
            			
            		}else if(_btn.data("relatype")=='c'){
            			_view.model.detailRelaCorp(
            				{
            					corpId:_btn.data("relaid")
            				},
            				function(result){
	    						if(result.success){
	    							var _data = result.data;
	    							_view.resetCorpForm();
	    							_view.putValueToForm(_data,"#add_corp_form");
	    							$("#add_corp_corpEstablishDate").val(_data.corpEstablishDateStr);
	    			            	if($("#add_corp_stock_check")[0].checked){
	    			            		var _checkBoxes = $(".add_corp_investment").find("input[name='investmentTypeCd']");
	    			            		var stockPro = $("#add_corp_stockProportion");
	    			            		var invAmt = $("#add_corp_investmentAmt");
	    			            		_checkBoxes.removeAttr("disabled");
	    			        			stockPro.removeAttr("disabled");
	    			        			invAmt.removeAttr("disabled");
	    			            		$(".add_corp_investment").show();
	    			            	}
	    							$("#add_corp_modal").modal("show");
	    						}
            				}
            			);
            		}
            		
            	});
            },
             // 地址列表
            initAddrTable :function(){
            	var viewSelf = this;
            	utils.dd.initDataDict(["AddressType"], function(dataDict) {
	    			var addrTable = $("#tbl_addrList").dataTable({
	    				bLengthChange:false,
	    				bServerSide: true,
	    				bFilter: false,
	    		    	sAjaxSource: $$ctx + "userMngInfo/addrList",
	    		    	fnServerParams: function (aoData) {
	  				      aoData.push({"name": "partyId", "value": $("#detail_party_id").val()});
	    		    	},aoColumns: [
	    		    	{mData: "addressTypeCd",mRender:function(data, type, rowdata){
	    		    		return dataDict.AddressType[data];
	    		    	}},
	    		    	{mData: null,mRender: function(data, type, rowdata) {
	    		    		var text='';//处理省市区
	    		    		if(rowdata.nationName){
	    		    			text+='/'
	    		    			text+=rowdata.nationName;
	    		    		}
	    		    		if(rowdata.provinceName){
	    		    			text+='/'
	    		    			text+=rowdata.provinceName;
	    		    		}
	    		    		if(rowdata.cityName){
	    		    			text+='/'
	    		    			text+=rowdata.cityName;
	    		    		}
	    		    		if(rowdata.countyName){
	    		    			text+='/'
	    		    			text+=rowdata.countyName;
	    		    		}
	    		    		return text.length>0?text.substring(1):text;
	    		    	}},
	    		    	{mData: "streetAddress"},
	    		    	{mData: "telephone"},
	    		    	{mData: null,mRender: function(data, type, rowdata) {
	    		    		var operation = 
			    	    		"<div class='btn-group'>" +
			    	    		"<button title='查看' role='addrView' data-id='" + rowdata.addressId + "' class='btn btn-xs btn-yellow'><i class='ace-icon fa fa-eye'></i></button>" +
			    	    		"<button title='修改' role='addrEdit' data-id='" + rowdata.addressId + "' class='btn btn-xs btn-info'><i class='ace-icon fa fa-edit'></i></button>" +
			    	    		"<button title='删除' role='addrDel' data-id='" + rowdata.addressId + "' class='btn btn-xs btn-danger'><i class='ace-icon fa fa-trash-o'></i></button>" +
				    			"</div>";
			    	    	return operation;
			    		}}]
	    			});
	    			viewSelf.addrTable = addrTable;
            	});
            },
            openAddrAdd:function(){
            	var viewSelf = this;
            	viewSelf.initAddrForm("add",{addressTypeCd:"1",
            		partyId:$("#detail_party_id").val()});
            	$("#input_addr_modal").modal("show");
            },
            initAddrFormValid:function(){//地址表单验证绑定
            	var viewSelf = this;
            	$("#input_addr_form").validate({
	                rules: rm_addr.rules,
	                messages: rm_addr.messages,
	                submitHandler: function(form) {
	                	viewSelf.model.saveAddr($("#input_addr_form").serialize(),function(r_data){
	                		if(r_data.success){
	                			$("#input_addr_modal").modal("hide");
	                			//刷新table
	                			viewSelf.addrTable.fnDraw();
	                		}else{
	                			utils.alert.err("<b>" + r_data.msg + "</b>");
	                		}
	                	});
	                    return false;
	                }
	            });
            },
            initAddrBtnLive:function(){//地址相关按钮绑定
            	var viewSelf = this;
            	//查看动态绑定事件
            	$(document).on("click", "button[role='addrView']", function(e) { 
    				var btnSelf=$(this);
					viewSelf.model.getAddrById(btnSelf.data("id"), function(r_data) {
						if (r_data.success) {
							viewSelf.initAddrForm("view",r_data.data);
							$("#input_addr_modal").modal("show");
						}else{
							utils.alert.err("<b>" + r_data.msg + "</b>");
						}
					});
    			});
            	//删除
    			$(document).on("click", "button[role='addrDel']", function(e) { // 动态绑定所有删除按钮的click事件
    				var btnSelf=$(this);
    				bootbox.confirm({
    					message: "确定要删除？",
    					buttons: {
    						confirm: {
    							label: "<i class='ace-icon fa fa-trash-o bigger-110'></i> 确定",
    							className: "btn-danger btn-sm"
    						},
    						cancel : {
    							label: "<i class='ace-icon fa fa-times bigger-110'></i> 取消",
    							className: "btn-sm"
    						}
    					},
    					callback: function(result) {
    						if (result) {
    							viewSelf.model.delAddrById(btnSelf.data("id"), function(r_data) {
    								if (r_data.success) {
    									viewSelf.addrTable.fnDraw(); // 重新加载表格中的数据
    								}else{
    									utils.alert.err("<b>" + r_data.msg + "</b>");
    								}
    							});
    						}
    					}
    				});
    			});
    			//修改按钮绑定事件
    			$(document).on("click", "button[role='addrEdit']", function(e) { 
    				var btnSelf=$(this);
					viewSelf.model.getAddrById(btnSelf.data("id"), function(r_data) {
						if (r_data.success) {
							viewSelf.initAddrForm("edit",r_data.data);
							$("#input_addr_modal").modal("show");
						}else{
							utils.alert.err(r_data.msg);
						}
					});
    			});
            },
            initAddrForm:function(action,data){//地址表单初始化（添加、修改、查看）
            	var addrForm=$("#input_addr_form");
            	var form_title=$("#addr_form_title")
            	switch (action) {
            	case "edit":
            		form_title.html("<i class='ace-icon fa fa-edit'></i>修改");
					break;
				case "view":
					form_title.html("<i class='ace-icon fa fa-eye'></i>查看");
					break;
				case "add":
					form_title.html("<i class='ace-icon fa fa-plus'></i>新增");
					break;
				default:
					break;
				}
            	if("view"==action){
            		addrForm.find(".modal-footer").hide();
            		addrForm.find(".form-group").find(":input").attr("disabled",true);
            	}else{
            		addrForm.find(".form-group").find(":input").attr("disabled",false);
            		addrForm.find(".modal-footer").show();
            	}
            	addrForm.find("select[name='addressTypeCd']").val(data.addressTypeCd);
            	addrForm.find(":hidden[name='partyId']").val(data.partyId);
            	addrForm.find(":hidden[name='addressId']").val(data.addressId);
            	addrForm.find(":input[name='streetAddress']").val(data.streetAddress);
            	addrForm.find(":input[name='telephone']").val(data.telephone);
            	addrForm.find(":input[name='nationalityCd']").val(data.nationalityCd);
            	$("#nation").change();//更新下一级级联
            	addrForm.find(":input[name='provinceCd']").val(data.provinceCd);
            	$("#province").change();//更新下一级级联
            	addrForm.find(":input[name='cityCd']").val(data.cityCd);
            	$("#city").change();//更新下一级级联
            	addrForm.find(":input[name='countyCd']").val(data.countyCd);
            	addrForm.find(":input[name='zipNum']").val(data.zipNum);
            	addrForm.find(":input[name='fax']").val(data.fax);
            	addrForm.find(":input[name='website']").val(data.website);
            },
            initNationAreaCascade:function(){//省市区级联初始化
            	var viewSelf = this;
            	var nation=$("#nation");
            	var province= $("#province");
            	var city=$("#city");
            	var county= $("#county");
            	var isAll=false;//true 全部国家；false 只有中国
            	if(isAll){
            		viewSelf.model.loadNationAreaData("root",function(r_data){
            			$.each(r_data,function(index, val){
            				nation.append("<option value='"+val.code+"'>"+val.name+"</option>");
            			});
            		});
            	}else{
            		nation.append("<option value='156'>中国</option>");
            	}
            	viewSelf.initSelectCascade(nation,province);
            	viewSelf.initSelectCascade(province,city);
            	viewSelf.initSelectCascade(city,county);
            },
            initSelectCascade:function($select1,$select2){//绑定change，实现级联
            	var viewSelf = this;
            	$select1.change(function(){
            		var selectSelf=$(this);
            		viewSelf.initSelectOptions($select2,selectSelf.val());
            	});
            },
            initSelectOptions:function($select,code){//构建options
            	var viewSelf = this;
            	$select.get(0).options.length=1;//清除多余options
            	if($select.attr("id")=="province"){
            		$("#city").get(0).options.length=1;
                	$("#county").get(0).options.length=1;
            	}else if($select.attr("id")=="city"){
                	$("#county").get(0).options.length=1;
            	}
        		if(code){
        			viewSelf.model.loadNationAreaData(code,function(r_data){
        				$.each(r_data,function(index, val){
        					$select.append("<option value='"+val.code+"'>"+val.name+"</option>");
        				});
        			});
        		}
            },
            //相关文档
            pdownloadBtnLive: function(){
	        	var viewSelf = this;
	        	$(document).on("click","#wdcb",function(e){
	        		var cbs = $("#tbWd").find(":checkbox[name='documentNums']");
	        		if($("#wdcb").is(":checked")){
	        			cbs.prop("checked",true);
	        		}else{
	        			cbs.prop("checked",false);
	        		}
	        	});
	        	$(document).on("click","button[role='pdownloadWd']",function(e){
	        		e.stopPropagation();
	        		var root_upload=$("#uploadPath").val();
	        		var cks = $("#tbWd input[name='documentNums']:checked");
	        		var choice = [];
	        		$.each(cks, function(i,e){
	        			choice.push(e.value);
	        		});
	        		if(choice.length<1){
	        			utils.alert.warn("必须选择一个文件才能进行下载");
	        			return false;
	        		}
	        		 var fileName = "BatchFiles";
	        		 var route = root_upload + '/downloadFileAction.action?cmd=downloadfilejs&DocumentNums=' + choice.join(",") +  "&filename="+ fileName +"&SYS_FLAG=bxloan";
	        		 location.href = route;
	        	});
	        },
            initDocsTable :function(){
            	var viewSelf = this;
            	utils.dd.initDataDict(["CustProjectAllDocType", "DocumentType","CreateType"], function(dataDict) {
	    			var docsTable = $("#tbWd").dataTable({
	    				bServerSide: true,
	    				bFilter: false,
	    				bLengthChange:false,
	    		    	sAjaxSource: $$ctx + "userMngInfo/docsList",
	    		    	fnServerParams: function (aoData) {
	  				      aoData.push({"name": "partyId", "value": $("#detail_party_id").val()});
	  				      var formSearch= $("#form-doc-search");
	  				      aoData.push({"name": "docContentType", "value":$.trim(formSearch.find(":input[name='docContentType']").val())});
	  				      aoData.push({"name": "docName", "value":$.trim(formSearch.find(":input[name='docName']").val())});
	    		    	},aoColumns: [
	    		    	{mData: "docId",mRender:function(data, type, rowdata){
	    		    		var cb  = "<input type='checkbox' name='documentNums' value='"+ rowdata.docId +"' />";
                        	return cb;
						}},
	    		    	{mData: "docName"},
	    		    	{mData: "custDocType",mRender:function(data, type, rowdata){
	    		    		if(data == ''){
	    		    			return '';
	    		    		}
	    		    		return dataDict.CustProjectAllDocType[data];
	    		    	}},
	    		    	{mData: "docTypeCd",mRender:function(data, type, rowdata){
	    		    		return dataDict.DocumentType[data];
	    		    	}},
	    		    	{mData: "customerName",bVisible: false},
	    		    	{mData: "createUserName"},
	    		    	{mData: "createTimeStr"},
	    		    	{mData: "createTypeCd",mRender:function(data, type, rowdata){
	    		    		return dataDict.CreateType[data];
	    		    	}},
	    		    	{mData: null,mRender: function(data, type, rowdata) {
	    		    		var operation = [];
	    		    		operation.push("<div class='btn-group'>");
	    		    		operation.push("<button title='下载' role='docDownload' data-doc_path='");
	    		    		operation.push(rowdata.docPath);
	    		    		operation.push("' data-doc_name='");
	    		    		operation.push(rowdata.docName);
	    		    		operation.push("' class='btn btn-xs btn-yellow'><i class='ace-icon fa fa-download'></i></button>");
	    		    		operation.push("<button title='删除' role='docDel' data-id='");
	    		    		operation.push(rowdata.docId);
	    		    		operation.push("' class='btn btn-xs btn-danger'><i class='ace-icon fa fa-trash-o'></i></button>");
	    		    		operation.push("</div>");
			    	    	return operation.join('');
			    		}}],
			    		fnDrawCallback: function (oSettings) {
			    			 utils.upload.refreshSelectorTable(controllerUrl +"/findDocumentCustDocTypes", {"partyId" : $("#detail_party_id").val()} );
	                    }
	    			});
	    			viewSelf.docsTable = docsTable;
            	});
            },
            initCustDocTable: function(){
            	var viewSelf = this;
            	viewSelf.model.findCustDocTypes({
            		"partyId" : $("#detail_party_id").val()
            	},function(r){
            		if(r!=null&&r.custMap){
            			var map = r.custMap;
            			var $tbody = $("#uploadTbody");
            			var count = 0;
            			var total = 0;
            			var html= "<tr>";
            			for (var key in map) {
            				count++;
            				var str = "<td>" +
            						   "<input type='radio' name='uploadDoc' value='"+ key +"' />" +
            				           "</td>"+
            				           "<td>" + 
            				            map[key]+
            				           "</td>"+
            				           "<td><span name='custSpan_"+ key +"'" +">" +
            				           "</span></td>";
            				 html+=str;
            				if(count==3||r.count==total){
            					html+="</tr>";
            					$tbody.html($tbody.html()+html);
            					count=0; html="<tr>";
            				}
            	        }  
            			
            			var viewSelf = this;
                        $(document).on("click", "input[name='uploadDoc'][type='radio']", function(e) {
                        	 var $this = $(this);
                        	$("#documentType").val($this.val());
                        });
                        
                        $(document).on("click", "button[name='closeUpload']", function(e) {
                       	 var $this = $(this);
                       	$("#documentType").val("");
                       });	
            			
            		}
            	});
            },
            initUploadBtn:function(){
            	//打开文档模态窗口
            	 var viewSelf = this;
            	 var partyId = $("#detail_party_id").val();
            	 var formData  = utils.upload.beforeUpload("userMngInfo/beforeUpload/"+partyId, null);
            	 var onStart = function(file, data){
            		 data.custDocType = $("input[name='uploadDoc'][type='radio']:checked").val();
            		 data.allDocType = "002";//企业
                 };
                 var onOneSuc = function(){
                 	utils.upload.beforeUpload("userMngInfo/beforeUpload/"+partyId, null );
                     $("#documentType").val("");
                     utils.upload.refreshSelectorTable(controllerUrl +"/findDocumentCustDocTypes", {"partyId" : $("#detail_party_id").val()} );
                    var oTable = $("#tbWd").dataTable();
                    oTable.fnDraw();
                 };
                 utils.upload.initUploadify(formData,"#uploadPath","#uploadFile", onStart, onOneSuc);
                 utils.upload.refreshSelectorTable(controllerUrl +"/findDocumentCustDocTypes", {"partyId" : $("#detail_party_id").val()} );
                 
              	var doc_form=$("#input_doc_form");
              	doc_form.find(":input[name='userName']").val(formData.createUserName);
            	$("#input_doc_modal").modal("show");
            },
            addDocUpload:function(){
            	//文档上传
            	$("#uploadFile").uploadify("upload", "*");//批量上传
            },
            searchDocOfClick:function(){
            	//文档搜索
            	var viewSelf = this;
                viewSelf.docsTable.fnDraw();
                return false;
            },
            initDocBtnLive:function(){
            	//文档相关按钮绑定
            	var viewSelf = this;
            	//下载按钮绑定事件
    			$(document).on("click", "button[role='docDownload']", function(e) { 
    				var btnSelf=$(this);
    				var uploadPath=$("#uploadPath").val();
    				var docPath=btnSelf.data("doc_path");
    				var docName=btnSelf.data("doc_name");
    				if(uploadPath&&docPath&&docName){
    					var route=uploadPath+"/fileDownloadServlet.servlet?cmd=downloadfilejs&path="+docPath+"&filename="+docName+"&SYS_FLAG=bxloan";
    					location.href = route;
    				}else{
    					utils.alert.warn("<b>该文档无法找到</b>");
    				}
    			});
    			//删除
    			$(document).on("click", "button[role='docDel']", function(e) { // 动态绑定所有删除按钮的click事件
    				var btnSelf=$(this);
    				bootbox.confirm({
    					message: "确定要删除？",
    					buttons: {
    						confirm: {
    							label: "<i class='ace-icon fa fa-trash-o bigger-110'></i> 确定",
    							className: "btn-danger btn-sm"
    						},
    						cancel : {
    							label: "<i class='ace-icon fa fa-times bigger-110'></i> 取消",
    							className: "btn-sm"
    						}
    					},
    					callback: function(result) {
    						if (result) {
    							viewSelf.model.delDocById(btnSelf.data("id"), function(r_data) {
    								if (r_data.success) {
    									utils.datatable.fresh("#tbWd");
    								}else{
    									utils.alert.err("<b>" + r_data.msg + "</b>");
    								}
    							});
    						}
    					}
    				});
    			});
            },
            
	        /** kangyf create 2014-10-21 */
	        initDataTablesForPcw: function() {
	            /** CW初始化DataTables */
	            var viewSelf = this;
	            /**
	  			 * 财务查看列表
	  			 * */
	            var oTable = $("#tbProjectCw").dataTable({
	                "sAjaxSource":$$ctx + "corpcustomer/findProjectFinalcials",
	                "bFilter": false,
	                "bAutoWidth": true,
	                "bLengthChange": false,
	                "aoColumns": [null, null, null, null, null, null],
	                "aoColumnDefs": [{
	                    "bVisible": false,
	                    "aTargets": [0]
	                },
	                {
	                    "aTargets": [5],
	                    "mRender": function(data, type, full) {
	                        var buttons = "<button type='button' role='detail_pcw' data-id='" + full[0] + "'  class='btn btn-xs btn-yellow' title='查看'><i class='ace-icon fa fa-eye'></i></button> ";
	                        return buttons;
	                    }
	                }
	                ],
	                "fnServerParams": function(aoData) {
	                    aoData.push({
	                        "name": "partyId",
	                        "value": $("#detail_party_id").val()
	                    });
	                },
	                "oLanguage": {
	    				"sZeroRecords": "<p class='text-warning'>没有查找到记录。若对客户发起业务时录入过财务信息，该表会有所显示，无需信贷员填写</p>",
	    				"sEmptyTable": "<p class='text-warning'>没有查找到记录。若对客户发起业务时录入过财务信息，该表会有所显示，无需信贷员填写</p>"
	    			}
	            });
	            viewSelf.oTable = oTable;
	        },
	        detailBtnLivePcw: function() {
                var viewSelf = this;
                $(document).on("click", "button[role='detail_pcw']", function(e) { // 动态绑定所有删除按钮的click事件
                    var $this = $(this);
                    $.ajax({
                        cache: true,
                        type: "POST",
                        url: $$ctx + "corpcustomer/findOneSalaCustFinace",
                        data: {
                            "id": $this.data("id")
                        },
                        async: true,
                        error: function(request) {
                        	utils.alert.err("<b>查找单一财务信息报错" + request + "</b>");
                        },
                        success: function(data) {
                            if (data != null) {
                                $.each($("#detailProjectFinaceForm").find("input[type='text'], select, textarea"),
                                function() {
                                    $(this).val(data[$(this).attr("name")]);
                                    $(this).attr("readonly", "readonly");
                                });
                                $("#projectFinace_modal div.modal-header h4").html("<i class='ace-icon fa fa-plus'></i> 查看财务信息");
                                $("#projectFinace_modal").modal("show");
                            } else {
                            	utils.alert.err("<strong>因未知错误，获取信息失败！</strong>");
                            }
                        }
                    });
                    return false;
                });
            },
            clickfunction: function() {
            	/*监控li*/
    			$(document).on("click", "#myTab li", function(e) {
    				myClickTab = $(this).find("a").attr("href");
    			});
            }
	        ///VIEW END    
        }
    );
    module.exports = view;
}
);
