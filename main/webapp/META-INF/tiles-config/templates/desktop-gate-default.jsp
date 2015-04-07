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
		<jsp:include page="/META-INF/tiles-config/templates/include/desktop-resources.jsp"/>
	</head>
	<body data-spy="scroll" data-target="#melon-nav-panel" data-offset="153">
		<jsp:include page="/META-INF/tiles-config/templates/include/desktop-gate-header.jsp"/>
		<%-- <div class="container melon-body clearfix">
			<tiles:insertAttribute name="body"/>
		</div> --%>
		<div class="container">
			<tiles:insertAttribute name="body"/>
		</div>
		<jsp:include page="/META-INF/tiles-config/templates/include/desktop-copyright.jsp"/>
	</body>
</html>
<c:if test="${!empty ACTION_HINT}">
	<script type="text/javascript">
		$(document).ready(function() {
			Logger.out('${ACTION_HINT.message}', '${ACTION_HINT.level}');
		});	
	</script>
</c:if>