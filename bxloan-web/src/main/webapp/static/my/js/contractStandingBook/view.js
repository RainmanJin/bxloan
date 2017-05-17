define(function(require, exports, module) {
	var model = require("./model");
	var rm = require("./rm");
	var utils = require("utils");
	var view = Backbone.View.extend({
		el : "body",
		events : {
			"click #search" : "search",
			"click #reset" : "reset"
		},
		initialize : function() {
			this.model = new model();
			this.render();
		},
		render : function() {
			this.initTable();
		},
		initTable : function() {
			var viewSelf = this;
			var oTable = $("#table").dataTable({
				sAjaxSource : $$ctx + "contractStandingBook/list",
				bFilter : false,
				bLengthChange : false,
				fnServerParams : function(aoData) {
					aoData.push({
						name : "customerName",
						value : $('#customerName').val()
					}, {
						name : "certificateTypeCd",
						value : $('#certificateTypeCd').val()
					}, {
						name : "certificateNum",
						value : $('#certificateNum').val()
					});
				}
			});
			viewSelf.oTable = oTable;
		},
		search : function() {
		},
		reset : function() {
		}
	});
	module.exports = view;
});