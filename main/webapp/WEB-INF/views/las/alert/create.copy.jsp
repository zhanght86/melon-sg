<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="hh" uri="http://www.legendsec.com/melon/helper" %>
<link rel="stylesheet" href="<mh:theme code='jquery.jstree.css'/>" media="all" />
<style type="text/css">
	#import-url-group .input-group{
		margin-bottom: 7px;
	}
</style>
<script type="text/javascript" src="<mh:theme code='jquery.jstree.js'/>"></script>
<script type="text/javascript" src="<mh:theme code='melon.ajaxtree.js'/>"></script>


	<div id="alert-wizard">
		<!-- 属性配置 -->
		<mh:section id="info">
			<jsp:attribute name="title">
				属性
			</jsp:attribute>
			<jsp:attribute name="body">
			<sf:form modelAttribute="alert" role="form" cssClass="form-horizontal">
	
				<sf:hidden path="id"/>
				<sf:hidden path="name" />
				<div class="form-group">
						<sf:label path="name" cssClass="col-xs-3 control-label">
							<fmt:message key="las.rule.name" />*
						</sf:label>
						<div class="col-xs-9 form-field">
							<sf:input path="name" cssClass="form-control" maxlength="20"/>
						</div>
					</div>
					
					<div class="form-group">
						<sf:label path="enabled" cssClass="col-xs-3 control-label">
							<fmt:message key="las.rule.isEnable" />
						</sf:label>
						<div class="col-xs-9 form-field">
							<sf:radiobutton path="enabled" label="" />启用
							<sf:radiobutton path="enabled" label="" />禁用
							
						</div>
					</div>
					
					<div class="form-group">
						<sf:label path="creator" cssClass="col-xs-3 control-label">
							<fmt:message key="las.rule.createUser" />
						</sf:label>
						<div class="col-xs-9 form-field">
							<sf:input path="creator" cssClass="form-control" maxlength="20"/>
						</div>
					</div>
					
					<div class="form-group">
						<sf:label path="description" cssClass="col-xs-3 control-label">
							<fmt:message key="las.rule.description" />
						</sf:label>
						<div class="col-xs-9 form-field">
							<sf:textarea path="description" cssClass="form-control" maxlength="500" rows="5" />
						</div>
					</div>
					</sf:form>
			</jsp:attribute>
		</mh:section>
			
		<%--条件信息 --%>
		<mh:section id="other">
			<jsp:attribute name="title">
				条件
			</jsp:attribute>
			<jsp:attribute name="body">
				<div class="col-xs-5 col-left">
					<button class="btn btn-primary btn-sm" id="btnCreate"><fmt:message key="button.create"/></button>
					<button class="btn btn-default btn-sm" id="btnRemove" style="margin-left: 6px;"><fmt:message key="button.remove"/></button>
					<form class="navbar-form navbar-right" role="search" onsubmit="return false;" style="margin-top: 0px;padding-right:0px;">
						<div class="form-group">
							<div class="input-group">
								<input type="text" class="form-control"	style="width: 125px; height: 30px;margin-right:1px;" id="searchName">
								<span class="input-group-btn">
									<button class="btn btn-primary btn-sm" type="button" id="btnSearch">
										<span class="glyphicon glyphicon-search"></span>
									</button>
								</span>
							</div>
						</div>
					</form>
					<mh:tree id="menu"
							rootId="1"
							url="security/menuResource/find"
							sortName="order"
							scriptSelf="false">
					</mh:tree>
				</div>
				<div class="col-xs-8 col-right" id="menuForm"></div>
			</jsp:attribute>
		</mh:section>
		
		<!-- 计数配置 -->
		<mh:section id="info">
			<jsp:attribute name="title">
				计数
			</jsp:attribute>
			<jsp:attribute name="body">
				计数配置
				
			</jsp:attribute>
		</mh:section>
		
		<!-- 动作配置 -->
		<mh:section id="info">
			<jsp:attribute name="title">
				动作
			</jsp:attribute>
			<jsp:attribute name="body">
				动作配置
				
			</jsp:attribute>
		</mh:section>
		</div>


<!-- 引入向导配置 -->
<link rel="stylesheet" href="<mh:theme code="jquery.steps.css"/>" media="all" />
<script src="<mh:theme code="jquery.steps.js"/>"></script>
<script type="text/javascript">
$(document).ready(function() {
	
	var alertForm = $("#alert");
	alertForm.validate({
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
		
	});
	
	//配置向导
	$("#alert-wizard").steps({
		
		 headerTag: "h4",
		 bodyTag: "section",
		 transitionEffect: "slideLeft",
		 //逐步验证
		 onStepChanging: function (event, currentIndex, newIndex) {
			 alertForm.validate().settings.ignore = ":disabled,:hidden";
			return true;
		 },
		 
		 onFinishing: function (event, currentIndex) {
			 alertForm.validate().settings.ignore = ":disabled";
		 	return alertForm.valid();
		 },
		 //提交表单
		 onFinished: function (event, currentIndex)	{
			 alertForm.submit();
		 }
	});
	
	/*
	 * 所有文档加载完成后注册
	 */
	window.onload = function() {
		
	};
	
});

</script>