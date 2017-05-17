define(function(require, exports, module) {
    var rm_corp = {
    		//certificateNum
			//customerName
			//businessLicenseNum
        rules: {
        	customerName: {
                //姓名
        		required:true,
        		isCorpCustomer: "#customerName",
        		nameStringCheck: true,
        		maxlength:50
            },
            certificateNum: {
                //证件号
            	required:true,
            	isOrgCodeAndLicence: true
            },
            businessLicenseNum: {
            	isBusinessLicenseNum:true
            }
            ,
            businessLicenseNum: {
            	maxlength:20
            	/*isBusinessLicenseNum:true*/
            }
        },
        messages: {
        	customerName: {
                //姓名
                required: "该项必填"
            },
            certificateNum: {
                //证件号
                required: "该项必填"
            }
        }
    };
    module.exports = rm_corp;
});