<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%-- 单独的表单必须嵌入到此样式标签里 --%>
<div class="col-xs-8 col-xs-offset-2 form-singlon">
	<sf:form modelAttribute="logger" role="form" cssClass="form-horizontal form-show">
	
		<sf:hidden path="id"/>
		
		<mh:section id="basic">
		
			<jsp:attribute name="title">
				<mh:value path="function"/>
			</jsp:attribute>
			
			<jsp:attribute name="body">
				
				<div class="form-group">
					<sf:label path="operator.username" cssClass="col-xs-3 control-label">
						<fmt:message key="system.logger.operator"/>
					</sf:label>
					
					<div class="col-xs-9 form-field">
						<mh:value path="operator.username"/>
					</div>
				</div>
				
				<div class="form-group">
					<sf:label path="occurTime" cssClass="col-xs-3 control-label">
						<fmt:message key="system.logger.time"/>
					</sf:label>
					
					<div class="col-xs-9 form-field">
						<mh:value path="occurTime"/>
					</div>
				</div>
				
				<div class="form-group">
					<sf:label path="ip" cssClass="col-xs-3 control-label">
						<fmt:message key="system.logger.ip"/>
					</sf:label>
					
					<div class="col-xs-9 form-field">
						<mh:value path="ip"/>
					</div>
				</div>
				
				<div class="form-group">
					<sf:label path="function" cssClass="col-xs-3 control-label">
						<fmt:message key="system.logger.function"/>
					</sf:label>
					
					<div class="col-xs-9 form-field">
						<mh:value path="function"/>
					</div>
				</div>
				
				<div class="form-group">
					<sf:label path="clazz" cssClass="col-xs-3 control-label">
						<fmt:message key="system.logger.clazz"/>
					</sf:label>
					
					<div class="col-xs-9 form-field">
						<mh:value path="clazz"/>
					</div>
				</div>
				
				<div class="form-group">
					<sf:label path="module" cssClass="col-xs-3 control-label">
						<fmt:message key="system.logger.module"/>
					</sf:label>
					
					<div class="col-xs-9 form-field">
						<mh:value path="module"/>
					</div>
				</div>
				
				<div class="form-group">
					<sf:label path="result" cssClass="col-xs-3 control-label">
						<fmt:message key="system.logger.result"/>
					</sf:label>
					
					<div class="col-xs-9 form-field">
						<mh:value path="result"/>
					</div>
				</div>
				
			</jsp:attribute>
		</mh:section>
				
		<div class="form-group form-button-panel">
			<div class="col-xs-9 col-xs-offset-3 form-buttons">
				<mh:button class="btn btn-default" href="system/logger/list"><fmt:message key="button.backList"/></mh:button>
			</div>
		</div>
			
	</sf:form>
</div>