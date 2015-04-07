<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>

<mh:section id="basic">

	<jsp:attribute name="title">
		<fmt:message key="alarm.type.root"/>
	</jsp:attribute>
	
	<jsp:attribute name="remarks">
		<div class="alert alert-info">
			<fmt:message key="alarm.type.root.title" />
		</div>
	</jsp:attribute>
	
	<jsp:attribute name="body">
		<fmt:message key="alarm.type.root.description"/>
	</jsp:attribute>
	
</mh:section>