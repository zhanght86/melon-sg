<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%-- 单独的表单必须嵌入到此样式标签里 --%>
<div class="col-xs-8 col-xs-offset-2 form-singlon">
	<sf:form modelAttribute="organization" role="form" cssClass="form-horizontal form-show">
		<sf:hidden path="id"/>
		
		<mh:section id="basic">
		
			<jsp:attribute name="title">
				<mh:value path="name"/>
			</jsp:attribute>
			
			<jsp:attribute name="body">
			
				<div class="form-group">
					<sf:label path="type" cssClass="col-xs-3 control-label">
						<fmt:message key="organ.organization.type"/>
					</sf:label>
					
					<div class="col-xs-9 form-field">
						<mh:dictionary var="organType" key="organType"/>
						<mh:value path="type" items="${organType}"/>
						<sf:errors path="type"/>
					</div>
				</div>
				
				<div class="form-group">
					<sf:label path="name" cssClass="col-xs-3 control-label">
						<fmt:message key="organ.organization.name"/>
					</sf:label>
					
					<div class="col-xs-9 form-field">
						<mh:value path="name"/>
					</div>
				</div>
				 
				<div class="form-group">
					<sf:label path="code" cssClass="col-xs-3 control-label">
						<fmt:message key="organ.organization.code"/>
					</sf:label>
					
					<div class="col-xs-9 form-field">
						<mh:value path="code"/>
					</div>
				</div>
				
				<div class="form-group">
					<sf:label path="level" cssClass="col-xs-3 control-label">
						<fmt:message key="organ.organization.level"/>
					</sf:label>
					
					<div class="col-xs-9 form-field">
						<mh:dictionary var="organLevel" key="organLevel"/>
						<mh:value path="level" items="${organLevel}"/>
						<sf:errors path="level"/>
					</div>
				</div>
				
				<div class="form-group">
					<sf:label path="principal" cssClass="col-xs-3 control-label">
						<fmt:message key="organ.organization.principal"/>
					</sf:label>
					
					<div class="col-xs-9 form-field">
						<mh:value path="principal"/>
					</div>
				</div>
				
				<div class="form-group">
					<sf:label path="prinTel" cssClass="col-xs-3 control-label">
						<fmt:message key="organ.organization.prinTel"/>
					</sf:label>
					
					<div class="col-xs-9 form-field">
						<mh:value path="prinTel"/>
					</div>
				</div>
				
				<div class="form-group">
					<sf:label path="contacter" cssClass="col-xs-3 control-label">
						<fmt:message key="organ.organization.contacter"/>
					</sf:label>
					
					<div class="col-xs-9 form-field">
						<mh:value path="contacter"/>
					</div>
				</div>
				
				<div class="form-group">
					<sf:label path="conTel" cssClass="col-xs-3 control-label">
						<fmt:message key="organ.organization.conTel"/>
					</sf:label>
					
					<div class="col-xs-9 form-field">
						<mh:value path="conTel"/>
					</div>
				</div>
				
				<div class="form-group">
					<sf:label path="address" cssClass="col-xs-3 control-label">
						<fmt:message key="organ.organization.address"/>
					</sf:label>
					
					<div class="col-xs-9 form-field">
						<mh:value path="address"/>
					</div>
				</div>
				
				<div class="form-group">
					<sf:label path="remark" cssClass="col-xs-3 control-label">
						<fmt:message key="organ.organization.remark"/>
					</sf:label>
					
					<div class="col-xs-9 form-field">
						<mh:value path="remark"/>
					</div>
				</div>
				
			</jsp:attribute>
		</mh:section>
		
		
		<div class="form-group form-button-panel">
			<div class="col-xs-9 col-xs-offset-3 form-buttons">
				<mh:button type="link" id="btnBack"  class="btn btn-default" href="#"><fmt:message key="organ.organization.back"/></mh:button>
			</div>
		</div>
	</sf:form>
</div>
<script type="text/javascript">
$(document).ready(function() {
	$("#btnBack").on("click", function(event) {
		history.back();
        event.preventDefault();
	});
});
</script>


