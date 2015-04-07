<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>
<%--@Author 甘焕--%>

<form role="form" class="form-horizontal" style="padding : 0px 30px;" action="<c:url value='/home/portal/copyView/' />${sourceId}">

	<div class="form-group">
		<label class="col-xs-3 control-label" for="targetId">
			<fmt:message key="home.portal.portalSelect" />
		</label>

		<div class="col-xs-9 form-field">
			<select id="targetId" name="targetId" class="form-control">
				<c:forEach items="${layouts}" var="layout">
					<option value="${layout.id}">${layout.typeName}</option>
				</c:forEach>
			</select>
		</div>
	</div>
	
</form>