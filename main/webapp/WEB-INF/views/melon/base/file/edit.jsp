<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sp" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html" %>
<%-- <script type="text/javascript" src="<mh:theme code='ckeditor.js'/>"></script> --%>
<c:forEach items="${files}" var="file">
	<a href="<c:url value="/system/file/download/${file.id}"/>">${file.name}</a>
</c:forEach>

<%-- 单独的表单必须嵌入到此样式标签里 --%>
<div class="col-xs-8 col-xs-offset-2 form-singlon">
	<sf:form modelAttribute="meFile" role="form" cssClass="form-horizontal" enctype="multipart/form-data">

	<sf:hidden path="id"/>
	
	<sf:hidden path="businessId"/>
	
	<sf:hidden path="extension"/>
	
	<sf:hidden path="userId"/>
	
	<div class="form-group">
		<sf:label path="fileName" cssClass="col-xs-3 control-label">
			<fmt:message key="system.file.fileName"/>
		</sf:label>
		
		<div class="col-xs-9 form-field">
			<sf:input path="fileName" cssClass="form-control" maxlength="30"/>
			<label class="form-hint"><fmt:message key="system.file.fileName.hint"/></label> 
			<sf:errors path="fileName"/>
		</div>
	</div>
	
	<div class="form-group">
		<sf:label path="secured" cssClass="col-xs-3 control-label">
			<fmt:message key="system.file.secured"/>
		</sf:label>
		
		<div class="col-xs-9 form-field">
			<mh:dictionary var="boolDicts" key="bool"/>
			<sf:radiobuttons path="secured" items="${boolDicts}" />
			<label class="form-hint"><fmt:message key="system.file.secured.hint"/></label> 
			<sf:errors path="secured"/>
		</div>
	</div>
	
	<div class="form-group">
		<sf:label path="username" cssClass="col-xs-3 control-label">
			<fmt:message key="system.file.username"/>
		</sf:label>
		
		<div class="col-xs-9 form-field">
			<sf:input path="username" cssClass="form-control" maxlength="30"/>
			<sf:errors path="username"/>
		</div>
	</div>
	
	<div class="form-group">
		<sf:label path="file" cssClass="col-xs-3 control-label">
			<fmt:message key="hint.required"/>
			<fmt:message key="system.file.file"/>
		</sf:label>
		
		<div class="col-xs-9 form-field">
			<sf:input path="file" type="file" cssClass="form-control" />
			<sf:errors path="file"/>
		</div>
	</div>
	
	<div class="form-group form-button-panel">
		<div class="col-xs-9 col-xs-offset-3 form-buttons">
			<mh:button class="btn btn-primary" id="btnSave"><fmt:message key="button.save"/></mh:button>
			<mh:button class="btn btn-default" href="system/file/list"><fmt:message key="button.backList"/></mh:button>
		</div>
	</div>
	</sf:form>
</div>
<script type="text/javascript">
$(document).ready(function() {
	var fileForm = $("#meFile");
	fileForm.validate({
		rules : {
			file : {
					required : true,
				}
		 	}
	});	
	//
	$("#btnSave").on("click", function(event) {
        if(fileForm.valid()){
        	fileForm.submit();
        }
		//阻止默认的提交动作
        event.preventDefault();
	});
	//ckeditor = CKEDITOR.replace("content");
});
</script> 