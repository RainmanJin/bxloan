/**
 * 概况工作信息表单的校验规则
 * */
define(function(require, exports, module) {
    var rmgk = {
        rules: {
        	markType: {
        		required: true
        	},
            customerName: {
                required: true,
                nameStringCheck: true,
                maxlength: 15
            },
            certificateNum: {
                required: true,
                isIDcard: "#certificateTypeCd",
                isOrgCode: "#certificateTypeCd",
                isAICard: "#certificateTypeCd",
                isPassport: "#certificateTypeCd"
            }, 
            professional: {
                maxlength:10
            },
            channelName: {
                stringCheck: true,
                maxlength:20
            },
            customerSource: {
            	//客户来源
            	required: true
            },
            genderCd: {
                required: true
            },
            degreeCd: {
                required: true
            },
            mobileTel: {
                required: true,
                isMobile: true
                //电话的格式校验
            },
            telphone2: {
                isMobile: true
            },
            email: {
                //email的格式校验
                isEmail: true,
                maxlength:20
            },
            qq: {
                //qq格式校验
            	isQq: true,
            	maxlength:10
            },
            chiledNum: {
                //子女人数必须是整数
                isIntNotNegative: true,
                maxlength:2
              
            },
            employmentType: {
                required: true
            },
            companyFoundYear: {
                //时间校验，自雇成立年限
            	isFloatOneBit: true,
                maxlength:3
            },
            marriageCd: {
                required: true
            },
            socialSecurityCode: {
                //社保卡号
                stringCheck: true,
                maxlength:18
            },
            censusType: {
                required: true
            },
            permanentAddress: {
                //户籍地
            	required: true,
            	specialStringCheck: true,
                maxlength:50
            },
            metier: {
                //职位名称
                stringCheck: true,
                maxlength:10
            },
            houseCondition: {
                required: true
            },
            position: {
                stringCheck: true,
                maxlength:60
            },
            inhabitancyStatus: {
                //居住情况
                required: true,
                maxlength:10
            },
            familyAddress: {
                //居住地址
                required: true,
                stringCheck: true,
                maxlength:30
            },
            houseShareCondition: {
                //是否共有
                required: true
            },
            houseShareNum: {
                //共有人数，整数
                isIntNotNegative: true,
                required: function(){
                	var flag = $("input[name='houseShareCondition']:checked").val();
                	if(flag==2){
                		$("#houseShareNum_font").hide();
                		return false;
                	}
                	$("#houseShareNum_font").show();
                	return true;
                },
                maxlength:2
            },
            workCompany: {
            	required: true, 
                stringCheck: true,
                maxlength:30
            },
            companyType: {
                required: true
            },
            industryCdMask: {
                required: true
            },
            companyAddress: {
                stringCheck: true,
                required: true,
                maxlength:30
            },
            companyTel: {
                //单位电话
                isPhone: true,
                required :true
            },
            companyFax: {
                //单位传真
            	isFax: true
            },
            localWorkYear: {
                //工作年限，整数
            	isFloatOneBit: true,
            	//isIntNotNegative: true,
            	maxlength:4,
            	required: true
            },
            microAccount: {
            	//微信
            	isMicro: true
            },
            currCompanyWorkYears: {
                //工作年限，整数
            	//isIntNotNegative: true,
            	isFloatOneBit: true,
                maxlength:4,
                required: true
            },
            paySocialSecurityInd: {
            	//是否缴纳社保
            	 required: true
            },
            payFundInd: {
            	//是否缴纳公积金
            	required: true
            },
            positiveInd:{
            	//是否转正
            	required: true
            },
            industryCdMask: {
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
            },
            permanentAddress:{
            	required: "请选择下面的省/自治州/直辖市、市/自治州、区/县，或填写具体地址。",
            	stringCheck: "填写具体地址不能包含非法字符和字母"
            }
        }
    };
    module.exports = rmgk;
});