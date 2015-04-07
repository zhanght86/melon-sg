<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>

<script type="text/javascript" src="<mh:theme code='jquery.vaildate.js'/>"></script>
<div class="page-edit">
	<div class="page-edit-header"></div>
	<div class="page-edit-body">
		<tiles:insertAttribute name="body"/>
	</div>
	<div class="page-edit-footer">
	</div>
</div>

<script type="text/javascript">
$(document).ready(function() {
	<%--为服务器的错误添加样式--%>
	$("label.control-error").each(function(index, item) {
		var formGroup = $(item).parents(".form-group");
		formGroup.addClass("has-error");
	});
});
</script>
