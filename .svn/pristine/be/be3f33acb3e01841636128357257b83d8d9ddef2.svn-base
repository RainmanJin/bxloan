define(function(require, exports, module) {
	var model = require("./model");
	var utils = require("utils");
	var view = Backbone.View.extend({
		el : "body",
		events : {},
		initialize : function() {
			this.model = new model();
			this.render();
		},
		render : function() {
			this.initTable();
		},
		initTable : function() {
			$("#table").dataTable({
				sAjaxSource : $$ctx + "countRegistration/findBySearch",
				bFilter : false,
				bLengthChange : false,
				aoColumnDefs : [ {
					aTargets : [ 2 ],
					mRender : function(data, type, full) {
						return utils.date.formatDate(data);
					}
				}, {
					aTargets : [ 4 ],
					mRender : function(data, type, full) {
						return "<div class='btn-group'style='width:100px;'>" +
									"<button title='查看' type='button' role='view' data-id='" + data + "' class='btn btn-xs btn-yellow'>" +
										"<i class='ace-icon fa fa-eye'></i>" +
									"</button>" +
								"</div>";
					}
				} ],
				fnServerParams : function(aoData) {
					aoData.push({
						name : "projectId",
						value : $('#projectId').val()
					});
				}
			});
			
			$(document).on("click", "button[role='view']", function(e) {
				var id = $(this).data("id");
				$.ajax({
					type : 'post',
					url : $$ctx + 'countRegistration/findOne',
					data : {
						"id" : id
					},
					success : function(employeeAttendance) {
						$('#map').attr('src' , $$ctx + "countRegistration/toMap");
						
						$("#map").load(function() {
							var $iframe =  $($('#map')[0].contentWindow.document.body);
							$iframe.find("#longitude").val(employeeAttendance.longitude);
							$iframe.find("#latitude").val(employeeAttendance.latitude);
						});
					}
				});
				$('#mapModal').modal('show');
			});
		}
	});
	module.exports = view;
});