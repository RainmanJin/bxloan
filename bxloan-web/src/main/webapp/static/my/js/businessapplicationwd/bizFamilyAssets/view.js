define(function(require, exports, module) {
	var model = require("./model");
	var rm_fixedAsset = require("./rm_fixedAsset");
	var rm_produceArea = require("./rm_produceArea");
	var utils = require("utils");
	var view = Backbone.View.extend({
		el : "#bizFamilyAssetsForm",
		events : {
			"click i[role='addProductArea']":"fnAddProductArea",
			"keyup .moneyValid" : "fnValidMoney",
			"click i[role='addFixedAssets']":"fnAddFixedAssets",
			'click button[role="saveNoFixedAssetLiab"]':"saveNoFixedAssetLiab",
			//生产区域
			'click button[role="editProduceArea"]':"clickEditProduceArea",
			'click button[role="delProduceArea"]':"clickDelProduceArea",
			'click button[role="viewProduceArea"]':"clickViewProduceArea",
		},
		initialize : function() {
			this.viewObj=$(this.el);
			this.model=new model();
			this.render();
		},
		render : function() {
			this.projId=$('#projectId').val();//申请id
			this.bizType=$('#bizType').val();//农贷业务类型
			this.isView=utils.parseBool($("#isView").val());//显示类型
			this.isEdit=utils.parseBool($("#isEdit").val());//是否是编辑
			var viewSelf=this;
			viewSelf.initTooltip();
			viewSelf.initInputDate();
			viewSelf.initUnitField();
			viewSelf.bindFormSubmit();
			viewSelf.initChangeEvent();
			utils.dd.initDataDict(["MyRentType","MyWillProduceFuture","MyCultivateBreedType","MyHaveLease"], function(dataDict){
				viewSelf.MyRentType=dataDict.MyRentType;//生产区域性质
				viewSelf.MyWillProduceFuture=dataDict.MyWillProduceFuture;//未来12个月是否生产
				viewSelf.MyCultivateBreedType=dataDict.MyCultivateBreedType;//种养类型
				viewSelf.MyHaveLease=dataDict.MyHaveLease;//是否有租赁协议
				viewSelf.initTbOfProduceArea();
			});
			viewSelf.model.findFixedAssetDictData(function(r_data){
				viewSelf.fixedAssetDictData=r_data.data;
				viewSelf.fixedAssetTypes={};//固定资产类型
				viewSelf.fixedAssetNameCodes={};//固定品名
				$.each(viewSelf.fixedAssetDictData.types,function(i,v){
					viewSelf.fixedAssetTypes[v.value]=v.name;
				});
				$.each(viewSelf.fixedAssetDictData.items,function(i,v){
					viewSelf.fixedAssetNameCodes[v.value]=v.name;
				});
				utils.dd.initDataDict(["MyFixedAssetHousingStructure"], function(dataDict){
					viewSelf.MyFixedAssetHousingStructure=dataDict.MyFixedAssetHousingStructure;//固定资产住房结构
					viewSelf.initTbOfFixedAsset();
					viewSelf.initCascadeOfFixedAsset();
				});
			});
			//租期单位
			viewSelf.tenancyUnits={
				'1':'年',
				'2':'月',
				'3':'日',
			}
			viewSelf.initTbOfNoFixedAsset();
		},
		initChangeEvent:function(){
			//生产区域：生产区域性质
			var $form_produceArea=$("#form-productionAreaOfBizFa");
			$form_produceArea.find('select[name="areaProperty"]').change(function(){
				var $selectSelf=$(this);
        		var selectVal=$selectSelf.val();
        		if(selectVal==3){
        			$form_produceArea.find(':input[name="tenancyUnit"]').prop("disabled",false);
        			$form_produceArea.find(':input[name="tenancy"]').prop("disabled",false).parents(".form-group").show();
        			$form_produceArea.find(':input[name="rent"]').prop("disabled",false).parents(".form-group").show();
        			$form_produceArea.find(':input[name="rentDateStr"]').prop("disabled",false).parents(".form-group").show();
        			$form_produceArea.find(':input[name="haveLease"]').prop("disabled",false).parents(".form-group").show();
        		}else{
        			$form_produceArea.find(':input[name="tenancyUnit"]').prop("disabled",true);
        			$form_produceArea.find(':input[name="tenancy"]').prop("disabled",true).parents(".form-group").hide();
        			$form_produceArea.find(':input[name="rent"]').prop("disabled",true).parents(".form-group").hide();
        			$form_produceArea.find(':input[name="rentDateStr"]').prop("disabled",true).parents(".form-group").hide();
        			$form_produceArea.find(':input[name="haveLease"]').prop("disabled",true).parents(".form-group").hide();
        		}
        		
			});
		},
		initInputDate:function(){
			var viewSelf=this;
			viewSelf.viewObj.find(":input.input-datepicker").datepicker({format : 'yyyy-mm-dd',todayHighlight : true,autoclose:true,
				clearBtn : true}).on("show",function(){
				$(".datepicker").css("z-index","99999");
			});
			//年份处理
			viewSelf.viewObj.find(":input.input-datepicker-year").datepicker(
					{ format: "yyyy", startView: 2, minViewMode: 2, todayBtn: "linked", clearBtn: true, autoclose: true}).on("show",function(){
				$(".datepicker").css("z-index","99999");
			});
		},
		initTooltip:function(){
			this.viewObj.on("mouseover","a[title]",function(){
				$(this).tooltip({
					//placement:"bottom",
					placement:function (context, source) {
						//sidebar中提示特殊处理位置，防止被遮盖
						if($(source).closest("div.sidebar").length>0){
							var offset = $(source).offset();
							if( parseInt(offset.left) < parseInt($("#sidebar").get(0).scrollWidth / 2) ) return 'right';
							return 'left';
						}
						return 'top';
					},
					html:true,
					template:'<div class="tooltip" role="tooltip"><div class="tooltip-arrow"></div><div class="tooltip-inner" style="white-space:nowrap;"></div></div>'
				})
				$(this).tooltip("show");
			});
		},
		bindFormSubmit:function(){
			var viewSelf=this;
			$("#form-fixedAssetOfBizFa").validate({
				rules:rm_fixedAsset.rules,
				messages:rm_fixedAsset.messages,
                submitHandler: function(form) {
					var params=[];
					var $form=$(form);
					utils.button.ban($form.find('button[role="submit"]'));//禁用按钮
					params.push($form.serialize());
					params.push('bizType='+viewSelf.bizType);
					params.push('projectId='+viewSelf.projId);
                	viewSelf.model.saveFixedAsset(params.join("&"),function(r_data){
                		if(r_data.success){
                			viewSelf.initTbOfFixedAsset();
                			$("#modal-fixedAssetOfBizFa").modal("hide");
                		}else{
                			utils.alert.err("<b>" + r_data.msg + "</b>");
                		}
                		utils.button.reset($form.find('button[role="submit"]'));//启用按钮
                	});
                    return false;
                }
            });
			$("#form-productionAreaOfBizFa").validate({
				rules:rm_produceArea.rules,
				messages:rm_produceArea.messages,
				submitHandler: function(form) {
					var $form=$(form);
					var areaProperty=$form.find('select[name="areaProperty"]').val();
					if(areaProperty==='3'){//租赁
						if(!$form.find(':input[name="rent"]').val()){
							utils.alert.warn("<strong>请输入租金</strong>");
							return false;
						}
					}
					var tenancy=$form.find(':input[name="tenancy"]').val();
					if(tenancy){
						var tenancyUnit=$form.find(':input[name="tenancyUnit"]').val();
						if(!tenancyUnit){
							utils.alert.warn("<strong>请输入租金单位</strong>");
							return false;
						}
					}
					utils.button.ban($form.find('button[role="submit"]'));//保存
					var params=[];
					params.push($form.serialize());
					params.push('projectId='+viewSelf.projId);
					viewSelf.model.saveProduceArea(params.join("&"),function(r_data){
						if(r_data.success){
                			viewSelf.initTbOfProduceArea();
                			$("#modal-productAreaOfBizFa").modal("hide");
                		}else{
                			utils.alert.err("<b>" + r_data.msg + "</b>");
                		}
						utils.button.reset($form.find('button[role="submit"]'));
					});
					return false;
				 }
			});
			//固定资产删除
			$(viewSelf.viewObj).on("click", 'button[role="delFixedAsset"]', function(e) { 
				var btnSelf=$(this);
				utils.button.confirm(btnSelf,function(result){
					if(result){
						viewSelf.model.delFixedAsset(btnSelf.data("id"),function(r_data){
							if(r_data.success){
								viewSelf.initTbOfFixedAsset();
							}else{
								utils.alert.err("<b>" + r_data.msg + "</b>");
							}
						});
					}
				});
			});
			//固定资产修改
			$(viewSelf.viewObj).on("click", 'button[role="editFixedAsset"]', function(e) { 
				var btnSelf=$(this);
				viewSelf.model.findFixedAsset(btnSelf.parents("tr").data("id"),function(r_data){
					if(!r_data.success){
						utils.alert.err("<b>" + r_data.msg + "</b>");
						return;
					}
					viewSelf.initFormOfFixedAsset(r_data.data,"edit");
				});
			});
			//固定资产查看
			$(viewSelf.viewObj).on("click", 'button[role="viewFixedAsset"]', function(e) { 
				var btnSelf=$(this);
				viewSelf.model.findFixedAsset(btnSelf.parents("tr").data("id"),function(r_data){
					if(!r_data.success){
						utils.alert.err("<b>" + r_data.msg + "</b>");
						return;
					}
					viewSelf.initFormOfFixedAsset(r_data.data,"view");
				});
			});
		},
		initTbOfProduceArea:function(){
			var viewSelf=this;
			var tenancyUnits=viewSelf.tenancyUnits;
			var params=[];
			params.push('projId='+viewSelf.projId);
			var areaPropertyMap=viewSelf.MyRentType;//生产区域性质
			var yesOrNoMap=viewSelf.MyWillProduceFuture;//未来12个月是否生产
			var cultivateBreedTypes=viewSelf.MyCultivateBreedType;//种养类型
			var isHaveMap=viewSelf.MyHaveLease;//是否有租赁协议
			viewSelf.model.findProduceAreaList(params.join("&"),function(r_data){
				if(!r_data.success){
					utils.alert.err("<b>" + r_data.msg + "</b>");
					return;
				}
				var tb=viewSelf.viewObj.find("table[role='tb_produceArea']");
    			var tb_tbody=tb.find("tbody");
    			tb_tbody.empty();
    			var html=[];
    			if(r_data.data!=null&&r_data.data.length>0){
	    			$.each(r_data.data,function(i,v){
	    				html.push('<tr data-id="'+v.id+'">');
	    				html.push('<td>'+v.location+'</td>');
	    				html.push('<td>'+v.area+'</td>');
	    				html.push('<td>'+areaPropertyMap[v.areaProperty]+'</td>');
	    				html.push('<td>'+yesOrNoMap[v.willProduceFuture]+'</td>');
	    				html.push('<td>'+v.produceContent+'</td>');
	    				html.push('<td>'+(v.equipmentSituation?v.equipmentSituation:'')+'</td>');
	    				html.push('<td>'+(v.rent?v.rent:'')+'</td>');
	    				html.push('<td>');
	    				if(v.tenancy){
	    					html.push(v.tenancy+tenancyUnits[v.tenancyUnit]);
	    				}
	    				html.push('</td>');
	    				html.push('<td>'+(v.rentDate?v.rentDate:'')+'</td>');
	    				html.push('<td>');
	    				html.push(isHaveMap[v.haveLease]?isHaveMap[v.haveLease]:'');
	    				html.push('</td>');
	    				html.push('<td>');
	    				if(viewSelf.isEdit){
	    					html.push('<button role="editProduceArea" title="修改" class="btn btn-xs btn-info">');
	    					html.push('<i class="ace-icon fa fa-edit"></i>');
	    					html.push('</button>');
	    					html.push('<button role="delProduceArea" title="删除" class="btn btn-xs btn-danger">');
	    					html.push('<i class="ace-icon fa fa-trash-o"></i>');
	    					html.push('</button>');
	    				}
	    				html.push('<button role="viewProduceArea" title="查看" class="btn btn-xs btn-yellow">');
	    				html.push('<i class="ace-icon fa fa-eye"></i>');
	    				html.push('</button>');
	    				html.push('</td>');
	    				html.push('</tr>');
	    			});
    			}else{
    				var colspan=tb.find("thead th").length;
    				html.push("<tr class='odd'><td valign='top' colspan='"+colspan+"' class='dataTables_empty'>没有符合条件的记录</td></tr>");
    			}
    			tb_tbody.html(html.join(""));
			});
		},
		initUnitField: function() {
            var uls = $("div.input-group-btn ul.dropdown-menu li");
            $.each(uls,
            function(i, e) {
                var $this = $(this);
                $this.on("click",
                function() {
                    var val = $this.data("value");
                    $this.closest("div.input-group").find(":hidden").val(val);
                });
            });
        },
		initFormOfProduceArea:function(data,showType){
			var viewSelf=this;
			var tenancyUnits=viewSelf.tenancyUnits;
			var form=$("#form-productionAreaOfBizFa");
			var title=form.find('[role="form-title"]');
			var btnSubmit=form.find('[role="submit"]');
			
			if(showType=="add"){//新增
				title.html('<i class="ace-icon fa fa-plus"></i>&nbsp;生产区域信息');
				form.find(':input[type="text"],:radio,select,textarea').prop("disabled",false);
				form.find(':hidden[name="id"]').val('');
				form.find(':hidden[name="tenancyUnit"]').val('');
				form.find('button[role="tenancyUnit"]').text("单位");
				form.find('button[role="btn-dropDown"]').show();
				form.get(0).reset();
				btnSubmit.show();
			}else {//修改或查看
				utils.forms.putValueToForm(data,form);
				form.find(':input[name="rentDateStr"]').val(data.rentDate);
				if(showType=="edit"){//修改
					title.html('<i class="ace-icon fa fa-edit"></i>&nbsp;生产区域信息');
					form.find(':input[type="text"],:radio,select,textarea').prop("disabled",false);
					form.find('button[role="btn-dropDown"]').show();
					btnSubmit.show();
					
				}else if(showType=="view"){//查看
					title.html('<i class="ace-icon fa fa-eye"></i>&nbsp;生产区域信息');
					form.find(':input[type="text"],:radio,select,textarea').prop("disabled",true);
					form.find('button[role="btn-dropDown"]').hide();
					btnSubmit.hide();
				}
				var tenancyUnit=form.find(':hidden[name="tenancyUnit"]').val();
				if(tenancyUnit){
					form.find('button[role="tenancyUnit"]').text(tenancyUnits[tenancyUnit]?tenancyUnits[tenancyUnit]:'单位');
				}else{
					form.find('button[role="tenancyUnit"]').text("单位");
				}
			}
			form.find('select[name="areaProperty"]').change();
			$("#modal-productAreaOfBizFa").modal("show");
		},
		clickEditProduceArea:function(e){
			var viewSelf=this;
			var btnSelf=$(e.currentTarget);
			viewSelf.model.findProductionAreaInfo(btnSelf.parents("tr").data("id"),function(r_data){
				if(!r_data.success){
					utils.alert.err("<b>" + r_data.msg + "</b>");
					return;
				}
				viewSelf.initFormOfProduceArea(r_data.data,"edit");
			});
		},
		clickDelProduceArea:function(e){
			var viewSelf=this;
			var btnSelf=$(e.currentTarget);
			utils.button.confirm(btnSelf,function(result){
				if(result){
					viewSelf.model.delProduceArea(btnSelf.parents('tr').data("id"),function(r_data){
						if(r_data.success){
							viewSelf.initTbOfProduceArea();
						}else{
							utils.alert.err("<b>" + r_data.msg + "</b>");
						}
					});
				}
			});
		},
		clickViewProduceArea:function(e){
			var viewSelf=this;
			var btnSelf=$(e.currentTarget);
			viewSelf.model.findProductionAreaInfo(btnSelf.parents("tr").data("id"),function(r_data){
				if(!r_data.success){
					utils.alert.err("<b>" + r_data.msg + "</b>");
					return;
				}
				viewSelf.initFormOfProduceArea(r_data.data,"view");
			});
		},
		initTbOfFixedAsset:function(){//初始化固定资产Table
			var viewSelf=this;
			var fixedAssetTypes=viewSelf.fixedAssetTypes;
			var fixedAssetNames=viewSelf.fixedAssetNameCodes;
			var structures=viewSelf.MyFixedAssetHousingStructure;
			var params=[];
			params.push('projId='+viewSelf.projId);
			viewSelf.model.findFixedAssetList(params.join("&"),function(r_data){
        		if(r_data.success){
        			var tb_fixedAssetList=viewSelf.viewObj.find("table[role='tb_fixedAsset']");
        			var tb_fixedAssetList_tbody=tb_fixedAssetList.find("tbody");
        			tb_fixedAssetList_tbody.empty();
        			var html=[];
        			if(r_data.data!=null&&r_data.data.length>0){
	        			$.each(r_data.data,function(i,v){
	        				html.push('<tr data-id='+v.id+'>');
	        				html.push('<td>'+fixedAssetTypes[v.assetType]+'</td>');
	        				html.push('<td>'+(fixedAssetNames[v.assetNameCode]?fixedAssetNames[v.assetNameCode]:v.assetName)+'</td>');
	        				html.push('<td>'+(v.estimatedPrice?v.estimatedPrice:'')+'</td>');
	        				html.push('<td>'+v.originalAcquisitionPrice+'</td>');
	        				html.push('<td>'+v.sizeOrQuantity+'</td>');
	        				html.push('<td>');
	        				if(v.buyOrBuildYear){
	        					html.push(v.buyOrBuildYear.substring(0,4));
	        				}
	        				html.push('</td>');
	        				html.push('<td>')
	        				if(v.structure){
	        					html.push('【'+structures[v.structure]+'】')
	        				}
	        				html.push((v.description?v.description:''));
	        				html.push('</td>');
	        				html.push('<td>');
	        				if(viewSelf.isEdit){
	        					html.push('<button role="editFixedAsset" title="修改" class="btn btn-xs btn-info">');
	        					html.push('<i class="ace-icon fa fa-edit"></i>');
	        					html.push('</button>');
	        					html.push('<button data-id='+v.id+' role="delFixedAsset" title="删除" class="btn btn-xs btn-danger">');
	        					html.push('<i class="ace-icon fa fa-trash-o"></i>');
	        					html.push('</button>');
	        				}
	        				html.push('<button role="viewFixedAsset" title="查看" class="btn btn-xs btn-yellow">');
	        				html.push('<i class="ace-icon fa fa-eye"></i>');
	        				html.push('</button>');
	        				html.push('</td>');
	        				html.push('</tr>');
	        			});
        			}else{
        				var colspan=tb_fixedAssetList.find("thead th").length;
        				html.push("<tr class='odd'><td valign='top' colspan='"+colspan+"' class='dataTables_empty'>没有符合条件的记录</td></tr>");
        			}
        			tb_fixedAssetList_tbody.html(html.join(""));
        		}else{
        			utils.alert.err("<b>" + r_data.msg + "</b>");
        		}
        	});
		},
		initCascadeOfFixedAsset:function(){
			var viewSelf=this;
			var types=viewSelf.fixedAssetDictData.types,
			cascadeData=viewSelf.fixedAssetDictData.cascadeData;
			var form=$("#form-fixedAssetOfBizFa");
			var $select_type=form.find('select[name="assetType"]');//类型
			$select_type.get(0).options.length=0;
			$.each(types,function(i,v){
				$select_type.append("<option value='"+v.value+"'>"+v.name+"</option>");
			});
			var $select_nameCode=form.find('select[name="assetNameCode"]');//品名
			var assetTypeCodes=['01'];//禁止填估计价值的类型
			$select_type.change(function(){
        		var $selectSelf=$(this);
        		var selectVal=$selectSelf.val();
        		$select_nameCode.get(0).options.length=0;
        		var items=cascadeData[selectVal];
        		if(items){
        			$.each(items,function(i,v){
        				$select_nameCode.append("<option value='"+v.value+"'>"+v.name+"</option>");
        			});
        		}
        		$select_nameCode.append("<option value='0000'>其他</option>");
        		var $estimatedPrice=form.find(':input[name="estimatedPrice"]');
        		if(selectVal=='06'){
        			$estimatedPrice.prop("disabled",true).parents('.form-group').hide();
        			form.find(':input[name="originalAcquisitionPrice"]').prop("disabled",true);
        			form.find(':input[name="sizeOrQuantity"]').prop("disabled",true).parents('.form-group').hide();
        			form.find(':input[name="buyOrBuildYearStr"]').prop("disabled",true);
        		}else if(assetTypeCodes.indexOf(selectVal)>=0){//禁止验证估计价值
        			$estimatedPrice.prop("disabled",true);
        			$estimatedPrice.parents('.form-group').show();
        			form.find(':input[name="originalAcquisitionPrice"]').prop("disabled",false);
        			form.find(':input[name="sizeOrQuantity"]').prop("disabled",false).parents('.form-group').show();
        			form.find(':input[name="buyOrBuildYearStr"]').prop("disabled",false);
        		}else{
        			$estimatedPrice.prop("disabled",false).parents('.form-group').show();
        			form.find(':input[name="originalAcquisitionPrice"]').prop("disabled",false);
        			form.find(':input[name="sizeOrQuantity"]').prop("disabled",false).parents('.form-group').show();
        			form.find(':input[name="buyOrBuildYearStr"]').prop("disabled",false);
        		}
        		$select_nameCode.change();//调用品名改变
        	});
			var $fg_structure=form.find('div[role="select-structure"]');
			$select_nameCode.change(function(){
				var $select_structure=form.find('select[name="structure"]');
				var $selectSelf=$(this);
				var selectVal=$selectSelf.val();
				if(selectVal=='0001'){//住房
					$select_structure.prop("disabled",false);
					$fg_structure.show();
				}else{
					$fg_structure.hide();
					$select_structure.prop("disabled",true);
				}
				var $assetName=form.find(':input[name="assetName"]');
				if(selectVal=='0000'){
					$assetName.prop("readonly",false).val('');
				}else{
					$assetName.prop("readonly",true).val(viewSelf.fixedAssetNameCodes[selectVal]);
				}
			});
			$select_type.trigger("change");//调用类型改变
			
		},
		initFormOfFixedAsset:function(data,showType){
			var form=$("#form-fixedAssetOfBizFa");
			var title=form.find('[role="form-title"]');
			var btnSubmit=form.find('[role="submit"]');
			
			var $select_type=form.find('select[name="assetType"]');
			if(showType=="add"){//新增
				title.html('<i class="ace-icon fa fa-plus"></i>&nbsp;固定资产');
				form.find(':input[name="id"]').val('');
				form.find(':input[type="text"],select,textarea').prop("disabled",false);
				btnSubmit.show();
				form.get(0).reset();
				$select_type.change();
			}else {//修改或查看
				utils.forms.putValueToForm(data,form);
				$select_type.change();
				form.find('select[name="assetNameCode"]').val(data.assetNameCode);
				form.find('select[name="assetNameCode"]').change();//品名
				form.find(':input[name="assetName"]').val(data.assetName);
				form.find('select[name="structure"]').val(data.structure);
				form.find(':input[name="buyOrBuildYearStr"]').val(data.buyOrBuildYear?data.buyOrBuildYear.substring(0,4):'');
				if(showType=="edit"){//修改
					title.html('<i class="ace-icon fa fa-edit"></i>&nbsp;固定资产');
					form.find(':input[type="text"],select,textarea').prop("disabled",false);
					btnSubmit.show();
					
				}else if(showType=="view"){//查看
					console.log(showType);
					title.html('<i class="ace-icon fa fa-eye"></i>&nbsp;固定资产');
					form.find(':input[type="text"],select,textarea').prop("disabled",true);
					btnSubmit.hide();
				}
			}
			$("#modal-fixedAssetOfBizFa").modal("show");
			
		},
		initTbOfNoFixedAsset:function(){//初始化非固定资产及负债Table
			/*var assetTypes={
					'00':'对非家庭成员提供的担保',
					'01':'资产',
					'02':'负债',
			}*/
			var viewSelf=this;
			var params=[];
			params.push('projId='+viewSelf.projId);
			viewSelf.model.findNoFixedAssetList(params.join("&"),function(r_data){
        		if(r_data.success){
        			var tb_noFixedAsset=viewSelf.viewObj.find("table[role='tb_noFixedAsset']");
        			var tb_noFixedAsset_tbody=tb_noFixedAsset.find("tbody");
        			tb_noFixedAsset_tbody.empty();
        			var assetTypes=r_data.data.assetTypes;
        			var html=[];
        			if(r_data.data.items){
        				$.each(r_data.data.items,function(i,v){
        					html.push('<tr class="data-item" data-id='+v.id+'>');
        					html.push('<td');
        					if(v.assetType==0){
        						html.push(' colspan="2"');
        					}
        					html.push('>'+assetTypes[v.assetType]+'</td>');
        					if(v.assetType!=0){
        						html.push('<td>'+(v.assetName?v.assetName:'')+'</td>');
        					}
        					html.push('<td><input name="money" class="form-control moneyValid" value="'+(v.price?v.price:'')+'" maxlength="18"></td>');
        					html.push('<td><input name="remark" class="form-control" value="'+(v.remark?v.remark:'')+'" maxlength="180"></td>');
        					html.push('</tr>');
        				});
        			}
        			tb_noFixedAsset_tbody.html(html.join(""));
        			if(viewSelf.isView){
        				$.each(tb_noFixedAsset_tbody.find(':input'),function(i,v){
        					$(this).prop('readonly','true').css({'background':'none !important'});
        				});
        			}
        		}else{
        			utils.alert.err("<b>" + r_data.msg + "</b>");
        		}
        	});
		},
		fnValidMoney:function(e){
			var regx =/^-?\d+\.?\d{0,2}$/;
			var $inputSelf=$(e.currentTarget);
			var val=$inputSelf.val();
			val=val.replace(/\b(0+)/gi,"");
			if(!regx.test(val)){
				$inputSelf.val(0);
			}else{
				$inputSelf.val(val);
			}
		},
		fnAddProductArea:function(){
			var viewSelf=this;
			viewSelf.initFormOfProduceArea(null,"add");
		},
		fnAddFixedAssets:function(){
			var viewSelf=this;
			viewSelf.initFormOfFixedAsset(null,"add");
		},
		saveNoFixedAssetLiab:function(e){
			var viewSelf=this;
			var btnSelf=$(e.currentTarget);
			utils.button.ban(btnSelf);//禁用按钮
			var tb_fixedAsset=viewSelf.viewObj.find("table[role='tb_noFixedAsset']");
			var dataItems=tb_fixedAsset.find("tr.data-item");
			var params=[];
			$.each(dataItems,function(i,v){
				var $tr=$(v);
				var dataItem={
						'id':$tr.data("id"),
						'money':$tr.find(":input[name='money']").val(),
						'remark':$tr.find(":input[name='remark']").val()
						};
				params.push(dataItem);
			});
			var projId=viewSelf.projId;
			viewSelf.model.saveNoFixedAssetLiab(projId,JSON.stringify(params),function(r_data){
				if(r_data.success){
					utils.alert.suc("<b>" + r_data.msg + "</b>");
				}else{
	    			utils.alert.err("<b>" + r_data.msg + "</b>");
	    		}
				utils.button.reset(btnSelf);//启用按钮
			});
		}
	});
	module.exports = view;
});