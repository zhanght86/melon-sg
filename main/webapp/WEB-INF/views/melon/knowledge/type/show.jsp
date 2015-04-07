<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div class="col-xs-8">
	<sf:form modelAttribute="knowledgeType" role="form" cssClass="form-horizontal form-show">
		
		<mh:section id="basic">
			<jsp:attribute name="title">
				<fmt:message key="knowledge.nav.basic"/>
			</jsp:attribute>
			
			<jsp:attribute name="body">
			
				<div class="form-group">
					<sf:label path="title" cssClass="col-xs-3 control-label">
						<fmt:message key="knowledge.type.title"/>
					</sf:label>
					<div class="col-xs-9 form-field">
						<mh:value path="title"/>
					</div>
				</div>
				
				<div class="form-group">
					<sf:label path="code" cssClass="col-xs-3 control-label">
						<fmt:message key="knowledge.type.code"/>
					</sf:label>
					<div class="col-xs-9 form-field">
						<mh:value path="code"/>
					</div>
				</div>
				
				<div class="form-group">
					<sf:label path="order" cssClass="col-xs-3 control-label">
						<fmt:message key="knowledge.type.order"/>
					</sf:label>
					<div class="col-xs-9 form-field">
						<mh:value path="order"/>
					</div>
				</div>
				
			 	<div class="form-group">
				    <sf:label path="remarks" cssClass="col-xs-3 control-label">
						<fmt:message key="knowledge.type.remarks"/>
					</sf:label>
					<div class="col-xs-9 form-field">
						<mh:value path="remarks"/>
					</div>
				</div>
				
			</jsp:attribute>
		</mh:section>	
	</sf:form>
</div>