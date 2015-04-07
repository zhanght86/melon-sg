<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="hh" uri="http://www.legendsec.com/melon/helper" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<style type="text/css">
	.tab-content{
		padding:20px 0px;
	}
	#levelSource{
		float:right;
		margin-top:4px;
	}
	.page-header{
		border-bottom:0px;
	}
	.block-design{
		margin:0px;
	}
	.chart{
		height:300px;
		margin-bottom:20px;
	}
	.numBg{
		background-color: #f55549;
	}
</style>
<div class="portal-main">
	<div class="page-header">
		<h3><fmt:message key="alarm.home.title"/></h3>
	 	<p>
			<fmt:message key="alarm.home.desc"/>
		</p>
	</div>
	<div class="row">
		<div class="col-xs-4">
			<div class="melon-protal-dataArea">
				<fmt:message key="alarm.home.today.handle">
					<fmt:param><strong class="userRate">${today}</strong></fmt:param>
				</fmt:message><br/>
				<fmt:message key="alarm.home.handled">
					<fmt:param><strong class="userRate">${handle}</strong></fmt:param>
				</fmt:message>
			</div>
			
			<a href="#" class="left-charts-a">
				<fmt:message key='alarm.home.unhandle'/>
			</a>
			<a href="#" class="left-charts-a">
				<fmt:message key='alarm.home.handle'/>
			</a>
			<a href="#" class="left-charts-a" >
				<fmt:message key="alarm.home.all"/>
			</a>
			<a href="#" class="btn btn-primary portal-chart-a">
				<fmt:message key='alarm.home.immediate.handle'/>
			</a>
		</div>
		<div class="col-xs-8">
			<div id="timeBucketChart" style="height: 290px;"></div>
		</div>
</div>
</div>
<div class="row" style="margin-right: -20px; margin-left: 0px;">
		<div class="col-sm-4 organ">
   			<div class="thumbnail">
   				<div class="caption">
					<h4>
						<fmt:message key="alarm.home.device.most">
					    	<fmt:param>5</fmt:param>
						</fmt:message>
					</h4>
					<div class="melon-portal-body">
						<ul class="list-group">
					      <c:forEach items="${devicelist}" var="dl">
					      	<li class="list-group-item">
					      		<a href="#">${dl[0]}</a>
					      		<span class="badge pull-right">${dl[1]}</span>
					      	</li>
					      </c:forEach>
						</ul>
					</div>
				</div>
			</div>
		</div>
		<div class="col-sm-4 organ">
   			<div class="thumbnail">
				<div class="caption">
					<h4>
						<fmt:message key="alarm.home.type.most">
							<fmt:param>5</fmt:param>
						</fmt:message>
					</h4>
					<div class="melon-portal-body">
						<ul class="list-group">
					      <c:forEach items="${typelist}" var="tl">
					      	<li class="list-group-item">
					      		<a href="#">${tl[0]}</a>
					      		<span class="badge pull-right">${tl[1]}</span>
					      	</li>
					      </c:forEach>
						</ul>
					</div>
				</div>
			</div>
		</div>
		<div class="col-sm-4 organ">
   			<div class="thumbnail">
				<div class="caption">
					<h4>
						<fmt:message key="alarm.home.time.longest">
							<fmt:param>5</fmt:param>
						</fmt:message>
					</h4>
					<div class="melon-portal-body">
						<ul class="list-group">
					      <c:forEach items="${longest}" var="lst">
					      	<li class="list-group-item">
					      		<a href="#">${lst[0]}</a>
					      		<span class="badge pull-right">${lst[1]}</span>
					      	</li>
					      </c:forEach>
						</ul>
					</div>
				</div>
   			</div>
   		</div>
</div>
<div class="row block-design">
	<div id="alarmResourceChart" class="chart"></div>
</div>
<script type="text/javascript" src="<mh:theme code='esl.js'/>"></script>
<script type="text/javascript">
$(document).ready(function() {
	var weekUrl="<c:url value='/alarm/base/findWeekCount'/>";
	require(["chart/mcharts"], function(mcharts) {
		//告警时间段分析
		var configs = {
			el : "#timeBucketChart",
			theme : "blue",
			title : "告警近七天时间段分析",
			rotate : false,
			data : weekUrl,
			dataZoom : false,
			ready : function() {
				this.defaults.yAxis[0].name="数量";
				$.extend(this.defaults.yAxis[0],{	//纵坐标
					nameTextStyle:{
						color:"#808080"
					},
					axisLabel:{
						textStyle:{
							color:"#808080"
						}
					}
				});
				$.extend(this.defaults.xAxis[0],{	//横坐标
					axisLabel:{
						textStyle:{
							color:"#808080"
						}
					}
				});
			},
			seriesConfig : [{
				name : '告警次数',
				type : 'bar',    //以什么类型展示    
				stack : "group",
				category : "0",//横坐标
				value : "1"  //纵坐标
			}]
		};
		
		new mcharts.Column(configs);
		//告警来源分析 las sni 平台 
		//安管平台,1:安全监控,2:日志审计,3:漏洞扫描,4:关联分析,5:安全人员
		var datas =[{
			"mon" : "安全监控",
			"monNum" : 120,
			"aud" : "日志审计",
			"audNum" : 90,
			"att" : "关联分析",
			"attNum" : 50,
			"ls" : "漏洞扫描",
			"lsNum" : 50,
			"renyuan":"安全人员",
			"ryNum":30
		}, {
			"mon" : "未被监控",
			"monNum" : 30,
			"aud" : "未被审计",
			"audNum" : 60,
			"att" : "未被关注",
			"attNum" : 10,
			"ls" : "未被扫描",
			"lsNum" : 150
		}];
		var labelTop = {
		    normal : {
		        label : {
		            show : true,
		            position : 'center',
		            textStyle: {
		                baseline : 'bottom'
		            }
		        },
		        labelLine : {
		            show : false
		        }
		    }
		};
		var labelBottom = {
		    normal : {
		        color: '#ccc',
		        label : {
		            show : true,
		            position : 'center',
		            formatter : function (a,b,c,d){
		            	return 100 - d + '%'
		            },
		            textStyle: {
		                baseline : 'top'
		            }
		        },
		        labelLine : {
		            show : false
		        }
		    },
		    emphasis: {
		        color: 'rgba(0,0,0,0)'
		    }
		};
		var pieConfigs = {
				el : "#alarmResourceChart",
				theme : "blue",
				data : datas,
				title : "告警来源分布图",
				ready : function() {
					$.each(this.defaults.series, function(index, item) {
						item.data[1].itemStyle=labelBottom;
						item.data[0].itemStyle=labelTop;
					});
				},
				seriesConfig : [{
					name : "las",
					type : 'pie',
					center : [120, 140],
				    radius : [40, 70],
					category : "mon",
					value : "monNum"
				},{
					name : "sni",
					type : 'pie',
					center : [290, 140],
				    radius : [40, 70],
					category : "aud",
					value : "audNum"
				},{
					name : "防火墙",
					type : 'pie',
					center : [460, 140],
				    radius : [40, 70],
					category : "ls",
					value : "lsNum"
				},{
					name : "路由器",
					type : 'pie',
					center : [630, 140],
				    radius : [40, 70],
					category : "att",
					value : "attNum"
				},{
					name : "安管平台",
					type : 'pie',
					center : [800, 140],
				    radius : [40, 70],
					category : "renyuan",
					value : "ryNum"
				}]
			};
			new mcharts.Pie(pieConfigs);
		});
	});
</script>