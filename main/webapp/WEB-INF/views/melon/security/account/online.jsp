<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="hh" uri="http://www.legendsec.com/melon/helper" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<mh:section id="basic">

	<jsp:attribute name="title">
		<fmt:message key="security.account.online">
		    <fmt:param><label style="color:#45a6d6">${users}</label></fmt:param>
		</fmt:message>
		<%-- <a href='<c:url value="/security/account/export?organId=${organId}"/>' class="btn btn-primary" style="float:right;" ><fmt:message key="button.export"/></a> --%>
		<a href='<c:url value="/security/account/listOrgans"/>' class="btn btn-default" style="float:right;"><fmt:message key="button.back"/></a>
	</jsp:attribute>
	
	<jsp:attribute name="body">
		<table class="table table-bordered table-hover">
			<thead>
				<tr>
					<th><fmt:message key="security.account.num" /></th>
					<th><fmt:message key="security.account.name" /></th>
					<th><fmt:message key="security.account.companyName" /></th>
					<th><fmt:message key="security.account.departName" /></th>
					<th><fmt:message key="security.account.username" /></th>
					<th><fmt:message key="security.account.code" /></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${onlines}" var="account" varStatus="ol">
					<tr>
						<td width="5%">${ol.count}</td>
						<td width="15%"><a href='<c:url value="/security/account/show/"/>${account.id}'>${account.name}</a></td>
						<td width="25%">${account.companyName}</td>
						<td width="25%">${account.departName}</td>
						<td width="20%">${account.username}</td>
						<td width="10%">${account.code}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</jsp:attribute>
</mh:section>

