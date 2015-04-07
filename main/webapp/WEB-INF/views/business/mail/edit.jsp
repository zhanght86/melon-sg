<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="hh" uri="http://www.legendsec.com/melon/helper"%>


<link rel="stylesheet" href="<mh:theme code='jquery.select.css'/>" media="all" />
<link rel="stylesheet" href="<mh:theme code='jquery.fileupload.css'/>" media="all" />

<script type="text/javascript" src="<mh:theme code='jquery.select.js'/>"></script>
<script type="text/javascript" src="<mh:theme code='jquery.fileupload.js'/>"></script>
<script type="text/javascript" src="<mh:theme code='ckeditor.js'/>"></script>

<%-- 单独的表单必须嵌入到此样式标签里 --%>
<div class="col-xs-8 col-xs-offset-2 form-singlon">
	<sf:form modelAttribute="mail" role="form" cssClass="form-horizontal" enctype="multipart/form-data">
		
		<sf:hidden path="id"/>
		<sf:hidden path="replyId"/>
		<sf:hidden path="sendTime"/>
		
		<div class="form-group">
		    <sf:label path="sender.username" cssClass="col-xs-3 control-label">
				<fmt:message key="hint.required"/> 
				<fmt:message key="mail.sender"/>
			</sf:label>
			<div class="col-xs-9 form-field">
				<mh:value path="sender.username" cssClass="form-control"/>
			</div>
		</div>
		
		<div class="form-group">
		    <sf:label path="userId" cssClass="col-xs-3 control-label">
				<fmt:message key="hint.required"/> 
				<fmt:message key="mail.user"/>
			</sf:label>
			<div class="col-xs-9 form-field">
				<sf:input path="userId" cssClass="form-control" maxlength="10"/>
				<hh:select id="userId" onceUrl="security/account/findUsers" />
				<sf:errors path="userId"/>
			</div>
		</div>
		
		<div class="form-group">
			<sf:label path="title" cssClass="col-xs-3 control-label">
				<fmt:message key="hint.required"/>
				<fmt:message key="mail.title"/>
			</sf:label>
			
			<div class="col-xs-9 form-field">
				<sf:input path="title" cssClass="form-control" maxlength="20"/>
				<sf:errors path="title"/>
			</div>
		</div>
		
		<div class="form-group">
			<sf:label path="attachId" cssClass="col-xs-3 control-label">
				<fmt:message key="mail.attach"/>
			</sf:label>
			
			<div class="col-xs-9 form-field">
				<sf:hidden path="attachId"  cssClass="form-control"/>
				<mh:upload domainId="${mail.attachId}" />
				<sf:errors path="attachId"/>
			</div>
		</div>
		
		<div class="form-group">
			<div class="col-xs-12 form-editor form-field">
				<sf:textarea path="content" cssClass="form-control" rows="10"/>
				<sf:errors path="content"/>
			</div>
		</div>
		
		<div class="form-group form-button-panel">
			<div class="col-xs-9 col-xs-offset-3 form-buttons">
				<mh:button class="btn btn-primary" id="btnSave"><fmt:message key="button.send"/></mh:button>
				<mh:button class="btn btn-default" href="message/mail/mailList"><fmt:message key="button.back"/></mh:button>
			</div>
		</div>
		
	</sf:form>
</div>
<script type="text/javascript" src="<mh:theme code='jquery.vaildate.js'/>"></script>
<script type="text/javascript">
	$(document).ready(function() {
		CKEDITOR.replace("content");
		var mailForm = $("#mail");
		
		//验证表单
		mailForm.validate({
			rules : {
				title : {
					required : true,
					stringChar : true
				},
				content : {
					required : true,
					stringChar : true
				}
			}
		});
		//处理保存按钮
		$("#btnSave").on("click", function(event) {
			if(mailForm.valid()){
			    //提交表单
				mailForm.submit();
			}
			//阻止默认的提交动作
			event.preventDefault();
		});
	});
</script>
