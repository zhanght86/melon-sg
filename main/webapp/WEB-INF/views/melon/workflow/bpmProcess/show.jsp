<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%-- 单独的表单必须嵌入到此样式标签里 --%>
<div class="col-xs-4" id="melon-nav-panel">
	<ul class="nav nav-pills nav-stacked affix">
		<li><a href="#basic"><fmt:message key="workflow.bpmProcess.basic"/></a></li>
		<li><a href="#process"><fmt:message key="workflow.bpmProcess.process"/></a></li>
		<li><a href="#other"><fmt:message key="workflow.bpmProcess.other"/></a></li>
	</ul>
</div>

<%-- 单独的表单必须嵌入到此样式标签里 --%>
<div class="col-xs-8 col-xs-offset-2 form-singlon">
	<sf:form modelAttribute="bpmProcess" role="form" cssClass="form-horizontal form-show">
		<sf:hidden path="id"/>
		
		<mh:section id="basic">
		
			<jsp:attribute name="title">
				<fmt:message key="workflow.bpmProcess.basic"/>
			</jsp:attribute>
			
			<jsp:attribute name="body">
				<div class="form-group">
					<sf:label path="code" cssClass="col-xs-3 control-label">
						<fmt:message key="workflow.bpmProcess.code"/>
					</sf:label>
					
					<div class="col-xs-9 form-field">
						<mh:value path="code"/>
					</div>
				</div>
				 
				<div class="form-group">
					<sf:label path="name" cssClass="col-xs-3 control-label">
						<fmt:message key="workflow.bpmProcess.name"/>
					</sf:label>
					
					<div class="col-xs-9 form-field">
						<mh:value path="name"/>
					</div>
				</div>
				
				<div class="form-group">
					<sf:label path="type" cssClass="col-xs-3 control-label">
						<fmt:message key="workflow.bpmProcess.type"/>
					</sf:label>
					
					<div class="col-xs-9 form-field">
						<mh:value path="type"/>
					</div>
				</div>
				
				<div class="form-group">
					<sf:label path="props" class="col-xs-3 control-label">
						<fmt:message key="workflow.bpmProcess.props" />
					</sf:label>
			
					<div class="col-xs-9 form-field">
						<c:forEach items="${bpmProcess.props}" var="prop">
							<p style="padding:5px 0;font-size:16px">
								<span style="margin-right:10px">${prop.key}</span>
								<span>${prop.value}</span>
							</p>
						</c:forEach>
					</div>
				</div>
				
			</jsp:attribute>
			
		</mh:section>
		
		<mh:section id="process">

			<jsp:attribute name="title">
				<fmt:message key="workflow.bpmProcess.process"/>
			</jsp:attribute>		

			<jsp:attribute name="body">			
			</jsp:attribute>

		</mh:section>
														   
		<mh:section id="other">
		
			<jsp:attribute name="title">
				<fmt:message key="workflow.bpmProcess.other"/>
			</jsp:attribute>
			
			<jsp:attribute name="body">
				<div class="form-group">
					<sf:label path="remarks" cssClass="col-xs-3 control-label">
						<fmt:message key="workflow.bpmProcess.remarks"/>
					</sf:label>
					
					<div class="col-xs-9 form-field">
						<mh:value path="remarks" cssStyle="font-size : 14px;"/>
					</div>
				</div>
			</jsp:attribute>
			
		</mh:section>
				
		<div class="form-group form-button-panel">
			<div class="col-xs-9 col-xs-offset-3 form-buttons">
				<sec:authorize url="workflow/bpmProcess/update/*">
					<mh:button class="btn btn-primary" href="workflow/bpmProcess/update/${bpmProcess.id}"><fmt:message key="button.update"/></mh:button>
				</sec:authorize>
			    <mh:button class="btn btn-default" href="workflow/bpmProcess/list"><fmt:message key="button.backList"/></mh:button>
			</div>
		</div>
			
	</sf:form>
</div>


