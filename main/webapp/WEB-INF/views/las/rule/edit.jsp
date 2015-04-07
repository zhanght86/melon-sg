<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="hh" uri="http://www.legendsec.com/melon/helper" %>

<style type="text/css">
	 #alert-wizard>.content {
	 	overflow-y:auto;
	} 
	
</style>

<sf:form modelAttribute="lasRule" role="form" cssClass="form-horizontal">
			
	<sf:hidden path="id"/>
	<sf:hidden path="createTime"/>
	<sf:hidden path="creator.userId"/>
	<sf:hidden path="creator.username"/>
	<sf:hidden path="creator.organId"/>
	<sf:hidden path="creator.organName"/>
				
	<div id="alert-wizard">
	
		<!-- 属性配置 -->
		<mh:section id="info">
			<jsp:attribute name="title">
				<fmt:message key="las.rule.attr" />
			</jsp:attribute>
			<jsp:attribute name="body">
					
				<div class="form-group">
					<sf:label path="name" cssClass="col-xs-3 control-label">
						<fmt:message key="hint.required" />
						<fmt:message key="las.rule.name" />
					</sf:label>
					<div class="col-xs-9 form-field">
						<sf:input path="name" cssClass="form-control" maxlength="20"/>
						<sf:errors path="name"/>
					</div>
				</div>
				
				<div class="form-group">
					<sf:label path="enabled" cssClass="col-xs-3 control-label">
						<fmt:message key="las.rule.isEnable" />
					</sf:label>
					<div class="col-xs-9 form-field">
					
					
					<mh:dictionary var="enableds" key="enableds"/>
					<sf:radiobuttons path="enabled" items="${enableds}"/>
						
					</div>
				</div>
				
				<div class="form-group">
					<sf:label path="creator" cssClass="col-xs-3 control-label">
						<fmt:message key="las.rule.createUser" />
					</sf:label>
					<div class="col-xs-9 form-field">
						<mh:value path="creator.username"/>
					</div>
				</div>
				
				<div class="form-group">
					<sf:label path="remarks" cssClass="col-xs-3 control-label">
						<fmt:message key="las.rule.description" />
					</sf:label>
					<div class="col-xs-9 form-field">
						<sf:textarea path="remarks" cssClass="form-control" maxlength="200" rows="5" />
					</div>
				</div>
					
			</jsp:attribute>
		</mh:section>
			
		<!-- 动作配置 -->
		<mh:section id="info">
			<jsp:attribute name="title">
				<fmt:message key="las.rule.act" />
			</jsp:attribute>
			<jsp:attribute name="body">
				<jsp:include page="/WEB-INF/views/las/alert/actionEdit.jsp" />
			</jsp:attribute>
		</mh:section>
		
		<%-- <!-- 计数配置 -->
		<mh:section id="info">
			<jsp:attribute name="title">
				计数
			</jsp:attribute>
			<jsp:attribute name="body">
				计数配置
				
			</jsp:attribute>
		</mh:section> --%>
		
	</div>
</sf:form>


<!-- 引入向导配置 -->
<link rel="stylesheet" href="<mh:theme code="jquery.steps.css"/>" media="all" />
<script src="<mh:theme code="jquery.steps.js"/>"></script>
<script type="text/javascript">
$(document).ready(function() {
	
	var ruleForm = $("#lasRule");
	
	function content() {
		
		if($(".actionType:eq(0)").prop("checked") == true) {
			$("#message").slideDown("normal",function(){
			 });
		} else {
			$("#message").slideUp("normal",function(){
				   $(".receive").val("");
				   $(".note").val("");
			 });
		}
		if($(".actionType:eq(1)").prop("checked") == true) {
			$("#mail").slideDown("normal",function(){
			 });
		} else {
			$("#mail").slideUp("normal",function(){
				   $(".subject").val("");
				   $(".address").val("");
				   $(".content").val("");
			 });
		}
	}
	
	/*
	 * 所有文档加载完成后注册
	 */
	window.onload = function() {
		
		ruleForm.validate({
			rules : {
				name : {
					required : true
				},
				remarks : {
					maxlength : 200
				}
				
			}
		});
		content();
		$(".actionType").on("click", function() {
			content();
		});
	};
	
	//配置向导
	$("#alert-wizard").steps({
		
		 headerTag: "h4",
		 bodyTag: "section",
		 transitionEffect: "slideLeft",
		 
		 //逐步验证
		 onStepChanging: function (event, currentIndex, newIndex) {
			ruleForm.validate().settings.ignore = ":disabled,:hidden";
			return ruleForm.valid();
		 },
		 
		 onFinishing: function (event, currentIndex) {
			 ruleForm.validate().settings.ignore = ":hidden,:disabled";
		 	return ruleForm.valid();
		 },
		 
		 //提交表单
		 onFinished: function (event, currentIndex)	{
			 ruleForm.submit();
		 }
	});
	
});

</script>
