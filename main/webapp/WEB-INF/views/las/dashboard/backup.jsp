<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sp" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html" %>
<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
	<head>
		<meta http-equiv="content-type" content="text/html; charset=utf-8"/>
		<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title><fmt:message key="melon.title"/></title>
		<link rel="stylesheet" href="<c:url value='/statics/kibana/css/bootstrap.light.min.css'/>" media="all" />
    	<link rel="stylesheet" href="<c:url value='/statics/kibana/css/timepicker.css'/>" media="all">
    	<link rel="stylesheet" href="<c:url value='/statics/kibana/css/animate.min.css'/>" media="all">
   		<link rel="stylesheet" href="<c:url value='/statics/kibana/css/normalize.min.css'/>" media="all">
   		<link rel="stylesheet" href="<c:url value='/statics/kibana/css/bootstrap-responsive.min.css'/>" media="all">
   		<link rel="stylesheet" href="<c:url value='/statics/kibana/css/font-awesome.min.css'/>" media="all">
   		<link rel="stylesheet" href="<c:url value='/statics/kibana/css/melon.theme-1.0.1.css'/>" media="all">
	    <script type="text/javascript">
	    	//以下地址需要配置成绝对地址
	    	var Kibana = {
	    		baseUrl : "<c:url value='/statics/kibana/'/>",
	    		appUrl : "<c:url value='/statics/kibana/app'/>",
	    		loadImg : "<c:url value='/statics/kibana/img/load.gif'/>",
	    		esUrl : "http://10.18.201.245:9288"//服务器地址
	    	};
	    </script>
	    <script type="text/javascript" src="<c:url value='/statics/kibana/vendor/require/require.js'/>"></script>
	    <script type="text/javascript" src="<c:url value='/statics/kibana/app/components/require.config.js'/>"></script>
	    <script type="text/javascript">
	    	//启动主程序
	    	require(['app'], function(){
	 			   		
	    	});
	    </script>
	</head>
	
	<body>
		<noscript>
	      <div class="container">
	        <center><h3>You must enable javascript to use Kibana</h3></center>
	      </div>
	    </noscript>
	    
	    <div ng-cloak ng-repeat='alert in dashAlerts.list' class="alert-{{alert.severity}} dashboard-notice" ng-show="$last">
      		<button type="button" class="close" ng-click="dashAlerts.clear(alert)" style="padding-right:50px">&times;</button>
      		<strong>{{alert.title}}</strong> 
      		<span ng-bind-html='alert.text'></span>
	      	<div style="padding-right:10px" class='pull-right small'> {{$index + 1}} alert(s) </div>
	    </div>
    	
    	<!-- 顶部导航区 -->
		<nav class="navbar navbar-default navbar-static-top melon-heading" role="navigation">
			<ul class="container-fluid ui-helper-reset melon-menu-group" style="padding : 0px;">
				<li class="melon-menu-item melon-menu-primary">
					<div class="container">
						<a href="<c:url value='/home/main'/>" class="melon-logo"></a>
						<div class="primary-menu-group">
							<c:forEach items="${ACCOUNT.menus}" var="firstMenu">
								<c:if test="${firstMenu.active}">
									<c:set var="currentMenu" value="${firstMenu}"/>
								</c:if>
								<a href="<c:url value='/${firstMenu.url}'/>" class="primary-menu-item ${firstMenu.active? 'menu-item-focus' : ''}">
									<span><c:out value="${firstMenu.shortName}"/></span>
								</a>
							</c:forEach>
							
						</div>
						<%--用户信息 --%>
						<div class="dropdown melon-user-info">
							<a class="badge" style="background-color: #ff5548;float:left;margin-top:15px;" href="javascript:void(0);">42</a>
							<a href="javascript:void(0);" class="dropdown-toggle" id="user-info-menu" data-toggle="dropdown">
								 <c:out value="${ACCOUNT.name}"/>
								 <div class="user-info-open-light"></div>
								 <div class="user-icon"></div>
							</a>
							<ul class="dropdown-menu" role="menu" aria-labelledby="user-info-menu">
							    <li role="presentation">
							    	<a role="menuitem" tabindex="-1" href="#">
							    		<fmt:message key="button.task"/>
							    	</a>
							    </li>
								<li role="presentation">
							    	<a role="menuitem" tabindex="-1" href="#">
							    		<fmt:message key="button.message"/>
							    	</a>
							    </li>
								<li role="presentation">
							    	<a role="menuitem" tabindex="-1" href="#">
							    		<fmt:message key="button.attention"/>
							    	</a>
							    </li>
							    <li role="presentation" class="divider"></li>
							    <li role="presentation">
							    	<a role="menuitem" tabindex="-1" href="<c:url value='/j_security_logout'/>">
							    		<fmt:message key="button.logout"/>
							    	</a>
							    </li>
							</ul>
						</div>
					</div>
				</li>
				<li class="melon-menu-item melon-menu-secondary">
					<div class="container" style="padding-left: 65px;">
						<ul class="secondary-menu-group ui-helper-reset">
							<c:forEach items="${currentMenu.items}" var="item">
									<c:choose>
										<c:when test="${empty item.items}">
											<li class="secondary-menu-item">
												<a href="<c:url value='/${item.url}'/>" class="${item.active? 'secondary-menu-focus' : ''}">
													 <c:out value="${item.shortName}"/>
												</a>
											</li>
										</c:when>
										<c:otherwise>
											<li class="secondary-menu-item dropdown">
												<a href="javascript:void(0);" data-toggle="dropdown" class="dropdown-toggle ${item.active? 'secondary-menu-focus' : ''}" id="item_${item.id}">
													 <c:out value="${item.shortName}"/>
													 &nbsp;<span class="caret"></span>
												</a>
												<ul class="dropdown-menu" role="menu" aria-labelledby="item_${item.id}">
													<c:forEach items="${item.items}" var="lastMenu">
														<li role="presentation">
															<a role="menuitem" tabindex="-1" href="<c:url value='/${lastMenu.url}'/>">
																<c:out value="${lastMenu.shortName}"/>
															</a>
														</li>
													</c:forEach>
												</ul>
											</li>
										</c:otherwise>
									</c:choose>
							</c:forEach>
						</ul>
					</div>
				</li>
			</ul>
		</nav>
		
		<div class="alert aelrt-info">Splitter</div>
		<!-- 查询区 -->
    	<div ng-cloak ng-view></div>
	</body>
</html>