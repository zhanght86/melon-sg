<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="hh" uri="http://www.legendsec.com/melon/helper" %>

<div class="page-query-heading">
	<sf:form modelAttribute="pageQuery.searchForm" role="form" cssClass="form-horizontal form-query">
		<mh:query>
			<jsp:attribute name="buttons">
				<mh:button href="asset/regulation/create" class="btn btn-primary"><fmt:message key="button.create"/></mh:button>
			</jsp:attribute>
			<jsp:attribute name="quickQuery">
				<!-- 快速查询摆放区 -->
				<sf:label path="name" cssClass="col-xs-4 control-label">
					<fmt:message key="melon.regulation.name"/>
				</sf:label>
				<div class="col-xs-8 form-field">
					<sf:input path="name" cssClass="form-control" maxlength="50" />
				</div>
			</jsp:attribute>
			<jsp:attribute name="advanceQuery">
				<%-- <div class="form-group">
					<div class="col-xs-5 form-field-group">
						<sf:label path="organName" cssClass="col-xs-4 control-label"><fmt:message key="melon.interStaff.organ"/></sf:label>
						
						<div class="col-xs-8 form-field">
							<sf:input path="organName" cssClass="form-control" maxlength="50" />
						</div>
					</div>	
					
					<div class="col-xs-5 form-field-group">
						<sf:label path="chargeName" cssClass="col-xs-4 control-label"><fmt:message key="melon.interStaff.chargePerson"/></sf:label>
						
						<div class="col-xs-8 form-field">
							<sf:input path="chargeName" cssClass="form-control" maxlength="50" />
						</div>
					</div>
				</div> --%>
			</jsp:attribute>
		</mh:query>
	</sf:form>
</div>

<div class="page-query-body">
	<mh:grid id="regulation" 
			 queryUrl = "regulation/regulate/list"
			 showUrl = "regulation/regulate/show"
			 deleteUrl = "regulation/regulate/remove"
			 updateUrl = "regulation/regulate/update"
			 hasPages= "true" 
			 var="regulationConfig">
		<mh:col name="name" title="melon.regulation.name" width="20"/>
		<mh:col name="code" title="melon.regulation.code" width="20"/>
		<mh:col name="number" title="melon.regulation.number" width="20"/>
		<mh:col name="orgName" title="melon.regulation.issueOrgan" width="20"/>
	</mh:grid>
</div>


