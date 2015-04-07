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
	<sf:form modelAttribute="pageQuery.searchForm" role="form" cssClass="form-horizontal form-query" method="POST">
		<mh:query>
			<jsp:attribute name="buttons">
				<mh:button href="knowledge/base/create" class="btn btn-default"><fmt:message key="button.create"/></mh:button>
			</jsp:attribute>
			<jsp:attribute name="quickQuery">
				<%--快速查询摆放区 --%>
				<sf:label path="title" cssClass="col-xs-4 control-label"><fmt:message key="knowledge.base.title"/></sf:label>
				<div class="col-xs-8 form-field">
					<sf:input path="title" cssClass="form-control"/>
				</div>
			</jsp:attribute>
			<jsp:attribute name="advanceQuery">
				<div class="form-group">
					<div class="col-xs-5 form-field-group">
						<sf:label path="issueOrgan" cssClass="col-xs-4 control-label"><fmt:message key="knowledge.base.issueOrgan"/></sf:label>
						<div class="col-xs-8 form-field">
							<sf:input path="issueOrgan" cssClass="form-control"/>
						</div>
					</div>
					<div class="col-xs-5 form-field-group">
						<sf:label path="keywords" cssClass="col-xs-4 control-label"><fmt:message key="knowledge.base.keywords"/></sf:label>
						<div class="col-xs-8 form-field">
							<sf:input path="keywords" cssClass="form-control"/>
						</div>
					</div>
				</div>
			</jsp:attribute>
		</mh:query>
	</sf:form>
</div>

<div class="page-query-body">
	<mh:grid id="knowledge" 
			 queryUrl = "knowledge/base/list"
			 showUrl = "knowledge/base/show"
			 deleteUrl = "knowledge/base/remove"
			 updateUrl = "knowledge/base/update"
			 hasPages= "true">
		<mh:col name="title" title="knowledge.base.title" width="50"/>
		<mh:col name="issueOrgan" title="knowledge.base.issueOrgan" width="20"/>
		<mh:col name="issueDate"  title="knowledge.base.issueDate" width="20" sortable="true"/>
		<mh:col name="count" width="10" title="knowledge.base.count" sortable="true"/>
	</mh:grid>
</div>