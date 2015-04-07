<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>
<%--
	--禁止修改此文件--
--%>
<!doctype html>
<html lang="zh">
<head>
	<meta http-equiv="content-type" content="text/html; charset=utf-8"/>
	<meta http-equiv="content-language" content="zh-CN" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
	<title><fmt:message key="melon.title"/></title>
	<link rel="stylesheet" href="<mh:theme code='bootstrap.css'/>" media="all" />
    <link rel="stylesheet" href="<mh:theme code='bootstrap.theme.css'/>" media="all" />
    <c:out value="<!--[if lt IE 9]>" escapeXml="false"/>
    	<script src="<mh:theme code='html5shiv.js'/>"></script>
    	<script src="<mh:theme code='respond.js'/>"></script>
    <c:out value="<![endif]-->" escapeXml="false"/>
</head>
<body>
	<div class="container">
		<tiles:insertAttribute name="body"/>
	</div>
</body>
</html>