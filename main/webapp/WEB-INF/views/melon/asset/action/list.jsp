<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>
<%@ taglib prefix="hh" uri="http://www.legendsec.com/melon/helper"%>

<mh:section id="basic">

	<jsp:attribute name="title">
	
	</jsp:attribute>
	
	
	<jsp:attribute name="body">
		<table class="table table-bordered table-hover">
			<thead>
				<tr>
					<th></th>
					<th><fmt:message key="asset.aciton.userName" /></th>
					<th><fmt:message key="asset.aciton.organName" /></th>
					<th><fmt:message key="asset.aciton.type" /></th>
					<th><fmt:message key="asset.aciton.actTime" /></th>
					<th><fmt:message key="asset.aciton.remarks" /></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${actions}" var="action" varStatus="num">
					<tr>
						<td width="5%">${num.count}</td>
						<td width="15%">${action.actor.username }</td>
						<td width="20%">${action.actor.organName }</td>
						<td width="20%">
							<mh:dictionary key="usingState" value="${action.type}"/>
						</td>
						<td width="20%">${action.actTime}</td>
						<td width="20%">${action.remarks }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</jsp:attribute>
</mh:section>
