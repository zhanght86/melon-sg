<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>
<%--@Author 甘焕--%>
<div class="col-xs-8 col-xs-offset-2 form-singlon">
	<sf:form modelAttribute="termQuery" method="POST" role="form" cssClass="form-horizontal" enctype="application/x-www-form-urlencoded; charset=UTF-8">
		<sf:hidden path="currPage"/>
		<div class="form-group">
			<div class="input-group col-xs-6">
				<sf:input path="searchForm"  class="form-control"/>
				<span class="input-group-btn">
					<button class="btn btn-default" type="submit">
						<fmt:message key="button.query" />
					</button>
				</span>
			</div>
		</div>
	</sf:form>
	
	<c:forEach items="${pages.results}" var="result">
		<div class="media">
			<div class="media-body">
   	 			<h4 class="media-heading">
   	 				<c:out value="${result.title}" escapeXml="false"/>
   	 			</h4>
   	 			<c:out value="${result.content}" escapeXml="false"/>
   	 		</div>
		</div>
		
	</c:forEach>
	
	<ul class="pagination pagination-lg" id="pagination">
		<c:set var="startPage" value="${pages.pages % 5 == 0 ? pages.pages/5 : pages.pages/5 + 1}"/>
		<li class="${startPage - 5 < 0 ? 'disabled' : ''}"><a href="#" index="${startPage - 5}">&laquo;</a></li>
		<c:forEach begin="${startPage}" end="${startPage + 4}" var="page">
			  <li class="${page > pages.pages?'disabled' : ''} ${page == pages.currPage?'active' : ''}"><a href="#" index="${page}">${page}</a></li>
		</c:forEach>
		<li class="${startPage + 5 > pages.pages ? 'disabled' : ''}"><a href="#" index="${startPage + 5}">&raquo;</a></li>
	</ul>
	
</div>

<script type="text/javascript">
	$(document).ready(function() {
		var term = $("#searchTerm").val(),
			currPage = $("#currPage"),
			form = $("#termQuery");
		$("#pagination>li").each(function(index, item) {
			if($(this).hasClass("disabled")) {
				$(this).find(">a").on("click", function(event) {
					event.preventDefault();
				});
			} else {
				$(this).find(">a").on("click", function(event) {
					var index = $(this).attr("index");
					currPage.val(index);
					form.submit();
					event.preventDefault();
				});
			}
		});
	});
</script>