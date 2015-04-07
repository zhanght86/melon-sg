<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>
<%--@Author 甘焕--%>
<div class="home-portal-body" id="chart3">

</div>
<script type="text/javascript">
	$(document).ready(function() {
		require(["chart/mcharts"], function(mcharts) {
			var configs = {
					el : "#chart3",
					theme : "blue",
					title : "安全评价分布",
					rotate : false,
					data : "<c:url value='/statics/json/datas-array.json'/>",
					dataZoom : false,
					ready : function() {
						//console.debug(this.defaults);
						//this.defaults.xAxis[0].boundaryGap = true;
						//console.debug(this.defaults);
						//this.defaults.xAxis[0].axisLabel.rotate = 45;
					},
					seriesConfig : [{
						name : '事件',
						label : "0",
						type : 'pie',
						center : ['50%', 240],
					    radius : [100, 140],
						stack : "group",
						category : "0",
						value : 1
					},{
						name : '告警',
						label : "0",
						type : 'pie',
						center : ['50%', 240],
						radius : [0, 70],
						category : "0",
						value : 2
					}]
				};
				var pie = new mcharts.Pie(configs);
		});
	});
</script>