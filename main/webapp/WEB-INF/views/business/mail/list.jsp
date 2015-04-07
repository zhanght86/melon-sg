<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="hh" uri="http://www.legendsec.com/melon/helper" %>

<mh:section id="basic">

	<jsp:attribute name="title">
		发件箱
	</jsp:attribute>
	
	<jsp:attribute name="button">
		<button class="btn btn-default dropdown-toggle btn-sm" type="button" id="Menu1" data-toggle="dropdown">
			 	<fmt:message key="button.reply"/>
		    	<span class="caret"></span>
		    </button>
		    <ul class="dropdown-menu" role="menu" aria-labelledby="Menu1">
			    <li role="presentation"><a role="menuitem" tabindex="-1" href="#" class="btn btn-default btn-xs"><fmt:message key="button.reply"/></a></li>
			    <li role="presentation"><a role="menuitem" tabindex="-1" href="#" class="btn btn-default btn-xs"><fmt:message key="button.replyAll"/></a></li>
			</ul>
		    <mh:button href="#" class="btn btn-default btn-sm"><fmt:message key="button.forwarding"/></mh:button>
		    <mh:button href="#" class="btn btn-default btn-sm"><fmt:message key="button.setRead"/></mh:button>
		    <mh:button href="#" class="btn btn-default btn-sm"><fmt:message key="button.setRemind"/></mh:button>
	</jsp:attribute>
	
	<jsp:attribute name="body">
		<table class="table table-bordered table-hover">
			<thead>
				<tr>
					<th><fmt:message key="mail.sender" /></th>
					<th><fmt:message key="mail.title" /></th>
					<th><fmt:message key="mail.sendTime" /></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${mails}" var="mail" varStatus="ol">
					<tr>
						<td width="5%">${ol.count}</td>
						<td width="20%">${mail.username}</td>
						<td width="50%">${mail.title}</td>
						<td width="30%">${mail.sendTime}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</jsp:attribute>
</mh:section>

