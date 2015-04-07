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
				<mh:button href="system/logger/listUser" class="btn btn-default"><fmt:message key="system.logger.count"/></mh:button>
			</jsp:attribute>
			<jsp:attribute name="quickQuery">
				<%--快速查询摆放区 --%>
				<sf:label path="operator.username" cssClass="col-xs-4 control-label"><fmt:message key="system.logger.operator"/></sf:label>
				<div class="col-xs-8 form-field">
					<sf:input path="operator.username" cssClass="form-control"/>
				</div>
			</jsp:attribute>
			<jsp:attribute name="advanceQuery">
				<%--独占一行的摆放方式 --%>
				<div class="form-group">
					
					<div class="col-xs-5 form-field-group">
						<sf:label path="function" cssClass="col-xs-4 control-label"><fmt:message key="system.logger.function"/></sf:label>
						<div class="col-xs-8 form-field">
							<sf:input path="function" cssClass="form-control"/>
						</div>
					</div>
					
					<div class="col-xs-5 form-field-group">
						<sf:label path="clazz" cssClass="col-xs-4 control-label"><fmt:message key="system.logger.clazz"/></sf:label>
						<div class="col-xs-8 form-field">
							<sf:input path="clazz" cssClass="form-control"/>
						</div>
					</div>
					
					<div class="col-xs-5 form-field-group" style="margin-top: 10px;">
						<sf:label path="ip" cssClass="col-xs-4 control-label"><fmt:message key="system.logger.ip"/></sf:label>
						<div class="col-xs-8 form-field">
							<sf:input path="ip" cssClass="form-control"/>
						</div>
					</div>
					
					<div class="col-xs-5 form-field-group " style="margin-top: 10px;">
						<sf:label path="occurTime" cssClass="col-xs-4 control-label">
							<fmt:message key="system.logger.time" />
						</sf:label>
						
						<div class="col-xs-8 form-field">
							<sf:input id="start" path="start" cssClass="form-control form-field-time"/>
							<hh:date id="start" maxDate="${maxDate}"/>
							-
							<sf:input id="end" path="end" cssClass="form-control form-field-time"/>
							<hh:date id="end" maxDate="${maxDate}"/>
						</div>
					</div>
				
				</div>
			</jsp:attribute>
		</mh:query>
	</sf:form>
</div>
<div class="page-query-body">
	<mh:grid id="logger"
			queryUrl="system/logger/list"
			showUrl="system/logger/show" 
			hasPages= "true"
			var="loggerConfig">
		<mh:col name="operator.username" title="system.logger.operator" width="15"/>
		<mh:col name="function" title="system.logger.function" width="20"/>
		<mh:col name="ip" title="system.logger.ip" width="20"/>
		<mh:col name="occurTime" title="system.logger.time" width="25"/>
		<mh:col name="clazz" title="system.logger.clazz" width="20"/>
	</mh:grid>
</div>