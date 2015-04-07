<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<div class="row" style="margin-right: -5px;">
	<c:forEach items="${organs}" var="organ">
		<div class="col-sm-4 organ" style="">
   			<div class="thumbnail">
   				<div class="caption">
					<a href="<c:url value="/security/account/findAll/"/>${organ.id}"><h4>${organ.name}（<fmt:message key="organ.organization.account" />${organ.count}）</h4></a>
					<p><fmt:message key="organ.organization.organPrin" />${organ.principal}</p>
					<p><fmt:message key="organ.organization.organPrinTel" />${organ.prinTel}</p>
				</div>
   			</div>
   		</div>
	</c:forEach>
</div>