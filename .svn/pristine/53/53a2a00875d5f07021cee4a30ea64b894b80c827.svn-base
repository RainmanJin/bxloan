/**
 * 财务表单的校验规则
 * 
 * author: lijing
 * lastModified: lijing 2014-08-07 16:30:24
 */

define(function(require, exports, module) {
    var rmcw = {
        rules: {
            accountName: {
                //户名
                stringCheck: true,
                required: true,
                maxlength:15
            },
            bankAccount: {
                //开户行
                stringCheck: true,
                required: true,
                maxlength:10
            },
            accountNum: {
                //账号
                isBankNum: true,
                required: true
            },
            accountStatus: {
                //使用状态
                required: true
            },
            accountPhone: {
                //预留手机
                isMobile: true
            }
        },
        messages: {
            accountName: {
                //户名
                required: "此项必填"
            },
            bankAccount: {
                //开户行
                required: "此项必填"
            },
            accountNum: {
                //账号
                required: "此项必填"
            },
            accountStatus: {
                //使用状态
                required: "此项必填"
            }
        }
    };
    module.exports = rmcw;
});