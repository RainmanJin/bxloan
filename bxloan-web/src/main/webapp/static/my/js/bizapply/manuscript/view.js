define(function(require, exports, module) {
	var model = require("./model");
	
	var view = Backbone.View.extend({
		el: "body",
		events: {
			"click #saveManu":"saveManuScript"
		},
		initialize: function() { /** 初始化 */
			this.model = new model();
			this.render();
		},
		render: function() { /** 页面渲染 */
			alert("it's here");
		},
		
		saveManuScript:function(){/*保存工作底稿*/
			alert("走到这里了");
		}
		
	});
	module.exports = view;
});