<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="hh" uri="http://www.legendsec.com/melon/helper" %>

<link rel="stylesheet" href="<mh:theme code='jquery.select.css'/>" media="all" />
<script type="text/javascript" src="<mh:theme code='jquery.select.js'/>"></script>

<div class="page-header">
	  <h3 style="display: inline-block;">
	  	<fmt:message key="asset.contrast.start">
		    <fmt:param>
		    	<a href="<c:url value='/asset/infoSystem/show/${sysId }'/>">
		    		${sysName}
		    	</a>	
		    </fmt:param>
		</fmt:message>
	  </h3>
</div>

<div class="page-query-heading">
	
	<sf:form modelAttribute="pageQuery.searchForm" role="form" cssClass="form-horizontal form-query">
		<sf:hidden path="id"/>
		<mh:query>
			<jsp:attribute name="buttons">
				<mh:button href="asset/infoSystem/startContrast/${sysId }" class="btn btn-default should-selected">开始对比</mh:button>
			</jsp:attribute>
			<jsp:attribute name="quickQuery">
				<!-- 快速查询摆放区 -->
				<sf:label path="organName" cssClass="col-xs-4 control-label"><fmt:message key="asset.abstract.organName"/></sf:label>
				<div class="col-xs-8 form-field">
					<sf:input path="organName" cssClass="form-control" maxlength="50" />
				</div>
			</jsp:attribute>
			<jsp:attribute name="advanceQuery">
				
				<div class="form-group">	
					<div class="col-xs-5 form-field-group">
						<sf:label path="name" cssClass="col-xs-4 control-label"><fmt:message key="asset.abstract.name"/></sf:label>
						<div class="col-xs-8 form-field">
							<sf:input path="name" cssClass="form-control" maxlength="50" />
						</div>
					</div>
				
					<div class="col-xs-5 form-field-group">
						<sf:label path="code" cssClass="col-xs-4 control-label"><fmt:message key="asset.abstract.code"/></sf:label>
						<div class="col-xs-8 form-field">
							<sf:input path="code" cssClass="form-control"/>
						</div>
					</div>
				</div>
				
				<div class="form-group">	
					<div class="col-xs-5 form-field-group">
						<sf:label path="using" cssClass="col-xs-4 control-label"><fmt:message key="asset.abstract.using"/></sf:label>
						<div class="col-xs-8 form-field">
							<select id="searchForm.using" class="form-control depart-select" name="searchForm.using">
								<option value="-1"><fmt:message key="asset.selected"></fmt:message></option>
								<option value="0"><fmt:message key="asset.no"></fmt:message></option>
								<option value="1"><fmt:message key="asset.yes"></fmt:message></option>
							</select>
						</div>
					</div>
				</div>
			</jsp:attribute>
		</mh:query>
	</sf:form>
</div>

<div class="page-query-body">
	<mh:grid id="infoSystem" 
			 queryUrl = "asset/infoSystem/contrast/${sysId }"
			 showUrl = "asset/infoSystem/show"
			 hasPages= "true" 
			 var="infoSystemConfig">
		<mh:col name="name" title="asset.abstract.name" width="20"/>
		<mh:col name="code" title="asset.abstract.code" width="20"/>
		<mh:col name="chargeName" title="asset.abstract.chargeName" width="20"/>
		<mh:col name="organName" title="asset.abstract.organName" width="20"/>
		<mh:col name="using" index="using" title="asset.abstract.using" width="20"/>
	</mh:grid>
</div>

