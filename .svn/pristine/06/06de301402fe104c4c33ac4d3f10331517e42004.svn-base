/**
 * 联系人信息表单的校验规则
 * 
 * author: lijing
 * lastModified: lijing 2014-08-07 16:30:24
 */

define(function(require, exports, module) {
    var rmlxr = {
        jy_rules: {
            name: {
                //姓名
                stringCheck: true,
                required: true,
                maxlength: 10
            },
            familyMembers:{
            	//是否家庭成员
            	required: true
            },
            familyFriendType: {
                //与借款人关系
                required: true
            },
            receiveEducation: {
                //教育程度
                required: true
            },
            certificateTypeCd: {
                //证件类型
                required: true
            },
            certificateCd: {
                //证件号码
                isIDcardFamily: "#hiddenForFamilyCertificateType",
                isOrgCode: "#hiddenForFamilyCertificateType",
                isAICard: "#hiddenForFamilyCertificateType",
                isPassport: "#hiddenForFamilyCertificateType",
                required: true
            },
            censusRegister: {
                //户籍所在地
            	specialStringCheck: true,
                required: true,
                maxlength: 50
            },
            telphone: {
            	isPhone: true,
                required: true
            },
            mobileTel: {
                isPhone: true
            },
            companyTel: {
                isPhone: true
            },
            school: {
                //学校名
                stringCheck: true,
                maxlength: 12
            },
            schoolAddress: {
                stringCheck: true,
                maxlength: 30
            },
           /* workCompany: {
                stringCheck: true,
                maxlength: 30,
            },*/
            companyAddress: {
                stringCheck: true,
                maxlength: 30
            },
            metier: {
                stringCheck: true,
                maxlength: 30
            },
            nowLiveAddress: {
                //现居住地
                stringCheck: true,
                required: true,
                maxlength: 30
            },
            cultureArea: {
                //是否缴纳社保
                required: true
            },
            familyHyzcfzRatio: {
                //是否缴纳公积金
                required: true
            }
        },
        nh_rules: {
            name: {
                //姓名
                stringCheck: true,
                required: true,
                maxlength: 10
            },
            familyMembers:{
            	//是否是家庭成员
                required: true
            },
            familyFriendType: {
                //与借款人关系
                required: true
            },
            receiveEducation: {
                //教育程度
                required: true
            },
            certificateTypeCd: {
                //证件类型
                required: true
            },
            certificateCd: {
                //证件号码
                isIDcardFamily: "#hiddenForFamilyCertificateType",
                isOrgCode: "#hiddenForFamilyCertificateType",
                isAICard: "#hiddenForFamilyCertificateType",
                isPassport: "#hiddenForFamilyCertificateType",
                required: true
            },
            censusRegister: {
                //户籍所在地
            	specialStringCheck: true,
                required: true,
                maxlength: 50
            },
            telphone: {
            	isPhone: true,
                required: true
            },
            mobileTel: {
                isPhone: true
            },
            companyTel: {
                isPhone: true
            },
            school: {
                //学校名
                stringCheck: true,
                maxlength: 12
            },
            schoolAddress: {
                stringCheck: true,
                maxlength: 30
            },
            /*workCompany: {
                stringCheck: true,
                maxlength: 30,
                required:true
            },*/
            companyAddress: {
                stringCheck: true,
                maxlength: 30
            },
            metier: {
                stringCheck: true,
                maxlength: 30
            },
            nowLiveAddress: {
                //现居住地
                stringCheck: true,
                required: true,
                maxlength: 30
            },
            cultureArea: {
                //是否缴纳社保
                required: true
            },
            familyHyzcfzRatio: {
                //是否缴纳公积金
                required: true
            },
            /*age: {
				required: true,
				isAge: true
			},*/
			personStatus: {
				required: true
			},
			/*monthlyIncome: {
				required: true,
				isPositiveNumberTwo: true
			},*/
			degreeCd: {
				required: true
			}
        },
        messages: {
            name: {
                //姓名
                required: "该项必填"
            },
            familyFriendType: {
                //与借款人关系
                required: "该项必填"
            },
            certificateTypeCd: {
                //证件类型
                required: "该项必选"
            },
            certificateCd: {
                //证件号码
                required: "该项必填"
            },
            censusRegister: {
                //户籍所在地
                required: "该项必填",
                noEnglishStringCheck: "填写具体地址不能包含非法字符和字母"
            },
            telphone: {
                //手机1
                required: "该项必填"
            },
            nowLiveAddress: {
                //现居住地
                required: "该项必填"
            },
            cultureArea: {
                //是否缴纳社保
                required: "该项必选"
            },
            familyHyzcfzRatio: {
                //是否缴纳公积金
                required: "该项必选"
            }
        }
    };
    module.exports = rmlxr;
});