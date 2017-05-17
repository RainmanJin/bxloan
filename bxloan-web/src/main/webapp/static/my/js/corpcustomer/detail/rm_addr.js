define(function(require, exports, module) {
    var rmAddr = {
		rules: {
			addressTypeCd: {
				required:true
		    },
		    streetAddress: {
		    	required:true
		    },
		    telephone: {
		    	required:true,
		    	isTel:true
		    },
		    zipNum:{
		    	required:false,
		    	isZipCode:true
		    },
		    fax:{
		    	required:false,
		    	isFax:true
		    },
		    website:{
		    	required:false,
		    	url:true
		    }
		},
		messages: {
			addressTypeCd: {
		        required: "该项必填"
		    },
		    streetAddress: {
		        required: "该项必填"
		    },
		    telephone: {
		        required: "该项必填"
		    }
		}
    };
    module.exports = rmAddr;
});