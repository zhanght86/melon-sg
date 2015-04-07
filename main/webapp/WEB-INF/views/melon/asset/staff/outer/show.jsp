<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>
<%@ taglib prefix="hh" uri="http://www.legendsec.com/melon/helper"%>

<mh:dictionary key="bool" var="booleanEnum" />
<mh:dictionary key="outerstaff.fulljob" var="fullJobEnum"/>
<div class="col-xs-8 col-xs-offset-2 form-singlon">
	
	<sf:form modelAttribute="outerStaff" role="form" cssClass="form-horizontal form-show">
	
		<sf:hidden path="id" />
	  
		<div class="form-group">
			<sf:label path="name" cssClass="col-xs-3 control-label">
				<fmt:message key="melon.outerStaff.name" />
			</sf:label>
	
			<div class="col-xs-9 form-field">
				<mh:value path="name" />
			</div>
		</div>
		
		<%-- <div class="form-group">
			<sf:label path="code" cssClass="col-xs-3 control-label">
				<fmt:message key="melon.outerStaff.code" />
			</sf:label>
	
			<div class="col-xs-9 form-field">
				<mh:value path="code" />
			</div>
		</div> --%>
	
		<div class="form-group">
			<sf:label path="number" cssClass="col-xs-3 control-label">
				<fmt:message key="melon.outerStaff.number" />
			</sf:label>
	
			<div class="col-xs-9 form-field">
				<mh:value path="number" />
			</div>
		</div>
	
		<div class="form-group">
			<sf:label path="sex" cssClass="col-xs-3 control-label">
				<fmt:message key="melon.outerStaff.sex" />
			</sf:label>
	
			<div class="col-xs-9 form-field">
				<mh:dictionary key="sexType" var="sexEnum"/>
				<mh:value path="sex" items="${sexEnum}"/>
			</div>
		</div>
	
		<div class="form-group">
			<sf:label path="birthday" cssClass="col-xs-3 control-label">
				<fmt:message key="melon.outerStaff.birthday" />
			</sf:label>
	
			<div class="col-xs-9 form-field">
				<mh:value path="birthday" />
			</div>
		</div>
		
		<div class="form-group">
			<sf:label path="tel" cssClass="col-xs-3 control-label">
				<fmt:message key="melon.outerStaff.tel" />
			</sf:label>
	
			<div class="col-xs-9 form-field">
				<mh:value path="tel" />
			</div>
		</div>
		
		<div class="form-group">
			<sf:label path="phone" cssClass="col-xs-3 control-label">
				<fmt:message key="melon.outerStaff.phone" />
			</sf:label>
	
			<div class="col-xs-9 form-field">
				<mh:value path="phone"/>
			</div>
		</div>
		
		<div class="form-group">
			<sf:label path="mail" cssClass="col-xs-3 control-label">
				<fmt:message key="melon.outerStaff.mail" />
			</sf:label>
	
			<div class="col-xs-9 form-field">
				<mh:value path="mail" />
			</div>
		</div>
		
		<div class="form-group">
			<sf:label path="companyName" cssClass="col-xs-3 control-label">
				<fmt:message key="melon.outerStaff.companyName" />
			</sf:label>
	
			<div class="col-xs-9 form-field">
				<mh:value path="companyName" />
			</div>
		</div>
		
		<%-- <div class="form-group">
			<sf:label path="departName" cssClass="col-xs-3 control-label">
				<fmt:message key="melon.outerStaff.department"/>
			</sf:label>
			
			<div class="col-xs-9 form-field">
			    <mh:value path="departName" />
			</div>
		</div>
		 --%>
		<div class="form-group">
		    <sf:label path="chargeName" cssClass="col-xs-3 control-label">
				<fmt:message key="melon.outerStaff.chargePerson"/>
			</sf:label>
			
			<div class="col-xs-9 form-field">
			    <mh:value path="chargeName"/>
			</div>
		</div>
		
		<div class="form-group">
			<sf:label path="officeDate" cssClass="col-xs-3 control-label">
				<fmt:message key="melon.outerStaff.officeDate" />
			</sf:label>
	
			<div class="col-xs-9 form-field">
				<mh:value path="officeDate"/>
			</div>
		</div>
	
		<div class="form-group">
			<sf:label path="fullJobs" cssClass="col-xs-3 control-label">
				<fmt:message key="melon.outerStaff.fullJob" />
			</sf:label>
	
			<div class="col-xs-9 form-field">
				<mh:value path="fullJobs" items="${fullJobEnum}"/>
			</div>
		</div>
		
		<div class="form-group">
			<sf:label path="examine" cssClass="col-xs-3 control-label">
				<fmt:message key="melon.outerStaff.examine" />
			</sf:label>
	
			<div class="col-xs-9 form-field">
				<mh:value path="examine" items="${booleanEnum}" />
			</div>
		</div>
		
		<div class="form-group">
			<sf:label path="security" cssClass="col-xs-3 control-label">
				<fmt:message key="melon.outerStaff.security" />
			</sf:label>
	
			<div class="col-xs-9 form-field">
				<mh:value  path="security" items="${booleanEnum}"/>
			</div>
		</div>
		
		<div class="form-group">
			<label class="col-xs-3 control-label">
				<fmt:message key="melon.staff.secAttach" />
			</label>
	
			<div class="col-xs-9 form-field">
				<c:forEach items="${secProFiles}" var="file">
					<div style="height: 34px; padding-top :9px">
						<a href='<c:url value="/system/ajaxfile/download/" />${file.id}'>${file.name}</a>
						<div class="attach-icon"></div>
					</div>
				</c:forEach>
			</div>
		</div>
		
		<div class="form-group">
			<sf:label path="certificates" cssClass="col-xs-3 control-label">
				<fmt:message key="melon.outerStaff.certificate" />
			</sf:label>
	
			<div class="col-xs-9 form-field">
				<mh:dictionary key="interstaff.certificate" var="certificateEnum" />
				<mh:value path="certificates" items="${certificateEnum}"/>
			</div>
		</div>
		
		<div class="form-group">
			<sf:label path="otherCertificate" cssClass="col-xs-3 control-label">
				<fmt:message key="melon.outerStaff.otherCertificate" />
			</sf:label>
	
			<div class="col-xs-9 form-field">
				<mh:value path="otherCertificate" />
			</div>
		</div>
		
		<div class="form-group">
			<label class="col-xs-3 control-label">
				<fmt:message key="melon.staff.cerAttach" />
			</label>
	
			<div class="col-xs-9 form-field">
				<c:forEach items="${cerFiles}" var="file">
					<div style="height: 34px; padding-top :9px">
						<a href='<c:url value="/system/ajaxfile/download/" />${file.id}'>${file.name}</a>
						<div class="attach-icon"></div>
					</div>
				</c:forEach>
			</div>
		</div>
		
		<div class="form-group">
			<sf:label path="maintainAutor" cssClass="col-xs-3 control-label">
				<fmt:message key="melon.outerStaff.maintainAutor" />
			</sf:label>
	
			<div class="col-xs-9 form-field">
				<mh:value path="maintainAutor" />
			</div>
		</div>
		
		<div class="form-group">
			<sf:label path="project" cssClass="col-xs-3 control-label">
				<fmt:message key="melon.outerStaff.project" />
			</sf:label>
	
			<div class="col-xs-9 form-field">
				<mh:value path="project" />
			</div>
		</div>
		
		<div class="form-group">
			<sf:label path="work" cssClass="col-xs-3 control-label">
				<fmt:message key="melon.outerStaff.work" />
			</sf:label>
	
			<div class="col-xs-9 form-field">
				<mh:value path="work" />
			</div>
		</div>
		
		<div class="form-group">
			<sf:label path="remark" cssClass="col-xs-3 control-label">
				<fmt:message key="melon.outerStaff.remark" />
			</sf:label>
	
			<div class="col-xs-9 form-field">
				<mh:value path="remark" />
			</div>
		</div>
	
		<div class="form-group form-button-panel">
			<div class="col-xs-9 col-xs-offset-3 form-buttons">
				<mh:button class="btn btn-default" id="btnBack" href="staff/outer/home">
					<fmt:message key="button.back" />
				</mh:button>
			</div>
		</div>
	
	</sf:form>
</div>
