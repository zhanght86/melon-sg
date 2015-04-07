<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>
<%--@Author 甘焕--%>
<div class="home-portal-body" id="diskBar">
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
	            			    	text : '硬盘使用情况'
	            			    },
	            			    tooltip : {
	            			        trigger: 'axis'
	            			    },
	            			    legend: {
	            			        data:["已使用", "空闲"]
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
	            			            data : []
	            			        }
	            			    ],
	            			    yAxis : [
	            			        {
	            			            type : 'value',
	            			            scale: true,
	            			            name : '空间大小(M)',
	            			            boundaryGap: [0.2, 0.2]
	            			        }
	            			    ],
	            			    series : []
	            			};
	            	var datas = <mh:toJson value="${result}" />,
		            	freeSerie = {
	            			name : "空闲",
 							type : "bar",
 							stack : "disk",
 							data : []
	            		},
	            		usedSerie = {
	            			name : "已使用",
 							type : "bar",
 							stack : "disk",
 							data : []
	            		};
	     			$.each(datas, function(index, item) {
	     				freeSerie.data.push(item.free);
	     				usedSerie.data.push(item.used);
	     				option.xAxis[0].data.push(item.name);
	     			});
	     		
	     			 option.series.push(freeSerie);
	     			 option.series.push(usedSerie);
	            	 //加载相关的图形插件就可以了
	            	 require(["echarts/chart/line", "echarts/chart/bar"], function() {
	            		 var chart = mc.Echarts.init(document.getElementById("diskBar"));
	            		 chart.setOption(option);
	             }
	         );
	      });

	});
		/* var datas = <mh:toJson value="${result}" />;
		console.log(datas);
		require(["chart/mcharts"], function(mcharts) {
			var configs = {
				el : "#diskBar",
				//theme : "blue",
				title : "硬盘使用情况",
				data : datas,
				dataZoom : false,
				//数据区域缩放，直角坐标系图表有效
				ready : function() {
					
					this.defaults.xAxis[0].axisLabel.rotate = 15;
					this.defaults.toolbox.show = false;
					$.extend(this.defaults.yAxis[0], {
						name : '单位(M)',
						boundaryGap : [0.2, 0.2],
						splitArea : false,
					});
					console.log(this.defaults);
				},
				seriesConfig : [
				{
					name : '已使用',
					type : 'bar',    //以什么类型展示    
					category : "name",//横坐标
					stack : "disk",
					value : "used"  //纵坐标
				},
				{
					name : '空闲',
					type : 'bar',    //以什么类型展示    
					category : "name",//横坐标
					stack : "disk",
					value : "free"  //纵坐标
				}]
			};
			var column = new mcharts.Column(configs);

		});
	}); */
</script>