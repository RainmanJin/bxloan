/**
 * 概况工作信息表单的校验规则
 * */
define(function(require, exports, module) {
    var rmgk = {
        jy_rules: {
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
            genderCd: {
                required: true
            },
            degreeCd: {
                required: true
            },
            customerSource: {
            	//客户来源
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
                maxlength:30
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
            marriageCd: {
                required: true
            },
            censusType: {
                required: true
            },
            permanentAddress: {
                //户籍地
            	required: true,
            	specialStringCheck: true,
                maxlength:60
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
                maxlength:60
            },
            companyTel: {
                //单位电话
                isPhone: true
            },
            microAccount: {
            	//微信
            	isMicro: true
            }
        },
        nh_rules: {
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
            customerSource: {
            	//客户来源
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
                maxlength:30
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
            censusType: {
                required: true
            },
            permanentAddress: {
                //户籍地
            	required: true,
            	specialStringCheck: true,
                maxlength:60
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
                maxlength:60
            },
            companyTel: {
                //单位电话
                isPhone: true
            },
            microAccount: {
            	//微信
            	isMicro: true
            },
            age: {
				required: true,
				isAge: true
			},
			genderCd: {
				required: true
			},
			marriageCd: {
				required: true
			},
			industryCdMask: {
				required: true
			},
			industryCd: { 
				required: true
			},
			yearsInIndustry: {
				isPositiveNumberTwo: true,
				required: true
			},
			localInhabitancyYears: {
				isFloatOneBit: true,
				required: true
			},
			agroPopulation: {
				isFloatOneBit: true,
				required: true
			},
			agroBizType: {
				required: true
			},
			inhabitancyStatus: {
				required: true
			},
			livingArea: {
				required: true,
				isIntThreeFloatOneBit: true
			},
			appliancesCase: {
				required: true
			},
			spouseCaseShow: {
				required: true
			},
			spouseEmploymentShow: {
				required: true
			},
			familySize: {
				required: true,
				digits: true,
				range:[1,100]
			},
			workForce: {
				required: true,
				digits: true,
				range: function(){
					var familyNum = $("#form-gkxx").find("input[name='familySize']").val()
					var i = 100;
					if(familyNum){
						i = parseInt(familyNum);
					}
					return [0,i];
				}
			},
			dependentPopulation: {
				required: true,
				range: function(){
					var familyNum = $("#form-gkxx").find("input[name='familySize']").val()
					var i = 100;
					if(familyNum){
						i = parseInt(familyNum);
					}
					return [0,i];
				}
			},
			dependentPopulationRate: {
				required: true,
				isIntThreeFloatOneBit: true
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
            	required: "请选择下面的省/自治州/直辖市、市/自治州、区/县，或填写具体地址",
            	stringCheck: "填写具体地址不能包含非法字符和字母"
            },
            familySize:{
        		isPopulation: "请输入1-100的整数"
        	},
        	dependentPopulation:{
        		isPopulation: "请输入0-100的整数"
        	},
        	workForce:{
        		isPopulation: "请输入0-100的整数"
        	}
        }
    };
    module.exports = rmgk;
});