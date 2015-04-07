<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>

<link rel="stylesheet" href="<mh:theme code='jquery.fileupload.css'/>" media="all" />
<script type="text/javascript" src="<mh:theme code='jquery.fileupload.js'/>"></script>


<%--
<!-- 明细弹框 -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="myModalLabel">设备明细</h4>
      </div>
      <div class="modal-body" id="deviceShowView">
      		<table class="">
      			
      		</table>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary">Save changes</button>
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>

<!-- 更新弹框 -->
<div class="modal fade" id="updateDialog" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="myModalLabel">设备明细</h4>
      </div>
      <div class="modal-body">
        		更新内容
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary">Save changes</button>
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>
 --%>

<span class="btn fileinput-button btn-success btn-block">
    <i class="glyphicon glyphicon-plus"></i>
    <span>添加附件</span>
    <input id="fileupload" type="file" name="files[]" multiple>
</span>
<a href="<c:url value="/statics/themes/default/devices/model/import_Devices.xls"/>">下载模板</a>
<%-- 
导入数据预览
<table id="tabDb" class="table table-bordered">
	<tr>
		<th>  </th>
		<th>名称</th>
		<th>编码</th>
		<th>所属单位</th>
		<th>所属安全域</th>
		<th>设备类型</th>
		<th>操作</th>
	</tr>
</table>

<a id="btnBack" class="btn btn-default" href="javascript:void(0);" style="float:right;">
	<fmt:message key="button.cancel"/>
</a>
<a id="btnBack" class="btn btn-default" href="javascript:void(0);" style="float:right;">
	<fmt:message key="button.sure"/>
</a>
--%>


<script type="text/javascript">
$(document).ready(function() {
	
	var importUrl = '<c:url value="/asset/device/import"/>',
		url = '<c:url value="/asset/device/list"/>',
		res = null,
		impRes = null;
		
	//上传附件
	$('#fileupload').fileupload({
        url: importUrl,
        dataType: 'json',
        done: function (e, data) {
        	res = data.result;
        	if(res.result) {
        		Dialog.warn("<fmt:message key='asset.imp.errorDb'/>");
        	}else{
        		impRes = res.impRes;
        		Dialog.warn("导入数据成功"+impRes.correctNumber+"条,失败"+impRes.errorNumber+"条");
        		window.location.href = url;
        	}
        }
    }).prop('disabled', !$.support.fileInput)
	.parent().addClass($.support.fileInput ? undefined : 'disabled');
	
});
</script>