<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="hh" uri="http://www.legendsec.com/melon/helper" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<link rel="stylesheet" href="<mh:theme code='jquery.select.css'/>" media="all" />
<script type="text/javascript" src="<mh:theme code='jquery.select.js'/>"></script>

<div class="page-query-heading">
	<sf:form modelAttribute="pageQuery.searchForm" role="form" cssClass="form-horizontal form-query">
		<mh:query>
			<jsp:attribute name="buttons">
				<mh:button href="las/sim/rule/create" class="btn btn-default"><fmt:message key="button.create"/></mh:button>
				<mh:button href="las/condition/tree" class="btn btn-default should-selected"><fmt:message key="button.configCond"/></mh:button>
				<mh:button href="las/sim/alert/countsEdit" class="btn btn-default should-selected"><fmt:message key="button.counts"/></mh:button>
			</jsp:attribute>
			<jsp:attribute name="quickQuery">
				<%--快速查询摆放区 --%>
				<sf:label path="name" cssClass="col-xs-4 control-label"><fmt:message key="las.rule.name"/></sf:label>
				<div class="col-xs-8 form-field">
					<sf:input path="name" cssClass="form-control"/>
				</div>
			</jsp:attribute>
			<jsp:attribute name="advanceQuery">
				<div class="form-group">
					<div class="col-xs-5 form-field-group">
						<sf:label path="name" cssClass="col-xs-4 control-label"><fmt:message key="las.rule.createUser"/></sf:label>
						<div class="col-xs-8 form-field">
							<sf:input path="creator.username" cssClass="form-control"/>
        				</div> 
					</div>
				</div>
			</jsp:attribute>
		</mh:query>
	</sf:form>
</div>

<div class="page-query-body">
	
	<mh:grid id="alert" 
			 queryUrl = "las/sim/rule/list"
			 updateUrl = "las/sim/rule/update"
			 deleteUrl= "las/sim/rule/remove"
			 hasPages= "true" 
			 multiSelect = "false"
			 var="alertConfig">
		<mh:col name="name" title="las.rule.name" width="30"/>
		<mh:col name="enabled" title="las.rule.state" width="15"/>
		<mh:col name="creator.username" title="las.rule.createUser" width="20"/>
		<mh:col name="createTime" title="las.rule.createTime" width="20"/>
	</mh:grid>
</div>
