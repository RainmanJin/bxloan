define(function(require, exports, module) {
	var model = require("./model");
	var rm_fr = require("./rm_fr.js");
	var utils = require("utils");
	var _alert = bootbox.alert;
	var _confirm = bootbox.confirm;
	var view = Backbone.View.extend({
		el: "body",
		events: {
			"click #btn-query": "clickBtnQuery",
		},
		initialize: function() { /** 初始化 */
			this.model = new model();
			this.render();
		},
		goBack: function(){
			history.go(-1);
		},
		render: function() { /** 页面渲染 */
			var viewSelf=this;
			this.initFirst();
			this.initDateInput();
			utils.dd.initDataDict(["ContractSearchStatusCode"], function(dataDict){
				viewSelf.initDataTables(dataDict);
			});
			this.initBtnLive();
		},
		initFirst:function(){
			$("form").find(".required").closest("div.form-group").find("label").prepend("<font color='red'>*</font>");
		},
		initDateInput:function(){
			var viewSelf=this;
			var $form=$("form[role='searchForm']");
			$form.find(":text[name='startDate']").datepicker({clearBtn:true});
			$form.find(":text[name='endDate']").datepicker({clearBtn:true});
			$("form .formdate").datepicker({todayBtn:true,autoclose:true}).on("click",function(){
			}).on("show",function(){
				$(".datepicker").css("z-index","99999");
			});
		},
		initDataTables: function(dataDict) { /** 初始化DataTables */
			var viewSelf = this;
			var oTable = $("#tbl").dataTable({
				bFilter:false,
				bLengthChange:false,
				bSort:false,
		    	sAjaxSource: $$ctx+"loanRecovery/contractList",
		    	fnServerParams:function(aoData){
		    		var $form=$("form[role='searchForm']");
		    		aoData.push({name:"customerName",value:$form.find(":text[name='customerName']").val()});
		    		aoData.push({name:"customerNum",value:$form.find(":text[name='customerNum']").val()});
		    		aoData.push({name:"contractNum",value:$form.find(":text[name='contractNum']").val()});
		    		aoData.push({name:"contractStatus",value:$form.find("select[name='contractStatus']").val()});
		    		var startDate=$form.find(":text[name='startDate']").val();
		    		var endDate=$form.find(":text[name='endDate']").val();
		    		if(startDate){
		    			aoData.push({name:"startDate",value:startDate});
		    		}
		    		if(endDate){
		    			aoData.push({name:"endDate",value:endDate});
		    		}
		    	},
		    	aoColumns: [
		    	    {mData:"contractNum",mRender:function(data, type, rowdata){
		    	    	var link_data = "<a title='点击查看合同详情' href='"+ $$ctx + "contractMng/viewDetail?projectId="+rowdata['projectId'] +"'>"+data+"</a>";
		    	    	return link_data;
		    	    }},
		    	    {mData:"customerName"},
		    	    {mData:"productName"},
		    	    {mData:"contractAmt"},
		    	    {mData:"contractBalance"},
		    	    {mData:"contractStatusCd",mRender:function(data, type, rowdata){
		    	    	return dataDict.ContractSearchStatusCode[data];
		    	    }},
		    	    {mData:"payloanDateStr"},
		    	    {mData:null,mRender: function(data, type, rowdata){
		    	    	return rowdata.repayedPeriod+"/"+rowdata.totalPeriod;
		    	    }},
		    	    {mData:"lastRepayDateStr"},
		    	    {mData:null,mRender: function(data, type, rowdata){
		    	    	var html= "<div class='btn-group '>";
		    	    	html += "<button type='button' class='btn btn-primary dropdown-toggle btn-xs' data-toggle='dropdown'> "
		    	        html += "请选择操作<span class='caret'></span>"
		    	        html += " "
		    	    	html += "</button>";
		    	    	html += "<ul class='dropdown-menu' role='menu'>";
						html += "<li><a href='#' title='费率登记' role='feeRegister' data-id='" + rowdata.contractId + "' ><span class='line_span'><i class='ace-icon fa fa-edit'></i></span>费率登记</a></li>";
						html += "<li><a href='#' title='正常还款' role='normalRepay' data-id='" + rowdata.contractId + "' ><span class='line_span'><i class='ace-icon fa fa-edit'></i></span>正常还款</a></li>";
						html += "<li><a href='#' title='逾期还款' role='overdueRepay' data-id='" + rowdata.contractId + "' ><span class='line_span'><i class='ace-icon fa fa-edit'></i></span>逾期还款</a></li>";
						html += "<li><a href='#' title='逾期试算' role='overdueTrial' data-id='" + rowdata.contractId + "' ><span class='line_span'><i class='ace-icon fa fa-edit'></i></span>逾期试算</a></li>";
						html += "<li><a href='#' title='查看还款记录' role='showRepayList' data-id='" + rowdata.contractId + "' ><span class='line_span'><i class='ace-icon fa fa-eye'></i></span>查看还款记录</a></li>";
						html += "<li><a href='#' title='查看还款计划' role='showRepayPlanList' data-id='" + rowdata.contractId + "' ><span class='line_span'><i class='ace-icon fa fa-eye'></i></span>查看还款计划</a></li>";
						html += "</ul>";
						html += "</div>";
		    	    	return html;
		    	    }}
		    	],
		    	"fnRowCallback": function( nRow, aData, iDisplayIndex, iDisplayIndexFull ) {
		    	      // 所有的a都加粗
		    			$(nRow).attr('data-id',aData.contractId);
		    	    },
		    	"fnDrawCallback":function(){
		    		$("#tbl tr:last").find("div.btn-group").addClass("dropup");
		    		utils.num.tableFormat(this);
		    	}
			});
			viewSelf.oTable = oTable;
			//viewSelf.hoverOfTableTr("#tbl tbody tr",oTable);
			
		},
		initRepayInfoTables:function(dataDict){//还款记录
			var viewSelf = this;
			var oTable = $("#tb_repay_info").dataTable({
				bFilter:false,
				bLengthChange:false,
				bSort:false,
		    	sAjaxSource: $$ctx+"loanRecovery/findRepayList",
		    	fnServerParams:function(aoData){
		    		aoData.push({name:"contractId",value:$("#global_contractId").val()});
		    	},
		    	aoColumns:[
		    	    {mData:"repayNum"},
		    	    {mData:"repayDateStr"},
		    	    {mData:"actualAmt"},
		    	    {mData:"fundsSource",mRender:function(data, type, rowdata){
		    	    	return dataDict?dataDict.FundsSource[data]:data;
		    	    }},
		    	    {mData:"repaymentStatusCd",mRender:function(data, type, rowdata){
		    	    	return dataDict?dataDict.PayLoanStatus[data]:data;
		    	    }}],
		    	fnDrawCallback:function(){
			    		utils.num.tableFormat(this);
			    	}
				
			});
			viewSelf.repayInfoTable = oTable;
		},
		initRepayPlanInfoTables:function(dataDict){//还款计划明细
			var viewSelf = this;
			var oTable = $("#tb_repay_plan").dataTable({
				bFilter:false,
				bLengthChange:false,
				bSort:false,
		    	sAjaxSource: $$ctx+"loanRecovery/findRepayPlanInfoList",
		    	fnServerParams:function(aoData){
		    		aoData.push({name:"contractId",value:$("#global_contractId").val()});
		    	},
		    	aoColumns:[
	    	        {mData:"period"},
	    	        {mData:"endDateStr"},
	    	        {mData:"principal"},
	    	        {mData:"interest"},
	    	        {mData:"principalInterest"},
	    	        {mData:"status",mRender:function(data, type, rowdata){
		    	    	return dataDict?dataDict.PlanStatus[data]:data;
		    	    }}],
		    	fnDrawCallback : function(){
		    		utils.num.tableFormat(this);
		    	}
			});
			viewSelf.repayPlanInfoTable = oTable;
		},
		clickBtnQuery:function(){
			var $form=$("form[role='searchForm']");
    		var startDate=$form.find(":text[name='startDate']").val();
    		var endDate=$form.find(":text[name='endDate']").val();
			if(startDate&&endDate&&startDate>endDate){
				bootbox.alert("【放款日期止】要在【放款日期起】之后");
			}
			var viewSelf = this;
            viewSelf.oTable.fnPageChange(0);;
            return false;
		},
		initBtnLive:function(){
			var viewSelf = this;
			
			
			//查看还款记录
			$(document).on("click","a[role='showRepayList']",function(e){
				var btnSelf = $(this);
				e.preventDefault();
				$("#global_contractId").val(btnSelf.data("id"));
				var repayInfoTable=viewSelf.repayInfoTable;
				if(!repayInfoTable){
					utils.dd.initDataDict(["FundsSource","PayLoanStatus"], function(dataDict){
						viewSelf.initRepayInfoTables(dataDict);
					});
				}else{
					repayInfoTable.fnSettings()._iDisplayStart = 0;
					repayInfoTable.fnDraw();
				}
				$("#repay_info_modal").modal("show");
				
			});
			//查看还款计划
			$(document).on("click","a[role='showRepayPlanList']",function(e){
				var btnSelf = $(this);
				e.preventDefault();
				$("#global_contractId").val(btnSelf.data("id"));
				var dt=viewSelf.repayPlanInfoTable;
				if(!dt){
					utils.dd.initDataDict(["PlanStatus"], function(dataDict){
						viewSelf.initRepayPlanInfoTables(dataDict);
					});
				}else{
					dt.fnSettings()._iDisplayStart = 0;
					dt.fnDraw();
				}
				$("#repay_plan_modal").modal("show");
			});
		},
		hoverOfTableTr:function(selector){
			var viewSelf=this;
			var oTable=this.oTable;
			//table tr hover按钮
			$(document).on("mouseenter mouseout","#tbl tbody tr",function(e){
				var nTr=$(this);
				if(e.type=="mouseenter"){
					oTable.fnOpen(nTr, viewSelf.fnInitButtons(nTr), 'details' );
				}else{
					oTable.fnClose(nTr);
				}
			});
		},
		fnInitButtons:function(nTr){
			var oTable=this.oTable;
			var sOut = '<table cellpadding="5" cellspacing="0" border="0" style="padding-left:50px;">';
		    sOut += '<tr><td>Rendering engine:</td><td>sdfsdfsdfds</td></tr>';
		    sOut += '<tr><td>Link to source:</td><td>Could provide a link here</td></tr>';
		    sOut += '<tr><td>Extra info:</td><td>And any further details here (images etc)</td></tr>';
		    sOut += '</table>';
		    return sOut;
		}
	});
	module.exports = view;
});