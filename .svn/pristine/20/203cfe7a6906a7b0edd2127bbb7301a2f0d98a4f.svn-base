define(function(require, exports, module) {
    var rm = {
		rules: {
			personType:{
				required:true
			},
			name:{
				required:true,
				maxlength:15
		    },
		    birthDate:{
		    	required:true
		    },
		    certificateCd:{
		    	required:true,
                isIDcard: "#add_person_certificateTypeCd",
                isOrgCode: "#add_person_certificateTypeCd",
                isAICard: "#add_person_certificateTypeCd",
                isPassport: "#add_person_certificateTypeCd"
		    },
		    contactTelNum:{
		    	isPhone:true
		    },
		    familyTelNum:{
		    	isPhone:true
		    },
		    residenceArea:{
		    	isIntPositive :true,
		    	maxlength:9
		    },
		    mailQq:{
		    	isQqOrEmail:true,
		    	maxlength:65
		    },
		    familyAddress:{
		    	maxlength:65
		    },
		    permanentAddress:{
		    	maxlength:65
		    },
		    investmentTypeCd:{
		    	required:true
		    },
		    residencePosition:{
		    	maxlength:65
		    },
		    residencePrimaryPrice:{
		    	isPositiveNumberTwo:true,
		    	maxlength:10
		    },
		    residenceNowPrice:{
		    	isPositiveNumberTwo:true,
		    	maxlength:10
		    },
		    investmentAmt:{
		    	required:true,
		    	isPositiveNumberSix : true,
		    	maxlength:13
		    },
		    stockProportion:{
		    	required:true,
		    	isPercentNumberTwo : true
		    },
		    workCompany:{
		    	maxlength:30
		    },
		    workCompanyTel : {
		    	isPhone:true,
		    	maxlength:30
		    },
		    companyAddress:{
		    	maxlength:65
		    },
		    incomeYear : {
		    	isPositiveNumberTwo :true,
		    	maxlength:16
		    },
		    incomeBonusYear : {
		    	isPositiveNumberTwo :true,
		    	maxlength:16
		    },
		    incomeMonthFact : {
		    	isPositiveNumberTwo :true,
		    	maxlength:16
		    },
		    familyTotalAsset : {
		    	isPositiveNumberTwo :true,
		    	maxlength:16
		    },
		    incomeMonthFact : {
		    	isPositiveNumberTwo :true,
		    	maxlength:16
		    },
		    familyTotalOwes : {
		    	isPositiveNumberTwo :true,
		    	maxlength:16
		    },
		    familyMonthlyTotalAmt : {
		    	isPositiveNumberTwo :true,
		    	maxlength:16
		    },
		    familyMonthlyTotalPayout : {
		    	isPositiveNumberTwo :true,
		    	maxlength:16
		    },
		    creditRemark:{
		    	maxlength:600
		    }
		    
		},
		messages: {
		}
    };
    module.exports = rm;
});