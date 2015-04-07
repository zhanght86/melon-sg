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
				<mh:button href="security/account/create" class="btn btn-default should-selected"><fmt:message key="button.handle"/></mh:button>
			</jsp:attribute>
			<jsp:attribute name="quickQuery">
				<%--快速查询摆放区 --%>
				<sf:label path="title" cssClass="col-xs-4 control-label"><fmt:message key="alarm.title"/></sf:label>
				<div class="col-xs-8 form-field">
					<sf:input path="title" cssClass="form-control"/>
				</div>
			</jsp:attribute>
			<jsp:attribute name="advanceQuery">
				<div class="form-group">
				
					<div class="col-xs-5 form-field-group">
						<sf:label path="deviceIp" cssClass="col-xs-4 control-label"><fmt:message key="alarm.deviceIp"/></sf:label>
						<div class="col-xs-8 form-field">
							<sf:input path="deviceIp" cssClass="form-control"/>
						</div>
					</div>
					
					<div class="col-xs-5 form-field-group">
						<sf:label path="level" cssClass="col-xs-4 control-label"><fmt:message key="alarm.level"/></sf:label>
						<div class="col-xs-8 form-field">
							<mh:dictionary var="alevel" key="alarmLevel"/>
							<sf:select path="level"  items="${alevel}" cssClass="form-control"/>
						</div>
					</div>
				</div>
				
				<div class="form-group">
					<div class="col-xs-5 form-field-group">
						<sf:label path="occurTime" cssClass="col-xs-4 control-label">
							<fmt:message key="alarm.occurTime" />
						</sf:label>
						
						<div class="col-xs-8 form-field">
							<sf:input id="startTime" path="startTime" cssClass="form-control form-field-time"/>
							<hh:date id="startTime" />
							-
							<sf:input id="endTime" path="endTime" cssClass="form-control form-field-time"/>
							<hh:date id="endTime" />
						</div>
					</div>
					
					<div class="col-xs-5 form-field-group">
						<sf:label path="state" cssClass="col-xs-4 control-label"><fmt:message key="alarm.state"/></sf:label>
						<div class="col-xs-8 form-field">
							<mh:dictionary var="astate" key="alarmState"/>
							<sf:select path="state" items="${astate}" cssClass="form-control"/>
						</div>
					</div>
				</div>
			</jsp:attribute>
		</mh:query>
	</sf:form>
</div>

<div class="page-query-body">
	<mh:grid id="alarm"
			 queryUrl = "alarm/base/list"
			 showUrl = "alarm/base/show"
			 hasPages= "true"
			 multiSelect = "false"
			 var="alarmConfig">
		<mh:col name="title" title="alarm.title" width="30"/>
		<mh:col name="level" title="alarm.level" width="15"/>
		<mh:col name="deviceIp" title="alarm.deviceIp" width="20" />
		<mh:col name="state" title="alarm.state" width="15" />
		<mh:col name="occurTime" title="alarm.occurTime" width="20" sortable="true"/>
	</mh:grid>
</div>