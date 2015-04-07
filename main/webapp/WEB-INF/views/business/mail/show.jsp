<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%-- 单独的表单必须嵌入到此样式标签里 --%>
<div class="col-xs-8 col-xs-offset-2 form-singlon">
	<sf:form modelAttribute="mail" role="form" cssClass="form-horizontal form-show">
	
		<sf:hidden path="id"/>
		
		<mh:section id="basic">
		
			<jsp:attribute name="title">
				<fmt:message key="mail.basic"/>
			</jsp:attribute>
			
			<jsp:attribute name="body">
				
				<div class="form-group">
					<sf:label path="sender.username" cssClass="col-xs-3 control-label">
						<fmt:message key="mail.sender"/>
					</sf:label>
					
					<div class="col-xs-9 form-field">
						<mh:value path="sender.username"/>
					</div>
				</div>
				
				<div class="form-group">
					<sf:label path="title" cssClass="col-xs-3 control-label">
						<fmt:message key="mail.title"/>
					</sf:label>
					
					<div class="col-xs-9 form-field">
						<mh:value path="title"/>
					</div>
				</div>
				
				
				<div class="form-group">
					<sf:label path="content" cssClass="col-xs-3 control-label">
						<fmt:message key="mail.content"/>
					</sf:label>
					
					<div class="col-xs-9 form-field">
						<mh:value path="content" cssStyle="font-size : 14px;"/>
					</div>
				</div>
				
				<div class="form-group">
					<sf:label path="attachId" cssClass="col-xs-3 control-label">
						<fmt:message key="mail.attach"/>
					</sf:label>
					
					<div class="col-xs-9 form-field">
						<mh:download businessId="attachId"/>
					</div>
				</div>
				
			</jsp:attribute>
		</mh:section>
				
		<div class="form-group form-button-panel">
			<div class="col-xs-9 col-xs-offset-3 form-buttons">
				<sec:authorize url="message/mail/update/*">
					<mh:button class="btn btn-primary" href="message/mail/update/${mail.id}"><fmt:message key="button.update"/></mh:button>
				</sec:authorize>
				<mh:button class="btn btn-default" href="message/mail/list">存草稿</mh:button>
			</div>
		</div>
			
	</sf:form>
</div>
