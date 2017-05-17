<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.coamc-tech.com/taglibs/datadict" prefix="dd" %>
<div id="input_addr_modal" class="modal" tabindex="-1" data-backdrop="static">
	<div class="modal-dialog" style="width:70%">
		<div class="modal-content">
			<form id="input_addr_form" class="form-horizontal">
				
				<input type="hidden" name="partyId" value=""/>
				<input type="hidden" name="addressId" value=""/>
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 id="addr_form_title" class="blue bigger">
						<i class='ace-icon fa fa-plus'></i> 新增
					</h4>
				</div>
				<div class="modal-body">
				
				<div class="row">
					<div class="col-md-12">
					<h4 class="blue">
           				地址
       				</h4>
			        <div class="form-group">
			        	<label class="col-xs-12 col-sm-2 control-label no-padding-right">
							国家：
						</label>
						<div class="col-xs-12 col-sm-3">
							<span class="block input-icon input-icon-right">
								<select id="nation" name="nationalityCd" class="form-control">
									<option value="">请选择</option>
			                    </select>
							</span>
						</div>
						<div class="help-block col-xs-12 col-sm-reset inline"></div>
						<label class="col-xs-12 col-sm-2 control-label no-padding-right">
							省份/自治区/直辖市：
						</label>
						<div class="col-xs-12 col-sm-3">
							<span class="block input-icon input-icon-right">
								<select id="province" name="provinceCd" class="form-control">
									<option value="">请选择</option>
			                    </select>
							</span>
						</div>
						<div class="help-block col-xs-12 col-sm-reset inline"></div>
					    
					</div>
					
					<div class="form-group">
						<label class="col-xs-12 col-sm-2 control-label no-padding-right">
							城市/自治州：
						</label>
						<div class="col-xs-12 col-sm-3">
							<span class="block input-icon input-icon-right">
							 	<select id="city" name="cityCd" class="form-control">
									<option value="">请选择</option>
			                    </select>
							</span>
						</div>
						<div class="help-block col-xs-12 col-sm-reset inline"></div>
						<label class="col-xs-12 col-sm-2 control-label no-padding-right">
							区/县：
						</label>
						<div class="col-xs-12 col-sm-3">
							<span class="block input-icon input-icon-right">
								<select id="county" name="countyCd"  class="form-control">
									<option value="">请选择</option>
			                    </select>
							</span>
						</div>
						<div class="help-block col-xs-12 col-sm-reset inline"></div>
					</div> 
					
					<div class="form-group">
						<label class="col-xs-12 col-sm-2 control-label no-padding-right">
							地址类型*：
						</label>
						<div class="col-xs-12 col-sm-3">
							<span class="block input-icon input-icon-right">
								<select name="addressTypeCd" class="form-control">
									<dd:options codeType="AddressType"/>
			                    </select>
							</span>
						</div>
						<div class="help-block col-xs-12 col-sm-reset inline"></div>
						<label class="col-xs-12 col-sm-2 control-label no-padding-right">
							街道地址*：
						</label>
						<div class="col-xs-12 col-sm-3">
							<span class="block input-icon input-icon-right">
								<input type="text" name="streetAddress"  class="form-control" 
								value="" />
							</span>
						</div>
						<div class="help-block col-xs-12 col-sm-reset inline"></div>
					</div>
					<div class="form-group">
				    	<label class="col-xs-12 col-sm-2 control-label no-padding-right">
							电话*：
						</label>
						<div class="col-xs-12 col-sm-3">
							<span class="block input-icon input-icon-right">
								<input type="text" name="telephone" class="form-control" 
								value="" />
							</span>
						</div>
						<div class="help-block col-xs-12 col-sm-reset inline"></div>
						<label class="col-xs-12 col-sm-2 control-label no-padding-right">
							邮编：
						</label>
						<div class="col-xs-12 col-sm-3">
							<span class="block input-icon input-icon-right">
								<input name="zipNum" type="text" class="form-control" 
								value="" />
							</span>
						</div>
						<div class="help-block col-xs-12 col-sm-reset inline"></div>
					</div>
					
					<div class="form-group">
						<label class="col-xs-12 col-sm-2 control-label no-padding-right">
							传真：
						</label>
						<div class="col-xs-12 col-sm-3">
							<span class="block input-icon input-icon-right">
								<input name="fax" type="text" class="form-control" 
								value="" />
							</span>
						</div>
						<div class="help-block col-xs-12 col-sm-reset inline"></div>
						<label class="col-xs-12 col-sm-2 control-label no-padding-right">
							网址：
						</label>
						<div class="col-xs-12 col-sm-3">
							<span class="block input-icon input-icon-right">
								<input name="website" type="text" class="form-control" 
								value="" />
							</span>
						</div>
						<div class="help-block col-xs-12 col-sm-reset inline"></div>
					</div>
					</div>
				</div>
				
				</div>
				<div class="modal-footer">
					<button type="submit" class="btn btn-sm btn-primary" >
						<i class="ace-icon fa fa-search"></i> 确定
					</button>
					<button type="button" class="btn btn-sm btn-default" data-dismiss="modal">
						<i class="ace-icon fa fa-times"></i> 取消
					</button>
				</div>
			</form>
		</div>
	</div>
</div>