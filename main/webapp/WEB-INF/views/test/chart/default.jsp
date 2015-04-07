<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<script type="text/javascript">
		    //定义通用的变量
		    var JsConstant = {
		        baseUrl : '<c:url value="/statics/scripts/"/>'
		    };
		</script>
		
		<title>Default</title>
	</head>
	<body>
	   <div id="cpu-chart" style="height:450px;width:800px;">
	   
	   </div>
	   <script src='<mh:theme code="esl.js"/>' type="text/javascript"></script>
	   <script src='<mh:theme code="jquery.js"/>' type="text/javascript"></script>
	   <script type="text/javascript">
	   
	     require(
	             [
	                 'chart/mcharts'
	             ],
	             function (mc) {
	            	 
	            	 
	            	 var option = {
	            			    title : {
	            			        text: '动态数据',
	            			        subtext: '纯属虚构'
	            			    },
	            			    tooltip : {
	            			        trigger: 'axis'
	            			    },
	            			    legend: {
	            			        data:['最新成交价']
	            			    },
	            			    toolbox: {
	            			        show : true,
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
	            			                    now = new Date(now - 2000);
	            			                }
	            			                return res;
	            			            })()
	            			        }
	            			    ],
	            			    yAxis : [
	            			        {
	            			            type : 'value',
	            			            scale: true,
	            			            name : '价格',
	            			            boundaryGap: [0.2, 0.2]
	            			        }
	            			    ],
	            			    series : [
	            			        {
	            			            name:'最新成交价',
	            			            type:'line',
	            			            data:(function (){
	            			                var res = [];
	            			                var len = 1;
	            			                while (len--) {
	            			                    res.push((Math.random()*10 + 5).toFixed(1) - 0);
	            			                }
	            			                return res;
	            			            })()
	            			        }
	            			    ]
	            			};
	            			
<<<<<<< .mine
	            	 var chart = mc.Echarts.init(document.getElementById("cpu-chart"));
	            	 //加载相关的图形插件就可以了
	            	 require(["echarts/chart/line", "echarts/chart/bar"], function() {
	            		 chart.setOption(option);
	            		 
	            		 var lastData = 11;
	                     var axisData;
	                     var timeTicket = setInterval(function (){
	                         lastData += Math.random() * ((Math.round(Math.random() * 10) % 2) == 0 ? 1 : -1);
	                         lastData = lastData.toFixed(1) - 0;
	                         axisData = (new Date()).toLocaleTimeString().replace(/^\D*/,'');
	                         
	                         var lastData = 11;
		            		 var axisData;
		            		 var timeTicket = setInterval(function (){
		            			    lastData += Math.random() * ((Math.round(Math.random() * 10) % 2) == 0 ? 1 : -1);
		            			    lastData = lastData.toFixed(1) - 0;
		            			    axisData = (new Date()).toLocaleTimeString().replace(/^\D*/,'');
		            			    
		            			    // 动态数据接口 addData
		            			    chart.addData([
		            			     
		            			        [
		            			            0,        // 系列索引
		            			            lastData, // 新增数据
		            			            true,    // 新增数据是否从队列头部插入
		            			            true,    // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
		            			            axisData  // 坐标轴标签
		            			        ]
		            			    ]);
		            			}, 2000);
=======
	            	 //var chart = mc.Echarts.init(document.getElementById("cpu-chart"));
	            	 var chart = new mc.Column({
	            		 el : "#cpu-chart",
	            		 ready : function() {
	            			 this.defaults = option;
	            		 }
>>>>>>> .r138
	            	 });
	            	 
	            	 
                     var lastData = 11;
                     var axisData;
                     var timeTicket = setInterval(function (){
                         lastData += Math.random() * ((Math.round(Math.random() * 10) % 2) == 0 ? 1 : -1);
                         lastData = lastData.toFixed(1) - 0;
                         axisData = (new Date()).toLocaleTimeString().replace(/^\D*/,'');
                         
                         // 动态数据接口 addData
                         chart.chart.addData([
                             [
                                 0,        // 系列索引
                                 Math.round(Math.random() * 1000), // 新增数据
                                 true,     // 新增数据是否从队列头部插入
                                 false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
                             ],
                             [
                                 1,        // 系列索引
                                 lastData, // 新增数据
                                 false,    // 新增数据是否从队列头部插入
                                 false,    // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
                                 axisData  // 坐标轴标签
                             ]
                         ]);
                     }, 2000);
	            	
	             }
	         );
	      });

	   </script>
	</body>
</html>