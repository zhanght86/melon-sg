<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>
<%--@Author 甘焕--%>

<sf:form modelAttribute="view" role="form" cssClass="form-horizontal" style="padding : 0px 30px;">

	<sf:hidden path="id" />
	
	<sf:hidden path="layout.id"/>
	<sf:hidden path="xpos"/>
	<sf:hidden path="ypos"/>
	
	<div class="form-group">
		<sf:label path="menuId" cssClass="col-xs-3 control-label">
			<fmt:message key="home.portal.menuId" />
		</sf:label>

		<div class="col-xs-9 form-field">
			<sf:select path="menuId" cssClass="form-control" items="${menus}" itemLabel="shortName" itemValue="id" >
			</sf:select>
		</div>
	</div>
	
	<div class="form-group">
		<sf:label path="width" cssClass="col-xs-3 control-label">
			<fmt:message key="home.portal.width" />
		</sf:label>

		<div class="col-xs-9 form-field">
			<sf:select path="width" cssClass="form-control">
				<c:forEach begin="1" end="12" var="num">
					<sf:option value="${num}" label="${num}"/>
				</c:forEach>
			</sf:select>
		</div>
	</div>
	
	<div class="form-group">
		<sf:label path="height" cssClass="col-xs-3 control-label">
			<fmt:message key="home.portal.height" />
		</sf:label>

		<div class="col-xs-9 form-field">
			<sf:select path="height" cssClass="form-control">
				<c:forEach begin="1" end="12" var="num">
					<sf:option value="${num}" label="${num}"/>
				</c:forEach>
			</sf:select>
		</div>
	</div>
	
</sf:form>