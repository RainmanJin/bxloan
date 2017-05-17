/**
 * 联保提表单的详细校验规则
 * 
 * author: lijing
 * lastModified: lijing 2014-08-02 16:30:24
 */


define(function(require, exports, module) {
    var rm = {
        rules: {
        	unGuId: {
                //联保体协议编号
                required: true,
            },
            unGroupName: {
                //联保体小组名称
                required: true
            },
            unType:{
            	//联保体类型
                required: true
            },
            guaranWay:{
            	//保证方式
                required: true
            },
            customerQuantity:{
            	//联保体成员数量
                required: true,
                min:2
            },
            begDateStr:{
            	//联保协议起始时间
                required: true
                //compareDate: ["#hiddenParams input[name='nowDate']",1]
            },
            endDateStr:{
            	//联保协议到期时间
                required: true,
                compareDate: ["#form-basicInfo input[name='begDateStr']",1]
            },
            unSigeDateStr:{
            	//联保协议签订日期
            	 required: true
            },
            custManager:{
            	//客户经理
            	required: true
            },
            segiDate:{
            	//登记日期
            }
           
        },
        messages: {
        	unGroupName:{
        		required: "此项必填"
        	},
        	begDateStr:{
        		//compareDate:"起始时间不能晚于今天"
        	},
        	endDateStr:{
        		compareDate:"起始时间不能晚于到期时间"
        	}
        }
    };
    module.exports = rm;
});