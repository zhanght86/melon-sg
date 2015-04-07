<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>


<mh:section id="basic">

	<jsp:attribute name="title">
	
	</jsp:attribute>
	
	
	<jsp:attribute name="body">
		<table class="table table-bordered">
			<thead>
			 	<tr>
					<th><fmt:message key="system.quick.level"/></th>
					<th><fmt:message key="system.quick.userName"/></th>
					<th><fmt:message key="system.quick.remarks"/></th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${quickNotes}" var="listNote"  varStatus="ol">
			<tr>
				<td width="15%">
					<c:choose>
						<c:when test="${listNote.level==1}">正常</c:when>
						<c:when test="${listNote.level==2}">一般</c:when>
						<c:when test="${listNote.level==3}">重要</c:when>
						<c:when test="${listNote.level==4}">紧急</c:when>
						<c:when test="${listNote.level==5}">非常紧急</c:when>
					</c:choose>
				</td>
				<td width="70%">${listNote.remarks}</td>
				<td width="15%">${listNote.creator.username}</td>
			</tr>
			</c:forEach>
			</tbody>
		</table>
	</jsp:attribute>
</mh:section>
