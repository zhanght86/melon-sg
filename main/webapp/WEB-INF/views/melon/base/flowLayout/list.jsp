<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="hh" uri="http://www.legendsec.com/melon/helper" %>

<link rel="stylesheet" href="<mh:theme code='jquery.select.css'/>" media="all" />
<script type="text/javascript" src="<mh:theme code='jquery.select.js'/>"></script>

<div class="page-query-heading">
	<sf:form modelAttribute="pageQuery.searchForm" role="form" cssClass="form-horizontal form-query">
		<mh:query>
			<jsp:attribute name="buttons">
				<mh:button href="base/flowLayout/edit" class="btn btn-default"><fmt:message key="button.create"/></mh:button>
			</jsp:attribute>
			<jsp:attribute name="quickQuery">
				<%--快速查询摆放区 --%>
				<sf:label path="name" cssClass="col-xs-4 control-label"><fmt:message key="base.flowLayout.name"/></sf:label>
				<div class="col-xs-8 form-field">
					<sf:input path="name" cssClass="form-control"/>
				</div>
			</jsp:attribute>
		</mh:query>
	</sf:form>
</div>

<div class="page-query-body">
	<mh:grid id="flowLayout" 
			 queryUrl = "base/flowLayout/list"
			 showUrl = "base/flowLayout/show"
			 deleteUrl = "base/flowLayout/remove"
			 updateUrl = "base/flowLayout/update"
			 hasPages= "true" 
			 var="roleConfig">
		<mh:col name="name" title="base.flowLayout.name" width="40"/>
		<mh:col name="type" title="base.flowLayout.type" width="30"/>
		<mh:col name="createTime" title="base.flowLayout.createTime" width="30"/>
	</mh:grid>
</div>

<script type="text/javascript">
	//截取到页面的Ajax请求
	$(document).ready(function(){
		
		
		
	});
</script>
