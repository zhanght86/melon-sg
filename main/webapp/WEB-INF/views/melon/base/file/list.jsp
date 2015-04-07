<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sp" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html" %>
<%@ taglib prefix="hh" uri="http://www.legendsec.com/melon/helper" %>

<link rel="stylesheet" href="<mh:theme code='jquery.select.css'/>" media="all" />
<script type="text/javascript" src="<mh:theme code='jquery.select.js'/>"></script>

<div class="page-query-heading">
  	<sf:form modelAttribute="pageQuery.searchForm" role="form" cssClass="form-horizontal form-query">
		<mh:query>
			<jsp:attribute name="buttons">
				<mh:button href="system/file/create" class="btn btn-default should-selected"><fmt:message key="button.create"/></mh:button>
		   <%-- <mh:button type="button" text="button.download" id="download"/>
				<mh:button type="button" text="button.upload" id="upload"/> --%>
			</jsp:attribute>
			<jsp:attribute name="quickQuery">
				<%--快速查询摆放区 --%>
				<sf:label path="fileName"  cssClass="col-xs-4 control-label">
			 		<fmt:message key="system.file.fileName"/>
		  		</sf:label>
				<div class="col-xs-8 form-field">
					<sf:input path="fileName" cssClass="form-control" />
				</div>
			</jsp:attribute>
			<jsp:attribute name="advanceQuery">
				<div class="form-group">
				
					<div class="col-xs-5 form-field-group">
						<sf:label path="fileName" cssClass="col-xs-4 control-label">
							<fmt:message key="system.file.fileName"/>
						</sf:label>
						<div class="col-xs-8 form-field" >
							<sf:input path="fileName" cssClass="form-control" maxlength="20" />
						</div>
					</div>
				</div>
			<%--<div class="form-group">
				    <div class="col-xs-5 form-field-group">
						<sf:label path="startTime" cssClass="col-xs-4 control-label">
							<fmt:message key="system.file.startTime"/>
						</sf:label>
						<div class="col-xs-8 form-field">
							<sf:input id="startTime" path="startTime" cssClass="form-control form-field-time"/>
							<hh:date id="startTime"/>
						</div>
					</div>
				
					<div class="col-xs-5 form-field-group">
						<sf:label path="endTime" cssClass="col-xs-4 control-label">
							<fmt:message key="system.file.endTime"/>
						</sf:label>
						<div class="col-xs-8 form-field" >
							<sf:input id="endTime" path="endTime" cssClass="form-control form-field-time"/>
							<hh:date id="endTime"/>
						</div>
					</div>
					
				<div class="form-group">
				
					<div class="col-xs-5 form-field-group">
						<sf:label path="username"cssClass="col-xs-4 control-label" >
							<fmt:message key="system.file.endTime"/>
						</sf:label>
						<div class="col-xs-8 form-field">
							<hh:date id="endTime"/>
						</div>
					</div>
					
					<div class="col-xs-5 form-field-group">
						<sf:label path="organName" cssClass="col-xs-4 control-label">
							<fmt:message key="system.file.organName"/>
						</sf:label>
						<div class="col-xs-8 form-field">
							<hh:date id="organName" />
						</div>
					</div>
				</div> --%>
			</jsp:attribute>
		</mh:query>
	</sf:form>
</div>

<div class="page-query-body">
	<mh:grid id="meFile"
			 queryUrl = "system/file/list"
			 showUrl = "system/file/fileshow"
			 deleteUrl = "system/file/remove"
			 hasPages= "true"
			 multiSelect = "false"
			 var="meFileConfig">
		<mh:col name="name" title="system.file.fileName" width="20"/>
		<mh:col name="type" title="system.file.type" width="15"/>
		<mh:col name="secured" title="system.file.secured" width="15" />
		<mh:col name="username" title="system.file.username" width="15" />
		<mh:col name="organName" title="system.file.organName" width="15" />
		<mh:col name="uploadTime" title="system.file.uploadTime" width="20" sortable="true"/>
	</mh:grid>
</div>		
