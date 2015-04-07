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
				<mh:button href="staff/outer/create" class="btn btn-primary"><fmt:message key="button.create"/></mh:button>
				<mh:button href="staff/outer/export" class="btn btn-default"><fmt:message key="button.export"/></mh:button>
			</jsp:attribute>
			<jsp:attribute name="quickQuery">
				<!-- 快速查询摆放区 -->
				<sf:label path="name" cssClass="col-xs-4 control-label">
					<fmt:message key="melon.interStaff.name"/>
				</sf:label>
				<div class="col-xs-8 form-field">
					<sf:input path="name" cssClass="form-control" maxlength="50" />
				</div>
			</jsp:attribute>
			<jsp:attribute name="advanceQuery">
				<div class="form-group">	
					<div class="col-xs-5 form-field-group">
						<sf:label path="departName" cssClass="col-xs-4 control-label"><fmt:message key="melon.outerStaff.department"/></sf:label>
						
						<div class="col-xs-8 form-field">
							<sf:input path="departName" cssClass="form-control" maxlength="50" />
						</div>
					</div>
					
					<div class="col-xs-5 form-field-group">
						<sf:label path="chargeName" cssClass="col-xs-4 control-label"><fmt:message key="melon.outerStaff.chargePerson"/></sf:label>
						
						<div class="col-xs-8 form-field">
							<sf:input path="chargeName" cssClass="form-control" maxlength="50" />
						</div>
					</div>
				</div>
			</jsp:attribute>
		</mh:query>
	</sf:form>
</div>

<div class="page-query-body">
	<mh:grid id="outerStaff" 
			 queryUrl = "staff/outer/list"
			 showUrl = "staff/outer/show"
			 deleteUrl = "staff/outer/remove"
			 updateUrl = "staff/outer/update"
			 hasPages= "true" 
			 var="outerStaffConfig">
		<mh:col name="name" title="melon.outerStaff.name" width="20"/>
		<mh:col name="departName" title="melon.outerStaff.department" width="20"/>
		<mh:col name="job" index="fullJob" title="melon.outerStaff.fullJob" width="20" />
		<mh:col name="chargeName" title="melon.outerStaff.chargePerson" width="20"/>
	</mh:grid>
</div>


