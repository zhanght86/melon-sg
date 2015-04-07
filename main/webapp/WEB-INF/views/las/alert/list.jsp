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
				<mh:button href="las/sim/alert/create" class="btn btn-default"><fmt:message key="button.create"/></mh:button>
			</jsp:attribute>
			<jsp:attribute name="quickQuery">
				<%--快速查询摆放区 --%>
				<sf:label path="name" cssClass="col-xs-4 control-label"><fmt:message key="las.rule.name"/></sf:label>
				<div class="col-xs-8 form-field">
					<sf:input path="name" cssClass="form-control"/>
				</div>
			</jsp:attribute>
			<jsp:attribute name="advanceQuery">
				<%-- <div class="form-group">
					<div class="col-xs-5 form-field-group">
						<sf:label path="companyName" cssClass="col-xs-4 control-label"><fmt:message key="security.account.companyName"/></sf:label>
						<div class="col-xs-8 form-field">
						    <sf:hidden path="companyName"/>
							<sf:input path="companyId" cssClass="form-control" id="company"/>
        					<hh:select id="company" onceUrl="organ/organization/findOrgans?parentId=0&type=1"/>
        				</div>
					</div>
					<div class="col-xs-5 form-field-group">
						<sf:label path="departName" cssClass="col-xs-4 control-label"><fmt:message key="security.account.departName"/></sf:label>
						<div class="col-xs-8 form-field">
						    <sf:hidden path="departName"/>
							<sf:select path="departId" cssClass="form-control depart-select" />
						</div>
					</div> --%>
				</div>
			</jsp:attribute>
		</mh:query>
	</sf:form>
</div>

<div class="page-query-body">
	
	<mh:grid id="alert" 
			 queryUrl = "las/sim/alert/list"
			 updateUrl = "las/sim/alert/update"
			 hasPages= "true" 
			 multiSelect = "false"
			 var="filterConfig">
		<mh:col name="name" title="las.rule.name" width="30"/>
		<mh:col name="action" title="las.rule.action" width="15"/>
		<mh:col name="createTime" title="las.rule.createTime" width="20"/>
		<mh:col name="updateTime" title="las.rule.updateTime" width="20"/>
	</mh:grid>
</div>

<script type="text/javascript">

   $(document).ready(function() {

		
   });
</script>