<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>
<%@ taglib prefix="hh" uri="http://www.legendsec.com/melon/helper"%>

<mh:dictionary key="bool" var="booleanEnum" />
<mh:dictionary key="boolInt" var="booleanIntEnum" />
<mh:dictionary key="interstaff.partjob" var="partJobEnum"/>
<div class="col-xs-8 col-xs-offset-2 form-singlon">
	
	<sf:form modelAttribute="interStaff" role="form" cssClass="form-horizontal form-show">
	
		<sf:hidden path="id" />
	  
		<div class="form-group">
			<sf:label path="name" cssClass="col-xs-3 control-label">
				<fmt:message key="melon.interStaff.name" />
			</sf:label>
	
			<div class="col-xs-9 form-field">
				<mh:value path="name" />
			</div>
		</div>
		
		<div class="form-group">
			<sf:label path="code" cssClass="col-xs-3 control-label">
				<fmt:message key="melon.interStaff.code" />
			</sf:label>
	
			<div class="col-xs-9 form-field">
				<mh:value path="code" />
			</div>
		</div>
	
		<div class="form-group">
			<sf:label path="number" cssClass="col-xs-3 control-label">
				<fmt:message key="melon.interStaff.number" />
			</sf:label>
	
			<div class="col-xs-9 form-field">
				<mh:value path="number" />
			</div>
		</div>
	
		<div class="form-group">
			<sf:label path="sex" cssClass="col-xs-3 control-label">
				<fmt:message key="melon.interStaff.sex" />
			</sf:label>
	
			<div class="col-xs-9 form-field">
				<mh:dictionary key="sexType" var="sexEnum"/>
				<mh:value path="sex" items="${sexEnum}"/>
			</div>
		</div>
	
		<div class="form-group">
			<sf:label path="birthday" cssClass="col-xs-3 control-label">
				<fmt:message key="melon.interStaff.birthday" />
			</sf:label>
	
			<div class="col-xs-9 form-field">
				<mh:value path="birthday" />
			</div>
		</div>
		
		<div class="form-group">
			<sf:label path="tel" cssClass="col-xs-3 control-label">
				<fmt:message key="melon.interStaff.tel" />
			</sf:label>
	
			<div class="col-xs-9 form-field">
				<mh:value path="tel" />
			</div>
		</div>
		
		<div class="form-group">
			<sf:label path="phone" cssClass="col-xs-3 control-label">
				<fmt:message key="melon.interStaff.phone" />
			</sf:label>
	
			<div class="col-xs-9 form-field">
				<mh:value path="phone"/>
			</div>
		</div>
		
		<div class="form-group">
			<sf:label path="mail" cssClass="col-xs-3 control-label">
				<fmt:message key="melon.interStaff.mail" />
			</sf:label>
	
			<div class="col-xs-9 form-field">
				<mh:value path="mail" />
			</div>
		</div>
		
		<div class="form-group">
			<sf:label path="organName" cssClass="col-xs-3 control-label">
				<fmt:message key="melon.interStaff.organ" />
			</sf:label>
	
			<div class="col-xs-9 form-field">
				<mh:value path="organName" />
			</div>
		</div>
		
		<div class="form-group">
			<sf:label path="departName" cssClass="col-xs-3 control-label">
				<fmt:message key="melon.interStaff.department"/>
			</sf:label>
			
			<div class="col-xs-9 form-field">
			    <mh:value path="departName" />
			</div>
		</div>
		
		<div class="form-group">
		    <sf:label path="chargeName" cssClass="col-xs-3 control-label">
				<fmt:message key="melon.interStaff.chargePerson"/>
			</sf:label>
			
			<div class="col-xs-9 form-field">
			    <mh:value path="chargeName"/>
			</div>
		</div>
		
		<div class="form-group">
			<sf:label path="officeDate" cssClass="col-xs-3 control-label">
				<fmt:message key="melon.interStaff.officeDate" />
			</sf:label>
	
			<div class="col-xs-9 form-field">
				<mh:value path="officeDate"/>
			</div>
		</div>
	
		<div class="form-group">
			<sf:label path="fullJobs" cssClass="col-xs-3 control-label">
				<fmt:message key="melon.interStaff.fullJob" />
			</sf:label>
	
			<div class="col-xs-9 form-field">
				<mh:dictionary key="interstaff.fulljob" var="fullJobEnum"/>
				<mh:value path="fullJobs" items="${fullJobEnum}"/>
			</div>
		</div>
		
		<div class="form-group">
			<sf:label path="partJobs" cssClass="col-xs-3 control-label">
				<fmt:message key="melon.interStaff.partJob" />
			</sf:label>
	
			<div class="col-xs-9 form-field">
				<mh:value path="partJobs" items="${partJobEnum}"/>
			</div>
		</div>
		
		<div class="form-group">
			<sf:label path="historyJob" cssClass="col-xs-3 control-label">
				<fmt:message key="melon.interStaff.historyJob" />
			</sf:label>
	
			<div class="col-xs-9 form-field">
				<mh:value path="historyJob" items="${partJobEnum}"/>
			</div>
		</div>
		
		<div class="form-group">
			<sf:label path="title" cssClass="col-xs-3 control-label">
				<fmt:message key="melon.interStaff.title" />
			</sf:label>
	
			<div class="col-xs-9 form-field">
				<mh:dictionary key="interstaff.title" var="titleEnum" />
				<mh:value path="title" items="${titleEnum}"/>
			</div>
		</div>
		
		<div class="form-group">
			<sf:label path="techTitle" cssClass="col-xs-3 control-label">
				<fmt:message key="melon.interStaff.techTitle" />
			</sf:label>
	
			<div class="col-xs-9 form-field">
				<mh:dictionary key="interstaff.techtitle" var="techtitleEnum" />
				<mh:value path="techTitle" items="${techtitleEnum}"/>
			</div>
		</div>
		
		<div class="form-group">
			<sf:label path="techSkills" cssClass="col-xs-3 control-label">
				<fmt:message key="melon.interStaff.techSkill" />
			</sf:label>
	
			<div class="col-xs-9 form-field">
				<mh:dictionary key="interstaff.techSkill" var="teckSkillEnum" />
				<mh:value path="techSkills" items="${teckSkillEnum}"/>
			</div>
		</div>
		
		<div class="form-group">
			<sf:label path="otherSkill" cssClass="col-xs-3 control-label">
				<fmt:message key="melon.interStaff.otherSkill" />
			</sf:label>
	
			<div class="col-xs-9 form-field">
				<mh:value path="otherSkill"/>
			</div>
		</div>
		
		<div class="form-group">
			<sf:label path="examine" cssClass="col-xs-3 control-label">
				<fmt:message key="melon.interStaff.examine" />
			</sf:label>
	
			<div class="col-xs-9 form-field">
				<mh:value path="examine" items="${booleanEnum}" />
			</div>
		</div>
		
		<div class="form-group">
			<sf:label path="lecturer" cssClass="col-xs-3 control-label">
				<fmt:message key="melon.interStaff.lecturer" />
			</sf:label>
	
			<div class="col-xs-9 form-field">
				<mh:value path="lecturer" items="${booleanIntEnum}"/>
			</div>
		</div>
		
		<div class="form-group">
			<sf:label path="expert" cssClass="col-xs-3 control-label">
				<fmt:message key="melon.interStaff.expert" />
			</sf:label>
	
			<div class="col-xs-9 form-field">
				<mh:value path="expert" items="${booleanIntEnum}"/>
			</div>
		</div>
		
		<div class="form-group">
			<sf:label path="projectTeam" cssClass="col-xs-3 control-label">
				<fmt:message key="melon.interStaff.projectTeam" />
			</sf:label>
	
			<div class="col-xs-9 form-field">
				<mh:value path="projectTeam" items="${booleanIntEnum}"/>
			</div>
		</div>
		
		<div class="form-group">
			<sf:label path="security" cssClass="col-xs-3 control-label">
				<fmt:message key="melon.interStaff.security" />
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
				<fmt:message key="melon.interStaff.certificate" />
			</sf:label>
	
			<div class="col-xs-9 form-field">
				<mh:dictionary key="interstaff.certificate" var="certificateEnum" />
				<mh:value path="certificates" items="${certificateEnum}"/>
			</div>
			
		</div>
		
		<div class="form-group">
			<sf:label path="otherCertificate" cssClass="col-xs-3 control-label">
				<fmt:message key="melon.interStaff.otherCertificate" />
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
						<a href='<c:url value="/system/ajaxfile/download/" />${file.id}' >${file.name}</a>
						<div class="attach-icon"></div>
					</div>
				</c:forEach>
			</div>
		</div>
		
		<div class="form-group">
			<sf:label path="paperNum" cssClass="col-xs-3 control-label">
				<fmt:message key="melon.interStaff.paperNum" />
			</sf:label>
	
			<div class="col-xs-9 form-field">
				<mh:value path="paperNum" />
			</div>
		</div>
		
		<div class="form-group">
			<label class="col-xs-3 control-label">
				<fmt:message key="melon.staff.paperAttach" />
			</label>
	
			<div class="col-xs-9 form-field">
				<c:forEach items="${paperFiles}" var="file">
					<div style="height: 34px; padding-top :9px">
						<a href='<c:url value="/system/ajaxfile/download/" />${file.id}' >${file.name}</a>
						<div class="attach-icon"></div>
					</div>
				</c:forEach>
			</div>
		</div>
		
		<div class="form-group">
			<sf:label path="remark" cssClass="col-xs-3 control-label">
				<fmt:message key="melon.interStaff.remark" />
			</sf:label>
	
			<div class="col-xs-9 form-field">
				<mh:value path="remark" />
			</div>
		</div>
	
		<div class="form-group form-button-panel">
			<div class="col-xs-9 col-xs-offset-3 form-buttons">
				<mh:button class="btn btn-default" id="btnBack" href="staff/inter/list">
					<fmt:message key="button.back" />
				</mh:button>
			</div>
		</div>
	
	</sf:form>
</div>
