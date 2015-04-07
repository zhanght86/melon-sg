<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>
<%@ taglib prefix="hh" uri="http://www.legendsec.com/melon/helper"%>
<%--@Author 甘焕--%>
<mh:section id="network">

	<jsp:attribute name="title">
		<fmt:message key="asset.infoSystem.duty" />
	</jsp:attribute>

	<jsp:attribute name="body">
		<div class="col-xs-8 col-xs-offset-2 form-singlon">
			<%-- 业务类型 
			<div class="form-group">
				<sf:label path="sysType" cssClass="col-xs-3 control-label">
					<fmt:message key="db.sysInfo.busType" />
				</sf:label>
				<div class="col-xs-9 form-field">
					<mh:dictionary var="sysType" key="sysType"/>
					<sf:select path="sysType" items="${sysType}" class="form-control"/>
					<sf:errors path="sysType" />
				</div>
			</div>--%> 
			
			<%--服务范围
			<div class="form-group">
				<sf:label path="serScope" cssClass="col-xs-3 control-label">
					<fmt:message key="db.sysInfo.netScope" />
				</sf:label>
				<div class="col-xs-9 form-field">
					<mh:dictionary var="serScope" key="serScope" />
					<sf:select path="serScope" items="${serScope}" class="form-control"/>
				<sf:errors path="serScope" />
				</div>
			</div>
			
			
			<%--服务对象
			<div class="form-group">
				<sf:label path="serObject" cssClass="col-xs-3 control-label">
					<fmt:message key="db.sysInfo.serObject" />
				</sf:label>
				<div class="col-xs-9 form-field">
					<mh:dictionary var="serObject" key="serObject" />
					<sf:select path="serObject" items="${serObject}" class="form-control"/>
					<sf:errors path="serObject" />
				</div>
			</div>--%> 
					
			<%--覆盖范围
			<div class="form-group">
				<sf:label path="netScope" cssClass="col-xs-3 control-label">
					<fmt:message key="db.sysInfo.netScope" />
				</sf:label>
				<div class="col-xs-9 form-field">
					<mh:dictionary var="netScope" key="netScope" />
					<sf:select path="netScope" items="${netScope}" class="form-control"/>
				<sf:errors path="netScope" />
				</div>
			</div>--%> 
					
		
			<%--网络性质 
			<div class="form-group">
				<sf:label path="netType" cssClass="col-xs-3 control-label">
					<fmt:message key="db.sysInfo.netType" />
				</sf:label>
				<div class="col-xs-9 form-field">
					<mh:dictionary var="netType" key="netType" />
					<sf:radiobuttons path="netType" items="${netType}" />
					<sf:errors path="netType" />
				</div>
			</div>--%> 
			
			
			<%--系统互联情况
			<div class="form-group">
				<sf:label path="connType" cssClass="col-xs-3 control-label">
					<fmt:message key="asset.infoSystem.connType" />
				</sf:label>
				<div class="col-xs-9 connType">
					<mh:dictionary var="connType" key="connType" />
					<sf:select path="connType" items="${connType}" class="form-control"/>
					<sf:errors path="connType" />
				</div>
			</div>--%>
		</div>
	</jsp:attribute>

</mh:section>