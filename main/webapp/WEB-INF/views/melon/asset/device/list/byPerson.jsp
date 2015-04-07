<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="hh" uri="http://www.legendsec.com/melon/helper" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:choose>
	<c:when test="${fn:length(devices)>0}">
		<mh:section id="basic">
			<jsp:attribute name="title">
				<fmt:message key="asset.device.list.person">
				    <fmt:param>${devices[0].chargeName}</fmt:param>
				    <fmt:param>
						<label style="color: #45a6d6"> ${fn:length(devices)}</label>
					</fmt:param>
				</fmt:message>
				<a href="<c:url value='/asset/device/export?person=${devices[0].chargerId}'/>" class="btn btn-primary" style="float: right;">						
					<fmt:message key="button.export" />
				</a>
			</jsp:attribute>
			<jsp:attribute name="body">
				<table class="table table-bordered table-hover">
					<thead>
						<tr>
							<th>#</th>
							<th><fmt:message key="asset.abstract.name" /></th>
							<th><fmt:message key="asset.abstract.code" /></th>
							<th><fmt:message key="asset.abstract.chargeName" /></th>
							<th><fmt:message key="asset.abstract.organName" /></th>
							<th><fmt:message key="asset.abstract.using" /></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${devices}" var="device" varStatus="num">
							<tr>
								<td width="5%">${num.count}</td>
								<td width="20%">
									<a class="jqrow-action jqrow-action-show" href="<c:url value='/asset/device/show/${device.id}'/>">
										${device.name}
									</a>
								</td>
								<td width="20%">${device.code}</td>
								<td width="20%">${device.chargeName}</td>
								<td width="20%">${device.organName}</td>
								<td width="20%">
									<mh:dictionary key="usingState" value="${device.using}"/>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</jsp:attribute>
		</mh:section>
	</c:when>
	<c:otherwise>
		<fmt:message key="asset.byPerson.Notlist.hint" />
	</c:otherwise>
</c:choose>

