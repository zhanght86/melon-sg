<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>
<div class="home-portal-body" id="memoryBar" style="width:100%;height:100%;">
</div>
<script type="text/javascript">
	$(document).ready(function() {
		
		require(
	             [
	                 'chart/mcharts'
	             ],
	             function (mc) {
	            	 var option = {
	            			    title : {
	            			    	text : '内存监控'
	            			    },
	            			    tooltip : {
	            			        trigger: 'axis'
	            			    },
	            			    legend: {
	            			        data:['系统内存']
	            			    },
	            			    toolbox: {
	            			        show : false,
	            			        feature : {
	            			            mark : {show: true},
	            			            dataView : {show: true, readOnly: false},
	            			            magicType : {show: true, type: ['line', 'bar']},
	            			            restore : {show: true},
	            			            saveAsImage : {show: true}
	            			        }
	            			    },
	            			    dataZoom : {
	            			        show : false,
	            			        start : 0,
	            			        end : 100
	            			    },
	            			    xAxis : [
	            			        {
	            			            type : 'category',
	            			            boundaryGap : true,
	            			            data : (function (){
	            			                var now = new Date();
	            			                var res = [];
	            			                var len = 10;
	            			                while (len--) {
	            			                    res.unshift(now.toLocaleTimeString().replace(/^\D*/,''));
	            			                    now = new Date(now - 10000);
	            			                }
	            			                return res;
	            			            })()
	            			        }
	            			    ],
	            			    yAxis : [
	            			        {
	            			            type : 'value',
	            			            scale: true,
	            			            name : '利用率(%)',
	            			            boundaryGap: [0.2, 0.2]
	            			        }
	            			    ],
	            			    series : []
	            			};
	            	var datas = <mh:toJson value="${result}" />;
	     			$.each(datas, function(index, data) {
	     				$.each(data, function(key, value) {
	     					var line = {
	     							name : "系统内存",
	     							type : "line",
	     							data : [value]
	     					}
	     					option.series.push(line);
	     				});
	     			});
	            	
	            	 //加载相关的图形插件就可以了
	            	 require(["echarts/chart/line", "echarts/chart/bar"], function() {
	            		 var chart = mc.Echarts.init(document.getElementById("memoryBar"));
	            		 chart.setOption(option);
	            		 var timeTicket = setInterval(function (){
	            			 	$.post("<c:url value='/home/portal/cpu' />", function(datas) {
	            			 		var axisData = (new Date()).toLocaleTimeString().replace(/^\D*/,'');
	            			 		$.each(datas, function(index, data) {
	            			 			var i = 0,
	            			 				tmps = [];
	            	     				$.each(data, function(key, value) {
	            	     					var tmp = [
	            	     							i,
	            	     							value,
	            	     							true,
	            	     							true,
	            	     							axisData
	            	     					]
	            	     					tmps.push(tmp);
	            	     					i = i+1;
	            	     				});
	            	     				chart.addData(tmps);
	            	     			});
	            			 		
	            			 	});
	            			}, 10000);
	            	
	             }
	         );
	      });

	});
</script>