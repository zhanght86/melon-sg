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
	<sf:form modelAttribute="pageQuery.searchForm"  role="form" cssClass="form-horizontal form-query" method="POST">
		<mh:query>
			<jsp:attribute name="buttons">
				<mh:button href="report/create" class="btn btn-default"><fmt:message key="button.create"/></mh:button>
				<mh:button href="report/view" class="btn btn-default should-selected" target="_blank" ><fmt:message key="report.view"/></mh:button>
			</jsp:attribute>
			<jsp:attribute name="quickQuery">
				<%--快速查询摆放区 --%>
				<sf:label path="name" cssClass="col-xs-4 control-label"><fmt:message key="report.name"/></sf:label>
				<div class="col-xs-8 form-field">
					<sf:input path="name" cssClass="form-control"/>
				</div>
			</jsp:attribute>
			<jsp:attribute name="advanceQuery">
				<%-- <div class="form-group">
					<div class="col-xs-5 form-field-group">
						<sf:label path="startDate" cssClass="col-xs-4 control-label"><fmt:message key="report.startDate"/></sf:label>
						<div class="col-xs-8 form-field">
							<sf:input path="startDate" cssClass="form-control"/>
						</div>
					</div>
					<div class="col-xs-5 form-field-group">
						<sf:label path="endDate" cssClass="col-xs-4 control-label"><fmt:message key="report.endDate"/></sf:label>
						<div class="col-xs-8 form-field">
							<sf:input path="endDate" cssClass="form-control"/>
						</div>
					</div>
				</div> --%>
			</jsp:attribute>
		</mh:query>
	</sf:form>
</div>

<div class="page-query-body">
	<mh:grid id="report" 
			 show = "${birt}"	
			 queryUrl = "report/list/${type}"
			 deleteUrl = "report/remove"
			 updateUrl = "report/update"
			 hasPages= "true">
		<mh:col name="name" title="report.name" width="20"/>
		<mh:col name="type" title="report.type" width="10"/>
		<mh:col name="category"  title="report.category" width="10" sortable="true"/>
		<mh:col name="createDate" title="report.createDate" width="20" sortable="true"/>
	</mh:grid>
</div>