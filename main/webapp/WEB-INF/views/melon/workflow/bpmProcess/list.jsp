<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<div class="page-query-heading">
	<sf:form modelAttribute="pageQuery.searchForm" role="form" cssClass="form-horizontal form-query">
		<mh:query>
			<jsp:attribute name="buttons">
				<mh:button href="workflow/bpmProcess/create" class="btn btn-default"><fmt:message key="button.create"/></mh:button>
			</jsp:attribute>
			<jsp:attribute name="quickQuery">
				<%--快速查询摆放区 --%>
				<sf:label path="name" cssClass="col-xs-4 control-label"><fmt:message key="workflow.bpmProcess.name"/></sf:label>
				<div class="col-xs-8 form-field">
					<sf:input path="name" cssClass="form-control"/>
				</div>
			</jsp:attribute>
			<jsp:attribute name="advanceQuery">
			</jsp:attribute>
		</mh:query>
	</sf:form>
</div>

<div class="page-query-body">
	<mh:grid id="bpmProcess" 
			 queryUrl = "workflow/bpmProcess/list"
			 showUrl = "workflow/bpmProcess/show"
			 deleteUrl = "workflow/bpmProcess/remove"
			 updateUrl = "workflow/bpmProcess/update"
			 hasPages= "true" 
			 multiSelect = "false"
			 var="bpmProcessConfig">
		<mh:col name="code" title="workflow.bpmProcess.code" width="20"/>
		<mh:col name="name" title="workflow.bpmProcess.name" width="30"/>
		<mh:col name="type" title="workflow.bpmProcess.type" width="20"/>
		<mh:col name="createTime" title="workflow.bpmProcess.createTime" width="20"/>
	</mh:grid>
	
</div>
