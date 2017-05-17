define(function (require, exports, module)
{
    var model = require("./model");
    var utils = require("utils");
    var _alert = bootbox.alert;
    var rm = require("../../../../../approvalApplyInfo/rm");
    
    var view = Backbone.View.extend(
        {
            el : "body",
            events :
            {
            	
            },
            initialize : function ()
            {
                /** 初始化 */
                this.model = new model();
                this.render();
            },
            render : function ()
            {
                /** 页面渲染 */
            	this.initTableRm();
            	this.initSubmitBtn();
            	this.initValues();
            },
            disableForm : function(){
            	var formSelector = "#apply_info_form";
            	utils.forms.disableForm(formSelector);
            },
            initValues : function(){
            	var formSelector = "#apply_info_form";
            	$.ajax({
                    cache: false,
                    type: "POST",
                    url: $$ctx + "approvalApplyInfo/findValue",
                    data: {subjectId:$("#apply_info_form_subjectId").val()},
                    async: false,
                    error: function(request) {
                    	utils.alert.err("查询内容时出现错误");
                    },
                    success: function(result) {
                    	if(result.success){
                    		for(k in result.data){
                    			if(result.data["WORK_YEAR_CK"]){
                    				$("button[role='nextStep']").show();
                    				$("button[role='nextStep']").removeClass("hidden");
                    			}
                    			/*if(k){
                    				$("button[role='nextStep']").show();
                    				$("button[role='nextStep']").removeClass("hidden");
                    				break;
                    			}*/
                    		}
                    		utils.forms.putValueToForm(result.data,formSelector)
                        }else{
                        	utils.alert.err(result.msg);
                        }
                    }
                });
            	
            },
            initSubmitBtn : function(){
            	var formSelector = "#apply_info_form";
            	var btnSelector = "#apply_info_submit_btn";
            	$(document).on("click",btnSelector,function(){
            		$(btnSelector).attr("disabled","disabled");
            		$(formSelector).submit();
            	});
            },
            initTableRm : function(){
        		
            	$.validator.prototype.focusInvalid= function() {
        			if ( this.settings.focusInvalid ) {
        				try {
        					var _element = $(this.findLastActive() || this.errorList.length && this.errorList[0].element || [])
        					.filter(":visible");
        					if(_element&&_element.context){
        						var tab = $(_element.context).closest(".tab-pane");
        						if(tab.hasClass("active")){
        							//ignore
        						}else{
        							$("a[href='#"+tab[0].id+"']")[0].click();
        						}
        					}
        					
        					$(this.findLastActive() || this.errorList.length && this.errorList[0].element || [])
        					.filter(":visible")
        					.focus()
        					// manually trigger focusin event; without it, focusin handler isn't called, findLastActive won't have anything to find
        					.trigger("focusin");
        				} catch(e) {
        					// ignore IE throwing errors when focusing hidden elements
        				}
        			}
        		};
            	
            	var submitBtnSelector = "#apply_info_submit_btn";
        		var formSelector = "#apply_info_form";
        		var errSelector = "#apply_info_err_msg_field";
        	    $(formSelector).validate({
	                rules: rm.rules,
	                messages: rm.messages,
	                highlight: function( element, errorClass, validClass ) {
	        			if ( element.type === "radio" ) {
	        				this.findByName( element.name ).addClass( errorClass ).removeClass( validClass );
	        			} else {
	        				$( element ).addClass( errorClass ).removeClass( validClass );
	        			}
	        			$(errSelector).html("信息填写有误,请检查.");
	        		},
	        		unhighlight: function( element, errorClass, validClass ) {
	        			if ( element.type === "radio" ) {
	        				this.findByName( element.name ).removeClass( errorClass ).addClass( validClass );
	        			} else {
	        				$( element ).removeClass( errorClass ).addClass( validClass );
	        			}
	        			if(this.errorList&&this.errorList.length>0){
	        				/////errorList是validator存储错误的容器(数组)
	        			}else{
	        				$(errSelector).html("");
	        			}
	        			$(submitBtnSelector).removeAttr("disabled");
	        		},
	                submitHandler: function(form) {
	                    $.ajax({
	                        cache: false,
	                        type: "POST",
	                        url: $$ctx + "approvalApplyInfo/submit",
	                        data: $(formSelector).serialize(),
	                        async: false,
	                        error: function(request) {
	                        	_alert("提交时出现错误");
	                        	$(submitBtnSelector).removeAttr("disabled");
	                        },
	                        success: function(result) {
	                        	if(result.success){
	                        		utils.alert.suc("保存成功");
	                        		$("button[role='nextStep']").show();
	                        		$("button[role='nextStep']").removeClass("hidden");
	                            }else{
	                            	utils.alert.err(result.msg);
	                            }
	                        	$(submitBtnSelector).removeAttr("disabled");
	                        }
	                    });
	                    
	                    return false;
	                }
	            });
            }
            
        }
        );
    module.exports = view;
}
);
