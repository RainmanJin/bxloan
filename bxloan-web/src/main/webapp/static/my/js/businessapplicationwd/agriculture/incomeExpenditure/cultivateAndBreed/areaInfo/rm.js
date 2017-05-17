define(function(require, exports, module) {
	var rm = {
			rules : {
				location:{
					required:true
				},
				area:{
					required:true,
					isNotNegative:true
				},
				unit:{
					required:true
				},
				rent:{
					required:function(){
						if($("select[name='areaProperty']").val()=="1"){
							return false;
						}else{
							return true;
						}
					}
				}
			}
	}
	module.exports = rm;
});