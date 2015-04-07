<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>
<style type="text/css">
	#quickNote .form-group{
		margin-bottom:15px;
		border-bottom:0px;
	}

</style>

<%--@Author 甘焕--%>
<sf:form modelAttribute="quickNote" role="form" cssClass="form-horizontal" style="padding : 0px 5px;">

		<sf:hidden path="id" />
		<div class="form-group">
			<sf:label path="level" cssClass="col-xs-3 control-label">
				<fmt:message key="system.quick.level" />
			</sf:label>
			<div class="col-xs-9 form-field">
				<div id="levelRating" class="rating star-rating"  data-rating-max="5" data-val="3"></div>
				<sf:hidden path="level"/>
				<sf:errors path="level" />
			</div>
		</div>
		
		<div class="form-group">
			<sf:label path="remarks" cssClass="col-xs-3 control-label">
				<fmt:message key="hint.required" />
				<fmt:message key="system.quick.remarks"/>
			</sf:label>
			<div class="col-xs-9 form-field">
				<sf:textarea path="remarks" cssClass="form-control"  rows="3" maxlength="150"/>
				<sf:errors path="remarks"/>
			</div>
		</div>
</sf:form>