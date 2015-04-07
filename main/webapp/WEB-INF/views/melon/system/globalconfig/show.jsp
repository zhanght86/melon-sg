<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%-- 单独的表单必须嵌入到此样式标签里 --%>
<div class="col-xs-8 col-xs-offset-2 form-singlon">
	<sf:form modelAttribute="globalConfig" role="form" cssClass="form-horizontal form-show">
		
		<div class="form-group">
			<sf:label path="organName" cssClass="col-xs-3 control-label">
				<fmt:message key="system.globalConfig.organName" />
			</sf:label>
	
			<div class="col-xs-9 form-field">
				<mh:value path="organName" />
			</div>
		</div>
		
		<div class="form-group">
			<sf:label path="servicer" cssClass="col-xs-3 control-label">
				<fmt:message key="system.globalConfig.servicer" />
			</sf:label>
	
			<div class="col-xs-9 form-field">
				<mh:value path="servicer" />
			</div>
		</div>
		
		<div class="form-group">
			<sf:label path="serviceTel" cssClass="col-xs-3 control-label">
				<fmt:message key="system.globalConfig.serviceTel" />
			</sf:label>
	
			<div class="col-xs-9 form-field">
				<mh:value path="serviceTel" />
			</div>
		</div>
		
		<div class="form-group">
			<sf:label path="serviceMail" cssClass="col-xs-3 control-label">
				<fmt:message key="system.globalConfig.serviceMail" />
			</sf:label>
	
			<div class="col-xs-9 form-field">
				<mh:value path="serviceMail" />
			</div>
		</div>
		
		<div class="form-group">
			<label class="col-xs-3 control-label">
				<fmt:message key="system.globalConfig.attributes" />
			</label>
	
			<div class="col-xs-9 form-field">
				<c:forEach items="${globalConfig.attributes}" var="attribute">
					<p style="padding:5px 0;font-size:16px">
						<span style="margin-right:10px">${attribute.key}</span>
						<span>${attribute.value}</span>
					</p>
				</c:forEach>
			</div>
		</div>
		
		<div class="form-group">
			<sf:label path="passStrength" cssClass="col-xs-3 control-label">
				<fmt:message key="system.globalConfig.passStrength" />
			</sf:label>
	
			<div class="col-xs-9 form-field">
				<mh:dictionary key="pass.strength" var="passEnum"/>
				<mh:value path="passStrength" items="${passEnum}" />
			</div>
		</div>
		
		<div class="form-group">
			<sf:label path="openStart" cssClass="col-xs-3 control-label">
				<fmt:message key="system.globalConfig.open" />
			</sf:label>
	
			<div class="col-xs-9 form-field">
				<mh:value path="openStart" />时至<mh:value path="openEnd" />时
			</div>
		</div>
		
		<div class="form-group">
			<sf:label path="ipLeftRange" cssClass="col-xs-3 control-label">
				<fmt:message key="system.globalConfig.ip" />
			</sf:label>
	
			<div class="col-xs-9 form-field">
				<mh:value path="ipLeftRange" />-<mh:value path="ipRightRange" />
			</div>
		</div>
		
		<div class="form-group">
			<sf:label path="tryTimes" cssClass="col-xs-3 control-label">
				<fmt:message key="system.globalConfig.try" />
			</sf:label>
	
			<div class="col-xs-9 form-field">
				<mh:value path="tryTimes" />
			</div>
		</div>
		
		<div class="form-group">
			<sf:label path="lockedTime" cssClass="col-xs-3 control-label">
				<fmt:message key="system.globalConfig.locked" />
			</sf:label>
	
			<div class="col-xs-9 form-field">
				<mh:value path="lockedTime" />
			</div>
		</div>
		
		<div class="form-group">
			<sf:label path="validity" cssClass="col-xs-3 control-label">
				<fmt:message key="system.globalConfig.validity" />
			</sf:label>
	
			<div class="col-xs-9 form-field">
				<mh:value path="validity" />
			</div>
		</div>
		
		<div class="form-group form-button-panel">
			<div class="col-xs-9 col-xs-offset-3 form-buttons">
				<mh:button class="btn btn-default" href="system/globalConfig/update"><fmt:message key="button.update"/></mh:button>
			</div>
		</div>
		
	</sf:form>
</div>
