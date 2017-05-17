define(function(require, exports, module) {
	var reg = /^-?[0-9]*(\.\d*)?$|^-?d^(\.\d*)?$/;
//	var model = require("./model");
	var utils = require("utils");
	var view = Backbone.View.extend({
		el : "body",
		events : {
			"click #customerForFloatWindow": "openCustomerWindow"  //点击客户信息
		},
		initialize : function() {  //初始化
//			this.model = new model();
			this.render();
		},
		render : function() {       //渲染
			this.initPage();        //初始化页面
			this.initFrameHight();  //初始化弹框
		},
		initFrameHight: function(){  //初始化客户信息弹框的高度
 			$("#partyIframe").load(function() {
 				 setInterval(function(){
 	        	    	var clientHeight = $("#partyIframe").contents().find("body").height();
 	        	    	$("#partyIframe").attr("height",clientHeight+"px!important;");
 	        	 },100);
 			});
 		},
		initPage : function() {  //页面初始化
			var applyAmt = $('#applyAmt').val();            //申报金额（元）
			var irNegoSymbCd = $('#irNegoSymbCd').val();    //利率上浮比例（%）
			var bizRate = $('#bizRate').val();				//年利率（%）
			var guaranteeMode = $('#guaranteeMode').val();  // 选中担保方式
			$.each(guaranteeMode.split(","), function(i, val) {
				var check = $('#basicInfoForm').find("input[type='checkbox'][value='" + val + "']");
				if (check[0])
					check[0].checked = true;
			});
			var isheadcol = $('#isheadcol').val();  // 选中总部协同业务
			if (isheadcol) {
				$('#basicInfoForm').find(':radio[name="isheadcol"][value="' + isheadcol + '"]').prop("checked",true);
			}
			if(guaranteeMode) {   // 根据担保方式确定哪些“新增关联”按钮可用
    			if (guaranteeMode.indexOf('1') >= 0 || guaranteeMode.indexOf('2') >= 0) {    // 选中抵、质押
    				$('#addPawn').removeAttr('disabled');
    			}
    			if (guaranteeMode.indexOf('3') >= 0) {    // 选中保证
    				$('#addBail').removeAttr('disabled');
    			}
			}
			setTimeout(function() {  //获取婚姻状况，决定“配偶是否作为共同还款人”是否可选
				if (!$('#marriageCd').val() || $('#marriageCd').val() != '20') {// 非个人客户或非已婚客户
					$('#mateBorrower').val('2');
					$('#mateBorrower').attr('disabled', true);
				}
			}, 100);
			
			$('#productType').val($('#productCd').val());  // 选定贷款产品
			
			var dateOnTop = function() {  //将datepicker控件放在最顶层
				$(".datepicker").css("z-index", "99999");
			};
			var defaultDateConf = {   //日期控件配置信息
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
			initDateP("#applyDate");  //初始化申报日期 
			
			$(document).on("click", "#basicInfoForm input[type=checkbox]", function(e) {  //绑定勾选担保方式事件
				if ($(this).is(':checked'))
					$('#guaranteeMode').val($('#guaranteeMode').val() + $(this).val() + ',');
				else
					$('#guaranteeMode').val($('#guaranteeMode').val().replace($(this).val() + ',', ''));
				$('#guaranteeMode').change();
			});
			
			$(document).on("blur", "#applyAmt, #bizRate, #irNegoSymbCd", function(e) { //去掉在数字文本框内输入的002,010等无效0
				var value = $(this).val();
				var id = $(this).attr('id');
				if($(this).attr('id') == 'applyAmt' && $("#applyAmt").valid()) {  //授信额度千分位显示
					$(this).val(utils.number.toAmt($(this).val()));
				} else if(value && value.match(reg) != null) {
					$(this).val(parseFloat(value));
				}
			});
			if ($('#type').val() == 'check') {  //type为check是，只能进行查看，控件都不可用
				$.each($("div").find(".page-content input[id!=query_documentName][name!=query_contentType][name!=documentNums][id!=wdcb]," +
				" .page-content select, .page-content textarea, #showTree_"), function() {
			     $(this).attr('disabled',true);
		        });
			}
		},
		openCustomerWindow: function() {   //客户信息弹框
			$.ajax({
				type : 'post',
				url : $$ctx + 'businessapplicationwd/findOneParty',
				data : {
					"partyId" : $('#partyId').val()
				},
				success : function(party) {
					var url = '';
					if(party.partyTypeCd == '1')
						url = $$ctx + 'corpcustomer/showDetail/' +party.partyId+ '?consultLocation=business';
					else
						url = $$ctx + 'singleCustomer/backToForm?customerId=' + party.partyId +
								'&workCode=TODETAIL&customerSource=detail&consultLocation=contract';
					$('#partyIframe').attr('src' , url);
					$('#partyDetailDiv').modal('show');   
				}
			});
        }
	});
	module.exports = view;
});