<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>
	
	<table class="table table-bordered">
		<tr>
			<td><fmt:message key="security.account.name"/></td>
			<td><fmt:message key="security.account.duty"/></td>
			<td><fmt:message key="security.account.companyName"/></td>
		</tr>
		<c:forEach var="list" items="${atts}">
		<tr>
			<td>${list.name}</td>
			<td>${list.duty}</td>
			<td>${list.companyName}</td>
		</tr>
		</c:forEach>
	</table>