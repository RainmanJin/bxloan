define(function(require, exports, module) {
	var model = require("../main/model");
	var utils = require("utils");
	var view = Backbone.View.extend({
		el : "body",
		events : {
		},
		initialize : function() {
			this.model = new model();
			this.render();
		},
		render : function() {
			var viewSelf = this;
			/** 初始化费用列表 */
			utils.dd.initDataDict(["FeeUsedType", "FeeType"],function(dataDict) {
				 viewSelf.initBizExpenseRateList(dataDict);
			});
		},
		initBizExpenseRateList : function(dataDict) {
			var viewSelf = this;
            var oTable = $("#tbExpenseRate").dataTable({
                sAjaxSource: $$ctx + "bizapply/searchBizExpenseRateList",
                bFilter: false,
                bLengthChange: false,
                aoColumns: [
                    {
                    	bVisible: false,
                        mData: "bizExpenseRateId"
                    },
                    {
                        mData: "expenseCollectionType",
                        mRender: function(data, type, rowdata) {
                            return dataDict.FeeUsedType[data];
                        }
                    },
                    {
                        mData: "expenseName",
                        mRender: function(data, type, rowdata) {
                            return dataDict.FeeType[data];
 	                    }
                    },
                    {
                        mData: "standardExpenseRate"
                    },
                    {
                        mData: "expenseRate"
                    },
                    {
                        mData: "standaredAmt"
                    },
                    {
                        mData: "expenseAmt"
                    },
                    {
                        mData: "sysCreateDateStr"
                    },
                    {
                    	bVisible: false,
                        mData: "sysCreateDate"
                    }
                ],
                fnServerParams: function(aoData) {
                    aoData.push({
                        "name": "projectNo",
                        "value": $('#projectNoField').val()
                    });
                },
                fnDrawCallback : function(){
                	utils.num.colsFormat(this, [5,6]);
                }
            });
            viewSelf.oTable = oTable;
		}
	});
	module.exports = view;
});