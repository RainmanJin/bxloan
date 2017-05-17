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
        </script>
      </head>
      
      <body class="no-skin">
        <c:import url="../../../commons/navbar.jsp" charEncoding="UTF-8" />
        <div class="main-container" id="main-container">
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
                  	单一客户
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
              <!-- 隐藏域 -->
              <div>
                <input type="hidden" id="customerIdField" />
                <input type="hidden" id="error" value="${error }">
                <input type="hidden" name="errorDateMessage" value="${requestScope.errorDateMessage}"/>
                <input type="hidden" name="errorDateMessage" value="${errorDateMessage}"/>
              </div>
              <%-- <c:import url="../../../commons/settings.jsp" charEncoding="UTF-8"
              />
              --%>
              <div class="page-header">
                <h1>
                  	单一客户
                  <small>
                    <i class="ace-icon fa fa-angle-double-right">
                    </i>
                    	客户列表
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
                     	 创建个人客户
                    </button>
                    <button id="show-createEntcus" class="btn btn-sm btn-success">
                      <i class="ace-icon fa fa-plus">
                      </i>
                      	创建企业客户
                    </button>
                  </span>
                </h1>
              </div>
              <!-- /.page-header -->
              <!-- 条件搜索 -->
              <div class="row" id="condSearchDiv" style="margin:auto;height:40px;">
                <form id="exec_search_form">
<!--                   <span> -->
<!--                     	客户名称： -->
<!--                     <input id="customerName" class="" style="height:32px;" name="customerName" placeholder="输入客户名称"/> -->
<!--                     	证件类型： -->
<!--                     <select name="certificateType" id="certificateType" style="height:32px"> -->
<!--                       <option value="">全部</option> -->
<!--                       <dd:options codeType="CertificateType" /> -->
<!--                     </select> -->
<!--                     	证件号码： -->
<!--                     <input id="certificateCode" style="height:32px;" name="certificateCode" -->
<!--                     placeholder="输入证件号码" /> -->
                    <!-- 借款人/担保人： -->
                    <%-- <select name="markType" id="markType" disabled="disabled">
                      <dd:options codeType="CustomerMarkType" />
                      </select>
                      --%>
<!--                   </span> -->
                  <div class="form-group">
						<label for="customerCd" class="control-label col-sm-1">客户名称：</label>
						<div class="col-sm-2">
							<input type="text" id="customerName" class="form-control col-sm-5 input-sm" placeholder="请输入客户名称" name="customerName">
						</div>
						<label for="custMng" class="control-label col-sm-1">客户类型：</label>
						<div class="col-sm-2">
							<select id="customerType" name="customerType" class="form-control col-sm-5 input-sm">
								<option value="">全部</option>
								<dd:options codeType="CustomerType" codeVals="1,2"/>
							</select>
						</div>
						<label for="contId" class="control-label col-sm-1">证件类型：</label>
						<div class="col-sm-2">
							<select name="certificateType" id="certificateType" class="form-control col-sm-5 input-sm" placeholder="客户类型">
								<option value="">全部</option>
								<dd:options codeType="CertificateType" />
							</select>
						</div>
						<label for="contId" class="control-label col-sm-1">证件号码：</label>
						<div class="col-sm-2">
							<input type="text" id="certificateCode" name="certificateCode" class="form-control col-sm-5 input-sm" placeholder="请输入证件号码" >
						</div>
					</div>
                </form>
              </div>
              <div class="row">
                <div class="col-xs-12">
                  <div class="row">
                    <div class="col-xs-12">
                      <table id="tbCustomers" class="table table-striped table-hover" style="width:100%!important;">
                        <thead>
                          <tr>
                            <th>
                              	选择
                            </th>
                            <th width="9%">
                              	客户编号
                            </th>
                            <th>
                              	客户名称
                            </th>
                            <th>
                              	客户类型
                            </th>
                            <th>
                              	证件类型
                            </th>
                            <th width="8%">
                              	证件号码
                            </th>
                            <th>
                              	状态
                            </th>
                            <th>
                              	客户经理
                            </th>
                            <th>
                              	创建日期
                            </th>
                            <th width="8%">
                              	借款人/担保人
                            </th>
                            <th width="13%">
                              	操作
                            </th>
                            <th>
                            	客户权限
                            </th>
                          </tr>
                        </thead>
                        <tbody>
                        </tbody>
                      </table>
                    </div>
                  </div>
                  
                  <!-- /.row -->
                  <div id="add-modal-form" class="modal fade" tabindex="-1" role="basic"
                  aria-hidden="true" style=";" data-backdrop="static">
                    <div class="modal-dialog" style="width:500px;margin:30px auto\0;">
                      <div class="modal-content">
                        <form id="addCustomerSimpleForm" name="addCustomerSimpleForm" action="singleCustomer/saveSimple"
                        class="form-horizontal" role="form" method="post">
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
                                  <label class="col-sm-3 control-label no-padding-right" for="addCustomerName">
                                    <font color='red'>
                                      *
                                    </font>
                                    	客户名称：
                                  </label>
                                  <div class="col-sm-7">
                                    <input type="text" id="addCustomerName" name="customerName" class="form-control"
                                    placeholder="输入名称" />
                                  </div>
                                </div>
                                <div class="form-group">
                                  <label class="col-sm-3 control-label no-padding-right" for="addCertificateType">
                                    <font color='red'>
                                      *
                                    </font>
                                   	 证件类型：
                                  </label>
                                  <div class="col-sm-7">
                                    <select name="certificateTypeCd" id="addCertificateType" class="form-control">
                                      <dd:options codeType="PCertificateType" />
                                    </select>
                                  </div>
                                </div>
                                <div class="form-group">
                                  <label class="col-sm-3 control-label no-padding-right" for="addCertificateNum">
                                    <font color='red'>
                                      *
                                    </font>
                                    	证件号码：
                                  </label>
                                  <div class="col-sm-7">
                                    <input type="text" id="addCertificateNum" name="certificateNum" class="form-control"
                                    placeholder="输入号码" />
                                  </div>
                                </div>
                                <div class="form-group">
                                  <label class="col-sm-3 control-label no-padding-right" for="addCertificateType">
                                    <font color='red'>
                                      *
                                    </font>
                                    	雇佣类型：
                                  </label>
                                  <div class="col-sm-7">
                                    <select name="employmentType" id="addEmploymentType" class="form-control">
                                      <dd:options codeType="EmploymentType" />
                                    </select>
                                  </div>
                                </div>
                              </div>
                            </div>
                          </div>
                          <div class="modal-footer">
                            <button id="add-simple-submit" class="btn btn-sm btn-primary" type="submit"
                            data-loading-text="正在保存中...">
                              <i class="ace-icon fa fa-save">
                              </i>
                              	保存
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
                  <div id="select_product_modal" class="modal" data-backdrop="static" tabindex="-1">
	                  <form id="select_product_form">
		                    <div class="modal-dialog">
		                      <div class="modal-content">
		                        <div class="modal-header" style="height: 70px;">
		                          <button type="button" class="close" data-dismiss="modal">
		                            &times;
		                          </button>
		                          <h4 class="blue bigger">
		                          </h4>
		                        </div>
		                        <div class="modal-body" style="margin-left: 10px;">
		                        	<table style="width: 100%;line-height: 25px;">
		                        		<tr>
		                        			<td colspan="2">
		                        				<span class="block input-icon input-icon-right">
		                        					业务类型：
													<label class="radio-inline" style="margin-left: 0px;">
														<input class="ace add_corp_Type form-control" type="radio" value="Biz" name="bizType">
														<span class="lbl"> 普通业务</span>
													</label>
													<label class="radio-inline" style="margin-left: 0px;">
														<input class="ace add_corp_Type form-control" type="radio" value="CreditBiz" name="bizType">
														<span class="lbl"> 授信业务</span>
													</label>
													<!-- <label class="radio-inline" style="margin-left: 0px;">
														<input class="ace add_corp_Type form-control" type="radio" value="batch" name="bizType">
														<span class="lbl"> 批量</span>
													</label> -->
												  </span>
		                        			</td>
		                        		</tr>
		                        		<tr>
		                        			<td width="40%">
		                        				<div id="tree" class="ztree"></div>
						                          <input type="hidden" class="form-control required" name="productCd" id="productCd">
						                          <input type="hidden" id="parentProductCd">
		                        			</td>
		                        			<td width="60%">
		                        				<span id="productDesc" style="border: 0px;color: red;">
		                        				</span>
		                        			</td>
		                        		</tr>
		                        	</table>
		                          
		                        </div>
		                        <div class="modal-footer">
		                          <button id="select_product_sure" class="btn btn-sm btn-primary">
		                            <i class="ace-icon fa fa-save">
		                            </i>
		                           	保存
		                          </button>
		                          <button class="btn btn-sm" data-dismiss="modal">
		                            <i class="ace-icon fa fa-times">
		                            </i>
		                           	 取消
		                          </button>
		                        </div>
		                      </div>
		                    </div>
	                    </form>
                  </div>
                  <%--创建弹窗 --%>
                    <div id="model-create" class="modal fade" tabindex="-1" data-backdrop="static">
                      <div class="modal-dialog" style="width:500px;margin:30px auto\0;">
                        <div class="modal-content">
                          <form id="createEntForm" name="createEntForm" action="userMng/addEntCustomer"
                          class="form-horizontal" role="form" method="post">
                            <div class="modal-header">
                              <button type="button" class="close" data-dismiss="modal">
                                &times;
                              </button>
                              <h4 class="blue bigger">
                                <i class='ace-icon fa fa-search'>
                                </i>
                                	创建
                              </h4>
                            </div>
                            <div class="modal-body">
                              <div class="row">
                                <div class="col-xs-12">
                                  <div class="form-group">
                                    <label class="col-xs-3 control-label no-padding-right" for="customerName">
                                     <font color='red'>
                                      *
                                     </font>
                                      	客户名称
                                    </label>
                                    <div class="col-xs-7">
                                      <input type="text" id="customerName" name="customerName" class="form-control required"
                                      />
                                    </div>
                                  </div>
                                  <div class="form-group">
                                    <label class="col-xs-3 control-label no-padding-right" for="certificateNum">
                                     <font color='red'>
                                      *
                                     </font>
                                      	组织机构代码
                                    </label>
                                    <div class="col-xs-7">
                                      <input type="text" id="certificateNum" name="certificateNum" class="form-control required"
                                      />
                                    </div>
                                  </div>
                                  <div class="form-group">
                                    <label class="col-xs-3 control-label no-padding-right" for="businessLicenseNum">
                                     <font color='red'>
                                      *
                                     </font>
                                      	营业执照
                                    </label>
                                    <div class="col-xs-7">
                                      <input type="text" id="businessLicenseNum" name="businessLicenseNum" class="form-control required"
                                      />
                                    </div>
                                  </div>
                                </div>
                              </div>
                            </div>
                            <div class="modal-footer">
                              <button class="btn btn-sm btn-primary submit" id="createEntForm-submit"
                              type="submit" data-loading-text="正在保存中...">
                                <i class="ace-icon fa fa-save">
                                </i>
                                	保存
                              </button>
                              <button class="btn btn-sm btn-default" data-dismiss="modal">
                                <i class="ace-icon fa fa-times">
                                </i>
                                	取消
                              </button>
                            </div>
                          </form>
                        </div>
                      </div>
                    </div>
                    <%--创建弹窗 END--%>
                </div>
              </div>
              <!-- <div class="row" style="margin:auto;height:40px;" align="right">
              <span>
              <button id="btn-mod" class="btn btn-sm btn-info">
              <i class="ace-icon fa fa-edit"></i>
              	修改
              </button>
              <button id="btn-detail" class="btn btn-sm btn-yellow">
              <i class="ace-icon fa fa-eye"></i>
              	查看
              </button>
              <button id="btn-biz" class="btn btn-sm btn-info">
              <i class="ace-icon fa fa-send"></i>
              	发起新业务
              </button>
              </span>
              </div> -->
            </div>
            <!-- /.page-content -->
          </div>
          <!-- /.main-content -->
          <c:import url="../../../commons/footer.jsp" charEncoding="UTF-8" />
        </div>
        <!-- /.main-container -->
        <c:import url="../../../commons/post-include.jsp" charEncoding="UTF-8"
        />
        <!-- page specific plugin scripts START -->
        <!-- page specific plugin scripts END -->
        <!-- inline scripts related to this page -->
        </script>
        <script>
          seajs.use('${ctx}/static/my/js/customer/uniqueCustomer/main/main');
        </script>
       <!--  <script>
          seajs.use('${ctx}/static/my/js/bizapply/basicapply/main/main');
        </script> -->
      </body>
    
    </html>