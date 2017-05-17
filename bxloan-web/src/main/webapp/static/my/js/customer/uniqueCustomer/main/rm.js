/**
 * singleCustomer页面新增客户表单的校验规则
 * 
 * author: lijing
 * lastModified: lijing 2014-08-02 16:30:24
 */


define(function(require, exports, module) {
    var rm = {
        rules: {
            customerName: {
                //姓名
                required: true,
                maxlength:12,
                nameStringCheck: true
            },
            certificateNum: {
                //证件号
                required: true,
                isIDcard: "#addCertificateType",
                isOrgCode: "#addCertificateType",
                isAICard: "#addCertificateType",
                isPassport: "#addCertificateType"
            },
            certificateType: {
                //证件类型
                required: true
            }
        },
        messages: {
            customerName: {
                //姓名
                required: "该项必填"
            },
            certificateNum: {
                //证件号
                required: "该项必填"
            },
            certificateType: {
                //证件类型
                required: "该项必填"
            }
        }
    };
    module.exports = rm;
});