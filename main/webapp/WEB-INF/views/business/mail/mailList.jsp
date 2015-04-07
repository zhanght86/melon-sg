<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>

<div class="col-xs-3 tree-left">
			<div class="report-header">
				<h4 class="report-title">页面邮箱</h4>
			</div>
			<ul class="list-group">
				<li class="list-group-item active"><a href="#MAIL_CONTAINER" id="send"><fmt:message key="button.write"/></a></li>
				<li class="list-group-item"><a href="#MAIL_CONTAINER" id="sendMail">发件箱</a><span class="badge pull-right">5</span></li>
				<li class="list-group-item"><a href="#MAIL_CONTAINER"  id="reciveMail">收件箱</a><span class="badge pull-right">4</span></li>
				<li class="list-group-item"><a href="#MAIL_CONTAINER"  id="draftMail">草稿箱</a><span class="badge pull-right">3</span></li>
				<li class="list-group-item"><a href="#MAIL_CONTAINER"  id="removeMail">已删邮件</a><span class="badge pull-right">0</span></li>
				<li class="list-group-item"><a href="#MAIL_CONTAINER"  id="themeMail">主题邮件</a><span class="badge pull-right">0</span></li>
			</ul>	
</div>
<div class="col-xs-9 col-right" id ="MAIL_CONTAINER" style="margin-bottom: -20px; padding-left: 20px; padding-right: 0px;">
 
</div>
<script type="text/javascript">
$(document).ready(function(){
	var defaultUrl = "<c:url value='/message/mail/welcome'/>",
	    sendUrl = "<c:url value='/message/mail/create'/>",
		sendMailUrl = "<c:url value='/message/mail/list'/>",
		mailDiv = $("#MAIL_CONTAINER");
		
	mailDiv.load(defaultUrl); //加载主页
	//写信页面
	$("#send").on("click",function(event){
		mailDiv.load(sendUrl); //加载写信页面 
		//阻止默认的表单提交
		event.preventDefault();
    });
	//发件箱页面
	$("#sendMail").on("click",function(event){
		mailDiv.load(sendMailUrl);//加载发件箱页面
		//阻止默认的表单提交
		event.preventDefault();
	});
});
</script>