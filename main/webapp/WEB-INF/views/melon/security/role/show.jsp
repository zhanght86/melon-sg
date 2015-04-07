<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<link rel="stylesheet" href="<mh:theme code='jquery.jstree.css'/>" media="all" />

<script type="text/javascript" src="<mh:theme code='jquery.jstree.js'/>"></script>
<script type="text/javascript" src="<mh:theme code='melon.ajaxtree.js'/>"></script>

<%-- 单独的表单必须嵌入到此样式标签里 --%>
<div class="col-xs-8 col-xs-offset-2 form-singlon">
	<sf:form modelAttribute="role" role="form" cssClass="form-horizontal form-show">
	
		<sf:hidden path="id"/>
		
		<mh:section id="basic">
		
			<jsp:attribute name="title">
				<mh:value path="name"/>
			</jsp:attribute>
			
			<jsp:attribute name="body">
				
				<div class="form-group">
					<sf:label path="name" cssClass="col-xs-3 control-label">
						<fmt:message key="security.role.name"/>
					</sf:label>
					
					<div class="col-xs-9 form-field">
						<mh:value path="name"/>
					</div>
				</div>
				
				<div class="form-group">
					<sf:label path="code" cssClass="col-xs-3 control-label">
						<fmt:message key="security.role.code"/>
					</sf:label>
					
					<div class="col-xs-9 form-field">
						<mh:value path="code"/>
					</div>
				</div>
				
				<div class="form-group">
					<sf:label path="level" cssClass="col-xs-3 control-label">
						<fmt:message key="security.role.level"/>
					</sf:label>
					
					<div class="col-xs-9 form-field">
						<mh:dictionary var="levelEnum" key="organLevel"/>
						<mh:value path="level" items="${levelEnum}"/>
					</div>
				</div>
				
				<div class="form-group">
					<sf:label path="mutex" cssClass="col-xs-3 control-label">
						<fmt:message key="security.role.mutex"/>
					</sf:label>
					
					<div class="col-xs-9 form-field">
						<mh:dictionary var="boolenums" key="bool"/>
						<mh:value path="mutex" items="${boolenums}"/>
					</div>
				</div>
				
				<div class="form-group">
					<sf:label path="order" cssClass="col-xs-3 control-label">
						<fmt:message key="security.role.order"/>
					</sf:label>
					
					<div class="col-xs-9 form-field">
						<mh:value path="order"/>
					</div>
				</div>
				
				<div class="form-group">
					<sf:label path="resources" cssClass="col-xs-3 control-label">
						<fmt:message key="security.role.resource"/>
					</sf:label>
					
					<div class="col-xs-9 form-field">
						<mh:tree id="menu"
								rootId="${role.id}"
								url="security/role/resource"
								sortName="order"
								multiSelect="false"
								scriptSelf="false">
						</mh:tree>
					</div>
				</div>
				
				<div class="form-group">
					<sf:label path="remarks" cssClass="col-xs-3 control-label">
						<fmt:message key="security.role.remarks"/>
					</sf:label>
					
					<div class="col-xs-9 form-field">
						<mh:value path="remarks" cssStyle="font-size : 14px;"/>
					</div>
				</div>
			</jsp:attribute>
		</mh:section>
				
		<div class="form-group form-button-panel">
			<div class="col-xs-9 col-xs-offset-3 form-buttons">
				<mh:button class="btn btn-primary" href="security/role/update/${role.id}"><fmt:message key="button.update"/></mh:button>
				<mh:button class="btn btn-default" href="security/role/list"><fmt:message key="button.backList"/></mh:button>
			</div>
		</div>
			
	</sf:form>
</div>
