<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>
<style type="text/css">
	.menu-active{
		border-bottom:3px solid #2e9bd1;
		color : #2e9bd1 !important;
	}
</style>
<nav class="navbar navbar-default navbar-static-top melon-heading" role="navigation">
	<ul class="container-fluid ui-helper-reset melon-menu-group" style="padding : 0px;">
		<li class="melon-menu-item melon-gate-logo-row">
			<div class="container">
				<a href="<c:url value='/gate/home/index'/>" class="melon-gate-logo"></a>
				<c:choose>
					<c:when test="${empty ACCOUNT}">
						<div id="user-info-menu" class="primary-login-group">
							<a href="#username">登录</a>&nbsp;或&nbsp;
							<a href="#">注册</a>
						</div>
					</c:when>
					<c:otherwise>
						<div id="user-info-menu" class="primary-login-group">
							<a href="<c:url value='/j_security_logout'/>">注销</a>
						</div>
					</c:otherwise>
				</c:choose>
			</div>
		</li>
		<li class="melon-menu-item gate-menu">
			<div class="container">
				<ul class="gate-menu-ul">
					<li class="gate-menu-li"><a id="homePage" href="<c:url value='/gate/home/index'/>" style="padding-left:16px;padding-right:16px;border-left:1px solid #f0f0f0;">首页</a></li>
					<li class="gate-menu-li"><a id="menu1" href="<c:url value='/gate/home/gateList?type=1'/>">总局发文</a></li>
					<li class="gate-menu-li"><a id="menu2" href="<c:url value='/gate/home/gateList?type=2'/>">安全动态</a></li>
					<li class="gate-menu-li"><a id="menu4" href="<c:url value='/gate/home/gateList?type=4'/>">安全法规</a></li>
					<li class="gate-menu-li"><a id="menu5" href="<c:url value='/gate/home/gateList?type=5'/>">安全通知</a></li>
					<li class="gate-menu-li"><a id="menu6" href="<c:url value='/gate/home/gateList?type=6'/>">安全标准</a></li>
					<li class="gate-menu-li"><a id="menu3" href="<c:url value='/gate/home/gateRegList'/>">安全制度</a></li>
					<li class="gate-menu-li"><a id="menu7" href="<c:url value='/gate/home/gateList?type=7'/>">安全策略</a></li>
				</ul>
			</div>
		</li>
	</ul>
</nav>

<div class="container" style="padding : 0px;">
	
</div>	
<script type="text/javascript">
	$(function(){
		//选中菜单标识 
		var type = '${type}';
		$("#menu"+type).addClass("menu-active");
	});

</script>
