<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
%>
  <%@include file="../../../commons/taglibs.jsp" %>
    <!DOCTYPE html>
    <html lang="zh">
      
      <head>
        <base href="${ctx}/">
        <title>
          	邦信微贷
        </title>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
        <meta name="description" content="Dashboard page" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0"
        />
        <c:import url="../../../commons/pre-include.jsp" charEncoding="UTF-8"
        />
        <!-- page specific plugin styles START -->
        <script>
          var $$ctx = "${ctx}/";
          var $$res = "${res}/";
        </script>
      </head>
      <body class="no-skin">
        <c:import url="../../../commons/navbar.jsp" charEncoding="UTF-8" />
        <div class="main-container" id="main-container">
         <!-- 隐藏域 -->
              <div>
               
              </div>
          <script type="text/javascript">
            try {
              ace.settings.check('main-container', 'fixed')
            } catch(e) {}
          </script>
          <c:import url="../../../commons/sidebar.jsp" charEncoding="UTF-8" />
          <div class="main-content">
            <div class="breadcrumbs" id="breadcrumbs">
              <script type="text/javascript">
                try {
                  ace.settings.check('breadcrumbs', 'fixed')
                } catch(e) {}
              </script>
              <ul class="breadcrumb">
                <li>
                  <i class="ace-icon fa fa-home home-icon">
                  </i>
                  <a href="/bxloan">
                    	主页	
                  </a>
                </li>
                 <li class="active">
                  	客户管理
                </li>
                <li class="active">
                  	联保体客户
                </li>
              </ul>
              <!-- /.breadcrumb -->
              <!-- <div class="nav-search" id="nav-search">
              <form class="form-search">
              <span class="input-icon">
              <input type="text" placeholder="Search ..." class="nav-search-input" id="nav-search-input" autocomplete="off" />
              <i class="ace-icon fa fa-search nav-search-icon"></i>
              </span>
              </form>
              </div> 
              -->
            </div>
            <div class="page-content">
             
              <%-- <c:import url="../../../commons/settings.jsp" charEncoding="UTF-8"
              />
              --%>
              <div class="page-header">
                <h1>
                  	客户管理
                  <small>
                    <i class="ace-icon fa fa-angle-double-right">
                    </i>
                    	联保体客户
                  </small>
                  <span style="float:right;">
                    <button id="btn-query" class="btn btn-sm btn-purple" type="button">
                      <i class="ace-icon fa fa-search">
                      </i>
                    	  查询
                    </button>
                    <button id="btn-reset" class="btn btn-sm btn-default">
                      <i class="ace-icon fa fa-undo">
                      </i>
                      	重置
                    </button>
                    <button id="btn-add" class="btn btn-sm btn-success">
                      <i class="ace-icon fa fa-plus">
                      </i>
                     	 新建
                    </button>
                  </span>
                </h1>
              </div>
              <!-- /.page-header -->
              
              <!-- 条件搜索 -->
              <div class="row" id="condSearchDiv" style="height:0px;">
              	<div class="col-xs-12">
              	<form class="form-horizontal" id="searchForm">
	              	<div class="form-group" >
	              		<label class="control-label col-sm-2">联保体协议编号：</label>
	              		<div class="col-sm-2">
	              		  <input type="text" name="unGuId" class="form-control"/>
	              		</div>
	              		<label class="control-label col-sm-2">联保体小组名称：</label>
	              		<div class="col-sm-2">
	              		  <input type="text" name="unGroupName" class="form-control"/>
	              		</div>
	              		<label class="control-label col-sm-2">联保体成员客户编号：</label>
	              		<div class="col-sm-2">
	              		  <input type="text" name="customerNum" class="form-control"/>
	              		</div>
	              	</div>
	                <div class="form-group">
	              		<label class="control-label col-sm-2">联保体成员名称：</label>
	              		<div class="col-sm-2">
	              		  <input type="text" name="customerName" class="form-control"/>
	              		</div>
	              		<label class="control-label col-sm-2">联保体成员证件类型：</label>
	              		<div class="col-sm-2">
	              		  <select name="certificateTypeCd" class="form-control">
	                 	 				<option value="">全部</option>
	                 	              <dd:options codeType="CertificateType"/>
	                 	              </select>
	              		</div>
	              		<label class="control-label col-sm-2">联保体成员证件号码：</label>
	              		<div class="col-sm-2">
	              		  <input type="text" name="certificateNum" class="form-control"/>
	              		</div>
	              	</div> 	
                 	<div class="form-group">
	              		<label class="control-label col-sm-2">联保体协议状态：</label>
	              		<div class="col-sm-2">
	              		  <select name="unStatus" class="form-control">
	                 				   <option value="">全部</option>
	                 	              <dd:options codeType="UnStatus"/>
	                 	              </select>
	              		</div>
	              		<label class="control-label col-sm-2">客户经理：</label>
	              		<div class="col-sm-2">
	              		  <input type="text" name="custManager" class="form-control"/>
	              		</div>
              		</div>
              		</form>
              	</div> 	 	
                </div>
              
              <div class="row">
                <div class="col-xs-12">
                      <table id="tbUniteCustomer" class="table table-striped table-hover" style="width:100%!important;">
                        <thead>
                          <tr>
                            <th>
                              	选择
                            </th>
                            <th>
                              	联保体协议编号
                            </th>
                            <th>
                              	联保体小组名称
                            </th>
                            <th>
                              	联保体类型
                            </th>
                            <th>
                              	起始日期
                            </th>
                            <th>
                              	到期日期
                            </th>
                            <th>
                              	客户经理
                            </th>
                            <th>
                              	成员数量
                            </th>
                            <th>
                              	保证方式
                            </th>
                            <th>
                              	联保体协议状态
                            </th>
                            <th width="125px">
                              	操作
                            </th>
                            <th>
                              	联保体质量
                            </th>
                          </tr>
                        </thead>
                        <tbody>
                        </tbody>
                      </table>
                </div>
              </div>
            </div>
            <!-- /.page-content -->
           
          </div>
          <!-- /.main-content -->
          <c:import url="../../../commons/footer.jsp" charEncoding="UTF-8" />
        </div>
        <!-- /.main-container -->
        <c:import url="../../../commons/post-include.jsp" charEncoding="UTF-8"/>
        <!-- page specific plugin scripts START -->
        <!-- page specific plugin scripts END -->
        <!-- inline scripts related to this page -->
        </script>
        <script>
          seajs.use('${ctx}/static/my/js/customer/uniteCustomer/main/main');
        </script>
       <!--  <script>
          seajs.use('${ctx}/static/my/js/bizapply/basicapply/main/main');
        </script> -->
        
<!-- 弹窗 -->
<div id="modalDiv" class="modal fade" tabindex="-1" role="basic"
    aria-hidden="true" style=";" data-backdrop="static">
      <div class="modal-dialog" style="width:500px;margin:30px auto\0;">
        <div class="modal-content">
          <form id="simpleForm" name="simpleForm" class="form-horizontal" >
            <div class="modal-header">
              <button type="button" class="close" data-dismiss="modal">
                &times;
              </button>
              <h4 class="blue bigger">
              </h4>
            </div>
            <div class="modal-body">
              <div class="row">
                <div class="col-xs-12">
                  <input type="hidden" id="form-field-0" name="id" />
                  <div class="form-group">
                    <label class="col-sm-4 control-label no-padding-right" for="unGuId">
                      <font color='red'>
                        *
                      </font>
                      	联保体协议编号：
                    </label>
                    <div class="col-sm-6">
                      <input type="text"  name="unGuId" class="form-control"
                      placeholder="请输入联保体协议编号" />
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-sm-4 control-label no-padding-right" for="unGroupName">
                      <font color='red'>
                        *
                      </font>
                     	  联保体小组名：
                    </label>
                    <div class="col-sm-6">
                      <input type="text" name="unGroupName"  class="form-control" placeholder="请输入联保体小组名称"/>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="modal-footer">
              <button id="add-simple-submit" class="btn btn-sm btn-primary" type="submit" 
              data-loading-text="正在提交中...">
                <i class="ace-icon fa fa-arrow-right">
                </i>
                	提交
              </button>
              <button class="btn btn-sm btn-default" data-dismiss="modal" type="button">
                <i class="ace-icon fa fa-times">
                </i>
               	 取消
              </button>
            </div>
          </form>
        </div>
      </div>
    </div>
      </body>
    
    </html>