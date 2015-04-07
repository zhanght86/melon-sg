<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>

<style type="text/css">
	.level>label {
		width : 50% ! important; 
	}
</style>

<div class="col-xs-8 col-xs-offset-2 form-singlon">
	<form class="form-horizontal" >
		<div class="form-group">
			<label class="col-xs-3 control-label">
				<fmt:message key="rule.template" />
			</label>
			
			<div class="col-xs-9 form-field">
				<select class="form-control" id="tmpSelect">
					<c:forEach items="${rules}" var="item">
						<option value="${item.id}">${item.name}</option>
					</c:forEach>
				</select>
			</div>
		</div>
	</form>

	
	<div id="loadForm">
	
	</div>
</div>

<script type="text/javascript">
	var tmpId;
	$(document).ready(function() {
		
		var url = "<c:url value='/las/rule/load?id='/>";
			tmpId = $("#tmpSelect").val();
		$("#loadForm").load(url+tmpId);
		$("#tmpSelect").change(function() {
			tmpId = $("#tmpSelect").val();
			$("#loadForm").load(url+tmpId);
		});
	});
</script>

