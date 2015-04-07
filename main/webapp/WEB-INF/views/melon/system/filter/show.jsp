<%
	/**
     * @since 1.0 2013-3-18,下午7:53:15
     *
     * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
     * @version  1.0
     */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html" %>

<%-- 单独的表单必须嵌入到此样式标签里 --%>
<div class="col-xs-8 col-xs-offset-2 form-singlon">
	<sf:form modelAttribute="filter" role="form" cssClass="form-horizontal form-show">
		<sf:hidden path="id"/>
		
		<div class="form-group">
			<sf:label path="filterName" cssClass="col-xs-3 control-label">
				<fmt:message key="era.filter.filterName" />
			</sf:label>
			<div class="col-xs-9 form-field">
				<mh:value path="filterName" />
			</div>
		</div>
		 
		<div class="form-group">
			<sf:label path="filterClass" cssClass="col-xs-3 control-label">
				<fmt:message key="era.filter.filterClass" />
			</sf:label>
			<div class="col-xs-9 form-field">
				<mh:value path="filterClass" />
			</div>
		</div>
		
		<div class="form-group">
			<sf:label path="enabled" cssClass="col-xs-3 control-label">
				<fmt:message key="era.filter.enabled" />
			</sf:label>
			<div class="col-xs-9 form-field">
				<c:if test="${filter.enabled}">
	       			是
		       	</c:if>
		       	<c:if test="${!filter.enabled}">
		       		否
		       	</c:if>
			</div>
		</div>
		
		<div class="form-group">
		
			<sf:label path="desc" cssClass="col-xs-3 control-label">
				<fmt:message key="era.filter.rules" />
			</sf:label>
			
			<div class="col-xs-9 form-field">
				<mh:value path="ruleName" />
			</div>
		</div>
		
		<div class="form-group">
			<sf:label path="desc" cssClass="col-xs-3 control-label">
				<fmt:message key="era.filter.desc" />
			</sf:label>
			<div class="col-xs-9 form-field">
				<mh:value path="desc" />
			</div>
		</div>
		
		<div class="form-group form-button-panel">
			<div class="col-xs-9 col-xs-offset-3 form-buttons">
			   	<c:url value="era/filter/list" var="listUrl"/>
				<mh:button class="btn btn-primary"  href="${listUrl}">
					<fmt:message key="button.back" />
				</mh:button>
			</div>
		</div>
	</sf:form>
</div>