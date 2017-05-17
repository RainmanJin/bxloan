define(function(require, exports, module) {
	var validObj = {
			rules : {
				location:{
					required:true,
					maxlength:200
				},
				area:{
					required:true,
					isNotNegative:true
				},
				areaProperty:{
					required:true
				},
				willProduceFuture:{
					required:true
				},
				produceContent:{
					required:true,
					rangelength:[0,1000]
				},
				cultivateBreedType:{
				},
				equipmentSituation:{
					rangelength:[0,100]
				},
				rent:{
					required:function(){
						var $form_produceArea=$("#form-productionAreaOfBizFa");
						return $form_produceArea.find('select[name="areaProperty"]').val()==3;
					},
					isNotNegative:true
				},
				tenancy:{
					required:function(){
						var $form_produceArea=$("#form-productionAreaOfBizFa");
						return $form_produceArea.find('select[name="areaProperty"]').val()==3;
					},
					digits:true,
					range:[1,100]
				},
				rentDateStr:{
					required:function(){
						var $form_produceArea=$("#form-productionAreaOfBizFa");
						return $form_produceArea.find('select[name="areaProperty"]').val()==3;
					}
				},
				haveLease:{
					required:function(){
						var $form_produceArea=$("#form-productionAreaOfBizFa");
						return $form_produceArea.find('select[name="areaProperty"]').val()==3;
					}
				}
			},
			messages:{
				rent:{
					isNotNegative:"请输入金额，如：100,200.00"
				}
			}
	}
	module.exports = validObj;
});