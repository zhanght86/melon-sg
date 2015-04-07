<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sp" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html" %>
<%-- 单独的表单必须嵌入到此样式标签里 --%>
<div class="col-xs-8 col-xs-offset-2 form-singlon">
	<sf:form modelAttribute="meFile" role="form" cssClass="form-horizontal form-show">
	
		<sf:hidden path="id"/>
		
		<mh:section id="basic">
		
			<jsp:attribute name="title">
				<mh:value path="function"/>
			</jsp:attribute>
			
			<jsp:attribute name="body">
				
				<div class="form-group">
					<sf:label path="fileName" cssClass="col-xs-3 control-label">
						<fmt:message key="system.file.fileName"/>
					</sf:label>
					
					<div class="col-xs-9 form-field">
						<mh:value path="fileName"/>
					</div>
				</div>
	
				<div class="form-group">
					<sf:label path="type" cssClass="col-xs-3 control-label">
						<fmt:message key="system.file.type"/>
					</sf:label>
					
					<div class="col-xs-9 form-field">
						<mh:value path="type"/>
					</div>
				</div>

				<div class="form-group">
					<sf:label path="secured" cssClass="col-xs-3 control-label">
						<fmt:message key="system.file.secured"/>
					</sf:label>
					
					<div class="col-xs-9 form-field">
						<mh:dictionary key="boolType" var="bool"/>						
						<mh:value path="secured" items="boolType"/>
					</div>
				</div>
				
				<div class="form-group">
					<sf:label path="organName" cssClass="col-xs-3 control-label">
						<fmt:message key="system.file.organName"/>
					</sf:label>
					
					<div class="col-xs-9 form-field">
						<mh:value path="organName"/>
					</div>
				</div>
				
				<div class="form-group">
					<sf:label path="username" cssClass="col-xs-3 control-label">
						<fmt:message key="system.file.username"/>
					</sf:label>
					
					<div class="col-xs-9 form-field">
						<mh:value path="username"/>
					</div>
				</div>
			
				<div class="form-group">
					<sf:label path="uploadTime" cssClass="col-xs-3 control-label">
						<fmt:message key="system.file.uploadTime"/>
					</sf:label>
					
					<div class="col-xs-9 form-field">
						<mh:value path="uploadTime"/>
						<%-- <fmt:formatDate value="${meFile.uploadTime}"   pattern="yyyy-MM-dd HH:mm:ss"/> --%>
					</div>
				</div>
				
			</jsp:attribute>
		</mh:section>
			<div class="form-group form-button-panel">
				<div class="col-xs-9 col-xs-offset-3 form-buttons">
					<mh:button class="btn btn-default" href="system/file/list"><fmt:message key="button.backList"/></mh:button>
					<mh:button class="btn btn-primarey" href="system/file/update/${meFile.id}"><fmt:message key="button.edit"/></mh:button>
				</div>
		</div>
	</sf:form>
</div>