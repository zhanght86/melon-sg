<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>
<%--@Author 甘焕--%>
<sf:form modelAttribute="layout" role="form" cssClass="form-horizontal" style="padding : 0px 30px;">

	<sf:hidden path="id" />
	<sf:hidden path="order" />
	<sf:hidden path="userId" />
	
	<div class="form-group">
		<sf:label path="typeName" cssClass="col-xs-3 control-label">
			<fmt:message key="hint.required" />
			<fmt:message key="home.portal.typeName" />
		</sf:label>

		<div class="col-xs-9 form-field">
			<sf:input path="typeName" cssClass="form-control" maxlength="20" />
			<sf:errors path="typeName" />
		</div>
	</div>
	
	<div class="form-group">
		<sf:label path="defaultHome" cssClass="col-xs-3 control-label">
			<fmt:message key="home.portal.defaultHome" />
		</sf:label>

		<div class="col-xs-9 form-field">
			<mh:dictionary var="boolenums" key="bool"/>
			<sf:radiobuttons path="defaultHome" items="${boolenums}"/>
			<label class="form-hint"><fmt:message key="home.portal.defaultHome.hint" /></label>
		</div>
	</div>
	
	<div class="form-group">
		<sf:label path="allUser" cssClass="col-xs-3 control-label">
			<fmt:message key="home.portal.allUser" />
		</sf:label>

		<div class="col-xs-9 form-field">
			<mh:dictionary var="boolenums" key="bool"/>
			<sf:radiobuttons path="allUser" items="${boolenums}"/>
		</div>
	</div>
	
	<div class="form-group">
		<sf:label path="leftToRight" cssClass="col-xs-3 control-label">
			<fmt:message key="home.portal.leftToRight" />
		</sf:label>

		<div class="col-xs-9 form-field">
			<mh:dictionary var="ltrenums" key="leftToRight"/>
			<sf:radiobuttons path="leftToRight" items="${ltrenums}"/>
		</div>
	</div>
	
	<div class="form-group">
		<sf:label path="remarks" cssClass="col-xs-3 control-label">
			<fmt:message key="home.portal.remarks"/>
		</sf:label>
		
		<div class="col-xs-9 form-field">
			<sf:textarea path="remarks" cssClass="form-control"  rows="3" maxlength="500"/>
			<sf:errors path="remarks"/>
		</div>
	</div>
	
</sf:form>
<script type="text/javascript" src="<mh:theme code='jquery.vaildate.js'/>"></script>
<script type="text/javascript">
var layoutForm = $("#layout");
$(document).ready(function() {
	layoutForm.validate({
		rules : {
			typeName : {
				required : true
		}
		}
	});
	
});
</script>