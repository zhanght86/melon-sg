<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%-- 单独的表单必须嵌入到此样式标签里 --%>
<div class="col-xs-8 col-xs-offset-2 form-singlon">
	<sf:form modelAttribute="checkTable" role="form" cssClass="form-horizontal form-show">
		
		<mh:section id="basic">
			
			<jsp:attribute name="title">
				<mh:value path="title"/>
			</jsp:attribute>
			
			<jsp:attribute name="body">
				<sf:hidden path="id"/>
				
				<div class="form-group">
					<sf:label path="title" cssClass="col-xs-3 control-label">
						<fmt:message key="checkTable.title"/>
					</sf:label>
					
					<div class="col-xs-9 form-field">
						<mh:value path="title"/>
					</div>
				</div>
				
				<div class="form-group">
					<sf:label path="issueOrgan" cssClass="col-xs-3 control-label">
						<fmt:message key="checkTable.issueOrgan"/>
					</sf:label>
					
					<div class="col-xs-9 form-field">
						<mh:value path="issueOrgan"/>
					</div>
				</div>
				
				<div class="form-group">
					<sf:label path="baseUrl" cssClass="col-xs-3 control-label">
						<fmt:message key="checkTable.baseUrl"/>
					</sf:label>
					
					<div class="col-xs-9 form-field">
						<mh:value path="baseUrl"/>
					</div>
				</div>
				
				<div class="form-group">
					<sf:label path="businessId" cssClass="col-xs-3 control-label">
						<fmt:message key="checkTable.businessId"/>
					</sf:label>
					
					<div class="col-xs-9 form-field">
						<mh:value path="businessId"/>
					</div>
				</div>
				
				<div class="form-group">
					<sf:label path="remarks" cssClass="col-xs-3 control-label">
						<fmt:message key="checkTable.remarks"/>
					</sf:label>
					
					<div class="col-xs-9 form-field">
						<mh:value path="remarks" cssStyle="font-size : 14px;"/>
					</div>
				</div>
				
				<div class="form-group form-button-panel">
					<div class="col-xs-9 col-xs-offset-3 form-buttons">
						<mh:button class="btn btn-primary" href="check/list"><fmt:message key="button.backList"/></mh:button>
						<mh:button class="btn btn-default" href="check/update/${checkTable.id}"><fmt:message key="button.update"/></mh:button>
					</div>
				</div>
			</jsp:attribute>
			
		</mh:section>
		
	</sf:form>
</div>
