<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>

<link rel="stylesheet" href="<mh:theme code='jquery.fileupload.css'/>" media="all" />
<script type="text/javascript" src="<mh:theme code='jquery.fileupload.js'/>"></script>

<span class="btn fileinput-button btn-success btn-block">
    <i class="glyphicon glyphicon-plus"></i>
    <span>添加附件</span>
    <input id="fileupload" type="file" name="files[]" multiple>
</span>

<%--@Author 甘焕--%>
系统门户暂缺，
<a href='<c:url value="/login"/>'>点这里登陆</a>
<script type="text/javascript">
$(document).ready(function() {
	var url = '<c:url value="/gate/home/import"/>';
	$('#fileupload').fileupload({
        url: url,
        dataType: 'json',
        done: function (e, data) {
        	if(data.result.error) {
        		alert("出错了!");
        	}
        }
    }).prop('disabled', !$.support.fileInput)
	.parent().addClass($.support.fileInput ? undefined : 'disabled');
});
</script>