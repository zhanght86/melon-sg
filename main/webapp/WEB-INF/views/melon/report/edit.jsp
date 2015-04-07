<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sp" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html" %>
<%@ taglib prefix="hh" uri="http://www.legendsec.com/melon/helper" %>

<link rel="stylesheet" href="<mh:theme code='jquery.fileupload.css'/>" media="all" />

<script type="text/javascript" src="<mh:theme code='jquery.fileupload.js'/>"></script>

<div class="col-xs-8 col-xs-offset-2 form-singlon">
	<sf:form modelAttribute="report" role="form" cssClass="form-horizontal">
		<sf:hidden path="id"/>
		<sf:hidden path="createDate"/>
		
		<div class="form-group">
			<sf:label path="name" cssClass="col-xs-3 control-label">
				<fmt:message key="hint.required"/>
				<fmt:message key="report.name"/>
			</sf:label>
			
			<div class="col-xs-9 form-field">
				<sf:input path="name" cssClass="form-control" maxlength="20"/>
				<sf:errors path="name"/>
			</div>
		</div>
		
		<div class="form-group">
			<sf:label path="type" cssClass="col-xs-3 control-label">
				<fmt:message key="report.type"/>
			</sf:label>
			
			<div class="col-xs-9 form-field">
				<mh:dictionary var="types" key="report.type"/>
				<sf:radiobuttons path="type" items="${types}"/>
				<sf:errors path="type"/>
			</div>
		</div>
		
		<div class="form-group">
			<sf:label path="category" cssClass="col-xs-3 control-label">
				<fmt:message key="report.category"/>
			</sf:label>
			
			<div class="col-xs-9 form-field">
				<mh:dictionary var="categories" key="report.category"/>
				<sf:radiobuttons path="category" items="${categories}"/>
				<sf:errors path="category"/>
			</div>
		</div>
		
		<div class="form-group">
			<sf:label path="remark" cssClass="col-xs-3 control-label">
				<fmt:message key="report.remark"/>
			</sf:label>
			
			<div class="col-xs-9 form-field">
				<sf:textarea path="remark" cssClass="form-control" maxlength="500" rows="5"/>
				<sf:errors path="remark"/>
			</div>
		</div>
		
		<div class="form-group">
			<sf:label path="businessId" cssClass="col-xs-3 control-label">
				<fmt:message key="report.reportFile"/>
			</sf:label>
			
			<div class="col-xs-9 form-field">
				<sf:hidden path="businessId"  cssClass="form-control"/>
				<mh:upload domainId="${report.businessId}" type="report" multi="false"/>
			</div>
		</div>
		
		<div class="form-group form-button-panel">
			<div class="col-xs-9 col-xs-offset-3 form-buttons">
				<mh:button class="btn btn-primary" id="btnSave"><fmt:message key="button.register"/></mh:button>
				<mh:button class="btn btn-default" href="report/home"><fmt:message key="button.back"/></mh:button> 
			</div>
		</div>
		
	</sf:form>
</div>

<script type="text/javascript">
$(document).ready(function() {
	var reportForm = $("#report");
	//配置验证信息
	 reportForm.validate({
		rules : {
			//基本信息
			name :{
				required : true,
				maxlength : 50
			},
	
			remark : {
				maxlength : 500
			}
		}
	  });
	
	$("#btnSave").on("click",function(event){
		
		if(reportForm.valid() && fileUpload.fileList.children().size()==0) {
			Logger.error("请上传报表文件！");
		}
		if(reportForm.valid() && fileUpload.fileList.children().size()>0){
			reportForm.submit();
		} 
		
		//阻止默认的提交动作
		event.preventDefault();
		});
	 
	}); 
	
</script>
