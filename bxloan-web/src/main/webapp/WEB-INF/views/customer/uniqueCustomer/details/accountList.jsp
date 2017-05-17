<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
%>
    <!-- <h4 class="blue">
        账户管理
    </h4> -->
    <div class="space-8">
    </div>
    <div id="faq-list-2" class="panel-group accordion-style1 accordion-style2">
        <div class="step-pane active" id="step1">
            <div align="right" style="margin-left:45px;">
                <span id="cwglSpan">
                    <button class="btn btn-sm btn-success" id="addCw" type="button">
                        <i class="ace-icon fa fa-plus">
                        </i>
                        	新增
                    </button>
                </span>
            </div>
            <div class="row">
                 <table id="tbCw" class="table table-striped table-hover col-xs-12">
                     <thead>
                         <tr>
                             <th>
                                 	选择
                             </th>
                             <th>
                                 	户名
                             </th>
                             <th>
                                 	开户行
                             </th>
                             <th>
                                 	账号
                             </th>
                             <th>
                                 	预留手机
                             </th>
                             <th>
                                	          是否使用
                             </th>
                             <th>
                                 	操作
                             </th>
                         </tr>
                     </thead>
                     <tbody>
                     </tbody>
                 </table>
            </div>
            <!-- /.row -->
        </div>
    </div>
    
    <!-- 新增财务管理 -->
						<div id="add-modal-formCw" class="modal fade" tabindex="-1" role="basic"
						aria-hidden="true" style=";" data-backdrop="static">
							<div class="modal-dialog">
								<div class="modal-content">
									<form id="addAccountForm" class="form-horizontal" role="form" method="post">
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
														<label class="col-sm-2 control-label no-padding-right" for="accountName">
															<font color='red'>
																*
															</font>
															户名：
														</label>
														<div class="col-sm-8">
															<input type="text" id="accountName" name="accountName" class="form-control  "
															 />
														</div>
													</div>
													<div class="form-group">
														<label class="col-sm-2 control-label no-padding-right" for="bankAccount">
															<font color='red'>
																*
															</font>
															开户行：
														</label>
														<div class="col-sm-8">
															<input type="text" id="bankAccount" name="bankAccount" class="form-control  "
															 />
														</div>
													</div>
													<div class="form-group">
														<label class="col-sm-2 control-label no-padding-right" for="accountNum">
															<font color='red'>
																*
															</font>
															账号：
														</label>
														<div class="col-sm-8">
															<input type="text" id="accountNum" name="accountNum" class="form-control  "
															 />
															<!-- onkeyup="formatBankNo(this)" onkeydown="formatBankNo(this)" -->
														</div>
													</div>
													<div class="form-group">
														<label class="col-sm-2 control-label no-padding-right" for="accountStatus">
															<font color='red'>
																*
															</font>
															是否使用：
														</label>
														<div class="col-sm-8">
															<span class="block input-icon input-icon-right">
																<input name="accountStatus" type="radio" class="ace" value="1" />
																<span class="lbl">
																	使用
																</span>
																<input name="accountStatus" type="radio" class="ace" value="2" />
																<span class="lbl">
																	未使用
																</span>
															</span>
														</div>
													</div>
													<div class="form-group">
														<label class="col-sm-2 control-label no-padding-right" for="accountPhone">
															预留手机号：
														</label>
														<div class="col-sm-8">
															<input type="text" name="accountPhone" id="accountPhone" class="form-control  "
															 />
														</div>
													</div>
												</div>
											</div>
										</div>
										<div class="modal-footer">
											<button id="add-Cw-submit" class="btn btn-sm btn-primary" type="submit"
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
						<!-- 修改与查看财务管理 -->
						<div id="mod-modal-formCw" class="modal fade" tabindex="-1" role="basic"
						aria-hidden="true" style=";" data-backdrop="static">
							<div class="modal-dialog">
								<div class="modal-content">
									<form id="modAccountForm" class="form-horizontal" role="form" method="post">
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
														<label class="col-sm-2 control-label no-padding-right" for="accountName">
															<font color='red'>
																*
															</font>
															户名：
														</label>
														<div class="col-sm-8">
															<input type="text" id="accountName" name="accountName" class="form-control  "
															 />
														</div>
													</div>
													<div class="form-group">
														<label class="col-sm-2 control-label no-padding-right" for="bankAccount">
															<font color='red'>
																*
															</font>
															开户行：
														</label>
														<div class="col-sm-8">
															<input type="text" id="bankAccount" name="bankAccount" class="form-control  "
															 />
														</div>
													</div>
													<div class="form-group">
														<label class="col-sm-2 control-label no-padding-right" for="accountNum">
															<font color='red'>
																*
															</font>
															账号：
														</label>
														<div class="col-sm-8">
															<input type="text" id="accountNum" name="accountNum" class="form-control  "
															 />
														</div>
													</div>
													<div class="form-group">
														<label class="col-sm-2 control-label no-padding-right" for="accountStatus">
															<font color='red'>
																*
															</font>
															是否使用：
														</label>
														<div class="col-sm-8">
															<span class="block input-icon input-icon-right">
																<input name="accountStatus" type="radio" class="ace" value="1" />
																<span class="lbl">
																	使用
																</span>
																<input name="accountStatus" type="radio" class="ace" value="2" />
																<span class="lbl">
																	未使用
																</span>
															</span>
														</div>
													</div>
													<div class="form-group">
														<label class="col-sm-2 control-label no-padding-right" for="telephone">
															预留手机号：
														</label>
														<div class="col-sm-8">
															<input type="text" name="accountPhone" id="accountPhone" class="form-control  "
															 />
														</div>
													</div>
												</div>
											</div>
										</div>
										<div class="modal-footer">
											<span id=modCwSpan>
												<button id="mod-Cw-submit" class="btn btn-sm btn-primary" type="submit"
												data-loading-text="正在提交中...">
													<i class="ace-icon fa fa-arrow-right">
													</i>
													提交
												</button>
											</span>
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