define(function(require, exports, module) {
	var model = require("./model");
	var utils = require("utils");
	var view = Backbone.View.extend({
		el: "body",
		events: {
			
		},
		initialize: function() { /** 初始化 */
			this.model = new model();
			this.render();
		},
		render: function() { /** 页面渲染 */
			this.initSuggestionTable();
		},
		initSuggestionTable: function() {
			var viewSelf = this;
			viewSelf.model.querySuggestion({
				workflowId:$("#workflowId").val()
			},function(data){
				if(data!=null){
					for(var i = 0;i<data.length;i++){
						var tbody = $("#tbody")[0];
						var tr = document.createElement("tr");
						var pre = data[i].comments!=null?"<td>"+data[i].comments+"</td>":"<td>未填写内容</td>"
						var back =  "<td>"+data[i].stageNameCn+"</td>"
								   + "<td>"+data[i].taskCreatorCn+"</td>"
								   + "<td>"+data[i].taskAssigneeCn+"</td>"
						           + "<td>"+data[i].createTime+"</td>"
								   + "<td>"+data[i].taskResultCn+"</td>";
						tr.innerHTML = pre+back;
						tbody.appendChild(tr);
					}
				}else{
					var tbody = $("#tbody")[0];
					tbody.innerHTML = "没有记录";
				}
				
			});
			
		}
	
		
	});
	module.exports = view;
});