<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>
<style type="text/css">
	
	.melon-portal-unit-detailCounts{
		margin:10px 0px 0px 30px;
		color:#fff; 
	}
	
	#organ-list {
		font-size : 12px;
		color : #fff;
		height : 260px;
	}
	#organ-list li {
		padding : 10px;
	}
	
</style>

<div class="portal-main">
	<div class="page-header">
	 	<p>
			<fmt:message key="system.home.desc"/>
		</p>
	</div>
	<div class="row">
		<div class="col-xs-4">
			<div class="melon-protal-dataArea">
				<fmt:message key="system.cpu"/><br/><span id="cpuUsed" class="userRate"> </span><br/>
				<fmt:message key="system.memory"/><br/><span id="memoryUsed" class="userRate"></span>
			</div>
			<a href="#" class="btn btn-primary portal-chart-a"><fmt:message key='system.home.searchSystem'/></a>
		</div>
		<div class="col-xs-8">
			<div id="chart" style="height: 250px;"></div>
		</div>
	</div>
</div>

<div class="row" style="margin:0px -20px;">
	
	<%--单位管理 --%>
	<div class="col-xs-4 melon-portal">
		<a href="<c:url value='/organ/organization/tree'/>" class="melon-portal-heading">
			<fmt:message key="system.home.unitManager"/>
		</a>
		<div class="melon-portal-body" style="background-color: #48b3e0;">
			<h1>
				${alldwSum}
				<small><fmt:message key="system.home.number"/></small>
			</h1>
			<div class="melon-portal-unit-detailCounts">
				<span>
					<fmt:message key="system.home.provinceUnit">
						<fmt:param>${zsdwCount}</fmt:param>
					</fmt:message>
				</span><br/>
				<span>
					<fmt:message key="system.home.cityUnit">
						<fmt:param>${cityCount}</fmt:param>
					</fmt:message>
				</span>
			</div>
		</div>
		<div class="melon-portal-footer"></div>
	</div>
	
	<%-- 用户管理 --%>
	<div class="col-xs-4 melon-portal">
		<a href="<c:url value='/security/account/listOrgans'/>" class="melon-portal-heading">
			<fmt:message key="system.home.userManager"/>
		</a>
		<div class="melon-portal-body" style="background-color: #76cc7c;">
			<h1>
				${userCount}
				<small><fmt:message key="system.home.count"/></small>
			</h1>
		</div>
		<div class="melon-portal-footer"></div>
	</div>  
	
	<%-- 角色管理 --%>
	<div class="col-xs-4 melon-portal" style="border-right:0px;">
		<a href="<c:url value='/security/role/list'/>" class="melon-portal-heading">
			<fmt:message key="system.home.jobManager"/>
		</a>
		<div class="melon-portal-body">
			<ul class="list-group">
		      <c:forEach items="${roles}" var="role">
		      	<li class="list-group-item">
		      		<a href="<c:url value='/security/role/show/${role[0]}'/>">${role[1]}</a>
		      		<span style="float:right;">${role[2]}</span>
		      	</li>
		      </c:forEach>
			</ul>
		</div>
		<div class="melon-portal-footer"></div>
	</div>
	
</div>

	
<div class="row" style="margin:0px -20px;">
	
	<div class="col-xs-8 melon-portal" style="border-bottom:0px;border-top:0px;">
		<a href="<c:url value='/security/account/online'/>" class="melon-portal-heading">
			在线用户
		</a>
		<div class="melon-portal-body" style="height: 260px;">
			<div id="chartPie" style="height: 100%;"></div>
		</div>
	</div>
	
	<div class="col-xs-4 melon-portal" style="border-bottom:0px;border-top:0px;border-right:0px;">
		<div class="melon-portal-heading">
			<fmt:message key="system.home.activeUnitMana"/>
		</div>
		<ul class="melon-portal-body ui-helper-reset" id="organ-list">
			<li style="background-color: #83d5f2;height:120px;">
				<fmt:message key="system.home.cityUnit">
					<fmt:param>${cityCount}</fmt:param>
				</fmt:message> 
			</li>
			<li style="background-color: #308fc7;height:90px;">
				<fmt:message key="system.home.provinceUnit">
					<fmt:param>${zsdwCount}</fmt:param>
				</fmt:message>
			</li>
			<li style="background-color: #5c5c5c;height:50px;">
				<fmt:message key="system.home.noActiveUser">
					<fmt:param>${unActiveCount}</fmt:param>
				</fmt:message>
			</li>
		</ul>
	</div>
</div>

<script type="text/javascript" src="<mh:theme code='esl.js'/>"></script>
<script type="text/javascript">
var datas = <mh:toJson value="${onlines}"/>,
	rotate = 0,
	count = 0,
	end = 100;
	
$(document).ready(function() {
	
	var getCpu = ${cpu}.toFixed(2),
		getMem = ${mem}.toFixed(2);
	
	$("#cpuUsed").text(getCpu+"%");
	$("#memoryUsed").text(getMem+"%");
	
	require(["chart/mcharts"], function(mcharts) {
		var configs = {
			el : "#chart",
			theme : "blue",
			title : "<fmt:message key='system.home.gaugeTitle'/>",
			data:[["CPU", getCpu, getMem]],
			  	  
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
		var onlineUrl = '<c:url value="/security/account/listOnlineUsers"/>';
		var configs = {
				el : "#chartPie",
				theme : "blue",
				//title : "<fmt:message key='system.home.pieTitle'/>",
				rotate : false,
				data : onlineUrl,
				dataZoom : false, 
				ready : function() {
					this.defaults.legend = false;
				},
				seriesConfig : [{
					name : "<fmt:message key='system.home.pieLegend'/>",
					type : 'pie',
					center : ['50%', 100],
				    radius : 100,
					stack : "group",
					category : "0",
					value : 1,
					itemStyle:{
				        normal:{
							label:{
							    show: true,
							    formatter: '{b} : {c} ({d}%)'
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