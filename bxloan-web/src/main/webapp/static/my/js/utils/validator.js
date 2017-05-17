var present_num= [];

jQuery.validator.addMethod("phone", function(value, element, param) {
	return this.optional(element) || /^1[2|3|5|7|8|9]\d{9}$/.test(value);
}, "请输入有效的手机号码");

/**验证手机号*/
jQuery.validator.addMethod("isMobile", function(value, element) {   
    var length = value.length;
    var mobile = /^((134[0-8]\d{7})|(14[57]\d{8})|(1[35][0-35-9]\d{8})|(17(0[059]\d{7})|([6-8]\d{8}))|(18\d{9}))$/;

    return this.optional(element) || (length == 11 && mobile.test(value));       
}, "请按照手机11位号码的规则填写");      

/**验证传真*/
jQuery.validator.addMethod("isFax", function(value, element) {       
    var length = value.length;   
    var fax = /^(([0\+]\d{2,3}-)?(0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/;   
    return this.optional(element) || (fax.test(value));       
}, "请按照国家代码(2到3位)-区号(2到3位)-电话号码(7到8位)-分机号(3位)的格式输入");    


/**验证座机号*/
jQuery.validator.addMethod("isTel", function(value, element) {    
  var tel = /^(\d{3,4}-?)?\d{7,9}$/g;    
  return this.optional(element) || (tel.test(value));    
}, "请按照 区号-号码 的规则填写");

/**手机电话皆可*/
jQuery.validator.addMethod("isPhone", function(value,element) {
    var length = value.length;   
    var mobile = /^((134[0-8]\d{7})|(14[57]\d{8})|(1[35][0-35-9]\d{8})|(17(0[059]\d{7})|([6-8]\d{8}))|(18\d{9}))$/;
    var tel =  /^(\d{3,4}-?)?\d{7,9}$/g;    
    return this.optional(element) || tel.test(value) || mobile.test(value);   
}, "请按照 座机:区号-号码;手机:11位号码的规则填写");
    
/**验证邮编*/
jQuery.validator.addMethod("isZipCode", function(value, element) {   
    var tel = /^[0-9]{6}$/;
    return this.optional(element) || (tel.test(value));
}, "请正确填写您的邮政编码");
/**微信号的验证*/
jQuery.validator.addMethod("isMicro", function(value,element) {
    var length = value.length;   
    var mobile = /^1[3|4|5|8][0-9]\d{4,8}$/;   
    var qq = /^[1-9]\d{4,9}$/;   
    return this.optional(element) || (qq.test(value) || mobile.test(value));   
}, "请填写微信号码:手机号或QQ号");


/**验证非负整数*/
jQuery.validator.addMethod("isIntNotNegative", function(value, element) {
	return this.optional(element) || /^\d+$/.test(value);  
}, "请输入大于或等于0的整数");  

/**验证正整数*/
jQuery.validator.addMethod("isIntPositive", function(value, element) { 
	return this.optional(element) || /^[1-9]\d*$/.test(value);  
}, "请输入大于0的整数");  

/**验证1-31*/
jQuery.validator.addMethod("isDayInMonth", function(value, element) { 
	return this.optional(element) || (/^[1-9]\d*$/.test(value)&&function(){
		var intVal = parseInt(value);
		return intVal>0&&intVal<=31;
	}());  
}, "请输入1-31的整数");

/**验证1-12*/
jQuery.validator.addMethod("isMonth", function(value, element) { 
	return this.optional(element) || (/^[1-9]\d*$/.test(value)&&function(){
		var intVal = parseInt(value);
		return intVal>0&&intVal<=12;
	}());  
}, "请输入1-12的整数");

/**农贷年龄 验证25-60*/
jQuery.validator.addMethod("isAgroAge", function(value, element) { 
	return this.optional(element) || (/^[1-9]\d*$/.test(value)&&function(){
		var intVal = parseInt(value);
		return intVal>24&&intVal<=60;
	}());  
}, "请输入25-60的正整数");  

jQuery.validator.addMethod("isAge", function(value, element) { 
	return this.optional(element) || (/^[1-9]\d*$/.test(value)&&function(){
		var intVal = parseInt(value);
		return intVal>=0&&intVal<=200;
	}());  
}, "请输入表示年龄的正整数");  

/**农贷 家庭人口等，0-100的数字*/
jQuery.validator.addMethod("isPopulation", function(value, element, param) { 
	return this.optional(element) || (/^[1-9]\d*$/.test(value)&&function(){
		var intVal = parseInt(value);
		present_num[0] = param[0];
		present_num[1] = param[1];
		return intVal>=param[0]&&intVal<=param[1];
	}());  
}, "请输入"+present_num[0]+"-"+present_num[1]+"的正整数");  

/**验证整数value是否大于或等于0*/
jQuery.validator.addMethod("isNotNegative", function(value, element) { 
	var positive = /^\d{0,99}(\.\d{0,2})?$/;      ///^[0-9]+(\.[0-9]+)?$/;
     return this.optional(element) || positive.test(value);       
}, "请输入大于或等于0的数字(允许2位小数)");  

/**验证农户客户的商户名*/
jQuery.validator.addMethod("isPeasantMerchant", function(value, element, param) {
	if ($(param).val() === '2') {
		return true;
	}else if($(param).val() === '3'){
		if(value!=""){
			return true;
		}else{
			return false;
		}
	}
}, "请填写商户名");
/**
 * 贷款期限校验（贷款试算）
 */
jQuery.validator.addMethod("loanTermByUnit", function(value, element, param) {
	var validator = this;
	var reg=/^\d+$/;//正整数
	var target = $(param);
	var msg="贷款期限校验失败！";
	if(!(this.optional(element)||reg.test(value))){
		validator.settings.messages[element.name].loanTermByUnit="请输入大于0的数字！"
		return false;
	}
	var val=parseInt(value);
	var valid=false;
	switch(target.val()){
		case '1'://年
			if(val>0&&val<=30){
				valid=true;
			}else{
				valid=false;
				msg="单位为【年】时,期限为0-30";
			}
			break;
		case '2'://月
			if(val>0&&val<=360){
				valid=true;
			}else{
				valid=false;
				msg="单位为【月】时,期限为0-360";
			}
			break;
		case '3'://日
			if(val>0&&val<=5000){
				valid=true;
			}else{
				valid=false;
				msg="单位为【日】时,期限为0-5000";
			}
			break;
		default:
			break;
	}
	validator.settings.messages[element.name].loanTermByUnit = msg;
	return valid;
}, "请填写贷款期限及单位！");
/**验证利率变动*/
jQuery.validator.addMethod("isRateAdjust", function(value, element, param) {
	if ($(param).val() === '1') {
		if(value!=""){
			return true
		}else{
			return false;
		}
	}
	return true;
}, "请填写利率变动的原因");

/**验证放款金额是否在可支付范围内*/
jQuery.validator.addMethod("isAffordLoanAmt", function(value, element, param) {
	if (param != null&&value <= $(param).val()) {
		if(value!=0){
			return true;
		}else{
			return false;
		}
		}else{
			return false;
		}
}, "本次发放金额不能大于可发放金额，且不可为0,请重新输入!");

/**验证是否必须填写费用来源*/
jQuery.validator.addMethod("needSourceType", function(value, element, param) {
	if ($(param).val() === '0') {
			return true
		}else{
			if(value==null){
			return false;}
			else{
				return true;
			}
		}
}, "请填写手续费和佣金的费用来源");

/**验证一位小数*/
jQuery.validator.addMethod("isFloatOneBit", function(value, element) {
				if(value!=""){
					return /^\d{0,2}(\.\d{0,1})?$/.test(value);
				}
			return true;
}, "请填写正数：最多两位整数，一位小数");

/**验证一位小数*/
jQuery.validator.addMethod("isIntThreeFloatOneBit", function(value, element) {
				if(value!=""){
					return /^\d{0,3}(\.\d{0,1})?$/.test(value);
				}
			return true;
}, "请填写正数：最多三位整数，一位小数");

/**验证还款约定日期*/
jQuery.validator.addMethod("isArrangeRepayDay", function(value, element, param) {
	if ($(param).val() === '1') {
		if(value!=""){
			return /^\d+$/.test(value)?(value<=31&&value>0):false;
		}else{
			return false;
		}
	}
	return true;
}, "请选择还款日期(1-31)");

/**验证身份证号15位、18位都可*/
jQuery.validator.addMethod("isIDcard", function(value, element, param) {
	if (param == null || $(param).val() === '100') {
		return checkIdCard(value);
	}
	return true;
}, "请正确填写15位或18位身份证号码");

/**验证组织机构号*/
jQuery.validator.addMethod("isOrgCode", function(value, element, param) {
	if (param == null || $(param).val() === '210') {
		return checkOrgCode(value)||/^\d{18}$/.test(value);
	}
	return true;
}, "请正确填写组织机构代码号（8位数字/大写字母+1位大写字母/数字）或 18位数字");

/**验证组织机构号*/
jQuery.validator.addMethod("isOrgCodeAndLicence", function(value, element) {
		return checkOrgCode(value)||/^\d{18}$/.test(value);
}, "（8位数字/大写字母 + 横杆(-) + 1位大写字母/数字）或 18位数字");

/**验证军官证*/
jQuery.validator.addMethod("isAICard", function(value, element, param) {
	if (param == null || $(param).val() === '112') {
		return checkAICard(value);
	}
	return true;
}, "请正确填写军官证");

/**验证护照编号*/
jQuery.validator.addMethod("isPassport", function(value, element, param) {
	if (param == null || $(param).val() === '456') {
		return checkPassport(value);
	}
	return true;
}, "请正确填写护照编号");

/**验证身份证号,联系人专用*/
jQuery.validator.addMethod("isIDcardFamily", function(value, element, param) {
	if (param == null || $(param).val() === '100') {
		return checkIdCard(value);
	}
	return true;
}, "请正确填写15位或18位身份证号码");

/**验证证件号,联系人专用*/
jQuery.validator.addMethod("isFamilyNum", function(value, element, param) {
	if ($(param).val()==""&&value!="") {
		return false;
	}
	return true;
}, "请先选择证件类型，再填写号码");

/**验证email*/
jQuery.validator.addMethod("isEmail", function(value, element) {
	 return this.optional(element) || /^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/.test(value);  
}, "请按照邮箱格式填写,如:zhangsan@coamc.com");    

/**验证整数和小数*/
jQuery.validator.addMethod("isNumber", function(value, element) {       
     return this.optional(element) || /^[-\+]?\d+$/.test(value) || /^[-\+]?\d+(\.\d+)?$/.test(value);       
}, "请输入整数或小数");   

/**正整数或小数*/
jQuery.validator.addMethod("isPositiveNumber", function(value, element) {       
     return this.optional(element) || /^[1-9]\d*$/.test(value) || /^\d+(\.\d+)?$/.test(value);       
}, "正整数或正小数");  


/**正整数或两位小数*/
jQuery.validator.addMethod("isPositiveNumberTwo", function(value, element) {
	//console.log(/^[0-9]+(\.[0-9]{1,2})?$/.test(value));
     return this.optional(element) || /^[0-9]+(\.[0-9]{1,2})?$/.test(value);       
}, "正整数或两位小数");

/**千分位正整数或两位小数*/
jQuery.validator.addMethod("isThousandsPositiveNumberTwo", function(value, element) {
	value = parseFloat(value.split(",").join(""));
     return this.optional(element) || /^[1-9]\d*$/.test(value) || /^\d+(\.\d{1,2})?$/.test(value);       
}, "正整数或两位小数");
/**正整数或六位小数*/
jQuery.validator.addMethod("isPositiveNumberFour", function(value, element) {       
     return this.optional(element) ||(/^[1-9]\d*$/.test(value) || /^\d+(\.\d{1,4})?$/.test(value));       
}, "请输入大于0的整数或四位小数"); 
/**正整数或六位小数*/
jQuery.validator.addMethod("isPositiveNumberSix", function(value, element) {       
     return this.optional(element) || /^[1-9]\d*$/.test(value) || /^\d+(\.\d{1,2,3,4,5,6})?$/.test(value);       
}, "正整数或两位小数"); 
/**验证营业执照*/
jQuery.validator.addMethod("isBusinessLicenseNum", function(value, element) {       
	return this.optional(element) || /^\d{6}[123]\d{7}[1-9]$/.test(value);       
}, "营业执照输入不合法");  

/**验证是否是0-9的数字*/
jQuery.validator.addMethod("isDigits", function(value, element) {       
     return this.optional(element) || /^\d+$/.test(value);       
}, "请输入0-9的数字");  

/**验证中文字符*/
jQuery.validator.addMethod("isChinese", function(value, element) {       
     return this.optional(element) || /^[\u0391-\uFFE5]+$/.test(value);       
}, "请输入中文。"); 

/**验证英文字符*/
jQuery.validator.addMethod("isEnglish", function(value, element) {       
     return this.optional(element) || /^[A-Za-z]+$/.test(value);       
}, "请输入英文。");   

/**匹配QQ号*/
jQuery.validator.addMethod("isQq", function(value, element) {       
    return this.optional(element) || /^[1-9]\d{4,9}$/.test(value);
}, "请正确输入5到10位的QQ号码");  

/**匹配QQ号或邮箱*/
jQuery.validator.addMethod("isQqOrEmail", function(value, element) {       
    return this.optional(element) 
    || /^[1-9]\d{4,9}$/.test(value)
    ||  /^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/.test(value);;
}, "请正确输入5到10位的QQ号码或邮箱账号");  

/**验证字符，仅能包含中文、英文、数字、下划线等字符。*/    
jQuery.validator.addMethod("stringCheck", function(value, element) {       
     return this.optional(element) || /^[a-zA-Z0-9\u4e00-\u9fa5-_]+$/.test(value);       
}, "请不要输入非中文、英文、数字、下划线的字符");  

/**验证字符，仅能包含中文、数字、下划线等字符。*/    
jQuery.validator.addMethod("specialStringCheck", function(value, element) {       
     return this.optional(element) || /^[\sa-zA-Z0-9\u4e00-\u9fa5-_]+$/.test(value);       
}, "请不要输入非中文、英文、数字、下划线、空格的字符");  

jQuery.validator.addMethod("nameStringCheck", function(value, element) {       
//     return this.optional(element) || /^[\sa-zA-Z\u4e00-\u9fa5]+$/.test(value);       
     return this.optional(element) || /^([\u4E00-\uFA29]|[\uE7C7-\uE7F3]|[a-zA-Z])*$/.test(value);       
}, "请输入中文或英文字符");  
/**验证银行卡号*/
jQuery.validator.addMethod("isBankNum", function(value, element) {
	return this.optional(element) || function(){
		value = value.trim();
		var num = /^\d*$/;  //全数字
		if(value.length>25||value.length<10){
			return false;
		}else if (!num.exec(value)) {
			return false;
		}else{
			return true;
		} 
	}();
}, "请正确填写10-25位的账户号码");
/**验证房产、车产专用*/
jQuery.validator.addMethod("hasHouseOrCar", function(value, element, param) {
	if (param == null || $(param).val() == '1') {
	     return  /^[1-9]\d*$/.test(value);    
	}
	return true;
}, "填写拥有数量，请输入整数");

/**企业客户名称*/
jQuery.validator.addMethod("isCorpCustomer", function(value, element) {
	var reg = /^[a-zA-Z0-9_()（）\u4e00-\u9fa5]+$/;  
	return reg.test(value);
}, "请填写中文字母数字或下划线组成的名称");

jQuery.validator.addMethod("notBothBlank", function(value, element, targetSelector) {
	return $.trim(value)!='' || $.trim($(targetSelector).val())!='' ;
}, "不能同时为空");

jQuery.validator.addMethod("valueEq", function(value, element, targetSelector) {
	return value == $(targetSelector).val() ;
}, "两次输入不一致");

/**小于100的正整数或两位小数*/
jQuery.validator.addMethod("isPercentNumberTwo", function(value, element) {       
     return this.optional(element) 
     ||((/^[1-9]\d*$/.test(value) || /^\d+(\.\d{1,2})?$/.test(value))&&(parseFloat(value)>0&&parseFloat(value)<=100));       
}, "输入大于0且小于100的正整数或两位小数");  

/**小于100的正整数或四位小数*/
jQuery.validator.addMethod("isPercentNumberFour", function(value, element) {       
     return this.optional(element) 
     ||((/^[1-9]\d*$/.test(value) || /^\d+(\.\d{1,4})?$/.test(value))&&(parseFloat(value)>0&&parseFloat(value)<=100));       
}, "输入大于0且小于100的正整数或四位小数");  

/**税务登记号*/
jQuery.validator.addMethod("isTaxRegNum", function(value, element) {       
     return this.optional(element) 
     ||/(^([0-9A-Za-z]|X)*$)|(^([0-9A-Za-z]|X)*$)/.test(value);
}, "请正确输入税务登记号"); 
//notNullWhenTargetCk : "input[type='radio'][value='0'][name='WORK_YEAR_CK']"
/**如果目标被选中时必须填写**/
jQuery.validator.addMethod("notNullWhenTargetCk", function(value, element, targetSelector) {
	if($(targetSelector)[0].checked){
		return $.trim(value)!='';
	}
	return true;
}, "此项不能为空");

/**比较日期*/
jQuery.validator.addMethod("compareDate", function(value, element, params) {
	var flag = false;
	if(parseInt(params[1]) == -1){
		value < $(params[0]).val()  ?(flag=true):(flag=false); 
	}else if(parseInt(params[1]) == 1){
		value > $(params[0]).val()  ?(flag=true):(flag=false);
	}else if(parseInt(params[1]) == -2){
		value <= $(params[0]).val()  ?(flag=true):(flag=false);
	}else if(parseInt(params[1]) == 2){
		value >= $(params[0]).val()  ?(flag=true):(flag=false);
	}else if(parseInt(params[1])==0){
		$(params[0]).val() == value?(flag=true):(flag=false);
	}else{
		return flag;
	}
	return flag;
}, "输入的日期时间不符合要求");

/**	判断是否符合产品表配置的贷款期限 */
jQuery.validator.addMethod("isLoanTerm", function(value, element) {
	var validator = this;
	var valid;
	var msg="贷款期限校验失败！";
	$.ajax({
		type : 'POST',
		url : $$ctx + 'productMng/findByProjectId',
		data : {
			"projectId" : $("#projectId").val()
		},
		async : false,
		success : function(productConfig) {
			var minLoanTerm = productConfig.minLoanTerm;
			var maxLoanTerm = productConfig.maxLoanTerm;
			var specialLoanTerm = productConfig.specialLoanTerm;
			if (productConfig.loanTermMode == '2') {// 区间
				msg = "贷款期限区间为：" + minLoanTerm + "-" + maxLoanTerm;
				valid = validator.optional(element) || ((/^[1-9]\d*$/.test(value) || /^[1-9]\d*$/.test(value)) && (parseInt(value) >= minLoanTerm) && (parseInt(value) <= maxLoanTerm));
			} else if (productConfig.loanTermMode == '1') {// 固定
				msg = "贷款期限必须为：" + minLoanTerm;
				valid = validator.optional(element) || ((/^[1-9]\d*$/.test(value) || /^[1-9]\d*$/.test(value)) && parseInt(value) == minLoanTerm);
			} else if (productConfig.loanTermMode == '3') {// 特殊
				msg = "贷款期限只能包括：" + specialLoanTerm;
				var legal = false;
				$.each(specialLoanTerm.split(","), function(i, item) {
					if (validator.optional(element) || ((/^[1-9]\d*$/.test(value) || /^[1-9]\d*$/.test(value)) && parseInt(value) == parseInt(item))) {
						legal = true;
						return false;
					}
				});
				valid = legal;
			} else {
				var applyTerm = $("#applyTermUnitSelect").val();
				minLoanTerm = 1;
				if(applyTerm == '1'){//年
					maxLoanTerm = 30;
				}else if(applyTerm == '2'){//月
					maxLoanTerm = 30 * 12;
				}else if(applyTerm == '3'){//日
					maxLoanTerm = 30 * 12 * 365;
				}
				msg = "贷款期限区间为：" + minLoanTerm + "-" + maxLoanTerm;
				valid = validator.optional(element) || ((/^[1-9]\d*$/.test(value) || /^[1-9]\d*$/.test(value)) && (parseInt(value) >= minLoanTerm) && (parseInt(value) <= maxLoanTerm));
			}
		}
	});
	validator.settings.messages[element.name].isLoanTerm = msg;
	return valid;
});
/**	判断是否符合产品表配置的申请金额 */
jQuery.validator.addMethod("isApplyAmt", function(value, element) {
	var validator = this;
	var valid;
	var msg = '金额校验失败！';
	$.ajax({
		type : 'POST',
		url : $$ctx + 'productMng/findByProjectId',
		data : {
			"projectId" : $("#projectId").val()
		},
		async : false,
		success : function(productConfig) {
			var minApplyAmt = productConfig.minApplyAmt;
			var maxApplyAmt = productConfig.maxApplyAmt;
			//console.info(minApplyAmt + ";" + maxApplyAmt);
			value = value.split(',').join("");
			if (isLegal(minApplyAmt) && isLegal(maxApplyAmt)) {// 区间
				msg = "申请金额区间为：" + minApplyAmt + "-" + maxApplyAmt;
				valid = validator.optional(element) || ((/^[0-9]+(\.[0-9]{1,2})?$/.test(value)) && (parseFloat(value) >= minApplyAmt && parseFloat(value) <= maxApplyAmt));
			} else {// 无
				msg = "正整数或两位小数";
				valid = validator.optional(element) || /^[0-9]+(\.[0-9]{1,2})?$/.test(value);
			}
		}
	});
	validator.settings.messages[element.name].isApplyAmt = msg;
	return valid;
});
/**	判断申请金额是否可用，是否符合产品配置表中的批量金额要求
 * 	@param parmas 项目id
 * */
jQuery.validator.addMethod("isApplyAmtAvailable", function(value, element, params){
	var validator = this;
	var valid;
	var msg = '金额校验失败！';
	$.ajax({
		type : 'POST',
		url : $$ctx + 'businessapplicationwd/checkApplyAmtIsAvailable',
		data: {
			"projectId" : $(params[0]).val(),
			"applyAmt" : parseFloat(element.value.split(",").join(""))
		},
		async : false,
		success : function(result){
			valid = result.success;
			validator.settings.messages[element.name].isApplyAmtAvailable = result.msg;
		}
	});
	return valid;
});
/**	判断申请金额小于授信剩余可用额度
 * 	@param
 * */
jQuery.validator.addMethod("checkCreditLoanAmt", function(value, element, params){
	var validator = this;
	var valid=true;
	var creditAmt=$("#applyUsefulAmt_s").val();
	value = value.split(',').join("");
	var msg = '金额校验失败！';
	if(parseFloat(value)>parseFloat(creditAmt)){
		valid=false;
	}
	return valid;
});
/**	判断是否符合产品表配置的利率 */
jQuery.validator.addMethod("isRate", function(value, element) {
	var validator = this;
	var valid;
	var msg="年利率校验失败！";
	$.ajax({
		type : 'POST',
		url : $$ctx + 'productMng/findByProjectId',
		data : {
			"projectId" : $("#projectId").val()
		},
		async : false,
		success : function(productConfig) {
			var minRate = productConfig.minRate;
			var maxRate = productConfig.maxRate;
			if (isLegal(minRate) && isLegal(maxRate)) {// 区间
				msg = "年利率区间为：" + minRate + "-" + maxRate;
				valid = validator.optional(element) || ((/^[1-9]\d*$/.test(value) || /^\d+(\.\d{1,2})?$/.test(value)) && (parseFloat(value) >= minRate && parseFloat(value) <= maxRate));
			} else {// 无
				msg = "输入大于0且小于100的正整数或两位小数";
				valid = validator.optional(element) || ((/^[1-9]\d*$/.test(value) || /^\d+(\.\d{1,2})?$/.test(value)) && (parseFloat(value) > 0 && parseFloat(value) <= 100));
			}
		}
	});
	validator.settings.messages[element.name].isRate = msg;
	return valid;
});
/**	判断是否符合产品表配置的逾期利率上浮比例 */
jQuery.validator.addMethod("isOverdueRate", function(value, element) {
	var validator = this;
	var valid;
	var msg="逾期利率校验失败！";
	$.ajax({
		type : 'POST',
		url : $$ctx + 'productMng/findByProjectId',
		data : {
			"projectId" : $("#projectId").val()
		},
		async : false,
		success : function(productConfig) {
			var minOverdueRate = productConfig.minOverdueRate;
			var maxOverdueRate = productConfig.maxOverdueRate;
			if (isLegal(minOverdueRate) && isLegal(maxOverdueRate)) {// 区间
				msg = "逾期利率上浮比区间为：" + minOverdueRate + "-" + maxOverdueRate;
				valid = validator.optional(element) || ((/^[1-9]\d*$/.test(value) || /^\d+(\.\d{1,2})?$/.test(value)) && (parseFloat(value) >= minOverdueRate && parseFloat(value) <= maxOverdueRate));
			} else {// 无
				msg = "输入大于0且小于100的正整数或两位小数";
				valid = validator.optional(element) || ((/^[1-9]\d*$/.test(value) || /^\d+(\.\d{1,2})?$/.test(value)) && (parseFloat(value) > 0 && parseFloat(value) <= 100));
			}
		}
	});
	validator.settings.messages[element.name].isOverdueRate = msg;
	return valid;
});
/**	判断是否符合产品表配置的提前还款违约金比例 */
jQuery.validator.addMethod("isPreRepaymentRate", function(value, element) {
	var validator = this;
	var valid;
	var msg="提前还款违约金校验失败！";
	$.ajax({
		type : 'POST',
		url : $$ctx + 'productMng/findByProjectId',
		data : {
			"projectId" : $("#projectId").val()
		},
		async : false,
		success : function(productConfig) {
			var minPreRepaymentRate = productConfig.minPreRepaymentRate;
			var maxPreRepaymentRate = productConfig.maxPreRepaymentRate;
			if (isLegal(minPreRepaymentRate) && isLegal(maxPreRepaymentRate)) {// 区间
				msg = "提前还款违约金比区间为：" + minPreRepaymentRate + "-" + maxPreRepaymentRate;
				valid = validator.optional(element) || ((/^[1-9]\d*$/.test(value) || /^\d+(\.\d{1,2})?$/.test(value)) && (parseFloat(value) >= minPreRepaymentRate && parseFloat(value) <= maxPreRepaymentRate));
			} else {// 无
				msg = "输入大于等于0且小于100的正整数或两位小数";
				valid = validator.optional(element) || ((/^[1-9]\d*$/.test(value) || /^\d+(\.\d{1,2})?$/.test(value)) && (parseFloat(value) >= 0 && parseFloat(value) <= 100));
			}
		}
	});
	validator.settings.messages[element.name].isPreRepaymentRate = msg;
	return valid;
});
/**	判断是否符合产品表配置的挪用利率上浮比例 */
jQuery.validator.addMethod("isPerculNegoRate", function(value, element) {
	var validator = this;
	var valid;
	var msg="挪用利率校验失败！";
	$.ajax({
		type : 'POST',
		url : $$ctx + 'productMng/findByProjectId',
		data : {
			"projectId" : $("#projectId").val()
		},
		async : false,
		success : function(productConfig) {
			var minPerculNegoRate = productConfig.minPerculNegoRate;
			var maxPerculNegoRate = productConfig.maxPerculNegoRate;
			if (isLegal(minPerculNegoRate) && isLegal(maxPerculNegoRate)) {// 区间
				msg = "挪用利率上浮比区间为：" + minPerculNegoRate + "-" + maxPerculNegoRate;
				valid = validator.optional(element) || ((/^[1-9]\d*$/.test(value) || /^\d+(\.\d{1,2})?$/.test(value)) && (parseFloat(value) >= minPerculNegoRate && parseFloat(value) <= maxPerculNegoRate));
			} else {// 无
				msg = "输入大于0且小于100的正整数或两位小数";
				valid = validator.optional(element) || ((/^[1-9]\d*$/.test(value) || /^\d+(\.\d{1,2})?$/.test(value)) && (parseFloat(value) > 0 && parseFloat(value) <= 100));
			}
		}
	});
	validator.settings.messages[element.name].isPerculNegoRate = msg;
	return valid;
});

var isLegal = function(value) {
	return typeof(value) != 'undefined' && $.trim(value) != '' && value != null && value != 'null';
};

/**jQuery的默认回复信息*/
jQuery.extend(jQuery.validator.messages, {
	  required: "此项必填",
	  remote: "此项输入有误，请修正",
	  number: "请输入合法的数字",
	  digits: "请输入整数",
	  maxlength: jQuery.validator.format("输入内容长度最多为 {0} "),
	  minlength: jQuery.validator.format("输入内容长度最少是 {0} "),
	  rangelength: jQuery.validator.format("输入内容长度介于 {0} 和 {1} 之间"),
	  range: jQuery.validator.format("请输入一个介于 {0} 和 {1} 之间的值"),
	  max: jQuery.validator.format("请输入一个最大为{0} 的值"),
	  min: jQuery.validator.format("请输入一个最小为{0} 的值")
	});




/**验证身份证号的方法*/
function checkIdCard(value) {
	var num = value;
	num = num.toUpperCase();  
    //身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X。   
    if (!(/(^\d{15}$)|(^\d{17}([0-9]|X)$)/.test(num)))   
    { 
        //alert('输入的身份证号长度不对，或者号码不符合规定！\n15位号码应全为数字，18位号码末位可以为数字或X。'); 
        return false; 
    } 
    //校验位按照ISO 7064:1983.MOD 11-2的规定生成，X可以认为是数字10。 
    //下面分别分析出生日期和校验位 
    var len, re; 
    len = num.length; 
    if (len == 15) 
    { 
        re = new RegExp(/^(\d{6})(\d{2})(\d{2})(\d{2})(\d{3})$/); 
        var arrSplit = num.match(re);

        //检查生日日期是否正确 
        var dtmBirth = new Date('19' + arrSplit[2] + '/' + arrSplit[3] + '/' + arrSplit[4]); 
        var bGoodDay; 
        bGoodDay = (dtmBirth.getYear() == Number(arrSplit[2])) && ((dtmBirth.getMonth() + 1) == Number(arrSplit[3])) && (dtmBirth.getDate() == Number(arrSplit[4])); 
        if (!bGoodDay) 
        { 
            //alert('输入的身份证号里出生日期不对！');   
            return false; 
        } 
        else 
        { 
                //将15位身份证转成18位 
                //校验位按照ISO 7064:1983.MOD 11-2的规定生成，X可以认为是数字10。 
                var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2); 
                var arrCh = new Array('1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'); 
                var nTemp = 0, i;   
                num = num.substr(0, 6) + '19' + num.substr(6, num.length - 6); 
                for(i = 0; i < 17; i ++) 
                { 
                    nTemp += num.substr(i, 1) * arrInt[i]; 
                } 
                num += arrCh[nTemp % 11];   
                return true;   
        }   
    } 
    if (len == 18) 
    { 
        re = new RegExp(/^(\d{6})(\d{4})(\d{2})(\d{2})(\d{3})([0-9]|X)$/); 
        var arrSplit = num.match(re);

        //检查生日日期是否正确 
        var dtmBirth = new Date(arrSplit[2] + "/" + arrSplit[3] + "/" + arrSplit[4]); 
        var bGoodDay; 
        bGoodDay = (dtmBirth.getFullYear() == Number(arrSplit[2])) && ((dtmBirth.getMonth() + 1) == Number(arrSplit[3])) && (dtmBirth.getDate() == Number(arrSplit[4])); 
        if (!bGoodDay) 
        { 
            //alert(dtmBirth.getYear()); 
            //alert(arrSplit[2]); 
            //alert('输入的身份证号里出生日期不对！'); 
            return false; 
        } 
    else 
    { 
        //检验18位身份证的校验码是否正确。 
        //校验位按照ISO 7064:1983.MOD 11-2的规定生成，X可以认为是数字10。 
        var valnum; 
        var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2); 
        var arrCh = new Array('1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'); 
        var nTemp = 0, i; 
        for(i = 0; i < 17; i ++) 
        { 
            nTemp += num.substr(i, 1) * arrInt[i]; 
        } 
        valnum = arrCh[nTemp % 11]; 
        if (valnum != num.substr(17, 1))  { 
            //alert('18位身份证的校验码不正确！应该为：' + valnum); 
            return false; 
        } 
        return true; 
    } 
    } 
    return false; 
}
/**验证组织机构代码
 *  
 * 计算公式：http://baike.baidu.com/link?url=41IJT9MfgD683PRqUCAH6j8f4iJ195dOtzqqpnaX9w-DLt3hUSOgD8Diiq97tjjAoo_lFOULF0UsnV05LH9aLa
 * C9=11-MOD（∑Ci(i=1→8)×Wi,11）
 * 式中： MOD——代表求余函数；
 *i——代表代码字符从左至右位置序号；
 *Ci——代表第i位上的代码字符的值（具体代码字符见附表）；
 *C9——代表校验码；当C9的值为10时，校验码应用大写的拉丁字母X表示；当C9的值为11时校验码用0表示。
 *Wi——代表第i位上的加权因子
 */
function checkOrgCode(code) {
    //机构代码
    var ws = [3, 7, 9, 10, 5, 8, 4, 2];//加权因子
    var str = '0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ';
    var reg = /^([0-9A-Z]){8}-[0-9|X]$/;// /^[A-Za-z0-9]{8}-[A-Za-z0-9]{1}$/
    var sum = 0;
    //∑Ci(i=1→8)×Wi
    for (var i = 0; i < 8; i++){
        sum += str.indexOf(code.charAt(i)) * ws[i];
    }
    var c9 = 11 - (sum % 11);
    var checkCode='';//第9位校验码
    switch (c9) {
	case 10:
		checkCode='X';
		break;
	case 11:
		checkCode='0';
		break;
	default:
		checkCode=c9+'';
		break;
	}
    return reg.test(code) && checkCode == code.charAt(9);
}

/**验证军官证*/
function checkAICard(value){
    var re= /^[0-9]{8}$/;
    return re.test(value);
  }

/**验证护照*/
function checkPassport(number){
	var str=number;
	var Expression=/(P\d{7})|(G\d{8})/;
	var objExp=new RegExp(Expression);
	if(objExp.test(str)==true){
	   return true;
	}else{
	   return false;
	} 
};
function changePermanentAddress(obj, locate){
	var value = $(obj).val();
	$("#permanentAddress").val("");
	var address = "";
	
	var nation = $("#nation option:selected").val()!=""?$("#nation option:selected").text():"";
	var province =  $("#province option:selected").val()!=""?$("#province option:selected").text():"";
	var city = $("#city option:selected").val()!=""?$("#city option:selected").text():"";
	var county = $("#county option:selected").val()!=""?$("#county option:selected").text():"";
	
	switch (locate) {
	case 1:{
		address += nation;
		break;
	}
	case 2:{
		address += nation + province;
		break;
	}
	case 3:{
		address += nation + province + city;
		break;
	}
	case 4:{
		address += nation + province + city + county;
		break;
	}
	default:
		break;
	}
		
	address = (address).replace('　',"");
	$("#permanentAddress").val(address);
}