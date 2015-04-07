<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>

<div class="col-page-title">
	<fmt:message key="asset.assetField.preview" />
</div>

<form id="preview" role="form" class="form-horizontal" action="<c:url value='/asset/field/saveOrUpdate'/>" method="POST">
	
	<jsp:include page="/WEB-INF/views/melon/asset/field/load.jsp"/>
	
	<div class="form-group form-button-panel">
		<div class="col-xs-9 col-xs-offset-3 form-buttons">
			<button type="button" class="btn btn-primary" id="btnBack"><fmt:message key="button.back"/></button>
		</div>
	</div>
</form>
<script type="text/javascript">
$(document).ready(function() {
	$("#btnBack").click(function() {
		fieldsGroup.load(groupUrl + node.id);
	})
	//$("#btnSave").on("click")
});  
</script>