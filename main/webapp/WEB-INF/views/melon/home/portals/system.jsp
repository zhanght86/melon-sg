<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>
<%--@Author 甘焕--%>
<div class="home-portal-body" id="system_chart">

</div>
<script type="text/javascript">
	$(document).ready(function() {
		/* var cpu = ${cpu},
			memory = ${memory}; */
		require(["chart/mcharts"], function(mcharts) {
			
			$.getJSON("", function(data) {
				//
				var seriesConfigs=[];
				$.each(data, function(key, value) {
					seriesConfigs.push({
						name : key,
						type : "line",
						data : [value]
					});
				});
			});
			var configs = {
					el : "#system_chart",
					theme : "blue",
					title : "<fmt:message key='system.home.gaugeTitle'/>",
					data:[["CPU", 24, 25]],
					  	  
					rotate : false,
					dataZoom : false,
					ready : function() {
						this.defaults.toolbox = false;
						this.defaults.legend = false;
						//标题的样式 
						$.extend(this.defaults.title, {
							x : 'center',
							y : 'bottom',
							textStyle : {
								fontSize : 12,
								color : "#808080"
							}
						});
						
					},
					seriesConfig : [{
						radius : 100,
						name : '内存',
						label : "0",		//标题
						type : 'gauge',
					//	category : "0",
						value : 1,
						axisLine: {
			            	// 坐标轴线
			            	show : true,
			                lineStyle: {       // 属性lineStyle控制线条样式
			                    color : [
		 		                	[0.2, "#5cd193"],  //百分比   ，  颜色值
		 		                	[0.8, "#4cb4e0"],
		 		                	[1, "#eb4c46"],
		 		                ]
			                }
			                
			            },
			            pointer: {
			                width : 10,
			                length: '80%',
			                color: '#f58a2c'
			            }, 
			            title: {	//仪表盘标题样式 
		            	    show : true,
		            	    offsetCenter: ['-20%', '87%'],
		            	    textStyle: {
		            	        color: '#2e9bd1',
		            	        fontSize : 12
		            	    }
			            },
			            detail : {
							offsetCenter: [20, 67],
							textStyle: {       
							// 其余属性默认使用全局文本样式，详见TEXTSTYLE
			                    fontSize : 18,
			                    color: '#2e9bd1'
			                }
						}
					},{
						radius : 55,
						name : 'CPU',
						label : "1",
						type : 'gauge',
						category : "0",
						value : 2,
						calculable:false,
						splitNumber:1,
						axisLine: {
			            	// 坐标轴线
			            	show : true,
			                lineStyle: {       // 属性lineStyle控制线条样式
			                    color : [
		 		                	[0.2, "#5cd193"],
		 		                	[0.4, "#4cb4e0"],
		 		                	[1, "#eb4c46"],
		 		                ]
			                }
			            },
			            axisLabel:{
			            	show: false
			            },
						pointer: {
			                width : 10,
			                length: '60%',
			                color: 'gray'
			            },
			            title: {	//仪表盘标题样式 
		            	    show : true,
		            	    offsetCenter: ['-35%', '110%'],
		            	    textStyle: {
		            	        color: '#5cd193',
		            	        fontSize : 12
		            	    }
			            },
						detail : {
							offsetCenter: [15, 40],
							textStyle: {       
							// 其余属性默认使用全局文本样式，详见TEXTSTYLE
			                    fontSize : 18,
			                    color: '#5cd193'
			                }
						}
					}]
				};
				var gauge = new mcharts.Gauge(configs);
		});
	});
</script>