<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>
<script src="<c:url value="/statics/scripts/echart/js/searchEs.js"/>"></script>
<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <div class="home-portal-body" id="main" style="width:100%;height:100%;"></div>
<script type="text/javascript">
var oldTime = new Date();//记录首次时间
var isAdd = true;




function searchLogByTime(date,obj){
	var end  = date;
	var start = end  - 30*1000;
	var total = 0;
	if(end-oldTime.getTime()>1000*30*10){
		isAdd=false;
	}
	searchEs.groupByName(null,"las-syslog","type","",start,end,10,function(array){
		var count = 0;
		$.each(arrays,function(ix,item){
			count +=item.count;
		})
		
		total = count;
		
		searchNetByTime(start,end,obj,isAdd,total);
	});
	return total;
}

function searchNetByTime(start,end,obj,isAdd,total){
	searchEs.groupByName(null,"las-netflow","type","",start,end,10,function(array){
		var count = 0;
		$.each(arrays,function(ix,item){
			count +=item.count;
		})
		axisData = (new Date(end)).toLocaleTimeString().replace(/^\D*/,'');
		// 动态数据接口 addData
	    obj.addData([
	        [
	            0,        // 系列索引
	            total/3, // 新增数据
	            false,     // 新增数据是否从队列头部插入
	            isAdd     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
	        ],
	        [
	            1,        // 系列索引
	            count/3, // 新增数据
	            false,    // 新增数据是否从队列头部插入
	            isAdd,    // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
	            axisData  // 坐标轴标签
	        ]
	    ]);

	});
		return total;
}	

$(document).ready(function() {
	var mydate = new Date();
	var date = new Date(mydate.getTime());//5分钟前开始
	var title = ['Syslog','Netflow'];
	var syslogvalues = Array();
	var netvalues = Array();
	var dataValue=[
        {
            name:'Syslog',
            type:'line',
            data:[0]
        },
        {
            name:'Netflow',
            type:'line',
            data:[0]
        }
    
	];
	
	require(
	    [
	        'chart/mcharts',
	    ],
function (mc) {
          // 基于准备好的dom，初始化echarts图表
//           var myChart = ec.init(document.getElementById('main')); 
         
     		 
          option = {
        		  title:{
        			  text:"事件趋势图"
        		  },
			    tooltip : {
			        trigger: 'axis'
			    },
			    legend: {
			        data:title
			    },
			    toolbox: {
			        show : true,
			        feature : {
			        	magicType : {show: true, type: ['line', 'bar']},
			            restore : {show: true},
			            saveAsImage : {show: true}
			        }
			    },
			    calculable : true,
			    dataZoom : {
			        show : false,
			        start : 0,
			        end : 10000
			    },
			    xAxis : [
			        {
			            type : 'category',
			            boundaryGap : true,
			            data : (function (){
			            	var temp = date.getTime()-30000;
			                var now = new Date(temp);
			                var res = [];
			                var len = 1;
			                while (len--) {
			                	//alert(now.toLocaleTimeString().replace(/^\D*/,''));
			                    res.unshift(now.toLocaleTimeString().replace(/^\D*/,''));
			                    //now = new Date(now - 1000*30);
			                }
			                return res;
			            })()
			        }
			    ],
			    yAxis : [
			        {
			        	name : '事件量',
			            type : 'value'
			        }
			    ],
			    series : dataValue
          };
          require(["echarts/chart/line", "echarts/chart/bar"], function() {
      		 var myChart = mc.Echarts.init(document.getElementById("main"));
           
          
          // 为echarts对象加载数据 
          myChart.setOption(option); 
           searchLogByTime(date.getTime(),myChart);
          
          var logdata=0;
          var netdata = 0;
          var axisData;
          timeTicket = setInterval(function (){
        	  var dd = new Date();
              logdata = searchLogByTime(dd.getTime(),myChart);
              
          }, 30000);
          }); 
          
      });
	
	
});



	
</script>

    
