<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="row">
		<div class="col-sx-12">
			<form id="form-doc-search" class="form-horizontal">
			
			 <div class="col-xs-12">
                <span>
                    	文件名：
                  	<input name="docName" type="text" placeholder="输入文档名称">
                  	  内容类型：
					<input name="docContentType" type="text" placeholder="输入内容类型">
                </span>
                <span style="float:right;" class="my-button-group">
                  	<button id="btn_doc_search" type="button" class="btn btn-sm btn-purple">
						<i class="ace-icon fa fa-search"></i> 查询
					</button>
					<button id="btn_doc_add" type="button" class="btn btn-sm btn-success">
						<i class="ace-icon fa fa-plus"></i> 新增
					</button> 
					<button type="button" class="btn btn-sm btn-yellow" role="pdownloadWd">
                            <i class="ace-icon fa fa-download">
                            </i>
                            	批量下载
                    </button>
                </span>
            </div>
			
			</form>
		</div>
</div>


<table id="tbWd" class="table table-striped table-hover">
	<thead>
		<tr>
			<th><input type="checkbox" id="wdcb" title='选取本页所有文档' />选择</th>
			<th>文档名称</th>
			<th>内容类型</th>
			<th>文档类型</th>
			<th>客户名称</th>
			<th>创建人</th>
			<th>创建时间</th>
			<th>关联方式</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
	</tbody>
</table>
