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
	<sf:form modelAttribute="flowLayout" role="form" cssClass="form-horizontal form-show">
	
		<sf:hidden path="id"/>
		
		<mh:section id="basic">
		
			<jsp:attribute name="title">
				<mh:value path="name"/>
			</jsp:attribute>
			
			<jsp:attribute name="body">
				
				<div class="form-group">
					<sf:label path="name" cssClass="col-xs-3 control-label">
						<fmt:message key="base.flowLayout.name"/>
					</sf:label>
					
					<div class="col-xs-9 form-field">
						<mh:value path="name"/>
					</div>
				</div>
				
				<div class="form-group">
					<sf:label path="type" cssClass="col-xs-3 control-label">
						<fmt:message key="base.flowLayout.type"/>
					</sf:label>
					
					<div class="col-xs-9 form-field">
						<mh:dictionary var="type" key="flowLayout.type"/>
						<mh:value path="type" items="${type}"/>
					</div>
				</div>
				
				<div class="form-group">
					<sf:label path="createTime" cssClass="col-xs-3 control-label">
						<fmt:message key="base.flowLayout.createTime"/>
					</sf:label>
					
					<div class="col-xs-9 form-field">
						<mh:value path="createTime"/>
					</div>
				</div>
				
				<div class="form-group">
					<sf:label path="remarks" cssClass="col-xs-3 control-label">
						<fmt:message key="base.flowLayout.remarks"/>
					</sf:label>
					
					<div class="col-xs-9 form-field">
						<mh:value path="remarks" cssStyle="font-size : 14px;"/>
					</div>
				</div>
			</jsp:attribute>
		</mh:section>
				
		<div class="form-group form-button-panel">
			<div class="col-xs-9 col-xs-offset-3 form-buttons">
				<sec:authorize url="base/flowLayout/update/*">
					<mh:button class="btn btn-primary" href="base/flowLayout/update/${flowLayout.id}"><fmt:message key="button.update"/></mh:button>
				</sec:authorize>
				<mh:button class="btn btn-default" href="base/flowLayout/list"><fmt:message key="button.backList"/></mh:button>
			</div>
		</div>
			
	</sf:form>
</div>
