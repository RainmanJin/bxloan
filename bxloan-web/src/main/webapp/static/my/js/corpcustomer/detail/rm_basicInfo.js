define(function(require, exports, module) {
    var rm = {
		rules: {
			markType: {
				required:$('#consultLocation').val() == 'businessAdd' ? false : true
		    },
		    customerSource: {
				required:true
		    },
			customerName: {
		        //姓名
				required:true,
				maxlength:50,
				nameStringCheck: true,
				isCorpCustomer: "#customerName"
		    },
		    industryLevelTwoCd:{
		    	required:true
		    },
		    businessLicenseNum:{
		    	required:true,
		    	maxlength:20
		    },
		    industryCdMask:{
		    	required:true
		    },
		    registeredCapital:{
		    	required:true,
		    	isPositiveNumberTwo:"#basic_registeredCapital",
		    	maxlength:16
		    },
		    actualRevAmt:{
		    	required:true,
		    	isPositiveNumberTwo:"#basic_actualRevAmt",
		    	maxlength:16
		    },
		    standedDate:{
		    	required:true
		    },
		    linkmanName:{
		    	required:true,
		    	stringCheck:true,
		    	maxlength:40
		    },
		    linkmanTel:{
		    	required:true,
		    	isPhone : "#basic_linkmanTel"
		    },
		    staffNum:{
		    	isIntPositive : "#basic_staffNum",
		    	maxlength:6
		    },
		    nationalTaxRegistrationNum:{
		    	notBothBlank : "#basic_localTaxRegistrationCum",
		    	maxlength:20,
		    	isTaxRegNum:true
		    },
		    localTaxRegistrationCum:{
		    	notBothBlank : "#basic_nationalTaxRegistrationNum",
		    	maxlength:20,
		    	isTaxRegNum:true
		    },
		    settlementAccountsOne:{
		    	maxlength:50
		    },
		    settlementAccountsTwo:{
		    	maxlength:50
		    },
		    settlementAccountsFirst:{
		    	required:false,
		    	isBankNum:true
		    },
		    settlementAccountsSecond:{
		    	required:false,
		    	isBankNum:true
		    },
		    loanCardNum:{
		    	isBankNum:true
		    }
		},
		messages: {
			 nationalTaxRegistrationNum:{
			    	notBothBlank : "国税登记号与地税登记号不能同时为空"
		    },
		    localTaxRegistrationCum:{
		    	notBothBlank : "国税登记号与地税登记号不能同时为空"
		    }
		}
    };
    module.exports = rm;
});