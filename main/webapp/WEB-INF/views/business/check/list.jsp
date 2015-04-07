<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="hh" uri="http://www.legendsec.com/melon/helper" %>

<link rel="stylesheet" href="<mh:theme code='jquery.select.css'/>" media="all" />
<script type="text/javascript" src="<mh:theme code='jquery.select.js'/>"></script>

<div class="page-query-heading">
	<sf:form modelAttribute="pageQuery.searchForm" role="form" cssClass="form-horizontal form-query">
		<mh:query>
		
			<jsp:attribute name="buttons">
				<mh:button href="business/checkTable/create" class="btn btn-default"><fmt:message key="button.create"/></mh:button>
			</jsp:attribute>
			
			<jsp:attribute name="quickQuery">
				<%--快速查询摆放区 --%>
				<sf:label path="title" cssClass="col-xs-4 control-label"><fmt:message key="checkTable.title"/></sf:label>
				<div class="col-xs-8 form-field">
					<sf:input path="title" cssClass="form-control" id="title"/>
				</div>
			</jsp:attribute>
			
			<jsp:attribute name="advanceQuery">
				<div class="form-group">
					<div class="col-xs-5 form-field-group">
						<sf:label path="issueOrgan" cssClass="col-xs-4 control-label"><fmt:message key="checkTable.issueOrgan"/></sf:label>
						<div class="col-xs-8 form-field">
							<sf:input path="issueOrgan" cssClass="form-control"/>
						</div>
					</div>
					
					<div class="col-xs-5 form-field-group ">
						<sf:label path="issueTime" cssClass="col-xs-4 control-label">
							<fmt:message key="checkTable.issueTime" />
						</sf:label>
						
						<div class="col-xs-8 form-field">
							<sf:input id="startTime" path="startTime" cssClass="form-control form-field-time"/>
							<hh:date id="startTime" maxDate="${maxDate}"/>
							-
							<sf:input id="endTime" path="endTime" cssClass="form-control form-field-time"/>
							<hh:date id="endTime" maxDate="${maxDate}"/>
						</div>
						
					</div>
					
				</div>
				
			</jsp:attribute>
		</mh:query>
	</sf:form>
</div>

<div class="page-query-body">
	<mh:grid id="checkTable"
			 queryUrl = "business/checkTable/list"
			 showUrl = "business/checkTable/show"
			 deleteUrl = "business/checkTable/remove"
			 updateUrl = "business/checkTable/update"
			 hasPages= "true"
			 multiSelect = "true"
			 var="checkTableConfig">
		<mh:col name="title" title="checkTable.title" width="40"/>
		<mh:col name="issueOrgan" title="checkTable.issueOrgan" width="30"/>
		<mh:col name="issueTime" title="checkTable.issueTime" width="30" sortable="true"/>
	</mh:grid>
</div>