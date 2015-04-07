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
	<div class="portal-main">
		<div class="page-header">
			<h3><fmt:message key="security.role.title"/></h3>
		 	<p>
				<fmt:message key="organ.listOrgans.description"/>
			</p>
		</div>
		<div class="row">
			<div class="col-xs-12">
				<div id="chart" style="height: 330px;"></div>
			</div>
		</div>
	</div>

	<sf:form modelAttribute="pageQuery.searchForm" role="form" cssClass="form-horizontal form-query">
		<mh:query>
			<jsp:attribute name="buttons">
				<mh:button href="security/role/create" class="btn btn-default"><fmt:message key="button.create"/></mh:button>
			</jsp:attribute>
			<jsp:attribute name="quickQuery">
				<%--快速查询摆放区 --%>
				<sf:label path="name" cssClass="col-xs-4 control-label"><fmt:message key="security.role.name"/></sf:label>
				<div class="col-xs-8 form-field">
					<sf:input path="name" cssClass="form-control"/>
				</div>
			</jsp:attribute>
			<jsp:attribute name="advanceQuery">
				<%--独占一行的摆放方式 --%>
				<div class="form-group">
					<div class="col-xs-10 form-field-group">
						<sf:label path="level" cssClass="col-xs-2 control-label"><fmt:message key="security.role.level"/></sf:label>
						<div class="col-xs-10 form-field">
							<mh:dictionary var="levelEnum" key="organLevel"/>
							<sf:checkboxes path="level"  items="${levelEnum}"/>
						</div>
					</div>
				</div>
			</jsp:attribute>
		</mh:query>
	</sf:form>
</div>

<div class="page-query-body">
	<mh:grid id="role" 
			 queryUrl = "security/role/list"
			 showUrl = "security/role/show"
			 deleteUrl = "security/role/remove"
			 updateUrl = "security/role/update"
			 hasPages= "true" 
			 var="roleConfig">
		<mh:col name="name" title="security.role.name" width="40"/>
		<mh:col name="code" title="security.role.code" width="15"/>
		<mh:col name="level" index="levels" title="security.role.level" width="30"/>
		<mh:col name="mutex" title="security.role.mutex" width="15"/>
	</mh:grid>
</div>

<script type="text/javascript" src="<mh:theme code='esl.js'/>"></script>
<script type="text/javascript">
//截取到页面的Ajax请求
$(document).ready(function(){
	require(["chart/mcharts"], function(mcharts) {
		var roleUrl = "<c:url value='/security/account/listRoleUsers'/>";
		var configs = {
				el : "#chart",
				theme : "blue",
				title : "岗位人员分布图",
				rotate : false,
				data :  roleUrl,
				dataZoom : false, 
				ready : function() {
				},
				seriesConfig : [{
					name : '岗位人员统计',
					label : "1",
					type : 'pie',
					center : ['50%', 200],
				    radius : 100,
					stack : "group",
					category : "1",
					value : 2
				}]
			};
			new mcharts.Pie(configs);
	});
});
</script>
