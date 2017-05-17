/**
 * 概况工作信息表单的校验规则
 * */
define(function(require, exports, module) {
    var rmgk = {
        rules: {
        	name: {
                required: true,
                stringCheck: true,
                maxlength: 15
            },
            certificateNum: {
                required: true,
                isIDcard: "#certificateTypeCd",
                isOrgCode: "#certificateTypeCd",
                isAICard: "#certificateTypeCd",
                isPassport: "#certificateTypeCd"
            }, 
            certificateTypeCd: {
            	required: true
            },
            logname: {
                stringCheck: true,
                maxlength:20
            },
            state: {
                required: true
            },
            address: {
               stringCheck: true
            },
            mobilephone: {
                required: true,
                isMobile: true
                //电话的格式校验
            },
            fixedphone: {
                isMobile: true
            },
            email: {
                //email的格式校验
                isEmail: true,
                maxlength:20
            },
            deptNameMask: {
            	required: true
            },
            unitPosition: {
            	required: true
            }
        },
        messages: {
            customerName: {
                required: "此项必填"
            },
            certificateNum: {
                required: "此项必填",
                remote: "请填写证件编号"
            },
            professional: {
                required: "此项必填",
                remote: "请填写职业"
            },
            genderCd: {
                required: "此项必填"
            },
            degreeCd: {
                required: "此项必填"
            },
            mobileTel: {
                required: "此项必填"
                //电话的格式校验
            },
            email: {
                //email的格式校验
                remote: "邮箱已被注册过"
            },
            employmentType: {
                required: "请选择雇佣类型"
            },
            marriageCd: {
                required: "此项必填"
            },
            socialSecurityCode: {
                remote: "请填写此项信息"
                //社会保障号
            },
            censusType: {
                required: "此项必填",
                remote: "请选择此项信息"
            },
            houseCondition: {
                required: "此项必填",
                remote: "请选择此项"
            },
            inhabitancyStatus: {
                //居住情况
                required: "此项必填",
                remote: "请填写此项"
            },
            familyAddress: {
                //居住地址
                required: "此项必填"
            },
            houseShareCondition: {
                //是否共有
                required: "此项必填",
                remote: "请选择此项"
            },
            houseShareNum: {
                //共有人数，整数
                required: "此项必填"
            },
            workCompany: {
                required: "请输入公司名称"
            },
            companyType: {
                required: "此项必填",
                remote: "请选择公司类型"
            },
            industryCdMask: {
                required: "此项必填"
            },
            companyAddress: {
                required: "此项必填"
            },
            microAccount: {
            	isPhone: "输入正确的微信号"
            }
        }
    };
    module.exports = rmgk;
});