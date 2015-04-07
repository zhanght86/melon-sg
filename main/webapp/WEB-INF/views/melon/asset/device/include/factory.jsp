<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>
<%@ taglib prefix="hh" uri="http://www.legendsec.com/melon/helper"%>

<%--@Author 甘焕--%>

<mh:section id="factory">
	<jsp:attribute name="title">
		<fmt:message key="asset.device.produ" />
	</jsp:attribute>
	
	<jsp:attribute name="body">
		<div class="col-xs-8 col-xs-offset-2 form-singlon">
		
			<%--型号--%>
			<div class="form-group">
				<sf:label path="model" cssClass="col-xs-3 control-label">
					<fmt:message key="asset.device.model" />
				</sf:label>
				<div class="col-xs-9 form-field">
					<sf:input path="model" cssClass="form-control" maxlength="100" />
					<sf:errors path="model" />
				</div>
			</div>
		
			<%--厂商--%>
			<div class="form-group">
				<sf:label path="producer" cssClass="col-xs-3 control-label">
					<fmt:message key="asset.device.producer" />
				</sf:label>
				<div class="col-xs-9 form-field">
					<sf:input path="producer" cssClass="form-control" maxlength="200" />
					<sf:errors path="producer" />
				</div>
			</div>

			<%--厂商联系方式--%>
			<div class="form-group">
				<sf:label path="producerTel" cssClass="col-xs-3 control-label">
					<fmt:message key="asset.device.producerTel" />
				</sf:label>
				<div class="col-xs-9 form-field">
					<sf:input path="producerTel" cssClass="form-control" maxlength="200" />
					<sf:errors path="producerTel" />
				</div>
			</div>

			<%--出厂日期--%>
			<div class="form-group">
				<sf:label path="produceTime" cssClass="col-xs-3 control-label">
					<fmt:message key="asset.device.produceTime" />
				</sf:label>
				<div class="col-xs-9 form-field">
					<sf:input path="produceTime" cssClass="form-control" maxlength="50"  />
					<sf:errors path="produceTime" />
				</div>
			</div>
			
			
			<%--保修开始时间 --%>
			<div class="form-group">
				<sf:label path="catTime" cssClass="col-xs-3 control-label">
					<fmt:message key="asset.device.catTime" />
				</sf:label>
				<div class="col-xs-9 form-field">
					<sf:input path="catTime" cssClass="form-control" maxlength="50"  />
					<sf:errors path="catTime" />
				</div>
			</div>
			
			<%--保修到期时间--%>
			<div class="form-group">
				<sf:label path="deadline" cssClass="col-xs-3 control-label">
					<fmt:message key="asset.device.deadline" />
				</sf:label>
				<div class="col-xs-9 form-field">
					<sf:input path="deadline" cssClass="form-control" maxlength="50"  />
					<sf:errors path="deadline" />
				</div>
			</div>
			
			
			
		</div>
	</jsp:attribute>
</mh:section>