<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.coamc-tech.com/taglibs/datadict" prefix="dd" %>
<div id="side_bar_userinfo_modal" class="modal" tabindex="-1" data-backdrop="static">
	<div class="modal-dialog" style="width:70%">
		<form class="form-horizontal" action="" method="post" role="form"  id="user_setting_form">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 id="addr_form_title" class="blue bigger">
						<i class='ace-icon fa fa-edit'></i> 用户信息修改
					</h4>
				</div>
				<div class="modal-body">
					<h4 class="blue">
			          	登陆密码
			        </h4>
			        <div class="form-group">
						<label class="col-xs-12 col-sm-2 control-label no-padding-right">
							登陆名称<span class='red'></span>
						</label>
						<div class="col-xs-12 col-sm-3">
							<span class="block input-icon input-icon-right">
								<input type="text" id=""
								name="logname" readonly="readonly"
								class="form-control" value="" />
							</span>
						</div>
						<div class="help-block col-xs-12 col-sm-reset inline"></div>
						<div class="help-block col-xs-12 col-sm-reset inline"></div>
					</div>
					<div class="form-group">
						<label class="col-xs-12 col-sm-2 control-label no-padding-right">
							旧密码<span class='red'></span>
						</label>
						<div class="col-xs-12 col-sm-3">
							<span class="block input-icon input-icon-right">
								<input type="password" id="side_bar_user_setting_logpass" 
								name="logpass"
								class="form-control" value="" />
							</span>
						</div>
						<div class="help-block col-xs-12 col-sm-reset inline"></div>
						<div class="help-block col-xs-12 col-sm-reset inline"></div>
					</div>
					<div class="form-group">
						<label class="col-xs-12 col-sm-2 control-label no-padding-right">
							新密码<span class='red'></span>
						</label>
						<div class="col-xs-12 col-sm-3">
							<span class="block input-icon input-icon-right">
								<input type="password" id="side_bar_user_setting_new_pass" 
								name="newPass"
								class="form-control" value="" />
							</span>
						</div>
						<div class="help-block col-xs-12 col-sm-reset inline"></div>
						<div class="help-block col-xs-12 col-sm-reset inline"></div>
					</div>
					<div class="form-group">
						<label class="col-xs-12 col-sm-2 control-label no-padding-right">
							确认新密码<span class='red'></span>
						</label>
						<div class="col-xs-12 col-sm-3">
							<span class="block input-icon input-icon-right">
								<input type="password" id="side_bar_user_setting_repeat_pass" 
								name="repeatNewPass"
								class="form-control" value="" />
							</span>
						</div>
						<div class="help-block col-xs-12 col-sm-reset inline"></div>
						<div class="help-block col-xs-12 col-sm-reset inline"></div>
					</div>
					<hr/>
					<h4 class="blue">
			          	个人信息
			        </h4>
			        <div class="form-group">
						<label class="col-xs-12 col-sm-2 control-label no-padding-right">
							单位电话<span class='red'>*</span>
						</label>
						<div class="col-xs-12 col-sm-3">
							<span class="block input-icon input-icon-right">
								<input type="text" name="fixedphone" id="" 
								class="form-control" value="" />
							</span>
						</div>
						<div class="help-block col-xs-12 col-sm-reset inline"></div>
				    	<label class="col-xs-12 col-sm-2 control-label no-padding-right">
							手机号码<span class='red'>*</span>
						</label>
						<div class="col-xs-12 col-sm-3">
							<span class="block input-icon input-icon-right">
								<input type="text"  
								name="mobilephone"
								id="" class="form-control" 
								value="" />
							</span>
						</div>
						<div class="help-block col-xs-12 col-sm-reset inline"></div>
					</div>
					<div class="form-group">
						<label class="col-xs-12 col-sm-2 control-label no-padding-right">
							电子邮箱<span class='red'>*</span>
						</label>
						<div class="col-xs-12 col-sm-3">
							<span class="block input-icon input-icon-right">
								<input type="text" 
								name="email" id="" 
								class="form-control" value="" />
							</span>
						</div>
						<div class="help-block col-xs-12 col-sm-reset inline"></div>
				    	<label class="col-xs-12 col-sm-2 control-label no-padding-right">
							备注
						</label>
						<div class="col-xs-12 col-sm-3">
							<span class="block input-icon input-icon-right">
								<input type="text" 
								name="remarks" 
								id="" class="form-control" 
								value="" />
							</span>
						</div>
						<div class="help-block col-xs-12 col-sm-reset inline"></div>
					</div>
					<hr/>
				</div>
				<div class="modal-footer">
	             	<button class="btn btn-sm btn-success" 
	             	id="" 
	             	type="submit">
	                 	<i class="ace-icon fa fa-check"></i> 修改
	             	</button>
	             	<button type="button" class="btn btn-sm btn-default" data-dismiss="modal">
						<i class="ace-icon fa fa-times"></i> 取消
					</button>
				</div>
			</div>
		</form>
	</div>
</div>



