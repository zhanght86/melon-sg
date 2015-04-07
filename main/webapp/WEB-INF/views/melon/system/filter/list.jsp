<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sp" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<c:if test="${!empty success}">
	<div id="melon-alert" class="alert alert-success fade in">
		<button class="close" aria-hidden="true" type="button">×</button>
		<h4><fmt:message key="hint.success"/></h4>
		<p></p>  
	</div>
</c:if>
<div class="page-query-heading">
	<sf:form modelAttribute="pageQuery.searchForm" role="form" cssClass="form-horizontal form-query">
		<mh:query>
		
			<jsp:attribute name="buttons">
				<mh:button href="era/filter/create" class="btn btn-primary"><fmt:message key="button.create"/></mh:button>
			</jsp:attribute>
			<jsp:attribute name="quickQuery">
				
				<sf:label path="filterName" cssClass="col-xs-4 control-label">
			 		<fmt:message key="era.filter.filterName" var="filterName"/>${filterName }
		  		</sf:label>
		  		<div class="col-xs-8 form-field">
					<sf:input path="filterName" cssClass="form-control" id="filterName" />
		  		</div>
		  		
			</jsp:attribute>
			
		</mh:query>
	</sf:form>
</div>
<div class="page-query-body">
	<mh:grid id="mRuleFilterConfig"
			 queryUrl = "era/filter/list"
			 showUrl = "era/filter/show"
			 updateUrl="era/filter/update"
			 deleteUrl="era/filter/remove"
			 hasPages="true"
			 var="mRuleFilterConfig">
		<mh:col name="filterName" title="era.filter.filterName" width="35" formatter="'showLink'" formatoptions="{baseUrl : '${showUrl}'}"/>
		<mh:col name="filterClass" title="era.filter.filterClass" width="35"/>
		<mh:col name="enabled" title="era.filter.enabled" width="30"/>
	</mh:grid>
</div>