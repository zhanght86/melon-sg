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
				<fmt:message key="melon.staff.outer.hint">
					<fmt:param><span class="userRate">${outerCount}</span></fmt:param>
				</fmt:message>
			</div>
		</div>
		<div class="col-xs-8">
			<div id="chartPie" style="height: 290px;"></div>
		</div>
	</div>
</div>
<div class="panel">
	<div class="page-header">
		<p id="outerTable" style="color: #808080;font-size: 12px;line-height: 20px;margin-bottom: 0;"><fmt:message key="melon.staff.outerTable"/></p>
	</div>
	<div class="panel-body" id="listBody" style="padding: 0">
	</div>
</div>

<script type="text/javascript" src="<mh:theme code='jquery.jqGrid.js'/>"></script>
<script type="text/javascript" src="<mh:theme code='melon.ajaxgrid.js'/>"></script>
<script type="text/javascript" src="<mh:theme code='esl.js'/>"></script>
<script type="text/javascript">
$(document).ready(function() {
	
	$("#listBody").load("<c:url value='/staff/outer/load' />");
	
	require(["chart/mcharts"], function(mcharts) {
		
		/* var dat = <mh:toJson value="${datas}" />; */
		var dat=[['办公室',1]];
		var configs = {
				el : "#chartPie",
				theme : "blue",
				title : '<fmt:message key="melon.staff.outer.chart" />',
				rotate : false,
				data : dat,
				dataZoom : false, 
				ready : function() {
					
				},
				seriesConfig : [{
					name : '所辖部门人员统计',
					label : "0",
					type : 'pie',
					center : ['50%', 150],
				    radius : 100,
					stack : "group",
					category : "0",
					value : 1,
					itemStyle:{
				        normal:{
							label:{
							    show: true,
							    formatter: '部门人员 :0 人 '   //{c} 人  {d}%'
							},
							labelLine :{show:true}
			            }
			        }
				}]
			};
			new mcharts.Pie(configs);
	});
});	

</script>





