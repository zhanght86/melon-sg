<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>
<nav class="navbar navbar-default navbar-static-top melon-heading" role="navigation">
	<ul class="container-fluid ui-helper-reset melon-menu-group" style="padding : 0px;">
		<li class="melon-menu-item melon-menu-primary">
			<div class="container">
				<a class="melon-logo"></a>
				<div class="primary-menu-group">
					<c:forEach items="${ACCOUNT.menus}" var="firstMenu">
						<c:if test="${firstMenu.active}">
							<c:set var="currentMenu" value="${firstMenu}"/>
						</c:if>
						<a href="<c:url value='/${firstMenu.url}'/>" class="primary-menu-item ${firstMenu.active? 'menu-item-focus' : ''}">
							<span style="padding:0 16px"><c:out value="${firstMenu.shortName}"/></span>
						</a>
					</c:forEach>
					
				</div>
				<%--用户信息 --%>
				<div class="dropdown melon-user-info">
					<a href="javascript:void(0);" class="dropdown-toggle" id="user-info-menu" data-toggle="dropdown">
						 <c:out value="${ACCOUNT.name}"/>
						 <div class="user-info-open-light"></div>
					</a>
					<ul class="dropdown-menu" role="menu" aria-labelledby="user-info-menu">
						<li role="presentation">
					    	<a role="menuitem" tabindex="-1" href="<c:url value='/home/attention/my?userId=${ACCOUNT.id}'/>">
					    		<fmt:message key="button.attention"/>
					    	</a>
					    </li>
						<li role="presentation">
					    	<a role="menuitem" tabindex="-1" href="<c:url value='/home/quicknote/my'/>">
					    		<fmt:message key="button.myComment"/>
					    	</a>
					    </li>
					    <li role="presentation" class="divider"></li>
					    <li role="presentation">
					    	<a role="menuitem" tabindex="-1" href="<c:url value='/security/account/show/${ACCOUNT.id}'/>">
					    		<fmt:message key="button.myInfos"/>
					    	</a>
					    </li>
					    <li role="presentation">
					    	<a role="menuitem" tabindex="-1" href="<c:url value='/security/account/resetPass?id=${ACCOUNT.id}'/>">
					    		<fmt:message key="button.resetPass"/>
					    	</a>
					    </li>
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
			<div class="container" style="padding-left: 95px;">
				<ul class="secondary-menu-group ui-helper-reset">
					<%--获取主页视图 --%>
					<c:forEach items="${homeLayouts}" var="hml">
							<li class="secondary-menu-item">
								<a href="<c:url value='/home/main?layout=${hml.id}'/>" class="${hml.active? 'secondary-menu-focus' : ''}">
									 <c:out value="${hml.typeName}"/>
								</a>
							</li>
					</c:forEach>
					
					<%--获取二级菜单 --%>
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
				<%-- <div class="secondary-menu-right">
					<a href="<c:url value='/message/mail/mailList'/>" class="my-email">
						<div class="my-email-icon"></div>
					</a>
					<a href="#" class="waitTodo">
						<div class="waitTodo-icon"></div>
						<span><fmt:message key="button.task"/></span>
					</a>
				</div> --%>
			</div>
		</li>
	</ul>
</nav>

<%--访问路径区 --%>
<%-- <div class="container" style="padding : 0px;">
	<div class="row melon-path-container">
		<div class="col-xs-4 melon-path-item span4" style="font-size : 16px;color:#2e9bd1;font-weight:bolder;">
			<strong><c:out value="${CURR_LOCATION.title}"/></strong>
		</div>
		<div class="col-xs-8 melon-path-item melon-path-info span6" style="float:right;">
			<fmt:message key="location.current"/>
			<ol class="breadcrumb" style="float:right;padding:0px;">
				<c:forEach items="${CURR_LOCATION.locations}" var="location" >
					<li>
						<a href="<c:url value='/${location.url}'/>" title="${location.title}">
							<c:out value="${location.shortName}"/>
						</a>
					</li>
				</c:forEach>
			</ol>
		</div>
	</div>
</div> --%>