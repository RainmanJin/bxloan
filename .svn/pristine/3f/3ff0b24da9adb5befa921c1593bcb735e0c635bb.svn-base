<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
%>
  <%@include file="../../commons/taglibs.jsp" %>
    <!DOCTYPE html>
    <html lang="zh" style="position:static;">
      
      <head>
        <base href="${ctx}/">
        <title>
          	${title }
        </title>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
        <meta name="description" content="Dashboard page" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0"
        />
        <c:import url="../../commons/pre-include.jsp" charEncoding="UTF-8"
        />
        <!-- page specific plugin styles START -->
        <style>
        #fuelux-wizard li {cursor:pointer;}
        </style>
        <script>
          var $$ctx = "${ctx}/";
        </script>
      </head>
      
      <body class="no-skin">
        <div class="main-container" id="main-container">
          <script type="text/javascript">
            try {
              ace.settings.check('main-container', 'fixed')
            } catch(e) {}
          </script>

								<div class="widget-body">
									<div class="widget-main">
										<div id="fuelux-wizard" data-target="#step-container">
											<ul class="wizard-steps">
												<li data-target="#step1" data-now="now" class="active" role="watchPoint" >
													<span class="step"></span>
													<span class="title">关联关系注释</span>
												</li>

												<li data-target="#step2" data-now="" role="watchPoint" >
													<span class="step"></span>
													<span class="title">图标注释</span>
												</li>

									 		<!-- 	<li data-target="#step3" data-now="" role="watchPoint" >
													<span class="step"></span>
													<span class="title">操作说明</span>
												</li> -->

												<li data-target="#step4" data-now="" role="watchPoint" >
													<span class="step"></span>
													<span class="title">运维支持</span>
												</li>
											</ul>
										</div>

										<hr>

										<div class="step-content pos-rel" id="step-container">
											<div class="step-pane active" id="step1">
												<h3 class="lighter block blue">一、关联关系注释</h3>
												
												<div style="text-align: left;" align="center">
												<table class="table table-striped table-hover" style="width: 60%;" align="center">
												
												<tr><td>1、全资关系</td><td>---持股比例为100%，关联类型为股东；</td></tr>
												<tr><td>2、控股关系</td><td>---持股比例大于50%，关联类型为股东；</td></tr>
												<tr><td>3、参股关系</td><td>---持股比例小于等于50%，关联类型为股东；</td></tr>
												<tr><td>4、股东关系</td><td>---无明确股东持股比例，关联类型为股东；</td></tr>
												<tr><td>5、实际控制关系</td><td>---企业实际控制人，数据来源对公企业的实际控制人；</td></tr>
												<tr><td>6、法人关系</td><td>---企业法人代表，数据来源对公企业的高管中的法人类型；</td></tr>
												<tr><td>7、集团关联关系</td><td>---集团成员客户，关联类型为集团；</td></tr>
												<tr><td>8、保证关系</td><td>---数据来源对外担保或反担保人信息；</td></tr>
												<tr><td>9、抵押关系</td><td>---数据来源对外担保或反担保人信息；</td></tr>
												<tr><td>10、质押关系</td><td>---数据来源对外担保或反担保人信息；</td></tr>
												<tr><td>11、担保关系</td><td>---数据来源对外担保中其他类型；</td></tr>
												<tr><td>12、对外投资</td><td>---关联类型为对外投资；</td></tr>
												<tr><td>13、兄妹</td><td>---关联类型为兄妹；</td></tr>
												<tr><td>14、其他亲属</td><td>---关联类型为其他亲属；</td></tr>
												<tr><td>15、朋友</td><td>---关联类型为朋友；</td></tr>
												<tr><td>16、子女</td><td>---关联类型为子女；</td></tr>
												<tr><td>17、父母</td><td>---关联类型为父母；</td></tr>
												<tr><td>18、实际用车人</td><td>---关联类型为实际用车人；</td></tr>
												<tr><td>19、实际用款人</td><td>---关联类型为实际用款人；</td></tr>
												<tr><td>20、募资关系</td><td>---关联类型为募资关系；</td></tr>
												</table>
												</div>
											</div>

											<div class="step-pane" id="step2">
												<h3 class="lighter block blue">二、图标注释</h3>
												<div style="text-align: left;" align="center">
												<table class="table table-striped table-hover" style="width: 60%;" align="center">
												
												<tr><td>1、</td><td>图标 <i class="ace-icon fa fa-edit"></i> 表示对记录进行“修改”；</td></tr>
												<tr><td>2、</td><td>图标 <i class="ace-icon fa fa-trash-o"></i> 表示对记录进行“删除”；</td></tr>
												<tr><td>3、</td><td>图标 <i class="ace-icon fa fa-eye"></i> 表示对记录进行“查看”，或查看隐藏选项；</td></tr>
												<tr><td>4、</td><td>图标 <i class="ace-icon fa fa-floppy-o"></i> 表示点击按钮可以保存修改；</td></tr>
												<tr><td>5、</td><td>图标 <i class="ace-icon fa fa-plus"> </i> 表示点击按钮可以新增一条记录；</td></tr>
												<tr><td>6、</td><td>图标 <i class="ace-icon fa fa-search"></i> 表示点击按钮可以按钮条件查询记录；</td></tr>
												<tr><td>7、</td><td>图标 <i class="ace-icon fa fa-download"></i> 表示下载该文件；</td></tr>
												<tr><td>8、</td><td>图标 <i class="ace-icon fa fa-upload"></i> 表示点击按钮上传本地文件；</td></tr>
												<tr><td>9、</td><td>图标 <i class="ace-icon fa fa-arrow-right"></i> 表示提交审核；</td></tr>
												<tr><td>10、</td><td>图标 <i class="ace-icon fa fa-undo"></i> 表示重置输入的信息；</td></tr>
												<tr><td>11、</td><td>图标 <i class="ace-icon fa fa-send"></i> 表示发起业务申请；</td></tr>
												<tr><td>12、</td><td>图标 <i class="ace-icon fa fa-chevron-left"></i> 表示返回上个页面；</td></tr>
												<tr><td>13、</td><td>图标 <i class="ace-icon fa fa-check"></i> 表示将该条记录的状态置为生效；</td></tr>
												<tr><td>14、</td><td>图标 <i class="ace-icon fa fa-power-off"></i> 表示退出系统</td></tr>
												<tr><td>15、</td><td>图标 <i class="ace-icon fa fa-check-square-o"></i> 表示提交至审批流程下一步</i></td></tr>
												</table>
												</div>
												
											</div>

										<!-- 	<div class="step-pane" id="step3">
												<h3 class="lighter block blue">三、操作说明</h3>
												<h4>1.图谱首页：</h4>
												通过输入“客户名称”、 “证件号码”信息实现客户关联关系图谱查询，也可直接点击“图谱查询”，进入图谱查询页面；支持客户名称模糊查询。
												<h4>2.按钮功能：</h4>
												① “原型”、“放大”、“缩小”、“全屏”按钮：实现图谱放大、缩小操作。</br>
												② “导出图谱”按钮：实现界面上图谱导出。
											</div> -->

											<div class="step-pane" id="step4">
												<h3 class="lighter block blue">三、运维支持</h3>
												<h4>联系方式</h4>
												<span>1.电话：010-66079731、010-66077440。</span></br>
												<span>2.邮箱：xiaodaixt@invest.coamc.com。</span>
											</div>
										</div>

									</div><!-- /.widget-main -->
								</div><!-- /.widget-body -->
             	
          
        </div>
        <!-- /.main-container -->
        <c:import url="../../commons/post-include.jsp" charEncoding="UTF-8"
        />
        <!-- page specific plugin scripts START -->
        <!-- page specific plugin scripts END -->
        <!-- inline scripts related to this page -->
        <script type="text/javascript">
         $(document).on("click","li[role='watchPoint']", function(){
         	var $li = $(this);
         	var isNow = $li.data("now");
         	
         	if($li.attr("class") != "active"){
         		$("#fuelux-wizard li[class='active']").removeAttr("class").attr("data-now","");
         		$li.attr("class","active");
         		$li.attr("data-now","now");
         	}
         	var target = $li.data("target");
         	target = target.substring(1,target.length);
         	$("#step-container div[class = 'step-pane active']").attr("class","step-pane");
         	$("#step-container div[id = '"+target+"']").attr("class", "step-pane active");
         });
        </script>
       
      </body>
    
    </html>