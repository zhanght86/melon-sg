<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>
<link rel="stylesheet" href="<mh:theme code='jquery.jqGrid.css'/>" media="all" />

<div class="portal-main">
	<div class="page-header">
	  <p>
	  	<fmt:message key="melon.staff.title"/>
	  </p>
	</div>
	<div class="row">
		<div class="col-xs-4">
			<div class="melon-protal-dataArea">
				<fmt:message key="melon.staff.count.hint">
					<fmt:param><span class="userRate">${interCount}</span></fmt:param>
					<fmt:param><span class="userRate">${outerCount}</span></fmt:param>
				</fmt:message>
			</div>
			
			<a href="#" class="left-charts-a" id="interStaff">
				<fmt:message key="melon.staff.inter"/>
			</a>
			
			<a href="#" class="left-charts-a" id="outerStaff">
				<fmt:message key='melon.staff.outer'/>
			</a>
		</div>
		<div class="col-xs-8">
			<div id="chartPie" style="height: 290px;"></div>
		</div>
	</div>
</div>
<div class="panel">
	<div class="page-header">
		<p id="interTable" style="color: #808080;font-size: 12px;line-height: 20px;margin-bottom: 0;"><fmt:message key="melon.staff.interTable"/></p>
		<p id="outerTable" style="color: #808080;font-size: 12px;line-height: 20px;margin-bottom: 0;display:none;"><fmt:message key="melon.staff.outerTable"/></p>
	</div>
	<div class="panel-body" id="listBody" style="padding: 0">
	</div>
</div>

<script type="text/javascript" src="<mh:theme code='jquery.jqGrid.js'/>"></script>
<script type="text/javascript" src="<mh:theme code='melon.ajaxgrid.js'/>"></script>
<script type="text/javascript" src="<mh:theme code='esl.js'/>"></script>
<script type="text/javascript">
$(document).ready(function() {
	
	$("#listBody").load("<c:url value='/staff/inter/load' />");
	
	$("#interStaff").click(function(event) {
		$("#listBody").load("<c:url value='/staff/inter/load' />", function() {
			$("#interTable").show();
			$("#outerTable").hide();
		});
		event.preventDefault();
	});
	
	$("#outerStaff").click(function(event) {
		$("#listBody").load("<c:url value='/staff/outer/load' />", function() {
			$("#interTable").hide();
			$("#outerTable").show();
		});
		event.preventDefault();
	});
	
	require(["chart/mcharts"], function(mcharts) {
		
		var dat = <mh:toJson value="${datas}" />;
		var configs = {
				el : "#chartPie",
				theme : "blue",
				title : "系统人员分布图",
				rotate : false,
				data : dat,
				dataZoom : false, 
				ready : function() {
					
				},
				seriesConfig : [{
					name : '内部人员',
					label : "0",
					type : 'pie',
					center : ['50%', 150],
				    radius : 100,
					stack : "group",
					category : "0",
					value : 1
				},{
					name : '外部人员',
					label : "0",
					type : 'pie',
					center : ['50%', 150],
				    radius : 100,
					stack : "group",
					category : "0",
					value : 1
				}]
			};
			new mcharts.Pie(configs);
	});
});	

</script>





