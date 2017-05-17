define(function(require, exports, module) {
    var rm = {
		rules: {
			corpType:{
				required:true
			},
			corpName : {
				required:true,
				isCorpCustomer:true,
				maxlength:65
		    },
		    certificateTypeCd: {
		    	required:true
		    },
		    certificateCd : {
		    	required:true,
		    	isOrgCodeAndLicence:true
		    },
		    businessLicenseNum : {
		    	required:true,
		    	maxlength:20
		    },
		    taxRegistrationNumNational:{
		    	notBothBlank : "#add_corp_taxRegistrationNumLocal",
		    	maxlength:20
		    },
		    taxRegistrationNumLocal:{
		    	notBothBlank : "#add_corp_taxRegistrationNumNational",
		    	maxlength:20
		    },
		    actualRevAmt : {
		    	required:true,
		    	isPositiveNumberSix :true,
		    	maxlength:16
		    },
		    registeredCapital : {
		    	required:true,
		    	isPositiveNumberSix:true,
		    	maxlength:16
		    },
		    contactorName : {
		    	required:true,
		    	maxlength:16
		    },
		    contactorTelNum : {
		    	required:true,
		    	isPhone:true
		    },
		    loanCardNum : {
		    	isBankNum : true
		    },
		    corpEstablishDate : {
		    	required:true
		    },
		    addressRegist:{
		    	maxlength:65
		    },
		    addressContact : {
		    	required:true,
		    	maxlength:65
		    },
		    investmentTypeCd : {
		    	required:true
		    },
		    investmentAmt:{
		    	required:true,
		    	isPositiveNumberSix : true,
		    	maxlength:13
		    },
		    stockProportion:{
		    	required:true,
		    	isPercentNumberTwo : true
		    }
		    
		},
		messages: {
			taxRegistrationNumNational:{
		    	notBothBlank : "国税登记号与地税登记号不能同时为空"
		    },
		    taxRegistrationNumLocal:{
		    	notBothBlank : "国税登记号与地税登记号不能同时为空"
		    }
		}
    };
    module.exports = rm;
});