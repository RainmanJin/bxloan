define(function(require, exports, module) {
	var rm = {
		rules: {
			checkType:{
				required:true
			},
			checkName:{
				required:function(){
					if($("select[name='checkType']").val()=="2"
						&& $("input[name='checkName']").val()==null){
						return false;
					}else{
						return true;
					}
				}
			},
			isPhone:{
				required:true
			},
			isIncomeSource:{
				required:true
			}, 
			isApplyMoney:{
				required:true
			},
			isApplyTerm:{
				required:true
			},
			isRate:{
				required:true
			},
			isBlameKnow:{
				required:true
			},
			isBlameClear:{
				required:true
			},
			time:{
				required:true
			},
			approvalUserName:{
				required:true
			}
		}
	}
	module.exports = rm;
})