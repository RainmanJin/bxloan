define(function(require, exports, module) {
	var rm = {
			rules: {
				customerName: {
					 stringCheck: true,
		             required: true
				},
				idCardNum: {
					required: true,
					isIDcard: "input:hidden[name='idCardNum_type']"
				},
				age: {
					required: true,
					isAgroAge: true
				},
				genderCd: {
					required: true
				},
				permanentAddress: {
					stringCheck: true,
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
					range:[1860,9999],
					digits:true,
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
				mobileTel: {
					required: true,
					isPhone: true
				},
				/*agroBizType: {
					required: true
				},*/
				inhabitancyStatus: {
					required: true
				},
				livingArea: {
					required: true,
					isIntThreeFloatOneBit: true
				},
				familyAddress: {
					stringCheck: true,
					required: true
				},
				appliancesCase: {
					required: true
				},
				spouseCase: {
					required: true
				},
				spouseEmployment: {
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
						var familyNum = $("#basicInfoForm_nd").find("input[name='familySize']").val()
						var i = 100;
						if(familyNum){
							i = parseInt(familyNum);
						}
						return [0,i];
					}
				},
				dependentPopulation: {
					required: true,
					digits: true,
					range: function(){
						var familyNum = $("#basicInfoForm_nd").find("input[name='familySize']").val()
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
	module.exports = rm;
});