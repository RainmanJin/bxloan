define(function(require, exports, module) {
	var rm = {
		rules : {
			applyDateStart : {
				max : function() {
					var applyDateEnd = $("#applyDateEnd").val();
					return applyDateEnd != "" ? applyDateEnd : "9999-99-99";
				}
			},
			applyDateEnd : {
				min : function() {
					var applyDateStart = $("#applyDateStart").val();
					return applyDateStart != "" ? applyDateStart : "0000-00-00";
				}
			},
			applyAmtMin : {
				max : function() {
					var applyAmtMax = $("#applyAmtMax").val();
					return applyAmtMax != "" ? applyAmtMax : 99999999;
				}
			},
			applyAmtMax : {
				min : function() {
					var applyAmtMin = $("#applyAmtMin").val();
					return applyAmtMin != "" ? applyAmtMin : 00000000;
				}
			}
		},
		messages : {
			applyDateStart : {
				max : "不能大于终止日期"
			},
			applyDateEnd : {
				min : "不能小于开始日期"
			},
			applyAmtMin : {
				max : "不能大于最大金额"
			},
			applyAmtMax : {
				min : "不能小于最小金额"
			}
		}
	};
	module.exports = rm;
});