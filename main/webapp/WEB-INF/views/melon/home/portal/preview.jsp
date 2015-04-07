<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>
<style type="text/css">
	.home-portal .home-portal-body {
		border : 1px solid #ddd;
		height : 100%;
		text-align : center;
	}
	.home-portal {
		float : ${ltr?"left" : "right"};
		padding : 1px;
	}
</style>

<c:forEach items="${views}" var="view">
	<div class="home-portal" data-id="${view.id}" data-width="${view.width}" data-height="${view.height}" data-url="<c:url value="/"/>${view.url}">
	</div>
</c:forEach>

<script type="text/javascript" src="<mh:theme code='esl.js'/>"></script>
<script type="text/javascript">
$(document).ready(function() {
	var width = $(".melon-body").width()-1;
	
	$(".home-portal").each(function(index, item) {
		var portal = $(item),
		 	url = portal.attr("data-url");
		 portal.load(url, function() {
			ow = parseInt(portal.attr("data-width")),
			oh = parseInt(portal.attr("data-height")),
			lw = Math.floor(width * ow / 12),
			lh = Math.floor(width * oh / 12);
			portal.css({
			width : lw,
			height : lh
			});
		}); 
});
});
</script>