<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>
<style type="text/css">
	.toTop{
		height:36px;
		width:36px;
		border-radius:2px;
		background-image: url('<c:url value="/statics/themes/default/melon/images/melon-commons-icon.png" />');
		background-position:-93px -55px;
		background-color:black;
		filter:alpha(opacity=30); 
		opacity:0.3;
		position:fixed;
			right:50px;
			bottom:100px;
	}
	.toTop:hover{
		filter:alpha(opacity=80); 
		opacity:0.8;
	}
	
	/**
	 * 版权    搜索框
	 */
	 
	.melon-footer-search {
		padding:10px 0px;
		background-color: #f7f7f7;
		border-top: 1px solid #e0e0e0;
	}
	.search-query {
		width:250px;
		height:34px;
		display: inline-block;
		margin-left:-5px;
		border-radius:0px;
	}
	.search-select{
		display: inline-block;
		width:100px;
		border-radius:2px 0 0 2px;
		padding-top:4px;
	}
	.melon-footer .melon-footer-search .btn-primary{
		border-radius:0 2px 2px 0;
	}
	.melon-footer{
		background-color: #2c4b7c;
	}
	.melon-footer .melon-copyright{
		text-align: center;
		color:#58afdb;
		font-family:"宋体";
		margin-top:20px;
		margin-bottom:20px;
		font-size:12px;
	}

</style>
<!-- 登录区域 -->
<div class="container">
	<c:url value="/j_security_login" var="loginUrl"/>
	<c:if test="${!empty loginUser}">
		<sf:form modelAttribute="loginUser" role="form" cssClass="form-horizontal" action="${loginUrl}">
			<div class="container gate-login-group">
				
				<label class="login-tip" >登录：</label>
				<div class="login-username">
					<sf:input path="username" cssClass="form-control" name="username" maxlength="20" tabindex="1" placeholder="用户名"/>
				</div>
				<div class="login-password">
					<sf:password path="password" cssClass="form-control" maxlength="20" tabindex="2" placeholder="密码"/>
				</div>
				<button class="btn btn-primary btn-login" tabindex="3">登录</button>
				<a class="btn btn-primary login-register" href='<c:url value="/security/account/register"/>'>注册</a>
				<a href='<c:url value="/security/account/getPassword"/>' class="forget-pwd">忘记密码?</a>
			</div>
		</sf:form>
	</c:if>
</div>	
	
<a href="#user-info-menu" class="toTop" title="<fmt:message key='melon.copyright.toTop'/>" style="outline:none;"></a>
<div class="container-fluid melon-footer" style="padding-left: 0px; padding-right: 0px;margin-top:40px;">
	<div class="melon-footer-search">
		<div class="input-append" style="text-align: center;">
			<select class="form-control search-select">
				<fmt:message key="melon.full-text.search.option" />
			</select>
		    <input type="text" class="form-control search-query">
		    <button type="submit" class="btn btn-primary" style="margin: -2px 0 0 -4px;"><fmt:message key='melon.full-text.search'/></button>
		 </div>
	</div>
	<div class="container melon-copyright">
		<fmt:message key="melon.copyright.guoshui" />
	</div>
</div>	
<script type="text/javascript">
	$(function(){
		//返回顶部按钮是否显示
		$('.toTop').hide();
		$(window).scroll(function() {
			 if($(this).scrollTop() != 0) {
				 $('.toTop').fadeIn();
			 } else {
				 $('.toTop').fadeOut();
			 }
		});
	});
	

</script>
