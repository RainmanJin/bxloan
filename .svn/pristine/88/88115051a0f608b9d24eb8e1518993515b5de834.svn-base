define(function(require, exports, module) {
	var model = require("./model");
	var rm = require("./rm");
	var acc_data;
	var view = Backbone.View.extend({
		el: "body",
		events: {
			"change #logname": "initOrg"
		},
		
		initialize: function() { /** 初始化 */
			this.model = new model();
			this.render();
		},
		render: function() { /** 页面渲染 */
			this.initLogname();
			this.initFormValidate();
		},
		initFormValidate: function(){
			$("#loginForm").validate({
				rules: rm.rules,
				messages: rm.messages
			});
		},
		initLogname: function() {
			var viewSelf = this;
			viewSelf.model.getPersons(function(r){
				$.each(r.data,function(i,item){
					$("<option value='"+item.id+"'>"+ item.logname+"</option>").appendTo("#logname");
				});
				viewSelf.oData = r.data;
				viewSelf.initOrg();
			});
			
		},
		initOrg: function(id){
			var viewSelf = this;
			$("#orgid").empty();
			var lognameIndex = $("#logname")[0].selectedIndex;
			$.each(viewSelf.oData[lognameIndex].orgs,function(i,item){
				$("<option value='"+item.id+"'>"+ item.name+"</option>").appendTo("#orgid");
			});
		}

	});
	module.exports = view;
});