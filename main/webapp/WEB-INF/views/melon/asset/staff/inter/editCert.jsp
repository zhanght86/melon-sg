<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>
<%@ taglib prefix="hh" uri="http://www.legendsec.com/melon/helper"%>

<input type="hidden" id="cert_checked" name="cert_checked"/>
<table class="table">
	<tbody id="certrows">
		<c:forEach items="${interStaff.certs}" var="cert" varStatus="ol">
			<tr>
				<td width="10%">
					<c:choose>
						<c:when test="${empty cert.endDate}">
							<input type="checkbox" class="check_show" name="cert_select" value="${ol.count}" />
						</c:when>
						<c:otherwise>
							<input type="checkbox" class="check_show" checked="checked" name="cert_select" value="${ol.count}" />
						</c:otherwise>
					</c:choose>
				</td>
				<td width="30%">
					<input type="text" id="certificate${ol.count}" name="certificate${ol.count}" value="${cert.certificate}" readonly="readonly"/></td>
				<td width="30%">
					<input type="text" id="endDate${ol.count}"  name="endDate${ol.count}" value="${cert.endDate}"/>
				</td>
				<td width="30%">
					<div class="certificates-upload">
						<input type="hidden" id="business${ol.count}" name="business${ol.count}" value="${cert.certificateBusiness}" />
						<mh:upload domainId="${cert.certificateBusiness}" />
					</div>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
