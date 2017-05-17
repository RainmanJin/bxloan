define(function(require, exports, module) {
	var rm_breed = {
			passRules: {
				workingYears:{
					isFloatOneBit: true,
					required : true
				},
				breedType:{
					required : true
				},
				breedContent:{
					stringCheck: true,
					required : true
				},
				breedMode:{
					required : true
				},
				breedScale:{
					isPositiveNumberTwo: true,
					required : true
				},
				saleNum:{
					required : true
				},
				salePrice:{
					isPositiveNumberTwo: true,
					required : true
				},
				salePriceUnit:{
					required : true
				},
				saleIncomeTotal:{
					isPositiveNumberTwo: true,
					required : true
				},
				costTotal:{
					isPositiveNumberTwo: true,
					required : true
				},
				singleProduceUnit:{
					required : true
				},
				highest:{
					isPositiveNumberTwo: true,
					required : true
				},
				lowest:{
					isPositiveNumberTwo: true,
					required : true
				},
				lastyear:{
					isPositiveNumberTwo: true,
					required : true
				},
				predict:{
					isPositiveNumberTwo: true,
					required : true
				},
				stockInitScale:{
					isPositiveNumberTwo: true,
					required : true
				},
				stockInitScaleUnit:{
					required : true
				},
				breedStockValue:{
					isPositiveNumberTwo: true,
					required : true
				},
				forageValue:{
					isPositiveNumberTwo: true,
					required : true
				}
	        },
	        futureRules:{
	        	workingYears:{
					isFloatOneBit: true,
					required : true
				},
				breedType:{
					required : true
				},
				breedContent:{
					stringCheck: true,
					required : true
				},
				breedMode:{
					required : true
				},
				breedScale:{
					isPositiveNumberTwo: true,
					required : true
				},
				saleNum:{
					required : true
				},
				salePrice:{
					isPositiveNumberTwo: true,
					required : true
				},
				salePriceUnit:{
					required : true
				},
				saleIncomeTotal:{
					isPositiveNumberTwo: true,
					required : true
				},
				costTotal:{
					isPositiveNumberTwo: true,
					required : true
				},
				singleProduceUnit:{
					required : true
				},
				predictProduceTotal:{
					isPositiveNumberTwo: true,
					required : true
				},
				predictProduceTotalUnit:{
					required : true
				},
				predictSaleTime:{
					//range:[1860,9999],
					//digits:true,
					required : true
				},
				predict:{
					isPositiveNumberTwo: true,
					required : true
				},
				stockInitScale:{
					isPositiveNumberTwo: true,
					required : true
				},
				stockInitScaleUnit:{
					required : true
				},
				breedStockValue:{
					isPositiveNumberTwo: true,
					required : true
				},
				forageValue:{
					isPositiveNumberTwo: true,
					required : true
				}
	        },
	        messages: {
	        	
	        }
	};
	module.exports = rm_breed;
});