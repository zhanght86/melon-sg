<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>
<%--@Author 甘焕--%>
<style type="text/css">
	.home-portal {
		float : ${ltr?"left" : "right"};
		padding : 1px;
	}
	
	.home-portal .home-portal-body {
		border : 1px solid #ddd;
		height : 100%;
		text-align : center;
	}
	.home-portal .home-portal-body h4{
		float:left;
		margin:20px 0px 20px 20px;
	}
	.home-portal-buttons{
		float:right;
		margin-right:20px;
		margin-top:20px;
	}
</style>
<c:forEach items="${views}" var="view">
	<div class="home-portal" data-id="${view.id}" data-width="${view.width}" data-height="${view.height}" >
		<div class="home-portal-body">
			<h4>${view.title}</h4>
			<div class="home-portal-buttons">
				<button class="btn btn-primary btn-xs view-update" data-id="${view.id}">
					<fmt:message key="button.update"/>
				</button>
				<button class="btn btn-default btn-xs view-remove" data-id="${view.id}">
					<fmt:message key="button.remove"/>
				</button>
			</div>
		</div>
	</div>
</c:forEach>

<script type="text/javascript">
	$(document).ready(function() {
		Portals.initUpdatePortal();
		Portals.initRemovePortal();
	});
</script>