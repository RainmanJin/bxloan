/**
 * modal方法组件
 * 
 * author: lijing
 * lastModified: lijing 2014-08-07 16:30:24
 */

define(function(require, exports, module) {
	var utils = require("utils");
    var modalModule = {
    	path :  $$ctx + 'unitecustomer/',
    	createAliveBtn: function(){
    			var text = ""
    			var status = $("#form-basicInfo").find("input[name='unStatus']").val();
            	if(status && status == "2"){
            		text = "失效";
            	}else{
            		text = "生效";
            	}
            	 $("#showAlive").html(
                         "<button class='btn btn-sm btn-primary' id='changeProtocol' type='button' data-status='"+ status +"'>"
         				+"<i class='ace-icon fa fa-arrow-right'>"
         				+"</i>"
         				+"协议"+ text
         				+"</button>");
    	},
        initCustManagerDatatable: function(){
        	/**
        	 * 初始化客户经理列表
        	 * */
        	 var viewSelf = this;
        	 utils.dd.initDataDict(["UserStatusCode"], function(dataDict) {
             var oTable = $("div[role='custManagerModal'] table").dataTable({
                 "sAjaxSource": viewSelf.path + "findCustManagerBySearch",
                 "bFilter": false,
                 "bAutoWidth": true,
                 "bLengthChange": false,
                 "aoColumns": [
                 {	
                     "mData": "id",
                     mRender: function(data, type, rowdata) {
                    	 var radio = "<input type='radio' name='sel_custManager_id' value="+ data +" />";
                    	 return radio;
                     }
                 },
                 {
                     "mData": "custManagerName"
                 },
                 {
                     "mData": "orgName",
                     mRender: function(data, type, rowdata) {
                    	 return data + "<input type='hidden' value='"+ rowdata.orgId +"' >";
                     }
                 },
                 {
                     "mData": "states",
                     mRender: function(data, type, rowdata) {
                    	 if(data){
                    		 return dataDict.UserStatusCode[data];
                    	 }else{
                    		 return "数据错误";
                    	 }
                     }
                 }
                ],
                 "fnServerParams": function(aoData) {
                     aoData.push({
                         "name": "name",
                         "value": $("#custManagerForm").find("input[name='custManager']").val()
                     });
                 }
             });
             viewSelf.oTable = oTable;
        	});
        },
        initCustFormBtn : function(){
        	var viewSelf=this;
        	$(document).on("click", "#custManagerForm button[role='search']",function(e){
        		viewSelf.oTable.fnSettings()._iDisplayStart = 0;
        		viewSelf.oTable.fnDraw();
        	});
        	$(document).on("click", "#custManagerForm button[role='reset']",function(e){
        		$("#custManagerForm").resetForm();
        	});
        	$(document).on("click", "div[role='custManagerModal'] button[role='sure']",function(e){
        		var $selrs = $("input[name='sel_custManager_id']");
        		if($selrs.val()){
        			var $form = $("#form-basicInfo");
        			var name = $("input[name='sel_custManager_id']:checked").closest("tr").find("td:eq(1)").text();
        			var value = $("input[name='sel_custManager_id']:checked").val();
        			$form.find("input[name='custManager']").val(name);
        			$form.find("input[name='managerId']").val(value);
        			$("div[role='custManagerModal']").modal("hide");
        		}
        	});
        },
        initCustomersDatatables: function(){
        	/**
        	 * 初始化客户经理列表
        	 * */
        	 var viewSelf = this;
        	 utils.dd.initDataDict(["CustomerType","CertificateType","CustomerStatusCode"], function(dataDict) {
             var customerTable = $('table[role="tb_customer"]').dataTable({
                 "sAjaxSource": viewSelf.path + "findCustomersBySearch",
                 "bFilter": false,
                 "bAutoWidth": true,
                 "bLengthChange": false,
                 "aoColumns": [
                 {	
                     "mData": "partyId",
                     mRender: function(data, type, rowdata) {
                    	 var radio = "<input type='checkbox' name='sel_cust_id' value="+ data +" />";
                    	 return radio;
                     }
                 },
                 {
                     "mData": "customerNum"
                 },
                 {
                     "mData": "customerName"
                 },
                 {
                     "mData": "partyTypeCd",
                     mRender: function(data, type, rowdata) {
                    	 if(data){
                    		 return dataDict.CustomerType[data];
                    	 }
                     }
                 },
                 {
                     "mData": "certificateTypeCd",
                     mRender: function(data, type, rowdata) {
                    	 if(data){
                    		 return dataDict.CertificateType[data];
                    	 }else{
                    		 return "-";
                    	 }
                     }
                 },
                 {
                     "mData": "certificateNum"
                 },
                 {
                     "mData": "status",
                     mRender: function(data, type, rowdata) {
                    	 if(data){
                    		 return dataDict.CustomerStatusCode[data];
                    	 }else{
                    		 return "-";
                    	 }
                     }
                 },
                 {
                	 "mData": "custManger" 
                 }
                ],
                 "fnServerParams": function(aoData) {
                	 //联保体id
                	 aoData.push({
                		 "name": "ugnId",
                		 "value": $("#ugnId").val()
                	 });
                	 aoData.push({
                		 "name": "unGuId",
                		 "value": $("#unGuId").val()
                	 });
                     aoData.push({
                         "name": "customerName",
                         "value": $("#customerSearchForm").find("input[name='customerName']").val()
                     });
                     aoData.push({
                         "name": "certificateCd",
                         "value": $("#customerSearchForm").find("select[name='certificateCd']").val()
                     });
                     aoData.push({
                         "name": "certificateNum",
                         "value": $("#customerSearchForm").find("input[name='certificateNum']").val()
                     });
                     aoData.push({
                         "name": "queryType",
                         "value": "0"
                     });
                 }
             });
             viewSelf.customerTable = customerTable;
        	});
        },
        initCustomerFormBtn : function(){
        	var viewSelf=this;
        	$(document).on("click", "#customerSearchForm button[role='search']",function(e){
        		viewSelf.customerTable.fnSettings()._iDisplayStart = 0;
        		viewSelf.customerTable.fnDraw();
        	});
        	$(document).on("click", "#customerSearchForm button[role='reset']",function(e){
        		$("#customerSearchForm").resetForm();
        	});
        	$(document).on("click", "div[role='customerModal'] button[role='sure']",function(e){
        		e.stopPropagation();
        		var custIds =[];//所选客户id
        		$.each($(":input[name='sel_cust_id']:checked"),function(i,v){
        			custIds.push($(v).val());
        		});
        		
        		/*if($selrs.val()){
        			var $modal = $("#widget-member");
        			var num = $("input[name='sel_cust_id']:checked").closest("tr").find("td:eq(1)").text();
        			var name = $("input[name='sel_cust_id']:checked").closest("tr").find("td:eq(2)").text();
        			var value = $("input[name='sel_cust_id']:checked").val();
        			$modal.find("input[name='meb_customerId']").val(value);
        			$modal.find("input[name='meb_customerNum']").val(num);
        			$modal.find("input[name='meb_customerName']").val(name);
        			$("div[role='customerModal']").modal("hide");
        			$("#form-members").valid();
        		}*/
        	});
        }
    };
    module.exports = modalModule;
});