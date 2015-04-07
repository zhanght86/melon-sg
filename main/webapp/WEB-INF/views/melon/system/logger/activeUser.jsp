<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!-- <div class="page-query-heading" >
	<label style="color:#45a6d6"><fmt:message key="system.logger.ative"/></label>
</div> -->
<div class="page-query-heading">
	<sf:form modelAttribute="pageQuery.searchForm" role="form" cssClass="form-horizontal form-query">
		<mh:query >
			
			<jsp:attribute name="buttons">
				<mh:button href="system/logger/listUser" class="btn btn-primary"><fmt:message key="system.logger.count"/></mh:button>
			</jsp:attribute>
			
			<jsp:attribute name="quickQuery">
				<!-- 快速查询摆放区 -->
				<sf:label path="operator.username" cssClass="col-xs-4 control-label"><fmt:message key="system.logger.operator"/></sf:label>
				<div class="col-xs-8 form-field">
					<sf:input path="operator.username" cssClass="form-control"/>
				</div>
			</jsp:attribute>
			
		 </mh:query> 
	</sf:form>
</div>
<div class="page-query-body">
	<mh:grid id="logger"
			queryUrl="system/logger/activeUser"
			showUrl = "security/account/show"
			dataType="ARRAY" 
			hasPages= "false"
			var="loggerConfig">
			<mh:col name="operator.id"  title="system.logger.operator" width="0" hidden="true"/>
			<mh:col name="operator.username" title="system.logger.operator"  width="30" sortable="false"/>
			<mh:col name="operator.function"  title="system.logger.lastFunction" width="40" sortable="false"/>
			<mh:col name="occurTime" title="system.logger.endTime"  width="30" sortable="false"/>
	</mh:grid>
</div>
