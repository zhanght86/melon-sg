<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="hh" uri="http://www.legendsec.com/melon/helper" %>

<div id="alert-wizard">

	<!-- 属性配置 -->
	<mh:section id="info">
		<jsp:attribute name="title">
			属性
		</jsp:attribute>
		<jsp:attribute name="body">
			<sf:form modelAttribute="lasRule" role="form" cssClass="form-horizontal">
	
				<sf:hidden path="id"/>
				<div class="form-group">
					<sf:label path="name" cssClass="col-xs-3 control-label">
						<fmt:message key="las.rule.name" />*
					</sf:label>
					<div class="col-xs-9 form-field">
						<sf:input  name="name" path="name" cssClass="form-control" maxlength="20"/>
					</div>
				</div>
				
				<div class="form-group">
					<sf:label path="enabled" cssClass="col-xs-3 control-label">
						<fmt:message key="las.rule.isEnable" />
					</sf:label>
					<div class="col-xs-9 form-field">
					
					
					<mh:dictionary var="enableds" key="enableds"/>
					<sf:radiobuttons path="enabled" items="${enableds }"/>
						
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
						<sf:textarea path="remarks" cssClass="form-control" maxlength="500" rows="5" />
					</div>
				</div>
				
			</sf:form>
		</jsp:attribute>
	</mh:section>
		
	<!-- 动作配置 -->
	<mh:section id="info">
		<jsp:attribute name="title">
			动作
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


<!-- 引入向导配置 -->
<link rel="stylesheet" href="<mh:theme code="jquery.steps.css"/>" media="all" />
<script src="<mh:theme code="jquery.steps.js"/>"></script>
<script type="text/javascript">
$(document).ready(function() {
	
	var alertForm = $("#lasRule"),
		dataStr = "";
	/* alertForm.validate({
		rules : {
			organName : {
				required : true,
				stringChar : true
			},
			servicer : {
				stringChar : true
			},
			serviceTel : {
				tel :true
			},
			serviceMail : {
				email : true
			},
			validity : {
				required : true,
				integer : true
			},
			ipLeft : {
				required : true,
				integer : true,
				range : [0,255]
			},
			ipRight : {
				required : true,
				integer : true,
				range : [0,255]
			}
		},
		
	}); */
	
	//配置向导
	$("#alert-wizard").steps({
		
		 headerTag: "h4",
		 bodyTag: "section",
		 transitionEffect: "slideLeft",
		 //逐步验证
		 onStepChanging: function (event, currentIndex, newIndex) {
			 //alertForm.validate().settings.ignore = ":disabled,:hidden";
			 dataStr += $("form").eq(currentIndex).serialize() + "&";
			 console.log($("form").eq(currentIndex).serialize());
			return alertForm.valid();
		 },
		 
		 onFinishing: function (event, currentIndex) {
			 //alertForm.validate().settings.ignore = ":disabled";
			 console.log($("form").eq(currentIndex).serialize());
			 dataStr += $("form").eq(currentIndex).serialize() + "&";
		 	return alertForm.valid();
		 },
		 //提交表单
		 onFinished: function (event, currentIndex)	{
			 console.log(dataStr);
			 $.post("<c:url value='/las/sim/alert/create'/>", dataStr, function(data) {
				 window.location.href = "<c:url value='/las/sim/alert/list'/>";
			 })
			 //alertForm.submit();
		 }
	});
	
	/*
	 * 所有文档加载完成后注册
	 */
	window.onload = function() {
		
	};
	
});

</script>
