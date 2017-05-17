/**
 * formCustomer页面中
 * 格式控制的相关方法
 * 
 * author: lijing
 * lastModified: lijing 2014-08-08 16:30:24
 */



function formatBankNo(BankNo) {
    if (BankNo.value == "") {
        return;
    }
    var account = new String(BankNo.value);
    account = account.substring(0, 22);
    /*帐号的总数, 包括空格在内 */
    if (account.match(".[0-9]{4}-[0-9]{4}-[0-9]{4}-[0-9]{7}") == null) {
        /* 对照格式 */
        if (account.match(".[0-9]{4}-[0-9]{4}-[0-9]{4}-[0-9]{7}|" + ".[0-9]{4}-[0-9]{4}-[0-9]{4}-[0-9]{7}|" + ".[0-9]{4}-[0-9]{4}-[0-9]{4}-[0-9]{7}|" + ".[0-9]{4}-[0-9]{4}-[0-9]{4}-[0-9]{7}") == null) {
            var accountNumeric = accountChar = "",
            i;
            for (i = 0; i < account.length; i++) {
                accountChar = account.substr(i, 1);
                if (!isNaN(accountChar) && (accountChar != " ")) accountNumeric = accountNumeric + accountChar;
            }
            account = "";
            for (i = 0; i < accountNumeric.length; i++) {
                if (i == 4) account = account + " ";
                /* 帐号第四位数后加空格 */
                if (i == 8) account = account + " ";
                /* 帐号第八位数后加空格 */
                if (i == 12) account = account + " ";
                /* 帐号第十二位后数后加空格 */
                account = account + accountNumeric.substr(i, 1)
            }
        }
    } else {
        account = " " + account.substring(1, 5) + " " + account.substring(6, 10) + " " + account.substring(14, 18) + "-" + account.substring(18, 25);
    }
    if (account != BankNo.value) BankNo.value = account;
}


/**
 * 将联系人表单中下拉框的值放入隐藏域

 * param: select 下拉框对象
 * author: lijing
 * lastModified: lijing 2014-08-08 16:55:13
 */
function putValueInHidden(select) {
	$("#hiddenForFamilyCertificateType").val($(select).val());
}

/**
 * 处理两个输入域的关联-房产情况

 * param: obj 下拉框对象
 * author: lijing
 * lastModified: lijing 2014-08-08 16:55:13
 */
function houseConditionChange(obj) {
	if(obj.value!=""){
		$("#houseCondition").val($(obj).val());
	}
    if (obj.value == "2") {
    	setHouseInfoUnusable();
		setHouseFinaceUnusable();
    } else if (obj.value == "1") {
    	setHouseInfoUsable();
		setHouseFinaceUsable();
    } else {
        return false;
    }
}
function changeHouseCondition(val) {
	$("#localHouseCondition").val($("#houseCondition").val());
	//$("#localHouseCondition").change();
	if(val == 2 || $("#houseCondition").val() == 2){
		setHouseInfoUnusable();
		setHouseFinaceUnusable();
	}else if (val == 1 || $("#houseCondition").val() == 1){
		setHouseInfoUsable();
		setHouseFinaceUsable();
	}else {
        return false;
    }
}
function setHouseInfoUsable() {
	$("#position").removeAttr("disabled");
	$("input[name='houseShareCondition']").removeAttr("disabled");
	$("#houseShareNum").removeAttr("disabled");
	$.post($$ctx + "singleCustomer/findOneCustomer",{customerId: $("#partyIdField").val()}).success(function(r) {
		if (r != null) {
			$("#inhabitancyStatus").val(r["inhabitancyStatus"]);
			$("#familyAddress").val(r["familyAddress"]);
			$("#position").val(r["position"]);
			$("#input[name='houseShareCondition'][value="+r["houseShareCondition"]+"]").attr("checked","checked");
			$("#houseShareNum").val(r["houseShareNum"]);
        } 
	}).error(function(){});
}
function setHouseInfoUnusable() {
	$("#position").val("");
	$("#position").attr("disabled","disabled");
	$("input[name='houseShareCondition']").attr("checked",false);
	$("input[name='houseShareCondition']").attr("disabled","disabled");
	$("#houseShareNum").val("");
	$("#houseShareNum").attr("disabled","disabled");
}
function setHouseFinaceUsable() {
	$("#localHouseNum").removeAttr("disabled");
    $("#houseesPrice").removeAttr("disabled");
    $("#houseesLoan").removeAttr("disabled");
    $.post($$ctx + "singleCustomer/findOneFinace",{customerId: $("#partyIdField").val()}).success(function(r) {
		if (r != null) {
			$("#localHouseNum").val(r["localHouseNum"]);
			$("#houseesPrice").val(r["houseesPrice"]);
			$("#houseesLoan").val(r["houseesLoan"]);
        } 
	}).error(function(){});
}
function setHouseFinaceUnusable() {
	$("#localHouseNum").attr("disabled", "disabled");
    $("#localHouseNum").val("");
    $("#houseesPrice").attr("disabled", "disabled");
    $("#houseesPrice").val("");
    $("#houseesLoan").attr("disabled", "disabled");
    $("#houseesLoan").val("");
    
  //当本地房产情况选择否时，清除以下元素的错误提示
    $('#houseesPrice,#localHouseNum,#houseesLoan').closest('div.form-group').removeClass('has-error');
    $('#houseesPrice,#localHouseNum,#houseesLoan').closest('span.block').next().remove();
}
/**
 * 处理两个输入域的关联-车产情况

 * param: obj 下拉框对象
 * author: lijing
 * lastModified: lijing 2014-08-08 16:55:13
 */
function carConditionChange(obj) {
	if(obj.value == "2"){
		$("#carNum").attr("readonly","readonly");
		$("#carNum").val("");
	} else if (obj.value == "1"){
		$("#carNum").removeAttr("readonly");
	}else{
		return false;
	}
}

/**修改familyfriendType触发
 * */
function changeFamilyFriendType(obj){
	var type = $(obj).val();
	$("#hiddenForFamilyFriendType").val(type);
	if(type==="1"){
		$("font[name='nowLiveAddressFont']").show();
	}else{
		$("font[name='nowLiveAddressFont']").hide();
	}
	
}
var _houseShareNum = $("#houseShareNum").val();
function changeHouseShareCondition(obj) {
	var flag = $("input[name='houseShareCondition']:checked").val();
	if(flag==2){
		$("#houseShareNum_span").hide();
		$("#houseShareNum").prop("readonly",true);
		$("#houseShareNum").val('');
	}else{
		$("#houseShareNum_span").show();
		$("#houseShareNum").removeAttr("readonly");
		$("#houseShareNum").val(_houseShareNum);
	}
}
/**
 * 处理两个输入域的关联-雇佣类型

 * param: obj 下拉框对象
 * author: lijing
 * lastModified: lijing 2014-08-08 16:55:13
 */
/*function changeEmploymentType(obj) {
	if(obj.value == "1"){
		$("#companyFoundYear").attr("readonly","readonly");
		$("#localWorkYear").removeAttr("readonly")
		$("#currCompanyWorkYears").removeAttr("readonly");
		$("input[name='paySocialSecurityInd']").removeAttr("disabled");
		$("input[name='payFundInd']").removeAttr("disabled");
		$("input[name='positiveInd']").removeAttr("disabled");
		
	} else if (obj.value == "2"||obj.value == "3"){
		$("#companyFoundYear").removeAttr("readonly");
		$("#localWorkYear").attr("readonly","readonly");
		$("#currCompanyWorkYears").attr("readonly","readonly");
		$("input[name='paySocialSecurityInd']").attr("disabled","disabled");
		$("input[name='payFundInd']").attr("disabled","disabled");
		$("input[name='positiveInd']").attr("disabled","disabled");
	}else{
		return false;
	}
}*/


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
		break;
	}
	case 2:{
		address +=  province;
		break;
	}
	case 3:{
		address +=  province + city;
		break;
	}
	case 4:{
		address +=  province + city + county;
		break;
	}
	default:
		break;
	}
		
	address = (address).replace('　',"");
	$("#permanentAddress").val(address);
}