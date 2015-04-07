<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="hh" uri="http://www.legendsec.com/melon/helper" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<mh:section id="basic">

	<jsp:attribute name="title">
		<fmt:message key="asset.infoSystem.organInfo">
		    <fmt:param><strong>${infoSystems[0].organName}</strong></fmt:param>
		    <fmt:param><label style="color:#45a6d6">${fn:length(infoSystems)}</label></fmt:param>
		</fmt:message>
		<a href="<c:url value='/asset/infoSystem/export?organId=${infoSystems[0].organId }'/>" class = "btn btn-primary" style="float:right;">
			<fmt:message key="button.export"/>	
		</a>
	</jsp:attribute>
	
	
	<jsp:attribute name="body">
		<%--czj明细表格 --%>
		<table class="table table-bordered table-hover">
			<thead>
				<tr>
					<th><fmt:message key="security.account.num" /></th>
					<th><fmt:message key="asset.abstract.name" /></th>
					<th><fmt:message key="asset.abstract.code" /></th>
					<th><fmt:message key="asset.abstract.chargeName" /></th>
					<th><fmt:message key="asset.abstract.organName" /></th>
					<th><fmt:message key="asset.abstract.using" /></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${infoSystems}" var="info" varStatus="num">
					<tr>
						<td width="5%">${num.count}</td>
						<td width="20%">${info.name}</td>
						<td width="20%">${info.code}</td>
						<td width="15%">${info.chargeName}</td>
						<td width="20%">${info.organName}</td>
						<td width="20%">
							<mh:dictionary key="usingState" value="${info.using}"/>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</jsp:attribute>
</mh:section>
