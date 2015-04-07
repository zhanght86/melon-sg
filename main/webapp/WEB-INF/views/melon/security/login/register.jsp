<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>
<%@ taglib prefix="hh" uri="http://www.legendsec.com/melon/helper" %>

<link rel="stylesheet" href="<mh:theme code='jquery.select.css'/>" media="all" />
<script type="text/javascript" src="<mh:theme code='jquery.select.js'/>"></script>

<sf:form modelAttribute="account" role="form" cssClass="form-horizontal">
	<sf:hidden path="id"/>
	<sf:hidden path="version"/>
	<sf:hidden path="createTime"/>
	
	<div id="account-wizard">
		<%-- 基本信息 --%>
		<mh:section id="basic">
			<jsp:attribute name="title">
				<fmt:message key="security.account.basic"/>
			</jsp:attribute>
			<jsp:attribute name="body">
				<div class="col-xs-8 col-xs-offset-2 form-singlon">
				
			 		<div class="form-group">
						<sf:label path="companyName" cssClass="col-xs-3 control-label">
							<fmt:message key="hint.required"/>
							<fmt:message key="security.account.companyName"/>
						</sf:label>
						<div class="col-xs-9 form-field">
							<sf:hidden path="companyId"/>
							<sf:input path="companyName" cssClass="form-control" id="company" maxlength="100"/>
							<hh:select id="company" onceUrl="organ/organization/findOrgans?parentId=0&type=1" />
							<sf:errors path="companyName"/>
						</div>
					</div>
		
					<div class="form-group">
						<sf:label path="departName" cssClass="col-xs-3 control-label">
						    <fmt:message key="hint.required"/>
							<fmt:message key="security.account.departName"/>
						</sf:label>
						<div class="col-xs-9 form-field">
						    <sf:hidden path="departName"/>
							<sf:select path="departId" cssClass="form-control" maxlength="100"/>
							<sf:errors path="departName"/>
						</div>
					</div>
			 		
				 	<div class="form-group">
						<sf:label path="name" cssClass="col-xs-3 control-label">
						    <fmt:message key="hint.required"/>
							<fmt:message key="security.account.name" />
						</sf:label>
						<div class="col-xs-9 form-field">
							<sf:input path="name" cssClass="form-control" maxlength="100"/>
							<sf:errors path="name"/>
						</div>
					</div>
					
					<div class="form-group">
						<sf:label path="duty" cssClass="col-xs-3 control-label">
							<fmt:message key="security.account.duty" />
						</sf:label>
						<div class="col-xs-9 form-field">
							<sf:input path="duty" cssClass="form-control" maxlength="100"/>
							<sf:errors path="duty"/>
						</div>
					</div>
					
					<div class="form-group">
						<sf:label path="code" cssClass="col-xs-3 control-label">
							<fmt:message key="security.account.code"/>
						</sf:label>
						<div class="col-xs-9 form-field">
							<sf:input path="code" cssClass="form-control" maxlength="50"/>
							<sf:errors path="code"/>
						</div>
					</div>
					
					<div class="form-group">
					  	<sf:label path="order" cssClass="col-xs-3 control-label">
							<fmt:message key="security.account.order"/>
						</sf:label>
						<div class="col-xs-9 form-field">
							<sf:input path="order" cssClass="form-control" />
							<sf:errors path="order"/>
							<%-- <label class="form-hint"><fmt:message key="hint.order"/></label> --%>
						</div>
					</div>
					
					<div class="form-group">
						<sf:label path="email" cssClass="col-xs-3 control-label">
						    <fmt:message key="hint.required"/>
							<fmt:message key="security.account.email"/>
						</sf:label>
						<div class="col-xs-9 form-field">
							<sf:input path="email" cssClass="form-control" maxlength="50"/>
							<sf:errors path="email"/>
						</div>
					</div>
					
					<div class="form-group">
						<sf:label path="tel" cssClass="col-xs-3 control-label">
							<fmt:message key="security.account.tel"/>
						</sf:label>
						<div class="col-xs-9 form-field">
							<sf:input path="tel" cssClass="form-control" maxlength="50"/>
							<sf:errors path="tel"/>
						</div>
					</div>
					
					<div class="form-group">
						<sf:label path="mobile" cssClass="col-xs-3 control-label">
							<fmt:message key="security.account.mobile"/>
						</sf:label>
						<div class="col-xs-9 form-field">
							<sf:input path="mobile" cssClass="form-control" maxlength="50"/>
							<sf:errors path="mobile"/>
						</div>
					</div>
				</div>	
			</jsp:attribute>
		</mh:section>
	
		<%-- 权限信息 --%>
		<mh:section id="profile">
			<jsp:attribute name="title">
				<fmt:message key="security.account.profile"/>
			</jsp:attribute>
			<jsp:attribute name="body">
				<div class="col-xs-8 col-xs-offset-2 form-singlon">
					<sf:hidden path="accountExpired"/>
					<sf:hidden path="accountLocked"/>
					<sf:hidden path="credentialsExpired"/>	
			        <sf:hidden path="roles"/>
					<div class="form-group">
						<sf:label path="username" cssClass="col-xs-3 control-label">
							<fmt:message key="hint.required"/>
							<fmt:message key="security.account.username"/>
						</sf:label>
						<div class="col-xs-9 form-field">
							<sf:input path="username" cssClass="form-control"  maxlength="20"/>
							<sf:errors path="username"/>
						</div>
					</div>
						
					<div class="form-group" id="passwordcontent">
						<sf:label path="password" cssClass="col-xs-3 control-label">
							<fmt:message key="hint.required"/>
							<fmt:message key="security.account.password" />
						</sf:label>
						<div class="col-xs-9 form-field">
						    <sf:password path="password" cssClass="form-control" maxlength="128"/>
							<sf:errors path="password"/>
						</div>
					</div>
						
					<div class="form-group" id="confirmPassword">
						<sf:label path="confirmPassword" cssClass="col-xs-3 control-label">
							<fmt:message key="hint.required"/>
							<fmt:message key="security.account.confirmPassword"/>
						</sf:label>
						<div class="col-xs-9 form-field">
							<sf:password path="confirmPassword" cssClass="form-control" />
							<sf:errors path="confirmPassword"/>
						</div>
					</div>
						
				</div>	
			</jsp:attribute>
		</mh:section>
		
		<%--其他信息 --%>
		<mh:section id="other">
			<jsp:attribute name="title">
				<fmt:message key="security.account.other"/>
			</jsp:attribute>
			<jsp:attribute name="body">
				<div class="col-xs-8 col-xs-offset-2 form-singlon">
				<sf:hidden path="enabled"/>
					<div class="form-group">
						<sf:label path="remarks" cssClass="col-xs-3 control-label">
							<fmt:message key="security.account.remarks"/>
						</sf:label>
						<div class="col-xs-9 form-field">
							<sf:textarea path="remarks" cssClass="form-control"  rows="5" maxlength="1000"/>
							<sf:errors path="remarks"/>
						</div>
					</div>
				
				</div>
			</jsp:attribute>
		</mh:section>

	</div>
</sf:form>
<link rel="stylesheet" href="<mh:theme code="jquery.steps.css"/>" media="all" />
<script src="<mh:theme code="jquery.steps.js"/>"></script>
<script type="text/javascript">
	$(document).ready(function() {
		var accountForm = $("#account");
		//配置验证信息
		 accountForm.validate({
			rules : {
				//基本信息
				companyName :{
					required : true
				},
				departId :{
					required : true
				}, 
				name : {
					required : true,
					stringChar : true
				},
				order : {
					integer : true,
					max : 9999
				},
				email : {
					required : true,
					email : true
				},
				//权限信息
				username : {
					required : true,
					stringChar : true,
					remote : {
						url : '<c:url value="/security/account/checkUnique"/>',
						type : 'POST',
						data : {
							username : function() {
								return $("#username").val();
							}
						}
					}
				},
				duty : {
					stringChar : true
				},
				password : {
					required : true,
					password : true
				},
				confirmPassword :{
					equalTo : "#password"
				},
				//其他
				remarks : {
					maxlength : 1000
				}
			}
		}); 
		
		//配置向导
		$("#account-wizard").steps({
			
			 headerTag: "h4",
			 bodyTag: "section",
			 transitionEffect: "slideLeft",
			 //逐步验证
			 onStepChanging: function (event, currentIndex, newIndex) {
				accountForm.validate().settings.ignore = ":disabled,:hidden";
				return accountForm.valid();
			 },
			 
			 onFinishing: function (event, currentIndex) {
				accountForm.validate().settings.ignore = ":disabled,:hidden";
			 	return accountForm.valid();
			 },
			 //提交表单
			 onFinished: function (event, currentIndex)	{
				accountForm.submit();
			 }
		});
		
		//初始化部门列表
		function initDepart(organId) {
			var url = '<c:url value="/organ/organization/findOrgans?type=2&parentId="/>' + organId,
				departEl = $("#departId"),
				value = departEl.val();
			$.getJSON(url, function(datas) {
				departEl.empty();
				$.each(datas, function(index, item) {
					var option = $("<option></option>").text(item.text).val(item.id).appendTo(departEl);
					if(value == item.id) {
						option.prop("selected", true); //设置选中值
					}
					$("#departName").val(item.text); //设限选中部门名称
				});
			});
		}
		
		//根据获取单位变化动态获取部门列表
		$("#company").on("change", function(e){
			var _this = $(this),
			    data  = e.added;
		      	$("#companyName").val(data.name),
				initDepart( _this.val());
	    }); 
		
	});
</script>