<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>

<!doctype html>
<html lang="zh">
	<head>
		<jsp:include page="/META-INF/tiles-config/templates/include/desktop-resources.jsp"/>
	</head>
	<body>
		<div class="">
			<tiles:insertAttribute name="body"/>
		</div>
	</body>
</html>