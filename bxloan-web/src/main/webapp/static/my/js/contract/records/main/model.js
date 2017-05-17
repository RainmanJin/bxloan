define(function(require, exports, module) {
    var model = Backbone.Model.extend({
        initialize: function() {
            // do nothing
        },
        checkContractStatus: function(data, callback) {
            $.post($$ctx + "contractList/checkContractStatus", data).success(function(r) {
                callback(r);
            }).error(function() {
                bootbox.alert("checkContractStatus失败请稍后重试");
            })
        },
        changeIsUpload: function(data, callback) {
            $.ajax({
                cache: true,
                type: "POST",
                url: $$ctx + "contractList/changeIsUpload",
                data: {
                    "payLoanId": data
                },
                async: false,
                error: function(request) {
                    alert("查找下载路径出错" + request);
                },
                success: function(data) {
                    callback(data);
                }
            });
        }
    });
    module.exports = model;
});