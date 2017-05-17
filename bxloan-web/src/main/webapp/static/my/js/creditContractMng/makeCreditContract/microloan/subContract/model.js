define(function(require, exports, module) {
	var model = Backbone.Model.extend({
		initialize: function() {
		},
		/** 下载抵质押从合同 */
		downloadCollaContr: function(id,callback){
			callback($$ctx + "contractFile/downloadCreditContractCollaSubContr/"+id);
		},
		/** 下载质押从合同清单 */
		downloadPledContrList: function(id,callback){
			callback($$ctx + "contractFile/downloadPledContrList/"+id);
		},
		/** 下载保证从合同 */
		downloadGuarContr: function(id,callback){
			callback($$ctx + "contractFile/downloadCreditContractAssureSubContr/"+id);
		},
		/** 验证是否可以下载从合同清单 */
		verifyContrListDownload:function(subContractId,callback){
			var modelSelf=$(this);
			$.post($$ctx + "contractFile/verifyContrListDownload/"+subContractId).success(function(r) {
				callback(r);
			}).error(function(){
				bootbox.alert("查询失败请稍后重试");
			});
		},
		/** 下载相关文档到本地 */
		downloadFile:function(url){
			var gotoLink = document.createElement('a');
			gotoLink.style.display = 'none';
			gotoLink.href = url;
		    document.body.appendChild(gotoLink);
		    document.body.removeChild(gotoLink);
		    gotoLink.click();
		}
	});
	module.exports = model;
});