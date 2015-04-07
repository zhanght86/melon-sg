<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

 <c:choose>
	    <c:when test="${loggers.size()>0}">
<mh:section id="basic">
	<jsp:attribute name="title">
	</jsp:attribute>
	<jsp:attribute name="body">
		<table class="table table-bordered table-hover">
			<thead>
				<tr>
					<th></th>
					<th><fmt:message key="system.logger.function" /></th>
					<th><fmt:message key="system.logger.ip" /></th>
					<th><fmt:message key="system.logger.time" /></th>
					<th><fmt:message key="system.logger.clazz" /></th>
					<th><fmt:message key="system.logger.module" /></th>
					<th><fmt:message key="system.logger.result" /></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${loggers}" var="logger" varStatus="num">
					<tr>
						<td width="3%">${num.count}</td>
						<td width="20%">${logger.function}</td>
						<td width="22%">${logger.occurTime}</td>
						<td width="15%">${logger.ip}</td>
						<td width="15%">${logger.clazz}</td>
						<td width="13%">${logger.module}</td>
						<td width="12%">${logger.result}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</jsp:attribute>
</mh:section>
</c:when>
	<c:otherwise>
		<fmt:message key="system.logger.Notlist.hint" />
	</c:otherwise>
</c:choose>

