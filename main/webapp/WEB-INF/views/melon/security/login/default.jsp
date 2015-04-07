<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>
<style type="text/css">
	.login-border{
		width:320px;
		height:506px;
		font-family: "微软雅黑";
		font-size:14px;
		margin:0px auto;
	}
	.login-border .login-logo{
		height:32px;
		background-position: -2px -102px;
		margin:32px 0px 26px 0px;
		display: block;
	}
	.gate-logo {
		background-image : url('<c:url value="/statics/themes/default/melon/images/logo-wangshen.png" />');
		height:48px;
		line-height :48px;
		display : block;
		font-size: 24px;
		background-repeat:no-repeat;
		background-position: -1px 4px;
		padding: 0 0 0 55px;
		margin-bottom:5px;
		color: #3c3c3c;
	}
	.gate-logo:hover  {
		color: #3c3c3c !important;
		text-decoration: none;
	}
	/*输入区域*/
	.login-input .username-label, .login-input .password-label {
		font-weight:normal;
		display: block;
		color:#808080;
	}
	.login-input .user-input,  .login-input .password-input {
		width:100%;
		height:36px;
	}
	.login-input .user-input:hover,  .login-input .password-input:hover{
		border:1px solid #66afe9;
	}
	.login-input .password-label{
		display: inline-block;
		margin-top:15px;
	}
	.login-input .forget-pwd{
		margin-left:10px;
		color:#1970b2;
		text-decoration: underline;
	}
	.login-input .other-operation{
		margin-top:14px;
		
	}
	.login-input .other-operation label{
		font-weight:normal;
		color:#808080;
		padding-top:0px;
	}
	.other-operation .register{
		float:right;
		font-weight:bold;
		color:#1970b2;
		margin-right:4px;
		text-decoration: underline;
	}
	
	/*按钮区域*/
	.login-buttons{
		width:100%;
		padding-top:22px;
	}
	.login-buttons .login-btn, .login-buttons .certificate-login-btn{
		background-color: #2e9bd1;
		width:100%;
		height:36px;
		color:#ffffff;
		border:0px;
		border-radius:2px;
	}
	.login-buttons .login-btn:hover{
		background-color:#248bc7;
	}
	.login-buttons .login-btn:active{
		background-color:#2475b2;
	}
	.login-buttons .certificate-login-btn{
		background-color:#5880c7;
	}
	.login-buttons .certificate-login-btn:hover{
		background-color:#4873bd;
	}
	.login-buttons .certificate-login-btn:active{
		background-color:#4368a8;
	}
	
	/*说明区域*/
	.login-buttons .login-choose{
		margin:20px 0px;
		width:100%;
		border-top:1px solid #e5e5e5;
	}
	.login-description{
		margin-top:32px;
		border-top:1px solid #ccc;
	}
	.login-description p{
		margin-top:14px;
		color:#808080;
		line-height:24px;
	}
	.login-description p a{
		color:#1970b2;
	}
	.login-border a:hover{
		color:#2e9bd1;
	}

</style>


	<c:url value="/j_security_login" var="loginUrl"/>
	<sf:form modelAttribute="loginUser" role="form" cssClass="form-horizontal" action="${loginUrl}">
		
		<div class="login-border">
			<a class="gate-logo"><fmt:message key="security.login.title"/></a>
			<div class="login-input">
				<label for="username" class="username-label"><fmt:message key="security.login.username"/></label>
				<sf:input path="username" cssClass="form-control user-input" name="username" maxlength="20" tabindex="1"/>
				
				<label for="password" class="password-label"><fmt:message key="security.account.password" /></label>
				<a href='<c:url value="/security/account/getPassword"/>' class="forget-pwd"><fmt:message key="security.login.forgetPwd"/></a>
				<sf:password path="password" cssClass="form-control password-input" maxlength="20" tabindex="2"/>
				
				<c:if test="${!empty LOGIN_ERROR}">
					<div style = "color: #ff5548; margin-top:15px; font-size:14px">
						${LOGIN_ERROR}
					</div>
				</c:if>
				
				
				<div class="other-operation">
					<fmt:message key="security.login.rememberMe" var="fmtRememberMe"/>
					<sf:checkbox path="rememberMe" class="remember-checkbox" label="${fmtRememberMe}"/>
					<a href='<c:url value="/security/account/register"/>' class="register"><fmt:message key="security.login.register"/></a>
				</div>
				
			</div>
			<div class="login-buttons">
				<mh:button class="login-btn" id="btnLogin" type="submit" tabindex="3"><fmt:message key="button.login"/></mh:button>
				<div class="login-choose"></div>
				<mh:button class="certificate-login-btn" id="certificate-login-btn" tabindex="4"><fmt:message key="security.login.certificate-login"/></mh:button>
			</div>
			<div class="login-description">
				<fmt:message key="security.login.description"/>
			</div>
		
		</div>
		
	</sf:form>

<script type="text/javascript">
	$(function(){
		//页面加载完后，用户名输入框为焦点
		$("#username").focus();
		//证书登录友情提示
		$("#certificate-login-btn").on("click",function(){
			Dialog.info("<fmt:message key='security.login.certificate-login-tip'/>");
		});
	});
</script>


