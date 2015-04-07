<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>
<%--@Author 甘焕--%>
<div class="home-portal-body" id="eventBar">
<div class="event_hint" style="margin-top:200px;">数据加载中...</div>
</div>
<script type="text/javascript">
	$(document).ready(function() {
		var start = ${start},
			end = ${end};
		require(["chart/mcharts"], function(mcharts) {
			var configs = {
				el : "#eventBar",
				theme : "blue",
				title : "7天安全事件TOP10",
				data : null,
				dataZoom : false,		//数据区域缩放，直角坐标系图表有效
				
				ready : function() {
					
					this.defaults.xAxis[0].axisLabel.rotate = 25;
					this.defaults.xAxis[0].boundaryGap = true;//类目起始和结束两端空白策略,默认是true即两端是空白的
				},
				seriesConfig : [{
					name : '事件数量',
					type : 'bar',    //以什么类型展示    
					category : "term",//横坐标
					value : "count"  //纵坐标
				}]
			};
			var parmas = [],
				name = "category";
			searchEs.groupByName(null, "las-syslog", name, parmas, start, end, 10, function(array) {
				if(array.length < 0) {
					$(".event_hint").text("当前无数据！")
				} else {
					$(".event_hint").hide();
					configs.data = array;
					var column = new mcharts.Column(configs);
				}

			});
		});
	});
</script>