<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>
<meta http-equiv="content-type" content="text/html; charset=utf-8"/>
<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title><fmt:message key="melon.title"/></title>
<link rel="stylesheet" href="<mh:theme code='jquery.ui.css'/>" media="all" />
<link rel="stylesheet" href="<mh:theme code='bootstrap.css'/>" media="all" />
<c:out value="<!--[if lt IE 9]>" escapeXml="false"/>
	<link rel="stylesheet" href="<mh:theme code='melon.hacker.css'/>" media="all" />
   	<script src="<mh:theme code='html5-respond.js'/>"></script>
<c:out value="<![endif]-->" escapeXml="false"/>
<script type="text/javascript">
	//定义通用的变量
	var JsConstant = {
		baseUrl : '<c:url value="/statics/scripts"/>'
	};
</script>
<script type="text/javascript"  src="<mh:theme code='jquery.js'/>"></script>
<script type="text/javascript"  src="<mh:theme code='bootstrap.js'/>"></script>
<script type="text/javascript"  src="<mh:theme code='jquery.ui.js'/>"></script>
<script type="text/javascript"  src="<mh:theme code='melon.commons.js'/>"></script>
